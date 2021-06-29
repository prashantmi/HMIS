package new_investigation.reports.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import new_investigation.vo.MachineTestReportNewVO;
import hisglobal.vo.UserVO;
import new_investigation.InvestigationConfig;

public class MachineTestReportNewDAO extends DataAccessObject 
{

	public MachineTestReportNewDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}


	
	public List<MachineTestReportNewVO> AjaxGetLabList(MachineTestReportNewVO vo, UserVO userVO) {
		
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
		String queryKey="SELECT.LAB_COMBO_NEW.HIVT_LABORATORY_MST";
		String query="";
		
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();
		
		Map populateMap= new HashMap();
		Sequence sq= new Sequence();
		List<MachineTestReportNewVO> listMachineTestReportNewVO=new ArrayList<MachineTestReportNewVO>();
		
		try 
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		try
		{
			populateMap.put(sq.next(), userVO.getHospitalCode());
			populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMap.put(sq.next(),userVO.getUserSeatId());
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
			rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if(!rs.next()) { }
			else {
				rs.beforeFirst();
				while(rs.next()) {
					MachineTestReportNewVO vo2 = new MachineTestReportNewVO();
					HelperMethods.populateVOfrmRS(vo2, rs);
					
					listMachineTestReportNewVO.add(vo2);
				}
			}
			
		}
		catch (Exception e)
		{		 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }
		
		return listMachineTestReportNewVO;
	}


	
public List<MachineTestReportNewVO> AjaxGetMachineList(MachineTestReportNewVO vo, UserVO userVO) {
	
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
	String queryKey="SELECT.LABTEST_MACHINE_NEW.HIVT_LABTEST_MST.MACHINE";
	String query="";
	
	ResultSet rs=null;
	Connection conn=super.getTransactionContext().getConnection();
	
	Map populateMap= new HashMap();
	Sequence sq= new Sequence();
	List<MachineTestReportNewVO> listMachineTestReportNewVO=new ArrayList<MachineTestReportNewVO>();
	
	try 
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	
	try
	{
		populateMap.put(sq.next(), userVO.getHospitalCode());
	    populateMap.put(sq.next(), vo.getLabCode());
		
		rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);
		
		if(!rs.next()) { }
		else {
			rs.beforeFirst();
			while(rs.next()) {
				MachineTestReportNewVO vo2 = new MachineTestReportNewVO();
				HelperMethods.populateVOfrmRS(vo2, rs);
				
				listMachineTestReportNewVO.add(vo2);
			}
		}
		
	}
	catch (Exception e)
	{		 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	
	return listMachineTestReportNewVO;
}





public List<MachineTestReportNewVO> AjaxGetMachineTestReportList(MachineTestReportNewVO vo, UserVO userVO) {
	
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
	
	String queryKey0="SELECT.MACHINE_LABTEST_LIST.HIVT_LABTEST_ALLUSER_MST.MACHINE";
	String queryKey1="SELECT.MACHINE_LABTEST_LIST.HIVT_LABTEST_USERWISE_MST.MACHINE";
	
	String query="";
	
	ResultSet rs=null;
	Connection conn=super.getTransactionContext().getConnection();
	
	Map populateMap= new HashMap();
	Sequence sq= new Sequence();
	List<MachineTestReportNewVO> listMachineTestReportNewVO=new ArrayList<MachineTestReportNewVO>();
	
	try 
	{	if(vo.getAcceptedByUser().equals("0"))
		query = HelperMethodsDAO.getQuery(filename, queryKey0);
	
		if(vo.getAcceptedByUser().equals("1"))
		query = HelperMethodsDAO.getQuery(filename, queryKey1);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	
	try
	{
		populateMap.put(sq.next(), vo.getFromDate());
		populateMap.put(sq.next(), vo.getToDate());
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
		if(vo.getAcceptedByUser().equals("1"))
		populateMap.put(sq.next(),userVO.getSeatId());
		
		populateMap.put(sq.next(), vo.getLabCode());
		populateMap.put(sq.next(), vo.getMachineId());
		
		populateMap.put(sq.next(), vo.getFromDate());
		populateMap.put(sq.next(), vo.getToDate());
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
		if(vo.getAcceptedByUser().equals("1"))
		populateMap.put(sq.next(),userVO.getSeatId());
		
		populateMap.put(sq.next(), vo.getLabCode());
		populateMap.put(sq.next(), vo.getMachineId());
		

		rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);
		
		if(!rs.next()) { }
		else {
			rs.beforeFirst();
			while(rs.next()) {
				MachineTestReportNewVO vo2 = new MachineTestReportNewVO();
				HelperMethods.populateVOfrmRS(vo2, rs);
				
				listMachineTestReportNewVO.add(vo2);
			}
		}
		
	}
	catch (Exception e)
	{		 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	
	return listMachineTestReportNewVO;
}

}
