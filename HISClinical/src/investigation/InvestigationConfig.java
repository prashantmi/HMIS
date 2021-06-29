package investigation;

public interface InvestigationConfig {

	String CHOICE_CR = "CR";
	String CHOICE_SLIDE = "S";
	String STATUS_OBJECT = "statusobject";
	String QUERY_FILE_FOR_DAO_INVESTIGATION = "investigation.investigationQuery";
	String EPISODE_ISOPEN_OPEN = "1";
	String EPISODE_ISOPEN_CLOSE = "0";
	String REQUISITION_RAISING_MODE = "LAB";
	String HOSPITAL_CODE = "101";
	String REQUISITIONRAISINGFORPATIENTWITHCRNO = "0";
	String REQUISITIONRAISINGFOROUTSIDEPATIENT = "1";
	String REQUISITIONRAISINGFOROUTSIDESAMPLE = "2";
	String seatId = "SEATID";
	String ESSENTIALBO_OPTION_REF_HOSPITAL = "optionRefHospital";
	String sessionPackingListObject = "packingListObject";

	String ITEM_CATEGORY_LIST="itemCatLst";
	String STORE_MODULE_ID="63";
	String LABLIST="labList";
	String TESTLIST="testList";
	String SAMPLELIST="sampleList";
	
	// session Attributes
	String sessionPatientCRNO = "PATIENTCRNO";
	String sessionPatientEpisodeNo = "PATIENTEPISODENO";
	String sessionPatientEpisodeVisitNo = "PATIENTEPISODEVISITNO";
	String sessionPatientAdmissionNo = "PATIENTADMISSIONNO";
	String sessionEpisodeVO = "EpisodeVO";
	String sessionPatientVO = "PatientVO";
	String sessionRequisitionListVO = "RequisitionListVO";
	String sessionRequisitionVO = "RequisitionVO";
	String sessionReferHospitalList = "ReferHospitalList";
	String sessionOptionUserLaboratoryList = "optionUserLaboratory";
	String sessionOptionTestList = "optionTest";
	String sessionOptionSampleList = "sampleTest";
	String sessionOptionPatientList = "PatientList";
	String sessionOptionHospitalList = "hospitalList";// Nitin Tyagi
	String userOptionSessionList = "userNameList";
	String sessionOptionUserDepartmentList = "departmentList";
	String sessionOptionUserTestList = "testList";
	String sessionUserVO = "UserVO";
	String HOSPITALCODE = "101";
	String HOSPITALNAME = "PGI-Chandigarh";
	String FILECREATIONTIME = "1";
	String sessionDuplicateSampleLabelList = "duplicateSampleLabelList";
	String sessionOptionEmployee = "employeeList";
	String sessionEmployeeId = "employeeId";
	String sessionEmployeeName= "employeeName";
	String WorkOrderListForRequisitionValidation = "requisitionValidationWorkList";
	String sessionUnBilledWorkOrders="sessionUnBilledWorkOrders";

	String ESSENTIAL_BO_OPTION_YEAR = "yearList";
	String DATABASE_DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

	String sessionOptionUserList = "userList";

	String STATUS_REQUISITION_RAISED = "0";// REQUISITION HEADER TABLE
	String STATUS_REQUISITION_SAMPLECOLLECTED = "2";// REQUISITION HEADER TABLE
	String STATUS_REQUISITION_PACKINGLISTGENERATED = "3";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_SAMPLEACCEPTED_INCOMPLETE = "4";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_SAMPLEACCEPTED_COMPLETED = "6";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_PATIENTACCEPTED_INCOMPLETE = "8";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_PATIENTACCEPTED_COMPLETED = "7";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_GROSSING_DONE_INCOMPLETE = "8";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_SLIDECREATION_DONE_INCOMPLETE = "9";// REQUISITION
	// HEADER
	// TABLE
	String STATUS_REQUISITION_RESULTENTERED_INCOMPLETE = "10";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_RESULTENTERED_COMPLETED = "11";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_RESULTVALIDATED_INCOMPLETE = "12";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_RESULTVALIDATED_COMPLETED = "13";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_RESULTPRINTED_INCOMPLETE = "14";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_RESULTPRINTED_COMPLETED = "15";// REQUISITION
	// HEADER TABLE
	String STATUS_REQUISITION_REJECTED = "16";// REQUISITION HEADER TABLE

