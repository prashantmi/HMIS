package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import bmed.masters.bo.EquipmentParameterMstBO;
import bmed.masters.bo.EquipmentTestMstBO;
import bmed.masters.controller.fb.EquipmentParameterMstFB;
import bmed.masters.vo.EquipmentParameterMstVO;
import bmed.masters.vo.EquipmentTestMstVO;


/**
 * @author:-	Arun V R
 * Creation Date:- 6-Aug-2012 
 * Modifying Date:-
 * Used For:-	
 * Team Lead By:-	
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentParameterMstDATA 
{

	/**
	 * To get Engineering Item Type Combo populated.
	 * 
	 * @param formBean the EquipmentTest Master FormBean
	 * @param request the HttpServletRequest
	 */
	public static void initializeAdd(EquipmentParameterMstFB formBean, HttpServletRequest request) 
	{
		
		String strMsgText;
		HisUtil hisutil = null;
		EquipmentTestMstBO bo = null;
		EquipmentTestMstVO vo = null;
		
		
	
		String arr_strListPageCombos[]=null;
		
		try 
		{
			hisutil = new HisUtil("bmed", "EquipmentParameterMstDATA");
			
			vo = new EquipmentTestMstVO();
			bo = new EquipmentTestMstBO();
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			
			arr_strListPageCombos=formBean.getCombo();
					
			formBean.setStrCtDate(ctDate);
			
			TransferObjectFactory.populateData(vo, formBean);

			vo.setStrHospitalCode(hosCode);
			
			
			if (vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			
		

		} 
		catch (Exception e) 
		{
			strMsgText = "EquipmentTestMstDATA.getEnggItemTypeCmbValues(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed","EquipmentTestMstDATA.getEnggItemTypeCmbValues()", strMsgText);
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
	 * to insert the data.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void insertRecord(EquipmentParameterMstFB formBean,	HttpServletRequest request) 
	{
		EquipmentParameterMstBO bo = null;
		EquipmentParameterMstVO vo = null;
		String strmsgText = "";
		try {
			bo = new EquipmentParameterMstBO();
			vo = new EquipmentParameterMstVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);

			vo.setStrParameterName(formBean.getStrParameterName());	
			vo.setStrEffectiveFrom(formBean.getStrEffectiveFrom());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());	//from session
			vo.setStrSeatId(formBean.getStrSeatId());	//from session

			bo.insertRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			if (vo.getBExistStatus() == false) {
				formBean.setStrWarningMsg("EquipmentTest Name already exist !");
			}
			else {
				formBean.setStrNormalMsg("Record Saved Successfully!");
			}

		}
		catch (Exception e) 
		{

			//e.printStackTrace();
			
			strmsgText = "EquipmentTestMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"EquipmentTestMstDATA->insertRecord()", strmsgText);
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
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void modifyRecord(EquipmentParameterMstFB formBean,	HttpServletRequest request) 
	{
		EquipmentParameterMstBO bo = null;
		EquipmentParameterMstVO vo = null;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strmsgText = "";
		String strChk = "";
		String seatid = null;

		
		try {
			bo = new EquipmentParameterMstBO();
			vo = new EquipmentParameterMstVO();
            String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			
			seatid=request.getSession().getAttribute("SEATID").toString();
			
			//chk value::		1000@101$1
            // Primary Key::	HEMNUM_PARAMETER_ID@GNUM_HOSPITAL_CODE

			if(strChk!=null && !strChk.trim().equals("")) {
				
				strTemp = strChk.replace('@', '#').split("#");
				strTemp2 = strTemp[1].replace("$", "#").split("#");

				vo.setStrParameterId(strTemp[0]);
				vo.setStrHospitalCode(strTemp2[0]);
				
			} else {
				vo.setStrParameterId(formBean.getStrParameterId());
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
			}
			
			vo.setStrSeatId(seatid);

			bo.modifyRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			formBean.setStrParameterName(vo.getStrParameterName());
			formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			formBean.setStrRemarks(vo.getStrRemarks());
			formBean.setStrIsValid(vo.getStrIsValid());
			;

		} 
		catch (Exception e) 
		{
			strmsgText = "EquipmentTestMstDATA.modifyRecord(fb,request) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed","EquipmentTestMstDATA->modifyRecord()", strmsgText);
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
	public static boolean updateRecord(EquipmentParameterMstFB formBean,HttpServletRequest request) 
	{
		EquipmentParameterMstBO bo = null;
		EquipmentParameterMstVO vo = null;
		boolean bReturnValue = true;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strMsgText;
		String strChk = "";

		String strCombos[] = request.getParameterValues("combo");

		try {
			bo = new EquipmentParameterMstBO();
			vo = new EquipmentParameterMstVO();
			
            strChk = request.getParameter("chk");
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_PARAMETER_ID@GNUM_HOSPITAL_CODE
            
			strTemp = strChk.replace('@', '#').split("#");
			
			strTemp2 = strTemp[1].replace("$", "#").split("#");

			String seatid = request.getSession().getAttribute("SEATID").toString();

			vo.setStrParameterId(strTemp[0]);
			vo.setStrHospitalCode(strTemp2[0]);
			vo.setStrSeatId(seatid);

									
			formBean.setStrChk(strChk);

			vo.setStrParameterName(formBean.getStrParameterName());
			vo.setStrEffectiveFrom(formBean.getStrEffectiveFrom());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrIsValid(formBean.getStrIsValid());
			
			bo.updateRecord(vo);

				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				if (vo.getBExistStatus() == false) 
				{
					formBean.setStrWarningMsg("Parameter Name already exist !");
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
			strMsgText = "EquipmentParameterMstDATA.updateRecord(vo) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","EquipmentParameterMstDATA->updateRecord()", strMsgText);
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
