package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: May 2014
 */

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.EmgMlcModificationDtlSUP;
import registration.transactions.controller.util.EmgMlcModificationDtlUTIL;

public class EmgMlcModificationDtlACTION extends EmgMlcModificationDtlSUP
{
	public String execute() throws Exception
	 {
		System.out.println("EmgMlcModificationDtlACTION :: execute()");
		//flagMlcOnlineOffline= RegistrationConfig.MLC_PROCESS_ONLINE_OFFLINE_TYPE;
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

			System.out.println("EmgMlcModificationDtlACTION :: NEW()");
			this.reset();
			WebUTIL.refreshTransState(objRequest,"EmgMlcModificationDtlACTION");
			EmgMlcModificationDtlUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			//EmgMlcDtlUTIL.setMlcOnlineOfflineMode(this, objRequest);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
		 	this.setAfterGo("0");
		 	
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
			System.out.println("EmgMlcModificationDtlACTION :: GETPATDTL()");
			this.clearMessageField();
			this.setAfterGo("1");
			
			EmgMlcModificationDtlUTIL.getMlcEssentialDetails(this, objRequest);
			
			return "NEW";
		}
		
		public void getMlcDtl_AJAX(){
			EmgMlcModificationDtlUTIL.getMlcDetails(this, objRequest, objResponse);
		}
		
		public void getMlcParameterDtl_AJAX(){
			EmgMlcModificationDtlUTIL.getMlcParameterDetails(this, objRequest, objResponse);
		}
		
		public void getBroughtByDtl_AJAX(){
			EmgMlcModificationDtlUTIL.getBroughtByDtl(this, objRequest, objResponse);
		}
		
		
		 
		public String SAVE(){
			
			System.out.println("EmgMlcModificationDtlACTION :: SAVE()");
			CharacterEncoding.setCharacterEncoding(objRequest);
			String strFlagSaveSuccessfull = EmgMlcModificationDtlUTIL.saveMlcDetails(this, objRequest);
			if("1".equals(strFlagSaveSuccessfull))
				return this.NEW();
			else{
				return "SAME";
			}
			
		}
		
		
		
		
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
