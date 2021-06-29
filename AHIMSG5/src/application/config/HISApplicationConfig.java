/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISApplicationConfig.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package application.config;

import hisglobal.config.HISConfig;
import hisglobal.utility.HisUtil;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;

import javax.servlet.ServletContext;

public class HISApplicationConfig extends HISConfig
{
	public boolean setApplictaionInitials(ServletContext objContext)
	{
		boolean flg = true;
		try
		{
			System.out.println("Initializing Applictaion Context --> " + objContext.getContextPath());
			
			/* Setting Configuration Static Fields */
			APPLICATION_CONTEXT = objContext.getContextPath();
			APPLICATION_TYPE = APP_TYPE.APPLICATION;
			APPLICATION_DESCRIPTION = "Main,Login,SSO";
			APPLICATION_SERVER_INFO = objContext.getServerInfo();
			// APPLICATION_SERVER_OS = ""
			APPLICATION_URL = "/AHIMSG5";
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
			//HISConfig.HIS_SNOMEDCT_SERVER_URL = "http://"+strSNOMEDServerIPPort;   // changed for: not globally populating issue,  by Manisha Gangwar Date: 22.8.2016
			HISConfig.HIS_SNOMEDCT_SERVER_URL = strProtocol+strSNOMEDServerIPPort;
			System.out.println("SNOMED CT Browser Server URL --> " + HISConfig.HIS_SNOMEDCT_SERVER_URL);

			// Initial Parameters
			//System.out.println(objContext.getInitParameterNames());

			// Setting Application Initial Attributes
			

			//Setting Captcha Implementataion Flag
			//Start:Sheeldarshi
			//Reason:Captcha Implementation Configurable
			String isCaptchaImplemenation=HisUtil.getParameterFromHisPathXML("HIS_CAPTCHA_IMPLEMENTATION");
			if(isCaptchaImplemenation!=null)
			HISConfig.CAPTCHA_IMPLEMENTATION=isCaptchaImplemenation;
			System.out.println("Captcha Requirement -->"+isCaptchaImplemenation);
			//End
		}
		catch (Exception e)
		{
			flg = false;
			// e.printStackTrace();
		}
		return flg;
	}

}
