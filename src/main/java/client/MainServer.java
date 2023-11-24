package client;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.AppConstants;
import server.Server;
import server.TimeServer;

// Alunos: Ana Caroline, João Alba, Nicole

public class MainServer {

	public static void main(String[] args) {
		try {
			List<LocalTime> times = new ArrayList<LocalTime>();

			ServerInfo serverInfo1 = new ServerInfo(AppConstants.SERVER_NAME_1, AppConstants.SERVER_PORT_1, 1);
			ServerInfo serverInfo2 = new ServerInfo(AppConstants.SERVER_NAME_2, AppConstants.SERVER_PORT_2, 2);
			ServerInfo serverInfo3 = new ServerInfo(AppConstants.SERVER_NAME_3, AppConstants.SERVER_PORT_3, 3);
			List<ServerInfo> serverList = new ArrayList<>(Arrays.asList(serverInfo1, serverInfo2, serverInfo3));

			LocalTime localTime = LocalTime.now();
			times.add(localTime);
			System.out.println("Horário Local: " + formatter.format(localTime));

			for(ServerInfo serverInfo : serverList){
				Registry registry = LocateRegistry.getRegistry(serverInfo.serverName, serverInfo.serverPort);
				Server server = (Server) registry.lookup(TimeServer.class.getSimpleName());
				serverInfo.setServer(server);
				System.out.printf("Conexão com Servidor %d estabelecida com sucesso.\n", serverInfo.serverNumber);
				//System.out.printf("Horário Servidor %d: %s", i, formatter.format(horarioServidor));
			}

			Long timeSum = localTime.toNanoOfDay();
			for(ServerInfo serverInfo : serverList){
				timeSum += serverInfo.getServer().getTime().toNanoOfDay();
			}

			Long timeAverage = timeSum / (serverList.size() + 1);

			//fix horario local

			for(ServerInfo serverInfo : serverList){
				Long serverTime = serverInfo.getServer().getTime().toNanoOfDay();
				Long difference = Math.abs(serverTime - timeAverage);
				serverInfo.getServer().setTime(difference);
			}

			System.out.println("Hor�rios atualizados");

			// Verificar horario em todas as inst�ncias
			System.out.println("Horario Local: " + formatter.format(localTime));
			for(ServerInfo serverInfo : serverList){
				System.out.printf("Horario Servidor %d: %s\n", serverInfo.serverNumber, formatter.format(serverInfo.getServer().getTime()));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private static class ServerInfo{
		private final String serverName;
		private final int serverPort;
		private final int serverNumber;
		private Server server;

		public ServerInfo(String serverName, int serverPort, int serverNumber) {
			this.serverName = serverName;
			this.serverPort = serverPort;
			this.serverNumber = serverNumber;
		}

		public Server getServer() {
			return server;
		}

		public void setServer(Server server) {
			this.server = server;
		}
	}
}