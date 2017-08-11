package testTime;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class FTPSOperations {

	FTPSClient ftpS = new FTPSClient(false);
	FTPFile[] ftpFileArray;
	String[] stringNamesArray ;
	boolean loginCompleted = false;
	
	
	public boolean connectToFTPSServer(String strServerIP, int intPortNumber, String strUsername, String strPassword, int timeout) {
		try {
			ftpS.setConnectTimeout(timeout);
			ftpS.connect(strServerIP, intPortNumber);
			loginCompleted = ftpS.login(strUsername, strPassword);
			
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginCompleted;
	}
	
	
	
	
	public String[] getListOfFiles(String strRemoteFolderPath) {
		try {
			
			//ftpFileArray = ftpS.listFiles(strRemoteFolderPath);
			stringNamesArray = ftpS.listNames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringNamesArray;
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
