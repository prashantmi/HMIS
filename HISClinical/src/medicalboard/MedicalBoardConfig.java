package medicalboard;

public interface MedicalBoardConfig 
{
	
	String ESSENTIALBO_OPTION_PRIMARY_CATEGORY= "optionPrimaryCategory";
	String ESSENTIALBO_OPTION_CERTIFICATE_TYPE= "optionCertificateType";
	String ESSENTIALBO_OPTION_ORGANISATION_NAME= "optionOrganisationName";
	String ESSENTIALBO_OPTION_DEPARTMENT_DETAIL= "optionDepartmemtDetail";
	String ESSENTIALBO_OPTION_ORGANISATION_TYPE= "optionOrganisationType";
	
	String[] MEDICAL_BOARD_ORGANIZATION_TYPES=new String[] {"","Govt.Office","Private Office","Court","Police","Insurance Company","School/College"};
	String MEDICALBOARD_ORGANIZATIONTYPES="orgTypeList";
	
	String MEDICAlBOARD_REQUESTFROM_ORGANIZATION="1";
	String MEDICAlBOARD_REQUESTFROM_INDIVIDUAL="2";
	String MEDICAlBOARD_REQUESTFROM_ANYONE="3";

	// FTS File Categories IDs
	String FTS_NUM_CATEGORY_ID_DISABILITY_CERTIFICATE="9";
	String FTS_NUM_CATEGORY_ID_MEDICAL_CERTIFICATE="10";
	String[] FTS_NUM_CATEGORY_NAME_ARRAY = {"","","","","","","","","","Disability","Medical Fitness"};

	
////////////////////////////Query File Name///////////////////////////////////////////////////
	String QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO = "medicalboard.masters.medicalBoardMasterQuery";
	String QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO = "medicalboard.transactions.medicalBoardTransactionQuery";
	String QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO = "medicalboard.medicalBoardEssentialQuery";
	///////////////////////////////////////////////////////////////////////////////////////////////////
	String[] MEDICAL_BOARDTYPE_NAME=new String[] {"","Physicican","Board"};
	String BOARD_TYPE_PHYSICIAN="1";
	String BOARD_TYPE_BOARD="2";
	String MEDICAL_BOARD_LIST="medicalBoardTypeLst";
	
	//checklist
	String CHECKLIST_COMPULSORY_FLAG_ARRAY[]={"None","Compulsory At Requisition","Compulsory At Acceptance","Compulsory At Post Medical Entry"};
	String COMPULSORY_NONE="0";
	String COMPULSORY_AT_TIME_OF_REQ="1";
	String COMPULSORY_AT_TIME_OF_ACCEPTANCE="2";
	String COMPULSORY_AT_TIME_OF_POST_ENTRY="3";
	
	String MEDICALBOARD_CHECKLIST_VO_ARRAY="checklistVOArray";
	
	String[] MEDICAL_BOARD_CHECKLIST_BY =new String[] {"","Ward Nurse","Sister Incharge","Peon","MRD Clerk"};
	
	String MEDICAL_BOARD_CHECKLIST_BY_LIST="checkListByList";
	String HMBT_BOARD_CONFIGURATION_DEPARTMENT_DETAIL="DepartmentDetail";
	String ESSENTIALBO_OPTION_CERTIFICATE_TYPE_LIST= "optionCertificateTypeList";
	String SLNO="1";
	

	
	String LIST_OF_CERTIFICATE_CATEGORY="ListOfCertificateCategory";
	String TABLE_ID_CERTIFICATE_TYPE_MST="8";
	String LIST_OF_BOARD_TYPE="listOfBoardType";
	String LIST_OF_DEPARTMENT_UNIT_CODE="ListOfDepartmentUnitCode";
	String LIST_OF_DISTRICT="ListOfDistrict";
	String VO_OF_CERTIFICATE_DETAIL="VoOfCertificateDetail"; 
	String DISTRICTID_LIST_SELECTED="DistrictListSelected";
	String DISTRICTID_LIST_REMAINING="DistrictListRemaining";
	
	
	String PROCEDURE_GET_EMPLIST_PROCESSWISE = "ahis_util.proc_emplist_processwise";
	String PROCESS_ID_DOCTOR_LIST="2";
	String PROCESS_ID_ESCORT_LIST="1";// 7 is for Peon Chnaged by Pragya 2012.10.12
	

