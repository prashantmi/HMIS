package appointment.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

//import registration.bo.PatientBO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.PatientVO;
import appointment.bo.AppointmentEssentialBO;

public class RescheduleCancelAppointmentDATA extends ControllerDATA {
	
	public static List getGenderList() {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		List lst=essentialBO.getGenderList(); 
		return lst;
	}
	public static String getAppointmentTypeList(String actualParameterReferenceId, UserVO userVO) {
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		String strResult =essentialBO.getAppointmentTypeList( actualParameterReferenceId, userVO); 
		return strResult;
	}

	public static RescheduleCancelAppointmentVO saveRescheduleCancelAppointment(	RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO, UserVO userVO) {
		System.out.println("saveRescheduleCancelAppointmentDATA");
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		objRescheduleCancelAppointmentVO =essentialBO.saveRescheduleCancelAppointment( objRescheduleCancelAppointmentVO, userVO); 
		return objRescheduleCancelAppointmentVO;
	}

	public static Map getPreviousAppointmentList( RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO,UserVO userVO) {
		System.out.println("RescheduleCancelDATA::getPreviousAppointmentList");
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		return essentialBO.getPreviousAppointmentList(objRescheduleCancelAppointmentVO,userVO);
	}
	public static RescheduleCancelAppointmentVO cancelAppointment(	RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO, UserVO userVO) {
		System.out.println("CancelAppointment::DATA");
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		objRescheduleCancelAppointmentVO =essentialBO.cancelAppointment( objRescheduleCancelAppointmentVO, userVO); 
		return objRescheduleCancelAppointmentVO;
	}
     
	public static Map searchAppointmentNoWise(RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO_p, UserVO objUserVO_p){
		System.out.println("RescheduleCancelData::getPatientDtlByAppointmntNo");
		AppointmentEssentialBO  objAppointmentEssentialBO= new AppointmentEssentialBO();
		return objAppointmentEssentialBO.searchAppointmentNoWise(objRescheduleCancelAppointmentVO_p,objUserVO_p);
	}
	
}
