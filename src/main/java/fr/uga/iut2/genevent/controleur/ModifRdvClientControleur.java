package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.GestionUtilitaire;
import fr.uga.iut2.genevent.modele.RdvClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;

/**
 * Contrôleur de la vue ModifRdvClient.
 * Cette vue permet de modifier un rendez-vous client. <br>
 * Elle permet de renseigner le nom, le prénom et le numéro de téléphone du client, le nom de l'événement, le lieu, la date, l'heure de début et de fin, le détail et le chargé d'accueil du rendez-vous et d'enregistrer le rendez-vous ou d'annuler la modification du rendez-vous.
 */
public class ModifRdvClientControleur extends MenuControleur{

    @FXML
    private Button btnEnregistrer, btnAnnuler;

    @FXML
    private TextField tfNomClient ,tfPrenomClient, tfNumTel, tfNomEvenement, tfLieu, tfHeureDebut, tfHeureFin;

    @FXML
    private TextArea taDetails;

    @FXML
    private DatePicker dateDate;

    @FXML
    private ComboBox<String> comboboxChargeAccueil;

    RdvClient rdvClient = EvenementsControleur.getRdvSelectionne();


    /**
     * Initialise la vue ModifRdvClient en ajoutant les employés de fonction "Chargé d'accueil" dans la combobox.
     * Les employés de fonction "Chargé d'accueil" sont récupérés de la liste des employés de la gestion.
     */
    @FXML
    private void initialize() {
        comboboxChargeAccueil.getItems().addAll(GestionUtilitaire.afficheEmployerByFonction("Chargé d'accueil", ACDeces.getGestion().getEmployes()));
        tfNomClient.setText(rdvClient.getNomClient());
        tfPrenomClient.setText(rdvClient.getPrenomClient());
        tfNumTel.setText(rdvClient.getNumeroTelClient());
        tfNomEvenement.setText(rdvClient.getNom());
        tfLieu.setText(rdvClient.getLieu());
        dateDate.setValue(rdvClient.getDate());
        tfHeureDebut.setText(rdvClient.getHeureDebut().toString());
        tfHeureFin.setText(rdvClient.getHeureFin().toString());
        comboboxChargeAccueil.setValue(GestionUtilitaire.afficheEmployerByFonction("Chargé d'accueil", ACDeces.getGestion().getEmployes()).get(0));
        taDetails.setText(rdvClient.getDetails());
    }

    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle modifie le rendez-vous client avec les informations saisies dans le formulaire et l'ajoute à la gestion des rendez-vous.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et le rendez-vous n'est pas modifié.
     *
     * @param event L'événement de clic sur le bouton "Enregistrer".
     * @throws IOException Si une erreur survient lors de la modification du rendez-vous.
     */
    @FXML
    public void btnEnregistrerClicked(ActionEvent event) throws IOException {

        //GESTION D'ERREURS

        boolean erreur = false;
//Erreur nomClient
        try{
            ContraintesUtilitaire.formatNom(tfNomClient.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNomClient,e.getMessage());
        }
//Erreur prenomClient
        try{
            ContraintesUtilitaire.formatPrenom(tfPrenomClient.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfPrenomClient,e.getMessage());
        }
//Erreur numTel
        try{
            ContraintesUtilitaire.normalizeStringNumbers(tfNumTel.getText(), 10);
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNumTel,e.getMessage());
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
//Erreur heureDebut
        try{
            ContraintesUtilitaire.formatHeure(tfHeureDebut.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfHeureDebut,e.getMessage());
        }
//Erreur heureFin
        try{
            ContraintesUtilitaire.formatHeure(tfHeureFin.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfHeureFin,e.getMessage());
        }
//Erreur chargeAccueil
        try{
            ContraintesUtilitaire.comboBoxVide(comboboxChargeAccueil);
        }catch (Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxChargeAccueil, e.getMessage());
        }

        if (!erreur) {

            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setNomClient(tfNomClient.getText());
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setPrenomClient(tfPrenomClient.getText());
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setNumeroTelClient(tfNumTel.getText());
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setNom(tfNomEvenement.getText());
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setLieu(tfLieu.getText());
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setDate(dateDate.getValue());
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setHeureDebut(LocalTime.parse(tfHeureDebut.getText()));
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setHeureFin(LocalTime.parse(tfHeureFin.getText()));
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setChargeAcceuil(GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxChargeAccueil.getValue()), ACDeces.getGestion().getEmployes()));
            ACDeces.getGestion().getCalendrier().getRdvClient(rdvClient).setDetails(taDetails.getText());

            btnAnnulerClicked(event);
        }
    }

    /**
     * Méthode appelée lors du clic sur le bouton "btnAnnuler".
     * Ferme la fenêtre actuelle.
     *
     * @param event L'événement de clic sur le bouton "btnAnnuler".
     */
    @FXML
    public void btnAnnulerClicked(ActionEvent event){
        try {
            // Charger la nouvelle vue
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/rdv-visualisation-view.fxml"));
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
     * @param employe la chaîne de caractères représentant l'employé à diviser
     * @return le premier élément de la chaîne résultante après la division
     */
    public static String splitEmploye(String employe){
        return employe.split(" ")[0];
    }
}
