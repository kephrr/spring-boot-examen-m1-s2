package ism.spring.exmaen.gestion.employes.controllers;

import ism.spring.exmaen.gestion.employes.controllers.form.DepartementsFrom;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/departements")
public interface IDepartementController extends IController<String, DepartementsFrom>{
}
