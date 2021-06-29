package mrd.transaction.dao;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.SummonDetailVO;

public class SummonDetailDAO extends DataAccessObject implements SummonDetailDAOi 
{
	public  SummonDetailDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(SummonDetailVO summonDetailVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_SUMMON_DTL";
        
       
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
        	
        	//getting summonId
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(), summonDetailVO.getSummonTypeId());
        	populateMAP.put(sq.next(), summonDetailVO.getRecordType());
        	populateMAP.put(sq.next(), summonDetailVO.getRecordCat());
        	populateMAP.put(sq.next(), summonDetailVO.getPatCrNo());
        	populateMAP.put(sq.next(), summonDetailVO.getEpisodeCode());
        	populateMAP.put(sq.next(), summonDetailVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), summonDetailVO.getMLCNo());
        	populateMAP.put(sq.next(), summonDetailVO.getPatAdmNo());
        	populateMAP.put(sq.next(), summonDetailVO.getCertificateNo());
        	populateMAP.put(sq.next(), summonDetailVO.getDeceasedNo());
        	populateMAP.put(sq.next(), summonDetailVO.getPostmortemId());
        	populateMAP.put(sq.next(), summonDetailVO.getSummonDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getSummonReceiveDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getHearingDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getCourtName());
        	populateMAP.put(sq.next(), summonDetailVO.getCourtAddress());
        	populateMAP.put(sq.next(), summonDetailVO.getJudgeName());
        	populateMAP.put(sq.next(), summonDetailVO.getCaseNo());
        	populateMAP.put(sq.next(), summonDetailVO.getPoliceStation());
        	populateMAP.put(sq.next(), summonDetailVO.getConstableName());
        	populateMAP.put(sq.next(), summonDetailVO.getConstableDesig());
        	populateMAP.put(sq.next(), summonDetailVO.getConstableBadgeNo());
        	//populateMAP.put(sq.next(), summonDetailVO.getIsEmpSpecific());
        	populateMAP.put(sq.next(), summonDetailVO.getEmpName());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignTo());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignSame());
        	populateMAP.put(sq.next(), summonDetailVO.getOtherAssignReason());
        	//populateMAP.put(sq.next(), summonDetailVO.getSummonAck());
        	populateMAP.put(sq.next(), summonDetailVO.getEntryMode());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignBy());
        	populateMAP.put(sq.next(), summonDetailVO.getSummonRemarks());
        	populateMAP.put(sq.next(), summonDetailVO.getStatus());
        	populateMAP.put(sq.next(), summonDetailVO.getVisitRemarks());
        	populateMAP.put(sq.next(), summonDetailVO.getNextHearingDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getInstruction());
        	populateMAP.put(sq.next(), summonDetailVO.getPatName());
        	populateMAP.put(sq.next(), summonDetailVO.getFatherName());
        	populateMAP.put(sq.next(), summonDetailVO.getSpouseName());
        	populateMAP.put(sq.next(), summonDetailVO.getMotherName());
        	populateMAP.put(sq.next(), summonDetailVO.getPatGenderCode());
        	populateMAP.put(sq.next(), summonDetailVO.getPatDOB());
        	populateMAP.put(sq.next(), summonDetailVO.getPatAgeType());
        	populateMAP.put(sq.next(), summonDetailVO.getPatAddress());
        	populateMAP.put(sq.next(), summonDetailVO.getCIDNo());
        	//populateMAP.put(sq.next(), summonDetailVO.getForwardTo());
        	populateMAP.put(sq.next(), summonDetailVO.getIsUpload());
        	//populateMAP.put(sq.next(), summonDetailVO.getScanSummon());
        	//populateMAP.put(sq.next(), summonDetailVO.getDeptCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	// entryDate(sysdate)
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignDate());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignSeatId());
        	populateMAP.put(sq.next(), summonDetailVO.getPostentryDate());
        	populateMAP.put(sq.next(), summonDetailVO.getPostSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
	
	public void saveComputSummonDtl(SummonDetailVO summonDetailVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.COMPUTRIZE.HPMRT_SUMMON_DTL";
        
       
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
        	
        	//getting summonId
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(), summonDetailVO.getSummonTypeId());
        	populateMAP.put(sq.next(), summonDetailVO.getRecordType());
        	populateMAP.put(sq.next(), summonDetailVO.getRecordCat());
        	populateMAP.put(sq.next(), summonDetailVO.getPatCrNo());
        	populateMAP.put(sq.next(), summonDetailVO.getEpisodeCode());
        	populateMAP.put(sq.next(), summonDetailVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), summonDetailVO.getMLCNo());
        	populateMAP.put(sq.next(), summonDetailVO.getPatAdmNo());
        	populateMAP.put(sq.next(), summonDetailVO.getCertificateNo());
        	populateMAP.put(sq.next(), summonDetailVO.getDeceasedNo());
        	populateMAP.put(sq.next(), summonDetailVO.getPostmortemId());
        	populateMAP.put(sq.next(), summonDetailVO.getSummonDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getSummonReceiveDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getHearingDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getCourtName());
        	populateMAP.put(sq.next(), summonDetailVO.getCourtAddress());
        	populateMAP.put(sq.next(), summonDetailVO.getJudgeName());
        	populateMAP.put(sq.next(), summonDetailVO.getCaseNo());
        	populateMAP.put(sq.next(), summonDetailVO.getPoliceStation());
        	populateMAP.put(sq.next(), summonDetailVO.getConstableName());
        	populateMAP.put(sq.next(), summonDetailVO.getConstableDesig());
        	populateMAP.put(sq.next(), summonDetailVO.getConstableBadgeNo());
        	//populateMAP.put(sq.next(), summonDetailVO.getIsEmpSpecific());
        	populateMAP.put(sq.next(), summonDetailVO.getEmpName());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignTo());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignSame());
        	populateMAP.put(sq.next(), summonDetailVO.getOtherAssignReason());
        	//populateMAP.put(sq.next(), summonDetailVO.getSummonAck());
        	populateMAP.put(sq.next(), summonDetailVO.getEntryMode());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignBy());
        	populateMAP.put(sq.next(), summonDetailVO.getSummonRemarks());
        	populateMAP.put(sq.next(), summonDetailVO.getStatus());
        	populateMAP.put(sq.next(), summonDetailVO.getVisitRemarks());
        	populateMAP.put(sq.next(), summonDetailVO.getNextHearingDateTime());
        	populateMAP.put(sq.next(), summonDetailVO.getInstruction());
        	populateMAP.put(sq.next(), summonDetailVO.getPatName());
        	populateMAP.put(sq.next(), summonDetailVO.getFatherName());
        	populateMAP.put(sq.next(), summonDetailVO.getSpouseName());
        	populateMAP.put(sq.next(), summonDetailVO.getMotherName());
        	populateMAP.put(sq.next(), summonDetailVO.getPatGenderCode());
        	populateMAP.put(sq.next(), summonDetailVO.getPatDOB());
        	//populateMAP.put(sq.next(), summonDetailVO.getPatAgeType());
        	populateMAP.put(sq.next(), summonDetailVO.getPatAddress());
        	populateMAP.put(sq.next(), summonDetailVO.getCIDNo());
        	//populateMAP.put(sq.next(), summonDetailVO.getForwardTo());
        	populateMAP.put(sq.next(), summonDetailVO.getIsUpload());
        	//populateMAP.put(sq.next(), summonDetailVO.getScanSummon());
        	//populateMAP.put(sq.next(), summonDetailVO.getDeptCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	// entryDate(sysdate)
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignDate());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignSeatId());
        	populateMAP.put(sq.next(), summonDetailVO.getPostentryDate());
        	populateMAP.put(sq.next(), summonDetailVO.getPostSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
	
	public void SaveAssignDetail(SummonDetailVO summonDetailVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="UPDATE.ASSIGN_DETAIL.HPMRT_SUMMON_DTL";
        
       
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
        	populateMAP.put(sq.next(), summonDetailVO.getAssignTo());
        	populateMAP.put(sq.next(), summonDetailVO.getAssignSame());
        	populateMAP.put(sq.next(), summonDetailVO.getOtherAssignReason());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	//assign date=sysdate
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), summonDetailVO.getStatus());
        	populateMAP.put(sq.next(), summonDetailVO.getSummonId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
	
	public void SavePostSummonDetail(SummonDetailVO summonDetailVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="UPDATE.POST_SUMMON.HPMRT_SUMMON_DTL";
        
       
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
        	populateMAP.put(sq.next(), summonDetailVO.getVisitRemarks());
        	populateMAP.put(sq.next(), summonDetailVO.getNextHearingDateTime());
        	//postnary date=sysdate
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), summonDetailVO.getStatus());
        	
        	populateMAP.put(sq.next(), summonDetailVO.getSummonId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        	   	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
	
	public void SaveSummonAcceptedDetail(String summonId,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="UPDATE.SUMMON_ACCEPTED_DETAIL.HPMRT_SUMMON_DTL";
        
       
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
        	populateMAP.put(sq.next(), MrdConfig.SUMMON_ACCEPTED);
        	populateMAP.put(sq.next(), summonId);
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
}
