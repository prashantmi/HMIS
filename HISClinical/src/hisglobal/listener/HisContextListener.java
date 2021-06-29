package hisglobal.listener;

import hisglobal.hisconfig.Config;
import investigation.cacheImplementation.cachemanager.InvestigationDocumentCacheManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lowagie.text.FontFactory;

public class HisContextListener implements ServletContextListener
{

	public void contextInitialized(ServletContextEvent arg0)
	{
		//String client = arg0.getServletContext().getInitParameter("Client");
		// arg0.getServletContext().setAttribute(Config.CLIENT_PGIMER, client);
		
		arg0.getServletContext().setAttribute(Config.CLIENT_NAME, Config.CLIENT);
		System.out.println("servlet context init==========================================================ssssss");
		arg0.getServletContext().setAttribute(Config.EMG_BROUGHT_BY_DETAIL_FLAG, Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE);
		arg0.getServletContext().setAttribute(Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG, Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE);
		arg0.getServletContext().setAttribute(Config.FILE_NO_GENERATION_FLAG_NAME, Config.FILE_NO_GENERATION_FLAG);
		populateVideoImageConfigurator(arg0.getServletContext()) ;
		// Registering Fonts
		//FontFactory.registerDirectory("C:\\Windows\\fonts");
		FontFactory.registerDirectory(arg0.getServletContext().getRealPath("/hisglobal/utility/fonts"));
	}
	
	public static void populateVideoImageConfigurator(ServletContext servletContext) 
	{
		try
		{
			InvestigationDocumentCacheManager.getInstance().loadAppletConfigurator("101");
			InvestigationDocumentCacheManager.getInstance().loadAppletUserInformationXML("101");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	 
	}
	
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}

}
