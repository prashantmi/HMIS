package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

public class ChartMasterDAO extends DataAccessObject
{
	public ChartMasterDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);

	}
	
	public void create(ChartMasterVO _chartMasterVO,UserVO _userVO) {

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
//			populateMAP.put(sq.next(), profileTypeMstVO.getProfileName());
//			populateMAP.put(sq.next(), Config.SL_NO);
//			populateMAP.put(sq.next(), profileTypeMstVO.getProfileDesc());
//			populateMAP.put(sq.next(), profileTypeMstVO.getIsUnique());
//			populateMAP.put(sq.next(), profileTypeMstVO.getGenerationMode());
//			populateMAP.put(sq.next(), profileTypeMstVO.getDefaultName());
//			populateMAP.put(sq.next(), profileTypeMstVO.getProfileUsablity());
//			populateMAP.put(sq.next(), profileTypeMstVO.getIsCdBurn());
//			populateMAP.put(sq.next(), profileTypeMstVO.getIsBilling());
//			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
//			populateMAP.put(sq.next(), profileTypeMstVO
//					.getIsDesclaimerRequired());
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
	
	public boolean checkDuplicate(ChartMasterVO _chartMasterVO,UserVO _userVO) 
	{
		
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HPMRT_PROFILE_TYPE_MST";
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
//			populateMAP.put(sq.next(), profileTypeMstVO.getProfileName());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
//			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
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
		
}
