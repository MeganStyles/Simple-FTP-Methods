package testTime;

import org.junit.Test;

/**
 * @author stylesm
 *
 */
public class Main {

	@Test
	public static void main(String[] args) {
/*
		FTPSOperations ftpS = new FTPSOperations();
		//System.out.println(
				ftpS.connectToFTPSServer("test.rebex.net", 990, "demo", "password");//);
	*/	
		SFTPOperations sftp = new SFTPOperations();
		sftp.connectToSFTPServer("127.0.0.1", 22, "sa", "12345", "C:\\Users\\stylesm\\Music\\private2");
		
	}
	

}
