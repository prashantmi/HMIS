package opd.audioVideoPlayer;

import opd.bo.delegate.OpdEssentialDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.UserVO;

public class HisAudioVideoDATA extends ControllerDATA {
	
	public static AudioVideoMasterVO[] getAudioVideoEssentials(String _unitCode, UserVO _userVO){
		
		OpdEssentialDelegate opdEssentialDelegate=new OpdEssentialDelegate();
		return opdEssentialDelegate.getAudioVideoEssentials(_unitCode,_userVO);
		
	}

}
