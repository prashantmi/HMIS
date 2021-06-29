package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.IcdCrossRefMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IcdCrossRefMasterDAO extends DataAccessObject implements IIcdCrossRefMasterDAOi
{

	Logger log;

	public IcdCrossRefMasterDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public List<Entry> getIndexTermCombo(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map mapPopulateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.INDEX_TERM.HGBT_ICD_INDEX_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		Sequence sq = new Sequence();

		try
		{

			mapPopulateMAP.put(sq.next(), _userVO.getHospitalCode());
			mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdIndexLevelMaserDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, mapPopulateMAP);

			if (!rs.next()) throw new HisRecordNotFoundException("No Index Term Exists in database  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> listAllRecord = new ArrayList<Entry>();
		try
		{
			listAllRecord = (List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getIndexTerm" + e);
		}
		return listAllRecord;
	}

	/*
	 * Populating the Icd cross ref Modifier on basis of index term
	 * 
	 * @param @param _userVO User Detail
	 */
	public List<Entry> getModifier(String strIndex, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.MODIFIER1.HGBT_ICD_INDEX_LEVEL_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			mapPopulateMAP.put(sq.next(), strIndex);
			mapPopulateMAP.put(sq.next(), _userVO.getHospitalCode());
			mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, mapPopulateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> listModifierCombo = new ArrayList<Entry>();
		try
		{
			listModifierCombo = (List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getIndexTerm" + e);
		}
		return listModifierCombo;
	}

	public List<IcdIndexLevelMasterVO> getSeeTerms(String strIndex, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.SEE.TERMS.HGBT_ICD_INDEX_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			mapPopulateMAP.put(sq.next(), strIndex);
			mapPopulateMAP.put(sq.next(), _userVO.getHospitalCode());
			mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DAO.mapPopulateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, mapPopulateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<IcdIndexLevelMasterVO> listSeeTerms = new ArrayList<IcdIndexLevelMasterVO>();
		ValueObject[] vo =	{};
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(IcdIndexLevelMasterVO.class, rs);
				for (ValueObject v : vo)
					listSeeTerms.add((IcdIndexLevelMasterVO) v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getSubgroupsByGroup" + e);
		}
		return listSeeTerms;
	}

	/*
	 * Populating the Icd cross ref Modifier 2 on basis of Modifier 1
	 * 
	 * @param @param _userVO User Detail
	 */
	public List<Entry> getInitializeSubModifierNext(String strModifierID, String level, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.SUB_MODIFIER_OF_LEVEL.HGBT_ICD_INDEX_LEVEL_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			mapPopulateMAP.put(sq.next(), strModifierID);
			mapPopulateMAP.put(sq.next(), level);
			mapPopulateMAP.put(sq.next(), _userVO.getHospitalCode());
			mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, mapPopulateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> listModifierCombo = new ArrayList<Entry>();
		try
		{
			listModifierCombo = (List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getIndexTerm" + e);
		}
		return listModifierCombo;
	}

	public List<IcdIndexLevelMasterVO> getSeeTermsForModi(String strModId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.SEE.TERMS.MOD.HGBT_ICD_INDEX_LEVEL_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			mapPopulateMAP.put(sq.next(), strModId);
			mapPopulateMAP.put(sq.next(), _userVO.getHospitalCode());
			mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DAO.mapPopulateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, mapPopulateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<IcdIndexLevelMasterVO> listSeeTerms = new ArrayList<IcdIndexLevelMasterVO>();
		ValueObject[] vo =
		{};
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(IcdIndexLevelMasterVO.class, rs);
				for (ValueObject v : vo)
					listSeeTerms.add((IcdIndexLevelMasterVO) v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getSubgroupsByGroup" + e);
		}
		return listSeeTerms;
	}

	/*
	 * To save Data on Add Page
	 */
	public void saveIcdCrossRefMaster(IcdCrossRefMasterVO vo, UserVO userVO)
	{
		String strQuery;
		String strQueryKey;
		String strFileName = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;

		if (vo.getStrCheck().equals("0")) // Index Master
		{
			if (vo.getSeeTermRadio().equals("0")) // See
			strQueryKey = "UPDATE.SEE.HGBT_ICD_INDEX_MST";
			else // See Also
			strQueryKey = "UPDATE.SEE.ALSO.HGBT_ICD_INDEX_MST";
		}
		else
		// Index Level Master
		{
			if (vo.getSeeTermRadio().equals("0")) strQueryKey = "UPDATE.SEE.HGBT_ICD_INDEX_LEVEL_MST";
			else strQueryKey = "UPDATE.SEE.ALSO.HGBT_ICD_INDEX_LEVEL_MST";
		}

		Sequence sq = new Sequence();
		HashMap<Integer, Object> populateMAP = new HashMap<Integer, Object>();
		ResultSet rs = null;

		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFileName, strQueryKey);
		}

		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), vo.getStrIndexTermPlus());
			populateMAP.put(sq.next(), vo.getRefIndexCode());

			// What to update index or modifier
			if (vo.getStrCheckSee().equals("0"))
				populateMAP.put(sq.next(), null);
			else
				populateMAP.put(sq.next(), vo.getStrRefModifier1());

			// where clause

			// In which to update index or modifier
			if (vo.getStrCheck().equals("0")) populateMAP.put(sq.next(), vo.getIndexCode());
			else populateMAP.put(sq.next(), vo.getIndexModifierID());

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), strQuery, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
		finally
		{
			populateMAP = null;
		}
	}
}
