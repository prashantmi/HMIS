package new_investigation.transactions.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import new_investigation.InvestigationConfig;
import new_investigation.vo.template.invStatusDashboardVO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;

public class invStatusDashboardDAO extends DataAccessObject{

	public invStatusDashboardDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}

	public List invStatusDashboardRecord(invStatusDashboardVO invstatusdashboardvo, UserVO _UserVO) {
		String query="";
		Map populateMap= new HashMap();
		invStatusDashboardVO dashVO = new invStatusDashboardVO();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_INV_STATUS_DASHBOARD_RECORD.HIVT_REQUISITION_DTL";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		/*populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
		populateMap.put(sq.next(),_UserVO.getUserSeatId());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		*/
		 
		
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
			rs = HelperMethodsDAO.executeQuery(conn, query);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(dashVO, rs);
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
		
		parameterCombo.add(dashVO);
		return parameterCombo;
	}

	public List getRequestedSampleListDashboard(String recordRequested,UserVO userVO) {
		String query="";
		ResultSet rs=null;
		List sampleList=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_INV_STATUS_DASHBOARD_RECORD_LISITNG";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
	
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
			//1 : pending for sample collection,    //2:pending for packing list generation
			//3 : pending for sample acceptance,    //4:pending for result entry
			//5 : pending for machine result entry, //6:pending for result validation
			//7 : pending for result revalidation
			
			
			if(Integer.parseInt(recordRequested)==2){
				query+=" and hivnum_reqdtl_status in (3) and hivnum_packing_list_no is null";
			}
			if(Integer.parseInt(recordRequested)==3){
				query+=" and hivnum_reqdtl_status in (4)";
			}
			if(Integer.parseInt(recordRequested)==4){
				query+=" and hivnum_reqdtl_status in (6)";
			}
			if(Integer.parseInt(recordRequested)==5){
				query+=" and hivnum_reqdtl_status in (17)";
			}
			if(Integer.parseInt(recordRequested)==6){
				query+=" and hivnum_reqdtl_status in (7)";
			}
			if(Integer.parseInt(recordRequested)==7){
				query+=" and hivnum_reqdtl_status in (8)";
			}

			rs = HelperMethodsDAO.executeQuery(conn, query);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while (rs.next()) {
					invStatusDashboardVO dashVO = new invStatusDashboardVO();
					HelperMethods.populateVOfrmRS(dashVO, rs);
					sampleList.add(dashVO);
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
		
		return sampleList;
	}


}
