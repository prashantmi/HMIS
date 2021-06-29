package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MBInvestigationRequisitionDetailVO;
import hisglobal.vo.MedicalBoardExternalReferVO;
import hisglobal.vo.MedicalBoardInvestigationMappingVO;
import hisglobal.vo.MedicalBoardReferMappingVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVisitDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.SlideBasedTest;
import hisglobal.vo.Test;
import hisglobal.vo.UserVO;
import investigation.InvestigationConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.MedicalExamInitiationDATA;
import medicalboard.transactions.controller.fb.MedicalExamInitiationFB;
import registration.RegistrationConfig;

public class MedicalExamInitiationUTL  extends ControllerUTIL{
	/**
	 * get the list of the certificate type for which candidate has attended today
	 * @param _request -HttpServletRequest
	 */
		public static void getCertificateTypeList(MedicalExamInitiationFB _fb,HttpServletRequest _request){	
		
			Status objStatus=new Status();
			//Map mp=new HashMap();
			List certificateTypeList=new ArrayList();
			try{
				UserVO userVO=getUserVO(_request);
				certificateTypeList=MedicalExamInitiationDATA.getCertificateTypeList(userVO);
		        WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE ,certificateTypeList);
		        		        
		        objStatus.add(Status.NEW);
			
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());	
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
			catch(Exception e){
				System.out.println("Inside Exception");
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
		public static void getCandidateList(MedicalExamInitiationFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			Map mp=new HashMap();
			//List <MedicalBoardRequisitionVO> mbRequisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
			try{
				UserVO userVO=getUserVO(_request);
				mp=MedicalExamInitiationDATA.getCandidateList(_fb.getCertificateTypeID(),userVO);
				_fb.setCertificateTypeName(getCertificateTypeName(_fb.getCertificateTypeID(), _request));
				
				WebUTIL.setMapInSession(mp,_request);
				
				//objStatus.add(Status.NEW);
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
			catch(Exception e){
				System.out.println("Inside Exception");
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
	 * get the list of the refer department/unit based on the certificate type
	 * @param _fb
	 * @param _request
	 */	
	public static void getReferMappingList(MedicalExamInitiationFB _fb,HttpServletRequest _request){	
			
		Status objStatus=new Status();
		List <MedicalBoardReferMappingVO> referMappingVOList=new ArrayList<MedicalBoardReferMappingVO>();
		EpisodeRefDtlVO episodeRefDtlVO=null;
		EpisodeRefDtlVO []episodeRefDtlVOArray=null;
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		Map essentialMap=new HashMap();
		
		try{
			UserVO userVO=getUserVO(_request);
			requisitionVOList=(List)_request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			
			//if only one candidate is selected then get todays previous refer detail of the candidate
			if(_fb.getSelectedCandidate()!=null && _fb.getSelectedCandidate().length==1){
				episodeRefDtlVO=new EpisodeRefDtlVO();
							
				for(int i=0;i<requisitionVOList.size();i++){
					if(i==Integer.parseInt(_fb.getSelectedCandidate()[0])){
						episodeRefDtlVO=new EpisodeRefDtlVO();
						HelperMethods.populate(episodeRefDtlVO,requisitionVOList.get(i));
						break;
					}
				}
			}
			
			essentialMap =MedicalExamInitiationDATA.getReferMappingList(_fb.getCertificateTypeID(),episodeRefDtlVO,userVO);
			
			referMappingVOList=(List)essentialMap.get(MedicalBoardConfig.MEDICAL_BOARD_REFER_MAPPING_VO_LIST);
			episodeRefDtlVOArray=(EpisodeRefDtlVO[])essentialMap.get(MedicalBoardConfig.EPISODE_REF_VO_LIST);
			
			//set the previous refer detail of the candidate if any
			setPreviousReferDetail(referMappingVOList, episodeRefDtlVOArray);
			
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.MEDICAL_BOARD_REFER_MAPPING_VO_LIST, referMappingVOList);
			
			objStatus.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,"","");	
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
			System.out.println("Inside Exception");
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
	 * get the detail of the Candidate Referral 
	 * @param _fb
	 * @param _request
	 */
	public static void getMBCandidateRefDetail(MedicalExamInitiationFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		List<EpisodeRefDtlVO> episodeRefDtlVOList=null;
		
		try{
			UserVO userVO=getUserVO(_request);
			
			episodeRefDtlVOList =MedicalExamInitiationDATA.getMBCandidateRefDetail(_fb.getPatCrNo(),userVO);
			
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.EPISODE_REF_VO_LIST, episodeRefDtlVOList);
			objStatus.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,e.getMessage(),"");	
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
			System.out.println("Inside Exception");
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
	 * get the detail of the Candidate Referral 
	 * @param _fb
	 * @param _request
	 */
	public static void getMBInvestigationEssential(MedicalExamInitiationFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		List<MedicalBoardInvestigationMappingVO> investigationMappingVOList=null;
		List sampleList=null;
		Map essentialMap=new HashMap();
		Map testSampleMap=new HashMap();
		List list=null;
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		MedicalBoardRequisitionVO []requisitionVOArray=null;
		try{
			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			requisitionVOList=(List)_request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			
			if(_fb.getSelectedCandidate()!=null){
				requisitionVOArray=new MedicalBoardRequisitionVO[_fb.getSelectedCandidate().length];						
				int k=0;
				for(int i=0;i<requisitionVOList.size();i++){
					if(k<_fb.getSelectedCandidate().length && i==Integer.parseInt(_fb.getSelectedCandidate()[k])){
						requisitionVOArray[k++]=requisitionVOList.get(i);
					}
				}
			}
			essentialMap =MedicalExamInitiationDATA.getMBInvestigationEssential(requisitionVOArray,_fb.getCertificateTypeID(),userVO);
			investigationMappingVOList=(List)essentialMap.get(MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST);
			sampleList=(List)essentialMap.get(MedicalBoardConfig.MB_INVESTIGATION_TEST_SAMPLE_LIST);

			// create the list of sample for each labtestcode that has been mapped in investigation mapping
			if(sampleList!=null){
				for(int i=0;i<investigationMappingVOList.size();i++){
					list=new ArrayList();
					for(int j=0;j<sampleList.size();j++){
						Entry entry=(Entry)sampleList.get(j);
						String testCode=investigationMappingVOList.get(i).getLabTestCode();//get the testCode from labTestCode (last four digit)
						//System.out.println(testCode+" ::  -->"+entry.getValue().substring(0,9));
						if(testCode.equals(entry.getValue().substring(0,9))){  // get testcode by getting the first four digit of sampleTestCode
							list.add(entry);
						}
					}
					testSampleMap.put(investigationMappingVOList.get(i).getLabTestCode(), list);
				}
			}
			
			List invReqVOList=(List)essentialMap.get(MedicalBoardConfig.INV_REQUISITION_DTL_VO_LIST);
			//if only one candidate is selected then get Investigation raised detail of the candidate
			if(requisitionVOArray[0].getIsInvestigationRaised().equals(MedicalBoardConfig.IS_INVESTIGATION_RAISED_YES))
				setPreviousInvDetail(investigationMappingVOList, invReqVOList);
				
			WebUTIL.setMapInSession(testSampleMap,_request);
			WebUTIL.setMapInSession(essentialMap,_request);
			//WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST, investigationMappingVOList);
			objStatus.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,"","");	
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
		catch(Exception e){
			System.out.println("Inside Exception");
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
	 * Save the candidate refer detail
	 * @param _fb
	 * @param _rq
	 */
	public static void saveCandidateReferDetail(MedicalExamInitiationFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		//HttpSession session=WebUTIL.getSession(_rq);
		EpisodeVO[] episodeVO=null;
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		EpisodeRefDtlVO[] episodeRefVO=null;
		MedicalBoardVisitDtlVO visitDetailVO[]=null;
		int i = 0;
		try{
			//get the list of the candidate
			requisitionVOList=(List)_rq.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			//get the department and unit of medical board
			String mbDepartmentUnitCode=(String)_rq.getSession().getAttribute(MedicalBoardConfig.MEDICAL_BOARD_DEPT_UNIT);
			
			//create the medical visit dtl VO for updating the isReferred flag
			visitDetailVO=new MedicalBoardVisitDtlVO[_fb.getSelectedCandidate().length];
			
			//creating episodeVO of the selected candidate
			episodeVO=new EpisodeVO[_fb.getSelectedCandidate().length];
			int k=0;
			for(;i<requisitionVOList.size();i++){
				if(k<_fb.getSelectedCandidate().length && i==Integer.parseInt(_fb.getSelectedCandidate()[k])){
					episodeVO[k]=new EpisodeVO(); 
					visitDetailVO[k]=new MedicalBoardVisitDtlVO();
					HelperMethods.populate(episodeVO[k],requisitionVOList.get(i));
					HelperMethods.populate(visitDetailVO[k],requisitionVOList.get(i));
					visitDetailVO[k].setIsReferred(MedicalBoardConfig.IS_REFERRED_YES);
					k++;
				}
			}
			
			//get the array of the selected department/unit to refer
			String referDeptArray[]=_fb.getReferDept().split("@");
			
			//creating the episode refer vo
			episodeRefVO=new EpisodeRefDtlVO[referDeptArray.length];
			
			for(i=0;i<episodeRefVO.length;i++){
				String referType=referDeptArray[i].split("#")[0];
				episodeRefVO[i]=new EpisodeRefDtlVO();
				//if refer type is department
				if(referType.equals(MedicalBoardConfig.MEDICAL_BOARD_REFER_TYPE_DEPARTMENT)){
					episodeRefVO[i].setToDepartmentCode(referDeptArray[i].split("#")[1]);
				}
				//if refer type is unit
				else{
					episodeRefVO[i].setToDepartmentCode(referDeptArray[i].split("#")[2].substring(0, 3));
					episodeRefVO[i].setToDepartmentUnitCode(referDeptArray[i].split("#")[2]);
				}
				episodeRefVO[i].setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);
				episodeRefVO[i].setSystemIPAddress(_rq.getRemoteAddr());
				//set the from department and unit
				if(!mbDepartmentUnitCode.equals("")){
					episodeRefVO[i].setFromDepartmentCode(mbDepartmentUnitCode.substring(0,3));
					episodeRefVO[i].setFromDepartmentUnitCode(mbDepartmentUnitCode);
				}	
			}
			
			
			MedicalExamInitiationDATA.saveCandidateReferDetail(episodeRefVO,episodeVO,visitDetailVO,getUserVO(_rq));
			objStatus.add(Status.DONE,"","Candidate Refered");
			
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}	
	}
	
	
	/**
	 * raise investigation of the test selected for the candidates
	 * @param _fb
	 * @param _rq
	 */
	public static void raiseCandidateInvestigation(MedicalExamInitiationFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		HttpSession session=_rq.getSession();
		EpisodeVO[] episodeVO=null;
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		List<EpisodeVO> episodeVOList=null;
		MedicalBoardVisitDtlVO visitDetailVO[]=null;
		List <Test> testVOList=new ArrayList<Test>();
		List <MedicalBoardInvestigationMappingVO> investigationMappingVOList=null;
		int i = 0;
		try{
			String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			//get the list of the candidate
			List<PatientDetailVO> patientDtlVOList =(List)session.getAttribute(MedicalBoardConfig.MEDICAL_BOARD_PATIENT_DTL_VO_LIST);
			requisitionVOList=(List)session.getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			investigationMappingVOList=(List)session.getAttribute(MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST);
			episodeVOList=(List)session.getAttribute(MedicalBoardConfig.MEDICAL_BOARD_EPISODE_VO_LIST);
			
			//creating episodeVO of the selected candidate
			episodeVO=new EpisodeVO[_fb.getSelectedCandidate().length];
			for(i=0;i<episodeVOList.size();i++){
				episodeVO[i]=new EpisodeVO(); 
				episodeVO[i]=episodeVOList.get(i);
			}
			
			//create the medical visit dtl VO for updating the isInvRaised flag
			visitDetailVO=new MedicalBoardVisitDtlVO[_fb.getSelectedCandidate().length];
			int k=0;
			for(i=0;i<requisitionVOList.size();i++){
				if(k<_fb.getSelectedCandidate().length && i==Integer.parseInt(_fb.getSelectedCandidate()[k])){
					//episodeVO[k]=new EpisodeVO(); 
					visitDetailVO[k]=new MedicalBoardVisitDtlVO();
					//HelperMethods.populate(episodeVO[k],requisitionVOList.get(i));
					HelperMethods.populate(visitDetailVO[k],requisitionVOList.get(i));
					visitDetailVO[k].setIsInvestigationRaised(MedicalBoardConfig.IS_INVESTIGATION_RAISED_YES);
					k++;
				}
			}
			
			//get the array of the selected labtestcode to raise investigation for the particular Test
			String labTestCodeArray[]=_fb.getLabTestCode().split("@");
			//get the array of the selected sampletestcode of the labtest
			String testSampleCodeArray[]=_fb.getSelectedSampleCode().split("@");
			
			for(i=0;i<investigationMappingVOList.size();i++){
				for(int j=0;j<labTestCodeArray.length;j++){
					String sampleCode=testSampleCodeArray[j];
					if(labTestCodeArray[j].equals(investigationMappingVOList.get(i).getLabTestCode())){
						//Test testVO=new Test();
						if(investigationMappingVOList.get(i).getTestType().equals("S")){
							/*SampleBasedTest sampleBasedTestVO=new SampleBasedTest();
							sampleBasedTestVO.setSelectedSampleCode(sampleCode);
							sampleBasedTestVO.setTestCode(investigationMappingVOList.get(i).getTestCode());
							sampleBasedTestVO.setTestType(investigationMappingVOList.get(i).getTestType());
							sampleBasedTestVO.setLaboratoryCode(investigationMappingVOList.get(i).getLabCode());
							sampleBasedTestVO.setLaboratoryName(investigationMappingVOList.get(i).getLabName());
							sampleBasedTestVO.setTestName(investigationMappingVOList.get(i).getLabTestName());
							sampleBasedTestVO.setPriority(MedicalBoardConfig.INV_RAISE_PRIORITY_NORMAL);//Priority normal
							testVOList.add(sampleBasedTestVO);*/
							
							SlideBasedTest slideBasedTestVO=new SlideBasedTest();
							slideBasedTestVO.setSelectedSpecimenCode(sampleCode.substring(5));
							slideBasedTestVO.setTestCode(investigationMappingVOList.get(i).getTestCode());
							slideBasedTestVO.setTestType(investigationMappingVOList.get(i).getTestType());
							slideBasedTestVO.setLaboratoryCode(investigationMappingVOList.get(i).getLabCode());
							slideBasedTestVO.setLaboratoryName(investigationMappingVOList.get(i).getLabName());
							slideBasedTestVO.setTestName(investigationMappingVOList.get(i).getLabTestName());
							slideBasedTestVO.setProposedTestDate(sysdate);
							slideBasedTestVO.setPriority(MedicalBoardConfig.INV_RAISE_PRIORITY_NORMAL);//Priority normal
							testVOList.add(slideBasedTestVO);
							
						}
						else if(investigationMappingVOList.get(i).getTestType().equals("I")){
							//testVO=new SlideBasedTest();
							SlideBasedTest slideBasedTestVO=new SlideBasedTest();
							slideBasedTestVO.setSelectedSpecimenCode(sampleCode.substring(5));
							slideBasedTestVO.setTestCode(investigationMappingVOList.get(i).getTestCode());
							slideBasedTestVO.setTestType(investigationMappingVOList.get(i).getTestType());
							slideBasedTestVO.setLaboratoryCode(investigationMappingVOList.get(i).getLabCode());
							slideBasedTestVO.setLaboratoryName(investigationMappingVOList.get(i).getLabName());
							slideBasedTestVO.setTestName(investigationMappingVOList.get(i).getLabTestName());
							slideBasedTestVO.setProposedTestDate(sysdate);
							slideBasedTestVO.setPriority(MedicalBoardConfig.INV_RAISE_PRIORITY_NORMAL);//Priority normal
							testVOList.add(slideBasedTestVO);
						}
						else{
							Test testVO=new Test();
							testVO.setTestCode(investigationMappingVOList.get(i).getTestCode());
							testVO.setTestType(investigationMappingVOList.get(i).getTestType());
							testVO.setLaboratoryCode(investigationMappingVOList.get(i).getLabCode());
							testVO.setLaboratoryName(investigationMappingVOList.get(i).getLabName());
							testVO.setTestName(investigationMappingVOList.get(i).getLabTestName());
							testVO.setProposedTestDate(sysdate);
							testVO.setPriority(MedicalBoardConfig.INV_RAISE_PRIORITY_NORMAL);//Priority normal
							testVOList.add(testVO);
						}	
					}
				}
			}
			
			MedicalExamInitiationDATA.raiseCandidateInvestigation(patientDtlVOList,episodeVO,testVOList,visitDetailVO,sysdate,getUserVO(_rq));
			objStatus.add(Status.DONE,"","Investigation Raised Successfully");
			
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}	
	}
	
	
	//set the candidate refer status as referred if candidate is referred earlier
	//set the acceptance date if the candidate has visit the referred department/unit
	public static void setPreviousReferDetail(List <MedicalBoardReferMappingVO> referMappingVOList,EpisodeRefDtlVO []episodeRefDtlVOArray){
		
		MedicalBoardReferMappingVO referMappingVO;
		if(referMappingVOList!=null && episodeRefDtlVOArray!=null){
			for(int i=0;i<referMappingVOList.size();i++){
				for(int j=0;j<episodeRefDtlVOArray.length;j++){
					referMappingVO=referMappingVOList.get(i);
					//if refer type is department
					if(referMappingVO.getReferType().equals(MedicalBoardConfig.MEDICAL_BOARD_REFER_TYPE_DEPARTMENT)){
						if(referMappingVO.getDepartmentCode().equals(episodeRefDtlVOArray[j].getToDepartmentCode()) 
								&& episodeRefDtlVOArray[j].getToDepartmentUnitCode()==null ){
							referMappingVO.setIsReferred(MedicalBoardConfig.IS_REFERRED_YES);
							referMappingVO.setReferVisitDate(episodeRefDtlVOArray[j].getEpisodeReferAcceptDate());
							referMappingVOList.set(i, referMappingVO);
						}
						//if candidate has visit the refer department/unit
						if(referMappingVO.getDepartmentCode().equals(episodeRefDtlVOArray[j].getToDepartmentCode()) 
								&& episodeRefDtlVOArray[j].getIsAccepted().equals("1")){
							referMappingVO.setIsReferred(MedicalBoardConfig.IS_REFERRED_YES);
							referMappingVO.setReferVisitDate(episodeRefDtlVOArray[j].getEpisodeReferAcceptDate());
							referMappingVOList.set(i, referMappingVO);
						}
					}
					//if refer type is unit
					else if(referMappingVO.getReferType().equals(MedicalBoardConfig.MEDICAL_BOARD_REFER_TYPE_UNIT)){
						if(referMappingVO.getDepartmentCode().equals(episodeRefDtlVOArray[j].getToDepartmentCode())
								&& referMappingVO.getDepartmentUnitCode().equals(episodeRefDtlVOArray[j].getToDepartmentUnitCode()) ){
							referMappingVO.setIsReferred(MedicalBoardConfig.IS_REFERRED_YES);
							referMappingVO.setReferVisitDate(episodeRefDtlVOArray[j].getEpisodeReferAcceptDate());
							referMappingVOList.set(i, referMappingVO);
						}
					}
				}
			}
		}
	}
	
	public static String getCertificateTypeName(String certificateTypeID,HttpServletRequest request){
		String name="";
		List certificateTypeList=(List)request.getSession().getAttribute(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE);
		for (Iterator iterator = certificateTypeList.iterator(); iterator
				.hasNext();) {
			Entry entry = (Entry) iterator.next();
			if(entry.getValue().equals(certificateTypeID)){
				name=entry.getLabel();
				break;
			}
		}
		return name;
	}


	
	/**
	 * get the detail of the Candidate's raised investigation 
	 * @param _fb
	 * @param _request
	 */
	public static void getMBCandidateInvDetail(MedicalExamInitiationFB fb,HttpServletRequest _request) {	
		
		Status objStatus=new Status();
		List<MBInvestigationRequisitionDetailVO> invRequisitionVOList=null;
		MBInvestigationRequisitionDetailVO invReqVO;
		try{
			UserVO userVO=getUserVO(_request);
			EpisodeVO episodeVO=new EpisodeVO();
			episodeVO.setPatCrNo(_request.getParameter("patCrNo"));
			episodeVO.setEpisodeCode(_request.getParameter("episodeCode"));
			episodeVO.setEpisodeVisitNo(_request.getParameter("episodeVisitNo"));
			
			invRequisitionVOList =MedicalExamInitiationDATA.getMBCandidateInvDetail(episodeVO,userVO);
			
			if(invRequisitionVOList==null){
				throw new HisRecordNotFoundException("No Investigation Detail Found");
			}
			for(int i=0;i<invRequisitionVOList.size();i++){
				invReqVO=new MBInvestigationRequisitionDetailVO();
				invReqVO=invRequisitionVOList.get(i);
				setTestStatus(invReqVO);
				invRequisitionVOList.set(i, invReqVO);
			}
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.INV_REQUISITION_DTL_VO_LIST, invRequisitionVOList);
			objStatus.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,e.getMessage(),"");	
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
			System.out.println("Inside Exception");
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
	
	//set the test status of the raised test by testStatusCode
	public static void setTestStatus(MBInvestigationRequisitionDetailVO invReqVO){
		int testStatusCode=Integer.parseInt(invReqVO.getTestStatusCode());
		String testStatus[]=InvestigationConfig.testStatus;
		invReqVO.setTestStatus(testStatus[testStatusCode-1]);
	}
		
	//set the previous investigation status of the candidate while raising the investigation
	//to display the status of the test
	public static void setPreviousInvDetail(List <MedicalBoardInvestigationMappingVO> invMappingVOList,List <MBInvestigationRequisitionDetailVO> invReqVOList){
		
		MedicalBoardInvestigationMappingVO invMappingVO;
		if(invMappingVOList!=null && invReqVOList!=null){
			for(int i=0;i<invMappingVOList.size();i++){
				for(int j=0;j<invReqVOList.size();j++){
					invMappingVO=invMappingVOList.get(i);
					if(invMappingVO.getLabTestCode().equals(invReqVOList.get(j).getLabTestCode())){
						invMappingVO.setIsInvRaised(MedicalBoardConfig.IS_INVESTIGATION_RAISED_YES);
						setTestStatus(invReqVOList.get(j));
						invMappingVO.setTestStatus(invReqVOList.get(j).getTestStatus());
						invMappingVOList.set(i, invMappingVO);
					}
				}
			}
		}
	}
	
public static void saveExternalRefer(MedicalExamInitiationFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		MedicalBoardExternalReferVO[] arrExternalReferVO=new MedicalBoardExternalReferVO[_fb.getSelectedCandidate().length];
		
		int i = 0;
		try{
			//get the list of the candidate
			requisitionVOList=(List)_rq.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
						
			int k=0;
			for(;i<requisitionVOList.size();i++){
				if(k<_fb.getSelectedCandidate().length && i==Integer.parseInt(_fb.getSelectedCandidate()[k])){
					arrExternalReferVO[k]=new MedicalBoardExternalReferVO();
					HelperMethods.populate(arrExternalReferVO[k],requisitionVOList.get(i));
					arrExternalReferVO[k].setReqID(requisitionVOList.get(i).getReqID());
					arrExternalReferVO[k].setExtReferTo(_fb.getExtReferTo());
					arrExternalReferVO[k].setReferReason(_fb.getReferReason());
					
					k++;
				}
			}
			
			
			
			MedicalExamInitiationDATA.saveExternalRefer(arrExternalReferVO,getUserVO(_rq));
			objStatus.add(Status.DONE,"","Candidate Refered");
			
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}	
	}

public static void getMBCandidateExtRefDetail(MedicalExamInitiationFB _fb,HttpServletRequest _request) {	
	
	Status objStatus=new Status();
	List<MedicalBoardExternalReferVO> externalReferVOList=null;
	
	try{
		UserVO userVO=getUserVO(_request);
		
		externalReferVOList =MedicalExamInitiationDATA.getMBCandidateExtRefDetail(_fb.getReqID(),userVO);
		
		WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.EPISODE_EXT_REF_VO_LIST, externalReferVOList);
		objStatus.add(Status.LIST);
		
	}
	catch(HisRecordNotFoundException e){
		System.out.println("Inside HisRecordNotFoundException");
		objStatus.add(Status.LIST,e.getMessage(),"");	
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
		System.out.println("Inside Exception");
		objStatus.add(Status.ERROR, "","Error");
		e.printStackTrace();
	}		
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
		
	}	
	
}
	

	
}//end class
