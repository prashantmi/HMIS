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
import new_investigation.vo.TestParaComboMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;

public class TestParaComboMstDAO extends DataAccessObject implements TestParaComboMstDAOi
{

	public TestParaComboMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}


	public void createTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_TEST_PARAM_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

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
			
			populateMAP.put(sq.next(), testparacombo_VO.getTestCode());
			populateMAP.put(sq.next(), testparacombo_VO.getParameterCode());
			populateMAP.put(sq.next(), testparacombo_VO.getTestCode());
			populateMAP.put(sq.next(), testparacombo_VO.getParameterCode());
			populateMAP.put(sq.next(), testparacombo_VO.getTestparaValue());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testparacombo_VO.getSetdefault());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("testparacombo_DAO.populateMAP::" + e);
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



	public void fetchCheckListTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_TEST_PARAM_COMBO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), testparacombo_VO.getTestCode());
		populateMap.put(sq.next(), testparacombo_VO.getParameterCode());
		populateMap.put(sq.next(), testparacombo_VO.getTestparaSeq());


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
				{System.out.println("Hi this is output of query "+rs.getString(3)+rs.getString(5));
					HelperMethods.populateVOfrmRS(testparacombo_VO ,rs);
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

	//to display data according to the TestPara code selected
	public List<TestParaComboMasterVO> fetchdisplaydataTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		List<TestParaComboMasterVO> testparacombo_listVO = new ArrayList<TestParaComboMasterVO>();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_DISPLAYDATA.HIVT_TEST_PARAM_COMBO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), testparacombo_VO.getTestCode());
		populateMap.put(sq.next(), testparacombo_VO.getParameterCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}


		ValueObject[] valueObjects = null;

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
				valueObjects = HelperMethods.populateVOfrmRS(TestParaComboMasterVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					testparacombo_listVO.add((TestParaComboMasterVO)valueObjects[i]);
				}

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
		return testparacombo_listVO;
	}

	// for setting 0 in all rows of default column
	public void removeDefaultTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="REMOVE_DEFAULT.HIVT_TEST_PARAM_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testparacombo_VO.getTestCode());
			populateMAP.put(sq.next(), testparacombo_VO.getParameterCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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
	
	// for Updating The old Record
		public void updateTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
		{
			String query = "";
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="UPDATE.HIVT_TEST_PARAM_COMBO_MST";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();

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
				populateMAP.put(sq.next(), testparacombo_VO.getTestParameterValue());
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), testparacombo_VO.getSetdefault());
				populateMAP.put(sq.next(), testparacombo_VO.getTestparaSeq());
				populateMAP.put(sq.next(), testparacombo_VO.getTestCode());
				populateMAP.put(sq.next(), testparacombo_VO.getParameterCode());
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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


	/// check for duplicate TestPara VALUE
	public String checkDuplicateTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_TEST_PARAM_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();			


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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);    
			populateMAP.put(sq.next(), testparacombo_VO.getTestCode());
			populateMAP.put(sq.next(), testparacombo_VO.getParameterCode());
			populateMAP.put(sq.next(), testparacombo_VO.getTestparaValue());

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


	/// check for duplicate TestPara VALUE on Modify
	public String checkDuplicateModifyTestParaCombo(TestParaComboMasterVO testparacombo_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_TEST_PARAM_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

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
			populateMAP.put(sq.next(), testparacombo_VO.getTestCode());
			populateMAP.put(sq.next(), testparacombo_VO.getParameterCode());
			populateMAP.put(sq.next(), testparacombo_VO.getTestParameterValue().trim());
			populateMAP.put(sq.next(), testparacombo_VO.getTestparaSeq());

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




	// For Test name combo

	public List getTestCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstTestName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_COMBO.HIVT_TEST_PARAM_COMBO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

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
				lstTestName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstTestName;
	}

	
	// For Test Para name combo

		public List getTestParaCombo(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstTestParaName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.TESTPARA_COMBO.HIVT_TEST_PARAM_COMBO_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

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
					//throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					lstTestParaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return lstTestParaName;
		}
		public List fetchParameterCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String queryKey="";
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			
			if(testparacombo_VO.getParaType().equals("2"))
			{
				 queryKey="SELECT.REQUISITION_FORM_PARAMETER_COMBO_TEST.HIVT_TEST_PARAM_COMBO_MST";
			}
			else if(testparacombo_VO.getParaType().equals("1"))
			 queryKey="SELECT.DEPARTMENT_PARAMETER_COMBO_TEST.HIVT_TEST_PARAM_COMBO_MST";
			else if(testparacombo_VO.getParaType().equals("0"))
				queryKey="SELECT.PARAMETER_COMBO_TEST.HIVT_TEST_PARAM_COMBO_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testparacombo_VO.getTestCode());

			try
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out	of property file::"+e);
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
					lstparameterName=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
				//	throw new HisRecordNotFoundException();	
				}
				else			 		
					throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			}	
			return lstparameterName;
		}


}
