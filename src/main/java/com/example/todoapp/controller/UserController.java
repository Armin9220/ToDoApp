package com.example.todoapp.controller;

import com.example.todoapp.model.User;
import com.example.todoapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für die Verwaltung von Benutzern (Users).
 * Dieser Controller bietet RESTful-Endpunkte zum Erstellen, Abrufen, Aktualisieren und Löschen von Benutzern.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Konstruktor zur Initialisierung des UserControllers mit dem UserService.
     * 
     * @param userService der UserService, der für die Verwaltung von Benutzern verwendet wird
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gibt eine Liste aller Benutzer zurück.
     * 
     * @return eine ResponseEntity mit der Liste der Benutzer und dem HTTP-Status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Gibt einen Benutzer basierend auf der angegebenen ID zurück.
     * 
     * @param id die ID des abzurufenden Benutzers
     * @return eine ResponseEntity mit dem gefundenen Benutzer und dem HTTP-Status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Erstellt einen neuen Benutzer.
     * 
     * @param user der zu erstellende Benutzer
     * @return eine ResponseEntity mit dem erstellten Benutzer und dem HTTP-Status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Aktualisiert einen vorhandenen Benutzer basierend auf der angegebenen ID.
     * 
     * @param id die ID des zu aktualisierenden Benutzers
     * @param user die aktualisierten Benutzerdaten
     * @return eine ResponseEntity mit dem aktualisierten Benutzer und dem HTTP-Status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Löscht einen Benutzer basierend auf der angegebenen ID.
     * 
     * @param id die ID des zu löschenden Benutzers
     * @return eine ResponseEntity mit dem HTTP-Status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
