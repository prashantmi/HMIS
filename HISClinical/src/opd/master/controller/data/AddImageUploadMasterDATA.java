package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdMasterDelegate;

public class AddImageUploadMasterDATA extends ControllerDATA
{
	// Saving Image Record
	public static void saveImage(ImageMasterVO imageMasterVO,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		opdMasterDelegate.saveImage(imageMasterVO,userVO);
	}
	
	// Getting Image Records
	public static ImageMasterVO getImageForModify(String imageCode,String imageSlno,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		 return (opdMasterDelegate.getImageForModify(imageCode,imageSlno,userVO));
	}
	
	// Modifying Image Record 
	public static void saveModifyImage(ImageMasterVO imageMasterVO,UserVO userVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		opdMasterDelegate.saveModifyImage(imageMasterVO,userVO);
	}
	
	public static byte[] fetchimageFromPostgres(String imageCode)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		byte[] getImage=opdMasterDelegate.fetchimageFromPostgres(imageCode);
		return getImage;
	}
}
