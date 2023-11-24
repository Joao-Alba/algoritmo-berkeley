package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ServerTwo {

	public static void main(String[] args) {
		try {
			// Servidor 2
			Server hs2 = new TimeServer(LocalTime.parse("07:10:00", formatter));
			Registry registry2 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_2);
			registry2.rebind(TimeServer.class.getSimpleName(), hs2);
			System.out.println(String.format("Servidor 2 iniciado na porta %s", AppConstants.SERVER_PORT_2));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
