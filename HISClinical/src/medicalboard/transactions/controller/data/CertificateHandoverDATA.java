package medicalboard.transactions.controller.data;

import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;
import medicalboard.transactions.delegate.MbTransactionDelegate;

public class CertificateHandoverDATA 
{
	public static Map setCertificateHandoverEssentials(UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.setCertificateHandoverEssentials(_UserVO);
	}
	
	public static Map getReqDateByCertificateTypeId(String certificaetTypeId,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getReqDateByCertificateTypeId(certificaetTypeId,_UserVO);
	}
	
	public static List getCandidateListByReqDate(String certificaetTypeId,String reqDate,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getCandidateListByReqDate(certificaetTypeId,reqDate,_UserVO);
	}
	
	public static List getcandidateListByCerficateType(String certificaetTypeId,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getcandidateListByCerficateType(certificaetTypeId,_UserVO);
	}
	public static List getAllCandidateListByCrNo(String patCrNo,UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getAllCandidateListByCrNo(patCrNo,userVO);
	}
		
	public static void saveHandOverDetail(List handOverDetailVOList,UserVO _UserVO)
	{
		MbTransactionDelegate mTranseDelegate=new MbTransactionDelegate();
		mTranseDelegate.saveHandOverDetail(handOverDetailVOList,_UserVO);
	}
	
	/** Getting The Certificate For Movement By Cr No 
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static PatientImageDtlVO  getPatientImageDtlByCrNo(String crNo,UserVO userVO)
	{
		MbTransactionDelegate  mTranseDelegate= new MbTransactionDelegate();
		return mTranseDelegate.getPatientImageDtlByCrNo(crNo,userVO);
	}
	
}
