package opd.transaction.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.UserVO;

public class ExternalInvestigationDATA extends ControllerDATA
{
	public static List getParameterForExtInv(UserVO userVO)
	{
		OpdEssentialDelegate  opdEssentialDelegate=new OpdEssentialDelegate();
		return opdEssentialDelegate.getParameterForExtInv(userVO);
	}
	
	public static void saveExtInvestigationDtl(List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO,UserVO userVO)
	{
		OpdPatientDelegate opdPatDelegate=new OpdPatientDelegate();
		opdPatDelegate.saveExtInvestigationDtl(lstEpiExtInvDtlVO,userVO);
	}
	
	public static EpisodeExtInvDtlVO[] getAddedExternalInvDetail(String patCrNo,UserVO userVO)
	{
		OpdPatientDelegate opdPatDelegate=new OpdPatientDelegate();
		return opdPatDelegate.getAddedExternalInvDetail(patCrNo,userVO);
	}
}
