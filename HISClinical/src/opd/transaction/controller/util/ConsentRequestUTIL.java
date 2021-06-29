package opd.transaction.controller.util;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.ConsentRequestDATA;
import opd.transaction.controller.fb.ConsentRequestFB;
import registration.controller.fb.CRNoFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class ConsentRequestUTIL extends ControllerUTIL
{
	public static boolean getEssentials(ConsentRequestFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		setSysdate(_rq);
		UserVO userVO = getUserVO(_rq);
		Map essentialMap = new HashMap();
		HttpSession session = _rq.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			CRNoFB fbb= new CRNoFB ();
			fbb.setPatCrNo(_fb.getPatCrNo());
			InpatientDetailUTL.getInpatientDetailByCrNo(fbb, _rq);//InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus=(String)patientVO.getPatStatusCode();
			_fb.setHiddenPatDeadStatus(patDeathStatus);
			
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			// here we retrive the selected patient from the (desk)list
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}*/
			_fb.setEpisodeCode(patientDetailVO.getEpisodeCode());
			_fb.setEpisodeVisitNo(patientDetailVO.getEpisodeVisitNo());
			_fb.setPatCrNo(patientDetailVO.getPatCrNo());
			
			ConsentRequestVO consentRequestVO = new ConsentRequestVO();
			HelperMethods.populate(consentRequestVO, patientDetailVO);
			
			consentRequestVO.setIsRevoke("1");
			essentialMap = ConsentRequestDATA.getEssentials(consentRequestVO, userVO);
			List relationList= (List)essentialMap.get(OpdConfig.ALL_RELATIONSHIP_LIST);
			ConsentRequestVO[] _consentRequestVO = (ConsentRequestVO[]) essentialMap.get(OpdConfig.CONSENT_REQUEST_VO_LIST);
			
			List receivedConsentRequestVO = new ArrayList();
			List pendingConsentRequestVO = new ArrayList();
			for (int i = 0; i < _consentRequestVO.length; i++)
			{
				if (_consentRequestVO[i].getConsentStatus().equals(OpdConfig.CONSENT_REQUEST))
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

	public static boolean setConsentData(ConsentRequestFB _fb, HttpServletRequest _rq)
	{
		boolean flg = true;
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
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flg = false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flg = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flg = false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flg = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flg;
	}

	public static boolean update(ConsentRequestFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		setSysdate(_rq);
		UserVO userVO = getUserVO(_rq);
		HashMap map=null;
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
			String[] index = _fb.getChk();
			ConsentRequestVO[] consentRequestVO = new ConsentRequestVO[index.length];
			String[] consentId = _fb.getConsentId();
			String[] serviceTypeId = _fb.getServiceTypeId();
			String[] serviceId = _fb.getServiceId();
			String[] requestId = _fb.getRequestID();
			String[] serviceConsentId=_fb.getServiceConsentId();
			
			String[] givenBy=_fb.getGivenBy();
			String[] relationCode=_fb.getRelationCode();
			String[] relativeIdRemark=_fb.getRelativeIdRemark();
			String[] relativeContactNo=_fb.getRelativeContactNo();
			String[] relativeAddr=_fb.getRelativeAddr();
			String[] relativeName=_fb.getRelativeName();
			
			
			for (int i = 0; i < index.length; i++)
			{
				int ind = Integer.parseInt(index[i]);
				consentRequestVO[i] = new ConsentRequestVO();
				HelperMethods.populate(consentRequestVO[i], selectedPatientVO);
				consentRequestVO[i].setTemplateId(consentId[ind]);
				consentRequestVO[i].setServiceTypeId(serviceTypeId[ind]);
				consentRequestVO[i].setServiceId(serviceId[ind]);
				consentRequestVO[i].setRequestID(requestId[ind]);
				consentRequestVO[i].setServiceConsentId(serviceConsentId[ind]);
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
			*/
			HttpSession session = _rq.getSession();
			List consentRequestVos=(List)session.getAttribute(OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST);
			List receiveConsentVoLst=new ArrayList();
			
			if(session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST)!=null)
				map=(HashMap)session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST);
			else
				map=new HashMap();
			
			//Integer ind=0;
			
			if(_fb.getChk()!=null)
			{
				for(int i=0;i<_fb.getChk().length;i++)
				{
					String serviceConsentId=_fb.getChk()[i];
					Integer ind=0;
					
					for(int j=0;j<_fb.getServiceConsentId().length;j++)
					{
						if(_fb.getServiceConsentId()[j].equals(_fb.getChk()[i]))
						{
							for(int k=0;k<consentRequestVos.size();k++)
							{
								ConsentRequestVO vo=(ConsentRequestVO)consentRequestVos.get(k);
								if(vo.getServiceConsentId().equals(serviceConsentId))
								{
									vo.setGivenBy(_fb.getGivenBy()[i]);
									//vo.setTemplateId(_fb.getConsentId()[ind]);
									if(_fb.getGivenBy()[i].equals("1"))
									{
										vo.setRelationCode(_fb.getRelationCode()[ind]);
										vo.setRelativeIdRemark(_fb.getRelativeIdRemark()[ind]);
										vo.setRelativeContactNo(_fb.getRelativeContactNo()[ind]);
										vo.setRelativeAddr(_fb.getRelativeAddr()[ind]);
										vo.setRelativeName(_fb.getRelativeName()[ind]);
									}
									else
									{
										vo.setRelationCode("");
										vo.setRelativeIdRemark("");
										vo.setRelativeContactNo("");
										vo.setRelativeAddr("");
										vo.setRelativeName("");
									}
									map.put(serviceConsentId, vo);
								}
							}
						}
						ind++;
					}
				}
			}
						
			Set keys = map.keySet();
			Iterator itr=keys.iterator();
			while(itr.hasNext())
			{
				String key=(String)itr.next();
				ConsentRequestVO vo=(ConsentRequestVO)map.get(key);
				vo.setPatCrNo(_fb.getPatCrNo());
				vo.setEpisodeCode(_fb.getEpisodeCode());
				vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				receiveConsentVoLst.add(vo);
			}
			
		    		
			ConsentRequestDATA.updateStatus(receiveConsentVoLst, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
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
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}
	
	public static boolean setData(ConsentRequestFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HashMap map=null;
		
		try
		{
			setSysdate(_rq);
			HttpSession session = _rq.getSession();
			List consentRequestVos=(List)session.getAttribute(OpdConfig.PENDING_CONSENT_REQUEST_VO_LIST);
			
			if(session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST)!=null)
				map=(HashMap)session.getAttribute(OpdConfig.SELECTED_CONSENT_REQUEST);
			else
				map=new HashMap();
			
			
			
			if(_fb.getChk()!=null)
			{
				for(int i=0;i<_fb.getChk().length;i++)
				{
					String serviceConsentId=_fb.getChk()[i];
					Integer ind=0;
					
					for(int j=0;j<_fb.getServiceConsentId().length;j++)
					{
						if(_fb.getServiceConsentId()[j].equals(_fb.getChk()[i]))
						{
							for(int k=0;k<consentRequestVos.size();k++)
							{
								ConsentRequestVO vo=(ConsentRequestVO)consentRequestVos.get(k);
								if(vo.getServiceConsentId().equals(serviceConsentId))
								{
									vo.setGivenBy(_fb.getGivenBy()[i]);
									vo.setTemplateId(_fb.getConsentId()[ind]);
									if(_fb.getGivenBy()[i].equals("1"))
									{
										vo.setRelationCode(_fb.getRelationCode()[ind]);
										vo.setRelativeIdRemark(_fb.getRelativeIdRemark()[ind]);
										vo.setRelativeContactNo(_fb.getRelativeContactNo()[ind]);
										vo.setRelativeAddr(_fb.getRelativeAddr()[ind]);
										vo.setRelativeName(_fb.getRelativeName()[ind]);
									}
									else
									{
										vo.setRelationCode("");
										vo.setRelativeIdRemark("");
										vo.setRelativeContactNo("");
										vo.setRelativeAddr("");
										vo.setRelativeName("");
									}
									map.put(serviceConsentId, vo);
								}
							}
						}
						ind++;
					}
				}
			}
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_CONSENT_REQUEST,map);
			
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
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
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}
}
