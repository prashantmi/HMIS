package medicalboard.transactions.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import medicalboard.MedicalBoardConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;



public class MbChecklistDetailDAO extends DataAccessObject implements MbChecklistDetailDAOi{

	public MbChecklistDetailDAO(TransactionContext _tx) {
		super(_tx);
	}

	public void create(MedicalBoardChecklistVO mChecklistVO, UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
//		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey="INSERT.HMBT_REQ_CHECKLIST_DTL";
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
			populateMap.put(sq.next(), mChecklistVO.getReqID());
			populateMap.put(sq.next(), mChecklistVO.getReqID());
			populateMap.put(sq.next(), mChecklistVO.getChecklistID());
			//populateMap.put(sq.next(), mChecklistVO.getChecklistBy());
			populateMap.put(sq.next(), mChecklistVO.getRemarks());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), userVO.getSeatId());
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
	
	
	public List getCheckListByReqId(String reqId, UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey="SELECT.HMBT_REQ_CHECKLIST_DTL";
		Sequence sq = new Sequence();
		List <MedicalBoardChecklistVO> checklistVOList=new ArrayList<MedicalBoardChecklistVO>();
		MedicalBoardChecklistVO checkListVO=null;
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
			//populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMap.put(sq.next(), reqId);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldReqExtBldBankDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			while(rs.next()){
				checkListVO=new MedicalBoardChecklistVO();
				HelperMethods.populateVOfrmRS(checkListVO, rs);
				checklistVOList.add(checkListVO);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return checklistVOList;
	}
	
	
	

	
}
