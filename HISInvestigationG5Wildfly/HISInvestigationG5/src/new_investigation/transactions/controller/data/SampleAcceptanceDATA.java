package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.SampleAcceptanceDelegate;
import new_investigation.vo.SampleAcceptanceVO;


public class SampleAcceptanceDATA
{
	 
	
	 
	
	public static Map sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceDelegate masterDelegate = new SampleAcceptanceDelegate();
		return masterDelegate.sampleAcceptanceLabCombo(sampleAcceptanceVO, _UserVO);
	}
	
	public static Map getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceDelegate masterDelegate = new SampleAcceptanceDelegate();
		return masterDelegate.getSampleAcceptanceDetail(sampleAcceptanceVO, _UserVO);
	}
	
	public static Map getSampleAcceptanceDetailOnLoad(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceDelegate masterDelegate = new SampleAcceptanceDelegate();
		return masterDelegate.getSampleAcceptanceDetailOnLoad(sampleAcceptanceVO, _UserVO);
	}
	
	public static Map SampleAcceptanceRejectionCombo(SampleAcceptanceVO sampleAcceptanceVO,List<String> reqList,UserVO _UserVO)
	{
		SampleAcceptanceDelegate masterDelegate = new SampleAcceptanceDelegate();
		return masterDelegate.SampleAcceptanceRejectionCombo(sampleAcceptanceVO, reqList,_UserVO);
	}
	
	
	public static boolean checkDailyLabNoDuplicacy(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceDelegate masterDelegate = new SampleAcceptanceDelegate();
		return masterDelegate.checkDailyLabNoDuplicacy(sampleAcceptanceVO,  _UserVO);
	}
	
	
	 
	//Requisition Save Logic
	public static Map saveSampleAcceptanceDetails(List<SampleAcceptanceVO> voSampleAcc, UserVO _userVO,HttpServletRequest _request)
	{
		SampleAcceptanceDelegate daoDelegate=new SampleAcceptanceDelegate();
		return daoDelegate.saveSampleAccDetails(voSampleAcc,  _userVO,  _request);
	}
	 
	public static Map rejectSampleAcceptanceDetails(List<SampleAcceptanceVO> voSampleAcc, UserVO _userVO,HttpServletRequest _request)
	{
		SampleAcceptanceDelegate daoDelegate=new SampleAcceptanceDelegate();
		return daoDelegate.rejectSampleAccDetails(voSampleAcc,  _userVO,  _request);
	}
	 
	
	public static Map getLabTestMachine(String labCode, UserVO _UserVO)
	{
		SampleAcceptanceDelegate masterDelegate = new SampleAcceptanceDelegate();
		return masterDelegate.getLabTestMachine(labCode, _UserVO);
	}
	
	
	
	public static String checkAutoGenFormate(SampleAcceptanceVO voSample,UserVO userVO)
	{
		SampleAcceptanceDelegate   daoDelegate=new SampleAcceptanceDelegate();
		return daoDelegate.checkAutoGenFormate(voSample, userVO);
	}	
	 
	
	public static Map fetchMachime( UserVO _UserVO)
	{
		SampleAcceptanceDelegate daoDelegate = new SampleAcceptanceDelegate();
		return daoDelegate.fetchMachime( _UserVO);
	}

	//added by krishnan nema on 26/10/2018
	public static Map modifySampleAcceptanceDetails(List<SampleAcceptanceVO> voSampleAcceptance, UserVO _userVO, HttpServletRequest _request) {
		SampleAcceptanceDelegate daoDelegate=new SampleAcceptanceDelegate();
		return daoDelegate.modifySampleAccDetails(voSampleAcceptance,  _userVO,  _request);
	}

	 
}
