package opd.transaction.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.UserVO;

public class OpdReferPatientDATA extends ControllerDATA
{
	public static Map getopeReferPatientEssentials(String _crNO, String _deptCode, UserVO _UserVO, String deskType, String episodeCode)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		return opdEssentialDelegate.getReferPatientEssentials(_crNO, _deptCode, _UserVO, deskType, episodeCode);
	}

	public static void saveOpdReferPatient(EpisodeRefDtlVO[] episodeRefDtlVO, UserVO userVO, String _deskType)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.saveOpdPatientReferDetail(episodeRefDtlVO, userVO, _deskType);
	}
}
