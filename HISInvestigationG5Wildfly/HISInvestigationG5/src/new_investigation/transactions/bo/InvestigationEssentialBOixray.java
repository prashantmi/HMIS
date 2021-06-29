package new_investigation.transactions.bo;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
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
import new_investigation.vo.OnlinePatientAcceptanceVO;
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


public interface InvestigationEssentialBOixray {
	
	//Investigation Requisition Raising Process
	public Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO);
		
	public Map searchLabWiseTestDtls(InvestigationSearchVO patVO, UserVO _UserVO);
	
	public Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO patVO, UserVO _UserVO);//searchLaboratoryWiseTestGroupOnClickRaisingCumCollection
	
	public  List saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO,String priorityAll,HttpSession session);
	public  List<Inv_EpisodeVO> getPatientEpisodeDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO);
	public  List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO);
	
	public Map<String,Map<String,List<String>>>  getBookMarkDetails(UserVO userVO);
	
	public Map searchBookMark(InvestigationSearchVO patVO, UserVO _UserVO);

	//Sample Colletion Process 
	public List<Inv_SampleCollectionVO> getSampleCollectionArea( UserVO _UserVO);
	
	public List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO, UserVO _UserVO);
	
	public Map getBilledPatientList(List<String> reqList,Inv_SampleCollectionVO voSample, UserVO _UserVO);
	 
	public List<String> getStaffDetails(String crNo, UserVO _UserVO);
	
	//save logic::sample Collection
	public  List saveSampleCollectionDetails(Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp,UserVO _userVO);
	
	public boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO voSample, UserVO _UserVO);
	
	public String checkAutoGenFormate(Inv_SampleCollectionVO voSample, UserVO _UserVO);
	
	
	//Packing List Generation Process getPackingListGenerationOnLoad
    public Map getPackingListGenerationEssentials(Inv_SampleCollectionVO voSample, UserVO _UserVO);
    
    public Map getPackingListGenerationOnLoad(Inv_SampleCollectionVO voSample, UserVO _UserVO);
    
    public List<Inv_SampleCollectionVO> getPackingListPatList(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _UserVO); 
    
    public  Map<String,List<Inv_SampleCollectionVO>> savePackingListDetails(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO);
    
    public  Map<String,List<Inv_SampleCollectionVO>> generateDuplicatePackingList(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO);
    

public Map patientDetails(List<OnlinePatientAcceptanceVO> onlinePatientvo,List<String> reqList, UserVO _UserVO);

	 
	public boolean checkDailyLabNoDuplicacy(OnlinePatientAcceptanceVO onlinePatientvo,  UserVO _UserVO);
	
	public String checkAutoGenFormate(OnlinePatientAcceptanceVO onlinePatientvo,  UserVO _UserVO);
	
	//Start Sample Acceptance Added By Pathan Basha
	
	public Map  sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
	
	public Map getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
	
	
	
	
	
	public Map getSampleAcceptanceDetailOnLoad(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
	
	public Map SampleAcceptanceRejectionCombo(SampleAcceptanceVO sampleAcceptanceVO,List<String> reqList, UserVO _UserVO);

	public boolean checkDailyLabNoDuplicacyforSampleAcc(SampleAcceptanceVO sampleAcceptanceVO,  UserVO _UserVO);

	public  Map saveSampleAccDetails(List<SampleAcceptanceVO>  voSampleAcc , UserVO _userVO,HttpServletRequest _request);
	
	public  Map rejectSampleAccDetails(List<SampleAcceptanceVO>  voSampleAcc , UserVO _userVO,HttpServletRequest _request);

	public  Map getLabTestMachine(String LabTestMachine , UserVO _userVO);

	public  List savePatientDetails(Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp,UserVO _userVO);
	public Map  LabCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO);
	
	public Map setPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO);
	
	public Map setPatientEssentialsOnLoad(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO);
 
	public String checkAutoGenFormateSampleAcceptance(SampleAcceptanceVO voSample, UserVO _UserVO);
	//START A RESULT ENTRY PROCESS ADDED BY BASHA ON 21-04-2015 
    public Map  getRequisitionRaisingEssentials(UserVO userVO);
    
    public String  getTestComboAJAX(String labCode,UserVO userVO); 
    
    public Map  getTestComboAJAXMAP(String labCode,UserVO userVO); 
    
    
    public Map  getPrvTestDtlAJAXMAP(String CrNo,UserVO userVO); 
    
    public Map searchTestGroup(InvestigationSearchVO searchVO, UserVO _UserVO);
	
    public String  getTestGroupAJAX(String labCode,UserVO userVO); 
    
    public String  getStringToAddToRowAJAX(String isTestGroup,LabTestVO voLabTest);
    
    //getreqStatusAJAX
    public String  getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO _UserVO); 
    public Map getTestsBasedOnGroup(InvestigationSearchVO searchVO, UserVO _UserVO);
   
    
    
    public Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO, UserVO _UserVO);
    
    public Map deleteReqDtl(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);
	
    public int checkBillDtl(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);
    
	public Map  LabComboForResultEntry(ResultEntryVO invresultentryvo, UserVO _UserVO);
	
	
	public Map  showCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO);
	
	public Map setPatientResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO);
	 
