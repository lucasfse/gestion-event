package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.GestionUtilitaire;
import fr.uga.iut2.genevent.modele.RdvClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Contrôleur de la vue FormulaireRDV.
 * Cette vue permet de créer un rendez-vous. <br>
 * Elle permet de renseigner le nom, le prénom et le numéro de téléphone du client, le nom de l'événement, le lieu, la date, l'heure de début et de fin, le détail et le chargé d'accueil du rendez-vous et d'enregistrer le rendez-vous ou d'annuler la création du rendez-vous.
 */
public class FormulaireRDVControleur extends MenuControleur{

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

    /**
     * Initialise la vue FormulaireRDV en ajoutant les employés de fonction "Chargé d'accueil" dans la combobox.
     * Les employés de fonction "Chargé d'accueil" sont récupérés de la liste des employés de la gestion.
     */
    @FXML
    private void initialize() {
        comboboxChargeAccueil.getItems().addAll(GestionUtilitaire.afficheEmployerByFonction("Chargé d'accueil", ACDeces.getGestion().getEmployes()));
    }

    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle crée un nouveau rendez-vous avec les informations saisies dans le formulaire et l'ajoute à la gestion des rendez-vous.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et le rendez-vous n'est pas créé.
     *
     * @param event L'événement de clic sur le bouton "Enregistrer".
     * @throws IOException Si une erreur survient lors de la création du rendez-vous.
     */
    @FXML
    public void btnEnregistrerClicked(ActionEvent event) throws IOException {


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
            //Si il n'y a pas d'erreur, créer un RDVClient avec toutes les informations du formulaire

            String nomClient = ContraintesUtilitaire.formatNom(tfNomClient.getText());
            String prenomClient = ContraintesUtilitaire.formatPrenom(tfPrenomClient.getText());
            String numTel = ContraintesUtilitaire.normalizeStringNumbers(tfNumTel.getText(), 10);
            String nomRDV = ContraintesUtilitaire.formatPrenom(tfNomEvenement.getText());
            String lieuRDV = ContraintesUtilitaire.formatLieu(tfLieu.getText());
            String heureDebutStr = ContraintesUtilitaire.formatHeure(tfHeureDebut.getText());
            String heureFinStr = ContraintesUtilitaire.formatHeure(tfHeureFin.getText());
            String detailsRDV = taDetails.getText();
            LocalDate dateRDV = dateDate.getValue();
    
            LocalTime heureDebut = LocalTime.parse(heureDebutStr);
            LocalTime heureFin = LocalTime.parse(heureFinStr);

            RdvClient rdvClient = new RdvClient(ContraintesUtilitaire.idCreateEvent(6, ACDeces.getGestion().getCalendrier()), nomRDV, lieuRDV, heureDebut, heureFin, dateRDV, nomClient, prenomClient, numTel,
                                            GestionUtilitaire.rechercheEmployeParId(splitEmploye(comboboxChargeAccueil.getValue()), ACDeces.getGestion().getEmployes()), detailsRDV);

            //Enregistrer le RDVClient dans la gestion dans la liste d'evenement
            ACDeces.getGestion().getCalendrier().addEvenement(rdvClient);

        
            //Fermer la page
            super.btnAnnulerClicked(event);
        }
        //Sinon ne rien faire
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
