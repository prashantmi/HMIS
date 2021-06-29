package registration.dao;

import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public interface EpisodeDiagnosisDAOi
{
	public void create(EpisodeDiagnosisVO _episodediagVO, UserVO _userVO);

	/**
	 * Getting Episode Diagnosis Detail for Patient Profile
	 * 
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO
	 * @param episodeVisitNo 
	 * @param profileGenerationMode 
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public EpisodeDiagnosisVO[] retrieveEpisodeDiagnosisForProfile(String _crNo, PatientDetailVO voDp, UserVO _userVO, String profileGenerationMode);

	
	/**
	 * Getting Episode Diagnosis Detail for Patient EMR
	 * 
	 * @param _crNo Patient CR No.
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs FOR ALL EPISODE WITH MAX VISIT
	 */
	public EpisodeDiagnosisVO[] retrieveEpisodeDiagnosisForEMR(String _crNo,String departmentUnitArray[],String accessType,UserVO _userVO);
	
	/**
	 * get the patient's current disease detail
	 * @param _crNo
	 * @param _userVO
	 * @return
	 */
	public EpisodeDiagnosisVO[] retrieveCurrentEpisodeDiagnosisForEMR(String _crNo,UserVO _userVO);
	
	/**
	 * Getting Episode Diagnosis Detail Episode and Visit wise for Patient EMR
	 * 
	 * @param EpisodeDiagnosisVO
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs FOR ALL EPISODE WITH MAX VISIT
	 */
	public EpisodeDiagnosisVO[] retrieveEpisodeDiagnosisEpisodeVisitWiseForEMR(EpisodeDiagnosisVO _episodeDiagnosisVO,UserVO _userVO);

	
	public EpisodeDiagnosisVO[] retrieveAdmissionDiagnosisForProfile(String _crNo, String _episodeCode, UserVO _userVO);

	/**
	 * remove the diagnosis detail by setting gnum_isvalid=0
	 * @param episodeDiaVO
	 * @param userVO
	 */
	public void removeDiagnosis(EpisodeDiagnosisVO episodeDiaVO,UserVO userVO);
}
