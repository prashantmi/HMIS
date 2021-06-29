package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.departmentSpecificResultEntryFB;
import new_investigation.transactions.delegate.InvResultValidationDelegate;
import new_investigation.transactions.delegate.departmentSpecificResultEntryDelegate;
import new_investigation.vo.template.ResultEntryVO;


public class departmentSpecificResultEntryDATA
{
	 
	
	public static Map LabComboForResultEntry(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
		return masterDelegate.LabComboForResultEntry(invresultentryvo, _UserVO);
	}
	 
	public static Map setPatientResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
		return masterDelegate.setPatientResultEntryEssentials(invresultentryvo, _UserVO);
	}
	
	
	//ResultEntry Save Logic
		public static Map saveResultEntryDetails(List<ResultEntryVO> voResultEntry,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request)
		{
			departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
			return masterDelegate.saveResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request);
		}
	
		
		//fetching the loinc Code
		public static String getLoincCode(String passValue)
		{
			departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
			return masterDelegate.getLoincCode(passValue);
		}
		public static Map showCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
		{
			departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
			return masterDelegate.showCannedDetails(labCode,cannedMacroCheck ,_UserVO);
		}
		
		public static Map modifyResultEntryDetails(List<ResultEntryVO> voResultEntry,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request)
		{
			departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
			return masterDelegate.modifyResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request);
		}
		
		public static Map autoCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
		{
			departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
			return masterDelegate.autoCannedDetails(labCode,cannedMacroCheck ,_UserVO);
		}
		public static String checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO)
		{
			departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
			return masterDelegate.checkcannedCodeName(fb ,_UserVO);
		}
		
		public static Map getDeptResultEntryData(ResultEntryVO InvResultEntryVO)
		{
			departmentSpecificResultEntryDelegate masterDelegate = new departmentSpecificResultEntryDelegate();
			return masterDelegate.getDeptResultEntryData(InvResultEntryVO);
		}

		
		
} 
