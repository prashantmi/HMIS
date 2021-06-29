package Ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUploader {

	FTPClient ftp = null;
	
	public FTPUploader(String host, String user, String pwd) throws Exception{
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(user, pwd);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
	}
	
	public void uploadFile(String localFileFullName, String fileName, String hostDir)
			throws Exception {
		try(InputStream input = new FileInputStream(new File(localFileFullName))){
		this.ftp.storeFile(hostDir + fileName, input);
		}
	}

	public void disconnect(){
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException f) {
				// do nothing as file is already saved to server
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Start");
		FTPUploader ftpUploader = new FTPUploader("10.226.1.173", "ftp_raol", "Raol12345");
		//FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
		// files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
		ftpUploader.uploadFile( "E:\\out.pdf", "out.pdf", "/opt/ftps/raol_ftp/ftpserver/ftpserver/");
		//ftpUploader.uploadFile("E:\\out.pdf", "331011800004863_10043_10092018022036.316PM.pdf", "ftp://ftp_raol:Raol12345@10.226.1.173/ftpserver/ftpserver/331011800004863/");
		//ftpUploader.uploadFile("E:\\out.pdf", "331011800004863_10043_10092018022036.316PM.pdf", "/ftpserver/ftpserver/331011800004863/");
		//ftpUploader.uploadFile("E:\\out.pdf", "331011800004863_10043_10092018022036.316PM.pdf", "/amit/");
		
		ftpUploader.disconnect();
		System.out.println("Done");
	}
	
}
