package ism.spring.exmaen.gestion.employes.controllers;

import ism.spring.exmaen.gestion.employes.controllers.form.EmployesForm;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/employes")
public interface IEmployeController extends IController<Long, EmployesForm> {
    @GetMapping("/departement/{code}")
    String findAllByDepartement(Model model, @PathVariable("code") String code,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "0") int page);

}
