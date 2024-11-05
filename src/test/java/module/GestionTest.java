package module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import fr.uga.iut2.genevent.modele.*;

import java.time.LocalDate;
import java.util.ArrayList;

class GestionTest {

    @Test
    void rechercheEmployeParId() {
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

        assertEquals(e3, gestion.rechercheEmployeParId("3"));
    }
}