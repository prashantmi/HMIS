package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.utility.HisUtil;
import ipd.masters.bo.RoomConfigMstBO;
import ipd.masters.controller.util.RoomConfigMstUTIL;
import ipd.masters.dao.RoomTypeMstDAO;
import ipd.masters.vo.RoomConfigMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RoomConfigMstACTION extends GenericController {
	String strtarget = "";

	/**
	 * calls super class constructor.
	 */
	public RoomConfigMstACTION() {
		super(new RoomConfigMstUTIL(), "/masters/CNTRoomConfigMst");
	}

	/**
	 *  Forwards Control to the ADD Page.
	 *  
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
			throws HisException, SQLException, Exception {
		//saveToken(request);
		generateToken(request);
		RoomConfigMstVO vo = (RoomConfigMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());		
		RoomConfigMstBO bo = new RoomConfigMstBO();
		bo.getPropertyComboValues(vo);
		
		strtarget = "add";
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
			validateToken(request, response);
		
			RoomConfigMstBO bo = new RoomConfigMstBO();
			RoomConfigMstVO vo = (RoomConfigMstVO) form;
			
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			strtarget = bo.InsertRecord(vo);
			
			return this.ADD( mapping,  form,
					 request,  response);
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

		generateToken(request);
		try {
			
			RoomConfigMstVO vo = (RoomConfigMstVO) form;
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			RoomConfigMstBO bo = new RoomConfigMstBO();
			
			bo.modifyRecord(request.getParameter("chk"), vo);
			
			
		} catch (Exception e) {
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
			throws Exception {
			validateToken(request, response);

			RoomConfigMstBO bo = new RoomConfigMstBO();
			RoomConfigMstVO vo = (RoomConfigMstVO) form;
			
			vo.setStrLastModifSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			strtarget = bo.updateRecord(request.getParameter("chk"), vo);
		
			if (strtarget.equals("list")) {
				return this.LIST(mapping, form, request, response);
			} else {
				return mapping.findForward(strtarget); 
			}
		}
	

	// //////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * -------------------------------Ajax Function's Started
	 * -------------------------------------
	 */
	/**
	 * UNITVAL(FOR ADD JSP PAGE)
	 */
	public ActionForward UNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   	
			RoomConfigMstVO vo = (RoomConfigMstVO) form;
			String[] strTemp = null;
		        //Get the(Mod)Value From JSPPage//
			String strValmode = (String) request.getParameter("modName");
				//Get the Building Code from Mod//
			strTemp = strValmode.replace("^", "#").split("#");
			String strVal = strTemp[0];
		
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String comboValues = vo.getStrBlockDetailAdd(strVal);
        
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);

		return null;
	}

	/**
	 * BLOCK IN CASE OF MODIFICATION
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */ 
	public ActionForward BLOCK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			RoomConfigMstVO vo = (RoomConfigMstVO) form;
			String[] strTemp = null;
					//Get the(Mod)Value From JSPPage//
			String strValmode = (String) request.getParameter("modName");
					//Get the Building Code from Mod//
			strTemp = strValmode.replace("^", "#").split("#");
			String strVal = strTemp[0];
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String comboValues = vo.getStrBlockDetailAdd(strVal);
        
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
			return null;
	}

	
	/*
//floor for modify page
	public ActionForward FLOOR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		// System.out.println("Inside UNITVAL1 =>");

		// ///////////Get the(Mod)Value From JSPPage/////////////
		
		VORoomConfigMst vo = (VORoomConfigMst) form;
		
		String strVal = (String) request.getParameter("modName");
		String strVal1 = (String) request.getParameter("modName1");
		
		//get hospital code from session 
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		// //////////////////////////Get The Building
		// Code//////////////////////////////////////
		String[] strTemp = null;
		strTemp = strVal.replace("^", "#").split("#");
		String strValu = strTemp[0];
		// System.out.println("Val =>"+strValu);
		// //////////////////////////////Get The Block
		// Code//////////////////////////////////////
		String[] temp1 = null;
		temp1 = strVal1.replace("^", "#").split("#");
		String Val1 = temp1[0];
		// System.out.println("Val1=>"+Val1);
		// //////////////////////////////Parsing Data into
		// Integer//////////////////////////////
		int i = Integer.parseInt(strValu);
		int j = Integer.parseInt(Val1);
		DAOIpd dipd = new DAOIpd("RoomConfigMst", "CNT");
		String comboValuesTest = "<option value='0'> Select Value </option>";
		try {
			// ////////////////////////Here we Use GetFloorDtl2()form DAOIpd
			// Global File//////////////////////
			comboValuesTest = hisutil.getOptionValue(dipd.getFloorDtl2(-1, -1,
					true,vo.getStrHospitalCode()), "0", "", true);
			// /////////////////////////////////////////////////////////////////////////////////////////////

			// System.out.println("Query in UNITVAL1 For
			// FloorType:"+comboValuesTest);
			hisutil = null;

		} catch (Exception e) {
			e.printStackTrace();

		}
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValuesTest);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
*/
	/**
	 * floor for modify page
	 */
	public ActionForward FLOOR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
	
				//Get the(Mod)Value From JSPPage//
		
		RoomConfigMstVO vo = (RoomConfigMstVO) form;
	    
		String strVal = (String) request.getParameter("modName");
		String strVal1 = (String) request.getParameter("modName1");
		
				//get hospital code from session 
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
				//	Get The Building Code//
		String[] strTemp = null;
		strTemp = strVal.replace("^", "#").split("#");
		String strValu = strTemp[0];
		
		//Get The Block Code//
		String[] temp1 = null;
		temp1 = strVal1.replace("^", "#").split("#");
		String Val1 = temp1[0];
		String  comboValuesTest ="";	
        comboValuesTest =vo.getStrFloorDetailAdd(strValu,Val1);
		 try{
		 
		 response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().print(comboValuesTest);
		 }
		 catch (Exception e){
			 
		 }
		

		return null;
	}

	

	
	/**
	 * UNITVAL1->FLOOR(FOR ADD JSP PAGE)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward UNITVAL1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		RoomConfigMstVO vo = (RoomConfigMstVO) form;
	    
		String strVal = (String) request.getParameter("modName");
		String strVal1 = (String) request.getParameter("modName1");
		
					//get hospital code from session 
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
	//Get The Building Code//
		String[] strTemp = null;
		strTemp = strVal.replace("^", "#").split("#");
		String strValu = strTemp[0];
			
		// Get The Block Code//
		String[] temp1 = null;
		temp1 = strVal1.replace("^", "#").split("#");
		String Val1 = temp1[0];
		String  comboValuesTest ="";	
		System.out.println("strValu :"+strValu);
		System.out.println("Val1 :"+Val1);
        comboValuesTest = vo.getStrFloorDetailAdd(strValu,Val1);
		 try{
		 
		 response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().print(comboValuesTest);
		 }
		 catch (Exception e){
			 
		 }
		

		return null;
	}

	
	
	 /**
	  * ADD-->UNITVAL=2-->ROOM(FOR ADD JSP PAGE)
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	public ActionForward UNITVAL2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		 RoomConfigMstVO vo = (RoomConfigMstVO) form;
				//get hospital code value from session and set in vo
		 vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		 String comboValues3="";
		try {
			
					//Get the(Mod)Value From JSPPage//
			String val = (String) request.getParameter("modName");
			String strBuildingId = (String) request.getParameter("strBuildingId").replace("^", "#").split("#")[0];
			String strBlockId = (String) request.getParameter("strBlockId");
			
			
			if(strBuildingId==null || strBuildingId.equals(""))
				strBuildingId="0";
			if(strBlockId==null || strBlockId.equals(""))
				strBlockId="0";
			/* modName Contain (FLOORID ^ SL_NO ^ TOTAL_ROOMS) */
			
			String[] temp = null;
			temp = val.replace("^", "#").split("#");
			String Val = temp[0];
			System.out.println(strBuildingId+"==="+strBlockId);
		    comboValues3 = vo.getRoomAdd(Val,strBuildingId,strBlockId);
		    
			
		} catch (Exception e) {
			e.printStackTrace();
			new HisException("ipd", "VORoomConfigMst", e
					.getMessage());
		}
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	/**
	 * MODIFY-->ROOM(FOR ADD JSP PAGE)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			RoomConfigMstVO vo = (RoomConfigMstVO) form;
					//get hospital code value from session and set in vo
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String comboValues3="";
		try {
			
					//Get the(Mod)Value From JSPPage//
			String val = (String) request.getParameter("modName");
			String val2 = (String) request.getParameter("mode"); 
			String strRoomNo = (String) request.getParameter("roomNo");
			System.out.println("cnt ROOM mode-"+val2);
			vo.setStrAddOrModifyMode(val2);
//System.out.println("                  "+(String) request.getParameter("strBuildingId"));
			String strBuildingId = (String) request.getParameter("strBuildingId").replace("^", "#").split("#")[0];
			String strBlockId = (String) request.getParameter("strBlockId");
			/* modName Contain (FLOORID ^ SL_NO ^ TOTAL_ROOMS) */
			if(strBuildingId==null || strBuildingId.equals(""))
				strBuildingId="0";
			if(strBlockId==null || strBlockId.equals(""))
				strBlockId="0";
			String[] temp = null;
			temp = val.replace("^", "#").split("#");
			String Val = temp[0];
			vo.setStrRoomNo(strRoomNo); 
		    comboValues3 = vo.getRoomAdd(Val,strBuildingId,strBlockId);
			
		} catch (Exception e) {
			e.printStackTrace();
			new HisException("ipd", "VORoomConfigMst", e
					.getMessage());
		}
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}


	  
	 
	
	
	/*
	
	// //////////////////////////////////UNITVAL=2(FOR ADD JSP
	// PAGE)//////////////////////////////////////////////////
	public ActionForward UNITVAL2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// System.out.println("Inside UNITVAL2");

		VORoomConfigMst vo = (VORoomConfigMst) form;
		//get hospital code value from session and set in vo
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
	
		String tempstr = "";
		//System.out.println("Val =>"+Val);
		// ////////////////////////////////////////////////////////
		String comboValues3 = "<option value='0'> Select Value </option>";
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		try {
			
			// ///////////Get the(Mod)Value From JSPPage/////////////
			String val = (String) request.getParameter("modName");
			// /////////////////////////////////////////////////////
			/* modName Contain (FLOORID ^ SL_NO ^ TOTAL_ROOMS) 
			// /////////////////////////////////////////////////////
			String[] temp = null;
			temp = val.replace("^", "#").split("#");
			String Val = temp[0];
			System.out.println("ValMONIKA=="+Val);
			String strqry = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.4");
			System.out.println("VALFLOOR =="+val);
			if (val == null)
				val = "0";
			tempstr = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.cond.0").replace("?",Val); //floor condition
			
		    strqry  += " WHERE " + tempstr;
			tempstr = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.cond.1").replace("?", ""+vo.getStrHospitalCode()+""); //hospital code condition 
			 
			 strqry  += " AND " + tempstr;
			  System.out.println("Inside Action room Query is=>"+strqry);

			
			comboValues3 = hisutil.getOptionValue(strqry, "0",
					"0^Select Value");
			// System.out.println("Query :"+comboValues3);
			hisutil = null;
		} catch (Exception e) {
			e.printStackTrace();
			new HisException("ipd", "VORoomConfigMst", e
					.getMessage());
		}
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
*/
	/**
	 * UNITVAL=3(FOR ADD JSP PAGE)
	 */
	public ActionForward UNITVAL3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RoomConfigMstVO vo = (RoomConfigMstVO) form;
				//get hospital code value from session and set in vo
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
				//Get the(Mod)Value From JSPPage//
		String strVal = (String) request.getParameter("modName");
				//Parsing Data into Integer//
		int i = Integer.parseInt(strVal);
		
		String comboValues2 = "<option value='0'> Select Value </option>";
				//Createing Object of DAORoomTypeMst//
		RoomTypeMstDAO drtype = new RoomTypeMstDAO();
				//Here Use RoomTypeDtl() from DAORoomType()//
		comboValues2 = hisutil.getOptionValue(drtype.getRoomTypeDtl(i, true,vo.getStrHospitalCode()),
				"0", "0^select value", true);
			
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues2);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	// ///////////////////////////////////END ALL AJAX FUNCTION//////////////////////////////////////////////
}
