package com.auspo.backend.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.auspo.backend.model.AltersKlasse;
import com.auspo.backend.repo.AltersKlasseRepo;

@Component
public class AltersKlassenInit implements CommandLineRunner {
    private final AltersKlasseRepo altersKlassenRepo;
    
    public AltersKlassenInit(AltersKlasseRepo altersKlasseRepo) {
        this.altersKlassenRepo = altersKlasseRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Dateninitialisierung 'AltersKlassen-Table' gestartet...");

        //Objekte Hardcode erzeugt wird  während des Refactoring durch eine ,json ersetzt
        AltersKlasse obj1 = new AltersKlasse();
        obj1.setKennziffer("22");
        obj1.setGeschlecht("männlich");
        obj1.setBezeichnung("Schüler III");
        obj1.setAge("bis 10 Jahre");
        obj1.setKategorie("Kugel");
        altersKlassenRepo.save(obj1);

        AltersKlasse obj2 = new AltersKlasse();
        obj2.setKennziffer("23");
        obj2.setGeschlecht("weiblich");
        obj2.setBezeichnung("Schüler III");
        obj2.setAge("bis 10 Jahre");
        obj2.setKategorie("Kugel");
        altersKlassenRepo.save(obj2);

        AltersKlasse obj3 = new AltersKlasse();
        obj3.setKennziffer("20");
        obj3.setGeschlecht("männlich");
        obj3.setBezeichnung("Schüler II");
        obj3.setAge("11 bis 12 Jahre");
        obj3.setKategorie("Kugel");
        altersKlassenRepo.save(obj3);

        AltersKlasse obj4 = new AltersKlasse();
        obj4.setKennziffer("21");
        obj4.setGeschlecht("weiblich");
        obj4.setBezeichnung("Schüler II");
        obj4.setAge("11 bis 12 Jahre");
        obj4.setKategorie("Kugel");
        altersKlassenRepo.save(obj4);

        AltersKlasse obj5 = new AltersKlasse();
        obj5.setKennziffer("20");
        obj5.setGeschlecht("männlich");
        obj5.setBezeichnung("Schüler I");
        obj5.setAge("13 bis 14 Jahre");
        obj5.setKategorie("Kugel");
        altersKlassenRepo.save(obj5);

        AltersKlasse obj6 = new AltersKlasse();
        obj6.setKennziffer("21");
        obj6.setGeschlecht("weiblich");
        obj6.setBezeichnung("Schüler I");
        obj6.setAge("13 bis 14 Jahre");
        obj6.setKategorie("Kugel");
        altersKlassenRepo.save(obj6);

        AltersKlasse obj7 = new AltersKlasse();
        obj7.setKennziffer("30");
        obj7.setGeschlecht("männlich");
        obj7.setBezeichnung("Jugend");
        obj7.setAge("15 bis 16 Jahre");
        obj7.setKategorie("Kugel");
        altersKlassenRepo.save(obj7);

        AltersKlasse obj8 = new AltersKlasse();
        obj8.setKennziffer("31");
        obj8.setGeschlecht("weiblich");
        obj8.setBezeichnung("Jugend");
        obj8.setAge("15 bis 16 Jahre");
        obj8.setKategorie("Kugel");
        altersKlassenRepo.save(obj8);

        AltersKlasse obj9 = new AltersKlasse();
        obj9.setKennziffer("42");
        obj9.setGeschlecht("männlich");
        obj9.setBezeichnung("Junioren II");
        obj9.setAge("17 bis 18 Jahre");
        obj9.setKategorie("Kugel");
        altersKlassenRepo.save(obj9);

        AltersKlasse obj10 = new AltersKlasse();
        obj10.setKennziffer("43");
        obj10.setGeschlecht("weiblich");
        obj10.setBezeichnung("Junioren II");
        obj10.setAge("17 bis 18 Jahre");
        obj10.setKategorie("Kugel");
        altersKlassenRepo.save(obj10);

        AltersKlasse obj11 = new AltersKlasse();
        obj11.setKennziffer("40");
        obj11.setGeschlecht("männlich");
        obj11.setBezeichnung("Junioren I");
        obj11.setAge("19 bis 20 Jahre");
        obj11.setKategorie("Kugel");
        altersKlassenRepo.save(obj11);

        AltersKlasse obj12 = new AltersKlasse();
        obj12.setKennziffer("41");
        obj12.setGeschlecht("weiblich");
        obj12.setBezeichnung("Junioren I");
        obj12.setAge("19 bis 20 Jahre");
        obj12.setKategorie("Kugel");
        altersKlassenRepo.save(obj12);

        AltersKlasse obj13 = new AltersKlasse();
        obj13.setKennziffer("10");
        obj13.setGeschlecht("männlich");
        obj13.setBezeichnung("Herren I");
        obj13.setAge("21 bis 40 Jahre");
        obj13.setKategorie("Kugel");
        altersKlassenRepo.save(obj13);

        AltersKlasse obj14 = new AltersKlasse();
        obj14.setKennziffer("11");
        obj14.setGeschlecht("weiblich");
        obj14.setBezeichnung("Damen I");
        obj14.setAge("21 bis 40 Jahre");
        obj14.setKategorie("Kugel");
        altersKlassenRepo.save(obj14);

        AltersKlasse obj15 = new AltersKlasse();
        obj15.setKennziffer("12");
        obj15.setGeschlecht("männlich");
        obj15.setBezeichnung("Herren II");
        obj15.setAge("41 bis 50 Jahre");
        obj15.setKategorie("Kugel");
        altersKlassenRepo.save(obj15);

        AltersKlasse obj16 = new AltersKlasse();
        obj16.setKennziffer("13");
        obj16.setGeschlecht("weiblich");
        obj16.setBezeichnung("Damen II");
        obj16.setAge("41 bis 50 Jahre");
        obj16.setKategorie("Kugel");
        altersKlassenRepo.save(obj16);

        AltersKlasse obj17 = new AltersKlasse();
        obj17.setKennziffer("14");
        obj17.setGeschlecht("männlich");
        obj17.setBezeichnung("Herren III");
        obj17.setAge("51 bis 60 Jahre");
        obj17.setKategorie("Kugel");
        altersKlassenRepo.save(obj17);

        AltersKlasse obj18 = new AltersKlasse();
        obj18.setKennziffer("15");
        obj18.setGeschlecht("weiblich");
        obj18.setBezeichnung("Damen III");
        obj18.setAge("51 bis 60 Jahre");
        obj18.setKategorie("Kugel");
        altersKlassenRepo.save(obj18);

        AltersKlasse obj19 = new AltersKlasse();
        obj19.setKennziffer("16");
        obj19.setGeschlecht("männlich");
        obj19.setBezeichnung("Herren IV");
        obj19.setAge("61 bis 70 Jahre");
        obj19.setKategorie("Kugel");
        altersKlassenRepo.save(obj19);

        AltersKlasse obj20 = new AltersKlasse();
        obj20.setKennziffer("17");
        obj20.setGeschlecht("weiblich");
        obj20.setBezeichnung("Damen IV");
        obj20.setAge("61 bis 70 Jahre");
        obj20.setKategorie("Kugel");
        altersKlassenRepo.save(obj20);

        AltersKlasse obj21 = new AltersKlasse();
        obj21.setKennziffer("18");
        obj21.setGeschlecht("männlich");
        obj21.setBezeichnung("Herren V");
        obj21.setAge("ab 71 Jahre");
        obj21.setKategorie("Kugel");
        altersKlassenRepo.save(obj21);

        AltersKlasse obj22 = new AltersKlasse();
        obj22.setKennziffer("19");
        obj22.setGeschlecht("weiblich");
        obj22.setBezeichnung("Damen V");
        obj22.setAge("ab 71 Jahre");
        obj22.setKategorie("Kugel");
        altersKlassenRepo.save(obj22);

        AltersKlasse obj23 = new AltersKlasse();
        obj23.setKennziffer("70");
        obj23.setGeschlecht("männlich");
        obj23.setBezeichnung("Senioren I");
        obj23.setAge("51 bis 60 Jahre");
        obj23.setKategorie("Kugel");
        altersKlassenRepo.save(obj23);

        AltersKlasse obj24 = new AltersKlasse();
        obj24.setKennziffer("71");
        obj24.setGeschlecht("weiblich");
        obj24.setBezeichnung("Senioren I");
        obj24.setAge("51 bis 60 Jahre");
        obj24.setKategorie("Kugel");
        altersKlassenRepo.save(obj24);

        AltersKlasse obj25 = new AltersKlasse();
        obj25.setKennziffer("72");
        obj25.setGeschlecht("männlich");
        obj25.setBezeichnung("Senioren II");
        obj25.setAge("61 bis 65 Jahre");
        obj25.setKategorie("Kugel");
        altersKlassenRepo.save(obj25);

        AltersKlasse obj26 = new AltersKlasse();
        obj26.setKennziffer("73");
        obj26.setGeschlecht("weiblich");
        obj26.setBezeichnung("Senioren II");
        obj26.setAge("61 bis 65 Jahre");
        obj26.setKategorie("Kugel");
        altersKlassenRepo.save(obj26);

        AltersKlasse obj27 = new AltersKlasse();
        obj27.setKennziffer("74");
        obj27.setGeschlecht("männlich");
        obj27.setBezeichnung("Senioren III");
        obj27.setAge("66 bis 70 Jahre");
        obj27.setKategorie("Kugel");
        altersKlassenRepo.save(obj27);

        AltersKlasse obj28 = new AltersKlasse();
        obj28.setKennziffer("75");
        obj28.setGeschlecht("weiblich");
        obj28.setBezeichnung("Senioren III");
        obj28.setAge("66 bis 70 Jahre");
        obj28.setKategorie("Kugel");
        altersKlassenRepo.save(obj28);

        AltersKlasse obj29 = new AltersKlasse();
        obj29.setKennziffer("76");
        obj29.setGeschlecht("männlich");
        obj29.setBezeichnung("Senioren IV");
        obj29.setAge("71 bis 75 Jahre");
        obj29.setKategorie("Kugel");
        altersKlassenRepo.save(obj29);

        AltersKlasse obj30 = new AltersKlasse();
        obj30.setKennziffer("77");
        obj30.setGeschlecht("weiblich");
        obj30.setBezeichnung("Senioren IV");
        obj30.setAge("71 bis 75 Jahre");
        obj30.setKategorie("Kugel");
        altersKlassenRepo.save(obj30);

        AltersKlasse obj31 = new AltersKlasse();
        obj31.setKennziffer("78");
        obj31.setGeschlecht("männlich");
        obj31.setBezeichnung("Senioren V");
        obj31.setAge("76 bis 80 Jahre");
        obj31.setKategorie("Kugel");
        altersKlassenRepo.save(obj31);

        AltersKlasse obj32 = new AltersKlasse();
        obj32.setKennziffer("79");
        obj32.setGeschlecht("weiblich");
        obj32.setBezeichnung("Senioren V");
        obj32.setAge("76 bis 80 Jahre");
        obj32.setKategorie("Kugel");
        altersKlassenRepo.save(obj32);

        AltersKlasse obj33 = new AltersKlasse();
        obj33.setKennziffer("80");
        obj33.setGeschlecht("männlich");
        obj33.setBezeichnung("Senioren VI");
        obj33.setAge("ab 81 Jahre");
        obj33.setKategorie("Kugel");
        altersKlassenRepo.save(obj33);

        AltersKlasse obj34 = new AltersKlasse();
        obj34.setKennziffer("81");
        obj34.setGeschlecht("weiblich");
        obj34.setBezeichnung("Senioren VI");
        obj34.setAge("ab 81 Jahre");
        obj34.setKategorie("Kugel");
        altersKlassenRepo.save(obj34);

        AltersKlasse obj35 = new AltersKlasse();
        obj35.setKennziffer("22");
        obj35.setGeschlecht("männlich");
        obj35.setBezeichnung("Schüler C");
        obj35.setAge("bis 10 Jahre");
        obj35.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj35);