	String ESSENTIAL_PATIENT_LIST_FOR_REQ= "essentialPatientListForReq";
	String BILLING_VO= "billingVO"; 
	String IS_LOCATIONBOUND_YES= "1"; 
	String IS_LOCATIONBOUND_NO= "0"; 
	
	//Request Status
	String REQUEST_STATUS_REQUEST_RAISED= "1";
	String REQUEST_STATUS_REQUEST_ACCEPTED= "2";
	String REQUISITION_STATUS_ACCEPTED="2";
	String REQUEST_STATUS_REQUEST_CANCELLED= "3";
	String REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE = "4";
	String REQUEST_STATUS_MEDICAL_PERFORMED= "5";
	String REQUEST_STATUS_PARTIAL_PERFORMED= "6";
	String REQUEST_STATUS_CERTIFICATE_GENERATED= "7";
	String REQUEST_STATUS_CERTIFICATE_HANDOVER= "8";
	
	String REQUISITION_SCHEDULE_LIST="requisitionScheduleList";
	String LOCATION_ELIGIBLITY_MSTVO_LIST="locationEligiblityMstVOList";

	String REQUISITION_FILE_CID_LIST="requisitionFileCIDList";
	
	// for billing
	String CHARGE_TYPE_ID_OPD="1";
	String CHARGE_TYPE_ID_IPD="0";
	String SERVICE_ID="5";
	String BSERVICE_ID="10";
	String HBLNUM_STATUS="1";
	String TRANS_TYPE="1";
	String PROCEDURE_UPDATE_QTY="BILL_INTERFACE.PROC_UPDATE_BILLQTY";
	String BILL_NO_LIST="billNoList";	
	
	String ORGANISATION_ID_OTHER="0";
	String IS_CHECKLIST_COMPULSORY_YES="1";
	String IS_CHECKLIST_COMPULSORY_NO="0";
	
	//requisition change/reschedule by
	String REQUISITION_CHANGE_REQ_MODE_BY_PATIENT="1";
	String REQUISITION_CHANGE_REQ_MODE_BY_ORGANIZATION="2";
	String REQUISITION_CHANGE_REQ_MODE_BY_HOSPITAL="3";
	String REQUISITION_CHANGE_REQ_MODE_BY_POST_ENTRY="4";
	
	String REQUISITION_DETAIL_VO_LIST="requisitionDtlVOList";
	String REQUISITION_DETAIL_VO_LIST_OLD="requisitionDtlVOListOld";
	
	String REQUISITION_CHANGE_BY[]={"For Patient","For Organisation","For Particular Date"};
	String REQUISITION_SCHEDULE_DATE_LIST="requisitionScheduleDateList";
	
	String MEDICALBOARD_CHECKLIST_VO_LIST="mbChecklistVOList";
	String MB_REQUISITION_CHECKLIST_VO_LIST="mbRequisitionChecklistVOList";
	
	// Board Master //
	
	String LIST_OF_EMP_DOCTOR="ListOfEmpDoctor";
	String LIST_OF_EMP_ESCORTS="ListOfEmpEscort";
	String LIST_OF_ROLL="ListOfRoll";
	
	String BOARD_TYPE_LIST="BoardTypeList";
	
	String BOARD_DETAIL_VOS="BoardDetailVos";
	
	String CERTIFICATE_TYPE_VO="CertificateTypeVo";
	String BOARD_DETAIL_VO="BoardDetailVo";
	
	String BOARD_NAME_LIST="BoardNameList";
	String CERTIFICATE_TYPE_LIST="CertificateTypeList";
	
	String SELECTED_BOARD_LIST="SelectedBoardList";
	String UNSELECTED_BOARD_LIST="UnselectedBoardList";

	//Certificate Checklist master
	
	String IS_COMPULSORY_OPTION_LIST="isCompulsoryOptionList";

	

	String CERTIFICATE_TYPE_NAME="certificateTypeName";
	String CHECKLIST_ADDED_TO_CERTIFICATE_TYPE="checklistAddedToCertificateType";
	String CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE="checklistNotAddedToCertificateType";
	String CERTIFICATE_CHECKLIST_VO_LIST="certificateChecklistVOlist";
	
	//Board_status
	String BOARD_STATUS_INPROCESS="1";
	String BOARD_STATUS_CLOSED="2";
	
