package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Obseque;
import fr.uga.iut2.genevent.modele.RdvClient;
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
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Contrôleur de la vue Evenements.
 * Cette vue permet de gérer les événements de l'application.
 * Elle permet d'afficher la liste des événements, d'ajouter un événement et de visualiser les détails d'un événement.
 */
public class EvenementsControleur extends MenuControleur{

    @FXML
    private Button ajoutRdvButton, ajoutObsequesButton;


    @FXML
    private TableView<RdvClient> rdvTableView;

    @FXML
    private TableColumn<RdvClient, LocalDate> dateRdvColumn;

    @FXML
    private TableColumn<RdvClient, LocalTime> debutRdvColumn, finRdvColumn;

    @FXML
    private TableColumn<RdvClient, String> nomRdvColumn;

    @FXML
    private TableView<Obseque> obsequesTableView;

    @FXML
    private TableColumn<Obseque, LocalDate> dateObsequesColumn;

    @FXML
    private TableColumn<Obseque, LocalTime> debutObsequesColumn, finObsequesColumn;

    @FXML
    private TableColumn<Obseque, String> nomObsequesColumn;



    private ObservableList<RdvClient> observableRdvClient;
    private ObservableList<Obseque> observableObseques;

    private static RdvClient rdvSelectionne;
    private static Obseque obsequesSelectionne;


    /**
     * Initialise la vue Evenements en configurant les cellules de la table et en affichant les Evenements.
     * Les Evenements sont affichés dans les tables rdvTableView pour les RdvClients et obsequesTableView pour les Obseques.
     */
    @FXML
    public void initialize() {
        // RdvClient
        dateRdvColumn.setCellValueFactory(new PropertyValueFactory<RdvClient, LocalDate>("date"));
        debutRdvColumn.setCellValueFactory(new PropertyValueFactory<RdvClient, LocalTime>("heureDebut"));
        finRdvColumn.setCellValueFactory(new PropertyValueFactory<RdvClient, LocalTime>("heureFin"));
        nomRdvColumn.setCellValueFactory(new PropertyValueFactory<RdvClient, String>("nom"));

        observableRdvClient = FXCollections.observableArrayList(ACDeces.getGestion().getCalendrier().getRdvClient());
        rdvTableView.setItems(observableRdvClient);
        rdvTableView.refresh();

        // Obseques
        dateObsequesColumn.setCellValueFactory(new PropertyValueFactory<Obseque, LocalDate>("date"));
        debutObsequesColumn.setCellValueFactory(new PropertyValueFactory<Obseque, LocalTime>("heureDebut"));
        finObsequesColumn.setCellValueFactory(new PropertyValueFactory<Obseque, LocalTime>("heureFin"));
        nomObsequesColumn.setCellValueFactory(new PropertyValueFactory<Obseque, String>("nom"));

        observableObseques = FXCollections.observableArrayList(ACDeces.getGestion().getCalendrier().getObseques());
        obsequesTableView.setItems(observableObseques);
        obsequesTableView.refresh();

        // Mise à jour de la liste d'évènements
        updateRdvClientList();
        updateObsequesList();

    }


    /**
     * Méthode appelée lorsqu'un utilisateur clique sur le bouton "ajoutRdvButton".
     * Cette méthode charge la vue "rdv-formulaire-view.fxml" et affiche une nouvelle fenêtre contenant cette vue.
     * La fenêtre est rendue non redimensionnable.
     * 
     * @param event L'événement de clic sur le bouton "ajoutRdvButton"
     * @throws IOException Si une erreur se produit lors du chargement de la vue
     */
    @FXML
    public void ajoutRdvButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/rdv-formulaire-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Rendez-vous ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "ajoutObsequesButton".
     * Cette méthode charge la vue "obseques-formulaire-view.fxml" et l'affiche dans une nouvelle fenêtre.
     * La fenêtre est rendue non redimensionnable.
     * 
     * @param event L'événement de clic sur le bouton "ajoutObsequesButton"
     * @throws IOException Si une erreur se produit lors du chargement de la vue.
     */
    @FXML
    public void ajoutObsequesButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/obseques-formulaire-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Obseque ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lorsqu'un utilisateur clique sur un Obseques (Evenement) de la table "obsequesTableView".
     * Cette méthode charge une nouvelle fenêtre qui affiche la visualisation de l'Obseque.
     * La fenêtre est affichée et non redimensionnable.
     * Après l'affichage de la fenêtre, la méthode updateObsequesList() est appelée pour mettre à jour la liste des Obseques.
     */
    @FXML
    public void onclickObseque()  {
        TableView.TableViewSelectionModel<Obseque> selectionModel = obsequesTableView.getSelectionModel();
        ObservableList<Obseque> selectedItems = selectionModel.getSelectedItems();
        this.obsequesSelectionne = selectedItems.get(0);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/obseques-visualisation-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Obseque visualisation");
            fenetre.setScene(scene);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
        updateObsequesList();
    }

    /**
     * Méthode appelée lorsqu'un utilisateur clique sur un Obseques (Evenement) de la table "rdvTableView".
     * Cette méthode charge une nouvelle fenêtre qui affiche la visualisation du RdvClient.
     * La fenêtre est affichée et non redimensionnable.
     * Après l'affichage de la fenêtre, la méthode updateRdvClientList() est appelée pour mettre à jour la liste des RdvClients.
     */
    @FXML
    public void onclickRdvClient()  {

        TableView.TableViewSelectionModel<RdvClient> selectionModel = rdvTableView.getSelectionModel();
        ObservableList<RdvClient> selectedItems = selectionModel.getSelectedItems();
        this.rdvSelectionne = selectedItems.get(0);


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/rdv-visualisation-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Rendez-vous visualisation");
            fenetre.setScene(scene);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
        updateRdvClientList();
    }


    /**
     * Met à jour la liste des rendez-vous clients.
     * Utilisé dans le cas de l'actualisation de la page.
     */
    public void updateRdvClientList() {
        observableRdvClient.clear();
        observableRdvClient.addAll(ACDeces.getGestion().getCalendrier().getRdvClient());
    }

    /**
     * Met à jour la liste des obsèques.
     * Utilisé dans le cas de l'actualisation de la page.
     */
    public void updateObsequesList() {
        observableObseques.clear();
        observableObseques.addAll(ACDeces.getGestion().getCalendrier().getObseques());
    }

    public static RdvClient getRdvSelectionne() {
        return rdvSelectionne;
    }

    public static Obseque getObsequeSelectionne() { return obsequesSelectionne; }

}
