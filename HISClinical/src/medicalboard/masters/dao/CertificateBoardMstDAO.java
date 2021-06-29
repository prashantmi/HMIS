package medicalboard.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MbCertificateBoardMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import medicalboard.MedicalBoardConfig;


public class CertificateBoardMstDAO extends DataAccessObject implements CertificateBoardMstDAOi {

	public CertificateBoardMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	public List getUnselectedBoard(MbCertificateBoardMstVO mBoardMstVO,UserVO userVO)
	    {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
			String queryKey ="SELECT_UNSELECTED_BOARD.HMBT_BOARD_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			 populateMAP.put(sq.next(), mBoardMstVO.getBoardTypeId());
			 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			 populateMAP.put(sq.next(), userVO.getHospitalCode());
			 populateMAP.put(sq.next(), mBoardMstVO.getCertificateTypeID());
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				//if (!rs.next()) throw new HisRecordNotFoundException("No Board Found For This Certificate Type");
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord=new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
	  }
	
	
	
	public List getSelectedBoard(MbCertificateBoardMstVO mBoardMstVO,UserVO userVO)
    {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="SELECT_SELECTED_BOARD.HMBT_CERTIFICATE_BOARD_MST";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		 populateMAP.put(sq.next(), userVO.getHospitalCode());
		 populateMAP.put(sq.next(), mBoardMstVO.getCertificateTypeID());
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = null;
		try
		{
			alRecord=new ArrayList();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		System.out.println("alRecord primary. cat" + alRecord);
		return alRecord;
  }

	
	
	
	public void create(MbCertificateBoardMstVO mBoardMstVO,String BoardId,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_CERTIFICATE_BOARD_MST";
		
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
	         	populateMAP.put(sq.next(),mBoardMstVO.getCertificateTypeID());
	        	populateMAP.put(sq.next(),mBoardMstVO.getCertificateTypeID());
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),BoardId);
	        	populateMAP.put(sq.next(),BoardId);
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	         	populateMAP.put(sq.next(),userVO.getSeatId());
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("populateMAP::"+e);
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
	
	
	
	
	public void updateCertificateBoardDetail(MbCertificateBoardMstVO mBoardMstVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="UPDATE.HMBT_CERTIFICATE_BOARD_MST";
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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), mBoardMstVO.getCertificateTypeID());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
	
	
	
	
	
	
}
