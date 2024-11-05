package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Employe qui permet de créer un employé des pompes funèbre. <br>
 * <ul>
 *     <li>id</li>
 *     <li>nom</li>
 *     <li>prenom</li>
 *     <li>fonction</li>
 *     <li>numeroTel</li>
 *     <li>dateDebutContrat</li>
 *     <li>dateFinContrat</li>
 *     <li>nbHeuresSemaine</li>
 *     <li>abscences</li>
 *     <li>contratTermine</li>
 *     <li>listEvenement</li>
 * </ul>
 * ATTENTION, il est important de noter différent élément : <br>
 * <ul>
 * <li>La date de fin de contrat doit être après la date de début de contrat.</li>
 * <li>Le numero de téléphone doit être de 10 chiffres.</li>
 * <li>Le nom et le prénom ne doivent pas être null.</li>
 * <li>Le nom et le prénom ne doivent pas contenir de caractère spéciaux ou de chiffre ou être vide.</li>
 * <li>Un employé peut posséder plusieurs fonction</li>
 * </ul>
 */
public class Employe {

//    Attribut

    private final String id;
    private String nom;
    private String prenom;
    private ArrayList<String> fonction;
    private String numeroTel;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    private Double nbHeuresSemaine;
    private ArrayList<LocalDate> abscences = new ArrayList<>();
    private Boolean contratTermine;
    private ArrayList<Evenement> listEvenement = new ArrayList<>();
    private ArrayList<LocalDate> abscenceDebut = new ArrayList<>();
    private ArrayList<LocalDate> abscenceFin = new ArrayList<>();

//  Logger
    private static Logger LOGGER = Logger.getLogger(Employe.class.getName());


//    Constructeur

    /**
     * Constructeur de la classe Employe avec les attributs de l'employé. <br>
     * ATTENTION, il est obligatoire que chaque paramètre soit remplis mais les listes peuvent être vide, cela n'affectera pas la sauvegarde.
     *
     * @param id l'identifiant de l'employé de type String
     * @param nom le nom de l'employé de type String
     * @param prenom le prénom de l'employé de type String
     * @param fonction la fonction de l'employé de type ArrayList<String>
     * @param numeroTel le numéro de téléphone de l'employé de type String
     * @param dateDebutContrat la date de début du contrat de l'employé de type LocalDate
     * @param dateFinContrat la date de fin du contrat de l'employé de type LocalDate
     * @param nbHeuresSemaine le nombre d'heures par semaine de l'employé de type Double
     * @param abscences les absences de l'employé de type ArrayList<LocalDate>
     * @param contratTermine le contrat de l'employé est-il terminé de type Boolean
     */
    public Employe(String id, String nom, String prenom, ArrayList<String> fonction, String numeroTel,
                   LocalDate dateDebutContrat, LocalDate dateFinContrat, Double nbHeuresSemaine, ArrayList<LocalDate> abscences,
                   Boolean contratTermine) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.numeroTel = numeroTel;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.nbHeuresSemaine = nbHeuresSemaine;
        this.abscences = abscences;
        this.contratTermine = contratTermine;

        LOGGER.log(Level.INFO, "Un Employé a été créé.");

    }

    /**
     * Constructeur de la classe Employe avec les attributs de l'employé. <br>
     * ATTENTION, il est obligatoire que chaque paramètre soit remplis mais les listes peuvent être vide, cela n'affectera pas la sauvegarde.
     *
     * @param id l'identifiant de l'employé de type String
     * @param nom le nom de l'employé de type String
     * @param prenom le prénom de l'employé de type String
     * @param fonction la fonction de l'employé de type ArrayList<String>
     * @param numeroTel le numéro de téléphone de l'employé de type String
     * @param dateDebutContrat la date de début du contrat de l'employé de type LocalDate
     * @param dateFinContrat la date de fin du contrat de l'employé de type LocalDate
     * @param nbHeuresSemaine le nombre d'heures par semaine de l'employé de type Double
     * @param abscences les absences de l'employé de type ArrayList<LocalDate>
     * @param contratTermine le contrat de l'employé est-il terminé de type Boolean
     * @param listEvenement la liste des événements de l'employé de type ArrayList<Evenement>
     */
    public Employe(String id, String nom, String prenom, ArrayList<String> fonction, String numeroTel,
                   LocalDate dateDebutContrat, LocalDate dateFinContrat, Double nbHeuresSemaine, ArrayList<LocalDate> abscences,
                   Boolean contratTermine, ArrayList<Evenement> listEvenement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.numeroTel = numeroTel;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.nbHeuresSemaine = nbHeuresSemaine;
        this.abscences = abscences;
        this.contratTermine = contratTermine;
        this.listEvenement = listEvenement;
        this.abscenceDebut = getAbscenceDebut();
        this.abscenceFin = getAbscenceFin();

        LOGGER.log(Level.INFO, "Un Employé a été créé.");

    }

