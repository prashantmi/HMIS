package mortuary;

public interface MortuaryConfig 
{
	////////////////////////////Query File Name///////////////////////////////////////////////////
	String QUERY_FILE_FOR_MORTUARY_DAO = "mortuary.mortuaryQuery";
	String QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO = "mortuary.mortuaryEssentialQuery";
	String QUERY_FILE_FOR_MORTUARY_MASTER_DAO = "mortuary.mortuaryMasterQuery";
	
	
	//////// Autopsy Type//////////////////
	String AUTOPSY_TYPE_NO_AUTOPSY="0";
	String AUTOPSY_TYPE_NORMAL_AUTOPSY="1";
	String AUTOPSY_TYPE_MLC_AUTOPSY="2";
	String AUTOPSY_TYPE_NORMAL_N_MLC_AUTOPSY="3";
	
	//////// Autopsy For//////////////////	
	String AUTOPSY_FOR_ALL_PATIENT_INHOUSE="1";
	String AUTOPSY_FOR_INHOUSE_DEATH_ONLY="2";
	String AUTOPSY_FOR_BROUGHT_DEATH_ONLY="3";
	
	///// Mortuary Type/////////////////
	String MORTUARY_TYPE_NORMAL="1";
	String MORTUARY_TYPE_EMERGENCY="2";
	
	/////// patient is MLC //////////////
	String IS_MLC_YES="1";
    String IS_MLC_NO="0";
	
	
	String ESSENTIAL_ALL_DEPARTMENT="essentialAllDepartment";
	String ESSENTIAL_ALL_EMP_BASED_ON_DEPT="essentialAllEmpBasedOnDept";
	String ESSENTIAL_ALL_BUILDING_BLOCK="essentialAllBuilding";
	String ESSENTIAL_ALL_BLOCK_BASED_ON_BUILDING="essentialAllBlockBasedOnBuilding";
	String ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK="essentialAllFloorBasedOnBlock";
	String ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR="essentialAllRoomBasedOnFloor";
	String ESENTIAL_ALL_PATIENT_RELATION="essentialAllPatientRelation";
	String ESENTIAL_ALL_CHAMBER="essentialAllChamber";
	String ESSENTIAL_ALL_CHAMBER_RACK_VO="essentialAllChamberRackVO";
	String ESSENTIAL_ALL_CHAMBER_RACK_LIST="essentialAllChamberRackList";
	String ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST="essentialAllMortuaryEmployeeList";
	String ESSENTIAL_ALL_OPINION="essentialAllOpinion";
	String ESSENTIAL_ALL_DECEASED_ITEM="essentialAllDeceasedItem";
	String ESSENTIAL_DEATH_MANNER_LIST="essentialDeathMannerList";
	String ESSENTIAL_INJURY_NATURE_LIST="essentialInjuryNatureList";
	String ESSENTIAL_INJURY_TYPE_LIST="essentialInjuryTypeList";
	String ESSENTIAL_INCISION_TYPE_LIST="essentialIncisionTypeList";
	String ESSENTIAL_AUTOPSY_TYPE_LIST="essentialAutopsyTypeList";
	String ESSENTIAL_PRESERVATIVE_LIST="essentialPreservativeList";
	String ESSENTIAL_ALL_EXTERNAL_LAB_LIST="essentialAllExternalLabList";
	String ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST="essentialAllExternalLabTestList";
	String ESSENTIAL_ALL_ASSOCIATED_HOSPITAL_LIST="essentialAllAssociatedHospitalList";
	
	
	String ESENTIAL_ALL_MORTUARY="essentialAllMortuary";
	String ESENTIAL_ALL_AREA_BASED_ON_MORTUARY="essentialAllAreaBasedOnMortuary";
	String ESENTIAL_ALL_CHAMBER_BASED_ON_MORTUARY_AREA="essentialAllChamberBasedOnMortuaryArea";
	
