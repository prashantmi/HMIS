package mortuary.transaction.controller.data;

import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.UserVO;

public class ExternalDeceasedAcceptanceDATA extends ControllerDATA
{
	public static Map getEssentialForExternalBodyAcceptance(UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForExternalBodyAcceptance(userVO);
	}
	
	public static String saveExternalDeceasedAcceptance(DeceasedDetailVO deceasedDtlVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.saveExternalDeceasedAcceptance(deceasedDtlVO,deceasedRelativeVO,deceasedStorageVO,deceasedBroughtVO,policeVerVO,userVO);
	}
	
	public static String saveOnSpotDeceasedAcceptance(DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedDetailVO deceasedDtlVO,DeceasedStorageDtlVO deceasedStorageVO,DeceasedBroughtByDtlVO deceasedBroughtVO,MortuaryPoliceVerificationVO policeVerVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.saveOnSpotDeceasedAcceptance(deceasedRelativeVO,deceasedDtlVO,deceasedStorageVO,deceasedBroughtVO,policeVerVO,userVO);
	}
	
	//added for the purpose of duplicate MLC Check at External Deceased Entry By Shruti Shail on 11-May-2017
	public static Boolean chkDuplicateMLC(String mlcNo,String HospCode)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.chkDuplicateMLC(mlcNo,HospCode);
		
	}
}