	/* new set of status for workorder multisession dtl table */

	String HWMD_APPOINTMENT_REQUIRED = "1";
	String HWMD_READY_SAMPLECOLLECTION = "2";
	String HWMD_READY_PACKINGLISTGENERATION = "3";
	String HWMD_READY_REQUISITION_VALIDATION = "4";
	String HWMD_READY_SAMPLEACCEPTANCE = "5";
	String HWMD_READY_PATIENTACCEPTANCE = "6";
	String HWMD_READY_RESULTENTRY = "10";
	String HWMD_RESULT_WITHELD = "8";
	String HWMD_READY_RESULTVALIDATION = "9";
	String HWMD_READY_RESULTREVALIDATION = "10";
	String HWMD_READY_RESULTPRINTING = "11";
	String HWMD_RESULT_PRINTED = "12";
	String HWMD_DUPLICATE_RESULT_PRINTED = "13";
	String HWMD_READY_RESULT_MODIFIED = "14";
	String HWMD_READY_FOR_GROSSING = "15";
	String HWMD_SAMPLE_RESHDULED = "17";// SAmaple acceptance
	String HWMD_SAMPLE_CANCELED = "18";// sample acceptance
	String HWMD_READY_FOR_SLIDEENTRY = "16";

	/* new set of status for workorder dtl table */
	String HRD_APPOINTMENT_REQUIRED = "1";
	String HRD_READY_SAMPLECOLLECTION = "2";
	String HRD_READY_PACKINGLISTGENERATION = "3";
	String HRD_READY_REQUISITION_VALIDATION = "4";
	String HRD_READY_SAMPLEACCEPTANCE = "5";
	String HRD_READY_PATIENTACCEPTANCE = "6";
	String HRD_READY_RESULTENTRY = "10";
	String HRD_RESULT_WITHELD = "8";
	String HRD_READY_RESULTVALIDATION = "9";
	String HRD_READY_RESULTREVALIDATION = "10";
	String HRD_READY_RESULTPRINTING = "11";
	String HRD_RESULT_PRINTED = "12";// work order completed
	String HRD_DUPLICATE_RESULT_PRINTED = "13";
	String HRD_READY_RESULT_MODIFIED = "14";
	String HRD_SAMPLE_CANCEL = "20";
	String HRD_READY_FOR_SLIDEENTRY = "21";

	//Start Grossing Process Sub Lab Status added Vijay Uniyal on 10 May 2010
	
	String HRD_READY_FOR_GROSSING="14"; //to be check same status for HRD_READY_RESULT_MODIFIED 
	String HRD_READY_SLIDEENTRY="15";
	String HRD_READY_FOR_PROCESSING="23";
	String HRD_READY_FOR_CUTTING="24";
	String HRD_READY_FOR_REPORTING="25";
	
	//End Grossing Process Sub Lab Status added Vijay Uniyal on 10 May 2010
	
	/* new set of status for workorder dtl table */
	String HRH_REQUEST_INCOMPLETE = "1";
	String HRH_REQUEST_COMPLETE = "2";
	String HSD_READY_PLISTGENERATION = "1";
	String HSD_READY_SAMPLEACCEPTANCE = "2";
	String HSD_SAMPLE_RESHEDULED = "3";
	String HSD_SAMPLE_CANCELED = "4";
	String HSD_READY_RESULTENTRY = "10";
	String HSD_READY_FOR_GROSSING = "6";
	String HSD_READY_FOR_SLIDEENTRY = "9";

	// String HRH_REQUEST_COMPLETE="2";

