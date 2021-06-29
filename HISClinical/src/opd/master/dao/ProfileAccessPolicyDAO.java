package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfileAccessPolicyDAO extends DataAccessObject implements
		ProfileAccessPolicyDAOi {

	Logger log;

	public ProfileAccessPolicyDAO(TransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public String checkDuplicateBeforeModify(
			ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_MODIFYSAVE.HPMRT_PROFILE_ACCESS_POLICY";
		String disasterTypeName = null;
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), profileAccessPolicyVO.getDeptUnitCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getPolicyType());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileAccessPolicyDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				disasterTypeName = rs.getString(1);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
			}
		}
		return disasterTypeName;
	}

	public String checkDuplicateBeforeSave(
			ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HPMRT_PROFILE_ACCESS_POLICY";
		String patientCategoryCode = null;
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), profileAccessPolicyVO.getDeptUnitCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getPolicyType());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileAccessPolicyDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				patientCategoryCode = rs.getString(1);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
			}
		}
		return patientCategoryCode;
	}

	public void save(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {
		// ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HPMRT_PROFILE_ACCESS_POLICY";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), profileAccessPolicyVO.getDeptUnitCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getPolicyType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getAccessType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getUserLevel());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileGroupId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			//populateMAP.put(sq.next(), profileAccessPolicyVO.getEffectiveFrom());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getEffectiveTo());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileAccessPolicyDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}

	}

	public ProfileAccessPolicyVO fetchProfileAccessPolicyModify(
			ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {

		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HPMRT_PROFILE_ACCESS_POLICY";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), profileAccessPolicyVO.getDeptUnitCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getPolicyType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getSerialNo());
			// populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileAccessPolicyDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Record Not Found");
			} else {
				HelperMethods.populateVOfrmRS(profileAccessPolicyVO, rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
			}
		}
		return profileAccessPolicyVO;
	}

	public void modify(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HPMRT_PROFILE_ACCESS_POLICY";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getDeptUnitCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getPolicyType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getSerialNo());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileAccessPolicyDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}

	}

	public void modifyInsert(ProfileAccessPolicyVO profileAccessPolicyVO,
			UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFY_INSERT.HPMRT_PROFILE_ACCESS_POLICY";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), profileAccessPolicyVO.getDeptUnitCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getPolicyType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getAccessType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getUserLevel());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileGroupId());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());
			//populateMAP.put(sq.next(), profileAccessPolicyVO.getEffectiveFrom());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getEffectiveTo());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileAccessPolicyDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}

	}
	
	
	public List getDeptUnitByProfileTypeAndPolicyType(
			ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {

		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.BY_PROFILE_TYPE_AND_POLICY_TYPE.HPMRT_PROFILE_ACCESS_POLICY";
		List departmentList=new ArrayList();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			
			populateMAP.put(sq.next(), profileAccessPolicyVO.getProfileType());
			populateMAP.put(sq.next(), profileAccessPolicyVO.getPolicyType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileAccessPolicyDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			departmentList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
			}
		}
		return departmentList;
	}


}
