package com.example.todoapp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Modellklasse, die einen Benutzer darstellt.
 * Ein Benutzer hat einen Benutzernamen, ein Passwort, eine E-Mail-Adresse sowie Listen von erstellten und zugewiesenen Aufgaben.
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    private String password; // Bitte dieses Passwort hashen

    private String email;

    // Eine Eins-zu-viele-Beziehung
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> createdTasks = new ArrayList<>();
    
    @ManyToMany(mappedBy = "assignees", fetch = FetchType.LAZY)
    private List<Task> assignedTasks = new ArrayList<>();
    

    // Konstruktoren
    /**
     * Standardkonstruktor zur Erstellung einer neuen Instanz von User.
     */
    public User() {}

    /**
     * Konstruktor zur Erstellung einer neuen Instanz von User mit spezifischen Werten.
     * 
     * @param username der Benutzername des Benutzers
     * @param password das Passwort des Benutzers
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password; // Bitte dieses Passwort hashen
    }

    // Getter und Setter
    /**
     * Gibt die ID des Benutzers zurück.
     * 
     * @return die ID des Benutzers
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die ID des Benutzers.
     * 
     * @param id die zu setzende ID des Benutzers
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt den Benutzernamen des Benutzers zurück.
     * 
     * @return der Benutzername des Benutzers
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setzt den Benutzernamen des Benutzers.
     * 
     * @param username der zu setzende Benutzername des Benutzers
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gibt das Passwort des Benutzers zurück.
     * 
     * @return das Passwort des Benutzers
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setzt das Passwort des Benutzers.
     * 
     * @param password das zu setzende Passwort des Benutzers
     */
    public void setPassword(String password) {
        this.password = password; // Bitte dieses Passwort hashen
    }

    /**
     * Gibt die Liste der erstellten Aufgaben zurück.
     * 
     * @return die Liste der erstellten Aufgaben
     */
    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    /**
     * Setzt die Liste der erstellten Aufgaben.
     * 
     * @param createdTasks die zu setzende Liste der erstellten Aufgaben
     */
    public void setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    /**
     * Gibt die Liste der zugewiesenen Aufgaben zurück.
     * 
     * @return die Liste der zugewiesenen Aufgaben
     */
    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    /**
     * Setzt die Liste der zugewiesenen Aufgaben.
     * 
     * @param assignedTasks die zu setzende Liste der zugewiesenen Aufgaben
     */
    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    // Hilfsmethoden
    /**
     * Fügt eine Aufgabe zur Liste der erstellten Aufgaben des Benutzers hinzu.
     * 
     * @param task die hinzuzufügende Aufgabe
     */
    public void addCreatedTask(Task task) {
        createdTasks.add(task);
        task.setCreator(this); // Setzt den Ersteller für die Aufgabe
    }

    /**
     * Gibt die E-Mail-Adresse des Benutzers zurück.
     * 
     * @return die E-Mail-Adresse des Benutzers
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse des Benutzers.
     * 
     * @param email die zu setzende E-Mail-Adresse des Benutzers
     */
    public void setEmail(String email) {
        this.email = email; 
    }

    /**
     * Entfernt eine Aufgabe von der Liste der erstellten Aufgaben des Benutzers.
     * 
     * @param task die zu entfernende Aufgabe
     */
    public void removeCreatedTask(Task task) {
        createdTasks.remove(task);
        task.setCreator(null); // Löscht den Ersteller von der Aufgabe
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }
}
