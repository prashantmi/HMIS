/** Module Name: PIS
*  Name of Process: PIS Global Configuration 
*  Name of Developer: Sh. Ashwini Mishra 
*  Name of TL: Sh. Ashwini Mishra 
*  Date of Creation: 08-07-2014
*  Last Modifier: Ashwini Mishra 
*  Last Modification Date: 21-11-2014
*  
*/

package hr.pis.config;
                                   
/**
 * PisConfig is an interface that defines hard-coded values that are
 * used for development of BO and DAO.
 * @author AHIS 
 */

public interface PisConfig {
	
	public static final String SUPER_USER_HOSPITAL_CODE="100";
	public static final String LOCAL_LANGUAGE="marathi";
	public static final String DEFAULT_VALUE_FOR_COUNTRY = "IND";
			
	public static final String FLAG_FOR_BASIC_PAY_WITHIN_RANGE = "1"; //1= Within Range, 0=Not Within Range
	
	public static final String VALUE_FOR_ORDER_AUTHORITY_TYPE_VISIBILITY = "1"; //1= Dont Show, 2=Show
	
	public static final String VALUE_FOR_FIELDS_VISIBILITY_MODE = "2"; //1=Show, 2=Hide
	
	public static final String LEAVE_MODULE_VALUE_FOR_FIELDS_VISIBILITY_MODE1 = "1"; //1=Show, 2=Hide FOR After Availed And Emergency Field
	public static final String LEAVE_MODULE_VALUE_FOR_FIELDS_VISIBILITY_MODE2 = "1"; //1=Show, 2=Hide FOR Leave Org. Period Field
	public static final String LEAVE_MODULE_VALUE_FOR_FIELDS_VISIBILITY_MODE3 = "1"; //1=Show, 2=Hide FOR Substitute Field
	public static final String LEAVE_MODULE_FLAG_FOR_POWER_DELEGATE_TO_FLD_VISIBILITY= "1"; //1=Show, 2=Hide FOR Substitute Field
	
	
	//For Order Default Values
	public static final String ORDER_DEFAULT_VALUE_FOR_ORDER_TO="1";
	public static final String ORDER_DEFAULT_VALUE_FOR_ORDER_COPY_TO="2";
	
	//Start: For Reports
	public static final String REPORT_LOGO_PATH = "/HISPis/hisglobal/images/logo.png"; 
	 
	//End: For Reports
	
	
	// Module ID
	public static final String MODULE_ID_PIS  = "20";
	public static final String MODULE_ID_Desk = "21";
			
	
	public static final String EMP_NO_LENGTH="12";
	public static final String EMP_NO_SIZE="14";
	public static final String EMP_NO_TYPE="1"; //1=numeric, 2=Alphanumeric, 3=Alpha
	public static final String EMP_NO_TYPE_CODE="9"; //5=numeric, 9=Alphanumeric with Space, 11=Alpha with space
	
	public static final String EMP_NO_FORMAT_SIZE_TWELVE = "12"; // value for size 12
	public static final String EMP_NO_FORMAT_SIZE = EMP_NO_FORMAT_SIZE_TWELVE;
	
	
	
	
			//Nature of Job Master (used by PIS and SC)
				public static final String PIST_NATURE_OF_JOB_MST_PERMANENT 			= "1";
				public static final String PIST_NATURE_OF_JOB_MST_CONTRACTUAL 			= "2";
				public static final String PIST_NATURE_OF_JOB_MST_ADHOC 				= "3";
				public static final String PIST_NATURE_OF_JOB_MST_DEPUTATION 			= "4";
				
				public static final String SALARY_TYPE_ID_FOR_GRADE ="1";
				public static final String SALARY_TYPE_ID_FOR_CONSOLIDATED ="2";
				
				//Configuration Master
					//Employee Class 
					public static final String PIST_CONFIGURATION_MST_CLASS_FACULTY 					= "21011";
					public static final String PIST_CONFIGURATION_MST_CLASS_LECTURERS	 				= "21012";
					
					
					//Status 
					public static final String PIST_CONFIGURATION_MST_STATUS_PROBATION 					= "23001"; 
					public static final String PIST_CONFIGURATION_MST_STATUS_PROBATION_EXTENSION 		= "23002";
					public static final String PIST_CONFIGURATION_MST_STATUS_CONFIRMED 					= "23003";
												
					//Final Status 
					public static final String PIST_CONFIGURATION_MST_FST_IN_SER			 			= "24001";
					public static final String PIST_CONFIGURATION_MST_FST_RETIRE		 				= "24002";
					public static final String PIST_CONFIGURATION_MST_FST_VOL_RETIRE					= "24003";	
					public static final String PIST_CONFIGURATION_MST_FST_PRE_RETIRE					= "24004";	
					public static final String PIST_CONFIGURATION_MST_FST_MED_RETIRE					= "24005";		
					public static final String PIST_CONFIGURATION_MST_FST_PER_TRANSFER					= "24006";	
					public static final String PIST_CONFIGURATION_MST_FST_ABSORPTION					= "24007";			
					public static final String PIST_CONFIGURATION_MST_FST_RESIGN		 				= "24008";
					public static final String PIST_CONFIGURATION_MST_FST_TERMINATE						= "24009";	
					public static final String PIST_CONFIGURATION_MST_FST_SUPERANNUATION		 		= "24010";
					public static final String PIST_CONFIGURATION_MST_FST_DEATH							= "24011";	
					public static final String PIST_CONFIGURATION_MST_FST_SER_PERIOD_COM				= "24012";	
		
					
					//Pay Scale Type
					public static final String PIST_CONFIGURATION_MST_PST_IAB			 				= "13001";
					public static final String PIST_CONFIGURATION_MST_PST_GPB			 				= "13002";
					