        AltersKlasse obj36 = new AltersKlasse();
        obj36.setKennziffer("23");
        obj36.setGeschlecht("weiblich");
        obj36.setBezeichnung("Schüler C");
        obj36.setAge("bis 10 Jahre");
        obj36.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj36);

        AltersKlasse obj37 = new AltersKlasse();
        obj37.setKennziffer("20");
        obj37.setGeschlecht("männlich");
        obj37.setBezeichnung("Schüler B");
        obj37.setAge("11 bis 12 Jahre");
        obj37.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj37);

        AltersKlasse obj38 = new AltersKlasse();
        obj38.setKennziffer("21");
        obj38.setGeschlecht("weiblich");
        obj38.setBezeichnung("Schüler B");
        obj38.setAge("11 bis 12 Jahre");
        obj38.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj38);

        AltersKlasse obj39 = new AltersKlasse();
        obj39.setKennziffer("20");
        obj39.setGeschlecht("männlich");
        obj39.setBezeichnung("Schüler A");
        obj39.setAge("13 bis 14 Jahre");
        obj39.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj39);

        AltersKlasse obj40 = new AltersKlasse();
        obj40.setKennziffer("21");
        obj40.setGeschlecht("weiblich");
        obj40.setBezeichnung("Schüler A");
        obj40.setAge("13 bis 14 Jahre");
        obj40.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj40);

        AltersKlasse obj41 = new AltersKlasse();
        obj41.setKennziffer("30");
        obj41.setGeschlecht("männlich");
        obj41.setBezeichnung("Jugend");
        obj41.setAge("15 bis 16 Jahre");
        obj41.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj41);

        AltersKlasse obj42 = new AltersKlasse();
        obj42.setKennziffer("31");
        obj42.setGeschlecht("weiblich");
        obj42.setBezeichnung("Jugend");
        obj42.setAge("15 bis 16 Jahre");
        obj42.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj42);

        AltersKlasse obj43 = new AltersKlasse();
        obj43.setKennziffer("40");
        obj43.setGeschlecht("männlich");
        obj43.setBezeichnung("Junioren");
        obj43.setAge("17 bis 20 Jahre");
        obj43.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj43);

        AltersKlasse obj44 = new AltersKlasse();
        obj44.setKennziffer("41");
        obj44.setGeschlecht("weiblich");
        obj44.setBezeichnung("Junioren");
        obj44.setAge("17 bis 20 Jahre");
        obj44.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj44);

        AltersKlasse obj45 = new AltersKlasse();
        obj45.setKennziffer("10");
        obj45.setGeschlecht("männlich");
        obj45.setBezeichnung("Herren I");
        obj45.setAge("21 bis 39 Jahre");
        obj45.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj45);

        AltersKlasse obj46 = new AltersKlasse();
        obj46.setKennziffer("11");
        obj46.setGeschlecht("weiblich");
        obj46.setBezeichnung("Damen I");
        obj46.setAge("21 bis 39 Jahre");
        obj46.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj46);

        AltersKlasse obj47 = new AltersKlasse();
        obj47.setKennziffer("12");
        obj47.setGeschlecht("männlich");
        obj47.setBezeichnung("Herren II");
        obj47.setAge("40 bis 49 Jahre");
        obj47.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj47);

        AltersKlasse obj48 = new AltersKlasse();
        obj48.setKennziffer("13");
        obj48.setGeschlecht("weiblich");
        obj48.setBezeichnung("Damen II");
        obj48.setAge("40 bis 49 Jahre");
        obj48.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj48);

        AltersKlasse obj49 = new AltersKlasse();
        obj49.setKennziffer("14");
        obj49.setGeschlecht("männlich");
        obj49.setBezeichnung("Master");
        obj49.setAge("50 bis 65 Jahre");
        obj49.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj49);

        AltersKlasse obj50 = new AltersKlasse();
        obj50.setKennziffer("15");
        obj50.setGeschlecht("weiblich");
        obj50.setBezeichnung("Master");
        obj50.setAge("50 bis 65 Jahre");
        obj50.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj50);

        AltersKlasse obj51 = new AltersKlasse();
        obj51.setKennziffer("16");
        obj51.setGeschlecht("männlich");
        obj51.setBezeichnung("Senioren I");
        obj51.setAge("66 bis 70 Jahre");
        obj51.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj51);

        AltersKlasse obj52 = new AltersKlasse();
        obj52.setKennziffer("17");
        obj52.setGeschlecht("weiblich");
        obj52.setBezeichnung("Senioren I");
        obj52.setAge("66 bis 70 Jahre");
        obj52.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj52);

        AltersKlasse obj53 = new AltersKlasse();
        obj53.setKennziffer("18");
        obj53.setGeschlecht("männlich");
        obj53.setBezeichnung("Senioren II");
        obj53.setAge("ab 71 Jahre");
        obj53.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj53);

        AltersKlasse obj54 = new AltersKlasse();
        obj54.setKennziffer("19");
        obj54.setGeschlecht("weiblich");
        obj54.setBezeichnung("Senioren II");
        obj54.setAge("ab 71 Jahre");
        obj54.setKategorie("Bogen Halle");
        altersKlassenRepo.save(obj54);

        AltersKlasse obj55 = new AltersKlasse();
        obj55.setKennziffer("22");
        obj55.setGeschlecht("männlich");
        obj55.setBezeichnung("Schüler C");
        obj55.setAge("bis 10 Jahre");
        obj55.setKategorie("Bogen Draußen");
        altersKlassenRepo.save(obj55);

        AltersKlasse obj56 = new AltersKlasse();
        obj56.setKennziffer("23");
        obj56.setGeschlecht("weiblich");
        obj56.setBezeichnung("Schüler C");
        obj56.setAge("bis 10 Jahre");
        obj56.setKategorie("Bogen Draußen");
        altersKlassenRepo.save(obj56);


        System.out.println("Dateninitialisierung 'AltersKlassen-Table' abgeschlossen.");
        
    }
    
}
