package com.example.TestJava.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "Personne")
public class Personne {

    @Id
    public String id;

    @Field(value = "nom")
    private String nom;

    @Field(value = "prenom")
    private String prenom;

    @Field(value = "dateNaissance")
    private LocalDate dateNaissance;

    public int age;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

}
