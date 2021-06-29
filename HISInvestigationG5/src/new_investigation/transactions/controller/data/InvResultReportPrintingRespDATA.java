package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.InvResultReportPrintingRespDelegate;
import new_investigation.vo.InvResultReportPrintingRespVO;



public class InvResultReportPrintingRespDATA
{
	 
	
	public static Map LabComboForResultReportPrinting(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespDelegate masterDelegate = new InvResultReportPrintingRespDelegate();
		return masterDelegate.LabComboForResultReportPrintingResp(invresultreportprintingvo, _UserVO);
	}
	 
	public static Map setResultReportPrintingEssentials(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespDelegate masterDelegate = new InvResultReportPrintingRespDelegate();
		return masterDelegate.setResultReportPrintingRespEssentials(invresultreportprintingvo, _UserVO);
	}
	
	
	
	public static Map PdfDetails(List<InvResultReportPrintingRespVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespDelegate masterDelegate = new InvResultReportPrintingRespDelegate();
		return masterDelegate.PdfDetails(invresultreportprintingvo, _UserVO);
	}
	
	
	public static Map setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespDelegate masterDelegate = new InvResultReportPrintingRespDelegate();
		return masterDelegate.setResultReportPrintingRespEssentialsOnLoad(invresultreportprintingvo, _UserVO);
	}
	
	
	public static Map saveResultReportPrintingDetails(List<InvResultReportPrintingRespVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespDelegate masterDelegate = new InvResultReportPrintingRespDelegate();
		return masterDelegate.saveResultReportPrintingRespDetails(invresultreportprintingvo, _UserVO);
	}

	public static String isfromFTPorMONGO( UserVO _UserVO)
	{
		InvResultReportPrintingRespDelegate masterDelegate = new InvResultReportPrintingRespDelegate();
		return masterDelegate.isfromFTPorMONGO( _UserVO);
	}
	
	
	/*
	//ResultEntry Save Logic
		public static Map saveResultEntryDetails(List<InvResultEntryVO> voResultEntry,List<InvResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.saveResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request);
		}*/
	
	 
	
	
	
	
	
	
	 
	
	 
} 
