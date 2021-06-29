package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.delegate.InvResultValidationDelegate;
import new_investigation.vo.template.ResultEntryVO;


public class InvResultValidationDATA
{


	public static Map LabComboForResultValidation(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvResultValidationDelegate masterDelegate = new InvResultValidationDelegate();
		return masterDelegate.LabComboForResultValidation(InvResultEntryVO, _UserVO);
	}

	public static Map setPatientResultValidationEssentials(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvResultValidationDelegate masterDelegate = new InvResultValidationDelegate();
		return masterDelegate.setPatientResultValidationEssentials(InvResultEntryVO, _UserVO);
	}


	public static Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvResultValidationDelegate masterDelegate = new InvResultValidationDelegate();
		return masterDelegate.getResultEntryData(InvResultEntryVO);
	}

	


	//ResultValidation Save Logic
	public static Map saveResultValidationDetails(List<ResultEntryVO> voResultValidation,List<ResultEntryVO>  invResultValidationForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationFB fb)
	{
		InvResultValidationDelegate masterDelegate = new InvResultValidationDelegate();
		return masterDelegate.saveResultValidationDetails(voResultValidation,invResultValidationForParameterDtlVO,  _userVO,  _request,fb);
	}

	//update on re validating directly
	public static void revalidate(List<ResultEntryVO> InvResultEntryVO, UserVO _UserVO)
	{
		InvResultValidationDelegate masterDelegate = new InvResultValidationDelegate();
		masterDelegate.revalidate(InvResultEntryVO, _UserVO);
	}
	
	public static String checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO)
	{
		InvResultValidationDelegate masterDelegate = new InvResultValidationDelegate();
		return masterDelegate.checkcannedCodeName(fb ,_UserVO);
	}



} 
