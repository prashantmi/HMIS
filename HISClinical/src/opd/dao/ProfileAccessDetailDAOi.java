package opd.dao;

import java.util.List;

import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.UserVO;

public interface ProfileAccessDetailDAOi
{
	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileAccessDetailVO _profileAccessDtlVO, UserVO _userVO);

	/**
	 * Removing Patient Profile Access Priviledges
	 * @param _profileId Profile Id 
	 * @param _userVO User Detail
	 */
	public void delete(String _profileId, UserVO _userVO);

	/**
	 * Getting Profile Access Priviledges
	 * @param _profileId Profile Id 
	 * @param _userVO User Detail
	 * @return List Parofile Access Priviledges
	 */
	public List<ProfileAccessDetailVO> get(String _profileId, UserVO _userVO);
	
	public void removeUserPriv(ProfileAccessDetailVO _profileAccessDetailVO, UserVO _userVO);
}
