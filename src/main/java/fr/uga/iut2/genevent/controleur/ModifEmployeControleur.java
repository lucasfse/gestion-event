package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.Employe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contrôleur de la vue ModifEmploye.
 * Cette vue permet de modifier un employé. <br>
 * Elle permet de remplir un formulaire pour modifier un employé et de choisir les fonctions lié à l'employé.
 * Il est possible de sauvegarder l'employé ou d'annuler la modification de l'employé. <br>
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.
 */
public class ModifEmployeControleur extends MenuControleur {

    @FXML
    private Button btnAnnuler, btnEnregistrer, btnAjoutAbsence;

    @FXML
    private TextField tfNom, tfPrenom, tfNumTel, tfNbHeuresSemaine;

    @FXML
    private DatePicker dateDateDebut, dateDateFin, dateDebutAbsence, dateFinAbsence;

    @FXML
    private CheckBox checkMaitreCeremonie, checkAssistantFuneraire, checkMarbrier, checkPorteur, checkThanatopracteur, checkChargeAccueil, checkEmploye;


    private Employe employe = EmployesControleur.getEmployeSelectionne();

    private ArrayList<LocalDate> listeAbsences = employe.getAbscences();


    /**
     * Initialise la vue ModifEmploye en ajoutant les informations de l'employé à modifier dans les champs du formulaire.
     * Les informations de l'employé sont récupérées de la liste des employés de la gestion.
     */
    @FXML
    public void initialize() {

        tfNom.setText(employe.getNom());
        tfPrenom.setText(employe.getPrenom());

        // Cocher les checkboxes correspondant aux fonctions de l'employé par default
        if (employe.getFonction().contains("Maitre de cérémonie")) {
            checkMaitreCeremonie.setSelected(true);
        }
        if (employe.getFonction().contains("Assistant funéraire")) {
            checkAssistantFuneraire.setSelected(true);
        }
        if (employe.getFonction().contains("Marbrier")) {
            checkMarbrier.setSelected(true);
        }
        if (employe.getFonction().contains("Porteur")) {
            checkPorteur.setSelected(true);
        }
        if (employe.getFonction().contains("Thanatopracteur")) {
            checkThanatopracteur.setSelected(true);
        }
        if (employe.getFonction().contains("Chargé d'accueil")) {
            checkChargeAccueil.setSelected(true);
        }
        if (employe.getFonction().contains("Employé")) {
            checkEmploye.setSelected(true);
        }


        tfNumTel.setText(employe.getNumeroTel());
        tfNbHeuresSemaine.setText(employe.getNbHeuresSemaine().toString());
        dateDateDebut.setValue(employe.getDateDebutContrat());
        dateDateFin.setValue(employe.getDateFinContrat());
    }


    /**
     * Gère l'action du bouton "Annuler".
     * Cette méthode est appelée lorsque le bouton "Annuler" est cliqué.
     * Elle charge la vue EmployeVisualisation.
     *
     * @param event L'événement de clic sur le bouton "Annuler".
     */
    @FXML
    public void btnAnnulerClicked(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/employe-visualisation-view.fxml"));
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
     * Gère l'action du bouton "AjoutAbsence".
     * Cette méthode est appelée lorsque le bouton "AjoutAbsence" est cliqué.
     * Elle ajoute une absence à la liste des absences de l'employé.
     */
    @FXML
    public void AddAbscence(ActionEvent event) {
        LocalDate dateDebut = dateDebutAbsence.getValue();
        LocalDate dateFin = dateFinAbsence.getValue();
        if (dateDebut != null && dateFin != null) {
            listeAbsences.add(dateDebut);
            listeAbsences.add(dateFin);
        }
    }

    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle modifie un employé avec les informations saisies dans le formulaire et l'ajoute à la gestion des employés.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et l'employé n'est pas modifié.
     */
    @FXML
    public void enregistrerEmployeButtonClicked(ActionEvent event) throws IOException {


        ArrayList<String> fontions = new ArrayList<String>();
        if (checkMaitreCeremonie.isSelected()) {
            fontions.add("Maitre de cérémonie");
        }
        if (checkAssistantFuneraire.isSelected()) {
            fontions.add("Assistant funéraire");
        }
        if (checkMarbrier.isSelected()) {
            fontions.add("Marbrier");
        }
        if (checkPorteur.isSelected()) {
            fontions.add("Porteur");
        }
        if (checkThanatopracteur.isSelected()) {
            fontions.add("Thanatopracteur");
        }
        if (checkChargeAccueil.isSelected()) {
            fontions.add("Chargé d'accueil");
        }
        if (checkEmploye.isSelected()) {
            fontions.add("Employé");
        }

        //GESTION D'ERREURS

        boolean erreur = false;
//Erreur nom
        try {
            ContraintesUtilitaire.formatNom(tfNom.getText());
        } catch (Exception e) {
            erreur = true;
            super.afficherTooltipErreur(tfNom, e.getMessage());
        }
//Erreur Prenom
        try {
            ContraintesUtilitaire.formatPrenom(tfPrenom.getText());
        } catch (Exception e) {
            erreur = true;
            super.afficherTooltipErreur(tfPrenom, e.getMessage());
        }
//Erreur numTel
        try {
            ContraintesUtilitaire.normalizeStringNumbers(tfNumTel.getText(), 10);
        } catch (Exception e) {
            erreur = true;
            super.afficherTooltipErreur(tfNumTel, e.getMessage());
        }
//Erreur nbHeuresSemaine
        try {
            ContraintesUtilitaire.normalizeStringNumbers(tfNbHeuresSemaine.getText());
        } catch (Exception e) {
            erreur = true;
            super.afficherTooltipErreur(tfNbHeuresSemaine, e.getMessage());
        }

        if (!erreur) {
            employe.setNom(tfNom.getText());
            employe.setPrenom(tfPrenom.getText());
            employe.setFonction(fontions);
            employe.setNumeroTel(tfNumTel.getText());
            employe.setDateDebutContrat(dateDateDebut.getValue());
            employe.setDateFinContrat(dateDateFin.getValue());
            employe.setNbHeuresSemaine(Double.parseDouble(tfNbHeuresSemaine.getText()));
            employe.setAbscences(listeAbsences);

            btnAnnulerClicked(event);
        }
    }
}
