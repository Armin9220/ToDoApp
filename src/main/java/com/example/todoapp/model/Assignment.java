package com.example.todoapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Modellklasse, die eine Zuweisung zwischen einem Benutzer und einer Aufgabe darstellt.
 * Eine Zuweisung enthält Informationen über den Benutzer, die Aufgabe und den Status der Zuweisung.
 */
@Entity
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;

    private boolean completed;

    // Konstruktoren, Getter, Setter
}
