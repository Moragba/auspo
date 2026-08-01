package com.auspo.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AltersKlasse {

    //Membervariablen
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String kennziffer;

    private String geschlecht;

    private String bezeichnung;

    private String age;

    private String kategorie;

    //Standard Konstruktor
    public AltersKlasse(){

    }

    //Getter und Setter
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getKennziffer(){
        return kennziffer;
    }
    public void setKennziffer(String kennziffer){
        this.kennziffer = kennziffer;
    }

    public String getGeschlecht(){
        return geschlecht;
    }
    public void setGeschlecht(String geschlecht){
        this.geschlecht = geschlecht;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public String getAge() {
        return age;
    }  
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getKategorie() {
        return kategorie;
    }
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

}
