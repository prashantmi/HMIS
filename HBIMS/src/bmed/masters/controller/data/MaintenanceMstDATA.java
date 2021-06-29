package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.hisconfig.Config;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.masters.bo.MaintenanceMstBO;
import bmed.masters.controller.fb.MaintenanceMstFB;
import bmed.masters.vo.MaintenanceMstVO;



/**
 * @author   Vivek Aggarwal
 * Creation Date:- 19-Jan-2011 
 * Modifying Date:- 21-Jan-2011
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class MaintenanceMstDATA 
{
	
	/**
	 * To populate the Engineering Item Type Combo on the Add Page.
	 * 
	 * @param formBean the Maintenance Master Form Bean
	 * @param request the HttpServletRequest
	 */
	public static void initializeAdd(MaintenanceMstFB formBean, HttpServletRequest request) 
	{
		
		String strMsgText;
		HisUtil hisutil = null;
		MaintenanceMstBO bo = null;
		MaintenanceMstVO vo = null;
			
		String strEngineeringItemTypeCmb=null;
		String arr_strListPageCombos[]=null;
		
		String strEngineeringItemSubTypeCmb=null;
		
		try 
		{
			hisutil = new HisUtil("bmed", "MaintenanceMstDATA");
			
			vo = new MaintenanceMstVO();
			bo = new MaintenanceMstBO();
			
			String strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			
			arr_strListPageCombos=formBean.getCombo();
			
			if(arr_strListPageCombos!=null && arr_strListPageCombos.length >= 2 ) {
				formBean.setStrEngineeringItemTypeId(arr_strListPageCombos[0]);
				formBean.setStrEngineeringItemSubTypeId(arr_strListPageCombos[1]);
				
			}
			else {
				formBean.setStrEngineeringItemTypeId("0");
				formBean.setStrEngineeringItemSubTypeId("0");
			}
			
			
			formBean.setStrCtDate(ctDate);
			
			TransferObjectFactory.populateData(vo, formBean);
			
			vo.setStrHospitalCode(strHosCode);
			
			
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
			if (vo.getStrEngineeringItemSubTypeWS() != null && vo.getStrEngineeringItemSubTypeWS().size() > 0) 
			{
				strEngineeringItemSubTypeCmb = hisutil.getOptionValue(vo.getStrEngineeringItemSubTypeWS(), vo.getStrEngineeringItemSubTypeId(),"0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemSubTypeCmb = "<option value='0'>Select Value</option>";
			}
			
			formBean.setStrEngineeringItemTypeCmb(strEngineeringItemTypeCmb);
			formBean.setStrEngineeringItemSubCmb(strEngineeringItemSubTypeCmb); 
			
		} 
		catch (Exception e) 
		{
			strMsgText = "Maintenance Master.MaintenanceMstDATA.getEnggItemTypeCmbValues(vo) --> "+ e.getMessage();
		HisException eObj = new HisException("bmed","MaintenanceMstDATA.getEnggItemTypeCmbValues()", strMsgText);
		formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");

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
	 * To get Engineering Item Sub Type Combo populated  based on the Engineering Item Type Combo.
	 * 
	 * @param formBean the Maintenance Master form bean
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse

	 */
	public static void getEnggItemSubTypeCmbValues(MaintenanceMstFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{
		String strMsgText = "";
		HisUtil hisutil = null;
		MaintenanceMstBO bo = null;
		MaintenanceMstVO vo = null;
		
		String strEngineeringItemTypeId ;
		String strEngineeringItemSubTypeWS ;
		
		try 
		{
			hisutil = new HisUtil("bmed", "MaintenanceMstDATA");
			
			vo = new MaintenanceMstVO();
			bo = new MaintenanceMstBO();
			
			String strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
	
			strEngineeringItemTypeId = request.getParameter("enggItemType");
			vo.setStrHospitalCode(strHosCode);
			vo.setStrEngineeringItemTypeId(strEngineeringItemTypeId);
			
// Calling BO Method
			bo.getEnggItemSubTypeCmbValues(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if (vo.getStrEngineeringItemSubTypeWS() != null && vo.getStrEngineeringItemSubTypeWS().size() > 0) 
			{
				strEngineeringItemSubTypeWS = hisutil.getOptionValue(vo.getStrEngineeringItemSubTypeWS(),"","0^Select Value", false);
			}
			
			else
			{
				strEngineeringItemSubTypeWS = "<option value='0'>Select Value</option>";
			}
			
			   response.setHeader("Cache-Control", "no-cache");
			   response.getWriter().print(strEngineeringItemSubTypeWS);
			
		} 
		catch (Exception e) 
		{
			strMsgText = "Maintenance Master.MaintenanceMstDATA.getEnggItemSubTypeCmbValues(vo) --> "
				+ e.getMessage();
		HisException eObj = new HisException("bmed","MaintenanceMstDATA.getEnggItemSubTypeCmbValues()", strMsgText);
		try 
		{
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print("ERROR#### Application Error [ERROR ID : " + eObj.getErrorID()
							+ "],Contact System Administrator! ");

		} 
		catch (Exception e1)
		{
			new HisException("bmed","MaintenanceMstDATA.getEnggItemSubTypeCmbValues()", strMsgText);
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
	 * to insert the data in the table HEMT_ENGG_MAINTENANCE_MST.
	 * 
	 * @param formBean the Maintenance Master form bean
	 * @param request the HttpServletRequest
	 */
	public static void insertRecord(MaintenanceMstFB formBean,	HttpServletRequest request) 
	{
		MaintenanceMstBO bo = null;
		MaintenanceMstVO vo = null;
		String strMsgText = "";
		try
		{
			bo = new MaintenanceMstBO();
			vo = new MaintenanceMstVO();

			String strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();

			formBean.setStrHospitalCode(strHosCode);
			formBean.setStrSeatId(seatid);

			vo.setStrMaintenanceName(formBean.getStrMaintenanceName());	
			vo.setStrEngineeringItemTypeId(formBean.getStrEngineeringItemTypeId());
			vo.setStrEngineeringItemSubTypeId(formBean.getStrEngineeringItemSubTypeId());
			vo.setStrEffectiveFrom(formBean.getStrEffectiveFrom());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());	//from session
			vo.setStrSeatId(formBean.getStrSeatId());	//from session

				
			bo.insertRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			if (vo.isBExistStatus() == false) {
				formBean.setStrWarningMsg("Maintenance Name already exist !");
			}
			else {
				formBean.setStrNormalMsg("Record Saved Successfully!");
			}

		} 
		catch (Exception e)
		{

			strMsgText = "Maintenance Master.MaintenanceMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"MaintenanceMstDATA->insertRecord()", strMsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
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
	 * @param formBean the Maintenance Master form bean
	 * @param request the HttpServletRequest request
	 */
	public static void modifyRecord(MaintenanceMstFB formBean,	HttpServletRequest request) 
	{
		MaintenanceMstBO bo = null;
		MaintenanceMstVO vo = null;
		String arr_strTemp[] = null;
		String arr_strTemp2[] = null;
		String strMsgText;
		String strChk ;

		
		try {
			bo = new MaintenanceMstBO();
			vo = new MaintenanceMstVO();
            String arr_strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			
			//chk value::		10@108$6
            // Primary Key::    HEMNUM_MAINTE_ID@GNUM_HOSPITAL_CODE
			
			if(strChk!=null && !strChk.trim().equals("")) {
				arr_strTemp = strChk.replace('@', '#').split("#");
				
				arr_strTemp2 = arr_strTemp[1].replace("$", "#").split("#");
				
				vo.setStrMaintenanceId(arr_strTemp[0]);
				vo.setStrHospitalCode(arr_strTemp2[0]);
			} else {
				
				vo.setStrMaintenanceId(formBean.getStrMaintenanceId());
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				
			}

			
			
			String seatid = request.getSession().getAttribute("SEATID").toString();


			
			vo.setStrSeatId(seatid);

			vo.setStrEngineeringItemTypeId(arr_strCombos[0]);
			vo.setStrEngineeringItemSubTypeId(arr_strCombos[1]);

			bo.modifyRecord(vo);
			
			
			formBean.setStrEngineeringItemTypeName(vo.getStrEngineeringItemTypeName());
			formBean.setStrEngineeringItemSubTypeName(vo.getStrEngineeringItemSubTypeName());
			formBean.setStrMaintenanceName(vo.getStrMaintenanceName());
			formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			formBean.setStrRemarks(vo.getStrRemarks());
			formBean.setStrIsValid(vo.getStrIsValid());
			formBean.setStrEngineeringItemTypeId(vo.getStrEngineeringItemTypeId());
			formBean.setStrEngineeringItemSubTypeId(vo.getStrEngineeringItemSubTypeId());
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			formBean.setStrEngineeringItemTypeCmb(vo.getStrEngineeringItemTypeCmb());
			formBean.setStrEngineeringItemSubType(vo.getStrEngineeringItemSubType());

			formBean.setStrMaintenanceName(vo.getStrMaintenanceName());
			formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			formBean.setStrRemarks(vo.getStrRemarks());
			formBean.setStrIsValid(arr_strCombos[2]);

		} 
		catch (Exception e) 
		{

			strMsgText = "MaintenanceMstDATA.modifyRecord(fb,request) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","MaintenanceMstDATA->modifyRecord()", strMsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
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
	 * @param formBean the form bean
	 * @param request the request
	 * 
	 * @return true, if update record
	 */
	public static boolean updateRecord(MaintenanceMstFB formBean,HttpServletRequest request) 
	{
		MaintenanceMstBO bo = null;
		MaintenanceMstVO vo = null;
		boolean bReturnValue = true;
		String arr_strTemp[] = null;
		String arr_strTemp2[] = null;
		String strMsgText;
		String strChk;

		String arr_strCombos[] = request.getParameterValues("combo");

		try {
			bo = new MaintenanceMstBO();
			vo = new MaintenanceMstVO();
			
            strChk = request.getParameter("chk");
			
			// chk value::     10@108$6
            // Primary Key::   HEMNUM_MAINTE_ID@GNUM_HOSPITAL_CODE
			
			if(strChk!=null && !strChk.trim().equals("")) {
				arr_strTemp = strChk.replace('@', '#').split("#");
				
				arr_strTemp2 = arr_strTemp[1].replace("$", "#").split("#");
				
				vo.setStrMaintenanceId(arr_strTemp[0]);
				vo.setStrHospitalCode(arr_strTemp2[0]);


				
			} else {
				
				vo.setStrMaintenanceId(formBean.getStrMaintenanceId());
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				
			}
            
			String seatid = request.getSession().getAttribute("SEATID").toString();

			vo.setStrSeatId(seatid);

			vo.setStrEngineeringItemTypeId(arr_strCombos[0]);
			vo.setStrEngineeringItemSubTypeId(arr_strCombos[1]);
			
			
			formBean.setStrChk(strChk);

			vo.setStrMaintenanceName(formBean.getStrMaintenanceName());
			vo.setStrEffectiveFrom(formBean.getStrEffectiveFrom());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrIsValid(formBean.getStrIsValid());
			
			bo.updateRecord(vo);

				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				
				if (vo.isBExistStatus() == false)		// There is a duplicacy 
				{
					formBean.setStrWarningMsg("Maintenance Name already exist !");
					bReturnValue = false;

				}	
				else 
				{
						formBean.setStrNormalMsg("Record Modified Successfully");
				}
			} 
			catch (Exception e) 
			{
			bReturnValue = false;
			strMsgText = "Maintenance Master.MaintenanceMstDATA.updateRecord(vo) --> "
							+ e.getMessage();
			HisException eObj = new HisException("bmed","MaintenanceMstDATA->updateRecord()", 
												strMsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
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
