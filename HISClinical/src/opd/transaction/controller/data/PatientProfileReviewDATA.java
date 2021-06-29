package opd.transaction.controller.data;

import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileReviewDtlVO;
import hisglobal.vo.UserVO;

public class PatientProfileReviewDATA extends ControllerDATA
{

	public static PatientProfileDetailVO[] getPreviousProfileDetails(String _crNo,UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.getPreviousProfileDetails(_crNo, _userVO);
	}
	
	public static void savePatientProfileReviewDetail(ProfileReviewDtlVO profileReviewDtlVO,UserVO userVO)
	{
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		opdPatientDelegate.savePatientProfileReviewDetail(profileReviewDtlVO,userVO);
	}
	
	public static ProfileReviewDtlVO[] fetchProfileReviewDetails(String _crNo,String profileId,UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.fetchProfileReviewDetails(_crNo,profileId, _userVO);
	}
}
