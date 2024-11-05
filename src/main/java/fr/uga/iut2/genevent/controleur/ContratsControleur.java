package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Contrat;
import fr.uga.iut2.genevent.modele.Obseque;
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
import java.util.List;

/**
 * Contrôleur de la vue Contrats.
 * Cette vue permet de visualiser les contrats non archivés et archivés.
 * Elle permet d'ajouter un contrat.
 * Elle permet également de visualiser un contrat en cliquant dessus.
 */
public class ContratsControleur extends MenuControleur{

    @FXML
    private Button ajoutContratButton;

    @FXML
    private TableView<Contrat> contratsTableView;

    @FXML
    private TableColumn<Contrat, String> identifiantColumn;

    @FXML
    private TableColumn<Contrat, String> nomColumn;

    @FXML
    private TableColumn<Contrat, String> prenomColumn;

    @FXML
    private TableColumn<Contrat, Boolean> signatureColumn;

    @FXML
    private TableColumn<Contrat, Boolean> reglementColumn;

    @FXML
    private TableColumn<Contrat, Boolean> executionColumn;

    @FXML
    private TableView<Contrat> contratsArchiveTableView;

    @FXML
    private TableColumn<Contrat, String> identifiantArchiveColumn;

    @FXML
    private TableColumn<Contrat, String> nomArchiveColumn;

    @FXML
    private TableColumn<Contrat, String> prenomArchiveColumn;

    @FXML
    private TableColumn<Contrat, Boolean> signatureArchiveColumn;

    @FXML
    private TableColumn<Contrat, Boolean> reglementArchiveColumn;

    @FXML
    private TableColumn<Contrat, Boolean> executionArchiveColumn;



    private ObservableList<Contrat> observableContrats;
    private ObservableList<Contrat> observableContratsArchive;

    private static Contrat contratSelectionne;


    /**
     * Initialise la vue Contrats en configurant les cellules de la table et en affichant les contrats non archivés et archivés.
     * Les contrats non archivés sont affichés dans la table contratsTableView.
     */
    @FXML
    public void initialize() {

        // Contrat non archivé
        identifiantColumn.setCellValueFactory(new PropertyValueFactory<Contrat, String>("idContrat"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Contrat, String>("nomClient"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<Contrat, String>("prenomClient"));
        signatureColumn.setCellValueFactory(new PropertyValueFactory<Contrat, Boolean>("contratSigne"));
        reglementColumn.setCellValueFactory(new PropertyValueFactory<Contrat, Boolean>("reglementTerminer"));
        executionColumn.setCellValueFactory(new PropertyValueFactory<Contrat, Boolean>("contratExecute"));

        observableContrats = FXCollections.observableArrayList(ACDeces.getGestion().getContratsNonArchive());
        contratsTableView.setItems(observableContrats);
        contratsTableView.refresh();

        // contrats archivés
        identifiantArchiveColumn.setCellValueFactory(new PropertyValueFactory<Contrat, String>("idContrat"));
        nomArchiveColumn.setCellValueFactory(new PropertyValueFactory<Contrat, String>("nomClient"));
        prenomArchiveColumn.setCellValueFactory(new PropertyValueFactory<Contrat, String>("prenomClient"));
        signatureArchiveColumn.setCellValueFactory(new PropertyValueFactory<Contrat, Boolean>("contratSigne"));
        reglementArchiveColumn.setCellValueFactory(new PropertyValueFactory<Contrat, Boolean>("reglementTerminer"));
        executionArchiveColumn.setCellValueFactory(new PropertyValueFactory<Contrat, Boolean>("contratExecute"));

        observableContratsArchive = FXCollections.observableArrayList(ACDeces.getGestion().getContratsArchive());
        contratsArchiveTableView.setItems(observableContratsArchive);
        contratsTableView.refresh();


        // Mise à jour de la liste des contrats
        updateContratNonArchiveList();
        updateContratArchiveList();
    }

    /**
     * Méthode appelée lorsqu'un bouton d'ajout de contrat est cliqué ("ajoutContratButton").
     * Cette méthode charge une nouvelle fenêtre contenant un formulaire d'ajout de contrat.
     * La fenêtre est créée à partir du fichier FXML "contrat-formulaire-view.fxml".
     * La fenêtre est affichée et ne peut pas être redimensionnée.
     *
     * @param event L'événement de clic sur le bouton "ajoutContratButton"
     * @throws IOException Si une erreur d'entrée/sortie se produit lors du chargement de la vue
     */
    @FXML
    public void ajoutContratButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/contrat-formulaire-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Contrat ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Méthode appelée lorsqu'un contrat est sélectionné dans la table des contrats.
     * Cette méthode charge une nouvelle fenêtre contenant les détails du contrat sélectionné.
     * La fenêtre est créée à partir du fichier FXML "contrat-visualisation-view.fxml".
     */
    @FXML
    public void onclickContrat()  {
        TableView.TableViewSelectionModel<Contrat> selectionModel = contratsTableView.getSelectionModel();
        ObservableList<Contrat> selectedItems = selectionModel.getSelectedItems();
        this.contratSelectionne = selectedItems.get(0);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/contrat-visualisation-view.fxml"));
            Stage fenetre = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Contrat visualisation");
            fenetre.setScene(scene);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
        updateContratNonArchiveList();
    }


    /**
     * Méthode appelée lorsqu'un contrat archivé est sélectionné dans la table des contrats archivés.
     * Cette méthode charge une nouvelle fenêtre contenant les détails du contrat archivé sélectionné.
     * La fenêtre est créée à partir du fichier FXML "contrat-visualisation-view.fxml".
     */
    @FXML
    public void onclickContratArchive()  {
        TableView.TableViewSelectionModel<Contrat> selectionModel = contratsArchiveTableView.getSelectionModel();
        ObservableList<Contrat> selectedItems = selectionModel.getSelectedItems();
        this.contratSelectionne = selectedItems.get(0);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/contrat-archive-visualisation-view.fxml"));
            Stage fenetre = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Contrat archivé visualisation");
            fenetre.setScene(scene);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
        updateContratArchiveList();
    }



    /**
     * Met à jour la liste des contrats non archivés.
     * 
     * Cette méthode récupère la liste des contrats non archivés à partir de ACDeces (la classe Application),
     * puis met à jour l'ObservableList utilisée pour afficher les contrats non archivés.
     */
    private void updateContratNonArchiveList() {
        // Récupération de la liste des employés à partir de ACDeces
        List<Contrat> contrats = ACDeces.getGestion().getContratsNonArchive();

        // Mise à jour de l'ObservableList
        observableContrats.clear();
        observableContrats.addAll(contrats);
    }

    /**
     * Met à jour la liste des contrats archivés.
     * 
     * Cette méthode récupère la liste des contrats archivés à partir de ACDeces (la classe Application),
     * puis met à jour l'ObservableList utilisée pour afficher les contrats archivés.
     */
    private void updateContratArchiveList() {
        // Récupération de la liste des employés à partir de ACDeces
        List<Contrat> contrats = ACDeces.getGestion().getContratsArchive();

        // Mise à jour de l'ObservableList
        observableContratsArchive.clear();
        observableContratsArchive.addAll(contrats);
    }

    /**
     * Retourne le contrat sélectionné.
     * @return Le contrat sélectionné.
     */
    public static Contrat getContratSelectionne() {
        return contratSelectionne;
    }
}
