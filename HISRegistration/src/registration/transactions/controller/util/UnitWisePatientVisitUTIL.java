package registration.transactions.controller.util;
/**
 * Modified By 	: Mukund Vinayak
 * Date			: 23/May/2016
 * Remarks		: Acting as Spl Clinic Visit Process for AIIMS Patna
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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

import registration.config.RegistrationConfig;
import registration.config.slip.NewRegistrationSlip;
import registration.config.slip.RegistrationSlip;
import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisSQLManualException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import registration.config.Exceptions.HisAppointmentNotAvailableException;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisEpisodeOpenInEmergencyException;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisRenewalRequiredException;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.data.NewRegistrationDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import registration.transactions.controller.data.UnitWisePatientVisitDATA;
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
import vo.registration.BeneficiaryPatientVO;
import vo.registration.CreditAvailDetailVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RenewalConfigVO;
import vo.registration.RegistrationConfigVO;

public class UnitWisePatientVisitUTIL extends ControllerUTIL
{	
	public static void setPatAmount(PatientVisitSUP objPatVisitSUP_p,PatientVO objPatientVO_p,UserVO objUserVO_p, HttpServletRequest objRequest_p ){
		String	strRenewalType =objPatientVO_p.getRenewalConfig().getStrRenewalType();
		String strAmountCollected="";
		HttpSession session = objRequest_p.getSession(); 
		if(strRenewalType==null || strRenewalType.equals("0"))
			strRenewalType="0";
		int key= Integer.parseInt(strRenewalType);
		System.out.println("strRenewalType :"+key);
		System.out.println("opdRenewalRequired :"+ objPatVisitSUP_p.getOpdRenewalRequired());
		
		if(strRenewalType.equals("2"))
			if(objPatientVO_p.getOpdRenewalRequired().equals("2")){
				objPatientVO_p.setOpdRenewalRequired("2");
				objPatVisitSUP_p.setOpdRenewalRequired("2");
			}
		
		objUserVO_p.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
		String strRenewalAmountCollected=UnitWisePatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
		
		//By Mukund on 26.08.2016
		String strSplVstAmount="";
		objUserVO_p.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
		strSplVstAmount=UnitWisePatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
		strSplVstAmount = strSplVstAmount==null || strSplVstAmount.equals("") ?"0.00":strSplVstAmount;
		//End
		
		session.setAttribute("PatsPrimaryCatCode", objPatientVO_p.getPatPrimaryCatCode());
		System.out.println("PatsPrimaryCatCode"+objPatientVO_p.getPatPrimaryCatCode());

		if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1") &&
				objPatientVO_p.getOtherHospitalDataFound()!=null && objPatientVO_p.getOtherHospitalDataFound().equals("0")){
			
			strAmountCollected="0.00";
			objPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
			objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
			objPatVisitSUP_p.setPatRenewalAmountDeptWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
			objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
			objPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);
		}
		else{
			switch(key)
			{
				case 1: case 2://for hospital wise
				
					if(objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE)){
						strAmountCollected=strRenewalAmountCollected;
					}
					else if(objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equals("2")){
						objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
						strAmountCollected=UnitWisePatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
					}
					else
					{	
						strAmountCollected="0.00";
					}
					//objPatientVO_p.setPatAmountCollected(strAmountCollected);
					//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
					if(!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
							!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
						objPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
						objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);
						//By Mukund on 26.08.2016
						objPatVisitSUP_p.setPatNewDeptVisitAmountSpl(strSplVstAmount.equals("-1")?"0.00":strSplVstAmount);
						objPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
					}
					else
					{
						objPatVisitSUP_p.setPatAmountHospitalWise(RegistrationConfig.PAT_CAT_FREE_FEES);
						objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);
						//By Mukund on 26.08.2016
						objPatVisitSUP_p.setPatNewDeptVisitAmountSpl(strSplVstAmount.equals("-1")?"0.00":strSplVstAmount);
						objPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
					}
					break;
					
				case 3: case 4://for dept wise (in same hospital)
					//objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
					
					if(objPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on")){
						objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
						strAmountCollected=UnitWisePatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
						strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")?"0.00":strAmountCollected;
						
						//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
						if(!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
								!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
							//By Mukund 08.12.2016
							/*
							objPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
							objPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected);
							objPatVisitSUP_p.setPatActualAmount(strAmountCollected);*/
							
							objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
							objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatNewDeptVisitAmountSpl(strSplVstAmount.equals("-1")?"0.00":strSplVstAmount);
							objPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
						
						}
						else
						{
							/*objPatVisitSUP_p.setPatAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);
							objPatVisitSUP_p.setPatNewDeptVisitAmount(RegistrationConfig.PAT_CAT_FREE_FEES);
							objPatVisitSUP_p.setPatActualAmount(strAmountCollected);*/
							

							objPatVisitSUP_p.setPatAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);
							objPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected.equals("-1")?"0.00":strAmountCollected);
							objPatVisitSUP_p.setPatActualAmount(strSplVstAmount);
							objPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatNewDeptVisitAmountSpl(strSplVstAmount.equals("-1")?"0.00":strSplVstAmount);
							//End:Mukund 08.12.2016
						}
						
					}
					if(objPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on")){
						objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
						strAmountCollected=UnitWisePatientVisitDATA.getBillAmount(objPatientVO_p.getPatPrimaryCatCode(),objUserVO_p);
						strAmountCollected =strAmountCollected==null || strAmountCollected.equals("")?"0.00":strAmountCollected;
						
						//To Show the Amount as Credit Logic cases added by Singaravelan on 01-Oct-2014
						if(!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) && 
								!objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
							//objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);							
							//Renewal Required Amount
							objPatVisitSUP_p.setPatRenewalAmountDeptWise(strRenewalAmountCollected.equals("-1")?"0.00":strRenewalAmountCollected);
							//objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatRenewalActualAmount(strRenewalAmountCollected);

						}
						else
						{
							//objPatVisitSUP_p.setPatAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);							
							//Renewal Required Amount
							objPatVisitSUP_p.setPatRenewalAmountDeptWise(RegistrationConfig.PAT_CAT_FREE_FEES);
							//objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
							objPatVisitSUP_p.setPatRenewalActualAmount(strRenewalAmountCollected);
						}
						
						
					}
					break;
				default : 
						strAmountCollected="0.00";
						//objPatVisitSUP_p.setPatAmountCollected(strAmountCollected);
						objPatVisitSUP_p.setPatAmountHospitalWise(strAmountCollected);
						objPatVisitSUP_p.setPatAmountDeptWise(strAmountCollected);
						objPatVisitSUP_p.setPatRenewalAmountDeptWise(strAmountCollected);
						//objPatientVO_p.setPatAmountCollected(strAmountCollected);
						objPatVisitSUP_p.setPatActualAmount(strAmountCollected);
						objPatVisitSUP_p.setPatRenewalActualAmount(strAmountCollected);
						objPatVisitSUP_p.setPatNewDeptVisitAmountSpl(strSplVstAmount.equals("-1")?"0.00":strSplVstAmount);
						objPatVisitSUP_p.setPatNewDeptVisitAmount(strAmountCollected.equals("-1")?"0.00":strAmountCollected);


			}
		}
		
		
		System.out.println("strAmountCollected :"+strAmountCollected);
		System.out.println("strRenewalAmountCollected :"+strRenewalAmountCollected);
	}
	public static void setPatientDtlByCrno( PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p){
		
		System.out.println("UnitWisePatientVisitUTIL :: setPatientDtlByCrno()");
		
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		objPatientVO.setPatCrNo(objPatVisitSUP_p.getPatCrNo());
		String hospitalCode=objUserVO.getHospitalCode();
		if(hospitalCode!=null){
			//To Check the Hospital Code in CRNO on the Basis hosCode Length Added by Singaravelan on 09-Jan-2015
			if(hospitalCode.length()==5){
				if(!hospitalCode.equals(objPatientVO.getPatCrNo().substring(0,5))){
						objPatientVO.setOtherHospitalFlag("1");
						objPatVisitSUP_p.setOtherHospitalFlag("1");
				}
				else{
					objPatientVO.setOtherHospitalFlag("0");
					objPatVisitSUP_p.setOtherHospitalFlag("0");
				}
			}
			else{
				if(!hospitalCode.equals(objPatientVO.getPatCrNo().substring(0,3))){
					objPatientVO.setOtherHospitalFlag("1");
					objPatVisitSUP_p.setOtherHospitalFlag("1");
				}
				else{
					objPatientVO.setOtherHospitalFlag("0");
					objPatVisitSUP_p.setOtherHospitalFlag("0");
				}
				
			}
		}
		else{
			objPatientVO.setOtherHospitalFlag("0");
			objPatVisitSUP_p.setOtherHospitalFlag("0");
		}
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
		try{
			objPatientVO=UnitWisePatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, visitType);
			//delete below line
			System.out.println("PatPrimaryCatGrpCode :"+objPatientVO.getPatPrimaryCatGrpCode());
			//objPatientVO.setPatPrimaryCatGrpCode(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY);
			objPatVisitSUP_p.setPatPrimaryCatGrpCode(objPatientVO.getPatPrimaryCatGrpCode());
			if(objPatientVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD))
			{
				objPatVisitSUP_p.setAfterGo("0");
				System.out.println("Patient Is Dead");
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}
			//To Check whether the CRNo is merged or not,Added by Singaravelan on 16-Jul-2015 
			if(objPatientVO.getPatIsMerged()!=null && objPatientVO.getPatIsMerged().equals("2"))
			{
				objPatVisitSUP_p.setAfterGo("0");
				System.out.println("CR No is Not Valid and its Already merged with another CR No");
				throw new HisException("CR No is Not Valid, Already Merged with CR No. " +objPatientVO.getPatMergedMainCrNO() );
			}
			
			//To Check whether the Patient is Admitted in Other Hospital or not,Added by Singaravelan on 26-Nov-2014 
			UnitWisePatientVisitUTIL.getAddmittedDetailsOnCRNO(objPatVisitSUP_p, objRequest_p);
			if(objPatVisitSUP_p.getIsPatAdmitted()!=null && objPatVisitSUP_p.getIsPatAdmitted().equals("1"))
				throw new HisException("Patient is Already Admitted in Another Hospital");
			
			//To Fetch the Credit Beneficiary Details regarding the Category Group
			if(objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) ||
					objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
				UnitWisePatientVisitUTIL.getCreditBeneficiaryDtlForPatCatOnCRNO(objPatVisitSUP_p,objRequest_p);
			}
			
			objPatVisitSUP_p.setOpdRenewalRequired(objPatientVO.getOpdRenewalRequired());
			
			if(objPatientVO.getIsCatExpired()!=null && objPatientVO.getIsCatExpired().equals("1"))
				throw new HisException("This Category Is Expired. Kindly Select Another Record or Renew its first.");
			
			if(objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO)==null)
				UnitWisePatientVisitUTIL.getMapOfRenewalConfigDtlOnKeyPatCat(objPatVisitSUP_p,objRequest_p);
			
			
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			RenewalConfigVO objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(objPatientVO.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");

			objPatVisitSUP_p.setStrRenewalType(objRenewalConfigVO.getStrRenewalType());
			objRenewalConfigVO.setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_OPD);
			objPatientVO.setRenewalConfig(objRenewalConfigVO);
			
			//Deepika Code
			////////////////////////hardcoded!!!
			//objPatientVO.setOtherHospitalFlag("1");
			//objPatientVO.setOtherHospitalDataFound("1");
			objPatVisitSUP_p.setOtherHospitalDataFound(objPatientVO.getOtherHospitalDataFound());
			/////////////
			
			// Setting Patient Amount
			setPatAmount(objPatVisitSUP_p, objPatientVO, objUserVO, objRequest_p);
			
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			//HttpSession objSession=objRequest_p.getSession();
			
			if(objPatVisitSUP_p.getModeCase().equals("0"))
			{
		 		
		 		objPatVisitSUP_p.setOldDepartmentVisit("On");
		 		setOldDepartmentVisitDtl(objPatVisitSUP_p,objRequest_p);
			}
			else if(objPatVisitSUP_p.getModeCase().equals("1"))
			{
				
				objPatVisitSUP_p.setNewDepartmentVisit("On");
				setNewDepartmentVisitDtl(objPatVisitSUP_p, objRequest_p);
			}
			else if(objPatVisitSUP_p.getModeCase().equals("2"))
			{
				if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					//System.out.println("---------"+objPatVisitSUP_p.getSelectedReferal()+"----------");
					if(objPatVisitSUP_p.getSelectedReferal().equalsIgnoreCase("O"))
					{
						objPatVisitSUP_p.setOldDepartmentVisit("On");
						setOldDepartmentVisitDtl(objPatVisitSUP_p,objRequest_p);
					}
					else
					{
						objPatVisitSUP_p.setNewDepartmentVisit("On");
						setNewDepartmentVisitDtl(objPatVisitSUP_p, objRequest_p);
					}

				}
				else
				{
					objPatVisitSUP_p.setOldDepartmentVisit("On");
					objPatVisitSUP_p.setNewDepartmentVisit("On");
					setOldDepartmentVisitDtl(objPatVisitSUP_p,objRequest_p);
					setNewDepartmentVisitDtl(objPatVisitSUP_p, objRequest_p);
				}
						
			}
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
			objPatVisitSUP_p.setErrorMessage("Renewal Required");
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
	   			objPatVisitSUP_p.setAfterGo("0");
	   		//status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
	   		objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDuplicateRecordException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objPatVisitSUP_p.setErrorMessage("Not Eligible For OPD");
			//status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
	   	catch(HisNotAnOPDcaseException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objPatVisitSUP_p.setErrorMessage("Not Eligible For OPD");
			//status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		
		catch(HisNotAnIPDcaseException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			objPatVisitSUP_p.setErrorMessage("Not Eligible For IPD");
			//status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Not applicable, Patient is Dead");
			if(objPatVisitSUP_p.getErrorMessage()==null || objPatVisitSUP_p.getErrorMessage().equals("")){
			}else{
				objPatVisitSUP_p.setAfterGo("0");
			}
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(HisException e){
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
			if(objPatVisitSUP_p.getErrorMessage()==null || objPatVisitSUP_p.getErrorMessage().equals("")){
			}else{
				objPatVisitSUP_p.setAfterGo("0");
			}
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	
	
	
	/**
	 * sets Patient Details By Name
	 * @param objPatVisitSUP_p PatientVisitSUP form bean
	 * @param objRequest_p HttpServletRequest
	 */
	
	
	/*public static void setPatientDtlByName( PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p){
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO[] objPatientVO=new PatientVO[]{};
		String searchName=objPatVisitSUP_p.getSearchName();
		
		objPatientVO=PatientVisitDATA.getPatientDtlByName(searchName,objUserVO);
		
		HttpSession objSession=objRequest_p.getSession();
		objSession.setAttribute(RegistrationConfig.PATIENT_VO,objPatientVO);
	}*/
	/**
	 * sets all initial Old Patient Visit Essentials
	 * calls getAllInitialOldDeptVisitEssentials of  PatientVisitDATA
	 * 
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setAllInitialOldPatientVisitEssentials(HttpServletRequest objRequest_p){
		Status status = new Status();
		try{
			//setSysdate(objRequest_p);
			Map mp=UnitWisePatientVisitDATA.getAllInitialOldDeptVisitEssentials();
			WebUTIL.setMapInSession(mp, objRequest_p,"PatientUnitWiseVisitACTION");
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
	 * calls getAllInitialNewDeptVisitEssentials() of PatientVisitDATA
	 * puts the intial Essentails obtained in session
	 * @param objRequest_p -HttpServletRequest
	 */
		public static void setAllInitialNewDeptVisitEssentials(HttpServletRequest objRequest_p,PatientVisitSUP objPatVisitSUP_p){
			Status  objStatus=new Status();
			// objStatus.add(Status.NEW, "", "");
			try{
			Map mp=UnitWisePatientVisitDATA.getAllInitialNewDeptVisitEssentials();
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mp, objRequest_p,"PatientUnitWiseVisitACTION");
			}
			catch(HisRegistrationTimingExpiredException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
				e.printStackTrace();
			}
			catch(HisRecordNotFoundException e){
				objPatVisitSUP_p.setErrorMessage("Record Not Found Error");
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
	 * sets old department visit essentials (PatientVisitSUP objPatVisitSUP_p, HttpServletRequest objRequest_p)
	 * getOldDeptVisitEssentials(objPatVisitSUP_p, getUserVO(objRequest_p)) from PatientVisitDATA
	 * @param objPatVisitSUP_p PatientVisitSUP form bean
	 * @param objRequest_p HttpServletRequest
	 */
	public static void setOldDeptVisitEssentials(PatientVisitSUP objPatVisitSUP_p, HttpServletRequest objRequest_p){
		
		Status status = new Status();
		try{
			Map mp=UnitWisePatientVisitDATA.getOldDeptVisitEssentials(objPatVisitSUP_p, getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mp, objRequest_p,"PatientUnitWiseVisitACTION");
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
	 * sets Episode Details( PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p )
	 * calls setPatientStatus( objRequest_p,objPatientVO) of OldPatientVisitUTIL
	 * @param objPatVisitSUP_p PatientVisitSUP form bean
	 * @param objRequest_p HttpServletRequest
	 */
	public static EpisodeVO[] setEpisodeDetails( PatientVisitSUP objPatVisitSUP_p,String strPatCatCode,HttpServletRequest objRequest_p ){		
	Status status = new Status();
	 EpisodeVO[] objArrEpisodeVO={};
		try{
			System.out.println("UnitWisePatientVisitUTIL :: setEpisodeDetails()");
			UserVO objUserVO =getUserVO(objRequest_p);		
			HttpSession objSession=WebUTIL.getSession(objRequest_p);
			PatientVO objPatientVO=(PatientVO)objSession.getAttribute(RegistrationConfig.PATIENT_VO);
			objPatientVO.setPatCrNo(objPatVisitSUP_p.getPatCrNo());
		  //  EpisodeVO[] objArrEpisodeVO ;
		
			if(objPatientVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD)==false){
				//objArrEpisodeVO=UnitWisePatientVisitDATA.getOldPatientEpisodes(objPatVisitSUP_p.getPatCrNo(),strPatCatCode, RegistrationConfig.EPISODE_VISIT_TYPE_OPD, objPatientVO.getRenewalConfig().getStrRenewalType(), objUserVO);
				//Modified By Mukund  Vinayak on 17.06.2016 for fetching the information registration configuration detail wise
				RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
				objArrEpisodeVO=UnitWisePatientVisitDATA.getOldPatientEpisodes2(objPatVisitSUP_p.getPatCrNo(),strPatCatCode, RegistrationConfig.EPISODE_VISIT_TYPE_OPD, objPatientVO.getRenewalConfig().getStrRenewalType(), objUserVO,objRegistrationConfigVO);
				System.out.println("objArrEpisodeVO.length :"+objArrEpisodeVO.length);
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
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
		finally
		{
			return objArrEpisodeVO;
		}
	}	
	

	
	
	public static RegistrationSlip preapareSlip(EpisodeVO objArrEpisodeVO[], PatientVO objPatientVO_p, PatientVisitSUP objPatVisitSUP_p, HttpServletRequest objRequest_p){		
		
		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(objPatientVO_p);
		HelperMethods.populate(regSlip,objPatientVO_p);
		regSlip.setPatAge(objPatientVO_p.getPatAge()+" "+objPatientVO_p.getPatAgeUnit());
		regSlip.setMlcDetail(objPatientVO_p.getMlcDetail().split("\\(")[0]);//  added by warish purpus to get mlc number
		HospitalMstVO hospitalVO=getHospitalVO(objRequest_p);
		
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		regSlip.setHospitalAddress1(hospitalVO.getAddress1());
		regSlip.setHospitalAddress2(hospitalVO.getAddress2());
		regSlip.setHospitalCity(hospitalVO.getCity());
		regSlip.setHospitalDistrict(hospitalVO.getDistrictName());
		regSlip.setHospitalState(hospitalVO.getStateName());
		regSlip.setHospitalpinCode(hospitalVO.getPinCode());
		regSlip.setHospitalPhone(hospitalVO.getPhone());
		regSlip.setHospitalFax(hospitalVO.getFax());
		regSlip.setHospitalEmail(hospitalVO.getEmail());

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
		
		//added by Mukund On 06.May.2016 for setting the Episode Visit Type
		String episodeVisitType[]=new String[episodeVOLength];
		
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
		
		String billNo[]=new String[objArrEpisodeVO.length];
		String patBillAmountCollected[]=new String[objArrEpisodeVO.length];


		String patMiddleName=regSlip.getPatMiddleName();

		if(!(patMiddleName==null || patMiddleName.equals(""))){
					//patMiddleName=patMiddleName.substring(0,1).toUpperCase();
					regSlip.setPatMiddleName(patMiddleName);
					}
		
		////Address For card
		String cityLocation="";
		String district="";
		String state="";
	
		String country=objPatientVO_p.getPatAddCountry();
		
		cityLocation=objPatientVO_p.getPatAddCity();
		if(objPatientVO_p.getPatAddDistrictCode()==null || objPatientVO_p.getPatAddDistrictCode().equals(""))
			district=objPatientVO_p.getPatAddDistrict();
		else
			district=objPatientVO_p.getPatAddDistrict();
		
			state=objPatientVO_p.getPatAddState();
		
		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		
		
		String address=objPatientVO_p.getPatAddHNo();
		address=address+" "+objPatientVO_p.getPatAddStreet();
		
		address=address+" " +objPatientVO_p.getPatAddCity();
		if(district.equals("-1"))
			district="";
			String address2=district;
	
		regSlip.setPatDistrict(district);	
			
		if(!address2.equals(""))
			address2=address2+","+state;
		else
			address2=state;
		
			address2=address2+","+country;
			if(objPatientVO_p.getPatAddPIN() != null && !objPatientVO_p.getPatAddPIN().equals(""))
			address2=address2+"-"+objPatientVO_p.getPatAddPIN();
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
			billNo[i]=objArrEpisodeVO[i].getBillNo();
			patBillAmountCollected[i]=objArrEpisodeVO[i].getPatAmountCollected();
			
			if(objArrEpisodeVO[i].getPatAmountCollected()!=null && !(objArrEpisodeVO[i].getPatAmountCollected().equals(""))
					&& !(objArrEpisodeVO[i].getPatAmountCollected().equals("0")))
			{
				printType[i]="R";//renewal
			}else
			{
				printType[i]="C";//continuation
			}
			
			//workingDays[i]=objArrEpisodeVO[i].getUnitWorkingDays();
			
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
			
			String[] temp=objArrEpisodeVO[i].getUnitWorkingDays().split("#");
			if(temp.length==0)
				temp=new String[]{"",""};
			System.out.println("temp[0]"+temp[0]);
			//System.out.println("temp[1]"+temp[1]);
			workingDays[i]=temp[0];
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
		regSlip.setBillNo(billNo);
		regSlip.setBillAmount(patBillAmountCollected);
		
		//added by Mukund On 06.May.2016 for episode visit type
		regSlip.setEpisodeVisitType(objArrEpisodeVO[0].getEpisodeVisitType());
		
		
		return regSlip;	
	}	
	
	/**
	 * saves old Patient Visit Details (HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p)
	 * calls saveOldPatientVisit(objPatientVO, objArrEpisodeVO, getUserVO(objRequest_p)) of PatientVisitDATA
	 * @param objRequest_p HttpServletRequest
	 * @param objPatVisitSUP_p PatientVisitSUP form bean
	 */
	public static boolean saveOldPatientVisit(HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p,String strMode_p){
		boolean renewStatus=false;
		Status objStatus = new Status();
		 //StringBuilder str=new StringBuilder();
		UserVO objUserVO=getUserVO(objRequest_p);
		
		EpisodeVO[] selectedEpisodeVO=null;
		String strPrintDivContent="",strBillPrintDiv="";
		String strUniqueIdDuplicyFlag = "0";
		try
		{
			System.out.println("UnitWisePatientVisitUTIL :: saveOldPatientVisit()");
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
			
			//String isVisitOnRequest=objPatVisitSUP_p.getOnRequestVisit();
			objPatientVO.setIsReferred(objPatVisitSUP_p.getIsReferred());
			objPatientVO.setPatRenewalActualAmount(objPatVisitSUP_p.getPatRenewalActualAmount());
			objPatientVO.setRegRenewalRequired(objPatVisitSUP_p.getRegRenewalRequired());
			if(objPatVisitSUP_p.getDepartmentsToVisitStamp()!=null && objPatVisitSUP_p.getDepartmentsToVisitStamp().length>0)
				deptToVisit=objPatVisitSUP_p.getDepartmentsToVisitStamp();
			else
				deptToVisit=new String[]{objPatVisitSUP_p.getHcode()};
			
			int x=deptToVisit.length;
			int y=arrEpisodeVOSes.length;
								
			EpisodeVO[] objArrEpisodeVO1 = new EpisodeVO[x];
			for(int i=0; i<y; i++)
			{
				for(int j=0;j<x;j++){
					
					if(arrEpisodeVOSes[i].getEpisodeCode().equals(deptToVisit[j])){
						//arrEpisodeVOSes[i].setPatVisitReason(objPatVisitSUP_p.getArrPatVisitReason());
						arrEpisodeVOSes[i].setSnomdCIdVisitReason(objPatVisitSUP_p.getArrsnomdCIdVisitReason());
						arrEpisodeVOSes[i].setSnomdPTVisitReason(objPatVisitSUP_p.getArrsnomdPTVisitReason());
						arrEpisodeVOSes[i].setCreditBillFlag(objPatVisitSUP_p.getArrCreditBillFlag()[i]);
						if("1".equals(objPatVisitSUP_p.getArrCreditBillFlag()[i])){
							arrEpisodeVOSes[i].setCreditLetterRefNo(objPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
							arrEpisodeVOSes[i].setCreditLetterDate(objPatVisitSUP_p.getArrCreditLetterDate()[i]);
						}
						objArrEpisodeVO1[k]=arrEpisodeVOSes[i];
						//this flag is used to check if visit is forceful
						objArrEpisodeVO1[k].setOnRequestVisit(objPatVisitSUP_p.getOnRequestVisit());
						k++;
					} 
				 }
			}
			//forward visit (onRequestVisit/hiddenEpisode-->visit stamp on objRequest_p)
			//renewal visit (selected objPatVisitSUP_p.getDepartmentsToVisitStamp().length)
			//Modified to insert the Amount only for renewal on the whole(hospital wise) by Singaravelan on 07-10-13
			String strRenewalTypeHospOrEpisode="";
			if(objPatVisitSUP_p.getStrRenewalType()!=null && 
					(objPatVisitSUP_p.getStrRenewalType().equals("3") || objPatVisitSUP_p.getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			if(strMode_p.equals("SAVE"))
			{
				selectedEpisodeVO=new EpisodeVO[objArrEpisodeVO1.length];
				System.out.println("objPatVisitSUP_p.getPatAmountHospitalWise() : "+objPatVisitSUP_p.getPatAmountHospitalWise());
				for(int i=0;i<objArrEpisodeVO1.length;i++)
				{
					selectedEpisodeVO[i]=objArrEpisodeVO1[i];
					
					selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
					selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
					/*if(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY.equals(objPatientVO.getPatPrimaryCatGrpCode())){
						selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
					}else*/ 
					if(strRenewalTypeHospOrEpisode.equals("E")){
						if(objArrEpisodeVO1[i].getRenewalRequired().equalsIgnoreCase("1")){
							selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatRenewalAmountDeptWise());
							objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
							selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
						}else{
							objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
							selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
						}
					}else if(strRenewalTypeHospOrEpisode.equals("H")){
						 if(objPatientVO.getOpdRenewalRequired()!=null && objPatientVO.getOpdRenewalRequired().equals("1")){
							 if(i==0){
								 objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
								 selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
								 selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatAmountHospitalWise());
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
				if(objPatVisitSUP_p.getDepartmentsToVisitStamp()!=null )
				{
					selectedEpisodeVO= new EpisodeVO[objPatVisitSUP_p.getDepartmentsToVisitStamp().length];
					//System.out.println(objPatVisitSUP_p.getDepartmentsToVisitStamp().length);
					//selectedEpisodeVO= new EpisodeVO[objPatVisitSUP_p.getDepartmentsToRenew().length];
					for(int i=0;i<objPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
					{
						if(arrEpisodeVOSes!=null)
						{
							for(int j=0;j<arrEpisodeVOSes.length;j++)
							{
								if(objPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrEpisodeVOSes[j].getEpisodeCode()))
								{
									selectedEpisodeVO[i]=new EpisodeVO();
									HelperMethods.populate(selectedEpisodeVO[i],arrEpisodeVOSes[j]);
									
									selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
									selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
									
									/*if(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY.equals(objPatientVO.getPatPrimaryCatGrpCode())){
										selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
									}else */
									if(strRenewalTypeHospOrEpisode.equals("E")){
										if(objArrEpisodeVO1[i].getRenewalRequired().equalsIgnoreCase("1")){
											selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatRenewalAmountDeptWise());
											objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
											selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
										}else{
											objUserVO.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
											selectedEpisodeVO[i].setTariffId(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
										}
									}else if(strRenewalTypeHospOrEpisode.equals("H")){
										 if(objPatientVO.getOpdRenewalRequired()!=null && objPatientVO.getOpdRenewalRequired().equals("1")){
											 if(i==0){
												 objUserVO.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
												 selectedEpisodeVO[i].setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
												 selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatAmountHospitalWise());
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
									
									selectedEpisodeVO[i].setEpisodeCode(objPatVisitSUP_p.getDepartmentsToVisitStamp()[i]);
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
			if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				EpisodeRefDtlVO[] arrEpisodeOldPatRefVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO);
				int indexID=Integer.parseInt(objPatVisitSUP_p.getIndexID());
				
				episodeRefVO.setPatCrNo(arrEpisodeOldPatRefVO[indexID].getPatCrNo());
				episodeRefVO.setEpisodeCode(arrEpisodeOldPatRefVO[indexID].getEpisodeCode());
				episodeRefVO.setEpisodeVisitNo(arrEpisodeOldPatRefVO[indexID].getEpisodeVisitNo());
				episodeRefVO.setSerialNo(arrEpisodeOldPatRefVO[indexID].getSerialNo());
				episodeRefVO.setMlcNo(arrEpisodeOldPatRefVO[indexID].getMlcNo());
				episodeRefVO.setMlcFlag(arrEpisodeOldPatRefVO[indexID].getMlcFlag());
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				objPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				objPatientVO.setIsPatReferByList(objPatVisitSUP_p.getIsPatReferByList());
				objPatientVO.setIsReferred(objPatVisitSUP_p.getIsReferred());
				
			}
			else
			{
				if(objPatVisitSUP_p.getIsReferred()!=null && objPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
				
					episodeRefVO.setIsRefferInOut(objPatVisitSUP_p.getIsRefferInOut());
		
					if(objPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null && objPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
						objPatVisitSUP_p.setPatRefGnctdHospitalDept(objPatVisitSUP_p.getPatRefHospitalDeptOther());
					}
		
					episodeRefVO.setExternalHospitalDepartment(objPatVisitSUP_p.getPatRefGnctdHospitalDept());
					episodeRefVO.setExternalHospitalDepartmentUnit(objPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
					episodeRefVO.setExternalHospitalDoctorName(objPatVisitSUP_p.getPatRefDoctor());
					episodeRefVO.setExternalHospitalName(objPatVisitSUP_p.getPatRefHospitalName());
					episodeRefVO.setExternalHospitalPatCrNo(objPatVisitSUP_p.getPatRefGnctdHospitalCrno());
					episodeRefVO.setEpisodeCode(objPatVisitSUP_p.getRefferringOPDEpisode());
				
					if(objPatVisitSUP_p.getIsAssociated()!=null && objPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE)) 
						episodeRefVO.setExternalHospitalCode(objPatVisitSUP_p.getPatRefGnctdHospitalCode());
					else if(objPatVisitSUP_p.getIsAssociated()!=null && objPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
						episodeRefVO.setExternalHospitalCode("");
					
					if(arrOpenOPDEpisodeVO!=null)
					{
						for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
							if((objPatVisitSUP_p.getIsRefferInOut()!=null && objPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL) )
									|| (objPatVisitSUP_p.getIsRefferInOut()!=null && objPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))){
								if(episodeRefVO.getEpisodeCode().equals(arrOpenOPDEpisodeVO[i].getEpisodeCode()))
								{
									episodeRefVO.setFromDepartmentCode(arrOpenOPDEpisodeVO[i].getFromDepartmentCode());
									episodeRefVO.setFromDepartmentUnitCode(arrOpenOPDEpisodeVO[i].getFromDepartmentUnitCode());
									episodeRefVO.setEpisodeVisitNo(arrOpenOPDEpisodeVO[i].getEpisodeVisitNo());
									episodeRefVO.setMlcNo(arrOpenOPDEpisodeVO[i].getMlcNo());
									episodeRefVO.setMlcFlag(arrOpenOPDEpisodeVO[i].getMlcFlag());
									
								}
							}
					}
				}
			}
			if((objPatVisitSUP_p.getIsReferred()!=null && objPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)) 
					&& !(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)) 
					&& (objPatVisitSUP_p.getIsRefferInOut()!=null && !(objPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)))){
				
				if(episodeRefVO.getFromDepartmentUnitCode().equals(objArrEpisodeVO1[0].getDepartmentUnitCode())){
					//objStatus.add(Status.TRANSINPROCESS,"Visiting Unit And Reffered Unit Cannot be Same","");
					renewStatus=true;
					objPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
					throw new HisInsertNotAllowedException("Visiting Unit And Reffered Unit Cannot be Same");
				}
				
			}

			/*  ## 		Modification Log							
			 		##		Modify Date				:06thFeb'15 
			 		##		Reason	(CR/PRS)		:To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
			 		##		Modify By				:Sheeldarshi */
			/*CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			if(objPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
			
				HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
				for(int i =0;i<objPatVisitSUP_p.getArrCreditLetterRefNo().length;i++)
				{
					objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
					objCreditAvailDtlVO.setCreditLetterRefNo(objPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
					strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);

					if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
						if(strUniqueIdDuplicyFlag.equals("1")){
							String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
							objPatVisitSUP_p.setErrorMessage(strErrMsg);
							//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
							return false;
						}
					}
				}
				
			}*/
			//End:sheeldarshi	
			//////////////////////////////////////////////////////////
			EpisodeVO[] objArrEpisodeVO2=null;
			objPatientVO.setRoundRobinUnitFlag("0");
			objPatientVO.setRoundRobinRoomFlag("1");
			objArrEpisodeVO2=UnitWisePatientVisitDATA.saveOldPatientVisit(objPatientVO, selectedEpisodeVO, objUserVO,episodeRefVO);
			
			objRequest_p.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO2);
			objPatVisitSUP_p.setSaveSuccessful("true");
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
			
			System.out.println("------------To Print------------"+objPatVisitSUP_p.getIsPrintCard()+"-------------------------");
			String tmpFileName=RegistrationConfig.CARD_OLD_DEPT_VISIT+objUserVO.getSeatId();
				
			//String str="";
			//String isPrintCard="1";
			//code for DOT Matrix Printing
			//if(isPrintCard.equals("1"))
			objPatVisitSUP_p.setStrNormalMsg(objPatVisitSUP_p.getStrNormalMsg()+ "\nOld Patient Visit Stamped Successfully");
			RegistrationSlip regSlip=preapareSlip(objArrEpisodeVO2,objPatientVO,objPatVisitSUP_p,objRequest_p);
			strPrintDivContent=NewRegistrationSlip.print(regSlip, tmpFileName, objRequest_p, "RP");
			objPatVisitSUP_p.setPrint("1");
			//objPatVisitSUP_p.setPrintDivContent(strPrintDivContent);
			objPatVisitSUP_p.setPrintDivContent("");
			
			System.out.println("strPrintDivContent (Old) :"+strPrintDivContent);
			
			//Bill Receipt Printing ----Commented as per the Telangana UAT requirement by Singaravelan on 12-Feb-2015

			/*if(objArrEpisodeVO2!=null){
				if((objArrEpisodeVO2[0].getPatAmountCollected()!=null)&&(objArrEpisodeVO2[0].getPatAmountCollected()!="0")&&!(objArrEpisodeVO2[0].getPatAmountCollected().equalsIgnoreCase("0.00")))
				{
					strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(objArrEpisodeVO2,objPatientVO,objPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"ODV");
					System.out.println("PrintBillSlip :"+strBillPrintDiv);
					WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
					strPrintDivContent=""+strBillPrintDiv+"";
					objPatVisitSUP_p.setPrintDivContent(strPrintDivContent);
				}
			}*/
			
			System.out.println("Full PrintDivContent (New) :"+strPrintDivContent);
			
		
		}
		catch(HisInsertNotAllowedException e){
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
			e.printStackTrace();
			
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			//objStatus.add(Status.RECORDFOUND,e.getMessage() ,"");
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());		
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
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
 * calls  getPatientStatus(objPatientVO, objUserVO) of UnitWisePatientVisitDATA
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
//	    patStatus=UnitWisePatientVisitDATA.getPatientStatus(objPatientVO_p, objUserVO);
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
	
	
	
	
	
	public static void getOpenEpisodeDtl(HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p){
		PatientVO objPatientVO=(PatientVO)WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.PATIENT_VO);
		UserVO objUserVO=getUserVO(objRequest_p);
		Status status=new Status();
		try{
			if(objPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
			EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=UnitWisePatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO,"3");
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
			objPatVisitSUP_p.setIsReferred("1");
			objPatVisitSUP_p.setIsRefferInOut("I");
			}
			else{
				objPatVisitSUP_p.setIsReferred("0"); 
			}
			status.add(Status.TRANSINPROCESS);
			}
		catch(HisRecordNotFoundException e){
   		
	   		e.printStackTrace();
	   		objPatVisitSUP_p.setErrorMessage("");
	   		status.add(Status.TRANSINPROCESS);
		}
		finally{
			WebUTIL.setStatus(objRequest_p,status);
		}
}
	
	/*public static boolean renewalOfRegistration(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p){
		boolean renewalStatus=false;
		PatientVO objPatientVO=(PatientVO) WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.PATIENT_VO);   
		Status objStatus =new Status();	
		UserVO objUserVO =getUserVO(objRequest_p);
		EpisodeVO[] selectedEpisodeVO=null;
		EpisodeVO[] arrayEpisodeForStamping=null;
		setSysdate(objRequest_p);
		String sysDate=(String)WebUTIL.getSession(objRequest_p).getAttribute(Config.SYSDATE);
		String amount=(String)WebUTIL.getSession(objRequest_p).getAttribute("amount");
	//	String isVisitOnRequest=objPatVisitSUP_p.getOnRequestVisit();
		try{
			if(RegistrationConfig.RENEWAL_TYPE.equals("3") || RegistrationConfig.RENEWAL_TYPE.equals("4") || RegistrationConfig.RENEWAL_TYPE.equals("5"))	
			{	
				EpisodeVO[] arrRenewalEpisodeVO=(EpisodeVO[])WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.RENEWAL_REQUIRED_EPISODE_ARRAY);
				selectedEpisodeVO= new EpisodeVO[objPatVisitSUP_p.getDepartmentsToVisitStamp().length];
				//selectedEpisodeVO= new EpisodeVO[objPatVisitSUP_p.getDepartmentsToRenew().length];
				for(int i=0;i<objPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
				{
					if(arrRenewalEpisodeVO!=null)
					{
					for(int j=0;j<arrRenewalEpisodeVO.length;j++)
					{
						if(objPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrRenewalEpisodeVO[j].getEpisodeCode()))
								
								{
								selectedEpisodeVO[i]=new EpisodeVO();
								HelperMethods.populate(selectedEpisodeVO[i],arrRenewalEpisodeVO[j]);
								amount=(String) WebUTIL.getSession(objRequest_p).getAttribute("amount");
								selectedEpisodeVO[i].setEpisodeCode(objPatVisitSUP_p.getDepartmentsToRenew()[i]);
								selectedEpisodeVO[i].setPatAmountCollected(amount);
								selectedEpisodeVO[i].setSystemIPAddress(objPatientVO.getSystemIPAddress());
								}
					}
					}
				}
				////////new method for combined renewal and stamping//////
				EpisodeVO[] objArrEpisodeVO=(EpisodeVO[])WebUTIL.getSession(objRequest_p).getAttribute( RegistrationConfig.ARRAY_EPISODES_TO_BE_STAMPED);
				EpisodeRefDtlVO episodeRefVO=(EpisodeRefDtlVO)WebUTIL.getSession(objRequest_p).getAttribute( RegistrationConfig.ARRAY_EPISODE_REFER_VO_RENEWAL);
				
				
				arrayEpisodeForStamping=UnitWisePatientVisitDATA.saveDeptWiseRenewalAndStaming(objPatientVO,selectedEpisodeVO,arrRenewalEpisodeVO,objArrEpisodeVO,episodeRefVO,sysDate,objUserVO);
				
				//////////////////////
				renewalStatus=true;
				objPatVisitSUP_p.setSaveSuccessful("true");
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
				RegistrationSlip regSlip= preapareSlip(arrayEpisodeForStamping,objPatientVO,objPatVisitSUP_p, objRequest_p);
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
					printCard(objPatVisitSUP_p, objRequest_p);
					objPatVisitSUP_p.setSaveSuccessful("false");
				}
				objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>Old Department Visit: </font>");
				 }
			}
			///////////////
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			//objStatus.add(Status.NEW,e.getMessage(),"");
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());	
		}catch(HisUpdateUnsuccesfullException e){
					
			objStatus.add(Status.UNSUCESSFULL,"","Update Failed");	
		}		
		catch(HisDataAccessException e){
			
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");
			objPatVisitSUP_p.setErrorMessage("Record Not Found");
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
	
//	public static void getRegistrationAllowed(HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p){
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
//			objPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisRecordNotFoundException e){
//   		
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisDataAccessException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		catch(HisException e){
//			e.printStackTrace();
//			WebUTIL.removeFromStatus(objRequest_p,Status.NEW);
//			objPatVisitSUP_p.setErrorMessage(e.getMessage());
//	   		//status.add(Status.ERROR_AE,e.getMessage(),"");
//		}
//		finally{
//			WebUTIL.setStatus(objRequest_p,status);
//		}
//	}
	
	public static void getOldPatReferDtl(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		try
		{
			System.out.println("UnitWisePatientVisitUTIL :: getOldPatReferDtl()");
			//Commented By Mukund on 21.06.2016 to get the data unit wise 
			//arrEpisodeOldPatRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,strMode_p);
			EpisodeRefDtlVO[] arrEpisodeOldPatRefDtlVO={};//UnitWisePatientVisitDATA.getReferPat(objUserVO,"3");
			
			RegistrationConfigVO voRegistrationConfig = (RegistrationConfigVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			if(voRegistrationConfig.getUnitWiseRegFor()==null || voRegistrationConfig.getUnitWiseRegFor().equals(""))
				arrEpisodeOldPatRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"10");
			else
			{
				if(voRegistrationConfig.getUnitWiseRegFor().equals("1"))//For OPD
					arrEpisodeOldPatRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"8");
				else if (voRegistrationConfig.getUnitWiseRegFor().equals("2"))//For Special
					arrEpisodeOldPatRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"9");
				else if (voRegistrationConfig.getUnitWiseRegFor().equals("3"))//For OPD and Special
					arrEpisodeOldPatRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"10");
			}
			
			
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO ,arrEpisodeOldPatRefDtlVO);
			System.out.println("arrEpisodeOldPatRefDtlVO.length :"+arrEpisodeOldPatRefDtlVO.length);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			//e.printStackTrace();
			System.out.println(e.getMessage());
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
	
	public static void getReferPatient(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p,String strMode_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		EpisodeRefDtlVO[] arrEpisodeRefDtlVO=null;
		try
		{
			System.out.println("UnitWisePatientVisitUTIL :: getReferPatient");
			//commented by mukund on 21.06.2016 to get the data unit wise
			//arrEpisodeRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,strMode_p);
						
			RegistrationConfigVO voRegistrationConfig = (RegistrationConfigVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			if(voRegistrationConfig.getUnitWiseRegFor()==null || voRegistrationConfig.getUnitWiseRegFor().equals(""))
				arrEpisodeRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"7");
			else
			{
				if(voRegistrationConfig.getUnitWiseRegFor().equals("1"))//For OPD
					arrEpisodeRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"5");
				else if (voRegistrationConfig.getUnitWiseRegFor().equals("2"))//For Special
					arrEpisodeRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"6");
				else if (voRegistrationConfig.getUnitWiseRegFor().equals("3"))//For OPD and Special
					arrEpisodeRefDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,"7");
			}
			
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_EPISODE_REFER_PAT_VO,arrEpisodeRefDtlVO);
			objStatus.add(Status.LIST);
			System.out.println("arrEpisodeRefDtlVO.length :"+arrEpisodeRefDtlVO!=null?arrEpisodeRefDtlVO.length:0);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_EPISODE_REFER_PAT_VO,arrEpisodeRefDtlVO);
			//objStatus.add(Status.ERROR,"",e.getMessage());
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Record Not Found");
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

	
	/*public static void setRefDepartment(PatientVisitSUP fb,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		try
		{
			List refDeptList=UnitWisePatientVisitDATA.setRefDepartment(objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL ,refDeptList);
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
	
	public static void printCard(PatientVisitSUP fb,HttpServletRequest objRequest_p)
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



	public static void setNewDepartmentVisitDtl(PatientVisitSUP objPatVisitSUP_p, HttpServletRequest objRequest_p) 
	{
		Status  objStatus=new Status();
		String amountCollected="";
		String amount="";
		boolean flagDeptNotFound=true;
		try{
			System.out.println("UnitWisePatientVisitUTIL :: setNewDepartmentVisitDtl()");
			UserVO objUserVO =getUserVO(objRequest_p);
			EpisodeRefDtlVO[] arrEpisodeRefPatVO = null, arrEpisodeApmtPatVO = null;
			PatientVO objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
			HttpSession objSession=objRequest_p.getSession();
			//start:sandip naik on 11/7/2017
			if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				arrEpisodeRefPatVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
			}
			else if(objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
			{
				arrEpisodeApmtPatVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_Appoint_PAT_VO);
			}//End:sandip naik on 11/7/2017
			Map mp1=new HashMap();
			try{
				if(objPatVisitSUP_p.getIsPatReferByList()!=null && objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)){
					int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
					objPatVisitSUP_p.setPatCrNo(arrEpisodeRefPatVO[index].getPatCrNo());
					//mp1=UnitWisePatientVisitDATA.getAllNewDeptVisitEssentials(objPatVisitSUP_p.getPatCrNo(),objUserVO);	    
					//modified by mukund on 16.06.2016
					RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
					mp1=UnitWisePatientVisitDATA.getAllNewDeptVisitEssentials2(objPatVisitSUP_p.getPatCrNo(),objUserVO, objRegistrationConfigVO);	    
				}
				//start:sandip naik on 11/7/2017
				else if(objPatVisitSUP_p.getIsPatFromAppointList()!=null && objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
				{
					int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
					objPatVisitSUP_p.setPatCrNo(arrEpisodeApmtPatVO[index].getPatCrNo());
					//mp1=UnitWisePatientVisitDATA.getAllNewDeptVisitEssentials(objPatVisitSUP_p.getPatCrNo(),objUserVO);	    
					//modified by mukund on 16.06.2016
					RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
					mp1=UnitWisePatientVisitDATA.getAllNewDeptVisitEssentials2(objPatVisitSUP_p.getPatCrNo(),objUserVO, objRegistrationConfigVO);	    
				
				}
				//end:sandip naik on 11/7/2017
				else{
					mp1=setAllNewDeptVisitEssentials(objPatVisitSUP_p,objRequest_p);
				}
				
			}catch(Exception e){
				//e.printStackTrace();
			}finally{
				WebUTIL.setMapInSession(mp1,objRequest_p,"PatientUnitWiseVisitACTION");
			}
			setDeptOptions(objRequest_p, objPatVisitSUP_p);
			
			objPatientVO.setPatCrNo(objPatVisitSUP_p.getPatCrNo());
			objPatientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			objPatientVO.setIsPatReferByList(objPatVisitSUP_p.getIsPatReferByList());
			objPatientVO.setIsPatFromAppointList(objPatVisitSUP_p.getIsPatFromAppointList());

			
		
			
			System.out.println("IsPatReferByList :"+objPatVisitSUP_p.getIsPatReferByList());
			System.out.println("IsPatFromAppointList :"+objPatVisitSUP_p.getIsPatFromAppointList());

			if(objPatVisitSUP_p.getIsPatReferByList()!=null && objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				String fromDepartmentCode="";
				int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());

				objPatVisitSUP_p.setToDepartment(arrEpisodeRefPatVO[index].getToDepartment());
				objPatVisitSUP_p.setToDepartmentCode(arrEpisodeRefPatVO[index].getToDepartmentCode());
				objPatVisitSUP_p.setToDepartmentUnit(arrEpisodeRefPatVO[index].getToDepartmentUnit());
				objPatVisitSUP_p.setToDepartmentUnitCode(arrEpisodeRefPatVO[index].getToDepartmentUnitCode());
				fromDepartmentCode=arrEpisodeRefPatVO[index].getFromDepartmentCode();
				String entryDate=arrEpisodeRefPatVO[index].getEntryDate();
				if(Integer.parseInt(arrEpisodeRefPatVO[index].getEpisodeVisitNo()) >1){
					entryDate="";
				}
			}
			//start:sandip naik on 11/7/2017
			else if(objPatVisitSUP_p.getIsPatFromAppointList()!=null && objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
			{	
				
				int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
				objPatVisitSUP_p.setToDepartment(arrEpisodeApmtPatVO[index].getToDepartment());
				objPatVisitSUP_p.setToDepartmentCode(arrEpisodeApmtPatVO[index].getToDepartmentCode());
				objPatVisitSUP_p.setToDepartmentUnit(arrEpisodeApmtPatVO[index].getToDepartmentUnit());
				objPatVisitSUP_p.setToDepartmentUnitCode(arrEpisodeApmtPatVO[index].getToDepartmentUnitCode());
			}//end:sandip naik on 11/7/2017

			
			//Setting Patient Amount
			
			HelperMethods.populatetToNullOrEmpty(objPatVisitSUP_p,objPatientVO);
			//setPatAmount(objPatVisitSUP_p, objPatientVO, objUserVO, objRequest_p);
			objPatVisitSUP_p.setPatBillAmountWithoutGrouping(objPatVisitSUP_p.getPatAmountCollected());
			objSession=WebUTIL.getSession(objRequest_p);
			objSession.setAttribute("objPatientVO",objPatientVO);
			objSession.setAttribute(RegistrationConfig.PATIENT_VO,objPatientVO);
			//String patStatus=UnitWisePatientVisitUTIL.setPatientStatus( objRequest_p,objPatientVO);
		
		//	if(objPatVisitSUP_p.getIsPatReferByList()!=null && !(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)))
			if((objPatVisitSUP_p.getIsPatReferByList()!=null) && (!(objPatVisitSUP_p.getIsPatReferByList().equals(""))))
			{
				try{
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=UnitWisePatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO,"3");//procedure created
					WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				catch(HisRecordNotFoundException e){
					String msg=e.getMessage();
				}
			}
			//Start:sandip naik on 11/7/2017
			else if((objPatVisitSUP_p.getIsPatFromAppointList()!=null) && (!(objPatVisitSUP_p.getIsPatFromAppointList().equals(""))))
			{
			try{
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=UnitWisePatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO,"3");//procedure created
					WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				catch(HisRecordNotFoundException e){
					String msg=e.getMessage();
				}
			}//end:sandip naik on 11/7/2017
			objStatus.add(Status.TRANSINPROCESS,"","");
		
		
		}
		 catch(HisDeadPatientException e){
		    	objPatVisitSUP_p.setErrorMessage(e.getMessage());
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
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisRecordNotFoundException e){
			System.out.println(e.getMessage());
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
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
	 * calls getAllNewDeptVisitEssentials() from UnitWisePatientVisitDATA
	 * sets the Essential in session
	 * @param objPatVisitSUP_p -NewDeptVisitFB form bean
	 * @param objRequest_p -HttpServletRequest
	 */
	public static Map setAllNewDeptVisitEssentials( PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p){
		
		Status objStatus = new Status();
		Map mp=null;
		try{
				
			System.out.println("UnitWisePatientVisitUTIL :: setAllNewDeptVisitEssentials()");
		UserVO objUserVO =getUserVO(objRequest_p);		
		//mp=UnitWisePatientVisitDATA.getAllNewDeptVisitEssentials(objPatVisitSUP_p.getPatCrNo(),objUserVO);	    
		//modified by mukund on 16.06.2016
		RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
		mp=UnitWisePatientVisitDATA.getAllNewDeptVisitEssentials2(objPatVisitSUP_p.getPatCrNo(),objUserVO,objRegistrationConfigVO);
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
			//WebUTIL.setMapInSession(mp,objRequest_p,"PatientUnitWiseVisitACTION");
		}
		return mp;
	}
	/**
	 * sets Department Options 
	 * retrieves departments to visit stamp from fb
	 * removes those departments from department combo options
	 * by calling removeEntriesfromOptions() from WebUTIL
	 * sets the remaining departments in 
	 * @param objRequest_p -HttpServletRequest
	 * @param objPatVisitSUP_p -NewDeptVisitFB form bean
	 */
	public static void setDeptOptions(HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p){
		System.out.println("UnitWisePatientVisitUTIL :: setDeptOptions()");
		String [] deptVisitArr=	objPatVisitSUP_p.getDepartmentsToVisitStamp();
		HttpSession objSession=objRequest_p.getSession();		
		Collection colOrgDept = (Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT);
		//Collection colOrgDept = (Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
		System.out.println("colOrgDept :"+colOrgDept);
		if(colOrgDept!=null){
			Collection newCol = new ArrayList(colOrgDept);
			//<<why returned collection need to be stored
			if(deptVisitArr!=null)
				newCol=WebUTIL.removeEntriesfromOptions(newCol, deptVisitArr);
			//System.out.println("-------------------"+newCol+"--------------------");
			//objSession.setAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
			System.out.println("newCol :"+newCol);
			///for capturing department specific mandatory fiels
			//objSession.setAttribute(RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, newCol);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, newCol);
		}else{
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.REGISTRATION_MANDATORY_DEPT_LIST, null);
		}
		
	}

	public static void getRefDept_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strRefHospCode= (String)objRequest.getParameter("refHospCode");
			String strFlafRefHospOrInst = (String)objRequest.getParameter("flafRefHospOrInst");
			
			UserVO userVO=getUserVO(objRequest);
			
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			if(strFlafRefHospOrInst!=null && strFlafRefHospOrInst.equals("I"))
				strRefHospCode=userVO.getHospitalCode();
			
			List lstRefDept=UnitWisePatientVisitDATA.getRefDept_AJAX(userVO, strRefHospCode);
			
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


	public static void setOldDepartmentVisitDtl(PatientVisitSUP objPatVisitSUP_p,
			HttpServletRequest objRequest_p) {
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
		try{
			
			System.out.println("UnitWisePatientVisitUTIL:: setOldDepartmentVisitDtl()");
			HttpSession objSession=objRequest_p.getSession();
			EpisodeRefDtlVO[] arrEpisodeOldPatRefVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO);
			System.out.println("IsPatReferByList :"+objPatVisitSUP_p.getIsPatReferByList());
			if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				int	indexID=Integer.parseInt(objPatVisitSUP_p.getIndexID());
				System.out.println("----------"+objPatVisitSUP_p.getIndexID()+"-----------");
				System.out.println("--------- After Index print ------------");
				objPatVisitSUP_p.setPatCrNo(arrEpisodeOldPatRefVO[indexID].getPatCrNo());
				EpisodeVO[] objArrEpisodeVO=null;
				if(arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode()==null ||arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode()=="")
				{
					objArrEpisodeVO=UnitWisePatientVisitDATA.getOldPatientEpisodesByDept(objPatVisitSUP_p.getPatCrNo(),arrEpisodeOldPatRefVO[indexID].getToDepartmentCode(),
														objPatientVO.getRenewalConfig().getStrRenewalType(),objUserVO, RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
				}
				else
				{
					objArrEpisodeVO=UnitWisePatientVisitDATA.getOldPatientEpisodesByUnit(objPatVisitSUP_p.getPatCrNo(),arrEpisodeOldPatRefVO[indexID].getToDepartmentUnitCode(), 
														objPatientVO.getRenewalConfig().getStrRenewalType(),objUserVO, RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
				}
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
				
			}
			
			if(!(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)))
			{
				// Has to implement (Aadil)
				EpisodeVO[] objArrEpisodeVO=null;
				objArrEpisodeVO=setEpisodeDetails(objPatVisitSUP_p,objPatientVO.getPatCatCode(),objRequest_p);
				Map referEssDept=UnitWisePatientVisitDATA.referedEssentialDepartment(objUserVO); 
				/*String str2="";*/
				for(int i=0;i<objArrEpisodeVO.length;i++)
				{
					if(objArrEpisodeVO[i].getArrPatVisitReason()!=null){
						String str = objArrEpisodeVO[i].getArrPatVisitReason().replace(',', ';');
						str=str+";";
						System.out.println("value of str : "+str);
						objArrEpisodeVO[i].setArrPatVisitReason(str);
						}
				}
				//objPatVisitSUP_p.setArrPatVisitReason(str2);
				
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
				WebUTIL.setMapInSession(referEssDept,objRequest_p,"PatientVisitACT");
			}
			
			//if(!(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)))
//			if(objPatVisitSUP_p.getIsPatReferByList()!=null && !(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)))
			if(objPatVisitSUP_p.getIsPatReferByList()!=null)
			{
				try{
					EpisodeRefDtlVO[] arrOPDOpenEpisodeVO=UnitWisePatientVisitDATA.getOpenEpisodeOPD(objPatientVO.getPatCrNo(), objUserVO, "3");//procedure created
					WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO,arrOPDOpenEpisodeVO);
				}
				catch(HisRecordNotFoundException e){
					String msg=e.getMessage();
				}
			}
			/*List refDeptList=UnitWisePatientVisitDATA.setRefDepartment(objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL ,refDeptList);*/
			status.add(Status.TRANSINPROCESS);
		
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();	   		
			objPatVisitSUP_p.setErrorMessage("");
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
			objPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Transaction Unsuccessful");
			//status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
		
	}
	
	
	
	/**
	 * saves the patient new department visit
	 * @param req -HttpServletRequest
	 * @param objPatVisitSUP_p -NewDeptVisitFB form bean
	 */
	public static void savePatientNewDepartmentVisit(HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p){
		
		Status objStatus=new Status();
		String str="",strBillPrintDiv="";
		try{
			EpisodeRefDtlVO episodeRefVO=new EpisodeRefDtlVO();
			HttpSession objSession=objRequest_p.getSession();
			PatientVO objPatientVO =(PatientVO)objSession.getAttribute(RegistrationConfig.PATIENT_VO);
			//updating patient vo mandatory fields
			PatientVO oldPatientVO=null;
			System.out.println("UnitWisePatientVisitUTIL :: savePatientNewDepartmentVisit()");
			System.out.println("----Dept unit Code---"+objPatVisitSUP_p.getDepartmentUnitCode()+"-----------");
			System.out.println("----Dept To Visit---"+objPatVisitSUP_p.getToDepartment()+"-----------");
			System.out.println("----Dept To Visit CODE---"+objPatVisitSUP_p.getToDepartmentCode()+"-----------");
			//System.out.println("----Cat Bound Flag---"+objPatVisitSUP_p.getPaymentModeCode().split("#")[1]+"-----------");
			//System.out.println("----Cat Code Bound---"+objPatVisitSUP_p.getPaymentModeCode().split("#")[2]+"-----------");

			//objPatientVO.setCatBoundFlag(objPatVisitSUP_p.getPaymentModeCode().split("#")[1]);
			//objPatientVO.setCatCodeBound(objPatVisitSUP_p.getPaymentModeCode().split("#")[2]);

			oldPatientVO=new PatientVO();
			HelperMethods.populate(oldPatientVO, objPatientVO);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objPatVisitSUP_p);
			
			UserVO objUserVO=getUserVO(objRequest_p);
			
			//gets the hospital information
			getHospitalVO(objRequest_p);
			
			String patPriCatLable=objPatientVO.getPatPrimaryCat();
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			//edited by sandip naik on 11/7/17 for apmnt details
			if(!((objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))||(objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))))
			{
				objPatientVO.setDepartmentCode(objPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				objPatientVO.setDepartmentUnitCode(objPatVisitSUP_p.getDepartmentCode().split("#")[1]);
									
			}//end by sandip naik on 11/7/17
			
			System.out.println("objPatientVO.getDepartmentUnitCode() :"+objPatientVO.getDepartmentUnitCode()+"-----------");
			System.out.println("objPatientVO.getDepartmentCode() :"+objPatientVO.getDepartmentCode()+"-----------");
			
			//String patAmountCollected=UnitWisePatientVisitDATA.getBillAmount(objPatientVO.getPatPrimaryCatCode(),objUserVO);
			String patAmountCollected=objPatVisitSUP_p.getPatAmountCollected();
			//For validating the pat amount collected
			if(objPatVisitSUP_p.getPatAmountCollected()==null){
				objPatVisitSUP_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}
			else if(objPatVisitSUP_p.getPatAmountCollected().equals("")){
				objPatVisitSUP_p.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
				return ;
			}
			if(!RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY.equals(objPatientVO.getPatPrimaryCatGrpCode())){
				objUserVO.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
				if(objPatVisitSUP_p.getStrRenewalType()!=null &&  (objPatVisitSUP_p.getStrRenewalType().equals("1") || objPatVisitSUP_p.getStrRenewalType().equals("2"))){
					if(objPatVisitSUP_p.getOpdRenewalRequired()!=null && objPatVisitSUP_p.getOpdRenewalRequired().equals("1")){
						objUserVO.setTariffID(RegistrationConfig.SPECIAL_RENEWAL_TARIFF_ID);
					}else{
						objUserVO.setTariffID(RegistrationConfig.SPECIAL_NEW_DEPT_VISIT_TARIFF_ID);
					}
				}
			}
			
			String regCatCode=objPatientVO.getPatRegCatCode();
			String genderCode=objPatientVO.getPatGenderCode();
			String patCrNo=objPatientVO.getPatCrNo();
			//System.out.println("-----------"+objPatVisitSUP_p.getPatRefGnctdHospitalDept()+"-----------");
			if(objPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null)
				if(objPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0"))
					objPatVisitSUP_p.setPatRefGnctdHospitalDept(objPatVisitSUP_p.getPatRefHospitalDeptOther());
			
			//HelperMethods.populate(objPatientVO, objPatVisitSUP_p);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objPatVisitSUP_p);
			if(objPatientVO.getIsUnknown().equalsIgnoreCase(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			{
				///removing (unknown) from unknown patient name
				objPatientVO.setPatFirstName(objPatientVO.getPatFirstName().substring(9));
			}
			
			
			objPatientVO.setPatGenderCode(genderCode);
			objPatientVO.setPatCrNo(patCrNo);
			objPatientVO.setPatRegCatCode(regCatCode);
			objPatientVO.setPatSecondaryCatCode("");
			objPatientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD);
			if(!(objPatVisitSUP_p.getOtherHospitalFlag().length()==0) && 
					objPatVisitSUP_p.getOtherHospitalFlag().equals("1") &&
					!objPatVisitSUP_p.getOtherHospitalDataFound().equals("1"))
			{
				objPatientVO.setPatPrimaryCatCode(objPatVisitSUP_p.getPatPrimaryCatCode().split("#")[0]);
				objPatientVO.setPatIdNo(objPatVisitSUP_p.getPatIdNo());
				objPatientVO.setPatPrimaryCatGrpCode(objPatVisitSUP_p.getPatPrimaryCatGrpCode());
			}
			//objPatientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
			//edited by sandip naik on 11/7/17 for apmnt details
			Collection colDept		=(Collection)objSession.getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
			if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)){
				setDepartmentDtl(colDept, objPatientVO, objPatVisitSUP_p.getDepartmentCode());
				objPatientVO.setRoundRobinUnitFlag("0");
				objPatientVO.setRoundRobinRoomFlag("1");
			}
			else if(objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
			{
				objPatientVO.setRoundRobinUnitFlag("0");
				objPatientVO.setRoundRobinRoomFlag("1");	
			}
			else
			{	
				setDepartmentDtl(colDept, objPatientVO, objPatVisitSUP_p.getDepartmentCode());
			}
			//end by sandip naik in 11/7/2017													
			String[] deptToVisit = objPatVisitSUP_p.getDepartmentsToVisitStamp();
			String[] arrFileNo=objPatVisitSUP_p.getArrFileNo();
			EpisodeVO[] objArrEpisodeVO1 = null;
			if(deptToVisit!=null && deptToVisit.length>0 )
				objArrEpisodeVO1 = new EpisodeVO[deptToVisit.length];
			else
				objArrEpisodeVO1 = new EpisodeVO[1];
			//edited by sandip naik on 11/7/17 for apmnt details
			if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
				int i=0;
				
				int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
				episodeRefVO.setEpisodeCode(episodeRefDtlVO[index].getEpisodeCode());
				episodeRefVO.setEpisodeVisitNo(episodeRefDtlVO[index].getEpisodeVisitNo());
				episodeRefVO.setSerialNo(episodeRefDtlVO[index].getSerialNo());
				episodeRefVO.setMlcNo(episodeRefDtlVO[index].getMlcNo());
				//Changed for getting MLC Flag
				episodeRefVO.setMlcFlag(episodeRefDtlVO[index].getMlcFlag());
				episodeRefVO.setFromDepartment(episodeRefDtlVO[index].getFromDepartment());
				objPatVisitSUP_p.setDepartmentCode(objPatVisitSUP_p.getToDepartmentCode());
				
				objPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				objPatientVO.setIsPatReferByList(objPatVisitSUP_p.getIsPatReferByList());
				
				objPatientVO.setDepartmentCode(objPatVisitSUP_p.getToDepartmentCode());
				objPatientVO.setDepartmentUnitCode(objPatVisitSUP_p.getToDepartmentUnitCode());
				if(episodeRefDtlVO[index].getToDepartmentUnitType()!=null && episodeRefDtlVO[index].getToDepartmentUnitType().equals(RegistrationConfig.ROSTERTYPE_SPECIAL))
					objPatientVO.setUnitRosterType(RegistrationConfig.ROSTERTYPE_SPECIAL);
				else
					objPatientVO.setUnitRosterType(RegistrationConfig.ROSTERTYPE_OPD);
			}//Start:sandip naik on 11/7/2017
			else if(objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
			{

				EpisodeRefDtlVO[] patientTempDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_Appoint_PAT_VO);
				int i=0;
				
				int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
				episodeRefVO.setEpisodeCode(patientTempDtlVO[index].getEpisodeCode());
				episodeRefVO.setEpisodeVisitNo(patientTempDtlVO[index].getEpisodeVisitNo());
				episodeRefVO.setSerialNo(patientTempDtlVO[index].getSerialNo());
				episodeRefVO.setMlcNo(patientTempDtlVO[index].getMlcNo());
				//Changed for getting MLC Flag
				episodeRefVO.setMlcFlag(patientTempDtlVO[index].getMlcFlag());
				episodeRefVO.setFromDepartment(patientTempDtlVO[index].getFromDepartment());
				objPatVisitSUP_p.setDepartmentCode(objPatVisitSUP_p.getToDepartmentCode());
				
				objPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				objPatientVO.setIsPatReferByList(objPatVisitSUP_p.getIsPatReferByList());
				
				objPatientVO.setDepartmentCode(objPatVisitSUP_p.getToDepartmentCode());
				objPatientVO.setDepartmentUnitCode(objPatVisitSUP_p.getToDepartmentUnitCode());
				if(patientTempDtlVO[index].getToDepartmentUnitType()!=null && patientTempDtlVO[index].getToDepartmentUnitType().equals(RegistrationConfig.ROSTERTYPE_SPECIAL))
					objPatientVO.setUnitRosterType(RegistrationConfig.ROSTERTYPE_SPECIAL);
				else
					objPatientVO.setUnitRosterType(RegistrationConfig.ROSTERTYPE_OPD);
				
			}
			else
			{

				EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
				
				episodeRefVO.setIsRefferInOut(objPatVisitSUP_p.getIsRefferInOut());
				if(objPatVisitSUP_p.getIsRefferInOut()!=null && objPatVisitSUP_p.getIsRefferInOut().equals("I")){
					episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				}else if(objPatVisitSUP_p.getIsRefferInOut()!=null && objPatVisitSUP_p.getIsRefferInOut().equals("E")){
					episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				
				episodeRefVO.setExternalHospitalDepartment(objPatVisitSUP_p.getPatRefGnctdHospitalDept());
				episodeRefVO.setExternalHospitalDepartmentUnit(objPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
				episodeRefVO.setExternalHospitalDoctorName(objPatVisitSUP_p.getPatRefDoctor());
				episodeRefVO.setExternalHospitalName(objPatVisitSUP_p.getPatRefHospitalName());
				episodeRefVO.setExternalHospitalPatCrNo(objPatVisitSUP_p.getPatRefGnctdHospitalCrno());
				episodeRefVO.setEpisodeCode(objPatVisitSUP_p.getRefferringOPDEpisode());
				
				if(objPatVisitSUP_p.getIsAssociated()!=null && objPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE))
					episodeRefVO.setExternalHospitalCode(objPatVisitSUP_p.getPatRefGnctdHospitalCode());
				else if(objPatVisitSUP_p.getIsAssociated()!=null && objPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
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
							//Changed for getting MLC Flag
							episodeRefVO.setMlcFlag(arrOpenOPDEpisodeVO[i].getMlcFlag());
							episodeRefVO.setFromDepartment(arrOpenOPDEpisodeVO[i].getFromDepartment());
							if(arrOpenOPDEpisodeVO[i].getToDepartmentUnitType()!=null && arrOpenOPDEpisodeVO[i].getToDepartmentUnitType().equals(RegistrationConfig.ROSTERTYPE_SPECIAL))
								objPatientVO.setUnitRosterType(RegistrationConfig.ROSTERTYPE_SPECIAL);
							else
								objPatientVO.setUnitRosterType(RegistrationConfig.ROSTERTYPE_OPD);
						}
				}
			
			}//end by sandip naik on 11/7/17
			
			objPatientVO.setIsReferred(objPatVisitSUP_p.getIsReferred());
			if(objPatientVO.getIsReferred().equals("true")||objPatientVO.getIsReferred().equals("1"))
				objPatientVO.setIsReferred("1");
			else
				objPatientVO.setIsReferred("0");
			Collection col=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
			String deptName="";
			
			if(deptToVisit==null || deptToVisit.length==0)
			{
				objArrEpisodeVO1 = new EpisodeVO[1];			
				objArrEpisodeVO1[0]=new EpisodeVO();
				
				//objArrEpisodeVO[0].setDepartmentCode(objPatVisitSUP_p.getDepartmentUnitCode().substring(0,3));
				if(objPatVisitSUP_p.getIsPatReferByList()!=null && objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{	
					objArrEpisodeVO1[0].setDepartmentCode(objPatVisitSUP_p.getToDepartmentCode());
					objArrEpisodeVO1[0].setDepartmentUnitCode(objPatVisitSUP_p.getToDepartmentUnitCode());
				}//edited by sandip naik on 11/7/17 for apmnt details
				else if(objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
				{	
					objArrEpisodeVO1[0].setDepartmentCode(objPatVisitSUP_p.getToDepartmentCode());
					objArrEpisodeVO1[0].setDepartmentUnitCode(objPatVisitSUP_p.getToDepartmentUnitCode());
				}//end by sandip naik on 11/7/17
				else{
					objArrEpisodeVO1[0].setDepartmentCode(objPatientVO.getDepartmentCode());
					objArrEpisodeVO1[0].setDepartmentUnitCode(objPatientVO.getDepartmentUnitCode());
					
				}
				
				//objArrEpisodeVO1[0].setFileNo(objPatVisitSUP_p.getFileNo());
				objArrEpisodeVO1[0].setPatAmountCollected(patAmountCollected);
				objArrEpisodeVO1[0].setIsReferred(objPatVisitSUP_p.getIsReferred());
				objArrEpisodeVO1[0].setSnomdCIdVisitReason(objPatVisitSUP_p.getSnomdCIdVisitReason());
				objArrEpisodeVO1[0].setSnomdPTVisitReason(objPatVisitSUP_p.getSnomdPTVisitReason());
				//Added by warish for cash combo text value save 14-08-18
				objArrEpisodeVO1[0].setPaymentModeCode(objPatVisitSUP_p.getPaymentModeCode());
				if (!objPatVisitSUP_p.getPaymentModeCode().equals("1"))
					objArrEpisodeVO1[0].setPaymentModeCodeRefId(objPatVisitSUP_p.getPaymentModeRefId());
				//end
				if(objPatVisitSUP_p.getIsReferred()!=null && objPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
					objArrEpisodeVO1[0].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				}
				if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
					int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
					deptName=episodeRefDtlVO[index].getToDepartment();
					objArrEpisodeVO1[0].setDepartment(deptName);

				}//edited by sandip naik on 11/7/17 for apmnt details
				else if(objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
				{
					EpisodeRefDtlVO[] patientTempDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_Appoint_PAT_VO);
					int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
					deptName=patientTempDtlVO[index].getToDepartment();
					objArrEpisodeVO1[0].setDepartment(deptName);
					

				}//end by sandip naik on 11/7/17
				else{
					deptName=UnitWisePatientVisitUTIL.getEntryLabel(col,objPatVisitSUP_p.getDepartmentCode());
					//String arr[]=objPatVisitSUP_p.getDepartmentCode().split("#");
					//objArrEpisodeVO1[0].setDepartment(deptName.substring(0,deptName.indexOf("(")));
					objArrEpisodeVO1[0].setDepartment(deptName);
					objArrEpisodeVO1[0].setDepartmentUnit(deptName.substring(deptName.indexOf("(")+1,deptName.length()-1));
				}
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
					//objArrEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatAmountCollected());
					objArrEpisodeVO1[i].setIsReferred(objPatVisitSUP_p.getIsReferred());
					objArrEpisodeVO1[i].setSnomdCIdVisitReason(objPatVisitSUP_p.getSnomdCIdVisitReason());
					objArrEpisodeVO1[i].setSnomdPTVisitReason(objPatVisitSUP_p.getSnomdPTVisitReason());
					objArrEpisodeVO1[i].setPatAmountCollected(patAmountCollected);
					if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
						int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
						deptName=episodeRefDtlVO[index].getToDepartment();
					}//edited by sandip naik on 11/7/17 for apmnt details
					else if(objPatVisitSUP_p.getIsPatFromAppointList().equals(RegistrationConfig.IS_PAT_REFER_BY_APMNT_TRUE))
					{
						EpisodeRefDtlVO[] patientTempDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_Appoint_PAT_VO);
						int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
						deptName=patientTempDtlVO[index].getToDepartment();
					}//end by sandip naik on 11/7/17
					else{
					 deptName=UnitWisePatientVisitUTIL.getEntryLabel(col,deptToVisit[i]);
					}
					//since department contains department name plus unit name
					//objArrEpisodeVO[i].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
					objArrEpisodeVO1[i].setDepartment(deptName);
					
				}
			} 
			String sysdate=(String)objSession.getAttribute(Config.SYSDATE);
			objPatientVO.setSystemDate(sysdate);
			/*if(objPatVisitSUP_p.getIsReferred()!=null && objPatVisitSUP_p.getIsReferred().equals("on")){
				objPatientVO.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
			}*/
			
	
			objRequest_p.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE, objPatVisitSUP_p.getDepartmentCode());
			
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
							&& objPatVisitSUP_p.getPatRefGnctdHospitalCode().equals(RegistrationConfig.EXTERNAL_INSTITUTE_PGI)){
						objPatientVO.setPatAmountCollected("0");
						objArrEpisodeVO1[0].setPatAmountCollected("0");
						break;
					}
				}	
			}
			System.out.println("DeptUnitCode(PatintVO) b4 calling DATA :"+objPatientVO.getDepartmentUnitCode());
			/*  ## 		Modification Log							
	 		##		Modify Date				:06thFeb'15 
	 		##		Reason	(CR/PRS)		://To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
	 		##		Modify By				:Sheeldarshi */
	//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
			String strUniqueIdDuplicyFlag = "0";
	CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
	objCreditAvailDtlVO.setCreditLetterRefNo(objPatVisitSUP_p.getCreditLetterRefNo());
	objCreditAvailDtlVO.setCreditLetterDate(objPatVisitSUP_p.getCreditLetterDate());
	objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
	if(objPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatientVO.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
	{
		HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
		objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
		strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);

		if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
			if(strUniqueIdDuplicyFlag.equals("1")){
				String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
				objPatVisitSUP_p.setErrorMessage(strErrMsg);
				//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
				//return false;
			}
		}
	}
	//End:sheeldarshi	
			//objPatientVO.getDepartmentUnitCode()
			
			/////////////////////////////////////////////////////////////////////////////
			EpisodeVO [] objArrEpisodeVO2=UnitWisePatientVisitDATA.newDepartmentVisit(objPatientVO, objArrEpisodeVO1, episodeRefVO, objUserVO,oldPatientVO);
			
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
			
			objPatVisitSUP_p.setStrNormalMsg(objPatVisitSUP_p.getStrNormalMsg()+ "\nNew Patient Visit Stamped Successfully");
			str=NewRegistrationSlip.print(preapareSlip(objArrEpisodeVO2,objPatientVO,objPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"DV");
			objPatVisitSUP_p.setPrint("2");
			objPatVisitSUP_p.setPrintDivContent(str);
			System.out.println("PrintDivContent (New) :"+objPatVisitSUP_p.getPrintDivContent());
			//objStatus.add(Status.DONE,str.toString(),"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientVisitedLabel'>"+"New Department Visit: "+"</div></font>");
			
			
			//Bill Receipt Printing --Commented as per the Telangana UAT requirement by Singaravelan on 12-Feb-2015
			/*if((objPatVisitSUP_p.getPatAmountCollected()!=null)&&(objPatVisitSUP_p.getPatAmountCollected()!="0")&&!(objPatVisitSUP_p.getPatAmountCollected().equalsIgnoreCase("0.00")))
			{
				strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(objArrEpisodeVO2,objPatientVO,objPatVisitSUP_p, objRequest_p), tmpFileName,objRequest_p,"NDV");
				System.out.println("PrintBillSlip :"+strBillPrintDiv);
				WebUTIL.setAttributeInSession(objRequest_p,"billReceiptString", strBillPrintDiv);
				str+=""+strBillPrintDiv+"";
				objPatVisitSUP_p.setPrintDivContent(str);
			}*/
			
			System.out.println("Full PrintDivContent (New) :"+str);
			
			if(objPatientVO.getOtherHospitalFlag()!=null && objPatientVO.getOtherHospitalFlag().equals("1") && objPatientVO.getOtherHospitalDataFound()!=null && !objPatientVO.getOtherHospitalDataFound().equals("1"))
				objRequest_p.getSession().removeAttribute("keyPatientVO");
			
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
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());		
		}
		catch(HisSQLManualException e){
			objPatVisitSUP_p.setErrorMessage("Department-Unit Limit Exhausted");
			objStatus.add(Status.ERROR_AE,"","Department-Unit Limit Exhausted");
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
	public static void getBillAmountByDeptGrouping(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p)
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
				String entryDate=objPatVisitSUP_p.getEntryDate();
				if(Integer.parseInt(objPatVisitSUP_p.getEpisodeVisitNo())>1)
					entryDate="";
				//billAmount=UnitWisePatientVisitDATA.getBillAmountByDeptGrouping(objPatientVO.getPatPrimaryCatCode(), objPatVisitSUP_p.getSelectedFromDept(), objPatVisitSUP_p.getDepartmentCode(), objUserVO);
				billAmount=UnitWisePatientVisitDATA.getBillAmountByDeptGrouping(objPatientVO.getPatPrimaryCatCode(), objPatVisitSUP_p.getSelectedFromDept(), objPatVisitSUP_p.getDepartmentCode(),entryDate, objUserVO);
				objPatVisitSUP_p.setPatAmountCollected(billAmount);
				objPatVisitSUP_p.setReferringInstType("O");
				objPatVisitSUP_p.setReferInternalExternal("I");
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



	public static void savePatientVisitStamp(HttpServletRequest objRequest_p,
			PatientVisitSUP objPatVisitSUP_p) {
			String[] deptToVisit = objPatVisitSUP_p.getDepartmentsToVisitStamp();
			//objUserVO.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
			//objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			EpisodeVO[] objArrEpisodeVO = new EpisodeVO[deptToVisit.length];		
			objArrEpisodeVO=populateNewDepartmentVisitEpisode(objRequest_p,objPatVisitSUP_p);
			savePatientVisit(objRequest_p, objPatVisitSUP_p, "SAVE", objArrEpisodeVO);
		
	}

	public static void savePatientVisit(HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p,String strMode_p,EpisodeVO[] arrNewDeptEpisodeVO){
		
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
			
			
			objUserVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			
			EpisodeVO[] arrEpisodeVOSes = (EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);		 
			String[] deptToVisit=null;
			
			String regCatCode=objPatientVO.getPatRegCatCode();
			String genderCode=objPatientVO.getPatGenderCode();
			String patCrNo=objPatientVO.getPatCrNo();
			if(objPatVisitSUP_p.getPatRefGnctdHospitalDept()!=null && objPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
				objPatVisitSUP_p.setPatRefGnctdHospitalDept(objPatVisitSUP_p.getPatRefHospitalDeptOther());
			}
			//HelperMethods.populate(objPatientVO, objPatVisitSUP_p);
			HelperMethods.populatetToNullOrEmpty(objPatientVO, objPatVisitSUP_p);
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
			//objPatientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
			//String isVisitOnRequest=objPatVisitSUP_p.getOnRequestVisit();
			objPatientVO.setIsReferred(objPatVisitSUP_p.getIsReferred());
			if(objPatVisitSUP_p.getDepartmentsToVisitStamp().length>0)
				deptToVisit=objPatVisitSUP_p.getDepartmentsToVisitStamp();
			else{
				
				///in case if only new department is selected since for old unit was closed
				//hcode is present in case of forward on objRequest_p
				if(objPatVisitSUP_p.getHcode()!=null && !objPatVisitSUP_p.getHcode().equals(""))
				{
					deptToVisit=new String[]{objPatVisitSUP_p.getHcode()};
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
						//arrEpisodeVOSes[i].setPatVisitReason(objPatVisitSUP_p.getArrPatVisitReason());
						arrEpisodeVOSes[i].setSnomdCIdVisitReason(objPatVisitSUP_p.getArrsnomdCIdVisitReason());
						arrEpisodeVOSes[i].setSnomdPTVisitReason(objPatVisitSUP_p.getArrsnomdPTVisitReason());
						arrEpisodeVOSes[i].setCreditBillFlag(objPatVisitSUP_p.getArrCreditBillFlag()[i]);
						if("1".equals(objPatVisitSUP_p.getArrCreditBillFlag()[i])){
							arrEpisodeVOSes[i].setCreditLetterRefNo(objPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
							arrEpisodeVOSes[i].setCreditLetterDate(objPatVisitSUP_p.getArrCreditLetterDate()[i]);
						}
						objArrEpisodeVO[k]=arrEpisodeVOSes[i];
						//this flag is used to check if visit is forceful
						objArrEpisodeVO[k].setOnRequestVisit(objPatVisitSUP_p.getOnRequestVisit());
						k++;
					} 
				 }
			}
			//forward visit (onRequestVisit/hiddenEpisode-->visit stamp on objRequest_p)
			//renewal visit (selected objPatVisitSUP_p.getDepartmentsToVisitStamp().length) Modified by Singaravelan on 07-10-13
			System.out.println("----New Dept visit-----------"+objPatVisitSUP_p.getNewDepartmentVisit()+"----------------");
			System.out.println("----Old Dept visit-----------"+objPatVisitSUP_p.getOldDepartmentVisit()+"----------------");

			String strRenewalTypeHospOrEpisode="";
			if(objPatVisitSUP_p.getStrRenewalType()!=null && 
					(objPatVisitSUP_p.getStrRenewalType().equals("3") || objPatVisitSUP_p.getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			if(strMode_p.equals("SAVE"))
			{
				selectedEpisodeVO=new EpisodeVO[objArrEpisodeVO.length];
				if(objPatVisitSUP_p.getNewDepartmentVisit().equalsIgnoreCase("on") && objPatVisitSUP_p.getOldDepartmentVisit().equalsIgnoreCase("on"))
				{		
					for(int i=0;i<objArrEpisodeVO.length;i++)
					{
						selectedEpisodeVO[i]=objArrEpisodeVO[i];
						
						selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
						selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
						
						if(strRenewalTypeHospOrEpisode.equals("E")){
							if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1")){
								selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatRenewalAmountDeptWise());
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
								selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatRenewalAmountDeptWise());
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
								selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatAmountDeptWise());
						}
						else if(strRenewalTypeHospOrEpisode.equals("H")){
							 if(objPatientVO.getOpdRenewalRequired()!=null && objPatientVO.getOpdRenewalRequired().equals("1")){
								 if(i==0)
									 selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatAmountHospitalWise());
								 else
									 selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
							 }
						 } //Above Code For Calculating PatAmount is commented, because for HospitalWise Amount is already calculated while populating episodeVo at NewDeptVisit (i.e in method populateNewDepartmentVisitEpisode)
						else{
							selectedEpisodeVO[i].setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
								
						}*/
					}
				}
					
			}
			else
			{
				if(objPatVisitSUP_p.getDepartmentsToVisitStamp()!=null )
				{
					selectedEpisodeVO= new EpisodeVO[objPatVisitSUP_p.getDepartmentsToVisitStamp().length];
				
				for(int i=0;i<objPatVisitSUP_p.getDepartmentsToVisitStamp().length;i++)
				{
					if(arrEpisodeVOSes!=null)
					{
					for(int j=0;j<arrEpisodeVOSes.length;j++)
					{
						if(objPatVisitSUP_p.getDepartmentsToVisitStamp()[i].equals(arrEpisodeVOSes[j].getEpisodeCode()))
						{
							selectedEpisodeVO[i]=new EpisodeVO();
								HelperMethods.populate(selectedEpisodeVO[i],arrEpisodeVOSes[j]);
								
								selectedEpisodeVO[i].setEpisodeStartDate(selectedEpisodeVO[i].getEpisodeDate());
								selectedEpisodeVO[i].setLastVisitDate(selectedEpisodeVO[i].getGdt_entry_date());
								
								if(strRenewalTypeHospOrEpisode.equals("E")){
									if(objArrEpisodeVO[i].getRenewalRequired().equalsIgnoreCase("1")){
										selectedEpisodeVO[i].setPatAmountCollected(objPatVisitSUP_p.getPatRenewalAmountDeptWise());
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
								
								selectedEpisodeVO[i].setEpisodeCode(objPatVisitSUP_p.getDepartmentsToVisitStamp()[i]);
								selectedEpisodeVO[i].setSystemIPAddress(objPatientVO.getSystemIPAddress());
						}
					}
					}
				}
				}
			}
					
			HttpSession objSession=WebUTIL.getSession(objRequest_p);

			EpisodeRefDtlVO episodeRefVO=new EpisodeRefDtlVO();
			if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				EpisodeRefDtlVO[] arrEpisodeOldPatRefVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO);
				int indexID=Integer.parseInt(objPatVisitSUP_p.getIndexID());
				
				episodeRefVO.setPatCrNo(arrEpisodeOldPatRefVO[indexID].getPatCrNo());
				episodeRefVO.setEpisodeCode(arrEpisodeOldPatRefVO[indexID].getEpisodeCode());
				episodeRefVO.setEpisodeVisitNo(arrEpisodeOldPatRefVO[indexID].getEpisodeVisitNo());
				episodeRefVO.setSerialNo(arrEpisodeOldPatRefVO[indexID].getSerialNo());
				episodeRefVO.setMlcNo(arrEpisodeOldPatRefVO[indexID].getMlcNo());
				episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);
				objPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
				objPatientVO.setIsPatReferByList(objPatVisitSUP_p.getIsPatReferByList());
				objPatientVO.setIsReferred(objPatVisitSUP_p.getIsReferred());
				
			}
			else
			{
			if(objPatVisitSUP_p.getIsReferred()!=null && objPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
			EpisodeRefDtlVO[] arrOpenOPDEpisodeVO = (EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
			
			episodeRefVO.setIsRefferInOut(objPatVisitSUP_p.getIsRefferInOut());
			
			if(objPatVisitSUP_p.getPatRefGnctdHospitalDept().equals("0")){
				objPatVisitSUP_p.setPatRefGnctdHospitalDept(objPatVisitSUP_p.getPatRefHospitalDeptOther());
			}
			
			episodeRefVO.setExternalHospitalDepartment(objPatVisitSUP_p.getPatRefGnctdHospitalDept());
			episodeRefVO.setExternalHospitalDepartmentUnit(objPatVisitSUP_p.getPatRefGnctdHospitalDeptUnit());
			episodeRefVO.setExternalHospitalDoctorName(objPatVisitSUP_p.getPatRefDoctor());
			episodeRefVO.setExternalHospitalName(objPatVisitSUP_p.getPatRefHospitalName());
			episodeRefVO.setExternalHospitalPatCrNo(objPatVisitSUP_p.getPatRefGnctdHospitalCrno());
			episodeRefVO.setEpisodeCode(objPatVisitSUP_p.getRefferringOPDEpisode());
		
			if(objPatVisitSUP_p.getIsAssociated()!=null && objPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_TRUE)) 
				episodeRefVO.setExternalHospitalCode(objPatVisitSUP_p.getPatRefGnctdHospitalCode());
			else if(objPatVisitSUP_p.getIsAssociated().equals(RegistrationConfig.IS_ASSOCIATED_FALSE))
				episodeRefVO.setExternalHospitalCode("");
			if(arrOpenOPDEpisodeVO!=null){
			for(int i=0;i<arrOpenOPDEpisodeVO.length;i++)
				if(objPatVisitSUP_p.getIsRefferInOut()!=null && (objPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL) || objPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)))	{
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
			if((objPatVisitSUP_p.getIsReferred()!=null && objPatVisitSUP_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)) && !(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE)) && !(objPatVisitSUP_p.getIsRefferInOut()!=null && objPatVisitSUP_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))){
				
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
					objPatVisitSUP_p.setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
					throw new HisInsertNotAllowedException();
					}
				}
			}
							
				//////////////////////////////////////////////////////////
				
				Map episodeMap=new HashMap();
				
				
				/*  ## 		Modification Log							
		 		##		Modify Date				:06thFeb'15 
		 		##		Reason	(CR/PRS)		:To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
		 		##		Modify By				:Sheeldarshi */
		String strUniqueIdDuplicyFlag = "0";
		CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
		objCreditAvailDtlVO.setCreditLetterRefNo(objPatVisitSUP_p.getCreditLetterRefNo());
		objCreditAvailDtlVO.setCreditLetterDate(objPatVisitSUP_p.getCreditLetterDate());
		objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
		if(objPatVisitSUP_p.getPatPrimaryCatGrpCode()!=null && objPatientVO.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
		{
			HelperMethods.populate(objCreditAvailDtlVO, objPatientVO);
			objCreditAvailDtlVO.setTariffId(objUserVO.getTariffID());
			strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);

			if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
				if(strUniqueIdDuplicyFlag.equals("1")){
					String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
					objPatVisitSUP_p.setErrorMessage(strErrMsg);
					}
			}
			for(int i =0;i<objPatVisitSUP_p.getArrCreditLetterRefNo().length;i++)
			{
				objCreditAvailDtlVO.setCreditLetterRefNo(objPatVisitSUP_p.getArrCreditLetterRefNo()[i]);
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(objUserVO, objCreditAvailDtlVO);

				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						String	strErrMsg="Patient with this Reference Letter No ("+objPatientVO.getCreditLetterRefNo()+ ") already registered.";
						objPatVisitSUP_p.setErrorMessage(strErrMsg);
						}
				}
			}
			
		}
		//End:sheeldarshi
				episodeMap=UnitWisePatientVisitDATA.savePatientVisit(objPatVisitSUP_p,objPatientVO, selectedEpisodeVO, objUserVO,episodeRefVO,arrNewDeptEpisodeVO);
				
				EpisodeVO[] _newEpisodeVO=(EpisodeVO[])episodeMap.get("NEWEPISODELIST");
				EpisodeVO[] _oldEpisodeVO=(EpisodeVO[])episodeMap.get("OLDEPISODELIST");
				
				//objRequest_p.setAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, objArrEpisodeVO);
				objPatVisitSUP_p.setSaveSuccessful("true");
				
				 
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
					
				RegistrationSlip regSlip1=preapareSlip(_newEpisodeVO,objPatientVO,objPatVisitSUP_p,objRequest_p);
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
						
					RegistrationSlip regSlip2=preapareSlip(_oldEpisodeVO,objPatientVO,objPatVisitSUP_p,objRequest_p);
					strPrintDivContent2=NewRegistrationSlip.print(regSlip2, tmpFileName, objRequest_p, "RP");
					
					strTempPrintDivContent=strPrintDivContent1;
					objPatVisitSUP_p.setPrintDivContent(strTempPrintDivContent);
					objPatVisitSUP_p.setPrint("1");
					System.out.println("Print in Both  :"+objPatVisitSUP_p.getPrintDivContent());
					
						
				}
				
			
			
		}
		
		catch(HisInsertNotAllowedException e){
			e.printStackTrace();
			
		}
		catch(HisRenewalRequiredException e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
			
		}
		catch(HisInvalidTokenNumberException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Invalid Token Number","");		
		}
		catch(HisAppointmentNotAvailableException e){
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
			
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
			HttpServletRequest objRequest_p, PatientVisitSUP objPatVisitSUP_p) {
		
		
		HttpSession objSession=objRequest_p.getSession();
		EpisodeVO[] objArrEpisodeVO =null;
		
		try
		{
			
			System.out.println("UnitWisePatientVisitUTIL :: populateNewDepartmentVisitEpisode()");
			Collection col=(Collection)objRequest_p.getSession().getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
			String deptName="";
			String[] deptToVisit = new String[0];
			objArrEpisodeVO = new EpisodeVO[deptToVisit.length];
			String patAmountCollected="0";
			
			patAmountCollected= objPatVisitSUP_p.getPatAmountCollected();
			
			if(deptToVisit==null || deptToVisit.length==0){
				objArrEpisodeVO = new EpisodeVO[1];			
				objArrEpisodeVO[0]=new EpisodeVO();
				System.out.println("-----------------"+objPatVisitSUP_p.getDepartmentUnitCode()+"-------------------");
				
				System.out.println("dept code :"+ objPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				System.out.println("dept unit code :"+objPatVisitSUP_p.getDepartmentCode().split("#")[1]);
				
				objArrEpisodeVO[0].setDepartmentCode(objPatVisitSUP_p.getDepartmentCode().split("#")[0]);
				objArrEpisodeVO[0].setDepartmentUnitCode(objPatVisitSUP_p.getDepartmentCode().split("#")[1]);
				objArrEpisodeVO[0].setFileNo(objPatVisitSUP_p.getFileNo());
				objArrEpisodeVO[0].setPatAmountCollected(patAmountCollected);
				objArrEpisodeVO[0].setIsReferred(objPatVisitSUP_p.getIsReferred());
		
			if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
					int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
					deptName=episodeRefDtlVO[index].getToDepartment();
				}else{
				 deptName=UnitWisePatientVisitUTIL.getEntryLabel(col,objPatVisitSUP_p.getDepartmentUnitCode());
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
					
					objArrEpisodeVO[i].setIsReferred(objPatVisitSUP_p.getIsReferred());
					objArrEpisodeVO[i].setPatAmountCollected(patAmountCollected);
					if(objPatVisitSUP_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						EpisodeRefDtlVO[] episodeRefDtlVO=(EpisodeRefDtlVO[])objSession.getAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
						int index=Integer.parseInt(objPatVisitSUP_p.getOnlineReferedIndex());
						deptName=episodeRefDtlVO[index].getToDepartment();
					}else{
					 deptName=UnitWisePatientVisitUTIL.getEntryLabel(col,deptToVisit[i]);
					}
					objArrEpisodeVO[i].setDepartment(deptName);
				}
			} 
			String sysdate=(String)objSession.getAttribute(Config.SYSDATE);
			
			

			objRequest_p.getSession().setAttribute(RegistrationConfig.SESSION_DEPARTMENT_CODE, objPatVisitSUP_p.getDepartmentCode());

			
			// setting the registration charge 0 for the department code matched in the configuration
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return objArrEpisodeVO;
	}
	
	public static void printLastOpdCard(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p){	
		
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
	public static void getMapOfRenewalConfigDtlOnKeyPatCat(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCatGroup=new HashMap();
		try
		{
			System.out.println("UnitWisePatientVisitUTIL :: getMapOfRenewalConfigDtlOnKeyPatCat()");
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			mapOfRenewalVoOnKeyPatCatGroup=UnitWisePatientVisitDATA.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
			
			objPatVisitSUP_p.setIsSnomedServiceOn(RegistrationConfig.SNOMED_SERVICE_OFF);
			if(HISConfig.HIS_SNOMEDCT_SERVICES_ON.equalsIgnoreCase("ON")){
				 objPatVisitSUP_p.setIsSnomedServiceOn(RegistrationConfig.SNOMED_SERVICE_ON);
			}
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCatGroup);
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
	
	private static void setDepartmentDtl(Collection objCol_p, PatientVO objPatientVO_p,  String strDeptCode_p){
		
		Iterator itr=objCol_p.iterator();
		while (itr.hasNext())
		{
			Entry objEntry=(Entry)itr.next();
			String[] strDeptValArray = objEntry.getValue().split("#");
			if(objEntry.getValue() !=null && objEntry.getValue().equals(strDeptCode_p))
				{
				objPatientVO_p.setDepartmentUnitCode(strDeptValArray[1]);
				objPatientVO_p.setRoomCode(strDeptValArray[2]);
				objPatientVO_p.setRoundRobinUnitFlag(strDeptValArray[4]);
				objPatientVO_p.setRoundRobinRoomFlag(strDeptValArray[3]);
				//objPatientVO_p.setDepartment(objEntry.getLabel());
				//Added for setting UNit Roster type to set Episode Type based on selected unit type
				objPatientVO_p.setUnitRosterType(strDeptValArray[8]);
				objPatientVO_p.setDepartment(strDeptValArray[9]);
				
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
	
	public static void getCreditBeneficiaryDtlForPatCatOnCRNO(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		BeneficiaryPatientVO creditBeneficiaryDtl_VO=new BeneficiaryPatientVO();
		try
		{
			System.out.println("UnitWisePatientVisitUTIL :: getCreditBeneficiaryDtlForPatCatOnCRNO()");
			creditBeneficiaryDtl_VO=UnitWisePatientVisitDATA.getCreditBeneficiaryEssentials(objPatVisitSUP_p, objUserVO);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_MAP_OF_CREDIT_BENEFICIARY_VO,creditBeneficiaryDtl_VO);

			objPatVisitSUP_p.setClientCode(creditBeneficiaryDtl_VO.getClientCode());
			objPatVisitSUP_p.setClientName(creditBeneficiaryDtl_VO.getClientName());
			
			if(objPatVisitSUP_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))					
				objPatVisitSUP_p.setStaffCardNo(creditBeneficiaryDtl_VO.getStaffCardNo());
			else
				objPatVisitSUP_p.setAgsNo(creditBeneficiaryDtl_VO.getStaffCardNo());
		
			objPatVisitSUP_p.setCreditLetterRefNo(creditBeneficiaryDtl_VO.getCreditLetterRefNo());
			objPatVisitSUP_p.setLetterType(creditBeneficiaryDtl_VO.getLetterType());
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
	
	public static void getInitEssentials(HttpServletRequest objRequest, HttpServletResponse objResponse, PatientVisitSUP objPatVisitSUP_p) {
		
		try{
			UserVO userVO=getUserVO(objRequest);
			HospitalMstVO hospitalVO=getHospitalVO(objRequest);
			
			String strCountryCode = RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE;
			String strStateCode   =	hospitalVO.getState();			
			
			List lstDistrict=UnitWisePatientVisitDATA.getDistrict_AJAX(userVO, strStateCode, strCountryCode);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);
			
			List lstRelation=UnitWisePatientVisitDATA.getRelationsList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,lstRelation);
			//Start warish for get cash combo
			List lstPaymentMode=UnitWisePatientVisitDATA.getPaymentModeList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.PAYMENT_MODE_OPTION_LIST,lstPaymentMode);
			List lstPaymentMode_processed = mapPaymentModeListToPatientCategoryRevist(lstPaymentMode, objPatVisitSUP_p);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.PAYMENT_MODE_OPTION_LIST_PROCESSED,lstPaymentMode_processed);
			//end
			
			List lstClient=PatientVisitDATA.getClientList(userVO);
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST,lstClient);

		}
		
		catch(Exception e){
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,new ArrayList());
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,new ArrayList());
		
		}
		
		
	}
	
	public static void getAddmittedDetailsOnCRNO(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		String isAdmitted="0";
		try
		{
			System.out.println("UnitWisePatientVisitUTIL :: getAddmittedDetailsOnCRNO()");
			isAdmitted=UnitWisePatientVisitDATA.checkPatientIsAdmitted(objPatVisitSUP_p, objUserVO);

			objPatVisitSUP_p.setIsPatAdmitted(isAdmitted);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
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
	
	/**Added by Mukund Vinayak on 15/06/2016
	 * for unitWise patient visit process to get Registration Config Dtl
	 * */
	
	public static void getRegistrationConfigDtl(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p)
	{
		System.out.println("UnitWisePatientVisitUTIL :: getRegistrationCofigDtl()");
				
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();

		try
		{
			voRegistrationConfig=UnitWisePatientVisitDATA.getRegistrationConfigDtl(objUserVO);
			setSysdateAndDefaultCrNoFormat(objRequest_p);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
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
	
	/**End: Mukund Vinayak*/
	/*By Mukund on 03.04.2017*/
	public static void getAppointedPatient(PatientVisitSUP objPatVisitSUP_p,HttpServletRequest objRequest_p,String strMode_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		Status objStatus=new Status();
		EpisodeRefDtlVO[] arrEpisodeAptDtlVO=null;
		try
		{
			System.out.println("UnitWisePatientVisitUTIL :: getAppointedPatient");
			arrEpisodeAptDtlVO=UnitWisePatientVisitDATA.getReferPat(objUserVO,strMode_p);
			WebUTIL.setAttributeInSession(objRequest_p,"arrEpisodeAppointDtlVO",arrEpisodeAptDtlVO);
			objStatus.add(Status.LIST);
			System.out.println("arrEpisodeRefDtlVO.length :"+arrEpisodeAptDtlVO!=null?arrEpisodeAptDtlVO.length:0);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objPatVisitSUP_p.setErrorMessage("Record Not Found");
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
	}//End: 03.04.2017
	
	public static List mapPaymentModeListToPatientCategoryRevist(List<Entry> lstPaymentMode, PatientVisitSUP objPatVisitSUP_p){
		
		List lstPymntMd_processd = new ArrayList(); 		
		String primCatCode = objPatVisitSUP_p.getPatPrimaryCatCode();
		
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

}
