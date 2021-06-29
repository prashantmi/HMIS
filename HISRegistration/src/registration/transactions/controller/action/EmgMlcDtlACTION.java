package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Apr 2014
 */

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import registration.config.CharacterEncoding;
import registration.transactions.controller.actionsupport.EmgMlcDtlSUP;
import registration.transactions.controller.util.EmgMlcDtlUTIL;

public class EmgMlcDtlACTION extends EmgMlcDtlSUP
{

	public String execute() throws Exception
	 {
		System.out.println("EmgMlcDtlACTION :: execute()");
		//flagMlcOnlineOffline= RegistrationConfig.MLC_PROCESS_ONLINE_OFFLINE_TYPE;
		return NEW();
	 }
	 
	@SuppressWarnings("static-access")
	public String NEW()
	{
		/*Start : Surabhi
		 * Reason : Changes done to print the certificate for mlc details
		 * date : 7th oct 2016 */
		
		/*System.out.println("EmgMlcDtlACTION :: NEW()");
		this.reset();
		WebUTIL.refreshTransState(objRequest,"EmgMlcDtlACTION");
		EmgMlcDtlUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
		EmgMlcDtlUTIL.setMlcOnlineOfflineMode(this, objRequest);
		
		System.out.println("Is Desk Call :: "+this.isDesk);
		if(this.isDesk!=null && !this.isDesk.equals("0")){	
			return GETPATDTL();
		}
		else{
			this.setIsDesk("0");
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		 	this.setAfterGo("0");
			return "NEW";
		}*/
		
		System.out.println("EmgMlcDtlACTION :: NEW()");
		if(this.isDesk!=null && !this.isDesk.equals("0")){	
			EmgMlcDtlUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			EmgMlcDtlUTIL.setMlcOnlineOfflineMode(this, objRequest);
			return GETPATDTL();
		}
		else if(this.printFlag!=null && this.printFlag.equals("1"))
		{
			this.setIsDesk("0");
		 	this.setAfterGo("0");
		 	
		 	return "NEW";
		}
		else{
			this.reset();
			WebUTIL.refreshTransState(objRequest,"EmgMlcDtlACTION");
			EmgMlcDtlUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			EmgMlcDtlUTIL.setMlcOnlineOfflineMode(this, objRequest);
			this.setIsDesk("0");
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		 	this.setAfterGo("0");
			return "NEW";
		}
 	// end
	}

	/**
	 * gets Patient Detail
	 * @return to the output view called "NEW"
	 */
	
	public String GETPATDTL()
	{
		System.out.println("EmgMlcDtlACTION :: GETPATDTL()");
		this.clearMessageField();
		this.setAfterGo("1");
		
		EmgMlcDtlUTIL.setEpisodeDetailsForMlc(this, objRequest);
		
		return "NEW";
	}
	
	public void getBroughtByDtl_AJAX(){
		EmgMlcDtlUTIL.setBroughtByDtl(this.getnVisitNoIndex(), objRequest, objResponse);
	}
	
	
	 
	public String SAVE(){
		
		System.out.println("EmgMlcDtlACTION :: SAVE()");
		CharacterEncoding.setCharacterEncoding(objRequest);
		String strFlagSaveSuccessfull = EmgMlcDtlUTIL.saveMlcDetails(this, objRequest);
		if(isDesk.equals("1")){
			return GETPATDTL();
		}
		else{
		if("1".equals(strFlagSaveSuccessfull))
			return this.NEW();
		else if("2".equals(strFlagSaveSuccessfull))
			return "NEW";
		else{
			return "NEW";
		}
		}
	}
	
	/*Start : Surabhi
	 * Reason : to print the mlc certificate
	 * date : 7th oct 2016 */
	public String PRINTCERT()
	{
		String patCRNo= (String)objRequest.getParameter("patCRNo");
		this.setPatCrNo(patCRNo);
		EmgMlcDtlUTIL.getMlcDetailByCrNo(this,objRequest);
		System.out.println("Print Flag: "+this.printFlag);
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
		 
		 return execute();
	 }

	
}
