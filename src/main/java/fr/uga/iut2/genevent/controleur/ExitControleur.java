package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * Contrôleur de la vue Exit.
 * Cette vue permet de quitter l'application.
 * Elle permet d'exporter les données de l'application et de fermer l'application.
 */
public class ExitControleur extends MenuControleur {

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnQuitter;


    /**
     * Méthode appelée lors du clic sur le bouton "btnQuitter".
     * Exporte toutes les données de l'application et ferme l'application.
     *
     * @param event L'événement de clic sur le bouton "btnQuitter".
     */
    @FXML
    private void btnQuitterClicked(ActionEvent event) {
        ACDeces.getGestion().exportAll();
        Platform.exit();
    }

}
