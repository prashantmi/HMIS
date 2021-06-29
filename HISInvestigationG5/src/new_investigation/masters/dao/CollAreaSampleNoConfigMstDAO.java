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
import new_investigation.vo.CollAreaSampleNoConfigMasterVO;

public class CollAreaSampleNoConfigMstDAO extends DataAccessObject implements CollAreaSampleNoConfigMstDAOi
{

	public CollAreaSampleNoConfigMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}




	public void createCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_COLAREA_SAMNO_CONFIG_MST";
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
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getAreaCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			// lab is no more primary key. area is
			//populateMAP.put(sq.next(), collareasamplenoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getAreaCode());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getPatientType());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getInitializationType());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getSampleNoFormat());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getSeqDigit());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getFromSeries());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getToSeries());
		/*	populateMAP.put(sq.next(), collareasamplenoconfig_VO.getInitDate());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getReinitDate());*/
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



	public void fetchCheckListCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_COLAREA_SAMNO_CONFIG_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), collareasamplenoconfig_VO.getSeqNo());
		//lab no more primary key. area is.
		//populateMap.put(sq.next(), collareasamplenoconfig_VO.getLabCode());
		populateMap.put(sq.next(), collareasamplenoconfig_VO.getAreaCode());
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
					HelperMethods.populateVOfrmRS(collareasamplenoconfig_VO ,rs);
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


	// for Updating The old Record
	public void updateCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_COLAREA_SAMNO_CONFIG_MST";
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
	
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getInitializationType());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getSampleNoFormat());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getSeqDigit());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getFromSeries());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getToSeries());
		/*	populateMAP.put(sq.next(), collareasamplenoconfig_VO.getInitDate());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getReinitDate());*/
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getSeqNo());
			//lab no more primary key
			//populateMAP.put(sq.next(), collareasamplenoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getAreaCode());
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



	/// check for duplicate Container Name
	public String checkDuplicateCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_COLAREA_SAMNO_CONFIG_MST";
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
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getPatientType());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), collareasamplenoconfig_VO.getAreaCode());


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


	// For lab combo

	public List getlabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlocationName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_COLAREA_SAMNO_CONFIG_MST";

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


	// For area combo

	public List getareaCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstwardName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.AREA_COMBO.HIVT_COLAREA_SAMNO_CONFIG_MST";

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
				lstwardName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstwardName;
	}

	public List getArea(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstArea=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.UNMAPPED_AREA.HIVT_COLAREA_SAMNO_CONFIG_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), collareasamplenoconfig_VO.getLabCode());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), collareasamplenoconfig_VO.getLabCode());
		populateMap.put(sq.next(), collareasamplenoconfig_VO.getPatientType());
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
				lstArea=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstArea;
	}

	
	
	//function to fetch lab based on area selected
	public List getAreaWiseLab(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstLab=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.UNMAPPED_LAB.HIVT_COLAREA_SAMNO_CONFIG_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), collareasamplenoconfig_VO.getAreaCode());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), collareasamplenoconfig_VO.getAreaCode());
		populateMap.put(sq.next(), collareasamplenoconfig_VO.getPatientType());
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
				lstLab=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstLab;
	}

}
