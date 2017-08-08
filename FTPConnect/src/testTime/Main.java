package testTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * @author stylesm
 *
 */
public class Main {

	public static void main(String[] args) {

		FTPOperations method = new FTPOperations();
		Boolean status = method.connectToFTPServer("127.0.0.1", 21, "sa", "12345");
		System.out.println(status);
				
	
		
		FTPFile file = method.getFileWithFileNameExt("/", "new new file");
		System.out.println(file);
		
		
		

		
FTPFile[] moreFiles = method.getFileWithPartialFileName("/", "test");
		
		
		System.out.format("%30s%16s%30s", "File Name", "File Size", "Last Modified");
		System.out.println();
		for(FTPFile file1 : moreFiles) {
			System.out.println();
			System.out.format("%30s%16s",file1.getName(), file1.getSize());
		}
		
		System.out.println();
	
		method.closeFTPConnectionLogout();
	}
	

}
