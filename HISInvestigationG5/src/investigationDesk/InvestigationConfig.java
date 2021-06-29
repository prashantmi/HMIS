package investigationDesk;

import hisglobal.config.HISConfig;
import hisglobal.utility.HisUtil;

public interface InvestigationConfig   

{
	// **** Configuartion Flags

	String YESNO_FLAG_YES ="1";
	String YESNO_FLAG_NO ="0";


	String IS_VALID_ACTIVE = "1";
	///For Investigation Master Added By Pathan Basha on 09-Jan02015


	String LIST_LOINIC_COMBO_UOM="lstLoinicCombouom";
	String LIST_TEST_COMBO="lstTestCombo";
	String LIST_TEST_COMBO_MAPPED="lstTestComboMapped";
	String LIST_LAB_COMBO="lstlabcombo";
	String LIST_MACRO_COMBO="lstmacrocombo";
	String LIST_SELECTED_MACRO_COMBO="lstselectedmacrocombo";
	String LIST_PARA_COMBO=	"lstParaCombo";
	String LIST_MACRO_COMBO_MAPPED="lstmacrocomboMapped";
	String LIST_CANNED_COMBO_MAPPED="lstcannedcomboMapped";
	//Investigation Requisition Raising Process

	String LIST_LAB_WISE_TEST_DTLS="listLabWiseTestDtls";

	String MODULE_ID_INVESTIGATION="15";

	// For Local Lab Canned Master(added by yogender 19-March2015)
	String LIST_CANNED_COMBO="lstcannedcombo";
	String LIST_SELECTED_CANNED_COMBO="lstselectedcannedcombo";

	String LIST_TESTGROUP_COMBO="lsttestgroupcombo";

	String REQUISITION_NO_SEQUENCE_INVESTIGATION="100001";

	String PATIENT_VO="invPatientVO";

	String LIST_EPISODE_VO="listEpisodeVO";

	// **** Query Property File
	String QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO = "investigationDesk.investigationMstQuerynew";
	String QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO="investigationDesk.transactions.investigationTransactionQuery";
	// /For Investigation Master Added By Pathan Basha on 09-Jan02015
	// Sample Master
	String LIST_LOINIC_COMBO="lstLoinicCombo";
	String SAMPLE_CODE = "101";
	String PARAMETER_CODE = "1001";
	String MANDATORY_CODE = "1001";
	String GNUM_SEAT_ID = "10001";
	String GN_ISVALID = "0";
	// **** Query Property File
	String LIST_TEST_METHOD_COMBO="lstTestMethodCombo";
	String LIST_SAMPLE_COMBO="lstSampleCombo";
	String LIST_MANDATORY_COMBO="lstMandatoryCombo";
	String LIST_SELECTED_COMBO="lstSelectedCombo";
	String DEPART_COMBO="lstdepartcombo";
	String LOCATION_CODE="1003";
	String HOSPITAL_CODE="100";

	String LIST_TEST_DAYS_COMBO="lstTestDaysCombo";
	//Investigation Requisition Raising Process

	String LIST_ADMISSION_VO = "listAdmissionVO";
	String MAP_BOOK_MARK = "mapBookMark";

	String LIST_PATIENT_EPISODE_VO="listPatientEpisodeVo";
	String LIST_PATIENT_ADMISSION_VO="listPatientAdmissionVo";
	// For Episode Saving Mandatory while raising an investigation
	String IS_EPISODE_BOUND_TRUE = "1";
	String IS_EPISODE_BOUND_FALSE = "2";
	String IS_EPISODE_BOUND = IS_EPISODE_BOUND_FALSE;


		//Requistion Header Status
		
		String REQUISITION_HEADER_STATUS_REQUEST_IN_PROGRESS="1";
		String REQUISITION_HEADER_STATUS_REQUEST_COMPLETE="2";
		String REQUISITION_HEADER_STATUS_REQUEST_CANCELLED="3";
		
		//Priority
		String INVESTIGATION_RAISING_PRIORITY_NORMAL="1";
		String INVESTIGATION_RAISING_PRIORITY_URGENT="2";
		String INVESTIGATION_RAISING_PRIORITY_CONFIDENTIAL="3";
		
