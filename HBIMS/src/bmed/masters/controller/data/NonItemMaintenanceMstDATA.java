package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.hisconfig.Config;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.global.controller.util.BmedConfigUtil;
import bmed.masters.bo.NonItemMaintenanceMstBO;
import bmed.masters.controller.fb.NonItemMaintenanceMstFB;
import bmed.masters.vo.NonItemMaintenanceMstVO;

public class NonItemMaintenanceMstDATA {

	public static void initializeAdd(HttpServletRequest request,
			NonItemMaintenanceMstFB formBean) {

		HisUtil util = null;
		NonItemMaintenanceMstBO bo = null;
		NonItemMaintenanceMstVO vo = null;

		// String strBuildingCodeOptions = null;
		String strDeptIdOptions = null;
		// String strMaintenanceIdOptions = null;
		String strMaintenancePeriodUnitIdOptions = null;
		String strNonItemOptions = null;
		String strMaintenanceNameOptions = null;

		try {

			util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");
			vo = new NonItemMaintenanceMstVO();
			bo = new NonItemMaintenanceMstBO();
			String      arrStrCombo[] = request.getParameterValues("combo");
			
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			if (formBean.getStrNonItemId() == null) {
				formBean.setStrNonItemId("0"); // No Non Item Selected
			}

			TransferObjectFactory.populateData(vo, formBean);

			if (formBean.getCombo() != null) {
				vo.setStrEngineeringItemTypeId(arrStrCombo[0]);
				vo.setStrEngineeringItemSubTypeId(arrStrCombo[1]);
			}
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.initializeAdd(vo);

			TransferObjectFactory.populateData(formBean, vo);

			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			//for item name combo options
			strNonItemOptions = util.getOptionValue(vo.getWrsNonItemOptions(),
					"0", "0^Select Value", false);
		
			//for department name combo options
			strDeptIdOptions = util.getOptionValue(vo.getWrsDeptIdOptions(),
					"0", "0^Select Value", false);
			
			// for maintenance name combo options
			/*strMaintenanceNameOptions =util.getOptionValue(
					vo.getWrsMaintenanceIdOptions(), "0",
					"0^Select Value", false);*/
		
			// for unit combo options
			strMaintenancePeriodUnitIdOptions = util.getOptionValue(
					vo.getWrsMaintenancePeriodUnitIdOptions(), "0",
					"0^Select Value", false);
			
			
			/* Getting different combo options in form bean */
			
			formBean.setStrNonItemOptions(strNonItemOptions);
			
			formBean.setStrDeptIdOptions(strDeptIdOptions);
			//formBean.setStrMaintenanceIdOptions(strMaintenanceNameOptions);
			formBean.setStrMaintenancePeriodUnitIdOptions(strMaintenancePeriodUnitIdOptions);
			

		} catch (Exception _e) {
			
			_e.printStackTrace();
			HisException eObj = new HisException("BMED",
					"NonItemMaintenanceMstDATA->initializeAdd()",
					_e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;
		}

	}

	// public static void getBlockCodeOptions(HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// HisUtil util = null;
	//
	// NonItemMaintenanceMstBO bo = null;
	// NonItemMaintenanceMstVO vo = null;
	//
	// //String strBuildingCode = null;
	// String strHospitalCode = null;
	// String strBlockCodeOptions = null;
	//
	// String strResponseString = null;
	// StringBuffer sbResponseStringBuffer = null;
	//
	// try {
	//
	// util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");
	//
	// vo = new NonItemMaintenanceMstVO();
	// bo = new NonItemMaintenanceMstBO();
	// sbResponseStringBuffer = new StringBuffer();
	//
	// //strBuildingCode = request.getParameter("strBuildingCode");
	// strHospitalCode = request.getSession()
	// .getAttribute("HOSPITAL_CODE").toString();
	//
	// vo.setStrHospitalCode(strHospitalCode);
	// // vo.setStrBuildingCode(strBuildingCode);
	//
	// bo.setBlockCodeOptions(vo);
	//
	// strBlockCodeOptions = util.getOptionValue(
	// vo.getWrsBlockCodeOptions(), "0", "0^All", false);
	//
	// sbResponseStringBuffer.append("SUCCESS");
	// sbResponseStringBuffer.append("####");
	// sbResponseStringBuffer.append(strBlockCodeOptions);
	//
	// } catch (Exception _e) {
	//
	// HisException eObj = new HisException("BMED",
	// "NonItemMaintenanceMstDATA->getBlockCodeOptions() -->",
	// _e.getMessage());
	//
	// sbResponseStringBuffer.append("ERROR");
	// sbResponseStringBuffer.append("####");
	// sbResponseStringBuffer.append("Application Error [ERROR ID : ");
	// sbResponseStringBuffer.append(eObj.getErrorID());
	// sbResponseStringBuffer.append("], Contact System Administrator! ");
	//
	// eObj = null;
	// } finally {
	//
	// strResponseString = sbResponseStringBuffer.toString();
	// try {
	// response.getWriter().write(strResponseString);
	// } catch (IOException _e) {
	//
	// new HisException("BMED",
	// "NonItemMaintenanceMstDATA->getBlockCodeOptions()",
	// _e.getMessage());
	// }
	// }
	//
	// }

	// public static void getFloorIdOptions(HttpServletRequest request,
	// HttpServletResponse response) {
	// HisUtil util = null;
	//
	// NonItemMaintenanceMstBO bo = null;
	// NonItemMaintenanceMstVO vo = null;
	//
	// //String strBuildingCode = null;
	// //String strBlockCode = null;
	// String strHospitalCode = null;
	// String strFloorIdOptions = null;
	//
	// String strResponseString = null;
	// StringBuffer sbResponseStringBuffer = null;
	//
	// try {
	//
	// util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");
	//
	// vo = new NonItemMaintenanceMstVO();
	// bo = new NonItemMaintenanceMstBO();
	// sbResponseStringBuffer = new StringBuffer();
	//
	// //strBuildingCode = request.getParameter("strBuildingCode");
	// //strBlockCode = request.getParameter("strBlockCode");
	//
	// strHospitalCode = request.getSession()
	// .getAttribute("HOSPITAL_CODE").toString();
	//
	// vo.setStrHospitalCode(strHospitalCode);
	// // vo.setStrBuildingCode(strBuildingCode);
	// // vo.setStrBlockCode(strBlockCode);
	//
	// bo.setFloorIdOptions(vo);
	//
	// strFloorIdOptions = util.getOptionValue(vo.getWrsFloorIdOptions(),
	// "0", "0^All", false);
	//
	// sbResponseStringBuffer.append("SUCCESS");
	// sbResponseStringBuffer.append("####");
	// sbResponseStringBuffer.append(strFloorIdOptions);
	//
	// } catch (Exception _e) {
	//
	// HisException eObj = new HisException("BMED",
	// "NonItemMaintenanceMstDATA->getBlockCodeOptions() -->",
	// _e.getMessage());
	//
	// sbResponseStringBuffer.append("ERROR");
	// sbResponseStringBuffer.append("####");
	// sbResponseStringBuffer.append("Application Error [ERROR ID : ");
	// sbResponseStringBuffer.append(eObj.getErrorID());
	// sbResponseStringBuffer.append("], Contact System Administrator! ");
	//
	// eObj = null;
	// } finally {
	//
	// strResponseString = sbResponseStringBuffer.toString();
	// try {
	// response.getWriter().write(strResponseString);
	// } catch (IOException e) {
	//
	// new HisException("BMED",
	// "NonItemMaintenanceMstDATA->getFloorIdOptions()",
	// e.getMessage());
	// }
	// }
	//
	// }

	// public static void getRoomIdOptions(HttpServletRequest request,
	// HttpServletResponse response) {
	// HisUtil util = null;
	//
	// NonItemMaintenanceMstBO bo = null;
	// NonItemMaintenanceMstVO vo = null;
	//
	// //String strBuildingCode = null;
	// //String strBlockCode = null;
	// //String strFloorId = null;
	// String strHospitalCode = null;
	// String strRoomIdOptions = null;
	//
	// String strResponseString = null;
	// StringBuffer sbResponseStringBuffer = null;
	//
	// try {
	//
	// util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");
	//
	// vo = new NonItemMaintenanceMstVO();
	// bo = new NonItemMaintenanceMstBO();
	// sbResponseStringBuffer = new StringBuffer();
	//
	// //strBuildingCode = request.getParameter("strBuildingCode");
	// //strBlockCode = request.getParameter("strBlockCode");
	// //strFloorId = request.getParameter("strFloorId");
	//
	// strHospitalCode = request.getSession()
	// .getAttribute("HOSPITAL_CODE").toString();
	//
	// vo.setStrHospitalCode(strHospitalCode);
	// // vo.setStrBlockCode(strBlockCode);
	// // vo.setStrBuildingCode(strBuildingCode);
	// // vo.setStrFloorId(strFloorId);
	//
	// bo.setRoomIdOptions(vo);
	//
	// strRoomIdOptions = util.getOptionValue(vo.getWrsRoomIdOptions(),
	// "0", "0^All", false);
	//
	// sbResponseStringBuffer.append("SUCCESS");
	// sbResponseStringBuffer.append("####");
	// sbResponseStringBuffer.append(strRoomIdOptions);
	//
	// } catch (Exception _e) {
	//
	// HisException eObj = new HisException("BMED",
	// "NonItemMaintenanceMstDATA->getRoomIdOptions() -->",
	// _e.getMessage());
	//
	// sbResponseStringBuffer.append("ERROR");
	// sbResponseStringBuffer.append("####");
	// sbResponseStringBuffer.append("Application Error [ERROR ID : ");
	// sbResponseStringBuffer.append(eObj.getErrorID());
	// sbResponseStringBuffer.append("], Contact System Administrator! ");
	//
	// eObj = null;
	// } finally {
	//
	// strResponseString = sbResponseStringBuffer.toString();
	// try {
	// response.getWriter().write(strResponseString);
	// } catch (IOException e) {
	//
	// new HisException("BMED",
	// "NonItemMaintenanceMstDATA->getRoomIdOptions()",
	// e.getMessage());
	// }
	// }
	//
	// }

	public static void getTaskOptions(HttpServletRequest request,
			HttpServletResponse response) {
		HisUtil util = null;

		NonItemMaintenanceMstBO bo = null;
		NonItemMaintenanceMstVO vo = null;

		String strEngineeringItemTypeId = null;
		String strEngineeringItemSubTypeId = null;
		String strMaintenanceId = null;
		String strHospitalCode,strDeptId,strNonItenId = null;
		// String strBuildingCode = null;
		// String strBlockCode = null;
		// String strFloorId = null;
		// String strRoomId = null;

		String strLeftTaskIdOptions = null;
		String strRightTaskIdOptions = null;

		String strResponseString = null;
		StringBuffer sbResponseStringBuffer = null;
		NonItemMaintenanceMstFB formBean =null;

		try {

			util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");

			vo = new NonItemMaintenanceMstVO();
			bo = new NonItemMaintenanceMstBO();
			sbResponseStringBuffer = new StringBuffer();
			formBean = new NonItemMaintenanceMstFB();

			strEngineeringItemTypeId = request
					.getParameter("strEngineeringItemTypeId");
			strEngineeringItemSubTypeId = request
					.getParameter("strEngineeringItemSubTypeId");
			strMaintenanceId = request.getParameter("strMaintenanceId");

			// strBuildingCode = request.getParameter("strBuildingCode");
			// strBlockCode = request.getParameter("strBlockCode");
			// strFloorId = request.getParameter("strFloorId");
			// strRoomId = request.getParameter("strRoomId");

			strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			strDeptId=request
			.getParameter("strDeptId");
			strNonItenId=request.getParameter("strNonItemId"); 

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrEngineeringItemTypeId(strEngineeringItemTypeId);
			vo.setStrEngineeringItemSubTypeId(strEngineeringItemSubTypeId);
			vo.setStrMaintenanceId(strMaintenanceId);
			vo.setStrDeptId(strDeptId);
			vo.setStrNonItemId(strNonItenId);
			// vo.setStrBuildingCode(strBuildingCode);
			// vo.setStrBlockCode(strBlockCode);
			// vo.setStrFloorId(strFloorId);
			// vo.setStrRoomId(strRoomId);

			bo.setTaskOptions(vo);

			strLeftTaskIdOptions = util.getOptionValue(
					vo.getWrsLeftTaskIdOptions(), "0", "", false);
			strRightTaskIdOptions = util.getOptionValue(
					vo.getWrsRightTaskIdOptions(), "0", "", false);

			sbResponseStringBuffer.append("SUCCESS");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append(strLeftTaskIdOptions);
			sbResponseStringBuffer.append("^");
			sbResponseStringBuffer.append(strRightTaskIdOptions);

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"NonItemMaintenanceMstDATA->getBlockCodeOptions() -->",
					_e.getMessage());

			sbResponseStringBuffer.append("ERROR");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append("Application Error [ERROR ID : ");
			sbResponseStringBuffer.append(eObj.getErrorID());
			sbResponseStringBuffer.append("], Contact System Administrator! ");

			eObj = null;
		} finally {

			strResponseString = sbResponseStringBuffer.toString();
			System.out.println("strResponseString--"+strResponseString);
			try {
				response.getWriter().write(strResponseString);
			} catch (IOException e) {

				new HisException("BMED",
						"NonItemMaintenanceMstDATA->getTaskOptions()",
						e.getMessage());
			}
		}

	}

