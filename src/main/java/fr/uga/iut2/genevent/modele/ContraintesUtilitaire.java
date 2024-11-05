package fr.uga.iut2.genevent.modele;

import javafx.scene.control.ComboBox;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe ContraintesUtilitaire qui permet de normaliser les chaînes de caractères et de créer des identifiants uniques pour les événements, les employés et les contrats.
 */
public class ContraintesUtilitaire {


    //    Logger

    private static Logger LOGGER = Logger.getLogger(ContraintesUtilitaire.class.getName());


    /**
     * Convertit une chaîne de caractères Unicode en une chaîne de caractères ASCII.
     *
     * @param s la chaîne de caractères Unicode à convertir
     * @return la chaîne de caractères ASCII convertie
     */
    public static String unicodeToAscii(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }


    /**
     * Normalise une chaîne de caractères en supprimant les caractères spéciaux et en remplaçant les ponctuations par des espaces.
     *
     * @param s la chaîne de caractères à normaliser
     * @return la chaîne de caractères normalisée
     */
    public static String normalizeString(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            throw new NullPointerException("L'entrée ne doit pas être nulle ou vide.");
        }

        //s = unicodeToAscii(s.trim());

        s = s.replaceAll("([.!?])", " $1");
        s = s.replaceAll("[^a-zA-Z0-9\\p{L}]+", " ");
        s = s.replaceAll("µ", " ");

        s = s.replaceAll("\\s+", " ").trim();
        
