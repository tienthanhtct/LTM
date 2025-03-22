package b12_rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(1099);
		DAO dao = new DAO();
		reg.rebind("SEARCH", dao);
	}
}
