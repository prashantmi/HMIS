package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.masters.bo.TaskMstBO;
import bmed.masters.controller.fb.TaskMstFB;
import bmed.masters.vo.TaskMstVO;


/**
 * @author:-	Vivek Aggarwal   
 * Creation Date:- 11-Jan-2011 
 * Modifying Date:- 24-Jan-2011
 * Used For:-	
 * Team Lead By:-	
 *  Module:- BMED(HIS Project)
 * 
 */
public class TaskMstDATA 
{

	/**
	 * To get Engineering Item Type Combo populated.
	 * 
	 * @param formBean the Task Master FormBean
	 * @param request the HttpServletRequest
	 */
	public static void initializeAdd(TaskMstFB formBean, HttpServletRequest request) 
	{
		
		String strMsgText;
		HisUtil hisutil = null;
		TaskMstBO bo = null;
		TaskMstVO vo = null;
		
		
		String strEngineeringItemTypeCmb=null;
		String arr_strListPageCombos[]=null;
		String strEngineeringItemSubTypeCmb=null;

		try 
		{
			hisutil = new HisUtil("bmed", "TaskMstDATA");
			
			vo = new TaskMstVO();
			bo = new TaskMstBO();
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
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

			vo.setStrHospitalCode(hosCode);
			
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
			
			formBean.setStrEngineeringItemTypeCmb(strEngineeringItemTypeCmb); 
			formBean.setStrEngineeringItemSubCmb(strEngineeringItemSubTypeCmb); 

		} 
		catch (Exception e) 
		{
			strMsgText = "TaskMstDATA.getEnggItemTypeCmbValues(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed","TaskMstDATA.getEnggItemTypeCmbValues()", strMsgText);
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
	 * To get Engineering Item Sub Type Combo populated.
	 * 
	 * @param formBean the Task Master form bean
	 * @param request the HttpServletRequest
	 * @param response the HttpServletResponse
	 */
	public static void getEnggItemSubTypeCmb(TaskMstFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		String strMsgText = "";
		HisUtil hisutil = null;
		TaskMstBO bo = null;
		TaskMstVO vo = null;
		
		String strEngineeringItemTypeId = "";
		String strEngineeringItemSubTypeWS = "";
		
		try 
		{
			hisutil = new HisUtil("bmed", "TaskMstDATA");
			
			vo = new TaskMstVO();
			bo = new TaskMstBO();
			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
	
			strEngineeringItemTypeId = request.getParameter("enggItemType");
			vo.setStrHospitalCode(hosCode);
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
			
			   response.setHeader("Cache-Control", "no-cache");
			   response.getWriter().print(strEngineeringItemSubTypeWS);
			
		} 
		catch (Exception e) 
		{
			strMsgText = "TaskMstDATA.getEnggItemSubTypeCmb(vo) --> " + e.getMessage();
		HisException eObj = new HisException("bmed", "TaskMstDATA.getEnggItemSubTypeCmb()", strMsgText);
		
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
				new HisException("bmed","TaskMstDATA.getEnggItemSubTypeCmb()", strMsgText);
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
	public static void insertRecord(TaskMstFB formBean,	HttpServletRequest request) 
	{
		TaskMstBO bo = null;
		TaskMstVO vo = null;
		String strmsgText = "";
		try {
			bo = new TaskMstBO();
			vo = new TaskMstVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);

			vo.setStrTaskName(formBean.getStrTaskName());	
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

			if (vo.getBExistStatus() == false) {
				formBean.setStrWarningMsg("Task Name already exist !");
			}
			else {
				formBean.setStrNormalMsg("Record Saved Successfully!");
			}

		}
		catch (Exception e) 
		{

			//e.printStackTrace();
			
			strmsgText = "TaskMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"TaskMstDATA->insertRecord()", strmsgText);
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
	public static void modifyRecord(TaskMstFB formBean,	HttpServletRequest request) 
	{
		TaskMstBO bo = null;
		TaskMstVO vo = null;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strmsgText = "";
		String strChk = "";
		String seatid = null;

		
		try {
			bo = new TaskMstBO();
			vo = new TaskMstVO();
            String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			
			seatid=request.getSession().getAttribute("SEATID").toString();
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_TASK_ID@GNUM_HOSPITAL_CODE

			if(strChk!=null && !strChk.trim().equals("")) {
				
				strTemp = strChk.replace('@', '#').split("#");
				strTemp2 = strTemp[1].replace("$", "#").split("#");

				vo.setStrTaskId(strTemp[0]);
				vo.setStrHospitalCode(strTemp2[0]);
				
			} else {
				vo.setStrTaskId(formBean.getStrTaskId());
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
			}
			
			vo.setStrSeatId(seatid);

			vo.setStrEngineeringItemTypeId(strCombos[0]);
			vo.setStrEngineeringItemSubTypeId(strCombos[1]);
			
			
			

			bo.modifyRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			formBean.setStrEngineeringItemTypeName(vo.getStrEngineeringItemTypeName());
			formBean.setStrEngineeringItemSubTypeName(vo.getStrEngineeringItemSubTypeName());
			formBean.setStrTaskName(vo.getStrTaskName());
			formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			formBean.setStrRemarks(vo.getStrRemarks());
			formBean.setStrIsValid(vo.getStrIsValid());
			formBean.setStrEngineeringItemTypeId(vo.getStrEngineeringItemTypeId());
			formBean.setStrEngineeringItemSubTypeId(vo.getStrEngineeringItemSubTypeId());

		} 
		catch (Exception e) 
		{
			strmsgText = "TaskMstDATA.modifyRecord(fb,request) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed","TaskMstDATA->modifyRecord()", strmsgText);
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
	public static boolean updateRecord(TaskMstFB formBean,HttpServletRequest request) 
	{
		TaskMstBO bo = null;
		TaskMstVO vo = null;
		boolean bReturnValue = true;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strMsgText;
		String strChk = "";

		String strCombos[] = request.getParameterValues("combo");

		try {
			bo = new TaskMstBO();
			vo = new TaskMstVO();
			
            strChk = request.getParameter("chk");
			
			//chk value::		10@108$6
            // Primary Key::	HEMNUM_TASK_ID@GNUM_HOSPITAL_CODE
            
			strTemp = strChk.replace('@', '#').split("#");
			
			strTemp2 = strTemp[1].replace("$", "#").split("#");

			String seatid = request.getSession().getAttribute("SEATID").toString();

			vo.setStrTaskId(strTemp[0]);
			vo.setStrHospitalCode(strTemp2[0]);
			vo.setStrSeatId(seatid);

			vo.setStrEngineeringItemTypeId(strCombos[0]);
			vo.setStrEngineeringItemSubTypeId(strCombos[1]);
						
			formBean.setStrChk(strChk);

			vo.setStrTaskName(formBean.getStrTaskName());
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
					formBean.setStrWarningMsg("Task Name already exist !");
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
			strMsgText = "TaskMstDATA.updateRecord(vo) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","TaskMstDATA->updateRecord()", strMsgText);
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
