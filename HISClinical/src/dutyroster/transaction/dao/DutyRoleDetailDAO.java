package dutyroster.transaction.dao;

import java.util.HashMap;
import java.util.Map;

//import disaster.DisasterConfig;
import dutyroster.DutyRosterConfig;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DisasterDetailVO;
import hisglobal.vo.DutyRoleDetailVO;
import hisglobal.vo.UserVO;

public class DutyRoleDetailDAO extends DataAccessObject {

	public DutyRoleDetailDAO(TransactionContext _tx) {
		super(_tx);
		
	}
	
	public void create(DutyRoleDetailVO _dutyRoleDetailVO,UserVO _userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename=DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	    String queryKey="INSERT.HDRT_DUTY_ROLE_DTL"; 
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	   
	    try{
	    	populateMap(_dutyRoleDetailVO,_userVO,populateMAP);
	    }
	    catch(Exception e){
	    	throw new HisDataAccessException("DutyRoleDetailDAO:populateMap(_dutyRoleDetailVO,populateMAP)::"+e);
	    }
	    try{
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e){
	    	throw new HisDataAccessException("INSERT FAILED:::create DutyRoleDetailDAO.getResultset"+e);
	    }
	}
	
	

		private void populateMap(DutyRoleDetailVO detailVO, UserVO _uservo,	Map populateMAP) {
			Sequence sq=new Sequence();
			populateMAP.put(sq.next(), detailVO.getGeneratedRosterId());
			populateMAP.put(sq.next(), detailVO.getDutyAreaId());
			populateMAP.put(sq.next(), detailVO.getEmpID());
			populateMAP.put(sq.next(), detailVO.getShiftID());
			populateMAP.put(sq.next(), detailVO.getRoleID());
			populateMAP.put(sq.next(), detailVO.getDutyRemarks());
			populateMAP.put(sq.next(), detailVO.getCancelRemarks());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _uservo.getSeatId());
			populateMAP.put(sq.next(), _uservo.getHospitalCode());
			populateMAP.put(sq.next(), detailVO.getFromDate());
			populateMAP.put(sq.next(), detailVO.getToDate());
			
		}


		
		public void update(DutyRoleDetailVO _dutyRoleDetailVO,UserVO _userVO)
		{
			String query  = "";
		    Map populateMAP =new HashMap();
		    String filename=DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		    String queryKey="UPDATE_IS_VALID.HDRT_DUTY_ROLE_DTL"; 
		    try{
		    	query =HelperMethodsDAO.getQuery(filename,queryKey);
		    }
		    catch(Exception e){
		    	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		    }
		   
		    try{
		    	Sequence sq=new Sequence();
		    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
				populateMAP.put(sq.next(), _dutyRoleDetailVO.getGeneratedRosterId());
				populateMAP.put(sq.next(), _dutyRoleDetailVO.getShiftID());
				populateMAP.put(sq.next(), _dutyRoleDetailVO.getFromDate());
				populateMAP.put(sq.next(), _dutyRoleDetailVO.getToDate());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		    }
		    catch(Exception e){
		    	throw new HisDataAccessException("DutyRoleDetailDAO:populateMap(_dutyRoleDetailVO,populateMAP)::"+e);
		    }
		    try{
		    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		    }
		    catch(Exception e){
		    	throw new HisDataAccessException("INSERT FAILED:::create DutyRoleDetailDAO.getResultset"+e);
		    }
		}
		
}
