package new_investigation.masters.controller.action;


import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.PDFPrintingFB;
import new_investigation.masters.controller.utl.PDFPrintingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PDFPrintingACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PDFPrintingFB fb = (PDFPrintingFB) form;
	//	//LOGGER_INV.log(Level.INFO,"ResultPrintingACTION ACTION DISPATCH: UNSPECIFIED");
		return this.PDFPRINTING(mapping,fb,request,response);
	}
	public ActionForward PDFPRINTING(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PDFPrintingFB fb=(PDFPrintingFB) form;
	//	//LOGGER_INV.log(Level.INFO,"ResultPrintingACTION ACTION DISPATCH: RESULTPRINTING");
		PDFPrintingUTIL.setResultPrintingEssential(request,fb);
		return mapping.findForward("RESULTPRINTING");
	}
	// this is the generic function that is used to populate the combos in the result validation process
	
	
	public ActionForward PATIENTWISERESULTPRINTING(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PDFPrintingFB fb=(PDFPrintingFB) form;			
	//	//LOGGER_INV.log(Level.INFO,"ResultPrintingACTION ACTION DISPATCH: PATIENTWISERESULTPRINTING");
		request.getSession().removeAttribute("PAGELIST");
		//ResultEntryUTIL.setResultEntryEssential(request,fb);
		return mapping.findForward("RESULTPRINTING");
	}
	public ActionForward OUTSIDEPATIENTWISERESULTPRINTING(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PDFPrintingFB fb=(PDFPrintingFB) form;
		//LOGGER_INV.log(Level.INFO,"ResultPrintingACTION ACTION DISPATCH: OUTSIDEPATIENTWISERESULTPRINTING");
		request.getSession().removeAttribute("PAGELIST");
		PDFPrintingUTIL.getTestEssentialforResultPrinting(request, fb);
		return mapping.findForward("RESULTPRINTING");
	}		
	
	public ActionForward RESULTPRINTINGLISTING(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PDFPrintingFB fb=(PDFPrintingFB) form;
		//LOGGER_INV.log(Level.INFO,"ResultPrintingACTION ACTION DISPATCH: RESULTPRINTINGLISTING");
		PDFPrintingUTIL.getworkOrderListForPdfPrinting(request,fb);
		return mapping.findForward("RESULTPRINTING");
	} 
	
	
	public ActionForward GETBYTEARRAY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PDFPrintingFB fb=(PDFPrintingFB) form;
		//LOGGER_INV.log(Level.INFO,"ResultPrintingAction ACTION DISPATCH: SAVERESULTS");
		//LOGGER_INV.log(Level.INFO,request.getParameter("Index"));
		//LOGGER_INV.log(Level.INFO,request.getParameter("isConfidential"));
		String isConfiString="";
		String password=request.getParameter("enteredPassword");
		//LOGGER_INV.log(Level.INFO,"password------->"+password);
		if(request.getParameter("isConfidential").equals("1"))
		{
			isConfiString=request.getParameter("isConfidential");
			
		}
		else
		{
			isConfiString=null;				
		}			
		
		PDFPrintingUTIL.getPdfByteArray(request,request.getParameter("Index"),response,isConfiString,password );
		return null;	
	}
		
	public ActionForward GETPRINTINGTEMPLATEVIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PDFPrintingFB fb=(PDFPrintingFB) form;
		PDFPrintingUTIL.getPdfByteArrayForPrintingTemplate(request,response);
		return null;	
	}
}
