package ism.spring.exmaen.gestion.employes.controllers.impl;

import ism.spring.exmaen.gestion.employes.controllers.IDepartementController;
import ism.spring.exmaen.gestion.employes.controllers.form.DepartementsFrom;
import ism.spring.exmaen.gestion.employes.controllers.form.EmployesForm;
import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.services.DepartementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class IDepartementControllerImpl implements IDepartementController {
    private final DepartementService departementService;
    private final String BASE_URL = "departements";
    @Override
    public String findAll(Model model,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(defaultValue = "0") int page) {
        Page<Departement> data = departementService.findAll(PageRequest.of(page, size));
        model.addAttribute("departements", data);

        model.addAttribute("pages", new int[data.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNext", data.hasNext());
        model.addAttribute("hasPrevious", data.hasPrevious());
        return BASE_URL+"/index";
    }

    @Override
    public String findByMatr(Model model,String id) {
        Optional<Departement> opt = departementService.findById(id);
        if(opt.isEmpty()) {
            model.addAttribute("errorMessage", "Ce département n'existe pas");
            return BASE_URL+"/index";
        }
        Departement dep = opt.get();
        DepartementsFrom form = new DepartementsFrom();
        form = form.toForm(dep);
        model.addAttribute("departementForm", form);
        model.addAttribute("departement", dep);
        return BASE_URL+"/show";
    }

    @Override
    public String deleteByMatr(Model model,String code) {
        Optional<Departement> byId = departementService.findById(code);
        if(byId.isEmpty()) {
            model.addAttribute("errorMessage", "Ce departement n'existe PAS");
            return BASE_URL+"/index";
        }
        int IsDeleted = departementService.delete(byId.get());
        String returnMessage = IsDeleted==1?"Supprimé avec succès":"Ce departement a déjà été supprimé";
        model.addAttribute("returnMessage", returnMessage);
        return BASE_URL+"/index";
    }

    @Override
    public String edit(Model model, DepartementsFrom form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<String> errors =new ArrayList<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.add(fieldError.getDefaultMessage()));
            model.addAttribute("errors", errors);
            model.addAttribute("departement", new Departement());
            model.addAttribute("departementForm", form);
            return BASE_URL+"/show";
        }else{
            try {
                Departement editingDep = form.toEntity();
                System.out.println("FORM TO ENTITY");
                Departement dep = departementService.findById(form.getCodeDep()).orElse(null);
                assert dep != null;
                dep.setNomDep(editingDep.getNomDep());
                dep.setCodeDep(editingDep.getCodeDep());
                departementService.save(dep);
                System.out.println("DEPARTEMENT MIS A JOUR");

                model.addAttribute("success", "Departement modifié avec succès");
                model.addAttribute("departementForm", editingDep);
                model.addAttribute("departement", dep);
                return BASE_URL+"/show";
            } catch (Exception e) {
                System.out.println("Exception : "+e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String save(Model model, DepartementsFrom form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String, String> errors =new HashMap<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(),fieldError.getDefaultMessage()));
            model.addAttribute("errors", errors);
        }else{
            try {
                Departement newDep = form.toEntity();
                departementService.save(newDep);
                model.addAttribute("success", "Departement crée avec succès");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return create(model);
    }

    @Override
    public String create(Model model) {
        model.addAttribute("departementForm", new DepartementsFrom());
        return BASE_URL+"/create";
    }
}
