package hissso.config;

import javax.servlet.ServletContext;

public abstract class HISSSOConfig
{
	// Configuration Settings
		// Secure Key Sizes
	public static int LOGIN_USER_SESSION_SALT_BYTE_SIZE = 24; // Good Size
		// Ticket Expiration
	public static int SSO_TICKET_EXPIRY_IN_SECONDS_TGT = 3600;		// 60 Minutes
	public static int SSO_TICKET_EXPIRY_IN_SECONDS_ST = 1200;		// 20 Minutes
	
	//public static int SSO_TICKET_EXPIRY_IN_SECONDS_TGT = 120;		// 60 Minutes
	//public static int SSO_TICKET_EXPIRY_IN_SECONDS_ST = 60;		// 20 Minutes
		// Login Configurations
	public static int LOGIN_LOCK_AFTER_UNSUCCESSFUL_LOGIN_COUNT = 20; //Changed from 3 to 20 by Garima for AIIMS Requirement discussed with Cheema Sir on 1st Nov 2018
		// Authorization
	public static int AUTHORIZATION_URL_LEVEL_AT_APPLICATION_ON = 1; 
	public static int AUTHORIZATION_URL_LEVEL_AT_APPLICATION_OFF = 0; 
	public static int AUTHORIZATION_URL_LEVEL_AT_APPLICATION_STATUS = AUTHORIZATION_URL_LEVEL_AT_APPLICATION_ON; 
	
		// Ticket Essentials
	public static String SSO_TGT_SERVICE_URL = "/HISSSOService";
	public static String SSO_TICKET_ID_INFO_SEPARATOR = "#";
	//public static String SSO_LOGIN_URL = "/hissso/Login.action";
	// Changes by Garima for extension change
	public static String SSO_LOGIN_URL = "/hissso/Login";
		// Error Page
	public static String SSO_AUTHENTICATION_ERROR_PAGE_URL = "/hissso/jsp/error/sso_error_login_authenticate.jsp";
	public static String SSO_AUTHORIZATION_ERROR_PAGE_URL = "/hissso/jsp/error/sso_error_login_authorize.jsp";
	public static String SSO_UNKNOWN_ERROR_PAGE_URL = "/hissso/jsp/error/sso_error_login_unknown.jsp";
	public static String SSO_ILLEGAL_ACTIVITY_ERROR_PAGE_URL = "/hissso/jsp/error/sso_error_login_illegalactivity.jsp";
		

	
	// Keys
		// Logged-in User Session Keys
	public static String LOGGEDIN_USER_GRANTING_TICKET_ID_VAR = "varSSOTicketGrantingTicket";
	public static String LOGGEDIN_USER_GRANTING_TICKET_ID = "keyLoggedInUserGrantingTicketId";
	public static String LOGGEDIN_USER_AUTHENTICATION_OBJECT = "keyLoggedInUserAuthenticationObject";
	public static String LOGGEDIN_USER_AUTHORIZATION_OBJECT = "keyLoggedInUserAuthorizationObject";
	public static String LOGGEDIN_USER_HASH_SALT = "keyLoggedInUserHashSalt";
	public static String LOGGEDIN_USER_SERVICE_TICKET_ID = "keyLoggedInUserServiceTicketId";
	public static String LOGGEDIN_USER_SECRET_KEY = "keyLoggedInUserSecretKey";
	
		// Logged-in User Session Keys for Specific
	public static String LOGGEDIN_USER_VO = "keyLoggedInUserVO";
	public static String LOGGEDIN_HOSPITALVO = "keyLoggedInHospitalVO";
	public static String LOGGEDIN_CURRENT_YEAR = "keyLoggedInCurrentYear";
	public static String LOGGEDIN_CURRENT_MONTH = "keyLoggedInCurrentMonth";
	public static String LOGGEDIN_SYSDATE_OBJECT = "keyLoggedInUserSystemDateObject";
	public static String LOGGEDIN_SYSDATE_STRING = "keyLoggedInUserSystemDateString";


		// SSO Registry
	public static String SSO_TICKET_REGITRY = "keySSOTicketRegistry";
	
	//Cash Collection Div Flag
	public static String SHOW_USERWISE_CASH_COLLECTED_DTL="keyToShowUserWiseCashCollected";
	public static String USERWISE_TOTAL_CASH_COLLECTED_DTL_MAP="keyUserWiseCashCollectedDtlMap"; 
	public static String USERWISE_TOTAL_CASH_COLLECTED="keyUserWiseCashCollected"; 
	public static String KEY_CASH_COLLECTION_ALLOWED = "keyCashCollectionAllowed";

	public static int LOGGEDIN_USER_MAX_TAB_ALLOWABLE = 5;
	public static String DESK_ALERT_COUNT="alertCount";
	public static String DESK_MARQUEE_MSG="mrqMsg";
	public static String SHOW_MENU="showMenu";
	public static String IS_ALERT_ROLE_ASSIGNED="keyToShowAlertNotificationIcon"; 

	// Initializing HISSSO Configurations  
	public static void initializeSSOClient(ServletContext objContext)
	{
		// Set SSO ST Register
		HISSSOSupport.initializeSSORegister(objContext);
		
		// Set Service Ticket
		HISSSOSupport.initHISServiceObject(objContext);
	}
}
