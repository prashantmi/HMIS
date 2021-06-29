package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.IpdConfigUtil;
import ipd.masters.bo.GlobalDischargeTypeMstBO;
import ipd.masters.controller.util.GlobalDischargeTypeMstUTIL;
import ipd.masters.vo.GlobalDischargeTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GlobalDischargeTypeMstACTION extends GenericController {
	String strtarget = "";

	public GlobalDischargeTypeMstACTION() // Constructor for DischargeTypeMst
	{
		super(new GlobalDischargeTypeMstUTIL(), "/masters/GlobalDischargeTypeMstACTION");
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
		generateToken(request);
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
			validateToken(request, response);
		//if (isTokenValid(request)) {
			GlobalDischargeTypeMstBO globalDischargeTypeMstBO = new GlobalDischargeTypeMstBO();
			GlobalDischargeTypeMstVO globalDischargeTypeMstVO = (GlobalDischargeTypeMstVO) form;
			
			globalDischargeTypeMstVO.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//globalDischargeTypeMstVO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			globalDischargeTypeMstVO.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE);
			strtarget = globalDischargeTypeMstBO.InsertRecord(globalDischargeTypeMstVO);
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

		
			generateToken(request);
			GlobalDischargeTypeMstBO globalDischargeTypeMstBO = new GlobalDischargeTypeMstBO();
			GlobalDischargeTypeMstVO globalDischargeTypeMstVO = (GlobalDischargeTypeMstVO) form;
			//globalDischargeTypeMstVO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			globalDischargeTypeMstVO.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE);
			globalDischargeTypeMstBO.modifyRecord(request.getParameter("chk"), globalDischargeTypeMstVO);
		
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
			throws Exception {
		{

			validateToken(request, response);
			GlobalDischargeTypeMstBO globalDischargeTypeMstBO = new GlobalDischargeTypeMstBO();
			GlobalDischargeTypeMstVO globalDischargeTypeMstVO = (GlobalDischargeTypeMstVO) form;
			
			globalDischargeTypeMstVO.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			//globalDischargeTypeMstVO.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			globalDischargeTypeMstVO.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE);
			//System.out.println("Hello Update"+request.getParameter("chk"));
			strtarget = globalDischargeTypeMstBO.updateRecord(request.getParameter("chk"), globalDischargeTypeMstVO);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
