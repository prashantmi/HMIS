package opd.transaction.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import registration.bo.delegate.EssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientCategoryVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class CsultyPatientDeskDATA extends ControllerDATA
{
	// Getting Casuality Patients List
	public static PatientDetailVO[] getCsultyPatientList(String _unitCode, String _roomCode, UserVO _userVO)
	{
		OpdEssentialDelegate opdessentialDelegate = new OpdEssentialDelegate();
		return opdessentialDelegate.getCsultyPatientList(_unitCode, _roomCode, _userVO);
	}

	// Getting Patient Primary Category List
	public static List<PatientCategoryVO> getPrimaryCatDetailVOs(UserVO _userVO)
	{
		EssentialDelegate essentialDelegate = new EssentialDelegate();
		return essentialDelegate.getPrimaryCatDetailVOs(_userVO);
	}

	// Saving Patient Episode
	public static void saveOpdPatientEpisode(String patCrNo, String SerialNo, String visitNo, String episodeCode, String isConfirmed, String episodeIsOpen, String nextVisitDate, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.saveOpdPatientEpisode(patCrNo, SerialNo, visitNo, episodeCode, isConfirmed, episodeIsOpen, nextVisitDate, _userVO);
	}
}
