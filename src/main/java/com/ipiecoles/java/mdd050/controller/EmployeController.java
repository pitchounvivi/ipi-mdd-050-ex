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

import javax.persistence.EntityNotFoundException;
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

        if(unEmploye.isEmpty()){
            throw new EntityNotFoundException("Employé " + id + "non trouvé");//erreur 404
        }

        return unEmploye.get();

    }


    // Autre possibilité vu durant la correction
//    @RequestMapping(method = RequestMethod.GET,
//            value = "/{id}",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public Optional<Employe> getEmploye(@PathVariable(value = "id") String id){
//        Long monId = null;
//        try {
//            monId = Long.parseLong(id);
//        } catch (Exception e){
//            //Lever une erreur 400
//            throw new IllegalArgumentException("L'identifiant " + id + " n'est pas numérique !");
//        }
//        Optional<Employe> optionalEmploye = employeRepository.findById(monId);
//        if(optionalEmploye.isEmpty()){
//            //Erreur 404
//            throw new EntityNotFoundException("L'employé d'identifiant " + id + " n'a pas été trouvé !");
//        }
//        return optionalEmploye;
//    }


    @RequestMapping(
            params = "matricule",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Employe parMatricule(
            @RequestParam("matricule") String matricule
    ){
        Employe uneMatricule = employeRepository.findByMatricule(matricule);

        if(uneMatricule == null){
            throw new EntityNotFoundException("Matricule " + matricule +" non trouvé 404");//matricule == null
        }

        return uneMatricule;

    }

    //on peut remplacer par @GetMapping (qui contient de base method GET)
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

    //on peut remplacer par @PostMapping (qui contient de base method POST)
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)//201
    public Employe createEmploye(
            @RequestBody Employe employe
    ){
        try {
            return employeRepository.save(employe);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Erreur 409");
        }
    }

    //on peut remplacer par @PutMapping (qui contient de base method PUT)
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{id}"
    )
    @ResponseStatus(value = HttpStatus.ACCEPTED)//202
    public Employe updateEmploye(
            //@PathVariable (value = "id") Long id, non nécessaire
            @RequestBody Employe employe,
            @PathVariable (value = "id") Long id
    ){
        if(!employeRepository.existsById(id)){
            throw new EntityNotFoundException("Employé " + id + "non trouvé");
        }

        return employeRepository.save(employe);

    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)//204
    public void deleteEmploye(
            @PathVariable (value = "id") Long id
    ){
        if(!employeRepository.existsById(id)){
            throw new EntityNotFoundException("Employé " + id + "non trouvé");
        }
            employeRepository.deleteById(id);
    }

}
