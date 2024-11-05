package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Prestation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de la vue ModifPrestations.
 * Cette vue permet de modifier une prestation. <br>
 * Elle permet de renseigner le nom, le type, le prix HT et le prix TTC de la prestation et d'enregistrer la prestation ou d'annuler la modification de la prestation.
 * Les informations de la prestation à modifier sont récupérées de la liste des prestations de la gestion.
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.
 */
public class ModifPrestationsControleur extends MenuControleur{

    @FXML
    private Label lbPrixTTC;

    @FXML
    private TextField tfNom, tfPrixHT;

    @FXML
    private ComboBox<String> comboboxType;

    private Prestation prestation = PrestationsControleur.getPrestationSelectionne();

    /**
     * Initialise la vue ModifPrestations en ajoutant les informations de la prestation à modifier dans les champs du formulaire.
     * Les informations de la prestation sont récupérées de la liste des prestations de la gestion.
     */
    @FXML
    private void initialize() {
        comboboxType.getItems().addAll("Obseque", "Rendez-Vous");
        comboboxType.setValue(prestation.getType());
        tfNom.setText(prestation.getNom());
        tfPrixHT.setText(String.valueOf(prestation.getPrixHT()));
        addPrixTTC();
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
     * Gère l'action du bouton "Annuler".
     * Cette méthode est appelée lorsque le bouton "Annuler" est cliqué.
     * Elle charge la vue Prestation-visualisation.
     */
    @FXML
    private void annulerButtonAction(ActionEvent event) {
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/prestation-visualisation-view.fxml"));
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
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle modifie la prestation avec les informations saisies dans le formulaire et l'ajoute à la gestion des prestations.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et la prestation n'est pas modifiée.
     *
     * @param event L'événement de clic sur le bouton "Enregistrer".
     */
    @FXML
    private void enregistrerButtonAction(ActionEvent event) {
            ACDeces.getGestion().getPrestation(prestation).setNom(tfNom.getText());
            ACDeces.getGestion().getPrestation(prestation).setPrixHT(Double.parseDouble(tfPrixHT.getText()));
            ACDeces.getGestion().getPrestation(prestation).setPrixTTC(Double.parseDouble(tfPrixHT.getText())*1.20);
            ACDeces.getGestion().getPrestation(prestation).setType(comboboxType.getValue());

            annulerButtonAction(event);

    }
}