	String PATIENT_DEATH_DETAIL_VO="patientDeathDetailVO";
	
	
	/////////// Deceased Acceptance////////////
	String DEAD_PATIENT_LIST_SEND_TO_MORTUARY="deadPatientListSendToMortuary";
	String EXISTING_POLICE_VERIFICATION_DETAIL="existingPoliceVerificationDetail";	
	String ARR_DECEASED_EXISTING_IMAGE_VO="arrDeceasedExistingImageVO";
	String ARR_NEW_ADDED_IMAGE="arrNewAddedImage";
	String UPLOADED_IMAGE_IN_SESSION="uploadedImageInSession";
	String DECEASED_IMAGE_UPLOADED_IN_SESSION="deceasedImageInSession";	
	String PATIENT_IMAGE_MAP="patientImageMap"; 
	
	String DECEASED_COLOR_NORMAL="#000000";				//BLACK
	String DECEASED_COLOR_MLC="#FF0000";				//RED
	String DECEASED_COLOR_BROUGHT_DEAD="#0000FF";		//BLUE
	String DECEASED_COLOR_UNKNOWN="#008000";			//GREEN
	
	////Deceased Type//////
	String DECEASED_TYPE_NORMAL="Normal";
	String DECEASED_TYPE_MLC="MLC";
	String DECEASED_TYPE_BROUGHT_DEAD="Brought Dead";
	String DECEASED_TYPE_UNKNOWN="Unknown";
	
	String ESSENTIAL_PEON_LIST="essentialPeonList";
	String NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER="0";
	String NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_HANDOVER="1";
	String NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE="2";
	
	///////////Body Handover To//////////
	String BODY_HANDOVER_TO_HOSPITAL_STAFF="1";
	String BODY_HANDOVER_TO_POLICE="2";
	String BODY_HANDOVER_TO_RELATIVE="3";
	
	////////Body Brought BY//////////////
	String BODY_BROUGHT_BY_HOSPITAL_STAFF="1";
	String BODY_BROUGHT_BY_HOSPITAL_POLICE="2";
	String BODY_BROUGHT_BY_HOSPITAL_RELATIVE="3";
	
	////////Mortuary Body Entry Mode////////////
	String ENTRY_MODE_INHOUSE_SEND_ONLINE="1";
	String ENTRY_MODE_INHOUSE_SEND_OFFLINE="2";
	String ENTRY_MODE_BROUGHT_DEAD_FROM_SPOT_BY_POLICE="3";
	String ENTRY_MODE_SEND_FROM_OTHER_HOSPITAL="4";
	String ENTRY_MODE_SEND_FROM_OTHER_PLACES="5";
	
	/////////Mortuary Body Status//////////
	String BODY_STATUS_IN_MORTUARY_OR_STREACHER="0";
	String BODY_STATUS_IN_CHAMBER="1";
	String BODY_STATUS_ISSUE_FOR_POSTMORTEM="2";
	String BODY_STATUS_HANDOVER="3";
	
	/*Setting processId for Employee List*/
	///////////Process ID/////////////////
	String DECEASED_ACCEPTANCE_PROCESS_ID="6";
	String DECEASED_ACCEPTANCE_BROUGHT_BY_PEON="6";
	String POSTMORTEM_REQUEST_APPROVED="4";
	
	////////Chamber Status///////////////
	String CHAMBER_STATUS_NOT_USED="0";
	String CHAMBER_STATUS_WORKING="1";
	
	////////Chamber Type///////////////
	String CHAMBER_TYPE_RACK_MOUNT="1";
	String CHAMBER_TYPE_CHAMBER_ROOM="2";
	
	////////Chamber Rack Status///////////////
	String CHAMBER_RACK_STATUS_NOT_USED="0";
	String CHAMBER_RACK_STATUS_AVAILABLE="1";
	String CHAMBER_RACK_STATUS_OCCUPIED="2";
	
	
	String DUTY_OFFICER_IS_IO="0";
	String DUTY_OFFICER_IS_OTHER="1";
	
	//////POLICE VERIFICATION//////////
	String POLICE_VERIFICATION_NEW="1";
	String POLICE_VERIFICATION_EXISTING="2";
	
	//////POLICE VERIFICATION EXISTS//////////
	String POLICE_VERIFICATION_EXIST_YES="1";
	String POLICE_VERIFICATION_EXIST_NO="2";
	