	String MEDICAL_BOARD_EPISODE_VO="medicalBoardEpisodeVO";
	String MEDICAL_BOARD_REFER_MAPPING_VO_LIST="medicalBoardReferMappingVOList";
	
	//Medical Board Refer Type
	String MEDICAL_BOARD_REFER_TYPE_DEPARTMENT="1";
	String MEDICAL_BOARD_REFER_TYPE_UNIT="2";
	
	//Refer isOptional
	String MEDICAL_BOARD_REFER_IS_OPTIONAL_YES="1";
	String MEDICAL_BOARD_REFER_IS_OPTIONAL_NO="0";
	
	String MEDICAL_BOARD_DEPT_UNIT="medicalBoardDeptUnit";
	
	//isRefer
	String IS_REFERRED_YES="1";
	String IS_REFERRED_NO="0";
	//isInvRaised
	String IS_INVESTIGATION_RAISED_NO="0";
	String IS_INVESTIGATION_RAISED_YES="1";
	
	//online offline flag
	String ONLINE="1";
	String OFFLINE="2";
	
	String POST_ENTRY_FLAG=ONLINE;
	
	
	
	/**************medical Board intialization*****************/
	String ALL_CERTIFICATE_TYPE_LIST="allCertificateTypeList";
	String MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE="medicalBoardListByCertificateType";
	String ADDED_BOARD_TEAM_DETAIL_VO_LIST="addedBoardTeamDetailVoList";
	String ADDED_ESCOETED_EMP_LIST="addedEscortedEmpList";
	String ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE="assignBoardListByCertificateType";
	
	String ROLE_ID_FOR_ESCORTED_EMPLOYEE="0";
	
	//duty status
	String DUTY_ASSIGNED="1";
	String DUTY_PERFORMED="2";
	String DUTY_CANCELED="3";
	
	//Board Status
	String BOARD_INPROCESS="1";
	String BOARD_CLOSED="2";
	
	String ASSIGNED_ESCOETED_EMP_LIST="assignedEscoetedEmpList";
	String ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST="assignedBoardTeamDetailVoList";
	String NEW_BOARD_ADD_LIST="newBoardAddList";
	String ADDED_BOARD_TEAM_DETAIL_VO_LIST_FOR_NEW_BOARD="addedBoardTeamDetailForNewBoard";
	String ADDED_ESCOETED_EMP_LIST_FOR_NEW_BOARD="addedEscortedEmpListForNewBoard";
	String ASSIGNED_ESCOETED_EMP_LIST_FOR_MODIFY="assignedEscortedEmpListForModify";
	String ASSIGNED_BOARD_TEAM_DETAIL_VO_LIST_FOR_MODIFY="assignedBoardTeamDetailForModify";
	String ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE_FOR_MODIFY="assignBoardListForModify";
	
	String ALL_DEPARTMENT_LIST="AllDepartmentList";
	String ALL_UNIT_LIST="AllUnitList";
	
	String ALL_DEPARTMENT_LIST_OLD="AllDepartmentListOld";
	String ALL_UNIT_LIST_OLD="AllUnitListOld";

	
	String EPISODE_REF_VO_LIST="episodeRefVOList";
	String EPISODE_EXT_REF_VO_LIST="episodeExtRefVOList";
	String MB_INVESTIGATION_MAPPING_VO_LIST="mbInvestigationMappingVOList";
	String MB_INVESTIGATION_TEST_SAMPLE_LIST="mbInvestigationTestSampleVOList";
	
	//Investigation isOptional
	String MEDICAL_BOARD_INVESTIGATION_IS_OPTIONAL_YES="1";
	String MEDICAL_BOARD_INVESTIGATION_IS_OPTIONAL_NO="0";
	
	String MEDICAL_BOARD_EPISODE_VO_LIST="mbEpisodeVOList";
	String MEDICAL_BOARD_PATIENT_DTL_VO_LIST="mbPatientDtlVOList";
	
	//Investigation
	String GROUP_CODE="0";
	String IS_GROUP_CODE="0";
	String INVESTIGATION_TEST_DTL_PROCEDURE="PKG_TESTPROCEDURE.getTestDetails";
	String REQUEST_AREA="1";
	String REQUEST_TYPE="2";//opd
	String INV_RAISE_PRIORITY_NORMAL="0";
	String INV_REQUISITION_DTL_VO_LIST="invReqDetailVOList";
	String ALL_OPINION_LIST_BY_REQUISITION_ID="allOpinionListByReqId";
	String ALL_BOARD_MEMBER_LIST_BY_REQUISITION_ID="allBoardMemberListByRequisitionId";
	