	String STATUS_REQUISITIONTYPE_OUTSIDESAMPLE = "4";// REQUISITION HEADER
	// TABLE
	String STATUS_REQUISITIONTYPE_OUTSIDEPATIENT = "3";// REQUISITION HEADER
	// TABLE
	String STATUS_REQUISITIONTYPE_WITHCRNO_IPD = "1";// REQUISITION HEADER
	// TABLE
	String STATUS_REQUISITIONTYPE_WITHCRNO_OPD = "2";

	String STATUS_REQUISITIONDETAIL_REQUISITIONRAISED = "1";// REQUISITION
	// DETAIL TABLE
	String STATUS_SAMPLECOLLECTED = "2";// REQUISITION DETAIL TABLE
	String STATUS_SAMPLECOLLECTIONCANCELLED = "3";// REQUISITION DETAIL TABLE
	String STATUS_SAMPLEACCEPTANCE_ACCEPTED = "4";// REQUISITION DETAIL TABLE
	String STATUS_PATIENTACCEPTANCE_ACCEPTED = "5";// REQUISITION DETAIL TABLE
	String STATUS_SPECIMEN_GROSSING_DONE = "6";// REQUISITION DETAIL TABLE
	String STATUS_SPECIMEN_SLIDECREATION_DONE = "7";// REQUISITION DETAIL TABLE
	String STATUS_SAMPLE_READY_FOR_RESULTENTRY = "8";// REQUISITION DETAIL
	// TABLE
	String STATUS_SAMPLE_RESULTENTRY = "9";// REQUISITION DETAIL TABLE
	String STATUS_SAMPLE_RESULTENTRY_VALIDATED = "10";// REQUISITION DETAIL
	// TABLE
	String STATUS_SAMPLE_RESULTENTRY_REVALIDATED = "11";// REQUISITION DETAIL
	// TABLE
	String STATUS_PATIENTACCEPTANCE_REJECTED = "12";// REQUISITION DETAIL TABLE
	String STATUS_SAMPLEACCEPTANCE_REJECTED = "13";// REQUISITION DETAIL TABLE
	String STATUS_SAMPLE_LICATERESULTPRINTING_VALIDATED = "17";// REQUISITION
	// DETAIL TABLE
	String STATUS_SAMPLE_DUPLICATERESULTPRINTING_VALIDATED = "19";// REQUISITION
	// DETAIL
	// TABLE

	String STATUS_RESULTENTRED = "6";// REQUISITION DETAIL TABLE 10-july
	String STATUS_RESULTVALIDATED = "7";// REQUISITION DETAIL TABLE
	String STATUS_RESULTREVALIDATED = "8";// REQUISITION DETAIL TABLE
	String STATUS_PATIENTACCEPTED_COMPLETED = "5"; // REQUISITION DETAIL TABLE
	String STATUS_REQUISITION_PATIENTACCEPTANCE_COMPLETED = "12";// REQUISITION
	// HEADER
	// TABLE

	String STATUS_SAMPLEACCEPTED = "1";// SAMPLE DETAIL TABLE
	String STATUS_SAMPLECOLLECTIONINTIALIZED = "0";// SAMPLE DETAIL TABLE
	String STATUS_SLIDEBASED_GROSSING_DONE = "2";// SAMPLE DETAIL TABLE
	String STATUS_SLIDEBASED_SLIDECREATED = "3";// SAMPLE DETAIL TABLE
	String STATUS_SAMPLEREJECTED = "4";// SAMPLE DETAIL TABLE

	String STATUS_PATIENTACCEPTED = "1";// Patient Acceptance Detail Table
	String STATUS_PATIENTREJECTED = "2";// Patient Acceptance Detail Table

