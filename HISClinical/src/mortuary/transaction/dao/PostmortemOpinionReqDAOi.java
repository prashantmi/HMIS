package mortuary.transaction.dao;

import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.UserVO;

public interface PostmortemOpinionReqDAOi
{
	//Inserting Record
	public void create(PostmortemOpinionReqDtlVO postmortmOpinionReqVO, UserVO userVO);
	
	public PostmortemOpinionReqDtlVO[] getRequestedOpinion(String postmortemId, UserVO userVO);
	
	public void update(PostmortemOpinionReqDtlVO postmortmOpinionReqVO, UserVO userVO);
	
}
