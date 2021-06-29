package hisglobal.tools.tag;

import hisglobal.utility.HisUtil;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;





import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.mongodb.MongoXmlHandler;




/**
 * Servlet implementation class for Servlet: ImageRendererServlet
 *
 */
 public class ImageRendererServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   private String strProcessId="11";
	private String strCRNoHospCode = "0";
	private String strFileName="11_Image_01";
	private String isImageStoredFTP="0";
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ImageRendererServlet() 
	{
		super();
	}   	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{		
		this.strProcessId="11";
		this.strCRNoHospCode=request.getParameter("crNo");
		//this.strFileName="11_Image_01";
		this.strFileName="11_Image_"+this.strCRNoHospCode+"_01";
		this.isImageStoredFTP=request.getParameter("isImageStoredFTP");
		//displayPatientImage(request.getParameter("crNo"),"11","11_Image_01",request,response);
		displayPatientImage(request.getParameter("crNo"),"11",this.strFileName,isImageStoredFTP,request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{		
		//displayPatientImage(request.getParameter("crNo"),"11","11_Image_01",request,response);
		displayPatientImage(request.getParameter("crNo"),"11","11_Image_01",isImageStoredFTP,request,response);
	}
//	public String displayPatientImage(String crNo,String processId,String fileName,HttpServletRequest request,HttpServletResponse response)
	public String displayPatientImage(String crNo,String processId,String fileName,String isImageStoredFTP,HttpServletRequest request,HttpServletResponse response)
	{
		OutputStream os = null;
		BufferedOutputStream bos = null;
		FileInputStream fileInStream=null;
		byte[] fileData=null;
		//System.out.println("STEP1");
		HttpSession session = request.getSession();
		//System.out.println("STEP2");
		//HttpServletRequest _request = (HttpServletRequest)getPageContext().getRequest();
		//System.out.println("STEP3");
		//HttpServletResponse response = (HttpServletResponse)getPageContext().getResponse();
		
		//System.out.println("STEP4");
		try
		{
			//System.out.println("STEP5");
			os = response.getOutputStream();
			//System.out.println("STEP6");
			//bos = new BufferedOutputStream(os);
			
				// Setting File Content Type into Response
				response.setContentType("image/jpeg");
				//System.out.println("STEP10");
				//if(!retrieveWriteFile(strProcessId, strFileName, strCRNoHospCode, response))
				if(!retrieveWriteFile(strProcessId, strFileName, strCRNoHospCode,isImageStoredFTP, response))
				{
					//System.out.println("STEP16");
					String msg="<B>This file does not exist:: Please Contact Administrator</B>";
					byte[] bytes=msg.getBytes();
					response.setHeader("Pragma", "no-cache");
					bos.write(bytes, 0, bytes.length);
				}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			String msg="<B>This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String msg="<B>This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		finally
		{
			try
			{
				if(bos!=null)	bos.close();
				if(fileInStream!=null) fileInStream.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
/**
 * Retrieving and Writing File to FTP Server
 * Test Case : Concurrent Folder Structure Creation, File Save and File Modify
 * 
 * @param strProcessId_p	Process ID
 * @param strFileName_p		File Name
 * @param inputStream_p		File Content 
 * @param strCRNoHosp_p		In case of Patient-Wise CRNo otherwise Hospital Code
 * @throws IOException
 */
	//public static boolean retrieveWriteFile(String strProcessId_p, String strFileName_p, String strCRNoHosp_p, HttpServletResponse response_p) throws FileNotFoundException
	public static boolean retrieveWriteFile(String strProcessId_p, String strFileName_p, String strCRNoHosp_p, String isImageStoredFTP,HttpServletResponse response_p) throws FileNotFoundException
	{
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		try
		{
			// Fetching FTP Server Address with Complete URL and User Name: Password
			//String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE;
			//String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE;
			if(isImageStoredFTP.equals("0"))
			{
				OutputStream os = null;
				String docId= strFileName_p;
				MongoXmlHandler.getInstance();
				byte[] getDoc= MongoXmlHandler.latestFetchFile(docId);
				 os = response_p.getOutputStream();
				bos = new BufferedOutputStream(os);
				inputStream = new ByteArrayInputStream(getDoc);
				int c;
				while ((c = inputStream.read()) != -1)
				{
					os.write(c);
				}  
			}
			else
			{
			String ftpServerURL = HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_URL");
			// <ftpServerUserName>:<ftpServerPassword>@<ftpServerURL>;
			//String ftpServerUserName = FTPStaticConfigurator.HIS_FTP_SERVER_USERNAME;
			//String ftpServerPassword = FTPStaticConfigurator.HIS_FTP_SERVER_PASSWORD;
			//System.out.println("STEP11");
			String strFileFtpURL = "ftp://" + ftpServerURL;
			String strFTPFilePath = FTPFileTransfer.getFilePath(strProcessId_p, strCRNoHosp_p);
			//System.out.println("STEP12");
			URL urlftp = new URL(strFileFtpURL + strFTPFilePath + "/" + strFileName_p);
	
			URLConnection urlc = urlftp.openConnection();
			//System.out.println("STEP13");
			urlc.connect();
			//System.out.println("STEP14");
			OutputStream os = response_p.getOutputStream();
			bos = new BufferedOutputStream(os);
			//System.out.println("STEP15");
			inputStream = urlc.getInputStream();
			
			int c;
			while ((c = inputStream.read()) != -1)
			{
				bos.write(c);
			}    
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(inputStream!=null) inputStream.close();
				if(bos!=null)	bos.close();
			}
			catch(Exception e)
			{
			}
		}
		return true;
	}
}