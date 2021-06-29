package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.InvResultReportPrintingDelegate;
import new_investigation.transactions.delegate.InvResultReportPrintingDelegateResp;
import new_investigation.vo.InvResultReportPrintingVO;



public class InvResultReportPrintingDATA
{
	 
	
	public static Map LabComboForResultReportPrinting(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingDelegate masterDelegate = new InvResultReportPrintingDelegate();
		return masterDelegate.LabComboForResultReportPrinting(invresultreportprintingvo, _UserVO);
	}
	 
	public static Map setResultReportPrintingEssentials(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingDelegate masterDelegate = new InvResultReportPrintingDelegate();
		return masterDelegate.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);
	}
	
	
	
	public static Map PdfDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingDelegate masterDelegate = new InvResultReportPrintingDelegate();
		return masterDelegate.PdfDetails(invresultreportprintingvo, _UserVO);
	}
	
	
	public static Map setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingDelegate masterDelegate = new InvResultReportPrintingDelegate();
		return masterDelegate.setResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);
	}
	
	
	public static Map saveResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingDelegate masterDelegate = new InvResultReportPrintingDelegate();
		return masterDelegate.saveResultReportPrintingDetails(invresultreportprintingvo, _UserVO);
	}

	public static String isfromFTPorMONGO( UserVO _UserVO)
	{
		InvResultReportPrintingDelegate masterDelegate = new InvResultReportPrintingDelegate();
		return masterDelegate.isfromFTPorMONGO( _UserVO);
	}
	
	
	/*
	//ResultEntry Save Logic
		public static Map saveResultEntryDetails(List<InvResultEntryVO> voResultEntry,List<InvResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.saveResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request);
		}*/
	
	 
	
	public static Map setResultReportPrintingEssentialsnew(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingDelegateResp masterDelegate = new InvResultReportPrintingDelegateResp();
		return masterDelegate.setResultReportPrintingEssentialsnew(invresultreportprintingvo, _UserVO);
	}
	public static String UPDATEREPORTS(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingDelegateResp masterDelegate = new InvResultReportPrintingDelegateResp();
		return masterDelegate.UPDATEREPORTS(invresultreportprintingvo, _UserVO);
	}
	
	
	
	 
	
	 
} 
