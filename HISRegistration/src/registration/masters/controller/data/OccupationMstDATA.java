package registration.masters.controller.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;
import auditlogClient.vo.AuditLogVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.OccupationVO;

/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
public class OccupationMstDATA 
{

	private OccupationVO modifyRecordOccupationMst(OccupationVO vo)
	{
		OccupationVO OccupationVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try{
			OccupationVO_p=bo.modifyRecordOccupationMst(vo);

			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("regMasterBO.modifyRecord(vo) --> " + vo.getStrMsgString());
			}
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			throw new HisApplicationExecutionException();
		}
		finally
		{
		}
		return OccupationVO_p;
	}


	public static boolean updateOccupationDtl(HttpServletRequest objRequest, OccupationVO VOOccupation, String strMode_p)
	{
		OccupationVO objOccupationVO;
		boolean bExistStatus=false;
		try 
		{
			objOccupationVO= new OccupationVO();
			objOccupationVO=VOOccupation;
			UserVO uservo = ControllerUTIL.getUserVO(objRequest);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus= bo.updateOccupationDtl(VOOccupation,strMode_p,uservo);


			if (objOccupationVO.getStrMsgType()!=null && objOccupationVO.getStrMsgType().equals("1")) {
				throw new Exception(objOccupationVO.getStrMsgString());
			}

			if (bExistStatus == false) 
			{
				VOOccupation.setStrWarning("Data already exist");
			} 
			else 
			{
				VOOccupation.setStrOldOccupationName("");
				Map mp= new HashMap();
				mp.put("save_1"  , VOOccupation);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=VOOccupation.getStrOccupationId();
				AuditlogDATA.saveUpdateAuditLog(mp,  uservo, objRequest, arrKeyVariables);
				VOOccupation.setStrMsg("Data Saved Successfully");
				
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "OccupationAction.OccupationDATA.updateOccupationDtl(vo) --> "+ e.getMessage();
			VOOccupation.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objOccupationVO = null;
		}
		return bExistStatus;
	}

	/**
	 * to get the data for modify page.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static OccupationVO modifyRecord( HttpServletRequest request, OccupationVO objOccupationVO) 
	{
		OccupationMstDATA OccupationMstDATA_obj = new OccupationMstDATA();
		String strMsgText = "";

		String strTemp[] = null;
		OccupationVO VOOccupation =new OccupationVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try {
			String strCombos[] = request.getParameterValues("combo");

			if(request.getParameter("chk") !=null && !request.getParameter("chk").equals(""))
			{
				strChk = request.getParameter("chk");
				strTemp = strChk.replace('$', '#').split("#");
				VOOccupation.setStrOccupationId(strTemp[0]);
				VOOccupation.setStrIsValid(strCombos[0]);
			}
			else
				VOOccupation=objOccupationVO;

			
			OccupationVO OccupationVO_p=OccupationMstDATA_obj.modifyRecordOccupationMst(VOOccupation);
			if (VOOccupation.getStrMsgType().equals("1")) 
			{
				throw new Exception(VOOccupation.getStrMsgString());
			}
			VOOccupation.setStrOccupationId(OccupationVO_p.getStrOccupationId());
			VOOccupation.setStrOccupationName(OccupationVO_p.getStrOccupationName());

			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_OCCUPATION_MASTER;
			mp.put("save_1", VOOccupation);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);
		
			

		} 
		catch (Exception e) 
		{
			strMsgText = "OccupationMstDATA.modifyRecord(request) --> "+ e.getMessage();
			VOOccupation.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
			HisException eObj = new HisException("registration","OccupationMstDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			OccupationMstDATA_obj = null;
			strMsgText = null;
		}
		return VOOccupation;
	}


	public static boolean saveOccupationDtl(HttpServletRequest objHttpServletRequest ,OccupationVO VOOccupation,String strMode_p) 
	{

		OccupationVO objOccupationVO;
		boolean bExistStatus=false;

		try
		{
			objOccupationVO= new OccupationVO();

			objOccupationVO=VOOccupation;
			//String abc = this
			UserVO userVo= ControllerUTIL.getUserVO(objHttpServletRequest);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.saveOccupationDtl(VOOccupation,strMode_p,userVo);

			if (objOccupationVO.getStrMsgType()!=null && objOccupationVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(objOccupationVO.getStrMsgString());
			}

			if (bExistStatus == false) 
			{
				VOOccupation.setStrWarning("Duplicate Name Exist..!");
			} else
			{
				VOOccupation.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "OccupationAction.OccupationDATA.saveOccupationDtl(vo) --> "
					+ e.getMessage();
			VOOccupation.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objOccupationVO = null;
		}
		return bExistStatus;
	}
}
