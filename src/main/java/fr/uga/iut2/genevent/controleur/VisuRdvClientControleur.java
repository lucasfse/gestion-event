package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.RdvClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue VisuRdvClient.
 * Cette vue permet de visualiser les informations d'un rendez-vous client.
 * Elle permet de modifier un rendez-vous client en cliquant sur un bouton.
 */
public class VisuRdvClientControleur {

    @FXML
    private Label lbNomClient, lbPrenomClient, lbNumTel, lbNomEvenement, lbLieu, lbDate, lbHeureDebut, lbHeureFin, lbChargeAccueil, lbDetails;

    private RdvClient rdvClient = EvenementsControleur.getRdvSelectionne();


    /**
     * Initialise la vue VisuRdvClient en ajoutant les informations du rendez-vous client dans les labels.
     * Les informations du rendez-vous client sont récupérées de la liste des rendez-vous clients de la gestion.
     */
    @FXML
    public void initialize() {
        lbNomClient.setText(rdvClient.getNomClient());
        lbPrenomClient.setText(rdvClient.getPrenomClient());
        lbNumTel.setText(rdvClient.getNumeroTelClient());
        lbNomEvenement.setText(rdvClient.getNom());
        lbLieu.setText(rdvClient.getLieu());
        lbDate.setText(rdvClient.getDate().toString());
        lbHeureDebut.setText(rdvClient.getHeureDebut().toString());
        lbHeureFin.setText(rdvClient.getHeureFin().toString());
        lbChargeAccueil.setText(rdvClient.getChargeAcceuil().getId() + " " + rdvClient.getChargeAcceuil().getNom() + " " + rdvClient.getChargeAcceuil().getPrenom());
        lbDetails.setText(rdvClient.getDetails());
    }

    /**
     * Gère l'action du bouton "Supprimer".
     * Cette méthode est appelée lorsque le bouton "Supprimer" est cliqué.
     * Elle supprime le rendez-vous client de la liste des rendez-vous clients de la gestion.
     *
     * @param event L'événement de clic sur le bouton "Supprimer".
     */
    @FXML
    public void supprimerButtonAction(ActionEvent event) {
        ACDeces.getGestion().getCalendrier().removeEvenement(rdvClient);
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Gère l'action du bouton "Modifier".
     * Cette méthode est appelée lorsque le bouton "Modifier" est cliqué.
     * Elle charge la vue RdvClientModification.
     *
     * @param event L'événement de clic sur le bouton "Modifier".
     */
    @FXML
    public void modifierButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/rdv-modification-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Rendez-vous modification");
            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
