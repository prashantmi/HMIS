package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UserVO;

public class VitalsRecordingDATA extends ControllerDATA
{
	/**
	 * Getting Vital Recording Essentials
	 * @param _patClinDtlVO
	 * @param _patClinicalDtlVO
	 * @param _lstTemplates
	 * @param _userVO
	 * @return Map of essentials
	 */
	public static Map getVitalsRecordingEssentials(PatientMonitoringMstVO _patMoniDtl, PatientClinicalDetailVO _patClinicalDtlVO, 
			List<Entry> _lstTemplates, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getVitalsRecordingEssentials(_patMoniDtl, _patClinicalDtlVO, _lstTemplates, _userVO); 
	}
	
	/** Getting the List of Ward on the basis of Unit
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public static List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getWardOnBasisOfUnitCode(unitCode,userVO); 
	}
}
