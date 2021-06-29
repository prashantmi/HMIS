package registration.transactions.controller.data;

/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import vo.registration.AddressVO;
import vo.registration.PatientBroughtByDtlVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.UnknownChangeDtlVO;
import vo.registration.UnknownVerificationDtlVO;

public class UnknownToKnownDATA extends ControllerDATA
{

	/**
	 * sets patient's address details in AddressVO
	 * 
	 * @param _patVO Provides patient details.
	 * @param _userVO Provides User details.
	 * @return array of addressVO
	 */
	public static AddressVO[] getAddressEssentials(PatientVO _patVO, UserVO _userVO)
	{
		PatientBO bo = new PatientBO();
		return bo.getPatAddressAll(_patVO, _userVO);
	}
	/**
	 * Gets Initial Patient Modification Address essentials
	 * @return Map containing Initial address essentials
	 */
	public static Map getInitialPatientModAddDetailEssentials()
	{
		Map mp = new HashMap();
		mp.put(RegistrationConfig.REGDESK_ADDRESSVO_ARR, new AddressVO[]
		{});
		return mp;
	}

	public static Map getEmgPatModFormEssentials_AJAX(UserVO _UserVO, String strStateCode)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getEmgPatModFormEssentials_AJAX(_UserVO,strStateCode);
		
		return mp;		
	}
	/**
	 * updates address detail modification record
	 * 
	 * @param arrAddressVO array of addressVO
	 * @param _verDocVO verification doc vo
	 * @param _userVO Provides User details.
	 * @param objStatus
	 * @param add_modify string containing address to modify/add
	 * @return
	 */
	public static int updateRecord(PatientVO _patientVO, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			UserVO _userVO,PatientIdVO objPatientIdVO_p,UnknownVerificationDtlVO UnknownVerificationDtlVO_p,UnknownChangeDtlVO UnknownChangeDtlVO_p, HttpServletRequest objRequest_p)
	{
		PatientBO  patientBO=new PatientBO();
		int x = patientBO.unknownToKnown(_patientVO, _patientVOOldData, addModify, _arrAddressVO, UnknownVerificationDtlVO_p,UnknownChangeDtlVO_p, _userVO,objPatientIdVO_p,objRequest_p);
		return x;
	}
	

	/**
	 * sets patient's address details in AddressVO
	 * 
	 * @param _patVO Provides patient details.
	 * @param _userVO Provides User details.
	 * @return array of addressVO
	 */
	public static PatientBroughtByDtlVO getBroughtByDetails(PatientBroughtByDtlVO PatientBroughtByDtlVO_p, UserVO _userVO)
	{
		PatientBO bo = new PatientBO();
		return bo.getPatBroughtByDetials(PatientBroughtByDtlVO_p, _userVO);
	}
	
}