		//Requisition Dtl Status
		String REQUISITION_DTL_STATUS_SAMPLE_BASED ="2"; // Sample based
		String REQUISITION_DTL_STATUS_PATIENT_BASED="5"; //Patient Based
		
		String REQUISITION_DTL_STATUS_PACKING_LIST ="3"; // Sample based
		String REQUISITION_DTL_STATUS_RESULT_ENTRY ="6"; // Sample based
		String REQUISITION_DTL_STATUS_SAMPLE_ACCEPTANCE ="4"; // Sample based
		
		
		String BILLING_REQUIRED_YES="1";
		String BILLING_REQUIRED_NO="2";
		String BILLING_REQUIRED=BILLING_REQUIRED_YES;
		
		
		String DML_BILLING_MODVAL="1";
		
		//End Requisition Raising Process
		
		
		
		
		
		//Sample Collection Process
		String LIST_PAT_SAMPLE_BILLED="lstBilledPat";
		String LIST_PAT_SAMPLE_UNBILLED="lstUnBilledPat";
		
		
		String MAP_PAT_SAMPLE_BILLED="mapBilledPat";
		String MAP_PAT_SAMPLE_UNBILLED="mapUnBilledPat";
		
		String LIST_UOM_COMBO="lstUOMCombo";
		String LIST_CONTAINER_COMBO="lstContainerCombo";
		
		String SAMPLE_NO_SEQUENCE_INVESTIGATION="100001";
		
		
		String SAMPLE_DTL_STATUS_READY_SAMPLE_ACCEPTANCE="5";
		
		
		//Packing List Generation Process
		String LIST_LABORATORY_COMBO="lstLabCombo";
		
		String LIST_PACKINGLIST_PATIENT_VO="lstPackingListPatientVO";
		
		String LIST_STATUS_INCOMPLETE="1";
		
		String PACKING_LIST_OFFLINE_NO="0";
		
		String PACKING_LIST_OFFLINE_YES="1";
		
		String MAP_PACKING_LIST_SAVE="mpPackingListSave";
		
		
		String PACKING_LIST_GENERATION_TYPE_NORMAL="0";
		String PACKING_LIST_GENERATION_TYPE_DUPLICATE="1";
		
		
		String PACKINGLIST_NO_SEQUENCE_INVESTIGATION="1001";
		String TEST_COMBO="lsttestcombo";
		String PARAMETER_COMBO="lstparametercombo";
		String CRITERIA_COMBO="lstcriteriacombo";
		String ELEMENT_TYPE_COMBO="elementtypecombo";
		String TEST_PARAMETER_COMBO="parametercombo";
		String SYSDATEOBJECT="sysdate";
		String LAB_COMBO="labcombo";
		String LIST_PATIENT_VO="listofpatientvo";
		String LIST_REQUISITION_PATIENT="listofrequisitiondetail";
		String LIST_PAT_PATIENT_UNBILLED="listOfUnBilled";
		String TEST_REASON_COMBO="testReasonCombo";
		String LIST_REQUISITION_PATIENT_BILLED="billed";
		String REQUISITION_DTL_STATUS="3";
		String REQUISITION_DTL_NOSTATUS_PACKING_LIST="10";
		String REQUISITION_DTL_ACCEPTED="6";
		String REQUISITION_DTL_RESCHEDULED="10";
		String REQUISITION_DTL_CANCELLED="9";
	// Test Type

	String TEST_TYPE_PATIENT_BASED = "P";
	String TEST_TYPE_SAMPLE_BASED = "S";
	String TEST_TYPE_SLIDE_BASED = "I";

	// Mandatory Type

	String MANDATORY_TYPE_COMBO = "1";
	String MANDATORY_TYPE_TEXT = "2";

