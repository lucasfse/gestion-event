package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contrôleur de la vue FormulaireEmploye.
 * Cette vue permet de créer un employé. <br>
 * Elle permet de remplir un formulaire pour créer un employé et de choisir les fonctions lié à l'employé.
 * Il est possible de sauvegarder l'employé ou d'annuler la création de l'employé. <br>
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.
 */
public class FormulaireEmployeControleur extends MenuControleur{

    // si indice impaire alors début de contrat si indice paire alors fin de contrat
    private ArrayList<LocalDate> listeAbsences = new ArrayList<LocalDate>();

    @FXML
    private Button btnAnnuler, btnEnregistrer, btnAjoutAbsence;

    @FXML
    private TextField tfNom, tfPrenom, tfNumTel, tfNbHeuresSemaine;

    @FXML
    private DatePicker dateDateDebut, dateDateFin, dateDebutAbsence, dateFinAbsence;

    @FXML
    private CheckBox checkMaitreCeremonie, checkAssistantFuneraire, checkMarbrier, checkPorteur, checkThanatopracteur, checkChargeAccueil, checkEmploye;

    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle crée un nouvel employé avec les informations saisies dans le formulaire et l'ajoute à la gestion des employés.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et l'employé n'est pas créé.
     */
    @FXML
        public void enregistrerEmployeButtonClicked(ActionEvent event) throws IOException {
        ArrayList<String> fontions = new ArrayList<String>();
        if(checkMaitreCeremonie.isSelected()){
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
        if ( checkThanatopracteur.isSelected() ) {
            fontions.add("Thanatopracteur");
        }
        if ( checkChargeAccueil.isSelected() ) {
            fontions.add("Chargé d'accueil");
        }
        if ( checkEmploye.isSelected() ) {
            fontions.add("Employé");
        }

        LocalDate dateDebut = dateDateDebut.getValue();
        LocalDate dateFin = dateDateFin.getValue();

        //GESTION D'ERREURS

        boolean erreur = false;
//Erreur nom
        try{
            ContraintesUtilitaire.formatNom(tfNom.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNom,e.getMessage());
        }
//Erreur Prenom
        try{
            ContraintesUtilitaire.formatPrenom(tfPrenom.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfPrenom,e.getMessage());
        }
//Erreur numTel
        try{
            ContraintesUtilitaire.normalizeStringNumbers(tfNumTel.getText(), 10);
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNumTel,e.getMessage());
        }
//Erreur nbHeuresSemaine
        try{
            ContraintesUtilitaire.normalizeStringNumbers(tfNbHeuresSemaine.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNbHeuresSemaine,e.getMessage());
        }

        if (!erreur) {
            //Si il n'y a pas d'erreur, ajouter un nouveau Employe ayant comme attribut les informations donner dans le formulaire dans la liste d'employe de gestion

            ACDeces.getGestion().addEmploye(new Employe(ContraintesUtilitaire.idCreateEmp(5 ,ACDeces.getGestion().getEmployes()),
            ContraintesUtilitaire.formatNom(tfNom.getText()), ContraintesUtilitaire.formatPrenom(tfPrenom.getText()), fontions,
            ContraintesUtilitaire.normalizeStringNumbers(tfNumTel.getText(), 10), dateDebut, dateFin,
            Double.parseDouble(ContraintesUtilitaire.normalizeStringNumbers(tfNbHeuresSemaine.getText())),
            listeAbsences, false));

            //Fermer la page
            super.btnAnnulerClicked(event);
        }
        //Sinon ne rien faire
    }

    /**
     * Gère l'action du bouton "Ajouter Absence".
     * Cette méthode est appelée lorsque le bouton "Ajouter Absence" est cliqué.
     * Elle ajoute une nouvelle période d'absence pour l'employé avec les dates de début et de fin spécifiées dans le formulaire.
     * Si les dates de début ou de fin ne sont pas spécifiées, aucune action n'est effectuée.
     */
    @FXML
    public void AddAbscence(ActionEvent event){
        LocalDate dateDebut = dateDebutAbsence.getValue();
        LocalDate dateFin = dateFinAbsence.getValue();
        if(dateDebut != null && dateFin != null){
            listeAbsences.add(dateDebut);
            listeAbsences.add(dateFin);
        }
    }

}
