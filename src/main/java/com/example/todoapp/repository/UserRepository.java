package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todoapp.model.*;

/**
 * Repository-Schnittstelle für die Verwaltung von {@link User} Entitäten.
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet CRUD-Operationen
 * sowie die Möglichkeit, benutzerdefinierte Abfragen für die {@link User} Entität durchzuführen.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
