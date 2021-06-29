package application.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import hisglobal.config.HISConfig;
import hisglobal.utility.HisUtil;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;

import javax.servlet.ServletContext;

public class HISApplicationConfig extends HISConfig
{
	private static Map<String, Object> mapURLWiseAllowedActionURIs;
	
	public boolean setApplictaionInitials(ServletContext objContext)
	{
		boolean flg = true;
		try
		{
			System.out.println("Initializing Applictaion Context --> " + objContext.getContextPath());
			
			/* Setting Configuration Static Fields */
			APPLICATION_CONTEXT = objContext.getContextPath();
			APPLICATION_TYPE = APP_TYPE.MODULE;
			APPLICATION_DESCRIPTION = "Module Registration";
			APPLICATION_SERVER_INFO = objContext.getServerInfo();
			// APPLICATION_SERVER_OS = ""
			APPLICATION_URL = "/HISRegistration";
			APPLICATION_SERVER_URL = "";

			// Setting Application Server IP and Port
			String isSSLEnable = HisUtil.getParameterFromHisPathXML("SERVER_SSL_ENABLE"); 
			String strApplicationServerIP = HisUtil.getParameterFromHisPathXML("HIS_APPSERVER_IP");
			String strApplicationServerPort = HisUtil.getParameterFromHisPathXML("HIS_APPSERVER_APPPORT");
			String strProtocol = "http://";
			if(isSSLEnable!=null && isSSLEnable.equals("1"))
				strProtocol = "https://";
			
			HISConfig.HIS_SERVICES_SERVER_URL = strProtocol+strApplicationServerIP+":"+strApplicationServerPort;
			System.out.println("Applictaion Server URL --> " + HISConfig.HIS_SERVICES_SERVER_URL);

			// Setting FTP Server URL, User Name and Password
			String strFTPServerURL = HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_URL");
			String strFTPServerUsername = HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_USERNAME");
			String strFTPServerPassword = HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_PASSWORD");
			FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE = strFTPServerURL;
			FTPStaticConfigurator.HIS_FTP_SERVER_USERNAME = strFTPServerUsername;
			FTPStaticConfigurator.HIS_FTP_SERVER_PASSWORD = strFTPServerPassword;
			System.out.println("FTP Server URL, UserName, Password --> " + FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE +", "+FTPStaticConfigurator.HIS_FTP_SERVER_USERNAME +", "+FTPStaticConfigurator.HIS_FTP_SERVER_PASSWORD);

			// Setting SNOIMED CT Server URL
			String strSNOMEDServerIPPort = HisUtil.getParameterFromHisPathXML("HIS_SNOMEDSERVER_IPPORT");
			HISConfig.HIS_SNOMEDCT_SERVER_URL = strProtocol+strSNOMEDServerIPPort;
			System.out.println("SNOMED CT Browser Server URL --> " + HISConfig.HIS_SNOMEDCT_SERVER_URL);
			
			//Setting MONGO DB SERVER IP URL ADDED BY Garima On 10-Feb-2016
			String strMongoServerIP = HisUtil.getParameterFromHisPathXML("HIS_MONGOSERVER_IP");
			HISConfig.HIS_MONGODB_SERVER_URL = strMongoServerIP;
			//setting SNOMED CT ON/OFF flag control from hispath.xml
			String isSnomedOn = HisUtil.getParameterFromHisPathXML("HIS_SNOMEDCT_ON");
			HISConfig.HIS_SNOMEDCT_SERVICES_ON = isSnomedOn;
			System.out.println("IS SNOMEDCT SERVICES ON REG--> " + HISConfig.HIS_SNOMEDCT_SERVICES_ON);
			
			// Initial Parameters
			//System.out.println(objContext.getInitParameterNames());

			// Setting Application Initial Attributes
			setApplictaionMapofAllowedURLs();
		}
		catch (Exception e)
		{
			flg = false;
			// e.printStackTrace();
		}
		return flg;
	}
	
	private void setApplictaionMapofAllowedURLs()//----- Not In Use Right Now 
	{
		mapURLWiseAllowedActionURIs = new HashMap<String, Object>(); 
	}

}
