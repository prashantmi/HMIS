package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.LabConfigratorMstVO;
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

public class LabConfigratorMstDAO extends DataAccessObject {

	public LabConfigratorMstDAO(TransactionContext _tx) {
		super(_tx);
		
	}

	public void fetchLocalLab(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_LOCAL.HIVT_LABORATORY_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), labConfigratorMstVO.getLabCode());
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
					HelperMethods.populateVOfrmRS(labConfigratorMstVO ,rs);
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

	public List getGlobalLabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.GLOBAL_LAB_COMBO.HIVT_LABORATORY_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
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
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	public List getLocalLabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_LABORATORY_MST";

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
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	public List getlocationCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlocationName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LOCATION_COMBO.HIVT_LABORATORY_MST";

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
			//	throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstlocationName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstlocationName;
	}
	public List getlabInchargeCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlabInchargeName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_INCHARGE_COMBO.HIVT_LABORATORY_MST";

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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstlabInchargeName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstlabInchargeName;
	}
	public void populate(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_LABORATORY_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
		populateMap.put(sq.next(), labConfigratorMstVO.getLabCode());
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
					HelperMethods.populateVOfrmRS(labConfigratorMstVO ,rs);
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
	public List<LabConfigratorMstVO> getTestBylabCode(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_SAMPLE.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		//populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
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
		return lstConfigVO;
	}
	public List<LabConfigratorMstVO> getTestBylabCodeLocal(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_SAMPLE.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
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
		return lstConfigVO;
	}
	
	public List<LabConfigratorMstVO> getMandBylabCode(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_MAND.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
				}
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			//else			 		
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return lstConfigVO;
	}
	public List<LabConfigratorMstVO> getMandBylabCodeLocal(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_MAND.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
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
		return lstConfigVO;
	}
	public String checkPrimaryKeyLocalLab(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKPRIMARYKEY.HIVT_LABORATORY_MST";
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
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());   
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
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
	public String checkDuplicateLocalLab(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_LABORATORY_MST";
		

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
			populateMAP.put(sq.next(), labConfigratorMstVO.getLaboratoryName().trim());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


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
	public void createLocalLab(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT_LOCAL.HIVT_LABORATORY_MST";
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
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getDepartment());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLaboratoryName());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabShortSName());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabType());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), labConfigratorMstVO.getHeadertext());
			populateMAP.put(sq.next(), labConfigratorMstVO.getFooterText());
			populateMAP.put(sq.next(), labConfigratorMstVO.getRemarks());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabWorkingDays());
			populateMAP.put(sq.next(), labConfigratorMstVO.getNumberofTests());
			populateMAP.put(sq.next(), labConfigratorMstVO.getSampleNumberConfig());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabNumberConfig());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLocation());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabIncharge());
			populateMAP.put(sq.next(), "1");
			populateMAP.put(sq.next(), "1");



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
	public void updateValid(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE_VALID.HIVT_LABORATORY_MST";
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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());

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
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	public void updateLocalLab(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE_LOCAL.HIVT_LABORATORY_MST";
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






			populateMAP.put(sq.next(), labConfigratorMstVO.getDepartment());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLaboratoryName());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabShortSName());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabType());
			populateMAP.put(sq.next(), labConfigratorMstVO.getHeadertext());
			populateMAP.put(sq.next(), labConfigratorMstVO.getFooterText());

			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), labConfigratorMstVO.getRemarks());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabWorkingDays());
			populateMAP.put(sq.next(), labConfigratorMstVO.getNumberofTests());
			populateMAP.put(sq.next(), labConfigratorMstVO.getSampleNumberConfig());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabNumberConfig());

			populateMAP.put(sq.next(), labConfigratorMstVO.getLocation());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabIncharge());
			populateMAP.put(sq.next(), "1");
			populateMAP.put(sq.next(), "1");

			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
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
	public List getLabTestData(String testCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="MODIFY.Lab_Test_Global_mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),testCode);
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
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labConfigratorMstVO, rs);
					
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
	public void insertLabTestData(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "ADD.Lab_Test_Global_mst";
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
			populateMAP.put(sq.next(),labConfigratorMstVO.getTestCode());
			populateMAP.put(sq.next(),labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(),labConfigratorMstVO.getTestDays());
			populateMAP.put(sq.next(),labConfigratorMstVO.getNoOfTest());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsTestAvailable());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsAppointment());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsMultisession());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsMandatoryReq());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsRequisitionFormNeeded());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsSampleFormNeeded());
			populateMAP.put(sq.next(),labConfigratorMstVO.getGenderBound());
			populateMAP.put(sq.next(),labConfigratorMstVO.getAgeBound());
			populateMAP.put(sq.next(),labConfigratorMstVO.getLowAgeRange());
			populateMAP.put(sq.next(),labConfigratorMstVO.getHighAgeRange());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsSecurePrinting());
			populateMAP.put(sq.next(),labConfigratorMstVO.getHeaderText());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsGrossingReq());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),labConfigratorMstVO.getFooterText());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsFilmReq());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsConsent());
			populateMAP.put(sq.next(),labConfigratorMstVO.getTestMethod());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsConfidential());
			populateMAP.put(sq.next(),labConfigratorMstVO.getTestPrintingType());
			populateMAP.put(sq.next(),labConfigratorMstVO.getPrintingTemplateId());
			populateMAP.put(sq.next(),labConfigratorMstVO.getReportAvailableAfter());	
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
	public List fetchLabTestSampleData(String testCode,String sampleCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
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
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),sampleCode);
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
					HelperMethods.populateVOfrmRS(labConfigratorMstVO, rs);
					
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
	public void insertLabTestSampleData(LabConfigratorMstVO labConfigratorMstVO,String testCode, UserVO _UserVO)
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
			populateMAP.put(sq.next(),labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(),testCode);
			populateMAP.put(sq.next(),labConfigratorMstVO.getSampleQty());
			populateMAP.put(sq.next(),labConfigratorMstVO.getSampleCode());
			populateMAP.put(sq.next(),labConfigratorMstVO.getContainerCode());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labConfigratorMstVO.getUomCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),labConfigratorMstVO.getSampleCollRemarks());
			populateMAP.put(sq.next(),labConfigratorMstVO.getIsDefaultSample());
				
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
	public void insertTestMandatoryData(String testCode ,LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.Hivt_Test_Mandatory_Global_Mst";
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
			populateMAP.put(sq.next(), labConfigratorMstVO.getMandCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
		

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
	public String checkDuplicateLabConfigratorModify(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_LABORATORY_MST";

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

			populateMAP.put(sq.next(), labConfigratorMstVO.getLaboratoryName().trim());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


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

	public String checkLocalLabTest(String testCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_TEST.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkDeletedLabTest(String testCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_TEST.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}

	public String checkLocalLabTestSample(String testCode,String sampleCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_TEST_SAMPLE.Lab_COnfigrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),sampleCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkDeletedLabTestSample(String testCode,String sampleCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_TEST_SAMPLE.Lab_COnfigrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),sampleCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkLocalTestMandatory(String testCode,String mandCode,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_TEST_MANDATORY.Lab_Confifrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),mandCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkDeletedLocalTestMandatory(String testCode,String mandCode,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_TEST_MANDATORY.Lab_Confifrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),mandCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	
	public String checkDeletedTestMandatory(String testCode,String mandCode,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_TEST_MANDATORY.Lab_Confifrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		populateMap.put(sq.next(),testCode);
		populateMap.put(sq.next(),mandCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public List<LabConfigratorMstVO> getTestCode(LabConfigratorMstVO labConfigratorMstVO,String count,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_TEST.LAB_CONFIGRATOR_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), labConfigratorMstVO.getLabCode());
		
		if(count.equals("0"))
				populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		else if(count.equals("1"))
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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
				}
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			//else			 		
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return lstConfigVO;
	}
	public List<LabConfigratorMstVO> getGroupByTestCode(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_GROUP_GLOBAL.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), labConfigratorMstVO.getLabCode());
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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
				}
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			//else			 		
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return lstConfigVO;
	}
	public List<LabConfigratorMstVO> getCannedByLabCode(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_GLOBAL_CANNED.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
				}
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			//else			 		
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return lstConfigVO;
	}
	public List<LabConfigratorMstVO> getMacroByLabCode(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_GLOBAL_MACRO.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
				}
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			//else			 		
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return lstConfigVO;
	}
	public void insertTestGroupData(String testCode ,LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_TEST_GROUP_INFO_MST";
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
			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getGroupCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), " ");
		

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
	public void insertLabCannedData(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT_GLOBAL.HIVT_LAB_CANNED_MST";
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
			populateMAP.put(sq.next(), labConfigratorMstVO.getCannedCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());

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
	public void insertLabMacroData(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT_LOCAL.HIVT_LAB_MACRO_MAPPING_MST";
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
			populateMAP.put(sq.next(), labConfigratorMstVO.getMacroCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		
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
	public List<LabConfigratorMstVO> getGroupBylabCodeLocal(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_LOCAL_GROUP.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
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
		return lstConfigVO;
	}
	public List<LabConfigratorMstVO> getCannedByLabCodeLocal(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_LOCAL_CANNED.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
				}
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			//else			 		
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return lstConfigVO;
	}
	public List<LabConfigratorMstVO> getMacroByLabCodeLocal(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<LabConfigratorMstVO> lstConfigVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_LOCAL_MACRO.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode() );
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
				lstConfigVO=new ArrayList<LabConfigratorMstVO>();
				LabConfigratorMstVO voLabConfig=null;
				while(rs.next())
				{
					voLabConfig=new LabConfigratorMstVO();
					HelperMethods.populateVOfrmRS(voLabConfig, rs);
					lstConfigVO.add(voLabConfig);
					
				}
				
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			//else			 		
			 //throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return lstConfigVO;
	}
	public String checkLocalTestGroup(String groupCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_TEST_MANDATORY.Lab_Confifrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getTestCode());
		populateMap.put(sq.next(),groupCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkLocalLabCanned(String cannedCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_CANNED.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),cannedCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkDeletedLabCanned(String cannedCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_CANNED.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),cannedCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkLocalLabMacro(String macroCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_MACRO.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),macroCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}
	public String checkDeletedLabMacro(String macroCode,LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String strTest="";
		ResultSet rs=null;
		List labName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_LOCAL_LAB_MACRO.Lab_Configrator_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		populateMap.put(sq.next(),labConfigratorMstVO.getLabCode());
		populateMap.put(sq.next(),macroCode);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
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
					strTest=rs.getString(1);
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
		return strTest;
	}

	public void deleteLabTest(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_LAB_TEST.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
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
	public void updateLabTest(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_LAB_TEST.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
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
	public void deleteTestMand(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_TEST_MANDATORY.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getMandCode());
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
	public void UpdateTestMand(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_TEST_MANDATORY.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getMandCode());
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
	public void deleteLabTestSample(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_LAB_TEST_SAMPLE.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getSampleCode());
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
	public void updateLabTestSample(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_LAB_TEST_SAMPLE.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getSampleCode());
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

	public void deleteLabCanned(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_Lab_CANNED.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getCannedCode());
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
	public void deleteLabMacro(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_Lab_MACRO.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getMacroCode());
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
	public void updateLabCanned(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_Lab_CANNED.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getCannedCode());
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
	public void updateLabMacro(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_Lab_MACRO.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getMacroCode());
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
	
	
	public void deleteLabTestSample_Test(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_LAB_TEST_SAMPLE_TEST.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labConfigratorMstVO.getLabCode());
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
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
	
	
	public void deleteTestMand_Test(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DELETE_TEST_MANDATORY_TEST.Lab_Configrator_Mst";
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labConfigratorMstVO.getTestCode());
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

}
