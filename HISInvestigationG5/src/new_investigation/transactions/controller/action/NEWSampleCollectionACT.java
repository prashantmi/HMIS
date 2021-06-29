package new_investigation.transactions.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
<!--
 
## Copyright Information				: C-DAC, Noida  
## Project Name				       		: NIMS
## Name of Developer		 			: PAWAN KUMAR B N
## Module Name					        : INVESTIGATION
## Process/Database Object Name	    	: Sample Collection

## Purpose						        : 
## Date of Creation						: 23-Feb-2015
## Modification Log						:				
## Modify Date					        :  
## Reason	(CR/PRS)			    	: 
## Modify By				        	: 


*/


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import hisglobal.backutil.exception.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import hisglobal.presentation.CSRFGardTokenAction;



import hisglobal.vo.UserVO;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.fb.InvTrackingReportFB;
import new_investigation.reports.controller.utl.InvTrackingReportUTIL;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
//import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.utl.NewSampleCollectionUTL;
import new_investigation.transactions.dao.SampleCollectionDAO;
import new_investigation.transactions.delegate.InvestigationEssentialsamplecollDelegate;
import new_investigation.vo.Inv_SampleCollectionVO;
 
public class NEWSampleCollectionACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		SampleCollectionFB fb=(SampleCollectionFB)form;
		return this.NEW(mapping,form,request,response);
	}          
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		

		// request.removeAttribute("patCrNo") ;
		// request.getAttribute("wardCode") ;
		// request.getAttribute("patStatusCode") ;

		generateToken(request);
		SampleCollectionFB fb=(SampleCollectionFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
         String isDuplicateBarecodeprint=fb.getDuplicateBarcodeGeneration()==null?"":fb.getDuplicateBarcodeGeneration();
		
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
			NewSampleCollectionUTL.getSampleCollectionArea(fb,request);
		}
		else
		{
			
			NewSampleCollectionUTL.getPatList(fb,request);
		}
		
		fb.setDuplicateBarcodeGeneration("2");
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETPATLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATList  ");
		//WebUTIL.refreshTransState(request);
		SampleCollectionFB fb = (SampleCollectionFB) form;
		String mode=fb.getModebarcode();
		//fb.setShowStatus("0");

		NewSampleCollectionUTL.getPatList(fb,request);
		System.out.println("Fb "+fb.getFromDate());
		fb.setPatCRNo(null);
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
              
		NewSampleCollectionUTL.showPatDetails(fb,request);
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

		 NewSampleCollectionUTL.saveSampleCollectionDetails(fb, request);
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
			
			StringBuffer strBuff = NewSampleCollectionUTL.checkSampleNoDuplicacy(fb, objRequest_p);
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
			
			StringBuffer strBuff = NewSampleCollectionUTL.checkAutoGenFormate(fb, objRequest_p);
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
              String todate=fb.getToDate();
              String fromdate=fb.getFromDate();
              String crno=fb.getPatCrNo();
			NewSampleCollectionUTL.getPatListBarcode(fb,request);
			//fb.setIsSampleAreaSelected("0");
			fb.setIsSampleAreaSelected("0");
      			fb.setShowStatus("3");

			fb.setSampleAreaCode("-1");
            fb.setDuplicateBarcodeGeneration("0");
          //  fb.setShowStatus("0");
            request.getSession().setAttribute("todate_dup", todate);
            request.getSession().setAttribute("fromdate_dup", fromdate);
            request.getSession().setAttribute("crno_dup", crno);

            
         /*   fb.setPatCRNo("");
            fb.setFromDate("");
            fb.setToDate("");*/
           fb.setStatuschange("1");
			return mapping.findForward("NEW");
		}
		
		
		public ActionForward DUPLICATEBARCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws BarcodeException, OutputException{
			System.out.println("InvestigationRaisingDtlACT: DUPLICATEBARCODE  ");
			//WebUTIL.refreshTransState(request);
			SampleCollectionFB fb = (SampleCollectionFB) form;
			//fb.setShowStatus("0");
           String todate= (String) request.getSession().getAttribute("todate_dup");
           String fromdate= (String) request.getSession().getAttribute("fromdate_dup");
           String crno= (String) request.getSession().getAttribute("crno_dup");
            fb.setToDate(todate);
            fb.setFromDate(fromdate);
            fb.setPatCrNo(crno);
            
			NewSampleCollectionUTL.duplicateBarCodeDetails(fb,request);
			fb.setShowStatus("0");
			fb.setIsSampleAreaSelected("1");
            fb.setSampleAreaCode("-1");
            fb.setFromDate(fromdate);
            fb.setToDate(todate);
            fb.setPatCrNo(crno);
            /* fb.setPatCRNo("");
            fb.setFromDate("");
            fb.setToDate("");*/
			fb.setDuplicateBarcodeGeneration("0");
			//fb.setFlagforipddesk("90");
			
			/*return this.NEW(mapping, form, request, response);*/
			return this.GETPATLISTBAROCDE(mapping, form, request, response);
			//return mapping.findForward("GETPATLISTBAROCDE");
		}
		
		

		public ActionForward AJX_CHECK_REQ_FORM_MASTER(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = NewSampleCollectionUTL.checkAutoGenFormate(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
		public ActionForward AJX_CHECK_REQFORM_TESTTYPE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = NewSampleCollectionUTL.getRequisitionFormMasterData(fb, objRequest_p);
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

			
			
			//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
			if(fb.getWardCode()!=null)
			{
				fb.setFlagforipddesk("1");
				UserVO userVO=ControllerUTIL.getUserVO(request);
				String flag=fb.getWardCode();
				InvestigationEssentialsamplecollDelegate   daoDelegate=new InvestigationEssentialsamplecollDelegate();

//				String collarea=daoDelegate.getcollectionareafromward(flag,userVO.getHospitalCode(),userVO.getUserSeatId());
				String collarea=daoDelegate.getcollectionareafromward(flag,userVO.getHospitalCode());

				fb.setSampleAreaCode(collarea);
				fb.setFlagforipddesk("10");
				NewSampleCollectionUTL.getPatList(fb,request);
				
				
				
			}
			else
			{
			if(!fb.getIsSampleAreaSelected().equals("1") && fb.getWardCode()==null)
			{
				WebUTIL.refreshTransState(request);
				fb.setLabTestCodeArray("");
				NewSampleCollectionUTL.getSampleCollectionArea(fb,request);
			}
			else
				NewSampleCollectionUTL.getPatList(fb,request);
			
			}
			
			
			
			 session.setAttribute("deskcrno", fb.getPatCrNo());
			 
			return mapping.findForward("NEW");
		}
		

		public ActionForward NEWDUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
			generateToken(request);
			SampleCollectionFB fb=(SampleCollectionFB)form;
			HttpSession session=WebUTIL.getSession(request);
			
	         String isDuplicateBarecodeprint=fb.getDuplicateBarcodeGeneration()==null?"":fb.getDuplicateBarcodeGeneration();
			
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
				NewSampleCollectionUTL.getSampleCollectionArea(fb,request);
			}
			else
			{
				
			//	NewSampleCollectionUTL.getPatList(fb,request);
			}
			
			
			fb.setDuplicateBarcodeGeneration(isDuplicateBarecodeprint);
			return mapping.findForward("NEW");
		}
		
		
