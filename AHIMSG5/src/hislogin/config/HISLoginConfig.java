/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISLoginConfig.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hislogin.config;

public interface HISLoginConfig
{
	// Configuration Settings
	
	public static String PASSWORD_STRENGTH_NO = "0";		// Password can Contain any character 
	public static String PASSWORD_STRENGTH_LOW = "1";		// Password Must Contain at least one Alphabet
	public static String PASSWORD_STRENGTH_MEDIUM = "2";	// Password Must Contain at least one Alphabet and one number
	public static String PASSWORD_STRENGTH_HIGH = "3";		// Password Must Contain at least one Alphabet, one number and one special character
	public static String PASSWORD_LOW_MESSAGE = "Password must contain at least one Alphabet.";
	public static String PASSWORD_MEDIUM_MESSAGE = "Password must contain at least one Alphabet and one Number.";
	public static String PASSWORD_HIGH_MESSAGE = "Password must contain at least one Alphabet, one Number and one Special Character.";
	public static String PASSWORD_STRENGTH = PASSWORD_STRENGTH_HIGH;
	public static String PASSWORD_STRENGTH_MESSAGE = PASSWORD_MEDIUM_MESSAGE;
	
	// Status Flags
	
	// Keys
	// HIS Logins
	public static String LOGGEDIN_USER_FAVAOURITE_LIST = "keyLoggedInUserFavouriteMenuList";
	public static String KEY_QUESTION_LIST = "keyQuestionList";
	public static String KEY_MENU_LIST = "keyMenuList";
	public static String KEY_MODULE_LIST = "keyModuleList";
	public static String KEY_MODULE_MENU_LIST = "keyModuleMenuList";
	public static String KEY_FAVOURITE_LIST = "keyFavouriteList";
	public static String KEY_LOGIN_LOG = "keyLoginLog";
	public static String KEY_LOGIN_FEATURES_USER_VO = "keyLoginFeaturesUserVO";
	public static String LOGGEDIN_USER_SELECTED_MENU_LIST = "keyLoggedInUserSelectedMenuList";
	public static String LOGGEDIN_USER_SELECTED_MENU_PROCESS_LIST = "keyLoggedInUserSelectedMenuProcessList";
	public static String LOGGEDIN_USER_SELECTED_MENUS_TYPE_COUNT = "keyLoggedInUserSelectedMenuTypeCount";



	// UserVO
		// HospitalVO
		// SysDate
	// UserDesk
		// Last Login Log
		// Unlogged out Log
		// Favourite List
		// 
	
}
