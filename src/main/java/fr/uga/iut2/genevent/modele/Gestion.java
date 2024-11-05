package fr.uga.iut2.genevent.modele;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import java.util.logging.Level;
import java.util.logging.Logger;


// Constructeur

/**
 * La Classe Gestion permet de gérer différents groupe de classe. <br>
 * <ul>
 *     <li>employer</li>
 *     <li>contrat</li>
 *     <li>véhicule</li>
 *     <li>prestation</li>
 *     <li>calendrier</li>
 * </ul>
 * Exporter leur attrbut dans un fichier csv et les importer, les recupérer et les afficher pour maitrisé la coérrence des données. <br>
 * Par manque de compréension de la sérialisation et de la désérialisation, nous avons choisi de les exporter et les importer dans des fichiers csv. De plus cette classe avait déjà été prévus dans le développement (voir diagramme de classe). <br>
 * A retenire quil n'est pas parfait mais emplement fonctionnel.
 */
public class Gestion {


    private static Logger LOGGER = Logger.getLogger(Gestion.class.getName());

    private ArrayList<Employe> employes = new ArrayList<>();
    private Calendrier calendrier = new Calendrier();
    private ArrayList<Contrat> contrats = new ArrayList<>();
    private ArrayList<Vehicule> vehicules = new ArrayList<>();
    private ArrayList<Prestation> prestations = new ArrayList<>();

    // Constructeur

    /**
     * Constructeur de la classe Gestion avec des attribut vide.
     */
    public Gestion() {
        LOGGER.log(Level.WARNING, "Gestion a été créé.");

    }

    /**
     * Constructeur de la classe Gestion avec des attributs initialisés par les paramètre.
     * @param employes Type Arraylist<Employe>
     * @param calendrier Type Calendrier
     * @param contrats Type Arraylist<Contrat>
     * @param vehicules Type Arraylist<Vehicule>
     * @param prestations Type Arraylist<Prestation>
     */
    public Gestion(ArrayList<Employe> employes, Calendrier calendrier, ArrayList<Contrat> contrats, ArrayList<Vehicule> vehicules, ArrayList<Prestation> prestations) {
        this.employes = employes;
        this.calendrier = calendrier;
        this.contrats = contrats;
        this.vehicules = vehicules;
        this.prestations = prestations;

        LOGGER.log(Level.WARNING, "Gestion a été créé.");

    }

    // Getters
    /**
     * Renvoie la liste des employés.
     * @return employés de type Arraylist<Employe>
     */
    public ArrayList<Employe> getEmployes() {
        return employes;
    }

    /**
     * Renvoie le calendrier.
     * @return le calendrier de type Calendrier
     */
    public Calendrier getCalendrier() {
        return calendrier;
    }

    /**
     * Renvoie la liste des contrats.
     * @return contrat de type Arraylist<Contrat>
     */
    public ArrayList<Contrat> getContrats() {
        return contrats;
    }


    /**
     * Renvoie un contrat possédant l'id recherché ou null s'il n'existe pas.
     * @param contrat de type Contrat
     * @return contrat de type Contrat
     */
    public Contrat getContrat(Contrat contrat) {
        Contrat contrat1 = null;
        for (Contrat c : contrats) {
            if (c.getIdContrat().equals(contrat.getIdContrat())) {
                contrat1 = c;
            }
        }
        return contrat1;
    }


    /**
     * Renvoie la liste des contrats archivés.
     * @return contrat archivés de type Arraylist<Contrat>
     */
    public ArrayList<Contrat> getContratsArchive() {
        ArrayList<Contrat> contratsArchive = new ArrayList<>();
        for (Contrat contrat : contrats) {
            if (contrat.getContratArchive()) { // TODO : changer pour isContratArchive
                contratsArchive.add(contrat);
            }
        }
        return contratsArchive;
    }

    /**
     * Renvoie la liste des contrats non archivés.
     * @return contrats non archivés de type Arraylist<Contrat>
     */
    public ArrayList<Contrat> getContratsNonArchive() {
        ArrayList<Contrat> contratsNonArchive = new ArrayList<>();
        for (Contrat contrat : contrats) {
            if (!contrat.getContratArchive()) { // TODO : changer pour isContratArchive
                contratsNonArchive.add(contrat);
            }
        }
        return contratsNonArchive;
    }

