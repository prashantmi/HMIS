package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.FilmMstVO;
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

public class FilmMstDAO extends DataAccessObject {

	public FilmMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	
	public List getFilmCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TESTNAME;
		String queryKey="SELECT.TESTNAME_COMBO.HIVT_TEST_MST";
		
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
				lsttest=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lsttest;
	}
	
	
	public List getitemnameCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_ITEMNAME;
		String queryKey="SELECT.ITEMNAME_COMBO.HSTT_ITEM_MST";
		
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);       

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lsttest=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lsttest;
	}
	
	public List getstorenameCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_ITEMNAME;
		String queryKey="SELECT.STORENAME_COMBO.HSTT_STORE_MST";
		
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
			populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_UserVO.getHospitalCode());       

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lsttest=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lsttest;
	}	

	public void createFilm(FilmMstVO testNewMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_FILM_DETAIL_MST";
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
			 			 
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
			populateMAP.put(sq.next(), testNewMasterVO.getFilmType());
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeLength());
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeBreadth());
			populateMAP.put(sq.next(), testNewMasterVO.getNoOfFilmReqd());
			populateMAP.put(sq.next(), testNewMasterVO.getItemStoreId());
			populateMAP.put(sq.next(), testNewMasterVO.getRemarks());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), testNewMasterVO.getStoreid());
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
	
	
	public int checkDuplicateFilm(FilmMstVO testNewMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_FILM_DETAIL_MST";
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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);         
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeLength());         
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeBreadth());         
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		int record=0;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=Integer.parseInt(rs.getString(1));
				System.out.println("string record" + record);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	
	public int checkModifyDuplicateFilm(FilmMstVO testNewMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKMODIFYDUPLICATE.HIVT_FILM_DETAIL_MST";
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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);         
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeLength());         
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeBreadth());
			populateMAP.put(sq.next(), testNewMasterVO.getFilmid());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		int record=0;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=Integer.parseInt(rs.getString(1));
				System.out.println("string record" + record);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}


	public void fetchFilm(FilmMstVO testNewMasterVO, UserVO _UserVO,String filmid,String slno)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_FILM_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
	
		populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), testNewMasterVO.getTestCode());
		populateMap.put(sq.next(), filmid);
		populateMap.put(sq.next(), slno);
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
					HelperMethods.populateVOfrmRS(testNewMasterVO ,rs);
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
	
	
	public void updateFilm(FilmMstVO testNewMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_FILM_DTL_MST";
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
			populateMAP.put(sq.next(), testNewMasterVO.getFilmType());
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeLength());
			populateMAP.put(sq.next(), testNewMasterVO.getFilmSizeBreadth());
			populateMAP.put(sq.next(), testNewMasterVO.getNoOfFilmReqd());
			populateMAP.put(sq.next(), testNewMasterVO.getStoreid());
			populateMAP.put(sq.next(), testNewMasterVO.getItemStoreId());
			populateMAP.put(sq.next(), testNewMasterVO.getRemarks());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
			populateMAP.put(sq.next(), testNewMasterVO.getFilmid());
			
		
			
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
