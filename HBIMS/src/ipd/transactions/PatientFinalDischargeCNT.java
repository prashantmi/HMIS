package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
//import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.servlets.ServletsUtilityConfig;
import hisglobal.vo.PatientDetailVO;
//import hisglobal.vo.PatientDetailVO;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import opd.transaction.controller.fb.ConsentRequestFB;






import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientFinalDischargeCNT extends CSRFGardTokenAction
{

	public PatientFinalDischargeCNT()
	{
	}

	public ActionForward unspecified(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException
	{ generateToken(_request);
		return this.APPROVED(_mapping, _form, _request, _response);
	}

	// ////////////////////DISCOUNT APPROVED///////////////////////////

	public ActionForward APPROVED(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException
	{
		String target = "approval";
		return _mapping.findForward(target);
	}

	// ////////////////////////// CLEAR//////////////////////////////////
	public ActionForward CLEAR(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException
	{
		return this.APPROVED(_mapping, _form, _request, _response);
	}

	// //////////////////////////CANCEL//////////////////////////////////
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HisException
	{
		return this.APPROVED(mapping, form, request, response);
	}

	public ActionForward INITIALPAGE(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException
	{
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	// ////////////////////////////SAVE///////////////////////////////////

	public ActionForward SAVE(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws Exception
	{	validateToken(_request,_response);
		//WebUTIL.refreshTransState(_request);
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientFinalDischargeDATA.insert(formBean, _request, _response);
		formBean.setStrCrNo("");
		return this.unspecified(_mapping, _form, _request, _response);
	}

	public ActionForward DISCHARGESLIP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setstr(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatID(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrAdmNo(request.getParameter("strAdmNo"));
		formBean.setIsDuplicateSlip(request.getParameter("duplicateMode"));
		PatientFinalDischargeDATA.printSlip(formBean);
		request.setAttribute("strAdmNo", formBean.getStrAdmNo());
		return mapping.findForward("printdischargecard");
	}
	// ////////////////////////GO FUNCTION//////////////////////////
	public ActionForward GO(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws Exception, SQLException
	{		generateToken(_request);
		String strTarget = "approval";
		//WebUTIL.refreshTransState(_request);
		boolean ret = false;
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdConfigUtil ipdC = new IpdConfigUtil(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrDiagType(ipdC.getStrDiagnosisType());
		formBean.setStrDischargeTypeAbsconded(ipdC.getStrDischargeTypeAbsconded());
		formBean.setStrDischargeTypeLAMA(ipdC.getStrDischargeTypeLAMA());
		formBean.setStrDischargeTypeReferral(ipdC.getStrDischargeTypeReferral());
		formBean.setStrDischargeTypeTransfer(ipdC.getStrDischargeTypeTransfer());
		formBean.setStrDischargeTypeDeath(ipdC.getStrDischargeTypeDeath());
		formBean.setStrDeathDetailsRequired(IpdConfigUtil.strDeathDetailsRequired);
		formBean.setStrNormalDischargeType(ipdC.getStrNormlaDischargeType());
		
		if (ipdC.getStrDiagnosisType().equals("1")) formBean.setStrDiagnosisType("0");
		if (ipdC.getStrDiagnosisType().equals("2")) formBean.setStrDiagnosisType("1");
		if (ipdC.getStrDiagnosisType().equals("0")) formBean.setStrDiagnosisType("0");
		formBean.setStrApplicationMode(IpdTransConfig.getDischargeParameter());
		if(formBean.getStrCrNo().equals(""))
		{
			formBean.setStrCrNo(_request.getParameter("patCrNo"));
			formBean.setCallFromIpdDesk("1");
		}
		PatientFinalDischargeDATA.getStrProfileId(formBean, _request);
		PatientFinalDischargeDATA.checkAdmin(formBean, _request);
		
		if(!formBean.getBillcount().equals("0") && formBean.getStrMsgType().equals("5"))
		{
			formBean.setStrErrMsgString("Patient Bill has not been Settled");
			formBean.setStrCrNo("");
			ret = false;
		}	
		
		if (!formBean.getStrMsgType().equals("100"))
		{
			// PatientFinalDischargeDATA.initIcdDiagnosis(_response, formBean);
			if (formBean.getStrMsgType().equals("4"))//not admitted
			{
				//formBean.setStrErrMsgString("Invalid CR No./Data not found");
				/* Message not proper as per HLD format, reported as bug by testing team
				 * changed on 08-Mar-2011 to resolve the bug
				 */
				formBean.setStrErrMsgString("Patient not Admitted!!");
				formBean.setStrCrNo("");
				ret = false;
			}
			else if (formBean.getStrMsgType().equals("5"))
			{
				formBean.setStrErrMsgString("Patient Bill has not been Settled");
				formBean.setStrCrNo("");
				ret = false;
			}
			else if ((formBean.getStrAdmStatCode().equals("12") || formBean.getStrAdmStatCode().equals("18"))
					&& formBean.getStrDischrg_Mode().trim().equals("1"))
			{
				// online
				formBean.setStrMsgType("");
				if (formBean.getStrAdmStatCode().trim().equals("18"))
				{
					PatientFinalDischargeDATA.getPatientDeathDetails(formBean, _request);
					PatientFinalDischargeDATA.getPatientDischargeTypeCombo(formBean, _request);
					PatientFinalDischargeDATA.getRsnRmk(formBean, _request);
					PatientFinalDischargeDATA.getStrTreatmentResultCombo(formBean, _request);
					ret = true;
				}
				else
				{
					formBean.setStrErrMsgString("Patient Discharge not Prepared");
					formBean.setStrCrNo("");
					ret = false;
				}
			}
			else if ((formBean.getStrAdmStatCode().equals("12") || formBean.getStrAdmStatCode().equals("18") || (formBean.getStrAdmStatCode().equals(
					"11")
					|| formBean.getStrAdmStatCode().equals("15") || formBean.getStrAdmStatCode().equals("17"))
					&& formBean.getStrClearForDischarge().equals("true"))
					&& formBean.getStrDischrg_Mode().trim().equals("2"))
			{
				// offline
				formBean.setStrMsgType("");
				PatientFinalDischargeDATA.getPatientDeathDetails(formBean, _request);
				PatientFinalDischargeDATA.getPatientDischargeTypeCombo(formBean, _request);
				PatientFinalDischargeDATA.getRsnRmk(formBean, _request);
				PatientFinalDischargeDATA.getStrTreatmentResultCombo(formBean, _request);
			}
			else
			{
				if (formBean.getIsDead().equals("1")) formBean.setStrErrMsgString("Patient is dead !!");
				else if ((formBean.getStrAdmStatCode().equals("11") || formBean.getStrAdmStatCode().equals("17"))
						&& formBean.getStrClearForDischarge().equals("false")) formBean.setStrErrMsgString("Patient is on transit and His Marginal Time is Remained !!");
				else if (formBean.getStrAdmStatCode().equals("13")) formBean.setStrErrMsgString("Patient Not Reported in ward !!");
				else if (formBean.getStrAdmStatCode().equals("14")) formBean.setStrErrMsgString("Admission Cancelled !!");
				else if (formBean.getStrAdmStatCode().equals("15") && formBean.getStrClearForDischarge().equals("false")) formBean.setStrErrMsgString("Patient is on leave and His Marginal Time is Remained  !!");
				else if (formBean.getStrAdmStatCode().equals("19")) formBean.setStrErrMsgString("Patient is Transit In Service Areas(OT,Inv,SA)  !!");
				else formBean.setStrErrMsgString("Unknown Status !!");

				formBean.setStrCrNo("");
				ret = false;
			}
		}
		else
		{
			formBean.setStrErrMsgString("Belonging of the Patiet has not been Returned");
			formBean.setStrCrNo("");
			ret = false;
		}
		if (ret == true) return _mapping.findForward(strTarget);
		else return this.APPROVED(_mapping, _form, _request, _response);
	}

	public ActionForward getICDDetails(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, Exception, SQLException
	{
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		PatientFinalDischargeDATA.initIcdDiagnosis(formBean, _request, _response);
		return null;
	}

	public ActionForward getPatDischargeParameter(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, Exception, SQLException
	{
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		PatientFinalDischargeDATA.getPatientDischargeParameter(formBean, _request, _response);
		return null;
	}

	// Hospital Diagnosis
	public ActionForward FINALPDIAGNOSIS(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException
	{
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		PatientFinalDischargeDATA.initFinalDiagnosis(_request, _response, formBean);
		return null;
	}

	public ActionForward ICDDIAGNOSIS(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException
	{
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		PatientFinalDischargeDATA.initIcdDiagnosis(formBean, _request, _response);

		return null;
	}

	public ActionForward HOSITALPDIAGNOSIS(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException
	{
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		PatientFinalDischargeDATA.initHospitalDiagnosis(formBean, _request, _response);

		return null;
	}

	public ActionForward transOf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException
	{
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) form;
		String sid = "";
		String sid1 = "";
		String sid2 = "";
		String tmp[] = null;
		formBean.setStrSeatID(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strValmode = (String) request.getParameter("modName");
/*		IpdConfigUtil ipdC = new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrDiagType(ipdC.getStrDiagnosisType());
		formBean.setStrDischargeTypeAbsconded(ipdC.getStrDischargeTypeAbsconded());
		formBean.setStrDischargeTypeLAMA(ipdC.getStrDischargeTypeLAMA());
		formBean.setStrDischargeTypeReferral(ipdC.getStrDischargeTypeReferral());
		formBean.setStrDischargeTypeTransfer(ipdC.getStrDischargeTypeTransfer());
		formBean.setStrDischargeTypeDeath(ipdC.getStrDischargeTypeDeath());
		formBean.setStrDeathDetailsRequired(IpdConfigUtil.strDeathDetailsRequired);
		formBean.setStrNormalDischargeType(ipdC.getStrNormlaDischargeType());*/
		formBean.setStrAjaxVAL(strValmode);
		tmp = strValmode.replace('^', '#').split("#");
		if (tmp.length == 1)
		{
			formBean.setStrDischrg_Mode(strValmode);
			sid = PatientFinalDischargeDATA.getDischargeDtl(formBean, request);
		}
		else
		{
			formBean.setStrDischrg_Mode(tmp[0]);
			formBean.setStrIsMLC(tmp[1]);
			formBean.setStrDisplay_MLC_Dtls(tmp[2]);
			formBean.setStrDisplay_OPD_Visit_Dtls(tmp[3]);
			formBean.setStrDeptUnitCode(tmp[4]);
			// formBean.setStrIsMLC("1");//delete
			if (formBean.getStrIsMLC().trim().equals("1") && formBean.getStrDisplay_MLC_Dtls().trim().equals("1")) sid1 = PatientFinalDischargeDATA
					.getMLCDetails(formBean, request);
			if (formBean.getStrDisplay_OPD_Visit_Dtls().trim().equals("1")) sid2 = PatientFinalDischargeDATA.getOPDVisitDetails(formBean, request);
			sid = sid1 + "^" + sid2;

		}

		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(sid);

		return null;
	}
	
	public ActionForward DISCHARGEADVISEDBY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws HisException, Exception, SQLException
	{
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) form;
		String strValmode = (String) request.getParameter("curDept");
		strValmode=strValmode.replaceAll("@","^");
		formBean.setStrCurrentDeptUnitRoom(strValmode);
		PatientFinalDischargeDATA.getDischargeAdvisedBy(formBean, request,response);
		
		
		return null;
	}

	public void SHOWRPT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HisException
	{

		// System.out.println("inside CNT");

		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientFinalDischargeDATA.showReport(formBean, request, response);

	}

	public void ABSCONDEDREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws HisException
	{

		 //System.out.println("inside CNT");

		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientFinalDischargeDATA.showAbscondedReport(formBean, request, response);

	}
	public ActionForward LAMACONSENT(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response) throws HisException
	{
		String target = "consentForm";
		PatientFinalDischargeFB formBean = (PatientFinalDischargeFB) _form;
		PatientDetailVO patDtlVO=new PatientDetailVO();
		
		patDtlVO.setPatCrNo(_request.getParameter("crNo").toString());
		patDtlVO.setPatName(_request.getParameter("patName").toString());
		patDtlVO.setPatGenderAge(_request.getParameter("patAgeGender").toString());
		patDtlVO.setPatGuardianName(_request.getParameter("patFatherSpouse").toString());
		//patDtlVO.setPatAddress(_request.getParameter("patAdd").toString());
		IpdConfigUtil config = new IpdConfigUtil(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strConsentTemplateId=config.getStrConsentTemplateId();
		formBean.setStrConsentTemplateId(strConsentTemplateId);
		
		GenericTemplateUtility.setVOInInfoBean(_request, patDtlVO);
		return _mapping.findForward(target);
	}
	public ActionForward PRINT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientFinalDischargeFB fb = (PatientFinalDischargeFB) form;
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getConsentHtmlCode());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:1011400003660");
		IpdConfigUtil config = new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strConsentTemplateId=config.getStrConsentTemplateId();
		fb.setStrConsentTemplateId(strConsentTemplateId);
		return mapping.findForward("consentForm");
	}
	
}
