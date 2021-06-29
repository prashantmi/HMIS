package medicalboard.masters.dao;

import hisglobal.vo.MbReferMappingMstVO;
import hisglobal.vo.UserVO;

public interface ReferMappingMstDAOi {

	public void create(MbReferMappingMstVO mappingMstVO,UserVO userVO);
	public MbReferMappingMstVO[] getReferMappingDetail(MbReferMappingMstVO mReferMappingMstVO,UserVO _UserVO);
	public void updateReferDetail(String certificateTypeId,UserVO _UserVO);
}
