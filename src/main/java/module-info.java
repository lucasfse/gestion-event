module genevent {
    requires commons.validator;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;

    opens fr.uga.iut2.genevent.vue to javafx.fxml;
    opens fr.uga.iut2.genevent.controleur to javafx.fxml;  // Ajoutez cette ligne pour permettre l'accès réflexif


    exports fr.uga.iut2.genevent;
    exports fr.uga.iut2.genevent.modele;
    opens fr.uga.iut2.genevent.modele; // This line is added

}