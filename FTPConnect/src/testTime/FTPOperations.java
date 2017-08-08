package testTime;

import java.io.File;
import org.junit.Assert;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;



public class FTPOperations {
	
	Boolean loginCompleted = false;
	String loginFail = "You have failed to log in to the FTP server.";
	FTPClient ftp = new FTPClient();
	Boolean actionCompleted = false;
	FTPFile[] fileList = null;
	FTPFile yourFile = null;
	String workingDirectory = null;
	
	
	public boolean connectToFTPServer(String strServerIP, int intPortNumber, String strUsername, String strPassword) {
		try {
			ftp.connect(strServerIP, intPortNumber);
			loginCompleted = ftp.login(strUsername, strPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginCompleted;
	}

	public boolean closeFTPConnectionLogout() {
		if (loginCompleted) {
			try {
				actionCompleted = ftp.logout();
				ftp.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return actionCompleted;
	}
	
	
	public FTPFile[] getListOfFiles(String strRemoteFolderPath) {
		if (loginCompleted) {
			try {
				fileList = ftp.listFiles(strRemoteFolderPath);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return fileList;
	}
	
	public boolean uploadFileToServer(String strLocalFolderPath, String strRemoteFolderPath, String strFileName ) {
		if(loginCompleted) {
			try {
				this.setWorkingDirectory(strRemoteFolderPath);
				FileInputStream inputStream = null;
				inputStream = new FileInputStream(strLocalFolderPath);
				actionCompleted = ftp.storeFile(strFileName, inputStream);
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return actionCompleted;
	}
	
	public FTPFile getFileWithFileNameExt(String strRemoteFolderPath, String strFileName) {
		if (loginCompleted) {
			this.setWorkingDirectory(strRemoteFolderPath);;
			try {
				fileList = ftp.listFiles(strFileName);
				yourFile = fileList[0];
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return yourFile;
	}
	
	public FTPFile[] getFileWithFileName(String strRemoteFolderPath, String strFileName) {
		if (loginCompleted) {
			FTPFile[] allFiles = this.getListOfFiles(strRemoteFolderPath);
			int maxNumberOfFilesInDirectory = allFiles.length;
			fileList = new FTPFile[maxNumberOfFilesInDirectory];
			int x = 0;
			for (FTPFile file : allFiles) {
				String fileName = file.getName();
				if (fileName.contains(strFileName)) {
					fileList[x] = file;
					x++;
				}
			}
		} else {
			Assert.fail(loginFail);
		}
		return fileList;
	}
		
	public FTPFile[] getFileWithPartialFileName(String strRemoteFolderPath, String strSearchWord) {
		if (loginCompleted) {
			this.setWorkingDirectory(strRemoteFolderPath);
			FTPFile[] allFiles = this.getListOfFiles(strRemoteFolderPath);
			int maxNumberOfFilesInDirectory = allFiles.length;
			fileList = new FTPFile[maxNumberOfFilesInDirectory];
			int x = 0;
			for (FTPFile file : allFiles) {
				String fileName = file.getName();
				if (fileName.contains(strSearchWord)) {
					fileList[x] = file;
					x++;
				}
			}
		} else {
			Assert.fail(loginFail);
		}
		return fileList;
	}
	
	public boolean downloadFileFromServer(String strLocalFolderPath, String strRemoteFolderPath, String strFileName) {
		if (loginCompleted) {
			try {
				this.setWorkingDirectory(strRemoteFolderPath);
				File downloadFile = new File(strLocalFolderPath + "\\" + strFileName);
				OutputStream fos = new FileOutputStream(downloadFile);
				actionCompleted = ftp.retrieveFile(strFileName, fos); 
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return actionCompleted;
	}
	
	
	public boolean deleteFileFromServer(String strRemoteFolderPath, String strFileName) {
		if (loginCompleted) {	
			try {
				this.setWorkingDirectory(strRemoteFolderPath);
				actionCompleted = ftp.deleteFile(strFileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return actionCompleted;
	}

	public boolean renameFileOnServer(String strRemoteFolderPath, String strOldFileName, String strNewFileName) {
		if (loginCompleted) {
			try {
				this.setWorkingDirectory(strRemoteFolderPath);
				actionCompleted = ftp.rename(strOldFileName, strNewFileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return actionCompleted;
	}
	
	public boolean createDirectoryOnServer(String strRemoteFolderPath, String strNewDirectoryName) {
		if (loginCompleted) {
			try {
				this.setWorkingDirectory(strRemoteFolderPath);
				actionCompleted = ftp.makeDirectory(strNewDirectoryName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return actionCompleted;
	}
	
	public boolean deleteDirectoryOnServer(String strRemoteFolderPath) {
		if (loginCompleted) {	
			try {
				actionCompleted = ftp.removeDirectory(strRemoteFolderPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return actionCompleted;
	}
	
	public String getWorkingDirectory() {
		if (loginCompleted) {	
			try {
				workingDirectory = ftp.printWorkingDirectory();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return workingDirectory;
	}
	
	//using a backslash before directory name will move from root. No back slash will move from current directory.
	public String setWorkingDirectory(String strRemoteFolderPath) {
		if (loginCompleted) {	
			try {
				ftp.changeWorkingDirectory(strRemoteFolderPath);
				workingDirectory = ftp.printWorkingDirectory();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Assert.fail(loginFail);
		}
		return workingDirectory;
	}
}
