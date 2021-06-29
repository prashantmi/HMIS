package inpatient.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.InpatientDetailFB;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InpatientDetailACT extends DispatchAction
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InpatientDetailFB fb = (InpatientDetailFB) form;
		System.out.println("InpatientDetailACT.execute()"+"   Patient CR No :"+fb.getPatCrNo());
		System.out.println("InpatientDetailACT.execute()"+"   Patient CR No :"+request.getParameter("patCrNo"));
		
		if(fb.getPatCrNo()!=null && !fb.getPatCrNo().trim().equals(""));
		else if (request.getParameter("patCrNo")!=null && !request.getParameter("patCrNo").trim().equals(""))
		{
			System.out.println("from req para::"+request.getParameter("patCrNo"));
			fb.setPatCrNo((String)request.getParameter("patCrNo"));
		}
		else if(request.getAttribute("patCrNo")!=null)
		{
			System.out.println("from req attr::"+request.getAttribute("patCrNo"));
			fb.setPatCrNo((String)request.getAttribute("patCrNo"));
		}
		else if(request.getSession().getAttribute("patCrNo")!=null)
		{
			System.out.println("from session::"+request.getSession().getAttribute("patCrNo"));
			fb.setPatCrNo((String)request.getSession().getAttribute("patCrNo"));
		}
		else
			return null;
			
		InpatientDetailUTL.getInpatientDetailByCrNo(fb, request);
		String app ="";
		if(request.getParameter("tileType")!=null)
			app = request.getParameter("tileType");
		HttpSession session = WebUTIL.getSession(request);	
		String deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if (fb.getIsIpd()!=null && fb.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE)) return mapping.findForward("IPD"+app);
		else if ((deskType!= null) && (deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))) return mapping.findForward("IPD"+app);
		else if ((deskType!= null) && ((deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK)) || (deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_IN_TRANSIT_DOCTOR_DESK))||(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NON_ACPT_DOCTOR_DESK))||(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_ON_LEAVE_DOCTOR_DESK)))) return mapping.findForward("IPD"+app);
		else return mapping.findForward("OPD"+app);
	}
}