	// Requisition Raised Through
	String INVESTIGATION_RAISED_THROUGH_OFFLINE = "0"; // Offline Process
	String INVESTIGATION_RAISED_THROUGH_OPD_DOCTOR_DESK = "1"; // OPD DOCTOR
	String INVESTIGATION_RAISED_THROUGH_OPD_BAY = "2"; // OPD BAY
	String INVESTIGATION_RAISED_THROUGH_CAUASAILTY_DESK = "3"; // CAUASAILTY
	String INVESTIGATION_RAISED_THROUGH_IPD_DOCTOR_DESK = "4"; // IPD DOCTOR
	String INVESTIGATION_RAISED_THROUGH_IPD_NURSING_DESK = "5"; // IPD NURSING
	String INVESTIGATION_RAISED_THROUGH_OT_DESK = "6"; // OT DESK
	String INVESTIGATION_RAISED_THROUGH_LAB = "7"; // OT DESK

	// IsAutomatedRequest

	String IS_AUTOMATED_REQUEST_ONLINE = "0";
	String IS_AUTOMATED_REQUEST_OFFLINE = "1";

	// Requsition Type

	String REQUISITION_TYPE_IPD = "1";
	String REQUISITION_TYPE_OPD = "2";
	String REQUISITION_TYPE_CASUALITY = "3";
	String REQUISITION_TYPE_OUTSIDE_PATIENT_EXTERNAL_LAB = "4";
	String REQUISITION_TYPE_OUTSIDE_PATIENT_ASSOCIATED_LAB = "5";

	String REPORTAVAILABLEAFTERRESULTPRINTING = "1";
	String REPORTAVAILABLEAFTERRESULTVALIDATION = "2";
	String REPORTAVAILABLEAFTERRESULTREVALIDATION = "3";
	
	String RESULTPRINTINGSTANDARDRANGE = "1";
	String RESULTPRINTINGWITHOUTSTANDARDRANGE = "0";
	
	// End Requisition Raising Process

	// Sample Master
	String LIST_MAND_COMBO = "lstMandCombo";
	String LIST_LOCATION_COMBO = "lstLocationCombo";
	String LIST_WARD_COMBO = "lstWardCombo";
	String LIST_TESTNAME_COMBO = "lstTestNameCombo";
	String LIST_TESTPARA_COMBO = "lstTestPara";
	String LIST_AREA_COMBO = "lstAreaName";
	String LIST_GROUP_COMBO = "lstGroupName";
	String LIST_LABINCHARGE_COMBO = "lstLabInchargeCombo";
	String LIST_TESTMANDREF_VO="lstTestMandRefVO";


	// Investigation Requisition Raising Process
	String LIST_SAMPLE_COLLECTION_VO = "lstsampleAreaVO";
	String LIST_SAMPLE_PATIENT_VO = "lstSamplePatientVO";
	String SELECTED_PAT_DETAILS = "voPatAdm";
	String IS_PAT_DETAIL_PAGE = "isPatDetailPage";

	// Display values for combo masters
	String DISPLAY_DATA_MAND_CODE = "displaythedata";
	String DISPLAY_DATA_TEST_PARA = "displayparavalue";
	
	
	String ARRAY_LABNAMES = "arrayLabNames";
	
	String ARRAY_TESTNAMES = "arrayTestNames";
	
	String ARRAY_TESTGROUPNAMES = "arrayTestGroupNames";
	
	String IS_TEST_GROUP_YES = "1";
	String IS_TEST_GROUP_NO = "0";
	

	
	//Added by Siddharth: 20/03/2015
	// For Template Processing
	int minimumNoOfColumnsinSimpleTemplate = 6;
	String	XML_TESTTEMPLATE="1";
	String XML_TESTSAMPLETEMPLATE="2";
	String XML_TESTREQUISITIONTEMPLATE="3";
	String XML_TEMPLATEQUERYFILE="4";
	String XML_PRINTINGTEMPLATE="5";
	String XML_LABTESTGROUPTEMPLATE="6";
	
