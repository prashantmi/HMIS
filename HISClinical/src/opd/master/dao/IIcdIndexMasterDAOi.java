package opd.master.dao;

import hisglobal.utility.Entry;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdIndexMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface IIcdIndexMasterDAOi 
{
	
	
	/*
	 * To save Data on Add Page
	 */
	public void saveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO);
	
	
	/*
	 * To check Duplicate Record
	 */
	public boolean chkDuplicateIcd(IcdIndexMasterVO vo,UserVO userVO, String strInsertUpdate);
	
	/*
	 * To modify data
	 */
	public void getModifyRecord(IcdIndexMasterVO vo, UserVO userVO);
	
	/*
	 * To Update an old Record While Modifying a particular Record 
	 */
	public void modifyUpdate(IcdIndexMasterVO vo, UserVO userVO);
	

	/*
	 * To ModifySave a Record
	 */
	public void modifySave(IcdIndexMasterVO vo, UserVO userVO);
	
	
	
	/*
	 * To Get Icd Diseases and Dual Icd Diseases on the Modify Page
	 */
	//public String[] getIcdDiseases(IcdIndexLevelMasterVO vo, UserVO userVO);
	
	
}
