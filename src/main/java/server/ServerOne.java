package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ServerOne {

	public static void main(String[] args) {
		try {
			Server hs1 = new TimeServer(LocalTime.parse("07:05:00", formatter));
			Registry registry1 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_1);
			registry1.rebind(TimeServer.class.getSimpleName(), hs1);
			System.out.println(String.format("Servidor 1 iniciado na porta %s", AppConstants.SERVER_PORT_1));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
