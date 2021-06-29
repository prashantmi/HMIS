package opd.master.dao;

import java.util.List;

import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileGroupDetailDAOi {

	public void save(ProfileGroupDtlVO profileGroupDetailVO,UserVO userVO);
	
	public List<ProfileGroupDtlVO> fetchProfileGroupDetailAccessModify(ProfileGroupDtlVO profileGroupDetailVO,UserVO userVO);
	
	public void modify(ProfileGroupDtlVO profileGroupDtlVO,UserVO userVO);
	
	public void modifyInsert(ProfileGroupDtlVO profileGroupDtlVO,UserVO userVO);
	
	public String getMaxSerialNoforSave(String strHospitalCode_p); 
}
