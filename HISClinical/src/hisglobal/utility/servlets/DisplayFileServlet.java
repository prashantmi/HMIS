package hisglobal.utility.servlets;

import hisglobal.hisconfig.Config;
import hisglobal.utility.HisFileControlUtil;
//import investigationDesk.transactions.controller.utl.MongoXmlHandler;

import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.generictemplate.TemplateDesignerUtility;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler; //Added By VASU on 25-AUG-2017











import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import opd.bo.OpdMasterBO;
import opd.master.controller.data.AddImageUploadMasterDATA;
import opd.master.dao.ImageMasterDAO;


public class DisplayFileServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;

	public DisplayFileServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String winPath = request.getParameter(ServletsUtilityConfig.FILE_PATH_WINDOWS);
		String linuxPath = request.getParameter(ServletsUtilityConfig.FILE_PATH_LINUX);
		String fileName = request.getParameter(ServletsUtilityConfig.FILE_NAME);
		//String imageFile = request.getParameter(ServletsUtilityConfig.IMAGE_FILE);
		//String fileNo = request.getParameter(ServletsUtilityConfig.FILE_NO);
		
		//byte[] getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(fileName);
		
		//Added by Vasu on 27.Dec.2019
		
		String fileNo = OpdMasterBO.getTemplateImageFileNo(fileName);
		
		InputStream is = FTPFileTransfer.retrieveFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, fileNo.replace("//", "/"), "");
		ByteArrayOutputStream os1 = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;

		while ((len = is.read(buffer)) != -1) {
			os1.write(buffer, 0, len);
		}
			byte[] bytes=os1.toByteArray();
			
		
		//byte[] bytes=AddImageUploadMasterDATA.fetchimageFromPostgres(fileNo);
		
		//byte[] bytes= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(fileName);

		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage bImageFromConvert = ImageIO.read(in);

		
		String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE; // <ftpServerUserName>:<ftpServerPassword>@<ftpServerURL>;"ftpmanglagiri:ftp@manglagiri@uatmanglagiri.dcservices.in/opt/ftp/uat_manglagiri";
		String ftpServerUserName = FTPStaticConfigurator.HIS_FTP_SERVER_USERNAME;
		String ftpServerPassword = FTPStaticConfigurator.HIS_FTP_SERVER_PASSWORD;
		
		String strHostOnly=ftpServerURL.substring(ftpServerURL.lastIndexOf("@")+1);
		strHostOnly=strHostOnly.split("/")[0];
		ftpServerUserName=ftpServerUserName.replaceAll("@", "%40");
		ftpServerPassword=ftpServerPassword.replaceAll("@", "%40");
		String path="ftp://"+ftpServerUserName+":"+ftpServerPassword+"@"+strHostOnly+fileNo.replace("//", "/");
		
		
		/*ImageIO.write(bImageFromConvert, "jpg", new File(
				"c:/new-darksouls.jpg"));*/
		/* String path = getServletConfig().getServletContext().getRealPath("/../HIS.war/hisglobal/images/testSave/"+".png");
		File outputfile = new File(path);
        outputfile.createNewFile();
        ImageIO.write(bImageFromConvert , "png", outputfile);
        bImageFromConvert.flush();*/
		
		/*HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, winPath, linuxPath);
        //HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, winPath, imageFile);
		OutputStream os = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		response.setContentType(fileUtil.getFileContentType());
		if(fileUtil.readFile())
		{
		byte[] bytes = fileUtil.getFileContent();*/
		//byte[] bytes=MongoXmlHandler.getInstance().latestFetchFile("331011500000807_3");  Tested by Akash Singh , Dated on 30-09-2015, due to mongoDb changes for Document upload process
		//byte[] getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(fileNo);
		OutputStream os = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		try
		{
			
			if (bytes != null)
			{
				response.setHeader("Pragma", "no-cache");
				bos.write(bytes, 0, bytes.length);
				response.getOutputStream().flush();
				bos.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		}
		/*else
		{
			String msg="<B>This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			bos.write(bytes, 0, bytes.length);
			response.getOutputStream().flush();
			bos.close();
		}
	}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request,response);
	}
}