	////////Image Upload Mode///////////
	String IMAGE_UPLOAD_FROM_MORTUARY="1";
	String IMAGE_UPLOAD_FROM_EMERGENCY="2";
	
	/////Existing Image Exist Flag////////
	String IMAGE_EXIST_YES="1";
	String IMAGE_EXIST_NO="0";
	
	////default Image////////
	String IS_DEFAULT_IMAGE_YES="1";
	String IS_DEFAULT_IMAGE_NO="0";
	
	////Deceased Storage Out For////////
	String DECEASED_OUT_FOR_HANDOVER="1";
	String DECEASED_OUT_FOR_POSTMORTUM="2";
	String DECEASED_OUT_FOR_TRANSFER="3";
	
	//////Deceased Storage Upto///////
	String DECEASED_STORAGE_UPTO_HOURS="72";
	
	/////Deceased Relative Flag/////////
	String DECEASED_RELATIVE_FLAG_HANDOVER="1";
	String DECEASED_RELATIVE_FLAG_STORAGE="2";
	String DECEASED_RELATIVE_FLAG_POSTMORTEM_REQUEST_BY_POLICE="3";
	String DECEASED_RELATIVE_FLAG_POSTMORTEM_PERFORMANCE="4";
	String DECEASED_RELATIVE_FLAG_IDENTIFIED="5";
	
	///Is Deceased Storage By Relative///
	String IS_DECEASED_STORAGE_BY_RELATIVE_YES="1";
	String IS_DECEASED_STORAGE_BY_RELATIVE_NO="0";
	
	//////Storage Deceased
	String DECEASED_LIST_IN_STREACHER="deceasedListInStreacher";
	
	////Deceased Handover//
	String DECEASED_LIST_TO_HANDOVER="deceasedListToHandover";
	String ARR_DECEASED_RELATIVE_VO="arrDeceasedRelativeVO";
	String IS_DECEASED_HANDOVER_FLAG="isDeceasedHandoverFlag";
	String DECEASED_POSTMORTEM_STATUS="deceasedPostmortemStatus";
	String DECEASED_HANDOVER_DTL_VO="deceasedHandOverDtlVO";
	String HOSPITAL_MST_VO="hospitalMasterVO";
	
	////Hand over Flag////
	String DEAD_BODY_HANDOVER_EXISTING="1";
	String DEAD_BODY_HANDOVER_NEW="2";
	
	////Relative Exist To Handover
	String RELATIVE_EXIST_TO_HANDOVER_YES="1";
	String RELATIVE_EXIST_TO_HANDOVER_NO="0";
	
	////Postmortem Request
	String DECEASED_LIST_FOR_POSTMORTEM_REQUEST="deceasedListForPostmortemRequest";
	String ARR_OPINION_REQUEST_VO="arrOpinionRequestVO";
	String ARR_ITEM_REQUEST_VO="arrItemRequestVO";
	String ARR_DECEASED_EXISTING_RELATIVE_VO="arrDeceasedExistingRelativeVO";
	String ARR_DECEASED_ADDED_RELATIVE_VO="arrDeceasedAddedRelativeVO";
	String ARR_CONSENT_MAPPING_DETAIL="arrConsentMappingDetail";
	
	///Postmortem Status
	String POSTMORTEM_STATUS_REQUEST_RAISED="1";
	String POSTMORTEM_STATUS_REQUEST_APPROVED="2";
	String POSTMORTEM_STATUS_REQUEST_CANCELED="3";
	String POSTMORTEM_STATUS_REQUEST_WAVEOFF="4";
	String POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY="5";
	String POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE="6";
	
	///Postmortem Is Repeat
	String POSTMORTEM_IS_REPEAT_YES="1";
	String POSTMORTEM_IS_REPEAT_NO="0";
	
	///Postmortem Type
	String POSTMORTEM_TYPE_FORENSIC="1";
	String POSTMORTEM_TYPE_CLINICAL="2";
	String POSTMORTEM_TYPE_NON_FORENSIC="3";
	
	///Postmortem Request Type
	String POSTMORTEM_REQ_TYPE_BODY="1";
	String POSTMORTEM_REQ_TYPE_BONE="2";
	
