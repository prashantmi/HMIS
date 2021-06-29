package Ftp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import DataHelper.PropertiesHelper;





public class FTPCredentialDemo {

	private static void check(FTPClient ftp, String cmd, boolean succeeded) throws IOException {
		if (!succeeded) {
			throw new IOException("FTP error: " + ftp.getReplyString());
		}
	}

	private static String today() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static void uploadfile(String server, String username, String Password, String sourcePath, String destDir) throws IOException {

		FTPClient ftp = new FTPClient();
		ftp.connect(server,PropertiesHelper.getFTPPORT());
		try {
			check(ftp, "login", ftp.login(username, Password));
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setFileTransferMode(ftp.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			System.out.println("Connected to " + server + ".");

			InputStream input = new FileInputStream(sourcePath);
			//PrintWriter writer = new PrintWriter("fileTemp.txt", "UTF-8");
			BufferedWriter out = null;
			
			FileWriter fstream = new FileWriter("output.txt", true); //true tells to append data.
			out = new BufferedWriter(fstream);
			
			try {
				String destination = destDir;
				if (destination.endsWith("/")) {
					destination += today() + "-" + new File(sourcePath).getName();
				}
			boolean storeFile=	ftp.storeFile(destination, input);
				
			if (storeFile) {
				System.out.println("==================");
			}
			else
			{
				System.out.println("=================="+storeFile);
			}
			
			System.out.println("Stored " + sourcePath + " to " + destination + ".");
				//writer.println("Stored "+ destination + ".");
				out.write("\nStored "+ destination + ".");
			} finally {
				if(input != null) {
					input.close();
			    }
				if(out != null) {
			        out.close();
			    }if(fstream != null) {
			    	fstream.close();
			    }
				
			}

			//check(ftp, "logout", ftp.logout());

		} finally {
			ftp.disconnect();
		}
	}

	public static void main(String[] args) throws IOException {
		FTPCredentialDemo upload = new FTPCredentialDemo();
		//upload.uploadfile("10.226.1.173", "ftp_raol", "Raol12345", "E:\\out.pdf", "/opt/ftps/raol_ftp/amit/out.pdf");
		//upload.uploadfile("10.226.1.173", "ftp_raol", "Raol12345", "E:\\out.pdf", "/opt/ftps/raol_ftp/ftpserver/ftpserver/331011800004863/331011800004863_10043_10092018022036.316PM.pdf");
		
		//FTPCredentialDemo.uploadfile("10.226.1.173", "ftp_raol", "Raol12345", "E:\\outPDF.pdf", "/opt/ftps/raol_ftp/ftpserver/ftpserver/331011800023591/331011800023591_10043_10182018115745.373AM.pdf");
	//FTPConnectionURI = ftp://arbareli:FTPlogin&*78@uataiimsraebareli.dcservices.in/ftpserver
		
		//FTPCredentialDemo.uploadfile("uatmanglagiri.dcservices.in", "ftpmanglagiri", "ftp@manglagiri", "E:\\outPDF.pdf", "/opt/ftp/ftpserver/abc.pdf");
		FTPCredentialDemo.upload("10.226.29.45", "chandan", "123456", "E:\\outPDF.pdf");
	
		//FTPCredentialDemo.upload("uatmanglagiri.dcservices.in", "ftpmanglagiri", "ftp@manglagiri", "E:\\outPDF.pdf");
		
	
	}
	   /*public static void main(String[] args) {
	        FTPClient client = new FTPClient();
	        String filename = "E:/outPDF.pdf";

	        // Read the file from resources folder.
	        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	        try (InputStream is = classLoader.getResourceAsStream(filename)) {
	            client.connect("uataiimsraebareli.dcservices.in");
	            client.login("arbareli", "FTPlogin&*78");

	            // Store file to server
	            client.storeFile(filename, is);
	            client.logout();
	            System.out.println("done");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                client.disconnect();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
*/
	public static boolean upload(String server,String username,String password,String sourcePath ){
        boolean Store=false;
        String destinationfile="/Send To Chandan/aaa/aerberily.pdf";
        try{
        	
        	File localfile= new File(sourcePath);
        FTPClient ftp = new FTPClient();
               // ftp.connect(server);
    /* you can use either code which is written above above or below code as ftp port 20 is used for the data transfer and port 21 is used for command and controlls */
           ftp.connect(InetAddress.getByName(server),14147); 
    //here 'server' is your domain name of ftp server or url
                if(!ftp.login(username, password))
                {
                	System.out.println("not logn----------");
                    ftp.logout();
                    return false;
                }
                
            	System.out.println(" logn----------");

          ftp.sendNoOp();//used so server timeout exception will not rise
                int reply = ftp.getReplyCode();
                
            	System.out.println(" logn----------"+reply);

                if (!FTPReply.isPositiveCompletion(reply))
                {
                	System.out.println("fail:");

                    ftp.disconnect();
                    return false;
                }
                
            	System.out.println("true:");

              ftp.enterLocalPassiveMode(); /* just include this line here and your code will work fine */
                InputStream in = new FileInputStream(localfile);
              // ftp.setFileType(ftp.BINARY_FILE_TYPE, ftp.BINARY_FILE_TYPE);
               ftp.setFileType(FTP.BINARY_FILE_TYPE);
               // ftp.setFileTransferMode(ftp.BINARY_FILE_TYPE);
                Store = ftp.storeFile(destinationfile, in);
                
                System.out.println("store"+Store);
                in.close();
             //ftp.disconnect();
     //here logout will close the connection for you
                ftp.logout();

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
        return Store;
    }	

}