	public static void insert(HttpServletRequest request,
			NonItemMaintenanceMstFB formBean) {
		HisUtil util = null;
		NonItemMaintenanceMstBO bo = null;
		NonItemMaintenanceMstVO vo = null;
		String strMaintenanceContractDetails;
		String[] arrStrMaintenanceContractDetails;

		try {

			util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");
			vo = new NonItemMaintenanceMstVO();
			bo = new NonItemMaintenanceMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			strMaintenanceContractDetails = formBean
					.getStrMaintenanceContractDetails();
			if (strMaintenanceContractDetails != null) {
				arrStrMaintenanceContractDetails = strMaintenanceContractDetails
						.split("\\^");
				formBean.setStrVendorId(arrStrMaintenanceContractDetails[0]);
				if ("WARRANTY".equals(arrStrMaintenanceContractDetails[2])) {
					formBean.setStrWarrantySlNo(arrStrMaintenanceContractDetails[1]);
				} else if ("MC"
						.equals(arrStrMaintenanceContractDetails[2])) {

					formBean.setStrMcSlNo(arrStrMaintenanceContractDetails[1]);

				}
			}

			TransferObjectFactory.populateData(vo, formBean);
			bo.insert(vo);
			TransferObjectFactory.populateData(formBean, vo);

			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"NonItemMaintenanceMstDATA->insert()", _e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;
		}

	}

