package ipd.transactions;

import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.ResourceBundle;

import ipd.IpdConfigUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NursingDeskTransDATA {

	public static void department(NursingDeskTransFB beanObj)
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		HisUtil util = null;
		LinkedHashMap<String, String> mapParam = null;
		IpdConfigUtil ipdC=new IpdConfigUtil(beanObj.getStrHospitalCode());
		try {
			mapParam = new LinkedHashMap<String, String>();
			mapParam.put("modeval", "1");
			mapParam.put("hosp_code", beanObj.getStrHospitalCode());
			mapParam.put("item_type", "2");
			mapParam.put("seatid", beanObj.getStrSeatId());
			mapParam.put("err", "#1");
			mapParam.put("resultset", "#2");
			beanObj.setStrBelongingItemValues(HisComboOptions.getOptionsFromProc("pkg_ipd_view.proc_hipt_ward_item_mst", mapParam, "0", "0^Select Value", false));
			/*ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
			//if(resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED").equals("1")){*/
			if(ipdC.getStrIssueItemRequired().equals("1")){
				mapParam.remove("item_type");
				mapParam.put("item_type", "1");
				beanObj.setStrIssuedItemValues(HisComboOptions.getOptionsFromProc("pkg_ipd_view.proc_hipt_ward_item_mst", mapParam, "0", "0^Select Value", false));
			}
			
			//beanObj.setStrNursCheckListMandatoryFlag(IpdTransConfig.getMandatoryNursCheckListAcceptance());
			/* Changes done by Amit Kumar Ateria 
			 * for Fetching values from In Patient Config Mst instead of property file*/
			//beanObj.setStrIssuedItemRequired(resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED"));
			//beanObj.setStrRemarksMandatoryFlag(resourceBundle.getString("BELONGING_REMARKS_MANDATORY"));
			beanObj.setStrIssuedItemRequired(ipdC.getStrIssueItemRequired());
			beanObj.setStrBelongingRequired(ipdC.getStrBelongingRequired());
			beanObj.setStrNursCheckListMandatoryFlag(ipdC.getStrNurseChecklistMandatory());
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();

			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrSeatId(beanObj.getStrSeatId());
			bo.department(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}

			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			} 
			else 
			{
				util = new HisUtil("ipd", "NursingDeskTransDATA");
				String strdept = util.getOptionValue(VO.getStrDepartmenttWs(),beanObj.getStrDepartment(), "0^Select Value", false);
				beanObj.setStrdeptproperty(strdept);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.GO()", VO.getStrMsgString());
			beanObj.setStrErrorMsg("Application Error [ERROR ID : "	+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (util != null)
				util = null;
		}
	}

	public static void unit(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		String modDept = request.getParameter("modDept");

		HisUtil util = null;
		String strunit = "";

		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();

			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrSeatId(beanObj.getStrSeatId());
			if (modDept != null)
				beanObj.setStrDepartment(modDept);

			VO.setStrDepartment(beanObj.getStrDepartment());

			beanObj.setStrDepartment(modDept);
			bo.unit(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			util = new HisUtil("ipd", "NursingDeskTransDATA");
			strunit = util.getOptionValue(VO.getStrUnitWs(), beanObj
					.getStrUnit(), "0^Select Value", false);

			if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			} 
			else 
			{
				response.setHeader("Cache-Control", "no-cache");
				beanObj.setStrunitproperty(strunit);
				VO.setStrunitproperty(strunit);
			}
		}
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.unit()", VO.getStrMsgString());
			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
			} 
			catch (Exception e1) 
			{				
			}

			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (util != null)
				util = null;

		}
	}
	public static void ward(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) {

		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		String modUnit = request.getParameter("modUnit");

		HisUtil util = null;
		String strward = "";

		try 
		{
			VO = new NursingDeskTransVO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			bo = new NursingDeskTransBO();
			if (modUnit != null)
				beanObj.setStrUnit(modUnit);
			VO.setStrUnit(beanObj.getStrUnit());

			bo.ward(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			util = new HisUtil("ipd", "NursingDeskTransDATA");
			strward = util.getOptionValue(VO.getStrWardWs(), beanObj
					.getStrWard(), "0^Select Value", false);

			if (beanObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(beanObj.getStrMsgString());
			} 
			else 
			{
				response.setHeader("Cache-Control", "no-cache");
				beanObj.setStrwardproperty(strward);
			}
		}
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.ward()-->", VO.getStrMsgString());
			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
			} 
			catch (Exception e1) 
			{
			}
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (util != null)
				util = null;
		}
	}
	public static void room(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		String modWard = request.getParameter("modWard");
		HisUtil util = null;
		String strroom = "";

		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrSeatId(beanObj.getStrSeatId());
			if (modWard != null)
				beanObj.setStrWard(modWard);
			VO.setStrWard(beanObj.getStrWard());
			bo.room(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			util = new HisUtil("ipd", "NursingDeskTransDATA");
			strroom = util.getOptionValue(VO.getStrRoomWs(), beanObj
					.getStrRoom(), "0^Select Value", false);
			
			if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			} 
			else 
			{
				response.setHeader("Cache-Control", "no-cache");
				beanObj.setStrroomproperty(strroom);
			}
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.room() --> -->", VO.getStrMsgString());
			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
			} 
			catch (Exception e1) 
			{
			}
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (util != null)
				util = null;
		}
	}
	public static void admitlist(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		String modDept = request.getParameter("modDept");
		String modUnit = request.getParameter("modUnit");
		String modWard = request.getParameter("modWard");
		String modRoom = request.getParameter("modRoom");
		
		

		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			
			if (modDept != null)
				beanObj.setStrDepartment(modDept);
			if (modUnit != null)
				beanObj.setStrUnit(modUnit);
			if (modWard != null)
				beanObj.setStrWard(modWard);
			if (modRoom != null)
				beanObj.setStrRoom(modRoom);
			VO.setStrIsCancelMode(beanObj.getStrIsCancelMode());
			if(VO.getStrIsCancelMode()!=null && VO.getStrIsCancelMode().equals("CANCEL")||	beanObj.getStrhtransinflag().equals("1"))
				VO.setStrAdmNo(request.getParameterValues("chk")[0].replace("@", "#").split("#")[1]);
			VO.setStrDepartment(beanObj.getStrDepartment());
			VO.setStrWard(beanObj.getStrWard());
			VO.setStrRoom(beanObj.getStrRoom());
			VO.setStrCrNo(beanObj.getStrCrNo());
			VO.setStrUnit(beanObj.getStrUnit());
			VO.setSharableChk(beanObj.getSharableChk());
			
			VO.setStrhtransinflag(beanObj.getStrhtransinflag());
			VO.setStrWadType(beanObj.getStrWadType());
			
			VO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			VO.setStrAdmNo(request.getParameter("chk").replace("@", "#").split("#")[1]);
			//get units present in current department & ward
			bo.unitCombo(VO);
			beanObj.setBillcount(VO.getBillcount());

			/*HisUtil util = new HisUtil("ipd", "NursingDeskTransDATA");
			String strunit = util.getOptionValue(VO.getStrUnitWs(), "", "0^Select Value", false);
			//VO.setStrUnit(beanObj.getStrUnit());
			beanObj.setStrunitproperty(strunit);
			VO.setStrunitproperty(beanObj.getStrunitproperty());*/
			//bo.admitedbed(VO);
			if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			//bo.beddetailcombo(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}

			String stradmitlist = NursingDeskTransHLP.getAdmitDetail(VO);
			beanObj.setStrAdmitDetailProperty(stradmitlist);
			beanObj.setDepartmentName(VO.getDepartmentName());
			beanObj.setWardName(VO.getWardName());
			beanObj.setStrPreviousOccupiedbed(VO.getStrPreviousOccupiedbed());
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.admitlist() --> -->", VO.getStrMsgString());
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	
	/* 
	 * Method Used To Patient Details From DAO
	 * For IPD desk   
	 */
	
	public static void admitlistIPD(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;

		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			
			
			VO.setStrDepartment(beanObj.getStrDepartment());
			VO.setStrWard(beanObj.getStrWard());
			VO.setStrRoom(beanObj.getStrRoom());
			VO.setStrCrNo(beanObj.getStrCrNo());
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrAdmNo(beanObj.getStrAdmNo());
			VO.setSharableChk(beanObj.getSharableChk());
			
			//get units present in current department & ward
			bo.unitCombo(VO);
			beanObj.setBillcount(VO.getBillcount());
		
			
			if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}

			String stradmitlist = NursingDeskTransHLP.getAdmitDetailIPDACCEPTANCE(VO);
			beanObj.setStrAdmitDetailProperty(stradmitlist);
			beanObj.setDepartmentName(VO.getDepartmentName());
			beanObj.setWardName(VO.getWardName());
			beanObj.setStrPreviousOccupiedbed(VO.getStrPreviousOccupiedbed());
			
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.admitlistIPD() --> -->", VO.getStrMsgString());
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	
	
	public static void admitlistIPDCANCEL(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;

		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			
			
			VO.setStrDepartment(beanObj.getStrDepartment());
			VO.setStrWard(beanObj.getStrWard());
			VO.setStrRoom(beanObj.getStrRoom());
			VO.setStrCrNo(beanObj.getStrCrNo());
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrAdmNo(beanObj.getStrAdmNo());
			
			//get units present in current department & ward
			bo.unitCombo(VO);
		
			
			if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}

			String stradmitlist = NursingDeskTransHLP.getAdmitDetailIPD(VO);
			beanObj.setStrAdmitDetailProperty(stradmitlist);
			beanObj.setDepartmentName(VO.getDepartmentName());
			beanObj.setWardName(VO.getWardName());
			beanObj.setStrPreviousOccupiedbed(VO.getStrPreviousOccupiedbed());
			
			
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.admitlist() --> -->", VO.getStrMsgString());
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}

	public static void checklist(NursingDeskTransFB beanObj,HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;

		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrUnit(beanObj.getStrUnit());
			String checklist = NursingDeskTransHLP.getChecklistDetail(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			}

			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			} 
			else 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(checklist);
			}
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.checklist() --> -->", VO.getStrMsgString());
			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
			} 
			catch (Exception e1)
			{
			}
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}

	public static void beddetail(NursingDeskTransFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;

		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();

			VO.setStrWard(request.getParameter("modWard"));
			VO.setStrRoom(request.getParameter("modRoom"));
			VO.setStrUnit(request.getParameter("modUnit"));
			VO.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.setBedDetails(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ // error
				throw new Exception(VO.getStrMsgString());
			}
			String strbed = NursingDeskTransHLP.getBedDetails(VO);

			if (VO.getStrMsgType().equals("1")) 
			{ // error
				throw new Exception(VO.getStrMsgString());
			}

			formBean.setStrBedProperty(strbed);
			formBean.setStrMsgString(VO.getStrMsgString());
			formBean.setStrMsgType(VO.getStrMsgType());

			if (formBean.getStrMsgType().equals("1")) 
			{ // error
				throw new Exception(formBean.getStrMsgString());
			}
		}
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.beddetail()-->", VO.getStrMsgString());
			formBean.setStrErrorMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}

	public static void save(NursingDeskTransFB formBean,HttpServletRequest request) 
	{
		String msgstr = "";
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		IpdConfigUtil ipdConfig=null;

		try 
		{
			ipdConfig=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			VO = (NursingDeskTransVO) TransferObjectFactory.populateData("ipd.transactions.NursingDeskTransVO", formBean);
			VO.setStrHospitalCode(formBean.getStrHospitalCode());
			VO.setHiddencrno(request.getParameter("hiddencrno"));
			VO.setHiddenadmno(request.getParameter("hiddenadmno"));
			VO.setHiddenbed(request.getParameter("hiddenbed"));
			VO.setHiddenchkremark(request.getParameter("hiddenchkremark"));
			VO.setHiddenbelonging(request.getParameter("hiddenbelonging"));
			VO.setStrDepartment(request.getParameter("strDepartment"));
			VO.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
			VO.setStrBedFlag(ipdConfig.getStrBedChange());
			VO.setStrRoom(formBean.getStrhiddenRoom());
			VO.setStrConsultantCode(formBean.getStrConsultantCode());
			VO.setStrConsultantName(formBean.getStrConsultantName());
			
			
	        System.out.println("strroom"+VO.getStrRoom());
			
			bo = new NursingDeskTransBO();
			bo.save(VO);
			
			/*
			 * 
			 *  added for IPD redirection if error occur then
			 *  page remains the same otherwise a success message JSP
			 *  will open.
			 */
			
			formBean.setStrMsgType(VO.getStrMsgType());
			
			
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			} 
			else 
			{
				msgstr = "Patient Accepted successfully ";
				formBean.setStrNormalMsg(msgstr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.save()-->", VO.getStrMsgString());
			if(e.getMessage().contains("Patient is already Accepted"))
				formBean.setStrErrorMsg("Error: Data Not Saved. Patient is already Accepted");
			else
				formBean.setStrErrorMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			
			if(e.getMessage().contains("Bed is already Ocuppied by another Patient!")) {
				formBean.setStrMsgString("Bed is already Ocuppied by another Patient!");
			}
			if(VO.getStrMsgString().contains("Bed is already Ocuppied by another Patient!")) {
				formBean.setStrMsgString("Bed is already Ocuppied by another Patient!");
			}
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	
	
	public static void saveCancel(NursingDeskTransFB formBean,HttpServletRequest request) 
	{
		String msgstr = "";
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		IpdConfigUtil ipdConfig=null;

		try 
		{
			ipdConfig=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			VO = (NursingDeskTransVO) TransferObjectFactory.populateData("ipd.transactions.NursingDeskTransVO", formBean);
			VO.setStrHospitalCode(formBean.getStrHospitalCode());
			VO.setHiddencrno(request.getParameter("hiddencrno"));
			VO.setHiddenadmno(request.getParameter("hiddenadmno"));
			VO.setHiddenbed(request.getParameter("hiddenbed"));
			VO.setHiddenchkremark(request.getParameter("hiddenchkremark"));
			VO.setHiddenbelonging(request.getParameter("hiddenbelonging"));
			VO.setStrDepartment(request.getParameter("strDepartment"));
			VO.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
			VO.setStrBedFlag(ipdConfig.getStrBedChange());
			VO.setStrRoom(formBean.getStrhiddenRoom());
			VO.setStrConsultantCode(formBean.getStrConsultantCode());
			VO.setStrConsultantName(formBean.getStrConsultantName());
			
			
	        System.out.println("strroom"+VO.getStrRoom());
			
			bo = new NursingDeskTransBO();
			bo.saveCancel(VO);
			
			/*
			 * 
			 *  added for IPD redirection if error occur then
			 *  page remains the same otherwise a success message JSP
			 *  will open.
			 */
			
			formBean.setStrMsgType(VO.getStrMsgType());
			
			
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			} 
			else 
			{
				msgstr = "Patient Movement cancelled successfully ";
				formBean.setStrNormalMsg(msgstr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.save()-->", VO.getStrMsgString());
			formBean.setStrErrorMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}

	public static void notreport(NursingDeskTransFB formBean,HttpServletRequest request) 
	{
		String msgstr = "";
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		IpdConfigUtil ipdConfig=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strHiddenCrNo = "";
		String strHiddenAdmNo = "";

		try 
		{

			VO = (NursingDeskTransVO) TransferObjectFactory.populateData("ipd.transactions.NursingDeskTransVO", formBean);
			VO.setStrHospitalCode(formBean.getStrHospitalCode());
			VO.setStrRemarks(formBean.getStrRemarks());
			VO.setStrSeatId(formBean.getStrSeatId());
			VO.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
			strHiddenAdmNo= request.getParameter("hiddenadmno");
			strHiddenCrNo = request.getParameter("hiddencrno");
			if(strHiddenAdmNo==null)
			{
				formBean.setHiddenadmno(formBean.getStrAdmNo());
				strHiddenAdmNo = formBean.getStrAdmNo();
				//VO.setStrhmoveno("1");
				VO.setStrhmoveno(formBean.getStrhmoveno());
			}
			if(strHiddenCrNo==null)
			{
				formBean.setHiddencrno(formBean.getStrCrNo());
				strHiddenCrNo = formBean.getStrCrNo();
			}
			VO.setHiddencrno(strHiddenCrNo);
			VO.setHiddenadmno(strHiddenAdmNo);

			bo = new NursingDeskTransBO();
			bo.notReport(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			else 
			{
				msgstr = "Patient Status Changed to Not Reporting  successfully ";
				formBean.setStrNormalMsg(msgstr);
			}
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.notreporting()-->", VO.getStrMsgString());
			formBean.setStrErrorMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	/* Method Added by Amt Kumar Ateria on 19-Apr-2011
	 * This metohd populates the bed combo after unit selection in acceptance page
	 */
	public static void bed(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		try 
		{
			// we do not need to call populate() function.
			// that is why we create an instance and pass it to the BO
			VO = new NursingDeskTransVO();
			VO = (NursingDeskTransVO) TransferObjectFactory.populateData("ipd.transactions.NursingDeskTransVO", beanObj);
			NursingDeskTransBO bo = new NursingDeskTransBO();

			VO.setStrWard(request.getParameter("wardCode"));
			VO.setStrUnit(request.getParameter("deptUnitCode"));
			VO.setStrDepartment(request.getParameter("deptCode"));
			VO.setStrRoom(request.getParameter("roomCode"));
			VO.setSharableChk(request.getParameter("shr_chk"));
			beanObj.setStrPreviousOccupiedbed(request.getParameter("previousBedCode"));
			VO.setStrPreviousOccupiedbed(beanObj.getStrPreviousOccupiedbed());
			bo.bed(VO);
			beanObj.setStrMsgType(VO.getStrMsgType());
			if (beanObj.getStrMsgType().equals("1")) 
			{
				beanObj.setStrMsgString(VO.getStrMsgString());// error
				throw new Exception(beanObj.getStrMsgString());
			} 
			else 
			{
				HisUtil util = new HisUtil("ipd", "NursingDeskTransDATA");
				VO.setStrBedProperty(util.getOptionValue(VO.getStrBedDetailWS(), VO.getStrPreviousOccupiedbed(), "0^Select Value", false));
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(VO.getStrBedProperty());
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","NursingDeskTransDATA->bed()", strmsgText);
			beanObj.setStrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
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
	
	
	public static void BEDCOMBOAJAX(NursingDeskTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		String wardCode = request.getParameter("wardCode");
		String deptUnitCode = request.getParameter("deptUnitCode");
		String roomCode = request.getParameter("roomCode");
		String strFlag = request.getParameter("strFlag");
		HisUtil util = null;
		String strbed = "";

		try {
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrSeatId(beanObj.getStrSeatId());
			if (wardCode != null)
				beanObj.setStrWard(wardCode);
			VO.setStrWard(beanObj.getStrWard());
			VO.setStrBedFlag(strFlag);
			VO.setStrRoom(roomCode);
			VO.setStrUnit(deptUnitCode);
			bo.BEDCOMBOAJAX(VO);
			if (VO.getStrMsgType().equals("1")) { 
				throw new Exception(VO.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			util = new HisUtil("ipd", "NursingDeskTransDATA");
			strbed = util.getOptionValue(VO.getStrBedDetailWS(), "", "0^Select Value", false);
			
			if (beanObj.getStrMsgType().equals("1")) { 
				throw new Exception(beanObj.getStrMsgString());
			} else {
				response.setHeader("Cache-Control", "no-cache");
				beanObj.setStrBedProperty(strbed);
				response.getWriter().print(strbed);
			}

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->NursingDesk",
					"NursingDeskTransDATA.bed() --> -->", VO.getStrMsgString());
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception e1) {
			}
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
	public static void admitlistNr(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			
			
			VO.setStrDepartment(beanObj.getStrDepartment());
			VO.setStrWard(beanObj.getStrWard());
			/*VO.setStrRoom(beanObj.getStrRoom());
			VO.setStrCrNo(beanObj.getStrCrNo());
			VO.setStrUnit(beanObj.getStrUnit());
			
			VO.setStrhtransinflag(beanObj.getStrhtransinflag());
			VO.setStrWadType(beanObj.getStrWadType());
			*/
			VO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//VO.setStrAdmNo(request.getParameter("chk").replace("@", "#").split("#")[1]);
			VO.setStrAdmNo(beanObj.getStrAdmNo());
			VO.setStrhmoveno(beanObj.getStrhmoveno());
			/*HisUtil util = new HisUtil("ipd", "NursingDeskTransDATA");
			String strunit = util.getOptionValue(VO.getStrUnitWs(), "", "0^Select Value", false);
			//VO.setStrUnit(beanObj.getStrUnit());
			beanObj.setStrunitproperty(strunit);
			VO.setStrunitproperty(beanObj.getStrunitproperty());*/
			//bo.admitedbed(VO);
		/*	if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			//bo.beddetailcombo(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
*/
			String stradmitlist = NursingDeskTransHLP.getAdmitDetailNr(VO);
			beanObj.setStrAdmitDetailProperty(stradmitlist);
			beanObj.setDepartmentName(VO.getDepartmentName());
			beanObj.setWardName(VO.getWardName());
			beanObj.setStrPreviousOccupiedbed(VO.getStrPreviousOccupiedbed());
			beanObj.setTimelt(VO.getTimelt());
			if(beanObj.getTimelt().equals("1"))
				beanObj.setStrErrorMsg("Patient can't be not reported before time limit");
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.admitlist() --> -->", VO.getStrMsgString());
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	public static void admitlistNrLeave(NursingDeskTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		try 
		{
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			
			
			VO.setStrDepartment(beanObj.getStrDepartment());
			VO.setStrWard(beanObj.getStrWard());
			/*VO.setStrRoom(beanObj.getStrRoom());
			VO.setStrCrNo(beanObj.getStrCrNo());
			VO.setStrUnit(beanObj.getStrUnit());
			
			VO.setStrhtransinflag(beanObj.getStrhtransinflag());
			VO.setStrWadType(beanObj.getStrWadType());
			*/
			VO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//VO.setStrAdmNo(request.getParameter("chk").replace("@", "#").split("#")[1]);
			VO.setStrAdmNo(beanObj.getStrAdmNo());
			VO.setStrhmoveno(beanObj.getStrhmoveno());
			/*HisUtil util = new HisUtil("ipd", "NursingDeskTransDATA");
			String strunit = util.getOptionValue(VO.getStrUnitWs(), "", "0^Select Value", false);
			//VO.setStrUnit(beanObj.getStrUnit());
			beanObj.setStrunitproperty(strunit);
			VO.setStrunitproperty(beanObj.getStrunitproperty());*/
			//bo.admitedbed(VO);
		/*	if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			//bo.beddetailcombo(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
*/
			String stradmitlist = NursingDeskTransHLP.getAdmitDetailNrLeave(VO);
			beanObj.setStrAdmitDetailProperty(stradmitlist);
			beanObj.setDepartmentName(VO.getDepartmentName());
			beanObj.setWardName(VO.getWardName());
			beanObj.setStrPreviousOccupiedbed(VO.getStrPreviousOccupiedbed());
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.admitlistNrLeave() --> -->", VO.getStrMsgString());
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	public static void GETCONSULTANT(NursingDeskTransFB beanObj,
			HttpServletRequest request, HttpServletResponse response) {

		NursingDeskTransVO VO = null;
		NursingDeskTransBO bo = null;
		String deptUnitCode = request.getParameter("deptUnitCode");
		HisUtil util = null;
		String strConsultantCode = "";

		try {
			VO = new NursingDeskTransVO();
			bo = new NursingDeskTransBO();
			VO.setStrHospitalCode(beanObj.getStrHospitalCode());
			VO.setStrSeatId(beanObj.getStrSeatId());
			
			VO.setStrUnit(deptUnitCode);
			bo.getConsultantName(VO);
			if (VO.getStrMsgType().equals("1")) { 
				throw new Exception(VO.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

			util = new HisUtil("ipd", "NursingDeskTransDATA");
			strConsultantCode = util.getOptionValue(VO.getConsultantWS(),deptUnitCode, "0^Select Value", false);
			System.out.println("-------------------------------->"+strConsultantCode);
			
			if (beanObj.getStrMsgType().equals("1")) { 
				throw new Exception(beanObj.getStrMsgString());
			} else {
	
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strConsultantCode);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			HisException eObj = new HisException(
					"IPD->Transactions->NursingDesk",
					"NursingDeskTransDATA.GETCONSULTANT() --> -->", VO.getStrMsgString());
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception e1) {
			}
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
}//End of Class