package opd.master.controller.util;

/**
 * @author  CDAC
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data. ModifyViewSymptomWiseTemplateMappingMasterDATA ;
import opd.master.controller.fb.SymptomWiseTemplateMasterFB;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.SymptomWiseTemplateMappingMasterVO;
import hisglobal.vo.UserVO;

public class ModifyViewSymptomWiseTemplateMappingMasterUTIL extends ControllerUTIL 
{
	
	public static boolean getDetail(SymptomWiseTemplateMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//List list=new ArrayList();
		//List allergyType=new ArrayList();
		//List symptomList=new ArrayList();
		List selectedList=new ArrayList();
		List allList=new ArrayList();
		Map essentialMap=new HashMap();
		 
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			SymptomWiseTemplateMappingMasterVO symptomWiseTemplateMappingMasterVO=new SymptomWiseTemplateMappingMasterVO();
			String chk=_fb.getChk().replace("^","@");
			String[] concatid=chk.split("@");
			symptomWiseTemplateMappingMasterVO.setSymptomCode(concatid[0]);
			symptomWiseTemplateMappingMasterVO.setTemplateCode(concatid[1]);
			symptomWiseTemplateMappingMasterVO.setIsValid(_fb.getIsActive());
			essentialMap= ModifyViewSymptomWiseTemplateMappingMasterDATA .getDetails(symptomWiseTemplateMappingMasterVO,userVO);
			
			selectedList=(List)essentialMap.get(OpdConfig.SYMPTOM_SELECTED_Template_LIST);
			allList=(List)essentialMap.get(OpdConfig.Template_ALL_Template_LIST);
			String symptomType=(String)essentialMap.get(OpdConfig.Symptom_TYPE_BY_Symptom_TYPE_CODE);
			WebUTIL.setAttributeInSession(_request, OpdConfig.SYMPTOM_SELECTED_Template_LIST_NEW, selectedList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.SYMPTOM_REMAINING_Template_LIST_NEW, allList);
			_fb.setSymptomTypeName(symptomType);
           
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	public static void saveModifyDetail(SymptomWiseTemplateMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(_request);
		
		try
		{
			//symptomWiseTemplateMappingMasterVO symptomWiseTemplateMappingMasterVO=new symptomWiseTemplateMappingMasterVO();
			String chk=_fb.getChk().replace("^","@");
			String[] concatid=chk.split("@");
			String symptomTypeCode=concatid[0];
			
				
			String[] selectedTemplate=_fb.getSelectedTemplate();
			//System.out.println(_fb.getSelectedSymptoms().toString());
			SymptomWiseTemplateMappingMasterVO[] _symptomWiseTemplateMappingMasterVO=new SymptomWiseTemplateMappingMasterVO[selectedTemplate.length] ;
			for(int i=0;i<_symptomWiseTemplateMappingMasterVO.length;i++)
			{
				_symptomWiseTemplateMappingMasterVO[i]=new SymptomWiseTemplateMappingMasterVO();
				_symptomWiseTemplateMappingMasterVO[i].setSymptomCode(symptomTypeCode);
				_symptomWiseTemplateMappingMasterVO[i].setTemplateCode(selectedTemplate[i]);
				_symptomWiseTemplateMappingMasterVO[i].setSymptomterm(_fb.getSymptomTypeName());
			}
			 ModifyViewSymptomWiseTemplateMappingMasterDATA .saveModifyDetail(_symptomWiseTemplateMappingMasterVO,userVO);
			
			objStatus.add(Status.DONE,"Record Saved Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	
}
