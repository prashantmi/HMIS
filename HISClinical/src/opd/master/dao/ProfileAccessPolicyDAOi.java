package opd.master.dao;

import java.util.List;

import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.UserVO;

public interface ProfileAccessPolicyDAOi {

	public String checkDuplicateBeforeSave(ProfileAccessPolicyVO profileAccessPolicyVO,UserVO userVO);
	
	public void save(ProfileAccessPolicyVO profileAccessPolicyVO,UserVO userVO);
	
	public ProfileAccessPolicyVO fetchProfileAccessPolicyModify(ProfileAccessPolicyVO profileAccessPolicyVO,UserVO userVO) ;
	
	public String checkDuplicateBeforeModify(ProfileAccessPolicyVO profileAccessPolicyVO,UserVO userVO);
	
	public void modify(ProfileAccessPolicyVO profileAccessPolicyVO,UserVO userVO);
	
	public void modifyInsert(ProfileAccessPolicyVO profileAccessPolicyVO,UserVO userVO);
	
	public List getDeptUnitByProfileTypeAndPolicyType(
			ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO);
	
}