	String XSL_PRINTINGTEMPLATE="1";
	String XSL_TESTPROCEDURESTYLESHEET="2";
	String XSL_VIEWTEMPLATE_STYLESHEET="3";
	String XSL_SAMPLEFORMSTYLESHEET="4";
	String XSL_SAMPLEFORMREPORTSTYLESHEET="5";
	String XSL_RESULTENTRYSTYLESHEET="6";
	String XSL_REQUISITIONFORMSTLYESHEET="7";
	String XSL_REQUISITIONFORMREPORTSTYLESHEET="8";
	String XSL_REPORTWITHOUTNORMALVALUES="9";
	String XSL_REPORTWITHNORMALVALUES="10";
	String XSL_REPORTDESIGNSTYLESHEET="11";
	String XSL_REPORT="12";
	String XSL_REDESIGNSTYLESHEET="13";
	String XSL_PRINTINGSTYLESHEET_HEADER="14";
	String XSL_PRINTINGSTYLESHEET_FOOTER="15";
	String XSL_PRINTINGSTYLESHEET_BODY="16";
	String XSL_COMMONLIBRARYTEMPLATESREPORT="17";
	String XSL_COMMONLIBRARYTEMLATES="18";
	//End Template Porcessing

	//Start of Color Coding for template
	String PRIORITY_NORMALCOLOR = "#F1ECE2"; 
	String PRIORITY_HIGHCOLOR = "#FFCCCC";
	String PRIORITY_LOWCOLOR = "#BDE4B8"; 
	//end of color coding for template
	
	
	//For Sample Acceptance Process
	
	String SAMPLE_ACCEPTANCE_LAB_COMBO="sampleAcceptanceLabCombo";
    String LIST_SAMPLE_ACCEPTANCE_VO="listOfSampleAcceptacneVo";
    String LIST_SAMPLE_ACCEPTANCE_DETAILS="listOfSample"; 
    String TEST_REJECTION_REASON_COMBO="rejectionReasomCombo";
    
    String MAP_SAMPLE_ACCEPTANCE_VO="mapOfSampleAcceptacneVo";
    
    
    String SAMPLE_ACCEPTANCE_PACKING_LIST="sampleAcc";
    String SAMPLE_ACCEPTANCE_PACKING_LIST_NEW="sampleAcc";
    String MAP_SAMPLE_ACCEPTANCE_SAMPLENO_VO="mapSampleAccSampleNoVo";
    String REQUISTION_DTL_UPDATE_ACCEPTED_STATUS="6"; 
    String REQUISTION_DTL_UPDATE_RESCHEDULED_STATUS="11";
    String REQUISTION_DTL_UPDATE_CANCELLED_STATUS="12";
    String HIVBL_LIST_STATUS="1";
    String REQUISTION_DTL_UPDATE_RECEIVED_NO_STATUS="26";
    String RECIEVED_STATUS="1";
    String ACCPETED_STATUS="1";
    String REJECTION_ACTION_CANCELLED_STATUS="1";
    String REJECTION_ACTION_RESCHEDULED_STATUS="2";
     String NOT_RECIEVED_STATUS="1";
     String NOT_ACCPETED_STATUS="0";
     
     String LIST_NOT_RECIEVED_STATUS="listNotRecievedStatus";
     String LIST_NOT_ACCPETED_STATUS="listNotAccpetedStatus";
     String LIST_ACCPETED_STATUS="listAccpetedStatus";
     String HIVBL_LIST_STATUS_COMPLETE="2";
     
	
	           /*User Bookmark Master*/
	
	String LIST_USER_COMBO="lstUserCombo";
	String LIST_DEPT_COMBO="lstDeptCombo";
	String LIST_UNIT_COMBO="lstUnitName";
	String LIST_TEST_GROUP_COMBO="lstTestGroupCombo";
	String ESSENTIAL_ALL_TEST_VO="essentialAllTestVO";
	String ESSENTIAL_ALL_GROUP_VO="essentialAllGroupVO";
	String LIST_BOOKMARK_COMBO="lstBookMarkCombo";

   
   
