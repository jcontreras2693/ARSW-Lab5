/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.persistence.*;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    private BlueprintsServices blueprintsServices;
    @Autowired
    public BlueprintAPIController(BlueprintsServices blueprintsServices){
        this.blueprintsServices =  blueprintsServices;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoXX() {
        //obtener datos que se enviarán a través del API
        return new ResponseEntity<>(blueprintsServices.getAllBlueprints(), HttpStatus.ACCEPTED);
    }
}