//	public Map ResultEntryPatientDetails(InvResultEntryVO invresultentryvo,List<String> reqList, UserVO _UserVO);

	public  Map saveResultEntryDetails(List<ResultEntryVO>  invresultentryvo ,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultEntryFB fb);

	public Map getAptBasedTest(InvestigationSearchVO patVO, UserVO _UserVO);
	public String getAccountNo(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO);
	public boolean checkRequisitionPending(InvestigationSearchVO patVO, UserVO _UserVO);
	/*//START A RESULT VALIDATION PROCESS ADDED BY BASHA
   
	public Map  getRequisitionRaisingEssentials(UserVO userVO);
    
    public String  getTestComboAJAX(String labCode,UserVO userVO); 
    
    public Map  getTestComboAJAXMAP(String labCode,UserVO userVO); 
    
    public Map searchTestGroup(InvestigationSearchVO searchVO, UserVO _UserVO);
	
    public String  getTestGroupAJAX(String labCode,UserVO userVO); 
    
    public Map getTestsBasedOnGroup(InvestigationSearchVO searchVO, UserVO _UserVO);*/
   
    public Map  LabComboForResultValidation(ResultEntryVO invresultvalidationvo, UserVO _UserVO);
	
	public Map setPatientResultValidationEssentials(ResultEntryVO invresultvalidationvo, UserVO _UserVO);
	 
	public Map getResultEntryData(ResultEntryVO invresultvalidationvo);

	//public Map getHyperLinkData(ResultEntryVO invresultvalidationvo);
	
//	public Map ResultValidationPatientDetails(InvResultValidationVO invresultvalidationvo,List<String> reqList, UserVO _UserVO);

	public  Map saveResultValidationDetails(List<ResultEntryVO>  invresultvalidationvo ,List<ResultEntryVO>  invResultValidationForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationFB fb);

	public void revalidate(List<ResultEntryVO> invresultvalidationvo, UserVO _UserVO);

	public String  checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO);
	
	public String  checkcannedCodeName1(InvResultValidationFB fb, UserVO _UserVO);
	// Sample Collection Cum Acceptance Process
	
	public boolean checkSampleNoDuplicacy(SampleCollectionCumAcceptanceVO voSample, UserVO _UserVO);
	
	public String checkAutoGenFormateSampleCollCum(SampleCollectionCumAcceptanceVO voSample, UserVO _UserVO);
	
	
	 
	public Map getBilledPatientList(List<String> reqList,SampleCollectionCumAcceptanceVO voSample, UserVO _UserVO);
	public List  saveSampleCollectionDetail(Map<String,Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>>> mp,UserVO _userVO);
	public List<SampleCollectionCumAcceptanceVO> getPatList(SampleCollectionCumAcceptanceVO objSampleCollectionVO, UserVO _UserVO);
	public List<SampleCollectionCumAcceptanceVO> getSampleCollectionAreas( UserVO _UserVO);
 
	//START A RESULT REPORT PRINTING ADDED BY PATHAN BASHA ON 25-05-2015
	
	
	public Map  LabComboForResultReportPrinting(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO);
	
	
	public Map setResultReportPrintingEssentials(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO);
	
	public Map setResultReportPrintingEssentials(invReportInProcessVO invresultreportprintingvo, UserVO _UserVO);
	
	public Map pdfDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO);
	
	public Map saveResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO);
	
 
	public Map setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO);
	
	
	
	//re validation 
	
    public Map  LabComboForResultReValidation(ResultEntryVO invresultvalidationvo, UserVO _UserVO);
    
	public Map setPatientResultReValidationEssentials(ResultEntryVO invresultvalidationvo, UserVO _UserVO);
	
	public  Map saveResultReValidationDetails(List<ResultEntryVO>  invresultvalidationvo ,List<ResultEntryVO>  invResultValidationForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationFB fb);

	//public Map getResultValidationData(ResultEntryVO invresultvalidationvo);

	public void revalidateDirectly(List<ResultEntryVO> invresultvalidationvo, UserVO _UserVO);

	//loinc code for entry valida and revalida
	
	public String getLoincCode(String passValue);
	
	//fetch list for para and loinc
	
