package ism.spring.exmaen.gestion.employes.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/employes")
public interface IEmployeController extends IController<Long> {
    @GetMapping("/departement/{code}")
    String findAllByDepartement(Model model, @PathVariable("code") String code,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "0") int page);
}
