package new_investigation.transactions.controller.utl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvResultEntryDATA;
import new_investigation.transactions.controller.data.InvResultValidationDATA;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.data.OnlinePatientAcceptanceDATA;
import new_investigation.transactions.controller.data.SampleAcceptanceDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.fb.SampleAcceptanceFB;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.SampleAcceptanceVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.ResultEntryVOGroupByValidatedBy;

public class InvResultValidationUTIL extends ControllerUTIL {
	public static String height = "50";

	public static void getEssential(InvResultValidationFB fb, HttpServletRequest request) {
		Status objStatus = new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
		ResultEntryVO InvResultEntryVO = new ResultEntryVO();

		try {
			Map mp = new HashMap();
			Map mpp = new HashMap();

			ControllerUTIL.setSysdate(request);
			Date dt = (Date) request.getSession().getAttribute(Config.SYSDATEOBJECT);
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT, dt);

			String tDate = WebUTIL.getCustomisedSysDate((Date) request.getSession().getAttribute(Config.SYSDATEOBJECT),
					"dd-MMM-yyyy");
			InvResultEntryVO.setFromDate(tDate);
			InvResultEntryVO.setToDate(tDate);
			InvResultEntryVO.setLabCode("%");
			InvResultEntryVO.setNewEntry("1");
			InvResultEntryVO.setOnLoadValue("ONLOAD");
			InvResultEntryVO.setSearchBy("1");
			InvResultEntryVO.setSampleAreaCode(fb.getSampleAreaCode());
			mp = InvResultValidationDATA.LabComboForResultValidation(InvResultEntryVO, userVO);
			WebUTIL.setMapInSession(mp, request);

			mpp = InvResultValidationDATA.setPatientResultValidationEssentials(InvResultEntryVO, userVO);
			mpp.put(InvestigationConfig.LIST_SAMPLE_COLLECTION_VO, "1");
			WebUTIL.setMapInSession(mpp, request);

			fb.setLabCode("%");

			objStatus.add(Status.TRANSINPROCESS);

			// HelperMethods.populate(fb, InvResultEntryVO);
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

	public static boolean setPatientEssentials(InvResultValidationFB fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		ResultEntryVO InvResultEntryVO = new ResultEntryVO();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp = new HashMap();
			InvResultEntryVO.setFromDate(fb.getFromDate());
			InvResultEntryVO.setToDate(fb.getToDate());
			InvResultEntryVO.setFromCollDate(fb.getFromCollDate());
			InvResultEntryVO.setToCollDate(fb.getToCollDate());
			InvResultEntryVO.setPatCRNo(fb.getPatCRNo());
			InvResultEntryVO.setLabCode(fb.getLabCode());
			InvResultEntryVO.setTestWiseCode(fb.getTestWiseCode());
			InvResultEntryVO.setFromLabNo(fb.getFromLabNo());
			InvResultEntryVO.setToLabNo(fb.getToLabNo());
			InvResultEntryVO.setFromSampleNo(fb.getFromSampleNo());
			InvResultEntryVO.setToSampleNo(fb.getToSampleNo());
			InvResultEntryVO.setGenerationType(fb.getGenerationType());
			InvResultEntryVO.setTestGroupCode(fb.getTestGroupCodeWise());
			InvResultEntryVO.setOnLoadValue(fb.getOnLoadValue());
			InvResultEntryVO.setNewEntry(fb.getNewEntry());
			InvResultEntryVO.setTempPatName(fb.getTempPatName());
			InvResultEntryVO.setSearchBy(fb.getSearchBy());
			InvResultEntryVO.setSampleAreaCode(fb.getSampleAreaCode());

			InvResultEntryVO.setSampleAreaName(fb.getSampleAreaName());
			mp = InvResultValidationDATA.setPatientResultValidationEssentials(InvResultEntryVO, userVO);
			mp.put(InvestigationConfig.FILTER_LIST, InvResultEntryVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COLLECTION_VO, "1");

			// session.setAttribute("isFilter", "1");
			String val = "1";
			/* mp=InvResultEntryDATA.LabComboForResultEntry(invresultentryvo, userVO); */
			mp.put(InvestigationConfig.isFilter, val);

			WebUTIL.setMapInSession(mp, _request);

			// HelperMethods.populate(fb, InvResultEntryVO);

			objStatus.add(Status.TRANSINPROCESS);
		} catch (HisRecordNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		} catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		} finally {
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static void showResultValidationPatDetails(InvResultValidationFB fb, HttpServletRequest request) {
		Status status = new Status();
		HttpSession session = request.getSession();

		Map mp = new HashMap();
		boolean flag = false;
		try {
			List<ResultEntryVO> lstinvresultentryvo_old = null;
			ResultEntryVO invresultentryv = new ResultEntryVO();
			List<ResultEntryVO> lstInvResultEntryVO = new ArrayList<ResultEntryVO>();
			List<String> reqList = new ArrayList();
			// fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			lstinvresultentryvo_old = (List<ResultEntryVO>) session
					.getAttribute(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
			String selectedCheckBoxValue = fb.getSelectedCheckbox();
			String hosCode = userVO.getHospitalCode();
			String[] arrSelectedRequisitions = selectedCheckBoxValue.split("@");
			for (int i = 0; i < arrSelectedRequisitions.length; i++) {
				for (ResultEntryVO resultEntryVO : lstinvresultentryvo_old) {
					resultEntryVO.setHospitalcode(hosCode);
					// fetching the list <parameter, loinc>

					// InvResultEntryDATA.fetchLoincCode(objPatientCollectionVO,userVO);

					String crNo = arrSelectedRequisitions[i].split("#")[0];
					String reqNO = arrSelectedRequisitions[i].split("#")[1];
					String reqDNo = arrSelectedRequisitions[i].split("#")[2];
					String grpCode = arrSelectedRequisitions[i].split("#")[3];

					if (resultEntryVO.getGroupCode() == null) {
						if (resultEntryVO.getPatCRNo().equals(crNo) && resultEntryVO.getRequisitionNo().equals(reqNO)
								&& resultEntryVO.getRequisitionDNo().equals(reqDNo)) {
							///// WebUTIL.populate(fb,objPatientCollectionVO);
							// WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO,objPatientCollectionVO);

							ResultEntryVO rvo = new ResultEntryVO(null, resultEntryVO.getTestCode(), null, null);
							// rvo.setSessionId("243434");
							// rvo.setToviewonly(fb.getToviewonly());
							rvo.setToviewonly(fb.getToviewonly());
							rvo.setRequisitionDNo(reqDNo);
							rvo.setSampleNo(resultEntryVO.getSampleNo());
							rvo.setFinalRemarkReqd(resultEntryVO.getFinalRemarkReqd());
							rvo.setFinalRemarkString(resultEntryVO.getFinalRemarkString());
							rvo.setLabCode(resultEntryVO.getLabCode());
							rvo.setSampleCode(resultEntryVO.getSampleCode());
							rvo.setUomCode(resultEntryVO.getUomCode());
							rvo.setHospitalcode(resultEntryVO.getHospitalcode());
							rvo.setEpisodeCode(resultEntryVO.getEpisodeCode());
							rvo.setRequisitionNo(resultEntryVO.getRequisitionNo());
							rvo.setSampleNo(resultEntryVO.getSampleNo());
							rvo.setPatAge(resultEntryVO.getPatAge());
							rvo.setPatGender(resultEntryVO.getPatGender());
							/*
							 * //Added by PrashantMi rvo.setIsRepeat(resultEntryVO.getIsRepeat());
							 */
							List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();
							lrvo.add(rvo);
							resultEntryVO.setResultEntryVOListValidatedBy(lrvo);
							lstInvResultEntryVO.add(resultEntryVO);
							// invresultentryv=objPatientCollectionVO;
							// ++i;
						}
					} else {
						if (resultEntryVO.getPatCRNo().equals(crNo) && resultEntryVO.getRequisitionNo().equals(reqNO)
								&& resultEntryVO.getGroupCode().equals(grpCode)) {
							///// WebUTIL.populate(fb,objPatientCollectionVO);
							// WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO,objPatientCollectionVO);

							ResultEntryVO rvo = new ResultEntryVO(null, resultEntryVO.getTestCode(), null, null);
							// rvo.setSessionId("243434");
							rvo.setToviewonly(fb.getToviewonly());
							rvo.setRequisitionDNo(resultEntryVO.getRequisitionDNo());
							rvo.setSampleNo(resultEntryVO.getSampleNo());
							rvo.setHospitalcode(resultEntryVO.getHospitalcode());
							rvo.setFinalRemarkReqd(resultEntryVO.getFinalRemarkReqd());
							rvo.setFinalRemarkString(resultEntryVO.getFinalRemarkString());
							rvo.setLabCode(resultEntryVO.getLabCode());
							rvo.setSampleCode(resultEntryVO.getSampleCode());
							rvo.setUomCode(resultEntryVO.getUomCode());
							rvo.setEpisodeCode(resultEntryVO.getEpisodeCode());
							rvo.setRequisitionNo(resultEntryVO.getRequisitionNo());
							rvo.setSampleNo(resultEntryVO.getSampleNo());
							rvo.setHospitalcode(resultEntryVO.getHospitalcode());
							rvo.setPatGender(resultEntryVO.getPatGender());
							rvo.setPatAge(resultEntryVO.getPatAge());
							/*
							 * //Added by PrashantMi rvo.setIsRepeat(resultEntryVO.getIsRepeat());
							 */
							List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();

							rvo.setGroupCode(resultEntryVO.getGroupCode());
							rvo.setDynamnicTemplateResultEntryGroup(
									resultEntryVO.getDynamnicTemplateResultEntryGroup());
							rvo.setDynamicTemplatePrintedGroup(resultEntryVO.getDynamicTemplatePrintedGroup());

							lrvo.add(rvo);
							resultEntryVO.setResultEntryVOListValidatedBy(lrvo);
							lstInvResultEntryVO.add(resultEntryVO);
							// invresultentryv=objPatientCollectionVO;
							// ++i;
						}

					}
				}

			}

			List<ResultEntryVO> lstInvResultEntryTemplateVO = null;
			lstInvResultEntryTemplateVO = TemplateProcessingUSE.groupSelectedWorkOrders(lstInvResultEntryVO, session);
			// System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
			mp.put(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES,
					lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getAutoList());

			mp.put(InvestigationConfig.LIST_RESULT_ENTRY, lstInvResultEntryTemplateVO);

			session.setAttribute("VOLIST", lstInvResultEntryTemplateVO);

			Map<String, String> objMapResEntrypid = new LinkedHashMap<String, String>();

			Map<String, List<ResultEntryVO>> objMapResVal = new LinkedHashMap<String, List<ResultEntryVO>>();
			String pidno = "";
			String ipdshow = "";
			for (int j = 0; j < lstInvResultEntryTemplateVO.size(); j++) {
				ResultEntryVO objResultEntryVO = lstInvResultEntryTemplateVO.get(j);
				List<ResultEntryVO> lstTempResultEntryVO = null;
				String strSampleNo = objResultEntryVO.getRequisitionNo() + objResultEntryVO.getSampleNo();

				lstTempResultEntryVO = objMapResVal.get(strSampleNo);

				if (lstTempResultEntryVO == null) {
					lstTempResultEntryVO = new ArrayList<ResultEntryVO>();
					lstTempResultEntryVO.add(objResultEntryVO);

					if (pidno.equals("")) {
						if (objResultEntryVO.getIspidshow().equals("1")) {
							ipdshow = objResultEntryVO.getIspidshow();
							pidno = objResultEntryVO.getPidno();
						}

					}

				} else {
					lstTempResultEntryVO.add(objResultEntryVO);

					if (pidno.equals("")) {
						if (objResultEntryVO.getIspidshow().equals("1")) {
							ipdshow = objResultEntryVO.getIspidshow();
							pidno = objResultEntryVO.getPidno();
						}

					}

				}

				objMapResVal.put(strSampleNo, lstTempResultEntryVO);

				if (pidno != null && !pidno.equals(""))
					objMapResEntrypid.put(strSampleNo, pidno);

				ipdshow = "";
				pidno = "";

			}

			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO, objMapResVal);
			mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO_PID, objMapResEntrypid);

			// mp=InvResultEntryDATA.patientDetails(invresultentryv,reqList, userVO);

			WebUTIL.setMapInSession(mp, request);

			status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static void getResultValidationTemplateForSelectedWorkOrders(HttpServletRequest request,
			InvResultValidationFB fb) throws Exception {
		// TODO Auto-generated method stub
		// For testing purpose, hardcoding the VO
		ResultEntryVOGroupByValidatedBy rv = new ResultEntryVOGroupByValidatedBy();
		rv.setClinicianName("clinicianName");
		// rv.setDetpUnitCode(detpUnitCode);
		// rv.setLabNo(labNo);
		rv.setLaboratoryCode("1");
		rv.setLaboratoryName("af");
		rv.setPatCRNo("232525235");
		rv.setPatGender("1");
		rv.setPatName("ss");
		rv.setPatVisitNo("1");
		rv.setRequisitionDate("2222222");
		rv.setRequisitionDNo("13123123");
		rv.setRequisitionTypeCode("1");
		// rv.setRequisitionTypeName(requisitionTypeName);
		ResultEntryVO rvo = new ResultEntryVO(null, "10009", null, null);
		rvo.setSessionId("243434");
		rvo.setRequisitionDNo("dfdfdf");

		List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();
		lrvo.add(rvo);
		rv.setResultEntryVOListValidatedBy(lrvo);
		// rv.setSampleName(sampleName);
		rv.setSampleNo("121");
		rv.setTestCode("10020");
		rv.setTestName("test name");
		rv.setUserSampleNo("12");
		// rv.setVisitDate(visitDate);

		List<ResultEntryVOGroupByValidatedBy> selectedWorkOrderList = null;
		// changed
		List<ResultEntryVOGroupByValidatedBy> displayList = null;// (List<ResultEntryVOGroupByValidatedBy>)request.getSession().getAttribute("PAGELIST");

		HttpSession session = request.getSession();
		try {
			int j = 0;
			if (fb.getSelectedWorkOrderNo() != null) {
				for (String i : fb.getSelectedWorkOrderNo()) {
					if (selectedWorkOrderList == null)
						selectedWorkOrderList = new ArrayList<ResultEntryVOGroupByValidatedBy>();

					selectedWorkOrderList.add(displayList.get(Integer.parseInt(i)));

				}
			}

			// changed
			selectedWorkOrderList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
			selectedWorkOrderList.add(rv);
			/// selectedWorkOrderList=TemplateProcessingUSE.groupSelectedWorkOrders(selectedWorkOrderList,
			/// session);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().removeAttribute("PAGELIST");
		System.out.println(
				selectedWorkOrderList.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
		request.getSession().setAttribute("SELECTEDWORKORDERLIST", selectedWorkOrderList);
		// LOGGER_INV.log(Level.INFO,"size of selected workorder =
		// "+selectedWorkOrderList.size());

	}

	// Save Logic
	public static void saveResultValidationPatientDetails(InvResultValidationFB _fb, HttpServletRequest _request) {

		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		Map mp = new HashMap();
		HashMap<String, String> mpParaLoinc = new HashMap();
		int SizeMapOne = 0;
		HashMap<String, String> fetchValue = new HashMap();
		List<HashMap> newmaplist = new ArrayList<HashMap>();
		String refRange = "";
		String deptunitcode = "";
		String patName = "";
		String CrNo = "";
		String testCode = "";
		String sampleNo = "";
		String labCode = "";
		String patAge = "";
		String patGender = "";
		String reportAvailableAfter = "";
		String patVisitDat = "";
		String patVisitNo = "";
		String labNo = "";
		String episodeCode = "";
		String deptCode = "";
		String deptUnitCode = "";
		String reqType = "";
		String testName = "";
		String labName = "";
		String sampleName = "";
		String tempSampleNo = "";
		String groupCode = "";
		String patunitname = "";
		String deptcode = "";
		String requisitionDNo = "";
		String saveReval = "";
		String visitReason;

		_request.removeAttribute("getuploadedfiledata");

		try {
			Date sysdate = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			UserVO _userVO = getUserVO(_request);
			String newEntry = _fb.getNewEntry();
			/////////////////////////////////////////////////////////// template session
			/////////////////////////////////////////////////////////// received////////////////////////
			List<ResultEntryVO> lstInvResultEntryTemplateVO = null;
			lstInvResultEntryTemplateVO = (ArrayList) _request.getSession()
					.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY);
			int tempLength = lstInvResultEntryTemplateVO.size();

			///////////////////////////////////////////////////////// template session code
			///////////////////////////////////////////////////////// ends//////////////////////////

			List<ResultEntryVO> invResultEntryVO = new ArrayList<ResultEntryVO>();
			List<ResultEntryVO> invResultValidationForParameterDtlVO = new ArrayList<ResultEntryVO>();

			String[] selectedLabTestCodeArray = _fb.getChkResultValidationPatient();

			String[] selectedFbValue = _fb.getResultValidationTemplateValueWithHash().split("@");

			int ResultValidationTemplateValueWithHash = selectedFbValue.length;// getting Fb Values For Selected Check
																				// Box

			int labRowCount = selectedLabTestCodeArray.length;

			Map<String, String> mpp = new HashMap<String, String>();

			String dataforuploadfiles = _fb.getFileuploaddatabase64();

			if (dataforuploadfiles != null && !dataforuploadfiles.equals("") && dataforuploadfiles.contains("#@#@")) {
				String data[] = dataforuploadfiles.split("#@#@");

				for (int k = 0; k < data.length; k++) {
					String datas = data[k];

					String datass[] = datas.split("@@");

					String reqdno = datass[0];
					String filename = datass[1];
					String content = datass[2];
					String testparacode = datass[3];

					mpp.put(reqdno + testparacode, filename + "@@" + content);
				}

			} else if (dataforuploadfiles != null && !dataforuploadfiles.equals("")) {
				String data[] = dataforuploadfiles.split("@@");

				String reqdno = data[0];
				String filename = data[1];
				String content = data[2];
				String testparacode = data[3];

				mpp.put(reqdno + testparacode, filename + "@@" + content);

			}

			_request.setAttribute("getuploadedfiledata", mpp);

			for (int i = 0; i < labRowCount; i++) {

				ResultEntryVO voResultValidation = new ResultEntryVO();

				String[] splittedTests = null;
				if (selectedLabTestCodeArray[i].contains("!")) {
					splittedTests = selectedLabTestCodeArray[i].split("!");
				}
				String patReqNo = null;

				// Getting chkBoxValues from split array
				// chkVal Order packingListNO#sampleNo#testCode#testName#labName#strReqDNo
				// //Please Ensure the corresponding Changes before changing this order

				if (splittedTests != null && splittedTests.length > 0) {
					for (String temp : splittedTests) {
						voResultValidation = new ResultEntryVO();
						String[] testCodeArray = temp.split("#");

						CrNo = testCodeArray[0];
						patReqNo = testCodeArray[1];
						requisitionDNo = testCodeArray[2];
						testCode = testCodeArray[3];

						sampleNo = testCodeArray[4];
						labCode = testCodeArray[5];
						patAge = testCodeArray[6];
						patGender = testCodeArray[7];
						reportAvailableAfter = testCodeArray[8];
						patVisitDat = testCodeArray[9];
						patVisitNo = testCodeArray[10];
						labNo = testCodeArray[11];
						episodeCode = testCodeArray[12];
						deptCode = testCodeArray[13];
						deptUnitCode = testCodeArray[14];
						reqType = testCodeArray[15];
						testName = testCodeArray[17];
						labName = testCodeArray[18];
						sampleName = testCodeArray[19];
						tempSampleNo = testCodeArray[20];
						groupCode = testCodeArray[21];
						String isPrintedWithDynamicGroup = testCodeArray[22];
						patName = testCodeArray[23];
						refRange = testCodeArray[24];
						deptunitcode = testCodeArray[25];
						deptcode = testCodeArray[26];
						tempSampleNo = testCodeArray[27];
						String commentsboxxval = testCodeArray[28];
						visitReason = testCodeArray[29];
						// visitReason=_fb.getVisitReason();

						// by default mot to be sent to reval process
						voResultValidation.setSaveReVal("0");
						if (_fb.getSaveReVal() != null)
							for (String tempSaveReVal : _fb.getSaveReVal()) {
								if (tempSaveReVal.equals(CrNo + "#" + patReqNo))
									voResultValidation.setSaveReVal("1");
								else
									voResultValidation.setSaveReVal("0");

							}

						voResultValidation.setPatCRNo(CrNo);
						voResultValidation.setPatCRNo(CrNo);
						voResultValidation.setRequisitionNo(patReqNo);
						voResultValidation.setRequisitionDNo(requisitionDNo);
						voResultValidation.setTestCode(testCode);
						voResultValidation.setParameterRequisitionDNo(requisitionDNo);
						voResultValidation.setSampleNo(sampleNo);
						voResultValidation.setLabCode(labCode);
						voResultValidation.setPatAge(patAge);
						voResultValidation.setPatGender(patGender);
						voResultValidation.setReportAvailableAfter(reportAvailableAfter);
						// getting Fb Values For Selected Check Box
						voResultValidation.setPatVisitDat(patVisitDat);
						voResultValidation.setPatVisitNo(patVisitNo);
						voResultValidation.setLabNo(labNo);
						voResultValidation.setEpisodeCode(episodeCode);
						voResultValidation.setDepartmentcode(deptCode);
						voResultValidation.setPatdeptunitcode(deptUnitCode);
						voResultValidation.setRequisitionTypeCode(reqType);
						voResultValidation.setPatLabName(labName);
						voResultValidation.setTestName(testName);
						voResultValidation.setSampleName(sampleName);
						voResultValidation.setTempSampleNo(tempSampleNo);
						voResultValidation.setNewEntry(newEntry);
						voResultValidation.setDynamicTemplatePrintedGroup(isPrintedWithDynamicGroup);
						voResultValidation.setGroupCode(groupCode);
						voResultValidation.setRemarkvaluess(commentsboxxval);
						/* Added by Prashant */
						voResultValidation.setVisitReason(visitReason);

						/*
						 * voResultValidation.setPatPuk(CrNo); voResultValidation.setPatReqNo(patReqNo);
						 * voResultValidation.setRequisitionDNo(requisitionDNo);
						 * voResultValidation.setTestCode(testCode);
						 */
						// getting Fb Values For Selected Check Box

						// added by krishnan nema on 01/02/2019 for save to draft
						if (_fb.getIsSaveToDraft() != null && _fb.getIsSaveToDraft().equals("1")) {
							voResultValidation.setIsSaveToDraft(_fb.getIsSaveToDraft());
						}

						invResultEntryVO.add(voResultValidation);

					}
				}

				else {
					voResultValidation = new ResultEntryVO();
					String[] testCodeArray = selectedLabTestCodeArray[i].split("#");

					CrNo = testCodeArray[0];
					patReqNo = testCodeArray[1];
					requisitionDNo = testCodeArray[2];
					testCode = testCodeArray[3];

					sampleNo = testCodeArray[4];
					labCode = testCodeArray[5];
					patAge = testCodeArray[6];
					patGender = testCodeArray[7];
					reportAvailableAfter = testCodeArray[8];
					patVisitDat = testCodeArray[9];
					patVisitNo = testCodeArray[10];
					labNo = testCodeArray[11];
					episodeCode = testCodeArray[12];
					deptCode = testCodeArray[13];
					deptUnitCode = testCodeArray[14];
					reqType = testCodeArray[15];
					testName = testCodeArray[17];
					labName = testCodeArray[18];
					sampleName = testCodeArray[19];
					tempSampleNo = testCodeArray[20];
					groupCode = testCodeArray[21];
					String isPrintedWithDynamicGroup = testCodeArray[22];
					patName = testCodeArray[23];
					refRange = testCodeArray[24];
					deptunitcode = testCodeArray[25];
					patunitname = testCodeArray[26];
					String commentsboxxval = testCodeArray[28];
					visitReason = testCodeArray[29];
					// visitReason=_fb.getVisitReason();

					// by default mot to be sent to reval process
					voResultValidation.setSaveReVal("0");
					if (_fb.getSaveReVal() != null)
						for (String tempSaveReVal : _fb.getSaveReVal()) {
							if (tempSaveReVal.equals(CrNo + "#" + patReqNo))
								voResultValidation.setSaveReVal("1");
							else
								;

						}
					voResultValidation.setPatCRNo(CrNo);
					voResultValidation.setPatCRNo(CrNo);
					voResultValidation.setRequisitionNo(patReqNo);
					voResultValidation.setRequisitionDNo(requisitionDNo);
					voResultValidation.setTestCode(testCode);
					voResultValidation.setParameterRequisitionDNo(requisitionDNo);
					voResultValidation.setSampleNo(sampleNo);
					voResultValidation.setLabCode(labCode);
					voResultValidation.setPatAge(patAge);
					voResultValidation.setPatGender(patGender);
					voResultValidation.setReportAvailableAfter(reportAvailableAfter);
					// getting Fb Values For Selected Check Box
					voResultValidation.setPatVisitDat(patVisitDat);
					voResultValidation.setPatVisitNo(patVisitNo);
					voResultValidation.setLabNo(labNo);
					voResultValidation.setEpisodeCode(episodeCode);
					voResultValidation.setDepartmentcode(deptCode);
					voResultValidation.setPatdeptunitcode(deptUnitCode);
					voResultValidation.setRequisitionTypeCode(reqType);
					voResultValidation.setPatLabName(labName);
					voResultValidation.setTestName(testName);
					voResultValidation.setSampleName(sampleName);
					voResultValidation.setTempSampleNo(tempSampleNo);
					voResultValidation.setNewEntry(newEntry);
					voResultValidation.setDynamicTemplatePrintedGroup(isPrintedWithDynamicGroup);
					voResultValidation.setGroupCode(groupCode);
					voResultValidation.setRemarkvaluess(commentsboxxval);
					/* Added by Prashant */
					voResultValidation.setVisitReason(visitReason);

					/*
					 * voResultValidation.setPatPuk(CrNo); voResultValidation.setPatReqNo(patReqNo);
					 * voResultValidation.setRequisitionDNo(requisitionDNo);
					 * voResultValidation.setTestCode(testCode);
					 */
					// getting Fb Values For Selected Check Box
					// added by krishnan nema on 01/02/2019 for save to draft
					if (_fb.getIsSaveToDraft() != null && _fb.getIsSaveToDraft().equals("1")) {
						voResultValidation.setIsSaveToDraft(_fb.getIsSaveToDraft());
					}
					invResultEntryVO.add(voResultValidation);
				}
				///////////////////////////////////// checking if req no matches with template
				///////////////////////////////////// received value////////////
				HashMap<String, String> mpLoinc = new HashMap<String, String>();
				for (int len = 0; len < tempLength; len++)
					if (requisitionDNo.equals(lstInvResultEntryTemplateVO.get(len).getRequisitionDNo())) {
						ResultEntryVO rvo = lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy()
								.get(0);
						mpParaLoinc = lstInvResultEntryTemplateVO.get(len).getResultEntryVOListValidatedBy().get(0)
								.getParaLoinc();
						// newmaplist.add(mpParaLoinc);

						if (rvo.getDynamnicTemplateResultEntryGroup() != null
								&& rvo.getDynamnicTemplateResultEntryGroup().equals("1")
								&& !mpLoinc.containsKey(rvo.getRequisitionNo() + rvo.getGroupCode())
								&& mpParaLoinc != null) {
							newmaplist.add(mpParaLoinc);
							mpLoinc.put(rvo.getRequisitionNo() + rvo.getGroupCode(), "1");
						} else if (rvo.getDynamnicTemplateResultEntryGroup() != null
								&& !rvo.getDynamnicTemplateResultEntryGroup().equals("1")) {
							newmaplist.add(mpParaLoinc);
						} else if (rvo.getDynamnicTemplateResultEntryGroup() == null) {
							newmaplist.add(mpParaLoinc);
						}
					}
				//////////////////////////////////// end checking req no with template
				//////////////////////////////////// received//////////////////////////

			} // end for loop

			for (int j = 0, i = 0; j < ResultValidationTemplateValueWithHash; j++) {
				ResultEntryVO voResultValidationForParaMeterDtl = new ResultEntryVO();

				String[] temaplateValue = selectedFbValue[j].split("#");

				String parameterRequisitionDNo = temaplateValue[0];
				String requisitionNo = temaplateValue[6];
				String splitedValue = temaplateValue[3];
				String testnamee = temaplateValue[22];
				String isHyperLink = temaplateValue[35];
//					String testParaMeterCode=splitedValue.substring(0, 5);
//					String parantParameterCode=splitedValue.substring(5,9);//parameter code
				String testParaMeterCode;
				String parantParameterCode;

				if (splitedValue.length() > 9) {
//					  testCode=splitedValue.substring(0, 5);
//					  testParaMeterCode=splitedValue.substring(5, 9);
//					  parantParameterCode=splitedValue.substring(9, 18);
					testCode = splitedValue.substring(splitedValue.length() - 9, splitedValue.length() - 4);
					testParaMeterCode = splitedValue.substring(splitedValue.length() - 4, splitedValue.length());
					parantParameterCode = splitedValue.substring(0, splitedValue.length() - 9);

				} else {
					testCode = splitedValue.substring(0, 5);
					testParaMeterCode = splitedValue.substring(5, 9);
					parantParameterCode = splitedValue;

				}

				String resultEntryValue = temaplateValue[4];

				if (SizeMapOne == 0) {
					fetchValue = newmaplist.get(i);
					i++;
					SizeMapOne = fetchValue.size();
					SizeMapOne = SizeMapOne / 3;
				}
				// String previousValue=mpParaLoinc.get(testParaMeterCode+"previousValue");
				String previousValue = fetchValue.get(splitedValue + "previousValue");
				SizeMapOne--;

				String html = createhtmlforecho(_fb.getEchovar(), parameterRequisitionDNo);
				if (!html.equals("")) {
					resultEntryValue = html;
					isHyperLink = "-";
				}

				String echodata = _fb.getEchovar();

				String finalechoval = "";

				if (echodata != null && !echodata.equals("")) {

					String echoval[] = echodata.split("\\*\\*\\*");
					for (int l = 0; l < echoval.length; l++) {

						if (echoval[l].contains(parameterRequisitionDNo)) {

							finalechoval = echoval[l];
						}

					}

				}

				voResultValidationForParaMeterDtl.setFinalechovalue(finalechoval);

				voResultValidationForParaMeterDtl.setResultEntryValue(resultEntryValue);
				voResultValidationForParaMeterDtl.setTestCode(testCode);
				voResultValidationForParaMeterDtl.setTestParaMeterCode(testParaMeterCode);
				voResultValidationForParaMeterDtl.setParantParaCode(parantParameterCode);
				voResultValidationForParaMeterDtl.setParameterRequisitionDNo(parameterRequisitionDNo);
				voResultValidationForParaMeterDtl.setPreviousValue(previousValue);
				voResultValidationForParaMeterDtl.setPatCRNo(CrNo);
				voResultValidationForParaMeterDtl.setLabCode(labCode);
				voResultValidationForParaMeterDtl.setPatAge(patAge);
				voResultValidationForParaMeterDtl.setPatGender(patGender);
				voResultValidationForParaMeterDtl.setReportAvailableAfter(reportAvailableAfter);

				voResultValidationForParaMeterDtl.setSampleNo(sampleNo);
				voResultValidationForParaMeterDtl.setLabNo(labNo);
				voResultValidationForParaMeterDtl.setTestName(testName);
				voResultValidationForParaMeterDtl.setPatLabName(labName);
				voResultValidationForParaMeterDtl.setPatName(patName);
				voResultValidationForParaMeterDtl.setRefRange(refRange);
				voResultValidationForParaMeterDtl.setDetpUnitCode(deptunitcode);
				voResultValidationForParaMeterDtl.setDepartmentcode(deptCode);
				voResultValidationForParaMeterDtl.setRequisitionTypeCode(reqType);
				voResultValidationForParaMeterDtl.setPatUnitName(patunitname);
				voResultValidationForParaMeterDtl.setTempSampleNo(tempSampleNo);
				voResultValidationForParaMeterDtl.setSampleName(sampleName);
				voResultValidationForParaMeterDtl.setTestName(testnamee);

				voResultValidationForParaMeterDtl.setIsHyperLink(isHyperLink);
				voResultValidationForParaMeterDtl.setRequisitionNo(requisitionNo);

				// by default mot to be sent to reval process
				voResultValidationForParaMeterDtl.setSaveReVal("0");
				if (_fb.getSaveReVal() != null)
					for (String tempSaveReVal : _fb.getSaveReVal()) {
						if (tempSaveReVal.equals(CrNo + "#" + temaplateValue[6]))
							voResultValidationForParaMeterDtl.setSaveReVal("1");
						else
							;

					}

				invResultValidationForParameterDtlVO.add(voResultValidationForParaMeterDtl);

			}

			if (_fb.getCrNoReqNoString() != null)
				if (invResultEntryVO != null) {
					invResultEntryVO.get(0).setCrNoReqNoString(_fb.getCrNoReqNoString());
					invResultEntryVO.get(0).setFinalRemarkValue(_fb.getFinalRemarksValue());
				}

			if (_fb.getCrNoReqNoStringAddendum() != null)
				if (invResultEntryVO != null) {
					invResultEntryVO.get(0).setCrNoReqNoStringAddendum(_fb.getCrNoReqNoStringAddendum());
					invResultEntryVO.get(0).setAddendumRemark(_fb.getAddendumRemarks());
				}

			mp = InvResultValidationDATA.saveResultValidationDetails(invResultEntryVO,
					invResultValidationForParameterDtlVO, _userVO, _request, _fb);
			List lstResultValidation = (List) mp.get(InvestigationConfig.LIST_RESULT_ENTRY_STATUS);
			StringBuilder str = new StringBuilder();

			objStatus.add(Status.DONE, str.toString(),
					"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
							+ "Result Validation Successful" + "</font>");

			objStatus.add(Status.NEW, "", "");
			objStatus.add(Status.TRANSINPROCESS, "", "");

		} catch (HisRecordNotFoundException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			System.out.println(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			System.out.println(e.getMessage());
		} catch (HisException e) {
			objStatus.add(Status.ERROR, "", "Error");
			System.out.println(e.getMessage());
		} finally {
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	/*
	 * public static void revalidateResultValidationPatDetails(InvResultValidationFB
	 * fb,HttpServletRequest request) {
	 * 
	 * Status status = new Status(); HttpSession session=request.getSession();
	 * 
	 * Map mp=new HashMap(); boolean flag=false; try{ List<ResultEntryVO>
	 * invresultentryvo=null; ResultEntryVO invresultentryv = new ResultEntryVO();
	 * List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
	 * List<String> reqList=new ArrayList(); //fb.setisPatDetailPage("1"); UserVO
	 * userVO = ControllerUTIL.getUserVO(request);
	 * invresultentryvo=(List<ResultEntryVO>)session.getAttribute(
	 * InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO); String
	 * selectedCheckBoxValue=fb.getSelectedCheckbox(); String[]
	 * arrSelectedRequisitions=selectedCheckBoxValue.split("@");
	 * 
	 * 
	 * 
	 * for(int i=0;i<arrSelectedRequisitions.length;i++) { for(ResultEntryVO
	 * objPatientCollectionVO:invresultentryvo) {
	 * 
	 * 
	 * // InvResultEntryDATA.fetchLoincCode(objPatientCollectionVO,userVO);
	 * 
	 * 
	 * String crNo=arrSelectedRequisitions[i].split("#")[0]; String
	 * reqNO=arrSelectedRequisitions[i].split("#")[1]; String
	 * reqDNo=arrSelectedRequisitions[i].split("#")[2]; String
	 * grpCode=arrSelectedRequisitions[i].split("#")[3];
	 * 
	 * if(objPatientCollectionVO.getGroupCode()==null) {
	 * if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.
	 * getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().
	 * equals(reqDNo)) { //updated by krishnan nema on 17/12/2018 boolean
	 * isSendToMachine = false; if(fb.getChkSendToMachine()!=null &&
	 * fb.getChkSendToMachine().length>0){ String[] reqListToSend =
	 * fb.getChkSendToMachine();
	 * 
	 * for(int p=0;p<reqListToSend.length;p++){ String reqToSend = reqListToSend[p];
	 * String reqDnoToSend = reqToSend.split("#")[1];
	 * if(reqDnoToSend.equals(reqDNo)){ isSendToMachine = true; } } }
	 * 
	 * if(!isSendToMachine){ objPatientCollectionVO.setIsSendToMachine("0");
	 * lstInvResultEntryVO.add(objPatientCollectionVO); }else{
	 * objPatientCollectionVO.setIsSendToMachine("1");
	 * lstInvResultEntryVO.add(objPatientCollectionVO); }
	 * 
	 * } } else {
	 * if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.
	 * getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().
	 * equals(grpCode)) { //updated by krishnan nema on 17/12/2018 boolean
	 * isSendToMachine = false; if(fb.getChkSendToMachine()!=null &&
	 * fb.getChkSendToMachine().length>0){ String[] reqListToSend =
	 * fb.getChkSendToMachine();
	 * 
	 * for(int p=0;p<reqListToSend.length;p++){ String reqToSend = reqListToSend[p];
	 * String reqDnoToSend = reqToSend.split("#")[1];
	 * if(reqDnoToSend.equals(reqDNo)){ isSendToMachine = true; } } }
	 * 
	 * if(!isSendToMachine){ objPatientCollectionVO.setIsSendToMachine("0");
	 * lstInvResultEntryVO.add(objPatientCollectionVO); }else{
	 * objPatientCollectionVO.setIsSendToMachine("1");
	 * lstInvResultEntryVO.add(objPatientCollectionVO); }
	 * //lstInvResultEntryVO.add(objPatientCollectionVO); }
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@"); int i=0;
	 * for(ResultEntryVO objPatientCollectionVO:invresultentryvo) {
	 * if(i<arrSelectedRequisitions.length) { String
	 * crNo=arrSelectedRequisitions[i].split("#")[0]; String
	 * reqNO=arrSelectedRequisitions[i].split("#")[1];
	 * 
	 * if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.
	 * getRequisitionNo().equals(reqNO)) {
	 * 
	 * 
	 * 
	 * lstInvResultEntryVO.add(objPatientCollectionVO);
	 * 
	 * 
	 * /////WebUTIL.populate(fb,objPatientCollectionVO);
	 * //WebUTIL.setAttributeInSession(request,InvestigationConfig.
	 * LIST_RESULT_ENTRY_PATIENT_VO,objPatientCollectionVO); // ResultEntryVO rvo =
	 * new ResultEntryVO(null,objPatientCollectionVO.getTestCode(),null,null); //
	 * rvo.setSessionId("243434"); //
	 * rvo.setRequisitionDNo(objPatientCollectionVO.getRequisitionDNo()); //
	 * List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>(); // lrvo.add(rvo);
	 * // objPatientCollectionVO.setResultEntryVOListValidatedBy(lrvo); //
	 * lstInvResultEntryVO.add(objPatientCollectionVO);
	 * //invresultentryv=objPatientCollectionVO; ++i; } } }
	 * 
	 * 
	 * InvResultValidationDATA.revalidate(lstInvResultEntryVO,userVO);
	 * //mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO,
	 * lstInvResultEntryVO); //for(String str:arrSelectedRequisitions) //
	 * List<InvResultEntryVO> lstInvResultEntryTemplateVO=null; //
	 * lstInvResultEntryTemplateVO=TemplateProcessingUSE.groupSelectedWorkOrders(
	 * lstInvResultEntryVO, session); //
	 * System.out.println(lstInvResultEntryTemplateVO.get(0).
	 * getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString()); //
	 * mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO,
	 * lstInvResultEntryTemplateVO);
	 * //mp=InvResultEntryDATA.patientDetails(invresultentryv,reqList, userVO);
	 * 
	 * // WebUTIL.setMapInSession(mp, request); //
	 * 
	 * 
	 * StringBuilder str = new StringBuilder();
	 * 
	 * 
	 * status.add(Status.DONE, str.toString(),
	 * "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
	 * + "Result Validation Successful" + "</font>");
	 * 
	 * 
	 * 
	 * 
	 * status.add(Status.NEW, "", ""); status.add(Status.TRANSINPROCESS, "", ""); }
	 * catch(Exception e){
	 * status.add(Status.ERROR_AE,"Application Execution Exception", "");
	 * e.printStackTrace(); } finally{ WebUTIL.setStatus(request, status); }
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	public static void revalidateResultValidationPatDetails(InvResultValidationFB fb,HttpServletRequest request)
	{
		
		Status status = new Status();
		HttpSession session=request.getSession();
		
		Map mp=new HashMap();
		boolean flag=false;
		try{
			List<ResultEntryVO> invresultentryvo=null;
			ResultEntryVO invresultentryv = new ResultEntryVO();
			List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);
			invresultentryvo=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
			String selectedCheckBoxValue=fb.getSelectedCheckbox();
			String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			
			
			
			for(int i=0;i<arrSelectedRequisitions.length;i++)
			 {
				for(ResultEntryVO objPatientCollectionVO:invresultentryvo)
				{
					 
					
				//	InvResultEntryDATA.fetchLoincCode(objPatientCollectionVO,userVO);
					
					 
					String crNo=arrSelectedRequisitions[i].split("#")[0];
					String reqNO=arrSelectedRequisitions[i].split("#")[1];
					String reqDNo=arrSelectedRequisitions[i].split("#")[2];
					 String grpCode=arrSelectedRequisitions[i].split("#")[3];
					 //String testCode=arrSelectedRequisitions[i].split("#")[4];
					 if(objPatientCollectionVO.getGroupCode()==null)
					 { 
					 if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getRequisitionDNo().equals(reqDNo))
					{
						//updated by krishnan nema on 17/12/2018
						 boolean isSendToMachine = false;
						 if(fb.getChkSendToMachine()!=null && fb.getChkSendToMachine().length>0){
							 	String[] reqListToSend = fb.getChkSendToMachine();
							
								for(int p=0;p<reqListToSend.length;p++){
									String reqToSend = reqListToSend[p];
									String reqDnoToSend = reqToSend.split("#")[1];
									if(reqDnoToSend.equals(reqDNo)){
										isSendToMachine = true;
									}
								}
						 }
						 
						 	if(!isSendToMachine){
						 		objPatientCollectionVO.setIsSendToMachine("0");
						 		lstInvResultEntryVO.add(objPatientCollectionVO);
							}else{
								objPatientCollectionVO.setIsSendToMachine("1");
								lstInvResultEntryVO.add(objPatientCollectionVO);
							}
						 
					}
					 }
					 else
					 {
						 
						 
						 String[] reqListToSendtest = fb.getChkSendToMachinetest();

						 if(reqListToSendtest!=null && reqListToSendtest.length>0)
						 {
							 
							 if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
								{
								 
								 
							
							 boolean isSendToMachine = false;
							 
						 	for(int p=0;p<reqListToSendtest.length;p++){
								String reqToSend = reqListToSendtest[p];
								String reqDnoToSend = reqToSend.split("#")[0];
								String testtosend = reqToSend.split("#")[1];
								
								if(reqDnoToSend.equals(reqNO) && objPatientCollectionVO.getRequisitionNo().equals(reqNO) && testtosend.equals(objPatientCollectionVO.getTestCode())){
									isSendToMachine = true;
									break;
								}
						 	}
						 	
						 	
						 	
						 	if(!isSendToMachine){
						// 		objPatientCollectionVO.setIsSendToMachine("0");
						 //		lstInvResultEntryVO.add(objPatientCollectionVO);
							}else{
								objPatientCollectionVO.setIsSendToMachine("1");
								lstInvResultEntryVO.add(objPatientCollectionVO);
							}
								}
							//	}
						 	
						 	
						 	
						 }
						 else
						 {
						 if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO)&&objPatientCollectionVO.getGroupCode().equals(grpCode))
							{


							 //updated by krishnan nema on 17/12/2018
							 boolean isSendToMachine = false;
							 if(fb.getChkSendToMachine()!=null && fb.getChkSendToMachine().length>0){
								 	String[] reqListToSend = fb.getChkSendToMachine();
								
									for(int p=0;p<reqListToSend.length;p++){
										String reqToSend = reqListToSend[p];
										String reqDnoToSend = reqToSend.split("#")[1];
										String testToSend = reqToSend.split("#")[2];
										if(reqDnoToSend.equals(reqDNo)){
											isSendToMachine = true;
										}
									}
							 }
							 
							 	if(!isSendToMachine){
							 		objPatientCollectionVO.setIsSendToMachine("0");
							 		lstInvResultEntryVO.add(objPatientCollectionVO);
								}else{
									objPatientCollectionVO.setIsSendToMachine("1");
									lstInvResultEntryVO.add(objPatientCollectionVO);
								}
							 //lstInvResultEntryVO.add(objPatientCollectionVO);
							} 
						 }
						 
					 }
					 }
			
				}
			
			
			
			
			
			
			
			
			
			
			/*String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
			int i=0;
				for(ResultEntryVO objPatientCollectionVO:invresultentryvo)
				{
					 if(i<arrSelectedRequisitions.length)
					 {
					String crNo=arrSelectedRequisitions[i].split("#")[0];
					String reqNO=arrSelectedRequisitions[i].split("#")[1];
					 
					if(objPatientCollectionVO.getPatCRNo().equals(crNo)&&objPatientCollectionVO.getRequisitionNo().equals(reqNO))
					{
						
					
						
						lstInvResultEntryVO.add(objPatientCollectionVO);
						
						
              /////WebUTIL.populate(fb,objPatientCollectionVO); 
						//WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO,objPatientCollectionVO);
					//	ResultEntryVO rvo = new ResultEntryVO(null,objPatientCollectionVO.getTestCode(),null,null);
					//	rvo.setSessionId("243434");
					//	rvo.setRequisitionDNo(objPatientCollectionVO.getRequisitionDNo());
					//	List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();
					//	lrvo.add(rvo);
					//	objPatientCollectionVO.setResultEntryVOListValidatedBy(lrvo); 
					//	lstInvResultEntryVO.add(objPatientCollectionVO);
						//invresultentryv=objPatientCollectionVO;
						++i;
					}
					 }
				}*/
				
				
				InvResultValidationDATA.revalidate(lstInvResultEntryVO,userVO);
				//mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_VO, lstInvResultEntryVO);
				//for(String str:arrSelectedRequisitions)
			//	List<InvResultEntryVO> lstInvResultEntryTemplateVO=null;
			//	lstInvResultEntryTemplateVO=TemplateProcessingUSE.groupSelectedWorkOrders(lstInvResultEntryVO, session);
			//	System.out.println(lstInvResultEntryTemplateVO.get(0).getResultEntryVOListValidatedBy().get(0).getTemplateDocumentString());
			//	mp.put(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO, lstInvResultEntryTemplateVO);
				//mp=InvResultEntryDATA.patientDetails(invresultentryv,reqList, userVO);
				
			//	WebUTIL.setMapInSession(mp, request);
			//		
			 
				
				StringBuilder str = new StringBuilder();	
				 
				 
				  status.add(Status.DONE, str.toString(),
							"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
									+ "Result Validation Successful" + "</font>");
				       

				  
				  
			status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
		
		
		
	}
	

	public static String checkCannedCodeName(InvResultValidationFB fb, HttpServletRequest objRequest_p) {

		HttpSession session = objRequest_p.getSession();
		String strMsgText = "";
		String strTestCombo = "";
		String mp = "";

		try {
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);

			String val = fb.getCannedContent();
			val = val.replace("$", "&");
			fb.setCannedContent(val);

			mp = InvResultValidationDATA.checkcannedCodeName(fb, userVO);

		} catch (Exception e) {
			strMsgText = "ResultEntryUtil.showCannedDetails() -> " + e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() +
			// "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return mp;
	}

	public static void printReport(InvResultValidationFB fb, HttpServletRequest objRequest_p,
			HttpServletResponse response) {

		HttpSession session = objRequest_p.getSession();
		String strMsgText = "";
		String strTestCombo = "";
		Map mp = new HashMap();
		Status status = new Status();
		List<byte[]> pdfs = new ArrayList<byte[]>();

		try {

			byte[] Pdf = new_investigation.transactions.controller.utl.MongoXmlHandler.getInstance()
					.latestFetchFile(fb.getFileName());

			pdfs.clear();
			pdfs.add(Pdf);

			response.setContentType("application/pdf");
			response.setHeader("Cache-Control", "no-cache");
			OutputStream output = response.getOutputStream();
			MergeAllPdfNewInv.concatallPDFs(pdfs, output, null, objRequest_p);
			output.flush();
			output.close();
			// return;

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() +
			// "],Contact System Administrator! ");
			// eObj = null;
		} finally {
			// WebUTIL.setStatus(objRequest_p, status);
		}

	}

	public static void getSampleCollectionArea(InvResultValidationFB fb, HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		HttpSession session = request.getSession();
		try {
			Map mpp = new HashMap();
			Map mp = new HashMap();
			List<Inv_SampleCollectionVO> lstsampleAreaVO = null;
			List<Inv_SampleCollectionVO> lstsamplePatinetVO = null;
			// List<Inv_SampleCollectionVO> lstsamplePatinetVO=null;
			ControllerUTIL.setSysdate(request);
			Date dt = (Date) request.getSession().getAttribute(Config.SYSDATEOBJECT);
			String todayDate = WebUTIL.getCustomisedSysDate(dt, "dd-MMM-yyyy");

			lstsampleAreaVO = new ArrayList<Inv_SampleCollectionVO>();
			Inv_SampleCollectionVO objSampleVO = new Inv_SampleCollectionVO();
			// WebUTIL.populate(lstsampleAreaVO,fb);
			UserVO userVO = ControllerUTIL.getUserVO(request);
			lstsampleAreaVO = SampleCollectionDATA.getSampleCollectionArea(userVO);
			if (lstsampleAreaVO != null) {
				WebUTIL.populate(fb, lstsampleAreaVO);

				List<Entry> sampleList = new ArrayList<Entry>();
				for (Inv_SampleCollectionVO sampleAreaVO : lstsampleAreaVO) {

					Entry entObj = new Entry();
					// updated by krishnan nema on 27/09/2018
					// entObj.setValue(sampleAreaVO.getSampleAreaCode());
					entObj.setValue(sampleAreaVO.getSampleAreaCode() + "#" + sampleAreaVO.getPatientType());
					;
					entObj.setLabel(sampleAreaVO.getSampleAreaName());
					sampleList.add(entObj);

				}
				WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_SAMPLE_COLLECTION_VO, sampleList);
				lstsamplePatinetVO = (List<Inv_SampleCollectionVO>) session
						.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);

				if (sampleList.size() == 1) {
					lstsamplePatinetVO = new ArrayList<Inv_SampleCollectionVO>();
					ResultEntryVO objSampleCollectionVO = new ResultEntryVO();

					objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
					objSampleCollectionVO.setFromDate(todayDate);
					objSampleCollectionVO.setToDate(todayDate);
					objSampleCollectionVO.setSampleAreaCode(((Entry) sampleList.get(0)).getValue());

					objSampleCollectionVO.setLabCode("%");
					objSampleCollectionVO.setNewEntry("1");
					objSampleCollectionVO.setOnLoadValue("ONLOAD");
					objSampleCollectionVO.setSearchBy("1");

					session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
					session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);

					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);

					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
					session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);

					// lstsamplePatinetVO=SampleCollectionDATA.getPatList(objSampleCollectionVO,userVO);

					mp = InvResultValidationDATA.LabComboForResultValidation(objSampleCollectionVO, userVO);
					WebUTIL.setMapInSession(mp, request);

					mpp = InvResultValidationDATA.setPatientResultValidationEssentials(objSampleCollectionVO, userVO);
					// mpp.put(InvestigationConfig.LIST_SAMPLE_COLLECTION_VO,"1");
					WebUTIL.setMapInSession(mpp, request);

					if (lstsamplePatinetVO != null) {
						WebUTIL.populate(fb, lstsamplePatinetVO);
						WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_SAMPLE_PATIENT_VO,
								lstsamplePatinetVO);

					}

					fb.setSampleAreaCode(((Entry) sampleList.get(0)).getValue());
					fb.setSampleAreaName(((Entry) sampleList.get(0)).getLabel());
					fb.setIsSampleAreaSelected("1");
					fb.setLabCode("%");
				}

				/*
				 * if(sampleList.size()==1) { lstsamplePatinetVO=new
				 * ArrayList<Inv_SampleCollectionVO>();
				 * 
				 * Inv_SampleCollectionVO objSampleCollectionVO= new Inv_SampleCollectionVO();
				 * //WebUTIL.populate(objSampleCollectionVO,fb);
				 * 
				 * objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
				 * objSampleCollectionVO.setFromDate(todayDate);
				 * objSampleCollectionVO.setToDate(todayDate);
				 * 
				 * objSampleCollectionVO.setSampleAreaCode(((Entry)sampleList.get(0)).getValue()
				 * );
				 * 
				 * session.removeAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
				 * session.removeAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
				 * 
				 * session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_BILLED);
				 * session.removeAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
				 * 
				 * session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
				 * session.removeAttribute(InvestigationConfig.MAP_PAT_SAMPLE_UNBILLED);
				 * 
				 * 
				 * lstsamplePatinetVO=SampleCollectionDATA.getPatList(objSampleCollectionVO,
				 * userVO); if(lstsamplePatinetVO!=null) {
				 * WebUTIL.populate(fb,lstsamplePatinetVO);
				 * WebUTIL.setAttributeInSession(request,InvestigationConfig.
				 * LIST_SAMPLE_PATIENT_VO,lstsamplePatinetVO);
				 * 
				 * }
				 * 
				 * fb.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
				 * fb.setSampleAreaName(((Entry)sampleList.get(0)).getLabel());
				 * fb.setIsSampleAreaSelected("1");
				 * 
				 * }
				 */
			}

			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static StringBuffer getRequisitionFormMasterData(InvResultValidationFB fb, HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session = objRequest_p.getSession();
		// Map<String, String> mapUnavailableTestCode =(Map<String, String>)
		// session.getAttribute(InvestigationConfig.MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS);
		// System.out.println("mapUnavailableTestCode : "+mapUnavailableTestCode);
		String strMsgText = "";
		String remarks = "1";
		String lstEpisodeVOo = "";
		Map mp = new HashMap();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
			String testCode = fb.getTestCodee();
			// LabTestVO voLabTest = mapUserCodeLabTestVO.get(userTestCode);

			lstEpisodeVOo = InvestigationRaisingDtlDATA.getRequisitionFormMasterData(testCode, userVO);

			// System.out.println("userTestCode getRemarksForUnavailableTest UTL
			// "+userTestCode+ " : remarks -- : "+remarks);

			sbAjaxRes.append(lstEpisodeVOo);

		} catch (Exception e) {
			strMsgText = "---> " + e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() +
			// "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}

		return sbAjaxRes;
	}

	public static String getfileuploaddatatestwise(InvResultValidationFB fb, HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String uploadfiledatatestwise = "";
		HttpSession session = objRequest_p.getSession();
		// Map<String, String> mapUnavailableTestCode =(Map<String, String>)
		// session.getAttribute(InvestigationConfig.MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS);
		// System.out.println("mapUnavailableTestCode : "+mapUnavailableTestCode);
		String strMsgText = "";
		String remarks = "1";
		String lstEpisodeVOo = "";
		Map mp = new HashMap();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
			String dno = fb.getRequisitionDNo()[0];
			// LabTestVO voLabTest = mapUserCodeLabTestVO.get(userTestCode);

			lstEpisodeVOo = InvestigationRaisingDtlDATA.getfileuploaddatatestwise(dno, userVO,
					fb.getTestParaMeterCode());

			// System.out.println("userTestCode getRemarksForUnavailableTest UTL
			// "+userTestCode+ " : remarks -- : "+remarks);

		} catch (Exception e) {
			strMsgText = "---> " + e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() +
			// "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}

		return lstEpisodeVOo;
	}

	public static String getechodata(String reqdno, HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String uploadfiledatatestwise = "";
		HttpSession session = objRequest_p.getSession();
		// Map<String, String> mapUnavailableTestCode =(Map<String, String>)
		// session.getAttribute(InvestigationConfig.MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS);
		// System.out.println("mapUnavailableTestCode : "+mapUnavailableTestCode);
		String strMsgText = "";
		String remarks = "1";
		String lstEpisodeVOo = "";
		Map mp = new HashMap();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
			String dno = reqdno;
			// LabTestVO voLabTest = mapUserCodeLabTestVO.get(userTestCode);

			lstEpisodeVOo = InvestigationRaisingDtlDATA.getechodata(dno, userVO);

			// System.out.println("userTestCode getRemarksForUnavailableTest UTL
			// "+userTestCode+ " : remarks -- : "+remarks);

		} catch (Exception e) {
			strMsgText = "---> " + e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() +
			// "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}

		return lstEpisodeVOo;
	}

	public static String createhtmlforecho(String echodata, String dno) {
		String data = "";

		if (echodata != null && !echodata.equals("")) {
			String echoval[] = echodata.split("\\*\\*\\*");

			for (int l = 0; l < echoval.length; l++) {

				if (echoval[l].contains(dno)) {

					data = createhtmlforechofinal(echoval[l]);
				}

			}

		}

		return data;

	}

	/* updated by PrashantMi */
	public static String createhtmlforechofinal(String echodata) {
		height = "25";
		echodata = echodata.split("\\^\\^\\^")[1];

		Map<String, String> mp = new HashMap<String, String>();
		if (echodata != null && !echodata.equals("")) {
			String[] values = echodata.split("@@@");

			for (int k = 0; k < values.length; k++) {

				String[] val = values[k].split("\\$\\$\\$");

				val[1] = "";

				String keyy = "";
				String vall = "";

				keyy = val[0];

				if (val.length == 2) {
					vall = "";
				} else if (val.length == 3) {
					vall = val[2];

				} else if (val.length > 3) {
					vall = val[2];

					for (int p = 3; p < val.length; p++) {
						vall = vall + "," + val[p];

					}

				}

				mp.put(keyy, vall);

			}

		}

		String ss = (String) mp.get("t30");

		String html = "";
		String width = 20 + "%";
		String t1 = mp.get("t1").equals("-1") ? "" : mp.get("t1");
		String t2 = mp.get("t2").equals("-1") ? "" : mp.get("t2");
		String t3 = mp.get("t3").equals("-1") ? "" : mp.get("t3");
		String t4 = mp.get("t4").equals("-1") ? "" : mp.get("t4");
		String t5 = mp.get("t5").equals("-1") ? "" : mp.get("t5");
		String t6 = mp.get("t6").equals("-1") ? "" : mp.get("t6");
		String t7 = mp.get("t7").equals("-1") ? "" : mp.get("t7");
		String t8 = mp.get("t8").equals("-1") ? "" : mp.get("t8");
		String t9 = mp.get("t9").equals("-1") ? "" : mp.get("t9");
		String t10 = mp.get("t10").equals("-1") ? "" : mp.get("t10");
		String t11 = mp.get("t11").equals("-1") ? "" : mp.get("t11");
		String t12 = mp.get("t12").equals("-1") ? "" : mp.get("t12");
		String t13 = mp.get("t13").equals("-1") ? "" : mp.get("t13");
		String t14 = mp.get("t14").equals("-1") ? "" : mp.get("t14");
		String t15 = mp.get("t15").equals("-1") ? "" : mp.get("t15");
		String t16 = mp.get("t16").equals("-1") ? "" : mp.get("t16");
		String t17 = mp.get("t17").equals("-1") ? "" : mp.get("t17");
		String t18 = mp.get("t18").equals("-1") ? "" : mp.get("t18");
		String t19 = mp.get("t19").equals("-1") ? "" : mp.get("t19");
		String t20 = mp.get("t20").equals("-1") ? "" : mp.get("t20");
		String t21 = mp.get("t21").equals("-1") ? "" : mp.get("t21");
		String t22 = mp.get("t22").equals("-1") ? "" : mp.get("t22");
		String t23 = mp.get("t23").equals("-1") ? "" : mp.get("t23");
		String t24 = mp.get("t24").equals("-1") ? "" : mp.get("t24");
		String t25 = mp.get("t25").equals("-1") ? "" : mp.get("t25");
		String t26 = mp.get("t26").equals("-1") ? "" : mp.get("t26");
		String t27 = mp.get("t27").equals("-1") ? "" : mp.get("t27");
		String t28 = mp.get("t28").equals("-1") ? "" : mp.get("t28");
		String t29 = mp.get("t29").equals("-1") ? "" : mp.get("t29");
		String t30 = mp.get("t30").equals("-1") ? "" : mp.get("t30");
		String t31 = mp.get("t31").equals("-1") ? "" : mp.get("t31");
		String t32 = mp.get("t32").equals("-1") ? "" : mp.get("t32");
		String t33 = mp.get("t33").equals("-1") ? "" : mp.get("t33");
		String t34 = mp.get("t34").equals("-1") ? "" : mp.get("t34");
		String t35 = mp.get("t35").equals("-1") ? "" : mp.get("t35");
		String t36 = mp.get("t36").equals("-1") ? "" : mp.get("t36");
		String t37 = mp.get("t37").equals("-1") ? "" : mp.get("t37");
		String t38 = mp.get("t38").equals("-1") ? "" : mp.get("t38");
		String t39 = mp.get("t39").equals("-1") ? "" : mp.get("t39");
		String t40 = mp.get("t40").equals("-1") ? "" : mp.get("t40");
		String t41 = mp.get("t41").equals("-1") ? "" : mp.get("t41");
		String t42 = mp.get("t42").equals("-1") ? "" : mp.get("t42");
		String t43 = mp.get("t43").equals("-1") ? "" : mp.get("t43");
		String t44 = mp.get("t44").equals("-1") ? "" : mp.get("t44");
		String t45 = mp.get("t45").equals("-1") ? "" : mp.get("t45");

		String t46 = mp.get("t46").equals("-1") ? "" : mp.get("t46");
		String t47 = mp.get("t47").equals("-1") ? "" : mp.get("t47");
		String t48 = mp.get("t48").equals("-1") ? "" : mp.get("t48");
		String t49 = mp.get("t49").equals("-1") ? "" : mp.get("t49");
		String t50 = mp.get("t50").equals("-1") ? "" : mp.get("t50");
		String t51 = mp.get("t51").equals("-1") ? "" : mp.get("t51");
		String t52 = mp.get("t52").equals("-1") ? "" : mp.get("t52");
		String t53 = mp.get("t53").equals("-1") ? "" : mp.get("t53");
		String t54 = mp.get("t54").equals("-1") ? "" : mp.get("t54");
		String t55 = mp.get("t55").equals("-1") ? "" : mp.get("t55");
		String t56 = mp.get("t56").equals("-1") ? "" : mp.get("t56");
		String t57 = mp.get("t57").equals("-1") ? "" : mp.get("t57");
		String t58 = mp.get("t58").equals("-1") ? "" : mp.get("t58").equals("") ? "-" : mp.get("t58");
		String t59 = mp.get("t59").equals("-1") ? "" : mp.get("t59").equals("") ? "-" : mp.get("t59");
		String t60 = mp.get("t60").equals("-1") ? "" : mp.get("t60").equals("") ? "-" : mp.get("t60");
		String t61 = mp.get("t61").equals("-1") ? "" : mp.get("t61").equals("") ? "-" : mp.get("t61");
		String t62 = mp.get("t62").equals("-1") ? "" : mp.get("t62").equals("") ? "-" : mp.get("t62");
		String t63 = mp.get("t63").equals("-1") ? "" : mp.get("t63").equals("") ? "-" : mp.get("t63");
		String t64 = mp.get("t64").equals("-1") ? "" : mp.get("t64").equals("") ? "-" : mp.get("t64");
		String t65 = mp.get("t65").equals("-1") ? "" : mp.get("t65").equals("") ? "-" : mp.get("t65");
		String t66 = mp.get("t66").equals("-1") ? "" : mp.get("t66").equals("") ? "-" : mp.get("t66");
		String t67 = mp.get("t67").equals("-1") ? "" : mp.get("t67").equals("") ? "-" : mp.get("t67");
		String t68 = mp.get("t68").equals("-1") ? "" : mp.get("t68").equals("") ? "-" : mp.get("t68");
		String t69 = mp.get("t69").equals("-1") ? "" : mp.get("t69").equals("") ? "-" : mp.get("t69");
		String t70 = mp.get("t70").equals("-1") ? "" : mp.get("t70").equals("") ? "-" : mp.get("t70");
		String t71 = mp.get("t71").equals("-1") ? "" : mp.get("t71").equals("") ? "-" : mp.get("t71");
		String t72 = mp.get("t72").equals("-1") ? "" : mp.get("t72");
		String t73 = mp.get("t73").equals("-1") ? "" : mp.get("t73");
		String t74 = mp.get("t74").equals("-1") ? "" : mp.get("t74");
		String t75 = mp.get("t75").equals("-1") ? "" : mp.get("t75");
		String t76 = mp.get("t76").equals("-1") ? "" : mp.get("t76");
		String t77 = mp.get("t77").equals("-1") ? "" : mp.get("t77");
		String t78 = mp.get("t78").equals("-1") ? "" : mp.get("t78");

		/*
		 * String t79=mp.get("t79").equals("-1")?"":mp.get("t79"); String
		 * t80=mp.get("t80").equals("-1")?"":mp.get("t80"); String
		 * t81=mp.get("t81").equals("-1")?"":mp.get("t81"); String
		 * t82=mp.get("t82").equals("-1")?"":mp.get("t82"); String
		 * t83=mp.get("t83").equals("-1")?"":mp.get("t83"); String
		 * t84=mp.get("t84").equals("-1")?"":mp.get("t84"); String
		 * t85=mp.get("t85").equals("-1")?"":mp.get("t85"); String
		 * t86=mp.get("t86").equals("-1")?"":mp.get("t86");
		 */
		String t87 = mp.get("t87").equals("-1") ? "" : mp.get("t87");
		String t88 = mp.get("t88").equals("-1") ? "" : mp.get("t88");
		String t89 = mp.get("t89").equals("-1") ? "" : mp.get("t89");
		String t90 = mp.get("t90").equals("-1") ? "" : mp.get("t90");
		String t91 = mp.get("t91").equals("-1") ? "" : mp.get("t91");
		String t92 = mp.get("t92").equals("-1") ? "" : mp.get("t92");
		String t93 = mp.get("t93").equals("-1") ? "" : mp.get("t93");
		String t94 = mp.get("t94").equals("-1") ? "" : mp.get("t94");

		String t95 = mp.get("t95").equals("-1") ? "" : mp.get("t95");
		String t96 = mp.get("t96").equals("-1") ? "" : mp.get("t96");
		String t97 = mp.get("t97").equals("-1") ? "" : mp.get("t97");
		String t98 = mp.get("t98").equals("-1") ? "" : mp.get("t98");
		String t99 = mp.get("t99").equals("-1") ? "" : mp.get("t99");
		String t100 = mp.get("t100").equals("-1") ? "" : mp.get("t100");
		String t101 = mp.get("t101").equals("-1") ? "" : mp.get("t101");
		String t102 = mp.get("t102").equals("-1") ? "" : mp.get("t102");
		String t103 = mp.get("t103").equals("-1") ? "" : mp.get("t103");
		String t104 = mp.get("t104").equals("-1") ? "" : mp.get("t104");
		String t105 = mp.get("t105").equals("-1") ? "" : mp.get("t105");
		String t106 = mp.get("t106").equals("-1") ? "" : mp.get("t106");
		String t107 = mp.get("t107").equals("-1") ? "" : mp.get("t107");
		String t108 = mp.get("t108").equals("-1") ? "" : mp.get("t108");
		String t109 = mp.get("t109").equals("-1") ? "" : mp.get("t109");
		String t110 = mp.get("t110").equals("-1") ? "" : mp.get("t110");

		/*
		 * String t46=mp.get("t46"); String t47=mp.get("t47"); String t48=mp.get("t48");
		 * String t49=mp.get("t49"); String t50=mp.get("t50"); String t51=mp.get("t51");
		 * String t52=mp.get("t52"); String t53=mp.get("t53"); String t54=mp.get("t54");
		 * String t55=mp.get("t55"); String t56=mp.get("t56"); String t57=mp.get("t57");
		 * String t58=mp.get("t58");
		 */

		String tableString = "<table width='100%' >";
		String firstRow = "<tr><td width='30%'><div align='left'><b> Procedure Done By </b></div></td>";
		firstRow += "<td width='70%'><div align='left'>" + t1 + "</div></td>";
		tableString += firstRow + "</tr>";

		String secondRow = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left'><b> Echo Window </b></div></td>";
		secondRow += "<td width='70'><div align='left'>" + t2 + "</div></td>";
		tableString += secondRow + "</tr>";

		String thirdRow = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left'><b> Morphology </b></div></td><td width='70%'></td>";
		tableString += thirdRow + "</tr>";

		String thirdRow1 = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left' ><b> AML </b></div></td>";
		if (t3.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Normal</div></td>";
		} else if (t4.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Abnormal (" + t5 + ")</div></td>";
		}
		tableString += thirdRow1 + "</tr>";

		String thirdRow2 = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left'><b> PML </b></div></td>";
		if (t6.equals("1")) {
			thirdRow2 += "<td width='70%' ><div align='left'>Normal</div></td>";
		} else if (t7.equals("1")) {
			thirdRow2 += "<td width='70%' ><div align='left'>Abnormal (" + t8 + ")</div></td>";
		}
		tableString += thirdRow2 + "</tr>";

		tableString += "</table>";

		// tableString+="<table width='100%' >";

		String mainlabel = "";
		String l1 = "";
		String l2 = "";
		String l3 = "";
		String l4 = "";

		mainlabel = "Doppler";
		l1 = "E (cm/s) ";
		l2 = "A (cm/s) ";
		l3 = "DT";
		l4 = "E/A ";

		tableString += gethtmll(t9, t10, t11, t12, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "Tissue Doppler";
		l1 = "E'' ";
		l2 = "A'' ";
		l3 = "E/e'' ";
		l4 = "";

		tableString += gethtmll(t13, t14, t15, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		tableString += "<table width='100%' >";
		// tableString+="";
		firstRow = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left'><b> Diastolic Function</b></div></td>";
		firstRow += "<td width='70%'><div align='left'>" + t16 + "</div></td>";
		tableString += firstRow + "</tr>";
		tableString += "</table>";

		mainlabel = "Mitral Stenosis";
		l1 = "";
		l2 = "Max DG";
		l3 = "Mean DG(MmHg)";
		l4 = "";

		tableString += gethtmlllabels(t17, t18, t19, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "35", "0");

		mainlabel = "MVA";
		l1 = "By Planimetry (Cmsq) ";
		l2 = "By PHT (Cmsq) ";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t20, t21, "", "", mainlabel, l1, l2, l3, l4, "30", "35", "35", "0", "0");

		mainlabel = "WILKINS SCORE";
		l1 = "Leaflet Mobility ";
		l2 = "Valve Thickness ";
		l3 = "Subvalvular Thickening ";
		l4 = "Valve Calcification ";

		tableString += gethtmll(t22, t23, t24, t25, mainlabel, l1, l2, l3, l4, "30", "20", "20", "15", "15");

		tableString += "<table width='100%' >";
		tableString += "";
		firstRow = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left'><b> Mitral Regurgitation </b></div></td>";
		firstRow += "<td width='70%'><div align='left'>" + t26 + "</div></td>";
		tableString += firstRow + "</tr></table>";

		mainlabel = "A4C";
		l1 = " LAA ";
		l2 = " Jet Area ";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t28, t27, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "PLAX";
		l1 = " LAA ";
		l2 = " Jet Area ";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t30, t29, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		/*
		 * mainlabel=""; l1=" JetArea(Cmsq) "; l2=" A4C (Cmsq) ";
		 * l3=" JetArea<br/>(Cmsq) "; l4=" LAA (Plax)<br/>(Cmsq) ";
		 * 
		 * tableString+=gethtmll(t27, t28, t29,t30, mainlabel, l1, l2, l3,
		 * l4,"30","20","20","15","15") ;
		 */

		mainlabel = "";
		l1 = " VC mm ";
		l2 = " RV ";
		l3 = " ERO";
		l4 = "";

		tableString += gethtmll(t31, t32, t33, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "TRISCUPID VALVE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5",
				"17.5");

		tableString += "<table width='100%' >";
		thirdRow1 = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left'><b> Morphology </b></div></td>";
		if (t34.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Normal</div></td>";
		} else if (t35.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Abnormal (" + t36 + ")</div></td>";
		}
		tableString += thirdRow1 + "</tr></table>";

		mainlabel = "Tricuspid Stenosis";
		l1 = "";
		l2 = "Max DG";
		l3 = "Mean DG(MmHg)";
		l4 = "";

		tableString += gethtmlllabels(t37, t38, t39, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "35", "0");

		mainlabel = "Tricuspid Regurgitation";
		l1 = "";
		l2 = " RVSP(mmHg)=RAP+ ";
		l3 = "";
		l4 = "";

		tableString += gethtmlllabels(t40, t41, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "52.5", "0", "0");

		mainlabel = "Pulmonary Hypertension";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmlllabels(t42, "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "AORTIC VALVE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5",
				"17.5");

		tableString += "<table width='100%' >";
		thirdRow1 = "<tr style='height:" + height
				+ "'><td width='30%'><div align='left' ><b> Morphology </b></div></td>";
		if (t43.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Normal</div></td>";
		} else if (t44.equals("1")) {
			thirdRow1 += "<td width='70%'><div align='left'>Abnormal (" + t45 + ")</div></td>";
		}
		tableString += thirdRow1 + "</tr></table>";

		mainlabel = "CUSPS";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t95, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "Doppler";
		l1 = "Aortic peak systolic velocity(m/sec)";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmll1td(t96, "", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "Aortic Stenosis";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t97, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "";
		l1 = "PSG(mmHg) ";
		l2 = "MSG(mmHg) ";
		l3 = "AVA(VTI) (cmsq) ";
		l4 = "";

		tableString += gethtmll(t98, t99, t100, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "35", "0");

		mainlabel = "";
		l1 = "Aortic annulus(mm)";
		l2 = "Sinus(mm)";
		l3 = "STJ(mm)";
		l4 = "";

		tableString += gethtmll(t101, t102, t103, "", mainlabel, l1, l2, l3, l4, "30", "35", "17.5", "17.5", "0");

		mainlabel = "Aortic Regurgitation";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t104, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "";
		l1 = "Jet Width<br/>(mm)";
		l2 = "LVOT width<br/>(mm)";
		l3 = "PHT<br/> ";
		l4 = "Vena Contracta<br/>(mm)";

		tableString += gethtmll(t105, t106, t107, t108, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5",
				"17.5");

		mainlabel = "PULMONARY VALVE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5",
				"17.5");

		mainlabel = "Morphology";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t109, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "Doppler";
		l1 = "PSV(M/sec)";
		l2 = "PAT(M/sec)";
		l3 = "";
		l4 = "";

		tableString += gethtmll(t46, t47, "", "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "Pulmonary Stenosis";
		l1 = "";
		l2 = "PSG(mmHg)";
		l3 = "MG(mmHg)";
		l4 = "";

		tableString += gethtmlllabels(t48, t49, t50, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5",
				"17.5");

		mainlabel = "Pulmonary Annulus size(mm)";
		l1 = "";
		l2 = "MPA(mm)";
		l3 = "RPA(mm)";
		l4 = "LPA(mm)";

		tableString += gethtmlllabels(t51, t52, t53, t54, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5",
				"17.5");

		mainlabel = "Pulmonary Regurgitation";
		l1 = "";
		l2 = "Early DG (MmHg)";
		l3 = "End DG (MmHg)";
		l4 = "";

		tableString += gethtmlllabels(t55, t56, t57, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "26.5", "26.5", "0");

		tableString += "<table width='100%' >";
		thirdRow1 = "<tr style='height:" + height
				+ "'><td width='20%'><div align='left'><b style='font-size: 15px'>MEASUREMENTS</b></div></td>";

		thirdRow1 += "<td width='10%' ><div align='left'><b style='font-size: 15px'>VALUES</b></div></td>";

		thirdRow1 += "<td width='20%' ><div align='left'><b style='font-size: 15px'>NORMAL Val.</b></div></td>";

		thirdRow1 += "<td width='20%'><div align='left'><b style='font-size: 15px' >MEASUREMENTS</b></div></td>";

		thirdRow1 += "<td width='10%' ><div align='left'><b style='font-size: 15px'>VALUES</b></div></td>";

		thirdRow1 += "<td width='20%' ><div align='left'><b style='font-size: 15px'>NORMAL Val.</b></div></td>";

		tableString += thirdRow1 + "</tr>";

		mainlabel = "IVsd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t58, "(6-10)mm", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "Aorta";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t59, "(20-37)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVIDd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t60, "(36-52)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LA";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t61, "(19-40)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVPWd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t62, "(7-11)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "RVIDd";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t63, "(4-14)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "IVss";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t64, "(7-12)mm  ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "RV Free Wall";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t65, "(upto 5 mm) ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVIDs";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t66, "", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "FS";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t67, "(34-44)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "LVPWs";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t68, "(8-12)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "TAPSE";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t69, "(>16)mm ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "EF";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone1(t70, "(>55%) ", "", "", mainlabel, l1, l2, l3, l4);

		mainlabel = "Visual EF";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllforone2(t71, "(>55%) ", "", "", mainlabel, l1, l2, l3, l4);
		tableString += "</table>";

		mainlabel = "EF By SIMPSON Method";
		l1 = "LVEDV";
		l2 = "LVESV";
		l3 = "EF";
		l4 = "";

		tableString += gethtmll(t72, t73, t110, "", mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		mainlabel = "IVS Motion";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t74, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "CHAMBERS";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllgrouplable("", "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "";
		l1 = "LA";
		l2 = "LV";
		l3 = "RA";
		l4 = "RV";

		tableString += gethtmll(t75, t76, t77, t78, mainlabel, l1, l2, l3, l4, "30", "17.5", "17.5", "17.5", "17.5");

		// old
		/*
		 * mainlabel="LA"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t75,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 * 
		 * mainlabel="LV"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t76,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="RA"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t77,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="RV"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t78,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 */

		// because less space
		/*
		 * mainlabel="Pericardium"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t79,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 */

		// old
		/*
		 * mainlabel="Effusion"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t80,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 * 
		 * mainlabel="Anterior To RV(mm)"; l1=""; l2=""; l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t81,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="Posterior To LV(Mm)"; l1=""; l2="";
		 * l3=""; l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t82,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ; mainlabel="Lateral(Mm)"; l1=""; l2=""; l3="";
		 * l4="";
		 * 
		 * tableString+=gethtmllfortwolabel(t83,"","","",mainlabel, l1, l2, l3,
		 * l4,"30","70","0","0","0") ;
		 */

		// because of less space
		/*
		 * mainlabel="Effusion"; l1=""; l2="Ant. To RV(mm)"; l3="Post. To LV(Mm)";
		 * l4="Lat(Mm)";
		 * 
		 * tableString+=gethtmlllabels(t80, t81, t82,t83,mainlabel, l1, l2, l3,
		 * l4,"30","15","18.3","18.3","18.3") ;
		 */

		// because of less space
		/*
		 * mainlabel="IVC Diameter"; l1="Expiration(mm)"; l2="Inspiration(mm)";
		 * l3="Collapsibility(%)"; l4="";
		 * 
		 * tableString+=gethtmll(t84,t85,t86,"",mainlabel, l1, l2, l3,
		 * l4,"30","24","24","22","0") ;
		 */

		mainlabel = "RWMA";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t87, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "BASAL LV";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t88, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "MID LV";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t89, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "APICAL LV";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t90, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "LV APEX";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t91, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "Remarks";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t92, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "IMPRESSION";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t93, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		mainlabel = "PLAN";
		l1 = "";
		l2 = "";
		l3 = "";
		l4 = "";

		tableString += gethtmllfortwolabel(t94, "", "", "", mainlabel, l1, l2, l3, l4, "30", "70", "0", "0", "0");

		html = tableString;
		return html;
	}

	public static String gethtmllgrouplable(String t9, String t10, String t11, String t12, String mainlabel, String l1,
			String l2, String l3, String l4, String md, String wd1, String wd2, String wd3, String wd4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";
		int count = 0;

		String thirdRow3 = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md
				+ "%'><div align='left' ><b> " + mainlabel + " </b></div></td>";
		if (!t9.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd1 + "%'><div align='left'> <b>" + l1 + "</b></div></td>";
		}
		if (!t10.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd2 + "%'><div align='left'><b> " + l2 + "</b></div></td>";
		}
		if (!t11.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd3 + "%'><div align='left'><b> " + l3 + " </b></div></td>";
		}
		if (!t12.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd4 + "%'><div align='left'><b>" + l4 + " </b></div></td>";
		}

		if (count < 4) {
			for (int k = count; k < 4; k++) {
				if (k < 2)
					thirdRow3 += "<td width='20%'></td>";
				else if (k == 2)
					thirdRow3 += "<td width='" + wd3 + "%'></td>";
				else if (k == 3)
					thirdRow3 += "<td width='" + wd4 + "%'></td>";
			}

		}
		tableString += thirdRow3 + "</tr>";

		/*
		 * if(!t9.equals("") || !t10.equals("") || !t11.equals("") || !t12.equals("") )
		 * {
		 * 
		 * int count80=0; String thirdRow4="<tr><td width='"
		 * +md+"%'><div align='left' style='margin-left: 10;'><b>  </b></div></td>";
		 * if(!t9.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd1+"%'><div align='left'>"+t9+"</div></td>"; }
		 * if(!t10.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd2+"%'><div align='left'>"+t10+"</div></td>"; }
		 * if(!t11.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd3+"%'><div align='left'>"+t11+"</div></td>"; }
		 * if(!t12.equals("")) { count80=count80+1;
		 * thirdRow4+="<td width='"+wd4+"%'><div align='left'>"+t12+"</div></td>"; }
		 * 
		 * if(count80<4) { for(int k=count80;k<4;k++) { if(k<2)
		 * thirdRow4+="<td width='20%'></td>"; else if(k==2)
		 * thirdRow4+="<td width='"+wd3+"%'></td>"; else if(k==3)
		 * thirdRow4+="<td width='"+wd4+"%'></td>"; }
		 * 
		 * } tableString+=thirdRow4+"</tr>"; }
		 */
		tableString += "</table>";
		return tableString;
	}

	public static String gethtmllforone(String t9, String t10, String t11, String t12, String mainlabel, String l1,
			String l2, String l3, String l4) {

		String firstRow = "<tr style='height:" + height + "'><td width='30%'><div align='left'><b>" + mainlabel
				+ "</b></div></td>";
		firstRow += "<td width='20%'><div align='left'>" + t9 + "</div></td><td width='20%'><div align='left'>" + t10
				+ "</div></td><td width='20%'></td><td width='10%'></td></tr>";

		return firstRow;

	}

	public static String gethtmllforone1(String t9, String t10, String t11, String t12, String mainlabel, String l1,
			String l2, String l3, String l4) {

		String firstRow = "<tr style='height:" + height + "'><td width='20%'><div align='left'><b>" + mainlabel
				+ "</b></div></td>";
		firstRow += "<td width='10%'><div align='left'>" + t9 + "</div></td><td width='20%'><div align='left'>" + t10
				+ "</div></td>";
		return firstRow;
	}

	public static String gethtmllforone2(String t9, String t10, String t11, String t12, String mainlabel, String l1,
			String l2, String l3, String l4) {

		String firstRow = "<td width='20%'><div align='left'><b>" + mainlabel + "</b></div></td>";
		firstRow += "<td width='10%'><div align='left'>" + t9 + "</div></td><td width='20%'><div align='left'>" + t10
				+ "</div></td></tr>";
		return firstRow;

	}

	public static String gethtmllfortwolabel(String t9, String t10, String t11, String t12, String mainlabel, String l1,
			String l2, String l3, String l4, String md, String wd1, String wd2, String wd3, String wd4) {

		String firstRow = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md
				+ "%'><div align='left'><b>" + mainlabel + "</b></div></td>";
		firstRow += "<td width='" + wd1 + "%'><div align='left'>" + t9 + "</div></td></tr></table>";

		return firstRow;

	}

	public static String gethtmll1td(String t9, String t10, String t11, String t12, String mainlabel, String l1,
			String l2, String l3, String l4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";

		int count = 0;

		String thirdRow3 = "<table width='100%' ><tr style='height:" + height
				+ "'><td width='30%'><div align='left' ><b> " + mainlabel + " </b></div></td>";

		if (!t9.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='70%' ><div align='left'> <b>" + l1 + "</b></div></td>";

			tableString += thirdRow3 + "</tr>";

			int count80 = 0;
			String thirdRow4 = "<tr><td width='30%'><div align='left' ><b>  </b></div></td>";

			count80 = count80 + 1;
			thirdRow4 += "<td width='70%'><div align='left'>" + t9 + "</div></td>";

			tableString += thirdRow4 + "</tr>";

		} else {
			tableString += thirdRow3 + "</tr>";
		}

		tableString += "</table>";

		/*
		 * if(t9.equals("")) { String
		 * thirdRow3="<table width='100%' ><tr><td width='30%'><div align='left' ><b> &nbsp; </b></div></td>"
		 * ;
		 * 
		 * count=count+1;
		 * thirdRow3+="<td width='70%' ><div align='left'> <b></b></div></td>";
		 * 
		 * tableString+=thirdRow3+"</tr>";
		 * 
		 * int count80=0; String
		 * thirdRow4="<tr><td width='30%'><div align='left' ><b>  </b></div></td>";
		 * 
		 * count80=count80+1;
		 * thirdRow4+="<td width='70%'><div align='left'></div></td>";
		 * 
		 * 
		 * 
		 * tableString+=thirdRow4+"</tr></table>"; }
		 */
		return tableString;
	}

	public static String gethtmlllabels(String t9, String t10, String t11, String t12, String mainlabel, String l1,
			String l2, String l3, String l4, String md, String wd1, String wd2, String wd3, String wd4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";
		int count = 0;
		String thirdRow3 = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md
				+ "%'><div align='left'><b> " + mainlabel + " </b></div></td>";
		if (!t9.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd1 + "%'><div align='left'>" + t9 + "</div></td>";
		}
		if (!t10.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd2 + "%'><div align='left'><b> " + l2 + "</b></div></td>";
		}
		if (!t11.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd3 + "%'><div align='left'><b> " + l3 + " </b></div></td>";
		}
		if (!t12.equals("")) {
			count = count + 1;
			thirdRow3 += "<td width='" + wd4 + "%'><div align='left'><b>" + l4 + " </b></div></td>";
		}

		if (count < 4) {
			for (int k = count; k < 4; k++) {
				if (k < 2)
					thirdRow3 += "<td width='" + wd1 + "%'></td>";
				else if (k == 2)
					thirdRow3 += "<td width='" + wd3 + "%'></td>";
				else if (k == 3)
					thirdRow3 += "<td width='" + wd4 + "%'></td>";
			}

		}
		tableString += thirdRow3 + "</tr>";

		if (!t10.equals("") || !t11.equals("") || !t12.equals("")) {
			int count80 = 0;
			String thirdRow4 = "<tr><td width='" + md + "%'><div align='left' ></div></td>";
			if (!t9.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd1 + "%'><div align='left'></div></td>";
			}
			if (!t10.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd2 + "%'><div align='left'>" + t10 + "</div></td>";
			}
			if (!t11.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd3 + "%'><div align='left'>" + t11 + "</div></td>";
			}
			if (!t12.equals("")) {
				count80 = count80 + 1;
				thirdRow4 += "<td width='" + wd4 + "%'><div align='left'>" + t12 + "</div></td>";
			}

			if (count80 < 4) {
				for (int k = count80; k < 4; k++) {
					if (k < 2)
						thirdRow4 += "<td width='" + wd1 + "%'></td>";
					else if (k == 2)
						thirdRow4 += "<td width='" + wd3 + "%'></td>";
					else if (k == 3)
						thirdRow4 += "<td width='" + wd4 + "%'></td>";
				}

			}

			tableString += thirdRow4 + "</tr>";
		}
		tableString += "</table>";

		return tableString;
	}

	public static String gethtmll(String t9, String t10, String t11, String t12, String mainlabel, String l1, String l2,
			String l3, String l4, String md, String wd1, String wd2, String wd3, String wd4) {

		if (t9.equals("-1"))
			t9 = "";

		if (t10.equals("-1"))
			t10 = "";

		if (t11.equals("-1"))
			t11 = "";

		if (t12.equals("-1"))
			t12 = "";

		String tableString = "";
		int count = 0;

		if (!t9.equals("") || !t10.equals("") || !t11.equals("") || !t12.equals("") || !mainlabel.equals("")) {

			String thirdRow3 = "<table width='100%' ><tr style='height:" + height + "'><td width='" + md
					+ "%'><div align='left' ><b> " + mainlabel + " </b></div></td>";
			if (!t9.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd1 + "%'><div align='left'> <b>" + l1 + "</b></div></td>";
			}
			if (!t10.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd2 + "%'><div align='left'><b> " + l2 + "</b></div></td>";
			}
			if (!t11.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd3 + "%'><div align='left'><b> " + l3 + " </b></div></td>";
			}
			if (!t12.equals("")) {
				count = count + 1;
				thirdRow3 += "<td width='" + wd4 + "%'><div align='left'><b>" + l4 + " </b></div></td>";
			}

			if (count < 4) {
				for (int k = count; k < 4; k++) {
					if (k < 2)
						thirdRow3 += "<td width='20%'></td>";
					else if (k == 2)
						thirdRow3 += "<td width='" + wd3 + "%'></td>";
					else if (k == 3)
						thirdRow3 += "<td width='" + wd4 + "%'></td>";
				}

			}
			tableString += thirdRow3 + "</tr>";

			if (!t9.equals("") || !t10.equals("") || !t11.equals("") || !t12.equals("")) {

				int count80 = 0;
				String thirdRow4 = "<tr><td width='" + md
						+ "%'><div align='left' style='margin-left: 10;'><b>  </b></div></td>";
				if (!t9.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd1 + "%'><div align='left'>" + t9 + "</div></td>";
				}
				if (!t10.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd2 + "%'><div align='left'>" + t10 + "</div></td>";
				}
				if (!t11.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd3 + "%'><div align='left'>" + t11 + "</div></td>";
				}
				if (!t12.equals("")) {
					count80 = count80 + 1;
					thirdRow4 += "<td width='" + wd4 + "%'><div align='left'>" + t12 + "</div></td>";
				}

				if (count80 < 4) {
					for (int k = count80; k < 4; k++) {
						if (k < 2)
							thirdRow4 += "<td width='20%'></td>";
						else if (k == 2)
							thirdRow4 += "<td width='" + wd3 + "%'></td>";
						else if (k == 3)
							thirdRow4 += "<td width='" + wd4 + "%'></td>";
					}

				}

				tableString += thirdRow4 + "</tr>";
			}
			tableString += "</table>";
		}
		return tableString;
	}

}