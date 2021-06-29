package opd.master.dao;

import hisglobal.vo.ProfileGroupMasterVO;
import hisglobal.vo.UserVO;

public interface ProfileGroupMasterDAOi {

	public String checkDuplicateBeforeSave(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO);
	
	public void save(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO);
	
	public ProfileGroupMasterVO fetchProfileGroupDetailModify(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO) ;
	
	public String checkDuplicateBeforeModify(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO);
	
	public void modify(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO);
	
	public void modifyInsert(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO);
}
