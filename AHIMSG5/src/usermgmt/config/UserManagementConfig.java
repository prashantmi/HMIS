package usermgmt.config;

public interface UserManagementConfig
{
	// Configuration Settings
	
	// Status Flags
	

	// View Procedures
	String PROC_VIEW_USER_MASTER = "{call pkg_usermgmt.proc_gblt_user_mst(?,?,?,?,?,?,?,?,?,?)}";
	String PROC_VIEW_HOSPITAL_MASTER = "{call pkg_usermgmt.proc_gblt_hospital_mst(?,?,?,?,?,?)}";
	String PROC_VIEW_MENU_MASTER = "{call pkg_usermgmt.proc_gblt_menu_mst(?,?,?,?,?,?)}";
	String PROC_VIEW_USER_LOGIN_LOG_DETAIL = "{call pkg_usermgmt.proc_user_login_log(?,?,?,?,?,?,?,?,?,?)}";
	String PROC_VIEW_UM_TABLES = "{call pkg_usermgmt.proc_gblt_tables(?,?,?,?,?)}";
	// DML Procedures
	String PROC_DML_USER_LOGIN_LOG_DETAIL = "{CALL pkg_usermgmt.dml_user_login_log(?,?,?,?,?,?,?)}";
	String PROC_DML_USER_MASTER = "{CALL pkg_usermgmt.dml_gblt_user_mst(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    String PROC_DML_MENU_MASTER="{CALL pkg_usermgmt.dml_gblt_menu_mst(?,?,?,?,?,?,?)}";


	// Keys
	String KEY_USER_MENU_LIST = "keyUserMenuList";
	String KEY_USER_FAVORITE_MENU_LIST = "keyUserFavoriteMenuList";
	String KEY_SYSTEM_DATETIME = "keySystemDateTime";
	String KEY_CASH_COLLECTION_ALLOWED = "keyCashCollectionAllowed";
	String KEY_USER_ALLOWED_MENU_LIST = "keyUserAllowedMenuList";


}
