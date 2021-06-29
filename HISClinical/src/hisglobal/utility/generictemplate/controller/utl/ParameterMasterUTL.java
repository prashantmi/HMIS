package hisglobal.utility.generictemplate.controller.utl;

/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.controller.data.GenericTemplateDATA;
import hisglobal.utility.generictemplate.controller.fb.ParameterMasterFB;
import hisglobal.vo.ParameterMasterVO;
import hisglobal.vo.TemplateGroupVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ParameterMasterUTL extends ControllerUTIL 
{
	// Setting Template Parameter Master Essentials
	public static void setEssentials(ParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			List<TemplateGroupVO> lstTempGroup = GenericTemplateDATA.getTemplateGroupList(userVO);
			WebUTIL.setAttributeInSession(_request, GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_GROUP, lstTempGroup);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW ,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}

	public static boolean saveParameter(ParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			ParameterMasterVO parameterVO= new ParameterMasterVO();
			parameterVO.setParaName(_fb.getParaName());
			parameterVO.setParaBound(_fb.getParaBound());
			parameterVO.setParaType(_fb.getParaType());
			parameterVO.setConceptId(_fb.getConceptId());
			parameterVO.setPrefferedTerm(_fb.getPrefferedTerm());
			
			
			saveFlag=GenericTemplateDATA.saveParameter(parameterVO, userVO);
			if(saveFlag){
				objStatus.add(Status.DONE,"","Parameter Added Successfully");
			}
			else
				objStatus.add(Status.DONE,"","Parameter Already Exists");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		return saveFlag;
	}
	
	public static void  getParameterById(ParameterMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		String id=_fb.getChk();	//.substring(0,_fb.getChk().indexOf("^"));
		ParameterMasterVO parameterVO;
		//String paraId[]=id.split("^");
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			setEssentials(_fb, _request);
			String listId="";
			parameterVO=GenericTemplateDATA.getParameterById(id, userVO);
			List<TemplateGroupVO> list=(ArrayList<TemplateGroupVO>)_request.getSession().getAttribute(GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_GROUP);
			for(TemplateGroupVO tempGrpVO : list)
				if(tempGrpVO.getTemplateGroupID().equalsIgnoreCase(parameterVO.getParaType()))
				{
					listId=tempGrpVO.getTemplateGroupName();
					break;
				}
			_fb.setParaId(parameterVO.getParaId());
			_fb.setParaName(parameterVO.getParaName());
			_fb.setParaBound(parameterVO.getParaBound());
			_fb.setParaType(parameterVO.getParaType());
			_fb.setIsActive(parameterVO.getIsValid());
			_fb.setParaTypeLabel(listId);
			_fb.setPrefferedTerm(parameterVO.getPrefferedTerm());
			_fb.setConceptId(parameterVO.getConceptId());
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
			
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	public static boolean updateParameter(ParameterMasterFB _fb,HttpServletRequest _request)
	{
		boolean flag =  true;
		Status  objStatus=new Status();
		ParameterMasterVO parameterVO;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			parameterVO= new ParameterMasterVO();
			parameterVO.setParaId(_fb.getParaId());
			parameterVO.setParaName(_fb.getParaName());
			parameterVO.setParaBound(_fb.getParaBound());
			parameterVO.setParaType(_fb.getParaType());
			parameterVO.setIsValid(_fb.getIsActive());
			parameterVO.setHospitalCode(userVO.getHospitalCode());
			parameterVO.setConceptId(_fb.getConceptId());
			parameterVO.setPrefferedTerm(_fb.getPrefferedTerm());
		
			flag=GenericTemplateDATA.updateParameter(parameterVO, userVO);
			if(flag)
				objStatus.add(Status.DONE,"","Parameter updated Successfully");
			else
				objStatus.add(Status.UNSUCESSFULL,"","Parameter Already Exists");
		}
		catch(HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		return flag;		
	}
}
