package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.BoardConfigurationVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.MbNewRegistrationDATA;
import medicalboard.transactions.controller.fb.MbNewRegistrationFB;
import registration.RegistrationConfig;


public class MbNewRegistrationUTL  extends ControllerUTIL{
	/**
	 * sets all new registration essentials for Medical Board
	 * @param _request -HttpServletRequest
	 */
		public static void setMsNewRegistrationEssentials(MbNewRegistrationFB _fb,HttpServletRequest _request){	
		
			Status objStatus=new Status();
			Map mp=new HashMap();
			List orgTypeList=new ArrayList();
			try{
			UserVO userVO=getUserVO(_request);
//			HttpSession session =_request.getSession();
			setSysdate(_request);
		    BoardConfigurationVO bConfigurationVO=new BoardConfigurationVO();
		    MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
		    List certificateType=new ArrayList();
		
			mp=MbNewRegistrationDATA.setMsNewRegistrationEssentials(userVO);
		
		
			WebUTIL.setMapInSession(mp,_request);
			
			
			for(int i=1;i<MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES.length;i++)
			{
				Entry entry=new Entry();
				entry.setLabel(MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES[i]);
				entry.setValue(String.valueOf(i));
				orgTypeList.add(entry);
			}
			
			WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES ,orgTypeList);
		
			isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL,getUserVO(_request));
			
			_fb.setIsActualDob("1");
			_fb.setPatAddCountryCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			_fb.setPatAddStateCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
		    _fb.setPatIsUrban(RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE);
		    _fb.setPatMaritalStatusCode(RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE);
			
		    bConfigurationVO=(BoardConfigurationVO)mp.get(MedicalBoardConfig.HMBT_BOARD_CONFIGURATION_DEPARTMENT_DETAIL);
		    
		    _fb.setDepartmentCode(bConfigurationVO.getDepartmentCode());
		    _fb.setDepartmentUnitCode(bConfigurationVO.getDepartmentUnitCode());
		    _fb.setRoomCode(bConfigurationVO.getRoomCode());
		    
		    
		     mCertificateTypeMstVOs=(MbCertificateTypeMstVO[])mp.get(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE);     
		        for(int i=0;i<mCertificateTypeMstVOs.length;i++)
		        {
		        	Entry entry=new Entry();
					entry.setLabel(mCertificateTypeMstVOs[i].getCertificateTypeName());
					entry.setValue(mCertificateTypeMstVOs[i].getCertificateTypeID());
					certificateType.add(entry);
		        }
		     
