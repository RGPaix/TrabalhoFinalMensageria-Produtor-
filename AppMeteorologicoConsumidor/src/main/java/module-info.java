open module com.appmeteorologicoconsumidor {
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires spring.core;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.rabbit;

    exports com.appmeteorologicoconsumidor;
}