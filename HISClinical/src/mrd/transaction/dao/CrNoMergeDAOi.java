package mrd.transaction.dao;

import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface CrNoMergeDAOi 
{
	public void saveNotUsedCrNo(CrNoMergeDtlVO crNoMergeDtlVO,UserVO userVO);
	
	public List getMergedCrNo(String crNo,UserVO userVO);
	
	public void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO);
	
	public List getMainCRNumberList(UserVO userVO);
	
	public String countMergedCrNo(String crNo,UserVO userVO);
	
	public String countMainCrNo(String crNo,UserVO userVO);
	
	public List<CrNoMergeDtlVO> getMergedCrNoForEMR(String crNo,UserVO userVO);
}
