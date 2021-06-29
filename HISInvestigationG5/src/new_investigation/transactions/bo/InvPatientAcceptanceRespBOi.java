package new_investigation.transactions.bo;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvPatientAcceptanceRespFB;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.SampleCollectionCumAcceptanceVO;
import new_investigation.vo.externalInvestigationCaptureVO;
import new_investigation.vo.filmUsedVO;
import new_investigation.vo.invAntibioticProcessVO;
import new_investigation.vo.invFungusProcessVO;
import new_investigation.vo.invReportAddendumVO;
import new_investigation.vo.invReportInProcessVO;
import new_investigation.vo.machineEnquiryVO;
import new_investigation.vo.machineResultEntryVO;
import new_investigation.vo.reportDownloadProcessVO;
import new_investigation.vo.testAvailabilityVO;
import new_investigation.vo.viewExternalInvVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TestWiseConsumableVO;
import new_investigation.vo.template.invStatusDashboardVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;

public interface InvPatientAcceptanceRespBOi {
	
	
	public Map AjaxGetPatAcceptanceReqnList(InvPatientAcceptanceRespVO invPatientAcceptanceRespVO, UserVO _UserVO);
	
	public  JsonObject savePatientDetails(Map<String,Map<String,Map<String,List<InvPatientAcceptanceRespVO>>>> mp,UserVO _userVO);
	
	public Map  LabCombos(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO);
	
	public Map setPatientEssentialsOnLoad(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO);
	
	public Map setPatientEssentials(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO);
	
	public Map patientDetails(List<InvPatientAcceptanceRespVO> onlinePatientvo,List<String> reqList, UserVO _UserVO);
	
	public boolean checkDailyLabNoDuplicacy(InvPatientAcceptanceRespVO onlinePatientvo,  UserVO _UserVO);
	
	public String checkAutoGenFormate(InvPatientAcceptanceRespVO onlinePatientvo,  UserVO _UserVO);
	
	public Map MachineCombos(InvPatientAcceptanceRespVO onlinePatientvo, UserVO userVO);
	
	public Map getUserList( UserVO _UserVO );
}





 