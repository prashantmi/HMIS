package opd.master.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.UserVO;

public class AudioVideoMasterDAO extends DataAccessObject
{
	Logger log;
	
	public AudioVideoMasterDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public void create(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="INSERT.HOPT_PLAYERFILE_MST";
	   	
	   	try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),OpdConfig.SLNO);
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileHeader());
        	populateMAP.put(sq.next(),OpdConfig.OPD_AUDIO_VIDEO_NAME);
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),audioVideoMasterVO.getExt());
        	
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),audioVideoMasterVO.getIsDefault());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("AudioVideoMasterDAO.populateMAP::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public String selectFileName(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.HOPT_PLAYERFILE_MST";
	   	
		try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        try
        {
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("AudioVideoMasterDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("No File Name Exists ... ");
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
	
	public AudioVideoMasterVO getAudioVideoForModify(String fileCode,String slNo,UserVO userVO)
	{
		AudioVideoMasterVO avMasterVO=new AudioVideoMasterVO();
		String query ="" ;
		Map populateMap =new HashMap();
		Connection conn =super.getTransactionContext().getConnection(); 
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.MODIFY.HOPT_PLAYERFILE_MST";
	   	
	   	try
	   	{
	   		query =HelperMethodsDAO.getQuery(filename,queryKey);
	   	}
	   	catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
	   	try
	   	{
	   		populateMap.put(sq.next(),fileCode);
	   		populateMap.put(sq.next(),userVO.getHospitalCode());
	   		populateMap.put(sq.next(),slNo);
	   	}
	   	catch(Exception e)
        {
        	throw new HisApplicationExecutionException("AudioVideoMasterDAO.populateMAP::"+e);
        }
	   	try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,populateMap);	 
	 	    if(!rs.next())
	 	    {
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else
	 	    {
	 	    	rs.beforeFirst();
	 	    	HelperMethods.populateVOfrmRS(avMasterVO,rs);	 	    	    	
	 	    }
	 	}
	   	catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: "+e);			 
		 }
	   	return avMasterVO;
	}
	
	public String getFileName(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="GET.HOPT_PLAYERFILE_MST";
	   	
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
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("AudioVideoMasterDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("No File Name Exists ... ");
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
	
	public void saveModifyAudioVideo(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="MODIFYSAVE.HOPT_PLAYERFILE_MST";
	   	
	   	try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileCode());
	   		populateMAP.put(sq.next(),audioVideoMasterVO.getFileCode());
	   		populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileHeader());
        	populateMAP.put(sq.next(),OpdConfig.OPD_AUDIO_VIDEO_NAME);
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileCode());
        	populateMAP.put(sq.next(),audioVideoMasterVO.getExt());
        	
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),audioVideoMasterVO.getIsDefault());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("AudioVideoMasterDAO.populateMAP::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public void updateudioVideo(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="UPDATE.HOPT_PLAYERFILE_MST";
	   	
		try
	   	{
	   		query =HelperMethodsDAO.getQuery(filename,queryKey);
	   	}
	   	catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
	   	try
	   	{
	   		populateMAP.put(sq.next(),userVO.getSeatId());
	   		populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
	   		populateMAP.put(sq.next(),audioVideoMasterVO.getFileCode());
	   		populateMAP.put(sq.next(),userVO.getHospitalCode());
	   		populateMAP.put(sq.next(),audioVideoMasterVO.getSlNo());

	   	}
	   	catch(Exception e)
        {
        	throw new HisApplicationExecutionException("AudioVideoMasterDAO.populateMAP::"+e);
        }
	   	try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public boolean checkDuplicateFile(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.DUPLICATE_CHECK.HOPT_PLAYERFILE_MST";
	   	
	   	try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileHeader());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	rs.next();
        	if(rs.getInt(1)==0)
        	{
        		flag=true;
        	}
        	else
        	{
        		flag=false;
        	}
            
            return flag;
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
	
	public boolean check_Modify_DuplicateFile(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.MODIFY_DUPLICATE_CHECK.HOPT_PLAYERFILE_MST";
	   	
	   	try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileHeader());
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),audioVideoMasterVO.getFileCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	rs.next();
        	if(rs.getInt(1)==0)
        	{
        		flag=true;
        	}
        	else
        	{
        		flag=false;
        	}
            
            return flag;
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
}
