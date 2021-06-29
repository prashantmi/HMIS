package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.transactions.controller.actionsupport.EmgMlcDtlSUP;
import registration.transactions.controller.data.EmgMlcDtlDATA;
import registration.transactions.controller.data.EmgPatDetailModificationDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.registration.EpisodeVO;
import vo.registration.MlcParameterDtlVO;
import vo.registration.MlcVO;
import vo.registration.PatientBroughtByDtlVO;
import vo.registration.PatientVO;
import vo.registration.PoliceVerificationDtlVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;

public class EmgMlcDtlUTIL extends ControllerUTIL{
	public static void setMlcOnlineOfflineMode(EmgMlcDtlSUP objEmgMlcDtlSUP_p,HttpServletRequest objRequest_p)
	{
		RegistrationConfigVO voRegistrationConfig= null;
		try{
			System.out.println("EmgMlcDtlUTIL :: setMlcOnlineOfflineMode()");
			UserVO objUserVO =getUserVO(objRequest_p);		
			voRegistrationConfig = EmgMlcDtlDATA.getRegistrationConfigDtl(objUserVO);
			if(voRegistrationConfig!=null){
				objEmgMlcDtlSUP_p.setFlagMlcOnlineOffline(voRegistrationConfig.getStrMlcMode());
				System.out.println("StrMlcMode :"+objEmgMlcDtlSUP_p.getFlagMlcOnlineOffline());
				if(objEmgMlcDtlSUP_p.getFlagMlcOnlineOffline()==null || objEmgMlcDtlSUP_p.getFlagMlcOnlineOffline().equals(""))
					throw new HisRecordNotFoundException("Please Select MLC Mode (Online/Offline) first.");
			}
		}catch(HisRecordNotFoundException e){
	   		objEmgMlcDtlSUP_p.setErrorMessage("Please Select MLC Mode (Online/Offline) first.");
	   		//throw new HisRecordNotFoundException("");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void setEpisodeDetailsForMlc( EmgMlcDtlSUP objEmgMlcDtlSUP_p,HttpServletRequest objRequest_p )
	{		
		EpisodeVO[] objArrEpisodeVO=null ;
		List<PatientBroughtByDtlVO> lstPatientBroughtByDtlVO=new ArrayList<PatientBroughtByDtlVO>();
		Map map = null;
		PatientVO objPatientVO = new PatientVO();
		String strMode;
		try{
			System.out.println("EmgMlcDtlUTIL :: setEpisodeDetailsForMlc()");
			UserVO objUserVO =getUserVO(objRequest_p);		
			objPatientVO.setPatCrNo(objEmgMlcDtlSUP_p.getPatCrNo());
			objPatientVO=PatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, "");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			
			String sys=(String)objRequest_p.getSession().getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			objEmgMlcDtlSUP_p.setHiddenTimeHr(time.split(":")[0]);
			objEmgMlcDtlSUP_p.setHiddenTimeMin(time.split(":")[1]);
			
			if(objEmgMlcDtlSUP_p.getIsDesk()!=null && objEmgMlcDtlSUP_p.getIsDesk().equals("1")){
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
				objEmgMlcDtlSUP_p.setVisitDate(sdf.format(new Date()));
			}
			
			//objPatientVO.setPatCrNo(objEmgMlcDtlSUP_p.getPatCrNo());
			   //Added by Vasu on 04.May.18 to get non-MLC episodes
			   //if(objPatientVO.getIsMLC().equals("0"))
			   //{
				   //strMode = "2";
			   //}
			   //else{
				strMode= EmgMlcDtlSUP.flagMlcOnlineOffline;
			   //}
				objArrEpisodeVO=EmgMlcDtlDATA.getEpisodeDtlForMLC(objUserVO, objEmgMlcDtlSUP_p.getPatCrNo(), objEmgMlcDtlSUP_p.getVisitDate(),strMode);
				System.out.println("objArrEpisodeVO.length :"+objArrEpisodeVO!=null?objArrEpisodeVO.length:0);
			
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.EMGREGISTRATIONDESK_MLC_EPISODEVO_ARR, objArrEpisodeVO);
			
			PatientBroughtByDtlVO objPatientBroughtByDtlVO = new PatientBroughtByDtlVO();
			objPatientBroughtByDtlVO.setPatCrNo(objEmgMlcDtlSUP_p.getPatCrNo());
			
			try{
				lstPatientBroughtByDtlVO=	EmgMlcDtlDATA.getBroughtByDetails(objPatientBroughtByDtlVO, objUserVO);
			}catch(Exception exc){
				if(exc.getClass()!=HisRecordNotFoundException.class)
					throw new Exception();
			}finally{
				WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.EMG_LISTOF_BROUGHTBY_DETAILS, lstPatientBroughtByDtlVO);
				System.out.println("setEpisodeDetailsForMlc -->> lstPatientBroughtByDtlVO.size :: "+lstPatientBroughtByDtlVO.size());
			}
			//HelperMethods.populate(objEmgMlcDtlSUP_p, objPatientBroughtByDtlVO);
			
			System.out.println("ConstableDesig :"+objEmgMlcDtlSUP_p.getConstableDesig());
			System.out.println("ConstableBadgeNo :"+objEmgMlcDtlSUP_p.getConstableBadgeNo());
			
			map=EmgMlcDtlDATA.getEssentials(objUserVO);
			WebUTIL.setMapInSession(map, objRequest_p,"EmgMlcDtlACTION");
			
			if(EmgMlcDtlSUP.flagMlcOnlineOffline.equals("2") && objEmgMlcDtlSUP_p.getVisitDate().equalsIgnoreCase(objEmgMlcDtlSUP_p.getEntryDate()))
					objEmgMlcDtlSUP_p.setIsTodayOfflineVisit("1");
				else
					objEmgMlcDtlSUP_p.setIsTodayOfflineVisit("0");
			
		}catch(HisRecordNotFoundException e){
	   		objEmgMlcDtlSUP_p.setErrorMessage(e.getMessage());
	   		if(e.getMessage()!=null && e.getMessage().trim().equals("Patient Details Not Found")){
	   			objEmgMlcDtlSUP_p.setAfterGo("0");
	   			if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   			{
	   				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   			}
	   		}else{
	   			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.EMGREGISTRATIONDESK_MLC_EPISODEVO_ARR, objArrEpisodeVO);
	   		}
	   		//throw new HisRecordNotFoundException("");
		}
		catch(HisException e){
			objEmgMlcDtlSUP_p.setErrorMessage("Error, Contact System Administrator.");
			objEmgMlcDtlSUP_p.setAfterGo("0");
		}
		catch(Exception e){
			objEmgMlcDtlSUP_p.setErrorMessage("Error, Contact System Administrator.");
			objEmgMlcDtlSUP_p.setAfterGo("0");
		}				
	}
	
	public static void setBroughtByDtl(String  strVisitNoIndx_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p)
	{
		int nVisitNoIndx= Integer.parseInt(strVisitNoIndx_p);
		HttpSession objSession = objRequest_p.getSession();
		PatientBroughtByDtlVO objPatientBroughtByDtlVO = new PatientBroughtByDtlVO();
		List<PatientBroughtByDtlVO> lstPatientBroughtByDtlVO= (List<PatientBroughtByDtlVO>)objSession.getAttribute(RegistrationConfig.EMG_LISTOF_BROUGHTBY_DETAILS);
		String strBroughtByJsonObj="";
		if(lstPatientBroughtByDtlVO!=null && lstPatientBroughtByDtlVO.size()>0)
			strBroughtByJsonObj=HelperMethods.createJSONObjectString(lstPatientBroughtByDtlVO.get(nVisitNoIndx));
		
		WebUTIL.writeResponse(objResponse_p, strBroughtByJsonObj, "text/html");
	}
	
	public static String saveMlcDetails(EmgMlcDtlSUP objEmgMlcDtlSUP_p, HttpServletRequest objRrequest_p)
	{
		Status objStatus = new Status();
		UserVO objUserVO=getUserVO(objRrequest_p);
		int nVisitNoIndx= Integer.parseInt(objEmgMlcDtlSUP_p.getnVisitNoIndex());
		EpisodeVO objEpisodeVO = null;
		EpisodeVO[] objArrEpisodeVO=null;
		List<MlcParameterDtlVO> lstMlcParameterDtlVO= null;
		String flagSaveSccessfull="0";
		boolean isTodayOfflineVisit=false;
		try
		{
			HttpSession session = WebUTIL.getSession(objRrequest_p);
			isTodayOfflineVisit= "1".equals(objEmgMlcDtlSUP_p.getIsTodayOfflineVisit())?true:false;
			
			objArrEpisodeVO = (EpisodeVO[])objRrequest_p.getSession().getAttribute(RegistrationConfig.EMGREGISTRATIONDESK_MLC_EPISODEVO_ARR);
			objEpisodeVO = objArrEpisodeVO[nVisitNoIndx];

			objEmgMlcDtlSUP_p.setEpisodeCode(objEpisodeVO.getEpisodeCode());
			objEmgMlcDtlSUP_p.setEpisodeVisitNo(objEpisodeVO.getEpisodeVisitNo());
			
			// Creating MLC VO			
			MlcVO objMlcVO = new MlcVO();
				
			HelperMethods.populate(objMlcVO, objEmgMlcDtlSUP_p);	
			objMlcVO.setMlcTime(objEmgMlcDtlSUP_p.getMlcTimeHr()+":"+objEmgMlcDtlSUP_p.getMlcTimeMin());
			
			PatientVO objPatientVO = (PatientVO) session.getAttribute(RegistrationConfig.PATIENT_VO);			
			HelperMethods.populatetToNullOrEmpty(objMlcVO, objPatientVO);
			
			
			objMlcVO.setPatMlcNo(objEmgMlcDtlSUP_p.getPatMlcNo());
			objMlcVO.setEpisodeCode(objEmgMlcDtlSUP_p.getEpisodeCode());
			objMlcVO.setEpisodeVisitNo(objEmgMlcDtlSUP_p.getEpisodeVisitNo());
			objMlcVO.setSystemIPAddress(objRrequest_p.getRemoteAddr());

			objMlcVO.setCmoCode(objUserVO.getUserEmpID());
			objMlcVO.setIsBroughtDead(objEmgMlcDtlSUP_p.getIsBroughtDead());
			
			
			
			if(objEmgMlcDtlSUP_p.getIsBroughtByPolice()==null)
				objMlcVO.setMlcType("0");
			else
				objMlcVO.setMlcType("1");
			
			// Setting MLC Parameter Detail VO
			if(objEmgMlcDtlSUP_p.getMlcTypeCode()!=null && objEmgMlcDtlSUP_p.getMlcTypeCode().length>0){
				lstMlcParameterDtlVO= new ArrayList<MlcParameterDtlVO>();
				for(int i=0; i<objEmgMlcDtlSUP_p.getMlcTypeCode().length; i++){
					MlcParameterDtlVO voMlcParamDtl = new MlcParameterDtlVO();
					populateMlcParameterDtlVO(objEmgMlcDtlSUP_p, voMlcParamDtl, i);
					voMlcParamDtl.setIsValid("1");
					lstMlcParameterDtlVO.add(voMlcParamDtl);
				}
			}
			// Creating Patient Brought By Detail VO
			PatientBroughtByDtlVO objPatBroughtByVO=new PatientBroughtByDtlVO();
			if("1".equals(objEmgMlcDtlSUP_p.getIsBroughtBy()))
				HelperMethods.populate(objPatBroughtByVO, objEmgMlcDtlSUP_p);
			
			
			PoliceVerificationDtlVO objPoliceVerDtlVO=new PoliceVerificationDtlVO();
			HelperMethods.populate(objPoliceVerDtlVO,objEmgMlcDtlSUP_p);
			/*if(objEmgMlcDtlSUP_p.getIsPoliceVerReq().equals(RegistrationConfig.MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_YES))
			{
				HelperMethods.populate(objPoliceVerDtlVO,objEmgMlcDtlSUP_p);
				if(objEmgMlcDtlSUP_p.getDutyOfficeFlag().equals(RegistrationConfig.DUTY_OFFICER_IS_IO))
				{
					objPoliceVerDtlVO.setDutyOffName(objEmgMlcDtlSUP_p.getOfficerIncharge());
					objPoliceVerDtlVO.setDutyOffDesignation(objEmgMlcDtlSUP_p.getIoDesignation());
					objPoliceVerDtlVO.setDutyOffBatchNo(objEmgMlcDtlSUP_p.getIoBatchNo());
				}
				
			}	
			else
				objPoliceVerDtlVO=null;*/
			if(objEmgMlcDtlSUP_p.getPrintFlag()!=null && objEmgMlcDtlSUP_p.getPrintFlag()!="")
			{
				objEmgMlcDtlSUP_p.setPrintFlag("1");
			}
			else
				objEmgMlcDtlSUP_p.setPrintFlag("0");
			objMlcVO=EmgMlcDtlDATA.saveMlcDetails(objMlcVO,lstMlcParameterDtlVO, objUserVO, objPatBroughtByVO,objPoliceVerDtlVO, EmgMlcDtlSUP.flagMlcOnlineOffline,isTodayOfflineVisit);	
			objEmgMlcDtlSUP_p.setIsDuplicateMlcNo(RegistrationConfig.MLC_NO_IS_DUPLICATE_NO);
			objEmgMlcDtlSUP_p.setStrNormalMsg("MLC Details Recorded Succesfully of CR No :"+objEmgMlcDtlSUP_p.getPatCrNo());
			flagSaveSccessfull= "1";
			objRrequest_p.getSession().removeAttribute("keyPatientVO");

		}

		catch (HisDuplicateRecordException e) 
		{
			objEmgMlcDtlSUP_p.setIsDuplicateMlcNo(RegistrationConfig.MLC_NO_IS_DUPLICATE_YES);
			flagSaveSccessfull= "2";
			//objEmgMlcDtlSUP_p.setMlcNo("");
			objEmgMlcDtlSUP_p.setStrWarningMsg(e.getMessage());
			e.printStackTrace();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			objEmgMlcDtlSUP_p.setErrorMessage("");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objEmgMlcDtlSUP_p.setErrorMessage("Error, Contact System Administrator");
			e.printStackTrace();
		}
		finally
		{
			return flagSaveSccessfull;
		}
	}
	
	public static void populateMlcParameterDtlVO(EmgMlcDtlSUP objEmgMlcDtlSUP_p, MlcParameterDtlVO objMlcParamDtlVO_p, int index)
	{
		objMlcParamDtlVO_p.setPatCrNo(objEmgMlcDtlSUP_p.getPatCrNo());
		objMlcParamDtlVO_p.setMlcNo(objEmgMlcDtlSUP_p.getPatMlcNo());
		objMlcParamDtlVO_p.setEpisodeCode(objEmgMlcDtlSUP_p.getEpisodeCode());
		objMlcParamDtlVO_p.setEpisodeVisitNo(objEmgMlcDtlSUP_p.getEpisodeVisitNo());
		//objMlcParamDtlVO_p.setSerialNo(objEmgMlcDtlSUP_p.);
		
		objMlcParamDtlVO_p.setMlcTypeCode(objEmgMlcDtlSUP_p.getMlcTypeCode()[index]);
		objMlcParamDtlVO_p.setInjurySide(objEmgMlcDtlSUP_p.getInjurySide()[index]);
		objMlcParamDtlVO_p.setInjuryNatureCode(objEmgMlcDtlSUP_p.getInjuryNatureCode()[index]);
		objMlcParamDtlVO_p.setTypeOfWeapon(objEmgMlcDtlSUP_p.getTypeOfWeapon()[index]);
		objMlcParamDtlVO_p.setInjurySize(objEmgMlcDtlSUP_p.getInjurySize()[index]);
		objMlcParamDtlVO_p.setInjuryDepth(objEmgMlcDtlSUP_p.getInjuryDepth()[index]);
		objMlcParamDtlVO_p.setBurnPercentage(objEmgMlcDtlSUP_p.getBurnPercentage()[index]);
		objMlcParamDtlVO_p.setPoisonRemarks(objEmgMlcDtlSUP_p.getPoisonRemarks()[index]);
	}
	
	/*Start : Surabhi
	 * Reason : to get the mlc details of the patients for the certificate
	 * date : 7th oct 2016 */
	public static void getMlcDetailByCrNo(EmgMlcDtlSUP objEmgMlcDtlSUP_p,HttpServletRequest objRequest_p)
	{
		Status objStatus=new Status();
		HttpSession session=objRequest_p.getSession();
		MlcVO objMlcVO_p=new MlcVO();
		MlcVO objMlcVO=new MlcVO();
		
		try
		{
			
			objMlcVO_p.setEpisodeCode(objEmgMlcDtlSUP_p.getEpisodeCode());
			objMlcVO_p.setEpisodeVisitNo(objEmgMlcDtlSUP_p.getEpisodeVisitNo());
			objMlcVO_p.setPatMlcNo(objEmgMlcDtlSUP_p.getPatMlcNo());
			objMlcVO=EmgMlcDtlDATA.getMlcDetailByCrNo(objMlcVO_p,objEmgMlcDtlSUP_p.getPatCrNo(),getUserVO(objRequest_p));
			HospitalMstVO objHospitalMstVO=ControllerUTIL.getHospitalVO(objRequest_p);
			
			HelperMethods.populatetToNullOrEmpty(objEmgMlcDtlSUP_p, objMlcVO);
			objEmgMlcDtlSUP_p.setAddress1(objHospitalMstVO.getAddress1());
			objEmgMlcDtlSUP_p.setPhone(objHospitalMstVO.getPhone());
			objEmgMlcDtlSUP_p.setAddress2(objHospitalMstVO.getAddress2());
			objEmgMlcDtlSUP_p.setCity(objHospitalMstVO.getCity());
			objEmgMlcDtlSUP_p.setPinCode(objHospitalMstVO.getPinCode());
			objEmgMlcDtlSUP_p.setDistrictName(objHospitalMstVO.getDistrictName());
			objEmgMlcDtlSUP_p.setStateName(objHospitalMstVO.getStateName());
			objEmgMlcDtlSUP_p.setHospitalName(objHospitalMstVO.getHospitalName());
			
			
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objEmgMlcDtlSUP_p.setAfterGo("0");
			objEmgMlcDtlSUP_p.addActionError("Data Access Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objEmgMlcDtlSUP_p.setAfterGo("0");
			objEmgMlcDtlSUP_p.addActionError("Application Execution Error");
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
	}
	// end
}
