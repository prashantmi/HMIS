package opd.master.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.UnitAudioVideoMasterVO;
import hisglobal.vo.UserVO;

public class UnitAudioVideoMasterDATA extends ControllerDATA
{
	public static List getAllUnitNotInTable(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getAllUnitNotInTable(userVO);
	}
	public static List getAllDept(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getAllDept(userVO);
	}
	public static List getAllUnit(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getAllUnit(userVO);
	}
	
	public static List getAllAudioVideoFile(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getAllAudioVideoFile(userVO);
	}
	
	public static List getAllAudioVideoFileHeader(UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getAllAudioVideoFileHeader(userVO);
	}
	
	public static void saveUnitAudioVideo(UnitAudioVideoMasterVO unitAVMasterVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveUnitAudioVideo(unitAVMasterVO,userVO);
	}
	
	public static AudioVideoMasterVO[] getUnitAudioVideoForModify(String unitCode,UserVO userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getAudioVideoEssentials(unitCode,userVO);
	}
	
	public static void deleteUnitAudioVideo(String unitCode,UserVO userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteUnitAudioVideo(unitCode,userVO);
	}
}
