package opd.dao;

/**
 * @author  CDAC
 */

import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.ISO;

import opd.OpdConfig;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import mrd.MrdConfig;

public class PatientClinicalDetailDAO extends DataAccessObject implements PatientClinicalDetailDAOi
{
	public PatientClinicalDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Getting Episode-Centric Clinical Template Data (OPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientEpisodeClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_EPI_CLI_DATA.HRGT_EPISODE_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientEpisodeClinicalData::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		Map<String, Map<String,String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String,String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return mpParaTempValues;		
	}

	/**
	 * Getting Patient Detail Clinical Template Data in Case of Admitted Patient (IPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientClinicalRecordData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_REC_CLI_DATA.HIPD_PAT_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _patClinicalVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patClinicalVO.getRecordDate());
			//populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientClinicalRecordData::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		Map<String, Map<String,String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String,String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return mpParaTempValues;		
	}

	/**
	 * Getting Episode-Centric Clinical Template Data Template Wise (OPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstTemps 
	 * @param _userVO User VO
	 * @return Array of GenericTemplateUtility.TempParameter Objects
	 */
	
	/**
	## 		Modification Log		: entObj.getValue()						
	##		Modify Date				: 18-02-2015	
	##		Reason	(CR/PRS)		: It was passing HGNUM_TEMPLATE_ID with # in Query & was showing error
	##		Modify By				: Akash Singh
	*/
	public GenericTemplateUtility.TempParameter[] getPatientEpisodeClinicalDataTempWise(PatientClinicalDetailVO _patClinicalVO, 
			List<Entry> _lstTemps, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_EPI_CLI_DATA_TEMP_WISE.HRGT_EPISODE_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			String tempIds = "";
			for(Entry entObj : _lstTemps)
				tempIds+=entObj.getValue().split("#")[0]+",";   //entObj.getValue()+",";
			if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
			query = query.replace("#", tempIds);

			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientEpisodeClinicalDataTempWise::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
				
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				
				/* Converting Array into "Map of temp Id by List of Entry Object with paraId/Value"
				Map<String, Map<String,String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String,String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}*/
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return tempParas;		
	}

	/**
	 * Getting Patient Detail Clinical Template Data in Case of Admitted Patient Template Wise (IPD)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstTemps 
	 * @param _userVO User VO
	 * @return Array of GenericTemplateUtility.TempParameter objects 
	 */
	public GenericTemplateUtility.TempParameter[] getPatientClinicalRecordDataTempWise(PatientClinicalDetailVO _patClinicalVO, 
			List<Entry> _lstTemps, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_REC_CLI_DATA.HIPD_PAT_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			String tempIds = "";
			for(Entry entObj : _lstTemps)
				tempIds+=entObj.getValue()+",";
			if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
			query = query.replace("#", tempIds);

			populateMAP.put(sq.next(), _patClinicalVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patClinicalVO.getRecordDate());
			//populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientClinicalRecordDataTempWise::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				
				/* Converting Array into "Map of temp Id by List of Entry Object with paraId/Value"
				Map<String, Map<String,String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String,String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}*/
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return tempParas;		
	}

	/**
	 * Getting Patient-Centric Clinical Template Data
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of tempId/Map of paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientCentricClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_CLI_DATA.HPMRT_PAT_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			String tempIds = "";
			for(int i=0;i<_patClinicalVO.getTemplateIds().length;i++)
				tempIds+=_patClinicalVO.getTemplateIds()[i]+",";
			if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
			query = query.replace("#", tempIds);
			
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientCentricClinicalData::populateMAP " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		Map<String, Map<String, String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);				
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String, String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getPatientCentricClinicalData" + e);
		}
		return mpParaTempValues;
	}
	
	/**
	 * Getting Patient Clinical Template Data Map (Patient-Centric + Episode-Centric)
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PATIENT_FINAL_CLINICAL_DATA.HPMRT.HRGT";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			String tempIds = "";
			for(int i=0;i<_patClinicalVO.getTemplateIds().length;i++)
				tempIds+=_patClinicalVO.getTemplateIds()[i]+",";
			if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
			query = query.replace("#", tempIds);
			
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PatientClinicalDetailDAO.getPatientClinicalData::populateMAP " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		Map<String, Map<String, String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);				
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String, String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return mpParaTempValues;		
	}

	/**
	 * Getting Patient Clinical Template Data Map
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return List of Entry Object with paraId/Value 
	 */
	public Map<String, Map<String,String>> getPatientsOnlyCentricClinicalData(PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PATIENT_FINAL_CLINICAL_DATA.HPMRT.HRGT";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patClinicalVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientsOnlyCentricClinicalData::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		Map<String, Map<String, String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);				
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String, String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return mpParaTempValues;		
	}

