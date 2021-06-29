package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.InvResultReportPrintingDelegate;
import new_investigation.transactions.delegate.invReportInProcessDelegate;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.invReportInProcessVO;

public class invReportInProcessDATA {

	
	
	public static Map setResultReportPrintingEssentialsOnLoad( UserVO _UserVO)
	{
		invReportInProcessDelegate masterDelegate = new invReportInProcessDelegate();
		return masterDelegate.setResultReportPrintingEssentialsOnLoad( _UserVO);
	}
	
	
	public static Map setResultReportPrintingEssentials(invReportInProcessVO invresultreportprintingvo, UserVO _UserVO)
	{
		invReportInProcessDelegate masterDelegate = new invReportInProcessDelegate();
		return masterDelegate.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);
	}
	
	public static void saveInJobWorkOrder(List<invReportInProcessVO> vo, UserVO _UserVO)
	{
		invReportInProcessDelegate masterDelegate = new invReportInProcessDelegate();
		 masterDelegate.saveInJobWorkOrder(vo, _UserVO);
	}
	
	
	public static List<invReportInProcessVO> getdno(invReportInProcessVO vo, UserVO _UserVO)
	{
		invReportInProcessDelegate masterDelegate = new invReportInProcessDelegate();
		return masterDelegate.getdno(vo, _UserVO);
	}
	
}
