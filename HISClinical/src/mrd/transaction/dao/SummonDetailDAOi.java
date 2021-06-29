package mrd.transaction.dao;

import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

public interface SummonDetailDAOi 
{
	public void create(SummonDetailVO summonDetailVO,UserVO userVO);
	
	public void SaveAssignDetail(SummonDetailVO summonDetailVO,UserVO userVO);
	
	public void SavePostSummonDetail(SummonDetailVO summonDetailVO,UserVO userVO);
	
	public void saveComputSummonDtl(SummonDetailVO summonDetailVO,UserVO userVO);
	
	public void SaveSummonAcceptedDetail(String summonId,UserVO userVO);
	
}
