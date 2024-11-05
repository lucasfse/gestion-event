package module;

import fr.uga.iut2.genevent.modele.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalendrierTest {

    @Test
     void getEvenement() {
        Calendrier c = new Calendrier();

        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(12,12,12), LocalDate.of(13,12,12), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());

        RdvClient rdv1 = new RdvClient("1", "rdv1", "lieu1", LocalTime.of(12, 12), LocalTime.of(12, 12),
                LocalDate.of(12,12,12), "client1nom", "client1prenom", "000000000000",
                e, "000000000000");
        RdvClient rdv2 = new RdvClient("2", "rdv2", "lieu2", LocalTime.of(12, 12), LocalTime.of(12, 12),
                LocalDate.of(12,12,12), "client1nom", "client1prenom", "000000000000",
                e, "000000000000");
        c.addEvenement(rdv1);
        c.addEvenement(rdv2);

        assertEquals(rdv2, c.getEvenement("2"));
    }

}