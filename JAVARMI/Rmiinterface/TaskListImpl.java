package Rmiinterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskListImpl extends UnicastRemoteObject implements TaskListInterface {

    private List<String> taches;

    public TaskListImpl() throws RemoteException {
        taches = new ArrayList<>();
    }

    @Override
    public void ajouterTache(String tache) throws RemoteException {
        taches.add(tache);
        System.out.println("Tâche ajoutée : " + tache);
    }

    @Override
    public void supprimerTache(int idTache) throws RemoteException {
        if (idTache >= 0 && idTache < taches.size()) {
            String tacheSupprimee = taches.remove(idTache);
            System.out.println("Tâche supprimée : " + tacheSupprimee);
        } else {
            System.out.println("Identifiant de tâche invalide.");
        }
    }

    @Override
    public List<String> obtenirToutesTaches() throws RemoteException {
        return taches;
    }
}
