package mortuary.transaction.dao;

import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;

public interface PostmortemRequiredDetailDAOi 
{
	//Generating Postmortem Id
	public String generatePostmortemId(PostmortemRequestDetailVO postmortmReqDtlVO, UserVO userVO);
	
	//Inserting Record
	public void create(PostmortemRequestDetailVO postmortmReqDtlVO, UserVO userVO);
	
	public PostmortemRequestDetailVO[] getPostmortemReqList(UserVO userVO);
	
	public PostmortemRequestDetailVO getPostmortemRequestDetail(String postmortemId,UserVO userVO);
	
	
	public void updatePreviousRow(PostmortemRequestDetailVO postmortmReqDtlVO, UserVO userVO);
	
	public void updatePostmortemStatus(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO, UserVO userVO);
	
	public String getPostmortemStatusForHandover(String deceasedNo,UserVO userVO);
	
	public void updatePostmortemStatusCompleted(String postmortemId,String status, UserVO userVO);
	
	
	
}
