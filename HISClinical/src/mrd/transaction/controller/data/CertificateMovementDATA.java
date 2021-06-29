package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;

public class CertificateMovementDATA extends ControllerDATA
{
	
	/** Getting The List of Certificate For Movement By Cr No 
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static CertificateIssueDtlVO[] getAllCertificateForMoveByCrNo(String crNo,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllCertificateForMoveByCrNo(crNo,userVO);
	}
	
	/** Getting The List of Certificate For Movement By Unit 
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public static CertificateIssueDtlVO[] getAllCertificateForMoveBydUnit(String unitCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllCertificateForMoveBydUnit(unitCode,userVO);
	}
	
	/** Saving Certificate Movement Detail
	 * @param recordMoveDtlVO
	 * @param userVO
	 */
	public static void saveCertificateMovement(List<RecordCheckListDtlVO> lstRecordCheckList,RecordDispatchDtlVO[] recordMoveDtlVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveCertificateMovement(lstRecordCheckList,recordMoveDtlVO, userVO);
	}
	
	/**  Getting The List of Unit
	 * @param userVO
	 * @return
	 */
	public static List getAllUnit(UserVO userVO)
	{
		OpdEssentialDelegate opdEssDelegate=new OpdEssentialDelegate();
		return opdEssDelegate.getAllUnit(userVO);
	}
	
	public static RecordTypeCheckListMstVO[] getCheckListForMedNFitCertificate(String recordType,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getCheckListForMedNFitCertificate(recordType,userVO);
	}
	
	public static void rejectCertificateHandover(List<RecordDispatchDtlVO> lstRejectRecord,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.rejectCertificateHandover(lstRejectRecord, userVO);
	}
	
	public static Map getEssentialForCertificateReceived(String recordType,String unitCode,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getEssentialForCertificateReceived(recordType,unitCode,userVO);
	}
	
}
