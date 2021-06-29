package opd.icdsearchengine.util;

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
import hisglobal.vo.UserVO;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import opd.OpdConfig;
import opd.icdsearchengine.ICDSearchEngineConfig;
import opd.icdsearchengine.data.ICDSearchEngineDATA;
import opd.icdsearchengine.fb.ICDEngineDiseaseDetailFB;
import opd.icdsearchengine.fb.ICDSearchEngineFB;
import opd.icdsearchengine.vo.ICDDiseaseDetailVO;
import opd.icdsearchengine.vo.ICDSearchEngineVO;
import opd.icdsearchengine.vo.ResultDiseaseListVO;
import opd.icdsearchengine.vo.ResultIndexListVO;

public class ICDSearchEngineUTL extends ControllerUTIL
{
	// Setting ICD Searching Essentials
	public static void setICDSearchingEssentials(ICDSearchEngineFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map<String, Object> mapEssential = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			
			_fb = setInitailSearchCriteria(_fb);
			// Getting Volume 1 Essentials
			if(_fb.getStrCurOption().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_1))
			{
				mapEssential = ICDSearchEngineDATA.getICDSearchingVolume1Essentials(userVO);
				WebUTIL.setMapInSession(mapEssential, _request);
			}
			
			// Getting Reports Essentials
			if(_fb.getStrCurOption().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_TABULAR_LIST))
			{
				mapEssential = ICDSearchEngineDATA.getICDSearchingReportsEssentials(userVO);
				WebUTIL.setMapInSession(mapEssential, _request);
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	private static ICDSearchEngineFB setInitailSearchCriteria(ICDSearchEngineFB _fb)
	{
		// Volume 1 Search
		if(_fb.getStrCurOption().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_1))
		{
			_fb.setStrRecordType(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			_fb.setStrOrderBy(ICDSearchEngineConfig.ORDER_BY_DISEASE_NAME);
			_fb.setStrSearchType(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME);
		}
		// Volume 3 Search
		if(_fb.getStrCurOption().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_1))
		{
			_fb.setStrSearchType(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN);
		}
		return _fb;
	}
	

	// Getting Data for Volume 1 Search
	public static void getICDSearchingResulforVol1(ICDSearchEngineFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List<ResultDiseaseListVO> lstResult = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
			
			setEngineVO(_fb, engineVO);
			
			lstResult = ICDSearchEngineDATA.getResultICDDiseaseList(engineVO, userVO);
			
			WebUTIL.setAttributeInSession(_request, ICDSearchEngineConfig.ICD_ENGINE_RESULT_ICD_DISEASE_LIST, lstResult);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	private static void setEngineVO(ICDSearchEngineFB _fb, ICDSearchEngineVO _engineVO)
	{
		try
		{
			//if(_fb.getStrCurOption().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_1))
			{
				if(_fb.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_ICD_CODE))
				{
					StringBuffer icdCode = new StringBuffer(_fb.getStrIcdCodeMain().trim());
					if(_fb.getStrIcdCodeSub()!=null && !_fb.getStrIcdCodeSub().trim().equals(""))
					{
						icdCode.append(".");
						icdCode.append(_fb.getStrIcdCodeSub().trim());
					}
					_fb.setStrIcdCodeName(icdCode.toString());
				}
				if(_fb.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE))
				{
					StringBuffer icdCode = new StringBuffer(_fb.getStrIcdCodeMain().trim());
					if(_fb.getStrIcdCodeSub()!=null && !_fb.getStrIcdCodeSub().trim().equals(""))
					{
						icdCode.append(".");
						icdCode.append(_fb.getStrIcdCodeSub().trim());
					}
					_fb.setStrExactIcdCode(icdCode.toString());
				}
				if(_fb.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME))
				{
					if(_fb.getStrIcdDiseaseName()!=null && _fb.getStrIcdDiseaseName().trim().equals(""))
						_fb.setStrIcdDiseaseName(null);
					
					if(_fb.getStrMainDiseaseTypeCode()!=null && _fb.getStrMainDiseaseTypeCode().trim().equals("-1"))
						_fb.setStrMainDiseaseTypeCode(null);
	
					if(_fb.getStrDiseaseTypeCode()!=null && _fb.getStrDiseaseTypeCode().trim().equals("-1"))
						_fb.setStrDiseaseTypeCode(null);
					
					if(_fb.getStrChronicDiseaseCode()!=null && _fb.getStrChronicDiseaseCode().trim().equals("-1"))
						_fb.setStrChronicDiseaseCode(null);
				}
				if(_fb.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN))
				{
					_fb.setStrSearchType(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME);
					_fb.setStrRecordType(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
					_fb.setStrOrderBy(ICDSearchEngineConfig.ORDER_BY_DISEASE_NAME);
					_fb.setStrIcdDiseaseName(_fb.getStrDiseaseName());
					if(_fb.getStrIndexSearchString()==null || _fb.getStrIndexSearchString().equals(""))
						_fb.setStrIndexSearchString(_fb.getStrDiseaseName());
					if(_fb.getStrIndexCode()==null || _fb.getStrIndexCode().equals(""))
						_fb.setStrIndexCode(null);
					_fb.setStrMainDiseaseTypeCode(null);
					_fb.setStrDiseaseTypeCode(null);
					_fb.setStrChronicDiseaseCode(null);
				}
			}
			/*else if(_fb.getStrCurOption().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_VOLUME_BOTH))
			{
				_fb.setStrSearchType(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME);
				_fb.setStrRecordType(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
				_fb.setStrOrderBy(ICDSearchEngineConfig.ORDER_BY_DISEASE_NAME);
				_fb.setStrIcdDiseaseName(_fb.getStrDiseaseName());
				_fb.setStrIndexSearchString(_fb.getStrDiseaseName());
				_fb.setStrMainDiseaseTypeCode(null);
				_fb.setStrDiseaseTypeCode(null);
				_fb.setStrChronicDiseaseCode(null);
			}*/
			HelperMethods.populate(_engineVO, _fb);
		}
		catch(Exception e)
		{			
		}
	}

	// Getting Data for Volume 1 Sub Disease Search
	public static StringBuffer getICDSearchingResulforVol1Sub(ICDSearchEngineVO _engineVO, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List<ResultDiseaseListVO> lstResult = null;
		StringBuffer sb = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lstResult = ICDSearchEngineDATA.getResultICDSubDiseaseList(_engineVO, userVO);
			sb = new StringBuffer();
			sb.append("[");
			for(ResultDiseaseListVO vo : lstResult)
			{
				sb.append("{");
				sb.append("strRecordType");sb.append(":");
				sb.append("\'");sb.append(vo.getRecordType());sb.append("\'");sb.append(",");
				sb.append("strIcdCode");sb.append(":");
				sb.append("\'");sb.append(vo.getDiseaseCode());sb.append("\'");sb.append(",");
				sb.append("strSearialNo");sb.append(":");
				sb.append("\'");sb.append(vo.getSlNo());sb.append("\'");sb.append(",");
				sb.append("strDiseaseName");sb.append(":");
				sb.append("\'");sb.append(vo.getFullDiseaseName());sb.append("\'");sb.append(",");
				sb.append("strParentCode");sb.append(":");
				sb.append("\'");sb.append(vo.getParentCode());sb.append("\'");
				sb.append("}");sb.append(",");
			}
			if(lstResult.size()>0)	sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
			sb = null;
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
			sb = null;
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
			sb = null;
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
			sb = null;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return sb;
	}

	// Getting ICD Disease Detail Data at time of New
	public static void getICDDiseaseDetailNEW(ICDEngineDiseaseDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			if(_fb.getDiseaseCode()!=null && !_fb.getDiseaseCode().trim().equals(""))
			{
				if(_fb.getRecordType()==null || _fb.getRecordType().trim().equals(""))
				{
					_fb.setRecordType(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
					_fb.setSlNo("-1");
				}
				if(_fb.getSlNo()==null && _fb.getSlNo().trim().equals(""))
					_fb.setSlNo("-1");
				
				ICDDiseaseDetailVO diseaseDtlVO = new ICDDiseaseDetailVO();
				HelperMethods.populate(diseaseDtlVO, _fb);
				diseaseDtlVO  = ICDSearchEngineDATA.getDetailICDDiseaseVO(diseaseDtlVO, userVO);
				setFBFromDiseaseDtlVO(_fb, diseaseDtlVO);

				_fb.setStrDetailMode("NEW");
				List<ICDDiseaseDetailVO> lstVOs = new ArrayList<ICDDiseaseDetailVO>();
				lstVOs.add(diseaseDtlVO);
				_fb.setCurIndex("0");
				_fb.setStrIsNext(false);
				_fb.setStrIsPrev(false);
				WebUTIL.setAttributeInSession(_request, ICDSearchEngineConfig.ICD_ENGINE_DETAIL_ICD_DISEASE_VO_VIEW_LIST, lstVOs);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Getting ICD Disease Detail Data at time of Show
	public static void getICDDiseaseDetailSHOW(ICDEngineDiseaseDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			if(_fb.getDiseaseCode()!=null && !_fb.getDiseaseCode().trim().equals(""))
			{
				if(_fb.getRecordType()==null || _fb.getRecordType().trim().equals(""))
				{
					_fb.setRecordType(ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
					_fb.setSlNo("-1");
				}
				if(_fb.getSlNo()==null && _fb.getSlNo().trim().equals(""))
					_fb.setSlNo("-1");
				
				ICDDiseaseDetailVO diseaseDtlVO = new ICDDiseaseDetailVO();
				HelperMethods.populate(diseaseDtlVO, _fb);
				diseaseDtlVO  = ICDSearchEngineDATA.getDetailICDDiseaseVO(diseaseDtlVO, userVO);
				setFBFromDiseaseDtlVO(_fb, diseaseDtlVO);

				List<ICDDiseaseDetailVO> lstVOs = null;
				lstVOs = (ArrayList<ICDDiseaseDetailVO>)_request.getSession().getAttribute(ICDSearchEngineConfig.ICD_ENGINE_DETAIL_ICD_DISEASE_VO_VIEW_LIST);
				int idn = Integer.parseInt(_fb.getCurIndex())+1;
				for(int loop=idn;loop<lstVOs.size();loop++)
					lstVOs.remove(idn);
				lstVOs.add(diseaseDtlVO);				
				_fb.setCurIndex(Integer.toString(idn));
				if(idn>0)	_fb.setStrIsPrev(true);
				else		_fb.setStrIsPrev(false);
				_fb.setStrIsNext(false);
				WebUTIL.setAttributeInSession(_request, ICDSearchEngineConfig.ICD_ENGINE_DETAIL_ICD_DISEASE_VO_VIEW_LIST, lstVOs);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Getting ICD Disease Detail Data at time of Prev
	public static void getICDDiseaseDetailPREV(ICDEngineDiseaseDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			List<ICDDiseaseDetailVO> lstVOs = null;
			lstVOs = (ArrayList<ICDDiseaseDetailVO>)_request.getSession().getAttribute(ICDSearchEngineConfig.ICD_ENGINE_DETAIL_ICD_DISEASE_VO_VIEW_LIST);
			int idn = Integer.parseInt(_fb.getCurIndex())-1;
			_fb.setCurIndex(Integer.toString(idn));
			if(idn>0)	_fb.setStrIsPrev(true);
			else		_fb.setStrIsPrev(false);
			if(idn<(lstVOs.size()-1))	_fb.setStrIsNext(true);
			else						_fb.setStrIsNext(false);
			ICDDiseaseDetailVO diseaseDtlVO = lstVOs.get(idn);
			setFBFromDiseaseDtlVO(_fb, diseaseDtlVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Getting ICD Disease Detail Data at time of Next
	public static void getICDDiseaseDetailNEXT(ICDEngineDiseaseDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			List<ICDDiseaseDetailVO> lstVOs = null;
			lstVOs = (ArrayList<ICDDiseaseDetailVO>)_request.getSession().getAttribute(ICDSearchEngineConfig.ICD_ENGINE_DETAIL_ICD_DISEASE_VO_VIEW_LIST);
			int idn = Integer.parseInt(_fb.getCurIndex())+1;
			_fb.setCurIndex(Integer.toString(idn));
			if(idn>0)	_fb.setStrIsPrev(true);
			else		_fb.setStrIsPrev(false);
			if(idn<(lstVOs.size()-1))	_fb.setStrIsNext(true);
			else						_fb.setStrIsNext(false);
			ICDDiseaseDetailVO diseaseDtlVO = lstVOs.get(idn);
			setFBFromDiseaseDtlVO(_fb, diseaseDtlVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	/*// Getting ICD Disease Detail Data
	public static ICDDiseaseDetailVO getICDDiseaseDetail(ICDEngineDiseaseDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		ICDDiseaseDetailVO diseaseDtlVO = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			diseaseDtlVO = new ICDDiseaseDetailVO();
			if(_fb.getDiseaseCode()!=null && !_fb.getDiseaseCode().trim().equals("") 
					&& _fb.getRecordType()!=null && !_fb.getRecordType().trim().equals("")
					&& _fb.getSlNo()!=null && !_fb.getSlNo().trim().equals(""))
			{
				HelperMethods.populate(diseaseDtlVO, _fb);
				diseaseDtlVO  = ICDSearchEngineDATA.getDetailICDDiseaseVO(diseaseDtlVO, userVO);
				setFBFromDiseaseDtlVO(_fb, diseaseDtlVO);
				WebUTIL.setAttributeInSession(_request, ICDSearchEngineConfig.ICD_ENGINE_DETAIL_ICD_DISEASE_VO, diseaseDtlVO);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return diseaseDtlVO;
	}*/
	
	private static void setFBFromDiseaseDtlVO(ICDEngineDiseaseDetailFB _fb, ICDDiseaseDetailVO _detailVO)
	{
		try
		{
			HelperMethods.populate(_fb, _detailVO);
			// Sub Groups
			if(_fb.getHaveSubGroups()!=null && !_fb.getHaveSubGroups().trim().equals(""))
			{
				String[] arr = _fb.getHaveSubGroups().split("#");
				String[] arrCode = new String[arr.length];
				String[] arrName = new String[arr.length];
				int i=0;
				for(String subgrp : arr)
				{
					arrName[i] = subgrp.split("@")[0];
					arrCode[i++] = subgrp.split("@")[1];
				}
				_fb.setIcdSubgroupCode(arrCode);
				_fb.setIcdSubgroup(arrName);
			}
			
			// Sub Diseases
			if(_fb.getHaveChildren()!=null && !_fb.getHaveChildren().trim().equals(""))
			{
				String[] arr = _fb.getHaveChildren().split("#");
				String[] arrCode = new String[arr.length];
				String[] arrName = new String[arr.length];
				int i=0;
				for(String subgrp : arr)
				{
					arrName[i] = subgrp.split("@")[0];
					arrCode[i++] = subgrp.split("@")[1];
				}
				_fb.setIcdSubdiseaseCodes(arrCode);
				_fb.setIcdSubdiseases(arrName);
			}

			// Synonyms
			if(_fb.getHaveSynonyms()!=null && !_fb.getHaveSynonyms().trim().equals(""))
			{
				String[] arr = _fb.getHaveSynonyms().split("#");
				String[] arrCode = new String[arr.length];
				String[] arrName = new String[arr.length];
				int i=0;
				for(String subgrp : arr)
				{
					arrName[i] = subgrp.split("@")[0];
					arrCode[i++] = subgrp.split("@")[1];
				}
				_fb.setArrStrSynonymCodes(arrCode);
				_fb.setArrStrSynonyms(arrName);
			}

			// Includes
			if(_fb.getHaveIncludes()!=null && !_fb.getHaveIncludes().trim().equals(""))
			{
				String[] arr = _fb.getHaveIncludes().split("#");
				String[] arrCode = new String[arr.length];
				String[] arrName = new String[arr.length];
				int i=0;
				for(String subgrp : arr)
				{
					arrName[i] = subgrp.split("@")[0];
					arrCode[i++] = subgrp.split("@")[1];
				}
				_fb.setArrStrIncludeCodes(arrCode);
				_fb.setArrStrIncludes(arrName);
			}
		
			// Excludes
			if(_fb.getHaveExcludes()!=null && !_fb.getHaveExcludes().trim().equals(""))
			{
				String[] arr = _fb.getHaveExcludes().split("#");
				String[] arrCode = new String[arr.length];
				String[] arrName = new String[arr.length];
				int i=0;
				for(String subgrp : arr)
				{
					arrName[i] = subgrp.split("@")[0];
					arrCode[i++] = subgrp.split("@")[1];
				}
				_fb.setArrStrExcludeCodes(arrCode);
				_fb.setArrStrExcludes(arrName);
			}
		
			// Chronic Disease
			if(_fb.getHaveChronicDiseases()!=null && !_fb.getHaveChronicDiseases().trim().equals(""))
			{
				String[] arr = _fb.getHaveChronicDiseases().split("#");
				String[] arrCode = new String[arr.length];
				String[] arrName = new String[arr.length];
				int i=0;
				for(String subgrp : arr)
				{
					arrName[i] = subgrp.split("@")[0];
					arrCode[i++] = subgrp.split("@")[1];
				}
				_fb.setArrStrChronicDiseaseCodes(arrCode);
				_fb.setArrStrChronicDiseases(arrName);
			}
			
		}
		catch(Exception e)
		{			
		}
	}

	// Getting Data for Volume 3 Search
	public static void getICDSearchingResulforVol3(ICDSearchEngineFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List<ResultIndexListVO> lstResult = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
			
			setEngineVO(_fb, engineVO);
			
			lstResult = ICDSearchEngineDATA.getResultIndexTermList(engineVO, userVO);
			
			WebUTIL.setAttributeInSession(_request, ICDSearchEngineConfig.ICD_ENGINE_RESULT_INDEX_TERM_LIST, lstResult);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Getting Data for Volume 3 Auto Complete Search
	public static StringBuffer getICDSearchingIndexAutoCompleteVol3(HttpServletRequest _request)
	{
		List<ResultIndexListVO> lst = null;
		StringBuffer sb = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lst = ICDSearchEngineDATA.getIndexTermList(userVO);
			sb = new StringBuffer();
			sb.append("[");
			for(ResultIndexListVO vo : lst)
			{
				sb.append("{");
				sb.append("indexCode");sb.append(":");
				sb.append("\'");sb.append(vo.getIndexCode());sb.append("\'");sb.append(",");
				sb.append("diagnosticTerm");sb.append(":");
				sb.append("\'");sb.append(vo.getDiagnosticTerm());sb.append("\'");
				sb.append("}");sb.append(",");
			}
			if(lst.size()>0)	sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sb = null;
		}
		finally
		{
		}
		return sb;
	}

	// Getting List of Level Modofier of Index Term for Volume 3 Tree
	public static StringBuffer getIndexTermModForVol3Tree(ICDSearchEngineVO _engineVO, HttpServletRequest _request)
	{
		List<ResultIndexListVO> lst = null;
		StringBuffer sb = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lst = ICDSearchEngineDATA.getIndexModiTermList(_engineVO, userVO);
			sb = new StringBuffer();
			sb.append("[");
			for(ResultIndexListVO vo : lst)
			{
				vo = setNullToEmpty(vo);
				sb.append("{");
				sb.append("indexModifierID");sb.append(":");
				sb.append("\'");sb.append(vo.getIndexModifierID());sb.append("\'");sb.append(",");
				sb.append("indexCode");sb.append(":");
				sb.append("\'");sb.append(vo.getIndexCode());sb.append("\'");sb.append(",");
				sb.append("parentIndexModifierCode");sb.append(":");
				sb.append("\'");sb.append(vo.getParentIndexModifierCode());sb.append("\'");sb.append(",");
				sb.append("modifierLevel");sb.append(":");
				sb.append("\'");sb.append(vo.getModifierLevel());sb.append("\'");sb.append(",");
				sb.append("modifier");sb.append(":");
				sb.append("\'");sb.append(vo.getModifier());sb.append("\'");sb.append(",");
				sb.append("isWith");sb.append(":");
				sb.append("\'");sb.append(vo.getIsWith());sb.append("\'");sb.append(",");
				sb.append("diseaseCode");sb.append(":");
				sb.append("\'");sb.append(vo.getDiseaseCode());sb.append("\'");sb.append(",");
				sb.append("dualDiseaseCode");sb.append(":");
				sb.append("\'");sb.append(vo.getDualDiseaseCode());sb.append("\'");sb.append(",");
				sb.append("seeTerm");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeTerm());sb.append("\'");sb.append(",");
				sb.append("seeTermCode");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeTermCode());sb.append("\'");sb.append(",");
				sb.append("seeIndexModifierId");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeIndexModifierId());sb.append("\'");sb.append(",");
				sb.append("seeAlsoTerm");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeAlsoTerm());sb.append("\'");sb.append(",");
				sb.append("seeAlsoTermCode");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeAlsoTermCode());sb.append("\'");sb.append(",");
				sb.append("seeAlsoIndexModifierId");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeAlsoIndexModifierId());sb.append("\'");sb.append(",");
				sb.append("remark");sb.append(":");
				sb.append("\'");sb.append(vo.getRemark());sb.append("\'");sb.append(",");
				sb.append("haveTree");sb.append(":");
				sb.append("\'");sb.append(vo.getHaveTree());sb.append("\'");
				sb.append("}");sb.append(",");
			}
			if(lst.size()>0)	sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sb = null;
		}
		finally
		{
		}
		return sb;
	}

	// Getting List of Level Modofier of Modifier Term for Volume 3 Sub Tree
	public static StringBuffer getModifierTermVol3Tree(ICDSearchEngineVO _engineVO, HttpServletRequest _request)
	{
		List<ResultIndexListVO> lst = null;
		StringBuffer sb = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lst = ICDSearchEngineDATA.getModifierTermTreeList(_engineVO, userVO);
			sb = new StringBuffer();
			sb.append("[");
			for(ResultIndexListVO vo : lst)
			{
				vo = setNullToEmpty(vo);
				sb.append("{");
				sb.append("indexModifierID");sb.append(":");
				sb.append("\'");sb.append(vo.getIndexModifierID());sb.append("\'");sb.append(",");
				sb.append("indexCode");sb.append(":");
				sb.append("\'");sb.append(vo.getIndexCode());sb.append("\'");sb.append(",");
				sb.append("parentIndexModifierCode");sb.append(":");
				sb.append("\'");sb.append(vo.getParentIndexModifierCode());sb.append("\'");sb.append(",");
				sb.append("modifierLevel");sb.append(":");
				sb.append("\'");sb.append(vo.getModifierLevel());sb.append("\'");sb.append(",");
				sb.append("modifier");sb.append(":");
				sb.append("\'");sb.append(vo.getModifier());sb.append("\'");sb.append(",");
				sb.append("isWith");sb.append(":");
				sb.append("\'");sb.append(vo.getIsWith());sb.append("\'");sb.append(",");
				sb.append("diseaseCode");sb.append(":");
				sb.append("\'");sb.append(vo.getDiseaseCode());sb.append("\'");sb.append(",");
				sb.append("dualDiseaseCode");sb.append(":");
				sb.append("\'");sb.append(vo.getDualDiseaseCode());sb.append("\'");sb.append(",");
				sb.append("seeTerm");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeTerm());sb.append("\'");sb.append(",");
				sb.append("seeTermCode");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeTermCode());sb.append("\'");sb.append(",");
				sb.append("seeIndexModifierId");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeIndexModifierId());sb.append("\'");sb.append(",");
				sb.append("seeAlsoTerm");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeAlsoTerm());sb.append("\'");sb.append(",");
				sb.append("seeAlsoTermCode");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeAlsoTermCode());sb.append("\'");sb.append(",");
				sb.append("seeAlsoIndexModifierId");sb.append(":");
				sb.append("\'");sb.append(vo.getSeeAlsoIndexModifierId());sb.append("\'");sb.append(",");
				sb.append("remark");sb.append(":");
				sb.append("\'");sb.append(vo.getRemark());sb.append("\'");sb.append(",");
				sb.append("haveTree");sb.append(":");
				sb.append("\'");sb.append(vo.getHaveTree());sb.append("\'");
				sb.append("}");sb.append(",");
			}
			if(lst.size()>0)	sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sb = null;
		}
		finally
		{
		}
		return sb;
	}
	
	private static ResultIndexListVO setNullToEmpty(ResultIndexListVO _vo)
	{
		try
		{
			Class strClass = _vo.getClass();
			Method[] arrMethods = strClass.getMethods();
			HashMap mpGetters = new HashMap();
			HashMap mpSetters = new HashMap();
			for (int i = 0; i < arrMethods.length; i++)
				if (arrMethods[i].getName().indexOf("set") == 0)
					mpSetters.put(arrMethods[i].getName().substring(3), new Integer(i));
			for (int i = 0; i < arrMethods.length; i++)
				if (arrMethods[i].getName().indexOf("get") == 0)
					if (mpSetters.containsKey(arrMethods[i].getName().substring(3)))
					{
						int idx = ((Integer) mpSetters.get(arrMethods[i].getName().substring(3))).intValue();
						Object str = arrMethods[i].invoke(_vo, null);
						if(str==null)	arrMethods[idx].invoke(_vo, new Object[]{""});
					}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException("Helpermethods.populate::" + e);
		}
		return _vo;
	}
	
	// Getting List of All Sub Groups of Group for Reports
	public static StringBuffer getAllSubGroupForReports(ICDSearchEngineVO _engineVO, HttpServletRequest _request)
	{
		List<Entry> lst = null;
		StringBuffer sb = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lst = ICDSearchEngineDATA.getAllSubGroupByGroup(_engineVO.getStrIcdGroupCode(), userVO);
			sb = new StringBuffer();
			sb.append("[");
			for(Entry vo : lst)
			{
				sb.append("{");
				sb.append("value");sb.append(":");
				sb.append("\'");sb.append(vo.getValue());sb.append("\'");sb.append(",");
				sb.append("label");sb.append(":");
				sb.append("\'");sb.append(vo.getLabel());sb.append("\'");
				sb.append("}");sb.append(",");
			}
			if(lst.size()>0)	sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sb = null;
		}
		finally
		{
		}
		return sb;
	}

	// Getting List of All Diseases of Sub Group for Reports
	public static StringBuffer getAllDiseasesForReports(ICDSearchEngineVO _engineVO, HttpServletRequest _request)
	{
		List<Entry> lst = null;
		StringBuffer sb = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lst = ICDSearchEngineDATA.getAllDiseasesBySubGroup(_engineVO.getStrIcdSubGroupCode(), userVO);
			sb = new StringBuffer();
			sb.append("[");
			for(Entry vo : lst)
			{
				sb.append("{");
				sb.append("value");sb.append(":");
				sb.append("\'");sb.append(vo.getValue());sb.append("\'");sb.append(",");
				sb.append("label");sb.append(":");
				sb.append("\'");sb.append(vo.getLabel());sb.append("\'");
				sb.append("}");sb.append(",");
			}
			if(lst.size()>0)	sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			sb = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sb = null;
		}
		finally
		{
		}
		return sb;
	}

	public static byte[] printReport(ICDSearchEngineFB _fb, HttpServletRequest _request, HttpServletResponse _response)
	{
		Status objStatus = new Status();
		StringBuffer bf = new StringBuffer();
		byte[] byteArray=null;
		try
		{
			UserVO userVO = getUserVO(_request);
			ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
			
				// Report Criteria & Target JRXML
			_fb.setJrxmlPath(OpdConfig.OPD_JRXML_PATH);
			_fb.setStrHospitalCode(userVO.getHospitalCode());
			if(_fb.getStrReportOption().equals("1"))
				_fb.setJrxmlName(OpdConfig.OPD_ICD_GROUP_LIST_REPORT);
			else if(_fb.getStrReportOption().equals("2"))
			{
				if(_fb.getStrIcdGroupCode().equals("0"))
					_fb.setJrxmlName(OpdConfig.OPD_ICD_SUBGROUP_LIST_REPORT);
				else
					_fb.setJrxmlName(OpdConfig.OPD_ICD_SUBGROUP_LIST_GROUPWISE_REPORT);
			}
			else if(_fb.getStrReportOption().equals("3"))
			{
				if(_fb.getStrIcdGroupCode().equals("0"))
					_fb.setJrxmlName(OpdConfig.OPD_ICD_DISEASE_LIST_REPORT);
				else if(_fb.getStrIcdSubGroupCode().equals("0"))
					_fb.setJrxmlName(OpdConfig.OPD_ICD_DISEASE_LIST_GROUPWISE_REPORT);
				else
					_fb.setJrxmlName(OpdConfig.OPD_ICD_DISEASE_LIST_SUBGROUPWISE_REPORT);
			}
			else if(_fb.getStrReportOption().equals("4"))
			{
				if(_fb.getStrIcdGroupCode().equals("0"))
					_fb.setJrxmlName(OpdConfig.OPD_ICD_SUBDISEASE_LIST_REPORT);
				else if(_fb.getStrIcdSubGroupCode().equals("0"))
					_fb.setJrxmlName(OpdConfig.OPD_ICD_SUBDISEASE_LIST_GROUPWISE_REPORT);
				else if(_fb.getStrIcdDiseaseCode().equals("0"))
					_fb.setJrxmlName(OpdConfig.OPD_ICD_SUBDISEASE_LIST_SUBGROUPWISE_REPORT);
				else
					_fb.setJrxmlName(OpdConfig.OPD_ICD_SUBDISEASE_LIST_DISEASEWISE_REPORT);
			}

			
			
			HelperMethods.populate(engineVO, _fb);
			JasperPrint jPrint = ICDSearchEngineDATA.getReport(engineVO, userVO);
			
			try
			{
				if (_fb.getStrReportType().equalsIgnoreCase(Config.PDF))
				{
					byte[] byteArrayAspdf = JasperExportManager.exportReportToPdf(jPrint);
					_response.setContentType("application/pdf");
					return byteArrayAspdf;
				}
				else
					_response.setContentType("text/html");

				String header = "<html>\n <head>\n <title></title>\n <meta http-equiv=\"Content-Type\" "
						+ "content=\"text/html;\"/>\n <style type=\"text/css\">\n "
						+ "@media print { div { display: none; }} </style>\n <script Language=javascript>" + "function printPage() \n {\n  "
						+ " window.print(); \n\n } \n\n " + "function backButton() \n { \n window.close(); \n } \n "
						+ "</script> </head>\n <body text=\"#000000\" link=\"#000000\" "
						+ "alink=\"#000000\" vlink=\"#000000\">\n <table width=\"100%\" cellpadding=\"0\" "
						+ "cellspacing=\"0\" border=\"0\">\n <tr><td width=\"50%\">&nbsp;</td><td align=\"center\">\n \n";
				String buttonHtml = "<div id=\"noPrint\" align=\"right\"><img class=\"button\" src='/HIS/hisglobal/images/buttons/btn-pnt.png' tabindex=\"1\" "
						+ "style=cursor:pointer onclick=\"printPage()\" onkeypress=\"if(event.keyCode==13)"
						+ " printPage()\"><img class=\"button\" src='/HIS/hisglobal/images/buttons/btn-ccl.png' tabindex=\"1\" "
						+ "style=cursor:pointer onclick=\"backButton()\" onkeypress=\"if(event.keyCode==13)" + " backButton()\"></div>";

				String footer = "</td><td width=\"50%\">&nbsp;</td></tr>\n </table>\n </body>\n </html>\n";
				header = header + buttonHtml;
				JRHtmlExporter htmlExporter = new JRHtmlExporter();
				htmlExporter.setParameter(JRHtmlExporterParameter. JASPER_PRINT, jPrint);
				htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, header);
				htmlExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, footer);
				htmlExporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT,JRHtmlExporterParameter.SIZE_UNIT_POINT);
				htmlExporter.setParameter(JRHtmlExporterParameter.OUTPUT_STRING_BUFFER,bf);
				htmlExporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,false);
				htmlExporter.exportReport();

				byteArray= bf.toString().getBytes();
			}
			catch (JRException e)
			{
			}
		}
		catch (HisRecordNotFoundException e)
		{
			
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return byteArray;
	}
}
