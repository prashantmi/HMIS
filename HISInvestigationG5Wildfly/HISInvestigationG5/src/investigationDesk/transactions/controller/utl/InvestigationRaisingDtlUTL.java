package investigationDesk.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.Inv_RequisitionRaisingEpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

import investigationDesk.InvestigationConfig;
import investigationDesk.transactions.controller.data.InvestigationRaisingDtlDATA;
import investigationDesk.transactions.controller.fb.InvestigationRaisingDtlFB;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;

public class InvestigationRaisingDtlUTL extends ControllerUTIL {
	public static void setPatientRegistrationEssentials(
			InvestigationRaisingDtlFB fb, HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		Map mp = new HashMap();
		HttpSession session = request.getSession();

		List<Inv_EpisodeVO> lstEpisodeVO = new ArrayList<Inv_EpisodeVO>();
		List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionVO = new ArrayList<Inv_PatientAdmissionDtlVO>();
		Map<String, Map<String, List<String>>> mpBookMark = new LinkedHashMap<String, Map<String, List<String>>>();
		String accountNo = "";
		// Is Category Expired Check
		boolean isCategoryExpired = false;

		/*PatientDetailVO selectedPatientVO = null;
		selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				

		fb.setPatCrNo(selectedPatientVO.getPatCrNo());*/

		try {
			Inv_RequisitionRaisingPatientVO patVO = null;
			if ((fb.getPatCrNo() != null)
					&& (fb.getPatCrNo().equals("") == false)) {
				patVO = new Inv_RequisitionRaisingPatientVO();
				Inv_RequisitionRaisingEpisodeVO episodeVO = new Inv_RequisitionRaisingEpisodeVO();
				WebUTIL.populate(patVO, fb);
				UserVO userVO = ControllerUTIL.getUserVO(request);
				patVO.setPatCRNo(fb.getPatCrNo());
                                                                           
				//String deptUnitCode = selectedPatientVO.getDepartmentUnitCode(); 
				  String deptUnitCode = fb.getDepartmentUnitCode();    //Modified by Vasu on 28.Nov.2017                                                                
				                                                                    // dept
																					// unit
																					// code
																					// required
																					// to
																					// fetch
																					// dept
																					// unit
																					// based
																					// bookmarks

				mp = InvestigationRaisingDtlDATA
						.getRequisitionRaisingEssentials(userVO);

				// Getting BookMark Details
				mpBookMark = InvestigationRaisingDtlDATA.getBookMarkDetails(
						userVO, deptUnitCode);

				// Getting Patient Details
				patVO = InvestigationRaisingDtlDATA
						.getPatientRegistration_EpisodeDetailEssentials(patVO,
								userVO);
				if (patVO != null && patVO.getPatCRNo() != null) {
					WebUTIL.populate(fb, patVO);

					if (patVO.getIsCatExpired() != null
							&& patVO.getIsCatExpired().equals(
									InvestigationConfig.YESNO_FLAG_NO)) {

						if (patVO.getPatStatus().equals("IPD")) {
							// IPD case

							// Use AdmissionVO to get details from DB
							accountNo = InvestigationRaisingDtlDATA
									.getAccountNo(patVO, userVO);
							fb.setAccountNo(accountNo);
							lstPatientAdmissionVO = InvestigationRaisingDtlDATA
									.getPatientAdmissionDetailEssentials(patVO,
											userVO);
							if (lstPatientAdmissionVO != null
									&& lstPatientAdmissionVO.size() > 0) {
								WebUTIL.populate(patVO,
										lstPatientAdmissionVO.get(0));
								fb.setAdmissionDate(patVO.getAdmissionDate());
							}

						} else // OPD case & Emergency case
						{
							lstEpisodeVO = InvestigationRaisingDtlDATA
									.getPatientEpisodeDetailEssentials(patVO,
											userVO);
							if (lstEpisodeVO != null && lstEpisodeVO.size() > 0)
								WebUTIL.populate(patVO, lstEpisodeVO.get(0));

						}

						// Setting Patient VO in session
						WebUTIL.setAttributeInSession(request,
								InvestigationConfig.PATIENT_VO, patVO);

						// For Putting AdmissionVO in session
						WebUTIL.setAttributeInSession(request,
								InvestigationConfig.LIST_EPISODE_VO,
								lstEpisodeVO);

						// For Putting AdmissionVO in session
						WebUTIL.setAttributeInSession(request,
								InvestigationConfig.LIST_ADMISSION_VO,
								lstPatientAdmissionVO);

						// setting BookMark in session
						WebUTIL.setAttributeInSession(request,
								InvestigationConfig.MAP_BOOK_MARK, mpBookMark);

						// Set Map in session
						WebUTIL.setMapInSession(mp, request);

						String lstSampleAccepted = (String) mp
								.get(InvestigationConfig.ARRAY_TESTNAMES);

						System.out.println("--ARRAY_TESTNAMES--:::"
								+ lstSampleAccepted);
					}// End isCatExpired
					else
						isCategoryExpired = true;

				}

				else {
					status.add(Status.ERROR_AE, "Patient Details Not Found ",
							"");
				}
			} else {

				status.add(Status.ERROR_AE, " Please Enter CR.No: ", "");
			}

			if (isCategoryExpired) {
				status.add(Status.ERROR_AE, " Patient Category Expired", "");
				status.add(Status.NEW, "", "");
			} else {
				if (patVO.getPatStatus().equals("IPD")) {

					// if(Integer.parseInt(accountNo)<=0)
					if (accountNo == null || "0".equals(accountNo))

					{
						status.add(Status.ERROR_AE,
								" Patient Account does not exist", "");
						throw new HisRecordNotFoundException();
					} else {
						status.add(Status.TRANSINPROCESS, "", "");
					}
				} else
					status.add(Status.TRANSINPROCESS, "", "");
			}
		} catch (Exception e) {
			// status.add(Status.ERROR_AE,"Application Execution Exception",
			// "");
			status.add(Status.NEW, "", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static void searchLaboratoryWiseTest(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");

		HttpSession session = request.getSession();
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			searchVO.setSearchLabName(fb.getSearchLabName() == null ? "" : fb
					.getSearchLabName());
			searchVO.setSearchTestName(fb.getSearchTestName() == null ? "" : fb
					.getSearchTestName());
			searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue() == null ? ""
					: fb.getTstOrTestGroupValue());
			mp = InvestigationRaisingDtlDATA.searchLabWiseTestDetails(searchVO,
					userVO);

			if (searchVO.getSearchTestGroupName() == null
					|| !searchVO.getTstOrTestGroupValue().equals("1")) {

				session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
			}

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");

			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static void searchLaboratoryWiseTestGroupOnClick(
			InvestigationRaisingDtlFB fb, HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");

		HttpSession session = request.getSession();
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			searchVO.setSearchLabName(fb.getSearchLabName() == null ? "" : fb
					.getSearchLabName());
			searchVO.setSearchTestName(fb.getSearchTestName() == null ? "" : fb
					.getSearchTestName());
			searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue());
			mp = InvestigationRaisingDtlDATA
					.searchLaboratoryWiseTestGroupOnClick(searchVO, userVO);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");

			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static void deleteRow(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status objStatus = new Status();
		// List indicationIdList=new ArrayList();
		HttpSession session = WebUTIL.getSession(request);

		try {
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="
					+ fb.getLabTestCodeArray());
			String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");

			List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);

			String tmpLabCodeHashTestCode = fb.getTmpLabCode() + "#"
					+ fb.getTmpTestCode();

			List<String> newLstLabTestCodeArray = new ArrayList<String>();

			boolean firstIteration = true;
			String strLabTestCodes = "";

			Iterator itr = lstLabTestCodeArray.iterator();
			while (itr.hasNext()) {
				String str = (String) itr.next();
				str = str.replace(";", "#");
				String[] arrStr = str.split("#"); // chkVal Order
													// LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType
				String labCodeHashTestCode = arrStr[0] + "#" + arrStr[2]; // labCode#testCode

				if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
					continue;
				else {
					// newLstLabTestCodeArray.add(str.replace(";","#"));
					if (firstIteration) {
						strLabTestCodes = str;
						firstIteration = false;
					} else {

						strLabTestCodes = strLabTestCodes + "@" + str;
					}
				}
			}

			System.out.println("strLabTestCodes= " + strLabTestCodes);

			fb.setLabTestCodeArray(strLabTestCodes);

			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(request, objStatus);
		}

	}

	// Save Logic
	public static void saveRequisitionDetails(InvestigationRaisingDtlFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		List bldReqIndicationDtlVOList = new ArrayList();
		String deskType = (String) session
				.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		Map<String, Map<String, LabTestVO>> mp = new LinkedHashMap<String, Map<String, LabTestVO>>();
		try {
			Date sysdate = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString = WebUTIL.getCustomisedSysDate(sysdate,
					"dd-MMM-yyyy hh:mm");
			UserVO _userVO = getUserVO(_request);
			// HelperMethods.populate(bloodRequisitionDtlVO, _fb);
			Object objLabTestVo = _request.getSession().getAttribute(
					InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
			List<LabTestVO> objLabTest = null;
			if (objLabTestVo != null) {
				objLabTest = (List<LabTestVO>) objLabTestVo;
			}
			// Getting PATVO from Session
			Inv_RequisitionRaisingPatientVO patVO = (Inv_RequisitionRaisingPatientVO) session
					.getAttribute(InvestigationConfig.PATIENT_VO);

			/*
			 * DateFormat dateFormat = new
			 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Calendar cal =
			 * Calendar.getInstance();
			 * System.out.println(dateFormat.format(cal.getTime()));
			 */

			patVO.setAdvisedByDocName(_fb.getAdvisedBy());
			patVO.setAdvisedByDocNo(_fb.getAdvisedByDoctorName());
			patVO.setAdmissionDate(_fb.getAdmissionDate());
			patVO.setPatCategoryCode(_fb.getPatCatCode());
			if (patVO != null) {

				// int labRowCount=Integer.parseInt(_fb.getNumberOfRow());

				String[] selectedLabTestCodeArray = _fb.getLabTestCodeArray()
						.split("@");

				int labRowCount = selectedLabTestCodeArray.length;

				for (int i = 0; i < labRowCount; i++) {

					String[] testCodeArray = selectedLabTestCodeArray[i]
							.split("#");
					String labCode = testCodeArray[0];
					String labName = testCodeArray[1];
					String testCode = testCodeArray[2];
					String testName = testCodeArray[3];
					String strDefSample = "-1";
					for (LabTestVO objTmpLabTestVO : objLabTest) {
						if (labCode.equals(objTmpLabTestVO.getLabCode())
								&& testCode.equals(objTmpLabTestVO
										.getTestCode())) {
							strDefSample = objTmpLabTestVO
									.getDefaultSampleCode();
						}
					}
					String sampleCombo = testCodeArray[4];
					String testType = testCodeArray[5];
					String isAppointment = testCodeArray[6].split("\\$")[0];
					String isOpdDesk = testCodeArray[6].split("\\$")[1];
					String isBayDesk = testCodeArray[6].split("\\$")[2];
					String isIpdDesk = testCodeArray[6].split("\\$")[3];
					String raiseAdvise = "";

					if (deskType.equals("1"))
						raiseAdvise = isOpdDesk;
					else if (deskType.equals("12"))
						raiseAdvise = isBayDesk;
					else if (deskType.equals("4"))
						raiseAdvise = isIpdDesk;
					else
						raiseAdvise = isOpdDesk;
					String isConfidential = testCodeArray[7];
					String sampleCode = (testCodeArray[8] == null
							|| testCodeArray[8].equals(null)
							|| testCodeArray[8].equals("null")
							|| testCodeArray[8].equals("-1") ? strDefSample
							: testCodeArray[8]);
					String priority = (testCodeArray[9] == null
							|| testCodeArray[9].equals(null)
							|| testCodeArray[9].equals("null") ? "1"
							: testCodeArray[9]);
					String testGroupCode = (testCodeArray[10] == null
							|| testCodeArray[10].equals(null)
							|| testCodeArray[10].equals("null") ? "0"
							: testCodeArray[10]);
					String testGroupType = (testCodeArray[11] == null
							|| testCodeArray[11].equals(null)
							|| testCodeArray[11].equals("null") ? "1"
							: testCodeArray[11]);
					// String
					// appoitmentNo=testCodeArray[19]==null||testCodeArray[19].equals(null)||testCodeArray[19].equals("null")?"0":testCodeArray[19];
					Map<String, LabTestVO> mpTest = mp.get(labCode);

					// First Time Insertion of Lab
					if (mpTest == null) {
						mpTest = new LinkedHashMap<String, LabTestVO>();

						LabTestVO voLabTest = new LabTestVO();

						// Setting VO Values from labStringArray
						voLabTest.setLabCode(labCode);
						voLabTest.setTestCode(testCode);
						if (testType.equals("P"))
							voLabTest.setSampleCode("-1");
						else
							voLabTest.setSampleCode(sampleCode);
						voLabTest.setPatCrNo(_fb.getPatCrNo());
						voLabTest.setTestType(testType);
						voLabTest.setIsAppointment(isAppointment);
						voLabTest.setRaiseAdvise(raiseAdvise);
						voLabTest.setIsConfidential(isConfidential);
						voLabTest.setPriority(priority);

						voLabTest.setLabName(labName);
						voLabTest.setTestName(testName);

						voLabTest.setTestGroupCode(testGroupCode);

						voLabTest.setTestGroupType(testGroupType);

						String[] splitsysDate = sysadteString.split(" ");

						voLabTest.setAppointmentDate(splitsysDate[0]);
						voLabTest.setAppointmentTime(splitsysDate[1]);
						voLabTest.setFinalMandValues(_fb.getFinalMandValues());
						voLabTest.setAdvisedByDoctorName(_fb
								.getAdvisedByDoctorName());
						voLabTest.setAdvisedBy(_fb.getAdvisedBy());
						voLabTest.setReqDateHeaderTable(splitsysDate[0]);
						if (_fb.getFinalMandCode().equals("undefined")) {
							voLabTest.setFinalMandCode(_fb
									.getFinalMandCodeValuesOnBookmark());
						} else {
							voLabTest.setFinalMandCode(_fb.getFinalMandCode());

						}

						if (raiseAdvise.equals("1")) {
							if (isAppointment.equals("1")) {

								// as appointment is not being raised from desk
								// but only advised
								/*
								 * voLabTest.setAppointmentRefNo("");
								 * voLabTest.setAppoitmentNo("0");
								 * voLabTest.setAppointmentDate("");
								 * voLabTest.setAppointmentTime("");
								 */

								
								//fetch appointment details from the array from fb
								if(_fb.getLabTestAptDate()!=null && _fb.getLabTestAptDate().length>0)
								{
										String apptArrayDate[]=_fb.getLabTestAptDate()[0].split(",");
										String apptArrayTime[]=_fb.getLabTestAptTime()[0].split(",");
										String apptArrayRefNo[]=_fb.getLabTestAptRefNo()[0].split(",");

										for(int labLen=0;labLen<apptArrayDate.length;labLen++)
										{
										
										if((voLabTest.getLabCode()+voLabTest.getTestCode()).equals(apptArrayDate[labLen].split("#")[0]))
										{
											voLabTest.setAppointmentDate(apptArrayDate[labLen].split("#")[1]);
											voLabTest.setAppointmentRefNo(apptArrayRefNo[labLen].split("#")[1]);
											voLabTest.setAppoitmentNo(apptArrayRefNo[labLen].split("#")[1]);
											voLabTest.setAppointmentTime(apptArrayTime[labLen].split("#")[1]);
										}
										}
									
									
									
								}
								else	
								{								
									
								voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
								voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
								voLabTest.setAppointmentDate(_fb.getAppointmentDate());
								voLabTest.setAppointmentTime(_fb.getAppointmentTime());
								
								}
							} else {
								voLabTest.setAppoitmentNo("0");
							}
						} else if (raiseAdvise.equals("0")) {
							if (isAppointment.equals("1")) {
								voLabTest.setAppointmentRefNo("");
								voLabTest.setAppoitmentNo("0");
								voLabTest.setAppointmentDate("");
								voLabTest.setAppointmentTime("");
							} else {
								voLabTest.setAppoitmentNo("0");
							}
						}

						// Still Some values need to be inserted

						mpTest.put(testCode, voLabTest);

						mp.put(labCode, mpTest);

					} else {

						LabTestVO voLabTest = new LabTestVO();

						// Setting VO Values from labStringArray
						voLabTest.setLabCode(labCode);
						voLabTest.setTestCode(testCode);
						if (testType.equals("P"))
							voLabTest.setSampleCode("-1");
						else
							voLabTest.setSampleCode(sampleCode);
						voLabTest.setPatCrNo(_fb.getPatCrNo());
						voLabTest.setTestType(testType);
						voLabTest.setIsAppointment(isAppointment);
						voLabTest.setRaiseAdvise(raiseAdvise);
						voLabTest.setIsConfidential(isConfidential);
						voLabTest.setPriority(priority);

						voLabTest.setLabName(labName);
						voLabTest.setTestName(testName);

						voLabTest.setTestGroupCode(testGroupCode);

						voLabTest.setTestGroupType(testGroupType);

						String[] splitsysDate = sysadteString.split(" ");

						voLabTest.setAppointmentDate(splitsysDate[0]);
						voLabTest.setAppointmentTime(splitsysDate[1]);

						voLabTest.setReqDateHeaderTable(splitsysDate[0]);
						voLabTest.setFinalMandValues(_fb.getFinalMandValues());

						voLabTest.setFinalMandCode(_fb.getFinalMandCode());
						voLabTest.setAdvisedByDoctorName(_fb
								.getAdvisedByDoctorName());
						voLabTest.setAdvisedBy(_fb.getAdvisedBy());
						if (raiseAdvise.equals("1")) {
							if (isAppointment.equals("1")) {

								// as appointment is not being raised from desk
								// but only advised
								/*
								 * voLabTest.setAppointmentRefNo("");
								 * voLabTest.setAppoitmentNo("0");
								 * voLabTest.setAppointmentDate("");
								 * voLabTest.setAppointmentTime("");
								 */

								
								//fetch appointment details from the array from fb
								if(_fb.getLabTestAptDate()!=null && _fb.getLabTestAptDate().length>0)
								{
										String apptArrayDate[]=_fb.getLabTestAptDate()[0].split(",");
										String apptArrayTime[]=_fb.getLabTestAptTime()[0].split(",");
										String apptArrayRefNo[]=_fb.getLabTestAptRefNo()[0].split(",");

										for(int labLen=0;labLen<apptArrayDate.length;labLen++)
										{
										
										if((voLabTest.getLabCode()+voLabTest.getTestCode()).equals(apptArrayDate[labLen].split("#")[0]))
										{
											voLabTest.setAppointmentDate(apptArrayDate[labLen].split("#")[1]);
											voLabTest.setAppointmentRefNo(apptArrayRefNo[labLen].split("#")[1]);
											voLabTest.setAppoitmentNo(apptArrayRefNo[labLen].split("#")[1]);
											voLabTest.setAppointmentTime(apptArrayTime[labLen].split("#")[1]);
										}
										}
									
									
									
								}
								else	
								{								
									
								voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
								voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
								voLabTest.setAppointmentDate(_fb.getAppointmentDate());
								voLabTest.setAppointmentTime(_fb.getAppointmentTime());
								
								}
							} else {
								voLabTest.setAppoitmentNo("0");
							}
						} else if (raiseAdvise.equals("0")) {
							if (isAppointment.equals("1")) {
								voLabTest.setAppointmentRefNo("");
								voLabTest.setAppoitmentNo("0");
								voLabTest.setAppointmentDate("");
								voLabTest.setAppointmentTime("");
							} else {
								voLabTest.setAppoitmentNo("0");
							}
						}
						// Still Some values need to be inserted

						mpTest.put(testCode, voLabTest);
					}

				}

				List listReqdtlId = InvestigationRaisingDtlDATA
						.saveRequisitionDetails(mp, patVO, _userVO);

				StringBuilder str = new StringBuilder();
				for (int i = 0; i < 1; i++) {
					String saveString = (String) listReqdtlId.get(i);
					String[] arrSaveString = saveString.split("#");

					str.append("<br>");
					// <table width='80%' border='1'><tr>");
					// str.append("<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
					str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
					str.append(" Requisition Raised Successfully for Patient CR Number::"
							+ "</font>");
					// str.append(
					// " Requisition Raised Successfully for Patient CR Number::"
					// + "</font></td>");
					// str.append( "<td width='20%' align='left' >");
					str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
					str.append((arrSaveString[0]) + "</font>");

					/*
					 * str.append(
					 * "<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "
					 * ); str.append( "Laboratory::" + "</font></td>");
					 * str.append( "<td width='20%' align='left' >");
					 * str.append(
					 * "<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
					 * ); str.append((arrSaveString[1])+ "</font>" + "</td>");
					 * 
					 * str.append(
					 * "<td width='20%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "
					 * ); str.append( "Test::" + "</font></td>"); str.append(
					 * "<td width='20%' align='left' >"); str.append(
					 * "<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
					 * ); str.append((arrSaveString[2])+ "</font>" + "</td>");
					 */

					// str.append("</tr></table>");
				}

				objStatus.add(Status.DONE, str.toString(),
						"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
								+ "" + "</font>");

			}

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

	// public static void searchBookMark(InvestigationRaisingDtlFB
	// fb,HttpServletRequest request)
	// {
	// Status status = new Status();
	// //status.add(Status.NEW, "", "");
	// Map mp=new HashMap();
	// try{
	// InvestigationSearchVO searchVO=new InvestigationSearchVO();
	// UserVO userVO=ControllerUTIL.getUserVO(request);
	//
	//
	// searchVO.setSearchLabName(fb.getSearchLabName());
	// searchVO.setSearchTestName(fb.getSearchTestName());
	//
	// //Resetting the LabCodeArray ; for selected lab types
	// //fb.setLabTestCodeArray("");
	//
	// String labTestCodeArray=fb.getLabTestCodeArray();
	//
	// //Logic to check whether the lab/test is already present in the list
	// String[] labTestCatalogue=labTestCodeArray.split("@");
	//
	// Set<String> setLabCatalogue=new HashSet<String>();
	// if(labTestCatalogue!=null && labTestCatalogue.length>0)
	// {
	// for(String str:labTestCatalogue)
	// {
	// if(!str.equals("")&&str!=null)
	// {
	// String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];//
	// labCode#testCode
	// setLabCatalogue.add(labCodeHashTestCode);
	// }
	// }
	// }
	//
	// mp=InvestigationRaisingDtlDATA.searchLabWiseTestDetails(searchVO,userVO);
	//
	// List<LabTestVO>
	// lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
	//
	// for(LabTestVO vo:lstLabTestVO)
	// {
	// String appendString=makeAppendString(vo,false);
	// //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1";
	// //as is not group based test
	// //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
	//
	// String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
	// //Add only those Lab/Tests which are not present in the list
	// if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
	// {
	// if(labTestCodeArray.equals(""))
	// labTestCodeArray=appendString;
	// else
	// labTestCodeArray=labTestCodeArray+"@"+appendString;
	// }
	//
	// }
	// fb.setLabTestCodeArray(labTestCodeArray);
	//
	// //Resetting LabCode,TestCode,SampleCode Arrays
	// String[] strNull=null;
	//
	// fb.setLabCode(strNull);
	// fb.setTestCode(strNull);
	// fb.setSampleInfo(strNull);
	//
	// WebUTIL.setMapInSession(mp, request);
	//
	// //status.add(Status.NEW, "", "");
	// status.add(Status.TRANSINPROCESS, "", "");
	// }
	// catch(Exception e){
	// status.add(Status.ERROR_AE,"Application Execution Exception", "");
	// e.printStackTrace();
	// }
	// finally{
	// WebUTIL.setStatus(request, status);
	// }
	// }

	public static void searchBookMark(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");

		PatientDetailVO selectedPatientVO = null;
		selectedPatientVO = (PatientDetailVO) request.getSession()
				.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);

		String deptUnitCode = selectedPatientVO.getDepartmentUnitCode(); // dept
																			// unit
																			// code
																			// required
																			// to
																			// fetch
																			// dept
																			// unit
																			// based
																			// bookmarks

		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);

			ControllerUTIL.setSysdate(request);
			Date dt = (Date) request.getSession().getAttribute(
					Config.SYSDATEOBJECT);
			WebUTIL.getSession(request).setAttribute(
					InvestigationConfig.SYSDATEOBJECT, dt);

			searchVO.setDeptUnitCode(deptUnitCode);
			searchVO.setSearchLabName("");
			searchVO.setSearchTestName(fb.getSearchTestName());

			searchVO.setBookMarkCode(fb.getBookMarkCode());

			// logic For Offline Appoitment Based Test Details
			searchVO.setTestCode(fb.getAptTestCode());

			String offlineAptDateAndTime = fb.getOfflineAppoitMentDate();

			// Logic to check whether the lab/test is already present in the
			// list
			String[] OfflineAppoitmentDtl = offlineAptDateAndTime.split("@");

			String hidAotNo = fb.getHidAptNo();

			// Logic to check whether the lab/test is already present in the
			// list
			String[] hideAptNoDtl = hidAotNo.split("`");

			int i = 0;

			// Resetting the LabCodeArray ; for selected lab types
			// fb.setLabTestCodeArray("");

			String labTestCodeArray = fb.getLabTestCodeArray();

			// Logic to check whether the lab/test is already present in the
			// list
			String[] labTestCatalogue = labTestCodeArray.split("@");

			Set<String> setLabCatalogue = new HashSet<String>();
			if (labTestCatalogue != null && labTestCatalogue.length > 0) {
				for (String str : labTestCatalogue) {
					if (!str.equals("") && str != null) {
						String labCodeHashTestCode = str.split("#")[0] + "#"
								+ str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}

			mp = InvestigationRaisingDtlDATA.searchBookMark(searchVO, userVO);

			List<LabTestVO> lstLabTestVO = (List<LabTestVO>) mp
					.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK);

			for (LabTestVO vo : lstLabTestVO) {

				if (OfflineAppoitmentDtl != null
						&& !OfflineAppoitmentDtl.equals("")
						&& searchVO.getBookMarkCode().equals("")) {
					vo.setOfflineAppoitMentDate(OfflineAppoitmentDtl[i]);
					vo.setHideAptNo(hideAptNoDtl[i]);
				}

				vo.setBookMarkCode(fb.getBookMarkCode());

				String appendString = makeAppendString(vo, false); // vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1";
																	// //as is
																	// not group
																	// based
																	// test
																	// //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());

				String tmpLabCodeHashTestCode = vo.getLabCode() + "#"
						+ vo.getTestCode();
				// Add only those Lab/Tests which are not present in the list
				if (!setLabCatalogue.contains(tmpLabCodeHashTestCode)) {
					if (labTestCodeArray.equals(""))
						labTestCodeArray = appendString;
					else
						labTestCodeArray = labTestCodeArray + "@"
								+ appendString;
				}
				i++;
			}
			fb.setLabTestCodeArray(labTestCodeArray);

			// Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull = null;

			fb.setLabCode(strNull);
			fb.setTestCode(strNull);
			fb.setSampleInfo(strNull);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	/**
	 * AJAX Response : Modifying LabTestCodeArray
	 * 
	 * @param objFB_p
	 * @param objRequest_p
	 *            Added By Pawan Kumar B N on 2011.12.21
	 */
	public static StringBuffer deleteLabTestCodeArray(
			InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try {
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="
					+ fb.getLabTestCodeArray());
			String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");

			List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);

			String tmpLabCodeHashTestCode = fb.getTmpLabCode() + "#"
					+ fb.getTmpTestCode();

			List<String> newLstLabTestCodeArray = new ArrayList<String>();

			boolean firstIteration = true;
			String strLabTestCodes = "";

			Iterator itr = lstLabTestCodeArray.iterator();
			while (itr.hasNext()) {
				String str = (String) itr.next();
				str = str.replace(";", "#");
				String[] arrStr = str.split("#"); // chkVal Order
													// LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType
				String labCodeHashTestCode = arrStr[0] + "#" + arrStr[2]; // labCode#testCode

				if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
					continue;
				else {
					// newLstLabTestCodeArray.add(str.replace(";","#"));
					if (firstIteration) {
						strLabTestCodes = str;
						firstIteration = false;
					}
					strLabTestCodes = strLabTestCodes + "@" + str;
				}
			}

			System.out.println("strLabTestCodes= " + strLabTestCodes);

			fb.setLabTestCodeArray(strLabTestCodes);

			// sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(strLabTestCodes);
			// sbAjaxRes.append("\'");
			// sbAjaxRes.append("}]");

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> "
					+ e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " +
			// eObj.getErrorID() + "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return sbAjaxRes;
	}

	/**
	 * AJAX Response : Modifying LabTestCodeArray
	 * 
	 * @param objFB_p
	 * @param objRequest_p
	 *            Added By Pawan Kumar B N on 2011.12.21
	 */
	public static StringBuffer modifyLabTestCodeArray(
			InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try {
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="
					+ fb.getLabTestCodeArray());
			String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");

			List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);

			String tmpLabCodeHashTestCode = fb.getTmpLabCode() + "#"
					+ fb.getTmpTestCode();

			String tmpSampleCode = fb.getTmpSampleCode();

			String strLabTestCodes = "";
			boolean firstIteration = true;

			for (String str : lstLabTestCodeArray) {
				str = str.replace(";", "#");

				if (str.contains("*"))
					str = str.replace("*", "&");

				String[] arrStr = str.split("#"); // chkVal Order
													// LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType
				String labCodeHashTestCode = arrStr[0] + "#" + arrStr[2]; // labCode#testCode

				if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode)) {
					// Change the Sample Code
					str = arrStr[0] + "#" + arrStr[1] + "#" + arrStr[2] + "#"
							+ arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5]
							+ "#" + arrStr[6] + "#" + arrStr[7] + "#"
							+ tmpSampleCode + "#" + arrStr[9] + "#"
							+ arrStr[10] + "#" + arrStr[11] + "#" + arrStr[12]
							+ "#" + arrStr[13] + "#" + arrStr[14] + "#"
							+ arrStr[15] + "#" + arrStr[16] + "#" + arrStr[17]
							+ "#" + arrStr[18] + "#" + arrStr[19];
				}

				if (firstIteration) {
					strLabTestCodes = str;
					firstIteration = false;
				} else {

					strLabTestCodes = strLabTestCodes + "@" + str;
				}
			}
			System.out.println("strLabTestCodes= " + strLabTestCodes);