//    Getter

    /**
     * Renvoie l'identifiant de l'employé.
     *
     * @return l'identifiant de l'employé de type String
     */
    public String getId() {
        return id;
    }

    /**
     * Renvoie le nom de l'employé.
     *
     * @return le nom de l'employé de type String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie le prénom de l'employé.
     *
     * @return le prénom de l'employé de type String
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Renvoie la fonction de l'employé.
     *
     * @return la fonction de l'employé de type ArrayList<String>
     */
    public ArrayList<String> getFonction() {
        return fonction;
    }

    /**
     * Renvoie le numéro de téléphone de l'employé.
     *
     * @return le numéro de téléphone de l'employé de type String
     */
    public String getNumeroTel() {
        return numeroTel;
    }

    /**
     * Renvoie la date de début du contrat de l'employé.
     *
     * @return la date de début du contrat de l'employé de type LocalDate
     */
    public LocalDate getDateDebutContrat() {
        return dateDebutContrat;
    }

    /**
     * Renvoie la date de fin du contrat de l'employé.
     *
     * @return la date de fin du contrat de l'employé de type LocalDate
     */
    public LocalDate getDateFinContrat() {
        return dateFinContrat;
    }

    /**
     * Renvoie le nombre d'heures par semaine de l'employé.
     *
     * @return le nombre d'heures par semaine de l'employé de type Double
     */
    public Double getNbHeuresSemaine() {
        return nbHeuresSemaine;
    }

    /**
     * Renvoie les absences de l'employé.
     *
     * @return les absences de l'employé de type ArrayList<LocalDate>
     */
    public ArrayList<LocalDate> getAbscences() {
        return abscences;
    }

    /**
     * Renvoie si le contrat de l'employé est terminé.
     *
     * @return si le contrat de l'employé est terminé de type Boolean
     */
    public ArrayList<Evenement> getListEvenement() {
        return listEvenement;
    }

    /**
     * Renvoie si le contrat de l'employé est terminé.
     *
     * @return si le contrat de l'employé est terminé de type Boolean
     */
    public Boolean getContratTermine() {
        return contratTermine;
    }

    /**
     * Renvoie les absences de l'employé.
     *
     * @return les absences de l'employé de type ArrayList<LocalDate>
     */
    public ArrayList<LocalDate> getAbscenceDebut() {
        ArrayList<LocalDate> abscences = new ArrayList<>();
        for (int i = 0; i<this.abscences.size(); i++){
            if (i%2 == 0){
                abscences.add(this.abscences.get(i));
            }
        }
        return abscences;
    }

    /**
     * Renvoie les absences de l'employé.
     *
     * @return les absences de l'employé de type ArrayList<LocalDate>
     */
    public ArrayList<LocalDate> getAbscenceFin() {
        ArrayList<LocalDate> abscences = new ArrayList<>();
        for (int i = 0; i<this.abscences.size(); i++){
            if (i%2 != 0){
                abscences.add(this.abscences.get(i));
            }
        }
        return abscences;
    }

    //    Setter

    /**
     * Modifie le nom de l'employé.
     *
     * @param nom le nouveau nom de l'employé. Type String
     */
    public void setNom(String nom) {
        try{
            this.nom = ContraintesUtilitaire.formatNom(nom);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le prenom de l'Employe "+nom+" n'est pas valide.");
            throw e;
        }
    }

    /**
     * Modifie le prénom de l'employé.
     *
     * @param prenom le nouveau prénom de l'employé. Type String
     */
    public void setPrenom(String prenom) {
        try{
            this.prenom = ContraintesUtilitaire.formatPrenom(prenom);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le prenom de l'Employe "+prenom+" n'est pas valide.");
            throw e;
        }
    }

    /**
     * Modifie la fonction de l'employé.
     *
     * @param fonction la nouvelle fonction de l'employé. Type ArrayList<String>
     */
    public void setFonction(ArrayList<String> fonction) {
        this.fonction = fonction;
    }

    /**
     * Modifie le numéro de téléphone de l'employé.
     *
     * @param numeroTel le nouveau numéro de téléphone de l'employé. Type String
     */
    public void setNumeroTel(String numeroTel) {
        try{
            this.numeroTel = ContraintesUtilitaire.normalizeStringNumbers(numeroTel, 10);
        } catch(NullPointerException e){
            LOGGER.log(Level.FINEST, "Le numéro de téléphone d'Employe' "+numeroTel+" n'est pas valide.");
            throw e;
        }
    }

    /**
     * Modifie la date de début du contrat de l'employé.
     *
     * @param dateDebutContrat la nouvelle date de début du contrat de l'employé. Type LocalDate
     */
    public void setDateDebutContrat(LocalDate dateDebutContrat) {
        if (dateDebutContrat.isAfter(dateFinContrat)) {
            LOGGER.log(Level.FINEST, "Le date de début de contrat ["+dateDebutContrat+"] doit être avant "+dateFinContrat+" pour être valide valide.");
            throw new IllegalArgumentException("La date de début du contrat doit être avant la date de fin du contrat.");
        } else {
            this.dateDebutContrat = dateDebutContrat;
        }
    }

    /**
     * Modifie la date de fin du contrat de l'employé.
     *
     * @param dateFinContrat la nouvelle date de fin du contrat de l'employé. Type LocalDate
     */
    public void setDateFinContrat(LocalDate dateFinContrat) {
        if (dateFinContrat.isBefore(dateDebutContrat)) {
            LOGGER.log(Level.FINEST, "Le date de fin de contrat ["+dateFinContrat+"] doit être après "+dateDebutContrat+" pour être valide valide.");
            throw new IllegalArgumentException("La date de fin du contrat doit être après la date de début du contrat.");
        } else {
            this.dateFinContrat = dateFinContrat;
        }
    }

    /**
     * Modifie le nombre d'heures par semaine de l'employé.
     *
     * @param nbHeuresSemaine le nouveau nombre d'heures par semaine de l'employé. Type Double
     */
    public void setNbHeuresSemaine(Double nbHeuresSemaine) {
        this.nbHeuresSemaine = nbHeuresSemaine;
    }

    /**
     * Modifie les absences de l'employé.
     *
     * @param abscences les nouvelles absences de l'employé. Type ArrayList<LocalDate>
     */
    public void setAbscences(ArrayList<LocalDate> abscences) {
        this.abscences = abscences;
    }

    /**
     * Modifie si le contrat de l'employé est terminé.
     *
     * @param listEvenement si le contrat de l'employé. Type ArrayList<Evenement>
     */
    public void setListEvenement(ArrayList<Evenement> listEvenement) {
        this.listEvenement = listEvenement;
    }

