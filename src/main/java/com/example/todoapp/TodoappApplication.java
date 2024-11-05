package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Die Hauptanwendungsklasse für die Todo-App.
 * Diese Klasse startet die Spring Boot-Anwendung.
 */
@SpringBootApplication
public class TodoappApplication {

    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args Die Argumente der Kommandozeile.
     */
    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }
}
