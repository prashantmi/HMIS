package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.SampleAcceptanceBarCodeDelegate;
import new_investigation.vo.SampleAcceptanceVO;


public class SampleAcceptanceBarCodeDATA
{
	 
	
	 
	
	public static Map sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceBarCodeDelegate masterDelegate = new SampleAcceptanceBarCodeDelegate();
		return masterDelegate.sampleAcceptanceLabCombo(sampleAcceptanceVO, _UserVO);
	}
	
	public static Map getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceBarCodeDelegate masterDelegate = new SampleAcceptanceBarCodeDelegate();
		return masterDelegate.getSampleAcceptanceDetail(sampleAcceptanceVO, _UserVO);
	}
	
	public static Map getSampleAcceptanceDetail1(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceBarCodeDelegate masterDelegate = new SampleAcceptanceBarCodeDelegate();
		return masterDelegate.getSampleAcceptanceDetail1(sampleAcceptanceVO, _UserVO);
	}
	
	public static Map getSampleAcceptanceDetailOnLoad(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceBarCodeDelegate masterDelegate = new SampleAcceptanceBarCodeDelegate();
		return masterDelegate.getSampleAcceptanceDetailOnLoad(sampleAcceptanceVO, _UserVO);
	}
	
	public static Map SampleAcceptanceRejectionCombo(SampleAcceptanceVO sampleAcceptanceVO,List<String> reqList,UserVO _UserVO)
	{
		SampleAcceptanceBarCodeDelegate masterDelegate = new SampleAcceptanceBarCodeDelegate();
		return masterDelegate.SampleAcceptanceRejectionCombo(sampleAcceptanceVO, reqList,_UserVO);
	}
	
	
	public static boolean checkDailyLabNoDuplicacy(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		SampleAcceptanceBarCodeDelegate masterDelegate = new SampleAcceptanceBarCodeDelegate();
		return masterDelegate.checkDailyLabNoDuplicacy(sampleAcceptanceVO,  _UserVO);
	}
	
	
	 
	//Requisition Save Logic
	public static Map saveSampleAcceptanceDetails(List<SampleAcceptanceVO> voSampleAcc, UserVO _userVO,HttpServletRequest _request)
	{
		SampleAcceptanceBarCodeDelegate daoDelegate=new SampleAcceptanceBarCodeDelegate();
		return daoDelegate.saveSampleAccDetails(voSampleAcc,  _userVO,  _request);
	}
	 
	public static Map LabCombos(SampleAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		SampleAcceptanceBarCodeDelegate masterDelegate = new SampleAcceptanceBarCodeDelegate();
		return masterDelegate.LabCombos(onlinePatientvo, _UserVO);
	}  
	
	 
}
