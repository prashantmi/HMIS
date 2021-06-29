package new_investigation.transactions.controller.utl;



import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
import investigationDesk.vo.viewInvestigationVO;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvResultReportPrintingDATA;
import new_investigation.transactions.controller.data.InvResultReportPrintingNewDATA;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingNewFB;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.template.ResultEntryVO;

public class InvResultReportPrintingNewUTIL extends ControllerUTIL
{
	public static String getdatacrnowise(InvResultReportPrintingNewFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		InvResultReportPrintingVO invresultReportPrintingvo=new InvResultReportPrintingVO();
		 String json="";
		 
		Map mp=new HashMap(); 
	try{	
		//Map mp=new HashMap(); 
		Map mpp=new HashMap();
		/*ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		mp=InvResultReportPrintingDATA.LabComboForResultReportPrinting(invresultReportPrintingvo, userVO);
		
		 
	  String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	  invresultReportPrintingvo.setFromDate(tDate);
	  invresultReportPrintingvo.setToDate(tDate);
	  invresultReportPrintingvo.setLabCode("%");*/
	  
		 invresultReportPrintingvo.setPatCRNo(fb.getPatCrNo());
		 json=InvResultReportPrintingNewDATA.getdatacrnowise(invresultReportPrintingvo, userVO,request);
		WebUTIL.setMapInSession(mpp, request);
		WebUTIL.setMapInSession(mp, request);

		
		/*mpp=SampleAcceptanceDATA.getSampleAcceptanceDetailOnLoad(sampleAcceptancevo, userVO);
		mp=SampleAcceptanceDATA.sampleAcceptanceLabCombo(sampleAcceptancevo, userVO);
		WebUTIL.setMapInSession(mp, request);
		WebUTIL.setMapInSession(mpp, request);
		*/
		
		
		objStatus.add(Status.TRANSINPROCESS);
		//HelperMethods.populate(fb, InvResultReportPrintingVO);
	}catch(HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
	}
	catch(HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR,"","Error");
	}
	return json;			
	
	
	
	}
	
	
	public static String getEssential(InvResultReportPrintingNewFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		InvResultReportPrintingVO invresultReportPrintingvo=new InvResultReportPrintingVO();
		 String json="";
		 
		Map mp=new HashMap(); 
	try{	
		//Map mp=new HashMap(); 
		Map mpp=new HashMap();
		/*ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		mp=InvResultReportPrintingDATA.LabComboForResultReportPrinting(invresultReportPrintingvo, userVO);
		
		 
	  String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	  invresultReportPrintingvo.setFromDate(tDate);
	  invresultReportPrintingvo.setToDate(tDate);
	  invresultReportPrintingvo.setLabCode("%");*/
	  
		 invresultReportPrintingvo.setPatCRNo(fb.getPatCrNo());
		 json=InvResultReportPrintingNewDATA.setResultReportPrintingEssentialsOnLoad(invresultReportPrintingvo, userVO,request);
		WebUTIL.setMapInSession(mpp, request);
		WebUTIL.setMapInSession(mp, request);

		
		/*mpp=SampleAcceptanceDATA.getSampleAcceptanceDetailOnLoad(sampleAcceptancevo, userVO);
		mp=SampleAcceptanceDATA.sampleAcceptanceLabCombo(sampleAcceptancevo, userVO);
		WebUTIL.setMapInSession(mp, request);
		WebUTIL.setMapInSession(mpp, request);
		*/
		
		
		objStatus.add(Status.TRANSINPROCESS);
		//HelperMethods.populate(fb, InvResultReportPrintingVO);
	}catch(HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
	}
	catch(HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR,"","Error");
	}
	return json;			
	
	
	
	}
	
	
	public static boolean setResultReportPrintingEssentials(InvResultReportPrintingFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvResultReportPrintingVO invresultReportPrintingvo=new InvResultReportPrintingVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			invresultReportPrintingvo.setFromDate(fb.getFromDate()); 
			invresultReportPrintingvo.setToDate(fb.getToDate());
			invresultReportPrintingvo.setPatCRNo(fb.getPatCRNo());
			//updated by krishnan nema on 28/09/2018
			//invresultReportPrintingvo.setLabCode(fb.getLabCode());
			invresultReportPrintingvo.setLabCode(fb.getLabCode().split("#")[0]);
			
			invresultReportPrintingvo.setPdfGenerationType(fb.getPdfGenerationType());
			invresultReportPrintingvo.setTestWiseCode(fb.getTestWiseCode());
			invresultReportPrintingvo.setFromLabNo(fb.getFromLabNo());
			invresultReportPrintingvo.setToLabNo(fb.getToLabNo());
			invresultReportPrintingvo.setFromSampleNo(fb.getFromSampleNo());
			invresultReportPrintingvo.setToSampleNo(fb.getToSampleNo());
			invresultReportPrintingvo.setGenerationType(fb.getGenerationType());
			invresultReportPrintingvo.setTestGroupCode(fb.getTestGroupCodeWise());
			invresultReportPrintingvo.setOnLoadValue(fb.getOnLoadValue());
			invresultReportPrintingvo.setTempPatName(fb.getTempPatName());
			invresultReportPrintingvo.setReportType(fb.getReportType());
			invresultReportPrintingvo.setDateType(fb.getDateType()!=null && !fb.getDateType().equals("")?fb.getDateType():"r");
			
			mp=InvResultReportPrintingDATA.setResultReportPrintingEssentials(invresultReportPrintingvo, userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			
			//HelperMethods.populate(fb, InvResultReportPrintingVO);
			  
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	public static void showResultEntryPatDetails(InvResultReportPrintingNewFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		try{
			
			String reporturl=(String)request.getParameter("reporturl");
			
			List<InvResultReportPrintingVO> InvResultReportPrintingVO=null;
			InvResultReportPrintingVO invresultentryv = new InvResultReportPrintingVO();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			//InvResultReportPrintingVO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			//String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=reporturl.split("@@");
			
			
			
			for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				/*for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					filename=crNo;
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNo=arrSelectedRequisitions[i].split(",")[2];
					 String grpCode=arrSelectedRequisitions[i].split(",")[3];
					
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
              
						 
						 objPatientCollectionVO.setReportType(fb.getReportType());
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
					}
					 }
					 else
					 {
						 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
							{
		              		 
								lstInvResultReportPrintingVO.add(objPatientCollectionVO);
								 
							} 
						 
					 }
					 }*/
			
				}
			
			/*int i=0;
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					 if(i<arrSelectedRequisitions.length)
					 {
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNO=arrSelectedRequisitions[i].split(",")[2];
					 
					if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getPatReqNo().equals(reqNO))
					{
              
						  
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
						++i;
					}
					 }
				}*/
				//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO, lstInvResultReportPrintingVO);
				//for(String str:arrSelectedRequisitions)
		//		mp=InvResultReportPrintingDATA.PdfDetails(lstInvResultReportPrintingVO,  userVO);

				
				
				List<InvResultReportPrintingVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
			//	List<InvResultReportPrintingVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);

				List<byte[]> pdfs = new ArrayList<byte[]>();
				 
				
				Map<String,String> objMapSamAcc= new HashMap<String,String>();
				
				
				 
				 
				for(int i=0;i<arrSelectedRequisitions.length;i++)
				{
					//InvResultReportPrintingVO objResultEntryVO = lstSampleAccepted.get(j);
					//List<InvResultReportPrintingVO> lstTempResultEntryVO = null;
					String strPdfName = arrSelectedRequisitions[i];

				//	lstTempResultEntryVO=objMapSamAcc.get(strPdfName);

					
					 

				

					objMapSamAcc.put(strPdfName,"1");

				}
				Set setPdfName=objMapSamAcc.keySet();

				Iterator itrPdfName=setPdfName.iterator();

				//iterate over Crno's
				while(itrPdfName.hasNext())
				{
					
				
				
				
				  //for(InvResultReportPrintingVO invReportvo:lstSampleAccepted)
				 // {
					
					 // String testingFileName=invReportvo.getPdfFileName();
					 
					  String strPdfName=(String)itrPdfName.next();
					  
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
					byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(strPdfName);
				 
					 
					 pdfs.add(Pdf);	
					  
					  
				  }
					
				//}
					 
				filename=filename+".pdf";
					 response.setContentType("application/pdf");
					 response.setHeader("content-disposition", "inline; filename="+filename);	
					// response.setHeader("Cache-Control", "no-cache");
						OutputStream output = response.getOutputStream();
						System.out.println("concatallPDFsconcatallPDFsconcatallPDFsconcatallPDFs chandannn");
						MergeAllPdfNewInv.concatallPDFs(pdfs, output, null,request);		
						//output.write(Pdf);
					 // System.out.println("------------------->"+output); 
						//mp=InvResultReportPrintingDATA.saveResultReportPrintingDetails(lstInvResultReportPrintingVO,  userVO);
					   
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			//WebUTIL.setStatus(request, status);
		}
		
		//return sbAjaxRes;
	}
	  
	
	
	public static void printReport(InvResultReportPrintingFB fb, HttpServletRequest objRequest_p,HttpServletResponse response)
	{
		
		 
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		Status status = new Status();
		List<byte[]> pdfs = new ArrayList<byte[]>();
		System.out.println("fb.getFileName() : "+fb.getFileName());

		try
		{
			
			byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(fb.getFileName());
			
			pdfs.clear();
			pdfs.add(Pdf);	
		
		 response.setContentType("application/pdf");
			 response.setHeader("Cache-Control", "no-cache");
				OutputStream output = response.getOutputStream();
				MergeAllPdfNewInv.concatallPDFs(pdfs, output, null,objRequest_p);		
				output.flush();
				output.close();
				//return;
			
		}
		catch (Exception e)
		{
			strMsgText = "InvResultReportPrintingUTIL.printReport() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
			//WebUTIL.setStatus(objRequest_p, status);
		}
	
	}	
	 
	

	public static void showResultEntryPatDetails_viewinv(InvResultReportPrintingFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		List<byte[]> pdfs = new ArrayList<byte[]>();

		try{
			List<viewInvestigationVO> InvResultReportPrintingVO=null;
			viewInvestigationVO invresultentryv = new viewInvestigationVO();
			List<viewInvestigationVO> lstInvResultReportPrintingVO=new ArrayList<viewInvestigationVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<viewInvestigationVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			
			
			/*for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(viewInvestigationVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					filename=crNo;
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNo=arrSelectedRequisitions[i].split(",")[2];
					 String grpCode=arrSelectedRequisitions[i].split(",")[3];
					
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
              
						 
						
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
					}
					 }
					 else
					 {
						 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
							{
		              		 
								lstInvResultReportPrintingVO.add(objPatientCollectionVO);
								 
							} 
						 
					 }
					 }
			
				}*/
			
			/*int i=0;
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					 if(i<arrSelectedRequisitions.length)
					 {
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNO=arrSelectedRequisitions[i].split(",")[2];
					 
					if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getPatReqNo().equals(reqNO))
					{
              
						  
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
						++i;
					}
					 }
				}*/
				//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO, lstInvResultReportPrintingVO);
				//for(String str:arrSelectedRequisitions)
				//mp=InvResultReportPrintingDATA.PdfDetails(lstInvResultReportPrintingVO,  userVO);

				
				
				List<viewInvestigationVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<viewInvestigationVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);


				
				Map<String,String> objMapSamAcc= new HashMap<String,String>();
				
				
				 
				 
				/*for(int j=0; j<lstSampleAccepted.size();j++)
				{
					viewInvestigationVO objResultEntryVO = lstSampleAccepted.get(j);
					List<viewInvestigationVO> lstTempResultEntryVO = null;
					String strPdfName = objResultEntryVO.getPdfFileName();

					lstTempResultEntryVO=objMapSamAcc.get(strPdfName);

					
					 

					if(lstTempResultEntryVO==null)
					{
						lstTempResultEntryVO=new ArrayList<viewInvestigationVO>();
						lstTempResultEntryVO.add(objResultEntryVO);
					}
					else
					{
						lstTempResultEntryVO.add(objResultEntryVO);
					}

					objMapSamAcc.put(strPdfName,lstTempResultEntryVO);

				}*/
				
				for(int i=0;i<arrSelectedRequisitions.length;i++)
				 {
				

					String pdfname=arrSelectedRequisitions[i];
					objMapSamAcc.put(pdfname,"12");
					
				 }
				
				Set setPdfName=objMapSamAcc.keySet();

				Iterator itrPdfName=setPdfName.iterator();

				//iterate over Crno's
				pdfs.clear();
				while(itrPdfName.hasNext())
				{
					
				
				
				
				  //for(InvResultReportPrintingVO invReportvo:lstSampleAccepted)
				 // {
					
					 // String testingFileName=invReportvo.getPdfFileName();
					 
					  String strPdfName=(String)itrPdfName.next();
					  
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
					  byte[] Pdf=null;
					  
					  Pdf =MongoXmlHandler.getInstance().latestFetchFile(strPdfName);
						 
					 pdfs.add(Pdf);	
					  
					  
				  }
					
				//}
					 
				filename=filename+".pdf";
					 response.setContentType("application/pdf");
					 response.setHeader("Cache-Control", "no-cache");
						
					// response.setHeader("Cache-Control", "no-cache");
						OutputStream output = response.getOutputStream();
						System.out.println("concatallPDFsconcatallPDFsconcatallPDFsconcatallPDFs chandannn");
						MergeAllPdfNewInv.concatallPDFs(pdfs, output, null,request);	
						output.flush();
						output.close();
						//output.write(Pdf);
					 // System.out.println("------------------->"+output); 
						//mp=InvResultReportPrintingDATA.saveResultReportPrintingDetails(lstInvResultReportPrintingVO,  userVO);
					   
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			//WebUTIL.setStatus(request, status);
		}
		
		//return sbAjaxRes;
	}
	

	public static void showResultEntryPatDetailsFTP(InvResultReportPrintingFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";		
		try{
			List<InvResultReportPrintingVO> InvResultReportPrintingVO=null;
			InvResultReportPrintingVO invresultentryv = new InvResultReportPrintingVO();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			
			
			for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					filename=crNo;
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNo=arrSelectedRequisitions[i].split(",")[2];
					 String grpCode=arrSelectedRequisitions[i].split(",")[3];
					
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
              
						 
						 objPatientCollectionVO.setReportType(fb.getReportType());
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
					}
					 }
					 else
					 {
						 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
							{
		              		 
								lstInvResultReportPrintingVO.add(objPatientCollectionVO);
								 
							} 
						 
					 }
					 }
			
				}
			
			int i=0;
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					 if(i<arrSelectedRequisitions.length)
					 {
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNO=arrSelectedRequisitions[i].split(",")[2];
					 
					if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getPatReqNo().equals(reqNO))
					{
              
						  
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
						++i;
					}
					 }
				}
				//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO, lstInvResultReportPrintingVO);
				//for(String str:arrSelectedRequisitions)
				mp=InvResultReportPrintingDATA.PdfDetails(lstInvResultReportPrintingVO,  userVO);

				
				
				List<InvResultReportPrintingVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<InvResultReportPrintingVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);

				//List<byte[]> pdfs = new ArrayList<byte[]>();
				List<InputStream> pdfs = new ArrayList<InputStream>();
				
				Map<String,List<InvResultReportPrintingVO>> objMapSamAcc= new HashMap<String,List<InvResultReportPrintingVO>>();
				
				
				 
				 
				for(int j=0; j<lstSampleAccepted.size();j++)
				{
					InvResultReportPrintingVO objResultEntryVO = lstSampleAccepted.get(j);
					List<InvResultReportPrintingVO> lstTempResultEntryVO = null;
					String strPdfName = objResultEntryVO.getPdfFileName();

					lstTempResultEntryVO=objMapSamAcc.get(strPdfName);

					
					 

					if(lstTempResultEntryVO==null)
					{
						lstTempResultEntryVO=new ArrayList<InvResultReportPrintingVO>();
						lstTempResultEntryVO.add(objResultEntryVO);
					}
					else
					{
						lstTempResultEntryVO.add(objResultEntryVO);
					}

					objMapSamAcc.put(strPdfName,lstTempResultEntryVO);

				}
				Set setPdfName=objMapSamAcc.keySet();

				Iterator itrPdfName=setPdfName.iterator();

				//String patientcreatefileftpaddress="ftp://ftp_raol:Raol12345@10.226.1.173/ftpserver/ftpserver";
				String patientcreatefileftpaddress= fb.getFtpserver();
				
				
				//iterate over Crno's
				while(itrPdfName.hasNext())
				
					//String hosCode= fb.getHospitalCode();
					//String hosCode= "33101";
					//System.out.println("hoscode : " + hosCode);
				{	 //Calendar calendar = Calendar.getInstance();
					String strPdfName = (String)itrPdfName.next();
					
					String crNo=strPdfName.substring(0,15);
					System.out.println(strPdfName.substring(5,7));
					String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
				    String insideyear=MergeAllPdfNewInv.getInsideYear(crNo);
				    String count=MergeAllPdfNewInv.getcount(crNo);
				      
					
					  
					//System.out.println("before while");
					
					  String strPdfPath = patientcreatefileftpaddress+"/"+strPdfName.substring(0,5)+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+strPdfName.substring(0,15)+"/"+strPdfName;
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					  
					  System.out.println("strPdfPath : "+strPdfPath);
					
					//ByteArrayOutputStream Pdf;
					  
					  URL urlftp=new URL(strPdfPath);
						URLConnection urlcon=urlftp.openConnection();
						pdfs.add(urlcon.getInputStream());	
				}
			
				//filename=filename+".pdf";
					 response.setContentType("application/pdf");
					 response.setHeader("content-disposition", "inline; filename="+filename);	
					// response.setHeader("Cache-Control", "no-cache");
						OutputStream output = response.getOutputStream();
						MergeAllPdfNewInv.concatallPDFs123(pdfs, output, null,request);		
						//output.write(Pdf);
					 // System.out.println("------------------->"+output); 
						mp=InvResultReportPrintingDATA.saveResultReportPrintingDetails(lstInvResultReportPrintingVO,  userVO);
					   
		}
		
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			//WebUTIL.setStatus(request, status);
		}
		
		//return sbAjaxRes;
	}

	
	
	public static void showResultEntryPatDetailsFTPnew(InvResultReportPrintingNewFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		try{
			List<InvResultReportPrintingVO> InvResultReportPrintingVO=null;
			InvResultReportPrintingVO invresultentryv = new InvResultReportPrintingVO();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			
			
			/*for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					filename=crNo;
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNo=arrSelectedRequisitions[i].split(",")[2];
					 String grpCode=arrSelectedRequisitions[i].split(",")[3];
					
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
              
						 
						 objPatientCollectionVO.setReportType(fb.getReportType());
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
					}
					 }
					 else
					 {
						 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
							{
		              		 
							 objPatientCollectionVO.setReportType(fb.getReportType());
								lstInvResultReportPrintingVO.add(objPatientCollectionVO);
								 
							} 
						 
					 }
					 }
			
				}
			
			 }*/
			
			
			/*int i=0;
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					 if(i<arrSelectedRequisitions.length)
					 {
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNO=arrSelectedRequisitions[i].split(",")[2];
					 
					if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getPatReqNo().equals(reqNO))
					{
              
						  
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
						++i;
					}
					 }
				}*/
				//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO, lstInvResultReportPrintingVO);
				//for(String str:arrSelectedRequisitions)
			/*	mp=InvResultReportPrintingDATA.PdfDetails(lstInvResultReportPrintingVO,  userVO);

				
				
				List<InvResultReportPrintingVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<InvResultReportPrintingVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);

				//List<byte[]> pdfs = new ArrayList<byte[]>(); //changed ftp by chandan
				List<InputStream> pdfs = new ArrayList<InputStream>();

				
				Map<String,List<InvResultReportPrintingVO>> objMapSamAcc= new HashMap<String,List<InvResultReportPrintingVO>>();
				
				
				 
				 
				for(int j=0; j<lstSampleAccepted.size();j++)
				{
					InvResultReportPrintingVO objResultEntryVO = lstSampleAccepted.get(j);
					List<InvResultReportPrintingVO> lstTempResultEntryVO = null;
					String strPdfName = objResultEntryVO.getPdfFileName();

					lstTempResultEntryVO=objMapSamAcc.get(strPdfName);

					
					 

					if(lstTempResultEntryVO==null)
					{
						lstTempResultEntryVO=new ArrayList<InvResultReportPrintingVO>();
						lstTempResultEntryVO.add(objResultEntryVO);
					}
					else
					{
						lstTempResultEntryVO.add(objResultEntryVO);
					}

					objMapSamAcc.put(strPdfName,lstTempResultEntryVO);

				}*/
				Map<String,String> objMapSamAcc= new HashMap<String,String>();
				List<InputStream> pdfs = new ArrayList<InputStream>();
				
				for(int i1=0;i1<arrSelectedRequisitions.length;i1++)
				 {
				

					String pdfname=arrSelectedRequisitions[i1];
					objMapSamAcc.put(pdfname,"12");
					
				 }
			
				Set setPdfName=objMapSamAcc.keySet();

				Iterator itrPdfName=setPdfName.iterator();

				String patientcreatefileftpaddress= fb.getFtpserver();

				//iterate over Crno's
				while(itrPdfName.hasNext())
				{
					
				
				
				
				  //for(InvResultReportPrintingVO invReportvo:lstSampleAccepted)
				 // {
					
					 // String testingFileName=invReportvo.getPdfFileName();
					    //  Thread.sleep(50000);
					  String strPdfName=(String)itrPdfName.next();
					  
					  String crNo=strPdfName.substring(0,15);
						System.out.println(strPdfName.substring(5,7));
						String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
					    String insideyear=MergeAllPdfNewInv.getInsideYear(crNo);
					    String count=MergeAllPdfNewInv.getcount(crNo);
					      

						  String strPdfPath = patientcreatefileftpaddress+"/"+strPdfName.substring(0,5)+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+strPdfName.substring(0,15)+"/"+strPdfName;
						//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
						  
						  System.out.println("strPdfPath : "+strPdfPath);
						
						//ByteArrayOutputStream Pdf;
						  
						  URL urlftp=new URL(strPdfPath);
							URLConnection urlcon=urlftp.openConnection();
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
				//	byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(strPdfName);
				 
					 
					 //pdfs.add(Pdf);	changed ftp by chnadan
							pdfs.add(urlcon.getInputStream());	
							
				  }
					
				//}
					 
				filename=filename+".pdf";
					 response.setContentType("application/pdf");
					 response.setHeader("content-disposition", "inline; filename="+filename);	
					// response.setHeader("Cache-Control", "no-cache");
						OutputStream output = response.getOutputStream();
						System.out.println("concatallPDFsconcatallPDFsconcatallPDFsconcatallPDFs chandannn");
						MergeAllPdfNewInv.concatallPDFsFTP(pdfs, output, null,request);		
						//output.write(Pdf);
					 // System.out.println("------------------->"+output); 
					//	mp=InvResultReportPrintingDATA.saveResultReportPrintingDetails(lstInvResultReportPrintingVO,  userVO);
					   
		}
		
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			//WebUTIL.setStatus(request, status);
		}
		
		//return sbAjaxRes;
	}
	
	public static void printReportFTP(InvResultReportPrintingFB fb, HttpServletRequest objRequest_p,HttpServletResponse response)
	{
		
		 
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		Status status = new Status();
		//List<byte[]> pdfs = new ArrayList<byte[]>(); //chnaged by chandan FTP
		List<InputStream> pdfs = new ArrayList<InputStream>();
		System.out.println("fb.getFileName() : "+fb.getFileName());
         String strPdfName=fb.getFileName();
		try
		{
			String patientcreatefileftpaddress= fb.getFtpserver();

			String crNo=strPdfName.substring(0,15);
			System.out.println(strPdfName.substring(5,7));
			String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
		    String insideyear=MergeAllPdfNewInv.getInsideYear(crNo);
		    String count=MergeAllPdfNewInv.getcount(crNo);
		      

			  String strPdfPath = patientcreatefileftpaddress+"/"+strPdfName.substring(0,5)+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+strPdfName.substring(0,15)+"/"+strPdfName;
			//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
			  
			  System.out.println("strPdfPath : "+strPdfPath);
			
			//ByteArrayOutputStream Pdf;
			  
			  URL urlftp=new URL(strPdfPath);
				URLConnection urlcon=urlftp.openConnection();
				
			/*byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(fb.getFileName());
			
			pdfs.clear();
			pdfs.add(Pdf)*/;	
			pdfs.clear();
			pdfs.add(urlcon.getInputStream());	

		 response.setContentType("application/pdf");
			 response.setHeader("Cache-Control", "no-cache");
				OutputStream output = response.getOutputStream();
				MergeAllPdfNewInv.concatallPDFsFTP(pdfs, output, null,objRequest_p);		
				output.flush();
				output.close();
				//return;
			
		}
		catch (Exception e)
		{
			strMsgText = "InvResultReportPrintingUTIL.printReport() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
			//WebUTIL.setStatus(objRequest_p, status);
		}
	
	}	

	
	
	public static void showResultEntryPatDetails_viewinvFTP(InvResultReportPrintingFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		//List<byte[]> pdfs = new ArrayList<byte[]>(); chnaged by chandna FTP
		List<InputStream> pdfs = new ArrayList<InputStream>();

		try{
			List<viewInvestigationVO> InvResultReportPrintingVO=null;
			viewInvestigationVO invresultentryv = new viewInvestigationVO();
			List<viewInvestigationVO> lstInvResultReportPrintingVO=new ArrayList<viewInvestigationVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<viewInvestigationVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			
			
			/*for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(viewInvestigationVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					filename=crNo;
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNo=arrSelectedRequisitions[i].split(",")[2];
					 String grpCode=arrSelectedRequisitions[i].split(",")[3];
					
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
              
						 
						
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
					}
					 }
					 else
					 {
						 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
							{
		              		 
								lstInvResultReportPrintingVO.add(objPatientCollectionVO);
								 
							} 
						 
					 }
					 }
			
				}*/
			
			/*int i=0;
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					 if(i<arrSelectedRequisitions.length)
					 {
					String crNo=arrSelectedRequisitions[i].split(",")[0];
					String reqNO=arrSelectedRequisitions[i].split(",")[1];
					String reqDNO=arrSelectedRequisitions[i].split(",")[2];
					 
					if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getPatReqNo().equals(reqNO))
					{
              
						  
						lstInvResultReportPrintingVO.add(objPatientCollectionVO);
						 
						++i;
					}
					 }
				}*/
				//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO, lstInvResultReportPrintingVO);
				//for(String str:arrSelectedRequisitions)
				//mp=InvResultReportPrintingDATA.PdfDetails(lstInvResultReportPrintingVO,  userVO);

				
				
				List<viewInvestigationVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<viewInvestigationVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);


				
				Map<String,String> objMapSamAcc= new HashMap<String,String>();
				
				
				 
				 
				/*for(int j=0; j<lstSampleAccepted.size();j++)
				{
					viewInvestigationVO objResultEntryVO = lstSampleAccepted.get(j);
					List<viewInvestigationVO> lstTempResultEntryVO = null;
					String strPdfName = objResultEntryVO.getPdfFileName();

					lstTempResultEntryVO=objMapSamAcc.get(strPdfName);

					
					 

					if(lstTempResultEntryVO==null)
					{
						lstTempResultEntryVO=new ArrayList<viewInvestigationVO>();
						lstTempResultEntryVO.add(objResultEntryVO);
					}
					else
					{
						lstTempResultEntryVO.add(objResultEntryVO);
					}

					objMapSamAcc.put(strPdfName,lstTempResultEntryVO);

				}*/
				
				for(int i=0;i<arrSelectedRequisitions.length;i++)
				 {
				

					String pdfname=arrSelectedRequisitions[i];
					objMapSamAcc.put(pdfname,"12");
					
				 }
				
				Set setPdfName=objMapSamAcc.keySet();

				Iterator itrPdfName=setPdfName.iterator();
				String patientcreatefileftpaddress= fb.getFtpserver();

				//iterate over Crno's
				pdfs.clear();
				while(itrPdfName.hasNext())
				{
					
				
				
				
				  //for(InvResultReportPrintingVO invReportvo:lstSampleAccepted)
				 // {
					
					 // String testingFileName=invReportvo.getPdfFileName();
					 
					  String strPdfName=(String)itrPdfName.next();
					  
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
					 /* byte[] Pdf=null;
					  
					  Pdf =MongoXmlHandler.getInstance().latestFetchFile(strPdfName);
						 
					 pdfs.add(Pdf);	*/
					  
					  
					  String crNo=strPdfName.substring(0,15);
						System.out.println(strPdfName.substring(5,7));
						String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
					    String insideyear=MergeAllPdfNewInv.getInsideYear(crNo);
					    String count=MergeAllPdfNewInv.getcount(crNo);
					      

						  String strPdfPath = patientcreatefileftpaddress+"/"+strPdfName.substring(0,5)+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+strPdfName.substring(0,15)+"/"+strPdfName;
						//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
						  
						  System.out.println("strPdfPath : "+strPdfPath);
						
						//ByteArrayOutputStream Pdf;
						  
						  URL urlftp=new URL(strPdfPath);
							URLConnection urlcon=urlftp.openConnection();
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
				//	byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(strPdfName);
				 
					 
					 //pdfs.add(Pdf);	changed ftp by chnadan
							pdfs.add(urlcon.getInputStream());	
							
					  
				  }
					
				//}
					 
				filename=filename+".pdf";
					 response.setContentType("application/pdf");
					 response.setHeader("Cache-Control", "no-cache");
						
					// response.setHeader("Cache-Control", "no-cache");
						OutputStream output = response.getOutputStream();
						System.out.println("concatallPDFsconcatallPDFsconcatallPDFsconcatallPDFs chandannn");
						MergeAllPdfNewInv.concatallPDFsFTP(pdfs, output, null,request);	
						output.flush();
						output.close();
						//output.write(Pdf);
					 // System.out.println("------------------->"+output); 
						//mp=InvResultReportPrintingDATA.saveResultReportPrintingDetails(lstInvResultReportPrintingVO,  userVO);
					   
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			//WebUTIL.setStatus(request, status);
		}
		
		//return sbAjaxRes;
	}

	
	public static String isfromFTPorMONGO(HttpServletRequest request,HttpServletResponse response)
	{
		String flg="";
		UserVO userVO = ControllerUTIL.getUserVO(request);
		
		if(userVO==null)
		{
			
			String hospitalcode=(String)request.getAttribute("hosptialcode");
			userVO =new UserVO();
			userVO.setHospitalCode(hospitalcode);
		}
		
		flg=InvResultReportPrintingDATA.isfromFTPorMONGO( userVO);
		
		return flg;
		
	}
	
	
	
	
}