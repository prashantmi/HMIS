package ipd.transactions;

import hisglobal.exceptions.HisException;


import hisglobal.presentation.CSRFGardTokenAction;
import ipd.IpdConfigUtil;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NursingDeskTransCNT extends CSRFGardTokenAction {
	boolean fDesk = false;
	boolean aDesk = false;
	// unspecified mode
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		generateToken(request);
		fDesk = false;
		aDesk = false;
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		NursingDeskTransDATA.department(formBean);
		formBean.setStrCrNo(null);
		String target = "acceptlist";

		return mapping.findForward(target);
	}

	// unit mode

	public ActionForward UNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		NursingDeskTransDATA.unit(formBean, request, response); // defined in
																// DATA class
		return null;
	}

	// ward maode
	public ActionForward WARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		NursingDeskTransDATA.ward(formBean, request, response); // defined in
																// DATA class
		return null;
	}

	// room mode
	public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		NursingDeskTransDATA.room(formBean, request, response); // defined in
																// DATA class
		return null;
	}

	// record mode
	public ActionForward RECORD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		NursingDeskTransDATA.admitlist(formBean, request, response); // defined
																		// in
																		// DATA
																		// class
		return null;
	}

	// bedstatus mode

	public ActionForward BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		NursingDeskTransDATA.beddetail(formBean, request, response);
		String strtarget = "bedstatus";
		return mapping.findForward(strtarget);
	}

	// article mode
	public ActionForward ARTICLE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String strtarget = "articledtl";
		return mapping.findForward(strtarget);
	}

	// checklist mode
	public ActionForward CHECKLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		String tmp = (String) request.getParameter("deptunit");
		formBean.setStrUnit(tmp);
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NursingDeskTransDATA.checklist(formBean, response);
		// String strtarget = "checklist";
		return null;
	}

	// this function is used for saving data
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		validateToken(request, response);
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrIsCancelMode(formBean.getStrIsCancelMode()==null || (!formBean.getStrIsCancelMode().equals("CANCEL"))?null:"CANCEL");
		NursingDeskTransDATA.save(formBean, request);
		/*
		 * NursingDeskTransDATA.department(formBean);
		 * NursingDeskTransDATA.unitFB(formBean);
		 * NursingDeskTransDATA.wardFB(formBean);
		 * NursingDeskTransDATA.roomFB(formBean);
		 * NursingDeskTransDATA.admitlistFB(formBean,fretvalue);
		 */
		// defined in DATA class
		if (fDesk)
			return this.CANCELTODESK(mapping, form, request, response);
		if(aDesk)
			return this.CANCELTOACCEPTANCEDESK(mapping, form, request, response);
		return this.unspecified(mapping, form, request, response);

	}
	
	
	// this function is used for saveing data
		public ActionForward SAVEIPD(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException {

			validateToken(request, response);
			String target = "addDesk";
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			
			formBean.setStrIsCancelMode(formBean.getStrIsCancelMode()==null || (!formBean.getStrIsCancelMode().equals("CANCEL"))  ?  null:"CANCEL");
			
			NursingDeskTransDATA.save(formBean, request);
		
			
			/*
			 * 
			 * The Below Code has been commented
			 * after the discussion that the 
			 * page remain the same if error occurs and
			 * new JSP will appear if data saved successfully 
			 */
			
			/* defined in DATA class
			if (fDesk)
				return this.CANCELTODESK(mapping, form, request, response);
			if(aDesk)
				return this.CANCELTOACCEPTANCEDESK(mapping, form, request, response);*/
			
			if(formBean.getStrMsgType().equals("1"))
			{
				return mapping.findForward("messageIpdDesk"); 
				//return mapping.findForward("addDesk");
			}
			else
			{
				return mapping.findForward("messageIpdDesk"); 
			}
			
		
		}
		
		
		
		public ActionForward SAVECANCEL(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {

			validateToken(request, response);
			String target = "CancelViewDesk";
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			
			NursingDeskTransDATA.saveCancel(formBean, request);
		
			if(formBean.getStrMsgType().equals("1"))
			{
				return mapping.findForward("CancelViewDesk");
			}
			else
			{
				return mapping.findForward("messageIpdDesk"); 
			}
			
		
		}

	// this function is used for updating data in case of Not Reporting
	public ActionForward NOTREPORT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		validateToken(request, response);
		// boolean fretvalue = true;
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		NursingDeskTransDATA.notreport(formBean, request);
		/*
		 * NursingDeskTransDATA.department(formBean);
		 * NursingDeskTransDATA.unitFB(formBean);
		 * NursingDeskTransDATA.wardFB(formBean);
		 * NursingDeskTransDATA.roomFB(formBean);
		 * 
		 * NursingDeskTransDATA.admitlistFB(formBean,fretvalue);
		 */

		// defined in DATA class
		//return mapping.findForward("messageIpdDesk"); 
		/*String target = "notreported";
		return mapping.findForward(target);*/
		if (fDesk)
			return this.CANCELTODESK(mapping, form, request, response);
		if(aDesk)
			return this.CANCELTOACCEPTANCEDESK(mapping, form, request, response);
		return this.unspecified(mapping, form, request, response);

	}
	
	
	// this function is used for updating data in case of Not Reporting
		public ActionForward NEW(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			/*fDesk = true;
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			HttpSession hs=request.getSession();
			PatientDetailVO voDP = null;
			
			System.out.println( request.getParameter("patCrNo"));
			formBean.setStrCrNo(request.getParameter("patCrNo"));
			if(hs.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
			{
				System.out.println("TAKING VO from :-- DynamicDeskConfig.DYNAMIC_DESK_TYPE");
				PatientDetailVO[] al=(PatientDetailVO[])hs.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < al.length; i++)
				{
					voDP = (PatientDetailVO) al[i];
				
					if (voDP.getPatCrNo().equals(formBean.getStrCrNo())) break;
				}
				
				System.out.println(voDP.getPatAdmNo());
				System.out.println(voDP.getPatCrNo());
				
				formBean.setStrCrNo(voDP.getPatCrNo());
				formBean.setStrAdmNo(voDP.getPatAdmNo());
				formBean.setStrHospitalCode(voDP.getHospitalCode());
				formBean.setStrSeatId(voDP.getSeatId());
				
//				vo.setPatCrNo(strCrNo);
//				vo.setStrEpisodeNumber(voDP.getEpisodeCode());
//				vo.setStrVisitValue(voDP.getEpisodeVisitNo());
//				vo.setStrAdmissionNo(voDP.getPatAdmNo());
//				vo.setStrDepartmentValue(strUnitCode.substring(0, 3));
//				vo.setStrCrNo(strCrNo);
//				//vo.setSerialNo(voDP.getSerialNo());
//				//vo.setIsConfirmed(voDP.getIsConfirmed());
//				//fb.setTriageDuration(voDP.getTriageDuration());
//				vo.setStrDeptUnitCode(voDP.getDepartmentUnitCode());
//				deptUnitCodeFb=voDP.getDepartmentUnitCode();
					// User Detail
				//vo.setStrSeatId(userVO.getSeatId());
				//_fb.setEmpNo(userVO.getUserEmpID());
			}
			else if(hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
			{
				System.out.println("TAKING VO from :-- DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO");
				voDP = (PatientDetailVO) hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
//				vo.setPatCrNo(strCrNo);
//				vo.setStrEpisodeNumber(voDP.getEpisodeCode());
//				vo.setStrVisitValue(voDP.getEpisodeVisitNo());
//				vo.setStrAdmissionNo(voDP.getPatAdmNo());
//				vo.setStrDepartmentValue(strUnitCode.substring(0, 3));
//				vo.setStrCrNo(strCrNo);
//				//vo.sets(voDP.getSerialNo());
//				//_fb.setIsConfirmed(voDP.getIsConfirmed());
//				//_fb.setTriageDuration(voDP.getTriageDuration());
//				vo.setStrDeptUnitCode(voDP.getDepartmentUnitCode());
//				deptUnitCodeFb=voDP.getDepartmentUnitCode();
//					// User Detail
				//vo.setStrSeatId(userVO.getSeatId());
				//_fb.setEmpNo(userVO.getUserEmpID());

			}*/
			
			/*formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
			formBean.setStrAdmNo(request.getParameter("chk").replace("$", "@").split("@")[1]);
			if(request.getParameter("chk").replace("$", "@").split("@").length>=4)
			formBean.setStrhmoveno(request.getParameter("chk").replace("$", "@").split("@")[3]);
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());*/
			
			/*NursingDeskTransDATA.notreport(formBean, request);

			return CANCELTODESK(mapping,form,request,response);*/
			
			aDesk = true;
			/*NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
					.split("@")[0]);
			formBean.setStrDepartment(request.getParameterValues("combo")[0]);
			formBean.setStrUnit(request.getParameterValues("combo")[1]);
			formBean.setStrWard(request.getParameterValues("combo")[2]);
			formBean.setStrRoom(request.getParameterValues("combo")[3]);
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			NursingDeskTransDATA.department(formBean);
			NursingDeskTransDATA.unit( formBean, request, response);
			NursingDeskTransDATA.ward( formBean, request, response);
			NursingDeskTransDATA.room( formBean, request, response);
			formBean.setStrDepartment(request.getParameterValues("combo")[0]);
			NursingDeskTransDATA.admitlist(formBean, request, response);*/
			String target = "addDesk";
			return mapping.findForward(target);

		}

	/**
	 * This method calls on cancel button to show the init page(in start up
	 * folder)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward clear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		return this.unspecified(mapping, form, request, response);

	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		// String strTarget = "initPage";
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;

	}

	/**
	 * This method calls on acceptance button of nursing desk 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 * Last Modify Amit Kumar Ateria on 29-04-2011
	 */
	public ActionForward ADDNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{	
		generateToken(request);
		fDesk = true;
		String strUnitId="";
		String strRoomId="";
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
		
		
		/******formBean.setStrAdmNo(request.getParameter("chk").replace("@", "#").split("#")[1]);******/
		
		/* Added by Asha*/
		strUnitId=request.getParameter("chk").replace("*", "#").split("#")[1];
		strRoomId=request.getParameter("chk").replace("*", "#").split("#")[2].replace("$", "@").split("@")[0];
		
	
		
		formBean.setStrUnit(strUnitId);
		formBean.setStrRoom(strRoomId);
		
		
		if(request.getParameter("chk").replace("$", "#").split("#")[1].length()	>	2)
		{
		formBean.setStrhtransinflag(request.getParameter("chk").replace("$", "#").split("#")[1].replace("*", "#").split("#")[4]);
		formBean.setStrWadType(request.getParameter("chk").replace("$", "#").split("#")[1].replace("*", "#").split("#")[5]);
		}
		
		formBean.setStrDepartment(request.getParameterValues("combo")[0]);
		//formBean.setStrUnit(request.getParameterValues("combo")[1]);//remove unit concept
		formBean.setStrWard(request.getParameterValues("combo")[2]);
		//formBean.setStrRoom(request.getParameterValues("combo")[3]);//remove room concept
		formBean.setStrIsCancelMode(request.getParameter("comboValue")==null || (!request.getParameter("comboValue").equals("CANCEL"))?null:"CANCEL");
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		IpdConfigUtil ipdUtil = new IpdConfigUtil(formBean.getStrHospitalCode());
		/* Commented by Amit Kumar Ateria for showing labels for dept,ward and removing unit,room concept*/
		
		//NursingDeskTransDATA.department(formBean);
		//NursingDeskTransDATA.unit( formBean, request, response);
		//NursingDeskTransDATA.ward( formBean, request, response);
		//NursingDeskTransDATA.room( formBean, request, response);
		//formBean.setStrDepartment(request.getParameterValues("combo")[0]);
		formBean.setStrIcuWardType(ipdUtil.getStrIcuWardType());
		formBean.setStrPvtWardType(ipdUtil.getStrPrivateWardType());
		
		NursingDeskTransDATA.admitlist(formBean, request, response);
		String target = "addDesk";

		return mapping.findForward(target);
	}
	
	
	/**
	 * Called When Acceptance ,Leave or InTrasit Button From 
	 * IPD Desk   
	 */
	public ActionForward ADDNURSINGDESKIPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		
		generateToken(request);
		fDesk = true;
		
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		
		
		HttpSession hs=request.getSession();
		//formBean.setStrCrNo(request.getParameter("patCrNo"));  
		
		/*PatientDetailVO voDP = (PatientDetailVO)hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		formBean.setStrCrNo(voDP.getPatCrNo());
		formBean.setStrAdmNo(voDP.getPatAdmNo());
		formBean.setStrDepartment(voDP.getDepartmentCode());
		formBean.setStrWard(voDP.getWardCode());
		formBean.setStrSeatId(voDP.getSeatId());
		formBean.setStrRoom(voDP.getRoom());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());*/
		formBean.setStrCrNo(request.getParameter("patCrNo"));
		formBean.setStrAdmNo(request.getParameter("patAdmNo"));
		formBean.setStrDepartment(request.getParameter("departmentUnitCode").substring(0, 3));
		formBean.setStrWard(request.getParameter("wardCode"));
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrRoom(request.getParameter("roomCode"));
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrCheckFlagType("1");
		IpdConfigUtil ipdUtil = new IpdConfigUtil(formBean.getStrHospitalCode());
		formBean.setStrIcuWardType(ipdUtil.getStrIcuWardType());
		formBean.setStrPvtWardType(ipdUtil.getStrPrivateWardType());
		NursingDeskTransDATA.admitlistIPD(formBean, request, response);
		String target = "addDesk";

		return mapping.findForward(target);
	}
	
	
	public ActionForward ADDNURSINGDESKIPDCANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		
		
		generateToken(request);
		fDesk = true;
		
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		
		HttpSession hs=request.getSession();
		//formBean.setStrCrNo(request.getParameter("patCrNo"));  
		
		/*PatientDetailVO voDP = (PatientDetailVO)hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		formBean.setStrCrNo(voDP.getPatCrNo());
		formBean.setStrAdmNo(voDP.getPatAdmNo());
		formBean.setStrDepartment(voDP.getDepartmentCode());
		formBean.setStrWard(voDP.getWardCode());
		formBean.setStrSeatId(voDP.getSeatId());
		formBean.setStrRoom(voDP.getRoomCode());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());*/
		formBean.setStrCrNo(request.getParameter("patCrNo"));
		formBean.setStrAdmNo(request.getParameter("patAdmNo"));
		formBean.setStrDepartment(request.getParameter("departmentUnitCode").substring(0, 3));
		formBean.setStrWard(request.getParameter("wardCode"));
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrRoom(request.getParameter("roomCode"));
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	
		NursingDeskTransDATA.admitlistIPDCANCEL(formBean, request, response);
		String target = "CancelViewDesk";

		return mapping.findForward(target);
	}
	
	
	/*
	 * Called In Case Of Non-accepted
	 * Patient did not Report.
	 */
	public ActionForward NOTREPORTEDNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		fDesk = true;
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
		formBean.setStrAdmNo(request.getParameter("chk").replace("$", "@").split("@")[1]);
		if(request.getParameter("chk").replace("$", "@").split("@").length>=4)
		formBean.setStrhmoveno(request.getParameter("chk").replace("$", "@").split("@")[3]);
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
		NursingDeskTransDATA.admitlistNr(formBean, request, response);

		//return CANCELTODESK(mapping,form,request,response);
		String target = "notreported";
		return mapping.findForward(target);
	}
	
	
	
	/*
	 * Called In Case Of Non-accepted
	 * Patient did not Report.(in case Of IPD Only)
	 */
	public ActionForward NOTREPORTEDNURSINGDESKIPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		fDesk = true;
		NursingDeskTransFB formBean = (NursingDeskTransFB) form;
		/*formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
		formBean.setStrAdmNo(request.getParameter("chk").replace("$", "@").split("@")[1]);
		if(request.getParameter("chk").replace("$", "@").split("@").length>=4)
		formBean.setStrhmoveno(request.getParameter("chk").replace("$", "@").split("@")[3]);
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());*/
		HttpSession hs=request.getSession();
		/*PatientDetailVO voDP = (PatientDetailVO)hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		formBean.setStrCrNo(voDP.getPatCrNo());
		formBean.setStrAdmNo(voDP.getPatAdmNo());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
        formBean.setStrhmoveno(voDP.getPatMovNo());
       
        
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());*/
		//formBean.setStrCrNo(request.getParameter("patCrNo"));
		//formBean.setStrAdmNo(request.getParameter("patAdmNo"));
		formBean.setStrDepartment(request.getParameter("departmentUnitCode").substring(0, 3));
		formBean.setStrWard(request.getParameter("wardCode"));
		//formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrRoom(request.getParameter("roomCode"));
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrCheckFlagType("1");
		
		formBean.setStrCrNo(request.getParameter("patCrNo"));
		formBean.setStrAdmNo(request.getParameter("patAdmNo"));
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrhmoveno(request.getParameter("patMovNo"));
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NursingDeskTransDATA.admitlistNr(formBean, request, response);

		//return mapping.findForward("messageIpdDesk");
		String target = "notreported";
		return mapping.findForward(target);
	}
	
	
	
	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		if(aDesk)
			return this.CANCELTOACCEPTANCEDESK(mapping, form, request, response);
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskFinalTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	/**
	 * This function is used to forward control back to patient Acceptance Desk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return acFwd
	 * @throws HisException
	 */
	public ActionForward CANCELTOACCEPTANCEDESK(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException {
			ActionForward acFwd = new ActionForward();
			acFwd
					.setPath("/ipd/transactions/PatientAcceptanceDeskFinalTransCNT.cnt?hmode=unspecified");
			acFwd.setContextRelative(true);
			return acFwd;
	}



	/**
	 * This function is used to insert details of not reported patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return CANCELTOACCEPTANCEDESK
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward NOTREPORTINGACCEPTANCEDESK(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {
				
				generateToken(request);
				aDesk = true;
				NursingDeskTransFB formBean = (NursingDeskTransFB) form;
				formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
				formBean.setStrAdmNo(request.getParameter("chk").replace("$", "@").split("@")[1]);			
				formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
				NursingDeskTransDATA.notreport(formBean, request);
				
			return CANCELTOACCEPTANCEDESK(mapping,form,request,response);
		}
		
	/**
		 * This function is used to forward control to patient acceptance   
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws HisException
		 * @throws SQLException
		 */
		public ActionForward ADDACCEPTANCEDESK(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException {
			
			generateToken(request);
			aDesk = true;
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
					.split("@")[0]);
			formBean.setStrDepartment(request.getParameterValues("combo")[0]);
			formBean.setStrUnit(request.getParameterValues("combo")[1]);
			formBean.setStrWard(request.getParameterValues("combo")[2]);
			formBean.setStrRoom(request.getParameterValues("combo")[3]);
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			NursingDeskTransDATA.department(formBean);
			NursingDeskTransDATA.unit( formBean, request, response);
			NursingDeskTransDATA.ward( formBean, request, response);
			NursingDeskTransDATA.room( formBean, request, response);
			formBean.setStrDepartment(request.getParameterValues("combo")[0]);
			NursingDeskTransDATA.admitlist(formBean, request, response);
			String target = "addDesk";
			return mapping.findForward(target);
		}
		/* Method added by Amit Kumar Ateria on 26-mar-2011 to show bed status in Nursing desk*/
		
		public ActionForward BEDCOMBO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
		{
		
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			NursingDeskTransDATA.bed(formBean, request, response); 
			return null;
		}
		
		/**
		 * This mode is used to bring the bed Combo when we click plus or minus button in patient acceptance process
		 */
		public ActionForward BED(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			NursingDeskTransDATA.BEDCOMBOAJAX(formBean, request, response); 
			return null;
		}
		
		public ActionForward NOTREPORTIPD(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			// boolean fretvalue = true;
			generateToken(request);
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			NursingDeskTransDATA.notreport(formBean, request);
			/*
			 * NursingDeskTransDATA.department(formBean);
			 * NursingDeskTransDATA.unitFB(formBean);
			 * NursingDeskTransDATA.wardFB(formBean);
			 * NursingDeskTransDATA.roomFB(formBean);
			 * 
			 * NursingDeskTransDATA.admitlistFB(formBean,fretvalue);
			 */

			// defined in DATA class
			if(formBean.getStrMsgType().equals("1"))
			{
				return mapping.findForward("notreported");
			}
			else
			{
				return mapping.findForward("messageIpdDesk"); 
			}

		}
		/*
		 * Called In Case Of Leave
		 * Patient did not Report.
		 */
		
		public ActionForward NOTREPORTINGNURSINGDESK(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {
			generateToken(request);
			fDesk = true;
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
			formBean.setStrAdmNo(request.getParameter("chk").replace("$", "@").split("@")[1]);
			if(request.getParameter("chk").replace("$", "@").split("@").length>=4)
			formBean.setStrhmoveno(request.getParameter("chk").replace("$", "@").split("@")[3]);
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			NursingDeskTransDATA.admitlistNrLeave(formBean, request, response);

			//return CANCELTODESK(mapping,form,request,response);
			String target = "notreported";
			return mapping.findForward(target);
		}
		
		/*
		 * Called In Case Of Leave
		 * Patient did not Report.(in case Of IPD Only)
		 */
		public ActionForward NOTREPORTINGNURSINGDESKIPD(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {
			generateToken(request);
			fDesk = true;
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			/*formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
			formBean.setStrAdmNo(request.getParameter("chk").replace("$", "@").split("@")[1]);
			if(request.getParameter("chk").replace("$", "@").split("@").length>=4)
			formBean.setStrhmoveno(request.getParameter("chk").replace("$", "@").split("@")[3]);
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());*/
			HttpSession hs=request.getSession();
			/*PatientDetailVO voDP = (PatientDetailVO)hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			formBean.setStrCrNo(voDP.getPatCrNo());
			formBean.setStrAdmNo(voDP.getPatAdmNo());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
	        formBean.setStrhmoveno(voDP.getPatMovNo());*/
			formBean.setStrDepartment(request.getParameter("departmentUnitCode").substring(0, 3));
			formBean.setStrWard(request.getParameter("wardCode"));
			formBean.setStrRoom(request.getParameter("roomCode"));
	        formBean.setStrCheckFlagType("1");
			
			formBean.setStrCrNo(request.getParameter("patCrNo"));
			formBean.setStrAdmNo(request.getParameter("patAdmNo"));
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setStrhmoveno(request.getParameter("patMovNo"));
	        
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			NursingDeskTransDATA.admitlistNrLeave(formBean, request, response);

			//return mapping.findForward("messageIpdDesk");
			String target = "notreported";
			return mapping.findForward(target);
		}
		
		public ActionForward GETCONSULTANTVALUE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			NursingDeskTransFB formBean = (NursingDeskTransFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			NursingDeskTransDATA.GETCONSULTANT(formBean, request, response); 
			return null;
		}
}