package ipd.masters.controller.action;

import ipd.IpdConfigUtil;
import ipd.masters.bo.BedTypeMstBO;
import ipd.masters.bo.ClinicalDocumentMstBO;
import ipd.masters.controller.util.ClinicalDocumentMstUTIL;
import ipd.masters.vo.BedTypeMstVO;
import ipd.masters.vo.ClinicalDocumentMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.utility.HisUtil;

public class ClinicalDocumentMstACTION extends GenericController {
	String strtarget = "";

	/**
	 * calls super class constructor.
	 */
	public ClinicalDocumentMstACTION() // Constructor for ClinicalDocumentMstACTION
	{
		super(new ClinicalDocumentMstUTIL(), "/masters/CNTClinicalDocumentMst");
	}
	
	/**
	 * Forwards Control to the Add page and data will insert.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception - No Exception occurs
	 * @throws SQLException - No (Database) SQLException
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		strtarget = "add";
		ClinicalDocumentMstVO vo = (ClinicalDocumentMstVO) form;
		ClinicalDocumentMstBO bo = null;
		HisUtil hisutil = null;
		String strGlobalClinicalDocumentType = "";
		
		try{
			hisutil = new HisUtil("ipd", "ClinicalDocumentMstACTION");
			
			bo = new ClinicalDocumentMstBO();
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			
			bo.getGlobalClinicalDocumentType(vo);
			
			
			if (vo.getWrsGlobalClinicalDocumentType()!= null
					&& vo.getWrsGlobalClinicalDocumentType().size() > 0) {
				strGlobalClinicalDocumentType = hisutil.getOptionValue(vo.getWrsGlobalClinicalDocumentType(), "","0^Select Value", false);
			} else {
				strGlobalClinicalDocumentType = "<option value='0'>Select Value</option>";
			}
			vo.setStrGlobalClinicalDocumentTypeCombo(strGlobalClinicalDocumentType);
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
		return mapping.findForward(strtarget);
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

			ClinicalDocumentMstBO bo = new ClinicalDocumentMstBO();
			ClinicalDocumentMstVO vo = (ClinicalDocumentMstVO) form;
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			strtarget = bo.InsertRecord(vo);
			return mapping.findForward(strtarget);
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
			throws HisException, SQLException {
		HisUtil hisutil = null;
		String strGlobalClinicalDocumentType = "";
		
		try {
			ClinicalDocumentMstBO bo = new ClinicalDocumentMstBO();
			ClinicalDocumentMstVO vo = (ClinicalDocumentMstVO) form;	
			hisutil = new HisUtil("ipd", "ClinicalDocumentMstACTION");
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String chk1=request.getParameter("chk");
			
			bo.modifyRecord(chk1, vo);
			vo.setStrChk1(request.getParameter("chk"));
			vo.setStrGlobalClinicalDocumentType(request.getParameter("chk").split("\\@")[0]);
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			bo.getGlobalClinicalDocumentType(vo);
			
			if (vo.getWrsGlobalClinicalDocumentType()!= null
					&& vo.getWrsGlobalClinicalDocumentType().size() > 0) {
				strGlobalClinicalDocumentType = hisutil.getOptionValue(vo.getWrsGlobalClinicalDocumentType(), vo.getStrGlobalClinicalDocumentType(),"0^Select Value", false);
			} else {
				strGlobalClinicalDocumentType = "<option value='0'>Data/N.A</option>";
			}
			vo.setStrGlobalClinicalDocumentTypeCombo(strGlobalClinicalDocumentType);
		} catch (Exception e) {
			e.printStackTrace();
			// Empty
		}
		strtarget = "modify";
		return mapping.findForward(strtarget);
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
			throws HisException, SQLException {
		{

			ClinicalDocumentMstBO bo = new ClinicalDocumentMstBO();
			ClinicalDocumentMstVO vo = (ClinicalDocumentMstVO) form;
			
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);

			if (strtarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strtarget);
		}
	}

}
