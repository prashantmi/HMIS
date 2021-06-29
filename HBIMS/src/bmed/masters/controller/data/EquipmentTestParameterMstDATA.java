package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.FormView;

import bmed.masters.bo.EquipmentParameterMstBO;
import bmed.masters.bo.EquipmentTestMstBO;
import bmed.masters.bo.EquipmentTestParameterMstBO;
import bmed.masters.controller.fb.EquipmentParameterMstFB;
import bmed.masters.controller.fb.EquipmentTestMstFB;
import bmed.masters.controller.fb.EquipmentTestParameterMstFB;
import bmed.masters.vo.EquipmentParameterMstVO;
import bmed.masters.vo.EquipmentTestMstVO;
import bmed.masters.vo.EquipmentTestParameterMstVO;


/**
 * @author:-	Arun V R
 * Creation Date:- 7-Aug-2012 
 * Modifying Date:-
 * Used For:-	
 * Team Lead By:-	
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestParameterMstDATA 
{

	/**
	 * To get Engineering Item Type Combo populated.
	 * 
	 * @param formBean the EquipmentTest Master FormBean
	 * @param request the HttpServletRequest
	 */
	public static void initializeAdd(EquipmentTestParameterMstFB formBean, HttpServletRequest request) 
	{
		
		String strMsgText;
		HisUtil hisutil = null;
		EquipmentTestParameterMstBO bo = null;
		EquipmentTestParameterMstVO vo = null;
		
		String strTestNameCmb=null;
		String strAvailableParameterOptions=null;
	
		String arr_strListPageCombos[]=null;
		
		try 
		{
			hisutil = new HisUtil("bmed", "EquipmentTestParameterMstDATA");
			
			vo = new EquipmentTestParameterMstVO();
			bo = new EquipmentTestParameterMstBO();
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			
			arr_strListPageCombos=formBean.getCombo();
					
			formBean.setStrCtDate(ctDate);
			formBean.setStrEffectiveFrom(ctDate);
			
			TransferObjectFactory.populateData(vo, formBean);

			vo.setStrHospitalCode(hosCode);
			
			bo.initializeAdd(vo);
			
			
			if (vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			if (vo.getStrTestNameWS() != null && vo.getStrTestNameWS().size() > 0) 
			{
				strTestNameCmb = hisutil.getOptionValue(vo.getStrTestNameWS(), vo.getStrTestId(),"0^Select Value", false);
			}
			
			else
			{
				strTestNameCmb = "<option value='0'>Select Value</option>";
			}
			
			
			/* Getting different combo options */
		//	strAvailableParameterOptions = hisutil.getOptionValue(
				//	vo.getWrsAvailableParameterOptions(), "0", "", false);
			

			/* Getting different combo options in form bean */
		//formBean.setStrAvailableTestOptions(strAvailableParameterOptions);
			
			
			
			formBean.setStrTestNameCmb(strTestNameCmb);
			
		
			
			
			
		

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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
		
	
	public static void getParameterCmb(EquipmentTestParameterMstFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		String strMsgText = "";
		HisUtil hisutil = null;
		EquipmentTestParameterMstBO bo = null;
		EquipmentTestParameterMstVO vo = null;
		
		String strTestId = "";
		String strAvailableParameterOptions = "";
		
		try 
		{
			hisutil = new HisUtil("bmed", "EquipmentTestParameterMstDATA");
			
			vo = new EquipmentTestParameterMstVO();
			bo = new EquipmentTestParameterMstBO();
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
	
			strTestId = request.getParameter("strTestId");
			vo.setStrHospitalCode(hosCode);
			vo.setStrTestId(strTestId);
			
			bo.getStrParameterCmb(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if (vo.getWrsAvailableParameterOptions() != null && vo.getWrsAvailableParameterOptions().size() > 0) 
			{
				strAvailableParameterOptions = hisutil.getOptionValue(vo.getWrsAvailableParameterOptions(),"","", false);
			}
			
			else
			{
				//strAvailableParameterOptions = "<option value='0'>Select Value</option>";
			}
			
			TransferObjectFactory.populateData(formBean, vo);
			
			formBean.setStrAvailableTestOptions(strAvailableParameterOptions);
			
		
			 //  response.setHeader("Cache-Control", "no-cache");
			  // response.getWriter().print(strAvailableParameterOptions);
			
		} 
		catch (Exception e) 
		{
			strMsgText = "EquipmentTestMstDATA.getEnggItemSubTypeCmb(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed", "EquipmentTestMstDATA.getEnggItemSubTypeCmb()", strMsgText);
		
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
				new HisException("bmed","EquipmentTestMstDATA.getEnggItemSubTypeCmb()", strMsgText);
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
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void insertRecord(EquipmentTestParameterMstFB formBean,	HttpServletRequest request) 
	{
		EquipmentTestParameterMstBO bo = null;
		EquipmentTestParameterMstVO vo = null;
		String strmsgText = "";
		try {
			bo = new EquipmentTestParameterMstBO();
			vo = new EquipmentTestParameterMstVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);

			vo.setStrTestId(formBean.getStrTestId());	
			vo.setArrStrSelectedTestId(formBean.getArrStrSelectedTestId());
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

			e.printStackTrace();
			
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
	public static void modifyRecord(EquipmentTestParameterMstFB formBean,	HttpServletRequest request) 
	{
		EquipmentTestParameterMstBO bo = null;
		EquipmentTestParameterMstVO vo = null;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strmsgText = "";
		String strChk = "";
		String seatid = null;

		HisUtil hisutil = null;
		try {
			bo = new EquipmentTestParameterMstBO();
			vo = new EquipmentTestParameterMstVO();
            String strCombos[] = request.getParameterValues("combo");
            hisutil=  new HisUtil("bmed", "EquipmentTestParameterMstDATA");
			strChk = request.getParameter("chk");
			
			seatid=request.getSession().getAttribute("SEATID").toString();
			
			//chk value::		1000@101$1
            // Primary Key::	HEMNUM_PARAMETER_ID@GNUM_HOSPITAL_CODE

			if(strChk!=null && !strChk.trim().equals("")) {
				
				strTemp = strChk.replace('@', '#').split("#");
				strTemp2 = strTemp[2].replace("$", "#").split("#");

				vo.setStrTestId(strTemp[0]);
				vo.setStrParameterId(strTemp[1]);
				vo.setStrHospitalCode(strTemp2[0]);
				
			} 
			//else {
//				vo.setStrTestId(formBean.getStrTestId());
//				vo.setStrHospitalCode(formBean.getStrHospitalCode());
//			}
			String strTestNameCmb="";
			vo.setStrSeatId(seatid);
			 bo.initializeModify(vo);
			 
				if (vo.getStrTestNameWS() != null && vo.getStrTestNameWS().size() > 0) 
				{
					strTestNameCmb = hisutil.getOptionValue(vo.getStrTestNameWS(), vo.getStrTestId(),"", false);
				}
				
				else
				{
					strTestNameCmb = "<option value='0'>Select Value</option>";
					
					
				}
				
				formBean.setStrTestNameCmb(strTestNameCmb);
				
				
				
				
			bo.modifyRecord(vo);
			
			
			bo.getStrParameterCmb(vo);
			bo.getStrSeletecParameterCmb(vo);
			bo.getStrParameterCmb(vo);
			String strSelectedParameterOptions="";
			String strAvailableParameterOptions="";
			if (vo.getWrsSelectedParameterOptions() != null && vo.getWrsSelectedParameterOptions().size() > 0) 
			{
				strSelectedParameterOptions = hisutil.getOptionValue(vo.getWrsSelectedParameterOptions(),"","", false);
			}
			
			if (vo.getWrsAvailableParameterOptions() != null && vo.getWrsAvailableParameterOptions().size() > 0) 
			{
				strAvailableParameterOptions = hisutil.getOptionValue(vo.getWrsAvailableParameterOptions(),"","", false);
			}
			
			
			
			//formBean.setArrStrSelectedTestId(vo.getArrStrSelectedTestId());
			
			formBean.setStrSelectedTestOptions(strSelectedParameterOptions);
			//formBean.setStrParameterName(vo.getStrParameterName());
			formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			formBean.setStrRemarks(vo.getStrRemarks());
			formBean.setStrIsValid(vo.getStrIsValid());
			formBean.setStrTestId(vo.getStrTestId());
			formBean.setStrAvailableTestOptions(strAvailableParameterOptions);
			
			
			
			
			
			

		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			strmsgText = "EquipmentTestMstDATA.modifyRecord(fb,request) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed","EquipmentTestMstDATA->modifyRecord()", strmsgText);
		//	formBean.setStrErrMsg("Application Error [ERROR ID : "
				//	+ eObj.getErrorID() + "],Contact System Administrator! ");

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
	public static boolean updateRecord(EquipmentTestParameterMstFB formBean,HttpServletRequest request) 
	{
		EquipmentTestParameterMstBO bo = null;
		EquipmentTestParameterMstVO vo = null;
		boolean bReturnValue = true;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strMsgText;
		String strChk = "";

		String strCombos[] = request.getParameterValues("combo");

		try {
			bo = new EquipmentTestParameterMstBO();
			vo = new EquipmentTestParameterMstVO();
			
            strChk = request.getParameter("chk");
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_PARAMETER_ID@GNUM_HOSPITAL_CODE
            
			//strTemp = strChk.replace('@', '#').split("#");
			
			//strTemp2 = strTemp[1].replace("$", "#").split("#");

			String seatid = request.getSession().getAttribute("SEATID").toString();
		
			vo.setStrSeatId(seatid);
			vo.setStrTestId(formBean.getStrTestId());
			//vo.setStrParameterId(formBean.get);
			vo.setArrStrSelectedTestId(formBean.getArrStrSelectedTestId());
			vo.setStrHospitalCode(ControllerUTIL.getUserVO(request).getHospitalCode());
			
									
			formBean.setStrChk(strChk);

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
				
				e.printStackTrace();
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
