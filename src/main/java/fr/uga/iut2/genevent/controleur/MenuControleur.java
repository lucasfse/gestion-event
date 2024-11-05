package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Contrôleur de la vue Menu.
 * Cette vue permet de naviguer entre les différentes pages de l'application.
 */
public class MenuControleur {

    @FXML
    private Button menuQuitterButton, menuSauvegarderButton, menuCalendrierButton, menuEvenementsButton, menuEmployesButton, menuContratsButton, menuPrestationsButton;

    /**
     * Méthode appelée lors du clic sur le bouton "menuSauvegarderButton".
     * Cette méthode exporte toutes les données de l'application et change la couleur du bouton en vert pendant 2 secondes.
     * Après 2 secondes, la couleur du bouton revient à sa couleur d'origine.
     *
     * @param event L'événement de clic sur le bouton.
     */
    @FXML
    public void menuSauvegarderButtonCliked(ActionEvent event) {

        ACDeces.getGestion().exportAll();
        menuSauvegarderButton.setStyle("-fx-background-color: #89ff86;" + "-fx-background-radius: 6px;");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), vert -> {
            // après 2 seconde revient à la couleur d'origine
            menuSauvegarderButton.setStyle("-fx-background-color: #ffffff;" + "-fx-background-radius: 6px;");
        }));
        timeline.play();

    }

    /**
     * Méthode appelée lors du clic sur le bouton "menuQuitterButton".
     * Cette méthode charge la vue "exit-view.fxml" et affiche une nouvelle fenêtre modale.
     * La fenêtre est non redimensionnable.
     * 
     * @param event L'événement de clic sur le bouton "menuQuitterButton".
     * @throws IOException Si une erreur de chargement de la vue se produit.
     */
    @FXML
    public void menuQuitterButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/exit-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton d'annulation se trouvant dans des fenêtres.
     * Cette méthode charge la vue "exit-view.fxml" et affiche une nouvelle fenêtre.
     * La fenêtre est non redimensionnable.
     * 
     * @param event L'événement de clic sur le bouton.
     * @throws IOException Si une erreur se produit lors du chargement de la vue.
     */
    @FXML
    public void annulerButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/exit-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Méthode appelée lors du clic sur le bouton "menuCalendrierButton".
    * Charge la vue "page-calendrier.fxml" à l'aide du FXMLLoader et appelle la méthode menuGeneral.
    * 
    * @param event L'événement de clic sur le bouton "menuCalendrierButton".
    * @throws IOException Si une erreur se produit lors du chargement de la vue.
    */
    @FXML
    public void menuCalendrierButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/page-calendrier.fxml"));
            menuGeneral(fxmlLoader, event);
            CalendrierControleur controller = fxmlLoader.getController();
            controller.miseEnFormeCalendrier();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Méthode appelée lors du clic sur le bouton "menuEvenementsButton".
     * Elle charge la page "page-evenements.fxml" et appelle la méthode menuGeneral.
     *
     * @param event l'événement de clic sur le bouton "menuEvenementsButton"
     * @throws IOException si une erreur de chargement de la page se produit
     */
    @FXML
    public void menuEvenementsButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/page-evenements.fxml"));
            menuGeneral(fxmlLoader, event);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lors du clic sur le bouton "menuEmployesButton".
     * Elle charge la page "page-employes.fxml" et appelle la méthode menuGeneral.
     *
     * @param event l'événement de clic sur le bouton "menuEmployesButton"
     * @throws IOException si une erreur de chargement de la page se produit
     */
    @FXML
    public void menuEmployesButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/page-employes.fxml"));
            menuGeneral(fxmlLoader, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Méthode appelée lors du clic sur le bouton "menuContratsButton".
     * Elle charge la page "page-contrats.fxml" et appelle la méthode menuGeneral.
     *
     * @param event l'événement de clic sur le bouton "menuContratsButton"
     * @throws IOException si une erreur de chargement de la page se produit
     */
    @FXML
    public void menuContratsButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/page-contrats.fxml"));
            menuGeneral(fxmlLoader, event);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lors du clic sur le bouton "menuPrestationsButton".
     * Elle charge la page "page-prestations.fxml" et appelle la méthode menuGeneral.
     *
     * @param event l'événement de clic sur le bouton "menuPrestationsButton"
     * @throws IOException si une erreur de chargement de la page se produit
     */
    @FXML
    public void menuPrestationsButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/page-prestations.fxml"));
            menuGeneral(fxmlLoader, event);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Charge et affiche la scène correspondant au menu général.
     * 
     * @param fxmlLoader le chargeur de fichier FXML utilisé pour charger la scène
     * @param event l'événement qui a déclenché l'appel à cette méthode
     * @throws IOException si une erreur se produit lors du chargement du fichier FXML
     */
    @FXML
    private void menuGeneral(FXMLLoader fxmlLoader, ActionEvent event) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
    }

    /**
     * Méthode appelée lorsque le bouton "Annuler" est cliqué.
     * Ferme la fenêtre actuelle.
     *
     * @param event L'événement de clic du bouton.
     */
    @FXML
    protected void btnAnnulerClicked(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Méthode appelée lorsqu'une exception à eu lieu lors du remplissage d'un formulaire.
     * Cette méthode créer un ToolTip qui va contenir le message d'erreur
     * Après 2 secondes, la couleur du bouton revient à sa couleur d'origine.
     *
     * @param textField Le TextField qui renvoie une erreur.
     * @param message Le message qui sera généralement le message de l'exception.
     */
    protected void afficherTooltipErreur(TextField textField, String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);

        // Positionner le Tooltip par rapport au TextField
        double posX = textField.getScene().getWindow().getX() + textField.localToScene(0, 0).getX() + textField.getScene().getX();
        double posY = textField.getScene().getWindow().getY() + textField.localToScene(0, 0).getY() + textField.getScene().getY() + textField.getHeight();

        tooltip.show(textField, posX, posY);
    }

    /**
     * Méthode appelée lorsqu'une exception à eu lieu lors du remplissage d'un formulaire.
     * Cette méthode créer un ToolTip qui va contenir le message d'erreur
     * Après 2 secondes, la couleur du bouton revient à sa couleur d'origine.
     *
     * @param comboBox Le ComboBox qui renvoie une erreur.
     * @param message Le message qui sera généralement le message de l'exception.
     */
    protected void afficherTooltipErreur(ComboBox<String> comboBox, String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);

        // Positionner le Tooltip par rapport au ComboBox
        double posX = comboBox.getScene().getWindow().getX() + comboBox.localToScene(0, 0).getX() + comboBox.getScene().getX();
        double posY = comboBox.getScene().getWindow().getY() + comboBox.localToScene(0, 0).getY() + comboBox.getScene().getY() + comboBox.getHeight();

        tooltip.show(comboBox, posX, posY);
    }

}
