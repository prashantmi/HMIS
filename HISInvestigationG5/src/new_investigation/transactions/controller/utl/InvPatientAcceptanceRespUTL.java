package new_investigation.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvPatientAcceptanceRespDATA;
import new_investigation.transactions.controller.data.InvPatientAcceptanceRespDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvPatientAcceptanceRespFB;
import new_investigation.transactions.controller.fb.InvPatientAcceptanceRespFB;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import hisglobal.utility.Entry;

public class InvPatientAcceptanceRespUTL extends ControllerUTIL {
	
	static String error = "Error Message Starts From Here [Added By Prashant] :";
	
	public static JsonObject AjaxGetPatAcceptanceReqnList(InvPatientAcceptanceRespFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvPatientAcceptanceRespVO invPatientAcceptanceRespVO = new InvPatientAcceptanceRespVO();
		Map mp=new HashMap();

		JsonObject jsonResponse = new JsonObject();
		JsonArray invPatAcceptanceReqnListJsonArr = new JsonArray();

		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);


			invPatientAcceptanceRespVO.setCollAreaCode(fb.getCollAreaCode());
			invPatientAcceptanceRespVO.setLabCode(fb.getLabCode());
			invPatientAcceptanceRespVO.setPatAcceptanceStatusCode(fb.getPatAcceptanceStatusCode());
			invPatientAcceptanceRespVO.setDateFiltersOrBoth(fb.getDateFiltersOrBoth());
			invPatientAcceptanceRespVO.setDateTypeCode(fb.getDateTypeCode());
			invPatientAcceptanceRespVO.setFromDate(fb.getFromDate());
			invPatientAcceptanceRespVO.setToDate(fb.getToDate());
			invPatientAcceptanceRespVO.setCrNo(fb.getCrNo());
			int dateTypeCode = Integer.parseInt(fb.getDateTypeCode());

