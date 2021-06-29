package mrd.transaction.controller.data;

import java.util.List;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatFamilyDocDtlVO;
import hisglobal.vo.UserVO;

public class PatientFamilyDoctorDtlDATA extends ControllerDATA
{
	public static PatFamilyDocDtlVO[] getExistingFamilyDoctorRecord(String crNo,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getExistingFamilyDoctorRecord(crNo,userVO);
	}
	
	public static List getPhysicianType(UserVO userVO)
	{
		MrdEssentialDelegate delegate=new MrdEssentialDelegate();
		return delegate.getPhysicianType(userVO);
	}
	
	public static void savePatientFamilyDoctorDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.savePatientFamilyDoctorDetail(patFamilyDocVO,userVO);
	}
	
	public static PatFamilyDocDtlVO getPatientFamilyDocDetail(String chk,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getPatientFamilyDocDetail(chk,userVO);
	}
	
	public static void modifyPatientFamilyDocDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.modifyPatientFamilyDocDetail(patFamilyDocVO,userVO);
	}
	
	public static void revokePatientFamilyDocDetail(String crNo,String hCode,String slNo,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.revokePatientFamilyDocDetail(crNo,hCode,slNo,userVO);
	}
}
