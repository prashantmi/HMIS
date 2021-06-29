package mortuary.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.UserVO;

public class PostmortemDetailDAO extends DataAccessObject implements PostmortemDetailDAOi
{
	public PostmortemDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(PostmortemDetailVO postmortemDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_DTL";
	    
	   
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getStartDateTime());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getEndDateTime());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getOpinion() );
	    	populateMAP.put(sq.next(), postmortemDtlVO.getRemarks());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getConductBy());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getComplexion());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getHairColorLength());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getEyeColor());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getLength());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getWeight());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getClothDetails());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getBodyLooks());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPupilRight());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPupilLeft());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getCorneaRight());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getCorneaLeft());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getCynosis());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getLividity());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getRigorMortis());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getOrifices());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getMouthOdour());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getIsDecomposition());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getDeathMannerCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryDateTime());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryNatureCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryTypeCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryRemarks());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getWeaponUsed());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getWeaponRemarks());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryPhoto());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getClinicalDiagnosis());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getIncisionType());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getDeathDateTime());
	    	
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemDetailDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HisDataAccessException While ADDING");
	    }
	}
	
	public boolean checkExistingRecord(String postmoretmId,UserVO userVO)
	{
		boolean exist=false;
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="CHECK_EXISTING_RECORD.HMRT_POSTMORTEM_DTL";
	    Connection conn=super.getTransactionContext().getConnection();
	    
	    populateMAP.put(sq.next(), postmoretmId);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), userVO.getHospitalCode());
	    
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if (!rs.next())
			{
				exist=false;
			}
			else
			{
				exist=true;
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
		return exist;
	}
	
	//Updating the Injury Details
	public void saveInjuriesDetail(PostmortemDetailVO postmortemInjuryVO, UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="UPDATE_INJURY_DETAIL.HMRT_POSTMORTEM_DTL";
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		  
		try
		{
			populateMAP.put(sq.next(),postmortemInjuryVO.getDeathMannerCode());
			populateMAP.put(sq.next(),postmortemInjuryVO.getInjuryDateTime());
			populateMAP.put(sq.next(),postmortemInjuryVO.getInjuryNatureCode());
			populateMAP.put(sq.next(),postmortemInjuryVO.getInjuryTypeCode());
			populateMAP.put(sq.next(),postmortemInjuryVO.getInjuryRemarks());
			populateMAP.put(sq.next(),postmortemInjuryVO.getWeaponUsed());
			populateMAP.put(sq.next(),postmortemInjuryVO.getWeaponRemarks());
			populateMAP.put(sq.next(),postmortemInjuryVO.getInjuryPhoto());
			populateMAP.put(sq.next(),postmortemInjuryVO.getPostmortemId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),postmortemInjuryVO.getPostmortemId());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PostmortemDetailDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
	public PostmortemDetailVO getAddedInjuryDetailToDisplay(String postmortemId,UserVO userVO)
	{
		PostmortemDetailVO injuryVO=new PostmortemDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="SELECT_INJURY_DETAIL.HMRT_POSTMORTEM_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),postmortemId);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(),postmortemId);
		
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				injuryVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(injuryVO,rs);
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
		return injuryVO;
	}
	
	public PostmortemDetailVO getExistingGeneralAppearance(String postmortemId,UserVO userVO)
	{
		PostmortemDetailVO generalAppearanceVO=new PostmortemDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="SELECT_GENERAL_APPEARANCE_DETAIL.HMRT_POSTMORTEM_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),postmortemId);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
	//	populateMap.put(sq.next(),postmortemId);
		
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				generalAppearanceVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(generalAppearanceVO,rs);
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
		return generalAppearanceVO;
	}
	
	public void updateGeneralAppearance(PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="UPDATE_GENERAL_APPEARANCE.HMRT_POSTMORTEM_DTL";
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		  
		try
		{
		
			populateMAP.put(sq.next(),postmortemDtlVO.getRemarks());
			populateMAP.put(sq.next(),postmortemDtlVO.getConductBy());
			populateMAP.put(sq.next(),postmortemDtlVO.getComplexion());
			populateMAP.put(sq.next(),postmortemDtlVO.getHairColorLength());
			populateMAP.put(sq.next(),postmortemDtlVO.getEyeColor());
			populateMAP.put(sq.next(),postmortemDtlVO.getLength());
			populateMAP.put(sq.next(),postmortemDtlVO.getWeight());
			populateMAP.put(sq.next(),postmortemDtlVO.getPupilRight());
			populateMAP.put(sq.next(),postmortemDtlVO.getPupilLeft());
			populateMAP.put(sq.next(),postmortemDtlVO.getClothDetails());
			populateMAP.put(sq.next(),postmortemDtlVO.getCorneaRight());
			populateMAP.put(sq.next(),postmortemDtlVO.getBodyLooks());
			populateMAP.put(sq.next(),postmortemDtlVO.getCorneaLeft());
			populateMAP.put(sq.next(),postmortemDtlVO.getCynosis());
			populateMAP.put(sq.next(),postmortemDtlVO.getLividity());
			populateMAP.put(sq.next(),postmortemDtlVO.getRigorMortis());
			populateMAP.put(sq.next(),postmortemDtlVO.getOrifices());
			populateMAP.put(sq.next(),postmortemDtlVO.getMouthOdour());
			populateMAP.put(sq.next(),postmortemDtlVO.getIsDecomposition());
			populateMAP.put(sq.next(),postmortemDtlVO.getDeathMannerCode());
			populateMAP.put(sq.next(),postmortemDtlVO.getInjuryDateTime());
			populateMAP.put(sq.next(),postmortemDtlVO.getInjuryNatureCode());
			populateMAP.put(sq.next(),postmortemDtlVO.getInjuryTypeCode());
			populateMAP.put(sq.next(),postmortemDtlVO.getInjuryRemarks());
			populateMAP.put(sq.next(),postmortemDtlVO.getWeaponUsed());
			populateMAP.put(sq.next(),postmortemDtlVO.getWeaponRemarks());
			populateMAP.put(sq.next(),postmortemDtlVO.getInjuryPhoto());
			populateMAP.put(sq.next(),postmortemDtlVO.getClinicalDiagnosis());
			populateMAP.put(sq.next(),postmortemDtlVO.getOpinion());
			
			
			populateMAP.put(sq.next(),postmortemDtlVO.getPostmortemId());
			populateMAP.put(sq.next(),postmortemDtlVO.getSrNo());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PostmortemDetailDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
	public void saveApprovedFinalOpinion(PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="APPROVED_FINAL_OPINION.HMRT_POSTMORTEM_DTL";
	    
	   
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getStartDateTime());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getEndDateTime());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getOpinion() );
	    	populateMAP.put(sq.next(), postmortemDtlVO.getRemarks());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getConductBy());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getComplexion());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getHairColorLength());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getEyeColor());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getLength());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getWeight());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getClothDetails());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getBodyLooks());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPupilRight());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getPupilLeft());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getCorneaRight());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getCorneaLeft());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getCynosis());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getLividity());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getRigorMortis());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getOrifices());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getMouthOdour());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getIsDecomposition());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getDeathMannerCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryDateTime());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryNatureCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryTypeCode());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryRemarks());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getWeaponUsed());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getWeaponRemarks());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getInjuryPhoto());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getClinicalDiagnosis());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getIncisionType());
	    	populateMAP.put(sq.next(), postmortemDtlVO.getDeathDateTime());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemDetailDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HisDataAccessException While ADDING");
	    }
	}
	
	public void invalidPreviousRow(PostmortemDetailVO postmortemDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="UPDATE_ISVALID_STATUS.HMRT_POSTMORTEM_DTL";
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		  
		try
		{
			populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(),postmortemDtlVO.getPostmortemId());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),postmortemDtlVO.getSrNo());
			  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PostmortemDetailDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 	
		
	}
}
