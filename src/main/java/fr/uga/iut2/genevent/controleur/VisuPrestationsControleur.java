package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Prestation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue VisuPrestations.
 * Cette vue permet de visualiser les informations d'une prestation.
 * Elle permet de modifier une prestation en cliquant sur un bouton.
 */
public class VisuPrestationsControleur {

    @FXML
    private Label lbNom, lbType, lbPrixHT, lbPrixTTC;

    private Prestation prestation = PrestationsControleur.getPrestationSelectionne();

    /**
     * Initialise la vue VisuPrestations en ajoutant les informations de la prestation dans les labels.
     * Les informations de la prestation sont récupérées de la liste des prestations de la gestion.
     */
    @FXML
    public void initialize() {
        lbNom.setText(prestation.getNom());
        lbType.setText(prestation.getType());
        lbPrixHT.setText(String.valueOf(prestation.getPrixHT()));
        lbPrixTTC.setText(String.valueOf(prestation.getPrixTTC()));
    }

    /**
     * Gère l'action du bouton "Annuler".
     * Cette méthode est appelée lorsque le bouton "Annuler" est cliqué.
     * Elle charge la vue Prestations.
     *
     * @param event L'événement de clic sur le bouton "Annuler".
     */
    @FXML
    public void supprimerBoutonAction(ActionEvent event) {
        ACDeces.getGestion().removePrestation(prestation);
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Gère l'action du bouton "Modifier".
     * Cette méthode est appelée lorsque le bouton "Modifier" est cliqué.
     * Elle charge la vue PrestationModification.
     *
     * @param event L'événement de clic sur le bouton "Modifier".
     */
    @FXML
    private void modifierButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/prestation-modification-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Prestation modification");
            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
