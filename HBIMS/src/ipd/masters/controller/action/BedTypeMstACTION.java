package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;
import ipd.masters.bo.BedTypeMstBO;
import ipd.masters.bo.WardTypeMstBO;
import ipd.masters.controller.util.BedTypeMstUTIL;
import ipd.masters.vo.BedTypeMstVO;
import ipd.masters.vo.WardTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BedTypeMstACTION extends GenericController {
	String strtarget = "";

	public BedTypeMstACTION() // Constructor for BedStatusMstACTION
	{
		super(new BedTypeMstUTIL(), "/masters/CNTBedTypeMst");
	}
	/**
	 * This function is used to forward the control to add page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		strtarget = "add";
		BedTypeMstVO vo = (BedTypeMstVO) form;
		BedTypeMstBO bo = null;
		HisUtil hisutil = null;
		String strGlobalBedType = "";
		
		try{
			hisutil = new HisUtil("ipd", "BedTypeMstACTION");
			
			bo = new BedTypeMstBO();
			vo.setStrHospital_id(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			
			bo.getGlobalBedType(vo);
			
			
			if (vo.getWrsGlobalBedType()!= null
					&& vo.getWrsGlobalBedType().size() > 0) {
				strGlobalBedType = hisutil.getOptionValue(vo.getWrsGlobalBedType(), "","0^Select Value", false);
			} else {
				strGlobalBedType = "<option value='0'>Select Value</option>";
			}
			vo.setStrGlobalBedTypeCombo(strGlobalBedType);
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
		
		return mapping.findForward(strtarget);

	}
	/**
	 * This function used to forward control to InsertRecord of BOBedTypeMst
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
         //System.out.println("inside save monika");
        // System.out.println("in save cnt hosp=="+request.getSession().getAttribute("HOSPITAL_CODE").toString());
        // if (isTokenValid(request)) {
			validateToken(request, response);
		    strtarget = "add";
			BedTypeMstBO bo =null;
			BedTypeMstVO vo = (BedTypeMstVO) form;
			HisUtil hisutil = null;
			try {
				bo = new BedTypeMstBO();
				hisutil = new HisUtil("ipd", "BedTypeMstACTION");
				String strGlobalBedType = "";
				//System.out.println("in save cnt hosp=="+request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrHospital_id(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.InsertRecord(vo);
				vo.setStrHospital_id(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
				
				bo.getGlobalBedType(vo);
				
				
				if (vo.getWrsGlobalBedType()!= null
						&& vo.getWrsGlobalBedType().size() > 0) {
					strGlobalBedType = hisutil.getOptionValue(vo.getWrsGlobalBedType(), "","0^Select Value", false);
				} else {
					strGlobalBedType = "<option value='0'>Select Value</option>";
				}
				vo.setStrGlobalBedTypeCombo(strGlobalBedType);
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
			//resetToken(request);
			return mapping.findForward(strtarget);
		
	}
/**
 * This function is used to forward control to modify page
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws HisException
 * @throws SQLException
 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BedTypeMstBO bo = new BedTypeMstBO();
		BedTypeMstVO vo = (BedTypeMstVO) form;
		HisUtil hisutil = null;
		String strGlobalBedType = "";
		
		try {
			hisutil = new HisUtil("ipd", "BedTypeMstACTION");
			bo.modifyRecord(request.getParameter("chk"), vo);
			
			vo.setStrHospital_id(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			vo.setStrGlobalBedType(request.getParameter("chk").split("\\@")[0]);
			bo.getGlobalBedType(vo);
			
			
			if (vo.getWrsGlobalBedType()!= null
					&& vo.getWrsGlobalBedType().size() > 0) {
				strGlobalBedType = hisutil.getOptionValue(vo.getWrsGlobalBedType(), vo.getStrGlobalBedType(),"0^Select Value", false);
			} else {
				strGlobalBedType = "<option value='0'>Select Value</option>";
			}
			vo.setStrGlobalBedTypeCombo(strGlobalBedType);
		} catch (Exception e) {
			// Empty
		}
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}
/**
 * This function is used to forward control updateRecord of BOBedTypeMst
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws HisException
 * @throws SQLException
 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		{
			validateToken(request, response);
			BedTypeMstBO bo = new BedTypeMstBO();
			BedTypeMstVO vo = (BedTypeMstVO) form;
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospital_id(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
