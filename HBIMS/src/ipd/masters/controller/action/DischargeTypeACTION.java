package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.DischargeTypeMstBO;
import ipd.masters.controller.util.DischargeTypeMstUTIL;
import ipd.masters.vo.DischargeTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DischargeTypeACTION extends GenericController {
	String strtarget = "";

	public DischargeTypeACTION() // Constructor for DischargeTypeMst
	{
		super(new DischargeTypeMstUTIL(), "/masters/CNTDischargeTypeMst");
	}
/**
 * forward control to add page
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
 * Invoke BODischargeTypeMst's insertRecord method and then forward control to add
 * page
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
		//if (isTokenValid(request)) {
			DischargeTypeMstBO bo = new DischargeTypeMstBO();
			DischargeTypeMstVO vo = (DischargeTypeMstVO) form;
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			strtarget = bo.InsertRecord(vo);
			//resetToken(request);
			return mapping.findForward(strtarget);
	//	} else {
		//	return mapping.findForward("add");
		//}
	}
/**
 * forward control to modify page
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
			throws Exception, SQLException {

		
			DischargeTypeMstBO bo = new DischargeTypeMstBO();
			DischargeTypeMstVO vo = (DischargeTypeMstVO) form;
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo.modifyRecord(request.getParameter("chk"), vo);
		
			// Empty
		
		strtarget = "modify";
		return mapping.findForward(strtarget);
		
	}
/**
 * invoke BODischargeTypeMst's updateRecord and 
 * forward control to modify page
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

			DischargeTypeMstBO bo = new DischargeTypeMstBO();
			DischargeTypeMstVO vo = (DischargeTypeMstVO) form;
			
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//System.out.println("Hello Update"+request.getParameter("chk"));
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
