package mortuary.transaction.controller.data;

import java.util.List;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.UserVO;

public class BodyIdentificationDetailDATA extends ControllerDATA
{
	public static List getAllRelationship(UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAllRelationship(userVO);
	}
	public static void saveBodyIdentificationDetail(DeceasedRelativeDtlVO[] arrRelativeVO, DeceasedIdentityDtlVO[] arrIdentityDtlVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveBodyIdentificationDetail(arrRelativeVO,arrIdentityDtlVO,userVO);
	}
	
	//Not In Used
	public static boolean checkBodyIdentified(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.checkBodyIdentified(deceasedNo,userVO);
	}
	
	public static DeceasedIdentityDtlVO[] getIdentifiedByDetail(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getIdentifiedByDetail(deceasedNo,userVO);
	}
	
	public static DeceasedRelativeDtlVO[] getExistingRelative(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getExistingRelative(deceasedNo,userVO);
	}
	
}
