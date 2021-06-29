package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import ipd.masters.bo.InPatientConfigMstBO;
import ipd.masters.vo.InPatientConfigMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class InPatientConfigMstACTION extends CSRFGardTokenAction {

	/**
	 * forwards control to the ipdconfig_ipd Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
			
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				
				System.out.println("---------------------------------------------------->>>>>> Unspecified in running");
				bo.setGenPropValues(vo);
				//bo.displayValues(vo);
				String target = "ipdgeneralsave";
				System.out.println("-------------------------------->>>>>>>>>>>>>>after target ipdgeneralsave in unspecified");
			return mapping.findForward(target);
	}
	
	public ActionForward ipdgeneral(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.setGenPropValues(vo);
				System.out.println("-------------------------------------------------------------------------------------->>>>>>>>>in new jsp ipdgeneral");
			return mapping.findForward("ipdgeneral");
	}
	
	public ActionForward ipdadmdtls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				bo.displayIpdAdmDtls(vo);
				
			return mapping.findForward("ipdadmdtls");
	}
	
	public ActionForward ipddischargedtls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				bo.displayIpdDischargeDtls(vo);
				
			return mapping.findForward("ipddischargedtls");
	}
	
	public ActionForward ipdpassdtls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.displayPassDetailsValues(vo);
				
			return mapping.findForward("ipdpassdtls");
	}
	
	public ActionForward ipdreportdtls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.displayReportDetailsValues(vo);
				
			return mapping.findForward("ipdreportdtls");
	}
	
	public ActionForward ipdbilldtls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				
				bo.displayBillDetailsValues(vo);
			
				
			return mapping.findForward("ipdbilldtls");
	}
	
	public ActionForward ipdprintdtls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
				generateToken(request);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.displayPrintDetailsValues(vo);
				
			return mapping.findForward("ipdprintdtls");
	}
	
	/**
	 * invokes the saveValues method of the BOInPatientConfigMst 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return - ActionForward with target save by invoking unspecified method.
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GENERALSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("---------------------------------------------------------->>>>>>>>>>>>>>>>>in generalsave before validate token");
				validateToken(request,response);
				System.out.println("---------------------------------------------------------->>>>>>>>>>>>>>>>>in generalsave after validate token");
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				//bo.saveValues(vo);
				bo.saveGeneralDatainDataBase(vo);
				
			return this.ipdgeneral(mapping, form, request, response);
	}
	
	/**
	 * invokes the saveValues method of the BOInPatientConfigMst 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return - ActionForward with target save by invoking unspecified method.
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward ADMISSIONSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
				validateToken(request, response);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				//bo.saveValues(vo);
				bo.saveAdmissionDtlDatainDataBase(vo);
				
			return this.ipdadmdtls(mapping, form, request, response);
	}
	
	public ActionForward DISCHARGESAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
				validateToken(request, response);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				//bo.saveValues(vo);
				bo.saveDischargeDtlDataInDataBase(vo);
				
			return this.ipddischargedtls(mapping, form, request, response);
	}
	
	public ActionForward PASSDTLSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
				validateToken(request, response);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.savePassDetails(vo);
				return mapping.findForward("ipdpassdtls");
			
	}
	public ActionForward RPTDTLSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
				validateToken(request, response);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.saveReportDetails(vo);
				
			return this.ipdreportdtls(mapping, form, request, response);
	}
	
	public ActionForward BILLDTLSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
				validateToken(request, response);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				//bo.saveBillDetailsValues(vo);
				bo.saveBillDatainDataBase(vo);
				return mapping.findForward("ipdbilldtls");
	}
	
	
	
	
	
	public ActionForward PRINTDTLSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
				validateToken(request, response);
				InPatientConfigMstVO vo = (InPatientConfigMstVO)form;
				InPatientConfigMstBO bo = new InPatientConfigMstBO();
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				bo.savePrintDetailsInDataBase(vo);
				
				return mapping.findForward("ipdprintdtls");
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
	 */
	public ActionForward INITIALPAGE(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
            throws HisException{
				/*ActionForward acFwd = new ActionForward();
				acFwd.setPath("/startup/initPage.jsp");
				acFwd.setContextRelative(true);
				return acFwd;*/
				return mapping.findForward("INIT");
		}
	
}
