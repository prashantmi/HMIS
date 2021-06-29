package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.IpdConfigUtil;
import ipd.masters.bo.GlobalBedTypeMstBO;
import ipd.masters.controller.util.GlobalBedTypeMstUTIL;
import ipd.masters.vo.GlobalBedTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GlobalBedTypeMstACTION extends GenericController {
	String strtarget = "";

	public GlobalBedTypeMstACTION() // Constructor for GlobalBedTypeMstACTION
	{
		super(new GlobalBedTypeMstUTIL(), "/masters/GlobalBedTypeMstACTION");
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
		//saveToken(request);
		strtarget = "add";
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
			GlobalBedTypeMstBO bo = new GlobalBedTypeMstBO();
			GlobalBedTypeMstVO vo = (GlobalBedTypeMstVO) form;
			//System.out.println("in save cnt hosp=="+request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//vo.setStrHospital_id(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospital_id(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			strtarget = bo.InsertRecord(vo);
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

		try {
			GlobalBedTypeMstBO bo = new GlobalBedTypeMstBO();
			GlobalBedTypeMstVO vo = (GlobalBedTypeMstVO) form;
			bo.modifyRecord(request.getParameter("chk"), vo);
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
			throws HisException, SQLException {
		{

			GlobalBedTypeMstBO bo = new GlobalBedTypeMstBO();
			GlobalBedTypeMstVO vo = (GlobalBedTypeMstVO) form;
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			//vo.setStrHospital_id(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospital_id(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
