package ipd.masters.controller.action;


import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.ComponentMstBO;
import ipd.masters.controller.util.ComponentMstUTIL;
import ipd.masters.vo.ComponentMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ComponentMstACTION extends GenericController {
	String strtarget = "";

	/**
	 * calls super class constructor
	 */
	public ComponentMstACTION() // Constructor for ComponentMstACTION
	{
		super(new ComponentMstUTIL(), "/masters/CNTComponentMst");
	}
	
	/**
	 * Forwards Control to the ADD Page
	 * 
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

		strtarget = "add";
		return mapping.findForward(strtarget);
	}
	
	/**
	 * To Save Data in Database & return Control Back to List Page
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

			ComponentMstBO bo = new ComponentMstBO();
			ComponentMstVO vo = (ComponentMstVO) form;
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			strtarget = bo.InsertRecord(vo);
	
			return mapping.findForward(strtarget);
		}
	
	/**
	 * Forwards control to the Modify Page
	 * 
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
			ComponentMstBO bo = new ComponentMstBO();
			ComponentMstVO vo = (ComponentMstVO) form;
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String chk1=request.getParameter("chk");
			bo.modifyRecord(chk1, vo);
			
		} catch (Exception e) {
			// Empty
		}
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}
	
	/**
	 * After Modification, Data is update and save into the database and return back to the list page
	 * 
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
			ComponentMstBO bo = new ComponentMstBO();
			ComponentMstVO vo = (ComponentMstVO) form;
			
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
