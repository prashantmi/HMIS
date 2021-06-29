package opd.icdsearchengine.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeClosedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import opd.icdsearchengine.ICDSearchEngineConfig;
import opd.icdsearchengine.dao.ICDSearchEngineDAO;
import opd.icdsearchengine.dao.ICDSearchEngineDAOi;
import opd.icdsearchengine.vo.ICDDiseaseDetailVO;
import opd.icdsearchengine.vo.ICDSearchEngineVO;
import opd.icdsearchengine.vo.ResultDiseaseListVO;
import opd.icdsearchengine.vo.ResultIndexListVO;

public class ICDSearchEngineBO implements ICDSearchEngineBOi
{

	// Getting Volume 1 Serching Essentials
	public Map<String, Object> getICDSearchingVolume1Essentials(UserVO _userVO)
	{
		Map<String, Object> mapEssential = new HashMap<String, Object>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);
			
			// All Chronic Diseases
			List<Entry> lstChronicDiseases = icdEngineDAO.getAllChronicDiseases(_userVO);
			mapEssential.put(ICDSearchEngineConfig.ICD_ENGINE_LIST_CHRONIC_DISEASES, lstChronicDiseases);

			// All Main Disease Types
			List<Entry> lstMainDiseaseTypes = icdEngineDAO.getAllMainDiseaseTypes(_userVO);
			mapEssential.put(ICDSearchEngineConfig.ICD_ENGINE_LIST_MAIN_DISEASE_TYPES, lstMainDiseaseTypes);

			// All Disease Types
			List<Entry> lstDiseaseTypes = icdEngineDAO.getAllDiseaseTypes(_userVO);
			mapEssential.put(ICDSearchEngineConfig.ICD_ENGINE_LIST_DISEASE_TYPES, lstDiseaseTypes);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return mapEssential;
	}

	// Getting Reports Searching Essentials
	public Map<String, Object> getICDSearchingReportsEssentials(UserVO _userVO)
	{
		Map<String, Object> mapEssential = new HashMap<String, Object>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);
			
			// All Chronic Diseases
			List<Entry> lstICDGroups = icdEngineDAO.getAllICDGroups(_userVO);
			mapEssential.put(ICDSearchEngineConfig.ICD_ENGINE_LIST_ICD_GROUPS, lstICDGroups);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return mapEssential;
	}

	// Getting Result for ICD Disease List
	public List<ResultDiseaseListVO> getResultICDDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		List<ResultDiseaseListVO> listResultICDDisease = new ArrayList<ResultDiseaseListVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listResultICDDisease = icdEngineDAO.getResultICDDiseaseList(_engineVO, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listResultICDDisease;
	}

	// Getting Result for ICD Sub Disease List
	public List<ResultDiseaseListVO> getResultICDSubDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		List<ResultDiseaseListVO> listResultICDSubDisease = new ArrayList<ResultDiseaseListVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listResultICDSubDisease = icdEngineDAO.getResultICDSubDiseaseList(_engineVO, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listResultICDSubDisease;
	}

	// Getting Detailed ICD Disease VO
	public ICDDiseaseDetailVO getDetailICDDiseaseVO(ICDDiseaseDetailVO _disVO, UserVO _userVO)
	{
		ICDDiseaseDetailVO icdDiseaseDtlVO = new ICDDiseaseDetailVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			icdDiseaseDtlVO = icdEngineDAO.getDetailICDDiseaseVO(_disVO, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return icdDiseaseDtlVO;
	}

	// Getting Result for Index Term List
	public List<ResultIndexListVO> getResultIndexTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		List<ResultIndexListVO> listResultIndexTerms = new ArrayList<ResultIndexListVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listResultIndexTerms = icdEngineDAO.getResultIndexTermList(_engineVO, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listResultIndexTerms;
	}

	// Getting List of Index Term List
	public List<ResultIndexListVO> getIndexTermList(UserVO _userVO)
	{
		List<ResultIndexListVO> listIndexTerms = new ArrayList<ResultIndexListVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listIndexTerms = icdEngineDAO.getIndexTermList(_userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listIndexTerms;
	}

	// Getting List of Modifier Term of Index Term
	public List<ResultIndexListVO> getIndexModiTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		List<ResultIndexListVO> listIndexTerms = new ArrayList<ResultIndexListVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listIndexTerms = icdEngineDAO.getIndexModiTermList(_engineVO, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listIndexTerms;
	}

	// Getting List of Modifier Term of Modifiers Terms
	public List<ResultIndexListVO> getModifierTermTreeList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		List<ResultIndexListVO> listIndexTerms = new ArrayList<ResultIndexListVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listIndexTerms = icdEngineDAO.getModifierTermTreeList(_engineVO, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listIndexTerms;
	}

	// Getting List of All Sub Groups By Group
	public List<Entry> getAllSubGroupByGroup(String _icdGroupCode, UserVO _userVO)
	{
		List<Entry> listSubGroups = new ArrayList<Entry>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listSubGroups = icdEngineDAO.getAllSubGroupByGroup(_icdGroupCode, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listSubGroups;
	}

	// Getting List of All Diseases By Sub Groups
	public List<Entry> getAllDiseasesBySubGroup(String _icdSubGroupCode, UserVO _userVO)
	{
		List<Entry> listDiseases = new ArrayList<Entry>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ICDSearchEngineDAOi icdEngineDAO = new ICDSearchEngineDAO(tx);			
			listDiseases = icdEngineDAO.getAllDiseasesBySubGroup(_icdSubGroupCode, _userVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
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
		return listDiseases;
	}

	public JasperPrint getReport(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		JasperPrint jPrint = null;
		try
		{
			tx.begin();
			
			InputStream is = this.getClass().getResourceAsStream(_engineVO.getJrxmlPath() + _engineVO.getJrxmlName());
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jReport = JasperCompileManager.compileReport(jasperDesign);
			Map mpParameter = new HashMap();

			populateParameteresForReport(mpParameter, _engineVO);

			jPrint = JasperFillManager.fillReport(jReport, mpParameter, tx.getConnection());
			List ls = jPrint.getPages();
			if (ls.size() == 0)
			{
				throw new HisDataAccessException();
			}
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
		return jPrint;
	}

	private static void populateParameteresForReport(Map _parameterMap, ICDSearchEngineVO  _engineVO)
	{

		Class reportVOClass =  _engineVO.getClass();
		Method[] methodsReportVO = reportVOClass.getMethods();
		try
		{
			for (int i = 0; i < methodsReportVO.length; i++)
			{
				String methodName = methodsReportVO[i].getName();
				String keyName = methodName.substring(3);
				String k = keyName.substring(1);
				String fistChar = keyName.substring(0, 1).toLowerCase();
				String finalKey = fistChar + k;
				if (methodName.startsWith("get"))
				{
					Object methodValue = methodsReportVO[i].invoke( _engineVO, null);
					if (methodValue instanceof String)
					{
						String Value = (String) methodValue;
						_parameterMap.put(finalKey, Value);
					}
				}
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException();

		}
	}
}