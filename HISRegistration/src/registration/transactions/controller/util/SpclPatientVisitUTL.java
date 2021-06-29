package registration.transactions.controller.util;
/**
 * Created By 	: Aadil Wasi
 * Date			: March 2013
 */
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisSQLManualException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.HisPrinterSupport;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.QRCodeTest;
import registration.config.RegistrationConfig;
import registration.config.RegistrationConfigurationBean;
import registration.config.Exceptions.HisAppointmentNotAvailableException;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisEpisodeOpenInEmergencyException;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisRenewalRequiredException;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.actionsupport.SpclPatientVisitSUP;
import registration.transactions.controller.data.NewRegistrationDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import registration.transactions.controller.data.SpclPatientVisitDATA;
import vo.registration.CreditAvailDetailVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RenewalConfigVO;

public class SpclPatientVisitUTL extends ControllerUTIL
{	
	public static void setPatAmount(SpclPatientVisitSUP objSpclPatVisitSUP_p,PatientVO objPatientVO_p,UserVO objUserVO_p, HttpServletRequest objRequest_p ){
		String	strRenewalType =objPatientVO_p.getRenewalConfig().getStrRenewalType();
		String strAmountCollected="";
		
		if(strRenewalType==null || strRenewalType.equals("0"))
			strRenewalType="0";
		int key= Integer.parseInt(strRenewalType);
		System.out.println("strRenewalType :"+key);
		System.out.println("splRenewalRequired :"+ objSpclPatVisitSUP_p.getSplRenewalRequired());
		
		if(strRenewalType.equals("2"))
			if(objPatientVO_p.getSplRenewalRequired().equals("2")){
				objSpclPatVisitSUP_p.setSplRenewalRequired("2");
				objPatientVO_p.setSplRenewalRequired("2");
			}
		
		
		objUserVO_p.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
		String strRenewalAmountCollected=SpclPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
		
		switch(key)
		{
			case 1: case 2:
			
				if(objPatientVO_p.getSplRenewalRequired()!=null && objPatientVO_p.getSplRenewalRequired().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE)){
					strAmountCollected=strRenewalAmountCollected;
				}
				else if(objPatientVO_p.getSplRenewalRequired()!=null && objPatientVO_p.getSplRenewalRequired().equals("2")){
					objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
					strAmountCollected=SpclPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
				}
				else
				{
					strAmountCollected="0.00";
				}
				objSpclPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected);
				break;
				
			case 3: case 4:
				//objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
				
