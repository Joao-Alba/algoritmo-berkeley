package server;

import static common.AppConstants.rand;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ServerTwo {

	public static void main(String[] args) {
		try {
			Long randomNano = LocalTime.now().toNanoOfDay();
			randomNano += rand.nextLong(-5000L, 5000L);
			TimeServer server = new TimeServer(LocalTime.ofNanoOfDay(randomNano));
			Registry registry = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_2);
			registry.rebind(TimeServer.class.getSimpleName(), server);
			System.out.println(String.format("Servidor 2 iniciado na porta %s", AppConstants.SERVER_PORT_2));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
