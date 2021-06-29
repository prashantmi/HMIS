package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AddServicesIPDTransCNT extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	 
		return this.ADDSERVICE(mapping, form, request, response);
	}
	
	
	public ActionForward ADDSERVICE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		
		return mapping.findForward("addservices");
		
	}
	
	
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		AddServicesIPDTransFB formBean = (AddServicesIPDTransFB) form;
		
		AddServicesIPDTransDATA.initAddService(request, formBean);
		
		return mapping.findForward("addservices");
		
	}
	
	
	public ActionForward PREVDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		AddServicesIPDTransFB formBean = (AddServicesIPDTransFB) form;
		
		AddServicesIPDTransDATA.getPreviousDetails(request, response, formBean);
		
		return null;
		
	}
	
	
	/**
	 * (TARIFFDTLS)--> 
	 * Ajax Function for Pop-Up in Drop Down Data 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward TARIFFDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		AddServicesIPDTransFB formBean = (AddServicesIPDTransFB) form;
		
		AddServicesIPDTransDATA.TARIFFDTLS(request, response, formBean);
		return null;
	}
	
	

	/**
	 * function invoked by Ajax, populates the Tariff Details based on Tariff Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TARIFFCODEDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		AddServicesIPDTransDATA.getTariffCodeDetails(request, response);

		return null;

	}


	/**
	 * function invoked by Ajax, populates the Tariff Details based on Tariff Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward DEFULTTARIFFLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		AddServicesIPDTransDATA.getDefaultTariffDetails(request, response );

		return null;

	}

	
	
	
	
	 /**
	 * (TRFUNIT)--> 
	 * Ajax Function 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward TRFUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		AddServicesIPDTransFB formBean = (AddServicesIPDTransFB) form;
		AddServicesIPDTransDATA.TRFUNIT(request, response,formBean);
		return null;
	}
	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETQTY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		AddServicesIPDTransFB formBean = (AddServicesIPDTransFB) form;
		
		AddServicesIPDTransDATA.getQuantity(request, response, formBean);
		
		return null;
		
	}
	
	
	/**
	 * (ADDSERVICEINSERT)-->
	 * INSERT Logic For IpdBillManagementTrans  Add Service Page  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		AddServicesIPDTransFB formBean = (AddServicesIPDTransFB) form;
       		
       	AddServicesIPDTransDATA.insert(request,formBean);  //defined in DATA class
     
			
		return this.ADDSERVICE(mapping, form, request, response);
	       
	}
	
}
