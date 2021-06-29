package ipd.transactions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
//import hisglobal.utility.SMSHttpsNICPostClient;
import hisglobal.utility.SMSSender.SMSHttpPostClient;
import ipd.ADTPrintingTransHLP;
//import ipd.DisplayImage;
import ipd.HLPOccupationDetails;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import billing.PrintHLP;


//import com.jscape.inet.sftp.packets.Dispatchable;


public class PatientAdmissionTransDATA {

	
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
	public static void initPatientAdmission(PatientAdmissionTransFB formBean) 
	{		
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		PatientAdmissionTransVO voObj= null;
		PatientAdmissionTransBO bo= null;
		String strAddressDepUbit="";
		String strEmgAddress="";
		String strAdmissionType="";
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(formBean.getStrHospCode());
		
		try 
		 {
			formBean.setStrFatherNameMandatoryFlag(IpdTransConfig.getMandatoryFatherNameAddmision());
			icu   =  new IpdConfigUtil(formBean.getStrHospCode());
			voObj =  new PatientAdmissionTransVO();
			bo    =  new PatientAdmissionTransBO();
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
			voObj.setStrAdmissionAdviceValidityFrom(ipdConfig.getStrAdmissionAdviceValidityFrom());
			voObj.setStrAdmissionAdviceValidityTo(ipdConfig.getStrAdmissionAdviceValidityTo());
			voObj.setStrIsAdmissionOnline(ipdConfig.getStrAdmissionOnline());
			voObj.setStrIsIntegratedWithBilling(ipdConfig.getStrIntegrationBilling());
			voObj.setStrIsAdvanceAmountAtAdmission(ipdConfig.getStrAdvanceAmountAdmission());
			voObj.setStrAdmissionCharge(ipdConfig.getStrAdmissionCharge());
			voObj.setStrDeptWardChangeChk(formBean.getStrDeptWardChangeChk());
		//	voObj.setStrDeptUnitCode("0");
			bo.setPatientDtl(voObj);
			formBean.setStrIsIntegratedWithBilling(voObj.getStrIsIntegratedWithBilling());
			formBean.setStrIsAdvanceAmountAtAdmission(voObj.getStrIsAdvanceAmountAtAdmission());
			formBean.setStrIsAdvanceAmountAtAdmissionTaken(voObj.getStrIsAdvanceAmountAtAdmissionTaken());
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrAdvanceAmountDate(voObj.getStrAdvanceAmountDate());
			formBean.setStrAdvanceAmountReceiptNo(voObj.getStrAdvanceAmountReceiptNo());
			formBean.setStrAgeUnit(voObj.getStrAgeUnit());
			formBean.setStrSexCode(voObj.getStrSexCode());
			formBean.setStrAge(voObj.getStrAge());			
			//formBean.setStrAdmissionType("0");
			formBean.setStrPatIsUnknown(voObj.getStrPatIsUnknown());
			formBean.setStrMsAppStatus(voObj.getStrMsAppStatus());
			formBean.setStrIcuWardType(ipdConfigUtil.getStrIcuWardType());
			formBean.setStrPvtWardType(ipdConfigUtil.getStrPrivateWardType());
			formBean.setBillcount(voObj.getBillcount());
			formBean.setStrPinCode(voObj.getStrPinCode());
			formBean.setStrActiveWallet(voObj.getStrActiveWallet());  
			
			/*if(!voObj.getStrCountryCode().equals("IND"))
			{
				//bo.setStateInputForeign(voObj);
				formBean.setStrStateName(voObj.getStrStateName());
				formBean.setStrDistrictName(voObj.getStrDistrict());
				
			}*/
			if(ipdConfigUtil.getStrUnitNameReq().equals("1"))
			{
				formBean.setStrHiddenUnit("1");
			}
			else
				formBean.setStrHiddenUnit("0");
			if(ipdConfigUtil.getStrRoomNoReq().equals("1"))
			formBean.setStrHiddenRoom("1");
			else
				formBean.setStrHiddenRoom("0");
			if(voObj.getStrCrNoValid().equals("1"))
			{
				formBean.setStrMsgString("Invalid CR No./Data not found/Visit Details not found in Current Hospital");
				formBean.setStrCrNo("");
			}
			else if(voObj.getStrIsAdvanceAmountAtAdmissionTaken().equals("0"))
			{
				formBean.setStrMsgString("Advance Amount is not deposited");
				formBean.setStrCrNo("");
			}
			else if(!voObj.getStrActiveWallet().equals("0"))
			{
				formBean.setStrMsgString("Patient has an active wallet A/C");
				formBean.setStrCrNo("");
			}
			else if(!voObj.getStrDormantAcc().equals("0"))
			{
				formBean.setStrMsgString("Patient has a Dormant A/C, Readmission won't be Allowed");
				formBean.setStrCrNo("");
			}
			else
			{
			
			
			if(voObj.getStrCrNoValid().equals("0"))
			{
				formBean.setStrNoOfFreePass(ipdConfig.getStrNoOfFreePass());
				formBean.setStrAdviceStatus(voObj.getStrAdviceStatus());
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrIsDead(voObj.getStrIsDead());
				formBean.setStrFreePassValid(ipdConfig.getStrNoOfFreePassAdmisssionTime());
				
				if(!(formBean.getStrPatStatusCode().equals(IpdTransConfig.getAdmittedCode()) || formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode())))
				{
					strAddressDepUbit=PatientAdmissionTransHLP.getPatientDetailModi(voObj);
					//formBean.setStrAdmissionType("0");
					HisUtil util = new HisUtil("Patient Admission Trans","PatientAdmissionDATA");
					if(voObj.getWrsAdmissionTypeValues()!=null && voObj.getWrsAdmissionTypeValues().size()>0)
					{
						strAdmissionType=util.getOptionValue(voObj.getWrsAdmissionTypeValues(), "0","", false);
						formBean.setStrAdmissionTypeValues(strAdmissionType);
						//admissionTypeName
						//formBean.setAdmissionTypeName(voObj.getWrsAdmissionTypeValues().getString(formBean.getStrAdmissionType()));
					}
					else
					{
						strAdmissionType="<option value='-1'>Select Value</option>";
						formBean.setStrAdmissionTypeValues(strAdmissionType);
					}
									
					formBean.setStrAddressModi(strAddressDepUbit.replace("^","#").split("#")[0]);
					formBean.setStrWardBedModi(strAddressDepUbit.replace("^","#").split("#")[1]);
					formBean.setStrEmgAddress(strAddressDepUbit.replace("^", "#").split("#")[2]);
					
					formBean.setStrDeptUnitName(voObj.getStrDeptName()+"/"+voObj.getStrUnitName());
					
					//formBean.setStrWardBedModi(PatientAdmissionTransHLP.getPatientWardDetailModi(voObj));
					if(voObj.getStrNewBorn().equals("1"))
					{
						strOccDtlsview = HLPOccupationDetails.getOccupationDetails(voObj.getStrMotherCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
						formBean.setStrBedCode(voObj.getStrBedCode());
					}
					
					else
					{
						strOccDtlsview = HLPOccupationDetails.getOccupationDetails(formBean.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
					}
					formBean.setStrIsAdmissionOnline(voObj.getStrIsAdmissionOnline());
					formBean.setOccupationDetailValues(strOccDtlsview);
					formBean.setStrWardTypeCode(voObj.getStrWardTypeCode());
					formBean.setStrWardCode(voObj.getStrWardCode());
					formBean.setStrBedTypeCode(voObj.getStrBedTypeCode());
					formBean.setStrRoomTypeCode(voObj.getStrRoomTypeCode());
					formBean.setStrWardName(voObj.getStrWardName());
					formBean.setStrDeptCode(voObj.getStrDeptCode());
					formBean.setStrTreatmentCategoryName(voObj.getStrTreatmentCategoryName());
					formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
					formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
					formBean.setStrIsUrban(voObj.getStrIsUrban());
					formBean.setStrConsultantName(voObj.getStrConsultantName());
					formBean.setStrConsultantCode(voObj.getStrConsultantCode());
					formBean.setStrEpisodeCode(voObj.getStrEpisodeCode());
					formBean.setStrVisitNo(voObj.getStrVisitNo());
					formBean.setStrMlcNo(voObj.getStrMlcNo());
					formBean.setStrAdviceAdmNo(voObj.getStrAdviceAdmNo());
					formBean.setStrBookingDate(voObj.getStrBookingDate());
					formBean.setStrNewBorn(voObj.getStrNewBorn());
					formBean.setStrMsApprovalFlag(voObj.getStrMsApprovalFlag());
					formBean.setStrBedCode(voObj.getStrBedCode());
					formBean.setStrRoomCode(voObj.getStrRoomCode());
					formBean.setStrRoom(voObj.getStrRoom());
					formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
					formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
					formBean.setStrMotherName(voObj.getStrMotherName());
					formBean.setStrMotherNationality(voObj.getStrMotherNationality());
					formBean.setStrMotherNationalityCode(voObj.getStrMotherNationalityCode());
					formBean.setStrMotherReligion(voObj.getStrMotherReligion());
					formBean.setStrMotherReligionCode(voObj.getStrMotherReligionCode());
					formBean.setStrMsApprovalStatus(voObj.getStrMsApprovalStatus());
					formBean.setStrDepartmentName(voObj.getStrDeptName());
					formBean.setStrDeptUnitName(voObj.getStrUnitName());
					formBean.setStrPrimaryCategoryCode(voObj.getStrPrimaryCategoryCode());
					
					//util.getOptionValue(voObj.getWrsAdmissionTypeValues(), "0","-1^Select Value",false);
					
					
					//checking if Admission charge required
					formBean.setStrAdmissionCharge(ipdConfigUtil.getStrAdmissionCharge());
					//Setting the Admission charge for that Patient (Depends on patient category)
					formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
					//formBean.setStrAdmissionType(voObj.getStrAdmissionType());
					
					if(voObj.getStrMsgType().equals("1"))
					{
						throw new Exception(voObj.getStrMsgString());
					}
			  }
			 if(voObj.getStrMsgType().equals("1"))
			 {
				throw new Exception(voObj.getStrMsgString());
			 }
		  }
			else
			{
				
				formBean.setStrMsgString("Invalid C.R No./Episode/Data not found");
				formBean.setStrCrNo("");
			}
			if(voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initAdmissionAdvice()", strmsgText);
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
	public static void initBedStatus(PatientAdmissionTransFB formBean,HttpServletRequest request) {
		String temp="";
		
		HisUtil util = null;
		try {
			
			
			
			PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
			PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
			
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrWardCode(request.getParameter("strWardCode"));
			vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
			vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrCrNo(request.getParameter("strCrNo"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrDeptName(request.getParameter("deptName"));
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
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				formBean.setStrDepartmentName(vo.getStrDeptName());
				formBean.setStrTreatmentCategoryCode(vo.getStrTreatmentCategoryCode());
				formBean.setStrDeptCode(vo.getStrDeptCode());	
				util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
				formBean.setStrDepartmentName(vo.getStrDeptName());
				formBean.setStrDeptCode(vo.getStrDeptCode());	
				util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
				if(vo.getStrMsApprovalFlag().equals("6") && vo.getStrMsApprovalStatus().equals("0"))
				{
					
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
				else
				{
					temp=util.getOptionValue(vo.getWardTypeWS(), vo.getStrWardTypeCode(),
						"0^Select Value", false);
					formBean.setStrwardType(temp);
					temp=util.getOptionValue(vo.getRoomTypeWS(), vo.getStrRoomTypeCode(),
							"0^Select Value", false);
					formBean.setStrRoomType(temp);
					temp=util.getOptionValue(vo.getWardWS(), vo.getStrWardCode(),
					"0^Select Value", false);
					 formBean.setStrWard(temp);
					 temp=util.getOptionValue(vo.getRoomWs(),vo.getStrRoomCode(),
					"0^Select Value", false);
					 formBean.setStrRoom(temp);
					 temp=util.getOptionValue(vo.getBedTypeWS(),vo.getStrBedTypeCode(),
					"0^Select Value", false);
					 formBean.setStrBedType(temp);
				}
				
			
		} catch (Exception e) {
			
			
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initBedStatus()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
	}
	/**
	 * This function is used to set initial parameters to search beds in particular ward
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void initBedDetails(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		
		PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		vo.setStrWardCode(request.getParameter("strWardCode"));
		vo.setStrRoom(request.getParameter("roomCode"));
		vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		bo.setBedDetails(vo);
		String res=PatientAdmissionTransHLP.getBedDetails(vo);
		try {
				
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(res+"^"+vo.getStrMsApprovalFlag());
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			   	HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initBedDetails()", strmsgText);
			    String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			    try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {
			    	
			    }
			     

		}
	}
	
/**
 * This function set initial parameters for ward combo when user change ward type on bed details pop up window
 * @param formBean
 * @param request
 * @param response
 */	
public static void initWardDetails(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		
		
		HisUtil util = null;
		try {
			PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
			PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
			String temp="";
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			if(formBean.getStrHiddenUnit()=="1")
			vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			else
				vo.setStrDeptUnitCode("");
			vo.setStrAgeUnit(request.getParameter("ageCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			vo.setStrCrNo(request.getParameter("strCrNo"));
			bo.setWardDetails(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				util = new HisUtil("Patient Admission Transaction",
				"PatientAdmissionDATA");
				temp=util.getOptionValue(vo.getWardWS(), "",
					"0^Select Value", false);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initWardDetails()", strmsgText);
			   String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			   try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {
			    	
			    }  
			   
		}
}
/**
 * This function is used to set initial parameters to bring room details on the basis of room type code
 * @param formBean
 * @param request
 * @param response
 */
public static void initRoomDetails(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
	HisUtil util = null;
	try {
		PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		String temp="";
		vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
		vo.setStrWardCode(request.getParameter("strWardCode"));
		vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		//vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		if((request.getParameter("deptUnitCode")!="" ))
		vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
		if((request.getParameter("deptCode")!=null))
		vo.setStrDeptCode(request.getParameter("deptCode"));
		else
			vo.setStrDeptCode("0");
		vo.setStrAgeUnit(request.getParameter("ageCode"));
		vo.setStrSexCode(request.getParameter("sexCode"));
		vo.setStrAge(request.getParameter("age"));
		vo.setStrCrNo(request.getParameter("strCrNo"));
		bo.setRoomDetails(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			throw new Exception(vo.getStrMsgString());
		}
			util = new HisUtil("Patient Admission Transaction",
			"PatientAdmissionDATA");
			temp=util.getOptionValue(vo.getRoomWs(),"",
					"0^Select Value", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp+"^"+vo.getStrAdmissionChargeValue());
		
	} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initRoomDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
		   try
		    {
		    	response.getWriter().print(response1);
		    	eObj=null;
		    }
		    catch(Exception e1)
		    {}  
	}
}
/**
 * This function invoke TransferObjectFactory.populateData() to transfer the values of all attributes of form bean into vo and the invoke bo insert method
 * @param formBean
 */
public static void insert(PatientAdmissionTransFB formBean,HttpServletRequest request)
{
		VisitorPassTransVO VisitorPassTransVO=null;
		String passNo[]=null;
		String passType[]=null;
		StringBuffer result=null;
		IpdConfigUtil ipdC=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		try
		{
			
			PatientAdmissionTransVO vo=(PatientAdmissionTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientAdmissionTransVO", formBean);
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//System.out.println("====================>>>>>>>>>>> pin code in insert() in data= "+vo.getStrPinCode());
			if(formBean.getStrHiddenUnit().equals("1"))
					vo.setStrDeptUnitName(formBean.getStrDepartmentName()+"/"+formBean.getStrDeptUnitName());
			else
			{
				vo.setStrDeptUnitName(formBean.getStrDepartmentName());
				//vo.setStrDeptUnitCode("0");
			}
			if(formBean.getStrHiddenRoom().equals("0"))
			{
					//vo.setStrRoomCode("0");
					vo.setStrRoom("");
			}
			else
			{
				vo.setStrRoomCode(formBean.getStrRoomCode());
				vo.setStrRoom(formBean.getStrRoom());
				
			}
			
			
			if(vo.getStrIsSingleWindowAdmission().equals("1"))
			{
				vo.setStrPaymentModeValue(formBean.getStrPayMode());
				vo.setStrPayDetail(formBean.getStrPayDetail());
			}
			
			//vo.setStrPaymentModeValue(formBean.getStrPayMode());
			
			
			vo.setStrAdmissionType(formBean.getStrAdmissionType());
			//System.out.println("admission type-------------------------->"+vo.getStrAdmissionType());
			vo.setStrSecondPersonName(formBean.getStrSecondPersonName());
			vo.setStrSecondPersonRelationCode(formBean.getStrSecondPersonRelationCode());
			vo.setStrFirstPersonName(formBean.getStrFirstPersonName());
			vo.setStrFirstPersonRelationCode(formBean.getStrFirstPersonRelationCode());
			vo.setStrEmgAddress1(formBean.getStrEmgAddress1());
			vo.setStrEmgAddress2(formBean.getStrEmgAddress2());
			
			vo.setStrWardName(formBean.getStrWard());
			vo.setStrCityLocation(formBean.getStrCityLocation());
			vo.setStrCaseSheetNo(formBean.getStrCaseSheetNo());
			
			vo.setStrDistrictCode(formBean.getStrDistrictCode());
			vo.setStrCountryName(formBean.getStrCountryName());
			vo.setStrStateName(formBean.getStrStateName());
			vo.setStrDistrict(formBean.getStrDistrictName());
			vo.setStrCountryCode(formBean.getStrCountry());
			
			

			if(!formBean.getStrCountry().equals("IND"))
			{
				vo.setStrDistrict(formBean.getStrDistrictName());
				//vo.setStrDistrict(formBean.getStrDistrictName());
				vo.setStrDistrictCode("0");
				vo.setStrState("0");
			}else{
				vo.setStrDistrict(formBean.getStrDistrictName());

			}
			
			PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
			VisitorPassTransVO=new VisitorPassTransVO();
			VisitorPassTransVO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			String printHLP="";
			vo.setStrCurrentDate(now());
			VisitorPassTransVO.setStrCrNo(vo.getStrCrNo());
			
			vo.setStrPhoneNo(formBean.getStrPhoneNo().equals("")? formBean.getStrMobileNo():formBean.getStrPhoneNo());
			//System.out.println("--------------->>>>>>>>>>> pin code data="+vo.getStrPinCode());
			vo.setStrBedCode(formBean.getStrBed());
			bo.insert(vo,VisitorPassTransVO);
			
			formBean.setStrBillNo(vo.getStrBillNo());
			
			
			/*if(ipdC.getStrAdvanceDepsoitAtAdmissionCounter().equals("1"))
					PrintHLP.ADVANCE(vo.getStrBillNo(), "2", vo.getStrHospCode(),"1", request, 0,"0","");*/
			
			
			/*System.out.println(" filepath  New "+request.getAttribute("filePath"));   */
			
			passNo = VisitorPassTransVO.getStrPassNo();
			passType = VisitorPassTransVO.getStrPassType();
			String email=formBean.getStrEmailId();
			//System.out.println("Email ID"+email+"phoneno"+vo.getStrPhoneNo());
			String smsMsg="Your IP registration completed  successfully in Ward "+vo.getStrWardName()+" with Admission No:"+vo.getStrAdmNo();
			
			
			
			final String   smsmessage  = smsMsg;
			final String patMobileNo=vo.getStrPhoneNo();
			/**Added by Vasu on 25.June for SMS Configuration*/
			if(patMobileNo!=null && !patMobileNo.equals("")){
			 //code from sending message through CDAC MUmbai SMS Gateway
			 //SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url, patientVO.getPatAddMobileNo(),message);
				new Thread( new Runnable() {
			           public void run(){

			        	 SMSHttpPostClient.sendSMS (patMobileNo,smsmessage);

			          return; // to stop the thread
			                          }
			         }).start();
			}
			
			//Code Commented For SMS Serv ice Service taking time so code started in new thread
			/*if(vo.getStrMsgType().equals("0"))
			{
				//NIC Gateway Code
				//SMSHttpsNICPostClient client = new SMSHttpsNICPostClient();				
				//SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway("","","","",vo.getStrPhoneNo(), smsMsg);
				//CDAC Mumbai Gateway Code 
				//SMSHttpPostClient client = new SMSHttpPostClient();
				SMSHttpPostClient.sendSMS (vo.getStrPhoneNo(), smsMsg);

				//if(email!=null && !email.equals(""))
					//client.sendEmail(email,"IPD Admission Confirmation from AIIMS Patna",smsMsg);
			}*/		
			
			if(vo.getStrMsgType().equals("0"))
			{
				formBean.setStrSaveFlag("1");				
				formBean.setStrPatientCrNo(formBean.getStrCrNo());
				result=new StringBuffer();
				//result.append("<table  align='center' border='0'>\n");
				result.append("Patient is Successfully Admitted with Admission No:"+vo.getStrAdmNo()+"");
				String PassName="";
				String strMsg="";
				if(passNo.length>0)
				{
					result.append("Visitor Pass Detail");
					result.append("Pass Type");
					result.append("Pass No");
					//result="Visitor Pass Detail<br>Pass Type\tPassNo<br>";
					for(int i=0;i<passNo.length;i++)
					{
						if(passType[i].equals("1"))
						{
							PassName="Free";
						}
						else
						{
							PassName="Attendant";
						}
						result.append(""+PassName+""+passNo[i]+"");
						strMsg+=""+PassName+passNo[i]+"<br>";
					  //  result+="<br>"+PassName+"\t"+passNo[i]+"<br>";
					}
				}
						
			
				//vo.setStrIsAdvanceAmountAtAdmission(ipdC.getStrAdmissionCharge());
				/*if(vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
				{
					bo.getDataForBillingslip(vo);
					if (!vo.getStrMsgType().equals("1")) 
					{
						PrintHLP.patientAdmissionBillService(vo,request);
						formBean.setStrNormalMsg("Receipt No. '"
								+ formBean.getStrBillNo()
								+ "/1"
								+ "' Generated Successfully for the Amount <img src='/AHIMS/hisglobal/images/INR.png'>'"
								+ HisUtil.getAmountWithDecimal(formBean
										.getStrOnlineTotalRecAmount(), 2) + "'");
	
						//String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
						//formBean.setStrFilePath(fileName);
					} 
					else 
					{
						throw new Exception(vo.getStrMsgString());
					}
				}*/
				formBean.setStrAdmNo(vo.getStrAdmNo());
				formBean.setStrMsg(result.toString());
				//formBean.setStrIsAdvanceAmountAtAdmission(vo.getStrIsAdvanceAmountAtAdmission());
			}
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{
				/* printHLP+="Admission Card\n\n";
				printHLP+="**************\n\n";
				
				try
				{
					StringBuilder sbAdd = new StringBuilder("");
					if(vo.getStrHouseNo()!=null && !vo.getStrHouseNo().trim().equals(""))		sbAdd.append(vo.getStrHouseNo());		
					if(vo.getStrStreet()!=null && !vo.getStrStreet().trim().equals(""))		{	sbAdd.append(", ");		sbAdd.append(vo.getStrStreet());	}
					if(vo.getStrCity()!=null && !vo.getStrCity().trim().equals(""))			{	sbAdd.append(", ");		sbAdd.append(vo.getStrCity());	}
					if(vo.getStrStateName()!=null && !vo.getStrStateName().trim().equals(""))		{	sbAdd.append(", ");		sbAdd.append(vo.getStrStateName());	}
					if(vo.getStrPinCode()!=null && !vo.getStrPinCode().trim().equals(""))			{	sbAdd.append(" - ");	sbAdd.append(vo.getStrPinCode());		}
					if(sbAdd.charAt(0) == ',') sbAdd.delete(0, 1);
					vo.setStrAddress(sbAdd.toString());
					printHLP+=ADTPrintingTransHLP.NewAdmission(vo,request);
					
				}
				catch(Exception _err)
				{
					formBean.setStrMsgString("Application Error [Either Printer is not working or not connected]");
				}
				*/
				try
				{
					if(ipdC.getStrAttendentPass().equals("1") && Integer.parseInt(ipdC.getStrNoOfFreePass())>0 && Integer.parseInt(ipdC.getStrNoOfPaidPass())>0)
					printHLP+="\n"+"\n"+"\nVisitor Pass\n"+ADTPrintingTransHLP.VisitorPass(VisitorPassTransVO, request);
				}
				catch(Exception _err)
				{
					_err.printStackTrace();
					formBean.setStrMsgString("Application Error [Either Printer is not working or not connected]");
				}
				finally{
					// out.close();
				}
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->insert()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	public static void printSlip(PatientAdmissionTransFB formBean) 
	{	
		PatientAdmissionTransVO voObj= null;
		PatientAdmissionTransBO bo= null;
		IpdConfigUtil ipdC=new IpdConfigUtil(formBean.getStrHospCode());
		StringBuffer sb1=new StringBuffer();
		
		try 
		{
			voObj =  new PatientAdmissionTransVO();
			bo    =  new PatientAdmissionTransBO();
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrAdmNo(formBean.getStrAdmNo());
			voObj.setStrPinCode(formBean.getStrPinCode());
			
			bo.printSlip(voObj);
			formBean.setStrDeptUnitName(voObj.getStrDeptName()+"/"+voObj.getStrUnitName());
			formBean.setStrWardName(voObj.getStrWardName());
			formBean.setStrConsultantName(voObj.getStrConsultantName());
			formBean.setStrMlcNo(voObj.getStrMlcNo());
			formBean.setStrRoom(voObj.getStrRoom());
			formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
			formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
			formBean.setStrMotherName(voObj.getStrMotherName());
			formBean.setStrDepartmentName(voObj.getStrDeptName());
			formBean.setStrDeptUnitName(voObj.getStrDeptUnitName());
			formBean.setStrPatName(voObj.getStrPatName());
			formBean.setStrAdmNo(voObj.getStrAdmNo());
			formBean.setStrAdmDateTime(voObj.getStrAdmDateTime());
			formBean.setStrFatherName(voObj.getStrFatherName());
			formBean.setStrHusbandName(voObj.getStrHusbandName());
			formBean.setStrAge(voObj.getStrAge());
			formBean.setStrAddress(voObj.getStrAddress());	
			formBean.setStrPhoneNo(voObj.getStrPhoneNo());
			//formBean.setStrPinCode(voObj.getStrPinCode());
			
			formBean.setStrMonthlyIncome(voObj.getStrMonthlyIncome());
			formBean.setStrIsNewBorn(voObj.getStrIsNewBorn());
			formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
			formBean.setStrMotherName(voObj.getStrMotherName());
			formBean.setStrBed(voObj.getStrBed());
			formBean.setStrMaritalStatus(voObj.getStrMaritalStatus());
			formBean.setStrHospDtl(voObj.getStrHospDtl());
			
			if(voObj.getStrEmgAddressFlag().equals("1"))
			{
				formBean.setStrEmgAddressFlag("1");
				formBean.setStrFirstPersonName(voObj.getStrFirstPersonName());
				formBean.setStrEmgAddress1(voObj.getStrEmgAddress1());
				formBean.setStrFirstpersonphone(voObj.getStrFirstpersonphone());
			}
			
			formBean.setStrPatCategoryName(voObj.getStrPatCategoryName());
			formBean.setStrPatientIdNumber(voObj.getStrPatientIdNumber());
			formBean.setStrPatientAdhaarNo(voObj.getStrPatientAdhaarNo());
			formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
			formBean.setStrAdmissionCharge(voObj.getStrAdmissionCharge());
			
			//formBean.setStrAdmissionCharge(ipdC.getStrAdmissionCharge());
			
			formBean.setStrDOB(voObj.getStrDobTime());
            formBean.setStrMaritalStatus(voObj.getStrMaritalStatus());
			formBean.setStrHouseNo(voObj.getStrHouseNo());	
			formBean.setStrStreet(voObj.getStrStreet());	
			formBean.setStrCity(voObj.getStrCity());    
			formBean.setStrDistrict(voObj.getStrDistrict());	
			formBean.setStrStateName(voObj.getStrStateName());
			//formBean.setStrPinCode(voObj.getStrPinCode()); 
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrAdvanceAmountReceiptNo(voObj.getStrAdvanceAmountReceiptNo());//Account No/Bill No
			formBean.setStrAdvanceAmountDate(voObj.getStrAdvanceAmountDate());
			formBean.setStrMobileNo(voObj.getStrMobileNo());
			formBean.setStrCaseSheetNo(voObj.getStrCaseSheetNo());
			formBean.setStrIsCreditAdvanceBilling(voObj.getStrIsCreditAdvanceBilling());
			formBean.setStrCreditLetterRefNo(voObj.getStrCreditLetterRefNo());
			formBean.setStrCreditLetterDate(voObj.getStrCreditLetterDate());
			formBean.setStrClientName(voObj.getStrClientName());
			formBean.setStrStaffNo(voObj.getStrStaffNo());
			formBean.setStrStaffName(voObj.getStrStaffName());
			formBean.setStrRelationship(voObj.getStrRelationship());
			formBean.setStrTehsil(voObj.getStrTehsil());
			formBean.setStrPolInfo(voObj.getStrPolInfo());
			formBean.setStrNamInf(voObj.getStrNamInf());
			formBean.setStrIdenMark(voObj.getStrIdenMark());
			formBean.setStrRefRem(voObj.getStrRefRem());
			formBean.setStrDetPs(voObj.getStrDetPs());
			formBean.setStrHospName(voObj.getStrHospName());
			formBean.setAdmissionTypeName(voObj.getAdmissionTypeName());
			formBean.setStrCountryName(voObj.getStrCountryName());
			formBean.setStrRegChg(voObj.getStrRegChg());
			
			StringBuffer sb=new StringBuffer();

			sb.append("CR No. :"+formBean.getStrCrNo());
			sb.append("\nAdmission No. :"+formBean.getStrAdmNo());
			sb.append("\nName :"+formBean.getStrPatName());
			sb.append("\nFather/Spouse Name :"+formBean.getStrFatherName()+"/"+(formBean.getStrHusbandName().equals("") ? "---" : formBean.getStrHusbandName()));
			sb.append("\nDepartment/Unit :"+ formBean.getStrDeptUnitName()); //(formBean.getStrDepartmentName().equals("") ? "--" :formBean.getStrDepartmentName()));
			sb.append("\nWard :"+formBean.getStrWardName());
			sb.append("\nAdmission Date :"+formBean.getStrAdmDateTime());
			sb.append("\nAge/Gender :"+formBean.getStrAge());
			sb.append("\nMobile No. :"+formBean.getStrMobileNo());
			sb.append("\nCategory :"+formBean.getStrPatCategoryName()+"/"+(formBean.getStrPatientIdNumber().equals("") ? "---" : formBean.getStrPatientIdNumber()));
			
			formBean.setStrPatCatGrp(voObj.getStrPatCatGrp());
			
			if(formBean.getStrPatCatGrp().equals("0"))//Paid
			{
				//sb1.append("<table width='100%' style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%' style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'><div align='left'><b>Advance Amount</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'><div align='left'><b>Reciept Amount</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'><div align='left'><b>Mode Of Payment</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'><div align='left'><b>Details</b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>Paying</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'>"+formBean.getStrAdvanceAmount()+"</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'>"+formBean.getStrAdvanceAmount()+"</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'>Cash</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'>"+formBean.getStrAdvanceAmountDate()+"/"+formBean.getStrAdvanceAmountReceiptNo()+"</td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}
			else if(formBean.getStrPatCatGrp().equals("3"))
			{
				//sb1.append("<table width='100%' style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'  style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'><div align='left'><b>Org. Name</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'><div align='left'><b>Letter No./Date</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='30%'><div align='left'><b>Eligible Amount</b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>Credit</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'>"+formBean.getStrClientName()+"</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'>"+formBean.getStrCreditLetterRefNo()+"/"+formBean.getStrCreditLetterDate()+"</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='30%'>"+formBean.getStrAdvanceAmount()+"</td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}
			
			else if(formBean.getStrPatCatGrp().equals("1"))//Staff
			{
				//sb1.append("<table width='100%'style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%' style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>Self/Depenedent</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='20%'><div align='left'><b>Name of Staff(if Dependent)</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>Relationhip</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>EC No.</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>SD By</b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>Satff</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}
			
			else if(formBean.getStrPatCatGrp().equals("4"))
			{
				//sb1.append("<table width='100%' style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%' style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>WAP/Card No.</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'><div align='left'><b>Code No.</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'><div align='left'><b>Eligible Amount</b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>ArogyaShree</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'>--</td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}			
			formBean.setStrPatCatSlip(sb1.toString());
			
			
			String qrCodeText=Qrcode.getText(sb.toString());
			String filePath2=HisUtil.getParameterFromHisPathXML("TEMP_PATH");
		    //String filePath = "C:\\PHDM\\AHIMS\\Qrcode images\\"+formBean.getStrAdmNo()+".png";
			String filePath = filePath2+ File.separator + formBean.getStrAdmNo()+".png";
		  //  String path_globalImages = resourceBundle.getValueFromKey("IMAGE_PATH_QR");
		    
		  //  System.out.println("path is "+path_globalImages);
		    
		    
		    int size = 1000;
	        String fileType = "png";
	        File qrFile = new File(filePath);
	        Qrcode.createQRImage(qrFile, qrCodeText, size, fileType);
	        
	        
			
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("ADT", "PatientAdmissionTransDATA->printSlip()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
			formBean.setStrCrNo("");
		}
}

	
	
	public static void printBillSlip(PatientAdmissionTransFB formBean) 
	{
	
		PatientAdmissionTransVO voObj= null;
		PatientAdmissionTransBO bo= null;
		try 
		{
			voObj =  new PatientAdmissionTransVO();
			bo    =  new PatientAdmissionTransBO();
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrAdmNo(formBean.getStrAdmNo());
			
			bo.getDataForBillingslip(voObj);
			
			
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("ADT", "PatientAdmissionTransDATA->printSlip()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
			formBean.setStrCrNo("");
		}
}
	public static void initUnit(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
	HisUtil util = null;
	try {
		PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		String temp="";
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		vo.setStrCrNo(request.getParameter("strCrNo"));
		
		vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
		if(request.getParameter("treatmentCategCode")!=null)
		vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		else
			vo.setStrTreatmentCategoryCode("0");
		if(request.getParameter("deptUnitCode")!=null)
		vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
		else
			vo.setStrDeptUnitCode("0");
		vo.setStrAgeUnit(request.getParameter("ageCode"));
		vo.setStrSexCode(request.getParameter("sexCode"));
		vo.setStrAge(request.getParameter("age"));
		
		bo.setUnitDetails(vo);
		
		if(vo.getStrMsgType().equals("1"))
		{
			throw new Exception(vo.getStrMsgString());
		}
		util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
		temp=util.getOptionValue(vo.getUnitWS(),"","0^Select Value", false);		
		temp+="^"+util.getOptionValue(vo.getWardWS(),"","0^Select Value", false);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(temp);	
		
	} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initRoomDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
		   try
		    {
		    	response.getWriter().print(response1);
		    	eObj=null;
		    }
		    catch(Exception e1)
		    {}  
	}
}
public static void initCat(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
	HisUtil util = null;
	try {
		PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		String temp="";
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrDeptUnitCode(request.getParameter("strDeptUnitCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		vo.setStrCrNo(request.getParameter("strCrNo"));
		bo.setTreatCat(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			throw new Exception(vo.getStrMsgString());
		}
		util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
		temp=util.getOptionValue(vo.getTreatmentCategWS(),vo.getStrTreatmentCategoryCode(),"0^Select Value", false);		
		temp+="^"+util.getOptionValue(vo.getConsultantWS(),"","0^Select Value", false);
		temp+="^"+util.getOptionValue(vo.getWardWS(),"","0^Select Value", false);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(temp);	
		//formBean.setStrTreatmentCategoryCode(vo.getStrTreatmentCategoryCode());
		
	} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initRoomDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
		   try
		    {
		    	response.getWriter().print(response1);
		    	eObj=null;
		    }
		    catch(Exception e1)
		    {}  
	}
}
public static void initChargeValue(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
{	
	try 
	{
		PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		
		vo.setStrWardCode(request.getParameter("wardCode"));
		vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		bo.setChargeVal(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			throw new Exception(vo.getStrMsgString());
		}			
			
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(vo.getStrAdmissionChargeValue()+"$"+vo.getStrAdvanceAmountVal());		
	} 
	catch (Exception e) 
	{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initChargeValue()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
		   try
		    {
		    	response.getWriter().print(response1);
		    	eObj=null;
		    }
		    catch(Exception e1)
		    {}  
	}
}

//ward
	public static void initConsWardDtl(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
	HisUtil util = null;
	try {
		
		PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		String temp="";
		vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		//vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrAgeUnit(request.getParameter("ageCode"));
		vo.setStrSexCode(request.getParameter("sexCode"));
		vo.setStrAge(request.getParameter("age"));
		vo.setStrCrNo(request.getParameter("strCrNo"));
		bo.setWardConsultant(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			throw new Exception(vo.getStrMsgString());
		}
			util = new HisUtil("Patient Admission Transaction",
			"PatientAdmissionDATA");
			
			
			temp=util.getOptionValue(vo.getConsultantWS(),"","0^Select Value", false);		
			temp+="^"+util.getOptionValue(vo.getWardWS(),"","0^Select Value", false);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);	
			
		
	} catch (Exception e) {
		
		
		
		
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initRoomDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
		   try
		    {
		    	response.getWriter().print(response1);
		    	eObj=null;
		    }
		    catch(Exception e1)
		    {}  
	}
}
	
	//Populate ward on the basis of Treatment Category
	public static void initTreatmenyCatWardDtl(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		HisUtil util = null;
		try {
			
			PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
			PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
			String temp="";
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			//vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrAgeUnit(request.getParameter("ageCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			vo.setStrCrNo(request.getParameter("strCrNo"));
			bo.setTreatmentCatWard(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
				util = new HisUtil("Patient Admission Transaction",
				"PatientAdmissionDATA");
				
				
				temp=util.getOptionValue(vo.getWardWS(),"","0^Select Value", false);
				formBean.setStrWard(vo.getStrWardName());
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);	
				
			
		} catch (Exception e) {
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initRoomDetails()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			   try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {}  
		}
	}
	
	public static void initWardonUnit(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		HisUtil util = null;
		try {
			
			PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
			PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
			String temp="";
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			vo.setStrDeptUnitCode(request.getParameter("strDeptUnitCode"));
			vo.setStrAgeUnit(request.getParameter("ageCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			vo.setStrCrNo(request.getParameter("strCrNo"));
			bo.setinitWardonUnit(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
				util = new HisUtil("Patient Admission Transaction",
				"PatientAdmissionDATA");
				
				
				temp=util.getOptionValue(vo.getWardWS(),"","0^Select Value", false);
				formBean.setStrWard(vo.getStrWardName());
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);	
				
			
		} catch (Exception e) {
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initRoomDetails()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			   try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {}  
		}
	}

	public static void initDistrict(PatientAdmissionTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		HisUtil util = null;
		try {
			
			PatientAdmissionTransVO vo=new PatientAdmissionTransVO();
			PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
			String temp="";
			vo.setStrHospCode(formBean.getStrHospCode());
			
			
			vo.setStrStateCode(request.getParameter("stateCode"));
			bo.getDistrict(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			util = new HisUtil("Patient Admission Transaction",
					"PatientAdmissionDATA");
					
					
					temp=util.getOptionValue(vo.getDistrictWS(),vo.getStrDistrictCode(),"0^Select Value", false);
					//formBean.setStrDistrict(vo.getStrDistrict());
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(temp);	
				
				
			
		} catch (Exception e) {
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initDistrict()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			   try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {}  
		}
}
	
	/**
	 * This function is used to set initial parameters required to display on main page 
	 * @param formBean
	 */
	public static void initPatientAdmissionBS(PatientAdmissionTransFB formBean) 
	{		
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		PatientAdmissionTransVO voObj= null;
		PatientAdmissionTransBO bo= null;
		String strAddressDepUbit="";
		String strEmgAddress="";
		String strAdmissionType="";
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(formBean.getStrHospCode());
		HisUtil hisUtil=null;
		
		try 
		{
			formBean.setStrFatherNameMandatoryFlag(IpdTransConfig.getMandatoryFatherNameAddmision());
			icu   =  new IpdConfigUtil(formBean.getStrHospCode());
			voObj =  new PatientAdmissionTransVO();
			bo    =  new PatientAdmissionTransBO();
			hisUtil = new HisUtil("Billing", "CashCollectionTransDATA");
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
			voObj.setStrAdmissionAdviceValidityFrom(ipdConfig.getStrAdmissionAdviceValidityFrom());
			voObj.setStrAdmissionAdviceValidityTo(ipdConfig.getStrAdmissionAdviceValidityTo());
			voObj.setStrIsAdmissionOnline(ipdConfig.getStrAdmissionOnline());
			voObj.setStrIsIntegratedWithBilling(ipdConfig.getStrIntegrationBilling());
			voObj.setStrIsAdvanceAmountAtAdmission(ipdConfig.getStrAdvanceAmountAdmission());
			voObj.setStrAdmissionCharge(ipdConfig.getStrAdmissionCharge());
			voObj.setStrDeptWardChangeChk(formBean.getStrDeptWardChangeChk());
			voObj.setStrIsSingleWindowAdmission(formBean.getIsSingleMenu());
		//	voObj.setStrDeptUnitCode("0");
			bo.setPatientDtl(voObj);
			formBean.setStrIsIntegratedWithBilling(voObj.getStrIsIntegratedWithBilling());
			formBean.setStrIsAdvanceAmountAtAdmission(voObj.getStrIsAdvanceAmountAtAdmission());
			formBean.setStrIsAdvanceAmountAtAdmissionTaken(voObj.getStrIsAdvanceAmountAtAdmissionTaken());
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrAdvanceAmountDate(voObj.getStrAdvanceAmountDate());
			formBean.setStrAdvanceAmountReceiptNo(voObj.getStrAdvanceAmountReceiptNo());
			formBean.setStrAgeUnit(voObj.getStrAgeUnit());
			formBean.setStrSexCode(voObj.getStrSexCode());
			formBean.setStrAge(voObj.getStrAge());			
			formBean.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yyyy"));
			//formBean.setStrAdmissionType("0");
			formBean.setStrPatIsUnknown(voObj.getStrPatIsUnknown());
			formBean.setStrMsAppStatus(voObj.getStrMsAppStatus());
			formBean.setStrIcuWardType(ipdConfigUtil.getStrIcuWardType());
			formBean.setStrPvtWardType(ipdConfigUtil.getStrPrivateWardType());
			formBean.setBillcount(voObj.getBillcount());
			formBean.setStrPinCode(voObj.getStrPinCode());
			
			/*if(!voObj.getStrCountryCode().equals("IND"))
			{
				//bo.setStateInputForeign(voObj);
				formBean.setStrStateName(voObj.getStrStateName());
				formBean.setStrDistrictName(voObj.getStrDistrict());
				
			}*/
			if(ipdConfigUtil.getStrUnitNameReq().equals("1"))
			{
				formBean.setStrHiddenUnit("1");
			}
			else
				formBean.setStrHiddenUnit("0");
			if(ipdConfigUtil.getStrRoomNoReq().equals("1"))
			formBean.setStrHiddenRoom("1");
			else
				formBean.setStrHiddenRoom("0");
			if(voObj.getStrCrNoValid().equals("1"))
			{
				formBean.setStrMsgString("Invalid CR No./Data not found/Visit Details not found in Current Hospital");
				formBean.setStrCrNo("");
			}
		/*	else if(voObj.getStrIsAdvanceAmountAtAdmissionTaken().equals("0"))
			{
				formBean.setStrMsgString("Advance Amount is not deposited");
				formBean.setStrCrNo("");
			}*/
			else
			{
			
			
			if(voObj.getStrCrNoValid().equals("0"))
			{
				formBean.setStrNoOfFreePass(ipdConfig.getStrNoOfFreePass());
				formBean.setStrAdviceStatus(voObj.getStrAdviceStatus());
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrIsDead(voObj.getStrIsDead());
				formBean.setStrFreePassValid(ipdConfig.getStrNoOfFreePassAdmisssionTime());
				
				if(!(formBean.getStrPatStatusCode().equals(IpdTransConfig.getAdmittedCode()) || formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode())))
				{
					strAddressDepUbit=PatientAdmissionTransHLP.getPatientDetailModi_Bootstrap(voObj);
					//formBean.setStrAdmissionType("0");
					HisUtil util = new HisUtil("Patient Admission Trans","PatientAdmissionDATA");
					
					formBean.setStrPayMode(util.getOptionValue(voObj.getStrPaymentMode(), "0","", false));
					
					
					
					if(voObj.getWrsAdmissionTypeValues()!=null && voObj.getWrsAdmissionTypeValues().size()>0)
					{
						strAdmissionType=util.getOptionValue(voObj.getWrsAdmissionTypeValues(), "0","", false);
						formBean.setStrAdmissionTypeValues(strAdmissionType);
						//admissionTypeName
						//formBean.setAdmissionTypeName(voObj.getWrsAdmissionTypeValues().getString(formBean.getStrAdmissionType()));
					}
					else
					{
						strAdmissionType="<option value='-1'>Select Value</option>";
						formBean.setStrAdmissionTypeValues(strAdmissionType);
					}
									
					formBean.setStrAddressModi(strAddressDepUbit.replace("^","#").split("#")[0]);
					formBean.setStrWardBedModi(strAddressDepUbit.replace("^","#").split("#")[1]);
					formBean.setStrEmgAddress(strAddressDepUbit.replace("^", "#").split("#")[2]);
					
					formBean.setStrDeptUnitName(voObj.getStrDeptName()+"/"+voObj.getStrUnitName());
					
					//formBean.setStrWardBedModi(PatientAdmissionTransHLP.getPatientWardDetailModi(voObj));
					if(voObj.getStrNewBorn().equals("1"))
					{
						strOccDtlsview = HLPOccupationDetails.getOccupationDetails_Bootstrap(voObj.getStrMotherCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
						formBean.setStrBedCode(voObj.getStrBedCode());
					}
					
					else
					{
						strOccDtlsview = HLPOccupationDetails.getOccupationDetails_Bootstrap(formBean.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
					}
					formBean.setStrIsAdmissionOnline(voObj.getStrIsAdmissionOnline());
					formBean.setOccupationDetailValues(strOccDtlsview);
					formBean.setStrWardTypeCode(voObj.getStrWardTypeCode());
					formBean.setStrWardCode(voObj.getStrWardCode());
					formBean.setStrBedTypeCode(voObj.getStrBedTypeCode());
					formBean.setStrRoomTypeCode(voObj.getStrRoomTypeCode());
					formBean.setStrWardName(voObj.getStrWardName());
					formBean.setStrDeptCode(voObj.getStrDeptCode());
					formBean.setStrTreatmentCategoryName(voObj.getStrTreatmentCategoryName());
					formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
					formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
					formBean.setStrIsUrban(voObj.getStrIsUrban());
					formBean.setStrConsultantName(voObj.getStrConsultantName());
					formBean.setStrConsultantCode(voObj.getStrConsultantCode());
					formBean.setStrEpisodeCode(voObj.getStrEpisodeCode());
					formBean.setStrVisitNo(voObj.getStrVisitNo());
					formBean.setStrMlcNo(voObj.getStrMlcNo());
					formBean.setStrAdviceAdmNo(voObj.getStrAdviceAdmNo());
					formBean.setStrBookingDate(voObj.getStrBookingDate());
					formBean.setStrNewBorn(voObj.getStrNewBorn());
					formBean.setStrMsApprovalFlag(voObj.getStrMsApprovalFlag());
					formBean.setStrBedCode(voObj.getStrBedCode());
					formBean.setStrRoomCode(voObj.getStrRoomCode());
					formBean.setStrRoom(voObj.getStrRoom());
					formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
					formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
					formBean.setStrMotherName(voObj.getStrMotherName());
					formBean.setStrMotherNationality(voObj.getStrMotherNationality());
					formBean.setStrMotherNationalityCode(voObj.getStrMotherNationalityCode());
					formBean.setStrMotherReligion(voObj.getStrMotherReligion());
					formBean.setStrMotherReligionCode(voObj.getStrMotherReligionCode());
					formBean.setStrMsApprovalStatus(voObj.getStrMsApprovalStatus());
					formBean.setStrDepartmentName(voObj.getStrDeptName());
					formBean.setStrDeptUnitName(voObj.getStrUnitName());
					formBean.setStrPrimaryCategoryCode(voObj.getStrPrimaryCategoryCode());
					
					//util.getOptionValue(voObj.getWrsAdmissionTypeValues(), "0","-1^Select Value",false);
					
					
					//checking if Admission charge required
					formBean.setStrAdmissionCharge(ipdConfigUtil.getStrAdmissionCharge());
					//Setting the Admission charge for that Patient (Depends on patient category)
					formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
					
					formBean.setStrAdvanceDepsoitAtAdmissionCounter(ipdConfigUtil.getStrAdvanceDepsoitAtAdmissionCounter());
					
					
					
					//formBean.setStrAdmissionType(voObj.getStrAdmissionType());
					
					if(voObj.getStrMsgType().equals("1"))
					{
						throw new Exception(voObj.getStrMsgString());
					}
			  }
			 if(voObj.getStrMsgType().equals("1"))
			 {
				throw new Exception(voObj.getStrMsgString());
			 }
		  }
			else
			{
				
				formBean.setStrMsgString("Invalid C.R No./Episode/Data not found");
				formBean.setStrCrNo("");
			}
			if(voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionTransDATA->initPatientAdmissionBS()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
			     formBean.setStrCrNo("");
		}
	}
	public static void getPayMode(PatientAdmissionTransFB formBean,
			HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
			PatientAdmissionTransBO bo=null;
			PatientAdmissionTransVO vo=null;
			HisUtil util=null;
			try{
				util = new HisUtil("Patient Admission Trans","PatientAdmissionDATA");
				bo    =  new PatientAdmissionTransBO();
				vo	  =  new PatientAdmissionTransVO();
				
				vo.setStrHospCode(formBean.getStrHospCode());
				vo.setStrPatCatCode(req.getParameter("tcat"));
				
				bo.getPayMode(vo);
				
				res.setHeader("Cache-Control", "no-cache");
				res.getWriter().print(util.getOptionValue(vo.getStrPaymentMode(), "0","", false));	
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		
	}	
}


