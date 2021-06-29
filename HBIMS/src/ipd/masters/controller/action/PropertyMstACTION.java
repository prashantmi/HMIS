package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.PropertyMstBO;
import ipd.masters.controller.util.PropertyMstUTIL;
import ipd.masters.vo.PropertyMstVO;
//import hisglobal.utility.HisUtil;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PropertyMstACTION extends GenericController {
	String strTarget = "";

	public PropertyMstACTION() // Constructor for PropertyMstACTION
	{
		super(new PropertyMstUTIL(), "/masters/CNTPropertyMst");
	}
	/**
	 * forwards control to the Property Master Add Page
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
		
		PropertyMstVO vo=null;
		PropertyMstBO bo=null;
		saveToken(request);
		try{
		vo=(PropertyMstVO)form;
		bo=new PropertyMstBO();
		bo.getCurrentdate(vo);
		}catch(Exception e){
			
		}
		strTarget = "add";

		return mapping.findForward(strTarget);
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
	 * After inserting, Data are save in Database 
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
			
		PropertyMstBO bo=null;
		PropertyMstVO vo=null;
		
		try{
			bo = new PropertyMstBO();
			vo = (PropertyMstVO) form;
					
			strTarget = bo.InsertRecord(vo,request);
			bo.getCurrentdate(vo);
			}catch(Exception e){
				
			}
		return mapping.findForward(strTarget);
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
		
		PropertyMstBO bo=null;
		PropertyMstVO vo=null;
		
		try {
			bo = new PropertyMstBO();
			vo = (PropertyMstVO) form;
			bo.modifyRecord(request.getParameter("chk"), vo,request);
		} catch (Exception e) {
		}
		strTarget = "modify";
		return mapping.findForward(strTarget);
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
		PropertyMstBO bo=null;
		PropertyMstVO vo=null;
		
		try{
			bo = new PropertyMstBO();
			vo = (PropertyMstVO) form;
			
			strTarget = bo.updateRecord(request.getParameter("strChk"),vo,request);
			
		}catch (Exception e) {
			
		}
		
			if (strTarget.equals("list"))
				return this.LIST(mapping, form, request, response);
			else
				return mapping.findForward(strTarget);
		}
}
