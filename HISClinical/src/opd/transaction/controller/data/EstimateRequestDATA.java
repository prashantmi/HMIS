/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: Amit Garg 	
	 ## Module Name					: MRD
	 ## Process/Database Object Name:Estimate Certificate  Request
	 ## Purpose						:Certificate Issue Request
	 ## Date of Creation			: 19-Nov-2014
	
 	 */


package opd.transaction.controller.data;

import opd.bo.delegate.OpdEssentialDelegate;

import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EstimateCertificateReqVO;
import hisglobal.vo.UserVO;

public class EstimateRequestDATA extends ControllerDATA {

	public static Map getEstimateRequestEssentials(EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getEstimateRequestEssentials(estReqDtlVO,  strUserVO);
	}
	
	
	public static Map getTariffsList(EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getTariffsList(estReqDtlVO,  strUserVO);
	}
	
	

	public static void saveEstimateCertificateReqDtl(EstimateCertificateReqVO estReqVO,UserVO userVO)
		{
		   OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		   opdEssDelegate.saveEstimateCertificateReqDtl(estReqVO,userVO);
		}

	
}
