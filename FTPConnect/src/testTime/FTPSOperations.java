package testTime;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.junit.Test;

public class FTPSOperations {

	FTPSClient ftpS = new FTPSClient(true);
	FTPFile[] ftpFileArray;
	boolean loginCompleted = false;
	
	
	public boolean connectToFTPSServer(String strServerIP, int intPortNumber, String strUsername, String strPassword) {
		try {
			ftpS.setConnectTimeout(10000);
			InetAddress inetAddress = InetAddress.getByName(strServerIP);
			//System.out.println("Connecting to " + inetAddress.toString() );
			ftpS.connect(inetAddress, intPortNumber);
			//System.out.println("Connected, logging in...");
			loginCompleted = ftpS.login(strUsername, strPassword);
			
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginCompleted;
	}
	
	
	
	
	public FTPFile[] getListOfFiles(String strServerIP, int intPortNumber, String strUsername, String strPassword, String strRemoteFolderPath) {
		try {
			//ftpS.setConnectTimeout(10000);
			//InetAddress inetAddress = InetAddress.getByName(strServerIP);
			//System.out.println("Connecting to " + inetAddress.toString() );
			FTPSClient ftpS1 = new FTPSClient(false);
			//FTPSClient ftpS1 = new FTPSClient(context);
			ftpS1.connect("195.144.107.198", 21);
			ftpS1.setControlKeepAliveReplyTimeout(10000);
			ftpFileArray = ftpS1.listFiles(strRemoteFolderPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftpFileArray;
	}
	
	public FTPFile[] getDirectory() {
		try {
			ftpFileArray = ftpS.listDirectories();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftpFileArray;
	}


}
