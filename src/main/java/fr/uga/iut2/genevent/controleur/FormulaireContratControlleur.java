package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Contrôleur de la vue FormulaireContrat.
 * Cette vue permet de créer un contrat. <br>
 * Elle permet de remplir un formulaire pour créer un contrat et de choisir les prestations lié au contrat.
 * Il est possible de sauvegarder le contrat ou d'annuler la création du contrat. <br>
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.
 */
public class FormulaireContratControlleur extends MenuControleur {

    @FXML
    private Button btnEnregistrer, btnAnnuler;

    @FXML
    private TextField tfPlaqueImmat, tfNomClient, tfPrenomClient, tfAdresseClient, tfCodePostal, tfVilleClient, tfNumTel, tfMontantRegle;

    @FXML
    private ComboBox<String> comboboxPrestations;

    @FXML
    private Text txtErreur;

    @FXML
    private Label lbMontantTotal;

    @FXML
    private TableView<Prestation> prestationsTableView;

    @FXML
    private TextArea taNotes;

    @FXML
    private RadioButton checkContratSigneT, checkContratSigneF, checkContratExecuteT, checkContratExecuteF, checkContratArchiveT, checkContratArchiveF;

    @FXML
    TableColumn<Prestation, String> nomPrestationColumn, typePrestationColumn, prixHTColumn, prixTTCColumn;

    private Double montantTotal = 0.0;
    private ArrayList<Prestation> prestations = new ArrayList<>();
    private ObservableList<Prestation> observablePrestations;



    /**
     * Initialisation de la fenêtre
     * Cette méthode est appelée automatiquement par JavaFX après que le fichier fxml ait été chargé.
     * Elle permet d'initialiser les combobox de la fenêtre et que les choix proposés soient cohérents avec les données de l'application.
     */
    @FXML
    public void initialize() {
        // Initialisation des ComboBox
        comboboxPrestations.getItems().addAll(GestionUtilitaire.affichePrestation(ACDeces.getGestion().getPrestations()));
    }



    /**
     * Gère l'action du bouton "Ajout Prestation".
     * Cette méthode est appelée lorsque le bouton "Ajout Prestation" est cliqué.
     * Elle ajoute la prestation sélectionnée dans la combobox à la vue de table des prestations et met à jour le montant total.
     * Si aucune prestation n'est sélectionnée dans la combobox, aucune action n'est effectuée.
     */
    @FXML
    public void btnAjoutPrestation() {

        for (int i = 0; i < prestationsTableView.getItems().size(); i++){
            montantTotal += prestationsTableView.getItems().get(i).getPrixTTC();
        }

        if (comboboxPrestations.getValue() != null) {
            prestations.add(GestionUtilitaire.recherchePrestationParId(splitPrestation(comboboxPrestations.getValue()), ACDeces.getGestion().getPrestations()));
        }
        nomPrestationColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("nom"));
        typePrestationColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("type"));
        prixHTColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("prixHT"));
        prixTTCColumn.setCellValueFactory(new PropertyValueFactory<Prestation, String>("prixTTC"));

        observablePrestations = FXCollections.observableArrayList(prestations);

        prestationsTableView.setItems(observablePrestations);
        prestationsTableView.refresh();
    }

    /**
     * Gère l'action du bouton "Enregistrer".
     * Cette méthode est appelée lorsque le bouton "Enregistrer" est cliqué.
     * Elle crée un nouveau contrat avec les informations saisies dans le formulaire et l'ajoute à la gestion des contrats.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et le contrat n'est pas créé.
     */
    @FXML
    public void btnEnregistrerClicked(ActionEvent event){


        Boolean contratSigner;
        Boolean contratArchive;
        Boolean contratExecute;

        if (checkContratSigneT.isSelected()){
            contratSigner = true;
        }else {
            contratSigner = false;
        }
        if(checkContratArchiveT.isSelected()) {
            contratArchive = true;
        }else {
            contratArchive = false;
        }
        if (checkContratExecuteT.isSelected()) {
            contratExecute = true;
        }else {
            contratExecute = false;
        }

        boolean montantRegler;
        if ( Double.parseDouble(tfMontantRegle.getText()) ==  montantTotal) montantRegler = true;
        else montantRegler = false;

        boolean erreur=false;

        //GESTION D'ERREURS

//Erreur prenoom
        try{
            ContraintesUtilitaire.formatPrenom(tfPrenomClient.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfPrenomClient,e.getMessage());
        }
//Erreur nom
        try{
            ContraintesUtilitaire.formatNom(tfNomClient.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNomClient,e.getMessage());
        }
//Erreur numTel
        try{
            ContraintesUtilitaire.normalizeStringNumbers(tfNumTel.getText(), 10);
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfNumTel,e.getMessage());
        }
//Erreur codePostal
        try{
            ContraintesUtilitaire.normalizeStringNumbers(tfCodePostal.getText(),5);
        }catch (Exception e){
            erreur=true;
            super.afficherTooltipErreur(tfCodePostal,e.getMessage());
        }
//Erreur villeClient
        try{
            ContraintesUtilitaire.formatLieu(tfVilleClient.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfVilleClient,e.getMessage());
        }
//Erreur montantRegle
        try{
            ContraintesUtilitaire.normalizeStringNumbers(tfMontantRegle.getText());
        } catch (Exception e){
            erreur = true;
            super.afficherTooltipErreur(tfMontantRegle,e.getMessage());
        }
//Erreur prestations
        try{
            ContraintesUtilitaire.comboBoxVide(comboboxPrestations);
        }catch (Exception e){
            erreur=true;
            super.afficherTooltipErreur(comboboxPrestations, e.getMessage());
        }


        if (!erreur) {
            //Si il n'y a pas d'erreur, créer un Contrat avec toutes les informations du formulaire

            Contrat contrat = new Contrat(ContraintesUtilitaire.idCreateCont(7, ACDeces.getGestion().getContrats()), tfNomClient.getText(), tfPrenomClient.getText(), tfAdresseClient.getText(),
                    tfCodePostal.getText(), tfVilleClient.getText(), tfNumTel.getText(), montantTotal, Double.parseDouble(tfMontantRegle.getText()), montantRegler, taNotes.getText(), contratSigner, contratExecute, contratArchive, prestations
            );

            //Enregistrer le contrat dans la gestion dans la liste de contrat
            ACDeces.getGestion().addContrat(contrat);

            //Fermer la fenêtre
            super.btnAnnulerClicked(event);

            
        }
        //Sinon ne rien faire
    }

    /**
     * Sépare les élément et ne garde que le premier pour garder l'ID.
     * @param prestation les information de la prestation à séparer
     * @return L'id de la préstation
     */
    public static String splitPrestation(String prestation){
        return prestation.split(" ")[0];
    }
}
