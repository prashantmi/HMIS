package opd.master.controller.data;

import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.UserVO;

public class AudioVideoMasterDATA extends ControllerDATA
{
	public static void saveAudioVideoFile(AudioVideoMasterVO audioVideoVO,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		opdMasterDelegate.saveAudioVideoFile(audioVideoVO,userVO);
	}
	
	public static AudioVideoMasterVO getAudioVideoForModify(String fileCode,String slNo,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		return(opdMasterDelegate.getAudioVideoForModify(fileCode,slNo,userVO));
	}
	
	public static void saveModifyAudioVideo(AudioVideoMasterVO audioVideoVO,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		opdMasterDelegate.saveModifyAudioVideo(audioVideoVO,userVO);
	}
}
