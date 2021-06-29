package mortuary.transaction.dao;

import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.UserVO;

public interface PostmortemDetailDAOi 
{
	public void create(PostmortemDetailVO postmortemDtlVO,UserVO userVO);
	
	public boolean checkExistingRecord(String postmortemId,UserVO userVO);
	
	public void saveInjuriesDetail(PostmortemDetailVO postmortemInjuryVO, UserVO userVO);
	
	public PostmortemDetailVO getAddedInjuryDetailToDisplay(String postmortemId,UserVO userVO);
	
	public PostmortemDetailVO getExistingGeneralAppearance(String postmortemId,UserVO userVO);
	
	public void updateGeneralAppearance(PostmortemDetailVO postmortemDtlVO,UserVO userVO);
	
	public void saveApprovedFinalOpinion(PostmortemDetailVO postmortemDtlVO,UserVO userVO);
	
	public void invalidPreviousRow(PostmortemDetailVO postmortemDtlVO,UserVO userVO);
	
	
}
