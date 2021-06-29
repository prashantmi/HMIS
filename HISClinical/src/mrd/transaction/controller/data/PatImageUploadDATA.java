package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

import mrd.transaction.delegate.MrdDelegate;

public class PatImageUploadDATA extends ControllerDATA
{
	
	/** Getting The List of Certificate For Movement By Cr No 
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static List getPatientImageDtlByCrNo(String crNo,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getPatientImageDtlByCrNo(crNo,userVO);
	}

	public static void deletePatientImage(PatientImageDtlVO[] patimageVO,
			UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.deletePatientImage(patimageVO,userVO);
		
	}

	public static void savePatientImage(PatientImageDtlVO[] patimageVO,
			UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.savePatientImage(patimageVO,userVO);
		
	}

	public static void modifyPatientImage(PatientImageDtlVO[] patimageVO,
			UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.modifyPatientImage(patimageVO,userVO);
		
	}
	
	
	public static byte[] latestFetchImagePostgres(String fileNo)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.latestFetchImagePostgres(fileNo);
	}

	
	
}
