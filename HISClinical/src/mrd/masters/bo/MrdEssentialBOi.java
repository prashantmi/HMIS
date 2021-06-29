package mrd.masters.bo;

import hisglobal.vo.EprRestrictedCategoryVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface MrdEssentialBOi {

	// Functions for Rack Shelf Master
	
	public List getRackNamelist(UserVO userVO);
	
	public Map getMrdBoundingEssential(UserVO userVO);
	
	public List getBoundedRecordType(String boundingMode,String boundingId,UserVO userVO);
	
	public List getRackBasedOnMrd(String mrdCode,UserVO userVO);
	
	public List getShelfBasedOnRack(String rackId,UserVO userVO);

	public Map getEssentialForEPRRestrictedCat(UserVO userVO);

	public void saveEprPatRestrictedCategory(EprRestrictedCategoryVO[] eprRestrictedCatMstVO, UserVO userVO);
	
	public Map getFormPReportEssentials(UserVO _userVO);
}