	/// Deceased List For Postmortem Entry
	String DECEASED_LIST_FOR_POSTMORTEM_ENTRY="deceasedListForPostmortemEntry";
	String POSTMORTEM_CONDUCTED_BY_LIST="postmortemConductedByList";
	String POSTMORTEM_CONDUCTED_BY_ROLE="postmortemConductedByRole";
	String ARR_POSTMORTEM_TEAM_DETAIL_VO="arrPostmortemTeamDetailVO";
	
	
	
	/////Deceased Item Preservation////////
	String ARR_DECEASED_ITEM_TOBE_PRESERVED="arrDeceasedItemToBePreserved";
	String ITEM_NOT_REQUESTED_N_PRESERVED_LIST="itemNotRequestedNPreservedList";
	String ARR_EXTRA_ITEM_ADDED_VO="arrExtraItemAddedVO";
	String ARR_PRESERVED_ITEM_DETAIL_VO="arrPreservedItemDetailVO";
	
	String IS_DECOMPOSITION_YES="1";
	String IS_DECOMPOSITION_NO="0";
	
	////Deceased Body Looks////////
	String DECEASED_BODY_LOOKS_FLAT="1";
	String DECEASED_BODY_LOOKS_PLUMP="2";
	String DECEASED_BODY_LOOKS_LEAN="3";
	
	////Postmortem Conducted By/////
	String POSTMORTEM_CONDUCTED_BY_TEAM="1";
	String POSTMORTEM_CONDUCTED_BY_PANEL="2";
	
	////Deceased Tile//////
	String DECEASED_DETAIL_VO="deceasedDetailVO";


	String[] DECEASED_ITEM_TYPE={"","Viscera","Blood","Cloth"};
	String DECEASED_ITEM_TYPE_LIST="itemTypeLst";
	String[] MORTUARY_AREA_TYPE={"","Primary Reception Area","Body Storage","Post Mortem Area"};
	String MORTUARY_AREA_TYPE_LIST="areaTypeLst";
	String MORTUARY_AREA_TYPE_RECEPTION="1";
	String MORTUARY_AREA_TYPE_STORAGE="2";
	String MORTUARY_AREA_POSTMORTEM="3";

	
	////Body Identification///
	String BODY_IDENTIFIED_BY_RELATIVE="1";
	String BODY_IDENTIFIED_BY_POLICE="2";
	String ARR_BODY_IDENTIFIED_BY="arrBodyIdentifiedBy";
	String ARR_BODY_IDENTIFIED_BY_POLICE="arrBodyIdentifiedByPolice";
	String ARR_BODY_IDENTIFIED_BY_RELATIVE="arrBodyIdentifiedByRelative";
	
	////Body Identification Mode ///
	String BODY_IDENTIFICATION_MODE_POSTMORTEM="1";
	String BODY_IDENTIFICATION_MODE_UNIDENTIFY_BODY="2";
	String BODY_IDENTIFICATION_MODE_CLAIMANT="3";
	
	///Photo Upload//////////
	String ARR_DECEASED_ADDED_PHOTO_VO="arrDeceasedAddedPhotoVO";
	
	String YES="1";
	String NO="0";
	
	///Unclaimed Body
	String IS_BODY_CLAIMED_YES="1";
	String IS_BODY_CLAIMED_NO="0";
	
	//Unknown & Unclaimed Body identification
	String UNKNOWN_N_UNCLAIMED_BODY_LIST="unknownNUnclaimedBodyList";
	String DECEASED_DETAIL_BY_DECEASED_NO_VO="deceasedDetilByDeceasedNoVO";	
	
	String UNIDENTIFIED_BODE_YES="1";
	String UNIDENTIFIED_BODE_NO="0";
	
	String ARR_OPINION_REQUESTED_VO="arrOpinionRequestedVO";
	String OPINION_LIST_NOT_REQUESTED="opinionListNotRequested";
	String ARR_EXTRA_OPINION_REQUESTED_VO="arrExtraOpinionRequestedVO";
	String OPINION_LIST_NOT_ADDED="opinionListNotAdded";
	
