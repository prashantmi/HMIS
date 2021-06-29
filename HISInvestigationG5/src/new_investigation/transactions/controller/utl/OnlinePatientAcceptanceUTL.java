package new_investigation.transactions.controller.utl;


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
import new_investigation.transactions.controller.data.OnlinePatientAcceptanceDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import hisglobal.utility.Entry;

public class OnlinePatientAcceptanceUTL extends ControllerUTIL
{
	public static boolean setPatientEssentials(OnlinePatientAcceptanceFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		OnlinePatientAcceptanceVO onlinePatientvo = new OnlinePatientAcceptanceVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			 
			onlinePatientvo.setFromDate(fb.getFromDate()); 
			onlinePatientvo.setToDate(fb.getToDate());
			onlinePatientvo.setPatCRNo(fb.getPatCRNo()); 
		onlinePatientvo.setLabCode(fb.getLabCode());
		onlinePatientvo.setSampleAreaName(fb.getSampleAreaName());
		onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());
		onlinePatientvo.setAcceptedToNotAccepted(fb.getAcceptedToNotAccepted());
			mp=OnlinePatientAcceptanceDATA.setPatientEssentials(onlinePatientvo, userVO);
			
			String val="1";
			/*			mp=InvResultEntryDATA.LabComboForResultEntry(invresultentryvo, userVO);*/
						mp.put(InvestigationConfig.isFilterPatAcc,val);
					mp.put(InvestigationConfig.FILTER_LIST_PATACC,onlinePatientvo);
			
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, onlinePatientvo);
			  
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
	
	
	public static boolean patientDetails(OnlinePatientAcceptanceFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		OnlinePatientAcceptanceVO onlinePatientvo = new OnlinePatientAcceptanceVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			 
			onlinePatientvo.setFromDate(fb.getFromDate()); 
			onlinePatientvo.setToDate(fb.getToDate());
			onlinePatientvo.setPatCRNo(fb.getPatCRNo()); 
		onlinePatientvo.setLabCode(fb.getLabCode());
			 
			//mp=OnlinePatientAcceptanceDATA.patientDetails(onlinePatientvo, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, onlinePatientvo);
			  
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
	
	public static void getUserList(OnlinePatientAcceptanceFB fb,HttpServletRequest request) {
		
		Status status = new Status();
		HttpSession session=request.getSession();
	try {
		UserVO userVO = ControllerUTIL.getUserVO(request);
		Map<String, String> userList = new HashMap<String, String>();
		userList = OnlinePatientAcceptanceDATA.getUserList(userVO);
		WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_USERLIST,userList);
	}
	catch(Exception e){
		status.add(Status.ERROR_AE,"Application Execution Exception", "");
		e.printStackTrace();
	 }	
	}
	
	public static void showPatDetails(OnlinePatientAcceptanceFB fb,HttpServletRequest request)
	{
		System.out.print("showPatDetailsshowPatDetails");
	
		
		Status status = new Status();
		HttpSession session=request.getSession();
		
		Map mp=new HashMap();
		boolean flag=false;
		String sampleAreaCode=fb.getSampleAreaCode();
		String staffImage="";
		
		try{
			List<OnlinePatientAcceptanceVO> onlinePatientvo=null;
			OnlinePatientAcceptanceVO onlinePatientv = new OnlinePatientAcceptanceVO();
			List<OnlinePatientAcceptanceVO> onlinePatientvvv=new ArrayList();

			List<String> reqList=new ArrayList();
			
			List<String> staffDetails=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			onlinePatientvo=(List<OnlinePatientAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			/*String crNo=arrSelectedRequisitions[0].split("#")[0];
			String reqNO=arrSelectedRequisitions[0].split("#")[1];
			*/
			
				for(OnlinePatientAcceptanceVO objPatientCollectionVO:onlinePatientvo)
				{
					objPatientCollectionVO.setAcceptedToNotAccepted(fb.getAcceptedToNotAccepted());
					String[] arrSelectedRequisitions1=selectedCheckBoxValue.split("@");
					for(int p=0;p<arrSelectedRequisitions1.length;p++)
					{
					String crNo1=arrSelectedRequisitions1[p].split("#")[0];
					String reqNO1=arrSelectedRequisitions1[p].split("#")[1];
					
					if(objPatientCollectionVO.getPatPuk().equals(crNo1)&&objPatientCollectionVO.getPatReqNo().equals(reqNO1))
					{
						WebUTIL.populate(fb,objPatientCollectionVO); 
						
						onlinePatientv=objPatientCollectionVO;
						onlinePatientvvv.add(onlinePatientv);
						flag=true;
					//	break;
					}
					
					}
					WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_PATIENT_VO,onlinePatientvvv);
				}
				
				for(String str:arrSelectedRequisitions)
					reqList.add(str);
				
				 
				mp=OnlinePatientAcceptanceDATA.patientDetails(onlinePatientvvv,reqList, userVO);
				
				WebUTIL.setMapInSession(mp, request);
					
				fb.setPatType(onlinePatientv.getPatType());
			   fb.setSampleAreaCode(sampleAreaCode);
			   
			   session.setAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE, "");
				
				/*staffDetails=OnlinePatientAcceptanceDATA.getStaffDetails(crNo,userVO);
				
				if(staffDetails != null){
					
						staffImage = getStaffImage(staffDetails.get(0),staffDetails.get(1),staffDetails.get(2));
						
						if(staffImage != null){
							if(!(staffImage.equals("0"))){
								
								session.setAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE, staffImage);
							}
						}
						
				}*/
			   
			   
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
	  
	// Save Logic
			public static void savePatientDetails(OnlinePatientAcceptanceFB _fb,HttpServletRequest _request)
			{
				Status objStatus=new Status();	
				HttpSession session=_request.getSession();
				
				 
			  	// Logic defined as Map<CRNo,Map<RequisitionNo,Map<labCode,List<Inv_SampleCollectionVO>>>
				Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp= new LinkedHashMap<String, Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>>();
				try
				{ 
					Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
					UserVO _userVO=getUserVO(_request);
					
				 			
					String[] selectedLabTestCodeArray=_fb.getChkSamplePatientOnSave();
					
					String acceptedToNotAccepted=_fb.getAcceptedToNotAccepted();
							
					
								int labRowCount=selectedLabTestCodeArray.length;
							
								
								for(int i=0,k=0;i<labRowCount;i++)
								{
								System.out.println(""+selectedLabTestCodeArray[i]);

								}
								
								
								for(int i=0,k=0;i<labRowCount;i++)
								{
									
									//Getting LabCodeValues from split array
									// chkVal Order crNo#requisitionNo#requisitionDNo //Please Ensure the corresponding Changes before changing this order
									String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
									System.out.println("selectedLabTestCodeArray --  "+selectedLabTestCodeArray.toString());
									
									String crNo=testCodeArray[0];
									String requisitionNo=testCodeArray[1];
									 
									String requisitionDNo=testCodeArray[2];
									String labCode=testCodeArray[3];
									 String billNo=testCodeArray[4];
									 String testName=testCodeArray[5];
									 String testCode=testCodeArray[6];
									 String labNOConfig=testCodeArray[7];
									 String grpcode=testCodeArray[10];
									 
									 int id=Integer.parseInt(testCodeArray[11]);
									 
									 String LabNoFormate=testCodeArray[12];
									 String initDate=testCodeArray[13];  //attention
									 String noofSegDigits=testCodeArray[14];
									 String fromSeries=testCodeArray[15];
									 String toSeries=testCodeArray[16];
									 String initType=testCodeArray[17];
									 String runningLabNo=testCodeArray[18];
									 
									 String configLab=testCodeArray[19];
									 String configSeq=testCodeArray[20];
									 String configType=testCodeArray[21];
									 String configTest=testCodeArray[22];
									
									 String acceptanceAreaCode=_fb.getSampleAreaCode();
									
									 String islabNoAreawise=testCodeArray[24];
									 
									//added by Prashant
									 String acceptanceDate=testCodeArray[25];
									 //String entryDate = _fb.getEntryDate();
									 
									Map<String,Map<String,List<OnlinePatientAcceptanceVO>>> mpReqNo= mp.get(crNo);
									
									// First Time Insertion 
									if(mpReqNo==null)
									{
										mpReqNo= new LinkedHashMap<String,Map<String,List<OnlinePatientAcceptanceVO>>>();
										
										Map<String,List<OnlinePatientAcceptanceVO>> mpPatient=new LinkedHashMap<String,List<OnlinePatientAcceptanceVO>>();
										
										List<OnlinePatientAcceptanceVO> lstPatient=new ArrayList<OnlinePatientAcceptanceVO>();
										OnlinePatientAcceptanceVO voPatient=new OnlinePatientAcceptanceVO();
										
										//Setting VO Values from labStringArray
										voPatient.setPatCRNo(crNo);
										//voPatient.setPatientCode(PatientCode);
										voPatient.setRequisitionNo(requisitionNo);
										voPatient.setRequisitionDNo(requisitionDNo);
										voPatient.setLabCode(labCode);
										voPatient.setBillNo(billNo);
										 voPatient.setTestName(testName);
										 voPatient.setTestCode(testCode);
										 
										 if(!labNOConfig.equals("2"))
												
											{
											 voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
											}
											else
											{
												voPatient.setLabNoConfiguration(labNOConfig);
											}
										 
										 voPatient.setCheckAutoLabConfig(labNOConfig);
										 voPatient.setAccepted(_fb.getAccepted()[id]);
										  
										 voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
										 voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
										 voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);
                                        voPatient.setMobileNo(_fb.getMobileNo());
										 voPatient.setEmailId(_fb.getEmailId());
										 voPatient.setAddress(_fb.getAddress());
										 voPatient.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode());
										 
										 voPatient.setLabNoFormat(LabNoFormate);
										 voPatient.setGroupCode(grpcode);
										 voPatient.setInitDate(initDate); //attention
										 voPatient.setNoOfSeqDigit(noofSegDigits);
										 voPatient.setFromSeries(fromSeries);
										 voPatient.setToSeries(toSeries);
										 voPatient.setInitType(initType);
										 voPatient.setRunningLabNo(runningLabNo);
										 voPatient.setConfigLab(configLab);
										 voPatient.setConfigSeq(configSeq);
										 voPatient.setConfigType(configType);
										 voPatient.setConfigTest(configTest);
										//added  by chandan
										 voPatient.setSampleAreaCode(acceptanceAreaCode);
										 voPatient.setIslabNoAreaWise(islabNoAreawise);
										 
										 //added by krishnan nema on 08/10/2018
										 voPatient.setMachineCode(_fb.getSelectedmachineCode());
										 
										//added by Prashant
										 voPatient.setAcceptanceDate(acceptanceDate);
										 voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
										 //voPatient.setEntryDate(entryDate);
										 voPatient.setUserHasPermission(_fb.getUserHasPermission());
										 
										lstPatient.add(voPatient);
								 
										mpPatient.put(labCode, lstPatient);
										
										//Putting Map of Patients in Map of Requisitions
										mpReqNo.put(requisitionNo, mpPatient);
										
									}
									else
									{
								 
								 
											Map<String,List<OnlinePatientAcceptanceVO>> mpPatient=mpReqNo.get(requisitionNo);
											
											// First Time Insertion 
											if(mpPatient==null)
											{
												 	mpPatient=new LinkedHashMap<String,List<OnlinePatientAcceptanceVO>>();
													
													List<OnlinePatientAcceptanceVO> lstPatient=new ArrayList<OnlinePatientAcceptanceVO>();
													OnlinePatientAcceptanceVO voPatient=new OnlinePatientAcceptanceVO();
													
													//Setting VO Values from labStringArray
													voPatient.setPatCRNo(crNo);
													//voSample.setPatientCode(PatientCode);
													voPatient.setRequisitionDNo(requisitionDNo);
													voPatient.setRequisitionNo(requisitionNo);
													voPatient.setLabCode(labCode);
													// Still Some values need to be inserted
													voPatient.setBillNo(billNo);
													voPatient.setTestName(testName);
													voPatient.setTestCode(testCode);
													voPatient.setAccepted(_fb.getAccepted()[id]);
													 
													 if(!labNOConfig.equals("2"))
															
														{
														 voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
														}
														else
														{
															voPatient.setLabNoConfiguration(labNOConfig);
														}
													 
													 voPatient.setCheckAutoLabConfig(labNOConfig);
													 voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
													 voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);

													 voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
													 voPatient.setMobileNo(_fb.getMobileNo());
													 voPatient.setEmailId(_fb.getEmailId());
													 voPatient.setAddress(_fb.getAddress());
													 voPatient.setLabNoFormat(LabNoFormate);
													 voPatient.setGroupCode(grpcode);
													 voPatient.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode());
													 
													 voPatient.setInitDate(initDate); //attention
													 voPatient.setNoOfSeqDigit(noofSegDigits);
													 voPatient.setFromSeries(fromSeries);
													 voPatient.setToSeries(toSeries);
													 voPatient.setInitType(initType);
													 voPatient.setRunningLabNo(runningLabNo);
													 voPatient.setConfigLab(configLab);
													 voPatient.setConfigSeq(configSeq);
													 voPatient.setConfigType(configType);
													 voPatient.setConfigTest(configTest);
													 
													//added  by chandan
													 voPatient.setSampleAreaCode(acceptanceAreaCode);
													 voPatient.setIslabNoAreaWise(islabNoAreawise);
													 
													//added by Prashant
													 voPatient.setAcceptanceDate(acceptanceDate);
													 voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
													 //voPatient.setEntryDate(entryDate);
													 voPatient.setUserHasPermission(_fb.getUserHasPermission());
													 
													//Adding List of PatientVO<=>RequisitionDNo's
													lstPatient.add(voPatient);
											 
													mpPatient.put(labCode, lstPatient);
											}
											else
											{
									 
														
														List<OnlinePatientAcceptanceVO> lstPatient=mpPatient.get(labCode);
														if(lstPatient==null||lstPatient.size()==0) // First Time Insertion
														{
															lstPatient=new ArrayList<OnlinePatientAcceptanceVO>();
															OnlinePatientAcceptanceVO voPatient=new OnlinePatientAcceptanceVO();
															
															//Setting VO Values from labStringArray
															voPatient.setPatCRNo(crNo);
															//voPatient.setSampleCode(sampleCode);
															voPatient.setRequisitionDNo(requisitionDNo);
															voPatient.setRequisitionNo(requisitionNo);
														voPatient.setLabCode(labCode);
														voPatient.setBillNo(billNo);	
														 voPatient.setTestName(testName);
														 voPatient.setTestCode(testCode);
														  voPatient.setAccepted(_fb.getAccepted()[id]);
														
														 if(!labNOConfig.equals("2"))
																
															{
															 voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
															}
															else
															{
																voPatient.setLabNoConfiguration(labNOConfig);
															}
														 voPatient.setCheckAutoLabConfig(labNOConfig);
														 voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
														 voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);

														 voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
														 voPatient.setMobileNo(_fb.getMobileNo());
														 voPatient.setEmailId(_fb.getEmailId());
														 voPatient.setAddress(_fb.getAddress());
														 voPatient.setLabNoFormat(LabNoFormate);
														 voPatient.setGroupCode(grpcode);
														 voPatient.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode());
														 
														 voPatient.setInitDate(initDate);
														 voPatient.setNoOfSeqDigit(noofSegDigits);
														 voPatient.setFromSeries(fromSeries);
														 voPatient.setToSeries(toSeries);
														 voPatient.setInitType(initType);
														 voPatient.setRunningLabNo(runningLabNo);
														 
														 voPatient.setConfigLab(configLab);
														 voPatient.setConfigSeq(configSeq);
														 voPatient.setConfigType(configType);
														 voPatient.setConfigTest(configTest);
														//added  by chandan
														 voPatient.setSampleAreaCode(_fb.getSampleAreaCode());
														 voPatient.setIslabNoAreaWise(islabNoAreawise);
														// Still Some values need to be inserted
														 
														//added by Prashant
														 voPatient.setAcceptanceDate(acceptanceDate);
														 voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
														 //voPatient.setEntryDate(entryDate);
														 voPatient.setUserHasPermission(_fb.getUserHasPermission());
														 
														//Adding List of PatientVO<=>RequisitionDNo's
														lstPatient.add(voPatient);
															
														}
														else
														{
															OnlinePatientAcceptanceVO voPatient=new OnlinePatientAcceptanceVO();
															
															//Setting VO Values from labStringArray
															voPatient.setPatCRNo(crNo);
															//voPatient.setSampleCode(sampleCode);
															voPatient.setRequisitionDNo(requisitionDNo);
															voPatient.setRequisitionNo(requisitionNo);
															voPatient.setLabCode(labCode);
															voPatient.setBillNo(billNo);
															 voPatient.setTestName(testName);
															 voPatient.setTestCode(testCode);
															 
															 voPatient.setAccepted(_fb.getAccepted()[id]);
															
															 if(!labNOConfig.equals("2"))
																	
																{
																 voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
																}
																else
																{
																	voPatient.setLabNoConfiguration(labNOConfig);
																}
															 voPatient.setCheckAutoLabConfig(labNOConfig);
															 voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
															 voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);

															 voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
															 voPatient.setMobileNo(_fb.getMobileNo());
															 voPatient.setEmailId(_fb.getEmailId());
															 voPatient.setAddress(_fb.getAddress());
															 voPatient.setLabNoFormat(LabNoFormate);
															 voPatient.setGroupCode(grpcode);
															 voPatient.setPatCategoryCode(_fb.getPatCategoryCode()==null?"":_fb.getPatCategoryCode());
															 
															 voPatient.setInitDate(initDate);
															 voPatient.setNoOfSeqDigit(noofSegDigits);
															 voPatient.setFromSeries(fromSeries);
															 voPatient.setToSeries(toSeries);
															 voPatient.setInitType(initType);
															 voPatient.setRunningLabNo(runningLabNo);
															 
															 voPatient.setConfigLab(configLab);
															 voPatient.setConfigSeq(configSeq);
															 voPatient.setConfigType(configType);
															 voPatient.setConfigTest(configTest);
															 //added  by chandan
															 voPatient.setSampleAreaCode(_fb.getSampleAreaCode());
															 voPatient.setIslabNoAreaWise(islabNoAreawise);
															 
														    //added by Prashant
															 voPatient.setAcceptanceDate(acceptanceDate);
															 voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
															 //voPatient.setEntryDate(entryDate);
															 voPatient.setUserHasPermission(_fb.getUserHasPermission());
															 
															 lstPatient.add(voPatient);
														}
													 
														mpPatient.put(labCode, lstPatient);
												 
											}
											
										 
											mpReqNo.put(requisitionNo, mpPatient);
									 
										} // end main else
										
									 
										mp.put(crNo, mpReqNo);
									
									}// end for loop
								
								 
 							List listReqdtlId=OnlinePatientAcceptanceDATA.savePatientDetails(mp, _userVO);
							 	
											 
								 
								 	StringBuilder str = new StringBuilder();	
								 	for(int i=0;i<listReqdtlId.size();i++)
								 	{
									str.append( "<br>");
									//<table border='1' width='50%'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
									str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+ "Patient Acceptance ::" + "</font>");
									//str.append( "<td width='25%' align='left' >");
									str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
									str.append(((String)listReqdtlId.get(i))+ "</font>");
								 	}
									
									objStatus.add(Status.DONE, str.toString(),
											"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
													+ "" + "</font>");
								
							session.removeAttribute(InvestigationConfig.LIST_PATIENT_VO);		
					   
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
	//End Save Logic
	
	
	 
	
	public static void getEssential(OnlinePatientAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		OnlinePatientAcceptanceVO onlinePatientvo=new OnlinePatientAcceptanceVO();
		String[] multipleHospitalArray = new String[1];
		multipleHospitalArray[0] = ControllerUTIL.getUserVO(request).getHospitalCode();
		System.out.println("Logged as Hospital Code"+ multipleHospitalArray[0]);
		 
	try{	
		Map mp=new HashMap(); 
		
		Map mpp=new HashMap();
		
		//updated by krishnan nema on 08/10/2018
		Map mapMachineCombo=new HashMap();
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());
		mpp=OnlinePatientAcceptanceDATA.setPatientEssentialsOnLoad(onlinePatientvo, userVO);
		mp=OnlinePatientAcceptanceDATA.LabCombos(onlinePatientvo, userVO);
		//updated by krishnan nema on 08/10/2018
		//onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode().split("#")[0]);
		//onlinePatientvo.setPatientType(fb.getSampleAreaCode().split("#")[1]);
		onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());
		mapMachineCombo=OnlinePatientAcceptanceDATA.MachineCombos(onlinePatientvo, userVO);
		
		WebUTIL.setMapInSession(mp, request);
		
		WebUTIL.setMapInSession(mpp, request);
		
		//updated by krishnan nema on 08/10/2018
		WebUTIL.setMapInSession(mapMachineCombo, request);
		
		HelperMethods.populate(fb, onlinePatientvo);
		
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
	
	
	
	/**
	 * AJAX Response : Checking Duplicacy of Sample No
	 * @param objFB_p
	 * @param objRequest_p
	 * 
	 */
	public static StringBuffer checkDailyLabNoDuplicacy(OnlinePatientAcceptanceFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			
			OnlinePatientAcceptanceVO onlinePatientvo = new OnlinePatientAcceptanceVO();
			
			//Setting Area Code and Sample No
			 
			onlinePatientvo.setLabNoConfiguration(fb.getStrDailyLabNo()); 

			boolean isTempSamplePresent = OnlinePatientAcceptanceDATA.checkDailyLabNoDuplicacy(onlinePatientvo, voUser);
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
	
	public static StringBuffer checkAutoGenFormate(OnlinePatientAcceptanceFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		String  strFormate="";
		Map mp=new HashMap();
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			
			OnlinePatientAcceptanceVO onlinePatientvo = new OnlinePatientAcceptanceVO();
			
			//LabCod ,TestCode,patType And TempSampleNo  
			onlinePatientvo.setLabCode(fb.getLabCode());
			onlinePatientvo.setTestCode(fb.getTmpTestCode());
			onlinePatientvo.setPatType(fb.getPatType());
			onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());
			  
		 
            
			
			strFormate = OnlinePatientAcceptanceDATA.checkAutoGenFormate(onlinePatientvo, voUser);
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
	

	public static void getSampleCollectionArea(OnlinePatientAcceptanceFB fb,HttpServletRequest request)
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
					//updated by krishnan nema on 08/10/2018
					entObj.setValue(sampleAreaVO.getSampleAreaCode());
					//entObj.setValue(sampleAreaVO.getSampleAreaCode()+"#"+sampleAreaVO.getPatientType());
					entObj.setLabel(sampleAreaVO.getSampleAreaName());
					sampleList.add(entObj);
					
				}
				WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_COLLECTION_VO,sampleList);
				lstsamplePatinetVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				
				if(sampleList.size()==1)
				{
					lstsamplePatinetVO=new ArrayList<Inv_SampleCollectionVO>();
					OnlinePatientAcceptanceVO objSampleCollectionVO= new OnlinePatientAcceptanceVO();
					
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
					
				//	lstsamplePatinetVO=SampleCollectionDATA.getPatList(objSampleCollectionVO,userVO);
				
					mpp=OnlinePatientAcceptanceDATA.setPatientEssentialsOnLoad(objSampleCollectionVO, userVO);
					mp=OnlinePatientAcceptanceDATA.LabCombos(objSampleCollectionVO, userVO);

					WebUTIL.setMapInSession(mpp, request);
					WebUTIL.setMapInSession(mp, request);

					
					if(lstsamplePatinetVO!=null)
					{
						WebUTIL.populate(fb,lstsamplePatinetVO); 
						WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
						
					}
					
					
					fb.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
					fb.setSampleAreaName(((Entry)sampleList.get(0)).getLabel());
				fb.setIsSampleAreaSelected("1");
				
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
	
	
}
