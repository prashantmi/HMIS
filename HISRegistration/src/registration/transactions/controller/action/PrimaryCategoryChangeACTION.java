package registration.transactions.controller.action;

/**
 * @author s.singaravelan
 * Creation Date:- 04-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpSession;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.actionsupport.PrimaryCategoryChangeSUP;
import registration.transactions.controller.util.PatientReferralUTIL;
import registration.transactions.controller.util.PatientVisitUTL;
import registration.transactions.controller.util.PrimaryCategoryChangeUTIL;
import vo.registration.PatientVO;

@SuppressWarnings("serial")
public class PrimaryCategoryChangeACTION extends PrimaryCategoryChangeSUP
{
	private String isExpired;	
	private String isIdRequired;

	
	public String execute() throws Exception {
		
		System.out.println("PrimaryCategoryChangeACTION :: execute()");
		boolean ff=objRequest.getSession().isNew();
		return NEW();
	}
	 
	public String NEW(){
		
		System.out.println("PrimaryCategoryChangeACTION:: NEW()");	
		this.reset();
		HttpSession session=objRequest.getSession();
		WebUTIL.refreshTransState(objRequest,"PrimaryCategoryChangeACTION");
		this.setPatCrNo((String) super.getObjRequest().getSession()
				.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		PrimaryCategoryChangeUTIL.setSysdate(objRequest);
		this.setIsDetailAvailable("0");
		this.setIsRenewal("0");
		System.out.println("-----NEW Id req-----"+this.getIsIdRequired()+"------");
		System.out.println("-----NEW Pat Id No-----"+this.getPatIdNo()+"------");
		isIdRequired=this.getIsIdRequired();
		return "NEW";
	}


	public String GETPATDTL(){
		
		System.out.println("PatientVisitACT :: GETPATDTL()");
		this.setIsDetailAvailable("0");
		PrimaryCategoryChangeUTIL.setPatientDtlByCrno(this, super.getObjRequest());
		System.out.println("---GETPATDTL Id req---"+this.getIsIdRequired()+"--------");
		System.out.println("-----GETPATDTL Pat Id No-----"+this.getPatIdNo()+"------");
		isIdRequired=this.getIsIdRequired();
		return "NEW";		
		
	}

	public String VERDOC(){
		
		System.out.println("PatientVisitACT :: VERDOC()");
		//PrimaryCategoryChangeUTIL.getVerificationDocumet(this, super.getObjRequest());
		PrimaryCategoryChangeUTIL.getVerificationDocumet(this, super.getObjRequest());
		System.out.println("--VERDOC getPatPrimaryCatValid----"+this.getPatPrimaryCatValid()+"-------");
		if(super.getObjRequest().getSession().getAttribute("optionVerificationDoc")==null)
			this.setErrorMessage("No Verification Document Found");
		this.setIsDetailAvailable("1");
		System.out.println("---VERDOC Id req---"+this.getIsIdRequired()+"--------");
		System.out.println("-----VERDOC Pat Id No-----"+this.getPatIdNo()+"------");
		isIdRequired=this.getIsIdRequired();
		return "NEW";		
		
	}
	
	public String RENEWVERDOC(){
		
		System.out.println("PatientVisitACT :: RENEWVERDOC()");
		//PrimaryCategoryChangeUTIL.getVerificationDocumet(this, super.getObjRequest());
		PatientVO patVo=(PatientVO)super.getObjRequest().getSession().getAttribute(RegistrationConfig.PATIENT_VO);
		PrimaryCategoryChangeUTIL.getRenewalVerificationDocumet(patVo.getPatCatCode(), super.getObjRequest());
		if(super.getObjRequest().getSession().getAttribute("optionVerificationDoc")==null)
			this.setErrorMessage("No Verification Document Found");
		this.setIsDetailAvailable("1");
		
		String primayPatCodeDetails=(String)super.getObjRequest().getSession().getAttribute(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_DETAILS);
		String details[]=primayPatCodeDetails.split("#");
		System.out.println("---details[2]---"+details[2]+"----");
		this.setIsIdRequired(details[2]);
		if(details[2].equals("2"))
		{
			String primayPatCodeIdDetails=(String)super.getObjRequest().getSession().getAttribute(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_ID_DETAILS);
			String idDetails[]=primayPatCodeIdDetails.split("#");
			System.out.println("---idDetails[0]---"+idDetails[0]+"----");
			this.setPatIdNo(idDetails[0]);
		}		
		System.out.println("---RENEWVERDOC---"+this.getPatIdNo()+"--------");

		return "NEW";		
		
	}
	
	public String SAVE() {

		PrimaryCategoryChangeUTIL.savePrimaryCategoryChange(this, super.getObjRequest());
		this.setIsDetailAvailable("0");
		this.setPatCrNo((String) super.getObjRequest().getSession()
				.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		return "NEW";
	}
	
	 public String POPUP()
	 {
		 System.out.println("NewRegistrationAction :: openPatPopup()");
		 String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		 //String alreadyRegisteredFlag= (String)objRequest.getParameter("alreadyRegisteredFlag");
		 //this.patPrimaryCat=patPrimarCatId==null?"":patPrimarCatId;
		 //this.alreadyRegisteredFlag=(alreadyRegisteredFlag==null && alreadyRegisteredFlag.equals(""))?"0":alreadyRegisteredFlag;
		 System.out.println("patPrSimarCatId :" + patPrimarCatId);
		 //System.out.println("this.alreadyRegisteredFlag :" + this.alreadyRegisteredFlag);
		 
		return "popup";
	 }
	 public String openPatPopup()
	 {
		 System.out.println("PatientcatchangeLog:: openPatPopup()");
		 
		  PrimaryCategoryChangeUTIL.getcatLogDtl(this,objRequest);
		
		 return "logpopup";
	 }
	
	 //By Mukund for audit log
	 public String openAuditPopup()
	 {
		 System.out.println("PatientcatchangeLog:: openPatPopup()");
		 
		  PrimaryCategoryChangeUTIL.getauditLogDtl(this,objRequest);
		
		 return "auditlogpopup";
	 }//End:Mukund
	public String CANCEL() throws Exception {
		
		this.clearMessageField();
		//this.setPrintDivContent("");
		return execute();
	 }
	
	 public String CLEAR() throws Exception
	 {
		 this.clearMessageField();
		 this.reset();
		 //this.setPrintDivContent("");
		 return execute();
	 }

	public String getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}

	public String getIsIdRequired() {
		return isIdRequired;
	}

	public void setIsIdRequired(String isIdRequired) {
		this.isIdRequired = isIdRequired;
	}
}
