package new_investigation.transactions.controller.utl;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.SimpleBookmark;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvResultReportPrintingDATA;
import new_investigation.transactions.controller.data.reportDownloadProcessDATA;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.reportDownloadProcessVO;
import hisglobal.Status;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class reportDownloadProcessUTL extends ControllerUTIL {

	
	public static String fetchMobileNo(reportDownloadProcessFB fb,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		String mp="";
	
		
		try
		{
			
          
			
			 mp=reportDownloadProcessDATA.fetchMobileNo(fb );
			

		}
		catch (Exception e)
		{
			strMsgText = "reportDownloadProcessUtl.fetchMobileNo() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return mp;
	}
	
	
	
	public static String fetchusername(reportDownloadProcessFB fb,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		String mp="";
	
		
		try
		{
			
          
			
			 mp=reportDownloadProcessDATA.fetchusername(fb );
			

		}
		catch (Exception e)
		{
			strMsgText = "reportDownloadProcessUtl.fetchMobileNo() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return mp;
	}
	
	
	
	public static String validatecrno(reportDownloadProcessFB fb,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		String mp="";
	
		
		try
		{
			
          
			
			 mp=reportDownloadProcessDATA.validatecrno(fb );
			

		}
		catch (Exception e)
		{
			strMsgText = "reportDownloadProcessUtl.fetchMobileNo() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return mp;
	}
	
	public static String fetchlist(reportDownloadProcessFB fb,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		String json="";
		reportDownloadProcessVO vo=new reportDownloadProcessVO();
		
		try
		{
			
          HelperMethods.populate(vo,fb);
			 mp=reportDownloadProcessDATA.fetchlist(vo );
			 json= (String) mp.get(json);
			 WebUTIL.setMapInSession(mp, objRequest_p);
               
		}
		catch (Exception e)
		{
			strMsgText = "reportDownloadProcessUtl.fetchMobileNo() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return json;
	}
	
	
	public static String Pfetchlist(reportDownloadProcessFB fb,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		String json="";
		reportDownloadProcessVO vo=new reportDownloadProcessVO();
		
		try
		{
			
          HelperMethods.populate(vo,fb);
			 mp=reportDownloadProcessDATA.Pfetchlist(vo );
			 json= (String) mp.get(json);
			 WebUTIL.setMapInSession(mp, objRequest_p);
               
		}
		catch (Exception e)
		{
			strMsgText = "reportDownloadProcessUtl.fetchMobileNo() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return json;
	}
	

	
	
	
	
	public static void  showResultEntryPatDetails(reportDownloadProcessFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		String base64bytearray="";
		
		try{
			  System.out.println("ipp:"+InetAddress.getLocalHost().getHostAddress());
			List<InvResultReportPrintingVO> InvResultReportPrintingVO=null;
			InvResultReportPrintingVO invresultentryv = new InvResultReportPrintingVO();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationConfig.PUBLIC_LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			
      //uncomment		String selectedCheckBoxValue=fb.getSelectedCheckbox();
			String selectedCheckBoxValue = request.getParameter("selectedCheckbox");
			
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			
			for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					String crNo=arrSelectedRequisitions[i].split("\\$")[0];
					filename=crNo;
					String reqNO=arrSelectedRequisitions[i].split("\\$")[1];
					String reqDNo=arrSelectedRequisitions[i].split("\\$")[2];
					 String grpCode=arrSelectedRequisitions[i].split("\\$")[3];
					
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
              
						 
			//uncomment			 objPatientCollectionVO.setReportType(fb.getReportType());
						 String ipaddress=InetAddress.getLocalHost().getHostAddress();
						 objPatientCollectionVO.setReportType("0");
						 objPatientCollectionVO.setIpAddress(ipaddress);
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
				
			
		mp=InvResultReportPrintingDATA.PdfDetails(lstInvResultReportPrintingVO,  userVO);  

				
				
				List<InvResultReportPrintingVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<InvResultReportPrintingVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);

				List<byte[]> pdfs = new ArrayList<byte[]>();
				 
				
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

				//iterate over Crno's
				while(itrPdfName.hasNext())
				{
					
				
				
				
				  //for(InvResultReportPrintingVO invReportvo:lstSampleAccepted)
				 // {
					
					 // String testingFileName=invReportvo.getPdfFileName();
					 
					  String strPdfName=(String)itrPdfName.next();
					  filename=strPdfName;
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
					byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(strPdfName);
				 
					String base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(Pdf);
					byte[] bytearray=org.apache.commons.codec.binary.Base64.decodeBase64(base64EncodedString);
					System.out.println("pdfarrayy:"+bytearray.toString());
					System.out.println("padf in string 64:"+base64EncodedString);
					 pdfs.add(Pdf);	
					  
					  
				  }
					
				//}
			              	filename=filename+".pdf";
					 response.setContentType("application/pdf");
					 response.setHeader("content-disposition", "inline; filename="+filename);
					 response.setHeader("content-disposition", "inline; filename="+filename);
					
					 /*response.setHeader("Cache-Control", "no-cache");*/
						OutputStream output = response.getOutputStream();
						MergeAllPdfNewInv.concatallPDFs(pdfs, output, null,request);		
						//output.write(Pdf);
					 // System.out.println("------------------->"+output); 
					
		//mp=reportDownloadProcessDATA.savePublicResultReportPrintingDetails(lstInvResultReportPrintingVO);
					   
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
	
	
	
	public static String sendMailReport(reportDownloadProcessFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		String  base64bytearray="";
		try{
			  System.out.println("ipp:"+InetAddress.getLocalHost().getHostAddress());
			List<InvResultReportPrintingVO> InvResultReportPrintingVO=null;
			InvResultReportPrintingVO invresultentryv = new InvResultReportPrintingVO();
			List<InvResultReportPrintingVO> lstInvResultReportPrintingVO=new ArrayList<InvResultReportPrintingVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			InvResultReportPrintingVO=(List<InvResultReportPrintingVO>)session.getAttribute(InvestigationConfig.PUBLIC_LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO);
			
      //uncomment		String selectedCheckBoxValue=fb.getSelectedCheckbox();
			String selectedCheckBoxValue = request.getParameter("selectedCheckbox");
			
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			
			for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(InvResultReportPrintingVO objPatientCollectionVO:InvResultReportPrintingVO)
				{
					String crNo=arrSelectedRequisitions[i].split("\\$")[0];
					String reqNO=arrSelectedRequisitions[i].split("\\$")[1];
					String reqDNo=arrSelectedRequisitions[i].split("\\$")[2];
					 String grpCode=arrSelectedRequisitions[i].split("\\$")[3];
					
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatPuk().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
              
						 
			//uncomment			 objPatientCollectionVO.setReportType(fb.getReportType());
						 String ipaddress=InetAddress.getLocalHost().getHostAddress();
						 objPatientCollectionVO.setReportType("0");
						 objPatientCollectionVO.setIpAddress(ipaddress);
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
				
			
		         mp=InvResultReportPrintingDATA.PdfDetails(lstInvResultReportPrintingVO,  userVO);  

				
				
				List<InvResultReportPrintingVO> lstInvResultEntryTemplateVO=null;
				//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
				List<InvResultReportPrintingVO> lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_RESULT_REPORT_PRINTING_PDF_DETAIL);
				WebUTIL.setMapInSession(mp, request);

				List<byte[]> pdfs = new ArrayList<byte[]>();
				 
				
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

				//iterate over Crno's
				while(itrPdfName.hasNext())
				{
					
				
				
				
				  //for(InvResultReportPrintingVO invReportvo:lstSampleAccepted)
				 // {
					
					 // String testingFileName=invReportvo.getPdfFileName();
					 
					  String strPdfName=(String)itrPdfName.next();
					  filename=strPdfName;
					 
					//String testingfileNAme="331041400000017_10008_05282015034109PM.pdf";
					
					//ByteArrayOutputStream Pdf;
					
					byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(strPdfName);
				 
				/*	String base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(Pdf);
					byte[] bytearray=org.apache.commons.codec.binary.Base64.decodeBase64(base64EncodedString);
					System.out.println("pdfarrayy:"+bytearray.toString());
					System.out.println("padf in string 64:"+base64EncodedString);*/
					 pdfs.add(Pdf);	
					  
					  
				  }
					
				//}
					
					// response.setContentType("application/pdf");
					// response.setHeader("content-disposition", "inline; filename="+filename);
					
					 /*response.setHeader("Cache-Control", "no-cache");*/
					//	OutputStream output = response.getOutputStream();
						base64bytearray=	concatallPDFs(pdfs, null,request);		
						//output.write(Pdf);
					 // System.out.println("------------------->"+output); 
					
	//	mp=reportDownloadProcessDATA.savePublicResultReportPrintingDetails(lstInvResultReportPrintingVO);
					   
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			//WebUTIL.setStatus(request, status);
		}
		return base64bytearray;
		
		//return sbAjaxRes;
	}
	
	public static void loginInsertDetails(String crNo,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		InvResultReportPrintingVO invresultreportprintingvo=new InvResultReportPrintingVO();
		
		try
		{
			String ipaddress=InetAddress.getLocalHost().getHostAddress();
			
			invresultreportprintingvo.setPatCRNo(crNo);
			invresultreportprintingvo.setIpAddress(ipaddress);
			
			 mp=reportDownloadProcessDATA.loginInsertDetails(invresultreportprintingvo );
			

		}
		catch (Exception e)
		{
			strMsgText = "reportDownloadProcessUtl.fetchMobileNo() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
	
	}

	
	
	public static String  concatallPDFs(List<byte[]> pdfs, String strMode,HttpServletRequest request)
	{
		PdfReader reader=null;
		PdfStamper stamp =null;
		 
		HttpSession session=request.getSession();
		String base64EncodedString="";
	try
	{
		
		
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		ByteArrayOutputStream byteArrayOutputStreamMerge=new ByteArrayOutputStream();
		//mergeMyFiles(filesTobeMerges, mergedFileLocation);/root/investigationDetails/watermark/duplicateStamp.jpg
		
		byteArrayOutputStreamMerge=mergeMyFiles(pdfs, byteArrayOutputStream);
		 base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(byteArrayOutputStreamMerge.toByteArray());
		//System.out.println("mergeall"+base64EncodedString);
		
		
			}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
	}
	finally
	{
		
			if(reader!=null){
				reader.close();
			}
			if(stamp!=null){
				try {
					stamp.close();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	return base64EncodedString;
	}
	
	
	private static ByteArrayOutputStream mergeMyFiles(List<byte[]> filesTobeMerges,
			ByteArrayOutputStream byteArrayOutputStream) {
		// TODO Auto-generated method stub
		System.out.println("Starting To Merge Files...");
		//System.out.println("Total Number Of Files To Be Merged..."+ filesToBeMerged.length + "\n");D:\root\investigationDetails\watermark\duplicateStamp.jpg D:\NEW_INVESTIGATION_21_05-2015\AHIMS\WebContent\new_investigation\images\duplicateStamp.jpg
		
		
		try {
			//Image img = Image.getInstance(InvestigationConfig.fetchfilepath+"duplicateStamp.jpg");	      
		   //  img.setAbsolutePosition(23,725);
		     
		    // Watermark watermark=new Watermark(img, 25, 725);
			int pageOffset = 0;
			ArrayList masterBookMarkList = new ArrayList();

			int fileIndex = 0;
			//String outFile = mergedFileLocation;
			Document document = null;
			PdfCopy writer = null;
			PdfReader reader = null;

			for (fileIndex = 0; fileIndex < filesTobeMerges.size(); fileIndex++) {

				
				/**
				 * Create a reader for the file that we are reading
				 */
				reader = new PdfReader(filesTobeMerges.get(fileIndex),"Administrator".getBytes()); //filesToBeMerged[fileIndex]
				System.out.println("Reading File -");

				/**
				 * Replace all the local named links with the actual
				 * destinations.
				 */
				reader.consolidateNamedDestinations();

				/**
				 * Retrieve the total number of pages for this document
				 */
				int totalPages = reader.getNumberOfPages();

				/**
				 * Get the list of bookmarks for the current document If the
				 * bookmarks are not empty, store the bookmarks into a master
				 * list
				 */
				
				System.out.println("Checking for bookmarks...");				
				List bookmarks = SimpleBookmark.getBookmark(reader);
				if (bookmarks != null) {
					if (pageOffset != 0)
						SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset,
								null);
					masterBookMarkList.addAll(bookmarks);
					System.out.println("Bookmarks found and storing...");
				} else {
					System.out.println("No bookmarks in this file...");
				}
				pageOffset += totalPages;

				/**
				 * Merging the files to the first file. If we are passing file1,
				 * file2 and file3, we will merge file2 and file3 to file1.
				 */
				if (fileIndex == 0) {
					/**
					 * Create the document object from the reader
					 */
					document = new Document(reader.getPageSizeWithRotation(1));

					/**
					 * Create a pdf write that listens to this document. Any
					 * changes to this document will be written the file
					 * 
					 * outFile is a location where the final merged document
					 * will be written to.
					 */

					System.out.println("Creating an empty PDF...");
					writer = new PdfCopy(document,
							byteArrayOutputStream);
					/**
					 * Open this document
					 */
					
					//writer.add(watermark);
					document.open();
					//document.add(img);
					//document.add(watermark);
				}
				/**
				 * Add the conent of the file into this document (writer). Loop
				 * through multiple Pages
				 */
				System.out.println("Merging File: ");
				PdfImportedPage page;
				for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
					page = writer.getImportedPage(reader, currentPage);					
					writer.addPage(page);					
				}

				/**
				 * This will get the documents acroform. This will return null
				 * if no acroform is part of the document.
				 * 
				 * Acroforms are PDFs that have been turned into fillable forms.
				 */
				System.out.println("Checking for Acroforms");
				PRAcroForm form = reader.getAcroForm();
				if (form != null) {
					writer.copyAcroForm(reader);
					System.out.println("Acroforms found and copied");
				} else
					System.out.println("Acroforms not found for this file");

				System.out.println();
			}
			/**
			 * After looping through all the files, add the master bookmarklist.
			 * If individual PDF documents had separate bookmarks, master
			 * bookmark list will contain a combination of all those bookmarks
			 * in the merged document.
			 */
			if (!masterBookMarkList.isEmpty()) {
				writer.setOutlines(masterBookMarkList);
				System.out.println("All bookmarks combined and added");

			} else {
				System.out.println("No bookmarks to add in the new file");

			}

			/**
			 * Finally Close the main document, which will trigger the pdfcopy
			 * to write back to the filesystem.
			 */
			if(document!=null)
			document.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}
	
}
