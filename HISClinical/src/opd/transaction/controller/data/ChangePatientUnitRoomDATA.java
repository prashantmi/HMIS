package opd.transaction.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import registration.bo.delegate.PatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;

public class ChangePatientUnitRoomDATA extends ControllerDATA
{
	public static Map getEpisodeRoomChangeEssential(UserVO _userVO,String _unitCode,String _roomCode,PatientDetailVO patDtlVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getEpisodeRoomChangeEssential(_userVO,_unitCode,_roomCode,patDtlVO);
	}
	
	public static void changePatientRoom(RoomChangeVO _roomChangeVO,EpisodeVO _episodeVO,DailyPatientVO _dailyPatientVO,UserVO _userVO,String _unitCode)
	{
		
		PatientDelegate  _patientDelegate= new PatientDelegate();
		_patientDelegate.changePatientRoom(_roomChangeVO,_episodeVO,_dailyPatientVO,_userVO,_unitCode);
	}
}
