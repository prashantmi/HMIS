package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class BirthRegistrationUploadDATA extends ControllerDATA
{
	public static ANCNeonatalDetailVO[] getListOfBirth(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getListOfBirth(userVO);
	}
	
	public static List getRelationList(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRelationList(userVO);
	}
	
	public static void saveForRegUpload(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveForBirthRegUpload(birthUploadVO,userVO);
	}
	
	public static BirthDeathUploadDtlVO getBirthDeathUploadDtl(String recordType, String crNo,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getBirthDeathUploadDtl(recordType, crNo,userVO);
	}
	
	public static void saveHandoverForRegUpload(BirthDeathUploadDtlVO birthHandoverVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveHandoverForBirthRegUpload(birthHandoverVO,userVO);
	}
	
	public static Map getMotherNChildDetail(String motherCrNo, String childCrNo,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getMotherNChildDetail(motherCrNo,childCrNo ,userVO);
	}
	
	public static PatientVO getBirthSlipDetail(String crNo,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getBirthSlipDetail(crNo ,userVO);
	}
	
	public static ANCNeonatalDetailVO[] searchForBirthRegUpload(PatientVO searchFindVO, UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.searchForBirthRegUpload(searchFindVO, userVO);
	}
	
	public static ANCNeonatalDetailVO[] searchForBirthRegUploadByMother(PatientVO searchFindVO, UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.searchForBirthRegUploadByMother(searchFindVO, userVO);
	}
	
}
