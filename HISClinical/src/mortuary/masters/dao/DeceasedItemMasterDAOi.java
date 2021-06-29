package mortuary.masters.dao;

import hisglobal.vo.DeceasedItemMasterVO;
import hisglobal.vo.UserVO;

public interface DeceasedItemMasterDAOi 
{
	//Inserting Record 
	public void create(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateItemName(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	
	public DeceasedItemMasterVO getDataForModify(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	
	public void updateDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	
	public void modifySaveDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	
	public String checkDuplicateNameForModify(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);

	
}