	//certificate Result
	String MB_CERTIFICATE_RESULT_POSITIVE="1";
	String MB_CERTIFICATE_RESULT_NEGATIVE="0";
	
	String IS_MEDICAL_PERFORMED_YES="1";
	String IS_MEDICAL_PERFORMED_NO="0";
	
	String MB_CONSULTANT_LIST="mbConsultantList";


	String YESNO_FLAG_NO="No";
	String YESNO_FLAG_YES="Yes";
	
	String DEPARTMENT_UNIT_SELECTED_LIST="DepartmentUnitSelectedList";
	String REFER_TYPE_FLAG_DEPARTMENT="1";
	String REFER_TYPE_FLAG_UNIT="2";
	
	String REFER_TYPE_DEPARTMENT="Department";
	String REFER_TYPE_UNIT="Unit";
	
	String REFER_DETAIL_VOS="ReferDetailVos";
	String PROCESS_TYPE_DEPARTMENT="1";
	
	/***********************CERTIFICATE HANDOVER DETAIL********************/
	
	String ALL_VARIFIED_CERTIFICATE_TYPE_LIST="allVarifiedCertificateTypeList";
	String ALL_REQ_DATE_BY_CERTIFICATE_TYPE_ID="allReqDateByCertificateTypeId";
	String CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE="candidateListByCertificateTypeAndReqDate";
	
	// issueType status 
	String REQUISITION_BY_ORGANIGATION_ONLY="1";
	String REQUISITION_BY_CANDIDATE_ONLY="2";
	String REQUISITION_BY_BOTH_ORGANIZATION_OR_CANDIDATE="3";
	
	String ISSUE_TO_ORGANIGATION_ONLY="1";
	String ISSUE_TO_CANDIDATE_ONLY="2";
	String ISSUE_TO_BOTH_CANDIDATE_OR_ORGANIZATION="3";
	String ISSUE_TO_RELATIVE="3";
	
	//deliveryType
	String DELIVERY_TYPE_REGISTERED_POST="1";
	String DELIVERY_TYPE_NORMAL_POST="2";
	
	//is duplicate flag
	String IS_DUPLICATE_YES="1";
	String IS_DUPLICATE_NO="0";
	
	//is authority proved
	String IS_AUTHORITY_PROVED_YES="1";
	String IS_AUTHORITY_PROVED_NO="0";
	
	
	String SELECTED_CANDIDATE_LIST="selectedCadidateList";
	String ALL_RELATIONSHIP_LIST="allRelationShipList";
	String ALL_HAND_OVER_BY_EMP_LIST="allHandOverByEmpList";
	String REQUISITION_NO_LIST_BY_CRNO_NO="allCandidateListByReqNo";
	
	String SEARCH_TYPE_CERTIFICATE_TYPE_WISE="1";
	String SEARCH_TYPE_PATIENT_WISE="2";
	
	String SELECTED_REQUISITION_BY_PATIENTWISE="selectedRequisitionByPatient";
	
	/***********INVESTIGATION MAPPING MST*******************/
	
	String ALL_LAB_TEST_LIST="allLabTestList";
	//String ALL_SELECTED_LAB_TEST_LIST="allSelectedLabTestList";
	String ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST="allSelectedInvestigationMappingVOList";
 
	/**************certificateVerification********************/
	String CERTIFICATE_TYPE_LIST_FOR_CERT_VERIFICATION="certTypeListForVerification";
	String MEDICAL_BOARD_LIST_FOR_CERT_VERIF="boardListForCertVerification";
	String REQUISITION_DETAIL_VO_LIST_FOR_CERT_VERIFICATION="reqVoListForCertVerification";
	String BOARD_MEMBER_OPINION_LIST_BY_REQID="boardMemberOpinionListByReqId";

	
	
	// online offline flag
	
	String LIST_OF_TEMPLATE="ListOfTemplate";
	
	// Template Id 
	
	String HGNUM_TEMPLATE_CATEGORY ="31";
	String DEPARTUNITCODE ="10117";
	
	
	//Web Cam Fiel Upload
	//String WEB_CAM_FILE_UPLOAD_BYTE_ARRAY = "webCamFileUploadArray";
	}




