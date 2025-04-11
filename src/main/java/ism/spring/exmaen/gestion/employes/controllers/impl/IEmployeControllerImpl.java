package ism.spring.exmaen.gestion.employes.controllers.impl;

import ism.spring.exmaen.gestion.employes.controllers.IEmployeController;
import ism.spring.exmaen.gestion.employes.controllers.form.EmployesForm;
import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.entities.Employe;
import ism.spring.exmaen.gestion.employes.services.DepartementService;
import ism.spring.exmaen.gestion.employes.services.EmployeService;
import jakarta.validation.Valid;
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
public class IEmployeControllerImpl implements IEmployeController {
    private final EmployeService employeService;
    private final DepartementService departementService;

    @Override
    public String findAll(Model model, int size, int page) {
        Page<Employe> data =  employeService.findAll(PageRequest.of(page, size));
        model.addAttribute("employes", data);

        model.addAttribute("pages", new int[data.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("hasNext", data.hasNext());
        model.addAttribute("hasPrevious", data.hasPrevious());
        return "employes/index";
    }

    @Override
    public String findByMatr(Model model,Long id) {
        Optional<Employe> opt = employeService.findById(id);
        if(opt.isEmpty()){
            model.addAttribute("errorMessage", "Cet employé n'existe PAS");
            return "employes/index";
        }
        Employe emp = opt.get();
        EmployesForm form = new EmployesForm();
        form = form.toForm(emp);

        Map<String, String> departements =new HashMap<>();
        departementService.findAll().forEach(d -> {
            departements.put(d.getCodeDep(), d.getNomDep());
        });
        model.addAttribute("departements", departements);
        model.addAttribute("employeForm", form);
        model.addAttribute("employe", emp);
        model.addAttribute("codeDep", emp.getDepartement().getCodeDep());
        return "employes/show";
    }

    @Override
    public String deleteByMatr(Model model, Long id) {
        Optional<Employe> opt = employeService.findById(id);
        if(opt.isEmpty()){
            model.addAttribute("errorMessage", "Cet employé n'existe PAS");
            return "employes/index";
        }
        int IsDeleted = employeService.delete(opt.get());
        String returnMessage = IsDeleted==1?"Supprimé avec succès":"Ce employé a déjà été supprimé";
        model.addAttribute("returnMessage", returnMessage);
        return "employes/index";
    }

    @Override
    public String save(Model model, @Valid EmployesForm form, BindingResult result) {
        if(result.hasErrors()){
            Map<String, String> errors =new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(),fieldError.getDefaultMessage()));
            model.addAttribute("errors", errors);
        }else{
            try {
                Departement dep = departementService.findById(form.getCodeDep()).orElse(null);
                Employe newEmp = form.toEntity();
                newEmp.setDepartement(dep);
                employeService.save(newEmp);
                model.addAttribute("success", "Employé crée avec succès");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return create(model);
    }

    @Override
    public String create(Model model) {
        Map<String, String> departements =new HashMap<>();
        departementService.findAll().forEach(d -> {
            departements.put(d.getCodeDep(), d.getNomDep());
        });
        model.addAttribute("departements", departements);
        model.addAttribute("employeForm", new EmployesForm());
        return "employes/create";
    }

    @Override
    public String findAllByDepartement(Model model, String code, @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "0") int page) {
        Departement departement = departementService.findById(code).orElse(null);
        Page<Employe> employes = employeService.findAllByDepartements(PageRequest.of(page, size), departement);
        model.addAttribute("employes", employes);
        if(departement==null){
            model.addAttribute("errorMessage", "Cet departement n'existe pas");
        }else{
            model.addAttribute("departement", departement.getNomDep());
        }
        return "employes/by-departements";
    }

    @Override
    public String edit(Model model, EmployesForm form, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors =new ArrayList<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.add(fieldError.getDefaultMessage()));
            model.addAttribute("errors", errors);
        }else{
            try {
                Employe editingEmp = form.toEntity();
                Employe emp = employeService.findById(editingEmp.getMatr()).orElse(null);
                assert emp != null;
                emp.setNom(editingEmp.getNom());
                emp.setPrenom(editingEmp.getPrenom());
                emp.setSalaireBase(editingEmp.getSalaireBase());
                employeService.save(emp);
                model.addAttribute("success", "Modifié avec succès");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return findByMatr(model, form.getMatr());
    }
}








