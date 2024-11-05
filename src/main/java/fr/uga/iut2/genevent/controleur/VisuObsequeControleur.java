package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Obseque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue VisuObseque.
 * Cette vue permet de visualiser les informations d'une obsèque.
 * Elle permet de modifier une obsèque en cliquant sur un bouton.
 */
public class VisuObsequeControleur {
    @FXML
    private Label lbNumContrat, lbNomEvenement, lbLieu, lbDate, lbHeureDebut, lbHeureFin, lbAssistantFuneraire, lbMaitreCeremonie, lbDetails;

    @FXML
    private TextArea taMarbrier, taPorteur, taThanatopracteur;

    private Obseque obseque = EvenementsControleur.getObsequeSelectionne();

    /**
     * Initialise la vue VisuObseque en ajoutant les informations de l'obsèque dans les labels et les textareas.
     * Les informations de l'obsèque sont récupérées de la liste des obsèques de la gestion.
     */
    @FXML
    public void initialize() {
        lbNumContrat.setText(obseque.getContrat().getIdContrat());
        lbNomEvenement.setText(obseque.getNom());
        lbLieu.setText(obseque.getLieu());
        lbDate.setText(obseque.getDate().toString());
        lbHeureDebut.setText(obseque.getHeureDebut().toString());
        lbHeureFin.setText(obseque.getHeureFin().toString());
        lbAssistantFuneraire.setText(obseque.getAssistantFunaire().getId() + " " + obseque.getAssistantFunaire().getNom() + " " + obseque.getAssistantFunaire().getPrenom());
        lbMaitreCeremonie.setText(obseque.getMaitreCeremonie().getId() + " " + obseque.getMaitreCeremonie().getNom() + " " + obseque.getMaitreCeremonie().getPrenom());

        taMarbrier.setText("");
        if (!obseque.getMarbriers().isEmpty()) {
            taMarbrier.setText(obseque.getMarbriers().get(0).getId() + " " + obseque.getMarbriers().get(0).getNom() + " " + obseque.getMarbriers().get(0).getPrenom());
            for (int i = 1; i < obseque.getMarbriers().size(); i++) {
                taMarbrier.setText(taMarbrier.getText() + "\n" + obseque.getMarbriers().get(i).getId() + " " + obseque.getMarbriers().get(i).getNom() + " " + obseque.getMarbriers().get(i).getPrenom());
            }
        }

        taPorteur.setText("");
        if (!obseque.getPorteurs().isEmpty()) {
            taPorteur.setText(obseque.getPorteurs().get(0).getId() + " " + obseque.getPorteurs().get(0).getNom() + " " + obseque.getPorteurs().get(0).getPrenom());
            for (int i = 1; i < obseque.getPorteurs().size(); i++) {
                taPorteur.setText(taPorteur.getText() + "\n" + obseque.getPorteurs().get(i).getId() + " " + obseque.getPorteurs().get(i).getNom() + " " + obseque.getPorteurs().get(i).getPrenom());
            }
        }

        taThanatopracteur.setText("");
        if (!obseque.getThanatopracteur().isEmpty()) {
            taThanatopracteur.setText(obseque.getThanatopracteur().get(0).getId() + " " + obseque.getThanatopracteur().get(0).getNom() + " " + obseque.getThanatopracteur().get(0).getPrenom());
            for (int i = 1; i < obseque.getThanatopracteur().size(); i++) {
                taThanatopracteur.setText(taThanatopracteur.getText() + "\n" + obseque.getThanatopracteur().get(i).getId() + " " + obseque.getThanatopracteur().get(i).getNom() + " " + obseque.getThanatopracteur().get(i).getPrenom());
            }
        }

        lbDetails.setText(obseque.getDetails());
    }

    /**
     * Gère l'action du bouton "Supprimer".
     * Cette méthode est appelée lorsque le bouton "Supprimer" est cliqué.
     * Elle supprime l'obsèque de la gestion des obsèques.
     *
     * @param event L'événement de clic sur le bouton "Supprimer".
     */
    @FXML
    public void supprimerButtonAction(ActionEvent event) {

        ACDeces.getGestion().getCalendrier().removeEvenement(obseque);

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Gère l'action du bouton "Modifier".
     * Cette méthode est appelée lorsque le bouton "Modifier" est cliqué.
     * Elle charge la vue ObsequeModification.
     *
     * @param event L'événement de clic sur le bouton "Modifier".
     */
    @FXML
    public void modifierButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/obseques-modification-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Obsèque modification");
            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
