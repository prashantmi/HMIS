package opd.transaction.controller.action;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GetFileFromRepositry
 *
 */
 public class GetFileFromRepositry extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GetFileFromRepositry() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//ServletContext sc=getServletContext();
			//String str=sc.getInitParameter("Client");
			//String name=(String)sc.getAttribute(Config.CLIENT);
			//Runtime rn=Runtime.getRuntime();
			//Process pc=rn.exec("cmd /C start C:/test.bat ");
			//File file=new File("C:\\ami\\amit1\\ Budget_log.log");
			
			FileInputStream  fos=new FileInputStream("C:/amit/amit1/Budget_log.log");		
		    byte [] readBytes=new byte[fos.available()];
			fos.read(readBytes);
			fos.close();
			//int i=rn.availableProcessors();
			//rn.exit(i);
			
	//	URL url=new URL("ftp","10.103.0.21",-1,"/SIDFTPrepositry/Oracle - O'Reilly - Oracle PL-SQL Language Pocket Reference.pdf");
		//URL url=new URL("ftp","localhost",-1,"/repositry/06$2007$salslips$118211003[1].pdf");
		//URL url=new URL("ftp://localhost/repositry/06$2007$salslips$118211003[1].zip;type=i");
		//URL url=new URL("ftp://localhost/repositry/A JavaScipt Timer.htm");
			/*URL url=new URL("ftp://10.103.0.21/SIDFTPrepositry/Oracle - O'Reilly - Oracle PL-SQL Language Pocket Reference.pdf;type=i");
			URLConnection uc= url.openConnection();
		String str=uc.getContentType();
			
		InputStream is=uc.getInputStream();
		int i=is.available();
		System.out.println("size==== "+i);
		byte[] bytes=new byte[i];
		int j=0;
		int re;
		while((re=is.read())!=-1){
			bytes[j]=(byte)re;
		}
		
		
		is.read(bytes,0,i);
		System.out.println("");
		FileOutputStream fos=null;
		 File file1=new File("c:\\repo\\file1.pdf");
		 fos=new FileOutputStream(file1);
		 fos.write(bytes);		
		 System.out.println("after writing to file");
		 
		OutputStream os =response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
	//	bytes[0]
		
		
		response.setContentType("application/pdf");
			if(bytes!=null)
			{
					response.setHeader("Pragma","no-cache");		 		
					bos.write(bytes, 0, bytes.length);
					response.getOutputStream().flush();
					bos.close();			
			}*/
			
			//Runtime rm=Runtime.getRuntime();
			OutputStream os =response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			response.setContentType("text/html");
			if(readBytes!=null)
			{
					response.setHeader("Pragma","no-cache");		 		
					bos.write(readBytes, 0, readBytes.length);
					response.getOutputStream().flush();
					bos.close();			
			}
			System.out.println("");
}
catch(Exception e){
	e.printStackTrace();

}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}