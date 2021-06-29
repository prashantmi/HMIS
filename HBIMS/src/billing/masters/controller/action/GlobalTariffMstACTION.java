/* Global Tariff Master ACTION
 * author: Debashis Sardar
 * Created on : 14-Sep-2011
 */

package billing.masters.controller.action;

import hisglobal.masterutil.GenericController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;
import billing.masters.controller.data.GlobalTariffMstDATA;
import billing.masters.controller.fb.GlobalTariffMstFB;
import billing.masters.controller.util.GlobalTariffMstUTL;

public class GlobalTariffMstACTION extends GenericController
{

	static GlobalTariffMstUTL masterObj = new GlobalTariffMstUTL();

	/*
	 * calls super class constructor.
	 */
	public GlobalTariffMstACTION()
	{
		super(masterObj, "masters/CNTTariffMst");
	}

	/**
	 * forwards control to the Add Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		generateToken(request);
		String target = "";
		GlobalTariffMstDATA data = new GlobalTariffMstDATA();
		GlobalTariffMstFB fbmst = (GlobalTariffMstFB) form;
		fbmst.setStrHospitalCode(BillConfigUtil.SUPER_HOSPITAL_CODE.toString());
		fbmst.setStrgrpId(request.getSession().getAttribute("GroupId")
				.toString());
		fbmst.setStrDataMode("0");
		data.initAdd(fbmst);
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
	 * @throws Exception
	 */
	public ActionForward SAVEADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request, response);
		GlobalTariffMstDATA data = new GlobalTariffMstDATA();
		GlobalTariffMstFB fbmst = (GlobalTariffMstFB) form;

		String sId[] = fbmst.getStrServiceId().replace("^", "#").split("#");
		fbmst.setStrServiceId(sId[0]);
		fbmst.setStrseatId(request.getSession().getAttribute("SEATID")
				.toString());
		fbmst.setStrHospitalCode(BillConfigUtil.SUPER_HOSPITAL_CODE.toString());

		fbmst.setStrDataMode("0");

		data.chkAddTariffName(fbmst);

		fbmst.setStrgrpId("0");
		
		fbmst.setStrsubgroupName("0");

		return this.ADD(mapping, fbmst, request, response);

	}

	/**
	 * forwards control to the  Modify Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		GlobalTariffMstFB fbmst = (GlobalTariffMstFB) form;
		GlobalTariffMstDATA data = new GlobalTariffMstDATA();
		fbmst.setStrDataMode("1");

		fbmst.setStrseatId(request.getSession().getAttribute("SEATID").toString());
		fbmst.setStrHospitalCode(BillConfigUtil.SUPER_HOSPITAL_CODE.toString());
		fbmst.setStrgrpId(request.getSession().getAttribute("GroupId").toString());

		data.toModifyData(fbmst, request.getParameter("chk"));

		fbmst.setStrUpdateChargeUnit("1");

		String target = "modify";
		return mapping.findForward(target);
	}

	/**
	 * invokes update Logic and forwards control to the list or
	 * Modify Page based on the Logic output
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception
	 */

	public ActionForward SAVEMODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request, response);
		boolean retVal = false;
		GlobalTariffMstFB fb = (GlobalTariffMstFB) form;
		GlobalTariffMstDATA data = new GlobalTariffMstDATA();
		fb.setStrChK(request.getParameter("chk"));
		String sId[] = fb.getStrServiceId().replace("^", "#").split("#");

		fb.setStrServiceId(sId[0]);
		fb.setStrHospitalCode(BillConfigUtil.SUPER_HOSPITAL_CODE.toString());
		fb.setStrseatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrgrpId(request.getSession().getAttribute("GroupId").toString());

		retVal = data.chkModifyTariffName(fb);

		fb.setStrGroupId("0");

		if (retVal == true)
			return this.LIST(mapping, fb, request, response);
		else
			return this.MODIFY(mapping, fb, request, response);

	}

}