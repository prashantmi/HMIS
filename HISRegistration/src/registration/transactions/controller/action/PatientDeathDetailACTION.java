package registration.transactions.controller.action;
/**
 * Developed By : Aadil Wasi
 * Date			: May 2013
 */

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientDeathDetailSUP;
import registration.transactions.controller.util.PatientDeathDetailUTIL;
import hisglobal.config.HISConfig;//961011900004354

public class PatientDeathDetailACTION  extends PatientDeathDetailSUP
{
	public String execute() throws Exception
	 {
		System.out.println("PatientDeathDetailACTION :: execute()");
		//flagMlcOnlineOffline= RegistrationConfig.MLC_PROCESS_ONLINE_OFFLINE_TYPE;
		return NEW();
	 }
	 
	public String NEW()
	{
		/*Start : Surabhi
		 * Reason : to get the patient death details for the certificate
		 * date : 7th oct 2016 */

		/*System.out.println("PatientDeathDetailACTION :: NEW()");
		WebUTIL.refreshTransState(objRequest,"PatientDeathDetailACTION");
		
		if(this.isDesk!=null && !this.isDesk.equals("0")){
			this.setNormalBodyHandover(RegistrationConfig.NORMAL_BODY_HANDOVER);
			//this.setMlcBodyHandover(RegistrationConfig.MLC_BODY_HANDOVER);
			this.setIsDelivery(RegistrationConfig.IS_DELIVERY_NO);
			this.setIsPregnant(RegistrationConfig.IS_PREGNATNT_NO);
			PatientDeathDetailUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			return GETPATDTL();
		}
		else{
			
			this.setPatCrNo("");
			this.reset();
			this.setNormalBodyHandover(RegistrationConfig.NORMAL_BODY_HANDOVER);
			//this.setMlcBodyHandover(RegistrationConfig.MLC_BODY_HANDOVER);
			this.setIsDelivery(RegistrationConfig.IS_DELIVERY_NO);
			this.setIsPregnant(RegistrationConfig.IS_PREGNATNT_NO);
			
			PatientDeathDetailUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		 	this.setAfterGo("0");
		 	
		 	return "NEW";
		}*/
		
			System.out.println("PatientDeathDetailACTION :: NEW()");
			this.setIsSnomedServiceOn("1");
		if(!HISConfig.HIS_SNOMEDCT_SERVICES_ON.equalsIgnoreCase("ON")){
			this.setIsSnomedServiceOn("0");
		}
		
		if(this.isDesk!=null && !this.isDesk.equals("0")){
			this.setNormalBodyHandover(RegistrationConfig.NORMAL_BODY_HANDOVER);
			this.setIsDelivery(RegistrationConfig.IS_DELIVERY_NO);
			this.setIsPregnant(RegistrationConfig.IS_PREGNATNT_NO);
			PatientDeathDetailUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			return GETPATDTL();
		}
		else if(this.printFlag!=null && this.printFlag.equals("1"))
		{
			this.setIsDesk("0");
			this.reset();
			this.setAfterGo("1");
			this.setNormalBodyHandover(RegistrationConfig.NORMAL_BODY_HANDOVER);
			this.setIsDelivery(RegistrationConfig.IS_DELIVERY_NO);
			this.setIsPregnant(RegistrationConfig.IS_PREGNATNT_NO);
			this.setAfterGo("0");
		 	
		 	return "NEW";
		}
		else{
			this.reset();
			this.setIsDesk("0");
			this.setPatCrNo("");
			this.reset();
			this.setNormalBodyHandover(RegistrationConfig.NORMAL_BODY_HANDOVER);
			this.setIsDelivery(RegistrationConfig.IS_DELIVERY_NO);
			this.setIsPregnant(RegistrationConfig.IS_PREGNATNT_NO);
			
			PatientDeathDetailUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		 	this.setAfterGo("0");
		 	
		 	return "NEW";
		 	
		 	//end
		}
	}

	/**
	 * gets Patient Detail
	 * @return to the output view called "NEW"
	 */
	
	public String GETPATDTL()
	{
		System.out.println("PatientDeathDetailACTION :: GETPATDTL()");
		this.clearMessageField();
		this.setAfterGo("1");
		PatientDeathDetailUTIL.getPatientdeathDetailEssential(this, objRequest);
		
		return "NEW";
	}
		
	/*public void getBroughtByDtl_AJAX(){
		PatientDeathDetailUTIL.setBroughtByDtl(this.getnVisitNoIndex(), objRequest, objResponse);
	}*/
	
	
	 
	public String SAVE(){
		
		System.out.println("PatientDeathDetailACTION :: SAVE()");
		CharacterEncoding.setCharacterEncoding(objRequest);
		boolean flagSaveSuccessfull = PatientDeathDetailUTIL.savePatientDeathDetail(this, objRequest);
		this.setPrintFlag("1");
		if(flagSaveSuccessfull)
			return this.NEW();
		else 
			return "NEW";
	}
	
	/*Start : Surabhi
	 * Reason : to print the patient death details for the certificate
	 * date : 7th oct 2016 */
	public String PRINTCERT()
	{
		/*PatientDeathDetailUTIL.getDeathDetailByCrNo(this,objRequest);
		
		return "PRINT";*/
		String patCRNo= (String)objRequest.getParameter("patCRNo");
		this.setPatCrNo(patCRNo);
		PatientDeathDetailUTIL.getDeathDetailByCrNo(this,objRequest);
		
		return "PRINT";
	}
	// end
	
	public String CANCEL() throws Exception
	 {
		this.clearMessageField();
		return this.NEW();
	 }
	 public String CLEAR() throws Exception
	 {
		 this.clearMessageField();
		 /* ## 	Modification Log							
		 	##		Modify Date				:12thDec'14 
		 	##		Reason	(CR/PRS)		:Bug fix 7727
		 	##		Modify By				:Sheeldarshi 
		*/
		 String afterGoClear=this.getAfterGo();
		 this.reset();
		 //return execute();
		 if(afterGoClear.equals("1"))
		 return GETPATDTL();
		 else
		 {
		this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		return "NEW";
		 }
		 //End:Sheeldarshi
		 
	 }
}