/*	public void fetchLoincCode(InvResultEntryVO invresultvalidationvo, UserVO _UserVO);
*/	
 
	
	//START A DUPLICATE REPORT PRINTING BY PATHAN BASHA 08-06-2015
	public Map  LabComboForDuplicateResultReportPrinting(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO);
	public Map setDuplicateResultReportPrintingEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO);
	
public Map setDuplicateResultReportPrintingEssentials(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO);
	
	
	public Map duplicatePdfDetails(List<InvDuplicateResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO);
	
	
	//Sample Acceptance Through BarCode
	
	public Map getSampleAcceptanceDetailBarCode(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
	
	public Map getSampleAcceptanceDetailBarCode1(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
	
	//modification result entry process
	public  Map modifyResultEntryDetails(List<ResultEntryVO>  invresultentryvo ,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultEntryFB _fb);

	public Map  autoCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO);

	public String  checkcannedCodeName(InvResultEntryFB fb, UserVO _UserVO);
	
	//inv raising appointment by desk
	public Map getAptByDesk(InvestigationSearchVO patVO, UserVO _UserVO);
	public Map getAppointment(String reqNo,String crNo, UserVO _UserVO);
	public  List saveAppointmentDetails(List<LabTestVO> mp,LabTestVO patVO,UserVO _userVO);
	public  Map getAppointmentDetailsOnClickGO(LabTestVO voLabTest,UserVO _userVO);

	
	
	
	//machine result entry process
	public Map  LabComboForMachineResultEntry(machineResultEntryVO resultentryvo, UserVO _UserVO);
	public Map getPatientMachineResultEntry(machineResultEntryVO invresultentryvo, UserVO _UserVO);
	public  Map saveMachineResultEntry(Map<String,List<machineResultEntryVO>>  mp_resultEntry , UserVO _userVO,HttpServletRequest _request);
	public Map  getLabBasedMachine(machineResultEntryVO resultentryvo, UserVO _UserVO);

	
	//machine result entry process
	public Map  getMachineComboForEnquiry(machineEnquiryVO resultentryvo, UserVO _UserVO);
	public Map getPatientmachineEnquiry(machineEnquiryVO invresultentryvo, UserVO _UserVO);
	public Map  getMachineBasedSampleNo(machineEnquiryVO resultentryvo, UserVO _UserVO);

	//external investigation capture
	public Map external_searchLabWiseTestDtls(InvestigationSearchVO patVO, UserVO _UserVO);
	public  List external_saveRequisitionDetails(List<externalInvestigationCaptureVO> lstExternalCapture,UserVO _userVO);

	//get packing list details for duplicate generation of pak list
	public Map getPackingListDetails(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);

	//for test avail
	public Map  LabComboForTestAvailability(testAvailabilityVO resultentryvo, UserVO _UserVO);
	public Map  getTestDetails(testAvailabilityVO invresultentryvo, UserVO _UserVO);
	public  Map updateTestDetails(List<testAvailabilityVO>  mp_resultEntry , UserVO _userVO,HttpServletRequest _request);

	//for film used
	public  List saveFilmDetails(List<filmUsedVO> lstFilm,List<filmUsedVO> lstFilmAdd,List<filmUsedVO> lstFilmWaste,filmUsedVO filmvo,UserVO _userVO,Map mp);
	public Map  LabCombos(filmUsedVO onlinePatientvo, UserVO _UserVO);
	public Map setPatientEssentials(filmUsedVO onlinePatientvo, UserVO _UserVO);
	public Map setPatientEssentialsOnLoad(filmUsedVO onlinePatientvo, UserVO _UserVO);
	public Map patientDetails(filmUsedVO onlinePatientvo,List<String> reqList, UserVO _UserVO);

   

	//raising cum sample collection
	public List saveRequisitionCollectionDetails(Map<String,Map<String,List<LabTestVO>>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO);
	public List insertRaisingCumSelectionInReqDtl(Map<String,Map<String,List<LabTestVO>>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO);
	public Map searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO patVO, UserVO _UserVO);
	public Map searchBookMarkRaisingCumCollection(InvestigationSearchVO patVO, UserVO _UserVO);
	public Map searchLaboratoryWiseTestGroupOnClickRaisingCumCollection(InvestigationSearchVO patVO, UserVO _UserVO);
	public Map getAptByDeskRaiseCumColl(InvestigationSearchVO patVO, UserVO _UserVO);
	public Map getAppointmentRaiseCumColl(String reqNo,String crNo, UserVO _UserVO);
	public List saveAppointmentDetailsRaiseCumColl(List<LabTestVO> mp,LabTestVO patVO,UserVO _userVO);
	public Map getAppointmentDetailsOnClickGORaiseCumColl(LabTestVO voLabTest,UserVO _userVO);
	
	
	//for Inv Audit Process
		public Map  LabComboForAudit(InvValueAuditFB invInvValueAuditFB, UserVO _UserVO);
		public Map  AllTestComboForAudit(InvValueAuditVO vo, UserVO _UserVO);
		public Map  TestComboForAudit(InvValueAuditVO vo, UserVO _UserVO);
		public Map  getlistauditprocess(InvValueAuditVO vo, UserVO _UserVO);
		public Map  showPatDetails(InvValueAuditVO vo, UserVO _UserVO,String dNo,String testCode,String labCode);
		
		
	//for view external inv process
		public Map  showPatDetails(viewExternalInvVO vo, UserVO _UserVO,String reqNos);
  //to get tests based on group code
	    public Map getGroupCodeWiseDtl(InvestigationSearchVO searchVO, UserVO _UserVO);
	    public Map getGroupCodeWiseDtlCumColl(InvestigationSearchVO searchVO, UserVO _UserVO);
	    public Map getTestsCodeWiseDtlCumColl(InvestigationSearchVO searchVO, UserVO _UserVO);

	    public Map  LabComboForTestWiseConsumable(TestWiseConsumableVO invresultentryvo, UserVO _UserVO);
	    
	    public Map setPatientTestWiseConsumableEssentials(TestWiseConsumableVO invresultentryvo, UserVO _UserVO);
	    
	    public Map getTestWiseConsumableList(TestWiseConsumableVO invresultentryvo, UserVO _UserVO);
	    
	    public Map saveConsumableList(TestWiseConsumableVO invresultentryvo, UserVO _UserVO);
	    
	    public Map getPatientDetailsTestWiseConsumable(TestWiseConsumableVO invresultentryvo, UserVO _UserVO);
	    
	    public Map updateTestWiseConsumableList(TestWiseConsumableVO invresultentryvo, UserVO _UserVO);
	    
