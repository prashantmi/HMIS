/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Sample Collection Cum Acceptance Process
 ## Purpose						        :  
 ## Date of Creation					:25-May-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.transactions.controller.utl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.SampleCollectionCumAcceptanceDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.SampleCollectionCumAcceptanceFB;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.SampleCollectionCumAcceptanceVO;
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

public class SampleCollectionCumAcceptanceUTIL extends ControllerUTIL {
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
	
		
	public static void getPatList(SampleCollectionCumAcceptanceFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
	try{
		List<SampleCollectionCumAcceptanceVO> lstsamplePatinetVO=null;
		
		lstsamplePatinetVO=new ArrayList<SampleCollectionCumAcceptanceVO>();
		SampleCollectionCumAcceptanceVO objSampleCollectionVO= new SampleCollectionCumAcceptanceVO();
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
		lstsamplePatinetVO=SampleCollectionCumAcceptanceDATA.getPatList(objSampleCollectionVO,userVO);
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
	public static void getSampleCollectionArea(SampleCollectionCumAcceptanceFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
	try{
		List<SampleCollectionCumAcceptanceVO> lstsampleAreaVO=null;
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		String todayDate = WebUTIL.getCustomisedSysDate(dt, "dd-MMM-yyyy");
		
		lstsampleAreaVO=new ArrayList<SampleCollectionCumAcceptanceVO>();
		SampleCollectionCumAcceptanceVO objSampleVO = new SampleCollectionCumAcceptanceVO();
		//WebUTIL.populate(lstsampleAreaVO,fb);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		lstsampleAreaVO=SampleCollectionCumAcceptanceDATA.getSampleCollectionArea(userVO);
			if(lstsampleAreaVO!=null)
			{
				WebUTIL.populate(fb,lstsampleAreaVO);
				
				
				List<Entry> sampleList=new ArrayList<Entry>();
				for (SampleCollectionCumAcceptanceVO sampleAreaVO : lstsampleAreaVO) {
									
					Entry entObj=new Entry();
					entObj.setValue(sampleAreaVO.getSampleAreaCode());
					entObj.setLabel(sampleAreaVO.getSampleAreaName());
					sampleList.add(entObj);
					
				}
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_COLLECTION_VO,sampleList);
				if(sampleList.size()==1)
				{	List<SampleCollectionCumAcceptanceVO> lstsamplePatinetVO=null;
					lstsamplePatinetVO=new ArrayList<SampleCollectionCumAcceptanceVO>();
					
					SampleCollectionCumAcceptanceVO objSampleCollectionVO= new SampleCollectionCumAcceptanceVO();
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
					
					
					lstsamplePatinetVO=SampleCollectionCumAcceptanceDATA.getPatList(objSampleCollectionVO,userVO);
					if(lstsamplePatinetVO!=null)
					{
						WebUTIL.populate(fb,lstsamplePatinetVO); 
						WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
						
					}
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
		public static void saveSampleCollectionDetails(SampleCollectionCumAcceptanceFB _fb,HttpServletRequest _request)
		{
			Status objStatus=new Status();	
			HttpSession session=_request.getSession();
			
			// Logic defined as Map<CRNo,Map<RequisitionNo,Map<SampleCode#labCode,List<SampleCollectionCumAcceptanceVO>>>
			Map<String,Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>>> mp= new LinkedHashMap<String, Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>>>();
			try
			{
				Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
				UserVO _userVO=getUserVO(_request);
			//	HelperMethods.populate(bloodRequisitionDtlVO, _fb);
				
				String mobileNo=_fb.getPatMobile()==null?"":_fb.getPatMobile();
				String emailId=_fb.getPatEmail()==null?"":_fb.getPatEmail();
				String patAddress=_fb.getPatAddress()==null?"":_fb.getPatAddress();
				
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
								String patType=testCodeArray[20];
								
								String patName=testCodeArray[12].split("\\$")[1];
								String patAge=testCodeArray[12].split("\\$")[2];
								String patGender=testCodeArray[12].split("\\$")[3];
							 
								 String sampleFormate=testCodeArray[13];
								 String initDate=testCodeArray[14];
								 String noofSegDigits=testCodeArray[15];
								 String fromSeries=testCodeArray[16];
								 String toSeries=testCodeArray[17];
								 String initType=testCodeArray[18];
								 String runningSampleNo=testCodeArray[19];
								 
								 String configLab=testCodeArray[21];
								 String configType=testCodeArray[22];
								 String configSeq=testCodeArray[23];
								 String configTest=testCodeArray[24];
								 String configArea=testCodeArray[25];
								 
								 
								Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>> mpReqNo= mp.get(crNo);
								
								// First Time Insertion 
								if(mpReqNo==null)
								{
									mpReqNo= new LinkedHashMap<String,Map<String,List<SampleCollectionCumAcceptanceVO>>>();
									
									Map<String,List<SampleCollectionCumAcceptanceVO>> mpSample=new LinkedHashMap<String,List<SampleCollectionCumAcceptanceVO>>();
									
									List<SampleCollectionCumAcceptanceVO> lstSample=new ArrayList<SampleCollectionCumAcceptanceVO>();
									SampleCollectionCumAcceptanceVO voSample=new SampleCollectionCumAcceptanceVO();
									
									//Setting VO Values from labStringArray
									voSample.setPatCRNo(crNo);
									voSample.setSampleCode(sampleCode);
									voSample.setRequisitionDNo(requisitionDNo);
									voSample.setRequisitionNo(requisitionNo);
									voSample.setLabCode(labCode);
									voSample.setPatMobile(mobileNo);
									voSample.setPatEmail(emailId);
									voSample.setPatAddress(patAddress);
									
									voSample.setSampleAreaCode(collAreaCode);
									voSample.setPrintStatus(printStatus);
									voSample.setSampleQnty(_fb.getSampleQnty()[index]);
									voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
									voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
									voSample.setPriorityCode(_fb.getPriorityCode()[index]);
									voSample.setTestBasedMachine(_fb.getTestBasedMachine()[i]);
									voSample.setTypeOfComponent("1"); // Need to Discuss
									
									
									voSample.setPatAge(patAge);
									voSample.setPatName(patName);
									voSample.setPatGenderCode(patGender);
									
                             if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
										
									{
										voSample.setTempSampleNo(_fb.getSampleNo()[index]);
									}
									else
									{
										voSample.setTempSampleNo(samplenoConfig);
									}
                             
                             //added by krishnan nema on 22/10/2018
                             voSample.setTempLabNumber(_fb.getLabNumber()[index]);
								
                             voSample.setCheckSamConfigForAutoGen(samplenoConfig);
									voSample.setPatType(patType);
									voSample.setBillNo(billNo);
									voSample.setTestCode(testCode);
									voSample.setSampleName(sampleName);
									// Still Some values need to be inserted
									voSample.setSampleNoFormat(sampleFormate);
									voSample.setInitDate(initDate);
									voSample.setNoOfSeqDigit(noofSegDigits);
									voSample.setFromSeries(fromSeries);
                                      voSample.setToSeries(toSeries);
                                      voSample.setInitType(initType);
                                      voSample.setRunningSampleNo(runningSampleNo);
                                      
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
										Map<String,List<SampleCollectionCumAcceptanceVO>> mpSample=mpReqNo.get(requisitionNo);
										
										// First Time Insertion 
										if(mpSample==null)
										{
											 	mpSample=new LinkedHashMap<String,List<SampleCollectionCumAcceptanceVO>>();
												
												List<SampleCollectionCumAcceptanceVO> lstSample=new ArrayList<SampleCollectionCumAcceptanceVO>();
												SampleCollectionCumAcceptanceVO voSample=new SampleCollectionCumAcceptanceVO();
												
												//Setting VO Values from labStringArray
												voSample.setPatCRNo(crNo);
												voSample.setSampleCode(sampleCode);
												voSample.setRequisitionDNo(requisitionDNo);
												voSample.setRequisitionNo(requisitionNo);
												voSample.setLabCode(labCode);
												voSample.setPatMobile(mobileNo);
												voSample.setPatEmail(emailId);
												voSample.setPatAddress(patAddress);
												
												
												voSample.setSampleAreaCode(collAreaCode);
												voSample.setPrintStatus(printStatus);
												voSample.setSampleQnty(_fb.getSampleQnty()[index]);
												voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
												voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
												voSample.setPriorityCode(_fb.getPriorityCode()[index]);

												voSample.setTestBasedMachine(_fb.getTestBasedMachine()[i]);
												voSample.setTypeOfComponent("1"); // Need to Discuss

												voSample.setPatAge(patAge);
												voSample.setPatName(patName);
												voSample.setPatGenderCode(patGender);
												
												
												if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
													
												{
													voSample.setTempSampleNo(_fb.getSampleNo()[index]);
												}
												else
												{
													voSample.setTempSampleNo(samplenoConfig);
												}
												
												//added by krishnan nema on 22/10/2018
					                             voSample.setTempLabNumber(_fb.getLabNumber()[index]);
											
												 voSample.setCheckSamConfigForAutoGen(samplenoConfig);
												voSample.setBillNo(billNo);
												voSample.setTestCode(testCode);
												voSample.setSampleName(sampleName);
												voSample.setPatType(patType);
												
												voSample.setSampleNoFormat(sampleFormate);
												voSample.setInitDate(initDate);
												voSample.setNoOfSeqDigit(noofSegDigits);
												voSample.setFromSeries(fromSeries);
			                                      voSample.setToSeries(toSeries);
			                                      voSample.setInitType(initType);
			                                      voSample.setRunningSampleNo(runningSampleNo);
			                                      
			                                      voSample.setConfigLab(configLab);
			                                      voSample.setConfigArea(configArea);
			                                      voSample.setConfigSeq(configSeq);
			                                      voSample.setConfigTest(configTest);
			                                      voSample.setConfigType(configType);
			                                      
												// Still Some values need to be inserted
												
												
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
													
													List<SampleCollectionCumAcceptanceVO> lstSample=mpSample.get(sampleCode+"#"+labCode);
													if(lstSample==null||lstSample.size()==0) // First Time Insertion
													{
														lstSample=new ArrayList<SampleCollectionCumAcceptanceVO>();
														SampleCollectionCumAcceptanceVO voSample=new SampleCollectionCumAcceptanceVO();
														
														//Setting VO Values from labStringArray
														voSample.setPatCRNo(crNo);
														voSample.setSampleCode(sampleCode);
														voSample.setRequisitionDNo(requisitionDNo);
														voSample.setRequisitionNo(requisitionNo);
														voSample.setLabCode(labCode);
														voSample.setPatMobile(mobileNo);
														voSample.setPatEmail(emailId);
														voSample.setPatAddress(patAddress);
														
														
														voSample.setSampleAreaCode(collAreaCode);
														voSample.setPrintStatus(printStatus);
														voSample.setSampleQnty(_fb.getSampleQnty()[index]);
														voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
														voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
														voSample.setPriorityCode(_fb.getPriorityCode()[index]);

														voSample.setTestBasedMachine(_fb.getTestBasedMachine()[i]);
														voSample.setTypeOfComponent("1"); // Need to Discuss
														
														voSample.setPatAge(patAge);
														voSample.setPatName(patName);
														voSample.setPatGenderCode(patGender);
														
														
														
														if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
															
														{
															voSample.setTempSampleNo(_fb.getSampleNo()[index]);
														}
														else
														{
															voSample.setTempSampleNo(samplenoConfig);
														}
														//added by krishnan nema on 22/10/2018
							                             voSample.setTempLabNumber(_fb.getLabNumber()[index]);
														
														 voSample.setCheckSamConfigForAutoGen(samplenoConfig);
														voSample.setBillNo(billNo);
														voSample.setTestCode(testCode);
														voSample.setSampleName(sampleName);
														voSample.setPatType(patType);
														
														voSample.setSampleNoFormat(sampleFormate);
														voSample.setInitDate(initDate);
														voSample.setNoOfSeqDigit(noofSegDigits);
														voSample.setFromSeries(fromSeries);
					                                      voSample.setToSeries(toSeries);
					                                      voSample.setInitType(initType);
					                                      voSample.setRunningSampleNo(runningSampleNo);
					                                      
					                                      voSample.setConfigLab(configLab);
					                                      voSample.setConfigArea(configArea);
					                                      voSample.setConfigSeq(configSeq);
					                                      voSample.setConfigTest(configTest);
					                                      voSample.setConfigType(configType);
					                                      
					                                      
														// Still Some values need to be inserted
														
														
														//Adding List of SampleVO<=>RequisitionDNo's
														lstSample.add(voSample);
														
													}
													else
													{
														SampleCollectionCumAcceptanceVO voSample=new SampleCollectionCumAcceptanceVO();
														
														//Setting VO Values from labStringArray
														voSample.setPatCRNo(crNo);
														voSample.setSampleCode(sampleCode);
														voSample.setRequisitionDNo(requisitionDNo);
														voSample.setRequisitionNo(requisitionNo);
														voSample.setLabCode(labCode);
														voSample.setPatMobile(mobileNo);
														voSample.setPatEmail(emailId);
														voSample.setPatAddress(patAddress);
														
														
														voSample.setSampleAreaCode(collAreaCode);
														voSample.setPrintStatus(printStatus);
														voSample.setSampleQnty(_fb.getSampleQnty()[index]);
														voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
														voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
														voSample.setPriorityCode(_fb.getPriorityCode()[index]);

														voSample.setTestBasedMachine(_fb.getTestBasedMachine()[i]);
														voSample.setTypeOfComponent("1"); // Need to Discuss
														
														voSample.setPatAge(patAge);
														voSample.setPatName(patName);
														voSample.setPatGenderCode(patGender);
														
														
														
                                                    if(!samplenoConfig.equals("1")||!samplenoConfig.equals("2"))
															
														{
															voSample.setTempSampleNo(_fb.getSampleNo()[index]);
														}
														else
														{
															voSample.setTempSampleNo(samplenoConfig);
														}
                                                  //added by krishnan nema on 22/10/2018
                                                    voSample.setTempLabNumber(_fb.getLabNumber()[index]);
                                                 
                                                    voSample.setCheckSamConfigForAutoGen(samplenoConfig);
														voSample.setBillNo(billNo);
														voSample.setTestCode(testCode);
														voSample.setSampleName(sampleName);
														voSample.setPatType(patType);
														
														voSample.setSampleNoFormat(sampleFormate);
														voSample.setInitDate(initDate);
														voSample.setNoOfSeqDigit(noofSegDigits);
														voSample.setFromSeries(fromSeries);
					                                      voSample.setToSeries(toSeries);
					                                      voSample.setInitType(initType);
					                                      voSample.setRunningSampleNo(runningSampleNo);
					                                      voSample.setConfigLab(configLab);
					                                      voSample.setConfigArea(configArea);
					                                      voSample.setConfigSeq(configSeq);
					                                      voSample.setConfigTest(configTest);
					                                      voSample.setConfigType(configType);
					                                      
					                                      
														// Still Some values need to be inserted
														
														
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
								
								
							 
							 List listReqdtlId=SampleCollectionCumAcceptanceDATA.saveSampleCollectionDetails(mp, _userVO);
							 
							 	StringBuilder str = new StringBuilder();	
							 //	str.append( "<br><table width='100%' border='1'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><u> ");
								//str.append( "<br>Sample Collection Details::" + "<br>");
							 	str.append( "<br>");
							 	for(int i=0;i<listReqdtlId.size();i++)
							 	{
							 		String saveString=(String)listReqdtlId.get(i);
							 		String[] arrSaveString=saveString.split("#");
							 		//System.out.println("arrSaveString - "+saveString);
							 		
							 		str.append( " Sample Collected and Accepted successfully for Patient CR Number ::	");
							 		str.append((arrSaveString[0]));
							 		str.append( " having Sample Name ::	");
							 		str.append((arrSaveString[1]));
							 		str.append( " and Sample No ::	");
							 		str.append((arrSaveString[2]));
							 		str.append( "<br>");
							 		
							 		/*str.append( "<br><table width='80%' border='1'><tr>");
								
							 		str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
									str.append( "Patient CR Number::" + "</font></td>");
									str.append( "<td width='20%' align='left' >");
									str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
									str.append((arrSaveString[0])+ "</font>" + "</td>");
							 		
							 		str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
									str.append( "Sample Name::" + "</font></td>");
									str.append( "<td width='20%' align='left' >");
									str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
									str.append((arrSaveString[1])+ "</font>" + "</td>");
									
									/*str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
									str.append( "Sample No::" + "</font></td>");
									str.append( "<td width='20%' align='left' >");
									str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
									str.append((arrSaveString[1])+ "</font>" + "</td>");
									
									
									str.append("</tr></table>"); */
							 	}
							 //	str.append("</table>");
								
								objStatus.add(Status.NEW, str.toString(),
										"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
												+ "Sample Collected and Accepted successfully" + "</font>");
							
								
				   
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
			
	
		public static void showPatDetails(SampleCollectionCumAcceptanceFB fb,HttpServletRequest request)
		{
			Status status = new Status();
			HttpSession session=request.getSession();
			UserVO _userVO=getUserVO(request);
			Map mp=new HashMap();
			Map mpMachine=new HashMap();
			String staffImage="";

			boolean flag=false;
			try{
				List<SampleCollectionCumAcceptanceVO> lstsamplePatinetVO=null;
				List<String> reqList=new ArrayList();
				SampleCollectionCumAcceptanceVO voSample=new SampleCollectionCumAcceptanceVO();
				
				List<String> staffDetails=new ArrayList();
				
				//fb.setisPatDetailPage("1");
				lstsamplePatinetVO=(List<SampleCollectionCumAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				String selectedCheckBoxValue=fb.getSelectedCheckbox();
				
				String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
				
				String crNo=arrSelectedRequisitions[0].split("#")[0];
				String reqNO=arrSelectedRequisitions[0].split("#")[1];
				String labcode=arrSelectedRequisitions[0].split("#")[2];
				String testCode=arrSelectedRequisitions[0].split("#")[5];

				
					for(SampleCollectionCumAcceptanceVO objSampleCollectionVO:lstsamplePatinetVO)
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
					mp=SampleCollectionCumAcceptanceDATA.getBilledPatList(reqList, voSample, _userVO);
					mpMachine=SampleCollectionCumAcceptanceDATA.getLabTestMachine(labcode+"#"+testCode, _userVO); // chamge back  to testcode for nims
					WebUTIL.setMapInSession(mp, request);
					WebUTIL.setMapInSession(mpMachine, request);
					
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
			 * //System.out.println("staffDetails zz : "+staffImage); } }
			 * 
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
		 * Added By Pawan Kumar B N   
		 */
		public static StringBuffer checkSampleNoDuplicacy(SampleCollectionCumAcceptanceFB fb, HttpServletRequest objRequest_p)
		{
			StringBuffer sbAjaxRes = new StringBuffer();
			String strMsgText = "";
			try
			{
				UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
				
				SampleCollectionCumAcceptanceVO voSampleCollection = new SampleCollectionCumAcceptanceVO();
				
				//Setting Area Code and Sample No
				voSampleCollection.setSampleAreaCode(fb.getSampleAreaCode());
				voSampleCollection.setTempSampleNo(fb.getStrSampleNo());

				boolean isTempSamplePresent = SampleCollectionCumAcceptanceDATA.checkSampleNoDuplicacy(voSampleCollection, voUser);
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
		
		public static StringBuffer checkAutoGenFormate(SampleCollectionCumAcceptanceFB fb, HttpServletRequest objRequest_p)
		{
			StringBuffer sbAjaxRes = new StringBuffer();
			String strMsgText = "";
			String  strFormate="";
			Map mp=new HashMap();
			try
			{
				UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
				
				SampleCollectionCumAcceptanceVO voSampleCollection = new SampleCollectionCumAcceptanceVO();
				
				//LabCod ,TestCode,patType And TempSampleNo  
				voSampleCollection.setLabCode(fb.getLabCode());
				voSampleCollection.setTestCode(fb.getTestCode());
				voSampleCollection.setPatType(fb.getPatType());
				voSampleCollection.setTempSampleNo(fb.getTempSampleNo());
				voSampleCollection.setConfigArea(fb.getSampleAreaCode());
                
				
				strFormate = SampleCollectionCumAcceptanceDATA.checkAutoGenFormate(voSampleCollection, voUser);
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

}
