package medicalboard.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.MbCertificateBoardMstVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.CertificateBoardMstDATA;
import medicalboard.masters.controller.fb.CertificateBoardMstFB;


public class CertificateBoardMstUTL extends ControllerUTIL{

	
   public static void getEssential(HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map mp=new HashMap();
		
		
//		MedicalBoardMasterVO[] mBoardMasterVO=null;
		MbCertificateTypeMstVO[] mCertificateTypeMstVO=null;
//		List boardNameList=new ArrayList(); 
		List certificateTypeList=new ArrayList();
		
		try{
		UserVO userVO=getUserVO(_request);
		setSysdate(_request);
		
		mp=CertificateBoardMstDATA.getCertificateBoardEssentials(userVO);
		
	/*	mBoardMasterVO=(MedicalBoardMasterVO[])mp.get(MedicalBoardConfig.BOARD_DETAIL_VO);
		
		for(int i=0;i<mBoardMasterVO.length;i++)
		{
			MedicalBoardMasterVO masterVO=(MedicalBoardMasterVO)mBoardMasterVO[i];
			Entry entry=new Entry();
			entry.setLabel(masterVO.getBoardName());
			entry.setValue(masterVO.getBoardID());
			   
			boardNameList.add(entry);
		}*/
		
	//	WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.BOARD_NAME_LIST ,boardNameList);
		
		mCertificateTypeMstVO=(MbCertificateTypeMstVO[])mp.get(MedicalBoardConfig.CERTIFICATE_TYPE_VO);
		
		for(int i=0;i<mCertificateTypeMstVO.length;i++)
		{
			MbCertificateTypeMstVO masterVO=(MbCertificateTypeMstVO)mCertificateTypeMstVO[i];
			Entry entry=new Entry();
			entry.setLabel(masterVO.getCertificateTypeName());
			entry.setValue(masterVO.getCertificateTypeID());
			   
			certificateTypeList.add(entry);
		}
		
		WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.CERTIFICATE_TYPE_LIST ,certificateTypeList);
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
  
   
  
   public static void getBoard(CertificateBoardMstFB fb, HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map mp=new HashMap();
		MbCertificateTypeMstVO[] mCertificateTypeMstVO=null;
		MbCertificateBoardMstVO mBoardMstVO=new MbCertificateBoardMstVO();
		
		try{
		UserVO userVO=getUserVO(_request);
		setSysdate(_request);
		
		HttpSession session=WebUTIL.getSession(_request);
		
	   mCertificateTypeMstVO=(MbCertificateTypeMstVO[])session.getAttribute(MedicalBoardConfig.CERTIFICATE_TYPE_VO);
		
		 for(int i=0;i<mCertificateTypeMstVO.length;i++)
		  {
			MbCertificateTypeMstVO masterVO=(MbCertificateTypeMstVO)mCertificateTypeMstVO[i];
			 if(fb.getCertificateTypeID().equals(masterVO.getCertificateTypeID()))
			 {
				 mBoardMstVO.setBoardTypeId(masterVO.getBoardType());
			 }
		  }
		
		   mBoardMstVO.setCertificateTypeID(fb.getCertificateTypeID());
 		
		   mp=CertificateBoardMstDATA.getBoard(mBoardMstVO,userVO);
		   
	/*	   List selectedBoard=new ArrayList();
		
		   if(mp.get(MedicalBoardConfig.SELECTED_BOARD_LIST)!=null){
		   selectedBoard=(List)mp.get(MedicalBoardConfig.SELECTED_BOARD_LIST);
		   List selectedBoardInFb=new ArrayList();
		   
		     for(int i=0;i<selectedBoard.size();i++)
		     {
		    	 Entry entry=new Entry();
		    	 entry=(Entry)selectedBoard.get(i);
		    	 selectedBoardInFb.add(entry.getValue());
		     }
		    
		     String[] arr=null;
		     fb.setOldSelectedBoardId((String[])selectedBoardInFb.toArray(arr));
		     
		     
		   }*/
		   
		   
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
 
  
   
   
   public static boolean saveCertificateBoardInfo(CertificateBoardMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
       boolean hasFlag=true;
//       Map mp = new HashMap();
//       HttpSession session =WebUTIL.getSession(_request);
		try
		{
			UserVO userVO = getUserVO(_request);
			MbCertificateBoardMstVO mBoardMstVO=new MbCertificateBoardMstVO();
			
			 
			String[] boardTypeIdArray=_fb.getSelectedBoardId();
			
			mBoardMstVO.setCertificateTypeID(_fb.getCertificateTypeID());
			
			CertificateBoardMstDATA.saveModCertificateBoardInfo(mBoardMstVO,boardTypeIdArray,userVO);
			
			 objStatus.add(Status.DONE, "Record Added Successfully","");
			
		}
		catch(HisDuplicateRecordException e)
		{ hasFlag=false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisRecordNotFoundException e)
		{
			hasFlag=false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			hasFlag=false;
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			hasFlag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{  hasFlag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
  
   
   
   
   

}
