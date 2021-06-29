package mrd.transaction.controller.data;

import mrd.transaction.delegate.MrdDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.UserVO;

public class FitnessCertificateDATA extends ControllerDATA
{
	/** Getting The List of All The Episodes of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static EpisodeVO[] getAllEpisodeOfThePatient(String crNo,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllEpisodeOfThePatient(crNo,userVO);
	}
	
	/** Getting the List of Medical Certificate Generated for the Patient On a particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public static PatMedicalDtlVO[] getMCListOfThePat(String crNo,String epiCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getIssuedMedicalCertificate(crNo,epiCode,userVO);
	}
	
	/** Saving the Fitness Date of The Patient
	 * @param patFitnessDtlVO
	 * @param userVO
	 */
	public static void saveFitnessDate(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveFitnessDate(patFitnessDtlVO,unitCode,genMode, userVO);
	}
}
