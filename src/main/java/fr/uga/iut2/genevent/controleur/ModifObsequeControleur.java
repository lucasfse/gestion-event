package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.Employe;
import fr.uga.iut2.genevent.modele.GestionUtilitaire;
import fr.uga.iut2.genevent.modele.Obseque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Contrôleur de la vue ModifObseque.
 * Cette vue permet de modifier un événement d'obsèques. <br>
 * Elle permet de remplir un formulaire pour modifier un événement d'obsèques et de choisir les employés liés à l'événement.
 * Il est possible de sauvegarder l'événement ou d'annuler la modification de l'événement. <br>
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.
 */
public class ModifObsequeControleur extends MenuControleur{
    @FXML
    private Button btnEnregistrer, btnAnnuler, btnAjoutMarbrier, btnAjoutPorteur, btnAjoutThanatopracteur;

    @FXML
    private ComboBox<String> comboboxNumContrat, comboboxMaitreCeremonie, comboboxAssistantFuneraire, comboboxMarbriers, comboboxPorteurs, comboboxThanatopracteurs, comboboxVehicule;

    @FXML
    private TextField tfNomEvenement, tfLieu, tfHeureDebut, tfHeureFin;

    @FXML
    private TextArea taDetails;

    @FXML
    private DatePicker dateDate;

    @FXML
    private TableView<Employe> tableviewMarbriers, tableviewPorteurs, tableviewThanatopracteurs;

    private ArrayList<Employe> marbriers = new ArrayList<>();
    private ArrayList<Employe> porteurs = new ArrayList<>();
    private ArrayList<Employe> thanatopracteurs = new ArrayList<>();

    private ObservableList<Employe> observableMarbrier;
    private ObservableList<Employe> observablePorteur;
    private ObservableList<Employe> observableThanatopracteur;

    @FXML
    private TableColumn<Employe, String> prenomMarbriersColumn, nomMarbriersColumn, prenomPorteursColumn, nomPorteursColumn, prenomThanatopracteursColumn, nomThanatopracteursColumn;

    Obseque obseque = EvenementsControleur.getObsequeSelectionne();

    /**
     * Initialise la vue ModifObseque en ajoutant les informations de l'événement d'obsèques à modifier dans les champs du formulaire.
     * Les informations de l'événement d'obsèques sont récupérées de la liste des événements d'obsèques de la gestion.
     */
    @FXML
    public void initialize() {
        // Initialisation des ComboBox
        comboboxNumContrat.getItems().addAll(GestionUtilitaire.listeContratId(ACDeces.getGestion().getContratsNonArchive()));
        comboboxMaitreCeremonie.getItems().addAll(GestionUtilitaire.afficheEmployerByFonction("Maitre de cérémonie", ACDeces.getGestion().getEmployes()));
        comboboxAssistantFuneraire.getItems().addAll(GestionUtilitaire.afficheEmployerByFonction("Assistant funéraire", ACDeces.getGestion().getEmployes()));
        comboboxMarbriers.getItems().addAll(GestionUtilitaire.afficheEmployerByFonction("Marbrier", ACDeces.getGestion().getEmployes()));
        comboboxPorteurs.getItems().addAll(GestionUtilitaire.afficheEmployerByFonction("Porteur", ACDeces.getGestion().getEmployes()));
        comboboxThanatopracteurs.getItems().addAll(GestionUtilitaire.afficheEmployerByFonction("Thanatopracteur", ACDeces.getGestion().getEmployes()));
        comboboxVehicule.getItems().addAll(GestionUtilitaire.afficheVehicule(ACDeces.getGestion().getVehicules()));

        comboboxNumContrat.setValue(obseque.getContrat().getIdContrat());
        tfNomEvenement.setText(obseque.getNom());
        tfLieu.setText(obseque.getLieu());
        dateDate.setValue(obseque.getDate());
        tfHeureDebut.setText(obseque.getHeureDebut().toString());
        tfHeureFin.setText(obseque.getHeureFin().toString());
        comboboxAssistantFuneraire.setValue(obseque.getAssistantFunaire().getId() + " " + obseque.getAssistantFunaire().getNom() + " " + obseque.getAssistantFunaire().getPrenom());
        comboboxMaitreCeremonie.setValue(obseque.getMaitreCeremonie().getId() + " " + obseque.getMaitreCeremonie().getNom() + " " + obseque.getMaitreCeremonie().getPrenom());

        comboboxVehicule.setValue(obseque.getVehicule().getPlaqueImmat());

        taDetails.setText(obseque.getDetails());

    }

