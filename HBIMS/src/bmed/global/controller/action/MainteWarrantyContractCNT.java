package bmed.global.controller.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bmed.global.controller.data.MainteWarrantyContractDATA;



/**
 * @author Aritra Kumar Dhawa Creation Date:- 11-jan-2011. Modifying
 *         Date:-11/Jan/2011. Used For:- Action Class for Non Item Maintenance
 *         Master Process. Team Lead By:- A S Cheema. Module:- BMED(HIS Project)
 * 
 */

public class MainteWarrantyContractCNT extends DispatchAction {

	/** The strtarget. */
	String strtarget = "";

	public ActionForward getData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		
		MainteWarrantyContractDATA.getData(request, response);
		
		return null;

	}
	
	public ActionForward getDataTrans(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		
		MainteWarrantyContractDATA.getDataTrans(request, response);
		
		return null;

	}


}
