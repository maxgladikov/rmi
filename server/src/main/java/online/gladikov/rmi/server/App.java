package online.gladikov.rmi.server;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.gladikov.rmi.lib.RemoteService;
/**
 *  The main method of the server needs to create the remote object that provides the service.
 *  Additionally, the remote object must be exported to the Java RMI runtime so that it may receive incoming remote calls.
 */

@Slf4j
@NoArgsConstructor
public class App {

    private static final  int PORT_NUMBER=10_000;
   
        
    public static void main(String[] args) throws IOException {
    	System.setProperty("java.rmi.server.hostname","192.168.201.63");
    	ProcessBuilder builder = new ProcessBuilder();
    	builder.command("/bin/sh", "-c", "rmiregistry", "10000").start();
    	RemoteService stub=null;
    	Registry registry=null;
    	Server obj = new Server();
     
        	 try {
				stub = (RemoteService) UnicastRemoteObject.exportObject(obj, PORT_NUMBER);
			} catch (RemoteException e) {
				 log.error("Creating a stub "+e.toString());
				 System.exit(1);
			}

            
           // Get registry
			try {
				registry = LocateRegistry.createRegistry(PORT_NUMBER);
				//registry = LocateRegistry.getRegistry(PORT_NUMBER);
				
			} catch (RemoteException e) {
				 log.error("Getting local registry "+e.toString());
				 System.exit(1);
			}
			// Bind the remote object's stub in the registry
            try {
				registry.bind("RemoteService", stub);
				log.info("Server ready: {}",registry);
				
				Arrays.stream(registry.list()).forEach(x -> log.info("Binded entity {}",x));
			} catch (RemoteException e) {
				 log.error("Binding 1 "+e.toString());
			} catch ( AlreadyBoundException e) {
				log.error("Binding 2 "+e.toString());
			}
            
           
        
       
    }
}