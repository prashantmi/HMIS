package new_investigation.transactions.controller.action;

import hisglobal.backutil.exception.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.utl.SampleCollectionUTL;
import new_investigation.transactions.delegate.InvestigationEssentialDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SampleCollectionAdvancedACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		SampleCollectionFB fb=(SampleCollectionFB)form;
		return this.NEW(mapping,form,request,response);
	}          
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		SampleCollectionFB fb=(SampleCollectionFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
         
		
		if(fb.getShowStatus()!=null){
			if(fb.getShowStatus().equals("1"))
			{
				fb.setShowStatus("1");
			}
			else if(fb.getShowStatus().equals("3"))
			{
				fb.setShowStatus("0");
			fb.setIsSampleAreaSelected("");
			
			}
			else
			{
				
				fb.setShowStatus("0");
			}
			}
		else
			fb.setShowStatus("0");
		
		
		
		
        //
		if(fb.getIsSampleAreaSelected()!=null){
			if(fb.getIsSampleAreaSelected().equals("1"))
			{
				fb.setIsSampleAreaSelected("1");
			}else
				fb.setIsSampleAreaSelected("0");
		}
		else
			fb.setIsSampleAreaSelected("0");

		
		
		//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
		if(!fb.getIsSampleAreaSelected().equals("1"))
		{
			WebUTIL.refreshTransState(request);
			fb.setLabTestCodeArray("");
			SampleCollectionUTL.getSampleCollectionArea(fb,request);
		}
		else
		{
			
			SampleCollectionUTL.getPatListSampleColAdvance(fb,request);
		}
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETPATLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATList  ");
		//WebUTIL.refreshTransState(request);
		SampleCollectionFB fb = (SampleCollectionFB) form;
		String mode=fb.getModebarcode();
		//fb.setShowStatus("0");

		SampleCollectionUTL.getPatListSampleColAdvance(fb,request);
		System.out.println("Fb "+fb.getFromDate());
		Date date1;
		Date date2;
		try {
			date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fb.getFromDate());
			date2 = new SimpleDateFormat("dd-MMM-yyyy").parse(fb.getToDate());
			WebUTIL.setAttributeInSession(request,Config.SELECTED_FROM_DATE,date1);
			WebUTIL.setAttributeInSession(request,Config.SELECTED_TO_DATE,date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		fb.setModebarcode(mode);
		return mapping.findForward("NEW");
	}
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SHOWPATDETAILS  ");
		SampleCollectionFB fb = (SampleCollectionFB) form;
		fb.setShowStatus("1");
              
		SampleCollectionUTL.showPatDetails(fb,request);
            if(fb.getSampleAreaCode().equals("15"))
            	fb.setFlagforipddesk("16");
		//fb.setFlagforipddesk("11");
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleCollectionFB fb=(SampleCollectionFB)form;
		Status  objStatus=new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleCollectionFB fb=(SampleCollectionFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)  throws Exception
	 {
		 HttpSession session=WebUTIL.getSession(request);
		 validateToken(request, response);
		 SampleCollectionFB fb=(SampleCollectionFB)form;
		 SampleCollectionUTL.saveSampleCollectionDetails(fb, request);
		 if(fb.getFlagforipddesk()!=null && fb.getFlagforipddesk().equals("15"))
		 fb.setFlagforipddesk("16");
		 else
			 fb.setShowStatus("0");
		 session.removeAttribute("deskcrno");
	     return this.NEW(mapping, form, request, response);
	 } 
	 
	 

		/**
		 * AJX_DUPLICACY_SAMPLENO     
	 	* Ajax Function for Checking Duplicacy
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward AJX_DUPLICACY_SAMPLENO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionUTL.checkSampleNoDuplicacy(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
		
		
		/**
		 * AJX_CHECK_AUTO_SAMPLENO_GEN     
	 	* Ajax Function for Checking Duplicacy
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward AJX_CHECK_AUTO_SAMPLENO_GEN(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionUTL.checkAutoGenFormate(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
		public ActionForward PRINT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
			 
			  
			return mapping.findForward("PRINT"); 
			
		}
		
		public ActionForward GETPATLISTBAROCDE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: GETPATList  ");
			//WebUTIL.refreshTransState(request);
			SampleCollectionFB fb = (SampleCollectionFB) form;
			//fb.setShowStatus("0");
              
			SampleCollectionUTL.getPatListBarcode(fb,request);
			fb.setIsSampleAreaSelected("0");
            fb.setSampleAreaCode("-1");
            fb.setPatCRNo("");
            fb.setFromDate("");
            fb.setToDate("");
           fb.setStatuschange("1");
			return mapping.findForward("NEW");
		}
		
		
		public ActionForward DUPLICATEBARCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws BarcodeException, OutputException{
			System.out.println("InvestigationRaisingDtlACT: DUPLICATEBARCODE  ");
			//WebUTIL.refreshTransState(request);
			SampleCollectionFB fb = (SampleCollectionFB) form;
			//fb.setShowStatus("0");

			SampleCollectionUTL.duplicateBarCodeDetails(fb,request);
			fb.setShowStatus("0");
			fb.setIsSampleAreaSelected("0");
            fb.setSampleAreaCode("-1");
            fb.setPatCRNo("");
            fb.setFromDate("");
            fb.setToDate("");
			/*return this.NEW(mapping, form, request, response);*/
			return mapping.findForward("NEW");
		}
		
		

		public ActionForward AJX_CHECK_REQ_FORM_MASTER(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionUTL.checkAutoGenFormate(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
		public ActionForward AJX_CHECK_REQFORM_TESTTYPE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionUTL.getRequisitionFormMasterData(fb, objRequest_p);
			//System.out.println("strBuff act"+strBuff);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		

		public ActionForward NEWW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
			 generateToken(request);
			SampleCollectionFB fb=(SampleCollectionFB)form;
			HttpSession session=WebUTIL.getSession(request);
			Date dt=new Date();
			String iscallfromdesk="0";
			//session.setAttribute("SYSDATEOBJECT",dt);
			
			if(fb.getShowStatus()!=null){
				if(fb.getShowStatus().equals("1"))
				{
					fb.setShowStatus("1");
				}
				else if(fb.getShowStatus().equals("3"))
				{
					fb.setShowStatus("0");
				fb.setIsSampleAreaSelected("");
				
				}
				else
				{
					
					fb.setShowStatus("0");
				}
				}
			else
				fb.setShowStatus("0");
			
			
			
			
	        //
			if(fb.getIsSampleAreaSelected()!=null){
				if(fb.getIsSampleAreaSelected().equals("1"))
				{
					fb.setIsSampleAreaSelected("1");
				}else
					fb.setIsSampleAreaSelected("0");
			}
			else
				fb.setIsSampleAreaSelected("0");

			
			
			
			if(fb.getWardCode()!=null)
			{
				fb.setFlagforipddesk("1");
				UserVO userVO=ControllerUTIL.getUserVO(request);
				String flag=fb.getWardCode();
				InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();

				String collarea=daoDelegate.getcollectionareafromward(flag,userVO.getHospitalCode());
			
				SampleCollectionUTL.getPatListSampleColAdvance(fb,request);
				fb.setFlagforipddesk("10");
				fb.setSampleAreaCode(collarea);
				
			}
			else
			{
			if(!fb.getIsSampleAreaSelected().equals("1") && fb.getWardCode()==null)
			{
				WebUTIL.refreshTransState(request);
				fb.setLabTestCodeArray("");
				SampleCollectionUTL.getSampleCollectionArea(fb,request);
			}
			else
				SampleCollectionUTL.getPatListSampleColAdvance(fb,request);
			
			}
			
			
			
			 session.setAttribute("deskcrno", fb.getPatCrNo());
			 
			return mapping.findForward("NEW");
		}
		
		
		
		
		
}