	public static void getMaintenanceOptions(HttpServletRequest request,
			HttpServletResponse response) {
		HisUtil util = null;

		NonItemMaintenanceMstBO bo = null;
		NonItemMaintenanceMstVO vo = null;
		NonItemMaintenanceMstFB formBean =null;

		// String strBuildingCode = null;
		// String strBlockCode = null;
		// String strFloorId = null;
		// String strRoomId = null;

		String strDeptId = null;
		String strNonItemId = null;
		String strEngineeringItemTypeId = null;
		String strEngineeringItemSubTypeId = null;

		String strHospitalCode = null;
		String strMaintenanceIdOptions = null;

		String strResponseString = null;
		StringBuffer sbResponseStringBuffer = null;

		try {

			util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");

			vo = new NonItemMaintenanceMstVO();
			bo = new NonItemMaintenanceMstBO();
			formBean = new NonItemMaintenanceMstFB();

			sbResponseStringBuffer = new StringBuffer();

			strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();

			// strBuildingCode = request.getParameter("strBuildingCode");
			// strBlockCode = request.getParameter("strBlockCode");
			// strFloorId = request.getParameter("strFloorId");
			// strRoomId = request.getParameter("strRoomId");

			strDeptId = request.getParameter("strDeptId");
			strNonItemId = request.getParameter("strNonItemId");
			strEngineeringItemTypeId = request
					.getParameter("strEngineeringItemTypeId");
			strEngineeringItemSubTypeId = request
					.getParameter("strEngineeringItemSubTypeId");

			vo.setStrHospitalCode(strHospitalCode);
			// vo.setStrBuildingCode(strBuildingCode);
			// vo.setStrBlockCode(strBlockCode);
			// vo.setStrFloorId(strFloorId);
			// vo.setStrRoomId(strRoomId);
			vo.setStrDeptId(strDeptId);
			vo.setStrNonItemId(strNonItemId);
			vo.setStrEngineeringItemTypeId(strEngineeringItemTypeId);
			vo.setStrEngineeringItemSubTypeId(strEngineeringItemSubTypeId);

			bo.setMaintenanceOptions(vo);

			strMaintenanceIdOptions = util.getOptionValue(
					vo.getWrsMaintenanceIdOptions(), "0", "0^Select Value",
					false);
			//formBean.setStrMaintenanceIdOptions(strMaintenanceIdOptions);
			sbResponseStringBuffer.append("SUCCESS");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append(strMaintenanceIdOptions);

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"NonItemMaintenanceMstDATA->getMaintenanceOptions() -->",
					_e.getMessage());

			sbResponseStringBuffer.append("ERROR");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append("Application Error [ERROR ID : ");
			sbResponseStringBuffer.append(eObj.getErrorID());
			sbResponseStringBuffer.append("], Contact System Administrator! ");

			eObj = null;
		} finally {

			strResponseString = sbResponseStringBuffer.toString();
			System.out.println("strResponseString--"+strResponseString);
			try {
				response.getWriter().write(strResponseString);
			} catch (IOException e) {

				new HisException("BMED",
						"NonItemMaintenanceMstDATA->getMaintenanceOptions()",
						e.getMessage());
			}
		}

	}

	public static void initializeModify(HttpServletRequest request,
			NonItemMaintenanceMstFB formBean) {
		HisUtil util = null;
		NonItemMaintenanceMstBO bo = null;
		NonItemMaintenanceMstVO vo = null;

		String strPrimaryKeySet = null;
		String[] arrStrPrimaryKeys = null;
		String[] strCheckValue = null;
		String strMaintenancePeriodUnitIdOptions = null;
		String strDeptIdOptions = null;
		String strLeftTaskIdOptions = null;
		String strRightTaskIdOptions = null;

		try {

			util = new HisUtil("BMED", "NonItemMaintenanceMstDATA");
			vo = new NonItemMaintenanceMstVO();
			bo = new NonItemMaintenanceMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());

			strCheckValue = formBean.getChk();
			if (strCheckValue != null) {
				strPrimaryKeySet = strCheckValue[0].split("\\$")[0];
				arrStrPrimaryKeys = strPrimaryKeySet.split("@");
			}

			/*
			 * if (formBean.getStrBuildingCode() == null) {
			 * formBean.setStrBuildingCode(arrStrPrimaryKeys[0]); // All //
			 * Building } if (formBean.getStrBlockCode() == null) {
			 * formBean.setStrBlockCode(arrStrPrimaryKeys[1]); // All Block } if
			 * (formBean.getStrFloorId() == null) {
			 * formBean.setStrFloorId(arrStrPrimaryKeys[2]); // All Floor } if
			 * (formBean.getStrRoomId() == null) {
			 * formBean.setStrRoomId(arrStrPrimaryKeys[3]); // All Room }
			 */

			if (formBean.getStrDeptId() == null) {
				formBean.setStrDeptId(arrStrPrimaryKeys[0]);
			}

			if (formBean.getStrNonItemId() == null) {
				formBean.setStrNonItemId(arrStrPrimaryKeys[1]);
			}

			if (formBean.getStrMaintenanceId() == null) {
				formBean.setStrMaintenanceId(arrStrPrimaryKeys[2]);
			}

			TransferObjectFactory.populateData(vo, formBean);

			bo.initializeModify(vo);

			TransferObjectFactory.populateData(formBean, vo);

			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			/* Getting different combo options */

			strDeptIdOptions = util.getOptionValue(vo.getWrsDeptIdOptions(),
					vo.getStrDeptId(), "0^Select Value", false);

			strLeftTaskIdOptions = util.getOptionValue(
					vo.getWrsLeftTaskIdOptions(), "0", "", false);
			strRightTaskIdOptions = util.getOptionValue(
					vo.getWrsRightTaskIdOptions(), "0", "", false);

			strMaintenancePeriodUnitIdOptions = util
					.getOptionValue(vo.getWrsMaintenancePeriodUnitIdOptions(),
							vo.getStrMaintenancePeriodUnitId(),
							"0^Select Value", false);

			/* Getting different combo options in form bean */

			formBean.setStrDeptIdOptions(strDeptIdOptions);

			formBean.setStrMaintenancePeriodUnitIdOptions(strMaintenancePeriodUnitIdOptions);

			formBean.setStrLeftTaskIdOptions(strLeftTaskIdOptions);
			formBean.setStrRightTaskIdOptions(strRightTaskIdOptions);

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"NonItemMaintenanceMstDATA->initializeModify()",
					_e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}

	}

	public static boolean update(HttpServletRequest request,
			NonItemMaintenanceMstFB formBean) {

		NonItemMaintenanceMstBO bo = null;
		NonItemMaintenanceMstVO vo = null;
		boolean fModificationStatus = false; // success: true, fail: false.

		try {

			vo = new NonItemMaintenanceMstVO();
			bo = new NonItemMaintenanceMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			TransferObjectFactory.populateData(vo, formBean);
			bo.update(vo);
			TransferObjectFactory.populateData(formBean, vo);
			fModificationStatus = true;

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"NonItemMaintenanceMstDATA->update()", _e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;
		}

		return fModificationStatus;
	}

	public static void initializeView(HttpServletRequest request,
			NonItemMaintenanceMstFB formBean) {

		NonItemMaintenanceMstBO bo = null;
		NonItemMaintenanceMstVO vo = null;

		String strPrimaryKeySet = null;
		String[] arrStrPrimaryKeys = null;
		String[] strCheckValue = null;

		try {

			vo = new NonItemMaintenanceMstVO();
			bo = new NonItemMaintenanceMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());

			strCheckValue = formBean.getChk();
			if (strCheckValue != null) {
				strPrimaryKeySet = strCheckValue[0].split("$")[0];
				arrStrPrimaryKeys = strPrimaryKeySet.split("@");
			}

			// formBean.setStrBuildingCode(arrStrPrimaryKeys[0]);
			//
			// formBean.setStrBlockCode(arrStrPrimaryKeys[1]);
			//
			// formBean.setStrFloorId(arrStrPrimaryKeys[2]);
			//
			// formBean.setStrRoomId(arrStrPrimaryKeys[3]);
			// formBean.setStrMaintenanceId(arrStrPrimaryKeys[4]); // All Room

			if (formBean.getStrDeptId() == null) {
				formBean.setStrDeptId(arrStrPrimaryKeys[0]);
			}

			if (formBean.getStrNonItemId() == null) {
				formBean.setStrNonItemId(arrStrPrimaryKeys[1]);
			}

			if (formBean.getStrMaintenanceId() == null) {
				formBean.setStrMaintenanceId(arrStrPrimaryKeys[2]);
			}

			TransferObjectFactory.populateData(vo, formBean);

			bo.initializeView(vo);

			TransferObjectFactory.populateData(formBean, vo);

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"NonItemMaintenanceMstDATA->initializeView(vo) -->",
					_e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}

	}


	
	/**
	 * To get Uploaded File
	 * 
	 * @param nonItemMaintenanceMstFB_p
	 * @param request_p
	 * @param response_p
	 */
	public static void getUploadedFile(NonItemMaintenanceMstFB nonItemMaintenanceMstFB_p,HttpServletRequest request_p, HttpServletResponse response_p)
	{			
				String strmsgText = null;
				String strFileName = "";
				File f = null;
				FileInputStream fis = null;
				byte[] fileContent = new byte[1024];
				BmedConfigUtil bmed =null;
				String strFtpFolderName ="";
				UserVO userVo = null;
				
				try 
				{
					   
					   bmed = new BmedConfigUtil();
					   userVo = ControllerUTIL.getUserVO(request_p);
					   strFtpFolderName = bmed.getStrFtpFileFolder("15", userVo.getHospitalCode());
				       
					  
					   
					   if(strFtpFolderName.equals("")||strFtpFolderName==null)
					   {
						    strFtpFolderName = "adil123";
					   } 
					
					
					strFileName = nonItemMaintenanceMstFB_p.getStrUploadFileId();
								
					//System.out.println("File Name::::::"+ItemWarrantyDtlsFB_p.getStrUploadFileId());
					String[] strTemp = strFileName.replace(".", "#").split("#");
					String strExt = strTemp[strTemp.length - 1];
					
				
					if (strExt.equalsIgnoreCase("pdf")) 
					{
	                    
						response_p.setContentType("application/pdf");
						response_p.setHeader("Content-disposition",	"attachment; filename="+strFtpFolderName+""+strFileName);

					} else if (strExt.equalsIgnoreCase("html")
							|| strExt.equalsIgnoreCase("htm")) {
						
						response_p.setContentType("text/html;charset=utf-8");
						response_p.setHeader("Content-disposition",
								"attachment; filename="+strFtpFolderName+""+strFileName);
						
					}else if (strExt.equalsIgnoreCase("xml")) {
						
						response_p.setContentType("application/xml");
						response_p.setHeader("Content-disposition",
								"attachment; filename="+strFtpFolderName+""+strFileName);
						
					} else if (strExt.equalsIgnoreCase("doc") || strExt.equalsIgnoreCase("docx")) {
						
						response_p.setContentType("application/msword");
						response_p.setHeader("Content-disposition",
								"attachment; filename="+strFtpFolderName+""+strFileName);
						
					} else if (strExt.equalsIgnoreCase("rdf")) {
						
						response_p.setContentType("application/msword");
						response_p.setHeader("Content-disposition",
								"attachment; filename="+strFtpFolderName+""+strFileName);
						
					} else if (strExt.equalsIgnoreCase("rtf")) {
						
						response_p.setContentType("application/msword");
						response_p.setHeader("Content-disposition",
								"attachment; filename="+strFtpFolderName+""+strFileName);
						
					} else {

						response_p.setContentType("text/html;charset=utf-8");
						response_p.setHeader("Content-disposition",
								"attachment; filename="+strFtpFolderName+""+strFileName);
						
					}
					/*******************************************************************/

					  String sessionFtpAddress="10.0.5.152/ftpserver"; //populate from session
					  String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
					  String Fileurl= "ftp://"+sessionFtpAddress+"/"+logicalName+"/"+strFtpFolderName;			
					 
					 
					  URL                  urlftp = new URL(Fileurl+"/"+strFileName);
					  URLConnection          urlc =	urlftp.openConnection();
					  InputStream              io = urlc.getInputStream();
					  		  
					 
					        FileOutputStream fos = new FileOutputStream(strFileName);
					        byte[] buf = new byte[4096];
					        int read = 0;
					        while ((read = io.read(buf)) > 0) 
					        {
					            fos.write(buf, 0, read);
					        }	    				  				  	  
					  
					     f = new File(strFileName);

						if (!f.isFile()) 
						{

							throw new Exception("Invalid File Path");
						}

						fis = new FileInputStream(f);

						while (fis.read(fileContent) != -1) 
						{
			                
							response_p.getOutputStream().write(fileContent);
						}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					strmsgText = "bmed.master.PreTechApprovalTransDATA.getUploadedFile --> "
							+ e.getMessage();
					HisException eObj = new HisException("bmed",
							"PreTechApprovalTransDATA->getUploadedFile()", strmsgText);
					nonItemMaintenanceMstFB_p.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
			
					eObj = null;
				} finally {
			
					if (f != null)
						f = null;
					if (fis != null)
						fis = null;
				}
			}

}
