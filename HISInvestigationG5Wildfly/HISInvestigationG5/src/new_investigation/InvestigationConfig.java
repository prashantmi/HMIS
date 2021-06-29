package new_investigation;

public interface InvestigationConfig {
  public  String YESNO_FLAG_YES = "1";
  
  public  String YESNO_FLAG_NO = "0";
  
  public  String IS_VALID_ACTIVE = "1";
  
  public  String LIST_LOINIC_COMBO_UOM = "lstLoinicCombouom";
  
  public  String LIST_TEST_COMBO = "lstTestCombo";
  
  public  String LIST_TEST_COMBO_MAPPED = "lstTestComboMapped";
  
  public  String LIST_LAB_COMBO = "lstlabcombo";
  
  public  String LIST_MACRO_COMBO = "lstmacrocombo";
  
  public  String LIST_SELECTED_MACRO_COMBO = "lstselectedmacrocombo";
  
  public  String LIST_PARA_COMBO = "lstParaCombo";
  
  public  String LIST_MACRO_COMBO_MAPPED = "lstmacrocomboMapped";
  
  public  String LIST_CANNED_COMBO_MAPPED = "lstcannedcomboMapped";
  
  public  String LIST_LAB_WISE_TEST_DTLS = "listLabWiseTestDtls";
  
  public  String LIST_LAB_DEPT_COMBO = "lstlabdeptcombo";
  
  public  String MODULE_ID_INVESTIGATION = "15";
  
  public  String LIST_CANNED_COMBO = "lstcannedcombo";
  
  public  String LIST_SELECTED_CANNED_COMBO = "lstselectedcannedcombo";
  
  public  String LIST_TESTGROUP_COMBO = "lsttestgroupcombo";
  
  public  String REQUISITION_NO_SEQUENCE_INVESTIGATION = "100001";
  
  public  String PATIENT_VO = "invPatientVO";
  
  public  String LIST_EPISODE_VO = "listEpisodeVO";
  
  public  String LIST_EPISODE_VOO = "listEpisodeVOO";
  
  public  String QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO = "new_investigation.investigationMstQuerynew";
  
  public  String QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO = "new_investigation.transactions.investigationTransactionQuery";
  
  public  String LIST_LOINIC_COMBO = "lstLoinicCombo";
  
  public  String SAMPLE_CODE = "101";
  
  public  String PARAMETER_CODE = "1001";
  
  public  String MANDATORY_CODE = "1001";
  
  public  String GNUM_SEAT_ID = "10001";
  
  public  String GN_ISVALID = "0";
  
  public  String LIST_TEST_METHOD_COMBO = "lstTestMethodCombo";
  
  public  String LIST_SAMPLE_COMBO = "lstSampleCombo";
  
  public  String LIST_MANDATORY_COMBO = "lstMandatoryCombo";
  
  public  String LIST_SELECTED_COMBO = "lstSelectedCombo";
  
  public  String DEPART_COMBO = "lstdepartcombo";
  
  public  String LOCATION_CODE = "1003";
  
  public  String HOSPITAL_CODE = "100";
  
  public  String LIST_TEST_DAYS_COMBO = "lstTestDaysCombo";
  
  public  String LIST_ADMISSION_VO = "listAdmissionVO";
  
  public  String MAP_BOOK_MARK = "mapBookMark";
  
  public  String LIST_PATIENT_EPISODE_VO = "listPatientEpisodeVo";
  
  public  String LIST_PATIENT_ADMISSION_VO = "listPatientAdmissionVo";
  
  public  String IS_EPISODE_BOUND_TRUE = "1";
  
  public  String IS_EPISODE_BOUND_FALSE = "2";
  
  public  String IS_EPISODE_BOUND = "2";
  
  public  String REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS = "1";
  
  public  String REQUISITION_HEADER_STATUS_REQUEST_COMPLETE = "2";
  
  public  String REQUISITION_HEADER_STATUS_REQUEST_CANCELLED = "3";
  
  public  String INVESTIGATION_RAISING_PRIORITY_NORMAL = "1";
  
  public  String INVESTIGATION_RAISING_PRIORITY_URGENT = "2";
  
  public  String INVESTIGATION_RAISING_PRIORITY_CONFIDENTIAL = "3";
  
  public  String REQUISITION_DTL_STATUS_SAMPLE_BASED = "2";
  
  public  String REQUISITION_DTL_STATUS_PATIENT_BASED = "5";
  
  public  String REQUISITION_DTL_STATUS_PACKING_LIST = "3";
  
  public  String REQUISITION_DTL_STATUS_RESULT_ENTRY = "6";
  
  public  String REQUISITION_DTL_STATUS_RESULT_ENTRY_SAMPLE_ACC = "6,17";
  
  public  String REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE = "4";
  
  public  String BILLING_REQUIRED_YES = "1";
  
  public  String BILLING_REQUIRED_NO = "2";
  
  public  String BILLING_REQUIRED = "1";
  
  public  String DML_BILLING_MODVAL = "1";
  
  public  String LIST_PAT_SAMPLE_BILLED = "lstBilledPat";
  
  public  String LIST_PAT_SAMPLE_UNBILLED = "lstUnBilledPat";
  
  public  String MAP_PAT_SAMPLE_BILLED = "mapBilledPat";
  
  public  String MAP_PAT_SAMPLE_UNBILLED = "mapUnBilledPat";
  
  public  String LIST_UOM_COMBO = "lstUOMCombo";
  
  public  String LIST_CONTAINER_COMBO = "lstContainerCombo";
  
