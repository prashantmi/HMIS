package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

public class ProfileTypeDAO extends DataAccessObject {

	public ProfileTypeDAO(TransactionContext _transactionContext) {
		super(_transactionContext);

	}

	/**
	 * get profile type based on usablity and generation mode
	 * @param profileType 
	 */
	public List<ProfileTypeMstVO> getProfileTypes(String usablity,
			String generationMode, UserVO _userVO) {
		System.out.println("In ProfileTypeDAO Class In getProfileTypes() Fun()"+" Usability :"+usablity+"Genration Mode"+generationMode);
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HPMRT_PROFILE_TYPE_MST";
		List<ProfileTypeMstVO> profileTypeVOList = new ArrayList<ProfileTypeMstVO>();
		ProfileTypeMstVO profileTypeVO = null;
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		Sequence sq = new Sequence();
		try {
			//Commented by Akash (31-07-2014) 			
/*			populateMAP.put(sq.next(), usablity);
			populateMAP.put(sq.next(),
					OpdConfig.PROFILE_TYPE_USABLITY_OPD_AND_IPD);
			populateMAP.put(sq.next(), generationMode);*/
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), usablity);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"OpdEssentialDAO.populateMAP::" + e);
		}

		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					profileTypeVO = new ProfileTypeMstVO();
					HelperMethods.populateVOfrmRS(profileTypeVO, rs);
					profileTypeVOList.add(profileTypeVO);
				}
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}
		return profileTypeVOList;
	}

	/**
	 * get all profile type
	 */
	public List getAllProfileTypes(UserVO _userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.All_PROFILE_TYPE.HPMRT_PROFILE_TYPE_MST";
		List profileTypeList = new ArrayList();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		Sequence sq = new Sequence();
		try {
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"OpdEssentialDAO.populateMAP::" + e);
		}

		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				profileTypeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}
		return profileTypeList;
	}

	/**
	 * insert the profile type data in the table
	 * 
	 * @param _userVO
	 */
	public void create(ProfileTypeMstVO profileTypeMstVO, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HPMRT_PROFILE_TYPE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		Sequence sq = new Sequence();
		try {
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileName());
			populateMAP.put(sq.next(), Config.SL_NO);
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileDesc());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsUnique());
			populateMAP.put(sq.next(), profileTypeMstVO.getGenerationMode());
			populateMAP.put(sq.next(), profileTypeMstVO.getDefaultName());
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileUsablity());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsCdBurn());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsBilling());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), profileTypeMstVO
					.getIsDesclaimerRequired());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"OpdEssentialDAO.populateMAP::" + e);
		}

		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate"
					+ e);
		}

	}

	public ProfileTypeMstVO fetchRecord(ProfileTypeMstVO profileTypeMstVO,
			UserVO userVO) {
		String query = "";

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		//ProfileTypeMstVO ProfileTypeVO = new ProfileTypeMstVO();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HPMRT_PROFILE_TYPE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
//			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileType());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileTypeMstVO.getSlNo());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"UserDeskMenuMasterDAO.populateMAP::" + e);
		}

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(super
					.getTransactionContext().getConnection(), query,
					populateMAP);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				HelperMethods.populateVOfrmRS(profileTypeMstVO, rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return profileTypeMstVO;
	}
	
	public void modify(ProfileTypeMstVO profileTypeMstVO, UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HPMRT_PROFILE_TYPE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		try {
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileType());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileTypeMstVO.getSlNo());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileTypeMstDAO.populateMAP::" + e);
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
	
	public void modifyInsert(ProfileTypeMstVO profileTypeMstVO,
			UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFY_INSERT.HPMRT_PROFILE_TYPE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileType());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileType());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileName());
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileDesc());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsUnique());
			populateMAP.put(sq.next(), profileTypeMstVO.getGenerationMode());
			populateMAP.put(sq.next(), profileTypeMstVO.getDefaultName());
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileUsablity());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsCdBurn());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsBilling());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), profileTypeMstVO.getIsDesclaimerRequired());
			
					
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileTypeDAO.populateMAP::" + e);
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

	
	public boolean checkDuplicateBeforeSave(ProfileTypeMstVO profileTypeMstVO, UserVO userVO) {
	
	ResultSet rs;
	boolean flag=false;
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HPMRT_PROFILE_TYPE_MST";
//	String profileName = null;
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		populateMAP.put(sq.next(), profileTypeMstVO.getProfileName());
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"ProfileTypeMasterDAO.populateMAP::" + e);
	}
	try {
		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    	rs.next();
    	if(rs.getInt(1)==0)
    	{
    		flag=true;
    	}
    	else
    	{
    		flag=false;
    	}
        
        return flag;
    } 
	catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else {
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.getResultset" + e);
		}
	}
}
	
	public boolean checkDuplicateBeforeModifySave(ProfileTypeMstVO profileTypeMstVO, UserVO userVO) {
		
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_MODIFY_SAVE.HPMRT_PROFILE_TYPE_MST";
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		try {
			populateMAP.put(sq.next(), profileTypeMstVO.getProfileName());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //addedby:NehaRajgariya date:7sept2016
			populateMAP.put(sq.next(),profileTypeMstVO.getProfileType());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileTypeMasterDAO.populateMAP::" + e);
		}
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	    	rs.next();
	    
	    	if(rs.getInt(1)==0)
	    	{
	    		flag=true;
	    	}
	    	else
	    	{
	    		flag=false;
	    	}
	        
	        return flag;
	    } 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
			}
		}
	}
		
	
	

}// end class