	// tableName
	String REQUISITION_TABLENAME = "HIVT_REQ_HEADER";
	String REQUISITIONDETAIL_TABLENAME = "HIVT_REQ_HEADER";
	String YEAR = "Y";
	String MONTH = "M";
	String WEEK = "W";
	String DAYS = "D";
	String hospitalCode = "HOSPITAL_CODE";
	String SAMPLE_CANCELED = "SampleCanceled";
	String SAMPLE_RESCHEDULE = "SampleRescheduled";
	String RESULT_ENTERED = "ResultEntered";
	enum month {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
	};

	enum days {
		Sunday, Monday, Tuesday, WednesDay, Thursday, Friday, SaturDay
	};

	enum functionNames {
		getSample, getOrganism
	};

	// /WORKORDERLIST REPORT FOR IPD OPD SAMPLE WISE AND TEST WISE
	String TEST_FILM_USAED_DATWISE="testFilmDetail.jrxml";
	String WORKORDERLIST_FOR_OPD_DATEWISE = "workOrderListForIOPD.jrxml";
	String WORKORDERLIST_FOR_OPD_TODAY = "OPDWiseWorkOrderListToday.jrxml";
	String WORKORDERLIST_FOR_IPD_TODAY = "IPDWiseWorkorderListTday.jrxml";
	String WORKORDERLIST_FOR_IPD_DATEWISE = "IPDWiseWorkorderListDateWise.jrxml";
	String WORKORDERLIST_FOR_SAMPLEWISE_TODAY = "WorkListGenerationSpecimenWiseTODAY.jrxml";
	String WORKORDERLIST_FOR_SAMPLEWISE_DATEWISE = "WorkListGenerationSpecimenWiseForPatients.jrxml";
	String WORKORDERLIST_FOR_TESTWISE_TODAY = "WorkListTestWiseGenerationTODAY.jrxml";
	String WORKORDERLIST_FOR_TESTWISE_DATEWISE = "WorkListTestWiseGenerationDateWise.jrxml";
	// ///////added by naveen for investigation reports//////
	String ESSENTIALBO_OPTION_SECONDARY_CATEGORY = "optionSecondaryCategory";
	String ESSENTIAL_BO_OPTION_ALLDEPARTMENT = "alldepartment";
	String ESSENTIAL_BO_OPTION_ALLSTAFF = "allStaff";
	String ESSENTIAL_BO_OPTION_ALLHOSPITAL ="hospitallist";
	String ESSENTIAL_BO_OPTION_ALLSAMPLENAME = "allSampleName";
	String ESSENTIAL_BO_OPTION_LAB = "lab";
	String ESSENTIAL_BO_OPTION_ALLCRNO = "crNo";
	String ESSENTIAL_BO_OPTION_ALLSLIDENO = "slideNo";
	String ESSENTIAL_BO_OPTION_ALLTESTNO = "allTestNo";
	String ESSENTIAL_BO_OPTION_ALLLABNO = "allLabNo";
	String ESSENTIAL_BO_OPTION_GENDER = "allGender";
	String QUERY_FILE_FOR_REPORTS = "investigation.investigationReportQuery";
	String SAMPLE_COLLECTION_LIST_TODAY = "sampleCollectionList_today.jrxml";
	String SAMPLE_COLLECTION_LIST_DATEWISE = "SampleCollectionList_DateWise.jrxml";
	String LIST_OF_SAMPLE_ACCEPTED_TODAY = "listOfSampleAccepted_today.jrxml";
	String LIST_OF_SAMPLE_ACCEPTED_DATEWISE = "listOfSampleAccepted_DateWise.jrxml";
	String COUNTING_OF_SAMPLE_COLLECTION_TODAY = "countingOfSampleCollection_today.jrxml";
	String COUNTING_OF_SAMPLE_COLLECTION_DATEWISE = "countingOfSampleCollection_DateWise.jrxml";
	String LISTING_OF_PATIENT_ACCEPTANCE_TODAY = "listingOfPatientAcceptance_today.jrxml";
	String LISTING_OF_PATIENT_ACCEPTANCE_DATEWISE = "listingOfPatientAcceptance_DateWise.jrxml";
	String INCOMPLETE_TEST_DETAILS_TODAY = ".jrxml";
	String INCOMPLETE_TEST_DETAILS_DATEWISE = ".jrxml";

