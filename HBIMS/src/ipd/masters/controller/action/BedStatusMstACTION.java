package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;
import ipd.masters.bo.BedStatusMstBO;
import ipd.masters.bo.BedTypeMstBO;
import ipd.masters.controller.util.BedStatusMstUTIL;
import ipd.masters.vo.BedStatusMstVO;
import ipd.masters.vo.BedTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BedStatusMstACTION extends GenericController {
	String strtarget = "";

	public BedStatusMstACTION() // Constructor for BedStatusMstACTION
	{
		super(new BedStatusMstUTIL(), "/masters/CNTBedStatusMst");
	}
/**
 * is used to forward control to add page
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
			throws HisException, SQLException {

		//saveToken(request);
		generateToken(request);
		strtarget = "add";
		BedStatusMstVO vo = (BedStatusMstVO) form;
		BedStatusMstBO bo = null;
		HisUtil hisutil = null;
		String strGlobalBedStatus = "";

		try
		{
			hisutil = new HisUtil("ADT", "BedStatusMstACTION");
			
			bo = new BedStatusMstBO();
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			
			bo.getGlobalBedStatus(vo);
			
			
			if (vo.getWrsGlobalBedStatus()!= null && vo.getWrsGlobalBedStatus().size() > 0) 
			{
				strGlobalBedStatus = hisutil.getOptionValue(vo.getWrsGlobalBedStatus(), "","0^Select Value", false);
			} 
			else 
			{
				strGlobalBedStatus = "<option value='0'>Select Value</option>";
			}
			vo.setStrGlobalBedStatusCombo(strGlobalBedStatus);
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		return mapping.findForward(strtarget);

	}
/**
 * is used to invoke  BOBedStatusMst's insertRecord()
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 * @throws SQLException
 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

			BedStatusMstBO bo = new BedStatusMstBO();
			BedStatusMstVO vo = (BedStatusMstVO) form;
			HisUtil hisutil = null;
			String strGlobalBedStatus = "";
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			if(request.getSession().getAttribute("strIsValid")!=null)
				vo.setStrIsValid(request.getSession().getAttribute("strIsValid").toString());
			else
				vo.setStrIsValid("1");
			
			strtarget = bo.InsertRecord(vo);
			try
			{
				hisutil = new HisUtil("ADT", "BedStatusMstACTION");
				
				bo = new BedStatusMstBO();
				vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
				
				bo.getGlobalBedStatus(vo);
				
				
				if (vo.getWrsGlobalBedStatus()!= null && vo.getWrsGlobalBedStatus().size() > 0) 
				{
					strGlobalBedStatus = hisutil.getOptionValue(vo.getWrsGlobalBedStatus(), "","0^Select Value", false);
				} 
				else 
				{
					strGlobalBedStatus = "<option value='0'>Select Value</option>";
				}
				vo.setStrGlobalBedStatusCombo(strGlobalBedStatus);
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
			
			return mapping.findForward(strtarget);
	}
/**
 * is used to forward control to modify page
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws HisException
 * @throws SQLException
 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		HisUtil hisutil = null;
		String strGlobalBedStatus = "";

		try {
			BedStatusMstBO bo = new BedStatusMstBO();
			BedStatusMstVO vo = (BedStatusMstVO) form;	
			hisutil = new HisUtil("ipd", "BedStatusMstACTION");
			
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo.modifyRecord(request.getParameter("chk"), vo);
			
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			vo.setStrGlobalBedStatus(request.getParameter("chk").split("\\@")[0]);
			bo.getGlobalBedStatus(vo);
			if (vo.getWrsGlobalBedStatus()!= null
					&& vo.getWrsGlobalBedStatus().size() > 0) {
				strGlobalBedStatus = hisutil.getOptionValue(vo.getWrsGlobalBedStatus(), vo.getStrGlobalBedStatus(),"0^Select Value", false);
			} else {
				strGlobalBedStatus = "<option value='0'>Select Value</option>";
			}
			vo.setStrGlobalBedStatusCombo(strGlobalBedStatus);
		} catch (Exception e) {
			// Empty
		}
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}
/**
 * invoke BOBedStatusMst's updateRecord and then control goes to List page
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws HisException
 * @throws SQLException
 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		{
			validateToken(request, response);
			BedStatusMstBO bo = new BedStatusMstBO();
			BedStatusMstVO vo = (BedStatusMstVO) form;
			
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
