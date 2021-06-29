/* (PACKAGE SERVICE MASTER)  */

package billing.masters.controller.action;
/* Package Service Master ACTION
 * author: Debashis Sardar
 * Created on : 01-Sep-2011
 */
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import java.io.PrintWriter;
import java.sql.SQLException;
import billing.masters.controller.fb.PackageServiceMstFB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import billing.masters.controller.data.PackageServiceMstDATA;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import billing.masters.controller.util.PackageServiceMstUTL;

public class PackageServiceMstACTION extends GenericController {

	// Use to get LIST page

	public PackageServiceMstACTION() {
		super(new PackageServiceMstUTL(), "masters/CNTpackservMst");
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
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		generateToken(request);
		PackageServiceMstFB fb = (PackageServiceMstFB) form;
		PackageServiceMstDATA data = new PackageServiceMstDATA();
		fb.setStrpackageId(request.getSession().getAttribute("strPkgId").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrtariffId(request.getSession().getAttribute("strTrfID").toString());
		data.initAdd(fb);
		data.tariffNameCmb(fb);
		data.unitName(fb);	
		String target = "add";
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
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
         int count=-1;
         String strMsg = "";
         String strWarning = "";
		PackageServiceMstDATA datamst = new PackageServiceMstDATA();
		PackageServiceMstFB fbmst = (PackageServiceMstFB) form;
		fbmst.setStrpackageId(request.getSession().getAttribute("strPkgId")
				.toString());
		fbmst.setStrseatId(request.getSession().getAttribute("SEATID").toString());
		fbmst.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		fbmst.setStrtariffId(request.getSession().getAttribute("strTrfID").toString());
		
		count=datamst.insertData(fbmst);
		if(count==0)
		{
			strMsg = "Record saved successfully!";
			
			fbmst.setStrNormalMsg(strMsg);
		}
		if(count==1)
		{
			strWarning = "Record Not Saved!Data Already Exist!!";
			fbmst.setWarningMsg(strWarning);
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
	 */	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		generateToken(request);
		String target = "modify";
		String strmsgText = null;
		PackageServiceMstDATA datamst = new PackageServiceMstDATA();
		PackageServiceMstFB fbmst = (PackageServiceMstFB) form;
		fbmst.setStrpackageId(request.getSession().getAttribute("strPkgId")
				.toString());
		fbmst.setStrChk1(request.getParameter("chk"));
		
		datamst.getData(fbmst, request.getParameter("chk"));
		
		
		try {

			datamst.initAdd(fbmst);
			datamst.tariffName(fbmst);
			fbmst.setStrChk1(request.getParameter("chk"));
			
		} catch (Exception e) {
			strmsgText = "billing.masters.CNTpackservMst.MODIFY(chk1,bean) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"PacServiceMaster->MODIFY()", strmsgText);
			fbmst.setErrmsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		}

		return mapping.findForward(target);
	}
	/**
	 * invokes update Logic and forwards control to the list or Modify Page based on the Logic output
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		{
			boolean retvalue;

			PackageServiceMstDATA data = new PackageServiceMstDATA();
			PackageServiceMstFB bean = (PackageServiceMstFB) form;
			bean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			bean.setStrLstModifySeatId(request.getSession().getAttribute("SEATID")
					.toString());
			bean.setStrpackageId(request.getSession().getAttribute("strPkgId")
					.toString());
			
			retvalue = data.updateRecord(request.getParameter("chk"), bean);
			if (!retvalue) {
				String target = "";
				target = "modify";
				return mapping.findForward(target);
			} else {
				return this.LIST(mapping, form, request, response);
			}

		}

	}

	

	
	/**
	 * to get Unit combo
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 */
	
	public ActionForward BASEUNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PackageServiceMstFB fbmst = (PackageServiceMstFB) form;
		PackageServiceMstDATA data = new PackageServiceMstDATA();
		try {
			String str = request.getParameter("unitId");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			fbmst.setStrunitId(str);
			fbmst.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			out.println(data.getUnitCombo(fbmst));
		} catch (Exception e) {

			new HisException("Billing", "PacServiceMaster->BASEUNITVAL()", e
					.getMessage());

		}
		return null;
	}

	/**
	 * for getting option value of combo on add page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception
	 */
	public ActionForward TARIFFDETAIL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		PackageServiceMstDATA data = new PackageServiceMstDATA();
		PackageServiceMstFB fb = (PackageServiceMstFB) form;
		fb.setStrpackageId(request.getSession().getAttribute("strPkgId").toString());
		fb.setStrtariffId(request.getSession().getAttribute("strTrfID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String res = data.addedTariffDetails(fb);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(res);

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			new HisException("Billing", "PacServiceMaster->TARIFFDETAIL()", e.getMessage());
		}
		return null;
	}
	/**
	 * To find the values of tariff combo in multi row according to group combo
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception
	 */
	
	public ActionForward TARIFFCOMBO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		PackageServiceMstDATA data = new PackageServiceMstDATA();
		PackageServiceMstFB fb = (PackageServiceMstFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrgroupId(request.getParameter("grpId"));
		String res = data.getTariffCombo(fb);
		try 
		{
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(res);

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			new HisException("Billing", "PacServiceMaster->TARIFFCOMBO()", e.getMessage());
		}
		return null;
	}

}
