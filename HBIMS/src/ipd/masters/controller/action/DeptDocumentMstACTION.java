package ipd.masters.controller.action;

import ipd.masters.bo.DeptDocumentMstBO;
import ipd.masters.controller.hlp.DeptDocumentMstHLP;
import ipd.masters.controller.util.DeptDocumentMstUTIL;
import ipd.masters.vo.DeptDocumentMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

public class DeptDocumentMstACTION extends GenericController {
	String strtarget = "";

	/**
	 * calls super class constructor
	 */
	public DeptDocumentMstACTION() // Constructor for DeptDocumentMstACTION
	{
		super(new DeptDocumentMstUTIL(), "/masters/CNTDeptDocumentMst");
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
			throws Exception, SQLException {
		strtarget = "add";
		DeptDocumentMstVO vo=(DeptDocumentMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
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
		
		    DeptDocumentMstBO bo = new DeptDocumentMstBO();
		    DeptDocumentMstVO vo = (DeptDocumentMstVO) form;
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		    vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			strtarget = bo.InsertRecord(vo);
		
			return mapping.findForward(strtarget);
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

		String strTarget = null;
		DeptDocumentMstVO formBean = (DeptDocumentMstVO) form;
		DeptDocumentMstBO bo = new DeptDocumentMstBO();
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
			throws HisException, SQLException {

		
		DeptDocumentMstVO formBean = (DeptDocumentMstVO) form;
		DeptDocumentMstBO bo = new DeptDocumentMstBO();
		formBean.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strChk = request.getParameter("chk");
		
		strtarget = bo.update(strChk, formBean);
		if (strtarget.equals("list")) 

			return this.LIST(mapping, form, request, response);
		 else 

			return mapping.findForward(strtarget);
		
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
		    DeptDocumentMstBO bo = new DeptDocumentMstBO();
		    DeptDocumentMstVO vo = (DeptDocumentMstVO) form;
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo.viewRecord(request.getParameter("chk"), vo);
			DeptDocumentMstHLP strHlp = new DeptDocumentMstHLP();
			String strCFSRView = strHlp.getStrCFSRView(vo);
			
			vo.setStrCFSRView(strCFSRView);
			
		target = "view";
		return mapping.findForward(target);
	}
	
	/**
	 * Invoked by Ajax Functions and sets the required Added Component Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward DEPTDOCVALUE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		
		String strDepartment = (String) request.getParameter("deptName");// compexam Value
		String strDocument = request.getParameter("docName");
		
		DeptDocumentMstVO vo = (DeptDocumentMstVO) form;		
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrDeptCode(strDepartment);
		vo.setStrDocumentCode(strDocument);
		String comboValues = vo.getHlpData();

				
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {

			new HisException("Ipd Master",
					"CNTDeptDocumentMst.DEPTDOCVALUE()", e.getMessage());
		}
		return null;
	}
}
