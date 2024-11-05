package module;


import fr.uga.iut2.genevent.modele.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @org.junit.jupiter.api.Test
    void setDateDebutContrat() {
        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());

        assertDoesNotThrow(() -> e.setDateDebutContrat(LocalDate.of(2020,6,10)));
        assertThrows(IllegalArgumentException.class, () -> e.setDateDebutContrat(LocalDate.of(2025,4,10)));
    }

    @Test
    void setDateFinContrat() {
        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(2020,5,10), LocalDate.of(2024,6,21), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());

        assertDoesNotThrow(() -> e.setDateFinContrat(LocalDate.of(2025,6,10)));
        assertThrows(IllegalArgumentException.class, () -> e.setDateFinContrat(LocalDate.of(2020,4,10)));

    }
}