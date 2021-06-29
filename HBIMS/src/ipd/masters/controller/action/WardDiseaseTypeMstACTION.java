package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.WardDiseaseTypeMstBO;
import ipd.masters.controller.util.WardDiseaseTypeMstUTIL;
import ipd.masters.vo.WardDiseaseTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WardDiseaseTypeMstACTION extends GenericController {
	String strtarget = "";
	
	/**
	 * calls super class constructor.
	 */
	public WardDiseaseTypeMstACTION() // Constructor for WardDiseaseTypeMstACTION
	{
		super(new WardDiseaseTypeMstUTIL(), "/masters/CNTWardDiseaseTypeMst");
	}
	
	/**
	 * Forwards Control to the Add page and data will insert.
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
	 * After inserting, Data are save in Database & return Control Back to List Page.
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
		
			WardDiseaseTypeMstBO bo = new WardDiseaseTypeMstBO();
			WardDiseaseTypeMstVO vo = (WardDiseaseTypeMstVO) form;
					
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
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
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		try {
			WardDiseaseTypeMstBO bo = new WardDiseaseTypeMstBO();
			WardDiseaseTypeMstVO vo = (WardDiseaseTypeMstVO) form;	
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo.modifyRecord(request.getParameter("chk"), vo);
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
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		{

			WardDiseaseTypeMstBO bo = new WardDiseaseTypeMstBO();
			WardDiseaseTypeMstVO vo = (WardDiseaseTypeMstVO) form;
			
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