    /**
     * Renvoie la liste des véhicules.
     * @return véhicules de type Arraylist<Vehicule>
     */
    public ArrayList<Vehicule> getVehicules() {
        return vehicules;
    }

    /**
     * Renvoie un véhicule possédant la plaqueImmat recherché ou null s'il n'xiste pas.
     * @param plaqueImmat de type String
     * @return vehicule de type Vehicule
     */
    public Vehicule getVehicule(String plaqueImmat) {
        Vehicule vehicule = null;
        for (Vehicule v : vehicules) {
            if (v.getPlaqueImmat().equals(plaqueImmat)) {
                vehicule = v;
            }
        }
        return vehicule;
    }


    /**
     * Renvoie la liste des prestations.
     * @return prestations de type Arraylist<Prestation>
     */
    public ArrayList<Prestation> getPrestations() {
        return prestations;
    }


    /**
     * Renvoie la prestation recherchée.
     * @param prestation de type Prestation
     * @return prestation de type Prestation
     */
    public Prestation getPrestation(Prestation prestation) {
        Prestation prestation1 = null;
        for (Prestation p : prestations) {
            if (p.getId().equals(prestation.getId())) {
                prestation1 = p;
            }
        }
        return prestation1;
    }


    // Setters

    /**
     * Modifie la liste des employés.
     * @param employes Type Arraylist<Employe>
     */
    public void setEmployes(ArrayList<Employe> employes) {
        this.employes = employes;
    }

    /**
     * Modifie le calendrier.
     * @param calendrier Type Calendrier
     */
    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    /**
     * Modifie la liste des contrats.
     * @param contrats Type Arraylist<Contrat>
     */
    public void setContrats(ArrayList<Contrat> contrats) {
        this.contrats = contrats;
    }

