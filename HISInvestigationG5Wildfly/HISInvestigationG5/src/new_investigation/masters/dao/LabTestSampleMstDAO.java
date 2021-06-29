/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE/SYSTEM GLOBAL MASTER
 ## Purpose						        : This master is used for mapping the Sample to labs and test globally i.e. on hospital code 100
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.LabTestSampleMstVO;
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

public class LabTestSampleMstDAO extends DataAccessObject {

	public LabTestSampleMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	public List getSampleNameLocal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List samplecombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Sample.Lab_Test_Sample_Local_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		
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
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{*/
				rs.beforeFirst();
				samplecombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			/*}*/
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
		return samplecombo;
	}
	public List getSampleNameGlobal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List samplecombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Sample.Lab_Test_Sample_Global_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{*/
				rs.beforeFirst();
				samplecombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			/*}*/
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
		return samplecombo;
	}
	public List getContainerName(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List containercombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Container.Lab_Test_Sample_Global_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				containercombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return containercombo;
	}

	public List getUOMName(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List UOMcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.UOM.Lab_Test_Sample_Global_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				UOMcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return UOMcombo;
	}

	public List getLabName(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Lab_Tset_Global_Mst.1";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
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
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}
	public List getTestName(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="Select.Test.Lab_Test_Global_Mst.1";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		
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
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}
	public void saveLabTestSample(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.Lab_Test_Sample_Global_Mst";
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
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleQty());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getContainerCode());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labTestSampleMstVO.getUomCode());
			populateMAP.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCollRemarks());
			populateMAP.put(sq.next(),labTestSampleMstVO.getIsDefaultSample());
				
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
	
	public List fetchModifyLabTestGlobal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MODIFY._Lab_Test_Sample_Global_mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
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
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				
				
				
				testMethod=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return testMethod;
	}
	
	public void saveModifyLabTestSample(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.MODIFY.Lab_Test_Sample_Global_Mst";
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
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleQty());
			populateMAP.put(sq.next(),labTestSampleMstVO.getContainerCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getUomCode());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCollRemarks());
			populateMAP.put(sq.next(),labTestSampleMstVO.getIsDefaultSample());
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
				
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
	
	
                                            	/*Local Master */
	
	public List getLabNameLocal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Lab_Tset_Global_Mst.1";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
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
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}
	
	public void saveLabTestLocalSample(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.Lab_Test_Sample_Global_Mst";
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
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleQty());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getContainerCode());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labTestSampleMstVO.getUomCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCollRemarks());
			populateMAP.put(sq.next(),labTestSampleMstVO.getIsDefaultSample());
				
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
	public List fetchModifyLabTestLocal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MODIFY._Lab_Test_Sample_Global_mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
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
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				
				
				
				testMethod=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return testMethod;
	}
	public List checkIsSampleDefaultGlobal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="Check_Is_Sample_Default";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),"1");
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
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName; 
	}
	public List checkIsSampleDefaultLocal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="Check_Is_Sample_Default";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),"1");
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
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}
	
	public void UpdateIsDefaultSampleGlobal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "UPDATE.Is_Default_Sample.Lab_Test_Sample_Mst";
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			
			
				
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

	public List checkIsSampleDefaultGlobalSpecific(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="Check_Is_Sample_Default_Specific";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),"1");
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
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}
	
	public List checkIsSampleDefaultLocalSpecific(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="Check_Is_Sample_Default_Specific";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),"1");
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
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}

	public void UpdateIsDefaultSampleLocal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "UPDATE.Is_Default_Sample.Lab_Test_Sample_Mst";
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			
				
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
	
	public void saveModifyLabTestSampleLocal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.MODIFY.Lab_Test_Sample_Global_Mst";
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
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleQty());
			populateMAP.put(sq.next(),labTestSampleMstVO.getContainerCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getUomCode());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCollRemarks());
			populateMAP.put(sq.next(),labTestSampleMstVO.getIsDefaultSample());
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				
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
	public List getUOM(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.UOM_SAMPLE_QTY.Lab_Test_Sample_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getContainerCode());
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
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}
	
	public String getContainerValues(String containerCode,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strUOMCodeHashVolume="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.UOM_SAMPLE_QTY.Lab_Test_Sample_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),containerCode);
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
				if(rs.next())
				{
					strUOMCodeHashVolume=rs.getString(1);
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
		return strUOMCodeHashVolume;
	}

	public List getSampleQty(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.SAMPLEQTY.Lab_Test_Sample_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getContainerCode());
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
				
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName;
	}
	public List checkDuplicateRecord(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_DATA.Lab_Test_Sample_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
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
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName; 
	}
	public void updateInsertData(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "UPDATE_INSERT.Lab_Test_Sample_mst";
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
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleQty());
			populateMAP.put(sq.next(),labTestSampleMstVO.getContainerCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getUomCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCollRemarks());
			populateMAP.put(sq.next(),labTestSampleMstVO.getIsDefaultSample());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
				
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
	public List checkDuplicateRecordLocal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_DATA.Lab_Test_Sample_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
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
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName; 
	}
	public void updateInsertDataLocal(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "UPDATE_INSERT.Lab_Test_Sample_mst";
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
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleQty());
			populateMAP.put(sq.next(),labTestSampleMstVO.getContainerCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getUomCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCollRemarks());
			populateMAP.put(sq.next(),labTestSampleMstVO.getIsDefaultSample());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),labTestSampleMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestSampleMstVO.getSampleCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				
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
	
	public List fetchGlobalData(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="FETCH_GLOBAL_DATA.Lab_Test_Sample_Local_Data";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestSampleMstVO.getLabCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getTestCode());
		populateMap.put(sq.next(),labTestSampleMstVO.getSampleCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labTestSampleMstVO, rs);
					
				}
				labName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return labName; 
	}
}
