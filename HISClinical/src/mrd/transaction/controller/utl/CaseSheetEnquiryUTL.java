package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CaseSheetEnquiryDATA;
import mrd.transaction.controller.fb.CaseSheetEnquiryFB;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;

public class CaseSheetEnquiryUTL extends ControllerUTIL 
{
	public static boolean getEssentials(CaseSheetEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		Map essentialMap=null;
		try
		{
		
			essentialMap=CaseSheetEnquiryDATA.getCaseSheetEnquiryEssentials(getUserVO(request));
			WebUTIL.setMapInSession(essentialMap,request);
			
			fb.setEnquiryType(MrdConfig.CASE_SHEET_ENQUIRY_PATIENT_WISE);
			objStatus.add(Status.NEW);
			// objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		
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
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	public static void searchPatientCaseSheet(CaseSheetEnquiryFB _fb, HttpServletRequest _rq){
		//HttpSession session= _rq.getSession();
		UserVO userVO=getUserVO(_rq);
		Status objStatus=new Status();
		CaseSheetEnquiryVO caseSheetEnquiryVO=new CaseSheetEnquiryVO();
		CommonCaseSheetEnquiryVO[] commonCaseSheetEnquiryVOs=null;
		try{
			
			HelperMethods.populate(caseSheetEnquiryVO, _fb);
			commonCaseSheetEnquiryVOs=CaseSheetEnquiryDATA.searchPatientCaseSheet(caseSheetEnquiryVO,userVO);
			
			CommonCaseSheetEnquiryVO[] arrayCommonEnquiryVOs=new CommonCaseSheetEnquiryVO[commonCaseSheetEnquiryVOs.length];
			for(int i=0;i<commonCaseSheetEnquiryVOs.length;i++)
			{
				arrayCommonEnquiryVOs[i]=new CommonCaseSheetEnquiryVO();
				HelperMethods.populatetToNullOrEmpty(commonCaseSheetEnquiryVOs[i], arrayCommonEnquiryVOs[i]);
			}
			
			if(commonCaseSheetEnquiryVOs!=null)
			{
				for(int i=0;i<commonCaseSheetEnquiryVOs.length;i++)
				{
					if(commonCaseSheetEnquiryVOs[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_NOT_IN_MRD))
					{
						commonCaseSheetEnquiryVOs[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_NOT_IN_MRD_LABEL);
					}
					if(commonCaseSheetEnquiryVOs[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_IN_MRD))
					{
						commonCaseSheetEnquiryVOs[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_IN_MRD_LABEL);
					}
					if(commonCaseSheetEnquiryVOs[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_ARCHIVED))
					{
						commonCaseSheetEnquiryVOs[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_ARCHIVED_LABEL);
					}
					if(commonCaseSheetEnquiryVOs[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_ISSUE))
					{
						commonCaseSheetEnquiryVOs[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_ISSUE_LABEL);
					}
					if(commonCaseSheetEnquiryVOs[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_LOST))
					{
						commonCaseSheetEnquiryVOs[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_LOST_LABEL);
					}
					if(commonCaseSheetEnquiryVOs[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_DESTROY))
					{
						commonCaseSheetEnquiryVOs[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_DESTROY_LABEL);
					}
					if(commonCaseSheetEnquiryVOs[i].getRecordType().equals(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET))
					{
						commonCaseSheetEnquiryVOs[i].setRecordTypeLabel(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET_LABEL);
					}
					if(commonCaseSheetEnquiryVOs[i].getRecordType().equals(MrdConfig.RECORD_TYPE_MLC_CASESHEET))
					{
						commonCaseSheetEnquiryVOs[i].setRecordTypeLabel(MrdConfig.RECORD_TYPE_MLC_CASESHEET_LABEL);
					}
				}
			}
			WebUTIL.setAttributeInSession(_rq,MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY, commonCaseSheetEnquiryVOs);
			objStatus.add(Status.NEW);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.NEW);
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally
		{
			
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static void getDischargeDetail(CaseSheetEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		UserVO userVO=getUserVO(_rq);
		Status objStatus=new Status();
		MrdRecordDtlVO[] mrdRecordDtlVO=null;
		//CaseSheetEnquiryVO caseSheetEnquiryVO=new CaseSheetEnquiryVO();
		CommonCaseSheetEnquiryVO _commonCaseSheetEnquiryVO=new CommonCaseSheetEnquiryVO();
		try{
			
			CommonCaseSheetEnquiryVO[] commonCaseSheetEnquiryVOs=(CommonCaseSheetEnquiryVO[])session.getAttribute(MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY);
			_commonCaseSheetEnquiryVO=commonCaseSheetEnquiryVOs[Integer.parseInt(_fb.getSelectedIndex())];
			String count=_commonCaseSheetEnquiryVO.getCount();
			
			if(!count.equals("0"))
			{
				mrdRecordDtlVO=CaseSheetEnquiryDATA.fetchRecordStorageDetail(_commonCaseSheetEnquiryVO,userVO);
			}
			
			if(mrdRecordDtlVO!=null)
			{
				for(int i=0;i<mrdRecordDtlVO.length;i++)
				{
					if(mrdRecordDtlVO[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_NOT_IN_MRD))
					{
						mrdRecordDtlVO[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_NOT_IN_MRD_LABEL);
					}
					if(mrdRecordDtlVO[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_IN_MRD))
					{
						mrdRecordDtlVO[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_IN_MRD_LABEL);
					}
					if(mrdRecordDtlVO[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_ARCHIVED))
					{
						mrdRecordDtlVO[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_ARCHIVED_LABEL);
					}
					if(mrdRecordDtlVO[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_ISSUE))
					{
						mrdRecordDtlVO[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_ISSUE_LABEL);
					}
					if(mrdRecordDtlVO[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_LOST))
					{
						mrdRecordDtlVO[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_LOST_LABEL);
					}
					if(mrdRecordDtlVO[i].getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_DESTROY))
					{
						mrdRecordDtlVO[i].setRecordStatusLabel(MrdConfig.MRD_RECORD_STATUS_DESTROY_LABEL);
					}
					if(mrdRecordDtlVO[i].getRecordType().equals(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET))
					{
						mrdRecordDtlVO[i].setRecordTypeLabel(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET_LABEL);
					}
					if(mrdRecordDtlVO[i].getRecordType().equals(MrdConfig.RECORD_TYPE_MLC_CASESHEET))
					{
						mrdRecordDtlVO[i].setRecordTypeLabel(MrdConfig.RECORD_TYPE_MLC_CASESHEET_LABEL);
					}
				}
			}
			
			WebUTIL.setAttributeInSession(_rq, MrdConfig.CASE_SHEET_ENQUIRY_VO, _commonCaseSheetEnquiryVO);
			WebUTIL.setAttributeInSession(_rq, MrdConfig.CASE_SHEET_ENQUIRY_RECORD_STORAGE_VO, mrdRecordDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally
		{
			
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static void getOrderByCrNo(CaseSheetEnquiryFB _fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		//CommonEnquiryVO[] commonEnqVOs;
		CommonCaseSheetEnquiryVO[] _commonCaseSheetEnquiryVO;
		try
		{
			_commonCaseSheetEnquiryVO = (CommonCaseSheetEnquiryVO[]) session.getAttribute(MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY);
			for (int i = 0; i < _commonCaseSheetEnquiryVO.length; i++)
			{
				_commonCaseSheetEnquiryVO[i].setOrderBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO);
			}
			if (_fb.getOrder().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, request);
			else if (_fb.getOrder().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	public static void getInOrder(CaseSheetEnquiryFB _fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		CommonCaseSheetEnquiryVO[] _commonCaseSheetEnquiryVO;
		_commonCaseSheetEnquiryVO = (CommonCaseSheetEnquiryVO[]) session.getAttribute(MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY);
		List list = new ArrayList();
		for (int i = 0; i < _commonCaseSheetEnquiryVO.length; i++)
		{
			_commonCaseSheetEnquiryVO[i].setSortType(Config.SORT_TYPE_ASC);
			list.add(_commonCaseSheetEnquiryVO[i]);
		}
		Collections.sort(list);
		System.out.println(list);

		_commonCaseSheetEnquiryVO = (CommonCaseSheetEnquiryVO[]) list.toArray(_commonCaseSheetEnquiryVO);
		session.setAttribute(MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY, _commonCaseSheetEnquiryVO);
		System.out.println("");
	}

	// *
	public static void getInDscOrder(CaseSheetEnquiryFB _fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		CommonCaseSheetEnquiryVO[] _commonCaseSheetEnquiryVO;
		_commonCaseSheetEnquiryVO = (CommonCaseSheetEnquiryVO[]) session.getAttribute(MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY);
		List list = new ArrayList();
		for (int i = 0; i < _commonCaseSheetEnquiryVO.length; i++)
		{
			_commonCaseSheetEnquiryVO[i].setSortType(Config.SORT_TYPE_DSC);
			list.add(_commonCaseSheetEnquiryVO[i]);
		}
		Collections.sort(list);
		System.out.println(list);

		_commonCaseSheetEnquiryVO = (CommonCaseSheetEnquiryVO[]) list.toArray(_commonCaseSheetEnquiryVO);
		session.setAttribute(MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY, _commonCaseSheetEnquiryVO);
		System.out.println("");
	}
	
	public static void getOrderByName(CaseSheetEnquiryFB _fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		CommonCaseSheetEnquiryVO[] _commonCaseSheetEnquiryVO;
		
		try
		{
			_commonCaseSheetEnquiryVO = (CommonCaseSheetEnquiryVO[]) session.getAttribute(MrdConfig.COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY);
			for (int i = 0; i < _commonCaseSheetEnquiryVO.length; i++)
			{
				_commonCaseSheetEnquiryVO[i].setOrderBy(Config.OPD_PATIENT_OREDRE_BY_NAME);
			}
			if (_fb.getOrder().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, request);
			else if (_fb.getOrder().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	public static void getCaseSheetEnquiry(CaseSheetEnquiryFB _fb, HttpServletRequest request)
	{
		//HttpSession session = request.getSession();
		Status objStatus = new Status();
		//CommonCaseSheetEnquiryVO[] _commonCaseSheetEnquiryVO;
		try
		{
			objStatus.add(Status.NEW);
			
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	public static void popUpDiagnosis(CaseSheetEnquiryFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		//List icdCodes = new ArrayList();
		HttpSession session = _rq.getSession();
		try
		{
			session.removeAttribute(MrdConfig.CASE_SHEET_ENQUIRY_DIAGNOSIS_CODE_LIST);
				
			
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static void searchIcdCode(CaseSheetEnquiryFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List icdCodes = new ArrayList();
		try
		{
			String searchCode = fb.getSearchCode();
			String searchDisease = fb.getSearchDisease();
			
			
				if (searchCode == null || searchCode.trim().equals("")) if (searchDisease == null || searchDisease.trim().equals("")) System.out
						.println("gfhfh");
				else icdCodes = CaseSheetEnquiryDATA.getDiseaseName(searchDisease, getUserVO(_rq));
				else icdCodes = CaseSheetEnquiryDATA.getIcdCodes(searchCode, getUserVO(_rq));

				if (icdCodes == null) throw new HisRecordNotFoundException("No Code Exists");
				WebUTIL.getSession(_rq).setAttribute(MrdConfig.CASE_SHEET_ENQUIRY_DIAGNOSIS_CODE_LIST, icdCodes);
			
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
}
