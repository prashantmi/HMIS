package mms.masters.controller.data;


import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.masters.bo.SmsMobileSetupMstBO;
import mms.masters.controller.fb.SmsMobileSetupMstFB;
import mms.masters.vo.SmsMobileSetupMstVO;

public class SmsMobileSetupMstDATA {

	/**
	 * to get the data for Hem Configuration.
	 * 
	 * @param formBean_p the SmsMobileSetupMstFB
	 * @param request_p the HttpServletRequest
	 */
	public static void getRecord(SmsMobileSetupMstFB formBean_p, HttpServletRequest request_p) 
	{
		
		SmsMobileSetupMstBO bo = null;
		SmsMobileSetupMstVO vo = null;
		String strMsgText = "";

		UserVO userVO= ControllerUTIL.getUserVO(request_p);
		HisUtil hisutil = null;
		String strProcessValues;
		try {
			bo = new SmsMobileSetupMstBO();
			vo = new SmsMobileSetupMstVO();
			hisutil = new HisUtil("mms", "EmployeeDetailMstDATA");
			vo.setStrHospitalCode(userVO.getHospitalCode());
			bo.getRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
						
			if (vo.getWrsData() != null && vo.getWrsData().size() > 0) 
			{
					strProcessValues = hisutil.getOptionValue(vo.getWrsData(),formBean_p.getStrReqTypeId(),"0^Select Value", false);
			}
			else
			{
				strProcessValues = "<option value='0'>Select Value</option>";
			}
			
			
			
			formBean_p.setStrProcessName(strProcessValues);
			
			//System.out.println("vo.setStrFolderName::::::"+vo.getStrFolderName());
		} 
		catch (Exception e) 
		{
			strMsgText = "SmsMobileSetupMstDATA.getRecord(fb,request_p) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","SmsMobileSetupMstDATA->getRecord()", strMsgText);
			formBean_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			bo = null;
			vo = null;
		}

	}

	
	/**
	 * to get the data for Hem Configuration.
	 * 
	 * @param formBean_p the SmsMobileSetupMstFB
	 * @param request_p the HttpServletRequest
	 */
	public static void getMobileNos(SmsMobileSetupMstFB formBean_p, HttpServletRequest request_p,HttpServletResponse response) 
	{
		
		SmsMobileSetupMstBO bo = null;
		SmsMobileSetupMstVO vo = null;
		String strMsgText = "";

		UserVO userVO= ControllerUTIL.getUserVO(request_p);
		HisUtil hisutil = null;
		String strMobileNos="";
		try {
			bo = new SmsMobileSetupMstBO();
			vo = new SmsMobileSetupMstVO();
			hisutil = new HisUtil("mms", "EmployeeDetailMstDATA");
			vo.setStrHospitalCode(userVO.getHospitalCode());
			vo.setStrReqTypeId(formBean_p.getStrReqTypeId());
			
			bo.getMobileNos(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
						
			if (vo.getPhoneNosWrs() != null && vo.getPhoneNosWrs().size() > 0) 
			{
				if(vo.getPhoneNosWrs().next())
					strMobileNos = vo.getPhoneNosWrs().getString("SSTSTR_MOBILE_NO");
					
			}
			else
			{
				strMobileNos = "";
			}
			
			
			formBean_p.setStrPhoneNo((strMobileNos==null||strMobileNos.equals(""))?"":strMobileNos.trim());
			
			
			//System.out.println("vo.setStrFolderName::::::"+vo.getStrFolderName());
		} 
		catch (Exception e) 
		{
			strMsgText = "SmsMobileSetupMstDATA.getRecord(fb,request_p) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","SmsMobileSetupMstDATA->getRecord()", strMsgText);
			formBean_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			bo = null;
			vo = null;
		}

	}
	
	
	/**
	 * to save the record
	 * 
	 * @param formBean_p the form bean
	 * @param request_p the HttpServletRequest
	 * 
	 * @return true, if update record
	 */
	public static boolean saveRecord(SmsMobileSetupMstFB formBean_p,HttpServletRequest request_p) 
	{
		SmsMobileSetupMstBO bo = null;
		SmsMobileSetupMstVO vo = null;
		boolean bReturnValue = true;
		String strMsgText;
		UserVO userVO= ControllerUTIL.getUserVO(request_p);
		
		try {
			bo = new SmsMobileSetupMstBO();
			vo = new SmsMobileSetupMstVO();
			
 			vo.setStrReqTypeId(formBean_p.getStrReqTypeId());
 			vo.setStrPhoneNo(formBean_p.getStrPhoneNo());
			vo.setStrHospitalCode(userVO.getHospitalCode());
			
			bo.saveRecord(vo);

				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				
				formBean_p.setStrNormalMsg("Record Modified Successfully");
				
			} 
			catch (Exception e) 
			{
			bReturnValue = false;
			strMsgText = "SmsMobileSetupMstDATA.saveRecord(vo) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","SmsMobileSetupMstDATA->saveRecord()", strMsgText);
			formBean_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");

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
