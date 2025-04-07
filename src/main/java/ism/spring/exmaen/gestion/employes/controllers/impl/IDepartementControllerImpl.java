package ism.spring.exmaen.gestion.employes.controllers.impl;

import ism.spring.exmaen.gestion.employes.controllers.IDepartementController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IDepartementControllerImpl implements IDepartementController {
    @Override
    public String findAll(Model model, @RequestParam(defaultValue = "10") int size,
                          @RequestParam(defaultValue = "0") int page) {
        return "departements/departements";
    }

    @Override
    public String findByMatr(Model model,String id) {
        return "";
    }

    @Override
    public String deleteByMatr(Model model,String id) {
        return "";
    }

    @Override
    public String edit(Model model) {
        return "";
    }

    @Override
    public String save(Model model) {
        return "";
    }
}
