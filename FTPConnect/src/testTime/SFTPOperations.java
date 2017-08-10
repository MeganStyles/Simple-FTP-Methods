package testTime;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTPSClient;
import com.jcraft.jsch.*;

public class SFTPOperations {
	FTPSClient ftpS = new FTPSClient();
	JSch jsch = new JSch();
	boolean loginCompleted = false;
	
	public boolean connectToSFTPServer(String strServerIP, int intPortNumber, String strUsername, String strPassword, String strPrivateKey) {
		try {
			//java.util.Properties config = new java.util.Properties(); 
	//		config.put("StrictHostKeyChecking", "no");
			
			System.out.println("connecting...");
			Session session = jsch.getSession(strUsername, strServerIP, intPortNumber);
	//		session.setConfig(config);
			session.setPassword(strPassword);
			System.out.println("password set");
			jsch.addIdentity(strPrivateKey);
			session.connect();
			System.out.println("Connected");
			
		//	session.setPassword(strPassword);
		//	jsch.addIdentity(strPrivateKey);
		//	session.connect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginCompleted;
	}

}
