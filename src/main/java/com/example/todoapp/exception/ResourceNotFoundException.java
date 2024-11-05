package com.example.todoapp.exception;

/**
 * Ausnahme, die ausgelöst wird, wenn eine angeforderte Ressource nicht gefunden wird.
 * Diese Ausnahme erbt von RuntimeException und kann in Controller-Klassen verwendet werden,
 * um auf fehlende Ressourcen zu reagieren.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Konstruktor zur Erstellung einer ResourceNotFoundException mit einer spezifischen Fehlermeldung.
     * 
     * @param message die Fehlermeldung, die die Ursache des Fehlers beschreibt
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
