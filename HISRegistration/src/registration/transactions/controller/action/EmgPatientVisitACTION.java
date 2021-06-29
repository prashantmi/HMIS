package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: 27th Feb 2014
 */

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.EmgPatientVisitSUP;
import registration.transactions.controller.util.EmgPatientVisitUTL;


public class EmgPatientVisitACTION extends EmgPatientVisitSUP 
{
	public String execute() throws Exception
	 {
		System.out.println("EmgEmgPatientVisitACTION :: execute()");
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
			System.out.println("EmgEmgPatientVisitACTION :: NEW()");
			
			this.reset();
			String strMode="";
			HttpSession session=objRequest.getSession();
			WebUTIL.refreshTransState(objRequest,"EmgPatientVisitACTION");
			EmgPatientVisitUTL.getMapOfRenewalConfigDtlOnKeyPatCat(this,objRequest);
		 	this.setAfterGo("0");
		 	
		 	//System.out.println("-----------"+this.getModeCase()+"-----------");
		 	if(this.getModeCase()==null)
		 		this.setModeCase("2");
		 	if(RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_BOTH) || RegistrationConfig.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(RegistrationConfig.REGISTRATION_ONLINE))
		 	{
		 		strMode="NEWPATIENT";
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
			System.out.println("EmgEmgPatientVisitACTION :: GETPATDTL()");
			
			String strMode="";
			this.setSaveSuccessful("");
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
				//Modified to Show the Data for the referral patient Selection too by Singaravelan on 15-10-13
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
			EmgPatientVisitUTL.setPatientDtlByCrno(this,objRequest);
			EmgPatientVisitUTL.getInitEssentials(objRequest, objResponse, this);
			System.out.println("patAmountCollected : "+this.patAmountCollected);
			
			return strMode;
		}
		
		public void getRefDept()
		 {
			 System.out.println("EmgPatientVisitACT :: getRefDept()");
			 EmgPatientVisitUTL.getRefDept_AJAX(objRequest, objResponse);
		 }
		
		/**
		 * saves Emergency Old Patient Visit Details
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param objRequest - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return calls the action "NEW"
		 * /**
		 * saves Emergency New Department Visit Details	
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param objRequest - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return calls the action "NEW"
		 */
		 
		public String SAVE(){
			
			System.out.println("EmgPatientVisitACT :: SAVE()");
			boolean renewStatus=false;
			if(this.getOldDepartmentVisit()==null)	this.oldDepartmentVisit="";
			if(this.getNewDepartmentVisit()==null)	this.newDepartmentVisit="";
			System.out.println("this.getOldDepartmentVisit() :"+this.getOldDepartmentVisit());
			System.out.println("this.getNewDepartmentVisit() :"+this.getNewDepartmentVisit());
			System.out.println("this.getIsReferred() :"+this.getIsReferred());
			
			/*if(this.getIsReferred()!=null && this.getIsReferred().equals("1")){
				this.setIsReferred("on");
			}*/
			if(this.isRefferInOut!=null && this.isRefferInOut.equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)){
				this.setIsRefferInOut("E");
			}
			
			if(this.getOldDepartmentVisit().equalsIgnoreCase("On") && this.getNewDepartmentVisit().equals(""))
			{
				renewStatus=EmgPatientVisitUTL.saveEmgPatientOldDepartmentVisit(objRequest,this,"SAVE");
				//System.out.println("Old Department Visit Saved");
			}
			else if(this.getOldDepartmentVisit().equals("")  && this.getNewDepartmentVisit().equalsIgnoreCase("On"))
			{
				System.out.println("Emg New Department Visit Saved");
				EmgPatientVisitUTL.saveEmgPatientNewDepartmentVisit(objRequest, this);
			}
			else if(this.getNewDepartmentVisit().equalsIgnoreCase("On") && this.getOldDepartmentVisit().equalsIgnoreCase("On"))
			{
				System.out.println("Both Saved");
				EmgPatientVisitUTL.saveEmgPatientVisitStamp(objRequest,this);
				//System.out.println("Patient Visit Saved");
			}
			
			
			if(renewStatus){
				this.setGoWithoutDeptVisitOn("1");
				return "SAME";
			}
			else
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
				EmgPatientVisitUTL.getCashCollectionDetail(objRequest,objResponse, this);
				return "cashCollectionPopup";
				
		}
}

