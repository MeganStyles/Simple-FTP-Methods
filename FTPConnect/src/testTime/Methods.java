package testTime;

import java.awt.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Methods {
	
	FTPClient ftp = new FTPClient();
	
	public boolean connectToFTPServer(String strServerIP, int intPortNumber, String strUsername, String strPassword) {
		boolean login = false;
		try {
			ftp.connect(strServerIP, intPortNumber);
			login = ftp.login(strUsername, strPassword);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return login;
	}

	public boolean closeFTPConnectionLogout() {
		boolean logout = false;
		try {
			logout = ftp.logout();
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logout;
	}
	
	public FTPFile[] viewListOfFiles(String strDirectoryPath) {
		FTPFile[] files = null;
		try {
			files = ftp.listFiles(strDirectoryPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}
	
	public boolean uploadFileToServer(String strFilePath, String strNewFileName ) {
		boolean uploaded = false;
		try {
			FileInputStream inputStream = null;
			inputStream = new FileInputStream(strFilePath);
			uploaded = ftp.storeFile(strNewFileName, inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploaded;
	}	
	
	public FTPFile[] getSpecificFileInWorkingDirectory(String strFileName) {
		FTPFile[] yourFile = new FTPFile[1];
		try {
			yourFile = ftp.listFiles(strFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yourFile;
	}
	
	//how to do this with list?? So I don't need to make a massive array for no reason.
	public FTPFile[] searchForFileInDirectory(String strDirectoryPath, String strSearchWord) {
		FTPFile[] allFiles = this.viewListOfFiles(strDirectoryPath);
		FTPFile[] foundFile = new FTPFile[100];
		//List<FTPFile> foundFile = new ArrayList<FTPFile>();
		int x = 0;
		for (FTPFile file : allFiles) {
			String fileName = file.getName();
			if (fileName.contains(strSearchWord)) {
				foundFile[x] = file;
				x++;
			}
		}

		return foundFile;
	}
	
	public boolean downloadFileFromServer(String strServerFileName, String strLocalDestination, String strNewFileName) {
		boolean download = false;
		try {
			File downloadFile = new File(strLocalDestination + "\\" + strNewFileName);
			OutputStream fos = new FileOutputStream(downloadFile);
			download = ftp.retrieveFile(strServerFileName, fos); 
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return download;
	}
	
	public boolean deleteFileFromServer(String strFileNamePath) {
		boolean deleted = false;
		try {
			deleted = ftp.deleteFile(strFileNamePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted;
	}

	public boolean renameFileOnServer(String strOldFileName, String strNewFileName) {
		boolean renamed = false;
		try {
			renamed = ftp.rename(strOldFileName, strNewFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return renamed;
	}
	
	public void createTxtFileOnServer(String strLocalFilePath, String strDataToWrite, boolean boolInsertToNewLine,) {
		TXTReadWrite write = new TXTReadWrite();
		write.writeToFile(strLocalFilePath, strDataToWrite, boolInsertToNewLine);
		
		//call the create file method from file resources.
		//call the upload file method from this class.
	}
	
	public boolean createDirectoryOnServer(String strDirectoryPath, String strNewDirectoryName) {
		boolean makeDir = false;
		try {
			makeDir = ftp.makeDirectory(strNewDirectoryName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return makeDir;
	}
	
	public boolean deleteDirectoryOnServer(String strDirectoryName) {
		boolean removed = false;
		try {
			removed = ftp.removeDirectory(strDirectoryName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return removed;
	}
	
	public String getCurrentDirectory() {
		String directory = null;
		try {
			directory = ftp.printWorkingDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return directory;
	}
	
	//using a backslash before directory name will move from root. No back slash will move from current directory.
	public String changeWorkingDirectory(String strDirectoryPathName) {
		String workingDirectory = null;
		try {
			ftp.changeWorkingDirectory(strDirectoryPathName);
			workingDirectory = ftp.printWorkingDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workingDirectory;
	}
	
	/*
	 * this is not useful. File is never used. Only file path.
	public File getServerFile(String strServerFileName, String strLocalDestination, String strNewFileName) {
		
		this.downloadFileFromServer(strServerFileName, strLocalDestination, strNewFileName);
		File file = new File(strLocalDestination);
		return file;
	}
	*/
	
	public boolean EditServerFile(String strLocalFilePath, String strDataToWrite, boolean boolInsertToNewLine, String strServerFileName) {
		
		this.downloadFileFromServer(strServerFileName, strLocalFilePath, strServerFileName);
		TXTReadWrite write = new TXTReadWrite();
		String filePath = strLocalFilePath + "//" + strServerFileName;
		try {
			write.writeToFile(filePath, strDataToWrite, boolInsertToNewLine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.deleteFileFromServer(strServerFileName);
		return this.uploadFileToServer(filePath, strServerFileName);
	}





}
