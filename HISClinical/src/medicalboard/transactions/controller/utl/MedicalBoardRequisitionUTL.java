package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
//import hisglobal.utility.filetransfer.FileTransfer;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbLocationEligiblityMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardBillingVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import investigation.InvestigationStaticConfigurator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.CertificateHandoverDATA;
import medicalboard.transactions.controller.data.MedicalBoardRequisitionDATA;
import medicalboard.transactions.controller.fb.MedicalBoardRequisitionFB;
import registration.RegistrationConfig;
import registration.controller.util.PatDetailUTIL;


public class MedicalBoardRequisitionUTL  extends ControllerUTIL{
	/**
	 * sets all new patient List for Medical Board Requisition
	 * @param _request -HttpServletRequest
	 */
	public static void getPatientList(MedicalBoardRequisitionFB _fb,HttpServletRequest _request){	

		Status objStatus=new Status();
//		Map mp=new HashMap();
		List<PatientVO> patientVOList=new ArrayList();
		try{
			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			patientVOList=MedicalBoardRequisitionDATA.getPatientListForRequisition(userVO);
			WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.ESSENTIAL_PATIENT_LIST_FOR_REQ ,patientVOList);

			objStatus.add(Status.LIST);

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


	public static void getPatientForRequisitionByCrNo(MedicalBoardRequisitionFB _fb,HttpServletRequest _request){	

		Status objStatus=new Status();
//		PatientVO patientVO=null;
		try{
			UserVO userVO=getUserVO(_request);
//			patientVO=
			MedicalBoardRequisitionDATA.getPatientForRequisitionByCrNo(_fb.getPatCrNo(),userVO);

			getCertificateTypeForRequisition(_fb, _request);

		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.LIST,e.getMessage(),"");	
			objStatus.add(Status.NEW);	
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


	public static void getCertificateTypeForRequisition(MedicalBoardRequisitionFB _fb,HttpServletRequest _request){	

		Status objStatus=new Status();
		Map mp=new HashMap();
//		List<PatientVO> patientVOList=new ArrayList();
		List <Entry> certificateTypeList=new ArrayList<Entry>();
		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;

		try{
			PatDetailUTIL.getPatientDtlByCrno(_fb, _request);


			


			PatientVO patientVO=(PatientVO)_request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			if(patientVO!=null){
				if(patientVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED)){
					objStatus.add(Status.TRANSINPROCESS,"","Patient is not an OPD patient");
				}
				else{
					UserVO userVO=getUserVO(_request);
					mp=MedicalBoardRequisitionDATA.getCertificateTypeForRequisition(userVO);
					mCertificateTypeMstVOs=(MbCertificateTypeMstVO[])mp.get(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE_LIST);
					if(mCertificateTypeMstVOs!=null){
						for(int i=0;i<mCertificateTypeMstVOs.length;i++){
							Entry entry=new Entry();
							entry.setValue(mCertificateTypeMstVOs[i].getCertificateTypeID());
							entry.setLabel(mCertificateTypeMstVOs[i].getCertificateTypeName());
							certificateTypeList.add(entry);

						}
					}

					_fb.setPatCrNo(_fb.getPatCrNo());
					WebUTIL.setMapInSession(mp, _request);
					WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE ,certificateTypeList);
					objStatus.add(Status.TRANSINPROCESS);
					
					PatientImageDtlVO patientImageDtlVOList=new PatientImageDtlVO();
					List byteArray=new ArrayList();
					patientImageDtlVOList=CertificateHandoverDATA.getPatientImageDtlByCrNo(_fb.getPatCrNo(),getUserVO(_request));
					String filepath=patientImageDtlVOList.getFilePath().replace("\\","/")+"/" + patientImageDtlVOList.getFileNo();
					System.out.println("filepath :"+filepath);
					byteArray.add(HelperMethods.getByteArrayOfImage(filepath));
					_fb.setPatCrNo(((PatientImageDtlVO)patientImageDtlVOList).getPatCrNo());
					byte[] objByte=null;
					if(byteArray.size() >=1)
					 objByte = (byte[])byteArray.get(0);
					WebUTIL.setAttributeInSession(_request,RegistrationConfig.UPLOADED_FILE_AS_ARRAY, objByte);
				}
			}
			else{
				objStatus.add(Status.LIST,"","Patient Detail Not Found");
			}

		}
		catch(HisRecordNotFoundException e){

			if(e.getMessage().equals("No Image Found"))
				e.printStackTrace();
			else
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.TRANSINPROCESS);
				//objStatus.add(Status.RECORDFOUND);
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				e.printStackTrace();
			}
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
		catch(FileNotFoundException e){
			objStatus.add(Status.NEW,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(IOException e){
			objStatus.add(Status.NEW,e.getMessage(),"");	
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}


	public static void getEssentialForRequisition(MedicalBoardRequisitionFB _fb,HttpServletRequest _request){	

		Status objStatus=new Status();
		Map mp=new HashMap();
//		String certificateType="";
		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
		HttpSession session =_request.getSession();
		String isBillingApply="0";
		List <MedicalBoardBillingVO> billingVO=null;
		MbCertificateTypeMstVO certificateTypeMstVO=null;
//		MbLocationEligiblityMstVO locationEligiblityMstVO=null;
		List <MbLocationEligiblityMstVO>locationEligiblityMstVOList=null;
		List <Entry> organisationTypeList=new ArrayList<Entry>();
		String isDataCorrect="true";
		_fb.setIsBillingDone("0");
		_fb.setExamDate("");
		try{

			PatientVO patientVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
			session.removeAttribute(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY);
			UserVO userVO=getUserVO(_request);
//			certificateType=_fb.getCertificateTypeID();
			mCertificateTypeMstVOs=(MbCertificateTypeMstVO[])session.getAttribute(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE_LIST);
			if(mCertificateTypeMstVOs!=null){
				for(int i=0;i<mCertificateTypeMstVOs.length;i++){
					if(mCertificateTypeMstVOs[i].getCertificateTypeID().equals(_fb.getCertificateTypeID())){
						certificateTypeMstVO=mCertificateTypeMstVOs[i];
						_fb.setReqBy(mCertificateTypeMstVOs[i].getRequestBy());
						if(!mCertificateTypeMstVOs[i].getIsBillApply().trim().equals("0")){
							isBillingApply="1";
						}
						certificateTypeMstVO.setIsBillApply(isBillingApply);
						_fb.setDepartmentUnitCode(mCertificateTypeMstVOs[i].getDepartmentUnitCode());

						break;
					}
				}
			}
			mp=MedicalBoardRequisitionDATA.getEssentialForRequisition(certificateTypeMstVO,_fb.getPatCrNo(),userVO);
			boolean isDistrictMatches=false;
			if(certificateTypeMstVO.getLocationBound().equals(MedicalBoardConfig.IS_LOCATIONBOUND_YES)){
				locationEligiblityMstVOList=(List)mp.get(MedicalBoardConfig.LOCATION_ELIGIBLITY_MSTVO_LIST);
				for(int i=0;i<locationEligiblityMstVOList.size();i++){
					if(locationEligiblityMstVOList.get(i).getDistrictId().equals(patientVO.getPatAddress().getPatAddDistrictCode())){
						isDistrictMatches=true;
						break;
					}
					else{
						isDistrictMatches=false;
					}
				}
			}
			for(int i=1;i<MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES.length;i++){
				Entry entry=new Entry();
				entry.setLabel(MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES[i]);
				entry.setValue(String.valueOf(i));
				organisationTypeList.add(entry);
			}

			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.RECORDFOUND);
			if(certificateTypeMstVO.getLocationBound().equals(MedicalBoardConfig.IS_LOCATIONBOUND_YES)){
				if(!isDistrictMatches){
					isDataCorrect="false";
					_fb.setIsDataCorrect(isDataCorrect);
					_fb.setCertificateTypeID("-1");
					//objStatus.add(Status.RECORDFOUND,"","Location doesnot matches. Cannot Continue");
					throw new HisDataAccessException("Location doesnot matches. Cannot Continue");
				}
			}	
			billingVO=(List)mp.get(MedicalBoardConfig.BILLING_VO);
			if(billingVO!=null && billingVO.size()==0){
				billingVO=null;
			}
			boolean flag=setBillingDetails(billingVO, _fb,_request);
			if(!flag){
				_fb.setIsBillingDone("0");
				isDataCorrect="false";
				_fb.setIsDataCorrect(isDataCorrect);
				_fb.setCertificateTypeID("-1");
				throw new HisDataAccessException("Record has been already added for the Certificate Type");
			}

			if(billingVO==null && isBillingApply.equals("1")){
				_fb.setIsBillingDone("0");
				isDataCorrect="false";
				_fb.setIsDataCorrect(isDataCorrect);
				_fb.setCertificateTypeID("-1");
				throw new HisDataAccessException("Billing Not Done Cannot Continue");
			}

			if(certificateTypeMstVO.getMinAge()!=null && !certificateTypeMstVO.getMinAge().equals("0")){
				String patientAgeUnit=patientVO.getPatAgeUnit();
				int patientAge=0;
				if(patientAgeUnit.startsWith("Y")){
					patientAge=Integer.parseInt(patientVO.getPatAge());
				}
				if(patientAge< Integer.parseInt(certificateTypeMstVO.getMinAge()) || patientAge > Integer.parseInt(certificateTypeMstVO.getMaxAge()) ){
					isDataCorrect="false";
					_fb.setIsDataCorrect(isDataCorrect);
					_fb.setCertificateTypeID("-1");
					throw new HisDataAccessException("Age Should be between " + certificateTypeMstVO.getMinAge() + "  and " +certificateTypeMstVO.getMaxAge() +" Years");
				}	
			}


			mp.put(MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES, organisationTypeList);
			WebUTIL.setMapInSession(mp, _request);


		}
		catch(HisDuplicateRecordException e){
			System.out.println("Inside HisDuplicateRecordException");
			objStatus.add(Status.TRANSINPROCESS);
			//objStatus.add(Status.RECORDFOUND);
			isDataCorrect="false";
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}		
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.TRANSINPROCESS);
			//objStatus.add(Status.RECORDFOUND);
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
			_fb.setIsDataCorrect(isDataCorrect);
		}	
	}


	public static void getScheduleList(MedicalBoardRequisitionFB _fb,HttpServletRequest _request){	

		Status objStatus=new Status();
		List<MedicalBoardRequisitionVO> scheduleList=null;
		MbCertificateTypeMstVO certificateTypeMstVO=new MbCertificateTypeMstVO();
		try{

			UserVO userVO=getUserVO(_request);
			certificateTypeMstVO.setCertificateTypeID(_request.getParameter("certificateType"));
			certificateTypeMstVO.setDepartmentUnitCode(_request.getParameter("unitCode"));
			_fb.setIndex(Integer.parseInt(_request.getParameter("index")));

			scheduleList=MedicalBoardRequisitionDATA.getScheduleList(certificateTypeMstVO,userVO);

			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.REQUISITION_SCHEDULE_LIST, scheduleList);
			objStatus.add(Status.LIST);
			//objStatus.add(Status.RECORDFOUND);


		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.RECORDFOUND);
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


	public static void getCIDNoList(MedicalBoardRequisitionFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstCIDs = null;
		MbCertificateTypeMstVO certificateTypeMstVO = new MbCertificateTypeMstVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			certificateTypeMstVO.setCertificateTypeID(_request.getParameter("certificateType"));
			certificateTypeMstVO.setDepartmentUnitCode(_request.getParameter("unitCode"));

			lstCIDs = MedicalBoardRequisitionDATA.getCIDNoList(certificateTypeMstVO, userVO);

			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.REQUISITION_FILE_CID_LIST, lstCIDs);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	public static void getOrganizationDetail(MedicalBoardRequisitionFB _fb,HttpServletRequest _request){	

		Status objStatus=new Status();
		String orgId="";
		MbOrganizationMstVO mOrganizationMstVO=new MbOrganizationMstVO();
//		HttpSession session =_request.getSession();
		try{

			UserVO userVO=getUserVO(_request);

			orgId=_fb.getOrgID();
			if(!orgId.equals(MedicalBoardConfig.ORGANISATION_ID_OTHER)){
				mOrganizationMstVO=MedicalBoardRequisitionDATA.getOrganizationDetail(orgId,userVO);

				_fb.setOrgTypeID(mOrganizationMstVO.getOrgTypeID());
				_fb.setOrgAddress(mOrganizationMstVO.getOrgAddress());
				_fb.setOrgName(mOrganizationMstVO.getOrgName());

			}    
			//to retain the form values if checkbox is selected
			retainFormValues(_request, _fb);

			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.RECORDFOUND);
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.RECORDFOUND);
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


	/**
	 * saves Medical Board Requisition Detail
	 * @param _request
	 * @param _fb
	 */
	public static void saveMedicalBoardRequisition(HttpServletRequest _request, MedicalBoardRequisitionFB _fb){
		UserVO userVO=getUserVO(_request);
		Status objStatus =new Status();	
//		String patPriCat=_fb.getPatPrimaryCatCode();
		List<MedicalBoardChecklistVO> lstCheckList=new ArrayList<MedicalBoardChecklistVO>();
		MedicalBoardRequisitionVO mRequisitionVO=new MedicalBoardRequisitionVO();
		List <MedicalBoardBillingVO> billingVOList=null;
		MedicalBoardBillingVO billingVO=null;
		try
		{
			billingVOList=(List)_request.getSession().getAttribute(MedicalBoardConfig.BILLING_VO);
			if(billingVOList!=null){
				if(billingVOList.size()==1){
					billingVO=billingVOList.get(0);
				}
				else{
					for(int i=0;i<billingVOList.size();i++){
						if(billingVOList.get(i).getBillNo().equals(_fb.getBillNo())){
							billingVO=billingVOList.get(i);
							break;
						}
					}
				}
			}	
			// checklist//
			if(_fb.getSelectedCheckListId()!=null){
				int countCheckList=_fb.getSelectedCheckListId().length;

				for(int i=0;i<countCheckList;i++)
				{
					MedicalBoardChecklistVO vo=new MedicalBoardChecklistVO();
					vo.setChecklistID(_fb.getCheckListIdArray()[i]);
					//vo.setChecklistBy("1");
					vo.setRemarks(_fb.getRemarks()[i]);
					lstCheckList.add(vo);
				}

			}
			// requsition //
			if(_fb.getReqBy().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL)){
				_fb.setOrgID("");
				_fb.setOrgName("");
				_fb.setOtherOrgName("");
				_fb.setOrgAddress("");
				_fb.setOrgTypeID("");
			}	
			if(_fb.getReqBy().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ANYONE)){
				if(_fb.getRequestFrom().equals(MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL)){
					_fb.setOrgID("");
					_fb.setOrgName("");
					_fb.setOtherOrgName("");
					_fb.setOrgAddress("");
					_fb.setOrgTypeID("");
				}	
			}


			String maxSerialNo=MedicalBoardRequisitionDATA.getMaxCRNOPatientImage(_fb.getPatCrNo(),getUserVO(_request));
			if(maxSerialNo == null || "".equals(maxSerialNo))
				maxSerialNo=""+0;

			byte[] byteArrayList=(byte[])_request.getSession().getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
			if(byteArrayList!=null && byteArrayList.length>0)
			{
				int i = Integer.parseInt(maxSerialNo) + 1;
				//String fileName=_fb.getPatCrNo()+"#"+ i ; String fileName=_fb.getPatCrNo()+"_"+ i+ ".png";
				//String fileName = _fb.getPatCrNo() + Config.PATIENT_IMAGE_FILE_STORAGE_SEPARATOR_NAME_N_SNO
				//	+ Integer.toString(i) + Config.PATIENT_IMAGE_FILE_STORAGE_EXT;
				//String fileSubFolder = ""; // -------
				//String filepath = Config.TARGET_FOLDER_PATIENT_IMAGE + fileSubFolder;

				//FileTransfer fileTransferUtil = new FileTransfer(fileName, fileSubFolder, Config.TARGET_FOLDER_PATIENT_IMAGE, byteArrayList);
				//fileTransferUtil.setAppPath(Config.PATH_APPLICATION_SERVER_FILE_STORAGE_WINDOWS, Config.PATH_APPLICATION_SERVER_FILE_STORAGE_LINUX);
				//fileTransferUtil.setFTPPath(InvestigationStaticConfigurator.resultprintingftpaddress, InvestigationStaticConfigurator.patientfileftpusername, InvestigationStaticConfigurator.patientfileftppassword);		
				
				/*if(!fileTransferUtil.saveFile())
				{
					throw new HisApplicationExecutionException(fileTransferUtil.getErrorMessage());
				} */

				PatientImageDtlVO patimageVO=new PatientImageDtlVO();
				patimageVO.setSerialNo(""+maxSerialNo);
				//patimageVO.setFileNo(fileName);
				patimageVO.setIsValid(Config.IS_VALID_ACTIVE);
				//patimageVO.setFilePath(filepath);
				patimageVO.setPatCrNo(_fb.getPatCrNo());
				patimageVO.setSeatId(userVO.getSeatId());
				patimageVO.setSystemIPAddress(userVO.getSeatId());
				patimageVO.setIsImageDefault("1");

				MedicalBoardRequisitionDATA.savePatientImage(patimageVO,getUserVO(_request));

				objStatus.add(Status.LIST,"","Images Saved Successfully");
			}
			//			else
			//			{
			//				objStatus.add(Status.LIST,"","Images Not Saved");
			//			}


			HelperMethods.populate(mRequisitionVO, _fb);
			mRequisitionVO.setRequestFrom(_fb.getReqBy());
			mRequisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
			if(_fb.getOrgID().equals(MedicalBoardConfig.ORGANISATION_ID_OTHER)){
				mRequisitionVO.setOrgName(_fb.getOtherOrgName());
			}
			else{
				mRequisitionVO.setOrgName(_fb.getOrgName());
			}

			MedicalBoardRequisitionDATA.saveMedicalBoardRequisition(lstCheckList,mRequisitionVO,billingVO,userVO);	
			objStatus.add(Status.DONE,"","Record Saved Successfully");	

		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,"",e.getMessage());		
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"",e.getMessage());
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	

	}


	public static void retainFormValues(HttpServletRequest request,MedicalBoardRequisitionFB _fb)
	{
		MedicalBoardChecklistVO[] checklistDtlVO=null;
		String remarks[]=null;

		checklistDtlVO=(MedicalBoardChecklistVO[])request.getSession().getAttribute(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY);
		//create an array of checkbox of the size of the old content VO.
		if(checklistDtlVO!=null){
			String checkBoxList[]=new String[checklistDtlVO.length ];

			int count=0;
			remarks=new String[checkBoxList.length];
			if(_fb.getSelectedCheckListId()!=null){
				int k;
				for(count=0,k=0;count<checkBoxList.length;count++){
					//if index of checkbox is same as other form field's index
					if(k<_fb.getSelectedCheckListId().length && 
							(count==Integer.parseInt(_fb.getSelectedCheckListId()[k].split("#")[0]))){
						//checkBoxList[count]=_fb.getSelectedContent()[count];
						remarks[count]=_fb.getRemarks()[k];
						k++;
					}
					//if the index of the checkbox is not same as other fields index 
					else{
						remarks[count]="";
					}
				}
			}
			//if no checkbox is selected then populate the field with the default value
			else{
				for(count=0;count<checkBoxList.length;count++){
					remarks[count]="";
				}
			}
			//set the new arrays of the field on the form
			_fb.setRemarks(remarks);
		}	

	}


	public static boolean setBillingDetails(List <MedicalBoardBillingVO> billingVOList,MedicalBoardRequisitionFB fb,HttpServletRequest request){
		List <Entry> billNoList=new ArrayList<Entry>();
		boolean flag=false;
		if(billingVOList==null){
			flag=true;
		}
		else{
			fb.setIsBillingDone("1");
			Entry entry=null;
			for(int i=0;i<billingVOList.size();i++){
				entry=new Entry();
				entry.setLabel(billingVOList.get(i).getBillNo());
				entry.setValue(billingVOList.get(i).getBillNo());
				billNoList.add(entry);
				if(Integer.parseInt(billingVOList.get(i).getRemainedQuantity())>0){
					flag=true;
				}

			}
			/*entry=new Entry();
				entry.setValue("909090");
				entry.setLabel("909090");
				billNoList.add(entry);*/
			WebUTIL.setAttributeInSession(request, MedicalBoardConfig.BILL_NO_LIST, billNoList);
		}
		return flag;

	}

}
