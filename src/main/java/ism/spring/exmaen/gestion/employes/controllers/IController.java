package ism.spring.exmaen.gestion.employes.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IController <Key>{
    @GetMapping("")
    String findAll(Model model, @RequestParam(defaultValue = "10") int size,
                   @RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}")
    String findByMatr(Model model,@PathVariable("id") Key id);

    @GetMapping("/delete/{id}")
    String deleteByMatr(Model model,@PathVariable("id") Key id);

    @PostMapping("/edit")
    String edit(Model model);

    @PostMapping("")
    String save(Model model);
}
