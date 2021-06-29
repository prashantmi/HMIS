package new_investigation.transactions.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.echoFB;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.transactions.controller.utl.InvValueAuditUTIL;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.utl.invFungusProcessUTL;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.invFungusProcessVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.xml.sax.SAXException;

public class echoACT extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		return this.NEW(objMapping_p, objForm_p, objRequest_p, objResponse_p);
		
	}
	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		
		String reqdno=(String)objRequest_p.getParameter("requisitionDNo");
		String echodata=(String)objRequest_p.getParameter("echodata");
		
		echodata=echodata.replaceAll("@ampersand@" , "&");
		echodata=echodata.replaceAll("@percent@", "%");
	
		
		echoFB efb=new echoFB();
		
		objRequest_p.getSession().setAttribute("reqdno", reqdno);
		objRequest_p.getSession().setAttribute("echodata", echodata);
		objRequest_p.setAttribute("echodata", echodata);
		
		/*generateToken(objRequest_p);
		invFungusProcessFB objFB = (invFungusProcessFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
        String dno=objFB.getRequisitionDNo();

		//session.setAttribute("reqn1o", dno.substring(0,dno.length() - 2));
		session.setAttribute("reqn1o",dno);

	//	WebUTIL.refreshTransState(objRequest_p);
		//DynamicDeskUTIL.refreshSessionState(objRequest_p);
		invFungusProcessUTL.getEssential(objFB, objRequest_p);*/
		return objMapping_p.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		
		System.out.println("=======================save echo");
		/*generateToken(objRequest_p);
		invFungusProcessFB objFB = (invFungusProcessFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
        String dno=objFB.getRequisitionDNo();

		//session.setAttribute("reqn1o", dno.substring(0,dno.length() - 2));
		session.setAttribute("reqn1o",dno);

	//	WebUTIL.refreshTransState(objRequest_p);
		//DynamicDeskUTIL.refreshSessionState(objRequest_p);
		invFungusProcessUTL.getEssential(objFB, objRequest_p);*/
		return objMapping_p.findForward("NEW");
	}
	
}
