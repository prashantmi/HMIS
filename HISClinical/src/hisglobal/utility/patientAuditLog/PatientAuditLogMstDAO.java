package hisglobal.utility.patientAuditLog;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientAuditLogMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class PatientAuditLogMstDAO extends DataAccessObject
{
	public  PatientAuditLogMstDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public  List <PatientAuditLogMstVO> selectPatientAuditLog(UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
        String queryKey="SELECT.HPMRT_PATIENT_AUDITLOG_MST";
        ResultSet rs;
        PatientAuditLogMstVO patientAuditLogVO=null;
        List <PatientAuditLogMstVO> patientAuditLogVOList=null;
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
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        //	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatientAuditLogMstDAO.populateMAP::"+e);
        }
        try
        {
        	
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(rs.next()){
        		rs.beforeFirst();
        		patientAuditLogVOList=new ArrayList<PatientAuditLogMstVO>();
        		while(rs.next()){
        			patientAuditLogVO=new PatientAuditLogMstVO();
        			HelperMethods.populateVOfrmRS(patientAuditLogVO, rs);
        			patientAuditLogVOList.add(patientAuditLogVO);
        		}
        	}
        	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }

        return patientAuditLogVOList;
	}
	
	
	public  Map selectPatientCurrentRecord(PatientAuditLogMstVO patientAuditLogVO,String patCrNo,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="";
		ResultSet rs;
		List columnList=new ArrayList();
		List rowList=new ArrayList();
		List columnNameList=new ArrayList();
		Map map=new HashMap();
		try
		{
			query =patientAuditLogVO.getCurrentRecordQuery();
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		try
		{
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), patCrNo);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PatientAuditLogMstDAO.populateMAP::"+e);
		}
		try
		{
			
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			ResultSetMetaData metaData=rs.getMetaData();
			for(int i=1;i<=metaData.getColumnCount();i++)
				if(patientAuditLogVO.getDisplayLogic().equals(Config.AUDIT_LOG_DISPLAY_LOGIC_NEEDED)){
					if(i==1 || i==2){
						continue;
					}
					else{
						columnNameList.add(metaData.getColumnLabel(i).replace("_"," "));
					}
				}else{
					columnNameList.add(metaData.getColumnLabel(i).replace("_"," "));
				}
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					columnList=new ArrayList();
					for(int j=1;j<=metaData.getColumnCount();j++){
						/*if(patientAuditLogVO.getDisplayLogic().equals(Config.AUDIT_LOG_DISPLAY_LOGIC_NEEDED)){
							if(j==1 || j==2){
								continue;
							}
							else{
								columnList.add(rs.getString(j));
							}
						}
						else{*/
							columnList.add(rs.getString(j));
						//}	
					}
					rowList.add(columnList);
				}
			}
			
			map.put(Config.AUDIT_LOG_COLUMN_NAME_LIST, columnNameList);
			map.put(Config.AUDIT_LOG_CURRENT_RECORD_ROW_LIST, rowList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
		
		return map;
	}
	
	
	public  List selectPatientAuditLogDetail(PatientAuditLogMstVO patientAuditLogVO,String patCrNo,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="";
		ResultSet rs;
		List columnList=new ArrayList();
		List rowList=new ArrayList();
		List columnNameList=new ArrayList();
		try
		{
			if(patientAuditLogVO.getIsDateWise().equals("1")){
				query =patientAuditLogVO.getAuditLogDateQuery();
			}
			else
				query =patientAuditLogVO.getAuditLogQuery();
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		try
		{
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), patCrNo);
			if(patientAuditLogVO.getIsDateWise().equals("1")){
				populateMAP.put(sq.next(), patientAuditLogVO.getFromDate());
				populateMAP.put(sq.next(), patientAuditLogVO.getToDate());
			}
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PatientAuditLogMstDAO.populateMAP::"+e);
		}
		try
		{
			
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			ResultSetMetaData metaData=rs.getMetaData();
			//for(int i=0;i<metaData.getColumnCount();i++)
				//columnNameList.add(metaData.getColumnLabel(i));
			if(rs.next()){
				rs.beforeFirst();
				System.out.println(rs.getFetchSize());
				System.out.println(metaData.getColumnCount());
				while(rs.next()){
					columnList=new ArrayList();
					for(int j=1;j<=metaData.getColumnCount();j++){
						/*if(patientAuditLogVO.getDisplayLogic().equals(Config.AUDIT_LOG_DISPLAY_LOGIC_NEEDED)){
							columnList.add(rs.getString(j));
						}else{
							columnList.add(rs.getString(j));
						}*/
						columnList.add(rs.getString(j));
					}
					rowList.add(columnList);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
		
		return rowList;
	}
	
	
	public  List selectPatientBaseRecord(PatientAuditLogMstVO patientAuditLogVO,String patCrNo,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="";
		ResultSet rs;
		List columnList=new ArrayList();
		List rowList=new ArrayList();
		try
		{
			query =patientAuditLogVO.getBaseRecordQuery();
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		try
		{
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), patCrNo);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("PatientAuditLogMstDAO.populateMAP::"+e);
		}
		try
		{
			
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			ResultSetMetaData metaData=rs.getMetaData();
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					columnList=new ArrayList();
					for(int j=1;j<=metaData.getColumnCount();j++){
						columnList.add(rs.getString(j));
					}
					rowList.add(columnList);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
		
		return rowList;
	}
	
	
}