  public  String SAMPLE_NO_SEQUENCE_INVESTIGATION = "100001";
  
  public  String SAMPLE_DTL_STATUS_READY_SAMPLE_ACCEPTANCE = "5";
  
  public  String STAFF_DEPENDENT_IMAGE = "staffImage";
  
  public  String URL_STAFF_DEPENDENT_IMAGE = "http://10.226.1.103:8080/HBIMS/services/restful/hisImageRetrievalUtil/";
  
  public  String LIST_LABORATORY_COMBO = "lstLabCombo";
  
  public  String LIST_PACKINGLIST_PATIENT_VO = "lstPackingListPatientVO";
  
  public  String LIST_STATUS_INCOMPLETE = "1";
  
  public  String PACKING_LIST_OFFLINE_NO = "0";
  
  public  String PACKING_LIST_OFFLINE_YES = "1";
  
  public  String MAP_PACKING_LIST_SAVE = "mpPackingListSave";
  
  public  String PACKING_LIST_GENERATION_TYPE_NORMAL = "0";
  
  public  String PACKING_LIST_GENERATION_TYPE_DUPLICATE = "1";
  
  public  String PACKINGLIST_NO_SEQUENCE_INVESTIGATION = "1001";
  
  public  String TEST_COMBO = "lsttestcombo";
  
  public  String PARAMETER_COMBO = "lstparametercombo";
  
  public  String CRITERIA_COMBO = "lstcriteriacombo";
  
  public  String ELEMENT_TYPE_COMBO = "elementtypecombo";
  
  public  String TEST_PARAMETER_COMBO = "parametercombo";
  
  public  String SYSDATEOBJECT = "sysdate";
  
  public  String LAB_COMBO = "labcombo";
  
  public  String LIST_PATIENT_VO = "listofpatientvo";
  
  public  String LIST_REQUISITION_PATIENT = "listofrequisitiondetail";
  
  public  String LIST_PAT_PATIENT_UNBILLED = "listOfUnBilled";
  
  public  String TEST_REASON_COMBO = "testReasonCombo";
  
  public  String LIST_REQUISITION_PATIENT_BILLED = "billed";
  
  public  String REQUISITION_DTL_STATUS = "3";
  
  public  String REQUISITION_DTL_NOSTATUS_PACKING_LIST = "10";
  
  public  String REQUISITION_DTL_ACCEPTED = "6";
  
  public  String REQUISITION_DTL_RESCHEDULED = "10";
  
  public  String REQUISITION_DTL_CANCELLED = "9";
  
  public  String TEST_TYPE_PATIENT_BASED = "P";
  
  public  String TEST_TYPE_SAMPLE_BASED = "S";
  
  public  String TEST_TYPE_SLIDE_BASED = "I";
  
  public  String MANDATORY_TYPE_COMBO = "1";
  
  public  String MANDATORY_TYPE_TEXT = "2";
  
  public  String INVESTIGATION_RAISED_THROUGH_OFFLINE = "0";
  
  public  String INVESTIGATION_RAISED_THROUGH_OPD_DOCTOR_DESK = "1";
  
  public  String INVESTIGATION_RAISED_THROUGH_OPD_BAY = "2";
  
  public  String INVESTIGATION_RAISED_THROUGH_CAUASAILTY_DESK = "3";
  
  public  String INVESTIGATION_RAISED_THROUGH_IPD_DOCTOR_DESK = "4";
  
  public  String INVESTIGATION_RAISED_THROUGH_IPD_NURSING_DESK = "5";
  
  public  String INVESTIGATION_RAISED_THROUGH_OT_DESK = "6";
  
  public  String INVESTIGATION_RAISED_THROUGH_LAB = "7";
  
  public  String IS_AUTOMATED_REQUEST_ONLINE = "0";
  
  public  String IS_AUTOMATED_REQUEST_OFFLINE = "1";
  
  public  String REQUISITION_TYPE_IPD = "1";
  
  public  String REQUISITION_TYPE_OPD = "2";
  
  public  String REQUISITION_TYPE_CASUALITY = "3";
  
  public  String REQUISITION_TYPE_OUTSIDE_PATIENT_EXTERNAL_LAB = "4";
  
  public  String REQUISITION_TYPE_OUTSIDE_PATIENT_ASSOCIATED_LAB = "5";
  
  public  String REPORTAVAILABLEAFTERRESULTPRINTING = "1";
  
  public  String REPORTAVAILABLEAFTERRESULTVALIDATION = "2";
  
  public  String REPORTAVAILABLEAFTERRESULTREVALIDATION = "3";
  
  public  String RESULTPRINTINGSTANDARDRANGE = "1";
  
  public  String RESULTPRINTINGWITHOUTSTANDARDRANGE = "0";
  
  public  String LIST_MAND_COMBO = "lstMandCombo";
  
  public  String LIST_LOCATION_COMBO = "lstLocationCombo";
  
  public  String LIST_WARD_COMBO = "lstWardCombo";
  
  public  String LIST_TESTNAME_COMBO = "lstTestNameCombo";
  
  public  String LIST_TESTPARA_COMBO = "lstTestPara";
  
  public  String LIST_AREA_COMBO = "lstAreaName";
  
  public  String LIST_GROUP_COMBO = "lstGroupName";
  
  public  String LIST_LABINCHARGE_COMBO = "lstLabInchargeCombo";
  
  public  String LIST_TESTMANDREF_VO = "lstTestMandRefVO";
  
  public  String LIST_SAMPLE_COLLECTION_VO = "lstsampleAreaVO";
  
