package ipd.masters.controller.action;

import ipd.masters.bo.GlobalClinicalDocumentMstBO;
import ipd.masters.controller.util.GlobalClinicalDocumentMstUTIL;
import ipd.masters.vo.GlobalClinicalDocumentMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.IpdConfigUtil;

public class GlobalClinicalDocumentMstACTION extends GenericController {
	String strtarget = "";

	/**
	 * calls super class constructor.
	 */
	public GlobalClinicalDocumentMstACTION() // Constructor for GlobalClinicalDocumentMstACTION
	{
		super(new GlobalClinicalDocumentMstUTIL(), "/masters/GlobalClinicalDocumentMstACTION");
	}
	
	/**
	 * Forwards Control to the Add page and data will insert.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception - No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		strtarget = "add";
		return mapping.findForward(strtarget);
	}
	
	/**
	 * After inserting, Data are save in Database & return Control Back to List Page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception -No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 * 
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

			GlobalClinicalDocumentMstBO bo = new GlobalClinicalDocumentMstBO();
			GlobalClinicalDocumentMstVO vo = (GlobalClinicalDocumentMstVO) form;
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			strtarget = bo.InsertRecord(vo);
			return mapping.findForward(strtarget);
	}
	
	/**
	 * Forwards control to the Modify Page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException - No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		try {
			GlobalClinicalDocumentMstBO bo = new GlobalClinicalDocumentMstBO();
			GlobalClinicalDocumentMstVO vo = (GlobalClinicalDocumentMstVO) form;	
			//vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			String chk1=request.getParameter("chk");
			
			bo.modifyRecord(chk1, vo);
			vo.setStrChk1(request.getParameter("chk"));
		} catch (Exception e) {
			// Empty
		}
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}

	/**
	 * After Modification, Data is update and save into the database and return back to the list page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException - No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		{

			GlobalClinicalDocumentMstBO bo = new GlobalClinicalDocumentMstBO();
			GlobalClinicalDocumentMstVO vo = (GlobalClinicalDocumentMstVO) form;
			
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			//vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
