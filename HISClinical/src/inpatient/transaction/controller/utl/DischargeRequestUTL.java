package inpatient.transaction.controller.utl;

import hisglobal.utility.Entry;
import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatientDetailVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DischargeRequestDATA;
import inpatient.transaction.controller.fb.DischargeRequestFB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import registration.RegistrationConfig;

public class DischargeRequestUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getPatientStatus(DischargeRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		boolean flag=true;
		String profileStatus="";
		String compolsory="0";		//Flag For Compulsory or not 0-->NO	1-->YES
		PatientDetailVO[] dailyPatientVOs = null;
		System.out.println("ssss");
		try
		{
			setSysdate(request);
			/*PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(voDP.getPatCrNo());
			
			Map map=DischargeRequestDATA.getDischargeStatusListNProfileStatus(voDP,getUserVO(request));
			
			session.setAttribute(InpatientConfig.DISCHARGE_STATUS_LIST, map);//addded by swati sagar on date:11-06-2019
			
			WebUTIL.setMapInSession(map, request);
			
			if(compolsory.equals(InpatientConfig.CHECK_DISCHARGE_SUMMARY_GENERATED_REQUIRED_NO))
			{
				System.out.println("ifffff");
				flag=DischargeRequestDATA.getPatientStatus(voDP.getPatAdmNo(),getUserVO(request));
					if(flag){
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
						
					}
					else
					{
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
						PatDischargeReqDtlVO patDischargeReqVO=DischargeRequestDATA.getDischargeRemarks(voDP.getPatAdmNo(),getUserVO(request));
						fb.setDischargeRemarksDisabled(patDischargeReqVO.getRemarks());
						/*if(patDischargeReqVO.getNextVisitDate()==null||patDischargeReqVO.getNextVisitDate().equals("")){
							
							fb.setNextVisitDate("-");
						}*/
						//else{
						fb.setNextVisitDate(patDischargeReqVO.getNextVisitDate());//added by swati on date :11-06-2019
					//	}
						fb.setDischargeStatus(patDischargeReqVO.getDischargeStatus());//added by swati on date :11-06-2019
						System.out.println("nextvisitdateeeestausssss....."+patDischargeReqVO.getDischargeStatus());
						System.out.println("nextvisitdateeee....."+fb.getNextVisitDate());
						
						List<Entry> lstDischargeStatus= new ArrayList<Entry>();
						lstDischargeStatus=(List<Entry>)map.get(InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST);
						System.out.println("fb.getDischargeStatus()"+fb.getDischargeStatus());
						for(int i=0;i<lstDischargeStatus.size();i++){
							System.out.println("gettttt");
							if(fb.getDischargeStatus().equals(lstDischargeStatus.get(i).getValue())){
								System.out.println("gettttftyhdt"+lstDischargeStatus.get(i).getValue());
								fb.setDischargeStatusValue(lstDischargeStatus.get(i).getLabel());
							}
						}
					
					}
					objStatus.add(Status.TRANSINPROCESS);
			}	
			else
			{
				System.out.println("elsesss");
				profileStatus=(String)map.get(InpatientConfig.ESSENTIAL_PROFILE_STATUS);
				fb.setProfileStatus(profileStatus);
				if(profileStatus.equals(OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED))
				{
					flag=DischargeRequestDATA.getPatientStatus(voDP.getPatAdmNo(),getUserVO(request));
					if(flag)
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
					else
					{
						fb.setPatStatus(InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
						PatDischargeReqDtlVO patDischargeReqVO=DischargeRequestDATA.getDischargeRemarks(voDP.getPatAdmNo(),getUserVO(request));
						fb.setDischargeRemarksDisabled(patDischargeReqVO.getRemarks());
						fb.setNextVisitDate(patDischargeReqVO.getNextVisitDate());//added by swati on date :11-06-2019
						System.out.println("nextvisitdateeee"+fb.getNextVisitDate());
					}
						objStatus.add(Status.TRANSINPROCESS);
				}
				else
					objStatus.add(Status.UNSUCESSFULL,"","Discharge Summary Not Created");
			}
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
	
	public static void saveDischargeRequest(DischargeRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatDischargeReqDtlVO patDischargeReqVO=new PatDischargeReqDtlVO();
		
		try
		{
			PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO=null;
			if(arrayDailyPatVO!=null)
			{
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
					break;
				}
			}
			}
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus=(String)patientVO.getPatStatusCode();
			
			if(patDeathStatus.equalsIgnoreCase(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
				patDischargeReqVO.setIsDead("1");
			else
				patDischargeReqVO.setIsDead("0");
			
			
			if(fb.getNextVisitDateFlag()!=null && !(fb.getNextVisitDateFlag().equals("-2")))
			{
				
				if(fb.getNextVisitDateFlag().equals(InpatientConfig.NEXT_VISIT_SELECTION_DATE))
				{
					
					if(fb.getNextVisitDate()==null){
						System.out.println("nullllllvalue");
						fb.setNextVisitDate("");
					}
					
					else{
						System.out.println("nullllllvalueelse");
					patDischargeReqVO.setNextVisitDate(fb.getNextVisitDate());
					
					}
			    }
			
				else
				{
					System.out.println("flaggggggg"+fb.getNextVisitDateFlag());
					String date=fb.getSysDate();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(date));
					int i=Integer.parseInt(fb.getNextVisitDays());
					c.add(Calendar.DATE, i);		//Getting The Previous Date of Fitness Date	
					String nextDate = sdf.format(c.getTime()); 
					
					patDischargeReqVO.setNextVisitDate(nextDate);
				}
			}
			
			patDischargeReqVO.setPatCrNo(patientVO.getPatCrNo());
			patDischargeReqVO.setPatAdmNo(patientVO.getPatAdmNo());
			patDischargeReqVO.setEpisodeCode(patientVO.getEpisodeCode());
			patDischargeReqVO.setEpisodeVisitNo(patientVO.getEpisodeVisitNo());
			
			patDischargeReqVO.setDischargeStatus(fb.getDischargeStatus());
			
			if(fb.getPatStatus().equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED ))
			{
				patDischargeReqVO.setRemarks(fb.getDischargeRemarks());
				patDischargeReqVO.setRequestType(InpatientConfig.DISCHARGE_REQUEST_TYPE_REQUEST);
			}
			else
			{
				patDischargeReqVO.setRemarks(fb.getRevokeRemarks());
				patDischargeReqVO.setRequestType(InpatientConfig.DISCHARGE_REQUEST_TYPE_REVOKE);
			}
			
			//List lstDischargeStatus=new ArrayList();
		//	lstDischargeStatus=session.getAttribute(InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST);
		//	essentialMap.get(InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST);
			
			
		//	lstDischargeStatus= (List)session.getAttribute(InpatientConfig.DISCHARGE_STATUS_LIST);
			
			DischargeRequestDATA.saveDischargeRequest(fb.getPatStatus(),patDischargeReqVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Remarks Added Successfully");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		

			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
}
