package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.bo.InPatientEssentialBO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.List;
import java.util.Map;

public class IpdPatDocDeskDATA extends ControllerDATA
{
	/** Getting the List of Admitted Patient List on the basis of unitCode & wardCode
	 * @param unitCode
	 * @param wardCode
	 * @param userVO
	 * @return
	 */
	public static PatientDetailVO[] getAdmittedPatientList(String roomCode,String unitCode,String wardCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getAdmittedPatientList(roomCode,unitCode,wardCode,userVO);
	}

	public static Map<String, Object> getTodayAllPatientList_AJAX(PatientDetailVO patientDetailVO, UserVO _UserVO, int p_page,int p_limit, String p_sidx, String p_sord, String p_where, String deskType) 
	{
		InPatientEssentialBO inPatientEssentialBO = new InPatientEssentialBO();
		return inPatientEssentialBO.getTodayAllPatientList_AJAX(patientDetailVO, _UserVO, p_page, p_limit, p_sidx, p_sord, p_where, deskType);
	}

	public static List getIpdDeskEssentials(UserVO userVO) 
	{	
		InPatientEssentialBO serviceBO = new InPatientEssentialBO();
		return (serviceBO.getIpdDeskEssentials(userVO));
	}

	public static List getWardOnBasisOfUnitCode(String unitCode, UserVO userVO) 
	{
		InPatientEssentialBO inPatientEssentialBO= new InPatientEssentialBO();
		return inPatientEssentialBO.getWardOnBasisOfUnitCode(unitCode,userVO); 
	}

	public static List getRoomOnBasisOfWardCode(String unitCode,String wardCode, UserVO userVO) 
	{
		InPatientEssentialBO inPatEssentialBO= new InPatientEssentialBO();
		return inPatEssentialBO.getRoomOnBasisOfWardCode(unitCode,wardCode,userVO); 
	}
	//Added by Vasu on 15.Sept.2018
	public static List getIpdNursingDeskEssentials(UserVO userVO) 
	{	
		InPatientEssentialBO serviceBO = new InPatientEssentialBO();
		//return (serviceBO.getIpdDeskEssentials(userVO));
		return (serviceBO.getIpdNursingDeskEssentials(userVO));		
	}
	
	//Added by Vasu on 15.Sept.2018
	public static List getWardOnBasisOfUnitCodeForIPDNursing(String unitCode, UserVO userVO) 
	{
		InPatientEssentialBO inPatientEssentialBO= new InPatientEssentialBO();
		//return inPatientEssentialBO.getWardOnBasisOfUnitCode(unitCode,userVO); 
		return inPatientEssentialBO.getWardOnBasisOfUnitCodeForIPDNursing(unitCode,userVO); 
	}
}