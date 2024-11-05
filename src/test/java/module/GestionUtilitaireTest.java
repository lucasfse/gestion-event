package module;


import fr.uga.iut2.genevent.modele.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class GestionUtilitaireTest {

    @org.junit.jupiter.api.Test
    void listeMetierExport() {
        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e2 = new Employe("2", "employe2nom", "employe2prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e3 = new Employe("3", "employe3nom", "employe3prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e4 = new Employe("4", "employe4nom", "employe4prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());

        ArrayList<String> a = new ArrayList<String>();
        a.add("marbrier");

        ArrayList<Employe> employes = new ArrayList<Employe>();
        employes.add(e);
        employes.add(e2);
        employes.add(e3);
        employes.add(e4);

        for(Employe employe : employes){
            employe.setFonction(a);
        }

        Contrat contrat = new Contrat("1", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Vehicule vehicule = new Vehicule("1", true, new ArrayList<Obseque>());
        
        Obseque obseque = new Obseque(contrat, "1", "nom", "lieu", LocalTime.of(12,12,12), LocalTime.of(13,13,13), LocalDate.of(01,01,01), "details", e, e, employes, employes, employes, vehicule);

        obseque.setMarbriers(employes);

    }

    @org.junit.jupiter.api.Test
    void listeMetierImport() {
        Gestion gestion = new Gestion();
        ArrayList<Employe> employes = new ArrayList<Employe>();

        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e2 = new Employe("2", "employe2nom", "employe2prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e3 = new Employe("3", "employe3nom", "employe3prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e4 = new Employe("4", "employe4nom", "employe4prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());

        employes.add(e);
        employes.add(e2);
        employes.add(e3);
        employes.add(e4);

        gestion.setEmployes(employes);

        ArrayList<Employe> resultats = new ArrayList<Employe>();
        resultats.add(e3);
        resultats.add(e4);

        assertEquals(resultats, GestionUtilitaire.listeMetierImport(gestion, "3;4"));
    }

    @Test
    void listeContratId(){
        ArrayList<Contrat> contrats = new ArrayList<Contrat>();
        Contrat c = new Contrat("1", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Contrat c2 = new Contrat("2", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Contrat c3 = new Contrat("3", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Contrat c4 = new Contrat("4", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());

        contrats.add(c);
        contrats.add(c2);
        contrats.add(c3);
        contrats.add(c4);

        ArrayList<String> resultats = new ArrayList<String>();
        resultats.add("1");
        resultats.add("2");
        resultats.add("3");
        resultats.add("4");

        assertEquals(resultats, GestionUtilitaire.listeContratId(contrats));
    }

    @Test
    void rechercheContratId(){
        ArrayList<Contrat> contrats = new ArrayList<Contrat>();
        Contrat c = new Contrat("1", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Contrat c2 = new Contrat("2", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Contrat c3 = new Contrat("3", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Contrat c4 = new Contrat("4", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());

        contrats.add(c);
        contrats.add(c2);
        contrats.add(c3);
        contrats.add(c4);

        assertEquals(c3, GestionUtilitaire.rechercheContratId("3", contrats));
        assertNull(GestionUtilitaire.rechercheContratId("12", contrats));

    }

    @Test
    void rechercheEmployeParId(){
        ArrayList<Employe> employes = new ArrayList<Employe>();
        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e2 = new Employe("2", "employe2nom", "employe2prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e3 = new Employe("3", "employe3nom", "employe3prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());

        employes.add(e);
        employes.add(e2);
        employes.add(e3);

        assertEquals(e2, GestionUtilitaire.rechercheEmployeParId("2", employes));
        assertNull(GestionUtilitaire.rechercheEmployeParId("5", employes));

    }

    @Test
    void recherchePrestationParId(){
        ArrayList<Prestation> prestations = new ArrayList<Prestation>();
        Prestation p = new Prestation(new ArrayList<Contrat>(), "1", 12.0, 12.0, "type", "nom");
        Prestation p2 = new Prestation(new ArrayList<Contrat>(), "2", 12.0, 12.0, "type", "nom");
        Prestation p3 = new Prestation(new ArrayList<Contrat>(), "3", 12.0, 12.0, "type", "nom");

        prestations.add(p);
        prestations.add(p2);
        prestations.add(p3);

        assertEquals(p2, GestionUtilitaire.recherchePrestationParId("2", prestations));
        assertNull(GestionUtilitaire.recherchePrestationParId("5", prestations));
    }

}