package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ImagePointerMasterDAO extends DataAccessObject implements ImagePointerMasterDAOi
{
	public ImagePointerMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void create(ImagePointerMasterVO ImagePointerMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HOPT_IMAGEDESC_MST";
		
		
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
	        	
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),"1");	//serial no=1
	        	populateMAP.put(sq.next(),ImagePointerMasterVO.getImageDesc());
	        	populateMAP.put(sq.next(),ImagePointerMasterVO.getColor());
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),ImagePointerMasterVO.getIsDefault());
	        	
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("ImagePointerMasterDAO.populateMAP::"+e);
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
	
	//Checking For Duplicate Name
	public String checkDuplicateImagePointer(ImagePointerMasterVO ImagePointerMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="CHECK_DUPLICATE_NAME.HOPT_IMAGEDESC_MST";
		
		
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
	        populateMAP.put(sq.next(),ImagePointerMasterVO.getImageDesc());
	        
	    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);	
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("ImagePointerMasterDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
	
	public ImagePointerMasterVO getDataForModify(ImagePointerMasterVO ImagePointerMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ImagePointerMasterVO vo=new ImagePointerMasterVO();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HOPT_IMAGEDESC_MST";
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
			populateMAP.put(sq.next(), ImagePointerMasterVO.getImageDescId());
			populateMAP.put(sq.next(), ImagePointerMasterVO.getSlNo());
			
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImagePointerMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
				/*_ImagePointerMasterVO.setAutopsyFor(rs.getString(1));
				_ImagePointerMasterVO.setAutopsyType(rs.getString(2));
				_ImagePointerMasterVO.setMortuaryType(rs.getString(3));
				_ImagePointerMasterVO.setEmailId(rs.getString(4));
				_ImagePointerMasterVO.setEmpNo(rs.getString(5));
				_ImagePointerMasterVO.setLocationDesc(rs.getString(6));
				_ImagePointerMasterVO.setMortuaryName(rs.getString(7));
				_ImagePointerMasterVO.setRoomNo(rs.getString(8));
				_ImagePointerMasterVO.setMortuaryShortName(rs.getString(9));
				_ImagePointerMasterVO.setWebsite(rs.getString(10));
				_ImagePointerMasterVO.setBlockId(rs.getString(11));
				_ImagePointerMasterVO.setBuildingCode(rs.getString(12));
				_ImagePointerMasterVO.setFloorId(rs.getString(13));
				_ImagePointerMasterVO.setRoomId(rs.getString(14));
				_ImagePointerMasterVO.setDepartmentCode(rs.getString(15));
				_ImagePointerMasterVO.setEffectiveFrom(rs.getString(16));
				_ImagePointerMasterVO.setEffectiveTo(rs.getString(17));
						*/
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	public void update(ImagePointerMasterVO ImagePointerMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HOPT_IMAGEDESC_MST";

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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), ImagePointerMasterVO.getImageDescId());
			populateMAP.put(sq.next(), ImagePointerMasterVO.getSlNo());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImagePointerMasterDAO.populateMAP::" + e);
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
	
	public void modifySave(ImagePointerMasterVO ImagePointerMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFYINSERT.HOPT_IMAGEDESC_MST";

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
			populateMAP.put(sq.next(), ImagePointerMasterVO.getImageDescId());
			populateMAP.put(sq.next(), ImagePointerMasterVO.getImageDescId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),ImagePointerMasterVO.getImageDesc());
        	populateMAP.put(sq.next(),ImagePointerMasterVO.getColor());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
        	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(), ImagePointerMasterVO.getIsValid());
        	populateMAP.put(sq.next(),ImagePointerMasterVO.getIsDefault());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
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
		
	public String checkDuplicateNameForModify(ImagePointerMasterVO ImagePointerMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HOPT_IMAGEDESC_MST";
		
		
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
	        populateMAP.put(sq.next(),ImagePointerMasterVO.getImageDesc());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),ImagePointerMasterVO.getImageDescId());
	   
	       	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	       	
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("ImagePointerMasterDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
	
	// Getting Default Images Pointer List
	public List<ImagePointerMasterVO> getDefaultImagePointersList(UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DEFAULT_IMAGEDESCS.HOPT_IMAGEDESC_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), OpdConfig.YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ImagePointerMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Default Image Pointer Exists ...");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<ImagePointerMasterVO> lstImagePointers = new ArrayList<ImagePointerMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ImagePointerMasterVO.class,rs);
				for(ValueObject v :vo)
					lstImagePointers.add((ImagePointerMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getting Default Image Pointers List" + e);
		}
		return lstImagePointers;
	}

}