    /**
     * Ajoute un marbrier à la liste des marbriers de l'événement d'obsèques.
     * Cette méthode est appelée lorsque le bouton "Ajouter" est cliqué.
     * Elle ajoute le marbrier sélectionné dans la combobox des marbriers à la liste des marbriers de l'événement d'obsèques.
     * Elle met à jour la table des marbriers pour afficher le marbrier ajouté.
     *
     * @param event L'événement de clic sur le bouton "Ajouter".
     */
    @FXML
    public void btnAjoutMarbrierClicked(ActionEvent event){
        if (comboboxMarbriers.getValue() != null) {
            marbriers.add(GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxMarbriers.getValue()), ACDeces.getGestion().getEmployes()));
        }
        nomMarbriersColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
        prenomMarbriersColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));

        observableMarbrier = FXCollections.observableArrayList(marbriers);

        tableviewMarbriers.setItems(observableMarbrier);
        tableviewMarbriers.refresh();

    }

    /**
     * Ajoute un porteur à la liste des porteurs de l'événement d'obsèques.
     * Cette méthode est appelée lorsque le bouton "Ajouter" est cliqué.
     * Elle ajoute le porteur sélectionné dans la combobox des porteurs à la liste des porteurs de l'événement d'obsèques.
     * Elle met à jour la table des porteurs pour afficher le porteur ajouté.
     *
     * @param event L'événement de clic sur le bouton "Ajouter".
     */
    @FXML
    public void btnAjoutPorteurClicked(ActionEvent event){
        if (comboboxPorteurs.getValue() != null) {
            porteurs.add(GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxPorteurs.getValue()), ACDeces.getGestion().getEmployes()));
        }

        nomPorteursColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
        prenomPorteursColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));

        observablePorteur = FXCollections.observableArrayList(porteurs);

        tableviewPorteurs.setItems(observablePorteur);
        tableviewPorteurs.refresh();

    }

    /**
     * Ajoute un thanatopracteur à la liste des thanatopracteurs de l'événement d'obsèques.
     * Cette méthode est appelée lorsque le bouton "Ajouter" est cliqué.
     * Elle ajoute le thanatopracteur sélectionné dans la combobox des thanatopracteurs à la liste des thanatopracteurs de l'événement d'obsèques.
     * Elle met à jour la table des thanatopracteurs pour afficher le thanatopracteur ajouté.
     *
     * @param event L'événement de clic sur le bouton "Ajouter".
     */
    @FXML
    public void btnAjoutThanatopracteurClicked(ActionEvent event){
        if (comboboxThanatopracteurs.getValue() != null) {
            thanatopracteurs.add(GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxThanatopracteurs.getValue()), ACDeces.getGestion().getEmployes()));
        }

        nomThanatopracteursColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
        prenomThanatopracteursColumn.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));

        observableThanatopracteur = FXCollections.observableArrayList(thanatopracteurs);

        tableviewThanatopracteurs.setItems(observableThanatopracteur);
        tableviewThanatopracteurs.refresh();


    }

    /**
     * Enregistre les modifications apportées à l'événement d'obsèques.
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle met à jour les informations de l'événement d'obsèques avec les données saisies dans le formulaire.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et l'événement d'obsèques n'est pas mis à jour.
     *
     * @param event L'événement de clic sur le bouton "Enregistrer".
     */
    @FXML
    public void btnEnregistrerClicked(ActionEvent event){

        //GESTION D'ERREURS

        boolean erreur = false;
//Erreur heureDebut
        String heureDebutStr = tfHeureDebut.getText();
        try{
            ContraintesUtilitaire.formatHeure(heureDebutStr);
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfHeureDebut,e.getMessage());

        }
