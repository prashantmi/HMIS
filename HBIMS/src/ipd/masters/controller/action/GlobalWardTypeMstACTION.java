package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.IpdConfigUtil;
import ipd.masters.bo.GlobalWardTypeMstBO;
import ipd.masters.controller.util.GlobalWardTypeMstUTIL;
import ipd.masters.vo.GlobalWardTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GlobalWardTypeMstACTION extends GenericController 
{
	String target = "";

	public GlobalWardTypeMstACTION() 
	{
		super(new GlobalWardTypeMstUTIL(), "/masters/GlobalWardTypeMstACTION");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 * forwards Control to the Add page and data will insert
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		target = "add";
		return mapping.findForward(target);

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 * After inserting, Data are save in Database & return Control Back to List Page
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
			validateToken(request, response);
			GlobalWardTypeMstBO bo = new GlobalWardTypeMstBO();
			GlobalWardTypeMstVO vo = (GlobalWardTypeMstVO) form;
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//vo.setStrhospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrhospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			target = bo.InsertRecord(vo);
			
			return mapping.findForward(target);
		}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 * Forwards control to the Modify Page
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

			try 
			{
				generateToken(request);
				GlobalWardTypeMstBO bo = new GlobalWardTypeMstBO();
				GlobalWardTypeMstVO vo = (GlobalWardTypeMstVO) form;
				//vo.setStrhospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrhospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
				vo.setChk1(request.getParameter("chk"));
				bo.modifyRecord(request.getParameter("chk"), vo);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		target = "modify";
		return mapping.findForward(target);
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 * After Modification, Data is update n save into the database and return back to the list page
	 */

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		try
		{
			validateToken(request, response);
			GlobalWardTypeMstBO bo = new GlobalWardTypeMstBO();
			GlobalWardTypeMstVO vo = (GlobalWardTypeMstVO) form;
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			//vo.setStrhospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrhospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			target = bo.updateRecord(request.getParameter("chk1"),vo);
		}
		catch (Exception e) 
		{
		}
			if (target.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(target);
		}
}
