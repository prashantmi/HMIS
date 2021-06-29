package usermgmt.masters;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usermgmt.masters.umgmtSessionMgtConfigActionForm;
import usermgmt.masters.umgmtSessionMgtConfigUtil;
import java.util.*;

import login.Auth;
import login.CSRFTokenUtil;

public class umgmtSessionMgtConfigAction  extends DispatchAction{

	



	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("INSIDE unspecified");
		umgmtSessionMgtConfigUtil myUtil = new umgmtSessionMgtConfigUtil();
		umgmtSessionMgtConfigActionForm myForm = (umgmtSessionMgtConfigActionForm) form;
		String hoscode=(String)request.getSession(false).getAttribute("HOSPITAL_CODE");
						
		try
		{
			
						
			List lst = myUtil.getPreviousValues(hoscode);
			//List lst = myUtil.getPreviousValues();
			
		
		myForm.setTimeOutTime(lst.get(0).toString());
		myForm.setLoginCount(lst.get(1).toString());
		myForm.setBlockAfter(lst.get(2).toString());
		myForm.setAuditLogPathWindow(lst.get(3).toString());
		myForm.setAuditLogPathLinux(lst.get(4).toString());
		myForm.setModificationTime(lst.get(5).toString());
		myForm.setPasswModificationTime(lst.get(6).toString());
		myForm.setPasswStrength(lst.get(7).toString());
		myForm.setHosCode(lst.get(8).toString());
		
		request.setAttribute("previousValues",lst);
		}
		catch(Exception e)
		{
			System.out.println("Exception in unspecifed controller "+e);
		}
		
		return mapping.findForward("init");
	}


	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("INSIDE SAVE");
		umgmtSessionMgtConfigUtil myUtil = new umgmtSessionMgtConfigUtil();
		umgmtSessionMgtConfigActionForm myForm = (umgmtSessionMgtConfigActionForm) form;
		Map valueMap = new HashMap();
		boolean status = false;
		valueMap.put("SESSION_TIME_OUT",myForm.getTimeOutTime());
		valueMap.put("LOGIN_COUNT",myForm.getLoginCount());
		valueMap.put("BLOCK_AFTER",myForm.getBlockAfter());
		valueMap.put("AUDIT_LOG_PATH_WINDOW",myForm.getAuditLogPathWindow());
		valueMap.put("AUDIT_LOG_PATH_LINUX", myForm.getAuditLogPathLinux());
		valueMap.put("REGISTRATION_MODIFICATION_TIME", myForm.getModificationTime());
		valueMap.put("PASSWORD_MODIFICATION_TIME", myForm.getPasswModificationTime());
		valueMap.put("PASSWORD_STRENGTH", myForm.getPasswStrength());
		valueMap.put("HOSPITAL_CODE", (String)request.getSession(false).getAttribute("HOSPITAL_CODE"));
		try
		{
			if(CSRFTokenUtil.isValid(request))
			{	
			status = myUtil.updateValues(valueMap);
			}
		}
		catch (Exception e) {
			
		}
		System.out.println("status IS "+status);
		if(status)
			request.setAttribute("message","Record Added Successfully");
		else
			request.setAttribute("message","Error while Saving Record(s)");
		
		return mapping.findForward("init");
	}

}
