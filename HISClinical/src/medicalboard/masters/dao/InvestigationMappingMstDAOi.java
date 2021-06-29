package medicalboard.masters.dao;

import hisglobal.vo.MbInvestigationMappingMstVO;
import hisglobal.vo.UserVO;

public interface InvestigationMappingMstDAOi 
{
	public void create(MbInvestigationMappingMstVO investigationMappingVO,UserVO userVO);
	
	public void updateInvestigationMappingDtl(MbInvestigationMappingMstVO investigationMappingVO,UserVO _UserVO);
	
}
