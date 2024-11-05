package com.example.todoapp.service;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse zur Verwaltung von {@link User} Entitäten.
 * Diese Klasse bietet Methoden zum Abrufen, Erstellen, Aktualisieren und Löschen von Benutzern.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Konstruktor zur Initialisierung der {@link UserService} mit dem erforderlichen UserRepository.
     *
     * @param userRepository Das Repository zur Verwaltung von Benutzern.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Ruft alle {@link User} Entitäten ab.
     *
     * @return Eine Liste aller Benutzer.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Ruft einen {@link User} anhand seiner ID ab.
     *
     * @param id Die ID des abzurufenden Benutzers.
     * @return Der gefundene {@link User}.
     * @throws ResourceNotFoundException Wenn der Benutzer nicht gefunden wird.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    /**
     * Erstellt einen neuen {@link User}.
     *
     * @param user Der zu erstellende Benutzer.
     * @return Der erstellte {@link User}.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Aktualisiert einen bestehenden {@link User} anhand seiner ID.
     *
     * @param id         Die ID des zu aktualisierenden Benutzers.
     * @param userDetails Ein {@link User} Objekt, das die neuen Werte enthält.
     * @return Der aktualisierte {@link User}.
     * @throws ResourceNotFoundException Wenn der Benutzer nicht gefunden wird.
     */
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    /**
     * Löscht einen {@link User} anhand seiner ID.
     *
     * @param id Die ID des zu löschenden Benutzers.
     * @throws ResourceNotFoundException Wenn der Benutzer nicht gefunden wird.
     */
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