	////Team Member Detail
	String ARR_ADDED_TEAM_MEMBER_VO="addedTeamMemberVO";
	
	String ARR_ADDED_OPINION_VO="arrAddedOpinionVO";
	
	///Template Id list
	String TEMPLATE_ID_LIST_FOR_POSTMORTEM="templateIdListForPostmortem";
	
	////Hand over Flag////
	String DEAD_BODY_IDENTIFY_BY_EXISTING="1";
	String DEAD_BODY_IDENTIFY_BY_NEW="2";
	
	////Relative Exist To Handover
	String RELATIVE_EXIST_TO_IDENTIFY_YES="1";
	String RELATIVE_EXIST_TO_IDENTIFY_NO="0";
	
	///Final Opinion To Be Approved List
	String ARR_FINAL_OPINION_TO_BE_APPROVED_VO="arrFinalOpinionToBeApprovedVO";
	
	
	////Postmortem Request To Be Approved Or Canceled
	String ARR_POSTMORTM_REQUEST_LIST_VO="arrPostmortemRequestListVO";
	String POSTMORTEM_REQUEST_DETAIL_VO="postmortemRequestDetail";
	String ARR_ITEM_PRESERVED_REQUESTED_VO="arrItemPreservedRequestedVO";
	String ITEM_LIST_NOT_REQUESTED="itemListNotRequested";
	String EMPLOYEE_INCHARGE_LIST="employeeInchargeList";
	String EMPLOYEE_APPROVED_BY_LIST="employeeApprovedByList";
	String POSTMORTEM_INCHARGE_ROLE_LIST="postmortemInchargeRoleList";
	String ARR_TEAM_INCHARGE_VO="arrTeamInchargeVO";
	String PM_REQUESTED_RELATIVE_DETAIL_VO="pmRequestedRelativeDetailVO";
	
	

	//PostMortem WaveOff Details
	String POSTMORTEM_WAVEOFF_DETAIL_VO_ARRAY="postMortemWaveoffDetailVOArray";
	String POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO="postmortemWaveoffPoliceVerificationDetailVO";
	String POSTMORTEM_WAVEOFF_MODE_AFTER_POSTMORTEM="1";
	String POSTMORTEM_WAVEOFF_MODE_BEFORE_POSTMORTEM="2";

	////Postmortem Accept/Reject Flag
	String ACCEPTANCE_FLAG_ACCEPT="1";
	String ACCEPTANCE_FLAG_REJECT="2";
	
	///HMRT_DECEASED_DTL -->> HMRNUM_POSTMORTEM_REQ
	String POSTMORTEM_REQUEST_OPTIONAL="0";
	String POSTMORTEM_REQUEST_COMPULSORY="1";
	String POSTMORTEM_REQUEST_WAVEOFF="2";

	
	//Team Incharge Flag
	String POSTMORTEM_INCHARGE_YES="1";
	String POSTMORTEM_INCHARGE_NO="0";
	
	//Postmortem Performed By Incharge Flag
	String INCHARGE_IS_PERFORMED_NO="0";
	String INCHARGE_IS_PERFORMED_ASSIGN="1";
	String INCHARGE_IS_PERFORMED_YES="2";
	
	String DEFAULT_ROLE_ID="11";
	
	///Deceased Item Status
	String DECEASED_ITEM_STATUS_MORTUARY="1";
	String DECEASED_ITEM_STATUS_HANDOVER_TO_POLICE="2";
	String DECEASED_ITEM_STATUS_SEND_TO_LAB="3";
	String DECEASED_ITEM_STATUS_STORE_IN_HOSPITAL="4";
	
	//Police Verification Entry Mode
	String POLICE_VERIFICATION_ENTRY_MODE_MLC="1";
	String POLICE_VERIFICATION_ENTRY_MODE_NEW="2";
	String POLICE_VERIFICATION_ENTRY_MODE_POSTMORTEM="3";
	
