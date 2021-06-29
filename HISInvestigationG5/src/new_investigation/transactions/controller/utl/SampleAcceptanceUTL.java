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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.PackingListGenerationDATA;
import new_investigation.transactions.controller.data.SampleAcceptanceDATA;
import new_investigation.transactions.controller.fb.SampleAcceptanceFB;
import new_investigation.vo.SampleAcceptanceVO;


public class SampleAcceptanceUTL extends ControllerUTIL
{

	public static void getSampleAcceptanceEssential(SampleAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		SampleAcceptanceVO sampleAcceptancevo=new SampleAcceptanceVO();
		String[] multipleHospitalArray = new String[1];
		multipleHospitalArray[0] = ControllerUTIL.getUserVO(request).getHospitalCode();
		System.out.println("Logged as Hospital Code"+ multipleHospitalArray[0]);

		try{	
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 

			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
			List	machinelist=PackingListGenerationDATA.machinelist(userVO);
			
		
			mpp=SampleAcceptanceDATA.getSampleAcceptanceDetailOnLoad(sampleAcceptancevo, userVO);
			mp=SampleAcceptanceDATA.sampleAcceptanceLabCombo(sampleAcceptancevo, userVO);
			WebUTIL.setMapInSession(mp, request);
			WebUTIL.setMapInSession(mpp, request);
			WebUTIL.setAttributeInSession(request,InvestigationConfig.MACHINE_LIST_ACCEPTANCE,machinelist);
			
			HelperMethods.populate(fb, sampleAcceptancevo);
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
	public static boolean getSampleAcceptanceEssentialsOnClickGo(SampleAcceptanceFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		SampleAcceptanceVO sampleAcceptancevo = new SampleAcceptanceVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			sampleAcceptancevo.setFromDate(fb.getFromDate()); 
			sampleAcceptancevo.setToDate(fb.getToDate());
			sampleAcceptancevo.setLabCode(fb.getLabCode());
			sampleAcceptancevo.setAcceptance(fb.getAcceptance());
			sampleAcceptancevo.setMachineCodee(fb.getMachineCodee());
			sampleAcceptancevo.setFlag(fb.getFlag());
			
			if(!(fb.getSearchCrNo()==null || fb.getSearchCrNo().equals(""))){
				sampleAcceptancevo.setSearchCrNo(fb.getSearchCrNo());
			}

			if(!(fb.getSampleNumber()==null || fb.getSampleNumber().equals(""))){
				sampleAcceptancevo.setSampleNumber(fb.getSampleNumber().trim());
			}
			mp=SampleAcceptanceDATA.getSampleAcceptanceDetail(sampleAcceptancevo, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, sampleAcceptancevo);

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
 
	public static void showPatDetailsOnRadioButton(SampleAcceptanceFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		Map mp=new HashMap();
		Map machineMap=new HashMap();
		boolean flag=false;
		try{
			SampleAcceptanceVO sampleAcceptance = new SampleAcceptanceVO();
			List<SampleAcceptanceVO> sampleAcceptancev=new ArrayList<SampleAcceptanceVO>();
			List<String> reqList=new ArrayList();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			Map<String,List<SampleAcceptanceVO>> _mp=(Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO);
			String selectedCheckBoxValue=fb.getSelectedRadioCheckbox();
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			String labCode=arrSelectedRequisitions[0].split("#")[0];
			String packingListN=arrSelectedRequisitions[0].split("#")[1];
			String testCode=arrSelectedRequisitions[0].split("#")[2];
			String shortSampleName=arrSelectedRequisitions[0].split("#")[3]; //added by chandan for refund check short sample name
			machineMap=SampleAcceptanceDATA.getLabTestMachine(labCode+"#"+testCode,userVO);
			

			WebUTIL.setMapInSession(machineMap, request);		

			
			WebUTIL.setMapInSession(machineMap, request);		
					
			int i,size=0;
			Iterator itr=_mp.keySet().iterator();
			while(itr.hasNext())
			{
				String packingListNo=(String)itr.next();
				List<SampleAcceptanceVO> sampleAcceptancevo=_mp.get(packingListNo);
				for(SampleAcceptanceVO objsampleAcceptanceCollectionVO:sampleAcceptancevo)
				{
					if(objsampleAcceptanceCollectionVO.getPackingListNO().equals(packingListN))
					{
						Map<String,List<SampleAcceptanceVO>> objMapSamAcc= new HashMap<String,List<SampleAcceptanceVO>>();
						String strSampleNoTemp = null;

						sampleAcceptancev.add(objsampleAcceptanceCollectionVO);

						for(int k=0; k<sampleAcceptancev.size();k++)
						{
							SampleAcceptanceVO objSampleAcceptanceVO = sampleAcceptancev.get(k);
							List<SampleAcceptanceVO> lstTempSampleAcceptanceVO = null;
							String strSampleNoTempp = objSampleAcceptanceVO.getTempSampleNo()+objSampleAcceptanceVO.getSampleNo();
							objSampleAcceptanceVO.setLabCode(labCode);
							objSampleAcceptanceVO.setShortSampleName(shortSampleName);  //added by chandan for refund set to check short sample name
							lstTempSampleAcceptanceVO=objMapSamAcc.get(strSampleNoTempp);

							if(lstTempSampleAcceptanceVO==null)
							{
								lstTempSampleAcceptanceVO=new ArrayList<SampleAcceptanceVO>();
								lstTempSampleAcceptanceVO.add(objSampleAcceptanceVO);
							}
							else
							{
								lstTempSampleAcceptanceVO.add(objSampleAcceptanceVO);
							}
							
							

							objMapSamAcc.put(strSampleNoTempp,lstTempSampleAcceptanceVO);

						}
						WebUTIL.setAttributeInSession(request,InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO,objMapSamAcc);
					}
				}
			} 
			mp=SampleAcceptanceDATA.SampleAcceptanceRejectionCombo(sampleAcceptance,reqList, userVO);
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

	// Save Logic
	public static void saveSampleAcceptanceDetail(SampleAcceptanceFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		Map mp=new HashMap();
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO=getUserVO(_request);

			List<SampleAcceptanceVO> voSampleAcceptance=new ArrayList<SampleAcceptanceVO>();

			String[] selectedLabTestCodeArray=_fb.getChkSamplePatient();

			String[] selectedFbValue=_fb.getTempReqDNo();

			int tempReqDnoCount=selectedFbValue.length;// getting Fb Values For Selected Check Box 
			
			int labRowCount=selectedLabTestCodeArray.length;

			for(int i=0;i<labRowCount;i++)
			{
				SampleAcceptanceVO voSampleAcc=new SampleAcceptanceVO();
				//Getting chkBoxValues from split array
				// chkVal Order packingListNO#sampleNo#testCode#testName#labName#strReqDNo //Please Ensure the corresponding Changes before changing this order
				String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
				String packingListNO=testCodeArray[0];
				String sampleNo=testCodeArray[1];
				String testCode=testCodeArray[2];
				String testName=testCodeArray[3];
				String labName=testCodeArray[4];
				String strReqDNo = testCodeArray[5];
                String tempSampleNo=testCodeArray[6];
                String labCode=testCodeArray[7];
                String labNoConfig=testCodeArray[8];
                String patType=testCodeArray[9];
                String patCRNo=testCodeArray[10];
                String patName=testCodeArray[11];
                String patAge=testCodeArray[12];
                String patGender=testCodeArray[13];
                String requisitionNo=testCodeArray[14];
                String shortSampleName=testCodeArray[15];
                String groupcode=testCodeArray[17];
                
                if(patType==null)
                	patType="1";
                
				voSampleAcc.setPackingListNO(packingListNO);
				voSampleAcc.setSampleNo(sampleNo);
				voSampleAcc.setTestCode(testCode);
				voSampleAcc.setTestName(testName);
				voSampleAcc.setLabName(labName);
				voSampleAcc.setRequisitionDNo(strReqDNo);
				voSampleAcc.setTempSampleNo(tempSampleNo);
				voSampleAcc.setLabCode(labCode);
				voSampleAcc.setCheckLabConfigForAutoGen(labNoConfig);
				voSampleAcc.setPatCRNo(patCRNo);
				voSampleAcc.setPatName(patName);
				voSampleAcc.setPatAge(patAge);
				voSampleAcc.setPatGender(patGender);
				voSampleAcc.setPatType(patType);
				voSampleAcc.setRequisitionNo(requisitionNo);
				voSampleAcc.setShortSampleName(shortSampleName);
				voSampleAcc.setGroupCode(groupcode);
				
				//voSampleAcc.setPatType(patType);
				// getting Fb Values For Selected Check Box 
				for(int j=0;j<tempReqDnoCount;j++)
				{
				if(_fb.getTempReqDNo()[j] != null)
					{
						if(_fb.getTempReqDNo()[j].equals(strReqDNo))
					{
					 		voSampleAcc.setRecieved(_fb.getRecieved()[j]);
							if(!labNoConfig.equals("2"))
							{
					 		voSampleAcc.setLabNoConfiguration(_fb.getLabNoConfiguration()[j]);
							}
							else
							{
								voSampleAcc.setLabNoConfiguration(labNoConfig);
							}
					 		voSampleAcc.setAccepted(_fb.getAccepted()[j]);
							voSampleAcc.setRejectionAction(_fb.getRejectionAction()[j]);
							voSampleAcc.setRejectionReason(_fb.getRejectionReason()[j]);
							voSampleAcc.setTestBasedMachine(_fb.getTestBasedMachine()[j]);
							voSampleAcc.setCollDate(_fb.getCollDate()[j]);
							voSampleAcc.setPriorityCode(_fb.getPriorityCode()[j]);
							
					}
					}
				
				}
				voSampleAcceptance.add(voSampleAcc);
			}// end forloop
			
			
			
			
			mp=SampleAcceptanceDATA.saveSampleAcceptanceDetails(voSampleAcceptance, _userVO, _request);
			//  Map accordingly to key of list declared
			List lstSampleAccepted=(List)mp.get(InvestigationConfig.LIST_ACCPETED_STATUS);
			List listSampleNotAccepted=(List)mp.get(InvestigationConfig.LIST_NOT_ACCPETED_STATUS);
			List listSampleNotReceived=(List)mp.get(InvestigationConfig.LIST_NOT_RECIEVED_STATUS);
			   
			StringBuilder str = new StringBuilder();	
			 
			  if(lstSampleAccepted!=null&&lstSampleAccepted.size()>0)
			  {
				  
				  str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ " Samples Accepted" + "</font>");
				  
				  
				for(int i=0;i<lstSampleAccepted.size();i++)
						{
							
							str.append( "<br><table border='1' width='60%'> ");
							str.append( "<td width='25%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append(((String)lstSampleAccepted.get(i))+ "</font>" + "</td></tr></table>");
						}
			  }
			  
			  if(listSampleNotAccepted!=null&&listSampleNotAccepted.size()>0)
			  {
				  str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ " Samples Not Accepted(Cancelled/Rescheduled)" + "</font>");
				  
				for(int i=0;i<listSampleNotAccepted.size();i++)
						{
							
							str.append( "<br><table border='1' width='60%'> ");
							str.append( "<td width='25%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append(((String)listSampleNotAccepted.get(i))+ "</font>" + "</td></tr></table>");
						}
			  }
			 
			  if(listSampleNotReceived!=null&&listSampleNotReceived.size()>0)
			  {
					str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ " Samples Not Recieved" + "</font>");
					
				for(int i=0;i<listSampleNotReceived.size();i++)
						{
						
							str.append( "<br><table border='1' width='60%'> ");
							str.append( "<td width='25%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append(((String)listSampleNotReceived.get(i))+ "</font>" + "</td></tr></table>");
						}
			  }

						objStatus.add(Status.DONE,str.toString(),"");

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

	/**
	 * AJAX Response : Checking Duplicacy of Sample No
	 * @param objFB_p
	 * @param objRequest_p
	 * 
	 */
	public static StringBuffer checkDailyLabNoDuplicacy(SampleAcceptanceFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);

			SampleAcceptanceVO onlinePatientvo = new SampleAcceptanceVO();
			onlinePatientvo.setLabNoConfiguration(fb.getStrDailyLabNo()); 
			boolean isTempSamplePresent = SampleAcceptanceDATA.checkDailyLabNoDuplicacy(onlinePatientvo, voUser);
			sbAjaxRes.append(isTempSamplePresent);

		}
		catch (Exception e)
		{
			strMsgText = "SampleAcceptanceUTIL.checkDailyLabNoDuplicacy() -> " + e.getMessage();
			new HisException("Investigation", "SampleAcceptanceUTIL.checkDailyLabNoDuplicacy() --> ", strMsgText);
		}
		finally
		{
		}
		return sbAjaxRes;
	}
	
	
	
	
	public static void rejectSampleAcceptanceDetail(SampleAcceptanceFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		Map mp=new HashMap();
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO=getUserVO(_request);

			List<SampleAcceptanceVO> voSampleAcceptance=new ArrayList<SampleAcceptanceVO>();

			String[] selectedLabTestCodeArray=_fb.getChkSamplePatient();

			String[] selectedFbValue=_fb.getTempReqDNo();

			int tempReqDnoCount=selectedFbValue.length;// getting Fb Values For Selected Check Box 
			
			int labRowCount=selectedLabTestCodeArray.length;

			for(int i=0;i<labRowCount;i++)
			{
				SampleAcceptanceVO voSampleAcc=new SampleAcceptanceVO();
				//Getting chkBoxValues from split array
				// chkVal Order packingListNO#sampleNo#testCode#testName#labName#strReqDNo //Please Ensure the corresponding Changes before changing this order
				String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
				String packingListNO=testCodeArray[0];
				String sampleNo=testCodeArray[1];
				String testCode=testCodeArray[2];
				String testName=testCodeArray[3];
				String labName=testCodeArray[4];
				String strReqDNo = testCodeArray[5];
                String tempSampleNo=testCodeArray[6];
                String labCode=testCodeArray[7];
                String labNoConfig=testCodeArray[8];
                
                String patType=testCodeArray[9];
                String patCRNo=testCodeArray[10];
                String patName=testCodeArray[11];
                String patAge=testCodeArray[12];
                String patGender=testCodeArray[13];
                String requisitionNo=testCodeArray[14];
                String shortSampleName=testCodeArray[15];
                String groupcode=testCodeArray[17];
                
                if(patType==null)
                	patType="1";
                
				voSampleAcc.setPackingListNO(packingListNO);
				voSampleAcc.setSampleNo(sampleNo);
				voSampleAcc.setTestCode(testCode);
				voSampleAcc.setTestName(testName);
				voSampleAcc.setLabName(labName);
				voSampleAcc.setRequisitionDNo(strReqDNo);
				voSampleAcc.setTempSampleNo(tempSampleNo);
				voSampleAcc.setLabCode(labCode);
				voSampleAcc.setCheckLabConfigForAutoGen(labNoConfig);
				
				voSampleAcc.setPatCRNo(patCRNo);
				voSampleAcc.setPatName(patName);
				voSampleAcc.setPatAge(patAge);
				voSampleAcc.setPatGender(patGender);
				voSampleAcc.setPatType(patType);
				voSampleAcc.setRequisitionNo(requisitionNo);
				voSampleAcc.setShortSampleName(shortSampleName);
				voSampleAcc.setGroupCode(groupcode);
				
				//voSampleAcc.setPatType(patType);
				// getting Fb Values For Selected Check Box 
				for(int j=0;j<tempReqDnoCount;j++)
				{
				if(_fb.getTempReqDNo()[j] != null)
					{
						if(_fb.getTempReqDNo()[j].equals(strReqDNo))
					{
					 		
							voSampleAcc.setRejectionAction(_fb.getRejectionAction()[j]);
							voSampleAcc.setRejectionReason(_fb.getRejectionReason()[j]);
					}
					}
				
				}
				voSampleAcceptance.add(voSampleAcc);
			}// end for loop
			
			
			
			
			mp=SampleAcceptanceDATA.rejectSampleAcceptanceDetails(voSampleAcceptance, _userVO, _request);
			//  Map accordingly to key of list declared
			List lstSampleRejected=(List)mp.get(InvestigationConfig.LIST_REJECTED);
			
			   
			StringBuilder str = new StringBuilder();	
			 
		
			  
			  if(lstSampleRejected!=null&&lstSampleRejected.size()>0)
			  {
				for(int i=0;i<lstSampleRejected.size();i++)
						{
							str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
										+ " Samples Rejected" + "</font>");
							str.append( "<br><table border='1' width='50%'> ");
							str.append( "<td width='25%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append(((String)lstSampleRejected.get(i))+ "</font>" + "</td></tr></table>");
						}
			  }
			 
			 
						objStatus.add(Status.DONE, str.toString(),"");

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
	
	public static String checkAutoGenFormate(SampleAcceptanceFB fb, HttpServletRequest objRequest_p)
	{
		String sbAjaxRes = "";
		String strMsgText = "";
		String  strFormate="";
		Map mp=new HashMap();
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			
			SampleAcceptanceVO voSampleCollection = new SampleAcceptanceVO();
			
			//LabCod ,TestCode,patType And TempSampleNo  
			voSampleCollection.setLabCode(fb.getLabCode());
			voSampleCollection.setTestCodee(fb.getTestCodee());
			voSampleCollection.setPatType(fb.getPatType());
			voSampleCollection.setTempSampleNo(fb.getTempSampleNo());

			
			strFormate = SampleAcceptanceDATA.checkAutoGenFormate(voSampleCollection, voUser);
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			 
			
			 
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
		return strFormate;
	}

	public static void getMachine(SampleAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		SampleAcceptanceVO sampleAcceptancevo=new SampleAcceptanceVO();
		String[] multipleHospitalArray = new String[1];
		multipleHospitalArray[0] = ControllerUTIL.getUserVO(request).getHospitalCode();
		System.out.println("Logged as Hospital Code"+ multipleHospitalArray[0]);

		try{	
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 

			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=SampleAcceptanceDATA.fetchMachime( userVO);
			
			WebUTIL.setMapInSession(mp, request);
			//WebUTIL.setMapInSession(mpp, request);

			//HelperMethods.populate(fb, sampleAcceptancevo);
		}catch(HisRecordNotFoundException e)
		{
			//System.out.println(e.getMessage());
		//	objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
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
	
	
	//added bby krishnan nema on 26/10/2018
	public static void modifySampleAcceptanceDetail(SampleAcceptanceFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		Map mp=new HashMap();
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO=getUserVO(_request);

			List<SampleAcceptanceVO> voSampleAcceptance=new ArrayList<SampleAcceptanceVO>();

			String[] selectedLabTestCodeArray=_fb.getChkSamplePatient();

			String[] selectedFbValue=_fb.getTempReqDNo();

			int tempReqDnoCount=selectedFbValue.length;// getting Fb Values For Selected Check Box 
			
			int labRowCount=selectedLabTestCodeArray.length;

			for(int i=0;i<labRowCount;i++)
			{
				SampleAcceptanceVO voSampleAcc=new SampleAcceptanceVO();
				//Getting chkBoxValues from split array
				// chkVal Order packingListNO#sampleNo#testCode#testName#labName#strReqDNo //Please Ensure the corresponding Changes before changing this order
				String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
				String packingListNO=testCodeArray[0];
				String sampleNo=testCodeArray[1];
				String testCode=testCodeArray[2];
				String testName=testCodeArray[3];
				String labName=testCodeArray[4];
				String strReqDNo = testCodeArray[5];
                String tempSampleNo=testCodeArray[6];
                String labCode=testCodeArray[7];
                String labNoConfig=testCodeArray[8];
                String patType=testCodeArray[9];
               
                String patCRNo=testCodeArray[10];
                String patName=testCodeArray[11];
                String patAge=testCodeArray[12];
                String patGender=testCodeArray[13];
                
                //String patCRNo=testCodeArray[10];
                //String patName=testCodeArray[11];
                //String patAge=testCodeArray[12];
               // String patGender=testCodeArray[13];
                //String requisitionNo=testCodeArray[14];
                //String shortSampleName=testCodeArray[15];
                if(patType==null)
                	patType="1";
                
                
                
				voSampleAcc.setPackingListNO(packingListNO);
				voSampleAcc.setSampleNo(sampleNo);
				voSampleAcc.setTestCode(testCode);
				voSampleAcc.setTestName(testName);
				voSampleAcc.setLabName(labName);
				voSampleAcc.setRequisitionDNo(strReqDNo);
				voSampleAcc.setTempSampleNo(tempSampleNo);
				voSampleAcc.setLabCode(labCode);
				voSampleAcc.setCheckLabConfigForAutoGen(labNoConfig);
				voSampleAcc.setPatCRNo(patCRNo);
				voSampleAcc.setPatName(patName);
				voSampleAcc.setPatAge(patAge);
				voSampleAcc.setPatGender(patGender);
				voSampleAcc.setPatType(patType);
				//voSampleAcc.setRequisitionNo(requisitionNo);
				//voSampleAcc.setShortSampleName(shortSampleName);
				//voSampleAcc.setPatType(patType);
				// getting Fb Values For Selected Check Box 
				for(int j=0;j<tempReqDnoCount;j++)
				{
				if(_fb.getTempReqDNo()[j] != null)
					{
						if(_fb.getTempReqDNo()[j].equals(strReqDNo))
					{
					 		voSampleAcc.setRecieved(_fb.getRecieved()[j]);
							if(!labNoConfig.equals("2"))
							{
					 		voSampleAcc.setLabNoConfiguration(_fb.getLabNoConfiguration()[j]);
							}
							else
							{
								voSampleAcc.setLabNoConfiguration(labNoConfig);
							}
							
							if(_fb.getRejectionAction()[j].equals("-1"))
					 		voSampleAcc.setAccepted("1");
							else
								voSampleAcc.setAccepted("0");
							//voSampleAcc.setRejectionAction(_fb.getRejectionAction()[j]);
							//voSampleAcc.setRejectionReason(_fb.getRejectionReason()[j]);
							voSampleAcc.setTestBasedMachine(_fb.getTestBasedMachine()[j]);
							//voSampleAcc.setCollDate(_fb.getCollDate()[j]);
							//voSampleAcc.setPriorityCode(_fb.getPriorityCode()[j]);
							
					}
					}
				
				}
				voSampleAcceptance.add(voSampleAcc);
			}// end for loop
			
			
			
			
			mp=SampleAcceptanceDATA.modifySampleAcceptanceDetails(voSampleAcceptance, _userVO, _request);
			
			
			//  Map accordingly to key of list declared
			
			List listMachineUpdated=(List)mp.get(InvestigationConfig.LIST_MODIFIED);
			   
			StringBuilder str = new StringBuilder();	
			 
			  if(listMachineUpdated!=null&&listMachineUpdated.size()>0)
			  {
				  
				  str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ " Machine Updated successfully" + "</font>");
				  
				  
				for(int i=0;i<listMachineUpdated.size();i++)
				{
							
							str.append( "<br><table border='1' width='60%'> ");
							str.append( "<td width='25%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append(((String)listMachineUpdated.get(i))+ "</font>" + "</td></tr></table>");
				}
			  }
			  
			  /*if(listMachineUpdated!=null&&listSampleNotAccepted.size()>0)
			  {
				  str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ " Samples Not Accepted(Cancelled/Rescheduled)" + "</font>");
				  
				for(int i=0;i<listSampleNotAccepted.size();i++)
						{
							
							str.append( "<br><table border='1' width='60%'> ");
							str.append( "<td width='25%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append(((String)listSampleNotAccepted.get(i))+ "</font>" + "</td></tr></table>");
						}
			  }*/
			 
			  /*if(listSampleNotReceived!=null&&listSampleNotReceived.size()>0)
			  {
					str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ " Samples Not Recieved" + "</font>");
					
				for(int i=0;i<listSampleNotReceived.size();i++)
						{
						
							str.append( "<br><table border='1' width='60%'> ");
							str.append( "<td width='25%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append(((String)listSampleNotReceived.get(i))+ "</font>" + "</td></tr></table>");
						}
			  }*/

						objStatus.add(Status.DONE,str.toString(),"");

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
	


}
