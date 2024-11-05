package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todoapp.model.*;
import java.util.List;

/**
 * Repository-Schnittstelle für die Verwaltung von {@link Task} Entitäten.
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet CRUD-Operationen
 * sowie die Möglichkeit, benutzerdefinierte Abfragen für die {@link Task} Entität durchzuführen.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    /**
     * Findet alle {@link Task} Entitäten mit dem angegebenen Status.
     *
     * @param status Der Status der gesuchten Aufgaben.
     * @return Eine Liste von Aufgaben, die dem angegebenen Status entsprechen.
     */
    List<Task> findByStatus(Task.TaskStatus status);
}
