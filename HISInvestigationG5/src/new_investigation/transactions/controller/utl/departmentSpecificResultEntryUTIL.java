package new_investigation.transactions.controller.utl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MongoHelper.MongoXmlHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.upload.FormFile;
import org.bson.types.ObjectId;

import com.edb.util.Base64;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.departmentSpecificResultEntryDATA;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.Helper.departmentTemplateProcessingUSE;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.departmentSpecificResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.ResultEntryVOGroupByValidatedBy;

public class departmentSpecificResultEntryUTIL extends ControllerUTIL
{
	public static void getEssential(departmentSpecificResultEntryFB fb,HttpServletRequest request)
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

			mp=departmentSpecificResultEntryDATA.LabComboForResultEntry(invresultentryvo, userVO);
			WebUTIL.setMapInSession(mp, request);
			
			  String tDate = WebUTIL.getCustomisedSysDate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			  invresultentryvo.setFromDate(tDate);
			  invresultentryvo.setToDate(tDate);
			  invresultentryvo.setLabCode("%");
			  invresultentryvo.setNewEntry("1");
			  invresultentryvo.setOnLoadValue("ONLOAD");
			  
				mpp=departmentSpecificResultEntryDATA.setPatientResultEntryEssentials(invresultentryvo, userVO);
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


	public static boolean setPatientEssentials(departmentSpecificResultEntryFB fb, HttpServletRequest _request)
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
			mp=departmentSpecificResultEntryDATA.setPatientResultEntryEssentials(invresultentryvo, userVO);
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

	public static void showResultEntryPatDetails(departmentSpecificResultEntryFB fb,HttpServletRequest request)
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

					//	departmentSpecificResultEntryDATA.fetchLoincCode(objPatientCollectionVO,userVO);


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
                            System.out.println("rvo.getIsrequisitionformneeded()::::"+rvo.getIsrequisitionformneeded());
                            System.out.println("objPatientCollectionVO.getIsrequisitionformneeded()::::"+objPatientCollectionVO.getIsrequisitionformneeded());
                            rvo.setIsrequisitionformneeded(objPatientCollectionVO.getIsrequisitionformneeded());
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
							
							//dynamic dept test
							rvo.setDynamnicTemplateResultEntryGroup(objPatientCollectionVO.getDynamnicTemplateResultEntryGroup());
							rvo.setDynamicTemplatePrintedGroup(objPatientCollectionVO.getDynamicTemplatePrintedGroup());
							
							rvo.setDeptTestTemplatePrinted(objPatientCollectionVO.getDeptTestTemplatePrinted());
							rvo.setDeptTestTemplateResultEntry(objPatientCollectionVO.getDeptTestTemplateResultEntry());
							
							
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

							List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();

							//invresultentryv=objPatientCollectionVO;
							//	++i;
							// if( objPatientCollectionVO.getDynamnicTemplateResultEntryGroup() != null && objPatientCollectionVO.getDynamnicTemplateResultEntryGroup().equals("1")) {
							// rvo.setTestName(objPatientCollectionVO.getGroupName());
							rvo.setGroupCode(objPatientCollectionVO.getGroupCode());
							rvo.setDynamnicTemplateResultEntryGroup(objPatientCollectionVO.getDynamnicTemplateResultEntryGroup());
							rvo.setDynamicTemplatePrintedGroup(objPatientCollectionVO.getDynamicTemplatePrintedGroup());
							
							rvo.setDeptTestTemplatePrinted(objPatientCollectionVO.getDeptTestTemplatePrinted());
							rvo.setDeptTestTemplateResultEntry(objPatientCollectionVO.getDeptTestTemplateResultEntry());
							
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

			//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO, lstInvResultEntryVO);
			//for(String str:arrSelectedRequisitions)
			List<ResultEntryVO> lstInvResultEntryTemplateVO=null;
			lstInvResultEntryTemplateVO=departmentTemplateProcessingUSE.groupSelectedWorkOrders(lstInvResultEntryVO, session);
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
			//mp.put(InvestigationConfig.LIST_RESULT_ENTRY,newLstInvResultEntryTemplateVO);

