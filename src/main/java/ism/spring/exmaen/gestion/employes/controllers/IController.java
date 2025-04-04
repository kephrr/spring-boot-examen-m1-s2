package ism.spring.exmaen.gestion.employes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface IController <Key>{
    @GetMapping("")
    String findAll();

    @GetMapping("/{id}")
    String findByMatr(@PathVariable("id") Key id);

    @GetMapping("/delete/{id}")
    String deleteByMatr(@PathVariable("id") Key id);

    @PostMapping("/edit")
    String edit();

    @PostMapping("")
    String save();
}
