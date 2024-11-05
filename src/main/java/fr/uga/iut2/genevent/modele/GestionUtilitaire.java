package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

/**
 * Classe GestionUtilitaire qui permet de gérer les employés.
 */
public class GestionUtilitaire {

    // Liste

    /**
     * Méthode qui permet de convertir une liste de String contenant les id des employés en une liste d'employé.
     *
     * @param gestion la gestion des employés
     * @param values la liste de String contenant les id des employés
     * @return la liste d'employé
     */
    public static ArrayList<Employe> listeMetierImport(Gestion gestion, String values){
        ArrayList<Employe> employes = new ArrayList<Employe>();
        for (int i = 0; i < values.split("⯉").length; i++) {
            Employe employe = gestion.rechercheEmployeParId(values.split("⯉")[i]);
            employes.add(employe);
        }
        return employes;
    }

    /**
     * Retourne une liste contenant les identifiants des contrats présents dans la liste donnée.
     *
     * @param contrats la liste des contrats
     * @return une liste contenant les identifiants des contrats
     */
    public static ArrayList<String> listeContratId(ArrayList<Contrat> contrats){
        ArrayList<String> contratsId = new ArrayList<String>();
        for (int i = 0; i < contrats.size(); i++){
            contratsId.add(contrats.get(i).getIdContrat());
        }
        return contratsId;
    }

    // Recherche

    /**
     * Recherche un contrat par son identifiant dans une liste de contrats.
     * 
     * @param id l'identifiant du contrat à rechercher
     * @param contrats la liste de contrats dans laquelle effectuer la recherche
     * @return le contrat correspondant à l'identifiant spécifié, ou null si aucun contrat n'est trouvé
     */
    public static Contrat rechercheContratId(String id, ArrayList<Contrat> contrats){
        Contrat contrat = null;
        for (int i = 0; i < contrats.size(); i++){
            if (contrats.get(i).getIdContrat().equals(id)){
                contrat = contrats.get(i);
            }
        }
        return contrat;
    }

    /**
        * Recherche un employé par son identifiant dans une liste d'employés.
        * 
        * @param id l'identifiant de l'employé à rechercher
        * @param employes la liste d'employés dans laquelle effectuer la recherche
        * @return l'employé correspondant à l'identifiant spécifié, ou null si aucun employé ne correspond
        */
    public static Employe rechercheEmployeParId (String id, ArrayList<Employe> employes){
        Employe employe = null;
        for (int i = 0; i < employes.size(); i++){
            if (employes.get(i).getId().equals(id)){
                employe = employes.get(i);
            }
        }
        return employe;
    }

    /**
     * Recherche une prestation par son identifiant dans une liste de prestations.
     * 
     * @param id l'identifiant de la prestation à rechercher
     * @param prestations la liste de prestations dans laquelle effectuer la recherche
     * @return la prestation correspondant à l'identifiant spécifié, ou null si aucune prestation n'est trouvée
     */
    public static Prestation recherchePrestationParId (String id, ArrayList<Prestation> prestations){
        Prestation presation = null;
        for (int i = 0; i < prestations.size(); i++){
            if (prestations.get(i).getId().equals(id)){
                presation = prestations.get(i);
            }
        }
        return presation;
    }

    //Affiche

    /**
     * Renvoie une liste d'employés ayant une fonction spécifique.
     *
     * @param fonction la fonction recherchée
     * @param employes la liste des employés à parcourir
     * @return une liste de chaînes de caractères contenant les informations des employés correspondants à la fonction recherchée
     */
    public static ArrayList<String> afficheEmployerByFonction(String fonction , ArrayList<Employe> employes){
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < employes.size(); i++){
            if (employes.get(i).getContratTermine() == false && employes.get(i).getFonction().contains(fonction)){
                   String employe = employes.get(i).getId() + " " + employes.get(i).getNom() + " " + employes.get(i).getPrenom() ;
                   res.add(employe);
            }
        }
        return res;
    }

    /**
     * Affiche les plaques d'immatriculation des véhicules en état.
     * 
     * @param vehicules la liste des véhicules à afficher
     * @return une liste contenant les plaques d'immatriculation des véhicules en état
     */
    public static ArrayList<String> afficheVehicule(ArrayList<Vehicule> vehicules){
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < vehicules.size(); i++){
            if (vehicules.get(i).getEnEtat()){
                String vehicule = vehicules.get(i).getPlaqueImmat();
                res.add(vehicule);
            }
        }
        return res;
    }

    /**
     * Affiche les informations des prestations dans une liste.
     * 
     * @param prestations la liste des prestations à afficher
     * @return une liste de chaînes de caractères contenant les informations des prestations
     */
    public static ArrayList<String> affichePrestation(ArrayList<Prestation> prestations){
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < prestations.size(); i++){
            String prestation =  prestations.get(i).getId() + " " + prestations.get(i).getNom() + " " + prestations.get(i).getType() + " " + String.format("%.2f",prestations.get(i).getPrixTTC()) + "€";
            res.add(prestation);
        }
        return res;
    }







}