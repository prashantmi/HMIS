/* Counter Master ACTION
 * Created By: Pawan Kumar B N
 * Created On: 01-Sep-2011
 */
package billing.masters.controller.action;

import hisglobal.masterutil.GenericController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;
import billing.masters.controller.data.CounterMstDATA;
import billing.masters.controller.fb.CounterMstFB;
import billing.masters.controller.util.CounterMstUTL;

/**
 * The Class CNTcounterMst.
 */
public class CounterMstACTION extends GenericController {

	/**
	 * Instantiates a new CNT Counter Master.
	 */
	public CounterMstACTION() {
		super(new CounterMstUTL(), "masters/CNTcounterMst");
	}

	/**
	 * forward to the Add page.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		generateToken(request);
		String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
				.toString();
		String moduleId=  request.getSession().getAttribute("USERVALUE").toString();// BillConfigUtil.BILL_MODULE_ID; //   
		

		CounterMstFB formBean = (CounterMstFB) form;

		formBean.setStrHospitalCode(hosCode);
		formBean.setModuleId(moduleId);
		/*try {
			//data.initialAdd(formBean);
			data.getBuildingCombo(formBean);

		} catch (Exception e) {
			strmsgText = "billing.masters.CNTCounterMst.ADD() --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"CounterMaster->initialAdd()", strmsgText);
			formBean.setErrmsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}*/

		String target = "add";
		return mapping.findForward(target);
	}

	/**
	 * retrieves data and forward the control to modify page
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		generateToken(request);
		String target = "modify";
		String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
				.toString();
		
		CounterMstFB fb = (CounterMstFB) form;
		CounterMstDATA data = new CounterMstDATA();

		fb.setStrHospitalCode(hosCode);

		fb.setStrChk1(request.getParameter("chk"));
		data.getDataToModify(fb, request.getParameter("chk"));

		/*try {
			data.getBuildingCombo(fb);
			data.getBlockCombo(fb);
			data.getFloorCombo(fb);
		} catch (Exception e) {
			strmsgText = "billing.masters.CNTcounterMst.MODIFY() --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"CNTcounterMst-->MODIFY()", strmsgText);
			fb.setErrmsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}*/

		return mapping.findForward(target);
	}

	/**
	 * Add's New Entries.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward SAVEADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
		CounterMstDATA data = new CounterMstDATA();
		CounterMstFB fb = (CounterMstFB) form;
		fb.setSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String moduleId= request.getSession().getAttribute("USERVALUE").toString(); //BillConfigUtil.BILL_MODULE_ID; //
		//System.out.println("moduleis"+moduleId);
		if(moduleId==null)
		{
		
			fb.setModuleId("0");
		}
		else
		{
			fb.setModuleId(request.getSession().getAttribute("USERVALUE").toString());
		}
		//fb.setModuleId(moduleId);
		fb.setStrBuildingId("0");
		fb.setStrFloorId("0");
		fb.setStrBlockId("0");
		data.chkAddCntr(fb);

		fb.setStrBuildingId("0");

		return this.ADD(mapping, fb, request, response);

	}

	/**
	 * Updates the Data.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ActionForward SAVEMODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
		boolean retVal = false;
		CounterMstFB fb = (CounterMstFB) form;
		CounterMstDATA data = new CounterMstDATA();

		fb.setLstSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		fb .setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String moduleId= request.getSession().getAttribute("USERVALUE").toString(); //BillConfigUtil.BILL_MODULE_ID; //
		//System.out.println("moduleis"+moduleId);
		if(moduleId==null)
		{
			fb.setModuleId(BillConfigUtil.BILL_MODULE_ID);
		}
		else
		{
			fb.setModuleId(request.getSession().getAttribute("USERVALUE").toString());
		}
		
		retVal = data.chkModifyCntr(fb, request.getParameter("chk"));

		fb.setStrBuildingId("0");

		if (retVal) {
			return this.LIST(mapping, fb, request, response);
		} else {
			return this.MODIFY(mapping, fb, request, response);

		}
	}

	
}
