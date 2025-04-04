package ism.spring.exmaen.gestion.employes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/employes")
public interface IEmployeController extends IController<Long> {
    @GetMapping("/departement/{code}")
    String findAllByDepartement(@PathVariable("code") String code);
}
