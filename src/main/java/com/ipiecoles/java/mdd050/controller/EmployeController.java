package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Commercial;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                @PathVariable (value = "id") Long id)
    {
        Optional <Employe> unEmploye = employeRepository.findById(id);
        return unEmploye.get();
    }

    @RequestMapping(
            params = "matricule",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Employe parMatricule(
            @RequestParam("matricule") String matricule
    )
    {
        Employe uneMatricule = employeRepository.findByMatricule(matricule);
        return uneMatricule;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"page","size","sortProperty","sortDirection"}
    )
    public Page<Employe> listEmployes(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sortProperty,
            @RequestParam String sortDirection
            ){
        return employeRepository.findAll(PageRequest.of(page, size,
                Sort.Direction.fromString(sortDirection), sortProperty));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employe createEmploye(
            @RequestBody Commercial employe
    ){
        return employeRepository.save(employe);
    }
}
