/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN JOHN BASHA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST SAMPLE Master
 ## Purpose						        : 
 ## Date of Creation					: 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.masters.controller.action;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import new_investigation.masters.controller.fb.TestParameterMstFB;
import new_investigation.masters.controller.fb.UOMMstFB;
import new_investigation.masters.controller.utl.InvSampleMstUTIL;
import new_investigation.masters.controller.utl.TestParameterMstUTIL;
import new_investigation.masters.controller.utl.UOMMstUTL;
import new_investigation.transactions.controller.Helper.InvestigationViewTemplateActionForm;
import new_investigation.transactions.controller.Helper.ViewTemplate;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;

public class TestParameterMstACT extends GenericController
{
 
	String target = null;
	String message="";
	
	public TestParameterMstACT()
	{

		super(new TestParameterMstUTIL(),"/masters/TestParameterMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParameterMstFB fb = (TestParameterMstFB) form;
		WebUTIL.refreshTransState(request);
		request.getSession().removeAttribute("RESULTENTRYVO");
		request.getSession().setAttribute("title","Test Parameter Master" );	
		ControllerUTIL.setSysdate(request);
		TestParameterMstUTIL.fetchTestParameterCombos(fb, request);		
		//setTemplate(request, fb);
		return mapping.findForward("ADD");
	}

	@SuppressWarnings("unused")
	public ActionForward SETPARAMETER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParameterMstFB fb = (TestParameterMstFB) form;
	    boolean hasFlag=  TestParameterMstUTIL.TestParameter(fb, request);
	    fb.setHmode("ADD");
	    setTemplate(request, fb);
		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParameterMstFB fb = (TestParameterMstFB) form;
		boolean hasFlag=false;
		
		if(fb.getReqMasterFormType()!=null && fb.getReqMasterFormType().equals("2"))
		{
			try {
				
				hasFlag=	TestParameterMstUTIL.saveTestParameterMasterForm(fb, request);
				
				if(hasFlag==true)
				setTemplateMasterDataTestSelection(request,fb);
				
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
		 hasFlag=  TestParameterMstUTIL.saveTestParameter(fb, request);
		 setTemplate(request, fb);
		}
	    
		if (hasFlag)
			fb.reset(mapping, request);
			     
		return mapping.findForward("ADD");
	}

	 
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParameterMstFB fb = (TestParameterMstFB) form;
		WebUTIL.refreshTransState(request);		
		TestParameterMstUTIL.fetchTestParameter(fb, request);
		setTemplate(request, fb);
		return mapping.findForward("MODIFY"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParserConfigurationException
	{
		TestParameterMstFB fb = (TestParameterMstFB) form;
		

		ViewTemplate viewUtil = new ViewTemplate();			
		InvestigationViewTemplateActionForm viewForm=new InvestigationViewTemplateActionForm();
		viewForm.setSelectedTest(fb.getTestCode());	
		viewForm.setParaType(fb.getParaType());
		//viewUtil.ViewTemplate11(request, viewForm); //need to be uncomment when 1st time data insert in hivt_document_testwise break at initial point
		
		boolean hasFlag= TestParameterMstUTIL.savemodifyTestParameter(fb, request);
		
		
		setTemplate(request, fb);
		if(hasFlag){	
			message="Data Saved Sucessfully";
			LIST(mapping, fb, request, response);
			return mapping.findForward("LIST");
		}
		else
	    	 return mapping.findForward("MODIFY"); 

	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParameterMstFB fb = (TestParameterMstFB) form;
		WebUTIL.refreshTransState(request);
		TestParameterMstUTIL.fetchTestParameter(fb, request);
		return mapping.findForward("ADD");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParameterMstFB fb = (TestParameterMstFB) form;
		TestParameterMstUTIL.refetchTestParameter(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
	
	public void setTemplate(HttpServletRequest request, TestParameterMstFB fb)
	{
		ViewTemplate viewUtil = new ViewTemplate();			
		InvestigationViewTemplateActionForm viewForm=new InvestigationViewTemplateActionForm();
		viewForm.setSelectedTest(fb.getTestCode());	
		viewForm.setParaType(fb.getParaType());
		viewUtil.ViewTemplate(request, viewForm);
		/*try {
			//setTemplateMasterDataTestSelection(request,fb);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
/*	public ActionForward AJX_URL_DATA(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		TestParameterMstFB fb=(TestParameterMstFB)objForm_p;
		
		StringBuffer strBuff = TestParameterMstUTIL.ajaxUrlCombo(fb, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}
*/
	
	
	public void setTemplateMasterDataTestSelection(HttpServletRequest request, TestParameterMstFB fb) throws XPathExpressionException
	{
		ViewTemplate viewUtil = new ViewTemplate();			
		InvestigationViewTemplateActionForm viewForm=new InvestigationViewTemplateActionForm();
		viewForm.setSelectedTest(fb.getTestCodee());	
		viewForm.setParaType(fb.getParaType());
		viewForm.setMastertestCode(fb.getMastertestCode());
		
		//viewUtil.ViewTemplate(request, viewForm);
		viewUtil.RequisitionFormMasterDataTestSelectionTemplate(request, viewForm);
		
	}
	
	
	
	public ActionForward AJX_TEST_COMBO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		TestParameterMstFB fb=(TestParameterMstFB)objForm_p;
		
		TestParameterMstUTIL.fetchTestParameterCombosReqForm(fb, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print("");
		return null;
	}
	
}