//  for inv antibiotic process
	    public Map  LabComboForAudit(invAntibioticProcessFB invinvAntibioticProcessFB, UserVO _UserVO);
	    public Map  getAntibioticName(invAntibioticProcessVO vo, UserVO _UserVO);
	    public String  getxml(invAntibioticProcessVO vo, UserVO _UserVO);
	    

	   //report addendum process
		public Map setPatientReportAddendumEssentials(ResultEntryVO invresultvalidationvo, UserVO _UserVO);
		public ResultEntryVO getNewEntriesPatient(ResultEntryVO invresultvalidationvo, UserVO _UserVO);
		public ResultEntryVO getOldEntriesPatient(ResultEntryVO invresultvalidationvo, UserVO _UserVO);
		public  Map saveReportAddendumDetails(ResultEntryVO newPatVO,List<ResultEntryVO> oldPatList,List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,String amendType);
		public Map reasonList( UserVO _UserVO);	


		

		
		//depat result entry
	//	public Map  LabComboForResultEntry(ResultEntryVO invresultentryvo, UserVO _UserVO);
		public Map setPatientDeptResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO);
		public  Map saveDeptResultEntryDetails(List<ResultEntryVO>  invresultentryvo ,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request);
		public  Map modifyDeptResultEntryDetails(List<ResultEntryVO>  invresultentryvo ,List<ResultEntryVO>  invResultEntryForParameterDtlVO, UserVO _userVO,HttpServletRequest _request);
		public Map getDeptResultEntryData(ResultEntryVO invresultvalidationvo);

