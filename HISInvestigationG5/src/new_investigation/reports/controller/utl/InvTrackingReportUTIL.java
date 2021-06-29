package new_investigation.reports.controller.utl;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.icu.text.SimpleDateFormat;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.data.InvTrackingReportDATA;
import new_investigation.reports.controller.fb.InvTrackingReportFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingNewFB;
import new_investigation.transactions.controller.utl.MergeAllPdfNewInv;
import new_investigation.transactions.controller.utl.MongoXmlHandler;
import new_investigation.vo.InvTrackingReportVO;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class InvTrackingReportUTIL {


	public static JsonObject AjaxGetPatDetails(InvTrackingReportFB fb, HttpServletRequest request) {
		
		Status objStatus = new Status();
		InvTrackingReportVO vo = new InvTrackingReportVO();
		Map mp=new HashMap();
		
		JsonObject jsonResponse = new JsonObject();
		JsonArray patDetailsObjectRowContainer = new JsonArray();
		
		try {
			
			UserVO userVO = ControllerUTIL.getUserVO(request);
			if(userVO.getHospitalCode()==null) {
				userVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?(fb.getCrNo()==null || fb.getCrNo().equals("")?null:fb.getCrNo().substring(0, 5)):fb.getHospitalCode());
			}
			HelperMethods.populate(vo, fb);
			ControllerUTIL.setSysdate(request);
		     
			mp=InvTrackingReportDATA.AjaxGetPatDetails(vo, userVO);
			InvTrackingReportVO vo2 = (InvTrackingReportVO) mp.get("patDetails");
			
			if(vo2!=null) {
			JsonObject objectRow = new JsonObject();

			/*OP Patient Data*/
			objectRow.addProperty("patientName", vo2.getPatName()==null?"":vo2.getPatName());
			objectRow.addProperty("crNumber", vo2.getCrNumber()==null?"":vo2.getCrNumber());
			objectRow.addProperty("isUnknown", vo2.getIsUnknown()==null?"":vo2.getIsUnknown());
			objectRow.addProperty("isDead", vo2.getIsDead()==null?"":vo2.getIsDead());
			objectRow.addProperty("isMlc", vo2.getIsMlc()==null?"":vo2.getIsMlc());
			objectRow.addProperty("isNewBorn", vo2.getIsNewBorn()==null?"":vo2.getIsNewBorn());
			objectRow.addProperty("isPregnant", vo2.getIsPregnant()==null?"":vo2.getIsPregnant());
			objectRow.addProperty("isVip", vo2.getIsVip()==null?"":vo2.getIsVip());
			objectRow.addProperty("isConfidential", vo2.getIsConfidential()==null?"":vo2.getIsConfidential());
			objectRow.addProperty("patFirstName", vo2.getPatFirstName()==null?"":vo2.getPatFirstName());
			objectRow.addProperty("patMiddleName", vo2.getPatMiddleName()==null?"":vo2.getPatMiddleName());
			objectRow.addProperty("patLastName", vo2.getPatLastName()==null?"":vo2.getPatLastName());
			objectRow.addProperty("patGuardianName", vo2.getPatGuardianName()==null?"":vo2.getPatGuardianName());
			objectRow.addProperty("patCategoryCode", vo2.getPatCategoryCode()==null?"":vo2.getPatCategoryCode());
			objectRow.addProperty("patAge", vo2.getPatAge()==null?"":vo2.getPatAge());
			objectRow.addProperty("patHusbandName", vo2.getPatHusbandName()==null?"":vo2.getPatHusbandName());
			objectRow.addProperty("patGenderCode", vo2.getPatGenderCode()==null?"":vo2.getPatGenderCode());
			objectRow.addProperty("patCategory", vo2.getPatCategory()==null?"":vo2.getPatCategory());
			objectRow.addProperty("patDOB", vo2.getPatDOB()==null?"":vo2.getPatDOB());
			objectRow.addProperty("isActualDob", vo2.getIsActualDob()==null?"":vo2.getIsActualDob());
			objectRow.addProperty("patGender", vo2.getPatGender()==null?"":vo2.getPatGender());
			objectRow.addProperty("patStatusCode", vo2.getPatStatusCode()==null?"":vo2.getPatStatusCode());
			objectRow.addProperty("patStatus", vo2.getPatStatus()==null?"":vo2.getPatStatus());
			objectRow.addProperty("patMobileNo", vo2.getPatMobileNo()==null?"":vo2.getPatMobileNo());
			objectRow.addProperty("patAddress", vo2.getPatAddress()==null?"":vo2.getPatAddress());
			objectRow.addProperty("isCatExpired", vo2.getIsCatExpired()==null?"":vo2.getIsCatExpired());
			objectRow.addProperty("patEmailId", vo2.getPatEmailId()==null?"":vo2.getPatEmailId());
			 /*IPD Patient Data*/
			objectRow.addProperty("admissionDate", vo2.getAdmissionDate()==null?"":vo2.getAdmissionDate());
			objectRow.addProperty("patDeptUnitCode", vo2.getPatDeptUnitCode()==null?"":vo2.getPatDeptUnitCode());
			objectRow.addProperty("patVisitNo", vo2.getPatVisitNo()==null?"":vo2.getPatVisitNo());
			objectRow.addProperty("patEpisodeCode", vo2.getPatEpisodeCode()==null?"":vo2.getPatEpisodeCode());
			objectRow.addProperty("admittedDepartmentCode", vo2.getAdmittedDepartmentCode()==null?"":vo2.getAdmittedDepartmentCode());
			objectRow.addProperty("patAdmissionNo", vo2.getPatAdmissionNo()==null?"":vo2.getPatAdmissionNo());
			objectRow.addProperty("patDeptUnit", vo2.getPatDeptUnit()==null?"":vo2.getPatDeptUnit());
			objectRow.addProperty("admittedDepartmentName", vo2.getAdmittedDepartmentName()==null?"":vo2.getAdmittedDepartmentName());
			objectRow.addProperty("patWardCode", vo2.getPatWardCode()==null?"":vo2.getPatWardCode());
			objectRow.addProperty("admittedDepartmentCode", vo2.getAdmittedDepartmentCode()==null?"":vo2.getAdmittedDepartmentCode());
			objectRow.addProperty("patWardName", vo2.getPatWardName()==null?"":vo2.getPatWardName());
			objectRow.addProperty("patRoomNo", vo2.getPatRoomNo()==null?"":vo2.getPatRoomNo());
			objectRow.addProperty("patRoomName", vo2.getPatRoomName()==null?"":vo2.getPatRoomName());
			objectRow.addProperty("bedCode", vo2.getBedCode()==null?"":vo2.getBedCode());
			objectRow.addProperty("bedName", vo2.getBedName()==null?"":vo2.getBedName());
			objectRow.addProperty("hospitalCode", vo2.getHospitalCode()==null?"":vo2.getHospitalCode());
			objectRow.addProperty("consultantName", vo2.getConsultantName()==null?"":vo2.getConsultantName());
			objectRow.addProperty("patMlcNo", vo2.getPatMlcNo()==null?"":vo2.getPatMlcNo());
			objectRow.addProperty("diagnosis", vo2.getDiagnosis()==null?"":vo2.getDiagnosis());
			objectRow.addProperty("patAccNo", vo2.getPatAccNo()==null?"":vo2.getPatAccNo());
					
			patDetailsObjectRowContainer.add(objectRow);
			}
			jsonResponse.add("patDetails", patDetailsObjectRowContainer);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
		return jsonResponse;
		
	}
	

	public static JsonObject AjaxGetPatReqnList(InvTrackingReportFB fb, HttpServletRequest request) {
		
		Status objStatus= new Status();
		
		InvTrackingReportVO vo = new InvTrackingReportVO();
		List<InvTrackingReportVO> sampleBasedReqnListInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
		List<InvTrackingReportVO> patientBasedReqnListInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
		Map mp=new HashMap();
		
		JsonObject jsonResponse = new JsonObject();
		JsonArray patBasedReqnListObjectRowsContainer= new JsonArray(); 
		JsonArray samBasedReqnListObjectRowsContainer= new JsonArray(); 
		
		try {
			
			UserVO userVO = ControllerUTIL.getUserVO(request);
			if(userVO.getHospitalCode()==null) {
				userVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?(fb.getCrNo()==null || fb.getCrNo().equals("")?null:fb.getCrNo().substring(0, 5)):fb.getHospitalCode());
			}
			HelperMethods.populate(vo, fb);
			ControllerUTIL.setSysdate(request);
			
			vo.setDataFromArchival("0");
			String dateFiltersOrBoth=vo.getDateFiltersOrBoth();
			if(dateFiltersOrBoth!=null)
			if(dateFiltersOrBoth.equals("0") || dateFiltersOrBoth.equals("1")) {
				Boolean isUnionFromOldTableRequired=checkUnionFromOldTableRequired(vo.getFromDate());
				if(isUnionFromOldTableRequired) {
					vo.setDataFromArchival("1");
				}
			}
			
			int dateTypeCode=0;
			try { dateTypeCode = Integer.parseInt(fb.getDateTypeCode()); }
			catch (NumberFormatException e){ dateTypeCode=0; }
			
			mp=InvTrackingReportDATA.AjaxGetPatReqnList(vo, userVO);
			sampleBasedReqnListInvTrackingReportVO=(List<InvTrackingReportVO>) mp.get("sampleBasedReqnList");
			patientBasedReqnListInvTrackingReportVO=(List<InvTrackingReportVO>) mp.get("patientBasedReqnList");
			
			int i=0;
			for(InvTrackingReportVO vo2 : sampleBasedReqnListInvTrackingReportVO) {
				JsonObject objectRow = new JsonObject();
				objectRow.addProperty("sno", ++i);
				
				String groupNameTestName=vo2.getGroupName()==null || vo2.getGroupName().equals("")?"":vo2.getGroupName()+" | ";
				groupNameTestName+=vo2.getTestName()==null?"":vo2.getTestName();
				
				JsonObject turnAroundTime=CompareTestTurnAroundTime(vo2.getSampleCollDate(), vo2.getReportGenerationDate(), vo2.getTestCode(), vo, request);
				
				String billNo=vo2.getBillNo()==null?"-":vo2.getBillNo().equals("0")?"-":vo2.getBillNo();
				String testRateDetail=vo2.getTestRateDetail()==null?"-":vo2.getTestRateDetail();
				String testRate=testRateDetail.contains("^")?testRateDetail.split("\\^")[0]:"0";
				try {
					if(Double.parseDouble(testRate)<=0) {billNo="-";}
					testRate=Double.parseDouble(testRate)>0?"&#8377"+" "+testRate:"Free"; 
				 }
				catch(NumberFormatException e) { testRate="Free"; billNo="-"; }
				
				String billedUnbilled="-";
				String requisitionStatusCode = vo2.getRequisitionStatusCode()==null?"-":vo2.getRequisitionStatusCode();
				if(requisitionStatusCode.equals("2") || requisitionStatusCode.equals("5") || requisitionStatusCode.equals("10")) {
					billedUnbilled=vo2.getBillDetail()==null?"-":vo2.getBillDetail().contains("^")?vo2.getBillDetail().split("\\^")[0]:"-";
					billedUnbilled=billedUnbilled.equals("0")?"UnBilled":"Billed";
				} else {
					billedUnbilled="Billed";
				}
				
				String filterDateTypeName = "";
				String filterDateTypeDate = "";
				switch(dateTypeCode) {

				case 1: filterDateTypeName = "Requisition Date";
						filterDateTypeDate = vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate();
						break;
				case 2: filterDateTypeName = "Sample Collection Date";
						filterDateTypeDate = vo2.getSampleCollDate()==null?"-": vo2.getSampleCollDate();
						break;
				case 3: filterDateTypeName = "Sample Acceptance Date";
						filterDateTypeDate = vo2.getSampleAccepDate()==null?"-": vo2.getSampleAccepDate();
						break;
				case 4: filterDateTypeName = "Result Entry Date";
						filterDateTypeDate = vo2.getResultEntryDate()==null?"-": vo2.getResultEntryDate();
						break;
				case 5: filterDateTypeName = "Result Validation Date";
						filterDateTypeDate = vo2.getResultValDate()==null?"-": vo2.getResultValDate();
						break;
				case 6: filterDateTypeName = "Report Printing Date";
						filterDateTypeDate = vo2.getReportPrintDate()==null?"-": vo2.getReportPrintDate();
						break;

				default: filterDateTypeName = "Requisition Date";
						filterDateTypeDate = vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate();
						break;
				}

				JsonObject reqnAdvisedNotes = new JsonObject();
				//vo2.getInstructionFlag()
				reqnAdvisedNotes.addProperty("instructionGeneric", vo2.getInstructionGeneric());
				reqnAdvisedNotes.addProperty("instructionFlag", "-");
				reqnAdvisedNotes.addProperty("chiefComplaintsName", vo2.getChiefComplaintsName());
				reqnAdvisedNotes.addProperty("diagnosisName", vo2.getDiagnosisName());
				reqnAdvisedNotes.addProperty("advisedNote", vo2.getAdvisedNote());
				reqnAdvisedNotes.addProperty("advisedByDocName", vo2.getAdvisedByDocName());
				
				JsonObject reqnDataForPDFReport = new JsonObject();
				reqnDataForPDFReport.addProperty("hospitalCode", vo2.getHospitalCode());
				reqnDataForPDFReport.addProperty("crNumber", vo2.getCrNumber());
				reqnDataForPDFReport.addProperty("labCode", vo2.getLabCode());
				reqnDataForPDFReport.addProperty("requisitionNo", vo2.getRequisitionNo());
				
				JsonObject reqnDataForGrouping = new JsonObject();
				//reqnDataForGrouping.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate());
				reqnDataForGrouping.addProperty("requisitionNo", vo2.getRequisitionNo());
				reqnDataForGrouping.addProperty("requisitionStatusCode", vo2.getRequisitionStatusCode()==null?"-":vo2.getRequisitionStatusCode() );
				reqnDataForGrouping.addProperty("filterDateTypeCode", dateTypeCode);
				reqnDataForGrouping.addProperty("filterDateTypeName", filterDateTypeName);
				reqnDataForGrouping.addProperty("filterDateTypeDate", filterDateTypeDate);
				reqnDataForGrouping.add("reqnAdvisedNotes", reqnAdvisedNotes);
				reqnDataForGrouping.addProperty("priorityCode",  vo2.getPriorityCode());
				reqnDataForGrouping.add("reqnDataForPDFReport", reqnDataForPDFReport);
				reqnDataForGrouping.addProperty("reportURL", vo2.getReportURL()==null?"-":vo2.getReportURL() );
				objectRow.add("reqnDataForGrouping", reqnDataForGrouping);
				
				objectRow.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"-":vo2.getRequisitionDate() );
				objectRow.addProperty("labName", vo2.getLabName()==null?"-":vo2.getLabName() );
				objectRow.addProperty("advisedByDept", vo2.getAdvisedByDept()==null?"-":vo2.getAdvisedByDept() );
				objectRow.addProperty("groupNameTestName",  groupNameTestName);
				objectRow.addProperty("requisitionStatus", vo2.getRequisitionStatus()==null?"-":vo2.getRequisitionStatus() );
				objectRow.addProperty("requisitionStatusCode", vo2.getRequisitionStatusCode()==null?"-":vo2.getRequisitionStatusCode() );
				objectRow.add("turnAroundTime", turnAroundTime);
				objectRow.addProperty("testRate", testRate);
				objectRow.addProperty("billedUnbilled", billedUnbilled);
				objectRow.addProperty("testNote", turnAroundTime.get("totNote").getAsString());
				
				objectRow.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"-":vo2.getRequisitionDate() );
				objectRow.addProperty("requisitionBy", vo2.getRequisitionBy()==null?"-":vo2.getRequisitionBy() );
				objectRow.addProperty("advisedByDoc", vo2.getAdvisedByDoc()==null?"-":vo2.getAdvisedByDoc() );
				
				objectRow.addProperty("requisitionDNo", vo2.getRequisitionDNo()==null?"-":vo2.getRequisitionDNo() );
				objectRow.addProperty("requisitionNo", vo2.getRequisitionNo()==null?"-":vo2.getRequisitionNo() );
				objectRow.addProperty("testCode", vo2.getTestCode()==null?"-":vo2.getTestCode() );
				objectRow.addProperty("groupCode", vo2.getGroupCode()==null?"-":vo2.getGroupCode() );
				objectRow.addProperty("isGroup", vo2.getIsGroup()==null?"-":vo2.getIsGroup() );
				
				objectRow.addProperty("groupNameTestName", groupNameTestName);
				objectRow.addProperty("appointmentDate", vo2.getAppointmentDate()==null?"-":vo2.getAppointmentDate() );
				objectRow.addProperty("billNo", billNo);
				objectRow.addProperty("billDate", vo2.getBillDate()==null?"-":vo2.getBillDate() );
				
				objectRow.addProperty("sampleName", vo2.getSampleName()==null?"-":vo2.getSampleName() );
				objectRow.addProperty("sampleNo", vo2.getSampleNo()==null?"-":vo2.getSampleNo() );
				objectRow.addProperty("sampleCollDate", vo2.getSampleCollDate()==null?"-":vo2.getSampleCollDate() );
				objectRow.addProperty("sampleCollArea", vo2.getCollectionArea()==null?"-":vo2.getCollectionArea() );
				objectRow.addProperty("sampleCollBy", vo2.getSampleCollBy()==null?"-":vo2.getSampleCollBy() );
				objectRow.addProperty("labNo", vo2.getLabNo()==null?"-":vo2.getLabNo() );
				objectRow.addProperty("labCode", vo2.getLabCode()==null?"-":vo2.getLabCode() );
				
				objectRow.addProperty("packingListNo", vo2.getPackingListNo()==null?"-":vo2.getPackingListNo() );
				objectRow.addProperty("packingListBy", vo2.getPackingListBy()==null?"-":vo2.getPackingListBy() );
				objectRow.addProperty("packingListDateTime", vo2.getPackingListDateTime()==null?"-":vo2.getPackingListDateTime() );
				
				objectRow.addProperty("sampleAccepDate", vo2.getSampleAccepDate()==null?"-":vo2.getSampleAccepDate() );
				objectRow.addProperty("sampleAccepBy", vo2.getSampleAccepBy()==null?"-":vo2.getSampleAccepBy() );
				objectRow.addProperty("sampleAccepMode", vo2.getSampleAccepDate()==null?"-": vo2.getMachineName()==null?"Manual":"Machine" );
				objectRow.addProperty("machineName", vo2.getMachineName()==null?"-":vo2.getMachineName() );
				
				objectRow.addProperty("sampleRejecDate", vo2.getSampleRejecDate()==null?"-":vo2.getSampleRejecDate() );
				objectRow.addProperty("sampleRejecBy", vo2.getSampleRejecBy()==null?"-":vo2.getSampleRejecBy() );
				objectRow.addProperty("sampleRejecReason", vo2.getSampleRejecReason()==null?"-":vo2.getSampleRejecReason() );
				
				objectRow.addProperty("resultEntryDate", vo2.getResultEntryDate()==null?"-":vo2.getResultEntryDate() );
				objectRow.addProperty("resultEntryBy", vo2.getResultEntryBy()==null?"-":vo2.getResultEntryBy() );
				objectRow.addProperty("resultEntryParam", vo2.getResultEntryParam()==null?"-":vo2.getResultEntryParam() );
				objectRow.addProperty("resultValidDate", vo2.getResultValDate()==null?"-":vo2.getResultValDate() );
				objectRow.addProperty("resultValidBy", vo2.getResultValBy()==null?"-":vo2.getResultValBy() );
				objectRow.addProperty("resultReValidDate", vo2.getResultReValDate()==null?"-":vo2.getResultReValDate() );
				objectRow.addProperty("resultReValidBy", vo2.getResultReValBy()==null?"-":vo2.getResultReValBy() );

				objectRow.addProperty("reportGenerationDate", vo2.getReportGenerationDate()==null?"-":vo2.getReportGenerationDate() );
				objectRow.addProperty("reportPrintDate", vo2.getReportPrintDate()==null?"-":vo2.getReportPrintDate() );
				objectRow.addProperty("reportPrintBy", vo2.getReportPrintBy()==null?"-":vo2.getReportPrintBy() );
				objectRow.addProperty("reportURL", vo2.getReportURL()==null?"-":vo2.getReportURL() );
				
				
		        samBasedReqnListObjectRowsContainer.add(objectRow);
			}
			
			i=0;
			for(InvTrackingReportVO vo2 : patientBasedReqnListInvTrackingReportVO) {
				JsonObject objectRow = new JsonObject();
				objectRow.addProperty("sno", ++i);
				
				String groupNameTestName=vo2.getGroupName()==null || vo2.getGroupName().equals("")?"":vo2.getGroupName()+" | ";
				groupNameTestName+=vo2.getTestName()==null?"":vo2.getTestName();
				
				JsonObject turnAroundTime=CompareTestTurnAroundTime(vo2.getPatientAccepDate(), vo2.getReportGenerationDate(), vo2.getTestCode(), vo, request);
				
				String billNo=vo2.getBillNo()==null?"-":vo2.getBillNo().equals("0")?"-":vo2.getBillNo();
				String testRateDetail=vo2.getTestRateDetail()==null?"-":vo2.getTestRateDetail();
				String testRate=testRateDetail.contains("^")?testRateDetail.split("\\^")[0]:"0";
				try {
					if(Double.parseDouble(testRate)<=0) {billNo="-";}
					testRate=Double.parseDouble(testRate)>0?"&#8377"+" "+testRate:"Free"; }
				catch(NumberFormatException e) { testRate="Free"; billNo="-"; }
				
				String billedUnbilled="-";
				
				String requisitionStatusCode = vo2.getRequisitionStatusCode()==null?"-":vo2.getRequisitionStatusCode();
				if(requisitionStatusCode.equals("2") || requisitionStatusCode.equals("5") || requisitionStatusCode.equals("10")) {
					
					billedUnbilled=vo2.getBillDetail()==null?"-":(vo2.getBillDetail().contains("^")?vo2.getBillDetail().split("\\^")[0]:"-");
					billedUnbilled=billedUnbilled.equals("0")?"UnBilled":"Billed";
				} else {
					billedUnbilled="Billed";
				}
				
				String filterDateTypeName = "";
				String filterDateTypeDate = "";
				switch(dateTypeCode) {

				case 1: filterDateTypeName = "Requisition Date";
						filterDateTypeDate = vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate();
						break;
				case 2: filterDateTypeName = "Patient Acceptance Date";
						filterDateTypeDate = vo2.getPatientAccepDate()==null?"-": vo2.getPatientAccepDate();
						break;
				case 3: filterDateTypeName = "Patient Acceptance Date";
						filterDateTypeDate = vo2.getPatientAccepDate()==null?"-": vo2.getPatientAccepDate();
						break;
				case 4: filterDateTypeName = "Result Entry Date";
						filterDateTypeDate = vo2.getResultEntryDate()==null?"-": vo2.getResultEntryDate();
						break;
				case 5: filterDateTypeName = "Result Validation Date";
						filterDateTypeDate = vo2.getResultValDate()==null?"-": vo2.getResultValDate();
						break;
				case 6: filterDateTypeName = "Report Printing Date";
						filterDateTypeDate = vo2.getReportPrintDate()==null?"-": vo2.getReportPrintDate();
						break;
				default: filterDateTypeName = "Requisition Date";
						 filterDateTypeDate = vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate();
						 break;
				}

				JsonObject reqnAdvisedNotes = new JsonObject();
				//vo2.getInstructionFlag()
				reqnAdvisedNotes.addProperty("instructionGeneric", vo2.getInstructionGeneric());
				reqnAdvisedNotes.addProperty("instructionFlag", "-");
				reqnAdvisedNotes.addProperty("chiefComplaintsName", vo2.getChiefComplaintsName());
				reqnAdvisedNotes.addProperty("diagnosisName", vo2.getDiagnosisName());
				reqnAdvisedNotes.addProperty("advisedNote", vo2.getAdvisedNote());
				reqnAdvisedNotes.addProperty("advisedByDocName", vo2.getAdvisedByDocName());
				
				JsonObject reqnDataForPDFReport = new JsonObject();
				reqnDataForPDFReport.addProperty("hospitalCode", vo2.getHospitalCode());
				reqnDataForPDFReport.addProperty("crNumber", vo2.getCrNumber());
				reqnDataForPDFReport.addProperty("labCode", vo2.getLabCode());
				reqnDataForPDFReport.addProperty("requisitionNo", vo2.getRequisitionNo());
				
				JsonObject reqnDataForGrouping = new JsonObject();
				//reqnDataForGrouping.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate());
				reqnDataForGrouping.addProperty("requisitionNo", vo2.getRequisitionNo());
				reqnDataForGrouping.addProperty("requisitionStatusCode", vo2.getRequisitionStatusCode()==null?"-":vo2.getRequisitionStatusCode() );
				reqnDataForGrouping.addProperty("filterDateTypeCode", dateTypeCode);
				reqnDataForGrouping.addProperty("filterDateTypeName", filterDateTypeName);
				reqnDataForGrouping.addProperty("filterDateTypeDate", filterDateTypeDate);
				reqnDataForGrouping.add("reqnAdvisedNotes", reqnAdvisedNotes);
				reqnDataForGrouping.addProperty("priorityCode",  vo2.getPriorityCode());
				reqnDataForGrouping.add("reqnDataForPDFReport", reqnDataForPDFReport);
				reqnDataForGrouping.addProperty("reportURL", vo2.getReportURL()==null?"-":vo2.getReportURL());
				objectRow.add("reqnDataForGrouping", reqnDataForGrouping);
				
				objectRow.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"-":vo2.getRequisitionDate() );
				objectRow.addProperty("labName", vo2.getLabName()==null?"-":vo2.getLabName() );
				objectRow.addProperty("advisedByDept", vo2.getAdvisedByDept()==null?"-":vo2.getAdvisedByDept() );
				objectRow.addProperty("groupNameTestName",  groupNameTestName);
				objectRow.addProperty("requisitionStatus", vo2.getRequisitionStatus()==null?"-":vo2.getRequisitionStatus() );
				objectRow.addProperty("requisitionStatusCode", vo2.getRequisitionStatusCode()==null?"-":vo2.getRequisitionStatusCode() );
				objectRow.add("turnAroundTime", turnAroundTime);
				objectRow.addProperty("testRate", testRate);
				objectRow.addProperty("billedUnbilled", billedUnbilled);
				objectRow.addProperty("testNote", turnAroundTime.get("totNote").getAsString());
				
				objectRow.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"-":vo2.getRequisitionDate() );
				objectRow.addProperty("requisitionBy", vo2.getRequisitionBy()==null?"-":vo2.getRequisitionBy() );
				objectRow.addProperty("advisedByDoc", vo2.getAdvisedByDoc()==null?"-":vo2.getAdvisedByDoc() );
				
				objectRow.addProperty("requisitionDNo", vo2.getRequisitionDNo()==null?"-":vo2.getRequisitionDNo() );
				objectRow.addProperty("requisitionNo", vo2.getRequisitionNo()==null?"-":vo2.getRequisitionNo() );
				objectRow.addProperty("testCode", vo2.getTestCode()==null?"-":vo2.getTestCode() );
				objectRow.addProperty("groupCode", vo2.getGroupCode()==null?"-":vo2.getGroupCode() );
				objectRow.addProperty("isGroup", vo2.getIsGroup()==null?"-":vo2.getIsGroup() );
				
				objectRow.addProperty("groupNameTestName", groupNameTestName);
				objectRow.addProperty("appointmentDate", vo2.getAppointmentDate()==null?"-":vo2.getAppointmentDate() );
				objectRow.addProperty("billNo", billNo);
				objectRow.addProperty("billDate", vo2.getBillDate()==null?"-":vo2.getBillDate() );
				
				objectRow.addProperty("accessionNo", vo2.getAccessionNo()==null?"-":vo2.getAccessionNo() );
				objectRow.addProperty("patientAccepDate", vo2.getPatientAccepDate()==null?"-":vo2.getPatientAccepDate() );
				objectRow.addProperty("patientAccepArea", vo2.getCollectionArea()==null?"-":vo2.getCollectionArea() );
				objectRow.addProperty("patientAccepBy", vo2.getPatientAccepBy()==null?"-":vo2.getPatientAccepBy() );
				objectRow.addProperty("patientAccepMode", vo2.getPatientAccepDate()==null?"-":vo2.getMachineName()==null?"Manual":"Machine");
				objectRow.addProperty("machineName", vo2.getMachineName()==null?"-":vo2.getMachineName() );
				
				objectRow.addProperty("patientRejecDate", vo2.getPatientRejecDate()==null?"-":vo2.getPatientRejecDate() );
				objectRow.addProperty("patientRejecBy", vo2.getPatientRejecBy()==null?"-":vo2.getPatientRejecBy() );
				objectRow.addProperty("patientRejecReason", vo2.getPatientRejecReason()==null?"-":vo2.getPatientRejecReason() );
				
				objectRow.addProperty("resultEntryDate", vo2.getResultEntryDate()==null?"-":vo2.getResultEntryDate() );
				objectRow.addProperty("resultEntryBy", vo2.getResultEntryBy()==null?"-":vo2.getResultEntryBy() );
				objectRow.addProperty("resultEntryParam", vo2.getResultEntryParam()==null?"-":vo2.getResultEntryParam() );
				objectRow.addProperty("resultValidDate", vo2.getResultValDate()==null?"-":vo2.getResultValDate() );
				objectRow.addProperty("resultValidBy", vo2.getResultValBy()==null?"-":vo2.getResultValBy() );
				objectRow.addProperty("resultReValidDate", vo2.getResultReValDate()==null?"-":vo2.getResultReValDate() );
				objectRow.addProperty("resultReValidBy", vo2.getResultReValBy()==null?"-":vo2.getResultReValBy() );

				objectRow.addProperty("reportGenerationDate", vo2.getReportGenerationDate()==null?"-":vo2.getReportGenerationDate() );
				objectRow.addProperty("reportPrintDate", vo2.getReportPrintDate()==null?"-":vo2.getReportPrintDate() );
				objectRow.addProperty("reportPrintBy", vo2.getReportPrintBy()==null?"-":vo2.getReportPrintBy() );
				objectRow.addProperty("reportURL", vo2.getReportURL()==null?"-":vo2.getReportURL() );
				
		        patBasedReqnListObjectRowsContainer.add(objectRow);
			}
			
			jsonResponse.add("sampleBasedReqnList", samBasedReqnListObjectRowsContainer);
			jsonResponse.add("patientBasedReqnList", patBasedReqnListObjectRowsContainer);
			
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e) {		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
		return jsonResponse;
		
	}
	
	
	public static String isfromFTPorMONGO(HttpServletRequest request,HttpServletResponse response)
	{
		String ftpUrl="";
		UserVO userVO = ControllerUTIL.getUserVO(request);
		
		if(userVO==null)
		{
			
			String hospitalcode=(String)request.getAttribute("hosptialcode");
			userVO =new UserVO();
			userVO.setHospitalCode(hospitalcode);
		}
		
		ftpUrl=InvTrackingReportDATA.isfromFTPorMONGO( userVO);
		
		return ftpUrl;
		
	}
	
	public static void AjaxGetPDFReportMongo(InvTrackingReportFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		try{
			
			String selectedPDFName= fb.getSelectedPDFName();
			List<InvTrackingReportVO> InvResultReportPrintingVO=null;
			InvTrackingReportVO invresultentryv = new InvTrackingReportVO();
			List<InvTrackingReportVO> lstInvResultReportPrintingVO=new ArrayList<InvTrackingReportVO>();
			List<String> reqList=new ArrayList();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			if(userVO.getHospitalCode()==null) {
				userVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?(fb.getCrNo()==null || fb.getCrNo().equals("")?null:fb.getCrNo().substring(0, 5)):fb.getHospitalCode());
			}
			String[] arrSelectedPDFName=selectedPDFName.split("@@");
					
			List<InvTrackingReportVO> lstInvResultEntryTemplateVO=null;
			WebUTIL.setMapInSession(mp, request);
			List<byte[]> pdfs = new ArrayList<byte[]>();
			Map<String,String> objMapSamAcc= new HashMap<String,String>();
			
			for(int i=0;i<arrSelectedPDFName.length;i++)
			{
				String strPdfName = arrSelectedPDFName[i];
				objMapSamAcc.put(strPdfName,"1");
			}
			Set setPdfName=objMapSamAcc.keySet();
			Iterator itrPdfName=setPdfName.iterator();

			//iterate over Crno's
			while(itrPdfName.hasNext()) {
				String strPdfName=(String)itrPdfName.next();
				filename=strPdfName;
				byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile(strPdfName); 
				pdfs.add(Pdf);	
			}

			response.setContentType("application/pdf");
			response.setHeader("content-disposition", "inline; filename="+filename);	
			OutputStream output = response.getOutputStream();
			//System.out.println("concatallPDFs");
			MergeAllPdfNewInv.concatallPDFs(pdfs, output, null,request);				   
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		
	}
	
	public static void AjaxGetPDFReportFTP(InvTrackingReportFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		Map mp=new HashMap();
		boolean flag=false;
		String filename="";
		try{
			List<InvTrackingReportVO> InvTrackingReportVO=null;
			InvTrackingReportVO invresultentryv = new InvTrackingReportVO();
			List<InvTrackingReportVO> lstInvTrackingReportVO=new ArrayList<InvTrackingReportVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			if(userVO.getHospitalCode()==null) {
				userVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?(fb.getCrNo()==null || fb.getCrNo().equals("")?null:fb.getCrNo().substring(0, 5)):fb.getHospitalCode());
			}
			String selectedPDFName= fb.getSelectedPDFName();			
			String[] arrSelectedPDFName=selectedPDFName.split("@@");
			Map<String,String> objMapSamAcc= new HashMap<String,String>();
			List<InputStream> pdfs = new ArrayList<InputStream>();
			
			for(int i1=0;i1<arrSelectedPDFName.length;i1++) {
				String pdfname=arrSelectedPDFName[i1];
				objMapSamAcc.put(pdfname,"12");		
			}
			
			Set setPdfName=objMapSamAcc.keySet();
			Iterator itrPdfName=setPdfName.iterator();
			String patientcreatefileftpaddress= fb.getFtpserver();

			//iterate over Crno's
			while(itrPdfName.hasNext()) {
				String strPdfName=(String)itrPdfName.next();
				String crNo="";
				if(strPdfName.length()>15)
				crNo=strPdfName.substring(0,15);
				//System.out.println(strPdfName.substring(5,7));
				String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
				String insideyear=MergeAllPdfNewInv.getInsideYear(crNo);
				String count=MergeAllPdfNewInv.getcount(crNo);
				String strPdfPath = patientcreatefileftpaddress+"/"+strPdfName.substring(0,5)+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+strPdfName.substring(0,15)+"/"+strPdfName;
				System.out.println("strPdfPath : "+strPdfPath);
					
				//ByteArrayOutputStream Pdf; 
				URL urlftp=new URL(strPdfPath);
				URLConnection urlcon=urlftp.openConnection();

				//pdfs.add(Pdf); 
				pdfs.add(urlcon.getInputStream());			
			}
				 
			filename=filename+".pdf";
			response.setContentType("application/pdf");
			response.setHeader("content-disposition", "inline; filename="+filename);	
			OutputStream output = response.getOutputStream();
			//System.out.println("concatallPDFs");
			MergeAllPdfNewInv.concatallPDFsFTP(pdfs, output, null,request);			   
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		
	}
	
	
	public static JsonObject AjaxGetReqnTestParamList(InvTrackingReportFB fb, HttpServletRequest request) {
		Status objStatus= new Status();
		
		InvTrackingReportVO vo = new InvTrackingReportVO();
		List<InvTrackingReportVO> listInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
		Map mp=new HashMap();
		
		JsonObject jsonResponse = new JsonObject();
		
		try {
			
			UserVO userVO = ControllerUTIL.getUserVO(request);
			if(userVO.getHospitalCode()==null) {
				userVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?(fb.getCrNo()==null || fb.getCrNo().equals("")?null:fb.getCrNo().substring(0, 5)):fb.getHospitalCode());
			}
			HelperMethods.populate(vo, fb);
			ControllerUTIL.setSysdate(request);
			
			vo.setDataFromArchival("0");
			String dateFiltersOrBoth=vo.getDateFiltersOrBoth();
			if(dateFiltersOrBoth!=null)
			if(dateFiltersOrBoth.equals("0") || dateFiltersOrBoth.equals("1")) {
				Boolean isUnionFromOldTableRequired=checkUnionFromOldTableRequired(vo.getFromDate());
				if(isUnionFromOldTableRequired) {
					vo.setDataFromArchival("1");
				}
			}
			
			
			mp=InvTrackingReportDATA.AjaxGetReqnTestParamList(vo, userVO);
			listInvTrackingReportVO=(List<InvTrackingReportVO>) mp.get("reqnTestParamList");
			
			JsonObject testCodeWiseArray = new JsonObject();
			JsonObject testAndGroupCodeWiseJson = new JsonObject();
			JsonObject groupCodeWiseArray = new JsonObject();
			
			Map<String, List<InvTrackingReportVO> > tesCodeWiseListMap = new HashMap<String, List<InvTrackingReportVO> >() ;
			Map<String, List<InvTrackingReportVO> > groupCodeWiseListMap = new HashMap<String, List<InvTrackingReportVO> >() ;
			
			for(InvTrackingReportVO vo2 : listInvTrackingReportVO) {
				if(vo2.getIsGroup().equals("0")) { 
					
					if(tesCodeWiseListMap.containsKey(vo2.getTestCode())) {
						List<InvTrackingReportVO> listInvTrackingReportVO2 = new ArrayList<InvTrackingReportVO>();
						listInvTrackingReportVO2 =  (List<InvTrackingReportVO>) tesCodeWiseListMap.get(vo2.getTestCode());
						listInvTrackingReportVO2.add(vo2);
						tesCodeWiseListMap.put(vo2.getTestCode(), listInvTrackingReportVO2);
					
					} else {
						List<InvTrackingReportVO> listInvTrackingReportVO2 = new ArrayList<InvTrackingReportVO>();
						listInvTrackingReportVO2.add(vo2);
						tesCodeWiseListMap.put(vo2.getTestCode(), listInvTrackingReportVO2);
					}
					
				} else if (vo2.getIsGroup().equals("1")){
					
					if(groupCodeWiseListMap.containsKey(vo2.getGroupCode())) {
						List<InvTrackingReportVO> listInvTrackingReportVO2 = new ArrayList<InvTrackingReportVO>();
						listInvTrackingReportVO2 =  (List<InvTrackingReportVO>) groupCodeWiseListMap.get(vo2.getGroupCode());
						listInvTrackingReportVO2.add(vo2);
						groupCodeWiseListMap.put(vo2.getGroupCode(), listInvTrackingReportVO2);
					
					} else {
						List<InvTrackingReportVO> listInvTrackingReportVO2 = new ArrayList<InvTrackingReportVO>();
						listInvTrackingReportVO2.add(vo2);
						groupCodeWiseListMap.put(vo2.getGroupCode(), listInvTrackingReportVO2);
					}
				}
			}
			
			List<InvTrackingReportVO> listTestInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
			
			JsonObject reqnDnoPlusTestCodeArrayContainer = new JsonObject();
			for (Map.Entry<String, List<InvTrackingReportVO> > entry : tesCodeWiseListMap.entrySet()) {
				listTestInvTrackingReportVO = entry.getValue();
				
				
				JsonObject testParamObjectArrayContainer  = new JsonObject();
				JsonObject reqnDnoPlusTestCodeArray= new JsonObject();
				String testName="";
				
			for(InvTrackingReportVO vo2 : listTestInvTrackingReportVO) {
					
					String reqnDnoPlusTestCode = vo2.getRequisitionDNo().concat(vo2.getTestCode());
					testName = vo2.getTestName()==null?"TestsCode:"+vo2.getTestCode():vo2.getTestName();
					
					String strTestParameterDetails = vo2.getTestParameterDetails()!=null && vo2.getTestParameterDetails()!=" "?vo2.getTestParameterDetails():"";
					if(!strTestParameterDetails.equals("")) {
						
					String[] strTestParaList = strTestParameterDetails.split(",");
					if(strTestParaList!=null && strTestParaList.length>0)
					{
						
						JsonArray testParamObjectArray= new JsonArray(); 
						int noOfParameter = strTestParaList.length;
						for(int i=0;i<noOfParameter;i++)
						{
							JsonObject paramObject = new JsonObject();
							String testParam = strTestParaList[i];
							String[] strParamValue = testParam.split("#");
							
							if(strParamValue.length>=5 && strParamValue[1]!="" && strParamValue[2]!="") {
							String isInteger = "0";
							try {
								Float paramValue = Float.parseFloat(strParamValue[2]);
								isInteger = "1";
							} catch (Exception e) {
								isInteger = "0";
							}
							String strParaCode = strParamValue[0] ;
							String strParaName = strParamValue[1];
							String strValue=strParamValue[2];
							String strParaRefRange=strParamValue[3];
							String strOutOfBound = strParamValue[4];
						
							
							paramObject.addProperty("paramCode", strParaCode+vo2.getTestCode());
							paramObject.addProperty("paraName", strParaName);
							paramObject.addProperty("paraValue", strValue);
							paramObject.addProperty("testName", vo2.getTestName()==null?"TestsCode:"+vo2.getTestCode():vo2.getTestName());
							paramObject.addProperty("refRange", strParaRefRange);
							paramObject.addProperty("outOfBound", strOutOfBound);
							paramObject.addProperty("dateTime", vo2.getRequisitionDate());
							paramObject.addProperty("isInteger", isInteger);
							
							testParamObjectArray.add(paramObject);
							}
						} 
						if(testParamObjectArray.size()>0)
						testParamObjectArrayContainer.add(reqnDnoPlusTestCode, testParamObjectArray);
					} } 
			 }
			
			if(!testParamObjectArrayContainer.isJsonNull() && !testParamObjectArrayContainer.toString().equalsIgnoreCase("{}")) {
			reqnDnoPlusTestCodeArray.add("reqnDnoPlusTestCode", testParamObjectArrayContainer);
			reqnDnoPlusTestCodeArray.addProperty("isGroup", "0");
			reqnDnoPlusTestCodeArray.addProperty("testCode", entry.getKey());
			reqnDnoPlusTestCodeArray.addProperty("testName", testName);
			reqnDnoPlusTestCodeArray.addProperty("Note", "Comments");	
			
			reqnDnoPlusTestCodeArrayContainer.add(entry.getKey(), reqnDnoPlusTestCodeArray);
			}
			}
			
			
			List<InvTrackingReportVO> listGroupInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
			JsonObject reqnNoPlusGroupCodeArrayContainer = new JsonObject();
				for (Map.Entry<String, List<InvTrackingReportVO> > entry : groupCodeWiseListMap.entrySet()) {
					listGroupInvTrackingReportVO = entry.getValue();
					
					
					
					JsonObject testParamObjectArrayContainer  = new JsonObject();
					JsonObject reqnNoPlusGroupCodeArray = new JsonObject();
					String groupName="";
					
				for(InvTrackingReportVO vo2 : listGroupInvTrackingReportVO) {
					
					String reqnNoPlusGroupCode = vo2.getRequisitionNo().concat(vo2.getGroupCode());
					groupName = vo2.getGroupName()==null?"GroupCode:"+vo2.getGroupCode():vo2.getGroupName();
					
					String strTestParameterDetails = vo2.getTestParameterDetails()!=null && vo2.getTestParameterDetails()!=" "?vo2.getTestParameterDetails():"";
					if(!strTestParameterDetails.equals("")) {
					String[] strTestParaList = strTestParameterDetails.split(",");
					if(strTestParaList!=null )
					{

					JsonArray testParamObjectArray= new JsonArray(); 
					if(testParamObjectArrayContainer.has(reqnNoPlusGroupCode)) {
						testParamObjectArray = testParamObjectArrayContainer.getAsJsonArray(reqnNoPlusGroupCode);
					}
					
					int noOfParameter = strTestParaList.length;
					
					for(int i=0;i<noOfParameter;i++)
					{
						JsonObject paramObject = new JsonObject();
						String testParam = strTestParaList[i];
						String[] strParamValue = testParam.split("#");
						
						if(strParamValue.length>=5 && strParamValue[1]!="" && strParamValue[2]!="") {
						String isInteger = "0";
						try {
							Float paramValue = Float.parseFloat(strParamValue[2]);
							isInteger = "1";
						} catch (Exception e) {
							isInteger = "0";
						}
						String strParaCode = strParamValue[0] ;
						String strParaName = strParamValue[1];
						String strValue=strParamValue[2];
						String strParaRefRange= strParamValue[3];
						String strOutOfBound = strParamValue[4];
						
						paramObject.addProperty("paramCode", strParaCode+vo2.getTestCode());
						paramObject.addProperty("paraName", strParaName);
						paramObject.addProperty("paraValue", strValue);
						paramObject.addProperty("testName", vo2.getTestName()==null?"TestsCode:"+vo2.getTestCode():vo2.getTestName());
						paramObject.addProperty("refRange", strParaRefRange);
						paramObject.addProperty("outOfBound", strOutOfBound);
						paramObject.addProperty("dateTime", vo2.getRequisitionDate());
						paramObject.addProperty("isInteger", isInteger);
					
						testParamObjectArray.add(paramObject);
						}
					}
					if(testParamObjectArray.size()>0)
					testParamObjectArrayContainer.add(reqnNoPlusGroupCode, testParamObjectArray);
				  } }	
				}
				
				if(!testParamObjectArrayContainer.isJsonNull() && !testParamObjectArrayContainer.toString().equalsIgnoreCase("{}")) {
				reqnNoPlusGroupCodeArray.add("reqnNoPlusGroupCode", testParamObjectArrayContainer);
				reqnNoPlusGroupCodeArray.addProperty("isGroup", "1");
				reqnNoPlusGroupCodeArray.addProperty("groupCode", entry.getKey());
				reqnNoPlusGroupCodeArray.addProperty("groupName", groupName);
				reqnNoPlusGroupCodeArray.addProperty("Note", "Comments");
				
				reqnNoPlusGroupCodeArrayContainer.add(entry.getKey(), reqnNoPlusGroupCodeArray);
				}
				}
				
				//testCodeWiseArray.add("testCodeWise", reqnDnoPlusTestCodeArrayContainer);
				//groupCodeWiseArray.add("groupCodeWise", reqnNoPlusGroupCodeArrayContainer);
			
				testAndGroupCodeWiseJson.add("testCodeWise", reqnDnoPlusTestCodeArrayContainer);
				testAndGroupCodeWiseJson.add("groupCodeWise", reqnNoPlusGroupCodeArrayContainer);
			
			jsonResponse.add("reqnTestParamList", testAndGroupCodeWiseJson);
			//jsonResponse.add("reqnTestParamList", groupCodeWiseArray);
			
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
		return jsonResponse;
	}
	
	
	public static JsonObject GetDateDiffs(String a, String b) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy H:mm", Locale.ENGLISH);
		JsonObject DiffDateTimeJson = new JsonObject();
		String diffDateTime="-";
		//default values so that there is no possibility fo rerror
		DiffDateTimeJson.addProperty("totalInDays", 0);
	    DiffDateTimeJson.addProperty("totalInHours", 0);
	    DiffDateTimeJson.addProperty("totalInMinutes", 0);
	    DiffDateTimeJson.addProperty("totalInSeconds", 0);
	    DiffDateTimeJson.addProperty("diffDateTime", diffDateTime);
	    
		try {
		if(a!=null && b!=null) {
		 
		    Date firstDate = sdf.parse(a);
		    Date secondDate = sdf.parse(b);
		 
		    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		    long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    long hours =TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS) - days*24;
		    long minutes =(TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS) -(hours+days*24)*60);
		    long seconds =TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS) -(minutes+(hours+days*24)*60)*60;
		    
		    if(days>0) {
		    	diffDateTime = days + " Days ";
		    	if(days<3 && hours>0)
		    		diffDateTime +=hours +" Hrs ";
		    }
		    else if(hours>0) {
		    	diffDateTime = hours + " hrs ";
		    	if(minutes>0)
		    		diffDateTime += minutes +" Min ";
		    }
		    else if(minutes>0) {
		    	diffDateTime = minutes +" Min ";
		    	if(seconds>0)
		    		diffDateTime +=seconds+" ss";
		    }
		    else if(seconds>0) {
		    	diffDateTime = seconds+" ss";
		    }
		    DiffDateTimeJson.addProperty("totalInDays", days);
		    DiffDateTimeJson.addProperty("totalInHours", hours+days*24);
		    DiffDateTimeJson.addProperty("totalInMinutes", minutes+(hours+days*24)*60);
		    DiffDateTimeJson.addProperty("totalInSeconds", seconds+(minutes+(hours+days*24)*60)*60);
		    DiffDateTimeJson.addProperty("diffDateTime", diffDateTime);
		}
		else if(a!=null && b==null) {
			 
		    Date firstDate = sdf.parse(a);
		    Date sysDate = new Date(System.currentTimeMillis());

		    long diffInMillies = Math.abs(sysDate.getTime() - firstDate.getTime());
		    long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    long hours =TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS) - days*24;
		    long minutes =(TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS) -(hours+days*24)*60);
		    long seconds =TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS) -(minutes+(hours+days*24)*60)*60;
		    
		    if(days>0) {
		    	diffDateTime = days + " Days ";
		    	if(days<3 && hours>0)
		    		diffDateTime +=hours +" Hrs ";
		    }
		    else if(hours>0) {
		    	diffDateTime = hours + " hrs ";
		    	if(minutes>0)
		    		diffDateTime += minutes +" Min ";
		    }
		    else if(minutes>0) {
		    	diffDateTime = minutes +" Min ";
		    	if(seconds>0)
		    		diffDateTime +=seconds+" ss";
		    }
		    else if(seconds>0) {
		    	diffDateTime = seconds+" ss";
		    }
		    DiffDateTimeJson.addProperty("totalInDays", days);
		    DiffDateTimeJson.addProperty("totalInHours", hours+days*24);
		    DiffDateTimeJson.addProperty("totalInMinutes", minutes+(hours+days*24)*60);
		    DiffDateTimeJson.addProperty("totalInSeconds", seconds+(minutes+(hours+days*24)*60)*60);
		    DiffDateTimeJson.addProperty("diffDateTime", diffDateTime);
		}
		else {
			
		}
		
		
		} catch (ParseException e) {
			e.printStackTrace();
			return DiffDateTimeJson;
		}
		return DiffDateTimeJson;
		
	}
	
	public static JsonObject CompareTestTurnAroundTime(String samOrPatAccepDate, String reportGenDate, String testCode, InvTrackingReportVO vo, HttpServletRequest request) {
		Map mapAvgTurnAroundTime = new HashMap();
		InvTrackingReportFB fb = new InvTrackingReportFB();
		JsonObject jsonTurnAroundTimeObject = new JsonObject();
		
		//default values so that there is no possibility fo rerror
		jsonTurnAroundTimeObject.addProperty("turnAroundTime", "-");
		jsonTurnAroundTimeObject.addProperty("totDecisionCode", "0");
		jsonTurnAroundTimeObject.addProperty("totNote", "-");
		
		try {
			
			HelperMethods.populate(fb, vo);
			UserVO userVO = ControllerUTIL.getUserVO(request);
			if(userVO.getHospitalCode()==null) {
				userVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?(fb.getCrNo()==null || fb.getCrNo().equals("")?null:fb.getCrNo().substring(0, 5)):fb.getHospitalCode());
			}
			
			mapAvgTurnAroundTime=(Map)request.getSession().getAttribute("mapAvgTurnAroundTime");
			
			if(mapAvgTurnAroundTime==null || mapAvgTurnAroundTime.get(testCode)==null)
			{	
				//mapAvgTurnAroundTime=InvTrackingReportDATA.GetTestTurnAroundTime(vo, userVO);
				//request.getSession().setAttribute("mapAvgTurnAroundTime", mapAvgTurnAroundTime);
				//mapAvgTurnAroundTime=GetTestTurnAroundTime(fb, request);
			}
			
			int avgTurnAroundTime=(int) Long.parseLong(mapAvgTurnAroundTime.get(testCode).toString()==null ? "0":mapAvgTurnAroundTime.get(testCode).toString());
		
			JsonObject DiffDateTimeJson = new JsonObject();
			DiffDateTimeJson=GetDateDiffs(samOrPatAccepDate, reportGenDate);
		
			int totalInHours=(int) DiffDateTimeJson.get("totalInHours").getAsLong();
		
			String totNote="-";
			String totDecisionCode="0";
		
			if(samOrPatAccepDate!=null && reportGenDate!=null && avgTurnAroundTime>0) {
				if( totalInHours<avgTurnAroundTime) {
					totDecisionCode="1"; totNote="Test before | TAT("+avgTurnAroundTime+"hrs)";
					}
				if( totalInHours==avgTurnAroundTime) {
					totDecisionCode="2"; totNote="Test within | TAT("+avgTurnAroundTime+"hrs)";
					}
				if(totalInHours>avgTurnAroundTime) {
					totDecisionCode="3"; totNote="Test delayed "+(totalInHours-avgTurnAroundTime)+"hrs above | TAT("+avgTurnAroundTime+"hrs)";
					}
			}
			else if(samOrPatAccepDate!=null && reportGenDate==null && avgTurnAroundTime>0) {
				if(totalInHours<avgTurnAroundTime) {
					totDecisionCode="4"; totNote="Test within | TAT("+avgTurnAroundTime+"hrs)";
					}
				if(totalInHours>avgTurnAroundTime) {
					totDecisionCode="5"; totNote="Test delayed "+(totalInHours-avgTurnAroundTime)+"hrs above | TAT("+avgTurnAroundTime+"hrs)";
					}
			}
			
			jsonTurnAroundTimeObject.addProperty("turnAroundTime", DiffDateTimeJson.get("diffDateTime").toString().replaceAll("\"", ""));
			jsonTurnAroundTimeObject.addProperty("totDecisionCode", totDecisionCode);
			jsonTurnAroundTimeObject.addProperty("totNote", totNote);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return jsonTurnAroundTimeObject;
		}
		return jsonTurnAroundTimeObject;
		
	}
	
	
	public static Map GetTestTurnAroundTime(InvTrackingReportFB fb, HttpServletRequest request) {
		
		Status objStatus = new Status();
		InvTrackingReportVO vo = new InvTrackingReportVO();
		Map mp=new HashMap();
		Map mapAvgTurnAroundTime = new HashMap();
		try {
			
			UserVO userVO = ControllerUTIL.getUserVO(request);
			if(userVO.getHospitalCode()==null) {
				userVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?(fb.getCrNo()==null || fb.getCrNo().equals("")?null:fb.getCrNo().substring(0, 5)):fb.getHospitalCode());
			}
			HelperMethods.populate(vo, fb);
			ControllerUTIL.setSysdate(request);
			
			mapAvgTurnAroundTime=(Map)request.getSession().getAttribute("mapAvgTurnAroundTime");
			
			if(mapAvgTurnAroundTime==null)
			{
				mapAvgTurnAroundTime=InvTrackingReportDATA.GetTestTurnAroundTime(vo, userVO);
				request.getSession().setAttribute("mapAvgTurnAroundTime", mapAvgTurnAroundTime);
			}
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
		
		return mapAvgTurnAroundTime;
	}
	
	
	public static Boolean checkUnionFromOldTableRequired(String fromDateStr) {
		Boolean isUnionFromOldTableRequired=false;
		
		/*Set flag isUnionFromOldTableRequired=true if from date is before 90 days from today*/
		Date date = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -90);
		Date threeMonthPast = cal.getTime();
		try {
			int dateDiff=0;
			
			SimpleDateFormat format = new SimpleDateFormat( "dd-MMM-yyyy" );
			Date fromDate = format.parse( fromDateStr );
			//Date fromDate = new Date(fromDateStr);
			dateDiff=threeMonthPast.compareTo(fromDate);
	
			if(dateDiff>0) {
				isUnionFromOldTableRequired=true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return isUnionFromOldTableRequired;
	}
	
}
