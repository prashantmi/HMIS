package mrd.transaction.controller.data;

import java.util.List;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class DeathRegistrationUploadDATA extends ControllerDATA
{
	public static PatientVO[] getListOfDeath(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getListOfDeath(userVO);
	}
	
	public static List getRelationList(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRelationList(userVO);
	}
	
	public static BirthDeathUploadDtlVO getBirthDeathUploadDtl(String recordType,String crNo,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getBirthDeathUploadDtl(recordType, crNo,userVO);
	}
	
	public static void saveForRegUpload(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveForBirthRegUpload(birthUploadVO,userVO);
	}
	
	public static void saveHandoverForRegUpload(BirthDeathUploadDtlVO birthHandoverVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveHandoverForBirthRegUpload(birthHandoverVO,userVO);
	}
	
	public static List getPatDeathUploadList(String patCrNo,String deathDate, String _firstName,String _middleName,String _lastName,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getPatDeathUploadList(patCrNo, deathDate,_firstName,_middleName,_lastName,userVO);
	}
	
	
}