  public  String LIST_SAMPLE_PATIENT_VO = "lstSamplePatientVO";
  
  public  String SELECTED_PAT_DETAILS = "voPatAdm";
  
  public  String IS_PAT_DETAIL_PAGE = "isPatDetailPage";
  
  public  String DISPLAY_DATA_MAND_CODE = "displaythedata";
  
  public  String DISPLAY_DATA_TEST_PARA = "displayparavalue";
  
  public  String ARRAY_LABNAMES = "arrayLabNames";
  
  public  String ARRAY_TESTNAMES = "arrayTestNames";
  
  public  String ARRAY_TESTGROUPNAMES = "arrayTestGroupNames";
  
  public  String IS_TEST_GROUP_YES = "1";
  
  public  String IS_TEST_GROUP_NO = "0";
  
  public  int minimumNoOfColumnsinSimpleTemplate = 6;
  
  public  String XML_TESTTEMPLATE = "1";
  
  public  String XML_TESTSAMPLETEMPLATE = "2";
  
  public  String XML_TESTREQUISITIONTEMPLATE = "3";
  
  public  String XML_TEMPLATEQUERYFILE = "4";
  
  public  String XML_PRINTINGTEMPLATE = "5";
  
  public  String XML_LABTESTGROUPTEMPLATE = "6";
  
  public  String XSL_PRINTINGTEMPLATE = "1";
  
  public  String XSL_TESTPROCEDURESTYLESHEET = "2";
  
  public  String XSL_VIEWTEMPLATE_STYLESHEET = "3";
  
  public  String XSL_SAMPLEFORMSTYLESHEET = "4";
  
  public  String XSL_SAMPLEFORMREPORTSTYLESHEET = "5";
  
  public  String XSL_RESULTENTRYSTYLESHEET = "6";
  
  public  String XSL_REQUISITIONFORMSTLYESHEET = "7";
  
  public  String XSL_REQUISITIONFORMREPORTSTYLESHEET = "8";
  
  public  String XSL_REPORTWITHOUTNORMALVALUES = "9";
  
  public  String XSL_REPORTWITHNORMALVALUES = "10";
  
  public  String XSL_REPORTDESIGNSTYLESHEET = "11";
  
  public  String XSL_REPORT = "12";
  
  public  String XSL_REDESIGNSTYLESHEET = "13";
  
  public  String XSL_PRINTINGSTYLESHEET_HEADER = "14";
  
  public  String XSL_PRINTINGSTYLESHEET_FOOTER = "15";
  
  public  String XSL_PRINTINGSTYLESHEET_BODY = "16";
  
  public  String XSL_COMMONLIBRARYTEMPLATESREPORT = "17";
  
  public  String XSL_COMMONLIBRARYTEMLATES = "18";
  
  public  String XSL_DYNAMICRESULTPRINTINGTEMPLATE = "22";
  
  public  String PRIORITY_NORMALCOLOR = "#F1ECE2";
  
  public  String PRIORITY_HIGHCOLOR = "#FFCCCC";
  
  public  String PRIORITY_LOWCOLOR = "#BDE4B8";
  
  public  String SAMPLE_ACCEPTANCE_LAB_COMBO = "sampleAcceptanceLabCombo";
  
  public  String LIST_SAMPLE_ACCEPTANCE_VO = "listOfSampleAcceptacneVo";
  
  public  String LIST_SAMPLE_ACCEPTANCE_DETAILS = "listOfSample";
  
  public  String TEST_REJECTION_REASON_COMBO = "rejectionReasomCombo";
  
  public  String MAP_SAMPLE_ACCEPTANCE_VO = "mapOfSampleAcceptacneVo";
  
  public  String SAMPLE_ACCEPTANCE_PACKING_LIST = "sampleAcc";
  
  public  String SAMPLE_ACCEPTANCE_PACKING_LIST_NEW = "sampleAcc";
  
  public  String MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO = "mapSampleAccSampleNoVo";
  
  public  String REQUISTION_DTL_UPDATE_ACCEPTED_STATUS = "6";
  
  public  String REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS = "11";
  
  public  String REQUISTION_DTL_UPDATE_CANCELLED_STATUS = "12";
  
  public  String HIVBL_LIST_STATUS = "1";
  
  public  String REQUISTION_DTL_UPDATE_RECEIVED_NO_STATUS = "26";
  
  public  String RECIEVED_STATUS = "1";
  
  public  String ACCPETED_STATUS = "1";
  
  public  String REJECTION_ACTION_CANCELLED_STATUS = "1";
  
  public  String REJECTION_ACTION_RESCHEDULED_STATUS = "2";
  
  public  String NOT_RECIEVED_STATUS = "1";
  
  public  String NOT_ACCPETED_STATUS = "0";
  
  public  String LIST_NOT_RECIEVED_STATUS = "listNotRecievedStatus";
  
  public  String LIST_NOT_ACCPETED_STATUS = "listNotAccpetedStatus";
  
  public  String LIST_ACCPETED_STATUS = "listAccpetedStatus";
  
  public  String HIVBL_LIST_STATUS_COMPLETE = "2";
  
  public  String LIST_USER_COMBO = "lstUserCombo";
  
  public  String LIST_DEPT_COMBO = "lstDeptCombo";
  
  public  String LIST_UNIT_COMBO = "lstUnitName";
  
  public  String LIST_TEST_GROUP_COMBO = "lstTestGroupCombo";
  
  public  String ESSENTIAL_ALL_TEST_VO = "essentialAllTestVO";
  
  public  String ESSENTIAL_ALL_GROUP_VO = "essentialAllGroupVO";
  
