package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisPatientStillUnknownException;
import registration.transactions.controller.actionsupport.ChangeTreatmentCategorySUP;
import registration.transactions.controller.data.ChangeTreatmentCategoryDATA;
import vo.registration.ChangeTreatmentCategoryVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;

public class ChangeTreatmentCategoryUTIL extends ControllerUTIL {

	
	public static void getEssentials(ChangeTreatmentCategorySUP objSUP_p,
			HttpServletRequest objRequest_p) {

		Status objStatus = new Status();
		Map essentialMap = new HashMap();

		try {

			essentialMap = ChangeTreatmentCategoryDATA.getPatientReferralEssentials(
					objSUP_p.getPatCrNo(), getUserVO(objRequest_p));
			WebUTIL.setMapInSession(essentialMap, objRequest_p,"ChangeTreatmentCategoryACTION");

			// objStatus.add(Status.NEW);

		} catch (HisRecordNotFoundException e) {
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			essentialMap = e.getEssentialMap();

			WebUTIL.setMapInSession(essentialMap, objRequest_p,"ChangeTreatmentCategoryACTION");
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
		} catch (Exception e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		} finally {
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
	}

	public static void setPatientDtlByCrno(ChangeTreatmentCategorySUP objSUP_p,HttpServletRequest objRequest_p) {
		Status status = new Status();
		EpisodeVO[] arrOpenEpisodeVO;
		EpisodeVO admittedPatientVO;
		try {
			UserVO userVO =getUserVO(objRequest_p);
			PatientVO objPatientVO=new PatientVO();
			objPatientVO.setPatCrNo(objSUP_p.getPatCrNo());
			setAllTreatmentCategoryEssentials(objSUP_p,objRequest_p);
			
			//objPatientVO=ChangeTreatmentCategoryDATA.getPatientDtl(objPatientVO,userVO);
			PatDetailUTIL.getPatientDtlByCrno(objSUP_p, objRequest_p);
			objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			if(objPatientVO.getPatIsMerged()!=null && objPatientVO.getPatIsMerged().equals("2"))
			{
				objSUP_p.setAfterGo("0");
				objSUP_p.setErrorMessage("CR No is Not Valid, Already Merged with CR No. " +objPatientVO.getPatMergedMainCrNO());
				throw new HisRecordNotFoundException("CR No is Not Valid, Already Merged with CR No. " +objPatientVO.getPatMergedMainCrNO() );
			}
			String crNo=objPatientVO.getPatCrNo();
			if(objSUP_p.getHmode().equals("GETPATDTL"))
			{
				if(objPatientVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_ADMITTED_IPD))
					objSUP_p.setIsIpdFlag(RegistrationConfig.YES);
				else
					objSUP_p.setIsIpdFlag(RegistrationConfig.NO);
			}
			
			if(objSUP_p.getIsIpdFlag().equals(RegistrationConfig.YES))
				{
				admittedPatientVO=ChangeTreatmentCategoryDATA.getPatientAdmittedEpisodes(crNo,userVO);
				admittedPatientVO.setPatPrimaryCatCode(objPatientVO.getPatCatCode());
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ADMITTED_PATIENT_VO,admittedPatientVO);
				}
			//else
			///{
				arrOpenEpisodeVO=ChangeTreatmentCategoryDATA.getAllOpenEpisodesVisitedToday(crNo, userVO);
				for(int i=0;i<arrOpenEpisodeVO.length;i++){
				if(arrOpenEpisodeVO[i].getPatSecondaryCatCode()==null)
					arrOpenEpisodeVO[i].setPatSecondaryCatCode("-1");
			}										
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPEN_EPISODE_VO,arrOpenEpisodeVO);
			//}
			String ipdFlag =objSUP_p.getIsIpdFlag();
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.IPD_FLAG,ipdFlag);
			
			objSUP_p.setGoFlag("1");
			objSUP_p.setAfterGo("1");
		} catch (HisRecordNotFoundException e) {
			objSUP_p.setGoFlag("0");
			objSUP_p.setErrorMessage(e.getMessage());
			status.add(Status.NEW, e.getMessage(), "");
		} catch (HisPatientStillUnknownException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.UNSUCESSFULL, e.getMessage(), "");
		} catch (HisDataAccessException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_DA, e.getMessage(), "");
		} catch (HisApplicationExecutionException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		} finally {
			WebUTIL.setStatus(objRequest_p, status);

		}
	}
	public static void setAllTreatmentCategoryEssentials(ChangeTreatmentCategorySUP _fb, HttpServletRequest _request){	
		System.out.println("inside setAllChangePrimaryCategoryEssentials()");
		Status objStatus=new Status();
		List secondaryCategory=new ArrayList();
		String[] catAndExpiryDayArray;
		String secCatAndExpiry="";
		
		List secondaryCategoryApplicableServices = new ArrayList();
		String[] applicableServicesArray;
		//String applicableServices = "";
		try{
			Map mp=ChangeTreatmentCategoryDATA.getTreatmentCategoryChangeInitials(getUserVO(_request));
			List secCatAndExpiryList=(List)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS);
			
			Iterator itr=secCatAndExpiryList.iterator();
			catAndExpiryDayArray=new String[secCatAndExpiryList.size()];
			while(itr.hasNext())
			{
				
				Entry entryObj=(Entry)itr.next();;
				secCatAndExpiry=secCatAndExpiry+entryObj.getValue()+":";
				entryObj.setValue(entryObj.getValue().substring(0, entryObj.getValue().indexOf("#")));
				secondaryCategory.add(entryObj);
				//i++;
			}
		
			_fb.setSecCatCodeAndExpiryDay(secCatAndExpiry);
			//catAndExpiryDayArray=(String[])secCatAndExpiryList.toArray();
			
			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY, secondaryCategory);
			
			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS, secCatAndExpiry);
			
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS,secCatAndExpiry);
			
			System.out.println("MAP:::"+mp);
			
			/*Added by Vasu dated on 17.April.18*/
			List secApplicableServicesList=(List)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_APPLICABLE_SERVICES);
			Iterator itr1=secApplicableServicesList.iterator();
			applicableServicesArray = new String[secApplicableServicesList.size()];
			while(itr1.hasNext())
			{
				
				Entry entryObj=(Entry)itr1.next();;
				//applicableServices=applicableServices+entryObj.getValue()+":";
				//entryObj.setValue(entryObj.getValue().substring(0, entryObj.getValue().indexOf("#")));
				secondaryCategoryApplicableServices.add(entryObj);
				//i++;
			}
			//_fb.setApplicableServices(applicableServices);
			
			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_APPLICABLE_SERVICES, secondaryCategoryApplicableServices);
			
            //mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_APPLICABLE_SERVICES, applicableServices);
			
			//WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_APPLICABLE_SERVICES,applicableServices);
			//End Vasu
			WebUTIL.setMapInSession(mp,_request,"ChangeTreatmentCategoryACTION");
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}		
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	

	

	public static void saveSecondaryCategoryChange(ChangeTreatmentCategorySUP objSUP_p,
			HttpServletRequest _rq) {

		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = WebUTIL.getSession(_rq);
		EpisodeVO[] arrOpenEpisodeVO = null;
		PatientVO patientVO = null;
		String depts="";
		ChangeTreatmentCategoryVO[] TreatmentCategoryVO =null;
		ChangeTreatmentCategoryVO[] TreatmentCategoryRevokeVO =null;
		EpisodeVO admittedPatientEpisodeVO;
		//ChangeTreatmentCategoryVO[] TreatmentCategoryVO =null;
		//ChangeTreatmentCategoryVO[] TreatmentCategoryRevokeVO =null;
		
		boolean flag = false;
		
		try {
			patientVO = (PatientVO) _rq.getSession().getAttribute(
					RegistrationConfig.PATIENT_VO);
			if(!objSUP_p.getIsIpdFlag().equals("1"))
			{
				
			arrOpenEpisodeVO = (EpisodeVO[]) _rq
					.getSession()
					.getAttribute(
							RegistrationConfig.ARR_OPEN_EPISODE_VO);
			//String[] selectedEpisodes = objSUP_p.getSelectEpisode();	
			String[] newSecCatselectedEpisodes = objSUP_p.getHiddenNewSecCatCode();
			String[] remarks=objSUP_p.getArrRemarks();
			String[] validUpto=objSUP_p.getArrValidUpto(); 
			String[] expiryDate=objSUP_p.getCategoryExpiryDate();
			String[] catClientCode=objSUP_p.getCatClientCode();
			//String[] episodeVisitNo=objSUP_p.getSelectEpisodeVisitNo();
			
			if(objSUP_p.getSelectEpisode()!=null && objSUP_p.getSelectEpisode().length!=0)
			{
				
				TreatmentCategoryVO = new ChangeTreatmentCategoryVO[objSUP_p.getSelectEpisode().length];
				for(int i=0,k=0,l=0,m=0;i<TreatmentCategoryVO.length;i++,k++,m++,l++)
				{
					int x=0;
					//int x=Integer.parseInt(objSUP_p.getSelectEpisode()[i]);
					for(int z=0;z<arrOpenEpisodeVO.length;z++){
						if(arrOpenEpisodeVO[z].getEpisodeCode().equals(objSUP_p.getSelectEpisode()[i]))
						{
							 x=z;
						break;
						}
					}
					TreatmentCategoryVO[i]=new ChangeTreatmentCategoryVO();
					HelperMethods.populate(TreatmentCategoryVO[i], patientVO);
					TreatmentCategoryVO[i].setEpisodeCode(arrOpenEpisodeVO[x].getEpisodeCode());
					TreatmentCategoryVO[i].setEpisodeVisitNo(arrOpenEpisodeVO[x].getEpisodeVisitNo());
					TreatmentCategoryVO[i].setPatNewSecondaryCatCode(newSecCatselectedEpisodes[l]);
					TreatmentCategoryVO[i].setRemarks(remarks[i]);
					TreatmentCategoryVO[i].setValidUpto(validUpto[m]);
					if(expiryDate!=null)
					TreatmentCategoryVO[i].setExpiryDate(expiryDate[m]);
					TreatmentCategoryVO[i].setIsIpdFlag(objSUP_p.getIsIpdFlag());
					if(catClientCode!=null)
						TreatmentCategoryVO[i].setCatClientCode(catClientCode[m]);
					
				}
				
					
				for(int j=0;j<TreatmentCategoryVO.length;j++){
					for(int i=0; i<arrOpenEpisodeVO.length; i++){
						String str1=TreatmentCategoryVO[j].getEpisodeCode();
						String str2=arrOpenEpisodeVO[i].getEpisodeCode();
					
						if(str1.trim().compareTo(str2.trim())==0){
							TreatmentCategoryVO[j].setPatPrevSecondaryCatCode(arrOpenEpisodeVO[i].getPatSecondaryCatCode());
							depts=depts+"	"+arrOpenEpisodeVO[i].getDepartment();
						}
						
					 }
				}
				for(int j=0;j<TreatmentCategoryVO.length;j++)
				{
					if(TreatmentCategoryVO[j].getPatPrevSecondaryCatCode().equals("-1"))
					{
						TreatmentCategoryVO[j].setPatPrevSecondaryCatCode("");
					}
				}
			}
	///////////REVOKE
				if(objSUP_p.getRevokeChk()!=null)
				{    
					TreatmentCategoryRevokeVO = new ChangeTreatmentCategoryVO[objSUP_p.getRevokeChk().length];
					for(int i=0;i<objSUP_p.getRevokeChk().length;i++)
					{	int y=0;
						//int y=Integer.parseInt(objSUP_p.getRevokeChk()[i]);
						for(int z=0;z<arrOpenEpisodeVO.length;z++){
							if(arrOpenEpisodeVO[z].getEpisodeCode().equals(objSUP_p.getRevokeChk()[i]))
							{
								 y=z;
							break;
							}
						}
						TreatmentCategoryRevokeVO[i]=new ChangeTreatmentCategoryVO();
						HelperMethods.populate(TreatmentCategoryRevokeVO[i], patientVO);
						TreatmentCategoryRevokeVO[i].setPatCrNo(objSUP_p.getPatCrNo());
						TreatmentCategoryRevokeVO[i].setEpisodeCode(arrOpenEpisodeVO[y].getEpisodeCode());
						TreatmentCategoryRevokeVO[i].setEpisodeVisitNo(arrOpenEpisodeVO[y].getEpisodeVisitNo());
						TreatmentCategoryRevokeVO[i].setIsIpdFlag(objSUP_p.getIsIpdFlag());
						TreatmentCategoryRevokeVO[i].setRemarks(remarks[i]);
						TreatmentCategoryRevokeVO[i].setPatNewSecondaryCatCode("");
						
					}
					
					for(int j=0;j<TreatmentCategoryRevokeVO.length;j++)
					{
						for(int i=0; i<arrOpenEpisodeVO.length; i++)
						{
							String str1=TreatmentCategoryRevokeVO[j].getEpisodeCode();
							String str2=arrOpenEpisodeVO[i].getEpisodeCode();
					
							if(str1.trim().compareTo(str2.trim())==0)
							{
								TreatmentCategoryRevokeVO[j].setPatPrevSecondaryCatCode(arrOpenEpisodeVO[i].getPatSecondaryCatCode());
								depts=depts+"	"+arrOpenEpisodeVO[i].getDepartment();
							}	
						}
					}
					
					for(int j=0;j<TreatmentCategoryRevokeVO.length;j++)
					{
						if(TreatmentCategoryRevokeVO[j].getPatPrevSecondaryCatCode().equals("-1"))
						{
							TreatmentCategoryRevokeVO[j].setPatPrevSecondaryCatCode("");
						}
					}
				}	
			ChangeTreatmentCategoryDATA.changeTreatmentCategory(TreatmentCategoryVO,TreatmentCategoryRevokeVO, getUserVO(_rq));
			}
			else
			{
				
				admittedPatientEpisodeVO = (EpisodeVO) _rq
						.getSession()
						.getAttribute(
								RegistrationConfig.ADMITTED_PATIENT_VO);
				ChangeTreatmentCategoryVO admittedPatientVO=new ChangeTreatmentCategoryVO();
				HelperMethods.populate(admittedPatientVO, patientVO);
				String newSecCatselectedEpisodes = objSUP_p.getHiddenNewSecCatCode()[0];
				//admittedPatientVO=new ChangeTreatmentCategoryVO();
				admittedPatientVO.setPatCrNo(objSUP_p.getPatCrNo());
				admittedPatientVO.setEpisodeCode(admittedPatientEpisodeVO.getEpisodeCode());
				admittedPatientVO.setEpisodeVisitNo(admittedPatientEpisodeVO.getEpisodeVisitNo());
				admittedPatientVO.setPatNewSecondaryCatCode(newSecCatselectedEpisodes);
				admittedPatientVO.setPatPrevSecondaryCatCode(admittedPatientEpisodeVO.getPatSecondaryCatCode());
				admittedPatientVO.setRemarks(objSUP_p.getRemarks());
				admittedPatientVO.setExpiryDate(admittedPatientEpisodeVO.getExpiryDate());
				admittedPatientVO.setSerialNo(admittedPatientEpisodeVO.getSerialNo());
				admittedPatientVO.setAdmissionNo(admittedPatientEpisodeVO.getAdmissionNo());
				admittedPatientVO.setIsIpdFlag(objSUP_p.getIsIpdFlag());
				//ChangeTreatmentCategoryDATA.changeIPDTreatmentCategory(admittedPatientVO, getUserVO(_rq));
			}
			
			objStatus.add(Status.DONE, "", "Patient Refered");

		} catch (HisRecordNotFoundException e) {
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			essentialMap = e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, _rq,"ChangeTreatmentCategoryACTION");
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} catch (Exception e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	//Added by Vasu dated on 9-April-2018 for adding multiple treatment categories into a particular episode
	
	public static void saveSecondaryTreatmentCategoryChange(ChangeTreatmentCategorySUP objSUP_p,HttpServletRequest _rq) {
		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = WebUTIL.getSession(_rq);
		EpisodeVO[] arrOpenEpisodeVO = null;
		PatientVO patientVO = null;
		String depts="";
		ChangeTreatmentCategoryVO[] TreatmentCategoryVO = null;
		//ChangeTreatmentCategoryVO[] TreatmentCategoryRevokeVO =null;
		EpisodeVO admittedPatientEpisodeVO;
		boolean flag = false;
		
		try {
			patientVO = (PatientVO) _rq.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			if(!objSUP_p.getIsIpdFlag().equals("1"))
			{
			//arrOpenEpisodeVO = (EpisodeVO[]) _rq.getSession().getAttribute(RegistrationConfig.ARR_OPEN_EPISODE_VO);
			String[] newSecCatselectedEpisodes = objSUP_p.getSecCatCode();
			String[] remarks=objSUP_p.getRemarksArr();
			String[] validUpto=objSUP_p.getValidUpto(); 
			String[] expiryDate=objSUP_p.getCategoryExpiryDate();
			String[] catClientCode=objSUP_p.getCatClientCode();
			String[] letterRefNo=objSUP_p.getLetterReferenceNo();
			String[] letterDate = objSUP_p.getLetterDate();
			String[] creditLimit=objSUP_p.getCreditLimit();
			String[] applicableServicesCode = objSUP_p.getApplicableServiceCode();
			String[] applicableServicesName = objSUP_p.getApplicableServicesName();
			String[] selectedCategoryName = objSUP_p.getSelectedCategoryName();
			if(objSUP_p.getSelectedEpisodeForTreatmentCategory()!=null && objSUP_p.getSelectedEpisodeForTreatmentCategory().length!=0)
			{
				
				TreatmentCategoryVO = new ChangeTreatmentCategoryVO[objSUP_p.getSelectedEpisodeForTreatmentCategory().length];
				String visitNo = objSUP_p.getSelectEpisodeVisitNo()[0];
				for(int i=0;i<TreatmentCategoryVO.length;i++){
					TreatmentCategoryVO[i]=new ChangeTreatmentCategoryVO();
					HelperMethods.populate(TreatmentCategoryVO[i], patientVO);
					
					TreatmentCategoryVO[i].setEpisodeCode(objSUP_p.getSelectedEpisodeForTreatmentCategory()[i]);
					TreatmentCategoryVO[i].setPatNewSecondaryCatCode(newSecCatselectedEpisodes[i]);
					TreatmentCategoryVO[i].setRemarks(remarks[i]);
					TreatmentCategoryVO[i].setValidUpto(validUpto[i]);
					TreatmentCategoryVO[i].setEpisodeVisitNo(visitNo);
					TreatmentCategoryVO[i].setLetterReferenceNo(letterRefNo[i]);
					TreatmentCategoryVO[i].setLetterDate(letterDate[i]);
					TreatmentCategoryVO[i].setCreditLimit(creditLimit[i]);
					TreatmentCategoryVO[i].setApplicableServicesCode(applicableServicesCode[i]); //Applicable Services Code
					TreatmentCategoryVO[i].setApplicableServicesName(applicableServicesName[i]);
					TreatmentCategoryVO[i].setSelectedCategoryName(selectedCategoryName[i]);
					if(expiryDate!=null)
					TreatmentCategoryVO[i].setExpiryDate(expiryDate[i]);
					TreatmentCategoryVO[i].setIsIpdFlag("0");
					/*if(catClientCode!=null)
						TreatmentCategoryVO[i].setCatClientCode(catClientCode[i]);*/
					
				}
			}
			objSUP_p.setPatCrNo(patientVO.getPatCrNo());
			//String episode = objSUP_p.getSelectedEpisodeForTreatmentCategory()[0];
			ChangeTreatmentCategoryDATA.changePreviousTreatmentCategory(TreatmentCategoryVO, getUserVO(_rq));
			//for(int i=0;i<TreatmentCategoryVO.length;i++){
			//if(TreatmentCategoryVO[i].getEpisodeCode().equals(episode)){
			 WebUTIL.setAttributeInSession(_rq,RegistrationConfig.PREVIOUS_TREATMENT_CATEGORIES,TreatmentCategoryVO);
			//}
			//}
			}
			else
			{
				String[] newSecCatselectedEpisodes = objSUP_p.getSecCatCode();
				String[] remarks=objSUP_p.getRemarksArr();
				String[] validUpto=objSUP_p.getValidUpto(); 
				String[] expiryDate=objSUP_p.getCategoryExpiryDate();
				String[] catClientCode=objSUP_p.getCatClientCode();
				String[] letterRefNo=objSUP_p.getLetterReferenceNo();
				String[] letterDate = objSUP_p.getLetterDate();
				String[] creditLimit=objSUP_p.getCreditLimit();
				String[] applicableServicesCode = objSUP_p.getApplicableServiceCode();
				String[] applicableServicesName = objSUP_p.getApplicableServicesName();
				String[] selectedCategoryName = objSUP_p.getSelectedCategoryName();
		        //String[] admissionNumber = objSUP_p.getAdmissionNo();  
				
				if(objSUP_p.getSelectedEpisodeForTreatmentCategory()!=null && objSUP_p.getSelectedEpisodeForTreatmentCategory().length!=0)
				{
					
					TreatmentCategoryVO = new ChangeTreatmentCategoryVO[objSUP_p.getSelectedEpisodeForTreatmentCategory().length];
					String visitNo = objSUP_p.getSelectEpisodeVisitNo()[0];
					String admissionNumber = objSUP_p.getAdmissionNo()[0];
					for(int i=0;i<TreatmentCategoryVO.length;i++){
						TreatmentCategoryVO[i]=new ChangeTreatmentCategoryVO();
						HelperMethods.populate(TreatmentCategoryVO[i], patientVO);
						
						TreatmentCategoryVO[i].setEpisodeCode(objSUP_p.getSelectedEpisodeForTreatmentCategory()[i]);
						TreatmentCategoryVO[i].setPatNewSecondaryCatCode(newSecCatselectedEpisodes[i]);
						TreatmentCategoryVO[i].setRemarks(remarks[i]);
						TreatmentCategoryVO[i].setValidUpto(validUpto[i]);
						TreatmentCategoryVO[i].setEpisodeVisitNo(visitNo);
						TreatmentCategoryVO[i].setLetterReferenceNo(letterRefNo[i]);
						TreatmentCategoryVO[i].setLetterDate(letterDate[i]);
						TreatmentCategoryVO[i].setCreditLimit(creditLimit[i]);
						TreatmentCategoryVO[i].setApplicableServicesCode(applicableServicesCode[i]); //Applicable Services Code
						TreatmentCategoryVO[i].setApplicableServicesName(applicableServicesName[i]);
						TreatmentCategoryVO[i].setSelectedCategoryName(selectedCategoryName[i]);
						TreatmentCategoryVO[i].setAdmissionNo(admissionNumber);
						if(expiryDate!=null)
						TreatmentCategoryVO[i].setExpiryDate(expiryDate[i]);
						TreatmentCategoryVO[i].setIsIpdFlag("1");
						/*if(catClientCode!=null)
							TreatmentCategoryVO[i].setCatClientCode(catClientCode[i]);*/
						
					}
				}
				    ChangeTreatmentCategoryDATA.changeIPDTreatmentCategory(TreatmentCategoryVO, getUserVO(_rq));
				    objSUP_p.setPatCrNo(patientVO.getPatCrNo());
			}
			
			objStatus.add(Status.DONE, "", "Patient Refered");

		} catch (HisRecordNotFoundException e) {
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			essentialMap = e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, _rq,"ChangeTreatmentCategoryACTION");
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} catch (Exception e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	public static void setPatientDtlForCategoryChange(ChangeTreatmentCategorySUP objSUP_p,HttpServletRequest objRequest_p) {
		Status status = new Status();
		EpisodeVO[] arrOpenEpisodeVO;
		EpisodeVO admittedPatientVO;
		EpisodeVO[] previousTreatmentCategories;
		try {
			UserVO userVO =getUserVO(objRequest_p);
			PatientVO objPatientVO=new PatientVO();
			objPatientVO.setPatCrNo(objSUP_p.getPatCrNo());
			setAllTreatmentCategoryEssentials(objSUP_p,objRequest_p);
			
			PatDetailUTIL.getPatientDtlByCrno(objSUP_p, objRequest_p);
			objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			String getSelectedEpisodeIndex =  objSUP_p.getSelectedEpisodeIndex();
		
			String crNo=objPatientVO.getPatCrNo();

				arrOpenEpisodeVO=ChangeTreatmentCategoryDATA.getAllOpenEpisodesVisitedToday(crNo, userVO);
				for(int i=0;i<arrOpenEpisodeVO.length;i++){
				if(arrOpenEpisodeVO[i].getPatSecondaryCatCode()==null)
					arrOpenEpisodeVO[i].setPatSecondaryCatCode("-1");
				//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPEN_EPISODE_VO,arrOpenEpisodeVO[getSelectedEpisodeIndex]);
			//}	
				int index = Integer.parseInt(getSelectedEpisodeIndex);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ARR_OPEN_EPISODE_VO,arrOpenEpisodeVO[index]);
			}
			//String ipdFlag =objSUP_p.getIsIpdFlag();
			String ipdFlag = "0";
			objSUP_p.setIsIpdFlag("0");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.IPD_FLAG,ipdFlag);
			
			if(objSUP_p.getIsIpdFlag().equals("0"))
			{
			int index = Integer.parseInt(getSelectedEpisodeIndex);
			String selectedEpisodeCode = arrOpenEpisodeVO[index].getEpisodeCode();
			previousTreatmentCategories = ChangeTreatmentCategoryDATA.getAllPreviousTreatmentCategories(crNo, selectedEpisodeCode,userVO);
			//for(int i=0;i<previousTreatmentCategories.length;i++)
			//{
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.GET_SELECTED_TREATMENT_CATEGORIES_FOR_PARTICULAR_EPISODE,previousTreatmentCategories);
			//}
			}
			objSUP_p.setGoFlag("1");
			objSUP_p.setAfterGo("1");
		} catch (HisRecordNotFoundException e) {
			objSUP_p.setGoFlag("0");
			objSUP_p.setErrorMessage(e.getMessage());
			status.add(Status.NEW, e.getMessage(), "");
		} catch (HisPatientStillUnknownException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.UNSUCESSFULL, e.getMessage(), "");
		} catch (HisDataAccessException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_DA, e.getMessage(), "");
		} catch (HisApplicationExecutionException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		} finally {
			WebUTIL.setStatus(objRequest_p, status);

		}
	}
	
	public static void setPatientDtlForCategoryChangeIPD(ChangeTreatmentCategorySUP objSUP_p,HttpServletRequest objRequest_p) {
		Status status = new Status();
		//EpisodeVO[] arrOpenEpisodeVO;
		EpisodeVO admittedPatientVO;
		EpisodeVO[] previousTreatmentCategories;
		try {
			UserVO userVO =getUserVO(objRequest_p);
			PatientVO objPatientVO=new PatientVO();
			objPatientVO.setPatCrNo(objSUP_p.getPatCrNo());
			String episodeCode = objSUP_p.getEpisodeCode();
			setAllTreatmentCategoryEssentials(objSUP_p,objRequest_p);
			
			PatDetailUTIL.getPatientDtlByCrno(objSUP_p, objRequest_p);
			objPatientVO=(PatientVO)objRequest_p.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			String crNo=objPatientVO.getPatCrNo();
			admittedPatientVO=ChangeTreatmentCategoryDATA.getPatientAdmittedEpisodes(crNo,userVO);
			admittedPatientVO.setPatPrimaryCatCode(objPatientVO.getPatCatCode());
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.ADMITTED_PATIENT_VO,admittedPatientVO);
			
			String ipdFlag = "1";
			objSUP_p.setIsIpdFlag("1");
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.IPD_FLAG,ipdFlag);
			if(objSUP_p.getIsIpdFlag().equals("1"))
			{
				
				previousTreatmentCategories = ChangeTreatmentCategoryDATA.getAllPreviousTreatmentCategories(crNo, episodeCode,userVO);
				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.GET_SELECTED_TREATMENT_CATEGORIES_FOR_PARTICULAR_EPISODE,previousTreatmentCategories);
				
			}
			
			objSUP_p.setGoFlag("1");
			objSUP_p.setAfterGo("1");
		} catch (HisRecordNotFoundException e) {
			objSUP_p.setGoFlag("0");
			objSUP_p.setErrorMessage(e.getMessage());
			status.add(Status.NEW, e.getMessage(), "");
		} catch (HisPatientStillUnknownException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.UNSUCESSFULL, e.getMessage(), "");
		} catch (HisDataAccessException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_DA, e.getMessage(), "");
		} catch (HisApplicationExecutionException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			objSUP_p.setGoFlag("0");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		} finally {
			WebUTIL.setStatus(objRequest_p, status);

		}
	}
} //End Class