public ActionForward AjaxGetPatDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
			{
				SampleCollectionFB fb = (SampleCollectionFB) form;

				try {
					JsonObject jsonResponse = new JsonObject();
					
				
					jsonResponse=NewSampleCollectionUTL.AjaxGetPatDetails(fb, request);
					
					response.setContentType("application/Json");
					response.getWriter().print(jsonResponse.toString());
					System.out.println("response = : = :"+jsonResponse.toString());
					
				}
				catch (JsonIOException e) {
					e.printStackTrace();
					StringWriter sw=new StringWriter();
					e.printStackTrace(new PrintWriter(sw));
					response.setContentType("text/html");
					try { response.getWriter().print("error"+sw.toString()); }
					catch (IOException e1) { e1.printStackTrace(); }
				}
				catch (IOException e) { 
					e.printStackTrace(); 
				}
				catch (Exception e) {
					e.printStackTrace();
					StringWriter sw=new StringWriter();
					e.printStackTrace(new PrintWriter(sw));
					response.setContentType("text/html");
					try { response.getWriter().print("error"+sw.toString()); }
					catch (IOException e1) { e1.printStackTrace(); }
				}
				return null;
}

public ActionForward AjaxBilledUnbilledDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	SampleCollectionFB fb = (SampleCollectionFB) form;
	
	try {
		
		String jsonResponse = null;
		jsonResponse=NewSampleCollectionUTL.AjaxBilledUnbilledDetails(fb, request);
		JSONObject json = new JSONObject(jsonResponse);
		response.setContentType("application/Json");
		response.getWriter().print(json.toString());
		System.out.println("response = : = :"+json.toString());
		
	}
	catch (JsonIOException e) {
		e.printStackTrace();
		StringWriter sw=new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		response.setContentType("text/html");
		try { response.getWriter().print("error"+sw.toString()); }
		catch (IOException e1) { e1.printStackTrace(); }
	}
	catch (IOException e) { 
		e.printStackTrace(); 
	}
	catch (Exception e) {
		e.printStackTrace();
		StringWriter sw=new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		response.setContentType("text/html");
		try { response.getWriter().print("error"+sw.toString()); }
		catch (IOException e1) { e1.printStackTrace(); }
	}
	return null;
}			
			
