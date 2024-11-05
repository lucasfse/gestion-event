package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Prestation qui permet de créer une prestation. <br>
 * <ul>
 *     <li>nom</li>
 *     <li>type</li>
 *     <li>prixHT</li>
 *     <li>prixTTC</li>
 *     <li>contrats</li>
 * </ul>
 * ATTENTION : Les attributs nom, type, prixHT, prixTTC sont obligatoires. Mais detail peut être null de plus il doivent être formater.
 */
public class Prestation {

//    Attribut

    private final String id;
    private String nom;
    private String type;
    private Double prixHT;
    private Double prixTTC;
    private ArrayList<Contrat> contrats = new ArrayList<>();

//    Logger

    private static Logger LOGGER = Logger.getLogger(Prestation.class.getName());

//    Constructeur

    /**
     * Constructeur de la classe Prestation avec les informations d'une prestation.
     * @param prixTTC le prix TTC de la prestation
     * @param prixHT le prix HT de la prestation
     * @param type le type de la prestation
     * @param nom le nom de la prestation
     */
    public Prestation(String id, Double prixTTC, Double prixHT, String type, String nom) {
        this.id = id;
        this.prixTTC = prixTTC;
        this.prixHT = prixHT;
        this.type = type;
        this.nom = nom;
    }


    /**
     * Constructeur de la classe Prestation avec les informations d'une prestation.
     *
     * @param contrats la liste des contrats associés à la prestation
     * @param prixTTC le prix TTC de la prestation
     * @param prixHT le prix HT de la prestation
     * @param type le type de la prestation
     * @param nom le nom de la prestation
     */
    public Prestation(ArrayList<Contrat> contrats, String id, Double prixTTC, Double prixHT, String type, String nom) {
        this.id = id;
        this.contrats = contrats;
        this.prixTTC = prixTTC;
        this.prixHT = prixHT;
        this.type = type;
        this.nom = nom;
        LOGGER.log(Level.INFO, "Une Prestation a été créé.");

    }

//    Getter

    /**
     * Renvoie l'identifiant de la prestation.
     *
     * @return l'identifiant de la prestation de type String
     */
    public String getId() {
        return id;
    }

    /**
     * Renvoie le nom de la prestation.
     *
     * @return le nom de la prestation de type String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le type de la prestation.
     *
     * @return le type de la prestation de type String
     */
    public String getType() {
        return type;
    }

    /**
     * Renvoie le prix hors taxe de la prestation.
     *
     * @return le prix hors taxe de la prestation de type Double
     */
    public Double getPrixHT() {
        return prixHT;
    }

    /**
     * Renvoie le prix TTC de la prestation.
     *
     * @return Le prix TTC de la prestation de type Double
     */
    public Double getPrixTTC() {
        return prixTTC;
    }

    /**
     * Renvoie la liste des contrats associés à cette prestation.
     *
     * @return la liste des contrats associés à cette prestation de type ArrayList<Contrat>
     */
    public ArrayList<Contrat> getContrats() {
        return contrats;
    }

//    Setter


    /**
     * Modifie le nom de la prestation.
     *
     * @param nom le nouveau nom de la prestation. Type String
     */
    public void setNom(String nom) {
        try{
            this.nom = ContraintesUtilitaire.formatPrenom(nom);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le nom de Prestation "+nom+" n'est pas valide.");
            throw e;
        }
    }

    /**
     * Modifie le type de la prestation.
     *
     * @param type le nouveau type de la prestation. Type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Modifie le prix hors taxe de la prestation.
     *
     * @param prixHT le nouveau prix hors taxe de la prestation. Type Double
     */
    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
    }

    /**
     * Modifie le prix TTC de la prestation.
     *
     * @param prixTTC le nouveau prix TTC de la prestation. Type Double
     */
    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    /**
     * Modifie la liste des contrats associés à la prestation.
     *
     * @param contrats la nouvelle liste des contrats associés à la prestation. Type ArrayList<Contrat>
     */
    public void setContrats(ArrayList<Contrat> contrats) {
        this.contrats = contrats;
    }

//    Autre méthode


    /**
     * Ajoute un contrat à la liste des contrats de la prestation.
     * 
     * @param element le contrat à ajouter à la liste des contrats de la prestation de type Contrat
     */
    public void addContrat(Contrat element) {
        contrats.add(element);
    }

    /**
     * Supprime un contrat de la liste des contrats de la prestation.
     * 
     * @param o le contrat à supprimer
     * @return true si le contrat a été supprimé avec succès, sinon false
     */
    public boolean removeContrat(Contrat o) {
        return contrats.remove(o);
    }
}
