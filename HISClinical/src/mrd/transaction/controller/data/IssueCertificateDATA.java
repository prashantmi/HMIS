package mrd.transaction.controller.data;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.UserVO;

public class IssueCertificateDATA extends ControllerDATA
{
	/** Getting the List of Generated Medical Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static PatMedicalDtlVO[] getAllGeneratedMCList(String crNo,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getAllGeneratedMCList(crNo, userVO);
	}
	
	/** Getting the List of Generated Fitness Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static PatFitnessDtlVO[] getAllGeneratedFCList(String crNo,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getAllGeneratedFCList(crNo, userVO);
	}
	
	/** Saving the Issue Certificate detail
	 * @param certificateIssueDtlVO
	 * @param userVO
	 */
	public static void saveCertificateIssueDtl(RecordDispatchDtlVO dispatchVO, CertificateIssueDtlVO certificateIssueDtlVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveCertificateIssueDtl(dispatchVO,certificateIssueDtlVO,userVO);
	}
	
	/** Getting the List of Duplicate Medical Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static PatMedicalDtlVO[] getAllDuplicateMCList(String crNo,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getAllDuplicateMCList(crNo, userVO);
	}
	
	/** Getting the List of Duplicate Fitness Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static PatFitnessDtlVO[] getAllDuplicateFCList(String crNo,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getAllDuplicateFCList(crNo, userVO);
	}
}
