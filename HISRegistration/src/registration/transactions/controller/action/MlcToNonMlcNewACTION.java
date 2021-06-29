package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Apr 2013
 */
import java.util.Enumeration;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;

import registration.config.CharacterEncoding;
import registration.transactions.controller.actionsupport.CRNoSUP;
import registration.transactions.controller.actionsupport.MlcToNonMlcNewSUP;
import registration.transactions.controller.actionsupport.PatientSearchSUP;
import registration.transactions.controller.util.MlcToNonMlcNewUTIL;
import registration.transactions.controller.util.NewRegistrationUTIL;
import registration.transactions.controller.util.PatDetailUTIL;

public class MlcToNonMlcNewACTION extends MlcToNonMlcNewSUP
{
	 
	 public String execute() throws Exception
	 {
		return NEW();
	 }
	 /**
		 * action mainly called at the initial loading of a page or when a form is reset
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "NEW"
		 */
		public String NEW()
		{
			System.out.println("MlcToNonMlcACTION :: NEW()");
			super.clear();
			WebUTIL.refreshTransState(objRequest,"MlcToNonMlcNewACTION");		
			MlcToNonMlcNewUTIL.setSysdateAndDefaultCrNoFormat(objRequest);
			Enumeration<String> strArr = super.getObjRequest().getSession().getAttributeNames();
			while(strArr.hasMoreElements()){
				System.out.println("strArr-->elmenet :"+strArr.nextElement());
			}
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			this.setAfterGo("0");
			return "NEW";
		}

	 /**
		 * gets Patient Detail
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "NEW"
		 */
		public String GETPATDTL()
		{
			System.out.println("MlcToNonMlcACTION :: GETPATDTL()");
			CharacterEncoding.setCharacterEncoding(objRequest);
			MlcToNonMlcNewUTIL.setPatientDtlByCrno(this, objRequest);
			return "NEW";
		}	

		 

		
	 
	 public String cancel() throws Exception
	 {
		 return execute();
	 }
	 public String Clear() throws Exception
	 {
		 return GETPATDTL();
	 }
	 
	 /**
		 * saves MLC to Non MLC Detail (i.e RevokeMLC) card Details
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return calls the action "NEW"
		 */
		public String SAVE(){
			if(MlcToNonMlcNewUTIL.revokeMLCEpisodes(this, objRequest)){
				this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
				this.setAfterGo("0");
				return "NEW";
			}else
				return NEW();		
		}
		public void setServletContext(ServletContext arg0) {
			// TODO Auto-generated method stub
			
		}

	}
