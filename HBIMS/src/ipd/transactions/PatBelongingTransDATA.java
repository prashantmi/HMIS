package ipd.transactions;

import ipd.IpdConfigUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.controller.HisComboOptions;

public class PatBelongingTransDATA {

	/**
	 * This function is used to check the patient is admitted or dead on 
	 * main page
	 * 
	 * @param formBean
	 */
	public static boolean patAdmStatus(PatBelongingTransFB formBean,HttpServletRequest request) {

		PatBelongingTransVO voObj = new PatBelongingTransVO();
		PatBelongingTransBO bo = new PatBelongingTransBO();
		boolean retVal=false;
		String strmsgText = "";
		LinkedHashMap<String, String> mapParam = null;
		IpdConfigUtil ipdC=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		try {
			mapParam = new LinkedHashMap<String, String>();
			mapParam.put("modeval", "1");
			mapParam.put("hosp_code", formBean.getStrHospitalCode());
			mapParam.put("item_type", "2");//2 for Belonging ,1 for Issued Items
			mapParam.put("seatid", formBean.getStrSeatId());
			mapParam.put("err", "#1");
			mapParam.put("resultset", "#2");
			formBean.setStrBelongingItemValues(HisComboOptions.getOptionsFromProc("pkg_ipd_view.proc_hipt_ward_item_mst", mapParam, "0", "0^Select Value", false));
			//ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
			//For Belonging
			//if(resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED").equals("1")){
			if(ipdC.getStrIssueItemRequired().equals("1"))
			{
				mapParam.remove("item_type");
				mapParam.put("item_type", "1");
				formBean.setStrIssuedItemValues(HisComboOptions.getOptionsFromProc("pkg_ipd_view.proc_hipt_ward_item_mst", mapParam, "0", "0^Select Value", false));
			}
			//formBean.setStrIssuedItemRequired(resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED"));
			if(request.getParameter("belMode")!=null && request.getParameter("belMode").equals("1"))//Belonging
			{
				voObj.setStrBelMode("1");
				formBean.setStrBelMode("1");
				formBean.setStrIssuedItemRequired("0");
				formBean.setStrBelongingRequired(ipdC.getStrBelongingRequired());
			}
			if(request.getParameter("belMode")!=null && request.getParameter("belMode").equals("2"))//Issued Items
			{
				voObj.setStrBelMode("2");
				formBean.setStrBelMode("2");
				formBean.setStrIssuedItemRequired(ipdC.getStrIssueItemRequired());
				formBean.setStrBelongingRequired("0");
			}
			String strPatientBlngdtl = "";
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrValue(formBean.getStrValue());
			
			if(formBean.getStrValue().equals("0"))//Add
				formBean.setStrValueLable("Received");
			else if(formBean.getStrValue().equals("1"))//Return
				formBean.setStrValueLable("Return");
			
			bo.setPatAdmStatus(voObj);
			
			if(voObj.getStrInvalidCrNo().equals("1"))
			{
				formBean.setStrErrMsg("Invalid CR No/Data not found");
				retVal=false;
			}
			else
			{
				formBean.setStrPatAdmCode(voObj.getStrPatAdmCode());
				formBean.setStrIsDead(voObj.getStrIsDead());
				//voObj.setStrPatAdmCode("12");//delete
				//voObj.setStrIsDead("1");//delete
				if(voObj.getStrValue().equals("0"))//Belonging Add
				{
					if(voObj.getStrPatAdmCode().equals("12"))
					{
						if(voObj.getStrIsDead().equals("1"))
						{
							formBean.setStrErrMsg("Patient is Dead");
							retVal=false;
						}else{
							retVal=true;
						 	}
					}else{
						formBean.setStrErrMsg("Patient Not Admited");
						retVal=false;
					}
					//strPatientBlngdtl = hisglobal.tools.hlp.PatientDtlHLP.patientDtl(voObj.getStrCrNo(), false);
					voObj.setStrAdmnNo(formBean.getStrAdmnNo());
					if(formBean.getStrBelMode().equals("1") && ipdC.getStrBelongingRequired().equals("1"))//Belonging
					{
						formBean.setPatBelongingDetail(PatBelongingTransHLP.getPatBelongingDetailRow(voObj));
					}
					if(formBean.getStrBelMode().equals("2") && ipdC.getStrIssueItemRequired().equals("1"))//Issued Items
					{
						formBean.setStrPatIssuedItemDtl(PatBelongingTransHLP.getPatIssuedItemDetailRow(voObj));
					}				
			}
			else//Belonging Return
			{
				strPatientBlngdtl = hisglobal.tools.hlp.PatientDtlHLP.patientDtl(voObj.getStrCrNo(), false);
				formBean.setStrPatientBlngdtl(strPatientBlngdtl);
				voObj.setStrBelMode(formBean.getStrBelMode());				
				//formBean.setPatBelongingDetail(PatBelongingTransHLP.getPatBelongingDetail(voObj));
				formBean.setStrAdmnNo(voObj.getStrAdmnNo());
				formBean.setStrAdmnDate(voObj.getStrAdmnDate());
				formBean.setStrWardCode(voObj.getStrWardCode());
				formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
				formBean.setStrRoomBed(voObj.getStrRoomBed());
				retVal = true;
				}
			}
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatBelongingTransDATA->patAdmStatus()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
			
		} finally {
			if (voObj != null) {
				voObj = null;
			}
			if (bo != null) {
				bo = null;
			}
		}
		return retVal;
	}
	
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void detail(PatBelongingTransFB formBean) {

		PatBelongingTransVO voObj = new PatBelongingTransVO();
		PatBelongingTransBO bo = new PatBelongingTransBO();
		WardDueIPDTransVO wardDueIPDTransVO = null;
		String strmsgText = "";
		try {
			wardDueIPDTransVO = new WardDueIPDTransVO();
			String strPatientBlngdtl = "";
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrValue(formBean.getStrValue());

			if (voObj.getStrValue().equals("0")) {
				strPatientBlngdtl = hisglobal.tools.hlp.PatientDtlHLP.patientDtl(voObj.getStrCrNo(), false);
				formBean.setStrPatientBlngdtl(strPatientBlngdtl);
				
				bo.setPatientblngDtl(voObj);
				formBean.setStrAdmnNo(voObj.getStrAdmnNo());
				formBean.setStrAdmnDate(voObj.getStrAdmnDate());
				formBean.setStrWardCode(voObj.getStrWardCode());
				formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
				formBean.setStrRoomBed(voObj.getStrRoomBed());
			} else {
				strPatientBlngdtl = hisglobal.tools.hlp.PatientDtlHLP.patientDtl(voObj.getStrCrNo(), false);
				formBean.setStrPatientBlngdtl(strPatientBlngdtl);
				
				wardDueIPDTransVO.setStrHospCode(formBean.getStrHospitalCode());
				wardDueIPDTransVO.setStrAdmNo(formBean.getStrAdmnNo());
				wardDueIPDTransVO.setStrDeptUnit(formBean.getStrDeptUnitCode());
				wardDueIPDTransVO.setStrWard(formBean.getStrWardCode());
				wardDueIPDTransVO.setStrBelMode(formBean.getStrBelMode());
				formBean.setPatBelongingDetail(WardDueIPDTransHLP.getPatientDueList(wardDueIPDTransVO));
				formBean.setStrAdmnNo(voObj.getStrAdmnNo());
				formBean.setStrAdmnDate(voObj.getStrAdmnDate());
				formBean.setStrWardCode(voObj.getStrWardCode());
				formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
				formBean.setStrRoomBed(voObj.getStrRoomBed());
			}

		} catch (Exception e) {
			strmsgText = e.getMessage();
			
			HisException eObj = new HisException("IPD", "PatBelongingTransDATA->detail()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
			formBean.setStrCrNo("");
		} finally {
			if (voObj != null) {
				voObj = null;
			}
		}
	}

	/**
	 * This function invoke TransferObjectFactory.populateData() to transfer the
	 * values of all attributes of form bean into vo and the invoke bo insert
	 * method
	 * 
	 * @param formBean
	 */
	public static void insert(PatBelongingTransFB formBean) {

		PatBelongingTransVO vo = (PatBelongingTransVO) TransferObjectFactory
				.populateData("ipd.transactions.PatBelongingTransVO", formBean);
		PatBelongingTransBO bo = new PatBelongingTransBO();
		
		String strmsgText = "";
		try {
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			bo.insert(vo);
			if (vo.getStrMsgType().equals("0")) {
				formBean.setStrNormalMsg("Record Saved Successfully");
			}
			else
			{
				throw new Exception(formBean.getStrMsgString());
			}
		} catch (Exception e) {
			
			strmsgText = e.getMessage();
			e.printStackTrace();
			HisException eObj = new HisException("IPD", "PatBelongingTransDATA->insert()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			formBean.setStrCrNo("");
			eObj = null;
    	} finally {
			if (vo != null) {
				vo = null;
			}
		}
	}

	/**
	 * This function invoke TransferObjectFactory.populateData() to transfer the
	 * values of all attributes of form bean into vo and the invoke bo insert
	 * method
	 * 
	 * @param formBean
	 */
	public static void update(PatBelongingTransFB _PatBelongingTransFB) {
		
		WardDueIPDTransVO wardDueIPDTransVO = null;
		WardDueIPDTransBO wardDueIPDTransBO = null;
		try {
			wardDueIPDTransVO = new WardDueIPDTransVO();
			wardDueIPDTransBO = new WardDueIPDTransBO();
			/*vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrchkvisit(formBean.getStrchkvisit());
			vo.setStrItemReturnDateU(formBean.getStrItemReturnDateU());
			vo.setStrItemReturnToU(formBean.getStrItemReturnToU());
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrWardCode(formBean.getStrWardCode());
			vo.setStrDeptUnitCode(formBean.getStrDeptUnitCode());
			vo.setStrItemNameU(formBean.getStrItemNameU());
			vo.setStrRelation(formBean.getStrRelation());
			vo.setStrslno(formBean.getStrslno());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrReturnRmks(formBean.getStrReturnRmks());
			bo.update(vo);*/
			String[] strTmpSlNo = new String[_PatBelongingTransFB.getStrChkItem().length];
			String[] strTmpItemType = new String[_PatBelongingTransFB.getStrChkItem().length];
			for (int nTmpI = 0; nTmpI < _PatBelongingTransFB.getStrChkItem().length; nTmpI++) 
			{
				strTmpSlNo[nTmpI] = _PatBelongingTransFB.getStrChkItem()[nTmpI].replace("^", "#").split("#")[0];
				strTmpItemType[nTmpI] = _PatBelongingTransFB.getStrChkItem()[nTmpI].replace("^", "#").split("#")[1];
			}
			wardDueIPDTransVO.setStrSlno(strTmpSlNo);
			wardDueIPDTransVO.setStrItemTypes(strTmpItemType);
			wardDueIPDTransVO.setStrItemReturnDate(_PatBelongingTransFB.getStrItemReturnDate());
			wardDueIPDTransVO.setStrItemReturnTo(_PatBelongingTransFB.getStrItemReturnTo());
			wardDueIPDTransVO.setStrRelation(_PatBelongingTransFB.getStrRelation());
			wardDueIPDTransVO.setStrStatus(_PatBelongingTransFB.getStrStatus());
			wardDueIPDTransVO.setStrSeatId(_PatBelongingTransFB.getStrSeatId());
			wardDueIPDTransVO.setStrHospCode(_PatBelongingTransFB.getStrHospitalCode());
			wardDueIPDTransVO.setStrAdmNo(_PatBelongingTransFB.getStrAdmnNo());
			wardDueIPDTransVO.setStrDeptUnit(_PatBelongingTransFB.getStrDeptUnitCode());
			wardDueIPDTransVO.setStrWard(_PatBelongingTransFB.getStrWardCode());
			wardDueIPDTransBO.save(wardDueIPDTransVO);
			if (wardDueIPDTransVO.getStrMsgType().equals("1"))
				throw new Exception(_PatBelongingTransFB.getStrMsgString());
			
			_PatBelongingTransFB.setStrNormalMsg("Record Update Successfully");
		} catch (Exception _Err) {
			HisException eObj = new HisException("IPD", "PatBelongingTransDATA->update()", _Err.getMessage());
			_PatBelongingTransFB.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			_PatBelongingTransFB.setStrCrNo("");
			eObj = null;
		} finally {
			wardDueIPDTransVO = null;
		}
	}
}