		        WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE_LIST ,certificateType);
		     
			objStatus.add(Status.NEW);
			objStatus.add(Status.INPROCESS);
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				//WebUTIL.setMapInSession(e.getEssentialMap(),_request);
				//Collection newCol=new ArrayList(); 
				//WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
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

		
		public static void getCheckList(MbNewRegistrationFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			Map mp=new HashMap();
		//	List orgTypeList=new ArrayList();
			String certificateType="";
			List checkListBy=new ArrayList();
			MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
			HttpSession session =_request.getSession();
			try{
				
				session.removeAttribute(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY);
			UserVO userVO=getUserVO(_request);
		
			setSysdate(_request);
		
			certificateType=_fb.getCertificateTypeID();
			mp=MbNewRegistrationDATA.getCheckList(certificateType,userVO);
		
			WebUTIL.setMapInSession(mp,_request);
			
			for(int i=1;i<MedicalBoardConfig.MEDICAL_BOARD_CHECKLIST_BY.length;i++)
			{
				Entry entry=new Entry();
				entry.setLabel(MedicalBoardConfig.MEDICAL_BOARD_CHECKLIST_BY[i]);
				entry.setValue(String.valueOf(i));
				checkListBy.add(entry);
			}
			
			WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.MEDICAL_BOARD_CHECKLIST_BY_LIST ,checkListBy);
			
			mCertificateTypeMstVOs=(MbCertificateTypeMstVO[])session.getAttribute(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE);
			
			   for(int i=0;i<mCertificateTypeMstVOs.length;i++)
			   {
				   if(certificateType.equals(mCertificateTypeMstVOs[i].getCertificateTypeID()))
				   {
//					   _fb.setBoardTypeID(mCertificateTypeMstVOs[i].getBoardTypeID());
//					   _fb.setWeekDay(mCertificateTypeMstVOs[i].getWeekDay());
					   _fb.setMaxRequest(mCertificateTypeMstVOs[i].getMaxRequest());
					   _fb.setMinRequest(mCertificateTypeMstVOs[i].getMinRequest());
				   }
			   }
			
			objStatus.add(Status.NEW);
			objStatus.add(Status.INPROCESS);
			
			
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW);
				objStatus.add(Status.INPROCESS);
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				
				//WebUTIL.setMapInSession(e.getEssentialMap(),_request);
				//Collection newCol=new ArrayList(); 
				//WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
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

		
		
        public static void getOrganizationDetail(MbNewRegistrationFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			String orgId="";
			MbOrganizationMstVO mOrganizationMstVO=new MbOrganizationMstVO();
//			HttpSession session =_request.getSession();
			try{
			
			UserVO userVO=getUserVO(_request);
		
			setSysdate(_request);
		
			orgId=_fb.getOrgID();
			mOrganizationMstVO=MbNewRegistrationDATA.getOrganizationDetail(orgId,userVO);
		
					   _fb.setOrgTypeID(mOrganizationMstVO.getOrgTypeID());
					   _fb.setOrgAddress(mOrganizationMstVO.getOrgAddress());
				  
			  objStatus.add(Status.NEW);
			  objStatus.add(Status.INPROCESS);
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW);
				objStatus.add(Status.INPROCESS);
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				
				//WebUTIL.setMapInSession(e.getEssentialMap(),_request);
				//Collection newCol=new ArrayList(); 
				//WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);
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

		
		
		
		
		/**
		 * saves New Patient Registration Details
		 * generates a String of Queue, Departement, unit and room 
		 * @param _request -HttpServletRequest
		 * @param _fb -NewRegistrationFB form bean
		 */
		public static void saveNewPatientRegistration(HttpServletRequest _request, MbNewRegistrationFB _fb){
			UserVO userVO=getUserVO(_request);
			//userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
			//EmpMasterVO empVO=null;
			Status objStatus =new Status();	
//			String patPriCat=_fb.getPatPrimaryCatCode();
			MedicalBoardRequisitionVO mRequisitionVO=new MedicalBoardRequisitionVO();
	
			try
			{
				if(_fb.getPatAddCountryCode()!=null && _fb.getPatAddCountryCode().equals(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE)){
					_fb.setPatAddStateName("");
				}
				else{
					_fb.setPatAddStateCode("");
				}
				if(!_fb.getPatAddStateCode().equalsIgnoreCase(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE)){	
					_fb.setPatAddCityLocCode("");				
					_fb.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_FALSE);
				}
			else{
				_fb.setPatAddCityLoc("");
				_fb.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_TRUE);
			}
			
		
			PatientVO patientVO = new PatientVO();	
			HelperMethods.populate(patientVO, _fb);
		
				
			EpisodeVO arrEpisodeVO=new EpisodeVO();		
		
				arrEpisodeVO.setDepartmentCode(_fb.getDepartmentCode());
				arrEpisodeVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
				arrEpisodeVO.setRoomCode(_fb.getRoomCode());
				
			
			patientVO.setSystemIPAddress(_request.getRemoteAddr());
			patientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD);	
