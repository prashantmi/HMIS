package new_investigation.transactions.controller.utl;



 
import java.io.InputStream;
import java.io.OutputStream;
 







import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 


 



import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
 






import java.io.File;
import java.io.FileOutputStream;
 
import java.io.OutputStream;
 




import com.edb.core.Encoding;








import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

 
 


import java.io.PrintWriter;
import java.io.StringReader;
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
 
import javax.sql.rowset.spi.XmlReader;










import org.apache.struts2.views.freemarker.tags.FileModel;
 









import com.lowagie.text.html.HtmlWriter;
import com.mongodb.Bytes;
import com.mongodb.gridfs.GridFSDBFile;
import com.sun.mail.iap.Response;

//import mondrian.olap.type.StringType;
 



import new_investigation.InvestigationConfig;
 
import new_investigation.transactions.controller.data.InvDuplicateResultReportPrintingDATA;
import new_investigation.transactions.controller.data.InvResultEntryDATA;
 
import new_investigation.transactions.controller.data.InvResultReportPrintingDATA;
 
import new_investigation.transactions.controller.data.OnlinePatientAcceptanceDATA;
import new_investigation.transactions.controller.data.SampleAcceptanceDATA;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.fb.InvDuplicateResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
 
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
import new_investigation.vo.InvResultReportPrintingVO;

public class InvDuplicateResultReportPrintingUTIL extends ControllerUTIL
{
	public static Map getEssential(InvDuplicateResultReportPrintingFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		InvDuplicateResultReportPrintingVO invresultReportPrintingvo=new InvDuplicateResultReportPrintingVO();
		 
		Map mp=new HashMap();
		 
	try{	
		 
		Map mpp=new HashMap();
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		mp=InvDuplicateResultReportPrintingDATA.LabComboForDuplicateResultReportPrinting(invresultReportPrintingvo, userVO);
		 String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		invresultReportPrintingvo.setFromDate(tDate);
		  invresultReportPrintingvo.setToDate(tDate);
		  invresultReportPrintingvo.setLabCode("%");
		
		
		mpp=InvDuplicateResultReportPrintingDATA.setDuplicateResultReportPrintingEssentialsOnLoad(invresultReportPrintingvo, userVO);
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
	
	return mp;
	
	}
	
	
	public static boolean setResultReportPrintingEssentials(InvDuplicateResultReportPrintingFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvDuplicateResultReportPrintingVO invresultReportPrintingvo=new InvDuplicateResultReportPrintingVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			invresultReportPrintingvo.setFromDate(fb.getFromDate()); 
			invresultReportPrintingvo.setToDate(fb.getToDate());
			invresultReportPrintingvo.setPatCRNo(fb.getPatCRNo()); 
			//updated by krishnan nema on 01/10/2018
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
			mp=InvDuplicateResultReportPrintingDATA.setDuplicateResultReportPrintingEssentials(invresultReportPrintingvo, userVO);
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
	
	public static void showResultEntryPatDetails(InvDuplicateResultReportPrintingFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		try{
			List<InvDuplicateResultReportPrintingVO> InvResultReportPrintingVO=null;
			InvDuplicateResultReportPrintingVO invresultentryv = new InvDuplicateResultReportPrintingVO();
			List<InvDuplicateResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<InvDuplicateResultReportPrintingVO>)session.getAttribute(InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			 
                   String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			 
			for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(InvDuplicateResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
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
			
				}
			
			 
			
			/*String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			int i=0;
				for(InvDuplicateResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
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
				mp=InvDuplicateResultReportPrintingDATA.duplicatePdfDetails(lstInvResultReportPrintingVO,  userVO);

				
				
				List<InvDuplicateResultReportPrintingVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<InvDuplicateResultReportPrintingVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);

				List<byte[]> pdfs = new ArrayList<byte[]>();
				 
				
				Map<String,List<InvDuplicateResultReportPrintingVO>> objMapSamAcc= new HashMap<String,List<InvDuplicateResultReportPrintingVO>>();
				
				
				 
				 
				for(int j=0; j<lstSampleAccepted.size();j++)
				{
					InvDuplicateResultReportPrintingVO objResultEntryVO = lstSampleAccepted.get(j);
					List<InvDuplicateResultReportPrintingVO> lstTempResultEntryVO = null;
					String strPdfName = objResultEntryVO.getPdfFileName();

					lstTempResultEntryVO=objMapSamAcc.get(strPdfName);

					
					 

					if(lstTempResultEntryVO==null)
					{
						lstTempResultEntryVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
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
					 response.setHeader("Cache-Control", "no-cache");
						OutputStream output = response.getOutputStream();
						String duplicate="DUPLICATEDPRINTING";
						
						MergeAllPdfNewInv.concatallPDFs(pdfs, output, duplicate,request);		
						//output.write(Pdf);
					 // System.out.println("------------------->"+output);
					  
					   
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
	  
	
	
	public static void showResultEntryPatDetailsFTP(InvDuplicateResultReportPrintingFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		try{
			List<InvDuplicateResultReportPrintingVO> InvResultReportPrintingVO=null;
			InvDuplicateResultReportPrintingVO invresultentryv = new InvDuplicateResultReportPrintingVO();
			List<InvDuplicateResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<InvDuplicateResultReportPrintingVO>)session.getAttribute(InvestigationConfig.LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			 
                   String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			 
			for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(InvDuplicateResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
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
			
				}
			
			 
			
			/*String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			int i=0;
				for(InvDuplicateResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
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
				mp=InvDuplicateResultReportPrintingDATA.duplicatePdfDetails(lstInvResultReportPrintingVO,  userVO);

				
				
				List<InvDuplicateResultReportPrintingVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<InvDuplicateResultReportPrintingVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);

				//List<byte[]> pdfs = new ArrayList<byte[]>(); //chnaged FTP by chandan
				List<InputStream> pdfs = new ArrayList<InputStream>();

				
				Map<String,List<InvDuplicateResultReportPrintingVO>> objMapSamAcc= new HashMap<String,List<InvDuplicateResultReportPrintingVO>>();
				
				
				 
				 
				for(int j=0; j<lstSampleAccepted.size();j++)
				{
					InvDuplicateResultReportPrintingVO objResultEntryVO = lstSampleAccepted.get(j);
					List<InvDuplicateResultReportPrintingVO> lstTempResultEntryVO = null;
					String strPdfName = objResultEntryVO.getPdfFileName();

					lstTempResultEntryVO=objMapSamAcc.get(strPdfName);

					
					 

					if(lstTempResultEntryVO==null)
					{
						lstTempResultEntryVO=new ArrayList<InvDuplicateResultReportPrintingVO>();
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
				String patientcreatefileftpaddress= "ftp://nimsftp:nims_123@10.226.2.186/ftpserver/ftpserver";

				//iterate over Crno's
				while(itrPdfName.hasNext())
				{
					
				
				
				
				  //for(InvResultReportPrintingVO invReportvo:lstSampleAccepted)
				 // {
					
					 // String testingFileName=invReportvo.getPdfFileName();
					 
					  String strPdfName=(String)itrPdfName.next();
					  
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
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
					 response.setHeader("Cache-Control", "no-cache");
						OutputStream output = response.getOutputStream();
						String duplicate="DUPLICATEDPRINTING";
						
						MergeAllPdfNewInv.concatallPDFsFTP(pdfs, output, duplicate,request);		
						//output.write(Pdf);
					 // System.out.println("------------------->"+output);
					  
					   
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
	  
	 
	
	
	 
		
}