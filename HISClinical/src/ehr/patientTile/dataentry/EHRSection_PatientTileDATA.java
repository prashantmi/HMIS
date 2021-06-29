package ehr.patientTile.dataentry;


import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class EHRSection_PatientTileDATA extends ControllerDATA
{
	/** Getting The Patient Detail By CR No For Desk
	 * @param deskType
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public static PatientDetailVO getInpatientDetailByCrNoNAdmNo(String deskType,PatientDetailVO patDtlVO,UserVO userVO)
	{
		EHRSection_PatientTileDelegate delegate=new EHRSection_PatientTileDelegate();
		return delegate.getInpatientDetailByCrNoNAdmNo(deskType,patDtlVO,userVO) ;  
	}
	
	/** Getting The MLC No of The Patient For Desk
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public static MlcVO getMlcNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		EHRSection_PatientTileDelegate delegate=new EHRSection_PatientTileDelegate();
		return delegate.getMlcNo(patDtlVO,userVO) ;  
	}
	
	
	public static PatientDetailVO getInpatientDiscByCrNoNAdmNo(String deskType,PatientDetailVO patDtlVO,UserVO userVO)
	{
		EHRSection_PatientTileDelegate delegate=new EHRSection_PatientTileDelegate();
		return delegate.getInpatientDiscDetailByCrNoNAdmNo(deskType,patDtlVO,userVO) ;  
	}
}
