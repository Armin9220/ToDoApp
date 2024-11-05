package com.example.todoapp.service;

import com.example.todoapp.exception.ResourceNotFoundException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse zur Verwaltung von {@link Task} Entitäten.
 * Diese Klasse enthält Methoden zum Erstellen, Abrufen, Aktualisieren und Löschen von Aufgaben,
 * sowie zum Zuweisen von Benutzern zu Aufgaben.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    /**
     * Konstruktor zur Initialisierung der {@link TaskService} mit den erforderlichen Repositories.
     *
     * @param taskRepository Das Repository zur Verwaltung von Aufgaben.
     * @param userRepository Das Repository zur Verwaltung von Benutzern.
     */
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /**
     * Erstellt eine neue {@link Task} und weist sie dem angegebenen Benutzer zu.
     *
     * @param task   Die zu erstellende Aufgabe.
     * @param userId Die ID des Benutzers, der die Aufgabe erstellt.
     * @return Die erstellte {@link Task}.
     * @throws ResourceNotFoundException Wenn der Benutzer nicht gefunden wird.
     */
    public Task createTask(Task task, Long userId) {
        User creator = userRepository.findById(userId)
                      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        task.setCreator(creator);
        return taskRepository.save(task);
    }

    /**
     * Ruft eine {@link Task} anhand ihrer ID ab.
     *
     * @param id Die ID der abzurufenden Aufgabe.
     * @return Die gefundene {@link Task}.
     * @throws ResourceNotFoundException Wenn die Aufgabe nicht gefunden wird.
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    /**
     * Aktualisiert eine bestehende {@link Task} anhand ihrer ID.
     *
     * @param id          Die ID der zu aktualisierenden Aufgabe.
     * @param updatedTask Die {@link Task}, die die neuen Werte enthält.
     * @return Die aktualisierte {@link Task}.
     * @throws ResourceNotFoundException Wenn die Aufgabe nicht gefunden wird.
     */
    public Task updateTask(Long id, Task updatedTask) {
        // Überprüfe, ob die Aufgabe existiert
        Task existingTask = getTaskById(id);
        // Aktualisiere die Eigenschaften der vorhandenen Aufgabe
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        // Speichere die aktualisierte Aufgabe und gebe sie zurück
        return taskRepository.save(existingTask);
    }

    /**
     * Löscht eine {@link Task} anhand ihrer ID.
     *
     * @param id Die ID der zu löschenden Aufgabe.
     * @throws ResourceNotFoundException Wenn die Aufgabe nicht gefunden wird.
     */
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    /**
     * Ruft alle {@link Task} Entitäten ab.
     *
     * @return Eine Liste aller Aufgaben.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Weist eine {@link Task} einem Benutzer zu.
     *
     * @param taskId Die ID der Aufgabe, die zugewiesen werden soll.
     * @param userId Die ID des Benutzers, dem die Aufgabe zugewiesen wird.
     * @return Die aktualisierte {@link Task} mit dem zugewiesenen Benutzer.
     * @throws ResourceNotFoundException Wenn die Aufgabe oder der Benutzer nicht gefunden wird.
     */
    public Task assignTaskToUser(Long taskId, Long userId) {
        Task task = getTaskById(taskId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        
        // Füge den Benutzer der Liste der zugewiesenen Benutzer für diese Aufgabe hinzu
        task.getAssignees().add(user);
        
        // Speichere die aktualisierte Aufgabe und gebe sie zurück
        return taskRepository.save(task);
    }
}
