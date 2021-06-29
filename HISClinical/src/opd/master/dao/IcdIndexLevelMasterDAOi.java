package opd.master.dao;

import hisglobal.utility.Entry;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1-Feb-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 28-Mar-2011  
 */
public interface IcdIndexLevelMasterDAOi 
{
	
	
	/*
	 * Getting the List of all Index Terms 
	 * 
	 * @param _userVO User Detail
	 */	
	public List<Entry> getIndexTermCombo(UserVO _userVO);
	
	/*
	 *  Populating the Parent Modifier Combo
	 *  
	 *  @param icdIndexLevelMasterVO the vo
	 *  @param _userVO User Detail
	 */
	public List<Entry> getParentModifierCombo(IcdIndexLevelMasterVO icdIndexLevelMasterVO,UserVO _userVO);
	

	/*
	 * Populating the Icd SubgroupCombo and Dual Icd SubgroupCombo on basis of Group  
	 * 
	 * @param icdIndexLevelMasterVO the vo
	 *  @param _userVO User Detail
	 */
	public List<IcdSubgroupMasterVO> getSubgroupsByGroup(String _icdGroupCode, UserVO _userVO);

	
	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO);
	
	/*
	 * To save Data on Add Page
	 */
	public void saveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO);
	
	/*
	 * To get modify details
	 */
	public void getModifyRecord(IcdIndexLevelMasterVO vo, UserVO userVO);
	
	/*
	 * To View Page
	 */
	public void getViewRecord(IcdIndexLevelMasterVO vo, UserVO userVO);

	
	
	/*
	 * To Update an old Record While Modifying a particular Record 
	 */
	public void modifyUpdate(IcdIndexLevelMasterVO vo, UserVO userVO);
	
	/*
	 * To ModifySave a Record
	 */
	public void modifySave(IcdIndexLevelMasterVO vo, UserVO userVO);
	
	/*
	 * To Check the duplicacy in case of Add and Modify
	 */
	public boolean chkDuplicate(IcdIndexLevelMasterVO vo,UserVO userVO, String strInsertUpdate);

	
}

