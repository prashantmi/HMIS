package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.UserVO;

public class UnknownBodyIdentificationDATA extends ControllerDATA
{
	public static DeceasedDetailVO[] getUnknownBodyList(UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getUnknownBodyList(userVO);
	}
	
	public static List getAllRelationship(UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAllRelationship(userVO);
	}
	
	public static DeceasedIdentityDtlVO[] getIdentifiedByDetail(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getIdentifiedByDetail(deceasedNo,userVO);
	}
	
	public static void saveUnknownBodyIdentificationDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveUnknownBodyIdentificationDetail(deceasedDtlVO,relativeVO,identityDtlVO,userVO);
	}
	
	public static Map getEssentialForUnknown(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForUnknown(deceasedNo,userVO);
	}
	
	public static void saveDeceasedClaimedDetail(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO relativeVO, DeceasedIdentityDtlVO identityDtlVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveDeceasedClaimedDetail(deceasedDtlVO,relativeVO,identityDtlVO,userVO);
	}
	
}