					public static final String PIST_CONFIGURATION_MST_PST_7PC			 				= "10005";
					
					
					
			//Organogram	
				//Organogram Type Master
				public static final String PIST_ORGANOGRAM_TYPE_MST_ORGANIZATION 			= "1";
				public static final String PIST_ORGANOGRAM_TYPE_MST_LEAVE		 			= "2";
					
			//Official Number MAster
				public static final String PIST_OFFICIAL_NUM_MST_BANK 					= "11";
				public static final String PIST_OFFICIAL_NUM_MST_LIC		 			= "12";
				public static final String PIST_OFFICIAL_NUM_MST_GPF 					= "13";
				public static final String PIST_OFFICIAL_NUM_MST_CC		 				= "14";
			//	public static final String PIST_OFFICIAL_NUM_MST_PC		 				= "15";
					
			//NAtionality Master
				public static final String NATIONALITY_INDIAN_ID 			= "1";
				
			//Score Type Master
				public static final String PIST_TYPE_OF_SCORE_ID			="20";
				
			//Leave Master
				public static final String LEAVE_EARNED_LEAVE_ID 			= "10002";
				public static final String LEAVE_HALF_PAY_LEAVE_ID 			= "10004";
				public static final String LEAVE_COMMUTTED_LEAVE_ID		 	= "10005";
				public static final String LEAVE_RESTRICTED_LEAVE_ID 		= "10006";
				public static final String LEAVE_VACATION_LEAVE_ID 			= "10015";
				
			
			//Address Type Master (used by PIS and SC)
				public static final String PIST_ADDRESS_TYPE_MST_OTHERS = "1";
				public static final String PIST_ADDRESS_TYPE_MST_HOME_TOWN = "2";
				public static final String PIST_ADDRESS_TYPE_MST_CURRENT= "4";

				
			//Role Management Type Master (used by PIS)
				public static final String GBLT_ROLE_MGT_TYPE_MST_GENERAL = "10001";
				public static final String GBLT_ROLE_MGT_TYPE_MST_LEAVE = "10002";
	
			// Transfer
				//Transfer Mode
				public static final String TRANS_MODE_ON_REQ = "11";
				public static final String TRANS_MODE_ADMINISTRATIVE = "12";
				
			// Promotion
				//Salary Type
				public static final String SALARY_TYPE_FOR_GRADE = "1";
				public static final String SALARY_TYPE_FOR_CONSOLIDATED = "2";
				
				//Promotion Mode
				public static final String PROM_MODE_FOR_DIRECT = "11";
				public static final String PROM_MODE_THROUGH_PROM_DUE_LIST = "12";
				
				
				
				//SUSPENSION Mode
				public static final String SUSP_MODE_ADMINISTRATIVE = "11";
				public static final String SUSP_MODE_AGAINSTCOMPLAIN = "12";
			
				//Increment
					//Increment Withheld Type Master
					public static final String INC_WH_REASON_ID_FOR_WITHHELD_OF_INCREMENT_TEMPORARY = "11";
				
					//Increment Emp. Specific
					public static final String INC_TYPE_ID_FOR_ANNUAL = "1";
					
				
				
					
				// Process Master
					// Field Value Master	
					public static final String PRO_ID_FOR_TRANSFER_REQ_DTL = "10101"; 
						public static final String TRANSFER_REQ_DTL_FLD1 = "1010101";
							public static final String TRANSFER_REQ_DTL_FLD1_VAL_NEW = "10";
							public static final String TRANSFER_REQ_DTL_FLD1_VAL_IN_PRO = "20";
							public static final String TRANSFER_REQ_DTL_FLD1_VAL_VAL = "30";
							public static final String TRANSFER_REQ_DTL_FLD1_VAL_REJ = "40";
							public static final String TRANSFER_REQ_DTL_FLD1_VAL_TRANSFERRED = "50";
						
						public static final String TRANSFER_REQ_DTL_FLD2 = "1010102"; 
											
					
					public static final String PRO_ID_FOR_TRANSFER_REQ_REC_HOD = "10103";  
						public static final String TRANSFER_REQ_REC_HOD_FLD1 = "1010301";
						
					public static final String PRO_ID_FOR_TRANSFER_REQ_APP_ESTB_SEC = "10104";				
						public static final String TRANSFER_REQ_APP_ESTB_SEC_FLD1 = "1010401";
						
					public static final String PRO_ID_FOR_TRANSFER_ORDER_DTL = "10105";
						public static final String TRANSFER_ORDER_DTL_FLD1 = "1010501";
						
					public static final String PRO_ID_FOR_TRANSFER_ORDER_VAL = "10106";
						public static final String TRANSFER_ORDER_VAL_FLD1 = "1010601";	
					
