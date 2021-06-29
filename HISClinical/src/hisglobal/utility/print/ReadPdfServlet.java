/* *******************************************************************************************
 * reads a PDF file using FileInputStream and 
 * then write in ByteArrayOutputStream 
 * the bufferArrayOutputStream is converted to byteArray 
 * and then write to servletOutputStream
 **********************************************************************************************/

package hisglobal.utility.print;

import hisglobal.hisconfig.Config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadPdfServlet extends HttpServlet
{
	public ReadPdfServlet()
	{
		super();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		doGet(req, resp);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..do post");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		if(req.getParameter("mode")!=null && req.getParameter("mode").equals("1")){
			PrintUTIL.createPdfFromHtml(req.getParameter("htmlCode"));
			System.out.println("Copies :"+req.getParameter("copies"));
			req.getRequestDispatcher("/hisglobal/utility/print/printDialog.jsp").forward(req, resp);
			
		}else{	
			String filePath="";
			try
			{
				if(req.getParameter("path")==null){
					if(System.getProperties().getProperty("os.name").indexOf("Win")!=-1){
						filePath=Config.PRINT_PDF_FILE_PATH_WINDOWS;
					}
					else {
						filePath=Config.PRINT_PDF_FILE_PATH_LINUX;
					}
				}
				else
					filePath=req.getParameter("path");
				String fileName="";
				if(req.getParameter("fileName")==null)
					fileName=Config.PRINT_PDF_FILE_NAME;
				else
					fileName=req.getParameter("fileName");	
				
				System.out.println("File Name :"+ filePath +"\\" + fileName);
				File f=new File(filePath+"/"+fileName);
				System.out.println("Starting reading File");
				FileInputStream fis=new FileInputStream(f);
				int contentLength = fis.available();
				ByteArrayOutputStream tmpOut;
				    if (contentLength != -1) {
				        tmpOut = new ByteArrayOutputStream(contentLength);
				    } else {
				        tmpOut = new ByteArrayOutputStream(16384); 
				    }
	
				byte[] buf = new byte[512];
			    while (true) {
			        int len = fis.read(buf);
			        if (len == -1) {
			            break;
			        }
			        tmpOut.write(buf, 0, len);
			    }
			    fis.close();
			    tmpOut.close(); 
	
			    byte[] array = tmpOut.toByteArray();
				
				ServletOutputStream sos= resp.getOutputStream();
				sos.write(array);
				sos.close();
				/*	System.out.println("Path>>>>>>>>>>>>>>>>>>>>>>>>>>>> : "+ new PrintApplet().getCodeBase());
				File f=new File("c:/print.pdf");
				FileOutputStream fos=new FileOutputStream(f);
				baosPDF.writeTo(fos);
				fos.close();*/
			}
			catch (Exception dex)
			{
				System.out.println("Exception in servlet :" +dex.getMessage());
				dex.printStackTrace();
			}
			finally
			{
				
			}
			
		}
		
			
		
	}//end doGet
	
}//end class
