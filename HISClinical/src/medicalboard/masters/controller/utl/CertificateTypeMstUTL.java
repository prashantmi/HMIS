package medicalboard.masters.controller.utl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.CertificateTypeMstDATA;
import medicalboard.masters.controller.fb.CertificateTypeMstFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.UserVO;

public class CertificateTypeMstUTL extends ControllerUTIL{

	
	public static void getEssential(HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map mp=new HashMap();
		
		try{
		UserVO userVO=getUserVO(_request);
		setSysdate(_request);
		
	//    BoardConfigurationVO bConfigurationVO=new BoardConfigurationVO();
	//    MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
	//    List certificateType=new ArrayList();
	
		mp=CertificateTypeMstDATA.getCertificateEssentials(userVO);
	
		   WebUTIL.setMapInSession(mp,_request);
		
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

	
	
	public static boolean saveCertificateTypeDtl(CertificateTypeMstFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		MbCertificateTypeMstVO mTypeMstVO=new MbCertificateTypeMstVO();
    	 boolean hasFlag=true;
		try
		{
			HelperMethods.populate(mTypeMstVO, fb);
			String[] districtId=null;
			districtId=fb.getSelectedDistrict();
			
			CertificateTypeMstDATA.saveCertificateTypeDtl(mTypeMstVO,districtId,getUserVO(request));
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisDuplicateRecordException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{  hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return hasFlag;
	}
	
	
	public static boolean getDataCertificateType(CertificateTypeMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MbCertificateTypeMstVO mTypeMstVO = new MbCertificateTypeMstVO();
		Map mp = new HashMap();
//		String str = new String();
		List districtList=new ArrayList();
		List RemaingDistrictList=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sCertificateId = concatid[0];
//			String shospitalCode = concatid[1];
			String sSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setCertificateTypeID(sCertificateId);
			fb.setSlNo(sSlno);

			mTypeMstVO.setSlNo(sSlno);
			mTypeMstVO.setCertificateTypeID(sCertificateId);
			
			mp = CertificateTypeMstDATA.getDataCertificateType(mTypeMstVO, userVO);
		    
			mTypeMstVO=(MbCertificateTypeMstVO)mp.get(MedicalBoardConfig.VO_OF_CERTIFICATE_DETAIL);
			
		    HelperMethods.populate(fb, mTypeMstVO);
		    
		    
		    districtList=(List)mp.get(MedicalBoardConfig.DISTRICTID_LIST_SELECTED);
		    RemaingDistrictList=(List)mp.get(MedicalBoardConfig.DISTRICTID_LIST_REMAINING);
		    
		    WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.DISTRICTID_LIST_SELECTED, districtList);
			
		    WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.LIST_OF_DISTRICT, RemaingDistrictList);
		    
		   if(mTypeMstVO.getMaxAge()!=null)
		    {
		    	fb.setAgeBound("1");
		    }
			//objStatus.add(Status.NEW);
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
	
	public static boolean saveModCertificateType(CertificateTypeMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			MbCertificateTypeMstVO mTypeMstVO = new MbCertificateTypeMstVO();
			HelperMethods.populate( mTypeMstVO , fb);
			String[] districtId=null;
			districtId=fb.getSelectedDistrict();
		
			flag=CertificateTypeMstDATA.saveModCertificateType(mTypeMstVO,districtId,userVO);
			if(flag){
				objStatus.add(Status.DONE,"","Record Modified Successfully");
			}
			else{
				objStatus.add(Status.DONE,"","Certificate Type already exists");
			}
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
			objStatus.add(Status.ERROR, "", "Abortion Method already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}

	
	
	
	
 }