  public  String LIST_BOOKMARK_COMBO = "lstBookMarkCombo";
  
  public  String MAP_TEST_SAMPLE_DTLS = "mapTestSampleDtl";
  
  public  String MAP_TEST_SAMPLE_DTLS_MAPPED = "mapTestSampleDtlMapped";
  
  public  String MAP_TEST_MAND_DTLS = "mapTestMandDtl";
  
  public  String MAP_TEST_MAND_DTLS_MAPPED = "mapTestMandDtlMapped";
  
  public  String MAP_TEST_GROUP_DTLS = "mapTestGroupDtl";
  
  public  String MAP_TEST_GROUP_DTLS_MAPPED = "mapTestGroupDtlMapped";
  
  public  String MAP_LOINC_DTLS = "mapLoincDtl";
  
  public  String LIST_LOINC_DTLS = "ListLoincDtl";
  
  public  String CR_NO_FORMAT_SIZE_TWELVE = "12";
  
  public  String CR_NO_FORMAT_SIZE_THIRTEEN = "13";
  
  public  String CR_NO_FORMAT_SIZE_FIFTEEN = "15";
  
  public  String CR_NO_FORMAT_SIZE = "13";
  
  public  String CR_NO_FORMAT_SIZE_FOR_FIFTEEN = "15";
  
  public  String LAB_COMBO_FOR_RESULT_ENTRY = "labComboForResultEntry";
  
  public  String LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO = "listPatientResultEntryEssentialVo";
  
  public  String LIST_RESULT_ENTRY_PATIENT_VO = "listResultEntryPatientVo";
  
  public  String LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO = "listResultEntryPatientTemplateVo";
  
  public  String LIST_RESULT_ENTRY_STATUS = "lstResultEntry";
  
  public  String REQUISTION_DTL_RESULT_ENTRY_STATUS = "7";
  
  public  String REQUISTION_DTL_RESULT_ENTRY_STATUS_SAVE_TO_DRAFT = "27";
  
  public  String REQUISTION_DTL_RESULT_VALIDATION_STATUS_SAVE_TO_DRAFT = "28";
  
  public  String pagewidthprinting = "595.0";
  
  public  String pagewidthheight = "867.6";
  
  public  String datePickerDateFormate = "%d-%b-%Y";
  
  public  String databaseDateFormate = "dd-MMM-yyyy";
  
  public  String formDateFormate = "dd-MMM-yyyy";
  
  public  String ARRAY_TESTNAMES_AJAX = "arrayTestNameAJAX";
  
  public  String LIST_PRVTESTDTL_AJAX = "listPrvTestDtl";
  
  public  String LIST_APTBASED_TEST = "listAptBasedTest";
  
  public  String APTID_FOR_INVESTIGATION = "2";
  
  public  String UPDATION_TYPE = "1";
  
  public  String STATUS_CODE = "1";
  
  public  String AVAILABLE_AFTER_RESULT_ENTRY = "1";
  
  public  String RESULT_VALIDATION = "2";
  
  public  String RESULT_REVALIDATION = "3";
  
  public  String LOINC_SCALE_COMBO = "loincScale";
  
  public  String REQUISTION_DTL_RESULT_VALIDATION_STATUS = "8";
  
  public  String LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK = "listLabWiseTestDetailForBookmark";
  
  public  String IS_MANDATORY_INFO = "1";
  
  public  String PROCEDURE_GENERATE_SAMPLENO = "pkg_inv_unique_no_generation.generate_save_sampleno";
  
  public  String PROCEDURE_INSERT_REQUSITION_DTL = "pkg_inv_dml.proc_insert_hivt_requisition_dtl";
  
  public  String PROCEDURE_INSERT_REQUSITION_DTL_ADDENDUM = "pkg_inv_dml.proc_insert_hivt_requisition_dtl_addendum";
  
  public  String PROCEDURE_INSERT_SAMPLE_DTL = "pkg_inv_dml.proc_insert_hivt_sample_dtl";
  
  public  String PROCEDURE_INSERT_SAMPLE_DTL_HMODE_1 = "{call pkg_inv_dml.proc_insert_hivt_sample_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
  
  public  String PROCEDURE_UPDATE_REQUSITION_HEADER = "pkg_inv_dml.proc_insert_hivt_reqisition_header";
  
  public  String PROCEDURE_UPDATE_REQUSITION_HEADER_ADDENDUM = "pkg_inv_dml.proc_insert_hivt_reqisition_header_addendum";
  
  public  String PROCEDURE_GET_ACOOUNT_NO = "BILL_MST.GET_PAT_ACCOUNTNO";
  
  public  String SAMPLE_COLLECTION_MODE = "1";
  
  public  String SAMPLE_COLLECTION_CUM_ACCEPTANCE_MODE = "2";
  
  public  String SAMPLE_RECEIVED = "1";
  
  public  String SAMPLE_ACCEPTED = "1";
  
  public  String CERTIFICATE_TYPE_ID_IPD = "2";
  
  public  String LAB_COMBO_FOR_RESULT_REPORT_PRINTING = "labComboForResultReportPrinting";
  
  public  String LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO = "lstResultReportPrintingVo";
  
  public  String LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO_INPROCESS = "lstResultReportPrintingVoinprocess";
  
  public  String LIST_RESULT_REPORT_PRINTING_PDF_DETAIL = "listResultReportPrintingPdfDetail";
  
  public  String READY_RESULTPRINTING = "13";
  
  public  String ARRAY_ADVISEDBY_NAMES = "arrayAdvisedByTestDetail";
  
