package mrd.transaction.dao;

import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;

import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;

public interface MrdEnquiryDAOi {

	public CommonCaseSheetEnquiryVO[] searchPatientCaseSheet(HashMap _finalQueryMap,UserVO _userVO);
	
	public MrdRecordDtlVO[] fetchRecordStorageDetail(CommonCaseSheetEnquiryVO _commonCaseSheetEnquiryVO,UserVO userVO);
	
	public CommonCaseSheetEnquiryVO[] searchChronicCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO);
	
	public CommonCaseSheetEnquiryVO[] searchAllergyCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO);
	
	public CommonCaseSheetEnquiryVO[] searchDiagnosisCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO);
}
