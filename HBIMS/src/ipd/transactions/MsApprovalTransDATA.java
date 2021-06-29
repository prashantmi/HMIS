package ipd.transactions;

import hisglobal.ReportUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.HLPOccupationDetails;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import java.text.SimpleDateFormat;

import billing.BillingBO;
import billing.BillingVO;

public class MsApprovalTransDATA {

	public static void DEPTUNIT(MsApprovalTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;

		String strCrNo = request.getParameter("modCrno");
		String strdeptunit = request.getParameter("modDeptUnit");

		try {

			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();

			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrCrNo(strCrNo);
			VO.setStrDeptUnitCmb(strdeptunit);

			bo.DEPTUNIT(VO);
			if ((VO.getStrIsDependent().equals("00"))
					|| (VO.getStrIsDependent().equals("0"))) {

				beanObj.setStrdependent("0");
			} else {
				beanObj.setStrdependent("1");
			}

			// set Messages into form bean
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			if (beanObj.getStrMsgType().equals("1")) { // error
				throw new Exception(beanObj.getStrMsgString());
			} else {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						VO.getStrProvisionDiagnosis() + "^"
								+ VO.getStrConsultantName());

				// beanObj.setStrConsultantName(VO.getStrConsultantName());
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());

			}

		}

		catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.GO() --> -->", VO.getStrMsgString());

			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {

			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}

	// provide room data on the basis of ward and hospital code
	public static void ROOM(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request, HttpServletResponse _response) {

		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;
		HisUtil util = null;

		String modWard = _request.getParameter("modWard");
		String strprivateroom = "";
		String tmpStrCombinedValue = "";
		try {
			msApprovalTransVO = new MsApprovalTransVO();
			msApprovalTransBO = new MsApprovalTransBO();
			tmpStrCombinedValue = _request.getParameter("strCombinedValue");
			_msApprovalTransFB.setStrSeatId(_request.getSession().getAttribute(
					"SEATID").toString());
			_msApprovalTransFB.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			msApprovalTransVO.setStrAge(_request.getParameter("strAge"));
			msApprovalTransVO.setStrAgeCode(_request.getParameter("strAgeCode"));
			msApprovalTransVO.setStrSex(_request.getParameter("strSex"));
			msApprovalTransVO.setStrPatCatCode(_request.getParameter("strPatCatCode"));
			msApprovalTransVO.setStrDeptUnitCode(tmpStrCombinedValue.split("@")[4]);
			msApprovalTransVO.setStrDeptCode(tmpStrCombinedValue.split("@")[4].substring(0, 3));
			msApprovalTransVO.setStrprivatewardValue(modWard);
			msApprovalTransVO.setStrHospitalCode(_msApprovalTransFB
					.getStrHospitalCode());
			msApprovalTransBO.privateroom(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

			util = new HisUtil("ipd", "MsApprovalTransDATA");
			strprivateroom = util.getOptionValue(msApprovalTransVO
					.getStrRoomWs(), "0", "0^Select Value", false);
			_response.setHeader("Cache-Control", "no-cache");
			_response.getWriter().print(strprivateroom);
		} catch (Exception e) {
			msApprovalTransVO.setStrMsgString(e.getMessage());
			msApprovalTransVO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.ROOM() -->", e.getMessage());
			try {
				_response.getWriter().print(
						"Error####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception e1) {
			}
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
			util = null;
		}
	}

	public static void BED(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request, HttpServletResponse _response) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;

		HisUtil util = null;
		String strPrivateBed = "";
		try {
			msApprovalTransVO = new MsApprovalTransVO();
			msApprovalTransBO = new MsApprovalTransBO();

			msApprovalTransVO.setStrWardCode(_request.getParameter("wardCode"));
			msApprovalTransVO.setStrRoomNo(_request.getParameter("roomCode"));
			msApprovalTransVO.setStrDeptUnitCode(_request
					.getParameter("deptUnitCode"));
			msApprovalTransVO.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());

			msApprovalTransBO.bed(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

			util = new HisUtil("ipd", "MsApprovalTransDATA");
			strPrivateBed = util.getOptionValue(
					msApprovalTransVO.getStrBedWs(), "0", "0^Select Value",
					false);
			_response.setHeader("Cache-Control", "no-cache");
			_response.getWriter().print(strPrivateBed);

		} catch (Exception e) {
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.BED() --> -->", e.getMessage());
			try {
				_response.getWriter().print(
						"Error####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception e1) {
			}
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
			util = null;
		}
	}
/*
	public static void ROOMDETAIL(MsApprovalTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;
		// String strmsgStr = "";
		String modeWard = request.getParameter("modWard");
		String modRoom = request.getParameter("modRoom");

		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();
			VO.setStrprivatewardValue(modeWard);
			VO.setStrRoomValue(modRoom);
			bo.roomdetail(VO);

			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			String tempStr = MsApprovalTransHLP.hlproomdetail(VO);
			// System.out.println("tempStr in room detail=="+tempStr);
			if (beanObj.getStrMsgType().equals("1")) { // error
				throw new Exception(beanObj.getStrMsgString());
			} else {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(tempStr);
			}

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.ROOMDETAIL() --> -->", VO
							.getStrMsgString());

			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
*/
	public static void chkFun(MsApprovalTransFB beanObj, String chk) {

		MsApprovalTransVO VO = new MsApprovalTransVO();
		String[] strtemp = chk.split("@");
		String strcrNo = strtemp[0];
		String stradvno = strtemp[1];
		//String strbookdt = strtemp[2];
		//String strhospitalCode = strtemp[3];
		try{
	
			VO.setStrCrNo(strcrNo);
			VO.setStrAdviceNo(stradvno);
			//VO.setStrBooking_date(strbookdt);
			//VO.setStrHospitalCode(strhospitalCode);
			beanObj.setStrCrNo(strcrNo);
			HisUtil util = new HisUtil("ipd", "MsApprovalTransDATA");
	
			beanObj.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			}catch (Exception e) {
	
		}
		
	}

	// set data in case of approve mode
	public static void msapprovalList(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;
		String strChk[] = null;
		String strLength = null;
		
		try {
			msApprovalTransBO = new MsApprovalTransBO();
			msApprovalTransVO = new MsApprovalTransVO();
			strChk = _request.getParameterValues("chk");
			strLength = _request.getParameter("chkLength");
			_msApprovalTransFB.setStrSeatId(_request.getSession().getAttribute(
					"SEATID").toString());
			_msApprovalTransFB.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			msApprovalTransVO.setStrSeatId(_msApprovalTransFB.getStrSeatId());
			msApprovalTransVO.setStrChk1(strChk);
			
			//System.out.println("ck1:"+msApprovalTransVO.getStrChk1());
			
			String chk[]=msApprovalTransVO.getStrChk1();
			msApprovalTransVO.setStrCrNo(chk[0].split("@")[0]);
			
			msApprovalTransVO.setStrLength(strLength);
			msApprovalTransVO.setStrHospitalCode(_msApprovalTransFB
					.getStrHospitalCode());

			msApprovalTransBO.msapprovalList(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

			//msApprovalTransBO.approvedby(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());
		
			_msApprovalTransFB.setStrApprovedBY(msApprovalTransVO
					.getStrApprovedBY());
			_msApprovalTransFB.setStrVerifiedBy(msApprovalTransVO
					.getStrVerifiedBy());
			_msApprovalTransFB.setHrowlengthApproval(strLength);
			_msApprovalTransFB.setApprovallist(MsApprovalTransHLP
					.approvallist(msApprovalTransVO.getApprovalListWs()));//HLP

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

			msApprovalTransBO.getEmployeeCombo(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

			_msApprovalTransFB.setStrEmployeeComboValues(msApprovalTransVO
					.getStrEmployeeComboValues());
			
			
			 _msApprovalTransFB.setStrBedLable("Bed");
			 _msApprovalTransFB.setStrdateLable("Proposed Admission Date");
			  msApprovalTransBO.getDataFromBooking(msApprovalTransVO);
			 _msApprovalTransFB.setStrLengthOfStay(msApprovalTransVO.getStrLengthOfStay());
		     _msApprovalTransFB.setStrSurgeryDate(msApprovalTransVO.getStrSurgeryDate());
		     _msApprovalTransFB.setStrAdmissionType(msApprovalTransVO.getStrAdmissionType());
			 _msApprovalTransFB.setStrAdviceNo(msApprovalTransVO.getStrAdviceNo());
			 _msApprovalTransFB.setStrEpisodeNumber(msApprovalTransVO.getStrEpisodeNumber());
			 _msApprovalTransFB.setStrDeptUnitCode(msApprovalTransVO.getStrDeptUnitCode());
			 _msApprovalTransFB.setStrWardName(msApprovalTransVO.getStrWardName());
			 _msApprovalTransFB.setStrWardCode(msApprovalTransVO.getStrWardCode());
			 _msApprovalTransFB.setStrRoomBedNo(msApprovalTransVO.getStrRoomBedNo());
			 _msApprovalTransFB.setStrPropAdminssionDate(msApprovalTransVO.getStrPropAdminssionDate());
			 _msApprovalTransFB.setStrDeptUnitCmb(msApprovalTransVO.getStrDeptUnitCmb());
			 _msApprovalTransFB.setStrConsultantName(msApprovalTransVO.getStrConsultantName());			
			 _msApprovalTransFB.setStrProvisionDiagnosis(msApprovalTransVO.getStrProvisionDiagnosis());
			 if(_msApprovalTransFB.getIsOffline().equals("1"))
			 {
				_msApprovalTransFB.setStrFormNo(msApprovalTransVO.getStrFormNo());
			 }
		} catch (Exception e) {
			msApprovalTransVO.setStrMsgString(e.getMessage());
			msApprovalTransVO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.msapprovalList() --> -->", e
							.getMessage());

			_msApprovalTransFB
					.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	// provide ordered list of approved patient
	public static void PATLIST(MsApprovalTransFB beanObj) {
		// String strmsgStr ="";

		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;

		try {
			bo = new MsApprovalTransBO();

			VO = new MsApprovalTransVO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());

			// bo.wardallotement(VO);
			bo.PATLIST(VO);

			beanObj.setStrAllotementlist(MsApprovalTransHLP.allotementlist(VO));
			// beanObj.setStrAllotementlist(MsApprovalTransHLP.allotementlist(VO));

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.PATLIST() --> -->", VO
							.getStrMsgString());

			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}

	// Provide list of ward,room and bed in which vacant bed are available or in
	// which beds are going to be vacant on current date

	public static void msallote(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO=new MsApprovalTransBO();

		try {
			msApprovalTransVO = new MsApprovalTransVO();

			_msApprovalTransFB.setStrSeatId(_request.getSession().getAttribute(
					"SEATID").toString());
			_msApprovalTransFB.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			msApprovalTransVO.setStrChk1(_request.getParameterValues("chk"));
			msApprovalTransVO.setStrHospitalCode(_msApprovalTransFB
					.getStrHospitalCode());
			
			String chk[]=msApprovalTransVO.getStrChk1();
			msApprovalTransVO.setStrCrNo(chk[0].split("@")[0]);
			
			 _msApprovalTransFB.setStrBedLable("Bed");
			 _msApprovalTransFB.setStrdateLable("Proposed Admission Date");
			  msApprovalTransBO.getDataFromBooking(msApprovalTransVO);// Admission Advice is not used in Odisha
			 // msApprovalTransBO.getDataFromPatAdmission(msApprovalTransVO);
			 _msApprovalTransFB.setStrLengthOfStay(msApprovalTransVO.getStrLengthOfStay());
		     _msApprovalTransFB.setStrSurgeryDate(msApprovalTransVO.getStrSurgeryDate());
		     _msApprovalTransFB.setStrAdmissionType(msApprovalTransVO.getStrAdmissionType());
			 _msApprovalTransFB.setStrAdviceNo(msApprovalTransVO.getStrAdviceNo());
			 _msApprovalTransFB.setStrEpisodeNumber(msApprovalTransVO.getStrEpisodeNumber());
			 _msApprovalTransFB.setStrDeptUnitCode(msApprovalTransVO.getStrDeptUnitCode());
			 _msApprovalTransFB.setStrWardName(msApprovalTransVO.getStrWardName());
			 _msApprovalTransFB.setStrWardCode(msApprovalTransVO.getStrWardCode());
			 _msApprovalTransFB.setStrWard(msApprovalTransVO.getStrWardCode());
			 _msApprovalTransFB.setStrRoomBedNo(msApprovalTransVO.getStrRoomBedNo());
			 _msApprovalTransFB.setStrPropAdminssionDate(msApprovalTransVO.getStrPropAdminssionDate());
			 _msApprovalTransFB.setStrDeptUnitCmb(msApprovalTransVO.getStrDeptUnitCmb());
			 _msApprovalTransFB.setStrConsultantName(msApprovalTransVO.getStrConsultantName());			
			 _msApprovalTransFB.setStrProvisionDiagnosis(msApprovalTransVO.getStrProvisionDiagnosis());
			 if(_msApprovalTransFB.getIsOffline().equals("1"))
			 {
				_msApprovalTransFB.setStrFormNo(msApprovalTransVO.getStrFormNo());
			 }
			
		    

			_msApprovalTransFB.setStrPatApprovedList(MsApprovalTransHLP
					.patApprovedList(msApprovalTransVO));

		} catch (Exception e) {
			//e.printStackTrace();
			msApprovalTransVO.setStrMsgString(e.getMessage());
			msApprovalTransVO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.msallote() --> -->", e.getMessage());

			_msApprovalTransFB
					.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
		}
	}

	// set data for set-priority page
	/*
	public static void prioritydata(String chk, MsApprovalTransFB beanObj) {
		// String strmsgStr = "";
		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;
		HisUtil util = null;

		String strprivateward = "";
		try {

			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO

			String[] strtemp = chk.split("@");
			String strcrNo = strtemp[0];
			String stradvno = strtemp[1];
			String strbookdt = strtemp[2];

			VO = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();

			VO.setStrCrNo(strcrNo);
			VO.setHadvno(stradvno);
			VO.setHbkdate(strbookdt);
			beanObj.setPatientDetailsWs(getPatientDtlsForPriorityWs(chk,
					beanObj));
			bo.prioritydata(VO);

			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
			// check error
			if (beanObj.getStrMsgType().equals("1")) { // error
				throw new Exception(beanObj.getStrMsgString());
			} else {
				beanObj.setStrCrNo(VO.getStrCrNo());
				beanObj.setHcrno(VO.getStrCrNo());
				beanObj.setHadvno(VO.getHadvno());
				beanObj.setHbkdate(VO.getHbkdate());
				beanObj.setStrPrivateBed(VO.getStrPrivateBed());
				beanObj.setStrEpisodeNumber(VO.getStrEpisodeNumber());
				// beanObj.setStrageSex(VO.getStrageSex());
				// beanObj.setStrNname(VO.getStrNname());
				// beanObj.setStrFatherHusband(VO.getStrFatherHusband());
				// beanObj.setStrPatCategory(VO.getStrPatCategory());
				// beanObj.setStrEpisodeNumber(VO.getStrEpisodeNumber());
				beanObj.setStrDeptUnit(VO.getStrDeptUnit());
				// beanObj.setStraddress(VO.getStraddress());
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
				// beanObj.setStrConsultantName(VO.getStrConsultantName());
				beanObj.setStrMlcNo(VO.getStrMlcNo());
				// beanObj.setStrAdviceNo(VO.getStrAdviceNo());
				// beanObj.setStrBooking_date(VO.getStrBooking_date());
				// beanObj.setStrBookingQno(VO.getStrBookingQno());

				util = new HisUtil("ipd", "MsApprovalTransDATA");

				strprivateward = util.getOptionValue(VO.getStrprivatewardWs(),
						"0", "0^Select Value", false);

				beanObj.setStrprivatewardValue(strprivateward);

			}
		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.prioritydata() --> -->", VO
							.getStrMsgString());

			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (util != null)
				util = null;
		}
	}
*/
	// set data for cancel page
	public static void cancel(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;
		String chk = null;
		try {
			msApprovalTransVO = new MsApprovalTransVO();
			msApprovalTransBO = new MsApprovalTransBO();

			chk = _request.getParameter("chk");
			String[] strtemp = chk.split("@");

			msApprovalTransVO.setStrCrNo(strtemp[0]);
			msApprovalTransVO.setStrAdviceNo(strtemp[1]);

			msApprovalTransVO.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			msApprovalTransVO.setStrSeatId(_request.getSession().getAttribute(
					"SEATID").toString());

			msApprovalTransBO.cancel(msApprovalTransVO);

			if (_msApprovalTransFB.getStrMsgType().equals("1"))
				throw new Exception(_msApprovalTransFB.getStrMsgString());

			_msApprovalTransFB.setStrCrNo(msApprovalTransVO.getStrCrNo());
			_msApprovalTransFB.setStrAdviceNo(msApprovalTransVO
					.getStrAdviceNo());
			_msApprovalTransFB.setStrRoomNo(msApprovalTransVO.getStrRoomNo());
			_msApprovalTransFB.setStrWardCode(msApprovalTransVO
					.getStrWardCode());
			_msApprovalTransFB.setStrWardType(msApprovalTransVO
					.getStrWardType());
			_msApprovalTransFB.setStrBedType(msApprovalTransVO.getStrBedType());
			_msApprovalTransFB.setStrRoomType(msApprovalTransVO
					.getStrRoomType());
			_msApprovalTransFB.setStrPrefAdmDate(msApprovalTransVO
					.getStrPrefAdmDate());
			_msApprovalTransFB.setStrBedCode(msApprovalTransVO.getStrBedCode());
			_msApprovalTransFB.setStrApprovedStatus(msApprovalTransVO
					.getStrApprovedStatus());
			_msApprovalTransFB.setStrEpisodeNumber(msApprovalTransVO
					.getStrEpisodeNumber());
			_msApprovalTransFB.setStrDeptUnit(msApprovalTransVO
					.getStrDeptUnit());
			_msApprovalTransFB.setStrAdmDate(msApprovalTransVO.getStrAdmDate());
			_msApprovalTransFB.setStrWardName(msApprovalTransVO
					.getStrWardName());
			_msApprovalTransFB.setStrRoomBedNo(msApprovalTransVO
					.getStrRoomBedNo());

			msApprovalTransBO.getEmployeeCombo(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

			_msApprovalTransFB.setStrEmployeeComboValues(msApprovalTransVO
					.getStrEmployeeComboValues());

		} catch (Exception e) {
			e.printStackTrace();
			_msApprovalTransFB.setStrCrNo("");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.cancel() --> -->", msApprovalTransVO
							.getStrMsgString());

			_msApprovalTransFB
					.setStrErrorMsg("Error####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	/*
	 * public static void status(MsApprovalTransFB beanObj) { String strmsgStr =
	 * ""; MsApprovalTransVO VO = null; MsApprovalTransBO bo = null;
	 * 
	 * VO = new MsApprovalTransVO(); String strmsg = ""; bo = new
	 * MsApprovalTransBO();
	 * 
	 * VO.setStrCrNo(beanObj.getStrCrNo());
	 * 
	 * VO.setStrHospitalCode(beanObj.getStrHospitalCode()); bo.status(VO);
	 * 
	 * if (VO.getStrPatStatusCode().equals("13")) {
	 * 
	 * strmsg = " Patient is Dead! "; beanObj.setStrErr(strmsg); }
	 * 
	 * else { // MsApprovalTransDATA data = new MsApprovalTransDATA();
	 * 
	 * MsApprovalTransDATA.GO(beanObj); } }
	 */
	public static void status(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;
		
		IpdConfigUtil icu = null;
		WebRowSet ws=null;
		HisUtil util = null;
		String temp = "";
		
		try {
			_msApprovalTransFB.setStrSeatId(_request.getSession().getAttribute("SEATID").toString());
			_msApprovalTransFB.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
			icu = new IpdConfigUtil(_msApprovalTransFB.getStrHospitalCode());
			msApprovalTransVO = new MsApprovalTransVO();
			String strmsg = "";
			msApprovalTransBO = new MsApprovalTransBO();
			_msApprovalTransFB.setIsOffline(icu.getStrMsApprovalOffline());
			msApprovalTransVO.setIsOffline(_msApprovalTransFB.getIsOffline());
			msApprovalTransVO.setStrCrNo(_msApprovalTransFB.getStrCrNo());
			msApprovalTransVO.setStrHospitalCode(_msApprovalTransFB.getStrHospitalCode());
			msApprovalTransBO.status(msApprovalTransVO);
			_msApprovalTransFB.setStrDepartment(msApprovalTransVO.getStrDepartment());
			_msApprovalTransFB.setStrMsgString(msApprovalTransVO.getStrMsgString());
			_msApprovalTransFB.setStrMsgType(msApprovalTransVO.getStrMsgType());
			_msApprovalTransFB.setStrAge(msApprovalTransVO.getStrAge());
			_msApprovalTransFB.setStrPatName(msApprovalTransVO.getStrPatName());
			_msApprovalTransFB.setStrSex(msApprovalTransVO.getStrSex());
			_msApprovalTransFB.setStrUnitValue(msApprovalTransVO.getStrUnitValue());
			//System.out.println("_msApprovalTransFB.setStrUnitValue"+_msApprovalTransFB.getStrUnitValue());
			_msApprovalTransFB.setStrUnitCode(msApprovalTransVO.getStrUnitCode());
			_msApprovalTransFB.setStrCrNo(msApprovalTransVO.getStrCrNo());
			_msApprovalTransFB.setStrEpisodeNumber(msApprovalTransVO.getStrEpisodeNumber());
			_msApprovalTransFB.setStrDepartment(msApprovalTransVO.getStrDepartment());
			_msApprovalTransFB.setStrUnit(msApprovalTransVO.getStrUnit());
			
			_msApprovalTransFB.setStrDepartmentValue(msApprovalTransVO.getStrDepartmentValue());
			_msApprovalTransFB.setStrVisitValue(msApprovalTransVO.getStrVisitValue());
			_msApprovalTransFB.setStrPrimaryCategory(msApprovalTransVO.getStrPrimaryCategory());
			_msApprovalTransFB.setStrSecondaryCategory(msApprovalTransVO.getStrSecondaryCategory());
			util = new HisUtil("ADT","MsApprovalTransDATA");
			/*temp = util.getOptionValue(msApprovalTransVO.getStrDeptComboValuesWs(), _msApprovalTransFB.getStrDepartmentValue(), "0^Select Value",
					true);
			_msApprovalTransFB.setStrDeptComboValues(temp);
			*/
			if (!msApprovalTransVO.getStrSecondaryCategory().equals("0")) 
			{
				temp = util.getOptionValue(msApprovalTransVO.getTreatmentCategoryWs(),IpdTransConfig.getTreatmentCategory(),"0^Select Value", false);	
				_msApprovalTransFB.setStrTreatmentCategoryValues(temp);
			} 
			else 
			{
				temp = util.getOptionValue(msApprovalTransVO.getTreatmentCategoryWs(), msApprovalTransVO.getStrPrimaryCategory(), "0^Select Value", false);
				_msApprovalTransFB.setStrTreatmentCategoryValues(temp);
			}
			/*if (_msApprovalTransFB.getStrMsgType().equals("0")) 
			{*/
				
				
				
				
				
			//}
				
			if(msApprovalTransVO.getStrMsgType().equals("2"))
			{
				strmsg = "Invalid CR number";
				_msApprovalTransFB.setStrCrNo("");
				_msApprovalTransFB.setStrErrorMsg(strmsg);
			}
			/*else
			{*/

				if (msApprovalTransVO.getStrMsgType().equals("1"))
					throw new Exception(msApprovalTransVO.getStrMsgString());

				//if (msApprovalTransVO.getStrPatStatusCode().equals("13")) {
				if (msApprovalTransVO.getStrIsdead().equals("1")) {
					strmsg = " Patient is Dead! ";
					_msApprovalTransFB.setStrCrNo("");
					_msApprovalTransFB.setStrErrorMsg(strmsg);
				}
				
				/*
				 * This function is loop will invoked when Patient is in IPD  
				 */
				
				 if (msApprovalTransVO.getStrPatStatusCode().equals("2")) { // IPD
					// Case
					strmsg = "Patient is Admitted !";
					_msApprovalTransFB.setStrMsg(strmsg);
					_msApprovalTransFB.setStrBedLable("Room/Bed");
					_msApprovalTransFB.setStrdateLable("Admitted Date");
					msApprovalTransBO.getDataFromPatAdmission(msApprovalTransVO);
					if (msApprovalTransVO.getStrMsgType().equals("1"))
						throw new Exception(msApprovalTransVO.getStrMsgString());
				_msApprovalTransFB.setStrGovermentEmployeeBasicPayLimit(icu.getGovtEmployeeBasicPayLimit());
				_msApprovalTransFB.setStrPrivateEmployeeMonthlyIncomeLimit(icu.getPrivateEmployeeMonthlyIncomeLimit());
				_msApprovalTransFB.setStrAdviceNo(msApprovalTransVO
						.getStrAdviceNo());
				_msApprovalTransFB.setStrEpisodeNumber(msApprovalTransVO
						.getStrEpisodeNumber());
				_msApprovalTransFB.setStrDeptUnitCode(msApprovalTransVO
						.getStrDeptUnitCode());
				_msApprovalTransFB.setStrWardName(msApprovalTransVO
						.getStrWardName());
				_msApprovalTransFB.setStrWardCode(msApprovalTransVO
						.getStrWardCode());
				_msApprovalTransFB.setStrRoomBedNo(msApprovalTransVO
						.getStrRoomBedNo());
				_msApprovalTransFB.setStrConsultantName(msApprovalTransVO
						.getStrConsultantName());
				_msApprovalTransFB.setStrPropAdminssionDate(msApprovalTransVO
						.getStrPropAdminssionDate());
				_msApprovalTransFB.setStrDeptUnitCmb(msApprovalTransVO
						.getStrDeptUnitCmb());
				_msApprovalTransFB.setStrAdmNo(msApprovalTransVO.getStrAdmNo());
				_msApprovalTransFB.setStrProvisionDiagnosis(msApprovalTransVO.getStrProvisionDiagnosis());
				_msApprovalTransFB.setStrWardType(msApprovalTransVO
						.getStrWardType());
				_msApprovalTransFB.setStrLengthOfStay(msApprovalTransVO.getStrLengthOfStay());
			    _msApprovalTransFB.setStrSurgeryDate(msApprovalTransVO.getStrSurgeryDate());
			    _msApprovalTransFB.setStrAdmissionType(msApprovalTransVO.getStrAdmissionType());
				if(_msApprovalTransFB.getIsOffline().equals("1"))
				{
					_msApprovalTransFB.setStrFormNo(msApprovalTransVO.getStrFormNo());
				}
			} 
				/*
				 * This function is loop will invoked when Patient is in Opd  
				 */
				
				 if (msApprovalTransVO.getStrPatStatusCode().equals("3")) { // OPD
				// Case
				strmsg = "Opd Patient !";
				_msApprovalTransFB.setStrMsg(strmsg);
				_msApprovalTransFB.setStrBedLable("Bed");
				_msApprovalTransFB.setStrdateLable("Proposed Admission Date");
				msApprovalTransBO.getDataFromBooking(msApprovalTransVO);
				
				/*
				 *This will check whether advice is generated or not!!! 
				 */
				
				/*if(msApprovalTransVO.getStrMsgType().equals("4"))
				{
					
					strmsg = "Admission Advice is not yet generated or expired";
					_msApprovalTransFB.setStrCrNo("");
					_msApprovalTransFB.setStrMsg("");
					_msApprovalTransFB.setStrErrorMsg(strmsg);
					
					if(_msApprovalTransFB.getIsOffline().equals("1"))
					{
						_msApprovalTransFB.setStrFormNo(msApprovalTransVO.getStrFormNo());
					}
					
				}
				else{
					*/	
					      MsApprovalTransDAO.getPrevoiusDiagnosisDtl(msApprovalTransVO);
					     _msApprovalTransFB.setStrLengthOfStay(msApprovalTransVO.getStrLengthOfStay());
					     _msApprovalTransFB.setStrSurgeryDate(msApprovalTransVO.getStrSurgeryDate());
					     _msApprovalTransFB.setStrAdmissionType(msApprovalTransVO.getStrAdmissionType());
						 _msApprovalTransFB.setStrAdviceNo(msApprovalTransVO.getStrAdviceNo());
						 _msApprovalTransFB.setStrEpisodeNumber(msApprovalTransVO.getStrEpisodeNumber());
						 _msApprovalTransFB.setStrDeptUnitCode(msApprovalTransVO.getStrDeptUnitCode());
						 _msApprovalTransFB.setStrWardName(msApprovalTransVO.getStrWardName());
						 _msApprovalTransFB.setStrWardCode(msApprovalTransVO.getStrWardCode());
						 _msApprovalTransFB.setStrRoomBedNo(msApprovalTransVO.getStrRoomBedNo());
						 _msApprovalTransFB.setStrPropAdminssionDate(msApprovalTransVO.getStrPropAdminssionDate());
						 _msApprovalTransFB.setStrDeptUnitCmb(msApprovalTransVO.getStrDeptUnitCmb());
						 _msApprovalTransFB.setStrConsultantName(msApprovalTransVO.getStrConsultantName());			
						 _msApprovalTransFB.setStrProvisionDiagnosis(msApprovalTransVO.getStrProvisionDiagnosis());
						 ws=msApprovalTransVO.getPreviousDiagnosisWs();
 						 _msApprovalTransFB.setStrPreviousDiagnosisDetail(AdmissionAdviceTransHLP.getDiagnosisDtl(ws,msApprovalTransVO.getStrVisitValue()))	;
						 if(_msApprovalTransFB.getIsOffline().equals("1"))
						 {
							_msApprovalTransFB.setStrFormNo(msApprovalTransVO.getStrFormNo());
						 }
				//}
				}
				
				 if (msApprovalTransVO.getStrPatStatusCode().equals("1")) { // Emergency
					// Case
					strmsg = "Emergency Patient !";
					_msApprovalTransFB.setStrMsg(strmsg);
					_msApprovalTransFB.setStrBedLable("Bed");
					_msApprovalTransFB.setStrdateLable("Proposed Admission Date");
					msApprovalTransBO.getDataFromBooking(msApprovalTransVO);
					
					/*
					 *This will check whether advice is generated or not!!! 
					 */
					
					/*if(msApprovalTransVO.getStrMsgType().equals("4"))
					{
						
						strmsg = "Admission Advice is not yet generated or expired";
						_msApprovalTransFB.setStrCrNo("");
						_msApprovalTransFB.setStrMsg("");
						_msApprovalTransFB.setStrErrorMsg(strmsg);
						
						if(_msApprovalTransFB.getIsOffline().equals("1"))
						{
							_msApprovalTransFB.setStrFormNo(msApprovalTransVO.getStrFormNo());
						}
						
					}
					else{*/
							
						      MsApprovalTransDAO.getPrevoiusDiagnosisDtl(msApprovalTransVO);
						     _msApprovalTransFB.setStrLengthOfStay(msApprovalTransVO.getStrLengthOfStay());
						     _msApprovalTransFB.setStrSurgeryDate(msApprovalTransVO.getStrSurgeryDate());
						     _msApprovalTransFB.setStrAdmissionType(msApprovalTransVO.getStrAdmissionType());
							 _msApprovalTransFB.setStrAdviceNo(msApprovalTransVO.getStrAdviceNo());
							 _msApprovalTransFB.setStrEpisodeNumber(msApprovalTransVO.getStrEpisodeNumber());
							 _msApprovalTransFB.setStrDeptUnitCode(msApprovalTransVO.getStrDeptUnitCode());
							 _msApprovalTransFB.setStrWardName(msApprovalTransVO.getStrWardName());
							 _msApprovalTransFB.setStrWardCode(msApprovalTransVO.getStrWardCode());
							 _msApprovalTransFB.setStrRoomBedNo(msApprovalTransVO.getStrRoomBedNo());
							 _msApprovalTransFB.setStrPropAdminssionDate(msApprovalTransVO.getStrPropAdminssionDate());
							 _msApprovalTransFB.setStrDeptUnitCmb(msApprovalTransVO.getStrDeptUnitCmb());
							 _msApprovalTransFB.setStrConsultantName(msApprovalTransVO.getStrConsultantName());			
							 _msApprovalTransFB.setStrProvisionDiagnosis(msApprovalTransVO.getStrProvisionDiagnosis());
							 ws=msApprovalTransVO.getPreviousDiagnosisWs();
	 						 _msApprovalTransFB.setStrPreviousDiagnosisDetail(AdmissionAdviceTransHLP.getDiagnosisDtl(ws,msApprovalTransVO.getStrVisitValue()))	;
							 if(_msApprovalTransFB.getIsOffline().equals("1"))
							 {
								_msApprovalTransFB.setStrFormNo(msApprovalTransVO.getStrFormNo());
							 }
					//}
					}
				
						/*if (!msApprovalTransVO.getStrPatStatusCode().equals("13") && 
								!msApprovalTransVO.getStrMsgType().equals("4")) {*/
				       if (!msApprovalTransVO.getStrIsdead().equals("1")) {
						
						String strConfCatCode = icu.getStaffCategory();
						
						/*
						 * This HLPOccupationDetails hlp is used to bring details from patient occupation detail table 
						 */
						
						String strOccupation = HLPOccupationDetails
								.getOccupationDetails(msApprovalTransVO.getStrCrNo(),
										msApprovalTransVO.getStrPatCategoryCode(),
										strConfCatCode,msApprovalTransVO.getStrHospitalCode());
		
						if (msApprovalTransVO.getStrMsgType().equals("1"))
							throw new Exception(msApprovalTransVO.getStrMsgString());
		
						_msApprovalTransFB.setStrOccupation(strOccupation);
						
						/*
						 * This function bring form details from the database
						 */
		
						msApprovalTransBO.getFormDetails(msApprovalTransVO);
		
						/*if (msApprovalTransVO.getStrMsgType().equals("1"))
							throw new Exception(msApprovalTransVO.getStrMsgString());*/
		
						_msApprovalTransFB.setStrApprovedStatus(msApprovalTransVO
								.getStrApprovedStatus());
						_msApprovalTransFB.setStrRemark(msApprovalTransVO
								.getStrRemark());
						_msApprovalTransFB.setStrFormNo(msApprovalTransVO
								.getStrFormNo());
						_msApprovalTransFB.setStrRequestDate(msApprovalTransVO
								.getStrRequestDate());
						_msApprovalTransFB.setStrWaitPeriod(msApprovalTransVO
								.getStrWaitPeriod());
						_msApprovalTransFB.setStrVerifiedDate(msApprovalTransVO
								.getStrVerifiedDate());
						_msApprovalTransFB.setStrOccID(msApprovalTransVO.getStrOccID());
						
						_msApprovalTransFB.setStrPriorityType(msApprovalTransVO.getStrPriorityType());
						if(_msApprovalTransFB.getStrPriorityType().equals("1"))
							_msApprovalTransFB.setStrPriorityFlag("1");
						
						
						
						
						
						_msApprovalTransFB.setStrUrgentTypeReason(msApprovalTransVO.getStrUrgentTypeReason());
						_msApprovalTransFB.setStrPriorityRemarksFlag(msApprovalTransVO.getStrUrgentTypeRemarks());
						
						
						System.out.println("urgent reason "+_msApprovalTransFB.getStrUrgentTypeReason());
						System.out.println("urgent remarks "+_msApprovalTransFB.getStrUrgentTypeRemarks());
		
						msApprovalTransBO.getEmployeeCombo(msApprovalTransVO);
		
						if (msApprovalTransVO.getStrMsgType().equals("1"))
							throw new Exception(msApprovalTransVO.getStrMsgString());
		
						_msApprovalTransFB.setStrEmployeeComboValues(msApprovalTransVO
								.getStrEmployeeComboValues());
		
						if (msApprovalTransVO.getStrAdmNo() != null)
							_msApprovalTransFB.setStrAdmNo(msApprovalTransVO
									.getStrAdmNo());
		
						if (!(_msApprovalTransFB.getStrApprovedStatus().equals("0"))) {
							if (!msApprovalTransVO.getStrMsgType().equals("2")) {
								_msApprovalTransFB.setStrCrNo("");
								_msApprovalTransFB
										.setStrErrorMsg("MS Approval can not be done because request is already approved!");
								_msApprovalTransFB.setStrMsg("");
							}
						}
						_msApprovalTransFB.setStrPatStatusCode(msApprovalTransVO
								.getStrPatStatusCode());
					}
				       else 
						{	
							throw new Exception(msApprovalTransVO.getStrMsgString());	
						}
				
			
		

		} catch (Exception e) {
			e.printStackTrace();
			_msApprovalTransFB.setStrMsg("");
			_msApprovalTransFB.setStrCrNo("");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.status() --> -->", msApprovalTransVO
							.getStrMsgString());

			_msApprovalTransFB
					.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	public static void GO(MsApprovalTransFB beanObj) {

		// String strmsgStr = "";
	//	String strdeptStr = "";
		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;
		HisUtil util = null;

		String strmsg = "";
		String strdatelabel = "";

		try {

			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO

			VO = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();

			VO.setStrCrNo(beanObj.getStrCrNo());
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			bo.go(VO);
			// System.out.println("VO.getStrPatCategoryCode() in
			// data=="+VO.getStrPatCategoryCode());
			// beanObj.setStrPatStatusCode(VO.getStrPatStatusCode());

			if (VO.getStrPatStatusCode().equals("13")) // dead patient
			{

				// System.out.println("inside if in data");
				strmsg = "Patient is Dead! ";
				beanObj.setStrErr(strmsg);

				throw new Exception("Patient Dead!");

			}
			if (VO.getStrPatStatusCode().equals("11")) // admitted patient
			{

				strdatelabel = "Admitted Date";

				beanObj.setStrdateLable(strdatelabel);
				strmsg = "Patient is Admitted !";
				beanObj.setStrMsg(strmsg);
			}

			else

			if (VO.getStrPatStatusCode().equals("12")) // opd patient
			{
				strdatelabel = "Proposed Admission Date";

				beanObj.setStrdateLable(strdatelabel);
				// System.out.println("inside if in data");
				strmsg = "Opd Patient !";
				beanObj.setStrMsg(strmsg);

			}

			IpdConfigUtil icu = new IpdConfigUtil(beanObj.getStrHospitalCode());
			String strConfCatCode = icu.getStaffCategory();

			beanObj.setPatientDetailsWs(getPatientDtlsWs(beanObj));

			if (beanObj.getPatientDetailsWs().next()) {

				VO.setStrPatCategoryCode(beanObj.getPatientDetailsWs()
						.getString(7));

				beanObj.getPatientDetailsWs().previous();

			} else {
				VO.setStrPatCategoryCode("0");
			}

			if (VO.getStrPatCategoryCode().equals("12")) {

				beanObj.setStrIsPgiEmp("1");
				beanObj.setStrGovtEmployee("1");

			} else {
				beanObj.setStrIsPgiEmp("0");
			}
			String strOccupation = HLPOccupationDetails.getOccupationDetails(VO
					.getStrCrNo(), VO.getStrPatCategoryCode(), strConfCatCode,VO.getStrHospitalCode());

			beanObj.setStrOccupation(strOccupation);

			// set Messages into form bean
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			// check error
			if (beanObj.getStrMsgType().equals("1")) { // error
				throw new Exception(beanObj.getStrMsgString());
			} else { // business object sets Web Row Set into value object
				// set required values into form bean
				// util = new HisUtil("ipd","MsApprovalTransDATA");
				// verifyStr =
				// util.getOptionValue(VO.getStrVerifiedByWs(),"0","0^Select
				// Value",false);
				// beanObj.setStrVerifiedByValues(verifyStr);

				beanObj.setStrageSex(VO.getStrageSex());
				beanObj.setStrNname(VO.getStrNname());
				beanObj.setStrFatherHusband(VO.getStrFatherHusband());
				beanObj.setStrPatCategory(VO.getStrPatCategory());
				beanObj.setStrEpisodeNumber(VO.getStrEpisodeNumber());
				beanObj.setStrDeptUnit(VO.getStrDeptUnit());
				beanObj.setStraddress(VO.getStraddress());
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
				// beanObj.setStrConsultantName(VO.getStrConsultantName());
				beanObj.setStrEmpName(VO.getStrEmpName());
				beanObj.setStrDesigName(VO.getStrDesigName());
				beanObj.setStrBasicIncom(VO.getStrBasicIncom());
				beanObj.setStrOfficeName(VO.getStrOfficeName());
				beanObj.setStrOfficeAddres1(VO.getStrOfficeAddress1());
				beanObj.setStrOfficeAddress2(VO.getStrOfficeAddress2());
				beanObj.setStrRelation(VO.getStrRelation());
				beanObj.setStrPhoneNo(VO.getStrPhoneNo());
				beanObj.setStrMlcNo(VO.getStrMlcNo());
				beanObj.setStrVerifiedBy(VO.getStrVerifiedBy());
				beanObj.setStrAdviceNo(VO.getStrAdviceNo());
				beanObj.setStrBooking_date(VO.getStrBooking_date());
				beanObj.setStrBookingQno(VO.getStrBookingQno());
				beanObj.setStrWardype(VO.getStrWardype());
				beanObj.setStrPrefAdmDate(VO.getStrPrefAdmDate());
				beanObj.setStrOccupation(strOccupation);
				beanObj.setStrDeptUnitCode(VO.getStrDeptUnitCode());
				beanObj.setStrDeptCode(VO.getStrDeptCode());

				if (VO.getStrPropAdminssionDate() == "") {

					beanObj.setStrPropAdminssionDate(VO.getStrPrefAdmDate());
				} else {
					beanObj.setStrPropAdminssionDate(VO
							.getStrPropAdminssionDate());

				}
				if (VO.getStrRequestDate() == "") {

					beanObj.setStrRequestDate(VO.getStrCtDate());

				} else {
					beanObj.setStrRequestDate(VO.getStrRequestDate());
				}

				if (VO.getStrVerifiedDate() == "") {

					beanObj.setStrVerifiedDate(VO.getStrCtDate());
				} else {

					beanObj.setStrVerifiedDate(VO.getStrVerifiedDate());
				}

				beanObj.setStrRemark(VO.getStrRemark());
				beanObj.setStrFormNo(VO.getStrFormNo());
				beanObj.setStrWaitPeriod(VO.getStrWaitPeriod());

				util = new HisUtil("ipd", "MsApprovalTransDATA");
				if (VO.getStrDeptUnitWs() != null) {
					VO.getStrDeptUnitWs().next();

					beanObj.setStrDeptUnitCmb(VO.getStrDeptUnitWs()
							.getString(1));

				}
				VO.setStrDeptUnitCmb(beanObj.getStrDeptUnitCmb());

			}

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.GO() --> -->", VO.getStrMsgString());

			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (util != null)
				util = null;
		}
	}

	// VIEW PAGE
	public static void view(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;

		try {
			msApprovalTransBO = new MsApprovalTransBO();
			msApprovalTransVO = new MsApprovalTransVO();
			msApprovalTransVO.setStrSeatId(_request.getSession().getAttribute("SEATID").toString());
			msApprovalTransVO.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
			msApprovalTransVO.setStrCrNo(_request.getParameter("chk").split("@")[0]);
			msApprovalTransVO.setStrAdviceNo(_request.getParameter("chk").split("@")[1]);

			msApprovalTransBO.getDataFromMSApproval(msApprovalTransVO);
					if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());
			if(msApprovalTransVO.getStrPatCategoryCode().equals(IpdTransConfig.getStaffCategCode()))
			{
				msApprovalTransVO.setStrIsPgiEmp("Yes");
			}
			else
			{
				msApprovalTransVO.setStrIsPgiEmp("No");
			}
			TransferObjectFactory.populateData(_msApprovalTransFB, msApprovalTransVO);
			
			IpdConfigUtil icu = new IpdConfigUtil(msApprovalTransVO.getStrHospitalCode());
			String strConfCatCode = icu.getStaffCategory();
			String strOccupation = HLPOccupationDetails.getOccupationDetailsView(msApprovalTransVO.getStrCrNo(),
							msApprovalTransVO.getStrPatCategoryCode(),
							strConfCatCode,msApprovalTransVO.getStrHospitalCode());

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

			_msApprovalTransFB.setStrOccupation(strOccupation);

		} catch (Exception e) {
			_msApprovalTransFB.setStrMsgType("1");
			_msApprovalTransFB.setStrCrNo("");
			HisException eObj = new HisException("MSApproval","MsApprovalTransDATA.insert()", e.getMessage());
			_msApprovalTransFB.setStrErrorMsg("ERROR####Application Error[ERROR ID: "+ eObj.getErrorID()+"],Contact System Administrator!");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	// insert dtad in msapproval and occupation detail
	public static void insert(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;
		try {
			msApprovalTransBO = new MsApprovalTransBO();
			_msApprovalTransFB.setStrSeatId(_request.getSession().getAttribute(
					"SEATID").toString());
			_msApprovalTransFB.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			msApprovalTransVO = (MsApprovalTransVO) TransferObjectFactory
					.populateData("ipd.transactions.MsApprovalTransVO",
							_msApprovalTransFB);

			msApprovalTransBO.insert(msApprovalTransVO);
			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());
			_msApprovalTransFB.setStrCrNo("");
			_msApprovalTransFB.setStrMsg("Data Has been Successfully Saved");
		} catch (Exception e) {
			e.printStackTrace();
			_msApprovalTransFB.setStrMsgType("1");
			_msApprovalTransFB.setStrCrNo("");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.insert() -->", e.getMessage());
			_msApprovalTransFB
					.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	// update approval status as approved
	public static void updatemsapproval(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;
		try {

			msApprovalTransBO = new MsApprovalTransBO();
			_msApprovalTransFB.setStrSeatId(_request.getSession().getAttribute(
					"SEATID").toString());

			_msApprovalTransFB.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());

			msApprovalTransVO = (MsApprovalTransVO) TransferObjectFactory
					.populateData("ipd.transactions.MsApprovalTransVO",
							_msApprovalTransFB);

			msApprovalTransBO.updatemsapproval(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

		} catch (Exception e) {
			msApprovalTransVO.setStrMsgString(e.getMessage());
			_msApprovalTransFB.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.updatemsapproval() --> -->", e
							.getMessage());
			_msApprovalTransFB
					.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	public static void savewardallotement(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {

		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;

		try {

			msApprovalTransVO = (MsApprovalTransVO) TransferObjectFactory
					.populateData("ipd.transactions.MsApprovalTransVO",
							_msApprovalTransFB);

			msApprovalTransVO.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			String tmpStrCombinedValue = _request.getParameter("approvedCrNo");

			msApprovalTransVO.setStrWardType(tmpStrCombinedValue.split("@")[3]);
			msApprovalTransVO.setStrAdviceNo(tmpStrCombinedValue.split("@")[1]);
			msApprovalTransVO.setStrCrNo(tmpStrCombinedValue.split("@")[0]);

			msApprovalTransBO = new MsApprovalTransBO();

			msApprovalTransBO.savewardallotement(msApprovalTransVO);
			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

		} catch (Exception e) {
			msApprovalTransVO.setStrMsgString(e.getMessage());
			_msApprovalTransFB.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.savewardallotement() --> -->",
					msApprovalTransVO.getStrMsgString());
			_msApprovalTransFB
					.setStrErrorMsg("Error####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	public static void cancelapproval(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;

		try {
			msApprovalTransBO = new MsApprovalTransBO();
			_msApprovalTransFB.setStrSeatId(_request.getSession().getAttribute(
					"SEATID").toString());
			_msApprovalTransFB.setStrHospitalCode(_request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());

			msApprovalTransVO = (MsApprovalTransVO) TransferObjectFactory
					.populateData("ipd.transactions.MsApprovalTransVO",
							_msApprovalTransFB);
			msApprovalTransBO.cancelapproval(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());

		} catch (Exception e) {
			e.printStackTrace();
			_msApprovalTransFB.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.cancelapproval() --> -->",
					msApprovalTransVO.getStrMsgString());
			_msApprovalTransFB
					.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	public static WebRowSet getPatientDtlsWs(MsApprovalTransFB formBean) {

		WebRowSet ws = null;

		BillingBO boObj = new BillingBO();
		BillingVO voObj = new BillingVO();

		voObj.setStrValue1(formBean.getStrCrNo());

		boObj.getPatientDetails(voObj);

		formBean.setStrMsgString(voObj.getStrMsgString());
		formBean.setStrMsgType(voObj.getStrMsgType());

		if (formBean.getStrMsgType().equals("0")) {

			if (voObj.getGblWs1().size() == 0) {

				formBean.setStrMsgString("Invalid Cr Number");
				formBean.setStrMsgType("1");

			}

			else {

				ws = voObj.getGblWs1();

			}
		}

		return ws;

	}

	public static WebRowSet getPatientDtlsForPriorityWs(String chk,
			MsApprovalTransFB formBean) {

		WebRowSet ws = null;

		BillingBO boObj = new BillingBO();
		BillingVO voObj = new BillingVO();
		String[] strtemp = chk.split("@");
		String strcrNo = strtemp[0];
		// String stradvno = strtemp[1];
		// String strbookdt = strtemp[2];

		voObj.setStrValue1(strcrNo);

		boObj.getPatientDetails(voObj);

		formBean.setStrMsgString(voObj.getStrMsgString());
		formBean.setStrMsgType(voObj.getStrMsgType());

		if (formBean.getStrMsgType().equals("0")) {

			if (voObj.getGblWs1().size() == 0) {

				formBean.setStrMsgString("Invalid Cr Number");
				formBean.setStrMsgType("1");

			}

			else {

				ws = voObj.getGblWs1();

			}
		}

		return ws;

	}

	public static boolean generateApproveList(MsApprovalTransFB beanObj) {

		// String strmsgStr = "";
		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;
		HisUtil util=null;
		boolean fretvalue = true;
		try {

			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();

			VO.setStrListDate(beanObj.getStrListDate());
VO.setStrHospitalCode(beanObj.getStrHospitalCode());
VO.setStrSeatId(beanObj.getStrSeatId());
			fretvalue = bo.generateApproveList(VO);

			// set Messages into form bean
			
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
			util=new HisUtil("transaction","MsApprovalTransDATA");
			beanObj.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			// check error
			if (beanObj.getStrMsgType().equals("1")) { // error
				throw new Exception(beanObj.getStrMsgString());
			}
			

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.generateApproveList() --> -->", VO
							.getStrMsgString());
			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
		return fretvalue;
	}

	/* this function list patients for not approval case */
	public static void notapprovedlist(MsApprovalTransFB beanObj) {

		// String strmsgStr = "";
		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;
		HisUtil util=null;
		ArrayList<String> strnotApprovepatList = null;

		try {

			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();

			strnotApprovepatList = bo.notapprovedlist(VO);

			VO.setStrNotApprovedList(strnotApprovepatList);// set patient list
			// in Vo

			String strTemp = MsApprovalTransHLP.hlpNotApprovedList(VO);
			beanObj.setStrNotApprovedData(strTemp);

			// set Messages into form bean
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
			util=new HisUtil("transaction","MsApprovalTransDATA");
			beanObj.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			// check error
			if (beanObj.getStrMsgType().equals("1")) { // error
				throw new Exception(beanObj.getStrMsgString());
			}
			

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.notapprovedlist() --> -->", VO
							.getStrMsgString());
			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}

	/* this function list patients for cancel case */
	/*
	public static void cancellist(MsApprovalTransFB beanObj) {

		// String strmsgStr = "";
		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;

		ArrayList<String> strcancelList = null;

		try {

			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();

			strcancelList = bo.cancellist(VO);

			VO.setStrCancelList(strcancelList); // set patient list in Vo

			String strTemp = MsApprovalTransHLP.hlpCancelList(VO);
			beanObj.setStrCancelData(strTemp);

			// set Messages into form bean
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			// check error
			if (beanObj.getStrMsgType().equals("1")) { // error
				throw new Exception(beanObj.getStrMsgString());
			}
			
			
			
		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.cancellist() --> -->", VO
							.getStrMsgString());
			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
*/
	public static void verifyCrNo(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request) {
		MsApprovalTransVO msApprovalTransVO = null;
	 	MsApprovalTransBO msApprovalTransBO = null;

		try {
			_msApprovalTransFB.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
			_msApprovalTransFB.setStrHospitalCode(_msApprovalTransFB.getStrHospitalCode());
			msApprovalTransVO = new MsApprovalTransVO();
			msApprovalTransBO = new MsApprovalTransBO();
			msApprovalTransVO.setStrCrNo(_msApprovalTransFB.getStrCrNo());
			msApprovalTransVO.setStrHospitalCode(_msApprovalTransFB.getStrHospitalCode());
			msApprovalTransBO.verifyCrNo(msApprovalTransVO);
			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());
			if (msApprovalTransVO.getStrMsgType().equals("2")) {
				_msApprovalTransFB.setStrCrNo("");
				_msApprovalTransFB.setStrErrorMsg("Please Enter Valid Cr. No.!");
				_msApprovalTransFB.setStrMsgType("2");
			}
		} catch (Exception e) {
			HisException eObj = new HisException("His Global Utility Method",
					"MsApprovalTransDATA.verifyCrNo() --> -->", e.getMessage());
			_msApprovalTransFB.setStrCrNo("");
			_msApprovalTransFB.setStrMsgType("2");
			_msApprovalTransFB
					.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator! ");
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
		}
	}

	public static void privateWardCombo(MsApprovalTransFB _msApprovalTransFB,
			HttpServletRequest _request, HttpServletResponse _response) {
		MsApprovalTransVO msApprovalTransVO = null;
		MsApprovalTransBO msApprovalTransBO = null;
		HisUtil util = null;

		try 
		{
			msApprovalTransBO = new MsApprovalTransBO();
			msApprovalTransVO = new MsApprovalTransVO();
			util = new HisUtil("ipd", "MsApprovalTransDATA");

			msApprovalTransVO.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());

			String tmpStrCombinedValue = _request.getParameter("strCombinedValue");
			String strWard = _request.getParameter("strWard");
			System.out.println("strWard"+strWard);
			
			//System.out.println("tmpStrCombinedValue"+tmpStrCombinedValue);
			msApprovalTransVO.setStrAge(_request.getParameter("strAge"));
			String wardType=_request.getParameter("strAgeCode");
			msApprovalTransVO.setStrAgeCode(wardType);
			msApprovalTransVO.setStrSex(_request.getParameter("strSex"));//null
			msApprovalTransVO.setStrPatCatCode(_request.getParameter("strPatCatCode"));
			
			//System.out.println(msApprovalTransVO.getStrAge()+" "+msApprovalTransVO.getStrAgeCode()+" "+msApprovalTransVO.getStrSex()+" "+msApprovalTransVO.getStrPatCatCode());

			
			msApprovalTransVO.setStrCrNo(tmpStrCombinedValue.split("@")[0]);
			msApprovalTransVO.setStrAdviceNo(tmpStrCombinedValue.split("@")[2].substring(0, 3));
			msApprovalTransVO.setStrWardType(wardType);
			//msApprovalTransVO.setStrWardType(tmpStrCombinedValue.split("@")[1]);
			msApprovalTransVO.setStrDeptUnitCode(tmpStrCombinedValue.split("@")[4]);
			msApprovalTransVO.setStrDeptCode(tmpStrCombinedValue.split("@")[3]);
			
			//System.out.println(msApprovalTransVO.getStrCrNo()+" "+msApprovalTransVO.getStrAdviceNo());
			//System.out.println(msApprovalTransVO.getStrWardType()+" "+msApprovalTransVO.getStrDeptUnitCode());
			//System.out.println(msApprovalTransVO.getStrDeptCode());

			//msApprovalTransVO.setStrHospitalCode(_msApprovalTransFB.getStrHospitalCode());

			msApprovalTransBO.privateWard(msApprovalTransVO);

			if (msApprovalTransVO.getStrMsgType().equals("1"))
				throw new Exception(msApprovalTransVO.getStrMsgString());
			_response.setHeader("Cache-Control", "no-cache");
			_response.getWriter().print(util.getOptionValue(msApprovalTransVO.getStrprivatewardWs(), strWard,"0^Select Value", false));

			_msApprovalTransFB.setStrCrNo(msApprovalTransVO.getStrCrNo());
			_msApprovalTransFB.setStrAdviceNo(msApprovalTransVO.getStrAdviceNo());
			_msApprovalTransFB.setStrWardType(msApprovalTransVO.getStrWardType());
			_msApprovalTransFB.setStrDeptUnitCode(msApprovalTransVO.getStrDeptUnitCode());
			_msApprovalTransFB.setStrDeptCode(msApprovalTransVO.getStrDeptCode());

		} 
		catch (Exception e) 
		{
			msApprovalTransVO.setStrMsgString(e.getMessage());
			msApprovalTransVO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.privateWardCombo() --> ", e
							.getMessage());

			try {
				_response.getWriter().print(
						"Error####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception e1) {

			}
			eObj = null;
		} finally {
			msApprovalTransVO = null;
			msApprovalTransBO = null;
			util = null;
		}
	}
	
	
	public static void showReport(MsApprovalTransFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				IpdConfigUtil ipdconfig = null;
				Map<String, Object> params = new HashMap<String, Object>();
				MsApprovalTransVO msApprovalTransVO=null;
				msApprovalTransVO = (MsApprovalTransVO) TransferObjectFactory.populateData("ipd.transactions.MsApprovalTransVO",formBean);
				try 
				{
					ipdconfig = new IpdConfigUtil(formBean.getStrHospitalCode());
					
					String strReportName1 = "LIST OF APPROVED PATIENTS BUT WARD NOT ALLOTED FOR PRIVATE WARD ALLOTMENT";
					String strReportName2 = "LIST OF PATIENTS FOR PRIVATE WARD ALLOTMENT";
					String strReportName3 = "LIST OF PATIENTS FOR PRIVATE WARD ALLOTMENT";//Duplicate
					String strReportName4 = "LIST OF REJECTED PATIENTS FOR PRIVATE WARD ALLOTMENT";
					String strReportName5 = "LIST OF CANCELLED PATIENTS FOR PRIVATE WARD ALLOTMENT";
					
					String strSeatId = formBean.getStrSeatId();
					String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					String strListNo = formBean.getStrListNo();
					
					reportFormat = formBean.getStrReportFormat();
					boolean footerVisible = true;
	
					if (formBean.getStrIsFooter().equals("1")) 
					{
						footerVisible = false;
					}
					if(formBean.getStrCase().equals("1"))//LIST OF APPROVED PATIENTS BUT WARD NOT ALLOTED FOR PRIVATE WARD ALLOTMENT
					{						
						String reportPath = "/ipd/reports/approvedButWardNotAlloted_ipdrpt.rptdesign";						
						formBean.setStrAdviceFrmValidity(ipdconfig.getStrAdmissionAdviceValidityFrom());
						formBean.setStrAdviceToValidity(ipdconfig.getStrAdmissionAdviceValidityTo()); 
						
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName1);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("advice_Frm_Validity", formBean.getStrAdviceFrmValidity());
						params.put("advice_To_Validity", formBean.getStrAdviceToValidity());
						params.put("hosp_Code", strHospitalCode);
						
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);						
					}
					else if(formBean.getStrCase().equals("2"))//LIST OF PATIENTS FOR PRIVATE WARD ALLOTMENT-NEW LIST/DUP LIST
					{					
						if(formBean.getStrWardAllotChk().equals("2"))//DUP LIST
						{	
							String reportPath = "/ipd/reports/wardAllotedList2_ipdrpt.rptdesign";							
							params.put("list_No", strListNo);
							params.put("hosp_Code", strHospitalCode);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName2);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							
							ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						}
						else if(formBean.getStrWardAllotChk().equals("1"))//NEW LIST
						{							
							MsApprovalTransDAO.setApprovedDataInList(msApprovalTransVO);//INSERT LIST DATA
							String reportPath = "/ipd/reports/wardAllotedList1_ipdrpt.rptdesign";
							
							formBean.setStrAdviceFrmValidity(ipdconfig.getStrAdmissionAdviceValidityFrom());
							formBean.setStrAdviceToValidity(ipdconfig.getStrAdmissionAdviceValidityTo()); 
							
							params.put("seat_Id", strSeatId);
							params.put("hosp_Code", strHospitalCode);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName3);
							params.put("advice_Frm_Validity", formBean.getStrAdviceFrmValidity());
							params.put("advice_To_Validity", formBean.getStrAdviceToValidity());
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							
							ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						}					
					}
					else if(formBean.getStrCase().equals("3"))
					{						
						String reportPath = "/ipd/reports/rejectedList_ipdrpt.rptdesign";
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");						
						
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName4);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						
					}
					else if(formBean.getStrCase().equals("4"))
					{						
						String reportPath = "/ipd/reports/cancelList_ipdrpt.rptdesign";
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
											
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName5);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);						
					}			
				} 
				catch (Exception e) 
				{
					//e.printStackTrace();
				}
			}

	public static void listValues(MsApprovalTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		MsApprovalTransVO VO = null;
		MsApprovalTransBO bo = null;
		HisUtil util = null;
		String strListVal = "";
		try {

			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			bo = new MsApprovalTransBO();
			VO = new MsApprovalTransVO();
			
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
					
			bo.getListValues(VO);
		//		String a="";

			
				
			if (VO.getStrMsgType().equals("1")) {

				throw new Exception(VO.getStrMsgString());

			}
			util = new HisUtil("IPD Reports", "MsApprovalTransDATA");
			strListVal = util.getOptionValue(VO.getStrListWs(), "0",
					"0^Select Value", false);
			beanObj.setStrListValues(strListVal);
			util=new HisUtil("transaction","MsApprovalTransDATA");
			beanObj.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			
		}catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->MSApproval",
					"MsApprovalTransDATA.GO() --> -->", VO.getStrMsgString());

			beanObj.setStrErrorMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {

			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	public static void setUnitComboValues( MsApprovalTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {
		String temp = "";
		try {
			MsApprovalTransVO msApprovalTransVO = new MsApprovalTransVO();
			msApprovalTransVO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			msApprovalTransVO.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			msApprovalTransVO.setStrCrNo(request.getParameter("puk"));
			msApprovalTransVO.setStrDepartmentCode(request.getParameter("strDepartmentCode"));
			//System.out.println("msApprovalTransVO.setStrDepartmentCode"+formBean.getStrUnitValue());
			MsApprovalTransBO bo = new MsApprovalTransBO();
			bo.setUnitComboValues(msApprovalTransVO);
			HisUtil util = new HisUtil("MS Approval","MsApprovalTransDATA");
			//System.out.println("formBean.getStrUnitValue()"+formBean.getStrUnitValue());
			temp = util.getOptionValue(msApprovalTransVO.getStrUnitComboValuesWs(), formBean.getStrUnitValue(), "0^Select Value",
					true);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"MsApprovalTransDATA->setUnitComboValues()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
	public static void initBedStatus(MsApprovalTransFB formBean,
			HttpServletRequest request) {

		MsApprovalTransVO vo = new MsApprovalTransVO();
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		String temp = request.getParameter("wardCode");
		String strTemp[] = temp.replace("^", "#").split("#");
		vo.setStrTreatmentCategoryCode(request
				.getParameter("treatmentCategory"));
		vo.setStrWard(strTemp[0]);
		vo.setStrWardTypeCode(strTemp[1]);
		formBean.setStrWard(request.getParameter("wardCode"));
		if (!(request.getParameter("deptname").equals(""))) {
			formBean.setStrDepartment(request.getParameter("deptname"));
			vo.setDeptNameFound(true);
		} else {

			vo.setDeptNameFound(false);
		}

		vo.setStrPrimaryCategory(request.getParameter("treatmentCategory"));
		vo.setStrDepartmentValue(request.getParameter("deptcode"));
		vo.setStrUnitValue(request.getParameter("deptUnitCode"));
		vo.setStrUnitCode(request.getParameter("ageCode"));
		vo.setStrSex(request.getParameter("sexCode"));
		vo.setStrAge(request.getParameter("age"));
		vo.setStrCrNo(request.getParameter("crNo"));
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		vo.setStrRoomType(IpdTransConfig.getRoomType());
		// vo.setStrPropAdmissionDate(request.getParameter("bDate"));
		//formBean.setStrPropAdmissionDate(request.getParameter("bDate"));
		MsApprovalTransBO bo = new MsApprovalTransBO();
		bo.setBedStatusValues(vo);
		formBean.setStrMsgString(vo.getStrMsgString());
		formBean.setStrMsgType(vo.getStrMsgType());
		formBean.setStrUnitCode(vo.getStrUnitValue());
		HisUtil util = null;
		try {

			if (formBean.getStrMsgType().equals("0")) {

				util = new HisUtil("Admission Advice Trans",
						"AdmissionAdviceTransDATA");
				temp = util.getOptionValue(vo.getWardTYPES(), vo
						.getStrWardTypeCode(), "0^Select Value", false);
				formBean.setStrWardTypeValue(temp);
				temp = util.getOptionValue(vo.getWardWS(), vo.getStrWard(),
						"0^Select Value", true);
				formBean.setStrWardValues(temp);
				temp = util.getOptionValue(vo.getRoomWs(), "0",
						"0^Select Value", false);
				formBean.setStrRoomValues(temp);
				temp = util.getOptionValue(vo.getRoomTypeWs(), IpdTransConfig
						.getRoomType(), "0^Select Value", false);

				formBean.setStrRoomTypeValues(temp);
				temp = util.getOptionValue(vo.getBedTypeWs(), IpdTransConfig
						.getBedTypeCode(), "0^Select Value", false);

				formBean.setStrBedTypeValues(temp);

				if (vo.isDeptNameFound() == false) {

					temp = util.getOptionValue(vo.getDepartTypeWS(), "0",
							"0^Select Value", true);
					formBean.setStrDepartmentValue(temp);

					formBean.setDeptFound(vo.isDeptNameFound());
				} else {
					formBean.setDeptFound(true);
				}

			} else {

				throw new Exception(formBean.getStrMsgString());

			}

		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"MsApprovalTransDATA->initBedStatus()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;

		} finally {

			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
			if (util != null)
				util = null;
		}

	}
	public static void checkDupicate(HttpServletRequest request,
			HttpServletResponse response, MsApprovalTransFB formBean) {
		try {
			String pk = request.getParameter("wardCode");
			String deptUnitCode = request.getParameter("deptUnitCode");
			String temp[] = pk.replace("^", "#").split("#");
			MsApprovalTransVO vo = new MsApprovalTransVO();
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			MsApprovalTransBO bo = new MsApprovalTransBO();
			vo.setStrWard(temp[0]);
			vo.setStrUnitValue(deptUnitCode);
			vo.setStrCrNo(request.getParameter("crno"));
			bo.CheckDuplicate(vo);
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(vo.getStrFlag());
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"MsApprovalTransDATA->checkDupicate()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;

		}
	}
	public static void initWardCombo(HttpServletRequest request,
			HttpServletResponse response, MsApprovalTransFB formBean) {
		String temp = "";
		try {
			MsApprovalTransVO vo = new MsApprovalTransVO();
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());			
			vo.setStrPrimaryCategory(request.getParameter("treatmentCategory"));
			vo.setStrDepartmentValue(request.getParameter("deptcode"));
			vo.setStrUnitValue(request.getParameter("deptUnitCode"));
			vo.setStrUnitCode(request.getParameter("ageCode"));
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());			
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			vo.setStrDepartment(request.getParameter("deptCode"));
			//vo.setStrUnitValue(request.getParameter("deptUnitCode"));
			vo.setStrUnitCode(request.getParameter("unitCode"));
			vo.setStrSex(request.getParameter("sex"));
			vo.setStrAge(request.getParameter("age"));			
			//vo.setStrDiseaseTypeCode(request.getParameter("diseaseType"));
			MsApprovalTransBO bo = new MsApprovalTransBO();
			bo.setWardCombo(vo);
			HisUtil util = new HisUtil("MS Approval Trans","MsApprovalTransDATA");
			temp = util.getOptionValue(vo.getWardWS(), "0", "0^Select Ward",true);
			if (!vo.getStrMsgType().equals("1")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"MsApprovalTransDATA->initWardCombo()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
	public static void setDeptComboValues( MsApprovalTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {
		String temp = "";
		MsApprovalTransBO bo = null;
		MsApprovalTransVO vo = null;
		try {
			vo = new MsApprovalTransVO();
			bo = new MsApprovalTransBO();
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrCrNo(formBean.getStrCrNo());
			bo.setDeptComboValues(vo);
			
			if(vo.getStrDeptComboValuesWs().size()==0)
			{
				formBean.setEpFlag("0");
			}
			HisUtil util = new HisUtil("MS Approval","MsApprovalTransDATA");
			temp = util.getOptionValue(vo.getStrDeptComboValuesWs(), formBean.getStrDepartmentValue(), "0^Select Value",
					true);
			formBean.setStrDeptComboValues(temp);
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","MsApprovalTransDATA->setDeptComboValues()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
}
