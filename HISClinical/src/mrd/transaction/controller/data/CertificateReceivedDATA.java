package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

public class CertificateReceivedDATA extends ControllerDATA
{
	/**  Getting the List Of Certificate, Rack & Shelf List
	 * @param certificateRcvMode
	 * @param _certificateIssueDtlVO
	 * @param userVO
	 * @return
	 */
	public static Map getEssentialForCertificateReceived(String certificateRcvMode,CertificateIssueDtlVO _certificateIssueDtlVO,UserVO userVO)
	{
		MrdEssentialDelegate  mrdDelegate= new MrdEssentialDelegate();
		return mrdDelegate.getEssentialForCertificateReceived(certificateRcvMode,_certificateIssueDtlVO,userVO);
	}
	
	/** Getting the List Of Shelf On Basis of Rack
	 * @param _certificateIssueDtlVO
	 * @param userVO
	 * @return
	 */
	/*public static List getSelf(CertificateIssueDtlVO _certificateIssueDtlVO,UserVO userVO)
	{
		MrdEssentialDelegate  mrdDelegate= new MrdEssentialDelegate();
		return mrdDelegate.getSelf(_certificateIssueDtlVO,userVO);
	}*/
	
	/**  Saving the Certificate Received Details
	 * @param certificateRcvMode
	 * @param mrdRecordDtlVO
	 * @param userVO
	 */
	/*public static void saveReceiveCertificate(String certificateRcvMode,MrdRecordDtlVO[] mrdRecordDtlVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveReceiveCertificate(certificateRcvMode,mrdRecordDtlVO,userVO);
	}*/
}
 