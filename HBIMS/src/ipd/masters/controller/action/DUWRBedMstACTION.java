package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.DUWRBedMstBO;
import ipd.masters.controller.hlp.DUWRBedMstHLP;
import ipd.masters.controller.util.DUWRBedMstUTIL;
import ipd.masters.vo.DUWRBedMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DUWRBedMstACTION extends GenericController {
	
	/**
	 * calls super class constructor
	 */
	public DUWRBedMstACTION() {
		super(new DUWRBedMstUTIL(),"/masters/CNTDUWRBedMst");
				
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
		generateToken(request);
		DUWRBedMstVO formBean = (DUWRBedMstVO) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strTarget = "add";
		formBean.setStrDeptUnitNameValues("0");
		formBean.setStrWardName("0");
		formBean.setStrRoomName("0");
		return mapping.findForward(strTarget);
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
			throws Exception {
				validateToken(request, response);
			    DUWRBedMstVO formBean = (DUWRBedMstVO) form;
				DUWRBedMstBO bo = new DUWRBedMstBO();
				formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.insert(formBean);
				formBean.setStrDeptUnitName("0");
				formBean.setStrWardName("0");
				formBean.setStrRoomName("0");
			    return ADD(mapping,form,request,response);//mapping.findForward(strTarget);
			}
	
	/**
	 * Invoked by Ajax Functions and sets the required Room Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward ROOMVALUE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String[] temp = null;
		String valward = (String) request.getParameter("wardName");// Ward Value
		temp = valward.replace("^", "#").split("#");
		String val = temp[0];
		String val1=temp[1];
		DUWRBedMstVO vo = (DUWRBedMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrWardName(val);
		vo.setStrWardType(val1);
		String comboValues = vo.getRoomNameAdd();
        try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {

			new HisException("Ipd Master",
					"CNTDUWRBedMst.WARDNMVALUE()", e.getMessage());
		}
		return null;
	}

	/**
	 * Invoked by Ajax Functions and sets the required Left Bed Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward LBEDVALUE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		
		String[] temp = null;
		String[] temp1 = null;
		String valdeptunit = (String) request.getParameter("deptunitName");
	    String valward = (String) request.getParameter("wardName");// ward value
	    String valroom = (String) request.getParameter("roomName");// room value
	
		temp = valward.replace("^", "#").split("#");
		String val = temp[0];
		temp1 = valroom.replace("^", "#").split("#");
		String var = temp1[0];
		String var1 = temp1[1];
		
		DUWRBedMstVO vo = (DUWRBedMstVO) form;
		vo.setStrDeptUnitName(valdeptunit);
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		vo.setStrWardName(val);
		vo.setStrRoomName(var);
		vo.setStrRoomType(var1);
		String comboValues = vo.LBedAdd();
         
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {

			new HisException("Ipd Master",
					"CNTDUWRBedMst.ROOMVALUE()", e.getMessage());
		}
		return null;
	}

	/**
	 * Invoked by Ajax Functions and sets the required Right Bed Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward RBEDVALUE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		
		String[] temp1 = null;
		String[] temp2 = null;
		
		String valdeptunit = (String) request.getParameter("deptunitName");// deptunit value
		String valward = (String) request.getParameter("wardName");// ward value
		String valroom = (String) request.getParameter("roomName");// room value
		
		temp1 = valward.replace("^", "#").split("#");
		temp2 = valroom.replace("^", "#").split("#");
		 
		String val = temp1[0];
		String var = temp2[0];
		
		DUWRBedMstVO vo = (DUWRBedMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrDeptUnitName(valdeptunit);
		vo.setStrWardName(val);
		vo.setStrRoomName(var);
		String comboValues = vo.RBedAdd();

		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {

			new HisException("Ipd Master",
					"CNTDUWRBedMst.ROOMVALUE()", e.getMessage());
		}
		return null;
	}

		
	/**
	 * Forwards control to the Modify Page
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
		String strTarget = null;
		DUWRBedMstVO formBean = (DUWRBedMstVO) form;
		DUWRBedMstBO bo = new DUWRBedMstBO();
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strChk = request.getParameter("chk");
		bo.modify(strChk, formBean);
		strTarget = "modify";
		return mapping.findForward(strTarget);
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
			throws Exception {
		
		validateToken(request, response);
		boolean fReturnValue = false;
		DUWRBedMstVO formBean = (DUWRBedMstVO) form;
		DUWRBedMstBO bo = new DUWRBedMstBO();
		formBean.setStrLastModSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strChk = request.getParameter("chk");
		fReturnValue = bo.update(strChk, formBean);

		if (fReturnValue) {

			return this.LIST(mapping, form, request, response);
		} else {

			return this.MODIFY(mapping, form, request, response);
		}
	}

	/**
	 * For View the data from List page using HLP class
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		    String target = "";
		    DUWRBedMstBO bo = new DUWRBedMstBO();
			DUWRBedMstVO vo = (DUWRBedMstVO) form;
			bo.viewRecord(request.getParameter("chk"), vo);
		
			DUWRBedMstHLP strHlp = new DUWRBedMstHLP();
			String strBedView = strHlp.getStrBedView(vo);
			
			vo.setStrBedView(strBedView);
			target = "view";
		    return mapping.findForward(target);
	}
}
