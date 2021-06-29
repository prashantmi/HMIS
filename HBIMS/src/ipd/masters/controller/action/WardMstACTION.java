package ipd.masters.controller.action;

import hisglobal.masterutil.GenericController;
import ipd.IpdConfigUtil;
import ipd.masters.bo.WardMstBO;
import ipd.masters.controller.util.WardMstUTIL;
import ipd.masters.vo.WardMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WardMstACTION extends GenericController {
	String target = "";

	public WardMstACTION() {
		super(new WardMstUTIL(), "/masters/CNTWardMst");
	}

	/**
	 * This function is used to forward control to add page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		
		generateToken(request);
		WardMstVO vo=(WardMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//String comboValues = vo.getStrRoom();
		//	saveToken(request);
		
		
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospitalCode());
		vo.setStrPrivateWardType(ipdConfigUtil.getStrPrivateWardType());
		target = "add";
		vo.setStrWardType("0");
		vo.setStrwardCode("0");
		vo.setStrBuilding("0");
		vo.setStrBlock("0");
		return mapping.findForward(target);

	}
/**
 * This function is used to bring blocks on add page through ajax
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 * @throws SQLException
 */
	public ActionForward MODEBLOCK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

	
		String valmode = (String) request.getParameter("modName");// Building
		String errMsg = "";
		WardMstVO vo = (WardMstVO) form;
		vo.setStrBuilding(valmode);
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String comboValues = vo.getBlockAdd();

		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {

			errMsg = "CNTWardMst -->" + e.getMessage();
			throw new Exception(errMsg);// TODO Auto-generated catch block

		}
		return null;
	}
/**
 * This function is used to bring room number in room combo
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 * @throws SQLException
 */
	public ActionForward MODEROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String strBuildingId = (String) request.getParameter("strBuildingId");// building
																	// value
		String strBlockId = (String) request.getParameter("strBlockId");// block
		String errMsg = "";
		WardMstVO vo = (WardMstVO) form;
		//vo.setStrBuilding(val);
		
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrBuilding(strBuildingId);
		vo.setStrBlock(strBlockId);
		String comboValues = vo.getStrRoom();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {

			errMsg = "CNTWardMst -->" + e.getMessage();
			throw new Exception(errMsg);// TODO Auto-generated catch block

		}
		return null;
	}

	// MODEROOMCOR mode called from ajax function for creating list box for Room
	// No in case of Correction

	public ActionForward MODEROOMCOR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String[] temp = null;
		String[] temp1 = null;
		String valmode = (String) request.getParameter("modName");// building
						// value
		String valmode1 = (String) request.getParameter("modName1");// block
																	// value

		// System.out.print("valmode----------------->"+valmode);
		// System.out.print("valmode1----------------->"+valmode1);
		temp = valmode.replace("^", "#").split("#");
		temp1 = valmode1.replace("^", "#").split("#");
		String val = temp[0];
		String val1 = temp1[0];
		// System.out.print("inside getRoomDtlcc- Building--------->"+val);
		// System.out.print("inside getRoomDtlcc-----Block----->"+val1);

		String errMsg = "";
		WardMstVO vo = (WardMstVO) form;
		vo.setStrBuilding(val);
		vo.setStrBlock(val1);
		String comboValues = vo.getRoom();

		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {

			errMsg = "CNTWardMst -->" + e.getMessage();
			throw new Exception(errMsg);// TODO Auto-generated catch block

		}
		return null;
	}

	/** 
	 * This function invoke BOWardMst's  InsertRecord() on the basis of result it forward response to back to add page.if record is successfully inserted then it show message it is saved successfully
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

	
			WardMstBO bo = new WardMstBO();
			WardMstVO vo = (WardMstVO) form;
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			target = bo.InsertRecord(vo);
			//resetToken(request);
			return ADD(mapping,form,request,response);//mapping.findForward(target);
			
	}
	
	

	/**
	 * this function is used to forward control to modify page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

			generateToken(request);
			String target = "";	

			WardMstBO bo = new WardMstBO();
			WardMstVO vo = (WardMstVO) form;
			
			vo.setStrChk(request.getParameter("chk"));
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo.modifyRecord(request.getParameter("chk"), vo);
			IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospitalCode());
			vo.setStrPrivateWardType(ipdConfigUtil.getStrPrivateWardType());
			request.setAttribute("combo", vo.getStrRRoomModi());
		
			target = "modify";
			return mapping.findForward(target);
		//return null;
	}

	/**
	 * This function is used to invoke BOWardMst's updateRecord & forward control back to list page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException

	{
		boolean target = false;
		WardMstVO vo = (WardMstVO) form;
		WardMstBO bo = new WardMstBO();
		vo.setStrLastModifySeatId(request.getSession().getAttribute("SEATID").toString());
		vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());			
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		target = bo.updateRecord(request.getParameter("chk"), vo);
		if (target) {
			return this.LIST(mapping, form, request, response);
		} else {
			return this.MODIFY(mapping, form, request, response);
		}
	}
/**
 * This function is used to invoke BOWardMst's viewRecord() and give response in the form of view jsp
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return ActionForward 
 * @throws Exception
 * @throws SQLException
 */
	public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		String target = "";
		try {

			WardMstBO bo = new WardMstBO();
			WardMstVO vo = (WardMstVO) form;
			bo.viewRecord(request.getParameter("chk"), vo);
		} catch (Exception e) {
		}
		target = "view";
		return mapping.findForward(target);

	}

}
