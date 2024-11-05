package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService; // final und im Konstruktor initialisieren

    @Autowired // Konstruktorinjektion
    public TaskController(TaskService taskService) {
        this.taskService = taskService; // hier wird taskService korrekt initialisiert
    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

 
    @PostMapping("/{userId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable Long userId) {
        Task createdTask = taskService.createTask(task, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        Task assignedTask = taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok(assignedTask);
    }

    // Weitere Endpunkte wie Update, Delete, Assign Task
}
