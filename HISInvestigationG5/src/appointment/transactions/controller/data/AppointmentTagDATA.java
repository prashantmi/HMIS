package appointment.transactions.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import vo.appointment.AppointmentParameterVO;
import vo.appointment.AppointmentSlotDtlVO;
import vo.appointment.NewAppointmentVO;
import appointment.bo.AppointmentEssentialBO;




public class AppointmentTagDATA extends ControllerDATA {

	public static Map getAppointmentEssentials_AJAX(UserVO userVO, String tagView,String aptId) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		Map mp=essentialBO.getAppointmentParameterEssentials_AJAX(userVO,tagView, aptId);
		return mp;
		
	}

	public static List getAppointmentIdWiseParameterDetail(UserVO userVO,String appointmentForId ,String tagView) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		List lst=essentialBO.getAppointmentIdWiseParameterDetail(userVO,appointmentForId ,tagView); 
		return lst;
	}

	public static Map  getAppointmentParameterValueDtl(UserVO userVO,AppointmentParameterVO objAppointmentParameterVO,String tagView, String parentParameterActualValue){
		
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		Map mp=essentialBO.getAppointmentParameterValueDtl(userVO,objAppointmentParameterVO ,tagView,parentParameterActualValue); 
		return mp;
	}

	public static String getActualParaRefernceId(UserVO userVO,	String appointmentForId, String allActualParameterId) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		String str=essentialBO.getActualParaRefernceId(userVO,appointmentForId ,allActualParameterId); 
		return str;		
	}

	public static Map<String, NewAppointmentVO> getAppointmentList(UserVO userVO, NewAppointmentVO objNewAppointmentVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		Map mp=essentialBO.getAppointmentList(userVO,objNewAppointmentVO); 
		return mp;
	}
	public static WebRowSet  makeSlotsData(UserVO userVO,	AppointmentParameterVO appParaVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		WebRowSet ws=essentialBO.makeSlotsData(userVO,appParaVO); 
		return ws;		
	}
	public static String  checkSlotAvailibilty(UserVO userVO,	AppointmentSlotDtlVO appParaVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		String st=essentialBO.checkSlotAvailibilty(userVO,appParaVO); 
		return st;		
	}
	
}