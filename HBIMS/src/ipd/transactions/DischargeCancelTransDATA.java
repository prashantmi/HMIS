package ipd.transactions;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.IpdBO;
import ipd.IpdConfigUtil;
import ipd.IpdVO;

import java.io.File;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

public class DischargeCancelTransDATA {

	
	private static ResourceBundle confProp = ResourceBundle.getBundle("ipd.ipd_conf");
	public static String discharge_summary_printing_required = confProp.getString("discharge_summary_printing_required");
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void getAdmDetails(HttpServletRequest request, DischargeCancelTransFB formBean) 
	{
		WebRowSet ws = null;
		IpdVO voObj = null;
		
		try
		{
			IpdBO boObj = new IpdBO();
			voObj = new IpdVO();
			
			voObj.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			voObj.setStrValue1(formBean.getStrCrNo());
			voObj.setStrValue2(formBean.getStrAdmnNo());
			boObj.getAdmissionDetails(voObj);
			
			ws = voObj.getGblWs1();
			String str="";
			
			str = DischargeCancelTransHLP.getAdmDetails(voObj);
			formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
			
			
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrErrMsgString());
			}
			formBean.setAdmissionDetailValues(str);
		}
		catch (Exception e) 
		{
			voObj.setStrErrMsgString(e.getMessage());
			voObj.setStrMsgType("1");
			HisException eObj = new HisException("ADT-->Discharge Cancellation","DischargeCancelTransDATA-->getAdmDetails()",voObj.getStrErrMsgString());
			formBean.setStrErrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
			e = null;
		}
		finally
		{
			if (voObj != null)
				voObj = null;
			if (formBean != null)
				formBean = null;
		}
	}
	public static void getAdmDetails_BS(HttpServletRequest request, DischargeCancelTransFB formBean) 
	{
		WebRowSet ws = null;
		IpdVO voObj = null;
		
		try
		{
			IpdBO boObj = new IpdBO();
			voObj = new IpdVO();
			
			voObj.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			voObj.setStrValue1(formBean.getStrCrNo());
			voObj.setStrValue2(formBean.getStrAdmnNo());
			boObj.getAdmissionDetails(voObj);
			
			ws = voObj.getGblWs1();
			String str="";
			
			str = DischargeCancelTransHLP.getAdmDetails_BS(voObj);
			formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
			
			
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrErrMsgString());
			}
			formBean.setAdmissionDetailValues(str);
		}
		catch (Exception e) 
		{
			voObj.setStrErrMsgString(e.getMessage());
			voObj.setStrMsgType("1");
			HisException eObj = new HisException("ADT-->Discharge Cancellation","DischargeCancelTransDATA-->getAdmDetails()",voObj.getStrErrMsgString());
			formBean.setStrErrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
			e = null;
		}
		finally
		{
			if (voObj != null)
				voObj = null;
			if (formBean != null)
				formBean = null;
		}
	}
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void detail(DischargeCancelTransFB formBean) {
		DischargeCancelTransVO vo = null;
		// DischargeCancelTransBO bo= null;
		try {
			vo = new DischargeCancelTransVO();
			// bo = new DischargeCancelTransBO();

			String strPatientAdmndtl = "";

			try {
				strPatientAdmndtl = hisglobal.tools.hlp.PatientDtlHLP
						.patientDtl(formBean.getStrCrNo(), false);
			} catch (Exception e) {

				throw e;

			}

			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			// bo.setPatientDtl(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrErrMsgString());
			}

			formBean.setStrPatientAdmndtl(strPatientAdmndtl);
			/*
			 * formBean.setStrAdmnNo(vo.getStrAdmnNo());
			 * formBean.setStrAdmnDate(vo.getStrAdmnDate());
			 * formBean.setStrWard(vo.getStrWard());
			 * formBean.setStrRoomBed(vo.getStrRoomBed());
			 */

		} catch (Exception e) {

			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA-->detail()", vo
							.getStrErrMsgString());

			if (e.getMessage().startsWith("Invalid")) {

				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			} else {
				formBean.setStrErrMsgString("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator! ");
			}
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}

	public static void getRsnRmk(DischargeCancelTransFB formBean) {
		DischargeCancelTransVO vo = new DischargeCancelTransVO();
		DischargeCancelTransBO bo = new DischargeCancelTransBO();
		try {
			vo.setStrCrNo(formBean.getStrCrNo());
			vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.DischargeCancelTransVO", formBean);
			vo.setStrDeptUnitCode(formBean.getStrDeptUnitCode());
			bo.getRsnRmk(vo);
			formBean.setStrDisBy(vo.getStrDisBy());
			formBean.setStrDisRsn(vo.getStrDisRsn());
			formBean.setStrRmk(vo.getStrRmk());
			formBean.setStrRsn(vo.getStrRsn());

			formBean.setStrMsgType(vo.getStrMsgType());
			if (formBean.getStrMsgType().equals("1")) {
				throw new Exception(formBean.getStrErrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA->getRsnRmk()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
			if (bo != null)
				bo = null;
		}
	}

	public static void initBedStatus(DischargeCancelTransFB formBean,
			HttpServletRequest request) {
		DischargeCancelTransVO vo = new DischargeCancelTransVO();
		DischargeCancelTransBO bo = new DischargeCancelTransBO();
		vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
				"ipd.transactions.DischargeCancelTransVO", formBean);
		vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
		vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
		vo.setStrCrNo(request.getParameter("crNo"));
		bo.setBedStatusDtl(vo);
		try {
			formBean.setStrRoomType(vo.getStrRoomType());
			formBean.setStrBedType(vo.getStrBedType());
			formBean.setStrMsgType(vo.getStrMsgType());
			if (formBean.getStrMsgType().equals("1")) {
				throw new Exception(formBean.getStrErrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA->initBedStatus()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	// /////////////////////////AJAX
	// RESPONSE//////////////////////////////////////
	public static String AjaxResponse(DischargeCancelTransFB formBean,
			String strValmode, HttpServletRequest request) {
		int flg = Integer.parseInt(strValmode);
		String tmp = request.getParameter("currDtl");
		String currStat[] = tmp.split("@");
		DischargeCancelTransBO bo = new DischargeCancelTransBO();
		DischargeCancelTransVO vo = new DischargeCancelTransVO();
		vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
				"ipd.transactions.DischargeCancelTransVO", formBean);
		if(currStat.length>=5)
		{
			//vo.setStrRoom("<option>" + currStat[3] + "</option>");
			//vo.setStrBed("<option>" + currStat[4] + "</option>");
			vo.setStrRoom("<option value='0'>Select Value</option>");
			vo.setStrBed("<option value='0'>Select Value</option>");
		}
		vo.setStrRoomType(formBean.getStrRoomType());
		vo.setStrBedType(formBean.getStrBedType());
		String movDetails = null;
		HisUtil util = null;
		try {
			/*
			 * if(flg==1) { movDetails =
			 * PatientTransferTransHLP.getChangeOfWard(vo); }
			 */
			if (flg == 1) {
				movDetails = DischargeCancelTransHLP.getChangeOfBed(vo);
			}
			/*
			 * if(flg==3) { movDetails =
			 * PatientTransferTransHLP.getChangeOfDeptUnit(vo); } if(flg==4) {
			 * movDetails = PatientTransferTransHLP.getChangeOfServArea(vo); }
			 */
			formBean.setStrMsgType(vo.getStrMsgType());

			if (formBean.getStrMsgType().equals("1")) { // error
				throw new Exception(formBean.getStrErrMsgString());
			}
		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA->AjaxResponse()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {

			if (vo != null)
				vo = null;

		}
		return movDetails;
	}
	
	public static String AjaxResponse_BS(DischargeCancelTransFB formBean,String strValmode, HttpServletRequest request) 
	{
		int flg = Integer.parseInt(strValmode);
		String tmp = request.getParameter("currDtl");
		String currStat[] = tmp.split("@");
		DischargeCancelTransBO bo = new DischargeCancelTransBO();
		DischargeCancelTransVO vo = new DischargeCancelTransVO();
		vo = (DischargeCancelTransVO) TransferObjectFactory.populateData("ipd.transactions.DischargeCancelTransVO", formBean);
		if(currStat.length>=5)
		{
			//vo.setStrRoom("<option>" + currStat[3] + "</option>");
			//vo.setStrBed("<option>" + currStat[4] + "</option>");
			vo.setStrRoom("<option value='0'>Select Value</option>");
			vo.setStrBed("<option value='0'>Select Value</option>");
		}
		vo.setStrRoomType(formBean.getStrRoomType());
		vo.setStrBedType(formBean.getStrBedType());
		String movDetails = null;
		HisUtil util = null;
		try 
		{
			/*
			 * if(flg==1) { movDetails =
			 * PatientTransferTransHLP.getChangeOfWard(vo); }
			 */
			if (flg == 1) {
				movDetails = DischargeCancelTransHLP.getChangeOfBed_BS(vo);
			}
			/*
			 * if(flg==3) { movDetails =
			 * PatientTransferTransHLP.getChangeOfDeptUnit(vo); } if(flg==4) {
			 * movDetails = PatientTransferTransHLP.getChangeOfServArea(vo); }
			 */
			formBean.setStrMsgType(vo.getStrMsgType());

			if (formBean.getStrMsgType().equals("1")) { // error
				throw new Exception(formBean.getStrErrMsgString());
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA->AjaxResponse()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;

		}
		return movDetails;
	}

	public static void room(DischargeCancelTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DischargeCancelTransVO vo = null;
		String modRoomType = request.getParameter("modRoomType");
		String temp[] = modRoomType.replace('^', '#').split("#");

		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			vo = new DischargeCancelTransVO();
			vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.DischargeCancelTransVO", formBean);
			vo.setStrUnitValue(request.getParameter("deptUnitCode"));
			vo.setStrUnitCode(request.getParameter("ageCode"));
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			vo.setStrUnitCode(request.getParameter("ageCode"));
			vo.setStrSex(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			vo.setStrCrNo(request.getParameter("strCrNo"));
			
			DischargeCancelTransBO bo = new DischargeCancelTransBO();

			vo.setStrWardCode(temp[0]);
			vo.setStrRoomTypeCode(temp[1]);
			if (temp.length > 2) {
				/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
				IpdConfigUtil ipdC=new IpdConfigUtil(formBean.getStrHospitalCode());
				//if (temp[3].equals("11")) {
				if (temp[3].equals(ipdC.getStrPrivateWardType())) {
					vo.setStrMsApprovalFlag(temp[2]);
					vo.setStrWardTypeCode(temp[3]);
					vo.setStrCrNo(temp[4]);
					vo.setStrAdviceAdmNo(temp[5]);
				}
			}
			bo.room(vo);
			formBean.setStrRoomCode(vo.getStrRoomCode());
			formBean.setStrMsgType(vo.getStrMsgType());
			if (formBean.getStrMsgType().equals("1")) { // error
				throw new Exception(formBean.getStrErrMsgString());
			} else {
				response.setHeader("Cache-Control", "no-cache");
				if (vo.getStrWardTypeCode().equals("11")
						&& vo.getStrMsApprovalFlag().equals("1")) {
					if (vo.getStrMsApprovalStatus().equals("2")
							&& !vo.getStrRoomCode().equals(""))
						response.getWriter().print(
								"<option selected value=" + vo.getStrRoomCode()
										+ ">" + vo.getStrRoom() + "</option>");
					else {
						response.getWriter().print(
								"<option selected value=''>N/A</option>");
					}
				} else
					response.getWriter().print(vo.getStrRoom()); // formBean.setStrProvisionDiagnosis(vo.getStrProvisionDiagnosis());
			}

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA->room()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}

	/** **********bed value******************* */

	public static void bed(DischargeCancelTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DischargeCancelTransVO vo = null;
		String modBedType = request.getParameter("modBedType");
		String temp[] = modBedType.split("@");

		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			vo = new DischargeCancelTransVO();
			vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.DischargeCancelTransVO", formBean);
			DischargeCancelTransBO bo = new DischargeCancelTransBO();

			vo.setStrWardCode(temp[0]);
			vo.setStrRoomCode(temp[1]);
			vo.setStrBedTypeCode(temp[2]);
			vo.setStrDeptUnitCode(temp[3]);
			vo.setStrRoomTypeCode(temp[4]);
			vo.setSharableChk(request.getParameter("shr_chk"));

			bo.bed(vo);
			formBean.setStrMsgType(vo.getStrMsgType());
			if (formBean.getStrMsgType().equals("1")) { // error
				throw new Exception(formBean.getStrErrMsgString());
			} else {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(vo.getStrBed());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA->bed()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}

	public static void beddetail(DischargeCancelTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DischargeCancelTransVO vo = new DischargeCancelTransVO();
		DischargeCancelTransBO BO = new DischargeCancelTransBO();
		vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
				"ipd.transactions.DischargeCancelTransVO", formBean);
		String tmp = request.getParameter("modPopUp");
		String temp[] = tmp.replace('^', '#').split("#");
		vo.setStrWardCode(temp[0]);
		vo.setStrRoomCode(temp[1]);
		vo.setStrBedTypeCode(temp[2]);
		vo.setStrDeptUnitCode(temp[3]);

		vo.setStrPopUp("1");
		try {
			BO.setBedDetails(vo);
			String strbed = DischargeCancelTransHLP.getBedDetails(vo);

			formBean.setStrBedProperty(strbed);
			formBean.setStrMsgType(vo.getStrMsgType());
			if (formBean.getStrMsgType().equals("1")) { // error
				throw new Exception(formBean.getStrErrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA->beddetail()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}

	public static boolean patStatusCode(DischargeCancelTransFB formBean) {

		DischargeCancelTransVO vo = null;
		DischargeCancelTransBO bo = null;
		boolean retVal = false;
	//	WebRowSet ws = null;

		try {

			vo = new DischargeCancelTransVO();
			bo = new DischargeCancelTransBO();
			vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.DischargeCancelTransVO", formBean);
			IpdConfigUtil ipd = new IpdConfigUtil(formBean.getStrHospitalCode());
			vo.setStrDischargeCancellationTime(ipd
					.getStrDischargeCancellationTime());

			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo("");
			bo.setpatStatusCode(vo);
			
			formBean.setStrAdmnStatusCode(vo.getStrAdmnStatusCode());
			formBean.setStrPatDeadCode(vo.getStrPatDeadCode());
			formBean.setStrCrNo(vo.getStrCrNo());
			formBean.setDeadStatus(vo.getDeadStatus());
			formBean.setDisStatus(vo.getDisStatus());
			
			//ws = vo.getStrDisAdm();
			if (vo.getDisStatus().equals("0") )
			{
				formBean.setStrErrMsgString("Discharge can be cancelled only on the same day!!!");
				formBean.setStrCrNo("");
				retVal = false;
				return retVal;
			}
			if (vo.getStrInvalidAdmNo().equals("1") )
			{
				formBean.setStrErrMsgString("Invalid Admission No./Data not found!!!");
				formBean.setStrCrNo("");
				retVal = false;
				return retVal;
			}

			else if (vo.getStrCrNo().equals("") ) {
				formBean.setStrErrMsgString("Either Discharge Cancellation Time Expired/Patient Not Admitted!!!");
				formBean.setStrCrNo("");
				retVal = false;
			} else {
				if (!formBean.getStrAdmnStatusCode().equals("12")) {

					if (formBean.getStrPatDeadCode().equals("1")){ //&& formBean.getDeadStatus().equals("")) {
						formBean.setStrErrMsgString("Patient is Dead!!");
						formBean.setStrCrNo("");
						retVal = false;
					}					
					else
						retVal = true;
				} else {
					formBean.setStrErrMsgString("Patient not Discharged!!");
					formBean.setStrCrNo("");
					retVal = false;
				}

			}

			if (formBean.getStrMsgType().equals("1")) { // error
				throw new Exception(formBean.getStrErrMsgString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			formBean.setStrErrMsgString(e.getMessage());
			formBean.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA-->patStatusCode()", formBean
							.getStrErrMsgString());
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
		return retVal;
	}

	/**
	 * This function invoke TransferObjectFactory.populateData() to transfer the
	 * values of all attributes of form bean into vo and the invoke bo insert
	 * method
	 * 
	 * @param formBean
	 */
	public static void insert(DischargeCancelTransFB formBean) {
		DischargeCancelTransVO vo = null;
		DischargeCancelTransBO bo = null;
		IpdConfigUtil ipdConfig = null;
		try {
			ipdConfig = new IpdConfigUtil(formBean.getStrHospitalCode());
			vo = new DischargeCancelTransVO();
			bo = new DischargeCancelTransBO();
			vo = (DischargeCancelTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.DischargeCancelTransVO", formBean);
			String tmp1[] = formBean.getCurDept_Unt_RomCode().replace('^', '#')
					.split("#");
			String tmp2[] = formBean.getCurWrdBedCode().replace('^', '#')
					.split("#");
			vo.setStrBillIntegration(ipdConfig.getStrIntegrationBilling());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrAdmnNo(formBean.getCurAdmNo());
			vo.setStrDeptCode(tmp1[0]);
			vo.setStrDeptUnitCode(tmp1[1]);
			vo.setStrRoomCode(tmp1[2]);
			vo.setStrWardCode(tmp2[0]);
			vo.setStrBedCode(tmp2[1]);
			vo.setStrRmk(formBean.getStrRmk());
			vo.setStrRsn(formBean.getStrRsn());
			vo.setStrRoom(formBean.getStrRoom());
			
			bo.insert(vo);
			formBean.setStrCrNo("");
			formBean.setStrCancelNo(vo.getStrCancelNo());
			if (vo.getStrMsgType().equals("0")) {
				formBean.setStrNormalMsgString("Record saved successfully");
				/*if(discharge_summary_printing_required.equals("1"))
				{
					String dirPath = HisUtil.getParameterFromHisPathXML("IPD_PATH");
					String oldFileName = formBean.getStrAdmnNo()+".pdf";
					String newFileName = formBean.getStrAdmnNo()+"_"+formBean.getStrCancelNo()+".pdf";
					
					renameFile(dirPath, oldFileName, newFileName);
				}*/
				
			}
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			formBean.setStrErrMsgString(e.getMessage());
			formBean.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD-->Discharge Cancellation",
					"DischargeCancelTransDATA-->insert()", formBean
							.getStrErrMsgString());
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;

		} finally {
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}

	/**
	 * renames the existing discharge summary file in a specified path
	 * @param dirPath - directory Path
	 * @param oldFileName - Old File Name with Extension
	 * @param newFileName - New File Name with Extension
	 * @return boolean - true  : Rename success
	 * 					 false : Rename failure 
	 * @throws Exception
	 */
	public static boolean renameFile(String dirPath, String oldFileName,
			String newFileName) throws Exception {

		boolean isRename = false;

		String strOldFileFullPath = "";

		String strNewFileFullPath = "";

		if (!oldFileName.contains(".")) {

			throw new Exception("The Old File Name must have Extention");

		}

		if (dirPath.endsWith("/") || dirPath.endsWith("\\")) {

			throw new Exception("Enter a Valid Directory Path");

		} else {

			strOldFileFullPath = dirPath + File.separator + oldFileName;

		}

		File fileOld = new File(strOldFileFullPath);

		if (!fileOld.exists()) {

			throw new Exception("The Required File " + oldFileName
					+ " is Not Found at " + dirPath);

		}

		if (!newFileName.contains(".")) {

			throw new Exception("The New File Name must have Extention");

		}

		String[] temp1 = oldFileName.replace(".", "#").split("#");

		String[] temp2 = newFileName.replace(".", "#").split("#");

		if (!temp1[1].trim().equalsIgnoreCase(temp2[1].trim())) {

			throw new Exception("Both The Files must have Extention");

		}

		if (dirPath.endsWith("/") || dirPath.endsWith("\\")) {

			throw new Exception("Enter a Valid Directory Path");

		} else {

			strNewFileFullPath = dirPath + File.separator + newFileName;

		}

		File fileNew = new File(strNewFileFullPath);

		isRename = fileOld.renameTo(fileNew);

		return isRename;

	}

}
