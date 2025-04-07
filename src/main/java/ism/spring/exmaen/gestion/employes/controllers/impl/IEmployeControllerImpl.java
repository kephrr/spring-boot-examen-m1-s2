package ism.spring.exmaen.gestion.employes.controllers.impl;

import ism.spring.exmaen.gestion.employes.controllers.IEmployeController;
import ism.spring.exmaen.gestion.employes.data.entities.Departement;
import ism.spring.exmaen.gestion.employes.data.entities.Employe;
import ism.spring.exmaen.gestion.employes.services.DepartementService;
import ism.spring.exmaen.gestion.employes.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IEmployeControllerImpl implements IEmployeController {
    private final EmployeService employeService;
    private final DepartementService departementService;

    @Override
    public String findAll(Model model,int size, int page) {
        Page<Employe> data =  employeService.findAll(PageRequest.of(page, size));
        model.addAttribute("employes", data);
        return "employes/index";
    }

    @Override
    public String findByMatr(Model model,Long id) {
        Optional<Employe> opt = employeService.findById(id);
        if(opt.isEmpty()){
            model.addAttribute("errorMessage", "Cet employé n'existe PAS");
            return "employes/index";
        }
        model.addAttribute("employe", opt.get());
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
    public String edit(Model model) {
        return "employes/employes";
    }

    @Override
    public String save(Model model) {
        return "";
    }

    @Override
    public String findAllByDepartement(Model model, String code, @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "0") int page) {
        Departement departement = departementService.findById(code).orElse(null);
        Page<Employe> employes = employeService.findAllByDepartements(PageRequest.of(page, size), departement);
        model.addAttribute("employes", employes);
        if(departement==null){
            model.addAttribute("returnMessage", "Cet departement n'existe pas");
        }else{
            model.addAttribute("departement", departement.getNomDep());
        }
        return "employes/by-departements";
    }
}








