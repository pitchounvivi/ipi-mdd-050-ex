package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/employes")
public class EmployeController {

    @Autowired
    private EmployeRepository employeRepository;

    // Cette méthode est utilisée pour être appliquée à l'adresse :
    // file:///D:/ipi/java/ipi-web-050-web/index.html
    // et
    // http://localhost:5367/employes/count
    @RequestMapping(
            value = "/count",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Long countEmploye(){
        return employeRepository.count();
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Employe unEmploye (
                @PathVariable (value = "id")
                Long id)
    {
        Optional <Employe> unEmploye = employeRepository.findById(id);
        return unEmploye.get();
    }

}
