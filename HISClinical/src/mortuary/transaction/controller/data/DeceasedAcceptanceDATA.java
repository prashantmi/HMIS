package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AddressVO;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedAcceptanceDATA extends ControllerDATA
{
	 
	/** Getting The List of Dead Patient Send To Mortuary
	 * @param userVO
	 * @return
	 */
	public static PatientDeathDetailVO[] getInHouseDeadPatientList(UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getInHouseDeadPatientList(userVO);
	}
	
	
	/** Getting The List of Peon on The Basis of Department Code Who Brought The Dead Body
	 * @param deptCode
	 * @param userVO
	 * @return
	 */
	public static List getPeonList(String deptCode,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPeonList(deptCode,userVO);
	}
	
	
	/** Getting Essential Data
	 * @param deptCode
	 * @param userVO
	 * @return
	 */
	public static Map getDeceasedEssential(String deptCode,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getDeceasedEssential(deptCode,userVO);
	}
	
	
	/** Saving Deceased Detail
	 * @param deceasedImageDtlVO
	 * @param policeVerVO
	 * @param deceasedBroughtVO
	 * @param deceasedDtlVO
	 * @param deceasedHandoverVO
	 * @param deceasedRelativeVO
	 * @param deceasedStorageVO
	 * @param userVO
	 */
	public static String saveDeceasedDetail(MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO,MortuaryPoliceVerificationVO policeVerVO,DeceasedBroughtByDtlVO deceasedBroughtVO,DeceasedDetailVO deceasedDtlVO,DeceasedHandoverDtlVO deceasedHandoverVO,DeceasedRelativeDtlVO deceasedRelativeVO,DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.saveDeceasedDetail(deceasedImageDtlVO,policeVerVO,deceasedBroughtVO,deceasedDtlVO,deceasedHandoverVO,deceasedRelativeVO,deceasedStorageVO,userVO);
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
	
	
	/** Getting Police Verification Detail
	 * @param mlcNo
	 * @param userVO
	 * @return
	 */
	public static PoliceVerificationDtlVO getPoliceVerificationDetail(String mlcNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPoliceVerificationDetail(mlcNo,userVO);
	}
	
	
	/** Getting Deceased Existing Image
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static PatientImageDtlVO[] getDeceasedExistingImage(String crNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getDeceasedExistingImage(crNo,userVO);
	}
	
	public static String getPatientIdMark(String crNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPatientIdMark(crNo,userVO);
	}
}
