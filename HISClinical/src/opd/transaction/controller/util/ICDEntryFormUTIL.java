package opd.transaction.controller.util;

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
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.ICDEntryFormDATA;
import opd.transaction.controller.fb.ICDEntryFormFB;

public class ICDEntryFormUTIL extends ControllerUTIL
{
	// Getting Todays OPD Patient List
	public static void setEssentials(ICDEntryFormFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status(); 
		try
		{
			setSysdate(_request);
			String strSysDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			_fb.setSysDate(strSysDate);
			_fb.setFromDate(strSysDate);
			_fb.setToDate(strSysDate);
			
			Map<String,Object> mapEssentials = ICDEntryFormDATA.getICDEntryFormEssentials(getUserVO(_request));

			WebUTIL.setMapInSession(mapEssentials, _request);			
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			session.removeAttribute("counter");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	public static void getOPDPatientList(ICDEntryFormFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		List<PatientDetailVO> lstPatients  = null;
		Map<String,PatientDetailVO> mapPatients = new HashMap<String, PatientDetailVO>();
		Status objStatus = new Status(); 
		try
		{
			PatientDetailVO voPatDtl = new PatientDetailVO();
			HelperMethods.populate(voPatDtl, _fb);
			voPatDtl.setPatIsDocPresent(_fb.getIcdCodeFlag());
			
			lstPatients = ICDEntryFormDATA.getOPDPatientListForICDEntry(_fb.getTabMode(), voPatDtl, _fb.getFromDate(), _fb.getToDate(), getUserVO(_request));
			_fb.setCurrentPage(1);
			
			if(lstPatients!=null && lstPatients.size()>0)
			{
				for(PatientDetailVO vo : lstPatients)
				{
					vo.setPatientName();
					//mapPatients.put(vo.getPatCrNo(), vo);
					mapPatients.put(vo.getPatCrNo()+"#"+vo.getEpisodeCode()+"#"+vo.getEpisodeVisitNo(), vo);
				}
			}
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST, lstPatients);	
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_PATIENT_MAP, mapPatients);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_SELECTED_PATIENT_SET, null);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_SEEN_PATIENT_SET, null);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			session.removeAttribute("counter");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	public static void setOPDPatientListForMod(ICDEntryFormFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status(); 
		try
		{
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST, null);	
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_PATIENT_MAP, null);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_SELECTED_PATIENT_SET, null);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ICD_ENTRY_FORM_SEEN_PATIENT_SET, null);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			session.removeAttribute("counter");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	/**
	 * AJAX Response : Getting ICD Codes List
	 * @param objFB_p
	 * @param objRequest_p
	 */
	public static StringBuffer getICDCodesList(ICDEntryFormFB objFB_p, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			
			List<Entry> lstICDCodes = ICDEntryFormDATA.getICDCodesListForICDEntry(voUser);
			
			if(lstICDCodes!=null && lstICDCodes.size()>0)
			{
				sbAjaxRes.append("[");
				for(Entry vo : lstICDCodes)
				{
					sbAjaxRes.append("{");
					sbAjaxRes.append("strICDCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getValue());sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");
				}
				if(lstICDCodes.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		}
		catch (Exception e)
		{
			strMsgText = "ICDEntryFormUTIL.getICDCodesList() -> " + e.getMessage();
			//HisException eObj = 
			new HisException("dietkitchen", "ICDEntryFormUTIL.getICDCodesList() --> ", strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return sbAjaxRes;
	}
	
	/**
	 * AJAX Response : Getting Unit Room List
	 * @param objFB_p
	 * @param objRequest_p
	 */
	public static StringBuffer getUnitRoomList(ICDEntryFormFB objFB_p, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			
			List<Entry> lstUnitRooms = ICDEntryFormDATA.getUnitRoomListForICDEntry(objFB_p.getDepartmentUnitCode(), voUser);
			
			if(lstUnitRooms!=null && lstUnitRooms.size()>0)
			{
				sbAjaxRes.append("[");
				for(Entry vo : lstUnitRooms)
				{
					sbAjaxRes.append("{");
					sbAjaxRes.append("roomCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getValue());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("roomName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getLabel());sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");
				}
				if(lstUnitRooms.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		}
		catch (Exception e)
		{
			strMsgText = "ICDEntryFormUTIL.getICDCodesList() -> " + e.getMessage();
			//HisException eObj = 
			new HisException("dietkitchen", "ICDEntryFormUTIL.getICDCodesList() --> ", strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return sbAjaxRes;
	}

	public static void setPaginationData(ICDEntryFormFB objFB_p, HttpServletRequest _request)
	{
		Status objStatus = new Status(); 
		try
		{
			setSelectedPatICDCodes(objFB_p, _request);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	private static void setSelectedPatICDCodes(ICDEntryFormFB objFB_p, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Set<String> setSelPats = null;
		Set<String> setSeenPats = null;
		Map<String, PatientDetailVO> mapPatients  = null;
			
		mapPatients = (Map<String, PatientDetailVO>) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_MAP);
		if(mapPatients==null)	mapPatients = new HashMap<String, PatientDetailVO>();

		setSelPats = (Set<String>) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_SELECTED_PATIENT_SET);
		if(setSelPats==null) setSelPats = new HashSet<String>();

		setSeenPats = (Set<String>) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_SEEN_PATIENT_SET);
		if(setSeenPats==null) setSeenPats = new HashSet<String>();
		
		// Setting Selected ICD Entered Patient
		if(objFB_p.getSelectedPatCrNo()!=null && objFB_p.getSelectedPatCrNo().length>0)
		{
			for(int i=0; i<objFB_p.getSelectedPatCrNo().length; i++)
			{
				if(objFB_p.getSelectedPatICD()!=null && objFB_p.getSelectedPatICD().length>i 
						&& objFB_p.getSelectedPatICD()[i]!=null && !objFB_p.getSelectedPatICD()[i].trim().equals(""))
				{
					//PatientDetailVO vo = mapPatients.get(objFB_p.getSelectedPatCrNo()[i].split("#")[0]);
					PatientDetailVO vo = mapPatients.get(objFB_p.getSelectedPatCrNo()[i]);
					vo.setPatDiagnosisDtl(objFB_p.getSelectedPatICD()[i]);
					//setSelPats.add(objFB_p.getSelectedPatCrNo()[i].split("#")[0]);
					setSelPats.add(objFB_p.getSelectedPatCrNo()[i]);
				}
			}
		}
		// Setting Seen Patient Not ICD Entered
		if(objFB_p.getSeenPat()!=null && objFB_p.getSeenPat().length>0)
		{
			for(int i=0; i<objFB_p.getSeenPat().length; i++)
			{
				if(!setSelPats.contains(objFB_p.getSeenPat()[i]))
					setSeenPats.add(objFB_p.getSeenPat()[i]);
			}
		}

		WebUTIL.setAttributeInSession(request, OpdConfig.ICD_ENTRY_FORM_SELECTED_PATIENT_SET, setSelPats);
		WebUTIL.setAttributeInSession(request, OpdConfig.ICD_ENTRY_FORM_SEEN_PATIENT_SET, setSeenPats);
		WebUTIL.setAttributeInSession(request, OpdConfig.ICD_ENTRY_FORM_PATIENT_MAP, mapPatients);
		objFB_p.setSelectedPatLen(Integer.toString(setSelPats.size()));
	}

	// Saving Patient ICD Detail
	public static boolean savePatientICDCodesDtl(ICDEntryFormFB objFB_p, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(request);
						
			setSelectedPatICDCodes(objFB_p, request);
			
			Set<String> setSelPats = null;
			Set<String> setSeenPats = null;
			Map<String, PatientDetailVO> mapPatients  = null;
			mapPatients = (Map<String, PatientDetailVO>) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_MAP);
			setSeenPats = (Set<String>)session.getAttribute(OpdConfig.ICD_ENTRY_FORM_SEEN_PATIENT_SET);

			Map<String,List<EpisodeDiagnosisVO>> mapPatDiag = new HashMap<String, List<EpisodeDiagnosisVO>>();
			if(session.getAttribute(OpdConfig.ICD_ENTRY_FORM_SELECTED_PATIENT_SET)!=null)
			{
				setSelPats = (Set<String>)session.getAttribute(OpdConfig.ICD_ENTRY_FORM_SELECTED_PATIENT_SET);
				for(String patCRNo : setSelPats)
				{
					List<EpisodeDiagnosisVO> lstPatDiag = new ArrayList<EpisodeDiagnosisVO>();
					PatientDetailVO voPatient = mapPatients.get(patCRNo);
					String[] icds = voPatient.getPatDiagnosisDtl().split("#");
					for(String icdCode : icds)
					{
						EpisodeDiagnosisVO voEpiDiag = new EpisodeDiagnosisVO();
						HelperMethods.populate(voEpiDiag, voPatient);
						voEpiDiag.setDiagnosticTypeCode(OpdConfig.DIAGNOSIS_TYPE_PROVISIONAL);
						voEpiDiag.setDiagnosticCode(icdCode);
						voEpiDiag.setDiagnosisCodeType(OpdConfig.CHOICE_ICD_CODE);
						voEpiDiag.setIsRepeat(OpdConfig.DIAGNOSIS_IS_REPEAT_NEW);
						
						lstPatDiag.add(voEpiDiag);
					}
					mapPatDiag.put(patCRNo, lstPatDiag);
				}
				ICDEntryFormDATA.saveOpdPatientsICDData(mapPatDiag, setSeenPats, userVO);
				objStatus.add(Status.NEW, "Records Saved Successfully","");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			flag = false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			flag = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	public static void getOrderBy(String orderFlag, ICDEntryFormFB objFB_p, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		try
		{
			setSelectedPatICDCodes(objFB_p, request);

			// Getting Patient List
			List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST);
			if(lstPatientDtl!=null && lstPatientDtl.size()>0)
			{
				for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(orderFlag);
	
				
				if (objFB_p.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(objFB_p, request);
				else if (objFB_p.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(objFB_p, request);
			}
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void getInOrder(ICDEntryFormFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		// Getting Patient List
		List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
		lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST);
		if(lstPatientDtl!=null && lstPatientDtl.size()>0)
		{
			for (PatientDetailVO vo : lstPatientDtl)	vo.setSortType(Config.SORT_TYPE_ASC);
			Collections.sort(lstPatientDtl);
	
			// Setting Patient List in Session after Sorting
			session.setAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST, lstPatientDtl);
		}
	}

	public static void getInDscOrder(ICDEntryFormFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		// Getting Patient List
		List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
		lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST);
		if(lstPatientDtl!=null && lstPatientDtl.size()>0)
		{
			for (PatientDetailVO vo : lstPatientDtl)	vo.setSortType(Config.SORT_TYPE_DSC);
			Collections.sort(lstPatientDtl);
	
			// Setting Patient List in Session after Sorting
			session.setAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST, lstPatientDtl);
		}
	}
}
