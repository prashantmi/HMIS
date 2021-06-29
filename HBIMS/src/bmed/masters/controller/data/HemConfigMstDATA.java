package bmed.masters.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import bmed.masters.bo.HemConfigMstBO;
import bmed.masters.controller.fb.HemConfigMstFB;
import bmed.masters.vo.HemConfigMstVO;

public class HemConfigMstDATA {

	/**
	 * to get the data for Hem Configuration.
	 * 
	 * @param formBean_p the HemConfigMstFB
	 * @param request_p the HttpServletRequest
	 */
	public static void getRecord(HemConfigMstFB formBean_p, HttpServletRequest request_p) 
	{
		
		HemConfigMstBO bo = null;
		HemConfigMstVO vo = null;
		String strMsgText = "";

		UserVO userVO= ControllerUTIL.getUserVO(request_p);
		
		try {
			bo = new HemConfigMstBO();
			vo = new HemConfigMstVO();
			
			vo.setStrHospitalCode(userVO.getHospitalCode());
			bo.getRecord(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			formBean_p.setStrShowMcDetailsUpto(vo.getStrShowMcDetailsUpto());
			formBean_p.setStrShowWarrantyDetailsUpto(vo.getStrShowWarrantyDetailsUpto());
			formBean_p.setStrFinancialStartDate(vo.getStrFinancialStartDate());
			formBean_p.setStrFinancialEndDate(vo.getStrFinancialEndDate());
			formBean_p.setStrItemUnderAmcOrWarranty(vo.getStrItemUnderAmcOrWarranty());
			formBean_p.setStrFolderName(vo.getStrFolderName());
			//System.out.println("vo.setStrFolderName::::::"+vo.getStrFolderName());
		} 
		catch (Exception e) 
		{
			strMsgText = "HemConfigMstDATA.getRecord(fb,request_p) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","HemConfigMstDATA->getRecord()", strMsgText);
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
	public static boolean saveRecord(HemConfigMstFB formBean_p,HttpServletRequest request_p) 
	{
		HemConfigMstBO bo = null;
		HemConfigMstVO vo = null;
		boolean bReturnValue = true;
		String strMsgText;
		UserVO userVO= ControllerUTIL.getUserVO(request_p);
		
		try {
			bo = new HemConfigMstBO();
			vo = new HemConfigMstVO();
			
 			
			vo.setStrShowMcDetailsUpto(formBean_p.getStrShowMcDetailsUpto());
			vo.setStrShowWarrantyDetailsUpto(formBean_p.getStrShowWarrantyDetailsUpto());
			vo.setStrFinancialStartDate(formBean_p.getStrFinancialStartDate());
			vo.setStrFinancialEndDate(formBean_p.getStrFinancialEndDate());
			vo.setStrItemUnderAmcOrWarranty(formBean_p.getStrItemUnderAmcOrWarranty());
			
			//Added by Adil Wasi
			vo.setStrFolderName(formBean_p.getStrFolderName());
			
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
				e.printStackTrace();
			bReturnValue = false;
			strMsgText = "HemConfigMstDATA.saveRecord(vo) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","HemConfigMstDATA->saveRecord()", strMsgText);
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
