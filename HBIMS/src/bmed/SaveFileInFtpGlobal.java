package bmed;

/**
 * Developer : Amit Kumar
 * Version : 1.0
 * Purpose : This Java Class is used to save file in FTP Server
 *  
*/


//import hisglobal.tools.imageUtility.JakartaFtpWrapper;
//import investigation.InvestigationStaticConfigurator;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//import com.jscape.inet.ftp.FtpException;
public class SaveFileInFtpGlobal 
{
    /**
     * This Method is used to Save File at FTP Server
     * @param Fileurl
     * @param Ftpurl
     * @param FileName
     * @param inputStream
     * @throws IOException
     * @throws FtpException
     */
	 
	public synchronized static void saveFileToLocation(String Fileurl, String Ftpurl, String FileName,InputStream inputStream) throws IOException, Exception
	{
		
		BufferedOutputStream bos = null ;
		
		try 
		{
			
			System.out.println("Save File To Location:  "+Ftpurl+"/"+FileName);
			URL urlftp =new URL(Ftpurl+"/"+FileName);
			URLConnection urlc=	urlftp.openConnection();
			
			try
			{
				bos=new BufferedOutputStream(urlc.getOutputStream());
				
			}
			catch(Exception ex)
			{
				//System.out.println("catch");
				ex.printStackTrace();
			}
			
			if(bos==null)
			{
				String[] folder=Fileurl.replace("/", "#").split("#");
				//createDirectoryStructure(folder[2],folder);
				bos=new BufferedOutputStream(urlc.getOutputStream());
			}
			
				
			
			byte[] buf = new byte[1024];

			for (int readNum; (readNum = inputStream.read(buf)) != -1;) 
			{
				bos.write(buf, 0, readNum); // no doubt here is 0
				// Writes len bytes from the specified byte array
				// starting at
				// offset off to this byte array output stream.
				
			}
						
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		finally
		{

					
			try
			{
			if(bos!=null)
				bos.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
	}
    
    /*private synchronized static void createDirectoryStructure(String ftpserver, String[] folders) {
		JakartaFtpWrapper ftp = null;
		try {
			 ftp = new JakartaFtpWrapper();
			
			
			 
			
			//String ftpUserName=InvestigationStaticConfigurator.patientfileftpusername; //"ggs";
			//String ftpUserPassword=InvestigationStaticConfigurator.patientfileftppassword; //"ggs123123";
					
			System.out.println("Connecting to " + ftpserver);
			if (ftp.connectAndLogin(ftpserver, ftpUserName, ftpUserPassword))
			{
				System.out.println("Connected to " + ftpserver);
				ftp.setPassiveMode(true);
				ftp.changeWorkingDirectory("ftpserver");
				System.out.println("Present Working Directory :"+ftp.pwd());
				for(int i=4;i<folders.length;i++)
				{
				System.out.println("directory "+folders[i]+" created");
				ftp.mkd(folders[i]);
				ftp.changeWorkingDirectory(folders[i]);
				}
			}
			else 
			{
				System.out.println("Unable to connect to" + ftpserver);
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	
	finally {
		try
		{
			if(ftp!=null)
			{
			ftp.logout();
			ftp.disconnect();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	  }
	}*/
    /**
     * 
     * @param Fileurl
     * @param FileName
     */
    public static void readFileFromLocation(String Fileurl,String FileName) 
    {
		InputStream io=null;
		try
		{
			  URL urlftp =new URL(Fileurl+"/"+FileName);
			  URLConnection urlc=	urlftp.openConnection();
			  io=urlc.getInputStream();
			  DataInputStream in = new DataInputStream(io);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  System.out.println (strLine);
			  }
			  //Close the input stream
			  in.close();
			
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    
	 
	
}
