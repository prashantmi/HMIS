package mortuary.masters.dao;


import hisglobal.vo.ChamberMasterVO;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.DisasterRoleMstVO;
import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.UserVO;

public interface ChamberRackMasterDAOi { 

	public void saveChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO);

	public ChamberRackMasterVO getChamberRackDetails(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO);

	public void modifyChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO);

	public void modifyInsertChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO);	
	
	public void checkDuplicateBeforeInsert(String rackName,String chamberId, UserVO _userVO) ;

	public void checkDuplicateBeforeModify(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO) ;	
	
	public void checkChamberIdPresent(String _chamberId,UserVO _userVO) ;	
}
