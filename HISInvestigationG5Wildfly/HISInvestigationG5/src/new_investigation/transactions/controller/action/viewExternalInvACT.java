package new_investigation.transactions.controller.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;


import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.viewExternalInvFB;
import new_investigation.transactions.controller.utl.InvValueAuditUTIL;
import new_investigation.transactions.controller.utl.MergeAllPdfNewInv;
import new_investigation.transactions.controller.utl.MongoXmlHandler;
import new_investigation.transactions.controller.utl.viewExternalInvUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class viewExternalInvACT extends CSRFGardTokenAction
{

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
	return this.NEW(mapping,form,request,response);
	}

	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		viewExternalInvFB objFB = (viewExternalInvFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
		WebUTIL.refreshTransState(objRequest_p);
		return objMapping_p.findForward("NEW");
	}
	
	
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		viewExternalInvFB fb = (viewExternalInvFB) form;
		
		fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
		
		viewExternalInvUTL.showPatDetails(fb,request);
		 
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward PRINTREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("viewInvestigationACT: printreport  ");
		viewExternalInvFB fb = (viewExternalInvFB) form;
		
		viewExternalInvUTL.printReport(fb,request,response);
		return null;
		//return mapping.findForward("NEW");
	}
	
	
	public ActionForward showFiles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		viewExternalInvFB fb = (viewExternalInvFB) form;
		
		//return an application file instead of html page
        response.setContentType(fb.getDowloadFilecontent());
        response.setHeader("Content-Disposition","attachment;filename="+fb.getDowloadFile()+"");
        
        try 
    	{
        	//Get it from file system
        	/*FileInputStream in = 
        		new FileInputStream(new File("C:\\bookmark.txt"));
        	*/
        	//Get it from web path
        	//jndi:/localhost/StrutsExample/upload/superfish.zip
        	//URL url = getServlet().getServletContext().getResource("upload/superfish.zip");
        	//InputStream in = url.openStream();
        	byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile1(fb.getDowloadFileid());
        	System.out.println("bytearray"+Pdf);
        	//Get it from bytes array
        //	byte[] bytes = new byte[4096];
            InputStream in = new ByteArrayInputStream(Pdf);

        	ServletOutputStream out = response.getOutputStream();
        	 
        	byte[] outputByte = new byte[Pdf.length];
        	//copy binary contect to output stream
        	while(in.read(outputByte, 0, Pdf.length) != -1)
        	{
        		out.write(outputByte, 0, Pdf.length);
        	}
        	in.close();
        	out.flush();
        	out.close();

    	}catch(Exception e){
    		e.printStackTrace();
    	}

		return null;
	}
	
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		viewExternalInvFB fb=(viewExternalInvFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}

	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		viewExternalInvFB fb = (viewExternalInvFB) form;
		
		fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
		
		viewExternalInvUTL.showPatDetails(fb,request);
		 
		return mapping.findForward("NEW");
	}
	
}