			Map<String,List<ResultEntryVO>> objMapResEntry= new HashMap<String,List<ResultEntryVO>>();

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
				}
				else
				{
					lstTempResultEntryVO.add(objResultEntryVO);
				}

				objMapResEntry.put(strSampleNo,lstTempResultEntryVO);

			}

			//mp.put(InvestigationConfig.LIST_SAMPLE_ACCEPTANCE_VO,lstsampleAcceptanceVO);
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO,objMapResEntry);


			//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO, lstInvResultEntryTemplateVO);
			//mp=departmentSpecificResultEntryDATA.patientDetails(invresultentryv,reqList, userVO);





			WebUTIL.setMapInSession(mp, request);


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
	//			HttpServletRequest request, departmentSpecificResultEntryFB fb) throws Exception {
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
	public static void saveResultEntryPatientDetails(departmentSpecificResultEntryFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		Map mp=new HashMap();
		int SizeMapOne=0;
		HashMap<String,String> mpParaLoinc=new HashMap();
		HashMap<String,String> fetchMap=new HashMap();
		List<HashMap> newmaplist= new ArrayList<HashMap>();
		
		try
		{
		
			//MongoClient mongo = new MongoClient("10.226.2.169", 27017);

			//DB db = mongo.getDB("testdb");

			//DBCollection table = db.getCollection("patientDocumentsFiles1001");
			DBCollection table = new_investigation.transactions.controller.utl.MongoXmlHandler.getInstance().getDbCollection();
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
			
			String saveReval="";
			String patCr="";
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
						patCr=CrNo;
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
						
						
						//by default mot to be sent to reval process
						voResultEntry1.setSaveReVal("0");
						if(_fb.getSaveReVal()!=null)
							for(String tempSaveReVal:_fb.getSaveReVal())
							{
								if(tempSaveReVal.equals(CrNo+"#"+patReqNo))
									voResultEntry1.setSaveReVal("1");
								else
									voResultEntry1.setSaveReVal("0");
								
							}
						
						
						

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
					
					//by default mot to be sent to reval process
					voResultEntry.setSaveReVal("0");
					if(_fb.getSaveReVal()!=null)
						for(String tempSaveReVal:_fb.getSaveReVal())
						{
							if(tempSaveReVal.equals(CrNo+"#"+patReqNo))
								voResultEntry.setSaveReVal("1");
							else
								;
							
						}

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
				if(splitedValue.length()>9)
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
				
/*				if(SizeMapOne==0)
				{
					fetchMap=newmaplist.get(i);
					i++;
					SizeMapOne=fetchMap.size();
					SizeMapOne=SizeMapOne/3;
				}
			
				String loinc=fetchMap.get(splitedValue);
				String refRange=fetchMap.get(splitedValue+"refrange");
				SizeMapOne--;
			*/

				String loinc="";
				String refRange="";
			
				voResultEntryForParaMeterDtl.setLoincCode(loinc);
				voResultEntryForParaMeterDtl.setStrRefRange(refRange);
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

				
				
			 	//by default mot to be sent to reval process
				voResultEntryForParaMeterDtl.setSaveReVal("0");
				if(_fb.getSaveReVal()!=null)
					for(String tempSaveReVal:_fb.getSaveReVal())
					{
						if(tempSaveReVal.equals(patCr+"#"+temaplateValue[6]))
							voResultEntryForParaMeterDtl.setSaveReVal("1");
						else
						;
						
					}
				
				
				
				
				invResultEntryForParameterDtlVO.add(voResultEntryForParaMeterDtl);
			}


System.out.println("total values are:  "+invResultEntryForParameterDtlVO.size());

if(_fb.getCrNoReqNoString()!=null)
if(invResultEntryVO!=null)
	{
	invResultEntryVO.get(0).setCrNoReqNoString(_fb.getCrNoReqNoString());
	invResultEntryVO.get(0).setFinalRemarkValue(_fb.getFinalRemarksValue());
	}


			mp=departmentSpecificResultEntryDATA.saveResultEntryDetails(invResultEntryVO,invResultEntryForParameterDtlVO, _userVO, _request);
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




	public static StringBuffer showCannedDetails(departmentSpecificResultEntryFB fb,  HttpServletRequest objRequest_p)
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


			/*if(fb.getCheckProcessFb().equals("departmentSpecificResultEntryFB"))
				 {
					   labCode=fb.getCannedLabCode();
					   cannedMacroCheck=fb.getCannedOrMacroCheck();
				 }*/
			//objRequest_p.getSession().setAttribute("a",strTestCombo);

			//strTestCombo=InvestigationRaisingDtlDATA.getTestComboAJAX(labCode,userVO);
			//session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			mp=departmentSpecificResultEntryDATA.showCannedDetails(labCode,cannedMacroCheck ,userVO);
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







	public static boolean getEntryTypeDetails(departmentSpecificResultEntryFB fb, HttpServletRequest _request)
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
			invresultentryvo.setPatCRNo(fb.getPatCRNo()); 
			invresultentryvo.setLabCode(fb.getLabCode());
			invresultentryvo.setNewEntry(fb.getNewEntry());

			mp=departmentSpecificResultEntryDATA.setPatientResultEntryEssentials(invresultentryvo, userVO); 
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


	public static void modifyResultEntryPatientDetails(departmentSpecificResultEntryFB _fb,HttpServletRequest _request)
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
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO=getUserVO(_request);



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



				
				//by default mot to be sent to reval process
				voResultEntry1.setSaveReVal("0");
				if(_fb.getSaveReVal()!=null)
					for(String tempSaveReVal:_fb.getSaveReVal())
					{
						if(tempSaveReVal.equals(CrNo+"#"+patReqNo))
							voResultEntry1.setSaveReVal("1");
						else
							voResultEntry1.setSaveReVal("0");
						
					}
				



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
				    	
						
						//by default mot to be sent to reval process
						voResultEntry1.setSaveReVal("0");
						if(_fb.getSaveReVal()!=null)
							for(String tempSaveReVal:_fb.getSaveReVal())
							{
								if(tempSaveReVal.equals(CrNo+"#"+patReqNo))
									voResultEntry1.setSaveReVal("1");
								else
									;
								
							}


					



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

			Map<String,String> testParaSeq=new HashMap<String,String>();
			
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
				String seqNo="";
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

				
				
				if(testParaSeq.get(parantParameterCode)==null)
					{testParaSeq.put(parantParameterCode, "1");
					seqNo="1";}
				else
				{
				seqNo=""+(Integer.parseInt(testParaSeq.get(parantParameterCode))+1);
				testParaSeq.put(parantParameterCode, seqNo);
				}
					
					
				String resultEntryValue=temaplateValue[4];
				/*mpParaLoinc=invResultEntryVO.get(j).getParaLoinc();*/


/*				if(SizeMapOne==0)
				{fetchValue=newmaplist.get(i);
				i++;
				SizeMapOne=fetchValue.size();
				SizeMapOne=SizeMapOne/3;
				}
				String loinc=fetchValue.get(parantParameterCode);
				refRange=fetchValue.get(parantParameterCode+"refrange");
				String previousValue=fetchValue.get(parantParameterCode+"previousValue");
				SizeMapOne--;
*/
				String loinc="";
				refRange="";
				String previousValue="";
				
				voResultEntryForParaMeterDtl.setLoincCode(loinc);
				voResultEntryForParaMeterDtl.setStrRefRange(refRange);
				voResultEntryForParaMeterDtl.setPreviousValue(previousValue);
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
				voResultEntryForParaMeterDtl.setSeqNo(seqNo);
				
			 	//by default mot to be sent to reval process
				voResultEntryForParaMeterDtl.setSaveReVal("0");
				if(_fb.getSaveReVal()!=null)
					for(String tempSaveReVal:_fb.getSaveReVal())
					{
						if(tempSaveReVal.equals(CrNo+"#"+temaplateValue[6]))
							voResultEntryForParaMeterDtl.setSaveReVal("1");
						else
						;
						
					}
				
				
				invResultEntryForParameterDtlVO.add(voResultEntryForParaMeterDtl);
			}




			mp=departmentSpecificResultEntryDATA.modifyResultEntryDetails(invResultEntryVO,invResultEntryForParameterDtlVO, _userVO, _request);
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

	public static StringBuffer autoCannedDetails(departmentSpecificResultEntryFB fb,  HttpServletRequest objRequest_p)
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



			mp=departmentSpecificResultEntryDATA.autoCannedDetails(labCode,cannedMacroCheck ,userVO);
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
	
	
	public static String checkCannedCodeName(InvResultValidationFB fb,  HttpServletRequest objRequest_p)
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
			 mp=departmentSpecificResultEntryDATA.checkcannedCodeName(fb ,userVO);
			
			
			

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



}