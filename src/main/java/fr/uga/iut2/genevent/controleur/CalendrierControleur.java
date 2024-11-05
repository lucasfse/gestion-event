package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.ACDeces;
import fr.uga.iut2.genevent.modele.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Contrôleur de la vue du calendrier. <br>
 * Cette vue permet d'afficher un calendrier. <br>
 * Le calendrier affiche les rendez-vous et les obsèques de la semaine. <br>
 * L'utilisateur peut ajouter un rendez-vous ou des obsèques et peut naviguer entre les semaines du calendrier. <br>
 */
public class CalendrierControleur extends MenuControleur{

    @FXML
    private Button ajoutRdvButton, ajoutObsequesButton, semainePrecedenteButton, semaineSuivanteButton;

    @FXML
    private Label numJourLabel1, numJourLabel2, numJourLabel3, numJourLabel4, numJourLabel5, numJourLabel6, numJourLabel7, intervalleDatesLabel;

    @FXML
    private VBox vBoxLundi, vBoxMardi, vBoxMercredi, vBoxJeudi, vBoxVendredi, vBoxSamedi, vBoxDimanche;
    @FXML
    private ArrayList<VBox> vBoxJours;

    private LocalDate date = LocalDate.now();
    private LocalDate dateFinSemaine;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * initialiseur par défaut de la classe CalendrierControleur. <br>
     * Cette méthode initialise la liste des jours de la semaine. <br>
     * Les jours de la semaine sont stockés dans une liste de ScrollPane lui même dans un vbox. <br>
     */
    @FXML
    public void initialize() {
        vBoxJours = new ArrayList<>(Arrays.asList(vBoxLundi, vBoxMardi, vBoxMercredi, vBoxJeudi, vBoxVendredi, vBoxSamedi, vBoxDimanche));
    }

