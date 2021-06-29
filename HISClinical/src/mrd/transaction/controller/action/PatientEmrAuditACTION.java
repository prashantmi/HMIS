package mrd.transaction.controller.action;
/**
 * @author : Adil Wasi
 * Creation Date: 06-Jun-2012
 */
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.PatientEmrAuditFB;
import mrd.transaction.controller.utl.PatientEmrAuditUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientEmrAuditACTION extends CSRFGardTokenAction{
	
	/**
	 * Unspecified
	 * 
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse_p) throws Exception
	{
		return this.NEW(objMapping_p, objForm_p, objRequest_p, objResponse_p);
	}
	
	
	/**
	 * NEW
	 * 
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		PatientEmrAuditFB objPatientEmrAuditFB=(PatientEmrAuditFB)objForm_p;
		WebUTIL.refreshTransState(objRequest_p);	
		ControllerUTIL.setSysdate(objRequest_p);
		/*fb.setHmode("NEW");
		fb.setTempMode(fb.getHmode());
		boolean flag=PatientEmrAuditUTL.getDeptUnitList(objPatientEmrAuditFB,objRequest_p);*/
		PatientEmrAuditUTIL.getAuditTypeWiseList(objPatientEmrAuditFB,objRequest_p);
		objPatientEmrAuditFB.setStrEmrAuditTypeId("-1");
		/*objPatientEmrAuditFB.setSelection("0");
		objPatientEmrAuditFB.setHideDuplicateLabel("false");*/
		//objPatientEmrAuditFB.reset(objMapping_p, objRequest_p);
		Status objStatus=new Status();
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(objRequest_p,objStatus);
		return objMapping_p.findForward("NEW");
	}
	
	
	/**
	 * Get Patient Detail
	 * 
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETPATDTL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse_p) throws Exception
	{
		PatientEmrAuditFB objPatientEmrAuditFB=(PatientEmrAuditFB)objForm_p;
		//PatientEmrAuditUTIL.getPatientCaseSheetDtlByCrNo(objPatientEmrAuditFB,objRequest_p);
		PatientEmrAuditUTIL.getPatientEmrAuditDtlByCrNo(objPatientEmrAuditFB,objRequest_p);
		//objPatientEmrAuditFB.setSelection("1");
		Status objStatus=new Status();
		objStatus.add(Status.LIST);
		//objStatus.add(Status.NEW);
		WebUTIL.setStatus(objRequest_p,objStatus);
		return objMapping_p.findForward("NEW");
	}
	
	
	/**
	 * Get Audit User through Ajax
	 * 
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETAUDITUSER_AJAX(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse_p) throws Exception
	{
		PatientEmrAuditFB objPatientEmrAuditFB=(PatientEmrAuditFB)objForm_p;
		ControllerUTIL.setSysdate(objRequest_p);
		Status objStatus=new Status();
		objStatus.add(Status.LIST);
		WebUTIL.setStatus(objRequest_p,objStatus);
		StringBuffer strBuff = PatientEmrAuditUTIL.getAuditUserList(objPatientEmrAuditFB, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		
		return null;
		/*Status objStatus=new Status();
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(objRequest_p,objStatus);
		return objMapping_p.findForward("NEW");*/
	}
	
	/**
	 * PAGINATION
	 * 
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 * @throws Exception
	 */
	public ActionForward PAGINATION(ActionMapping objMapping_p,ActionForm objForm_p,HttpServletRequest objRequest_p,HttpServletResponse objResponse_p)
	{
//		PatientEmrAuditFB objPatientEmrAuditFB=(PatientEmrAuditFB)objForm_p;
		Status  objStatus=new Status();
		objStatus.add(Status.LIST);
		WebUTIL.setStatus(objRequest_p,objStatus);
		return objMapping_p.findForward("NEW");
	}	
	
	/**
	 * SHOW PATIENT EMR DIALTILE 
	 * 
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 * @throws Exception
	 */
	public ActionForward SHOWPATIENTEMRDIALTILE(ActionMapping objMapping_p,ActionForm objForm_p,HttpServletRequest objRequest_p,HttpServletResponse objResponse_p)
	{
		PatientEmrAuditFB objPatientEmrAuditFB=(PatientEmrAuditFB)objForm_p;
		
		PatientEmrAuditUTIL.showPatientEmrAuditDiagnosisDialTileByPrimaryKey(objPatientEmrAuditFB,objRequest_p);
		/*Status objStatus=new Status();
		objStatus.add(Status.LIST);
		WebUTIL.setStatus(objRequest_p,objStatus);*/
		//System.out.println("strPrimaryKey :"+objPatientEmrAuditFB.getStrPrimaryKey());
		return objMapping_p.findForward("MOVEMENT");
	}
	
	/**
	 * SAVE AUDIT DETAIL
	 * 
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 * @throws Exception
	 */
	public ActionForward SAVEAUDITDETAIL(ActionMapping objMapping_p,ActionForm objForm_p,HttpServletRequest objRequest_p,HttpServletResponse objResponse_p) throws Exception
	{
		validateToken(objRequest_p,objResponse_p);
		PatientEmrAuditFB objPatientEmrAuditFB=(PatientEmrAuditFB)objForm_p;
		if(PatientEmrAuditUTIL.savePatientEmrAuditDtl(objPatientEmrAuditFB,objRequest_p))
		{
			objPatientEmrAuditFB.setPatCrNo(objPatientEmrAuditFB.getStrSearchPatCrNo());
			PatientEmrAuditUTIL.getPatientEmrAuditDtlByCrNo(objPatientEmrAuditFB,objRequest_p);
			Status objStatus=new Status();
			objStatus.add(Status.LIST,"Record Saved Successfully","");
			objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
			return objMapping_p.findForward("NEW");
		}
		else
		{
			return objMapping_p.findForward("MOVEMENT");
		}
		
	}
	
	/** Back To the Init Page
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 */
	public ActionForward CANCEL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		
		PatientEmrAuditFB objPatientEmrAuditFB=(PatientEmrAuditFB)objForm_p;
		objPatientEmrAuditFB.setPatCrNo(objPatientEmrAuditFB.getStrSearchPatCrNo());
		Status objStatus=new Status();
		objStatus.add(Status.LIST,"","");
		objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
		return this.GETPATDTL(objMapping_p, objForm_p, objRequest_p, objResponse_p);
	}
	
	/** Back To the Init Page
	 * @param objMapping_p
	 * @param objForm_p
	 * @param objRequest_p
	 * @param objResponse_p
	 * @return
	 */
	public ActionForward FINALCANCEL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p)
	{
		return objMapping_p.findForward("CANCEL");
	}
}