	String COUNTING_OF_SAMPLE_COLLECTION_DATEWISE_SPECIMEN = "countingOfSampleCollection_DateWise_Specimen.jrxml";
	String COUNTING_OF_SAMPLE_COLLECTION_DATEWISE_STAFF = "countingOfSampleCollection_DateWise_Staff.jrxml";
	String COUNTING_OF_SAMPLE_COLLECTION_TODAY_SPECIMEN = "countingOfSampleCollection_Today_Specimen.jrxml";
	String COUNTING_OF_SAMPLE_COLLECTION_TODAY_STAFF = "countingOfSampleCollection_Today_Staff.jrxml";

	String LAB_REGISTER_REPORT_TODAY = "labRegisterReport_Today.jrxml";
	String LAB_REGISTER_REPORT_DATEWISE = "labRegisterReport_DateWise.jrxml";

	String LIST_OF_SLIDE_SUPPLIED_CRNO_WISE = "listOfSlidesSupplied_CRWise.jrxml";
	String LIST_OF_SLIDE_SUPPLIED_SLIDENO_WISE = "listOfSlidesSupplied_SlideWise.jrxml";
	String LIST_OF_SLIDE_SUPPLIED_TODAY = "listOfSlidesSupplied_Today.jrxml";
	String LIST_OF_SLIDE_SUPPLIED_DATEWISE = "listOfSlidesSupplied_DateWise.jrxml";

	String LIST_OF_REQUISITION_FOR_VARIOUS_TEST_TODAY_LAB = "requisitionForVariousTest_Today_LabWise.jrxml";
	String LIST_OF_REQUISITION_FOR_VARIOUS_TEST_TODAY_DEPARTMENT = "requisitionForVariousTest_Today_DeptWise.jrxml";
	String LIST_OF_REQUISITION_FOR_VARIOUS_TEST_TODAY_TEST = "requisitionForVariousTest_Today_TestWise.jrxml";

	String LIST_OF_REQUISITION_FOR_VARIOUS_TEST_DATEWISE_LAB = "requisitionForVariousTest_DateWise_LabWise.jrxml";
	String LIST_OF_REQUISITION_FOR_VARIOUS_TEST_DATEWISE_DEPARTMENT = "requisitionForVariousTest_DateWise_DeptWise.jrxml";
	String LIST_OF_REQUISITION_FOR_VARIOUS_TEST_DATEWISE_TEST = "requisitionForVariousTest_DateWise_TestWise.jrxml";

	String STATUS_OF_TEST_LABWISE_DATEWISE = "LabWiseStatusOfTest_DateWise.jrxml";
	String STATUS_OF_TEST_LABWISE_TODAY = "LabWiseStatusOfTest_Today.jrxml";
	String REQUISITION_FOR_SAMPLES_NOT_COLLECTED_TODAY = "SampleNotCollectedRequisition_TodayPGI.jrxml";
	String REQUISITION_FOR_SAMPLES_NOT_COLLECTED_DATEWISE = "SampleNotCollectedRequisition_DateWisePGI.jrxml";
	String SAMPLE_COLLECTION_LIST_PGI_TODAY = "SampleCollectionList_todayPGI.jrxml";
	String SAMPLE_COLLECTION_LIST_PGI_DATEWISE = "SampleCollectionList_DateWisePGI.jrxml";

	String ESSENTIAL_BO_OPTION_WEEK = "weekList";
	String LIST_OF_TEST_PERFORMED_LAB_WISE = "ListOfTestPerformedLabWise.jrxml";
	String LIST_OF_TEST_PERFORMED_LAB_WISE_MONTHWISE = "ListOfTestPerformedLabWise_MonthWise.jrxml";

	String INVESTIGATION_JRXML_PATH = "/investigation/reports/jrxml/";
	String LAB_TEST_PARAMETER_REPORT = "LabTestParameterReport.jrxml";
	
