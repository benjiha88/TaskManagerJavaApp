
package todoapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("\nMenu :");
            System.out.println("1. Ajouter une tâche");
            System.out.println("2. Lister les tâches");
            System.out.println("3. Marquer une tâche comme terminée");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option (1-4) : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la ligne vide après l'entree du choix

            switch (choice) {
                case 1:
                    System.out.print("Entrez la description de la tâche : ");
                    String description = scanner.nextLine();

                    System.out.print("Entrez la date d'échéance (jj/mm/aaaa) : ");
                    String dueDate = scanner.nextLine();

                    System.out.print("Entrez la priorité (Haute, Moyenne, Basse) : ");
                    String priority = scanner.nextLine();

                    taskManager.addTask(description, dueDate, priority);
                    break;

                case 2:
                    taskManager.listTasks();
                    break;

                case 3:
                    System.out.print("Entrez le numéro de la tâche à marquer comme terminée : ");
                    int taskNumber = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.completeTask(taskNumber - 1);
                    break;

                case 4:
                    System.out.println("Au revoir!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Option invalide, réessayez.");
            }
        }
    }
}

