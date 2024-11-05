package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe Evenement qui permet de créer un événement. <br>
 * <ul>
 *     <li>idEvenement</li>
 *     <li>calendrier</li>
 *     <li>nom</li>
 *     <li>lieu</li>
 *     <li>date</li>
 *     <li>heureDebut</li>
 *     <li>heureFin</li>
 *     <li>details</li>
 * </ul>
 * ATTENTION : Les attributs nom, lieu, date, heureDebut, heureFin, details sont obligatoires. Mais detail peut être null de plus il doivent être formater. <br>
 * Les id sont générés automatiquement depuis la liste des evenements déjà stocker.
 */
public abstract class Evenement {
//    Attribut
    private final String idEvenement;
    private Calendrier calendrier;
    private String nom;
    private String lieu;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String details;

//    Logger

    private static Logger LOGGER = Logger.getLogger(Evenement.class.getName());


//    Constructeur

    /**
     * Constructeur de la classe Evenement avec les informations d'un événement.
     *
     * @param id l'identifiant de l'événement de type String
     * @param nom le nom de l'événement de type String
     * @param lieu le lieu de l'événement de type String
     * @param heureDebut l'heure de début de l'événement de type LocalTime
     * @param heureFin l'heure de fin de l'événement de type LocalTime
     * @param date la date de l'événement de type LocalDate
     */
    public Evenement(String id, String nom, String lieu, LocalTime heureDebut, LocalTime heureFin, LocalDate date) {
        this.idEvenement = id;
        this.nom = nom;
        this.lieu = lieu;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.date = date;

        LOGGER.log(Level.INFO, "Un "+Evenement.class.getName()+" a été créé.");

    }

    /**
     * Constructeur de la classe Evenement avec les informations d'un événement.
     *
     * @param id l'identifiant de l'événement de type String
     * @param nom le nom de l'événement de type String
     * @param lieu le lieu de l'événement de type String
     * @param date la date de l'événement de type LocalDate
     * @param heureDebut l'heure de début de l'événement de type LocalTime
     * @param heureFin l'heure de fin de l'événement de type LocalTime
     * @param details les détails de l'événement de type String
     */
    public Evenement(String id, String nom, String lieu, LocalDate date, LocalTime heureDebut, LocalTime heureFin, String details) {
        this.idEvenement = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.details = details;
        LOGGER.log(Level.INFO, "Un "+Evenement.class.getName()+" a été créé.");

    }

//    Getter

    /**
     * Renvoie l'identifiant de l'événement.
     *
     * @return l'identifiant de l'événement de type String
     */
    public String getIdEvenement() {
        return idEvenement;
    }

    /**
     * Renvoie le nom de l'événement.
     *
     * @return le nom de l'événement de type String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le lieu de l'événement.
     *
     * @return le lieu de l'événement de type String
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Renvoie la date de l'événement.
     *
     * @return la date de l'événement de type LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Renvoie l'heure de début de l'événement.
     *
     * @return l'heure de début de l'événement de type LocalTime
     */
    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    /**
     * Renvoie l'heure de fin de l'événement.
     *
     * @return l'heure de fin de l'événement de type LocalTime
     */
    public LocalTime getHeureFin() {
        return heureFin;
    }

    /**
     * Renvoie les détails de l'événement.
     *
     * @return les détails de l'événement de type String
     */
    public String getDetails() {
        return details;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

//    Setter

    /**
     * Modifie le nom de l'événement.
     *
     * @param nom le nouveau nom de l'événement. Type String
     */
    public void setNom(String nom) {
        try{
            this.nom = ContraintesUtilitaire.formatPrenom(nom);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le nom "+nom+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Modifie le lieu de l'événement.
     *
     * @param lieu le nouveau lieu de l'événement. Type String
     */
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    /**
     * Modifie la date de l'événement.
     *
     * @param date la nouvelle date de l'événement. Type LocalDate
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Modifie l'heure de début de l'événement.
     *
     * @param heureDebut la nouvelle heure de début de l'événement. Type LocalTime
     */
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     * Modifie l'heure de fin de l'événement.
     *
     * @param heureFin la nouvelle heure de fin de l'événement. Type LocalTime
     */
    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * Modifie les détails de l'événement.
     *
     * @param details les nouveaux détails de l'événement. Type String
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Cette méthode abstraite modifie le contrat de l'événement utilisé dans la classe Obseque.
     */
    public abstract Object getContrat();
}
