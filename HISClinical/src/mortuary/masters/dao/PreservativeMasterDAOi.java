package mortuary.masters.dao;


import hisglobal.vo.PreservativeMasterVO;
import hisglobal.vo.UserVO;

public interface PreservativeMasterDAOi { 

	public void saveExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO);

	public PreservativeMasterVO getExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO);

	public void modiftExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO);

	public void modifyInsertExternalLabTestDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO);	
	
	public void checkDuplicateBeforeInsert(String preservativeName, UserVO _userVO) ;

	public void checkDuplicateBeforeModify(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO) ;	
	
}
