package opd.master.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.commons.codec.binary.Base64;
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
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.Sequence;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ImageMasterDAO extends DataAccessObject implements ImageMasterDAOi
{
	Logger log;

	public ImageMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// Inserting Image Record
	public void create(ImageMasterVO imageMasterVO, UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="INSERT.HOPT_IMAGE_MST";
	   	
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
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode()); //Modified by Saurabh on 7-Sept-2017 for inserting hopital code '100' into table
        	populateMAP.put(sq.next(),OpdConfig.SLNO);
        	populateMAP.put(sq.next(),imageMasterVO.getImageName());
        	populateMAP.put(sq.next(),OpdConfig.OPD_IMAGE_FIRST_NAME);
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),imageMasterVO.getExt());
        	
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	//populateMAP.put(sq.next(),imageMasterVO.getIsDefault()); 
        	populateMAP.put(sq.next(),OpdConfig.YES);// Modified by VASU on 7-Sept-2017 to get IsDefault value "1" for image examination
        	//populateMAP.put(sq.next(),imageMasterVO.getImageFile());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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
	
	public void saveImageToPostgres(ImageMasterVO imageMasterVO, UserVO userVO) throws SQLException
	{
		PreparedStatement ps = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		try
		{
			if(imageMasterVO.getImageFile()!=null)
			{
			 String query = " update HOPT_IMAGE_MST SET gbyte_image_data=? where hopnum_image_code=? ";
		   	 ps = conn.prepareStatement(query);
		   	 InputStream iss = new ByteArrayInputStream(imageMasterVO.getImageFile());	
		   	 ps.setBinaryStream(1,iss,imageMasterVO.getImageFile().length);
			// ps.setBytes(1,decodedData);
		   	 ps.setString(2,imageMasterVO.getImageCode());
		   	// ps.setString(3,_patientImageDtlVO.getPatCrNo());
		   	 ps.executeUpdate();
			}
		}
		catch(Exception e)
		   	{
		   		System.out.println("exception in Image saving transaction..."+e); 
		   
		   			ps.close();
		   			ps =null;  
		   			try {
						throw new Exception("error in saving image to postgres...terminated unsuccesfully");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    
		   	}
	}
	
	/*public String selectFileName(ImageMasterVO imageMasterVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HOPT_IMAGE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), imageMasterVO.getImageName());
			populateMAP.put(sq.next(), OpdConfig.OPD_IMAGE_FIRST_NAME);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), imageMasterVO.getExt());

			populateMAP.put(sq.next(), RegistrationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
*/
	// Selecting File Name
	public String selectFileName(ImageMasterVO imageMasterVO, UserVO userVO)

	{

		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.FILENAME.HOPT_IMAGE_MST";
	   	
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
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode()); // Modified by Saurabh on 7-Sept-2017 
        	populateMAP.put(sq.next(),imageMasterVO.getImageName());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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
		/*String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.FILENAME.HOPT_IMAGE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), imageMasterVO.getImageName());
			populateMAP.put(sq.next(), RegistrationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No File Name Exists ... ");
			rs.first();
			return rs.getString(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

	}*/

	public ImageMasterVO getImageForModify(String imageCode,String imageSlno, UserVO userVO)
	{

		ImageMasterVO imageMasterVO=new ImageMasterVO();
		String query =  "" ;
		Map populateMap =new HashMap();
		Connection conn =super.getTransactionContext().getConnection(); 
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.MODIFY.HOPT_IMAGE_MST";
	   	
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
	   		populateMap.put(sq.next(),imageCode);
	   		populateMap.put(sq.next(),imageSlno);
	   		populateMap.put(sq.next(),userVO.getHospitalCode());
//	   		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
	   	}
	   	catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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
	 	    
	 	    	HelperMethods.populateVOfrmRS(imageMasterVO,rs);	 	    	    	
	 	    }
	 	}
	   /*	catch(Exception e)

		ImageMasterVO imageMasterVO = new ImageMasterVO();
		String query = "";
		Map populateMap = new HashMap();
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.MODIFY.HOPT_IMAGE_MST";

		try

		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMap.put(sq.next(), imageCode);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(imageMasterVO, rs);
			}
		}*/
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return imageMasterVO;
	}

	// Updating Image Record
	public void update(ImageMasterVO imageMasterVO, UserVO userVO)
	{

		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="UPDATE.HOPT_IMAGE_MST";
	   	
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
	   		populateMAP.put(sq.next(),imageMasterVO.getImageCode());
	   		populateMAP.put(sq.next(),userVO.getHospitalCode());
	   		populateMAP.put(sq.next(),imageMasterVO.getSlNo());

	   	}
	   	catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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

		/*String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HOPT_IMAGE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMAP.put(sq.next(), imageMasterVO.getImageName());
			populateMAP.put(sq.next(), OpdConfig.OPD_IMAGE_FIRST_NAME);
			populateMAP.put(sq.next(), imageMasterVO.getImageCode());
			populateMAP.put(sq.next(), imageMasterVO.getExt());
			populateMAP.put(sq.next(), imageMasterVO.getImageCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}*/

	}

	public void modifySaveWithImage(ImageMasterVO imageMasterVO, UserVO userVO)
	{

		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="MODIFYSAVEWITHIMAGE.HOPT_IMAGE_MST";
	   	
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
	   		
	   		populateMAP.put(sq.next(),imageMasterVO.getImageCode());
	   		populateMAP.put(sq.next(),imageMasterVO.getImageCode());
	   		populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),imageMasterVO.getImageName());
        	populateMAP.put(sq.next(),OpdConfig.OPD_IMAGE_FIRST_NAME);
        	populateMAP.put(sq.next(),imageMasterVO.getImageCode());
        	populateMAP.put(sq.next(),imageMasterVO.getExt());
        	populateMAP.put(sq.next(),imageMasterVO.getIsValid());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),imageMasterVO.getIsDefault());
        	//populateMAP.put(sq.next(),imageMasterVO.getImageFile() );
	   	}
	   	catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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
	
	public void modifySaveWithoutImage(ImageMasterVO imageMasterVO, UserVO userVO)
	{

		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="MODIFYSAVEWITHOUTIMAGE.HOPT_IMAGE_MST";
	   	
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
	   		
	   		
	   		populateMAP.put(sq.next(),imageMasterVO.getImageCode());
	   		populateMAP.put(sq.next(),imageMasterVO.getImageCode());
	   		populateMAP.put(sq.next(),userVO.getHospitalCode());
	   		populateMAP.put(sq.next(),imageMasterVO.getImageName());
	   		populateMAP.put(sq.next(),imageMasterVO.getFileName());
	   		populateMAP.put(sq.next(),imageMasterVO.getIsValid());
	   		populateMAP.put(sq.next(),userVO.getSeatId());
	   		populateMAP.put(sq.next(),userVO.getHospitalCode());
	   		populateMAP.put(sq.next(),imageMasterVO.getIsDefault());
	   	}
	   	catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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

	
	// Selecting File Name For Modification
	public String selectFileNameModify(ImageMasterVO imageMasterVO, UserVO userVO)
	{

		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.FILENAME.MODIFY.HOPT_IMAGE_MST";
	   	
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
        	populateMAP.put(sq.next(),imageMasterVO.getImageCode());
        	populateMAP.put(sq.next(),imageMasterVO.getImageName());
        	populateMAP.put(sq.next(),imageMasterVO.getIsValid());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),imageMasterVO.getImageCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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

		/*String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.FILENAME.MODIFY.HOPT_IMAGE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), imageMasterVO.getImageCode());
			populateMAP.put(sq.next(), imageMasterVO.getImageName());
			populateMAP.put(sq.next(), RegistrationConfig.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No File Name Exists ... ");
			rs.first();
			return rs.getString(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}*/

	}

	// Updating Image Name
	public void updateImageName(ImageMasterVO imageMasterVO, UserVO userVO)
	{

		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="UPDATE.IMAGE_NAME.HOPT_IMAGE_MST";
	   	
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
	   		populateMAP.put(sq.next(),imageMasterVO.getImageCode());
	   		populateMAP.put(sq.next(),userVO.getHospitalCode());
	   		populateMAP.put(sq.next(),imageMasterVO.getSlNo());
	   	}
	   	catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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

		/*String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.IMAGE_NAME.HOPT_IMAGE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMAP.put(sq.next(), imageMasterVO.getImageName());
			populateMAP.put(sq.next(), imageMasterVO.getImageCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
*/
	}
	
	public boolean checkDuplicateImage(ImageMasterVO imageMasterVO, UserVO userVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.DUPLICATE_CHECK.HOPT_IMAGE_MST";
	   	
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
        	populateMAP.put(sq.next(),imageMasterVO.getImageName());
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
	
	public boolean check_Modify_DuplicateImage(ImageMasterVO imageMasterVO, UserVO userVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.MODIFY_DUPLICATE_CHECK.HOPT_IMAGE_MST";
	   	
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
        	populateMAP.put(sq.next(),imageMasterVO.getImageName());
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),imageMasterVO.getImageCode());
        	
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

	// Getting Default Images List
	public List<ImageMasterVO> getDefaultImagesList(UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DEFAULT_IMAGES.HOPT_IMAGE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		try
		{
			populateMAP.put(sq.next(), OpdConfig.YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Default Image Exists ...");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<ImageMasterVO> lstImages = new ArrayList<ImageMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ImageMasterVO.class,rs);
				for(ValueObject v :vo)
					lstImages.add((ImageMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getting Default Image List" + e);
		}
		return lstImages;
	}
	
	
	
	public byte[] fetchimageFromPostgres(String imageCode)
	{
		

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		byte[] imgBytes = null;
		InputStream fis=null;
		try
		{
			
			
			String query = "select gbyte_image_data from HOPT_IMAGE_MST where hopnum_image_code=? and gdt_lstmod_date is null and gnum_lstmod_seatid is null ";
			 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		   	 ps.setString(1,imageCode);
			 ResultSet resultSet = ps.executeQuery();
			 
			 if(resultSet!=null && resultSet.next())
				{
				 Base64 codec = new Base64();
				 imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
				  imgBytes = resultSet.getBytes(1);
				  if(imgBytes!=null)
				  fis = new ByteArrayInputStream(imgBytes); 
					
				  
				}
		
	}
	   
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return imgBytes;
	}
	
	
	public void updateImageToPostgres(ImageMasterVO imageMasterVO, UserVO userVO) throws SQLException
	{
		PreparedStatement ps = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		try
		{
			if(imageMasterVO.getImageFile()!=null)
			{
			 String query = " update HOPT_IMAGE_MST SET gbyte_image_data=? where hopnum_image_code=? and gdt_lstmod_date is null and gnum_lstmod_seatid is null ";
		   	 ps = conn.prepareStatement(query);
		   	 InputStream iss = new ByteArrayInputStream(imageMasterVO.getImageFile());	
		   	 ps.setBinaryStream(1,iss,imageMasterVO.getImageFile().length);
			// ps.setBytes(1,decodedData);
		   	 ps.setString(2,imageMasterVO.getImageCode());
		   	// ps.setString(3,_patientImageDtlVO.getPatCrNo());
		   	 ps.executeUpdate();
			}
		}
		catch(Exception e)
		   	{
		   		System.out.println("exception in Image saving transaction..."+e); 
		   
		   			ps.close();
		   			ps =null;  
		   			try {
						throw new Exception("error in saving image to postgres...terminated unsuccesfully");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    
		   	}
	}

	//Added by Vasu on 26.Dec.2019
	public void createTemplateImage(HisFileControlUtil fileUtil, UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="INSERT.HOPT_IMAGE_MST";
	   	
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
        	populateMAP.put(sq.next(),fileUtil.getFileName());
        	populateMAP.put(sq.next(),OpdConfig.OPD_IMAGE_FIRST_NAME);
    
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),".jpg");
        	
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	 
        	populateMAP.put(sq.next(),OpdConfig.NO);
        	
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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

	//Added by Vasu on 26.Dec.2019
	public String selectTemplateImageName(HisFileControlUtil fileUtil,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.FILENAME.HOPT_IMAGE_MST";
	   	
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
        	populateMAP.put(sq.next(),fileUtil.getFileName());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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

	//Added by Vasu on 26.Dec.2019
	public void saveTemplateImageFTPDetails(HisFileControlUtil fileUtil,UserVO userVO, String ftpLocation) throws SQLException
	{
		PreparedStatement ps = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		try
		{
			if(fileUtil.getFileContent()!=null)
			{
			 String query = " update HOPT_IMAGE_MST SET hopstr_file_name=? where hopnum_image_code=? ";
		   	 ps = conn.prepareStatement(query);
		   	 //InputStream iss = new ByteArrayInputStream(fileUtil.getFileContent());	
		   	 //ps.setBinaryStream(1,iss,fileUtil.getFileContent().length);
			// ps.setBytes(1,decodedData);
		   	 ps.setString(1,ftpLocation);
		   	 ps.setString(2,fileUtil.getActualFileName());
		   	// ps.setString(3,_patientImageDtlVO.getPatCrNo());
		   	 ps.executeUpdate();
			}
		}
		catch(Exception e)
		   	{
		   		System.out.println("exception in Image saving transaction..."+e); 
		   
		   			ps.close();
		   			ps =null;  
		   			try {
						throw new Exception("error in saving image to postgres...terminated unsuccesfully");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    
		   	}
		
	}

	//Added by Vasu on 27.Dec.2019
	public String getTemplateImageFileNo(String fileName) 
	{	
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.FILENO.HOPT_IMAGE_MST";
	   	
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
        	
        	populateMAP.put(sq.next(),fileName);
        	populateMAP.put(sq.next(),fileName);
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
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
	
	
	
}
