package medicalboard.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.fb.MedicalBoardInitializationFB;
import medicalboard.transactions.controller.utl.MedicalBoardInitializationUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MedicalBoardInitializationACT extends CSRFGardTokenAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		WebUTIL.refreshTransState(request);
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.NEW_BOARD_ADD_LIST);
		fb.reset(mapping, request);
		MedicalBoardInitializationUTL.setMedicalBoardInitializationEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward GETSCHEDULELIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		//fb.setAssignBoardNoArray(null);
		//fb.setSelectedBoardArray(null);
		MedicalBoardInitializationUTL.getScheduleList(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward GETBOARDDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY);
		session.removeAttribute(MedicalBoardConfig.NEW_BOARD_ADD_LIST);
		MedicalBoardInitializationUTL.getBoardDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDDOCROLEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.AddDocRoleRow(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEDOCROLEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.RemovedDocRoleROW(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward ADDESCORTEDROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.AddEscortedRow(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEESCORTEDROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.RemoveEscoretedRow(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTEAMDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.getTeamDetailByBoardId(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.saveBoardAndBoardTeamDetail(fb, request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	
	
	public ActionForward GETASSIGNTEAMDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.getAssignTeamDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDASSIGNDOCROLEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.AddAssignDocRoleRow(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEASSIGNDOCROLEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.RemovedAssignDocRoleROW(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward ADDASSIGNESCORTEDROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.AddAssignEscortedRow(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEASSIGNESCORTEDROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.RemoveAssignEscoretedRow(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDNEWBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY);
		//session.removeAttribute(MedicalBoardConfig.ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD);
		//session.removeAttribute(MedicalBoardConfig.ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD);
		MedicalBoardInitializationUTL.getAvailableBoardDetailList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTEAMDETAILNEWBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.getTeamDetailByBoardIdForNewBoard(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDDOCROLEROWNEWBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.AddDocRoleRowForNewBoard(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEDOCROLEROWNEWBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.RemovedDocRoleROWForNewBoard(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward ADDESCORTEDROWNEWBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.AddEscortedRowForNewBoard(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEESCORTEDROWNEWBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.RemoveEscoretedRowForNewBoard(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVENEWBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.addNewBoardAndBoardTeamDetail(fb, request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward DELETEBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.RemoveBoard(fb, request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.modifyAssignBoardDetail(fb, request);
		return mapping.findForward("NEW");
		//return this.NEW(mapping, form, request, response);
	}
	
	
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		MedicalBoardInitializationUTL.updateAssignBoardAndBoardTeamDetail(fb, request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward REFERESHPAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb=(MedicalBoardInitializationFB)form;
		fb.setDivMessage("");
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE);
		session.removeAttribute(MedicalBoardConfig.NEW_BOARD_ADD_LIST);
		session.removeAttribute(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY);
		MedicalBoardInitializationUTL.refreshPage(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardInitializationFB fb = (MedicalBoardInitializationFB) form;
		Status objStatus= new Status();
		
		//objStatus.add(Status.RECORDFOUND);
		objStatus.add(Status.LIST);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("POPUP");
	}
	
	
	
}
