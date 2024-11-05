package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Vehicule qui permet de créer un véhicule. <br>
 * <ul>
 *     <li>plaqueImmat</li>
 *     <li>enEtat</li>
 *     <li>obseques</li>
 * </ul>
 * ATTENTION : Les attributs plaqueImmat, enEtat sont obligatoires.
 * Les obseques sont une liste d'obsèques.
 */
public class Vehicule {

//    Attribut
    private String plaqueImmat;
    private Boolean enEtat;
    private ArrayList<Obseque> obseques = new ArrayList<>();

//    Logger

    private static Logger LOGGER = Logger.getLogger(Vehicule.class.getName());

//    Constructeur

    /**
     * Constructeur de la classe Vehicule avec les informations d'un véhicule.
     *
     * @param plaqueImmat la plaque d'immatriculation du véhicule de type String
     * @param enEtat l'état du véhicule de type Boolean
     */
    public Vehicule(String plaqueImmat, Boolean enEtat) {
        this.plaqueImmat = plaqueImmat;
        this.enEtat = enEtat;
        LOGGER.log(Level.INFO, "Un Vehicule as été créé.");

    }

    /**
     * Constructeur de la classe Vehicule avec les informations d'un véhicule.
     *
     * @param plaqueImmat la plaque d'immatriculation du véhicule de type String
     * @param enEtat l'état du véhicule de type Boolean
     * @param obseques la liste des obsèques associées à ce véhicule de type ArrayList
     */
    public Vehicule(String plaqueImmat, Boolean enEtat, ArrayList<Obseque> obseques) {
        this.plaqueImmat = plaqueImmat;
        this.enEtat = enEtat;
        this.obseques = obseques;
        LOGGER.log(Level.INFO, "Un Vehicule as été créé.");

    }

//    Getter


    /**
     * Renvoie la plaque d'immatriculation du véhicule.
     *
     * @return la plaque d'immatriculation du véhicule de type String
     */
    public String getPlaqueImmat() {
        return plaqueImmat;
    }

    /**
     * Renvoie l'état du véhicule.
     * 
     * @return true si le véhicule est en état, false sinon.
     */
    public Boolean getEnEtat() {
        return enEtat;
    }

    /**
     * Renvoie la liste des obsèques associées à ce véhicule.
     * 
     * @return la liste des obsèques associées à ce véhicule de type ArrayList
     */
    public ArrayList<Obseque> getObseques() {
        return obseques;
    }

//    Setter


    /**
     * Modifie la plaque d'immatriculation du véhicule.
     *
     * @param plaqueImmat la nouvelle plaque d'immatriculation du véhicule de type String
     */
    public void setPlaqueImmat(String plaqueImmat) {
        try{
            this.plaqueImmat = ContraintesUtilitaire.normalizeStringPlaque(plaqueImmat);
        } catch(Exception e){
            LOGGER.log(Level.FINEST, "La plaque d'immatriculation du Vehicule "+plaqueImmat+" n'est pas valide.");
            throw e;
        }
    }

    /**
     * Modifie l'état du véhicule.
     *
     * @param enEtat true si le véhicule est en état, false sinon.
     */
    public void setEnEtat(Boolean enEtat) {
        this.enEtat = enEtat;
    }

    /**
     * Modifie la liste des obsèques associées à ce véhicule.
     *
     * @param obseques la nouvelle liste des obsèques associées à ce véhicule de type ArrayList
     */
    public void setObseques(ArrayList<Obseque> obseques) {
        this.obseques = obseques;
    }

//    autre méthode


    /**
     * Ajoute un élément Obseque à la liste des obsèques.
     * 
     * @param element l'élément Obseque à ajouter à la liste des obsèques
     */
    public void addObseque( Obseque element) {

        obseques.add(element);
        element.addVehicule(this);
    }

    /**
     * Supprime un objet de la liste des obsèques.
     * 
     * @param o l'objet à supprimer de la liste des obsèques
     */
    public void removeobseques(Object o) {
        obseques.remove(o);
    }
}
