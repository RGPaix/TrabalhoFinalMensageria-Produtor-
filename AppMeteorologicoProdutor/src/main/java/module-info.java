open module com.appmeteorologicoprodutor {
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
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.boot;
    requires spring.rabbit;
    requires spring.amqp;
    requires spring.beans;
    requires org.slf4j;

    requires spring.core;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.web;
    requires jakarta.validation;
    requires org.hibernate.orm.core;

    exports com.appmeteorologicoprodutor;
    exports com.appmeteorologicoprodutor.Models;
}