package mortuary.masters.dao;


import hisglobal.vo.ExternalLabTestMasterVO;
import hisglobal.vo.UserVO;

public interface ExternalLabTestMasterDAOi { 

	public void saveExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO);

	public ExternalLabTestMasterVO getExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO);

	public void modiftExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO);

	public void modifyInsertExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO);	
	
	public void checkDuplicateBeforeInsert(String chamberName, UserVO _userVO) ;

	public void checkDuplicateBeforeModify(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO) ;	
	
}
