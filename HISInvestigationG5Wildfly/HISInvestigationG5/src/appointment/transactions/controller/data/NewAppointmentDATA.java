package appointment.transactions.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import vo.appointment.AppointmentParameterVO;
import vo.appointment.NewAppointmentVO;
import appointment.bo.AppointmentEssentialBO;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;




public class NewAppointmentDATA extends ControllerDATA {

	public static List getGenderList() {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		List lst=essentialBO.getGenderList(); 
		return lst;
	}
	
	public static List getDepartmentList(UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		List lst=essentialBO.getDepartmentList(userVO); 
		return lst;
	}

	public static String getAppointmentTypeList(String actualParameterReferenceId, UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		String strResult =essentialBO.getAppointmentTypeList( actualParameterReferenceId, userVO); 
		return strResult;
	}

	public static NewAppointmentVO SaveNewAppointment(	NewAppointmentVO objNewAppointmentVO, UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		objNewAppointmentVO =essentialBO.SaveNewAppointment( objNewAppointmentVO, userVO); 
		return objNewAppointmentVO;
	}

	public static NewAppointmentVO ConfirmAppointment(NewAppointmentVO objNewAppointmentVO, UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		objNewAppointmentVO =essentialBO.ConfirmAppointment( objNewAppointmentVO, userVO); 
		return objNewAppointmentVO;
	}
	
	public static WebRowSet getShiftDetailsForApt(Map<String,String> mapAptDetails, UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		WebRowSet ws =essentialBO.getShiftDetailsForApt(userVO, mapAptDetails); 
		return ws;
	}
}