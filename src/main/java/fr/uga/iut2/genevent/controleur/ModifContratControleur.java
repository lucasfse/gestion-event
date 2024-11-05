package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.ContraintesUtilitaire;
import fr.uga.iut2.genevent.modele.Contrat;
import fr.uga.iut2.genevent.modele.GestionUtilitaire;
import fr.uga.iut2.genevent.modele.Prestation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Contrôleur de la vue Contrat-modification.
 * Cette vue permet de modifié un contrat. <br>
 * Elle permet de remplir un formulaire pour créer un contrat et de choisir les prestations lié au contrat.
 * Il est possible de sauvegarder la modification ou d'annuler la modification du contrat. <br>
 * En terme d'érgonomie et ce si pour tout les formulaires et modificateur des données. Il a été décidé de créent un contraleur pour chaque page au lieu d'un système par héritage, cela pour deux raisons. <br>
 * La première est d'évité de perdre l'utilisation de tout les formulaire et modificateur dans le cas ou un bouton hériter ce voyait défaillant. <br>
 * La deuxième étant que cette solution était la plus rapide à mettre en place et que dans la limite de temps fournit ce choix était le plus pragmatique pour pouvoir maitrisé les autres fonctionnalitées de l'application dans les temps.
 */
public class ModifContratControleur extends MenuControleur {

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

    private Contrat contrat = ContratsControleur.getContratSelectionne();

    /**
     * Initialisation du formulaire
     * Avec les information du contrat remplissant les champs du formulaire et les prestations liées au contrat, modifiable par l'utilisateur.
     */
    @FXML
    public void initialize() {
        // Initialisation des ComboBox
        comboboxPrestations.getItems().addAll(GestionUtilitaire.affichePrestation(ACDeces.getGestion().getPrestations()));

        tfNomClient.setText(contrat.getNomClient());
        tfPrenomClient.setText(contrat.getPrenomClient());
        tfAdresseClient.setText(contrat.getAdresseClient());
        tfCodePostal.setText(contrat.getCodePostal());
        tfVilleClient.setText(contrat.getVilleClient());
        tfNumTel.setText(contrat.getNumeroClient());
        lbMontantTotal.setText(contrat.getMontantTotal().toString());
        tfMontantRegle.setText(contrat.getMontantRegle().toString());
        taNotes.setText(contrat.getNotes());
        if (contrat.getContratSigne()) checkContratSigneT.setSelected(true);
        else checkContratSigneF.setSelected(true);
        if (contrat.getContratExecute()) checkContratExecuteT.setSelected(true);
        else checkContratExecuteF.setSelected(true);
        if (contrat.getContratArchive()) checkContratArchiveT.setSelected(true);
        else checkContratArchiveF.setSelected(true);
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
     * Elle met à jour les informations du contrat avec les données saisies dans le formulaire.
     * Si une erreur est détectée dans les informations saisies, un message d'erreur est affiché et le contrat n'est pas mis à jour.
     *
     * @param event L'événement de clic sur le bouton "Enregistrer".
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

        //GESTION D'ERREUR

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



        if (!erreur) {
            ACDeces.getGestion().getContrat(contrat).setNomClient(tfNomClient.getText());
            ACDeces.getGestion().getContrat(contrat).setPrenomClient(tfPrenomClient.getText());
            ACDeces.getGestion().getContrat(contrat).setAdresseClient(tfAdresseClient.getText());
            ACDeces.getGestion().getContrat(contrat).setCodePostal(tfCodePostal.getText());
            ACDeces.getGestion().getContrat(contrat).setVilleClient(tfVilleClient.getText());
            ACDeces.getGestion().getContrat(contrat).setNumeroClient(tfNumTel.getText());
            ACDeces.getGestion().getContrat(contrat).setPrestation(prestations);
            ACDeces.getGestion().getContrat(contrat).setMontantTotal(montantTotal);
            ACDeces.getGestion().getContrat(contrat).setMontantRegle(Double.parseDouble(tfMontantRegle.getText()));
            ACDeces.getGestion().getContrat(contrat).setContratSigne(contratSigner);
            ACDeces.getGestion().getContrat(contrat).setContratExecute(contratExecute);
            ACDeces.getGestion().getContrat(contrat).setContratArchive(contratArchive);
            ACDeces.getGestion().getContrat(contrat).setNotes(taNotes.getText());

            super.btnAnnulerClicked(event);

        }
    }

    /**
     * Cette méthode divise la chaîne 'prestation' donnée en fonction des espaces et renvoie la première partie.
     * Elle viens récupérer l'id de la prestation.
     *
     * @param prestation La chaîne à diviser. Type String.
     * @return La première partie de la chaîne divisée de type String.
     */
    public static String splitPrestation(String prestation){
        return prestation.split(" ")[0];
    }
}
