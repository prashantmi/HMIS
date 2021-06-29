package inpatient.transaction.controller.data;

import inpatient.transaction.bo.InpatientBO;
import inpatient.transaction.bo.InpatientBOi;
import inpatient.transaction.delegate.InpatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class InpatientDetailDATA extends ControllerDATA
{
	/** Getting The Patient Detail By CR No For Desk
	 * @param deskType
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public static PatientDetailVO getInpatientDetailByCrNoNAdmNo(String deskType,PatientDetailVO patDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getInpatientDetailByCrNoNAdmNo(deskType,patDtlVO,userVO) ;  
	}
	
	
	public static PatientDetailVO getMRDPatientDtlByCrNo(String deskType,PatientDetailVO patDtlVO,UserVO userVO)
	{
		InpatientBO serviceBO = new InpatientBO();
		return serviceBO.getMRDPatientDtlByCrNo(deskType,patDtlVO,userVO) ;  
	}
	
	
	/** Getting The MLC No of The Patient For Desk
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public static MlcVO getMlcNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getMlcNo(patDtlVO,userVO) ;  
	}
}
