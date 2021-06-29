package mongoapp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Endpoint;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DataHelper.JakartaFTPWrapper;
import DataHelper.PropertiesHelper;
import Ftp.JakartaFtpWrapper;

public class demo1 {

	
	public static void main(String[] args) throws IOException {

		
		createUpdatePatientFile();
		//InputStream inp=null;
		//uploadFile("11", "a.xml",inp , "379131900017199") ;
		
		//Endpoint.publish("http://localhost:7779/ws/hello", new InvReportService());  
		
	}	
	





public static String uploadFile(String strProcessId_p, String strFileName_p, InputStream inputStream_p, String strCRNoHosp_p) throws IOException
{
	BufferedOutputStream bos = null;
	String fileLocation="ftpserver";
	String filePath = null;
	try
	{
		// Fetching FTP Server Address with Complete URL and User Name: Password
		/*String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE; // <ftpServerUserName>:<ftpServerPassword>@<ftpServerURL>;
		String ftpServerUserName = FTPStaticConfigurator.HIS_FTP_SERVER_USERNAME;
		String ftpServerPassword = FTPStaticConfigurator.HIS_FTP_SERVER_PASSWORD;*/
		String ftpServerURL = "arbareli:FTPlogin&*78@uataiimsraebareli.dcservices.in:21"; // <ftpServerUserName>:<ftpServerPassword>@<ftpServerURL>;"ftpmanglagiri:ftp@manglagiri@uatmanglagiri.dcservices.in/opt/ftp/uat_manglagiri";
		String ftpServerUserName = "arbareli";
		String ftpServerPassword = "FTPlogin&*78";
		
		

		String strFileFtpURL = "ftp://" + ftpServerURL;
		//String strFTPFilePath = getFilePath(strProcessId_p, strCRNoHosp_p);
		
		
		/*String year=    strCRNoHosp_p.substring(5,7);
        
        String insideyear=getInsideYear(strCRNoHosp_p);
        String count=getcount(strCRNoHosp_p);
         
         String saveStatusFTP = null;*/
        /* String pdfFtpurl = PropertiesHelper.getFTPConnectionURI() +"/"+resultEntryVOGroupByValidatedByObj.getHospitalCode() +"/"+"20"+year+"/"+insideyear+"/"+resultEntryVOGroupByValidatedByObj.getPatCRNo();
         String pdfFileurl= PropertiesHelper.getFTPConnectionURI() +"/"+resultEntryVOGroupByValidatedByObj.getHospitalCode() +"/"+"20"+ year+"/"+insideyear+"/"+resultEntryVOGroupByValidatedByObj.getPatCRNo();
         System.out.println("pdfFileurl"+pdfFileurl);*/
         
//         String strFTPFilePath = ftpServerURL +"/"+strCRNoHosp_p.substring(0,5) +"/"+"20"+year+"/"+insideyear+"/"+count+"/"+strCRNoHosp_p;
//         String pdfFileurl= ftpServerURL +"/"+strCRNoHosp_p.substring(0,5) +"/"+"20"+ year+"/"+insideyear+"/"+count+"/"+strCRNoHosp_p;
		
         String strFTPFilePath =getFilePath(strProcessId_p, strCRNoHosp_p);
         System.out.println("strFTPFilePath========"+strFTPFilePath);
         
		//String folderPath="ftpserver"+strFTPFilePath;
         String folderPath=strFTPFilePath;
//		URL urlftp = new URL(strFTPFilePath + "/" + strFileName_p);
		
		String[] folder = folderPath.replace("/", "#").split("#");
		String newfpppath="";
		for(int i=0;i<folder.length;i++){
			//fileLocation = folder[i]+"/";
			if(!folder[i].trim().equals(""))
				fileLocation = fileLocation+"/"+folder[i];
		}
		fileLocation = fileLocation+ "/" + strFileName_p;
		 filePath=folderPath+"/"+strFileName_p;
		
		 URL urlftp = new URL(strFileFtpURL + strFTPFilePath + "/" + strFileName_p);
		// URL urlftpnew = new URL(strFileFtpURL + strFTPFilePath);
         System.out.println("urlftp============="+urlftp);
		
         
		URLConnection urlc = urlftp.openConnection();

		try
		{
			bos = new BufferedOutputStream(urlc.getOutputStream());
		}
		catch (Exception ex)
		{
			System.out.println("Folder Structure Not Created or Not a Vlaid FTP Server");
			//ex.printStackTrace();
		}

		if (bos == null)
		{
			//String[] folder = folderPath.replace("/", "#").split("#");
			
			/*for(int i=0;i<folder.length;i++){
				fileLocation = fileLocation+"/"+folder[i];
			}*/
			// fileLocation = fileLocation+ "/" + strFileName_p;
			
			String strFileFtpServerURLOnly = "";
			
			if (strFileFtpURL.contains("@")) 
				strFileFtpServerURLOnly = ftpServerURL.substring(ftpServerURL.lastIndexOf("@")+1);
			else 
				strFileFtpServerURLOnly = ftpServerURL;
				System.out.println("strFileFtpServerURLOnly========"+strFileFtpServerURLOnly);
			//folder[0] = strFTPFilePath.split("/")[1];
			createFTPDirectoryStructure(strFileFtpServerURLOnly, ftpServerUserName, ftpServerPassword, folder);
				//createFTPDirectoryStructure("", folder);
			System.out.println("urlftp============="+urlftp);
			//urlc = urlftp.openConnection();
			//urlc = urlftpnew.openConnection();
			
			bos = new BufferedOutputStream(urlc.getOutputStream());
		}

		byte[] buf = new byte[1024];

		for (int readNum; (readNum = inputStream_p.read(buf)) != -1;)
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
			if (bos != null) bos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	return filePath;
}



public static String getFilePath(String strProcessId_p, String strCRNoHosp_p)
{
	String strFilePath="";
	try{
		
		String year=    strCRNoHosp_p.substring(5,7);
		strFilePath = "/ftpserve11/"+strCRNoHosp_p.substring(0,5)+"/" +strProcessId_p  + "/"+"20"+year+"/" + strCRNoHosp_p;
		System.out.println(strFilePath);
		
	}
	catch(Exception e)
	{
		strFilePath = "";
		e.printStackTrace();
	}
	return strFilePath;
}



private static void createFTPDirectoryStructure(String strFTPHost_p, String strFTPServerUserName_p, String strFTPServerPassword_p,
		String[] arrFolders_p)
{
	JakartaFTPWrapper ftp = null;
	String strFTPHostPath="";
	try
	{
		//strFTPHost_p="ftpmanglagiri:ftp@manglagiri@uatmanglagiri.dcservices.in/opt/ftp/uat_manglagiri";
		ftp = new JakartaFTPWrapper();
		String strFTPHostOnly = strFTPHost_p.split("/")[0];
		
		//if(strFTPHost_p.split("/")[1]!=null)
		for(int i=1;i<strFTPHost_p.split("/").length;i++)
		 strFTPHostPath = strFTPHostPath+"/"+strFTPHost_p.split("/")[i];
		
		

		System.out.println("Connecting to Host" + strFTPHostOnly + "  Host Path :"+ strFTPHostPath +" UN:"+strFTPServerUserName_p+" PW:"+strFTPServerPassword_p);
		if (ftp.connectAndLogin("uataiimsraebareli.dcservices.in", strFTPServerUserName_p, strFTPServerPassword_p))
		{
			System.out.println();
			 System.out.println("Connected to " + strFTPHostOnly);
		//	ftp.changeWorkingDirectory(strFTPHostPath);
			 System.out.println("Move to " + strFTPHostPath);
		//	ftp.setPassiveMode(true);
			// System.out.println("Present Working Directory :" + ftp.pwd());
			for (int i = 0; i < (arrFolders_p.length); i++)
			{
				if(arrFolders_p[i]!=null && !arrFolders_p[i].trim().equals(""))
				{
					// System.out.println("directory " + arrFolders_p[i] + " created");
					int result = ftp.mkd(arrFolders_p[i]);
					ftp.changeWorkingDirectory(arrFolders_p[i]);
					System.out.println("FTP Folder Created Succesfully :: " + arrFolders_p[i] + " :: " + result);
				}
			}
			System.out.println("FTP Folder Structure Succesfully Created");
		}
		else
		{
			throw new Exception("Can't Login to FTP Server");
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if (ftp != null)
			{
				ftp.logout();
				ftp.disconnect();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}




private synchronized static void createFTPDirectoryStructure(String ftpserver, String[] folders) {
	try {
		
		JakartaFtpWrapper ftp = new JakartaFtpWrapper();
		/*String ftpUserName=InvestigationStaticConfigurator.patientfileftpusername;
		String ftpUserPassword=InvestigationStaticConfigurator.patientfileftppassword;*/
		
		String ftpUserName="ftpmanglagiri";
		String ftpUserPassword="ftp@manglagiri";
		
		if (ftp.connectAndLogin("uatmanglagiri.dcservices.in", ftpUserName, ftpUserPassword))
		{
			try 
			{
				//ftp.setPassiveMode(true);
			//	ftp.changeWorkingDirectory(folders[3]);
				for(int i=3;i<folders.length;i++)
				{
					if(!folders[i].equals(""))
					{
					ftp.mkd(folders[i]);
					ftp.changeWorkingDirectory(folders[i]);
					}
					
					}
				
			} 
			catch (Exception ftpe)
			{
				ftpe.printStackTrace();
			}
			finally 
			{
				ftp.logout();
				ftp.disconnect();
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
	finally
	{
		
	}
}





private synchronized static void createFTPDirectoryStructure11(String ftpserver, String[] folders) {
	try {
		
		JakartaFtpWrapper ftp = new JakartaFtpWrapper();
		/*String ftpUserName=InvestigationStaticConfigurator.patientfileftpusername;
		String ftpUserPassword=InvestigationStaticConfigurator.patientfileftppassword;*/
		
		String ftpUserName=PropertiesHelper.getFTPConnectionUsername();
		String ftpUserPassword=PropertiesHelper.getFTPConnectionPassword();
		
		if (ftp.connectAndLogin("uataiimsraebareli.dcservices.in", ftpUserName, ftpUserPassword))
		{
			try 
			{
				//ftp.setPassiveMode(true);
			//	ftp.changeWorkingDirectory(folders[3]);
				for(int i=0;i<folders.length;i++)
				{
					ftp.mkd(folders[i]);
					ftp.changeWorkingDirectory(folders[i]);
				}
				
			} 
			catch (Exception ftpe)
			{
				ftpe.printStackTrace();
			}
			finally 
			{
				ftp.logout();
				ftp.disconnect();
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
	finally
	{
		
	}
}



public static BufferedOutputStream createUpdatePatientFile()
	{
   	 // Calendar calendar = Calendar.getInstance();
   	  
		boolean directoryExists=true;
		//String directoryMethod=InvestigationStaticConfigurator.patientcreatefileftpaddress+"/"+directory;
		//String directoryMethod=PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+""+"20"+year+"/"+insideyear+ "/"+directory;
		String directoryMethod=PropertiesHelper.getFTPConnectionURI()+"/"+"5555";
		
		BufferedOutputStream bos = null ;
		URL urlftp=null;
		URLConnection urlc=null;
		try
		{
		//urlftp =new URL(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
			//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+""+"20"+year+"/"+insideyear+"/"+directory+"/"+directory+".xml");
			
					
			urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+"5555/"+"a.xml");
			urlc=	urlftp.openConnection();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		/*try
		{
			
			bos=new BufferedOutputStream(urlc.getOutputStream());
			
		}
		catch(Exception ex)
		{
			System.out.println("<!-- createUpdatePatientFile::CreateRequisitionDirectory -->");
			ex.printStackTrace();
		}*/
		
		if(bos==null)
		{
			System.out.println("BOS is null");
			String[] folder=directoryMethod.replace("/", "#").split("#");
			
			
			if(folder[2]!=null && folder[2].contains("%40") && folder[2].replace("@", "#").split("#").length>1)
			{
				String data=folder[2];
				data=data.replaceAll("%40", "@");
				createFTPDirectoryStructure(data.replace("@", "#").split("#")[2],folder);
			}
			else if(folder[2]!=null && folder[2].replace("@", "#").split("#").length>1)
			{
				String data=folder[2];
				data=data.replaceAll("%40", "@");
				createFTPDirectoryStructure(data.replace("@", "#").split("#")[1],folder);
			}
			else
			{
				createFTPDirectoryStructure(folder[2],folder);
			}
			System.out.println("File Structure created");
			//createBlankXmlFile(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
			System.out.println("File XML created");
			try
			{
			//urlftp =new URL(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
			//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+ "20"+year+"/"+insideyear+"/"+directory+"/"+directory+".xml");
		   	//	URLConnection urlc1=null;

				//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+directory+"/"+directory+".xml");
			
		//	urlc1=	urlftp.openConnection();
			bos=new BufferedOutputStream(urlc.getOutputStream());
			System.out.println("BOS reconstructed XML created");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
				
		return bos;
		
	}


}
