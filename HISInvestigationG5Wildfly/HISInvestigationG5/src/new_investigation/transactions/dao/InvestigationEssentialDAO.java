package new_investigation.transactions.dao;

import hisglobal.backutil.HisDAO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import java.util.Iterator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.codehaus.jettison.json.JSONObject;

import new_investigation.InvestigationConfig;
import new_investigation.vo.BookMarkVO;
import new_investigation.vo.InvTrackingReportVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationRequistionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.RequistionHeaderVO;
import new_investigation.vo.invReportInProcessVO;
import new_investigation.vo.template.ResultEntryVO;



public class InvestigationEssentialDAO extends DataAccessObject implements InvestigationEssentialDAOi
{


	public InvestigationEssentialDAO(JDBCTransactionContext _tx) { super(_tx); }



	public Inv_RequisitionRaisingPatientVO getInvRaisingPatDetails(Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO,String deskType) {
		Inv_RequisitionRaisingPatientVO invReqRaisingVO;
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "";
		Sequence sq = new Sequence();



		if(deskType!=null && !deskType.equals("") && deskType.equals("1"))
		{
			queryKey=	"SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL_DAILY";
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), patVO.getPatVisitNo());
			populateMAP.put(sq.next(), patVO.getPatepisodecode()) ;

			populateMAP.put(sq.next(), patVO.getPatCRNo());
		}
		else
		{
			queryKey=	"SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL";
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), patVO.getPatCRNo());
		}



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);
			rs.beforeFirst();
			invReqRaisingVO = new Inv_RequisitionRaisingPatientVO();
			if (rs.next())
			{

				HelperMethods.populateVOfrmRS(invReqRaisingVO, rs);
			}
		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return invReqRaisingVO;
	}

	public List<LabTestVO> searchLabWiseTestDtls(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";


		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";


		Sequence sq = new Sequence();




		String condition1 = " and a.GNUM_LABCODE =" + searchVO.getSearchLabName();
		String condition2 = "";
		if (searchVO.getSearchTestName() != null && searchVO.getSearchTestName().contains("#")) {

			condition2 = " and a.GNUM_TEST_CODE =" + searchVO.getSearchTestName().split("#")[0];
		}
		else {

			condition2 = " and a.GNUM_TEST_CODE =" + searchVO.getSearchTestName();
		} 

		String condition3 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code and gnum_is_valid=1)";

		String condition4 = " and a.gnum_test_code not in (" + searchVO.getTestCode() + ")";
		String orderBy = " ORDER BY labname, testname";
		String condition5 = " and a.gnum_test_code  in (" + searchVO.getTestCodesSearchKeyword() + ")";


		String deskcalllabhide = "";

		if (searchVO.getPatAdmNo() == null || !searchVO.getPatAdmNo().equals("-1"))
		{
			//  deskcalllabhide = " and a.gnum_labcode not in (select nvl(n.gnum_lab_code,'0') from hivt_laboratory_mst n where n.gnum_lab_code=a.gnum_labcode and n.gnum_isvalid=1 and n.gnum_hospital_code=" + _UserVO.getHospitalCode() + "  and n.gnum_islabhidefromdesk=1)";
			deskcalllabhide = "and gnum_islabhidefromdesk_reqd=0 ";
		}




		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//  populateMAP.put(sq.next(), "15");
		// populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		//  populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			if (searchVO.getSearchLabName() != null && !searchVO.getSearchLabName().equals(""))
				query = String.valueOf(query) + condition1; 
			if (searchVO.getTestSearchKeyword().equals("") && searchVO.getSearchTestName() != null && !searchVO.getSearchTestName().equals(""))
				query = String.valueOf(query) + condition2; 
			if (!searchVO.getTestSearchKeyword().equals("")) {
				query = String.valueOf(query) + condition5;
			}
			if (searchVO.getSearchTestGroupName() != null && !searchVO.getSearchTestGroupName().equals("")) {
				query = String.valueOf(query) + condition3;
			}

			if (searchVO.getIsAddendum() != null)
			{
				if (searchVO.getIsAddendum() != null || searchVO.getIsAddendum().equals("1")) {
					query = String.valueOf(query) + condition4;
				}
			}
			if (searchVO.getPatAdmNo() == null || !searchVO.getPatAdmNo().equals("-1"))
			{
				query = String.valueOf(query) + deskcalllabhide;
			}

			query = String.valueOf(query) + orderBy;




			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public List<LabTestVO> searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";

		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";

		Sequence sq = new Sequence();

		String condition1 = "and a.gnum_test_code not in (" + searchVO.getTestCode() + ")";
		String condition2 = " ORDER BY  TestGroupCode,labCode,gnum_test_seq_no";







		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			if (searchVO.getIsAddendum() != null)
			{
				if (searchVO.getIsAddendum() != null || searchVO.getIsAddendum().equals("1"))
					query = String.valueOf(query) + condition1; 
			}
			query = String.valueOf(query) + condition2;

			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}




	public List<LabTestVO> searchBookMark(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_PRA";
		Sequence sq = new Sequence();



		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_PRA_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_PRA";

		String condition1 = "and EXISTS (SELECT 1 FROM HIVT_BOOKMARK_MST where ";
		String condition3 = "gnum_isvalid=1 and gnum_bookmark_code =" + searchVO.getBookMarkCode() + " AND";

		String condition2 = "  hivtnum_lab_code=a.gnum_labcode AND hivtnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code)";
		String condition4 = "and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GNUM_TEST_CODE) LIKE '%'||UPPER(TRIM('" + searchVO.getSearchTestName() + "'))||'%')";
		String condition5 = "   AND EXISTS (SELECT 1 FROM hivt_test_mst WHERE gnum_test_code = a.gnum_test_code AND  gnum_test_code in(" + searchVO.getTestCode() + "0)) and exists (select 1 from hivt_laboratory_mst where gnum_lab_code=a.gnum_labcode and   gnum_lab_code in(" + searchVO.getLabCode() + "0) ) ";

		String orderBy = " ORDER BY labname, testname";



		populateMAP.put(sq.next(), searchVO.getBookMarkCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getUserId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		// populateMAP.put(sq.next(), "15");
		// populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			if (searchVO.getBookMarkCode() != null && !searchVO.getBookMarkCode().equals(""))
			{
				query = String.valueOf(query) + condition1 + condition3 + condition2 + orderBy;
			}

			if (searchVO.getSearchTestName() != null && !searchVO.getSearchTestName().equals(""))
			{
				query = String.valueOf(query) + condition4 + orderBy;
			}

			if (searchVO.getTestCode() != null && !searchVO.getTestCode().equals(""))
			{
				query = String.valueOf(query) + condition5 + orderBy;
			}

			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		List<LabTestVO> lstLabVOnew=null;

		if(lstLabVO!=null && lstLabVO.size()>0)
		{
			Map<String,List<LabTestVO> > mpp=new HashMap<String,List<LabTestVO>>();

			lstLabVOnew=new ArrayList<LabTestVO>();

			for(int k=0;k<lstLabVO.size();k++)
			{
				LabTestVO vo=lstLabVO.get(k);
				String testgroupcode=vo.getTestGroupCode();

				boolean flg=false;

				if(testgroupcode!=null && !testgroupcode.equals("") && !testgroupcode.equals("-1"))
				{
					String flgg=getgroupcodeishideornot(testgroupcode);

					if(flgg!=null && !flgg.equals("") && flgg.equals("0"))
						flg=true;
				}

				if(mpp.containsKey(testgroupcode) )
				{
					if(flg==false)
					{
						List<LabTestVO> volist=mpp.get(testgroupcode);
						volist.add(vo);

						mpp.put(testgroupcode, volist);

					}

				}
				else 
				{
					if(flg==false)
					{
						List<LabTestVO> volist=new ArrayList<LabTestVO>();
						volist.add(vo);

						mpp.put(testgroupcode, volist);
					}

				}
			}


			if(mpp!=null && mpp.size()>0)
			{

				Iterator itrTest=mpp.keySet().iterator();

				while(itrTest.hasNext())//for(int j=0;j<sizeTest;j++)
				{

					String groupcode=(String)itrTest.next();
					List<LabTestVO> volist=mpp.get(groupcode);

					for(int l=0;l<volist.size();l++)
					{
						lstLabVOnew.add(volist.get(l));
					}

				}
			}


		}

		return lstLabVOnew;
	}




	public List<LabTestVO> searchTestGroup(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";

		if(searchVO.getRaisethrough()!=null  && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";

		Sequence sq = new Sequence();

		String condition1 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_lab_code=" + searchVO.getSearchLabName() + " AND gnum_hospital_Code=a.gnum_hospital_Code and gnum_is_valid=1)";
		String condition2 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_hospital_Code=a.gnum_hospital_Code and gnum_is_valid=1)";

		String orderBy = " ORDER BY labname, testname";



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//  populateMAP.put(sq.next(), "15");
		//   populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		//   populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			if (searchVO.getSearchTestGroupName() != null && !searchVO.getSearchTestGroupName().equals("") && searchVO.getSearchLabName() != null && !searchVO.getSearchLabName().equals(""))
				query = String.valueOf(query) + condition1; 
			if (searchVO.getSearchTestGroupName() != null && !searchVO.getSearchTestGroupName().equals(""))
				query = String.valueOf(query) + condition2; 
			query = String.valueOf(query) + orderBy;

			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}



	public List<LabTestVO> getTestsUsingGroup(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";
		Sequence sq = new Sequence();

		String condition1 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code and gnum_is_valid=1)";

		String orderBy = " ORDER BY labname, testname";



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		// populateMAP.put(sq.next(), "15");
		//  populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		//  populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			query = String.valueOf(query) + condition1 + orderBy;
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}




	public List getSampleCombo(String labCode, String testCode, UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List lstSample = new ArrayList();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LABTESTSAMPLECOMBO.HIVT_LABTEST_SAMPLE_MST";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), labCode);
		populateMap.put(sq.next(), testCode);
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				lstSample = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstSample;
	}




























































	public void insertRequisitionSequenceInMaintainer(String labCode, String sequence, String yymmdd, UserVO _userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "INSERT.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
							e);
		} 


		try {
			populateMAP.put(sq.next(), labCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), sequence);
			populateMAP.put(sq.next(), yymmdd);


		}
		catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
		} 
	}



	public void updateRequisitionSequenceInMaintainer(String sequence, String labcCode, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "UPDATE.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			populateMAP.put(sq.next(), sequence);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labcCode);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
	}






	public String insertRequisitionHeaderDtl(RequistionHeaderVO voReqHeader, UserVO _userVO) {
		String error = "";



		try {
			Procedure strProc = new Procedure("pkg_inv_dml.proc_insert_hivt_reqisition_header");
			strProc.addInParameter(1, 12, "3");


			strProc.addInParameter(2, 12, voReqHeader.getRequisitionNumber());
			strProc.addInParameter(3, 12, voReqHeader.getPatCrNo());
			strProc.addInParameter(4, 12, _userVO.getHospitalCode());
			strProc.addInParameter(5, 12, voReqHeader.getVisitNo());
			strProc.addInParameter(6, 12, voReqHeader.getLabCode());
			strProc.addInParameter(7, 12, voReqHeader.getReqHeaderStatus());
			strProc.addInParameter(8, 12, voReqHeader.getReqHeaderStatus());
			strProc.addInParameter(9, 12, _userVO.getSeatId());
			strProc.addInParameter(10, 12, voReqHeader.getEpisodeCode());
			strProc.addInParameter(11, 12, voReqHeader.getPatName());
			strProc.addInParameter(12, 12, voReqHeader.getReqType());
			strProc.addInParameter(13, 12, voReqHeader.getPatDob());
			strProc.addInParameter(14, 12, voReqHeader.getVisitDate());
			strProc.addInParameter(15, 12, voReqHeader.getIsActualDob());
			strProc.addInParameter(16, 12, voReqHeader.getAdvisedByDocNo());
			strProc.addInParameter(17, 12, voReqHeader.getGenderCode());
			strProc.addInParameter(18, 12, voReqHeader.getRequsitionRaisedThrough());
			strProc.addInParameter(19, 12, voReqHeader.getPatAge());
			strProc.addInParameter(20, 12, voReqHeader.getPatAdmissionNo());
			strProc.addInParameter(21, 12, voReqHeader.getPatAddress());
			// strProc.addInParameter(21, 12, "Cardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/Cardioaa");

			strProc.addInParameter(22, 12, voReqHeader.getWardCode());
			strProc.addInParameter(23, 12, voReqHeader.getMobileNo());
			strProc.addInParameter(24, 12, voReqHeader.getEmailId());
			strProc.addInParameter(25, 12, voReqHeader.getRoomCode());
			strProc.addInParameter(26, 12, voReqHeader.getBedCode());
			strProc.addInParameter(27, 12, voReqHeader.getMlcNo());
			strProc.addInParameter(28, 12, voReqHeader.getBedName());
			strProc.addInParameter(29, 12, voReqHeader.getRoomName());
			strProc.addInParameter(30, 12, voReqHeader.getWardName());
			strProc.addInParameter(31, 12, voReqHeader.getDeptName());
			strProc.addInParameter(32, 12, null);
			strProc.addInParameter(33, 12, voReqHeader.getDeptUnitName());
			strProc.addInParameter(34, 12, voReqHeader.getDeptCode());
			strProc.addInParameter(35, 12, voReqHeader.getDeptUnitCode());
			strProc.addInParameter(36, 12, voReqHeader.getIsAutomatedRequest());
			strProc.addInParameter(37, 12, voReqHeader.getAdvisedByDocName());
			strProc.addInParameter(38, 12, voReqHeader.getRefHospitalCode());
			strProc.addInParameter(39, 12, "");
			strProc.addInParameter(40, 12, voReqHeader.getRefLabCode());
			strProc.addInParameter(41, 12, voReqHeader.getHospOrLabName());
			strProc.addInParameter(42, 12, voReqHeader.getExtCrNo());
			strProc.addInParameter(43, 12, voReqHeader.getBillNo());
			strProc.addInParameter(44, 12, voReqHeader.getDeleteReason());
			strProc.addInParameter(45, 12, "1");

			strProc.addInParameter(46, 12, null);
			strProc.addInParameter(47, 12, voReqHeader.getPatCatCode());
			strProc.addInParameter(48, 12, voReqHeader.getVisitReason());

			strProc.addOutParameter(49, 12);
			strProc.execute(getTransactionContext().getConnection());
			error = (String)strProc.getParameterAt(49);
			System.out.println(" error" + error);
			String str = error;

		}
		catch (HisDataAccessException e) {

			throw new HisDataAccessException();
		} 

		return error;
	}






























































































	public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO, String priorityAll, RequistionHeaderVO voReqHeader) {
		String sampleNo = "";
		String errorMsg = "";
		ResultSet rs = null;


		try {
			if (voLabTest.getStrTestGroupCode() == null)
			{
				voLabTest.setStrTestGroupType(null);
			}

			if (voLabTest.getIslabappointmentbased() != null && voLabTest.getIslabappointmentbased().equals("1")) {

				voLabTest.setStrIsAppointment(voLabTest.getIslabappointmentbased());

			}
			else if (voLabTest.getIsAppointment() != null && voLabTest.getIsAppointment().equals("1")) {

				voLabTest.setStrIsAppointment("2");
			} 






			System.out.println("hello site 1");
			if (voLabTest.getSite() == null || voLabTest.getSite().equals("") || voLabTest.getSite().equals("null")) {
				System.out.println("hello site");
				voLabTest.setSite(null);
			} 


			Procedure strProc = new Procedure("pkg_inv_dml.proc_insert_hivt_requisition_dtl_new");

			strProc.addInParameter(1, 12, "3");
			strProc.addInParameter(2, 12, _userVO.getHospitalCode());
			strProc.addInParameter(3, 12, voLabTest.getStrRequsitionDNo());
			strProc.addInParameter(4, 12, (voLabTest.getStrIsAppointment() == null) ? "0" : voLabTest.getStrIsAppointment());
			strProc.addInParameter(5, 12, voLabTest.getStrLabCode());
			strProc.addInParameter(6, 12, voLabTest.getStrTestCode().toString());
			strProc.addInParameter(7, 12, (voLabTest.getStrIsConfidential() == null) ? "0" : voLabTest.getStrIsConfidential().toString());
			strProc.addInParameter(8, 12, voLabTest.getStrPriority());
			strProc.addInParameter(9, 12, null);
			strProc.addInParameter(10, 12, voLabTest.getStrRequisitionDtlStatus());
			strProc.addInParameter(11, 12, _userVO.getSeatId());
			strProc.addInParameter(12, 12, voLabTest.getAppointmentRefNo());
			strProc.addInParameter(13, 12, null);
			strProc.addInParameter(14, 12, voLabTest.getStrTestGroupCode());
			strProc.addInParameter(15, 12, voLabTest.getStrTestGroupType());
			strProc.addInParameter(16, 12, null);
			strProc.addInParameter(17, 12, null);
			strProc.addInParameter(18, 12, null);
			strProc.addInParameter(19, 12, null);
			strProc.addInParameter(20, 12, voLabTest.getStrSampleCode());
			strProc.addInParameter(21, 12, null);
			strProc.addInParameter(22, 12, null);
			strProc.addInParameter(23, 12, null);
			strProc.addInParameter(24, 12, null);
			strProc.addInParameter(25, 12, null);
			strProc.addInParameter(26, 12, null);
			strProc.addInParameter(27, 12, voLabTest.getStrWorkOrderSequence());
			strProc.addInParameter(28, 12, null);
			strProc.addInParameter(29, 12, null);
			strProc.addInParameter(30, 12, null);
			strProc.addInParameter(31, 12, voLabTest.getStrTypeOfComponent());
			strProc.addInParameter(32, 12, voLabTest.getStrReqNo());
			strProc.addInParameter(33, 12, null);
			strProc.addInParameter(34, 12, null);
			strProc.addInParameter(35, 12, null);
			strProc.addInParameter(36, 12, null);
			strProc.addInParameter(37, 12, null);
			strProc.addInParameter(38, 12, null);
			strProc.addInParameter(39, 12, voLabTest.getStrAppointmentTime());
			strProc.addInParameter(40, 12, null);
			strProc.addInParameter(41, 12, null);
			strProc.addInParameter(42, 12, null);
			strProc.addInParameter(43, 12, null);
			strProc.addInParameter(44, 12, null);
			strProc.addInParameter(45, 12, null);
			strProc.addInParameter(46, 12, voLabTest.getStrAppointmentDate());
			strProc.addInParameter(47, 12, priorityAll);






			strProc.addInParameter(48, 12, (voLabTest.getSite() == null) ? null : voLabTest.getSite());


			strProc.addInParameter(49, 12, voReqHeader.getPatCrNo());
			strProc.addInParameter(50, 12, voReqHeader.getVisitNo());
			strProc.addInParameter(51, 12, null);
			strProc.addInParameter(52, 12, voReqHeader.getReqHeaderStatus());
			strProc.addInParameter(53, 12, voReqHeader.getEpisodeCode());
			strProc.addInParameter(54, 12, voReqHeader.getPatName());
			strProc.addInParameter(55, 12, voReqHeader.getReqType());
			strProc.addInParameter(56, 12, voReqHeader.getPatDob());
			strProc.addInParameter(57, 12, voReqHeader.getVisitDate());
			strProc.addInParameter(58, 12, voReqHeader.getIsActualDob());
			strProc.addInParameter(59, 12, voReqHeader.getAdvisedByDocNo());
			strProc.addInParameter(60, 12, voReqHeader.getGenderCode());
			strProc.addInParameter(61, 12, voReqHeader.getRequsitionRaisedThrough());
			strProc.addInParameter(62, 12, voReqHeader.getPatAge());
			strProc.addInParameter(63, 12, voReqHeader.getPatAdmissionNo());
			strProc.addInParameter(64, 12, voReqHeader.getPatAddress());
			//  strProc.addInParameter(64, 12, "Cardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/CardioCardiology/Cardioaa");
			strProc.addInParameter(65, 12, voReqHeader.getWardCode());
			strProc.addInParameter(66, 12, voReqHeader.getMobileNo());
			strProc.addInParameter(67, 12, voReqHeader.getEmailId());
			strProc.addInParameter(68, 12, voReqHeader.getRoomCode());
			strProc.addInParameter(69, 12, voReqHeader.getBedCode());
			strProc.addInParameter(70, 12, voReqHeader.getMlcNo());
			strProc.addInParameter(71, 12, voReqHeader.getBedName());
			strProc.addInParameter(72, 12, voReqHeader.getRoomName());
			strProc.addInParameter(73, 12, voReqHeader.getWardName());
			strProc.addInParameter(74, 12, voReqHeader.getDeptName());

			strProc.addInParameter(75, 12, "");
			strProc.addInParameter(76, 12, voReqHeader.getDeptUnitName());
			strProc.addInParameter(77, 12, "");
			strProc.addInParameter(78, 12, voReqHeader.getDeptCode());
			strProc.addInParameter(79, 12, voReqHeader.getDeptUnitCode());
			strProc.addInParameter(80, 12, voReqHeader.getIsAutomatedRequest());
			strProc.addInParameter(81, 12, voReqHeader.getAdvisedByDocName());
			strProc.addInParameter(82, 12, voReqHeader.getRefHospitalCode());
			strProc.addInParameter(83, 12, "");
			strProc.addInParameter(84, 12, voReqHeader.getRefLabCode());
			strProc.addInParameter(85, 12, voReqHeader.getHospOrLabName());
			strProc.addInParameter(86, 12, voReqHeader.getExtCrNo());
			strProc.addInParameter(87, 12, voReqHeader.getDeleteReason());
			strProc.addInParameter(88, 12, "1");
			strProc.addInParameter(89, 12, voReqHeader.getPatCatCode());
			strProc.addInParameter(90, 12, voReqHeader.getVisitReason());
			strProc.addInParameter(91, 12, "");
			strProc.addInParameter(92, 12, "");
			strProc.addInParameter(93, 12, "");
			strProc.addInParameter(94, 12, "");
			strProc.addInParameter(95, 12, "");

			strProc.addInOutParameter(96, 12, "");




			strProc.execute(getTransactionContext().getConnection());

		}
		catch (HisRecordNotFoundException e) {

			throw new HisRecordNotFoundException("No Record Found");
		} 
	}




















































































	public List<Inv_EpisodeVO> getPatientEpisode(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.PATIENT_EPISODE_DTLS.HRGT_EPISODE_DTL";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();

		try {
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			populateMAP.put(sq.next(), patVO.getPatCRNo());

		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 

		PatientDetailVO[] dailyPatientVOs = null;
		List<Inv_EpisodeVO> lstPatientDetailVO = new ArrayList<Inv_EpisodeVO>();
		ValueObject[] valueObjects = null;


		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{




				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(Inv_EpisodeVO.class, rs);

				for (int i = 0; i < valueObjects.length; i++)
				{

					lstPatientDetailVO.add((Inv_EpisodeVO)valueObjects[i]);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		} 
		return lstPatientDetailVO;
	}



	public List<Inv_PatientAdmissionDtlVO> getPatientAdmission(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.PATIENT_ADMISSION_DTLS.HIPT_PATADMISSION_DTL";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();


		try {
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			populateMAP.put(sq.next(), patVO.getPatCRNo());

		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 

		Inv_PatientAdmissionDtlVO[] dailyPatientVOs = null;
		List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionDetailVO = new ArrayList<Inv_PatientAdmissionDtlVO>();
		ValueObject[] valueObjects = null;


		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{




				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(Inv_PatientAdmissionDtlVO.class, rs);

				for (int i = 0; i < valueObjects.length; i++)
				{

					lstPatientAdmissionDetailVO.add((Inv_PatientAdmissionDtlVO)valueObjects[i]);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
		} 

		return lstPatientAdmissionDetailVO;
	}


	public List<BookMarkVO> getBookMarkList(UserVO _userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.BOOK_MARK_DTLS.HIVT_BOOKMARK_MST_RAISING";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();









		try {
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			populateMAP.put(sq.next(), _userVO.getUserId());


		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 

		BookMarkVO[] bookMarkVOs = null;
		List<BookMarkVO> lstBookMarkVO = new ArrayList<BookMarkVO>();
		ValueObject[] valueObjects = null;


		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{




				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(BookMarkVO.class, rs);

				for (int i = 0; i < valueObjects.length; i++)
				{

					lstBookMarkVO.add((BookMarkVO)valueObjects[i]);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		} 
		return lstBookMarkVO;
	}



	public List getLabNames(UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstLabNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_COMBO_NAMES.HIVT_LABORATORY_MST";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), "15");
		populateMap.put(sq.next(), _UserVO.getUserSeatId());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstLabNames = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstLabNames;
	}



	public List getTestNames(UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEST_COMBO_NAMES.HIVT_TEST_MST";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstTestNames = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstTestNames;
	}


	public List getTestNamesUsingAJAX(String labCode, UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEST_COMBO_NAMES_AJAX.HIVT_TEST_MST";

		String condition1 = "and gnum_labcode =" + labCode + " order by testName ";



		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			if (!labCode.equals("")) {
				query = String.valueOf(query) + condition1;
			}
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstTestNames = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstTestNames;
	}


	public List getTestGroupNames(UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEST_GROUP_NAMES.HIVT_TEST_GROUP_MST";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstTestNames = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstTestNames;
	}


	public List getTestGroupUsingAJAX(String labCode, UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEST_GROUP_NAMES_AJAX.HIVT_TEST_GROUP_MST";

		String condition1 = "AND gnum_lab_code=" + labCode + " ";
		String condition2 = ") ORDER BY testgroupname";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			if (!labCode.equals("")) {



				query = String.valueOf(query) + condition1 + condition2;

			}
			else {

				query = String.valueOf(query) + condition2;
			} 
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstTestNames = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstTestNames;
	}










	public void updateRequisitionHeader(String requisitionNumber, String appoitmentDate, UserVO _userVO) {
		try {
			Procedure strProc = new Procedure("pkg_inv_dml.proc_insert_hivt_reqisition_header");
			strProc.addInParameter(1, 12, "2");
			strProc.addInParameter(2, 12, requisitionNumber);
			strProc.addInParameter(3, 12, null);
			strProc.addInParameter(4, 12, null);
			strProc.addInParameter(5, 12, null);
			strProc.addInParameter(6, 12, null);
			strProc.addInParameter(7, 12, null);
			strProc.addInParameter(8, 12, null);
			strProc.addInParameter(9, 12, null);
			strProc.addInParameter(10, 12, null);
			strProc.addInParameter(11, 12, null);
			strProc.addInParameter(12, 12, null);
			strProc.addInParameter(13, 12, null);
			strProc.addInParameter(14, 12, null);
			strProc.addInParameter(15, 12, null);
			strProc.addInParameter(16, 12, null);
			strProc.addInParameter(17, 12, null);
			strProc.addInParameter(18, 12, null);
			strProc.addInParameter(19, 12, null);
			strProc.addInParameter(20, 12, null);
			strProc.addInParameter(21, 12, null);
			strProc.addInParameter(22, 12, null);
			strProc.addInParameter(23, 12, null);
			strProc.addInParameter(24, 12, null);
			strProc.addInParameter(25, 12, null);
			strProc.addInParameter(26, 12, null);
			strProc.addInParameter(27, 12, null);
			strProc.addInParameter(28, 12, null);
			strProc.addInParameter(29, 12, null);
			strProc.addInParameter(30, 12, null);
			strProc.addInParameter(31, 12, null);
			strProc.addInParameter(32, 12, null);
			strProc.addInParameter(33, 12, null);
			strProc.addInParameter(34, 12, null);
			strProc.addInParameter(35, 12, null);
			strProc.addInParameter(36, 12, null);
			strProc.addInParameter(37, 12, null);
			strProc.addInParameter(38, 12, null);
			strProc.addInParameter(39, 12, null);
			strProc.addInParameter(40, 12, null);
			strProc.addInParameter(41, 12, null);
			strProc.addInParameter(42, 12, null);
			strProc.addInParameter(43, 12, null);
			strProc.addInParameter(44, 12, null);
			strProc.addInParameter(45, 12, null);
			strProc.addInParameter(46, 12, appoitmentDate);
			strProc.addInParameter(47, 12, null);
			strProc.addInParameter(48, 12, null);
			strProc.addInOutParameter(49, 12, "");
			strProc.execute(getTransactionContext().getConnection());
		}
		catch (HisDataAccessException e) {

			throw new HisDataAccessException();
		} 
	}















































	public List<LabTestVO> getPrvTestDtlUsingAJAX(String CrNo, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_PRV_TEST_DETAIL";
		Sequence sq = new Sequence();




		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), CrNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}





	public List<LabTestVO> getAptBasedTest(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.APT_BASED_DETAIL.HAPT_APPOINTMENT_DTL";
		Sequence sq = new Sequence();





		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);







			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public void insertHivtRequsitionTestMandatoryDtl(InvestigationRequistionVO voLabTest, UserVO _userVO) {
		try {
			Procedure strProc = new Procedure("pkg_inv_dml.proc_insert_hivt_requsition_test_mandatory_dtl");
			strProc.addInParameter(1, 12, "1");
			strProc.addInParameter(2, 12, voLabTest.getStrRequsitionDNo());
			strProc.addInParameter(3, 12, voLabTest.getStrTestCode());
			strProc.addInParameter(4, 12, voLabTest.getMandCode());
			strProc.addInParameter(5, 12, voLabTest.getFinalMandValues());
			strProc.addInParameter(6, 12, _userVO.getSeatId());
			strProc.addInParameter(7, 12, _userVO.getHospitalCode());
			strProc.addInParameter(8, 12, "1");
			strProc.addInOutParameter(9, 12, "");
			strProc.execute(getTransactionContext().getConnection());
		}
		catch (HisDataAccessException e) {

			throw new HisDataAccessException();
		} 
	}
































































	public String getAccountNo(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
		String accountNo = "";

		try {
			Procedure strProc = new Procedure("BILL_MST.GET_PAT_ACCOUNTNO");
			strProc.addInParameter(1, 12, userVO.getHospitalCode());
			strProc.addInParameter(2, 12, "2");
			strProc.addInParameter(3, 12, patVO.getPatCRNo());

			strProc.setReturnType(2);
			return accountNo = strProc.execute(getTransactionContext().getConnection()).toString();

		}
		catch (HisRecordNotFoundException e) {

			throw new HisRecordNotFoundException("No Record Found");
		} 
	}







	public List getAdvisedByNames(UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.ADVISEDBY_DOCTOR_NAMES.PIST_EMP_REGISTRATION_DTL";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), "1");
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstTestNames = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstTestNames;
	}


	public String generateRequisitionNoSequence(String labCode, UserVO userVO) {
		String reqNo = "";
		String errorMsg = "";
		ResultSet rs = null;

		try {
			Procedure strProc = new Procedure("pkg_inv_unique_no_generation.generate_save_requisitionno");
			strProc.addInParameter(1, 12, userVO.getHospitalCode());
			strProc.addInParameter(2, 12, labCode);
			strProc.setReturnType(12);
			return reqNo = (String)strProc.execute(getTransactionContext().getConnection());

		}
		catch (HisRecordNotFoundException e) {

			throw new HisRecordNotFoundException("No Record Found");
		} 
	}



	public String getBillingCheck1(UserVO userVO) {
		String query = "";
		String check = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;

		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.GETBILLING_CHECK";
		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), userVO.getUserSeatId());


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			check = rs.getString(1);
			System.out.println("check::" + check);



		}
		catch (HisRecordNotFoundException e) {


			return check;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return check;
	}







	public List<LabTestVO> getTestsCodeWiseDtl(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";
		Sequence sq = new Sequence();


		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";


		String condition1 = " AND UPPER(TRIM(gnum_user_test_code)) =UPPER(TRIM('" + searchVO.getTestCodeWise() + "'))";

		String orderBy = " ORDER BY labname, testname";



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//   populateMAP.put(sq.next(), "15");
		//  populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		//  populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			query = String.valueOf(query) + condition1 + orderBy;
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			return lstLabVO;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}





	public List getTestCodeNames(UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEST_CODE_WISE.HIVT_LABORATORY_TEST_MST";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstTestNames = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				return lstTestNames;
			}

			return lstTestNames;
		} 
		return lstTestNames;
	}


	public String checkBillDtl(InvestigationSearchVO searchVO, UserVO _UserVO, String reqType) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String record = null;
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.BILLINGSTATUS.HIVT_REQUISITION_DTL";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 











		try {
			populateMAP.put(sq.next(), reqType);


			populateMAP.put(sq.next(), searchVO.getRequisitionNo());
			populateMAP.put(sq.next(), searchVO.getDelTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			if (rs.next()) {
				record = rs.getString(1);
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 

		return record;
	}



	public void deleteReqDtl(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "UPDATE.REQUISITIONSTATUS.HIVT_REQUISITION_DTL";
		String queryKey1 = "DELETE_GROUP_WISE_RAISING";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

			if (searchVO.getGroupcode() != null && !searchVO.getGroupcode().equals("0"))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
			}
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			if (searchVO.getGroupcode() != null && !searchVO.getGroupcode().equals("0"))
			{
				populateMAP.put(sq.next(), "16");
				populateMAP.put(sq.next(), _UserVO.getSeatId());


				populateMAP.put(sq.next(), "2");
				populateMAP.put(sq.next(), "5");
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());

				populateMAP.put(sq.next(), searchVO.getGroupcode());
				populateMAP.put(sq.next(), searchVO.getRequisitionNo());

			}
			else
			{
				populateMAP.put(sq.next(), "16");
				populateMAP.put(sq.next(), _UserVO.getSeatId());


				populateMAP.put(sq.next(), "2");
				populateMAP.put(sq.next(), "5");
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getDelLabCode());
				populateMAP.put(sq.next(), searchVO.getDelTestCode());
				populateMAP.put(sq.next(), searchVO.getRequisitionNo());
			}

		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
	}


	public List getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstReqStatus = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEST.REQSTATUS.HIVT_REQUISITION_DTL";



		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), searchVO.getDupLabCode());
		populateMap.put(sq.next(), searchVO.getDupTestCode());

		populateMap.put(sq.next(), searchVO.getPatientCrNo());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());





		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 


		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					lstReqStatus = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstReqStatus;
	}






	public boolean checkRequisitionPending(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		int count = 0;
		boolean isTempReqPresent = false;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.REQUISITON.PENDING.DTL.HIVT_REQUISITION_DTL";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), searchVO.getDupLabCode());
		populateMap.put(sq.next(), searchVO.getDupTestCode());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		populateMap.put(sq.next(), searchVO.getPatientCrNo());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next()) {






				count = rs.getInt(1);
				if (count > 0) {
					isTempReqPresent = true;
				}
			} 
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return isTempReqPresent;
	}






	public InvestigationRequistionVO ConfirmAppointment(InvestigationRequistionVO searchVO, UserVO _userVO) {
		HisDAO daoObj = null;
		String strProcName = "{call pkg_appointment_Transaction.proc_confirm_appt_save(?::character varying,?::character varying,?::character varying,?::character varying,?)}";
		int nProcIndex = 0;
		int sq = 0;
		String strErr = "";











		System.out.println("AppointmentQueueNo=3");
		System.out.println("apt no=" + searchVO.getAppointmentNo());


		try {
			HelperMethods.setNullToEmpty(searchVO);
			daoObj = new HisDAO("Appointment", "AppointmentDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "appointmentno", searchVO.getAppointmentNo(), ++sq);
			daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(), ++sq);
			daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId(), ++sq);
			daoObj.setProcInValue(nProcIndex, "appointmentstatus", "3", ++sq);
			daoObj.setProcOutValue(nProcIndex, "err", 1, ++sq);
			daoObj.executeProcedureByPosition(nProcIndex);
			searchVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
		}
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e + strErr);
		}
		finally {

			if (daoObj != null) {

				daoObj.free();
				daoObj = null;
			} 
		} 
		return searchVO;
	}





	public List<LabTestVO> getAptByDesk(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.APT_BY_DESK.HIVT_REQUISITION_HEADER";
		Sequence sq = new Sequence();
		String cond1 = "and TRUNC(HIVDT_REQUISITION_DATE)>=TRUNC(TO_DATE(?,'dd-Mon-YYYY')) and TRUNC(HIVDT_REQUISITION_DATE)<=TRUNC(TO_DATE(?,'dd-Mon-YYYY'))";
		String cond2 = "and TRUNC(HIVDT_REQUISITION_DATE)>=TRUNC(TO_DATE(sysdate,'dd-Mon-YYYY')) and TRUNC(HIVDT_REQUISITION_DATE)<=TRUNC(TO_DATE(sysdate,'dd-Mon-YYYY'))";
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		populateMAP.put(sq.next(), "55");


		if (!searchVO.getFromDate().equals("")) {

			populateMAP.put(sq.next(), searchVO.getFromDate());
			populateMAP.put(sq.next(), searchVO.getToDate());
		} 

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		if (!searchVO.getFromDate().equals("")) {
			query = String.valueOf(query) + cond1;
		} else {
			query = String.valueOf(query) + cond2;
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);
			rs.beforeFirst();

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient List  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO invLabTestVO = null;
			while (rs.next()) {
				invLabTestVO = new LabTestVO();
				HelperMethods.populateVOfrmRS(invLabTestVO, rs);
				lstLabVO.add(invLabTestVO);
			}

		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}








	public List<LabTestVO> getAppointment(String reqNo, String crNo, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.GET_APPOINTMENT_DTL.HIVT_REQUISITION_DTLS";
		Sequence sq = new Sequence();











		populateMAP.put(sq.next(), "55");
		populateMAP.put(sq.next(), reqNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 













		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);
			rs.beforeFirst();

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient List  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO invLabTestVO = null;
			while (rs.next()) {
				invLabTestVO = new LabTestVO();
				HelperMethods.populateVOfrmRS(invLabTestVO, rs);
				invLabTestVO.setPatCrNo(crNo);
				invLabTestVO.setReqNo(reqNo);
				lstLabVO.add(invLabTestVO);
			}

		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}




	public void saveAppointmentDetail(InvestigationRequistionVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "UPDATE.SAVE_APPOINTMENT.HIVT_REQUISITION_DTL";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 






		try {
			populateMAP.put(sq.next(), searchVO.getStrAppointmentDate());
			populateMAP.put(sq.next(), searchVO.getStrAppointmentTime());
			populateMAP.put(sq.next(), searchVO.getAppointmentRefNo());

			if (searchVO.getStrSampleCode() == null) {
				populateMAP.put(sq.next(), "5");
			} else if (searchVO.getStrSampleCode().equals("-1")) {
				populateMAP.put(sq.next(), "5");
			} else {
				populateMAP.put(sq.next(), "2");
			} 






			populateMAP.put(sq.next(), searchVO.getStrRequsitionDNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
	}





	public List<LabTestVO> getAppointmentDetailsOnClickGO(LabTestVO voLabTest, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.GET_APPOINTMENT_DTL_ON_CLICK_GO.HIVT_REQUISITION_DTLS";
		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), "55");
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), voLabTest.getPatCrNo());


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);
			rs.beforeFirst();

			if (rs.next()) {





				rs.beforeFirst();
				lstLabVO = new ArrayList<LabTestVO>();
				LabTestVO invLabTestVO = null;
				while (rs.next()) {
					invLabTestVO = new LabTestVO();
					HelperMethods.populateVOfrmRS(invLabTestVO, rs);
					lstLabVO.add(invLabTestVO);
				}

			} 
		} catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public void tempFunction(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEMP";


		Sequence sq = new Sequence();




		String condition1 = " and a.GNUM_LABCODE =" + searchVO.getSearchLabName();
		String condition2 = " and a.GNUM_TEST_CODE =" + searchVO.getSearchTestName();
		String condition3 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code)";
		String orderBy = " ORDER BY labname, testname";



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), "15");




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);


















		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
	}




	public List<LabTestVO> searchLabTestSample(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_TEST_SAMPLE_WITHOUT_LAB_PERMISSION";


		Sequence sq = new Sequence();











		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//  populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//  populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		//   populateMAP.put(sq.next(), "15");




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 






		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}









	public void insertRaisingCumCollectionDetails(LabTestVO voLabTest, UserVO _userVO) {
		String sampleNo = "";
		String errorMsg = "";
		ResultSet rs = null;

		try {
			Procedure strProc = new Procedure("pkg_inv_dml.proc_insert_hivt_requisition_dtl");
			strProc.addInParameter(1, 12, "4");
			strProc.addInParameter(2, 12, _userVO.getHospitalCode());
			strProc.addInParameter(3, 12, voLabTest.getReqDno());
			strProc.addInParameter(4, 12, (voLabTest.getIsAppointment() == null) ? "0" : voLabTest.getIsAppointment());
			strProc.addInParameter(5, 12, voLabTest.getLabCode());
			strProc.addInParameter(6, 12, voLabTest.getTestCode());
			strProc.addInParameter(7, 12, (voLabTest.getIsConfidential() == null) ? "0" : voLabTest.getIsConfidential());
			strProc.addInParameter(8, 12, voLabTest.getPriority());
			strProc.addInParameter(9, 12, null);
			strProc.addInParameter(10, 12, voLabTest.getRequisitionDtlStatus());
			strProc.addInParameter(11, 12, _userVO.getSeatId());
			strProc.addInParameter(12, 12, voLabTest.getAppointmentRefNo());
			strProc.addInParameter(13, 12, voLabTest.getTempSampleNo());
			strProc.addInParameter(14, 12, (voLabTest.getStrTestGroupCode() == null) ? voLabTest.getTestGroupCode() : voLabTest.getStrTestGroupCode());
			strProc.addInParameter(15, 12, voLabTest.getTestGroupType());
			strProc.addInParameter(16, 12, null);
			strProc.addInParameter(17, 12, voLabTest.getBillNo());
			strProc.addInParameter(18, 12, null);
			strProc.addInParameter(19, 12, null);
			strProc.addInParameter(20, 12, voLabTest.getSampleCode());
			strProc.addInParameter(21, 12, null);
			strProc.addInParameter(22, 12, null);
			strProc.addInParameter(23, 12, null);
			strProc.addInParameter(24, 12, null);
			strProc.addInParameter(25, 12, null);
			strProc.addInParameter(26, 12, null);
			strProc.addInParameter(27, 12, voLabTest.getWorkingOrderSequence());
			strProc.addInParameter(28, 12, null);
			strProc.addInParameter(29, 12, null);
			strProc.addInParameter(30, 12, null);
			strProc.addInParameter(31, 12, voLabTest.getTypeOfComponent());
			strProc.addInParameter(32, 12, voLabTest.getReqNo());
			strProc.addInParameter(33, 12, "1");
			strProc.addInParameter(34, 12, null);
			strProc.addInParameter(35, 12, null);
			strProc.addInParameter(36, 12, _userVO.getSeatId());
			strProc.addInParameter(37, 12, _userVO.getSeatId());
			strProc.addInParameter(38, 12, voLabTest.getSampleAreaCode());
			strProc.addInParameter(39, 12, voLabTest.getAppointmentTime());
			strProc.addInParameter(40, 12, null);
			strProc.addInParameter(41, 12, "1");
			strProc.addInParameter(42, 12, voLabTest.getSampleNo());
			strProc.addInParameter(43, 12, voLabTest.getUomCode());
			strProc.addInParameter(44, 12, voLabTest.getContainerVolume());
			strProc.addInParameter(45, 12, voLabTest.getContainerCode());
			strProc.addInParameter(46, 12, voLabTest.getAppointmentDate());
			strProc.addInParameter(47, 12, null);

			strProc.addInOutParameter(48, 12, "");
			strProc.addInParameter(49, 12, null);

			strProc.execute(getTransactionContext().getConnection());

		}
		catch (HisRecordNotFoundException e) {

			throw new HisRecordNotFoundException("No Record Found");
		} 
	}







	public List<LabTestVO> searchLabTestSampleRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.RAISING_CUM_COLLECTION.LAB_TEST_SAMPLE";


		Sequence sq = new Sequence();











		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), "15");




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 






		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public List<LabTestVO> searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.RAISING_CUM_SELECTION.HIVT_TEST_MST";


		Sequence sq = new Sequence();




		String condition1 = " and a.GNUM_LABCODE =" + searchVO.getSearchLabName();
		String condition2 = " and a.GNUM_TEST_CODE =" + searchVO.getSearchTestName();
		String condition3 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code)";
		String orderBy = " ORDER BY labname, testname";



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			if (searchVO.getSearchLabName() != null && !searchVO.getSearchLabName().equals(""))
				query = String.valueOf(query) + condition1; 
			if (searchVO.getSearchTestName() != null && !searchVO.getSearchTestName().equals(""))
				query = String.valueOf(query) + condition2; 
			if (searchVO.getSearchTestGroupName() != null && !searchVO.getSearchTestGroupName().equals("")) {
				query = String.valueOf(query) + condition3;
			}


			query = String.valueOf(query) + orderBy;




			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}




	public List<LabTestVO> searchBookMarkRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.RAISING_CUM_SELECTION.HIVT_TEST_MST";
		Sequence sq = new Sequence();

		String condition1 = "and EXISTS (SELECT 1 FROM HIVT_BOOKMARK_MST where ";
		String condition3 = "gnum_isvalid=1 and gnum_bookmark_code =" + searchVO.getBookMarkCode() + " AND";
		String condition2 = "  hivtnum_lab_code=a.gnum_labcode AND hivtnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code  and gnum_user_id=" + _UserVO.getSeatId() + ")";
		String condition4 = "and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GNUM_TEST_CODE) LIKE '%'||UPPER(TRIM('" + searchVO.getSearchTestName() + "'))||'%')";
		String condition5 = "   AND EXISTS (SELECT 1 FROM hivt_test_mst WHERE gnum_test_code = a.gnum_test_code AND  gnum_test_code in(" + searchVO.getTestCode() + "0)) and exists (select 1 from hivt_laboratory_mst where gnum_lab_code=a.gnum_labcode and   gnum_lab_code in(" + searchVO.getLabCode() + "0) ) ";

		String orderBy = " ORDER BY labname, testname";


		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			if (searchVO.getBookMarkCode() != null && !searchVO.getBookMarkCode().equals(""))
			{
				query = String.valueOf(query) + condition1 + condition3 + condition2 + orderBy;
			}

			if (searchVO.getSearchTestName() != null && !searchVO.getSearchTestName().equals(""))
			{
				query = String.valueOf(query) + condition4 + orderBy;
			}

			if (searchVO.getTestCode() != null && !searchVO.getTestCode().equals(""))
			{
				query = String.valueOf(query) + condition5 + orderBy;
			}

			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public List<LabTestVO> searchLaboratoryWiseTestGroupOnClickRaisingCumCollection(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.RAISING_CUM_COLLECTION.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST";


		Sequence sq = new Sequence();








		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 




		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public List<LabTestVO> getAppointmentRaiseCumColl(String reqNo, String crNo, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.RAISE_CUM_COLL.GET_APPOINTMENT_DTL.HIVT_REQUISITION_DTLS";
		Sequence sq = new Sequence();











		populateMAP.put(sq.next(), "55");
		populateMAP.put(sq.next(), reqNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 













		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);
			rs.beforeFirst();

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient List  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO invLabTestVO = null;
			while (rs.next()) {
				invLabTestVO = new LabTestVO();
				HelperMethods.populateVOfrmRS(invLabTestVO, rs);
				invLabTestVO.setPatCrNo(crNo);
				invLabTestVO.setReqNo(reqNo);
				lstLabVO.add(invLabTestVO);
			}

		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}









	public List fetchUserGroupCode(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List userGroupCode = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.USER_GROUP_CODE.HIVT_TEST_GROUP_INFO_MST_WITHOUT_LAB_PERMISSION";



		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.USER_GROUP_CODE.HIVT_TEST_GROUP_INFO_MST_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.USER_GROUP_CODE.HIVT_TEST_GROUP_INFO_MST_WITHOUT_LAB_PERMISSION";



		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		// populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		// populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		// populateMAP.put(sq.next(), "15");




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 






		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			userGroupCode = HelperMethodsDAO.getAlOfEntryObjects(rs);




		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return userGroupCode;
	}





	public Map getTestAvailabilityDetail(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		Map<String, String> testAvailabilityDetail = new HashMap<String, String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.TEST_AVAILABILITY.HIVT_TEST_MST";

		/*
		 * Sequence sq = new Sequence();
		 * 
		 * 
		 * 
		 * populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * try { query = HelperMethodsDAO.getQuery(filename, queryKey);
		 * 
		 * } catch (Exception e) {
		 * 
		 * throw new
		 * HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
		 * + e); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * try { rs =
		 * HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query,
		 * populateMAP);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * String userTestCode = ""; String remarks = ""; while (rs.next()) {
		 * userTestCode = rs.getString(1); remarks = rs.getString(2);
		 * 
		 * testAvailabilityDetail.put(userTestCode.toUpperCase(), remarks);
		 * 
		 * }
		 * 
		 * 
		 * } catch (HisRecordNotFoundException e) {
		 * 
		 * 
		 * throw new HisRecordNotFoundException(e.getMessage()); } catch (Exception e) {
		 * 
		 * throw new
		 * HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
		 * + e); }
		 */
		return testAvailabilityDetail;
	}





	public List<LabTestVO> getGroupCodeWiseDtl(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "	.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST2_WITHOUT_LAB_PERMISSION";

		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST2_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST2_WITHOUT_LAB_PERMISSION";



		Sequence sq = new Sequence();

		String orderBy = " ORDER BY  TestGroupCode,labCode,gnum_test_seq_no";

		String condition1 = "and a.gnum_test_code not in (" + searchVO.getTestCode() + ")";


		populateMAP.put(sq.next(), searchVO.getUserGroupCodeWise());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			if (searchVO.getIsAddendum() != null)
			{
				if (searchVO.getIsAddendum() != null || searchVO.getIsAddendum().equals("1"))
					query = String.valueOf(query) + condition1; 
			}
			query = String.valueOf(query) + orderBy;
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			return lstLabVO;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public List<LabTestVO> getGroupCodeWiseDtlCumColl(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.RAISING_CUM_COLLECTION.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST2";
		Sequence sq = new Sequence();

		String orderBy = " ORDER BY  TestGroupCode,labCode,gnum_test_seq_no";




		populateMAP.put(sq.next(), searchVO.getUserGroupCodeWise());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			query = String.valueOf(query) + orderBy;
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			return lstLabVO;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}





	public List fetchUserGroupCodeCumColl(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List userGroupCode = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.USER_GROUP_CODE_CUM_COLL.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), "15");




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 






		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			userGroupCode = HelperMethodsDAO.getAlOfEntryObjects(rs);




		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return userGroupCode;
	}







	public List<LabTestVO> getTestsCodeWiseDtlCumColl(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.RAISING_CUM_SELECTION.HIVT_TEST_MST";
		Sequence sq = new Sequence();

		String condition1 = " AND UPPER(TRIM(gnum_user_test_code)) =UPPER(TRIM('" + searchVO.getTestCodeWise() + "'))";

		String orderBy = " ORDER BY labname, testname";


		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			query = String.valueOf(query) + condition1 + orderBy;
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			return lstLabVO;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public void insertRequisitionFormDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "INSERT.RESULT.FORM.DETAIL.HIVT_REQUISITIONFORM_DTL";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
							e);
		} 


		try {
			String value = voResultEntry.getResultEntryValue().equals("") ? "-" : voResultEntry.getResultEntryValue();

			populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());

			populateMAP.put(sq.next(), voResultEntry.getTestCode());
			populateMAP.put(sq.next(), voResultEntry.getParantParaCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), value);
			populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());
			populateMAP.put(sq.next(), "1");
			populateMAP.put(sq.next(), voResultEntry.getLoincCode());
			populateMAP.put(sq.next(), voResultEntry.getStrRefRange());
			populateMAP.put(sq.next(), voResultEntry.getLabCode());






		}
		catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITIONFORM_DTL Table");
		} 
	}




	public String isaddendumreqdno(String reqno, UserVO _userVO) {
		String query = "";

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.FIND_MAX_REQDNO.HIVT_REQ_HEADER";
		String record = null;











		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			return record;
		} 


		try {
			populateMAP.put(sq.next(), reqno);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());


		}
		catch (Exception e) {

			return record;
		} 

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);



			while (rs.next())
			{
				record = rs.getString(1);
			}
		}
		catch (Exception e) {

			return record;
		} 
		return record;
	}






	public void updateinheaderfordifflabaddendum(String newreqno, String oldreqno, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "UPDATE.HIVT_REQ_HEADER_DIFF_LAB_ADDENDUM";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			populateMAP.put(sq.next(), newreqno);
			populateMAP.put(sq.next(), oldreqno);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
	}




	public String insertRequisitionHeaderDtl_addendum(RequistionHeaderVO voReqHeader, UserVO _userVO) {
		String error = "";



		try {
			Procedure strProc = new Procedure("pkg_inv_dml.proc_insert_hivt_reqisition_header_addendum");
			strProc.addInParameter(1, 12, "3");
			strProc.addInParameter(2, 12, voReqHeader.getRequisitionNumber());
			strProc.addInParameter(3, 12, voReqHeader.getPatCrNo());
			strProc.addInParameter(4, 12, _userVO.getHospitalCode());
			strProc.addInParameter(5, 12, voReqHeader.getVisitNo());
			strProc.addInParameter(6, 12, voReqHeader.getLabCode());
			strProc.addInParameter(7, 12, voReqHeader.getReqHeaderStatus());
			strProc.addInParameter(8, 12, voReqHeader.getReqHeaderStatus());
			strProc.addInParameter(9, 12, _userVO.getSeatId());
			strProc.addInParameter(10, 12, voReqHeader.getEpisodeCode());
			strProc.addInParameter(11, 12, voReqHeader.getPatName());
			strProc.addInParameter(12, 12, voReqHeader.getReqType());
			strProc.addInParameter(13, 12, voReqHeader.getPatDob());
			strProc.addInParameter(14, 12, voReqHeader.getVisitDate());
			strProc.addInParameter(15, 12, voReqHeader.getIsActualDob());
			strProc.addInParameter(16, 12, voReqHeader.getAdvisedByDocNo());
			strProc.addInParameter(17, 12, voReqHeader.getGenderCode());
			strProc.addInParameter(18, 12, voReqHeader.getRequsitionRaisedThrough());
			strProc.addInParameter(19, 12, voReqHeader.getPatAge());
			strProc.addInParameter(20, 12, voReqHeader.getPatAdmissionNo());
			strProc.addInParameter(21, 12, voReqHeader.getPatAddress());
			strProc.addInParameter(22, 12, voReqHeader.getWardCode());
			strProc.addInParameter(23, 12, voReqHeader.getMobileNo());
			strProc.addInParameter(24, 12, voReqHeader.getEmailId());
			strProc.addInParameter(25, 12, voReqHeader.getRoomCode());
			strProc.addInParameter(26, 12, voReqHeader.getBedCode());
			strProc.addInParameter(27, 12, voReqHeader.getMlcNo());
			strProc.addInParameter(28, 12, voReqHeader.getBedName());
			strProc.addInParameter(29, 12, voReqHeader.getRoomName());
			strProc.addInParameter(30, 12, voReqHeader.getWardName());
			strProc.addInParameter(31, 12, voReqHeader.getDeptName());
			strProc.addInParameter(32, 12, null);
			strProc.addInParameter(33, 12, voReqHeader.getDeptUnitName());
			strProc.addInParameter(34, 12, voReqHeader.getDeptCode());
			strProc.addInParameter(35, 12, voReqHeader.getDeptUnitCode());
			strProc.addInParameter(36, 12, voReqHeader.getIsAutomatedRequest());
			strProc.addInParameter(37, 12, voReqHeader.getAdvisedByDocName());
			strProc.addInParameter(38, 12, voReqHeader.getRefHospitalCode());
			strProc.addInParameter(39, 12, "");
			strProc.addInParameter(40, 12, voReqHeader.getRefLabCode());
			strProc.addInParameter(41, 12, voReqHeader.getHospOrLabName());
			strProc.addInParameter(42, 12, voReqHeader.getExtCrNo());
			strProc.addInParameter(43, 12, voReqHeader.getBillNo());
			strProc.addInParameter(44, 12, voReqHeader.getDeleteReason());
			strProc.addInParameter(45, 12, "1");

			strProc.addInParameter(46, 12, null);
			strProc.addInParameter(47, 12, voReqHeader.getPatCatCode());
			strProc.addInParameter(48, 12, voReqHeader.getVisitReason());
			strProc.addInParameter(49, 12, voReqHeader.getIsassociatedTest());
			strProc.addInParameter(50, 12, voReqHeader.getIsassociatedreqno());
			strProc.addOutParameter(51, 12);
			strProc.execute(getTransactionContext().getConnection());
			error = (String)strProc.getParameterAt(51);
			System.out.println(" error" + error);
			String error1 = error;
			error1 = error1.concat("1");

		}
		catch (HisDataAccessException e) {

			throw new HisDataAccessException();
		} 

		return error;
	}





	public List<InvestigationRequistionVO> getDetailsOnBasisReqDno(String reqdno, UserVO _UserVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<InvestigationRequistionVO> parameterCombo = new ArrayList<InvestigationRequistionVO>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_LIST_DATA_FOR_ADDEDNDUM_TEST_ON_OLD_REQ_DNO_HIVT_REQUISITION_DTL";
		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();
		ValueObject[] valueObjects = null;
		List<InvestigationRequistionVO> lstInvResultEntryVO = new ArrayList<InvestigationRequistionVO>();

		populateMap.put(sq.next(), reqdno);
		populateMap.put(sq.next(), _UserVO.getHospitalCode());






		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}


			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(InvestigationRequistionVO.class, rs);

			for (int i = 0; i < valueObjects.length; i++)
			{

				lstInvResultEntryVO.add((InvestigationRequistionVO)valueObjects[i]);

				parameterCombo.add((InvestigationRequistionVO)lstInvResultEntryVO.get(i));

			}

		}
		catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return parameterCombo;
	}



























































































	public void insertRequisitionDtl_addendum(InvestigationRequistionVO voLabTest, UserVO _userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "UPDATE.HIVT_REQ_DTL_ADENDUM_TEST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			populateMAP.put(sq.next(), "1");
			populateMAP.put(sq.next(), voLabTest.getReportDate());
			populateMAP.put(sq.next(), voLabTest.getReportUrl());
			populateMAP.put(sq.next(), (voLabTest.getChangeCount() == null) ? "0" : voLabTest.getChangeCount());
			populateMAP.put(sq.next(), (voLabTest.getReportChange() == null) ? "0" : voLabTest.getReportChange());
			populateMAP.put(sq.next(), (voLabTest.getReportChangeSeatId() == null) ? "0" : voLabTest.getReportChangeSeatId());
			populateMAP.put(sq.next(), voLabTest.getReportChangeDate().toString());

			populateMAP.put(sq.next(), "6");
			populateMAP.put(sq.next(), voLabTest.getSampleNo());
			populateMAP.put(sq.next(), voLabTest.getTempSampleNo());
			populateMAP.put(sq.next(), (voLabTest.getStrCollDateTime() == null) ? "" : voLabTest.getStrCollDateTime().toString());
			populateMAP.put(sq.next(), voLabTest.getStrCollectionSeatId());
			populateMAP.put(sq.next(), voLabTest.getStrSampleCollectionAreaCode());
			populateMAP.put(sq.next(), voLabTest.getIs_Sample_Received());
			populateMAP.put(sq.next(), voLabTest.getStrIsAccepted());
			populateMAP.put(sq.next(), voLabTest.getStrAcceptanceDateTime().toString());
			populateMAP.put(sq.next(), voLabTest.getStrAcceptanceSeatId());
			populateMAP.put(sq.next(), voLabTest.getStrDailyLabNo());
			populateMAP.put(sq.next(), voLabTest.getUomCode());
			populateMAP.put(sq.next(), voLabTest.getCollectedVolume());
			populateMAP.put(sq.next(), voLabTest.getContainerCode());
			populateMAP.put(sq.next(), voLabTest.getStrRequsitionDNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());









		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
	}






	public String getlabcodesaddendum(String reqno, UserVO userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String Formate = "";
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT_ALL_TETCODE_ADDENDUM";
		Connection conn = getTransactionContext().getConnection();


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();


		try {
			populateMAP.put(sq.next(), reqno);
			populateMAP.put(sq.next(), userVO.getHospitalCode());



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					Formate = rs.getString(1);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return Formate;
	}




	public String getTestCodesKeyword(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		String Filename = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;

		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_TEST_CODES_KEYWORD";
		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		String condition = " And lower(gstr_test_name) like '%" + searchVO.getTestSearchKeyword().split("\\(")[0].toLowerCase() + "%'";



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 
		query = String.valueOf(query) + condition;

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}


			rs.beforeFirst();

			rs.next();
			Filename = rs.getString(1);
			System.out.println("filename return successfully " + Filename);

		}
		catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return Filename;
	}





	public String getfileuploaddatatestwise(String dno, UserVO _userVO, String testParaMeterCode) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.FILE_UPLOAD_TESTWISE_REQ_DTL";
		String returnn = "";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();



		try {
			populateMAP.put(sq.next(), dno);
			populateMAP.put(sq.next(), testParaMeterCode);

			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			while (rs.next())
			{
				returnn = rs.getString(1);
				System.out.println("string record" + returnn);
			}

		}
		catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		} 
		return returnn;
	}



	public void insertRequisitionDtl_addendumNewTest(InvestigationRequistionVO voLabTest, UserVO _userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "UPDATE.HIVT_REQ_DTL_ADENDUM_TEST11";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 









		try {
			populateMAP.put(sq.next(), "6");
			populateMAP.put(sq.next(), voLabTest.getSampleNo());
			populateMAP.put(sq.next(), voLabTest.getTempSampleNo());
			populateMAP.put(sq.next(), (voLabTest.getStrCollDateTime() == null) ? "" : voLabTest.getStrCollDateTime().toString());
			populateMAP.put(sq.next(), voLabTest.getStrCollectionSeatId());
			populateMAP.put(sq.next(), voLabTest.getStrSampleCollectionAreaCode());
			populateMAP.put(sq.next(), voLabTest.getIs_Sample_Received());
			populateMAP.put(sq.next(), voLabTest.getStrIsAccepted());
			populateMAP.put(sq.next(), voLabTest.getStrAcceptanceDateTime().toString());
			populateMAP.put(sq.next(), voLabTest.getStrAcceptanceSeatId());
			populateMAP.put(sq.next(), voLabTest.getStrDailyLabNo());
			populateMAP.put(sq.next(), voLabTest.getUomCode());
			populateMAP.put(sq.next(), voLabTest.getCollectedVolume());
			populateMAP.put(sq.next(), voLabTest.getContainerCode());
			populateMAP.put(sq.next(), voLabTest.getStrRequsitionDNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());









		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
	}




	public List<LabTestVO> getPrvTestDtlUsingAJAXX(String CrNo, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_PRV_TEST_DETAILLLLL";
		Sequence sq = new Sequence();




		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), CrNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}





	public void deleteReqDtll(String lab, String test, String requisitionno, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "DELETE_RESULT_ENTRY_TEST_FROM_RAISING";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			populateMAP.put(sq.next(), "16");
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());


			populateMAP.put(sq.next(), "6");

			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), lab);
			populateMAP.put(sq.next(), test);
			populateMAP.put(sq.next(), requisitionno);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
	}





	public String getlabcodesaddendumResultEntry(String reqno, UserVO userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String Formate = "";
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT_ALL_TETCODE_ADDENDUM_RESULT_ENTRY";
		Connection conn = getTransactionContext().getConnection();


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();


		try {
			populateMAP.put(sq.next(), reqno);
			populateMAP.put(sq.next(), userVO.getHospitalCode());



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (rs.next())
			{




				rs.beforeFirst();
				if (rs.next())
				{
					Formate = rs.getString(1);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return Formate;
	}



	public List getlabteriff(UserVO _UserVO, InvestigationSearchVO searchVO) {
		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List lsttest = new ArrayList();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LABNAME_COMBO.TERIFFTEST_WITHOUT_LAB_PERMISSION";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		String deskcalllabhide = "";

		if (searchVO.getPatAdmNo() == null || !searchVO.getPatAdmNo().equals("-1"))
		{
			deskcalllabhide = " and gnum_islabhidefromdesk=0";
		}


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

			if (searchVO.getPatAdmNo() == null || !searchVO.getPatAdmNo().equals("-1"))
			{
				query = String.valueOf(query) + deskcalllabhide;

			}
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		System.out.println("Query ---------> " + query);



		try {
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			//  populateMap.put(sq.next(), _UserVO.getUserSeatId());
			//  populateMap.put(sq.next(), _UserVO.getHospitalCode());



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 


		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				lsttest = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}

		} catch (Exception e) {

			if (e.getClass() != HisRecordNotFoundException.class)
			{



				throw new HisDataAccessException("HisDataAccessException:: " + e); } 
		} 
		return lsttest;
	}




	public List<LabTestVO> searchLabWiseTestDtlstariff(InvestigationSearchVO searchVO, UserVO _UserVO, Inv_RequisitionRaisingPatientVO patvo, Inv_PatientAdmissionDtlVO patvoadd) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_TEST_TARIFF_WITHOUT_LAB_PERMISSION";

		Sequence sq = new Sequence();

		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_TEST_TARIFF_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_TEST_TARIFF_WITHOUT_LAB_PERMISSION";



		String orderBytestwise = "";
		if (searchVO.getSearchTestNamelabwise() != null)
		{
			if (searchVO.getSearchTestNamelabwise().contains("#")) {

				searchVO.setSearchLabName(searchVO.getSearchTestNamelabwise().split("#")[1]);
				orderBytestwise = " ORDER BY gnum_test_code=" + searchVO.getSearchTestNamelabwise().split("#")[0] + " desc ";
			} 
		}


		String deskcalllabhide = "";

		if (searchVO.getPatAdmNo() == null || !searchVO.getPatAdmNo().equals("-1"))
		{
			// deskcalllabhide = " and a.gnum_labcode not in (select nvl(n.gnum_lab_code,'0') from hivt_laboratory_mst n where n.gnum_lab_code=a.gnum_labcode and n.gnum_isvalid=1 and n.gnum_hospital_code=" + _UserVO.getHospitalCode() + "  and n.gnum_islabhidefromdesk=1)";
			deskcalllabhide = "and gnum_islabhidefromdesk_reqd=0 ";
		}

		String condition1 = " and a.GNUM_LABCODE =" + searchVO.getSearchLabName();
		String condition2 = " and a.GNUM_TEST_CODE =" + searchVO.getSearchTestName();
		String condition3 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code)";

		String condition4 = " and a.gnum_test_code not in (" + searchVO.getTestCode() + ")";
		String orderBy = " ORDER BY labname, testname";
		String condition5 = " and a.gnum_test_code  in (" + searchVO.getTestCodesSearchKeyword() + ")";


		String wardcode = "";
		int requisitionTypeForBilling = 0;

		if (patvo != null && patvo.getPatStatus().equals("IPD")) {

			requisitionTypeForBilling = 2;
			wardcode = patvoadd.getPatwardcode();
		}
		else if (patvo != null && patvo.getPatStatus().equals("OPD")) {

			requisitionTypeForBilling = 1;
		}
		else {

			requisitionTypeForBilling = 1;
		} 

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), wardcode);
		populateMAP.put(sq.next(), patvo.getPatCategoryCode());
		populateMAP.put(sq.next(), Integer.toString(requisitionTypeForBilling));

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			if (searchVO.getSearchLabName() != null && !searchVO.getSearchLabName().equals(""))
				query = String.valueOf(query) + condition1; 
			if (searchVO.getTestSearchKeyword().equals("") && searchVO.getSearchTestName() != null && !searchVO.getSearchTestName().equals(""))
				query = String.valueOf(query) + condition2; 
			if (!searchVO.getTestSearchKeyword().equals("")) {
				query = String.valueOf(query) + condition5;
			}
			if (searchVO.getSearchTestGroupName() != null && !searchVO.getSearchTestGroupName().equals("")) {
				query = String.valueOf(query) + condition3;
			}

			if (searchVO.getIsAddendum() != null)
			{
				if (searchVO.getIsAddendum() != null || searchVO.getIsAddendum().equals("1")) {
					query = String.valueOf(query) + condition4;
				}
			}
			if (searchVO.getPatAdmNo() == null || !searchVO.getPatAdmNo().equals("-1"))
			{
				query = String.valueOf(query) + deskcalllabhide;
			}

			if (searchVO.getSearchTestNamelabwise() != null) {

				query = String.valueOf(query) + orderBytestwise;
			}
			else {

				query = String.valueOf(query) + orderBy;
			} 

			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}






	public List<LabTestVO> searchLabWiseTestDtlsNEW(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";


		Sequence sq = new Sequence();


		if(searchVO.getRaisethrough()!=null && searchVO.getRaisethrough().equals("1"))
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION_OPDDESK";
		else
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_WITHOUT_LAB_PERMISSION";


		String condition1 = " and a.GNUM_LABCODE =" + searchVO.getSearchLabName();

		String condition3 = "and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code =" + searchVO.getSearchTestGroupName() + " AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code and gnum_is_valid=1)";

		String condition4 = " and a.gnum_test_code not in (" + searchVO.getTestCode() + ")";

		String val = searchVO.getSearchTestName();
		String orderBy = "";
		if (val != null && val.contains("#"))
		{
			orderBy = " ORDER BY a.gnum_test_code=" + val.split("#")[0] + " desc ,gnum_labcode=" + val.split("#")[1] + " desc";
		}







		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//   populateMAP.put(sq.next(), "15");
		//  populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		//  populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {
			if (searchVO.getSearchLabName() != null && !searchVO.getSearchLabName().equals("")) {
				query = String.valueOf(query) + condition1;
			}

			if (!searchVO.getTestSearchKeyword().equals(""))
			{

				if (searchVO.getSearchTestGroupName() != null && !searchVO.getSearchTestGroupName().equals("")) {
					query = String.valueOf(query) + condition3;
				}
			}
			if (searchVO.getIsAddendum() != null)
			{
				if (searchVO.getIsAddendum() != null || searchVO.getIsAddendum().equals("1"))
					query = String.valueOf(query) + condition4; 
			}
			query = String.valueOf(query) + orderBy;




			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}





	public String getBillingCheck1testcode(String testcodess, String ward, String req, String cat, UserVO userVO) {
		String query = "";
		String check = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;

		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.GETBILLING_CHECK_TEST_CODE_GET";
		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), ward);
		populateMAP.put(sq.next(), cat);
		populateMAP.put(sq.next(), req);
		populateMAP.put(sq.next(), testcodess);
		populateMAP.put(sq.next(), userVO.getHospitalCode());


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (rs.next())
			{




				check = rs.getString(1);
				System.out.println("bill test::" + testcodess + "::rupeee:::" + check);

			}

		}
		catch (HisRecordNotFoundException e) {


			return check;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return check;
	}







	public String isduplicatetestraisedtoday(InvestigationSearchVO searchVO, UserVO _UserVO, Inv_RequisitionRaisingPatientVO patVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String record = null;
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_ISDUPLICATE_TEST_RAISED";
		String queryKey_grp = "SELECT_ISDUPLICATE_TEST_RAISED_GROUP";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 










		try {
			if (searchVO.getGroupcode().equals("0"))
			{

				// populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getLabCode());
				populateMAP.put(sq.next(), searchVO.getTestCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getPatientCrNo());

			}
			else
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_grp);


				//   populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getLabCode());
				populateMAP.put(sq.next(), searchVO.getGroupcode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getPatientCrNo());


			}




		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			if (rs.next())
				record = rs.getString(1); 
			System.out.println("isduplicatetestraised====================" + record);

		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 

		return record;
	}




	public String getgrpcode(String searchVO, UserVO _UserVo) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String record = null;
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_GRP_CODE_ISDUPLICATE";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 












		try {
			populateMAP.put(sq.next(), searchVO);
			populateMAP.put(sq.next(), _UserVo.getHospitalCode());









		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			if (rs.next())
				record = rs.getString(1); 
			System.out.println("isduplicatetestraised====================" + record);

		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 

		return record;
	}




	public void insertPid(RequistionHeaderVO voLabTest, UserVO _userVO, String piddd, String piddata) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "INSERT.HIVT_ICTC_DTL";
		String[] val = piddd.split("@@");
		String reqno = "";
		String testcode = "";
		for (int k = 0; k < val.length; k++) {

			String[] vales = val[k].split("#");
			reqno = vales[0];
			testcode = String.valueOf(testcode) + vales[2] + ",";
		} 


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			populateMAP.put(sq.next(), voLabTest.getPatCrNo());

			populateMAP.put(sq.next(), voLabTest.getPatCrNo());



			populateMAP.put(sq.next(), reqno);

			populateMAP.put(sq.next(), piddata.split("@@@")[1]);
			System.out.println("qwerty1 " + piddata.split("@@@")[1]);

			populateMAP.put(sq.next(), voLabTest.getFollowup());
			populateMAP.put(sq.next(), voLabTest.getGenderCode());

			populateMAP.put(sq.next(), voLabTest.getPatName());

			populateMAP.put(sq.next(), voLabTest.getPatAddress());








			populateMAP.put(sq.next(), voLabTest.getPatCrNo());



			populateMAP.put(sq.next(), voLabTest.getPatCrNo());

			populateMAP.put(sq.next(), voLabTest.getPatAge());

			populateMAP.put(sq.next(), voLabTest.getMobileNo());

			populateMAP.put(sq.next(), piddata.split("@@@")[2]);
			System.out.println("qwerty2 " + piddata.split("@@@")[2]);

			populateMAP.put(sq.next(), piddata.split("@@@")[0]);
			System.out.println("qwerty0 " + piddata.split("@@@")[0]);


			populateMAP.put(sq.next(), null);

			populateMAP.put(sq.next(), null);

			populateMAP.put(sq.next(), "1");

			populateMAP.put(sq.next(), _userVO.getSeatId());



		}
		catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_DTL Table");
		} 
	}



	public Inv_ictc_VO getictcdetails(Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO) {
		Inv_ictc_VO invReqRaisingVO;
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_ISPID_EXIST";
		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), patVO.getPatCRNo());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);
			rs.beforeFirst();
			invReqRaisingVO = new Inv_ictc_VO();
			if (rs.next())
			{

				HelperMethods.populateVOfrmRS(invReqRaisingVO, rs);
			}
		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return invReqRaisingVO;
	}



	public String ispidexist(InvestigationSearchVO searchVO, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String record = null;
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "IS_PID_EXIST_";



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 













		try {
			populateMAP.put(sq.next(), searchVO.getIspidexist());








		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			if (rs.next())
				record = rs.getString(1); 
			System.out.println("ispidexist====================" + record);

		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 

		return record;
	}




	public String AJX_IS_LAB_MANDTORY(InvestigationSearchVO searchVO, UserVO _UserVO, Inv_RequisitionRaisingPatientVO patVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String record = null;
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_ISMANDATORY_LAB";



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 










		try {
			populateMAP.put(sq.next(), searchVO.getLabCode());

			populateMAP.put(sq.next(), _UserVO.getHospitalCode());








		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			if (rs.next())
				record = rs.getString(1); 
			System.out.println("isduplicatetestraised====================" + record);

		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 

		return record;
	}


















































































































	public String updateappointmentdateinheader(InvestigationRequistionVO voLabTest, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "UPDATE.APPOINTMENT.LAB_BAISED.HEADER";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			populateMAP.put(sq.next(), voLabTest.getLabbaseddatetimeappt());
			populateMAP.put(sq.next(), voLabTest.getStrReqNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 
		return null;
	}


	public Inv_RequisitionRaisingPatientVO getInvRaisingPatDetailsnew(String crno, UserVO _UserVO, String reqno) {
		Inv_RequisitionRaisingPatientVO invReqRaisingVO;
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_DATA_BILLING_REQUISITIONWISE";
		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), reqno);



		populateMAP.put(sq.next(), _UserVO.getHospitalCode());



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);
			rs.beforeFirst();
			invReqRaisingVO = new Inv_RequisitionRaisingPatientVO();
			if (rs.next())
			{

				HelperMethods.populateVOfrmRS(invReqRaisingVO, rs);
			}
		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return invReqRaisingVO;
	}




	public String checkBillDtl_beforedelete(InvestigationSearchVO searchVO, UserVO _UserVO, String reqType) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String record = null;
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.BILLINGSTATUS.HIVT_REQUISITION_DTL_BEFORE_DELETE";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 











		try {
			populateMAP.put(sq.next(), reqType);


			populateMAP.put(sq.next(), searchVO.getRequisitionNo());
			populateMAP.put(sq.next(), searchVO.getDelTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			if (rs.next()) {
				record = rs.getString(1);
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 

		return record;
	}




	public List<BookMarkVO> getBookMarkListraising(UserVO _userVO, String iscallingfromdesk,String desktype) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "";

		if(desktype!=null && desktype.equals("1"))
			queryKey= "SELECT.BOOK_MARK_DTLS.HIVT_BOOKMARK_MST_RAISING_OPDDESK";
		else
			queryKey= "SELECT.BOOK_MARK_DTLS.HIVT_BOOKMARK_MST_RAISING";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

			if (iscallingfromdesk != null && iscallingfromdesk.equals("1"))
			{

				query = String.valueOf(query) + "and hivtnum_lab_code not in (select nvl(n.gnum_lab_code,'0') from hivt_laboratory_mst n where n.gnum_lab_code=hivtnum_lab_code and n.gnum_isvalid=1 and n.gnum_hospital_code=" + _userVO.getHospitalCode() + "  and n.gnum_islabhidefromdesk  in (1) )";
			}

			query = String.valueOf(query) + "order by INITCAP(hivtstr_bookmark_name)";

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();









		try {
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			populateMAP.put(sq.next(), _userVO.getUserId());


		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 

		BookMarkVO[] bookMarkVOs = null;
		List<BookMarkVO> lstBookMarkVO = new ArrayList<BookMarkVO>();
		ValueObject[] valueObjects = null;


		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{




				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(BookMarkVO.class, rs);

				for (int i = 0; i < valueObjects.length; i++)
				{

					lstBookMarkVO.add((BookMarkVO)valueObjects[i]);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		} 
		return lstBookMarkVO;
	}




	public String getRequisitionFormMasterData(String testcode, UserVO _userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.DATA_REQUISITION_FORM_HIVT_REQFROM_TEST_PARA_MST";
		String returnn = "";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();



		try {
			populateMAP.put(sq.next(), testcode);

		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			while (rs.next())
			{
				returnn = rs.getString(1);
				System.out.println("string record" + returnn);
			}

		}
		catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		} 
		return returnn;
	}






	public String getechodata(String dno, UserVO _userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.ECHO.DATA";
		String returnn = "";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();



		try {
			populateMAP.put(sq.next(), dno);


		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			while (rs.next())
			{
				returnn = rs.getString(1);
				System.out.println("string record" + returnn);
			}

		}
		catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		} 
		return returnn;
	}




	public List<LabTestVO> gettestnameforxrayprocess(UserVO _UserVO) {
		List<LabTestVO> lstLabVO = null;

		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.SEARCHINGTESTCODE.FOR.XRAYPRCOESS";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				lstLabVO = new ArrayList<LabTestVO>();
				LabTestVO voLabTest = null;
				while (rs.next()) {
					voLabTest = new LabTestVO();
					HelperMethods.populateVOfrmRS(voLabTest, rs);
					lstLabVO.add(voLabTest);
				}

			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstLabVO;
	}




	public List<LabTestVO> searchLabWiseTestDtlstariffxrayprocess(UserVO _UserVO, Inv_RequisitionRaisingPatientVO patvo, List<Inv_PatientAdmissionDtlVO> patvoaddm) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_TEST_TARIFF_WITHOUT_LAB_PERMISSION_XRAYPROCESS";

		Sequence sq = new Sequence();
		Inv_PatientAdmissionDtlVO patvoadd = null;
		if (patvoaddm != null && patvoaddm.size() > 0) {
			patvoadd = (Inv_PatientAdmissionDtlVO)patvoaddm.get(0);
		}


		String orderBytestwise = "";










		String deskcalllabhide = "";











		String orderBy = " ORDER BY labname, testname";



		String wardcode = "";
		int requisitionTypeForBilling = 0;

		if (patvo != null && patvo.getPatStatus().equals("IPD")) {

			requisitionTypeForBilling = 2;
			wardcode = patvoadd.getPatwardcode();
		}
		else if (patvo != null && patvo.getPatStatus().equals("OPD")) {

			requisitionTypeForBilling = 1;
		}
		else {

			requisitionTypeForBilling = 1;
		} 

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), wardcode);
		populateMAP.put(sq.next(), patvo.getPatCategoryCode());
		populateMAP.put(sq.next(), Integer.toString(requisitionTypeForBilling));

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), "15");
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 






























		try {
			query = String.valueOf(query) + orderBy;


			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (rs.next())
			{




				rs.beforeFirst();
				lstLabVO = new ArrayList<LabTestVO>();
				LabTestVO voLabTest = null;
				while (rs.next()) {
					voLabTest = new LabTestVO();
					HelperMethods.populateVOfrmRS(voLabTest, rs);
					lstLabVO.add(voLabTest);
				}

			}

		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}





	public List<LabTestVO> getviews(UserVO _UserVO) {
		List<LabTestVO> lstLabVO = null;

		String query = "";
		Map populateMap = new HashMap();
		ResultSet rs = null;
		List<String> lstTestNames = new ArrayList<String>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_VIEWS_TESTWISE";

		Sequence sq = new Sequence();
		Connection conn = getTransactionContext().getConnection();




		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (rs.next())
			{




				rs.beforeFirst();
				lstLabVO = new ArrayList<LabTestVO>();
				LabTestVO voLabTest = null;
				while (rs.next()) {
					voLabTest = new LabTestVO();
					HelperMethods.populateVOfrmRS(voLabTest, rs);
					lstLabVO.add(voLabTest);
				}

			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}

			throw new HisDataAccessException("HisDataAccessException:: " + e);
		} 
		return lstLabVO;
	}




	public String getextratestforbillingxray(UserVO userVO) {
		String query = "";
		String check = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;

		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_ADDDITIONCHARGETEST_XRAY";
		Sequence sq = new Sequence();








		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (rs.next())
			{




				check = rs.getString(1);
				System.out.println("check::" + check);

			}

		}
		catch (HisRecordNotFoundException e) {


			return check;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return check;
	}




	public String gettestcodesforwhichnotaddextracharge(UserVO userVO) {
		String query = "";
		String check = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;

		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_ADDDITIONCHARGENOTADDFORTEST_XRAY";
		Sequence sq = new Sequence();








		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (rs.next())
			{




				check = rs.getString(1);
				System.out.println("check::" + check);

			}

		}
		catch (HisRecordNotFoundException e) {


			return check;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return check;
	}





	public List<BookMarkVO> getBookMarkListraisingxray(UserVO _userVO, String iscallingfromdesk) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.BOOK_MARK_DTLS.HIVT_BOOKMARK_MST_RAISING_XRAYPROCESS";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

			query = String.valueOf(query) + "and exists (select 1 from hivt_laboratory_mst n where n.gnum_lab_code=hivtnum_lab_code and n.gnum_isvalid=1 and n.gnum_hospital_code=" + _userVO.getHospitalCode() + "  and n.gnum_issearch  in (1) )";

			if (iscallingfromdesk != null && iscallingfromdesk.equals("1"))
			{

				query = String.valueOf(query) + "and hivtnum_lab_code not in (select nvl(n.gnum_lab_code,'0') from hivt_laboratory_mst n where n.gnum_lab_code=hivtnum_lab_code and n.gnum_isvalid=1 and n.gnum_hospital_code=" + _userVO.getHospitalCode() + "  and n.gnum_islabhidefromdesk  in (1) )";
			}

			query = String.valueOf(query) + "order by INITCAP(hivtstr_bookmark_name)";

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();









		try {
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			populateMAP.put(sq.next(), _userVO.getUserId());


		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		} 

		BookMarkVO[] bookMarkVOs = null;
		List<BookMarkVO> lstBookMarkVO = new ArrayList<BookMarkVO>();
		ValueObject[] valueObjects = null;


		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{




				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(BookMarkVO.class, rs);

				for (int i = 0; i < valueObjects.length; i++)
				{

					lstBookMarkVO.add((BookMarkVO)valueObjects[i]);
				}
			}

		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		} 
		return lstBookMarkVO;
	}



	public String getbillfornoechargeviews(UserVO userVO) {
		String query = "";
		String check = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;

		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT_TEST_FOR_NOT_ADD_VIEWS_CHARGE";
		Sequence sq = new Sequence();








		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			check = rs.getString(1);
			System.out.println("check::" + check);



		}
		catch (HisRecordNotFoundException e) {


			return check;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return check;
	}





	public String getlastreqdate(String testcode, String labcode, String crno, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String record = null;
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "GET_LAST_REQ_DATE";



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 













		try {
			populateMAP.put(sq.next(), testcode);
			populateMAP.put(sq.next(), labcode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), crno);








		}
		catch (Exception e) {

			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		} 

		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

			if (rs.next())
				record = rs.getString(1); 
			System.out.println("ispidexist====================" + record);

		}
		catch (Exception e) {

			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		} 

		return record;
	}









	public List<Inv_SampleCollectionVO> getallreqdetails(UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Inv_SampleCollectionVO> lstinvSampleCollectionVO = null;
		lstinvSampleCollectionVO = new ArrayList<Inv_SampleCollectionVO>();


		try {
			tx.begin();
			SampleCollectionDAO objSampleCollectionDAO = new SampleCollectionDAO(tx);
			lstinvSampleCollectionVO = objSampleCollectionDAO.getSampleCollectionArea(_UserVO);


		}
		catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("error.... Essential BO");
		}
		finally {

			tx.close();
		} 

		return lstinvSampleCollectionVO;
	}




	public Map<String, LabTestVO> getPrvTestDtlUsingAJAXxray(String CrNo, UserVO _UserVO, String fromwhichcall) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		Map<String, LabTestVO> mp = new HashMap<String, LabTestVO>();
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "";

		if (fromwhichcall.equals("0")) {
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_PRV_TEST_DETAIL_XRAY";
		} else {
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_PRV_TEST_DETAIL_XRAY_ALL";
		} 
		Sequence sq = new Sequence();




		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), CrNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		if (fromwhichcall.equals("1")) {

			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), CrNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		} 





		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				voLabTest.setPatCrNo(CrNo);
				lstLabVO.add(voLabTest);
			} 


			if (lstLabVO != null && lstLabVO.size() > 0)
			{

				String keyreqno = "";
				for (int k = 0; k < lstLabVO.size(); k++)
				{
					LabTestVO vo = (LabTestVO)lstLabVO.get(k);

					if (mp.containsKey(vo.getReqNo()))
					{
						LabTestVO voo = (LabTestVO)mp.get(vo.getReqNo());
						String testcode = String.valueOf(voo.getPrvTestCode()) + "," + vo.getPrvTestCode();
						String testname = String.valueOf(voo.getTestName()) + "," + vo.getTestName();
						String testwiseviewcount = String.valueOf(voo.getTotalviewcount()) + "," + vo.getTotalviewcount();
						voo.setTestCode(testcode);
						voo.setTestName(testname);
						voo.setTotalviewcount(testwiseviewcount);
						mp.put(voo.getReqNo(), voo);
					}
					else
					{
						mp.put(vo.getReqNo(), vo);

					}


				}


			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return mp;
	}




	public List<LabTestVO> getPrvTestDtlUsingAJAXxrayreqwise(String reqno, UserVO _UserVO) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_PRV_TEST_DETAIL_XRAY_REQ_WISE";
		Sequence sq = new Sequence();




		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), reqno);


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException e) {


			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}





	public List<LabTestVO> getPrvTestDtlUsingAJAXX_NEW(String CrNo, UserVO _UserVO, String fromwhichcall) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "";
		if (fromwhichcall == null || fromwhichcall.equals("") || fromwhichcall.equals("0")) {
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_PRV_TEST_DETAIL";
		} else {
			queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST_PRV_TEST_DETAILLLLL_NEW";
		} 
		Sequence sq = new Sequence();




		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), CrNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		if (fromwhichcall != null && !fromwhichcall.equals("") && fromwhichcall.equals("1")) {

			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), CrNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		} 



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}




	public List<invReportInProcessVO> getdno(invReportInProcessVO invresultreportprintingvo, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="";

		//queryKey="SELECT.RESULT_REPORT_PRINTING_HIVT_REQUISITION_HEADER";


		String queryKey1 ="";


		queryKey1=	"SELECT.RESULT_REPORT_PRINTING_FOR_PDF_IN_PROCESS_HIVT_REQUISITION_HEADER_INPROCESS_DNO_WISE";


		try
		{


			query = HelperMethodsDAO.getQuery(filename, queryKey1);


		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{


			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),invresultreportprintingvo.getRequisitionNo());




			//populateMAP.put(sq.next(), _userVO.getHospitalCode());

			//populateMAP.put(sq.next(), patVO.getPatCRNo());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}

		//PatientDetailVO[] dailyPatientVOs = null;
		List<invReportInProcessVO> lstinvReportInProcessVO = new ArrayList<invReportInProcessVO>();
		ValueObject[] valueObjects = null;

		try
		{

			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invReportInProcessVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstinvReportInProcessVO.add((invReportInProcessVO)valueObjects[i]);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("InvResultReportPrintingDAO:setPatientEssentials:HelperMethods :: " + e);
		}
		return lstinvReportInProcessVO;
	}	



	public List<LabTestVO> getcharge(InvestigationSearchVO searchVO, UserVO _UserVO, Inv_RequisitionRaisingPatientVO patvo, Inv_PatientAdmissionDtlVO patvoadd) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SEELCT_CHARGE_TEST_LIST";

		Sequence sq = new Sequence();


		String wardcode = "0";
		int requisitionTypeForBilling = 0;

		if (patvo != null && patvo.getPatStatus().equals("IPD")) {

			requisitionTypeForBilling = 2;
			wardcode = patvoadd.getPatwardcode();
		}
		else if (patvo != null && patvo.getPatStatus().equals("OPD")) {

			requisitionTypeForBilling = 1;
		}
		else {

			requisitionTypeForBilling = 1;
		} 

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), wardcode);
		populateMAP.put(sq.next(), patvo.getPatCategoryCode());
		populateMAP.put(sq.next(), Integer.toString(requisitionTypeForBilling));
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());





		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {


			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				//  throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}



	public String  getgroupcodeishideornot(String groupcode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String Formate="0";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;

		String queryKey = "SELECT_IS_GROUP_CODE_HIDE_NOT";
		Connection conn=super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(),groupcode);
			//populateMAP.put(sq.next(),userVO.getHospitalCode());



		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				if(rs.next())
				{
					Formate=rs.getString(1);
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
		return Formate;
	}




	public List<LabTestVO> gettestcharges(InvestigationSearchVO searchVO, UserVO _UserVO, Inv_RequisitionRaisingPatientVO patvo, Inv_PatientAdmissionDtlVO patvoadd) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT.TEST_CHARGES";

		Sequence sq = new Sequence();



		String wardcode = "";
		int requisitionTypeForBilling = 0;

		if (patvo != null && patvo.getPatStatus().equals("IPD")) {

			requisitionTypeForBilling = 2;
			wardcode = patvoadd.getPatwardcode();
		}
		else if (patvo != null && patvo.getPatStatus().equals("OPD")) {

			requisitionTypeForBilling = 1;
		}
		else {

			requisitionTypeForBilling = 1;
		} 

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), wardcode);
		populateMAP.put(sq.next(), patvo.getPatCategoryCode());
		populateMAP.put(sq.next(), Integer.toString(requisitionTypeForBilling));

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());


		
		/*
		 * populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 * populateMAP.put(sq.next(), wardcode);
		 * populateMAP.put(sq.next(),patvo.getPatCategoryCode());
		 * populateMAP.put(sq.next(),Integer.toString(requisitionTypeForBilling));
		 * populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 * populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 */
		 

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {


			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				//   throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return lstLabVO;
	}



	public List<LabTestVO> gettestchargesgrp(InvestigationSearchVO searchVO, UserVO _UserVO, Inv_RequisitionRaisingPatientVO patvo, Inv_PatientAdmissionDtlVO patvoadd) {
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<LabTestVO> lstLabVO = null;
		String filename = "new_investigation.transactions.investigationTransactionQuery";

		String queryKey = "SELECT.GROUP_CHARGES";

		Sequence sq = new Sequence();



		String wardcode = "";
		int requisitionTypeForBilling = 0;

		if (patvo != null && patvo.getPatStatus().equals("IPD")) {

			requisitionTypeForBilling = 2;
			wardcode = patvoadd.getPatwardcode();
		}
		else if (patvo != null && patvo.getPatStatus().equals("OPD")) {

			requisitionTypeForBilling = 1;
		}
		else {

			requisitionTypeForBilling = 1;
		} 

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), wardcode);
		populateMAP.put(sq.next(), patvo.getPatCategoryCode());
		populateMAP.put(sq.next(), Integer.toString(requisitionTypeForBilling));
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());



		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 


		try {


			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				//   throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			rs.beforeFirst();
			lstLabVO = new ArrayList<LabTestVO>();
			LabTestVO voLabTest = null;
			while (rs.next()) {
				voLabTest = new LabTestVO();
				HelperMethods.populateVOfrmRS(voLabTest, rs);
				lstLabVO.add(voLabTest);

			}


		}
		catch (HisRecordNotFoundException hisRecordNotFoundException) {



		}
		catch (Exception e) {

			System.out.println("some problem while fetching rates....");
		} 
		return lstLabVO;
	}



	public String getsamplecodebydefault(InvestigationRequistionVO voLabTest,UserVO userVO) {
		String query = "";
		String check = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;

		String filename = "new_investigation.transactions.investigationTransactionQuery";
		String queryKey = "SELECT.BYDEFAULT.CHECKSAMPLENO";
		Sequence sq = new Sequence();



		populateMAP.put(sq.next(), voLabTest.getStrLabCode());
		populateMAP.put(sq.next(), voLabTest.getStrTestCode().toString());
		populateMAP.put(sq.next(), userVO.getHospitalCode());


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 



		try {
			rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
					populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Test / Lab  Found");
			}


			check = rs.getString(1);
			System.out.println("check::" + check);



		}
		catch (HisRecordNotFoundException e) {


			return check;
		}
		catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		} 
		return check;
	}