    /**
     * Modifie la liste des véhicules.
     * @param vehicules Type Arraylist<Vehicule>
     */
    public void setVehicules(ArrayList<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    /**
     * Modifie la liste des prestations.
     * @param prestations Type Arraylist<Prestation>
     */
    public void setPrestations(ArrayList<Prestation> prestations) {
        this.prestations = prestations;
    }


    // Adders

    /**
     * Ajoute un employé à la liste des employés.
     * @param employe Type Employe
     */
    public void addEmploye(Employe employe) {
        employes.add(employe);
    }

    /**
     * Ajoute un contrat à la liste des contrats.
     * @param contrat Type Contrat
     */
    public void addContrat(Contrat contrat) {
        contrats.add(contrat);
    }

    /**
     * Ajoute un véhicule à la liste des véhicules.
     * @param vehicule Type Vehicule
     */
    public void addVehicule(Vehicule vehicule) {
        vehicules.add(vehicule);
    }

    /**
     * Ajoute une prestation à la liste des prestations.
     * @param prestation Type Prestation
     */
    public void addPrestation(Prestation prestation) {
        prestations.add(prestation);
    }

    /**
     * Ajoute un événement au calendrier.
     * @param evenement Type Evenement
     */
    public void addEvenement(Evenement evenement) {
        calendrier.addEvenement(evenement);
    }


    // Removers
    /**
     * Supprime un employé de la liste des employés.
     * @param employe Type Employe
     */
    public void removeEmploye(Employe employe) {
        employes.remove(employe);
    }

    /**
     * Supprime un contrat de la liste des contrats.
     * @param contrat Type Contrat
     */
    public void removeContrat(Contrat contrat) {
        contrats.remove(contrat);
    }

    /**
     * Supprime un véhicule de la liste des véhicules.
     * @param vehicule Type Vehicule
     */
    public void removeVehicule(Vehicule vehicule) {
        vehicules.remove(vehicule);
    }

    /**
     * Supprime une prestation de la liste des prestations.
     * @param prestation Type Prestation
     */
    public void removePrestation(Prestation prestation) {
        prestations.remove(prestation);
    }

    // Autres méthodes :

    // Méthode déplacée de la classe Employée
    private boolean existeEmployer(String nom, String prenom) {
        for (Employe employe : employes) {
            if (employe.getNom().equals(nom) && employe.getPrenom().equals(prenom)) {
                return true;
            }
        }
        return false;
    }

    // Recherche

    /**
     * Recherche un employé par son id.
     * @param idEmploye Type String
     * @return employe de type Employe
     */
    public Employe rechercheEmployeParId(String idEmploye) {
        for (Employe employe : employes) {
            if (employe.getId().equals(idEmploye)) {
                return employe;
            }
        }
        return null;
    }



    // Import

    /**
     * Importe les employés depuis un fichier csv.
     * Le fichier doit être situé dans le dossier "sauvegardes" et doit être nommé "employes.csv". <br>
     * Le fichier doit être formaté de la manière suivante :
     * id⯒nom⯒prenom⯒fonction⯒numeroTel⯒dateDebutContrat⯒dateFinContrat⯒nbHeuresSemaine⯒abscences⯒contratTermine
     * Le fonctionnement est le même pour les autres méthodes d'import, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * En utilisant c(est caractère nous récupérons les données et les ajoutons dans les listes correspondantes dans des String[] puis leur afféctons le bon type de données ou recherchons l'objet en question dans le cas des ID.
     */
    public void importEmployes() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("sauvegardes/employes.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("⯒");

                // récupérer l'id
                String id = values[0];

                // récupérer le nom
                String nom = values[1];

                // récupérer le prénom
                String prenom = values[2];

                // récupérer les fonctions
                ArrayList<String> fonction = new ArrayList<>(Arrays.asList(values[3].split("⯉")));

                // récupérer le numéro de téléphone
                String numeroTel = values[4];

                // récupérer la date de début de contrat
                LocalDate dateDebutContrat = LocalDate.parse(values[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                // récupérer la date de fin de contrat
                LocalDate dateFinContrat = LocalDate.parse(values[6], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                // récupérer le nombre d'heures par semaine
                Double nbHeuresSemaine = Double.parseDouble(values[7]);

                // récupérer les absences
                ArrayList<LocalDate> abscences = new ArrayList<>();
                if (!values[8].equals("")){
                    for (String s : values[8].split("⯉")) {
                        LocalDate date = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        abscences.add(date);
                    }
                }

                // récupérer si le contrat est terminé
                Boolean contratTermine = Boolean.parseBoolean(values[9]);

                // créer l'employé
                Employe employe = new Employe(id, nom, prenom, fonction, numeroTel, dateDebutContrat, dateFinContrat, nbHeuresSemaine, abscences, contratTermine);
                employes.add(employe);

                LOGGER.log(Level.WARNING, "Un Employées ont été importés");
            }br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Importe le calendrier depuis un fichier csv.
     * Le fichier doit être situé dans le dossier "sauvegardes" et doit être nommé "calendrier.csv". <br>
     * Le fichier doit être formaté de la manière suivante :
     * type⯒id⯒nom⯒lieu⯒date⯒heureDebut⯒heureFin⯒details⯒[nomClient⯒prenomClient⯒numeroTelClient⯒idChargeAcceuil] ou [contratId⯒plaqueImmat⯒idMaitreCeremonie⯒idAssistantFunaire⯒marbriers⯒porteurs⯒thanatopracteurs]
     * Le fonctionnement est le même pour les autres méthodes d'import, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * En utilisant c'est caractère nous récupérons les données et les ajoutons dans les listes correspondantes dans des String[] puis leur afféctons le bon type de données ou recherchons l'objet en question dans le cas des ID.
     */
    public void importCalendrier() {
        LOGGER.log(Level.INFO, "Le Calendrier as été importé");

        try {
            BufferedReader br = new BufferedReader(new FileReader("sauvegardes/calendrier.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("⯒");

                // récupérer le type d'événement
                String type = values[0];

                String id = values[1];

                // récupérer le nom
                String nom = values[2];

                // récupérer le lieu
                String lieu = values[3];

                // récupérer la date
                LocalDate date = LocalDate.parse(values[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                // récupérer l'heure de début
                LocalTime heureDebut = LocalTime.parse(values[5], DateTimeFormatter.ofPattern("HH:mm"));

                // récupérer l'heure de fin
                LocalTime heureFin = LocalTime.parse(values[6], DateTimeFormatter.ofPattern("HH:mm"));

                // récupérer les détails
                String details = values[7];

                if (Objects.equals(type, "RdvClient")) {
                    String nomClient = values[8];
                    String prenomClient = values[9];
                    String numeroTelClient = values[10];
                    String idChargeAcceuil = values[11];

                    calendrier.addEvenement(new RdvClient(id, nom, lieu, heureDebut, heureFin, date, nomClient, prenomClient, numeroTelClient, rechercheEmployeParId(idChargeAcceuil), details));
                } else if (Objects.equals(type, "Obseque")) {
                    String contratId = values[8];
                    Contrat contrat = null;
                    for (int i = 0; i < getContrats().size(); i++) {
                        if (getContrats().get(i).getIdContrat().equals(contratId)) {
                            contrat = getContrats().get(i);
                        }
                    }

                    Vehicule vehicule = getVehicule(values[9]);
                    Employe maitreCeremonie = rechercheEmployeParId(values[10]);
                    Employe assistantFunaire = rechercheEmployeParId(values[11]);
                    ArrayList<Employe> marbrier = GestionUtilitaire.listeMetierImport(this, values[12]);
                    ArrayList<Employe> porteur = GestionUtilitaire.listeMetierImport(this, values[13]);
                    ArrayList<Employe> thanatopracteur = GestionUtilitaire.listeMetierImport(this, values[14]);
                    calendrier.addEvenement(new Obseque(contrat, id, nom, lieu, heureDebut, heureFin, date, details, assistantFunaire, maitreCeremonie, marbrier, porteur, thanatopracteur, vehicule));
                }
            }
        
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Importe les contrats depuis un fichier csv.
     * Le fichier doit être situé dans le dossier "sauvegardes" et doit être nommé "contrats.csv". <br>
     * Le fichier doit être formaté de la manière suivante :
     * id⯒nomClient⯒prenomClient⯒adresseClient⯒codePostal⯒villeClient⯒numeroClient⯒montantTotal⯒montantRegle⯒reglementTerminer⯒notes⯒contratSigne⯒contratExecute⯒contratArchive⯒prestations
     * Le fonctionnement est le même pour les autres méthodes d'import, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * En utilisant c'est caractère nous récupérons les données et les ajoutons dans les listes correspondantes dans des String[] puis leur afféctons le bon type de données ou recherchons l'objet en question dans le cas des ID.
     */
    public void importContrats() {
        LOGGER.log(Level.INFO, "Les Contrats ont été importés");

        try {
            BufferedReader br = new BufferedReader(new FileReader("sauvegardes/contrats.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("⯒");

                // récupérer l'id du contrat
                String idContrat = values[0];

                // récupérer le nom du client
                String nomClient = values[1];

                // récupérer le prénom du client
                String prenomClient = values[2];

                // récupérer l'adresse du client
                String adresseClient = values[3];

                // récupérer le code postal du client
                String codePostal = values[4];

                // récupérer la ville du client
                String villeClient = values[5];

                // récupérer le numéro du client
                String numeroClient = values[6];

                // récupérer le montant total
                Double montantTotal = Double.parseDouble(values[7]);

                // récupérer le montant réglé
                Double montantRegle = Double.parseDouble(values[8]);

                // récupérer si le règlement est terminé
                Boolean reglementTerminer = Boolean.parseBoolean(values[9]);

                // récupérer les notes
                String notes = values[10];

                // récupérer si le contrat est signé
                Boolean contratSigne = Boolean.parseBoolean(values[11]);

                // récupérer si le contrat est exécuté
                Boolean contratExecute = Boolean.parseBoolean(values[12]);

                // récupérer si le contrat est archivé
                Boolean contratArchive = Boolean.parseBoolean(values[13]);

                ArrayList<Prestation> prestations = new ArrayList<>();
                for (String s : values[14].split("⯉")) {
                    for (int i = 0; i < getPrestations().size(); i++) {
                        if (getPrestations().get(i).getId().equals(s)) {
                            prestations.add(getPrestations().get(i));
                        }
                    }
                }

                // créer le contrat
                Contrat contrat = new Contrat(idContrat, nomClient, prenomClient, adresseClient, codePostal, villeClient, numeroClient, montantTotal, montantRegle, reglementTerminer, notes, contratSigne, contratExecute, contratArchive, prestations);
                contrats.add(contrat);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Importe les prestations depuis un fichier csv.
     * Le fichier doit être situé dans le dossier "sauvegardes" et doit être nommé "prestations.csv". <br>
     * Le fichier doit être formaté de la manière suivante :
     * id⯒nom⯒type⯒prixHT⯒prixTTC
     * Le fonctionnement est le même pour les autres méthodes d'import, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * En utilisant c'est caractère nous récupérons les données et les ajoutons dans les listes correspondantes dans des String[] puis leur afféctons le bon type de données ou recherchons l'objet en question dans le cas des ID.
     */
    public void importPrestations() {
        LOGGER.log(Level.INFO, "Les Prestations ont été importés");

        try {
            BufferedReader br = new BufferedReader(new FileReader("sauvegardes/prestations.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("⯒");

                // récupérer l'id
                String id = values[0];

                // récupérer le nom
                String nom = values[1];

                // récupérer le type
                String type = values[2];

                // récupérer le prix HT
                Double prixHT = Double.parseDouble(values[3]);

                // récupérer le prix TTC
                Double prixTTC = Double.parseDouble(values[4]);

                // créer la prestation
                Prestation prestation = new Prestation(id, prixTTC, prixHT, type, nom);
                prestations.add(prestation);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Importe les véhicules depuis un fichier csv.
     * Le fichier doit être situé dans le dossier "sauvegardes" et doit être nommé "vehicules.csv". <br>
     * Le fichier doit être formaté de la manière suivante :
     * plaqueImmat⯒enEtat⯒obseques
     * Le fonctionnement est le même pour les autres méthodes d'import, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * En utilisant c'est caractère nous récupérons les données et les ajoutons dans les listes correspondantes dans des String[] puis leur afféctons le bon type de données ou recherchons l'objet en question dans le cas des ID.
     */
    public void importVehicules() {
        LOGGER.log(Level.INFO, "Les Vehicules ont été importés");

        try {
            BufferedReader br = new BufferedReader(new FileReader("sauvegardes/vehicules.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("⯒");

                // récupérer la plaque d'immatriculation
                String plaqueImmat = values[0];

                // récupérer si le véhicule est en état
                Boolean enEtat = Boolean.parseBoolean(values[1]);

                ArrayList<Obseque> obseque = new ArrayList<>();
                if (values.length > 2){
                    for (String s : values[2].split("⯉")) {
                        for (int i = 0; i < getCalendrier().getCalendrier().size(); i++) {
                            if (getCalendrier().getCalendrier().get(i).getIdEvenement().equals(s)) {
                                obseque.add((Obseque) getCalendrier().getCalendrier().get(i));
                            }
                        }
                    }
                }

                // créer le véhicule
                Vehicule vehicule = new Vehicule(plaqueImmat, enEtat, obseque);
                vehicules.add(vehicule);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Importe tous les éléments depuis les fichiers csv.
     */
    public void importAll() {
        LOGGER.log(Level.WARNING, "Toutes les Classes ont été importés");

        importVehicules();
        importEmployes();
        importPrestations();
        importContrats();
        importCalendrier();
    }

    // Export

    /**
     * Exporte les employés dans un fichier csv.
     * Le fichier est situé dans le dossier "sauvegardes" et est nommé "employes.csv". <br>
     * Le fichier est formaté de la manière suivante :
     * id⯒nom⯒prenom⯒fonction⯒numeroTel⯒dateDebutContrat⯒dateFinContrat⯒nbHeuresSemaine⯒abscences⯒contratTermine
     * Le fonctionnement est le même pour les autres méthodes d'export, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * Pour cela nous exportons chaqu'une des données dans des String puis ajoutons ces String dans un fichier csv avec pour séparateur des données le caractère "⯒" et pour les listes de données "⯉".
     */
    public void exportEmployes() {
        LOGGER.log(Level.INFO, "Les Employes ont été exportés");


        File directory = new File("sauvegardes");
        if (!directory.exists()) {
            directory.mkdir();
        }


        try {
            File file = new File("sauvegardes/employes.csv");
            if(file.exists()){
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Employe employe : employes) {
                ArrayList<String> listEvenement = new ArrayList<>();
                String[] values = {
                        String.valueOf(employe.getId()),
                        employe.getNom(),
                        employe.getPrenom(),
                        String.join("⯉", employe.getFonction()),
                        employe.getNumeroTel(),
                        employe.getDateDebutContrat().format(dateFormatter),
                        employe.getDateFinContrat().format(dateFormatter),
                        String.valueOf(employe.getNbHeuresSemaine()),
                        String.join("⯉", employe.getAbscences().stream().map(date -> date.format(dateFormatter)).toArray(String[]::new)),
                        String.valueOf(employe.isContratTermine())
                };
                writer.write(String.join("⯒", values) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporte les véhicules dans un fichier csv.
     * Le fichier est situé dans le dossier "sauvegardes" et est nommé "vehicules.csv". <br>
     * Le fichier est formaté de la manière suivante :
     * plaqueImmat⯒enEtat⯒obseques
     * Le fonctionnement est le même pour les autres méthodes d'export, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * Pour cela nous exportons chaqu'une des données dans des String puis ajoutons ces String dans un fichier csv avec pour séparateur des données le caractère "⯒" et pour les listes de données "⯉".
     */
    public void exportCalendrier() {
        LOGGER.log(Level.INFO, "Le Calendrier as été exporté");

        try {
            File directory = new File("sauvegardes");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Créer le fichier s'il n'existe pas déjà
            File file = new File("sauvegardes/calendrier.csv");
            if(file.exists()){
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter("sauvegardes/calendrier.csv");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            for (Evenement evenement : calendrier.getCalendrier()) {
                String[] values = {
                        evenement.getIdEvenement(),
                        evenement.getNom(),
                        evenement.getLieu(),
                        evenement.getDate().format(dateFormatter),
                        evenement.getHeureDebut().format(timeFormatter),
                        evenement.getHeureFin().format(timeFormatter),
                        evenement.getDetails()
                };
                if (evenement instanceof RdvClient) {
                    RdvClient rdvClient = (RdvClient) evenement;
                    String[] clientValues = {
                            rdvClient.getNomClient(),
                            rdvClient.getPrenomClient(),
                            rdvClient.getNumeroTelClient(),
                            rdvClient.getChargeAcceuil().getId()
                    };
                    writer.write("RdvClient⯒" + String.join("⯒", values) + "⯒" + String.join("⯒", clientValues) + "\n");
                } else if (evenement instanceof Obseque) {
                    Obseque obseque = (Obseque) evenement;
                    String[] obsequeValue = {
                            obseque.getContrat().getIdContrat(),
                            obseque.getVehicule().getPlaqueImmat(),
                            obseque.getMaitreCeremonie().getId(),
                            obseque.getAssistantFunaire().getId(),
                            obseque.getMarbriers().stream().map(Employe::getId).collect(Collectors.joining("⯉")),
                            obseque.getPorteurs().stream().map(Employe::getId).collect(Collectors.joining("⯉")),
                            obseque.getThanatopracteur().stream().map(Employe::getId).collect(Collectors.joining("⯉"))
                    };
                    writer.write("Obseque⯒" + String.join("⯒", values) + "⯒" + String.join("⯒", obsequeValue) + "\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporte les contrats dans un fichier csv.
     * Le fichier est situé dans le dossier "sauvegardes" et est nommé "contrats.csv". <br>
     * Le fichier est formaté de la manière suivante :
     * id⯒nomClient⯒prenomClient⯒adresseClient⯒codePostal⯒villeClient⯒numeroClient⯒montantTotal⯒montantRegle⯒reglementTerminer⯒notes⯒contratSigne⯒contratExecute⯒contratArchive⯒prestations
     * Le fonctionnement est le même pour les autres méthodes d'export, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * Pour cela nous exportons chaqu'une des données dans des String puis ajoutons ces String dans un fichier csv avec pour séparateur des données le caractère "⯒" et pour les listes de données "⯉".
     */
    public void exportContrats() {
        LOGGER.log(Level.INFO, "Les Contrats ont été exportés");

        try {
            File directory = new File("sauvegardes");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Créer le fichier s'il n'existe pas déjà
            File file = new File("sauvegardes/contrats.csv");
            if(file.exists()){
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter("sauvegardes/contrats.csv");
            for (Contrat contrat : contrats) {
                ArrayList<String> prestations = new ArrayList<>();
                for (Prestation prestation : contrat.getPrestation()) {
                    prestations.add(prestation.getId());
                }
                // ajout des id des prestations
                String prestation = "";
                for (int i = 0; i < prestations.size(); i++) {
                    if (i > 0){ prestation += "⯉";}
                    prestation += prestations.get(i);
                }

                String[] values = {
                        String.valueOf(contrat.getIdContrat()),
                        contrat.getNomClient(),
                        contrat.getPrenomClient(),
                        contrat.getAdresseClient(),
                        contrat.getCodePostal(),
                        contrat.getVilleClient(),
                        contrat.getNumeroClient(),
                        String.valueOf(contrat.getMontantTotal()),
                        String.valueOf(contrat.getMontantRegle()),
                        String.valueOf(contrat.getReglementTerminer()),
                        contrat.getNotes(),
                        String.valueOf(contrat.getContratSigne()),
                        String.valueOf(contrat.getContratExecute()),
                        String.valueOf(contrat.getContratArchive()),
                        prestation
                };
                writer.write(String.join("⯒", values) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporte les prestations dans un fichier csv.
     * Le fichier est situé dans le dossier "sauvegardes" et est nommé "prestations.csv". <br>
     * Le fichier est formaté de la manière suivante :
     * id⯒nom⯒type⯒prixHT⯒prixTTC
     * Le fonctionnement est le même pour les autres méthodes d'export, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * Pour cela nous exportons chaqu'une des données dans des String puis ajoutons ces String dans un fichier csv avec pour séparateur des données le caractère "⯒" et pour les listes de données "⯉".
     */
    public void exportPrestations() {
        LOGGER.log(Level.INFO, "Les Prestations ont été exportés");

        try {
            // Créer le répertoire s'il n'existe pas déjà
            File directory = new File("sauvegardes");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Créer le fichier s'il n'existe pas déjà
            File file = new File("sauvegardes/prestations.csv");
            if(file.exists()){
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter("sauvegardes/prestations.csv");
            for (Prestation prestation : prestations) {
                ArrayList<String> contrats = new ArrayList<>();
                for (Contrat contrat : prestation.getContrats()) {
                    contrats.add(contrat.getIdContrat());
                }
                String[] values = {
                        prestation.getId(),
                        prestation.getNom(),
                        prestation.getType(),
                        String.valueOf(prestation.getPrixHT()),
                        String.valueOf(prestation.getPrixTTC()),
                        String.join("⯉", contrats)
                };
                writer.write(String.join("⯒", values) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporte les véhicules dans un fichier csv.
     * Le fichier est situé dans le dossier "sauvegardes" et est nommé "vehicules.csv". <br>
     * Le fichier est formaté de la manière suivante :
     * plaqueImmat⯒enEtat⯒obseques
     * Le fonctionnement est le même pour les autres méthodes d'export, séparation des données dans le fichier par "⯒" et des listes de donnée par "⯉". <br>
     * Pour cela nous exportons chaqu'une des données dans des String puis ajoutons ces String dans un fichier csv avec pour séparateur des données le caractère "⯒" et pour les listes de données "⯉".
     */
    public void exportVehicules() {
        LOGGER.log(Level.INFO, "Les Vehicules ont été exportés");

        try {
            // Créer le répertoire s'il n'existe pas déjà
            File directory = new File("sauvegardes");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Créer le fichier s'il n'existe pas déjà
            File file = new File("sauvegardes/vehicules.csv");
            if(file.exists()){
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter("sauvegardes/vehicules.csv");
            for (Vehicule vehicule : vehicules) {
                ArrayList<String> obseques = new ArrayList<>();
                for (Obseque obseque : vehicule.getObseques()) {
                    obseques.add(obseque.getIdEvenement());
                }
                String[] values = {
                        vehicule.getPlaqueImmat(),
                        String.valueOf(vehicule.getEnEtat()),
                        String.join("⯉", obseques)
                };
                writer.write(String.join("⯒", values) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     /**
     * Exporte tous les éléments dans les fichiers csv.
     */
    public void exportAll() {
        LOGGER.log(Level.WARNING, "Toutes les Classes ont été exportés");

        exportVehicules();
        // id = 5 pour les employes
        exportEmployes();
        // id = 6 pour les evenements
        exportCalendrier();
        // id = 8 pour les prestations
        exportPrestations();
        // id = 7 pour les contrats
        exportContrats();

    }

}