	/** Inserts Patient Clinical Detail if present Update otherwise Insert
	 * @param _patClinDtlVO PatientClinicalDetailVO
	 * @param _userVO User VO
	 */
	public void savePtientClinicalDetail(String _deskType, PatientClinicalDetailVO _patClinDtlVO, UserVO _userVO)
	{
		String errorCode="";
		String errorMsg="";
		
		try
		{			
			if(_deskType.equals("12")){_deskType="1";}
			Procedure strProc=new Procedure(OpdDaoConfig.PROCEDURE_SAVE_PATIENT_EPISODE_CLINICAL_DATA);
			int i=1;
		    strProc.addInParameter(i++,Types.VARCHAR,_deskType);
		    strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getPatCrNo());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getEpisodeCode());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getEpisodeVisitNo());
			if(_patClinDtlVO.getAdmissionNo()==null) _patClinDtlVO.setAdmissionNo("");
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getAdmissionNo());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getRecordDate());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getDeskMenuId());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getTemplateId());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaId());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaValue());
			strProc.addInParameter(i++,Types.VARCHAR,_userVO.getSeatId());
			strProc.addInParameter(i++,Types.VARCHAR,_userVO.getHospitalCode());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaValueConcpetId());
			strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaValuePreffereTerm());
			strProc.addOutParameter(i++,Types.VARCHAR);
			strProc.addOutParameter(i++,Types.VARCHAR);
			
			strProc.execute(super.getTransactionContext().getConnection());
			
			errorCode=(String) strProc.getParameterAt(15);
			errorMsg=(String) strProc.getParameterAt(16);
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg == null ? "" : errorCode+":"+errorMsg) + e);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg == null ? "" : errorCode+":"+errorMsg) + e);
		}
	}

	/**
	 * Getting Clinical Record Date List
	 * @param _patAdmNo Patient Admission No
	 * @param _userVO User VO
	 * @return List of Entry objects of Record Data / Record Date
	 */
	public List<Entry> getClinicalRecordDateList(String _patAdmNo,String deskMenuId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.ALL_RECORD_DATES.HIPD_PAT_CLINICAL_DTL_DESKMENU";
		String queryKey1 = "SELECT.ALL_RECORD_DATES.HIPD_PAT_CLINICAL_DTL";

		try
		{
			if(deskMenuId!=null && deskMenuId.length()>0)
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			else
			query = HelperMethodsDAO.getQuery(filename, queryKey1);
				
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if(deskMenuId!=null && deskMenuId.length()>0)
			{
			populateMAP.put(sq.next(), _patAdmNo);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), deskMenuId);
			}
			else
			{
			populateMAP.put(sq.next(), _patAdmNo);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientsOnlyCentricClinicalData::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<Entry> lstDates = null;
		try
		{
			if(rs.next())
			{
				lstDates = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstDates;		
	}
	
	/**
	 * Getting Vital Last Record Value
	 * @param _patClinVO Patient Detail
	 * @param params Vitals
	 * @param _userVO User Detail
	 * @return List of Values in VO
	 */
	public List<PatientClinicalDetailVO> getVitalsLastValues(PatientClinicalDetailVO _patClinVO, String[] params,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String queryKey = "SELECT.LAST_RECORD_VALUE.HIPD_PAT_CLINICAL_DTL";

		try
		{
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			String str ="";
			for(String val : params)
				str+=val+",";
			if(!str.equals("")) str = str.substring(0,str.length()-1);
			query = query.replace("#",str);
			
			populateMAP.put(sq.next(), _patClinVO.getAdmissionNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patClinVO.getAdmissionNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getVitalsLastValues::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<PatientClinicalDetailVO> lstPatCliDtl = new ArrayList<PatientClinicalDetailVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientClinicalDetailVO.class, rs);
				for(ValueObject v : vo)
					lstPatCliDtl.add((PatientClinicalDetailVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstPatCliDtl;		
	}
	
	/** 
	 * Getting Patient All Vitals For EMR
	 * @param _patClinVO Patient Detail
	 * @param _userVO User Detail
	 * @return Array of Values in VO
	 */
	public PatientClinicalDetailVO[] getPatientVitalsEMR(PatientClinicalDetailVO _patClinVO,String [] departmentUnitArray,String accessType,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		PatientClinicalDetailVO[] patientClinicalDetailVOs=null;
		String queryKey = "SELECT.HIPD_PAT_CLINICAL_DTL.VITAL_EMR";
		String orderBy=" order by hrgdt_record_date desc";
		try
		{
			String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = a.hrgnum_episode_code "
						+ "and HRGNUM_PUK=a.HRGNUM_PUK and HRGNUM_VISIT_NO=a.HRGNUM_VISIT_NO and gnum_isvalid=a.gnum_isvalid "
					+ " AND gnum_hospital_code = a.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _patClinVO.getPatCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), GenericTemplateConfig.TEMPLATE_CATEGORY_VITAL_MONITORING);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getVitalsLastValues::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		 
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientClinicalDetailVO.class, rs);
				patientClinicalDetailVOs = new PatientClinicalDetailVO[vo.length];
				for(int i=0;i<vo.length;i++)
					patientClinicalDetailVOs[i]=(PatientClinicalDetailVO)vo[i];
			}
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return patientClinicalDetailVOs;		
	}

	/**
	 * Getting Patient All Complaints For EMR
	 * @param _patClinVO Patient Detail
	 * @param _userVO User Detail
	 * @return Array of Values in VO
	 */
	public PatientClinicalDetailVO[] getPatientComplaintsByCrNoEMR(PatientClinicalDetailVO _patClinVO,String _templateCategory,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		PatientClinicalDetailVO[] patientClinicalDetailVOs=null;
		String queryKey = "SELECT.HRGT_EPISODE_CLINICAL_DTL.HIPD_PAT_CLINICAL_DTL.BY_CRNO_EMR";

		try
		{
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _patClinVO.getPatCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _templateCategory);
			populateMAP.put(sq.next(), _patClinVO.getPatCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _templateCategory);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientComplaintsByCrNoEMR::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			/*if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else*/ throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		 
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientClinicalDetailVO.class, rs);
				patientClinicalDetailVOs = new PatientClinicalDetailVO[vo.length];
				for(int i=0;i<vo.length;i++)
					patientClinicalDetailVOs[i]=(PatientClinicalDetailVO)vo[i];
			}
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return patientClinicalDetailVOs;		
	}

	// Episode and IPD Clinical Record Data for Patient Profile
	public Map<String,Map<String, Map<String,String>>> getPatientClinicalDataDateWiseforProfile(PatientClinicalDetailVO _patClinicalVO, boolean isOPD, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKeyOPD = "SELECT.PAT_EPICLIDATA_DATE_PROFILE.HIPD_PAT_CLINICAL_DTL";
		String queryKeyIPD = "SELECT.PAT_IPDCLIDATA_DATE_PROFILE.HIPD_PAT_CLINICAL_DTL";

		try
		{
			if(isOPD)
				query = HelperMethodsDAO.getQuery(filename, queryKeyOPD);
			else
				query = HelperMethodsDAO.getQuery(filename, queryKeyIPD);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if(isOPD)
			{
				populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
				populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			else
			{
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _patClinicalVO.getAdmissionNo());
				populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientClinicalRecordData::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		Map<String,Map<String, Map<String,String>>> mpParaTempValues = new HashMap<String, Map<String, Map<String,String>>>();
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String, Map<String,String>> mapDate = mpParaTempValues.get(paraValVO.getRecordDate());
					if(mapDate==null)
						mapDate = new LinkedHashMap<String, Map<String,String>>();
					Map<String,String> map = mapDate.get(paraValVO.getTemplateId());
					if(map==null)
						map = new LinkedHashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mapDate.put(paraValVO.getTemplateId(), map);
					mpParaTempValues.put(paraValVO.getRecordDate(), mapDate);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		//System.out.println("Got Single Gynae Data Map ::" + mpParaTempValues.keySet().size());
		return mpParaTempValues;		
	}
}


