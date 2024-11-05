package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Contrat qui permet de créer un contrat. <br>
 * <ul>
 *     <li>idContrat</li>
 *     <li>nomClient</li>
 *     <li>prenomClient</li>
 *     <li>adresseClient</li>
 *     <li>codePostal</li>
 *     <li>villeClient</li>
 *     <li>numeroClient</li>
 *     <li>montantTotal</li>
 *     <li>montantRegle</li>
 *     <li>reglementTerminer</li>
 *     <li>notes</li>
 *     <li>contratSigne</li>
 *     <li>contratExecute</li>
 *     <li>contratArchive</li>
 *     <li>prestation</li>
 * </ul>
 * ATTENTION : Les attributs nomClient, prenomClient, codePostal, numeroClient sont obligatoires. Mais adresseClient, villeClient, montantTotal, montantRegle, reglementTerminer, notes, contratSigne, contratExecute, contratArchive peuvent être null. <br>
 * Les id sont générés automatiquement depuis la liste des contrats déjà stocker.
 */
public class Contrat {

//    Attribut

    private final String idContrat;
    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String codePostal;
    private String villeClient;
    private String numeroClient;
    private Double montantTotal;
    private Double montantRegle;
    private Boolean reglementTerminer;
    private String notes;
    private Boolean contratSigne;
    private Boolean contratExecute;
    private Boolean contratArchive;
    private ArrayList<Prestation> prestation = new ArrayList<>();

    // Logger

    private static Logger LOGGER = Logger.getLogger(Contrat.class.getName());


//    Constructeur

    /**
     * Constructeur de la classe Contrat avec les informations d'un contrat.
     *
     * @param idContrat l'identifiant du contrat. Type String
     * @param nomClient le nom du client. Type String
     * @param prenomClient le prénom du client. Type String
     * @param adresseClient l'adresse du client. Type String
     * @param codePostal le code postal du client. Type String
     * @param villeClient la ville du client. Type String
     * @param numeroClient le numéro de téléphone du client. Type String
     * @param montantTotal le montant total du contrat. Type Double
     * @param montantRegle le montant réglé pour le contrat. Type Double
     * @param reglementTerminer l'état du règlement du contrat. Type Boolean
     * @param notes les notes associées au contrat. Type String
     * @param contratSigne l'état du contrat (signé ou non). Type Boolean
     * @param contratExecute l'état d'exécution du contrat. Type Boolean
     * @param contratArchive l'état d'archivage du contrat. Type Boolean
     * ATTENTION : Les attributs nomClient, prenomClient, codePostal, numeroClient sont obligatoires. Mais adresseClient, villeClient, montantTotal, montantRegle, reglementTerminer, notes, contratSigne, contratExecute, contratArchive peuvent être null.
     */
    public Contrat(String idContrat, String nomClient, String prenomClient, String adresseClient, String codePostal, String villeClient,
                   String numeroClient, Double montantTotal, Double montantRegle, Boolean reglementTerminer, String notes,
                   Boolean contratSigne, Boolean contratExecute, Boolean contratArchive, ArrayList<Prestation> prestation) {
        this.idContrat = idContrat;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.adresseClient = adresseClient;
        this.codePostal = codePostal;
        this.villeClient = villeClient;
        this.numeroClient = numeroClient;
        this.montantTotal = montantTotal;
        this.montantRegle = montantRegle;
        this.reglementTerminer = reglementTerminer;
        this.notes = notes;
        this.contratSigne = contratSigne;
        this.contratExecute = contratExecute;
        this.contratArchive = contratArchive;
        this.prestation = prestation;

        for (int i = 0; i < prestation.size(); i++){
            prestation.get(i).addContrat(this);
        }

        LOGGER.log(Level.INFO, "Un nouveau Contrat a été créé.");

    }


//    Getter

    /**
     * Renvoie l'identifiant du contrat.
     *
     * @return l'identifiant du contrat de type String
     */
    public String getIdContrat() {
        return idContrat;
    }

    /**
     * Renvoie le nom du client.
     *
     * @return le nom du client de type String
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * Renvoie le prénom du client.
     *
     * @return le prénom du client de type String
     */
    public String getPrenomClient() {
        return prenomClient;
    }

    /**
     * Renvoie l'adresse du client.
     *
     * @return l'adresse du client de type String
     */
    public String getAdresseClient() {
        return adresseClient;
    }

    /**
     * Renvoie le code postal associé à ce contrat.
     *
     * @return le code postal associé à ce contrat de type String
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Renvoie la ville du client.
     *
     * @return la ville du client de type String
     */
    public String getVilleClient() {
        return villeClient;
    }

    /**
     * Renvoie le numéro du client.
     *
     * @return le numéro du client de type String
     */
    public String getNumeroClient() {
        return numeroClient;
    }

    /**
     * Renvoie le montant total du contrat.
     *
     * @return le montant total du contrat de type Double
     */
    public Double getMontantTotal() {
        return montantTotal;
    }

    /**
     * Renvoie le montant réglé pour ce contrat.
     *
     * @return le montant réglé pour ce contrat de type Double
     */
    public Double getMontantRegle() {
        return montantRegle;
    }

    /**
     * Renvoie l'état du règlement du contrat.
     *
     * @return true si le règlement est terminé, sinon false.
     */
    public Boolean getReglementTerminer() {
        return reglementTerminer;
    }

