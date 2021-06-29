package inpatient.masters.bo;

import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface UnitInvParaMappingMstBOi {
	
	public void saveUnitWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO);
	
	public void updateTableUnitWise(String _unitId,UserVO _UserVO);
	
	public void updateTableWardWise(String _unitId,String _wardCode, UserVO _UserVO);

	public void saveUnitWardWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO);
	
	public List gettingWards(String _deptUnitCode,UserVO _UserVO);

}