				if(objSpclPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on")){
					objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
					strAmountCollected=SpclPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
					strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")?"0.00":strAmountCollected;
					objSpclPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
					objSpclPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected);
				}
				if(objSpclPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on")){
					objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
					strAmountCollected=SpclPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
					strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")?"0.00":strAmountCollected;
					objSpclPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
					
					//Renewal Required Amount
					objSpclPatVisitSUP_p.setPatRenewalAmountDeptWise(strRenewalAmountCollected);
				}
				break;
			default : 
					strAmountCollected="0.00";
					//objSpclPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
					objSpclPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected);
					objSpclPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
					objSpclPatVisitSUP_p.setPatRenewalAmountDeptWise(strAmountCollected);
		}
		
		
		System.out.println("strAmountCollected :"+strAmountCollected);
		System.out.println("strRenewalAmountCollected :"+strRenewalAmountCollected);
	}
	public static void setPatientDtlByCrno( SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p){
		
		System.out.println("SpclPatientVisitUTL :: setPatientDtlByCrno()");
		
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		objPatientVO.setPatCrNo(objSpclPatVisitSUP_p.getPatCrNo());
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL;
		try{
			Date sysDate=(Date)objRequest_p.getSession().getAttribute(Config.SYSDATEOBJECT);
			objSpclPatVisitSUP_p.setSysdate(HelperMethods.getSysdate(sysDate, "dd-MMM-yyyy"));
			
			objPatientVO=SpclPatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, visitType);
			
			objSpclPatVisitSUP_p.setPatPrimaryCatGrpCode(objPatientVO.getPatPrimaryCatGrpCode());
			objSpclPatVisitSUP_p.setSplRenewalRequired(objPatientVO.getSplRenewalRequired());
			
			if(objPatientVO.getIsCatExpired()!=null && objPatientVO.getIsCatExpired().equals("1"))
				throw new HisException("This Category Is Expired. Kindly Select Another Record or Renew its first.");
			if(RegistrationConfig.PATIENT_IS_DEAD.equals(objPatientVO.getPatIsDead()))
			{
				objSpclPatVisitSUP_p.setAfterGo("0");
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}
			
			if(objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO)==null)
				SpclPatientVisitUTL.getMapOfRenewalConfigDtlOnKeyPatCat(objSpclPatVisitSUP_p,objRequest_p);
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			RenewalConfigVO objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(objPatientVO.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");
			
			objSpclPatVisitSUP_p.setStrRenewalType(objRenewalConfigVO.getStrRenewalType());
			objRenewalConfigVO.setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_SPEACIAL);
			objPatientVO.setRenewalConfig(objRenewalConfigVO);
			
			// Setting Patient Amount
			setPatAmount(objSpclPatVisitSUP_p, objPatientVO, objUserVO, objRequest_p);
			
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			//HttpSession objSession=objRequest_p.getSession();
			
			if(objSpclPatVisitSUP_p.getModeCase().equals("0"))
			{
		 		
		 		objSpclPatVisitSUP_p.setOldDepartmentVisit("On");
		 		setOldDepartmentVisitDtl(objSpclPatVisitSUP_p,objRequest_p);
			}
			else if(objSpclPatVisitSUP_p.getModeCase().equals("1"))
			{
				
				objSpclPatVisitSUP_p.setNewDepartmentVisit("On");
				setNewDepartmentVisitDtl(objSpclPatVisitSUP_p, objRequest_p);
			}
			else if(objSpclPatVisitSUP_p.getModeCase().equals("2"))
			{
				if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					//System.out.println("---------"+objSpclPatVisitSUP_p.getSelectedReferal()+"----------");
					if(objSpclPatVisitSUP_p.getSelectedReferal().equalsIgnoreCase("O"))
					{
						objSpclPatVisitSUP_p.setOldDepartmentVisit("On");
						setOldDepartmentVisitDtl(objSpclPatVisitSUP_p,objRequest_p);
					}
					else
					{
						objSpclPatVisitSUP_p.setNewDepartmentVisit("On");
						setNewDepartmentVisitDtl(objSpclPatVisitSUP_p, objRequest_p);
					}

				}
				else
				{
					objSpclPatVisitSUP_p.setOldDepartmentVisit("On");
					objSpclPatVisitSUP_p.setNewDepartmentVisit("On");
					setOldDepartmentVisitDtl(objSpclPatVisitSUP_p,objRequest_p);
					setNewDepartmentVisitDtl(objSpclPatVisitSUP_p, objRequest_p);
				}
						
			}
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
			objSpclPatVisitSUP_p.setErrorMessage("Renewal Required");
	   		//status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   		{
	   			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   		}
	   		if(e.getMessage()!=null && e.getMessage().trim().equals("Patient Details Not Found"))
	   			objSpclPatVisitSUP_p.setAfterGo("0");
	   		//status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
	   		objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDuplicateRecordException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objSpclPatVisitSUP_p.setErrorMessage("Not Eligible For OPD");
			//status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
	   	catch(HisNotAnOPDcaseException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objSpclPatVisitSUP_p.setErrorMessage("Not Eligible For OPD");
			//status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objSpclPatVisitSUP_p.setErrorMessage("Not Eligible For IPD");
			//status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			//e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage("Not applicable, Patient is Dead");
			if(objSpclPatVisitSUP_p.getErrorMessage()==null || objSpclPatVisitSUP_p.getErrorMessage().equals("")){
			}else{
				objSpclPatVisitSUP_p.setAfterGo("0");
			}
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(HisException e){
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
			if(objSpclPatVisitSUP_p.getErrorMessage()==null || objSpclPatVisitSUP_p.getErrorMessage().equals("")){
			}else{
				objSpclPatVisitSUP_p.setAfterGo("0");
			}
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	
	
	/**
	 * sets Patient Details By Name
	 * @param objSpclPatVisitSUP_p SpclPatientVisitSUP
	 * @param objRequest_p HttpServletRequest
	 */
	
	
	/*public static void setPatientDtlByName( SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p){
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO[] objPatientVO=new PatientVO[]{};
		String searchName=objSpclPatVisitSUP_p.getSearchName();
		
		objPatientVO=SpclPatientVisitDATA.getPatientDtlByName(searchName,objUserVO);
		
		HttpSession objSession=objRequest_p.getSession();
		objSession.setAttribute(RegistrationConfig.PATIENT_VO,objPatientVO);
	}*/
	/**
	 * sets all initial Old Patient Visit Essentials
	 * calls getAllInitialOldDeptVisitEssentials of  SpclPatientVisitDATA
	 * 
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setAllInitialOldPatientVisitEssentials(HttpServletRequest objRequest_p){
		Status status = new Status();
		try{
			//setSysdate(objRequest_p);
			Map mp=SpclPatientVisitDATA.getAllInitialOldDeptVisitEssentials();
			WebUTIL.setMapInSession(mp, objRequest_p,"SpclPatientVisitACTION");
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
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	/**
	 * sets the intial essentials 
	 * for the NEW mode
	 * calls getAllInitialNewDeptVisitEssentials() of SpclPatientVisitDATA
	 * puts the intial Essentails obtained in session
	 * @param objRequest_p -HttpServletRequest
	 */
		public static void setAllInitialNewDeptVisitEssentials(HttpServletRequest objRequest_p,SpclPatientVisitSUP objSpclPatVisitSUP_p){
			Status  objStatus=new Status();
			// objStatus.add(Status.NEW, "", "");
			try{
			Map mp=SpclPatientVisitDATA.getAllInitialNewDeptVisitEssentials();
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mp, objRequest_p,"SpclPatientVisitACTION");
			}
			catch(HisRegistrationTimingExpiredException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
				e.printStackTrace();
			}
			catch(HisRecordNotFoundException e){
				objSpclPatVisitSUP_p.setErrorMessage("Record Not Found Error");
				//objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");	
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
				WebUTIL.setStatus(objRequest_p, objStatus);
			}	
			
		}
	/**
	 * sets old department visit essentials (SpclPatientVisitSUP objSpclPatVisitSUP_p, HttpServletRequest objRequest_p)
	 * getOldDeptVisitEssentials(objSpclPatVisitSUP_p, getUserVO(objRequest_p)) from SpclPatientVisitDATA
	 * @param objSpclPatVisitSUP_p SpclPatientVisitSUP
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setOldDeptVisitEssentials(SpclPatientVisitSUP objSpclPatVisitSUP_p, HttpServletRequest objRequest_p){
		
		Status status = new Status();
		try{
			Map mp=SpclPatientVisitDATA.getOldDeptVisitEssentials(objSpclPatVisitSUP_p, getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mp, objRequest_p,"SpclPatientVisitACTION");
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
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	/**
	 * sets Episode Details( SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p )
	 * calls setPatientStatus( objRequest_p,objPatientVO) of OldSplPatientVisitUTIL
	 * @param objSpclPatVisitSUP_p SpclPatientVisitSUP 
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setEpisodeDetails( SpclPatientVisitSUP objSpclPatVisitSUP_p,String strPatCatCode_p,HttpServletRequest objRequest_p ){		
	Status status = new Status();
		try{
			System.out.println("SpclPatientVisitUTL :: setEpisodeDetails()");
			UserVO objUserVO =getUserVO(objRequest_p);		
			HttpSession objSession=WebUTIL.getSession(objRequest_p);
			PatientVO objPatientVO=(PatientVO)objSession.getAttribute(RegistrationConfig.PATIENT_VO);
			objPatientVO.setPatCrNo(objSpclPatVisitSUP_p.getPatCrNo());
		    EpisodeVO[] objArrEpisodeVO ;
		
			if(objPatientVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_IS_DEAD)==false){
				objArrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodes(objSpclPatVisitSUP_p.getPatCrNo(), strPatCatCode_p,RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL, objPatientVO.getRenewalConfig().getStrRenewalType(), objUserVO);
				System.out.println("objArrEpisodeVO.length :"+objArrEpisodeVO.length);
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
			}
			
			
		}catch(HisRecordNotFoundException e){
	   		e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		throw new HisRecordNotFoundException("");
		}
	   	catch(HisApplicationExecutionException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
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
	

	
	
	public static RegistrationSlip preapareSlip(EpisodeVO objArrEpisodeVO[], PatientVO objPatientVO_p, SpclPatientVisitSUP objSpclPatVisitSUP_p, HttpServletRequest objRequest_p){		
		
		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(objPatientVO_p);
		HelperMethods.populate(regSlip,objPatientVO_p);
		regSlip.setPatAge(objPatientVO_p.getPatAge()+" "+objPatientVO_p.getPatAgeUnit());
		regSlip.setMlcDetail(objPatientVO_p.getMlcDetail().split("\\(")[0]);//  added by warish purpus to get mlc number
		HospitalMstVO hospitalVO=getHospitalVO(objRequest_p);
		regSlip.setHospitalName(hospitalVO.getHospitalName());

		String primaryCatName=objPatientVO_p.getPatPrimaryCat();
		regSlip.setPatPrimaryCat(primaryCatName);
		UserVO objUserVO=getUserVO(objRequest_p);
		
		/***/
		RegistrationConfigurationBean objRegConfigbean  = (RegistrationConfigurationBean)WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.Registration_Configuration_Bean);
		regSlip.setIsQRCodePrintFlag(objRegConfigbean.getIsQRCodePrint());
		/***/
		int episodeVOLength=objArrEpisodeVO.length;
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
		String filePrintFlag[]=new String[objArrEpisodeVO.length];
		String cardPrintFlag[]=new String[objArrEpisodeVO.length];
		String departmentCode[]=new String[objArrEpisodeVO.length];
		String referFromDepartment[]=new String[objArrEpisodeVO.length];
		String printType[]=new String[objArrEpisodeVO.length];
		String expDate[]=new String[objArrEpisodeVO.length];
		String billNo[]=new String[objArrEpisodeVO.length];//By Mukund on 18.07.2017
		
		String patMiddleName=regSlip.getPatMiddleName();

		//By Mukund on 21.04.2017 
		/*if(!(patMiddleName==null || patMiddleName.equals(""))){
					patMiddleName=patMiddleName.substring(0,1).toUpperCase();
					regSlip.setPatMiddleName(patMiddleName);
					}*/
		
		String cityLocation=objPatientVO_p.getPatAddCity();
		
		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		
		
		//address for card
		String district="";
		String address=(objPatientVO_p.getPatAddHNo()==null?" ":objPatientVO_p.getPatAddHNo());
		address=address+(objPatientVO_p.getPatAddStreet()==null?" ":objPatientVO_p.getPatAddStreet());
		
		address=address+" "+cityLocation;
		//address=address+" " +(objPatientVO_p.getPatAddCity()==null?" ":objPatientVO_p.getPatAddCity());
		String address2="";	
		if(objPatientVO_p.getPatAddDistrict()!=null && !objPatientVO_p.getPatAddDistrict().equals("-1"))
			address2=objPatientVO_p.getPatAddDistrict();
		else
			address2=district;
		
		if(!address2.equals(""))
			address2+=","+(objPatientVO_p.getPatAddState()==null?" ":(" "+objPatientVO_p.getPatAddState()));
		else
			address2=objPatientVO_p.getPatAddState()==null?" ":" "+objPatientVO_p.getPatAddState();

		
		//address2=address2+" "+objPatientVO_p.getPatAddState();
		address2=address2+" "+objPatientVO_p.getPatAddCountry();
		if(!objPatientVO_p.getPatAddPIN().equals("0"))
		address2=address2+" "+(objPatientVO_p.getPatAddPIN()==null?" ":objPatientVO_p.getPatAddPIN());
		if(objPatientVO_p.getPatAddMobileNo() != null && !objPatientVO_p.getPatAddMobileNo().equals(""))
			address2=address2+" Mobile:"+objPatientVO_p.getPatAddMobileNo();
		
		for(int i=0;i<episodeVOLength;i++){
			if(objArrEpisodeVO[i].getFileNo()==null)
			{
				objArrEpisodeVO[i].setFileNo("");
				objArrEpisodeVO[i].setFileNoView("");
			}
			fileNo[i]=objArrEpisodeVO[i].getFileNo();
			fileNoView[i]=objArrEpisodeVO[i].getFileNoView();
			roomName[i]=objArrEpisodeVO[i].getRoom();
			depName[i]=objArrEpisodeVO[i].getDepartment();			
			depUnit[i]=objArrEpisodeVO[i].getDepartmentUnit();
			queueNo[i]=objArrEpisodeVO[i].getQueNo();
			unitConsultant[i]=objArrEpisodeVO[i].getUnitConsultant();
			filePrintFlag[i]=objArrEpisodeVO[i].getFilePrintSetting();
			cardPrintFlag[i]=objArrEpisodeVO[i].getCardPrintSetting();
			departmentCode[i]=objArrEpisodeVO[i].getDepartmentCode();
			referFromDepartment[i]=objArrEpisodeVO[i].getReferFromDepartment();
			expDate[i]=objArrEpisodeVO[i].getExpiryDate();
			billNo[i]=objArrEpisodeVO[i].getBillNo();//By Mukund on 18.05.2017
			
			if(objArrEpisodeVO[i].getPatAmountCollected()!=null && !(objArrEpisodeVO[i].getPatAmountCollected().equals(""))
					&& !(objArrEpisodeVO[i].getPatAmountCollected().equals("0")))
			{
				printType[i]="R";//renewal
			}else
			{
				printType[i]="C";//continuation
			}
			
			workingDays[i]=objArrEpisodeVO[i].getUnitWorkingDays();
			if(workingDays[i].indexOf("#")>0)
				workingDays[i]=workingDays[i].split("#")[0];
			
				if(objArrEpisodeVO[i].getDisclaimer()!="" && objArrEpisodeVO[i].getDisclaimer()!=null){
					disclaimer1[i]=objArrEpisodeVO[i].getDisclaimer().split("@")[0];
					disclaimer2[i]=objArrEpisodeVO[i].getDisclaimer().split("@")[1];
					disclaimer3[i]=objArrEpisodeVO[i].getDisclaimer().split("@")[2];
					isHeader[i]=objArrEpisodeVO[i].getDisclaimer().split("@")[3];
					alignment[i]=objArrEpisodeVO[i].getDisclaimer().split("@")[4];
				}
				else{
					disclaimer1[i]="";
					disclaimer2[i]="";
					disclaimer3[i]="";
					isHeader[i]="";
					alignment[i]="";
				}
			
			episodeDate[i]=(String)objRequest_p.getSession().getAttribute(Config.SYSDATE);
			regSlip.setEpisodeVisitType(objArrEpisodeVO[i].getEpisodeVisitType());
		}
		regSlip.setEpisodeDate(episodeDate);
		regSlip.setHostName(objRequest_p.getRemoteAddr());
		regSlip.setPatCrNo(objArrEpisodeVO[0].getPatCrNo());
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
		regSlip.setPatMonthlyIncome(objPatientVO_p.getPatMonthlyIncome()==null?" ":objPatientVO_p.getPatMonthlyIncome());
		regSlip.setUnitConsultant(unitConsultant);
		regSlip.setFilePrintFlag(filePrintFlag);
		regSlip.setCardPrintFlag(cardPrintFlag);
		regSlip.setDeptCode(departmentCode);
		regSlip.setReferFromDepartment(referFromDepartment);
		regSlip.setPrintType(printType);
		regSlip.setExpDate(expDate);
		regSlip.setPatAmountCollected(objArrEpisodeVO[0].getPatAmountCollected());
		if(regSlip.getPatAmountCollected()==null) 
			regSlip.setPatAmountCollected("0");
		else if(regSlip.getPatAmountCollected().equals(""))
				regSlip.setPatAmountCollected("0");
		
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		regSlip.setHospitalAddress1(hospitalVO.getAddress1());
		regSlip.setHospitalAddress2(hospitalVO.getAddress2());
		regSlip.setHospitalCity(hospitalVO.getCity());
		regSlip.setHospitalState(hospitalVO.getStateName());
		regSlip.setHospitalpinCode(hospitalVO.getPinCode());
		regSlip.setHospitalPhone(hospitalVO.getPhone());
		regSlip.setHospitalFax(hospitalVO.getFax());
		regSlip.setHospitalEmail(hospitalVO.getEmail());
		regSlip.setHospitalDistrict(hospitalVO.getDistrictName());
		
		if(objPatientVO_p.getPatPrimaryCatGrp().equals("0"))
		{
			regSlip.setPatPrimaryCatGrp("Payment"); 
		   regSlip.setPatPrimaryCatGrpCode("0"); 
		}
		else if(objPatientVO_p.getPatPrimaryCatGrp().equals("1"))
		{
			regSlip.setPatPrimaryCatGrp("Staff"); 
		regSlip.setPatPrimaryCatGrpCode("1");
		}
		else if(objPatientVO_p.getPatPrimaryCatGrp().equals("2"))
		{
		regSlip.setPatPrimaryCatGrp("Free"); 
		regSlip.setPatPrimaryCatGrpCode("2");
		}
		else if (objPatientVO_p.getPatPrimaryCatGrp().equals("3"))
		{
		regSlip.setPatPrimaryCatGrp("Credit/Beneficery"); 
		regSlip.setPatPrimaryCatGrpCode("3");
		}
		else if (objPatientVO_p.getPatPrimaryCatGrp().equals("4"))
		{
		regSlip.setPatPrimaryCatGrp("Credit/Beneficery Without Reference"); 
		regSlip.setPatPrimaryCatGrpCode("4");
		}
		regSlip.setLoginUserName(objUserVO.getUsrName());//By Mukund on 15.05.2017		
		return regSlip;	
	}	
	
	/**
	 * saves old Patient Visit Details (HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p)
	 * calls saveOldPatientVisit(objPatientVO, objArrEpisodeVO, getUserVO(objRequest_p)) of SpclPatientVisitDATA
	 * @param objRequest_p HttpServletRequest
	 * @param objSpclPatVisitSUP_p SpclPatientVisitSUP form bean
	 */
	public static boolean saveOldPatientVisit(HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p,String strMode_p){
		boolean renewStatus=false;
		Status objStatus = new Status();
		 //StringBuilder str=new StringBuilder();
		UserVO objUserVO=getUserVO(objRequest_p);
		
		EpisodeVO[] selectedEpisodeVO=null;
		String strPrintDivContent="",strBillPrintDiv="";
		try
		{
			System.out.println("SpclPatientVisitUTL :: saveOldPatientVisit()");
			getHospitalVO(objRequest_p);
			
			//objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			
			int k=0;		
			HttpSession session=WebUTIL.getSession(objRequest_p);			
			PatientVO objPatientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);	
			if(objPatientVO.getIsUnknown()!=null && objPatientVO.getIsUnknown().equalsIgnoreCase(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			{
				///removing (unknown) from unknown patient name
				objPatientVO.setPatFirstName(objPatientVO.getPatFirstName().substring(9));
			}
			EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR);		 
			String[] deptToVisit=null;
			
			if(objSpclPatVisitSUP_p.getIsReferred()!=null&&objSpclPatVisitSUP_p.getIsReferred().equalsIgnoreCase("on")){
				objSpclPatVisitSUP_p.setIsReferred("1");
			}
			
			//String isVisitOnRequest=objSpclPatVisitSUP_p.getOnRequestVisit();
			objPatientVO.setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
			if(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp()!=null && objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length>0)
				deptToVisit=objSpclPatVisitSUP_p.getDepartmentsToVisitStamp();
			else
				deptToVisit=new String[]{objSpclPatVisitSUP_p.getHcode()};
			
			//To Populate the Appointment Details
			if(objSpclPatVisitSUP_p.getPatAptNo()!=null&&objSpclPatVisitSUP_p.getPatAptNo()!="")
			{
				objPatientVO.setPatAptNo(objSpclPatVisitSUP_p.getPatAptNo());
				objPatientVO.setPatAptSlot(objSpclPatVisitSUP_p.getPatAptSlot());
				objPatientVO.setPatAptQueueNO(objSpclPatVisitSUP_p.getPatAptQueueNO());
				objPatientVO.setIsAppointment("1");
			}
			
			
			int x=deptToVisit.length;
			int y=arrEpisodeVOSes.length;
								
			EpisodeVO[] objArrEpisodeVO1 = new EpisodeVO[x];
			for(int i=0; i<y; i++)
			{
				for(int j=0;j<x;j++){
					
					if(arrEpisodeVOSes[i].getEpisodeCode().equals(deptToVisit[j])){
						
						arrEpisodeVOSes[i].setPatVisitReason(objSpclPatVisitSUP_p.getArrPatVisitReason()==null?"":objSpclPatVisitSUP_p.getArrPatVisitReason()[i]);
						arrEpisodeVOSes[i].setCreditBillFlag(objSpclPatVisitSUP_p.getArrCreditBillFlag()==null?"":objSpclPatVisitSUP_p.getArrCreditBillFlag()[i]);
						if(objSpclPatVisitSUP_p.getArrCreditBillFlag()!=null&&"1".equals(objSpclPatVisitSUP_p.getArrCreditBillFlag()[i])){
							arrEpisodeVOSes[i].setCreditLetterRefNo(objSpclPatVisitSUP_p.getArrCreditLetterRefNo()==null?"":objSpclPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
							arrEpisodeVOSes[i].setCreditLetterDate(objSpclPatVisitSUP_p.getArrCreditLetterDate()==null?"":objSpclPatVisitSUP_p.getArrCreditLetterDate()[i]);
						}
						objArrEpisodeVO1[k]=arrEpisodeVOSes[i];
						//this flag is used to check if visit is forceful
						objArrEpisodeVO1[k].setOnRequestVisit(objSpclPatVisitSUP_p.getOnRequestVisit());
						k++;
					} 
				 }
			}
			//forward visit (onRequestVisit/hiddenEpisode-->visit stamp on objRequest_p)
			//renewal visit (selected objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length)
			//Modified to insert the Amount only for renewal on the whole(hospital wise) by Singaravelan on 07-10-13
			String strRenewalTypeHospOrEpisode="";
			if(objSpclPatVisitSUP_p.getStrRenewalType()!=null && 
					(objSpclPatVisitSUP_p.getStrRenewalType().equals("3") || objSpclPatVisitSUP_p.getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			if(strMode_p.equals("SAVE"))
			{
				selectedEpisodeVO=new EpisodeVO[objArrEpisodeVO1.length];
				System.out.println("objSpclPatVisitSUP_p.getPatAmountHospitalWise() : "+objSpclPatVisitSUP_p.getPatAmountHospitalWise());
				for(int i=0;i<objArrEpisodeVO1.length;i++)
				{
					selectedEpisodeVO[i]=objArrEpisodeVO1[i];
					
					selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
					selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
					
					if(strRenewalTypeHospOrEpisode.equals("E")){
						if(objArrEpisodeVO1[i].getRenewalRequired().equalsIgnoreCase("1")){
							selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatRenewalAmountDeptWise());
							selectedEpisodeVO[i].setPatActualAmount(objSpclPatVisitSUP_p.getPatActualAmount());//added by mukund on 03.08.2016
							
							objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
							selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
						}else{
							objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							//added by Mukund on 05.08.2016
							selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountCollected());
							selectedEpisodeVO[i].setPatActualAmount(objSpclPatVisitSUP_p.getPatActualAmount());
						}
					}else if(strRenewalTypeHospOrEpisode.equals("H")){
						 if(objPatientVO.getSplRenewalRequired()!=null && objPatientVO.getSplRenewalRequired().equals("1")){
							 if(i==0){
								 objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
								 selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
								 selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountHospitalWise());
							 }
							 else{
								 objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
								 selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
								 selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
							 }
						 }
					 }
					else{
						selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
					}
					System.out.println("selectedEpisodeVO[i].getPatAmountHospitalWise() : "+selectedEpisodeVO[i].getPatAmountCollected());
				}
					
			}
			else
			{
				if(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp()!=null )
				{
					selectedEpisodeVO= new EpisodeVO[objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length];
					//System.out.println(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length);
					//selectedEpisodeVO= new EpisodeVO[objSpclPatVisitSUP_p.getDepartmentsToRenew().length];
					for(int i=0;i<objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
					{
						if(arrEpisodeVOSes!=null)
						{
							for(int j=0;j<arrEpisodeVOSes.length;j++)
							{
								if(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrEpisodeVOSes[j].getEpisodeCode()))
								{
									selectedEpisodeVO[i]=new EpisodeVO();
									HelperMethods.populate(selectedEpisodeVO[i],arrEpisodeVOSes[j]);
									
									selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
									selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
									
									if(strRenewalTypeHospOrEpisode.equals("E")){
										if(objArrEpisodeVO1[i].getRenewalRequired().equalsIgnoreCase("1")){
											selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatRenewalAmountDeptWise());
											objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
											selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
										}else{
											objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
											selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
										}
									}else if(strRenewalTypeHospOrEpisode.equals("H")){
										 if(objPatientVO.getSplRenewalRequired()!=null && objPatientVO.getSplRenewalRequired().equals("1")){
											 if(i==0){
												 objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
												 selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
												 selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountHospitalWise());
											 }
											 else{
												 objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
												 selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
												 selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
											 }
										 }
									 }
									else{
										selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
									}
									
									selectedEpisodeVO[i].setEpisodeCode(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp()[i]);
									selectedEpisodeVO[i].setSystemIPAddress(objPatientVO.getSystemIPAddress());
								}
							}
						}
					}
				}
			}
			System.out.println("renewal vo len "+selectedEpisodeVO!=null?selectedEpisodeVO.length:0);
			
						
			HttpSession objSession=WebUTIL.getSession(objRequest_p);

			EpisodeRefDtlVO episodeRefVO=new EpisodeRefDtlVO();
			if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				EpisodeRefDtlVO[] arrEpisodeOldPatRefVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_OLD_SPECIAL_PAT_REFER_VO);
				int indexID=Integer.parseInt(objSpclPatVisitSUP_p.getIndexID());
				
				episodeRefVO.setPatCrNo(arrEpisodeOldPatRefVO[indexID].getPatCrNo());
				episodeRefVO.setEpisodeCode(arrEpisodeOldPatRefVO[indexID].getEpisodeCode());
				episodeRefVO.setEpisodeVisitNo(arrEpisodeOldPatRefVO[indexID].getEpisodeVisitNo());
				episodeRefVO.setSerialNo(arrEpisodeOldPatRefVO[indexID].getSerialNo());
				episodeRefVO.setMlcNo(arrEpisodeOldPatRefVO[indexID].getMlcNo());
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				objSpclPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				objPatientVO.setIsPatReferByList(objSpclPatVisitSUP_p.getIsPatReferByList());
				objPatientVO.setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
				
			}
			else
			{
				if(objSpclPatVisitSUP_p.getIsReferred()!=null && objSpclPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
				
					episodeRefVO.setIsRefferInOut(objSpclPatVisitSUP_p.getIsRefferInOut());
		
					if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null && objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
						objSpclPatVisitSUP_p.setPatRefGnctdHospitalDept(objSpclPatVisitSUP_p.getPatRefHospitalDeptOther());
					}
		
					episodeRefVO.setExternalHospitalDepartment(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept());
					episodeRefVO.setExternalHospitalDepartmentUnit(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
					episodeRefVO.setExternalHospitalDoctorName(objSpclPatVisitSUP_p.getPatRefDoctor());
					episodeRefVO.setExternalHospitalName(objSpclPatVisitSUP_p.getPatRefHospitalName());
					episodeRefVO.setExternalHospitalPatCrNo(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCrno());
					episodeRefVO.setEpisodeCode(objSpclPatVisitSUP_p.getRefferringOPDEpisode());
				
					if(objSpclPatVisitSUP_p.getIsAssociated()!=null && objSpclPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE)) 
						{
						if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode().contains("#"))
							episodeRefVO.setExternalHospitalCode(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode().split("#")[0]);
						else
						episodeRefVO.setExternalHospitalCode(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode());
						}
					else if(objSpclPatVisitSUP_p.getIsAssociated()!=null && objSpclPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
						episodeRefVO.setExternalHospitalCode("");
					
					if(arrOpenOPDEpisodeVO!=null)
					{
						for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
							if((objSpclPatVisitSUP_p.getIsRefferInOut()!=null && objSpclPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL) )
									|| (objSpclPatVisitSUP_p.getIsRefferInOut()!=null && objSpclPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))){
								if(episodeRefVO.getEpisodeCode().equals(arrOpenOPDEpisodeVO[i].getEpisodeCode()))
								{
									episodeRefVO.setFromDepartmentCode(arrOpenOPDEpisodeVO[i].getFromDepartmentCode());
									episodeRefVO.setFromDepartmentUnitCode(arrOpenOPDEpisodeVO[i].getFromDepartmentUnitCode());
									episodeRefVO.setEpisodeVisitNo(arrOpenOPDEpisodeVO[i].getEpisodeVisitNo());
									episodeRefVO.setMlcNo(arrOpenOPDEpisodeVO[i].getMlcNo());
								}
							}
					}
				}
			}
			if((objSpclPatVisitSUP_p.getIsReferred()!=null && objSpclPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)) 
					&& !(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)) 
					&& (objSpclPatVisitSUP_p.getIsRefferInOut()!=null && !(objSpclPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)))){
				
				if(episodeRefVO.getFromDepartmentUnitCode().equals(objArrEpisodeVO1[0].getDepartmentUnitCode())){
					//objStatus.add(Status.TRANSINPROCESS,"Visiting Unit And Reffered Unit Cannot be Same","");
					renewStatus=true;
					objSpclPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
					throw new HisInsertNotAllowedException("Visiting Unit And Reffered Unit Cannot be Same");
				}
				
			}
			/*  ## 		Modification Log							
	 		##		Modify Date				:06thFeb'15 
	 		##		Reason	(CR/PRS)		://To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
	 		##		Modify By				:Sheeldarshi */
			//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
					String strUniqueIdDuplicyFlag = "0";
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			objCreditAvailDtlVO.setCreditLetterRefNo(objSpclPatVisitSUP_p.getCreditLetterRefNo());
			objCreditAvailDtlVO.setCreditLetterDate(objSpclPatVisitSUP_p.getCreditLetterDate());
			objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
			if(objSpclPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatientVO.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
				objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);
		
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
						objSpclPatVisitSUP_p.setErrorMessage(strErrMsg);
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						//return false;
					}
				}
				//By Mukund on 16.11.2016
				objPatientVO.setStaffCardNo(objSpclPatVisitSUP_p.getStaffCardNo()); 
				objPatientVO.setStaffCardName(objSpclPatVisitSUP_p.getStaffCardName());
				objPatientVO.setClientCode(objSpclPatVisitSUP_p.getClientCode());
				objPatientVO.setClientName(objSpclPatVisitSUP_p.getClientName());
				objPatientVO.setLetterType(objSpclPatVisitSUP_p.getLetterType());
				objPatientVO.setCreditLetterRefNo(objSpclPatVisitSUP_p.getCreditLetterRefNo());
				objPatientVO.setCreditLetterDate(objSpclPatVisitSUP_p.getCreditLetterDate());
				objPatientVO.setCreditLimit(objSpclPatVisitSUP_p.getCreditLimit());
				objPatientVO.setCardvalidityDate(objSpclPatVisitSUP_p.getCardvalidityDate());
				//End Mukund
			}
			//End:sheeldarshi	
						
			//////////////////////////////////////////////////////////
			EpisodeVO[] objArrEpisodeVO2=null;
			objArrEpisodeVO2=SpclPatientVisitDATA.saveSpclOldPatientVisit(objPatientVO, selectedEpisodeVO, objUserVO,episodeRefVO);
			
			objRequest_p.setAttribute(RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO2);
			objSpclPatVisitSUP_p.setSaveSuccessful("true");
			int tempEpiLen=objArrEpisodeVO2.length;
			String[] strQueueNo=new String[tempEpiLen];
			String[] strDeptName=new String[tempEpiLen];
			String[] strUnitName=new String[tempEpiLen];
			String[] strRoomName=new String[tempEpiLen];
			 
			for(int i=0;i<tempEpiLen;i++)
			{
				 strQueueNo[i]=objArrEpisodeVO2[i].getQueNo();
				 strDeptName[i]=objArrEpisodeVO2[i].getDepartment();
				 strUnitName[i]=objArrEpisodeVO2[i].getDepartmentUnit();
				 strRoomName[i]=objArrEpisodeVO2[i].getRoom();
			}
			 /////////for displaying the name ////////////
			if(objPatientVO.getPatMiddleName()==null)
				objPatientVO.setPatMiddleName("");
			if(objPatientVO.getPatLastName()==null)
				objPatientVO.setPatLastName("");
			
			System.out.println("------------To Print------------"+objSpclPatVisitSUP_p.getIsPrintCard()+"-------------------------");
			String tmpFileName=RegistrationConfig.CARD_OLD_DEPT_VISIT+objUserVO.getSeatId();
				
			//String str="";
			//String isPrintCard="1";
			//code for DOT Matrix Printing
			//if(isPrintCard.equals("1"))
			objSpclPatVisitSUP_p.setStrNormalMsg(objSpclPatVisitSUP_p.getStrNormalMsg()+ "\nOld Patient Visit Stamped Successfully");
			RegistrationSlip regSlip=preapareSlip(objArrEpisodeVO2,objPatientVO,objSpclPatVisitSUP_p,objRequest_p);
			strPrintDivContent=NewRegistrationSlip.print(regSlip, tmpFileName, objRequest_p, "RP");
			objSpclPatVisitSUP_p.setPrint("1");
			//objSpclPatVisitSUP_p.setPrintDivContent(strPrintDivContent);
			objSpclPatVisitSUP_p.setPrintDivContent(" ");
			
			System.out.println("strPrintDivContent (Old) :"+strPrintDivContent);
			
			//Bill Receipt Printing 
			if(objArrEpisodeVO2!=null){
				if((objArrEpisodeVO2[0].getPatAmountCollected()!=null)&&!(objArrEpisodeVO2[0].getPatAmountCollected().equals("0"))&&!(objArrEpisodeVO2[0].getPatAmountCollected().equalsIgnoreCase("0.00")))
				{
					strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(objArrEpisodeVO2,objPatientVO,objSpclPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"ODV");
					System.out.println("PrintBillSlip :"+strBillPrintDiv);
					WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
					strPrintDivContent=""+strBillPrintDiv+"";
					objSpclPatVisitSUP_p.setPrintDivContent(strPrintDivContent);
				}
			}
			
			System.out.println("Full PrintDivContent (New) :"+objSpclPatVisitSUP_p.getPrintDivContent());
			
			if(objSpclPatVisitSUP_p.getIsPrintStatus().equals("1")){
				//objStatus.add(Status.DONE," ","Patient Visited Successfully");
				//objStatus.add(Status.DONE,strPrintDivContent.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divSplPatientVisitedLabel'>"+"Old Department Visit : "+"</div></font>");
				//objStatus.add(Status.DONE,strPrintDivContent.toString(),"");
				//By Mukund on 16.11.2016 to print the bill reciept for oldDeptVisit
				objStatus.add(Status.DONE,"","<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divSplPatientVisitedLabel'>"+"Patient Visited Sucessfully "+"</div></font><font color='#000000'>"+objSpclPatVisitSUP_p.getPrintDivContent().toString()+"</font>");

			}	
			else if(objSpclPatVisitSUP_p.getIsPrintStatus().equals("2")){
				//objStatus.add(Status.DONE," ","Patient Visited Successfully");
				//objStatus.add(Status.DONE,strPrintDivContent.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divSplPatientVisitedLabel'>"+"Old Department Visit : "+"</div></font>");
				//objStatus.add(Status.DONE,strPrintDivContent.toString(),"");
				//By Mukund on 16.11.2016 to print the bill reciept for oldDeptVisit
				objStatus.add(Status.DONE,"","<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divSplPatientVisitedLabel'>"+"Patient Visited Sucessfully "+"</div></font><font color='#000000'></font>");

			}	
			
		
		}
		catch(HisInsertNotAllowedException e){
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
			e.printStackTrace();
			
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			//objStatus.add(Status.RECORDFOUND,e.getMessage() ,"");
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());		
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}finally{
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
		return renewStatus;
	}

	
	
	
	
	
	public static void getOpenEpisodeDtl(HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p){
		PatientVO objPatientVO=(PatientVO)WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.PATIENT_VO);
		UserVO objUserVO=getUserVO(objRequest_p);
		Status status=new Status();
		try{
			if(objSpclPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
			EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO,"3");
			for(int i=0;i<arrOPDOpenEpisodeVO.length;i++)
			{
				if(arrOPDOpenEpisodeVO[i].getFromDepartment()==null)
					arrOPDOpenEpisodeVO[i].setFromDepartment("");
				if(arrOPDOpenEpisodeVO[i].getFromDepartmentUnit()==null)
					arrOPDOpenEpisodeVO[i].setFromDepartmentUnit("");
				if(arrOPDOpenEpisodeVO[i].getFromDepartmentUnitCode()==null)
					arrOPDOpenEpisodeVO[i].setFromDepartmentUnitCode("");
			}
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
			objSpclPatVisitSUP_p.setIsReferred("1");
			objSpclPatVisitSUP_p.setIsRefferInOut("I");
			}
			else{
				objSpclPatVisitSUP_p.setIsReferred("0"); 
			}
			status.add(Status.TRANSINPROCESS);
			}
		catch(HisRecordNotFoundException e){
   		
	   		e.printStackTrace();
	   		objSpclPatVisitSUP_p.setErrorMessage("");
	   		status.add(Status.TRANSINPROCESS);
		}
		finally{
			WebUTIL.setStatus(objRequest_p,status);
		}
}
	
	
//	public static void getRegistrationAllowed(HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p){
//		
//		//	UserVO objUserVO=getUserVO(objRequest_p);
//		System.out.println("SplVisitUTL :: getRegistrationAllowed()");
//		Status status=new Status();
//		try{
//			isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(objRequest_p));
//			/*if(true)
//				throw new HisRegistrationTimingExpiredException("dsdsd");*/
//		}
//		catch (HisRegistrationTimingExpiredException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisRecordNotFoundException e){
//   		
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisDataAccessException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		finally{
//			WebUTIL.setStatus(objRequest_p,status);
//		}
//	}
	
	public static void getOldPatReferDtl(SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		try
		{
			System.out.println("SpclPatientVisitUTL :: getOldPatReferDtl()");
			EpisodeRefDtlVO[] arrEpisodeOldPatRefDtlVO=SpclPatientVisitDATA.getReferPat(objUserVO,"3");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_EPISODE_OLD_SPECIAL_PAT_REFER_VO ,arrEpisodeOldPatRefDtlVO);
			System.out.println("arrEpisodeOldPatRefDtlVO.length :"+arrEpisodeOldPatRefDtlVO.length);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			//e.printStackTrace();
			System.out.println(e.getMessage());
			//objStatus.add(Status.ERROR,"",e.getMessage());
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			objSpclPatVisitSUP_p.setErrorMessage("Record Not Found");
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
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}
	
	public static void getReferPatient(SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p,String strMode_p)
	{
		System.out.println("patientVisitUTL :: getReferPatient()");
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		EpisodeRefDtlVO[] arrEpisodeRefDtlVO=null;
		try
		{
			System.out.println("patientVisitUTL :: getReferPatient");
			arrEpisodeRefDtlVO=SpclPatientVisitDATA.getReferPat(objUserVO,strMode_p);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO,arrEpisodeRefDtlVO);
			objStatus.add(Status.LIST);
			System.out.println("arrEpisodeRefDtlVO.length :"+arrEpisodeRefDtlVO!=null?arrEpisodeRefDtlVO.length:0);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage("Record Not Found");
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");		
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
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}

	
	public static void printCard(SpclPatientVisitSUP objSplPatientVisitSUP_p,HttpServletRequest objRequest_p)
	{
		System.out.println("SpclPatientVisitUTL :: printCard()");
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		try
		{
			HttpSession session=objRequest_p.getSession();
			
			
			String tmpFileName=RegistrationConfig.CARD_NEW_DEPT_VISIT+objUserVO.getSeatId();			
			
			RegistrationSlip _rSlip=(RegistrationSlip)objRequest_p.getSession().getAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
			RegistrationSlip registrationSlip=null;
			if(_rSlip!=null){
				for(int i=0;i<_rSlip.getDepartmentToBeVisited().length;i++){
					registrationSlip=_rSlip;
					registrationSlip.setDepartmentToBeVisited(new String[]{_rSlip.getDepartmentToBeVisited()[i]});
					objRequest_p.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE,_rSlip.getDeptCode()[i]);
					if(_rSlip.getDeptCode()[i].equals(RegistrationConfig.RADIOTHERAPY_DEPT_CODE)){
						if(_rSlip.getReferFromDepartment()[i]==null || _rSlip.getReferFromDepartment()[i].equals("-1") 
								|| _rSlip.getReferFromDepartment()[i].equals(""))
						{
							_rSlip.setReferFromDepartment(new String[_rSlip.getDepartmentToBeVisited().length]);
						}
						objRequest_p.getSession().setAttribute(RegistrationConfig.REFER_BY, _rSlip.getReferFromDepartment()[i]);
					}
					NewRegistrationSlip.print(registrationSlip, tmpFileName, objRequest_p,"DV");
				}
			}	
			System.out.println("printed");
			objStatus.add(Status.DONE,"","Printed Sucessfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Error in Printing");
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}



	public static void setNewDepartmentVisitDtl(SpclPatientVisitSUP objSpclPatVisitSUP_p, HttpServletRequest objRequest_p) 
	{
		Status  objStatus=new Status();
		//String amountCollected="";
		//String amount="";
		//boolean flagDeptNotFound=true;
		try{
			System.out.println("SpclPatientVisitUTL :: setNewDepartmentVisitDtl()");
			UserVO objUserVO =getUserVO(objRequest_p);
			PatientVO objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
			HttpSession objSession=objRequest_p.getSession();
			EpisodeRefDtlVO[] arrEpisodeRefPatVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
			//System.out.println("-----------"+arrEpisodeRefPatVO+"-----------");
			//System.out.println("-----------"+objSpclPatVisitSUP_p.getOnlineReferedIndex()+"-----------");
			Map mp1=new HashMap();
			try{
				if(objSpclPatVisitSUP_p.getIsPatReferByList()!=null && objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)){
					int index=Integer.parseInt(objSpclPatVisitSUP_p.getOnlineReferedIndex());
					objSpclPatVisitSUP_p.setPatCrNo(arrEpisodeRefPatVO[index].getPatCrNo());
					mp1=SpclPatientVisitDATA.getAllNewDeptVisitEssentials(objSpclPatVisitSUP_p.getPatCrNo(),objUserVO);	    
				}
				else{
					mp1=setAllNewDeptVisitEssentials(objSpclPatVisitSUP_p,objRequest_p);
				}
				
			}catch(Exception e){
				//e.printStackTrace();
			}finally{
				WebUTIL.setMapInSession(mp1,objRequest_p,"SpclPatientVisitACTION");
			}
			setDeptOptions(objRequest_p, objSpclPatVisitSUP_p);
			
			objPatientVO.setPatCrNo(objSpclPatVisitSUP_p.getPatCrNo());
			objPatientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			objPatientVO.setIsPatReferByList(objSpclPatVisitSUP_p.getIsPatReferByList());
			
		
			
			System.out.println("IsPatReferByList :"+objSpclPatVisitSUP_p.getIsPatReferByList());
			if(objSpclPatVisitSUP_p.getIsPatReferByList()!=null && objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				String fromDepartmentCode="";
				int index=Integer.parseInt(objSpclPatVisitSUP_p.getOnlineReferedIndex());

				objSpclPatVisitSUP_p.setToDepartment(arrEpisodeRefPatVO[index].getToDepartment());
				objSpclPatVisitSUP_p.setToDepartmentCode(arrEpisodeRefPatVO[index].getToDepartmentCode());
				objSpclPatVisitSUP_p.setToDepartmentUnit(arrEpisodeRefPatVO[index].getToDepartmentUnit());
				objSpclPatVisitSUP_p.setToDepartmentUnitCode(arrEpisodeRefPatVO[index].getToDepartmentUnitCode());
				fromDepartmentCode=arrEpisodeRefPatVO[index].getFromDepartmentCode();
				String entryDate=arrEpisodeRefPatVO[index].getEntryDate();
				if(Integer.parseInt(arrEpisodeRefPatVO[index].getEpisodeVisitNo()) >1){
					entryDate="";
				}
			}
			
			//Setting Patient Amount
			
			HelperMethods.populatetToNullOrEmpty(objSpclPatVisitSUP_p,objPatientVO);
			//setPatAmount(objSpclPatVisitSUP_p, objPatientVO, objUserVO, objRequest_p);
			objSpclPatVisitSUP_p.setPatBillAmountWithoutGrouping(objSpclPatVisitSUP_p.getPatAmountCollected());
			objSession=WebUTIL.getSession(objRequest_p);
			objSession.setAttribute("objPatientVO",objPatientVO);
			objSession.setAttribute(RegistrationConfig.PATIENT_VO,objPatientVO);
		
			if(objSpclPatVisitSUP_p.getIsPatReferByList()!=null && !(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)))
			{
				try{
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO,"3");//procedure created
					WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				catch(HisRecordNotFoundException e){
					String msg=e.getMessage();
				}
			}
			objStatus.add(Status.TRANSINPROCESS,"","");
		
		
		}
		 catch(HisDeadPatientException e){
		    	objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
				//e.printStackTrace();
		    	System.out.println(e.getMessage());
		    }
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch(HisEpisodeOpenInEmergencyException e){ 
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisRecordNotFoundException e){
			System.out.println(e.getMessage());
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally{
			WebUTIL.setStatus(objRequest_p,objStatus);
		//	objRequest_p.setAttribute(RegistrationConfig.STATUS_OBJECT,objStatus);
			
		}
		
	}
	/**
	 * sets New DEpartment Visit Essentials
	 * calls getAllNewDeptVisitEssentials() from SpclPatientVisitDATA
	 * sets the Essential in session
	 * @param objSpclPatVisitSUP_p -NewDeptVisitFB form bean
	 * @param objRequest_p -HttpServletRequest
	 */
	public static Map setAllNewDeptVisitEssentials( SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p){
		
		Status objStatus = new Status();
		Map mp=null;
		try{
				
			System.out.println("SpclPatientVisitUTL :: setAllNewDeptVisitEssentials()");
		UserVO objUserVO =getUserVO(objRequest_p);		
	    mp=SpclPatientVisitDATA.getAllNewDeptVisitEssentials(objSpclPatVisitSUP_p.getPatCrNo(),objUserVO);	    
		
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
			//WebUTIL.setMapInSession(mp,objRequest_p);
		}
		return mp;
	}
	/**
	 * sets Department Options 
	 * retrieves departments to visit stamp from objSplPatientVisitSUP_p
	 * removes those departments from department combo options
	 * by calling removeEntriesfromOptions() from WebUTIL
	 * sets the remaining departments in 
	 * @param objRequest_p -HttpServletRequest
	 * @param objSpclPatVisitSUP_p -NewDeptVisitFB form bean
	 */
	public static void setDeptOptions(HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p){
		System.out.println("SpclPatientVisitUTL :: setDeptOptions()");
		String [] deptVisitArr=	objSpclPatVisitSUP_p.getDepartmentsToVisitStamp();
		//HttpSession objSession=objRequest_p.getSession();		
		Collection colOrgDept = (Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_SPL_NEW_DEPT_VISIT_DEPARTMENT);
		//Collection colOrgDept = (Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
		System.out.println("colOrgDept :"+colOrgDept);
		if(colOrgDept!=null){
			Collection newCol = new ArrayList(colOrgDept);
			//<<why returned collection need to be stored
			if(deptVisitArr!=null)
				newCol=WebUTIL.removeEntriesfromOptions(newCol, deptVisitArr);
			//System.out.println("-------------------"+newCol+"--------------------");
			//objSession.setAttribute(RegistrationConfig.SPLREGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.SPLREGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
			System.out.println("newCol :"+newCol);
			///for capturing department specific mandatory fiels
			//objSession.setAttribute(RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, newCol);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, newCol);
		}else{
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, null);
		}
		
	}

	public static void getRefDept_AJAX(HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) {
		System.out.println("SpclPatientVisitUTL :: getRefDept_AJAX()");
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strRefHospCode= (String)objRequest_p.getParameter("refHospCode");
			String strFlafRefHospOrInst = (String)objRequest_p.getParameter("flafRefHospOrInst");
			
			UserVO userVO=getUserVO(objRequest_p);
			
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			if(strFlafRefHospOrInst!=null && strFlafRefHospOrInst.equals("I"))
				strRefHospCode=userVO.getHospitalCode();
			
			List lstRefDept=SpclPatientVisitDATA.getRefDept_AJAX(userVO, strRefHospCode);
			
			createOptionObject((List<Entry>)lstRefDept,responseDocument,"patRefGnctdHospitalDept");
			
			//System.out.println("outputString "+outputString);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			System.out.println("outputString "+outputString);
			writeResponse(objResponse_p, outputString);
		}	
	}


	public static void setOldDepartmentVisitDtl(SpclPatientVisitSUP objSpclPatVisitSUP_p,
			HttpServletRequest objRequest_p) {
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
		try{
			
			System.out.println("SpclPatientVisitUTL:: setOldDepartmentVisitDtl()");
			HttpSession objSession=objRequest_p.getSession();
			EpisodeRefDtlVO[] arrEpisodeOldPatRefVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_OLD_SPECIAL_PAT_REFER_VO);
			System.out.println("IsPatReferByList :"+objSpclPatVisitSUP_p.getIsPatReferByList());
			if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				int	indexID=Integer.parseInt(objSpclPatVisitSUP_p.getIndexID());
				System.out.println("----------"+objSpclPatVisitSUP_p.getIndexID()+"-----------");
				System.out.println("--------- After Index print ------------");
				objSpclPatVisitSUP_p.setPatCrNo(arrEpisodeOldPatRefVO[indexID].getPatCrNo());
				EpisodeVO[] objArrEpisodeVO=null;
				if(arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode()==null ||arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode()=="")
				{
					objArrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByDept(objSpclPatVisitSUP_p.getPatCrNo(),arrEpisodeOldPatRefVO[indexID].getToDepartmentCode(),
														objPatientVO.getRenewalConfig().getStrRenewalType(),objUserVO, RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				}
				else
				{
					objArrEpisodeVO=SpclPatientVisitDATA.getOldPatientEpisodesByUnit(objSpclPatVisitSUP_p.getPatCrNo(),arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode(), 
														objPatientVO.getRenewalConfig().getStrRenewalType(),objUserVO, RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				}
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
				
			}
			
			if(!(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)))
			{
				setEpisodeDetails(objSpclPatVisitSUP_p,objPatientVO.getPatCatCode(),objRequest_p);
				Map referEssDept=SpclPatientVisitDATA.referedEssentialDepartment(objUserVO);  
				WebUTIL.setMapInSession(referEssDept,objRequest_p,"SpclPatientVisitACTION");
			}
			
			if(!(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))){
				try{
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=SpclPatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO, "3");//procedure created
					WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				catch(HisRecordNotFoundException e){
					String msg=e.getMessage();
				}
			}
			/*List refDeptList=SpclPatientVisitDATA.setRefDepartment(objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL ,refDeptList);*/
			status.add(Status.TRANSINPROCESS);
		
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();	   		
			objSpclPatVisitSUP_p.setErrorMessage("");
	   		//status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
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
			objSpclPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
		
	}
	
	
	
	/**
	 * saves the patient new department visit
	 * @param req -HttpServletRequest
	 * @param objSpclPatVisitSUP_p -NewDeptVisitFB form bean
	 */
	public static void savePatientNewDepartmentVisit(HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p){
		
		Status objStatus=new Status();
		String str="",strBillPrintDiv="";
		try{
			EpisodeRefDtlVO episodeRefVO=new EpisodeRefDtlVO();
			HttpSession objSession=objRequest_p.getSession();
			PatientVO objPatientVO =(PatientVO)objSession.getAttribute(RegistrationConfig.PATIENT_VO);
			//updating patient vo mandatory fields
			PatientVO oldPatientVO=null;
			System.out.println("SpclPatientVisitUTL :: savePatientNewDepartmentVisit()");
			System.out.println("----Dept unit Code---"+objSpclPatVisitSUP_p.getDepartmentUnitCode()+"-----------");
			System.out.println("----Dept To Visit---"+objSpclPatVisitSUP_p.getToDepartment()+"-----------");
			System.out.println("----Dept To Visit---"+objSpclPatVisitSUP_p.getToDepartmentCode()+"-----------");
			
			oldPatientVO=new PatientVO();
			HelperMethods.populate(oldPatientVO, objPatientVO);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objSpclPatVisitSUP_p);
			//added by Mukund on 04.08.2016
			objPatientVO.setClientCode(objSpclPatVisitSUP_p.getClientCode());
			objPatientVO.setClientName(objSpclPatVisitSUP_p.getClientName());
			//To set Credit Limit for Beneficiary Categories
			objSpclPatVisitSUP_p.setPrint("0");
			if(objSpclPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objSpclPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				objPatientVO.setCreditLimit(objSpclPatVisitSUP_p.getCreditLimit());
			}
			if(objSpclPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objSpclPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) )
			{
				objPatientVO.setCreditLimit(objSpclPatVisitSUP_p.getAgsCreditLimit());
				objPatientVO.setLetterType("-1");
			}
			UserVO objUserVO=getUserVO(objRequest_p);
			/*End:Mukund*/
			//gets the hospital information
			getHospitalVO(objRequest_p);
			
			String patPriCatLable=objPatientVO.getPatPrimaryCat();
			objUserVO.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			
			if(objSpclPatVisitSUP_p.getStrRenewalType()!=null &&  (objSpclPatVisitSUP_p.getStrRenewalType().equals("1") || objSpclPatVisitSUP_p.getStrRenewalType().equals("2"))){
				if(objSpclPatVisitSUP_p.getSplRenewalRequired()!=null && objSpclPatVisitSUP_p.getSplRenewalRequired().equals("1")){
					objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
				}else{
					objUserVO.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
				}
			}
			
			if(!objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE) && !objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)){
				objPatientVO.setDepartmentCode(objSpclPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				objPatientVO.setDepartmentUnitCode(objSpclPatVisitSUP_p.getDepartmentCode().split("#")[1]);
			}
			if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)){
				objPatientVO.setDepartmentCode(objSpclPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				objPatientVO.setDepartmentUnitCode(objSpclPatVisitSUP_p.getDepartmentCode().split("#")[1]);
			}
			
			if(objSpclPatVisitSUP_p.getIsReferred()!=null&&objSpclPatVisitSUP_p.getIsReferred().equalsIgnoreCase("on")){
				objSpclPatVisitSUP_p.setIsReferred("1");
			}
			
			System.out.println("objPatientVO.getDepartmentUnitCode() :"+objPatientVO.getDepartmentUnitCode()+"-----------");
			System.out.println("objPatientVO.getDepartmentCode() :"+objPatientVO.getDepartmentCode()+"-----------");
			
			//String patAmountCollected=SpclPatientVisitDATA.getBillAmount(objPatientVO.getPatPrimaryCatCode(),objUserVO);	
			String patAmountCollected=objSpclPatVisitSUP_p.getPatAmountCollected();
			//For validating the pat amount collected
			if(objSpclPatVisitSUP_p.getPatAmountCollected()==null){
				objSpclPatVisitSUP_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}
			else if(objSpclPatVisitSUP_p.getPatAmountCollected().equals("")){
				objSpclPatVisitSUP_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}
			
			String regCatCode=objPatientVO.getPatRegCatCode();
			String genderCode=objPatientVO.getPatGenderCode();
			String patCrNo=objPatientVO.getPatCrNo();
			//System.out.println("-----------"+objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept()+"-----------");
			if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null)
				if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0"))
					objSpclPatVisitSUP_p.setPatRefGnctdHospitalDept(objSpclPatVisitSUP_p.getPatRefHospitalDeptOther());
			
			//HelperMethods.populate(objPatientVO, objSpclPatVisitSUP_p);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objSpclPatVisitSUP_p);
			if(objPatientVO.getIsUnknown().equalsIgnoreCase(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			{
				///removing (unknown) from unknown patient name
				objPatientVO.setPatFirstName(objPatientVO.getPatFirstName().substring(9));
			}
			
			/*
			  // Commented, Because PatAmountCollected automatically get populated from objSpclPatVisitSUP_p above
			  if(objPatientVO.getRenewalConfig()!=null){
				if("1".equals(objPatientVO.getRenewalConfig().getStrRenewalType()) || "2".equals(objPatientVO.getRenewalConfig().getStrRenewalType())){
					objPatientVO.setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountHospitalWise());
				}else if("3".equals(objPatientVO.getRenewalConfig().getStrRenewalType()) || "4".equals(objPatientVO.getRenewalConfig().getStrRenewalType())){
					objPatientVO.setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountDeptWise());
				}else{
					objPatientVO.setPatAmountCollected("0");
				}
			}*/
			objPatientVO.setPatGenderCode(genderCode);
			objPatientVO.setPatCrNo(patCrNo);
			objPatientVO.setPatRegCatCode(regCatCode);
			objPatientVO.setPatSecondaryCatCode("");
			objPatientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD);
			
			Collection colDept		=(Collection)objSession.getAttribute(RegistrationConfig.SPLREGISTRATIONDESK_OPTION_DEPARTMENT);
			
			if(!objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE))
				setDepartmentDtl(colDept, objPatientVO, objSpclPatVisitSUP_p.getDepartmentCode());
			else{
				setDepartmentDtl_PreApt(objPatientVO,objSpclPatVisitSUP_p);
			}

			
							
			String[] deptToVisit = objSpclPatVisitSUP_p.getDepartmentsToVisitStamp();
			String[] arrFileNo=objSpclPatVisitSUP_p.getArrFileNo();
			EpisodeVO[] objArrEpisodeVO1 = null;
			if(deptToVisit!=null && deptToVisit.length>0 )
				objArrEpisodeVO1 = new EpisodeVO[deptToVisit.length];
			else
				objArrEpisodeVO1 = new EpisodeVO[1];
			
			if(!objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
				
				episodeRefVO.setIsRefferInOut(objSpclPatVisitSUP_p.getIsRefferInOut());
				if(objSpclPatVisitSUP_p.getIsRefferInOut()!=null && objSpclPatVisitSUP_p.getIsRefferInOut().equals("I")){
					episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				}else if(objSpclPatVisitSUP_p.getIsRefferInOut()!=null && objSpclPatVisitSUP_p.getIsRefferInOut().equals("E")){
					episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				
				episodeRefVO.setExternalHospitalDepartment(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept());
				episodeRefVO.setExternalHospitalDepartmentUnit(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
				episodeRefVO.setExternalHospitalDoctorName(objSpclPatVisitSUP_p.getPatRefDoctor());
				episodeRefVO.setExternalHospitalName(objSpclPatVisitSUP_p.getPatRefHospitalName());
				episodeRefVO.setExternalHospitalPatCrNo(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCrno());
				episodeRefVO.setEpisodeCode(objSpclPatVisitSUP_p.getRefferringOPDEpisode());
				
				if(objSpclPatVisitSUP_p.getIsAssociated()!=null && objSpclPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE))
					{
					if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode().contains("#"))
						episodeRefVO.setExternalHospitalCode(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode().split("#")[0]);
					else
					episodeRefVO.setExternalHospitalCode(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode());
					}
				else if(objSpclPatVisitSUP_p.getIsAssociated()!=null && objSpclPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
					episodeRefVO.setExternalHospitalCode("");
				else
					episodeRefVO.setExternalHospitalCode("");
				
				if(arrOpenOPDEpisodeVO!=null){
					for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
						if(episodeRefVO.getEpisodeCode()!=null && episodeRefVO.getEpisodeCode().equals(arrOpenOPDEpisodeVO[i].getEpisodeCode())){
							episodeRefVO.setFromDepartmentCode(arrOpenOPDEpisodeVO[i].getFromDepartmentCode());
							episodeRefVO.setFromDepartmentUnitCode(arrOpenOPDEpisodeVO[i].getFromDepartmentUnitCode());
							episodeRefVO.setEpisodeVisitNo(arrOpenOPDEpisodeVO[i].getEpisodeVisitNo());
							episodeRefVO.setMlcNo(arrOpenOPDEpisodeVO[i].getMlcNo());
							episodeRefVO.setFromDepartment(arrOpenOPDEpisodeVO[i].getFromDepartment());
						}
				}
			}
			else
			{
				EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
				int i=0;
				/*for(;i<episodeRefDtlVO.length;i++){
					if(episodeRefDtlVO[i].getPatCrNo().equals(objSpclPatVisitSUP_p.getPatCrNo())){
						break;
					}
				}*/
				int index=Integer.parseInt(objSpclPatVisitSUP_p.getOnlineReferedIndex());
				episodeRefVO.setEpisodeCode(episodeRefDtlVO[index].getEpisodeCode());
				episodeRefVO.setEpisodeVisitNo(episodeRefDtlVO[index].getEpisodeVisitNo());
				episodeRefVO.setSerialNo(episodeRefDtlVO[index].getSerialNo());
				episodeRefVO.setMlcNo(episodeRefDtlVO[index].getMlcNo());
				episodeRefVO.setFromDepartment(episodeRefDtlVO[index].getFromDepartment());
				objSpclPatVisitSUP_p.setDepartmentCode(objSpclPatVisitSUP_p.getToDepartmentCode());
				objPatientVO.setDepartmentCode(objSpclPatVisitSUP_p.getDepartmentCode());
				objSpclPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				objPatientVO.setIsPatReferByList(objSpclPatVisitSUP_p.getIsPatReferByList());
			}
			
			objPatientVO.setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
			Collection col=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.SPLREGISTRATIONDESK_OPTION_DEPARTMENT);
			String deptName="";
			
			if(deptToVisit==null || deptToVisit.length==0)
			{
				objArrEpisodeVO1 = new EpisodeVO[1];			
				objArrEpisodeVO1[0]=new EpisodeVO();
				
				//objArrEpisodeVO[0].setDepartmentCode(objSpclPatVisitSUP_p.getDepartmentUnitCode().substring(0,3));
				if(objSpclPatVisitSUP_p.getIsPatReferByList()!=null && objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					objArrEpisodeVO1[0].setDepartmentCode(objSpclPatVisitSUP_p.getToDepartmentCode());
				else{
					objArrEpisodeVO1[0].setDepartmentCode(objPatientVO.getDepartmentCode());
					objArrEpisodeVO1[0].setDepartmentUnitCode(objPatientVO.getDepartmentUnitCode());
					
				}
				
				//objArrEpisodeVO1[0].setFileNo(objSpclPatVisitSUP_p.getFileNo());
				objSpclPatVisitSUP_p.setDepartmentUnitCode(objSpclPatVisitSUP_p.getDepartmentCode());
				objArrEpisodeVO1[0].setPatAmountCollected(patAmountCollected);
				//added by warish for paymot mode
				objArrEpisodeVO1[0].setPaymentModeCode(objSpclPatVisitSUP_p.getPaymentModeCode());
				if(!objSpclPatVisitSUP_p.getPaymentModeCode().equals("1"))
					objArrEpisodeVO1[0].setPaymentModeCodeRefId(objSpclPatVisitSUP_p.getPaymentModeRefId());
				
				if(objSpclPatVisitSUP_p.getIsReferred()!=null && objSpclPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
					objArrEpisodeVO1[0].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				}
				if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
					int index=Integer.parseInt(objSpclPatVisitSUP_p.getOnlineReferedIndex());
					deptName=episodeRefDtlVO[index].getToDepartment();
				}
				else{
					deptName=SpclPatientVisitUTL.getEntryLabel(col,objSpclPatVisitSUP_p.getDepartmentUnitCode());
				}
				
				if(deptName.indexOf('(')>0)
					deptName=deptName.substring(0,deptName.indexOf('('));
				objArrEpisodeVO1[0].setDepartment(deptName);
			} 
			else
			{
				objArrEpisodeVO1 = new EpisodeVO[deptToVisit.length];
				for(int i=0; i<objArrEpisodeVO1.length; i++)
				{
					objArrEpisodeVO1[i]=new EpisodeVO();
					//System.out.println("----Dept To Visit---"+deptToVisit[i]+"-----------");
					objArrEpisodeVO1[i].setDepartmentCode(deptToVisit[i]);
					objArrEpisodeVO1[i].setFileNo(arrFileNo[i]);
					//objArrEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountCollected());
					objArrEpisodeVO1[i].setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
					objArrEpisodeVO1[i].setPatAmountCollected(patAmountCollected);
				if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
						int index=Integer.parseInt(objSpclPatVisitSUP_p.getOnlineReferedIndex());
						deptName=episodeRefDtlVO[index].getToDepartment();
					}else{
					 deptName=SpclPatientVisitUTL.getEntryLabel(col,deptToVisit[i]);
					}
					//since department contains department name plus unit name
					//objArrEpisodeVO[i].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
					if(deptName.indexOf('(')>0)
						deptName=deptName.substring(0,deptName.indexOf('('));
					 
					objArrEpisodeVO1[i].setDepartment(deptName);
				}
			} 
			String sysdate=(String)objSession.getAttribute(Config.SYSDATE);
			objPatientVO.setSystemDate(sysdate);
			/*if(objSpclPatVisitSUP_p.getIsReferred()!=null && objSpclPatVisitSUP_p.getIsReferred().equals("on")){
				objPatientVO.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
			}*/
			
	
			objRequest_p.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE, objSpclPatVisitSUP_p.getDepartmentCode());
			
			// setting the registration charge 0 for the department code matched in the configuration
			String [] freeDeptCodeArray=RegistrationConfig.DEPT_FOR_FREE_REG.split(",");
			for(int i=0;i<freeDeptCodeArray.length;i++){
				if(episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)){
					if(objArrEpisodeVO1[0].getDepartmentCode().equals(freeDeptCodeArray[i])){
						objPatientVO.setPatAmountCollected("0");
						objArrEpisodeVO1[0].setPatAmountCollected("0");
						break;
					}
				}
				else{
					if(objArrEpisodeVO1[0].getDepartmentCode().equals(freeDeptCodeArray[i])
							&& objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode().equals(RegistrationConfig.EXTERNAL_INSTITUTE_PGI)){
						objPatientVO.setPatAmountCollected("0");
						objArrEpisodeVO1[0].setPatAmountCollected("0");
						break;
					}
				}	
			}
			System.out.println("DeptUnitCode(PatintVO) b4 calling DATA :"+objPatientVO.getDepartmentUnitCode());
			
			//objPatientVO.getDepartmentUnitCode()
			/*  ## 		Modification Log							
	 		##		Modify Date				:06thFeb'15 
	 		##		Reason	(CR/PRS)		://To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
	 		##		Modify By				:Sheeldarshi */
			//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
					String strUniqueIdDuplicyFlag = "0";
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			objCreditAvailDtlVO.setCreditLetterRefNo(objSpclPatVisitSUP_p.getCreditLetterRefNo());
			objCreditAvailDtlVO.setCreditLetterDate(objSpclPatVisitSUP_p.getCreditLetterDate());
			objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
			if(objSpclPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatientVO.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
				objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
				/*if(objSpclPatVisitSUP_p.getIsPatReferByList()!=null&&!objSpclPatVisitSUP_p.getIsPatReferByList().equals("1"))
					strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);
		
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
						objSpclPatVisitSUP_p.setErrorMessage(strErrMsg);
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						//return false;
					}
				}*/
			}
			//End:sheeldarshi	
			/////////////////////////////////////////////////////////////////////////////
			EpisodeVO [] objArrEpisodeVO2=SpclPatientVisitDATA.saveSpclNewDepartmentVisit(objPatientVO, objArrEpisodeVO1, episodeRefVO, objUserVO,oldPatientVO);
			
			objRequest_p.setAttribute(RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO1);	
			
		    // StringBuilder str=new StringBuilder();
			 
		     int arrEpisodeVOLength=objArrEpisodeVO2.length;
			 String[] strQueueNo=new String[arrEpisodeVOLength];
			 String[] strDeptName=new String[arrEpisodeVOLength];
			 String[] strUnitName=new String[arrEpisodeVOLength];
			 String[] strRoomName=new String[arrEpisodeVOLength];
			 
			 
			 for(int i=0;i<arrEpisodeVOLength;i++){
				 strQueueNo[i]=objArrEpisodeVO2[i].getQueNo();
				 if(objArrEpisodeVO2[i].getDepartment().indexOf('(')>0)
					 strDeptName[i]=objArrEpisodeVO2[i].getDepartment().substring(0,objArrEpisodeVO2[i].getDepartment().indexOf('('));
				 else
					 strDeptName[i]=objArrEpisodeVO2[i].getDepartment();					
				 strUnitName[i]=objArrEpisodeVO2[i].getDepartmentUnit();
				 strRoomName[i]=objArrEpisodeVO2[i].getRoom();
				 objArrEpisodeVO2[i].setExpiryDate(objArrEpisodeVO2[i].getEpisodeExpiryDate());
						 	    
			 }
			
			// System.out.println("display string"+str);
			//By Mukund on 25.09.2017
			 
			//calling barcode slip content creation method
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.EPISODE_FOR_BARCODE, objArrEpisodeVO2);
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATVO_FOR_BARCODE, objPatientVO);
			BarcodeGenerationUTIL.saveAndCreateBarcodeSlipPrintingDetails(objRequest_p, objArrEpisodeVO2, objPatientVO);
			
			String tmpFileName=RegistrationConfig.CARD_NEW_DEPT_VISIT+objUserVO.getSeatId();
			objPatientVO.setPatPrimaryCat(patPriCatLable);
			
			/**To set the qrcode for printing over the OPD card*/
			String jsonStr = objPatientVO.getStrQRCode();
			QRCodeTest objQRC = new QRCodeTest();
			objQRC.createQRCode(jsonStr, objRequest_p);
			/***/
			
			
			objSpclPatVisitSUP_p.setStrNormalMsg(objSpclPatVisitSUP_p.getStrNormalMsg()+ "\nNew Patient Visit Stamped Successfully");
			str=NewRegistrationSlip.print(preapareSlip(objArrEpisodeVO2,objPatientVO,objSpclPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"DV");
			objSpclPatVisitSUP_p.setPrint("1");
			objSpclPatVisitSUP_p.setPrintDivContent(str);
			System.out.println("PrintDivContent (New) :"+objSpclPatVisitSUP_p.getPrintDivContent());
			//objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientVisitedLabel'>"+"New Department Visit: "+"</div></font>");
			
			//Bill Receipt Printing 
			if((objSpclPatVisitSUP_p.getPatAmountCollected()!=null)&&!(objSpclPatVisitSUP_p.getPatAmountCollected().equals("0"))&&!(objSpclPatVisitSUP_p.getPatAmountCollected().equalsIgnoreCase("0.00")))
			{
				strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(objArrEpisodeVO2,objPatientVO,objSpclPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"NDV");
				System.out.println("PrintBillSlip :"+strBillPrintDiv);
				WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
				str+=""+strBillPrintDiv+"";
				objSpclPatVisitSUP_p.setPrintDivContent(str);
			}
			
			System.out.println("Full PrintDivContent (New) :"+str);
			
			if(objSpclPatVisitSUP_p.getIsPrintStatus().equals("1")){
				//objStatus.add(Status.DONE,"","Patient Visited Sucessfully");
				//objStatus.add(Status.DONE,str.toString(),"");
				objStatus.add(Status.DONE,"","<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientRegisteredLabel'>"+"Patient Visited Sucessfully "+"</div></font><font color='#000000'>"+str+"</font>");
				//objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divSplPatientVisitedLabel'>"+"New Department Visit: "+"</div></font>");
			}else if(objSpclPatVisitSUP_p.getIsPrintStatus().equals("2")){
				objStatus.add(Status.DONE,"","<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientRegisteredLabel'>"+"Patient Visited Sucessfully "+"</div></font><font color='#000000'></font>");
			}

			objSpclPatVisitSUP_p.setPrint("2");
		 }
		catch(HisUpdateUnsuccesfullException e){
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
		}		
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());		
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisSQLManualException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Department-Unit Limit Exhausted");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
		 WebUTIL.setStatus(objRequest_p,objStatus);		
		}	
	}
	
	public static void saveSplPatientVisitStamp(HttpServletRequest objRequest_p,
			SpclPatientVisitSUP objSpclPatVisitSUP_p) {
			System.out.println("SpclPatientVisitUTL:: saveSplPatientVisitStamp()");
			String[] deptToVisit = objSpclPatVisitSUP_p.getDepartmentsToVisitStamp();
			//objUserVO.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
			//objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			EpisodeVO[] objArrEpisodeVO = new EpisodeVO[deptToVisit.length];		
			objArrEpisodeVO=populateNewDepartmentVisitEpisode(objRequest_p,objSpclPatVisitSUP_p);
			savePatientVisit(objRequest_p, objSpclPatVisitSUP_p, "SAVE", objArrEpisodeVO);
		
	}

	public static void savePatientVisit(HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p,String strMode_p,EpisodeVO[] objArrNewDeptEpisodeVO_p){
		
		Status objStatus = new Status();
		 
		UserVO objUserVO=getUserVO(objRequest_p);
		
		EpisodeVO[] selectedEpisodeVO=null;
		String[] strQueueNo=null;
		String[] strDeptName=null;
		String[] strUnitName=null;
		String[] strRoomName=null;
		String strPrintDivContent1="",strPrintDivContent2="";
		String strBillPrintDivContent1="",strBillPrintDivContent2="";
		String strTempPrintDivContent="";
		
		try{
			System.out.println("SpclPatientVisitUTL:: savePatientVisit()");
			getHospitalVO(objRequest_p);
			int k=0;		
			HttpSession session=WebUTIL.getSession(objRequest_p);			
			PatientVO objPatientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);	
			
			
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			
			EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR);		 
			String[] deptToVisit=null;
			
			String regCatCode=objPatientVO.getPatRegCatCode();
			String genderCode=objPatientVO.getPatGenderCode();
			String patCrNo=objPatientVO.getPatCrNo();
			if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null && objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
				objSpclPatVisitSUP_p.setPatRefGnctdHospitalDept(objSpclPatVisitSUP_p.getPatRefHospitalDeptOther());
			}
			if(objSpclPatVisitSUP_p.getIsReferred()!=null&&objSpclPatVisitSUP_p.getIsReferred().equalsIgnoreCase("on")){
				objSpclPatVisitSUP_p.setIsReferred("1");
			}
			//HelperMethods.populate(objPatientVO, objSpclPatVisitSUP_p);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objSpclPatVisitSUP_p);
			if(objPatientVO.getIsUnknown()!=null && objPatientVO.getIsUnknown().equalsIgnoreCase(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			{
				///removing (unknown) from unknown patient name
				objPatientVO.setPatFirstName(objPatientVO.getPatFirstName().substring(9));
			}
			objPatientVO.setPatGenderCode(genderCode);
			objPatientVO.setPatCrNo(patCrNo);
			objPatientVO.setPatRegCatCode(regCatCode);
			objPatientVO.setPatSecondaryCatCode("");
			objPatientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD);
			//String isVisitOnRequest=objSpclPatVisitSUP_p.getOnRequestVisit();
			objPatientVO.setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
			if(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length>0)
				deptToVisit=objSpclPatVisitSUP_p.getDepartmentsToVisitStamp();
			else{
				
				///in case if only new department is selected since for old unit was closed
				//hcode is present in case of forward on objRequest_p
				if(objSpclPatVisitSUP_p.getHcode()!=null && !objSpclPatVisitSUP_p.getHcode().equals(""))
				{
					deptToVisit=new String[]{objSpclPatVisitSUP_p.getHcode()};
				}else{
					deptToVisit=new String[0];
				}
			}
			
			int x=deptToVisit.length;
			int y=0;
			if(arrEpisodeVOSes!=null)
			{			
			 y=arrEpisodeVOSes.length;
			}
			
			
			EpisodeVO[] objArrEpisodeVO = new EpisodeVO[x];
			for(int i=0; i<y; i++)
			{
				for(int j=0;j<x;j++){
					
					if(arrEpisodeVOSes[i].getEpisodeCode().equals(deptToVisit[j])){
						arrEpisodeVOSes[i].setPatVisitReason(objSpclPatVisitSUP_p.getArrPatVisitReason()[i]);
						arrEpisodeVOSes[i].setCreditBillFlag(objSpclPatVisitSUP_p.getArrCreditBillFlag()[i]);
						if("1".equals(objSpclPatVisitSUP_p.getArrCreditBillFlag()[i])){
							arrEpisodeVOSes[i].setCreditLetterRefNo(objSpclPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
							arrEpisodeVOSes[i].setCreditLetterDate(objSpclPatVisitSUP_p.getArrCreditLetterDate()[i]);
						}
						objArrEpisodeVO[k]=arrEpisodeVOSes[i];
						//this flag is used to check if visit is forceful
						objArrEpisodeVO[k].setOnRequestVisit(objSpclPatVisitSUP_p.getOnRequestVisit());
						k++;
					} 
				 }
			}
			//forward visit (onRequestVisit/hiddenEpisode-->visit stamp on objRequest_p)
			//renewal visit (selected objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length) Modified by Singaravelan on 07-10-13
			System.out.println("----New Dept visit-----------"+objSpclPatVisitSUP_p.getNewDepartmentVisit()+"----------------");
			System.out.println("----Old Dept visit-----------"+objSpclPatVisitSUP_p.getOldDepartmentVisit()+"----------------");

			String strRenewalTypeHospOrEpisode="";
			if(objSpclPatVisitSUP_p.getStrRenewalType()!=null && 
					(objSpclPatVisitSUP_p.getStrRenewalType().equals("3") || objSpclPatVisitSUP_p.getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			if(strMode_p.equals("SAVE"))
			{
				selectedEpisodeVO=new EpisodeVO[objArrEpisodeVO.length];
				if(objSpclPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on") && objSpclPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on"))
				{		
					for(int i=0;i<objArrEpisodeVO.length;i++)
					{
						selectedEpisodeVO[i]=objArrEpisodeVO[i];
						
						selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
						selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
						
						if(strRenewalTypeHospOrEpisode.equals("E")){
							if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1")){
								selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatRenewalAmountDeptWise());
								objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
							}else{
								objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							}
						}else if(strRenewalTypeHospOrEpisode.equals("H")){
							 
						 }
						else{
							selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
						}
						
					}
				
				}
				else
				{
					for(int i=0;i<objArrEpisodeVO.length;i++)
					{
						selectedEpisodeVO[i]=objArrEpisodeVO[i];
						
						selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
						selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
						

						if(strRenewalTypeHospOrEpisode.equals("E")){
							if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1")){
								selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatRenewalAmountDeptWise());
								objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
							}else{
								objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							}
						}
						else{
							selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
						}
						
						/*if(strRenewalTypeHospOrEpisode.equals("E")){
							if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1"))
								selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountDeptWise());
						}
//						else if(strRenewalTypeHospOrEpisode.equals("H")){
//							 if(objPatientVO.getSplRenewalRequired()!=null && objPatientVO.getSplRenewalRequired().equals("1")){
//								 if(i==0)
//									 selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountHospitalWise());
//								 else
//									 selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
//							 }
//						 } //Above Code For Calculating PatAmount is commented, because for HospitalWise Amount is already calculated while populating episodeVo at NewDeptVisit (i.e in method populateNewDepartmentVisitEpisode)
						else{
							selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
								
						}*/
					}
				}
					
			}
			else
			{
				if(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp()!=null )
				{
					selectedEpisodeVO= new EpisodeVO[objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length];
				//System.out.println(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length);
				//selectedEpisodeVO= new EpisodeVO[objSpclPatVisitSUP_p.getDepartmentsToRenew().length];
				for(int i=0;i<objSpclPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
				{
					if(arrEpisodeVOSes!=null)
					{
					for(int j=0;j<arrEpisodeVOSes.length;j++)
					{
						if(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrEpisodeVOSes[j].getEpisodeCode()))
						{
							selectedEpisodeVO[i]=new EpisodeVO();
								HelperMethods.populate(selectedEpisodeVO[i],arrEpisodeVOSes[j]);
								
								selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
								selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
								
								if(strRenewalTypeHospOrEpisode.equals("E")){
									if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1")){
										selectedEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatRenewalAmountDeptWise());
										objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
										selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
									}else{
										objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
										selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
									}
								}
								else{
									selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
								}
								
								selectedEpisodeVO[i].setEpisodeCode(objSpclPatVisitSUP_p.getDepartmentsToVisitStamp()[i]);
								selectedEpisodeVO[i].setSystemIPAddress(objPatientVO.getSystemIPAddress());
						}
					}
					}
				}
				}
			}
					
			HttpSession objSession=WebUTIL.getSession(objRequest_p);

			EpisodeRefDtlVO episodeRefVO=new EpisodeRefDtlVO();
			if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				EpisodeRefDtlVO[] arrEpisodeOldPatRefVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_OLD_SPECIAL_PAT_REFER_VO);
				int indexID=Integer.parseInt(objSpclPatVisitSUP_p.getIndexID());
				
				episodeRefVO.setPatCrNo(arrEpisodeOldPatRefVO[indexID].getPatCrNo());
				episodeRefVO.setEpisodeCode(arrEpisodeOldPatRefVO[indexID].getEpisodeCode());
				episodeRefVO.setEpisodeVisitNo(arrEpisodeOldPatRefVO[indexID].getEpisodeVisitNo());
				episodeRefVO.setSerialNo(arrEpisodeOldPatRefVO[indexID].getSerialNo());
				episodeRefVO.setMlcNo(arrEpisodeOldPatRefVO[indexID].getMlcNo());
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				objSpclPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				objPatientVO.setIsPatReferByList(objSpclPatVisitSUP_p.getIsPatReferByList());
				objPatientVO.setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
				
			}
			else
			{
			if(objSpclPatVisitSUP_p.getIsReferred()!=null && objSpclPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
			EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
			
			episodeRefVO.setIsRefferInOut(objSpclPatVisitSUP_p.getIsRefferInOut());
			
			if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
				objSpclPatVisitSUP_p.setPatRefGnctdHospitalDept(objSpclPatVisitSUP_p.getPatRefHospitalDeptOther());
			}
			
			episodeRefVO.setExternalHospitalDepartment(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDept());
			episodeRefVO.setExternalHospitalDepartmentUnit(objSpclPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
			episodeRefVO.setExternalHospitalDoctorName(objSpclPatVisitSUP_p.getPatRefDoctor());
			episodeRefVO.setExternalHospitalName(objSpclPatVisitSUP_p.getPatRefHospitalName());
			episodeRefVO.setExternalHospitalPatCrNo(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCrno());
			episodeRefVO.setEpisodeCode(objSpclPatVisitSUP_p.getRefferringOPDEpisode());
		
			if(objSpclPatVisitSUP_p.getIsAssociated()!=null && objSpclPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE)) 
				{
				if(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode().contains("#"))
					episodeRefVO.setExternalHospitalCode(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode().split("#")[0]);
				else
				episodeRefVO.setExternalHospitalCode(objSpclPatVisitSUP_p.getPatRefGnctdHospitalCode());
				}
			else if(objSpclPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
				episodeRefVO.setExternalHospitalCode("");
			if(arrOpenOPDEpisodeVO!=null){
			for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
				if(objSpclPatVisitSUP_p.getIsRefferInOut()!=null && (objSpclPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL) || objSpclPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)))	{
				if(episodeRefVO.getEpisodeCode().equals(arrOpenOPDEpisodeVO[i].getEpisodeCode())){
					episodeRefVO.setFromDepartmentCode(arrOpenOPDEpisodeVO[i].getFromDepartmentCode());
					episodeRefVO.setFromDepartmentUnitCode(arrOpenOPDEpisodeVO[i].getFromDepartmentUnitCode());
					episodeRefVO.setEpisodeVisitNo(arrOpenOPDEpisodeVO[i].getEpisodeVisitNo());
					episodeRefVO.setMlcNo(arrOpenOPDEpisodeVO[i].getMlcNo());
				}
				}
			}
			}
			}
			if((objSpclPatVisitSUP_p.getIsReferred()!=null && objSpclPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)) && !(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)) && !(objSpclPatVisitSUP_p.getIsRefferInOut()!=null && objSpclPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))){
				
				/*
				 * In case of Patient Visit if Both old visit and New Visit is Selected
				 * And no Department is selected for old Visit 
				 * then we need not check if Visiting and reffered unit are same as no visiting unit is present
				 * as Hcode is null the array objArrEpisodeVO formed would be null
				 * giving a bug id 17581 (Deepika Gaba ) 
				 */
				if(objArrEpisodeVO.length!=0)
				{
					if(episodeRefVO.getFromDepartmentUnitCode().equals(objArrEpisodeVO[0].getDepartmentUnitCode())){
					objStatus.add(Status.TRANSINPROCESS,"Visiting Unit And Reffered Unit Cannot be Same","");
					objSpclPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
					throw new HisInsertNotAllowedException();
					}
				}
			}
							
				//////////////////////////////////////////////////////////
				
				Map episodeMap=new HashMap();
				
				/*  ## 		Modification Log							
		 		##		Modify Date				:06thFeb'15 
		 		##		Reason	(CR/PRS)		://To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
		 		##		Modify By				:Sheeldarshi */
				//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
						String strUniqueIdDuplicyFlag = "0";
				CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
				objCreditAvailDtlVO.setCreditLetterRefNo(objSpclPatVisitSUP_p.getCreditLetterRefNo());
				objCreditAvailDtlVO.setCreditLetterDate(objSpclPatVisitSUP_p.getCreditLetterDate());
				objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
				if(objSpclPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatientVO.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
				{
					HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
					objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
					strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);
			
					if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
						if(strUniqueIdDuplicyFlag.equals("1")){
							String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
							objSpclPatVisitSUP_p.setErrorMessage(strErrMsg);
							//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
							//return false;
						}
					}
				}
				//End:sheeldarshi
				
				episodeMap=SpclPatientVisitDATA.saveSpclPatientVisit(objPatientVO, selectedEpisodeVO, objUserVO,episodeRefVO,objArrNewDeptEpisodeVO_p);
				
				EpisodeVO[] _newEpisodeVO=(EpisodeVO[])episodeMap.get("SPCLNEWEPISODELIST");
				EpisodeVO[] _oldEpisodeVO=(EpisodeVO[])episodeMap.get("SPCLOLDEPISODELIST");
				
				//objRequest_p.setAttribute(RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
				objSpclPatVisitSUP_p.setSaveSuccessful("true");
				
				 
				String patPriCatLable=objPatientVO.getPatPrimaryCat();
				String tmpFileName="";
				if(_newEpisodeVO!=null)
				{
				
				int tempEpiLen=_newEpisodeVO.length;
				 strQueueNo=new String[tempEpiLen];
				 strDeptName=new String[tempEpiLen];
				 strUnitName=new String[tempEpiLen];
				 strRoomName=new String[tempEpiLen];
				 
				 for(int i=0;i<tempEpiLen;i++){
					 strQueueNo[i]=_newEpisodeVO[i].getQueNo();
					 strDeptName[i]=_newEpisodeVO[i].getDepartment();
					 strUnitName[i]=_newEpisodeVO[i].getDepartmentUnit();
					 strRoomName[i]=_newEpisodeVO[i].getRoom();
					
					 	    
				 }
				 /////////for displaying the name ////////////
				 if(objPatientVO.getPatMiddleName()==null)
					 objPatientVO.setPatMiddleName("");
				 if(objPatientVO.getPatLastName()==null)
					 objPatientVO.setPatLastName("");
				 ///////////////////////////////////////////
				
				 
				 tmpFileName  =RegistrationConfig.CARD_NEW_DEPT_VISIT+objUserVO.getSeatId();
					objPatientVO.setPatPrimaryCat(patPriCatLable);
					
				RegistrationSlip regSlip1=preapareSlip(_newEpisodeVO,objPatientVO,objSpclPatVisitSUP_p,objRequest_p);
				strPrintDivContent1=NewRegistrationSlip.print(regSlip1, tmpFileName, objRequest_p, "RP");
					
				}
				if(_oldEpisodeVO!=null && _oldEpisodeVO.length>0)
				{
					int tempEpiLen=_oldEpisodeVO.length;
					 strQueueNo=new String[tempEpiLen];
					 strDeptName=new String[tempEpiLen];
					 strUnitName=new String[tempEpiLen];
					 strRoomName=new String[tempEpiLen];
					 
					 for(int i=0;i<tempEpiLen;i++){
						 strQueueNo[i]=_oldEpisodeVO[i].getQueNo();
						 strDeptName[i]=_oldEpisodeVO[i].getDepartment();
						 strUnitName[i]=_oldEpisodeVO[i].getDepartmentUnit();
						 strRoomName[i]=_oldEpisodeVO[i].getRoom();
						
						 	    
					 }
					 /////////for displaying the name ////////////
					 if(objPatientVO.getPatMiddleName()==null)
						 objPatientVO.setPatMiddleName("");
					 if(objPatientVO.getPatLastName()==null)
						 objPatientVO.setPatLastName("");
					
					
					
					 
					tmpFileName  =RegistrationConfig.CARD_OLD_DEPT_VISIT+objUserVO.getSeatId();
					objPatientVO.setPatPrimaryCat(patPriCatLable);
						
					RegistrationSlip regSlip2=preapareSlip(_oldEpisodeVO,objPatientVO,objSpclPatVisitSUP_p,objRequest_p);
					strPrintDivContent2=NewRegistrationSlip.print(regSlip2, tmpFileName, objRequest_p, "RP");
					
					//objSpclPatVisitSUP_p.setPrintDivContent(strPrintDivContent1+strPrintDivContent2);
					strTempPrintDivContent=strPrintDivContent1;
					//objEmgPatVisitSUP_p.setPrintDivContent(strPrintDivContent1+strPrintDivContent2);
					objSpclPatVisitSUP_p.setPrintDivContent(strTempPrintDivContent);
					objSpclPatVisitSUP_p.setPrint("1");
					System.out.println("Print in Both  :"+objSpclPatVisitSUP_p.getPrintDivContent());
					
					
					//Both Case Bill Print
					if((objSpclPatVisitSUP_p.getPatAmountCollected()!=null)&&(objSpclPatVisitSUP_p.getPatAmountCollected()!="0")&&!(objSpclPatVisitSUP_p.getPatAmountCollected().equalsIgnoreCase("0.00")))
					{
						strBillPrintDivContent1=NewRegistrationSlip.printBillReceipt(preapareSlip(_newEpisodeVO,objPatientVO,objSpclPatVisitSUP_p,objRequest_p), tmpFileName,objRequest_p,"NDV");
						System.out.println("PrintBillSlip :"+strBillPrintDivContent1);
						strTempPrintDivContent+=""+strBillPrintDivContent1+"";
						objSpclPatVisitSUP_p.setPrintDivContent(strTempPrintDivContent);
					}
					if(_oldEpisodeVO!=null){
						if((_oldEpisodeVO[0].getPatAmountCollected()!=null)&&(_oldEpisodeVO[0].getPatAmountCollected()!="0")&&!(_oldEpisodeVO[0].getPatAmountCollected().equalsIgnoreCase("0.00")))
						{
							strBillPrintDivContent2=NewRegistrationSlip.printBillReceipt(preapareSlip(_oldEpisodeVO,objPatientVO,objSpclPatVisitSUP_p,objRequest_p), tmpFileName,objRequest_p,"ODV");
							System.out.println("PrintBillSlip :"+strBillPrintDivContent2);
							strTempPrintDivContent+=""+strBillPrintDivContent2+"";
							objSpclPatVisitSUP_p.setPrintDivContent(strTempPrintDivContent);
						}
					}
						
				}
				
			
			
		}
		
		catch(HisInsertNotAllowedException e){
			e.printStackTrace();
			
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
			//objStatus.add(Status.RECORDFOUND,e.getMessage() ,"");
			
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());		
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Application Execution Error" ,"");
		}finally{
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
		
	}

	private static EpisodeVO[] populateNewDepartmentVisitEpisode(
			HttpServletRequest objRequest_p, SpclPatientVisitSUP objSpclPatVisitSUP_p) {
		
		
		HttpSession objSession=objRequest_p.getSession();
		EpisodeVO[] objArrEpisodeVO =null;
		
		try
		{
			
			System.out.println("SpclPatientVisitUTL :: populateNewDepartmentVisitEpisode()");
			Collection col=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.SPLREGISTRATIONDESK_OPTION_DEPARTMENT);
			String deptName="";
			String[] deptToVisit = new String[0];
			objArrEpisodeVO = new EpisodeVO[deptToVisit.length];
			String patAmountCollected="0";
			
			patAmountCollected= objSpclPatVisitSUP_p.getPatAmountCollected();
			
			if(deptToVisit==null || deptToVisit.length==0){
				objArrEpisodeVO = new EpisodeVO[1];			
				objArrEpisodeVO[0]=new EpisodeVO();
				System.out.println("-----------------"+objSpclPatVisitSUP_p.getDepartmentUnitCode()+"-------------------");
				//objArrEpisodeVO[0].setDepartmentCode(objSpclPatVisitSUP_p.getDepartmentUnitCode().substring(0,3));
				
				System.out.println("dept code :"+ objSpclPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				System.out.println("dept unit code :"+objSpclPatVisitSUP_p.getDepartmentCode().split("#")[1]);
				
				objArrEpisodeVO[0].setDepartmentCode(objSpclPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				objArrEpisodeVO[0].setDepartmentUnitCode(objSpclPatVisitSUP_p.getDepartmentCode().split("#")[1]);
				objArrEpisodeVO[0].setFileNo(objSpclPatVisitSUP_p.getFileNo());
				objArrEpisodeVO[0].setPatAmountCollected(patAmountCollected);
				objArrEpisodeVO[0].setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
				/*if(objSpclPatVisitSUP_p.getIsReferred()!=null && objSpclPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
					objArrEpisodeVO[0].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				}*/
				if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
					int index=Integer.parseInt(objSpclPatVisitSUP_p.getOnlineReferedIndex());
					deptName=episodeRefDtlVO[index].getToDepartment();
				}else{
				 deptName=SpclPatientVisitUTL.getEntryLabel(col,objSpclPatVisitSUP_p.getDepartmentUnitCode());
				}
				
				
				System.out.println("-----------------"+deptName+"-------------------");
				//since department contains department name plus unit name
				//objArrEpisodeVO[0].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
				objArrEpisodeVO[0].setDepartment(deptName);
			} 
			else{
				objArrEpisodeVO = new EpisodeVO[deptToVisit.length];
				for(int i=0; i<objArrEpisodeVO.length; i++){
					objArrEpisodeVO[i]=new EpisodeVO();
					objArrEpisodeVO[i].setDepartmentCode(deptToVisit[i]);
					//objArrEpisodeVO[i].setPatAmountCollected(objSpclPatVisitSUP_p.getPatAmountCollected());
					
					objArrEpisodeVO[i].setIsReferred(objSpclPatVisitSUP_p.getIsReferred());
					objArrEpisodeVO[i].setPatAmountCollected(patAmountCollected);
					if(objSpclPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_SPECIAL_PAT_VO);
						int index=Integer.parseInt(objSpclPatVisitSUP_p.getOnlineReferedIndex());
						deptName=episodeRefDtlVO[index].getToDepartment();
					}else{
					 deptName=SpclPatientVisitUTL.getEntryLabel(col,deptToVisit[i]);
					}
					objArrEpisodeVO[i].setDepartment(deptName);
				}
			} 
			String sysdate=(String)objSession.getAttribute(Config.SYSDATE);
			
			

			objRequest_p.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE, objSpclPatVisitSUP_p.getDepartmentCode());

			
			// setting the registration charge 0 for the department code matched in the configuration
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return objArrEpisodeVO;
	}
	
	public static void printLastOpdCard(SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p){	
		
		Status objStatus=new Status();
		try{
			UserVO objUserVO=getUserVO(objRequest_p);
			String tmpFileName=RegistrationConfig.CARD_NEW_DEPT_VISIT+objUserVO.getSeatId();
			tmpFileName+="opdCard";
			boolean fileExists=false;
			File f=null;
			if(HisFileControlUtil.isWindowsOS()){
				f=new File(System.getProperties().getProperty("java.io.tmpdir")+ tmpFileName+".dat");
				if(f.exists())
					fileExists=true;	
			}
			else{
				f=new File("/root/"+tmpFileName+".dat");
				if(f.exists())
					fileExists=true;
			}
				
			if(fileExists){
				String clientOS=NewRegistrationSlip.getClientSystemOsType(objRequest_p);
				HisPrinterSupport.printSlip(objRequest_p.getRemoteHost(),tmpFileName+".dat",clientOS,"HISPRINTER");
				//objSpclPatVisitSUP_p.setPrint("1");
				//objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientVisitedLabel'>"+"Card Printed Successfully"+"</div></font>");
				
				objStatus.add(Status.DONE,"","Card Printed Successfully");
			}
			else
				objStatus.add(Status.DONE);	
		}
		catch(Exception e){
			objStatus.add(Status.ERROR);
		}		
		finally
		{
			WebUTIL.setStatus(objRequest_p,objStatus);
			
		}	
	}
	public static void getMapOfRenewalConfigDtlOnKeyPatCat(SpclPatientVisitSUP objSpclPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCatGroup=new HashMap();
		try
		{
			System.out.println("SpclPatientVisitUTL :: getMapOfRenewalConfigDtlOnKeyPatCat()");
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			mapOfRenewalVoOnKeyPatCatGroup=SpclPatientVisitDATA.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
			//objStatus.add(Status.ERROR,"",e.getMessage());
			objSpclPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			objSpclPatVisitSUP_p.setErrorMessage("Record Not Found");
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
	
	private static void setDepartmentDtl(Collection objCol_p, PatientVO objPatientVO_p,  String strDeptCode_p){
		
		Iterator itr=objCol_p.iterator();
		while (itr.hasNext())
		{
			Entry objEntry=(Entry)itr.next();
			String[] strDeptValArray = objEntry.getValue().split("#");		
			
			if(objEntry.getValue() !=null && objEntry.getValue().equals(strDeptCode_p)){
					objPatientVO_p.setDepartmentUnitCode(strDeptValArray[1]);
					objPatientVO_p.setRoomCode(strDeptValArray[2]);					
					//objPatientVO_p.setRoundRobinUnitFlag(strDeptValArray[3]);		
					objPatientVO_p.setRoundRobinUnitFlag("0"); //Set the RRUnitFlag to zero Unit pre-selected on Special Visit cases 
					objPatientVO_p.setRoundRobinRoomFlag(strDeptValArray[3]);
					objPatientVO_p.setDepartment(objEntry.getLabel());
					break;
				}	
				
				//return objEntry.getLabel();		
		}		
		//System.out.println("getEntryLabel:  "+)
	}
	
	//To set the Department Details on Pre-Appointment Basis
	private static void setDepartmentDtl_PreApt(PatientVO objPatientVO_p,  SpclPatientVisitSUP objSpclPatVisit_p){		
		
			String[] strDeptValArray = objSpclPatVisit_p.getDepartmentCode().split("#");			
				
			objPatientVO_p.setDepartmentUnitCode(strDeptValArray[1]);
			objPatientVO_p.setRoomCode(strDeptValArray[2]);					
			//objPatientVO_p.setRoundRobinUnitFlag(strDeptValArray[3]);		
			objPatientVO_p.setRoundRobinUnitFlag("0");
			//objPatientVO_p.setRoundRobinRoomFlag(strDeptValArray[4]);
			objPatientVO_p.setRoundRobinRoomFlag("0");
			objPatientVO_p.setDepartment(objSpclPatVisit_p.getDepartment());				
	
	}
	
	public static void writeResponse(HttpServletResponse objResponse_p, String strOutput_p){
		try{
			objResponse_p.reset();
			objResponse_p.setContentType("text/xml");
			objResponse_p.setHeader("Cache-Control", "no-cache");
			//System.out.println(output);
			objResponse_p.getWriter().write(strOutput_p);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private static void createOptionObject(List<Entry> objListEntry_p,Document responseDocument_p, String strFieldName_p) 
	{
	 
		Element fieldElement=responseDocument_p.createElement(strFieldName_p);
		for(Entry entry:objListEntry_p)
		{
			Element option= responseDocument_p.createElement("option");
			fieldElement.appendChild(option);
			
			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument_p.getFirstChild().appendChild(fieldElement);
	}

	public static String getEntryLabel(Collection objCol_p,String strValue_p)
	{
		
		Iterator it=objCol_p.iterator();
		while (it.hasNext())
		{
			Entry objEntry=(Entry)it.next();
			if(objEntry.getValue().equals(strValue_p))
				return objEntry.getLabel();		
		}		
		return strValue_p;				
	}
}
