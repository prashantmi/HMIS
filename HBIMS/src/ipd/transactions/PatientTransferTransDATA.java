package ipd.transactions;

import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.ResourceBundle;

import ipd.IpdConfigUtil;
//import ipd.IpdTransConfig;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientTransferTransDATA {

	public static void initClientDetail(String strChk,
			PatientTransferTransFB formBean) {
		PatientTransferTransVO vo = new PatientTransferTransVO();
		vo.setStrChk(strChk);
	     try{
			formBean.setStrCrNo(strChk);
			formBean.setStrMsgType(vo.getStrMsgType());
			// String tempStr = MsApprovalTransHLP.ocuupationView(VO);
			if (formBean.getStrMsgType().equals("1")) {
				formBean
						.setStrErrMsgString("Error while initializing Client Details");// error
				throw new Exception(formBean.getStrErrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->initClientDetail()", strmsgText);
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
	
	public static void getTransferAdvisedBy(PatientTransferTransFB _formBean, HttpServletRequest _Request,HttpServletResponse response)
	{
		PatientTransferTransVO patientTransferTransVO = null;
		PatientTransferTransBO patientTransferTransBO = null;
		try
		{
			patientTransferTransVO = new PatientTransferTransVO();
			patientTransferTransBO = new PatientTransferTransBO();
			//System.out.println("dis mode"+patientFinalDischargeVO.getStrDischrg_Mode());
			patientTransferTransVO.setStrHospCode(_Request.getSession().getAttribute("HOSPITAL_CODE").toString());
			patientTransferTransVO.setStrCurrentDeptUnitRoom(_formBean.getStrCurrentDeptUnitRoom());
			patientTransferTransVO.setStrConsultant(_Request.getParameter("cons"));
			patientTransferTransBO.getAdvisedBy(patientTransferTransVO);
			_formBean.setStrRmk(patientTransferTransVO.getStrRmk());
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(patientTransferTransVO.getStrRmk());
			
			if (patientTransferTransVO.getStrMsgType().equals("1")) throw new Exception(patientTransferTransVO.getStrMsgString());
		}
		catch (Exception e)
		{
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getRsnRmk()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientTransferTransBO = null;
			patientTransferTransVO = null;

		}
	}

	// /////////////////////////AJAX
	// RESPONSE//////////////////////////////////////
	public static String AjaxResponse(PatientTransferTransFB formBean,
			String strValmode, HttpServletRequest request) {
		PatientTransferTransVO vo = new PatientTransferTransVO();
		int flg = Integer.parseInt(strValmode);
		String movDetails = "";
		try {
			vo.setStrErrorCode("0");
			if (formBean.getStrMsgType().equals("1")) {
				vo.setStrErrorCode("1");
				throw new Exception(formBean.getStrErrMsgString());
			} else {
				
				String tmp = request.getParameter("currDtl");
				//System.out.println("DATATATATT----->"+tmp);
				String currStat[] = tmp.replace('^', '#').split("#");
				vo = (PatientTransferTransVO) TransferObjectFactory
						.populateData(
								"ipd.transactions.PatientTransferTransVO",
								formBean);
				vo.setStrdeptproperty(formBean.getStrdeptproperty());
				if(currStat.length>=4)
				{
					vo.setStrUnitName("<option>" + currStat[1] + "</option>");
					vo.setStrWard("<option>" + currStat[2] + "</option>");
					vo.setStrRoom("<option>" + currStat[3] + "</option>");
					vo.setStrBed("<option>" + currStat[4] + "</option>");
				}
				else
				{
					vo.setStrUnitName("<option></option>");
					vo.setStrWard("<option></option>");
					vo.setStrRoom("<option></option>");
					vo.setStrBed("<option></option>");
				}
				vo.setStrwardType(formBean.getStrwardType());
				vo.setStrRoomType(formBean.getStrRoomType());
				vo.setStrBedType(formBean.getStrBedType());
				vo.setStrServArea(formBean.getStrServArea());
				vo.setStrPrevDblOcc(formBean.getStrPrevDblOcc());
				//System.out.println("SET DATA ---->"+formBean.getStrPrevDblOcc());
				
				
				if (flg == 1) {
					movDetails = PatientTransferTransHLP.getChangeOfWard(vo);
				}
				if (flg == 2) {
					movDetails = PatientTransferTransHLP.getChangeOfBed(vo);
				}
				/*if (flg == 3) {
					movDetails = PatientTransferTransHLP
							.getChangeOfDeptUnit(vo);
				}*/
				
				if(flg == 3)
				{
					movDetails=PatientTransferTransHLP.getChangeOfServiceAreaOT(formBean);
				}
				
				if (flg == 4) {
					movDetails = PatientTransferTransHLP
							.getChangeOfServArea(vo);
				}
				if (flg == 5) {
					movDetails = PatientTransferTransHLP
							.getChangeOfHospital(vo);
				}
				if (flg == 6) {
					String strUnitCombo=unitChangeCombo(formBean,request);
					String wardCode = request.getParameter("wardCode");
					vo.setStrWardCode(wardCode);
				    vo.setStrunitproperty(strUnitCombo);
					movDetails = PatientTransferTransHLP
							.getChangeOfUnit(vo);
				}
				formBean.setStrMsgType(vo.getStrMsgType());
				if (formBean.getStrMsgType().equals("1")) {
					vo.setStrErrorCode("2");
					formBean.setStrErrMsgString(vo.getStrErrMsgString());// error
					throw new Exception(formBean.getStrErrMsgString());
				}
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->AjaxResponse()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			vo.setStrErrMsgString(formBean.getStrErrMsgString());
			movDetails = PatientTransferTransHLP.getError(vo);
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
		}
		return movDetails;
	}

	
	
	/*
	 *  IN CASE OF IPD DESK ONLY
	 *  USED TO BRING HLP ON MAIN PAGE
	 */
	public static String AjaxResponseIPD(PatientTransferTransFB formBean,
			String strValmode, HttpServletRequest request) {
		PatientTransferTransVO vo = new PatientTransferTransVO();
		int flg = Integer.parseInt(strValmode);
		String movDetails = "";
		try {
			vo.setStrErrorCode("0");
			if (formBean.getStrMsgType().equals("1")) {
				vo.setStrErrorCode("1");
				throw new Exception(formBean.getStrErrMsgString());
			} else {
				
				String tmp = request.getParameter("currDtl");
				//System.out.println("DATATATATT----->"+tmp);
				String currStat[] = tmp.replace('^', '#').split("#");
				vo = (PatientTransferTransVO) TransferObjectFactory
						.populateData(
								"ipd.transactions.PatientTransferTransVO",
								formBean);
				vo.setStrdeptproperty(formBean.getStrdeptproperty());
				if(currStat.length>=4)
				{
					vo.setStrUnitName("<option>" + currStat[1] + "</option>");
					vo.setStrWard("<option>" + currStat[2] + "</option>");
					vo.setStrRoom("<option>" + currStat[3] + "</option>");
					vo.setStrBed("<option>" + currStat[4] + "</option>");
				}
				else
				{
					vo.setStrUnitName("<option></option>");
					vo.setStrWard("<option></option>");
					vo.setStrRoom("<option></option>");
					vo.setStrBed("<option></option>");
				}
				vo.setStrwardType(formBean.getStrwardType());
				vo.setStrRoomType(formBean.getStrRoomType());
				vo.setStrBedType(formBean.getStrBedType());
				vo.setStrServArea(formBean.getStrServArea());
				vo.setStrPrevDblOcc(formBean.getStrPrevDblOcc());
				vo.setSharableChk(formBean.getSharableChk());
				//System.out.println("SET DATA ---->"+formBean.getStrPrevDblOcc());
				
				
				if (flg == 1) {
					movDetails = PatientTransferTransHLP.getChangeOfWardIPD(vo);
				}
				if (flg == 2) {
					movDetails = PatientTransferTransHLP.getChangeOfBedIPD(vo);
				}
				/*if (flg == 3) {
					movDetails = PatientTransferTransHLP
							.getChangeOfDeptUnit(vo);
				}*/
				
				if(flg == 3)
				{
					movDetails=PatientTransferTransHLP.getChangeOfServiceAreaOT(formBean);
				}
				
				if (flg == 4) {
					movDetails = PatientTransferTransHLP
							.getChangeOfServArea(vo);
				}
				if (flg == 5) {
					movDetails = PatientTransferTransHLP
							.getChangeOfHospital(vo);
				}
				if (flg == 6) {
					String strUnitCombo=unitChangeCombo(formBean,request);
					String wardCode = request.getParameter("wardCode");
					vo.setStrWardCode(wardCode);
				    vo.setStrunitproperty(strUnitCombo);
					movDetails = PatientTransferTransHLP
							.getChangeOfUnit(vo);
				}
				formBean.setStrMsgType(vo.getStrMsgType());
				if (formBean.getStrMsgType().equals("1")) {
					vo.setStrErrorCode("2");
					formBean.setStrErrMsgString(vo.getStrErrMsgString());// error
					throw new Exception(formBean.getStrErrMsgString());
				}
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->AjaxResponse()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			vo.setStrErrMsgString(formBean.getStrErrMsgString());
			movDetails = PatientTransferTransHLP.getError(vo);
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
		}
		return movDetails;
	}

	public static void getRsnRmk(PatientTransferTransFB formBean) {
		PatientTransferTransVO vo = new PatientTransferTransVO();
		PatientTransferTransBO bo = new PatientTransferTransBO();
		HisUtil util=null;
		String strTransferType=""; 
		LinkedHashMap<String , String> mapProcedureParam =null;
		try {
			 StringBuilder sBuffer = new StringBuilder();
			 sBuffer.append("<select name='strLocation12'>");
             mapProcedureParam = new LinkedHashMap<String, String>();
             mapProcedureParam.put("err", "#1");
             mapProcedureParam.put("resultset", "#2");
             sBuffer.append(HisComboOptions.getOptionsFromProc("pkg_ipd_view.Proc_hospital_mst", mapProcedureParam, "", "0^Select Value", false));
             sBuffer.append("</select>");
             formBean.setStrHospCombo(sBuffer.toString());
			 vo = (PatientTransferTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientTransferTransVO", formBean);
			 bo.getRsnRmk(vo);
			 util = new HisUtil("ipd", "PatientTransferTransDATA");
			 strTransferType = util.getOptionValue(vo.getTransferedTypeWs(), "0","0^Select Value", false);
			 formBean.setStrTransferTypeValues(strTransferType);
			 formBean.setStrDisBy(vo.getStrDisBy());
			 formBean.setStrDisRsn(vo.getStrDisRsn());
			//formBean.setStrRmk(vo.getStrRmk());
			 formBean.setStrRsn(vo.getStrRsn());
			 formBean.setStrMsgType(vo.getStrMsgType());
			// String tempStr = MsApprovalTransHLP.ocuupationView(VO);
			 if (formBean.getStrMsgType().equals("1")) {
				formBean.setStrErrMsgString(vo.getStrErrMsgString());// error
				throw new Exception(formBean.getStrErrMsgString());
			}
		} catch (Exception e) {
			 String strmsgText = e.getMessage();
			 HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->getRsnRmk()", strmsgText);
			 formBean.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		 }  finally {
			 bo = null;
			 vo = null;
		}
	}
	

	// /////////////////////////////////////////////////////////////////////////////

	public static void initOnlineReq(String strChk,
			PatientTransferTransFB formBean) {
		PatientTransferTransVO vo = new PatientTransferTransVO();
		vo.setStrChk(strChk);
		vo.setStrHospCode(formBean.getStrHospCode());
		HisUtil util = null;
		IpdConfigUtil ipdConfig = null;
		try {
			ipdConfig = new IpdConfigUtil(formBean.getStrHospCode());
			formBean.setStrCrNo1(strChk);
			formBean.setStrMsgType(vo.getStrMsgType());
			formBean.setStrPvtWardCode(ipdConfig.getStrPrivateWardType());
			formBean.setStrIpdConfIcuWard(ipdConfig.getStrIcuWardType());
			// String tempStr = MsApprovalTransHLP.ocuupationView(VO);
			if (formBean.getStrMsgType().equals("1")) {
				formBean.setStrErrMsgString(vo.getStrErrMsgString());// error
				throw new Exception(formBean.getStrErrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","PatientTransferTransDATA->initOnlineReq()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
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

	/** ********Department Combo********** */
	public static void department(PatientTransferTransFB beanObj) {

		PatientTransferTransVO VO = null;
		HisUtil util = null;
		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			// VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			bo.department(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			// String tempStr = MsApprovalTransHLP.ocuupationView(VO);
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} else {
				util = new HisUtil("ipd", "PatientTransferTransDATA");
				String strdept = util.getOptionValue(VO.getStrDepartmenttWs(),
						"0", "0^Select Value", false);
				// System.out.println("dept..cmb->"+strdept);
				beanObj.setStrdeptproperty(strdept);
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->department()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
	}

	/** **************for Unit Combo********************* */

	public static void unit(PatientTransferTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {
		PatientTransferTransVO VO = null;

		HisUtil util = null;
		String strunit = "";
		// VO.setStrHospitalCode(beanObj.getStrHospitalCode());
		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			String modDept = request.getParameter("modDept");
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			// System.out.println("ward codein
			// cnt=="+VO.getStrprivatewardName());
			VO.setStrDepartment(modDept);
			bo.unit(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			util = new HisUtil("ipd", "PatientTransferTransDATA");
			strunit = util.getOptionValue(VO.getStrUnitWs(), "0",
					"0^Select Value", false);
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} else {
				// System.out.println("unit string:"+strunit);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strunit);
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->unit()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
	}

	public static String unitChangeCombo(PatientTransferTransFB beanObj,
			HttpServletRequest request) {
		PatientTransferTransVO VO = null;

		HisUtil util = null;
		String strunit = "";
		
		try {
			String deptCode = request.getParameter("deptCode");
			String unitCode = request.getParameter("unitCode");
			String wardCode = request.getParameter("wardCode");
			
			
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			VO.setStrUnit(unitCode);
			VO.setStrWard(wardCode);
			VO.setStrDepartment(deptCode);
			bo.unitChangeCombo(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			util = new HisUtil("ipd", "PatientTransferTransDATA");
			strunit = util.getOptionValue(VO.getStrUnitWs(), "0",
					"0^Select Value", false);
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} 
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->unitChangeCombo()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
		return strunit;
	}

	// this function is called for populating ward value on the basis of unit
	// combo

	public static void ward(PatientTransferTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		PatientTransferTransVO VO = new PatientTransferTransVO();
		;
		String modWardTpe = "";
		String temp[] = null;
		try {

			modWardTpe = request.getParameter("modWardTpe");
			temp = modWardTpe.replace('^', '#').split("#");
			// System.out.println("modUnit in ward=="+modWardTpe);
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			// System.out.println("ward codein
			// cnt=="+VO.getStrprivatewardName());
			VO.setStrDeptCode(temp[0]);
			VO.setStrDeptUnitCode(temp[1]);
			VO.setStrWardTypeCode(temp[2]);
			//'&ageCode='+document.forms[0].strAgeUnit.value+'&sexCode='+ document.forms[0].strSexCode.value+'&age='+document.forms[0].strAge.value+'&treatmentCategCode='+document.forms[0].strCategoryCode.value;
			VO.setStrAge(request.getParameter("age"));
			VO.setStrAgeCode(request.getParameter("ageCode"));
			VO.setStrGenderCode(request.getParameter("sexCode"));
			VO.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			VO.setStrCrNo(request.getParameter("crNo"));
			bo.ward(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString()); // error
				throw new Exception(beanObj.getStrErrMsgString());
			} else {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(VO.getStrWard());
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->ward()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
	}
	
	// this function is called for populating room value on the basis of ward
	// combo
	
	public static void room(PatientTransferTransFB beanObj,HttpServletRequest request, HttpServletResponse response) {

		PatientTransferTransVO VO = null;
		String modRoomType = request.getParameter("modRoomType");
		String temp[] = modRoomType.replace('^', '#').split("#");
		// System.out.println("modRoomType=="+modRoomType);
		// VO.setStrHospitalCode(beanObj.getStrHospitalCode());
		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			// System.out.println("ward codein
			// cnt=="+VO.getStrprivatewardName());
			VO.setStrWardCode(temp[0]);
			VO.setStrRoomTypeCode(temp[1]);
			VO.setStrAge(request.getParameter("age"));
			VO.setStrAgeCode(request.getParameter("ageCode"));
			VO.setStrGenderCode(request.getParameter("sexCode"));
			VO.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			VO.setStrCrNo(request.getParameter("crNo"));
			VO.setStrDeptUnitCode(request.getParameter("strDeptUnitCode"));
			VO.setStrDeptCode(request.getParameter("strDeptCode"));
			if (temp.length > 2) {
				//System.out.println("temp3"+temp[3]);
				//if (temp[3].equals("11")) {
				/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
				IpdConfigUtil ipdC=new IpdConfigUtil(VO.getStrHospCode());
				if (temp[3].equals(ipdC.getStrPrivateWardType())) {
					VO.setStrMsApprovalFlag(temp[2]);
					VO.setStrWardTypeCode(temp[3]);
					VO.setStrCrNo(temp[4]);
					VO.setStrAdviceAdmNo(temp[5]);
				}
			}
			bo.room(VO);
			beanObj.setStrRoomCode(VO.getStrRoomCode());
			beanObj.setStrMsgType(VO.getStrMsgType());
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} else {
				response.setHeader("Cache-Control", "no-cache");
				// System.out.println("response str->"+"<option selected
				// value="+VO.getStrRoomCode()+">"+VO.getStrRoom()+"</option>");
				//System.out.println("temp3"+temp[3]);
				//if (temp[3].equals("11")) {
				/* Changed By Amit Kumar Ateria on 24 Jan 2011 to check MS Approval for Private Ward*/
				IpdConfigUtil ipdC=new IpdConfigUtil(VO.getStrHospCode());
				if (VO.getStrWardTypeCode().equals(ipdC.getStrPrivateWardType()) && VO.getStrMsApprovalFlag().equals("1") )
				/*if (VO.getStrWardTypeCode().equals("11")
						&& VO.getStrMsApprovalFlag().equals("1")) */
				{
					beanObj.setStrMSBed(VO.getStrBedCode());
					if ((VO.getStrMsApprovalStatus().equals("6") || VO
							.getStrMsApprovalStatus().equals("3"))
							&& !VO.getStrRoomCode().equals(""))
						response.getWriter().print(
								"<option selected value=" + VO.getStrRoomCode()
										+ "^" + VO.getStrBedCode() + ">"
										+ VO.getStrRoom() + "</option>");
					else {
						// VO.setStrCmbRed("1");
						response.getWriter().print(
								"<option selected value='0'>N/A</option>");
					}
				} else
					response.getWriter().print(VO.getStrRoom()); // beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->room()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
	}
	public static void roomCombo(PatientTransferTransFB beanObj,HttpServletRequest request, HttpServletResponse response) {
		PatientTransferTransVO VO = null;

		HisUtil util = null;
		String strroom = "";
		
		try {
			String deptCode = request.getParameter("deptCode");
			String unitCode = request.getParameter("unitCode");
			String wardCode = request.getParameter("wardCode");
			
			
			
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			VO.setStrUnit(unitCode);
			VO.setStrWard(wardCode);
			VO.setStrDepartment(deptCode);
			VO.setStrAge(request.getParameter("age"));
			VO.setStrAgeCode(request.getParameter("ageUnit"));
			VO.setStrGenderCode(request.getParameter("gender"));
			VO.setStrTreatmentCategoryCode(request.getParameter("category"));
			bo.roomCombo(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			util = new HisUtil("ipd", "PatientTransferTransDATA");
			strroom = util.getOptionValue(VO.getStrRoomWs(), "0",
					"0^Select Value", false);
			
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} 
			else
			{
				
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strroom);
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->roomCombo()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
		
		
		
	}
	/** **********bed value******************* */

	public static void bed(PatientTransferTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		PatientTransferTransVO VO = null;
		String modBedType = request.getParameter("modBedType");
		String shr_chk = request.getParameter("shr_chk");
		String temp[] = modBedType.replace('^', '#').split("#");
		// System.out.println("modBedType=="+modBedType);
		// VO.setStrHospitalCode(beanObj.getStrHospitalCode());
		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			// System.out.println("ward codein
			// cnt=="+VO.getStrprivatewardName());
			VO.setStrWardCode(temp[0]);
			VO.setStrRoomCode(temp[1]);
			VO.setStrBedTypeCode(temp[2]);
			VO.setStrDeptUnitCode(temp[3]);
			VO.setSharableChk(shr_chk);
			bo.bed(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			if (beanObj.getStrMsgType().equals("1")) 
			{
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} 
			else 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(VO.getStrBed());
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","PatientTransferTransDATA->bed()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
	}

	public static void servRoom(PatientTransferTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		PatientTransferTransVO VO = null;
		String modServCode = request.getParameter("modServCode");
		// VO.setStrHospitalCode(beanObj.getStrHospitalCode());
		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			// System.out.println("ward codein
			// cnt=="+VO.getStrprivatewardName());
			VO.setStrServAreaCode(modServCode);
			bo.servRoom(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} else {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(VO.getStrRoom());
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->servRoom()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
	}

	public static void consltntID(PatientTransferTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		PatientTransferTransVO VO = null;
		String modconsltntID = request.getParameter("modConsltntID");
		// String strDeptCode= request.getParameter("modConsltntID");
		// System.out.println("modconsltntID->"+modconsltntID);
		String temp[] = modconsltntID.replace('^', '#').split("#");
		try {
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new PatientTransferTransVO();
			VO = (PatientTransferTransVO) TransferObjectFactory.populateData(
					"ipd.transactions.PatientTransferTransVO", beanObj);
			PatientTransferTransBO bo = new PatientTransferTransBO();
			VO.setStrCrNo(temp[0]);
			VO.setStrDeptCode(temp[1]);
			VO.setStrDeptUnitCode(temp[2]);
			bo.consltntID(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());// error
				throw new Exception(beanObj.getStrErrMsgString());
			} else {
				// System.out.println("modconsltntID
				// string->"+VO.getStrConsultantCode());
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(VO.getStrConsultantCode());
				// beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"PatientTransferTransDATA->consltntID()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (beanObj != null)
				beanObj = null;
		}
	}

	/*
	 * public static void beddetail(PatientTransferTransFB
	 * formBean,HttpServletRequest request,HttpServletResponse response) {
	 * 
	 * System.out.println("inside DATA-->beddetail");
	 * 
	 * PatientTransferTransVO VO=new PatientTransferTransVO();
	 * PatientTransferTransBO BO=new PatientTransferTransBO(); try { VO =
	 * (PatientTransferTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientTransferTransVO",formBean);
	 * String tmp=request.getParameter("modPopUp"); String
	 * temp[]=tmp.replace('^','#').split("#"); VO.setStrWardCode(temp[0]);
	 * VO.setStrRoomCode(temp[1]); VO.setStrBedTypeCode(temp[2]);
	 * VO.setStrDeptUnitCode(temp[3]); VO.setStrPopUp("1");
	 * BO.setBedDetails(VO); String
	 * strbed=PatientTransferTransHLP.getBedDetails(VO);
	 * System.out.println("strbed String in data=="+strbed);
	 * formBean.setStrBedProperty(strbed);
	 * formBean.setStrMsgType(VO.getStrMsgType());
	 * if(formBean.getStrMsgType().equals("1")) { //error throw new
	 * Exception(formBean.getStrErrMsgString()); } } catch(Exception e) { String
	 * strmsgText = e.getMessage(); HisException eObj = new HisException("IPD",
	 * "PatientTransferTransDATA->beddetail()", strmsgText);
	 * formBean.setStrErrMsgString("Application Error [ERROR ID : " +
	 * eObj.getErrorID() + "], Contact System Administrator! "); eObj = null; }
	 * finally { if(VO != null) VO = null; if(formBean != null) formBean = null; } }
	 */

	/** **********INIT All Combos ****************** */

	public static void initBedStatus(PatientTransferTransFB formBean,
			HttpServletRequest request) {
		PatientTransferTransVO vo = new PatientTransferTransVO();
		PatientTransferTransBO bo = new PatientTransferTransBO();

		try 
		{
			vo = (PatientTransferTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientTransferTransVO", formBean);
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrWardCode(request.getParameter("wardCode"));
			vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
			vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrCrNo(request.getParameter("crNo"));
			bo.setBedStatusDtl(vo);
			formBean.setStrwardType(vo.getStrwardType());
			formBean.setStrRoomType(vo.getStrRoomType());
			formBean.setStrBedType(vo.getStrBedType());
			formBean.setStrServArea(vo.getStrServArea());
			formBean.setStrMsgType(vo.getStrMsgType());
			if (formBean.getStrMsgType().equals("1")) {
				formBean.setStrErrMsgString(vo.getStrErrMsgString());// error
				throw new Exception(formBean.getStrErrMsgString());
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","PatientTransferTransDATA->initBedStatus()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	public static boolean admstat(PatientTransferTransFB formBean) {
		PatientTransferTransVO vo = new PatientTransferTransVO();
		String isDead = "";
		boolean retVal = false;
		IpdConfigUtil ipdC=new IpdConfigUtil(formBean.getStrHospCode());
		try {
			vo = (PatientTransferTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientTransferTransVO", formBean);
			vo.setStrCrNo(formBean.getStrCrNo());
			PatientTransferTransBO.admstat(vo);
			//ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
			//formBean.setStrIssuedItemRequired(resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED"));
			formBean.setStrIssuedItemRequired(ipdC.getStrIssueItemRequired());
			formBean.setStrBelongingRequired(ipdC.getStrBelongingRequired());
			formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
			isDead = vo.getStrIsDead();
			formBean.setStrIsDead(vo.getStrIsDead());
			formBean.setStrPrevDblOcc(vo.getStrPrevDblOcc());
			formBean.setStrHoldRoom(vo.getStrHoldRoom());
			formBean.setStrConsultant(vo.getStrConsultant());
			formBean.setStrMsgType(vo.getStrMsgType());
			if (formBean.getStrMsgType().equals("1")) {
				formBean.setStrErrMsgString(vo.getStrErrMsgString());// error
				throw new Exception(formBean.getStrErrMsgString());
			}
			// vo.setStrAdmStatCode(admitted);//hard code...delete after
			// isDead="1";//hard code...delete after
			if(vo.getStrAdmStatCode().equals("12")) {
				// System.out.println("isDead->"+isDead);
				if (isDead.equals("1")) {
					formBean.setStrErrMsgString("Patient is Dead!!");
					formBean.setStrCrNo("");
					retVal = false;
				} else
					retVal = true;
			} else {
				formBean.setStrErrMsgString("Patient Not Admitted!!");
				formBean.setStrCrNo("");
				retVal = false;
			}

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","PatientTransferTransDATA->admstat()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		return retVal;
	}

	public static void insert(PatientTransferTransFB formBean) {
		String strInsert = formBean.getStrInsert();
		boolean retVal = true;
		try {
			PatientTransferTransVO vo = new PatientTransferTransVO();
			String tmp[] = strInsert.replace('^', '#').split("#");
			vo = (PatientTransferTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientTransferTransVO", formBean);
			vo.setStrCrNo(tmp[0]);
			vo.setStrDeptCode(tmp[1]);
			vo.setStrDeptUnitCode(tmp[2]);
			vo.setStrWardCode(tmp[3]);
			vo.setStrRoomCode(tmp[4]);
			vo.setStrBedCode(tmp[5]);
			vo.setStrRsn(tmp[6]);
			vo.setStrEntryDate(tmp[7]);
			vo.setStrOldWardCode(tmp[8]);
			vo.setStrOldDeptUnitCode(formBean.getCurDept_Unt_RomCode().replace("^", "#").split("#")[1]);
			vo.setStrAdmNo(formBean.getCurAdmNo());
			vo.setStrOldRoomCode(tmp[9]);
			vo.setStrOldBedCode(tmp[10]);
			vo.setStrAdviceAdmNo(tmp[11]);
			vo.setStrTransFlg(tmp[12]);
			if (tmp[12].equals("7") && tmp.length > 13) {
				//vo.setStrServAreaCode(tmp[13]);
				vo.setStrRmk(tmp[13]);
			} else if (tmp[12].equals("8") && tmp.length > 13) {
				vo.setStrLocation(tmp[13]);
				vo.setStrRmk(tmp[14]);
			} else {
				vo.setStrRmk(tmp[13]);
			}
			vo.setStrTransferTypeBelonging(formBean.getStrTransferTypeBelonging());
			vo.setStrTransferTypeIssue(formBean.getStrTransferTypeIssue());
			retVal = PatientTransferTransBO.insert(vo);
			formBean.setStrMsgType(vo.getStrMsgType());
			if (retVal == true) {
				formBean.setStrNormalMsgString("Record Successfully Saved");
				formBean.setStrCrNo("");
			} else {
				if (formBean.getStrMsgType().equals("1")) { // error
					formBean.setStrErrMsgString(vo.getStrErrMsgString());
					throw new Exception(formBean.getStrErrMsgString());
				} else {
					formBean.setStrErrMsgString("Error while Saving Record!!");
					formBean.setStrCrNo("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",	"PatientTransferTransDATA->insert()", strmsgText);
			formBean.setStrErrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}

	}
	
	
	//to get service type combo 
	public static void getServiceType(PatientTransferTransFB formBean,HttpServletRequest request, HttpServletResponse response) {
		
		PatientTransferTransVO voObj= null;
		PatientTransferTransBO bo= null;
			HisUtil util = null;
			String temp="";
			try 
			 {
				voObj =  new PatientTransferTransVO();
				bo    =  new PatientTransferTransBO();
				
				voObj.setStrHospCode(formBean.getStrHospCode());
				bo.getServiceType(voObj);
				util = new HisUtil("Patient Transfer Transaction","PatientTransferTransDATA");
				temp = util.getOptionValue(voObj.getStrServiceTypeWS(), "0","0^Select Value", false);
				formBean.setStrServiceType(temp);
			
				if(voObj.getStrMsgType().equals("1"))
				{
						throw new Exception(voObj.getStrMsgString());
				}
							
			} 
			catch (Exception e) {
					e.printStackTrace();
					String strmsgText = e.getMessage();
				    HisException eObj = new HisException("IPD", "PatientTransferTransDATA->getServiceType()", strmsgText);
				    formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				    eObj = null;
				     
			}
		}
	
	
	public static void getServiceName(PatientTransferTransFB formBean,HttpServletRequest request, HttpServletResponse response) {
		
		PatientTransferTransVO voObj= null;
		PatientTransferTransBO bo= null;
			HisUtil util = null;
			String temp="";
			try 
			 {
				voObj =  new PatientTransferTransVO();
				bo    =  new PatientTransferTransBO();
				
				voObj.setStrHospCode(formBean.getStrHospCode());
				voObj.setStrServiceTypeId(formBean.getStrServiceTypeId());
				bo.getServiceName(voObj);
				String strBedVacantMode=voObj.getStrBedVacantMode();
				
				util = new HisUtil("Patient Transfer Transaction",
									"PatientTransferTransDATA");
				temp = util.getOptionValue(voObj.getStrServiceNameWS(), "0",
							"0^Select Value", false);
					
				temp=temp.concat("@").concat(strBedVacantMode);
					
					System.out.println("vacant mode "+temp);
					if(voObj.getStrMsgType().equals("1"))
					{
						throw new Exception(voObj.getStrMsgString());
					}
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(temp);
							
			} 
			catch (Exception e) {
					e.printStackTrace();
					String strmsgText = e.getMessage();
				   HisException eObj = new HisException("IPD", "PatientTransferTransDATA->getServiceName()", strmsgText);
				   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				     eObj = null;
				     
			}
		}
	
	
	
  public static void serviceValidation(PatientTransferTransFB formBean,HttpServletRequest request, HttpServletResponse response) {
		
		PatientTransferTransVO voObj= null;
		PatientTransferTransBO bo= null;
			HisUtil util = null;
			String temp="";
			String strServiceName = request.getParameter("strServiceName");
			String strServiceVal=strServiceName.replace("^", "@").split("@")[0];
			String strServiceDept=strServiceName.replace("^", "@").split("@")[1];
			String strServiceUnit=strServiceName.replace("^", "@").split("@")[2];
			try 
			 {
				voObj =  new PatientTransferTransVO();
				bo    =  new PatientTransferTransBO();
				String strCrNo = request.getParameter("strCrNo");
				voObj.setStrCrNo(strCrNo);
				String serviceTypeId=request.getParameter("serviceTypeId");
				formBean.setStrServiceTypeId(serviceTypeId);
				voObj.setStrHospCode(formBean.getStrHospCode());
				voObj.setStrServiceTypeId(formBean.getStrServiceTypeId());
				voObj.setStrServiceName(strServiceVal);
				voObj.setStrServiceDeptCode(strServiceDept);
				voObj.setStrServiceUnitCode(strServiceUnit);
				bo.serviceValidation(voObj);
				String strValidatedFlag=voObj.getStrValidatedFlag();
				
					if(voObj.getStrMsgType().equals("1"))
					{
						throw new Exception(voObj.getStrMsgString());
					}
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strValidatedFlag);
							
			} 
			catch (Exception e) {
					e.printStackTrace();
					String strmsgText = e.getMessage();
				   HisException eObj = new HisException("IPD", "PatientTransferTransDATA->getServiceName()", strmsgText);
				   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				     eObj = null;
				     
			}
		}
	

}
