package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.masters.bo.ComplaintEscalationMstBO;
import bmed.masters.controller.fb.ComplaintEscalationMstFB;
import bmed.masters.vo.ComplaintEscalationMstVO;

public class ComplaintEscalationMstDATA 
{
	/**
	 * to get Add page Component
	 * validateIssue
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void getAddPageComponent(ComplaintEscalationMstFB formBean,HttpServletRequest request) 
	{
		ComplaintEscalationMstBO bo = null;
		ComplaintEscalationMstVO vo = null;
		HisUtil             hisutil = null;
		String strmsgText;
		String strEmpNameCmb;
		String strUnitCmb;
		String strLevelTypeCmb;
		try 
		  {
			hisutil = new HisUtil("bmed", "ComplaintEscalationMstDATA");
			bo = new ComplaintEscalationMstBO();
			vo = new ComplaintEscalationMstVO();
						
			String    strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String          strSeatId = request.getSession().getAttribute("SEATID").toString();
			String        strComboVal = request.getParameter("comboValue").toString();
			String      arrStrCombo[] = request.getParameterValues("combo");
			String          strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			
			
			formBean.setStrHospitalCode(strHospitalCode);
			formBean.setStrSeatId(strSeatId);
			formBean.setComboValue(strComboVal);
			formBean.setCombo(arrStrCombo);
			
			formBean.setStrChk(arrStrCombo[0]+"@"+arrStrCombo[1]+"@"+strHospitalCode);
			
			vo.setStrEnggItemTypeId(arrStrCombo[0]);
			vo.setStrEnggItemSubTypeId(arrStrCombo[1]);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrIsValid("1");
			// Calling BO Method			
			bo.getAddPageComponent(vo);
			
			if (vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{		
				if (vo.getWsEnggNameCmb() != null && vo.getWsEnggNameCmb().size() > 0) 
				{
					strEmpNameCmb = hisutil.getOptionValue(vo.getWsEnggNameCmb(), ""	, "0^Select Value", false);
				} 
				else 
				{
					strEmpNameCmb = "<option value='0'>Select Value</option>";
				}
				if (vo.getWsUnitName() != null && vo.getWsUnitName().size() > 0) 
				{
					strUnitCmb = hisutil.getOptionValue(vo.getWsUnitName(), ""	, "0^Select Value", false);
					
				} 
				else 
				{
					strUnitCmb = "<option value='0'>Select Value</option>";
				}
				if (vo.getWsLevelTypeCmb()!= null && vo.getWsLevelTypeCmb().size() > 0) 
				{
					strLevelTypeCmb = hisutil.getOptionValue(vo.getWsLevelTypeCmb(), ""	, "0^Select Value", false);
				} 
				else 
				{
					strLevelTypeCmb = "<option value='0'>Select Value</option>";
				}
				
				    formBean.setStrLevelTypeCmb(strLevelTypeCmb);
                    formBean.setStrEnggItemTypeCmb(vo.getStrEnggItemTypeCmb());
                    formBean.setStrEnggItemSubTypeCmb(vo.getStrEnggItemSubTypeCmb());
				    formBean.setStrEmpNameCmb(strEmpNameCmb);
				    formBean.setStrUnitIdCmb(strUnitCmb);
					formBean.setStrCtDate(strCtDate);
					
					
		    }
		   }
			catch (Exception e) 
			{
            e.printStackTrace();
			strmsgText = "ComplaintEscalationMstDATA.getAddPageComponent(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintEscalationMstDATA->initialAdd()", strmsgText);
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
	 * This function is used to display employee Information through Ajax request: 
	 * @param formBean
	 */
	public static void getEmpInfo(ComplaintEscalationMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) 
	{
		ComplaintEscalationMstVO vo=null;
		ComplaintEscalationMstBO bo= null;
		
		String strHosCode;
		String strSeatid;
		String strEmpNo;
		String strEmpInfo;
		
		try 
		{
		
			        vo = new ComplaintEscalationMstVO();
			        bo = new ComplaintEscalationMstBO();
		
			strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			 strSeatid = request.getSession().getAttribute("SEATID").toString();
			  strEmpNo = (String) request.getParameter("empNo");
			
			vo.setStrEmpNo(strEmpNo);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			
			// Calling BO Method	
			bo.getEmpInfo(vo);			
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			if(vo.getStrEmpInfo()==null)
			{	
				strEmpInfo = "---@@---";
			}
			else
			{
				strEmpInfo = vo.getStrEmpInfo();
			}	
				  response.setHeader("Cache-Control", "no-cache");
				  response.getWriter().print(strEmpInfo);
					
			
			
									
		} catch (Exception e) {
		  //e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ComplaintEscalationMstDATA->getEmpInfo()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			
		}
	}
	