			mp=InvPatientAcceptanceRespDATA.AjaxGetPatAcceptanceReqnList(invPatientAcceptanceRespVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			List<InvPatientAcceptanceRespVO> lstInvPatientAcceptanceRespVO_groupModified=(List<InvPatientAcceptanceRespVO>) mp.get(InvestigationConfig.LIST_EPISODE_VO);


			int i=0;
			for(InvPatientAcceptanceRespVO vo2 : lstInvPatientAcceptanceRespVO_groupModified) {
				
				JsonObject reqnJsonObj = new JsonObject();
				reqnJsonObj.addProperty("sno", ++i);


				String filterDateTypeName = "";
				String filterDateTypeDate = "";
				switch(dateTypeCode) {

				case 1: filterDateTypeName = "Requisition Date";
						filterDateTypeDate = vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate();
						break;
				case 2: filterDateTypeName = "Patient Acceptance Date";
						filterDateTypeDate = vo2.getPatAccepDate()==null?"-": vo2.getPatAccepDate();
						break;

				default: filterDateTypeName = "Requisition Date";
						filterDateTypeDate = vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate();
						break;
				}

				JsonObject reqnDataForGrouping = new JsonObject();
				//reqnDataForGrouping.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate());
				reqnDataForGrouping.addProperty("requisitionNo", vo2.getRequisitionNo());
				reqnDataForGrouping.addProperty("filterDateTypeCode", dateTypeCode);
				reqnDataForGrouping.addProperty("filterDateTypeName", filterDateTypeName);
				reqnDataForGrouping.addProperty("filterDateTypeDate", filterDateTypeDate);


				JsonObject reqnAdvisedNotes = new JsonObject();
				//vo2.getInstructionFlag()
				reqnAdvisedNotes.addProperty("instructionGeneric", vo2.getInstructionGeneric());
				reqnAdvisedNotes.addProperty("instructionFlag", "-");
				reqnAdvisedNotes.addProperty("chiefComplaintsName", vo2.getChiefComplaintsName());
				reqnAdvisedNotes.addProperty("diagnosisName", vo2.getDiagnosisName());
				reqnAdvisedNotes.addProperty("advisedNote", vo2.getAdvisedNote());
				reqnAdvisedNotes.addProperty("advisedByDocName", vo2.getAdvisedByDocName());
				reqnDataForGrouping.add("reqnAdvisedNotes", reqnAdvisedNotes);

				reqnDataForGrouping.addProperty("priorityCode",  vo2.getPriorityCode());
				
				reqnJsonObj.add("reqnDataForGrouping", reqnDataForGrouping);

				reqnJsonObj.addProperty("requisitionDate", vo2.getRequisitionDate()==null?"":vo2.getRequisitionDate());
				reqnJsonObj.addProperty("reqnCheckBox", "");
				reqnJsonObj.addProperty("crNo", vo2.getPatCRNo());
				reqnJsonObj.addProperty("patName", vo2.getPatName());
				reqnJsonObj.addProperty("patUnitName", vo2.getPatUnitName());
				reqnJsonObj.addProperty("patLabName", vo2.getPatLabName());
				reqnJsonObj.addProperty("requisitionNo", vo2.getRequisitionNo());
				reqnJsonObj.addProperty("requisitionDNo", vo2.getRequisitionDNo());
				reqnJsonObj.addProperty("testCode", vo2.getTestCode());
				reqnJsonObj.addProperty("groupCode", vo2.getGroupCode());
				reqnJsonObj.addProperty("patGender", vo2.getPatGender());
				reqnJsonObj.addProperty("patAge", vo2.getPatAge());
				reqnJsonObj.addProperty("patAgeGender", vo2.getPatAge()+" | "+vo2.getPatGender());
				String testName=vo2.getTestName()==null?"":vo2.getTestName().replaceAll("[\\{\\}\"]", "").replaceAll(",", ", ");
				String groupName=vo2.getGroupName()==null?"":vo2.getGroupName().replaceAll("[\\{\\}\"]", "").replaceAll("[\\{\\}\"]", "").replaceAll(",", ", ");
				reqnJsonObj.addProperty("testName", testName);
				reqnJsonObj.addProperty("groupName", groupName);
				reqnJsonObj.addProperty("testNameOrGroupName", groupName==null||groupName.equals("NA") || groupName.equals("")?testName:groupName);
				reqnJsonObj.addProperty("isRepeatCount", vo2.getIsRepeat());
				reqnJsonObj.addProperty("tempSampleNo", vo2.getTempSampleNo());
				reqnJsonObj.addProperty("accessionNo", vo2.getLabNo());
				reqnJsonObj.addProperty("tempSampleOrAccessionNo", vo2.getTempSampleNo()==null?vo2.getLabNo():vo2.getTempSampleNo());
				reqnJsonObj.addProperty("patStatus", vo2.getPatStatus());
				reqnJsonObj.addProperty("sendToMachineCheckBox", "");
				reqnJsonObj.addProperty("isMachineBasedTest", vo2.getIsMachineBasedTest());
				reqnJsonObj.addProperty("machineCode", vo2.getMachineCode());
				reqnJsonObj.addProperty("priorityCode", vo2.getPriorityCode());
				reqnJsonObj.addProperty("reqnPriority", vo2.getPriorityName());
				reqnJsonObj.addProperty("view", "view");
				
				reqnJsonObj.addProperty("patAccepDate", vo2.getPatAccepDate()==null?"-": vo2.getPatAccepDate());
				reqnJsonObj.addProperty("patBedName", vo2.getPatBedName()==null?"-": vo2.getPatBedName());
				reqnJsonObj.addProperty("patRoomName", vo2.getPatRoomName()==null?"-": vo2.getPatRoomName());
				reqnJsonObj.addProperty("patWardName", vo2.getPatWardName()==null?"-": vo2.getPatWardName());
				
				reqnJsonObj.addProperty("patBedOrRoom", vo2.getPatBedName()==null||vo2.getPatBedName().equals("-")?vo2.getPatRoomName(): vo2.getPatBedName());
				reqnJsonObj.addProperty("patWardOrBlock", vo2.getPatWardName()==null?"-": vo2.getPatWardName());
				
				reqnJsonObj.addProperty("reqnTestParamJson", "");

				
				invPatAcceptanceReqnListJsonArr.add(reqnJsonObj);
 			}

			jsonResponse.add("patAcceptanceReqnList", invPatAcceptanceReqnListJsonArr);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return jsonResponse;
	}
	