					//Increment Withheld Dtl
					public static final String PRO_ID_FOR_INC_WH="10201";
						public static final String FLD_ID_FOR_INC_WH_DTL="1020101";
				
					public static final String PRO_ID_FOR_INC_WH_VAL = "10202";
					
					public static final String PRO_ID_FOR_INC_EOS = "10203";
					
					public static final String PRO_ID_FOR_INC_EOS_VAL = "10204";
					
					public static final String PRO_ID_FOR_INC_EOC = "10205";
					
					public static final String PRO_ID_FOR_INC_EOC_VAL = "10206";
					
					//Increment (Emp. Specific)
					public static final String PRO_ID_FOR_INC_EMP_SPECIFIC="10207";
						public static final String FLD_ID_FOR_INC_EMP_SPECIFIC="1020701";
				
							
					
					public static final String PRO_ID_FOR_LEAVE_REQUEST="10303";
						public static final String FIELD_ID_FOR_LEAVE_REQUEST="1030301";
							public static final String LEAVE_REQ_LEAVE_FLAG_FOR_NEW = "10";
							public static final String LEAVE_REQ_LEAVE_FLAG_FOR_INPROCESS = "20";
							public static final String LEAVE_REQ_LEAVE_FLAG_FOR_GRANTED = "30";
							public static final String LEAVE_REQ_LEAVE_FLAG_FOR_REJECTED = "40";
							public static final String LEAVE_REQ_LEAVE_FLAG_FOR_CANCELLED = "50";
						
					public static final String PRO_ID_FOR_OFFLINE_LEAVE_REQUEST="10311";					
						
					public static final String PRO_ID_FOR_LEAVE_REQUEST_AUTHORISATION="10305";
						public static final String FIELD_ID_FOR_LEAVE_REQUEST_AUTHORISATION="1030501";
							public static final String LEAVE_REQ_AUTH_TYPE_FOR_SANCTION = "10";
							public static final String LEAVE_REQ_AUTH_TYPE_FOR_APPROVING = "20";
							public static final String LEAVE_REQ_AUTH_TYPE_FOR_HR = "30";
						
						public static final String FIELD_ID_FOR_LEAVE_REQUEST_SANCTIONED="1030502";
							public static final String LEAVE_REQ_STATUS_FOR_RECOMMENDED = "10";
							public static final String LEAVE_REQ_STATUS_FOR_REJECTED = "20";
						
						public static final String FIELD_ID_FOR_LEAVE_REQUEST_APPROVED="1030503";
						
						public static final String FIELD_ID_FOR_LEAVE_REQUEST_HR="1030504";
					
						
						
					//Leave Joining
					public static final String PROCESS_ID_FOR_LEAVE_JOINING="10306";
						public static final String FIELD_ID_FOR_LEAVE_JOINING="1030601";
							public static final String LEAVE_JOINING_REQ_STATUS_FOR_NEW = "10";
							public static final String LEAVE_JOINING_REQ_STATUS_INPROCESS = "20";
							public static final String LEAVE_JOINING_REQ_STATUS_APPROVED = "30";
							public static final String LEAVE_JOINING_REQ_STATUS_REJECTED = "40";
	
						
					
					//Leave Joining Authorization
					public static final String PROCESS_ID_FOR_LEAVE_JOINING_AUTHORISATION="10308";
						public static final String FIELD_ID_FOR_LEAVE_JOINING_AUTHORISATION="1030801";
							public static final String LEAVE_JOINING_AUTH_TYPE_FOR_SANCTION = "10";
							public static final String LEAVE_JOINING_AUTH_TYPE_FOR_HR = "30";
							public static final String LEAVE_JOINING_AUTH_STATUS_FOR_RECOMMENDED = "10";
							public static final String LEAVE_JOINING_AUTH_STATUS_FOR_REJECTED = "20";
							
						public static final String FIELD_ID_FOR_LEAVE_JOINING_SANCTIONED="1030802";
							
						public static final String FIELD_ID_FOR_LEAVE_JOINING_APPROVED="1030804";	
						
					//Leave Encashment	
					public static final String PROCESS_ID1_FOR_LEAVE_ENCASH="10313";
						public static final String FIELD_ID_FOR_LEAVE_ENCASH_REQ_STATUS="1031301";
							public static final String LEAVE_ENCASH_REQ_STATUS_FOR_NEW = "10";
							public static final String LEAVE_ENCASH_REQ_STATUS_FOR_INPROCESS = "20";
							public static final String LEAVE_ENCASH_REQ_STATUS_FOR_GRANTED = "30";
							public static final String LEAVE_ENCASH_REQ_STATUS_FOR_REJECTED= "40";
						