	String sessionGrossingDetailList = "GROSSINGDETAILLIST";
	Object sessionSlideDetailList = "BLOCKDETAILLIST";

	// /////////ADDED BY SHWETA////////////////////////////
	String COUNT_OF_RAISED_INVESTIGATIONS_TODAY = "CountOfInvestigationRaised_Today.jrxml";
	String COUNT_OF_RAISED_INVESTIGATIONS_DATEWISE = "CountOfInvestigationRaised_DateWise.jrxml";
	String COUNT_OF_RAISED_INVESTIGATIONS_DEPTWISE = "CountOfInvestigationRaised_DeptWise.jrxml";

	String TEST_STATUS_REPORT_OPD_DATEWISE = "TestStatusReportOPD_Datewise.jrxml";
	String TEST_STATUS_REPORT_OPD_TODAY = "TestStatusReportOPD_Today.jrxml";
	String TEST_STATUS_REPORT_IPD_DATEWISE = "TestStatusReportIPD_Datewise.jrxml";
	String TEST_STATUS_REPORT_IPD_TODAY = "TestStatusReportIPD_Today.jrxml";

	String TEST_STATUS_SUMMARY_DATEWISE = "TestStatusSummary_Datewise.jrxml";
	String TEST_STATUS_SUMMARY_TODAY = "TestStatusSummary_Today.jrxml";

	String WORKLOAD_REPORT_LAB_TECHNICIAN_DATEWISE = "WorkLoadReport_LabTechnicianDatewise.jrxml";
	String WORKLOAD_REPORT_LAB_TECHNICIAN_TODAY = "WorkLoadReport_LabTechnicianToday.jrxml";

	String WORKLOAD_REPORT_LAB_TEST_DATEWISE = "WorkLoadReport_LabTest_Datewise.jrxml";
	String WORKLOAD_REPORT_LAB_TEST_TODAY = "WorkLoadReport_LabTest_Today.jrxml";

	String WORKLOAD_REPORT_DEPT_DATEWISE = "WorkLoadReport_Dept_Datewise.jrxml";
	String WORKLOAD_REPORT_DEPT_TODAY = "WorkLoadReport_Dept_Today.jrxml";

	String TOTAL_INVESTIGATION_DONE_DATEWISE = "TotalInvDone_Datewise.jrxml";
	String TOTAL_INVESTIGATION_DONE_TODAY = "TotalInvDone_Today.jrxml";

	String LABWISE_PERFORMANCE_SUMMARY_DATEWISE = "LabWisePerformanceSummarry_Datewise.jrxml";
	String RADIOLOGY_MLC_REGISTER_DATEWISE = "RadiologyMLCRegister_DateWise.jrxml";
	String RADIOLOGY_MLC_REGISTER_TODAY = "RadiologyMLCRegister_Today.jrxml";
	String SAMPLECOLLECTIONREPORT_DATEWISE = "sampleCollectionReport_DateWise.jrxml";
	String SAMPLECOLLECTIONREPORT_TODAY = "sampleCollectionReport_Today.jrxml";
	
	
	//ADDED BY ANSHUL
	String LABCOUNTOFINVESTIGATION_DATEWISE="LabWiseCountOfInvestigation_Datewise.jrxml";
	String LABCOUNTOFINVESTIGATION_TODAY="LabWiseCountOfInvestigation_Today.jrxml";
	String INVESTIGATIONCARRIEDOUTIPDOPD_DATEWISE="TotalInvestigationCarriedOutOPDIPD_Datewise.jrxml";
	String INVESTIGATIONCARRIEDOUTIPDOPD_TODAY="TotalInvestigationCarriedOutOPDIPD_Today.jrxml";
	String LABWISEGENDERWISECOUNTOFINV_TODAY="LabWisGenderWiseCountOfInvestigation_Datewise.jrxml";
	String LABWISEGENDERWISECOUNTOFINV_DATEWISE="LabWisGenderWiseCountOfInvestigation_Today.jrxml";
	
