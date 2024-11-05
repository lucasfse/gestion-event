package module;


import fr.uga.iut2.genevent.modele.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


class ContraintesUtilitaireTest {

    @Test
    void unicodeToAscii() {
        assertEquals("u-ueaaeec a", ContraintesUtilitaire.unicodeToAscii("u-ùéàaèéç à"));
    }

    @Test
    void normalizeString() {
        assertEquals("ùùùù izehohéèyài àiriuç g çfho", ContraintesUtilitaire.normalizeString("???ùùùù¤¤||[[[]]]}}}{{*/*/-+?,..//:;,!§%%µµµ£££$$¤izehohéèyài&@@^^````##àiriuç&g_çfho"));
    }

    @Test
    void normalizeStringNumbers() {
        assertEquals("1234567890", ContraintesUtilitaire.normalizeStringNumbers("1234567é###¨¨890azEErtyuiopé&é&",10));
    }

    @Test
    void normalizeStringPlaque() {
        assertEquals("AB-123-CD", ContraintesUtilitaire.normalizeStringPlaque("AB-123-CD"));
        assertEquals("AB-123-CD", ContraintesUtilitaire.normalizeStringPlaque("AB123CD"));
        assertEquals("AB-123-CD", ContraintesUtilitaire.normalizeStringPlaque("ab-123-CD"));
        assertEquals("AB-123-CD", ContraintesUtilitaire.normalizeStringPlaque("ab123cd"));
        assertEquals("AB-123-CD", ContraintesUtilitaire.normalizeStringPlaque("ab123cD"));
    }

    @Test
    void formatNom() {
        assertEquals("GRÉGOIRE", ContraintesUtilitaire.formatNom("grégoire"));
        assertEquals("GRÉGOIRE", ContraintesUtilitaire.formatNom("gRÉgoire"));
        assertEquals("GRÉGOIRE", ContraintesUtilitaire.formatNom("GRÉGOIRE"));
        assertEquals("GRÉGOIRE", ContraintesUtilitaire.formatNom("grégoire&#["));
        assertEquals("GRÉGOIRE", ContraintesUtilitaire.formatNom("grégoire1234"));
        assertEquals("ÙÙÙÙ IZEHOHÉÈYÀI ÀIRIUÇ G ÇFHO", ContraintesUtilitaire.formatNom("??ùùùù¤¤||[[[]]]}}}{{*/*/-+?,..//:;,!§%µµµ££$$¤izehohéèyài&@@^^``##àiriuç&g_çfho"));
    }

    @Test
    void formatPrenom() {
        assertEquals("Grégoire", ContraintesUtilitaire.formatPrenom("grégoire"));
        assertEquals("Grégoire", ContraintesUtilitaire.formatPrenom("gRÉgoire"));
        assertEquals("Grégoire", ContraintesUtilitaire.formatPrenom("GRÉGOIRE"));
        assertEquals("Grégoire", ContraintesUtilitaire.formatPrenom("grégoire"));
        assertEquals("Grégoire", ContraintesUtilitaire.formatPrenom("grégoire1234"));
        assertEquals("Éé ùùùù izehohéèyài àiriuç g çfho", ContraintesUtilitaire.formatPrenom("éé??ùùùù¤¤¤|||[[]]]}}{{*/*/-+?,..//:;,!§%%µµ££$$¤izehohéèyài&@@^^``##àiriuç&g_çfho"));
    }

    

    @Test
    void idCreateEvent() {
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


        assertEquals("000003", ContraintesUtilitaire.idCreateEvent(6, c));

    }

    @Test
    void idCreateEmp() {
        Employe e = new Employe("1", "employe1nom", "employe1prenom", new ArrayList<String>(), "000000000000", LocalDate.of(12,12,12), LocalDate.of(13,12,12), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
        Employe e2 = new Employe("2", "employe2nom", "employe2prenom", new ArrayList<String>(), "000000000000", LocalDate.of(12,12,12), LocalDate.of(13,12,12), 12.0, new ArrayList<LocalDate>(), false, new ArrayList<Evenement>());
    
        ArrayList<Employe> employes = new ArrayList<Employe>();
        employes.add(e);
        employes.add(e2);

        assertEquals("00003", ContraintesUtilitaire.idCreateEmp(5, employes));
    }

    @Test
    void idCreateCont() {
        Contrat contrat = new Contrat("1", "nom", "type", "azerty", "12345", "azerty", "12", 100.0, 90.0, false, "no", true, false, false, new ArrayList<Prestation>());

        ArrayList<Contrat> contrats = new ArrayList<Contrat>();
        contrats.add(contrat);

        assertEquals("0000002", ContraintesUtilitaire.idCreateCont(7, contrats));
    }

    @Test
    void idCreatePrestation() {
        Prestation prestation = new Prestation(new ArrayList<Contrat>(), "1", 12.0, 12.0, "type", "nom");

        ArrayList<Prestation> prestations = new ArrayList<Prestation>();
        prestations.add(prestation);

        assertEquals("00000002", ContraintesUtilitaire.idCreatePrestation(8, prestations));
    }
}