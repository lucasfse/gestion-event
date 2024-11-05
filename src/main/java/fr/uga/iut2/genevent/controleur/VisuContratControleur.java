package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Contrat;
import fr.uga.iut2.genevent.modele.Prestation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contrôleur de la vue VisuContrat.
 * Cette vue permet de visualiser un contrat. <br>
 * Elle permet de visualiser les informations du contrat et les prestations liées au contrat.
 * Il est possible de modifier le contrat ou d'archiver le contrat.
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.

 */
public class VisuContratControleur {

    @FXML
    private Label lbNom, lbPrenom, lbAdresse, lbCodePostal, lbVille, lbNumTel, lbMontantTotal, lbMontantRegle, lbNotes, lbContratSigne, lbContratExecute, lbContratArchive;

    @FXML
    private TableView<Prestation> prestationsTableView;

    @FXML
    private TableColumn<Prestation, String> nomPrestationColumn, typeColumn, prixHTColumn, prixTTCColumn;

    private Contrat contrat = ContratsControleur.getContratSelectionne();

    private ArrayList<Prestation> prestations = new ArrayList<>();
    private ObservableList<Prestation> observablePrestations;

    /**
     * Initialise la vue VisuContrat en ajoutant les informations du contrat dans les labels et les prestations liées au contrat dans le tableau des prestations.
     */
    @FXML
    public void initialize() {
        lbNom.setText(contrat.getNomClient());
        lbPrenom.setText(contrat.getPrenomClient());
        lbAdresse.setText(contrat.getAdresseClient());
        lbCodePostal.setText(contrat.getCodePostal());
        lbVille.setText(contrat.getVilleClient());
        lbNumTel.setText(contrat.getNumeroClient());
        lbMontantTotal.setText(contrat.getMontantTotal().toString());
        lbMontantRegle.setText(contrat.getMontantRegle().toString());
        lbNotes.setText(contrat.getNotes());
        lbContratSigne.setText(contrat.getContratSigne() ? "Oui" : "Non");
        lbContratExecute.setText(contrat.getContratExecute() ? "Oui" : "Non");
        lbContratArchive.setText(contrat.getContratArchive() ? "Oui" : "Non");

        prestations = contrat.getPrestation();

        nomPrestationColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("nom"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("type"));
        prixHTColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("prixHT"));
        prixTTCColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("prixTTC"));

        observablePrestations = FXCollections.observableArrayList(prestations);
        prestationsTableView.setItems(observablePrestations);
    }

    /**
     * Permet de retourner à la vue précédente.
     * @param event
     */
    @FXML
    public void ArchiverButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/archiver-contrat-view.fxml"));
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
     * Permet de modifier le contrat.
     * @param event
     */
    @FXML
    public void modifierButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/contrat-modification-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Contrat modification");
            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
