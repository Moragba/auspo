package com.auspo.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Disziplin {
    
    //Memebervariablen    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String kennziffer;

    private String bezeichnung;
    
    private String verband;

    @Column(columnDefinition = "TEXT")
    private String waffenart;

    private String lauflaenge;

    private String visierung;

    private String geschoss;

    private String distanz;

    private String anschlagsart;

    private String wettkampfschuesse;

    private String zeitvorgabeInMin;

    private String scheibenNr;

    private String infos;
    


    //Standardkonstruktor
    public Disziplin(){

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

    public String getVerband(){
        return verband;
    }
    public void setVerband(String verband){
        this.verband = verband;
    }

    public String getWaffenart() {
        return waffenart;
    }

    public void setWaffenart(String waffenart) {
        this.waffenart = waffenart;
    }

    public String getLauflaenge() {
        return lauflaenge;
    }

    public void setLauflaenge(String lauflaenge) {
        this.lauflaenge = lauflaenge;
    }

    public String getVisierung() {
        return visierung;
    }

    public void setVisierung(String visierung) {
        this.visierung = visierung;
    }

    public String getGeschoss() {
        return geschoss;
    }

    public void setGeschoss(String geschoss) {
        this.geschoss = geschoss;
    }

    public String getDistanz() {
        return distanz;
    }

    public void setDistanz(String distanz) {
        this.distanz = distanz;
    }

    public String getAnschlagsart() {
        return anschlagsart;
    }

    public void setAnschlagsart(String anschlagsart) {
        this.anschlagsart = anschlagsart;
    }

    public String getWettkampfschuesse() {
        return wettkampfschuesse;
    }

    public void setWettkampfschuesse(String wettkampfschuesse) {
        this.wettkampfschuesse = wettkampfschuesse;
    }

    public String getZeitvorgabeInMin() {
        return zeitvorgabeInMin;
    }

    public void setZeitvorgabeInMin(String zeitvorgabeInMin) {
        this.zeitvorgabeInMin = zeitvorgabeInMin;
    }

    public String getScheibenNr() {
        return scheibenNr;
    }

    public void setScheibenNr(String scheibenNr) {
        this.scheibenNr = scheibenNr;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
}
