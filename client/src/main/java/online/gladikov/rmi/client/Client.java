package online.gladikov.rmi.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lombok.extern.slf4j.Slf4j;
import online.gladikov.rmi.lib.RemoteService;

@Slf4j
public class Client {

    private Client() {}

    public static void main(String[] args) throws InterruptedException {
    


      
    	Registry registry=null;
    	RemoteService stub=null;
        try {
             registry = LocateRegistry.getRegistry("192.168.201.63",10000);
             log.info("Remote Register has been retrieved");
             log.info(registry.toString());
        } catch(RemoteException e) {
        	 log.error("Remote Register has not been retrieved: {}" , e.toString());
        	 System.exit(1);
        }
        
        try {
            stub = (RemoteService) registry.lookup("RemoteService");
            log.info("Remote object got: ");
    	} catch (Exception e) {
            log.error("Remote object error: {}" , e.toString());
            System.exit(1);
        }
        
        
        for(int i=0;i<100;i++) {
        		try {
		        
		            String response = stub.getString();
		            log.info("****** {}  *******",stub.getString());
	        	} catch (Exception e) {
		            log.error("Client exception: {}" , e.toString());
		        }
	        Thread.sleep(1000);
        }
    
    }
}