package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.reports.delegate.InvTrackingReportDelegate;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.delegate.InvestigationEssentialDelegate;
import new_investigation.vo.InvTrackingReportVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;


public class SampleCollectionDATA
{
	public static List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getSampleCollectionArea(userVO);
	}
	
	public static List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO, UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getPatList(objSampleCollectionVO,userVO);
	}
	
	
	/*
	 * public static Map searchLabWiseTestDetails(InvestigationSearchVO
	 * searchVO,UserVO userVO) { InvestigationEssentialDelegate daoDelegate=new
	 * InvestigationEssentialDelegate(); return
	 * daoDelegate.searchLabWiseTestDetails(searchVO,userVO); }
	 * 
	 * 
	 */
	
	
	//Sample Collection Save Logic
	public static List saveSampleCollectionDetails(Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp,UserVO _userVO)
	{
		InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.saveSampleCollectionDetails(mp,_userVO);
	}
	 
		public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPatientEpisodeDetail(patVO,_userVO);
		}
		
		 
		public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPatientAdmissionDetail(patVO,_userVO);
		}
		
		public static Map getBilledPatList(List<String> reqList,Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getBilledPatList(reqList,voSample,userVO);
		}
		
		public static List<String> getStaffDetails(String crNo, UserVO _UserVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getStaffDetails(crNo, _UserVO);
		}
		
		public static boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.checkSampleNoDuplicacy(voSample, userVO);
		}
	
		
		public static String checkAutoGenFormate(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.checkAutoGenFormate(voSample, userVO);
		}
		
		public static List<Inv_SampleCollectionVO> getPatListBarcode(Inv_SampleCollectionVO objSampleCollectionVO, UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPatListBarcode(objSampleCollectionVO,userVO);
		}
		//added by krishnan nema on 25042019
		public static List<Inv_SampleCollectionVO> getPatListSampleColAdvance(Inv_SampleCollectionVO objSampleCollectionVO, UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPatListSampleColAdvance(objSampleCollectionVO,userVO);
		}
		
		public static String getsamplebarcodeconfig(UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getsamplebarcodeconfig( userVO);
		}
		
		//AjaxBilledUnbilledDetails
		
		public static String AjaxBilledUnbilledDetails(Inv_SampleCollectionVO vo, UserVO userVO) {
			InvestigationEssentialDelegate reportDelegate = new InvestigationEssentialDelegate();
			return reportDelegate.AjaxBilledUnbilledDetails(vo, userVO);
		}
		//AjaxGetDetails
		public static String AjaxGetDetails(Inv_SampleCollectionVO vo, UserVO userVO) {
			InvestigationEssentialDelegate reportDelegate = new InvestigationEssentialDelegate();
			return reportDelegate.AjaxGetDetails(vo, userVO);
		}
		
}
