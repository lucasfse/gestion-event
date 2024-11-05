package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Prestation;
import fr.uga.iut2.genevent.modele.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue Prestations.
 * Cette vue permet de visualiser les prestations et les véhicules.
 * Elle permet d'ajouter une prestation ou un véhicule.
 * Elle permet également de visualiser une prestation ou un véhicule en cliquant dessus.
 */
public class PrestationsControleur extends MenuControleur {
    @FXML
    private TableView<Prestation> tableViewPresta;

    @FXML
    private TableColumn<Prestation, String> nomColumn;

    @FXML
    private TableColumn<Prestation, String> typeColumn;

    @FXML
    private TableColumn<Prestation, Double> prixHTColumn;

    @FXML
    private TableColumn<Prestation, Double> prixTTCColumn;



    @FXML
    private TableView<Vehicule> tableViewVehicule;

    @FXML
    private TableColumn<Vehicule, String> plaqueImmatColumn;

    @FXML
    private TableColumn<Vehicule, Boolean> enEtatColumn;


    private ObservableList<Prestation> observablePresta;
    private ObservableList<Vehicule> observableVehicule;
    private static Vehicule vehiculeSelectionne;
    private static Prestation prestationSelectionne;



    /**
     * Initialise la vue Prestation en configurant les cellules de la table "tableViewPresta" et en affichant les Prestations.
     * Les Prestations sont affichés dans la table "tableViewPresta".
     */
    @FXML
    private void initialize() {

        // Prestations
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        prixHTColumn.setCellValueFactory(new PropertyValueFactory<>("prixHT"));
        prixTTCColumn.setCellValueFactory(new PropertyValueFactory<>("prixTTC"));

        observablePresta = FXCollections.observableArrayList(ACDeces.getGestion().getPrestations());
        tableViewPresta.setItems(observablePresta);
        tableViewPresta.refresh();

        // Vehicules
        plaqueImmatColumn.setCellValueFactory(new PropertyValueFactory<>("plaqueImmat"));
        enEtatColumn.setCellValueFactory(new PropertyValueFactory<>("enEtat"));

        observableVehicule = FXCollections.observableArrayList(ACDeces.getGestion().getVehicules());
        tableViewVehicule.setItems(observableVehicule);
        tableViewVehicule.refresh();

        // Mise à jour des listes
        updatePrestationList();
        updateVehiculeList();

    }


    /**
     * Méthode appelée lors du clic sur le bouton d'ajout de Vehicle.
     * Ouvre une nouvelle fenêtre pour ajouter un véhicule.
     * Charge le fichier FXML "vehicule-ajout-view.fxml" et affiche la scène dans une nouvelle fenêtre.
     * La nouvelle fenêtre est non redimensionnable.
     * En cas d'erreur lors du chargement du fichier FXML, une trace de la pile d'erreurs est affichée.
     *
     * @param event L'événement de clic sur le bouton.
     * @throws IOException Si une erreur se produit lors du chargement du fichier FXML.
     */
    @FXML
    private void ajoutVehiculeClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/vehicule-ajout-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Véhicule ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Méthode appelée lors du clic sur le bouton d'ajout de Prestation.
     * Ouvre une nouvelle fenêtre pour ajouter un véhicule.
     * Charge le fichier FXML "prestation-formulaire-view.fxml" et affiche la scène dans une nouvelle fenêtre.
     * La nouvelle fenêtre est non redimensionnable.
     * En cas d'erreur lors du chargement du fichier FXML, une trace de la pile d'erreurs est affichée.
     *
     * @param event L'événement de clic sur le bouton.
     * @throws IOException Si une erreur se produit lors du chargement du fichier FXML.
     */
    @FXML
    private void ajoutPrestationClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/prestation-formulaire-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Prestation ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Méthode appelée lors du clic sur une Prestation de la table "tableViewPresta".
     * Cette méthode charge la vue "prestation-visualisation-view.fxml" à l'aide du FXMLLoader,
     * crée une nouvelle fenêtre, définit la scène avec la vue chargée et affiche la fenêtre.
     * Ensuite, la méthode appelle la méthode "updatePrestationList()" pour mettre à jour la liste des prestations.
     * 
     * @see FXMLLoader
     * @see Stage
     * @see Scene
     * @see IOException
     */
    @FXML
    private void onClickPrestation() {
        TableView.TableViewSelectionModel<Prestation> selectionModel = tableViewPresta.getSelectionModel();
        ObservableList<Prestation> selectedItems = selectionModel.getSelectedItems();
        this.prestationSelectionne = selectedItems.get(0);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/prestation-visualisation-view.fxml"));
            Stage fenetre = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Prestation visualisation");
            fenetre.setScene(scene);
            fenetre.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updatePrestationList();
    }

    /**
     * Méthode appelée lors du clic sur un Vehicule de la table "tableViewVehicule".
     * Cette méthode charge la vue "vehicule-visualisation-view.fxml" à l'aide du FXMLLoader,
     * crée une nouvelle fenêtre, définit la scène avec la vue chargée et affiche la fenêtre.
     * Ensuite, la méthode appelle la méthode "updateVehiculeList()" pour mettre à jour la liste des véhicules.
     * 
     * @see FXMLLoader
     * @see Stage
     * @see Scene
     * @see IOException
     */
    @FXML
    private void onClickVehicule() {
        TableView.TableViewSelectionModel<Vehicule> selectionModel = tableViewVehicule.getSelectionModel();

        ObservableList<Vehicule> selectedItems = selectionModel.getSelectedItems();
        this.vehiculeSelectionne = selectedItems.get(0);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/vehicule-visualisation-view.fxml"));
            Stage fenetre = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Véhicule visualisation");
            fenetre.setScene(scene);
            fenetre.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateVehiculeList();
    }

    /**
     * Met à jour la liste des prestations.
     * 
     * Cette méthode récupère la liste des prestations à partir de ACDeces (la classe Application),
     * puis met à jour l'ObservableList utilisée pour afficher les prestations.
     */
    private void updatePrestationList() {
        observablePresta.clear();
        observablePresta.addAll(ACDeces.getGestion().getPrestations());
    }

    /**
     * Met à jour la liste des véhicules.
     * 
     * Cette méthode récupère la liste des véhicules à partir de ACDeces (la classe Application),
     * puis met à jour l'ObservableList utilisée pour afficher les véhicules.
     */
    private void updateVehiculeList() {
        observableVehicule.clear();
        observableVehicule.addAll(ACDeces.getGestion().getVehicules());
    }

    public static Vehicule getVehiculeSelectionne() {
        return vehiculeSelectionne;
    }

    public static Prestation getPrestationSelectionne() {
        return prestationSelectionne;
    }

}
