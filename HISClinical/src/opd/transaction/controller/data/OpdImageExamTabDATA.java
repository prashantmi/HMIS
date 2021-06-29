package opd.transaction.controller.data;

/**
 * @author  CDAC
 */

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import registration.bo.delegate.PatientDelegate;

public class OpdImageExamTabDATA extends ControllerDATA 
{
	// Getting Image Examination Essentials
	public static Map getEssentials(String _unitCode, OpdPatientImageDtlVO _patImgVO, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate=new OpdEssentialDelegate();
		return opdEssentialDelegate.getImageExaminationEssentials(_unitCode, _patImgVO, _userVO);
	}

	// Saving OPD Patient Image
	/*public static void saveOpdPatientImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.savePatientExaminationImage(_vo, _image, _userVO);
	}*/
	
	// Added by VASU on 21-AUG-2017
	public static void saveOpdPatientImage(OpdPatientImageDtlVO patimageVO,
			UserVO userVO) {
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.savePatientExaminationImage(patimageVO,userVO);
		
	}
	
	//End VASU

	// Modifying OPD Patient Image
	public static void modifyOpdPatientImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.modifyPatientExaminationImage(_vo, _image, _userVO);
	}

	// Getting Image Log Detail
	public static OpdPatientImageDtlVO getImageLogDetail(String _imageFileName, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate=new OpdEssentialDelegate();
		return opdEssentialDelegate.getImageLogDetail(_imageFileName, _userVO);
	}

	// * Getting Images List of Current Unit --Not Used
	public static List getOPDImagesListOfUnit(String _unitCode,UserVO _UserVO)
	{
		OpdEssentialDelegate  opdEssentialDelegate=new OpdEssentialDelegate();
		return opdEssentialDelegate.getOPDImagesListOfUnit(_unitCode,_UserVO);
	}

	// * Getting Images List of Patient in given Episode Visit --Not Used
	public static List<OpdPatientImageDtlVO> getOPDPatOldImagesList(OpdPatientImageDtlVO _PatImgVo,UserVO _UserVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		return opdMasterDelegate.getOPDPatOldImagesList(_PatImgVo,_UserVO);
	}

	// * Getting Editor Essentials e.g. Color-Description List --Not Used
	public static String getUnitsImageColorDesc(String _unitCode,UserVO _UserVO)
	{
		OpdEssentialDelegate  opdEssentialDelegate=new OpdEssentialDelegate();
		return opdEssentialDelegate.getUnitsImageColorDesc(_unitCode,_UserVO);
	}

	// * Creating Entry For Image --Not Used
	public static OpdPatientImageDtlVO createEntryForImage(OpdPatientImageDtlVO _vo,UserVO _UserVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		return opdMasterDelegate.createEntryForImage(_vo,_UserVO);
	}

	// * Removing Entry For Image -- Not Used
	public static void removeEntryForImage(OpdPatientImageDtlVO _vo,UserVO _UserVO)
	{
		OpdMasterDelegate opdMasterDelegate=new OpdMasterDelegate();
		opdMasterDelegate.removeEntryForImage(_vo,_UserVO);
	}
	
	public static byte[] fetchImageFromPostgres(String imgCode) {
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		byte[] getdoc = opdPatientDelegate.fetchImageFromPostgres(imgCode);
		return getdoc;
	}
	
	public static byte[] viewImageFromPostgres(OpdPatientImageDtlVO vo) {
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		byte[] getdoc = opdPatientDelegate.viewImageFromPostgres(vo);
		return getdoc;
	}

}
