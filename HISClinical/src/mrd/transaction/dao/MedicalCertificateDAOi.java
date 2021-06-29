package mrd.transaction.dao;

import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.UserVO;

public interface MedicalCertificateDAOi 
{
	
	/**  Getting The List of All The Episodes of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public EpisodeVO[] getAllEpisodeOfThePatient(String crNo,UserVO userVO);
	
	/**  Saving the Medical Certificate Details 
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public void create(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO);
	
	/** Checking for Already Generated Medical Certificate
	 * @param patMedicalDtlVO
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] checkExistingRecord(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO);
	
	/** Getting the List of Medical Certificate Generated for the Patient On a particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] getIssuedMedicalCertificate(String crNo,String epiCode,UserVO userVO);
	
	/** Generating the Medical Certificate Id
	 * @param patMedicalDtlVO
	 * @param unitCode
	 * @param genMode
	 * @param userVO
	 * @return
	 */
	public String generateCertificateId(PatMedicalDtlVO patMedicalDtlVO,String unitCode,String genMode,UserVO userVO);
	
	/** Updating the Record
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public void update(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO);
	
	/** Checking for Already Existing Record
	 * @param patMedicalDtlVO
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] checkExistingRecordForExtend(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO);
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO);
	
}
