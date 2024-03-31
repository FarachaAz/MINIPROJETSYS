package javaaRmijava;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import Rmiinterface.TaskListImpl;

public class TaskListServer {
    public static void main(String[] args) {
        try {
            TaskListImpl gestionnaireTaches = new TaskListImpl();
            LocateRegistry.createRegistry(1099); // Port par défaut du registre RMI
            Naming.rebind("//localhost/GestionnaireTachesService", gestionnaireTaches);
            System.out.println("Serveur de liste de tâches en cours d'exécution...");
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
