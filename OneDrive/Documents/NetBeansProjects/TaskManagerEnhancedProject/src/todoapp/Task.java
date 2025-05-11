
package todoapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private boolean completed;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private String priority; // "Haute", "Moyenne", "Basse"

    public Task(String description, LocalDate dueDate, String priority) {
        this.description = description;
        this.completed = false;
        this.creationDate = LocalDate.now();
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return (completed ? "[X] " : "[ ] ") + description +
               " | Crée le: " + creationDate.format(formatter) +
               " | Échéance: " + dueDate.format(formatter) +
               " | Priorité: " + priority;
    }
}
