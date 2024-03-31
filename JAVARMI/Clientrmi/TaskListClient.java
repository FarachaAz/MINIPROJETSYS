package Clientrmi;

import java.rmi.Naming;
import java.util.List;

import Rmiinterface.TaskListInterface;

public class TaskListClient {
    public static void main(String[] args) {
        try {
            TaskListInterface gestionnaireTaches = (TaskListInterface) Naming.lookup("//localhost/GestionnaireTachesService");

            // Ajout de tâches
            gestionnaireTaches.ajouterTache("Tâche 1");
            gestionnaireTaches.ajouterTache("Tâche 2");
            gestionnaireTaches.ajouterTache("Tâche 3");

            // Obtenir toutes les tâches
            List<String> toutesTaches = gestionnaireTaches.obtenirToutesTaches();
            System.out.println("Toutes les tâches :");
            for (String tache : toutesTaches) {
                System.out.println(tache);
            }

            // Suppression d'une tâche
            gestionnaireTaches.supprimerTache(2); // Supprime "Tâche 3"

            // Obtenir toutes les tâches à nouveau
            toutesTaches = gestionnaireTaches.obtenirToutesTaches();
            System.out.println("Toutes les tâches après suppression :");
            for (String tache : toutesTaches) {
                System.out.println(tache);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
