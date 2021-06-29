package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;


import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.HisPrinterSupport;
import hisglobal.utility.HisUtil;
import vo.registration.BeneficiaryPatientVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import hisglobal.vo.HospitalMstVO;
import vo.registration.PatientVO;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;

import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.config.RegistrationConfigurationBean;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.data.EmgPatientVisitDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import registration.transactions.controller.data.SpclPatientVisitDATA;
import registration.transactions.controller.data.SplRegistrationDATA;
import registration.transactions.controller.data.UnitWisePatientVisitDATA;
import registration.transactions.controller.fb.SplPatientVisitFB;
import registration.transactions.controller.fb.newSplRegFB;
import registration.config.Exceptions.HisAppointmentNotAvailableException;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisEpisodeOpenInEmergencyException;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisRenewalRequiredException;
import registration.config.slip.RegistrationSlip;


public class SplPatientVisitWithAppUTIL extends ControllerUTIL {	
	
	
	public static void getMapOfRenewalConfigDtlOnKeyPatCat(SplPatientVisitFB fb,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCatGroup=new HashMap();
		RegistrationConfigVO registrationConfigVO=new RegistrationConfigVO();
		RegistrationConfigurationBean _objRegConfigVO2 = new RegistrationConfigurationBean();
		
		try
		{
			System.out.println("SplPatientVisitWithAppUTIL :: getMapOfRenewalConfigDtlOnKeyPatCat()");
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			mapOfRenewalVoOnKeyPatCatGroup=SpclPatientVisitDATA.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO);
			registrationConfigVO=SpclPatientVisitDATA.getRegistrationConfigDtl(objUserVO);
			_objRegConfigVO2=EmgPatientVisitDATA.getRegistrationConfigDtl2(objUserVO) ; //objEssentialDAO.getRegistrationConfigDtl2(objUserVO,"1","1");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.Registration_Configuration_Bean,_objRegConfigVO2);
			//objPatVisitSUP_p.setStrRenewalType(objRenewalConfigVO.getStrRenewalType());
			//System.out.println("StrRenewalType :"+objPatVisitSUP_p.getStrRenewalType());
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,registrationConfigVO);

		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,registrationConfigVO);
			//objStatus.add(Status.ERROR,"",e.getMessage());
			fb.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			fb.setErrorMessage("Record Not Found");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
		}
		catch(HisException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}
	
	public static void setPatientDtlByCrno( SplPatientVisitFB _fb,HttpServletRequest request){
		Status status=new Status();
		UserVO userVO =getUserVO(request);
		PatientVO patVO=new PatientVO();
		patVO.setPatCrNo(_fb.getPatCrNo());
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL;
		try{
			
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			patVO=SpclPatientVisitDATA.getPatientDtl(patVO,userVO, visitType);
			/***/
			setQrCode(_fb, patVO, userVO, request);
			
			WebUTIL.setAttributeInSession(request,RegistrationConfig.PATIENT_VO,patVO);
			
			//delete below line
			System.out.println("PatPrimaryCatGrpCode :"+patVO.getPatPrimaryCatGrpCode());
			
			
			//To Fetch the Credit Beneficiary Details regarding the Category Group
			_fb.setPatPrimaryCatGrpCode(patVO.getPatPrimaryCatGrpCode());			
					
			if(patVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD))
			{
				
				System.out.println("Patient Is Dead");
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}
			_fb.setSplRenewalRequired(patVO.getSplRenewalRequired());
			
			if(patVO.getIsCatExpired()!=null && patVO.getIsCatExpired().equals("1"))
				throw new HisException("This patient's Category is Expired. Kindly Select Another Record or Renew its first.");
			
			if(request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO)==null)
				SplPatientVisitWithAppUTIL.getMapOfRenewalConfigDtlOnKeyPatCat(_fb,request);
			
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			RenewalConfigVO objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(patVO.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");

			_fb.setStrRenewalType(objRenewalConfigVO.getStrRenewalType());
			objRenewalConfigVO.setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_OPD);
			objRenewalConfigVO.setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_SPEACIAL);
			patVO.setRenewalConfig(objRenewalConfigVO);
			
			/*code added by Garima for Renewal*/
			
			HttpSession ses=request.getSession();
			
			String tempDeptUnitCode=_fb.getDepartmentCode();
			if(_fb.getDepartmentCode().indexOf("#")>0){
				String tempCode[]=_fb.getDepartmentCode().split("#");
				_fb.setDepartmentCode(tempCode[0]);
				_fb.setDepartmentUnitCode(tempCode[1]);
			}
			
			//call the method to get the Episode Referal Details
			setReferEpisodeDtl(_fb, request);
									
			if((_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)))
			{
				setOldDepartmentVisitDtl(_fb,request);
				if(request.getSession().getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR)!=null)
					_fb.setModeCase("0");
				else
					_fb.setModeCase("1");
			}
			
			if(_fb.getModeCase().equals("0"))
			{
		 		
		 		_fb.setOldDepartmentVisit("On");
		 		setOldDepartmentVisitDtl(_fb,request);
			}
			else if(_fb.getModeCase().equals("1"))
			{
				
				_fb.setNewDepartmentVisit("On");
				setNewDepartmentVisitDtl(_fb, request);
			}
			else if(_fb.getModeCase().equals("2"))
			{
				//Modified to Show the Data for the referral patient Selection too by Singaravelan on 15-10-13
				if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					//System.out.println("---------"+_fb.getSelectedReferal()+"----------");
					if(_fb.getSelectedReferal().equalsIgnoreCase("O"))
					{
						_fb.setOldDepartmentVisit("On");
						setOldDepartmentVisitDtl(_fb,request);
					}
					else
					{
						_fb.setNewDepartmentVisit("On");
						setNewDepartmentVisitDtl(_fb, request);
					}

				}
				else
				{
					_fb.setOldDepartmentVisit("On");
					_fb.setNewDepartmentVisit("On");
					setOldDepartmentVisitDtl(_fb,request);
					setNewDepartmentVisitDtl(_fb, request);
				}
						
			}
			
			// Setting Patient Amount
			 setPatAmount(_fb, patVO, userVO, request);
			_fb.setDepartmentCode(tempDeptUnitCode);
			_fb.setDepartment(patVO.getDepartment());
			
			RegistrationConfigVO registrationConfigVO=(RegistrationConfigVO)request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			EpisodeRefDtlVO[] episodeReferalDtlVO=(EpisodeRefDtlVO[])request.getSession().getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
		
			//if(registrationConfigVO.getIsCrossConsulation().equals("1")&&episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0){
			if((registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_Special_Only)||registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_OPD_and_Special_Both))&&episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0){
				_fb.setIsReferred("on");
				_fb.setIsRefferInOut("I");
				_fb.setReferInternalExternal("I");
				_fb.setPatIsCrossConsultantWithReferal("1");
				_fb.setReferingRowIndex("0");
			}
			
			if(_fb.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) ||
					_fb.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
				getCreditBeneficiaryDtlForPatCatOnCRNO(_fb,request);
			}
			
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		if( (patVO!=null) && (patVO.getPatPrimaryCatCode()==null ||patVO.getPatPrimaryCatCode().equals("")))
	   		{
	   			WebUTIL.setAttributeInSession(request,RegistrationConfig.PATIENT_VO,patVO);
	   		}
	   		status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
		}
	   	catch(HisNotAnOPDcaseException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(HisException e){
			_fb.setErrorMessage(e.getMessage());
			status.add(Status.ERROR_AE,e.getMessage() ,"");
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	
	
	
	/**
	 * sets Patient Details By Name
	 * @param _fb SplPatientVisitFB form bean
	 * @param request HttpServletRequest
	 */
	
	
	/*public static void setPatientDtlByName( SplPatientVisitFB _fb,HttpServletRequest request){
		UserVO userVO =getUserVO(request);
		PatientVO[] patVO=new PatientVO[]{};
		String searchName=_fb.getSearchName();
		
		patVO=SpclPatientVisitDATA.getPatientDtlByName(searchName,userVO);
		
		HttpSession ses=request.getSession();
		ses.setAttribute(RegistrationConfig.PATIENT_VO,patVO);
	}*/
	/**
	 * sets all initial Old Patient Visit Essentials
	 * calls getAllInitialOldDeptVisitEssentials of  SplPatientVisitDATA
	 * 
	 * @param _rq HttpServletRequest
	 */
	public static void setAllInitialOldSplPatientVisitEssentials(HttpServletRequest _rq){
		Status status = new Status();
		try{
			//setSysdate(_rq);
			Map mp=SpclPatientVisitDATA.getAllInitialOldDeptVisitEssentials();
			WebUTIL.setMapInSession(mp, _rq,"SplPatientVisitwithAptmntACTION");
		//	status.add(Status.NEW);
		}catch(HisRecordNotFoundException e){
			status.add(Status.UNSUCESSFULL,"Record Not found" ,"");
		}catch(HisDataAccessException e){
			status.add(Status.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			status.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}finally{
			WebUTIL.setStatus(_rq, status);
		}
	}
	/**
	 * sets the intial essentials 
	 * for the NEW mode
	 * calls getAllInitialNewDeptVisitEssentials() of newDeptVisitDATA
	 * puts the intial Essentails obtained in session
	 * @param _rq -HttpServletRequest
	 */
		public static void setAllInitialNewDeptVisitEssentials(HttpServletRequest _rq,SplPatientVisitFB _fb){
			Status  objStatus=new Status();
			// objStatus.add(Status.NEW, "", "");
			try{
			Map mp=SpclPatientVisitDATA.getAllInitialNewDeptVisitEssentials();
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(_rq));
			WebUTIL.setMapInSession(mp, _rq,"SplPatientVisitwithAptmntACTION");
			}
			catch(HisRegistrationTimingExpiredException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				WebUTIL.removeFromStatus(_rq,Status.NEW);
				e.printStackTrace();
			}
			catch(HisRecordNotFoundException e){
				objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");	
			}		
			catch(HisDataAccessException e){
				objStatus.add(Status.ERROR_DA,"","Data Access Error");		
			}
			catch(HisApplicationExecutionException e){		
				objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			}
			catch(HisException e){
				objStatus.add(Status.ERROR,"","Error");
			}		
			finally
			{
				WebUTIL.setStatus(_rq, objStatus);
			}	
			
		}
	/**
	 * sets old department visit essentials (SplPatientVisitFB _fb, HttpServletRequest _rq)
	 * getOldDeptVisitEssentials(_fb, getUserVO(_rq)) from SplPatientVisitDATA
	 * @param _fb SplPatientVisitFB form bean
	 * @param _rq HttpServletRequest
	 */
	public static void setOldDeptVisitEssentials(SplPatientVisitFB _fb, HttpServletRequest _rq){
		
		Status status = new Status();
		try{
			Map mp=SpclPatientVisitDATA.getOldDeptVisitEssentials(_fb, getUserVO(_rq));
			WebUTIL.setMapInSession(mp, _rq,"SplPatientVisitwithAptmntACTION");
			//status.add(Status.INPROCESS);
		}catch(HisRecordNotFoundException e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Record Not found" ,"");
		}catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace(); 
			status.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}finally{
			WebUTIL.setStatus(_rq, status);
		}
	}
	/**
	 * sets Episode Details( SplPatientVisitFB _fb,HttpServletRequest _rq )
	 * calls setPatientStatus( _rq,patVO) of OldSplPatientVisitUTIL
	 * @param _fb SplPatientVisitFB form bean
	 * @param _rq HttpServletRequest
	 */
	public static void setEpisodeDetails( SplPatientVisitFB _fb,HttpServletRequest _rq ){		
	Status status = new Status();
		try{
			UserVO userVO =getUserVO(_rq);		
			HttpSession ses=WebUTIL.getSession(_rq);
			PatientVO patVO=(PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			patVO.setPatCrNo(_fb.getPatCrNo());
		    EpisodeVO[] arrEpisodeVO ;
	//	    String[] arrDeptRenew;
		
			if(patVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD)==false){
				//arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodes(_fb.getPatCrNo(), userVO, RegistrationConfig.EPISODE_VISIT_TYPE_OPD,patVO.getRegRenewalRequired(),patVO.getPatPrimaryCatCode(),RegistrationConfig.RENEWAL_TARIFF_ID,RegistrationConfig.REGISTRATION_SERVICE_ID,RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
				arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodes(_fb.getPatCrNo(), patVO.getPatPrimaryCatCode(), RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL, patVO.getRenewalConfig().getStrRenewalType(), userVO);
				WebUTIL.setAttributeInSession(_rq,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
			}
			
			
		}catch(HisRecordNotFoundException e){
	   		e.printStackTrace();
	   		status.add(Status.ERROR_DA,e.getMessage() ,"");
	   		System.out.println(e.getMessage());
	   		WebUTIL.setAttributeInSession(_rq,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, null);
	   		throw new HisRecordNotFoundException(e.getMessage());
		}
	   	catch(HisApplicationExecutionException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_DA,e.getMessage() ,"");
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HisException();
		}				
	}	
	

	
	
	public static RegistrationSlip preapareSlip(EpisodeVO episodeVO[], PatientVO _patVO, SplPatientVisitFB _fb, HttpServletRequest _request){		
		
		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(_patVO);
		HelperMethods.populate(regSlip,_patVO);
		regSlip.setPatAge(_patVO.getPatAge()+" "+_patVO.getPatAgeUnit());
		
		//HospitalMstVO hospitalVO=(HospitalMstVO)_request.getSession().getAttribute(Config.HOSPITAL_VO);
		HospitalMstVO hospitalVO=getHospitalVO(_request);
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		// String primaryCatName=getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY),_fb.getPatPrimaryCatCode());
		String primaryCatName=_patVO.getPatPrimaryCat();
		regSlip.setPatPrimaryCat(primaryCatName);
		//regSlip.setEpisodeDate(_patVO.getRegisterDate());
		/*if(regSlip.getEpisodeDate()==null || regSlip.getEpisodeDate().equals(""));
			regSlip.setEpisodeDate((String)_request.getSession().getAttribute(Config.SYSDATE));*/
		
		int episodeVOLength=episodeVO.length;
		String fileNo[] =new String[episodeVOLength];
		String fileNoView[] =new String[episodeVOLength];
		String roomName[] =new String[episodeVOLength];
		String depName[]=new String[episodeVOLength];
		String depUnit[]=new String[episodeVOLength];
		String queueNo[]=new String[episodeVOLength];
		String workingDays[]=new String[episodeVOLength];
		String timings[]=new String[episodeVOLength];
		String disclaimer1[]=new String[episodeVOLength];
		String disclaimer2[]=new String[episodeVOLength];
		String disclaimer3[]=new String[episodeVOLength];
		String isHeader[]=new String[episodeVOLength];
		String alignment[]=new String[episodeVOLength];
		String episodeDate[]=new String[episodeVOLength];
		String unitConsultant[]=new String[episodeVOLength];
		String filePrintFlag[]=new String[episodeVO.length];
		String cardPrintFlag[]=new String[episodeVO.length];
		String departmentCode[]=new String[episodeVO.length];
		String referFromDepartment[]=new String[episodeVO.length];
		String printType[]=new String[episodeVO.length];
		String expDate[]=new String[episodeVO.length];

		String patMiddleName=regSlip.getPatMiddleName();

		if(!(patMiddleName==null || patMiddleName.equals(""))){
					patMiddleName=patMiddleName.substring(0,1).toUpperCase();
					regSlip.setPatMiddleName(patMiddleName);
					}
		
		String cityLocation=_patVO.getPatAddCity();
		
		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		
		
		//address for card
		String district="";
		String address=(_patVO.getPatAddHNo()==null?" ":_patVO.getPatAddHNo());
		address=address+(_patVO.getPatAddStreet()==null?" ":_patVO.getPatAddStreet());
		
		address=address+" "+cityLocation;
		//address=address+" " +(_patVO.getPatAddCity()==null?" ":_patVO.getPatAddCity());
		String address2="";	
		if(_patVO.getPatAddDistrict()!=null && !_patVO.getPatAddDistrict().equals("-1"))
			address2=_patVO.getPatAddDistrict();
		else
			address2=district;
		
		if(!address2.equals(""))
			address2=address2+" "+_patVO.getPatAddState()==null?" ":" "+_patVO.getPatAddState();
		else
			address2=_patVO.getPatAddState()==null?" ":" "+_patVO.getPatAddState();

		
		//address2=address2+" "+_patVO.getPatAddState();
		address2=address2+" "+_patVO.getPatAddCountry();
		if(!_patVO.getPatAddPIN().equals("0"))
		address2=address2+" "+(_patVO.getPatAddPIN()==null?" ":_patVO.getPatAddPIN());
		
		for(int i=0;i<episodeVOLength;i++){
			if(episodeVO[i].getFileNo()==null)
			{
				episodeVO[i].setFileNo("");
				episodeVO[i].setFileNoView("");
			}
			fileNo[i]=episodeVO[i].getFileNo();
			fileNoView[i]=episodeVO[i].getFileNoView();
			roomName[i]=episodeVO[i].getRoom();
			depName[i]=episodeVO[i].getDepartment();			
			depUnit[i]=episodeVO[i].getDepartmentUnit();
			queueNo[i]=episodeVO[i].getQueNo();
			unitConsultant[i]=episodeVO[i].getUnitConsultant();
			filePrintFlag[i]=episodeVO[i].getFilePrintSetting();
			cardPrintFlag[i]=episodeVO[i].getCardPrintSetting();
			departmentCode[i]=episodeVO[i].getDepartmentCode();
			referFromDepartment[i]=episodeVO[i].getReferFromDepartment();
			expDate[i]=episodeVO[i].getExpiryDate();
			if(episodeVO[i].getPatAmountCollected()!=null && !(episodeVO[i].getPatAmountCollected().equals(""))
					&& !(episodeVO[i].getPatAmountCollected().equals("0")))
			{
				printType[i]="R";//renewal
			}else
			{
				printType[i]="C";//continuation
			}
			
			/*String[] temp=episodeVO[i].getUnitWorkingDays().split("#");
			if(temp.length==0)
				temp=new String[]{"",""};
			workingDays[i]=temp[0];
			timings[i]=temp[i];*/
			workingDays[i]=episodeVO[i].getUnitWorkingDays();
			
				if(episodeVO[i].getDisclaimer()!="" && episodeVO[i].getDisclaimer()!=null){
					disclaimer1[i]=episodeVO[i].getDisclaimer().split("@")[0];
					disclaimer2[i]=episodeVO[i].getDisclaimer().split("@")[1];
					disclaimer3[i]=episodeVO[i].getDisclaimer().split("@")[2];
					isHeader[i]=episodeVO[i].getDisclaimer().split("@")[3];
					alignment[i]=episodeVO[i].getDisclaimer().split("@")[4];
				}
				else{
					disclaimer1[i]="";
					disclaimer2[i]="";
					disclaimer3[i]="";
					isHeader[i]="";
					alignment[i]="";
				}
			
			episodeDate[i]=(String)_request.getSession().getAttribute(Config.SYSDATE);
			regSlip.setEpisodeVisitType(episodeVO[i].getEpisodeVisitType());
		}
		regSlip.setEpisodeDate(episodeDate);
		regSlip.setHostName(_request.getRemoteAddr());
		regSlip.setPatCrNo(episodeVO[0].getPatCrNo());
		regSlip.setRoom(roomName);
		regSlip.setDepartmentToBeVisited(depName);		
		regSlip.setFileNo(fileNo);
		regSlip.setFileNoView(fileNoView);
		regSlip.setDepartmentUnit(depUnit);		
		regSlip.setQueNo(queueNo);
		regSlip.setWorkingDays(workingDays);
		regSlip.setTiming(timings);
		regSlip.setDisclaimer1(disclaimer1);
		regSlip.setDisclaimer2(disclaimer2);
		regSlip.setDisclaimer3(disclaimer3);
		regSlip.setIsHeader(isHeader);
		regSlip.setAlignment(alignment);
		regSlip.setAddress1(address);
		regSlip.setAddress2(address2);
		regSlip.setPatMonthlyIncome(_patVO.getPatMonthlyIncome()==null?" ":_patVO.getPatMonthlyIncome());
		regSlip.setUnitConsultant(unitConsultant);
		regSlip.setFilePrintFlag(filePrintFlag);
		regSlip.setCardPrintFlag(cardPrintFlag);
		regSlip.setDeptCode(departmentCode);
		regSlip.setReferFromDepartment(referFromDepartment);
		regSlip.setPrintType(printType);
		regSlip.setExpDate(expDate);
		regSlip.setPatAmountCollected(episodeVO[0].getPatAmountCollected());
		if(regSlip.getPatAmountCollected()==null) 
			regSlip.setPatAmountCollected("0");
		else if(regSlip.getPatAmountCollected().equals(""))
				regSlip.setPatAmountCollected("0");
		
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		regSlip.setHospitalAddress1(hospitalVO.getAddress1());
		regSlip.setHospitalAddress2(hospitalVO.getAddress2());
		regSlip.setHospitalCity(hospitalVO.getCity());
		regSlip.setHospitalState(hospitalVO.getState());
		regSlip.setHospitalpinCode(hospitalVO.getPinCode());
		regSlip.setHospitalPhone(hospitalVO.getPhone());
		regSlip.setHospitalFax(hospitalVO.getFax());
		regSlip.setHospitalEmail(hospitalVO.getEmail());
		
		return regSlip;	
	}	
	
	
/**
 * sets Patient Status
 * calls  getPatientStatus(patVO, userVO) of SplPatientVisitDATA
 * @param _rq HttpServletRequest
 * @param patVO- Provides Patient details.
 * @return returns patient Status as String
 */
	/*public static String setPatientStatus(HttpServletRequest _rq,PatientVO patVO){
		Status  objStatus=new Status();
		UserVO userVO =getUserVO(_rq);
		
		String patStatus="";
		try{
	    patStatus=SpclPatientVisitDATA.getPatientStatus(patVO, userVO);
	    
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,"e.getMessage","Records Not Found");	
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}		
		finally
		{
			_rq.setAttribute(Config.STATUS_OBJECT,objStatus);
		 
		}	
		WebUTIL.setAttributeInSession(_rq,RegistrationConfig.PATIENT_VO,patVO);
		return patStatus;
	}
	*/
	
	
	
	
	public static void getOpenEpisodeDtl(HttpServletRequest _request, SplPatientVisitFB _fb){
		PatientVO patientVO=(PatientVO)WebUTIL.getSession(_request).getAttribute(RegistrationConfig.PATIENT_VO);
		UserVO userVO=getUserVO(_request);
		Status status=new Status();
		try{
			if(_fb.getIsReferred().equals("1")){
			//EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(patientVO.getPatCrNo(), userVO);
			EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(patientVO.getPatCrNo(), userVO,"3");

			for(int i=0;i<arrOPDOpenEpisodeVO.length;i++)
			{
				if(arrOPDOpenEpisodeVO[i].getFromDepartment()==null)
					arrOPDOpenEpisodeVO[i].setFromDepartment("");
				if(arrOPDOpenEpisodeVO[i].getFromDepartmentUnit()==null)
					arrOPDOpenEpisodeVO[i].setFromDepartmentUnit("");
				if(arrOPDOpenEpisodeVO[i].getFromDepartmentUnitCode()==null)
					arrOPDOpenEpisodeVO[i].setFromDepartmentUnitCode("");
			}
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
			_fb.setIsReferred("1");
			_fb.setIsRefferInOut("I");
			}
			else{
				_fb.setIsReferred("0"); 
			}
			status.add(Status.TRANSINPROCESS);
			}
		catch(HisRecordNotFoundException e){
   		
	   		e.printStackTrace();
	   		status.add(Status.TRANSINPROCESS);
		}
		finally{
			WebUTIL.setStatus(_request,status);
		}
}
	
	/*public static boolean renewalOfRegistration(SplPatientVisitFB _fb,HttpServletRequest _rq){
		boolean renewalStatus=false;
		PatientVO patientVO=(PatientVO) WebUTIL.getSession(_rq).getAttribute(RegistrationConfig.PATIENT_VO);   
		Status objStatus =new Status();	
		UserVO userVO =getUserVO(_rq);
		EpisodeVO[] selectedEpisodeVO=null;
		EpisodeVO[] arrayEpisodeForStamping=null;
		setSysdate(_rq);
		String sysDate=(String)WebUTIL.getSession(_rq).getAttribute(Config.SYSDATE);
		String amount=(String)WebUTIL.getSession(_rq).getAttribute("amount");
	//	String isVisitOnRequest=_fb.getOnRequestVisit();
		try{
			if(Config.RENEWAL_TYPE.equals("3") || Config.RENEWAL_TYPE.equals("4") || Config.RENEWAL_TYPE.equals("5"))	
			{	
				EpisodeVO[] arrRenewalEpisodeVO=(EpisodeVO[])WebUTIL.getSession(_rq).getAttribute(RegistrationConfig.RENEWAL_REQUIRED_EPISODE_ARRAY);
				selectedEpisodeVO= new EpisodeVO[_fb.getDepartmentsToVisitStamp().length];
				//selectedEpisodeVO= new EpisodeVO[_fb.getDepartmentsToRenew().length];
				for(int i=0;i<_fb.getDepartmentsToVisitStamp().length;i++)
				{
					if(arrRenewalEpisodeVO!=null)
					{
					for(int j=0;j<arrRenewalEpisodeVO.length;j++)
					{
						if(_fb.getDepartmentsToVisitStamp()[i].equals(arrRenewalEpisodeVO[j].getEpisodeCode()))
								
								{
								selectedEpisodeVO[i]=new EpisodeVO();
								HelperMethods.populate(selectedEpisodeVO[i],arrRenewalEpisodeVO[j]);
								amount=(String) WebUTIL.getSession(_rq).getAttribute("amount");
								selectedEpisodeVO[i].setEpisodeCode(_fb.getDepartmentsToRenew()[i]);
								selectedEpisodeVO[i].setPatAmountCollected(amount);
								selectedEpisodeVO[i].setSystemIPAddress(patientVO.getSystemIPAddress());
								}
					}
					}
				}
				////////new method for combined renewal and stamping//////
				EpisodeVO[] arrEpisodeVO=(EpisodeVO[])WebUTIL.getSession(_rq).getAttribute( RegistrationConfig.ARRAY_EPISODES_TO_BE_STAMPED);
				EpisodeRefDtlVO episodeRefVO=(EpisodeRefDtlVO)WebUTIL.getSession(_rq).getAttribute( RegistrationConfig.ARRAY_EPISODE_REFER_VO_RENEWAL);
				
				
				arrayEpisodeForStamping=SpclPatientVisitDATA.saveDeptWiseRenewalAndStaming(patientVO,selectedEpisodeVO,arrRenewalEpisodeVO,arrEpisodeVO,episodeRefVO,sysDate,userVO);
				
				//////////////////////
				renewalStatus=true;
				_fb.setSaveSuccessful("true");
			}
			
			
			///////////////Setting status afetr renewal and stamping///////////////
			if(Config.RENEWAL_TYPE.equals("3") || Config.RENEWAL_TYPE.equals("4") || Config.RENEWAL_TYPE.equals("5"))
			{
			if(renewalStatus==true)
				{
				StringBuilder str=new StringBuilder();
				String patPriCatLable=patientVO.getPatPrimaryCat();
				 
				int arrEpiLen=arrayEpisodeForStamping.length;
				 String[] strQueueNo=new String[arrEpiLen];
				 String[] strDeptName=new String[arrEpiLen];
				 String[] strUnitName=new String[arrEpiLen];
				 String[] strRoomName=new String[arrEpiLen];
				 
				 for(int i=0;i<arrEpiLen;i++){
					 strQueueNo[i]=arrayEpisodeForStamping[i].getQueNo();
					 strDeptName[i]=arrayEpisodeForStamping[i].getDepartment();
					 strUnitName[i]=arrayEpisodeForStamping[i].getDepartmentUnit();
					 strRoomName[i]=arrayEpisodeForStamping[i].getRoom();
					
					 	    
				 }
				 /////////for displaying the name ////////////
				 if(patientVO.getPatMiddleName()==null)
					 patientVO.setPatMiddleName("");
				 if(patientVO.getPatLastName()==null)
					 patientVO.setPatLastName("");
				 ///////////////////////////////////////////
				 str.append("<br><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>  Name &nbsp;&nbsp;    :: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(patientVO.getPatFirstName());str.append(" ");str.append(patientVO.getPatMiddleName());str.append(" ");str.append(patientVO.getPatLastName());str.append("</font><br>");
				 str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>  CR NO &nbsp;:: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(arrayEpisodeForStamping[0].getPatCrNo());str.append("</font>");
				 str.append("<table width='50%'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>   Dept Name:: </font></td>");
				 str.append("<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>   Unit No::</font></td>");
				 str.append("<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>   Room No::</font></td>");
				 str.append("<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>   Queue No::</font><br></td></tr></table>");
				 
				 for(int i=0;i<arrEpiLen;i++){
					 str.append("<table width='50%'><tr><td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strDeptName[i]);str.append("</font></td>");
					 str.append("<td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strUnitName[i]);str.append("</font></td>");
					 str.append("<td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strRoomName[i]);str.append("</font></td>");
					 str.append("<td width='25%'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(strQueueNo[i]);str.append("</font><br></td></tr></table>");
				 }
				 
				 
				 		 
		//		String tmpFileName=RegistrationConfig.CARD_OLD_DEPT_VISIT+userVO.getSeatId();
				patientVO.setPatPrimaryCat(patPriCatLable);
				
				//////Changing the print method moving it to NewPatientRegistrationSlip//
				RegistrationSlip regSlip= preapareSlip(arrayEpisodeForStamping,patientVO,_fb, _rq);
				_rq.getSession().setAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT, regSlip);
				boolean printmandatory=false;
				for(int i=0;i<arrayEpisodeForStamping.length;i++)
				{
					if(arrayEpisodeForStamping[i].getPatAmountCollected()!=null && !(arrayEpisodeForStamping[i].getPatAmountCollected().equals(""))
							&& !(arrayEpisodeForStamping[i].getPatAmountCollected().equals("0")))
					{
						//changed for not making the print mandatory
						//printmandatory=true;
						break;
					}
				}
				if(printmandatory)
				{
					printCard(_fb, _rq);
					_fb.setSaveSuccessful("false");
				}
				objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>Old Department Visit: </font>");
				 }
			}
			///////////////
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			objStatus.add(Status.NEW,e.getMessage(),"");
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"",e.getMessage());	
		}catch(HisUpdateUnsuccesfullException e){
					
			objStatus.add(Status.UNSUCESSFULL,"","Update Failed");	
		}		
		catch(HisDataAccessException e){
			
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e){
			e.printStackTrace();
			
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e){
			e.printStackTrace();
			
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
		return renewalStatus;
	}	*/
	
	
	
	public static void getOldPatReferDtl(SplPatientVisitFB fb,HttpServletRequest request)
	{
		UserVO userVO =getUserVO(request);
		Status objStatus=new Status();
		try
		{
			//EpisodeRefDtlVO[] arrEpisodeOldPatRefDtlVO=SplPatientVisitDATA.getSCReferPat(userVO);
			EpisodeRefDtlVO[] arrEpisodeOldPatRefDtlVO=SpclPatientVisitDATA.getReferPat(userVO,"3");

			WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO ,arrEpisodeOldPatRefDtlVO);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void getReferPatient(SplPatientVisitFB _fb,HttpServletRequest _request)
	{
		UserVO userVO =getUserVO(_request);
		Status objStatus=new Status();
		EpisodeRefDtlVO[] arrEpisodeRefDtlVO=null;
		try
		{
			//arrEpisodeRefDtlVO=SplPatientVisitDATA.getReferPat(userVO);
			arrEpisodeRefDtlVO=SpclPatientVisitDATA.getReferPat(userVO,"2");

			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_EPISODE_REFER_PAT_VO,arrEpisodeRefDtlVO);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_EPISODE_REFER_PAT_VO,arrEpisodeRefDtlVO);
			objStatus.add(Status.ERROR,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}

	
	public static void setRefDepartment(SplPatientVisitFB fb,HttpServletRequest request)
	{
		UserVO userVO =getUserVO(request);
		Status objStatus=new Status();
		try
		{
			List refDeptList=SpclPatientVisitDATA.getRefDept_AJAX(userVO, userVO.getHospitalCode());
			WebUTIL.setAttributeInSession(request,RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL ,refDeptList);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	


	public static void setNewDepartmentVisitDtl(SplPatientVisitFB _fb,
		HttpServletRequest _request) {
		Status  objStatus=new Status();
		String amountCollected="";
		String amount="";
		try{
				UserVO userVO =getUserVO(_request);
			PatientVO patVO=(PatientVO)_request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
			
			HttpSession ses=_request.getSession();
			EpisodeRefDtlVO[] arrEpisodeRefPatVO=(EpisodeRefDtlVO[])ses.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
			//System.out.println("-----------"+arrEpisodeRefPatVO+"-----------");
			//System.out.println("-----------"+_fb.getOnlineReferedIndex()+"-----------");
			boolean value=false;
			if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)){
			//	_fb.setPatCrNo(_fb.getSelectedReffereCrNo());
				int index=Integer.parseInt(_fb.getOnlineReferedIndex());
				_fb.setPatCrNo(arrEpisodeRefPatVO[index].getPatCrNo());
				//Map mp=SpclPatientVisitDATA.getAllSplNewDeptVisitEssentials(_fb.getPatCrNo(),userVO);
				Map mp=SpclPatientVisitDATA.getAllNewDeptVisitEssentials(_fb.getPatCrNo(),userVO);	    

				WebUTIL.setMapInSession(mp,_request,"SplPatientVisitwithAptmntACTION");
			}
			else if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)){
					//	_fb.setPatCrNo(_fb.getSelectedReffereCrNo());
					//int index=Integer.parseInt(_fb.getOnlineReferedIndex());
					//_fb.setPatCrNo(arrEpisodeRefPatVO[index].getPatCrNo());
					//Map mp=SpclPatientVisitDATA.getAllSplNewDeptVisitEssentials(_fb.getPatCrNo(),userVO);
					Map mp=SpclPatientVisitDATA.getAllNewDeptVisitEssentialsForSplWithAppt(_fb.getPatCrNo(),userVO);	    

					
					_fb.setToDepartment(_fb.getDepartment());
					_fb.setToDepartmentCode(_fb.getDepartmentCode().length()>3?_fb.getDepartmentCode().substring(0, 3):_fb.getDepartmentCode());
					_fb.setToDepartmentUnit(_fb.getDepartment());
					_fb.setToDepartmentUnitCode(_fb.getDepartmentUnitCode());
					_fb.setDepartmentUnitCode(_fb.getDepartmentUnitCode());

					WebUTIL.setMapInSession(mp,_request,"SplPatientVisitwithAptmntACTION");
			}
			else{
				setAllNewDeptVisitEssentials(_fb,_request);
				
			}
			setDeptOptions(_request, _fb);
			patVO.setPatCrNo(_fb.getPatCrNo());
			patVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			patVO.setIsPatReferByList(_fb.getIsPatReferByList());
			//newDeptVisitDATA.getPatientDtl(patVO,userVO);
			//WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO, patVO);
			
		
				
			
			
			//_fb.setIsPatReferByList(RegistrationConfig.IS_PAT_REFER_BY_LIST_FALSE);
		
			if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				String fromDepartmentCode="";
				int index=Integer.parseInt(_fb.getOnlineReferedIndex());
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartment()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartmentCode()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartmentUnit()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartmentUnitCode()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getFromDepartmentCode()+"------------");

				_fb.setToDepartment(arrEpisodeRefPatVO[index].getToDepartment());
				_fb.setToDepartmentCode(arrEpisodeRefPatVO[index].getToDepartmentCode());
				_fb.setToDepartmentUnit(arrEpisodeRefPatVO[index].getToDepartmentUnit());
				_fb.setToDepartmentUnitCode(arrEpisodeRefPatVO[index].getToDepartmentUnitCode());
				fromDepartmentCode=arrEpisodeRefPatVO[index].getFromDepartmentCode();
				String entryDate=arrEpisodeRefPatVO[index].getEntryDate();
				if(Integer.parseInt(arrEpisodeRefPatVO[index].getEpisodeVisitNo()) >1){
					entryDate="";
				}
				/*for(int i=0;i<arrEpisodeRefPatVO.length;i++)
				{
					if(_fb.getPatCrNo().equals(arrEpisodeRefPatVO[i].getPatCrNo()))
					{
						_fb.setToDepartment(arrEpisodeRefPatVO[i].getToDepartment());
						_fb.setToDepartmentCode(arrEpisodeRefPatVO[i].getToDepartmentCode());
						_fb.setToDepartmentUnit(arrEpisodeRefPatVO[i].getToDepartmentUnit());
						_fb.setToDepartmentUnitCode(arrEpisodeRefPatVO[i].getToDepartmentUnitCode());
						fromDepartmentCode=arrEpisodeRefPatVO[i].getFromDepartmentCode();
					}
					
				}*/
				///call only if billing is required
				//if(Config.REGISTRATION_BILLING_REQUIRED.equals(Config.REGISTRATION_BILLING_REQUIRED_YES))
					//amountCollected=newDeptVisitDATA.getBillAmountByDeptGrouping(patVO.getPatPrimaryCatCode(), fromDepartmentCode, _fb.getToDepartmentCode(),entryDate, userVO); //CALL AHIS_REG PROCEDURE //procedure not created
			}
			else{
				///call only if billing is required///
				//if(Config.REGISTRATION_BILLING_REQUIRED.equals(Config.REGISTRATION_BILLING_REQUIRED_YES))
				 //amountCollected=newDeptVisitDATA.getBillAmount(patVO.getPatPrimaryCatCode(),userVO);//commented by garima for hospital renewal logic in maharshtra
				
			}
			/*value=newDeptVisitDATA.getEmgOpenEpisodeCheck(patVO, userVO);
			if(value==true){
				System.out.println("if episode open in emg...value  "+value);
				throw new HisEpisodeOpenInEmergencyException();
			}			
			else
				System.out.println("else...if episode open in emg...value  "+value);*/
			
			/*Renewal COde commented by Garima*/
			
			RenewalConfigVO objRenewalConfigVO=null;
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(_fb.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");
			patVO.setRenewalConfig(objRenewalConfigVO);
			patVO.getRenewalConfig().setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_SPEACIAL);
			//End: Sheeldarshi: 30Sep'14
			//int key=new Integer(Config.RENEWAL_TYPE).intValue();
			int key=new Integer(patVO.getRenewalConfig().getStrRenewalType()).intValue();
			
			/*switch(key){
			case 1: case 2:
			
			if(patVO.getRegRenewalRequired()!=null&&patVO.getRegRenewalRequired().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE)){
				userVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
				
				//amount=newDeptVisitDATA.getBillAmount(patVO.getPatPrimaryCatCode(),userVO);
				amountCollected=SpclPatientVisitDATA.getBillAmount(patVO.getPatPrimaryCatCode(),userVO);
				//_fb.setAmount(amount);
				//throw new HisRenewalRequiredException();
			}
			else
			{
				amountCollected="0";
			}
			_fb.setAmount(amountCollected);
			break;
			
			}*/

			if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE))
				patVO.setDepartmentUnitCode(_fb.getDepartmentCode());
			
			patVO.setPatAptNo(_fb.getPatAptNo());
			HelperMethods.populate(_fb,patVO);
			//_fb.setPatAmountCollected(amountCollected);
			_fb.setPatBillAmountWithoutGrouping(_fb.getPatAmountCollected());
			ses=WebUTIL.getSession(_request);
			ses.setAttribute("patVO",patVO);
			ses.setAttribute(RegistrationConfig.PATIENT_VO,patVO);
			//String patStatus=RegDskNewDeptVisitUTIL.setPatientStatus( _request);
			if(patVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD))
			{
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}
			if(!(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))){
				RegistrationConfigVO registrationConfigVO=(RegistrationConfigVO)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
				EpisodeRefDtlVO[] episodeReferalDtlVO=(EpisodeRefDtlVO[])_request.getSession().getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
				
			try{
				//EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SplPatientVisitDATA.getOpenEpisodeOPD(patVO.getPatCrNo(), userVO);//procedure created
				if(!(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE))){
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(patVO.getPatCrNo(), userVO, "3");
					WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				else
				{
					//if(registrationConfigVO.getIsCrossConsulation().equals("1")&&episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0){
					if((registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_Special_Only)||registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_OPD_and_Special_Both))&&episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0){
						EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=(EpisodeRefDtlVO[])_request.getSession().getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
						WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
					}
					else{
						EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(patVO.getPatCrNo(), userVO, "3");
						WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
					}
				}
			}
			catch(HisRecordNotFoundException e){
				String msg=e.getMessage();
			}
			}
			List refDeptList=SpclPatientVisitDATA.getRefDept_AJAX(userVO, userVO.getHospitalCode());
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL ,refDeptList);
			objStatus.add(Status.TRANSINPROCESS,"","");
		
		
		}
		 catch(HisDeadPatientException e){
		    	objStatus.add(Status.ERROR_AE,e.getMessage(),"");
				//e.printStackTrace();
		    	System.out.println(e.getMessage());
		    }
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		objStatus.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisEpisodeOpenInEmergencyException e){ 
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"Episode Opened in emergency", "");
		}
		catch(HisRecordNotFoundException e){
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e){		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally{
			WebUTIL.setStatus(_request,objStatus);
		//	_request.setAttribute(RegistrationConfig.STATUS_OBJECT,objStatus);
			
		}
		
	}
	/**
	 * sets New DEpartment Visit Essentials
	 * calls getAllNewDeptVisitEssentials() from newDeptVisitDATA
	 * sets the Essential in session
	 * @param _fb -NewDeptVisitFB form bean
	 * @param _rq -HttpServletRequest
	 */
	public static void setAllNewDeptVisitEssentials( SplPatientVisitFB _fb,HttpServletRequest _rq){
		
		Status objStatus = new Status();
		try{
				
		UserVO userVO =getUserVO(_rq);		
	    //Map mp=PatientVisitDATA.getAllSplNewDeptVisitEssentials(_fb.getPatCrNo(),userVO);
	    Map mp=SpclPatientVisitDATA.getAllNewDeptVisitEssentials(_fb.getPatCrNo(),userVO);	    

		WebUTIL.setMapInSession(mp,_rq,"SplPatientVisitwithAptmntACTION");
		
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","");
			throw new HisDataAccessException();
		}
		catch(HisRecordNotFoundException e){
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","");
			throw new HisApplicationExecutionException();
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","");
			throw new HisException();
		}		
		finally
		{
			WebUTIL.setStatus(_rq,objStatus);			
		}	
	}
	/**
	 * sets Department Options 
	 * retrieves departments to visit stamp from fb
	 * removes those departments from department combo options
	 * by calling removeEntriesfromOptions() from WebUTIL
	 * sets the remaining departments in 
	 * @param _req -HttpServletRequest
	 * @param _fb -NewDeptVisitFB form bean
	 */
	public static void setDeptOptions(HttpServletRequest _req, SplPatientVisitFB _fb){
		
		String [] deptVisitArr=	_fb.getDepartmentsToVisitStamp();
		HttpSession ses=_req.getSession();		
		//Collection colOrgDept = (Collection)_req.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT);
		Collection colOrgDept = (Collection)_req.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_SPL_NEW_DEPT_VISIT_DEPARTMENT);
		//Collection colOrgDept = (Collection)_req.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
		//System.out.println("-------------------"+colOrgDept+"--------------------");
		Collection newCol = new ArrayList(colOrgDept);
		//<<why returned collection need to be stored
	    newCol=WebUTIL.removeEntriesfromOptions(newCol, deptVisitArr);
	    //System.out.println("-------------------"+newCol+"--------------------");
		ses.setAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
		ses.setAttribute(RegistrationConfig.SPLREGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
		///for capturing department specific mandatory fiels
		//ses.setAttribute(Config.REGISTRATION_MANDATORY_DEPT_LIST, newCol);
		
	}
	
	public static void setOldDepartmentVisitDtlOFF(SplPatientVisitFB _fb,
			HttpServletRequest request) {
		Status status=new Status();
		UserVO userVO =getUserVO(request);
		PatientVO patVO=(PatientVO)request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
		try{status.add(Status.TRANSINPROCESS);}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();	   		
	   		status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
		}
	   	catch(HisNotAnOPDcaseException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
		
	}
	
	

	public static void setOldDepartmentVisitDtl(SplPatientVisitFB _fb,
			HttpServletRequest request) {
		Status status=new Status();
		UserVO userVO =getUserVO(request);
		PatientVO patVO=(PatientVO)request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
		try{
			
			
			HttpSession ses=request.getSession();
			EpisodeRefDtlVO[] arrEpisodeOldPatRefVO=(EpisodeRefDtlVO[])ses.getAttribute(RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO);
			if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				int	indexID=Integer.parseInt(_fb.getIndexID());
				//System.out.println("----------"+_fb.getIndexID()+"-----------");
				//System.out.println("----------"+arrEpisodeOldPatRefVO+"-----------");
				_fb.setPatCrNo(arrEpisodeOldPatRefVO[indexID].getPatCrNo());
				if(arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode()==null ||arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode()=="")
				{
					//_fb.setDeptOrUnitName(arrEpisodeOldPatRefVO[indexID].getToDepartment());
					EpisodeVO[] arrEpisodeVO=null;
					arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByDept(_fb.getPatCrNo(), arrEpisodeOldPatRefVO[indexID].getToDepartmentCode(), patVO.getRenewalConfig().getStrRenewalType(), userVO, RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					//arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByDept(_fb.getPatCrNo(),arrEpisodeOldPatRefVO[indexID].getToDepartmentCode(), userVO, RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
					WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
					WebUTIL.setAttributeInSession(request,RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);

				}
				else
				{
					//_fb.setDeptOrUnitName(arrEpisodeOldPatRefVO[indexID].getToDepartmentUnit());
					EpisodeVO[] arrEpisodeVO=null;
					arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByUnit(_fb.getPatCrNo(),arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode(), patVO.getRenewalConfig().getStrRenewalType(), userVO, RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					//arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByUnit(_fb.getPatCrNo(),arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode(), userVO, RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
					WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
					WebUTIL.setAttributeInSession(request,RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);

				}
				
			}
	
			if(!(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)))
			{
			setEpisodeDetails(_fb,request);
			//Map referEssDept=SpclPatientVisitDATA.referedEssentialDepartment(userVO);  
			//WebUTIL.setMapInSession(referEssDept,request,"SplPatientVisitwithAptmntACTION");
			}
			
			RegistrationConfigVO registrationConfigVO=(RegistrationConfigVO)request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			EpisodeRefDtlVO[] episodeReferalDtlVO=(EpisodeRefDtlVO[])request.getSession().getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
		
			if((_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)))
			{
				EpisodeVO[] arrEpisodeVO=null;
				//arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByUnit(_fb.getPatCrNo(),_fb.getDepartmentCode(), userVO, RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
				arrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByUnit(_fb.getPatCrNo(),_fb.getDepartmentUnitCode(),patVO.getRenewalConfig().getStrRenewalType(),userVO, RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
				WebUTIL.setAttributeInSession(request,RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
				Map referEssDept=SpclPatientVisitDATA.referedEssentialDepartment(userVO);  
				WebUTIL.setMapInSession(referEssDept,request,"SplPatientVisitwithAptmntACTION");
				
				//if(registrationConfigVO.getIsCrossConsulation().equals("1")&&episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0){
				if((registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_Special_Only)||registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_OPD_and_Special_Both))&&episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0){
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=(EpisodeRefDtlVO[])request.getSession().getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
					WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}	
				else{
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(patVO.getPatCrNo(), userVO, "3");
					WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				for(int i=0;i<arrEpisodeVO.length;i++)
				{
					EpisodeVO tempEpisodeVo=(EpisodeVO)arrEpisodeVO[i];
					if(tempEpisodeVo.getRenewalRequired().equals("1"))
						_fb.setSplRenewalRequired(tempEpisodeVo.getRenewalRequired());
				}
			}
			
			if((_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_FALSE))){
				try{
					//EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(patVO.getPatCrNo(), userVO);//procedure created
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(patVO.getPatCrNo(), userVO, "3");
					WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				catch(HisRecordNotFoundException e){
					String msg=e.getMessage();
				}
			}
			//List refDeptList=SpclPatientVisitDATA.setRefDepartment(userVO);
			List refDeptList=SpclPatientVisitDATA.getRefDept_AJAX(userVO,userVO.getHospitalCode());
			WebUTIL.setAttributeInSession(request,RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL ,refDeptList);
			status.add(Status.TRANSINPROCESS);
		
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();	
			
			WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, null);
			WebUTIL.setAttributeInSession(request,RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, null);
			if(_fb.getModeCase().equals("1") || !_fb.getSelectedReferal().equals("0"))
	   		status.add(Status.UNSUCESSFULL,"","");
			else
			status.add(Status.UNSUCESSFULL,e.getMessage(),"");
		}
	   	catch(HisNotAnOPDcaseException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
		
	}
	
	
	
	


	
	

	private static EpisodeVO[] populateNewDepartmentVisitEpisode(
			HttpServletRequest _request, SplPatientVisitFB _fb) {
		
		
		HttpSession ses=_request.getSession();
		EpisodeVO[] arrEpisodeVO =null;
		
		try
		{
			Collection col=(Collection)_request.getSession().getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
			String deptName="";
			String[] deptToVisit = new String[0];
			arrEpisodeVO = new EpisodeVO[deptToVisit.length];
			String patAmountCollected=_fb.getPatAmountCollected();
			
			if(deptToVisit.length==0){
				arrEpisodeVO = new EpisodeVO[1];			
				arrEpisodeVO[0]=new EpisodeVO();
				System.out.println("-----------------"+_fb.getDepartmentUnitCode()+"-------------------");
				//arrEpisodeVO[0].setDepartmentCode(_fb.getDepartmentUnitCode().substring(0,3));
				arrEpisodeVO[0].setDepartmentCode(_fb.getDepartmentUnitCode());
				arrEpisodeVO[0].setDepartmentUnitCode(_fb.getDepartmentUnitCode());
				arrEpisodeVO[0].setFileNo(_fb.getFileNo());
				arrEpisodeVO[0].setPatAmountCollected(patAmountCollected);
				arrEpisodeVO[0].setIsReferred(_fb.getIsReferred());
				if(_fb.getIsReferred()!=null && _fb.getIsReferred().equals("on")){
					arrEpisodeVO[0].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				}
				if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])ses.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
					int index=Integer.parseInt(_fb.getOnlineReferedIndex());
					deptName=episodeRefDtlVO[index].getToDepartment();
				}else{
				 deptName=SplRegistrationUTIL.getEntryLabel(col,_fb.getDepartmentUnitCode());
				}
				System.out.println("-----------------"+deptName+"-------------------");
				//since department contains department name plus unit name
				//arrEpisodeVO[0].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
				arrEpisodeVO[0].setDepartment(deptName);
			} 
			else{
				arrEpisodeVO = new EpisodeVO[deptToVisit.length];
				for(int i=0; i<arrEpisodeVO.length; i++){
					arrEpisodeVO[i]=new EpisodeVO();
					arrEpisodeVO[i].setDepartmentCode(deptToVisit[i]);
					//arrEpisodeVO[i].setPatAmountCollected(_fb.getPatAmountCollected());
					arrEpisodeVO[i].setIsReferred(_fb.getIsReferred());
					arrEpisodeVO[0].setPatAmountCollected(patAmountCollected);
					if(_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])ses.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
						int index=Integer.parseInt(_fb.getOnlineReferedIndex());
						deptName=episodeRefDtlVO[index].getToDepartment();
					}else{
					 deptName=SplRegistrationUTIL.getEntryLabel(col,deptToVisit[i]);
					}
					arrEpisodeVO[i].setDepartment(deptName);
				}
			} 
			String sysdate=(String)ses.getAttribute(Config.SYSDATE);
			
			

			_request.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE, _fb.getDepartmentCode());
			if(_fb.getDepartmentUnitCode().equals(RegistrationConfig.RADIOTHERAPY_DEPT_CODE)){
				arrEpisodeVO[0].setPatAmountCollected("0");
			}

			if(_fb.getDepartmentUnitCode().equals(RegistrationConfig.RADIOTHERAPY_DEPT_CODE)){
				
				arrEpisodeVO[0].setPatAmountCollected("0");
			}

			// setting the registration charge 0 for the department code matched in the configuration
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrEpisodeVO;
	}
	
	
	
	public static void getAppointmentNewRegTdDtl(SplPatientVisitFB fb,HttpServletRequest request)
	{
		UserVO userVO =getUserVO(request);
		Status objStatus=new Status();
		PatientVO[] arrPatDtlVO=null;
		
		Map mapOfRenewalVoOnKeyPatCatGroup=new HashMap();
		try
		{
			//PatientVO[] arrPatDtlVO=SpclPatientVisitDATA.getApptmentPatRegd(userVO);
			arrPatDtlVO=SpclPatientVisitDATA.getApptmentPatRegd(userVO);
			
			WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO ,arrPatDtlVO);
			WebUTIL.setAttributeInSession(request,RegistrationConfig.ALL_PATIENT_VO_LIST ,arrPatDtlVO);
			
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			//e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","<div id='divNoAppointmentAvailableLabel'>"+e.getMessage()+"</div>");
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}	
	
	public static void getVisitTdDtl(SplPatientVisitFB fb,HttpServletRequest request)
	{
		UserVO userVO =getUserVO(request);
		Status objStatus=new Status();
		try
		{
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}	
	
	/**
	 * To Populate the Form bean with the selected Appointment No Details 
	 * Added by Singaravelan on 28-Aug-2014
	 */
	
	public static void setVoArrEssentials(HttpServletRequest _request,SplPatientVisitFB _fb)
	{
		Status objStatus=new Status();
		HttpSession _ses=_request.getSession();
		PatientVO[] _voArr=((PatientVO[])_ses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST));
		PatientVO[] _newVoArr={};
		List<PatientVO> lstPatVo=new ArrayList<PatientVO>();
		try{
			for(PatientVO _voTemp:_voArr){			
				if((_fb.getSearchId().equalsIgnoreCase("1")&&_voTemp.getPatAptNo().contains(_fb.getSearchValue()))||
						(_fb.getSearchId().equalsIgnoreCase("2")&&_voTemp.getPatFirstName().toUpperCase().contains(_fb.getSearchValue().toUpperCase()))||
						(_fb.getSearchId().equalsIgnoreCase("3")&&_voTemp.getPatAddContactNo().contains(_fb.getSearchValue()))||
						//(_fb.getSearchId().equalsIgnoreCase("4")&&_voTemp.getPatAddEmailId().contains(_fb.getSearchValue())))//Commented By Mukund to add Crno as a searching criteria too
						(_fb.getSearchId().equalsIgnoreCase("4")&&_voTemp.getPatCrNo().contains(_fb.getSearchValue())))
					lstPatVo.add(_voTemp);
				
			}	
			_newVoArr=new PatientVO[lstPatVo.size()];
			if(lstPatVo.size()>0){
				for (int i = 0; i < lstPatVo.size(); i++){
					_newVoArr[i] = (PatientVO) lstPatVo.get(i);
				}
			}	
			else{
				WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO,null);
				WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
				throw new HisRecordNotFoundException("Patient Appointment Details Not Found");
			}
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO,_newVoArr);
			objStatus.add(Status.LIST,"","");
		}
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
		
	}
	
	/**
	 * To search in the list of Patients in the Special Clinic Registration with Appointment
	 * retrieves a list of patients registered in the last specified duration
	 * 
	 * @param _request - HttpServletRequest
	 * Added by Singaravelan on 28-Aug-2014
	 */
	public static void searchSpecialCRNoWithAppointment(HttpServletRequest _request,SplPatientVisitFB _fb){
		Status objStatus=new Status();
		 PatientVO patientVO[]=null;
		 PatientVO _patVO=new PatientVO();

		try{
			 UserVO userVO =getUserVO(_request);
			 setSysdate(_request);			 
			 HelperMethods.populate(_patVO, _fb);
			 
			 if(_patVO.getSearchId().equalsIgnoreCase("1"))
					 _patVO.setSearchId("HAPNUM_APT_REQ_NO");
			 else if(_patVO.getSearchId().equalsIgnoreCase("2"))
					 _patVO.setSearchId("HAPSTR_PAT_FIRST_NAME");
			 else if(_patVO.getSearchId().equalsIgnoreCase("3"))
					 _patVO.setSearchId("HAPSTR_MOBILE_NO");	
			 else if(_patVO.getSearchId().equalsIgnoreCase("4"))
				 _patVO.setSearchId("HAPSTR_EMAIL");	
				 
			 patientVO=SpclPatientVisitDATA.searchSpecialCRNoWithAppointment(_patVO,userVO);		
		
		WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO,patientVO);
		objStatus.add(Status.LIST,"","");	
		   }
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
	}
	
	
	
	public static void setPatientAptDtlByAptNO(SplPatientVisitFB fb,HttpServletRequest request)
	{
		UserVO userVO =getUserVO(request);
		Status objStatus=new Status();
		try
		{
			PatientVO _patVO=new PatientVO();
			_patVO.setPatAptNo(fb.getPatAptNo());
			_patVO=SpclPatientVisitDATA.getApptmentDtlsWithAptNO(_patVO, userVO);
			fb.setPatAptNo(_patVO.getPatAptNo());
			fb.setPatAptQueueNO(_patVO.getPatAptQueueNO());
			fb.setPatAptSlot(_patVO.getPatAptSlot());
			fb.setIsAppointment("1");
		}
		catch(HisRecordNotFoundException e)
		{
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	
	public static void setReferEpisodeDtl(SplPatientVisitFB _fb,
			HttpServletRequest request) {
		Status status=new Status();
		UserVO userVO =getUserVO(request);
		PatientVO patVO=(PatientVO)request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
		try{			
			
			HttpSession ses=request.getSession();

			if((_fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)))
			{
				EpisodeRefDtlVO[] arrEpisodeRefDtlVO=null;
				arrEpisodeRefDtlVO=SpclPatientVisitDATA.getReferEpisodeDtl(_fb.getPatCrNo(),_fb.getDepartmentUnitCode(),userVO);
				WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO, arrEpisodeRefDtlVO);

			}
		
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();	   		
	   		status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
		}
	   	catch(HisNotAnOPDcaseException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
		
	}
	
	
	public static void setPatAmount(SplPatientVisitFB objPatVisitSUP_p,PatientVO objPatientVO_p,UserVO objUserVO_p, HttpServletRequest objRequest_p ){
		String	strRenewalType =objPatientVO_p.getRenewalConfig().getStrRenewalType();
		String strAmountCollected="";
		String strAmountWOCrConsultation="0.00";
		HttpSession session=objRequest_p.getSession();
		if(strRenewalType==null || strRenewalType.equals("0"))
			strRenewalType="0";
		int key= Integer.parseInt(strRenewalType);
		System.out.println("strRenewalType :"+key);
		session.setAttribute("PatPrimaryCatsCode", objPatientVO_p.getPatPrimaryCatCode());
		
		if(strRenewalType.equals("2"))
			if(objPatientVO_p.getSplRenewalRequired().equals("2")){
				objPatientVO_p.setSplRenewalRequired("2");
				objPatVisitSUP_p.setRegRenewalRequired("2");
			}
		
		objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_RENEWAL_TARIFF_ID);
		String strRenewalAmountCollected=PatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
		
		
		//To set the Tariff ID on the basis Config and Referal Details for the Cross Consultation Added by Singaravelan on 08-Oct-2014\
		RegistrationConfigVO registrationConfigVO=(RegistrationConfigVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
		EpisodeRefDtlVO[] episodeReferalDtlVO=(EpisodeRefDtlVO[])objRequest_p.getSession().getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
		
				
		//Cross Consultation code end by S.Velen on 08-Oct-2014
		
		
		
		if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1") &&
				objPatientVO_p.getOtherHospitalDataFound()!=null && objPatientVO_p.getOtherHospitalDataFound().equals("0")){
			
			strAmountCollected="0.00";
			//objPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected);
			//objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
			//objPatVisitSUP_p.setPatRenewalAmountDeptWise(strAmountCollected);
			//objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
			
			objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
		}
		else{
			switch(key)
			{
				case 1: case 2:
				
					if(objPatientVO_p.getSplRenewalRequired()!=null && objPatientVO_p.getSplRenewalRequired().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE)){
						strAmountCollected=strRenewalAmountCollected;
					}
					else if(objPatientVO_p.getSplRenewalRequired()!=null && objPatientVO_p.getSplRenewalRequired().equals("2")){
						objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
						strAmountCollected=PatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
					}
					else
					{
						strAmountCollected="0.00";
						if(episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0)
						{
							//if(registrationConfigVO.getIsCrossConsulation().equalsIgnoreCase("1"))
							if(registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_Special_Only)||registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_OPD_and_Special_Both))
							{
								objUserVO_p.setTariffID(RegistrationConfig.SPL_CROSS_CONSULTATION_TARIFF_ID);
								strAmountCollected=PatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
								objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
								strAmountWOCrConsultation=PatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
							}
							else
								strAmountCollected="0.00";
						}
						else
							strAmountCollected="0.00";
						
						
						
					}
					//objPatientVO_p.setPatAmountCollected(strAmountCollected);
					//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
					if(strAmountCollected==null || strAmountCollected.equals("")|| strAmountCollected.equals("-1"))
						strAmountCollected="0.00";
					if(strAmountWOCrConsultation==null || strAmountWOCrConsultation.equals("")|| strAmountWOCrConsultation.equals("-1"))
						strAmountWOCrConsultation="0.00";
					if(!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
							!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
						//objPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected);
						objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
						objPatVisitSUP_p.setPatAmountNCrConsultation(strAmountWOCrConsultation);
						objPatVisitSUP_p.setPatAmountCrConsultation(strAmountCollected);
						//objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
						//SPECIAL_NEW_DEPT_VISIT_TARIFF_ID
					}
					else
					{
						//objPatVisitSUP_p.setPatAmountHospitalWise(RegistrationConfig.PAT_CAT_FREE_FEES);
						objPatVisitSUP_p.setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
						objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objPatVisitSUP_p.setPatAmountNCrConsultation(strAmountWOCrConsultation);
						objPatVisitSUP_p.setPatAmountCrConsultation(strAmountCollected);
					}
					break;
					
				case 3: case 4:
					//objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
					
					if(objPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on")){
												
						if(episodeReferalDtlVO!=null&&episodeReferalDtlVO.length>0)
						{
							//if(registrationConfigVO.getIsCrossConsulation().equalsIgnoreCase("1")){
							if(registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_Special_Only)||registrationConfigVO.getIsCrossConsulation().equals(RegistrationConfig.Cross_Consultation_For_OPD_and_Special_Both)){
								objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
								strAmountWOCrConsultation=PatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
								objUserVO_p.setTariffID(RegistrationConfig.SPL_CROSS_CONSULTATION_TARIFF_ID);
							}
							else
								objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
						}
						else
							objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
						
						strAmountCollected=PatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
						strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")|| strAmountCollected.equals("-1")?"0.00":strAmountCollected;
						
						//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
						if(!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
								!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
							//objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
							//objPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected);
							objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
							objPatVisitSUP_p.setPatAmountNCrConsultation(strAmountWOCrConsultation);
							objPatVisitSUP_p.setPatAmountCrConsultation(strAmountCollected);

						}
						else
						{
							//objPatVisitSUP_p.setPatAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);
							//objPatVisitSUP_p.setPatNewDeptVisitAmount(RegistrationConfig.PAT_CAT_FREE_FEES);
							objPatVisitSUP_p.setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
							objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatAmountNCrConsultation(strAmountWOCrConsultation);
							objPatVisitSUP_p.setPatAmountCrConsultation(strAmountCollected);

						}
						
					}
					if(objPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on")){
						objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
						strAmountCollected=PatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
						strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")|| strAmountCollected.equals("-1")?"0.00":strAmountCollected;
						
						//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
						if(!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
								!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
							//objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);							
							//Renewal Required Amount
							objPatVisitSUP_p.setPatRenewalAmountDeptWise(strRenewalAmountCollected);
							objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
							objPatVisitSUP_p.setPatAmountNCrConsultation(strAmountWOCrConsultation);
							objPatVisitSUP_p.setPatAmountCrConsultation(strAmountCollected);

						}
						else
						{
							objPatVisitSUP_p.setPatAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);							
							//Renewal Required Amount
							objPatVisitSUP_p.setPatRenewalAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);
							//objPatVisitSUP_p.setPatRenewalAmountDeptWise(strRenewalAmountCollected);
							//objPatVisitSUP_p.setPatRenewalActualAmount(strRenewalAmountCollected);
							objPatVisitSUP_p.setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
							//By Mukund on 16.11.2016 
							if(objPatVisitSUP_p.getStrRenewalType()!=null && !objPatVisitSUP_p.getStrRenewalType().equals(""))
								objPatVisitSUP_p.setPatActualAmount(strRenewalAmountCollected);
							else
								objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
							//End: Mukund
							objPatVisitSUP_p.setPatAmountNCrConsultation(strAmountWOCrConsultation);
							objPatVisitSUP_p.setPatAmountCrConsultation(strAmountCollected);
							

						}
						
						
					}
					break;
				default : 
						strAmountCollected="0.00";
						//objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
						//objPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected);
						//objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
						//objPatVisitSUP_p.setPatRenewalAmountDeptWise(strAmountCollected);
						//objPatientVO_p.setPatAmountCollected(strAmountCollected);
						objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
						objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objPatVisitSUP_p.setPatAmountNCrConsultation(strAmountWOCrConsultation);
						objPatVisitSUP_p.setPatAmountCrConsultation(strAmountCollected);

			}
		}
		
		
		System.out.println("strAmountCollected :"+strAmountCollected);
		System.out.println("strRenewalAmountCollected :"+strRenewalAmountCollected);
	}
	
	public static void getCreditBeneficiaryDtlForPatCatOnCRNO(SplPatientVisitFB objPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		BeneficiaryPatientVO creditBeneficiaryDtl_VO=new BeneficiaryPatientVO();
		try
		{
			System.out.println("PatientVisitUTL :: getCreditBeneficiaryDtlForPatCatOnCRNO()");
			creditBeneficiaryDtl_VO=PatientVisitDATA.getCreditBeneficiaryEssentials(objPatVisitSUP_p, objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_CREDIT_BENEFICIARY_VO,creditBeneficiaryDtl_VO);

			objPatVisitSUP_p.setClientCode(creditBeneficiaryDtl_VO.getClientCode());
			objPatVisitSUP_p.setClientName(creditBeneficiaryDtl_VO.getClientName());
			
			if(objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))					
				objPatVisitSUP_p.setStaffCardNo(creditBeneficiaryDtl_VO.getStaffCardNo());
			else
				objPatVisitSUP_p.setAgsNo(creditBeneficiaryDtl_VO.getStaffCardNo());
		
			objPatVisitSUP_p.setCreditLetterRefNo(creditBeneficiaryDtl_VO.getCreditLetterRefNo());
			objPatVisitSUP_p.setLetterType(creditBeneficiaryDtl_VO.getLetterType());
			//added by Mukund on 27.07.2016
			//objPatVisitSUP_p.setLetterType(creditBeneficiaryDtl_VO.getLetterType());
			//objPatVisitSUP_p.setCreditLimit(creditBeneficiaryDtl_VO.getBalanceCreditLimit());
			//objPatVisitSUP_p.setAgsCreditLimit(creditBeneficiaryDtl_VO.getCreditLimit());
			
			objPatVisitSUP_p.setCreditLetterDate(creditBeneficiaryDtl_VO.getCreditLetterDate());
			objPatVisitSUP_p.setStaffCardName(creditBeneficiaryDtl_VO.getStaffCardName());
			objPatVisitSUP_p.setRelationWithStaff(creditBeneficiaryDtl_VO.getDependentRelationCode());
			objPatVisitSUP_p.setCardvalidityDate(creditBeneficiaryDtl_VO.getCardvalidityDate());

			objPatVisitSUP_p.setAgsDistrictCode(creditBeneficiaryDtl_VO.getAgsDistrictCode());
			objPatVisitSUP_p.setAgsCounterNo(creditBeneficiaryDtl_VO.getAgsCounterNo());
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_CREDIT_BENEFICIARY_VO,creditBeneficiaryDtl_VO);
			//objStatus.add(Status.ERROR,"",e.getMessage());
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			objPatVisitSUP_p.setErrorMessage("Record Not Found");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
		}
		catch(HisException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}
	
	public static void getInitEssentials(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		try{
			UserVO userVO=getUserVO(objRequest);
			HospitalMstVO hospitalVO=getHospitalVO(objRequest);
			
			String strCountryCode = RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE;
			String strStateCode   =	hospitalVO.getState();			
			
			List lstDistrict=PatientVisitDATA.getDistrict_AJAX(userVO, strStateCode, strCountryCode);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);
			
			List lstRelation=PatientVisitDATA.getRelationsList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,lstRelation);
			
			List lstClient=PatientVisitDATA.getClientList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST,lstClient);
			//added by Mukund on 27.07.2016
			List lstLetterType=PatientVisitDATA.getLetterTypeList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST,lstLetterType);
			//Start warish for get cash combo
			List lstPaymentMode=PatientVisitDATA.getPaymentModeList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.PAYMENT_MODE_OPTION_LIST,lstPaymentMode);
			//end

			
		}
		
		catch(Exception e){
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,new ArrayList());
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,new ArrayList());
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST,new ArrayList());

		
		}
		
		
	}
	/* #Start				:Sheeldarshi 
	#Modify Date(CR/PRS)	:22ndMay'15 
	#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	public static void getCashCollectionDetail(HttpServletRequest request,HttpServletResponse response, SplPatientVisitFB objPatSup_p) {
		// TODO Auto-generated method stub
		//NewPatientRegistrationSUP voObj = null;
	
		RegEssentialBO  essentialBO=new RegEssentialBO();
		BillConfigUtil bcu = null;
		try {
	
			PatientVO patientVO = new PatientVO();	
			HttpSession session=request.getSession();
			UserVO userVO=getUserVO(request);
			String strHospitalCode = session.getAttribute("HOSPITAL_CODE").toString();
			bcu = new BillConfigUtil(strHospitalCode);
			String strSeatId = session.getAttribute("SEATID").toString();
			
			
					
			patientVO.setStrValue1(strHospitalCode);
			patientVO.setStrValue2(strSeatId);
		
			
			essentialBO.getCashCollectionDetail(patientVO,userVO);
			
			if (patientVO.getStrMsgType().equals("0"))
			{
				objPatSup_p.setStrResultWs(patientVO.getGblWs1());

						
				getCashCollectionDetailView(patientVO,request);
			      
				

			} 
			else 
			{

				throw new Exception(patientVO.getStrMsgString());

			}
			
			
		}
		catch (Exception e) 
		{

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Global Billing File", "hisglobal.BillingDATA.getCashCollectionDetail()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (IOException e1) 
			{
				//System.out.println("Inside IInd Else::::"+e1.getMessage());
			}

		} 
		finally {

				essentialBO = null;
				//patientVO = null;
				bcu = null;
		}
	}
	public static void getCashCollectionDetailView(PatientVO voObj, HttpServletRequest _request) {
		WebRowSet ws = voObj.getGblWs1();
		
		try {

			
			if (ws != null) {
				
				if(ws.size() != 0){
					
					ws.next();
							
				
					//Double netAmount=   ws.getDouble(3)+ws.getDouble(4);
				_request.setAttribute("userName",ws.getString(1));
				_request.setAttribute("totalBill", ws.getInt(2));
				_request.setAttribute("recievedAmount", HisUtil.getAmountWithDecimal(ws.getDouble(3),2));
				_request.setAttribute("refundAmount", HisUtil.getAmountWithDecimal(Math.abs(ws.getDouble(4)),2));
				_request.setAttribute("netAmount", HisUtil.getAmountWithDecimal(ws.getDouble(5),2));
			
				System.out.println("recievedAmount"+ws.getDouble(3));
				
			} else {

				throw new Exception("Cash Collection WebRowSet is Null");

			}
			}
			} catch (Exception e) {
			
		 
			
			new HisException("Cash Collection Detail",
					"billing.HLPBilling.getCashCollectionDetailView()-->", e
							.getMessage());
		}

	}

	//By Mukund to populate the deptToSearchFrom Dropdown
	public static void getDeptUnit(HttpServletRequest _request)
	{
			Status objStatus=new Status();
			Map mp=new HashMap();
			try{
				 UserVO userVO =getUserVO(_request);
				 setSysdate(_request);			 
				mp=SplRegistrationDATA.getRegistrationFormInitialEssentials(userVO);
				mp.put("deptToSearchFrom", mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));
				WebUTIL.setMapInSession(mp,_request,"NewSplRegwithAptmntACTION");
	 			objStatus.add(Status.LIST,"","");	
			   }
			catch (HisRegistrationTimingExpiredException e) {
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				WebUTIL.removeFromStatus(_request,Status.NEW);
				e.printStackTrace();
			}
			catch(HisRecordNotFoundException e){
				objStatus.add(Status.ERROR_DA,"","<div id='divNoAppointmentAvailableLabel'>"+e.getMessage()+"</div>");
				objStatus.add(Status.LIST,"","");
				e.printStackTrace();
			}
			catch(HisDataAccessException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){
				objStatus.add(Status.ERROR_AE,e.getMessage(),"");
				e.printStackTrace();
			}
			catch(HisException e){
				objStatus.add(Status.ERROR,e.getMessage(),"");
				e.printStackTrace();
			}
			finally
			{
			WebUTIL.setStatus(_request,objStatus);			
			}
	}
	/***for Patient QR Code while revisit*/
	public static void setQrCode(SplPatientVisitFB objVisitSUP_p, PatientVO objPatientVO, UserVO objUserVO, HttpServletRequest objRequest_p)
	{
		String strQRCode = EmgPatientVisitDATA.getPatientQRCode(objPatientVO.getPatCrNo(), objUserVO);
		if(strQRCode!=null && !strQRCode.equals(""))
		{
			objPatientVO.setStrQRCode(strQRCode);
		}
	}
	
}//end of class


