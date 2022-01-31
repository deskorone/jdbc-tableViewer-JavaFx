module com.ui.fivelaba {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    opens com.ui.fivelaba to javafx.fxml;
    exports com.ui.fivelaba;
    opens com.logic.model to javafx.base;
    opens com.logic to javafx.base;


}