	String CHOICE_LABORATORY="LABORATORYCHOSEN";
	String CHOICE_DEPARTMENT="DEPARTMENTCHOSEN";
	String CHOICE_TEST="TESTCHOSEN";
	/*
	 * value = 0 :means server is deployed in windows value = 1 :deployment is
	 * in Linux
	 * 
	 */
	int deploymentMode = 0;
	String windowsPath = "c:/investigationDetails/";
	String linuxPath = "/root/investigationDetails/";

	String REF_CRITERIA_NORMAL = "10";
	String REF_CRITERIA_GENDER = "11";
	String REF_CRITERIA_AGE = "12";
	String REF_CRITERIA_GENDER_AGE = "13";
	String REF_CRITERIA_MANDATORY = "14";
	String REF_CRITERIA_MANDATORY_GENDER = "15";
	String REF_CRITERIA_MANDATORY_AGE = "16";
	String REF_CRITERIA_GENDER_MANDATORY_AGE = "17";

	String PARAMETER_CONTROL_COMBO = "D";
	String PARAMETER_CONTROL_TEXTBOX = "E";
	String sessionRequisitionVOList = "requistionVOList";

	String sessionUOMList = "uomOptionList";

	String sessionCurrentlyRaisedRequisitionMap = "CurrentlyRaisedRequisitionMap";

	
	String collectionArea="SampleCollectionArea";


	String windowsPathForCannedFile = "c:/CannedFile";
	String linuxPathForCannedFile = "/root/CannedFile";

	int allowed_backdays_episode = 1000;

	int minimumNoOfColumnsinSimpleTemplate = 6;
	String databaseDriver = "com.edb.Driver";
	String databaseURL = "jdbc:oracle:thin:@10.0.5.1:1521:AHIS";
	String databaseuserName = "GGSH";
	String databasepassword = "ggsh";    
	String hospitalName="Guru Gobind Singh Govt. Hospital";
	String hospitalAddress="Raghubir Nagar,New Delhi-110027";
	//String[]  testSessionStatus={"Appointment Pending","Ready Sample Collection","Ready PackingList Generation","Ready Sample Acceptance","Patient Acceptance"	,"Ready Result Entry"		,"Ready Result Validation","Ready Result ReValidation","Ready for Result Printing","Ready Duplicate Result Printing","Patient Rejected","Patient Acceptance Rescheduled","Ready for Grossing","Ready For Slide Entry","Sample Resheduled","Sample Cancelled","Ready Result Modification","Test Cancelled"};
	//String[]  testSessionStatus={"Appointment Pending","Ready Sample Collection","Ready PackingList Generation","Ready Sample Acceptance","Patient Acceptance"	,"Ready Result Entry"		,"Ready Result Validation","Ready Result ReValidation","Ready for Result Printing","Ready Duplicate Result Printing","Patient Rejected","Patient Acceptance Rescheduled","Ready for Grossing","Ready For Slide Entry","Sample Resheduled","Sample Cancelled","Ready Result Modification","Test Cancelled"};
	String[]  testSessionStatus={"Appointment Pending"	,"Ready Sample Collection"	,"Ready PackingList Generation"	,"","Ready Sample Acceptance","Ready Patient Acceptance"	    ,"Ready Result Entry"		   ,"Ready Result Validation"		,"Ready Result ReValidation"	,""		,""		,"Patient Rejected"   ,"","","",""		,"Ready For Printing"	,"Ready For Duplicate Printing"		,""			,"Test Cancelled"	,"Test Deleted"};
	String[]  testStatus= {	"Test Ordered","Test Ordered","Sample Collected",""	,"Test Ordered waiting for Patient Acceptance"		,"Appointment Scheduled"    		,"Sample / Patient Accepted"   ,"Result Entered"        		,"Report Ready"			 			,""		,""		,"Patient Rejected"   ,"","Grossing Started","Staining Started",""		,"Report Ready"			,"Report Ready"						,""			,"Test Cancelled"	,"Test Deleted","","Processing Started","Cutting Started","Reporting Started","","","","Sample Accepted for Machine",""};//AppointmentScheduled
	String[]  testColors= {		"TestOrdered","TestOrdered","SampleCollected",""	,"SampleCollected"		,"AppointmentScheduled"				,"SamplePatientAccepted"	  ,"ResultEntered" 					,"ReportReady"						,""		,""		,"PatientRejected"	  ,"","GrossingStarted","StainingStarted",""		,"ReportReady"			,"ReportReady"						,""			,"TestCancelled"	,"","","ProcessingStarted","CuttingStarted","ReportingStarted","","","","Sample Accepted for Machine",""};//AppointmentScheduled
	String PRIORITY_NORMALCOLOR = "#F1ECE2"; 
	String PRIORITY_HIGHCOLOR = "#FFCCCC";
	String PRIORITY_LOWCOLOR = "#BDE4B8"; 
	String databaseDateFormate="dd-MMM-yyyy";
	String formDateFormate="dd-MMM-yyyy";
	String datePickerDateFormate="%d-%b-%Y";
	String sessionRejectionReasonList="rejectionReasonList";
	String sessionPatientRejectionReasonList="patientRejectionReasonList";
	String PATIENT_CANCELED = "PatientCanceled";
	String PATIENT_RESCHEDULE = "PatientRescheduled";
	String PATIENT_RESULT_ENTERED = "PatientResultEntered";
	String windowsPathForPDFFile = "c:/PdfFile";
	String linuxPathForPDFFile = "/root/PdfFile";
	String collectionAreaCode = "collectionAreaCode";
	