					//Leave Encashment Authorization				
					public static final String PROCESS_ID2_FOR_LEAVE_ENCASH_AUTH="10314";
						public static final String FIELD_ID_FOR_LEAVE_ENCASH_AUTH_TYPE="1031401";
							public static final String LEAVE_ENCASH_AUTH_TYPE_FOR_SANCTION = "10";
							public static final String LEAVE_ENCASH_AUTH_TYPE_FOR_APPROVING = "20";
							public static final String LEAVE_ENCASH_AUTH_TYPE_FOR_HR = "30";
						public static final String FIELD_ID_FOR_LEAVE_ENCASH_SANCTIONED="1031402";
						public static final String FIELD_ID_FOR_LEAVE_ENCASH_APPROVED="1031403";
						public static final String FIELD_ID_FOR_LEAVE_ENCASH_HR="1031404";
						
					
					//Promotion
					public static final String PRO_ID_FOR_PROMOTION_DUE_LIST = "10401";
						public static final String PROMOTION_DUE_LIST_FLD1 = "1040101";
							public static final String PROMOTION_DUE_LIST_FLD1_FOR_NEW = "10";
							public static final String PROMOTION_DUE_LIST_FLD1_FOR_SUBMITTED = "20";
						public static final String PROMOTION_DUE_LIST_FLD2 = "1040102";
							public static final String PROMOTION_DUE_LIST_FLD2_FOR_NEW = "10";
							public static final String PROMOTION_DUE_LIST_FLD2_FOR_COMPLETED = "20";
					
					public static final String PRO_ID_FOR_PROMOTION_DUE_LIST_FILTER = "10402";
						public static final String PROMOTION_DUE_LIST_FILTER_FLD1 = "1040201";
							public static final String PROMOTION_DUE_LIST_FILTER_FLD1_FOR_NEW = "10";
							public static final String PROMOTION_DUE_LIST_FILTER_FLD1_FOR_SUBMITTED = "20";
						public static final String PROMOTION_DUE_LIST_FILTER_FLD2 = "1040202";
							public static final String PROMOTION_DUE_LIST_FILTER_FLD2_FOR_NEW = "10";
							public static final String PROMOTION_DUE_LIST_FILTER_FLD2_FOR_COMPLETED = "20";
						
					public static final String PRO_ID_FOR_PROMOTION_ORDER_DTL = "10405";
						public static final String PROMOTION_ORDER_DTL_FLD1 = "1040501";
							public static final String PROMOTION_ORDER_DTL_FLD1_FOR_IN_PROCESS = "10";
							//public static final String PROMOTION_ORDER_DTL_FLD1_FOR_IN_PROCESS = "20";
							public static final String PROMOTION_ORDER_DTL_FLD1_FOR_VALIDATED_AND_JOINING_REQ = "30";
							public static final String PROMOTION_ORDER_DTL_FLD1_FOR_VALIDATED_AND_PROMOTED = "40";
							public static final String PROMOTION_ORDER_DTL_FLD1_FOR_REJECTED = "50";
							public static final String PROMOTION_ORDER_DTL_FLD1_FOR_CANCELLED = "60";
							
						
					public static final String PRO_ID_FOR_PROMOTION_ORDER_VAL = "10406";
						public static final String PROMOTION_ORDER_VAL_FLD1 = "1040601";	
				
					public static final String PRO_ID_FOR_PD_IMMOVABLE_PROPERTY = "10501";
						public static final String PD_IMMOVABLE_PROPERTY_FLD1 = "1050101";	
							public static final String  PD_IMMOVABLE_PROPERTY_FLD1_FOR_NEW = "10";
							public static final String  PD_IMMOVABLE_PROPERTY_FLD1_FOR_SUBMIT_TO_HR = "20";
							public static final String  PD_IMMOVABLE_PROPERTY_FLD1_FOR_UPDATE_BY_HR = "30";
							public static final String  PD_IMMOVABLE_PROPERTY_FLD1_FOR_REJECTED = "40";
						public static final String PD_IMMOVABLE_PROPERTY_FLD2 = "1050102";	
							public static final String  PD_IMMOVABLE_PROPERTY_FLD2_FOR_IN_POSSESION = "10";
							public static final String  PD_IMMOVABLE_PROPERTY_FLD2_FOR_NOT_IN_POSSESION = "20";
					
					//Suspension		
					public static final String PRO_ID_FOR_SUSPENSION_ORDER_DTL = "10701";
						public static final String SUSPENSION_ORDER_DTL_FLD1 = "1070101";	
							public static final String  SUSPENSION_ORDER_DTL_FLD1_FOR_NEW = "10";
							public static final String  SUSPENSION_ORDER_DTL_FLD1_FOR_VALIDATED = "30";
							public static final String  SUSPENSION_ORDER_DTL_FLD1_FOR_REJECTED = "40";

					public static final String PRO_ID_FOR_SUSPENSION_ORDER_DTL_VALIDATION = "10702";
						public static final String SUSPENSION_ORDER_DTL_VALIDATION_FLD1 = "1070201";	
							
					public static final String PRO_ID_FOR_SUSPENSION_ALLOWANCE_REVIEW_DTL = "10703";
						public static final String SUSPENSION_ALLOWANCE_REVIEW_DTL_FLD1 = "1070301";	
								public static final String  SUSPENSION_ALLOWANCE_REVIEW_DTL_FLD1_FOR_NEW = "10";
								public static final String  SUSPENSION_ALLOWANCE_REVIEW_DTL_FLD1_FOR_VALIDATED = "30";
								public static final String  SUSPENSION_ALLOWANCE_REVIEW_DTL_FLD1_FOR_REJECTED = "40";
					
								
					public static final String PRO_ID_FOR_SUSPENSION_ALLOWANCE_REVIEW_DTL_VALIDATION = "10704";
						public static final String SUSPENSION_ALLOWANCE_REVIEW_DTL_VALIDATION_FLD1 = "1070401";	
					
