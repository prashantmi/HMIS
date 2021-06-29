package mrd.transaction.dao;

import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.UserVO;

public interface FitnessCertificateDAOi 
{
	
	/**  Saving the Fitness Date of The Patient
	 * @param patFitnessDtlVO
	 * @param userVO
	 */
	public void create(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO,String id);
	
	/** Checking For Already Exist Fitness Date
	 * @param patFitnessDtlVO
	 * @param userVO
	 * @return
	 */
	public String checkEsistingFitnessDate(PatFitnessDtlVO patFitnessDtlVO,UserVO userVO);
	
	public String generateFitnessCertificateId(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO);
	
}