	String sessionOptionUserCollectionAreaList = "optionUserCollectionArea";
	
	
	String INVITEMTESTCONSUMEVO = "ITEMTESTCOMCUMEVOLIST";
	String INVLABTESTCONSUMEVO ="Inv_labtestConsumeVO";
	String INVSESSIONBATCHWISECOUNSUMEDETAIL="InvSessionBatchWiseConsumeDetails";

	int ftpdeploymentMode = 0;

	//specify the Barcode printer name in printerName field
	String printerName="TSC TTP-243E";
	//String printerName="TLP2284";
	enum patienttype {
		OPD,IPD,OutsidePatient,OutsideSample,OperationTheatre
	};
	
	String STATUS_REQUISITIONTYPE_OPERATIONTHEATRE = "0";
	
		
	String	XML_TESTTEMPLATE="1";
	String XML_TESTSAMPLETEMPLATE="2";
	String XML_TESTREQUISITIONTEMPLATE="3";
	String XML_TEMPLATEQUERYFILE="4";
	String XML_PRINTINGTEMPLATE="5";
	String XML_LABTESTGROUPTEMPLATE="6";
	String XML_LABREGISTERCONFIGURATION="7";
	String XML_LABORATORYREQUISITIONTEMPLATE="8";
	String XML_IMAGEUTILITYUSERINFORMATION="9";
	String XML_APPLETCONFIGURATOR="10";
	
	
	String XSL_PRINTINGTEMPLATE="1";
	String XSL_TESTPROCEDURESTYLESHEET="2";
	String XSL_STYLESHEET="3";
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
	
	String collectionAreaCodeActual="collectionAreaCodeActual";
	String defaultCRNO="";//provision of default cr no
	String ESSENTIAL_BO_OPTION_ALLWARD = "OPTION_ALLWARD";
	String ESSENTIAL_BO_OPTION_ALLUNITNO = "OPTION_ALLUNITNO";
	String DEPARTMENTLABORATORYMONTHLYREPORT="departmentLaboratoryTestReport_monthly.jrxml";
	String sessionOptionUserSampleNoList = "optionUserSampleNoList";
	String sessionOptionBillNoList = "optionBillNoList";
}
