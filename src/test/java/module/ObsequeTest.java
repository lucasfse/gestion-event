package module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import fr.uga.iut2.genevent.modele.Contrat;
import fr.uga.iut2.genevent.modele.Employe;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Obseque;
import fr.uga.iut2.genevent.modele.Prestation;
import fr.uga.iut2.genevent.modele.Vehicule;

class ObsequeTest {

    @org.junit.jupiter.api.Test
    void updateContratExecute() {

        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e2 = new Employe("2", "employe2nom", "employe2prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e3 = new Employe("3", "employe3nom", "employe3prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e4 = new Employe("4", "employe4nom", "employe4prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());

        ArrayList<Employe> employes = new ArrayList<Employe>();
        employes.add(e);
        employes.add(e2);
        employes.add(e3);
        employes.add(e4);

        Contrat contrat = new Contrat("1", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());
        Contrat contrat2 = new Contrat("2", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());

        Vehicule vehicule = new Vehicule("1", true, new ArrayList<Obseque>());
        
        Obseque obseque = new Obseque(contrat, "1", "nom", "lieu", LocalTime.of(12,12,12), LocalTime.of(13,13,13), LocalDate.of(01,01,01), "details", e, e, employes, employes, employes, vehicule);
        Obseque obseque2 = new Obseque(contrat, "2", "nom", "lieu", LocalTime.of(12,12,12), LocalTime.of(13,13,13), LocalDate.of(011212,01,01), "details", e, e, employes, employes, employes, vehicule);


        obseque.setContrat(contrat);
        obseque2.setContrat(contrat2);

        assertEquals(true, obseque.updateContratExecute());       // car date obseque est déjà passée (LocalDate.of(01,01,01))
        assertEquals(false, obseque2.updateContratExecute());     // car date obseque2 est dans le futur (LocalDate.of(011212,01,01)

    }
}