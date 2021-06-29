package ipd.transactions;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.ADTPrintingTransHLP;
import ipd.HLPOccupationDetails;
import ipd.IpdConfigUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class  SlipReprintTransDATA {
	public static String DATE_FORMAT_NOW = "dd-MMM-yyyy/HH:mm:ss";
/**
 * This function is used to give current date
 * @return
 */
	public static String now() 

	{

	HisUtil util=null;

	String a="";

	util=new HisUtil("transaction","PatientTransferTransHLP");

	try{

	a= util.getASDate(DATE_FORMAT_NOW);

	}

	catch(Exception e){

	}

	/*Calendar cal = Calendar.getInstance();

	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

	return sdf.format(cal.getTime());*/

	return a;

	}



	
	/**
	 * This function is used to set initial parameters required to display on main page 
	 * @param formBean
	 */
	
	public static void initPatientAdmission(SlipReprintTransFB formBean) {
		
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		SlipReprintTransVO voObj= null;
		SlipReprintTransBO bo= null;
		HisUtil util = null;
		String temp="";
		try 
		 {
			icu   =  new IpdConfigUtil(formBean.getStrHospCode());
			voObj =  new SlipReprintTransVO();
			bo    =  new SlipReprintTransBO();
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
			voObj.setStrModifyTimeValidity(ipdConfig.getStrModificationTimeValidity());
			voObj.setStrIsIntegratedWithBilling(ipdConfig.getStrIntegrationBilling());
			voObj.setStrIsAdvanceAmountAtAdmission(ipdConfig.getStrAdvanceAmountAdmission());
			bo.setPatientDtl(voObj);
			formBean.setStrIsIntegratedWithBilling(voObj.getStrIsIntegratedWithBilling());
			formBean.setStrIsAdvanceAmountAtAdmission(voObj.getStrIsAdvanceAmountAtAdmission());
			formBean.setStrIsAdvanceAmountAtAdmissionTaken(voObj.getStrIsAdvanceAmountAtAdmissionTaken());
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrAdvanceAmountDate(voObj.getStrAdvanceAmountDate());
			formBean.setStrAdvanceAmountReceiptNo(voObj.getStrAdvanceAmountReceiptNo());
			if(voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}
			if(voObj.getStrPatStatusCode().equals("14"))
			{
				formBean.setStrMsgString("Admission is cancelled");
				formBean.setStrCrNo("");
			}
			else if(voObj.getStrPatStatusCode().equals("13"))
			{
				formBean.setStrMsgString("Patient has not been reported");
				formBean.setStrCrNo("");
			}
			else if(voObj.getStrValidCrNo().equals("1"))
			{
				formBean.setStrMsgString("Invalid CR No/Data not found");
				formBean.setStrCrNo("");
			}
			else if(voObj.getStrModifyTimeStatus().equals("1"))
			{
				formBean.setStrMsgString("Invalid CR No./Data not found");
				formBean.setStrCrNo("");
			}
			
			else
			{
				util = new HisUtil("Patient Admission Modification Trans",
									"PatientAdmissionModificationTransDATA");
				temp = util.getOptionValue(voObj.getDepartWS(), voObj.getStrDeptCode(),
									"0^Select Value", false);
				formBean.setStrDeptValue(temp);
				temp = util.getOptionValue(voObj.getUnitWS(), voObj.getStrDeptUnitCode(),
						"0^Select Value", false);
				formBean.setStrUnitValue(temp);	
				formBean.setStrAdmissionChargeValue(ipdConfig.getStrNewBornBabyAdmissionCharge());
				formBean.setStrNewBornRegistrationCharge(ipdConfig.getStrNewBornBabyRegistrationCharge());
				formBean.setStrAddressModi(PatientAdmissionModificationTransHLP.getPatientDetailModi(voObj));

				// Setting Consultant Values List (updated at 31-Mar-2011 By Pragya)
				temp="";
				temp+=util.getOptionValue(voObj.getConsultantWS(),voObj.getStrConsultantCode(),"0^Select Value", false);
				formBean.setStrConsultantValues(temp);	
				
				strOccDtlsview = HLPOccupationDetails.getOccupationDetails(voObj.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
				formBean.setOccupationDetailValues(strOccDtlsview);
				formBean.setStrDeptName(voObj.getStrDeptName());
				formBean.setStrUnitName(voObj.getStrUnitName());
				formBean.setStrNewBorn(voObj.getStrNewBorn());
				formBean.setStrBedCode(voObj.getStrBedCode());
				formBean.setStrRoomCode(voObj.getStrRoomCode());
				formBean.setStrRoom(voObj.getStrRoom());
				formBean.setStrWardName(voObj.getStrWardName());
				formBean.setStrWardTypeCode(voObj.getStrWardTypeCode());
				formBean.setStrCrNo(voObj.getStrCrNo());
				formBean.setStrWardTypeCode(voObj.getStrWardTypeCode());
				formBean.setStrRoomCode(voObj.getStrRoomCode());
				formBean.setStrRoom(voObj.getStrRoom());
				formBean.setStrWardCode(voObj.getStrWardCode());
				formBean.setStrDeptCode(voObj.getStrDeptCode());
				formBean.setStrRoomTypeCode(voObj.getStrRoomTypeCode());
				formBean.setStrConsultantCode(voObj.getStrConsultantCode());
				formBean.setStrConsultantName(voObj.getStrConsultantName());
				formBean.setStrEpisodeCode(voObj.getStrEpisodeCode());
				formBean.setStrVisitNo(voObj.getStrVisitNo());
				formBean.setStrMlcNo(voObj.getStrMlcNo());
				formBean.setStrAdviceAdmNo(voObj.getStrAdviceAdmNo());
				formBean.setStrBookingDate(voObj.getStrBookingDate());
				formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
				formBean.setStrDeptUnitName(voObj.getStrDeptUnitName());
				formBean.setStrDeptCode(voObj.getStrDeptCode());
				formBean.setStrMsApprovalStatus(voObj.getStrMsApprovalStatus());
				formBean.setStrMsApprovalFlag(voObj.getStrMsApprovalFlag());
				formBean.setStrAdmNo(voObj.getStrAdmNo());
				formBean.setStrBedTypeCode(voObj.getStrBedTypeCode());
				formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
				formBean.setStrAdmDateTime(voObj.getStrAdmDateTime());
				if(voObj.getStrMsgType().equals("1"))
				{
						
					throw new Exception(voObj.getStrMsgString());
				}
			}
			
						
		} /*catch (SQLException e) {

			formBean.setStrMsgString("No Data in OCC Table");
			formBean.setStrMsgType("1");

			new HisException("Patient Admission",
					"PatientAdmissionDATA.initPatientAdmission()->", e
							.getMessage());

		} */catch (Exception e) {
				
				e.printStackTrace();
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionModificationTransDATA->initAdmissionAdvice()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
			     formBean.setStrCrNo("");
		}
	}
	/**
	 * This function is used to initial parameters for bed details pop up window 
	 * @param formBean
	 * @param request
	 */
	public static void initBedStatus(SlipReprintTransFB formBean,HttpServletRequest request) {
		String temp="";
	
		HisUtil util = null;
		try {
			SlipReprintTransVO vo=new SlipReprintTransVO();
			SlipReprintTransBO bo=new SlipReprintTransBO();
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrWardCode(request.getParameter("wardCode"));
			vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
			vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
			vo.setStrCrNo(request.getParameter("crNo"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrDeptName(request.getParameter("deptName"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrMsApprovalStatus(request.getParameter("msApprovalStatus"));
			vo.setStrMsApprovalFlag(request.getParameter("msApprovalFlag"));
			vo.setStrWardName(request.getParameter("wardName"));
			vo.setStrRoomCode(request.getParameter("roomCode"));
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			vo.setStrAgeUnit(request.getParameter("ageCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			bo.setBedStatusDtl(vo);
			formBean.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			formBean.setStrDepartmentName(vo.getStrDeptName());
			formBean.setStrDeptCode(vo.getStrDeptCode());
			
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			if(vo.getStrMsApprovalFlag().equals("6") && vo.getStrMsApprovalStatus().equals("0"))
			{
				util = new HisUtil("Patient Admission Modification Trans",
				"PatientAdmissionModificationTransDATA");
				temp="<option value='"+vo.getStrWardTypeCode()+"' selected>Private</option>";
				
				formBean.setStrwardType(temp);
				temp="<option value='"+vo.getStrWardCode()+"'>"+vo.getStrWardName()+"</option>";
				formBean.setStrWard(temp);
				temp=util.getOptionValue(vo.getRoomTypeWS(), vo.getStrRoomTypeCode(),
						"0^Select Value", false);
				formBean.setStrRoomType(temp);
				 temp=util.getOptionValue(vo.getRoomWs(),vo.getStrRoomCode(),
							"0^Select Value", false);
						 formBean.setStrRoom(temp);
				temp=util.getOptionValue(vo.getBedTypeWS(),vo.getStrBedTypeCode(),
							"0^Select Value", false);
				formBean.setStrBedType(temp);
			    formBean.setStrMsApprovalFlag(vo.getStrMsApprovalFlag());
				formBean.setStrMsApprovalStatus(vo.getStrMsApprovalStatus());
							
			}
			else{
				util = new HisUtil("Patient Admission Modification Trans",
						"PatientAdmissionModificationTransDATA");
				temp=util.getOptionValue(vo.getWardTypeWS(), vo.getStrWardTypeCode(),
					"0^Select Value", false);
				formBean.setStrwardType(temp);
				temp=util.getOptionValue(vo.getWardWS(), vo.getStrWardCode(),
						"0^Select Value", false);
					formBean.setStrWard(temp);
					temp=util.getOptionValue(vo.getRoomTypeWS(), vo.getStrRoomTypeCode(),
					"0^Select Value", false);
				formBean.setStrRoomType(temp);
				
				temp=util.getOptionValue(vo.getRoomWs(),vo.getStrRoomCode(),
					"0^Select Value", false);
				formBean.setStrRoom(temp);
				temp=util.getOptionValue(vo.getBedTypeWS(),vo.getStrBedTypeCode(),
					"0^Select Value", false);
				formBean.setStrBedType(temp);
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "PatientAdmissionModificationTransDATA->initBedStatus()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
			     formBean.setStrCrNo("");
		}
	}
	/**
	 * This function is used to set initial parameters to search beds in particular ward
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void initBedDetails(SlipReprintTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		
		
		try {
			SlipReprintTransVO vo=new SlipReprintTransVO();
			SlipReprintTransBO bo=new SlipReprintTransBO();
			vo.setStrWardCode(request.getParameter("wardCode"));
			vo.setStrRoom(request.getParameter("roomCode"));
			vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			bo.setBedDetails(vo);
			String res="";
			//res=PatientAdmissionModificationTransHLP.getBedDetails(vo);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(res+"^"+vo.getStrMsApprovalFlag());
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
			
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("ADT", "PatientAdmissionModificationTransDATA->initBedDetails()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (Exception e1) 
			{

			//System.out.println("Inside IInd Else::::"+e1.getMessage());

			}
		}
	}
	
/**
 * This function set initial parameters for ward combo when user change ward type on bed details pop up window
 * @param formBean
 * @param request
 * @param response
 */	
public static void initWardDetails(SlipReprintTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		
		
		HisUtil util = null;
		try {
			SlipReprintTransVO vo=new SlipReprintTransVO();
			SlipReprintTransBO bo=new SlipReprintTransBO();
			String temp="";
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			vo.setStrAgeUnit(request.getParameter("ageCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			vo.setStrCrNo(request.getParameter("crNo"));
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			
			
			vo.setStrHospCode(formBean.getStrHospCode());
			bo.setWardDetails(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				else
				{
				util = new HisUtil("Patient Admission Modification Trans",
				"PatientAdmissionModificationTransDATA");
				temp=util.getOptionValue(vo.getWardWS(), "",
					"0^Select Value", false);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
				}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("ADT", "PatientAdmissionModificationTransDATA->initWardDetails()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (Exception e1) 
			{

			//System.out.println("Inside IInd Else::::"+e1.getMessage());

			}
		}
		
}
/**
 * This function is used to set initial parameters to bring room details on the basis of room type code
 * @param formBean
 * @param request
 * @param response
 */
public static void initRoomDetails(SlipReprintTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
	
	
	HisUtil util = null;
	try {
		SlipReprintTransVO vo=new SlipReprintTransVO();
		SlipReprintTransBO bo=new SlipReprintTransBO();
		String temp="";
		vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
		vo.setStrWardCode(request.getParameter("wardCode"));
		vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
		vo.setStrAgeUnit(request.getParameter("ageCode"));
		vo.setStrSexCode(request.getParameter("sexCode"));
		vo.setStrAge(request.getParameter("age"));
		vo.setStrCrNo(request.getParameter("crNo"));
		vo.setStrHospCode(formBean.getStrHospCode());
		bo.setRoomDetails(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			throw new Exception();
		}
		else
		{
			util = new HisUtil("Patient Admission Modification Trans",
					"PatientAdmissionModificationTransDATA");
			temp=util.getOptionValue(vo.getRoomWs(),"",
					"0^Select Value", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		String strmsgText = e.getMessage();
		response.setHeader("Cache-Control", "no-cache");
		try 
		{
			HisException eObj = new HisException("ADT", "PatientAdmissionModificationTransDATA->initRoomDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			response.getWriter().print(response1);
			eObj = null;
		}
		catch (Exception e1) 
		{

		//System.out.println("Inside IInd Else::::"+e1.getMessage());

		}
	
	}
}
/**
 * This function invoke TransferObjectFactory.populateData() to transfer the values of all attributes of form bean into vo and the invoke bo insert method
 * @param formBean
 */
public static void insert(SlipReprintTransFB formBean,HttpServletRequest request)
{
		try
		{
			SlipReprintTransVO vo=(SlipReprintTransVO) TransferObjectFactory.populateData("ipd.transactions.SlipReprintTransVO", formBean);
			//SlipReprintTransBO bo=new SlipReprintTransBO();
			
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrIpAddress(formBean.getStrHospCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrCurrentDate(now());
			if(vo.getStrIsNewBorn().trim().equals("1") && (vo.getStrMotherName()==null || vo.getStrMotherName().trim().equals("") 
					|| vo.getStrMotherCrNo()==null || vo.getStrMotherCrNo().trim().equals("")))
			{
				String[] tmp2 = vo.getStrMotherDtl().replace('^', '#').split("#");
				vo.setStrMotherName(tmp2[0]);
				vo.setStrMotherCrNo(tmp2[1]);
			}

			//bo.insert(vo);
			/*if(vo.getStrMsgType().equals("0"))
			{
				formBean.setStrMsg("Patient Admission Record is Successfully Updated");
			}
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{*/
				//String[] temp=vo.getStrHiddenPatDtl().replace("^","#").split("#");
				//String strHiddenPatDtl=vo.getStrPatientName()+"^"+vo.getStrFatherName()+"/"+vo.getStrSpouseName()+"^"+temp[2]+"^"+temp[3]+"^"+vo.getStrHouseNo()+","+vo.getStrStreet()+","+vo.getStrCity();
				//vo.setStrHiddenPatDtl(strHiddenPatDtl);
				// Setting Address
				/*StringBuilder sbAdd = new StringBuilder("");
				if(vo.getStrHouseNo()!=null && !vo.getStrHouseNo().trim().equals(""))		sbAdd.append(vo.getStrHouseNo());		
				if(vo.getStrStreet()!=null && !vo.getStrStreet().trim().equals(""))		{	sbAdd.append(", ");		sbAdd.append(vo.getStrStreet());	}
				if(vo.getStrCity()!=null && !vo.getStrCity().trim().equals(""))			{	sbAdd.append(", ");		sbAdd.append(vo.getStrCity());	}
				//if(vo.getStrDistrict()!=null && !vo.getStrDistrict().trim().equals(""))	{	sbAdd.append(", ");		sbAdd.append(vo.getStrDistrict());		}
					//Now available here only Code is available
				if(vo.getStrStateName()!=null && !vo.getStrStateName().trim().equals(""))		{	sbAdd.append(", ");		sbAdd.append(vo.getStrStateName());	}
				//if(vo.getStrCountryName()!=null && !vo.getStrCountryName().trim().equals(""))	{	sbAdd.append(", ");		sbAdd.append(vo.getStrCountryName());	}
				if(vo.getStrPinCode()!=null && !vo.getStrPinCode().trim().equals(""))			{	sbAdd.append(" - ");	sbAdd.append(vo.getStrPinCode());		}
				if(sbAdd.charAt(0) == ',') sbAdd.delete(0, 1);
				vo.setStrAddress(sbAdd.toString());*/
				ADTPrintingTransHLP.AdmissionSlipReprint(vo,request);
				formBean.setStrSaveFlag("1");
				formBean.setStrPatientCrNo(formBean.getStrCrNo());
				formBean.setStrMsg("Slip Printed Succesfully.");
			//}
		}
		catch(Exception e)
		{
				String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionModificationTransDATA->insert()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
			     formBean.setStrCrNo("");
		}
	}
public static void initUnitDtl(SlipReprintTransFB formBean,HttpServletRequest request,HttpServletResponse response)
{
	SlipReprintTransVO vo=null;
	SlipReprintTransBO bo=null;
	HisUtil util = null;
	String temp;
	try
	{
		
		vo=new SlipReprintTransVO();
		bo=new SlipReprintTransBO();
		vo.setStrMotherDeptCode(request.getParameter("deptCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		//vo.setStrHospCode("108");
		bo.setUnitValue(vo);
		util = new HisUtil("Patient Admission Modification Trans",
					"PatientAdmissionModificationTransDATA");
		temp = util.getOptionValue(vo.getUnitWS(), "0",
		"0^Select Value", false);
		response.getWriter().write(temp);
	}
	catch(Exception e)
	{
			String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "PatientAdmissionModificationTransDATA->initUnitDtl()", strmsgText);
		   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
		     formBean.setStrCrNo("");
	}
}
}