//  inprocess report new form
		public Map setResultReportPrintingEssentialsOnLoad( UserVO _UserVO);
		
		public void saveInJobWorkOrder(List<invReportInProcessVO> vo, UserVO _UserVO);
		
		public Map  LabCombos(SampleAcceptanceVO onlinePatientvo, UserVO _UserVO);
		
		public List<Inv_SampleCollectionVO> getPatListBarcode(Inv_SampleCollectionVO objSampleCollectionVO, UserVO _UserVO);

		
		
		// report history fetch
		
		public Map  fetchActiveReportData(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO);
		public Map  fetchActiveReportData1(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO);
		public Map  fetchActiveReportDataall(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO);
		public Map  fetchActiveReportDataallInactive(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO);
		
		
		
		//addendum
		public  Map saveAddendumDetails(invReportAddendumVO newPatVO,UserVO _userVO);

      // result entry check billing for addendum test
		public String  checkBilling(InvResultEntryFB fb, UserVO _UserVO);

      //raising addendum labcodes fetch   
		public String  getlabcodesaddendum(String reqno,UserVO userVO);

		public Map fetchMachime( UserVO _UserVO);
		
		// report download
				public String fetchMobileNo(reportDownloadProcessFB fb);
				public String getBillingCheck(UserVO userVO);
				public String fetchusername(reportDownloadProcessFB fb);
				public Map fetchlist(reportDownloadProcessVO vo);
				public Map Pfetchlist(reportDownloadProcessVO vo);
				public Map savePublicResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo);
				public String validatecrno(reportDownloadProcessFB fb);
				public Map loginInsertDetails(InvResultReportPrintingVO invresultreportprintingvo);
				
				public String getInstruction(UserVO userVO,ResultEntryVO invresultentryv);

				public  String getRequisitionFormMasterData(String patVO,UserVO _userVO);

				public  Map saveAddendumDetails(InvResultEntryVO newPatVO,UserVO _userVO);
                
			    public Map  getPrvTestDtlAJAXMAPP(String CrNo,UserVO userVO); 

			    public Map deleteReqDtll(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);

			    public List machinelist( UserVO _UserVO);

				public Map searchLabWiseTestDtlsNEW(InvestigationSearchVO patVO, UserVO _UserVO);
				
				//added by krishnan nema on 01/10/2018
				public Map  LabComboForResultValidationAddendum(ResultEntryVO invresultvalidationvo, UserVO _UserVO);

				  public abstract String CHECKCISPARAMETERDEPENDENTRF(String paramString, UserVO paramUserVO,InvResultEntryFB fbb);

				 public abstract String getBillingChecktestcode(String paramString1, String paramString2, String paramString3, String paramString4, UserVO paramUserVO);
				  
				  public abstract String getcollectionareafromward(String paramString1, String paramString2);

				  //added by krishnan nema on 08/10/2018
				public Map MachineCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO userVO);

			    public String issufficientbalance(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);
			  //added by krishnan nema on 26/10/2018
				public Map modifySampleAccDetails(List<SampleAcceptanceVO> voSampleAcceptance, UserVO _userVO, HttpServletRequest _request);


			    public String isduplicatetestraisedtoday(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);
			    public String getgrpcode(String searchVO, UserVO _UserVO);

				public Inv_ictc_VO getictcdetails(Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO);
			    
			    public String ispidexist(InvestigationSearchVO searchVO, UserVO _UserVO);

			    public Map getStatusDashboardRecord(invStatusDashboardVO invstatusdashboardvo,UserVO userVO);

			    public Map getRequestedSampleListDashboard(String recordRequested,UserVO userVO); 
			    
			    public String AJX_IS_LAB_MANDTORY(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);

			    public String updateappointmentdateinheader(LabTestVO voLabTest, UserVO _UserVO);


				public Map<String,Map<String,List<String>>>  getBookMarkListraising(UserVO userVO,String iscallingfromdesk);
				

				public  String getfileuploaddatatestwise(String patVO,UserVO _userVO,String testParaMeterCode);

				
				//fungus
				public Map  getAntibioticName(invFungusProcessVO vo, UserVO _UserVO);
				public Map  LabComboForAudit(invFungusProcessFB invInvValueAuditFB, UserVO _UserVO);
			    public String  getxml(invFungusProcessVO vo, UserVO _UserVO);

				public String  isfromFTPorMONGO(UserVO _UserVO);

				//added by krishnan nema on 25042019
			    public List<Inv_SampleCollectionVO> getPatListSampleColAdvance(Inv_SampleCollectionVO objSampleCollectionVO, UserVO _UserVO);
			    
				public  String getechodata(String reqdno,UserVO _userVO);

			    public Map  getessentialdetailsforxray(UserVO userVO,Inv_RequisitionRaisingPatientVO patVO,List<Inv_PatientAdmissionDtlVO> patadm);

				public  List savexrayRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO,String priorityAll,HttpSession session);

				public Map<String,Map<String,List<String>>>  getBookMarkListraisingxray(UserVO userVO,String iscallingfromdesk);

			    public String issufficientbalancexray(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);

			    public String getlastreqdate(String testcode,String labcode, String crno,UserVO _UserVO);


			    public Map<String,LabTestVO> getPrvTestDtlAJAXMAPxray(String CrNo,UserVO userVO,String fromwhicall); 

			    public Map deleteReqDtlxray(InvestigationSearchVO searchVO, UserVO _UserVO,Inv_RequisitionRaisingPatientVO  lstPatVO);

}





 