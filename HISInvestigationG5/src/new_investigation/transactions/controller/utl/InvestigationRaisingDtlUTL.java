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
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.Inv_RequisitionRaisingEpisodeVO;
import hisglobal.vo.UserVO;

import java.nio.channels.SeekableByteChannel;
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
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlXrayFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.SMS.*;

public class InvestigationRaisingDtlUTL extends ControllerUTIL
{
	public static void setPatientRegistrationEssentials(InvestigationRaisingDtlFB fb,HttpServletRequest request)
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
		Inv_ictc_VO ictc =null;
		if((fb.getPatCrNo()!=null) && (fb.getPatCrNo().equals("")==false))
		{
		patVO=new Inv_RequisitionRaisingPatientVO();
		Inv_RequisitionRaisingEpisodeVO episodeVO=new Inv_RequisitionRaisingEpisodeVO();
		WebUTIL.populate(patVO,fb);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		patVO.setPatCRNo(fb.getPatCrNo());
		
		mp=InvestigationRaisingDtlDATA.getRequisitionRaisingEssentials(userVO);
		
		String deskcalllabhide="0";

		if(fb.getPatAdmNo()==null || !fb.getPatAdmNo().equals("-1")) //calling from desk....
		{
			deskcalllabhide="1";
		}
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
			patVO.setPatepisodecode(fb.getEpisodeCode());
			patVO.setPatVisitNo(fb.getEpisodeVisitNo());
		}
		
		
		
		//Getting BookMark Details
		mpBookMark=InvestigationRaisingDtlDATA.getBookMarkListraising(userVO,deskcalllabhide,deskType);
		
		//Getting Patient Details
		
