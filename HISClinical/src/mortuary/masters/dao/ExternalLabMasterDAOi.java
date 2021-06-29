package mortuary.masters.dao;


import hisglobal.vo.ExternalLabMasterVO;
import hisglobal.vo.UserVO;

public interface ExternalLabMasterDAOi { 

	public void saveExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO);

	public ExternalLabMasterVO getExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO);

	public void modiftExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO);

	public void modifyInsertExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO);	
	
	public void checkDuplicateBeforeInsert(String chamberName, UserVO _userVO) ;

	public void checkDuplicateBeforeModify(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO) ;	
	
}