					public static final String PRO_ID_FOR_SUSPENSION_REVOCATION_DTL = "10705";
						public static final String SUSPENSION_REVOCATION_DTL_FLD1 = "1070501";
							public static final String  SUSPENSION_REVOCATION_DTL_FLD1_FOR_NEW = "10";
							public static final String  SUSPENSION_REVOCATION_DTL_FLD1_FOR_VALIDATED = "30";
							public static final String  SUSPENSION_REVOCATION_DTL_FLD1_FOR_REJECTED = "40";
			
					public static final String PRO_ID_FOR_SUSPENSION_REVOCATION_DTL_VALIDATION = "10706";	
						public static final String SUSPENSION_REVOCATION_DTL_VALIDATION_FLD1 = "1070601";	
						
					//Deputation Out
					public static final String PROCESS_ID_FOR_DEPUTATION_OUT="10801";
						public static final String FIELD_ID_FOR_DEPUTATION_OUT="1080101";
				
					public static final String PROCESS_ID_FOR_DEPUTATION_OUT_VALIDATION="10802";
						public static final String FIELD_ID_FOR_DEPUTATION_OUT_VALIDATION="1080201";
						
					// Status Change And Extention
					public static final String PROCESS_ID_FOR_STATUS_CHANGE_AND_EXTENTION="10901";
						public static final String FIELD_ID_FOR_STATUS_CHANGE_AND_EXTENTION="1090101";
					
					public static final String PROCESS_ID_FOR_STATUS_CHANGE_AND_EXTENTION_VALIDATION="10902";
						public static final String FIELD_ID_FOR_STATUS_CHANGE_AND_EXTENTION_VALIDATION="1090201";
						
					
				//Disciplinary Process	
					//Disciplinary Complaint	
					public static final String PROCESS_ID_FOR_DP_COMPLAINT="11001";
						public static final String DP_COMPLAINT_FLD1="1100101";
						public static final String DP_COMPLAINT_FLD2="1100102";
						
					//Disciplinary Investigating Officer	
					public static final String PROCESS_ID_FOR_DP_OFFICER="11002";
						public static final String DP_OFFICER_FLD1="1100201";
					

					// Disciplinary Proceeding	
					public static final String PROCESS_ID_FOR_DP_PROCEEDINGS="11003";
						public static final String DP_PROCEEDINGS_FLD1="1100301";

					// Disciplinary Authority Decision	
					public static final String PROCESS_ID_FOR_DP_AUTHORITY_DECISION="11004";
						public static final String DP_AUTHORITY_DECISION_FLD1="1100401";
						
					//Demotion Mode
					public static final String DEMOTION_MODE_FOR_DIRECT = "11";
						public static final String DEMOTION_MODE_THROUGH_DP = "12";
					
					// Demotion Order Details	
					public static final String PROCESS_ID_FOR_DP_DEMOTION_ORDER_DTL="11005";
						public static final String DP_DEMOTION_ORDER_DTL_STATUS_FLD="1100501";
							public static final String  DP_DEMOTION_ORDER_DTL_STATUS_FLD_FOR_NEW = "10";
							public static final String  DP_DEMOTION_ORDER_DTL_STATUS_FLD_FOR_VALIDATED = "30";
							public static final String  DP_DEMOTION_ORDER_DTL_STATUS_FLD_FOR_REJECTED = "40";
						
					// Demotion Order Validation Details	
					public static final String PROCESS_ID_FOR_DP_DEMOTION_ORDER_DTL_VALIDATION="11006";
						public static final String DP_DEMOTION_ORDER_DTL_VALIDATION_VALIDATE_STATUS_FLD="1100601";
							public static final String  DP_DEMOTION_ORDER_DTL_VALIDATION_VALIDATE_STATUS_FLD_FOR_VALIDATED = "1";
							public static final String  DP_DEMOTION_ORDER_DTL_VALIDATION_VALIDATE_STATUS_FLD_FOR_REJECTED = "2";
						
					
						
				//Order Details	
					public static final String PRO_ID_FOR_ORDER_DETAILS = "11101";
						public static final String ORDER_DETAILS_FLD1 = "1110101";
				
				//Service Book: PCE	
					public static final String PRO_ID_FOR_PCE = "11201";
					public static final String PRO_ID_FOR_EMP_CERTIFICATE = "11202";
				
					
				//Service Book: Internal Audit Details	
					public static final String PRO_ID_FOR_SB_AUDIT_DETAILS = "11301";
						
					//Personnel Details	
					public static final String PRO_ID_FOR_PERSONNEL_DETAILS = "11401";
					
					
				/*
				//public static final String TRANSFER_REQ_REC_HOD_FLD1 = "str_auth_type";	
				public static final String INC_WH_FLD1 = "num_is_inc_wh_done";
				public static final String INC_WH_VAL_FLD1 = "str_validate_status";
				public static final String INC_EOS_FLD1 = "num_is_inc_done";
				public static final String INC_EOS_VAL_FLD1 = "str_validate_status";
				public static final String INC_EOC_FLD1 = "num_is_inc_done";
				public static final String INC_EOC_VAL_FLD1 = "str_validate_status";
				*/
				
