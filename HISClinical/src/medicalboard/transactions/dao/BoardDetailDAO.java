package medicalboard.transactions.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class BoardDetailDAO extends DataAccessObject implements BoardDetailDAOi{
	
	public BoardDetailDAO(TransactionContext _tx) {
		super(_tx);
	}
	
	public void create(BoardDetailVO boardDetailVO, UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
//		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey="INSERT.HMBT_BOARD_DTL";
		Sequence sq = new Sequence();
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
			
			populateMap.put(sq.next(), boardDetailVO.getBoardNo());
			
			//sereal number
			populateMap.put(sq.next(), boardDetailVO.getBoardNo());
				
			populateMap.put(sq.next(), boardDetailVO.getBoardStartTime());
			populateMap.put(sq.next(), boardDetailVO.getActualDateTime());
			populateMap.put(sq.next(), boardDetailVO.getCertificateTypeId());
			populateMap.put(sq.next(), boardDetailVO.getLocation());
			populateMap.put(sq.next(), boardDetailVO.getBoardStatus());
			populateMap.put(sq.next(), boardDetailVO.getCloseDateTime());
			populateMap.put(sq.next(), boardDetailVO.getApprovedDate());
			populateMap.put(sq.next(), boardDetailVO.getApprovedBy());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), userVO.getSeatId());
			//entrydate
			populateMap.put(sq.next(), userVO.getHospitalCode());
			populateMap.put(sq.next(), boardDetailVO.getBoardId());
			
					
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldReqExtBldBankDtlDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public String  checkIsBoardExistForCertifecateType(String certificateTypeId,String scheduleDate ,UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey="SELECT.COUNT.HMBT_BOARD_DTL";
		Sequence sq = new Sequence();
		String count="";
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
			populateMap.put(sq.next(), userVO.getHospitalCode());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), scheduleDate);
			populateMap.put(sq.next(), certificateTypeId);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldReqExtBldBankDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			rs.next();
			count=rs.getString(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return count;
	}
	
	public String  getBoardNumber(UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey="SELECT.BOARD_NO.HMBT_BOARD_DTL";
		Sequence sq = new Sequence();
		String boardNo="";
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
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldReqExtBldBankDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			rs.next();
			boardNo=rs.getString(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return boardNo;
	}
	
	public void deleteBoardDetail(String boardNo,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		 String queryKey="DELETE.HMBT_BOARD_DTL";
		 Sequence sq=new Sequence();
		 
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
			  populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			  populateMAP.put(sq.next(), MedicalBoardConfig.BOARD_CLOSED);
			  populateMAP.put(sq.next(), boardNo);
			  
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				//throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
	public void updateBoardDetail(String boardNo,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		 String queryKey="UPDATE.HMBT_BOARD_DTL";
		 Sequence sq=new Sequence();
		 
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
			  populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			  //populateMAP.put(sq.next(), MedicalBoardConfig.BOARD_CLOSED);
			  populateMAP.put(sq.next(), boardNo);
			  
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				//throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
}
