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
import opd.master.controller.data.ModifyViewAllergyWiseSymptomMasterDATA;
import opd.master.controller.fb.AllergyWiseSymptomMasterFB;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.AllergyWiseSymptomMasterVO;
import hisglobal.vo.UserVO;

public class ModifyViewAllergyWiseSymptomMasterUTIL extends ControllerUTIL 
{
	
	public static boolean getDetail(AllergyWiseSymptomMasterFB _fb, HttpServletRequest _request)
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
			AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO=new AllergyWiseSymptomMasterVO();
			String chk=_fb.getChk().replace("^","@");
			String[] concatid=chk.split("@");
			allergyWiseSymptomMasterVO.setAllergyTypeCode(concatid[0]);
			allergyWiseSymptomMasterVO.setSymptomCode(concatid[1]);
			allergyWiseSymptomMasterVO.setIsValid(_fb.getIsActive());
			essentialMap=ModifyViewAllergyWiseSymptomMasterDATA.getDetails(allergyWiseSymptomMasterVO,userVO);
			
			selectedList=(List)essentialMap.get(OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST);
			allList=(List)essentialMap.get(OpdConfig.SYMPTOM_ALL_SYMPTOM_LIST);
			String allergyType=(String)essentialMap.get(OpdConfig.ALLERGY_TYPE_BY_ALLERGY_TYPE_CODE);
			WebUTIL.setAttributeInSession(_request, OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST, selectedList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.SYMPTOM_REMAINING_SYMPTOM_LIST, allList);
			_fb.setAllergyTypeName(allergyType);
           
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
	
	public static void saveModifyDetail(AllergyWiseSymptomMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(_request);
		
		try
		{
			//AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO=new AllergyWiseSymptomMasterVO();
			String chk=_fb.getChk().replace("^","@");
			String[] concatid=chk.split("@");
			String allergyTypeCode=concatid[0];
			
				
			String[] selectedSymptoms=_fb.getSelectedSymptoms();
			//System.out.println(_fb.getSelectedSymptoms().toString());
			AllergyWiseSymptomMasterVO[] _allergyWiseSymptomMasterVO=new AllergyWiseSymptomMasterVO[selectedSymptoms.length] ;
			for(int i=0;i<_allergyWiseSymptomMasterVO.length;i++)
			{
				_allergyWiseSymptomMasterVO[i]=new AllergyWiseSymptomMasterVO();
				_allergyWiseSymptomMasterVO[i].setAllergyTypeCode(allergyTypeCode);
				_allergyWiseSymptomMasterVO[i].setSymptomCode(selectedSymptoms[i]);
			}
			ModifyViewAllergyWiseSymptomMasterDATA.saveModifyDetail(_allergyWiseSymptomMasterVO,userVO);
			
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
