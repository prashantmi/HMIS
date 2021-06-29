package opd.transaction.controller.util;

import inpatient.InpatientConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.ConsentOfflineInboxDATA;
import opd.transaction.controller.fb.ConsentOfflineInboxFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class ConsentOfflineInboxUTIL extends ControllerUTIL
{
	public static boolean getEssentials(ConsentOfflineInboxFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		setSysdate(_rq);
		UserVO userVO = getUserVO(_rq);
		Map essentialMap = new HashMap();
		//HttpSession session = _rq.getSession();
		try
		{
				
			ConsentRequestVO consentRequestVO = new ConsentRequestVO();
			consentRequestVO.setPatCrNo(_fb.getPatCrNo());
			
			consentRequestVO.setIsRevoke("1");
			essentialMap = ConsentOfflineInboxDATA.getEssentials(consentRequestVO, userVO);
			List relationList= (List)essentialMap.get(OpdConfig.ALL_RELATIONSHIP_LIST);
			ConsentRequestVO[] _consentRequestVO = (ConsentRequestVO[]) essentialMap.get(OpdConfig.CONSENT_REQUEST_VO_LIST);
			
			List receivedConsentRequestVO = new ArrayList();
			List pendingConsentRequestVO = new ArrayList();
			for (int i = 0; i < _consentRequestVO.length; i++)
			{
				if (_consentRequestVO[i].getConsentStatus().equals("1"))
				{
					_consentRequestVO[i].setConsentStatus("Pending");
					pendingConsentRequestVO.add(_consentRequestVO[i]);
				}
				else
				{
					_consentRequestVO[i].setConsentStatus("Received");
					receivedConsentRequestVO.add(_consentRequestVO[i]);
				}
			}
			
			//get and set relationship Name
			Iterator<ConsentRequestVO> itr=receivedConsentRequestVO.iterator();
			while(itr.hasNext())
			{
				ConsentRequestVO consentReqVO = itr.next();
				Iterator relationItr=relationList.iterator();
				while(relationItr.hasNext())
				{
					Entry obj=(Entry)relationItr.next();
					if(obj.getValue().equals(consentReqVO.getRelationCode()))
					{
						consentReqVO.setRelationshipName(obj.getLabel());
					}
				}
			}
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST, pendingConsentRequestVO);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST, receivedConsentRequestVO);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ALL_RELATIONSHIP_LIST, relationList);
			if(pendingConsentRequestVO.size()<=0)
				objStatus.add(Status.TRANSINPROCESS,"No Pending Consent Request Exists","");
			else
				objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	public static boolean setConsentData(ConsentOfflineInboxFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<ConsentRequestVO> lstReceivedConsents = null;
		int index;
		try
		{
			setSysdate(_rq);
			HttpSession session = _rq.getSession();
			
			PatientDetailVO patDtlVO=new PatientDetailVO();
			patDtlVO= (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			
			if(patDtlVO!=null && patDtlVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				GenericTemplateUtility.setVOInInfoBean(_rq, patDtlVO);
	
				if(_rq.getParameter("i")!=null)
				{
					index = Integer.parseInt(_rq.getParameter("i"));
					
					if(_rq.getParameter("isGiven")!=null && _rq.getParameter("isGiven").equals("1"))
						lstReceivedConsents = (List<ConsentRequestVO>)session.getAttribute(OpdConfig.RECEIVED_CONSENT_REQUEST_VO_LIST);
					else
						lstReceivedConsents = (List<ConsentRequestVO>)session.getAttribute(OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST);

					ConsentRequestVO targetConsent = lstReceivedConsents.get(index);
					GenericTemplateUtility.setVOInInfoBean(_rq, targetConsent);

					objStatus.add(Status.TRANSINPROCESS);
				}
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	public static boolean update(ConsentOfflineInboxFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		setSysdate(_rq);
		UserVO userVO = getUserVO(_rq);
		//HttpSession session=_rq.getSession();
		try
		{
			/*
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) WebUTIL.getSession(_rq).getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			// here we retrive the selected patient from the (desk)list
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}
			*/
			
			//PatientDetailVO patDtlVO=new PatientDetailVO();
			//patDtlVO= (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			
			String[] index = _fb.getChk();
			ConsentRequestVO[] consentRequestVO = new ConsentRequestVO[index.length];
			String[] consentId = _fb.getConsentId();
			String[] serviceTypeId = _fb.getServiceTypeId();
			String[] serviceId = _fb.getServiceId();
			String[] requestId = _fb.getRequestID();
			
			String[] givenBy=_fb.getGivenBy();
			//String[] relationCode=_fb.getRelationCode();
			//String[] relativeIdRemark=_fb.getRelativeIdRemark();
			//String[] relativeContactNo=_fb.getRelativeContactNo();
			//String[] relativeAddr=_fb.getRelativeAddr();
			//String[] relativeName=_fb.getRelativeName();
			String[] episodeCode=_fb.getEpisodeCode();
			String[] episodeVisitNo=_fb.getEpisodeVisitNo();
			
			for (int i = 0; i < index.length; i++)
			{
				int ind = Integer.parseInt(index[i]);
				consentRequestVO[i] = new ConsentRequestVO();
				consentRequestVO[i].setPatCrNo(_fb.getPatCrNo());
				consentRequestVO[i].setEpisodeCode(episodeCode[ind]);
				consentRequestVO[i].setEpisodeVisitNo(episodeVisitNo[ind]);
				//HelperMethods.populate(consentRequestVO[i], patDtlVO);
				consentRequestVO[i].setTemplateId(consentId[ind]);
				consentRequestVO[i].setServiceTypeId(serviceTypeId[ind]);
				consentRequestVO[i].setServiceId(serviceId[ind]);
				consentRequestVO[i].setRequestID(requestId[ind]);
				consentRequestVO[i].setGivenBy(givenBy[i]);
				if(givenBy[i].equals("1"))
				{
					consentRequestVO[i].setRelationCode(_fb.getRelationCode()[ind]);
					consentRequestVO[i].setRelativeIdRemark(_fb.getRelativeIdRemark()[ind]);
					consentRequestVO[i].setRelativeContactNo(_fb.getRelativeContactNo()[ind]);
					consentRequestVO[i].setRelativeAddr(_fb.getRelativeAddr()[ind]);
					consentRequestVO[i].setRelativeName(_fb.getRelativeName()[ind]);
				}
				else
				{
					consentRequestVO[i].setRelationCode("");
					consentRequestVO[i].setRelativeIdRemark("");
					consentRequestVO[i].setRelativeContactNo("");
					consentRequestVO[i].setRelativeAddr("");
					consentRequestVO[i].setRelativeName("");
				}
			}
			ConsentOfflineInboxDATA.updateStatus(consentRequestVO, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}
}
