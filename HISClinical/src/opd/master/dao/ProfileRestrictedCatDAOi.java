package opd.master.dao;

import java.util.List;

import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.UserVO;

public interface ProfileRestrictedCatDAOi {
	
	public String checkDuplicateBeforeSave(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO);
	
	public void save(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO);
	
	public ProfileRestrictedCategoryMasterVO fetchPatientCatModify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) ;
	
	public String checkDuplicateBeforeModify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO);
	
	public void modify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO);
	
	public void modifyInsert(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO);

	public List getCategoryBasedOnProfileType(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO);
	
	public void updateIsValid(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO);
}
