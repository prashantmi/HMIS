package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;
import ipd.masters.bo.WardTypeMstBO;
import ipd.masters.controller.util.WardTypeMstUTIL;
import ipd.masters.vo.WardTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WardTypeMstACTION extends GenericController {
	String target = "";

	/**
	 * calls super class constructor.
	 */
	public WardTypeMstACTION() {

		super(new WardTypeMstUTIL(), "/masters/CNTWardTypeMst");
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
	 * forwards Control to the Add page and data will insert
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		target = "add";
		WardTypeMstVO vo = (WardTypeMstVO) form;
		WardTypeMstBO bo = null;
		HisUtil hisutil = null;
		String strGlobalWardType = "";
		
		try{
			hisutil = new HisUtil("ipd", "WardTypeMstACTION");
			
			bo = new WardTypeMstBO();
			//vo.setStrhospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			vo.setStrhospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			bo.getGlobalWardType(vo);
			
			
			if (vo.getWrsGlobalWardType()!= null
					&& vo.getWrsGlobalWardType().size() > 0) {
				strGlobalWardType = hisutil.getOptionValue(vo.getWrsGlobalWardType(), "","0^Select Value", false);
			} else {
				strGlobalWardType = "<option value='0'>Select Value</option>";
			}
			vo.setStrGlobalWardTypeCombo(strGlobalWardType);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return mapping.findForward(target);

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 * After inserting, Data are save in Database & return Control Back to List Page
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
			validateToken(request, response);
			WardTypeMstBO bo = new WardTypeMstBO();
			WardTypeMstVO vo = (WardTypeMstVO) form;
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrhospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			target = bo.InsertRecord(vo);
			
			HisUtil hisutil = null;
			String strGlobalWardType = "";
			
			try
			{
				hisutil = new HisUtil("ipd", "WardTypeMstACTION");				
				bo = new WardTypeMstBO();
				vo.setStrhospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
				
				bo.getGlobalWardType(vo);				
				
				if (vo.getWrsGlobalWardType()!= null && vo.getWrsGlobalWardType().size() > 0) {
					strGlobalWardType = hisutil.getOptionValue(vo.getWrsGlobalWardType(), "","0^Select Value", false);
				} 
				else 
				{
					strGlobalWardType = "<option value='0'>Select Value</option>";
				}
				vo.setStrGlobalWardTypeCombo(strGlobalWardType);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}			
			return mapping.findForward(target);
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
	 * Forwards control to the Modify Page
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		try {
			WardTypeMstBO bo = new WardTypeMstBO();
			WardTypeMstVO vo = (WardTypeMstVO) form;
			String strGlobalWardType = "";
			HisUtil hisutil;
			
			hisutil = new HisUtil("ipd", "WardTypeMstACTION");
			vo.setStrhospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setChk1(request.getParameter("chk"));
			bo.modifyRecord(request.getParameter("chk"), vo);

			vo.setStrhospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			String strChk=request.getParameter("chk");
			strChk=strChk.replace("@", "$");
			String[] temp = strChk.replace('$', '#').split("#");
			vo.setStrGlobalWardType(temp[1]);
			
			bo.getGlobalWardType(vo);
			
			if (vo.getWrsGlobalWardType()!= null
					&& vo.getWrsGlobalWardType().size() > 0) {
				
				strGlobalWardType = hisutil.getOptionValue(vo.getWrsGlobalWardType(), vo.getStrGlobalWardType(),"0^Select Value", false);
			} else {
				strGlobalWardType = "<option value='0'>Select Value</option>";
			}
			
			vo.setStrGlobalWardTypeCombo(strGlobalWardType);
			
		} catch (Exception e) {
		}
		target = "modify";
		return mapping.findForward(target);
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
	 * After Modification, Data is update n save into the database and return back to the list page
	 */

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		try{
			validateToken(request, response);
			WardTypeMstBO bo = new WardTypeMstBO();
			WardTypeMstVO vo = (WardTypeMstVO) form;
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrhospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			target = bo.updateRecord(request.getParameter("chk1"),vo);
		}catch (Exception e) {
		}
			if (target.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(target);
		}

}
