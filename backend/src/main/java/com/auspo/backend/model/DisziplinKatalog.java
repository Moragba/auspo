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
@Table(name="disziplin_katalog")
public class DisziplinKatalog {
    
    //Memebervariablen    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String kennziffer;

    private String bezeichnung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verband_id", nullable = false)
    private Verband verband;


    //Standardkonstruktor
    public DisziplinKatalog(){

    }

    //Getter und Setter
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getKennziffer(){
        return kennziffer;
    }
    public void setKennziffer(String kennziffer){
        this.kennziffer = kennziffer;
    }

    public String getBezeichnung(){
        return bezeichnung;
    }
    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

    public Verband getVerband(){
        return verband;
    }
    public void setVerband(Verband verband){
        this.verband = verband;
    }
}
