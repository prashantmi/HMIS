package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


public class BillReconcileTransCNT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
		generateToken(request);

		return this.RECONCILE(mapping, form, request, response);
	}

	public ActionForward RECONCILE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
		generateToken(request);

		String target = "reconcile";
		
		
		
	//	System.out.println("Inside Reconcile ");
		
		/*formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillReconcileTransDATA.getRsnRmk(formBean);*/
		
		//BillReconcileTransFB formBean = (BillReconcileTransFB) form;
		//BillReconcileTransDATA.initClientDetail(request, formBean);
		return mapping.findForward(target);
	}

	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws HisException, SQLException 
	{
		generateToken(request);

		BillReconcileTransFB formBean = (BillReconcileTransFB) form;
		String strTarget = "reconcile";
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		 formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
		BillReconcileTransDATA.initReconcilation(formBean);
		return mapping.findForward(strTarget);
	}

	public ActionForward GETBILLDTLSLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		
		//System.out.println("inside BillDtl");
		
		BillReconcileTransFB formBean = (BillReconcileTransFB) form;
		BillReconcileTransDATA.getBillDetailsList(formBean, request, response);
		return null;
	}
	
	/**
	 * This function is called for bill service details [as user selects bill no]
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GETBILLTARIFFDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException 
	{
		
		//System.out.println("inside BServID");
		BillReconcileTransFB formBean = (BillReconcileTransFB) form;
		BillReconcileTransDATA.getTariffOrFinalSettlementDetails(formBean, request, response);
		return null;
	}

	/**
	 * To get group details in final settlement (consolidated details) section
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws Exception
	 * @throws SQLException
	 */	
	public ActionForward FINALSETTLEMENTDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException 
	{
		
		//System.out.println("inside fsetServDtl");
		
		BillReconcileTransFB formBean = (BillReconcileTransFB) form;
		BillReconcileTransDATA.finalSettlementTariffDetails(formBean, request, response);
		return null;
	}

	public ActionForward PopUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{

		String sid = "";
		response.setHeader("Cache-Control", "no-cache");
		try 
		{
			sid = BillReconcileTransDATA.popUpShw(request);
			response.getWriter().print(sid);
		}
		catch (Exception e) 
		{
			new HisException("Bill Reconcile Transaction","BillReconcileTransCNT","BillReconcileTransCNT.PopUp()-->" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * function invoked by ajax, populates the Group Combo box based on Hospital
	 * Service and with or without Pack Flag.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GROUPDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
	      BillReconcileTransDATA.getGroupDetails(request, response );
          return null;
	}

	/**
	 * function invoked by ajax, populates the Tariff List based on Selected
	 * Group Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TARIFFDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		BillReconcileTransDATA.getTariffDetails(request, response);
		return null;
	}
	
	/**
	 * Used to get unit data based on tariff name [In case of new tariff]
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TRFUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		BillReconcileTransDATA.getOffLineTariffUnitDetails(request, response );

		return null;

	}
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	 /**
	    * this method is used to insert the data.
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @return
	 	* @throws HisException
	 	* @throws SQLException
	 	*/

	public ActionForward INSERT1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);

		BillReconcileTransFB formBean = (BillReconcileTransFB) form;
		
		//System.out.println("CNT : INSERT1");
		
		BillReconcileTransDATA.insertData(formBean,request,response);
		
		
		
		return this.RECONCILE(mapping, formBean,request, response);
		
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

		BillReconcileTransDATA.getTariffCodeDetails(request, response);

		return null;

	}
	
	}

	


