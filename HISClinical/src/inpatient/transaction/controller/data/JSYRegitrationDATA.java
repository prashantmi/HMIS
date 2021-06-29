package inpatient.transaction.controller.data;

import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InpatientDelegate;
import java.util.Map;


public class JSYRegitrationDATA {
	
	public static Map<String, Object> getPatDetailForJSYRegistration(String patCrNo,UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		return delegate.getPatDetailForJSYRegistration(patCrNo,_userVO);
	}

	public static void saveJSYDetail(JSYRegitrationVO jRegitrationVO,PatientDetailVO pDetailVO,String jsyCategoryCode,SecondaryCategoryChangeVO sChangeVO,UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		delegate.saveJSYDetail(jRegitrationVO,pDetailVO,jsyCategoryCode,sChangeVO,_userVO);
	}
	
	
	
	
}
