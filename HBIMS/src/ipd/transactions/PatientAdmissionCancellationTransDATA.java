package ipd.transactions;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class  PatientAdmissionCancellationTransDATA {

	/**
	 * This function is used to set initial parameters required to display on main page 
	 * @param formBean
	 */
	public static void initPatientAdmission(PatientAdmissionCancellationTransFB formBean) 
	{		
		PatientAdmissionCancellationTransVO voObj= null;
		PatientAdmissionCancellationTransBO bo= null;
		HisUtil util = null;
		String temp="";
		try 
		{			
			voObj =  new PatientAdmissionCancellationTransVO();
			bo    =  new PatientAdmissionCancellationTransBO();
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			bo.setPatientDtl(voObj);
			formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
			if(voObj.getStrValidCrNo().equals("1"))
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Invalid CR No. or Data Not found");
			}
			else if(voObj.getStrIsNotAdmitted().equals("1"))
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is Not Admitted");
			}
			else if(voObj.getStrIsAccepted().equals("1"))
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient has been accepted,No cancellation can be done");
			}
			else if (Integer.parseInt(voObj.getStrCheckForServiceAndAdvance()) > 0) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Admission Can Not Be Cancelled As Billing Service/Part Payment has Been Availed for this Account");
			}
			else
			{
			util = new HisUtil("Patient Admission Cancellation Trans","PatientAdmissionCancellationTransDATA");
				/*temp = util.getOptionValue(voObj.getDepartWS(), voObj.getStrDeptCode(),
								"0^Select Value", false);
				formBean.setStrDeptValue(temp);
				temp = util.getOptionValue(voObj.getUnitWS(), voObj.getStrDeptUnitCode(),
					"0^Select Value", false);
				formBean.setStrUnitValue(temp);	*/
				temp = util.getOptionValue(voObj.getConsultantWS(), "0","0^Select Value", false);
				formBean.setStrConsultantValues(temp);
				IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
				//formBean.setStrAdmissionChargeValue(ipdConfig.getStrNewBornBabyAdmissionCharge());
				formBean.setStrNewBornRegistrationCharge(ipdConfig.getStrNewBornBabyRegistrationCharge());
				formBean.setStrAddressModi(PatientAdmissionCancellationTransHLP.getPatientDetailModi(voObj));
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
				formBean.setStrAdmNo(voObj.getStrAdmNo());
				formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
				formBean.setStrAdmissionCharge(voObj.getStrAdmissionCharge());
				
				formBean.setStrAdvanceDepositStatus(voObj.getStrAdvanceDepositStatus());
				formBean.setStrAdvanceDetails(voObj.getStrAdvanceDetails());
				
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
			   HisException eObj = new HisException("IPD", "PatientAdmissionCancellationTransDATA->initAdmissionAdvice()", strmsgText);
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
	public static void initBedStatus(PatientAdmissionCancellationTransFB formBean,HttpServletRequest request) {
		String temp="";
		NewBornBabyTransVO vo=new NewBornBabyTransVO();
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
		vo.setStrWardCode(request.getParameter("wardCode"));
		vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
		vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrCrNo(request.getParameter("crNo"));
		//vo.setStrHospCode(formBean.getStrHospCode());
		vo.setStrHospCode("108");
		bo.setBedStatusDtl(vo);
		formBean.setStrDepartmentName(vo.getStrDeptName());
		formBean.setStrDeptCode(vo.getStrDeptCode());
		HisUtil util = null;
		try {
				/*if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}*/
				util = new HisUtil("Patient Admission Cancellation Trans",
				"PatientAdmissionCancellationTransDATA");
				temp=util.getOptionValue(vo.getWardTypeWS(), vo.getStrWardTypeCode(),
					"0^Select Value", false);
				formBean.setStrwardType(temp);
				temp=util.getOptionValue(vo.getWardWS(), vo.getStrWardCode(),
						"0^Select Value", false);
					formBean.setStrWard(temp);
					
				temp=util.getOptionValue(vo.getRoomTypeWS(), vo.getStrRoomTypeCode(),
					"0^Select Value", false);
				formBean.setStrRoomType(temp);
				
				temp=util.getOptionValue(vo.getRoomWs(),"",
					"0^Select Value", false);
				formBean.setStrRoom(temp);
				temp=util.getOptionValue(vo.getBedTypeWS(),vo.getStrBedTypeCode(),
					"0^Select Value", false);
				formBean.setStrBedType(temp);
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
				
			   HisException eObj = new HisException("IPD", "PatientAdmissionCancellationTransDATA->initBedStatus()", strmsgText);
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
	public static void initBedDetails(PatientAdmissionCancellationTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		
		NewBornBabyTransVO vo=new NewBornBabyTransVO();
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		vo.setStrWardCode(request.getParameter("wardCode"));
		vo.setStrRoom(request.getParameter("roomCode"));
		vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		bo.setBedDetails(vo);
		String res=NewBornBabyTransHLP.getBedDetails(vo);
		try {
				
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(res+"^"+vo.getStrMsApprovalFlag());
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionCancellationTransDATA->initBedDetails()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
	}
	
/**
 * This function set initial parameters for ward combo when user change ward type on bed details pop up window
 * @param formBean
 * @param request
 * @param response
 */	
public static void initWardDetails(PatientAdmissionCancellationTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		
		NewBornBabyTransVO vo=new NewBornBabyTransVO();
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		String temp="";
		vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		bo.setWardDetails(vo);
		HisUtil util = null;
		try {
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				util = new HisUtil("Patient Admission Cancellation Trans",
				"PatientAdmissionCancellationTransDATA");
				temp=util.getOptionValue(vo.getWardWS(), "",
					"0^Select Value", false);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAdmissionCancellationTransDATA->initWardDetails()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
}
/**
 * This function is used to set initial parameters to bring room details on the basis of room type code
 * @param formBean
 * @param request
 * @param response
 */
public static void initRoomDetails(PatientAdmissionCancellationTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
	
	NewBornBabyTransVO vo=new NewBornBabyTransVO();
	NewBornBabyTransBO bo=new NewBornBabyTransBO();
	String temp="";
	vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
	vo.setStrWardCode(request.getParameter("wardCode"));
	vo.setStrHospCode(formBean.getStrHospCode());
	bo.setRoomDetails(vo);
	HisUtil util = null;
	try {
			util = new HisUtil("Patient Admission Cancellation Trans",
			"PatientAdmissionCancellationTransDATA");
			temp=util.getOptionValue(vo.getRoomWs(),"",
					"0^Select Value", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "PatientAdmissionCancellationTransDATA->initRoomDetails()", strmsgText);
		   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
	}
}
/**
 * This function invoke TransferObjectFactory.populateData() to transfer the values of all attributes of form bean into vo and the invoke bo insert method
 * @param formBean
 */
public static void insert(PatientAdmissionCancellationTransFB formBean)
{
		try
		{
			PatientAdmissionCancellationTransVO vo=(PatientAdmissionCancellationTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientAdmissionCancellationTransVO", formBean);
			PatientAdmissionCancellationTransBO bo=new PatientAdmissionCancellationTransBO();
			IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
			vo.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
			vo.setStrAdvanceRefund(ipdConfig.getStrRefundRequestAdmissionCancellation());
			bo.insert(vo);
			if(vo.getStrMsgType().equals("0"))
			{
				formBean.setStrMsg("Admission Cancelled Successfully.");
			}
			
		}
		catch(Exception e)
		{
				
				String strmsgText = e.getMessage();
			    HisException eObj = new HisException("IPD", "PatientAdmissionCancellationTransDATA->insert()", strmsgText);
			    formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
	}
public static void initUnitDtl(PatientAdmissionCancellationTransFB formBean,HttpServletRequest request,HttpServletResponse response)
{
	NewBornBabyTransVO vo=null;
	NewBornBabyTransBO bo=null;
	HisUtil util = null;
	String temp;
	try
	{
		
		vo=new NewBornBabyTransVO();
		bo=new NewBornBabyTransBO();
		vo.setStrMotherDeptCode(request.getParameter("deptCode"));
		vo.setStrHospCode("108");
		bo.setUnitValue(vo);
		util = new HisUtil("Patient Admission Cancellation Trans",
		"PatientAdmissionCancellationTransDATA");
		temp = util.getOptionValue(vo.getUnitWS(), "0",
		"0^Select Value", false);
		response.getWriter().write(temp);
	}
	catch(Exception e)
	{
			String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "PatientAdmissionCancellationTransDATA->initUnitDtl()", strmsgText);
		   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
	}
}
}
