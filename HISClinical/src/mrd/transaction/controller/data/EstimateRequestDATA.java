package mrd.transaction.controller.data;

import opd.bo.delegate.OpdEssentialDelegate;

import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import mrd.transaction.bo.MrdEssentialBO;
import mrd.vo.EstimateCertificateReqVO;
import hisglobal.vo.UserVO;

public class EstimateRequestDATA extends ControllerDATA {

	public static Map getEstimateRequestEssentials(EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();
		return (serviceBO.getEstimateRequestEssentials(estReqDtlVO, strUserVO));
	}
	

	public static void saveEstimateCertificateReqDtl(EstimateCertificateReqVO estReqVO,UserVO userVO)
		{
		MrdEssentialBO serviceBO=new MrdEssentialBO();
		serviceBO.saveEstimateCertificateReqDtl(estReqVO,userVO);
		}

	
}