				String OPTIONS_PROCESS_FLD_VAL1= "optionProcessFldVal1";
				String OPTIONS_PROCESS_FLD_VAL2 = "optionProcessFldVal2";
				
								
				//Event Master
				public static final String EVENT_ID_PROBATION_EXTENTION="13";
				public static final String EVENT_ID_PROBATION_CLEARANCE="14";
				public static final String EVENT_ID_PROMOTION="15";
				public static final String EVENT_ID_PROMOTION_WITHHELD="16";
				public static final String EVENT_ID_INCREMENT="17";
				public static final String EVENT_ID_INCREMENT_WITHHELD="18";
				public static final String EVENT_ID_TRANSFER="19";
				public static final String EVENT_ID_SUSPENSION="20";
				public static final String EVENT_ID_SUSPENSION_ALLOW_REV="21";
				public static final String EVENT_ID_REVOCATION="22";
				public static final String EVENT_ID_DEPUTATION_IN="23";
				public static final String EVENT_ID_PERIOD_EXTENDED_DI="24";
				public static final String EVENT_ID_DEPUTATION_OUT="25";
				public static final String EVENT_ID_PERIOD_EXTENDED_DO="26";
				public static final String EVENT_ID_PERIOD_COMPLETED_DO="27";
				public static final String EVENT_ID_SERVICE_PERIOD_EXTENTION="28";
				public static final String EVENT_ID_SERVICE_COMPLETE="29";
				
		
				//PCE
				String SB_PCE_ENTRY_TYPE_ONLINE="1";
				String SB_PCE_ENTRY_TYPE_OFFLINE="2";
				
				
				
				
				//Leave Offline and Leave Request and Leave Sanctioning
				public static final String ORGANOGRAM_TYPE_ID="1";
				public static final String HOLIDAY_ID_FOR_GHAZETTED_HOLIDAY="11";
				public static final String HOLIDAY_ID_FOR_WEEKLY_OFF="13";
				
				
				
				// Emp Status Change And Extention Dtl
				String STATUS_CHNG_TYPE_ID_FOR_OTHER="11";
				String STATUS_CHNG_TYPE_ID_FOR_END_OF_SERVICE="12";
				
				String EVENT_FOR_PERMANENT_PROB_NODEP_NOSUSP="13, 14";
				String EVENT_FOR_PERMANENT_PROB_NODEP_SUSP="13, 14";
				String EVENT_FOR_PERMANENT_PROB_DEP_NOSUSP="13, 14, 26, 27";
				String EVENT_FOR_PERMANENT_PROB_DEP_SUSP="13, 14, 26, 27";
				String EVENT_FOR_PERMANENT_PROBCLR_NODEP_NOSUSP="";
				String EVENT_FOR_PERMANENT_PROBCLR_NODEP_SUSP="";
				String EVENT_FOR_PERMANENT_PROBCLR_DEP_NOSUSP="26, 27";
				String EVENT_FOR_PERMANENT_PROBCLR_DEP_SUSP="26, 27";
				String FINAL_STATUS_FOR_PERMANENT="24002, 24003, 24004, 24005, 24006, 24007, 24008, 24009, 24010, 24011";
				
				String EVENT_FOR_ADHOC_PROB_NODEP_NOSUSP="13, 14, 28";
				String EVENT_FOR_ADHOC_PROB_NODEP_SUSP="13, 14, 28";
				String EVENT_FOR_ADHOC_PROB_DEP_NOSUSP="13, 14, 28";
				String EVENT_FOR_ADHOC_PROB_DEP_SUSP="13, 14, 28";
				String EVENT_FOR_ADHOC_PROBCLR_NODEP_NOSUSP="28";
				String EVENT_FOR_ADHOC_PROBCLR_NODEP_SUSP="28";
				String EVENT_FOR_ADHOC_PROBCLR_DEP_NOSUSP="28";
				String EVENT_FOR_ADHOC_PROBCLR_DEP_SUSP="28";
				String FINAL_STATUS_FOR_ADHOC="24002, 24003, 24004, 24005, 24006, 24007, 24008, 24009, 24010, 24011";
				
