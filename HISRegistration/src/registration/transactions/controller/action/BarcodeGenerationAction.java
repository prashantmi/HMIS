package registration.transactions.controller.action;
/**
 * Created By 	: Mukund Vinayak
 * Date			: Sept 2017
 */
import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;

import registration.config.CharacterEncoding;
import registration.transactions.controller.actionsupport.BarcodeGenerationSUP;
import registration.transactions.controller.util.BarcodeGenerationUTIL;

public class BarcodeGenerationAction extends BarcodeGenerationSUP
{
	 
	 public String execute() throws Exception
	 {
		boolean ff=objRequest.getSession().isNew();
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
			super.clear();
			WebUTIL.refreshTransState(super.getObjRequest(),"BarcodeGenerationAction");		
			BarcodeGenerationUTIL.setSysdate(super.getObjRequest());
		//	PatientDtlModifcationUTIL.setAllCRNoForModification(super.getObjRequest());
			Status status = new Status();
			status.add(Status.NEW);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			WebUTIL.setStatus(super.getObjRequest(), status);
			this.setAfterGo("0");
			this.setTempAfterGo("0");
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
			CharacterEncoding.setCharacterEncoding(objRequest);
			BarcodeGenerationUTIL.getPatientDetail(this,super.getObjRequest());
			this.setChoice("2");
			return "NEW";
		}	

		public void CHOICE(){	   
		    BarcodeGenerationUTIL.setDetailsByChoice(this,super.getObjRequest(),this.getObjResponse());
		}
		 

		
	 public void setServletContext(ServletContext context) {
			this.objContext=context;
			
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
		 * saves duplicate card Details
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return calls the action "NEW"
		 */
		public String SAVE(){
			CharacterEncoding.setCharacterEncoding(objRequest);
			BarcodeGenerationUTIL.saveBarcodePrinting(objRequest,this);
			this.clear();
			return NEW();		
		}
		
		public String PRINT()
		{			  
			return "PRINTBARCODE";	
		}
		
		public void SAVEFROMOUTSIDE(){
			CharacterEncoding.setCharacterEncoding(objRequest);
			BarcodeGenerationUTIL.saveBarcodePrintingFromOutsideProcess(objRequest,this);
			
		}

	}
