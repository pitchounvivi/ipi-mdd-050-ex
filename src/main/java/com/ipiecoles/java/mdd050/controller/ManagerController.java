package com.ipiecoles.java.mdd050.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/managers")
public class ManagerController {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{idManager}/equipe/{idTechnicien}/remove"
    )
    public void removeTechnicienFromEquipe(
            @PathVariable Long idManager,
            @PathVariable Long idTechnicien
    ){

    }
}
