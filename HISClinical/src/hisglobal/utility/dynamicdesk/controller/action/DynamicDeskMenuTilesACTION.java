
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	Docter Desk
## Process/Database Object Name	:	Desk Tiles Details
## Purpose						:	online request raise from OPD Doctor Desk or OPD Bay Desk or IPD Doctor Desk. Doctor provide request slip to patient with complete medical certificate information like rest dates, fitness dates etc.
## Date of Creation				: 	
## Modification Log				:					
##		Modify Date				: 	16- December - 2014
##		Reason	(CR/PRS)		: 	in function INIT made check for IPD & set Ward Cord in DynamicDeskConfig
##		Modify By				: 	Akash Singh
*/
package hisglobal.utility.dynamicdesk.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.fb.DynamicDeskFB;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DynamicDeskMenuTilesACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		return INIT(objMapping_p,objForm_p,objRequest_p,objResponse_p);
	}

	public ActionForward INIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		DynamicDeskFB fb = (DynamicDeskFB) form;
		WebUTIL.refreshTransState(request); // Commented By Akash 2015.02.04 as required by New Desk
		HttpSession session = WebUTIL.getSession(request);	
		String deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		fb.setDeskType(deskType);

		DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE, fb.getDepartmentUnitCode());
		//DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE, );
		DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_LIST_KEY, fb.getSelListItemKey());
		
		// Setting Dynamic Desk Essentials From Session		
		System.out.println("selListItemKey  :"+request.getParameter("selListItemKey"));
		System.out.println("Unit Code  :"+request.getParameter("departmentUnitCode"));
		System.out.println("Desk Type  :"+fb.getDeskType());

		String arr[] = request.getParameter("selListItemKey").split("@");
		if((deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))||(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK))||(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_IN_TRANSIT_DOCTOR_DESK))||(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_ON_LEAVE_DOCTOR_DESK))||(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NON_ACPT_DOCTOR_DESK)))
		{
				fb.setWardCode(arr[4]);				
				DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE, fb.getWardCode());
				//if((arr[6]!= null)&& (!arr[6].equals(""))){DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE, arr[6]);}
		}
		DynamicDeskUTIL.getDynamicDeskEssentials(fb, request);
		// -- Fetching desk Id based on List Key through Procedure
		// -- Menu Detail based on Desk ID
		return mapping.findForward(fb.getDeskType());//"TILES");
	}

	
	public ActionForward AJX_G_DESKMENU_NONPAT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		System.out.println("DynamicDeskMenuTilesACTION.AJX_G_DESKMENU_NONPAT()");
		DynamicDeskFB objFB = (DynamicDeskFB) objForm_p;
		String strOutput = DynamicDeskUTIL.getDeskMenusList(objFB, DynamicDeskConfig.DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL, objRequest_p, objResponse_p);
		DynamicDeskUTIL.writeResponse(objResponse_p, strOutput);
		return null;	
	}

	public ActionForward AJX_G_DESKMENU_LEFT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		System.out.println("DynamicDeskMenuTilesACTION.AJX_G_DESKMENU_LEFT()");
		DynamicDeskFB objFB = (DynamicDeskFB) objForm_p;
		String strOutput = DynamicDeskUTIL.getDeskMenusList(objFB, DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL, objRequest_p, objResponse_p);
		DynamicDeskUTIL.writeResponse(objResponse_p, strOutput);
		return null;	
	}

	public ActionForward AJX_G_DESKMENU_RIGHT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		System.out.println("DynamicDeskMenuTilesACTION.AJX_G_DESKMENU_RIGHT()");
		DynamicDeskFB objFB = (DynamicDeskFB) objForm_p;
		String strOutput = DynamicDeskUTIL.getDeskMenusList(objFB, DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL, objRequest_p, objResponse_p);
		DynamicDeskUTIL.writeResponse(objResponse_p, strOutput);
		return null;	
	}

	public ActionForward AJX_G_DESKMENU_TOP(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		System.out.println("DynamicDeskMenuTilesACTION.AJX_G_DESKMENU_TOP()");
		DynamicDeskFB objFB = (DynamicDeskFB) objForm_p;
		String strOutput = DynamicDeskUTIL.getDeskMenusList(objFB, DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL, objRequest_p, objResponse_p);
		DynamicDeskUTIL.writeResponse(objResponse_p, strOutput);
		return null;	
	}

	public ActionForward AJX_G_DESKMENU_BOTTOM(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		System.out.println("DynamicDeskMenuTilesACTION.AJX_G_DESKMENU_BOTTOM()");
		DynamicDeskFB objFB = (DynamicDeskFB) objForm_p;
		String strOutput = DynamicDeskUTIL.getDeskMenusList(objFB, DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL, objRequest_p, objResponse_p);
		DynamicDeskUTIL.writeResponse(objResponse_p, strOutput);
		return null;	
	}
}