public ActionForward AjaxGetDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	SampleCollectionFB fb = (SampleCollectionFB) form;
	
	try {
		String jsonResponse = null;
		
		jsonResponse=NewSampleCollectionUTL.AjaxGetDetails(fb, request);
		
		response.setContentType("application/Json");
		
		//String FinalValue =jsonResponse.toString();
		
		if(jsonResponse!=null)
		{
			response.getWriter().print(jsonResponse.toString());
			
		}
		else{
			response.getWriter().print("No data Found");
		}
		
			/*
			 * 
			 * response.getWriter().print(jsonResponse.toString());
			 * 
			 * System.out.println("response = : = :"+jsonResponse.toString());
			 * if(jsonResponse.toString().equalsIgnoreCase(null)) {
			 * response.getWriter().print("No data Found"); }
			 */
		
	}
	catch (JsonIOException e) {
		e.printStackTrace();
		StringWriter sw=new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		response.setContentType("text/html");
		try { response.getWriter().print("error"+sw.toString()); }
		catch (IOException e1) { e1.printStackTrace(); }
	}
	catch (IOException e) { 
		e.printStackTrace(); 
	}
	catch (Exception e) {
		e.printStackTrace();
		
		StringWriter sw=new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		response.setContentType("text/html");
		try { response.getWriter().print("error"+sw.toString()); }
		catch (IOException e1) { e1.printStackTrace(); }
	}
	return null;
}		


