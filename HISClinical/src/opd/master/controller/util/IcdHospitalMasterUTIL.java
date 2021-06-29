package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.IcdHospitalMasterDATA;
import opd.master.controller.fb.IcdHospitalMasterFB;
import registration.RegistrationConfig;

public class IcdHospitalMasterUTIL extends ControllerUTIL
{
	public static boolean getEssentails(IcdHospitalMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			List lstHosDiseases = IcdHospitalMasterDATA.getEssentails(userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_HOS_DISEASE_LIST, lstHosDiseases);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found ");
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
	
	public static boolean fetchDiseaseList(IcdHospitalMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			IcdHospitalMasterVO _diseaseMasterVO=new IcdHospitalMasterVO();
			_diseaseMasterVO.setHospitalDiseaseCode(_fb.getHospitalDiseaseCode());
			

			List DiseaseName = IcdHospitalMasterDATA.fetchDiseaseList(_diseaseMasterVO,userVO);
			String[] arrCode = null;
			String[] arrName = null;
			if(DiseaseName.size()>0)
			{
				_fb.setDisplayList(true);
				arrCode = new String[DiseaseName.size()];
				arrName = new String[DiseaseName.size()];
			}
			else
				_fb.setDisplayList(false);
			for(int i = 0; i<DiseaseName.size();i++)
			{
				Entry entObj = (Entry)DiseaseName.get(i);
				arrCode[i]=entObj.getValue();
				arrName[i]=entObj.getLabel();					
			}
			_fb.setDiagnosticCode(arrCode);
			_fb.setDiagnosticName(arrName);
		    WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_SELECTED_DIAGNOSIS_CODE,_fb.getDiagnosticCode());
		    WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_SELECTED_DIAGNOSIS_NAME,_fb.getDiagnosticName());		  
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ICD_DISEASE_LIST_BY_HOSDIS, DiseaseName);
			objStatus.add(Status.NEW);
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
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static void save(IcdHospitalMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_SELECTED_DIAGNOSIS_CODE,fb.getDiagnosticCode());
	    WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_SELECTED_DIAGNOSIS_NAME,fb.getDiagnosticName());
	    List l= new ArrayList();
	    IcdHospitalMasterVO[] vos = new IcdHospitalMasterVO[fb.getDiagnosticCode().length]; 
	    for(int i=0;i<fb.getDiagnosticCode().length;i++)
	    {
	    	Entry entObj = new Entry(fb.getDiagnosticName()[i],fb.getDiagnosticCode()[i]);
	    	l.add(entObj);

	    	IcdHospitalMasterVO vo= new IcdHospitalMasterVO();
	    	vo.setHospitalDiseaseCode(fb.getHospitalDiseaseCode());
	    	vo.setDiseaseCode(fb.getDiagnosticCode()[i]);
	    	vos[i]=vo;
	    }
	    WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ICD_DISEASE_LIST_BY_HOSDIS, l);
		
	    
		UserVO userVO = getUserVO(request);
		IcdHospitalMasterVO _icdHospitalMasterVO=new IcdHospitalMasterVO();
		_icdHospitalMasterVO.setHospitalDiseaseCode(fb.getHospitalDiseaseCode());
		
		IcdHospitalMasterDATA.save(vos, userVO);
		
		try
		{
			
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
			WebUTIL.setStatus(request,objStatus);
		}
	}
}
