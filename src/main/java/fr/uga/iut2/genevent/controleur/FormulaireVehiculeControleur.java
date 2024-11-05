package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * Contrôleur de la vue FormulaireVehicule.
 * Cette vue permet de créer un véhicule. <br>
 * Elle permet de renseigner la plaque d'immatriculation et l'état du véhicule et d'enregistrer le véhicule ou d'annuler la création du véhicule.
 */
public class FormulaireVehiculeControleur extends MenuControleur{

    @FXML private Button btnEnregistrer, btnAnnuler;

    @FXML private TextField tfPlaqueImmat;

    @FXML private Text txtErreur;

    @FXML private RadioButton radioButtonOui, radioButtonNon;


    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle crée un nouveau véhicule avec les informations saisies dans le formulaire et l'ajoute à la gestion des véhicules.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et le véhicule n'est pas créé.
     *
     * @param event L'événement de clic sur le bouton "Enregistrer".
     */
    @FXML
    public void enregistrerVoitureButtonClicked(ActionEvent event) {
        boolean etat = false;

        if (radioButtonNon.isSelected()){
            etat = false;
        }
        if (radioButtonOui.isSelected()) {
            etat = true;
        }

        boolean erreur = false;
//Erreur plaqueImmat
        try{
            ContraintesUtilitaire.normalizeStringPlaque(tfPlaqueImmat.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfPlaqueImmat,e.getMessage());
        }

        if (!erreur) {
            //Si il n'y a pas d'erreur, ajouter un nouveau Vehicule ayant comme attribut les informations donner dans le formulaire dans la liste d'employe de vehicule
            ACDeces.getGestion().addVehicule(new Vehicule(ContraintesUtilitaire.normalizeStringPlaque(tfPlaqueImmat.getText()), etat));

            //Fermer la page
            super.btnAnnulerClicked(event);
        }
        //Sinon fermer la page
    }

}
