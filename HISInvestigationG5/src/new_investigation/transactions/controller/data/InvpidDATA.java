package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.fb.InvpidFB;
import new_investigation.transactions.delegate.InvpidDelegate;
import new_investigation.vo.InvResultReportPrintingVO;



public class InvpidDATA
{
	 
	
	public static Map LabComboForResultReportPrinting(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvpidDelegate masterDelegate = new InvpidDelegate();
		return masterDelegate.LabComboForResultReportPrinting(invresultreportprintingvo, _UserVO);
	}
	 
	public static Map setResultReportPrintingEssentials(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvpidDelegate masterDelegate = new InvpidDelegate();
		return masterDelegate.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);
	}
	
	
	
	public static Map PdfDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvpidDelegate masterDelegate = new InvpidDelegate();
		return masterDelegate.PdfDetails(invresultreportprintingvo, _UserVO);
	}
	
	
	public static Map setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO,HttpServletRequest request)
	{
		InvpidDelegate masterDelegate = new InvpidDelegate();
		return masterDelegate.setResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO,request);
	}
	
	
	public static String saveResultReportPrintingDetails(InvpidFB fb, UserVO _UserVO,HttpServletRequest req)
	{
		InvpidDelegate masterDelegate = new InvpidDelegate();
		return masterDelegate.saveResultReportPrintingDetails(fb, _UserVO,req);
	}

	public static String isfromFTPorMONGO( UserVO _UserVO)
	{
		InvpidDelegate masterDelegate = new InvpidDelegate();
		return masterDelegate.isfromFTPorMONGO( _UserVO);
	}
	
	
	/*
	//ResultEntry Save Logic
		public static Map saveResultEntryDetails(List<InvResultEntryVO> voResultEntry,List<InvResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request)
		{
			InvResultEntryDelegate masterDelegate = new InvResultEntryDelegate();
			return masterDelegate.saveResultEntryDetails(voResultEntry,invResultEntryForParameterDtlVO,  _userVO,  _request);
		}*/
	
	 
	

	public static String setResultReportPrintingEssentialsOnLoaddata(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvpidDelegate masterDelegate = new InvpidDelegate();
		return masterDelegate.setResultReportPrintingEssentialsOnLoaddata(invresultreportprintingvo, _UserVO);
	}
	
	
	
	
	
	
	 
	
	 
} 
