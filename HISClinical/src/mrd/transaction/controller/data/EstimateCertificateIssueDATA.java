/*Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:22-Nov-2014 

*/

package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;
import mrd.vo.EstimateCertificateIssueVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class EstimateCertificateIssueDATA extends ControllerDATA{

	public static Map getEstimateCertificateReqDtl(UserVO UserVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return (delegate.getEstimateCertificateReqDtl(UserVO));
	}
	public static Map getEstimateCertificateIssueDtl(EstimateCertificateIssueVO estimateCertificateIssueVO,UserVO UserVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return (delegate.getEstimateCertificateIssueDtl(estimateCertificateIssueVO,UserVO));
	}
	public static void saveEstimateCertificateIssueDtl(EstimateCertificateIssueVO estimateCertificateIssueVO,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		delegate.saveEstimateCertificateIssueDtl(estimateCertificateIssueVO,userVO);
	}
	public static Map generateEstimateCertificate(EstimateCertificateIssueVO estimateCertificateIssueVO,UserVO UserVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return (delegate.generateEstimateCertificate(estimateCertificateIssueVO,UserVO));
	}

}
