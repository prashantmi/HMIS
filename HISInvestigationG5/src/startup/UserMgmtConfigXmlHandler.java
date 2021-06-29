package startup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;

public class UserMgmtConfigXmlHandler {




	private String WINDOWS_PATH = "c:\\PHDM\\AHIS\\UserMgtConfig.xml";
	private String LINUX_PATH = "/root/PHDM/AHIS/UserMgtConfig.xml";
	
	
	
	public int  getSessionTimeOut()
	{
		int sessionTimeOut = 10;
		sessionTimeOut = Integer.parseInt(getTagValue(0));
		return sessionTimeOut;
	}
	public int  getLoginCount()
	{
		int loginCount = 10;
		loginCount = Integer.parseInt(getTagValue(1));
		return loginCount;
	}
	
	
	
	public int  getBlockAfter()
	{
		int blockafter = 10;
		blockafter = Integer.parseInt(getTagValue(2));
		return blockafter;
	}
	public int  getAuditLogPathWindow()
	{
		int auditlogpathwindow = 10;
		auditlogpathwindow = Integer.parseInt(getTagValue(3));
		return auditlogpathwindow;
	}
	
	public int  getAuditLogPathLinux()
	{
		int auditlogpathlinux = 10;
		auditlogpathlinux = Integer.parseInt(getTagValue(4));
		return auditlogpathlinux;
	}

	public int  getRegistrationModificationTime()
	{
		int registrationModificationTime = 10;
		registrationModificationTime = Integer.parseInt(getTagValue(5));
		return registrationModificationTime;
	}
	
	public int  getPasswModificationTime()
	{
		int passwModificationTime = 10;
		passwModificationTime = Integer.parseInt(getTagValue(6));
		return passwModificationTime;
	}
	
	public int  getPasswStrength()
	{
		int passwStrength = 1;
		passwStrength = Integer.parseInt(getTagValue(7));
		return passwStrength;
	}
	
	
	private String getTagValue(int index)
	{
		String tagValue = "";		
		try
		{
			Document doc = null;	 
			String osName = getOsName();
			doc = getParsedDocument(osName);
			
			
			
			
			Element root = doc.getRootElement();		
			List children = root.getChildren();				
			for(int i=0;i<children.size();i++)
			{
				Element e  = (Element)children.get(i);
				if(i==index)				
					tagValue = e.getText();				
			}		
		}
		catch(Exception e)
		{
			System.out.println("Exception during Parsing getConfigValues()"+e);
		}			
		return tagValue;	
	}
	public List getConfigValues() throws Exception
	{
		List itemList = new ArrayList();
		try
		{
			Document doc = null;	
			File f = null;
			String osName = getOsName();
			doc = getParsedDocument(osName);
			
			if(osName.startsWith("Win"))	
			{
				f = new File(WINDOWS_PATH);
				if(!f.exists())
			
				{
					Element root = doc.getRootElement();	
					List children = root.getChildren();	
					
					for(int i=0;i<children.size();i++)
					{
						Element e  = (Element)children.get(i);
						itemList.add(e.getText());			
					}
				}
				else
				{//page should be blank	
					Element root = doc.getRootElement();	
					List children = root.getChildren();	
				
					for(int i=0;i<children.size();i++)
						{
						Element e  = (Element)children.get(i);
						itemList.add(e.getText());			
							}
				}
			}	
		    else if(osName.startsWith("Lin"))	
		    {   f = new File(LINUX_PATH);
		    	if(!f.exists())	
		                 {
                   			Element root = doc.getRootElement();	
        					List children = root.getChildren();	
        					
        					for(int i=0;i<children.size();i++)
        					{
        						Element e  = (Element)children.get(i);
        						itemList.add(e.getText());			
        					}
                   		}
                   		else
                   		{
                   			Element root = doc.getRootElement();	
        					List children = root.getChildren();	
        					
        					for(int i=0;i<children.size();i++)
        					{
        						Element e  = (Element)children.get(i);
        						itemList.add(e.getText());			
        					}
                   		}
		       }
		}
		catch(Exception e)
		{
			System.out.println("Exception during Parsing getConfigValues()"+e);
		}
			
		return itemList;
	}
	
	
			
	
			
	private String getOsName()
	{
		return System.getProperties().getProperty("os.name");
	}
	
	private Document getParsedDocument(String osName) throws Exception
	{	
		SAXBuilder sb = new SAXBuilder();		
		Document doc = null;
		File f = null;		
		if(osName.startsWith("Win"))			
			f = new File(WINDOWS_PATH);
		else if(osName.startsWith("Lin"))
			f = new File(LINUX_PATH);
		doc 		= sb.build(f);		
		return doc;
	}
	
	
}

