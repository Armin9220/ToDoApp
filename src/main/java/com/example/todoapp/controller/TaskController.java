package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller für die Verwaltung von Aufgaben (Tasks).
 * Dieser Controller bietet RESTful-Endpunkte zum Erstellen, Abrufen, Löschen und Zuweisen von Aufgaben.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService; // final und im Konstruktor initialisieren

    /**
     * Konstruktor zur Initialisierung des TaskControllers mit dem TaskService.
     * 
     * @param taskService der TaskService, der für die Verwaltung von Aufgaben verwendet wird
     */
    @Autowired // Konstruktorinjektion
    public TaskController(TaskService taskService) {
        this.taskService = taskService; // hier wird taskService korrekt initialisiert
    }

    /**
     * Gibt eine Liste aller Aufgaben zurück.
     * 
     * @return eine ResponseEntity mit der Liste der Aufgaben und dem HTTP-Status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Erstellt eine neue Aufgabe für den angegebenen Benutzer.
     * 
     * @param task die zu erstellende Aufgabe
     * @param userId die ID des Benutzers, dem die Aufgabe zugewiesen wird
     * @return eine ResponseEntity mit der erstellten Aufgabe und dem HTTP-Status 201 (Created)
     */
    @PostMapping("/{userId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable Long userId) {
        Task createdTask = taskService.createTask(task, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * Gibt eine Aufgabe basierend auf der angegebenen ID zurück.
     * 
     * @param id die ID der abzurufenden Aufgabe
     * @return eine ResponseEntity mit der gefundenen Aufgabe und dem HTTP-Status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Löscht eine Aufgabe basierend auf der angegebenen ID.
     * 
     * @param id die ID der zu löschenden Aufgabe
     * @return eine ResponseEntity mit dem HTTP-Status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Weist eine Aufgabe einem Benutzer zu.
     * 
     * @param taskId die ID der Aufgabe, die zugewiesen wird
     * @param userId die ID des Benutzers, dem die Aufgabe zugewiesen wird
     * @return eine ResponseEntity mit der zugewiesenen Aufgabe und dem HTTP-Status 200 (OK)
     */
    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        Task assignedTask = taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok(assignedTask);
    }

    // Weitere Endpunkte wie Update, Delete, Assign Task
}
