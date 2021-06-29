package usermgmt.masters;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;
import org.w3c.dom.NodeList;


public class umgmtSessionConfigXmlHandler {
	
	private String WINDOWS_PATH = "c:\\AIIMSP\\AHIMSG5\\AHIS\\UserMgtConfig.xml";
	private String LINUX_PATH = "/opt/AIIMSP/AHIMSG5/AHIS/UserMgtConfig.xml";
	
	public int  getSessionTimeOut()
	{
		int sessionTimeOut = 10;
		sessionTimeOut = Integer.parseInt(getTagValue(0));
		return sessionTimeOut;
	}
	
	public int  getSessionTimeOut(String hoscode)
	{
		int sessionTimeOut = 10;
		try{
		sessionTimeOut = Integer.parseInt(getTagValue(hoscode,0));
		}catch (Exception e) {			
		}
		return sessionTimeOut;
	}
	
	public int  getLoginCount()
	{
		int loginCount = 10;
		loginCount = Integer.parseInt(getTagValue(1));
		return loginCount;
	}	
	
	public int getLoginCount(String hoscode)
	{
		int loginCount = 10;
		try{
		loginCount = Integer.parseInt(getTagValue(hoscode,1));
		}catch (Exception e) {			
		}
		return loginCount;
	}
	
	public int  getBlockAfter()
	{
		int blockafter = 10;
		blockafter = Integer.parseInt(getTagValue(2));
		return blockafter;
	}
	
	public int  getBlockAfter(String hoscode)
	{
		int blockafter = 10;
		try{
		blockafter = Integer.parseInt(getTagValue(hoscode,1));
		}catch (Exception e) {			
		}
		return blockafter;
	}
	
	public int  getAuditLogPathWindow()
	{
		int auditlogpathwindow = 10;
		auditlogpathwindow = Integer.parseInt(getTagValue(3));
		return auditlogpathwindow;
	}
	
	public int  getAuditLogPathWindow(String hoscode)
	{
		int auditlogpathwindow = 10;
		try{
		auditlogpathwindow = Integer.parseInt(getTagValue(hoscode,1));
		}catch (Exception e) {			
		}
		return auditlogpathwindow;
	}
	
	public int  getAuditLogPathLinux()
	{
		int auditlogpathlinux = 10;
		auditlogpathlinux = Integer.parseInt(getTagValue(4));
		return auditlogpathlinux;
	}
	
	public int  getAuditLogPathLinux(String hoscode)
	{
		int auditlogpathlinux = 10;
		try{
		auditlogpathlinux = Integer.parseInt(getTagValue(hoscode,1));
		}catch (Exception e) {			
		}
		return auditlogpathlinux;
	}
	
	public int getModificationTime() {
		int modificationTime = 10;
		modificationTime = Integer.parseInt(getTagValue(5));
		return modificationTime;
	}
	
	public int  getModificationTime(String hoscode)
	{
		int modificationTime = 10;
		try{
		modificationTime = Integer.parseInt(getTagValue(hoscode,1));
		}catch (Exception e) {			
		}
		return modificationTime;
	}
	
	public int getPasswordModificationTime() {
		int paswModificationTime = 90;
		paswModificationTime = Integer.parseInt(getTagValue(6));
		return paswModificationTime;
	}
	