    /**
     * Méthode appelée lorsqu'un utilisateur clique sur le bouton "ajoutRdvButton".
     * Cette méthode charge une nouvelle fenêtre contenant un formulaire de rendez-vous.
     * La fenêtre est créée à partir du fichier FXML "obseques-formulaire-view.fxml".
     * La fenêtre est affichée et ne peut pas être redimensionnée.
     *
     * @param event L'événement de clic sur le bouton "ajoutRdvButton".
     * @throws IOException Si une erreur se produit lors du chargement.
     */
    @FXML
    public void ajoutRdvButtonClicked(ActionEvent event) throws IOException {
       try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/rdv-formulaire-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Rendez-vous ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lorsqu'un utilisateur clique sur le bouton "ajoutObsequesButton".
     * Cette méthode charge une nouvelle fenêtre contenant un formulaire pour ajouter des obsèques.
     * La fenêtre est créée à partir du fichier FXML "obseques-formulaire-view.fxml".
     * La fenêtre est affichée et ne peut pas être redimensionnée.
     *
     * @param event L'événement de clic sur le bouton "ajoutObsequesButton".
     * @throws IOException Si une erreur se produit lors du chargement.
     */
    @FXML
    public void ajoutObsequesButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/obseques-formulaire-view.fxml"));
            Stage fenetre= new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            fenetre.setTitle("Obsèque ajout");
            fenetre.setScene(scene);
            fenetre.setResizable(false);
            fenetre.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met en forme le calendrier en affichant les dates correspondantes dans les labels.
     * Les dates de la semaine sont affichées à partir de la date où se trouve le calendrier.
     * Par défaut, la date du calendrier est la date actuelle.
     */
    public void miseEnFormeCalendrier() {
        // Ajuster la date au début de la semaine (lundi)

        // On cherche le lundi de la semaine
        while (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            date = date.minusDays(1);
        }

        // On affiche les dates des jours de la semaine
        numJourLabel1.setText(formatter.format(date));
        numJourLabel2.setText(formatter.format(date.plusDays(1)));
        numJourLabel3.setText(formatter.format(date.plusDays(2)));
        numJourLabel4.setText(formatter.format(date.plusDays(3)));
        numJourLabel5.setText(formatter.format(date.plusDays(4)));
        numJourLabel6.setText(formatter.format(date.plusDays(5)));
        numJourLabel7.setText(formatter.format(date.plusDays(6)));
        dateFinSemaine = date.plusDays(6);

        intervalleDatesLabel.setText(date.getDayOfMonth() + " au " + formatter.format(date.plusDays(6)));
        affichageEvenements();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "semainePrecedenteButton".
     * Cette méthode permet d'afficher la semaine précédente du calendrier.
     * Cette méthode met à jour les labels des numéros de jour en soustrayant 7 jours à la date du calendrier.
     * Elle met également à jour la date et le label de l'intervalle de dates.
     *
     * @param event L'événement de clic sur le bouton "semainePrecedenteButton".
     */
    @FXML
    public void semainePrecedenteButtonClicked(ActionEvent event) {
        date = date.minusWeeks(1);
        miseEnFormeCalendrier();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "moisPrecedentButton".
     * Cette méthode permet d'afficher le mois précédent du calendrier.
     * Cette méthode met à jour les labels affichant les numéros de jour en soustrayant 1 mois à la date du calendrier.
     * Elle met également à jour le label affichant l'intervalle de dates de la semaine.
     *
     * @param event L'événement de clic sur le bouton "moisPrecedentButton".
     */
    @FXML
    public void moisPrecedentButtonClicked(ActionEvent event) {
        date = date.minusMonths(1);
        miseEnFormeCalendrier();
    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "moisSuivantButton".
     * Cette méthode permet d'afficher le mois suivant du calendrier.
     * Cette méthode met à jour les labels affichant les numéros de jour en ajoutant 1 mois à la date du calendrier.
     * Elle met également à jour le label affichant l'intervalle de dates de la semaine.
     *
     * @param event L'événement de clic sur le bouton "moisSuivantButton".
     */
    @FXML
    public void moisSuivantButtonClicked(ActionEvent event) {
        date = date.plusMonths(1);
        miseEnFormeCalendrier();
    }



    /**
     * Méthode appelée lorsqu'on clique sur le bouton "semaineSuivanteButton".
     * Cette méthode permet d'afficher la semaine suivante du calendrier.
     * Cette méthode met à jour les labels affichant les numéros de jour en ajoutant 7 jours à la date du calendrier.
     * Elle met également à jour le label affichant l'intervalle de dates de la semaine.
     *
     * @param event L'événement de clic sur le bouton "semaineSuivanteButton".
     */
    @FXML
    public void semaineSuivanteButtonClicked(ActionEvent event) {
        date = date.plusWeeks(1);
        miseEnFormeCalendrier();
    }

    /**
     * Cette méthode affiche dans le calendrier les informations des événements de la semaine.
     * <ul>
     *     <li>Les rendez-vous de la semaine avec nom de l'événement date</li>
     *     <li>Les obseque de la semaine avec nom de l'événement date</li>
     * </ul>
     */
    @FXML
    public void affichageEvenements() {
        clearVBox();
        ArrayList<Evenement> calendriers = ACDeces.getGestion().getCalendrier().getCalendrier();
        BackgroundFill backgroundFillRdvClient = new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background backgroundRdvClient = new Background(backgroundFillRdvClient);
        BackgroundFill backgroundFillObseques = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background backgroundObseques = new Background(backgroundFillObseques);

        for (int i = 0; i<calendriers.size(); i++) {
            Evenement evenement = calendriers.get(i);
            if (!evenement.getDate().isBefore(date) && !evenement.getDate().isAfter(dateFinSemaine)) {
                int difference = (int) ChronoUnit.DAYS.between(date, evenement.getDate());
                Label horaires = new Label(evenement.getHeureDebut() + " - " + evenement.getHeureFin());
                Label nom = new Label(evenement.getNom());
                Label type = new Label(evenement.getClass().getSimpleName());
                VBox event = new VBox(horaires, nom, type);
                vBoxJours.get(difference).getChildren().add(event);
                event.setPadding(new Insets(10));
                event.setAlignment(Pos.CENTER);
                if (evenement.getClass().getSimpleName().equals("RdvClient")) {
                    event.setBackground(backgroundRdvClient);
                } else {
                    event.setBackground(backgroundObseques);
                }
                setVBoxMargin(vBoxJours.get(difference));
            }
        }
    }

    /**
     * Cette méthode permet de vider les VBox des jours de la semaine.<br>
     * elle est utilisé pour l'actualisation de la page
     */
    private void clearVBox() {
        for (VBox vBox : vBoxJours) {
            vBox.getChildren().clear();
        }
    }

    /**
     * Cette méthode permet de définir les marges des éléments d'un VBox.
     *
     * @param vBox Le VBox dont les éléments doivent être espacés.
     */
    private void setVBoxMargin(VBox vBox) {
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            VBox.setMargin(vBox.getChildren().get(i), new Insets(2.5));
        }
    }

}
