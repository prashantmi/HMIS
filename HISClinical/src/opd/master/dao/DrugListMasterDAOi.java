package opd.master.dao;

import java.util.List;

import hisglobal.vo.DrugListMasterVO;
import hisglobal.vo.UserVO;

public interface DrugListMasterDAOi 
{
	public void creat(DrugListMasterVO drugListMasterVO ,UserVO userVO);
	public String checkDuplicateName(DrugListMasterVO drugListMasterVO,UserVO userVO);
	public DrugListMasterVO getDataForModify(DrugListMasterVO drugListMasterVO, UserVO _UserVO);
	public String checkDuplicateNameForModify(DrugListMasterVO drugListMasterVO,UserVO userVO);
	public void updateDrugListMaster(DrugListMasterVO drugListMasterVO,UserVO _UserVO);
	public void saveModDrugListMaster(DrugListMasterVO drugListMasterVO ,UserVO userVO);
	public List getDrugListNameList(UserVO _userVO);
}
