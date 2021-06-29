
package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

public class InpatientBulletinDetailDATA extends ControllerDATA
{
	/** Getting the List of Admitted Patient List on the basis of unitCode & wardCode
	 * @param unitCode
	 * @param wardCode
	 * @param userVO
	 * @return
	 */
	
	
	public static Map getBulletinDetails(PatientDetailVO patDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getBulletinDetails(patDtlVO,userVO) ;  
	}
	
	public static void saveAndUpdatePatientBulletinDetails(PatientBulletinDetailVO _oldPatientBulletinVO,PatientBulletinDetailVO _newPatientBulletinVO,UserVO userVO,String revoke)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveAndUpdatePatientBulletinDetails(_oldPatientBulletinVO,_newPatientBulletinVO,userVO,revoke) ;  
	}
	

	public static List getRemarks(String processId,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getProgressNotes(processId,userVO);
	}

	public static PatientBulletinDetailVO[] getAllAdmittedPatientListBulletin(UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getAllAdmittedPatientListBulletin(userVO) ;  
	}

}
