package inpatient.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.MonitorVitalsDATA;
import inpatient.transaction.controller.fb.MonitorVitalsFB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MoinitorVitalsUTL extends ControllerUTIL
{

	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static boolean getEssentails(MonitorVitalsFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_request);
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			UserDeskMenuTemplateMasterVO _userDeskTempVO=new UserDeskMenuTemplateMasterVO();
			// Setting Desk Essentials
			// User Seat, Unit, Ward
			_fb.setUserSeatId(userVO.getUserSeatId());
			//_fb.setDeptUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			_fb.setWardCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
			// Episode, Visit
			/*DailyPatientVO[] al = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO voDP = new DailyPatientVO();
			for (int i = 0; i < al.length; i++)
			{
				voDP = (DailyPatientVO) al[i];
				if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
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
			_fb.setDeptUnitCode(voDP.getDepartmentUnitCode());
			// Desk Type, Desk Id, Desk Menu Name
			String _deskType=(String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			_fb.setDeskId((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID));
			
			HelperMethods.populate(_userDeskTempVO, _fb);
			
			List<Entry> templateParaList = MonitorVitalsDATA.getEssentails(_deskType, _userDeskTempVO, userVO);
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.TEMPLATE_PARAMETER_LIST, templateParaList);
			
			
			////////////////////////////////////////////////////////////////////////////////
			
			PatientMonitoringMstVO patMonitringMstVO=new PatientMonitoringMstVO();
			PatientMonitoringMstVO[] _patMonitringMstVO=null;
			
			HelperMethods.populate(patMonitringMstVO, voDP);
			_patMonitringMstVO = MonitorVitalsDATA.getVitalDetail(patMonitringMstVO, userVO);
			
			for(int k=0;k<_patMonitringMstVO.length;k++)
			{
				if(_patMonitringMstVO[k].getRemarks()==null)
				{
					_patMonitringMstVO[k].setRemarks("");
				}
			}
			Iterator itr=templateParaList.iterator();
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();

				for(int j=0;j<_patMonitringMstVO.length;j++)
				{
					if(obj.getValue().equals(_patMonitringMstVO[j].getParaId()))
					{
						itr.remove();
					}
				}
				
			}
			WebUTIL.setAttributeInSession(_request, InpatientConfig.SELECTED_PARAMETER_LIST, _patMonitringMstVO);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	public static void addRow(MonitorVitalsFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		PatientMonitoringMstVO[] patMonitoringVital=null;
		PatientMonitoringMstVO[] patientMonitoringVital=null;
		PatientMonitoringMstVO[] patientMonitor=null;
		List lstTemp=null;
		String paraName="";
		
		try
		{
			lstTemp=(ArrayList)session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
			for(int i=0;i<lstTemp.size();i++)
			{
				Entry ent=(Entry)lstTemp.get(i);
			    if( ent.getValue().equalsIgnoreCase(_fb.getParaId()))
			    {
			    	paraName=ent.getLabel();
			    	break;
			    }
			}
			
			List newList = new ArrayList(lstTemp);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,_fb.getParaId()); 
			WebUTIL.setAttributeInSession(_request,InpatientConfig.TEMPLATE_PARAMETER_LIST,newList);
			
			patMonitoringVital=(PatientMonitoringMstVO[])session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);
			
			if(patMonitoringVital==null)
			{
				patientMonitoringVital=new PatientMonitoringMstVO[1];
				
				patientMonitoringVital[0]=new PatientMonitoringMstVO();
				patientMonitoringVital[0].setParaId(_fb.getParaId());
				patientMonitoringVital[0].setParaName(paraName);
				patientMonitoringVital[0].setRemarks(_fb.getVitalRemarks());
				patientMonitoringVital[0].setDuration(_fb.getVitalDuration());
				
				WebUTIL.setAttributeInSession(_request,InpatientConfig.ARR_PAT_VITAL_MONITOR , patientMonitoringVital);
			}
			else
			{
				patientMonitor=new PatientMonitoringMstVO[patMonitoringVital.length+1];
				int i=0;
				for(;i<patMonitoringVital.length;i++)
				{
					patientMonitor[i]=patMonitoringVital[i];
				}
				patientMonitor[i]=new PatientMonitoringMstVO();
				
				patientMonitor[i].setParaId(_fb.getParaId());
				patientMonitor[i].setParaName(paraName);
				patientMonitor[i].setRemarks(_fb.getVitalRemarks());
				patientMonitor[i].setDuration(_fb.getVitalDuration());
				
				WebUTIL.setAttributeInSession(_request,InpatientConfig.ARR_PAT_VITAL_MONITOR , patientMonitor);
			}
			objStatus.add(Status.TRANSINPROCESS);
			
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
	
	public static void deleteRow(MonitorVitalsFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		PatientMonitoringMstVO[] patMonitoringVitalVO=null;
		PatientMonitoringMstVO[] patientMonitoringVitalVO=null;
		
		try
		{
			List lstTemp=(List)session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
			List newList = new ArrayList(lstTemp);
			newList=(List) WebUTIL.addEntryToOptions(newList,_fb.getHiddenParaId(),_fb.getHiddenParaName()); 
			WebUTIL.setAttributeInSession(_request,InpatientConfig.TEMPLATE_PARAMETER_LIST,newList);
			
			patMonitoringVitalVO=(PatientMonitoringMstVO[])session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);
			patientMonitoringVitalVO=new PatientMonitoringMstVO[patMonitoringVitalVO.length-1];
			
			int j=0;
			for(int i=0;i<patMonitoringVitalVO.length;i++)
			{
				if(!_fb.getHiddenParaId().equals(patMonitoringVitalVO[i].getParaId()))
				{
					patientMonitoringVitalVO[j]=patMonitoringVitalVO[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(_request,InpatientConfig.ARR_PAT_VITAL_MONITOR , patientMonitoringVitalVO);
			objStatus.add(Status.TRANSINPROCESS);
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

	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void saveDetail(MonitorVitalsFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		PatientMonitoringMstVO[] patMonitoringVitalVO=null;
		PatientMonitoringMstVO[] patientMonitoringVitalVO=null;
		PatientDetailVO[] dailyPatientVOs = null;
		int count;
		int j=0;
		
		try
		{
			/*DailyPatientVO[] al = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO voDP = new DailyPatientVO();
			for (int i = 0; i < al.length; i++)
			{
				voDP = (DailyPatientVO) al[i];
				if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
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
			
///////Start Addition of New Vitals////////////
			
			patMonitoringVitalVO=(PatientMonitoringMstVO[])session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR);
			
			if(patMonitoringVitalVO==null)
				if(_fb.getParaId().equals("-1"))
					count=0;
				else
					count=1;
			else
				if(_fb.getParaId().equals("-1"))
					count=patMonitoringVitalVO.length;
				else
					count=patMonitoringVitalVO.length+1;
			
			if(count>0)
			{
				patientMonitoringVitalVO=new PatientMonitoringMstVO[count];
				if(!_fb.getParaId().equals("-1"))
				{
					patientMonitoringVitalVO[j]=new PatientMonitoringMstVO();
					HelperMethods.populate(patientMonitoringVitalVO[0], voDP);
					patientMonitoringVitalVO[j].setParaId(_fb.getParaId());
					patientMonitoringVitalVO[j].setDuration(_fb.getVitalDuration());
					patientMonitoringVitalVO[j].setRemarks(_fb.getVitalRemarks());
					patientMonitoringVitalVO[j].setInstructionMode(InpatientConfig.ENTER_BY_DOCTOR);
					patientMonitoringVitalVO[j].setVarifyFlag(InpatientConfig.VITALS_VARIFFIED_FLAG_BY_DOCTOR);
					j++;
				}
				
				if(patMonitoringVitalVO!=null)
				{
					
					int len=patMonitoringVitalVO.length;
					for(int i=0;i<len;i++)
					{
						patientMonitoringVitalVO[j]=new PatientMonitoringMstVO();
						
						HelperMethods.populate(patientMonitoringVitalVO[j], voDP);
						patientMonitoringVitalVO[j].setParaId(patMonitoringVitalVO[i].getParaId());
						patientMonitoringVitalVO[j].setDuration(patMonitoringVitalVO[i].getDuration());
						patientMonitoringVitalVO[j].setRemarks(patMonitoringVitalVO[i].getRemarks());
						patientMonitoringVitalVO[j].setInstructionMode(InpatientConfig.ENTER_BY_DOCTOR);
						patientMonitoringVitalVO[j].setVarifyFlag(InpatientConfig.VITALS_VARIFFIED_FLAG_BY_DOCTOR);
						j++;
					}
				}
			}	
			
///////End Addition of New Vitals////////////
			
///////Start Modification of  Vitals////////////
			
			String[] modParaId=_fb.getModChk();
			String[] modDuration=_fb.getModDuration();
			String[] modRemarks=_fb.getModRemarks();
			String[] modSerialNo=_fb.getArrSerialNo();
			
			PatientMonitoringMstVO[] modPatMonitringMstVO=null;
			if(modDuration!=null)
			{
				modPatMonitringMstVO=new PatientMonitoringMstVO[modDuration.length];
			}
			if(modDuration!=null)
			{
				for(int i=0;i<modDuration.length;i++)
				{
					modPatMonitringMstVO[i]=new PatientMonitoringMstVO();
					
					HelperMethods.populate(modPatMonitringMstVO[i], voDP);
					
					modPatMonitringMstVO[i].setDuration(modDuration[i]);
					modPatMonitringMstVO[i].setParaId(modParaId[i]);
					modPatMonitringMstVO[i].setRemarks(modRemarks[i]);
					modPatMonitringMstVO[i].setSerialNo(modSerialNo[i]);
				}
			}
			
///////End Modification of  Vitals////////////
			
			
			MonitorVitalsDATA.saveVitalsDetail(patientMonitoringVitalVO,modPatMonitringMstVO,getUserVO(_request));
			objStatus.add(Status.DONE,"","Record added Successfully");
			
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
	
	public static void revokeVitals(MonitorVitalsFB _fb,HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		PatientMonitoringMstVO patMonitoringVitalVO=new PatientMonitoringMstVO();
		
		try
		{
			
			DailyPatientVO[] arrayDailyPatVO = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
					selectedPatientVO = arrayDailyPatVO[i];
			
			String revokeParaId=_fb.getRevokeParaId();
			HelperMethods.populate(patMonitoringVitalVO, selectedPatientVO);
			MonitorVitalsDATA.revokeVitals(revokeParaId,patMonitoringVitalVO,getUserVO(_request));
			
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}
}
