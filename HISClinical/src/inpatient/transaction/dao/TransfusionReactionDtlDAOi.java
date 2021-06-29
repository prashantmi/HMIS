package inpatient.transaction.dao;

import java.util.List;

import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.UserVO;

public interface TransfusionReactionDtlDAOi {
	
	public void creat(TransfusionReactionDtlVO transReactionDtlVO,UserVO userVO);
	
	public List getTemplateIdList(UserVO userVO);
	
	public String getMaxSlNo(UserVO userVO);

}
