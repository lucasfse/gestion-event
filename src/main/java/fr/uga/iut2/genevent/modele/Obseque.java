package fr.uga.iut2.genevent.modele;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Obseque qui hérite de la classe Evenement, elle permet de créer un événement obsèque, avec en plus des attributs de Evenement. <br>
 * <ul>
 *     <li>vehicule</li>
 *     <li>assistant funéraire</li>
 *     <li>maître de cérémonie</li>
 *     <li>marbriers</li>
 *     <li>porteurs</li>
 *     <li>thanatopracteur</li>
 *     <li>contrat</li>
 * </ul>
 * ATTENTION : Les attributs marbier, porteur, thanatoprecteur sont des listes d'employés.
 */
public class Obseque extends Evenement{

//      Attribut

    private Vehicule vehicule;
    private Employe assistantFunaire;
    private Employe maitreCeremonie;
    private ArrayList<Employe> marbriers;
    private ArrayList<Employe> porteurs;
    private ArrayList<Employe> thanatopracteur;
    private Contrat contrat;

    //    Logger

    private static Logger LOGGER = Logger.getLogger(Obseque.class.getName());


//    Constructeur

    /**
     * Constructeur de la classe Obseque avec les informations d'un événement et les employés en lien avec les obsèques.
     *
     * @param contrat le contrat associé à l'objet Obseque
     * @param id l'identifiant de l'objet Obseque
     * @param nom le nom de l'objet Obseque
     * @param lieu le lieu de l'objet Obseque
     * @param heureDebut l'heure de début de l'objet Obseque
     * @param heureFin l'heure de fin de l'objet Obseque
     * @param date la date de l'objet Obseque
     * @param details les détails de l'objet Obseque
     * @param assistantFunaire l'employé assistant funéraire associé à l'objet Obseque
     * @param maitreCeremonie l'employé qui est le maître de cérémonie des obsèques
     * @param marbriers la liste des marbriers associés à l'objet Obseque
     * @param porteurs la liste des employés porteurs pour les obsèques
     * @param thanatopracteurs la liste des thanatopracteurs associés à l'objet Obseque
     * ATTENTION : Les attributs marbier, porteur, thanatoprecteur sont des listes d'employés et doivent être remplis de minimum un employé.
     */
    public Obseque(Contrat contrat, String id, String nom, String lieu, LocalTime heureDebut, LocalTime heureFin, LocalDate date, String details, Employe assistantFunaire,
                   Employe maitreCeremonie, ArrayList<Employe> marbriers, ArrayList<Employe> porteurs, ArrayList<Employe> thanatopracteurs, Vehicule vehicule) {
        super(id, nom, lieu, date, heureDebut, heureFin, details);
        this.contrat = contrat;
        this.assistantFunaire = assistantFunaire;
        this.maitreCeremonie = maitreCeremonie;
        this.marbriers = marbriers;
        this.porteurs = porteurs;
        this.thanatopracteur = thanatopracteurs;
        maitreCeremonie.addEvenement(this);
        assistantFunaire.addEvenement(this);
        for (Employe marbrier : marbriers){
            marbrier.addEvenement(this);
        }
        for (Employe porteur : porteurs){
            porteur.addEvenement(this);
        }
        for (Employe thanatopracteur : thanatopracteurs){
            thanatopracteur.addEvenement(this);
        }
        this.vehicule = vehicule;
        LOGGER.log(Level.INFO, "Une Obseque (Evenement) as été créé.");

    }

//    Getter


    /**
     * Renvoie le véhicule associé à l'objet Obseque.
     *
     * @return le véhicule de type Vehicule
     */
    public Vehicule getVehicule() {
        return vehicule;
    }

    /**
     * Renvoie l'employé assistant funéraire associé à l'objet Obseque.
     *
     * @return l'employé assistant funéraire de type Employe
     */
    public Employe getAssistantFunaire() {
        return assistantFunaire;
    }

    /**
     * Renvoie l'employé qui est le maître de cérémonie des obsèques.
     *
     * @return l'employé maître de cérémonie de type Employe
     */
    public Employe getMaitreCeremonie() {
        return maitreCeremonie;
    }

    /**
     * Renvoie la liste des marbriers associés à l'objet Obseque.
     *
     * @return la liste des marbriers de type ArrayList<Employe>
     */
    public ArrayList<Employe> getMarbriers() {
        return marbriers;
    }

    /**
     * Renvoie la liste des employés porteurs pour les obsèques.
     *
     * @return la liste des porteurs de type ArrayList<Employe>
     */
    public ArrayList<Employe> getPorteurs() {
        return porteurs;
    }

