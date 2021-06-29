package mortuary.transaction.controller.data;

import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AddressVO;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.UserVO;

public class OfflineDeceasedAcceptanceDATA extends ControllerDATA
{
	public static PatientDeathDetailVO getDeceasedHandoverDetail(String crNo,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getDeceasedHandoverDetail(crNo,userVO);
	}
	
	public static Map getDeceasedEssentialStorageDetail(UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getDeceasedEssentialStorageDetail(userVO);
	}
	
	public static String saveOfflineDeceasedStorageDetail(DeceasedDetailVO deceasedDtlVO, DeceasedBroughtByDtlVO deceasedBroughtVO, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.saveOfflineDeceasedStorageDetail(deceasedDtlVO, deceasedBroughtVO, deceasedRelativeVO, deceasedStorageVO,userVO);
	}
	
	/** Getting Deceased Address
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static AddressVO getPatAddress(String crNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPatAddress(crNo,userVO);
	}
}
