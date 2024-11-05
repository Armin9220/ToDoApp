package com.example.todoapp.service;

import com.example.todoapp.exception.ResourceNotFoundException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Task task, Long userId) {
        User creator = userRepository.findById(userId)
                      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        task.setCreator(creator);
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }


    

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

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
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