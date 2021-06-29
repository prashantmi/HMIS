package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: March 2013
 */

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.SpclPatientVisitSUP;
import registration.transactions.controller.util.SpclPatientVisitUTL;

public class SpclPatientVisitACTION extends SpclPatientVisitSUP 
{
	public String execute() throws Exception
	 {
		System.out.println("SpclPatientVisitACTION :: execute()");
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
			System.out.println("SpclPatientVisitACTION :: NEW()");
			
			this.reset();
			String strMode="";
			HttpSession session=objRequest.getSession();
			WebUTIL.refreshTransState(objRequest,"SpclPatientVisitACTION");
			SpclPatientVisitUTL.getMapOfRenewalConfigDtlOnKeyPatCat(this,objRequest);
		 	//SpclPatientVisitUTL.setSysdate(objRequest);
		 	//this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		 	this.setAfterGo("0");
		 	
		 	//SpclPatientVisitUTL.getRegistrationAllowed(objRequest, this);
		 	//System.out.println("-----------"+this.getModeCase()+"-----------");
		 	if(this.getModeCase()==null)
		 		this.setModeCase("2");
		 		//this.setModeCase("2");
		 	if(RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_BOTH) || RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(RegistrationConfig.REGISTRATION_ONLINE))
		 	{
		 		//SpclPatientVisitUTL.getOldPatReferDtl(this, objRequest);
		 		//SpclPatientVisitUTL.getReferPatient(this,objRequest);//NOT REQUIRED ARPGYA
		 		if(this.getModeCase().equals("0"))
				{
			 		strMode="NEWPATIENT";
			 		SpclPatientVisitUTL.getOldPatReferDtl(this, objRequest);
				}
				else if(this.getModeCase().equals("1"))
				{
					strMode="NEWPATIENT";
					SpclPatientVisitUTL.getReferPatient(this,objRequest,"2");
				}
				else 
				{
					strMode="NEWPATIENT";
					SpclPatientVisitUTL.getOldPatReferDtl(this, objRequest);
			 		SpclPatientVisitUTL.getReferPatient(this,objRequest,"2");
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
			System.out.println("SpclPatientVisitACTION :: GETPATDTL()");
			//NewDeptVisitUTIL.getPatientDetail(this,super.getObjRequest());
			//this.setAfterGo("1");
			
			//SpclPatientVisitUTL.setPatientDtlByCrno(this,objRequest);
			String strMode="";
			//SpclPatientVisitUTL.setRefDepartment(this,objRequest);
			this.clearMessageField();
			objRequest.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
			
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
			SpclPatientVisitUTL.setPatientDtlByCrno(this,objRequest);
			System.out.println("patAmountCollected : "+this.patAmountCollected);
			
			return strMode;
		}
		
		public void getRefDept()
		 {
			   
			 System.out.println("SplPatientVisitACT :: getRefDept()");
				
			 SpclPatientVisitUTL.getRefDept_AJAX(objRequest, objResponse);
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
			
			System.out.println("SplPatientVisitACT :: SAVE()");
			boolean renewStatus=false;
			if(this.getOldDepartmentVisit()==null)	this.oldDepartmentVisit="";
			if(this.getNewDepartmentVisit()==null)	this.newDepartmentVisit="";
			System.out.println("this.getOldDepartmentVisit() :"+this.getOldDepartmentVisit());
			System.out.println("this.getNewDepartmentVisit() :"+this.getNewDepartmentVisit());
			System.out.println("this.getIsReferred() :"+this.getIsReferred());
			
			/*if(this.getIsReferred()!=null && this.getIsReferred().equals("1")){
				this.setIsReferred("on");
			}*/
			//try{
				if(this.isRefferInOut!=null && this.isRefferInOut.equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)){
					this.setIsRefferInOut("E");
				}
				
				if(this.getOldDepartmentVisit().equalsIgnoreCase("On") && this.getNewDepartmentVisit().equals(""))
				{
					renewStatus=SpclPatientVisitUTL.saveOldPatientVisit(objRequest,this,"SAVE");
					//System.out.println("Old Department Visit Saved");
				}
				else if(this.getOldDepartmentVisit().equals("")  && this.getNewDepartmentVisit().equalsIgnoreCase("On"))
				{
					System.out.println("New Department Visit Saved");
					SpclPatientVisitUTL.savePatientNewDepartmentVisit(objRequest, this);
				}
				else if(this.getNewDepartmentVisit().equalsIgnoreCase("On") && this.getOldDepartmentVisit().equalsIgnoreCase("On"))
				{
					System.out.println("Both Saved");
					SpclPatientVisitUTL.saveSplPatientVisitStamp(objRequest,this);
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
			System.out.println("SplPatientVisitACT :: DGNDETAIL()");
			this.setPatCrNo(this.getCrNoToRetrieve());
			//WebUTIL.setAttributeInSession(objRequest,RegistrationConfig.retrieveBypatNameCRNO,this.getDiagnosticCode());
			//WebUTIL.setAttributeInSession(objRequest,RegistrationConfig.ARR_SELECTED_DIAGNOSIS_NAME,this.getDiagnosticName());
			return "SAME";
		 }
		public String EPISODEDTL(){
			System.out.println("SplPatientVisitACT :: EPISODEDTL()");
			SpclPatientVisitUTL.getOpenEpisodeDtl(objRequest,this);
			return "SAMENEWPATIENT";
		}
		
		
		
		public String RENEWAL(){
			
			System.out.println("SplPatientVisitACT :: RENEWAL()");
			boolean renewStatus=false;
			
			if(this.getModeCase().equals("0"))
			{
				renewStatus=SpclPatientVisitUTL.saveOldPatientVisit(objRequest,this,"RENEWAL");
			}
			
			return "SAMENEWPATIENT";
			
			
		}
		//for reprint opd card
		public String PRINT(){
			System.out.println("SplPatientVisitACT :: PRINT()");
			SpclPatientVisitUTL.printLastOpdCard(this,objRequest);
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
		 
		 public SpclPatientVisitSUP getActionSUP()
		 {
			 return this;
		 }
}
