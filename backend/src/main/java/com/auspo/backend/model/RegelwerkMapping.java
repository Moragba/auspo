package com.auspo.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="regelwerk_mapping")
public class RegelwerkMapping{

    
    //Membervariablen
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verband_id", nullable = false)
    private Verband verband;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feld_id", nullable = false)
    private FeldDefinition feld;

    private Boolean pflicht;

    private int sortierung;

    //StandardKontruktor
    public RegelwerkMapping(){
        
    }

    //Getter und Setter
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public Verband getVerband(){
        return verband;
    }
    public void setVerband(Verband verband){
        this.verband = verband;
    }

    public FeldDefinition getFeld(){
        return feld;
    }
    public void setFeld(FeldDefinition feld){
        this.feld = feld;
    }

    public Boolean isPflicht(){
        return pflicht;
    }
    public void setPflicht(Boolean pflicht){
        this.pflicht = pflicht;
    }

    public int getSortierung(){
        return sortierung;
    }
    public void setSortierung(int sortierung){
        this.sortierung = sortierung;
    }



}