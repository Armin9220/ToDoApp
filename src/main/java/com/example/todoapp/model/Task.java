package com.example.todoapp.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference // Prevents recursion when serializing
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable(
        name = "task_assignee",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference // Prevents recursion when serializing
    private Set<User> assignees = new HashSet<>();  // Changed to Set to avoid duplicates

    public enum TaskStatus {
        OPEN,
        COMPLETED
    }

    // Constructors
    public Task() {}

    public Task(String title, String description, TaskStatus status, User creator) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.creator = creator;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<User> assignees) {
        this.assignees = assignees;
    }

    // Utility methods to manage assignees
    public void addAssignee(User assignee) {
        this.assignees.add(assignee);
        assignee.getAssignedTasks().add(this);
    }

    public void removeAssignee(User assignee) {
        this.assignees.remove(assignee);
        assignee.getAssignedTasks().remove(this);
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', status=" + status + "}";
    }
}
