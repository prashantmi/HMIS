package appointment.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.PatientVO;
import appointment.bo.AppointmentEssentialBO;

public class ShiftAndSlotCancellationDATA extends ControllerDATA {
	
	public static RescheduleCancelAppointmentVO cancelShift(RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO, UserVO userVO) {
		System.out.println("saveRescheduleCancelAppointmentDATA");
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		objRescheduleCancelAppointmentVO =essentialBO.cancelShift(objRescheduleCancelAppointmentVO, userVO); 
		return objRescheduleCancelAppointmentVO;
	}
	
	public static String cancelSlots(List<RescheduleCancelAppointmentVO> _lstobjRescheduleCancelAppointmentVO, UserVO userVO) {
		System.out.println("saveRescheduleCancelAppointmentDATA");
		AppointmentEssentialBO  essentialBO=new AppointmentEssentialBO();
		String strmsg =essentialBO.cancelSlots(_lstobjRescheduleCancelAppointmentVO, userVO); 
		return strmsg;
	}
	
	
}