  public  String WATERMARK_IMAGE_FOR_DUPLICATE_REPORT_PRINTING = "\\new_investigation\\images\\duplicateStamp.jpg";
  
  public  String IMAGE_FOR_HEADER = "\\new_investigation\\images\\nimsHeader.jpg";
  
  public  String LIST_RESULT_ENTRY = "lstOfResultEntry";
  
  public  String MANUAL_LAB = "1";
  
  public  String SAME_AS_SAMPLE_LAB = "3";
  
  public  String PATIENT_STATUS_IPD = "2";
  
  public  String LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING = "duplicateReportPrintingLabCombo";
  
  public  String LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO = "listDuplicateReprotPrinting";
  
  public  String REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING = "14";
  
  public  String PROCEDURE_GENERATE_REQUISITIONNO = "pkg_inv_unique_no_generation.generate_save_requisitionno";
  
  public  String INSERT_REQ_DTL = "3";
  
  public  String UPDATE_APPOINTMENT_DATE = "2";
  
  public  String INSERT_REQ_HEADER = "3";
  
  public  String PROCEDURE_INSERT_REQ_TEST_MAND_DTL = "pkg_inv_dml.proc_insert_hivt_requsition_test_mandatory_dtl";
  
  public  String INSERT_REQ_TESTMAND = "1";
  
  public  String LIST_LAB_WISE_GROUP_DTLS = "labWiseGRoupDetails";
  
  public  String MAP_USER_CODE_WISE_TEST_DTLS = "userCodeWiseLabTestDetails";
  
  public  String MAP_USER_CODE_WISE_TEST_AVAILABILITY_DTLS = "userCodeWiseTestAvailibilityDetails";
  
  public  String REFERENCE_RANGE_CRITERIA_NORMAL = "10";
  
  public  String REFERENCE_RANGE_CRITERIA_AGE = "11";
  
  public  String REFERENCE_RANGE_CRITERIA_GENDER = "12";
  
  public  String LIST_CANNED_FILE_DETAILS = "listOfCannedDetails";
  
  public  String TEST_WISE_COMBO_FOR_RESULT_ENTRY = "testWiseComboForResultEntry";
  
  public  String SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY = "sampleNoWiseComboForResultEntry";
  
  public  String LABNO_WISE_COMBO_FOR_RESULT_ENTRY = "labNoWiseComboForResultEntry";
  
  public  String TEST_GROUP_COMBO_FOR_RESULT_ENTRY = "testGroupWiseCombo";
  
  public  String EMP_NAME_COMBO_FOR_RESULT_ENTRY = "empNameWiseComboForResultEntry";
  
  public  String REPORT_PDF_GEN = "26";
  
  public  String REPORT_PDF_GEN_INPROCESS = "13";
  
  public  String DUPLICATE_REPORT_PRINITNG = "14";
  
  public  String ARRAY_TEST_CODE_WISE = "arrayTestCodeWise";
  
  public  String LIST_TEST_CODE_WISE_DTLS = "listTestCodeWiseDtl";
  
  public  String ARRAY_TEST_CODE_WISE_FOR_COMBO = "arryTestCodeWiseForCombo";
  
  public  String ARRAY_TEST_CODE_WISE_FOR_COMBO_VALUE = "arrayTestCodeWiseForComboValue";
  
  public  String UPDATE_REQ_STATUS_AFETR_DELETE = "16";
  
  public  String UPDATE_REQ_STATUS_WHERE_CONDITION = "2";
  
  public  String UPDATE_REQ_STATUS_FOR_PATIENT_BASED_WHERE_CONDITION = "5";
  
  public  String APPOINTMENT_STATUS_CONFIRMED = "3";
  
  public  String QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO = "new_investigation.reports.investigationReportQuery";
  
  public  String LIST_REJECTED = "listRejected";
  
  public  String RESULT_MODIFICATION_PROCESS = "1";
  
  public  String RESULT_VALIDATION_PROCESS = "2";
  
  public  String RESULT_REVALIDATION_PROCESS = "3";
  
  public  String LIST_CANNED_CODE_FILE_DETAILS = "cannedCodeListing";
  
  public  String LIST_SELECTED_CANNED_COMBO_CODES = "cannedCodeVO";
  
  public  String LIST_SELECTED_MACRO_COMBO_CODES = "macroCodeVO";
  
  public  String LIST_APT_BY_DESK = "lstAptByDesk";
  
  public  String LIST_APT_DETAILS_REQ_DTL_DESK = "lstAptDetailsDeskDetails";
  
  public  String APPOINTMENT_PATIENT_SELECTED = "selectedForAppointment";
  
  public  String LIST_APT_DETAILS_ON_CLICK_GO = "detailsOnClick";
  
  public  String ADVISED_TEST_STATUS = "55";
  
  public  String PARAMETER_MACHINE_COMBO = "parameterMachineCombo";
  
  public  String PARAMETER_TEST_COMBO = "parameterTestCombo";
  
  public  String DISPLAY_DATA_MACHINE_TEST_PARAM = "displayDataMachineTestParameter";
  
  public  String LIST_MACHINE_COMBO = "lstMachineCombo";
  
  public  String LIST_MACHINE_STRING = "stringOfMachine";
  
  public  String LAB_COMBO_FOR_MACHINE_RESULT_ENTRY = "comboMachineLab";
  
  public  String MACHINE_RESULT_ENTRY_ESSENTIALS_VO = "machineResultVO";
  
  public  String REQUISITION_DTL_STATUS_MACHINE_RESULT_ENTRY = "17";
  