	public static boolean setPatientEssentials(InvPatientAcceptanceRespFB fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		InvPatientAcceptanceRespVO onlinePatientvo = new InvPatientAcceptanceRespVO();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp = new HashMap();

			onlinePatientvo.setFromDate(fb.getFromDate());
			onlinePatientvo.setToDate(fb.getToDate());
			onlinePatientvo.setPatCRNo(fb.getPatCRNo());
			onlinePatientvo.setLabCode(fb.getLabCode());
			onlinePatientvo.setSampleAreaName(fb.getSampleAreaName());
			onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());
			onlinePatientvo.setAcceptedToNotAccepted(fb.getAcceptedToNotAccepted());
			mp = InvPatientAcceptanceRespDATA.setPatientEssentials(onlinePatientvo, userVO);

			String val = "1";
			/* mp=InvResultEntryDATA.LabComboForResultEntry(invresultentryvo, userVO); */
			mp.put(InvestigationConfig.isFilterPatAcc, val);
			mp.put(InvestigationConfig.FILTER_LIST_PATACC, onlinePatientvo);

			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, onlinePatientvo);

			objStatus.add(Status.TRANSINPROCESS);
		} catch (HisRecordNotFoundException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		} catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		} finally {
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static void getUserList(InvPatientAcceptanceRespFB fb, HttpServletRequest request) {

		Status status = new Status();
		HttpSession session = request.getSession();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(request);
			Map<String, String> userList = new HashMap<String, String>();
			userList = InvPatientAcceptanceRespDATA.getUserList(userVO);
			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_USERLIST, userList);
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
	}

	public static void AjaxShowReqnDetails(InvPatientAcceptanceRespFB fb, HttpServletRequest request) {
		System.out.print("AjaxShowReqnDetails");

		Status status = new Status();
		HttpSession session = request.getSession();

		Map mp = new HashMap();
		boolean flag = false;
		String sampleAreaCode = fb.getSampleAreaCode();
		String staffImage = "";

		try {
			List<InvPatientAcceptanceRespVO> onlinePatientvo = null;
			InvPatientAcceptanceRespVO onlinePatientv = new InvPatientAcceptanceRespVO();
			List<InvPatientAcceptanceRespVO> onlinePatientvvv = new ArrayList();

			List<String> reqList = new ArrayList();

			List<String> staffDetails = new ArrayList();
			// fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			onlinePatientvo = (List<InvPatientAcceptanceRespVO>) session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
			String selectedCheckBoxValue = fb.getSelectedCheckbox();

			String[] arrSelectedRequisitions = selectedCheckBoxValue.split("@");

			for (InvPatientAcceptanceRespVO objPatientCollectionVO : onlinePatientvo) {
				objPatientCollectionVO.setAcceptedToNotAccepted(fb.getAcceptedToNotAccepted());
				String[] arrSelectedRequisitions1 = selectedCheckBoxValue.split("@");
				for (int p = 0; p < arrSelectedRequisitions1.length; p++) {
					String crNo1 = arrSelectedRequisitions1[p].split("#")[0];
					String reqNO1 = arrSelectedRequisitions1[p].split("#")[1];

					if (objPatientCollectionVO.getPatPuk().equals(crNo1)
							&& objPatientCollectionVO.getPatReqNo().equals(reqNO1)) {
						WebUTIL.populate(fb, objPatientCollectionVO);

						onlinePatientv = objPatientCollectionVO;
						onlinePatientvvv.add(onlinePatientv);
						flag = true;
					}

				}
				WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_PATIENT_VO, onlinePatientvvv);
			}

			for (String str : arrSelectedRequisitions)
				reqList.add(str);

			mp = InvPatientAcceptanceRespDATA.patientDetails(onlinePatientvvv, reqList, userVO);

			WebUTIL.setMapInSession(mp, request);

			fb.setPatType(onlinePatientv.getPatType());
			fb.setSampleAreaCode(sampleAreaCode);

			session.setAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE, "");

			status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	// Save Logic
	public static void savePatientDetails(InvPatientAcceptanceRespFB _fb, HttpServletRequest _request) {
		
		JsonObject jsonResponse = new JsonObject();
		JsonArray acceptedPatDataArr = new JsonArray();
        String isSuccess = "0";
        StringWriter sw = new StringWriter();
        
		Status objStatus = new Status();
		HttpSession session = _request.getSession();

		Map<String, Map<String, Map<String, List<InvPatientAcceptanceRespVO>>>> mp = new LinkedHashMap<String, Map<String, Map<String, List<InvPatientAcceptanceRespVO>>>>();
		try {
			Date sysdate = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO = getUserVO(_request);

			String[] selectedLabTestCodeArray = _fb.getChkSamplePatientOnSave();

			String acceptedToNotAccepted = _fb.getAcceptedToNotAccepted();

			int labRowCount = selectedLabTestCodeArray.length;

			for (int i = 0, k = 0; i < labRowCount; i++) {
				System.out.println("" + selectedLabTestCodeArray[i]);

			}

			for (int i = 0, k = 0; i < labRowCount; i++) {

				// Getting LabCodeValues from split array
				// chkVal Order crNo#requisitionNo#requisitionDNo //Please Ensure the
				// corresponding Changes before changing this order
				String[] testCodeArray = selectedLabTestCodeArray[i].split("#");
				System.out.println("selectedLabTestCodeArray --  " + selectedLabTestCodeArray.toString());
				if(testCodeArray.length>25) {

				String crNo = testCodeArray[0];
				String requisitionNo = testCodeArray[1];

				String requisitionDNo = testCodeArray[2];
				String labCode = testCodeArray[3];
				String billNo = testCodeArray[4];
				String testName = testCodeArray[5];
				String testCode = testCodeArray[6];
				String labNOConfig = testCodeArray[7];
				String grpcode = testCodeArray[10];

				int id = Integer.parseInt(testCodeArray[11]);

				String LabNoFormate = testCodeArray[12];
				String initDate = testCodeArray[13]; // attention
				String noofSegDigits = testCodeArray[14];
				String fromSeries = testCodeArray[15];
				String toSeries = testCodeArray[16];
				String initType = testCodeArray[17];
				String runningLabNo = testCodeArray[18];

				String configLab = testCodeArray[19];
				String configSeq = testCodeArray[20];
				String configType = testCodeArray[21];
				String configTest = testCodeArray[22];

				String acceptanceAreaCode = _fb.getSampleAreaCode();

				String islabNoAreawise = testCodeArray[24];

				// added by Prashant
				String acceptanceDate = testCodeArray[25];
				// String entryDate = _fb.getEntryDate();

				Map<String, Map<String, List<InvPatientAcceptanceRespVO>>> mpReqNo = mp.get(crNo);

				// First Time Insertion
				if (mpReqNo == null) {
					mpReqNo = new LinkedHashMap<String, Map<String, List<InvPatientAcceptanceRespVO>>>();

					Map<String, List<InvPatientAcceptanceRespVO>> mpPatient = new LinkedHashMap<String, List<InvPatientAcceptanceRespVO>>();

					List<InvPatientAcceptanceRespVO> lstPatient = new ArrayList<InvPatientAcceptanceRespVO>();
					InvPatientAcceptanceRespVO voPatient = new InvPatientAcceptanceRespVO();

					// Setting VO Values from labStringArray
					voPatient.setPatCRNo(crNo);
					voPatient.setRequisitionNo(requisitionNo);
					voPatient.setRequisitionDNo(requisitionDNo);
					voPatient.setLabCode(labCode);
					voPatient.setBillNo(billNo);
					voPatient.setTestName(testName);
					voPatient.setTestCode(testCode);

					if (!labNOConfig.equals("2"))

					{
						voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
					} else {
						voPatient.setLabNoConfiguration(labNOConfig);
					}

					voPatient.setCheckAutoLabConfig(labNOConfig);
					voPatient.setAccepted(_fb.getAccepted()[id]);

					voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
					voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
					voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);
					voPatient.setMobileNo(_fb.getMobileNo());
					voPatient.setEmailId(_fb.getEmailId());
					voPatient.setAddress(_fb.getAddress());
					voPatient.setPatCategoryCode(_fb.getPatCategoryCode() == null ? "" : _fb.getPatCategoryCode());

					voPatient.setLabNoFormat(LabNoFormate);
					voPatient.setGroupCode(grpcode);
					voPatient.setInitDate(initDate); // attention
					voPatient.setNoOfSeqDigit(noofSegDigits);
					voPatient.setFromSeries(fromSeries);
					voPatient.setToSeries(toSeries);
					voPatient.setInitType(initType);
					voPatient.setRunningLabNo(runningLabNo);
					voPatient.setConfigLab(configLab);
					voPatient.setConfigSeq(configSeq);
					voPatient.setConfigType(configType);
					voPatient.setConfigTest(configTest);
					// added by chandan
					voPatient.setSampleAreaCode(acceptanceAreaCode);
					voPatient.setIslabNoAreaWise(islabNoAreawise);

					// added by krishnan nema on 08/10/2018
					voPatient.setMachineCode(_fb.getSelectedmachineCode());

					// added by Prashant
					voPatient.setAcceptanceDate(acceptanceDate);
					voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
					// voPatient.setEntryDate(entryDate);
					voPatient.setUserHasPermission(_fb.getUserHasPermission());

					lstPatient.add(voPatient);

					mpPatient.put(labCode, lstPatient);

					// Putting Map of Patients in Map of Requisitions
					mpReqNo.put(requisitionNo, mpPatient);

				} else {

					Map<String, List<InvPatientAcceptanceRespVO>> mpPatient = mpReqNo.get(requisitionNo);

					// First Time Insertion
					if (mpPatient == null) {
						mpPatient = new LinkedHashMap<String, List<InvPatientAcceptanceRespVO>>();

						List<InvPatientAcceptanceRespVO> lstPatient = new ArrayList<InvPatientAcceptanceRespVO>();
						InvPatientAcceptanceRespVO voPatient = new InvPatientAcceptanceRespVO();

						// Setting VO Values from labStringArray
						voPatient.setPatCRNo(crNo);
						// voSample.setPatientCode(PatientCode);
						voPatient.setRequisitionDNo(requisitionDNo);
						voPatient.setRequisitionNo(requisitionNo);
						voPatient.setLabCode(labCode);
						// Still Some values need to be inserted
						voPatient.setBillNo(billNo);
						voPatient.setTestName(testName);
						voPatient.setTestCode(testCode);
						voPatient.setAccepted(_fb.getAccepted()[id]);

						if (!labNOConfig.equals("2"))

						{
							voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
						} else {
							voPatient.setLabNoConfiguration(labNOConfig);
						}

						voPatient.setCheckAutoLabConfig(labNOConfig);
						voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
						voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);

						voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
						voPatient.setMobileNo(_fb.getMobileNo());
						voPatient.setEmailId(_fb.getEmailId());
						voPatient.setAddress(_fb.getAddress());
						voPatient.setLabNoFormat(LabNoFormate);
						voPatient.setGroupCode(grpcode);
						voPatient.setPatCategoryCode(_fb.getPatCategoryCode() == null ? "" : _fb.getPatCategoryCode());

						voPatient.setInitDate(initDate); // attention
						voPatient.setNoOfSeqDigit(noofSegDigits);
						voPatient.setFromSeries(fromSeries);
						voPatient.setToSeries(toSeries);
						voPatient.setInitType(initType);
						voPatient.setRunningLabNo(runningLabNo);
						voPatient.setConfigLab(configLab);
						voPatient.setConfigSeq(configSeq);
						voPatient.setConfigType(configType);
						voPatient.setConfigTest(configTest);

						// added by chandan
						voPatient.setSampleAreaCode(acceptanceAreaCode);
						voPatient.setIslabNoAreaWise(islabNoAreawise);

						// added by Prashant
						voPatient.setAcceptanceDate(acceptanceDate);
						voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
						voPatient.setUserHasPermission(_fb.getUserHasPermission());

						// Adding List of PatientVO<=>RequisitionDNo's
						lstPatient.add(voPatient);

						mpPatient.put(labCode, lstPatient);
					} else {

						List<InvPatientAcceptanceRespVO> lstPatient = mpPatient.get(labCode);
						if (lstPatient == null || lstPatient.size() == 0) // First Time Insertion
						{
							lstPatient = new ArrayList<InvPatientAcceptanceRespVO>();
							InvPatientAcceptanceRespVO voPatient = new InvPatientAcceptanceRespVO();

							// Setting VO Values from labStringArray
							voPatient.setPatCRNo(crNo);
							// voPatient.setSampleCode(sampleCode);
							voPatient.setRequisitionDNo(requisitionDNo);
							voPatient.setRequisitionNo(requisitionNo);
							voPatient.setLabCode(labCode);
							voPatient.setBillNo(billNo);
							voPatient.setTestName(testName);
							voPatient.setTestCode(testCode);
							voPatient.setAccepted(_fb.getAccepted()[id]);

							if (!labNOConfig.equals("2"))

							{
								voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
							} else {
								voPatient.setLabNoConfiguration(labNOConfig);
							}
							voPatient.setCheckAutoLabConfig(labNOConfig);
							voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
							voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);

							voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
							voPatient.setMobileNo(_fb.getMobileNo());
							voPatient.setEmailId(_fb.getEmailId());
							voPatient.setAddress(_fb.getAddress());
							voPatient.setLabNoFormat(LabNoFormate);
							voPatient.setGroupCode(grpcode);
							voPatient.setPatCategoryCode(
									_fb.getPatCategoryCode() == null ? "" : _fb.getPatCategoryCode());

							voPatient.setInitDate(initDate);
							voPatient.setNoOfSeqDigit(noofSegDigits);
							voPatient.setFromSeries(fromSeries);
							voPatient.setToSeries(toSeries);
							voPatient.setInitType(initType);
							voPatient.setRunningLabNo(runningLabNo);

							voPatient.setConfigLab(configLab);
							voPatient.setConfigSeq(configSeq);
							voPatient.setConfigType(configType);
							voPatient.setConfigTest(configTest);
							// added by chandan
							voPatient.setSampleAreaCode(_fb.getSampleAreaCode());
							voPatient.setIslabNoAreaWise(islabNoAreawise);
							// Still Some values need to be inserted

							// added by Prashant
							voPatient.setAcceptanceDate(acceptanceDate);
							voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
							voPatient.setUserHasPermission(_fb.getUserHasPermission());

							// Adding List of PatientVO<=>RequisitionDNo's
							lstPatient.add(voPatient);

						} else {
							InvPatientAcceptanceRespVO voPatient = new InvPatientAcceptanceRespVO();

							// Setting VO Values from labStringArray
							voPatient.setPatCRNo(crNo);
							// voPatient.setSampleCode(sampleCode);
							voPatient.setRequisitionDNo(requisitionDNo);
							voPatient.setRequisitionNo(requisitionNo);
							voPatient.setLabCode(labCode);
							voPatient.setBillNo(billNo);
							voPatient.setTestName(testName);
							voPatient.setTestCode(testCode);

							voPatient.setAccepted(_fb.getAccepted()[id]);

							if (!labNOConfig.equals("2"))

							{
								voPatient.setLabNoConfiguration(_fb.getLabNoConfiguration()[id]);
							} else {
								voPatient.setLabNoConfiguration(labNOConfig);
							}
							voPatient.setCheckAutoLabConfig(labNOConfig);
							voPatient.setRejectionAction(_fb.getRejectionAction()[id]);
							voPatient.setExtrarejectionReason(_fb.getExtrarejectionReason()[id]);

							voPatient.setRejectionReason(_fb.getRejectionReason()[id]);
							voPatient.setMobileNo(_fb.getMobileNo());
							voPatient.setEmailId(_fb.getEmailId());
							voPatient.setAddress(_fb.getAddress());
							voPatient.setLabNoFormat(LabNoFormate);
							voPatient.setGroupCode(grpcode);
							voPatient.setPatCategoryCode(
							_fb.getPatCategoryCode() == null ? "" : _fb.getPatCategoryCode());

							voPatient.setInitDate(initDate);
							voPatient.setNoOfSeqDigit(noofSegDigits);
							voPatient.setFromSeries(fromSeries);
							voPatient.setToSeries(toSeries);
							voPatient.setInitType(initType);
							voPatient.setRunningLabNo(runningLabNo);

							voPatient.setConfigLab(configLab);
							voPatient.setConfigSeq(configSeq);
							voPatient.setConfigType(configType);
							voPatient.setConfigTest(configTest);
							// added by chandan
							voPatient.setSampleAreaCode(_fb.getSampleAreaCode());
							voPatient.setIslabNoAreaWise(islabNoAreawise);

							// added by Prashant
							voPatient.setAcceptanceDate(acceptanceDate);
							voPatient.setAcceptedToNotAccepted(acceptedToNotAccepted);
							// voPatient.setEntryDate(entryDate);
							voPatient.setUserHasPermission(_fb.getUserHasPermission());

							lstPatient.add(voPatient);
						}

						mpPatient.put(labCode, lstPatient);

					}

					mpReqNo.put(requisitionNo, mpPatient);

				} // end main else

				mp.put(crNo, mpReqNo);
			}
			} // end for loop

			jsonResponse = InvPatientAcceptanceRespDATA.savePatientDetails(mp, _userVO);
			
			StringBuilder str = new StringBuilder();
			JsonArray objarr= new JsonArray();
			objarr = (JsonArray) jsonResponse.get("acceptedPatDataArr");
			for (JsonElement key : objarr) {
				str.append("<br>");
				str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
						+ "Patient Acceptance ::" + "</font>");
				str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
				str.append(((String) key.toString()) + "</font>");
				
				JsonObject acceptedPat = new JsonObject();
				acceptedPat.addProperty("crNo", "");
				acceptedPat.addProperty("requisitionNo", "");
				acceptedPat.addProperty("requisitionDNo", "");
				acceptedPat.addProperty("labNo", "");
				acceptedPat.addProperty("isSuccess", "1");
				
				acceptedPatDataArr.add(acceptedPat);
			}

			objStatus.add(Status.DONE, str.toString(), "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>" + "" + "</font>");
			
			List<InvPatientAcceptanceRespVO> listInvPatientAcceptanceRespVO = null;
			listInvPatientAcceptanceRespVO = (List<InvPatientAcceptanceRespVO>) session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
			
			//WebUTIL.setAttributeInSession(_request, InvestigationConfig.LIST_PATIENT_VO, listInvPatientAcceptanceRespVO);
			session.removeAttribute(InvestigationConfig.LIST_PATIENT_VO);
			
			isSuccess = "1";
			
		} catch (HisRecordNotFoundException e) {
			isSuccess = "0";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		} catch (HisDataAccessException e) {
			isSuccess = "0";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			isSuccess = "0";
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			System.out.println(e.getMessage());
		} catch (HisException e) {
			isSuccess = "0";
			objStatus.add(Status.ERROR, "", "Error");
			System.out.println(e.getMessage());
		} finally {
			jsonResponse.addProperty("responseFromProcess", "savePatAcceptance");
			//jsonResponse.add("acceptedPatDataArr", acceptedPatDataArr);
			jsonResponse.addProperty("closeIframe", "1");
			jsonResponse.addProperty("isSuccess", isSuccess);
            jsonResponse.addProperty("errorMsg", error + sw.toString());
            session.setAttribute("patientAcceptanceSessionJson", jsonResponse.toString());
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static void getEssential(InvPatientAcceptanceRespFB fb, HttpServletRequest request) {
		Status objStatus = new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		InvPatientAcceptanceRespVO onlinePatientvo = new InvPatientAcceptanceRespVO();
		String[] multipleHospitalArray = new String[1];
		multipleHospitalArray[0] = ControllerUTIL.getUserVO(request).getHospitalCode();
		System.out.println("Logged as Hospital Code" + multipleHospitalArray[0]);

		try {
			Map mp = new HashMap();

			Map mpp = new HashMap();

			// updated by krishnan nema on 08/10/2018
			Map mapMachineCombo = new HashMap();

			ControllerUTIL.setSysdate(request);
			Date dt = (Date) request.getSession().getAttribute(Config.SYSDATEOBJECT);
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT, dt);
			onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());
			mpp = InvPatientAcceptanceRespDATA.setPatientEssentialsOnLoad(onlinePatientvo, userVO);
			mp = InvPatientAcceptanceRespDATA.LabCombos(onlinePatientvo, userVO);
			
			onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());
			mapMachineCombo = InvPatientAcceptanceRespDATA.MachineCombos(onlinePatientvo, userVO);

			WebUTIL.setMapInSession(mp, request);

			WebUTIL.setMapInSession(mpp, request);

			// updated by krishnan nema on 08/10/2018
			WebUTIL.setMapInSession(mapMachineCombo, request);

			HelperMethods.populate(fb, onlinePatientvo);

		} catch (HisRecordNotFoundException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Data Not Found");
		} catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}

	}

	/**
	 * Checking Duplicate Sample No
	 */
	public static StringBuffer checkDailyLabNoDuplicacy(InvPatientAcceptanceRespFB fb,
			HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try {
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);

			InvPatientAcceptanceRespVO onlinePatientvo = new InvPatientAcceptanceRespVO();

			// Setting Area Code and Sample No

			onlinePatientvo.setLabNoConfiguration(fb.getStrDailyLabNo());

			boolean isTempSamplePresent = InvPatientAcceptanceRespDATA.checkDailyLabNoDuplicacy(onlinePatientvo,
					voUser);
			sbAjaxRes.append(isTempSamplePresent);

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
			new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
			
		} finally {
		}
		return sbAjaxRes;
	}

	public static StringBuffer checkAutoGenFormate(InvPatientAcceptanceRespFB fb, HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		String strFormate = "";
		Map mp = new HashMap();
		try {
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);

			InvPatientAcceptanceRespVO onlinePatientvo = new InvPatientAcceptanceRespVO();

			// LabCod ,TestCode,patType And TempSampleNo
			onlinePatientvo.setLabCode(fb.getLabCode());
			onlinePatientvo.setTestCode(fb.getTmpTestCode());
			onlinePatientvo.setPatType(fb.getPatType());
			onlinePatientvo.setSampleAreaCode(fb.getSampleAreaCode());

			strFormate = InvPatientAcceptanceRespDATA.checkAutoGenFormate(onlinePatientvo, voUser);

			sbAjaxRes.append(strFormate);
		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
			new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
			
		} finally {
		}
		return sbAjaxRes;
	}

}