//Erreur heureFin
        String heureFinStr = tfHeureFin.getText();
        try{
            ContraintesUtilitaire.formatHeure(heureFinStr);
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfHeureFin,e.getMessage());
        }
//Erreur nomEvenement
        try{
            ContraintesUtilitaire.formatPrenom(tfNomEvenement.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNomEvenement,e.getMessage());
        }
//Erreur lieu
        try{
            ContraintesUtilitaire.formatLieu(tfLieu.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfLieu,e.getMessage());
        }
//Erreur assistanFuneraire
        try {
            ContraintesUtilitaire.comboBoxVide(comboboxAssistantFuneraire);
        }catch(Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxAssistantFuneraire, e.getMessage());
        }
//Erreur maitreCeremonie
        try {
            ContraintesUtilitaire.comboBoxVide(comboboxMaitreCeremonie);
        }catch(Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxMaitreCeremonie, e.getMessage());
        }
//Erreur marbrirer
        try {
            ContraintesUtilitaire.comboBoxVide(comboboxMarbriers);
        }catch(Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxMarbriers, e.getMessage());
        }
//Erreur Porteurs
        try {
            ContraintesUtilitaire.comboBoxVide(comboboxPorteurs);
        }catch(Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxPorteurs, e.getMessage());
        }
//Erreur thanatopracteurs
        try {
            ContraintesUtilitaire.comboBoxVide(comboboxThanatopracteurs);
        }catch(Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxThanatopracteurs, e.getMessage());
        }
//Erreur vehicule
        try {
            ContraintesUtilitaire.comboBoxVide(comboboxVehicule);
        }catch(Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxVehicule, e.getMessage());
        }

        if (!erreur){

            heureDebutStr = ContraintesUtilitaire.formatHeure(heureDebutStr);
            heureFinStr = ContraintesUtilitaire.formatHeure(heureFinStr);

            LocalTime heureDebut = LocalTime.parse(heureDebutStr);
            LocalTime heureFin = LocalTime.parse(heureFinStr);
            LocalDate date = dateDate.getValue();

            ACDeces.getGestion().getCalendrier().getObseque(obseque).setContrat(GestionUtilitaire.rechercheContratId(comboboxNumContrat.getValue(), ACDeces.getGestion().getContrats()));
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setNom(tfNomEvenement.getText());
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setLieu(tfLieu.getText());
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setDate(date);
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setHeureDebut(heureDebut);
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setHeureFin(heureFin);
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setAssistantFunaire(GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxAssistantFuneraire.getValue()), ACDeces.getGestion().getEmployes()));
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setMaitreCeremonie(GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxMaitreCeremonie.getValue()), ACDeces.getGestion().getEmployes()));
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setMarbriers(marbriers);
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setPorteurs(porteurs);
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setThanatopracteur(thanatopracteurs);
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setVehicule(ACDeces.getGestion().getVehicule(comboboxVehicule.getValue()));
            ACDeces.getGestion().getCalendrier().getObseque(obseque).setDetails(taDetails.getText());

            btnAnnulerClicked(event);
        }

    }

    /**
     * Ferme la fenêtre actuelle
     * @param event evennement action
     */
    @FXML
    public void btnAnnulerClicked(ActionEvent event){

        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/obseques-visualisation-view.fxml"));
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
     * Cette méthode permet de diviser une chaîne de caractères représentant un employé en utilisant l'espace comme séparateur
     * et de retourner le premier élément de la chaîne résultante.
     *
     * @param employe la chaîne de caractères représentant un employé
     * @return le premier élément de la chaîne résultante après la division
     */
    public static String splitEmploye(String employe){
        return employe.split(" ")[0];
    }
}
