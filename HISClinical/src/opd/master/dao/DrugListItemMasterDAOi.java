package opd.master.dao;

import java.util.List;

import hisglobal.vo.DrugListItemMstVO;
import hisglobal.vo.UserVO;

public interface DrugListItemMasterDAOi 
{
	public void creat(DrugListItemMstVO drugDetailVo ,UserVO userVO);
	public List getDrugDeatilByDrugList(DrugListItemMstVO drugListItemMstVO,UserVO _userVO);
	public void updateDrugListItemMaster(DrugListItemMstVO drugListItemMstVO,UserVO _UserVO);
}
