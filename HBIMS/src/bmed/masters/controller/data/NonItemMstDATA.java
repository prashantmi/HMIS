package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.hisconfig.Config;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.masters.bo.NonItemMstBO;
import bmed.masters.controller.fb.NonItemMstFB;
import bmed.masters.vo.NonItemMstVO;


/**
 * @author:-	Vivek Aggarwal   
 * Creation Date:- 11-April-2011 
 * Modifying Date:- 13-April-2011	
 *  Module:- BMED(HIS Project)
 * 
 */
public class NonItemMstDATA 
{

	/**
	 * To get Engineering Item Type Combo populated.
	 * 
	 * @param formBean_p the Non-Item Master FormBean
	 * @param request_p the HttpServletRequest
	 */
	public static void initializeAdd(NonItemMstFB formBean_p, HttpServletRequest request_p) 
	{
		
		String strMsgText;
		HisUtil hisutil = null;
		NonItemMstBO bo = null;
		NonItemMstVO vo = null;
		
		
		String strEngineeringItemTypeCmb=null;
		String arr_strListPageCombos[]=null;
		String strEngineeringItemSubTypeCmb=null;

		try 
		{
			hisutil = new HisUtil("bmed", "NonItemMstDATA");
			
			vo = new NonItemMstVO();
			bo = new NonItemMstBO();
			
			String strHosCode = request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			
			arr_strListPageCombos=formBean_p.getCombo();
			
			if(arr_strListPageCombos!=null && arr_strListPageCombos.length >= 2 ) {
				formBean_p.setStrEngineeringItemTypeId(arr_strListPageCombos[0]);
				formBean_p.setStrEngineeringItemSubTypeId(arr_strListPageCombos[1]);
				
			}
			else {
				formBean_p.setStrEngineeringItemTypeId("0");
				formBean_p.setStrEngineeringItemSubTypeId("0");
			}
			
			formBean_p.setStrCtDate(strCtDate);
			
			TransferObjectFactory.populateData(vo, formBean_p);

			vo.setStrHospitalCode(strHosCode);
			
            // Calling BO object
			bo.initializeAdd(vo);
			
			if (vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			if (vo.getStrEngineeringItemTypeWS() != null && vo.getStrEngineeringItemTypeWS().size() > 0) 
			{
				strEngineeringItemTypeCmb = hisutil.getOptionValue(vo.getStrEngineeringItemTypeWS(), vo.getStrEngineeringItemTypeId(),"0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemTypeCmb = "<option value='0'>Select Value</option>";
			}
			
			if (vo.getStrEngineeringItemSubWS() != null && vo.getStrEngineeringItemSubWS().size() > 0) 
			{
				strEngineeringItemSubTypeCmb = hisutil.getOptionValue(vo.getStrEngineeringItemSubWS(), vo.getStrEngineeringItemSubTypeId(),"0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemSubTypeCmb = "<option value='0'>Select Value</option>";
			}
			
			formBean_p.setStrEngineeringItemTypeCmb(strEngineeringItemTypeCmb); 
			formBean_p.setStrEngineeringItemSubCmb(strEngineeringItemSubTypeCmb); 

		} 
		catch (Exception e) 
		{
			strMsgText = "NonItemMstDATA.getEnggItemTypeCmbValues(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed","NonItemMstDATA.getEnggItemTypeCmbValues()", strMsgText);
		formBean_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
		
		eObj = null;
		}
		finally 
		{
			hisutil = null;
			bo = null;
			vo = null;
		}
	}
		
	
	/**
	 * To get Engineering Item Sub Type Combo populated.
	 * 
	 * @param formBean_p the Non-Item Master form bean
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getEnggItemSubTypeCmb(NonItemMstFB formBean_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strMsgText = "";
		HisUtil hisutil = null;
		NonItemMstBO bo = null;
		NonItemMstVO vo = null;
		
		String strEngineeringItemTypeId = "";
		String strEngineeringItemSubTypeWS = "";
		
		try 
		{
			hisutil = new HisUtil("bmed", "NonItemMstDATA");
			
			vo = new NonItemMstVO();
			bo = new NonItemMstBO();
			
			String strHosCode = request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
	
			strEngineeringItemTypeId = request_p.getParameter("enggItemType");
			vo.setStrHospitalCode(strHosCode);
			vo.setStrEngineeringItemTypeId(strEngineeringItemTypeId);
			
			bo.getStrEngineeringItemSubType(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if (vo.getStrEngineeringItemSubWS() != null && vo.getStrEngineeringItemSubWS().size() > 0) 
			{
				strEngineeringItemSubTypeWS = hisutil.getOptionValue(vo.getStrEngineeringItemSubWS(),"","0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemSubTypeWS = "<option value='0'>Select Value</option>";
			}
			
			   response_p.setHeader("Cache-Control", "no-cache");
			   response_p.getWriter().print(strEngineeringItemSubTypeWS);
			
		} 
		catch (Exception e) 
		{
			strMsgText = "NonItemMstDATA.getEnggItemSubTypeCmb(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed", "NonItemMstDATA.getEnggItemSubTypeCmb()", strMsgText);
		
			try 
			{
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
	
			}
			catch (Exception e1)
			{
				new HisException("bmed","NonItemMstDATA.getEnggItemSubTypeCmb()", strMsgText);
			}

		eObj = null;
		} 
		finally
		{
			hisutil = null;
			vo = null;
			bo = null;
		}

	}

	
	/**
	 * to insert the data.
	 * 
	 * @param formBean_p the form bean
	 * @param request_p the request
	 */
	public static void insertRecord(NonItemMstFB formBean_p,	HttpServletRequest request_p) 
	{
		NonItemMstBO bo = null;
		NonItemMstVO vo = null;
		String strMsgText = "";
		try {
			bo = new NonItemMstBO();
			vo = new NonItemMstVO();

			String strHosCode = request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request_p.getSession().getAttribute("SEATID").toString();

			formBean_p.setStrHospitalCode(strHosCode);
			formBean_p.setStrSeatId(strSeatId);

			vo.setStrNonItemName(formBean_p.getStrNonItemName());	
			vo.setStrEngineeringItemTypeId(formBean_p.getStrEngineeringItemTypeId());
			vo.setStrEngineeringItemSubTypeId(formBean_p.getStrEngineeringItemSubTypeId());
			vo.setStrEffectiveFrom(formBean_p.getStrEffectiveFrom());
			vo.setStrRemarks(formBean_p.getStrRemarks());
			vo.setStrHospitalCode(formBean_p.getStrHospitalCode());	//from session
			vo.setStrSeatId(formBean_p.getStrSeatId());	//from session

			bo.insertRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			if (vo.getBExistStatus() == false) {
				formBean_p.setStrWarningMsg("Non-Item Name already exist !");
			}
			else {
				formBean_p.setStrNormalMsg("Record Saved Successfully!");
			}

		}
		catch (Exception e) 
		{

//			e.printStackTrace();
			
			strMsgText = "NonItemMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"NonItemMstDATA->insertRecord()", strMsgText);
			formBean_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			bo = null;
			vo = null;
		}

	}

	/**
	 * to get the data for modify page.
	 * 
	 * @param formBean_p the form bean
	 * @param request_p the request
	 */
	public static void modifyRecord(NonItemMstFB formBean_p,	HttpServletRequest request_p) 
	{
		NonItemMstBO bo = null;
		NonItemMstVO vo = null;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strMsgText = "";
		String strChk = "";
		String strSeatId = null;

		
		try {
			bo = new NonItemMstBO();
			vo = new NonItemMstVO();
            String strCombos[] = request_p.getParameterValues("combo");

			strChk = request_p.getParameter("chk");
			
			strSeatId=request_p.getSession().getAttribute("SEATID").toString();
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_NONITEM_ID@GNUM_HOSPITAL_CODE

			if(strChk!=null && !strChk.trim().equals("")) {
				
				strTemp = strChk.replace('@', '#').split("#");
				strTemp2 = strTemp[1].replace("$", "#").split("#");

				vo.setStrNonItemId(strTemp[0]);
				vo.setStrHospitalCode(strTemp2[0]);
				
			} else {
				vo.setStrNonItemId(formBean_p.getStrNonItemId());
				vo.setStrHospitalCode(formBean_p.getStrHospitalCode());
			}
			
			vo.setStrSeatId(strSeatId);

			vo.setStrEngineeringItemTypeId(strCombos[0]);
			vo.setStrEngineeringItemSubTypeId(strCombos[1]);
			
			
			

			bo.modifyRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			formBean_p.setStrEngineeringItemTypeName(vo.getStrEngineeringItemTypeName());
			formBean_p.setStrEngineeringItemSubTypeName(vo.getStrEngineeringItemSubTypeName());
			formBean_p.setStrNonItemName(vo.getStrNonItemName());
			formBean_p.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			formBean_p.setStrRemarks(vo.getStrRemarks());
			formBean_p.setStrIsValid(vo.getStrIsValid());
			formBean_p.setStrEngineeringItemTypeId(vo.getStrEngineeringItemTypeId());
			formBean_p.setStrEngineeringItemSubTypeId(vo.getStrEngineeringItemSubTypeId());

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strMsgText = "NonItemMstDATA.modifyRecord(fb,request_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed","NonItemMstDATA->modifyRecord()", strMsgText);
			formBean_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			bo = null;
			vo = null;
		}

	}

	/**
	 * to update the record after modifying it.
	 * 
	 * @param formBean_p the form bean
	 * @param request_p the request
	 * 
	 * @return true, if update record
	 */
	public static boolean updateRecord(NonItemMstFB formBean_p,HttpServletRequest request_p) 
	{
		NonItemMstBO bo = null;
		NonItemMstVO vo = null;
		boolean bReturnValue = true;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strMsgText;
		String strChk = "";

		String strCombos[] = request_p.getParameterValues("combo");

		try {
			bo = new NonItemMstBO();
			vo = new NonItemMstVO();
			
            strChk = request_p.getParameter("chk");
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_NONITEM_ID@GNUM_HOSPITAL_CODE
            
			strTemp = strChk.replace('@', '#').split("#");
			
			strTemp2 = strTemp[1].replace("$", "#").split("#");

			String strSeatId = request_p.getSession().getAttribute("SEATID").toString();

			vo.setStrNonItemId(strTemp[0]);
			vo.setStrHospitalCode(strTemp2[0]);
			vo.setStrSeatId(strSeatId);

			vo.setStrEngineeringItemTypeId(strCombos[0]);
			vo.setStrEngineeringItemSubTypeId(strCombos[1]);
						
			formBean_p.setStrChk(strChk);

			vo.setStrNonItemName(formBean_p.getStrNonItemName());
			vo.setStrEffectiveFrom(formBean_p.getStrEffectiveFrom());
			vo.setStrRemarks(formBean_p.getStrRemarks());
			vo.setStrIsValid(formBean_p.getStrIsValid());
			
			bo.updateRecord(vo);

				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				if (vo.getBExistStatus() == false) 
				{
					formBean_p.setStrWarningMsg("Non-Item Name already exist !");
					bReturnValue = false;

				}
				else 
				{
						formBean_p.setStrNormalMsg("Record Modified Successfully");
				}
			} 
			catch (Exception e) 
			{
			bReturnValue = false;
			strMsgText = "NonItemMstDATA.updateRecord(vo) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","NonItemMstDATA->updateRecord()", strMsgText);
			formBean_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			} 
			finally 
			{
			bo = null;
			vo = null;
			}
				
			return bReturnValue;
	}
	
}
