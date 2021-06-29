package inpatient.transaction.dao;

import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public interface PatIntakeOutDtlDAOi 
{
	/** Saving Out Take Parameter
	 * @param patIntakeOutDtlVO
	 * @param userVO
	 */
	public void saveOutParameter(PatIntakeOutDtlVO patIntakeOutDtlVO,UserVO userVO);
	
	/** Getting the Details of out Take Parameter of the Patient  
	 * @param dailyPatVO
	 * @param userVO
	 * @return
	 */
	public PatIntakeOutDtlVO[] getOutParaDetail(String mode,PatientDetailVO dailyPatVO,UserVO userVO);
	
	public PatIntakeOutDtlVO[] getIntakeSummary(String patCrNo,UserVO userVO);
	
	public PatIntakeOutDtlVO[] getOuttakeSummary(String patCrNo,UserVO userVO);
	
	public PatIntakeOutDtlVO[] getViewSummaryDetail(String admNo,String fromDate,String toDate,UserVO userVO);
	
	public PatIntakeOutDtlVO[] getOutParaDetailEMR(String mode,PatientDetailVO dailyPatVO,String []departmentUnitArray,String accessType,UserVO userVO);

	public void getSnomedIdTerm(PatIntakeOutDtlVO patIntakeOutDtlVO, UserVO userVO);
}
