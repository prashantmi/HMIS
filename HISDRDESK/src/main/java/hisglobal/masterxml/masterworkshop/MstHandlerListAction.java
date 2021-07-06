package hisglobal.masterxml.masterworkshop;

import hisglobal.masterxml.MastersUTIL;
import hisglobal.masterxml.masterworkshop.tools.Combo;
import hisglobal.masterxml.masterworkshop.tools.Control;
import hisglobal.masterxml.masterworkshop.tools.MasterListDtl;
import hisglobal.masterxml.masterworkshop.tools.MasterListTO;
import hisglobal.masterxml.masterworkshop.tools.MasterTO;
import hisglobal.masterxml.masterworkshop.utils.MasterBuilder;
import hisglobal.masterxml.masterworkshop.utils.MstInstanceRepository;
import hisglobal.masterxml.masterworkshop.dao.*;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.io.InputStream;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;

public class MstHandlerListAction extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		//System.out.println("i am in unspecified	");
		// //////This line is added to remove the TOlstObject in case the page is refreshed/////
		request.getSession().removeAttribute("TOLstObject");
		// //////////////////////////////////////////////
		return LIST(mapping, form, request, response);
	}

	/**
	 * This method sets the value in listTO to set the startval when the sublist for ten or less records is extracted out of
	 * the normal list or search list It also sets the sorton to 0 ie the 0th column,order to asc and search or sort mode to
	 * sort
	 * 
	 */

	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		Status objStatus = new Status();
		//System.out.println("i m in pagination");
		try
		{
			HttpSession sessionObj = request.getSession();
			MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			String startindex = request.getParameter("startIndex");
			((MasterListTO) objMstTo).setStartval(Integer.parseInt(startindex));
			((MasterListTO) objMstTo).setSortOn(1);// /value changed to 1 from 0
			((MasterListTO) objMstTo).setOrder(0);
			((MasterListTO) objMstTo).setSearchOrSort(""); // Dont Need set sort while Pagination called
			//System.out.println("((MasterListTO)objMstTo).getSortOn()::" + ((MasterListTO) objMstTo).getSortOn());
			//sessionObj.setAttribute("TOLstObject", objMstTo);
			WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "Session Object Might have expired", e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("list");
	}

	public ActionForward SEQUENCE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//System.out.println("i m in SEQUENCE");
		/*
		 * try{ HttpSession sessionObj=request.getSession(); MasterTO
		 * objMstTo=(MasterTO)sessionObj.getAttribute("TOLstObject"); String startindex=request.getParameter("startIndex");
		 * ((MasterListTO)objMstTo).setStartval(Integer.parseInt(startindex)); ((MasterListTO)objMstTo).setSortOn(0);
		 * ((MasterListTO)objMstTo).setOrder(0); ((MasterListTO)objMstTo).setSearchOrSort("sort");
		 * System.out.println("((MasterListTO)objMstTo).getSortOn()::"+((MasterListTO)objMstTo).getSortOn());
		 * sessionObj.setAttribute("TOLstObject",objMstTo); } catch(Exception e) { objStatus.add(Status.ERROR,"Session Object
		 * Might have expired",e.getMessage()); } finally{ WebUTIL.setStatus(request,objStatus); }
		 */
		return mapping.findForward("SEQUENCE");
	}

	public ActionForward ROSTERSEQUENCE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("ROSTERSEQUENCE");
	}

	/**
	 * This method first extracts the mst file name from the session . Looks for LstTO object in session if LstTO object is
	 * not found in session the request for master is first of its type calls the method getMasterInstace of
	 * MstInstanceRepository It then sets the value for controls in form bean if LstTO object found in session i.e. the
	 * request for the master is for first time but diffrent masterto exist in session calls the method getMasterInstace of
	 * MstInstanceRepository It then sets the value for controls in form bea
	 * 
	 * @see masterWorkshop.utils.MstInstanceRepository#getMasterInstace(String) else request is for retrieving data for
	 *      dependent combo. or inactive/active records * It then forwards to list page.jsp
	 * @see masterWorkshop.utils.MasterBuilder#buildMaster(String)
	 */
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		// get The name of master requested
		Status objStatus = new Status();
		MstHandlerListFormBn formBean = (MstHandlerListFormBn) form;
		// String [] chk= formBean.getChk( );
		//System.out.println("Im In List ");
		// get an Object of MasterBuilder >>> MasterBuilder.newInstance()
		HttpSession sessionObj = request.getSession();
		String mstFilename = (String) sessionObj.getAttribute("mstFilename");
		String hospitalCode = ControllerUTIL.getUserVO(request).getHospitalCode();

		MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");

		//System.out.println("sdsdddddddddd");
		//System.out.println("objMstTO" + sessionObj.getAttribute("TOLstObject"));
		if (sessionObj.getAttribute("TOLstObject") == null)// the request for master is first of its type
		{
			//System.out.println("inside if when 	if(objMstTO==null) ");
			mstFilename = (String) sessionObj.getAttribute("mstFilename");
			System.out.println("mstFilename:::" + mstFilename);
			InputStream is = MastersUTIL.getResource(getServlet().getServletContext(), "/hisglobal/masterxml/masterworkshopxml/" + mstFilename);
			//sessionObj.setAttribute("XML_FILE_AS_INPUTSTREAM", is);
			WebUTIL.setMasterAttributeInSession(request, "XML_FILE_AS_INPUTSTREAM", is);
			MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, is);
			//System.out.println("objMstBuilder" + objMstBuilder);

			objMstTo = objMstBuilder.buildMaster(mstFilename, hospitalCode);
			if (objMstTo == null) return mapping.findForward("error");
			objMstTo.setSeatId(ControllerUTIL.getUserVO(request).getSeatId());
			objMstTo.setHospitalCode(ControllerUTIL.getUserVO(request).getHospitalCode());
			//sessionObj.setAttribute("TOLstObject", objMstTo);
			WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
			// >>>>>
			//System.out.println("sdfsdnjkfsdk");
			ArrayList alcontrols = objMstTo.getControls();
			for (int i = 0; i < alcontrols.size(); i++)
			{
				Control objControl = (Control) alcontrols.get(i);
				String dVal = objControl.getDefaultValue();
				formBean.setControls(i, dVal);
			}
			if (formBean.getIsActive() == null) formBean.setIsActive("1");
			if (formBean.getIsActive().equals("")) formBean.setIsActive("1");
			//System.out.println("Forwarding ot list page");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("list");
		}
		else if (!objMstTo.getMasterName().equalsIgnoreCase(mstFilename))// <<the request for the master is for first time
		// but diffrent masterto already exist in session
		{
			//System.out.println("inside else if");
			mstFilename = (String) sessionObj.getAttribute("mstFilename");
			//System.out.println("mstFilename:::" + mstFilename);
			InputStream is = MastersUTIL.getResource(getServlet().getServletContext(), "/hisglobal/masterxml/masterworkshopxml/" + mstFilename);
			//sessionObj.setAttribute("XML_FILE_AS_INPUTSTREAM", is);
			WebUTIL.setMasterAttributeInSession(request, "XML_FILE_AS_INPUTSTREAM", is);
			MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
					.getAttribute("XML_FILE_AS_INPUTSTREAM"));
			//System.out.println("objMstBuilder" + objMstBuilder);
			objMstTo = objMstBuilder.buildMaster(mstFilename, hospitalCode);
			if (objMstTo == null) return mapping.findForward("error");
			objMstTo.setSeatId(ControllerUTIL.getUserVO(request).getSeatId());
			objMstTo.setHospitalCode(ControllerUTIL.getUserVO(request).getHospitalCode());
			//sessionObj.setAttribute("TOLstObject", objMstTo);
			WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
			// >>>>>
			ArrayList alcontrols = objMstTo.getControls();
			for (int i = 0; i < alcontrols.size(); i++)
			{
				Control objControl = (Control) alcontrols.get(i);
				String dVal = objControl.getDefaultValue();
				formBean.setControls(i, dVal);
			}
			if (formBean.getIsActive() == null) formBean.setIsActive("1");
			if (formBean.getIsActive().equals("")) formBean.setIsActive("1");
			/*
			 * System.out.println("formBean.controls.length:::"+formBean.getControls().length); for(int i=0;i<formBean.getControls().length;i++) {
			 * formBean.setControls(i,"-1"); } //formBean.setControls(0,"-1"); //formBean.setControls(1,"-1"); //>>
			 */
			//System.out.println("Forwarding ot list page");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("list");
		}
		else
		{
			try
			{
				//System.out.println("in else ");
				if (formBean.getIsActive() == null) formBean.setIsActive("1");
				if (formBean.getIsActive().equals("")) formBean.setIsActive("1");

				objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
				if (!formBean.getIsActive().equalsIgnoreCase(((MasterListTO) objMstTo).getIsActive()))
				{
					//System.out.println("change in active/inactive");
					MastersUTIL.setListPageDefaults(objMstTo);
					formBean.setTxtSearch("");
				}
				for (int i = 0; i < formBean.getControls().length; i++)
				{
					//System.out.println("formBean.getControls()[i]" + formBean.getControls()[i]);
					//System.out.println("formBean.getControls()[i]" + formBean.getControls()[i]);
					// Combo cmb=(Combo)alControls.get(i);
					// cmb.getDefaultValue();
					ArrayList alControls = objMstTo.getControls();
					Combo cmb = (Combo) alControls.get(i);
					//System.out.println("cmb.getControls()[i]" + cmb.getDefaultValue());

					if (!formBean.getControls()[i].equalsIgnoreCase(cmb.getDefaultValue()))
					{
						//System.out.println("change");
						MastersUTIL.setListPageDefaults(objMstTo);
					}
				}

				// MasterListDtl ObjMstLstDtl=((MasterListTO)objMstTo).getObjMstLstDtl();
				mstFilename = objMstTo.getMasterName();
				MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
						.getAttribute("XML_FILE_AS_INPUTSTREAM"));
				objMstTo = objMstBuilder.buildMaster(objMstTo, formBean, hospitalCode);
				//System.out.println("((MasterListTO)objMstTo).getIsActive()::::" + ((MasterListTO) objMstTo).getIsActive());
				//System.out.println("formBean.getIsActive():::" + formBean.getIsActive());
				ArrayList alcontrols = objMstTo.getControls();
				//System.out.println("alcontrols:::" + alcontrols.size());
				for (int i = 0; i < alcontrols.size(); i++)
				{
					Control objControl = (Control) alcontrols.get(i);
					//System.out.println("objControl.getDefaultValue()" + objControl.getDefaultValue());
					String dVal = objControl.getDefaultValue();
					formBean.setControls(i, dVal);
				}
				/*
				 * if(formBean.getIsActive().equals("1")||formBean.getIsActive()==null) {
				 * ((MasterListTO)objMstTo).setIsActive("1"); for(int i=0;i<alcontrols.size();i++) { Control objControl
				 * =(Control)alcontrols.get(i); String dVal=objControl.getDefaultValue(); formBean.setControls(i,dVal); } }
				 * else { ((MasterListTO)objMstTo).setIsActive(formBean.getIsActive()); for(int i=0;i<alcontrols.size();i++) {
				 * Control objControl =(Control)alcontrols.get(i); String dVal=objControl.getDefaultValue();
				 * formBean.setControls(i,dVal); } }
				 */
				objMstTo.setSeatId(ControllerUTIL.getUserVO(request).getSeatId());
				objMstTo.setHospitalCode(ControllerUTIL.getUserVO(request).getHospitalCode());
				//sessionObj.setAttribute("TOLstObject", objMstTo);
				WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
				objStatus.add(Status.INPROCESS);
				WebUTIL.setStatus(request, objStatus);
			}// end of try
			catch (Exception e)
			{
				objStatus.add(Status.ERROR_AE, "", e.getMessage());
				WebUTIL.setStatus(request, objStatus);
			}
			return mapping.findForward("list");
		}
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		// get The name of master requested
		String hospitalCode = ControllerUTIL.getUserVO(request).getHospitalCode();
		HttpSession sessionObj = request.getSession();
		MstHandlerListFormBn formBean = (MstHandlerListFormBn) form;
	//	System.out.println("formBean.getControls().length;" + formBean.getControls().length);
	//	System.out.println("in else ");
		MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
		ArrayList alcontrols = objMstTo.getControls();
		for (int i = 0; i < alcontrols.size(); i++)
		{
			Control objCtrl = (Control) alcontrols.get(i);
			String dVal = objCtrl.getDefaultValue();
			formBean.setControls(i, dVal);
		}

		formBean.setIsActive(((MasterListTO) objMstTo).getIsActive());
		formBean.setComboSearch(String.valueOf(((MasterListTO) objMstTo).getSearchField()));

		String mstFilename = objMstTo.getMasterName();
		MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
				.getAttribute("XML_FILE_AS_INPUTSTREAM"));
		//System.out.println("isactiveformbean" + formBean.getIsActive());
		objMstTo = objMstBuilder.buildMaster(objMstTo, formBean, hospitalCode);
		//sessionObj.setAttribute("TOLstObject", objMstTo);
		WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
		return mapping.findForward("list");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession sessionObj = request.getSession();
		String hospitalCode = ControllerUTIL.getUserVO(request).getHospitalCode();
		MstHandlerListFormBn formBean = (MstHandlerListFormBn) form;
		//System.out.println("formBean.getControls().length;" + formBean.getControls().length);
		//System.out.println("in else ");
		MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
		ArrayList alcontrols = objMstTo.getControls();
		for (int i = 0; i < alcontrols.size(); i++)
		{
			Control objCtrl = (Control) alcontrols.get(i);
			String dVal = objCtrl.getDefaultValue();
			formBean.setControls(i, dVal);
		}

		formBean.setIsActive(((MasterListTO) objMstTo).getIsActive());
		formBean.setComboSearch(String.valueOf(((MasterListTO) objMstTo).getSearchField()));

		//MasterListDtl ObjMstLstDtl = ((MasterListTO) objMstTo).getObjMstLstDtl();
		String mstFilename = objMstTo.getMasterName();
		MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
				.getAttribute("XML_FILE_AS_INPUTSTREAM"));
		// System.out.println("isactiveformbean"+formBean.getIsActive());
		objMstTo = objMstBuilder.buildMaster(objMstTo, formBean, hospitalCode);
		// sessionObj.setAttribute("TOLstObject",objMstTo);
		return mapping.findForward("list");
	}

	/**
	 * This method sets the setSortOn >>col setOrder >>asc setSearchOrSort("sort"); in listTO
	 */

	public ActionForward ASC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		// get The name of master requested
		Status objStatus = new Status();
		//System.out.println("inside asc");
		try
		{
			HttpSession sessionObj = request.getSession();
			MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			String sortOn = request.getParameter("sortOn");
			//System.out.println("sortOn::" + sortOn);
			String order = request.getParameter("order");
			//System.out.println("order::" + order);
			//System.out.println("((MasterListTO)objMstTo).getStartval())" + ((MasterListTO) objMstTo).getStartval());
			((MasterListTO) objMstTo).setSortOn(Integer.parseInt(sortOn));
			((MasterListTO) objMstTo).setOrder(Integer.parseInt(order));
			((MasterListTO) objMstTo).setSearchOrSort("sort");
			//sessionObj.setAttribute("TOLstObject", objMstTo);
			WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
			//System.out.println("((MasterListTO)objMstTo).getSortOn()::" + ((MasterListTO) objMstTo).getSortOn());
			objStatus.add(Status.INPROCESS);
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, "", e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("list");
	}

	/**
	 * This method sets the setSortOn >>col setOrder >>dsc setSearchOrSort("sort"); in listTO
	 */

	public ActionForward DSC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		// get The name of master requested
		//System.out.println("inside mode dsc");
		HttpSession sessionObj = request.getSession();
		Status objStatus = new Status();
		try
		{
			MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			//System.out.println("objMstTo.getMasterName():::" + objMstTo.getMasterName());
			String sortOn = request.getParameter("sortOn");
			String order = request.getParameter("order");
			((MasterListTO) objMstTo).setSortOn(Integer.parseInt(sortOn));
			((MasterListTO) objMstTo).setOrder(Integer.parseInt(order));
			((MasterListTO) objMstTo).setSearchOrSort("sort");
			//sessionObj.setAttribute("TOLstObject", objMstTo);
			WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
			objStatus.add(Status.INPROCESS);
			// System.out.println("((MasterListTO)objMstTo).getSortOn()::"+((MasterListTO)objMstTo).getSortOn());
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, "", e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}

		return mapping.findForward("list");
	}

	/**
	 * This method sets the followin in listTO ((MasterListTO)objMstTo).setSearchOrSort("search");
	 * ((MasterListTO)objMstTo).setSearchmode(1); ((MasterListTO)objMstTo).setSearchField(Integer.parseInt(searchField));
	 * ((MasterListTO)objMstTo).setTxtSearch(txtSearch); if txtSearch ="" it sets the mode to default
	 */

	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		// get The name of master requested
		Status objStatus = new Status();
		try
		{
			HttpSession sessionObj = request.getSession();
			MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			MstHandlerListFormBn formBean = (MstHandlerListFormBn) form;
			String searchField = formBean.getComboSearch();
			//System.out.println("searchField:::" + searchField);
			String txtSearch = formBean.getTxtSearch();

			((MasterListTO) objMstTo).setSearchField(Integer.parseInt(searchField));
			((MasterListTO) objMstTo).setTxtSearch(txtSearch);
			((MasterListTO) objMstTo).setStartval(0);
			if (txtSearch.trim().equalsIgnoreCase(""))
			{
				((MasterListTO) objMstTo).setSearchmode(0);
				((MasterListTO) objMstTo).setSortOn(0);
				((MasterListTO) objMstTo).setOrder(0);
				((MasterListTO) objMstTo).setStartval(0);
				((MasterListTO) objMstTo).setSearchOrSort("");
				//System.out.println("search string is empty setting normal view");
				return LIST(mapping, form, request, response);
			}
			((MasterListTO) objMstTo).setSearchOrSort("search");
			((MasterListTO) objMstTo).setSearchmode(1);// will tell the list page to use search list rather than normal list
			// for viewing records
			//sessionObj.setAttribute("TOLstObject", objMstTo);
			WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
		}// end of try
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, "", e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("list");
	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		//System.out.println("forwarding to add action");
		return mapping.findForward("add");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		//System.out.println("forwarding to Modify action");
		return mapping.findForward("modify");
	}
	
	

	public ActionForward REPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		try
		{
			HttpSession sessionObj = request.getSession();
			String hospitalCode = ControllerUTIL.getUserVO(request).getHospitalCode();
			ControllerUTIL.setSysdate(request);
			MstHandlerListFormBn formBean = (MstHandlerListFormBn) form;
			MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			String mstFilename = objMstTo.getMasterName();
			MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj.getAttribute("XML_FILE_AS_INPUTSTREAM"));
			objMstTo = objMstBuilder.buildMaster(objMstTo, formBean, hospitalCode);
			request.setAttribute("IS_ACTIVE", formBean.getIsActive());
			//sessionObj.setAttribute("TOLstObject", objMstTo);
			WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
			//System.out.println("forwarding to Report page");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, "", e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}

		return mapping.findForward("report");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		//System.out.println("forwarding to modify action to construct view page page");
		return mapping.findForward("modify");
	}

	public ActionForward DELETE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		String hospitalCode = ControllerUTIL.getUserVO(request).getHospitalCode();
		boolean recorddeleted = false;
		MstHandlerListFormBn formBean = (MstHandlerListFormBn) form;
		//System.out.println("deleting records");
		String[] pkValues = request.getParameterValues("chk");
		HttpSession sessionObj = request.getSession();

		if (sessionObj.getAttribute("TOLstObject") != null)
		{
			MasterTO objMstTo = (MasterTO) sessionObj.getAttribute("TOLstObject");
			//System.out.println("((MasterListTO)objMstTo).getMasterName()" + ((MasterListTO) objMstTo).getMasterName());
			MasterListDtl objMasterLstdtl = ((MasterListTO) objMstTo).getObjMstLstDtl();
			String isvalidField = objMasterLstdtl.getIsvalidfield();
			//System.out.println("objMasterLstdtl.getIsvalidfield()" + objMasterLstdtl.getIsvalidfield());
			int idx = isvalidField.lastIndexOf(".");
			String isvalidFld = isvalidField.substring(idx + 1);
			ArrayList alPrimaryKey = objMasterLstdtl.getAlPrimarykey();
			String tableName = objMasterLstdtl.getListTable();
			//System.out.println("tableName" + tableName);
			//System.out.println("alPrimaryKey::" + alPrimaryKey);
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();
				MasterBuilderDb objMstBuildDb = new MasterBuilderDb(tx);
				//System.out.println("ghhghghhg");
				recorddeleted = objMstBuildDb.deleteRecord(pkValues, alPrimaryKey, tableName, isvalidFld);
				tx.close();
				//System.out.println("recorddeleted::" + recorddeleted);
				String mstFilename = objMstTo.getMasterName();
				MasterBuilder objMstBuilder = MstInstanceRepository.getMasterInstace(mstFilename, (InputStream) sessionObj
						.getAttribute("XML_FILE_AS_INPUTSTREAM"));
				objMstTo = objMstBuilder.buildMaster(objMstTo, formBean, hospitalCode);
				//sessionObj.setAttribute("TOLstObject", objMstTo);
				WebUTIL.setMasterAttributeInSession(request, "TOLstObject", objMstTo);
				objStatus.add(Status.CUSTOM, "RECORD DELETED SUCCESSFULLY", "");
				WebUTIL.setStatus(request, objStatus);
			}
			catch (Exception e)
			{
				tx.rollback();
				tx.close();
				objStatus.add(Status.UNSUCESSFULL, "DELETION FAILED", "");
				WebUTIL.setStatus(request, objStatus);
			}
			return mapping.findForward("list");
		}
		else
		{
			objStatus.add(Status.UNSUCESSFULL, "DELETION FAILED", "SESSION OBJECT EXPIRED");
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("list");
	}// end of method DELETE

}// and of class

