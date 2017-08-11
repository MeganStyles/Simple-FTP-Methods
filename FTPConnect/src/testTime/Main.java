package testTime;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

/**
 * @author stylesm
 *
 */
public class Main {

	@Test
	public static void main(String[] args) {

		FTPSOperations ftpS = new FTPSOperations();
		
		System.out.println(ftpS.connectToFTPSServer("195.144.107.198", 21, "demo", "password", 10000));
	//System.out.println(ftpS.getListOfFiles("/").length);
		
		String[] nameArray = ftpS.getListOfFiles("/");
		System.out.println(nameArray.length);
	/*	for(String name : nameArray) {
			//String name = file.getName().toString();
			System.out.println(name);
		}*/
		
		
				
	/*	
		SFTPOperations sftp = new SFTPOperations();
		sftp.connectToSFTPServer("demo.wftpserver.com", 2222, "tester", "password", "C:\\Users\\stylesm\\Music\\private2.pub" );
		*/
	}
	

}
