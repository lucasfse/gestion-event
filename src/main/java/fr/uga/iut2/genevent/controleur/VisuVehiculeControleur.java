package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Vehicule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue VisuVehicule.
 * Cette vue permet de visualiser les informations d'un véhicule.
 * Elle permet de modifier un véhicule en cliquant sur un bouton.
 */
public class VisuVehiculeControleur {

    @FXML
    private Label lbPlaqueImmat, LbPlaqueImmatModif, lbEnEtat;

    @FXML
    private RadioButton rbOui, rbNon;



    private Vehicule vehicule = PrestationsControleur.getVehiculeSelectionne();

    /**
     * Initialise la vue VisuVehicule en ajoutant les informations du véhicule dans les labels.
     * Les informations du véhicule sont récupérées de la liste des véhicules de la gestion.
     */
    @FXML
    public void initialize() {
        lbPlaqueImmat.setText(vehicule.getPlaqueImmat());
        if (vehicule.getEnEtat()) {
            lbEnEtat.setText("Oui");
        } else {
            lbEnEtat.setText("Non");
        }
    }

    /**
     * Gère l'action du bouton "Modifier".
     * Cette méthode est appelée lorsque le bouton "Modifier" est cliqué.
     * Elle charge la vue VehiculeModification.
     *
     * @param event L'événement de clic sur le bouton "Modifier".
     */
    @FXML
    private void modifierButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/vehicule-modification-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Véhicule modification");
            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
