package loinc.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 02-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import loinc.bo.LoincMasterBO;
import loinc.vo.LoincVO;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class LoincMstDATA 
{

	private LoincVO modifyRecordLocationMst(LoincVO vo)
	{
		LoincVO LocationVO_p = null;
		LoincMasterBO bo = new LoincMasterBO();
		try
		{
		//	LocationVO_p=bo.modifyRecordLocationMst(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("regMasterBO.modifyRecordLocationMst(vo) --> " + vo.getStrMsgString());
			}
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	  
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e)
		{	
			e.printStackTrace();
			throw new HisDataAccessException();  	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
		}
		return LocationVO_p;
	}

	//To populate the values in the Department Master Screen Combos
	public static List getLoincTestName(HttpServletRequest request )
	{
		List alList=null;
		try
		{
			
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			LoincMasterBO bo = new LoincMasterBO();
			alList=bo.getLoincTestName(uservo);			
			request.getSession().setAttribute("testName",alList);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return alList;

	}

	public static List GetPropertyCombo(HttpServletRequest request)
	{
	List alList=null;
	try
	{
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		LoincMasterBO bo = new LoincMasterBO();
		alList=bo.GetPropertyCombo(uservo);			
		request.getSession().setAttribute("UOMList",alList);

	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return alList;

}
	
	
	public static void searchSuggestiveLoinc(LoincVO strLoincVo )
	{
		try 
		{
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			//code contains the full values of combo(appended with ^) and Value signifies the actual name(e.g. hemoglobin).
			
			//get test name value (e.g hemoglobin) 
			String strTestNameValue= request.getParameter("strTestNameValue").toString();
			
			// get para name code, it contains value for ( e.g. 10011001^Ord)
			
			String strTestParaNameCode = request.getParameter("strTestParaNameCode").toString();
			
			// ^ is replaced by # in order to split as we cannot split vales with ^
			String strTestParaNameCodeSplit =	strTestParaNameCode.replace('^', '#');
			String[] Split = strTestParaNameCodeSplit.split("#");
			strTestParaNameCode = Split[0]; 
			strLoincVo.setstrTestParaNameCode(strTestParaNameCode);
			String StrScale = Split[1]; //second part contains scale 
				
			
			// get the code for sample  (e.g 110^10^MCnc^Bld)
			String strTestSampleCode = request.getParameter("strTestSampleCode").toString();
						
			String strTestSampleCodeSplit =	strTestSampleCode.replace('^', '#');
			String[] parts = strTestSampleCodeSplit.split("#");
			strTestSampleCode = parts[0];
			String StrProperty = parts[2]; 
			String StrSystem = parts[3];
			strLoincVo.setStrTestSample(strTestSampleCode);
			
			String StrTimeofMeasurement = strLoincVo.getStrTimeofMeasurement();
			LoincMasterBO bo = new LoincMasterBO();
			
			//strLoincVo.setStrTestNameValue("Hemoglobin");
			//strLoincVo.setStrTestParaName("Hemoglobin");
			//strLoincVo.setStrSystem("Bld");
			//strLoincVo.setStrProperty("MCnc");
			//strLoincVo.setStrTimeofMeasurement("Pt");
			//strLoincVo.setStrScale("Qn");
			strLoincVo.setStrTestNameValue(strTestNameValue);
			strLoincVo.setStrSystem(StrSystem);
			strLoincVo.setStrProperty(StrProperty);
			strLoincVo.setStrTimeofMeasurement(StrTimeofMeasurement);
			strLoincVo.setStrScale(StrScale);
			
			LoincVO[] arrLoincVo =  bo.searchSuggestiveLoinc(strLoincVo, uservo);
			
			if(arrLoincVo == null && arrLoincVo.length == 0)
				strLoincVo.setStrWarning("No LOINC Code found");
			WebUTIL.setAttributeInSession(request,"lstLoincVo",arrLoincVo);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "LocationMstDATA.updateLocationDtl(vo) --> "+ e.getMessage();
			strLoincVo.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			strLoincVo = null;
		}
		
	}
	
	
	public static void searchLoinc(LoincVO strLoincVo )
	{
		try 
		{
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			//code contains the full values of combo(appended with ^) and Value signifies the actual name(e.g. hemoglobin).
			
			//get test name value (e.g hemoglobin) 
			String strIsLoincName = strLoincVo.getIsLoincName();
			String strLoincSearchParameter =  strLoincVo.getStrLoincSearchParameter();
			
			System.out.println(strIsLoincName);
			System.out.println(strLoincSearchParameter);
			
			LoincMasterBO bo = new LoincMasterBO();
			LoincVO[] arrLoincVo =  bo.searchLoinc(strLoincVo, uservo);
		
			
			if(arrLoincVo == null && arrLoincVo.length == 0)
				strLoincVo.setStrWarning("No LOINC Code found");
			WebUTIL.setAttributeInSession(request,"lstLoincVo",arrLoincVo);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strLoincVo.setStrWarning("No LOINC Code found");
			String strmsgText = "LocationMstDATA.updateLocationDtl(vo) --> "+ e.getMessage();
			strLoincVo.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			strLoincVo = null;
		}
		
	}

	
	/*public static List getLoincTestParaNameforModify(HttpServletRequest request )

	{
		List alList=null;
		try
		{
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			LoincMasterBO bo = new LoincMasterBO();
			alList=bo.getLoincTestParaNameforModify(uservo);			
			request.getSession().setAttribute("UOMList",alList);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return alList;

	}*/
	
	
	public static List getTestParaName_AJAX(UserVO _UserVO,String strTestCode)
	{
		LoincMasterBO bo = new LoincMasterBO();
		return bo.getTestParaName_AJAX(_UserVO,strTestCode);
	}
	
	
	public static List getTestSample_AJAX(UserVO _UserVO, String strTestCode, String strTestType)
	{
		List alList=null;
		try
		{
			ActionContext acx=ActionContext.getContext();
			LoincMasterBO bo = new LoincMasterBO();
			return bo.getLoincTestSample(_UserVO, strTestCode,strTestType);			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return alList;

	}

	
	//To Save the Location Details 
	public static boolean saveLoincDtl(LoincVO locModel,String strMode_p) 
	{

		String strmsgText = "";
		boolean bExistStatus=false;
		try {
					
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			LoincMasterBO bo = new LoincMasterBO();
			//code contains the full values of combo(appended with ^) and Value signifies the actual name(e.g. hemoglobin).
			
			//get loinc code
			String strLoincCode= request.getParameter("strLoincCode").toString();
			
			//get  test name and code
			String strTestNameCode= request.getParameter("strTestNameCode").toString();
			// ^ is replaced by # in order to split as we cannot split vales with ^
			String strTestNameCodeSplit =	strTestNameCode.replace('^', '#');
			String strTestNameValue = strTestNameCodeSplit.split("#")[0]; 
			
			//get test para name and code
			String strTestParaNameCode = request.getParameter("strTestParaNameCode").toString();
			String strTestParaNameCodeSplit =	strTestParaNameCode.replace('^', '#');
			String[] Split = strTestParaNameCodeSplit.split("#");
			String strTestParaNameValue = Split[0]; 
			
			//get  test sample name and code
			String strTestSampleCode = request.getParameter("strTestSampleCode").toString();
			String strTestSampleCodeSplit =	strTestSampleCode.replace('^', '#');
			String[] parts = strTestSampleCodeSplit.split("#");
			
			//get sample value
			String strTestSampleValue = parts[0];
			
			//get unit of measurement
			String strUOMValue = parts[1]; 
								
			//locModel.setStrLoincCode("L10980");
			locModel.setStrLoincCode(strLoincCode);
			//locModel.setStrTestName("1001");
			locModel.setStrTestNameCode(strTestNameValue);
			//locModel.setStrTestParaName("1000");
			locModel.setstrTestParaNameCode(strTestParaNameValue);
			//locModel.setStrTestSample("100");
			locModel.setstrTestSampleCode(strTestSampleValue);
			//	locModel.setStrUOMCode("MCnc");
			locModel.setStrUOMValue(strUOMValue);
			
			
			//objLocModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.saveLoincDtl(locModel,strMode_p,uservo);

			if (locModel.getStrMsgType()!=null && locModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(locModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				locModel.setStrMsg("Data Saved Successfully");
			} else 
			{
				locModel.setStrWarning("Duplicate Name Exist..!");
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

			strmsgText= "LocationMstDATA.saveLocationDtl(vo) --> "+ e.getMessage();
			locModel.setStrErrorMsg(strmsgText);
			locModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} 

		finally 
		{
			locModel = null;
		}
		return bExistStatus;
	}


}
