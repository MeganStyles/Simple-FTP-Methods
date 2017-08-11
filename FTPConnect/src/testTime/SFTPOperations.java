package testTime;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFileChooser;

import org.apache.commons.net.ftp.FTPSClient;
import com.jcraft.jsch.*;
import com.jcraft.jsch.ConfigRepository.Config;

public class SFTPOperations {
	FTPSClient ftpS = new FTPSClient();
	JSch jsch = new JSch();
	boolean loginCompleted = false;
	
	public boolean connectToSFTPServer(String strServerIP, int intPortNumber, String strUsername, String strPassword, String strPrivateKeyPath) {
		try {
			//TODO: change to different library
			System.out.println("connecting...");
			Session session = jsch.getSession(strUsername, strServerIP, intPortNumber);
			//jsch.getConfig(strPrivateKey);
			//HostKeyRepository hkr=jsch.getHostKeyRepository();
		  
		 //   HostKey hostKey = new HostKey ("127.0.0.1",hkr.toString().getBytes());
		/*	String knownHostPublicKey = "127.0.0.1 ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAQEApzCLdXj+SYdhJYM6yBgWE+EFP/SUbL1SJ4hP\r\n" + 
					"LsghHBQHV+z7rV7PhmKTd7KZ87fyrLRDGY3YEsByECSNT9AQYDIYcTniFegajmQJ\r\n" + 
					"1/9XRJt3JSFYGtt6WdGVWHSIP5lY3q3H1yGl+JksBY7iqG4t/46LP7mLIb0Etosb\r\n" + 
					"gscKos73nIaAD/1gyumJkaVZm6GT2m0IGhUKXuOHCIPjQX2nrGItJMqnrPWeY9BE\r\n" + 
					"7ORgwkCXBtHFI0RXjLPkWTXRXSuvjz/f7E+utge0+BH0YKU079pxlC8Euio3geXm\r\n" + 
					"AN4+b8LRxMpsJkwF/ggUtomp6muFZGDauxXqQdWTGy1P71l61Q==\r\n" + 
					"";
			jsch.setKnownHosts(new ByteArrayInputStream(knownHostPublicKey.getBytes()));
	*/
			session.setPassword(strPassword);
			System.out.println("password set");
		
//			jsch.addIdentity(strPrivateKey, "password");
		
			session.connect();
			System.out.println("Connected");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginCompleted;
	}

}
