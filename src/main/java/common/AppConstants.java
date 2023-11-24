package common;

import java.time.format.DateTimeFormatter;

public interface AppConstants {

	String SERVER_NAME_1 = "localhost";
	int SERVER_PORT_1 = 1500;
	
	String SERVER_NAME_2 = "localhost";
	int SERVER_PORT_2 = 1501;
	
	String SERVER_NAME_3 = "localhost";
	int SERVER_PORT_3 = 1502;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
}
