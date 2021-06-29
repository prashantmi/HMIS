package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.IpdConfigUtil;
import ipd.masters.bo.GlobalBedStatusMstBO;
import ipd.masters.controller.util.GlobalBedStatusMstUTIL;
import ipd.masters.vo.GlobalBedStatusMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Adil Wasi
 *
 */

public class GlobalBedStatusMstACTION extends GenericController {
	String strtarget = "";

	public GlobalBedStatusMstACTION() // Constructor for GlobalBedStatusMstACTION
	{
		super(new GlobalBedStatusMstUTIL(), "/masters/GlobalBedStatusMstACTION");
	}
/**
 * is used to forward control to add page
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

		saveToken(request);
		strtarget = "add";
		return mapping.findForward(strtarget);

	}
/**
 * is used to invoke  GlobalBedStatusMstBO's insertRecord()
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
			GlobalBedStatusMstBO bo = new GlobalBedStatusMstBO();
			GlobalBedStatusMstVO vo = (GlobalBedStatusMstVO) form;
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			//vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			if(request.getSession().getAttribute("strIsValid")!=null)
			vo.setStrIsValid(request.getSession().getAttribute("strIsValid").toString());
			
			else
				vo.setStrIsValid("1");
			
			strtarget = bo.InsertRecord(vo);
			//resetToken(request);
			return mapping.findForward(strtarget);
		/*} else {
			return mapping.findForward("add");
		}*/

	}
/**
 * is used to forward control to modify page
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
			GlobalBedStatusMstBO bo = new GlobalBedStatusMstBO();
			GlobalBedStatusMstVO vo = (GlobalBedStatusMstVO) form;	
			//vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			bo.modifyRecord(request.getParameter("chk"), vo);
		} catch (Exception e) {
			// Empty
		}
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}
/**
 * invoke GlobalBedStatusMstBO's updateRecord and then control goes to List page
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

			GlobalBedStatusMstBO bo = new GlobalBedStatusMstBO();
			GlobalBedStatusMstVO vo = (GlobalBedStatusMstVO) form;
			
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			//vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			//System.out.println("Hello Update"+request.getParameter("chk"));
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