//    Autre Méthode

    /**
     * Ajoute un événement à la liste des événements de l'employé.
     *
     * @param element l'événement à ajouter. Type Evenement
     */
    public void addEvenement(Evenement element) {
        listEvenement.add(element);
    }

    /**
     * Supprime un événement de la liste des événements de l'employé.
     *
     * @param o l'événement à supprimer. Type Evenement
     */
    public void removeEvenement(Evenement o) {
        listEvenement.remove(o);
    }

    /**
     * Ajoute une fonction à la liste des fonctions de l'employé.
     *
     * @param element la fonction à ajouter. Type String
     */
    public void addFonction(String element) {
        fonction.add(element);
    }

    /**
     * Supprime une fonction de la liste des fonctions de l'employé.
     *
     * @param o la fonction à supprimer. Type Object
     */
    public void removeFonction(Object o) {
        fonction.remove(o);
    }

    /**
     * Ajoute une absence à la liste des absences de l'employé.
     *
     * @param element l'absence à ajouter. Type LocalDate
     */
    public void addAbscence(LocalDate element) {
        abscences.add(element);
    }

    /**
     * Supprime une absence de la liste des absences de l'employé.
     *
     * @param o l'absence à supprimer. Type Object
     */
    public void removeAbscence(Object o) {
        abscences.remove(o);
    }

    // Autres méthodes

    /**
     * Vérifie si le contrat de l'employé est terminé.
     *
     * @return si le contrat de l'employé est terminé. Type Boolean
     */
    public boolean isContratTermine(){
        LocalDate now = LocalDate.now();
        if (now.isAfter(dateFinContrat)) {
            this.contratTermine = true; // TODO : do something for contratTermine
            return true;
        }
        this.contratTermine = false;
        return false;
    }
}
