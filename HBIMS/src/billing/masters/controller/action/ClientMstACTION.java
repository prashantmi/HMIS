
/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Client Master ACTION
 * 
 * Created on: 30-08-2011
 */
                
package billing.masters.controller.action;

import hisglobal.TransferObjectFactory;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import billing.BillConfigUtil;
import billing.masters.controller.data.ClientMstDATA;
import billing.masters.controller.fb.ClientMstFB;
import billing.masters.controller.util.ClientMstUTL;
import billing.masters.vo.ClientMstVO;

public class ClientMstACTION extends GenericController {

	static ClientMstUTL masterObj = new ClientMstUTL();
	String strtarget = "";
	String calledFrom = "";

	/*
	 * calls super class constructor.
	 */
	public ClientMstACTION() {
		super(masterObj, "/masters/CNTClientMst");
	}

	/*
	 * Forwards Control to the ADD Page.
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		generateToken(request);
		String target = "add";
		ClientMstFB formBean = (ClientMstFB) form;
		String prevMsg="";
		
		prevMsg=formBean.getStrWarning();
		
		ClientMstVO clientMstVo=new ClientMstVO();
		clientMstVo=ClientMstDATA.setClientTypeId(request);
		
		//fill values in formbean for client type..
		TransferObjectFactory.populateData(formBean, clientMstVo);
		
		formBean.setStrCatValues(ClientMstDATA.getCategory());
		
		if(calledFrom.equals("SAVE"))
		{
			formBean.setStrWarning(prevMsg);
		}
		
		
		
		return mapping.findForward(target);
	}

	/* To Save Data in Database & return Control Back to List Page */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		ClientMstDATA data = new ClientMstDATA();
		ClientMstFB formBean = (ClientMstFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		String hosCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		formBean.setStrHospitalCode(hosCode);

		data.insertRecord(request,formBean);
		calledFrom="SAVE";
		
		return this.ADD(mapping, form, request, response);

	}
	/*
	 * Forwards control to the Modify Page.
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		ClientMstDATA data = new ClientMstDATA();
		ClientMstFB fb = (ClientMstFB) form;
		fb.setStrChk(request.getParameter("chk"));
		data.modifyRecord(fb.getStrChk(), fb);
		fb.setStrCatValues(ClientMstDATA.getCategory());
		fb.setHmode("MODIFY");
		strtarget = "add";
		return mapping.findForward(strtarget);
	}

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		{
			validateToken(request, response);
			ClientMstDATA data = new ClientMstDATA();
			ClientMstFB fb = (ClientMstFB) form;
			fb.setStrLastModSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			fb.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			String hosCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			fb.setStrHospitalCode(hosCode);
			strtarget = data.updateRecord(fb.getStrChk(), fb);
			fb.setStrCatValues(ClientMstDATA.getCategory());
			fb.setHmode("MODIFY");
			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