			fb.setLabTestCodeArray(strLabTestCodes);

			// sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(strLabTestCodes);
			// sbAjaxRes.append("\'");
			// sbAjaxRes.append("}]");

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> "
					+ e.getMessage();
			// HisException eObj =
			e.printStackTrace();
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " +
			// eObj.getErrorID() + "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return sbAjaxRes;
	}

	/**
	 * AJAX Response : Modifying LabTestCodeArray
	 * 
	 * @param objFB_p
	 * @param objRequest_p
	 *            Added By Pawan Kumar B N on 2011.12.21
	 */
	public static StringBuffer modifyPriority(InvestigationRaisingDtlFB fb,
			HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try {
			System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::="
					+ fb.getLabTestCodeArray());
			String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");

			List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);

			String tmpLabCodeHashTestCode = fb.getTmpLabCode() + "#"
					+ fb.getTmpTestCode();

			String tmpPriority = fb.getTmpPriority();

			String strLabTestCodes = "";
			boolean firstIteration = true;

			for (String str : lstLabTestCodeArray) {
				str = str.replace(";", "#");

				String[] arrStr = str.split("#"); // chkVal Order
													// LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode
				String labCodeHashTestCode = arrStr[0] + "#" + arrStr[2]; // labCode#testCode

				if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode)) {
					// Change the Sample Code
					str = arrStr[0] + "#" + arrStr[1] + "#" + arrStr[2] + "#"
							+ arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5]
							+ "#" + arrStr[6] + "#" + arrStr[7] + "#"
							+ arrStr[8] + "#" + tmpPriority + "#" + arrStr[10]
							+ "#" + arrStr[11] + "#" + null + "#" + null + "#"
							+ null + "#" + null + "#" + null + "#" + null;
				}

				if (firstIteration) {
					strLabTestCodes = str;
					firstIteration = false;
				} else
					strLabTestCodes = strLabTestCodes + "@" + str;
			}

			System.out.println("strLabTestCodes= " + strLabTestCodes);

			fb.setLabTestCodeArray(strLabTestCodes);

			// sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(strLabTestCodes);
			// sbAjaxRes.append("\'");
			// sbAjaxRes.append("}]");

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> "
					+ e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " +
			// eObj.getErrorID() + "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return sbAjaxRes;
	}

	/**
	 * AJAX Response : populating Test Combo based on labCode using AJAX
	 * 
	 * @param objFB_p
	 * @param objRequest_p
	 *            Added By Pawan Kumar B N on 2011.12.21 pouplatePrvTestDtl
	 */
	public static StringBuffer pouplateTestCombo(InvestigationRaisingDtlFB fb,
			HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session = objRequest_p.getSession();
		String strMsgText = "";
		String strTestCombo = "";
		Map mp = new HashMap();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
			String labCode = fb.getTmpLabCode();
			objRequest_p.getSession().setAttribute("a", strTestCombo);

			// strTestCombo=InvestigationRaisingDtlDATA.getTestComboAJAX(labCode,userVO);
			// session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			mp = InvestigationRaisingDtlDATA.getTestComboAJAXMAP(labCode,
					userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);
			String lstSampleAccepted = (String) mp
					.get(InvestigationConfig.ARRAY_TESTNAMES_AJAX);
			sbAjaxRes.append(lstSampleAccepted);

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> "
					+ e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " +
			// eObj.getErrorID() + "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return sbAjaxRes;
	}

	public static StringBuffer pouplatePrvTestDtl(InvestigationRaisingDtlFB fb,
			HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();

		HttpSession session = objRequest_p.getSession();
		String strMsgText = "";
		String strTestCombo = "";
		Map mp = new HashMap();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
			String CrNo = fb.getTmpCrNo();

			// strTestCombo=InvestigationRaisingDtlDATA.getTestComboAJAX(labCode,userVO);
			// session.removeAttribute(InvestigationConfig.ARRAY_TESTNAMES);
			mp = InvestigationRaisingDtlDATA.getPrvTestDtlAJAXMAP(CrNo, userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);

			String lstSampleAccepted = (String) mp
					.get(InvestigationConfig.LIST_PRVTESTDTL_AJAX);

			fb.setPrvTestDtl(lstSampleAccepted);
			System.out.println("-------------->" + lstSampleAccepted);
			sbAjaxRes.append(lstSampleAccepted);

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> "
					+ e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " +
			// eObj.getErrorID() + "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return sbAjaxRes;
	}

	public static void searchTestGroup(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());

			// Resetting the LabCodeArray ; for selected lab types
			// fb.setLabTestCodeArray("");

			String labTestCodeArray = fb.getLabTestCodeArray();

			// Logic to check whether the lab/test is already present in the
			// list
			String[] labTestCatalogue = labTestCodeArray.split("@");

			Set<String> setLabCatalogue = new HashSet<String>();
			if (labTestCatalogue != null && labTestCatalogue.length > 0) {
				for (String str : labTestCatalogue) {
					if (!str.equals("") && str != null) {
						String labCodeHashTestCode = str.split("#")[0] + "#"
								+ str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}

			mp = InvestigationRaisingDtlDATA.searchTestGroup(searchVO, userVO);

			List<LabTestVO> lstLabTestVO = (List<LabTestVO>) mp
					.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);

			for (LabTestVO vo : lstLabTestVO) {
				String appendString = makeAppendString(vo, true); // vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());

				String tmpLabCodeHashTestCode = vo.getLabCode() + "#"
						+ vo.getTestCode();
				// Add only those Lab/Tests which are not present in the list
				if (!setLabCatalogue.contains(tmpLabCodeHashTestCode)) {
					if (labTestCodeArray.equals(""))
						labTestCodeArray = appendString;
					else
						labTestCodeArray = labTestCodeArray + "@"
								+ appendString;
				}

			}
			fb.setLabTestCodeArray(labTestCodeArray);

			// Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull = null;

			fb.setLabCode(strNull);
			fb.setTestCode(strNull);
			fb.setSampleInfo(strNull);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	/**
	 * AJAX Response : populating Test Group based on labCode using AJAX
	 * 
	 * @param objFB_p
	 * @param objRequest_p
	 *            Added By Pawan Kumar B N on 2011.12.21
	 */
	public static StringBuffer pouplateTestGroup(InvestigationRaisingDtlFB fb,
			HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession session = objRequest_p.getSession();
		String strMsgText = "";
		String strTestCombo = "";
		Map mp = new HashMap();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
			String labCode = fb.getTmpLabCode();
			strTestCombo = InvestigationRaisingDtlDATA.getTestGroupAJAX(
					labCode, userVO);
			sbAjaxRes.append(strTestCombo);

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> "
					+ e.getMessage();
			// HisException eObj =
			new HisException(strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " +
			// eObj.getErrorID() + "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return sbAjaxRes;
	}

	public static void getTestsBasedOnGroups(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			// Resetting the LabCodeArray ; for selected lab types
			// fb.setLabTestCodeArray("");

			String labTestCodeArray = fb.getLabTestCodeArray();

			// Logic to check whether the lab/test is already present in the
			// list
			String[] labTestCatalogue = labTestCodeArray.split("@");

			Set<String> setLabCatalogue = new HashSet<String>();
			if (labTestCatalogue != null && labTestCatalogue.length > 0) {
				for (String str : labTestCatalogue) {
					if (!str.equals("") && str != null) {
						String labCodeHashTestCode = str.split("#")[0] + "#"
								+ str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}

			mp = InvestigationRaisingDtlDATA.getTestsBasedOnGroups(searchVO,
					userVO);

			List<LabTestVO> lstLabTestVO = (List<LabTestVO>) mp
					.get(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);

			for (LabTestVO vo : lstLabTestVO) {

				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				vo.setTestGroupType("1");
				String appendString = makeAppendString(vo, true); // vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());

				String tmpLabCodeHashTestCode = vo.getLabCode() + "#"
						+ vo.getTestCode();
				// Add only those Lab/Tests which are not present in the list
				if (!setLabCatalogue.contains(tmpLabCodeHashTestCode)) {
					if (labTestCodeArray.equals(""))
						labTestCodeArray = appendString;
					else
						labTestCodeArray = labTestCodeArray + "@"
								+ appendString;
				}

			}
			fb.setLabTestCodeArray(labTestCodeArray);

			// Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull = null;

			fb.setLabCode(strNull);
			fb.setTestCode(strNull);
			fb.setSampleInfo(strNull);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static void searchTest(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			searchVO.setBookMarkCode(fb.getBookMarkCode());

			// Resetting the LabCodeArray ; for selected lab types
			// fb.setLabTestCodeArray("");

			String labTestCodeArray = fb.getLabTestCodeArray();

			// Logic to check whether the lab/test is already present in the
			// list
			String[] labTestCatalogue = labTestCodeArray.split("@");

			Set<String> setLabCatalogue = new HashSet<String>();
			if (labTestCatalogue != null && labTestCatalogue.length > 0) {
				for (String str : labTestCatalogue) {
					if (!str.equals("") && str != null) {
						String labCodeHashTestCode = str.split("#")[0] + "#"
								+ str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}

			mp = InvestigationRaisingDtlDATA.searchBookMark(searchVO, userVO);

			List<LabTestVO> lstLabTestVO = (List<LabTestVO>) mp
					.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);

			for (LabTestVO vo : lstLabTestVO) {
				String appendString = makeAppendString(vo, false); // vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"1";
																	// //as is
																	// not group
																	// based
																	// test
																	// //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());

				String tmpLabCodeHashTestCode = vo.getLabCode() + "#"
						+ vo.getTestCode();
				// Add only those Lab/Tests which are not present in the list
				if (!setLabCatalogue.contains(tmpLabCodeHashTestCode)) {
					if (labTestCodeArray.equals(""))
						labTestCodeArray = appendString;
					else
						labTestCodeArray = labTestCodeArray + "@"
								+ appendString;
				}

			}
			fb.setLabTestCodeArray(labTestCodeArray);

			// Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull = null;

			fb.setLabCode(strNull);
			fb.setTestCode(strNull);
			fb.setSampleInfo(strNull);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static String makeAppendString(LabTestVO vo, boolean isGroupBased) {
		String appendString = "";
		if (isGroupBased)
			appendString = vo.getLabCode()
					+ "#"
					+ vo.getLabName()
					+ "#"
					+ vo.getTestCode()
					+ "#"
					+ vo.getTestName()
					+ "#"
					+ vo.getSampleComboStr()
					+ "#"
					+ vo.getTestType()
					+ "#"
					+ vo.getIsAppointment()
					+ "$"
					+ vo.getDeskProperties()
					+ "#"
					+ vo.getIsConfidential()
					+ "#"
					+ vo.getSampleCode()
					+ "#"
					+ (vo.getPriority() == null ? "1" : vo.getPriority())
					+ "#"
					+ (vo.getTestGroupCode() == null ? "0" : vo
							.getTestGroupCode())
					+ "#"
					+ (vo.getTestGroupType() == null ? "0" : vo
							.getTestGroupType())
					+ "#"
					+ vo.getIsMandatoryReq()
					+ "#"
					+ (vo.getBookMarkCode() == null ? "null" : vo
							.getBookMarkCode())
					+ "#"
					+ (vo.getOfflineAppoitMentDate() == null ? "null" : vo
							.getOfflineAppoitMentDate()) + "#"
					+ vo.getSetMandTextBoxCombo() + "#"
					+ vo.getMandComboTextBoxComboNames() + "#"
					+ vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#"
					+ vo.getHideAptNo() + "#";
		else

			appendString = vo.getLabCode()
					+ "#"
					+ vo.getLabName()
					+ "#"
					+ vo.getTestCode()
					+ "#"
					+ vo.getTestName()
					+ "#"
					+ vo.getSampleComboStr()
					+ "#"
					+ vo.getTestType()
					+ "#"
					+ vo.getIsAppointment()
					+ "$"
					+ vo.getDeskProperties()
					+ "#"
					+ vo.getIsConfidential()
					+ "#"
					+ (vo.getSampleCode() == null ? vo.getSingleSample() : vo
							.getSampleCode())
					+ "#"
					+ (vo.getPriority() == null ? "1" : vo.getPriority())
					+ "#"
					+ "0"
					+ "#"
					+ "0"
					+ "#"
					+ vo.getIsMandatoryReq()
					+ "#"
					+ (vo.getBookMarkCode() == "" ? "null" : vo
							.getBookMarkCode())
					+ "#"
					+ (vo.getOfflineAppoitMentDate() == "" ? "null" : vo
							.getOfflineAppoitMentDate()) + "#"
					+ vo.getSetMandTextBoxCombo() + "#"
					+ vo.getMandComboTextBoxComboNames() + "#"
					+ vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#"
					+ vo.getHideAptNo() + "#"; // as is not group based test
												// //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
		return appendString;
	}

	public static void getAptBasedTest(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			searchVO.setSearchLabName(fb.getSearchLabName() == null ? "" : fb
					.getSearchLabName());
			searchVO.setSearchTestName(fb.getSearchTestName() == null ? "" : fb
					.getSearchTestName());

			mp = InvestigationRaisingDtlDATA.getAptBasedTest(searchVO, userVO);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");

			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static void getTestsCodeWiseDtl(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);
			// searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			// searchVO.setSearchTestGroup(fb.getSearchTestGroup());

			searchVO.setTestCodeWise(fb.getTestCodeWise());
			// Resetting the LabCodeArray ; for selected lab types
			// fb.setLabTestCodeArray("");

			String labTestCodeArray = fb.getLabTestCodeArray();

			// Logic to check whether the lab/test is already present in the
			// list
			String[] labTestCatalogue = labTestCodeArray.split("@");

			Set<String> setLabCatalogue = new HashSet<String>();
			if (labTestCatalogue != null && labTestCatalogue.length > 0) {
				for (String str : labTestCatalogue) {
					if (!str.equals("") && str != null) {
						String labCodeHashTestCode = str.split("#")[0] + "#"
								+ str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}

			mp = InvestigationRaisingDtlDATA.getTestsCodeWiseDtl(searchVO,
					userVO);

			List<LabTestVO> lstLabTestVO = (List<LabTestVO>) mp
					.get(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS);

			for (LabTestVO vo : lstLabTestVO) {

				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				// vo.setTestGroupType("1");
				String appendString = makeAppendString(vo, false); // vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());

				String tmpLabCodeHashTestCode = vo.getLabCode() + "#"
						+ vo.getTestCode();
				// Add only those Lab/Tests which are not present in the list
				if (!setLabCatalogue.contains(tmpLabCodeHashTestCode)) {
					if (labTestCodeArray.equals(""))
						labTestCodeArray = appendString;
					else
						labTestCodeArray = labTestCodeArray + "@"
								+ appendString;
				}

			}
			fb.setLabTestCodeArray(labTestCodeArray);

			// Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull = null;

			fb.setLabCode(strNull);
			fb.setTestCode(strNull);
			fb.setSampleInfo(strNull);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	public static void deleteReqDtl(InvestigationRaisingDtlFB fb,
			HttpServletRequest request) {
		Status status = new Status();
		// status.add(Status.NEW, "", "");
		Map mp = new HashMap();
		try {
			InvestigationSearchVO searchVO = new InvestigationSearchVO();
			UserVO userVO = ControllerUTIL.getUserVO(request);

			searchVO.setDelLabCode(fb.getDelLabCode());
			searchVO.setDelTestCode(fb.getDelTestCode());

			mp = InvestigationRaisingDtlDATA.deleteReqDtl(searchVO, userVO);

			WebUTIL.setMapInSession(mp, request);

			// status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		} catch (Exception e) {
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	}

	// Check Duplicate Requisiton Logic
	public static StringBuffer checkRequisitionPending(
			InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		String strTestCombo = "";
		try {
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);

			InvestigationSearchVO searchVO = new InvestigationSearchVO();

			// Setting Lab Code and Test Code
			searchVO.setDupLabCode(fb.getDupLabCode());
			searchVO.setDupTestCode(fb.getDupTestCode());
			searchVO.setPatientCrNo(fb.getPatCrNo());

			boolean isTempSamplePresent = InvestigationRaisingDtlDATA
					.checkRequisitionPending(searchVO, voUser);
			strTestCombo = InvestigationRaisingDtlDATA.getreqStatusAJAX(
					searchVO, voUser);
			// sbAjaxRes.append("[{isTempSamplePresent:\'");
			sbAjaxRes.append(isTempSamplePresent);
			sbAjaxRes.append(",");
			sbAjaxRes.append(strTestCombo);

		} catch (Exception e) {
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> "
					+ e.getMessage();
			// HisException eObj =
			new HisException("Investigation",
					"SampleCollectionUTIL.checkSampleNoDuplicacy() --> ",
					strMsgText);
			// objFB_p.setStrErr("Application Error [ERROR ID : " +
			// eObj.getErrorID() + "],Contact System Administrator! ");
			// eObj = null;
		} finally {
		}
		return sbAjaxRes;
	}

}
