package investigationDesk.transactions.controller.utl;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import investigationDesk.InvestigationConfig;
import investigationDesk.transactions.controller.data.SampleCollectionDATA;
import investigationDesk.transactions.controller.fb.InvestigationRaisingDtlFB;
import investigationDesk.transactions.controller.fb.SampleCollectionFB;
import investigationDesk.transactions.controller.fb.invRaisingCumSamCollectionFB;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.Inv_SampleCollectionVO;


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
		WebUTIL.populate(objSampleCollectionVO,fb);
		
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
	public static void getSampleCollectionArea(SampleCollectionFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		
		PatientDetailVO selectedPatientVO = null;
		selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		
		fb.setPatCRNo(selectedPatientVO.getPatCrNo());
		String wardCode=selectedPatientVO.getWardCode();
		
	try{
		List<Inv_SampleCollectionVO> lstsampleAreaVO=null;
		
		
		String value="";
		int i=0;
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		String todayDate = WebUTIL.getCustomisedSysDate(dt, "dd-MMM-yyyy");
		
		lstsampleAreaVO=new ArrayList<Inv_SampleCollectionVO>();
		Inv_SampleCollectionVO objSampleVO = new Inv_SampleCollectionVO();
		//WebUTIL.populate(lstsampleAreaVO,fb);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		lstsampleAreaVO=SampleCollectionDATA.getSampleCollectionArea(userVO,wardCode);
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
				if(sampleList.size()==1)
				{	List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
					lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
					
					Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
					//WebUTIL.populate(objSampleCollectionVO,fb);
					
					objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
					objSampleCollectionVO.setFromDate(todayDate);
					objSampleCollectionVO.setToDate(todayDate);
					
					objSampleCollectionVO.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
					fb.setSampleAreaCode(objSampleCollectionVO.getSampleAreaCode());
					
					session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
					session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
					
					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
					
					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
					
					
					lstsamplePatinetVO=SampleCollectionDATA.getPatList(objSampleCollectionVO,userVO);
					
					if(lstsamplePatinetVO!=null)
					{
					for(i=0;i<lstsamplePatinetVO.size();i++)
					{
						Inv_SampleCollectionVO volist=lstsamplePatinetVO.get(i);
						value+=volist.getPatCRNo()+"#"+volist.getRequisitionNo()+"#"+volist.getLabCode()+"#"+volist.getSampleCode();
						value+="@";
						
					}
					fb.setChkSamplePatient(value.split("@"));
					}
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
		public static void saveSampleCollectionDetails(SampleCollectionFB _fb,HttpServletRequest _request)
		{
			Status objStatus=new Status();	
			HttpSession session=_request.getSession();
			
			// Logic defined as Map<CRNo,Map<RequisitionNo,Map<SampleCode#labCode,List<Inv_SampleCollectionVO>>>
			Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp= new LinkedHashMap<String, Map<String,Map<String,List<Inv_SampleCollectionVO>>>>();
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
									
									voSample.setSampleAreaCode(collAreaCode);
									voSample.setPrintStatus(printStatus);
									voSample.setSampleQnty(_fb.getSampleQnty()[index]);
									voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
									voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
									voSample.setTypeOfComponent("1"); // Need to Discuss
									 
									
									
									voSample.setBillNo(billNo);
									voSample.setTestCode(testCode);
									voSample.setSampleName(sampleName);
									
									voSample.setCheckAutoLabConfig(samplenoConfig);
									
									voSample.setPatType(patType);
								       voSample.setConfigLab(configLab);
	                                      voSample.setConfigArea(configArea);
	                                      voSample.setConfigSeq(configSeq);
	                                      voSample.setConfigTest(configTest);
	                                      voSample.setConfigType(configType);
	                                      
	                                      
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
												
												
												voSample.setSampleAreaCode(collAreaCode);
												voSample.setPrintStatus(printStatus);
												voSample.setSampleQnty(_fb.getSampleQnty()[index]);
												voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
												voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
												voSample.setTypeOfComponent("1"); // Need to Discuss
												
												 
												voSample.setBillNo(billNo);
												voSample.setTestCode(testCode);
												voSample.setSampleName(sampleName);
											       voSample.setConfigLab(configLab);
				                                      voSample.setConfigArea(configArea);
				                                      voSample.setConfigSeq(configSeq);
				                                      voSample.setConfigTest(configTest);
				                                      voSample.setConfigType(configType);
				                                      
				                                      
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
														
														
														voSample.setSampleAreaCode(collAreaCode);
														voSample.setPrintStatus(printStatus);
														voSample.setSampleQnty(_fb.getSampleQnty()[index]);
														voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
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
													       voSample.setConfigLab(configLab);
						                                      voSample.setConfigArea(configArea);
						                                      voSample.setConfigSeq(configSeq);
						                                      voSample.setConfigTest(configTest);
						                                      voSample.setConfigType(configType);
						                                      
						                                      
														voSample.setSampleNoFormat(sampleFormate);
														voSample.setInitDate(initDate);
														voSample.setNoOfSeqDigit(noofSegDigits);
														voSample.setFromSeries(fromSeries);
					                                      voSample.setToSeries(toSeries);
					                                      voSample.setInitType(initType);
					                                      voSample.setRunningSampleNo(runningSampleNo);
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
														
														
														voSample.setSampleAreaCode(collAreaCode);
														voSample.setPrintStatus(printStatus);
														voSample.setSampleQnty(_fb.getSampleQnty()[index]);
														voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
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
					                                      voSample.setConfigLab(configLab);
					                                      voSample.setConfigArea(configArea);
					                                      voSample.setConfigSeq(configSeq);
					                                      voSample.setConfigTest(configTest);
					                                      voSample.setConfigType(configType);
					                                      
					                                      
					                                      voSample.setRunningSampleNo(runningSampleNo);
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
							 
							 	StringBuilder str = new StringBuilder();	
							 //	str.append( "<br><table width='100%' border='1'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><u> ");
								//str.append( "<br>Sample Collection Dtls::" + "<br>");
							 	for(int i=0;i<listReqdtlId.size();i++)
							 	{
							 		String saveString=(String)listReqdtlId.get(i);
							 		String[] arrSaveString=saveString.split("#");
							 		
							 		str.append( " Sample Collected Succesfully for Patient CR Number::	");
							 		str.append((arrSaveString[0]));
							 		str.append( " having Sample Name::	");
							 		str.append((arrSaveString[1]));
							 		str.append( " and Sample No::	");
							 		str.append((arrSaveString[2]));
							 		str.append( "<br>");
							 		
							 		
							 		
							 		
							 		
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
							 //	str.append("</table>");
								
								objStatus.add(Status.NEW, str.toString(),
										"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
												+ "Sample Collection Details::" + "<br></font>");
							
								
				   
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
			
	
		public static void showPatDetails(SampleCollectionFB fb,HttpServletRequest request)
		{
			Status status = new Status();
			HttpSession session=request.getSession();
			UserVO _userVO=getUserVO(request);
			Map mp=new HashMap();
			boolean flag=false;
			try{
				List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
				List<String> reqList=new ArrayList();
				Inv_SampleCollectionVO voSample=new Inv_SampleCollectionVO();
				
				//fb.setisPatDetailPage("1");
				lstsamplePatinetVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				String selectedCheckBoxValue=fb.getSelectedCheckbox();
				String crNo="";
				String reqNO="";
				String[] arrSelectedRequisitions=fb.getChkSamplePatient();
				
				if(arrSelectedRequisitions!=null)
				{
				 crNo=arrSelectedRequisitions[0].split("#")[0];
				 reqNO=arrSelectedRequisitions[0].split("#")[1];
				}
				
				
				if(lstsamplePatinetVO!=null)
				{
					for(Inv_SampleCollectionVO objSampleCollectionVO:lstsamplePatinetVO)
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
				}
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
                voSampleCollection.setSampleAreaCode(fb.getSampleAreaCode());
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
		

		
}
