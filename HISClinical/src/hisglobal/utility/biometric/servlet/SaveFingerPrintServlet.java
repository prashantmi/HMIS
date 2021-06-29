package hisglobal.utility.biometric.servlet;

import hisglobal.business.GlobalEssentialDelegate;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveFingerPrintServlet extends HttpServlet
{
	public SaveFingerPrintServlet()
	{
		super();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		doGet(req, resp);
		
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		GlobalEssentialDelegate delegate=new GlobalEssentialDelegate();
		if(req.getParameter("mode")==null){
			req.getRequestDispatcher("/hisglobal/utility/biometric/fingerPrintVerification.jsp").forward(req, resp);
		}
		else{
			try
			{
				System.out.println("installing software");
				
				
			}
			
			catch (Exception dex)
			{
				System.out.println("Exception in servlet :" +dex.getMessage());
				dex.printStackTrace();
			}
			
		}
		
	}

	private void installSoftware(String clientOS, String serverOS, String exeFilePath,String remoteMachine) {
		String pathOfBatchFile="";
		if(clientOS.startsWith("Win")){
			clientOS=Config.OS_TYPE_WINDOWS;
		}
		else{
			clientOS=Config.OS_TYPE_LINUX;
		}
		if(System.getProperties().getProperty("os.name").indexOf("Win")!=-1){
			pathOfBatchFile="c://installSoftware";
		}
		else {
			pathOfBatchFile="/installSoftware";
			System.out.println("Linux pathOfBatchFile "+pathOfBatchFile);
		}
		File f=new File(pathOfBatchFile);
		if(!f.exists()){
			f.mkdir();
		}
		pathOfBatchFile=pathOfBatchFile+"/install.bat";
		f=new File(pathOfBatchFile);
		
		try{
			
			System.out.println("getting runtime ");
			Runtime runtime=Runtime.getRuntime();
			String exeName=exeFilePath.split("#")[1]+"\\"+exeFilePath.split("#")[2];
			exeName=exeName.replace("/","\\");
			String fileContent="c:\n "+
			"cd "+f.getParent()+ " \n" +
			"psexec -i \\\\"+remoteMachine +" -c " + "\""+ exeName + "\"" + "\n"
			+"exit";
			char arr[]=fileContent.toCharArray();
			byte [] array=new byte[arr.length];
			for(int i=0;i<arr.length;i++){
				array[i]=(byte)arr[i];
			}
			FileOutputStream fos=new FileOutputStream(f);
			fos.write(array);
			fos.close();
			System.out.println("Executing process");
			System.out.println("pathOfBatchFile "+pathOfBatchFile);
			runtime.exec("cmd /c start "+pathOfBatchFile);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
