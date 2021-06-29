package mrd.transaction.dao;

import hisglobal.vo.MrdRecordMovementVO;
import hisglobal.vo.UserVO;

public interface MrdRecordMovementDAOi {

	public void create(MrdRecordMovementVO movementVO,UserVO userVO);
}
