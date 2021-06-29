package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Feb 2013
 */

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.util.CreditPatientVisitUTL;
import vo.registration.EpisodeVO;

public class PatientCreditVisitACT extends PatientVisitSUP 
{
	public String execute() throws Exception
	 {
		System.out.println("NewDeptVisitACTION :: execute()");
		boolean ff=objRequest.getSession().isNew();
		return NEW();
	 }
	 /**
		 * action mainly called at the initial loading of a page or when a form is reset
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @return String to the output view called "NEW"
		 */
		public String NEW()
		{
			System.out.println("NewDeptVisitACTION :: NEW()");
			
			this.reset();
			String strMode="";
			HttpSession session=objRequest.getSession();
			WebUTIL.refreshTransState(objRequest,"PatientVisitACT");
			CreditPatientVisitUTL.getMapOfRenewalConfigDtlOnKeyPatCat(this,objRequest);
		 	this.setAfterGo("0");
		 	
		 	if(this.getModeCase()==null)
		 		this.setModeCase("2");
		 	if(RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_BOTH) || RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(RegistrationConfig.REGISTRATION_ONLINE))
		 	{
		 		if(this.getModeCase().equals("0"))
				{
			 		strMode="NEWPATIENT";
			 		CreditPatientVisitUTL.getOldPatReferDtl(this, objRequest);
				}
				else if(this.getModeCase().equals("1"))
				{
					strMode="NEWPATIENT";
					CreditPatientVisitUTL.getReferPatient(this,objRequest,"2");
				}
				else 
				{
					strMode="NEWPATIENT";
					CreditPatientVisitUTL.getOldPatReferDtl(this, objRequest);
			 		CreditPatientVisitUTL.getReferPatient(this,objRequest,"2");
				}
		 	}	
		 	return "NEW";
		}

	 /**
		 * gets Patient Detail
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param objRequest - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "NEW"
		 */
		public String GETPATDTL()
		{
			System.out.println("NewDeptVisitACTION :: GETPATDTL()");
			String strMode="";
			this.clearMessageField();
			objRequest.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
			
			WebUTIL.refreshTransState(objRequest,"PatientVisitACT");
			//function calling is moved inside util for proper exception handling
			this.afterGo="1";
			this.setGoWithoutDeptVisitOn("0");
			if(this.getModeCase().equals("0"))
			{
		 		strMode="SAMENEWPATIENT";
		 		this.setOldDepartmentVisit("On");
			}
			else if(this.getModeCase().equals("1"))
			{
				strMode="SAMENEWPATIENT";
				this.setNewDepartmentVisit("On");
			}
			else if(this.getModeCase().equals("2"))
			{
				if(this.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					if(this.getSelectedReferal().equalsIgnoreCase("O"))
					{
						this.setOldDepartmentVisit("On");
						this.setModeCase("0");
					}
					else
					{
						this.setNewDepartmentVisit("On");
						this.setModeCase("1");
					}
					strMode="SAMENEWPATIENT";		
				}
				else
				{
				this.setOldDepartmentVisit("On");
				this.setNewDepartmentVisit("On");
				strMode="SAMENEWPATIENT";		
				}
			}
			
			CharacterEncoding.setCharacterEncoding(objRequest);
			CreditPatientVisitUTL.setPatientDtlByCrno(this,objRequest);
			CreditPatientVisitUTL.getInitEssentials(objRequest, objResponse);
			CreditPatientVisitUTL.getCreditBenDtl(objRequest, this);
			
			System.out.println("objRequest.getCharacterEncoding() :"+ objRequest.getCharacterEncoding());
			System.out.println("-------Pat Actual Amt-----------"+this.getPatActualAmount()+"--------------")	;	
			System.out.println("-------Client code-----------"+this.getClientCode()+"--------------")	;	

			return strMode;
		}
		
		public String RELOADPATDTL()
		{
			System.out.println("NewDeptVisitACTION :: GETPATDTL()");
			String strMode="";
			this.afterGo="1";
			this.setGoWithoutDeptVisitOn("0");			
			this.setPatPrimaryCatGrpCode(this.getPatPrimaryCatCode().split("#")[9]);
			
			if(this.getPatPrimaryCatCode().indexOf(",")>0)
				this.setPatPrimaryCatCode(this.getPatPrimaryCatCode().split(",")[0]);
			
			if(this.getModeCase().equals("0"))
			{
		 		strMode="SAMENEWPATIENT";
		 		this.setOldDepartmentVisit("On");
			}
			else if(this.getModeCase().equals("1"))
			{
				strMode="SAMENEWPATIENT";
				this.setNewDepartmentVisit("On");
			}
			else if(this.getModeCase().equals("2"))
			{
				if(this.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
				{
					if(this.getSelectedReferal().equalsIgnoreCase("O"))
					{
						this.setOldDepartmentVisit("On");
						this.setModeCase("0");
					}
					else
					{
						this.setNewDepartmentVisit("On");
						this.setModeCase("1");
					}
					strMode="SAMENEWPATIENT";		
				}
				else
				{
				this.setOldDepartmentVisit("On");
				this.setNewDepartmentVisit("On");
				strMode="SAMENEWPATIENT";		
				}
			}
			
			return strMode;
		}
		/**
		 * sets Secondary Category Essentials
		* @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param objRequest - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "SAME"
		 */
		/*public String GETREGFEE(){		
			System.out.println("PatientVisitACT :: GETREGFEE()");
			CreditPatientVisitUTL.getBillAmountByDeptGrouping(this, objRequest);
			Status status=(Status)objRequest.getAttribute(Config.STATUS_OBJECT);
			
			if(this.getNewDepartmentVisit().equalsIgnoreCase("on"))
				this.setNewDepartmentVisit("On");
			
			if(this.getOldDepartmentVisit().equalsIgnoreCase("on"))
				this.setOldDepartmentVisit("On");
			
			return "SAMENEWPATIENT";
		}*/
		/*public String OLDDEPARTMENTVISITDTL(){
			
			System.out.println("PatientVisitACT :: OLDDEPARTMENTVISITDTL()");
			
			//System.out.println("PatientVisitACTION:OLDDEPARTMENTVISITDTL");
			//CreditPatientVisitUTL.setAllInitialOldPatientVisitEssentials(objRequest);
			CreditPatientVisitUTL.setOldDepartmentVisitDtl(this,objRequest);
			
			this.setSaveSuccessful("");
			
			if(this.getNewDepartmentVisit().equalsIgnoreCase("on"))
				this.setNewDepartmentVisit("On");
			
			//this.setOldDepartmentVisit("1");
			objRequest.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
			return "SAMENEWPATIENT";
		}*/
		
		public void getRefDept()
		 {
			   
			 System.out.println("PatientVisitACT :: getRefDept()");
				
			 CreditPatientVisitUTL.getRefDept_AJAX(objRequest, objResponse);
		 }
		
		/**
		 * saves Old Patient Visit Details
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param objRequest - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return calls the action "NEW"
		 * /**
		 * saves New Department Visit Details	
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param objRequest - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return calls the action "NEW"
		 */
		 
		public String SAVE(){
			
			System.out.println("PatientVisitACT :: SAVE()");
			boolean renewStatus=false;
			if(this.getOldDepartmentVisit()==null)	this.oldDepartmentVisit="";
			if(this.getNewDepartmentVisit()==null)	this.newDepartmentVisit="";
			System.out.println("this.getOldDepartmentVisit() :"+this.getOldDepartmentVisit());
			System.out.println("this.getNewDepartmentVisit() :"+this.getNewDepartmentVisit());
			System.out.println("this.getIsReferred() :"+this.getIsReferred());
			if(this.getIsReferred()=="true")
				this.setIsReferred("1");
			if(this.getIsReferred()=="false")
				this.setIsReferred("0");
			if(this.getPatActualAmount()==null || this.getPatActualAmount().equals(""))	this.patActualAmount="0.00";
			/*if(this.getIsReferred()!=null && this.getIsReferred().equals("1")){
				this.setIsReferred("on");
			}*/
			//try{
				if(this.isRefferInOut!=null && this.isRefferInOut.equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)){
					this.setIsRefferInOut("E");
				}
				
				if(this.getOldDepartmentVisit().equalsIgnoreCase("On") && this.getNewDepartmentVisit().equals(""))
				{
					renewStatus=CreditPatientVisitUTL.saveOldPatientVisit(objRequest,this,"SAVE");
					//System.out.println("Old Department Visit Saved");
				}
				else if(this.getOldDepartmentVisit().equals("")  && this.getNewDepartmentVisit().equalsIgnoreCase("On"))
				{
					System.out.println("New Department Visit Saved");
					CreditPatientVisitUTL.savePatientNewDepartmentVisit(objRequest, this);
				}
				else if(this.getNewDepartmentVisit().equalsIgnoreCase("On") && this.getOldDepartmentVisit().equalsIgnoreCase("On"))
				{
					System.out.println("Both Saved");
					CreditPatientVisitUTL.savePatientVisitStamp(objRequest,this);
					//System.out.println("Patient Visit Saved");
				}
			/*}catch(Exception e){
				renewStatus=false;
				System.out.println(e);
			}*/
				/*EpisodeVO[] voEpisode=(EpisodeVO[])	objRequest.getSession().getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);
				System.out.println("REGISTRATIONDESK_EPISODEVO_ARR.length :"+voEpisode!=null?voEpisode.length:0);
				System.out.println("Error Message :"+this.getErrorMessage());*/
			if(renewStatus){
				this.setGoWithoutDeptVisitOn("1");
				return "SAME";
			}
			else
				return this.NEW();
			
		}
		
		
		public String DGNDETAIL(){
			System.out.println("PatientVisitACT :: DGNDETAIL()");
			this.setPatCrNo(this.getCrNoToRetrieve());
			//WebUTIL.setAttributeInSession(objRequest,RegistrationConfig.retrieveBypatNameCRNO,this.getDiagnosticCode());
			//WebUTIL.setAttributeInSession(objRequest,RegistrationConfig.ARR_SELECTED_DIAGNOSIS_NAME,this.getDiagnosticName());
			return "SAME";
		 }
		public String EPISODEDTL(){
			System.out.println("PatientVisitACT :: EPISODEDTL()");
			CreditPatientVisitUTL.getOpenEpisodeDtl(objRequest,this);
			return "SAMENEWPATIENT";
		}
		
		
		
		public String RENEWAL(){
			
			System.out.println("PatientVisitACT :: RENEWAL()");
			boolean renewStatus=false;
			
			if(this.getModeCase().equals("0"))
			{
				renewStatus=CreditPatientVisitUTL.saveOldPatientVisit(objRequest,this,"RENEWAL");
			}
			
			return "SAMENEWPATIENT";
			
			
		}
		//for reprint opd card
		public String PRINT(){
			System.out.println("PatientVisitACT :: PRINT()");
			CreditPatientVisitUTL.printLastOpdCard(this,objRequest);
			//this.reset(mapping, objRequest);
			this.setSaveSuccessful("");
			objRequest.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
			return this.NEW();
			
		}	
		
		public String CANCEL() throws Exception
		 {
			this.clearMessageField();
			this.setPrintDivContent("");
			this.setModeCase(null);
			return execute();
		 }
		 public String CLEAR() throws Exception
		 {
			 this.clearMessageField();
			 this.setPrintDivContent("");
			 this.setModeCase(null);
			 return execute();
		 }
		 /* #Start					:Sheeldarshi 
			#Modify Date(CR/PRS)	:22ndMay'15 
			#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
		*/

		 public String CASHCOLLECTIONPOPUP() {

				//BillingFB formBean = (BillingFB) form;
				String target="cashCollectionPopup";
				CreditPatientVisitUTL.getCashCollectionDetail(objRequest,objResponse, this);
				return "cashCollectionPopup";
				
		}
}
