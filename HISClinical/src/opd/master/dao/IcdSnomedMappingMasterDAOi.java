package opd.master.dao;

import hisglobal.vo.IcdSnomedMappingMasterVO;
import hisglobal.vo.UserVO;

public interface IcdSnomedMappingMasterDAOi {

	public boolean checkDuplicateBeforeSave(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, String _snomedID);
		
	public IcdSnomedMappingMasterVO fetchIcdSnomedModify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO);
		
	public void modify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO);	
	
	public void mapSnomed(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO);

	}
