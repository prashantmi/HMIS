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
import new_investigation.vo.MachineTestParameterMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;

public class MachineTestParameterMstDAO extends DataAccessObject implements MachineTestParameterMstDAOi
{

	public MachineTestParameterMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}


	public List getMachineCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMachineName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MACHINE_TEST_PARAMETER_MST.MACHINE_COMBO";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		
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
				lstMachineName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstMachineName;
	}
	public List getTestCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstTestName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MACHINE_TEST_PARAMETER_MST.TEST_COMBO";

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
	
	public List<MachineTestParameterMasterVO> displayParameterDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		List<MachineTestParameterMasterVO> machineTestParamMst_listVO = new ArrayList<MachineTestParameterMasterVO>();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_DISPLAYDATA.MACHINE_TEST_PARAMETER_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), machineTestParaMstVO.getTestCode());
		populateMap.put(sq.next(), machineTestParaMstVO.getMachineCode());
		
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
				valueObjects = HelperMethods.populateVOfrmRS(MachineTestParameterMasterVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					machineTestParamMst_listVO.add((MachineTestParameterMasterVO)valueObjects[i]);
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
		return machineTestParamMst_listVO;
	}
	
	public void saveDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HMIT_MACHINE_TESTPARA_MST";
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
			populateMAP.put(sq.next(), machineTestParaMstVO.getMachineCode());
			populateMAP.put(sq.next(), machineTestParaMstVO.getTestCode());
			populateMAP.put(sq.next(), machineTestParaMstVO.getParameterCode());
			populateMAP.put(sq.next(), machineTestParaMstVO.getMachineParameterCode());
			populateMAP.put(sq.next(), machineTestParaMstVO.getMachineParameterName());
			populateMAP.put(sq.next(), machineTestParaMstVO.getMachineCode());
			populateMAP.put(sq.next(), machineTestParaMstVO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), machineTestParaMstVO.getParaCount());
			

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("machineTestParaMst_DAO.populateMAP::" + e);
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

	
	
	/*public void fetchCheckListMachineTestParaCombo(MachineTestParameterMasterVO testparacombo_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HMIT_MACHINE_TEST_PARAMETER_MST";

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
				{
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
*/
	
	
	
/*	public void fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HMIT_MACHINE_TEST_PARA_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), machineTestParaMstVO.getMachineCode());
		//populateMap.put(sq.next(), machineTestParaMstVO.getTestCode());
		//populateMap.put(sq.next(), testparacombo_VO.getParameterCode());
		//populateMap.put(sq.next(), testparacombo_VO.getTestparaSeq());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

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
					HelperMethods.populateVOfrmRS(machineTestParaMstVO ,rs);
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
	}*/

	public List<MachineTestParameterMasterVO> fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HMIT_MACHINE_TEST_PARA_MST";
		List<MachineTestParameterMasterVO> machineTestParamMst_listVO = new ArrayList<MachineTestParameterMasterVO>();

		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), machineTestParaMstVO.getMachineCode());
		populateMap.put(sq.next(), machineTestParaMstVO.getTestCode());
		
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
				valueObjects = HelperMethods.populateVOfrmRS(MachineTestParameterMasterVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					machineTestParamMst_listVO.add((MachineTestParameterMasterVO)valueObjects[i]);
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
		return machineTestParamMst_listVO;
	}
	
	
	public List<MachineTestParameterMasterVO> displayDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HMIT_MACHINE_TEST_PARA_MST";
		List<MachineTestParameterMasterVO> machineTestParamMst_listVO = new ArrayList<MachineTestParameterMasterVO>();

		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), machineTestParaMstVO.getMachineCode());
		populateMap.put(sq.next(), machineTestParaMstVO.getTestCode());
		
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
				valueObjects = HelperMethods.populateVOfrmRS(MachineTestParameterMasterVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					machineTestParamMst_listVO.add((MachineTestParameterMasterVO)valueObjects[i]);
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
		return machineTestParamMst_listVO;
	}
	
	
	
	/*
	public String checkDuplicateModifyTestParaCombo(MachineTestParameterMasterVO testparacombo_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HMIT_MACHINE_TEST_PARAM_MST";
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
	}*/


	public void updateTestParaCombo(MachineTestParameterMasterVO testparacombo_VO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HMIT_MACHINE_TESTPARA_MST";
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
			populateMAP.put(sq.next(), testparacombo_VO.getMachineParameterCode());
			populateMAP.put(sq.next(), testparacombo_VO.getMachineParameterName());
			populateMAP.put(sq.next(), testparacombo_VO.getMachineCode());
			populateMAP.put(sq.next(), testparacombo_VO.getParameterCode());
			populateMAP.put(sq.next(), testparacombo_VO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());

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


	
	
	

}
