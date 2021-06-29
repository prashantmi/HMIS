package opd.transaction.controller.data;
/**
 * @copyright CDAC
 * @developer Hruday Meher
 */

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class OpdDiagnosisDATA extends ControllerDATA {
	
	/** Getting data for ICD diagnosis
	 * @param _userVO
	 * @param _patCrNo
	 * @param _episodeCode
	 * @return
	 */
	public static Map getIcdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getIcdDiagnosisEssential(_patDtlVO, _userVO); 
	}
	
	/** Getting data for Hospital diagnosis
	 * @param _userVO
	 * @param _patCrNo
	 * @param _episodeCode
	 * @return
	 */
	public static Map getHospitalDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getHospitalDiagnosisEssential(_patDtlVO, _userVO);
	}
	
	/** Getting data for Hospital diagnosis
	 * @param _userVO
	 * @param _patCrNo
	 * @param _episodeCode
	 * @return
	 */
	public static Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getSnomdDiagnosisEssential(_patDtlVO, _userVO);
	}
	
	
	
	/** Getting list on the basis of ICD Code
	 * @param _searchCode
	 * @param _userVO
	 * @return
	 */
	public static List getIcdCodes(String _searchCode,UserVO _userVO){
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getIcdCodes(_searchCode,_userVO);
	}
	
	/** Getting list on the basis of ICD Disease name
	 * @param _searchDisease
	 * @param _userVO
	 * @return
	 */
	public static List getDiseaseName(String _searchDisease, UserVO _userVO){
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getDiseaseName(_searchDisease,_userVO);
	}
	
	/**
	 * Getting List of ICD Diseases on the basis of Search Criteria
	 * 
	 * @param _strSearch Search String either ICD Code or Disease Name Segment
	 * @param _strSearchType Search Style either Code-based or Name-based
	 * @param _userVO User Detail
	 * @return List ICD Disease Detail
	 */
	public static List<IcdDiseaseMasterVO> getICDCodesSearchDetail(String _strSearch, String _strSearchType, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getICDCodesSearchDetail(_strSearch, _strSearchType, _userVO);
	}

	/**
	 * Getting List of ICD Sub Diseases on the basis of Clicked Parent ICD Disease Code
	 * 
	 * @param _strICDCode Clicked ICD Code
	 * @param _userVO User Detail
	 * @return List of ICD Sub Disease
	 */
	public static List<IcdDiseaseMasterVO> getICDSubDiseases(String _strICDCode, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getICDSubDiseases(_strICDCode, _userVO);
	}

	/** Saving Patient Diagnosis
	 * @param episodeDiagnosisVO
	 * @param userVO
	 */
	public static void saveOpdDiagnosisDetails(EpisodeDiagnosisVO[] episodeDiagnosisVO,UserVO userVO){
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		patientDelegate.saveOpdDiagnosisDetails(episodeDiagnosisVO,userVO);
	}
	
	/** Getting list on the basis of Hospital Disease name
	 * @param _searchDisease
	 * @param _userVO
	 * @return
	 */
	public static List getHospitalDiagnosisName(String _searchDisease,UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getHospitalDiagnosisName(_searchDisease,_userVO);
	}
	
	/** Getting list on the basis of Hospital Code
	 * @param _searchCode
	 * @param _userVO
	 * @return
	 */
	public static List getHospitalDiagnosisCodes(String _searchCode,UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getHospitalDiagnosisCodes(_searchCode,_userVO);
	}
	
	/** Revoking Patient diagnosis
	 * @param revokeCode
	 * @param episodeDiaVO
	 * @param userVO
	 */
	public static void revokeDiagnosis(String revokeCode,EpisodeDiagnosisVO episodeDiaVO,UserVO userVO)
	{
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		patientDelegate.revokeDiagnosis(revokeCode,episodeDiaVO,userVO);
	}
	
	//Not In Use
	/** Getting Both ICD & Hospital Diagnosis Essential Detail
	 * @param _userVO
	 * @param _patCrNo
	 * @param _episodeCode
	 * @return
	 */
	public static Map getIcdNHospitalDiagnosisEssential(UserVO _userVO,String _patCrNo,String _episodeCode)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getIcdNHospitalDiagnosisEssential(_userVO,_patCrNo,_episodeCode); 
	}
	
	public static List getEssentialDiagnosisDetail(EpisodeDiagnosisVO previousDiagVO,UserVO userVO){
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		return patientDelegate.getEssentialDiagnosisDetail(previousDiagVO,userVO);
	}
	
	
	/*AddedBy: NehaRajgariya Date:28July2016*/
	public static String getIcdHospCode(String deptUnitCode,UserVO userVO) {
		// TODO Auto-generated method stub
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		return patientDelegate.getIcdHospCode(deptUnitCode,userVO);
	}
	
	
}
