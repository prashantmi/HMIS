package registration.bo.helper;

import hisglobal.hisconfig.Config;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeVO;
import registration.*;

public class PatientBOHelper 
{
	public static void setNewPatRegEpisodeVO(EpisodeVO _episode)
	{
		_episode.setIsValid(Config.IS_VALID_ACTIVE);
		//_episode.setEpisodeDate("");
		_episode.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);		
		//_episode.setEpisodeTime("12:06:04");
		_episode.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		//_episode.setEntryDate("TO_CHAR(SYSDATE, 'DD/MON/YYYY')");
		_episode.setSpecifyExpiry(RegistrationConfig.SPECIFY_EXPIRY_TRUE);
	}

	public static void setDailyPatientDetails(DailyPatientVO _dailyPatient)
	{
	}
	
}
