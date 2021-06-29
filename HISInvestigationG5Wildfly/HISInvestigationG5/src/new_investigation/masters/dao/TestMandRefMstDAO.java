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
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.TestMandRefMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;

public class TestMandRefMstDAO extends DataAccessObject implements TestMandRefMstDAOi
{

	public TestMandRefMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}


	public void createTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_TEST_MAND_REF_VAL_MST";
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

			populateMAP.put(sq.next(), testmandref_VO.getTestCode());
			populateMAP.put(sq.next(), testmandref_VO.getParameterCode());
			populateMAP.put(sq.next(), testmandref_VO.getTestCode());
			populateMAP.put(sq.next(), testmandref_VO.getParameterCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

			populateMAP.put(sq.next(), testmandref_VO.getGender());
			populateMAP.put(sq.next(), testmandref_VO.getLowAge());
			populateMAP.put(sq.next(), testmandref_VO.getHighAge());
			populateMAP.put(sq.next(), testmandref_VO.getLowAgeUom());
			populateMAP.put(sq.next(), testmandref_VO.getHighAgeUom());
			populateMAP.put(sq.next(), testmandref_VO.getLowValue());
			populateMAP.put(sq.next(), testmandref_VO.getHighValue());
			populateMAP.put(sq.next(), testmandref_VO.getLowValueUom());
			populateMAP.put(sq.next(), testmandref_VO.getHighValueUom());
			populateMAP.put(sq.next(), testmandref_VO.getSymbol());
			populateMAP.put(sq.next(), testmandref_VO.getRemarks());
			
			//added by chandan for new range
			populateMAP.put(sq.next(), testmandref_VO.getRange());
			populateMAP.put(sq.next(), testmandref_VO.getRangeUom());
			populateMAP.put(sq.next(), testmandref_VO.getRangeTyp());
			
			
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testmandref_VO.getLabCode());
			populateMAP.put(sq.next(), testmandref_VO.getSampleCode());
			populateMAP.put(sq.next(), testmandref_VO.getMandCode());


		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("testmandref_DAO.populateMAP::" + e);
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



	public List<TestMandRefMasterVO> fetchCheckListTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_TEST_MAND_REF_VAL_MST";
		
		 List<TestMandRefMasterVO> lstTestMandRefMasterVO=new ArrayList<TestMandRefMasterVO>(); 
       
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		
		populateMap.put(sq.next(), testmandref_VO.getTestCode());
		populateMap.put(sq.next(), testmandref_VO.getParameterCode());
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), testmandref_VO.getRangeTyp());

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
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(TestMandRefMasterVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstTestMandRefMasterVO.add((TestMandRefMasterVO)valueObjects[i]);
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
		return lstTestMandRefMasterVO;
	}

	//to display data according to the TestPara code selected

	public List<TestMandRefMasterVO> fetchdisplaydataTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		List<TestMandRefMasterVO> testmandref_listVO = new ArrayList<TestMandRefMasterVO>();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_DISPLAYDATA.HIVT_TEST_MAND_REF_VAL_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), testmandref_VO.getTestCode());
		populateMap.put(sq.next(), testmandref_VO.getParameterCode());
		
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
				valueObjects = HelperMethods.populateVOfrmRS(TestMandRefMasterVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					testmandref_listVO.add((TestMandRefMasterVO)valueObjects[i]);
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
		return testmandref_listVO;
	}

	// for Updating The old Record
	public void updateTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_TEST_MAND_REF_VAL_MST";
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
			
			
			populateMAP.put(sq.next(), testmandref_VO.getGender());
			populateMAP.put(sq.next(), testmandref_VO.getLowAge());
			populateMAP.put(sq.next(), testmandref_VO.getHighAge());
			populateMAP.put(sq.next(), testmandref_VO.getLowAgeUom());
			populateMAP.put(sq.next(), testmandref_VO.getHighAgeUom());
			populateMAP.put(sq.next(), testmandref_VO.getLowValue());
			populateMAP.put(sq.next(), testmandref_VO.getHighValue());
			populateMAP.put(sq.next(), testmandref_VO.getLowValueUom());
			populateMAP.put(sq.next(), testmandref_VO.getHighValueUom());
			populateMAP.put(sq.next(), testmandref_VO.getSymbol());
			populateMAP.put(sq.next(), testmandref_VO.getRemarks());
			
			populateMAP.put(sq.next(), testmandref_VO.getRange());
			populateMAP.put(sq.next(), testmandref_VO.getRangeUom());
			populateMAP.put(sq.next(), testmandref_VO.getRangeTyp());
			
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), testmandref_VO.getLabCode());
			populateMAP.put(sq.next(), testmandref_VO.getSampleCode());
			populateMAP.put(sq.next(), testmandref_VO.getMandCode());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testmandref_VO.getSeqNo());
			populateMAP.put(sq.next(), testmandref_VO.getTestCode());
			populateMAP.put(sq.next(), testmandref_VO.getParameterCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);


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
	public String checkDuplicateTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_TEST_MAND_REF_VAL_MST";
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
			populateMAP.put(sq.next(), testmandref_VO.getTestCode());
			populateMAP.put(sq.next(), testmandref_VO.getParameterCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);


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
	public String checkDuplicateModifyTestMandRef(TestMandRefMasterVO testmandref_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_TEST_MAND_REF_VAL_MST";
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
			populateMAP.put(sq.next(), testmandref_VO.getTestCode());
			populateMAP.put(sq.next(), testmandref_VO.getParameterCode());

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
		String queryKey="SELECT.TEST_COMBO.HIVT_TEST_MAND_REF_VAL_MST";

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

		public List getTestMandRef(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstTestParaName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.TESTPARA_COMBO.HIVT_TEST_MAND_REF_VAL_MST";

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
					lstTestParaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return lstTestParaName;
		}
		public List fetchParameterCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.PARAMETER_COMBO_TEST.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testmandref_VO.getTestCode());

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

		
		public String fetchCriteriaCode(TestMandRefMasterVO testmandref_VO,UserVO _UserVO)
		{
			String query = "";
			ResultSet rs;

			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="FETCH_CRITERIACODE.HIVT_TEST_MAND_REF_VAL_MST";
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
				populateMAP.put(sq.next(), testmandref_VO.getTestCode());
				populateMAP.put(sq.next(), testmandref_VO.getParameterCode());

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

		
		public List fetchLabCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.LAB_COMBO_TEST.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			

			
			

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

		
		public List fetchSampleCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.SAMPLE_COMBO_TEST.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			

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

		public List fetchLabComboFilter(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.LAB_COMBO_FILTER_TEST.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			

			
			

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

		
		public List fetchSampleComboFilter(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.SAMPLE_COMBO_FILTER_TEST.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			

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

		
		public List fetchLabSampleCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.LAB_SAMPLE_COMBO_TEST.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMap.put(sq.next(), testmandref_VO.getLabCode());

			
			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), testmandref_VO.getLabCode());

			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

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

		
		public List fetchMandCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstparameterName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.MAND_COMBO_TEST.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();


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

		public List fetchOld(String testCode, String parameterCode, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstOld=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.OLD_SEQ_NO.HIVT_TEST_MAND_REF_VAL_MST";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			populateMap.put(sq.next(), testCode);
			populateMap.put(sq.next(), parameterCode);


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
					lstOld=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return lstOld;
		}
		
		public void deleteTestMandRef(String str1, String testCode, String parameterCode, UserVO _UserVO)
		{
			String query = "";
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="DELETE_GNUM.HIVT_TEST_MAND_REF_VAL_MST";
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
				populateMAP.put(sq.next(), testCode);
				populateMAP.put(sq.next(), parameterCode);
				populateMAP.put(sq.next(), str1);
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);


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
		
		public List<TestMandRefMasterVO> fetchCheckListLocalTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.HIVT_TEST_MAND_REF_VAL_MST";
			
			 List<TestMandRefMasterVO> lstLocalTestMandRefMasterVO=new ArrayList<TestMandRefMasterVO>(); 
	       
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			
			populateMap.put(sq.next(), testmandref_VO.getTestCode());
			populateMap.put(sq.next(), testmandref_VO.getParameterCode());
			populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMap.put(sq.next(), testmandref_VO.getRangeTyp());

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
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(TestMandRefMasterVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						lstLocalTestMandRefMasterVO.add((TestMandRefMasterVO)valueObjects[i]);
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
			return lstLocalTestMandRefMasterVO;
		}

}
