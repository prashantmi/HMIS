package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import new_investigation.InvestigationConfig;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.TestGroupMasterVO;

public class LocalTestGroupMstDAO extends DataAccessObject implements LocalTestGroupMstDAOi
{

	public LocalTestGroupMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	/////////////////////////////////////////Local Test Group master////////////////////////////////////////

	public void createLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.LOCAL.HIVT_TEST_LOCALGROUP_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupCde());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupName());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupType());
			populateMAP.put(sq.next(), testgroupmaster_VO.getIsprint());         //Added by harshita
             populateMAP.put(sq.next(), testgroupmaster_VO.getRemarks());
			populateMAP.put(sq.next(), testgroupmaster_VO.getPreferenceOrder());//Added by Harshita
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("TestGroupMaster.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	/*public void fetchLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_TEST_GROUP_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
		populateMap.put(sq.next(),testgroupmaster_VO.getGroupCde());
		
		
		
		

		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(testgroupmaster_VO ,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
	}
	*/
	
	
	public void fetchCheckListLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LOCAL.HIVT_TEST_GROUP_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		//populateMap.put(sq.next(),testgroupmaster_VO.getGroupCde());         /*harshita*/
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		//populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
		populateMap.put(sq.next(),testgroupmaster_VO.getGroupCde());
		//populateMap.put(sq.next(),testgroupmaster_VO.getPreferenceOrder());                   /*harshita*/
		
		
		
		

		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(testgroupmaster_VO ,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
	}
	
	
	
	

	public List gettestgroupCombo(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttestgroupName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LOCAL.TESTGROUP_COMBO.HIVT_TEST_GROUP_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		/*populateMap.put(sq.next(),_UserVO.getHospitalCode());*/
		/*populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);*/
	/*	populateMap.put(sq.next(),testgroupmaster_VO.getGroupCde());*/
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lsttestgroupName=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return lsttestgroupName;
	}
	
	
	public void updateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.LOCAL.HIVT_TEST_GROUP_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{	
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupName());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE); 
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupType());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testgroupmaster_VO.getRemarks());
			populateMAP.put(sq.next(),testgroupmaster_VO.getPreferenceOrder());             /*Harshita*/
			populateMAP.put(sq.next(),testgroupmaster_VO.getIsprint());                    /*Harshita*/
			populateMAP.put(sq.next(),testgroupmaster_VO.getGroupCde());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("rejectionreasonmaster_DAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}


	public String checkDuplicateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.LOCAL.HIVT_TEST_GROUP_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupCde());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());

			
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}

	public String checkDuplicateModifyLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.LOCAL.HIVT_TEST_GROUP_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);       
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupName().trim());
			populateMAP.put(sq.next(), testgroupmaster_VO.getGroupCode());
		//	populateMAP.put(sq.next(), testgroupmaster_VO.getPreferenceOrder());                   /*harshita*/

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("rejectionreasonmaster_DAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	
	public void fetchdisplaydataLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
	String queryKey="SELECT.LOCAL.LOCAL_GROUP_NAME.HIVT_TEST_GROUP_MST";

	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();

	
/*	populateMap.put(sq.next(),_UserVO.getHospitalCode());
*/	
	populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
	populateMap.put(sq.next(),testgroupmaster_VO.getGroupCde());
	
	
	
	

	try
	{
		query = HelperMethodsDAO.getQuery(filename,queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
		if (!rs.next())
		{
			throw new HisRecordNotFoundException();
		}
		else
		{
			rs.beforeFirst();
			while(rs.next())
			{
				HelperMethods.populateVOfrmRS(testgroupmaster_VO ,rs);
			}
		}
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException();	
		}
		else			 		
			throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	}	}

	///////////////////////////////////////////////Local Test Group master ////////////////////////////////////////////

	
}
