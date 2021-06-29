package inpatient.transaction.controller.utl;

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
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.ExtAdministrationDATA;
import inpatient.transaction.controller.fb.ExtAdministrationFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;

public class ExtAdministrationUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(ExtAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			
			String sys=(String)session.getAttribute(Config.SYSDATE);

			String time=sys.split(" ")[1];

			_fb.setSysHours(time.split(":")[0]);
			_fb.setSysMinut(time.split(":")[1]);
						
			_fb.setSysDate(sysadteString);
			_fb.setTimeLimit(InpatientConfig.TIME_LIMIT_FOR_EXTERNAL_TREATMENT);
			_fb.setBeforeTimeLimit(InpatientConfig.BEFORE_TIME_LIMIT_FOR_EXTERNAL_TREATMENT);
			
			// Setting Desk Essentials
			// Episode, Visit, Admission No
			/*PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}*/

			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			_fb.setPatCrNo(voDP.getPatCrNo());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setAdmissionNo(voDP.getPatAdmNo());
			_fb.setAdmissionDate(voDP.getAdmDateTime());
			
			mpEssentials=ExtAdministrationDATA.getPatExtTreatmentDetailEssential(voDP, userVO);
			
			
			//identify which is sosIvfluid or which scheduledIvFluid
			List inProcessIvFuidList=(List)mpEssentials.get(InpatientConfig.SELECTED_IVFLUIDS_LIST);
			if(inProcessIvFuidList!=null)
			{
				for(int i=0;i<inProcessIvFuidList.size();i++)
				{
					DrugAdminDtlVO vo=(DrugAdminDtlVO)inProcessIvFuidList.get(i);
					if(vo.getDoseTime()==null)
					{
						vo.setSosFlag(InpatientConfig.IS_SOS_IVFLUID);
					}
					else
					{
						vo.setSosFlag(InpatientConfig.IS_NOT_SOS_IVFLUID);
					}
					if(vo.getRemarks()==null)
					{
						vo.setRemarks("");
					}
				}
			}
			
			
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
			List todayExtList=(List)mpEssentials.get(InpatientConfig.TODAY_EXT_TREATMENT_LIST_FOR_PAT);
			List activityList=(List)mpEssentials.get(InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT);
			if((todayExtList==null || todayExtList.size()==0) && (activityList==null || activityList.size()==0) )
			{
				objStatus.add(Status.DONE, " ", "No other treatment or activity for execution");
			}
			else
				objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	
	
	
	public static void addNewActivityRow(ExtAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		
		try
		{
			setSysdate(_rq);
			//Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			//String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			
			List extAdminDtlVOList=null;
			extAdminDtlVOList=(List)session.getAttribute(InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST);
			
			if(extAdminDtlVOList==null)
			{
				extAdminDtlVOList=new ArrayList();
			}
			
			ExtAdminDtlVO vo=new ExtAdminDtlVO();
						
			vo.setExtTreatmentId(_fb.getActivityExtId());
			vo.setAdministrationTime(_fb.getActivitySelStartExecTimeHrs()+":"+_fb.getActivitySelStartExecTimeMin());
			vo.setRemarks(_fb.getActivitySelRemarks());
				
			//get and set ext treatment  name
			List activityList=(List)session.getAttribute(InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT);
			for(int i=0;i<activityList.size();i++)
			{
				Entry obj=(Entry)activityList.get(i);
				if(obj.getValue().equals(_fb.getActivityExtId()))
				{
					vo.setExtTreatmentName(obj.getLabel());
				}
			}
						
			 vo.setPatCrNo(_fb.getPatCrNo());
			 vo.setPatAdmNo(_fb.getAdmissionNo());
			 vo.setEpisodeCode(_fb.getEpisodeCode());
			 vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			 vo.setAdminFlag(OpdConfig.NEW_RECORD);
			
			
			 extAdminDtlVOList.add(vo);
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST, extAdminDtlVOList);
			//session.setAttribute(InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST, drugAdminDtlVOList);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}                                                                                          
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	
		
	public static void deleteActivityRow(ExtAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		List activityExtAdminList=null;
		try
		{
			setSysdate(_rq);
			activityExtAdminList =(List)session.getAttribute(InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST);
			
			int rowIndex = Integer.parseInt(_fb.getIndex());
			int j=0;
			Iterator itr=activityExtAdminList.iterator();
			
			while(itr.hasNext())
			{
				//ExtAdminDtlVO vo=(ExtAdminDtlVO)
				itr.next();
				
				if(rowIndex==j)
				{
					itr.remove();
				}
				
				j++;
			}
			session.setAttribute(InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST, activityExtAdminList);
			
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}                                                                                          
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	} 
	
	// saving drug Execution Dtl
	public static void saveExtAdmin(ExtAdministrationFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		
		try
		{	
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			_fb.setAdministrationDate(sysadteString);
								
			/***************************Saving activity**********************************************/
			
			//saving first row of activity 
			
			List actvityList=(List)session.getAttribute(InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST);
			if(actvityList==null)
			{
				actvityList=new ArrayList();
			}
			if(_fb.getActivityExtId()!=null && _fb.getActivityExtId()!="")
			{
				ExtAdminDtlVO vo=new ExtAdminDtlVO();
				
				vo.setExtTreatmentId(_fb.getActivityExtId());
				vo.setAdministrationTime(_fb.getActivitySelStartExecTimeHrs()+":"+_fb.getActivitySelStartExecTimeMin());
				vo.setRemarks(_fb.getActivitySelRemarks());
					
				//get and set ext treatment  name
				List activityList=(List)session.getAttribute(InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT);
				for(int i=0;i<activityList.size();i++)
				{
					Entry obj=(Entry)activityList.get(i);
					if(obj.getValue().equals(_fb.getActivityExtId()))
					{
						vo.setExtTreatmentName(obj.getLabel());
					}
				}
							
				 vo.setPatCrNo(_fb.getPatCrNo());
				 vo.setPatAdmNo(_fb.getAdmissionNo());
				 vo.setEpisodeCode(_fb.getEpisodeCode());
				 vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				 vo.setAdminFlag(OpdConfig.DRUG_GIVEN_IN_PER_SCHEDULE);
				 vo.setTreatmentType(OpdConfig.ONE_TIME_ACTIVITY);
				 
				 actvityList.add(vo);
				
			}
			
			//saving activity list
			if(actvityList!=null)
			{
				for(int i=0;i<actvityList.size();i++)
				{
					ExtAdminDtlVO vo=(ExtAdminDtlVO)actvityList.get(i);
					vo.setTreatmentType(OpdConfig.ONE_TIME_ACTIVITY);
					vo.setAdminFlag(OpdConfig.DRUG_GIVEN_IN_PER_SCHEDULE);
					vo.setAdministrationTime(sysadteString+" "+vo.getAdministrationTime());
				}
			}
			
			/**********************saving external treatment execution********************************************/
			
			List extAdminVoList=new ArrayList();
			
			String[] extSelIndexArray= _fb.getExtSelIndexArray();
			if(extSelIndexArray!=null)
			{
				for(int i=0;i<extSelIndexArray.length;i++)
				{
					ExtAdminDtlVO vo=new ExtAdminDtlVO();
					
					Integer ind=Integer.parseInt(extSelIndexArray[i]);
					vo.setAdministrationTime(sysadteString+" "+_fb.getExtSelStartExecutionTimeHrs()[ind]+":"+_fb.getExtSelStartExecutionTimeMin()[ind]);
					vo.setAdminFlag(OpdConfig.DRUG_GIVEN_IN_PER_SCHEDULE);
					if(_fb.getExtSelIsDurationBound()[i].equals(InpatientConfig.IS_DURATION_BOUND_YES))
					{
						vo.setAdministrationEndTime(sysadteString+" "+_fb.getExtSelEndExecutionTimeHrs()[ind]+":"+_fb.getExtSelEndExecutionTimeMin()[ind]);
					}
					
					vo.setAdviceDate(_fb.getExtSelAdviceDateArray()[ind]);
					vo.setSerialNo(_fb.getExtSelSerealNoArray()[ind]);
					vo.setPatCrNo(_fb.getPatCrNo());
					vo.setRemarks(_fb.getExtSelRemarksArray()[ind]);
					vo.setEpisodeCode(_fb.getEpisodeCode());
					
					extAdminVoList.add(vo);
				}
			}
			
			
			ExtAdministrationDATA.saveExtTreatment(actvityList,extAdminVoList,getUserVO(_request));
			// Nursing Desk Data Set
			PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(_request, _fb.getDeskMenuId());

			objStatus.add(Status.TRANSINPROCESS,"","Treatment Given Successfully");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
}
