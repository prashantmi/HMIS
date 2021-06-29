/**
 *  CNTTariffMst.java
 */
package billing.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.masters.controller.data.LocalTariffMstDATA;
import billing.masters.controller.fb.LocalTariffMstFB;
import billing.masters.controller.util.LocalTariffMstUTL;

public class LocalTariffMstACTION extends GenericController
{

	static LocalTariffMstUTL masterObj = new LocalTariffMstUTL();

	/*
	 * calls super class constructor.
	 */
	public LocalTariffMstACTION()
	{
		super(masterObj, "masters/CNTTrfMst");
	}

	/*
	 * forwards control to the add page.
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		generateToken(request);
		String target = "";
		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrDataMode("0");
		target = "add";
		return mapping.findForward(target);
	}

	/*
	 * To add data.
	 */
	public ActionForward SAVEADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request, response);
		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		LocalTariffMstDATA data = new LocalTariffMstDATA();
		String sId[] = fb.getStrServiceId().replace("^", "#").split("#");
		fb.setStrServiceId(sId[0]);
		fb.setSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrDataMode("0");

		data.chkAddTariffName(fb);

		fb.setGroupId("0");
		fb.setDefaultUnit("0");

		return this.ADD(mapping, fb, request, response);

	}

	/*
	 * to recieve response from AJAX function to populate combo.
	 */
	public ActionForward POPULATEDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		try
		{
			String str = request.getParameter("service");
			String strTrf = request.getParameter("tariffId");
			String temp[] = str.replace("^", "#").split("#");

			if (strTrf == null)
			{
				strTrf = "0";
			}
			if (!str.equals("0"))
			{
				fb.setServiceName(temp[0]);
				/*if(temp[0].equals("7"))//Service Area
					fb.setStrHospitalCode("100");
				else*/
				//fb.setStrHospitalCode(temp[1]);
				fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			}

			fb.setTariffId(strTrf);
			String res = fb.getAddTariffCombo();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(res);

		} catch (Exception e)
		{
			e.printStackTrace();
			new HisException("billing", "CNTtariffMst.populateData()", e
					.getMessage());
		}
		return null;
	}

	/*
	 * forwards control to the modify page.
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		generateToken(request);
		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		LocalTariffMstDATA data = new LocalTariffMstDATA();
		fb.setStrDataMode("1");

		fb.setSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		boolean retval = data.toModifyData(fb, request.getParameter("chk"));

		if (!retval)
			GETMULTIROW(mapping, fb, request, response);

		fb.setStrUpdateChargeUnit("1");

		String target = "modify";
		return mapping.findForward(target);
	}

	/*
	 * To modify data.
	 */
	public ActionForward SAVEMODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request, response);
		boolean retVal = false;
		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		LocalTariffMstDATA data = new LocalTariffMstDATA();
		fb.setStrChK(request.getParameter("chk"));
		String sId[] = fb.getStrServiceId().replace("^", "#").split("#");

		fb.setStrServiceId(sId[0]);
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setSeatId(request.getSession().getAttribute("SEATID").toString());
		retVal = data.chkModifyTariffName(fb);
		fb.setStrGroupId("0");
		if (retVal == true)
			return this.LIST(mapping, fb, request, response);
		else
			return this.MODIFY(mapping, fb, request, response);
		// return mapping.findForward(target);
	}

	public void getAddGroupCombo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{

		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String isPackage = request.getParameter("isPackage");
		fb.setIsPackage(isPackage);
		String grpCmb = fb.getAddGroupCombo();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(grpCmb);

	}

	public void GLOBALTARIFF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{

		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String groupId = request.getParameter("groupId");
		fb.setGroupId(groupId);
		String trfCombo = fb.getAddGlobalTariffCombo();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(trfCombo);

	}

	public void GETMULTIROW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String res = "";

		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		LocalTariffMstDATA data = new LocalTariffMstDATA();
		
		res = data.getMultiRow(fb);
		fb.setStrMultiRow(res);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(res);

	}

	public ActionForward viewPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		generateToken(request);
		// boolean retVal = false;
		LocalTariffMstFB fb = (LocalTariffMstFB) form;
		LocalTariffMstDATA data = new LocalTariffMstDATA();

		fb.setSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		fb.setStrDataMode("2");

		data.toModifyData(fb, request.getParameter("chk"));

		GETMULTIROW(mapping, fb, request, response);
		String target = "viewPage";
		return mapping.findForward(target);
	}

}