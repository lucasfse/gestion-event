package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Employe;
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
import java.time.LocalDate;

/**
 * Contrôleur de la vue VisuEmployes.
 * Cette vue permet de visualiser les informations d'un employé.
 * Elle permet de modifier un employé en cliquant sur un bouton.
 */
public class VisuEmployesControleur {

    @FXML
    private Label lbNom, lbPrenom, lbFonction, lbNumTel, lbDateDebut, lbDateFin, lbNbHeuresSemaine;

    @FXML
    TableView<Employe> tableviewAbsences;

    @FXML
    TableColumn<Employe, LocalDate> debutAbsenceColumn, finAbsenceColumn;

    ObservableList<Employe> observableAbsences;

    private Employe employe = EmployesControleur.getEmployeSelectionne();

    /**
     * Initialise la vue VisuEmployes en ajoutant les informations de l'employé dans les labels.
     * Les informations de l'employé sont récupérées de la liste des employés de la gestion.
     * Les absences de l'employé sont affichées dans le tableau des absences.
     * Les absences de l'employé sont récupérées de la liste des employés de la gestion.
     */
    @FXML
    public void initialize() {
        lbNom.setText(employe.getNom());
        lbPrenom.setText(employe.getPrenom());
        lbFonction.setText(employe.getFonction().toString());
        lbNumTel.setText(employe.getNumeroTel());
        lbDateDebut.setText(employe.getDateDebutContrat().toString());
        lbDateFin.setText(employe.getDateFinContrat().toString());
        lbNbHeuresSemaine.setText(employe.getNbHeuresSemaine().toString());

        debutAbsenceColumn.setCellValueFactory(new PropertyValueFactory<Employe, LocalDate>("abscenceDebut"));
        finAbsenceColumn.setCellValueFactory(new PropertyValueFactory<Employe, LocalDate>("abscenceFin"));

        observableAbsences = FXCollections.observableArrayList(employe);

        tableviewAbsences.setItems(observableAbsences);
    }

    /**
     * Gère l'action du bouton "Modifier".
     * Cette méthode est appelée lorsque le bouton "Modifier" est cliqué.
     * Elle charge la vue EmployeModification.
     *
     * @param event L'événement de clic sur le bouton "Modifier".
     */
    @FXML
    public void modifierButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/employe-modification-view.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Obtenir la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Employer modification");
            // Changer la scène de la fenêtre actuelle
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}