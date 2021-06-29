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
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.controller.utl.InvValueAuditUTIL;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.utl.invAntibioticProcessUTL;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.invAntibioticProcessVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.xml.sax.SAXException;

public class invAntibioticProcessACT extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		return this.NEW(objMapping_p, objForm_p, objRequest_p, objResponse_p);
		
	}
	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		invAntibioticProcessFB objFB = (invAntibioticProcessFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
        String dno=objFB.getRequisitionDNo();

		//session.setAttribute("reqn1o", dno.substring(0,dno.length() - 2));
		session.setAttribute("reqn1o",dno);

	//	WebUTIL.refreshTransState(objRequest_p);
		//DynamicDeskUTIL.refreshSessionState(objRequest_p);
		invAntibioticProcessUTL.getEssential(objFB, objRequest_p);
		return objMapping_p.findForward("NEW");
	}
	public ActionForward GETANTIBIOTICDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
		HttpSession session=WebUTIL.getSession(request);
		invAntibioticProcessFB fb = (invAntibioticProcessFB) form;
        String dno=fb.getRequisitionDNo();

		//session.setAttribute("reqn1o", dno.substring(0,dno.length() - 2));
		session.setAttribute("reqn1o",dno);

		//fb.setShowStatus("0");
		String isaddlist=fb.getIsaddlist();
          String growth=fb.getGrowthCode();
          String organism=fb.getOrganismCode();
          String reqnoo=fb.getRequisitionDNo();
		invAntibioticProcessUTL.getAntibioticName(fb,request);
		 fb.setShowStatus("0");
		 
		 
		 Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);
	 		
		                           if(mpBilled!=null)
				                    {

				            			List<invAntibioticProcessVO> hyprlistdata1=new ArrayList<invAntibioticProcessVO>();

				          Iterator itr=mpBilled.keySet().iterator();
				          Iterator itr1=mpBilled.keySet().iterator();
				          while(itr1.hasNext())
					 		{
				        	  String organismm="";
				        		String organisgm1=(String)itr1.next();
				        		organismm=organisgm1;
				        		organisgm1=organisgm1.split("#")[1];
					 			 if(organism.equals(organisgm1))
					 			 {
					 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organismm);
					 			antibioticprocessVO rowSpanSize=lstVOSample.get(0);
					 			growth=rowSpanSize.getGrowth();
					 			
					 			break;
					 			 }
					 			 else
					 			 {
					 				 growth="-1";
					 			 }
					 		}
				                    }
		fb.setGrowthCode(growth);
		
		if(isaddlist!=null && isaddlist.equals("2"))
		{
			fb.setIsaddlist("");
			
		}
		
		request.setAttribute("organismcodee", fb.getOrganismCode());
		//request.setAttribute("reqno", reqnoo.substring(0,reqnoo.length() - 2));
		if(fb.getCounterrr()!=null && fb.getCounterrr().equals("0") )
		{
     	   fb.setCounterrr(null);
		}
		else
			fb.setCounterrr("1");
		
		request.setAttribute("reqno", reqnoo);
           
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETANTIBIOTICDETAILS1(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invAntibioticProcessFB fb = (invAntibioticProcessFB) form;
		//fb.setShowStatus("0");
          
		invAntibioticProcessUTL.getAntibioticName(fb,request);
		 fb.setShowStatus("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		invAntibioticProcessFB fb=(invAntibioticProcessFB)form;
		Status  objStatus=new Status();
		String reqnoo=(String)session.getAttribute("reqn1o");
			String orgnaismcode12=(String)session.getAttribute("organismcodee");

			
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		request.setAttribute("organismcodee", orgnaismcode12);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
		 validateToken(request, response);
		invAntibioticProcessFB fb = (invAntibioticProcessFB) form;
		
		String isaddlist=fb.getIsaddlist();
		//fb.setShowStatus("0");
          
		invAntibioticProcessUTL.saveSearchData(fb,request);
		
		if(isaddlist!=null && isaddlist.equals("1"))
		{
			fb.setIsaddlist("");
			
		}
		fb.setCounterrr(null);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SAXException, IOException, ParserConfigurationException{
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invAntibioticProcessFB fb = (invAntibioticProcessFB) form;
		//fb.setShowStatus("0");
          
		invAntibioticProcessUTL.saveSearchData(fb,request);
		 
		return mapping.findForward("NEW");
	}
	

	public ActionForward ADDDATAINLIST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		HttpSession ss=objRequest_p.getSession();
		invAntibioticProcessFB fb=(invAntibioticProcessFB)objForm_p;
		String growth=fb.getGrowthCode();
		String organismcode= invAntibioticProcessUTL.adddatainlist(fb, objRequest_p);
		//System.out.println("strBuff act"+strBuff);
		fb.setOrganismCode(organismcode);
		fb.setGrowthCode(growth);
		fb.setIsaddlist("1");
		objResponse_p.setHeader("Cache-Control", "no-cache");
//		ss.setAttribute("reqn1o", fb.getRequisitionNo());
		ss.setAttribute("reqn1o", fb.getRequisitionDNo());

		objResponse_p.getWriter().print("");
		return objMapping_p.findForward("NEW");
	}
	
	
	
	public ActionForward AJX_GET_LIST_ANTIBIOITC(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws IOException 
	{
		invAntibioticProcessFB fb=(invAntibioticProcessFB)objForm_p;
		StringBuffer strBuff = null;
		//StringBuffer strBuff = invAntibioticProcessUTL.modifyPriority(fb, objRequest_p);
		invAntibioticProcessUTL.getAntibioticName(fb,objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		//objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}

}
