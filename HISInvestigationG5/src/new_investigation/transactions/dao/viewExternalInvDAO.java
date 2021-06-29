package new_investigation.transactions.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import java.sql.Types;

import new_investigation.vo.viewExternalInvVO;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class viewExternalInvDAO extends DataAccessObject {

	public viewExternalInvDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	
	
	public List<viewExternalInvVO> showPatDetails(viewExternalInvVO vo,UserVO _UserVO,String reqNos)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List<viewExternalInvVO> patlst=new ArrayList<viewExternalInvVO>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		//String queryKey="SELECT_LIST_DATA_SHOW_FOR_EXTERNAL_PROCESS_HIVT_REQUISiTION_DTL";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<viewExternalInvVO> lstInvResultEntryVO = new ArrayList<viewExternalInvVO>();
		List<String> reqDnoList = new ArrayList<String>(); 
		String reqDno = "";
		//String queryReqNo = "select string_agg(hivnum_req_no,', ') as reqNoList from hivt_requisition_header where gnum_hospital_code = "+ _UserVO.getHospitalCode() + "hrgnum_puk = "+vo.getPatCrNo();
		//String queryReqDno = "select hivtnum_req_dno as reqDno from hivt_requisition_dtl where gnum_hospital_code = " + _UserVO.getHospitalCode() + "and hivnum_reqdtl_status > 6 and hivnum_req_no in ("+reqNos+")";
		String queryNew = "select distinct a.hivtnum_req_dno as requisitionDno,  (select(select INITCAP(GSTR_TEST_NAME) from hivt_test_mst where gnum_isvalid=1 and gnum_test_code=r.gnum_test_code) from hivt_requisition_dtl r where hivtnum_req_dno = a.hivtnum_req_dno)as testName , pkg_inv_fun.fun_puk_fromreqno((select hivnum_req_no from hivt_requisition_dtl where hivtnum_req_dno = a.hivtnum_req_dno),a.gnum_hospital_code) AS patCRNo, pkg_inv_fun.fun_patName_fromreqno((select hivnum_req_no from hivt_requisition_dtl where hivtnum_req_dno = a.hivtnum_req_dno),a.gnum_hospital_code) AS patName,(select gstr_lab_name from hivt_laboratory_mst where gnum_lab_code = (select gnum_lab_code from hivt_requisition_dtl where hivtnum_req_dno = a.hivtnum_req_dno) and gnum_isvalid=1 and gnum_hospital_code = a.gnum_hospital_code) as labName, string_agg(a.hivt_upload_filename, '#@#') as fileName, string_agg(a.hivt_upload_filedata, '#@#') as fileUploadData, (select hivdt_requisition_date from hivt_requisition_header where hivnum_req_no = (select hivnum_req_no from hivt_requisition_dtl where hivtnum_req_dno = a.hivtnum_req_dno)) as reqDate from hivt_parameter_dtl a where a.hivt_upload_filedata is not null and a.hivt_upload_filename is not null and a.hivtnum_req_dno in (select hivtnum_req_dno as reqDno from hivt_requisition_dtl where gnum_hospital_code = a.gnum_hospital_code and hivnum_reqdtl_status > 6 and hivnum_req_no in (select hivnum_req_no from hivt_requisition_header where hrgnum_puk = ?)) group by requisitionDno, a.gnum_hospital_code";
		/*try
		{
			rs =  HelperMethodsDAO.executeQuery(conn, queryReqNo);
			while(rs.next()){
				reqNos = rs.getString("reqNoList");
			}
			
			
			rs =  HelperMethodsDAO.executeQuery(conn, queryReqDno);
			while(rs.next()){
				reqDno = rs.getString("reqDno");
				reqDnoList.add(reqDno);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		*/
		
	//	String whereClause=" AND hivnum_reqdtl_status >6 and hivnum_req_no in ("+reqNos+")";
		//String orderBy="order by hivdt_coll_date_time ";
	  
		//populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(),_UserVO.getHospitalCode());
		//populateMap.put(sq.next(),reqNos);
		
	
	/*	try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		*/
		//query+=whereClause;
		//query+=orderBy;
		System.out.println(queryNew);		
		try
		{
			populateMap.put(sq.next(), vo.getPatCrNo());
			
			rs =  HelperMethodsDAO.executeQuery(conn, queryNew, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(viewExternalInvVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					viewExternalInvVO exVO = new viewExternalInvVO();
					exVO = (viewExternalInvVO)valueObjects[i];
					lstInvResultEntryVO.add(exVO);
					patlst.add(lstInvResultEntryVO.get(i)); //add all other values
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
		return patlst;
	}
	
	
	
	
}
