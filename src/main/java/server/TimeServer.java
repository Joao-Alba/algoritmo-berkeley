package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

import common.AppConstants;

public class TimeServer extends UnicastRemoteObject implements Server {

	private LocalTime horario;

	public TimeServer(LocalTime horario) throws RemoteException {
		this.horario = horario;
	}

	@Override
	public LocalTime getTime() throws RemoteException {
		return horario;
	}

	@Override
	public void setTime(Long nanoDifference) throws RemoteException {
		Long currentTime = this.horario.toNanoOfDay();
		currentTime += nanoDifference;
		LocalTime newTime = LocalTime.ofNanoOfDay(currentTime);
		System.out.println("Hor�rio atualizado: " + AppConstants.formatter.format(newTime));
		this.horario = newTime;
	}

}