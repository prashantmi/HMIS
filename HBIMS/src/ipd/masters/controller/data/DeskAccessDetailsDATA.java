package ipd.masters.controller.data;

import ipd.masters.bo.DeskAccessDetailsBO;
import ipd.masters.controller.fb.DeskAccessDetailsFB;
import ipd.masters.vo.DeskAccessDetailsVO;
import hisglobal.exceptions.HisException;

import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @author Adil Wasi
 *
 */

public class DeskAccessDetailsDATA 
{
	/**
	 * to display the current date and userName,DeskName combos and menu list 
	 * 
	 * @param formBean_p
	 * @param request_p
	 */
	public static void getInitialValues(DeskAccessDetailsFB formBean_p,
			HttpServletRequest request_p) 
	{

		DeskAccessDetailsBO deskAccessDetailsBO = null;
		DeskAccessDetailsVO deskAccessDetailsVO = null;
		HisUtil hisutil = null;

		String strmsgText = "";

		String strCtDate = "";
		String strHosCode = "";
		String strSeatId = "";

		String strUserNameCombo = "";
		String strDeskNameCombo = "";

		try 
		{
			deskAccessDetailsBO = new DeskAccessDetailsBO();
			deskAccessDetailsVO = new DeskAccessDetailsVO();

			hisutil = new HisUtil("transutil", "DeskAccessDetailsDATA");
			strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			formBean_p.setStrCtDate(strCtDate);

			strHosCode = request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			strSeatId = request_p.getSession().getAttribute("SEATID").toString();


			deskAccessDetailsVO.setStrHospitalCode(strHosCode);
			deskAccessDetailsVO.setStrSeatId(strSeatId);


			deskAccessDetailsBO.getInitialValues(deskAccessDetailsVO);

			if (deskAccessDetailsVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(deskAccessDetailsVO.getStrMsgString());
			}

			strUserNameCombo = hisutil.getOptionValue(deskAccessDetailsVO.getUserNameComboWS(),
					"0^Select Value", "Select Value", true);
			deskAccessDetailsVO.setStrUserNameCombo(strUserNameCombo);

			formBean_p.setStrUserNameCombo(strUserNameCombo);

			strDeskNameCombo = hisutil.getOptionValue(deskAccessDetailsVO.getDeskNameComboWS(),
					"0^Select Value", "Select Value", true);
			deskAccessDetailsVO.setStrDeskNameCombo(strDeskNameCombo);

			formBean_p.setStrDeskNameCombo(strDeskNameCombo);

		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			strmsgText = "hisglobaltransactionutil.DeskAccessDetailsDATA.getInitialValues(deskAccessDetailsVO) --> "
				+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DeskAccessDetailsDATA->getInitialValues()", strmsgText);
			formBean_p.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally 
		{
			deskAccessDetailsBO=null;
			deskAccessDetailsVO=null;
			hisutil = null;
		}

	}

	/**
	 * To get values of LEFT MENU LIST
	 * 
	 * @param form
	 * @param request_p
	 */
	public static void getLeftList(DeskAccessDetailsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response) 
	{

		String strmsgText = "";
		DeskAccessDetailsBO deskAccessDetailsBO = null;
		DeskAccessDetailsVO deskAccessDetailsVO = null;
		HisUtil hisutil = null;

		String strHospitalCode = "";
		String strSeatId = "";
		String strUserId = "";
		String strDeskId = "";

		String strLeftListValues = "";


		try 
		{

			deskAccessDetailsBO = new DeskAccessDetailsBO();
			deskAccessDetailsVO = new DeskAccessDetailsVO();
			hisutil = new HisUtil("transutil", "DeskAccessDetailsDATA");

			strHospitalCode = request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			//request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			strSeatId = request_p.getSession().getAttribute("SEATID").toString();//request_p.getSession().getAttribute("SEATID").toString();

			strUserId = (String) request_p.getParameter("userId");
			strDeskId = (String) request_p.getParameter("deskId");
			//System.out.println("strUserId"+strUserId);
			//System.out.println("strDeskId"+strDeskId);

			deskAccessDetailsVO.setStrHospitalCode(strHospitalCode);
			deskAccessDetailsVO.setStrSeatId(strSeatId);
			deskAccessDetailsVO.setStrUserId(strUserId);
			deskAccessDetailsVO.setStrDeskId(strDeskId);

			deskAccessDetailsBO.getLeftList(deskAccessDetailsVO);
			if (deskAccessDetailsVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(deskAccessDetailsVO.getStrMsgString());
			}

			strLeftListValues = hisutil.getOptionValue(deskAccessDetailsVO.getLeftMenuListWS(), "", "", true);

			// System.out.println("strLeftListValues-"+strLeftListValues);
			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strLeftListValues);

			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) 
		{
			strmsgText = "hisglobaltransactionutil.DeskAccessDetailsDATA.getLeftList(deskAccessDetailsVO) --> "
				+ e.getMessage();
			HisException eObj = new HisException("hisglobaltransactionutil",
					"DeskAccessDetailsDATA->getLeftList()", strmsgText);
			try 
			{

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");

			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			eObj = null;
		} 
		finally 
		{
			deskAccessDetailsVO = null;
			deskAccessDetailsBO = null;
			hisutil = null;
		}
	}

	/**
	 * To get values of RIGHT MENU LIST
	 * 
	 * @param form
	 * @param request_p
	 */
	public static void getRightList(DeskAccessDetailsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response) 
	{

		String strmsgText = "";
		DeskAccessDetailsBO deskAccessDetailsBO = null;
		DeskAccessDetailsVO deskAccessDetailsVO = null;
		HisUtil hisutil = null;

		String strHospitalCode = "";
		String strSeatId = "";
		String strUserId = "";
		String strDeskId = "";

		String strRightListValues = "";


		try 
		{

			deskAccessDetailsBO = new DeskAccessDetailsBO();
			deskAccessDetailsVO = new DeskAccessDetailsVO();
			hisutil = new HisUtil("transutil", "DeskAccessDetailsDATA");

			strHospitalCode = request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			strSeatId = request_p.getSession().getAttribute("SEATID").toString();

			strUserId = (String) request_p.getParameter("userId");
			strDeskId = (String) request_p.getParameter("deskId");

			deskAccessDetailsVO.setStrHospitalCode(strHospitalCode);
			deskAccessDetailsVO.setStrSeatId(strSeatId);
			deskAccessDetailsVO.setStrUserId(strUserId);
			deskAccessDetailsVO.setStrDeskId(strDeskId);

			deskAccessDetailsBO.getRightList(deskAccessDetailsVO);
			if (deskAccessDetailsVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(deskAccessDetailsVO.getStrMsgString());
			}

			strRightListValues = hisutil.getOptionValue(deskAccessDetailsVO.getRightMenuListWS(), "", "", true);

			// System.out.println("strRightListValues-"+strRightListValues);

			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strRightListValues);

			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
		catch (Exception e) 
		{
			strmsgText = "hisglobaltransactionutil.DeskAccessDetailsDATA.getRightList(deskAccessDetailsVO) --> "
				+ e.getMessage();
			HisException eObj = new HisException("hisglobaltransactionutil",
					"DeskAccessDetailsDATA->getRightList()", strmsgText);
			try 
			{

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");

			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			eObj = null;
		} 
		finally 
		{
			deskAccessDetailsVO = null;
			deskAccessDetailsBO = null;
			hisutil = null;
		}
	}

	/**
	 * to insert the data
	 * 
	 * @param formBean_p
	 * @param request_p
	 */
	public static void insertRecord(DeskAccessDetailsFB formBean_p,
			HttpServletRequest request_p) 
	{
		DeskAccessDetailsBO deskAccessDetailsBO = null;
		DeskAccessDetailsVO deskAccessDetailsVO = null;
		String strmsgText = "";

		int nRightListLen =0;
		String RightListArray[] = null;
		String strHospitalCode = "";
		String strSeatId = "";

		try 
		{
			deskAccessDetailsBO = new DeskAccessDetailsBO();
			deskAccessDetailsVO = new DeskAccessDetailsVO();

			strHospitalCode = request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			strSeatId = request_p.getSession().getAttribute("SEATID").toString();


			deskAccessDetailsVO.setStrHospitalCode(strHospitalCode);
			deskAccessDetailsVO.setStrSeatId(strSeatId);
			deskAccessDetailsVO.setStrIsValid("1");
			deskAccessDetailsVO.setStrEffectiveFrom(formBean_p.getStrEffectiveFrom());
			deskAccessDetailsVO.setStrUserId(formBean_p.getStrUserId());
			deskAccessDetailsVO.setStrDeskId(formBean_p.getStrDeskId());
			deskAccessDetailsVO.setStrMenuPosition(formBean_p.getStrMenuPosition());

			// System.out.println("data formBean_p.getStrRightMenuNames()"+formBean_p.getStrRightMenuNames().length);
			RightListArray = formBean_p.getStrRightMenuNames();
			nRightListLen = RightListArray.length;

			deskAccessDetailsBO.deleteRecords(deskAccessDetailsVO);
			if (deskAccessDetailsVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(deskAccessDetailsVO.getStrMsgString());
			}
			for(int i=0;i<nRightListLen;i++)
			{
				if(!RightListArray[i].equals("0"))
				{
					deskAccessDetailsVO.setStrMenuId(RightListArray[i]);
					deskAccessDetailsBO.insertRecord(deskAccessDetailsVO);
					if (deskAccessDetailsVO.getStrMsgType().equals("1")) {
						throw new Exception(deskAccessDetailsVO.getStrMsgString());
					}
				}

			}

			if (deskAccessDetailsVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(deskAccessDetailsVO.getStrMsgString());
			}

			else 
			{
				formBean_p.setStrMsg("Data Saved Successfully");
			}

		}
		catch (Exception e) 
		{
			strmsgText = "hisglobaltransactionutil.DeskAccessDetailsDATA.getInitialValues(deskAccessDetailsVO) --> "
				+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DeskAccessDetailsDATA->getInitialValues()", strmsgText);
			formBean_p.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally 
		{
			deskAccessDetailsBO=null;
			deskAccessDetailsVO=null;

		}

	}
}
