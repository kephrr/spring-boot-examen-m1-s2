package ism.spring.exmaen.gestion.employes.controllers.impl;

import ism.spring.exmaen.gestion.employes.controllers.IDepartementController;
import ism.spring.exmaen.gestion.employes.controllers.form.EmployesForm;
import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.services.DepartementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IDepartementControllerImpl implements IDepartementController {
    private final DepartementService departementService;
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
        return "departements/index";
    }

    @Override
    public String findByMatr(Model model,String id) {
        Departement departement = departementService.findById(id).orElse(null);
        if(departement != null) {
            model.addAttribute("departement", departement);
        }else{
            model.addAttribute("returnMessage", "Ce département n'existe pas");
        }
        return "";
    }

    @Override
    public String deleteByMatr(Model model,String id) {
        return "";
    }

    @Override
    public String edit(Model model, EmployesForm form, BindingResult bindingResult) {
        return "";
    }

    @Override
    public String save(Model model, EmployesForm form, BindingResult bindingResult) {
        return "";
    }

    @Override
    public String create(Model model) {
        return "";
    }
}
