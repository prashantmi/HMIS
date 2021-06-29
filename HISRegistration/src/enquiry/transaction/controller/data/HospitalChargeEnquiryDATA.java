package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;

public class HospitalChargeEnquiryDATA extends ControllerDATA {
	
	public static Map getAllHospitalTarrifList(String groupId, UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getAllHospitalTarrifList(groupId,_userVO);
	}

	public static Map getChargeDetailByTariff(String tariffId,UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getChargeDetailByTariff(tariffId,userVO);
	}
		
}
