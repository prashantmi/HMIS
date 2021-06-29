package bmed.masters.controller.data;
import hisglobal.TransferObjectFactory;
import hisglobal.hisconfig.Config;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import bmed.masters.bo.ExpertiseMstBO;
import bmed.masters.controller.fb.ExpertiseMstFB;
import bmed.masters.vo.ExpertiseMstVO;

/**
 * @author Arun VR  
 * Creation Date:- 20-jan-2011 
 * Modifying Date:- 
 * Used For:-Expertise Master
 * Team Lead By:- Amit Kumar
 * Module:- BMED(HIS Project)
 * 
 */
public class ExpertiseMstDATA {


	/**
	 * to display the components for expertise master
	 * 
	 * @param request the form bean
	 * @param formBean the request
	 */

	public static void getAddPageComponent(HttpServletRequest request,
			ExpertiseMstFB formBean) {

		ExpertiseMstVO vo = null;
		ExpertiseMstBO bo = null;
		String strmsgText,hosCode,ctDate;
		HisUtil hisutil = null;


		try
		{
			      bo = new ExpertiseMstBO();
			      vo = new ExpertiseMstVO();
			 hisutil = new HisUtil("bmed", "ExpertiseMstDATA");
			 hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			  ctDate = hisutil.getASDate("dd-MMM-yyyy");

			vo.setStrHospitalCode(hosCode);
			// Calling BO Method					
			bo.initAdd(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}

			hisutil = new HisUtil("bmed", "ExpertiseMstBO");
			formBean.setStrCtDate(ctDate); 

		} catch (Exception e) {

//			e.printStackTrace();

			strmsgText = "ExpertiseMstDATA.getAddPageComponent(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ExpertiseMstDATA->getAddPageComponent()", strmsgText);
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
	 * to insert the data.
	 * @param request the request
	 * @param formbean the formbean
	 */

	public static void InsertRecord(ExpertiseMstFB formbean,
			HttpServletRequest request) {

		String strmsgText = "";
		ExpertiseMstVO vo = null;
		ExpertiseMstBO bo = null;
		String strIsValid;

		try {

			        bo = new ExpertiseMstBO();
			        vo = new ExpertiseMstVO();
			strIsValid = formbean.getCombo()[0];
			formbean.setStrIsValid(strIsValid);


			vo.setStrIsValid(formbean.getStrIsValid());
			//formbean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formbean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formbean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());          

			       vo = (ExpertiseMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ExpertiseMstVO", formbean);


			bo.insertRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			if (vo.getBExistStatus()== true) 
			{
				formbean.setStrWarning("Expertise Name already exist !!!");
			}
			else 
			{
				formbean.setStrMsg("Record saved successfully!");

			}


		}

		catch (Exception e) {

			strmsgText = "ExpertiseMaster.ExpertiseMstDATA.insertRecord(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("store",
					"ExpertiseMstDATA->insertRecord()", strmsgText);
			formbean.setStrErr("Application Error [ERROR ID : "
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
	public static void modify(HttpServletRequest request, ExpertiseMstFB formBean) 
	{
		ExpertiseMstBO bo = null;
		ExpertiseMstVO vo = null;
		String  arrTemp[] = null;

		String     strChk = "duplicate";

		try
		{
			          vo = new ExpertiseMstVO();
			          bo = new ExpertiseMstBO();
			
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setStrIsValid(formBean.getCombo()[0]);

			if (request.getParameter("chk") != null) 
			{            

				  strChk = request.getParameter("chk");
				  strChk = strChk.replace("$", "@");
				 arrTemp = strChk.split("@");
				vo.setStrHospitalCode(arrTemp[0]);
				vo.setStrSkillId(arrTemp[1]);
				formBean.setStrChk(strChk);
			} 
			else if(strChk.equals("duplicate")){
				
				 strChk = formBean.getStrChk();
				 strChk = strChk.replace("$", "@");
				arrTemp = strChk.split("@");
				vo.setStrHospitalCode(arrTemp[0]);
				vo.setStrSkillId(arrTemp[1]);
				
				
			}
			// Calling BO Method
			bo.modify(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				formBean.setStrExpertiseName(vo.getStrExpertiseName());
				formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
				formBean.setStrRemarks(vo.getStrRemarks());
			} 


		} catch (Exception e) {
			//e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed",
					"ExpertiseMstDATA->modify()", strmsgText);
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
	 * @param formBean the form bean
	 * @param request the request
	 * 
	 * @return true, if update record
	 */
	public static boolean updateRecord(ExpertiseMstFB formBean,HttpServletRequest request)
	{
		ExpertiseMstBO bo;
		ExpertiseMstVO vo;
		String strChk,strHosCode,strSeatid;
		String strmsgText = "";
		boolean  retValue = true;
		String  arrTemp[] = null;

		try 
		{

			         bo = new ExpertiseMstBO();
			         vo = new ExpertiseMstVO();
			 //strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			  strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			  strSeatid = request.getSession().getAttribute("SEATID").toString();

			     strChk = formBean.getStrChk();
			     strChk = strChk.replace("$", "@");
		  	    arrTemp = strChk.split("@");
	
		  	formBean.setStrHospitalCode(arrTemp[0]);
			formBean.setStrSkillId(arrTemp[1]);
			formBean.setStrChk(strChk);

			formBean.setStrHospitalCode(strHosCode);
			formBean.setStrSeatId(strSeatid);			


			        vo = (ExpertiseMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ExpertiseMstVO", formBean);
            // Calling BO method
			bo.updateRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			else 
			{

				if (vo.getBExistStatus()== true) 
				{
					formBean.setStrWarning("Expertise Name already exist !!!!");
					retValue=false;
				}
				else 
				{
					formBean.setStrMsg("Record Modify Successfully");
				}
			}

		} catch (Exception e) 
		{
			//e.printStackTrace();

			strmsgText = "ExpertiseMstDATA.updateRecord(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ExpertiseMstDATA->updateRecord()", strmsgText);
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
