package mortuary.transaction.controller.data;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.UserVO;

public class PhotoUploadDATA extends ControllerDATA
{
	public static MortuaryDeceasedImageDtlVO[] getExistingPhoto(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getExistingPhoto(deceasedNo,userVO); 
	}
	
	public static void saveUploadedPhoto(String deceasedNo, String srNo,MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveUploadedPhoto(deceasedNo,srNo,deceasedImageDtlVO,userVO);
	}
}
