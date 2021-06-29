package appointment.transactions.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import vo.appointment.AppointmentParameterVO;
import vo.appointment.AptDskCalendarVO;
import vo.appointment.NewAppointmentVO;
import appointment.bo.AppointmentEssentialBO;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;




public class AptDskCalendarDATA extends ControllerDATA {

	public static String getDayWiseAptCountDetail(AptDskCalendarVO aptDskCalendarVO, UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		String strResult =essentialBO.getDayWiseAptCountDetail( aptDskCalendarVO, userVO); 
		return strResult;
	}
	
	public static String getDayWiseAptDetail(AptDskCalendarVO aptDskCalendarVO, UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		String strResult =essentialBO.getDayWiseAptDetail( aptDskCalendarVO, userVO); 
		return strResult;
	}
}