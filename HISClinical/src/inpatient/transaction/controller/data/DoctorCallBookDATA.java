package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.ArrayList;
import java.util.List;

import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.UserVO;


public class DoctorCallBookDATA 

{
	public static List getConsultantDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate = new InPatientEssentialDelegate();
		List consultantDetails = new ArrayList();
		consultantDetails = essentialDelegate.getConsultantDetails(unitCode,_UserVO);
		return consultantDetails;
	}
	
	public static List getConsultantPhone(String empNo,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate = new InPatientEssentialDelegate();
		List phoneDetails = new ArrayList();
		phoneDetails = essentialDelegate.getConsultantPhone(empNo,_UserVO);
		return phoneDetails;
	}
	
	public static List getPeonDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate = new InPatientEssentialDelegate();
		List peonDetails = new ArrayList();
		peonDetails = essentialDelegate.getPeonDetails(unitCode,_UserVO);
		return peonDetails;
	}
	
	public static List getAllConsultantDetails(String unitCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate = new InPatientEssentialDelegate();
		List consultantDetails = new ArrayList();
		consultantDetails = essentialDelegate.getAllConsultantDetails(unitCode,_UserVO);
		return consultantDetails;
	}

	public static boolean saveDoctorCallDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{
		InpatientDelegate inpatientDelegate = new InpatientDelegate();
		boolean hasFlag=inpatientDelegate.saveDoctorCallDetails(_callBookVO, _UserVO);
		return hasFlag;
	}
	
	public static boolean ModifyDoctorCallDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{
		InpatientDelegate inpatientDelegate = new InpatientDelegate();
		boolean hasFlag=inpatientDelegate.ModifyDoctorCallDetails(_callBookVO, _UserVO);
		return hasFlag;
	}
	
	public static List getDeptUnitList(UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getDeptUnitList(userVO); 
	}
	
	public static DoctorCallBookVO[] getCallBookDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getCallBookDetails(doctorCallBookVO,_userVO);
	}
	

	public static DoctorCallBookVO[] getAllCalls(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getAllCalls(doctorCallBookVO,_userVO);
	}
	
	public static DoctorCallBookVO getCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getCallDetails(doctorCallBookVO,_userVO);
	}

	public static DoctorCallBookVO[] getOnCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){

		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getOnCallDetails(doctorCallBookVO,_userVO);
	}
	public static List getCallRemarksNNotes(String processId,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getCallRemarksNNotes(processId,userVO);
	}

	//addedBy:NehaRajgariya Date:1sept2016
	public static String getSmsFlagDetails(DoctorCallBookVO _callBookVO, UserVO _userVO) {
		InpatientDelegate inpatientDelegate = new InpatientDelegate();
		String smsFlag=inpatientDelegate.getSmsFlagDetails(_callBookVO, _userVO);
		return smsFlag;
		
	}

	public static List getUnitPhone(String unitCode, UserVO userVO) {
		InPatientEssentialDelegate essentialDelegate = new InPatientEssentialDelegate();
		List phoneDetails = new ArrayList();
		phoneDetails = essentialDelegate.getUnitPhone(unitCode,userVO);
		return phoneDetails;

		
	}
}
