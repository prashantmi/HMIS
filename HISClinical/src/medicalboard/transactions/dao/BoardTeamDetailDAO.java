package medicalboard.transactions.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.BoardTeamDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class BoardTeamDetailDAO extends DataAccessObject implements BoardTeamDetailDAOi
{
	public BoardTeamDetailDAO(TransactionContext _tx) {
		super(_tx);
	}
	
	public void create(BoardTeamDetailVO boardTeamDetailVO, UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
//		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey="INSERT.HMBT_BOARD_TEAM_DTL";
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
			
			populateMap.put(sq.next(), boardTeamDetailVO.getBoardNo());
			
			//sereal number
			populateMap.put(sq.next(), boardTeamDetailVO.getBoardNo());
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
			populateMap.put(sq.next(), userVO.getHospitalCode());
			populateMap.put(sq.next(), boardTeamDetailVO.getEmployeeId());
			populateMap.put(sq.next(), boardTeamDetailVO.getRoleId());
			populateMap.put(sq.next(), boardTeamDetailVO.getDutyStatus());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), userVO.getSeatId());
			populateMap.put(sq.next(), boardTeamDetailVO.getLastModifyDate());
			populateMap.put(sq.next(), boardTeamDetailVO.getLastModifySeatId());
			//entrydate
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
	
	public void deleteBoardTeamDetail(String boardNo,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		 String queryKey="DELETE.HMBT_BOARD_TEAM_DTL";
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
			  populateMAP.put(sq.next(), MedicalBoardConfig.DUTY_CANCELED);
			  populateMAP.put(sq.next(), userVO.getSeatId());
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
	
	public void updateBoardTeamDetail(String boardNo,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		 String queryKey="UPDATE.HMBT_BOARD_TEAM_DTL";
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
			  //populateMAP.put(sq.next(), MedicalBoardConfig.DUTY_CANCELED);
			  populateMAP.put(sq.next(), userVO.getSeatId());
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