				String EVENT_FOR_CONTRACTUAL_PROB_NODEP_NOSUSP="13, 14, 28";
				String EVENT_FOR_CONTRACTUAL_PROB_NODEP_SUSP="13, 14, 28";
				String EVENT_FOR_CONTRACTUAL_PROB_DEP_NOSUSP="13, 14, 28";
				String EVENT_FOR_CONTRACTUAL_PROB_DEP_SUSP="13, 14, 28";
				String EVENT_FOR_CONTRACTUAL_PROBCLR_NODEP_NOSUSP="28";
				String EVENT_FOR_CONTRACTUAL_PROBCLR_NODEP_SUSP="28";
				String EVENT_FOR_CONTRACTUAL_PROBCLR_DEP_NOSUSP="28";
				String EVENT_FOR_CONTRACTUAL_PROBCLR_DEP_SUSP="28";
				String FINAL_STATUS_FOR_CONTRACTUAL="24002, 24003, 24004, 24005, 24006, 24007, 24008, 24009, 24010, 24011";
				
				
				String EVENT_FOR_DEPUTATION_PROB_NODEP_NOSUSP="13, 14, 24";
				String EVENT_FOR_DEPUTATION_PROB_NODEP_SUSP="13, 14, 24";
				String EVENT_FOR_DEPUTATION_PROB_DEP_NOSUSP="13, 14, 24";
				String EVENT_FOR_DEPUTATION_PROB_DEP_SUSP="13, 14, 24";
				String EVENT_FOR_DEPUTATION_PROBCLR_NODEP_NOSUSP="24";
				String EVENT_FOR_DEPUTATION_PROBCLR_NODEP_SUSP="24";
				String EVENT_FOR_DEPUTATION_PROBCLR_DEP_NOSUSP="24";
				String EVENT_FOR_DEPUTATION_PROBCLR_DEP_SUSP="24";
				String FINAL_STATUS_FOR_DEPUTATION="24012";
				
				
				String OPTIONS_STATUS_CHANGE_TYPE="optionStatusChangeTypeList";
				
				//Deputation Out
				public static final String TYPE_OF_EMPLOYEES_FOR_DEPUTATION_OUT="1";  // 1=Permanent, 2=Contractual, 3=Adhoc, 4=Deputation
				
				//Complaint Proceeding
				public static final String TYPE_OF_PROCEEDING_FOR_OTHERS="99";
				public static final String TYPE_OF_ACTIVITY_FOR_OTHERS="99";
					
				
				
					
				//START: Global
				String LEAVE_ESSENTIAL_BO_OPTION_ORGANOGRAM_TYPE = "optionOrganogramType";
				String LEAVE_ESSENTIAL_BO_OPTION_LEAVE_TYPE = "optionLeaveType";
				String LEAVE_ESSENTIAL_BO_OPTION_LEAVE = "optionLeave";
				String LEAVE_ESSENTIAL_BO_OPTION_GENDER = "optionGender";
				String LEAVE_ESSENTIAL_BO_OPTION_LEAVE_AVAIL_TYPE = "optionLeaveAvailType";
				String LEAVE_ESSENTIAL_BO_OPTION_LEAVE_NAT_OF_JOB_TYPE = "optionLeaveNatOfJob";
				String LEAVE_ESSENTIAL_BO_OPTION_EMPLOYEE_CLASS = "optionLeaveEmployeeClass";
				String LEAVE_ESSENTIAL_BO_OPTION_LEAVE_PERIODICITY = "optionLeavePeriodcity";
				String LEAVE_ESSENTIAL_BO_OPTION_LEAVE_CUMULATIVE = "optionLeaveCumulative";
					
				String OPTIONS_SALARY_TYPE = "optionSalaryType";
				String OPTIONS_PROCESS_TYPE = "optionProcessType";
				String OPTIONS_PROCESS = "optionProcess";
				String OPTIONS_PROCESS_FIELD_TYPE = "optionProcessFieldType";
				String OPTIONS_PROCESS_MAPPING_TYPE = "optionProcessMapping";
				String OPTIONS_PROCESS_NAME = "optionProcessName";
				String OPTIONS_ACTIONS_FROM = "optionAction";
				String OPTIONS_ACTIONS_TO = "optionActionTo";
				String OPTIONS_MODULE = "optionModule";
					
					
				String OPTIONS_PS_CATEGORY = "optionPayScaleCategory";
				String OPTIONS_PS_REVISION = "optionPayScaleRevision";
				String OPTIONS_PS_TYPE = "optionPayScaleType";

			
				String OPTIONS_EMP_OFFICE_LIST = "optionEmpOffice";
				String OPTIONS_ESTB_SEC_LIST = "optionEstbSec";
				String OPTIONS_SERVICE_GROUP = "optionSerGrp";				
				String OPTIONS_DESIGNATION_LIST = "optionDesignationList";
				String OPTIONS_EVENT_LIST = "optionEventList";
				String OPTIONS_DEPARTMENT_LIST = "optionDept";	
				String OPTIONS_PLACE_OF_POSTING_LIST = "optionPlaceOfPosting";	
				String OPTIONS_CADRE_LIST = "optionCadre";
				String OPTIONS_EMP_CLASS_LIST = "optionEmpClass";
				String OPTIONS_EMP_STATUS_LIST = "optionStatusList";
				String OPTIONS_EMP_FINAL_STATUS_LIST = "optionFinalStatusList";
				String OPTIONS_PRD_EVENT_LIST = "optionPRDEventList";
				
					
				//Transfer
				String OPTIONS_TRANSFER_REASON = "proctransferreason";
				String OPTIONS_TRANSFER_MODE = "proctransfermode";
				String OPTIONS_TRANSFER_TYPE = "proctransfertype";
				String OPTIONS_TRANSFER_CRITERIA = "proctransfercriteria";
				String OPTIONS_TRANSFER_REQUEST_STATUS = "proctransferrequeststatus";
				
				//Promotion
				String OPTIONS_PROMOTION_MODE = "procpromotionmode";
				String OPTIONS_PROMOTION_TYPE = "procpromotiontype";
				String OPTIONS_PROMOTION_STATUS = "procpromotionstatus";
				String OPTIONS_PROMOTION_SUB_STATUS = "procpromotionsubstatus";
				String OPTIONS_PROMOTION_DUE_LIST= "procpromotionduelist";
				