		patVO=InvestigationRaisingDtlDATA.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO,deskType);

		
		  request.getSession().setAttribute("InvpatvoCharges", patVO);
		  
		ictc=InvestigationRaisingDtlDATA.getictcdetails(patVO,userVO);

		
		if(patVO!=null&&patVO.getPatCRNo()!=null)
			{
				WebUTIL.populate(fb,patVO);
				
			
				if(patVO.getIsCatExpired()!=null&&patVO.getIsCatExpired().equals(InvestigationConfig.YESNO_FLAG_NO))
				{
					
					
				
					if(patVO.getPatStatus().equals("IPD"))
		              {
		                  // IPD case
		                  
						// Use AdmissionVO to get details from DB
					 accountNo=InvestigationRaisingDtlDATA.getAccountNo(patVO, userVO);
					    fb.setAccountNo(accountNo);
						lstPatientAdmissionVO=InvestigationRaisingDtlDATA.getPatientAdmissionDetailEssentials(patVO,userVO);
						if(lstPatientAdmissionVO!=null&&lstPatientAdmissionVO.size()>0)
						{	WebUTIL.populate(patVO,lstPatientAdmissionVO.get(0));
							fb.setAdmissionDate(patVO.getAdmissionDate());
						}

						
		              }
		              else //OPD case & Emergency case
		              {
		            	  if(deskType!=null && !deskType.equals("") && deskType.equals("1"))
		            	  {	  
		            		  Inv_EpisodeVO objInv_EpisodeVO = new Inv_EpisodeVO();
								WebUTIL.populate(objInv_EpisodeVO,patVO);
		            		  lstEpisodeVO.add(objInv_EpisodeVO);
		            	  /*if(lstEpisodeVO!=null&&lstEpisodeVO.size()>0)
		            	   
							WebUTIL.populate(patVO,lstEpisodeVO.get(0))*/;
		            	  }
		            	  else
		            	  {
			            	  lstEpisodeVO=InvestigationRaisingDtlDATA.getPatientEpisodeDetailEssentials(patVO,userVO);

		            	  }
		            	  
		                  
		              }
					
					
					//Setting Patient VO in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.PATIENT_VO,patVO);
				//	System.out.println("setting in the session   ......"+patVO.getDepartmentcode());
					// For Putting AdmissionVO in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_EPISODE_VO,lstEpisodeVO);
					
					
					// For Putting AdmissionVO in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_ADMISSION_VO,lstPatientAdmissionVO);
					
					//setting BookMark in session
					WebUTIL.setAttributeInSession(request,InvestigationConfig.MAP_BOOK_MARK,mpBookMark);
					
					if(ictc!=null)
					WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_PID_PAT,ictc);

					
					//Set Map in session
					WebUTIL.setMapInSession(mp, request);
					
					String lstSampleAccepted=(String)mp.get(InvestigationConfig.ARRAY_TESTNAMES);
					
				//	System.out.println("--ARRAY_TESTNAMES--:::"+lstSampleAccepted);
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
	public static void searchLaboratoryWiseTest(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
		Map mpcharge=new HashMap();

	try{
		
		session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
		String testcodes="";
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName()==null?"":fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup()==null?"":fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue()==null?"0":fb.getTstOrTestGroupValue());
		searchVO.setTestSearchKeyword(fb.getTestSearchKeyword()==null?"":fb.getTestSearchKeyword());
		searchVO.setPatientCrNo(fb.getPatCrNo());
		searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
	       searchVO.setRaisethrough(fb.getCallingdesk());

		searchVO.setPatAdmNo(fb.getPatAdmNo());
		
		if(session.getAttribute("IsAddendum")!=null)
		{
		   String reqno=(String) session.getAttribute("reqNo");
		   
		   
		   if(session.getAttribute("IsAddendumENTRY")==null)
		    testcodes=data.getlabcodesaddendum(reqno,userVO);
		   else
			   testcodes=InvestigationEssentialBO.getlabcodesaddendumResultentry(reqno,userVO);
		    
		    
		    String crno=	(String) session.getAttribute("PatCrNo");
	       String labCode=	(String) session.getAttribute("labCode");
	       String testCode=	(String) session.getAttribute("testCode");
	       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
	       fb.setIsAddendum(isAdddnudm);
	       fb.setLabCodee(labCode);
	       fb.setTestCodee(testcodes);
	       
	       
	       searchVO.setPatientCrNo(fb.getPatCrNo());
		
	       
		if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
		{
			
			searchVO.setIsAddendum(fb.getIsAddendum());
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setTestCode(fb.getTestCodee());
		}
		}	
 
		
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
		
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)!=null)
		{	
			searchVO.setGroupCodeEmpty("0");
		}
			
		
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
		
		mp=InvestigationRaisingDtlDATA.searchLabWiseTestDetails(searchVO,userVO,request);
			
		//mpcharge=InvestigationRaisingDtlDATA.setchargestestngroup(searchVO,userVO,request);

		if(searchVO.getSearchTestGroupName()==null||!searchVO.getTstOrTestGroupValue().equals("1"))
		{			
			//session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
		}

		WebUTIL.setMapInSession(mp, request);
		WebUTIL.setMapInSession(mpcharge, request);

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
		String testcodes="";
		InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue());
		
		if(session.getAttribute("IsAddendum")!=null)
		{
	       String crno=	(String) session.getAttribute("PatCrNo");
	       String labCode=	(String) session.getAttribute("labCode");
	       String testCode=	(String) session.getAttribute("testCode");
	       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
	       fb.setIsAddendum(isAdddnudm);
	       fb.setLabCodee(labCode);
	       fb.setTestCodee(testcodes);
	       
		
		
		if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
		{
			
			searchVO.setIsAddendum(fb.getIsAddendum());
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setTestCode(fb.getTestCodee());
		}
		}	
		
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
		
		
		mp=InvestigationRaisingDtlDATA.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO);
			
		 

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
			//System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
		//	System.out.println("fb.getNewlabtestcodearray() :::::::::::::::::::::="+fb.getNewlabtestcodearray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			String[] labTestCodeArraynew=fb.getNewlabtestcodearray().split("@");

			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			List<String> lstLabTestCodeArraynew=Arrays.asList(labTestCodeArraynew);

			
			
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			String tmpLabCodeHashTestCode_isgroup=fb.getTmpGroupCode();
			
			
			List<String> newLstLabTestCodeArray=new ArrayList<String>();
			
			boolean firstIteration=true;
			String strLabTestCodes="";
			
			Iterator itr=lstLabTestCodeArraynew.iterator();
			
			Map<String,String> mpsite=new HashMap<String, String>();
			
			while(itr.hasNext())
			{
				Iterator itrnew=lstLabTestCodeArraynew.iterator();
				String str=(String)itr.next();
				str=str.replace(";","#");
		if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				String labCodeHashTestCode_isgroup=arrStr[10] ; // is group
				
				if(tmpLabCodeHashTestCode_isgroup!=null && !tmpLabCodeHashTestCode_isgroup.equals("undefined") && !tmpLabCodeHashTestCode_isgroup.equals("")  && !tmpLabCodeHashTestCode_isgroup.equals("0"))
				{
					labCodeHashTestCode=arrStr[0]+"#"+arrStr[10];  //labCode#testCode
					tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpGroupCode();
				}
				else
				{
					
				}
				
				String site="null";
				
			//	if(arrStr.length<28) // add first time site in testcodearray only
				
				while(itrnew.hasNext())
				{
					String strnew=(String)itrnew.next();
					strnew=strnew.replace(";","#");
			if(strnew.contains("*"))
				strnew=strnew.replace("*","&");

					String[] arrStrnew=strnew.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
					String labCodeHashTestCodenew=arrStrnew[0]+"#"+arrStrnew[2];  //labCode#testCode
					
					if(tmpLabCodeHashTestCode_isgroup!=null && !tmpLabCodeHashTestCode_isgroup.equals("undefined") && !tmpLabCodeHashTestCode_isgroup.equals("")  && !tmpLabCodeHashTestCode_isgroup.equals("0"))
					{
						labCodeHashTestCodenew=arrStrnew[0]+"#"+arrStrnew[10]; 
					}
					
					if(labCodeHashTestCodenew.equals(labCodeHashTestCode))
					{
						if(arrStrnew.length==30)
						site=arrStrnew[29];
						
						mpsite.put(arrStrnew[0]+"#"+arrStrnew[2], site);
					 // break;
					}
					else
					{
						if(arrStrnew.length==30)
						site=arrStrnew[29];
						
						mpsite.put(arrStrnew[0]+"#"+arrStrnew[2], site);
					}
					}
				
				
				
				Iterator itrold=lstLabTestCodeArray.iterator();
				
				
				while(itrold.hasNext())
				{
					String strnew=(String)itrold.next();
					strnew=strnew.replace(";","#");
			if(strnew.contains("*"))
				strnew=strnew.replace("*","&");

					String[] arrStrnew=strnew.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
					String labCodeHashTestCodenew=arrStrnew[0]+"#"+arrStrnew[2];  //labCode#testCode
					
					if(tmpLabCodeHashTestCode_isgroup!=null && !tmpLabCodeHashTestCode_isgroup.equals("undefined") && !tmpLabCodeHashTestCode_isgroup.equals("")  && !tmpLabCodeHashTestCode_isgroup.equals("0"))
					{
						labCodeHashTestCodenew=arrStrnew[0]+"#"+arrStrnew[10];  //labCode#testCode
					}
					
					if(labCodeHashTestCodenew.equals(labCodeHashTestCode))
					{
						arrStr[4]=arrStrnew[4];
						mpsite.put(arrStrnew[0]+"#"+arrStrnew[2], site);
					   // break;
					}
					
					
					}
				
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
					continue;
				else
				{
					//newLstLabTestCodeArray.add(str.replace(";","#"));
					if(firstIteration)
					{
						if(arrStr.length>29)
						{
							if(mpsite.containsKey(arrStr[2]+"#"+arrStr[4]))
						    site=(String)mpsite.get(arrStr[2]+"#"+arrStr[4]);
							
							strLabTestCodes=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19]+"#"+arrStr[20]+"#"+arrStr[21]+"#"+arrStr[22]+"#"+arrStr[23]+"#"+arrStr[24]+"#"+arrStr[25]+"#"+arrStr[26]+"#"+arrStr[27]+"#"+arrStr[28]+"#"+site+"#";
						}
						else
						{
							if(mpsite.containsKey(arrStr[2]+"#"+arrStr[4]))
							    site=(String)mpsite.get(arrStr[2]+"#"+arrStr[4]);
								
							strLabTestCodes=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19]+"#"+arrStr[20]+"#"+arrStr[21]+"#"+arrStr[22]+"#"+arrStr[23]+"#"+arrStr[24]+"#"+arrStr[25]+"#"+arrStr[26]+"#"+arrStr[27]+"#"+arrStr[28]+"#"+site+"#";
						}
							//strLabTestCodes=str+site+"#";
						
						firstIteration=false;
					}
					else
					{
						
						if(arrStr.length>29)
						{
							   if(mpsite.containsKey(arrStr[2]+"#"+arrStr[4]))
							    site=(String)mpsite.get(arrStr[2]+"#"+arrStr[4]);
								
							
							strLabTestCodes=strLabTestCodes+"@"+arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19]+"#"+arrStr[20]+"#"+arrStr[21]+"#"+arrStr[22]+"#"+arrStr[23]+"#"+arrStr[24]+"#"+arrStr[25]+"#"+arrStr[26]+"#"+arrStr[27]+"#"+arrStr[28]+"#"+site+"#";
						}
						else
							{
							
							if(mpsite.containsKey(arrStr[2]+"#"+arrStr[4]))
							    site=(String)mpsite.get(arrStr[2]+"#"+arrStr[4]);
								
							strLabTestCodes=strLabTestCodes+"@"+arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19]+"#"+arrStr[20]+"#"+arrStr[21]+"#"+arrStr[22]+"#"+arrStr[23]+"#"+arrStr[24]+"#"+arrStr[25]+"#"+arrStr[26]+"#"+arrStr[27]+"#"+arrStr[28]+"#"+site+"#";
							}//	strLabTestCodes=strLabTestCodes+"@"+str+site+"#";
					}
				}
			}
			
		//	System.out.println("strLabTestCodes= "+strLabTestCodes);
			
			WebUTIL.setAttributeInSession(request, "labTestCodeArray", strLabTestCodes);
			
			//session.setAttribute("labTestCodeArray", strLabTestCodes);
			
			fb.setLabTestCodeArray(strLabTestCodes);
			fb.setNewlabtestcodearray(strLabTestCodes);
			
		//	System.out.println("fb.getNewlabtestcodearray() after processing :::::::::::::::::::::="+fb.getNewlabtestcodearray());
			
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
	public static void saveRequisitionDetails(InvestigationRaisingDtlFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();	
		HttpSession session=_request.getSession();
		List bldReqIndicationDtlVOList=new ArrayList();
		String testcodess="";
	/*	String deskType = (String) session
				.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);*/
		String deskType = "";
		
		if((_fb.getPatAdmNo()==null || _fb.getPatAdmNo().equals("") || _fb.getPatAdmNo().equals("-1")) && _fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		
		if(_fb.getPatAdmNo()!=null && !_fb.getPatAdmNo().equals("") && !_fb.getPatAdmNo().equals("-1") &&   _fb.getDepartmentUnitCode()!=null ) //calling from ipd desk
		{
			deskType="4";
		}
		
		if( _fb.getDepartmentUnitCode()==null && (_fb.getPatAdmNo()==null || _fb.getPatAdmNo().equals("-1") || _fb.getPatAdmNo().equals(""))) //simply calling from inv module
		{
			deskType="";
		}
		
		
		String cashCrNo = "";
		
		String flag="";
		Map<String,Map<String,LabTestVO>> mp= new LinkedHashMap<String,Map<String,LabTestVO>>();
		String priorityAll="";
		String[] msgBuilder;
		Inv_RequisitionRaisingPatientVO patVO=null;
		Inv_RequisitionRaisingPatientVO patVO1=null;
		try
		{
			_fb.setCashCrNo(_fb.getPatCrNo());
			cashCrNo = _fb.getPatCrNo();
			
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy hh:mm");
			UserVO _userVO=getUserVO(_request);
		//	HelperMethods.populate(bloodRequisitionDtlVO, _fb);
			Object objLabTestVo = _request.getSession().getAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
			List<LabTestVO> objLabTest = null;
			if(objLabTestVo != null)
			{
				objLabTest=(List<LabTestVO>) objLabTestVo;
			}
			
			// episode selected modify by chandan
			 List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
		
			 if(lstPatEpisodeVO.size()==0)
			 {
				 patVO=new Inv_RequisitionRaisingPatientVO();
				 patVO1=new Inv_RequisitionRaisingPatientVO();
				 patVO1= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
				 HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
				 patVO.setRequisitionraisedthrough(deskType);
			 }
			 
			 
			 for(int k=0;k<lstPatEpisodeVO.size();k++)
			 {
				 String selectedEpisodeCode=_fb.getSelectedEpisode();
				 if(lstPatEpisodeVO.get(k).getPatepisodecode().equalsIgnoreCase(selectedEpisodeCode))
				 {
					 patVO=new Inv_RequisitionRaisingPatientVO();
					 patVO1=new Inv_RequisitionRaisingPatientVO();
					 
					 patVO1= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
					 
					/*HelperMethods.populatetToNullOrEmpty(patVO, lstPatEpisodeVO.get(k));*/
					 WebUTIL.populate(patVO,lstPatEpisodeVO.get(k));
					 HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
					 patVO.setRequisitionraisedthrough(deskType);
				//	 WebUTIL.populate(patVO,patVO1);
				 }
				 
			 }
			//Getting PATVO from Session
			/*Inv_RequisitionRaisingPatientVO patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);*/
			
			
			
		//	System.out.println("alert");
			
			
			priorityAll=_fb.getPriorityAll();
			
			/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println(dateFormat.format(cal.getTime())); */
			 patVO.setVisitReason(_fb.getVisitReason());
			 patVO.setAdvisedByDocName(_fb.getAdvisedBy());
			 patVO.setAdvisedByDocNo(_fb.getAdvisedByDoctorName());
			patVO.setAdmissionDate(_fb.getAdmissionDate() );
			patVO.setPatCategoryCode(_fb.getPatCatCode());
			patVO.setFollowup(_fb.getFollowup());
			patVO.setRequisitionraisedthrough(deskType);
			if(patVO!=null)
					{
						
						//int labRowCount=Integer.parseInt(_fb.getNumberOfRow());
						
				String[] selectedLabTestCodeArray=_fb.getNewlabtestcodearray().split("@"); // changed by chandan for large no of sample add issue
						/*String[] selectedLabTestCodeArray=_fb.getLabTestCodeArray().split("@");*/
						/*msgBuilder=_fb.getLabTestCodeArray().split("@");*/
				msgBuilder=_fb.getNewlabtestcodearray().split("@");
						int labRowCount=selectedLabTestCodeArray.length;
						
						for(int i=0;i<labRowCount;i++)
						{
						
							
							String reqDNo="";
							String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
							String labCode=testCodeArray[0];
							String labName=testCodeArray[1];
							String testCode=testCodeArray[2];
							String ispidshoww="0";

							
							String pidddata=_fb.getPiddata();
							
							String[] pidddataarray=pidddata.split("##");
							
							for(int l=0;l<pidddataarray.length;l++)
							{
								String datas=pidddataarray[l];
								String labcode="";
								String testcode="";
									String ispidshow="";
								if(datas!=null && datas.length()>2)
								{
								 labcode=datas.split("@@")[0];
								 testcode=datas.split("@@")[1];
								 ispidshow=datas.split("@@")[2];
								}
								
								if(labCode.equals(labcode) && testCode.equals(testcode))
								{
									if(ispidshow.equals("1"))
									{
										ispidshoww="1";
									}
								}
								
							}
							
							
							
							testcodess+=testCode+"#";
							
							String testName=testCodeArray[3];
							String strDefSample="-1";
							for(LabTestVO objTmpLabTestVO : objLabTest)
							{
								if(labCode.equals(objTmpLabTestVO.getLabCode()) &&  testCode.equals(objTmpLabTestVO.getTestCode()))
								{
									strDefSample=objTmpLabTestVO.getDefaultSampleCode();
								}
							}
							String sampleCombo=testCodeArray[4];
							
							if(sampleCombo.contains("advised"))
									{flag="1";
								reqDNo=sampleCombo.split("&")[2];}
							else
								flag="";
							
								String deskvalues=testCodeArray[26];
								String deskvaluessplit[]=deskvalues.split("\\$");
							String isOpdDesk = deskvaluessplit[0];
							String isBayDesk = deskvaluessplit[1];
							String isIpdDesk = deskvaluessplit[2];
						String  islabappbased=testCodeArray[28];

							String sitevalue="null";
						
							if( testCodeArray.length>=30)
						        sitevalue=testCodeArray[29];
                              
							if(sitevalue.equals("null"))
							{
								sitevalue="";
							}
							
							//advicetest var
							
							/*String raiseAdvise = "";
                              deskType="1";
							if (deskType.equals("1"))
								raiseAdvise = isOpdDesk;
							else if (deskType.equals("12"))
								raiseAdvise = isBayDesk;
							else if (deskType.equals("4"))
								raiseAdvise = isIpdDesk;
							else
								raiseAdvise = isOpdDesk;
							*/
							
							
							String raiseAdvise = "";
                           // deskType="1";
							      if(!deskType.equals(""))
							if (deskType.equals("1") || deskType.equals("12")) //assume opd n bay desk same...
								raiseAdvise = isOpdDesk;
							else if (deskType.equals("4"))
								raiseAdvise = isIpdDesk;
							else
								raiseAdvise = isOpdDesk;
							
							
							
							String testType=testCodeArray[5];
							String isAppointment=testCodeArray[6];
							String isConfidential=testCodeArray[7];
							String sampleCode1=(testCodeArray[8]==null||testCodeArray[8].equals(null)||testCodeArray[8].equals("null")||testCodeArray[8].equals("-1")?strDefSample:testCodeArray[8]);
							String sampleCode="";
							if(sampleCode1 != null)
								if(sampleCode1.contains("$"))
									sampleCode= sampleCode1.split("\\$")[0];
							else
								sampleCode= sampleCode1;
									String priority=(testCodeArray[9]==null||testCodeArray[9].equals(null)||testCodeArray[9].equals("null")?"1":testCodeArray[9]);
							String testGroupCode=(testCodeArray[10]==null||testCodeArray[10].equals(null)||testCodeArray[10].equals("null")?"0":testCodeArray[10]);
							String testGroupType=(testCodeArray[11]==null||testCodeArray[11].equals(null)||testCodeArray[11].equals("null")?"1":testCodeArray[11]);
							
							String isrequisitionformneeded=(testCodeArray[24]==null||testCodeArray[24].equals(null)||testCodeArray[24].equals("null")?"0":testCodeArray[24]);
							
							String reqsampleshortname=(testCodeArray[25]==null||testCodeArray[25].equals(null)||testCodeArray[25].equals("null")?"":testCodeArray[25]);
							//String appoitmentNo=testCodeArray[19]==null||testCodeArray[19].equals(null)||testCodeArray[19].equals("null")?"0":testCodeArray[19];
						//21 and 22 are isavailable string having 2 strings # separated
							String reqdSampleName=testCodeArray[23]==null?"":testCodeArray[23];
							Map<String,LabTestVO> mpTest= mp.get(labCode);
							
						
							// First Time Insertion of Lab
							if(mpTest==null)
							{
								mpTest= new LinkedHashMap<String, LabTestVO>();
								
								LabTestVO voLabTest=new LabTestVO();
								
								//if advised test. i.e. already raised and awaiting appointment allocation
								if(flag.equals("1"))
									voLabTest.setAlreadyRaised("1");
								else
									voLabTest.setAlreadyRaised("");
								
								
								
								//Setting VO Values from labStringArray
								/*ddddd*/
								
								voLabTest.setLabCode(labCode);
								if(ispidshoww.equals("1"))
								{
								voLabTest.setPidtestcontains(pidddata);
								voLabTest.setPiddata(_fb.getPidd());
								//voLabTest.setFollowup((_fb.getFollowup());
								}
								voLabTest.setTestCode(testCode);
								if(testType.equals("P"))
								{	voLabTest.setSampleCode("-1");
								voLabTest.setReqdSampleName("");}
								else
								{	voLabTest.setSampleCode(sampleCode);
								voLabTest.setReqdSampleName(reqdSampleName);
								}
								voLabTest.setPatCrNo(_fb.getPatCrNo());
								voLabTest.setTestType(testType);
								voLabTest.setIsAppointment(isAppointment);
								voLabTest.setIslabappointmentbased(islabappbased);

								voLabTest.setRaiseAdvise(raiseAdvise);//advicetest
								
								voLabTest.setIsConfidential(isConfidential);
								voLabTest.setPriority(priority);
								
								voLabTest.setLabName(labName);
								voLabTest.setTestName(testName);
								voLabTest.setRequisitionDNo(reqDNo);
								voLabTest.setTestGroupCode(testGroupCode);
								
								voLabTest.setTestGroupType(testGroupType);
								voLabTest.setIsRequisitionFormNeeded(isrequisitionformneeded);
								voLabTest.setReqSampleShortName(reqsampleshortname);
								
						String[]	splitsysDate=sysadteString.split(" ");
								
								voLabTest.setAppointmentDate(splitsysDate[0]);
								voLabTest.setAppointmentTime(splitsysDate[1]);
								voLabTest.setFinalMandValues(_fb.getFinalMandValues());
								voLabTest.setAdvisedByDoctorName(_fb.getAdvisedByDoctorName());
								voLabTest.setAdvisedBy(_fb.getAdvisedBy());
								voLabTest.setReqDateHeaderTable(splitsysDate[0]);
								if(_fb.getFinalMandCode().equals("undefined"))
								{
								voLabTest.setFinalMandCode(_fb.getFinalMandCodeValuesOnBookmark());
								}
								else
								{
									voLabTest.setFinalMandCode(_fb.getFinalMandCode());
									
								}
								
							/*	if (raiseAdvise.equals("1")) {*/
								String labbasedappt="";
								if(session.getAttribute("labbasedapppointment")!=null)
								 labbasedappt=(String) session.getAttribute("labbasedapppointment");
								if(!labbasedappt.equals(""))
								{
								String appbaseddata[]=labbasedappt.split("#");

                                      if(labCode.equals(appbaseddata[4]))
                                      {
                                    	  voLabTest.setAppointmentDate(appbaseddata[2].split(" ")[0]);
											voLabTest.setAppointmentRefNo(appbaseddata[3]);
											voLabTest.setAppoitmentNo(appbaseddata[3]);
											voLabTest.setAppointmentTime(appbaseddata[2].split(" ")[1]);
											voLabTest.setLabbasedaptdatetime(appbaseddata[2]);
											//InvestigationRaisingDtlDATA.updateappointmentdateinheader(voLabTest,_userVO);
                                      }
								
								}
								else
									
									if(islabappbased.equals("1"))
									{
										
										
										//fetch appointment details from the array from fb
										if(_fb.getLabTestAptDate()!=null && _fb.getLabTestAptDate().length>0)
										{
												String apptArrayDate[]=_fb.getLabTestAptDate()[0].split(",");
												String apptArrayTime[]=_fb.getLabTestAptTime()[0].split(",");
												String apptArrayRefNo[]=_fb.getLabTestAptRefNo()[0].split(",");

												for(int labLen=0;labLen<apptArrayDate.length;labLen++)
												{
												
												if((voLabTest.getLabCode()+voLabTest.getTestCode()).equals(apptArrayDate[labLen].split("#")[0]))
												{
													voLabTest.setAppointmentDate(apptArrayDate[labLen].split("#")[1]);
													voLabTest.setAppointmentRefNo(apptArrayRefNo[labLen].split("#")[1]);
													voLabTest.setAppoitmentNo(apptArrayRefNo[labLen].split("#")[1]);
													voLabTest.setAppointmentTime(apptArrayTime[labLen].split("#")[1]);
												}
												}
											
											
											
										}
										else	
										{								
											
										voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
										voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
										voLabTest.setAppointmentDate(_fb.getAppointmentDate());
										voLabTest.setAppointmentTime(_fb.getAppointmentTime());
										
										}
									}
								
									else if(isAppointment.equals("1"))
								{
									
									
									//fetch appointment details from the array from fb
									if(_fb.getLabTestAptDate()!=null && _fb.getLabTestAptDate().length>0)
									{
											String apptArrayDate[]=_fb.getLabTestAptDate()[0].split(",");
											String apptArrayTime[]=_fb.getLabTestAptTime()[0].split(",");
											String apptArrayRefNo[]=_fb.getLabTestAptRefNo()[0].split(",");

											for(int labLen=0;labLen<apptArrayDate.length;labLen++)
											{
											
											if((voLabTest.getLabCode()+voLabTest.getTestCode()).equals(apptArrayDate[labLen].split("#")[0]))
											{
												voLabTest.setAppointmentDate(apptArrayDate[labLen].split("#")[1]);
												voLabTest.setAppointmentRefNo(apptArrayRefNo[labLen].split("#")[1]);
												voLabTest.setAppoitmentNo(apptArrayRefNo[labLen].split("#")[1]);
												voLabTest.setAppointmentTime(apptArrayTime[labLen].split("#")[1]);
											}
											}
										
										
										
									}
									else	
									{								
										
									voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
									voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
									voLabTest.setAppointmentDate(_fb.getAppointmentDate());
									voLabTest.setAppointmentTime(_fb.getAppointmentTime());
									
									}
								}
								
								else
								{
									voLabTest.setAppoitmentNo("0");
									
								}
								/*}
								 else if (raiseAdvise.equals("0")) {
										if (isAppointment.equals("1")) {
											voLabTest.setAppointmentRefNo("");
											voLabTest.setAppoitmentNo("0");
											voLabTest.setAppointmentDate("");
											voLabTest.setAppointmentTime("");
										} else {
											voLabTest.setAppoitmentNo("0");
										}
									}*/
								// Still Some values need to be inserted
								
								voLabTest.setAdvice(_fb.getAdvice());
								
								voLabTest.setRequisitionFormData(_fb.getRequisitionFormData());
								
								voLabTest.setSite(sitevalue);
								
								
								
								mpTest.put(testCode,voLabTest);
								
								mp.put(labCode, mpTest);
								
							}
							else
							{
								
								LabTestVO voLabTest=new LabTestVO();
								
								
								
								//if advised test. i.e. already raised and awaiting appointment allocation
								if(flag.equals("1"))
									voLabTest.setAlreadyRaised("1");
								else
									voLabTest.setAlreadyRaised("");
								
								voLabTest.setRaiseAdvise(raiseAdvise);//advicetest
								
								
								//Setting VO Values from labStringArray
								voLabTest.setLabCode(labCode);
								
								if(ispidshoww.equals("1"))
								{
								voLabTest.setPidtestcontains(pidddata);
								voLabTest.setPiddata(_fb.getPidd());
								}
								
								voLabTest.setTestCode(testCode);
								if(testType.equals("P"))
								{voLabTest.setSampleCode("-1");
								voLabTest.setReqdSampleName("");
}
							else
								{voLabTest.setSampleCode(sampleCode);	
								voLabTest.setReqdSampleName(reqdSampleName);
}
								voLabTest.setPatCrNo(_fb.getPatCrNo());
								voLabTest.setTestType(testType);
								voLabTest.setIsAppointment(isAppointment);
								voLabTest.setIslabappointmentbased(islabappbased);

								voLabTest.setIsConfidential(isConfidential);
								voLabTest.setPriority(priority);
								
								voLabTest.setLabName(labName);
								voLabTest.setTestName(testName);
								voLabTest.setRequisitionDNo(reqDNo);

								voLabTest.setTestGroupCode(testGroupCode);
								
								voLabTest.setTestGroupType(testGroupType);
								voLabTest.setIsRequisitionFormNeeded(isrequisitionformneeded);
								voLabTest.setReqSampleShortName(reqsampleshortname);
                               String[]	splitsysDate=sysadteString.split(" ");
								
								voLabTest.setAppointmentDate(splitsysDate[0]);
								voLabTest.setAppointmentTime(splitsysDate[1]);
								
								voLabTest.setReqDateHeaderTable(splitsysDate[0]);
								voLabTest.setFinalMandValues(_fb.getFinalMandValues());
								
								voLabTest.setFinalMandCode(_fb.getFinalMandCode());
								voLabTest.setAdvisedByDoctorName(_fb.getAdvisedByDoctorName());
								voLabTest.setAdvisedBy(_fb.getAdvisedBy());
							/*	if (raiseAdvise.equals("1")) {*/
								String labbasedappt="";
								if(session.getAttribute("labbasedapppointment")!=null)
								 labbasedappt=(String) session.getAttribute("labbasedapppointment");
								if(!labbasedappt.equals(""))
								{
								String appbaseddata[]=labbasedappt.split("#");

                                      if(labCode.equals(appbaseddata[4]))
                                      {
                                    	  voLabTest.setAppointmentDate(appbaseddata[2].split(" ")[0]);
											voLabTest.setAppointmentRefNo(appbaseddata[3]);
											voLabTest.setAppoitmentNo(appbaseddata[3]);
											voLabTest.setAppointmentTime(appbaseddata[2].split(" ")[1]);
											voLabTest.setLabbasedaptdatetime(appbaseddata[2]);

                                      }
								
								}
								else
							{
									if(islabappbased.equals("1"))
									{
										
										
										//fetch appointment details from the array from fb
										if(_fb.getLabTestAptDate()!=null && _fb.getLabTestAptDate().length>0)
										{
												String apptArrayDate[]=_fb.getLabTestAptDate()[0].split(",");
												String apptArrayTime[]=_fb.getLabTestAptTime()[0].split(",");
												String apptArrayRefNo[]=_fb.getLabTestAptRefNo()[0].split(",");

												for(int labLen=0;labLen<apptArrayDate.length;labLen++)
												{
												
												if((voLabTest.getLabCode()+voLabTest.getTestCode()).equals(apptArrayDate[labLen].split("#")[0]))
												{
													voLabTest.setAppointmentDate(apptArrayDate[labLen].split("#")[1]);
													voLabTest.setAppointmentRefNo(apptArrayRefNo[labLen].split("#")[1]);
													voLabTest.setAppoitmentNo(apptArrayRefNo[labLen].split("#")[1]);
													voLabTest.setAppointmentTime(apptArrayTime[labLen].split("#")[1]);
												}
												}
											
											
											
										}
										else	
										{								
											
										voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
										voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
										voLabTest.setAppointmentDate(_fb.getAppointmentDate());
										voLabTest.setAppointmentTime(_fb.getAppointmentTime());
										
										}
									}
									else if(isAppointment.equals("1"))
								{
									
									
									//fetch appointment details from the array from fb
									if(_fb.getLabTestAptDate()!=null && _fb.getLabTestAptDate().length>0)
									{
											String apptArrayDate[]=_fb.getLabTestAptDate()[0].split(",");
											String apptArrayTime[]=_fb.getLabTestAptTime()[0].split(",");
											String apptArrayRefNo[]=_fb.getLabTestAptRefNo()[0].split(",");

											for(int labLen=0;labLen<apptArrayDate.length;labLen++)
											{
											
											if((voLabTest.getLabCode()+voLabTest.getTestCode()).equals(apptArrayDate[labLen].split("#")[0]))
											{
												voLabTest.setAppointmentDate(apptArrayDate[labLen].split("#")[1]);
												voLabTest.setAppointmentRefNo(apptArrayRefNo[labLen].split("#")[1]);
												voLabTest.setAppoitmentNo(apptArrayRefNo[labLen].split("#")[1]);
												voLabTest.setAppointmentTime(apptArrayTime[labLen].split("#")[1]);
											}
											}
										
										
										
									}
									else	
									{								
										
									voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
									voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
									voLabTest.setAppointmentDate(_fb.getAppointmentDate());
									voLabTest.setAppointmentTime(_fb.getAppointmentTime());
									
									}
								}
								else
								{
									voLabTest.setAppoitmentNo("0");
								}
							}
								/*}
								else if (raiseAdvise.equals("0")) {
									if (isAppointment.equals("1")) {
										voLabTest.setAppointmentRefNo("");
										voLabTest.setAppoitmentNo("0");
										voLabTest.setAppointmentDate("");
										voLabTest.setAppointmentTime("");
									} else {
										voLabTest.setAppoitmentNo("0");
									}
								}*/
								
								// Still Some values need to be inserted
								
								
								/*dddd*/
								voLabTest.setAdvice(_fb.getAdvice());
								voLabTest.setRequisitionFormData(_fb.getRequisitionFormData());
								
								voLabTest.setSite(sitevalue);

								
								mpTest.put(testCode,voLabTest);
							}
							
							
					}
						 
						 List listReqdtlId=InvestigationRaisingDtlDATA.saveRequisitionDetails(mp,patVO, _userVO,priorityAll,session);
						 
						 String check  =InvestigationRaisingDtlDATA.getBillingCheck(_userVO);
						 
						// System.out.println("check1111::::"+check);
						 
						 
						// System.out.println("alltestcodes:::::::::::::::::::::::::::"+patVO.getPatwardcode()+"::::::::::::::"+patVO.getPatStatus()+"::::::::::::::::"+testcodess);
						 
						 
						 if(patVO.getPatStatus()!=null && patVO.getPatStatus().equalsIgnoreCase("ipd"))
						 {
							 float pi=0.0f ;
							
							 int requisitionTypeForBilling=0;
								
							   if(patVO!=null && patVO.getPatStatus().equals("IPD"))
							   {  
								   requisitionTypeForBilling=2;
								   
							   }
							   String req=Integer.toString(requisitionTypeForBilling);
							   
							 for(int k=0;k<testcodess.split("#").length;k++)
							 {
								 
								 String testcode=testcodess.split("#")[k];
								 String rates=InvestigationRaisingDtlDATA.getBillingChecktestcode(testcode,patVO.getPatwardcode(),req,patVO.getPatCategoryCode(),_userVO);
								 
								 if(rates.contains("^"))
								 {
									 rates=rates.split("\\^")[0];
									 
								 }
								 
								 float number = Float.parseFloat(rates);
								  	
								 pi+=number;
								 
							//	 System.out.println("rupeeeeeeee::testcode:"+testcode+"::"+pi);
								 
							 }
							 
						//	 System.out.println("rupeeeeeeee:total rupeeee"+ pi);
							 
							 
							 if(patVO.getPatMobileNo()!=null && pi!=0)
							 {
								 String message="Dear "+patVO.getPatFirstName().trim()+",\n"+"You investigations of Rs "+pi+" has been raised and deducted from your account."+"\n"+"Now you can move for Sample Collection."+"\n"+"AIIMS Patna";
							// SMSHttpPostClient.sendSMS (patVO.getPatMobileNo(),message);
							 }
						 }
						 
						 
						 
						 /*// send msg code
						 StringBuffer msg=new StringBuffer();
						 for(int k=0;k<msgBuilder.length;k++)
						 {
							 String[] value=msgBuilder[k].split("#");
							 msg.append("your insruction for lab "+value[3] +"is" + value[20]);
							 msg.append(",");
							 msg.setLength(msg.length() - 1);							 
							 
						 }
						 
							String   message  = "Hello "+_fb.getPatName()+","+ msg.toString()+".Thanks and Regards.NIMS";
		            		 System.out.println(message);
						 
						 if(_fb.getMobileNo()!=null)
						 {
							 SMSHttpPostClient.sendSingleSMSThroughSMSGateway(InvestigationConfig.sms_username,InvestigationConfig.sms_password,InvestigationConfig.sms_senderId,InvestigationConfig.sms_url,_fb.getMobileNo(),message);	 
							 
						 }
						 else
						 {}*/
						 
						 if( listReqdtlId.size()<=0)
						 {
								objStatus.add(Status.DONE, "Some error ocuured.Please try again.",
										"");
							 
							 
						 }
						 else
						 {
						 	StringBuilder str = new StringBuilder();
						 	for(int i=0;i<1;i++)
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
							//session.setAttribute("crNumber1", arrSaveString[0]);
							//WebUTIL.getSession(_request).setAttribute("crno987654",arrSaveString[0]);
							//WebUTIL.setAttributeInSession(_request, "crNumber1", arrSaveString[0]);
							//System.out.println(" crNumber1 :"+session.getAttribute("crNumber1")+ " : arrSaveString[0] : "+arrSaveString[0]);
							//System.out.println(WebUTIL.getSession(_request).getAttribute("crNumber1")+"hello1");
							str.append( "<br>");
							if(!arrSaveString[3].equals("2"))
							{// check pat status billing not show for ipd pat. //visit type code 1-opd, 2,3-emergency, 4 special   2-ipd
							if(!check.equals("0"))
							{
								if(!_fb.getCasualitydesk().equals(""))
								{
						//	str.append("<font color='#FF0000' size='3' face='Verdana, Arial, Helvetica, sans-serif'>");
					//		str.append("<div id='billingid'><a   href='#' onclick='showCashCollection()' class='forgetPwd' style='font-size: 18px;'><b><u>Go To Cash Collection Desk</u></b></a></div></font>");
							
								}
								
								}
							}
							else
							{
								if(!check.equals("0"))
								{
									if(!(patVO.getPatStatus().equalsIgnoreCase("ipd"))){
										if(!_fb.getCasualitydesk().equals(""))
										{
								//		str.append("<font color='#FF0000' size='3' face='Verdana, Arial, Helvetica, sans-serif'>");
							//			str.append("<div id='billingid'><a   href='#' onclick='showCashCollection()' class='forgetPwd' style='font-size: 18px;'><b><u>Go To Cash Collection Desk</u></b></a></div></font>");
										}
										}
									
								}	
								
							}
							//<img src='/HISInvestigationG5/hisglobal/images/arrow_here.gif'/>
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
							
							if(session.getAttribute("IsAddendum")!=null)
							 {
								
							//	 System.out.println("msgg:"+str.toString());
								 session.setAttribute("addendumStatusFromRaising", str.toString());
								 
								 if(_fb.getDeletedtestdataviaresultentry()!=null && !_fb.getDeletedtestdataviaresultentry().equals("")) //deleterasiedtest
								 {
									
									 InvestigationSearchVO searchVO=new InvestigationSearchVO();
									 searchVO.setDeletedtestdataviaresultentry(_fb.getDeletedtestdataviaresultentry());
									 mp=InvestigationRaisingDtlDATA.deleteReqDtll(searchVO,_userVO,null);
									 
								 }
							 }
							
							//str.append("</tr></table>");
						 	}
							
							objStatus.add(Status.DONE, str.toString(),
									"");
						 }
							//System.out.println(WebUTIL.getSession(_request).getAttribute("crNumber1")+"AAA");
					}
							
			   
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			 if(session.getAttribute("IsAddendum")!=null)
			 {
				
				 
				 session.setAttribute("addendumStatusFromRaising", e.getMessage());
			 }
		//	System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			if(session.getAttribute("IsAddendum")!=null)
			 {
				
				 
				 session.setAttribute("addendumStatusFromRaising", e.getMessage());
			 }
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			if(session.getAttribute("IsAddendum")!=null)
			 {
				
				 
				 session.setAttribute("addendumStatusFromRaising", e.getMessage());
			 }
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			System.out.println(e.getMessage());
		}
		catch (HisException e)
		{
			if(session.getAttribute("IsAddendum")!=null)
			 {
				
				 
				 session.setAttribute("addendumStatusFromRaising", e.getMessage());
			 }
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
//		mp=InvestigationRaisingDtlDATA.searchLabWiseTestDetails(searchVO,userVO);
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
		searchVO.setLabCode(fb.getAptLabCode());
		
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
		String newlabtestcodearray=fb.getNewlabtestcodearray();
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
			
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
		
		mp=InvestigationRaisingDtlDATA.searchBookMark(searchVO, userVO);
		
		List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK);
		
		
		
		if(lstLabTestVO!=null)
		for(LabTestVO vo:lstLabTestVO)
		{
			
			if(OfflineAppoitmentDtl!=null&&!OfflineAppoitmentDtl.equals("")&&searchVO.getBookMarkCode().equals(""))
			{
			vo.setOfflineAppoitMentDate(OfflineAppoitmentDtl[i]);
		    vo.setHideAptNo(hideAptNoDtl[i]);
			}
		    
			vo.setBookMarkCode(fb.getBookMarkCode());
			
		
			//added by prashant
			String appendString=null;
			String newappendString=null;
			if(!(vo.getTestGroupCode()==null) && !vo.getTestGroupCode().equals("-1")) {
				appendString=makeAppendStringC(vo,true);      //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
				
				newappendString=makeAppendString1C(vo,true);
		}
		else {
			appendString=makeAppendStringC(vo,false);      //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
			
			newappendString=makeAppendString1C(vo,false);
		}
		
			
					
					//  String appendString=makeAppendString(vo,false); 
					//  String newappendString=makeAppendString1(vo,false);
					 
			
			String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
			//Add only those Lab/Tests which are not present in the list
			if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
			{
				if(labTestCodeArray.equals(""))
				{
					labTestCodeArray=appendString;
					newlabtestcodearray=newappendString;
				}
					else
					{
					labTestCodeArray=labTestCodeArray+"@"+appendString;
					newlabtestcodearray=newlabtestcodearray+"@"+newappendString;
					}
					}
			i++;
		}
		fb.setLabTestCodeArray(labTestCodeArray);
		fb.setNewlabtestcodearray(newlabtestcodearray);
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
			//System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
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
			
			//System.out.println("strLabTestCodes= "+strLabTestCodes);
			
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
			//System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			//System.out.println("strLabTestCodes= "+lstLabTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			String tmpSampleCode=fb.getTmpSampleCode();
			
			String sampleshortname=fb.getReqSampleShortName();
			
			
			
			String strLabTestCodes="";
			boolean firstIteration=true;
			
			for(String str:lstLabTestCodeArray)
			{
				str=str.replace(";","#");

				if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				
				String site="null";
				if(arrStr[5].equalsIgnoreCase("p"))
				{
					site="1";
				}
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
				{
					// Change the Sample Code
					str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+tmpSampleCode+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19]+"#"+arrStr[20]+"#"+arrStr[21]+"#"+arrStr[22]+"#"+arrStr[23]+"#"+arrStr[24]+"#"+sampleshortname+"#"+arrStr[26]+"#"+arrStr[27]+"#"+arrStr[28]+"#"+site+"#";

				}
				
				if(firstIteration)
				{
					strLabTestCodes=str;
					firstIteration=false;
				}
				else
					strLabTestCodes=strLabTestCodes+"@"+str;
			}
			
		//	System.out.println("strLabTestCodes= "+strLabTestCodes);

		//	fb.setLabTestCodeArray(strLabTestCodes); // change by chandan for large sample no issue in micrology lab on 27-aprl-2017...
			fb.setNewlabtestcodearray(strLabTestCodes);
			
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
		//	System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
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
					str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+aptNo+arrStr[20]+arrStr[21]+arrStr[22]+arrStr[23];
					
				}
				
				if(firstIteration)
				{
					strLabTestCodes=str;
					firstIteration=false;
				}
				else
					strLabTestCodes=strLabTestCodes+"@"+str;
			}
			
		//	System.out.println("strLabTestCodes= "+strLabTestCodes);

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
		//	System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getNewlabtestcodearray());
			String[] labTestCodeArray=fb.getNewlabtestcodearray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			String tmpPriority=fb.getTmpPriority();
			
			String sampleshortname=fb.getReqSampleShortName();
			
			String strLabTestCodes="";
			boolean firstIteration=true;
			
			for(String str:lstLabTestCodeArray)
			{
				str=str.replace(";","#");

				if(str.contains("*"))
					str=str.replace("*","&");

				String[] arrStr=str.split("#");  // chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode  
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[2];  //labCode#testCode
				
				String site="null";
				if(arrStr[5].equalsIgnoreCase("p"))
				{
					site="1";
				}
				
				
				if(tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
				{
					// Change the Sample Code
				//	str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+tmpPriority+"#"+arrStr[10]+"#"+arrStr[11];
					str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+tmpPriority+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19]+"#"+arrStr[20]+"#"+arrStr[21]+"#"+arrStr[22]+"#"+arrStr[23]+"#"+arrStr[24]+"#"+sampleshortname+"#"+arrStr[26]+"#"+arrStr[27]+"#"+arrStr[28]+"#"+site+"#";


				}
				
				if(firstIteration)
				{
					strLabTestCodes=str;
					firstIteration=false;
				}
				else
				strLabTestCodes=strLabTestCodes+"@"+str;
			}
			
		//	System.out.println("strLabTestCodes= "+strLabTestCodes);

			//fb.setLabTestCodeArray(strLabTestCodes);
			fb.setNewlabtestcodearray(strLabTestCodes);

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
			
			//strTestCombo=InvestigationRaisingDtlDATA.getTestComboAJAX(labCode,userVO);
			//session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			mp=InvestigationRaisingDtlDATA.getTestComboAJAXMAP(labCode,userVO);
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
	
	public static StringBuffer pouplatePrvTestDtl(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
	    StringBuffer sbAjaxRes = new StringBuffer();
	    
	    HttpSession session = objRequest_p.getSession();
	    String strMsgText = "";
	    String strTestCombo = "";
	    Map mp = new HashMap();
	    
	    try {
	      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
	      String CrNo = fb.getTmpCrNo();
	      String fromwhichcall = "0";
	      
	      fromwhichcall = (fb.getPrev_req_fromwhichcall() == null) ? "0" : fb.getPrev_req_fromwhichcall();


	      
	      mp = InvestigationRaisingDtlDATA.getPrvTestDtlAJAXMAP(CrNo, userVO, fromwhichcall);
	      WebUTIL.setMapInSession(mp, objRequest_p);
	      
	      
	      String lstSampleAccepted = (String)mp.get("listPrvTestDtl");
	      
	      fb.setPrvTestDtl(lstSampleAccepted);
	    //  System.out.println("-------------->" + lstSampleAccepted);
	      sbAjaxRes.append(lstSampleAccepted);
	    
	    }
	    catch (Exception e) {
	      
	      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
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
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
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
			
		mp=InvestigationRaisingDtlDATA.searchTestGroup(searchVO,userVO);
		
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
			strTestCombo=InvestigationRaisingDtlDATA.getTestGroupAJAX(labCode,userVO);
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
	
	//changed by ashu
	
	public static StringBuffer getUserTestCodeVal(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session=objRequest_p.getSession();
		Map<String, LabTestVO> mapUserCodeLabTestVO =(Map<String, LabTestVO>) session.getAttribute(InvestigationConfig.MAP_USER_CODE_WISE_TEST_DTLS);
		String isTestGroup = ((InvestigationRaisingDtlFB) objRequest_p.getAttribute("InvestigationRaisingDtlFB")).getIsTestGroup();
		
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			String userTestCode=fb.getUserTestCode();
			LabTestVO voLabTest = mapUserCodeLabTestVO.get(userTestCode);
			strTestCombo=InvestigationRaisingDtlDATA.getStringToAddToRowAJAX(isTestGroup,voLabTest);
			sbAjaxRes.append(strTestCombo);
			//sbAjaxRes.append(labCode);
			//sbAjaxRes.append("10001#Biochemistry-General Medicine#10086#agetest (45699)#<option value='-1'>Select Value</option><option value='1005$ABDOMEN'>Abdomen</option><option value='1001$BLD'>Blood</option>#S#0#0#1005$ABDOMEN#1#0#0#0#null#null#null#null#null##null#null#1#1#Blood#0#null#");
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
		//System.out.println("sbAjaxRes strTestCombo : "+sbAjaxRes);
		return sbAjaxRes;
	}
	
	public static StringBuffer getRemarksForUnavailableTest(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session=objRequest_p.getSession();
		Map<String, String> mapUnavailableTestCode =(Map<String, String>) session.getAttribute(InvestigationConfig.MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS);
		//System.out.println("mapUnavailableTestCode : "+mapUnavailableTestCode);
		String strMsgText = "";
		String  remarks="1";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			String userTestCode=fb.getUserTestCode();
			//LabTestVO voLabTest = mapUserCodeLabTestVO.get(userTestCode);
			if(mapUnavailableTestCode.containsKey(userTestCode)){
				remarks = mapUnavailableTestCode.get(userTestCode);
				
				//System.out.println("userTestCode getRemarksForUnavailableTest UTL "+userTestCode+ " : remarks -- : "+remarks);
				
				if(remarks == null)
					sbAjaxRes.append("0");
				else
					sbAjaxRes.append(remarks);
			}else
				sbAjaxRes.append(remarks);
			
			
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
				
			mp=InvestigationRaisingDtlDATA.getTestsBasedOnGroups(searchVO,userVO);
			
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
				
			mp=InvestigationRaisingDtlDATA.searchBookMark(searchVO,userVO);
			
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
		{
			appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==null?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
					+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#";
		

		}else {
			appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"0"+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==""?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==""?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
					+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());

		// System.out.println("lenght:::"+appendString.split("#").length);
			
		}
 		return appendString;
	}
	
	
	public static String makeAppendStringC(LabTestVO vo,boolean isGroupBased)
	{
		String appendString="";
		
		if(isGroupBased)
		{
			/*appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==null?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
					+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#";
		*/
			appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+null+"#"+vo.getIsMandatoryReq()+"#"+null+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"##"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+"0"+"#"+"0"+"#"  //islabavailable contains 2 strings with # separated. care to be taken
			+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"; 

		}else {
			appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"0"+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==""?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==""?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
					+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());

		// System.out.println("lenght:::"+appendString.split("#").length);
			
		}
 		return appendString;
	}	

	public static String makeAppendString1(LabTestVO vo,boolean isGroupBased)
	{
		String appendString="";
		
		String newappendString="";
		
		if(vo.getTestCode().equals("12779"))
		{
			System.out.println("match ");
		}
		
		String site="null";
		if(vo.getTestType().equalsIgnoreCase("p"))
		{
			site="1";
		}
		
		if(isGroupBased)
		{
			
			String combostr="sample not carry";
			
		     newappendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+combostr+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==null?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
				+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"+site+"#";
		
		
		} 

		else
		{
			
			
			String combostr="sample not carry";
			
			newappendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+combostr+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"0"+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==""?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==""?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
					+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"+site+"#"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
		}
			// System.out.println("lenght:::"+appendString.split("#").length);
		
 		return newappendString;
	}	

	public static String makeAppendString1C(LabTestVO vo,boolean isGroupBased)
	{
		String appendString="";
		
		String newappendString="";
		
		if(vo.getTestCode().equals("12779"))
		{
		//	System.out.println("match ");
		}
		
		String site="null";
		if(vo.getTestType().equalsIgnoreCase("p"))
		{
			site="1";
		}
		
		if(isGroupBased)
		{
			
			String combostr="sample not carry";
		/*	
		     newappendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+combostr+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==null?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
				+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+site+"#";
		*/
			
			newappendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+combostr+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+null+"#"+vo.getIsMandatoryReq()+"#"+null+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"##"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+"0"+"#"+"0"+"#"  //islabavailable contains 2 strings with # separated. care to be taken
			+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"+site+"#";

		
		} 

		else
		{
			
			
			String combostr="sample not carry";
			
			newappendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+combostr+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"0"+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==""?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==""?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
					+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"+site+"#"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
		}
			// System.out.println("lenght:::"+appendString.split("#").length);
		
 		return newappendString;
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
		
		
		mp=InvestigationRaisingDtlDATA.getAptBasedTest(searchVO,userVO);
			
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
		HttpSession session=request.getSession();
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			String testcodes="";
			InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		//	searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			//searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			
			searchVO.setTestCodeWise(fb.getTestCodeWise());
			//Resetting the LabCodeArray ; for selected lab types
			//fb.setLabTestCodeArray("");
			
			
			if(session.getAttribute("IsAddendum")!=null)
			{
		       String crno=	(String) session.getAttribute("PatCrNo");
		       String labCode=	(String) session.getAttribute("labCode");
		       String testCode=	(String) session.getAttribute("testCode");
		       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
		       fb.setIsAddendum(isAdddnudm);
		       fb.setLabCodee(labCode);
		       fb.setTestCodee(testcodes);
		       
			
			
			if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
			{
				
				searchVO.setIsAddendum(fb.getIsAddendum());
				searchVO.setLabCode(fb.getLabCodee());
				searchVO.setTestCode(fb.getTestCodee());
			}
			}	
			String labTestCodeArray=fb.getLabTestCodeArray();
			String newlabTestCodeArray=fb.getNewlabtestcodearray();
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
				
			
			
			String deskType="";
			if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
			{
				deskType="1";
			}
			searchVO.setRaisethrough(deskType);
			
			mp=InvestigationRaisingDtlDATA.getTestsCodeWiseDtl(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS);
			
			for(LabTestVO vo:lstLabTestVO)
			{
				String []labDetails=new String[vo.getIsLabAvailable().split("#").length];
				labDetails=vo.getIsLabAvailable().split("#");
				if(labDetails[1].equals("1")){
					if(labDetails[0].equals("1"))
					{
						
					}
					else{
						status.add(Status.CODE_TRANS_INPROCESS,"Lab Time Out,Can not Add Test From "+vo.getLabName()+" Lab");
						break;
					}
					
					
					//labDetails 0-is LabAvailable ,1-is TimeBound
				}
				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				//vo.setTestGroupType("1");
				// add instructionpat in vo by chandan on 15th-july-2016
				String appendString=makeAppendString(vo,false);       //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType()+"#"+vo.getInstructionPat);
				
				String appendString1=makeAppendString1(vo,false); 
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
					{
						labTestCodeArray=appendString;
						newlabTestCodeArray=appendString1;
					}
						else
						{
						labTestCodeArray=labTestCodeArray+"@"+appendString;
						newlabTestCodeArray=newlabTestCodeArray+"@"+appendString1;
						}
					
					}
				
			}
			fb.setLabTestCodeArray(labTestCodeArray);
			fb.setNewlabtestcodearray(newlabTestCodeArray);
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
	   
	//checkBillDtl
	public static int checkBillDtl(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		Map mp=new HashMap();
		int billDetail = 0;
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setDelLabCode(fb.getDelLabCode());
			searchVO.setDelTestCode(fb.getDelTestCode());
			searchVO.setRequisitionNo(fb.getRequisitionNo());
			
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
		
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			billDetail=InvestigationRaisingDtlDATA.checkBillDtl(searchVO,userVO,lstPatVO);
			 
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
		
		return billDetail;
	}
	
	public static void deleteReqDtl(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		Map mp=new HashMap();
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			String isbilledornot=request.getParameter("isbilledornot");
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setDelLabCode(fb.getDelLabCode());
			searchVO.setDelTestCode(fb.getDelTestCode());
			searchVO.setRequisitionNo(fb.getRequisitionNo());
			searchVO.setIsbilledornot(isbilledornot);
			if(fb.getGroupraisedalready()!=null && !fb.getGroupraisedalready().equals("0"))
			searchVO.setGroupcode(fb.getGroupraisedalready());

			//searchVO.setRequisitionNo(fb.getRequisitionNo());
		
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			mp=InvestigationRaisingDtlDATA.deleteReqDtl(searchVO,userVO,lstPatVO);
			 
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

			boolean isTempSamplePresent = InvestigationRaisingDtlDATA.checkRequisitionPending(searchVO, voUser);
			strTestCombo=InvestigationRaisingDtlDATA.getreqStatusAJAX(searchVO, voUser);
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
		
		mp=InvestigationRaisingDtlDATA.getAptByDesk(searchVO,userVO);
			
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
			
			
			mp=InvestigationRaisingDtlDATA.getAppointment(reqNO,crNo,_userVO);
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
							String sampleCode=array[10];
							
							
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
							voLabTest.setSampleCode(sampleCode);
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
						 
						 List listReqdtlId=InvestigationRaisingDtlDATA.saveAppointmentDetails(lstLabTestVO,patVO, _userVO);
						 
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
	
	
		
		mp=InvestigationRaisingDtlDATA.getAppointmentDetailsOnClickGO(voLabTest,userVO);
		
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
		
	
	public static void deleteGroupRow(InvestigationRaisingDtlFB fb, HttpServletRequest request)
	{
		Status objStatus=new Status();	
		//List indicationIdList=new ArrayList();
		HttpSession session=WebUTIL.getSession(request);
		
		try
		{
		//	System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getLabTestCodeArray());
			String[] labTestCodeArray=fb.getLabTestCodeArray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();//here groupcode value passed in tmpTestCode
			
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
				String labCodeHashTestCode=arrStr[0]+"#"+arrStr[10];  //labCode#groupCode
				
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
			
		//	System.out.println("strLabTestCodes= "+strLabTestCodes);
			
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
	
	
	//to get tests based on user group codes
	public static void getGroupCodeWiseDtl(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
			String testcodes="";
			InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
			searchVO.setUserGroupCodeWise(fb.getUserGroupCodeWise());
			String labTestCodeArray=fb.getLabTestCodeArray();
			String newlabtestcodearray=fb.getNewlabtestcodearray();
			
			
			String labTestCodeArraynew="";
			String newlabtestcodearraynew="";
			
			if(session.getAttribute("IsAddendum")!=null)
			{
		       String crno=	(String) session.getAttribute("PatCrNo");
		       String labCode=	(String) session.getAttribute("labCode");
		       String testCode=	(String) session.getAttribute("testCode");
		       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
		       fb.setIsAddendum(isAdddnudm);
		       fb.setLabCodee(labCode);
		       fb.setTestCodee(testcodes);
		       
			
			
			if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
			{
				
				searchVO.setIsAddendum(fb.getIsAddendum());
				searchVO.setLabCode(fb.getLabCodee());
				searchVO.setTestCode(fb.getTestCodee());
			}
			}	
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
				
			
			String deskType="";
			if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
			{
				deskType="1";
			}
			searchVO.setRaisethrough(deskType);
			
			mp=InvestigationRaisingDtlDATA.getGroupCodeWiseDtl(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_GROUP_CODE_WISE_DTLS);
			
			if(lstLabTestVO!=null)
			{
			for(LabTestVO vo:lstLabTestVO)
			{
				 
			String []labDetails=new String[vo.getIsLabAvailable().split("#").length];
			labDetails=vo.getIsLabAvailable().split("#");
			if(labDetails[1].equals("1")){
				if(labDetails[0].equals("1"))
				{
					
				}
				else{
					status.add(Status.TRANSINPROCESS, "","Lab Time Out,Can not Add Test From "+vo.getLabName()+" Lab");
					break;
				}
				
				
				//labDetails 0-is LabAvailable ,1-is TimeBound
			}
				vo.setTestGroupType("1");
				String appendString=makeAppendString(vo,true);       //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());
				
				String newappendString=makeAppendString1(vo,true);
				
				if(vo.getIspidshow()!=null && vo.getIspidshow().equals("1"))
				{
					
					session.setAttribute("pidsetforgrpcase", "1");
				}
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
					{
						labTestCodeArray=appendString;
						newlabtestcodearray=newappendString;
					}
						else
						{
							labTestCodeArray=labTestCodeArray+"@"+appendString;
							newlabtestcodearray=newlabtestcodearray+"@"+newappendString;
							
						}
						
				}
			

			}
			
			
			Inv_RequisitionRaisingPatientVO patVO=null;

			patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			int requisitionTypeForBilling=0;
			String patcatcode=patVO.getPatCategoryCode();
			String wardcode="";
               
			   if(patVO.getPatStatusCode().equals("2"))
				{
				   wardcode=patVO.getPatwardcode();
					requisitionTypeForBilling=2;
				}
				else 
				{
					//visit type code 1-opd, 2,3-emergency, 4 special
					if(patVO.getPatvisittypecode()==null)
						requisitionTypeForBilling=1;
					else{
						
					
					if(patVO.getPatvisittypecode().equals("1"))
						requisitionTypeForBilling=1;
					if(patVO.getPatvisittypecode().equals("4"))
						requisitionTypeForBilling=4;
					if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
						requisitionTypeForBilling=3;
					}
					
				}
			   
			InvestigationSearchVO searchVO1=new InvestigationSearchVO();
			UserVO userVO1=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setPatientCrNo(fb.getPatCrNo());
			String requisitionTypeForBilling12=Integer.toString(requisitionTypeForBilling);
			searchVO.setRequisitingtypeforbilling(requisitionTypeForBilling12);
			searchVO.setWarcode(wardcode);
			searchVO.setPatcatcode(patcatcode);
			searchVO.setUsertestcode(fb.getUserTestCode());
			searchVO.setGroupcode(fb.getUserGroupCodeWise());
			String grpdata=newlabtestcodearray;
			searchVO.setIsamountsufficientflag(grpdata.replaceAll("#", "^"));
			searchVO.setTestCode(fb.getTestCodee());
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
		
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			String flag=InvestigationRaisingDtlDATA.issufficientbalance(searchVO,userVO,lstPatVO);
			
			if(flag.equals("1"))
			{
				fb.setLabTestCodeArray("");
				fb.setNewlabtestcodearray("");
				session.setAttribute("issuffientamountforgroup", "1");
				
			}
			
			else
			{
			//
			
			fb.setLabTestCodeArray(labTestCodeArray);
			fb.setNewlabtestcodearray(newlabtestcodearray);
			}
			
			
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	
	
	
	public static StringBuffer getRequisitionFormMasterData(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
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

	
	public static StringBuffer pouplatePrvTestDtll(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		 
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			String CrNo=fb.getRequisitionNo();
			
			//strTestCombo=InvestigationRaisingDtlDATA.getTestComboAJAX(labCode,userVO);
			//session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			if(CrNo!=null) //chandannnn
			mp=InvestigationRaisingDtlDATA.getPrvTestDtlAJAXMAPP(CrNo,userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);
			
			
			String  lstSampleAccepted=(String)mp.get(InvestigationConfig.LIST_PRVTESTDTL_AJAX_RESULT_ENTRY);
			
			session.setAttribute("raisingdetailsentry", lstSampleAccepted);
			fb.setRequisitionDetailsforResultEntry(lstSampleAccepted);
		//	System.out.println("-------------->"+lstSampleAccepted);
		//	sbAjaxRes.append(lstSampleAccepted);
			
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

	
	public static String deleteReqDtll(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		Map mp=new HashMap();
		String data="";
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setDelLabCode(fb.getDelLabCode());
			searchVO.setDelTestCode(fb.getDelTestCode());
			searchVO.setRequisitionNo(fb.getRequisitionNo());
			
			searchVO.setRequisitionNo(fb.getRequisitionNo());
			
			if(fb.getDeletedtestdataviaresultentry()!=null)
			data=fb.getDeletedtestdataviaresultentry();
			
			 data+=fb.getDelLabCode()+"$"+fb.getDelTestCode()+"$"+fb.getRequisitionNo()+"@";
			
			fb.setDeletedtestdataviaresultentry(data);
			
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			//mp=InvestigationRaisingDtlDATA.deleteReqDtll(searchVO,userVO,lstPatVO);
			 
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
		return data;
	}

	
	
	public static void searchLaboratoryWiseTestNEW(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		String testcodes="";
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName()==null?"":fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup()==null?"":fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue()==null?"0":fb.getTstOrTestGroupValue());
		searchVO.setTestSearchKeyword(fb.getTestSearchKeyword()==null?"":fb.getTestSearchKeyword());
		searchVO.setPatientCrNo(fb.getPatCrNo());
		searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
		
		if(fb.getSearchTestNamelabwise()!=null)
		searchVO.setSearchTestNamelabwise(fb.getSearchTestNamelabwise());
		
		if(session.getAttribute("IsAddendum")!=null)
		{
		   String reqno=(String) session.getAttribute("reqNo");
		   
		   if(session.getAttribute("IsAddendumENTRY")==null)
		    testcodes=data.getlabcodesaddendum(reqno,userVO);
		   else
			   testcodes=InvestigationEssentialBO.getlabcodesaddendumResultentry(reqno,userVO);
		    
		    
		    String crno=	(String) session.getAttribute("PatCrNo");
	       String labCode=	(String) session.getAttribute("labCode");
	       String testCode=	(String) session.getAttribute("testCode");
	       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
	       fb.setIsAddendum(isAdddnudm);
	       fb.setLabCodee(labCode);
	       fb.setTestCodee(testcodes);
	       
	       
	       searchVO.setPatientCrNo(fb.getPatCrNo());
		
		if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
		{
			
			searchVO.setIsAddendum(fb.getIsAddendum());
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setTestCode(fb.getTestCodee());
		}
		}	
 
		
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
		
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)!=null)
		{	
			searchVO.setGroupCodeEmpty("0");
		}
		
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
		
			
		mp=InvestigationRaisingDtlDATA.searchLabWiseTestDetailsNEW(searchVO,userVO);
			
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
	
	
	public static String issufficientbalance(InvestigationRaisingDtlFB fb,HttpServletRequest request,int requisitionTypeForBilling,String patcatcode,String wardcode)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		String flag="";
		Map mp=new HashMap();
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setPatientCrNo(fb.getPatCrNo());
			String requisitionTypeForBilling12=Integer.toString(requisitionTypeForBilling);
			searchVO.setRequisitingtypeforbilling(requisitionTypeForBilling12);
			searchVO.setWarcode(wardcode);
			searchVO.setPatcatcode(patcatcode);
			searchVO.setUsertestcode(fb.getUserTestCode());
			searchVO.setGroupcode(fb.getUserGroupCodeWise());
			searchVO.setIsamountsufficientflag(fb.getIsamountsufficientflag());
			searchVO.setTestCode(fb.getTestCodee());
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
		
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			flag=InvestigationRaisingDtlDATA.issufficientbalance(searchVO,userVO,lstPatVO);
			 
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
		
		return flag;
	}
	
	
	public static String ispidexist(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		String flag="";
		Map mp=new HashMap();
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setPatientCrNo(fb.getPatCrNo());
			searchVO.setIspidexist(fb.getIspidexist());
			
			
			
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
 
			
			
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			flag=InvestigationRaisingDtlDATA.ispidexist(searchVO,userVO);
			 
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
		
		return flag;
	}

	
	public static String isduplicatetestraisedtoday(InvestigationRaisingDtlFB fb,HttpServletRequest request,int requisitionTypeForBilling,String patcatcode,String wardcode)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		String flag="";
		Map mp=new HashMap();
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setPatientCrNo(fb.getPatCrNo());
			searchVO.setTestCode(fb.getTestCodee());
			
			
			if(fb.getUsergrpcode()!=null && !fb.getUsergrpcode().equals(""))
			{
				flag=InvestigationRaisingDtlDATA.getgrpcode(fb.getUsergrpcode(),userVO);

				String grp=flag.split("#")[0];
				String lab=flag.split("#")[1];
				fb.setLabCodee(lab);
				fb.setGroupraisedalready(grp);
				
			}
			
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setGroupcode(fb.getGroupraisedalready());
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
 
			
			
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			flag=InvestigationRaisingDtlDATA.isduplicatetestraisedtoday(searchVO,userVO,lstPatVO);
			 
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
		
		return flag;
	}
	
	public static String ispidexist(InvestigationRaisingDtlFB fb,HttpServletRequest request,int requisitionTypeForBilling,String patcatcode,String wardcode)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		String flag="";
		Map mp=new HashMap();
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setPatientCrNo(fb.getPatCrNo());
			searchVO.setTestCode(fb.getTestCodee());
		
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setGroupcode(fb.getGroupraisedalready());
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
 
			
			
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			flag=InvestigationRaisingDtlDATA.ispidexist(searchVO,userVO);
			 
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
		
		return flag;
	}
	
	
	public static String AJX_IS_LAB_MANDTORY(InvestigationRaisingDtlFB fb,HttpServletRequest request,int requisitionTypeForBilling,String patcatcode,String wardcode)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		HttpSession session=request.getSession();
		String flag="";
		Map mp=new HashMap();
		try{
			
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			
			
			searchVO.setLabCode(fb.getTmpLabCode());
			
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
 
			
			
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			flag=InvestigationRaisingDtlDATA.AJX_IS_LAB_MANDTORY(searchVO,userVO,lstPatVO);
			 
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
		
		return flag;
	}
	
	
	public static StringBuffer modifysite(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			//System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="+fb.getNewlabtestcodearray());
			String[] labTestCodeArray=fb.getNewlabtestcodearray().split("@");
			
			List<String> lstLabTestCodeArray=Arrays.asList(labTestCodeArray);
			
			String tmpLabCodeHashTestCode=fb.getTmpLabCode()+"#"+fb.getTmpTestCode();
			
			String tmpsite=fb.getTmpsite();
			
			String sampleshortname=fb.getReqSampleShortName();
			
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
				//	str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+tmpPriority+"#"+arrStr[10]+"#"+arrStr[11];
					str=arrStr[0]+"#"+arrStr[1]+"#"+arrStr[2]+"#"+arrStr[3]+"#"+arrStr[4]+"#"+arrStr[5]+"#"+arrStr[6]+"#"+arrStr[7]+"#"+arrStr[8]+"#"+arrStr[9]+"#"+arrStr[10]+"#"+arrStr[11]+"#"+arrStr[12]+"#"+arrStr[13]+"#"+arrStr[14]+"#"+arrStr[15]+"#"+arrStr[16]+"#"+arrStr[17]+"#"+arrStr[18]+"#"+arrStr[19]+"#"+arrStr[20]+"#"+arrStr[21]+"#"+arrStr[22]+"#"+arrStr[23]+"#"+arrStr[24]+"#"+arrStr[25]+"#"+arrStr[26]+"#"+arrStr[27]+"#"+arrStr[28]+"#"+tmpsite+"#";


				}
				
				if(firstIteration)
				{
					strLabTestCodes=str;
					firstIteration=false;
				}
				else
				strLabTestCodes=strLabTestCodes+"@"+str;
			}
			
			//System.out.println("strLabTestCodes= "+strLabTestCodes);

			//fb.setLabTestCodeArray(strLabTestCodes);
			fb.setNewlabtestcodearray(strLabTestCodes);

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
	
	
	
	public static String setvisittypeforappointment(InvestigationRaisingDtlFB _fb, HttpServletRequest objRequest_p)
	{
		String visittype="";
		String strMsgText = "";
         HttpSession session=objRequest_p.getSession();
		Inv_RequisitionRaisingPatientVO patVO=null;
		Inv_RequisitionRaisingPatientVO patVO1=null;

		try
		{

	// episode selected modify by chandan
				 List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
			
				 if(lstPatEpisodeVO.size()==0)
				 {
					 patVO=new Inv_RequisitionRaisingPatientVO();
					 patVO1=new Inv_RequisitionRaisingPatientVO();
					 patVO1= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
					 HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
				 }
				 
				 
				 for(int k=0;k<lstPatEpisodeVO.size();k++)
				 {
					 String selectedEpisodeCode=_fb.getSelectedEpisode();
					 if(lstPatEpisodeVO.get(k).getPatepisodecode().equalsIgnoreCase(selectedEpisodeCode))
					 {
						 patVO=new Inv_RequisitionRaisingPatientVO();
						 patVO1=new Inv_RequisitionRaisingPatientVO();
						 
						 patVO1= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
						 
						/*HelperMethods.populatetToNullOrEmpty(patVO, lstPatEpisodeVO.get(k));*/
						 WebUTIL.populate(patVO,lstPatEpisodeVO.get(k));
						 HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
					//	 WebUTIL.populate(patVO,patVO1);
					 }
					 
				 }
			
				 visittype=patVO.getPatvisittypecode();
				 
			
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
		return visittype;
	}

	
	
	public static String getesttdatatestwisexrayprocess(InvestigationRaisingDtlXrayFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		HttpSession session=objRequest_p.getSession();
		try
		{
			String testlabcode=fb.getTestlabcode();
			
			String testcode=testlabcode.split("\\^")[0];

			String labcodecode=testlabcode.split("\\^")[1];			
			
			String keyy=testcode+"#"+labcodecode;
			
			Map<String,LabTestVO> _mpp=(Map<String,LabTestVO>)session.getAttribute(InvestigationConfig.MAP_ALL_TEST_DATA_XRAY_PROCESS);

			
			if(_mpp!=null && _mpp.size()>0)
			{
				
				  Iterator itrAuto=_mpp.keySet().iterator();

				  LabTestVO vo=(LabTestVO)_mpp.get(keyy);
				  
					strMsgText=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"0"+"#"+vo.getIsMandatoryReq()+"#"+(vo.getBookMarkCode()==""?"null":vo.getBookMarkCode())+"#"+(vo.getOfflineAppoitMentDate()==""?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"#"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+(vo.getInstructionPat()==null?"NA":vo.getInstructionPat())+"#"+vo.getIsLabAvailable()+"#"  //islabavailable contains 2 strings with # separated. care to be taken
							+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
				  
			}
			
			
			
			
			
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			//sbAjaxRes.append(strLabTestCodes);
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
		return strMsgText;
	}

	
	
	public static void getcharge(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		
		session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
		String testcodes="";
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName()==null?"":fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup()==null?"":fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue()==null?"0":fb.getTstOrTestGroupValue());
		searchVO.setTestSearchKeyword(fb.getTestSearchKeyword()==null?"":fb.getTestSearchKeyword());
		searchVO.setPatientCrNo(fb.getPatCrNo());
		searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
	
		searchVO.setPatAdmNo(fb.getPatAdmNo());
		
	
		  
	
	       
	       searchVO.setPatientCrNo(fb.getPatCrNo());
	
			
		mp=InvestigationRaisingDtlDATA.getcharge(searchVO,userVO,request);
		

		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
	}	 
	
	catch(Exception e){
		status.add(Status.ERROR_AE,"Application Execution Exception", "");
		e.printStackTrace();
	}
	finally{
		WebUTIL.setStatus(request, status);
	}
	
}

	
	
	public static void setchargestestngroup(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		
		
		String testcodes="";
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName()==null?"":fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup()==null?"":fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue()==null?"0":fb.getTstOrTestGroupValue());
		searchVO.setTestSearchKeyword(fb.getTestSearchKeyword()==null?"":fb.getTestSearchKeyword());
		searchVO.setPatientCrNo(fb.getPatCrNo());
		searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
	       searchVO.setRaisethrough(fb.getCallingdesk());

		searchVO.setPatAdmNo(fb.getPatAdmNo());
		
		if(session.getAttribute("IsAddendum")!=null)
		{
		   String reqno=(String) session.getAttribute("reqNo");
		   
		   
		   if(session.getAttribute("IsAddendumENTRY")==null)
		    testcodes=data.getlabcodesaddendum(reqno,userVO);
		   else
			   testcodes=InvestigationEssentialBO.getlabcodesaddendumResultentry(reqno,userVO);
		    
		    
		    String crno=	(String) session.getAttribute("PatCrNo");
	       String labCode=	(String) session.getAttribute("labCode");
	       String testCode=	(String) session.getAttribute("testCode");
	       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
	       fb.setIsAddendum(isAdddnudm);
	       fb.setLabCodee(labCode);
	       fb.setTestCodee(testcodes);
	       
	       
	       searchVO.setPatientCrNo(fb.getPatCrNo());
		
	       
		if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
		{
			
			searchVO.setIsAddendum(fb.getIsAddendum());
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setTestCode(fb.getTestCodee());
		}
		}	
 
		
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
		
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)!=null)
		{	
			searchVO.setGroupCodeEmpty("0");
		}
			
		
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
		
		mp=InvestigationRaisingDtlDATA.setchargestestngroup(searchVO,userVO,request);
			
		if(searchVO.getSearchTestGroupName()==null||!searchVO.getTstOrTestGroupValue().equals("1"))
		{			
			//session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
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
	
	
	
	
} 
