
package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.WardCriteriaMstBO;
import ipd.masters.controller.util.WardCriteriaMstUTIL;
import ipd.masters.vo.WardCriteriaMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WardCriteriaMstACTION extends GenericController {
	String target = "";

	/**
	 * calls super class constructor.
	 */
	public WardCriteriaMstACTION() {
		super(new WardCriteriaMstUTIL(), "/masters/CNTWardCriteriaMst");
	}

	/**
	 *  Forwards Control to the ADD Page.
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

		WardCriteriaMstVO vo = (WardCriteriaMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	//	BOWardCriteria  bo = new BOWardCriteria();
		target = "add";
		return mapping.findForward(target);

	}




	/**
	 * After inserting, Data are save in Database & return Control Back to List Page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception -No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 * 
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

			String target = "";
			WardCriteriaMstBO  bo = new WardCriteriaMstBO();
			WardCriteriaMstVO vo = (WardCriteriaMstVO) form;
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			target = bo.InsertRecord(vo);
		
			return mapping.findForward(target);
		}

	/**
	 * Forwards control to the Modify Page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException - No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String target = "";
		try {

			WardCriteriaMstBO  bo = new WardCriteriaMstBO();
			WardCriteriaMstVO vo = (WardCriteriaMstVO) form;
			
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo.modifyRecord(request.getParameter("chk"), vo);
		} catch (Exception e) {
		}
		target = "modify";
		return mapping.findForward(target);

	}

	/**
	 * After Modification, Data is update and save into the database and return back to the list page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException - No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException

	{
		String target ="";
		WardCriteriaMstBO  bo = new WardCriteriaMstBO();
		WardCriteriaMstVO vo = (WardCriteriaMstVO) form;
		vo.setStrLastModifySeatId(request.getSession().getAttribute("SEATID").toString());
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		target =bo.updateRecord(request.getParameter("chk"),vo);
		
		
		
		if (target.equals("list"))
			return this.LIST(mapping, form, request, response);
		else
			return mapping.findForward(target);
	}

	/*public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		// System.out.println("inside VIEW MODE");
		String target = "";
		try {

			BOWardMst bo = new BOWardMst();
			VOWardMst vo = (VOWardMst) form;
			bo.viewRecord(request.getParameter("chk"), vo);
		} catch (Exception e) {
		}
		target = "view";
		return mapping.findForward(target);

	}
*/
	public ActionForward GETROOMVALUES(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		WardCriteriaMstVO vo = (WardCriteriaMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrWardCmb(request.getParameter("strWardCodeNoA"));
		WardCriteriaMstBO  bo = new WardCriteriaMstBO();
		bo.getRoomValues(vo,response);
		return null;

	}
	
	public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		WardCriteriaMstVO formBean = (WardCriteriaMstVO) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString()); 
		//BOWardCriteria  bo = new BOWardCriteria();
		WardCriteriaMstBO.showReport(formBean, request, response);
		
		return null;
	}
}
