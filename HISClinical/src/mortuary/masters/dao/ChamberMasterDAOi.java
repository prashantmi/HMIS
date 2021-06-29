package mortuary.masters.dao;


import hisglobal.vo.ChamberMasterVO;
import hisglobal.vo.DisasterRoleMstVO;
import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.UserVO;

public interface ChamberMasterDAOi { 

	public void saveChamber(ChamberMasterVO _chamberMstVO,UserVO _userVO);

	public ChamberMasterVO getChamberDetails(ChamberMasterVO _chamberMstVO,UserVO _userVO);

	public void modifyChamber(ChamberMasterVO _chamberMstVO,UserVO _userVO);

	public void modifyInsertChamber(ChamberMasterVO _chamberMstVO,UserVO _userVO);	
	
	public void checkDuplicateBeforeInsert(String chamberName, UserVO _userVO) ;

	public void checkDuplicateBeforeModify(ChamberMasterVO _chamberMstVO,UserVO _userVO) ;	
	
}
