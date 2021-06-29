package inpatient.transaction.dao;

import java.util.List;

import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.UserVO;

public interface BloodTransfusionDAOi 
{
	public void creat(BloodTransfusionDtlVO bloodTransfusionDtlVO,UserVO userVO);
	
	public List getTemplateIdList(UserVO userVO);
	
}
