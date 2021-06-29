package billing.masters.controller.action;
/* Unit Value Master ACTION
 * author: Debashis Sardar
 * Created on : 16-Sep-2011
 */
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import billing.masters.controller.data.UnitValueMstDATA;
import billing.masters.controller.fb.UnitValueMstFB;
import billing.masters.controller.util.UnitValueMstBSUTL;


public class BSUnitValueMstACTION extends GenericController {

	public BSUnitValueMstACTION() {

		super(new UnitValueMstBSUTL(), "masters/CNTUnitValueMstBS");

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
			throws HisException, SQLException {
		generateToken(request);
		UnitValueMstFB fb = (UnitValueMstFB) form;
		UnitValueMstDATA data = new UnitValueMstDATA();
		fb.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

	
		String strModuleId = request.getSession().getAttribute("strModuleId")
				.toString();
		

		fb.setStrModuleId(strModuleId);

		fb.setStrmoduleName(request.getParameter("comboValue"));
		fb.setStrCmbval(data.getStrCmbval(fb));
		String target = "add";
		return mapping.findForward(target);

	}

	/**
	 * forwards control to the  Modify Page
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
			throws HisException, SQLException {
		generateToken(request);
		String target = "";
		try {

			UnitValueMstDATA data = new UnitValueMstDATA();
			UnitValueMstFB fb = (UnitValueMstFB) form;
			fb.setStrmoduleName(request.getParameter("comboValue"));
			fb.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			data.modifyRecord(request.getParameter("chk"), fb);
			

		} catch (Exception e) {
			// Empty
		}
		target = "modify";
		return mapping.findForward(target);

	}
	/**
	 * FROMUNITVAL mode called from ajax function for creating combo for from unit
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	
	public ActionForward FROMUNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		
		UnitValueMstDATA data = new UnitValueMstDATA();
		String value = (String) request.getParameter("modName");
		UnitValueMstFB fb = (UnitValueMstFB) form;

		fb.setStrmoduleName(value);
		fb.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		String comboValues = data.getCmbval1(fb);

		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		return null;
	}

	
	/**
	 * TOUNITVAL mode called from ajax function for creating combo for to unit
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws Exception
	 */
	public ActionForward TOUNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception {

		String val = (String) request.getParameter("fromunit");
		String val1 = (String) request.getParameter("modName");
		UnitValueMstFB fb = (UnitValueMstFB) form;
		UnitValueMstDATA data = new UnitValueMstDATA();
		fb.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		fb.setStrfromUnit(val);
		fb.setStrmoduleName(val1);

		String comboValues = data.getCmbval2(fb);

		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		return null;
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
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException

	{
		validateToken(request, response);
		String target = "";
		UnitValueMstFB fb = (UnitValueMstFB) form;
		UnitValueMstDATA data = new UnitValueMstDATA();
		fb.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

		target = data.updateRecord(request.getParameter("chk"), fb);

		if (target.equals("list"))
			return this.LIST(mapping, form, request, response);
		else
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
			throws Exception, SQLException {
		// String target ="";
		validateToken(request, response);
		UnitValueMstDATA data = new UnitValueMstDATA();
		UnitValueMstFB fb = (UnitValueMstFB) form;
		fb.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

		data.InsertRecord(fb);

		return this.ADD(mapping, form, request, response);

	}

}
