package hisglobal.presentation;

import hisglobal.business.GlobalEssentialDelegate;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.util.Date;
import java.util.List;


public class ControllerDATA
{
	/**
	 * 
	 * sets sys date.
	 * called during NEW mode.
	 * 
	 * @return sysdate
	 */
	public static String[] getSysDate(UserVO _userVO)
	{
		GlobalEssentialDelegate delegate = new GlobalEssentialDelegate();
		String[] dateAndCrNoFormat = delegate.getSysDate(_userVO);
		return dateAndCrNoFormat;
	}

	public static Date getSysDateAsDate()
	{
		return new GlobalEssentialDelegate().getSysDate(new Date());
	}

	public static String isRegistrationAllowed(String _categoryCode, UserVO _userVO)
	{
		return new GlobalEssentialDelegate().isRegistrationAllowed(_categoryCode, _userVO);
	}

	public static List getSystemDateAndFormat(UserVO _userVO)
	{
		GlobalEssentialDelegate delegate = new GlobalEssentialDelegate();
		List dateAndCrNoFormat = delegate.getSystemDateAndFormat(_userVO);
		return dateAndCrNoFormat;
	}

	public static HospitalMstVO getHospitalDetail(String hospitalCode) {
		
		 return new GlobalEssentialDelegate().getHospitalDetail(hospitalCode);
	}
	

	
	public static List getHospitalCombo(){
		GlobalEssentialDelegate  essentialDelegate=new GlobalEssentialDelegate();
	    return essentialDelegate.getHospitalCombo();
	    
	}
	
	
}
