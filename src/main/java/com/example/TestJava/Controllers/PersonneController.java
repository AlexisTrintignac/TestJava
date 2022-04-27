package com.example.TestJava.Controllers;

import com.example.TestJava.Model.Personne;
import com.example.TestJava.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonneController {

    @Autowired
    PersonneRepository personneRepository;

    /**
     * Recupérer toutes les personnes enregistrées par ordre aplphabétique, avec leur âge
     * @return la liste des personnes
     */
    @GetMapping("/getPersonnes")
    public ResponseEntity<List<Personne>> findAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        List<Personne> personnes = personneRepository.findAll();
        return new ResponseEntity(personnes, headers, HttpStatus.OK);
    }

    /**
     * Créer une nouvelle personne et renvoie la nouvelle personne
     * @param personne
     * @return la personne enregistrée
     */
    @PutMapping("/createPersonne")
    public ResponseEntity<Personne> create(@RequestBody Personne personne){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Personne personneCreated = new Personne();
        try {
            personneCreated = personneRepository.createPersonne(personne);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity(personneCreated, headers, HttpStatus.OK);
    }
}
