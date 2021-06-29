package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.UserVO;

public class OpinionApprovalDATA extends ControllerDATA
{
	public static DeceasedDetailVO[] getFinalOpinionToBeApproved(UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getFinalOpinionToBeApproved(userVO);
	}
	
	public static PostmortemDetailVO getExistingGeneralAppearance(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getExistingGeneralAppearance(postmortemId,userVO);
	}
	
	public static Map getPostmortemConductedEssential(String dob, String postmortemId, UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPostmortemConductedEssential(dob, postmortemId, userVO);
	}
	
	public static Map<String, Map<String, String>> getTemplateDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getTemplateDetail(postmortemId,userVO);
	}
	
	public static PostmortemOpinionDetailVO[] getAddedOpinion(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAddedOpinion(postmortemId,userVO);
	}
	
	public static void saveApprovedFinalOpinion(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl,PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveApprovedFinalOpinion(postMortemExamVOList,lstOpinionDtl,postmortemDtlVO,userVO);
	}
	
	public static MortuaryExtLabRequestDtlVO[] getSampleResult(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getSampleResult(postmortemId,userVO);
	}
	

}
