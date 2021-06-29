/**********************************************************
 Project:	   AHIMS_G5	
 File:         LoginFeaturesUTL.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hislogin.transactions.utl;

import hisglobal.config.HISConfig;
import hisglobal.security.SecureCryptAlgorithm;
import hisglobal.utility.Entry;
import hisglobal.utility.helper.DateHelperMethods;
import hislogin.config.HISLoginConfig;
import hislogin.transactions.actionsupport.LoginFeatureSUP;
import hislogin.transactions.data.LoginFeaturesDATA;
import hissso.config.HISSSOConfig;
import hissso.config.HISSSOServerConfig;
import hissso.security.SecureHashAlgorithm;
import hissso.validation.credentails.authentication.AuthenticationCredentials;
import hissso.validation.credentails.authorization.AuthorizationCredentials;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import vo.hissso.LoginVO;
import vo.usermgmt.MenuMasterVO;
import vo.usermgmt.UserLoginLogVO;
import vo.usermgmt.UserMasterVO;

public class LoginFeaturesUTL
{

	public static String LOG_FEATURE_STATUS_INITIAL = "INITIAL";
	public static String LOG_FEATURE_STATUS_NEW_PASSWORD = "NEW_PASSWORD";
	public static String LOG_FEATURE_STATUS_CHANGE_PASSWORD = "CHANGE_PASSWORD";
	public static String LOG_FEATURE_STATUS_CHANGE_USER_DETAILS = "CHANGE_USER_DETAILS";
	public static String LOG_FEATURE_STATUS_NEW_USER_DETAILS = "NEW_USER_DETAILS";
	public static String LOG_FEATURE_STATUS_USER_LOG_DETAILS = "USER_LOG_DETAILS";
	//public static String LOG_FEATURE_STATUS_USER_LOG_DETAILS_GO = "USER_LOG_DETAILS_GO";
	public static String LOG_FEATURE_STATUS_DONE = "DONE";

	public static boolean initForgotPassword(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_INITIAL);
			objSession = objRequest.getSession();

			// Change Session Salt
			String randomSessionSalt = SecureHashAlgorithm.getRandomSalt(HISSSOServerConfig.LOGIN_SESSION_SALT_BYTE_SIZE);
			objSession.setAttribute(HISSSOServerConfig.LOGIN_FEATURES_SESSION_SALT, randomSessionSalt);

			// Clearing Form Data
			objActionSupport.clear();

			// Setting Question List
			List<Entry> lstQuestions = LoginFeaturesDATA.getQuestionList(new UserMasterVO());
			objSession.setAttribute(HISLoginConfig.KEY_QUESTION_LIST, lstQuestions);
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			objActionSupport.addActionError("Unknown Problem Occured!");
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean validateForgotPassword(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = false;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Check Credentials
			// Fetch/Set User Forgot Password Credentials
			String strSessionSalt = (String) objSession.getAttribute(HISSSOServerConfig.LOGIN_FEATURES_SESSION_SALT);
			LoginVO voLogin = new LoginVO();
			BeanUtils.copyProperties(voLogin, objActionSupport);
			voLogin.setVarLoginSessionSalt(strSessionSalt);
			voLogin.setVarIPAddress(objRequest.getRemoteAddr());

			// Change Session Salt
			String randomSessionSalt = SecureHashAlgorithm.getRandomSalt(HISSSOServerConfig.LOGIN_SESSION_SALT_BYTE_SIZE);
			objSession.setAttribute(HISSSOServerConfig.LOGIN_FEATURES_SESSION_SALT, randomSessionSalt);

			// Checking Valid User Detail from DB for entered Hint Answer
			UserMasterVO voUser = LoginFeaturesDATA.getValidForgotDetail(voLogin);

			if (voUser == null || voUser.getVarLoggedIn().equals(HISConfig.NO))
			{
				flg = false;

				// Setting Error Message
				if (voUser != null) objActionSupport.addActionError(voUser.getVarLoginMessage());
				else objActionSupport.addActionError("Invalid Details!");
				objActionSupport.setVarStatus(LOG_FEATURE_STATUS_INITIAL);
				objActionSupport.clear();
			}
			else
			{
				// If Valid user
				flg = true;

				BeanUtils.copyProperties(objActionSupport, voUser);
				objSession.setAttribute(HISLoginConfig.KEY_LOGIN_FEATURES_USER_VO, voUser);

				objActionSupport.setVarStatus(LOG_FEATURE_STATUS_NEW_PASSWORD);
			}
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			// Set Error Message
			objActionSupport.addActionError("Unknown Problem Occured!");
			objActionSupport.clear();
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean saveForgotPassword(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Getting User VO from Session
			UserMasterVO voUser = (UserMasterVO) objSession.getAttribute(HISLoginConfig.KEY_LOGIN_FEATURES_USER_VO);
			UserMasterVO voUserNew = new UserMasterVO();
			BeanUtils.copyProperties(voUserNew, voUser);
			voUserNew.setVarOldPassword(voUserNew.getVarPassword());
			voUserNew.setVarPassword(objActionSupport.getVarPassword());
			voUserNew.setVarSeatId(null);
			//Added by Vasu on 17.May.18
			 if(objActionSupport.getNewPassword().toUpperCase().contains(voUser.getVarUserName().toUpperCase()) || objActionSupport.getNewPassword().toUpperCase().contains("CDAC"))
			{
				   objActionSupport.addActionError("Password must not contain user name or cdac!");
			}
			 else{
			// Save New Password
			LoginFeaturesDATA.changeUserPasswordDetail(voUserNew);
			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_DONE);
			objActionSupport.addActionError("Password Changed Successfully!");
			 }
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			// Set Error Message
			objActionSupport.addActionError("Unknown Problem Occured! Your Password is not Changed!");
			objActionSupport.clear();
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean initFirstLogin(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			// Clearing Form Data
			objActionSupport.clear();

			objSession = objRequest.getSession();
			// Setting First Login Info
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			if (objAuthenticate == null) return false;
			objActionSupport.setVarUserName(objAuthenticate.getVoUser().getVarUserName());
			objActionSupport.setVarUsrName(objAuthenticate.getVoUser().getVarUsrName());

			// Setting Question List
			List<Entry> lstQuestions = LoginFeaturesDATA.getQuestionList(new UserMasterVO());
			objSession.setAttribute(HISLoginConfig.KEY_QUESTION_LIST, lstQuestions);
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			objActionSupport.addActionError("Unknown Problem Occured!");
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean saveFirstLogin(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Getting User VO from Session
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			UserMasterVO voUser = objAuthenticate.getVoUser();
			UserMasterVO voUserNew = new UserMasterVO();
			BeanUtils.copyProperties(voUserNew, objActionSupport);
			voUserNew.setVarUserId(voUser.getVarUserId());
			voUserNew.setVarHospitalCode(voUser.getVarHospitalCode());
			voUserNew.setVarOldPassword(voUser.getVarPassword());
			voUserNew.setVarSeatId(voUser.getVarSeatId());

			// Save New Password
			LoginFeaturesDATA.changeUserDetail(voUserNew);

			// Setting User Hint Question & Password Detail
			voUser.setVarQuestionId(objActionSupport.getVarQuestionId());
			voUser.setVarHintAnswer(objActionSupport.getVarHintAnswer());
			voUser.setVarOldPassword(voUser.getVarPassword());
			voUser.setVarPassword(objActionSupport.getVarPassword());

			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_DONE);
			objActionSupport.addActionError("User Detail Changed Successfully!");
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			// Set Error Message
			objActionSupport.addActionError("Unknown Problem Occured! Your Password is not Changed!");
			objActionSupport.clear();
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean initChangePassword(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			// Clearing Form Data
			objActionSupport.clear();

			objSession = objRequest.getSession();
			// Setting First Login Info
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			if (objAuthenticate == null) return false;
			objActionSupport.setVarUserName(objAuthenticate.getVoUser().getVarUserName());
		    String strSecureKey = SecureCryptAlgorithm.generateKeyAES();
		    objSession.setAttribute(HISSSOConfig.LOGGEDIN_USER_SECRET_KEY, strSecureKey);
		    System.out.println("key="+strSecureKey);
		    System.out.println("aes="+SecureCryptAlgorithm.encryptAES("adm123", strSecureKey));
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			objActionSupport.addActionError("Unknown Problem Occured!");
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean saveChangePassword(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();
			String parm = objActionSupport.getCaptchaResponse();
		    String c= (String)objSession.getAttribute("CAPTCHA_KEY") ;
		    
		    String sceureKey = (String) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_SECRET_KEY);
			// Getting User VO from Session
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			UserMasterVO voUser = objAuthenticate.getVoUser();

			if (!voUser.getVarPassword().equals(objActionSupport.getVarOldPassword()))
			{
				// objActionSupport.clear();
				objActionSupport.addActionError("Old Password is Wrong!");
			}
			else if(parm!=null && !parm.equals(c) )
		    {
		      objActionSupport.addActionError("Invalid Captcha! Please try again!");
		    }
			//Server side validation for HEX HashCode by sandip naik on 27 April,2017
			else if(objActionSupport.getVarPassword()==null || objActionSupport.getVarPassword().isEmpty() || !(objActionSupport.getVarPassword().matches("[A-Fa-f0-9]+") && objActionSupport.getVarPassword().length()==40))
			{
				   objActionSupport.addActionError("Wrong Password! Please try again!"); 	
			}
			else if(objActionSupport.getVarConfirmPassword()==null || !objActionSupport.getVarConfirmPassword().equals(objActionSupport.getVarPassword()))
			{
				   objActionSupport.addActionError("Wrong Password! Please try again!");  	
			}//End Sandip 27.04.2017
			
			//Added by Vasu on 17.May.18
			else if(objActionSupport.getNewPassword().toUpperCase().contains(voUser.getVarUserName().toUpperCase()) || objActionSupport.getNewPassword().toUpperCase().contains("CDAC"))
			{
				   objActionSupport.addActionError("Password must not contain user name or cdac!");
			}
			//End Vasu
			else // Decrypt Secure password and validate for Strongness By Neha 2017.05.09
			{
				UserMasterVO voUserNew = new UserMasterVO();
				BeanUtils.copyProperties(voUserNew, objActionSupport);
				//voUserNew.setVarUserId(voUser.getVarUserSeatId());
				voUserNew.setVarUserId(voUser.getVarUserId());
				voUserNew.setVarHospitalCode(voUser.getVarHospitalCode());
				voUserNew.setVarSeatId(voUser.getVarUserId());
				voUserNew.setVarUserSeatId(voUser.getVarUserSeatId());
				//By Sandip on 27.04.2017
				/*// Save New Password
					LoginFeaturesDATA.changeUserPasswordDetail(voUserNew);
					// Setting User Password Detail
					voUser.setVarOldPassword(voUser.getVarPassword());
					voUser.setVarPassword(objActionSupport.getVarPassword());
	
					objActionSupport.setVarStatus(LOG_FEATURE_STATUS_DONE);
					objActionSupport.addActionError("User Password Changed Successfully!");*/
				
				if (objActionSupport.getVarPassword().equals(objActionSupport.getVarConfirmPassword()))
				{
					// Save New Password
					//String encrypt = SecureCryptAlgorithm.encrypt(objActionSupport.getVarPassword(), sceureKey);
					String decrypt = SecureCryptAlgorithm.decryptAES(objActionSupport.getVarSK(), sceureKey);
					System.out.println("decrypted message" + decrypt);

					if(checkPassStrength(decrypt))
					{
						LoginFeaturesDATA.changeUserPasswordDetail(voUserNew);
						// Setting User Password Detail
						voUser.setVarOldPassword(voUser.getVarPassword());
						voUser.setVarPassword(objActionSupport.getVarPassword());
		
						objActionSupport.setVarStatus(LOG_FEATURE_STATUS_DONE);
						objActionSupport.addActionError("User Password Changed Successfully!");
					}
					else
					{
						objActionSupport.addActionError("Wrong Password! Please try again!");
					}//End Sandip on 27.04.2017
				}
				else
				{
					objActionSupport.addActionError("Wrong Password! Please try again!");
				}//End Sandip on 27.04.2017
			}
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			// Set Error Message
			objActionSupport.addActionError("Unknown Problem Occured! Your Password is not Changed!");
			//objActionSupport.clear();
			initChangePassword(objActionSupport,objRequest,objResponse);
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean initChangeUserDetails(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Clearing Form Data
			// objActionSupport.clear();
			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_INITIAL);

			// Setting First Login Info
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			if (objAuthenticate != null)
			{
				objActionSupport.setVarUserName(objAuthenticate.getVoUser().getVarUserName());
			}
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			objActionSupport.addActionError("Unknown Problem Occured!");
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean validatePassword(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Getting User VO from Session
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			UserMasterVO voUser = objAuthenticate.getVoUser();

			if (!voUser.getVarPassword().equals(objActionSupport.getVarPassword()))
			{
				objActionSupport.setVarPassword("");
				objActionSupport.setVarStatus(LOG_FEATURE_STATUS_INITIAL);
				objActionSupport.addActionError("Password is Wrong!");
			}
			else
			{
				// If Valid user
				flg = true;

				// populate user detail in actionform
				BeanUtils.copyProperties(objActionSupport, voUser);
				objActionSupport.setVarOldHintAnswer(objAuthenticate.getVoUser().getVarHintAnswer());
				objSession.setAttribute(HISLoginConfig.KEY_LOGIN_FEATURES_USER_VO, voUser);

				List<Entry> lstQuestions = LoginFeaturesDATA.getQuestionList(new UserMasterVO());
				objSession.setAttribute(HISLoginConfig.KEY_QUESTION_LIST, lstQuestions);

				// Setting First Login Info
				AuthorizationCredentials objAuthorization = (AuthorizationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHORIZATION_OBJECT);
				if (objAuthorization != null)
				{
					List<Entry> lstDefMenus = new ArrayList<Entry>();

					HashSet<String> lstModules = new HashSet<String>();
					List<Entry> lstModuleNames = new ArrayList<Entry>();

					List<MenuMasterVO> lstMenus = objAuthorization.getMenuList();
					if(lstMenus!=null)
					{
					for (MenuMasterVO v : lstMenus)
					{
						Entry objEnt = new Entry();
						objEnt.setLabel(v.getVarModuleName() + "->" + v.getVarMenuName());
						objEnt.setValue(v.getVarMenuId());
						lstDefMenus.add(objEnt);

						String str = new String();
						str = v.getVarModuleName();
						lstModules.add(str);
					}
					for (String str : lstModules)
					{
						Entry objEn = new Entry();
						objEn.setLabel(str);
						objEn.setValue(str);
						lstModuleNames.add(objEn);
					}
					}
					// getting and setting fav menu
					List<MenuMasterVO> lstFavourite = (List<MenuMasterVO>) objSession.getAttribute(HISLoginConfig.LOGGEDIN_USER_FAVAOURITE_LIST);
					List<Entry> lstFavourites = new ArrayList<Entry>();
					if (lstFavourite != null)
					{
						for (MenuMasterVO v : lstFavourite)
						{
							Entry objEnt = new Entry();
							objEnt.setLabel(v.getVarMenuName());
							objEnt.setValue(v.getVarMenuId());
							lstFavourites.add(objEnt);
						}
						System.out.println(objActionSupport.getVarUserId());
					}
					objSession.setAttribute(HISLoginConfig.KEY_MENU_LIST, lstDefMenus);
					objSession.setAttribute(HISLoginConfig.KEY_MODULE_LIST, lstModuleNames);
					objSession.setAttribute(HISLoginConfig.KEY_MODULE_MENU_LIST, new ArrayList<Entry>());
					objSession.setAttribute(HISLoginConfig.KEY_FAVOURITE_LIST, lstFavourites);
				}
				objActionSupport.setVarStatus(LOG_FEATURE_STATUS_CHANGE_USER_DETAILS);
			}
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			// Set Error Message
			objActionSupport.addActionError("Unknown Problem Occured!");
			objActionSupport.clear();
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean saveChangeUserDetails(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Getting User VO from Session
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			UserMasterVO voUser = objAuthenticate.getVoUser();

			UserMasterVO voUserFinal = new UserMasterVO();
			BeanUtils.copyProperties(voUserFinal, voUser);
			voUserFinal.setVarQuestionId(objActionSupport.getVarQuestionId());
			voUserFinal.setVarHintAnswer(objActionSupport.getVarHintAnswer());
			voUserFinal.setVarMobileNumber(objActionSupport.getVarMobileNumber());
			voUserFinal.setVarEmailId(objActionSupport.getVarEmailId());
			voUserFinal.setVarMenuId(objActionSupport.getVarMenuId());

			String strFavMenu[] = objActionSupport.getVarFavMenuId();
			List<MenuMasterVO> lstNewFav = new ArrayList<MenuMasterVO>();
			for (String menuId : strFavMenu)
			{
				int i = 0;
				MenuMasterVO voMenu = new MenuMasterVO();
				voMenu.setVarUserId(voUserFinal.getVarUserId());
				voMenu.setVarHospitalCode(voUser.getVarHospitalCode());
				voMenu.setVarDisplayOrder(Integer.toString(i));
				voMenu.setVarSeatId(voUserFinal.getVarSeatId());
				voMenu.setVarMenuId(menuId);
				lstNewFav.add(voMenu);
				i++;
			}

			System.out.println("fav list......");
			for (int i = 0; i < strFavMenu.length; i++)
				System.out.println(strFavMenu[i]);

			// Save New Password
			LoginFeaturesDATA.changeLoginUserDetails(voUserFinal, lstNewFav);

			// Setting User Password Detail
			// objActionSupport.setVarOldPassword(objActionSupport.getVarPassword());
			// voUser.setVarUserName(objActionSupport.getVarUserName());
			voUser.setVarQuestionId(objActionSupport.getVarQuestionId());
			voUser.setVarHintAnswer(objActionSupport.getVarHintAnswer());
			voUser.setVarMobileNumber(objActionSupport.getVarMobileNumber());
			voUser.setVarEmailId(objActionSupport.getVarEmailId());
			voUser.setVarMenuId(objActionSupport.getVarMenuId());
			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_INITIAL);
			objActionSupport.addActionError("User Login details Changed Successfully! Re-login to see changes.");

		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			// Set Error Message
			objActionSupport.addActionError("Unknown Problem Occured! Your User Login details are not Changed!" + e.getMessage());
			objActionSupport.clear();
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static void getMenuList(HttpServletRequest objRequest, HttpServletResponse objResponse)
	{

		System.out.println("LoginFeaturesUTL :: getMenuList");
		StringBuilder strResponse = new StringBuilder();
		List<Entry> lstModuleMenu = new ArrayList();
		try
		{
			String strModuleId = (String) objRequest.getParameter("moduleId");

			HttpSession objSession = objRequest.getSession();
			AuthorizationCredentials objAuthorization = (AuthorizationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHORIZATION_OBJECT);
			if (objAuthorization != null)
			{
				strResponse.append("{");
				List<MenuMasterVO> lstMenus = objAuthorization.getMenuList();
				int i = 0;
				if (strModuleId != null && !strModuleId.equals(""))
				{
					for (MenuMasterVO v : lstMenus)
					{
						if (v.getVarModuleName().equals(strModuleId))
						{
							if (i == 0) strResponse.append("\"" + v.getVarMenuId() + "\":\"" + v.getVarMenuName().trim() + "\"");
							else strResponse.append(",\"" + v.getVarMenuId() + "\":\"" + v.getVarMenuName() + "\"");
							i++;
							lstModuleMenu.add(new Entry(v.getVarMenuName(), v.getVarMenuId()));
						}
					}
				}
				objSession.setAttribute(HISLoginConfig.KEY_MODULE_MENU_LIST, lstModuleMenu);

				strResponse.append("}");
				System.out.println("strResponse :" + strResponse);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			writeResponse(objResponse, strResponse.toString());
		}

	}

	public static void writeResponse(HttpServletResponse resp, String output)
	{
		try
		{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			// System.out.println(output);
			resp.getWriter().write(output);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static boolean inituserLogDetails(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse,String frDate,String toDate)
	{
		int num = 10;
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();
			// Getting User VO from Session
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			UserMasterVO voUser = objAuthenticate.getVoUser();
			// If Valid user
			flg = true;
			objSession.setAttribute(HISLoginConfig.KEY_LOGIN_FEATURES_USER_VO, voUser);
			List<UserLoginLogVO> LoginList = LoginFeaturesDATA.getLogList(voUser, num,null,null);
			objSession.setAttribute(HISLoginConfig.KEY_LOGIN_LOG, LoginList);
			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_INITIAL);
		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			objActionSupport.addActionError("Unknown Problem Occured!" + e.getMessage());
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}

	public static boolean allUserLogDetails(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse,String frDate,String toDate)
	{
		int num = 11;
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Getting User VO from Session
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			UserMasterVO voUser = objAuthenticate.getVoUser();
			Date objSysDate = (Date) objSession.getAttribute(HISSSOConfig.LOGGEDIN_SYSDATE_OBJECT);
			//String strDate = (String) objSession.getAttribute(HISSSOConfig.LOGGEDIN_SYSDATE_STRING);
			if(objSysDate!=null)
				//strDate = DateHelperMethods.getDateString(objSysDate,"dd-MM-yyyy"); // in format dd-MM-yyyy HH:mm
			frDate = DateHelperMethods.getDateString(objSysDate,"dd-MMM-yyyy"); // in format dd-MM-yyyy HH:mm
			toDate = DateHelperMethods.getDateString(objSysDate,"dd-MMM-yyyy"); // in format dd-MM-yyyy HH:mm
			//System.out.println("in utl allUserLogDetails fdate"+frDate+" "+toDate);
			// If Valid user
			flg = true;

			objSession.setAttribute(HISLoginConfig.KEY_LOGIN_FEATURES_USER_VO, voUser);
			List<UserLoginLogVO> LoginList = LoginFeaturesDATA.getLogList(voUser, num,frDate,toDate);
			objSession.setAttribute(HISLoginConfig.KEY_LOGIN_LOG, LoginList);
			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_USER_LOG_DETAILS);

		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			objActionSupport.addActionError("Unknown Problem Occured!" + e.getMessage());
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}
	public static boolean datewiseUserLogDetails(LoginFeatureSUP objActionSupport, HttpServletRequest objRequest, HttpServletResponse objResponse,String frDate,String toDate)
	{
		int num = 11;
		boolean flg = true;
		HttpSession objSession = null;
		try
		{
			objSession = objRequest.getSession();

			// Getting User VO from Session
			AuthenticationCredentials objAuthenticate = (AuthenticationCredentials) objSession.getAttribute(HISSSOConfig.LOGGEDIN_USER_AUTHENTICATION_OBJECT);
			UserMasterVO voUser = objAuthenticate.getVoUser();

			// If Valid user
			flg = true;

			objSession.setAttribute(HISLoginConfig.KEY_LOGIN_FEATURES_USER_VO, voUser);
			List<UserLoginLogVO> LoginList = LoginFeaturesDATA.getLogList(voUser, num,frDate,toDate);
			objSession.setAttribute(HISLoginConfig.KEY_LOGIN_LOG, LoginList);
			objActionSupport.setVarStatus(LOG_FEATURE_STATUS_USER_LOG_DETAILS);
			//System.out.println("in utl fdate"+frDate+" "+toDate);

		}
		catch (Exception e)
		{
			flg = false;
			// Log or Populate Error Here
			objActionSupport.addActionError("Unknown Problem Occured!" + e.getMessage());
			e.printStackTrace();// Hide This for Production
		}
		return flg;
	}
	//By Vasu On 26.03.18
	/*public static void setCaptcha(LoginFeatureSUP objActionSupport,HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException, IOException
	{
		int width = 160;
		int height = 50;
		try{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	    Graphics2D graphics2D = image.createGraphics();
	    Random r = new Random();
	    String token = Long.toString(Math.abs(r.nextLong()), 36);
	    String ch = token.substring(0,6);
	    Color c = new Color(0.6662f, 0.4569f, 0.3232f);
	    
	    graphics2D.setColor(Color.decode("#002820"));
	    graphics2D.fillRect(0, 0, image.getWidth(), image.getHeight());
	   
	    //Color c = new Color(0,0,255);
	    GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
	    graphics2D.setPaint(gp);
	    Font font=new Font("Verdana", Font.CENTER_BASELINE , 26);
	    graphics2D.setFont(font);
	    graphics2D.drawString(ch,26,33);
	    graphics2D.dispose();
	    
	    HttpSession session = objRequest.getSession(true);
	    session.setAttribute("CAPTCHA_KEY",ch);

	    OutputStream outputStream = objResponse.getOutputStream();
	    ImageIO.write(image, "jpeg", outputStream);
	    outputStream.close();
		}
		catch(Exception ee)
		{
			objActionSupport.addActionError("Unknown Problem Occured While Trying to fetch Captcha!");
		}
	}*/
	public static void setCaptcha(LoginFeatureSUP objActionSupport,HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException, IOException
	{
		int width = 160;
		int height = 50;
		try{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	    Graphics2D graphics2D = image.createGraphics();
        //Added by Vasu on 22.March.2018 for numeric captcha generation
	    int a = (new Double(Math.floor(Math.random()*100))).intValue();
        int b = (new Double(Math.floor(Math.random()*10))).intValue();
        if(a<=9)
        {
        	a=a+10;
        }
        while(b==0)
        {
        	b = (new Double(Math.floor(Math.random()*10))).intValue();
        }
        int chVal = a+b;
        String ch = new String(a +" + "+ b + " = ");
	    
        /*Random r = new Random();
	    String token = Long.toString(Math.abs(r.nextLong()), 36);
	    String ch = token.substring(0,6);*/
	    
	    
	    Color c = new Color(0.6662f, 0.4569f, 0.3232f);
	    
	    graphics2D.setColor(Color.decode("#002820"));
	    graphics2D.fillRect(0, 0, image.getWidth(), image.getHeight());
	   
	    //Color c = new Color(0,0,255);
	    GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
	    graphics2D.setPaint(gp);
	    Font font=new Font("Verdana", Font.CENTER_BASELINE , 26);
	    graphics2D.setFont(font);
	    graphics2D.drawString(ch,26,33);
	    graphics2D.dispose();
	    
	    HttpSession session = objRequest.getSession(true);
	    session.setAttribute("CAPTCHA_KEY", Integer.toString(chVal));

	    OutputStream outputStream = objResponse.getOutputStream();
	    objResponse.setContentType("image/*"); 
	    ImageIO.write(image, "jpeg", outputStream);
	    outputStream.close();
		}
		catch(Exception ee)
		{
			objActionSupport.addActionError("Unknown Problem Occured While Trying to fetch Captcha!");
		}
	}//End By Vasu on 26.03.2018
	
	public static boolean checkPassStrength(String strPassword)
	{
		//boolean hasFlag = false;
		String strength = HISLoginConfig.PASSWORD_STRENGTH;
		
		String regex = "[0-9a-zA-Z.&_!~`@#$%^&*:<>?]+";
	    boolean isAllPresent =strPassword.matches(regex);
		regex = ".*[A-Za-z].*";
    	boolean isAlphaPresent = strPassword.matches(regex);
		regex = ".*[0-9].*";
    	boolean isNumPresent = strPassword.matches(regex);
		regex = ".*[.&_!~`@#$%^&*:<>?].*";
    	boolean isSpecialPresent =strPassword.matches(regex);

    	
    	if(!isAllPresent)	return false;

		if(strength.equals(HISLoginConfig.PASSWORD_STRENGTH_NO))
			return true;

		if(strength.equals(HISLoginConfig.PASSWORD_STRENGTH_LOW) && !isAlphaPresent)
			return false;
		
		if(strength.equals(HISLoginConfig.PASSWORD_STRENGTH_MEDIUM) && !(isAlphaPresent && isNumPresent))		
			return false;	
		
		if(strength.equals(HISLoginConfig.PASSWORD_STRENGTH_HIGH) && !(isAlphaPresent && isNumPresent && isSpecialPresent))
			return false;
		
		return true;
	}
}
