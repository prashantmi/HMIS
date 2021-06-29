package opd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import opd.bo.OpdEssentialBO;
import opd.bo.OpdPatientBO;

public class ICDEntryFormDATA extends ControllerDATA
{
	public static Map<String,Object> getICDEntryFormEssentials(UserVO voUser_p)
	{
		OpdEssentialBO boOPDEss = new OpdEssentialBO();
		return boOPDEss.getICDEntryFormEssentials(voUser_p);
	}

	public static List<PatientDetailVO> getOPDPatientListForICDEntry(String _tabMode, PatientDetailVO voPatDtl_p, String strFromDat_p, String strToDate_p, UserVO voUser_p)
	{
		OpdEssentialBO boOPDEss = new OpdEssentialBO();
		return boOPDEss.getOPDPatientListForICDEntry(_tabMode, voPatDtl_p, strFromDat_p, strToDate_p, voUser_p);
	}

	public static List<Entry> getICDCodesListForICDEntry(UserVO voUser_p)
	{
		OpdEssentialBO boOPDEss = new OpdEssentialBO();
		return boOPDEss.getICDCodesListForICDEntry(voUser_p);
	}

	public static List<Entry> getUnitRoomListForICDEntry(String strUnitCode_p, UserVO voUser_p)
	{
		OpdEssentialBO boOPDEss = new OpdEssentialBO();
		return boOPDEss.getUnitRoomListForICDEntry(strUnitCode_p, voUser_p);
	}

	public static void saveOpdPatientsICDData(Map<String,List<EpisodeDiagnosisVO>> mapPatDiag_p, Set<String> setSeenPats_p, UserVO voUser_p)
	{
		OpdPatientBO boOPDPat = new OpdPatientBO();
		boOPDPat.saveOPDPatsDiagnosisByICDEntryFormy(mapPatDiag_p, setSeenPats_p, voUser_p);
	}
}
