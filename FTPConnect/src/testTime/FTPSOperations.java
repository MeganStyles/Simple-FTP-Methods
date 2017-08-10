package testTime;

import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPSClient;
import org.junit.Test;

public class FTPSOperations {

	FTPSClient ftpS = new FTPSClient(true);
	boolean loginCompleted = false;
	
	
	public boolean connectToFTPSServer(String strServerIP, int intPortNumber, String strUsername, String strPassword) {
		try {
			ftpS.setConnectTimeout(10000);
			InetAddress inetAddress = InetAddress.getByName(strServerIP);
			System.out.println("Connecting to " + inetAddress.toString() );
			ftpS.connect(inetAddress, intPortNumber);
			System.out.println("Connected, logging in...");
			loginCompleted = ftpS.login(strUsername, strPassword);
			System.out.println("Logged in = " + loginCompleted);
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginCompleted;
	}
	


}
