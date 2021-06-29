package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVisitDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.MbCandidateAcceptanceDATA;
import medicalboard.transactions.controller.fb.MbCandidateAcceptanceFB;
import registration.RegistrationConfig;
import registration.controller.util.PatDetailUTIL;

public class MbCandidateAcceptanceUTL  extends ControllerUTIL{
	/**
	 * get the list of the certificate type for which requisition has been scheduled today
	 * @param _request -HttpServletRequest
	 * @throws Exception 
	 */
		public static void getCertificateTypeList(MbCandidateAcceptanceFB _fb,HttpServletRequest _request) throws Exception{	
		
			Status objStatus=new Status();
//			Map mp=new HashMap();
			List certificateTypeList=new ArrayList();
			try{
				UserVO userVO=getUserVO(_request);
				HisUtil hisutil=new HisUtil("cssd","RequestOfItemsUTIL");
				String examDate = hisutil.getASDate("dd-MMM-yyyy");
				_fb.setStrPreviousDate(examDate);
				certificateTypeList=MbCandidateAcceptanceDATA.getCertificateTypeList(userVO,examDate);
		        WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE ,certificateTypeList);
		        _fb.setCertificateTypeID("%");
		        
		        getCandidateList(_fb, _request,examDate);
		        if(_fb.getStrPreviousDate().equals(examDate)){
		        	_fb.setChkPreviousDate("0");
				}
				else
					_fb.setChkPreviousDate("1");
		        objStatus.add(Status.NEW);
		        
		    }
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.LIST,"",e.getMessage());	
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
		
		
		/**
		 * get the list of the candidate who are scheduled today
		 * @param _fb
		 * @param _request
		 * @throws Exception 
		 */
		public static void getCandidateList(MbCandidateAcceptanceFB _fb,HttpServletRequest _request,String examDate) throws Exception{	
			
			Status objStatus=new Status();
//			Map mp=new HashMap();
			List <MedicalBoardRequisitionVO> mbRequisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
			try{
				UserVO userVO=getUserVO(_request);
				List certificateTypeList=new ArrayList();
				certificateTypeList=MbCandidateAcceptanceDATA.getCertificateTypeList(userVO,examDate);
			        
				mbRequisitionVOList=MbCandidateAcceptanceDATA.getCandidateList(_fb.getCertificateTypeID(),userVO,examDate);
				WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,mbRequisitionVOList);
		        WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE ,certificateTypeList);
		        HisUtil hisutil=new HisUtil("cssd","RequestOfItemsUTIL");
				String sysdate = hisutil.getASDate("dd-MMM-yyyy");
				objStatus.add(Status.NEW);
				objStatus.add(Status.LIST);
				 if(_fb.getStrPreviousDate().equals(sysdate)){
			        	_fb.setChkPreviousDate("0");
					}
					else
						_fb.setChkPreviousDate("1");
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,new ArrayList());

				objStatus.add(Status.NEW);
			//	objStatus.add(Status.LIST);
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
		

	/**
	 * get the list of the checklist which are compulsory at acceptance level
	 * and the list of checklist which has been added previously during requisition raising
	 * @param _fb
	 * @param _request
	 */	
	public static void getChecklistDetail(MbCandidateAcceptanceFB _fb,HttpServletRequest _request){	
			
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		//List <MedicalBoardChecklistVO> checklistVOList=new ArrayList<MedicalBoardChecklistVO>();
		List boardList=null;
		List <MedicalBoardRequisitionVO> mbRequisitionVOList=null;
		try{
			UserVO userVO=getUserVO(_request);
			PatDetailUTIL.getPatientDtlByCrno(_fb, _request);
			mbRequisitionVOList=(List)_request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			//MedicalBoardChecklistVO checklistVO=new MedicalBoardChecklistVO();
			MedicalBoardRequisitionVO requisitionVO=new MedicalBoardRequisitionVO();
			requisitionVO.setCertificateTypeID(_fb.getCertificateTypeID());
			requisitionVO.setReqID(_fb.getReqID());
			requisitionVO.setPatCrNo(_fb.getPatCrNo());
			requisitionVO.setVisitNo(_fb.getVisitNo());
			essentialMap =MbCandidateAcceptanceDATA.getCandidateAcceptanceEssential(requisitionVO,userVO,_fb.getStrPreviousDate());
			
			for(int i=0;i<mbRequisitionVOList.size();i++){
				if(mbRequisitionVOList.get(i).getReqID().equals(_fb.getReqID())){
					requisitionVO.setBoardName(mbRequisitionVOList.get(i).getBoardName());
					break;
				}
			}
			
			boardList=(List)essentialMap.get(MedicalBoardConfig.MEDICAL_BOARD_LIST);
			int visitNo=Integer.parseInt(_fb.getVisitNo());
			if(visitNo==1 && (boardList==null || boardList.size()==0)){
					throw new HisRecordNotFoundException("No Board Found");
			}
			if(visitNo>1){
				_fb.setBoardName(requisitionVO.getBoardName());
			}
					
			WebUTIL.setMapInSession(essentialMap,_request);
			_fb.setFlag("true");
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.TRANSINPROCESS,e.getMessage(),"");	
			essentialMap=e.getEssentialMap();
			//WebUTIL.setMapInSession(essentialMap,_request);
			_fb.setFlag("false");
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
		
	
	/**
	 * save the acceptance detail
	 * @param _fb
	 * @param _request
	 */
	public static void saveCandidateAcceptance(MbCandidateAcceptanceFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		MedicalBoardRequisitionVO requisitionVO=null;
		List <MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		List <MedicalBoardChecklistVO> checklistVOList=new ArrayList<MedicalBoardChecklistVO>();
		List <MedicalBoardChecklistVO> checklistVOOldList=null;
		MedicalBoardChecklistVO checklistVO=null;
		MedicalBoardVisitDtlVO visitDetailVO=new MedicalBoardVisitDtlVO();
		EpisodeVO episodeVO=null;
				
		try{
			UserVO userVO=getUserVO(_request);
			requisitionVOList=(List)_request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			checklistVOOldList=(List)_request.getSession().getAttribute(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST);
			PatientVO patientVO=(PatientVO)_request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			episodeVO=(EpisodeVO)_request.getSession().getAttribute(MedicalBoardConfig.MEDICAL_BOARD_EPISODE_VO);
			
			//get the requisition detail of the selected candidate
			if(requisitionVOList!=null){
				for(int i=0;i<requisitionVOList.size();i++){
					if(requisitionVOList.get(i).getPatCrNo().equals(_fb.getPatCrNo())){
						requisitionVO=requisitionVOList.get(i);
						break;
					}
				}
			}
			
			//checklist detail
			if(_fb.getSelectedCheckList()!=null){
				for(int i=0;i<_fb.getSelectedCheckList().length;i++){
					checklistVO=new MedicalBoardChecklistVO();
					checklistVO=checklistVOOldList.get(Integer.parseInt(_fb.getSelectedCheckList()[i].split("#")[0]));
					checklistVO.setRemarks(_fb.getRemarks()[i]);
					checklistVO.setReqID(_fb.getReqID());
					checklistVOList.add(checklistVO);
				}
			}
			
			//set requisition status as accepted
			requisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE);
			if(Integer.parseInt(requisitionVO.getVisitNo())==1){
				requisitionVO.setBoardNo(_fb.getBoardNo());
			}
			HelperMethods.populate(visitDetailVO, requisitionVO);
			visitDetailVO.setIsReferred(MedicalBoardConfig.IS_REFERRED_NO);
			visitDetailVO.setIsInvestigationRaised(MedicalBoardConfig.IS_INVESTIGATION_RAISED_NO);
			
			episodeVO.setPatPrimaryCat(patientVO.getPatPrimaryCatCode());
			patientVO.setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
			
			//For previous date candidates
			HisUtil hisutil=new HisUtil("cssd","RequestOfItemsUTIL");
			String examDate = hisutil.getASDate("dd-MMM-yyyy");
			//Previious date data
			requisitionVO.setChkPreviousDate(_fb.getChkPreviousDate());
			requisitionVO.setStrPreviousDate(_fb.getStrPreviousDate());
			MbCandidateAcceptanceDATA.saveCandidateAcceptance(checklistVOList,requisitionVO,episodeVO,patientVO,visitDetailVO,userVO);
			
			objStatus.add(Status.DONE,"","Record Saved Successfully");
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,new ArrayList());

		//	objStatus.add(Status.NEW);
		//	objStatus.add(Status.LIST);
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
		catch(Exception e){
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
	
	/**
	 * get the list of the candidate who are scheduled today
	 * @param _fb
	 * @param _request
	 */
	public static void getPrevCandidateList(MbCandidateAcceptanceFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
//		Map mp=new HashMap();
		List <MedicalBoardRequisitionVO> mbRequisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		try{
			UserVO userVO=getUserVO(_request);
			mbRequisitionVOList=MbCandidateAcceptanceDATA.getPrevCandidateList(_fb.getCertificateTypeID(),userVO);
			WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,mbRequisitionVOList);
			
			objStatus.add(Status.NEW);
			objStatus.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
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
	
			
}//end class
