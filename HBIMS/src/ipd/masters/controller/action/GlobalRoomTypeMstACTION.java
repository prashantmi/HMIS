package ipd.masters.controller.action;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.IpdConfigUtil;
import ipd.masters.bo.GlobalRoomTypeMstBO;
import ipd.masters.controller.util.GlobalRoomTypeMstUTIL;
import ipd.masters.vo.GlobalRoomTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
	
public class GlobalRoomTypeMstACTION extends GenericController 
 {
	    String target ="";
	    public GlobalRoomTypeMstACTION()  //Constructor for GlobalRoomTypeMstACTION
		{
			super(new GlobalRoomTypeMstUTIL(),"/masters/GlobalRoomTypeMstACTION");				
		}
	    /**
	     * Forward Control to Add page
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return Action forward
	     * @throws HisException
	     * @throws SQLException
	     */
		public ActionForward ADD (ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)throws HisException, SQLException
		{
			generateToken(request);
			target="add";
			return mapping.findForward(target);
		
		}
	/**
	 * Invoke Bo's InsertRecord method forward page back to add page.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
		public ActionForward SAVE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)throws Exception, SQLException
		{
			validateToken(request, response);
			GlobalRoomTypeMstBO bo = new GlobalRoomTypeMstBO();
			GlobalRoomTypeMstVO vo = (GlobalRoomTypeMstVO)form ; 
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
//			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE);
//			System.out.println("In CNT (SAVE-I)");
//			System.out.println("In SAVE Mode =>"+vo.getstrRemarks());
			target = bo.InsertRecord(vo);
			return mapping.findForward(target);
		
		}
 /**
  * This function is used to invoke Bo's modifyRecord and forward back control to modify page.
  * @param mapping
  * @param form
  * @param request
  * @param response
  * @return
  * @throws HisException
  * @throws SQLException
  */
		public ActionForward MODIFY (ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)throws HisException, SQLException
	    {
				
			generateToken(request);
			try
			{
				GlobalRoomTypeMstBO bo = new GlobalRoomTypeMstBO();
				GlobalRoomTypeMstVO vo = (GlobalRoomTypeMstVO)form ; 
			//	DAOGlobalRoomTypeMst.getData(vo,request.getParameter("chk"));
			    bo.modifyRecord(request.getParameter("chk"),vo);
			}
			catch(Exception e)
		   	{
				e.printStackTrace();
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
		 * @throws Exception 
		 */
		public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)throws Exception
		{
		      {
					 validateToken(request, response);      
			    	 GlobalRoomTypeMstBO bo = new GlobalRoomTypeMstBO();
					 GlobalRoomTypeMstVO vo = (GlobalRoomTypeMstVO)form ; 
					 
				     vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
					 //vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				     vo.setStrHospitalCode(IpdConfigUtil.SUPER_HOSPITAL_CODE);
			//		 System.out.println("Hello In UPDATE Mode=>"+vo.getStrRoomType());
					 target = bo.updateRecord(request.getParameter("chk"), vo);
					   
					 if(target.equals("list"))
					    return	this.LIST(mapping,form,request,response);
					 else
					   	return mapping.findForward(target);
			  }
    	}	
 } 