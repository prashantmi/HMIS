package ipd.masters.controller.action;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;
import ipd.masters.bo.BedTypeMstBO;
import ipd.masters.bo.RoomTypeMstBO;
import ipd.masters.controller.util.RoomTypeMstUTIL;
import ipd.masters.vo.BedTypeMstVO;
import ipd.masters.vo.RoomTypeMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;
	
public class RoomTypeMstACTION extends GenericController 
 {
	    String target ="";
	    public RoomTypeMstACTION()  //Constructor for RoomTypeMstACTION
		{
			super(new RoomTypeMstUTIL(),"/masters/CNTRoomTypeMst");				
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
			RoomTypeMstVO vo = (RoomTypeMstVO) form;
			RoomTypeMstBO bo = null;
			HisUtil hisutil = null;
			String strGlobalRoomType = "";
			//BillConfigUtil billConfigUtilObj = new BillConfigUtil("101");
			//System.out.println("Consumable Charges Group :"+billConfigUtilObj.getStrConsumableChargesTariffCode());
			try{
				hisutil = new HisUtil("ipd", "RoomTypeMstACTION");
				
				bo = new RoomTypeMstBO();
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				
				bo.getGlobalRoomType(vo);
				
				
				if (vo.getWrsGlobalRoomType()!= null
						&& vo.getWrsGlobalRoomType().size() > 0) {
					strGlobalRoomType = hisutil.getOptionValue(vo.getWrsGlobalRoomType(), "","0^Select Value", false);
				} else {
					strGlobalRoomType = "<option value='0'>Select Value</option>";
				}
				vo.setStrGlobalRoomTypeCombo(strGlobalRoomType);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
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
			
			RoomTypeMstBO bo = new RoomTypeMstBO();
			RoomTypeMstVO vo = (RoomTypeMstVO)form ; 
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			target = bo.InsertRecord(vo);
			
			HisUtil hisutil = null;
			String strGlobalRoomType = "";
			try
			{
				hisutil = new HisUtil("ADT", "RoomTypeMstACTION");				
				bo = new RoomTypeMstBO();
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				
				bo.getGlobalRoomType(vo);				
				
				if (vo.getWrsGlobalRoomType()!= null && vo.getWrsGlobalRoomType().size() > 0) 
				{
					strGlobalRoomType = hisutil.getOptionValue(vo.getWrsGlobalRoomType(), "","0^Select Value", false);
				} 
				else 
				{
					strGlobalRoomType = "<option value='0'>Select Value</option>";
				}
				
				vo.setStrGlobalRoomTypeCombo(strGlobalRoomType);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
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
			HisUtil hisutil = null;
			String strGlobalRoomType = "";	
			try
			{
				RoomTypeMstBO bo = new RoomTypeMstBO();
				RoomTypeMstVO vo = (RoomTypeMstVO)form ; 
				hisutil = new HisUtil("ipd", "RoomTypeMstACTION");
			//	DAORoomTypeMst.getData(vo,request.getParameter("chk"));
			    bo.modifyRecord(request.getParameter("chk"),vo);
			    
			    vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrGlobalRoomType(request.getParameter("chk").split("\\@")[0]);
				
				
				bo.getGlobalRoomType(vo);
				
				if (vo.getWrsGlobalRoomType()!= null
						&& vo.getWrsGlobalRoomType().size() > 0) {
					strGlobalRoomType = hisutil.getOptionValue(vo.getWrsGlobalRoomType(), vo.getStrGlobalRoomType(),"0^Select Value", false);
				} else {
					strGlobalRoomType = "<option value='0'>Select Value</option>";
				}
				vo.setStrGlobalRoomTypeCombo(strGlobalRoomType);
			}
			catch(Exception e)
		   	{
				//e.printStackTrace();
				//Empty
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
		 */
		public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)throws Exception
		{
	      {
			 validateToken(request, response);    
	    	 RoomTypeMstBO bo = new RoomTypeMstBO();
			 RoomTypeMstVO vo = (RoomTypeMstVO)form ; 
			 
			    vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	//		 System.out.println("Hello In UPDATE Mode=>"+vo.getStrRoomType());
			 target = bo.updateRecord(request.getParameter("chk"), vo);
			   
			 if(target.equals("list"))
			    return	this.LIST(mapping,form,request,response);
			 else
			   	return mapping.findForward(target);
		  }
    	}
	
 } 




