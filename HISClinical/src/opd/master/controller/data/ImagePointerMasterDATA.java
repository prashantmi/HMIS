package opd.master.controller.data;

import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;

import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.UserVO;


public class ImagePointerMasterDATA extends ControllerDATA
{
	
	public static void saveDetail(ImagePointerMasterVO imagePointerMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveImagePointerDetail(imagePointerMasterVO,_userVO);
		
	}
	
	public static ImagePointerMasterVO getModifyDetail(ImagePointerMasterVO imagePointerMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		return opdDelegate.getModifyDetail(imagePointerMasterVO,_userVO);
		
	}
	
	public static void saveModifyDetail(ImagePointerMasterVO imagePointerMasterVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveModifyDetail(imagePointerMasterVO,_userVO);
		
	}
	
	
}
