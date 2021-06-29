package inpatient.transaction.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.bo.GenericTemplateEssentialBO;
import hisglobal.vo.MonitoringModeMstVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

public class MonitorVitalsDATA extends ControllerDATA 
{
	/** 
	 * Getting the List of Parameter
	 * @param _deskType
	 * @param _userDeskTempVO
	 * @param _userVO
	 * @return
	 */
	public static List<Entry> getEssentails(List<Entry> lstTemplates, UserVO _userVO)
	{
		GenericTemplateEssentialBO serviceBO = new GenericTemplateEssentialBO();
		return serviceBO.getEntryParametesListOfTemplates(lstTemplates, _userVO);
	}
	
	/**
	 * Getting Essentials for Monitoring Vitals
	 * @param _deskType Desk Type
	 * @param _userDeskMenuTempVO User Desk Menu Detail
	 * @param _patClinicalVO Patient Clinical Data VO
	 * @param patMonitringMstVO Pat Monitoring VO
	 * @param _userVO User VO
	 * @return Map of Essentials
	 */
	public static Map<String, Object> getEssentailsForMonitoringVital(String _deskType, UserDeskMenuTemplateMasterVO _userDeskMenuTempVO, 
			PatientClinicalDetailVO _patClinicalVO, PatientMonitoringMstVO _patMonitringMstVO, UserVO _userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getEssentailsForMonitoringVital(_deskType,_userDeskMenuTempVO,_patClinicalVO,_patMonitringMstVO,_userVO);
	}

	/** Saving the Vital Details of the Patient
	 * @param patMonitringMstVO
	 * @param modPatMonitringMstVO
	 * @param userVO
	 */
	public static void saveVitalsDetail(PatientMonitoringMstVO[] patMonitringMstVO,PatientMonitoringMstVO[] modPatMonitringMstVO,UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		delegate.saveVitalsDetail(patMonitringMstVO,modPatMonitringMstVO,userVO); 
	}
	
	/** Getting the Vital Details of the Patient
	 * @param patMonitringMstVO
	 * @param userVO
	 * @return
	 */
	public static PatientMonitoringMstVO[] getVitalDetail(PatientMonitoringMstVO patMonitringMstVO,UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getVitalDetail(patMonitringMstVO,userVO); 
	}
	
	/** Revoking the Vitals of the Patient
	 * @param paraId
	 * @param patMonitoringVitalVO
	 * @param userVO
	 */
	public static void revokeVitals(String paraId,PatientMonitoringMstVO patMonitoringVitalVO,UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		delegate.revokeVitals(paraId,patMonitoringVitalVO,userVO);
	}
	
	public static MonitoringModeMstVO[] getMonitorMode(UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getMonitorMode(userVO);
	}
	
}
