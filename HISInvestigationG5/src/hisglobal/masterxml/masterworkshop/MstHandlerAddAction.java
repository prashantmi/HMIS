package hisglobal.masterxml.masterworkshop;

import hisglobal.masterxml.masterworkshop.tools.Control;
import hisglobal.masterxml.masterworkshop.tools.MasterAddTO;
import hisglobal.masterxml.masterworkshop.tools.MasterListTO;
import hisglobal.masterxml.masterworkshop.tools.MasterTO;
import hisglobal.masterxml.masterworkshop.utils.*;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.io.InputStream;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;

public class MstHandlerAddAction extends DispatchAction
{
	/**
	 * This method upon recieving request for ADD
	 * Checks if TOLst object is there in the session or not.
	 * if not>>> forward to error page 
	 * else
	 * Extracts the master name from the List TO.Then, this master name from the listto is used to 
	 * get the instance of masterbuilder on which buildMaster method is invoked
	 * @see hisglobal.masterxml.masterworkshop.utils.MasterBuilder#buildMaster(String, String, MasterTO) 
	 * The method returns MAsterADDTO object which is then put into session twice with two attributes
	 * TOAddObject &  TOAddInitialObject(TOAddInitialObject used to restore the initial state of TO when records are saved
	 * as TOAddObject might have changed when it was resubmitted to retrieve the values for the dependent controls 
	 * This ADDTOobject is then used to initialise the default values in the form bean for add page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus = new Status();

		HttpSession sessionObj = request.getSession();
		//System.out.println("objLstTO" + sessionObj.getAttribute("TOLstObject"));

		if (sessionObj.getAttribute("TOLstObject") == null)
		{
			//System.out.println("TOLStObject is NUll..");
			objStatus.add(Status.CUSTOM, "LISTTO IN SESSION NOT FOUND", "SESSION MIGHT HAVE EXPIRED ");
			WebUTIL.setStatus(request, objStatus);
		}
		else
		{
			MstHandlerAddFormBn formBean = (MstHandlerAddFormBn) form;
			//System.out.println(" TOObject is nOt nUll...");
			MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			String masterName = ((MasterListTO) objMstTo).getObjMstLstDtl().getMasterName();
			//System.out.println("MasterName::" + masterName);
			String mstFilename = objMstTo.getMasterName();
			//System.out.println("MasterName::" + mstFilename);
			MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
					.getAttribute("XML_FILE_AS_INPUTSTREAM"));
			try
			{
				MasterTO objMstAddTo = objMstBuilder.buildMaster(masterName, "add", objMstTo);
				//sessionObj.setAttribute("TOAddObject", objMstAddTo);
				WebUTIL.setMasterAttributeInSession(request,"TOAddObject", objMstAddTo);
				//sessionObj.setAttribute("TOAddInitialObject", objMstAddTo);
				WebUTIL.setMasterAttributeInSession(request, "TOAddInitialObject", objMstAddTo);
				//System.out.println("after setting to in session for add");
				ArrayList alcontrols = objMstAddTo.getControls();
				if (((MasterAddTO) objMstAddTo).getObjMstAddDtl().getStaticPage() != null) formBean.setStaticPage(((MasterAddTO) objMstAddTo)
						.getObjMstAddDtl().getStaticPage());

				try
				{
					for (int i = 0; i < alcontrols.size(); i++)
					{
						//System.out.println("inside for");
						Control objControl = (Control) alcontrols.get(i);
						//System.out.println("sdn");
						if (objControl.getClass() == MasterConfig.CLASS_CHECKBOX)
						{
							//System.out.println("sdsj");
							String dVal = objControl.getDefaultValue();
							String[] arrStr = dVal.split("~");
							//System.out.println("dVal:::" + dVal);
							formBean.getMultiSelect(i).setData(arrStr);
						}
						else
						{
							String dVal = objControl.getDefaultValue();
							//System.out.println("dVal:::" + dVal);
							formBean.setControl(i, dVal);
						}
						//System.out.println("sdbgkjb" + formBean.getControls()[i]);
					}
					objStatus.add(Status.INPROCESS);
					WebUTIL.setStatus(request, objStatus);
				}
				catch (Exception e)
				{
					System.out.println("exception " + e);
					e.printStackTrace();
					objStatus.add(Status.CUSTOM, "LISTTO IN SESSION NOT FOUND", "SESSION MIGHT HAVE EXPIRED");
					WebUTIL.setStatus(request, objStatus);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			//return mapping.findForward("add");

		}
		//System.out.println("forwarding to add page");
		return mapping.findForward("add");
	}

	/**
	 * This Method resubmits the page to retrieve the values for the dependent controls on the form
	 * The method upon recieving the request for the dependent values .first checks the master names for listTO 
	 * and addTO in session .If name's doesn't match it forwards the control to error page. 
	 * Else
	 * Retreive the Master instance from the repository and invoke buildmaster
	 * @see hisglobal.masterxml.masterworkshop.utils.MasterBuilder#buildMaster(MasterTO, hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn) 
	 * It then sets the returned AddTO in session. 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward RESUBMIT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		HttpSession sessionObj = request.getSession();
		//System.out.println("in resubmit");
		MstHandlerAddFormBn formBeanAdd = (MstHandlerAddFormBn) form;
		//System.out.println("sdgjh");
		MasterTO objMstAddTo = (MasterTO) sessionObj.getAttribute("TOAddObject");
		MasterTO objMstListTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
		//System.out.println("objMstAddTo" + objMstAddTo);
		//System.out.println("objMstAddTo" + objMstListTo);
		//System.out.println("objMstAddTo.getTitle()" + objMstAddTo.getTitle());
		//System.out.println("objMstListTo.getTitle()" + objMstListTo.getTitle());

		if (objMstAddTo.getTitle().equals(objMstListTo.getTitle()))
		{
			//System.out.println("inside ifdata");
			String mstFilename = objMstListTo.getMasterName();
			MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
					.getAttribute("XML_FILE_AS_INPUTSTREAM"));
			objMstAddTo = objMstBuilder.buildMaster(objMstAddTo, formBeanAdd);
			//sessionObj.setAttribute("TOAddObject", objMstAddTo);
			WebUTIL.setMasterAttributeInSession(request, "TOAddObject", objMstAddTo);
			objStatus.add(Status.INPROCESS);
			WebUTIL.setStatus(request, objStatus);
		}
		else
		{
			objStatus.add(Status.CUSTOM, "LISTTO IN SESSION NOT FOUND", "SESSION MIGHT HAVE EXPIRED");
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("add");
		}
		return mapping.findForward("add");
	}

	/**
	 * This method  Inserts the form values in the database.
	 * The method upon recieving the request for the dependent values .first checks the master names for listTO 
	 * and addTO in session .If name's doesn't match it forwards the control to error page. 
	 * Else
	 * Retrieves the master instance and invokes the method save record
	 * @see masterWorkshop.utils.MasterBuilder#saveRecord(MasterTO, MasterTO, hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn)
	 *  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */

