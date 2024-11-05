package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.CalendrierControleur;
import fr.uga.iut2.genevent.modele.Gestion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ACDeces extends Application {


    private static final Logger LOGGER = Logger.getLogger(ACDeces.class.getName());

    private static final LogManager logManager = LogManager.getLogManager();

    static{
        try {
            logManager.readConfiguration( new FileInputStream("conf/logging.properties") );
        } catch ( IOException exception ) {
            LOGGER.log( Level.SEVERE, "Cannot read configuration file",
                    exception );
        }
    }
// getter gestion
    private static final Gestion gestion = new Gestion();

    public static Gestion getGestion() {
        return gestion;
    }

// start
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/page-calendrier.fxml"));
        Scene mainScene = new Scene(mainViewLoader.load());
        // import au lancement
        getGestion().importAll();

        // title
        primaryStage.setTitle("ACDeces");
        // size
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        // récupération du controlleur.
        CalendrierControleur controller = mainViewLoader.getController();
        //Calendrier
        controller.miseEnFormeCalendrier();
    }

    public static void main(String[] args) {
        launch();
        getGestion().exportAll();
    }

}
