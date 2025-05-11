
package todoapp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private final String filePath = "tasks.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TaskManager() {
        loadTasksFromFile();
    }

    public void addTask(String description, String dueDateStr, String priority) {
        try {
            LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
            Task task = new Task(description, dueDate, priority);
            tasks.add(task);
            saveTasksToFile();
            System.out.println("Tâche ajoutée avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur : Veuillez entrer une date valide au format jj/mm/aaaa.");
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche trouvée.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(true);
            saveTasksToFile();
            System.out.println("Tâche marquée comme terminée.");
        } else {
            System.out.println("Index invalide.");
        }
    }

    private void loadTasksFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                boolean completed = parts[0].startsWith("[X]");
                String description = parts[0].substring(4).trim();
                LocalDate creationDate = LocalDate.parse(parts[1].split(": ")[1], formatter);
                LocalDate dueDate = LocalDate.parse(parts[2].split(": ")[1], formatter);
                String priority = parts[3].split(": ")[1];

                Task task = new Task(description, dueDate, priority);
                task.setCompleted(completed);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des tâches : " + e.getMessage());
        }
    }

    private void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(task);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}