   String MAP_TEST_SAMPLE_DTLS="mapTestSampleDtl";
   String MAP_TEST_SAMPLE_DTLS_MAPPED="mapTestSampleDtlMapped";
   String MAP_TEST_MAND_DTLS="mapTestMandDtl";
   String MAP_TEST_MAND_DTLS_MAPPED="mapTestMandDtlMapped";
   String MAP_TEST_GROUP_DTLS="mapTestGroupDtl";
   String MAP_TEST_GROUP_DTLS_MAPPED="mapTestGroupDtlMapped";
   String MAP_LOINC_DTLS="mapLoincDtl";
   String LIST_LOINC_DTLS="ListLoincDtl";
// -- CR Number Size Configuration
	String CR_NO_FORMAT_SIZE_TWELVE = "12"; // value for size 12
	String CR_NO_FORMAT_SIZE_THIRTEEN = "13"; // value for size 13
	String CR_NO_FORMAT_SIZE_FIFTEEN = "15"; // value for size 13
	String CR_NO_FORMAT_SIZE = CR_NO_FORMAT_SIZE_THIRTEEN;
	String CR_NO_FORMAT_SIZE_FOR_FIFTEEN = CR_NO_FORMAT_SIZE_FIFTEEN;
	
	//--- START RESULT ENTRY PROCESS ADDED BY BASHA ON 21-04-2015
	String LAB_COMBO_FOR_RESULT_ENTRY="labComboForResultEntry";
	String LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO="listPatientResultEntryEssentialVo";
    String LIST_RESULT_ENTRY_PATIENT_VO="listResultEntryPatientVo";
   String LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO="listResultEntryPatientTemplateVo";
   String LIST_RESULT_ENTRY_STATUS="lstResultEntry";
   String REQUISTION_DTL_RESULT_ENTRY_STATUS="7"; 
   /* Added by Siddharth
    * Date: 13/04/2015
    * Purpose: For Result Entry Group VO
    */
   public static String pagewidthprinting="595.0";
	public static String pagewidthheight="A4867.6";
	String datePickerDateFormate="%d-%b-%Y";
	String databaseDateFormate="dd-MMM-yyyy";
	String formDateFormate="dd-MMM-yyyy";
	String ARRAY_TESTNAMES_AJAX="arrayTestNameAJAX";
	String LIST_PRVTESTDTL_AJAX="listPrvTestDtl";
 
	String LIST_APTBASED_TEST="listAptBasedTest";
	String APTID_FOR_INVESTIGATION="2";
	String UPDATION_TYPE="1";
	String STATUS_CODE="1";
	String AVAILABLE_AFTER_RESULT_ENTRY="1";
	String  RESULT_VALIDATION="2";
	String  RESULT_REVALIDATION="3";
 
	
	//loinc scale for test parameter master
	String LOINC_SCALE_COMBO="loincScale";
	//result validation
	String REQUISTION_DTL_RESULT_VALIDATION_STATUS="8";
	String LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK="listLabWiseTestDetailForBookmark";
	
	String IS_MANDATORY_INFO="1";
	//FOR SMAPLE COLLECTION AND SAMPLE COLLECTION CUM ACCEPTANCE PROCESS
	String PROCEDURE_GENERATE_SAMPLENO = "pkg_inv_unique_no_generation.generate_save_sampleno";
	String PROCEDURE_INSERT_REQUSITION_DTL = "pkg_inv_dml.proc_insert_hivt_requisition_dtl";
	String PROCEDURE_INSERT_SAMPLE_DTL = "pkg_inv_dml.proc_insert_hivt_sample_dtl";
	String PROCEDURE_UPDATE_REQUSITION_HEADER = "pkg_inv_dml.proc_insert_hivt_reqisition_header";
	String PROCEDURE_GET_ACOOUNT_NO = "BILL_MST.GET_PAT_ACCOUNTNO";
	String SAMPLE_COLLECTION_MODE="1";
	String SAMPLE_COLLECTION_CUM_ACCEPTANCE_MODE="2";
	String SAMPLE_RECEIVED="1";
	String SAMPLE_ACCEPTED="1";
	String CERTIFICATE_TYPE_ID_IPD="2";

	
	//FOR RESULT REPORT PRINTING PROCESS
	
String LAB_COMBO_FOR_RESULT_REPORT_PRINTING="labComboForResultReportPrinting";
	
String LIST_RESULT_REPORT_PRINTING_ESSENTIALS_VO="lstResultReportPrintingVo";


String LIST_RESULT_REPORT_PRINTING_PDF_DETAIL="listResultReportPrintingPdfDetail";
 

