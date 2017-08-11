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
		
		//System.out.println(ftpS.connectToFTPSServer("test.rebex.net", 21, "demo", "password"));
		System.out.println(ftpS.getListOfFiles("ftp://demo@test.rebex.net", 21, "demo", "password","/"));
		
		/*FTPFile[] fileArray = ftpS.getListOfFiles("/");
		for(FTPFile file : fileArray) {
			String name = file.getName().toString();
			System.out.println(name);
		}
		System.out.println(fileArray.length);*/
		
		
				
	/*	
		SFTPOperations sftp = new SFTPOperations();
		sftp.connectToSFTPServer("demo.wftpserver.com", 2222, "tester", "password", "C:\\Users\\stylesm\\Music\\private2.pub" );
		*/
	}
	

}
