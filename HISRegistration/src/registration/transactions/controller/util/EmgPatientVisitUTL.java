package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.HisPrinterSupport;
import hisglobal.utility.HisUtil;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.sql.rowset.WebRowSet;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.bo.RegEssentialBO;
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
import registration.transactions.controller.actionsupport.EmgPatientVisitSUP;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.data.EmgPatientVisitDATA;
import registration.transactions.controller.data.NewRegistrationDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.registration.BeneficiaryPatientVO;
import vo.registration.CreditAvailDetailVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import vo.registration.PatientBroughtByDtlVO;

public class EmgPatientVisitUTL extends ControllerUTIL
{	
	public static void setPatAmount(EmgPatientVisitSUP objEmgPatVisitSUP_p,PatientVO objPatientVO_p,UserVO objUserVO_p, HttpServletRequest objRequest_p ){
		String	strRenewalType =objPatientVO_p.getRenewalConfig().getStrRenewalType();
		String strAmountCollected="";
		
		if(strRenewalType==null || strRenewalType.equals("0"))
			strRenewalType="0";
		int key= Integer.parseInt(strRenewalType);
		System.out.println("strRenewalType :"+key);
		System.out.println("emgRenewalRequired :"+ objEmgPatVisitSUP_p.getEmgRenewalRequired());
		
		HttpSession session=objRequest_p.getSession();
		session.setAttribute("PatPriCategory", objPatientVO_p.getPatPrimaryCatCode());
		System.out.println("***objPatientVO_p.getPatPrimaryCatCode***"+objPatientVO_p.getPatPrimaryCatCode());
		if(strRenewalType.equals("2"))
			if(objPatientVO_p.getEmgRenewalRequired().equals("2")){
				objEmgPatVisitSUP_p.setEmgRenewalRequired("2");
				objPatientVO_p.setEmgRenewalRequired("2");
			}
		
		objUserVO_p.setTariffID(RegistrationConfig.EMERGENCY_RENEWAL_TARIFF_ID);
		String strRenewalAmountCollected=EmgPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
		
		switch(key)
		{
			case 1: case 2:
			
				if(objPatientVO_p.getEmgRenewalRequired()!=null && objPatientVO_p.getEmgRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE)){
					strAmountCollected=strRenewalAmountCollected;
				}
				else if(objPatientVO_p.getEmgRenewalRequired()!=null && objPatientVO_p.getEmgRenewalRequired().equals("2")){
					objUserVO_p.setTariffID(RegistrationConfig.EMERGENCY_NEW_DEPT_VISIT_TARIFF_ID);
					strAmountCollected=EmgPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
				}
				else
				{
					strAmountCollected="0.00";
				}
				//objPatientVO_p.setPatAmountCollected(strAmountCollected);
				//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
				if(!objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
						!objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
					objEmgPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
					objEmgPatVisitSUP_p.setPatActualAmount(strAmountCollected);
					objEmgPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);

				}
				else
				{
					objEmgPatVisitSUP_p.setPatAmountHospitalWise(RegistrationConfig.PAT_CAT_FREE_FEES);
					objEmgPatVisitSUP_p.setPatActualAmount(strAmountCollected);
					objEmgPatVisitSUP_p.setPatRenewalActualAmount(strRenewalAmountCollected);
				}
				break;				
				
			case 3: case 4:
				//objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
				
