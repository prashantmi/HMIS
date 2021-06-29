package opd.transaction.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.Apt_slotDtlVO;
import hisglobal.vo.UserVO;

public class OpdNextVisitAppointmentDATA extends ControllerDATA {
	
	public static Map getSlotDtl(Apt_slotDtlVO _slotDtlVO ,UserVO userVO)
	{			 
		return new OpdEssentialDelegate().getSlotDtl(_slotDtlVO, userVO );
	}
	/*public static Apt_appointmentDtlVO SaveData(Apt_appointmentDtlVO _appointmentDtlVO,Apt_slotDtlVO _slotDtlVO,PatientVO _patientVO,UserVO userVO)
	{			 
		return new OpdPatientDelegate().SaveNextVisitAppiontemntData(_appointmentDtlVO,_slotDtlVO,_patientVO,userVO);
	}*/
	public static Apt_slotDtlVO getNextSlotDate(Apt_slotDtlVO _slotDtlVO,UserVO uservo)
	{
		return new OpdEssentialDelegate().getNextSlotDate(_slotDtlVO,uservo); 
	}
	public static void saveOpdPatientEpisode(String patCrNo,String SerialNo,String visitNo,String episodeCode,String isConfirmed,String episodeIsOpen,String nextVisitDate,UserVO _userVO)
	{
		new  OpdPatientDelegate().saveOpdPatientEpisode(patCrNo, SerialNo, visitNo, episodeCode, isConfirmed, episodeIsOpen, nextVisitDate,_userVO);
	}
}
