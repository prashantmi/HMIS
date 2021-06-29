package ipd.masters.controller.action;

import hisglobal.masterutil.GenericController;
import ipd.masters.bo.WardRoomBedMstBO;
import ipd.masters.controller.util.WardRoomBedMstUTIL;
import ipd.masters.vo.WardRoomBedMstVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class WardRoomBedMstACTION extends GenericController {
	String target = "";

	public WardRoomBedMstACTION() {
		super(new WardRoomBedMstUTIL(), "/masters/CNTWardRoomBedMst");
	}
/**
 *  This function forward control to add page
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 * @throws SQLException
 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		//saveToken(request);
		generateToken(request);
		target = "add";
		WardRoomBedMstBO bo=new WardRoomBedMstBO();
		WardRoomBedMstVO vo=(WardRoomBedMstVO) form;
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		bo.getRoom(vo);
		return mapping.findForward(target);

	}
/**
 * This function firstly invoke WardRoomBedMstBO's insertRecord() and on the basis of this 
 * function it show status of record on the add page whether it is saved successfully or not.
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
			validateToken(request, response);
		//if (isTokenValid(request)) {
			String target = "";
			WardRoomBedMstBO bo = new WardRoomBedMstBO();
			WardRoomBedMstVO vo = (WardRoomBedMstVO) form;
			//System.out.println("Hello Save");
			//String a[]=vo.getBedName();
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			target = bo.InsertRecord(vo);

			//resetToken(request);
			//return mapping.findForward(target);
			return ADD(mapping,form,request,response);//mapping.findForward(target);
		//} else {
			//return mapping.findForward("add");
		//}
	}
	/**
	 * This function is invoked through ajax on add page when ward is selected 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return on the basis of ward it return room number
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		//saveToken(request);
		target = "add";
		String errMsg;
		String pk=request.getParameter("modName");
		WardRoomBedMstVO vo=(WardRoomBedMstVO) form;
		WardRoomBedMstBO bo=new WardRoomBedMstBO();
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String res=bo.getRoom(pk, vo);
		res=res+"$"+vo.getStrBuildingBlock()+"$"+vo.getStrBedInWard()+"$"+vo.getStrBedLimit();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(res);

		} catch (Exception e) {

			errMsg = "CNTWardMst -->" + e.getMessage();
			throw new Exception(errMsg);// TODO Auto-generated catch block

		}
		return null;
		
	}
/**
 * This function bring bed details on add page when room number is selected on add page
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 * @throws SQLException
 */
	public ActionForward BED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		//saveToken(request);
		target = "add";
		String errMsg;
		String pk2;
		
		pk2=request.getParameter("modName2");
		String paraId=request.getParameter("roomNo");
		String roomNo=paraId.replace("^","#").split("#")[0];
		WardRoomBedMstVO vo=(WardRoomBedMstVO) form;
		WardRoomBedMstBO bo=new WardRoomBedMstBO();
		vo.setStrRoomId(roomNo);
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String res=bo.getBedDetail(pk2, vo);
		//res=res+"$"+vo.getStrBuildingBlock();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(res);

		} catch (Exception e) {

			errMsg = "CNTWardMst -->" + e.getMessage();
			throw new Exception(errMsg);// TODO Auto-generated catch block

		}
		return null;
		
	}
	/**
	 * This function used to create grid on add page for new record to be entered
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	
	public ActionForward NEWBEDENTRY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception, SQLException
	{
		String errMsg="";
		String pk1=request.getParameter("modName");
		String paraId=request.getParameter("roomNo");
		WardRoomBedMstBO bo=new WardRoomBedMstBO();
		WardRoomBedMstVO vo=(WardRoomBedMstVO)form;
		vo.setStrRoomId(paraId);
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String res=bo.createNewEntrySpace(pk1,vo);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(res);

		} catch (Exception e) {

			errMsg = "CNTWardMst -->" + e.getMessage();
			throw new Exception(errMsg);// TODO Auto-generated catch block

		}
		return null;
	}
	/**
	 * This function invoke WardRoomBedMstBO's updateRecord and forward control back to list page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException

	{
		validateToken(request, response);
		WardRoomBedMstVO vo = (WardRoomBedMstVO) form;
		WardRoomBedMstBO bo = new WardRoomBedMstBO();
		
		//vo.setStrLastModSeatId(request.getSession().getAttribute("SEATID").toString());
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		target = bo.updateRecord(request.getParameter("chk"), vo);

		if (target.equals("list"))
			return this.LIST(mapping, form, request, response);
		else
			return mapping.findForward("modify");
		//return null;

	}
/*
	public ActionForward UNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		VOWardRoomBedMst vo = (VOWardRoomBedMst) form;
		String[] strTemp = null;
		
		String strValmode = (String) request.getParameter("modName");
		strTemp = strValmode.replace("^", "#").split("#");

		String strTemp4[] = strTemp[4].replace('$', '#').split("#");
		String strTemp5[] = strTemp[5].replace('$', '#').split("#");

		//vo.setStrWardCode(strTemp[0]);
		//vo.setStrBuildingID(strTemp4[0]);
		//vo.setStrBlockID(strTemp5[0]);
		//String comboValues = vo.getRoomDtl();

		//response.setHeader("Cache-Control", "no-cache");
		//response.getWriter().print(comboValues);

		return null;
	}

	public ActionForward UNITVAL1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		VOWardRoomBedMst vo = (VOWardRoomBedMst) form;

		String[] strTemp = null;
		String strValmode = (String) request.getParameter("modName");
		strTemp = strValmode.replace("^", "#").split("#");

		String strTemp4[] = strTemp[4].replace('$', '#').split("#");
		String strTemp5[] = strTemp[5].replace('$', '#').split("#");

		//vo.setStrWardCode(strTemp[0]);
		//vo.setStrBuildingID(strTemp4[0]);
		//vo.setStrBlockID(strTemp5[0]);
		/////////////////Here We Get(Building-Block)Name//////////
		 //String str = vo.getStrSetID();
         //String comboValuesTest = vo.getStrBuildingName()+"/"+vo.getStrBlockName();
		//////////////////////////////////////////////////////////
		response.setHeader("Cache-Control", "no-cache");
		//response.getWriter().print(comboValuesTest);

		return null;
	}

	public ActionForward UNITVAL3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String[] strTemp = null;
		VOWardRoomBedMst vo = (VOWardRoomBedMst) form;
		String strValue = (String) request.getParameter("modName");
		String strValmode = (String) request.getParameter("modName1");
		strTemp = strValmode.replace("^", "#").split("#");

		//vo.setStrWardCode(strTemp[0]);
	//	vo.setStrRoomId(strValue);
		//String str2 = vo.getStrHtmlRow();
		//vo.setStrHtmlRow(str2);

		response.setHeader("Cache-Control", "no-cache");
		//response.getWriter().print(str2);

		return null;
	}

	public ActionForward UNITVAL4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		WardRoomBedMstBO bo = new WardRoomBedMstBO();
		VOWardRoomBedMst vo = (VOWardRoomBedMst)form ; 
    	String strVal  = (String) request.getParameter("modName");
		String strVal1 = (String) request.getParameter("modName1");
		////////////////////////////Get The Building Code//////////////////////////////////////
		String[] strTemp = null;
		strTemp = strVal.replace("^", "#").split("#");
		String strValu = strTemp[0];
		///////////////////////////////Get The Block Code//////////////////////////////////////
		String[] temp1 = null;
		temp1 = strVal1.replace("^", "#").split("#");
	 	String Val1 = temp1[0];
	 	///////////////////////////////Parsing Data into Integer//////////////////////////////
		//vo.setStrRoomId(strValu);
		//String str1 = vo.getStrBedNameValues();
		//String str2 = vo.getStrBedTypeVlaues();		
	    try 
		{
            response.setHeader("Cache-Control", "no-cache");
        }
		catch (Exception e) 
		{
          e.printStackTrace();
        }

		return null;
	}

	*/
	/**
	 * This function is used to forward control to modify page with details of ward room bed master of particular record to be modified
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

			generateToken(request);
		
			String target = "";
		

			WardRoomBedMstBO bo = new WardRoomBedMstBO();
			WardRoomBedMstVO vo = (WardRoomBedMstVO) form;
			//System.out.println("Modify------->"+request.getParameter("chk"));
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrChk(request.getParameter("chk"));
			bo.modifyRecord(request.getParameter("chk"), vo);
		target = "modify";
		return mapping.findForward(target);

	}
	
	/**
	 * This function is invoked through ajax on add page when room is selected 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return on the basis of ward it return room number
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward PROPERTY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		//saveToken(request);
		target = "add";
		String errMsg;
		String paraId=request.getParameter("roomNo");
		String room=paraId.replace("^","@").split("@")[0];
		String searchIndex =request.getParameter("searchIndex"); 
		WardRoomBedMstVO vo=(WardRoomBedMstVO) form;
		WardRoomBedMstBO bo=new WardRoomBedMstBO();
		vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrRoomId(room);
		String res=bo.getProperty(searchIndex,room, vo);
		res = res.concat("^"+searchIndex);
		//res=res+"$"+vo.getStrBuildingBlock()+"$"+vo.getStrBedInWard()+"$"+vo.getStrBedLimit();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(res);

		} catch (Exception e) {

			errMsg = "CNTWardMst -->" + e.getMessage();
			throw new Exception(errMsg);// TODO Auto-generated catch block

		}
		return null;
		
	}	
}