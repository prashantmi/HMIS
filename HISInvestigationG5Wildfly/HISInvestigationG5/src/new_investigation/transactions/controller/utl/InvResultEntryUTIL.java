package new_investigation.transactions.controller.utl;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.data.InvResultEntryDATA;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.invAntibioticProcessVO;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.upload.FormFile;
import org.bson.types.ObjectId;

import MongoHelper.MongoXmlHandler;

import com.itextpdf.text.pdf.codec.Base64;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class InvResultEntryUTIL extends ControllerUTIL
{
    public static String height="50";

	public static void getEssential(InvResultEntryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
		
		ResultEntryVO invresultentryvo=new ResultEntryVO();
		
		try{	
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 


			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=InvResultEntryDATA.LabComboForResultEntry(invresultentryvo, userVO);
			WebUTIL.setMapInSession(mp, request);
			
			  String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			  invresultentryvo.setFromDate(tDate);
			  invresultentryvo.setToDate(tDate);
			  invresultentryvo.setLabCode("%");
			  invresultentryvo.setNewEntry("1");
			  invresultentryvo.setOnLoadValue("ONLOAD");
			  invresultentryvo.setSearchBy("1");
			  invresultentryvo.setSampleAreaCode(fb.getSampleAreaCode());
			  
				mpp=InvResultEntryDATA.setPatientResultEntryEssentials(invresultentryvo, userVO);
				mpp.put(InvestigationConfig.LIST_SAMPLE_COLLECTION_VO, "1");
				WebUTIL.setMapInSession(mpp, request);
			  
				fb.setLabCode("%");
			//HelperMethods.populate(fb, invresultentryvo);
				objStatus.add(Status.TRANSINPROCESS);
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



	}


	public static boolean setPatientEssentials(InvResultEntryFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		ResultEntryVO invresultentryvo = new ResultEntryVO();
		ResultEntryVO invresultentryvosession = new ResultEntryVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 

			invresultentryvo.setFromDate(fb.getFromDate()); 
			invresultentryvo.setToDate(fb.getToDate());
			invresultentryvo.setToCollDate(fb.getToCollDate());
			invresultentryvo.setFromCollDate(fb.getFromCollDate());
			invresultentryvo.setPatCRNo(fb.getPatCRNo()); 
			invresultentryvo.setLabCode(fb.getLabCode());
			invresultentryvo.setTestWiseCode(fb.getTestWiseCode());
			invresultentryvo.setFromLabNo(fb.getFromLabNo());
			invresultentryvo.setToLabNo(fb.getToLabNo());
			invresultentryvo.setFromSampleNo(fb.getFromSampleNo());
			invresultentryvo.setToSampleNo(fb.getToSampleNo());
			invresultentryvo.setGenerationType(fb.getGenerationType());
			invresultentryvo.setTestGroupCode(fb.getTestGroupCodeWise());
			invresultentryvo.setOnLoadValue(fb.getOnLoadValue());
			invresultentryvo.setNewEntry(fb.getNewEntry());
			invresultentryvo.setTempPatName(fb.getTempPatName());
			invresultentryvo.setSearchBy(fb.getSearchBy());
			invresultentryvo.setSampleAreaCode(fb.getSampleAreaCode());
			invresultentryvo.setSampleAreaName(fb.getSampleAreaName());
			mp=InvResultEntryDATA.setPatientResultEntryEssentials(invresultentryvo, userVO);
			mp.put(InvestigationConfig.FILTER_LIST,invresultentryvo);
			
		//	session.setAttribute("isFilter", "1");
			String val="1";
/*			mp=InvResultEntryDATA.LabComboForResultEntry(invresultentryvo, userVO);*/
			mp.put(InvestigationConfig.isFilter,val);
			mp.put(InvestigationConfig.LIST_SAMPLE_COLLECTION_VO,val);
			
			WebUTIL.setMapInSession(mp, _request);


			//HelperMethods.populate(fb, invresultentryvo);

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

	public static void showResultEntryPatDetails(InvResultEntryFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		HttpSession session=request.getSession();

		Map mp=new HashMap();
		boolean flag=false;
		try{
			List<ResultEntryVO> invresultentryvo=null;
			ResultEntryVO invresultentryv = new ResultEntryVO();
			List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();

			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			invresultentryvo=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();

			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			int length=arrSelectedRequisitions.length;

			for(int i=0;i<arrSelectedRequisitions.length;i++)
			{
				for(ResultEntryVO objPatientCollectionVO:invresultentryvo)
				{

					//fetching the list <parameter, loinc>

					//	InvResultEntryDATA.fetchLoincCode(objPatientCollectionVO,userVO);


					String crNo=arrSelectedRequisitions[i].split("#")[0];
					String reqNO=arrSelectedRequisitions[i].split("#")[1];
					String reqDNo=arrSelectedRequisitions[i].split("#")[2];
					String grpCode=arrSelectedRequisitions[i].split("#")[3];

					if(objPatientCollectionVO.getGroupCode()==null)
					{ 
						if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
						{
							/////WebUTIL.populate(fb,objPatientCollectionVO); 
							//WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO,objPatientCollectionVO);

							ResultEntryVO rvo = new ResultEntryVO(null,objPatientCollectionVO.getTestCode(),null,null);
							//rvo.setSessionId("243434");

							rvo.setRequisitionDNo(reqDNo);
							rvo.setSampleNo(objPatientCollectionVO.getSampleNo());


							rvo.setLabCode(objPatientCollectionVO.getLabCode());
							rvo.setFinalRemarkReqd(objPatientCollectionVO.getFinalRemarkReqd());
							rvo.setFinalRemarkString(objPatientCollectionVO.getFinalRemarkString());
							rvo.setSampleCode(objPatientCollectionVO.getSampleCode());
							rvo.setUomCode(objPatientCollectionVO.getUomCode());
							rvo.setEpisodeCode(objPatientCollectionVO.getEpisodeCode());
							rvo.setRequisitionNo(objPatientCollectionVO.getRequisitionNo());
							rvo.setSampleNo(objPatientCollectionVO.getSampleNo());
							rvo.setHospitalcode(userVO.getHospitalCode());
							rvo.setPatGender(objPatientCollectionVO.getPatGender());
							rvo.setPatAge(objPatientCollectionVO.getPatAge());
							/*//Added by PrashantMi
							rvo.setIsRepeat(objPatientCollectionVO.getIsRepeat());
							*/
							List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();
							lrvo.add(rvo);
							objPatientCollectionVO.setResultEntryVOListValidatedBy(lrvo); 
							lstInvResultEntryVO.add(objPatientCollectionVO);
							//invresultentryv=objPatientCollectionVO;
							//++i;
						}
					}
					else
					{
						if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
						{
							/////WebUTIL.populate(fb,objPatientCollectionVO); 
							//WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO,objPatientCollectionVO);

							ResultEntryVO rvo = new ResultEntryVO(null,objPatientCollectionVO.getTestCode(),null,null);
							//rvo.setSessionId("243434");

							rvo.setRequisitionDNo(objPatientCollectionVO.getRequisitionDNo());
							rvo.setSampleNo(objPatientCollectionVO.getSampleNo());
							rvo.setFinalRemarkReqd(objPatientCollectionVO.getFinalRemarkReqd());
							rvo.setFinalRemarkString(objPatientCollectionVO.getFinalRemarkString());

							rvo.setLabCode(objPatientCollectionVO.getLabCode());
							rvo.setSampleCode(objPatientCollectionVO.getSampleCode());
							rvo.setUomCode(objPatientCollectionVO.getUomCode());
							rvo.setEpisodeCode(objPatientCollectionVO.getEpisodeCode());
							rvo.setRequisitionNo(objPatientCollectionVO.getRequisitionNo());
							rvo.setSampleNo(objPatientCollectionVO.getSampleNo());
							rvo.setPatGender(objPatientCollectionVO.getPatGender());
							rvo.setPatAge(objPatientCollectionVO.getPatAge());
							rvo.setHospitalcode(objPatientCollectionVO.getHospitalcode());
							/*//Added by PrashantMi
							rvo.setIsRepeat(objPatientCollectionVO.getIsRepeat());
							*/
							List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();

							//invresultentryv=objPatientCollectionVO;
							//	++i;
							// if( objPatientCollectionVO.getDynamnicTemplateResultEntryGroup() != null && objPatientCollectionVO.getDynamnicTemplateResultEntryGroup().equals("1")) {
							// rvo.setTestName(objPatientCollectionVO.getGroupName());
							rvo.setGroupCode(objPatientCollectionVO.getGroupCode());
							rvo.setDynamnicTemplateResultEntryGroup(objPatientCollectionVO.getDynamnicTemplateResultEntryGroup());
							rvo.setDynamicTemplatePrintedGroup(objPatientCollectionVO.getDynamicTemplatePrintedGroup());
							//									 if(lstInvResultEntryVO.size() == 0){
							//										 lrvo.add(rvo);
							//											objPatientCollectionVO.setResultEntryVOListValidatedBy(lrvo); 
							//											lstInvResultEntryVO.add(objPatientCollectionVO);
							//									 }
							//									 else {
							//									 for( int k = 0; k < lstInvResultEntryVO.size(); k++)
							//										 {
							//										    List<ResultEntryVO> lr = lstInvResultEntryVO.get(k).getResultEntryVOListValidatedBy();
							//										   for( int j = 0; j < lr.size(); j++)
							//										   {
							//											   if(lr.get(j).getGroupCode() != null && lr.get(j).getGroupCode().equals(rvo.getGroupCode())
							//													   && lr.get(j).getDynamnicTemplateResultEntryGroup() != null && 
							//													   lr.get(j).getDynamnicTemplateResultEntryGroup().equals(objPatientCollectionVO.getDynamnicTemplateResultEntryGroup()))
							//											   {
							//												// do nothing   
							//											   }
							//											   else
							//											   {
							//												   lrvo.add(rvo);
							//													objPatientCollectionVO.setResultEntryVOListValidatedBy(lrvo); 
							//													lstInvResultEntryVO.add(objPatientCollectionVO);
							//											   }
							//										   }
							//										 }
							//									 }
							//								 }
							//								 else 
							//								 {
							//									 lrvo.add(rvo);
							//										objPatientCollectionVO.setResultEntryVOListValidatedBy(lrvo); 
							//										lstInvResultEntryVO.add(objPatientCollectionVO);
							//								 }

							lrvo.add(rvo);
							objPatientCollectionVO.setResultEntryVOListValidatedBy(lrvo); 
							lstInvResultEntryVO.add(objPatientCollectionVO);

						} 

					}
				}

			}

			//for(String str:arrSelectedRequisitions)
			List<ResultEntryVO> lstInvResultEntryTemplateVO=null;
			lstInvResultEntryTemplateVO=TemplateProcessingUSE.groupSelectedWorkOrders(lstInvResultEntryVO, session);
			//		System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
			//System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getParaLoinc());
			//				List<ResultEntryVO> newLstInvResultEntryTemplateVO= new ArrayList<ResultEntryVO>();
			//				for(int i = 0; i < lstInvResultEntryTemplateVO.size(); i++ )
			//				{
			//					ResultEntryVO objResultEntryVO = lstInvResultEntryTemplateVO.get(i);
			//					List<ResultEntryVO> tempObj = new ArrayList<ResultEntryVO>();
			//					tempObj = objResultEntryVO.getResultEntryVOListValidatedBy();
			//					for(ResultEntryVO resultEntryVO: objResultEntryVO.getResultEntryVOListValidatedBy())
			//					{
			//						if(resultEntryVO.isDoCreateTemplate())
			//						{
			//						//	tempObj.remove(resultEntryVO);
			//						}
			//					}
			//					objResultEntryVO.setResultEntryVOListValidatedBy(tempObj);
			//					newLstInvResultEntryTemplateVO.add(objResultEntryVO);				
			//				}
			mp.put(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES, lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getAutoList());
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY,lstInvResultEntryTemplateVO);
			
			//WebUTIL.setAttributeInSession(request, "VOLIST", lstInvResultEntryTemplateVO);
			//mp.put(InvestigationConfig.LIST_RESULT_ENTRY,newLstInvResultEntryTemplateVO);

			Map<String,List<ResultEntryVO>> objMapResEntry= new LinkedHashMap<String,List<ResultEntryVO>>();

			Map<String,String> objMapResEntrypid= new LinkedHashMap<String,String>();
			
			String ipdshow="";
			String pidno="";
			
			for(int j=0; j<lstInvResultEntryTemplateVO.size();j++)
			{
				ResultEntryVO objResultEntryVO = lstInvResultEntryTemplateVO.get(j);
				List<ResultEntryVO> lstTempResultEntryVO = null;
				String strSampleNo = objResultEntryVO.getRequisitionNo()+objResultEntryVO.getSampleNo();//req no and sample no --- differentiate on the basis of large sample no for diff samples// only req no can be used if sample differentiation not required//
                
                
				lstTempResultEntryVO=objMapResEntry.get(strSampleNo);

				if(lstTempResultEntryVO==null)
				{
					lstTempResultEntryVO=new ArrayList<ResultEntryVO>();
					lstTempResultEntryVO.add(objResultEntryVO);
					
					if(pidno.equals(""))
	                {
					  if(objResultEntryVO.getIspidshow().equals("1"))
					 {
					 ipdshow=objResultEntryVO.getIspidshow();
	                 pidno=objResultEntryVO.getPidno();
					 }
					 
	                }
					
				}
				else
				{
					lstTempResultEntryVO.add(objResultEntryVO);
					
					if(pidno.equals(""))
	                {
					  if(objResultEntryVO.getIspidshow().equals("1"))
					 {
					 ipdshow=objResultEntryVO.getIspidshow();
	                 pidno=objResultEntryVO.getPidno();
					 }
					 
	                }
					
				}

				objMapResEntry.put(strSampleNo,lstTempResultEntryVO);
				
				 if(pidno!=null && !pidno.equals(""))
				objMapResEntrypid.put(strSampleNo,pidno);
				
				ipdshow="";
				pidno="";
			}

			//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO,objMapResEntry);
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO_PID,objMapResEntrypid);


			//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO, lstInvResultEntryTemplateVO);
			//mp=InvResultEntryDATA.patientDetails(invresultentryv,reqList, userVO);


/*			String setXMLvalue=(String)session.getAttribute("requiredXML");

               mp.put("setXMLinSession", setXMLvalue);
*/			WebUTIL.setMapInSession(mp, request);


			status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}


	// 
	//	public static void getResultEntryTemplateForSelectedWorkOrders(
	//			HttpServletRequest request, InvResultEntryFB fb) throws Exception {
	//		// TODO Auto-generated method stub
	//		// For testing purpose, hardcoding the VO
	//		ResultEntryVOGroupByValidatedBy rv = new ResultEntryVOGroupByValidatedBy();
	//		rv.setClinicianName("clinicianName");
	//		//rv.setDetpUnitCode(detpUnitCode);
	//		//rv.setLabNo(labNo);
	//		rv.setLaboratoryCode("1");
	//		rv.setLaboratoryName("af");
	//		rv.setPatCRNo("232525235");
	//		rv.setPatGender("1");
	//		rv.setPatName("ss");
	//		rv.setPatVisitNo("1");
	//		rv.setRequisitionDate("2222222");
	//		rv.setRequisitionDNo("13123123");
	//		rv.setRequisitionTypeCode("1");
	//		//rv.setRequisitionTypeName(requisitionTypeName);
	//		ResultEntryVO rvo = new ResultEntryVO(null,"10009",null,null);
	//		rvo.setSessionId("243434");
	//		rvo.setRequisitionDNo("dfdfdf");
	// 
	//	
	//		List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();
	//		lrvo.add(rvo);
	//		rv.setResultEntryVOListValidatedBy(lrvo);
	//		//rv.setSampleName(sampleName);
	//		rv.setSampleNo("121");
	//		rv.setTestCode("10020");
	//		rv.setTestName("test name");
	//		rv.setUserSampleNo("12");
	//		//rv.setVisitDate(visitDate);
	//		
	//		List<ResultEntryVOGroupByValidatedBy> selectedWorkOrderList=null;
	//		//changed
	//		List<ResultEntryVOGroupByValidatedBy> displayList=null;//(List<ResultEntryVOGroupByValidatedBy>)request.getSession().getAttribute("PAGELIST");
	//		
	//		HttpSession session = request.getSession();
	//		try
	//		{
	//		int j=0;
	//		if(fb.getSelectedWorkOrderNo()!=null)
	//		{
	//			for(String i:fb.getSelectedWorkOrderNo())
	//			{
	//				if(selectedWorkOrderList==null)
	//					selectedWorkOrderList=new ArrayList<ResultEntryVOGroupByValidatedBy>();
	//				
	//				selectedWorkOrderList.add(displayList.get(Integer.parseInt(i)));
	//			
	//				
	//			}
	//		}
	//		
	//		//changed
	//		selectedWorkOrderList=new ArrayList<ResultEntryVOGroupByValidatedBy>();
	//		selectedWorkOrderList.add(rv);
	//		///selectedWorkOrderList=TemplateProcessingUSE.groupSelectedWorkOrders(selectedWorkOrderList, session);
	//		
	//		
	//		}
	//		catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		request.getSession().removeAttribute("PAGELIST");
	//		System.out.println(selectedWorkOrderList.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
	//		request.getSession().setAttribute("SELECTEDWORKORDERLIST",selectedWorkOrderList);
	//	//	LOGGER_INV.log(Level.INFO,"size of selected workorder  = "+selectedWorkOrderList.size());
	//		
	//	}

	// Save Logic
	public static void saveResultEntryPatientDetails(InvResultEntryFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		Map mp=new HashMap();
		int SizeMapOne=0;
		HashMap<String,String> mpParaLoinc=new HashMap();
		HashMap<String,String> fetchMap=new HashMap();
		List<HashMap> newmaplist= new ArrayList<HashMap>();
		
		_request.removeAttribute("getuploadedfiledata");
		DBCollection table = null;

		try
		{
		
            Map<String,String> mpp=new HashMap<String,String>();      
			
			String dataforuploadfiles=_fb.getFileuploaddatabase64();
			
			if(dataforuploadfiles!=null && !dataforuploadfiles.equals("") && dataforuploadfiles.contains("#@#@"))
			{
				table= new_investigation.transactions.controller.utl.MongoXmlHandler.getInstance().getDbCollection();

				
				String data[]=dataforuploadfiles.split("#@#@");
				
				for(int k=0;k<data.length;k++)
				{
					String datas=data[k];
					
					String datass[]=datas.split("@@");
				
					String reqdno=datass[0];
				String filename=datass[1];
				String content=datass[2];
				String testparacode=datass[3];
				
				mpp.put(reqdno+testparacode, filename+"@@"+content);
				}
				
			}
			else if(dataforuploadfiles!=null && !dataforuploadfiles.equals("") )
			{
				
				table= new_investigation.transactions.controller.utl.MongoXmlHandler.getInstance().getDbCollection();

				
	            String data[]=dataforuploadfiles.split("@@");
				
				String reqdno=data[0];
				String filename=data[1];
				String content=data[2];
				String testparacode=data[3];
				
				mpp.put(reqdno+testparacode, filename+"@@"+content);
			
				
			}
			
			        _request.setAttribute("getuploadedfiledata", mpp);
			
			//MongoClient mongo = new MongoClient("10.226.2.169", 27017);

			//DB db = mongo.getDB("testdb");

			//DBCollection table = db.getCollection("patientDocumentsFiles1001");
			//DBCollection table = new_investigation.transactions.controller.utl.MongoXmlHandler.getInstance().getDbCollection();
			UserVO _UserVO1=getUserVO(_request);			
			//StringBuffer sbName=new StringBuffer();
		
			List myFiles =(List) _fb.getFile();
			 int sizeForcrreqFile=myFiles.size()-1;
			 for(int j1=0;j1<myFiles.size();j1++){
				 String filename="";
				 String fileextentsion="";
				 int filelength=0;
				 String crNoo="";
				 String reqNoo="";
				 if(myFiles.get(j1)!=null)
				 {
				 FormFile myFile =(FormFile)myFiles.get(j1) ;
				  filename=myFile.getFileName();
				  fileextentsion=myFile.getContentType();
				  filelength=myFile.getFileSize();
				 // byte[] filedatabyte=file.getFileData(); 
				  byte[] filedatabyte=myFile.getFileData();
				String value=_fb.getCrreqnoFile()[sizeForcrreqFile];
				sizeForcrreqFile--;
				String[] concatid = value.split("#");
					 crNoo=concatid[0];
					 reqNoo=concatid[1];
				
					 
					 BasicDBObject document1 = new BasicDBObject();
					 
					 BasicDBObject reqValuess=new BasicDBObject();
						reqValuess.put("crno", crNoo);
						 
						
						
						//start ch
						
						
						 DBCursor count1 = table.find(reqValuess);
						 DBObject reqValueObject=new BasicDBObject();
					boolean alreadyPresent=false;
							while(count1.hasNext())
							
								{alreadyPresent=true;
								reqValueObject=count1.next();
								}
							
							if(alreadyPresent)
							{
								
								BasicDBObject document11 = new BasicDBObject();
								document11.put("requisitions.reqno", reqNoo);
								int d=table.find(document11).count();
								
								
								
/*								BasicDBList bson1 = ( BasicDBList )reqValueObject.get("requisitions");	
								
								//System.out.println(bson1.size());

						for(int k1=0;k1<bson1.size();k1++)
						{	
							
							BasicDBObject bson2=(BasicDBObject)bson1.get(k1);
						*/
							//BasicDBObject bson3=(BasicDBObject)bson1.get("files");
						
							if(d!=0)
							{
								ObjectId id=new ObjectId();
	                              BasicDBList listArrayVal1=new BasicDBList();
								  	
								  	List<BasicDBObject> reqWiseListing =new ArrayList<BasicDBObject>();
								  	
								  	BasicDBObject reqValues11=new BasicDBObject();
								  	
								  	reqValues11.put("filename", filename);
								  	reqValues11.put("filesize",filelength);
								  	reqValues11.put("contenttype",fileextentsion);
								  	reqValues11.put("hospitalcode",_UserVO1.getHospitalCode());
								  	reqValues11.put("seatid",_UserVO1.getSeatId());
								  	reqValues11.put("valid", 1);
								  	reqValues11.put("timedate", new Date());
								  	reqValues11.put("_Id",id);
								  	
								  	//listArrayVal1.add(reqValues11);
									
								  //	BasicDBObject documentinside = new BasicDBObject();
								  	
								  //	documentinside.put("reqno", reqNoo);
									//documentinside.put("files", listArrayVal1);
									
									//reqWiseListing.add(documentinside);
								  	
								
								  	document1.put("requisitions.$.files", reqValues11);
								  	
									BasicDBObject reqValues1222=new BasicDBObject();
								  	reqValues1222.put("$push", document1);
								  	
								  	//listArrayVal1.add(reqValues1222);
									
								  	//document1.put("requisitions", reqValues1222);
								  	
								  	BasicDBObject reqValues12=new BasicDBObject();
								  	reqValues12.put("crno", crNoo);
								  	reqValues12.put("requisitions.reqno", reqNoo);
									
								  	
								  	table.update(reqValues12, reqValues1222);
								  	
								  String  finalfilebname=id+"_"+filename;
								  String base64String=Base64.encodeBytes(filedatabyte);
								  byte[] bytearray = Base64.decode(base64String);
								  if(filedatabyte!=null && filedatabyte.length>0)
									 {
							         MongoXmlHandler.getInstance("cdacpatprofile8").savePDFFile(finalfilebname, bytearray);
							         
									 }
								
								
							}
							else
							{
							
								ObjectId id=new ObjectId();
                              BasicDBList listArrayVal1=new BasicDBList();
							  	
							  	List<BasicDBObject> reqWiseListing =new ArrayList<BasicDBObject>();
							  	
							  	BasicDBObject reqValues11=new BasicDBObject();
							  	
								reqValues11.put("filename", filename);
							  	reqValues11.put("filesize",filelength);
							  	reqValues11.put("contenttype",fileextentsion);
							  	reqValues11.put("hospitalcode",_UserVO1.getHospitalCode());
							  	reqValues11.put("seatid",_UserVO1.getSeatId());
							  	reqValues11.put("valid", 1);
							  	reqValues11.put("timedate", new Date());
							  	reqValues11.put("_Id",id);
							  	
							  	listArrayVal1.add(reqValues11);
								
							  	BasicDBObject documentinside = new BasicDBObject();
							  	
							  	documentinside.put("reqno", reqNoo);
								documentinside.put("files", listArrayVal1);
								
								//reqWiseListing.add(documentinside);
							  	
							
							  	document1.put("requisitions", documentinside);
							  	
								BasicDBObject reqValues1222=new BasicDBObject();
							  	reqValues1222.put("$push", document1);
							  	
							  	//listArrayVal1.add(reqValues1222);
								
							  	//document1.put("requisitions", reqValues1222);
							  	
							  	BasicDBObject reqValues12=new BasicDBObject();
								reqValues12.put("crno", crNoo);
								
							  	
							  	table.update(reqValues12, reqValues1222);
							  	
							  	String  finalfilebname=id+"_"+filename;
							  
							  if(filedatabyte!=null && filedatabyte.length>0)
								 {
						         MongoXmlHandler.getInstance("cdacpatprofile8").savePDFFile(finalfilebname, filedatabyte);
						         
								 }
								
								
							
								
								
							//System.out.println(bson2.get("reqno").equals(reqNoo));
									
								
						}		
								
								
								
							/*DBObject bson1 = ( DBObject )reqValueObject.get("requisitions");	
								 
						 //System.out.println(bson1);
						 
							DBObject reqnoo = ( DBObject )bson1.get(reqNoo);	
							
							if(reqnoo!=null) 
							 {
							
								ObjectId id=new  ObjectId();
								System.out.println(id);
						
								//BasicDBList listArrayVal1=new BasicDBList();
							  	
							  	BasicDBObject reqValues11=new BasicDBObject();
							  	
							  	reqValues11.put("filname", filename);
							  	reqValues11.put("filesize",filelength);
							  	reqValues11.put("fileextension",fileextentsion);
							  	reqValues11.put("hospitalcode",_UserVO1.getHospitalCode());
							  	reqValues11.put("valid", 1);
							  	reqValues11.put("TimeDate", new Date());
							  	reqValues11.put("_Id", id);
							  
							  	BasicDBList listArrayVal=new BasicDBList();
							  	listArrayVal.add(reqValues11);
							  	
							  
							  	BasicDBObject reqValues122=new BasicDBObject();
								reqValues122.put(reqNoo, reqValues11);

								BasicDBObject reqValues1222=new BasicDBObject();
							  	reqValues1222.put("$push", reqValues122);
							  	
							  	//listArrayVal1.add(reqValues1222);
								
							  	//document1.put("requisitions", reqValues1222);
							  	
							  	BasicDBObject reqValues12=new BasicDBObject();
								reqValues12.put("crno", crNoo);
								
							  	
							  	table.update(reqValues12, reqValues1222);
							  	
							  String  finalfilebname=id+filename;
							  
							  if(filedatabyte!=null && filedatabyte.length>0)
								 {
						         MongoXmlHandler.getInstance("externalDocuments").savePDFFile(finalfilebname, filedatabyte);
						         
								 }
							  	
	
								
								System.out.println("exist");
								}
							else
							 {
								
								
								System.out.println("not");}*/
				
							}
							else{
								
								ObjectId id=new ObjectId();
							  	document1.put("crno", crNoo);
							  	
							  	BasicDBList listArrayVal1=new BasicDBList();
							  	
							  	List<BasicDBObject> reqWiseListing =new ArrayList<BasicDBObject>();
							  	
							  	BasicDBObject reqValues11=new BasicDBObject();
							  	
							  	reqValues11.put("filename", filename);
							  	reqValues11.put("filesize",filelength);
							  	reqValues11.put("contenttype",fileextentsion);
							  	reqValues11.put("hospitalcode",_UserVO1.getHospitalCode());
							  	reqValues11.put("seatid",_UserVO1.getSeatId());
							  	reqValues11.put("valid", 1);
							  	reqValues11.put("timedate", new Date());
							  	reqValues11.put("_Id",id);
							  	
							  	listArrayVal1.add(reqValues11);
								
							  	BasicDBObject documentinside = new BasicDBObject();
							  	
							  	documentinside.put("reqno", reqNoo);
								documentinside.put("files", listArrayVal1);
								reqWiseListing.add(documentinside);
							  	
							
							  	document1.put("requisitions", reqWiseListing);
							  	
							  	table.insert(document1);
							  	
							  	String  finalfilebname=id+"_"+filename;
								  
								  if(filedatabyte!=null && filedatabyte.length>0)
									 {
							         MongoXmlHandler.getInstance("cdacpatprofile8").savePDFFile(finalfilebname, filedatabyte);
							         
									 }
								System.out.println("new insert");
							}	
						
						
						
						
						
						
						
						
						
						
						
						//end ch
						
						
						//table.find(reqValues1);
						/* int count = table.find(reqValuess).count();
						 
					if(count!=0)
					{
						
						ObjectId id=new  ObjectId();
						System.out.println(id);
				
						//BasicDBList listArrayVal1=new BasicDBList();
					  	
					  	BasicDBObject reqValues11=new BasicDBObject();
					  	reqValues11.put("reqno", reqNoo);
					  	reqValues11.put("filname", filename);
					  	reqValues11.put("filesize",filelength);
					  	reqValues11.put("fileextension",fileextentsion);
					  	reqValues11.put("hospitalcode",_UserVO1.getHospitalCode());
					  	reqValues11.put("valid", 1);
					  	reqValues11.put("TimeDate", new Date());
					  	reqValues11.put("_Id", id);
					  	
						BasicDBObject reqValues122=new BasicDBObject();
						reqValues122.put("requisitions", reqValues11);

						BasicDBObject reqValues1222=new BasicDBObject();
					  	reqValues1222.put("$push", reqValues122);
					  	
					  	//listArrayVal1.add(reqValues1222);
						
					  	//document1.put("requisitions", reqValues1222);
					  	
					  	BasicDBObject reqValues12=new BasicDBObject();
						reqValues12.put("crno", crNoo);
						
					  	
					  	table.update(reqValues12, reqValues1222);
					  	
					  String  finalfilebname=id+filename;
					  
					  if(filedatabyte!=null && filedatabyte.length>0)
						 {
				         MongoXmlHandler.getInstance("externalDocuments").savePDFFile(finalfilebname, filedatabyte);
				         
						 }
					  	
					}
					else	
					{
				
				ObjectId id=new ObjectId();
			  	document1.put("crno", crNoo);
			  	
			  	BasicDBList listArrayVal1=new BasicDBList();
			  	
			  	BasicDBObject reqValues11=new BasicDBObject();
			  	reqValues11.put("reqno", reqNoo);
			  	reqValues11.put("filname", filename);
			  	reqValues11.put("filesize",filelength);
			  	reqValues11.put("fileextension",fileextentsion);
			  	reqValues11.put("hospitalcode",_UserVO1.getHospitalCode());
			  	reqValues11.put("valid", 1);
			  	reqValues11.put("TimeDate", new Date());
			  	reqValues11.put("_Id",id);
			  	
			  	listArrayVal1.add(reqValues11);
				
			  	document1.put("requisitions", listArrayVal1);
				 
			  	table.insert(document1);
			  	
			  	String  finalfilebname=id+filename;
				  
				  if(filedatabyte!=null && filedatabyte.length>0)
					 {
			         MongoXmlHandler.getInstance("externalDocuments").savePDFFile(finalfilebname, filedatabyte);
			         
					 }
				  
					}*/
			// }
				 }
			
			 }
	
			
			
			
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			Map<String, List<ResultEntryVO>> dynamicReqNoList = (Map<String, List<ResultEntryVO>>) session.getAttribute("dynamicTemplateList");
			UserVO _userVO=getUserVO(_request);

             String requisitionDNo="";

			//		mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO, lstInvResultEntryTemplateVO);



			///////////////////////////////////////////////////////////template session received////////////////////////
			List<ResultEntryVO> lstInvResultEntryTemplateVO=null;
			lstInvResultEntryTemplateVO=(ArrayList)_request.getSession().getAttribute(InvestigationConfig.LIST_RESULT_ENTRY);
			int tempLength=lstInvResultEntryTemplateVO.size();
			/////////////////////////////////////////////////////////template session code ends//////////////////////////



			List<ResultEntryVO> invResultEntryVO=new ArrayList<ResultEntryVO>();
			List<ResultEntryVO> invResultEntryForParameterDtlVO=new ArrayList<ResultEntryVO>();


			String[] selectedLabTestCodeArray=_fb.getChkResultEntryPatient();
			
			
			String[] selectedFbValue=_fb.getResultEntryTemplateValueWithHash().split("@");
			
			int ResultEntryTemplateValueWithHash=selectedFbValue.length;// getting Fb Values For Selected Check Box 

			int labRowCount=selectedLabTestCodeArray.length;
System.out.println("lab row count is : " +labRowCount );
			for(int i=0;i<labRowCount;i++)
			{


				ResultEntryVO voResultEntry=new ResultEntryVO();
				//	Getting chkBoxValues from split array

				//invresultentryvo.getPatPuk()+"#"+invresultentryvo.getPatReqNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()
				//+"#"+invresultentryvo.getSampleCode()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+
				//"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()

				String[] splittedTests = null;
				if(selectedLabTestCodeArray[i].contains("!"))
				{
					splittedTests = selectedLabTestCodeArray[i].split("!");
				}

				String patReqNo = null;
				if(splittedTests != null && splittedTests.length > 0)
				{
					for(String temp: splittedTests)
					{
						ResultEntryVO voResultEntry1=new ResultEntryVO();
						String[] testCodeArray=temp.split("#");

						String CrNo=testCodeArray[0];
						patReqNo=testCodeArray[1];
						 requisitionDNo=testCodeArray[2];

						String testCode=testCodeArray[3];
						String sampleNo=testCodeArray[4];
						String labCode=testCodeArray[5];
						String patAge=testCodeArray[6];
						String patGender=testCodeArray[7];
						String reportAvailableAfter=testCodeArray[8];
						String patVisitDat=testCodeArray[9];
						String patVisitNo=testCodeArray[10];
						String labNo=testCodeArray[11];
						String episodeCode=testCodeArray[12];
						String deptCode=testCodeArray[13];
						String deptUnitCode=testCodeArray[14];
						String reqType=testCodeArray[15];
						String testName=testCodeArray[17];
						String labName=testCodeArray[18];
						String sampleName=testCodeArray[19];
						String tempSampleNo=testCodeArray[20];											
						String groupCode = testCodeArray[21];
						String isPrintedWithDynamicGroup = testCodeArray[22];
						String commentsboxxval = testCodeArray[30];
						String visitReason= testCodeArray[31];
						//String visitReason= _fb.getVisitReason(); 
						
						voResultEntry1.setPatCRNo(CrNo);
						voResultEntry1.setRequisitionNo(patReqNo);
						voResultEntry1.setRequisitionDNo(requisitionDNo);
						voResultEntry1.setTestCode(testCode);
						voResultEntry1.setParameterRequisitionDNo(requisitionDNo);
						voResultEntry1.setSampleNo(sampleNo);
						voResultEntry1.setLabCode(labCode);
						voResultEntry1.setPatAge(patAge);
						voResultEntry1.setPatGender(patGender);
						voResultEntry1.setReportAvailableAfter(reportAvailableAfter);
						// getting Fb Values For Selected Check Box 
						voResultEntry1.setPatVisitDat(patVisitDat);
						voResultEntry1.setPatVisitNo(patVisitNo);
						voResultEntry1.setLabNo(labNo);
						voResultEntry1.setEpisodeCode(episodeCode);
						voResultEntry1.setDepartmentcode(deptCode);
						voResultEntry1.setPatdeptunitcode(deptUnitCode);
						voResultEntry1.setRequisitionTypeCode(reqType);
						voResultEntry1.setPatLabName(labName);
						voResultEntry1.setTestName(testName);
						voResultEntry1.setSampleName(sampleName);
						voResultEntry1.setTempSampleNo(tempSampleNo);
						voResultEntry1.setDynamicTemplatePrintedGroup(isPrintedWithDynamicGroup);
						voResultEntry1.setGroupCode(groupCode);
						voResultEntry1.setRemarkvaluess(commentsboxxval);
						/* Added by Prashant */
						voResultEntry1.setVisitReason(visitReason);
						//added by krishnan nema on 28/01/2018 for save to draft
						if(_fb.getIsSaveToDraft()!=null && _fb.getIsSaveToDraft().equals("1")){
							voResultEntry1.setIsSaveToDraft(_fb.getIsSaveToDraft());
						}
						invResultEntryVO.add(voResultEntry1);
					}
				}
				else {
					voResultEntry = new ResultEntryVO();
					String[] testCodeArray=selectedLabTestCodeArray[i].split("#");

					String CrNo=testCodeArray[0];
					patReqNo=testCodeArray[1];
					 requisitionDNo=testCodeArray[2];

					String testCode=testCodeArray[3];
					String sampleNo=testCodeArray[4];
					String labCode=testCodeArray[5];
					String patAge=testCodeArray[6];
					String patGender=testCodeArray[7];
					String reportAvailableAfter=testCodeArray[8];
					String patVisitDat=testCodeArray[9];
					String patVisitNo=testCodeArray[10];
					String labNo=testCodeArray[11];
					String episodeCode=testCodeArray[12];
					String deptCode=testCodeArray[13];
					String deptUnitCode=testCodeArray[14];
					String reqType=testCodeArray[15];
					String testName=testCodeArray[17];
					String labName=testCodeArray[18];
					String sampleName=testCodeArray[19];
					String tempSampleNo=testCodeArray[20];											
					String groupCode = testCodeArray[21];
					String isPrintedWithDynamicGroup = testCodeArray[22];
					String empCode = testCodeArray[27];
					String commentsboxxval = testCodeArray[30];
					String visitReason= testCodeArray[31];
					//String visitReason= _fb.getVisitReason();
					
					voResultEntry.setPatCRNo(CrNo);
					voResultEntry.setRequisitionNo(patReqNo);
					voResultEntry.setRequisitionDNo(requisitionDNo);
					voResultEntry.setTestCode(testCode);
					voResultEntry.setParameterRequisitionDNo(requisitionDNo);
					voResultEntry.setSampleNo(sampleNo);
					voResultEntry.setLabCode(labCode);
					voResultEntry.setPatAge(patAge);
					voResultEntry.setPatGender(patGender);
					voResultEntry.setReportAvailableAfter(reportAvailableAfter);
					// getting Fb Values For Selected Check Box 
					voResultEntry.setPatVisitDat(patVisitDat);
					voResultEntry.setPatVisitNo(patVisitNo);
					voResultEntry.setLabNo(labNo);
					voResultEntry.setEpisodeCode(episodeCode);
					voResultEntry.setDepartmentcode(deptCode);
					voResultEntry.setPatdeptunitcode(deptUnitCode);
					voResultEntry.setRequisitionTypeCode(reqType);
					voResultEntry.setPatLabName(labName);
					voResultEntry.setTestName(testName);
					voResultEntry.setSampleName(sampleName);
					voResultEntry.setTempSampleNo(tempSampleNo);
					voResultEntry.setDynamicTemplatePrintedGroup(isPrintedWithDynamicGroup);
					voResultEntry.setGroupCode(groupCode);
					voResultEntry.setRemarkvaluess(commentsboxxval);
					/* Added by Prashant */
					voResultEntry.setVisitReason(visitReason);
					//added by krishnan nema on 28/01/2018 for save to draft
					
					voResultEntry.setCrNoReqNoString(_fb.getCrNoReqNoString());
					voResultEntry.setFinalRemarkValue(_fb.getFinalRemarksValue());
					voResultEntry.setEmpNameWise(_fb.getEmpNameWise());
					
					if(_fb.getIsSaveToDraft()!=null && _fb.getIsSaveToDraft().equals("1")){
						voResultEntry.setIsSaveToDraft(_fb.getIsSaveToDraft());
					}
					//voResultEntry.setEmpNameWise(empCode);
					invResultEntryVO.add(voResultEntry);
				}

				
				System.out.println(" temp length is : "+ tempLength);
				/////////////////////////////////////checking if req no matches with template received value////////////
				HashMap<String, String> mpLoinc = new HashMap<String, String>();
				for(int len=0;len<tempLength;len++)
					if(requisitionDNo.equals(lstInvResultEntryTemplateVO.get(len).getRequisitionDNo()))
					{	
						ResultEntryVO rvo= lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy().get(0);
						mpParaLoinc=lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy().get(0).getParaLoinc();
						if(rvo.getDynamnicTemplateResultEntryGroup() != null && 
								rvo.getDynamnicTemplateResultEntryGroup().equals("1") && !mpLoinc.containsKey(rvo.getRequisitionNo()+rvo.getGroupCode())
								&& mpParaLoinc != null)
						{
							newmaplist.add(mpParaLoinc);
							mpLoinc.put(rvo.getRequisitionNo()+rvo.getGroupCode(), "1");
						}
						else if (rvo.getDynamnicTemplateResultEntryGroup() != null && 
								!rvo.getDynamnicTemplateResultEntryGroup().equals("1")) {
							newmaplist.add(mpParaLoinc);
						}
						else if(rvo.getDynamnicTemplateResultEntryGroup() == null)
						{
							newmaplist.add(mpParaLoinc);
						}
						//		System.out.println("Siddharth: InvResultEntryUTIL: The map size mpParaLoinc is " + mpParaLoinc.size());
						//mprefUOM=lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy().get(0).getRefUOM();
					}
				////////////////////////////////////end checking req no with template received//////////////////////////
				System.out.println("Siddharth: InvResultEntryUTIL: The newmaplist size is: " + newmaplist.size());


			}// end for loop
                

			for(int j=0,i=0;j<ResultEntryTemplateValueWithHash;j++)
			{
				ResultEntryVO voResultEntryForParaMeterDtl=new ResultEntryVO();

				String[] temaplateValue=selectedFbValue[j].split("#");
                 System.out.println("len:"+selectedFbValue[j].split("#").length);
				String parameterRequisitionDNo=temaplateValue[0];
				
				
				String splitedValue=temaplateValue[3];
				String testParaMeterCode="";
				String parantParameterCode="";
				String testCode="";
				String CrNO=temaplateValue[5];
				String reqNo=temaplateValue[6];
				String labCode=temaplateValue[10];
				String sampleCode=temaplateValue[9];
				String Age=temaplateValue[11];
				String gender=temaplateValue[12];
				String reportValidationAfter=temaplateValue[13];
				String uomCode=temaplateValue[21];
				/*String isHyperLink=temaplateValue[32];
				String parameterOrder=temaplateValue[33];*/
				String isHyperLink=temaplateValue[37];
				String parameterOrder=temaplateValue[38];
				if(splitedValue.length()==5)
				{
					testCode=splitedValue.substring(0, 5);
				}
				else if(splitedValue.length()>9)
				{
					//					  testCode=splitedValue.substring(0, 5);
					//					  testParaMeterCode=splitedValue.substring(5, 9);
					//					  parantParameterCode=splitedValue.substring(9, 18);
					testCode=splitedValue.substring(splitedValue.length()-9, splitedValue.length()-4);
					testParaMeterCode=splitedValue.substring(splitedValue.length()-4, splitedValue.length());
					parantParameterCode=splitedValue.substring(0, splitedValue.length() -9);					   					  
				}
				else
				{
					testCode=splitedValue.substring(0, 5);
					testParaMeterCode=splitedValue.substring(5, 9);
					parantParameterCode=splitedValue;
				}

				String resultEntryValue=temaplateValue[4];
				System.out.println("Siddharth: Before SizeMap One");
				if( SizeMapOne==0 && newmaplist.size()>=j) //by chandan as some template not open in productn
				{
					System.out.println("Siddharth: Before SizeMap One"+i);

					fetchMap=newmaplist.get(i);
					i++;
					SizeMapOne=fetchMap.size();
					SizeMapOne=SizeMapOne/3;
				}
				//	String loinc=fetchMap.get(testParaMeterCode);
				String loinc=fetchMap.get(splitedValue);
				//	String refRange=fetchMap.get(testParaMeterCode+"refrange");
				String refRange=fetchMap.get(splitedValue+"refrange");
				SizeMapOne--;
				//String ref=mprefUOM.get(parantParameterCode);


				//fetch the loinc code
				/*	String passValue=testCode+'#'+testParaMeterCode+'#'+sampleCode+'#'+uomCode;
					String loincCode=InvResultEntryDATA.getLoincCode(passValue);
					voResultEntryForParaMeterDtl.setLoincCode(loincCode);*/

				//	voResultEntryForParaMeterDtl.setRefRange(ref);
				voResultEntryForParaMeterDtl.setLoincCode(loinc);
				voResultEntryForParaMeterDtl.setStrRefRange(refRange);
				
				String html=	createhtmlforecho(_fb.getEchovar(),parameterRequisitionDNo);
                             if(!html.equals(""))
                             {
                            	 resultEntryValue=html;
                            	 isHyperLink="-";
                             }
			
                  String echodata= _fb.getEchovar();
                
                  String finalechoval="";
                
                  if(echodata!=null && !echodata.equals(""))
                  {
                	  
          			String echoval[]=echodata.split("\\*\\*\\*");
          			for(int l=0;l<echoval.length;l++)
        			{
        				
        				if(echoval[l].contains(parameterRequisitionDNo))
        				{
        					
        					finalechoval=echoval[l];
        				}
        				
        			}
          			
                  }
                  
  				voResultEntryForParaMeterDtl.setFinalechovalue(finalechoval);

				voResultEntryForParaMeterDtl.setResultEntryValue(resultEntryValue);
				voResultEntryForParaMeterDtl.setTestParaMeterCode(testParaMeterCode);
				voResultEntryForParaMeterDtl.setTestCode(testCode);
				voResultEntryForParaMeterDtl.setParantParaCode(parantParameterCode);
				voResultEntryForParaMeterDtl.setParameterRequisitionDNo(parameterRequisitionDNo);
				voResultEntryForParaMeterDtl.setPatCRNo(CrNO);
				voResultEntryForParaMeterDtl.setRequisitionNo(reqNo);
				voResultEntryForParaMeterDtl.setLabCode(labCode);
				voResultEntryForParaMeterDtl.setSampleCode(sampleCode);
				voResultEntryForParaMeterDtl.setPatAge(Age);
				voResultEntryForParaMeterDtl.setPatGender(gender);
				voResultEntryForParaMeterDtl.setReportAvailableAfter(reportValidationAfter);
				voResultEntryForParaMeterDtl.setIsHyperLink(isHyperLink);
				voResultEntryForParaMeterDtl.setParameterOrder(parameterOrder);
				invResultEntryForParameterDtlVO.add(voResultEntryForParaMeterDtl);
				
				
			}


System.out.println("total values are:  "+invResultEntryForParameterDtlVO.size());

if(_fb.getCrNoReqNoString()!=null)
if(invResultEntryVO!=null)
	{
	invResultEntryVO.get(0).setCrNoReqNoString(_fb.getCrNoReqNoString());
	invResultEntryVO.get(0).setFinalRemarkValue(_fb.getFinalRemarksValue());
	invResultEntryVO.get(0).setEmpNameWise(_fb.getEmpNameWise());

	
	}

          
			mp=InvResultEntryDATA.saveResultEntryDetails(invResultEntryVO,invResultEntryForParameterDtlVO, _userVO, _request,_fb);
			List lstResultEntry=(List)mp.get(InvestigationConfig.LIST_RESULT_ENTRY_STATUS);
			StringBuilder str = new StringBuilder();	

			//if(lstResultEntry!=null&&lstResultEntry.size()>0)
			// {
			//for(int i=0;i<lstResultEntry.size();i++)
			//		{
			//		str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
			//					+ " " + "</font>");
			//		str.append( "<br><table border='1' width='50%'> ");
			//		str.append( "<td width='25%' align='left' >");
			//		str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
			//		str.append(( (String)lstResultEntry.get(i))+ "</font>" + "</td></tr></table>");
			//	}
			//}
			objStatus.add(Status.DONE, str.toString(),
					"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ "Result Entry Successful" + "</font>");



			objStatus.add(Status.NEW, "", "");
			objStatus.add(Status.TRANSINPROCESS, "", "");

		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			System.out.println(e.getMessage());
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			objStatus.add(Status.ERROR, "", " Byte Array Error");
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}




	public static StringBuffer showCannedDetails(InvResultEntryFB fb,  HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);




			String	  labCode=fb.getCannedLabCode();
			String	  cannedMacroCheck=fb.getCannedOrMacroCheck();


			/*if(fb.getCheckProcessFb().equals("InvResultEntryFB"))
				 {
					   labCode=fb.getCannedLabCode();
					   cannedMacroCheck=fb.getCannedOrMacroCheck();
				 }*/
			//objRequest_p.getSession().setAttribute("a",strTestCombo);

			//strTestCombo=InvestigationRaisingDtlDATA.getTestComboAJAX(labCode,userVO);
			//session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			mp=InvResultEntryDATA.showCannedDetails(labCode,cannedMacroCheck ,userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);
			String lstSampleAccepted=(String)mp.get(InvestigationConfig.LIST_CANNED_FILE_DETAILS);
			sbAjaxRes.append(lstSampleAccepted);

		}
		catch (Exception e)
		{
			strMsgText = "ResultEntryUtil.showCannedDetails() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return sbAjaxRes;
	}







	public static boolean getEntryTypeDetails(InvResultEntryFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		ResultEntryVO invresultentryvo = new ResultEntryVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			invresultentryvo.setFromDate(fb.getFromDate()); 
			invresultentryvo.setToDate(fb.getToDate());
			invresultentryvo.setFromCollDate(fb.getFromCollDate());
			invresultentryvo.setToCollDate(fb.getToCollDate());
			invresultentryvo.setPatCRNo(fb.getPatCRNo()); 
			invresultentryvo.setLabCode(fb.getLabCode());
			invresultentryvo.setNewEntry(fb.getNewEntry());
			invresultentryvo.setSearchBy(fb.getSearchBy());
			invresultentryvo.setSampleAreaCode(fb.getSampleAreaCode());
			mp=InvResultEntryDATA.setPatientResultEntryEssentials(invresultentryvo, userVO); 
			WebUTIL.setMapInSession(mp, _request);



			//HelperMethods.populate(fb, invresultentryvo);

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


	public static void modifyResultEntryPatientDetails(InvResultEntryFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		Map mp=new HashMap();
		int SizeMapOne=0;
		HashMap<String,String> mpParaLoinc=new HashMap();
		HashMap<String,String> fetchValue=new HashMap();
		List<HashMap> newmaplist= new ArrayList<HashMap>();
		
		String requisitionDNo="";
		String refRange="";
	    String deptunitcode="";
	    String deptcode="";
		String patName="";
		String CrNo="";
		String testCode="";
		String sampleNo="";
		String labCode="";
		String patAge="";
		String patGender="";
		String reportAvailableAfter="";
		String patVisitDat="";
		String patVisitNo="";
		String labNo="";
		String episodeCode="";
		String deptCode="";
		String deptUnitCode="";
		String reqType="";
		String testName="";
		String labName="";
		String sampleName="";
		String tempSampleNo="";
		String groupCode="";
		String patUnitName="";
		String visitReason;
		_request.removeAttribute("getuploadedfiledata");

	
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO=getUserVO(_request);



			//		mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO, lstInvResultEntryTemplateVO);


			

              Map<String,String> mpp=new HashMap<String,String>();      
			
			String dataforuploadfiles=_fb.getFileuploaddatabase64();
			
			if(dataforuploadfiles!=null && !dataforuploadfiles.equals("") && dataforuploadfiles.contains("#@#@"))
			{
				String data[]=dataforuploadfiles.split("#@#@");
				
				for(int k=0;k<data.length;k++)
				{
					String datas=data[k];
					
					String datass[]=datas.split("@@");
				
					String reqdno=datass[0];
				String filename=datass[1];
				String content=datass[2];
				String testparacode=datass[3];

				mpp.put(reqdno+testparacode, filename+"@@"+content);
				}
				
			}
			else if(dataforuploadfiles!=null && !dataforuploadfiles.equals("") )
			{
	            String data[]=dataforuploadfiles.split("@@");
				
				String reqdno=data[0];
				String filename=data[1];
				String content=data[2];
				String testparacode=data[3];

				mpp.put(reqdno+testparacode, filename+"@@"+content);
			
				
			}
			
			        _request.setAttribute("getuploadedfiledata", mpp);
					
			        

			///////////////////////////////////////////////////////////template session received////////////////////////
			List<ResultEntryVO> lstInvResultEntryTemplateVO=null;
			lstInvResultEntryTemplateVO=(ArrayList)_request.getSession().getAttribute(InvestigationConfig.LIST_RESULT_ENTRY);
			int tempLength=lstInvResultEntryTemplateVO.size();
			/////////////////////////////////////////////////////////template session code ends//////////////////////////



			List<ResultEntryVO> invResultEntryVO=new ArrayList<ResultEntryVO>();
			List<ResultEntryVO> invResultEntryForParameterDtlVO=new ArrayList<ResultEntryVO>();


			String[] selectedLabTestCodeArray=_fb.getChkResultEntryPatient();


			String[] selectedFbValue=_fb.getResultEntryTemplateValueWithHash().split("@");

			int ResultEntryTemplateValueWithHash=selectedFbValue.length;// getting Fb Values For Selected Check Box 

			int labRowCount=selectedLabTestCodeArray.length;

			for(int i=0;i<labRowCount;i++)
			{

				ResultEntryVO voResultEntry=new ResultEntryVO();
				//	Getting chkBoxValues from split array

				//invresultentryvo.getPatPuk()+"#"+invresultentryvo.getPatReqNo()+"#"+invresultentryvo.getRequisitionDNo()+"#"+invresultentryvo.getTestCode()
				//+"#"+invresultentryvo.getSampleCode()+"#"+invresultentryvo.getLabCode()+"#"+invresultentryvo.getPatAge()+
				//"#"+invresultentryvo.getPatGender()+"#"+invresultentryvo.getReportAvailableAfter()

				String[] splittedTests = null;
				if(selectedLabTestCodeArray[i].contains("!"))
				{
					splittedTests = selectedLabTestCodeArray[i].split("!");
				}

				String patReqNo = null;
				if(splittedTests != null && splittedTests.length > 0)
				{
					for(String temp: splittedTests)
					{
						ResultEntryVO voResultEntry1=new ResultEntryVO();
						String[] testCodeArray=temp.split("#");

					

				 CrNo=testCodeArray[0];
				patReqNo=testCodeArray[1];
			 requisitionDNo=testCodeArray[2];

				 testCode=testCodeArray[3];
				 sampleNo=testCodeArray[4];
				 labCode=testCodeArray[5];
				 patAge=testCodeArray[6];
				 patGender=testCodeArray[7];
				 reportAvailableAfter=testCodeArray[8];
				 patVisitDat=testCodeArray[9];
				 patVisitNo=testCodeArray[10];
				 labNo=testCodeArray[11];
				 episodeCode=testCodeArray[12];
				 deptCode=testCodeArray[13];
				 deptUnitCode=testCodeArray[14];
				 reqType=testCodeArray[15];
				 testName=testCodeArray[17];
				 labName=testCodeArray[18];
				 sampleName=testCodeArray[19];
				 tempSampleNo=testCodeArray[20];
				 groupCode = testCodeArray[21];
				String isPrintedWithDynamicGroup = testCodeArray[22];
				String commentsboxxval = testCodeArray[28];
				visitReason= testCodeArray[29];
				//visitReason=_fb.getVisitReason();


			



				voResultEntry1.setPatCRNo(CrNo);
				voResultEntry1.setRequisitionNo(patReqNo);
				voResultEntry1.setRequisitionDNo(requisitionDNo);
				voResultEntry1.setTestCode(testCode);
				voResultEntry1.setParameterRequisitionDNo(requisitionDNo);
				voResultEntry1.setSampleNo(sampleNo);
				voResultEntry1.setLabCode(labCode);
				voResultEntry1.setPatAge(patAge);
				voResultEntry1.setPatGender(patGender);
				voResultEntry1.setReportAvailableAfter(reportAvailableAfter);
				// getting Fb Values For Selected Check Box 
				voResultEntry1.setPatVisitDat(patVisitDat);
				voResultEntry1.setPatVisitNo(patVisitNo);
				voResultEntry1.setLabNo(labNo);
				voResultEntry1.setEpisodeCode(episodeCode);
				voResultEntry1.setDepartmentcode(deptCode);
				voResultEntry1.setPatdeptunitcode(deptUnitCode);
				voResultEntry1.setRequisitionTypeCode(reqType);
				voResultEntry1.setPatLabName(labName);
				voResultEntry1.setTestName(testName);
				voResultEntry1.setSampleName(sampleName);
				voResultEntry1.setTempSampleNo(tempSampleNo);
				voResultEntry1.setDynamicTemplatePrintedGroup(isPrintedWithDynamicGroup);
				voResultEntry1.setGroupCode(groupCode);
				voResultEntry1.setRemarkvaluess(commentsboxxval);
				/* Added by Prashant */
				voResultEntry1.setVisitReason(visitReason);
				
				
				
				
				voResultEntry1.setCrNoReqNoString(_fb.getCrNoReqNoString());
				voResultEntry1.setFinalRemarkValue(_fb.getFinalRemarksValue());
				voResultEntry1.setEmpNameWise(_fb.getEmpNameWise());
				//updated by krishnan nema on 29/01/2018 for save to draft changes
				if(_fb.getIsSaveToDraft()!=null && _fb.getIsSaveToDraft().equals("1")){
					voResultEntry1.setIsSaveToDraft(_fb.getIsSaveToDraft());
				}
				/*	 voResultEntry.setParaLoinc(mpParaLoinc);*/
				invResultEntryVO.add(voResultEntry1);
				
					}
				}
					else
					{
						String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
						ResultEntryVO voResultEntry1=new ResultEntryVO();
				
						 CrNo=testCodeArray[0];
						patReqNo=testCodeArray[1];
						requisitionDNo=testCodeArray[2];

						 testCode=testCodeArray[3];
						 sampleNo=testCodeArray[4];
						 labCode=testCodeArray[5];
						 patAge=testCodeArray[6];
						 patGender=testCodeArray[7];
						 reportAvailableAfter=testCodeArray[8];
						 patVisitDat=testCodeArray[9];
						 patVisitNo=testCodeArray[10];
						 labNo=testCodeArray[11];
						 episodeCode=testCodeArray[12];
						 deptCode=testCodeArray[13];
						 deptUnitCode=testCodeArray[14];
						 reqType=testCodeArray[15];
						 testName=testCodeArray[17];
						 labName=testCodeArray[18];
						 sampleName=testCodeArray[19];
						 tempSampleNo=testCodeArray[20];
						 groupCode = testCodeArray[21];
						String isPrintedWithDynamicGroup = testCodeArray[22];
						patName=testCodeArray[23];
						refRange=testCodeArray[24];
						deptunitcode=testCodeArray[25];
						patUnitName=testCodeArray[26];
				    	
						String commentsboxxval = testCodeArray[28];
						visitReason= testCodeArray[29];
						//visitReason=_fb.getVisitReason();



					



						voResultEntry1.setPatCRNo(CrNo);
						voResultEntry1.setRequisitionNo(patReqNo);
						voResultEntry1.setRequisitionDNo(requisitionDNo);
						voResultEntry1.setTestCode(testCode);
						voResultEntry1.setParameterRequisitionDNo(requisitionDNo);
						voResultEntry1.setSampleNo(sampleNo);
						voResultEntry1.setLabCode(labCode);
						voResultEntry1.setPatAge(patAge);
						voResultEntry1.setPatGender(patGender);
						voResultEntry1.setReportAvailableAfter(reportAvailableAfter);
						// getting Fb Values For Selected Check Box 
						voResultEntry1.setPatVisitDat(patVisitDat);
						voResultEntry1.setPatVisitNo(patVisitNo);
						voResultEntry1.setLabNo(labNo);
						voResultEntry1.setEpisodeCode(episodeCode);
						voResultEntry1.setDepartmentcode(deptCode);
						voResultEntry1.setPatdeptunitcode(deptUnitCode);
						voResultEntry1.setRequisitionTypeCode(reqType);
						voResultEntry1.setPatLabName(labName);
						voResultEntry1.setTestName(testName);
						voResultEntry1.setSampleName(sampleName);
						voResultEntry1.setTempSampleNo(tempSampleNo);
						voResultEntry1.setDynamicTemplatePrintedGroup(isPrintedWithDynamicGroup);
						voResultEntry1.setGroupCode(groupCode);
						voResultEntry1.setRemarkvaluess(commentsboxxval);
						/* Added by Prashant */
						voResultEntry1.setVisitReason(visitReason);
						//updated by krishnan nema on 29/01/2018 for save to draft changes
						if(_fb.getIsSaveToDraft()!=null && _fb.getIsSaveToDraft().equals("1")){
							voResultEntry1.setIsSaveToDraft(_fb.getIsSaveToDraft());
						}
						
						voResultEntry1.setCrNoReqNoString(_fb.getCrNoReqNoString());
						voResultEntry1.setFinalRemarkValue(_fb.getFinalRemarksValue());
						voResultEntry1.setEmpNameWise(_fb.getEmpNameWise());
						/*	 voResultEntry.setParaLoinc(mpParaLoinc);*/
						invResultEntryVO.add(voResultEntry1);
					
					
					}
					
				
				
				
				
				/////////////////////////////////////checking if req no matches with template received value////////////
				HashMap<String, String> mpLoinc = new HashMap<String, String>();
				for(int len=0;len<tempLength;len++)
					if(requisitionDNo.equals(lstInvResultEntryTemplateVO.get(len).getRequisitionDNo()))
					{	
						ResultEntryVO rvo= lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy().get(0);
						mpParaLoinc=lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy().get(0).getParaLoinc();
					
						if(rvo.getDynamnicTemplateResultEntryGroup() != null && 
								rvo.getDynamnicTemplateResultEntryGroup().equals("1") && !mpLoinc.containsKey(rvo.getRequisitionNo()+rvo.getGroupCode())
								&& mpParaLoinc != null)
						{
							newmaplist.add(mpParaLoinc);
							mpLoinc.put(rvo.getRequisitionNo()+rvo.getGroupCode(), "1");
						}
						else if (rvo.getDynamnicTemplateResultEntryGroup() != null && 
								!rvo.getDynamnicTemplateResultEntryGroup().equals("1")) {
							newmaplist.add(mpParaLoinc);
						}
						else if(rvo.getDynamnicTemplateResultEntryGroup() == null)
						{
							newmaplist.add(mpParaLoinc);
						}
								//		System.out.println("Siddharth: InvResultEntryUTIL: The map size mpParaLoinc is " + mpParaLoinc.size());
								//mprefUOM=lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy().get(0).getRefUOM();
					}
				////////////////////////////////////end checking req no with template received//////////////////////////

			}// end for loop


			for(int j=0,i=0;j<ResultEntryTemplateValueWithHash;j++)
			{
				ResultEntryVO voResultEntryForParaMeterDtl=new ResultEntryVO();

				String[] temaplateValue=selectedFbValue[j].split("#");

				String parameterRequisitionDNo=temaplateValue[0];
				String splitedValue=temaplateValue[3];
				String testParaMeterCode="";
				String parantParameterCode="";
				 testCode="";
				String CrNO=temaplateValue[5];
				String reqNo=temaplateValue[6];
				 labCode=temaplateValue[10];
				String sampleCode=temaplateValue[9];
				String Age=temaplateValue[11];
				String gender=temaplateValue[12];
				String reportValidationAfter=temaplateValue[13];
				String uomCode=temaplateValue[21];
				String testnamee=temaplateValue[22];
				String isHyperLink=temaplateValue[35];
				if(splitedValue.length()>9)
				{

					testCode=splitedValue.substring(splitedValue.length()-9, splitedValue.length()-4);
					testParaMeterCode=splitedValue.substring(splitedValue.length()-4, splitedValue.length());
					parantParameterCode=splitedValue.substring(0, splitedValue.length() -9);


				}
				else
				{
					testCode=splitedValue.substring(0, 5);
					testParaMeterCode=splitedValue.substring(5, 9);
					parantParameterCode=splitedValue;

				}

				String resultEntryValue=temaplateValue[4];
				/*mpParaLoinc=invResultEntryVO.get(j).getParaLoinc();*/


				if(SizeMapOne==0)
				{fetchValue=newmaplist.get(i);
				i++;
				SizeMapOne=fetchValue.size();
				SizeMapOne=SizeMapOne/3;
				}
				String loinc=fetchValue.get(parantParameterCode);
				 refRange=fetchValue.get(parantParameterCode+"refrange");
				String previousValue=fetchValue.get(parantParameterCode+"previousValue");
				//String ref=mprefUOM.get(parantParameterCode);

				SizeMapOne--;

				voResultEntryForParaMeterDtl.setLoincCode(loinc);
				voResultEntryForParaMeterDtl.setStrRefRange(refRange);
				voResultEntryForParaMeterDtl.setPreviousValue(previousValue);
				
				
				String html=	createhtmlforecho(_fb.getEchovar(),parameterRequisitionDNo);
                if(!html.equals(""))
                {
               	 resultEntryValue=html;
               	 isHyperLink="-";
                }
                
                String echodata= _fb.getEchovar();
                
                String finalechoval="";
              
                if(echodata!=null && !echodata.equals(""))
                {
              	  
        			String echoval[]=echodata.split("\\*\\*\\*");
        			for(int l=0;l<echoval.length;l++)
      			{
      				
      				if(echoval[l].contains(parameterRequisitionDNo))
      				{
      					
      					finalechoval=echoval[l];
      				}
      				
      			}
        			
                }
				
				voResultEntryForParaMeterDtl.setFinalechovalue(finalechoval);

				
				voResultEntryForParaMeterDtl.setResultEntryValue(resultEntryValue);
				voResultEntryForParaMeterDtl.setTestParaMeterCode(testParaMeterCode);
				voResultEntryForParaMeterDtl.setTestCode(testCode);
				voResultEntryForParaMeterDtl.setParantParaCode(parantParameterCode);
				voResultEntryForParaMeterDtl.setParameterRequisitionDNo(parameterRequisitionDNo);
				voResultEntryForParaMeterDtl.setPatCRNo(CrNO);
				voResultEntryForParaMeterDtl.setRequisitionNo(reqNo);
				voResultEntryForParaMeterDtl.setLabCode(labCode);
				voResultEntryForParaMeterDtl.setSampleCode(sampleCode);
			//	voResultEntryForParaMeterDtl.setPatAge(Age);
			//	voResultEntryForParaMeterDtl.setPatGender(gender);
				voResultEntryForParaMeterDtl.setReportAvailableAfter(reportValidationAfter);
				
				
				voResultEntryForParaMeterDtl.setLabCode(labCode);
				voResultEntryForParaMeterDtl.setPatAge(patAge);
				voResultEntryForParaMeterDtl.setPatGender(patGender);
				voResultEntryForParaMeterDtl.setSampleNo(sampleNo);
				voResultEntryForParaMeterDtl.setLabNo(labNo);
				voResultEntryForParaMeterDtl.setTestName(testName);
				voResultEntryForParaMeterDtl.setPatLabName(labName);
				voResultEntryForParaMeterDtl.setPatName(patName);
				voResultEntryForParaMeterDtl.setRefRange(refRange);
				voResultEntryForParaMeterDtl.setDetpUnitCode(deptunitcode);
				voResultEntryForParaMeterDtl.setDepartmentcode(deptCode);
				voResultEntryForParaMeterDtl.setRequisitionTypeCode(reqType);
				voResultEntryForParaMeterDtl.setPatUnitName(patUnitName);
				voResultEntryForParaMeterDtl.setTempSampleNo(tempSampleNo);
				voResultEntryForParaMeterDtl.setSampleName(sampleName);
				voResultEntryForParaMeterDtl.setTestName(testnamee);
				voResultEntryForParaMeterDtl.setIsHyperLink(isHyperLink);
				invResultEntryForParameterDtlVO.add(voResultEntryForParaMeterDtl);
			}


			if(_fb.getCrNoReqNoString()!=null)
				if(invResultEntryVO!=null)
					{
					invResultEntryVO.get(0).setCrNoReqNoString(_fb.getCrNoReqNoString());
					invResultEntryVO.get(0).setFinalRemarkValue(_fb.getFinalRemarksValue());
					}

			mp=InvResultEntryDATA.modifyResultEntryDetails(invResultEntryVO,invResultEntryForParameterDtlVO, _userVO, _request,_fb);
			List lstResultEntry=(List)mp.get(InvestigationConfig.LIST_RESULT_ENTRY_STATUS);
			StringBuilder str = new StringBuilder();	

			objStatus.add(Status.DONE, str.toString(),
					"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ "Result Modified Successfully" + "</font>");



			objStatus.add(Status.NEW, "", "");
			objStatus.add(Status.TRANSINPROCESS, "", "");

		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			System.out.println(e.getMessage());
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			System.out.println(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	public static StringBuffer autoCannedDetails(InvResultEntryFB fb,  HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);




			String	  labCode=fb.getCannedLabCode();
			String	  cannedMacroCheck=fb.getCannedOrMacroCheck();



			mp=InvResultEntryDATA.autoCannedDetails(labCode,cannedMacroCheck ,userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);
			String lstSampleAccepted=(String)mp.get(InvestigationConfig.LIST_CANNED_CODE_FILE_DETAILS);
			sbAjaxRes.append(lstSampleAccepted);

		}
		catch (Exception e)
		{
			strMsgText = "ResultEntryUtil.showCannedDetails() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return sbAjaxRes;
	}
	
	
	public static String checkCannedCodeName(InvResultEntryFB fb,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		String mp="";
	
		
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
          
			String val=fb.getCannedContent();
				val=val.replace("$","&");
			 fb.setCannedContent(val);
			 mp=InvResultEntryDATA.checkcannedCodeName(fb ,userVO);
			
			
			

		}
		catch (Exception e)
		{
			strMsgText = "ResultEntryUtil.showCannedDetails() -> " + e.getMessage();
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

	
	
	public static void printReport(InvResultEntryFB fb, HttpServletRequest objRequest_p,HttpServletResponse response)
	{
		
		 
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		Status status = new Status();
		List<byte[]> pdfs = new ArrayList<byte[]>();
		

		try
		{
			
			byte[] Pdf=new_investigation.transactions.controller.utl.MongoXmlHandler.getInstance().latestFetchFile(fb.getFileName());
			
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
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
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

	
	
	public static String checkBilling(InvResultEntryFB fb,  HttpServletRequest objRequest_p)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		String mp="";
	
		
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
          
			/*String val=fb.getCannedContent();
				val=val.replace("$","&");
			 fb.setCannedContent(val);*/
			 mp=InvResultEntryDATA.checkBilling(fb ,userVO);
			
			
			

		}
		catch (Exception e)
		{
			strMsgText = "ResultEntryUtil.checkBilling() -> " + e.getMessage();
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
	

	public static void getSampleCollectionArea(InvResultEntryFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
	try{
		Map mpp=new HashMap();
		Map mp=new HashMap();
		List<Inv_SampleCollectionVO> lstsampleAreaVO=null;
		List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
		//List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		String todayDate = WebUTIL.getCustomisedSysDate(dt, "dd-MMM-yyyy");
		
		lstsampleAreaVO=new ArrayList<Inv_SampleCollectionVO>();
		Inv_SampleCollectionVO objSampleVO = new Inv_SampleCollectionVO();
		//WebUTIL.populate(lstsampleAreaVO,fb);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		lstsampleAreaVO=SampleCollectionDATA.getSampleCollectionArea(userVO);
			if(lstsampleAreaVO!=null)
			{
				WebUTIL.populate(fb,lstsampleAreaVO);
				
				
				List<Entry> sampleList=new ArrayList<Entry>();
				for (Inv_SampleCollectionVO sampleAreaVO : lstsampleAreaVO) {
									
					Entry entObj=new Entry();
					//updated by krishnan nema on 27/09/2018
					//entObj.setValue(sampleAreaVO.getSampleAreaCode());
					entObj.setValue(sampleAreaVO.getSampleAreaCode()+"#"+sampleAreaVO.getPatientType());
					entObj.setLabel(sampleAreaVO.getSampleAreaName());
					sampleList.add(entObj);
					
				}
				
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_COLLECTION_VO,sampleList);
				lstsamplePatinetVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				
				if(sampleList.size()==1)
				{
					lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
					ResultEntryVO objSampleCollectionVO= new ResultEntryVO();
					
					objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
					objSampleCollectionVO.setFromDate(todayDate);
					objSampleCollectionVO.setToDate(todayDate);
					objSampleCollectionVO.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
					
				
					
					objSampleCollectionVO.setLabCode("%");
					objSampleCollectionVO.setNewEntry("1");
					objSampleCollectionVO.setOnLoadValue("ONLOAD");
					objSampleCollectionVO.setSearchBy("1");
					  
					session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
					session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
					
					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
					
					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
					
				//	lstsamplePatinetVO=SampleCollectionDATA.getPatList(objSampleCollectionVO,userVO);
				
					mpp=InvResultEntryDATA.setPatientResultEntryEssentials(objSampleCollectionVO, userVO);
					mp=InvResultEntryDATA.LabComboForResultEntry(objSampleCollectionVO, userVO);
					WebUTIL.setMapInSession(mp, request);
					WebUTIL.setMapInSession(mpp, request);
					
					
					if(lstsamplePatinetVO!=null)
					{
						WebUTIL.populate(fb,lstsamplePatinetVO); 
						WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
						
					}
					
					
					fb.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
					fb.setSampleAreaName(((Entry)sampleList.get(0)).getLabel());
				fb.setIsSampleAreaSelected("1");
				fb.setLabCode("%");
				}
				
				/*if(sampleList.size()==1)
				{
					lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
					
					Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
					//WebUTIL.populate(objSampleCollectionVO,fb);
					
					objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
					objSampleCollectionVO.setFromDate(todayDate);
					objSampleCollectionVO.setToDate(todayDate);
					
					objSampleCollectionVO.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
					
					session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
					session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
					
					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
					
					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
					
					
					lstsamplePatinetVO=SampleCollectionDATA.getPatList(objSampleCollectionVO,userVO);
					if(lstsamplePatinetVO!=null)
					{
						WebUTIL.populate(fb,lstsamplePatinetVO); 
						WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
						
					}
				
					fb.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
					fb.setSampleAreaName(((Entry)sampleList.get(0)).getLabel());
				fb.setIsSampleAreaSelected("1");
				
				}*/
			}
		
			
		
		status.add(Status.TRANSINPROCESS, "", "");
	}
	catch(Exception e){
		status.add(Status.ERROR_AE,"Application Execution Exception", "");
		e.printStackTrace();
	}
	finally{
		WebUTIL.setStatus(request, status);
	}
}

	
	
	public static StringBuffer getRequisitionFormMasterData(InvResultEntryFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session=objRequest_p.getSession();
	//	Map<String, String> mapUnavailableTestCode =(Map<String, String>) session.getAttribute(InvestigationConfig.MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS);
		//System.out.println("mapUnavailableTestCode : "+mapUnavailableTestCode);
		String strMsgText = "";
		String  remarks="1";
		String lstEpisodeVOo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			String testCode=fb.getTestCodee();
			//LabTestVO voLabTest = mapUserCodeLabTestVO.get(userTestCode);

			lstEpisodeVOo=InvestigationRaisingDtlDATA.getRequisitionFormMasterData(testCode,userVO);

			
				//System.out.println("userTestCode getRemarksForUnavailableTest UTL "+userTestCode+ " : remarks -- : "+remarks);
		
				sbAjaxRes.append(lstEpisodeVOo);
			
			
		}
		catch (Exception e)
		{
			strMsgText = "---> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		
		return sbAjaxRes;
	}

	
	public static void saveAddendumDetails(InvResultEntryFB fb,HttpServletRequest _request)
	{
	    
		
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		Map mp=new HashMap();
		HashMap<String,String> mpParaLoinc=new HashMap();
		InvResultEntryVO vo=new InvResultEntryVO();
		int SizeMapOne=0;
		int tempLength=0;
		int ResultValidationTemplateValueWithHash=-1;
		int labRowCount=-1;
		HashMap<String,String> fetchValue=new HashMap();
		List<HashMap> newmaplist= new ArrayList<HashMap>();
		List<ResultEntryVO> lstinvresultentryvo_old=null;
		ResultEntryVO newPatientDetails=null;
		    String refRange="";
		    String deptunitcode="";
			String patName="";
			String CrNo="";
			String testCode="";
			String sampleNo="";
			String labCode="";
			String patAge="";
			String patGender="";
			String reportAvailableAfter="";
			String patVisitDat="";
			String patVisitNo="";
			String labNo="";
			String episodeCode="";
			String deptCode="";
			String deptUnitCode="";
			String reqType="";
			String testName="";
			String labName="";
			String sampleName="";
			String tempSampleNo="";
			String groupCode="";
			String patunitname="";
			String deptcode="";
		
			
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO=getUserVO(_request);
			
			//updating the patient values
			
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			String crNo=arrSelectedRequisitions[0].split("\\$")[0];
			String reqNO=arrSelectedRequisitions[0].split("\\$")[1];
			String reqDNo=arrSelectedRequisitions[0].split("\\$")[2];
			 String grpCode=arrSelectedRequisitions[0].split("\\$")[3];
			 String testCode1=arrSelectedRequisitions[0].split("\\$")[4];
			 String labCode1=arrSelectedRequisitions[0].split("\\$")[5];
			 vo.setPatCRNo(crNo);
             vo.setReqNo(reqNO);        
			 vo.setRequisitionDNo(reqDNo);
			 vo.setTestCode(testCode1);
			 vo.setLabCode(labCode1);
			 
			
			//	String newEntry=_fb.getNewEntry();
			///////////////////////////////////////////////////////////template session received////////////////////////
		
			mp=InvResultEntryDATA.saveAddendumDetails(vo, _userVO);
		
		
			   
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			System.out.println(e.getMessage());
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			System.out.println(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	
	public static String CHECKCISPARAMETERDEPENDENT(String fb,  HttpServletRequest objRequest_p,String reqno,String selectedindex,InvResultEntryFB fbb)
	{
		
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		String mp="";
	
		
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
          
			 mp=InvResultEntryDATA.CHECKCISPARAMETERDEPENDENT(fb ,userVO,fbb);
			
			 
			 
			 Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata);

			 
			 if(mpBilled!=null && selectedindex!=null && !selectedindex.equalsIgnoreCase("select")|| !selectedindex.equalsIgnoreCase("positive"))
                {
        			List<invAntibioticProcessVO> hyprlistdata1=new ArrayList<invAntibioticProcessVO>();

        			boolean flag=false;
      Iterator itr1=mpBilled.keySet().iterator();
      while(itr1.hasNext())
 		{
    	  
 			String organisgm1=(String)itr1.next();
        for(int k=0;k<fbb.getRequisitionDNo().length;k++)
        {
        	String reqdno=fbb.getRequisitionDNo()[k];
 			if(organisgm1.contains(reqdno))
 			{
 				if(fbb.getIscallfromonloaddynamic()!=null && !fbb.getIscallfromonloaddynamic().equals("1"))
 				mpBilled.remove(organisgm1);
 			}
        }	 
 		}
      session.setAttribute(InvestigationConfig.list_in_sessionhyperlinkdata,mpBilled);
                }
			
			

		}
		catch (Exception e)
		{
			strMsgText = "ResultEntryUtil.showCannedDetails() -> " + e.getMessage();
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
	
	
	public static String createhtmlforecho(String echodata,String dno)
	{
		String data="";
		
		
		if(echodata!=null && !echodata.equals(""))
		{
			String echoval[]=echodata.split("\\*\\*\\*");
			
			
			for(int l=0;l<echoval.length;l++)
			{
				
				if(echoval[l].contains(dno))
				{
					
				data=	createhtmlforechofinal(echoval[l]);
				}
				
			}
			
		}
		
		
		return data;
		
	}
	
	/* updated by PrashantMi */
	public static String createhtmlforechofinal(String echodata) {
		height = "25";
		echodata = echodata.split("\\^\\^\\^")[1];

		Map<String, String> mp = new HashMap<String, String>();
		if (echodata != null && !echodata.equals("")) {
			String[] values = echodata.split("@@@");

			for (int k = 0; k < values.length; k++) {

				String[] val = values[k].split("\\$\\$\\$");

				val[1] = "";

				String keyy = "";
				String vall = "";

				keyy = val[0];

				if (val.length == 2) {
					vall = "";
				} else if (val.length == 3) {
					vall = val[2];

				} else if (val.length > 3) {
					vall = val[2];

					for (int p = 3; p < val.length; p++) {
						vall = vall + "," + val[p];

					}

				}

				mp.put(keyy, vall);

			}

		}

		String ss = (String) mp.get("t30");

		String html = "";
		String width = 20 + "%";
		String t1 = mp.get("t1").equals("-1") ? "" : mp.get("t1");
		String t2 = mp.get("t2").equals("-1") ? "" : mp.get("t2");
		String t3 = mp.get("t3").equals("-1") ? "" : mp.get("t3");
		String t4 = mp.get("t4").equals("-1") ? "" : mp.get("t4");
		String t5 = mp.get("t5").equals("-1") ? "" : mp.get("t5");
		String t6 = mp.get("t6").equals("-1") ? "" : mp.get("t6");
		String t7 = mp.get("t7").equals("-1") ? "" : mp.get("t7");
		String t8 = mp.get("t8").equals("-1") ? "" : mp.get("t8");
		String t9 = mp.get("t9").equals("-1") ? "" : mp.get("t9");
		String t10 = mp.get("t10").equals("-1") ? "" : mp.get("t10");
		String t11 = mp.get("t11").equals("-1") ? "" : mp.get("t11");
		String t12 = mp.get("t12").equals("-1") ? "" : mp.get("t12");
		String t13 = mp.get("t13").equals("-1") ? "" : mp.get("t13");
		String t14 = mp.get("t14").equals("-1") ? "" : mp.get("t14");
		String t15 = mp.get("t15").equals("-1") ? "" : mp.get("t15");
		String t16 = mp.get("t16").equals("-1") ? "" : mp.get("t16");
		String t17 = mp.get("t17").equals("-1") ? "" : mp.get("t17");
		String t18 = mp.get("t18").equals("-1") ? "" : mp.get("t18");
		String t19 = mp.get("t19").equals("-1") ? "" : mp.get("t19");
		String t20 = mp.get("t20").equals("-1") ? "" : mp.get("t20");
		String t21 = mp.get("t21").equals("-1") ? "" : mp.get("t21");
		String t22 = mp.get("t22").equals("-1") ? "" : mp.get("t22");
		String t23 = mp.get("t23").equals("-1") ? "" : mp.get("t23");
		String t24 = mp.get("t24").equals("-1") ? "" : mp.get("t24");
		String t25 = mp.get("t25").equals("-1") ? "" : mp.get("t25");
		String t26 = mp.get("t26").equals("-1") ? "" : mp.get("t26");
		String t27 = mp.get("t27").equals("-1") ? "" : mp.get("t27");
		String t28 = mp.get("t28").equals("-1") ? "" : mp.get("t28");
		String t29 = mp.get("t29").equals("-1") ? "" : mp.get("t29");
		String t30 = mp.get("t30").equals("-1") ? "" : mp.get("t30");
		String t31 = mp.get("t31").equals("-1") ? "" : mp.get("t31");
		String t32 = mp.get("t32").equals("-1") ? "" : mp.get("t32");
		String t33 = mp.get("t33").equals("-1") ? "" : mp.get("t33");
		String t34 = mp.get("t34").equals("-1") ? "" : mp.get("t34");
		String t35 = mp.get("t35").equals("-1") ? "" : mp.get("t35");
		String t36 = mp.get("t36").equals("-1") ? "" : mp.get("t36");
		String t37 = mp.get("t37").equals("-1") ? "" : mp.get("t37");
		String t38 = mp.get("t38").equals("-1") ? "" : mp.get("t38");
		String t39 = mp.get("t39").equals("-1") ? "" : mp.get("t39");
		String t40 = mp.get("t40").equals("-1") ? "" : mp.get("t40");
		String t41 = mp.get("t41").equals("-1") ? "" : mp.get("t41");
		String t42 = mp.get("t42").equals("-1") ? "" : mp.get("t42");
		String t43 = mp.get("t43").equals("-1") ? "" : mp.get("t43");
		String t44 = mp.get("t44").equals("-1") ? "" : mp.get("t44");
		String t45 = mp.get("t45").equals("-1") ? "" : mp.get("t45");

		String t46 = mp.get("t46").equals("-1") ? "" : mp.get("t46");
		String t47 = mp.get("t47").equals("-1") ? "" : mp.get("t47");
		String t48 = mp.get("t48").equals("-1") ? "" : mp.get("t48");
		String t49 = mp.get("t49").equals("-1") ? "" : mp.get("t49");
		String t50 = mp.get("t50").equals("-1") ? "" : mp.get("t50");
		String t51 = mp.get("t51").equals("-1") ? "" : mp.get("t51");
		String t52 = mp.get("t52").equals("-1") ? "" : mp.get("t52");
		String t53 = mp.get("t53").equals("-1") ? "" : mp.get("t53");
		String t54 = mp.get("t54").equals("-1") ? "" : mp.get("t54");
		String t55 = mp.get("t55").equals("-1") ? "" : mp.get("t55");
		String t56 = mp.get("t56").equals("-1") ? "" : mp.get("t56");
		String t57 = mp.get("t57").equals("-1") ? "" : mp.get("t57");
		String t58 = mp.get("t58").equals("-1") ? "" : mp.get("t58").equals("") ? "-" : mp.get("t58");
		String t59 = mp.get("t59").equals("-1") ? "" : mp.get("t59").equals("") ? "-" : mp.get("t59");
		String t60 = mp.get("t60").equals("-1") ? "" : mp.get("t60").equals("") ? "-" : mp.get("t60");
		String t61 = mp.get("t61").equals("-1") ? "" : mp.get("t61").equals("") ? "-" : mp.get("t61");
		String t62 = mp.get("t62").equals("-1") ? "" : mp.get("t62").equals("") ? "-" : mp.get("t62");
		String t63 = mp.get("t63").equals("-1") ? "" : mp.get("t63").equals("") ? "-" : mp.get("t63");
		String t64 = mp.get("t64").equals("-1") ? "" : mp.get("t64").equals("") ? "-" : mp.get("t64");
		String t65 = mp.get("t65").equals("-1") ? "" : mp.get("t65").equals("") ? "-" : mp.get("t65");
		String t66 = mp.get("t66").equals("-1") ? "" : mp.get("t66").equals("") ? "-" : mp.get("t66");
		String t67 = mp.get("t67").equals("-1") ? "" : mp.get("t67").equals("") ? "-" : mp.get("t67");
		String t68 = mp.get("t68").equals("-1") ? "" : mp.get("t68").equals("") ? "-" : mp.get("t68");
		String t69 = mp.get("t69").equals("-1") ? "" : mp.get("t69").equals("") ? "-" : mp.get("t69");
		String t70 = mp.get("t70").equals("-1") ? "" : mp.get("t70").equals("") ? "-" : mp.get("t70");
		String t71 = mp.get("t71").equals("-1") ? "" : mp.get("t71").equals("") ? "-" : mp.get("t71");
		String t72 = mp.get("t72").equals("-1") ? "" : mp.get("t72");
		String t73 = mp.get("t73").equals("-1") ? "" : mp.get("t73");
		String t74 = mp.get("t74").equals("-1") ? "" : mp.get("t74");
		String t75 = mp.get("t75").equals("-1") ? "" : mp.get("t75");
		String t76 = mp.get("t76").equals("-1") ? "" : mp.get("t76");
		String t77 = mp.get("t77").equals("-1") ? "" : mp.get("t77");
		String t78 = mp.get("t78").equals("-1") ? "" : mp.get("t78");

		/*
		 * String t79=mp.get("t79").equals("-1")?"":mp.get("t79"); String
		 * t80=mp.get("t80").equals("-1")?"":mp.get("t80"); String
		 * t81=mp.get("t81").equals("-1")?"":mp.get("t81"); String
		 * t82=mp.get("t82").equals("-1")?"":mp.get("t82"); String
		 * t83=mp.get("t83").equals("-1")?"":mp.get("t83"); String
		 * t84=mp.get("t84").equals("-1")?"":mp.get("t84"); String
		 * t85=mp.get("t85").equals("-1")?"":mp.get("t85"); String
		 * t86=mp.get("t86").equals("-1")?"":mp.get("t86");
		 */
		String t87 = mp.get("t87").equals("-1") ? "" : mp.get("t87");
		String t88 = mp.get("t88").equals("-1") ? "" : mp.get("t88");
		String t89 = mp.get("t89").equals("-1") ? "" : mp.get("t89");
		String t90 = mp.get("t90").equals("-1") ? "" : mp.get("t90");
		String t91 = mp.get("t91").equals("-1") ? "" : mp.get("t91");
		String t92 = mp.get("t92").equals("-1") ? "" : mp.get("t92");
		String t93 = mp.get("t93").equals("-1") ? "" : mp.get("t93");
		String t94 = mp.get("t94").equals("-1") ? "" : mp.get("t94");

		String t95 = mp.get("t95").equals("-1") ? "" : mp.get("t95");
		String t96 = mp.get("t96").equals("-1") ? "" : mp.get("t96");
		String t97 = mp.get("t97").equals("-1") ? "" : mp.get("t97");
		String t98 = mp.get("t98").equals("-1") ? "" : mp.get("t98");
		String t99 = mp.get("t99").equals("-1") ? "" : mp.get("t99");
		String t100 = mp.get("t100").equals("-1") ? "" : mp.get("t100");
		String t101 = mp.get("t101").equals("-1") ? "" : mp.get("t101");
		String t102 = mp.get("t102").equals("-1") ? "" : mp.get("t102");
		String t103 = mp.get("t103").equals("-1") ? "" : mp.get("t103");
		String t104 = mp.get("t104").equals("-1") ? "" : mp.get("t104");
		String t105 = mp.get("t105").equals("-1") ? "" : mp.get("t105");
		String t106 = mp.get("t106").equals("-1") ? "" : mp.get("t106");
		String t107 = mp.get("t107").equals("-1") ? "" : mp.get("t107");
		String t108 = mp.get("t108").equals("-1") ? "" : mp.get("t108");
		String t109 = mp.get("t109").equals("-1") ? "" : mp.get("t109");
		String t110 = mp.get("t110").equals("-1") ? "" : mp.get("t110");

		/*
		 * String t46=mp.get("t46"); String t47=mp.get("t47"); String t48=mp.get("t48");
		 * String t49=mp.get("t49"); String t50=mp.get("t50"); String t51=mp.get("t51");
		 * String t52=mp.get("t52"); String t53=mp.get("t53"); String t54=mp.get("t54");
		 * String t55=mp.get("t55"); String t56=mp.get("t56"); String t57=mp.get("t57");
		 * String t58=mp.get("t58");
		 */

		String tableString = "<table width='100%' >";
		String firstRow = "<tr><td width='30%'><div align='left'><b> Procedure Done By </b></div></td>";
		firstRow += "<td width='70%'><div align='left'>" + t1 + "</div></td>";
		tableString += firstRow + "</tr>";

		String secondRow = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b> Echo Window </b></div></td>";
		secondRow += "<td width='70'><div align='left'>" + t2 + "</div></td>";
		tableString += secondRow + "</tr>";

		String thirdRow = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b> Morphology </b></div></td><td width='70%'></td>";
		tableString += thirdRow + "</tr>";

		String thirdRow1 = "<tr style='height:" + height + "'><td width='30%'><div align='left' ><b> AML </b></div></td>";
		if (t3.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Normal</div></td>";
		} else if (t4.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Abnormal (" + t5 + ")</div></td>";
		}
		tableString += thirdRow1 + "</tr>";

		String thirdRow2 = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b> PML </b></div></td>";
		if (t6.equals("1")) {
			thirdRow2 += "<td width='70%' ><div align='left'>Normal</div></td>";
		} else if (t7.equals("1")) {
			thirdRow2 += "<td width='70%' ><div align='left'>Abnormal (" + t8 + ")</div></td>";
		}
		tableString += thirdRow2 + "</tr>";

		tableString += "</table>";

		// tableString+="<table width='100%' >";

		String mainlabel = "";
		String l1 = "";
		String l2 = "";
		String l3 = "";
		String l4 = "";

		mainlabel = "Doppler";
		l1 = "E (cm/s) ";
		l2 = "A (cm/s) ";
		l3 = "DT";
		l4 = "E/A ";

		tableString += gethtmll(t9, t10, t11, t12, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "Tissue Doppler";
		l1 = "E'' ";
		l2 = "A'' ";
		l3 = "E/e'' ";
		l4 = "";

		tableString += gethtmll(t13, t14, t15, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		tableString += "<table width='100%' >";
		// tableString+="";
		firstRow = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b> Diastolic Function</b></div></td>";
		firstRow += "<td width='70%'><div align='left'>" + t16 + "</div></td>";
		tableString += firstRow + "</tr>";
		tableString += "</table>";

		mainlabel = "Mitral Stenosis";
		l1 = "";
		l2 = "Max DG";
		l3 = "Mean DG(MmHg)";
		l4 = "";

		tableString += gethtmlllabels(t17, t18, t19, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "35", "0");

		mainlabel = "MVA";
		l1 = "By Planimetry (Cmsq) ";
		l2 = "By PHT (Cmsq) ";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t20, t21, "", "", mainlabel, l1, l2, l3, l4, "30", "35", "35", "0", "0");

		mainlabel = "WILKINS SCORE";
		l1 = "Leaflet Mobility ";
		l2 = "Valve Thickness ";
		l3 = "Subvalvular Thickening ";
		l4 = "Valve Calcification ";

		tableString += gethtmll(t22, t23, t24, t25, mainlabel, l1, l2, l3, l4, "30", "20", "20", "15", "15");

		tableString += "<table width='100%' >";
		tableString += "";
		firstRow = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b> Mitral Regurgitation </b></div></td>";
		firstRow += "<td width='70%'><div align='left'>" + t26 + "</div></td>";
		tableString += firstRow + "</tr></table>";

		mainlabel = "A4C";
		l1 = " LAA ";
		l2 = " Jet Area ";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t28, t27, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "PLAX";
		l1 = " LAA ";
		l2 = " Jet Area ";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t30, t29, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		/*
		 * mainlabel=""; l1=" JetArea(Cmsq) "; l2=" A4C (Cmsq) ";
		 * l3=" JetArea<br/>(Cmsq) "; l4=" LAA (Plax)<br/>(Cmsq) ";
		 * 
		 * tableString+=gethtmll(t27, t28, t29,t30, mainlabel, l1, l2, l3,
		 * l4,"30","20","20","15","15") ;
		 */

		mainlabel = "";
		l1 = " VC mm ";
		l2 = " RV ";
		l3 = " ERO";
		l4 = "";

		tableString += gethtmll(t31, t32, t33, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "TRISCUPID VALVE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		tableString += "<table width='100%' >";
		thirdRow1 = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b> Morphology </b></div></td>";
		if (t34.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Normal</div></td>";
		} else if (t35.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Abnormal (" + t36 + ")</div></td>";
		}
		tableString += thirdRow1 + "</tr></table>";

		mainlabel = "Tricuspid Stenosis";
		l1 = "";
		l2 = "Max DG";
		l3 = "Mean DG(MmHg)";
		l4 = "";

		tableString += gethtmlllabels(t37, t38, t39, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "35", "0");

		mainlabel = "Tricuspid Regurgitation";
		l1 = "";
		l2 = " RVSP(mmHg)=RAP+ ";
		l3 = "";
		l4 = "";

		tableString += gethtmlllabels(t40, t41, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "52.5", "0", "0");

		mainlabel = "Pulmonary Hypertension";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmlllabels(t42, "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "AORTIC VALVE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		tableString += "<table width='100%' >";
		thirdRow1 = "<tr style='height:" + height + "'><td width='30%'><div align='left' ><b> Morphology </b></div></td>";
		if (t43.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Normal</div></td>";
		} else if (t44.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Abnormal (" + t45 + ")</div></td>";
		}
		tableString += thirdRow1 + "</tr></table>";

		mainlabel = "CUSPS";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t95, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "Doppler";
		l1 = "Aortic peak systolic velocity(m/sec)";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmll1td(t96, "", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "Aortic Stenosis";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t97, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "";
		l1 = "PSG(mmHg) ";
		l2 = "MSG(mmHg) ";
		l3 = "AVA(VTI) (cmsq) ";
		l4 = "";

		tableString += gethtmll(t98, t99, t100, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "35", "0");

		mainlabel = "";
		l1 = "Aortic annulus(mm)";
		l2 = "Sinus(mm)";
		l3 = "STJ(mm)";
		l4 = "";

		tableString += gethtmll(t101, t102, t103, "", mainlabel, l1, l2, l3, l4, "30", "35", "17.5", "17.5", "0");

		mainlabel = "Aortic Regurgitation";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t104, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "";
		l1 = "Jet Width<br/>(mm)";
		l2 = "LVOT width<br/>(mm)";
		l3 = "PHT<br/> ";
		l4 = "Vena Contracta<br/>(mm)";

		tableString += gethtmll(t105, t106, t107, t108, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "PULMONARY VALVE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "Morphology";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t109, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "Doppler";
		l1 = "PSV(M/sec)";
		l2 = "PAT(M/sec)";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t46, t47, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "Pulmonary Stenosis";
		l1 = "";
		l2 = "PSG(mmHg)";
		l3 = "MG(mmHg)";
		l4 = "";

		tableString += gethtmlllabels(t48, t49, t50, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "Pulmonary Annulus size(mm)";
		l1 = "";
		l2 = "MPA(mm)";
		l3 = "RPA(mm)";
		l4 = "LPA(mm)";

		tableString += gethtmlllabels(t51, t52, t53, t54, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "Pulmonary Regurgitation";
		l1 = "";
		l2 = "Early DG (MmHg)";
		l3 = "End DG (MmHg)";
		l4 = "";

		tableString += gethtmlllabels(t55, t56, t57, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "26.5", "26.5", "0");

		tableString += "<table width='100%' >";
		thirdRow1 = "<tr style='height:" + height + "'><td width='20%'><div align='left'><b style='font-size: 15px'>MEASUREMENTS</b></div></td>";

		thirdRow1 += "<td width='10%' ><div align='left'><b style='font-size: 15px'>VALUES</b></div></td>";

		thirdRow1 += "<td width='20%' ><div align='left'><b style='font-size: 15px'>NORMAL Val.</b></div></td>";

		thirdRow1 += "<td width='20%'><div align='left'><b style='font-size: 15px' >MEASUREMENTS</b></div></td>";

		thirdRow1 += "<td width='10%' ><div align='left'><b style='font-size: 15px'>VALUES</b></div></td>";

		thirdRow1 += "<td width='20%' ><div align='left'><b style='font-size: 15px'>NORMAL Val.</b></div></td>";

		tableString += thirdRow1 + "</tr>";

		mainlabel = "IVsd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t58, "(6-10)mm", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "Aorta";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t59, "(20-37)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVIDd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t60, "(36-52)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LA";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t61, "(19-40)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVPWd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t62, "(7-11)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "RVIDd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t63, "(4-14)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "IVss";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t64, "(7-12)mm  ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "RV Free Wall";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t65, "(upto 5 mm) ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVIDs";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t66, "", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "FS";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t67, "(34-44)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVPWs";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t68, "(8-12)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "TAPSE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t69, "(>16)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "EF";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t70, "(>55%) ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "Visual EF";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t71, "(>55%) ", "", "", mainlabel, l1, l2, l3, l4);
		tableString += "</table>";

		mainlabel = "EF By SIMPSON Method";
		l1 = "LVEDV";
		l2 = "LVESV";
		l3 = "EF";
		l4 = "";

		tableString += gethtmll(t72, t73, t110, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "IVS Motion";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t74, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "CHAMBERS";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "";
		l1 = "LA";
		l2 = "LV";
		l3 = "RA";
		l4 = "RV";

		tableString += gethtmll(t75, t76, t77, t78, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		// old
		/*
		 * mainlabel="LA"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t75,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 * 
		 * mainlabel="LV"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t76,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="RA"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t77,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="RV"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t78,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 */

		// because less space
		/*
		 * mainlabel="Pericardium"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t79,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 */

		// old
		/*
		 * mainlabel="Effusion"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t80,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 * 
		 * mainlabel="Anterior To RV(mm)"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t81,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="Posterior To LV(Mm)"; l1=""; l2="";
		 * l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t82,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="Lateral(Mm)"; l1=""; l2=""; l3="";
		 * l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t83,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 */

		// because of less space
		/*
		 * mainlabel="Effusion"; l1=""; l2="Ant. To RV(mm)"; l3="Post. To LV(Mm)";
		 * l4="Lat(Mm)";
		 * 
		 * tableString+=gethtmlllabels(t80, t81, t82,t83,mainlabel, l1, l2, l3,
		 * l4,"30","15","18.3","18.3","18.3") ;
		 */

		// because of less space
		/*
		 * mainlabel="IVC Diameter"; l1="Expiration(mm)"; l2="Inspiration(mm)";
		 * l3="Collapsibility(%)"; l4="";
		 * 
		 * tableString+=gethtmll(t84,t85,t86,"",mainlabel, l1, l2, l3,
		 * l4,"30","24","24","22","0") ;
		 */

		mainlabel = "RWMA";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t87, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "BASAL LV";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t88, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "MID LV";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t89, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "APICAL LV";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t90, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "LV APEX";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t91, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "Remarks";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t92, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "IMPRESSION";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t93, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "PLAN";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t94, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		html = tableString;
		return html;
	}

	public static String gethtmllgrouplable(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4,
			String md, String wd1, String wd2, String wd3, String wd4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";
		int count = 0;

		String thirdRow3 = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md + "%'><div align='left' ><b> " + mainlabel
				+ " </b></div></td>";
		if (!t9.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd1 + "%'><div align='left'> <b>" + l1 + "</b></div></td>";
		}
		if (!t10.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd2 + "%'><div align='left'><b> " + l2 + "</b></div></td>";
		}
		if (!t11.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd3 + "%'><div align='left'><b> " + l3 + " </b></div></td>";
		}
		if (!t12.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd4 + "%'><div align='left'><b>" + l4 + " </b></div></td>";
		}

		if (count < 4) {
			for (int k = count; k < 4; k++) {
				if (k < 2)
					thirdRow3 += "<td width='20%'></td>";
				else if (k == 2)
					thirdRow3 += "<td width='" + wd3 + "%'></td>";
				else if (k == 3)
					thirdRow3 += "<td width='" + wd4 + "%'></td>";
			}

		}
		tableString += thirdRow3 + "</tr>";

		/*
		 * if(!t9.equals("") || !t10.equals("") || !t11.equals("") || !t12.equals("") )
		 * {
		 * 
		 * int count80=0; String thirdRow4="<tr><td width='"
		 * +md+"%'><div align='left' style='margin-left: 10;'><b>  </b></div></td>";
		 * if(!t9.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd1+"%'><div align='left'>"+t9+"</div></td>"; }
		 * if(!t10.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd2+"%'><div align='left'>"+t10+"</div></td>"; }
		 * if(!t11.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd3+"%'><div align='left'>"+t11+"</div></td>"; }
		 * if(!t12.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd4+"%'><div align='left'>"+t12+"</div></td>"; }
		 * 
		 * if(count80<4) { for(int k=count80;k<4;k++) { if(k<2)
		 * thirdRow4+="<td width='20%'></td>"; else if(k==2)
		 * thirdRow4+="<td width='"+wd3+"%'></td>"; else if(k==3)
		 * thirdRow4+="<td width='"+wd4+"%'></td>"; }
		 * 
		 * } tableString+=thirdRow4+"</tr>"; }
		 */
		tableString += "</table>";
		return tableString;
	}

	public static String gethtmllforone(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4) {

		String firstRow = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b>" + mainlabel + "</b></div></td>";
		firstRow += "<td width='20%'><div align='left'>" + t9 + "</div></td><td width='20%'><div align='left'>" + t10
				+ "</div></td><td width='20%'></td><td width='10%'></td></tr>";

		return firstRow;

	}

	public static String gethtmllforone1(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4) {

		String firstRow = "<tr style='height:" + height + "'><td width='20%'><div align='left'><b>" + mainlabel + "</b></div></td>";
		firstRow += "<td width='10%'><div align='left'>" + t9 + "</div></td><td width='20%'><div align='left'>" + t10 + "</div></td>";
		return firstRow;
	}

	public static String gethtmllforone2(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4) {

		String firstRow = "<td width='20%'><div align='left'><b>" + mainlabel + "</b></div></td>";
		firstRow += "<td width='10%'><div align='left'>" + t9 + "</div></td><td width='20%'><div align='left'>" + t10 + "</div></td></tr>";
		return firstRow;

	}

	public static String gethtmllfortwolabel(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4,
			String md, String wd1, String wd2, String wd3, String wd4) {

		String firstRow = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md + "%'><div align='left'><b>" + mainlabel
				+ "</b></div></td>";
		firstRow += "<td width='" + wd1 + "%'><div align='left'>" + t9 + "</div></td></tr></table>";

		return firstRow;

	}

	public static String gethtmll1td(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";

		int count = 0;

		String thirdRow3 = "<table width='100%' ><tr style='height:" + height + "'><td width='30%'><div align='left' ><b> " + mainlabel + " </b></div></td>";

		if (!t9.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='70%' ><div align='left'> <b>" + l1 + "</b></div></td>";

			tableString += thirdRow3 + "</tr>";

			int count80 = 0;
			String thirdRow4 = "<tr><td width='30%'><div align='left' ><b>  </b></div></td>";

			count80 = count80 + 1;
			thirdRow4 += "<td width='70%'><div align='left'>" + t9 + "</div></td>";

			tableString += thirdRow4 + "</tr>";

		} else {
			tableString += thirdRow3 + "</tr>";
		}

		tableString += "</table>";

		/*
		 * if(t9.equals("")) { String
		 * thirdRow3="<table width='100%' ><tr><td width='30%'><div align='left' ><b> &nbsp; </b></div></td>"
		 * ;
		 * 
		 * count=count+1;
		 * thirdRow3+="<td width='70%' ><div align='left'> <b></b></div></td>";
		 * 
		 * tableString+=thirdRow3+"</tr>";
		 * 
		 * int count80=0; String
		 * thirdRow4="<tr><td width='30%'><div align='left' ><b>  </b></div></td>";
		 * 
		 * count80=count80+1;
		 * thirdRow4+="<td width='70%'><div align='left'></div></td>";
		 * 
		 * 
		 * 
		 * tableString+=thirdRow4+"</tr></table>"; }
		 */
		return tableString;
	}

	public static String gethtmlllabels(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4, String md,
			String wd1, String wd2, String wd3, String wd4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";
		int count = 0;
		String thirdRow3 = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md + "%'><div align='left'><b> " + mainlabel
				+ " </b></div></td>";
		if (!t9.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd1 + "%'><div align='left'>" + t9 + "</div></td>";
		}
		if (!t10.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd2 + "%'><div align='left'><b> " + l2 + "</b></div></td>";
		}
		if (!t11.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd3 + "%'><div align='left'><b> " + l3 + " </b></div></td>";
		}
		if (!t12.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd4 + "%'><div align='left'><b>" + l4 + " </b></div></td>";
		}

		if (count < 4) {
			for (int k = count; k < 4; k++) {
				if (k < 2)
					thirdRow3 += "<td width='" + wd1 + "%'></td>";
				else if (k == 2)
					thirdRow3 += "<td width='" + wd3 + "%'></td>";
				else if (k == 3)
					thirdRow3 += "<td width='" + wd4 + "%'></td>";
			}

		}
		tableString += thirdRow3 + "</tr>";

		if (!t10.equals("") || !t11.equals("") || !t12.equals("")) {
			int count80 = 0;
			String thirdRow4 = "<tr><td width='" + md + "%'><div align='left' ></div></td>";
			if (!t9.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd1 + "%'><div align='left'></div></td>";
			}
			if (!t10.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd2 + "%'><div align='left'>" + t10 + "</div></td>";
			}
			if (!t11.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd3 + "%'><div align='left'>" + t11 + "</div></td>";
			}
			if (!t12.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd4 + "%'><div align='left'>" + t12 + "</div></td>";
			}

			if (count80 < 4) {
				for (int k = count80; k < 4; k++) {
					if (k < 2)
						thirdRow4 += "<td width='" + wd1 + "%'></td>";
					else if (k == 2)
						thirdRow4 += "<td width='" + wd3 + "%'></td>";
					else if (k == 3)
						thirdRow4 += "<td width='" + wd4 + "%'></td>";
				}

			}

			tableString += thirdRow4 + "</tr>";
		}
		tableString += "</table>";

		return tableString;
	}

	public static String gethtmll(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2, String l3, String l4, String md,
			String wd1, String wd2, String wd3, String wd4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";
		int count = 0;

		if (!t9.equals("") || !t10.equals("") || !t11.equals("") || !t12.equals("") || !mainlabel.equals("")) {

			String thirdRow3 = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md + "%'><div align='left' ><b> " + mainlabel
					+ " </b></div></td>";
			if (!t9.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd1 + "%'><div align='left'> <b>" + l1 + "</b></div></td>";
			}
			if (!t10.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd2 + "%'><div align='left'><b> " + l2 + "</b></div></td>";
			}
			if (!t11.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd3 + "%'><div align='left'><b> " + l3 + " </b></div></td>";
			}
			if (!t12.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd4 + "%'><div align='left'><b>" + l4 + " </b></div></td>";
			}

			if (count < 4) {
				for (int k = count; k < 4; k++) {
					if (k < 2)
						thirdRow3 += "<td width='20%'></td>";
					else if (k == 2)
						thirdRow3 += "<td width='" + wd3 + "%'></td>";
					else if (k == 3)
						thirdRow3 += "<td width='" + wd4 + "%'></td>";
				}

			}
			tableString += thirdRow3 + "</tr>";

			if (!t9.equals("") || !t10.equals("") || !t11.equals("") || !t12.equals("")) {

				int count80 = 0;
				String thirdRow4 = "<tr><td width='" + md + "%'><div align='left' style='margin-left: 10;'><b>  </b></div></td>";
				if (!t9.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd1 + "%'><div align='left'>" + t9 + "</div></td>";
				}
				if (!t10.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd2 + "%'><div align='left'>" + t10 + "</div></td>";
				}
				if (!t11.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd3 + "%'><div align='left'>" + t11 + "</div></td>";
				}
				if (!t12.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd4 + "%'><div align='left'>" + t12 + "</div></td>";
				}

				if (count80 < 4) {
					for (int k = count80; k < 4; k++) {
						if (k < 2)
							thirdRow4 += "<td width='20%'></td>";
						else if (k == 2)
							thirdRow4 += "<td width='" + wd3 + "%'></td>";
						else if (k == 3)
							thirdRow4 += "<td width='" + wd4 + "%'></td>";
					}

				}

				tableString += thirdRow4 + "</tr>";
			}
			tableString += "</table>";
		}
		return tableString;
	}

}