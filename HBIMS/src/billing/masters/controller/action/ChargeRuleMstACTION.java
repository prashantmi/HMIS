/**
 * 
 */
package billing.masters.controller.action;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


import billing.masters.vo.*;
import billing.masters.controller.fb.*;
import billing.masters.bo.*;
import billing.masters.controller.hlp.*;


/**
 * Developer : DEEPIKA GABA
 * Process Name : Charge Rule Master
 * Date : 2 SEP 2011
 * Version : 1.0
 * Modify Date : 
 *
 */

public class ChargeRuleMstACTION extends CSRFGardTokenAction {
	
	/**
	 * forwards control to the Charge Rule Master.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = null;
		String target = "add";
		HisUtil hisutil = null;
		String strNewChargeType = "";
		String strOldChargeType = "";
		try{

			hisutil = new HisUtil("Billing", "ChargeRuleMstACTION");
			vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
			bo = new ChargeRuleMstBO();
			
			String effectivefrom = hisutil.getASDate("dd-MMM-yyyy");
			vo.setStrEffectiveFrom(effectivefrom);
			
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	
			bo.getRuleDetails(vo);
			
			String strHLP = ChargeRuleMstHLP.getRuleDetails(vo.getRuleDetails());
			
			vo.setStrRuleDetails(strHLP);
			
			//System.out.println("strHLP--"+strHLP);
			
			if (vo.getNewChargeTypeWS()!= null && vo.getNewChargeTypeWS().size() > 0) 
			{
				strNewChargeType = hisutil.getOptionValue(vo.getNewChargeTypeWS(), "","0^Select Value", false);
			} 
			else 
			{
				strNewChargeType = "<option value='0'>Select Value</option>";
			}
			vo.setStrNewChargeTypeCombo(strNewChargeType);
			
			if (vo.getOldChargeTypeWS()!= null && vo.getOldChargeTypeWS().size() > 0) 
			{
				strOldChargeType = hisutil.getOptionValue(vo.getOldChargeTypeWS(), "","0^Select Value", false);
			} 
			else 
			{
				strOldChargeType = "<option value='0'>Select Value</option>";
			}
			vo.setStrOldChargeTypeCombo(strOldChargeType);
			
			TransferObjectFactory.populateData(fb, vo);		
			//fb.setStrNewChargeTypeCombo(vo.getStrNewChargeTypeCombo());
			//fb.setStrOldChargeTypeCombo(vo.getStrOldChargeTypeCombo());
			//fb.setStrRuleDetails(vo.getStrRuleDetails());
			System.out.println();
		}catch(Exception e)
		{
			e.printStackTrace();
		}return mapping.findForward(target);
	}
	
	/**
	 * get Ward Type (New IPD Charge Type) on the 
	 * basis of Hospital Service through AJAX (Rule For).
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward NEWWARDTYPE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = null;
		HisUtil hisutil = null;
		String strNewIPDChargeType = "";

		try {

			hisutil = new HisUtil("Billing", "ChargeRuleMstACTION");
			vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
			 bo = new ChargeRuleMstBO();
			
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			
			//System.out.println("newchargetype-->"+request.getParameter("newchargetype"));
			
			vo.setStrNewChargeTypeId(request.getParameter("newchargetype"));
			
			
			bo.getNewWardType(vo);
				
				if (vo.getNewIPDChargeTypeWS()!= null
						&& vo.getNewIPDChargeTypeWS().size() > 0) {
					strNewIPDChargeType = hisutil.getOptionValue(vo.getNewIPDChargeTypeWS(), "",
							"0^Select Value", false);
				} else {
					strNewIPDChargeType = "<option value='0'>Select Value</option>";
				}
			
			    
					   response.setHeader("Cache-Control", "no-cache");
					   response.getWriter().print(strNewIPDChargeType);
			   
				
		

		} catch (Exception e) {
			e.printStackTrace();

			new HisException("Billing", "ChargeRuleMstACTION->NEWWARDTYPE()", e
					.getMessage());

		}

		return null;
	}
	
	/**
	 * get Ward Type (Old IPD Charge Type) on the 
	 * basis of Hospital Service through AJAX (Rule with).
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward OLDWARDTYPE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = null;
		HisUtil hisutil = null;
		String strOldIPDChargeType = "";

		try {

			hisutil = new HisUtil("Billing", "ChargeRuleMstACTION");
			bo = new ChargeRuleMstBO();
			vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
			 
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			
			//System.out.println("oldchargetype-->"+request.getParameter("oldchargetype"));
			
			vo.setStrOldChargeTypeId(request.getParameter("oldchargetype"));
			
			
			bo.getOldWardType(vo);
				
				if (vo.getOldIPDChargeTypeWS()!= null
						&& vo.getOldIPDChargeTypeWS().size() > 0) {
					strOldIPDChargeType = hisutil.getOptionValue(vo.getOldIPDChargeTypeWS(), "",
							"0^Select Value", false);
				} else {
					strOldIPDChargeType = "<option value='0'>Select Value</option>";
				}
			
			    
					   response.setHeader("Cache-Control", "no-cache");
					   response.getWriter().print(strOldIPDChargeType);
			   
				
		

		} catch (Exception e) {

			new HisException("Billing", "ChargeRuleMstACTION->NEWWARDTYPE()", e
					.getMessage());

		}

		return null;
	}
	
	/**
	 * get Patient category (New Pat Category) on the 
	 * basis of Hospital Service and Ward Type through AJAX (Rule with).
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward NEWPATCAT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = null;
		HisUtil hisutil = null;
		String strNewPatcat = "";

		try {

			hisutil = new HisUtil("Billing", "ChargeRuleMstACTION");
			
			bo = new ChargeRuleMstBO();
			vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
			
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			
			
			vo.setStrNewChargeTypeId(request.getParameter("newchargetype"));
			vo.setStrNewIPDChargeTypeId(request.getParameter("newwardtype"));
			
			
			bo.getNewPatCat(vo);
				
				if (vo.getNewPatCatWS()!= null
						&& vo.getNewPatCatWS().size() > 0) {
					strNewPatcat = hisutil.getOptionValue(vo.getNewPatCatWS(), "",
							"0^Select Value", false);
				} else {
					strNewPatcat = "<option value='0'>Select Value</option>";
				}
			
			    
					   response.setHeader("Cache-Control", "no-cache");
					   response.getWriter().print(strNewPatcat);
			   
				
		

		} catch (Exception e) {

			new HisException("Billing", "ChargeRuleMstACTION->NEWPATCAT()", e
					.getMessage());

		}

		return null;
	}
	
	/**
	 * get Patient category (Old Pat Category) on the 
	 * basis of Hospital Service and Ward Type through AJAX (Rule with).
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward OLDPATCAT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = null;
		HisUtil hisutil = null;
		String strOldPatCat = "";

		try {

			hisutil = new HisUtil("Billing", "ChargeRuleMstACTION");
			
			 bo = new ChargeRuleMstBO();
			 vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			
			
			//vo.setStrOldChargeTypeId(request.getParameter("oldchargetype"));
			//vo.setStrOldIPDChargeTypeId(request.getParameter("oldwardtype"));
			
			
			bo.getOldPatCat(vo);
				
				if (vo.getOldPatCatWS()!= null
						&& vo.getOldPatCatWS().size() > 0) {
					strOldPatCat = hisutil.getOptionValue(vo.getOldPatCatWS(), "",
							"0^Select Value", false);
				} else {
					strOldPatCat = "<option value='0'>Select Value</option>";
				}
			
			    
					   response.setHeader("Cache-Control", "no-cache");
					   response.getWriter().print(strOldPatCat);
			   
				
		

		} catch (Exception e) {

			new HisException("Billing", "ChargeRuleMstACTION->OLDPATCAT()", e
					.getMessage());

		}

		return null;
	}
	
	/**
	 * To insert the data.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = new ChargeRuleMstBO();
		vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
		vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		vo.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		vo.setStrIsValid("1");
		
		bo.insertData(vo);

		vo.setStrNewChargeTypeId("0");
		vo.setStrNewIPDChargeTypeId("0");
		
		TransferObjectFactory.populateData(fb, vo);

		return this.unspecified(mapping, fb, request, response);

	}
	
	/**
	 * This method is used to cancel the Process.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward CANCELPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
		
	}
	
	/**
	 * To Correct the data.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward CORRECTION(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = new ChargeRuleMstBO();
		
		vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
		vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		vo.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
			
		
		bo.correctData(vo);

		return this.unspecified(mapping, fb, request, response);

	}
	
	/**
	 * To MODIFY AND INSERT THE DATA the data.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward MODIFICATION(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = new ChargeRuleMstBO();
		
		vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
		vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		vo.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
			
		vo.setStrLastModSeatId(vo.getStrSeatId());
		vo.setStrIsValid("1");
		
		bo.modifyData(vo);
		
		vo.setStrModify("1");
		TransferObjectFactory.populateData(fb, vo);
		return this.unspecified(mapping, fb, request, response);

	}
	
	/**
	 * To delete the data from the table.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward DELETE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		ChargeRuleMstFB fb = (ChargeRuleMstFB) form;
		ChargeRuleMstVO vo=new ChargeRuleMstVO();
		ChargeRuleMstBO bo = new ChargeRuleMstBO();
		
		vo=(ChargeRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeRuleMstVO", fb);
		vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		vo.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		
		bo.deleteData(vo);
		
		

		return this.unspecified(mapping, fb, request, response);

	}

}
