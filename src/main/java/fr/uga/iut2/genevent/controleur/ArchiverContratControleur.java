package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Contrat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue d'archivage d'un contrat. <br>
 * Cette vue permet d'archiver un contrat. <br>
 * Un contrat archivé n'est plus modifiable. <br>
 */
public class ArchiverContratControleur extends MenuControleur {

    private Contrat contrat = ContratsControleur.getContratSelectionne();

    @FXML
    public void archiverAnnulerButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/contrat-visualisation-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Archivage du contrat");
            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void archiverButtonAction(ActionEvent event) throws IOException {
        ACDeces.getGestion().getContrat(contrat).setContratArchive(true);
        btnAnnulerClicked(event);
    }

}
