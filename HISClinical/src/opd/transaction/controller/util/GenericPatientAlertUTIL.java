package opd.transaction.controller.util;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import opd.OpdConfig;
import opd.transaction.controller.data.GenericPatientAlertDATA;
import opd.transaction.controller.fb.GenericPatientAlertFB;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDeadPatientException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;

public class GenericPatientAlertUTIL extends ControllerUTIL
{
	/** Getting All The Assigned Alerts of the Patient & List of Alert Names
	 * @param fb
	 * @param request
	 */
	public static void getEssentialForPatAlerts(GenericPatientAlertFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		
		try
		{
			session.removeAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
			InpatientDetailUTL.getInpatientDetailByCrNo(fb, request);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus=(String)patientVO.getIsDead();
			fb.setHiddenPatDeadStatus(patDeathStatus);
			
			/*InpatientDetailUTL.getInpatientDetailByCrNo(fb, request);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus=(String)patientVO.getPatStatusCode();
			if(patDeathStatus.equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
			{
				throw new HisDeadPatientException("Patient is Dead");
			}
			else
			{*/
				setSysdate(request);
				//  Getting All The Assigned Alerts of the Patient
				List<PatientAlertsDetailVO> patAssignedAlertVO = GenericPatientAlertDATA.getPatientAssignedAlert(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO, patAssignedAlertVO);
				
				// List of Alert Names that are not assigned to the Patient
				List lstAlert=GenericPatientAlertDATA.getAllPatAlerts(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST, lstAlert);
				
				PatientDetailVO patVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				String dob="";
				if(patVO!=null)
					dob=patVO.getPatDOB();
				fb.setDob(dob);
				if(lstAlert.size()>0)
					objStatus.add(Status.TRANSINPROCESS);
				else
					objStatus.add(Status.TRANSINPROCESS,"","No Alert Found");
		//	}	
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
		catch (HisDeadPatientException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	// Adding the Value of first Row in the VO
	public static void addRow(GenericPatientAlertFB fb, HttpServletRequest request)
	{
		Status  objStatus=new Status();
		HttpSession session=request.getSession();
		List<PatientAlertsDetailVO> lstAddedPatAlerts = null;
		List<Entry> lstAlert = null;
		String alertName = "";
		
		try
		{
			setCheckedRevoke(fb,request);

			/*lstAlert=(List<Entry>)session.getAttribute(OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST);
			for(Entry ent : lstAlert)
			    if( ent.getValue().equalsIgnoreCase(fb.getPatAlertId()))
			    {
			    	alertName=ent.getLabel();
			    	break;
			    }
			
			List newList = new ArrayList(lstAlert);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getPatAlertId()); 
			WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST,newList);
			
			*/
			lstAddedPatAlerts=(List<PatientAlertsDetailVO>)session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
			
			if(lstAddedPatAlerts==null)
				lstAddedPatAlerts = new ArrayList<PatientAlertsDetailVO>();

			PatientAlertsDetailVO voPatAlert = new PatientAlertsDetailVO();
			voPatAlert.setAlertName(fb.getAlertName());
			voPatAlert.setPatAlertId(fb.getPatAlertId());
			voPatAlert.setDurationDate(fb.getDurationDate());
			voPatAlert.setRemarks(fb.getRemarks());
			lstAddedPatAlerts.add(voPatAlert);
			WebUTIL.setAttributeInSession(request, OpdConfig.ARR_ADDED_PATIENT_ALERT, lstAddedPatAlerts);
			
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
			WebUTIL.setStatus(request,objStatus);
		}
		
	}	
	
	private static void setCheckedRevoke(GenericPatientAlertFB fb, HttpServletRequest request)
	{
		Status  objStatus=new Status();
		HttpSession session=request.getSession();
		List<PatientAlertsDetailVO> patAssignedAlertVO = null;
		try
		{
			patAssignedAlertVO = (ArrayList<PatientAlertsDetailVO>)session.getAttribute(OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO);
			for(PatientAlertsDetailVO vo : patAssignedAlertVO)
			{
				vo.setCheckedRevoke(false);
				vo.setRevokeRemarks(null);
			}
			int i=0;
			if(fb.getRevokeChronics()!=null)
			{
				for(String chk : fb.getRevokeChronics())
				{
					for(PatientAlertsDetailVO vo : patAssignedAlertVO)
						if(chk.trim().equalsIgnoreCase(vo.getPatAlertId().trim()))
						{
							vo.setCheckedRevoke(true);
							vo.setRevokeRemarks(fb.getRevokeRemarks()[i++]);
							break;
						}
				}
			}
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	// Deleting the Row of VO
	public static void deleteRow(GenericPatientAlertFB fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		List<PatientAlertsDetailVO> lstAddedPatAlerts = null;
		List lstAlert=null;
		
		try
		{
			setCheckedRevoke(fb,_request);

			lstAlert=(List)session.getAttribute(OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST);
			
			List newList = new ArrayList(lstAlert);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenPatAlertId(),fb.getHiddenAlertName()); 
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST,newList);
			
			lstAddedPatAlerts = (List<PatientAlertsDetailVO>)session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);			
			
			for(PatientAlertsDetailVO vo : lstAddedPatAlerts)
				if(fb.getHiddenPatAlertId().equals(vo.getPatAlertId()))
				{
					lstAddedPatAlerts.remove(vo);
					break;
				}
			WebUTIL.setAttributeInSession(_request,OpdConfig.ARR_ADDED_PATIENT_ALERT , lstAddedPatAlerts);
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
	
	//  Saving the Patient Alerts
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void savePatientAlerts(GenericPatientAlertFB fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		List<PatientAlertsDetailVO> patAssignedAlertVO = null;
		List<PatientAlertsDetailVO> lstAddedPatAlerts = null;
		List<PatientAlertsDetailVO> lstRevokedPatAlerts = new ArrayList<PatientAlertsDetailVO>();

		List<Entry> lstAlert=null;
		String alertName="";
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{		
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
			fb.setEpisodeCode(voDP.getEpisodeCode());
			
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if(vo.getPatCrNo().equals(fb.getPatCrNo()))
				{
					voDP = vo;
					break;
				}*/
			
			// Revoked alerts List if Any
			setCheckedRevoke(fb, _request);
			patAssignedAlertVO = (List<PatientAlertsDetailVO>)session.getAttribute(OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO);

			int i=0;
			for(PatientAlertsDetailVO vo : patAssignedAlertVO)
				if(vo.isCheckedRevoke())
				{
					lstRevokedPatAlerts.add(vo);
					vo.setRemarks(fb.getRevokeRemarks()[i++]);

					HelperMethods.populate(vo, voDP);
					vo.setAdmissionNo(voDP.getPatAdmNo());
				}
			
			// New Added Alerts(Chronic Diseases) 
			lstAddedPatAlerts = (List<PatientAlertsDetailVO>)session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
			if(lstAddedPatAlerts==null)
				lstAddedPatAlerts = new ArrayList<PatientAlertsDetailVO>();
			else
			{
				for(PatientAlertsDetailVO vo : lstAddedPatAlerts)
				{
					HelperMethods.populate(vo, voDP);
					vo.setAdmissionNo(voDP.getPatAdmNo());
				}
			}
			
			//if(!fb.getPatAlertId().trim().equalsIgnoreCase("-1"))
			if(!fb.getPatAlertId().equalsIgnoreCase(""))
			{
			
				/*lstAlert=(List<Entry>)session.getAttribute(OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST);
				for(Entry ent : lstAlert)
				    if( ent.getValue().equalsIgnoreCase(fb.getPatAlertId()))
				    {
				    	alertName=ent.getLabel();
				    	break;
				    }
*/
				
				PatientAlertsDetailVO voPatAlert = new PatientAlertsDetailVO();
				voPatAlert.setPatAlertId(fb.getPatAlertId());
				voPatAlert.setAlertName(fb.getAlertName());
				voPatAlert.setRemarks(fb.getRemarks());
				voPatAlert.setDurationDate(fb.getDurationDate());
				HelperMethods.populate(voPatAlert, voDP);
				voPatAlert.setAdmissionNo(voDP.getPatAdmNo());
				
				lstAddedPatAlerts.add(voPatAlert);
			}

			//GenericPatientAlertDATA.revokeAlerts(patientAlertDtlVO,getUserVO(request));
			//objStatus.add(Status.DONE,"","Patient Chronic Disease is  Revoked");
			
			GenericPatientAlertDATA.savePatientAlerts(lstAddedPatAlerts,lstRevokedPatAlerts,getUserVO(_request));
			objStatus.add(Status.DONE,"","Patient Chronic Disease Updated Successfully");
			session.removeAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
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
	// Revoking the Alerts of the Patient
	public static void revokeAlerts(GenericPatientAlertFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		PatientAlertsDetailVO patientAlertDtlVO=new PatientAlertsDetailVO();
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{
			String revokeAlertId=fb.getRevokeAlertId();
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
					selectedPatientVO = arrayDailyPatVO[i];*/
			
			HelperMethods.populate(patientAlertDtlVO, patientDetailVO);
			List<PatientAlertsDetailVO> patAssignedAlertVO=(List<PatientAlertsDetailVO>)session.getAttribute(OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO);
			for(PatientAlertsDetailVO vo : patAssignedAlertVO)
			{
				if(revokeAlertId.equals(vo.getPatAlertId()))
				{
					patientAlertDtlVO.setAlertName(vo.getAlertName());
					patientAlertDtlVO.setDurationDate(vo.getDurationDate());
				}
			}
			int i=Integer.parseInt(fb.getIndex());
			patientAlertDtlVO.setRemarks(fb.getRevokeRemarks()[i]);
			patientAlertDtlVO.setRevokeRemarks(fb.getRevokeRemarks()[i]);
			patientAlertDtlVO.setPatAlertId(revokeAlertId);
			
			GenericPatientAlertDATA.revokeAlerts(patientAlertDtlVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Patient Chronic Disease is  Revoked");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	// Getting All Alerts of the Patient
	public static void getAllPatientAlert(GenericPatientAlertFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			PatientAlertsDetailVO[] patAllAlertVO=GenericPatientAlertDATA.getAllPatientAlert(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,OpdConfig.ARR_ALL_PATIENT_ALERT_VO , patAllAlertVO);
			if(patAllAlertVO.length>0)
				objStatus.add(Status.TRANSINPROCESS);
			else
				objStatus.add(Status.TRANSINPROCESS,"","No Record Found");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	public static void calculateDay(GenericPatientAlertFB fb, HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			objStatus.add(Status.NEW, "", "");
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}
}
