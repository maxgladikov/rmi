package online.gladikov.rmi.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
    String getString() throws RemoteException;
}