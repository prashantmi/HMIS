package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import bmed.masters.bo.ServiceEngineerMstBO;
import bmed.masters.controller.fb.ServiceEngineerMstFB;
import bmed.masters.controller.hlp.ServiceEngineerMstHLP;
import bmed.masters.vo.ServiceEngineerMstVO;
/**
 * @author   Amit kr
 * Creation Date:- 17-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */

public class ServiceEngineerMstDATA 
{
	/**
	 * to display the Item Category combo.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void getAddPageComponent(ServiceEngineerMstFB formBean,HttpServletRequest request) 
	{
		ServiceEngineerMstBO   bo = null;
		ServiceEngineerMstVO   vo = null;
		HisUtil           hisutil = null;
		String      arrStrCombo[] = null;
		
		String strEnggNameCmb;
		String strHospitalCode,strSeatId,strComboVal,strCtDate,strmsgText;
		
		
		try 
		{
			         hisutil = new HisUtil("bmed", "ServiceEngineerMstDATA");
			              bo = new ServiceEngineerMstBO();
			              vo = new ServiceEngineerMstVO();
						
			 strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			       strSeatId = request.getSession().getAttribute("SEATID").toString();
			     strComboVal = request.getParameter("comboValue").toString();
			     arrStrCombo = request.getParameterValues("combo");
			       strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			
			
			formBean.setStrHospitalCode(strHospitalCode);
			formBean.setStrSeatId(strSeatId);
			formBean.setComboValue(strComboVal);
			formBean.setCombo(arrStrCombo);
			
			formBean.setStrPkey(arrStrCombo[0]+"@"+arrStrCombo[1]+"@"+arrStrCombo[2]);
			
			vo.setStrEnggItemTypeId(arrStrCombo[0]);
			vo.setStrEnggItemSubTypeId(arrStrCombo[1]);
			vo.setStrHospitalCode(strHospitalCode);
			
			
			// Calling BO method			
			bo.getAddPageComponent(vo);
			
			if (vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{				
				if (vo.getWsEnggNameCmb() != null && vo.getWsEnggNameCmb().size() > 0) 
				{
					strEnggNameCmb = hisutil.getOptionValue(vo.getWsEnggNameCmb(), ""	, "0^Select Value", false);
				} 
				else 
				{
					strEnggNameCmb = "<option value='0'>Select Value</option>";
				}
				
				    formBean.setStrServiceEnggNameCmb(strEnggNameCmb);
					formBean.setStrEnggItemTypeCmb(vo.getStrEnggItemTypeCmb());
					formBean.setStrEnggItemSubTypeCmb(vo.getStrEnggItemSubTypeCmb());
					formBean.setStrCtDate(strCtDate);
					
					
		    }
		   }
			catch (Exception e) 
			{
            e.printStackTrace();
			strmsgText = "ServiceEngineerMstDATA.getAddPageComponent(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ServiceEngineerMstDATA->initialAdd()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			hisutil = null;
		}

	}
	
	/**
	 * to insert the data
	 * 
	 * @param formBean
	 * @param request
	 */
	public static void insertRecord(ServiceEngineerMstFB formBean,
			HttpServletRequest request) 
	{
		ServiceEngineerMstBO bo = null;
		ServiceEngineerMstVO vo = null;
		String strmsgText,strComboVal;
		try 
		{

			          bo = new ServiceEngineerMstBO();
			          vo = new ServiceEngineerMstVO();
			strComboVal  = request.getParameter("comboValue").toString();
						
			formBean.setComboValue(strComboVal);
						
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());          
            
			vo = (ServiceEngineerMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ServiceEngineerMstVO", formBean);

		    // Call BO Method
			bo.insertRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			else 
			{
				formBean.setStrMsgString("Data Saved Successfully");
				formBean.setStrPkey(vo.getStrPkey());
				
			}
			

		} 
		catch (Exception e) 
		{
            //e.printStackTrace();
			strmsgText = "ServiceEngineerMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ServiceEngineerMstDATA->insertRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

	}
	
	/**
	 * Modify.
	 * 
	 * @param request the request
	 * @param formBean the form bean
	 */
	public static void modify(HttpServletRequest request, ServiceEngineerMstFB formBean) 
	{
		ServiceEngineerMstBO bo = null;
		ServiceEngineerMstVO vo = null;
		String        arrTemp[] = null;
		String    arrStrCombo[] = null;
		HisUtil hisutil;
		String strChk;
		String strEnggNameCmb,strCtDate;
				
		try
		{
					         vo = new ServiceEngineerMstVO();
					         bo = new ServiceEngineerMstBO();
					    hisutil = new HisUtil("bmed","ServiceEngineerMstDATA");
				      strCtDate = hisutil.getASDate("dd-MMM-yyyy");
					arrStrCombo = request.getParameterValues("combo");
			
			formBean.setStrIsValid(arrStrCombo[2]);
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			
			if (request.getParameter("chk") != null) 
			{   							
				// chk--->>> empId@EnggItemType@EnggItemSubType@HospCode
				 strChk = request.getParameter("chk");
				 strChk = strChk.replace("$", "@");
				arrTemp = strChk.split("@");
								
				vo.setStrEmpId(arrTemp[0]);
				vo.setStrEnggItemTypeId(arrTemp[1]);
				vo.setStrEnggItemSubTypeId(arrTemp[2]);
				
				
				formBean.setStrChk(strChk);
			} 
			else 
			{
				 strChk = formBean.getStrChk();
				 strChk = strChk.replace("$", "@");
				arrTemp = strChk.split("@");
				
				vo.setStrEmpId(arrTemp[0]);
				vo.setStrEnggItemTypeId(arrTemp[1]);
				vo.setStrEnggItemSubTypeId(arrTemp[2]);
				formBean.setStrChk(strChk);
			}
		
			vo.setStrIsValid(formBean.getStrIsValid());
			 // Call BO Method Here
			 bo.modify(vo);
			 if (vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				else
				{				
					if (vo.getWsEnggNameCmb() != null && vo.getWsEnggNameCmb().size() > 0) 
					{
						strEnggNameCmb = hisutil.getOptionValue(vo.getWsEnggNameCmb(), arrTemp[0], "0^Select Value", false);
					} 
					else 
					{
						strEnggNameCmb = "<option value='0'>Select Value</option>";
					}
					 formBean.setStrServiceEnggNameList(ServiceEngineerMstHLP.getServiceEnggNameHlp(vo.getWsEnggNameHlp()));
					 formBean.setStrServiceEnggNameCmb(strEnggNameCmb);
					 formBean.setStrEnggItemTypeCmb(vo.getStrEnggItemTypeCmb());
					 formBean.setStrEnggItemSubTypeCmb(vo.getStrEnggItemSubTypeCmb());
					 formBean.setStrCtDate(strCtDate);
					 formBean.setStrIsValid(vo.getStrIsValid());
					 formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
					 formBean.setStrRemarks(vo.getStrRemarks());
			    }
			 
			
		}
		
	    catch (Exception e) 
	    {
		//e.printStackTrace();
		String strmsgText = e.getMessage();
		HisException eObj = new HisException("bmed",
				"ServiceEngineerMstDATA->modify()", strmsgText);
		formBean.setStrErr("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "], Contact System Administrator! ");
		eObj = null;
	} finally {
		bo = null;
		vo = null;
		
	}
  }
	
	/**
	 * to update the record.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 * 
	 * @return true, if update record
	 */
	public static boolean updateRecord(ServiceEngineerMstFB formBean,HttpServletRequest request)
	{
		ServiceEngineerMstBO bo = null;
		ServiceEngineerMstVO vo = null;
		boolean        retValue = true;
		String           temp[] = null;
		String strmsgText,strHosCode,strSeatid;
		String strChk;
		
		try {
			          bo = new ServiceEngineerMstBO();
			          vo = new ServiceEngineerMstVO();
			  strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			   strSeatid = request.getSession().getAttribute("SEATID").toString();

			if (request.getParameter("chk") != null) 
			{   							
				// chk--->>> empId@EnggItemType@EnggItemSubType@HospCode
				strChk = request.getParameter("chk");
				strChk = strChk.replace("$", "@");
				  temp = strChk.split("@");
				
				
				formBean.setStrEmpId(temp[0]);
				formBean.setStrEnggItemTypeId(temp[1]);
				formBean.setStrEnggItemSubTypeId(temp[2]);
				formBean.setStrHospitalCode(temp[4]);
				
				formBean.setStrChk(strChk);
			} 
			else 
			{
				strChk = formBean.getStrChk();
				strChk = strChk.replace("$", "@");
				  temp = strChk.split("@");
				
				formBean.setStrEmpId(temp[0]);
				formBean.setStrEnggItemTypeId(temp[1]);
				formBean.setStrEnggItemSubTypeId(temp[2]);
				formBean.setStrHospitalCode(temp[4]);
				
				formBean.setStrChk(strChk);
			}
			formBean.setStrHospitalCode(strHosCode);
			formBean.setStrSeatId(strSeatid);			
			
			vo = (ServiceEngineerMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ServiceEngineerMstVO", formBean);
			// Calling BO Method
			bo.updateRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			else 
			{
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				} 
				else 
				{
					formBean.setStrMsg("Record Modify Successfully");
				}
			}

		} catch (Exception e) {

			strmsgText = "ServiceEngineerMstDATA.updateRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ServiceEngineerMstDATA->updateRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
			retValue = false;

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
		
		return retValue;
	}
		



}
