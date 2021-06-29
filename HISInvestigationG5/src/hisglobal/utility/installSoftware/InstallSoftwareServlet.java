package hisglobal.utility.installSoftware;

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

public class InstallSoftwareServlet extends HttpServlet
{
	public InstallSoftwareServlet()
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
		InstallSoftwareVO softwareVO=new InstallSoftwareVO();
		List  softwareVOList=null;
		String mode=req.getParameter("hmode");
		//Enumeration enume=req.getHeaderNames();
		String filePath="";
		String serverOS="";
		String clientOS="";
		if(System.getProperties().getProperty("os.name").indexOf("Win")!=-1){
			serverOS=Config.OS_TYPE_WINDOWS;
		}
		else{
			serverOS=Config.OS_TYPE_LINUX;
		}
		
		if(mode==null || mode.equals("")){
			try
			{
				if(req.getHeader("user-agent").indexOf("Win")!=-1){
					clientOS=Config.OS_TYPE_WINDOWS;
				}
				else{
					clientOS=Config.OS_TYPE_LINUX;
				}
				softwareVO.setClientOS(clientOS);
				softwareVO.setServerOS(serverOS);
				softwareVOList=delegate.getSoftwareList(softwareVO);
				WebUTIL.setAttributeInSession(req, Config.SOFTWARE_LIST_VO, softwareVOList);
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			finally{
				req.getRequestDispatcher("/hisglobal/utility/installSoftware/installSoftware.jsp").forward(req, resp);
			}
			
		}
		else{
			clientOS=req.getParameter("clientOS");
			String exeFilePath=req.getParameter("selectedSoftwareId");
			String pathOfBatchFile="";
			try
			{
				System.out.println("installing software");
				installSoftware(clientOS,serverOS,exeFilePath,req.getRemoteAddr());
				
			}
		
			catch (Exception dex)
			{
				System.out.println("Exception in servlet :" +dex.getMessage());
				dex.printStackTrace();
			}
			finally
			{
				req.getRequestDispatcher("/hisglobal/utility/installSoftware/installSoftware.jsp").forward(req, resp);
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