				if(objEmgPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on")){
					objUserVO_p.setTariffID(RegistrationConfig.EMERGENCY_NEW_DEPT_VISIT_TARIFF_ID);
					strAmountCollected=EmgPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
					strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")?"0.00":strAmountCollected;
					
					//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
					if(!objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
							!objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
						objEmgPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
						objEmgPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected);
						objEmgPatVisitSUP_p.setPatActualAmount(strAmountCollected);
					}
					else
					{
						objEmgPatVisitSUP_p.setPatAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);
						objEmgPatVisitSUP_p.setPatNewDeptVisitAmount(RegistrationConfig.PAT_CAT_FREE_FEES);
						objEmgPatVisitSUP_p.setPatActualAmount(strAmountCollected);
					}
					
				}
				if(objEmgPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on")){
					objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
					strAmountCollected=EmgPatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
					strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")?"0.00":strAmountCollected;
					
					//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
					if(!objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
							!objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
						//objEmgPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);							
						//Renewal Required Amount
						objEmgPatVisitSUP_p.setPatRenewalAmountDeptWise(strRenewalAmountCollected.equals("-1")?"0.00":strRenewalAmountCollected);
						//objEmgPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objEmgPatVisitSUP_p.setPatRenewalActualAmount(strRenewalAmountCollected);

					}
					else
					{
						//objEmgPatVisitSUP_p.setPatAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);							
						//Renewal Required Amount
						objEmgPatVisitSUP_p.setPatRenewalAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);
						//objEmgPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objEmgPatVisitSUP_p.setPatRenewalActualAmount(strRenewalAmountCollected);

					}
				}
				break;
			default : 
					strAmountCollected="0.00";
					//objEmgPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
					objEmgPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected);
					objEmgPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
					objEmgPatVisitSUP_p.setPatRenewalAmountDeptWise(strAmountCollected);
					//objPatientVO_p.setPatAmountCollected(strAmountCollected);
					objEmgPatVisitSUP_p.setPatActualAmount(strAmountCollected);
					objEmgPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);

		}
		
		
		System.out.println("strAmountCollected :"+strAmountCollected);
		System.out.println("strRenewalAmountCollected :"+strRenewalAmountCollected);
	}
	public static void setPatientDtlByCrno( EmgPatientVisitSUP objEmgPatVisitSUP_p,HttpServletRequest objRequest_p){
		
		System.out.println("EmgPatientVisitUTL :: setPatientDtlByCrno()");
		
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		objPatientVO.setPatCrNo(objEmgPatVisitSUP_p.getPatCrNo());
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC;
		try{
			Date sysDate=(Date)objRequest_p.getSession().getAttribute(Config.SYSDATEOBJECT);
			objEmgPatVisitSUP_p.setSysdate(HelperMethods.getSysdate(sysDate, "dd-MMM-yyyy"));
			objPatientVO=EmgPatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, visitType);
			objPatientVO.setPatPrimaryCatGrp(objPatientVO.getPatPrimaryCatGrpCode());
			objEmgPatVisitSUP_p.setPatPrimaryCatGrpCode(objPatientVO.getPatPrimaryCatGrpCode());
			objEmgPatVisitSUP_p.setEmgRenewalRequired(objPatientVO.getEmgRenewalRequired());
			
			//To Check whether the CRNo is merged or not,Added by Singaravelan on 16-Jul-2015 
			if(objPatientVO.getPatIsMerged()!=null && objPatientVO.getPatIsMerged().equals("2"))
			{
				objEmgPatVisitSUP_p.setAfterGo("0");
				System.out.println("CR No is Not Valid and its Already merged with another CR No");
				throw new HisException("CR No is Not Valid, Already Merged with CR No. " +objPatientVO.getPatMergedMainCrNO() );
			}
			
			if(objPatientVO.getIsCatExpired()!=null && objPatientVO.getIsCatExpired().equals("1"))
				/* #Start					:Sheeldarshi 
				#Modify Date(CR/PRS)	:25thMay'15 
				#Reason					: UAT Test Report Requirements:Requirement 4
				 */	
				//throw new HisException("This Category Is Expired. Kindly Select Another Record or Renew its first.");
				throw new HisException("Patient Category Validity is over. Kindly renew it first.");
				//End
			if("1".equals(objPatientVO.getPatIsDead()))
			{
				objEmgPatVisitSUP_p.setAfterGo("0");
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}			
			
			
								
			//To Fetch the Credit Beneficiary Details regarding the Category Group,Added by Singaravelan on 20-Jul-2015
			if(objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) ||
					objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
				getCreditBeneficiaryDtlForPatCatOnCRNO(objEmgPatVisitSUP_p,objRequest_p);
			}
			
			if(objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO)==null)
				EmgPatientVisitUTL.getMapOfRenewalConfigDtlOnKeyPatCat(objEmgPatVisitSUP_p,objRequest_p);
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			RenewalConfigVO objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(objPatientVO.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");

			objEmgPatVisitSUP_p.setStrRenewalType(objRenewalConfigVO.getStrRenewalType());
			objRenewalConfigVO.setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_CASUAL);
			objPatientVO.setRenewalConfig(objRenewalConfigVO);
			
			// Setting Patient Amount
			setPatAmount(objEmgPatVisitSUP_p, objPatientVO, objUserVO, objRequest_p);
			//Setting Patient QRCode
			setQrCode(objEmgPatVisitSUP_p, objPatientVO, objUserVO, objRequest_p);
			
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			//HttpSession objSession=objRequest_p.getSession();
			
			if(objEmgPatVisitSUP_p.getModeCase().equals("0"))
			{
		 		objEmgPatVisitSUP_p.setOldDepartmentVisit("On");
		 		setOldDepartmentVisitDtl(objEmgPatVisitSUP_p,objRequest_p);
			}
			else if(objEmgPatVisitSUP_p.getModeCase().equals("1"))
			{
				objEmgPatVisitSUP_p.setNewDepartmentVisit("On");
				setNewDepartmentVisitDtl(objEmgPatVisitSUP_p, objRequest_p);
			}
			else if(objEmgPatVisitSUP_p.getModeCase().equals("2"))
			{
				objEmgPatVisitSUP_p.setOldDepartmentVisit("On");
				objEmgPatVisitSUP_p.setNewDepartmentVisit("On");
				setOldDepartmentVisitDtl(objEmgPatVisitSUP_p,objRequest_p);
				setNewDepartmentVisitDtl(objEmgPatVisitSUP_p, objRequest_p);
			}
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
			objEmgPatVisitSUP_p.setErrorMessage("Renewal Required");
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
	   			objEmgPatVisitSUP_p.setAfterGo("0");
	   		//status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
	   		objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDuplicateRecordException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objEmgPatVisitSUP_p.setErrorMessage("Not Eligible For OPD");
			//status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
	   	catch(HisNotAnOPDcaseException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objEmgPatVisitSUP_p.setErrorMessage("Not Eligible For OPD");
			//status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objEmgPatVisitSUP_p.setErrorMessage("Not Eligible For IPD");
			//status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			//e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Not applicable, Patient is Dead");
			if(objEmgPatVisitSUP_p.getErrorMessage()==null || objEmgPatVisitSUP_p.getErrorMessage().equals("")){
			}else{
				objEmgPatVisitSUP_p.setAfterGo("0");
			}
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(HisException e){
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
			if(objEmgPatVisitSUP_p.getErrorMessage()==null || objEmgPatVisitSUP_p.getErrorMessage().equals("")){
			}else{
				objEmgPatVisitSUP_p.setAfterGo("0");
			}
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	
	/**
	 * sets all initial Old Patient Visit Essentials
	 * calls getAllInitialOldDeptVisitEssentials of  EmgPatientVisitDATA
	 * 
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setAllInitialOldEmgPatientVisitEssentials(HttpServletRequest objRequest_p){
		Status status = new Status();
		try{
			//setSysdate(objRequest_p);
			Map mp=EmgPatientVisitDATA.getAllInitialOldDeptVisitEssentials();
			WebUTIL.setMapInSession(mp, objRequest_p,"EmgPatientVisitACTION");
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
	 * calls getAllInitialNewDeptVisitEssentials() of EmgPatientVisitDATA
	 * puts the intial Essentails obtained in session
	 * @param objRequest_p -HttpServletRequest
	 */
		public static void setAllInitialNewDeptVisitEssentials(HttpServletRequest objRequest_p,EmgPatientVisitSUP objEmgPatVisitSUP_p){
			Status  objStatus=new Status();
			// objStatus.add(Status.NEW, "", "");
			try{
			Map mp=EmgPatientVisitDATA.getAllInitialNewDeptVisitEssentials();
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mp, objRequest_p,"EmgPatientVisitACTION");
			}
			catch(HisRegistrationTimingExpiredException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
				e.printStackTrace();
			}
			catch(HisRecordNotFoundException e){
				objEmgPatVisitSUP_p.setErrorMessage("Record Not Found Error");
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
	 * sets old department visit essentials (EmgPatientVisitSUP objEmgPatVisitSUP_p, HttpServletRequest objRequest_p)
	 * getOldDeptVisitEssentials(objEmgPatVisitSUP_p, getUserVO(objRequest_p)) from EmgPatientVisitDATA
	 * @param objEmgPatVisitSUP_p EmgPatientVisitSUP form bean
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setOldDeptVisitEssentials(EmgPatientVisitSUP objEmgPatVisitSUP_p, HttpServletRequest objRequest_p){
		
		Status status = new Status();
		try{
			Map mp=EmgPatientVisitDATA.getOldDeptVisitEssentials(objEmgPatVisitSUP_p, getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mp, objRequest_p,"EmgPatientVisitACTION");
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
	 * sets Episode Details( EmgPatientVisitSUP objEmgPatVisitSUP_p,HttpServletRequest objRequest_p )
	 * calls setPatientStatus( objRequest_p,objPatientVO) of OldEmgPatientVisitUTIL
	 * @param objEmgPatVisitSUP_p EmgPatientVisitSUP form bean
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setEpisodeDetails( EmgPatientVisitSUP objEmgPatVisitSUP_p,String strPatCatCode_p,HttpServletRequest objRequest_p ){		
	Status status = new Status();
		try{
			System.out.println("EmgPatientVisitUTL :: setEpisodeDetails()");
			UserVO objUserVO =getUserVO(objRequest_p);		
			HttpSession objSession=WebUTIL.getSession(objRequest_p);
			PatientVO objPatientVO=(PatientVO)objSession.getAttribute(RegistrationConfig.PATIENT_VO);
			objPatientVO.setPatCrNo(objEmgPatVisitSUP_p.getPatCrNo());
		    EpisodeVO[] objArrEpisodeVO ;
		
			if(objPatientVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD)==false){
				objArrEpisodeVO=EmgPatientVisitDATA.getOldPatientEpisodes(objEmgPatVisitSUP_p.getPatCrNo(), strPatCatCode_p,RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC, objPatientVO.getRenewalConfig().getStrRenewalType(), objUserVO);
				System.out.println("objArrEpisodeVO.length :"+objArrEpisodeVO.length);
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
			}
			
			
		}catch(HisRecordNotFoundException e){
	   		e.printStackTrace();
	   		WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, null);
	   		throw new HisRecordNotFoundException("");
		}
	   	catch(HisApplicationExecutionException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_DA,e.getMessage() ,"");
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HisException();
		}				
	}	
	

	
	
	public static RegistrationSlip preapareSlip(EpisodeVO objArrEpisodeVO[], PatientVO objPatientVO_p, EmgPatientVisitSUP objEmgPatVisitSUP_p, HttpServletRequest objRequest_p) throws ParseException{		
		
		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(objPatientVO_p);
		HelperMethods.populate(regSlip,objPatientVO_p);
		regSlip.setPatAge(objPatientVO_p.getPatAge()+" "+objPatientVO_p.getPatAgeUnit());
		regSlip.setMlcDetail(objPatientVO_p.getMlcDetail().split("\\(")[0]);//  added by warish purpus to get mlc number
		HospitalMstVO hospitalVO=getHospitalVO(objRequest_p);
		regSlip.setHospitalName(hospitalVO.getHospitalName());

		String primaryCatName=objPatientVO_p.getPatPrimaryCat();
		regSlip.setPatPrimaryCat(primaryCatName);
		
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
		
		/***/
		
		RegistrationConfigurationBean objRegConfigbean  = (RegistrationConfigurationBean)WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.Registration_Configuration_Bean);
		regSlip.setIsQRCodePrintFlag(objRegConfigbean.getIsQRCodePrint());
		
		/***/

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
		String patMiddleName=regSlip.getPatMiddleName();

		if(!(patMiddleName==null || patMiddleName.equals(""))){
					patMiddleName=patMiddleName.substring(0,1).toUpperCase();
					regSlip.setPatMiddleName(patMiddleName);
					}
		
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
			  /*      ## 		Modification Log							
			##		Modify Date				:13thMar'15 
			##		Reason	(CR/PRS)		:Display hours in valid upto field in bill print
			##		Modify By				:Sheeldarshi 
		   */
			SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
		    
		    if(expDate[i]!=null&&expDate[i]!="")
		    	expDate[i]=_sdf2.format(_sdf3.parse(expDate[i]));
		    	//expDate[i]=_sdf1.format(_sdf2.parse(expDate[i]));
		    
		    //End
		   
		    
			if(objArrEpisodeVO[i].getPatAmountCollected()!=null && !(objArrEpisodeVO[i].getPatAmountCollected().equals(""))
					&& !(objArrEpisodeVO[i].getPatAmountCollected().equals("0")))
			{
				printType[i]="R";//renewal
			}else
			{
				printType[i]="C";//continuation
			}
			
			workingDays[i]=objArrEpisodeVO[i].getUnitWorkingDays();
			
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
		return regSlip;	
	}	
	
	/**
	 * saves old Patient Visit Details (HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p)
	 * calls saveOldEmgPatientVisit(objPatientVO, objArrEpisodeVO, getUserVO(objRequest_p)) of EmgPatientVisitDATA
	 * @param objRequest_p HttpServletRequest
	 * @param objEmgPatVisitSUP_p EmgPatientVisitSUP form bean
	 */
	public static boolean saveEmgPatientOldDepartmentVisit(HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p,String strMode_p){
		boolean renewStatus=false;
		Status objStatus = new Status();
		 //StringBuilder str=new StringBuilder();
		UserVO objUserVO=getUserVO(objRequest_p);
		
		EpisodeVO[] selectedEpisodeVO=null;
		String strUniqueIdDuplicyFlag = "0";
		String strPrintDivContent="",strBillPrintDiv="";
		try
		{
			System.out.println("EmgPatientVisitUTL :: saveOldEmgPatientVisit()");
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
			EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);		 
			String[] deptToVisit=null;
			
			//String isVisitOnRequest=objEmgPatVisitSUP_p.getOnRequestVisit();
			objPatientVO.setIsReferred(objEmgPatVisitSUP_p.getIsReferred());
			objPatientVO.setPatRenewalActualAmount(objEmgPatVisitSUP_p.getPatRenewalActualAmount());
			
			if(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()!=null && objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length>0)
				deptToVisit=objEmgPatVisitSUP_p.getDepartmentsToVisitStamp();
			else
				deptToVisit=new String[]{objEmgPatVisitSUP_p.getHcode()};
			
			int x=deptToVisit.length;
			int y=arrEpisodeVOSes.length;
								
			EpisodeVO[] objArrEpisodeVO1 = new EpisodeVO[x];
			
			/**Added by Vasu on 18.May.2018 for MLC and Ambulatory Flag for old Dept Visit*/
			String AmbulatoryFlag = objEmgPatVisitSUP_p.getIsAmbulatoryCheckUncheck();
			String mlcFlag = objEmgPatVisitSUP_p.getIsMLCFlag();
			String patPrimaryCatName = objEmgPatVisitSUP_p.getPatPrimaryCat();
			/**End Vasu*/
			for(int i=0; i<y; i++)
			{
				for(int j=0;j<x;j++){
					
					if(arrEpisodeVOSes[i].getEpisodeCode().equals(deptToVisit[j])){
						arrEpisodeVOSes[i].setPatVisitReason(objEmgPatVisitSUP_p.getArrPatVisitReason()[i]);
						arrEpisodeVOSes[i].setCreditBillFlag(objEmgPatVisitSUP_p.getArrCreditBillFlag()[i]);
						arrEpisodeVOSes[i].setSnomdCIdVisitReason(objEmgPatVisitSUP_p.getArrsnomdCIdVisitReason());
						arrEpisodeVOSes[i].setSnomdPTVisitReason(objEmgPatVisitSUP_p.getArrsnomdPTVisitReason());
						
						if(objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
						{
							if("1".equals(objEmgPatVisitSUP_p.getArrCreditBillFlag()[i])){
								//arrEpisodeVOSes[i].setCreditLetterRefNo(objPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
								//arrEpisodeVOSes[i].setCreditLetterDate(objPatVisitSUP_p.getArrCreditLetterDate()[i]);
								arrEpisodeVOSes[i].setCreditLetterRefNo(objEmgPatVisitSUP_p.getCreditLetterRefNo());
								arrEpisodeVOSes[i].setCreditLetterDate(objEmgPatVisitSUP_p.getCreditLetterDate());
								
							}
							else
							{
								
								/*
								arrEpisodeVOSes[i].setCreditLetterRefNo(objEmgPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
								arrEpisodeVOSes[i].setCreditLetterDate(objEmgPatVisitSUP_p.getArrCreditLetterDate()[i]);*/
								arrEpisodeVOSes[i].setCreditLetterRefNo(objEmgPatVisitSUP_p.getCreditLetterRefNo());
								arrEpisodeVOSes[i].setCreditLetterDate(objEmgPatVisitSUP_p.getCreditLetterDate());
							}
							if(arrEpisodeVOSes[i].getRenewalRequired().equalsIgnoreCase("1"))
							{
								objPatientVO.setCreditLetterDate(objEmgPatVisitSUP_p.getCreditLetterDate());
								objPatientVO.setCreditLetterRefNo(objEmgPatVisitSUP_p.getCreditLetterRefNo());
								objPatientVO.setCreditLimit(objEmgPatVisitSUP_p.getCreditLimit());
								objPatientVO.setClientCode(objEmgPatVisitSUP_p.getClientCode());
								objPatientVO.setClientName(objEmgPatVisitSUP_p.getClientName());
								objPatientVO.setStaffCardNo(objEmgPatVisitSUP_p.getStaffCardNo());
								objPatientVO.setStaffCardName(objEmgPatVisitSUP_p.getStaffCardName());
								objPatientVO.setLetterType(objEmgPatVisitSUP_p.getLetterType());
								objPatientVO.setCardvalidityDate(objEmgPatVisitSUP_p.getCardvalidityDate());
								objPatientVO.setRelationWithStaff(objEmgPatVisitSUP_p.getRelationWithStaff());
								objPatientVO.setRelationNameWithStaff(objEmgPatVisitSUP_p.getRelationNameWithStaff());
								
							}

						}
						if(objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) )
						{
							if(arrEpisodeVOSes[i].getRenewalRequired().equalsIgnoreCase("1"))
							{
									objPatientVO.setClientCode(objEmgPatVisitSUP_p.getClientCode());
									objPatientVO.setClientName(objEmgPatVisitSUP_p.getClientName());
									objPatientVO.setAgsNo(objEmgPatVisitSUP_p.getAgsNo());
									objPatientVO.setAgsDistrictCode(objEmgPatVisitSUP_p.getAgsDistrictCode());
									objPatientVO.setAgsCounterNo(objEmgPatVisitSUP_p.getAgsCounterNo());
									objPatientVO.setCreditLimit(objEmgPatVisitSUP_p.getAgsCreditLimit());
								
							}
							
						}
						/**Added by Vasu on 18.May.2018 for MLC and Ambulatory Flag for old Dept Visit*/
						//arrEpisodeVOSes[i].setMlcFlag(objEmgPatVisitSUP_p.getArrMlcFlag()[i]);
						arrEpisodeVOSes[i].setMlcFlag(mlcFlag);
						arrEpisodeVOSes[i].setIsAmbulatory(AmbulatoryFlag);
						arrEpisodeVOSes[i].setPatPrimaryCat(patPrimaryCatName);
						/**End Vasu*/
						
						objArrEpisodeVO1[k]=arrEpisodeVOSes[i];
						//this flag is used to check if visit is forceful
						objArrEpisodeVO1[k].setOnRequestVisit(objEmgPatVisitSUP_p.getOnRequestVisit());
						k++;
					} 
				 }
			}
			//forward visit (onRequestVisit/hiddenEpisode-->visit stamp on objRequest_p)
			//renewal visit (selected objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length)
			//Modified to insert the Amount only for renewal on the whole(hospital wise) by Singaravelan on 07-10-13
			String strRenewalTypeHospOrEpisode="";
			if(objEmgPatVisitSUP_p.getStrRenewalType()!=null && 
					(objEmgPatVisitSUP_p.getStrRenewalType().equals("3") || objEmgPatVisitSUP_p.getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			if(strMode_p.equals("SAVE"))
			{
				selectedEpisodeVO=new EpisodeVO[objArrEpisodeVO1.length];
				System.out.println("objEmgPatVisitSUP_p.getPatAmountHospitalWise() : "+objEmgPatVisitSUP_p.getPatAmountHospitalWise());
				for(int i=0;i<objArrEpisodeVO1.length;i++)
				{
					selectedEpisodeVO[i]=objArrEpisodeVO1[i];
					
					selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
					System.out.println("EpisodeDate :"+objArrEpisodeVO1[i].getEpisodeStartDate());
					selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
					
					if(strRenewalTypeHospOrEpisode.equals("E")){
						if(objArrEpisodeVO1[i].getRenewalRequired().equalsIgnoreCase("1")){
							selectedEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatRenewalAmountDeptWise());
							selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
						}else{
							objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
						}
					}else if(strRenewalTypeHospOrEpisode.equals("H")){
						 if(objPatientVO.getEmgRenewalRequired()!=null && objPatientVO.getEmgRenewalRequired().equals("1")){
							 if(i==0){
								 objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
								 selectedEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatAmountHospitalWise());
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
				if(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()!=null )
				{
					selectedEpisodeVO= new EpisodeVO[objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length];
					//System.out.println(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length);
					//selectedEpisodeVO= new EpisodeVO[objEmgPatVisitSUP_p.getDepartmentsToRenew().length];
					for(int i=0;i<objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
					{
						if(arrEpisodeVOSes!=null)
						{
							for(int j=0;j<arrEpisodeVOSes.length;j++)
							{
								if(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrEpisodeVOSes[j].getEpisodeCode()))
								{
									selectedEpisodeVO[i]=new EpisodeVO();
									HelperMethods.populate(selectedEpisodeVO[i],arrEpisodeVOSes[j]);
									
									selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
									System.out.println("EpisodeDate :"+objArrEpisodeVO1[i].getEpisodeStartDate());
									selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
									
									if(strRenewalTypeHospOrEpisode.equals("E")){
										if(objArrEpisodeVO1[i].getRenewalRequired().equalsIgnoreCase("1")){
											selectedEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatRenewalAmountDeptWise());
											selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
										}else{
											objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
											selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
										}
									}else if(strRenewalTypeHospOrEpisode.equals("H")){
										 if(objPatientVO.getEmgRenewalRequired()!=null && objPatientVO.getEmgRenewalRequired().equals("1")){
											 if(i==0){
												 objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
												 selectedEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatAmountHospitalWise());
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
									selectedEpisodeVO[i].setEpisodeCode(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()[i]);
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
			
			if(objEmgPatVisitSUP_p.getIsReferred()!=null && objEmgPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
			{
				EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
			
				episodeRefVO.setIsRefferInOut(objEmgPatVisitSUP_p.getIsRefferInOut());
	
				if(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null && objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
					objEmgPatVisitSUP_p.setPatRefGnctdHospitalDept(objEmgPatVisitSUP_p.getPatRefHospitalDeptOther());
				}
	
				episodeRefVO.setExternalHospitalDepartment(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept());
				episodeRefVO.setExternalHospitalDepartmentUnit(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
				episodeRefVO.setExternalHospitalDoctorName(objEmgPatVisitSUP_p.getPatRefDoctor());
				episodeRefVO.setExternalHospitalName(objEmgPatVisitSUP_p.getPatRefHospitalName());
				episodeRefVO.setExternalHospitalPatCrNo(objEmgPatVisitSUP_p.getPatRefGnctdHospitalCrno());
				episodeRefVO.setEpisodeCode(objEmgPatVisitSUP_p.getRefferringOPDEpisode());
			
				if(objEmgPatVisitSUP_p.getIsAssociated()!=null && objEmgPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE)) 
					episodeRefVO.setExternalHospitalCode(objEmgPatVisitSUP_p.getPatRefGnctdHospitalCode());
				else if(objEmgPatVisitSUP_p.getIsAssociated()!=null && objEmgPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
					episodeRefVO.setExternalHospitalCode("");
				
				if(arrOpenOPDEpisodeVO!=null)
				{
					for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
						if((objEmgPatVisitSUP_p.getIsRefferInOut()!=null && objEmgPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL) )
								|| (objEmgPatVisitSUP_p.getIsRefferInOut()!=null && objEmgPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))){
							if(episodeRefVO.getEpisodeCode().equals(arrOpenOPDEpisodeVO[i].getEpisodeCode()))
							{
								episodeRefVO.setFromDepartmentCode(arrOpenOPDEpisodeVO[i].getFromDepartmentCode());
								episodeRefVO.setFromDepartmentUnitCode(arrOpenOPDEpisodeVO[i].getFromDepartmentUnitCode());
								episodeRefVO.setEpisodeVisitNo(arrOpenOPDEpisodeVO[i].getEpisodeVisitNo());
								episodeRefVO.setMlcNo(arrOpenOPDEpisodeVO[i].getMlcNo());
							}
						}
				}
				
				if(objEmgPatVisitSUP_p.getIsRefferInOut()!=null && !(objEmgPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))){
					if(episodeRefVO.getFromDepartmentUnitCode().equals(objArrEpisodeVO1[0].getDepartmentUnitCode())){
						renewStatus=true;
						objEmgPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						throw new HisInsertNotAllowedException("Visiting Unit And Reffered Unit Cannot be Same");
					}
				}
			}
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:09thFeb'15 
	 		##		Reason	(CR/PRS)		:To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
	 		##		Modify By				:Sheeldarshi */
				/*CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
				if(objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
				{
				
					HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
					for(int i =0;i<objEmgPatVisitSUP_p.getArrCreditLetterRefNo().length;i++)
					{
						objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
						//objCreditAvailDtlVO.setCreditLetterRefNo(objEmgPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
						objCreditAvailDtlVO.setCreditLetterRefNo(objEmgPatVisitSUP_p.getCreditLetterRefNo());
						strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);
			
						if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
							if(strUniqueIdDuplicyFlag.equals("1")){
								String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
								objEmgPatVisitSUP_p.setErrorMessage(strErrMsg);
								//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
								return false;
							}
						}
					}
					
				}*/
				//End:sheeldarshi	
							
			//////////////////////////////////////////////////////////
			//Added by Vasu on 08.May.2018 to add brought by details into patient revisit process
            PatientBroughtByDtlVO patientBroughtByDtlVO=new PatientBroughtByDtlVO();
             
            if(objEmgPatVisitSUP_p.getIsBroughtBy()!=null){
            	if(objEmgPatVisitSUP_p.getIsBroughtBy().equals(RegistrationConfig.IS_BROUGHT_BY_TRUE)){
            					 HelperMethods.populate(patientBroughtByDtlVO, objEmgPatVisitSUP_p);
            				}
            				}
            				else{
            					patientBroughtByDtlVO=null;
            				}

			EpisodeVO[] objArrEpisodeVO2=null;
			/**Added by Vasu on 18.May.2018 for MLC and Ambulatory Flag for old Dept Visit*/
			objPatientVO.setIsAmbulatory(objEmgPatVisitSUP_p.getIsAmbulatoryCheckUncheck());
			objPatientVO.setIsMLC(objEmgPatVisitSUP_p.getIsMLCFlag());
			/**End Vasu*/
			objArrEpisodeVO2=EmgPatientVisitDATA.saveOldEmgPatientVisit(objPatientVO, selectedEpisodeVO, objUserVO,episodeRefVO,patientBroughtByDtlVO);
			
			objRequest_p.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO2);
			objEmgPatVisitSUP_p.setSaveSuccessful("true");
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
			
			System.out.println("------------To Print------------"+objEmgPatVisitSUP_p.getIsPrintCard()+"-------------------------");
			String tmpFileName=RegistrationConfig.CARD_OLD_DEPT_VISIT+objUserVO.getSeatId();
				
			//String str="";
			//String isPrintCard="1";
			//code for DOT Matrix Printing
			//if(isPrintCard.equals("1"))
			objEmgPatVisitSUP_p.setStrNormalMsg(objEmgPatVisitSUP_p.getStrNormalMsg()+ "\nOld Patient Visit Stamped Successfully");
			RegistrationSlip regSlip=preapareSlip(objArrEpisodeVO2,objPatientVO,objEmgPatVisitSUP_p,objRequest_p);
			strPrintDivContent=NewRegistrationSlip.print(regSlip, tmpFileName, objRequest_p, "RP");
			objEmgPatVisitSUP_p.setPrint("1");
			//objEmgPatVisitSUP_p.setPrintDivContent(strPrintDivContent);
			objEmgPatVisitSUP_p.setPrintDivContent("");
			
			System.out.println("strPrintDivContent (Old) :"+strPrintDivContent);
			
			
			//Bill Receipt Printing 

			if(objArrEpisodeVO2!=null){
				if((objArrEpisodeVO2[0].getPatAmountCollected()!=null)&&!(objArrEpisodeVO2[0].getPatAmountCollected().equals("0"))&&!(objArrEpisodeVO2[0].getPatAmountCollected().equalsIgnoreCase("0.00")))
				{
					strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(objArrEpisodeVO2,objPatientVO,objEmgPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"ODV");
					System.out.println("PrintBillSlip :"+strBillPrintDiv);
					WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
					strPrintDivContent=""+strBillPrintDiv+"";
					objEmgPatVisitSUP_p.setPrintDivContent(strPrintDivContent);
				}
			}
			
			System.out.println("Full PrintDivContent (New) :"+strPrintDivContent);
			
		
		}
		catch(HisInsertNotAllowedException e){
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
			e.printStackTrace();
			
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			//objStatus.add(Status.RECORDFOUND,e.getMessage() ,"");
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());		
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
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
/**
 * sets Patient Status
 * calls  getPatientStatus(objPatientVO, objUserVO) of EmgPatientVisitDATA
 * @param objRequest_p HttpServletRequest
 * @param objPatientVO- Provides Patient details.
 * @return returns patient Status as String
 */
//	public static String setPatientStatus(HttpServletRequest objRequest_p,PatientVO objPatientVO_p){
//		Status  objStatus=new Status();
//		UserVO objUserVO =getUserVO(objRequest_p);
//		
//		String patStatus="";
//		try{
//	    patStatus=EmgPatientVisitDATA.getPatientStatus(objPatientVO_p, objUserVO);
//	    
//		}
//		catch(HisRecordNotFoundException e){
//			objStatus.add(Status.UNSUCESSFULL,"e.getMessage","Records Not Found");	
//		}		
//		catch(HisDataAccessException e){
//			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
//		}
//		catch(HisApplicationExecutionException e){		
//			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisException e){
//			objStatus.add(Status.ERROR,e.getMessage(),"");
//		}		
//		finally
//		{
//			objRequest_p.setAttribute(Config.STATUS_OBJECT,objStatus);
//		 
//		}	
//		WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO_p);
//		return patStatus;
//	}
	
	
	
	
	
	public static void getOpenEpisodeDtl(HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p){
		PatientVO objPatientVO=(PatientVO)WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.PATIENT_VO);
		UserVO objUserVO=getUserVO(objRequest_p);
		Status status=new Status();
		try{
			if(objEmgPatVisitSUP_p.getIsReferred().equals("1")){
			EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=EmgPatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO,"3");
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
			objEmgPatVisitSUP_p.setIsReferred("1");
			objEmgPatVisitSUP_p.setIsRefferInOut("I");
			}
			else{
				objEmgPatVisitSUP_p.setIsReferred("0"); 
			}
			status.add(Status.TRANSINPROCESS);
			}
		catch(HisRecordNotFoundException e){
   		
	   		e.printStackTrace();
	   		objEmgPatVisitSUP_p.setErrorMessage("");
		}
		finally{
			WebUTIL.setStatus(objRequest_p,status);
		}
	}
	
	/*public static boolean renewalOfRegistration(EmgPatientVisitSUP objEmgPatVisitSUP_p,HttpServletRequest objRequest_p){
		boolean renewalStatus=false;
		PatientVO objPatientVO=(PatientVO) WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.PATIENT_VO);   
		Status objStatus =new Status();	
		UserVO objUserVO =getUserVO(objRequest_p);
		EpisodeVO[] selectedEpisodeVO=null;
		EpisodeVO[] arrayEpisodeForStamping=null;
		setSysdate(objRequest_p);
		String sysDate=(String)WebUTIL.getSession(objRequest_p).getAttribute(Config.SYSDATE);
		String amount=(String)WebUTIL.getSession(objRequest_p).getAttribute("amount");
	//	String isVisitOnRequest=objEmgPatVisitSUP_p.getOnRequestVisit();
		try{
			if(RegistrationConfig.RENEWAL_TYPE.equals("3") || RegistrationConfig.RENEWAL_TYPE.equals("4") || RegistrationConfig.RENEWAL_TYPE.equals("5"))	
			{	
				EpisodeVO[] arrRenewalEpisodeVO=(EpisodeVO[])WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.RENEWAL_REQUIRED_EPISODE_ARRAY);
				selectedEpisodeVO= new EpisodeVO[objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length];
				//selectedEpisodeVO= new EpisodeVO[objEmgPatVisitSUP_p.getDepartmentsToRenew().length];
				for(int i=0;i<objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
				{
					if(arrRenewalEpisodeVO!=null)
					{
					for(int j=0;j<arrRenewalEpisodeVO.length;j++)
					{
						if(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrRenewalEpisodeVO[j].getEpisodeCode()))
								
								{
								selectedEpisodeVO[i]=new EpisodeVO();
								HelperMethods.populate(selectedEpisodeVO[i],arrRenewalEpisodeVO[j]);
								amount=(String) WebUTIL.getSession(objRequest_p).getAttribute("amount");
								selectedEpisodeVO[i].setEpisodeCode(objEmgPatVisitSUP_p.getDepartmentsToRenew()[i]);
								selectedEpisodeVO[i].setPatAmountCollected(amount);
								selectedEpisodeVO[i].setSystemIPAddress(objPatientVO.getSystemIPAddress());
								}
					}
					}
				}
				////////new method for combined renewal and stamping//////
				EpisodeVO[] objArrEpisodeVO=(EpisodeVO[])WebUTIL.getSession(objRequest_p).getAttribute( RegistrationConfig.ARRAY_EPISODES_TO_BE_STAMPED);
				EpisodeRefDtlVO episodeRefVO=(EpisodeRefDtlVO)WebUTIL.getSession(objRequest_p).getAttribute( RegistrationConfig.ARRAY_EPISODE_REFER_VO_RENEWAL);
				
				
				arrayEpisodeForStamping=EmgPatientVisitDATA.saveDeptWiseRenewalAndStaming(objPatientVO,selectedEpisodeVO,arrRenewalEpisodeVO,objArrEpisodeVO,episodeRefVO,sysDate,objUserVO);
				
				//////////////////////
				renewalStatus=true;
				objEmgPatVisitSUP_p.setSaveSuccessful("true");
			}
			
			
			///////////////Setting status afetr renewal and stamping///////////////
			if(RegistrationConfig.RENEWAL_TYPE.equals("3") || RegistrationConfig.RENEWAL_TYPE.equals("4") || RegistrationConfig.RENEWAL_TYPE.equals("5"))
			{
			if(renewalStatus==true)
				{
				StringBuilder str=new StringBuilder();
				String patPriCatLable=objPatientVO.getPatPrimaryCat();
				 
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
				 if(objPatientVO.getPatMiddleName()==null)
					 objPatientVO.setPatMiddleName("");
				 if(objPatientVO.getPatLastName()==null)
					 objPatientVO.setPatLastName("");
				 ///////////////////////////////////////////
				 str.append("<br><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>  Name &nbsp;&nbsp;    :: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");str.append(objPatientVO.getPatFirstName());str.append(" ");str.append(objPatientVO.getPatMiddleName());str.append(" ");str.append(objPatientVO.getPatLastName());str.append("</font><br>");
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
				 
				 
				 		 
		//		String tmpFileName=RegistrationConfig.CARD_OLD_DEPT_VISIT+objUserVO.getSeatId();
				objPatientVO.setPatPrimaryCat(patPriCatLable);
				
				//////Changing the print method moving it to NewRegistrationSlip//
				RegistrationSlip regSlip= preapareSlip(arrayEpisodeForStamping,objPatientVO,objEmgPatVisitSUP_p, objRequest_p);
				objRequest_p.getSession().setAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT, regSlip);
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
					printCard(objEmgPatVisitSUP_p, objRequest_p);
					objEmgPatVisitSUP_p.setSaveSuccessful("false");
				}
				objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>Old Department Visit: </font>");
				 }
			}
			///////////////
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			//objStatus.add(Status.NEW,e.getMessage(),"");
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());	
		}catch(HisUpdateUnsuccesfullException e){
					
			objStatus.add(Status.UNSUCESSFULL,"","Update Failed");	
		}		
		catch(HisDataAccessException e){
			
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");
			objEmgPatVisitSUP_p.setErrorMessage("Record Not Found");
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
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
		return renewalStatus;
	}	*/
	
//	public static void getRegistrationAllowed(HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p){
//		
//	//	UserVO objUserVO=getUserVO(objRequest_p);
//		Status status=new Status();
//		try{
//			isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(objRequest_p));
//			/*if(true)
//				throw new HisRegistrationTimingExpiredException("dsdsd");*/
//		}
//		catch (HisRegistrationTimingExpiredException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisRecordNotFoundException e){
//   		
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisDataAccessException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		finally{
//			WebUTIL.setStatus(objRequest_p,status);
//		}
//	}
	
	public static void printCard(EmgPatientVisitSUP fb,HttpServletRequest objRequest_p)
	{
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



	public static void setNewDepartmentVisitDtl(EmgPatientVisitSUP objEmgPatVisitSUP_p, HttpServletRequest objRequest_p) 
	{
		Status  objStatus=new Status();
		
		try{
			System.out.println("EmgPatientVisitUTL :: setNewDepartmentVisitDtl()");
			UserVO objUserVO =getUserVO(objRequest_p);
			PatientVO objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
			HttpSession objSession=objRequest_p.getSession();
			EpisodeRefDtlVO[] arrEpisodeRefPatVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
			//System.out.println("-----------"+arrEpisodeRefPatVO+"-----------");
			//System.out.println("-----------"+objEmgPatVisitSUP_p.getOnlineReferedIndex()+"-----------");
			try{
				setAllNewDeptVisitEssentials(objEmgPatVisitSUP_p,objRequest_p);
			}catch(Exception e){
				//e.printStackTrace();
			}
			setDeptOptions(objRequest_p, objEmgPatVisitSUP_p);
			
			objPatientVO.setPatCrNo(objEmgPatVisitSUP_p.getPatCrNo());
			objPatientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			objPatientVO.setIsPatReferByList(objEmgPatVisitSUP_p.getIsPatReferByList());
			
		
			
			System.out.println("IsPatReferByList :"+objEmgPatVisitSUP_p.getIsPatReferByList());
			if(objEmgPatVisitSUP_p.getIsPatReferByList()!=null && objEmgPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				String fromDepartmentCode="";
				int index=Integer.parseInt(objEmgPatVisitSUP_p.getOnlineReferedIndex());
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartment()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartmentCode()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartmentUnit()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getToDepartmentUnitCode()+"------------");
				//System.out.println("------------"+arrEpisodeRefPatVO[index].getFromDepartmentCode()+"------------");

				objEmgPatVisitSUP_p.setToDepartment(arrEpisodeRefPatVO[index].getToDepartment());
				objEmgPatVisitSUP_p.setToDepartmentCode(arrEpisodeRefPatVO[index].getToDepartmentCode());
				objEmgPatVisitSUP_p.setToDepartmentUnit(arrEpisodeRefPatVO[index].getToDepartmentUnit());
				objEmgPatVisitSUP_p.setToDepartmentUnitCode(arrEpisodeRefPatVO[index].getToDepartmentUnitCode());
				fromDepartmentCode=arrEpisodeRefPatVO[index].getFromDepartmentCode();
				String entryDate=arrEpisodeRefPatVO[index].getEntryDate();
				if(Integer.parseInt(arrEpisodeRefPatVO[index].getEpisodeVisitNo()) >1){
					entryDate="";
				}
				
			}
			
			//Setting Patient Amount

			HelperMethods.populatetToNullOrEmpty(objEmgPatVisitSUP_p,objPatientVO);
			//setPatAmount(objEmgPatVisitSUP_p, objPatientVO, objUserVO, objRequest_p);
			objEmgPatVisitSUP_p.setPatBillAmountWithoutGrouping(objEmgPatVisitSUP_p.getPatAmountCollected());
			objSession=WebUTIL.getSession(objRequest_p);
			objSession.setAttribute("objPatientVO",objPatientVO);
			objSession.setAttribute(RegistrationConfig.PATIENT_VO,objPatientVO);
			//String patStatus=EmgPatientVisitUTL.setPatientStatus( objRequest_p,objPatientVO);
			
			if("1".equals(objPatientVO.getPatIsDead())){
				objEmgPatVisitSUP_p.setAfterGo("0");
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}
			try{
				EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=EmgPatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO,"3");//procedure created
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
			}
			catch(HisRecordNotFoundException e){
				String msg=e.getMessage();
			}
			//List refDeptList=EmgPatientVisitDATA.setRefDepartment(objUserVO);
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL ,refDeptList);
			objStatus.add(Status.TRANSINPROCESS,"","");
		
		
		}
		 catch(HisDeadPatientException e){
		    	//objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		    	objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
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
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
			objStatus.add(Status.ERROR,"Episode Opened in emergency", "");
		}
		catch(HisRecordNotFoundException e){
			System.out.println(e.getMessage());
			objEmgPatVisitSUP_p.setErrorMessage("");
			//objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
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
			WebUTIL.setStatus(objRequest_p,objStatus);
		//	objRequest_p.setAttribute(RegistrationConfig.STATUS_OBJECT,objStatus);
			
		}
		
	}
	/**
	 * sets New DEpartment Visit Essentials
	 * calls getAllNewDeptVisitEssentials() from EmgPatientVisitDATA
	 * sets the Essential in session
	 * @param objEmgPatVisitSUP_p -NewDeptVisitFB form bean
	 * @param objRequest_p -HttpServletRequest
	 */
	public static void setAllNewDeptVisitEssentials( EmgPatientVisitSUP objEmgPatVisitSUP_p,HttpServletRequest objRequest_p){
		
		Status objStatus = new Status();
		Map mp=new HashMap();
		try
		{
			System.out.println("EmgPatientVisitUTL :: setAllNewDeptVisitEssentials()");
			UserVO objUserVO =getUserVO(objRequest_p);
			//added By Mukund on 09.06.2016
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_EMERGENCY);
			
		    mp=EmgPatientVisitDATA.getAllNewEmgDeptVisitEssentials(objEmgPatVisitSUP_p.getPatCrNo(),objUserVO);	    
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
			WebUTIL.setMapInSession(mp,objRequest_p,"EmgPatientVisitACTION");
		}	
	}
	/**
	 * sets Department Options 
	 * retrieves departments to visit stamp from fb
	 * removes those departments from department combo options
	 * by calling removeEntriesfromOptions() from WebUTIL
	 * sets the remaining departments in 
	 * @param objRequest_p -HttpServletRequest
	 * @param objEmgPatVisitSUP_p -NewDeptVisitFB form bean
	 */
	public static void setDeptOptions(HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p){
		System.out.println("EmgPatientVisitUTL :: setDeptOptions()");
		String [] deptVisitArr=	objEmgPatVisitSUP_p.getDepartmentsToVisitStamp();
		HttpSession objSession=objRequest_p.getSession();		
		Collection colOrgDept = (Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_EMG_NEW_DEPT_VISIT_DEPARTMENT);
		//Collection colOrgDept = (Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
		System.out.println("colOrgDept :"+colOrgDept);
		if(colOrgDept!=null){
			Collection newCol = new ArrayList(colOrgDept);
			//<<why returned collection need to be stored
			if(deptVisitArr!=null)
				newCol=WebUTIL.removeEntriesfromOptions(newCol, deptVisitArr);
		    //System.out.println("-------------------"+newCol+"--------------------");
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.EMGREGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
			//objSession.setAttribute(RegistrationConfig.EMGREGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
			System.out.println("newCol :"+newCol);
			///for capturing department specific mandatory fiels
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, newCol);
			//objSession.setAttribute(RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, newCol);
		}else{
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, null);
		}
		
	}

	public static void getRefDept_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		
		try
		{
			String strRefHospCode= (String)objRequest.getParameter("refHospCode");
			String strFlafRefHospOrInst = (String)objRequest.getParameter("flafRefHospOrInst");
			
			UserVO userVO=getUserVO(objRequest);
			
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			if(strFlafRefHospOrInst!=null && strFlafRefHospOrInst.equals("I"))
				strRefHospCode=userVO.getHospitalCode();
			
			List lstRefDept=EmgPatientVisitDATA.getRefDept_AJAX(userVO, strRefHospCode);
			
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
			writeResponse(objResponse, outputString);
		}	
	}


	public static void setOldDepartmentVisitDtl(EmgPatientVisitSUP objEmgPatVisitSUP_p,
			HttpServletRequest objRequest_p) 
	{
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
		try
		{
			System.out.println("EmgPatientVisitUTL:: setOldDepartmentVisitDtl()");
			HttpSession objSession=objRequest_p.getSession();
			
			setEpisodeDetails(objEmgPatVisitSUP_p,objPatientVO.getPatCatCode(),objRequest_p);
			Map referEssDept=EmgPatientVisitDATA.referedEssentialDepartment(objUserVO);  
			WebUTIL.setMapInSession(referEssDept,objRequest_p,"EmgPatientVisitACTION");
			
			try{
				EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=EmgPatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(),  objUserVO, "2");//procedure created
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
			}
			catch(HisRecordNotFoundException e){
				String msg=e.getMessage();
			}
		
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();	   		
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
	   	catch(HisNotAnOPDcaseException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Not Eligible For OPD");
		}
		catch(HisNotAnIPDcaseException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Not Eligible For IPD");
		}
		catch(HisDeadPatientException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Not applicable, Patient is Dead");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
		
	}
	
	
	
	/**
	 * saves the patient new department visit
	 * @param req -HttpServletRequest
	 * @param objEmgPatVisitSUP_p -NewDeptVisitFB form bean
	 */
	public static void saveEmgPatientNewDepartmentVisit(HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p){
		
		Status objStatus=new Status();
		String str="",strBillPrintDiv="";
		try{
			EpisodeRefDtlVO episodeRefVO=new EpisodeRefDtlVO();
			HttpSession objSession=objRequest_p.getSession();
			PatientVO objPatientVO =(PatientVO)objSession.getAttribute(RegistrationConfig.PATIENT_VO);
			//updating patient vo mandatory fields
			PatientVO oldPatientVO=null;
			System.out.println("EmgPatientVisitUTL :: savePatientNewDepartmentVisit()");
			System.out.println("----Dept unit Code---"+objEmgPatVisitSUP_p.getDepartmentUnitCode()+"-----------");
			System.out.println("----Dept To Visit---"+objEmgPatVisitSUP_p.getToDepartment()+"-----------");
			System.out.println("----Dept To Visit---"+objEmgPatVisitSUP_p.getToDepartmentCode()+"-----------");
			
			oldPatientVO=new PatientVO();
			PatientBroughtByDtlVO patientBroughtByDtlVO=new PatientBroughtByDtlVO();
			HelperMethods.populate(oldPatientVO, objPatientVO);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objEmgPatVisitSUP_p);
			
			UserVO objUserVO=getUserVO(objRequest_p);
			
			//gets the hospital information
			getHospitalVO(objRequest_p);
			
			String patPriCatLable=objPatientVO.getPatPrimaryCat();
			objUserVO.setTariffID(RegistrationConfig.EMERGENCY_NEW_DEPT_VISIT_TARIFF_ID);
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			
			if(objEmgPatVisitSUP_p.getStrRenewalType()!=null &&  (objEmgPatVisitSUP_p.getStrRenewalType().equals("1") || objEmgPatVisitSUP_p.getStrRenewalType().equals("2"))){
				if(objEmgPatVisitSUP_p.getEmgRenewalRequired()!=null && objEmgPatVisitSUP_p.getEmgRenewalRequired().equals("1")){
					objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
				}else{
					objUserVO.setTariffID(RegistrationConfig.EMERGENCY_NEW_DEPT_VISIT_TARIFF_ID);
				}
			}
			
			objPatientVO.setDepartmentCode(objEmgPatVisitSUP_p.getDepartmentCode().split("#")[0]);
			objPatientVO.setDepartmentUnitCode(objEmgPatVisitSUP_p.getDepartmentCode().split("#")[1]);
			
			System.out.println("objPatientVO.getDepartmentUnitCode() :"+objPatientVO.getDepartmentUnitCode()+"-----------");
			System.out.println("objPatientVO.getDepartmentCode() :"+objPatientVO.getDepartmentCode()+"-----------");
			
			//String patAmountCollected=EmgPatientVisitDATA.getBillAmount(objPatientVO.getPatPrimaryCatCode(),objUserVO);	
			String patAmountCollected=objEmgPatVisitSUP_p.getPatAmountCollected();
			//For validating the pat amount collected
			if(objEmgPatVisitSUP_p.getPatAmountCollected()==null){
				objEmgPatVisitSUP_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}
			else if(objEmgPatVisitSUP_p.getPatAmountCollected().equals("")){
				objEmgPatVisitSUP_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}
			
			String regCatCode=objPatientVO.getPatRegCatCode();
			String genderCode=objPatientVO.getPatGenderCode();
			String patCrNo=objPatientVO.getPatCrNo();
			//System.out.println("-----------"+objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept()+"-----------");
			if(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null)
				if(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0"))
					objEmgPatVisitSUP_p.setPatRefGnctdHospitalDept(objEmgPatVisitSUP_p.getPatRefHospitalDeptOther());
			
			//HelperMethods.populate(objPatientVO, objEmgPatVisitSUP_p);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objEmgPatVisitSUP_p);
			if(objPatientVO.getIsUnknown().equalsIgnoreCase(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			{
				///removing (unknown) from unknown patient name
				objPatientVO.setPatFirstName(objPatientVO.getPatFirstName().substring(9));
			}
			
			
			objPatientVO.setPatGenderCode(genderCode);
			objPatientVO.setPatCrNo(patCrNo);
			objPatientVO.setPatRegCatCode(regCatCode);
			objPatientVO.setPatSecondaryCatCode("");
			objPatientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_EMERGENCY_CLINIC);
			
			Collection colDept		=(Collection)objSession.getAttribute(RegistrationConfig.EMGREGISTRATIONDESK_OPTION_DEPARTMENT);
			setDepartmentDtl(colDept, objPatientVO, objEmgPatVisitSUP_p.getDepartmentCode());
			
							
			String[] deptToVisit = objEmgPatVisitSUP_p.getDepartmentsToVisitStamp();
			String[] arrFileNo=objEmgPatVisitSUP_p.getArrFileNo();
			EpisodeVO[] objArrEpisodeVO1 = null;
			if(deptToVisit!=null && deptToVisit.length>0 )
				objArrEpisodeVO1 = new EpisodeVO[deptToVisit.length];
			else
				objArrEpisodeVO1 = new EpisodeVO[1];
			
			///////////
			EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
			
			episodeRefVO.setIsRefferInOut(objEmgPatVisitSUP_p.getIsRefferInOut());
			if(objEmgPatVisitSUP_p.getIsRefferInOut()!=null && objEmgPatVisitSUP_p.getIsRefferInOut().equals("I")){
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
			}else if(objEmgPatVisitSUP_p.getIsRefferInOut()!=null && objEmgPatVisitSUP_p.getIsRefferInOut().equals("E")){
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
			}
			
			episodeRefVO.setExternalHospitalDepartment(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept());
			episodeRefVO.setExternalHospitalDepartmentUnit(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
			episodeRefVO.setExternalHospitalDoctorName(objEmgPatVisitSUP_p.getPatRefDoctor());
			episodeRefVO.setExternalHospitalName(objEmgPatVisitSUP_p.getPatRefHospitalName());
			episodeRefVO.setExternalHospitalPatCrNo(objEmgPatVisitSUP_p.getPatRefGnctdHospitalCrno());
			episodeRefVO.setEpisodeCode(objEmgPatVisitSUP_p.getRefferringOPDEpisode());
			
			if(objEmgPatVisitSUP_p.getIsAssociated()!=null && objEmgPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE))
				episodeRefVO.setExternalHospitalCode(objEmgPatVisitSUP_p.getPatRefGnctdHospitalCode());
			else if(objEmgPatVisitSUP_p.getIsAssociated()!=null && objEmgPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
				episodeRefVO.setExternalHospitalCode("");
			else
				episodeRefVO.setExternalHospitalCode("");
			
			if(arrOpenOPDEpisodeVO!=null)
			{
				for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
				{
					if(episodeRefVO.getEpisodeCode()!=null && episodeRefVO.getEpisodeCode().equals(arrOpenOPDEpisodeVO[i].getEpisodeCode()))
					{
						episodeRefVO.setFromDepartmentCode(arrOpenOPDEpisodeVO[i].getFromDepartmentCode());
						episodeRefVO.setFromDepartmentUnitCode(arrOpenOPDEpisodeVO[i].getFromDepartmentUnitCode());
						episodeRefVO.setEpisodeVisitNo(arrOpenOPDEpisodeVO[i].getEpisodeVisitNo());
						episodeRefVO.setMlcNo(arrOpenOPDEpisodeVO[i].getMlcNo());
						episodeRefVO.setFromDepartment(arrOpenOPDEpisodeVO[i].getFromDepartment());
					}
				}
			}
			//////////
			
			
			objPatientVO.setIsReferred(objEmgPatVisitSUP_p.getIsReferred());
			Collection col=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.EMGREGISTRATIONDESK_OPTION_DEPARTMENT);
			String deptName="";
			/**Added by Vasu on 18.May.2018 for MLC and Ambulatory Flag for New Dept Visit*/
			String AmbulatoryFlag = objEmgPatVisitSUP_p.getIsAmbulatoryCheckUncheck();
			String mlcFlag = objEmgPatVisitSUP_p.getIsMLCFlag();
			/**End Vasu*/
			if(deptToVisit==null || deptToVisit.length==0)
			{
				objArrEpisodeVO1 = new EpisodeVO[1];			
				objArrEpisodeVO1[0]=new EpisodeVO();
				
				//objArrEpisodeVO[0].setDepartmentCode(objEmgPatVisitSUP_p.getDepartmentUnitCode().substring(0,3));
				if(objEmgPatVisitSUP_p.getIsPatReferByList()!=null && objEmgPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					objArrEpisodeVO1[0].setDepartmentCode(objEmgPatVisitSUP_p.getToDepartmentCode());
				else{
					objArrEpisodeVO1[0].setDepartmentCode(objPatientVO.getDepartmentCode());
					objArrEpisodeVO1[0].setDepartmentUnitCode(objPatientVO.getDepartmentUnitCode());
					
				}
				
				//objArrEpisodeVO1[0].setFileNo(objEmgPatVisitSUP_p.getFileNo());
				objArrEpisodeVO1[0].setPatAmountCollected(patAmountCollected);
				objArrEpisodeVO1[0].setIsReferred(objEmgPatVisitSUP_p.getIsReferred());
				objArrEpisodeVO1[0].setSnomdCIdVisitReason(objEmgPatVisitSUP_p.getSnomdCIdVisitReason());
				objArrEpisodeVO1[0].setSnomdPTVisitReason(objEmgPatVisitSUP_p.getSnomdPTVisitReason());
				
			//Added by warish for cash combo text value save 13-08-18
							objArrEpisodeVO1[0].setPaymentModeCode(objEmgPatVisitSUP_p.getPaymentModeCode());
							if(!objEmgPatVisitSUP_p.getPaymentModeCode().equals("1"))
								objArrEpisodeVO1[0].setPaymentModeCodeRefId(objEmgPatVisitSUP_p.getPaymentModeRefId());
							//end
				
				if(objEmgPatVisitSUP_p.getIsReferred()!=null && objEmgPatVisitSUP_p.getIsReferred().equals("on")){
					objArrEpisodeVO1[0].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				}
				
				deptName=EmgPatientVisitUTL.getEntryLabel(col,objEmgPatVisitSUP_p.getDepartmentUnitCode());
				objArrEpisodeVO1[0].setDepartment(deptName);
				objArrEpisodeVO1[0].setMlcFlag(objEmgPatVisitSUP_p.getMlcFlag());
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
					//objArrEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatAmountCollected());
					objArrEpisodeVO1[i].setIsReferred(objEmgPatVisitSUP_p.getIsReferred());
					objArrEpisodeVO1[0].setSnomdCIdVisitReason(objEmgPatVisitSUP_p.getSnomdCIdVisitReason());
					objArrEpisodeVO1[0].setSnomdPTVisitReason(objEmgPatVisitSUP_p.getSnomdPTVisitReason());
					
					objArrEpisodeVO1[i].setPatAmountCollected(patAmountCollected);
					
					deptName=EmgPatientVisitUTL.getEntryLabel(col,deptToVisit[i]);
					
					//since department contains department name plus unit name
					//objArrEpisodeVO[i].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
					objArrEpisodeVO1[i].setDepartment(deptName);

                    /**Added by Vasu on 18.May.2018 for MLC and Ambulatory Flag for New Dept Visit*/
					//objArrEpisodeVO1[i].setMlcFlag(objEmgPatVisitSUP_p.getMlcFlag());
					objArrEpisodeVO1[i].setMlcFlag(mlcFlag);
					objArrEpisodeVO1[i].setIsAmbulatory(AmbulatoryFlag);
					/**End Vasu*/
				}
			} 
			String sysdate=(String)objSession.getAttribute(Config.SYSDATE);
			objPatientVO.setSystemDate(sysdate);
			if(objEmgPatVisitSUP_p.getIsReferred()!=null && objEmgPatVisitSUP_p.getIsReferred().equals("on")){
				objPatientVO.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
			}
			
	
			objRequest_p.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE, objEmgPatVisitSUP_p.getDepartmentCode());
			
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
							&& objEmgPatVisitSUP_p.getPatRefGnctdHospitalCode().equals(RegistrationConfig.EXTERNAL_INSTITUTE_PGI)){
						objPatientVO.setPatAmountCollected("0");
						objArrEpisodeVO1[0].setPatAmountCollected("0");
						break;
					}
				}	
			}
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:09thFeb'15 
	 		##		Reason	(CR/PRS)		://To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
	 		##		Modify By				:Sheeldarshi */
			//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
			String strUniqueIdDuplicyFlag = "0";
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			objCreditAvailDtlVO.setCreditLetterRefNo(objEmgPatVisitSUP_p.getCreditLetterRefNo());
			objCreditAvailDtlVO.setCreditLetterDate(objEmgPatVisitSUP_p.getCreditLetterDate());
			objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
			objPatientVO.setClientCode(objEmgPatVisitSUP_p.getClientCode());
			objPatientVO.setClientName(objEmgPatVisitSUP_p.getClientName());
			if(objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatientVO.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
				objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);
		
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
						objEmgPatVisitSUP_p.setErrorMessage(strErrMsg);
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						//return false;
					}
				}
			}
			//End:sheeldarshi	
			/////////////////////////////////////////////////////////////////////////////
			//Added by Vasu on 08.May.18
			if(objEmgPatVisitSUP_p.getIsBroughtBy()!=null){
            	if(objEmgPatVisitSUP_p.getIsBroughtBy().equals(RegistrationConfig.IS_BROUGHT_BY_TRUE)){
            					 HelperMethods.populate(patientBroughtByDtlVO, objEmgPatVisitSUP_p);
            				}
            				}
            				else{
            					patientBroughtByDtlVO=null;
            				}
			//End Vasu
			/**Added by Vasu on 18.May.2018 for MLC and Ambulatory Flag for New Dept Visit*/
			objPatientVO.setIsAmbulatory(objEmgPatVisitSUP_p.getIsAmbulatoryCheckUncheck());
			objPatientVO.setIsMLC(objEmgPatVisitSUP_p.getIsMLCFlag());
			
			for(int i=0;i<objArrEpisodeVO1.length;i++)
			{
				objArrEpisodeVO1[i].setMlcFlag(mlcFlag);
				objArrEpisodeVO1[i].setIsAmbulatory(AmbulatoryFlag);
			}
			/**End Vasu*/
			EpisodeVO [] objArrEpisodeVO2=EmgPatientVisitDATA.saveEmgNewDepartmentVisit(objPatientVO, objArrEpisodeVO1, episodeRefVO, objUserVO,oldPatientVO, objRequest_p,patientBroughtByDtlVO);
			
			objRequest_p.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO1);	
			
		    // StringBuilder str=new StringBuilder();
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.EPISODE_FOR_BARCODE, objArrEpisodeVO2);
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATVO_FOR_BARCODE, objPatientVO);
			BarcodeGenerationUTIL.saveAndCreateBarcodeSlipPrintingDetails(objRequest_p, objArrEpisodeVO2, objPatientVO);
 
		     int arrEpisodeVOLength=objArrEpisodeVO2.length;
			 String[] strQueueNo=new String[arrEpisodeVOLength];
			 String[] strDeptName=new String[arrEpisodeVOLength];
			 String[] strUnitName=new String[arrEpisodeVOLength];
			 String[] strRoomName=new String[arrEpisodeVOLength];
			 
			 
			 for(int i=0;i<arrEpisodeVOLength;i++){
				 strQueueNo[i]=objArrEpisodeVO2[i].getQueNo();
				 strDeptName[i]=objArrEpisodeVO2[i].getDepartment();
				 strUnitName[i]=objArrEpisodeVO2[i].getDepartmentUnit();
				 strRoomName[i]=objArrEpisodeVO2[i].getRoom();
						 	    
			 }
			
			// System.out.println("display string"+str);
			
			String tmpFileName=RegistrationConfig.CARD_NEW_DEPT_VISIT+objUserVO.getSeatId();
			objPatientVO.setPatPrimaryCat(patPriCatLable);
			
			objEmgPatVisitSUP_p.setStrNormalMsg(objEmgPatVisitSUP_p.getStrNormalMsg()+ "\nNew Patient Visit Stamped Successfully");
			str=NewRegistrationSlip.print(preapareSlip(objArrEpisodeVO2,objPatientVO,objEmgPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"DV");
			objEmgPatVisitSUP_p.setPrint("2");   //opd card print 1 to enable 2 to disable
			objEmgPatVisitSUP_p.setPrintDivContent(str);
			System.out.println("PrintDivContent (New) :"+objEmgPatVisitSUP_p.getPrintDivContent());
			//objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divEmgPatientVisitedLabel'>"+"New Department Visit: "+"</div></font>");
			
			
			//Bill Receipt Printing 
			if((objEmgPatVisitSUP_p.getPatAmountCollected()!=null)&&!(objEmgPatVisitSUP_p.getPatAmountCollected().equals("0"))&&!(objEmgPatVisitSUP_p.getPatAmountCollected().equalsIgnoreCase("0.00")))
			{
				strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(objArrEpisodeVO2,objPatientVO,objEmgPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"NDV");
				System.out.println("PrintBillSlip :"+strBillPrintDiv);
				WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
				str+=""+strBillPrintDiv+"";
				objEmgPatVisitSUP_p.setPrintDivContent(str);
			}
			
			System.out.println("Full PrintDivContent (New) :"+str);
			
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
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
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
	/*
	public static void getBillAmountByDeptGrouping(EmgPatientVisitSUP objEmgPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		PatientVO objPatientVO=null;
		HttpSession objSession=objRequest_p.getSession();
		String billAmount="";
		try
		{
			objUserVO.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
			objPatientVO =(PatientVO)objSession.getAttribute(RegistrationConfig.PATIENT_VO);
			if(objPatientVO!=null){
				String entryDate=objEmgPatVisitSUP_p.getEntryDate();
				if(Integer.parseInt(objEmgPatVisitSUP_p.getEpisodeVisitNo())>1)
					entryDate="";
				//billAmount=EmgPatientVisitDATA.getBillAmountByDeptGrouping(objPatientVO.getPatPrimaryCatCode(), objEmgPatVisitSUP_p.getSelectedFromDept(), objEmgPatVisitSUP_p.getDepartmentCode(), objUserVO);
				billAmount=EmgPatientVisitDATA.getBillAmountByDeptGrouping(objPatientVO.getPatPrimaryCatCode(), objEmgPatVisitSUP_p.getSelectedFromDept(), objEmgPatVisitSUP_p.getDepartmentCode(),entryDate, objUserVO);
				objEmgPatVisitSUP_p.setPatAmountCollected(billAmount);
				objEmgPatVisitSUP_p.setReferringInstType("O");
				objEmgPatVisitSUP_p.setReferInternalExternal("I");
			}
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}*/



	public static void saveEmgPatientVisitStamp(HttpServletRequest objRequest_p,
			EmgPatientVisitSUP objEmgPatVisitSUP_p) {
			String[] deptToVisit = objEmgPatVisitSUP_p.getDepartmentsToVisitStamp();
			//objUserVO.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
			//objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			EpisodeVO[] objArrEpisodeVO = new EpisodeVO[deptToVisit.length];		
			objArrEpisodeVO=populateNewDepartmentVisitEpisode(objRequest_p,objEmgPatVisitSUP_p);
			saveEmgPatientVisit(objRequest_p, objEmgPatVisitSUP_p, "SAVE", objArrEpisodeVO);
		
	}

	public static void saveEmgPatientVisit(HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p,String strMode_p,EpisodeVO[] arrNewDeptEpisodeVO){
		
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
			
			getHospitalVO(objRequest_p);
			int k=0;		
			HttpSession session=WebUTIL.getSession(objRequest_p);			
			PatientVO objPatientVO= (PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);	
			PatientBroughtByDtlVO patientBroughtByDtlVO=new PatientBroughtByDtlVO();
			
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			
			EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);		 
			String[] deptToVisit=null;
			
			String regCatCode=objPatientVO.getPatRegCatCode();
			String genderCode=objPatientVO.getPatGenderCode();
			String patCrNo=objPatientVO.getPatCrNo();
			if(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null && objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
				objEmgPatVisitSUP_p.setPatRefGnctdHospitalDept(objEmgPatVisitSUP_p.getPatRefHospitalDeptOther());
			}
			//HelperMethods.populate(objPatientVO, objEmgPatVisitSUP_p);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objEmgPatVisitSUP_p);
			if(objPatientVO.getIsUnknown()!=null && objPatientVO.getIsUnknown().equalsIgnoreCase(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			{
				///removing (unknown) from unknown patient name
				objPatientVO.setPatFirstName(objPatientVO.getPatFirstName().substring(9));
			}
			objPatientVO.setPatGenderCode(genderCode);
			objPatientVO.setPatCrNo(patCrNo);
			objPatientVO.setPatRegCatCode(regCatCode);
			objPatientVO.setPatSecondaryCatCode("");
			objPatientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_EMERGENCY_CLINIC);
			//String isVisitOnRequest=objEmgPatVisitSUP_p.getOnRequestVisit();
			objPatientVO.setIsReferred(objEmgPatVisitSUP_p.getIsReferred());
			if(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length>0)
				deptToVisit=objEmgPatVisitSUP_p.getDepartmentsToVisitStamp();
			else{
				
				///in case if only new department is selected since for old unit was closed
				//hcode is present in case of forward on objRequest_p
				if(objEmgPatVisitSUP_p.getHcode()!=null && !objEmgPatVisitSUP_p.getHcode().equals(""))
				{
					deptToVisit=new String[]{objEmgPatVisitSUP_p.getHcode()};
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
				for(int j=0;j<x;j++)
				{
					if(arrEpisodeVOSes[i].getEpisodeCode().equals(deptToVisit[j])){
						arrEpisodeVOSes[i].setPatVisitReason(objEmgPatVisitSUP_p.getArrPatVisitReason()[i]);
						arrEpisodeVOSes[i].setCreditBillFlag(objEmgPatVisitSUP_p.getArrCreditBillFlag()[i]);
						if("1".equals(objEmgPatVisitSUP_p.getArrCreditBillFlag()[i])){
							arrEpisodeVOSes[i].setCreditLetterRefNo(objEmgPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
							arrEpisodeVOSes[i].setCreditLetterDate(objEmgPatVisitSUP_p.getArrCreditLetterDate()[i]);
						}
						arrEpisodeVOSes[i].setMlcFlag(objEmgPatVisitSUP_p.getArrMlcFlag()[i]);
						objArrEpisodeVO[k]=arrEpisodeVOSes[i];
						//this flag is used to check if visit is forceful
						objArrEpisodeVO[k].setOnRequestVisit(objEmgPatVisitSUP_p.getOnRequestVisit());
						k++;
					} 
				 }
			}
			//forward visit (onRequestVisit/hiddenEpisode-->visit stamp on objRequest_p)
			System.out.println("----New Dept visit-----------"+objEmgPatVisitSUP_p.getNewDepartmentVisit()+"----------------");
			System.out.println("----Old Dept visit-----------"+objEmgPatVisitSUP_p.getOldDepartmentVisit()+"----------------");

			String strRenewalTypeHospOrEpisode="";
			if(objEmgPatVisitSUP_p.getStrRenewalType()!=null && 
					(objEmgPatVisitSUP_p.getStrRenewalType().equals("3") || objEmgPatVisitSUP_p.getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			if(strMode_p.equals("SAVE"))
			{
				selectedEpisodeVO=new EpisodeVO[objArrEpisodeVO.length];
				if(objEmgPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on") && objEmgPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on"))
				{		
					for(int i=0;i<objArrEpisodeVO.length;i++)
					{
						selectedEpisodeVO[i]=objArrEpisodeVO[i];
						
						selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
						selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
						
						if(strRenewalTypeHospOrEpisode.equals("E")){
							if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1")){
								selectedEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatRenewalAmountDeptWise());
								objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
							}else{
								objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							}
						}else if(strRenewalTypeHospOrEpisode.equals("H")){
							// Code For Calculating PatAmount is deleted, because for HospitalWise Amount is already calculated while populating episodeVo at EmgNewDeptVisit (i.e in method populateNewDepartmentVisitEpisode)
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
								selectedEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatRenewalAmountDeptWise());
								objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
							}else{
								objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
								selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							}
						}else if(strRenewalTypeHospOrEpisode.equals("H")){
							// Code For Calculating PatAmount is deleted, because for HospitalWise Amount is already calculated while populating episodeVo at EmgNewDeptVisit (i.e in method populateNewDepartmentVisitEpisode) 
						 }	
						else{
							selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
								
						}
					}
				}
					
			}
			else
			{
				if(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()!=null )
				{
					selectedEpisodeVO= new EpisodeVO[objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length];
					//System.out.println(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length);
					for(int i=0;i<objEmgPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
					{
						if(arrEpisodeVOSes!=null)
						{
							for(int j=0;j<arrEpisodeVOSes.length;j++)
							{
								if(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrEpisodeVOSes[j].getEpisodeCode()))
								{
									selectedEpisodeVO[i]=new EpisodeVO();
										HelperMethods.populate(selectedEpisodeVO[i],arrEpisodeVOSes[j]);
										
										selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
										selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
										
										if(objEmgPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on") && objEmgPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on"))
										{
											if(strRenewalTypeHospOrEpisode.equals("E")){
												if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1")){
													selectedEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatRenewalAmountDeptWise());
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
										}								
										selectedEpisodeVO[i].setEpisodeCode(objEmgPatVisitSUP_p.getDepartmentsToVisitStamp()[i]);
										selectedEpisodeVO[i].setSystemIPAddress(objPatientVO.getSystemIPAddress());
								}
							}
						}
					}
				}
			}
						
			HttpSession objSession=WebUTIL.getSession(objRequest_p);

			EpisodeRefDtlVO episodeRefVO=new EpisodeRefDtlVO();
			
			if(objEmgPatVisitSUP_p.getIsReferred()!=null && objEmgPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
			{
				EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
				
				episodeRefVO.setIsRefferInOut(objEmgPatVisitSUP_p.getIsRefferInOut());
				
				if(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
					objEmgPatVisitSUP_p.setPatRefGnctdHospitalDept(objEmgPatVisitSUP_p.getPatRefHospitalDeptOther());
				}
				
				episodeRefVO.setExternalHospitalDepartment(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDept());
				episodeRefVO.setExternalHospitalDepartmentUnit(objEmgPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
				episodeRefVO.setExternalHospitalDoctorName(objEmgPatVisitSUP_p.getPatRefDoctor());
				episodeRefVO.setExternalHospitalName(objEmgPatVisitSUP_p.getPatRefHospitalName());
				episodeRefVO.setExternalHospitalPatCrNo(objEmgPatVisitSUP_p.getPatRefGnctdHospitalCrno());
				episodeRefVO.setEpisodeCode(objEmgPatVisitSUP_p.getRefferringOPDEpisode());
			
				if(objEmgPatVisitSUP_p.getIsAssociated()!=null && objEmgPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE)) 
					episodeRefVO.setExternalHospitalCode(objEmgPatVisitSUP_p.getPatRefGnctdHospitalCode());
				else if(objEmgPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
					episodeRefVO.setExternalHospitalCode("");
				if(arrOpenOPDEpisodeVO!=null){
					for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
						if(objEmgPatVisitSUP_p.getIsRefferInOut()!=null && (objEmgPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL) || objEmgPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)))	{
						if(episodeRefVO.getEpisodeCode().equals(arrOpenOPDEpisodeVO[i].getEpisodeCode())){
							episodeRefVO.setFromDepartmentCode(arrOpenOPDEpisodeVO[i].getFromDepartmentCode());
							episodeRefVO.setFromDepartmentUnitCode(arrOpenOPDEpisodeVO[i].getFromDepartmentUnitCode());
							episodeRefVO.setEpisodeVisitNo(arrOpenOPDEpisodeVO[i].getEpisodeVisitNo());
							episodeRefVO.setMlcNo(arrOpenOPDEpisodeVO[i].getMlcNo());
						}
					}
				}
				
				if(objEmgPatVisitSUP_p.getIsRefferInOut()!=null && objEmgPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)){
					
					if(objArrEpisodeVO.length!=0)
					{
						if(episodeRefVO.getFromDepartmentUnitCode().equals(objArrEpisodeVO[0].getDepartmentUnitCode())){
							objEmgPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
							throw new HisInsertNotAllowedException("Visiting Unit And Reffered Unit Cannot be Same");
						}
					}
				}
			}
			
							
			//////////////////////////////////////////////////////////
			/*  ## 		Modification Log							
	 		##		Modify Date				:09thFeb'15 
	 		##		Reason	(CR/PRS)		:To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
	 		##		Modify By				:Sheeldarshi */
			String strUniqueIdDuplicyFlag = "0";
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			objCreditAvailDtlVO.setCreditLetterRefNo(objEmgPatVisitSUP_p.getCreditLetterRefNo());
			objCreditAvailDtlVO.setCreditLetterDate(objEmgPatVisitSUP_p.getCreditLetterDate());
			objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
			if(objEmgPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatientVO.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
				objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);
		
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
						objEmgPatVisitSUP_p.setErrorMessage(strErrMsg);
						//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
						//return false;
					}
				}
				for(int i =0;i<objEmgPatVisitSUP_p.getArrCreditLetterRefNo().length;i++)
				{
					objCreditAvailDtlVO.setCreditLetterRefNo(objEmgPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
					strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);
		
					if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
						if(strUniqueIdDuplicyFlag.equals("1")){
							String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
							objEmgPatVisitSUP_p.setErrorMessage(strErrMsg);
							//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
							}
					}
				}
				
			}
			//End:sheeldarshi
			Map episodeMap=new HashMap();
			
			//Added by Vasu on 08.May.18
			if(objEmgPatVisitSUP_p.getIsBroughtBy()!=null){
            	if(objEmgPatVisitSUP_p.getIsBroughtBy().equals(RegistrationConfig.IS_BROUGHT_BY_TRUE)){
            					 HelperMethods.populate(patientBroughtByDtlVO, objEmgPatVisitSUP_p);
            				}
            				}
            				else{
            					patientBroughtByDtlVO=null;
            				}
			//End Vasu
			
			episodeMap=EmgPatientVisitDATA.saveEmgPatientVisit(objPatientVO, selectedEpisodeVO, objUserVO,episodeRefVO,arrNewDeptEpisodeVO, objRequest_p,patientBroughtByDtlVO);
			
			EpisodeVO[] _newEpisodeVO=(EpisodeVO[])episodeMap.get("EMGNEWEPISODELIST");
			EpisodeVO[] _oldEpisodeVO=(EpisodeVO[])episodeMap.get("EMGOLDEPISODELIST");
			
			//objRequest_p.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
			objEmgPatVisitSUP_p.setSaveSuccessful("true");
			
			 
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
					
					//////Changing the print method moving it to NewRegistrationSlip//
					/*for(int i=0;i<_newEpisodeVO.length;i++)
						_newEpisodeVO[i].setPatAmountCollected("0");*/
					
				RegistrationSlip regSlip1=preapareSlip(_newEpisodeVO,objPatientVO,objEmgPatVisitSUP_p,objRequest_p);
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
					
				RegistrationSlip regSlip2=preapareSlip(_oldEpisodeVO,objPatientVO,objEmgPatVisitSUP_p,objRequest_p);
				strPrintDivContent2=NewRegistrationSlip.print(regSlip2, tmpFileName, objRequest_p, "RP");
				
				//strTempPrintDivContent=strPrintDivContent1+strPrintDivContent2;//No need to print the Old Dept Visit Card
				strTempPrintDivContent=strPrintDivContent1;
				//objEmgPatVisitSUP_p.setPrintDivContent(strPrintDivContent1+strPrintDivContent2);
				objEmgPatVisitSUP_p.setPrintDivContent(strTempPrintDivContent);
				objEmgPatVisitSUP_p.setPrint("1");
				System.out.println("Print in Both  :"+objEmgPatVisitSUP_p.getPrintDivContent());
				
				//Both Case Bill Print
				if((objEmgPatVisitSUP_p.getPatAmountCollected()!=null)&&(objEmgPatVisitSUP_p.getPatAmountCollected()!="0")&&!(objEmgPatVisitSUP_p.getPatAmountCollected().equalsIgnoreCase("0.00")))
				{
					strBillPrintDivContent1=NewRegistrationSlip.printBillReceipt(preapareSlip(_newEpisodeVO,objPatientVO,objEmgPatVisitSUP_p,objRequest_p), tmpFileName,objRequest_p,"NDV");
					System.out.println("PrintBillSlip :"+strBillPrintDivContent1);
					strTempPrintDivContent+=""+strBillPrintDivContent1+"";
					objEmgPatVisitSUP_p.setPrintDivContent(strTempPrintDivContent);
				}
				if(_oldEpisodeVO!=null){
					if((_oldEpisodeVO[0].getPatAmountCollected()!=null)&&(_oldEpisodeVO[0].getPatAmountCollected()!="0")&&!(_oldEpisodeVO[0].getPatAmountCollected().equalsIgnoreCase("0.00")))
					{
						strBillPrintDivContent2=NewRegistrationSlip.printBillReceipt(preapareSlip(_oldEpisodeVO,objPatientVO,objEmgPatVisitSUP_p,objRequest_p), tmpFileName,objRequest_p,"ODV");
						System.out.println("PrintBillSlip :"+strBillPrintDivContent2);
						strTempPrintDivContent+=""+strBillPrintDivContent2+"";
						objEmgPatVisitSUP_p.setPrintDivContent(strTempPrintDivContent);
					}
				}
					
			}
				
			
			
		}
		
		catch(HisInsertNotAllowedException e){
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
			e.printStackTrace();
			
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
			//objStatus.add(Status.RECORDFOUND,e.getMessage() ,"");
			
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
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
			HttpServletRequest objRequest_p, EmgPatientVisitSUP objEmgPatVisitSUP_p) {
		
		
		HttpSession objSession=objRequest_p.getSession();
		EpisodeVO[] objArrEpisodeVO =null;
		
		try
		{
			
			System.out.println("EmgPatientVisitUTL :: populateNewDepartmentVisitEpisode()");
			Collection col=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.EMGREGISTRATIONDESK_OPTION_DEPARTMENT);
			String deptName="";
			String[] deptToVisit = new String[0];
			objArrEpisodeVO = new EpisodeVO[deptToVisit.length];
			String patAmountCollected="0";
			
			patAmountCollected= objEmgPatVisitSUP_p.getPatAmountCollected();
			/*if(objEmgPatVisitSUP_p.getStrRenewalType()!=null && (objEmgPatVisitSUP_p.getStrRenewalType().equals("3") || objEmgPatVisitSUP_p.getStrRenewalType().equals("4")))
				patAmountCollected=objEmgPatVisitSUP_p.getPatAmountCollected();
			else
				patAmountCollected=RegistrationConfig.PAT_CAT_FREE_FEES;*/
				
			
			if(deptToVisit==null || deptToVisit.length==0){
				objArrEpisodeVO = new EpisodeVO[1];			
				objArrEpisodeVO[0]=new EpisodeVO();
				System.out.println("-----------------"+objEmgPatVisitSUP_p.getDepartmentUnitCode()+"-------------------");
				//objArrEpisodeVO[0].setDepartmentCode(objEmgPatVisitSUP_p.getDepartmentUnitCode().substring(0,3));
				
				System.out.println("dept code :"+ objEmgPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				System.out.println("dept unit code :"+objEmgPatVisitSUP_p.getDepartmentCode().split("#")[1]);
				
				objArrEpisodeVO[0].setDepartmentCode(objEmgPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				objArrEpisodeVO[0].setDepartmentUnitCode(objEmgPatVisitSUP_p.getDepartmentCode().split("#")[1]);
				objArrEpisodeVO[0].setFileNo(objEmgPatVisitSUP_p.getFileNo());
				objArrEpisodeVO[0].setPatAmountCollected(patAmountCollected);
				objArrEpisodeVO[0].setIsReferred(objEmgPatVisitSUP_p.getIsReferred());
				if(objEmgPatVisitSUP_p.getIsReferred()!=null && objEmgPatVisitSUP_p.getIsReferred().equals("on")){
					objArrEpisodeVO[0].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				}
				if(objEmgPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
					int index=Integer.parseInt(objEmgPatVisitSUP_p.getOnlineReferedIndex());
					deptName=episodeRefDtlVO[index].getToDepartment();
				}else{
				 deptName=EmgPatientVisitUTL.getEntryLabel(col,objEmgPatVisitSUP_p.getDepartmentUnitCode());
				}
				
				
				System.out.println("-----------------"+deptName+"-------------------");
				//since department contains department name plus unit name
				//objArrEpisodeVO[0].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
				objArrEpisodeVO[0].setDepartment(deptName);
				objArrEpisodeVO[0].setMlcFlag(objEmgPatVisitSUP_p.getMlcFlag());
			} 
			else{
				objArrEpisodeVO = new EpisodeVO[deptToVisit.length];
				for(int i=0; i<objArrEpisodeVO.length; i++){
					objArrEpisodeVO[i]=new EpisodeVO();
					objArrEpisodeVO[i].setDepartmentCode(deptToVisit[i]);
					//objArrEpisodeVO[i].setPatAmountCollected(objEmgPatVisitSUP_p.getPatAmountCollected());
					
					objArrEpisodeVO[i].setIsReferred(objEmgPatVisitSUP_p.getIsReferred());
					objArrEpisodeVO[i].setPatAmountCollected(patAmountCollected);
					if(objEmgPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
						int index=Integer.parseInt(objEmgPatVisitSUP_p.getOnlineReferedIndex());
						deptName=episodeRefDtlVO[index].getToDepartment();
					}else{
					 deptName=EmgPatientVisitUTL.getEntryLabel(col,deptToVisit[i]);
					}
					objArrEpisodeVO[i].setDepartment(deptName);
					objArrEpisodeVO[i].setMlcFlag(objEmgPatVisitSUP_p.getMlcFlag());
				}
			} 
			String sysdate=(String)objSession.getAttribute(Config.SYSDATE);
			
			

			objRequest_p.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE, objEmgPatVisitSUP_p.getDepartmentCode());

			
			// setting the registration charge 0 for the department code matched in the configuration
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return objArrEpisodeVO;
	}
	
	public static void printLastOpdCard(EmgPatientVisitSUP objEmgPatVisitSUP_p,HttpServletRequest objRequest_p){	
		
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
				//objEmgPatVisitSUP_p.setPrint("1");
				//objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divEmgPatientVisitedLabel'>"+"Card Printed Successfully"+"</div></font>");
				
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
	public static void getMapOfRenewalConfigDtlOnKeyPatCat(EmgPatientVisitSUP objEmgPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCatGroup=new HashMap();
		RegistrationConfigVO voRegistrationConfig=new RegistrationConfigVO();
		RegistrationConfigurationBean _objRegConfigVO2 = new RegistrationConfigurationBean();
		try
		{
			System.out.println("EmgPatientVisitUTL :: getMapOfRenewalConfigDtlOnKeyPatCat()");
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			
			mapOfRenewalVoOnKeyPatCatGroup=EmgPatientVisitDATA.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
			
			voRegistrationConfig=EmgPatientVisitDATA.getRegistrationConfigDtl(objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
		
			_objRegConfigVO2=EmgPatientVisitDATA.getRegistrationConfigDtl2(objUserVO) ; //objEssentialDAO.getRegistrationConfigDtl2(objUserVO,"1","1");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.Registration_Configuration_Bean,_objRegConfigVO2);
		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
			//objStatus.add(Status.ERROR,"",e.getMessage());
			objEmgPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			objEmgPatVisitSUP_p.setErrorMessage("Record Not Found");
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
				objPatientVO_p.setRoundRobinUnitFlag(strDeptValArray[4]);
				objPatientVO_p.setRoundRobinRoomFlag(strDeptValArray[3]);
				objPatientVO_p.setDepartment(objEntry.getLabel());
				break;
				//return objEntry.getLabel();		
			}
		}		
		//System.out.println("getEntryLabel:  "+)
	}
	
	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			//System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private static void createOptionObject(List<Entry> list,Document responseDocument, String fieldName) 
	{
	 
		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			
			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}

	public static String getEntryLabel(Collection _col,String _value)
	{
		
		Iterator it=_col.iterator();
		while (it.hasNext())
		{
			Entry objEntry=(Entry)it.next();
			if(objEntry.getValue().equals(_value))
				return objEntry.getLabel();		
		}		
		return _value;				
	}
	/* #Start					:Sheeldarshi 
	#Modify Date(CR/PRS)	:22ndMay'15 
	#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	public static void getCashCollectionDetail(HttpServletRequest request,HttpServletResponse response, EmgPatientVisitSUP objPatSup_p) {
		
	
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
	
	public static void getInitEssentials(HttpServletRequest objRequest, HttpServletResponse objResponse, EmgPatientVisitSUP _objEmgPatientVisitSUP) {
		
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
			//Start warish for get cash combo
			List lstPaymentMode = PatientVisitDATA.getPaymentModeList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.PAYMENT_MODE_OPTION_LIST,lstPaymentMode);
			
			List lstPaymentMode_processed = mapPaymentModeListToPatientCategoryEmgRevist(lstPaymentMode, _objEmgPatientVisitSUP);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.PAYMENT_MODE_OPTION_LIST_PROCESSED,lstPaymentMode_processed);
			//end
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,new ArrayList());
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,new ArrayList());
		
		}
		
		
	}
	
	public static void getCreditBeneficiaryDtlForPatCatOnCRNO(EmgPatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p)
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
			//By mukund on 05.02.2019
			if(objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))	
			{
				objPatVisitSUP_p.setStaffCardNo(creditBeneficiaryDtl_VO.getStaffCardNo());
				objPatVisitSUP_p.setCreditLimit(creditBeneficiaryDtl_VO.getCreditLimit());
				objPatVisitSUP_p.setLetterType(creditBeneficiaryDtl_VO.getLetterType());
			}
			else
			{
				objPatVisitSUP_p.setAgsNo(creditBeneficiaryDtl_VO.getStaffCardNo());
				objPatVisitSUP_p.setAgsCreditLimit(creditBeneficiaryDtl_VO.getCreditLimit());
			}
			/*
			if(objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))					
				objPatVisitSUP_p.setStaffCardNo(creditBeneficiaryDtl_VO.getStaffCardNo());
			else
				objPatVisitSUP_p.setAgsNo(creditBeneficiaryDtl_VO.getStaffCardNo());*/
		
			objPatVisitSUP_p.setCreditLetterRefNo(creditBeneficiaryDtl_VO.getCreditLetterRefNo());
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
	//by Mukund for Patient QR Code while revisit
	public static void setQrCode(EmgPatientVisitSUP objEmgPatVisitSUP_p, PatientVO objPatientVO, UserVO objUserVO, HttpServletRequest objRequest_p)
	{
		String strQRCode = EmgPatientVisitDATA.getPatientQRCode(objPatientVO.getPatCrNo(), objUserVO);
		if(strQRCode!=null && !strQRCode.equals(""))
		{
			objPatientVO.setStrQRCode(strQRCode);
		}
	}
	
	public static List mapPaymentModeListToPatientCategoryEmgRevist(List<Entry> lstPaymentMode, EmgPatientVisitSUP objEmgPatVisitSUP_p){
		
		List lstPymntMd_processd = new ArrayList(); 		
		String primCatCode = objEmgPatVisitSUP_p.getPatPrimaryCatCode();
		
		for(Entry entry:lstPaymentMode){
           
			String[] _pmtMdCd = entry.getValue().split("#");//1#11@1^12@11#0
            String pymntMdName = entry.getLabel();
            
            String[] arrPrimCatWidDfltPymntMd = _pmtMdCd[1].replace("^", "#").split("#");
			
            for(int j=0; j<arrPrimCatWidDfltPymntMd.length; j++){
            	if(arrPrimCatWidDfltPymntMd[j].split("@")[0].equals(primCatCode)){
					Entry objEntry = new Entry();
					objEntry.setLabel(pymntMdName);
					if(_pmtMdCd[0].equals(arrPrimCatWidDfltPymntMd[j].split("@")[1]))
						objEntry.setValue(_pmtMdCd[0]+"#1");
					else
						objEntry.setValue(_pmtMdCd[0]+"#0");
					//System.out.println("Entry: " + objEntry);
					lstPymntMd_processd.add(objEntry);
            	}
			}
		}
		if(lstPymntMd_processd.size()<=0){
			Entry objEntry = new Entry();
			objEntry.setLabel("Select Value");
			objEntry.setValue("-1");
			//System.out.println("Entry: " + objEntry);
			lstPymntMd_processd.add(objEntry);
		}
		
        return lstPymntMd_processd;
	}

}//End of class
