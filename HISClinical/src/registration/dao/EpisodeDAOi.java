package registration.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;
/**
 * EpisodeDAOi is an interface that declares CRUD methods associated with HRGT_EPISODE_DTL table.
 * @author AHIS
 */
public interface EpisodeDAOi {

	/**
	 * Creates an entry in DB for an episode of a patient.
	 */
	public EpisodeVO create(EpisodeVO  _episodeVO,UserVO _userVO);

	/**
     * Retrieves all the episodes of a patient which are still open on the basis of CR No.
     * Also checks that the episodes should be valid and active.
     */
	public EpisodeVO[] retrieveByCrNo(String crNo, UserVO _userVO);	
	
	public void updateEpisodeDtl(EpisodeVO _episodeVO,UserVO _userVO);

	/**
	 * Updates an episode record with the MLC number in the DB.
	 */
	public void episodeUpdateMlcNo(MlcVO _mlcVO, UserVO _userVO);

	/**
     * Retrieves the episode of a patient which was opened in emergency on the basis of CR No.
     * Also checks that the episodes should be valid and active.
     */
	public EpisodeVO retrieveEmgEpisodeByCrNo(PatientVO _patientVO);
	
	/**
	 * Updates an episode record with the Referral Acceptance Date of a patient 
	 * if the patient is referred from one department to some other department.
	 * If a ptient is referred internally, his previous episode detail record is updated for referral acceptance date.
	 */
	public void episodeUpdateReferralAcceptanceDate(EpisodeReferVO _episodeReferVO, UserVO _userVO);
	
	/**
     * Retrieves the episode of a patient.
     */
	public EpisodeVO retrieveByEpisodeNo(EpisodeVO _episodeVO, UserVO _userVO);
	

	/**
	 * Updates an entry in DB for referral record of an episode of a patient.
	 * Used at Registration desk.
	 */
	public int updateEpisodeDtlAtREG(HisDAO daoObj,EpisodeVO _episodeVO, UserVO _userVO);

    /**
     * Retrieves the departments in which a patient's episode is alreay opened.
     */
    public List getOpenEpisodeDepartment(EpisodeVO _episodeVO, UserVO _userVO);
    

	/**
	 * Updates an entry in DB for change of primary and secondary category of a patient during an episode.
	 */
	public int updateSecondaryCategoryEpisodeDtl(EpisodeVO _episodeVO, UserVO _userVO);
	

	/**
     * Retrieves all the episodes of a patient.
     * Also checks that the episodes should be valid and active.
     */
	public EpisodeVO[] retrieveAllByCrNo(PatientVO _patientVO, UserVO _userVO);


	/**
     * Checks whether any episode for a patient which was opened in emergency on the basis of CR No.
     * Also checks that the episodes should be valid and active.
     */
	public int checkOpenEmgEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO);
	
	/**
     * Retrieves all the episodes of a patient where patient has been referred.
     * Also checks that the episodes should be valid and active.
     */
	public EpisodeVO[] retrieveAllReferredEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO);
	
	public EpisodeVO getLastEmergencyEpisode(PatientVO _patVO, UserVO _userVO);
	
	public String isPatientMLC(PatientVO _patientVO, UserVO _userVO);
	

	/**
     * Retrieves all episodes of a patient currently opened in OPD.
     * Also checks that the episodes should be valid and active.
     */
	public EpisodeVO[] retrieveAllOpenOPDByCrNo(String crNo, UserVO _userVO);
	
	/**
     * Retrieves file number of the patient for each deaprtment.
     * Also checks that the episodes should be valid.
     */
	public Map getDeptWiseFileNo(String crNo, UserVO _userVO);
	
	public EpisodeVO[] getDeptWiseFileNoChange(String crNo, UserVO _userVO);
	

	//public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, String primCatCode, UserVO _userVO);

	public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, UserVO _userVO);

	
	public String[] getDeptForRenewal(String crNo, UserVO _userVO);
	
	public String[] getDeptNoCollectionAtNewDeptVisit(String crNo, UserVO _userVO);
	

	public boolean saveRenewalDtl(EpisodeVO _episodeVO, UserVO _userVO);
	
	public String[] getEpisdoeCode(String crNo, String deptUnitCode,UserVO _userVO);
	

	public EpisodeVO[] retrieveOldPatientEpisodes(String crNo, UserVO _userVO);
	
	public EpisodeVO[] retrieveAllEpisodes(String crNo, UserVO _userVO);
	
	public void changeEpisodeDetails(EpisodeVO _episodeVO,UserVO _userVO);
	
	public void updatePatCategory(EpisodeVO _episodeVO,UserVO _userVO);
	
	/**
	 * Retrieves all visit details of an episode of patient
	 * @param	_episodeVO Epispde Detail
	 * @param	_userVO	User Detail
	 * @return	List of EpisodeVO
	 */
	public List<EpisodeVO> retrieveAllVisitsByEpisodeNo(EpisodeVO _episodeVO, UserVO _userVO);
	public void episodeDetailUpdateMlcFlag(MlcVO _mlcVO, UserVO _userVO);
	public void patientDetailUpdateMlcFlag(MlcVO _mlcVO, UserVO _userVO);
	public void dailyPatientDetailUpdateMlcFlag(MlcVO _mlcVO, UserVO _userVO);
	
}
