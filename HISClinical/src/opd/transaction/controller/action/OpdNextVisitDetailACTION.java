package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdNextVisitDetailFB;
import opd.transaction.controller.util.OpdNextVisitDetailUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdNextVisitDetailACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OpdNextVisitDetailFB fb = (OpdNextVisitDetailFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		OpdNextVisitDetailUTIL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdNextVisitDetailFB fb = (OpdNextVisitDetailFB) form;
		boolean isSave = OpdNextVisitDetailUTIL.saveNextVisitDetail(fb, request);
		if(isSave)
		{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			Status objStatus = new Status();
			if(fb.getPatNextAptNo()!=null && !fb.getPatNextAptNo().equals(""))
			{
				objStatus.add(Status.TRANSINPROCESS, "", "Visit Summary Saved Successfully... Next Appointment Date:-"+fb.getEpisodeNextVisitDate()+"  Next Appointment No:-"+fb.getPatNextAptNo()+"    Next appointment slot:-"+fb.getPatNextAptSlot());
			}
			
			else
			{
				objStatus.add(Status.TRANSINPROCESS, "", "Visit Summary Saved Successfully");
			}
			
			fb.reset(mapping, request);			
			OpdNextVisitDetailUTIL.setEssentials(fb, request);
			
			request.setAttribute(Config.STATUS_OBJECT, objStatus);	
			
			
		}
		return mapping.findForward("NEW");
		
		
		/*HttpSession session=request.getSession();
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType==null)
			deskType="";
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_CMO_DESK))
			return mapping.findForward("LIST");
		else
			return mapping.findForward("NEW");*/
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdNextVisitDetailFB fb = (OpdNextVisitDetailFB) form;
		boolean isModify = OpdNextVisitDetailUTIL.modifyNextVisitDetail(fb, request);
		if(isModify)
		{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			Status objStatus = new Status();
			
			if(fb.getPatNextAptNo()!=null && !fb.getPatNextAptNo().equals(""))
			{
				objStatus.add(Status.TRANSINPROCESS, "", "Visit Summary Modified Successfully... Next Appointment Date:-"+fb.getEpisodeNextVisitDate()+"  Next Appointment No:-"+fb.getPatNextAptNo()+"    Next appointment slot:-"+fb.getPatNextAptSlot());
			}
			else
			{
				objStatus.add(Status.TRANSINPROCESS, "", "Visit Summary Modified Successfully");
			}
			//fb.reset(mapping, request);			
			//OpdNextVisitDetailUTIL.setEssentials(fb, request);
			request.setAttribute(Config.STATUS_OBJECT, objStatus);			
		}
		return mapping.findForward("NEW");
		
		
		/*HttpSession session=request.getSession();
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType==null)
			deskType="";
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_CMO_DESK))
			return mapping.findForward("LIST");
		else
			return mapping.findForward("NEW");*/
	}

	// Episode Keyword Popup
	public ActionForward ADDKEYWORDS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("POPUPKEYWORD");
	}

	// Progress Notes Popup
	public ActionForward POPUPNOTES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("POPUPNOTES");
	}

	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdNextVisitDetailFB fb = (OpdNextVisitDetailFB) form;
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		request.setAttribute(Config.STATUS_OBJECT, objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
	
	// Visit Summry Popup
	public ActionForward POPUPSUMMARY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdNextVisitDetailFB fb = (OpdNextVisitDetailFB) form;
		OpdNextVisitDetailUTIL.showVisitSummary(fb, request);
		return mapping.findForward("POPUPSUMMARY");
	}	

	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
		throws HisException, Exception, SQLException
	{
		OpdNextVisitDetailFB fb = (OpdNextVisitDetailFB) objForm_p;
		OpdNextVisitDetailUTIL.getVisitSummaryDetails(fb, objRequest_p);
		//OpdNextVisitDetailUTIL.setEssentials(fb, objRequest_p);
		StringBuffer strBuff= new StringBuffer();
		strBuff.append("{header:'Visit Summary',data:[");
		if(fb.getEpisodeDate()!=null)
			strBuff.append("{header:'First Visit Date',value:'" + fb.getEpisodeDate() + "'},");
		strBuff.append("{header:'Current Visit No',value:'" + fb.getEpisodeVisitNo() + "'}");
		//if(fb.getEpisodeIsOpen()!=null && !fb.getEpisodeIsOpen().equals(""))

		{
			if(fb.getEpisodeKeywords()!=null && !fb.getEpisodeKeywords().equals("")) strBuff.append(",{header:'Keywords',value:'" + fb.getEpisodeKeywords().replace("##",",") + "'}");
			if(fb.getVisitNotes()!=null && !fb.getVisitNotes().equals("")) strBuff.append(",{header:'Visit Notes',value:'" + fb.getVisitNotes() + "'}");
		}
		strBuff.append("]}");
		System.out.println("OpdNextVisitDetailACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
}
