package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.delegate.InvResultEntryDelegate;
import new_investigation.transactions.delegate.InvResultEntryRespDelegate;
import new_investigation.transactions.delegate.InvResultValidationRespDelegate;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.InvResultValidationRespVO;
import new_investigation.vo.template.ResultEntryVO;


public class InvResultEntryRespDATA
{
	 
	public static Map AjaxGetEntryReqnList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		InvResultEntryRespDelegate masterDelegate = new InvResultEntryRespDelegate();
		return masterDelegate.AjaxGetEntryReqnList(invResultValidationRespVO, _UserVO);
	}
	
	
	public static Map LabComboForResultEntry(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
		return masterDelegate.LabComboForResultEntry(invresultentryvo, _UserVO);
	}
	 
	public static Map setPatientResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
		return masterDelegate.setPatientResultEntryEssentials(invresultentryvo, _UserVO);
	}
	
	
	//ResultEntry Save Logic
		public static Map saveResultEntryDetails(List<ResultEntryVO> voResultEntry,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
		{
			InvResultEntryRespDelegate masterDelegate = new InvResultEntryRespDelegate();
			return masterDelegate.saveResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request,fb) ;
		}
	
		
		//fetching the loinc Code
		public static String getLoincCode(String passValue)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.getLoincCode(passValue);
		}
		public static Map showCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.showCannedDetails(labCode,cannedMacroCheck ,_UserVO);
		}
		
		public static Map modifyResultEntryDetails(List<ResultEntryVO> voResultEntry,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
		{
			InvResultEntryRespDelegate masterDelegate = new InvResultEntryRespDelegate();
			return masterDelegate.modifyResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request,fb);
		}
		
		public static Map autoCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.autoCannedDetails(labCode,cannedMacroCheck ,_UserVO);
		}
		public static String checkcannedCodeName(InvResultEntryFB fb, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.checkcannedCodeName(fb ,_UserVO);
		}
		
		
		public static String checkBilling(InvResultEntryFB fb, UserVO _UserVO)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.checkBilling(fb ,_UserVO);
		}
		

		public static Map saveAddendumDetails(InvResultEntryVO newPatVO,UserVO _userVO)		{
			
			InvResultEntryRespDelegate masterDelegate = new InvResultEntryRespDelegate();
			return masterDelegate.saveAddendumDetails(newPatVO,  _userVO);
		}

		
		public static String CHECKCISPARAMETERDEPENDENT(String fb, UserVO _UserVO,InvResultEntryFB fbb)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.CHECKCISPARAMETERDEPENDENT(fb ,_UserVO,fbb);
		}
		
		
} 
