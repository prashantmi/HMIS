package opd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.ServiceAreaVO;
import hisglobal.vo.ServiceVO;
import hisglobal.vo.UserVO;

public class OpdServiceRequisitionDATA extends ControllerDATA {
	
	public static Map getServiceEssentials(UserVO _userVO,DailyPatientVO selectedPatientVO)
	{	
		return  new OpdEssentialDelegate().getServiceEssentials(_userVO,selectedPatientVO);
	}
	
	public static List getDeptWiseServiceAreaList(ServiceAreaVO _serviceAreaVO,UserVO _userVO)
	{	
		return  new OpdEssentialDelegate().getDeptWiseServiceAreaList(_serviceAreaVO,_userVO);
	}
	
	public static List getServiceAreaWiseServiceList(ServiceVO _serviceVO,UserVO _userVO)
	{	
		return  new OpdEssentialDelegate().getServiceAreaWiseServiceList(_serviceVO,_userVO);
	}
	
	/*public static Service_Req_dtlVO SaveServiceRequisition(Service_Req_dtlVO _service_Req_dtlVO,UserVO _userVO)
	{	
		return  new OpdPatientDelegate().SaveServiceRequisition(_service_Req_dtlVO,_userVO);
	}	
	public static List getServiceWiseParameters(ServiceVO _serviceVO,UserVO _userVO){
		return  new OpdEssentialDelegate().getServiceWiseParameters(_serviceVO,_userVO);
	}*/
}