				//Suspension
				String OPTIONS_SUSPENSION_REASON = "procsuspensionreason";
				String OPTIONS_SUSPENSION_MODE = "procsuspensionmode";
				String OPTIONS_SUSPENSION_TYPE = "procsuspensiontype";
				String OPTIONS_SUSPENSION_STATUS = "procsuspensionstatus";
				
				//Suspension Allow Review
				String OPTIONS_SUSPENSION_AllW_REVIEW_REASON = "procsuspensionreason";
				String OPTIONS_SUSPENSION_AllW_REVIEW_MODE = "procsuspensionmode";
				String OPTIONS_SUSPENSION_AllW_REVIEW_TYPE = "procsuspensiontype";
				String OPTIONS_SUSPENSION_AllW_REVIEW_STATUS = "procsuspensionallowreviewstatus";
				
				
				String OPTIONS_PROMOTION_SCR_TYPE = "procpromotionscrtype";
				//Process Field Value
				String OPTIONS_PROC_FLD = "optionProcField";
				String OPTIONS_PROC_FLD_VAL = "optionProcFieldVal";

				String OPTIONS_MEASURING_UNIT_TYPE = "optionMeasuringUnitType";
				
				//Immovable Property Declaration
				String OPTIONS_CHANGE = "optionChange";
				String OPTIONS_ACT_ON_PROP = "optionActOnProp";
				String OPTIONS_IMMOV_PROP_TYPE = "optionImmovPropType";
				String OPTIONS_COUNTRY = "optionCountry";
				String OPTIONS_STATE = "optionState";
				String OPTIONS_DISTRICT = "optionDistrict";
				String OPTIONS_DEC_STATUS = "optionDecStatus";
				String OPTIONS_PROP_AREA_UNIT = "optionPropAreaUnit";
				String OPTIONS_LAND_NATURE = "optionLandNature";
				String OPTIONS_PROP_ACQUIRED_TYPE = "optionPropAcquiredType";
				String OPTIONS_COST_UNIT = "optionCostUnit";
				String OPTIONS_ANNUAL_INC_UNIT = "optionAnnualIncUnit";
				String OPTIONS_SOLD_PRICE_UNIT = "optionSoldPriceUnit";
				String OPTIONS_PROP_STATUS = "optionPropStatus";
				

				//Monthly Attendance
				String OPTIONS_MONTH = "optionMonth";
				String OPTIONS_YEAR = "optionYear";
				
				//Acr Track Details
				String OPTION_ACR_LOCATION="optionAcrLocation";
				
				String OPTION_ACR_COMP_STATUS="optionAcrCompStatus";
				String OPTION_ACR_RO_PER_GRADE="optionAcrRoPerGrade";
				String OPTION_ACR_REV_PER_GRADE="optionAcrRevPerGrade";
					
				//END: Global	
				
				//Disciplinary (DP)
				String 	OPTIONS_COMPLAIN_TYPE="optionComplainType";
				String 	OPTIONS_COMPLAIN_CATEGORY="optionComplainCategory";
				String 	OPTIONS_COMPLAINT_SOURCE="optionComplainSource";
				String 	OPTIONS_INVESTIGATING_OFFICER="optionInvestigatingOfficer";
					
				String 	OPTIONS_ACTIVITY_TYPE="optionActivityType";
				String 	OPTIONS_PROCEEDING_TYPE="optionProceedingType";
				//Authority Order Details
				String OPTION_AUTH_ORDER_DTL_PROCESS="optionProcessType";
				String OPTION_AUTH_ORDER_DTL_STATUS="optionOrderStatusType";
				
				//University/Board Master
				String OPTION_UNIVERSITY_BOARD="optionUniversityBoard";
				
				//Course Master
				String OPTION_COURSE_TYPE="optionCourseType";
				String OPTION_COURSE_LEVEL="optionCourseLevel";
				
				//Subject Master
				String OPTION_COURSE="optionCourse";
				
				//DP Authority Details
				String OPTION_PENALTY_TYPE="optionPenaltyType";
				String OPTION_PENALTY="optionPenalty";
				String OPTION_INVOLVE_NATURE="optionInvolveNature";
				
				
				//Demotion
				String OPTIONS_DEMOTION_MODE = "procdemotionmode";
				String OPTIONS_DEMOTION_STATUS = "procdemotionstatus";
				
				//Travel Mode Value
				String OPTIONS_TRAVEL_MODE = "optionTravelMode";
				
				//Transportation Form Value
				String OPTIONS_TRANS_FORM = "optionTransForm";
				
				//EMployee Certificate 
				String 	OPTIONS_EMPLOYEE_CERTIFICATE="optionEmployeeCertificate";
				
				
				/*
			 	01-Personnel Record
				03- transfer
				04-Increment
				05-Promotion
				06-Disciplinary Process
				07-Suspension
				08-Demotion
				09-PCD
				10-Global -Organogram, Role management
				11-Leave
				12- property Dec
				13- Attendance
				14-ACR
			*/
					
}//end
