package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.Prestation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;


/**
 * Contrôleur de la vue FormulairePrestation.
 * Cette vue permet de créer une prestation. <br>
 * Elle permet de renseigner le nom, le type, le prix HT et le prix TTC de la prestation et d'enregistrer la prestation ou d'annuler la création de la prestation.
 */
public class FormulairePrestationControleur extends PrestationsControleur{

    @FXML
    private Button btnEnregistrer, btnAnnuler;

    @FXML
    private ComboBox<String> comboboxType;

    @FXML
    private TextField tfNom, tfPrixHT;

    @FXML
    private Label lbPrixTTC;

    /**
     * Initialise la vue FormulairePrestation en ajoutant les types de prestation dans la combobox.
     * Les types de prestation sont "Obseque" et "Rendez-Vous" il est donné à l'utilisateur de remplir lui même c'est éléments.
     * Les prix HT et TTC sont initialisés à 0.
     */
    @FXML
    private void initialize() {
        comboboxType.getItems().addAll("Obseque", "Rendez-Vous");
    }

    /**
     * Calcule et affiche le prix TTC en fonction du prix HT entré.
     * Cette méthode est appelée lorsque le prix HT est modifié.
     * Si le champ du prix HT est vide, le label du prix TTC est vidé.
     * Sinon, le prix TTC est calculé en ajoutant 20% au prix HT et est affiché dans le label du prix TTC.
     */
    @FXML
    public void addPrixTTC() {
        if (tfPrixHT.getText().isBlank() || tfPrixHT.getText() == null){
            lbPrixTTC.setText("");
        }else {
            Double prix = Double.parseDouble(tfPrixHT.getText()) * 1.20;
            lbPrixTTC.setText(String.format("%.2f", prix));
        }
    }

    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle crée une nouvelle Prestation avec les informations saisies dans le formulaire et l'ajoute à la gestion des Prestations.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et la Prestation n'est pas créée.
     */
    @FXML
    public void btnEnregistrerClicked(ActionEvent event) throws IOException {

        //GESTION D'ERREURS

        boolean erreur = false;
//Erreur prixHT
        try{
            ContraintesUtilitaire.normalizeStringNumbers(tfPrixHT.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfPrixHT,e.getMessage());
        }
//Erreur nom
        try{
            ContraintesUtilitaire.formatPrenom(tfNom.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNom,e.getMessage());
        }
//Erreur typeActivite
        try {
            ContraintesUtilitaire.comboBoxVide(comboboxType);
        }catch (Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxType, e.getMessage());
        }

        if (!erreur) {
            //Si il n'y a pas d'erreur, créer une Prestation avec toutes les informations du formulaire
            Prestation prestation = new Prestation(ContraintesUtilitaire.idCreatePrestation(8, ACDeces.getGestion().getPrestations()),
                    Double.parseDouble(ContraintesUtilitaire.normalizeStringNumbers(lbPrixTTC.getText())),
                    Double.parseDouble(ContraintesUtilitaire.normalizeStringNumbers(tfPrixHT.getText())),
                    comboboxType.getValue(), ContraintesUtilitaire.formatPrenom(tfNom.getText()));

            //Enregistrer la prestation dans la gestion dans la liste de prestation
            ACDeces.getGestion().addPrestation(prestation);

            super.btnAnnulerClicked(event);
        }
            
    }

}
