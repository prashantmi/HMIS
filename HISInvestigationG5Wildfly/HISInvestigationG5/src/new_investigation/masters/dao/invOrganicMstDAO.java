package new_investigation.masters.dao;

import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;

	import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

	import new_investigation.InvestigationConfig;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.invOrganicMstVO;
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

	public class invOrganicMstDAO extends DataAccessObject {

		public invOrganicMstDAO(TransactionContext _tx) {
			super(_tx);
			// TODO Auto-generated constructor stub
		}


		

		public void createOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = "INSERT.HIVT_ORGANISM_MST";
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
				
				populateMAP.put(sq.next(),_UserVO.getSeatId());
			   populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicName());
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicNameOrder());
				
				
		
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
		
		
		public int checkDuplicateOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="CHECKDUPLICATE.HIVT_ORGANISM_MST";
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
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicName());         
				
				 
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
		
	
		public int checkDuplicateOrderOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="CHECKDUPLICATEORDER.HIVT_ORGANISM_MST";
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
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicNameOrder());         
				
				 
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
		
	
		
		public int checkModifyDuplicateOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="CHECKMODIFYDUPLICATE.HIVT_ORGANISML_MST";
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
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicName());
				/*populateMAP.put(sq.next(), testNewMasterVO.getOrganicNameCode());
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicNameOrder());
				*/
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
		
		public int checkModifyDuplicateOrderOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="CHECKMODIFYDUPLICATEORDER.HIVT_ORGANISML_MST";
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
				
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicNameOrder());
				
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


		public void fetchOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO,String namecode)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.HIVT_ORGANISM_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
		
			populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), namecode);
			/*populateMap.put(sq.next(), _UserVO.getSeatId());*/
			
			
			
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
		
		
		public void updateOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="UPDATE.HIVT_ORGANISM_MST";
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
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicName());
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicNameOrder());
			
				/*where clause*/
				populateMAP.put(sq.next(), testNewMasterVO.getOrganicNameCode());
				
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

