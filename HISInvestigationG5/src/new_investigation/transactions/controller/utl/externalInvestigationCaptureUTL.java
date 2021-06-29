package new_investigation.transactions.controller.utl;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.Inv_RequisitionRaisingEpisodeVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.externalInvestigationCaptureDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.externalInvestigationCaptureFB;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.externalInvestigationCaptureVO;


public class externalInvestigationCaptureUTL extends ControllerUTIL
{
	
	//using for external capture to get patient details of the crno entered
	public static void setPatientRegistrationEssentials(externalInvestigationCaptureFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		List<Inv_EpisodeVO> lstEpisodeVO=new ArrayList<Inv_EpisodeVO>();
		List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionVO=new ArrayList<Inv_PatientAdmissionDtlVO>();
		Map<String,Map<String,List<String>>> mpBookMark=new LinkedHashMap<String,Map<String,List<String>>>();
		String accountNo="";
		//Is Category Expired Check
		boolean isCategoryExpired=false;

	try{
		Inv_RequisitionRaisingPatientVO patVO=null;
		if((fb.getPatCrNo()!=null) && (fb.getPatCrNo().equals("")==false))
		{
		patVO=new Inv_RequisitionRaisingPatientVO();
		Inv_RequisitionRaisingEpisodeVO episodeVO=new Inv_RequisitionRaisingEpisodeVO();
		WebUTIL.populate(patVO,fb);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		patVO.setPatCRNo(fb.getPatCrNo());
		
	//	mp=externalInvestigationCaptureDATA.getRequisitionRaisingEssentials(userVO);
		
		//Getting BookMark Details
		//mpBookMark=externalInvestigationCaptureDATA.getBookMarkDetails(userVO);
		
		//Getting Patient Details
		patVO=externalInvestigationCaptureDATA.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO);
			if(patVO!=null&&patVO.getPatCRNo()!=null)
			{
				WebUTIL.populate(fb,patVO);
				
				if(patVO.getIsCatExpired()!=null&&patVO.getIsCatExpired().equals(InvestigationConfig.YESNO_FLAG_NO))
				{
				
					if(patVO.getPatStatus().equals("IPD"))
		              {
		                  // IPD case
		                  
						// Use AdmissionVO to get details from DB
					 accountNo=externalInvestigationCaptureDATA.getAccountNo(patVO, userVO);
					    fb.setAccountNo(accountNo);
						lstPatientAdmissionVO=externalInvestigationCaptureDATA.getPatientAdmissionDetailEssentials(patVO,userVO);
						if(lstPatientAdmissionVO!=null&&lstPatientAdmissionVO.size()>0)
						{	WebUTIL.populate(patVO,lstPatientAdmissionVO.get(0));
							fb.setAdmissionDate(patVO.getAdmissionDate());
						}

						
		              }
		              else //OPD case & Emergency case
		              {
		            	  lstEpisodeVO=externalInvestigationCaptureDATA.getPatientEpisodeDetailEssentials(patVO,userVO);
		            	  if(lstEpisodeVO!=null&&lstEpisodeVO.size()>0)
								WebUTIL.populate(patVO,lstEpisodeVO.get(0));
		                  
		              }
					
					
					//Setting Patient VO in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.PATIENT_VO,patVO);
					
					// For Putting AdmissionVO in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_EPISODE_VO,lstEpisodeVO);
					
					
					// For Putting AdmissionVO in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_ADMISSION_VO,lstPatientAdmissionVO);
					
					//setting BookMark in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.MAP_BOOK_MARK,mpBookMark);
					
					//Set Map in session
					WebUTIL.setMapInSession(mp, request);
					
					String lstSampleAccepted=(String)mp.get(InvestigationConfig.ARRAY_TESTNAMES);
					
					System.out.println("--ARRAY_TESTNAMES--:::"+lstSampleAccepted);
				}//End isCatExpired
				else
					isCategoryExpired=true;
				
			}

			else
			{
				status.add(Status.ERROR_AE,"Patient Details Not Found ", "");
			}
		}
		else
		{
			
			status.add(Status.ERROR_AE," Please Enter CR.No: ", "");
		}
		
		
		
		if(isCategoryExpired)
		{
			status.add(Status.ERROR_AE," Patient Category Expired", "");
		status.add(Status.NEW, "", "");
		}
		else
		{
			if(patVO.getPatStatus().equals("IPD"))
			{
 
				//if(Integer.parseInt(accountNo)<=0)
				if(accountNo == null || "0".equals(accountNo))
 
				{	
					status.add(Status.ERROR_AE," Patient Account does not exist", "");
					throw new HisRecordNotFoundException();
				}
				else
				{
					status.add(Status.TRANSINPROCESS, "", "");	
				}	
			}
			else
				status.add(Status.TRANSINPROCESS, "", "");
		}
	}
	catch(Exception e){
		//status.add(Status.ERROR_AE,"Application Execution Exception", "");
		status.add(Status.NEW, "", "");
		e.printStackTrace();
	}
	finally{
		WebUTIL.setStatus(request, status);
	}
}
	
	
	//using for external capture to get various combos
	public static void searchLaboratoryWiseTest(externalInvestigationCaptureFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
	//	searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
	//	searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
	//	searchVO.setSearchTestGroupName(fb.getSearchTestGroupName()==null?"":fb.getSearchTestGroupName());
	//	searchVO.setSearchTestGroup(fb.getSearchTestGroup()==null?"":fb.getSearchTestGroup());
	//	searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue()==null?"0":fb.getTstOrTestGroupValue());
		
		
		

		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_LABNAMES)!=null)
		{	
			searchVO.setLabEmpty("0");
		
		}
			
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_TESTNAMES)!=null)
		{	
			searchVO.setTestEmpty("0");
		
		}
			
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE)!=null)
		{	
			searchVO.setTestCodeEmpty("0");
		}
			
		mp=externalInvestigationCaptureDATA.searchLabWiseTestDetails(searchVO,userVO);
			
		if(searchVO.getSearchTestGroupName()==null||!searchVO.getTstOrTestGroupValue().equals("1"))
		{			
			session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
		}

		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		 
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
	
	
	
	
	
	public static void searchLaboratoryWiseTestGroupOnClick(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue());
		mp=externalInvestigationCaptureDATA.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO);
			
		 

		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		 
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
	
	public static void deleteRow(InvestigationRaisingDtlFB fb, HttpServletRequest request)
	{
		Status objStatus=new Status();	
		//List indicationIdList=new ArrayList();
		HttpSession session=WebUTIL.getSession(request);
		
		try
		{
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			List<String> newLstLabTestCodeArray=new ArrayList<String>();
			
			boolean firstIteration=true;
			String strLabTestCodes="";
			
			Iterator itr=lstLabTestCodeArray.iterator();
			while(itr.hasNext())
			{
				String str=(String)itr.next();
				str=str.replace(";","#");
		if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
					continue;
				else
				{
					//newLstLabTestCodeArray.add(str.replace(";","#"));
					if(firstIteration)
					{
						strLabTestCodes=str;
						firstIteration=false;
					}
					else
					{
						
						strLabTestCodes=strLabTestCodes+"@"+str;
					}
				}
			}
			
			System.out.println("strLabTestCodes= "+strLabTestCodes);
			
			fb.setLabTestCodeArray(strLabTestCodes);
			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(request, objStatus);
		}
		
	}
	
	// Save Logic
	public static void saveRequisitionDetails(externalInvestigationCaptureFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		String flag="";
		Map<String,Map<String,LabTestVO>> mp= new LinkedHashMap<String,Map<String,LabTestVO>>();
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy hh:mm");
			UserVO _userVO=getUserVO(_request);
			List<externalInvestigationCaptureVO> lstExternalCapture=new ArrayList<externalInvestigationCaptureVO>();
			
			
			
			//Getting PATVO from Session
			Inv_RequisitionRaisingPatientVO patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			if(patVO!=null)
					;
						
			
			for(int i=0;i<_fb.getExternalLabCode().length;i++)
			{
				
				externalInvestigationCaptureVO externalCapture_vo=new externalInvestigationCaptureVO();
				
				externalCapture_vo.setExternalLabCode(_fb.getExternalLabCode()[i]);
				externalCapture_vo.setLabCode(_fb.getLabCode()[i]);
				externalCapture_vo.setTestCode(_fb.getTestCode()[i]);
				externalCapture_vo.setParameterCode(_fb.getParameterCode()[i]);
				externalCapture_vo.setSampleCode(_fb.getSampleCode()[i]);
				
				externalCapture_vo.setLabName(_fb.getLabName()[i]);
				externalCapture_vo.setTestName(_fb.getTestName()[i]);
				externalCapture_vo.setParameterName(_fb.getParameterName()[i]);
				externalCapture_vo.setSampleName(_fb.getSampleName()[i]);
				
				externalCapture_vo.setResult(_fb.getResult()[i]);
				
				externalCapture_vo.setPatCrNo(patVO.getPatCRNo());
				externalCapture_vo.setFileName(_fb.getFileName()==null?"-":_fb.getFileName());
				
				lstExternalCapture.add(externalCapture_vo);
								
			}
			
			
			
			
			List listReqdtlId=externalInvestigationCaptureDATA.saveRequisitionDetails(lstExternalCapture, _userVO);
						 
						 	StringBuilder str = new StringBuilder();	
						 	for(int ii=0;ii<1;ii++)
						 	{
						 		String saveString=(String)listReqdtlId.get(ii);
						 		String[] arrSaveString=saveString.split("#");
						 		
							str.append( "<br>");
							str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
							str.append( " Investigation Captured Successfully for Patient CR Number::" + "</font>");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append((arrSaveString[0])+ "</font>");
							
				
						 	}
							
							objStatus.add(Status.DONE, str.toString(),
									"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
											+ "Investigation Captured" + "</font>");
						
					
							
			   
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
	
//	public static void searchBookMark(InvestigationRaisingDtlFB fb,HttpServletRequest request)
//	{
//		Status status = new Status();
//		//status.add(Status.NEW, "", "");
//		Map mp=new HashMap();
//	try{
//		InvestigationSearchVO searchVO=new InvestigationSearchVO();
//		UserVO userVO=ControllerUTIL.getUserVO(request);
//		
//		
//		searchVO.setSearchLabName(fb.getSearchLabName());
//		searchVO.setSearchTestName(fb.getSearchTestName());
//		
//		//Resetting the LabCodeArray ; for selected lab types
//		//fb.setLabTestCodeArray("");
//		
//		String labTestCodeArray=fb.getLabTestCodeArray();
//		 
//		//Logic to check whether the lab/test is already present in the list
//		String[] labTestCatalogue=labTestCodeArray.split("@");
//		
//		Set<String> setLabCatalogue=new HashSet<String>();
//		if(labTestCatalogue!=null && labTestCatalogue.length>0)
//		{
//			for(String str:labTestCatalogue)
//			{
//				if(!str.equals("")&&str!=null)
//				{
//					String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
//					setLabCatalogue.add(labCodeHashTestCode);
//				}
//			}
//		}
//			
//		mp=externalInvestigationCaptureDATA.searchLabWiseTestDetails(searchVO,userVO);
//		
//		List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
//		
//		for(LabTestVO vo:lstLabTestVO)
//		{
//			String appendString=makeAppendString(vo,false);      //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
//			
//			String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
//			//Add only those Lab/Tests which are not present in the list
//			if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
//			{
//				if(labTestCodeArray.equals(""))
//					labTestCodeArray=appendString;
//				else
//					labTestCodeArray=labTestCodeArray+"@"+appendString;
//			}
//			
//		}
//		fb.setLabTestCodeArray(labTestCodeArray);
//		
//		//Resetting LabCode,TestCode,SampleCode Arrays
//		String[] strNull=null;
//		
//		fb.setLabCode(strNull);	
//		fb.setTestCode(strNull);	
//		fb.setSampleInfo(strNull);
//		
//		WebUTIL.setMapInSession(mp, request);
//		
//		//status.add(Status.NEW, "", "");
//		status.add(Status.TRANSINPROCESS, "", "");
//		}
//		catch(Exception e){
//			status.add(Status.ERROR_AE,"Application Execution Exception", "");
//			e.printStackTrace();
//		}
//		finally{
//			WebUTIL.setStatus(request, status);
//		}
//	}
	
	public static void searchBookMark(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		  
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		
		
		
		
		searchVO.setSearchLabName("");
		searchVO.setSearchTestName(fb.getSearchTestName());
		
		searchVO.setBookMarkCode(fb.getBookMarkCode());
		
		//logic For Offline Appoitment Based Test Details
		searchVO.setTestCode(fb.getAptTestCode());
		
String offlineAptDateAndTime=fb.getOfflineAppoitMentDate();
		
		//Logic to check whether the lab/test is already present in the list
		String[] OfflineAppoitmentDtl=offlineAptDateAndTime.split("@");
		
String hidAotNo=fb.getHidAptNo();
		
		//Logic to check whether the lab/test is already present in the list
		String[] hideAptNoDtl=hidAotNo.split("`");
		
		
		int i=0;
		
		//Resetting the LabCodeArray ; for selected lab types
		//fb.setLabTestCodeArray("");
		
		String labTestCodeArray=fb.getLabTestCodeArray();
		
		//Logic to check whether the lab/test is already present in the list
		String[] labTestCatalogue=labTestCodeArray.split("@");
		
		Set<String> setLabCatalogue=new HashSet<String>();
		if(labTestCatalogue!=null && labTestCatalogue.length>0)
		{
			for(String str:labTestCatalogue)
			{
				if(!str.equals("")&&str!=null)
				{
					String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
					setLabCatalogue.add(labCodeHashTestCode);
				}
			}
		}
			
		mp=externalInvestigationCaptureDATA.searchBookMark(searchVO, userVO);
		
		List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK);
		
		
		
		
		for(LabTestVO vo:lstLabTestVO)
		{
			
			if(OfflineAppoitmentDtl!=null&&!OfflineAppoitmentDtl.equals("")&&searchVO.getBookMarkCode().equals(""))
			{
			vo.setOfflineAppoitMentDate(OfflineAppoitmentDtl[i]);
		    vo.setHideAptNo(hideAptNoDtl[i]);
			}
		    
			vo.setBookMarkCode(fb.getBookMarkCode());
		
			String appendString=makeAppendString(vo,false);      //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
			
			String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
			//Add only those Lab/Tests which are not present in the list
			if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
			{
				if(labTestCodeArray.equals(""))
					labTestCodeArray=appendString;
				else
					labTestCodeArray=labTestCodeArray+"@"+appendString;
			}
			i++;
		}
		fb.setLabTestCodeArray(labTestCodeArray);
		
		//Resetting LabCode,TestCode,SampleCode Arrays
		String[] strNull=null;
		
		fb.setLabCode(strNull);	
		fb.setTestCode(strNull);	
		fb.setSampleInfo(strNull);
		
		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
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
	 * AJAX Response : Modifying LabTestCodeArray
	 * @param objFB_p
	 * @param objRequest_p
	 * Added By Pawan Kumar B N  on 2011.12.21
	 */
	public static StringBuffer deleteLabTestCodeArray(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			List<String> newLstLabTestCodeArray=new ArrayList<String>();
			
			boolean firstIteration=true;
			String strLabTestCodes="";
			
			Iterator itr=lstLabTestCodeArray.iterator();
			while(itr.hasNext())
			{
				String str=(String)itr.next();
				str=str.replace(";","#");

				if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
					continue;
				else
				{
					//newLstLabTestCodeArray.add(str.replace(";","#"));
					if(firstIteration)
					{
						strLabTestCodes=str;
						firstIteration=false;
					}
					strLabTestCodes=strLabTestCodes+"@"+str;
				}
			}
			
			System.out.println("strLabTestCodes= "+strLabTestCodes);
			
			fb.setLabTestCodeArray(strLabTestCodes);
			
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(strLabTestCodes);
			//sbAjaxRes.append("\'");
			//sbAjaxRes.append("}]");
			
			
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
		}
		return sbAjaxRes;
	}
	
	
	/**
	 * AJAX Response : Modifying LabTestCodeArray
	 * @param objFB_p
	 * @param objRequest_p
	 * Added By Pawan Kumar B N   
	 * modified By Pathan Basha 
	 */
	public static StringBuffer modifyLabTestCodeArray(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			System.out.println("strLabTestCodes= "+lstLabTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			String tmpSampleCode=fb.getTmpSampleCode();
			
			String strLabTestCodes="";
			boolean firstIteration=true;
			
			for(String str:lstLabTestCodeArray)
			{
				str=str.replace(";","#");

				if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				
				
				
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
				{
					// Change the Sample Code
					str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+tmpSampleCode+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19];
				}
				
				if(firstIteration)
				{
					strLabTestCodes=str;
					firstIteration=false;
				}
				else
					strLabTestCodes=strLabTestCodes+"@"+str;
			}
			
			System.out.println("strLabTestCodes= "+strLabTestCodes);

			fb.setLabTestCodeArray(strLabTestCodes);
			
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(strLabTestCodes);
			//sbAjaxRes.append("\'");
			//sbAjaxRes.append("}]");
			
			
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
		}
		return sbAjaxRes;
	}
	
	
	
	public static StringBuffer modifyLabTestCodeArrayForAppoitmentNo(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			String aptNo=fb.getAppoitmentNo();
			
			String strLabTestCodes="";
			boolean firstIteration=true;
			
			for(String str:lstLabTestCodeArray)
			{
				str=str.replace(";","#");

				if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
				{
					// Change the Sample Code
					str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+aptNo;
				}
				
				if(firstIteration)
				{
					strLabTestCodes=str;
					firstIteration=false;
				}
				else
					strLabTestCodes=strLabTestCodes+"@"+str;
			}
			
			System.out.println("strLabTestCodes= "+strLabTestCodes);

			fb.setLabTestCodeArray(strLabTestCodes);
			
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(strLabTestCodes);
			//sbAjaxRes.append("\'");
			//sbAjaxRes.append("}]");
			
			
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
		}
		return sbAjaxRes;
	}
	
	
	/**
	 * AJAX Response : Modifying LabTestCodeArray
	 * @param objFB_p
	 * @param objRequest_p
	 * Added By Pawan Kumar B N  on 2011.12.21
	 */
	public static StringBuffer modifyPriority(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			String tmpPriority=fb.getTmpPriority();
			
			String strLabTestCodes="";
			boolean firstIteration=true;
			
			for(String str:lstLabTestCodeArray)
			{
				str=str.replace(";","#");

				if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
				{
					// Change the Sample Code
					str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+tmpPriority+"#"+arrStr[10]+"#"+arrStr[11];
				}
				
				if(firstIteration)
				{
					strLabTestCodes=str;
					firstIteration=false;
				}
				strLabTestCodes=strLabTestCodes+"@"+str;
			}
			
			System.out.println("strLabTestCodes= "+strLabTestCodes);

			fb.setLabTestCodeArray(strLabTestCodes);
			
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(strLabTestCodes);
			//sbAjaxRes.append("\'");
			//sbAjaxRes.append("}]");
			
			
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
		}
		return sbAjaxRes;
	}
		
	
	
	/**
	 * AJAX Response : populating Test Combo based on labCode using AJAX
	 * @param objFB_p
	 * @param objRequest_p
	 * Added By Pawan Kumar B N  on 2011.12.21 pouplatePrvTestDtl
	 */
	public static StringBuffer pouplateTestCombo(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			String labCode=fb.getTmpLabCode();
			objRequest_p.getSession().setAttribute("a",strTestCombo);
			
			//strTestCombo=externalInvestigationCaptureDATA.getTestComboAJAX(labCode,userVO);
			//session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			mp=externalInvestigationCaptureDATA.getTestComboAJAXMAP(labCode,userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);
			String lstSampleAccepted=(String)mp.get(InvestigationConfig.ARRAY_TESTNAMES_AJAX);
			sbAjaxRes.append(lstSampleAccepted);
			
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
		}
		return sbAjaxRes;
	}
	
	public static StringBuffer pouplatePrvTestDtl(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		 
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			String CrNo=fb.getTmpCrNo();
			
			//strTestCombo=externalInvestigationCaptureDATA.getTestComboAJAX(labCode,userVO);
			//session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			mp=externalInvestigationCaptureDATA.getPrvTestDtlAJAXMAP(CrNo,userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);
			
			
			String  lstSampleAccepted=(String)mp.get(InvestigationConfig.LIST_PRVTESTDTL_AJAX);
			
			fb.setPrvTestDtl(lstSampleAccepted);
			System.out.println("-------------->"+lstSampleAccepted);
			sbAjaxRes.append(lstSampleAccepted);
			
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
		}
		return sbAjaxRes;
	}
	
	
	
	public static void searchTestGroup(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
		
		//Resetting the LabCodeArray ; for selected lab types
		//fb.setLabTestCodeArray("");
		
		String labTestCodeArray=fb.getLabTestCodeArray();
		
		//Logic to check whether the lab/test is already present in the list
		String[] labTestCatalogue=labTestCodeArray.split("@");
		
		Set<String> setLabCatalogue=new HashSet<String>();
		if(labTestCatalogue!=null && labTestCatalogue.length>0)
		{
			for(String str:labTestCatalogue)
			{
				if(!str.equals("")&&str!=null)
				{
					String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
					setLabCatalogue.add(labCodeHashTestCode);
				}
			}
		}
			
		mp=externalInvestigationCaptureDATA.searchTestGroup(searchVO,userVO);
		
		List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
		
		for(LabTestVO vo:lstLabTestVO)
		{
			String appendString=makeAppendString(vo,true);     //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());
			
			String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
			//Add only those Lab/Tests which are not present in the list
			if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
			{
				if(labTestCodeArray.equals(""))
					labTestCodeArray=appendString;
				else
					labTestCodeArray=labTestCodeArray+"@"+appendString;
			}
			
		}
		fb.setLabTestCodeArray(labTestCodeArray);
		
		//Resetting LabCode,TestCode,SampleCode Arrays
		String[] strNull=null;
		
		fb.setLabCode(strNull);	
		fb.setTestCode(strNull);	
		fb.setSampleInfo(strNull);
		
		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
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
	 * AJAX Response : populating Test Group based on labCode using AJAX
	 * @param objFB_p
	 * @param objRequest_p
	 * Added By Pawan Kumar B N  on 2011.12.21
	 */
	public static StringBuffer pouplateTestGroup(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			String labCode=fb.getTmpLabCode();
			strTestCombo=externalInvestigationCaptureDATA.getTestGroupAJAX(labCode,userVO);
			sbAjaxRes.append(strTestCombo);
			
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
		}
		return sbAjaxRes;
	}
	
	public static void getTestsBasedOnGroups(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
			searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			//Resetting the LabCodeArray ; for selected lab types
			//fb.setLabTestCodeArray("");
			
			String labTestCodeArray=fb.getLabTestCodeArray();
			
			//Logic to check whether the lab/test is already present in the list
			String[] labTestCatalogue=labTestCodeArray.split("@");
			
			Set<String> setLabCatalogue=new HashSet<String>();
			if(labTestCatalogue!=null && labTestCatalogue.length>0)
			{
				for(String str:labTestCatalogue)
				{
					if(!str.equals("")&&str!=null)
					{
						String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}
				
			mp=externalInvestigationCaptureDATA.getTestsBasedOnGroups(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
			
			for(LabTestVO vo:lstLabTestVO)
			{
				 
				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				vo.setTestGroupType("1");
				String appendString=makeAppendString(vo,true);       //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
						labTestCodeArray=appendString;
					else
						labTestCodeArray=labTestCodeArray+"@"+appendString;
				}
				
			}
			fb.setLabTestCodeArray(labTestCodeArray);
			
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
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
	public static void searchTest(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
			searchVO.setBookMarkCode(fb.getBookMarkCode());
			
			
			
			//Resetting the LabCodeArray ; for selected lab types
			//fb.setLabTestCodeArray("");
			
			String labTestCodeArray=fb.getLabTestCodeArray();
			
			//Logic to check whether the lab/test is already present in the list
			String[] labTestCatalogue=labTestCodeArray.split("@");
			
			Set<String> setLabCatalogue=new HashSet<String>();
			if(labTestCatalogue!=null && labTestCatalogue.length>0)
			{
				for(String str:labTestCatalogue)
				{
					if(!str.equals("")&&str!=null)
					{
						String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}
				
			mp=externalInvestigationCaptureDATA.searchBookMark(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
			
			for(LabTestVO vo:lstLabTestVO)
			{
				String appendString=makeAppendString(vo,false);   //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
						labTestCodeArray=appendString;
					else
						labTestCodeArray=labTestCodeArray+"@"+appendString;
				}
				
			}
			fb.setLabTestCodeArray(labTestCodeArray);
			
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
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
	
	
	
	public static String makeAppendString(LabTestVO vo,boolean isGroupBased)
	{
		String appendString="";
		if(isGroupBased)
			appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==null?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#";
		else
 
			appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"0"+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==""?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==""?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
 		return appendString;
	}	




	public static void getAptBasedTest(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		
		
		mp=externalInvestigationCaptureDATA.getAptBasedTest(searchVO,userVO);
			
		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		 
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
	

	
	public static void getTestsCodeWiseDtl(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		//	searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			//searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			
			searchVO.setTestCodeWise(fb.getTestCodeWise());
			//Resetting the LabCodeArray ; for selected lab types
			//fb.setLabTestCodeArray("");
			
			String labTestCodeArray=fb.getLabTestCodeArray();
			
			//Logic to check whether the lab/test is already present in the list
			String[] labTestCatalogue=labTestCodeArray.split("@");
			
			Set<String> setLabCatalogue=new HashSet<String>();
			if(labTestCatalogue!=null && labTestCatalogue.length>0)
			{
				for(String str:labTestCatalogue)
				{
					if(!str.equals("")&&str!=null)
					{
						String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}
				
			mp=externalInvestigationCaptureDATA.getTestsCodeWiseDtl(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS);
			
			for(LabTestVO vo:lstLabTestVO)
			{
				 
				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				//vo.setTestGroupType("1");
				String appendString=makeAppendString(vo,false);       //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
						labTestCodeArray=appendString;
					else
						labTestCodeArray=labTestCodeArray+"@"+appendString;
				}
				
			}
			fb.setLabTestCodeArray(labTestCodeArray);
			
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
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
	   
	
	
	
	public static void deleteReqDtl(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			
			searchVO.setDelLabCode(fb.getDelLabCode());
			searchVO.setDelTestCode(fb.getDelTestCode());
		 
		 
				
			mp=externalInvestigationCaptureDATA.deleteReqDtl(searchVO,userVO);
			 
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
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
	   
	
	//Check Duplicate Requisiton Logic
	public static StringBuffer checkRequisitionPending(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		String strTestCombo="";
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			
			//Setting Lab Code and Test Code
			 searchVO.setDupLabCode(fb.getDupLabCode());
			 searchVO.setDupTestCode(fb.getDupTestCode());
			 searchVO.setPatientCrNo(fb.getPatCrNo());

			boolean isTempSamplePresent = externalInvestigationCaptureDATA.checkRequisitionPending(searchVO, voUser);
			strTestCombo=externalInvestigationCaptureDATA.getreqStatusAJAX(searchVO, voUser);
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(isTempSamplePresent);
			sbAjaxRes.append(",");
			sbAjaxRes.append(strTestCombo);
			
			
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

	
	public static void getAptByDesk(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		
		searchVO.setFromDate(fb.getFromDate());
		searchVO.setToDate(fb.getToDate());
		
		mp=externalInvestigationCaptureDATA.getAptByDesk(searchVO,userVO);
			
		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		 
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
	
	
	public static void getAppointment(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		UserVO _userVO=getUserVO(request);
		Map mp=new HashMap();
		boolean flag=false;
		try{
			List<LabTestVO> lstPatientVO=null;
			List<String> reqList=new ArrayList();
		
			LabTestVO labTestVO= new LabTestVO();
			//fb.setisPatDetailPage("1");
			lstPatientVO=(List<LabTestVO>)session.getAttribute(InvestigationConfig.LIST_APT_BY_DESK);
			

			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			String crNo=arrSelectedRequisitions[0].split("#")[0];
			String reqNO=arrSelectedRequisitions[0].split("#")[1];
			
			for(LabTestVO objSampleCollectionVO:lstPatientVO)
			{
				if(flag) break;
				if(objSampleCollectionVO.getPatCrNo().equals(crNo)&&objSampleCollectionVO.getReqNo().equals(reqNO))
				{
					//WebUTIL.populate(fb,objSampleCollectionVO); 
					WebUTIL.setAttributeInSession(request,InvestigationConfig.APPOINTMENT_PATIENT_SELECTED,objSampleCollectionVO);
					labTestVO=objSampleCollectionVO;
					flag=true;
					break;
				}
			}
			
			
			mp=externalInvestigationCaptureDATA.getAppointment(reqNO,crNo,_userVO);
				//WebUTIL.populate(fb,voSample); 
				
				//Billed Transactions
			/*	mp=SampleCollectionDATA.getBilledPatList(reqList, voSample, _userVO);*/
				
				WebUTIL.setMapInSession(mp, request);
				
			
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
	
	
/*	
	String[] selectedReqDnoArray=fb.getAllotAppointment();
	int rowCount=selectedReqDnoArray.length;
	
	
				for(int i=0,k=0;i<rowCount;i++)
				{
					
					//Getting LabCodeValues from split array
					// chkVal Order crNo#requisitionNo#sampleCode#requisitionDNo //Please Ensure the corresponding Changes before changing this order
	
					String[] reqDno=selectedReqDnoArray[i].split("#");
				}*/

	
	public static void saveAppointmentDetails(InvestigationRaisingDtlFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		List<LabTestVO> lstLabTestVO=new ArrayList<LabTestVO>();
		
		Map<String,Map<String,LabTestVO>> mp= new LinkedHashMap<String,Map<String,LabTestVO>>();
		try
		{
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy hh:mm");
			UserVO _userVO=getUserVO(_request);
	
			//Getting PATVO from Session
			LabTestVO patVO= (LabTestVO)session.getAttribute(InvestigationConfig.APPOINTMENT_PATIENT_SELECTED);
			
			
			//appointment details array
			String[] selectedReqDnoArray=_fb.getAllotAppointment();
			int rowCount=selectedReqDnoArray.length;
		
									
						for(int i=0;i<rowCount;i++)
						{
						
							
							
							String[] array=selectedReqDnoArray[i].split("#");
							//String allot=voPatApt.getReqNo()+"#"+voPatApt.getPatCrNo()+"#"+voPatApt.getRequisitionDNo()+"#"+voPatApt.getLabCode()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getPriorityCode()+"#"+voPatApt.getPatType()+"#"+voPatApt.getGroupType()+"#"+voPatApt.getGroupCode()+"#"+isappointment;							
					
							String reqNo=array[0];
							String crNo=array[1];
							String reqDNo=array[2];
							String labCode=array[3];
							String testCode=array[4];
							String priorityCode=array[5];
							String patType=array[6];//reqtype
							String groupType=array[7];
							String testGroupCode=array[8];
							String isAppointment=array[9];
							
							
							LabTestVO voLabTest=new LabTestVO();
							
							
						
							voLabTest.setLabCode(labCode);
							voLabTest.setTestCode(testCode);
							voLabTest.setRequisitionDNo(reqDNo);
							voLabTest.setPatCrNo(crNo);
							voLabTest.setReqNo(reqNo);
							voLabTest.setPatType(patType);
							voLabTest.setPriority(priorityCode);
							voLabTest.setTestGroupType(groupType);						
							voLabTest.setTestGroupCode(testGroupCode);
							voLabTest.setIsAppointment(isAppointment);
								//voLabTest.setTestGroupType(testGroupType);
								
						String[]	splitsysDate=sysadteString.split(" ");
					
						
						voLabTest.setAppointmentDate(splitsysDate[0]);
						voLabTest.setAppointmentTime(splitsysDate[1]);
							
						
								if(voLabTest.getIsAppointment().equals("1"))
								{
									voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
									voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
									voLabTest.setAppointmentDate(_fb.getAppointmentDate());
									voLabTest.setAppointmentTime(_fb.getAppointmentTime());
								
								}
								else
								{
									voLabTest.setAppoitmentNo("0");
									
									
								}
								// Still Some values need to be inserted
								
						
								
							lstLabTestVO.add(voLabTest);
								
							
							
							
							
					}
						 
						 List listReqdtlId=externalInvestigationCaptureDATA.saveAppointmentDetails(lstLabTestVO,patVO, _userVO);
						 
						 	StringBuilder str = new StringBuilder();	
						 	for(int i=0;i<listReqdtlId.size();i++)
						 	{
						 		String saveString=(String)listReqdtlId.get(i);
						 		String[] arrSaveString=saveString.split("#");
						 		
							str.append( "<br>");
							//<table width='80%' border='1'><tr>");
							//str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
							str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
							str.append( " Requisition Raised Successfully for Patient CR Number::" + "</font>");
							//str.append( " Requisition Raised Successfully for Patient CR Number::" + "</font></td>");
							//str.append( "<td width='20%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append((arrSaveString[0])+ "</font>");
							
						/*	str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
							str.append( "Laboratory::" + "</font></td>");
							str.append( "<td width='20%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append((arrSaveString[1])+ "</font>" + "</td>");
							
							str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
							str.append( "Test::" + "</font></td>");
							str.append( "<td width='20%' align='left' >");
							str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
							str.append((arrSaveString[2])+ "</font>" + "</td>");*/
							
							//str.append("</tr></table>");
						 	}
							
							objStatus.add(Status.DONE, str.toString(),
									"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
											+ "Requisition Raised" + "</font>");
						
					
							
			   
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
	
	
	
	public static void getAppointmentDetailsOnClickGO(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		String arrayString="";
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		UserVO userVO=ControllerUTIL.getUserVO(request);
		LabTestVO voLabTest=new LabTestVO();
		voLabTest.setPatCrNo(fb.getPatCrNo());
	
	
		
		mp=externalInvestigationCaptureDATA.getAppointmentDetailsOnClickGO(voLabTest,userVO);
		
	//	if(mp!=null)
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_APT_DETAILS_ON_CLICK_GO);
		
		if(!(lstLabTestVO==null))
		for(LabTestVO vo:lstLabTestVO)
		{
		
			arrayString+=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+"advised1"+"&"+vo.getSampleName()+"&"+vo.getRequisitionDNo()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"0"+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==""?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==""?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
				
			arrayString+="@";
		}
		
		
 
		if(!arrayString.equals(""))
			arrayString = arrayString.substring(0, arrayString.length()-1);
			
			
			
		fb.setLabTestCodeArray(arrayString);
		
		 

		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		 
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
