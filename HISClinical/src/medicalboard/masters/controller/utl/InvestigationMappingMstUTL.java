package medicalboard.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.MbInvestigationMappingMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.InvestigationMappingMstDATA;
import medicalboard.masters.controller.fb.InvestigationMappingMstFB;


public class InvestigationMappingMstUTL extends ControllerUTIL 
{

	public static void getEssential(InvestigationMappingMstFB fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map essentialMp=new HashMap();
		List certificateTypeName=new ArrayList();
		try{
		UserVO userVO=getUserVO(_request);
		setSysdate(_request);
		String certificateTypeId="";
		if(fb.getControls()[0]!=null)
		{
		    certificateTypeId=fb.getControls()[0];
			fb.setCertificateTypeID(certificateTypeId);
		}
		
		essentialMp=InvestigationMappingMstDATA.getInvestigationMappingMstEssentials(certificateTypeId,userVO);
		
				
		certificateTypeName=(List)essentialMp.get(MedicalBoardConfig.CERTIFICATE_TYPE_LIST);
		
		   for(int i=0;i<certificateTypeName.size();i++)
		   {
			   Entry entry=new Entry();
			   entry=(Entry)certificateTypeName.get(i);
			  if(fb.getCertificateTypeID().equals(entry.getValue()))
			  {
				  fb.setCertificateTypeName(entry.getLabel());
			  }
		   }
		   WebUTIL.setMapInSession(essentialMp,_request);
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}		
		catch(HisRegistrationTimingExpiredException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}
	
public static void getModifyDetail(InvestigationMappingMstFB fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map essentialMp=new HashMap();
		List certificateTypeName=new ArrayList();
//		List selLabTestList=null;
		try{
		UserVO userVO=getUserVO(_request);
		setSysdate(_request);
		
		// Fetching Selected Record Primary Key
		String chk = fb.getChk()[0].replace("^", "@");
		String[] concatid = chk.split("@");

		String scertificateTypeId = concatid[0];
//		String sSlno = concatid[1];
//		String shospitalCode = concatid[2];
		
		
		
		/*
		String certificateTypeId="";
		if(fb.getControls()[0]!=null)
		{
		    certificateTypeId=fb.getControls()[0];
			fb.setCertificateTypeID(certificateTypeId);
		}
		*/
		fb.setCertificateTypeID(scertificateTypeId);;
		essentialMp=InvestigationMappingMstDATA.getInvestigationModifyEssentials(scertificateTypeId,userVO);
		
				
		certificateTypeName=(List)essentialMp.get(MedicalBoardConfig.CERTIFICATE_TYPE_LIST);
		
		   for(int i=0;i<certificateTypeName.size();i++)
		   {
			   Entry entry=new Entry();
			   entry=(Entry)certificateTypeName.get(i);
			  if(fb.getCertificateTypeID().equals(entry.getValue()))
			  {
				  fb.setCertificateTypeName(entry.getLabel());
			  }
		   }
		   
		   		   
		   WebUTIL.setMapInSession(essentialMp,_request);
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}		
		catch(HisRegistrationTimingExpiredException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}



public static void removeListData(InvestigationMappingMstFB fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	
	try{
//			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			
			List selLabTestCodeList=(List)session.getAttribute(MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST);
			List allLabTestList=(List)session.getAttribute(MedicalBoardConfig.ALL_LAB_TEST_LIST);
			Entry addObj=new Entry();
			
			Integer count=0;
			
			Iterator removeItr=selLabTestCodeList.iterator();
			while(removeItr.hasNext())
			{
				MbInvestigationMappingMstVO investMappingVO=(MbInvestigationMappingMstVO)removeItr.next();
				if(count.toString().equals(fb.getIndex()))
				{
					addObj.setLabel(investMappingVO.getLabTestName());
					addObj.setValue(investMappingVO.getLabTestCode());
					removeItr.remove();
					break;
				}
				count++;
			}
			
			allLabTestList.add(addObj);
					
			
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ALL_LAB_TEST_LIST, allLabTestList);
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST, selLabTestCodeList);
			
			fb.setLabTestCode("-1");
			fb.setIsOptional("-1");
	}
	catch(HisRecordNotFoundException e){
		System.out.println("Inside HisRecordNotFoundException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		e.printStackTrace();
	}		
	catch(HisRegistrationTimingExpiredException e){
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void addList(InvestigationMappingMstFB fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	
	try{
//			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			
			List selLabTestCodeList=(List)session.getAttribute(MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST);
			
			if(selLabTestCodeList==null)
			{
				selLabTestCodeList=new ArrayList();
			}
			
			List allLabTestList=(List)session.getAttribute(MedicalBoardConfig.ALL_LAB_TEST_LIST);
			
			Iterator itr=allLabTestList.iterator();
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();
				if(obj.getValue().equals(fb.getLabTestCode()))
				{
					MbInvestigationMappingMstVO investMappingVO=new MbInvestigationMappingMstVO();
					
					investMappingVO.setLabTestCode(obj.getValue());
					investMappingVO.setLabTestName(obj.getLabel());
					investMappingVO.setIsOptional(fb.getIsOptional());
					investMappingVO.setCertificateTypeID(fb.getCertificateTypeID());
					if(fb.getIsOptional().equals(MedicalBoardConfig.MEDICAL_BOARD_INVESTIGATION_IS_OPTIONAL_YES))
					{
						investMappingVO.setIsOptionalName("Yes");
					}
					else
					{
						investMappingVO.setIsOptionalName("No");
					}
					
					selLabTestCodeList.add(investMappingVO);
					itr.remove();
				}
			}
			
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST, selLabTestCodeList);
			
			fb.setLabTestCode("-1");
			fb.setIsOptional("-1");
			
			
	}
	catch(HisRecordNotFoundException e){
		System.out.println("Inside HisRecordNotFoundException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		e.printStackTrace();
	}		
	catch(HisRegistrationTimingExpiredException e){
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void saveInvestigationMappintDtl(InvestigationMappingMstFB fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	
	try{
			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			
			List selInvestMapVOList=(List)session.getAttribute(MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST);
			
			InvestigationMappingMstDATA.saveInvestigationMappintDtl(selInvestMapVOList,userVO);
			
			fb.setLabTestCode("-1");
			fb.setIsOptional("-1");
			
			objStatus.add(Status.DONE,"Record saved successfully","");
	}
	catch(HisRecordNotFoundException e){
		System.out.println("Inside HisRecordNotFoundException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		e.printStackTrace();
	}		
	catch(HisRegistrationTimingExpiredException e){
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}

public static void updateInvestigationMappintDtl(InvestigationMappingMstFB fb,HttpServletRequest _request){	
	
	Status objStatus=new Status();
	HttpSession session=WebUTIL.getSession(_request);
	
	try{
			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			
			List selInvestMapVOList=(List)session.getAttribute(MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST);
			
			InvestigationMappingMstDATA.updateInvestigationMappintDtl(selInvestMapVOList,userVO);
			
			fb.setLabTestCode("-1");
			fb.setIsOptional("-1");
			
			objStatus.add(Status.DONE,"Record update successfully","");
	}
	catch(HisRecordNotFoundException e){
		System.out.println("Inside HisRecordNotFoundException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		e.printStackTrace();
	}		
	catch(HisRegistrationTimingExpiredException e){
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisDataAccessException e){
		System.out.println("Inside HisDataAccessException");
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		e.printStackTrace();
	}
	catch(HisApplicationExecutionException e){		
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "","Application Execution Error");
		e.printStackTrace();
	}
	catch(HisException e){
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	    System.out.println("objStatus in finally"+objStatus);		 
	    System.out.println("objStatus list"+objStatus.getStatusList());
	}	
}


}
