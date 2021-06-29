package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.fb.PatientTreatmentDetailFB;
import opd.transaction.controller.util.PatientTreatmentDetailUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PatientTreamentDetalACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	} 

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		PatientTreatmentDetailUTIL.setEssentials(fb, request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPD");
			return mapping.findForward("NEW");
		}
		else
		{
			return mapping.findForward("NEW");
		}
		
	}
	
	/*public ActionForward GETDRUGLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		//PatientTreatmentDetailUTIL.getSetDrugList(fb, request, response);
		return null;
	}*/
	
	public ActionForward GETEXTTREATMENTLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.getSetExtTreatmentList(fb, request, response);
		return null;
	}
	
	public ActionForward GETDRUGDOSELIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.getSetDrugDoseList(fb, request, response);
		return null;
	} 
	
	/*public ActionForward GETDRUGROUTELIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.getSetDrugRouteList(fb, request, response);
		return null;
	}*/


	public ActionForward ADDDRUGROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.addNewDrugRow(fb, request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPDDRUGDETAIL");
			return mapping.findForward("DRUGDETAIL");
		}
		else
		{
			return mapping.findForward("DRUGDETAIL");
		}
		
		
		
	}
	
	public ActionForward ADDEXTTREATMENTROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.addNewExtTretmentRow(fb, request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPDDRUGDETAIL");
			return mapping.findForward("DRUGDETAIL");
		}
		else
		{
			return mapping.findForward("DRUGDETAIL");
		}
		
		
	}
	
	public ActionForward SEARCHDRUG(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		System.out.println("searchDrug");
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.searchDrug(fb, request);
		return mapping.findForward("SEARCHDRUG");
	}

	public ActionForward POPULATESEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.populateSearchedDrug(fb, request, response);
		return null;
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		
		if(PatientTreatmentDetailUTIL.save(fb, request))
		{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			fb.reset(mapping, request);
			PatientTreatmentDetailUTIL.setEssentials(fb, request);
			Status status = new Status();
			status.add(Status.TRANSINPROCESS,"Treatment Detail Saved Successfully","");
			request.setAttribute(Config.STATUS_OBJECT, status);
			if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				//return mapping.findForward("IPDDRUGDETAIL");
				return mapping.findForward("DRUGDETAIL");
			}
			else
			{
				return mapping.findForward("DRUGDETAIL");
			}
			
		}
		else
		{
			fb.reset(mapping, request);
			if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				//return mapping.findForward("IPDDRUGDETAIL");
				return mapping.findForward("DRUGDETAIL");
			}
			else
			{
				return mapping.findForward("DRUGDETAIL");
			}
			
		}		
	}
	
	
	public ActionForward RxContinueDelRowFromTable(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.rxContinueDelRowFromTable(fb,request);
		//fb.reset(mapping,request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPDDRUGDETAIL");
			return mapping.findForward("DRUGDETAIL");	
		}
		else
		{
			return mapping.findForward("DRUGDETAIL");	
		}
			
	}
	
	public ActionForward RxContinueExtDelRowFromTable(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.rxContinueExtDelRowFromTable(fb,request);
		fb.reset(mapping,request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPDDRUGDETAIL");
			return mapping.findForward("DRUGDETAIL");	
		}
		else
		{
			return mapping.findForward("DRUGDETAIL");	
		}
				
	}

	public ActionForward DrugTime(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP);
		PatientTreatmentDetailUTIL.getDefaultShedule(fb,request);
		return mapping.findForward("DRUGTIME");		
	}
	
	public ActionForward SAVEDRUGSHEDULE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.saveDrugShedule(fb,request);
		return mapping.findForward("DRUGTIME");		
	}
	
	public ActionForward PREVDRUGSHEDULE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.getPrevDrugShedule(fb,request);
		return mapping.findForward("PREVDRUGSCHEDULE");		
	}
	public ActionForward INSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		//DrugAdministrationUTL.getIntruction(fb,request);
		return mapping.findForward("INSTRUCTION");	
	}
	
	public ActionForward ENTERDAYS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		//DrugAdministrationUTL.getIntruction(fb,request);
		return mapping.findForward("ENTERDAYS");	
	}
	
	public ActionForward OTHERINSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		String choice=(String)WebUTIL.getSession(request).getAttribute(OpdConfig.SELECTED_CHOICE);
		fb.setInstActivityType(choice);
		//DrugAdministrationUTL.getIntruction(fb,request);
		return mapping.findForward("OTHERINSTRUCTION");	
	}
	
	public ActionForward LOGDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		return mapping.findForward("LOGDETAIL");	
	}
	public ActionForward PRINT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		return mapping.findForward("PRINT");	
	}
	
	public ActionForward DRUGPROPERTIES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.getDrugProperty(fb,request);
		return mapping.findForward("DRUGPROPERTIES");	
	}
		
	public ActionForward SAVEOTHERINSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response); 	
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.saveOtherInstruction(fb,request);
		return mapping.findForward("OTHERINSTRUCTION");		
	}
	
	public ActionForward ALLINSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.getAllInstructionAndActivity(fb,request);
		fb.setInstActivityType("1");
		WebUTIL.setAttributeInSession(request, OpdConfig.SELECTED_CHOICE, "1");
		return mapping.findForward("OTHERINSTRUCTION");	
	}
	
	public ActionForward DEPTWISEINSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.getDeptWiseInstructionAndActivity(fb,request);
		fb.setInstActivityType("0");
		WebUTIL.setAttributeInSession(request, OpdConfig.SELECTED_CHOICE, "0");
		return mapping.findForward("OTHERINSTRUCTION");	
	}
	
	public ActionForward DELETEDRUGROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.deleteDrugRow(fb,request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPDDRUGDETAIL");
			return mapping.findForward("DRUGDETAIL");
		}
		else
		{
			return mapping.findForward("DRUGDETAIL");
		}
	}
	
	public ActionForward DELETEEXTROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		PatientTreatmentDetailUTIL.deleteExtRow(fb,request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPDDRUGDETAIL");
			return mapping.findForward("DRUGDETAIL");
		}
		else
		{
			return mapping.findForward("DRUGDETAIL");
		}
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		return mapping.findForward("LOGDETAIL");
	}
	
	public ActionForward DRUGINSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		return mapping.findForward("DRUGINSTRUCTION");
	}
	
	public ActionForward GETDRUGLISTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.getDrugListDetail(fb, request);
		return mapping.findForward("SEARCHDRUG");
	}
	
	public ActionForward POPULATEDRUGLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) form;
		PatientTreatmentDetailUTIL.populateDrugList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REVOKEREMARKS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)form;
		return mapping.findForward("REVOKEREMARKS");
	}
	
	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		StringBuffer strBuff= new StringBuffer();
		PatientTreatmentDetailFB fb = (PatientTreatmentDetailFB) objForm_p;
		strBuff = PatientTreatmentDetailUTIL.setEssentialsForTreatmentDetails(fb, objRequest_p);		
		System.out.println("PatientTreamentDetalACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
	

}