	public ActionForward ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		Status objStatus = new Status();
		HttpSession sessionObj = request.getSession();
		//System.out.println("SAVE");
		MstHandlerAddFormBn formBeanAdd = (MstHandlerAddFormBn) form;
		MasterTO objMstAddTo = (MasterTO) sessionObj.getAttribute("TOAddObject");
		MasterTO objMstListTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
		boolean success = false;
		String statusMessage="FALSE";
		if (objMstAddTo.getTitle().equals(objMstListTo.getTitle()))
		{

			//first check for the mandatory fields value in form bean. 
			//for text and textareas they canot be null.
			//from combos some value has to be checked.
			//first retrieve ADDTO 
			// ArrayList alControls =((MasterAddTO)objMstAddTo).getControls();

			String mstFilename = objMstListTo.getMasterName();
			MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
					.getAttribute("XML_FILE_AS_INPUTSTREAM"));
			Object[] ctrlArray = formBeanAdd.getControls();
			for (int i = 0; i < ctrlArray.length; i++)
			{
				if (ctrlArray[i] != null && ((String) ctrlArray[i]).equalsIgnoreCase("-1")) ctrlArray[i] = "";
				//System.out.println("ctrlArray[" + i + "]" + ctrlArray[i]);
			}
			statusMessage = objMstBuilder.saveRecord(objMstAddTo, objMstListTo, formBeanAdd);
			//sessionObj.setAttribute("TOAddObject",objMstAddTo);	   		
		}
		if (statusMessage.equals("TRUE"))
		{
			//System.out.println("inside if success");
			MasterTO objMstAddInitTo = (MasterTO) sessionObj.getAttribute("TOAddInitialObject");
			//sessionObj.setAttribute("TOAddObject", objMstAddInitTo);
			WebUTIL.setMasterAttributeInSession(request, "TOAddObject", objMstAddInitTo);
			ArrayList alcontrols = objMstAddInitTo.getControls();
			for (int i = 0; i < alcontrols.size(); i++)
			{

				Control objControl = (Control) alcontrols.get(i);
				if (objControl.getClass() == MasterConfig.CLASS_CHECKBOX)
				{
					String dVal = objControl.getDefaultValue();
					String[] arrStr = dVal.split("~");
					//System.out.println("dVal:::" + dVal);
					formBeanAdd.getMultiSelect(i).setData(arrStr);
				}

				else
				{
					String dVal = objControl.getDefaultValue();
					//System.out.println("dVal:::" + dVal);
					formBeanAdd.setControl(i, dVal);
				}

				//System.out.println("sdbgkjb" + formBeanAdd.getControls()[i]);
			}
			//System.out.println("forwarding to add page :::::::::");
			objStatus.add(Status.INPROCESS);
			objStatus.add(Status.CUSTOM, "Record Added Successfully", "");
			WebUTIL.setStatus(request, objStatus);
			
		}
		else if(statusMessage.equals("FALSE"))
		{
			objStatus.add(Status.INPROCESS, "INSERTION FAILED", "");
			WebUTIL.setStatus(request, objStatus);
			//return mapping.findForward("add");
		}else if(statusMessage.equals("DUPLICATE"))
		{
			objStatus.add(Status.INPROCESS, "Record Not Saved : Duplicate Name Exist", "");
			WebUTIL.setStatus(request, objStatus);
			//return mapping.findForward("add");
		}
		return mapping.findForward("add");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("list");

	}

}//end of class
