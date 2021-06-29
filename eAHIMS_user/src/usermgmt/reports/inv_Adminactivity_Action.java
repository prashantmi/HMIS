package usermgmt.reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import usermgmt.FuncLib;
import HisGlobal.AuditLog;
import HisGlobal.HisMethods;
import HisGlobal.HisResultSet;

/*
http://localhost:8080/admin/usermgmt/reports/admin_actionnew.cnt
*/


public class inv_Adminactivity_Action extends DispatchAction
{
	AuditLog log =new AuditLog();
	FuncLib funobj=new FuncLib();
	inv_Adminactivity_Util myUtil	= new inv_Adminactivity_Util();
	private String WINDOWS_PATH = "c:\\RAOL\\AHIS\\UserMgtConfig.xml";
	private String LINUX_PATH = "/root/RAOL/AHIS/UserMgtConfig.xml";
	


	public ActionForward  unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("IN UNSPECIFIED");

		try
		{

		inv_Adminactivity_ActionForm myForm	=  (inv_Adminactivity_ActionForm) form;
		myForm.setFromDate(funobj.getSysdate());
		System.out.println("fromDate"+myForm.getFromDate());
		myForm.setDtDate(funobj.getSysdate());
		


				request.setAttribute("modList",modulecombo(myForm,request) );
				
		}
		catch(Exception e)
		{
			System.out.println("Exception there in Action "+e);
		}


		return mapping.findForward("init");

	}//end off unspecified
	public List modulecombo(inv_Adminactivity_ActionForm myForm,HttpServletRequest request) throws Exception
	{
		String query="";
		String hoscode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		System.out.println("HospitalCode"+hoscode);
		query	= " SELECT GSTR_MODULE_NAME,GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST A where A.GNUM_HOSPITAL_CODE='"+hoscode+"'and A.GBL_ISVALID=1";
				  //"  order by initcap(GSTR_MODULE_NAME)";
		
		List modList	= new ArrayList();
	
		modList	= (ArrayList) new HisGlobal.HisComboValue().getComboList(query);		
		
		return modList;		
	
	}//end of moduleCombo
	
	public ActionForward MENU(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{

		System.out.println("IN MENU");

		try
		{
			String path=getPath();
			System.out.println("Path"+path);
		inv_Adminactivity_ActionForm myForm	=  (inv_Adminactivity_ActionForm) form;
		//String module=myForm.getMod();
		String module=request.getParameter("mod");
		System.out.println("module"+module);
		String osName = System.getProperties().getProperty("os.name");
		char sepChar = '\\';
		if(osName.startsWith("Win"))
			sepChar = '\\';
		else if(osName.startsWith("Lin"))
			sepChar = '/';
		String finalpath=path+sepChar+module;
		String fullpath=finalpath.replace(sepChar,'*');
		request.setAttribute("fullpath",fullpath);
		System.out.println("finalpath"+finalpath);
		request.setAttribute("modList",modulecombo(myForm,request) );
		
		TEMP( mapping, form, request, response,finalpath);
		//request.setAttribute("errorDetails",AuditLog.getAuditLogDetails(finalpath,myForm.getFromDate(),myForm.getToDate()));
		String hmode=myForm.getHmode();
		System.out.println("hmode"+hmode);
		request.setAttribute("hmode",hmode);
		}
		catch(Exception  e)
		{
			System.out.println("Exception there in Action "+e);
		}
		return mapping.findForward("init");

	}//end off MENU
	public String  getPath()
	{
		System.out.println("Inside getPath");
		String path=null;
		String osName = System.getProperties().getProperty("os.name");
		if(osName.startsWith("Win"))
		{
		 path = getTagValue(3);
		
		}
		else if(osName.startsWith("Lin"))
		{
			 path = getTagValue(4);
			
			}
		return path;
	}
	
	private String getTagValue(int index)
	{
		String tagValue = "";		
		try
		{
			System.out.println("Inside getTagValue");
			Document doc = null;	 
			String osName = getOsName();
			System.out.println("osName"+osName);
			doc = getParsedDocument(osName);		
			Element root = doc.getRootElement();		
			List children = root.getChildren();				
			for(int i=0;i<children.size();i++)
			{
				Element e  = (Element)children.get(i);
				if(i==index)				
					tagValue = e.getText();
				System.out.println("tagValue"+tagValue);
			}		
		}
		catch(Exception e)
		{
			System.out.println("Exception during Parsing getConfigValues()"+e);
		}			
		return tagValue;	
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

	

	public ActionForward TEMP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response,String finalpath)
	{
		try
		{

		inv_Adminactivity_ActionForm myForm	=  (inv_Adminactivity_ActionForm) form;


		
		String hmode=myForm.getHmode();
		System.out.println("hmode"+hmode);
		request.setAttribute("hmode",hmode);
		request.setAttribute("finalpath",finalpath);
		request.setAttribute("AuditLogDetails",AuditLog.getAuditLogDetails(finalpath,myForm.getFromDate(),myForm.getToDate()));
		}
		catch(Exception e)
		{
			System.out.println("Exception there in Action "+e);
		}


		return mapping.findForward("init");

	}//end off TEMP






	
}