public ActionForward AjaxSaveDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)  throws Exception
{
	 SampleCollectionFB _fb=(SampleCollectionFB)form;
	 HttpSession session=WebUTIL.getSession(request);
		InvestigationEssentialsamplecollDelegate   daoDelegate=new InvestigationEssentialsamplecollDelegate();

		UserVO userVO=ControllerUTIL.getUserVO(request);

	// session.removeAttribute(InvestigationConfig.UPLOADED_FILE_AS_ARRAY);

	    StringBuffer jb = new StringBuffer();
	  
	    String line = null;
	 

	 	Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp= new LinkedHashMap<String, Map<String,Map<String,List<Inv_SampleCollectionVO>>>>();

	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);
	    
	    
	    String mobileNo=_fb.getPatMobile()==null?"":_fb.getPatMobile();
		String emailId=_fb.getPatEmail()==null?"":_fb.getPatEmail();
		String patAddress=_fb.getPatAddress()==null?"":_fb.getPatAddress();
		

	    
	    
	    JSONObject json = new JSONObject(jb.toString());
	    JSONArray jsonArray = new JSONArray(json.getString("selectedList"));
	   // System.out.println(jsonArray.length());
	    for(int i=0;i<jsonArray.length();i++)
	    {
	    	//String patientStatus = jsonArray.getJSONObject(0).getString("crno");	
	    	JSONObject childJSONObject = jsonArray.getJSONObject(i);
	        String testName = childJSONObject.getString("testName");
	    	String labName = childJSONObject.getString("labName");
	    	String requisitionDate = childJSONObject.getString("requisitionDate");
	    	String sampleName = childJSONObject.getString("sampleName");
	    	String sampleNo = childJSONObject.getString("sampleNo");
	    	String sampleQnty = childJSONObject.getString("sampleQnty");
	    	String UOM = childJSONObject.getString("UOM");
	    	String container = childJSONObject.getString("container");
	        String priorityAll = childJSONObject.getString("priorityAll");
	    	String machineCode = childJSONObject.getString("machineCode");
	    	String priorityAllCode = childJSONObject.getString("priorityAllCode");
	    	String patInstruct = childJSONObject.getString("patInstruct");
	    	String requisitionNo = childJSONObject.getString("requisitionNo");
	    	String labCode = childJSONObject.getString("labCode");
	    	String testCode = childJSONObject.getString("testCode");
	    	String isConfidential = childJSONObject.getString("isConfidential");
	    	String requisitionDNo = childJSONObject.getString("requisitionDNo");
	    	String reqDtlStatus = childJSONObject.getString("reqDtlStatus");
	    	String sampleCode = childJSONObject.getString("sampleCode");
	    	String defaultContainerCode = childJSONObject.getString("defaultContainerCode");
	    	String defaultUOMCode = childJSONObject.getString("defaultUOMCode");
	    	String samplenoConfig = childJSONObject.getString("sampleNoConfiguration");
	    	String collInstruct = childJSONObject.getString("collInstruct");
	    	String isrequisitionformneeded = childJSONObject.getString("isrequisitionformneeded");
	    	String miscDate = childJSONObject.getString("miscDate");
	    	String billDetail = childJSONObject.getString("billDetail");
	    	String billNo = childJSONObject.getString("billNo");
	    	String patType = childJSONObject.getString("patType");
	    	String sampleNoFormat = childJSONObject.getString("sampleNoFormat");
	    	String initDate = childJSONObject.getString("initDate");
	    	String noOfSeqDigit = childJSONObject.getString("noOfSeqDigit");
	    	String fromSeries = childJSONObject.getString("fromSeries");
	    	String toSeries = childJSONObject.getString("toSeries");
	    	String initType = childJSONObject.getString("initType");
	    	String runningSampleNo = childJSONObject.getString("runningSampleNo");
	    	String configLab = childJSONObject.getString("configLab");
	    	String configType = childJSONObject.getString("configType");
	    	String configSeq = childJSONObject.getString("configSeq");
	    	String configTest = childJSONObject.getString("configTest");
	     	String configArea = childJSONObject.getString("configArea");
	     	String crno = childJSONObject.getString("crno");
	     	String collAreaCode = childJSONObject.getString("sampleAreaCode");
	     	String wardcode = childJSONObject.getString("wardCode");
	     	
	     	
	     	if(wardcode !=null && !wardcode.equals(""))
	     	{
	     		
			String collarea=daoDelegate.getcollectionareafromward(wardcode,userVO.getHospitalCode());
	     	
			if(collarea!=null && !collarea.equals(""))
			{
				collAreaCode=collarea;
			}
			
	     	}
	     	
	     	
	     	String patName=childJSONObject.getString("patName");
	    	String patCategoryCode=childJSONObject.getString("patCategoryCode");
		     _fb.setPatCategoryCode(patCategoryCode);
	     	_fb.setPatName(patName);
	    	Map<String,Map<String,List<Inv_SampleCollectionVO>>> mpReqNo= mp.get(crno);
			// First Time Insertion 
			if(mpReqNo==null)
			{
				mpReqNo= new LinkedHashMap<String,Map<String,List<Inv_SampleCollectionVO>>>();
				
				Map<String,List<Inv_SampleCollectionVO>> mpSample=new LinkedHashMap<String,List<Inv_SampleCollectionVO>>();
				
				List<Inv_SampleCollectionVO> lstSample=new ArrayList<Inv_SampleCollectionVO>();
				Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
				
				
				if(samplenoConfig.equals("1")||samplenoConfig.equalsIgnoreCase("2"))
					
				{
					voSample.setTempSampleNo(samplenoConfig);
				}
				else
				{
					voSample.setTempSampleNo(sampleNo);
				}
				
				
				//Setting VO Values from labStringArray
				voSample.setPatCRNo(crno);
				voSample.setSampleCode(sampleCode);
				voSample.setRequisitionDNo(requisitionDNo);
				voSample.setRequisitionNo(requisitionNo);
				voSample.setLabCode(labCode);
				voSample.setPatMobile(mobileNo);
				voSample.setPatEmail(emailId);
				voSample.setPatAddress(patAddress);
				voSample.setPatName(patName);
				voSample.setPatCategoryCode(_fb.getPatCategoryCode());
				
				voSample.setSampleAreaCode(collAreaCode);
				voSample.setWardCode(wardcode);
				
				//voSample.setPrintStatus(printStatus);
				voSample.setSampleQnty(sampleQnty);
				voSample.setDefaultContainerCode(defaultContainerCode);
				voSample.setDefaultmachineCode(machineCode);
				voSample.setDefaultUOMCode(defaultUOMCode);
				voSample.setTypeOfComponent("1"); // Need to Discuss
	
				voSample.setBillNo(billNo);
				voSample.setTestCode(testCode);
				voSample.setSampleName(sampleName);
				
				voSample.setCheckAutoLabConfig(samplenoConfig);
				voSample.setTestName(testName);
				voSample.setPatType(patType);
				voSample.setSampleNoFormat(sampleNoFormat);
				voSample.setInitDate(initDate);
				voSample.setNoOfSeqDigit(noOfSeqDigit);
				voSample.setFromSeries(fromSeries);
                  voSample.setToSeries(toSeries);
                  voSample.setInitType(initType);
                  voSample.setRunningSampleNo(runningSampleNo);
                  voSample.setRequisitionDate(requisitionDate);
                  voSample.setLabName(labName);
                  
                  voSample.setConfigLab(configLab);
                  voSample.setConfigArea(configArea);
                  voSample.setConfigSeq(configSeq);
                  voSample.setConfigTest(configTest);
                  voSample.setConfigType(configType);
                  
              	//Adding List of SampleVO<=>RequisitionDNo's
				lstSample.add(voSample);
				
				//Putting list in Map of SampleCodes
				mpSample.put(sampleCode+"#"+labCode, lstSample);
				
				//Putting Map of Samples in Map of Requisitions
				mpReqNo.put(requisitionNo, mpSample);
				
			}
			else
			{
						//Getting Map of Sample Codes
					Map<String,List<Inv_SampleCollectionVO>> mpSample=mpReqNo.get(requisitionNo);
					
					// First Time Insertion 
					if(mpSample==null)
					{
						 	mpSample=new LinkedHashMap<String,List<Inv_SampleCollectionVO>>();
							
							List<Inv_SampleCollectionVO> lstSample=new ArrayList<Inv_SampleCollectionVO>();
							Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
							
							//Setting VO Values from labStringArray
							voSample.setPatCRNo(crno);
							voSample.setSampleCode(sampleCode);
							voSample.setRequisitionDNo(requisitionDNo);
							voSample.setRequisitionNo(requisitionNo);
							voSample.setLabCode(labCode);
							voSample.setPatMobile(mobileNo);
							voSample.setPatEmail(emailId);
							voSample.setPatAddress(patAddress);
							voSample.setPatName(patName);
							voSample.setPatCategoryCode(_fb.getPatCategoryCode());
							
							voSample.setSampleAreaCode(collAreaCode);
							voSample.setWardCode(wardcode);

							//voSample.setPrintStatus(printStatus);
							voSample.setSampleQnty(sampleQnty);
							voSample.setDefaultContainerCode(defaultContainerCode);
							voSample.setDefaultmachineCode(machineCode);
							voSample.setDefaultUOMCode(defaultUOMCode);
							voSample.setTypeOfComponent("1"); // Need to Discuss
							
							voSample.setTestName(testName);			 
							voSample.setBillNo(billNo);
							voSample.setTestCode(testCode);
							voSample.setSampleName(sampleName);
							 voSample.setRequisitionDate(_fb.getRequisitionDate());
                              voSample.setLabName(_fb.getLabName());
                           // Still Some values need to be inserted
                              if(samplenoConfig.equals("1")||samplenoConfig.equalsIgnoreCase("2"))
              					
              				{
              					voSample.setTempSampleNo(samplenoConfig);
              				}
              				else
              				{
              					voSample.setTempSampleNo(sampleNo);
              				}
							
							voSample.setCheckAutoLabConfig(samplenoConfig);
							voSample.setPatType(patType);
							
							voSample.setSampleNoFormat(sampleNoFormat);
							voSample.setInitDate(initDate);
							voSample.setNoOfSeqDigit(noOfSeqDigit);
							voSample.setFromSeries(fromSeries);
                              voSample.setToSeries(toSeries);
                              voSample.setInitType(initType);
                              voSample.setRunningSampleNo(runningSampleNo);
                              voSample.setRequisitionDate(requisitionDate);
                              voSample.setLabName(labName);
                              voSample.setConfigLab(configLab);
                              voSample.setConfigArea(configArea);
                              voSample.setConfigSeq(configSeq);
                              voSample.setConfigTest(configTest);
                              voSample.setConfigType(configType);
                              
							//Adding List of SampleVO<=>RequisitionDNo's
							lstSample.add(voSample);
							
							//Putting list in Map of SampleCodes
							mpSample.put(sampleCode+"#"+labCode, lstSample);
							
						
					}
					else
					{
								List<Inv_SampleCollectionVO> lstSample=mpSample.get(sampleCode+"#"+labCode);
								if(lstSample==null||lstSample.size()==0) // First Time Insertion
								{
									lstSample=new ArrayList<Inv_SampleCollectionVO>();
									Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
									
									//Setting VO Values from labStringArray
									voSample.setPatCRNo(crno);
									voSample.setSampleCode(sampleCode);
									voSample.setRequisitionDNo(requisitionDNo);
									voSample.setRequisitionNo(requisitionNo);
									voSample.setLabCode(labCode);
									voSample.setPatMobile(mobileNo);
									voSample.setPatEmail(emailId);
									voSample.setPatAddress(patAddress);
									voSample.setPatName(patName);
									voSample.setPatCategoryCode(_fb.getPatCategoryCode());
									
									voSample.setTestName(testName);
									voSample.setSampleAreaCode(collAreaCode);
									voSample.setWardCode(wardcode);

									//voSample.setPrintStatus(printStatus);
									voSample.setSampleQnty(sampleQnty);
									voSample.setDefaultContainerCode(defaultUOMCode);
									voSample.setDefaultmachineCode(machineCode);
									voSample.setDefaultUOMCode(defaultUOMCode);
									voSample.setTypeOfComponent("1"); // Need to Discuss
									voSample.setBillNo(billNo);
									voSample.setTestCode(testCode);
									voSample.setSampleName(sampleName);
									// Still Some values need to be inserted
									if(samplenoConfig.equals("1")||samplenoConfig.equalsIgnoreCase("2"))
										
									{
										voSample.setTempSampleNo(samplenoConfig);
									}
									else
									{
										voSample.setTempSampleNo(sampleNo);
									}
									
									voSample.setCheckAutoLabConfig(samplenoConfig);
									voSample.setPatType(patType);
									
									voSample.setSampleNoFormat(sampleNoFormat);
									voSample.setInitDate(initDate);
									voSample.setNoOfSeqDigit(noOfSeqDigit);
									voSample.setFromSeries(fromSeries);
                                      voSample.setToSeries(toSeries);
                                      voSample.setInitType(initType);
                                      voSample.setRunningSampleNo(runningSampleNo);
                                      voSample.setRequisitionDate(requisitionDate);
                                      voSample.setLabName(labName);
                                      
                                      voSample.setConfigLab(configLab);
                                      voSample.setConfigArea(configArea);
                                      voSample.setConfigSeq(configSeq);
                                      voSample.setConfigTest(configTest);
                                      voSample.setConfigType(configType);
                                    //Adding List of SampleVO<=>RequisitionDNo's
									lstSample.add(voSample);
									
								}
								else
								{
									Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
									
									//Setting VO Values from labStringArray
									voSample.setPatCRNo(crno);
									voSample.setSampleCode(sampleCode);
									voSample.setRequisitionDNo(requisitionDNo);
									voSample.setRequisitionNo(requisitionNo);
									voSample.setLabCode(labCode);
									voSample.setPatMobile(mobileNo);
									voSample.setPatEmail(emailId);
									voSample.setPatAddress(patAddress);
									voSample.setPatCategoryCode(_fb.getPatCategoryCode());
									
									voSample.setTestName(testName);
									voSample.setSampleAreaCode(collAreaCode);
									voSample.setWardCode(wardcode);

									//voSample.setPrintStatus(printStatus);
									voSample.setSampleQnty(sampleQnty);
									voSample.setDefaultContainerCode(defaultContainerCode);
									voSample.setDefaultmachineCode(machineCode);
									voSample.setDefaultUOMCode(defaultUOMCode);
									voSample.setTypeOfComponent("1"); // Need to Discuss
									voSample.setBillNo(billNo);
									voSample.setTestCode(testCode);
									voSample.setSampleName(sampleName);
									voSample.setPatName(patName);
									// Still Some values need to be inserted
									
									if(samplenoConfig.equals("1")||samplenoConfig.equalsIgnoreCase("2"))
										
									{
										voSample.setTempSampleNo(samplenoConfig);
									}
									else
									{
										voSample.setTempSampleNo(sampleNo);
									}
									
									voSample.setCheckAutoLabConfig(samplenoConfig);
									voSample.setPatType(patType);
									voSample.setSampleNoFormat(sampleNoFormat);
									voSample.setInitDate(initDate);
									voSample.setNoOfSeqDigit(noOfSeqDigit);
									voSample.setFromSeries(fromSeries);
                                      voSample.setToSeries(toSeries);
                                      voSample.setInitType(initType);
                                      voSample.setRunningSampleNo(runningSampleNo);
                                      voSample.setRequisitionDate(requisitionDate);
                                      voSample.setLabName(labName);
                                      
                                      voSample.setConfigLab(configLab);
                                      voSample.setConfigArea(configArea);
                                      voSample.setConfigSeq(configSeq);
                                      voSample.setConfigTest(configTest);
                                      voSample.setConfigType(configType);
                                      voSample.setPatName(patName);
                                      
                                      
									//Adding List of SampleVO<=>RequisitionDNo's
									lstSample.add(voSample);
								}
								
								//Putting list in Map of SampleCodes
								mpSample.put(sampleCode+"#"+labCode, lstSample);
							//}
					}
					
					//Putting Map of Samples in Map of Requisitions
					mpReqNo.put(requisitionNo, mpSample);
					
									
				//	}// end while
					
				} // end main else
				
				// Putting Map of Requisitions in Map of CrNo's  => currently only one CR No is allowed
				mp.put(crno, mpReqNo);
			
			}// end for loop
			
     
     
	 NewSampleCollectionUTL.AJAXsaveSampleCollectionDetails(mp,_fb,request,response);
	 
	 if(_fb.getFlagforipddesk()!=null && _fb.getFlagforipddesk().equals("15"))
	 _fb.setFlagforipddesk("16");
	 else
		 _fb.setShowStatus("0");
	 
	 
	 return null;
  
} 

