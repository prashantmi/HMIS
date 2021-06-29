/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.history.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ehr.examination.data.EHRSection_ExaminationDAO;
import ehr.history.data.EHRSection_HistoryDAO;
import ehr.history.vo.EHRSection_HistoryVO;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.PatientClinicalDetailDAO;
import opd.dao.PatientClinicalDetailDAOi;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.dao.TemplateMasterDAO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;



public class EHRSection_HistoryBO {

	public List<Entry> getDeskMenuTemplateList(String _deskType, UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstTemplates = null;
		try
		{
			tx.begin();

			EHRSection_HistoryDAO dao =new EHRSection_HistoryDAO(tx);
			lstTemplates = dao.getDeskMenuTemplateList(_deskType, _userDeskTempVO, _userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstTemplates;
	}
	
	
	public Map<String, Map<String, String>> getPatientFinalClinicalData(String _deskType, EHRSection_HistoryVO _patClinicalVO, UserVO _userVO)
	{
		Map<String, Map<String, String>> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EHRSection_HistoryDAO dao =new EHRSection_HistoryDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK)) mpTempParaVals = dao.getPatientClinicalData(_patClinicalVO, _userVO);
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK)) mpTempParaVals = dao.getPatientClinicalData(_patClinicalVO, _userVO);//dao.getPatientCentricClinicalData(_patClinicalVO,	_userVO);
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK)) mpTempParaVals = dao.getPatientCentricClinicalData(
					_patClinicalVO, _userVO);
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) mpTempParaVals = dao.getPatientCentricClinicalData(
					_patClinicalVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}

	
	public Map<String, TemplateMasterVO> getAllTemplateDetails(List<Entry> _lstTemps, UserVO _userVO)
	{
		Map<String, TemplateMasterVO> mapTempDtl = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EHRSection_HistoryDAO dao =new EHRSection_HistoryDAO(tx);
			mapTempDtl = new HashMap<String, TemplateMasterVO>();
			for (Entry e : _lstTemps)
			{
				TemplateMasterVO vo = new TemplateMasterVO();
				vo.setTemplateId(e.getValue());
				vo = dao.getTemplateDataById(vo, _userVO);
				mapTempDtl.put(e.getValue(), vo);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mapTempDtl;
	}
	
	public List<Entry> getReportDateList(String _deskType, EHRSection_HistoryVO _patCliDtlVO, UserVO _userVO)
	{
		List<Entry> lstReportDates = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			EHRSection_HistoryDAO dao =new EHRSection_HistoryDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK)) lstReportDates = dao
					.getPrevVisitReportDates(_patCliDtlVO.getPatCrNo(), _patCliDtlVO.getEpisodeCode(), _patCliDtlVO.getDeskMenuId(),_userVO);  ////added deskmenu  by manisha gangwar 
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) lstReportDates = dao
					.getClinicalRecordDateList(_patCliDtlVO.getAdmissionNo(), _patCliDtlVO.getDeskMenuId(), _userVO);  //added deskmenu by manisha gangwar 
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstReportDates;
	}
	
	
	public Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> getPatientChartClinicalDataTempWiseWithParaDtl(
			String _deskType, EHRSection_HistoryVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
	{
		Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EHRSection_HistoryDAO dao =new EHRSection_HistoryDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>>();
				for (Entry e : _lstReportDates)
				{
					EHRSection_HistoryVO patCliVO = new EHRSection_HistoryVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setEpisodeVisitNo(e.getValue());

					// Map<String, Map<String, String>> map = dao.getPatientEpisodeClinicalDataTempWise(patCliVO, _lstTemps,
					// _userVO);
					GenericTemplateUtility.TempParameter[] tempParas = dao.getPatientEpisodeClinicalDataTempWise(patCliVO, _lstTemps, _userVO);
					Map<String, Map<String, GenericTemplateUtility.TempParameter>> map = new HashMap<String, Map<String, GenericTemplateUtility.TempParameter>>();

					for (GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, GenericTemplateUtility.TempParameter> mpTemporary = null;
						if (map.get(paraValVO.getTemplateId()) != null) mpTemporary = map.get(paraValVO.getTemplateId());
						else mpTemporary = new HashMap<String, GenericTemplateUtility.TempParameter>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO);
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}

					mpTempParaVals.put(e.getValue(), map);
				}
			}
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>>();
				for (Entry e : _lstReportDates)
				{
					EHRSection_HistoryVO patCliVO = new EHRSection_HistoryVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setRecordDate(e.getValue());

					GenericTemplateUtility.TempParameter[] tempParas = dao.getPatientClinicalRecordDataTempWise(patCliVO, _lstTemps, _userVO);

					Map<String, Map<String, GenericTemplateUtility.TempParameter>> map = new HashMap<String, Map<String, GenericTemplateUtility.TempParameter>>();
					for (GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, GenericTemplateUtility.TempParameter> mpTemporary = null;
						if (map.get(paraValVO.getTemplateId()) != null) mpTemporary = map.get(paraValVO.getTemplateId());
						else mpTemporary = new HashMap<String, GenericTemplateUtility.TempParameter>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO);
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}

					mpTempParaVals.put(e.getValue(), map);
				}
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}
	
	public void savePatientClinicalDetail(String _deskType, List<PatientClinicalDetailVO> _lstPatCliDtl, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EHRSection_HistoryDAO dao = new EHRSection_HistoryDAO(tx);
			for (PatientClinicalDetailVO vo : _lstPatCliDtl)
				dao.savePtientClinicalDetail(_deskType, vo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

}//end class
