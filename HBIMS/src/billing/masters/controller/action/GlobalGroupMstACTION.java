package billing.masters.controller.action;

/* Global Group Master ACTION
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;

import billing.masters.controller.data.GlobalGroupMstDATA;
import billing.masters.controller.fb.GlobalGroupMstFB;
import billing.masters.controller.util.GlobalGroupMstUTIL;

public class GlobalGroupMstACTION extends GenericController
{

	String target = null;

	public GlobalGroupMstACTION()
	{

		super(new GlobalGroupMstUTIL(), "masters/CNTGroupMst");

	}

	/**
	 * forwards control to the Add Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		generateToken(request);
		target = "add";
		return mapping.findForward(target);

	}

	/**
	 * invokes insert Logic and forwards control to the Add Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		validateToken(request, response);
		int count = -1;
		String errmsg = "";
		String msg = "";
		GlobalGroupMstDATA data = new GlobalGroupMstDATA();
		GlobalGroupMstFB fb = (GlobalGroupMstFB) form;
		fb.setStrhospitalcode(BillConfigUtil.SUPER_HOSPITAL_CODE.toString());
		fb.setStrseatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrisValid(request.getSession().getAttribute("isvalid")
				.toString());

		count = data.InsertRecord(fb);
		if (count == 0)
		{

			errmsg = "Record already exist!";
			fb.setWarnings(errmsg);

		}

		if (count == 1)
		{
			errmsg = "Record not saved successfully!";
			fb.setStrErrorMsg(errmsg);

		}
		if (count == 2)
		{
			msg = "Record Saved Successfully!";
			fb.setMsg(msg);

		}

		return this.ADD(mapping, form, request, response);

	}

	/**
	 * forwards control to the Modify Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		generateToken(request);
		try
		{
			GlobalGroupMstDATA data = new GlobalGroupMstDATA();
			GlobalGroupMstFB fb = (GlobalGroupMstFB) form;

			data.modifyRecord(request.getParameter("chk"), fb);
			fb.setChk1(request.getParameter("chk"));
		} 
		catch (Exception e)
		{
		}
		target = "modify";
		return mapping.findForward(target);

	}

	/**
	 * invokes update Logic and forwards control to the list or Modify Page
	 * based on the Logic output
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException
	{
		validateToken(request, response);
		{
			int count = -1;
			GlobalGroupMstDATA data = new GlobalGroupMstDATA();
			GlobalGroupMstFB fb = (GlobalGroupMstFB) form;

			fb.setStrseatId(request.getSession().getAttribute("SEATID").toString());

			if (Integer.parseInt(fb.getChk1().toString().replace("|", "#").split("#")[1].replace("$", "#").split("#")[0]) > 0 && fb.getStrisValid().equals("2"))
			{
				count = 0;
			} 
			else
			{
				count = data.updateRecord(fb.getChk1(), fb);
			}
			if (count == 0)
			{
				String errmsg = "Record can not be modified due to duplicacy!";
				if (Integer.parseInt(fb.getChk1().toString().replace("|", "#").split("#")[1].replace("$", "#").split("#")[0]) > 0 && fb.getStrisValid().equals("2"))
				{
					errmsg = "Record can not be made InActive due to child data found!";
				}
				fb.setStrErrorMsg(errmsg);
				target = "modify";
			}
			if (count == 1)
			{

				String errmsg = "Record Not Modified!";
				fb.setStrErrorMsg(errmsg);
				target = "modify";
			}
			if (count == 2)
			{
				target = "list";
			}
			if (target.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(target);
		}
	}
}