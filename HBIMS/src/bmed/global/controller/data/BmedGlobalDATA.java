package bmed.global.controller.data;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import bmed.EMMSStaticConfigurator;
import bmed.SaveFileInFtpGlobal;
import bmed.global.controller.util.BmedConfigUtil;

public class BmedGlobalDATA 
{
	public static String uploadFile(FormFile fileName,String strMode,String hospCode, String strChildFolderName)throws Exception
	{
		String strFileName = "";
		String sessionFtpAddress = EMMSStaticConfigurator.bmedpath;
		String strRootFolderName = "";

		BmedConfigUtil bmed = null;

		try{
			if(fileName.getFileName().length()!=0)
			{
				System.out.println("File Uploading Start........");
				bmed = new BmedConfigUtil();
				strRootFolderName = bmed.getStrFtpFileFolder(strMode,hospCode);
				strFileName = fileName.getFileName();
	
				// String Fileurl= "ftp://"+sessionFtpAddress+"/"+logicalName+"/"+strFtpFolderName;
				String Fileurl = "ftp://" + sessionFtpAddress + "/" + strRootFolderName+ "/" + strChildFolderName;
				String Ftpurl = "ftp://" + sessionFtpAddress + "/" + strRootFolderName+ "/" + strChildFolderName;
				
				System.out.println("File Name :" + fileName.getFileName());
				System.out.println("File Size :" + fileName.getFileSize());
				System.out.println("File name Length : " + fileName.getFileName().length());
				System.out.println("Ftp Url::::" + Ftpurl);
				System.out.println("File Url:::::" + Fileurl);
				System.out.println("File Name::::" + strFileName);
	
				SaveFileInFtpGlobal.saveFileToLocation(Fileurl, Ftpurl,strFileName, fileName.getInputStream());
	
				System.out.println("File Uplaoded Successfully");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("File Uplaoding Failed.");
			strFileName = "";
		}
		return strFileName;
	}
	   public static void getUploadedFile(String strFileName,String strChildFolderName,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			String sessionFtpAddress = EMMSStaticConfigurator.bmedpath;
			String strRootFolderName = "";
			File f = null;
			FileInputStream fis = null;
			byte[] fileContent = new byte[1024];
			UserVO userVo = null;
			BmedConfigUtil bmed = new BmedConfigUtil();
			try 
			{
				System.out.println("File Downloading Start.....");
				
				userVo = ControllerUTIL.getUserVO(request);
				String hospCode = userVo.getHospitalCode();
				strRootFolderName = bmed.getStrFtpFileFolder("15", hospCode);

				String[] strTemp = strFileName.replace(".", "#").split("#");
				String strExt = strTemp[strTemp.length - 1];
				
				 if (strExt.equalsIgnoreCase("txt")
						|| strExt.equalsIgnoreCase("txt")) {
					
					response.setContentType("application/txt");
					response.setHeader("Content-disposition",
							" filename="+strFileName);
				}
				 else if (strExt.equalsIgnoreCase("pdf")) 
				{
	                
					response.setContentType("application/pdf");
					response.setHeader("Content-disposition",	"attachment; filename="+strFileName);

				} else if (strExt.equalsIgnoreCase("html")
						|| strExt.equalsIgnoreCase("htm")) {
					
					response.setContentType("text/html;charset=utf-8");
					response.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				}else if (strExt.equalsIgnoreCase("xml")) {
					
					response.setContentType("application/xml");
					response.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				} else if (strExt.equalsIgnoreCase("doc") || strExt.equalsIgnoreCase("docx")) {
					
					response.setContentType("application/msword");
					response.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
				} else if (strExt.equalsIgnoreCase("rdf")) {
					
					response.setContentType("application/msword");
					response.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				} else if (strExt.equalsIgnoreCase("rtf")) {
					
					response.setContentType("application/msword");
					response.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				}else if((strExt.equalsIgnoreCase("jpg"))||(strExt.equalsIgnoreCase("jpeg"))||(strExt.equalsIgnoreCase("png"))||
						(strExt.equalsIgnoreCase("gif"))||(strExt.equalsIgnoreCase("bmp"))){
					response.setContentType("application/jpeg");
					response.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
				}
				else {

					response.setContentType("text/html;charset=utf-8");
					response.setHeader("Content-disposition",
							"attachment; filename="+strFileName);
					
				}
				/*******************************************************************/

				String Fileurl= "ftp://"+sessionFtpAddress+"/"+strRootFolderName+"/"+strChildFolderName;			
				 
				System.out.println("test:::"+Fileurl);
				 
				URL                  urlftp = new URL(Fileurl+"/"+strFileName);
				URLConnection          urlc =	urlftp.openConnection();
				InputStream              io = urlc.getInputStream();
				 
				FileOutputStream fos = new FileOutputStream(strFileName);
				byte[] buf = new byte[4096];
				int read = 0;
				while ((read = io.read(buf)) > 0) 
				{
				      fos.write(buf, 0, read);
				}	    				  				  	  
				fos.close();
				f = new File(strFileName);
				if (!f.isFile()) 
				{
					throw new Exception("Invalid File Path");
				}
				fis = new FileInputStream(f);
				while (fis.read(fileContent) != -1) 
				{
					response.getOutputStream().write(fileContent);
				}
				if(fis!=null){
					fis.close();
					fis = null;
				}
				System.out.println("File Downloaing Sucessfull .....!");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("File Downloaing Failed .....!");
			}
		}
}