	//result revalidation
	
	String READY_RESULTPRINTING="13";
	
	 String ARRAY_ADVISEDBY_NAMES="arrayAdvisedByTestDetail";
	
	 String WATERMARK_IMAGE_FOR_DUPLICATE_REPORT_PRINTING="\\investigationDesk\\images\\duplicateStamp.jpg";
	String LIST_RESULT_ENTRY="lstOfResultEntry";
	String MANUAL_LAB="1";
	String SAME_AS_SAMPLE_LAB="3";
	String PATIENT_STATUS_IPD="2";
	
	
	//FOR DUPLICATE REPORT PRINTING 
	
String	LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING="duplicateReportPrintingLabCombo";
String  LIST_DUPLICATE_RESULT_REPORT_PRINTING_ESSENTIALS_VO="listDuplicateReprotPrinting";

String REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING="14";


//procedure to generate and insert/update req no//raising transaction
String PROCEDURE_GENERATE_REQUISITIONNO = "pkg_inv_unique_no_generation.generate_save_requisitionno";
String INSERT_REQ_DTL="3";
String UPDATE_APPOINTMENT_DATE="2";
String INSERT_REQ_HEADER="3";
String PROCEDURE_INSERT_REQ_TEST_MAND_DTL="pkg_inv_dml.proc_insert_hivt_requsition_test_mandatory_dtl";
String INSERT_REQ_TESTMAND="1";

String LIST_LAB_WISE_GROUP_DTLS="labWiseGRoupDetails";

String REFERENCE_RANGE_CRITERIA_NORMAL = "11";

String REFERENCE_RANGE_CRITERIA_GENDER = "12";
String REFERENCE_RANGE_CRITERIA_AGE = "13";

String LIST_REQ_DATA="getReqData";

String RAISING_CUM_COLLECTION="4"; //mode for procedure to insert in requisition detail

//SET MONGODB CREDENTIAL 
          
           
			
			//String strMongoServerPort = HisUtil.getParameterFromHisPathXML("HIS_APPSERVER_APPPORT");
		 
			//System.out.println("Applictaion Server URL --> " + HISConfig.HIS_SERVICES_SERVER_URL);

String ARRAY_TEST_CODE_WISE="arrayTestCodeWise";
String LIST_TEST_CODE_WISE_DTLS="listTestCodeWiseDtl";
String ARRAY_TEST_CODE_WISE_FOR_COMBO="arryTestCodeWiseForCombo";
String ARRAY_TEST_CODE_WISE_FOR_COMBO_VALUE="arrayTestCodeWiseForComboValue";
String UPDATE_REQ_STATUS_AFETR_DELETE="16";
String UPDATE_REQ_STATUS_WHERE_CONDITION="2";
String UPDATE_REQ_STATUS_FOR_PATIENT_BASED_WHERE_CONDITION="5";
String APPOINTMENT_STATUS_CONFIRMED="3";
//String REFERENCE_RANGE_CRITERIA_GENDER = "12";
//String REFERENCE_RANGE_CRITERIA_AGE = "13";
//SET MONGODB CREDENTIAL 
            
           
			
			//String strMongoServerPort = HisUtil.getParameterFromHisPathXML("HIS_APPSERVER_APPPORT");
		 
			//System.out.println("Applictaion Server URL --> " + HISConfig.HIS_SERVICES_SERVER_URL);

String LIST_REJECTED="listRejected";

String APPOINTMENT_ADVISED_DESK="55";	//STATUS to be set when appointment test is advised by the doctor but appointment at lab is not confirmed. 
										//appointment will be confirmed at the investigation lab

String MAP_PACK_LIST_DETAILS_VO="lstPackListVO";

String LIST_SINGLE_LAB_WISE_GROUP_DTLS="singleLabWiseGRoupDetails";
String LIST_PACKINGLIST_PATIENT_VO_NEWW="lstPackingListPatientVO_neww";

 }
