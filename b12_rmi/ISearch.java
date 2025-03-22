package b12_rmi;

import java.rmi.*;

public interface ISearch extends Remote {
	public String findByID(String id) throws RemoteException;

	public String findByName(String partName)throws RemoteException;

}
