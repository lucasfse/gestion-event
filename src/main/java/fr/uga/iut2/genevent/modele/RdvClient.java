package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe RdvClient qui hérite de la classe Evenement, elle permet de créer un rendez-vous client, avec en plus des attributs de Evenement. <br>
 * <ul>
 *     <li>nom du client</li>
 *     <li>prenom du client</li>
 *     <li>numero du client</li>
 *     <li>charger de l'acceuil</li>
 * </ul>
 */
public class RdvClient extends Evenement {

//    Attribut

    private String nomClient;
    private String prenomClient;
    private String numeroTelClient;
    private Employe chargeAcceuil;

//    Logger
    private static Logger LOGGER = Logger.getLogger(RdvClient.class.getName());


//    Construction

    /**
     * Constructeur de la classe RdvClient avec les information d'un événement et celle du client en liens avec le rendez-vous, puis l'employer charger de l'acceuil.
     *
     * @param id l'identifiant du rendez-vous de type String
     * @param nom le nom du rendez-vous de type String
     * @param lieu le lieu du rendez-vous de type String
     * @param heureDebut l'heure de début du rendez-vous de type LocalTime
     * @param heureFin l'heure de fin du rendez-vous de type LocalTime
     * @param date la date du rendez-vous de type LocalDate
     * @param nomClient le nom du client de type String
     * @param prenomClient le prénom du client de type String
     * @param numeroTelClient le numéro de téléphone du client de type String
     * @param chargeAcceuil l'employé en charge de l'accueil de type Employe
     */
    public RdvClient(String id, String nom, String lieu, LocalTime heureDebut, LocalTime heureFin, LocalDate date,
                     String nomClient, String prenomClient, String numeroTelClient, Employe chargeAcceuil, String details) {
        super(id, nom, lieu, date, heureDebut, heureFin, details);
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.numeroTelClient = numeroTelClient;
        this.chargeAcceuil = chargeAcceuil;
        chargeAcceuil.addEvenement(this);
        LOGGER.log(Level.INFO, "Une Obseque (Evenement) as été créé.");

    }

//      Getter

    /**
     * Renvoie le nom du client.
     *
     * @return le nom du client
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     * Renvoie le prénom du client.
     *
     * @return le prénom du client
     */
    public String getPrenomClient() {
        return prenomClient;
    }

    /**
     * Renvoie le numéro de téléphone du client.
     *
     * @return Le numéro de téléphone du client.
     */
    public String getNumeroTelClient() {
        return numeroTelClient;
    }

    /**
     * Renvoie l'employé en charge de l'accueil.
     *
     * @return L'employé en charge de l'accueil.
     */
    public Employe getChargeAcceuil() {
        return chargeAcceuil;
    }

    /**
     * Renvoie le contrat associé au rendez-vous.
     */
    public Contrat contratAssocie() {
        return null;
    }



//      Setter

    /**
     * Permet de modifier le nom du client.
     *
     * @param nomClient le nouveau nom du client
     */
    public void setNomClient(String nomClient) {
        try{
            this.nomClient = ContraintesUtilitaire.formatNom(nomClient);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le nom du client "+nomClient+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Permet de modifier le prénom du client.
     *
     * @param prenomClient le nouveau prénom du client
     */
    public void setPrenomClient(String prenomClient) {
        try{
            this.nomClient = ContraintesUtilitaire.formatPrenom(prenomClient);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le prenom du client "+prenomClient+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Permet de modifier le numéro de téléphone du client.
     *
     * @param numeroTelClient le nouveau numéro de téléphone du client
     */
    public void setNumeroTelClient(String numeroTelClient) {
        try{
            this.numeroTelClient = ContraintesUtilitaire.normalizeStringNumbers(numeroTelClient, 10);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le numéro de téléphone du client "+numeroTelClient+" n'est pas valide.");

            throw e;
        }
    }

    /**
     * Permet de modifier l'employé en charge de l'accueil.
     *
     * @param chargeAcceuil le nouvel employé en charge de l'accueil
     */
    public void setChargeAcceuil(Employe chargeAcceuil) {
        this.chargeAcceuil = chargeAcceuil;
    }

    /**
     * Permet de modifier le contrat associé au rendez-vous.
     */
    @Override
    public Object getContrat() {
        return null;
    }
}
