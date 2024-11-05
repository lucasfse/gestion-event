package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * Contrôleur de la vue FormulaireObseque.
 * Cette vue permet de créer un évènement de type Obseque. <br>
 * Elle permet de remplir un formulaire pour créer un évènement de type Obseque.
 * Il est possible de sauvegarder l'évènement ou d'annuler la création de l'évènement. <br>
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.
 */
public class FormulaireObsequeControleur extends MenuControleur{

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


    /**
     * Initialise la vue FormulaireObseque en ajoutant les numéros de contrat, les employés et les véhicules dans les combobox.
     * Les numéros de contrat sont récupérés à partir de la liste des contrats non archivés.
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
    }

    /**
     * Gère l'action du bouton "Ajouter".
     * Cette méthode est appelée lorsque le bouton "Ajouter" est cliqué.
     * Elle ajoute un marbrier à la liste des marbriers de l'obseque.
     * @param event l'évènement associé au clic sur le bouton "Ajouter"
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
     * Gère l'action du bouton "Ajouter".
     * Cette méthode est appelée lorsque le bouton "Ajouter" est cliqué.
     * Elle ajoute un porteur à la liste des porteurs de l'obseque.
     * @param event l'évènement associé au clic sur le bouton "Ajouter"
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
     * Gère l'action du bouton "Ajouter".
     * Cette méthode est appelée lorsque le bouton "Ajouter" est cliqué.
     * Elle ajoute un thanatopracteur à la liste des thanatopracteurs de l'obseque.
     * @param event l'évènement associé au clic sur le bouton "Ajouter"
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
     * Gère l'action du bouton "Annuler".
     * Cette méthode est appelée lorsque le bouton "Annuler" est cliqué.
     * Elle ferme la page de formulaire.
     * @param event l'évènement associé au clic sur le bouton "Annuler"
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
            //Si il n'y a pas d'erreur, créer un Obseque avec toutes les informations du formulaire

            heureDebutStr = ContraintesUtilitaire.formatHeure(heureDebutStr);
            heureFinStr = ContraintesUtilitaire.formatHeure(heureFinStr);

            LocalTime heureDebut = LocalTime.parse(heureDebutStr);
            LocalTime heureFin = LocalTime.parse(heureFinStr);
            LocalDate date = dateDate.getValue();
            
            Obseque obseque = new Obseque(
                GestionUtilitaire.rechercheContratId(comboboxNumContrat.getValue(), ACDeces.getGestion().getContrats()),
                ContraintesUtilitaire.idCreateEvent(6, ACDeces.getGestion().getCalendrier()),
                ContraintesUtilitaire.formatPrenom(tfNomEvenement.getText()),
                ContraintesUtilitaire.formatLieu(tfLieu.getText()),
                heureDebut,
                heureFin,
                date,
                taDetails.getText(),
                GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxAssistantFuneraire.getValue()), ACDeces.getGestion().getEmployes()),
                GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxMaitreCeremonie.getValue()), ACDeces.getGestion().getEmployes()),
                marbriers,
                porteurs,
                thanatopracteurs,
                ACDeces.getGestion().getVehicule(comboboxVehicule.getValue())
            );

            //Enregistrer l'obseque dans la gestion dans la liste d'évènement
            ACDeces.getGestion().addEvenement(obseque);

            //Ferme la page de formulaire
            super.btnAnnulerClicked(event);

        }
        //Sinon ne rien faire
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
