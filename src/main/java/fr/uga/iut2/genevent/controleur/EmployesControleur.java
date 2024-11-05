package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur de la vue Employes.
 * Cette vue permet de gérer les employés de l'application.
 * Elle permet d'afficher la liste des employés, d'ajouter un employé et de visualiser les détails d'un employé.
 */
public class EmployesControleur extends MenuControleur{

    @FXML
    private TableView<Employe> employesTableView;

    @FXML
    private TableColumn<Employe, String> identifiantColumn;

    @FXML
    private TableColumn<Employe, String> nomColumn;

    @FXML
    private TableColumn<Employe, String> prenomColumn;

    @FXML
    private TableColumn<Employe, ArrayList<String>> fonctionsColumn;

    @FXML
    private TableColumn<Employe, String> telephoneColumn;

    @FXML
    private TableColumn<Employe, String> heuresSemainesColumn;

    @FXML
    private Button ajoutEmployeButton;

    private ObservableList<Employe> observableEmployes;
    private static Employe employeSelectionne;



    /**
     * Initialise la vue Employes en configurant les cellules de la table et en affichant les employés.
     * Les contrats non archivés sont affichés dans la table contratsTableView.
     */
    @FXML
    public void initialize() {
        identifiantColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));
        fonctionsColumn.setCellValueFactory(new PropertyValueFactory<Employe, ArrayList<String>>("fonction"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("numeroTel"));
        heuresSemainesColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("nbHeuresSemaine"));


        observableEmployes = FXCollections.observableArrayList(ACDeces.getGestion().getEmployes());

        employesTableView.setItems(observableEmployes);
        employesTableView.refresh();

        updateEmployeList();
    }

    /**
     * Méthode appelée lorsqu'un utilisateur clique sur un Employe de la table "employesTableView".
     * Cette méthode charge une nouvelle fenêtre qui affiche la visualisation des employés.
     * La fenêtre est affichée et non redimensionnable.
     * Après l'affichage de la fenêtre, la méthode updateEmployeList() est appelée pour mettre à jour la liste des employés.
     */
    @FXML
    public void onclickEmployer()  {


        TableView.TableViewSelectionModel<Employe> selectionModel = employesTableView.getSelectionModel();
        ObservableList<Employe> selectedItems = selectionModel.getSelectedItems();
        this.employeSelectionne = selectedItems.get(0);


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/employe-visualisation-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Employer visualisation");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
        updateEmployeList();

        // il faudra ressortir les informations pour la view

    }

    /**
     * Méthode appelée lorsqu'un utilisateur clique sur le bouton "ajoutEmployeButton".
     * Cette méthode charge la vue "employe-formulaire-view.fxml" et affiche une nouvelle fenêtre contenant cette vue.
     * La fenêtre est affichée et rendue non redimensionnable.
     * 
     * @param event L'événement de clic sur le bouton "ajoutEmployeButton"
     * @throws IOException Si une erreur se produit lors du chargement de la vue
     */
    @FXML
    public void ajoutEmployeButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/employe-formulaire-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Employer ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour la liste des employés.
     * 
     * Cette méthode récupère la liste des employés à partir de ACDeces et met à jour l'ObservableList utilisée pour afficher les employés.
     */
    private void updateEmployeList() {
        // Récupération de la liste des employés à partir de ACDeces
        List<Employe> employes = ACDeces.getGestion().getEmployes();

        // Mise à jour de l'ObservableList
        observableEmployes.clear();
        observableEmployes.addAll(employes);
    }

    public static Employe getEmployeSelectionne() {
        return employeSelectionne;
    }

}
