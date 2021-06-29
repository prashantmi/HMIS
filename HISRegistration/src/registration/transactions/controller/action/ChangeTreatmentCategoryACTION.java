package registration.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;

import registration.transactions.controller.actionsupport.ChangeTreatmentCategorySUP;
import registration.transactions.controller.util.ChangeTreatmentCategoryUTIL;;

public class ChangeTreatmentCategoryACTION extends ChangeTreatmentCategorySUP {

	private String message;
	private String strMsgString;
	private String strMsgType;
	private String strNormalMessage;
	private String strWarning;
	private String strErrorMsg;

	public String execute() {
		return NEW();
	}

	public String NEW() {

		WebUTIL.refreshTransState(super.getObjRequest(),"ChangeTreatmentCategoryACTION");
		ChangeTreatmentCategoryUTIL.setSysdate(super.getObjRequest());
		Status status = new Status();
		status.add(Status.NEW);
		this.setPatCrNo((String) super.getObjRequest().getSession()
				.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		this.setGoFlag("0");
		this.setAfterGo("0");
		WebUTIL.setStatus(super.getObjRequest(), status);

		return "NEW";
	}

	public String GETPATDTL() {
		this.setHmode("GETPATDTL");
		ChangeTreatmentCategoryUTIL.setPatientDtlByCrno(this, super.getObjRequest());
		if(this.getGoFlag().equals("1"))
		{
		populateformvalues();
	
		}
		return "NEW";
	}
	
	public String GETEPISODE() {
		this.setHmode("GETEPISODE");
		ChangeTreatmentCategoryUTIL.setPatientDtlByCrno(this, super.getObjRequest());
		if(this.getGoFlag().equals("1"))
		{
		populateformvalues();
	
		}
		return "NEW";
	}

	public void populateformvalues() {

		System.out.println("PatientReferralACTION :: populateFormValues()");
		message = "Inside populateFormValues_AJAX() method";

		ChangeTreatmentCategoryUTIL.getEssentials(this, objRequest);

	}


	public String SAVE() {

		ChangeTreatmentCategoryUTIL.saveSecondaryCategoryChange(this, super.getObjRequest());
		String normalMessage="Treatment Category for Patient with CR No.";
		normalMessage = normalMessage.concat(this.getPatCrNo());
		normalMessage=normalMessage.concat(" changed successfully");
		this.setNormalMessage(normalMessage);
		this.setGoFlag("0");
		this.setAfterGo("0");
		this.setPatCrNo((String) super.getObjRequest().getSession()
				.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		return "NEW";
	}

	
	//Added by Vasu dated on 9-Apr-2018 to add multiple treatment categories for a particular episode
	public String SAVETREATMENTCATEGORY() {

		ChangeTreatmentCategoryUTIL.saveSecondaryTreatmentCategoryChange(this, super.getObjRequest());
		String normalMessage="Treatment Category for Patient with CR No.";
		normalMessage = normalMessage.concat(this.getPatCrNo());
		normalMessage=normalMessage.concat(" changed successfully");
		this.setNormalMessage(normalMessage);
		this.setGoFlag("0");
		this.setAfterGo("0");
		this.setPatCrNo((String) super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		return "NEW";
	}
	//end Vasu
	
	public String GETDTLFORCATCHANGE() {
		this.setHmode("GETDTLFORCATCHANGE");
		ChangeTreatmentCategoryUTIL.setPatientDtlForCategoryChange(this, super.getObjRequest());
		if(this.getGoFlag().equals("1"))
		{
		populateformvalues();
	
		}
		//return "NEW";
		return "ADD";
	}
	//Added by Vasu on 20.April.2018
	public String GETDTLFORCATCHANGEIPD() {
		this.setHmode("GETDTLFORCATCHANGE");
		ChangeTreatmentCategoryUTIL.setPatientDtlForCategoryChangeIPD(this, super.getObjRequest());
		if(this.getGoFlag().equals("1"))
		{
		populateformvalues();
	
		}
		//return "NEW";
		return "ADD";
	}
	
	public String cancel() throws Exception {
		return NEW();
	}

	public void setServletContext(ServletContext context) {
		this.objContext = context;

	}

}
