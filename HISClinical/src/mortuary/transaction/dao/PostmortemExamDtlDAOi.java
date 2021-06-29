package mortuary.transaction.dao;

import java.util.Map;

import hisglobal.vo.PostmortemExamDtlVO;
import hisglobal.vo.UserVO;

public interface PostmortemExamDtlDAOi 
{
	public void create(PostmortemExamDtlVO postmortemExamDtlVO,UserVO userVO);
	
	public Map<String, Map<String, String>> getTemplateDetail(String postmortemId,UserVO userVO);
	
	public void updateValue(PostmortemExamDtlVO postmortemExamDtlVO,UserVO userVO);
	
	public void invalidTheRecord(String postmortemId,UserVO userVO);
}