	/**
	 * to insert the data into database
	 * 
	 * @param formBean
	 * @param request
	 */
	public static void insertRecord(ComplaintEscalationMstFB formBean,
			HttpServletRequest request) 
	{
		ComplaintEscalationMstBO bo = null;
		ComplaintEscalationMstVO vo = null;
		String strmsgText,strChk;
		String temp[] = null;

		try 
		{

			        bo = new ComplaintEscalationMstBO();
			        vo = new ComplaintEscalationMstVO();
 				strChk = formBean.getStrChk();
				strChk = strChk.replace("$", "@");
				  temp = strChk.split("@");
							
				formBean.setStrEnggItemTypeId(temp[0]);
				formBean.setStrEnggItemSubTypeId(temp[1]);
				formBean.setStrChk(strChk);
			    formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString()); 
			    formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			 
			    
			    vo.setStrEmpNameId(formBean.getStrEmpNameId());	
				vo.setStrEnggItemTypeId(temp[0]);
				vo.setStrEnggItemSubTypeId(temp[1]);
				vo.setStrLevelType(formBean.getStrLevelType());
				vo.setStrPeriod(formBean.getStrPeriod());
				vo.setStrUnitId(formBean.getStrUnitId());
				
				vo.setStrRemarks(formBean.getStrRemarks());
				vo.setStrHospitalCode(temp[2]);	//from session
				vo.setStrSeatId(formBean.getStrSeatId());	//from session
            
		//	vo = (ComplaintEscalationMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ComplaintEscalationMstVO", formBean);

			// Calling BO Method
			bo.insertRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			if (vo.getBExistStatus() == false) 
			{
				formBean.setStrWarning("Emp No. already exist!!!");
			}
			else 
			{
				formBean.setStrMsgString("Data Saved Successfully");
				formBean.setStrChk(vo.getStrChk());
				
			}
			

		} 
		catch (Exception e) 
		{
           e.printStackTrace();
			strmsgText = "ComplaintEscalationMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintEscalationMstDATA->insertRecord()", strmsgText);
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
	
