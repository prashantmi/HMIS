package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.invReportAddendumDelegate;
import new_investigation.vo.invReportAddendumVO;
import new_investigation.vo.template.ResultEntryVO;


public class invReportAddendumDATA
{


	public static Map LabComboForResultValidation(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.LabComboForResultValidation(InvResultEntryVO, _UserVO);
	}

	public static Map setPatientReportAddendumEssentials(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.setPatientReportAddendumEssentials(InvResultEntryVO, _UserVO);
	}


	public static Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.getResultEntryData(InvResultEntryVO);
	}




	//ResultValidation Save Logic
	public static Map saveReportAddendumDetails(ResultEntryVO newPatVO,List<ResultEntryVO> oldPatList,List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,String amendType)		{
	
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.saveReportAddendumDetails(newPatVO,oldPatList,invResultentryVO,invResultValidationForParameterDtlVO,  _userVO,  _request, amendType);
	}

	//update on re validating directly
	public static void revalidate(List<ResultEntryVO> InvResultEntryVO, UserVO _UserVO)
	{
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		masterDelegate.revalidate(InvResultEntryVO, _UserVO);
	}


	public static ResultEntryVO getNewEntriesPatient(ResultEntryVO voResultValidation,UserVO _userVO)
	{
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.getNewEntriesPatient(voResultValidation,  _userVO);
	}
	public static ResultEntryVO getOldEntriesPatient(ResultEntryVO voResultValidation,UserVO _userVO)
	{
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.getOldEntriesPatient(voResultValidation,  _userVO);
	}
	
	
	// for reason list
	public static Map reasonList( UserVO _UserVO)
	{
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.reasonList( _UserVO);
	}
	
	
	
	public static Map saveAddendumDetails(invReportAddendumVO newPatVO,UserVO _userVO)		{
		
		invReportAddendumDelegate masterDelegate = new invReportAddendumDelegate();
		return masterDelegate.saveAddendumDetails(newPatVO,  _userVO);
	}
	
	
} 
