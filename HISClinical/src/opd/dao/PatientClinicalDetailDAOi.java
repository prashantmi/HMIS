package opd.dao;

import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface PatientClinicalDetailDAOi 
{
	/**
	 * Getting Episode-Centric Clinical Template Data (OPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientEpisodeClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO);

	/**
	 * Getting Patient Detail Clinical Template Data in Case of Admitted Patient (IPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientClinicalRecordData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO);

	/**
	 * Getting Episode-Centric Clinical Template Data Template Wise (OPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstTemps 
	 * @param _userVO User VO
	 * @return Array of GenericTemplateUtility.TempParameter Objects 
	 */
	public GenericTemplateUtility.TempParameter[] getPatientEpisodeClinicalDataTempWise(PatientClinicalDetailVO _patClinicalVO, 
			List<Entry> _lstTemps, UserVO _userVO);

	/**
	 * Getting Patient Detail Clinical Template Data in Case of Admitted Patient Template Wise (IPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstTemps 
	 * @param _userVO User VO
	 * @return Array of GenericTemplateUtility.TempParameter objects 
	 */
	public GenericTemplateUtility.TempParameter[] getPatientClinicalRecordDataTempWise(PatientClinicalDetailVO _patClinicalVO, 
			List<Entry> _lstTemps, UserVO _userVO);

	/**
	 * Getting Patient-Centric Clinical Template Data
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of tempId/Map of paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientCentricClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO);

	/**
	 * Getting Patient Clinical Template Data Map
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO);

	/** Inserts Patient Clinical Detail if present Update otherwise Insert
	 * @param _patClinDtlVO PatientClinicalDetailVO
	 * @param _userVO User VO
	 */
	public void savePtientClinicalDetail(String _deskType, PatientClinicalDetailVO _patClinDtlVO, UserVO _userVO);

	/**
	 * Getting Clinical Record Date List
	 * @param _patAdmNo Patient Admission No
	 * @param _userVO User VO
	 * @return List of Entry objects of Record Data / Record Date
	 */
	public List<Entry> getClinicalRecordDateList(String _patAdmNo,String deskMenuId, UserVO _userVO);

	/**
	 * Getting Patient Clinical Template Data Map
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientsOnlyCentricClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO);

	/**
	 * Getting Vital Last Record Value
	 * @param _patClinVO Patient Detail
	 * @param params Vitals
	 * @param _userVO User Detail
	 * @return List of Values in VO
	 */
	public List<PatientClinicalDetailVO> getVitalsLastValues(PatientClinicalDetailVO _patClinVO, String[] params,UserVO _userVO);
	
	/**
	 * Getting All Vitals Detail 
	 * @param _patClinVO Patient Detail
	 * @param _userVO User Detail
	 * @return PatientClinicalDetailVO[] 
	 */
	public PatientClinicalDetailVO[] getPatientVitalsEMR(PatientClinicalDetailVO _patClinVO,String [] departmentUnitArray,String accessType,UserVO _userVO);
	
	/**
	 * Getting All Complaints By CrNo Detail 
	 * @param _patClinVO Patient Detail
	 * @param _userVO User Detail
	 * @return PatientClinicalDetailVO[] 
	 */
	public PatientClinicalDetailVO[] getPatientComplaintsByCrNoEMR(PatientClinicalDetailVO _patClinVO,String _templateCategory,UserVO _userVO);
}
