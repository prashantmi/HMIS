package opd.dao;

import hisglobal.vo.ProfileReviewDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileReviewDtlDAOi 
{
	public void create(ProfileReviewDtlVO _profileReviewDtlVO, UserVO userVO);
	
	public ProfileReviewDtlVO[] fetchProfileReviewDetails(String _crNo,String profileId,UserVO _userVO);
}
