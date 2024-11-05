package fr.uga.iut2.genevent.modele;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Calendrier qui permet de créer un calendrier contenant la liste des événements.
 */
public class Calendrier {


//    Attribut

    private ArrayList<Evenement> evenements = new ArrayList<>();

//  Logger

    private static final Logger LOGGER = Logger.getLogger(Calendrier.class.getName());


//    Constructeur

    /**
     * Constructeur de la classe Calendrier sans assignation au attribut.
     */
    public Calendrier() {
        LOGGER.log(Level.INFO, "Calendrier a été créé.");

    }

    /**
     * Constructeur de la classe Calendrier avec la liste des événements.
     *
     * @param evenements la liste des événements, Type ArrayList<Evenement>
     */
    public Calendrier(ArrayList<Evenement> evenements) {
        this.evenements = evenements;
        LOGGER.log(Level.INFO, "Calendrier a été créé.");
    }

//    Getter

    /**
     * Renvoie la liste des événements.
     *
     * @return la liste des événements de type ArrayList<Evenement>
     */
    public ArrayList<Evenement> getCalendrier() {
        return evenements;
    }


    /**
     * Renvoie la liste des événements de type RdvClient.
     *
     * @return la liste des événements de type RdvClient de type ArrayList<RdvClient>
     */
    public ArrayList<RdvClient> getRdvClient() {
        ArrayList<RdvClient> rdvClient = new ArrayList<>();
        for (Evenement evenement : evenements) {
            if (evenement instanceof RdvClient) {
                rdvClient.add((RdvClient) evenement);
            }
        }
        return rdvClient;
    }


    /**
     * Renvoie un événement de type RdvClient en fonction de son identifiant.
     * @param rdvClient l'événement de type RdvClient, Type RdvClient
     * @return l'événement de type RdvClient correspondant à l'identifiant, Type RdvClient
     */
    public RdvClient getRdvClient(RdvClient rdvClient) {
        RdvClient rdvClient1 = null;
        for (Evenement evenement : evenements) {
            if (evenement instanceof RdvClient) {
                if (evenement.getIdEvenement().equals(rdvClient.getIdEvenement())) {
                    rdvClient1 = (RdvClient) evenement;
                }
            }
        }
        return rdvClient1;
    }


    /**
     * Renvoie la liste des événements de type Obseques.
     *
     * @return la liste des événements de type Obseques de type ArrayList<Obseque>
     */
    public ArrayList<Obseque> getObseques() {
        ArrayList<Obseque> obseques = new ArrayList<>();
        for (Evenement evenement : evenements) {
            if (evenement instanceof Obseque) {
                obseques.add((Obseque) evenement);
            }
        }
        return obseques;
    }


    /**
     * Renvoie un événement de type Obseques en fonction de son identifiant.
     * @param obseque l'événement de type Obseques, Type Obseque
     * @return l'événement de type Obseques correspondant à l'identifiant, Type Obseque
     */
    public Obseque getObseque(Obseque obseque) {
        Obseque obseque1 = null;
        for (Evenement evenement : evenements) {
            if (evenement instanceof Obseque) {
                if (evenement.getIdEvenement().equals(obseque.getIdEvenement())) {
                    obseque1 = (Obseque) evenement;
                }
            }
        }
        return obseque1;
    }



    /**
     * Renvoie un événement en fonction de son identifiant.
     *
     * @param id l'identifiant de l'événement, Type String
     * @return l'événement correspondant à l'identifiant de type Evenement
     */
    public Evenement getEvenement(String id ) {
        for (Evenement evenement : evenements) {
            if (evenement.getIdEvenement().equals(id)) {
                return evenement;
            }
        }
        return null;
    }

//    Setter

    /**
     * Modifie la liste des événements.
     *
     * @param evenements la nouvelle liste des événements, Type ArrayList<Evenement>
     */
    public void setEvenements(ArrayList<Evenement> evenements) {
        this.evenements = evenements;
    }

//    Autre méthode

    /**
     * Ajoute un événement à la liste des événements.
     *
     * @param element l'événement à ajouter, Type Evenement
     */
    public void addEvenement(Evenement element) {
        evenements.add(element);
    }

    /**
     * Supprime un événement de la liste des événements.
     *
     * @param o l'événement à supprimer, Type Evenement
     * @return true si l'événement a été supprimé, false sinon, Type boolean
     */
    public boolean removeEvenement(Evenement o) {
        return evenements.remove(o);
    }
}
