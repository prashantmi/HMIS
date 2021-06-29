package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.UserVO;

public class GeneralAppearanceEntryDATA extends ControllerDATA
{
	public static Map getPostmortemConductedEssential(String dob, String postmortemId, UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPostmortemConductedEssential(dob, postmortemId, userVO);
	}
	
	public static void saveGeneralAppearance(List postMortemExamVOList, List<PostmortemOpinionDetailVO> lstOpinionDtl,PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveGeneralAppearance(postMortemExamVOList,lstOpinionDtl,postmortemDtlVO,userVO);
	}
	
	//Not in Used
	public static boolean checkExistingRecord(String postmoretmId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.checkExistingRecord(postmoretmId,userVO);
	}
	
	public static PostmortemDetailVO getExistingGeneralAppearance(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getExistingGeneralAppearance(postmortemId,userVO);
	}
	
	public static PostmortemOpinionDetailVO[] getAddedOpinion(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAddedOpinion(postmortemId,userVO);
	}
	
	public static void updateGeneralAppearance(List postMortemExamVOList,List<PostmortemOpinionDetailVO> lstOpinionMod, List<PostmortemOpinionDetailVO> lstOpinionDtl,PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.updateGeneralAppearance(postMortemExamVOList, lstOpinionMod, lstOpinionDtl,postmortemDtlVO,userVO);
	}
	
	public static Map<String, Map<String, String>> getTemplateDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getTemplateDetail(postmortemId,userVO);
	}
}