	public static void modify(HttpServletRequest request, ComplaintEscalationMstFB formBean) 
	{
		ComplaintEscalationMstBO bo = null;
		ComplaintEscalationMstVO vo = null;
		String temp[] = null;
		String temp1[] = null;
		HisUtil hisutil;
		String strChk;
		String strUnitCmb,strEmpInfo;
		String strCtDate;
		String strCombo[] = null; 
				
		try
		{
			        vo = new ComplaintEscalationMstVO();
			        bo = new ComplaintEscalationMstBO();
			   hisutil = new HisUtil("bmed","ComplaintEscalationMstDATA");
			 strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			  strCombo = request.getParameterValues("combo");
			
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setStrIsValid(strCombo[2]);
			
			if (request.getParameter("chk") != null) 
			{   							
				// chk--->>> A.HEMNUM_ENGG_ITEM_TYPE_ID||'@'||A.HEMNUM_ENGG_ITEM_SUB_TYPE_ID
				// ||'@'||A.HEMSTR_EMP_ID||'@'||HEMNUM_LEVELTYPE_ID||'@'||A.GNUM_HOSPITAL_CODE
				strChk = request.getParameter("chk");
				strChk = strChk.replace("$", "@");
				  temp = strChk.split("@");
				
				vo.setStrEnggItemTypeId(temp[0]);
				vo.setStrEnggItemSubTypeId(temp[1]);
				vo.setStrEmpNo(temp[2]);
				vo.setStrLevelType(temp[3]);
				vo.setStrHospitalCode(temp[4]);
				formBean.setStrChk(strChk);
			} 
			else 
			{
				strChk = formBean.getStrChk();
				strChk = strChk.replace("$", "@");
				  temp = strChk.split("@");
				
				vo.setStrEnggItemTypeId(temp[0]);
				vo.setStrEnggItemSubTypeId(temp[1]);
				vo.setStrEmpNo(temp[2]);
				vo.setStrLevelType(temp[3]);
				vo.setStrHospitalCode(temp[4]);
				
				formBean.setStrChk(strChk);
			}
			 // Call BO Method Here
			 bo.modify(vo);
			 
			 if (vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				else
				{				
					
					
					if (vo.getWsUnitName() != null && vo.getWsUnitName().size() > 0) 
					{
						strUnitCmb = hisutil.getOptionValue(vo.getWsUnitName(), vo.getStrUnitId(), "0^Select Value", false);
					} 
					else 
					{
						strUnitCmb = "<option value='0'>Select Value</option>";
					}
					if(vo.getStrEmpInfo()==null)
					{	
						strEmpInfo = "---@@---";
					}
					else
					{
						strEmpInfo = vo.getStrEmpInfo();
					}	
					    temp1 = strEmpInfo.split("@@");			
					    formBean.setStrLevelType(vo.getStrLevelTypeCmb());
					    formBean.setStrPeriod(vo.getStrPeriod());
	                    formBean.setStrEnggItemTypeCmb(vo.getStrEnggItemTypeCmb());
	                    formBean.setStrEnggItemSubTypeCmb(vo.getStrEnggItemSubTypeCmb());
					    formBean.setStrEmpNameCmb(vo.getStrEmpNameCmb());
					    formBean.setStrEmailId(temp1[1]);
					    formBean.setStrContactNo(temp1[0]);
					    formBean.setStrUnitIdCmb(strUnitCmb);
						formBean.setStrCtDate(strCtDate);
						formBean.setStrRemarks(vo.getStrRemarks());
			    }
			 
			
		}
		
	    catch (Exception e) 
	    {
		
		String strmsgText = e.getMessage();
		HisException eObj = new HisException("bmed",
				"ComplaintEscalationMstDATA->modify()", strmsgText);
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
	
	public static boolean updateRecord(ComplaintEscalationMstFB formBean,HttpServletRequest request)
	{
		ComplaintEscalationMstBO bo = null;
		ComplaintEscalationMstVO vo = null;
		String strmsgText = "";
		boolean retValue = true;
		String temp[] = null;
		String strChk;
		
		try 
		{
			                bo = new ComplaintEscalationMstBO();
			                vo = new ComplaintEscalationMstVO();
			 String strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			 String  strSeatid = request.getSession().getAttribute("SEATID").toString();

						strChk = formBean.getStrChk();
						strChk = strChk.replace("$", "@");
						  temp = strChk.split("@");
			
			formBean.setStrEnggItemTypeId(temp[0]);
			formBean.setStrEnggItemSubTypeId(temp[1]);
			formBean.setStrEmpNo(temp[2]);
			formBean.setStrLevelType(temp[3]);
			formBean.setStrHospitalCode(temp[4]);
			
			formBean.setStrChk(strChk);
									
			formBean.setStrHospitalCode(strHosCode);
			formBean.setStrSeatId(strSeatid);			
			
			vo = (ComplaintEscalationMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ComplaintEscalationMstVO", formBean);
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

			strmsgText = "ComplaintEscalationMstDATA.updateRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintEscalationMstDATA->updateRecord()", strmsgText);
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
