package Rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TaskListInterface extends Remote {
    void ajouterTache(String tache) throws RemoteException;
    void supprimerTache(int idTache) throws RemoteException;
    List<String> obtenirToutesTaches() throws RemoteException;
}
