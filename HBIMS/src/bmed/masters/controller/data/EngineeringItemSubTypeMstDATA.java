package bmed.masters.controller.data;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import javax.servlet.http.HttpServletRequest;
import bmed.masters.bo.EngineeringItemSubTypeMstBO;
import bmed.masters.controller.fb.EngineeringItemSubTypeMstFB;
import bmed.masters.vo.EngineeringItemSubMstVO;

/**
 * @author Arun V.R
 * Creation Date:- 11-jan-2011 
 * Modifying Date:- 21-jan-2011
 * Used For:-Engineering Item Sub Type Master
 * Team Lead By:- Amit Kumar
 * Module:- BMED(HIS Project)
 * 
 */
public class EngineeringItemSubTypeMstDATA {


	/**
	 * to display the parent parameter combo.
	 * 
	 * @param request the form bean
	 * @param formBean the request
	 */

	public static void getAddPageComponent(HttpServletRequest request,
			EngineeringItemSubTypeMstFB formBean) {

		EngineeringItemSubMstVO vo = null;
		EngineeringItemSubTypeMstBO bo = null;
		String strmsgText;
		HisUtil hisutil = null;
		String strEnggItemTypeCmb;

		try
		{
			bo = new EngineeringItemSubTypeMstBO();
			vo = new EngineeringItemSubMstVO();
			hisutil = new HisUtil("bmed", "EngineeringItemSubTypeMstDATA");

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
			.toString();
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");

			vo.setStrHospitalCode(hosCode);
			// Calling BO Method					
			bo.initAdd(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}

			hisutil = new HisUtil("bmed", "EngineeringItemSubTypeMstBO");
			String strCombo[] = request.getParameterValues("combo");

			if (vo.getEnggItemTypeCmbWS() != null && vo.getEnggItemTypeCmbWS().size() > 0) 
			{
				strEnggItemTypeCmb = hisutil.getOptionValue(vo.getEnggItemTypeCmbWS(),strCombo[0],"0^Select Value",  false);
			} else {
				strEnggItemTypeCmb = "<option value='0'>Select Value 1</option>";
			}
			formBean.setStrEnggItemTypeCmb(strEnggItemTypeCmb);

			formBean.setStrCtDate(ctDate); 

		} catch (Exception e) {

			strmsgText = "EngineeringItemSubTypeMstDATA.getAddPageComponent(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"EngineeringItemSubTypeMstDATA->getAddPageComponent()", strmsgText);
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
	 * 
	 * @param request the request
	 * @param formbean the formbean
	 */

	public static void InsertRecord(EngineeringItemSubTypeMstFB formbean,
			HttpServletRequest request) {

		String strmsgText = "";
		String strEngItemTypeId;
		EngineeringItemSubMstVO vo = null;
		EngineeringItemSubTypeMstBO bo = null;

		try {

			bo = new EngineeringItemSubTypeMstBO();
			vo = new EngineeringItemSubMstVO();
			strEngItemTypeId = formbean.getCombo()[0];
			formbean.setStrEngItemTypeId(strEngItemTypeId);
			vo.setStrEnggItemTypeId(formbean.getStrEnggItemTypeId());
			formbean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formbean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());          

			vo = (EngineeringItemSubMstVO) TransferObjectFactory.populateData("bmed.masters.vo.EngineeringItemSubMstVO", formbean);


			bo.insertRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			if (vo.getBExistStatus()== true) 
			{
				formbean.setStrWarning("Engineering Item Sub Type Name already exist!");
			}
			else 
			{
				formbean.setStrMsg("Record saved successfully!");
				formbean.setStrEnggItemTypeId(vo.getStrEngItemTypeId());
			}


		}

		catch (Exception e) {

			strmsgText = "EngineeringItemSubTypeMaster.EngineeringItemSubTypeMstDATA.insertRecord(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("store",
					"EngineeringItemSubTypeMstDATA->insertRecord()", strmsgText);
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
	public static void modify(HttpServletRequest request, EngineeringItemSubTypeMstFB formBean) 
	{
		EngineeringItemSubTypeMstBO bo = null;
		EngineeringItemSubMstVO vo = null;
		String temp[] = null;
		String strChk="1";



		try
		{
			vo = new EngineeringItemSubMstVO();
			bo = new EngineeringItemSubTypeMstBO();

			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			if(formBean.getCombo()!=null) {
				formBean.setStrIsValid(formBean.getCombo()[1]);	
			}

			TransferObjectFactory.populateData(vo, formBean);
			if (request.getParameter("chk") != null ) 
			{            

				strChk = request.getParameter("chk");
				strChk = strChk.replace("$", "@");
				temp = strChk.split("@");
				vo.setStrHospitalCode(temp[0]);
				vo.setStrEngItemSubTypeId(temp[1]);
				vo.setStrEnggItemTypeId(temp[2]);
				formBean.setStrEngItemSubTypeId(temp[1]);
				formBean.setStrEngItemTypeId(temp[2]);
				formBean.setStrChk(strChk);


			} 
			else if(strChk.equals("1")){

				strChk = formBean.getStrChk();
				strChk = strChk.replace("$", "@");
				temp = strChk.split("@");
				vo.setStrHospitalCode(temp[0]);
				vo.setStrEngItemSubTypeId(temp[1]);
				vo.setStrEnggItemTypeId(temp[2]);

			}
			bo.modify(vo);



			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				formBean.setStrEnggItemTypeCmb(vo.getStrEnggItemTypeCmb());
				formBean.setStrEngItemSubTypeName(vo.getStrEngItemSubTypeName());
				formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
				formBean.setStrRemarks(vo.getStrRemarks());


			} 


		} catch (Exception e) {
//			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed",
					"EngineeringItemSubTypeMstDATA->modify()", strmsgText);
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
	 * @return true, if update record
	 */
	public static boolean updateRecord(EngineeringItemSubTypeMstFB formBean,HttpServletRequest request)
	{
		EngineeringItemSubTypeMstBO bo;
		EngineeringItemSubMstVO vo;
		String strmsgText = "";
		boolean retValue = true;
		String temp[] = null;
		String strChk;

		try {

			bo = new EngineeringItemSubTypeMstBO();
			vo = new EngineeringItemSubMstVO();

			String strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strSeatid = request.getSession().getAttribute("SEATID").toString();


			strChk = formBean.getStrChk();
			if(strChk!=null && !strChk.equals("")){
				strChk = strChk.replace("$", "@");
				temp = strChk.split("@");
				formBean.setStrHospitalCode(temp[0]);
				formBean.setStrEngItemSubTypeId(temp[1]);
				formBean.setStrEngItemTypeId(temp[2]);
				formBean.setStrChk(strChk);
			}


			formBean.setStrHospitalCode(strHosCode);
			formBean.setStrSeatId(strSeatid);			
			vo = (EngineeringItemSubMstVO) TransferObjectFactory.populateData("bmed.masters.vo.EngineeringItemSubMstVO", formBean);

			bo.updateRecord(vo);


			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			else 
			{

				if (vo.getBExistStatus()== true) 
				{
					formBean.setStrWarning("Engineering Item Sub Type Name already exist!");
					retValue=false;
				}
				else 
				{
					formBean.setStrMsg("Record Modify Successfully");
				}
			}

		} catch (Exception e) 
		{
	//		e.printStackTrace();

			strmsgText = "EngineeringItemSubTypeMstDATA.updateRecord(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"EngineeringItemSubTypeMstDATA->updateRecord()", strmsgText);
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
