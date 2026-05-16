package com.auspo.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="feld_definition")
public class FeldDefinition{

    //Membervariablen
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="label_text")
    private String labelText;

    @Column(name="input_type")
    private String inputType;

    @Column(name="tech_name")
    private String techName;

    //StandardKontructor

    public FeldDefinition(){

    }

    //Getter und Setter
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    
    public String getlabeLText(){
        return labelText;
    }
    public void setlabeLText(String labelText){
        this.labelText = labelText;
    }

    public String getInputType(){
        return inputType;
    }
    public void setInputType(String inputType){
        this.inputType = inputType;
    }
    
    public String getTechName(){
        return techName;
    }
    public void setTechName(String techName){
        this.techName = techName;
    }
}