public ActionForward AJAX_CHECK_AUTO_SAMPLENO_GEN(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
		HttpServletResponse response) throws HisException, Exception, SQLException
				{
					SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
				
					try {
					String jsonResponse = null;
					jsonResponse = NewSampleCollectionUTL.ajaxcheckAutoGenFormate(fb, objRequest_p);
					response.setContentType("application/Json");
					response.getWriter().print(jsonResponse.toString());
					System.out.println("response = : = :"+jsonResponse.toString());
					
						}
				catch (JsonIOException e) {
					e.printStackTrace();
					StringWriter sw=new StringWriter();
					e.printStackTrace(new PrintWriter(sw));
					response.setContentType("text/html");
					try { response.getWriter().print("error"+sw.toString()); }
					catch (IOException e1) { e1.printStackTrace(); }
				}
				catch (IOException e) { 
					e.printStackTrace(); 
				}
				catch (Exception e) {
					e.printStackTrace();
					StringWriter sw=new StringWriter();
					e.printStackTrace(new PrintWriter(sw));
					response.setContentType("text/html");
					try { response.getWriter().print("error"+sw.toString()); }
					catch (IOException e1) { e1.printStackTrace(); }
				}
				return null;
				}	
	
public ActionForward AjaxNEWDUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws HisException, Exception, SQLException
{
	SampleCollectionFB fb = (SampleCollectionFB) form;
      String todate=fb.getToDate();
      String fromdate=fb.getFromDate();
      String crno=fb.getPatCRNo();
      try {
			String jsonResponse = null;
			jsonResponse = NewSampleCollectionUTL.AJAXgetPatListBarcode(fb,request);
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
			System.out.println("response = : = :"+jsonResponse.toString());
			
				}
		catch (JsonIOException e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print("error"+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print("error"+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
      
	fb.setIsSampleAreaSelected("0");
			fb.setShowStatus("3");

	fb.setSampleAreaCode("-1");
    fb.setDuplicateBarcodeGeneration("0");

    request.getSession().setAttribute("todate_dup", todate);
    request.getSession().setAttribute("fromdate_dup", fromdate);
    request.getSession().setAttribute("crno_dup", crno);

   fb.setStatuschange("1");
	
	return null;
}

public ActionForward AJAXDUPLICATEBARCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws BarcodeException, OutputException, IOException, JSONException{
	SampleCollectionFB fb = (SampleCollectionFB) form;
	 StringBuffer jb = new StringBuffer();
	 String line = null;
	 
	 List<Inv_SampleCollectionVO> lstSample=new ArrayList<Inv_SampleCollectionVO>();

	 BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);
	    JSONObject json = new JSONObject(jb.toString());
	    JSONArray jsonArray = new JSONArray(json.getString("selectedListForDuplicateBarcode"));
	   
	    for(int i=0;i<jsonArray.length();i++)
	    {	
	    	Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
	    	JSONObject childJSONObject = jsonArray.getJSONObject(i);
	        String crno = childJSONObject.getString("crno");
	    	String patName = childJSONObject.getString("patName");
	    	String labName = childJSONObject.getString("labName");
	    	String SampleNo = childJSONObject.getString("SampleNo");
	    	String requisitionDate = childJSONObject.getString("requisitionDate");
	    	String patStatus = childJSONObject.getString("patStatus");
	    	String sugarTestCode = childJSONObject.getString("sugarTestCode");
	        String sampleCollectionDate = childJSONObject.getString("sampleCollectionDate");
	    	String requisitionNo = childJSONObject.getString("requisitionNo");
	    	String sampleCode = childJSONObject.getString("sampleCode");
	    	String sampleName = childJSONObject.getString("sampleName");
	    	
			voSample.setPatCRNo(crno);
	    	voSample.setSampleName(sampleName);
	    	voSample.setpatName(patName);
	    	voSample.setLabName(labName);
	    	voSample.setRequisitionNo(requisitionNo);
	    	voSample.setSugarTestCode(sugarTestCode);
	    	voSample.setSampleCollectionDate(sampleCollectionDate);
	    	voSample.setRequisitionDate(requisitionDate);
	     	voSample.setSampleNo(SampleNo);;
	     	  lstSample.add(voSample);
	    }
	  
	    System.out.println(lstSample);
	  
	NewSampleCollectionUTL.AJAXduplicateBarCodeDetails(fb,lstSample,request,response);
	fb.setShowStatus("0");
	fb.setIsSampleAreaSelected("1");
    fb.setSampleAreaCode("-1");
	fb.setDuplicateBarcodeGeneration("0");
	return null;
	//return mapping.findForward("GETPATLISTBAROCDE");
}



public ActionForward NEWWW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	generateToken(request);
	SampleCollectionFB fb=(SampleCollectionFB)form;
	HttpSession session=WebUTIL.getSession(request);
	//fb.setPatCrNo("9610120000");
//	fb.setCrNo("961012000000732");
	//fb.setPatStatusCode("2");
//	fb.getWardCode()
//	fb.setSampleAreaCode("101");
	

	// request.removeAttribute("patCrNo") ;
	 //request.getAttribute("wardCode") ;
	 //request.getAttribute("patStatusCode") ;






//String crno=(String)request.getAttribute("patCrNo");
//String wardcode=(String)request.getAttribute("wardCode");
//String patStatusCode=(String)request.getAttribute("patStatusCode");

//	AjaxGetPatDetails(mapping,form,request,response);
	//AjaxGetDetails(mapping,form,request,response);
//	request.setAttribute("sampleAreaCode", fb.getSampleAreaCode());
	request.setAttribute("patCrNo", fb.getPatCrNo());
	request.setAttribute("wardCode", fb.getWardCode());
	request.setAttribute("patStatusCode", "2");
	
	return this.NEW(mapping,form,request,response);

}

}