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
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.core.MediaType;

//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.ClientConfig;





import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

//import org.json.JSONObject;
//import org.json.JSONArray;







import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.linear.twoOfFive.Int2of5Barcode;
import net.sourceforge.barbecue.linear.twoOfFive.Std2of5Barcode;
import net.sourceforge.barbecue.output.OutputException;
import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.data.InvTrackingReportDATA;
import new_investigation.reports.controller.fb.InvTrackingReportFB;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.invRaisingCumSamCollectionFB;
import new_investigation.vo.InvTrackingReportVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;


public class SampleCollectionUTL extends ControllerUTIL
{
	public static void setPatientRegistrationEssentials(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		List<Inv_EpisodeVO> lstEpisodeVO=new ArrayList<Inv_EpisodeVO>();
		List<Inv_PatientAdmissionDtlVO> lstPatientVO=new ArrayList<Inv_PatientAdmissionDtlVO>();
		Map mp=new HashMap(); 
	try{
		Inv_RequisitionRaisingPatientVO patVO=null;
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
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
	
		
	public static void getPatList(SampleCollectionFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
	try{
		List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
		
		lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
		Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
	
	//	WebUTIL.populate(objSampleCollectionVO,fb);
		if(fb.getPatCrNo()!=null && !fb.getPatCrNo().equals(""))
			objSampleCollectionVO.setPatCRNo(fb.getPatCrNo());
		
		if(fb.getPatCRNo()!=null && !fb.getPatCRNo().equals(""))
		objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
		objSampleCollectionVO.setFromDate(fb.getFromDate());
		objSampleCollectionVO.setToDate(fb.getToDate());
		
		objSampleCollectionVO.setSampleAreaCode(fb.getSampleAreaCode());
		objSampleCollectionVO.setWardCode(fb.getWardCode());

		session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
		session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
		
		session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
		session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
		
		session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
		session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
		
		
		
		//InvestigationConfig.SELECTED_PAT_DETAILS
		
		UserVO userVO=ControllerUTIL.getUserVO(request);
		lstsamplePatinetVO=SampleCollectionDATA.getPatList(objSampleCollectionVO,userVO);
		
		
			//if(lstsamplePatinetVO!=null)
			//{
				//WebUTIL.populate(fb,lstsamplePatinetVO.get(0)); 
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
				
			
				
			//}
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
	
	
	public static void getPatListBarcode(SampleCollectionFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
	try{
		List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
		
		lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
		Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
	
	//	WebUTIL.populate(objSampleCollectionVO,fb);
		
		objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
		objSampleCollectionVO.setFromDate(fb.getFromDate());
		objSampleCollectionVO.setToDate(fb.getToDate());
		
		objSampleCollectionVO.setSampleAreaCode(fb.getSampleAreaCode());
		
		session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
		session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
		
		session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
		session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
		
		session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
		session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
		
		
		
		//InvestigationConfig.SELECTED_PAT_DETAILS
		
		UserVO userVO=ControllerUTIL.getUserVO(request);
		lstsamplePatinetVO=SampleCollectionDATA.getPatListBarcode(objSampleCollectionVO,userVO);
			//if(lstsamplePatinetVO!=null)
			//{
				//WebUTIL.populate(fb,lstsamplePatinetVO.get(0)); 
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO_BARCODE,lstsamplePatinetVO);
				
			//}
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
	
	public static void getSampleCollectionArea(SampleCollectionFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
	try{
		List<Inv_SampleCollectionVO> lstsampleAreaVO=null;
		List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
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
					
					entObj.setValue(sampleAreaVO.getSampleAreaCode());
					entObj.setLabel(sampleAreaVO.getSampleAreaName());
					sampleList.add(entObj);
					
				}
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_COLLECTION_VO,sampleList);
				lstsamplePatinetVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				if(sampleList.size()==1)
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
				
				}
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
	
	// Save Logic
	public static void saveSampleCollectionDetails(SampleCollectionFB _fb,HttpServletRequest _request) throws BarcodeException, OutputException
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		String[] msgBuilder;
		// Logic defined as Map<CRNo,Map<RequisitionNo,Map<SampleCode#labCode,List<Inv_SampleCollectionVO>>>
		Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp= new LinkedHashMap<String, Map<String,Map<String,List<Inv_SampleCollectionVO>>>>();
		//OutputStream os=new ByteArrayOutputStream();
		
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO=getUserVO(_request);
		//	HelperMethods.populate(bloodRequisitionDtlVO, _fb);
			
			String mobileNo=_fb.getPatMobile()==null?"":_fb.getPatMobile();
			String emailId=_fb.getPatEmail()==null?"":_fb.getPatEmail();
			String patAddress=_fb.getPatAddress()==null?"":_fb.getPatAddress();
			String patName=_fb.getPatName()==null?"":_fb.getPatName().trim();
			
			if(patName.length()>10)
			{
				patName=patName.substring(0,10);					
			}

			
			String collAreaCode=_fb.getSampleAreaCode()==null?"":_fb.getSampleAreaCode();
			String printStatus="0"; // Need to discuss
			
						
			String[] selectedLabTestCodeArray=_fb.getChkSamplePatient();
			
						int labRowCount=selectedLabTestCodeArray.length;
						
						for(int i=0,k=0;i<labRowCount;i++)
						{
							
							//Getting LabCodeValues from split array
							// chkVal Order crNo#requisitionNo#sampleCode#requisitionDNo //Please Ensure the corresponding Changes before changing this order
							String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
							
							String crNo=testCodeArray[0];
								String requisitionNo=testCodeArray[1];
							String sampleCode=testCodeArray[2];
							String requisitionDNo=testCodeArray[3];
							String labCode=testCodeArray[4];
							String billNo=testCodeArray[5];
							String testCode=testCodeArray[6];
							String sampleName=testCodeArray[7];
							int index=Integer.parseInt(testCodeArray[8]);
							String samplenoConfig=testCodeArray[9];
							String patType=testCodeArray[22];
							 String sampleFormate=testCodeArray[15];
							 String initDate=testCodeArray[16];
							 String noofSegDigits=testCodeArray[17];
							 String fromSeries=testCodeArray[18];
							 String toSeries=testCodeArray[19];
							 String initType=testCodeArray[20];
							 String runningSampleNo=testCodeArray[21];
							 String requisitionDate=testCodeArray[12];
							 String labName=testCodeArray[11];
							 
							 String configLab=testCodeArray[23];
							 String configType=testCodeArray[24];
							 String configSeq=testCodeArray[25];
							 String configTest=testCodeArray[26];
							 String configArea=testCodeArray[27];
							
							 String testname=testCodeArray[13];;
							
							 
							Map<String,Map<String,List<Inv_SampleCollectionVO>>> mpReqNo= mp.get(crNo);
							
							
							// First Time Insertion 
							if(mpReqNo==null)
							{
								mpReqNo= new LinkedHashMap<String,Map<String,List<Inv_SampleCollectionVO>>>();
								
								Map<String,List<Inv_SampleCollectionVO>> mpSample=new LinkedHashMap<String,List<Inv_SampleCollectionVO>>();
								
								List<Inv_SampleCollectionVO> lstSample=new ArrayList<Inv_SampleCollectionVO>();
								Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
								
								
								if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
									
								{
									voSample.setTempSampleNo(_fb.getSampleNo()[index]);
								}
								else
								{
									voSample.setTempSampleNo(samplenoConfig);
								}
								//Setting VO Values from labStringArray
								voSample.setPatCRNo(crNo);
								voSample.setSampleCode(sampleCode);
								voSample.setRequisitionDNo(requisitionDNo);
								voSample.setRequisitionNo(requisitionNo);
								voSample.setLabCode(labCode);
								voSample.setPatMobile(mobileNo);
								voSample.setPatEmail(emailId);
								voSample.setPatAddress(patAddress);
								voSample.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode()) ;
								
								voSample.setSampleAreaCode(collAreaCode);
								voSample.setPrintStatus(printStatus);
								voSample.setSampleQnty(_fb.getSampleQnty()[index]);
								voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
								voSample.setDefaultmachineCode(_fb.getDefaultmachineCode()[index]);
								voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
								voSample.setTypeOfComponent("1"); // Need to Discuss
								 
								
								
								voSample.setBillNo(billNo);
								voSample.setTestCode(testCode);
								voSample.setSampleName(sampleName);
								
								voSample.setCheckAutoLabConfig(samplenoConfig);
								voSample.setTestName(testname);
								voSample.setPatType(patType);
								// Still Some values need to be inserted
								/*String sampleFormate=testCodeArray[13];
								 String initDate=testCodeArray[14];
								 String noofSegDigits=testCodeArray[15];
								 String fromSeries=testCodeArray[16];
								 String toSeries=testCodeArray[17];
								 String initType=testCodeArray[18];
								 String runningSampleNo=testCodeArray[19];*/
								
								voSample.setSampleNoFormat(sampleFormate);
								voSample.setInitDate(initDate);
								voSample.setNoOfSeqDigit(noofSegDigits);
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
								//Set setRequisitions=mpReqNo.keySet();
								
								//Iterator itrReqNo=setRequisitions.iterator();
								
								//while(itrReqNo.hasNext())
								//{
									//String reqNo=(String)itrReqNo.next();
									
									//Getting Map of Sample Codes
									Map<String,List<Inv_SampleCollectionVO>> mpSample=mpReqNo.get(requisitionNo);
									
									// First Time Insertion 
									if(mpSample==null)
									{
										 	mpSample=new LinkedHashMap<String,List<Inv_SampleCollectionVO>>();
											
											List<Inv_SampleCollectionVO> lstSample=new ArrayList<Inv_SampleCollectionVO>();
											Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
											
											//Setting VO Values from labStringArray
											voSample.setPatCRNo(crNo);
											voSample.setSampleCode(sampleCode);
											voSample.setRequisitionDNo(requisitionDNo);
											voSample.setRequisitionNo(requisitionNo);
											voSample.setLabCode(labCode);
											voSample.setPatMobile(mobileNo);
											voSample.setPatEmail(emailId);
											voSample.setPatAddress(patAddress);
											voSample.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode()) ;
											
											
											voSample.setSampleAreaCode(collAreaCode);
											voSample.setPrintStatus(printStatus);
											voSample.setSampleQnty(_fb.getSampleQnty()[index]);
											voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
											voSample.setDefaultmachineCode(_fb.getDefaultmachineCode()[index]);
											voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
											voSample.setTypeOfComponent("1"); // Need to Discuss
											
											voSample.setTestName(testname);			 
											voSample.setBillNo(billNo);
											voSample.setTestCode(testCode);
											voSample.setSampleName(sampleName);
											 voSample.setRequisitionDate(_fb.getRequisitionDate());
		                                      voSample.setLabName(_fb.getLabName());
		                                      
		                                 
		                                      
		                                      
											// Still Some values need to be inserted
											

											if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
												
											{
												voSample.setTempSampleNo(_fb.getSampleNo()[index]);
											}
											else
											{
												voSample.setTempSampleNo(samplenoConfig);
											}
											
											voSample.setCheckAutoLabConfig(samplenoConfig);
											voSample.setPatType(patType);
											
											voSample.setSampleNoFormat(sampleFormate);
											voSample.setInitDate(initDate);
											voSample.setNoOfSeqDigit(noofSegDigits);
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
											//Set setSamples=mpSample.keySet();
										
											//Iterator itrSamples=setSamples.iterator();
										
											//while(itrSamples.hasNext())
											//{
												//String tmpSampleCode=(String)itrSamples.next();  // sampleCode#labCode
												
												List<Inv_SampleCollectionVO> lstSample=mpSample.get(sampleCode+"#"+labCode);
												if(lstSample==null||lstSample.size()==0) // First Time Insertion
												{
													lstSample=new ArrayList<Inv_SampleCollectionVO>();
													Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
													
													//Setting VO Values from labStringArray
													voSample.setPatCRNo(crNo);
													voSample.setSampleCode(sampleCode);
													voSample.setRequisitionDNo(requisitionDNo);
													voSample.setRequisitionNo(requisitionNo);
													voSample.setLabCode(labCode);
													voSample.setPatMobile(mobileNo);
													voSample.setPatEmail(emailId);
													voSample.setPatAddress(patAddress);
													voSample.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode()) ;
													
													voSample.setTestName(testname);
													voSample.setSampleAreaCode(collAreaCode);
													voSample.setPrintStatus(printStatus);
													voSample.setSampleQnty(_fb.getSampleQnty()[index]);
													voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
													voSample.setDefaultmachineCode(_fb.getDefaultmachineCode()[index]);
													voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
													voSample.setTypeOfComponent("1"); // Need to Discuss
													
											 
													voSample.setBillNo(billNo);
													voSample.setTestCode(testCode);
													voSample.setSampleName(sampleName);
													// Still Some values need to be inserted
													if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
														
													{
														voSample.setTempSampleNo(_fb.getSampleNo()[index]);
													}
													else
													{
														voSample.setTempSampleNo(samplenoConfig);
													}
													
													voSample.setCheckAutoLabConfig(samplenoConfig);
													voSample.setPatType(patType);
													
													voSample.setSampleNoFormat(sampleFormate);
													voSample.setInitDate(initDate);
													voSample.setNoOfSeqDigit(noofSegDigits);
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
													voSample.setPatCRNo(crNo);
													voSample.setSampleCode(sampleCode);
													voSample.setRequisitionDNo(requisitionDNo);
													voSample.setRequisitionNo(requisitionNo);
													voSample.setLabCode(labCode);
													voSample.setPatMobile(mobileNo);
													voSample.setPatEmail(emailId);
													voSample.setPatAddress(patAddress);
													voSample.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode()) ;
													
													voSample.setTestName(testname);
													voSample.setSampleAreaCode(collAreaCode);
													voSample.setPrintStatus(printStatus);
													voSample.setSampleQnty(_fb.getSampleQnty()[index]);
													voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
													voSample.setDefaultmachineCode(_fb.getDefaultmachineCode()[index]);
													voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
													voSample.setTypeOfComponent("1"); // Need to Discuss
													
													 
													voSample.setBillNo(billNo);
													voSample.setTestCode(testCode);
													voSample.setSampleName(sampleName);
													
													// Still Some values need to be inserted
													
													if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
														
													{
														voSample.setTempSampleNo(_fb.getSampleNo()[index]);
													}
													else
													{
														voSample.setTempSampleNo(samplenoConfig);
													}
													
													
													voSample.setCheckAutoLabConfig(samplenoConfig);
													voSample.setPatType(patType);
													voSample.setSampleNoFormat(sampleFormate);
													voSample.setInitDate(initDate);
													voSample.setNoOfSeqDigit(noofSegDigits);
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
												
												//Putting list in Map of SampleCodes
												mpSample.put(sampleCode+"#"+labCode, lstSample);
											//}
									}
									
									//Putting Map of Samples in Map of Requisitions
									mpReqNo.put(requisitionNo, mpSample);
									
													
								//	}// end while
									
								} // end main else
								
								// Putting Map of Requisitions in Map of CrNo's  => currently only one CR No is allowed
								mp.put(crNo, mpReqNo);
							
							}// end for loop
							
							
						 
						 List listReqdtlId=SampleCollectionDATA.saveSampleCollectionDetails(mp, _userVO);
						  		    StringBuffer htmlContents=new StringBuffer();
						 
						  		  
						  	/*//send msg
						  		   
						  		  //msgBuilder=_fb.getLabTestCodeArray().split("@");*/
						  		  /*StringBuffer msg=new StringBuffer();
						  		msg.append("your insruction for lab " );
						  		String[] selectedLabTestCodeArray1=_fb.getChkSamplePatient(); 
						  		  for(int k=0;k<selectedLabTestCodeArray1.length;k++)
									 {
										 
										 String[] value=selectedLabTestCodeArray1[k].split("#");
										 msg.append( value[27] +"is " + value[26]);
										 msg.append(",");
										 msg.setLength(msg.length() - 1);							 
										 
									 }*/
									 
										//String   message  = "Hello "+_fb.getPatName()+","+ msg.toString()+".Thanks and Regards.NIMS";
					            	/*	 System.out.println("chand"+msg);
									
						  		    */
						  		    
						  		    
						 
						 	StringBuilder str = new StringBuilder();	
						 //	str.append( "<br><table width='100%' border='1'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><u> ");
							//str.append( "<br>Sample Collection Dtls::" + "<br>");
						 	HashMap<String, byte[]> t = new HashMap<String, byte[]>();
						 	for(int i=0;i<listReqdtlId.size();i++)
						 	{
						 		String saveString=(String)listReqdtlId.get(i);
						 		String[] arrSaveString=saveString.split("#");
						 		
						 		boolean flg=false;
						 		String samplenoo=arrSaveString[2];
						 		String samplenoomachine=arrSaveString[2];

						 		if(arrSaveString.length==12)
						 		{
						 			flg=true;
						 			samplenoo=arrSaveString[2]+""+arrSaveString[11]+"";
						 		}
						 		
						 		
						 		if(arrSaveString.length==11)
						 		{
						 			flg=true;
						 			samplenoo=arrSaveString[2]+""+arrSaveString[10]+"";
						 		}
						 		
						 		str.append( " Sample Collected Succesfully for Patient CR Number::	");
						 		str.append((arrSaveString[0]));
						 		str.append( " having Sample Name::	");
						 		str.append((arrSaveString[1]));
						 		str.append( " and Sample No::	");
						 		str.append((arrSaveString[2]));
						 		//str.append( "<br>");
						 		str.append( " and Test Name::	");
						 		str.append((arrSaveString[9]));
						 		str.append( "<br>");
						 		
						 		//START BAR CODE LOGIC ADDED BY PATHAN BASHA ON 14-07-2015
						 		String barCodeGenSiString=String.valueOf(listReqdtlId.size());
						 		


						 	//	Barcode barcode = BarcodeFactory.createCode128(arrSaveString[3]);

					 		/*if((arrSaveString[3].length()%2)==1){
						 			arrSaveString[3]=arrSaveString[3];
						 		}*/
						 		System.out.println("len:"+arrSaveString[6].length());
						 		System.out.println("tempsampleno:"+arrSaveString[6]);
						 		 
						 		/*Linear linear = new Linear(); 
						 		    linear.setType(Linear.INTERLEAVED25); 
						 		     linear.setData("0123456789012"); 
						 		    linear.renderBarcode("c:/barcode.gif"); 
						 		Std2of5Barcode barcode = new Std2of5Barcode(arrSaveString[6],true);
						 		//Int2of5Barcode barcode = new Int2of5Barcode(arrSaveString[3]);

						 		 barcode.setBarWidth(1);
				      		       barcode.setResolution(203);
				      		       barcode.setBarHeight(15);
				      		      */ 
						 		
						 		OutputStream os=new ByteArrayOutputStream();
				      		       
						 		Barcode     barcode = BarcodeFactory.createCode128(arrSaveString[6]);
				     			
						 		barcode.setBarWidth(1);
				      		       barcode.setResolution(203);
				      		       //barcode.setBarHeight(10);
				  			  
				      		     Font font=new Font("Plain",Font.PLAIN,0);
				      		       barcode.setFont(font);
				      		     BarcodeImageHandler.writePNG(barcode, os);
									
				      		  
				      		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
				      			 byte[] data=bos.toByteArray();
				      			 t.put(arrSaveString[6], data);
				      			
				      			try {
				      				bos.flush();
									os.flush();
									bos.close();
									os.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				      		    //System.out.println("cho"+data.toString());
				      		  
				      		  

/*							 		htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
					            htmlContents.append("<tr><td width='50%'  ><div id='"+i+"diivBarCodeControl'><img style='margin-left:-13px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:-25px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
					            htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='"+i+"diivBarCodeControl'><center>"+arrSaveString[6]+"</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='"+i+"diivBarCodeControlAll'><center>"+arrSaveString[6]+"</center></div></td></tr>");
					            htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[7]+"</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[7]+"</td></tr> </table></td></tr>");

*/
				      			
				      			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				      			Date date = new Date();
				      			String datee=dateFormat.format(date);
				      			String reqdatee=arrSaveString[5];
				      			
				      			
				      			 SimpleDateFormat sdf = 	new SimpleDateFormat("dd-MMM-yyyy");
								 SimpleDateFormat sdf2 = 	new SimpleDateFormat("dd-MMM-yyyy");

							   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy ");
							   SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yy ");
							   String reqdateee="";
							   String colldateee="";
							   try {
									Date date1 = sdf.parse(reqdatee);
									reqdateee=sdf1.format(date1);
									Date date2 = sdf2.parse(datee);
									colldateee=sdf3.format(date2);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									
								}
				      		
				      		if(_userVO.getHospitalCode().equals("96101") || _userVO.getHospitalCode().equals("10911"))
				      			{
				      			
							   /*boolean flgg=false;	
							   if(flgg)
				      			{*/
							 		htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
						            htmlContents.append("<tr><td width='50%'  ><div id='"+i+"diivBarCodeControl'><img style='margin-left:-13px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+arrSaveString[6]+"\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:-25px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+arrSaveString[6]+"\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
						            htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='"+i+"diivBarCodeControl'><center>"+arrSaveString[6]+"</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='"+i+"diivBarCodeControlAll'><center>"+arrSaveString[6]+"</center></div></td></tr>");
						            
						            htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"/"+datee+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patName+"</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"/"+datee+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patName+"</td></tr> </table></td></tr>");
						            
						            
						            
						            htmlContents.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
								
							 		

						        
				      			}
				      			else
				      			{
				      				
				      				String is_sammple_duplicate_print= SampleCollectionDATA.getsamplebarcodeconfig(_userVO);
				      				
				      			   htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
					               

					               if(arrSaveString[6]!=null && (arrSaveString[6].contains("F") || arrSaveString[6].contains("A") || arrSaveString[6].contains("R") || arrSaveString[6].contains("B") || arrSaveString[6].contains("P")))
					               {
					            	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
					            		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
					            	   else
					            		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:160px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
					               
					            	   	
					               }   		
					               else
					               {   
					               
					            	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
							           htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
					            	   else
					            		 htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:140px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
					            		   
					               }
					               
					               if (flg) {
					                    
					            	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
									   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:45'>" + samplenoo + "</div></td></tr>");
					            	   else
					            		 htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:45'>" + samplenoo + "</div></td><td width='50%' align='left' style='font-size:12px;'><div id='" + i + "diivBarCodeControlAll' style='margin-left:50'>" + samplenoo + "</div></td></tr>");
					            		   
					                }
					                else {
					                   
					             	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
										htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:45'>" + arrSaveString[6] + "</div></td></tr>");
					             	   else
					             		  htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:45'>" + arrSaveString[6] + "</div></td><td width='50%' align='left' style='font-size:12px;'><div id='" + i + "diivBarCodeControlAll' style='margin-left:50'>" + arrSaveString[6] + "</div></td></tr>");
					             		   
					             	   
					                }
					                
					                
					               if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
									htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[5] + "/" + datee + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patName + "</td></tr> </table></td></tr>");
					               else
					            	htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[5] + "/" + datee + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patName + "</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:8px;'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[5] + "/" + datee + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patName + "</td></tr> </table></td></tr>");
					            	   
					               
					               		htmlContents.append("<input type='hidden' id='barCodeGenSize' value='" + barCodeGenSiString + "' name='barCodeGenSize'/></table>");
							 		
						      
						            
						            
									
									
									
						            
				      			}
				      			
						    		    
						 		   
					        	 		
						 		/*str.append( "<br><table width='90%' border='1'><tr>");              
							    
							
							
						   	
						 		   
						 		str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
								str.append( " Sample Collected Succesfully for Patient CR Number::" + "</font></td>");
								str.append( "<td width='20%' align='left' >");
								str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
								str.append((arrSaveString[0])+ "</font>" + "</td><tr>");
								
								str.append("<tr><td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
								str.append( "Sample Name::" + "</font></td>");
								str.append( "<td width='20%' align='left' >");
								str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
								str.append((arrSaveString[1])+ "</font>" + "</td>");
								
								str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
								str.append( "Sample No::" + "</font></td>");
								str.append( "<td width='20%' align='left' >");
								str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
								str.append((arrSaveString[2])+ "</font>" + "</td>");
								
								
								str.append("</tr></table>");*/
						 		
						 		
						 		
						 	}
						 	
						 	session.setAttribute(InvestigationConfig.UPLOADED_FILE_AS_ARRAY, t); 
						 	session.setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
						 	_fb.setSaveConfirmFlag("1");
						 //	str.append("</table>");
						 	  System.out.println("html Contents"+htmlContents);
							objStatus.add(Status.NEW,str.toString(),
									"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
											+ "Sample Collection Successful" + "<br></font>");
						
							
			   
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
			objStatus.add(Status.ERROR_AE, "", "There was Some Problem.Please Try Again");
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
	
	
		public static void showPatDetails(SampleCollectionFB fb,HttpServletRequest request)
		{
			Status status = new Status();
			HttpSession session=request.getSession();
			UserVO _userVO=getUserVO(request);
			Map mp=new HashMap();
			boolean flag=false;
			String staffImage="";
			
			try{
				List<Inv_SampleCollectionVO> lstsamplePatinetForSampleCollectionVO=null;
				List<String> reqList=new ArrayList();
				Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
				
				List<String> staffDetails=new ArrayList();
				
				//fb.setisPatDetailPage("1");
				lstsamplePatinetForSampleCollectionVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				String selectedCheckBoxValue=fb.getSelectedCheckbox();
				
				String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
				
				String crNo=arrSelectedRequisitions[0].split("#")[0];
				String reqNO=arrSelectedRequisitions[0].split("#")[1];

				
					for(Inv_SampleCollectionVO objSampleCollectionVO:lstsamplePatinetForSampleCollectionVO)
					{
						if(flag) break;
						if(objSampleCollectionVO.getPatCRNo().equals(crNo)&&objSampleCollectionVO.getRequisitionNo().equals(reqNO))
						{
							//WebUTIL.populate(fb,objSampleCollectionVO); 
							WebUTIL.setAttributeInSession(request,InvestigationConfig.SELECTED_PAT_DETAILS,objSampleCollectionVO);
							voSample=objSampleCollectionVO;
							flag=true;
							break;
						}
					}
					
					for(String str:arrSelectedRequisitions)
						reqList.add(str);
					
					//WebUTIL.populate(fb,voSample); 
					
					//Billed Transactions
					mp=SampleCollectionDATA.getBilledPatList(reqList, voSample, _userVO);
					
					WebUTIL.setMapInSession(mp, request);
					
					session.setAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE, "");
					
			/*
			 * staffDetails=SampleCollectionDATA.getStaffDetails(crNo,_userVO);
			 * 
			 * if(staffDetails != null){
			 * 
			 * staffImage =
			 * getStaffImage(staffDetails.get(0),staffDetails.get(1),staffDetails.get(2));
			 * 
			 * if(staffImage != null){ if(!(staffImage.equals("0"))){
			 * 
			 * session.setAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE, staffImage);
			 * } }
			 * 
			 * }
			 */
						
					
					
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
	
		public static String getStaffImage(String empId,String slNo,String hosCode){
			
			String base64EncodedImage = "";
			String args = empId+"/"+slNo+"/"+hosCode;
			StringBuffer buf=new StringBuffer();
			
			try {

		  		//URL url = new URL("http://10.226.2.186:8780/HBIMS/services/restful/hisImageRetrievalUtil/"+args);
				URL url = new URL(InvestigationConfig.URL_STAFF_DEPENDENT_IMAGE+args);
				System.out.println("URL STAFF DEPENDENT IMAGE -> "+url);
		  		
		  		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		  		conn.setRequestMethod("GET");
		  		conn.setRequestProperty("Accept", "application/json");

		  		if (conn.getResponseCode() != 200) {
		  			throw new RuntimeException("Failed : HTTP error code : "
		  					+ conn.getResponseCode());
		  		}

		  		
		  		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		  		String output;
		  		//System.out.println("Output from Server .... \n");
		  		while ((output = br.readLine()) != null) {
		  			//System.out.println(output);
		  			buf.append(output);
		  		}

		  		JSONParser parser = new JSONParser();
		  		
		  		JSONObject jsonObject = (JSONObject) parser.parse(buf.toString());
	  			
		  		//String name = (String) jsonObject.get("source");
		        //System.out.println(name);
		  		
	  			JSONArray getArray = (JSONArray) jsonObject.get("data");
	  			//System.out.println(getArray);
	  			
	  			
	  			for (int i = 0; i < getArray.size(); i++) {
		            JSONObject objects = (JSONObject) getArray.get(i);
		            Set keyS = objects.keySet();
		            
		            Iterator<String> key = keyS.iterator();
		            while (key.hasNext()) {
		                String k = key.next().toString();
		                if(k.equalsIgnoreCase("HRGBYTE_IMAGE"))
		                {
		                	//System.out.println("Key : " + k + ", value : " + objects.getString(k));
		                	base64EncodedImage=(String) objects.get(k);
		                }
		                
		            }
		        }
	  			
		  		conn.disconnect();

		  	  } catch (MalformedURLException e) {

		  		e.printStackTrace();

		  	  } catch (IOException e) {

		  		e.printStackTrace();

		  	  } catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return base64EncodedImage;
		}
		
		
		
		/**
		 * AJAX Response : Checking Duplicacy of Sample No
		 * @param objFB_p
		 * @param objRequest_p
		 * Added By Pawan Kumar B N  on 2011.12.21
		 */
		public static StringBuffer checkSampleNoDuplicacy(SampleCollectionFB fb, HttpServletRequest objRequest_p)
		{
			StringBuffer sbAjaxRes = new StringBuffer();
			String strMsgText = "";
			try
			{
				UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
				
				Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
				
				//Setting Area Code and Sample No
				voSampleCollection.setSampleAreaCode(fb.getSampleAreaCode());
				voSampleCollection.setTempSampleNo(fb.getStrSampleNo());

				boolean isTempSamplePresent = SampleCollectionDATA.checkSampleNoDuplicacy(voSampleCollection, voUser);
				//sbAjaxRes.append("[{isTempSamplePresent:\'");
				sbAjaxRes.append(isTempSamplePresent);
				//sbAjaxRes.append("\'");
				//sbAjaxRes.append("}]");
				
				
			}
			catch (Exception e)
			{
				strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
				//HisException eObj = 
				new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
				//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				//eObj = null;
			}
			finally
			{
			}
			return sbAjaxRes;
		}
		
		
		
		/**
		 * AJAX Response : Checking SampleNO Formate Is Configure Or Not  
		 * @param objFB_p
		 * @param objRequest_p
		 * Added By Pathan Basha  on 2015.06.24
		 */
		public static StringBuffer checkAutoGenFormate(SampleCollectionFB fb, HttpServletRequest objRequest_p)
		{
			StringBuffer sbAjaxRes = new StringBuffer();
			String strMsgText = "";
			String  strFormate="";
			Map mp=new HashMap();
			try
			{
				UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
				
				Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
				
				//LabCod ,TestCode,patType And TempSampleNo  
				voSampleCollection.setLabCode(fb.getLabCode());
				voSampleCollection.setTestCode(fb.getTestCode());
				voSampleCollection.setPatType(fb.getPatType());
				voSampleCollection.setTempSampleNo(fb.getTempSampleNo());
				voSampleCollection.setConfigArea(fb.getSampleAreaCode());
				
				
				strFormate = SampleCollectionDATA.checkAutoGenFormate(voSampleCollection, voUser);
				//sbAjaxRes.append("[{isTempSamplePresent:\'");
				 
				sbAjaxRes.append(strFormate);
				
				 
				//sbAjaxRes.append("\'");
				//sbAjaxRes.append("}]");
				
				
			}
			catch (Exception e)
			{
				strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
				//HisException eObj = 
				new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
				//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				//eObj = null;
			}
			finally
			{
			}
			return sbAjaxRes;
		}
		
		
		
		//new method for raising cum sample collection
		
		
		/**
		 * AJAX Response : Checking Duplicacy of Sample No
		 * @param objFB_p
		 * @param objRequest_p
		 * Added By Pawan Kumar B N  on 2011.12.21
		 */
		public static StringBuffer checkSampleNoDuplicacyRaisingCumSampleCollection(invRaisingCumSamCollectionFB fb, HttpServletRequest objRequest_p,String sampleNo, String sampleAreaCode)
		{
			StringBuffer sbAjaxRes = new StringBuffer();
			String strMsgText = "";
			try
			{
				UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
				
				Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
				
				//Setting Area Code and Sample No
				voSampleCollection.setSampleAreaCode(sampleAreaCode);
				voSampleCollection.setTempSampleNo(sampleNo);

				boolean isTempSamplePresent = SampleCollectionDATA.checkSampleNoDuplicacy(voSampleCollection, voUser);
				//sbAjaxRes.append("[{isTempSamplePresent:\'");
				sbAjaxRes.append(isTempSamplePresent);
				//sbAjaxRes.append("\'");
				//sbAjaxRes.append("}]");
				
				
			}
			catch (Exception e)
			{
				strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
				//HisException eObj = 
				new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
				//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				//eObj = null;
			}
			finally
			{
			}
			return sbAjaxRes;
		}
		
		
		public static void duplicateBarCodeDetails(SampleCollectionFB fb,HttpServletRequest request) throws BarcodeException, OutputException
		{
			
			HttpSession session=request.getSession();
			StringBuffer htmlContents=new StringBuffer();
			UserVO _userVO=getUserVO(request);

			HashMap<String, byte[]> t = new HashMap<String, byte[]>();
			for(int i=0;i<fb.getChkSamplePatient().length;i++)
			{
				
				boolean flg=false;
				String[] values=null;
				if(fb.getChkSamplePatient()[i].split("#").length==9)
				{
					values=new String[9];
					values=fb.getChkSamplePatient()[i].split("#");
					flg=true;
				}
				else
				{
					values=new String[8];
					values=fb.getChkSamplePatient()[i].split("#");
				}
				
					
				String sampleno=values[2];
				String crno=values[0];
				String samplename=values[1];
				String reqdate=values[5];
				String labname=values[4];
				String reqdateee = "";  
				String colldate=values[6];
				String colldatee="";
				
				 SimpleDateFormat sdf = 	new SimpleDateFormat("dd-MMM-yyyy");
				 SimpleDateFormat sdf2 = 	new SimpleDateFormat("dd-MMM-yyyy");

			   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy ");
			   SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yy ");
			   
			   try {
				Date date = sdf.parse(reqdate);
				reqdateee=sdf1.format(date);
				Date date1 = sdf2.parse(colldate);
				colldatee=sdf3.format(date1);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   
				String sugartestcodesampleno="";
				
				if(flg==true)
				{
					sugartestcodesampleno=values[8];
					
				}
				
				String patname="";
				
					if(values[7]!=null && values[7].trim().length()>10)
				 patname=values[7].trim().substring(0,10);
					else
						patname=values[7].trim();		
				
				
				String barCodeGenSiString=String.valueOf(fb.getChkSamplePatient().length);
				
				 OutputStream os=new ByteArrayOutputStream();
				Barcode     barcode = BarcodeFactory.createCode128(sampleno);
     			
		 		barcode.setBarWidth(1);
      		       barcode.setResolution(203);
      		       //barcode.setBarHeight(10);
  			  
      		     Font font=new Font("Plain",Font.PLAIN,0);
      		       barcode.setFont(font);
      		     BarcodeImageHandler.writePNG(barcode, os);
					
      		  
      		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
      			 byte[] data=bos.toByteArray();
      			
      			t.put(sampleno, data);
      		    //System.out.println("cho"+data.toString());
      		  
      		  
               try {
					bos.flush();
					os.flush();
					bos.close();
	                os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              
		 		/*htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
	            htmlContents.append("<tr><td width='50%'  ><div id='"+i+"diivBarCodeControl'><img style='margin-left:-13px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:-25px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
	            htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='"+i+"diivBarCodeControl'><center>"+sampleno+"</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='"+i+"diivBarCodeControlAll'><center>"+sampleno+"</center></div></td></tr>");
	            htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+crno+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+labname+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>"+reqdate+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>"+samplename+"</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+crno+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+labname+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>"+reqdate+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>"+samplename+"</td></tr> </table></td></tr>");

		 	*/
      			
      			if(_userVO.getHospitalCode().equals("96101") || _userVO.getHospitalCode().equals("10911"))
      			{
              /* boolean flgg=false;
               if(flgg)
               {*/
            	   
		 		htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
	            htmlContents.append("<tr><td width='50%'  ><div id='"+i+"diivBarCodeControl'><img style='margin-left:-13px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+sampleno+"\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:-25px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+sampleno+"\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
	            htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='"+i+"diivBarCodeControl'><center>"+sampleno+"</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='"+i+"diivBarCodeControlAll'><center>"+sampleno+"</center></div></td></tr>");
	            htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+crno+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+labname+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Coll Date:</b></td><td height='8' style='font-size:9px;'>"+reqdate+"/"+colldate+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patname+"</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+crno+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+labname+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Coll Date:</b></td><td height='8' style='font-size:9px;'>"+reqdate+"/"+colldate+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patname+"</td></tr> </table></td></tr>");

	            
	            htmlContents.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
      			}
      			else
      			{
		 		
      				String is_sammple_duplicate_print= SampleCollectionDATA.getsamplebarcodeconfig(_userVO);
    				//String is_sammple_duplicate_print= "1";

    				
    		    		    
                   htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
               
                   if(sampleno!=null && (sampleno.contains("F") || sampleno.contains("A") || sampleno.contains("R") || sampleno.contains("B") || sampleno.contains("P")))
                   {
                	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
                		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
                		   else      
                	     htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:160px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image fsound in session\"   width='100%' ></div></td></tr>");
                   }else
                   {
                	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
                		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
                		   else
                	   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:140px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image fsound in session\"   width='100%' ></div></td></tr>");
                   }
                   
                   if (flg) {
                   
                	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
                		   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:45'>" + sampleno + sugartestcodesampleno + "</div></td></tr>");
                		   else
                	   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:45'>" + sampleno + sugartestcodesampleno + "</div></td><td width='50%' align='left' style='font-size:12px;'><div id='" + i + "diivBarCodeControlAll' style='margin-left:40'>" + sampleno + sugartestcodesampleno + "</div></td></tr>");
                   }
                   else {
                       
                	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
                		   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + sampleno + "</div></td></tr>");
                		   else
                	   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + sampleno + "</div></td><td width='50%' align='left' style='font-size:12px;'><div id='" + i + "diivBarCodeControlAll' style='margin-left:80'>" + sampleno + "</div></td></tr>");
                   }
                   
                   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
                	   htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + reqdate + "/" + colldate + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patname + "</td></tr> </table></td></tr>");
                	   else
                   htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + reqdate + "/" + colldate + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patname + "</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:8px;'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + reqdate + "/" + colldate + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patname + "</td></tr> </table></td></tr>");
                   
                   
                   htmlContents.append("<input type='hidden' id='barCodeGenSize' value='" + barCodeGenSiString + "' name='barCodeGenSize'/></table>");
    		 		
    		 		
    		 		
	          
      			} 	    
		 		   
	        	 		
		 		
		 		
		 		
		 		
		 	}
		 	
			session.setAttribute(InvestigationConfig.UPLOADED_FILE_AS_ARRAY, t); 
		 	session.setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
		 	fb.setSaveConfirmFlag("1");
		 //	str.append("</table>");
		 	  System.out.println("html Contents"+htmlContents);
				
			}
			

		
		public static StringBuffer getRequisitionFormMasterData(SampleCollectionFB fb, HttpServletRequest objRequest_p)
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
				String testCode=fb.getTestCode();
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
		
		//added by krishnan nema on 25042019
		public static void getPatListSampleColAdvance(SampleCollectionFB fb,HttpServletRequest request)
		{
			Status status = new Status();
			//status.add(Status.NEW, "", "");
			HttpSession session=request.getSession();
		try{
			List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
			
			lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
			Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
		
		//	WebUTIL.populate(objSampleCollectionVO,fb);
			if(fb.getPatCrNo()!=null && !fb.getPatCrNo().equals(""))
				objSampleCollectionVO.setPatCRNo(fb.getPatCrNo());
			
			if(fb.getPatCRNo()!=null && !fb.getPatCRNo().equals(""))
			objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
			objSampleCollectionVO.setFromDate(fb.getFromDate());
			objSampleCollectionVO.setToDate(fb.getToDate());
			
			objSampleCollectionVO.setSampleAreaCode(fb.getSampleAreaCode());
			objSampleCollectionVO.setWardCode(fb.getWardCode());

			session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
			session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
			
			session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
			session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
			
			session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
			session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
			
			
			
			//InvestigationConfig.SELECTED_PAT_DETAILS
			
			UserVO userVO=ControllerUTIL.getUserVO(request);
			lstsamplePatinetVO=SampleCollectionDATA.getPatListSampleColAdvance(objSampleCollectionVO,userVO);
			
			
				//if(lstsamplePatinetVO!=null)
				//{
					//WebUTIL.populate(fb,lstsamplePatinetVO.get(0)); 
					WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
					
				
					
				//}
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
	
//Added by prachi for patient dtl tile
		
		public static JsonObject AjaxGetPatDetails(SampleCollectionFB fb, HttpServletRequest request) {
			
			Status objStatus = new Status();
			InvTrackingReportVO vo = new InvTrackingReportVO();
			Map mp=new HashMap();
			
			JsonObject jsonResponse = new JsonObject();
			JsonArray patDetailsObjectRowContainer = new JsonArray();
			
			try {
				fb.setSearchType("1");    //CrNo based patient search
				UserVO userVO = ControllerUTIL.getUserVO(request);
				HelperMethods.populate(vo, fb);
				ControllerUTIL.setSysdate(request);
				
				mp=InvTrackingReportDATA.AjaxGetPatDetails(vo, userVO);
				InvTrackingReportVO vo2 = (InvTrackingReportVO) mp.get("patDetails");
				
				if(vo2!=null) {
				JsonObject objectRow = new JsonObject();

				/*OPD Patient Data*/
				objectRow.addProperty("patientName", vo2.getPatName()==null?"":vo2.getPatName());
				objectRow.addProperty("crNumber", vo2.getCrNumber()==null?"":vo2.getCrNumber());
				objectRow.addProperty("isUnknown", vo2.getIsUnknown()==null?"":vo2.getIsUnknown());
				objectRow.addProperty("isDead", vo2.getIsDead()==null?"":vo2.getIsDead());
				objectRow.addProperty("isMlc", vo2.getIsMlc()==null?"":vo2.getIsMlc());
				objectRow.addProperty("patFirstName", vo2.getPatFirstName()==null?"":vo2.getPatFirstName());
				objectRow.addProperty("patMiddleName", vo2.getPatMiddleName()==null?"":vo2.getPatMiddleName());
				objectRow.addProperty("patLastName", vo2.getPatLastName()==null?"":vo2.getPatLastName());
				objectRow.addProperty("patGuardianName", vo2.getPatGuardianName()==null?"":vo2.getPatGuardianName());
				objectRow.addProperty("patCategoryCode", vo2.getPatCategoryCode()==null?"":vo2.getPatCategoryCode());
				objectRow.addProperty("patAge", vo2.getPatAge()==null?"":vo2.getPatAge());
				objectRow.addProperty("patHusbandName", vo2.getPatHusbandName()==null?"":vo2.getPatHusbandName());
				objectRow.addProperty("patGenderCode", vo2.getPatGenderCode()==null?"":vo2.getPatGenderCode());
				objectRow.addProperty("patCategory", vo2.getPatCategory()==null?"":vo2.getPatCategory());
				objectRow.addProperty("patDOB", vo2.getPatDOB()==null?"":vo2.getPatDOB());
				objectRow.addProperty("isActualDob", vo2.getIsActualDob()==null?"":vo2.getIsActualDob());
				objectRow.addProperty("patGender", vo2.getPatGender()==null?"":vo2.getPatGender());
				objectRow.addProperty("patStatusCode", vo2.getPatStatusCode()==null?"":vo2.getPatStatusCode());
				objectRow.addProperty("patStatus", vo2.getPatStatus()==null?"":vo2.getPatStatus());
				objectRow.addProperty("patMobileNo", vo2.getPatMobileNo()==null?"":vo2.getPatMobileNo());
				objectRow.addProperty("patAddress", vo2.getPatAddress()==null?"":vo2.getPatAddress());
				objectRow.addProperty("isCatExpired", vo2.getIsCatExpired()==null?"":vo2.getIsCatExpired());
				objectRow.addProperty("patEmailId", vo2.getPatEmailId()==null?"":vo2.getPatEmailId());
				 /*IPD Patient Data*/
				objectRow.addProperty("admissionDate", vo2.getAdmissionDate()==null?"":vo2.getAdmissionDate());
				objectRow.addProperty("patDeptUnitCode", vo2.getPatDeptUnitCode()==null?"":vo2.getPatDeptUnitCode());
				objectRow.addProperty("patVisitNo", vo2.getPatVisitNo()==null?"":vo2.getPatVisitNo());
				objectRow.addProperty("patEpisodeCode", vo2.getPatEpisodeCode()==null?"":vo2.getPatEpisodeCode());
				objectRow.addProperty("admittedDepartmentCode", vo2.getAdmittedDepartmentCode()==null?"":vo2.getAdmittedDepartmentCode());
				objectRow.addProperty("patAdmissionNo", vo2.getPatAdmissionNo()==null?"":vo2.getPatAdmissionNo());
				objectRow.addProperty("patDeptUnit", vo2.getPatDeptUnit()==null?"":vo2.getPatDeptUnit());
				objectRow.addProperty("admittedDepartmentName", vo2.getAdmittedDepartmentName()==null?"":vo2.getAdmittedDepartmentName());
				objectRow.addProperty("patWardCode", vo2.getPatWardCode()==null?"":vo2.getPatWardCode());
				objectRow.addProperty("admittedDepartmentCode", vo2.getAdmittedDepartmentCode()==null?"":vo2.getAdmittedDepartmentCode());
				objectRow.addProperty("patWardName", vo2.getPatWardName()==null?"":vo2.getPatWardName());
				objectRow.addProperty("patRoomNo", vo2.getPatRoomNo()==null?"":vo2.getPatRoomNo());
				objectRow.addProperty("patRoomName", vo2.getPatRoomName()==null?"":vo2.getPatRoomName());
				objectRow.addProperty("bedCode", vo2.getBedCode()==null?"":vo2.getBedCode());
				objectRow.addProperty("bedName", vo2.getBedName()==null?"":vo2.getBedName());
				objectRow.addProperty("hospitalCode", vo2.getHospitalCode()==null?"":vo2.getHospitalCode());
				objectRow.addProperty("consultantName", vo2.getConsultantName()==null?"":vo2.getConsultantName());
				objectRow.addProperty("patMlcNo", vo2.getPatMlcNo()==null?"":vo2.getPatMlcNo());
				objectRow.addProperty("diagnosis", vo2.getDiagnosis()==null?"":vo2.getDiagnosis());
				objectRow.addProperty("patAccNo", vo2.getPatAccNo()==null?"":vo2.getPatAccNo());
						
				patDetailsObjectRowContainer.add(objectRow);
				}
				jsonResponse.add("patDetails", patDetailsObjectRowContainer);

				objStatus.add(Status.TRANSINPROCESS);
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
			return jsonResponse;
			
		}
	
		
public static String AjaxBilledUnbilledDetails(SampleCollectionFB fb, HttpServletRequest request) {
			
			Status objStatus = new Status();
			
			Inv_SampleCollectionVO vo = new Inv_SampleCollectionVO();
			//Map mp=new HashMap();
			String mp = null;
			try {
				
				UserVO userVO = ControllerUTIL.getUserVO(request);
				HelperMethods.populate(vo, fb);
				if(fb.getPatStatusCode().equalsIgnoreCase("1"))   //------OPD
				{
					vo.setReqType(fb.getPatStatusCode());
				}
				if(fb.getPatStatusCode().equalsIgnoreCase("2"))   //-----------IPD
				{
					vo.setReqType(fb.getPatStatusCode());
				}
				if(fb.getPatStatusCode().equalsIgnoreCase("3"))    //---------OPD EMERGENCY
				{
					vo.setReqType(fb.getPatStatusCode());
				}
				if(fb.getPatStatusCode().equalsIgnoreCase("4"))    //-----------OPD SPECIAL
				{
					vo.setReqType(fb.getPatStatusCode());
				}
				
				
				ControllerUTIL.setSysdate(request);
				
				mp=SampleCollectionDATA.AjaxBilledUnbilledDetails(vo, userVO);
				
				objStatus.add(Status.TRANSINPROCESS);
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
				
		
public static String AjaxGetDetails(SampleCollectionFB fb, HttpServletRequest request) {
	
	Status objStatus = new Status();
	Inv_SampleCollectionVO vo = new Inv_SampleCollectionVO();
	String mp = null;
	
		try {
			
			UserVO userVO = ControllerUTIL.getUserVO(request);
			HelperMethods.populate(vo, fb);
			if(fb.getPatStatusCode().equalsIgnoreCase("1"))   //------OPD
			{
				vo.setReqType("2");
			}
			if(fb.getPatStatusCode().equalsIgnoreCase("2"))   //-----------IPD
			{
				vo.setReqType("1");
			}
			if(fb.getPatStatusCode().equalsIgnoreCase("3"))    //---------OPD EMERGENCY
			{
				vo.setReqType("3");
			}
			if(fb.getPatStatusCode().equalsIgnoreCase("4"))    //-----------OPD SPECIAL
			{
				vo.setReqType("4");
			}
			
			
			ControllerUTIL.setSysdate(request);
			
			mp=SampleCollectionDATA.AjaxGetDetails(vo, userVO);
			
		
		objStatus.add(Status.TRANSINPROCESS);
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
	

public static void AJAXsaveSampleCollectionDetails(Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp,SampleCollectionFB _fb,HttpServletRequest _request,HttpServletResponse _response) throws BarcodeException, OutputException, IOException
{
	Status objStatus=new Status();	
	HttpSession session=_request.getSession();
	String[] msgBuilder;
	UserVO _userVO=getUserVO(_request);
			String mobileNo=_fb.getPatMobile()==null?"":_fb.getPatMobile();
			String emailId=_fb.getPatEmail()==null?"":_fb.getPatEmail();
			String patAddress=_fb.getPatAddress()==null?"":_fb.getPatAddress();
			String patName=_fb.getPatName()==null?"":_fb.getPatName().trim();

		   

	try
	{
					 List listReqdtlId=SampleCollectionDATA.saveSampleCollectionDetails(mp, _userVO);
					  		    StringBuffer htmlContents=new StringBuffer();
					 		 	StringBuilder str = new StringBuilder();	
				
					 	HashMap<String, byte[]> t = new HashMap<String, byte[]>();
					 	for(int i=0;i<listReqdtlId.size();i++)
					 	{
					 		String saveString=(String)listReqdtlId.get(i);
					 		String[] arrSaveString=saveString.split("#");
					 		
					 		boolean flg=false;
					 		String samplenoo=arrSaveString[2];
					 		String samplenoomachine=arrSaveString[2];

					 		if(arrSaveString.length==12)
					 		{
					 			flg=true;
					 			samplenoo=arrSaveString[2]+""+arrSaveString[11]+"";
					 		}
					 		
					 		
					 		if(arrSaveString.length==11)
					 		{
					 			flg=true;
					 			samplenoo=arrSaveString[2]+""+arrSaveString[10]+"";
					 		}
					 		
					 		str.append( " Sample Collected Succesfully for Patient CR Number::	");
					 		str.append((arrSaveString[0]));
					 		str.append( " having Sample Name::	");
					 		str.append((arrSaveString[1]));
					 		str.append( " and Sample No::	");
					 		str.append((arrSaveString[2]));
					 		//str.append( "<br>");
					 		str.append( " and Test Name::	");
					 		str.append((arrSaveString[9]));
					 		str.append( "<br>");
					 		
					 		//START BAR CODE LOGIC ADDED BY PATHAN BASHA ON 14-07-2015
					 		String barCodeGenSiString=String.valueOf(listReqdtlId.size());
					 		
					 		System.out.println("len:"+arrSaveString[6].length());
					 		System.out.println("tempsampleno:"+arrSaveString[6]);
					 		OutputStream os=new ByteArrayOutputStream();
			      		       
					 		Barcode     barcode = BarcodeFactory.createCode128(arrSaveString[6]);
			     			
					 		barcode.setBarWidth(1);
			      		       barcode.setResolution(203);
			      		       //barcode.setBarHeight(10);
			  			  
			      		     Font font=new Font("Plain",Font.PLAIN,0);
			      		       barcode.setFont(font);
			      		     BarcodeImageHandler.writePNG(barcode, os);
								
			      		  
			      		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
			      			 byte[] data=bos.toByteArray();
			      			 t.put(arrSaveString[6], data);
			      			
			      			try {
			      				bos.flush();
								os.flush();
								bos.close();
								os.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			      		  	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			      			Date date = new Date();
			      			String datee=dateFormat.format(date);
			      			String reqdatee=arrSaveString[5];
			      			
			      			
			      			 SimpleDateFormat sdf = 	new SimpleDateFormat("dd-MMM-yyyy");
							 SimpleDateFormat sdf2 = 	new SimpleDateFormat("dd-MMM-yyyy");

						   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy ");
						   SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yy ");
						   String reqdateee="";
						   String colldateee="";
						   try {
								Date date1 = sdf.parse(reqdatee);
								reqdateee=sdf1.format(date1);
								Date date2 = sdf2.parse(datee);
								colldateee=sdf3.format(date2);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								
							}
			      		
			      		if(_userVO.getHospitalCode().equals("96101") || _userVO.getHospitalCode().equals("10911"))
			      			{
			      			
						   	htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
					            htmlContents.append("<tr><td width='50%'  ><div id='"+i+"diivBarCodeControl'><img style='margin-left:-13px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+arrSaveString[6]+"\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:-25px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+arrSaveString[6]+"\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
					            htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='"+i+"diivBarCodeControl'><center>"+arrSaveString[6]+"</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='"+i+"diivBarCodeControlAll'><center>"+arrSaveString[6]+"</center></div></td></tr>");
					            
					            htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"/"+datee+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patName+"</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"/"+datee+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patName+"</td></tr> </table></td></tr>");
					            
					            
					            
					            htmlContents.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
							
						 		

					        
			      			}
			      			else
			      			{
			      				
			      				String is_sammple_duplicate_print= SampleCollectionDATA.getsamplebarcodeconfig(_userVO);
			      				
			      			   htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
				               

				               if(arrSaveString[6]!=null && (arrSaveString[6].contains("F") || arrSaveString[6].contains("A") || arrSaveString[6].contains("R") || arrSaveString[6].contains("B") || arrSaveString[6].contains("P")))
				               {
				            	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
				            		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
				            	   else
				            		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:160px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
				               
				            	   	
				               }   		
				               else
				               {   
				               
				            	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
						           htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
				            	   else
				            		 htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:140px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + arrSaveString[6] + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
				            		   
				               }
				               
				               if (flg) {
				                    
				            	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
								   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + samplenoo + "</div></td></tr>");
				            	   else
				            		 htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + samplenoo + "</div></td><td width='50%' align='left' style='font-size:12px;'><div id='" + i + "diivBarCodeControlAll' style='margin-left:80'>" + samplenoo + "</div></td></tr>");
				            		   
				                }
				                else {
				                   
				             	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
									htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + arrSaveString[6] + "</div></td></tr>");
				             	   else
				             		  htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + arrSaveString[6] + "</div></td><td width='50%' align='left' style='font-size:12px;'><div id='" + i + "diivBarCodeControlAll' style='margin-left:80'>" + arrSaveString[6] + "</div></td></tr>");
				             		   
				             	   
				                }
				                
				                
				               if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
								htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[5] + "/" + datee + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patName + "</td></tr> </table></td></tr>");
				               else
				            	htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[5] + "/" + datee + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patName + "</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:8px;'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + arrSaveString[5] + "/" + datee + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patName + "</td></tr> </table></td></tr>");
				            	   
				               
				               		htmlContents.append("<input type='hidden' id='barCodeGenSize' value='" + barCodeGenSiString + "' name='barCodeGenSize'/></table>");
						 		
					          
			      			}
			      			
					    
					 	}
					 	//ResponseMgs
					 	session.setAttribute(InvestigationConfig.UPLOADED_FILE_AS_ARRAY, t); 
					 	session.setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
					 	_fb.setSaveConfirmFlag("1");
					 //	str.append("</table>");
					 	  System.out.println("html Contents"+htmlContents);
							objStatus.add(Status.TRANSINPROCESS);
							WebUTIL.setStatus(_request,objStatus);
					
						_fb.setResponseMgs("Sample Collected Successful");

						_response.getWriter().write(_fb.getResponseMgs());
					//	_response.getWriter().append(htmlContents.toString());
		   
	}
	catch (HisRecordNotFoundException e)
	{
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		System.out.println(e.getMessage());
		_fb.setResponseMgs("There is some problem in sample collection ");
	}
	catch (HisDataAccessException e)
	{
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		System.out.println(e.getMessage());
		_fb.setResponseMgs("There is some problem in sample collection ");
	}
	catch (HisApplicationExecutionException e)
	{
		objStatus.add(Status.ERROR_AE, "", "There was Some Problem.Please Try Again");
		System.out.println(e.getMessage());
		_fb.setResponseMgs("There is some problem in sample collection ");
	}
	catch (HisException e)
	{
		objStatus.add(Status.ERROR, "", "Error");
		System.out.println(e.getMessage());
		_fb.setResponseMgs("There is some problem in sample collection ");
	}
	finally
	{
		WebUTIL.setStatus(_request, objStatus);
	}
}

public static String ajaxcheckAutoGenFormate(SampleCollectionFB fb, HttpServletRequest objRequest_p)
{
	StringBuffer sbAjaxRes = new StringBuffer();
	String  strFormate="";
	String  json="";
	Map mp=new HashMap();
	try
	{
		UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
		
		Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
		
		//LabCod ,TestCode,patType And TempSampleNo  
		voSampleCollection.setLabCode(fb.getLabCode());
		voSampleCollection.setTestCode(fb.getTestCode());
		voSampleCollection.setPatType(fb.getPatType());
		voSampleCollection.setTempSampleNo(fb.getTempSampleNo());
		voSampleCollection.setConfigArea(fb.getSampleAreaCode());
		
		
		strFormate = SampleCollectionDATA.checkAutoGenFormate(voSampleCollection, voUser);
		
	      Gson gson = new Gson();

	      json = gson.toJson(strFormate);

	      System.out.println(json);
		
	}
	catch (Exception e)
	{
		
		
	}
	finally
	{
	}
	return json;
}
public static String AJAXgetPatListBarcode(SampleCollectionFB fb,HttpServletRequest request)
{
	Status status = new Status();
	HttpSession session=request.getSession();
	JsonObject jsonObj = new JsonObject();
try{
	List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
	
	lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
	Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
	objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
	objSampleCollectionVO.setFromDate(fb.getFromDate());
	objSampleCollectionVO.setToDate(fb.getToDate());
	
	objSampleCollectionVO.setSampleAreaCode(fb.getSampleAreaCode());
	
	session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
	session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
	
	session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
	session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
	
	session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
	session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
	
	UserVO userVO=ControllerUTIL.getUserVO(request);
	lstsamplePatinetVO=SampleCollectionDATA.getPatListBarcode(objSampleCollectionVO,userVO);

	//-----------------converting arraylist to json object nd then json string--------------------------------
	 JsonArray jsonArray2 = new Gson().toJsonTree(lstsamplePatinetVO).getAsJsonArray();
     
     jsonObj.add("DuplicateBarCodePatientData", jsonArray2);
     System.out.println(jsonObj.toString());

	
	
	status.add(Status.TRANSINPROCESS, "", "");
}
catch(Exception e){
	status.add(Status.ERROR_AE,"Application Execution Exception", "");
	e.printStackTrace();
}
finally{
	WebUTIL.setStatus(request, status);
}
return jsonObj.toString();

}

public static void AJAXduplicateBarCodeDetails( SampleCollectionFB fb,List<Inv_SampleCollectionVO> lstSample,HttpServletRequest request,HttpServletResponse response) throws BarcodeException, OutputException, IOException
{
	StringBuffer htmlContents=new StringBuffer();
	UserVO _userVO=getUserVO(request);
	Status objStatus=new Status();	
	HttpSession session=request.getSession();
	HashMap<String, byte[]> t = new HashMap<String, byte[]>();
	
	 Inv_SampleCollectionVO voSampleCollection=new Inv_SampleCollectionVO();
	
	for(int i=0;i<lstSample.size();i++)
	{
		voSampleCollection=lstSample.get(i);
		
			String sampleno=voSampleCollection.getSampleNo();
			String crno = voSampleCollection.getPatCRNo();
			String samplename = voSampleCollection.getSampleName();
			String reqdate = voSampleCollection.getRequisitionDate();
			String labname = voSampleCollection.getLabName();
			String colldate = voSampleCollection.getSampleCollectionDate();
			String reqdateee = "";  
			String colldatee="";
		
		 SimpleDateFormat sdf = 	new SimpleDateFormat("dd-MMM-yyyy");
		 SimpleDateFormat sdf2 = 	new SimpleDateFormat("dd-MMM-yyyy");

	   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy ");
	   SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yy ");
	   
	   try {
		Date date = sdf.parse(reqdate);
		reqdateee=sdf1.format(date);
		Date date1 = sdf2.parse(colldate);
		colldatee=sdf3.format(date1);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   
		String sugartestcodesampleno="";
		sugartestcodesampleno=voSampleCollection.getSugarTestCode();
		String patname="";
		
			if(voSampleCollection.getpatName()!=null && voSampleCollection.getpatName().trim().length()>10)
		 patname=voSampleCollection.getpatName().trim().substring(0,10);
			else
				patname=voSampleCollection.getpatName().trim();		
		
		
		String barCodeGenSiString=String.valueOf(lstSample.size());
		
		 OutputStream os=new ByteArrayOutputStream();
		Barcode     barcode = BarcodeFactory.createCode128(sampleno);
			
 		barcode.setBarWidth(1);
		       barcode.setResolution(203);
		       //barcode.setBarHeight(10);
		  
		     Font font=new Font("Plain",Font.PLAIN,0);
		       barcode.setFont(font);
		     BarcodeImageHandler.writePNG(barcode, os);
			
		  
		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
			 byte[] data=bos.toByteArray();
			
			t.put(sampleno, data);
		    //System.out.println("cho"+data.toString());
		  
		  
       try {
			bos.flush();
			os.flush();
			bos.close();
            os.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     		if(_userVO.getHospitalCode().equals("96101") || _userVO.getHospitalCode().equals("10911"))
			{
      /* boolean flgg=false;
       if(flgg)
       {*/
    	   
 		htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
        htmlContents.append("<tr><td width='50%'  ><div id='"+i+"diivBarCodeControl'><img style='margin-left:-13px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+sampleno+"\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:-25px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+sampleno+"\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
        htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='"+i+"diivBarCodeControl'><center>"+sampleno+"</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='"+i+"diivBarCodeControlAll'><center>"+sampleno+"</center></div></td></tr>");
        htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+crno+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+labname+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Coll Date:</b></td><td height='8' style='font-size:9px;'>"+reqdate+"/"+colldate+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patname+"</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+crno+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>"+labname+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Coll Date:</b></td><td height='8' style='font-size:9px;'>"+reqdate+"/"+colldate+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patname+"</td></tr> </table></td></tr>");

        
        htmlContents.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
			}
			else
			{
 		
				String is_sammple_duplicate_print= SampleCollectionDATA.getsamplebarcodeconfig(_userVO);
			//String is_sammple_duplicate_print= "1";

			
	    		    
           htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
       
           if(sampleno!=null && (sampleno.contains("F") || sampleno.contains("A") || sampleno.contains("R") || sampleno.contains("B") || sampleno.contains("P")))
           {
        	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
        		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
        		   else      
        	     htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:20px;height: 30px;width:160;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:160px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image fsound in session\"   width='100%' ></div></td></tr>");
           }else
           {
        	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
        		   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
        		   else
        	   htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:20px;height: 30px;width:140px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno=" + sampleno + "\" alt=\"no image fsound in session\"   width='100%' ></div></td></tr>");
           }
           
          
               
        	   if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
        		   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + sampleno + "</div></td></tr>");
        		   else
        	   htmlContents.append("<tr><td width='50%'  style='font-size:12px;' ><div id='" + i + "diivBarCodeControl' style='margin-left:80'>" + sampleno + "</div></td><td width='50%' align='left' style='font-size:12px;'><div id='" + i + "diivBarCodeControlAll' style='margin-left:80'>" + sampleno + "</div></td></tr>");
           
           
           if(is_sammple_duplicate_print!=null && !is_sammple_duplicate_print.equals("") && is_sammple_duplicate_print.equals("1"))
        	   htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + reqdate + "/" + colldate + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patname + "</td></tr> </table></td></tr>");
        	   else
           htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:8px; height='10'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + reqdate + "/" + colldate + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patname + "</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:8px;'><b>CR No.</b></td><td height='8' style='font-size:8px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Lab Name:</b></td><td height='8' style='font-size:8px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:8px;'>" + reqdate + "/" + colldate + "</td></tr><tr><td height='8' style='font-size:8px;'><b>Pt:</b></td><td height='8' style='font-size:8px;'>" + patname + "</td></tr> </table></td></tr>");
           
           
           htmlContents.append("<input type='hidden' id='barCodeGenSize' value='" + barCodeGenSiString + "' name='barCodeGenSize'/></table>");
	 		
	 		
	 		
      
			} 	    
 		   
  
	
	
	}
	fb.setSaveConfirmFlag("1");
	  System.out.println("html Contents"+htmlContents);
	 session.setAttribute(InvestigationConfig.UPLOADED_FILE_AS_ARRAY, t); 
	 	session.setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
			objStatus.add(Status.TRANSINPROCESS);
			WebUTIL.setStatus(request,objStatus);
	
		fb.setResponseMgs("Barcode generated succefully");

		response.getWriter().write(fb.getResponseMgs());
	
}




}