    /**
     * Renvoie les notes associées à ce contrat.
     *
     * @return les notes associées à ce contrat de type String
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Renvoie l'état du contrat (signé ou non).
     *
     * @return true si le contrat est signé, sinon false.
     */
    public Boolean getContratSigne() {
        return contratSigne;
    }

    /**
     * Renvoie l'état d'exécution du contrat.
     *
     * @return true si le contrat est exécuté, sinon false.
     */
    public Boolean getContratExecute() {
        return contratExecute;
    }

    /**
     * Renvoie l'état d'archivage du contrat.
     *
     * @return true si le contrat est archivé, sinon false.
     */
    public Boolean getContratArchive() {
        return contratArchive;
    }

    /**
     * Renvoie la liste des prestations associées à ce contrat.
     *
     * @return la liste des prestations associées à ce contrat de type ArrayList<Prestation>
     */
    public ArrayList<Prestation> getPrestation() {
        return prestation;
    }

//    Setter

    /**
     * Modifie le nom du client.
     *
     * @param nomClient le nouveau nom du client. Type String
     */
    public void setNomClient(String nomClient) {
        try{
            this.nomClient = ContraintesUtilitaire.formatNom(nomClient);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le prénom du client "+nomClient+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Modifie le prénom du client.
     *
     * @param prenomClient le nouveau prénom du client. Type String
     */
    public void setPrenomClient(String prenomClient) {
        try{
            this.prenomClient = ContraintesUtilitaire.formatPrenom(prenomClient);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le prénom du client "+prenomClient+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Modifie l'adresse du client.
     *
     * @param adresseClient la nouvelle adresse du client. Type String
     */
    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    /**
     * Modifie le code postal associé à ce contrat.
     *
     * @param codePostal le nouveau code postal associé à ce contrat. Type String
     */
    public void setCodePostal(String codePostal) {
        try{
            this.codePostal = ContraintesUtilitaire.normalizeStringNumbers(codePostal, 5);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le code postal "+codePostal+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Modifie la ville du client.
     *
     * @param villeClient la nouvelle ville du client. Type String
     */
    public void setVilleClient(String villeClient) {
        this.villeClient = villeClient;
    }

    /**
     * Modifie le numéro du client.
     *
     * @param numeroClient le nouveau numéro du client. Type String
     */
    public void setNumeroClient(String numeroClient) {
        try{
            this.numeroClient = ContraintesUtilitaire.normalizeStringNumbers(numeroClient, 10);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le numero de téléphone du client "+numeroClient+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Modifie le montant total du contrat.
     *
     * @param montantTotal le nouveau montant total du contrat. Type Double
     */
    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    /**
     * Modifie le montant réglé pour ce contrat.
     *
     * @param montantRegle le nouveau montant réglé pour ce contrat. Type Double
     */
    public void setMontantRegle(Double montantRegle) {
        this.montantRegle = montantRegle;
    }

    /**
     * Modifie l'état du règlement du contrat.
     *
     * @param reglementTerminer true si le règlement est terminé, sinon false. Type Boolean
     */
    public void setReglementTerminer(Boolean reglementTerminer) {
        this.reglementTerminer = reglementTerminer;
    }

    /**
     * Modifie les notes associées à ce contrat.
     *
     * @param notes les nouvelles notes associées à ce contrat. Type String
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Modifie l'état du contrat (signé ou non).
     *
     * @param contratSigne true si le contrat est signé, sinon false. Type Boolean
     */
    public void setContratSigne(Boolean contratSigne) {
        this.contratSigne = contratSigne;
    }

    /**
     * Modifie l'état d'exécution du contrat.
     *
     * @param contratArchive true si le contrat est archivé, sinon false. Type Boolean
     */
    public void setContratArchive(Boolean contratArchive) {
        this.contratArchive = contratArchive;
    }

    /**
     * Modifie la liste des prestations associées à ce contrat.
     *
     * @param prestation la nouvelle liste des prestations associées à ce contrat. Type ArrayList<Prestation>
     */
    public void setPrestation(ArrayList<Prestation> prestation) {
        this.prestation = prestation;
    }

    /**
     * Modifie l'état d'exécution du contrat.
     * @param contratExecute true si le contrat est exécuté, sinon false. Type Boolean
     */
    public void setContratExecute(Boolean contratExecute) {
        this.contratExecute = contratExecute;
    }


    /**
     * Modifie l'état d'exécution du contrat.
     *
     * @param dateObseques date des obsèques. Type LocalDate
     * @return true si le contrat est exécuté, sinon false. Type Boolean
     */
    public boolean setContratExecute(LocalDate dateObseques) {
        LocalDate now = LocalDate.now();
        if (now.isAfter(dateObseques)) {
            this.contratExecute = true; // TODO : do something for ContratExecute
            return true;
        }
        this.contratExecute = false;
        return false;
    }

//      Autre méthode

    /**
     * Ajoute une prestation à la liste des prestations associées à ce contrat.
     *
     * @param element la prestation à ajouter. Type Prestation
     */
    public void addPrestation( Prestation element) {
        prestation.add(element);
    }

    /**
     * Supprime une prestation de la liste des prestations associées à ce contrat.
     *
     * @param o la prestation à supprimer. Type Object
     */
    public void removePrestation(Object o) {
        prestation.remove(o);
    }



}
