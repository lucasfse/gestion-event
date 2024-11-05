package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue ModifVehicule.
 * Cette vue permet de modifier un véhicule. <br>
 * Elle permet de renseigner la plaque d'immatriculation et l'état du véhicule et d'enregistrer le véhicule ou d'annuler la modification du véhicule.
 * Les informations du véhicule à modifier sont récupérées de la liste des véhicules de la gestion.
 */
public class ModifVehiculeControleur extends MenuControleur{

    @FXML
    private Label lbPlaqueImmat;

    @FXML
    private RadioButton rbOui, rbNon;

    private Vehicule vehicule = PrestationsControleur.getVehiculeSelectionne();

    /**
     * Initialise la vue ModifVehicule en ajoutant les informations du véhicule à modifier dans les champs du formulaire.
     * Les informations du véhicule sont récupérées de la liste des véhicules de la gestion.
     */
    @FXML
    private void initialize() {
        lbPlaqueImmat.setText(vehicule.getPlaqueImmat());
        if (vehicule.getEnEtat()) {
            rbOui.setSelected(true);
        } else {
            rbNon.setSelected(true);
        }
    }

    /**
     * Gère l'action du bouton "Annuler".
     * Cette méthode est appelée lorsque le bouton "Annuler" est cliqué.
     * Elle charge la vue VehiculeVisualisation.
     *
     * @param event L'événement de clic sur le bouton "Annuler".
     */
    @FXML
    private void annulerButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/vehicule-visualisation-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle modifie le véhicule avec les informations saisies dans le formulaire et l'ajoute à la gestion des véhicules.
     *
     * @param event L'événement de clic sur le bouton "Enregistrer".
     */
    @FXML
    private void enregistrerButtonAction(ActionEvent event) {

        ACDeces.getGestion().getVehicule(lbPlaqueImmat.getText());


        if (rbOui.isSelected()) {
            ACDeces.getGestion().getVehicule(lbPlaqueImmat.getText()).setEnEtat(true);
        } else {
            ACDeces.getGestion().getVehicule(lbPlaqueImmat.getText()).setEnEtat(false);
        }
        annulerButtonAction(event);
    }
}