  public  String TEST_STATUS_MACHINE_ENQUIRY = "testStausValue";
  
  public  String LIST_SAMPLE_NO_MACHINE_ENQUIRY = "lstSampleNo";
  
  public  String MACHINE_RESULT_ENQUIRY_VO = "voMachineEnquiry";
  
  public  String SAMPLE_NO_MACHINE_ENQUIRY = "comboSampleNo";
  
  public  String LIST_MACHINE_COMBO_MACHINE_ENQUIRY = "lstMachineComboMachineEnquiry";
  
  public  String LIST_MACHINE_MST_FORMAT = "lstMachineMstFormat";
  
  public  String LIST_MACHINE_MST_LOCATION_COMBO = "lstMachineLocationCombo";
  
  public  String LIST_MACHINE_MST_COMMPORT = "lstMachineMstCommPort";
  
  public  String sessionOptionTestList = "optionTest";
  
  public  String sessionOptionGroupList = "optionGroup";
  
  public  String MACHINE_COMMPORTS_DETAILS = "lstMachineCommPortDetails";
  
  public  String MACHINE_PREVIOUS_COMMPORTS_DETAILS = "lstPreviousMachineCommPortDetails";
  
  public  String DISPLAY_DATA_MACHINE_TEST_PARAM_ADDED = "lstAlreadyAddedData";
  
  public  String AUTOCOMPLETE_LIST_VALUES = "autocompleteList";
  
  public  String LIST_PRINTING_TEMPLATE_COMBO = "listPrintingTemplateCombo";
  
  public  String LIST_STATE_COMBO = "lstStateCombo";
  
  public  String LIST_TEST_PRINTING_TEMPLATE = "lstTestPrintingTemplateCombo";
  
  public  String ARRAY_SAMPLENAMES = "lstSampleNames";
  
  public  String ARRAY_PARAMETERNAME = "lstParameterNames";
  
  public  String ARRAY_LABNAMES_EXTERNAL = "lstExternalLabs";
  
  public  String MAP_PACK_LIST_DETAILS_VO = "lstPackListVO";
  
  public  String GET_EDITOR_LIMIT = "12000";
  
  public  String LAB_COMBO_FOR_TEST_AVAILABILITY = "labListTestAvailability";
  
  public  String TEST_AVAILABILITY_DETAILS = "lstAvaialabilityTest";
  
  public  String LIST_TEST_SEQ_NO = "lstTestSeqNoInfoMst";
  
  public  String FILM_USED_LAB_COMBO = "lstFilmUsedLabCombo";
  
  public  String LIST_TEST_DETAILS_FILM_USED = "lstTestDetailsFilmUsed";
  
  public  String LIST_TEST_COMBO_FILM_MST = "lstTestComboFilmMst";
  
  public  String LIST_ITEM_NAME_STORE = "lstIttemNameFromStore";
  
  public  String LIST_FILM_SIZE_FILM = "lstFilmSizeFilm";
  
  public  String RAISING_CUM_COLLECTION = "4";
  
  public  String LIST_SAMPLE_COLLECTION_VO_RAISING = "lstSampleAreaRaising";
  
  public  String LIST_STRING_BATCH_DETAILS = "lstBatchDetailsFilmUsed";
  
  public  String QUERY_FILE_FOR_INVESTIGATION_TESTNAME = "new_investigation.investigationMstQuerynew";
  
  public  String LIST_FILM_TESTNAME_COMBO = "list_film_testname_combo";
  
  public  String QUERY_FILE_FOR_INVESTIGATION_ITEMNAME = "new_investigation.investigationMstQuerynew";
  
  public  String LIST_FILM_ITEMNAME_COMBO = "list_film_itemname_combo";
  
  public  String LIST_FILM_STORENAME_COMBO = "list_film_storename_combo";
  
  public  String GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO = "groupModifiedListForResultEntry";
  
  public  String GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO_INPROCESS = "groupModifiedListForResultEntryinprocess";
  
  public  String LAB_COMBO_FOR_AUDIT_PROCESS = "lab_combo_for_audit_process";
  
  public  String TEST_COMBO_FOR_AUDIT_PROCESS = "test_combo_for_audit_process";
  
  public  String LIST_PATIENT_AUDIT_PROCESS_ESSENTIALS_VO = "list_patient_audit_prcoess_essentials_vo";
  
  public  String LIST_PATIENT_AUDIT_PROCESS_ESSENTIALS_VO1 = "list_patient_audit_prcoess_essentials_vo1";
  
  public  String LIST_PATIENT_AUDIT_MODIFY_PROCESS_ESSENTIALS_VO = "list_patient_audit_modify_prcoess_essentials_vo";
  
  public  String LIST_SINGLE_LAB_WISE_GROUP_DTLS = "singleLabWiseGRoupDetails";
  
  public  String LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO = "list_patient_view_external_prcoess_essentials_vo";
  
  public  String LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESNAME = "list_patient_view_external_prcoess_essentials_vo_filesname";
  
  public  String LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESCONTENTTYPE = "list_patient_view_external_prcoess_essentials_vo_filescontenttype";
  
  public  String LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESID = "list_patient_view_external_prcoess_essentials_vo_filesid";
  
  public  String ARRAY_GROUP_CODE_WISE = "userGroupCodesLst";
  
  public  String LIST_GROUP_CODE_WISE_DTLS = "groupCodeBasedTests";
  
  public  String NEW_ENTRIES_PATIENT_VO = "newPatientEntryDetails";
  
  public  String OLD_ENTRIES_PATIENT_VO = "oldPatientEntryDetails";
  
