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
				
		boolean uploaded = method.uploadFileToServer(
				"C:\\Users\\stylesm\\Documents\\HealthAlliance\\TestData_excel2db\\documents\\Test Scenarios.v3.0.xls", 
				"Test Scenarios.v3.0");
		System.out.println(uploaded);
		System.out.println(method.getCurrentDirectory());
		
		FTPFile[] files = method.getListOfFiles(method.getCurrentDirectory());
		
		
		System.out.format("%30s%16s%30s", "File Name", "File Size", "Last Modified");
		System.out.println();
		for(FTPFile file : files) {
			System.out.println();
			Calendar timeStamp = file.getTimestamp();
			SimpleDateFormat modify = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String lastModified = modify.format(timeStamp.getTime());
			System.out.format("%30s%16s%30s",file.getName(), file.getSize(), lastModified);
		}
		
		System.out.println();
		
		String bool = method.setWorkingDirectory("/New directory");
		System.out.println(bool);
		//System.out.println(method.renameFileOnServer("savedstuff.txt", "Saved Stuff.txt"));
		System.out.println(method.createDirectoryOnServer("/New directory", "another directory"));
		method.deleteDirectoryOnServer("another directory");
/*	
		
FTPFile[] moreFiles = method.getSpecificFileInWorkingDirectory("savedstuff.txt");
		
		
		System.out.format("%30s%16s%30s", "File Name", "File Size", "Last Modified");
		System.out.println();
		for(FTPFile file : moreFiles) {
			System.out.println();
			Calendar timeStamp = file.getTimestamp();
			SimpleDateFormat modify = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String lastModified = modify.format(timeStamp.getTime());
			System.out.format("%30s%16s%30s",file.getName(), file.getSize(), lastModified);
		}
		
		System.out.println();
	*/	
		method.closeFTPConnectionLogout();
	}
	

}
