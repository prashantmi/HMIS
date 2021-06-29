/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Consumable Charge Discount Rule Master ACTION
 * 
 * Created on: 08-09-2011
 */
                                                                                                       

package billing.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.masters.controller.data.ConsumableChargeDiscountRuleMstDATA;
import billing.masters.controller.fb.ConsumableChargeDiscountRuleMstFB;
import billing.masters.controller.util.ConsumableChargeDiscountRuleMstUTL;


/*
 * Class : ConsumableChargeDiscountRuleMasterACTION
 */

public class ConsumableChargeDiscountRuleMstACTION extends GenericController {

	static ConsumableChargeDiscountRuleMstUTL masterObj = new ConsumableChargeDiscountRuleMstUTL();

	/*
	 * calls super class constructor.
	 */
	public ConsumableChargeDiscountRuleMstACTION() {
		super(masterObj, "/masters/CNTCCRuleMst");
	}
	
	/**
	 * forwards control to the Consumable Charge Discount Rule Master ADD Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		generateToken(request);
		String hoscode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		
		ConsumableChargeDiscountRuleMstFB fb= (ConsumableChargeDiscountRuleMstFB)form;
		ConsumableChargeDiscountRuleMstDATA data= new ConsumableChargeDiscountRuleMstDATA();
		
		fb.setStrHospitalCode(hoscode);
		fb.setStrHospServiceValues(data.getHospServiceValues(fb.getStrChargeTypeId(),hoscode));
		fb.setStrWardTypeValues(data.getStrWardValues(fb.getStrIpdChargeTypeId(),hoscode));
		fb.setStrPatCatValues(data.getStrCategoryValues(fb.getStrPatientCatCode()));
		
		String target = "add";
		return mapping.findForward(target);
	}

	
	/**
	 * To Save Data in Database & return Control Back to List Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		String hoscode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		ConsumableChargeDiscountRuleMstFB fb= (ConsumableChargeDiscountRuleMstFB)form;
		ConsumableChargeDiscountRuleMstDATA data= new ConsumableChargeDiscountRuleMstDATA();
		fb.setStrHospitalCode(hoscode);
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		data.insert(fb);

		return this.ADD(mapping, form, request, response);

	}
	/**
	 * forwards control to the Consumable Charge Discount Rule Master Modify Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		ConsumableChargeDiscountRuleMstDATA data = new ConsumableChargeDiscountRuleMstDATA();
		ConsumableChargeDiscountRuleMstFB fb = (ConsumableChargeDiscountRuleMstFB) form;
		String hoscode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		String chk = request.getParameter("chk");
		data.modifyRecord(chk, fb);
		fb.setStrHospServiceValues(data.getHospServiceValues(fb.getStrChargeTypeId(),hoscode));
		fb.setStrWardTypeValues(data.getStrWardValues(fb.getStrIpdChargeTypeId(),hoscode));
		fb.setStrPatCatValues(data.getStrCategoryValues(fb.getStrPatientCatCode()));
		String target = "modify";

		return mapping.findForward(target);
	}
	
	/**
	 * To update Data in Database & return Control Back to List Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		ConsumableChargeDiscountRuleMstDATA data = new ConsumableChargeDiscountRuleMstDATA();
		ConsumableChargeDiscountRuleMstFB fb = (ConsumableChargeDiscountRuleMstFB) form;
		String hoscode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		String chk = request.getParameter("chk");
		fb.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		fb.setStrLstModSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(hoscode);
		
		data.Update(fb,chk); // calling the function Update() in DATA class
		
		return this.LIST(mapping, form, request, response);
		

		
	}


}