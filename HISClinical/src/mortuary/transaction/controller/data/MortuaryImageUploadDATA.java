package mortuary.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.UserVO;
import java.util.List;
import mortuary.transaction.delegate.MortuaryDelegate;


public class MortuaryImageUploadDATA extends ControllerDATA
{
	
	/** Getting The List of Certificate For Movement By Cr No 
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static List getPatientImageDtlByDeceasedNo(String deceasedNo,UserVO userVO)
	{
		MortuaryDelegate  mortuaryDelegate= new MortuaryDelegate();
		return mortuaryDelegate.getPatientImageDtlByDeceasedNo(deceasedNo,userVO);
	}

	public static void deleteDeceasedImage(MortuaryDeceasedImageDtlVO[] deceasedImageVO,
			UserVO userVO) {
		MortuaryDelegate  mortuaryDelegate= new MortuaryDelegate();
		mortuaryDelegate.deleteDeceasedImage(deceasedImageVO,userVO);
		
	}

	public static void saveDeceasedImage(MortuaryDeceasedImageDtlVO[] deceasedImageVO,
			MortuaryDeceasedImageDtlVO[] deceasedImageUpdateVO, UserVO userVO) {
		MortuaryDelegate  mortuaryDelegate= new MortuaryDelegate();
		mortuaryDelegate.saveDeceasedImage(deceasedImageVO,deceasedImageUpdateVO,userVO);
		
	}
	
	
}
