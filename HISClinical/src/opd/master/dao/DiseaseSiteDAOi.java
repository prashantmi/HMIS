package opd.master.dao;

import hisglobal.vo.DiseaseSiteMasterVO;
import hisglobal.vo.UserVO;

public interface DiseaseSiteDAOi
{
	// Saving Disease Site Record 
	public void create(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Checking Duplicate Disease Site Record
	// returns true if found a Duplicate Record otherwise false
	public boolean checkDuplicate(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Getting Detail of Disease Site Record
	public DiseaseSiteMasterVO get(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Updating the Disease Site Record
	public void update(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Deleting the Disease Site Record
	public void delete(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Saving Updated Disease Site Record
	public void createUpdate(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Checking Duplicate Disease Site for Modification
	public boolean checkDuplicateforModify(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);
}
