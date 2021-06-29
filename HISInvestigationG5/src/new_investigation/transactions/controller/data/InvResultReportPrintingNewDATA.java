package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.InvResultReportPrintingDelegate;
import new_investigation.transactions.delegate.InvResultReportPrintingNewDelegate;
import new_investigation.vo.InvResultReportPrintingVO;



public class InvResultReportPrintingNewDATA
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
	
	
	public static String setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO,HttpServletRequest request)
	{
		InvResultReportPrintingNewDelegate masterDelegate = new InvResultReportPrintingNewDelegate();
		return masterDelegate.setResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO,request);
	}
	
	
	public static String getdatacrnowise(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO,HttpServletRequest request)
	{
		InvResultReportPrintingNewDelegate masterDelegate = new InvResultReportPrintingNewDelegate();
		return masterDelegate.getdatacrnowise(invresultreportprintingvo, _UserVO,request);
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
	
	 
	
	
	
	
	
	
	 
	
	 
} 
