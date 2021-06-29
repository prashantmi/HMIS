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
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class DeceasedImageDtlDAO extends DataAccessObject implements DeceasedImageDtlDAOi
{
	public DeceasedImageDtlDAO(TransactionContext _tx)
	{
		super (_tx);
	}
	
	///Inserting Record
	public void create(MortuaryDeceasedImageDtlVO deceasedImageDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_DECEASED_IMAGE_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getPatCrNo());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getEpisodeCode());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getIsDefaultImage());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getUploadMode());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getFilePath());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getImageHeader());
	    	populateMAP.put(sq.next(), deceasedImageDtlVO.getUploadRemarks());
	    	
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceasedImageDtlDAO.populateMAP::"+e);
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
	
	//Getting The File Name
	public String getFileName(String deceasedNo)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="GET_FILE_NAME.HMRT_DECEASED_IMAGE_DTL";
	    
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
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public MortuaryDeceasedImageDtlVO[] getExistingPhotoByDeceasedNo(String deceasedNo,UserVO userVO)
	{
		MortuaryDeceasedImageDtlVO[] arrDeceasedImageVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DECEASED_IMAGE.HMRT_DECEASED_IMAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

	//	populateMAP.put(sq.next(), "1080800001924");
		populateMAP.put(sq.next(), deceasedNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrDeceasedImageVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MortuaryDeceasedImageDtlVO.class, rs);
				arrDeceasedImageVO = new MortuaryDeceasedImageDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedImageVO[i] = (MortuaryDeceasedImageDtlVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedImageVO;
	}
	
	public void updateIsDefaultNoToAll(String deceasedNo, UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="UPDATE_IS_DEFAULT_IMAGE_NO.HMRT_DECEASED_IMAGE_DTL";
		
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
			populateMAP.put(sq.next(),MortuaryConfig.IS_DEFAULT_IMAGE_NO);
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("DeceasedImageDtlDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	/*if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
	public void updateIsDefaultValue(String deceasedNo, String srNo, UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="UPDATE_IS_DEFAULT_IMAGE.HMRT_DECEASED_IMAGE_DTL";
		
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
			populateMAP.put(sq.next(),MortuaryConfig.IS_DEFAULT_IMAGE_YES);
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),srNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("DeceasedImageDtlDAO:populateMap::"+e);
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
	
	public void updateIsDefaultValue(MortuaryDeceasedImageDtlVO deceasedImageDtlVO, UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="UPDATE_IS_DEFAULT_IMAGE.HMRT_DECEASED_IMAGE_DTL";
		
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
			populateMAP.put(sq.next(),deceasedImageDtlVO.getIsDefaultImage());
			populateMAP.put(sq.next(),deceasedImageDtlVO.getDeceasedNo());
			populateMAP.put(sq.next(),deceasedImageDtlVO.getSlNo());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("DeceasedImageDtlDAO:populateMap::"+e);
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
	
	
	public void deleteDeceasedImage(MortuaryDeceasedImageDtlVO deceasedImageDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="REMOVE.IMAGE.HMRT_DECEASED_IMAGE_DTL";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),userVO.getSeatId());
			populateMAP.put(sq.next(),deceasedImageDtlVO.getDeceasedNo());
			populateMAP.put(sq.next(),deceasedImageDtlVO.getSlNo());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("DeceasedImageDtlDAO:populateMap::"+e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
	public MortuaryDeceasedImageDtlVO getDedeasedDefaultImage(String deceasedNo,UserVO userVO)
	{
		MortuaryDeceasedImageDtlVO deceasedImageVO=new MortuaryDeceasedImageDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="SELECT_DECEASED_DEFAULT_IMAGE.HMRT_DECEASED_IMAGE_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),MortuaryConfig.IS_DEFAULT_IMAGE_YES);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
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
				deceasedImageVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deceasedImageVO,rs);
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
		return deceasedImageVO;
	}
	
}
