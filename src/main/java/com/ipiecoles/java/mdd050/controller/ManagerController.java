package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/managers")
public class ManagerController {

    @Autowired
    private TechnicienRepository technicienRepository;
    private ManagerRepository managerRepository;

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
    public void addTechnicienToEquipe(
            @PathVariable Long id,
            @PathVariable String matricule
    ){
        // récupérer le technicien à partir du matricule

        //récupérer le manager par son  id


        //setter le manager au tech et save

        
    }









}
