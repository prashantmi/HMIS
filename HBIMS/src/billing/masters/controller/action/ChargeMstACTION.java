package billing.masters.controller.action;
/* Charge Master ACTION
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import billing.masters.controller.hlp.ChargeMstHLP;
import billing.masters.controller.data.ChargeMstDATA;
import billing.masters.controller.fb.ChargeMstFB;
import billing.masters.controller.util.ChargeMstUTL;


public class ChargeMstACTION extends GenericController {

	/**
	 * calls super class constructor.
	 */
	public ChargeMstACTION() {
		super(new ChargeMstUTL(), "/masters/CNTChargeMst");
	}

	/**
	 * forwards control to the add page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		generateToken(request);
		ChargeMstDATA data = new ChargeMstDATA();
		ChargeMstFB fb = (ChargeMstFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());		
		String strHService = request.getParameter("service");		
        fb.setHospitalService(strHService);
		String strTarget = "add";
		fb.setPatientCategory("<option value='0'>Select Value</option>");
		data.getStrHospitalServiceCombo(fb);
		
		return mapping.findForward(strTarget);
	}

	/**
	 * populates group combo using AJAX.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward GRPCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChargeMstFB fbmst = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();
		try 
		{
			String strHService = request.getParameter("service");
			fbmst.setHospitalService(strHService);			
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getGroupCombo( fbmst));
		} 
		catch (Exception e) 
		{
			new HisException("Billing", "CNTChargeMst->GRPCMB()", e.getMessage());
		}

		return null;
	}
	/**
	 * populates GETPATCATCOMBO combo using AJAX.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward GETPATCATCOMBO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		ChargeMstFB fbmst = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();

		try 
		{
			String strHService = request.getParameter("service");
			fbmst.setHospitalService(strHService);
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			fbmst.setStrGroupId(request.getParameter("groupId"));
			fbmst.setStrTariffName(request.getParameter("tariffId"));
			fbmst.setStrPackHospService(request.getParameter("hService"));
			fbmst.setStrWardType(request.getParameter("wardType"));
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");		 
			out.println(data.getPatientCategory(fbmst));
		} 
		catch (Exception e) 
		{
			new HisException("Billing", "CNTChargeMst->GRPCMB()", e.getMessage());
		}

		return null;
	}
	/**
	 * populates tariff combo using AJAX.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward TRFCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChargeMstFB fbmst = (ChargeMstFB) form;
		try {
			ChargeMstDATA data = new ChargeMstDATA();
			String str = request.getParameter("service");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			fbmst.setStrGroupName(str);
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getTariffCombo(fbmst));
		} catch (Exception e) {

			new HisException("Billing", "CNTChargeMst->TRFCMB()", e
					.getMessage());

		}
		return null;
	}

	/**
	 * populates unit combo using AJAX.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward UNTCMB(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		
		ChargeMstFB fbmst = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();		
		try 
		{
			String str = request.getParameter("service");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();			
			fbmst.setStrUnit(str);
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());			
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getUnitCombo(fbmst));
		} 
		catch (Exception e) 
		{
			new HisException("Billing", "CNTChargeMst->UNTCMB()", e.getMessage());
		}
		return null;
	}
	/**
	 * populates unitcombo1 using AJAX.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward UNTCMB1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		ChargeMstFB fbmst = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();
		
		try 
		{
			String str = request.getParameter("service");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			fbmst.setStrUnit(str);
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getUnitCombo(fbmst));
		} 
		catch (Exception e) 
		{
			new HisException("Billing", "CNTChargeMst->UNTCMB1()", e.getMessage());
		}
		return null;
	}

	/**
	 * populates Package Details using AJAX.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward PACKDET(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			ChargeMstDATA data = new ChargeMstDATA();
			ChargeMstFB fbmst = (ChargeMstFB) form;
			String str = request.getParameter("service");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			fbmst.setStrTariffName(str);

			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getDataOnPack(fbmst));

		} catch (Exception e) {
			new HisException("Billing", "CNTChargeMst->PACKDET()", e.getMessage());
		}
		return null;
	}

	/**
	 * populates ward combo using AJAX.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	public ActionForward WRDCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChargeMstFB fbmst = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();
		try {
			String str = request.getParameter("service1");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			fbmst.setHospitalService(str);
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getWardCombo(fbmst));
		} catch (Exception e) {

			new HisException("Billing", "CNTChargeMst->WRDCMB()", e.getMessage());

		}
		return null;
	}

	/**
	 * populates group combo using AJAX.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward GRPCMB1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChargeMstFB fbmst = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();
		try {
			String str = request.getParameter("service");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			fbmst.setStrPackHospService(str);
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getGroupCombo1(fbmst));

		} catch (Exception e) {

			new HisException("Billing", "CNTChargeMst->GRPCMB1()", e.getMessage());

		}
		return null;
	}
	/**
	 * populates Tariff combo using AJAX.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward TRFCMB1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChargeMstFB fbmst = (ChargeMstFB) form;
		try {
			String str = request.getParameter("service");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ChargeMstDATA data = new ChargeMstDATA();
			fbmst.setStrPackGroupName(str);
			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getTariffCombo1(fbmst));

		} catch (Exception e) {

			new HisException("Billing", "CNTChargeMst->TRFCMB1()", e.getMessage());

		}
		return null;
	}
	/**
	 * populates previous data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward PREVDATA(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		ChargeMstFB fbmst = (ChargeMstFB) form;
		
		try 
		{
			String hService = request.getParameter("hService");
			String tariffId = request.getParameter("tariffId");
			String wardType = request.getParameter("wardType");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			fbmst.setHospitalService(hService);
			fbmst.setStrTariffName(tariffId.replace("^", "#").split("#")[0]);
			fbmst.setStrWardType(wardType);

			fbmst.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			response.setHeader("Cache-Control", "no-cache");
			out.println(ChargeMstHLP.getPreviousData(fbmst));

		} 
		catch (Exception e) 
		{
			new HisException("Billing", "CNTChargeMst->PREVDATA()", e.getMessage());
		}
		return null;
	}

	/**
	 * To insert Data.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward SAVEADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);
		ChargeMstDATA data = new ChargeMstDATA();
		ChargeMstFB fb = (ChargeMstFB) form;
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		int tmp_counter = data.insertData(fb);
		if (tmp_counter==0) {
			
			fb.setStrNormalMsg("Record saved successfully");
			fb.setStrHospitalService("0");
		} 
		if(tmp_counter==1)
			
				
		fb.setStrWarningMsg("Record already Exist"); 
			
			if (tmp_counter==2)
				
			fb.setStrErrMsg("Record not saved");
			

		fb.setStrHospitalService("0");
		fb.setStrPackHospService("0");

		return this.ADD(mapping, fb, request, response);

	}

	/**
	 * forwards control to the modify page.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		generateToken(request);
		boolean fretValue = false;
		String strTarget = null;

		ChargeMstFB fb = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		fb.setStrGroupId(request.getParameter("chk").replace("@", "#").replace("|", "#").split("#")[9]);
		fretValue = data.getDataToModify(fb, request.getParameter("chk"));
		
		String strGpCmb = data.getGroupCombo( fb);
		fb.setGroupCombo(strGpCmb);
		String strTariffCmb = data.getTariffCombo( fb);
		fb.setTariffCombo(strTariffCmb);
		String strUnitCmb = data.getUnitCombo(fb);
		fb.setUnitCombo(strUnitCmb);
		data.getStrHospitalServiceCombo(fb);
		String strGpCmb1 = data.getGroupCombo1( fb);
			fb.setGroupCombo1(strGpCmb1);
			 String strTariffCmb1 = data.getTariffCombo1( fb);
			 fb.setTariffCombo1(strTariffCmb1);
			 String strUnitCmb1 = data.getUnitCombo(fb);
			 fb.setUnitCombo(strUnitCmb1);
			 String strWardCmb = data.getWardCombo(fb);
			 fb.setWardCombo(strWardCmb);
		
		try{
			if(request.getParameter("chk").replace("@", "#").replace("|", "#").split("#")[6].equals("0")){
				fb.setStrModifyDtl(ChargeMstHLP.getModifyData(fb));
			}else{
				fb.setStrModifyDtl(ChargeMstHLP.getModifyData(fb));
				
			}
			
			
		}catch(Exception e){
			
			new HisException("Billing", "CNTChargeMst->MODIFY()", e.getMessage());
			
		}
		
		 
		if (fretValue == true) {
			strTarget = "modify";
			return mapping.findForward(strTarget);
		} else {
			return this.LIST(mapping, fb, request, response);
		}

	}

	/**
	 * calls function to update data.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward SAVEMODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		validateToken(request, response);
		ChargeMstFB fb = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();

		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrLastModiSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		boolean fVal = false;
		String chk = request.getParameter("chk");
		fVal = data.modifyData1(fb, chk);

		if (fVal) {
			return this.LIST(mapping, fb, request, response);
		} else {
			return this.MODIFY(mapping, fb, request, response);
		}
	}

	/**
	 * forwards control to the BatchUpdate jsp page.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward BATCHUPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChargeMstDATA data = new ChargeMstDATA();
		ChargeMstFB fb = (ChargeMstFB) form;
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strHService = request.getParameter("service");
		
        fb.setHospitalService(strHService);
		
		data.getStrHospitalServiceCombo(fb);
		fb.setPatientCategory(data.getPatientCategory(fb));

		String strTarget = "batch";
		return mapping.findForward(strTarget);
	}

	/**
	 * calls function(s) to save batch data.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward SAVEBATCH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String strTarget = "batch";

		ChargeMstFB fb = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();

		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		boolean fVal = data.batchData(fb);
		if (fVal) {
			
			fb.setStrNormalMsg("Data In Batch Updated Successfully.");
		} else {
			
			fb.setStrErrMsg("Error On Batch Update Page.");
		}

		if (fVal) {

			return this.BATCHUPDATE(mapping, fb, request, response);
		} else {
			return mapping.findForward(strTarget);
		}
	}
	
	/**
	 * to delete data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward DELETEDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ChargeMstFB fb = (ChargeMstFB) form;
		ChargeMstDATA data = new ChargeMstDATA();

		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		data.deleteData(fb, request.getParameterValues("chk"));
		
		return this.LIST(mapping, form, request, response);
	}
}
