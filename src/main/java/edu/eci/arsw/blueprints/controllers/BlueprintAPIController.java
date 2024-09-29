package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    private final BlueprintsServices blueprintsServices;

    @Autowired
    public BlueprintAPIController(BlueprintsServices blueprintsServices) {
        this.blueprintsServices = blueprintsServices;
    }

    // Maneja el GET para obtener los planos de un autor específico
    @RequestMapping(value = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerPlanosPorAutor(@PathVariable("author") String autor) {
        try {
            Set<?> bp = blueprintsServices.getBlueprintsByAuthor(autor);
            if (bp.isEmpty()) {
                return new ResponseEntity<>("AUTHOR NOT FOUND", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bp, HttpStatus.OK);
        } catch (BlueprintNotFoundException e) {
            return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Maneja el GET para obtener los planos de un autor por nombre de autor y plano
    @RequestMapping(value = "/{author}/{blueprint}", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerPlanosPorAutorPlano(@PathVariable("author") String autor, @PathVariable("blueprint") String blueprint) {
        try {
            Blueprint bp = blueprintsServices.getBlueprint(autor, blueprint);
            if (bp.equals(null)) {
                return new ResponseEntity<>("BLUEPRINT NOT FOUND", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bp, HttpStatus.OK);
        } catch (BlueprintNotFoundException e) {
            return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Maneja el POST para crear un nuevo plano
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoAñadirBlueprint(@RequestBody Blueprint bp) {
        try {
            blueprintsServices.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED, HttpStatus.OK);
        } catch (BlueprintPersistenceException e) {
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
        }
    }

    //Maneja el PUT para actualizar un nuevo plano
    @RequestMapping(value = "/{author}/{blueprint}", method = RequestMethod.PUT)
    public ResponseEntity<?> manejadorPostRecursoXX(@PathVariable("author") String autor, @PathVariable("blueprint") String blueprint, @RequestBody List<Point> points) {
        try {
            blueprintsServices.updateBlueprint(autor, blueprint, points);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BlueprintNotFoundException e) {
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
        }
    }
}