        return s;
    }



    /**
     * Normalise une chaîne de caractères en supprimant tous les caractères non numériques,
     * à l'exception des virgules.
     *
     * @param s la chaîne de caractères à normaliser
     * @return la chaîne de caractères normalisée
     * @throws NullPointerException si la chaîne de caractères est nulle ou vide
     */
    public static String normalizeStringNumbers(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            LOGGER.log(Level.FINE, "Une entrée est nulle ou vide.");
            throw new NullPointerException("L'entrée ne doit pas être nulle ou vide.");

        }
        s = s.replaceAll("[^0-9.]+", "");

        if (s == null || s.isEmpty() || s.isBlank()) {
            LOGGER.log(Level.FINE, "Une entrée est nulle ou vide.");
            throw new NullPointerException("L'entrée doit contenir des chiffres.");

        }
        return s;
    }

    /**
     * Normalise une chaîne de caractères en supprimant tous les caractères non numériques,
     * puis vérifie si la longueur de la chaîne normalisée correspond à un nombre spécifié.
     *
     * @param s la chaîne de caractères à normaliser
     * @param n le nombre de chiffres attendu dans la chaîne normalisée
     * @return la chaîne de caractères normalisée
     * @throws NullPointerException si l'entrée est nulle ou vide
     * @throws IllegalArgumentException si la longueur de la chaîne normalisée ne correspond pas à n
     */
    public static String normalizeStringNumbers(String s, int n) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            LOGGER.log(Level.FINE, "Une entrée est nulle ou vide.");
            throw new NullPointerException("L'entrée ne doit pas être nulle ou vide.");

        }
        s = s.replaceAll("[^0-9.]+", "");
        if (s.length() != n) {
            LOGGER.log(Level.FINE, "Une entrée devant contenir "+ n +" chiffres n'en contiend que " + s.length() + ".");
            throw new IllegalArgumentException("L'entrée doit contenir "+ n +" chiffres.");

        }

        return s;
    }


    /**
     * Normalise une chaîne de caractères représentant une plaque d'immatriculation.
     *
     * @param s la chaîne de caractères à normaliser
     * @return la chaîne de caractères normalisée
     * @throws NullPointerException si la chaîne est nulle ou vide
     * @throws IllegalArgumentException si la chaîne ne contient pas 7 ou 9 caractères
     */
    public static String normalizeStringPlaque(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) {
            LOGGER.log(Level.FINE, "Une entrée est nulle ou vide.");
            throw new NullPointerException("L'entrée ne doit pas être nulle ou vide.");
        }

        s = unicodeToAscii(s.trim());
        s = s.toUpperCase();
        s = s.replaceAll("[^A-Z0-9]", "");

        if (s.length() != 7) {
            LOGGER.log(Level.FINE, "Une entrée devant contenir 7 ou 9 charactères correctes n'en contiend que " + s.length() + ".");
            throw new IllegalArgumentException("L'entrée doit contenir 7 chiffres.");
        }
        s = s.substring(0, 2).replaceAll("[^A-Z]", "") + "-" + s.substring(2, 5).replaceAll("[^0-9]", "") + "-" + s.substring(5, 7);

        if (s.length() != 9) {
            LOGGER.log(Level.FINE, "Une entrée devant contenir 7 ou 9 charactères correctes n'en contiend que " + s.length() + ".");
            throw new IllegalArgumentException("L'entrée doit contenir 9 chiffres.");
        }

        return s;
    }


    /**
     * Formate le nom en normalisant les caractères et en le mettant en majuscules.
     * 
     * @param nom le nom à formater. Type String
     * @return le nom formaté en majuscules et normalisé de type String
     */
    public static String formatNom(String nom) {
        nom = normalizeString(nom);
        nom = nom.replaceAll("[0-9]+", "");
        nom = nom.toUpperCase();

        if (nom == null || nom.isEmpty() || nom.isBlank()) {
            LOGGER.log(Level.FINE, "Le nom ne doit contenir uniquement des lettres.");
            throw new NullPointerException("Le nom ne doit contenir uniquement des lettres.");

        }

        return nom;

    }

    /**
     * Formate le prénom en mettant la première lettre en majuscule et les autres lettres en minuscules.
     *
     * @param nom le prénom à formater. Type String
     * @return le prénom formaté de type String
     */
    public static String formatPrenom(String nom) {
        nom = normalizeString(nom);
        nom = nom.replaceAll("[0-9]+", "");
        nom = nom.toLowerCase();
        nom = nom.substring(0,1).toUpperCase() + nom.substring(1);

        if (nom == null || nom.isEmpty() || nom.isBlank()) {
            LOGGER.log(Level.FINE, "L'entrée ne doit contenir que des lettres");
            throw new NullPointerException("L'entrée ne doit contenir que des lettres");

        }

        return nom;

    }

    /**
     * Formate le lieu en mettant la première lettre en majuscule et le reste en minuscules.
     * 
     * @param lieu le lieu à formater
     * @return le lieu formaté
     */
    public static String formatLieu(String lieu) {
        lieu = normalizeString(lieu);
        lieu = lieu.toLowerCase();
        lieu = lieu.substring(0,1).toUpperCase() + lieu.substring(1);
        return lieu;

    }

    /**
     * Formate l'heure donnée en une chaîne de caractères au format "HH:mm".
     * 
     * @param heure l'heure à formater (au format "HHmm")
     * @return la chaîne de caractères formatée au format "HH:mm"
     * @throws NullPointerException si l'heure est nulle ou vide
     * @throws IllegalArgumentException si l'heure ne contient pas exactement 4 chiffres
     */
    public static String formatHeure(String heure) {
        if (heure == null || heure.isEmpty() || heure.isBlank()) {
            LOGGER.log(Level.FINE, "Une heure est nulle ou vide.");
            throw new NullPointerException("L'heure ne doit pas être nulle ou vide.");
        }
        heure = heure.replaceAll("[^0-9]","");

        if (heure.length() != 4) {
            LOGGER.log(Level.FINE, "Une entrée devant contenir 4 chiffres corrects n'en contiend que " + heure.length() + ".");
            throw new IllegalArgumentException("L'entrée doit contenir 4 chiffres.");
        }

        heure = heure.substring(0, 2) + ":" + heure.substring(2);

        return heure;

    }

    /**
     * Regarde si le comboBox est vide.
     *
     * @param comboBox est le comboBox que l'on vérifie
     * @throws NullPointerException si rien n'a été séléctionner dans le comboBox
     */
    public static void comboBoxVide(ComboBox<String> comboBox) {
        if (comboBox.getValue()==null){
            LOGGER.log(Level.FINE, "Le comboBox ne doit pas être vide");
            throw new NullPointerException("Le comboBox ne doit pas être vide");
        }

    }

    /**
     * Crée un identifiant unique pour un événement.
     *
     * @param len la longueur de l'identifiant unique de l'événement. Type int
     * @param calendrier le calendrier contenant les événements. Type Calendrier
     * @return l'identifiant unique de l'événement de type String
     */
    public static String idCreateEvent(int len, Calendrier calendrier){
        ArrayList<Evenement> evenements = calendrier.getCalendrier();
        ArrayList<Integer> idList = new ArrayList<>();
        int id = -1;
        for (Evenement evenement : evenements){
            idList.add(Integer.parseInt(evenement.getIdEvenement()));
        }
        for (int i = 1; i<idList.size(); i++){
            if (idList.contains(i)){ continue; }
            else {id = i;}
        }
        if (id == -1){ id = evenements.size()+1;}
        String idString = String.valueOf(id);
        for (int i = idString.length(); i<len; i++){
            idString = "0" + idString;
        }
        return idString;
    }

    /**
     * Crée un identifiant unique pour un employé.
     *
     * @param len la longueur de l'identifiant unique de l'employé. Type int
     * @param employes la liste des employés. Type ArrayList<Employe>
     * @return l'identifiant unique de l'employé de type String
     */
    public static String idCreateEmp(int len, ArrayList<Employe> employes){

        ArrayList<Integer> idList = new ArrayList<>();
        int id = -1;
        for (Employe employe : employes){
            idList.add(Integer.parseInt(employe.getId()));
        }
        for (int i = 1; i<idList.size(); i++){
            if (idList.contains(i)){ continue; }
            else {id = i;}
        }
        if (id == -1){ id = employes.size()+1;}
        String idString = String.valueOf(id);
        for (int i = idString.length(); i<len; i++){
            idString = "0" + idString;
        }
        return idString;
    }

    /**
     * Crée un identifiant unique pour un contrat.
     *
     * @param len la longueur de l'identifiant unique du contrat. Type int
     * @param contrats la liste des contrats. Type ArrayList<Contrat>
     * @return l'identifiant unique du contrat de type String
     */
    public static String idCreateCont(int len, ArrayList<Contrat> contrats){
        ArrayList<Integer> idList = new ArrayList<>();
        int id = -1;
        for (Contrat contrat : contrats){
            idList.add(Integer.parseInt(contrat.getIdContrat()));
        }
        for (int i = 1; i<idList.size(); i++){
            if (idList.contains(i)){ continue; }
            else {id = i;}
        }
        if (id == -1){ id = contrats.size()+1;}
        String idString = String.valueOf(id);
        for (int i = idString.length(); i<len; i++){
            idString = "0" + idString;
        }
        return idString;
    }

    /**
     * Génère un identifiant unique pour une prestation en fonction de la longueur spécifiée et de la liste de prestations existantes.
     *
     * @param len la longueur de l'identifiant généré
     * @param prestations la liste des prestations existantes
     * @return l'identifiant unique généré de la Prestation de type String
     */
    public static String idCreatePrestation(int len, ArrayList<Prestation> prestations){
        ArrayList<Integer> idList = new ArrayList<>();
        int id = -1;
        for (Prestation prestation : prestations){
            idList.add(Integer.parseInt(prestation.getId()));
        }
        for (int i = 1; i<idList.size(); i++){
            if (idList.contains(i)){ continue; }
            else {id = i;}
        }
        if (id == -1){ id = prestations.size()+1;}
        String idString = String.valueOf(id);
        for (int i = idString.length(); i<len; i++){
            idString = "0" + idString;
        }
        return idString;
    }

}
