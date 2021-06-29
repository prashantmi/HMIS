package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.List;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class NursingStationLoginDATA extends ControllerDATA
{
	/** Getting the List of Ward
	 * @param userVO
	 * @return
	 */
	public static List getWardListBasedOnRole(UserVO userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getWardListBasedOnRole(userVO); 
	}

	/** Getting the List of Unit
	 * @param userVO
	 * @return
	 */
	public static List getDeptUnitList(UserVO userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getDeptUnitList(userVO); 
	}
	
	/** Getting the List of Ward on the basis of Unit
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public static List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getWardOnBasisOfUnitCode(unitCode,userVO); 
	}
	
	public static List getRoomOnBasisOfWardCode(String unitCode,String wardCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate= new InPatientEssentialDelegate();
		return delegate.getRoomOnBasisOfWardCode(unitCode,wardCode,userVO); 
	}
}
