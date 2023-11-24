package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ServerThree {

	public static void main(String[] args) {
		try {
			// Servidor 3
			Server hs3 = new TimeServer(LocalTime.parse("07:15:00", formatter));
			Registry registry3 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_3);
			registry3.rebind(TimeServer.class.getSimpleName(), hs3);
			System.out.println(String.format("Servidor 3 iniciado na porta %s", AppConstants.SERVER_PORT_3));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