  public  String REPORT_ADDENDUM_PROCESS = "4";
  
  public  String sms_username = "nims";
  
  public  String sms_password = "Cd-nims123";
  
  public  String sms_senderId = "CDNIMS";
  
  public  String sms_url = "http://msdgweb.mgov.gov.in/esms/sendsmsrequest";
  
  public  String USAGE_STATUS_INUSE_VALUE = "1";
  
  public  String USAGE_STATUS_USED_VALUE = "2";
  
  public  String USAGE_STATUS_STOP_USING_VALUE = "3";
  
  public  String USAGE_STATUS_INUSE_LABEL = "In Use";
  
  public  String USAGE_STATUS_USED_LABEL = "Used";
  
  public  String USAGE_STATUS_STOP_USING_LABEL = "Stopped Using Due To Qualilty Reasons";
  
  public  String ITEMNAME_COMBO_HSTT_ITEM_MST = "itemNameComboHsstItemMst";
  
  public  String ITEMTYPE_COMBO_HIVT_ITEMTYPE_MST = "itemTypeComboHivttItemMst";
  
  public  String OTHER_ID_LIST = "otherIdList";
  
  public  String ITEM_LIST_LEFT_COMBO = "itemlistLeftCombo";
  
  public  String ITEM_LIST_RIGHT_COMBO = "itemlistRightCombo";
  
  public  String LIST_TEST_WISE_ITEM_CONSUMABLE = "testWiseItemConsumable";
  
  public  String LIST_TEST_WISE_CONSUMABLE_LOT = "listTestWiseCosnumableLot";
  
  public  String PATIENT_TEST_WISE_ITEM_CONSUMABLE = "testWiseConsumableGetPatientDetails";
  
  public  String LIST_TEST_WISE_ITEM_CONSUMABLE_ALL = "testWiseConsumableItemDetailsALL";
  
  public  String LIST_ORGANIC_NAME_COMBO = "list_organic_name_combo";
  
  public  String LIST_ANTIBIOTIC_NAME_COMBO = "list_antibiotic_name_combo";
  
  public  String URL_MAP_COMBO = "url_map_combo";
  
  public  String ORGANISM_COMBO = "organism_combo";
  
  public  String ANTIBIOTIC_COMBO = "antibiotic_combo";
  
  public  String XML_DEPARTMENT_TESTTEMPLATE = "2";
  
  public  int deploymentMode = 1;
  
  public  String windowsPath = "c:/investigationDetails/";
  
  public  String linuxPath = "/root/investigationDetails/";
  
  public  String HYPER_LINK_DATA_LIST = "hyper_link_data_list";
  
  public  String XSL_STYLESHEET = "3";
  
  public  String UPLOADED_FILE_AS_ARRAY = "uploadedFileAsArray";
  
  public  String ADDENDUM_REASON_LIST = "addendum_reason_list";
  
  public  String GENERIC_TEMPLATE_ID = "9999";
  
  public  String wardname = "Ward Name";
  
  public  String SAMPLEBASED_LIST_PACKINGLIST_PATIENT_VO = "sampleBasedPackingList";
  
  public  String MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO_DETAILS = "mapSampleAccSampleNoVodetails";
  
  public  String LIST_SAMPLE_PATIENT_VO_BARCODE = "lstSamplePatientVObarcode";
  
  public  String DATA_FOR_REPORT_HISTORY = "data_for_report_history";
  
  public  String DATA_FOR_REPORT_HISTORY_ALL = "data_for_report_historyall";
  
  public  String DATA_FOR_REPORT_HISTORY_ALL_INACTIVE = "data_for_report_historyall_inactive";
  
  public  String FILTER_LIST = "filter_list";
  
  public  String isFilter = "isFilter";
  
  public  String REFERENCE_RANGE_CRITERIA_GENDER_AGE = "13";
  
  public  int OTP_EXPIRY_TIME = 5;
  
  public  String publicjson = "";
  
  public  String PUBLIC_LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO = "publiclstResultReportPrintingVo";
  
  public  String MASTERTYPE_TEST_COMBO = "mastertype_test_code";
  
  public  String TEST_COMBO_REQQ = "TEST_COMBO_REQQ";
  
  public  String LIST_PRVTESTDTL_AJAX_RESULT_ENTRY = "listPrvTestDtlresultentry";
  
  public  String MAP_LAB_CODE_TERIIFF_CHANGE = "labcodeteriffchage";
  
  public  String LIST_MACHINEE_COMBO = "lstmachineeCombo";
  
  public  String APPOINTMENT_ADVISED_DESK = "55";
  
  public  String MACHINE_LIST_ACCEPTANCE = "machinelistacceptance";
  
  public  String ARRAY_TEST_CODE_WISE_LABWISE = "arrayTestCodeWiselabwise";
  
  public  String DEPENDENT_ELEMENT_LIST = "dependent_elemnt_list";
  
  public  String DEPENDENT_ELEMENT_LIST_VALUE = "dependent_elemnt_list_value";
  
  public  String DEPENDENT_CONTROL_LIST = "dependent_control_list";
  
  public  String list_in_sessionhyperlinkdata = "listinsessionhyperlinkdata";
  
  public  String list_in_sessionhyperlinkdata1 = "listinsessionhyperlinkdata1";
  
  public  String list_in_sessionhyperlinkdata_Table = "listinsessionhyperlinkdatatable";
  
  public  String LIST_LOCATION_BASED_MACHINE__COMBO = "listlocationbasedmachine";
  
  public  String LIST_MODIFIED = "list_modifed";
  
