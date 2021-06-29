package opd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.UserVO;

public class EpisodeAllergyDATA extends ControllerDATA {
	
	/** Getting Essential data for patient Allergy
	 * @param _userVO
	 * @return
	 */
	public static Map getAllergyType(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getAllergyType(_userVO);
	}
	
	/*public static EpisodeAllergiesVO[] getEpisodeAllergiesByPatient(DailyPatientVO _dailyPatientVO,UserVO _userVO)
	{
		OpdPatientDelegate opdPatDelegate= new OpdPatientDelegate();
		return opdPatDelegate.getEpisodeAllergiesByPatient(_dailyPatientVO,_userVO);
	}*/
	
	/** Getting all the previous allergies of the patient
	 * @param _dailyPatientVO
	 * @param _userVO
	 * @return
	 */
	public static PatAllergyDtlVO[] getEpisodeAllergiesByPatient(DailyPatientVO _dailyPatientVO,UserVO _userVO)
	{
		OpdPatientDelegate opdPatDelegate= new OpdPatientDelegate();
		return opdPatDelegate.getEpisodeAllergiesByPatient(_dailyPatientVO,_userVO);
	}
	
	/** Getting list of allergies name based on allergy type
	 * @param _allergyCode
	 * @param _userVO
	 * @return
	 */
	public static List getAllergiesEssential(String _allergyCode,UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getAllergiesEssential(_allergyCode,_userVO);
	}
	
	/** Saving patient allergies
	 * @param _episodeAllergiesVO
	 * @param _userVO
	 */
	public static void saveAllergyDetails(EpisodeAllergiesVO[] _episodeAllergiesVO,UserVO _userVO){
		OpdPatientDelegate opdPatDelegate= new OpdPatientDelegate();
		opdPatDelegate.saveAllergyDetails(_episodeAllergiesVO,_userVO);
	}
	
	/**Getting the allergies detail of the given allergy
	 * @param selAllergyID
	 * @param selAllergyCode
	 * @param _dailyPatientVO
	 * @param _userVO
	 * @return
	 */
	public static EpisodeAllergiesVO[] getAllergyDtlEpisodeWise(String selAllergyID,String selAllergyCode,DailyPatientVO _dailyPatientVO,UserVO _userVO)
	{
		OpdPatientDelegate opdPatDelegate= new OpdPatientDelegate();
		return opdPatDelegate.getAllergyDtlEpisodeWise(selAllergyID,selAllergyCode,_dailyPatientVO,_userVO);
	}
	
	/** Getting the list of allergy Site
	 * @param userVO
	 * @return
	 */
	public static List getAllAllergySite(UserVO userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getAllAllergySite(userVO);
	}
	
	/** Getting the list of allergy symptom
	 * @param allergyTypeCode
	 * @param userVO
	 * @return
	 */
	public static Map getCommonNAllergyTypeSymptom(String allergyTypeCode,UserVO userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getCommonNAllergyTypeSymptom(allergyTypeCode,userVO);
	}
	
	/** Getting the list of advice
	 * @param userVO
	 * @return
	 */
	public static List getAdvice(UserVO userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getAdvice(userVO);
	}
	
	/** Revoking the allergy
	 * @param epiAllergyVO
	 * @param userVO
	 */
	public static void revokeAllergy(EpisodeAllergiesVO epiAllergyVO, UserVO userVO )
	{
		OpdPatientDelegate opdPatDelegate= new OpdPatientDelegate();
		opdPatDelegate.revokeAllergy(epiAllergyVO,userVO);
	}

}