public List<Inv_SampleCollectionVO> AjaxBilledUnbilledDetails(Inv_SampleCollectionVO vo, UserVO userVO) {
		
		
		String filename=InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey1="SELECT_INV_BILLED_UNBILLED_DTL_BASED_ON_CRNO";
				
		String query1="";
	

		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();
		
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		Inv_SampleCollectionVO vo2 = new Inv_SampleCollectionVO();
		List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
		try
		{
				query1 = HelperMethodsDAO.getQuery(filename, queryKey1);
			
		} 

		catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		try
		{	
			
				populateMap.put(sq.next(), vo.getReqType());
				populateMap.put(sq.next(), vo.getCrNo());
				populateMap.put(sq.next(),userVO.getHospitalCode());
	
			rs = HelperMethodsDAO.executeQuery(conn, query1, populateMap);
			
			if(!rs.next()) { throw new HisRecordNotFoundException("No Patient List  Found"); }
			else {
				rs.beforeFirst();
				 lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
				while(rs.next()) {
					vo2=new Inv_SampleCollectionVO();
                	HelperMethods.populateVOfrmRS(vo2, rs);
                	 lstInvSampleCollectionVO.add(vo2);
				}
			}
			
			
		}
		
		catch (Exception e)
		{		 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}
		return lstInvSampleCollectionVO;
	}

}
