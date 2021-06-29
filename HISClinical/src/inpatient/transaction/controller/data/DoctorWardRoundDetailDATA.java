package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.DoctorWardRoundDtlVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;

public class DoctorWardRoundDetailDATA extends ControllerDATA
{

	/**  Getting the List of Employee Unit Wise
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public static List getRoundByEssentials(String unitCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getRoundByEssentials(unitCode,userVO);
	}
	
	public static List getAllConsultantDetails(String unitCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getAllConsultantDetails(unitCode,userVO);
	}
	
	public static void saveDoctorWardRoundDtl(DoctorWardRoundDtlVO _doctorWardRoundDtlVO,List callBookList, UserVO _userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveDoctorWardRoundDtl(_doctorWardRoundDtlVO,callBookList,_userVO);
	}
	
	public static List getDeptUnitList(UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getDeptUnitList(userVO); 
	}
	
	public static DoctorCallBookVO[] getOnCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getOnCallDetails(doctorCallBookVO,_userVO);
	}
}
