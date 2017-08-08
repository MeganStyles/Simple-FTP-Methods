package testTime;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.*;

public class FTPActions {
	
	FTPClient ftpClientXLight = new FTPClient();
	
	//Connect and login. If anonymous login use strUserName = "anonymous" strPassword = ""
	public void connectToFTPServer (String strServerIP, int intPort, String strUserName, String strPassword) {
		
		try {
			ftpClientXLight.connect(strServerIP, intPort);
			
			boolean login = ftpClientXLight.login(strUserName, strPassword);
			
			if (login) {
				System.out.println("Connection Extablished...");
				System.out.println("Status: " + ftpClientXLight.getStatus());
				System.out.println("Status: " + ftpClientXLight.getReplyCode());
			}else {
				System.out.println("Connection Failed...");
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeFTPConnectionLogout() {
		try {
			boolean logout = ftpClientXLight.logout();
			
			if(logout) {
				System.out.println("Connection closed...");
				
			}else {
				System.out.println("Connection Failed...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ftpClientXLight.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getlistFTPFiles() {
		try {
			FTPFile[] files = ftpClientXLight.listFiles();
			System.out.format("%30s%16s%30s", "File Name", "File Size", "Last Modified");
			System.out.println();
			for(FTPFile file : files) {
				if(file.getType()==FTPFile.FILE_TYPE) {
					System.out.println();
					Calendar timeStamp = file.getTimestamp();
					SimpleDateFormat modify = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					String lastModified = modify.format(timeStamp.getTime());
					System.out.format("%30s%16s%30s",file.getName(), file.getSize(), lastModified);
				}
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}