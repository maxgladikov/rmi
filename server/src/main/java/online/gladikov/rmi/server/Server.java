package online.gladikov.rmi.server;

import java.rmi.RemoteException;

import online.gladikov.rmi.lib.RemoteService;

public class Server implements RemoteService{

	@Override
	public String getString() throws RemoteException {
		return "Hi from remote";
	}

}
