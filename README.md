# TO Do APP
# Inhaltsverzeichnis

1. [Grundidee](#grundidee)
2. [Arbeitsplan](#arbeitsplan)
3. [Anforderungsanalyse](#anforderungsanalyse)
   - [User Stories](#user-stories)
4. [Datenmodell](#datenmodell)
5. [Klassendiagramm](#klassendiagramm)
6. [Testplan](#testplan)
   - [Positive Testfälle](#positive-testfälle)
   - [Negative Testfälle](#negative-testfälle)
   - [Testprotokoll](#testprotokoll)
7. [Installationsanleitung](#installationsanleitung)
8. [Hilfestellungen](#hilfestellungen)
9. [Auswertung](#auswertung)
   - [Stärken](#stärken)
   - [Schwächen](#schwächen)
   - [Verbesserungsvorschläge](#verbesserungsvorschläge)
10. [API-Dokumentation](#api-dokumentation)

# Projektdokumentation 

Projektziel: Webbasierte To-Do-Liste
Das Ziel dieses Projekts ist die Entwicklung einer benutzerfreundlichen, webbasierten To-Do-Liste, die Nutzern hilft, ihre täglichen Aufgaben effizient zu verwalten. Benutzer können Aufgaben hinzufügen, bearbeiten, als erledigt markieren oder löschen.
Die Anwendung wird so gestaltet, dass sie sowohl für Studierende als auch für Berufstätige nützlich ist und ihnen eine einfache Möglichkeit bietet, den Überblick über anstehende Aufgaben zu behalten und ihre Produktivität zu steigern.
Zusätzlich können Benutzer andere Nutzer erstellen, ihnen Aufgaben zuweisen und sowohl ihre eigenen Aufgaben als auch die zugewiesenen Aufgaben anderer Nutzer einsehen.
## Grundidee

Das Ziel dieses Projekts ist die Entwicklung einer benutzerfreundlichen, webbasierten To-Do-Liste, die Nutzern hilft, ihre täglichen Aufgaben effizient zu verwalten. Benutzer können Aufgaben hinzufügen, bearbeiten, als erledigt markieren oder löschen. Die Anwendung wird so gestaltet, dass sie sowohl für Studierende als auch für Berufstätige nützlich ist und ihnen eine einfache Möglichkeit bietet, den Überblick über anstehende Aufgaben zu behalten und ihre Produktivität zu steigern. Zusätzlich können Benutzer andere Nutzer erstellen, ihnen Aufgaben zuweisen und sowohl ihre eigenen Aufgaben als auch die zugewiesenen Aufgaben anderer Nutzer einsehen.

## Arbeitsplan

### 1. Vorbereitung
- **Dauer:** 1,5 Stunden
- **Aufgaben:**
  - Projektidee und Zielsetzung festhalten.
  - User Stories erstellen.
  - Datenmodell und Akzeptanzkriterien definieren.

### 2. Datenmodell und REST-Endpunkte vorbereiten
- **Dauer:** 3,5 Stunden
- **Aufgaben:**
  - Datenbankmodell für Benutzer und Aufgaben entwerfen.
  - Entwurf der benötigten Endpunkte für CRUD-Operationen (Create, Read, Update, Delete).
  - Validierungskriterien für Eingaben und Fehlermeldungen planen.
  - Testfälle für Positiv- und Negativtests erstellen.

### 3. Implementierung der REST-API
- **Dauer:** 5 Stunden
- **Aufgaben:**
  - Benutzerverwaltung (Registrierung und Authentifizierung).
  - Endpunkte für Aufgabenverwaltung (Erstellen, Zuweisen, Anzeigen, Löschen).
  - Zugriffsrechte und Filteroptionen implementieren.

### 4. Validierung und Fehlermanagement
- **Dauer:** 3 Stunden
- **Aufgaben:**
  - Implementierung von Validierungsregeln.
  - Erstellung und Integration von Fehlermeldungen.
  - Sicherstellen der Dateneingabekontrolle (z.B. gegen SQL-Injection).

### 5. Unit-Tests schreiben und manuelle Tests durchführen
- **Dauer:** 3 Stunden
- **Aufgaben:**
  - Erstellen von Unit-Tests für die wichtigsten Endpunkte.
  - Testprotokoll für Positiv- und Negativtests.
  - Fehlerbehebung basierend auf Testergebnissen.

### 6. Dokumentation und Abschluss
- **Dauer:** 3 Stunden
- **Aufgaben:**
  - Erstellen der Projektdokumentation (Projektidee, Klassendiagramm, Endpunktdokumentation).
  - Installationsanleitung und Testprotokoll dokumentieren.
  - ZIP-File erstellen und Projekt in GitHub bereitstellen.

**Gesamtzeit:** 19,5 Stunden

## Anforderungsanalyse

[Insomnia](Insomnia_2024-11-05.json)

### User Stories

1. **Aufgaben erstellen**
   - **Beschreibung:** Als Benutzer möchte ich eine neue Aufgabe erstellen können, um To-Dos zu verwalten.
   - **Akzeptanzkriterien:**
     - Eine Aufgabe enthält mindestens einen Titel und eine Beschreibung.
     - Das System bestätigt, dass die Aufgabe erfolgreich erstellt wurde.
     - Die erstellte Aufgabe ist eindeutig einem Benutzer zugeordnet.

2. **Aufgaben zuweisen**
   - **Beschreibung:** Als Benutzer möchte ich die Möglichkeit haben, eine Aufgabe einem anderen Benutzer zuweisen, um das Aufgabenmanagement im Team zu unterstützen.
   - **Akzeptanzkriterien:**
     - Die Aufgabe kann einem Benutzer aus der Benutzerdatenbank zugewiesen werden.
     - Das System zeigt an, ob die Zuweisung erfolgreich war.
     - Der Empfänger der Aufgabe kann diese als ihm zugewiesen einsehen.

3. **Aufgaben anzeigen**
   - **Beschreibung:** Als Benutzer möchte ich die Möglichkeit haben, meine eigenen oder mir zugewiesene Aufgaben einzusehen, um einen Überblick über meine Aufgaben zu behalten.
   - **Akzeptanzkriterien:**
     - Das System listet alle eigenen und zugewiesenen Aufgaben auf.
     - Aufgaben sind nach Status (offen/erledigt) filterbar.

## Datenmodell

User zu Task:
Ein Benutzer kann viele Aufgaben erstellen, referenziert durch created_by in der TASK-Tabelle.
Ein Benutzer kann vielen Aufgaben zugewiesen werden, basierend auf der ASSIGNMENT-Tabelle.

Task zu Assignment:
Eine Aufgabe kann mehreren Benutzern zugewiesen werden, da sie in der ASSIGNMENT-Tabelle mehrfach verknüpft ist.
Die task_id in der ASSIGNMENT-Tabelle referenziert die entsprechende Aufgabe.

User zu Assignment:
Ein Benutzer kann viele Einträge in der ASSIGNMENT-Tabelle haben, wenn ihm mehrere Aufgaben zugewiesen sind.

## Klassendiagramm
![ERM DIAGRAMM](https://github.com/user-attachments/assets/7d136bc7-169a-4055-91c0-d0435859fd44)


## Testplan

### Positive Testfälle

1. **Test der Erstellung eines neuen Tasks**
   - **Testfall:** `testCreateTask()`
   - **Erwartetes Ergebnis:** Ein neuer Task sollte erfolgreich erstellt werden.
   - **Tatsächliches Ergebnis:** Der Task wird erfolgreich erstellt und in der Datenbank gespeichert.
   - **Begründung:** Überprüft die Funktionalität zur Task-Erstellung.

2. **Test der Anzeige aller Benutzer**
   - **Testfall:** `testGetAllUsers()`
   - **Erwartetes Ergebnis:** Alle vorhandenen Benutzer sollten erfolgreich angezeigt werden.
   - **Tatsächliches Ergebnis:** Die Liste der Benutzer wird korrekt zurückgegeben.
   - **Begründung:** Überprüft die Funktionalität zum Abrufen aller Benutzer.

3. **Test der Erstellung eines neuen Assignments**
   - **Testfall:** `testCreateAssignment()`
   - **Erwartetes Ergebnis:** Ein neues Assignment sollte erfolgreich erstellt werden.
   - **Tatsächliches Ergebnis:** Das Assignment wird erfolgreich erstellt und in der Datenbank gespeichert.
   - **Begründung:** Überprüft die Funktionalität zur Erstellung eines neuen Assignments.

### Negative Testfälle

4. **Test der Erstellung eines neuen Tasks (Problem)**
   - **Testfall:** `testCreateTask()`
   - **Erwartetes Ergebnis:** Ein neuer Task sollte erfolgreich erstellt werden.
   - **Tatsächliches Ergebnis:** Fehler beim Erstellen eines neuen Tasks.
   - **Begründung:** Dieser Test zeigt ein Problem in der Implementierung der Task-Erstellung.

5. **Test der Zuweisung eines Tasks an einen Benutzer (Problem)**
   - **Testfall:** `testAssignTaskToUser()`
   - **Erwartetes Ergebnis:** Der Task sollte einem Benutzer zugewiesen werden.
   - **Tatsächliches Ergebnis:** Fehler beim Zuweisen des Tasks an den Benutzer.
   - **Begründung:** Dieser Test zeigt, dass die Zuweisung von Tasks an Benutzer nicht funktioniert.

6. **Test der Aktualisierung eines Tasks (Problem)**
   - **Testfall:** `testUpdateTask()`
   - **Erwartetes Ergebnis:** Der Task sollte erfolgreich aktualisiert werden.
   - **Tatsächliches Ergebnis:** Fehler beim Aktualisieren des Tasks.
   - **Begründung:** Dieser Test zeigt ein Problem in der Implementierung der Task-Aktualisierung.
  
## Automatische Tests

![image](https://github.com/user-attachments/assets/b6f7a2e3-a26a-44cd-a250-91adec6606a8)


### Testprotokoll

Das Testprotokoll dokumentiert die Ergebnisse der durchgeführten Tests, sowohl positiv als auch negativ, und dient als Grundlage für die weitere Analyse und Behebung der festgestellten Probleme.

## Installationsanleitung

1. **Voraussetzungen:**
   - JDK 11 oder höher
   - Maven
   - MySQL-Datenbank

2. **Datenbank einrichten:**
   ```sql
   CREATE DATABASE todoapp;
   CREATE USER 'todoappuser'@'localhost' IDENTIFIED BY 'password';
   GRANT ALL PRIVILEGES ON todoapp.* TO 'todoappuser'@'localhost';
   FLUSH PRIVILEGES;
3. **Anwendung konfigurieren**
   Ändern Sie die application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/todoapp
spring.datasource.username=todoappuser
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
4. **Projekt ausführen**
   mvn spring-boot:run
5. **API testen:**
   Verwenden Sie Insomnia oder Postman, um die API-Endpunkte zu testen.

## Hilfestellungen
   
**Allgemeine Hinweise:**

Überprüfen Sie die Netzwerkkonfiguration, um sicherzustellen, dass der MySQL-Server erreichbar ist.
Achten Sie auf Fehlermeldungen und deren Bedeutung.
Fehlerbehebung:

Bei Problemen mit der Datenbankverbindung die application.properties erneut überprüfen.
Log-Dateien im logs-Verzeichnis analysieren, um mögliche Fehlerquellen zu identifizieren.
Auswertung
In diesem Abschnitt reflektiere ich über die Module und das Projekt. Ich werde Stärken und Schwächen identifizieren und Verbesserungsvorschläge für zukünftige Projekte formulieren.

## Stärken
Strukturierte Herangehensweise an die Projektplanung und -dokumentation.
Benutzer- und Aufgabenverwaltung wurde erfolgreich implementiert.

## Schwächen
Einige Funktionen könnten optimiert werden, um die Performance zu verbessern.
Testen der Anwendung war zeitaufwendig.

## Verbesserungsvorschläge
Automatisierte Tests einführen, um die Qualität des Codes zu sichern.
Regelmäßige Code-Reviews durchführen, um frühzeitig Probleme zu identifizieren.

## API-Dokumentation
Die API-Endpunkte sind im Folgenden dokumentiert:

**Benutzer-Management**
POST /users: Erstellen eines neuen Benutzers
GET /users: Alle Benutzer auflisten
GET /users/{id}: Benutzer anhand der ID abrufen
PUT /users/{id}: Benutzer aktualisieren
DELETE /users/{id}: Benutzer löschen

**Aufgaben-Management**
POST /tasks: Erstellen einer neuen Aufgabe
GET /tasks: Alle Aufgaben auflisten
GET /tasks/{id}: Aufgabe anhand der ID abrufen
PUT /tasks/{id}: Aufgabe aktualisieren
DELETE /tasks/{id}: Aufgabe löschen

**Zuweisungen**
POST /assignments: Erstellen einer neuen Zuweisung
GET /assignments: Alle Zuweisungen auflisten
GET /assignments/{id}: Zuweisung anhand der ID abrufen
DELETE /assignments/{id}: Zuweisung löschen
