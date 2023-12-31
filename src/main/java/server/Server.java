package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface Server extends Remote {

	LocalTime getTime() throws RemoteException;

	void setTime(Long nanoDifference) throws RemoteException;
}