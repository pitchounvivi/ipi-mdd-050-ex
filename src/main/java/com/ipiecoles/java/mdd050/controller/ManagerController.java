package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/managers")
public class ManagerController {

    @Autowired
    private TechnicienRepository technicienRepository;
    private ManagerRepository managerRepository;
    private EmployeRepository employeRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{idManager}/equipe/{idTechnicien}/remove"
    )
    public void removeTechnicienFromEquipe(
            @PathVariable Long idManager,
            @PathVariable Long idTechnicien
    ){

        //récupère technicien
        Optional <Technicien> technicienOptional = technicienRepository.findById(idTechnicien);
        if (technicienOptional.isEmpty()){
            throw new EntityNotFoundException("Impossible de trouver technicien" + idTechnicien);
        }

        //set son manager à null
        Technicien technicien = technicienOptional.get();
        if (technicien.getManager() == null){
            throw new IllegalArgumentException("Le technicien n'a pas de manager");
        }
        if (!technicien.getManager().getId().equals(idManager)){
            throw new IllegalArgumentException("Le manager " + idManager + " n'a pas de tech " +
                    "d'id " + idTechnicien + " dans son equipe");
        }
        technicien.setManager(null);

        //persiste la modification
        technicienRepository.save(technicien);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}/equipe/{matricule}/add"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addTechnicienToEquipe(
            @PathVariable Long id,
            @PathVariable String matricule
    ){
        // récupérer le technicien à partir du matricule
        Employe employe = employeRepository.findByMatricule(matricule);

        if (!(employe instanceof  Technicien)){
            throw new IllegalArgumentException("L'employé de matricule " + matricule + "n'existe pas ou n'est pas un tech");
        }

        Technicien technicien = (Technicien)employe;

        if (technicien.getManager() != null){
            throw new IllegalArgumentException("Le tech de matricule " + matricule + " a déjà un manager");
        }

        //récupérer le manager par son  id
        Optional<Manager> managerOptional = managerRepository.findById(id);

        if (managerOptional.isEmpty()){
            throw new EntityNotFoundException("Le manager " + id + " n'existe pas");
        }

        Manager manager = managerOptional.get();

        //setter le manager au tech et save
        technicien.setManager(manager);

        technicienRepository.save(technicien);

    }









}
