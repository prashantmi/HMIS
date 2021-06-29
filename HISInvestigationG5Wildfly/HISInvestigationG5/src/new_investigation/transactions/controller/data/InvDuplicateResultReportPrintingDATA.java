package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.InvDuplicateResultReportPrintingDelegate;
import new_investigation.transactions.delegate.InvResultReportPrintingDelegate;
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
import new_investigation.vo.InvResultReportPrintingVO;



public class InvDuplicateResultReportPrintingDATA
{
	 
	
	public static Map LabComboForDuplicateResultReportPrinting(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvDuplicateResultReportPrintingDelegate masterDelegate = new InvDuplicateResultReportPrintingDelegate();
		return masterDelegate.LabComboForDuplicateResultReportPrinting(invresultreportprintingvo, _UserVO);
	}
	 
	public static Map setDuplicateResultReportPrintingEssentials(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvDuplicateResultReportPrintingDelegate masterDelegate = new InvDuplicateResultReportPrintingDelegate();
		return masterDelegate.setDuplicateResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);
	}
	
	
	
	public static Map duplicatePdfDetails(List<InvDuplicateResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvDuplicateResultReportPrintingDelegate masterDelegate = new InvDuplicateResultReportPrintingDelegate();
		return masterDelegate.duplicatePdfDetails(invresultreportprintingvo, _UserVO);
	}
	
	
	public static Map setDuplicateResultReportPrintingEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvDuplicateResultReportPrintingDelegate masterDelegate = new InvDuplicateResultReportPrintingDelegate();
		return masterDelegate.setDuplicateResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);
	}
	
	
	/*
	//ResultEntry Save Logic
		public static Map saveResultEntryDetails(List<InvResultEntryVO> voResultEntry,List<InvResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.saveResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request);
		}*/
	
	 
	
	
	
	
	
	
	 
	
	 
} 