	public int  getPasswordModificationTime(String hoscode)
	{
		int paswModificationTime = 10;
		try{
			paswModificationTime = Integer.parseInt(getTagValue(hoscode,1));
		}catch (Exception e) {			
		}
		return paswModificationTime;
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
		
		System.out.println("---Login Config Item List---"+itemList+"-------");	
		return itemList;
	}
	public String getTagValue(String hoscode,int index) throws Exception
	{
		List itemList = new ArrayList();
		String tagValue = "";	
		
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
					List children=root.getChildren();
					Element selected=null;					
											
					for(int i=0;i<children.size();i++)
					{
							Element e1  = (Element)children.get(i);
							if(e1.getChildText("hoscode").equalsIgnoreCase(hoscode))
							{
								selected=e1;
							}
					}
					
					List selectedChild=selected.getChildren();
					for(int i=0;i<selectedChild.size();i++)
					{
						Element e  = (Element)selectedChild.get(i);
						itemList.add(e.getText());
						if(i==index)				
							tagValue = e.getText();		
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
        					Element selected=null;					
							
        					for(int i=0;i<children.size();i++)
        					{
        							Element e1  = (Element)children.get(i);
        							if(e1.getChildText("hoscode").equalsIgnoreCase(hoscode))
        								selected=e1;
        					}
        					
        					List selectedChild=selected.getChildren();
        					for(int i=0;i<selectedChild.size();i++)
        					{
        						Element e  = (Element)selectedChild.get(i);
        						itemList.add(e.getText());	
        						if(i==index)				
        							tagValue = e.getText();		
        					}
                   		}
		       }
		}
		catch(Exception e)
		{
			System.out.println("Exception during Parsing getConfigValues()"+e);
		}
			
		System.out.println("---Login Config Item List---"+itemList+"-------");
		return tagValue;
	}
	
	public List getConfigValues(String hoscode) throws Exception
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
					List children=root.getChildren();
					Element selected=null;					
											
					for(int i=0;i<children.size();i++)
					{
							Element e1  = (Element)children.get(i);
							if(e1.getChildText("hoscode").equalsIgnoreCase(hoscode))
							{
								selected=e1;
							}
					}
					
					List selectedChild=selected.getChildren();
					for(int i=0;i<selectedChild.size();i++)
					{
						Element e  = (Element)selectedChild.get(i);
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
        					Element selected=null;					
							
        					for(int i=0;i<children.size();i++)
        					{
        							Element e1  = (Element)children.get(i);
        							if(e1.getChildText("hoscode").equalsIgnoreCase(hoscode))
        								selected=e1;
        					}
        					
        					List selectedChild=selected.getChildren();
        					for(int i=0;i<selectedChild.size();i++)
        					{
        						Element e  = (Element)selectedChild.get(i);
        						itemList.add(e.getText());	
        						
        					}
                   		}
		       }
		}
		catch(Exception e)
		{
			System.out.println("Exception during Parsing getConfigValues()"+e);
		}
			
		System.out.println("---Login Config Item List---"+itemList+"-------");
		return itemList;
	}
	
	public boolean updateFile(Map valueMap) throws Exception
	{
		boolean status = true;
		List itemList=getConfigValues();
		
		String timeOut 		= valueMap.get("SESSION_TIME_OUT").toString();
		String loginCount 	= valueMap.get("LOGIN_COUNT").toString();
		String blockafter 	= valueMap.get("BLOCK_AFTER").toString();
		String AuditLogPathWindow 	= valueMap.get("AUDIT_LOG_PATH_WINDOW").toString();
	    String AuditLogPathLinux = valueMap.get("AUDIT_LOG_PATH_LINUX").toString();	
	    String RegistrationModificationTime = valueMap.get("REGISTRATION_MODIFICATION_TIME").toString();
	    String PasswordModificationTime = valueMap.get("PASSWORD_MODIFICATION_TIME").toString();
	    String PasswordStrength = valueMap.get("PASSWORD_STRENGTH").toString();
	    String hosCode=valueMap.get("HOSPITAL_CODE").toString();
	    
	    
		File f = null;
		Document doc = null;
		SAXBuilder sb = new SAXBuilder();
				
		String osName = getOsName();
		if(osName.startsWith("Win"))			
			{
			 f = new File(WINDOWS_PATH);
			        if (!f.exists())
						{
							
								File f1;
							    f1=new File(WINDOWS_PATH);
							    if(!f1.exists())
							    {
							    	f1.createNewFile();
							    	Writer output = null;
		                        	String text= "<?xml version='1.0' encoding='UTF-8'?>"+ 
							    	"<UserManagementConfig>" + 
							    	"<hospital>" + 
							    	"<sessionTimeOut>"+""+"</sessionTimeOut>"+ 
							    	"<loginType>"+""+"</loginType>"+ 
							    	"<blockAfter>"+""+"</blockAfter>"+ 
							    	"<AuditLogPathWindow>"+""+"</AuditLogPathWindow>"+ 
							    	"<AuditLogPathLinux>"+""+"</AuditLogPathLinux>"+ 
							    	"<RegistrationModificationTime>"+""+"</RegistrationModificationTime>"+ 
							    	"<PasswordModificationTime>"+""+"</PasswordModificationTime>"+
							    	"<PasswordStrength>"+""+"</PasswordStrength>"+
							    	"<hoscode>"+""+"</hoscode>"+
							    	"</hospital>" +
							    	"</UserManagementConfig>";
							     //This code is to write the text string to file
		                        	output = new BufferedWriter(new FileWriter(f1));
							    	output.write(text);
							    	output.close();
							   }
						}
			       }
			     	 else if(osName.startsWith("Lin"))	
					    {   f = new File(LINUX_PATH);
					    	if(!f.exists())	
					    		
					    	{
					    		f.createNewFile();
						    	Writer output = null;
	                        	String text= "<?xml version='1.0' encoding='UTF-8'?>"+ 
						    	" <UserManagementConfig>" + 
						    	"<hospital>" + 
						    	"<sessionTimeOut>"+""+"</sessionTimeOut>"+ 
						    	"<loginType>"+""+"</loginType>"+ 
						    	"<blockAfter>"+""+"</blockAfter>"+ 
						    	"<AuditLogPathWindow>"+""+"</AuditLogPathWindow>"+ 
						    	"<AuditLogPathLinux>"+""+"</AuditLogPathLinux>"+ 
						    	"<RegistrationModificationTime>"+""+"</RegistrationModificationTime>"+ 
						    	"<PasswordModificationTime>"+""+"</PasswordModificationTime>"+
						    	"<PasswordStrength>"+""+"</PasswordStrength>"+
						    	"<hoscode>"+""+"</hoscode>"+
						    	"</hospital>" +
						    	"</UserManagementConfig>";
						     //This code is to write the text string to file
	                        	output = new BufferedWriter(new FileWriter(f));
						    	output.write(text);
						    	output.close();
					    	}
					    	          
					      }
			    	 		
		   
		
		doc 		= sb.build(f);			
		doc = getParsedDocument(osName);		
		XMLOutputter op = new XMLOutputter();
		OutputStream os = new FileOutputStream(f);	
		
		Element root = doc.getRootElement();
		List children = root.getChildren();
				
		Element selected=null;					
		
		for(int i=0;i<children.size();i++)
		{
				Element e1  = (Element)children.get(i);
				if(e1.getChildText("hoscode").equalsIgnoreCase(hosCode))
					selected=e1;
		}
		
		if(selected!=null)
		{
		List selectedChild=selected.getChildren();
		for(int i=0;i<selectedChild.size();i++)
		{
			Element e  = (Element)selectedChild.get(i);
			switch(i)
			{
					case 0:		e.setText(timeOut);				   			break;			
					case 1:		e.setText(loginCount);						break;			
					case 2:		e.setText(blockafter);						break;			
					case 3:		e.setText(AuditLogPathWindow);  			break;	
					case 4:     e.setText(AuditLogPathLinux);  			    break;
					case 5:     e.setText(RegistrationModificationTime);    break;
					case 6:     e.setText(PasswordModificationTime);   		break;
					case 7:     e.setText(PasswordStrength);   				break;
			}			
		}		
		op.output(doc,os);	
		}
		else
		{
			FileWriter fileWriter = new FileWriter(f,true);
			BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
			 
			Writer output = null;
        	String text= "<?xml version='1.0' encoding='UTF-8'?>"+ 
	    	"<UserManagementConfig>" + 
	    	"<hospital>" + 
	    	"<sessionTimeOut>"+timeOut+"</sessionTimeOut>"+ 
	    	"<loginType>"+loginCount+"</loginType>"+ 
	    	"<blockAfter>"+blockafter+"</blockAfter>"+ 
	    	"<AuditLogPathWindow>"+AuditLogPathWindow+"</AuditLogPathWindow>"+ 
	    	"<AuditLogPathLinux>"+AuditLogPathLinux+"</AuditLogPathLinux>"+ 
	    	"<RegistrationModificationTime>"+RegistrationModificationTime+"</RegistrationModificationTime>"+ 
	    	"<PasswordModificationTime>"+PasswordModificationTime+"</PasswordModificationTime>"+
	    	"<PasswordStrength>"+PasswordStrength+"</PasswordStrength>"+
	    	"<hoscode>"+hosCode+"</hoscode>"+
	    	"</hospital>" +
	    	"</UserManagementConfig>";
        	//This code is to write the text string to file
        	//output = new BufferedWriter(new FileWriter(f));
        	fileWriter.append(text);
        	//op.output(doc, out);
        	bufferFileWriter.close();
		}
		return status;
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
