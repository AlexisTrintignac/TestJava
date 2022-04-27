package com.example.TestJava.Repository;

import com.example.TestJava.Model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonneRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Personne> findAll(){
        Query query = new Query();
        List<Personne> personnes = mongoTemplate.find(query, Personne.class);
        for (Personne personne : personnes){
            personne.age = this.calculerAge(personne);
        }
        List<Personne> sortedPersonnes = personnes.stream()
                .sorted(Comparator.comparing(Personne::getNom))
                .collect(Collectors.toList());
        return sortedPersonnes;
    }

    public int calculerAge(Personne personne){
        LocalDate now = LocalDate.now();
        personne.age = personne.getDateNaissance().until(now).getYears();
        int age = personne.getDateNaissance().until(now).getYears();
        return age;
    }

    public Personne createPersonne(Personne personne) throws Exception{
        if(this.calculerAge(personne) >= 150){
            throw new Exception("L'age est trop élevé !");
        }
        Personne personeSaved = mongoTemplate.insert(personne);
        return personeSaved;
    }
}