    /**
     * Renvoie la liste des thanatopracteurs associés à l'objet Obseque.
     *
     * @return la liste des thanatopracteurs de type ArrayList<Employe>
     */
    public ArrayList<Employe> getThanatopracteur() {
        return thanatopracteur;
    }

    /**
     * Renvoie le contrat associé à l'objet Obseque.
     *
     * @return le contrat de type Contrat
     */
    public Contrat getContrat() {
        return contrat;
    }

//    Setter

    /**
     * Modifie le véhicule associé à l'objet Obseque.
     *
     * @param vehicule le nouveau véhicule. Type Vehicule
     */
    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    /**
     * Modifie l'employé assistant funéraire associé à l'objet Obseque.
     *
     * @param assistantFunaire le nouvel employé assistant funéraire. Type Employe
     */
    public void setAssistantFunaire(Employe assistantFunaire) {
        this.assistantFunaire = assistantFunaire;
    }

    /**
     * Modifie l'employé qui est le maître de cérémonie des obsèques.
     *
     * @param maitreCeremonie le nouvel employé maître de cérémonie. Type Employe
     */
    public void setMaitreCeremonie(Employe maitreCeremonie) {
        this.maitreCeremonie = maitreCeremonie;
    }

    /**
     * Modifie la liste des marbriers associés à l'objet Obseque.
     *
     * @param marbriers la nouvelle liste des marbriers. Type ArrayList<Employe>
     */
    public void setMarbriers(ArrayList<Employe> marbriers) {
        this.marbriers = marbriers;
    }

    /**
     * Modifie la liste des employés porteurs pour les obsèques.
     *
     * @param porteurs la nouvelle liste des porteurs. Type ArrayList<Employe>
     */
    public void setPorteurs(ArrayList<Employe> porteurs) {
        this.porteurs = porteurs;
    }

    /**
     * Modifie la liste des thanatopracteurs associés à l'objet Obseque.
     *
     * @param thanatopracteur la nouvelle liste des thanatopracteurs. Type ArrayList<Employe>
     */
    public void setThanatopracteur(ArrayList<Employe> thanatopracteur) {
        this.thanatopracteur = thanatopracteur;
    }

    /**
     * Modifie le contrat associé à l'objet Obseque.
     *
     * @param contrat le nouveau contrat. Type Contrat
     */
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

//  Autre méthode

    //      ADD

    /**
     * Ajoute un marbrier à la liste des marbriers associés à l'objet Obseque.
     *
     * @param marbrier l'employé marbrier à ajouter. Type Employe
     */
    public void addMarbrier(Employe marbrier) {
        this.marbriers.add(marbrier);
    }

    /**
     * Ajoute un employé à la liste des porteurs pour cet objet Obseque.
     *
     * @param porteur l'employé à ajouter à la liste des porteurs. Type Employe
     */
    public void addPorteur(Employe porteur) {
        this.porteurs.add(porteur);
    }

    /**
     * Ajoute un thanatopracteur à la liste des thanatopracteurs associés à l'objet Obseque.
     *
     * @param thanatopracteur l'employé thanatopracteur à ajouter. Type Employe
     */
    public void addthanatopracteur(Employe thanatopracteur) {
        this.thanatopracteur.add(thanatopracteur);
    }

    /**
     * Ajoute un véhicule à la liste des véhicules associés à l'objet Obseque.
     *
     * @param vehicule le véhicule à ajouter. Type Vehicule
     */
    public void addVehicule(Vehicule vehicule){
        this.vehicule = vehicule;
    }

    //      REMOVE

    /**
     * Supprime un marbrier de la liste des marbriers associés à l'objet Obseque.
     *
     * @param marbrier l'employé marbrier à supprimer. Type Employe
     */
    public void removeMarbrier(Employe marbrier) {
        this.marbriers.remove(marbrier);
    }

    /**
     * Supprime un employé de la liste des porteurs pour cet objet Obseque.
     *
     * @param porteur l'employé à supprimer de la liste des porteurs. Type Employe
     */
    public void removePorteur(Employe porteur) {
        this.porteurs.remove(porteur);
    }

    /**
     * Supprime un thanatopracteur de la liste des thanatopracteurs associés à l'objet Obseque.
     *
     * @param thanatopracteur l'employé thanatopracteur à supprimer. Type Employe
     */
    public void removethanatopracteur(Employe thanatopracteur) {
        this.thanatopracteur.remove(thanatopracteur);
    }

    // Autres méthodes

    /**
     * Permet de modifier le contrat associé à l'objet Obseque.
     */
    public boolean updateContratExecute(){
        return contrat.setContratExecute(super.getDate());
    }



}