  public  String list_in_sessionhyperlinkdata1111 = "listinsessionhyperlinkdata111";
  
  public  String LIST_PID_PAT = "listpidpat";
  
  public  String LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO_PID = "listResultEntryPatientTemplateVopid";
  
  public  String LIST_TEST_SEQ_NO_ANTIBOTIC = "lstTestSeqNoInfoMst_antibiotic";
  
  public  String ANTIBIOTIC_COMBO_PREFFREDED = "antibiotic_combo_preffered";
  
  public  String GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO1 = "groupModifiedListForResultEntry1";
  
  public  String LIST_STATUS_DASHBOARD_RECORD = "listStatusDashboardRecord";
  
  public  String LIST_INV_LISTING_REPORT_NEW = "listInvListingReportNew";
  
  public  String PAT_PREV_REQUISITION = "patprevrquisition";
  
  public  String list_fungus_in_sessionhyperlinkdata = "listfungusinsessionhyperlinkdata";
  
  public  String ARRAY_TEST_CODE_WISE_LABWISE_TESTWISESEARCH = "arrayTestCodeWiselabwise_labwisesearch";
  
  public  String ARRAY_GROUP_CODE_WISE_LABWISE = "userGroupCodesLst_labwise";
  
  public  String isFilterPatAcc = "isFilterpatacc";
  
  public  String FILTER_LIST_PATACC = "filter_list_patacc";
  
  public  String ARRAY_ADVISEDBY_NAMES_XRAYPROCESS = "arrayAdvisedByTestDetail_xrayprocess";
  
  public  String ARRAY_TESTNAMES_XRAYPROCESS = "array_testnameforxrayprocess";
  
  public  String MAP_BOOK_MARK_XRAYPROCESS = "mapBookMarkxrayprcoess";
  
  public  String MAP_ALL_TEST_DATA_XRAY_PROCESS = "mapalldataxrayprocess";
  
  public  String MAP_ALL_TEST_DATA_XRAY_VIEWS_PROCESS = "mapalldataxrayviewsprocess";
  
  public  String testfornotaddingviewscharge = "testfornotaddingviewscharge";
  
  public  String LIST_PREV_REQ_XRAY = "lsitprevreqxray";
  
  public  String QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO_XRAY = "new_investigation.transactions.investigationTransactionQueryxray";
  
  public  String LIST_USERLIST = "userList";
  
  public  String PROCEDURE_INSERT_REQUSITION_DTL_NEW = "pkg_inv_dml.proc_insert_hivt_requisition_dtl_new";

  String PROCEDURE_GET_PAT_DTL = "pkg_inv_rpt_prcoess.proc_inv_pat_dtl";

  String LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO_NEW="lstResultReportPrintingVoneww";

  public  String QUERY_FILE_FOR_INVESTIGATION_TRACKING_REPORTSDAO = "new_investigation.reports.investigationTrackingReportQuery";
  
  public  String TESTWISETERIIFS = "testwiseterrifs";

  String QUERY_FILE_FOR_INVESTIGATION_CHATBOT = "new_investigation.chatbot.helpers.investigationChatbotQuery";

  public  String ALL_TEST_RATE = "all_test_rate";

  public  String ALL_GROUP_RATE = "all_group_rate";

  
  // Added by Deepandu Jain 22-10-2019 for Task Tracking jsp 

  
  
  public  String LIST_PROJECTOPTION_COMBO = "lstprojectcombo";
  
  public  String LIST_MODULEOPTION_COMBO = "lstmodulecombo";
  
  public  String LIST_TASKOPTION_COMBO = "lsttaskcombo";
  
  public  String LIST_TLEADOPTION_COMBO = "lsttleadcombo";
  
  public  String LIST_ASSIGNTOOPTION_COMBO = "lstassigntocombo";
  
  public  String LIST_STATUSOPTION_COMBO = "lststatuscombo";
  
  public  String LIST_ACTIVITYOPTION_COMBO = "lstactivitycombo";
  
  public String  PMS_TASK_VOS = "pmsTaskVOs";
  

  public  String QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO_REQ = "new_investigation.transactions.investigationTransactionQueryreq";

  
  public  String MAP_ALL_GROUP_DATA_NEWREQ_PROCESS = "mapalldatagroupreqnewprocess";

  
  public  String QUERY_FILE_FOR_INVESTIGATION_RESULT_VALIDATION_DAO = "new_investigation.transactions.invResultValidationRespQuery";
  
  
  public  String LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO = "listResultValidationRespTestTemplateVo";
  
  public  String LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO_PID = "listResultValidationRespTestTemplateVoPid";
  
  
  public  String LIST_RESULT_ENTRY_RESP_TEST_TEMPLATE_VO = "listResultEntryRespTestTemplateVo";
  
  public  String LIST_RESULT_ENTRY_RESP_TEST_TEMPLATE_VO_PID = "listResultEntryRespTestTemplateVoPid";
  
  
  public  String LIST_RESULT_REVALIDATION_RESP_TEST_TEMPLATE_VO = "listResultReValidationRespTestTemplateVo";
  
  public  String LIST_RESULT_REVALIDATION_RESP_TEST_TEMPLATE_VO_PID = "listResultReValidationRespTestTemplateVoPid";

//Added by prachi
  public  String LIST_FASTING_TYPE = "FastingTimeCombo";
  public  String LIST_FASTING_TIME = "FastingTypeCombo";
  public String MST_VO="mst_vo" ;
  
  public  String REQUISTION_DTL_RESULT_REVALIDATION_STATUS_SAVE_TO_DRAFT = "30";

}
