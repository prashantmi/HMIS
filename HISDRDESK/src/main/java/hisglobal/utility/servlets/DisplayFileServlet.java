package hisglobal.utility.servlets;

import hisglobal.hisconfig.Config;
import hisglobal.utility.HisFileControlUtil;
//import investigationDesk.transactions.controller.utl.MongoXmlHandler;

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
		byte[] bytes= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(fileName);

		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage bImageFromConvert = ImageIO.read(in);

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
