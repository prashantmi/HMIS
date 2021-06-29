package hisglobal.utility.generictemplate.masters.data;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import auditlogClient.util.AuditlogDATA;
//import auditlogClient.vo.AuditLogVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.ParameterMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.utility.generictemplate.bo.RegMasterBO;


/**
 * Created By 	: Komal yadav
 * Date			: feb-26-2014
 */
public class ParameterMstDATA 
{

	private ParameterMasterVO modifyRecordParameterMst(ParameterMasterVO vo)
	{
		ParameterMasterVO paraMstVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try{
			paraMstVO_p=bo.modifyRecordParameterMst(vo);

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
		return paraMstVO_p;
	}


	public static boolean updateParameterMasterDtl(HttpServletRequest objRequest, ParameterMasterVO VOParaMst, String strMode_p)
	{
		ParameterMasterVO objParaMstVO;
		boolean bExistStatus=false;
		try 
		{
			objParaMstVO= new ParameterMasterVO();
			objParaMstVO=VOParaMst;
			UserVO uservo = ControllerUTIL.getUserVO(objRequest);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus= bo.updateParameterMasterDtl(VOParaMst,strMode_p,uservo);


			if (objParaMstVO.getStrMsgType()!=null && objParaMstVO.getStrMsgType().equals("1")) {
				throw new Exception(objParaMstVO.getStrMsgString());
			}

			if (bExistStatus == false) 
			{
				VOParaMst.setStrWarning("Data already exist");
			} 
			else 
			{
				VOParaMst.setOldParaName("");
				Map mp= new HashMap();
				mp.put("save_1"  , VOParaMst);
				String[] arrKeyVariables= new String[2];
				arrKeyVariables[0]=VOParaMst.getParaName();
				arrKeyVariables[1]=VOParaMst.getParaMstId();
				//AuditlogDATA.saveUpdateAuditLog(mp,  uservo, objRequest, arrKeyVariables);
				VOParaMst.setStrMsg("Data Saved Successfully");
				
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "ParameterMasterAction.ParameterMasterDATA.updateParametermasterDtl(vo) --> "+ e.getMessage();
			VOParaMst.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objParaMstVO = null;
		}
		return bExistStatus;
	}

	/**
	 * to get the data for modify page.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static ParameterMasterVO modifyRecord( HttpServletRequest request, ParameterMasterVO objParaMstVO) 
	{
		ParameterMstDATA ParameterMstDATA_obj = new ParameterMstDATA();
		String strMsgText = "";

		String strTemp[] = null;
		ParameterMasterVO VOParaMst =new ParameterMasterVO();
		String strChk = "";

		try {
			String strCombos[] = request.getParameterValues("combo");

			if(request.getParameter("chk") !=null && !request.getParameter("chk").equals(""))
			{
				strChk = request.getParameter("chk");
				strTemp = strChk.replace('$', '#').split("#");
				VOParaMst.setParaMstId(strTemp[0]);
				VOParaMst.setStrIsValid(strCombos[0]);
			}
			else
				VOParaMst=objParaMstVO;

			
			ParameterMasterVO paraMstVO_p=ParameterMstDATA_obj.modifyRecordParameterMst(VOParaMst);
			if (VOParaMst.getStrMsgType().equals("1")) 
			{
				throw new Exception(VOParaMst.getStrMsgString());
			}
			VOParaMst.setParaMstId(paraMstVO_p.getParaMstId());
			VOParaMst.setParaName(paraMstVO_p.getParaName());

			if(request.getParameter("chk") !=null && !request.getParameter("chk").equals(""))
			{
				VOParaMst.setStrIsValid("");
				//AuditLogVO objAuditLogVO= new AuditLogVO(AuditLogVO.REGISTRATION,"1");
				Map mp= new HashMap();
				mp.put("save_1", VOParaMst);
			//	objAuditLogVO.setMpobjectsOnInit(mp);
				//AuditlogDATA.initAuditLog(objAuditLogVO, ControllerUTIL.getUserVO(request),request);
			}

		} 
		catch (Exception e) 
		{
			strMsgText = "ParameterMstDATA.modifyRecord(request) --> "+ e.getMessage();
			VOParaMst.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
			HisException eObj = new HisException("hisglobal","ParameterMstDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			ParameterMstDATA_obj = null;
			strMsgText = null;
		}
		return VOParaMst;
	}


	public static boolean saveParameterMasterDtl(HttpServletRequest objHttpServletRequest ,ParameterMasterVO VOParaMst,String strMode_p) 
	{

		ParameterMasterVO objParaMstVO;
		boolean bExistStatus=false;

		try
		{
			objParaMstVO= new ParameterMasterVO();

			objParaMstVO=VOParaMst;
			//String abc = this
			UserVO userVo= ControllerUTIL.getUserVO(objHttpServletRequest);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.saveParameterMasterDtl(VOParaMst,strMode_p,userVo);

			if (objParaMstVO.getStrMsgType()!=null && objParaMstVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(objParaMstVO.getStrMsgString());
			}

			if (bExistStatus == false) 
			{
				VOParaMst.setStrWarning("Duplicate Name Exist..!");
			} else
			{
				VOParaMst.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "ParameterMasterAction.ParametermasterDATA.saveParametermasterDtl(vo) --> "
					+ e.getMessage();
			VOParaMst.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objParaMstVO = null;
		}
		return bExistStatus;
	}
}
