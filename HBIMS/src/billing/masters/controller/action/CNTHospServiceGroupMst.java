package billing.masters.controller.action;

import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.masters.bo.BOHospServiceGroupMst;
import billing.masters.controller.fb.VOHospServiceGroupMst;
import billing.masters.controller.util.UTLHospServiceGroupMst;

public class CNTHospServiceGroupMst extends GenericController {
	String target = "";

	public CNTHospServiceGroupMst() {

		super(new UTLHospServiceGroupMst(), "masters/CNTHospServiceGroupMst");

	}

	// service mode called by ajax function for creating left list box

	public ActionForward SERVICE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String val = (String) request.getParameter("hserviceName");
		// System.out.println("inside hservice val func -->"+val);
		VOHospServiceGroupMst vo = (VOHospServiceGroupMst) form;
		vo.setHserviceName(val);
		vo.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		String listValues = vo.getListValues();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(listValues);

		} catch (Exception e) {

		 		 
		}
		return null;
	}

	// displays add page
	public ActionForward ADD(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException 
	{
		generateToken(request);
		String target = "";
		VOHospServiceGroupMst vo = (VOHospServiceGroupMst) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		target = "add";
		return mapping.findForward(target);

	}

	// insert record

	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		BOHospServiceGroupMst bo = new BOHospServiceGroupMst();
		VOHospServiceGroupMst vo = (VOHospServiceGroupMst) form;
		vo.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		vo.setSeatId(request.getSession().getAttribute("SEATID").toString());
		bo.InsertRecord(vo);

		return this.ADD(mapping, form, request, response);

	}

	// displays modify page

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		String target = "";

		BOHospServiceGroupMst bo = new BOHospServiceGroupMst();
		VOHospServiceGroupMst vo = (VOHospServiceGroupMst) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		bo.modifyRecord(request.getParameter("chk"), vo);

		target = "modify";
		return mapping.findForward(target);

	}

	// update modified record

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		{
			BOHospServiceGroupMst bo = new BOHospServiceGroupMst();
			VOHospServiceGroupMst vo = (VOHospServiceGroupMst) form;
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			vo
					.setSeatId(request.getSession().getAttribute("SEATID")
							.toString());
			target = bo.updateRecord(request.getParameter("chk"), vo);
			if (target.equals(target))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(target);

		}

	}

}