//			HttpSession sess=WebUTIL.getSession(_request);
//			String sysdate=(String)sess.getAttribute(Config.SYSDATE);
			patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);
		
			if(WebUTIL.getSession(_request).getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY)!=null){
				//FormFile imageFile=(FormFile)WebUTIL.getSession(_req).getAttribute(RegistrationConfig.UPLOADED_FILE);
				patientVO.setImageFile((byte[])WebUTIL.getSession(_request).getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY));
			    patientVO.setImageFileName((String)WebUTIL.getSession(_request).getAttribute(RegistrationConfig.UPLOADED_FILE_NAME));
			    }
		
			
			// checklist//
			
			 int countCheckList=_fb.getSelectedCheckListId().length;
			 List lstCheckList=new ArrayList();
			  for(int i=0;i<countCheckList;i++)
			  {
				  MedicalBoardChecklistVO vo=new MedicalBoardChecklistVO();
				   vo.setChecklistID(_fb.getCheckListIdArray()[i]);
//				   vo.setChecklistBy(_fb.getCheckByIdArray()[i]);
				   vo.setRemarks(_fb.getRemarks()[i]);
				   lstCheckList.add(vo);
			  }
			
			
			
			// requsition //
			
			HelperMethods.populate(mRequisitionVO, _fb);
			
			
			
			EpisodeVO episodeVO= MbNewRegistrationDATA.registerNewPatient(patientVO, arrEpisodeVO,lstCheckList,mRequisitionVO,userVO);	
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, episodeVO);
			
			
			
			
			
			String str="";
			// String strQueueNo=episodeVO.getQueNo();
			 String strDeptName=episodeVO.getDepartment();
			 String strUnitName=episodeVO.getDepartmentUnit();
			 String strRoomName=episodeVO.getRoom();
			// String[] strFileName=new String[episodeVO.length];
		
			 
			 str +="<br>"+ "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> " +"Name"+ "&nbsp;&nbsp; ::"+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"</b></font>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
			 		+patientVO.getPatFirstName().toUpperCase()+ " "+ patientVO.getPatMiddleName().toUpperCase()+" "+patientVO.getPatLastName().toUpperCase()+"</font>"+ "<br>";
			 str +="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> " +"CR NO"+" ::"+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"</b></font>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+episodeVO.getPatCrNo()+"</font>";
			 str += "<table width='50%'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " +  "Dept Name::"+"</font></td>";
			// if(RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILE_NO_GENRATION_MANNUAL_TRUE) ||RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILE_NO_GENRATION_AUTO_TRUE) )
			//	 str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Queue No::"+"</font></td>";
			 str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Unit No::"+"</font></td>";
			 str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Room No::"+"</font></td>";
			// str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Queue No::"+"</font><br></td></tr></table>";
			
			   str += "<table width='50%'><tr>"+"<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strDeptName+"</font>"+"</td>";
			//   if(RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILE_NO_GENRATION_MANNUAL_TRUE) ||RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILENO_GENRATION_TYPE_AUTO) )
			//		 str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " +strFileName[i]+"</font></td>";
			   str += "<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strUnitName+"</font>"+"</td>";
			   str += "<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strRoomName+"</font>"+"</td>";            
			  // str += "<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strQueueNo+"</font>"+"<br>"+"</td>"+"</tr>"+"</table>";
			   
				objStatus.add(Status.DONE,str,"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+"Patient Registered "+"</font>"); 
				
			}
		//	String tmpFileName=RegistrationConfig.CARD_NEW_REGISTRATION+userVO.getSeatId();
		//	RegistrationsSlipFactoryI registrationsSlipFactoryI=new RegistrationsSlipFactory();
		//	RegistrationsSlip  registrationSlip=registrationsSlipFactoryI.createRegSlip(RegistrationConfig.NEW_REGISTRATION_SLIP,patientVO,episodeVO,tmpFileName);
			//registrationSlip.genrateSlip();
			
		////Changing the print method moving it to NewPatientRegistrationSlip//
			//printRegistrationlabel(preapareSlip(episodeVO,patientVO,_fb, _request), tmpFileName);
		//	for(int k=0;k<episodeVO.length;k++)
		//	{System.out.println("dept"+episodeVO[k].getDepartment());
		//			}
			//NewPatientRegistrationSlip.patientOPDCard(preapareSlip(episodeVO,patientVO,_fb, _request), tmpFileName);
			/////////
		
			/*catch(HisRecordNotFoundException e){{
				System.out.println("Inside HisRecordNotFoundException");			
				objStatus.add(Status.UNSUCESSFULL,"","Recor not found")
			}
				
			}*/
			catch(HisInvalidTokenNumberException e){
				System.out.println("Inside HisInvalidTokenNumberException");			
				objStatus.add(Status.UNSUCESSFULL,"","INVALID TOKEN NUMBER");	
			}
			catch(HisUpdateUnsuccesfullException e){
				System.out.println("Inside HisUpdateUnsuccesfullException");			
				objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
			}		
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,"","Registration Failed");		
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE,"","Application Execution Error");
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
		
		
		
		
}
