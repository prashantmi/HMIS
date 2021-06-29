package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import opd.bo.delegate.OpdEssentialDelegate;

public class CaseSheetEnquiryDATA extends ControllerDATA
{
	
	public static Map getCaseSheetEnquiryEssentials(UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getCaseSheetEnquiryEssentials(userVO); 
	}
	
	public static CommonCaseSheetEnquiryVO[] searchPatientCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO){
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.searchPatientCaseSheet(caseSheetEnquiryVO,_userVO);
	}
	
	public static MrdRecordDtlVO[] fetchRecordStorageDetail(CommonCaseSheetEnquiryVO _commonCaseSheetEnquiryVO,UserVO _userVO){
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.fetchRecordStorageDetail(_commonCaseSheetEnquiryVO,_userVO);
	}

	public static List getIcdCodes(String _searchCode,UserVO _userVO){
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getIcdCodes(_searchCode,_userVO);
	}
	
	public static List getDiseaseName(String _searchDisease, UserVO _userVO){
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getDiseaseName(_searchDisease,_userVO);
	}
}
