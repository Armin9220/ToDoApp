package com.example.todoapp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
    private String password; // Consider hashing this

    private String email;
    // One-to-Many relationship
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> createdTasks = new ArrayList<>();
    
    @ManyToMany(mappedBy = "assignees", fetch = FetchType.LAZY)
    private List<Task> assignedTasks = new ArrayList<>();
    

    // Constructors
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password; // Hash this password when setting
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // Hash this password when setting
    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    // Utility methods
    public void addCreatedTask(Task task) {
        createdTasks.add(task);
        task.setCreator(this); // Set the creator for the task
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email; }
     public void removeCreatedTask(Task task) {
        createdTasks.remove(task);
        task.setCreator(null); // Clear the creator from the task
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }
}
