package hisglobal.masterxml.masterworkshop;

import hisglobal.masterxml.masterworkshop.tools.Control;
import hisglobal.masterxml.masterworkshop.tools.MasterListTO;
import hisglobal.masterxml.masterworkshop.tools.MasterTO;
import hisglobal.masterxml.masterworkshop.utils.MasterBuilder;
import hisglobal.masterxml.masterworkshop.utils.MstInstanceRepository;
import hisglobal.masterxml.masterworkshop.tools.*;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.io.InputStream;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;

public class MstHandlerModifyAction extends DispatchAction
{

	/**
	 * This method is Invoked to display Modification page
	 * The method first checks the TOLst object in session .If not found forward to error page
	 * First retrieves the primary key value from the parameter(chk) for which the records are to be modified
	 * Same way retrieve the isactive value from the parameters.
	 * Retrieves the LstTO Object from the session and the master instance
	 * Use the master instance to invoke overloaded buildmaster and pass 
	 * @see hisglobal.masterxml.masterworkshop.utils.MasterBuilder#buildMaster(String, String, String)
	 * Then sets the default values in from bean using returned MODTO.   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		Status objStatus = new Status();
		HttpSession sessionObj = request.getSession();
		MasterTO objMstTo = (MasterListTO) sessionObj.getAttribute("TOLstObject");
		//System.out.println("TOLstObject" + (sessionObj.getAttribute("TOLstObject")).getClass().getName());

		if (objMstTo == null)
		{
			System.out.println("TOLstObject is nUll..");
			objStatus.add(Status.CUSTOM, "LISTTO IN SESSION NOT FOUND", "SESSION MIGHT HAVE EXPIRED ");
			WebUTIL.setStatus(request, objStatus);
		}
		else
		{
			try
			{
				String chk = request.getParameter("chk");
				//System.out.println("chk" + chk);
				String ActiveStatus = request.getParameter("isActive");
				//System.out.println("isActive:::::" + ActiveStatus);
				MstHandlerModifyFormBn formBeanMod = (MstHandlerModifyFormBn) form;
				// MstHandlerListFormBn formBeanLst = new MstHandlerListFormBn(); 
				//System.out.println("list TO TOLstObject is nOt nUll...");
				objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
				String masterName = ((MasterListTO) objMstTo).getObjMstLstDtl().getMasterName();
				String mstFilename = objMstTo.getMasterName();
				MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
						.getAttribute("XML_FILE_AS_INPUTSTREAM"));
				MasterTO objMstModTo = objMstBuilder.buildMaster(masterName, chk, ActiveStatus, objMstTo);
				objMstModTo.setTitle(objMstTo.getTitle());
				//((MasterModTO)objMstModTo).setPkValue(chk);		  		  
				//sessionObj.setAttribute("TOModObject", objMstModTo);
				WebUTIL.setMasterAttributeInSession(request, "TOModObject", objMstModTo);
				//System.out.println("after setting to in session for modify");
				// this will set the default values in bean Page Acc to MODTO default vallues..

				if (((MasterModTO) objMstModTo).getObjMstModDtl().getStaticPage() != null) formBeanMod
						.setStaticPage(((MasterModTO) objMstModTo).getObjMstModDtl().getStaticPage());

				ArrayList alcontrols = objMstModTo.getControls();

				for (int i = 0; i < alcontrols.size(); i++)
				{
					Control objControl = (Control) alcontrols.get(i);
					//System.out.println("objControl:::" + objControl);
					if (objControl.getClass() == MasterConfig.CLASS_CHECKBOX)
					{
						String dVal = objControl.getDefaultValue();
						String[] arrStr = dVal.split("~");
						//System.out.println("dVal:::" + dVal);
						formBeanMod.getMultiSelect(i).setData(arrStr);
					}
					else
					{
						String dVal = objControl.getDefaultValue();
						//String dVal="1";				
						//System.out.println("dVal:::" + dVal);
						formBeanMod.setContrl(i, dVal);//<<<<<<<<<<<<<Check Changes
					}
					//System.out.println("sdbgkjb" + formBeanMod.getControls()[i]);
				}
				objStatus.add(Status.INPROCESS);
				WebUTIL.setStatus(request, objStatus);
			}//end of try
			catch (Exception e)
			{
				e.printStackTrace();
				objStatus.add(Status.CUSTOM, "ERROR::::", e.getMessage());
				WebUTIL.setStatus(request, objStatus);
			}
		}
		//System.out.println("forwarding to modify page");
		return mapping.findForward("modify");
	}//end of method

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		//System.out.println("djbfjd");
		Status objStatus = new Status();
		HttpSession sessionObj = request.getSession();
		//System.out.println("hello");

		MasterTO objMstTo = (MasterListTO) sessionObj.getAttribute("TOLstObject");
		//System.out.println("TOLstObject" + (sessionObj.getAttribute("TOLstObject")).getClass().getName());

		if (objMstTo == null)
		{
			//System.out.println("TOLstObject is nUll..");
			objStatus.add(Status.CUSTOM, "LISTTO IN SESSION NOT FOUND", "SESSION MIGHT HAVE EXPIRED ");
			WebUTIL.setStatus(request, objStatus);
		}
		else
		{
			try
			{

				String chk = request.getParameter("chk");
				//System.out.println("chk" + chk);
				String ActiveStatus = request.getParameter("isActive");
				//System.out.println("isActive:::::" + ActiveStatus);
				MstHandlerModifyFormBn formBeanMod = (MstHandlerModifyFormBn) form;
				//MstHandlerListFormBn formBeanLst = new MstHandlerListFormBn(); 
				//System.out.println("list TO TOLstObject is nOt nUll...");
				objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
				String masterName = ((MasterListTO) objMstTo).getObjMstLstDtl().getMasterName();
				String mstFilename = objMstTo.getMasterName();
				MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
						.getAttribute("XML_FILE_AS_INPUTSTREAM"));
				MasterTO objMstModTo = objMstBuilder.buildMaster(masterName, chk, ActiveStatus, objMstTo);
				objMstModTo.setTitle(objMstTo.getTitle());
				//((MasterModTO)objMstModTo).setPkValue(chk);		  		  
				//sessionObj.setAttribute("TOModObject", objMstModTo);
				WebUTIL.setMasterAttributeInSession(request, "TOModObject", objMstModTo);
				//System.out.println("after setting to in session for modify");
				//this will set the default values in bean Page Acc to MODTO default values..
				if (((MasterModTO) objMstModTo).getObjMstModDtl().getStaticPage() != null)
				{
					formBeanMod.setStaticPage(((MasterModTO) objMstModTo).getObjMstModDtl().getStaticPage());
					return mapping.findForward("modify");
				}
				ArrayList alcontrols = objMstModTo.getControls();
				for (int i = 0; i < alcontrols.size(); i++)
				{
					Control objControl = (Control) alcontrols.get(i);
					if (objControl.getClass() == MasterConfig.CLASS_CHECKBOX)
					{
						String dVal = objControl.getDefaultValue();
						String[] arrStr = dVal.split("~");
						//System.out.println("dVal:::" + dVal);
						formBeanMod.getMultiSelect(i).setData(arrStr);
					}
					else
					{
						String dVal = objControl.getDefaultValue();
						//System.out.println("dVal:::" + dVal);
						formBeanMod.setContrl(i, dVal);///<<<<<<<<<<<<<<<<<<<<<<<<
					}

					//System.out.println("sdbgkjb" + formBeanMod.getControls()[i]);
				}
				objStatus.add(Status.INPROCESS);
				WebUTIL.setStatus(request, objStatus);
			}//end of try
			catch (Exception e)
			{
				objStatus.add(Status.CUSTOM, "ERROR::::", e.getMessage());
				WebUTIL.setStatus(request, objStatus);
				e.printStackTrace();
			}

		}
		//System.out.println("forwarding to view page");
		return mapping.findForward("view");

	}//end of method	  

	public ActionForward RESUBMIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		Status objStatus = new Status();
		try
		{

			HttpSession sessionObj = request.getSession();
			//System.out.println("in resubmit");
			MstHandlerModifyFormBn formBeanMod = (MstHandlerModifyFormBn) form;
			//System.out.println("sdgjh");
			MasterTO objMstModTo = (MasterTO) sessionObj.getAttribute("TOModObject");
			MasterTO objMstListTo = (MasterTO) sessionObj.getAttribute("TOLstObject");

			if (objMstModTo.getTitle().equals(objMstListTo.getTitle()))
			{
				//System.out.println("titles found same");
				String mstFilename = objMstListTo.getMasterName();
				MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
						.getAttribute("XML_FILE_AS_INPUTSTREAM"));
				objMstModTo = objMstBuilder.buildMaster(objMstModTo, formBeanMod);
				//sessionObj.setAttribute("TOModObject", objMstModTo);
				WebUTIL.setMasterAttributeInSession(request, "TOModObject", objMstModTo);
				objStatus.add(Status.INPROCESS);
				WebUTIL.setStatus(request, objStatus);
			}
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", e.getMessage());
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("modify");

	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		//System.out.println("inside MODIFYSAVE");
		String statusMessage="FALSE";
		Status objStatus = new Status();
		try
		{
			HttpSession sessionObj = request.getSession();
			//System.out.println("SAVE OF MODIFY");
			MstHandlerModifyFormBn formBeanMod = (MstHandlerModifyFormBn) form;
			MasterTO objMstModTo = (MasterTO) sessionObj.getAttribute("TOModObject");
			MasterTO objMstListTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			//System.out.println("objMstModTo" + objMstModTo);
			//System.out.println("objMstAddTo" + objMstListTo);
			//System.out.println("objMstModTo.getTitle()" + objMstModTo.getTitle());
			//System.out.println("objMstListTo.getTitle()" + objMstListTo.getTitle());
			boolean success = false;
			if (objMstModTo.getTitle().equals(objMstListTo.getTitle()))
			{
				//System.out.println("inside ifdata");
				String mstFilename = objMstListTo.getMasterName();
				MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
						.getAttribute("XML_FILE_AS_INPUTSTREAM"));
				Object[] ctrlArray = formBeanMod.getControls();
				for (int i = 0; i < ctrlArray.length; i++)
				{
					if (ctrlArray[i] != null && ((String) ctrlArray[i]).equalsIgnoreCase("-1")) ctrlArray[i] = "";
					//System.out.println("ctrlArray[" + i + "]" + ctrlArray[i]);
				}
				statusMessage = objMstBuilder.updateRecord(objMstModTo, objMstListTo, formBeanMod);

				//success=true;			   		
			}
			if (statusMessage.equals("TRUE"))
			{
				//System.out.println("inside success");
				//System.out.println("hmode" + request.getParameter("hmode"));
				objStatus.add(Status.INPROCESS, "Record Modified Successfully", "");
				WebUTIL.setStatus(request, objStatus);
				return mapping.findForward("list");
			}
			else if(statusMessage.equals("FALSE"))
			{
				objStatus.add(Status.UNSUCESSFULL, "UPDATE FAILED", "");
				WebUTIL.setStatus(request, objStatus);
				return mapping.findForward("list");
			}
			else if(statusMessage.equals("DUPLICATE"))
			{
				objStatus.add(Status.INPROCESS, "Record Not Saved : Duplicate Name Exist", "");
				WebUTIL.setStatus(request, objStatus);
				return mapping.findForward("modify");
			}
		}//end of try
		catch (Exception e)
		{
			objStatus.add(Status.UNSUCESSFULL, "UPDATE FAILED", "");
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("list");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		return mapping.findForward("list");
	}
}//end of class

