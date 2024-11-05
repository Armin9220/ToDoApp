package com.example.todoapp;

import com.example.todoapp.exception.ResourceNotFoundException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.service.TaskService;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService taskService;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        taskService = new TaskService(taskRepository, userRepository);
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task.");
        task.setStatus(Task.TaskStatus.OPEN);

        Long userId = 1L;

        Mockito.when(taskRepository.save(task)).thenReturn(task);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        Task createdTask = taskService.createTask(task, userId);
        assertEquals("Test Task", createdTask.getTitle());
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task foundTask = taskService.getTaskById(1L);
        assertEquals("Test Task", foundTask.getTitle());
    }

    @Test
    void testUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old Task");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(Task.TaskStatus.OPEN);

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));

        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setStatus(Task.TaskStatus.COMPLETED);

        Mockito.when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task result = taskService.updateTask(1L, updatedTask);
        assertEquals("Updated Task", result.getTitle());
        assertEquals(Task.TaskStatus.COMPLETED, result.getStatus());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        taskService.deleteTask(1L);
        Mockito.verify(taskRepository, Mockito.times(1)).delete(task);
    }

    @Test
    void testGetTaskByIdNotFound() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            taskService.getTaskById(1L);
        });

        assertEquals("Task not found with id 1", exception.getMessage());
    }
}