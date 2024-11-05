package com.example.todoapp.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Modellklasse, die eine Aufgabe darstellt.
 * Eine Aufgabe hat einen Titel, eine Beschreibung, einen Status, einen Ersteller und eine Menge von zugewiesenen Benutzern.
 */
@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonBackReference // Verhindert Rekursion bei der Serialisierung
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable(
        name = "task_assignee",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference // Verhindert Rekursion bei der Serialisierung
    private Set<User> assignees = new HashSet<>();  // Ändert auf Set, um Duplikate zu vermeiden

    /**
     * Enum zur Darstellung des Aufgabestatus.
     */
    public enum TaskStatus {
        OPEN,
        COMPLETED
    }

    // Konstruktoren
    /**
     * Standardkonstruktor zur Erstellung einer neuen Instanz von Task.
     */
    public Task() {}

    /**
     * Konstruktor zur Erstellung einer neuen Instanz von Task mit spezifischen Werten.
     * 
     * @param title der Titel der Aufgabe
     * @param description die Beschreibung der Aufgabe
     * @param status der Status der Aufgabe
     * @param creator der Benutzer, der die Aufgabe erstellt hat
     */
    public Task(String title, String description, TaskStatus status, User creator) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.creator = creator;
    }

    // Getter und Setter
    /**
     * Gibt die ID der Aufgabe zurück.
     * 
     * @return die ID der Aufgabe
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die ID der Aufgabe.
     * 
     * @param id die zu setzende ID der Aufgabe
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt den Titel der Aufgabe zurück.
     * 
     * @return der Titel der Aufgabe
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setzt den Titel der Aufgabe.
     * 
     * @param title der zu setzende Titel der Aufgabe
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gibt die Beschreibung der Aufgabe zurück.
     * 
     * @return die Beschreibung der Aufgabe
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setzt die Beschreibung der Aufgabe.
     * 
     * @param description die zu setzende Beschreibung der Aufgabe
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gibt den Status der Aufgabe zurück.
     * 
     * @return der Status der Aufgabe
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Setzt den Status der Aufgabe.
     * 
     * @param status der zu setzende Status der Aufgabe
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Gibt den Benutzer zurück, der die Aufgabe erstellt hat.
     * 
     * @return der Ersteller der Aufgabe
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Setzt den Benutzer, der die Aufgabe erstellt hat.
     * 
     * @param creator der zu setzende Ersteller der Aufgabe
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Gibt die Menge der zugewiesenen Benutzer zurück.
     * 
     * @return die Menge der zugewiesenen Benutzer
     */
    public Set<User> getAssignees() {
        return assignees;
    }

    /**
     * Setzt die Menge der zugewiesenen Benutzer.
     * 
     * @param assignees die zu setzende Menge der zugewiesenen Benutzer
     */
    public void setAssignees(Set<User> assignees) {
        this.assignees = assignees;
    }

    // Hilfsmethoden zur Verwaltung von Zuweisungen
    /**
     * Fügt einen Benutzer als Zuweisung zur Aufgabe hinzu.
     * 
     * @param assignee der Benutzer, der der Aufgabe zugewiesen werden soll
     */
    public void addAssignee(User assignee) {
        this.assignees.add(assignee);
        assignee.getAssignedTasks().add(this);
    }

    /**
     * Entfernt einen Benutzer von der Zuweisung der Aufgabe.
     * 
     * @param assignee der Benutzer, der von der Aufgabe entfernt werden soll
     */
    public void removeAssignee(User assignee) {
        this.assignees.remove(assignee);
        assignee.getAssignedTasks().remove(this);
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', status=" + status + "}";
    }
}