	///External Deceased Acceptance ////////////
	String ESS_ALL_GENDER_LIST="essAllGenderList";
	String ESS_ALL_MARITIAL_STATUS_LIST="essAllMaritialStatus";
	
	
	////Relative Code for "Self"
	String RELATIVE_CODE_OF_SELF="10";
	
	
	///Sample Send To external Lab
	String ARR_ADDED_INVESTTIGATION_VO="arrAddedInvestigationVO";
	String ARR_PRESERVED_ITEM_IN_MORTUARY_DETAIL_VO="arrPreservedItemInMortuaryDetailVO";
	
	///External Request Sample Status
	String EXTERNAL_REQUEST_SAMPLE_STATUS_SEND="1";
	String EXTERNAL_REQUEST_SAMPLE_STATUS_RECEIVED_BACK="2";
	
	///External Lab Request Status
	String EXTERNAL_LAB_REQUESTE_STATUS_REQUEST_RAISED="1";
	String EXTERNAL_LAB_REQUESTE_STATUS_REQUEST_SEND="2";
	String EXTERNAL_LAB_REQUESTE_STATUS_REPORT_RECEIVED="3";
	
	///Sample Result Entry
	String ARR_REQUESTED_ID_BY_POSTMORTEM_NO_VO="arrRequestedIdByPostmortemNoVO";
	String ARR_REQUESTED_SAMPLE_BY_REQUEST_ID_VO="arrRequestedSampleSampleByRequestIdVO";
	String ARR_REQUESTED_INVESTIGATION_BY_REQUEST_ID_VO="arrRequestedInvestigationByRequestIdVO";
	String ARR_RECEIVED_REPORT_BY_POSTMORTEM_NO_VO="arrReceivedReportByPostmortemNoVO";
	String MAP_REQUEST_ID_N_FINAL_RESULT_BY_POSTMORTEM_NO="mapRequestIdNFinalResultByPostmortemNo";
	String MAP_REQUEST_ID_N_LAB_TEST_BY_POSTMORTEM_NO="mapRequestIdNLabTestByPostmortemNo";
	
	String DECEASED_NO_BY_POSTMORTEM_ID="decesedNoByPostmortemId";
	
	////Postmortem Handover Detail
	String ARR_POSTMORTEM_READY_TO_HANDOVER_VO="arrPostmortemReadyToHandoverVO";
	
	///Search Deceased No
	String ARR_SEARCHED_DECEASED_VO="arrSearchDeceasedVO";
	
	///Search Postmortem No
	String ARR_SEARCHED_POSTMORTEM_VO="arrSearchedPostmortemVO";
	

	//Reports
	
	String MORTUARY_JRXML_PATH ="/mortuary/reports/jrxml/";
	String LIST_OF_DECEASED="ListofDeceased.jrxml";
	String DOCTOR_WISE_POSTMORTEM_LISTING="DoctorWisePostmortemRecordListing.jrxml";
	String DOCTOR_WISE_POSTMORTEM_COUNT="DoctorWisePostmortemRecordCount.jrxml";
	String DEATHMANNER_WISE_POSTMORTEM_COUNT="DeathMannerWisePostmortemRecordCount.jrxml";
	String LAB_AND_TEST_WISE_POSTMORTEM_LISTING="LabTEstWisePostmortemDetails.jrxml";
	String TEST_WISE_POSTMORTEM_LISTING="TEstWisePostmortemDetails.jrxml";
	String IONAME_AND_PSWISE_POSTMORTEM_DETAIL_IONAMEWISE="PsWisePostmortemDetailsIONAME.jrxml";
	String IONAME_AND_PSWISE_POSTMORTEM_DETAIL_PSNAMEWISE="PsWisePostmortemDetailsPSNAME.jrxml";
	String BROUGHT_DEAD_CASES_DATEWISE="broughtDeadCasesDateWise.jrxml";

	////Consent Service Type Id For Clinical Autopsy
	String CONSENT_SERVICE_TYPE_ID_CLINICAL_AUTOPSY="16";
	String CONSENT_NOT_REQUIRED="0";
	String CONSENT_RAISED="1";
	String CONSENT_RECEIVED="2";
	String CONSENT_GIVEN_BY_RELATIVE="1";
	//Hospital Combo List @ Anant Patel
	String HOSPITAL_COMBO_LIST = "hospitalComboList";

	
}
