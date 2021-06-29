package new_investigation.transactions.dao;



import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.invReportAddendumVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TriStringObject;


public class invReportAddendumDAO extends DataAccessObject
{




	public invReportAddendumDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}




	public List LabComboForResultValidation(ResultEntryVO InvResultEntryVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LAB_COMBO_FOR_RESULT_ENTRY_HIVT_LABORATORY_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
		populateMap.put(sq.next(),_UserVO.getUserSeatId());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());



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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return parameterCombo;
	}


	/*public List<ResultEntryVO> setPatientReportAddendumEssentials(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.REPORT_ADDENDUM_HIVT_REQUISITION_HEADER";
		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+InvResultEntryVO.getPatCRNo();
		String searchPatName=" And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like '%"+InvResultEntryVO.getTempPatName()+"%'";

	//	String condition1=" AND gnum_test_code ="+InvResultEntryVO.getTestWiseCode();
	//	String condition2="AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')>=regexp_replace('"+InvResultEntryVO.getFromSampleNo()+"','[^[:digit:]]','','g') AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')<=regexp_replace('"+InvResultEntryVO.getToSampleNo()+"','[^[:digit:]]','','g')";
	//	String condition3="AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')>=regexp_replace('"+InvResultEntryVO.getFromLabNo()+"','[^[:digit:]]','','g') AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')<=regexp_replace('"+InvResultEntryVO.getToLabNo()+"','[^[:digit:]]','','g')";
	//	String condition4=" AND hgnum_group_code ="+InvResultEntryVO.getTestGroupCode();

		String reqDtlValue="";
		String conditionLab="";

		//append order by confition in this string
		//		String orderByCondition=" order by hivt_entry_date,patCRNo,tempSampleNo";//,hivtnum_req_dno ";
		String orderByCondition=" order by hivdt_coll_date_time,tempSampleNo";//,hivtnum_req_dno ";
	
		
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
		

	

		if(InvResultEntryVO.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+InvResultEntryVO.getLabCode();
			
			
			
		
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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


			populateMAP.put(sq.next(),InvResultEntryVO.getFromDate());
			populateMAP.put(sq.next(),InvResultEntryVO.getToDate());
		//	populateMAP.put(sq.next(),InvResultEntryVO.getLabCode());



		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}

		PatientDetailVO[] dailyPatientVOs = null;
		List<ResultEntryVO> lstinvReportAddendumVO = new ArrayList<ResultEntryVO>();
		List<ResultEntryVO> finalLststinvReportAddendumVO = new ArrayList<ResultEntryVO>();
		ValueObject[] valueObjects = null;
		query+=conditionLab;
		query+=reqDtlValue;
		try
		{
			if(InvResultEntryVO.getPatCRNo()!=null&&!InvResultEntryVO.getPatCRNo().equals(""))
			{
				query+=condition5;
			}

			if(InvResultEntryVO.getTempPatName()!=null&&!InvResultEntryVO.getTempPatName().equals(""))
			{
				query+=searchPatName;
			}
			
			
			
			if(InvResultEntryVO.getGenerationType()!=null&&!InvResultEntryVO.getOnLoadValue().equals("ONLOAD"))
			{
			;
			}
			
			query+=orderByCondition;
			
			
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(ResultEntryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstinvReportAddendumVO.add((ResultEntryVO)valueObjects[i]);

					if(lstinvReportAddendumVO.get(i).getReqDtlStatus().equals(InvestigationConfig.READY_RESULTPRINTING) || lstinvReportAddendumVO.get(i).getReqDtlStatus().equals(InvestigationConfig.REPORT_PDF_GEN))
					{
						if(lstinvReportAddendumVO.get(i).getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
						{

							finalLststinvReportAddendumVO.add(lstinvReportAddendumVO.get(i));
							//status 13 and 26 added if its report is available after this process only
						}



					}
					else
						finalLststinvReportAddendumVO.add(lstinvReportAddendumVO.get(i)); // all other values added



				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
		}
		return finalLststinvReportAddendumVO;
	}*/	

	
	public List<ResultEntryVO> setPatientReportAddendumEssentials(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.RESULT_ADDENDUM_HIVT_REQUISITION_HEADER";
		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+InvResultEntryVO.getPatCRNo();
		String searchPatName=" And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like '%"+InvResultEntryVO.getTempPatName()+"%'";

		String condition1=" AND gnum_test_code ="+InvResultEntryVO.getTestWiseCode();
		String condition2="AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')>=regexp_replace('"+InvResultEntryVO.getFromSampleNo()+"','[^[:digit:]]','','g') AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')<=regexp_replace('"+InvResultEntryVO.getToSampleNo()+"','[^[:digit:]]','','g')";
		String condition3="AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')>=regexp_replace('"+InvResultEntryVO.getFromLabNo()+"','[^[:digit:]]','','g') AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')<=regexp_replace('"+InvResultEntryVO.getToLabNo()+"','[^[:digit:]]','','g')";
		String condition4=" AND hgnum_group_code ="+InvResultEntryVO.getTestGroupCode();

		String reqDtlValue="";
		String conditionLab="";

		//append order by confition in this string
		//		String orderByCondition=" order by hivt_entry_date,patCRNo,tempSampleNo";//,hivtnum_req_dno ";
		String orderByCondition=" order by hivdt_coll_date_time,tempSampleNo";//,hivtnum_req_dno ";
	
		//req dtl value division for new entry and modification
		if(InvResultEntryVO.getNewEntry()!=null&&InvResultEntryVO.getNewEntry().equals("2"))
		{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
		}

		else
			reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") ";
		
		if(InvResultEntryVO.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+InvResultEntryVO.getLabCode();
			
			
			
		
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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(),_UserVO.getUserSeatId());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			populateMAP.put(sq.next(),InvResultEntryVO.getFromDate());
			populateMAP.put(sq.next(),InvResultEntryVO.getToDate());
		//	populateMAP.put(sq.next(),InvResultEntryVO.getLabCode());



		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}

		PatientDetailVO[] dailyPatientVOs = null;
		List<ResultEntryVO> lstInvResultValidationVO = new ArrayList<ResultEntryVO>();
		List<ResultEntryVO> finalLststInvResultValidationVO = new ArrayList<ResultEntryVO>();
		ValueObject[] valueObjects = null;
		query+=conditionLab;
		query+=reqDtlValue;
		try
		{
			if(InvResultEntryVO.getPatCRNo()!=null&&!InvResultEntryVO.getPatCRNo().equals(""))
			{
				query+=condition5;
			}

			if(InvResultEntryVO.getTempPatName()!=null&&!InvResultEntryVO.getTempPatName().equals(""))
			{
				query+=searchPatName;
			}
			
			
			
			if(InvResultEntryVO.getGenerationType()!=null&&!InvResultEntryVO.getOnLoadValue().equals("ONLOAD"))
			{
				if(InvResultEntryVO.getGenerationType().equals("T")&&!InvResultEntryVO.getTestWiseCode().equals("-1"))
				{
					query+=condition1;
				}

				if(InvResultEntryVO.getGenerationType().equals("L")&&!InvResultEntryVO.getFromLabNo().equals("-1")&&!InvResultEntryVO.getToLabNo().equals("-1"))
				{

					query+=condition3;
				}

				if(InvResultEntryVO.getFromSampleNo()!=null && !InvResultEntryVO.getFromSampleNo().equals("-1") && !InvResultEntryVO.getToSampleNo().equals("-1") && InvResultEntryVO.getToSampleNo()!=null)
				{
					query+=condition2;

				}

				if(InvResultEntryVO.getGenerationType().equals("TG")&&!InvResultEntryVO.getTestGroupCode().equals("-1")&&!InvResultEntryVO.getTestGroupCode().equals(""))
				{
					query+=condition4;
				}
			}
			
			query+=orderByCondition;
			
			System.out.println("query:"+query);
			
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(ResultEntryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					//lstInvResultValidationVO.add((ResultEntryVO)valueObjects[i]);

					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					String seatidList = "";
					ResultEntryVO resVo = new ResultEntryVO();
					resVo = (ResultEntryVO)valueObjects[i];
					if(resVo.getAddendumType()!=null && resVo.getAddendumType().equals("1")){
							if(resVo.getIsAddendumEntryUser()!=null && resVo.getIsAddendumEntryUser().equals("1")){
								seatidList+= resVo.getResultEntrySeat() + "#";
							}
							if(resVo.getIsAddendumValidUser()!=null && resVo.getIsAddendumValidUser().equals("1")){
								seatidList+= resVo.getResultValSeat() + "#";
							}
							if(resVo.getIsAddendumRevalUser()!=null && resVo.getIsAddendumRevalUser().equals("1")){
								seatidList+= resVo.getResultRevalSeat() + "#";
							}
							
							if(seatidList.contains(_UserVO.getUserId())){
								lstInvResultValidationVO.add(resVo);
							}
							
					}else{
						lstInvResultValidationVO.add(resVo);
					}
					


				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
		}
		return lstInvResultValidationVO;
	}	

	public void updateResultValidationInRequisitionDtl(ResultEntryVO voResultValidation, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.REQDTLS.RESULT.VALIDATION.HIVT_REQUISITION_DTLS";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{

			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(),voResultValidation.getReqDtlStatus());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), voResultValidation.getRequisitionDNo());

			populateMAP.put(sq.next(), voResultValidation.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}



	public void insertResultValidationDtl(ResultEntryVO voResultValidation, UserVO _UserVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL";
		String queryKey_FILEUPLOAD ="UPDATE.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL_FILEUPLOAD";


		try {
			
			if(voResultValidation.getFileuploaddata()!=null && !voResultValidation.getFileuploaddata().equals(""))
				query = HelperMethodsDAO.getQuery(filename, queryKey_FILEUPLOAD);
			else
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {

			//populateMAP.put(sq.next(), voResultValidation.getResultEntryValue());
			String resultEntryvalue=voResultValidation.getResultEntryValue();
			resultEntryvalue=resultEntryvalue.replace("&lt;", "&amp;lt;");
			resultEntryvalue=resultEntryvalue.replace("&gt;", "&amp;gt;");
			
			
			populateMAP.put(sq.next(), (voResultValidation.getResultEntryValue().equals(""))?"--":resultEntryvalue);
			
                          populateMAP.put(sq.next(), _UserVO.getSeatId());
                          
                          
                          if(voResultValidation.getFileuploaddata()!=null && !voResultValidation.getFileuploaddata().equals(""))
              			{
              			populateMAP.put(sq.next(), voResultValidation.getFileuploaddata()==null?"":voResultValidation.getFileuploaddata()); 
              			populateMAP.put(sq.next(), voResultValidation.getFilename()==null?"":voResultValidation.getFilename()); 
              			}
                          

			//WHERE            
			populateMAP.put(sq.next(), voResultValidation.getParameterRequisitionDNo());

			populateMAP.put(sq.next(), voResultValidation.getTestCode()); 
			populateMAP.put(sq.next(), voResultValidation.getTestParaMeterCode());
			populateMAP.put(sq.next(), voResultValidation.getParantParaCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());



		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
		}

	}

	public List getResultValidfationDataList(ResultEntryVO InvResultEntryVO)
	{
		//String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();

		String query=null;
		ResultSet rs=null;

		Sequence sq = new Sequence();
		Map populateMap= new HashMap();

		List<TriStringObject> resultValidationList=new ArrayList<TriStringObject>();	
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.HIVT_PARAMETER_DTL.WORKORDERRESULTENTRYDATA.RESULTVALIDATIONPROCESS";			
		Connection conn = super.getTransactionContext().getConnection();	

		populateMap.put(sq.next(), InvResultEntryVO.getRequisitionDNo());
		populateMap.put(sq.next(), InvResultEntryVO.getHospitalcode());

		//   populateMap.put(sq.next(), hosCode);


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query :WorkOrder List For Result Validation "+query);
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try
		{


			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);


			while(rs.next())
			{

				String parentCode="";

				if(rs.getString(1).equals(rs.getString(3)))
					parentCode=rs.getString(3);
				else
					parentCode=rs.getString(3)+rs.getString(1);

				TriStringObject triStringObject=  new TriStringObject(rs.getString(1),rs.getString(2), parentCode);
				if(resultValidationList==null)
					resultValidationList=new ArrayList<TriStringObject>();


				resultValidationList.add(triStringObject);
			}

		}		
		catch(Exception e)
		{
			e.printStackTrace();
			throw  new HisDataAccessException("RESULT Validation :getResultValidfationDataList  :"+ e);
		}		
		return resultValidationList;


	}





	public void insertResultValidationDtlInJobWorkorderData(ResultEntryVO voResultEntry, UserVO _UserVO) {
       
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.REPORTCAHNGE.HIVT_JOBWORKORDER_DATA";
		
//<<<<<<< .mine
		

//	} catch (Exception e) {
//		throw new HisApplicationExecutionException(
//				"IcdGroupMasterDAO.populateMAP::" + e);
//	}
//	try {
//=======
//		
//>>>>>>> .r5884


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {





			populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
			//Need To Be ADD IS_VALID_ACTIVE
			populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
			populateMAP.put(sq.next(), InvestigationConfig.UPDATION_TYPE);
			populateMAP.put(sq.next(), InvestigationConfig.STATUS_CODE);
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
			populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
			populateMAP.put(sq.next(), voResultEntry.getTestCode());
			populateMAP.put(sq.next(), voResultEntry.getLabCode());
			populateMAP.put(sq.next(), voResultEntry.getSampleNo());
			populateMAP.put(sq.next(), voResultEntry.getPatAge());
			populateMAP.put(sq.next(), voResultEntry.getPatGender());

			populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode()==null?"":voResultEntry.getTestParaMeterCode()); 
			populateMAP.put(sq.next(), voResultEntry.getReportAvailableAfter());	        

			populateMAP.put(sq.next(), voResultEntry.getResultEntryValue()==null?"":voResultEntry.getResultEntryValue());
			populateMAP.put(sq.next(), voResultEntry.getPatVisitDat());
			if(voResultEntry.getPatVisitNo()!=null)
				if(voResultEntry.getPatVisitNo().equals("null"))
					voResultEntry.setPatVisitNo("");
			populateMAP.put(sq.next(), voResultEntry.getPatVisitNo());
			populateMAP.put(sq.next(), voResultEntry.getLabNo());
			populateMAP.put(sq.next(), voResultEntry.getEpisodeCode());
			populateMAP.put(sq.next(), voResultEntry.getDepartmentcode());
			populateMAP.put(sq.next(), voResultEntry.getPatdeptunitcode());
			populateMAP.put(sq.next(), voResultEntry.getRequisitionTypeCode());
			populateMAP.put(sq.next(), voResultEntry.getTestName());
			populateMAP.put(sq.next(), voResultEntry.getPatLabName());
			populateMAP.put(sq.next(), voResultEntry.getSampleName());
			populateMAP.put(sq.next(), voResultEntry.getTempSampleNo());

			if(voResultEntry.getDynamicTemplatePrintedGroup() != null && voResultEntry.getDynamicTemplatePrintedGroup().equals("1"))
	        {
	        	populateMAP.put(sq.next(), voResultEntry.getGroupCode());
	        }
	        else
	        {
	        	populateMAP.put(sq.next(), "");
	        }
			populateMAP.put(sq.next(), "1");
			populateMAP.put(sq.next(), voResultEntry.getReportUrl());


		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
		}

	}



	public String setComboValueName(String testCode, String paraCode, String paraEntry)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="CHECK_COMBO_VALUE.HIVT_TEST_PARAMETER_COMBO_MST";
		String record=null;
		try
		{
			int value= Integer.parseInt(paraEntry);
			paraEntry.valueOf(value);
		}
		catch(Exception e)
		{
			paraEntry="0";
		}

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return record;
		}
		try
		{
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);       
			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(),paraCode);
			populateMAP.put(sq.next(),paraEntry);

		}

		catch (Exception e)
		{
			return record;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);



			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			return record;
		}
		return record;



	}
	public String setRangeFlagValueName(String testCode,ResultEntryVO invresultentryvo,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="SELECT.HIVT_LABORATORY_TEST_MST.PRINT_WITH_STANDARD_RANGES";
		String record=null;


		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			return record;
		}
		try
		{

			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(),invresultentryvo.getLabCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());




		}

		catch (Exception e)
		{
			return record;
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

			if (!rs.next())
			{
				return record;
			}
			else
			{
				record=rs.getString(1);
			}
			/*while (rs.next())
			{
				record=rs.getString(1);
			}*/
		}
		catch (Exception e)
		{
			return record;
		}
		return record;




	}




	public List setLabNoComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List labNoCombo=new ArrayList();
		List finalLabNoCombo=new ArrayList();
		List<String> reportAvailAfter=new ArrayList<String>();
		List<String> reqStatus=new ArrayList<String>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.LABNO_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
		String orderBy="order by labNo";
		String reqDtlValue="";
		String conditionLab="";

		//req dtl value division for new entry and modification
		if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
		{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
		}

		else
			reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+") ";
		
		if(invresultentryvo.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
			
			
			
			

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
		//populateMAP.put(sq.next(),invresultentryvo.getLabCode());

		try
		{


			query = HelperMethodsDAO.getQuery(filename,queryKey);


		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		query+=conditionLab;
		query=query+reqDtlValue+orderBy;
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return labNoCombo;
			}
			else
			{
				rs.beforeFirst();
				labNoCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}


			rs.beforeFirst();
			while(rs.next())
			{ reportAvailAfter.add(rs.getString(3));
			reqStatus.add(rs.getString(4));
			}

			for (int i = 0; i < reportAvailAfter.size(); i++)
			{
				//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];

				if(reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
				{
					if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
					{	
						if(!finalLabNoCombo.contains(labNoCombo.get(i)))
						finalLabNoCombo.add(labNoCombo.get(i))	; 
						//status 13 and 26 added if its report is available after this process only
					}

				}
				else{
					if(!finalLabNoCombo.contains(labNoCombo.get(i)))
					finalLabNoCombo.add(labNoCombo.get(i))	; //add all other values
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return labNoCombo;
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return finalLabNoCombo;
	}


	public List setTestComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testCombo=new ArrayList();
		List finalTestCombo=new ArrayList();
		List<String> reportAvailAfter=new ArrayList<String>();
		List<String> reqStatus=new ArrayList<String>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTWISE_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
		String orderBy="order by testName";
		String reqDtlValue="";
		String conditionLab="";
		//req dtl value division for new entry and modification
		if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
		{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
		}

		else
			reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+") ";
		
		if(invresultentryvo.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
			
			
		
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());


		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
	//	populateMAP.put(sq.next(),invresultentryvo.getLabCode());



		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		query+=conditionLab;
		query=query+reqDtlValue+orderBy;
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return testCombo;
			}
			else
			{
				rs.beforeFirst();
				testCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}

			rs.beforeFirst();
			while(rs.next())
			{ reportAvailAfter.add(rs.getString(3));
			reqStatus.add(rs.getString(4));
			}

			for (int i = 0; i < reportAvailAfter.size(); i++)
			{
				//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];

				if(reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
				{
					if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
					{						
						finalTestCombo.add(testCombo.get(i))	; 
						//status 13 and 26 added if its report is available after this process only
					}

				}
				else{
					finalTestCombo.add(testCombo.get(i))	; //add all other values
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return testCombo;
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return finalTestCombo;
	}



	public List setSamplNoComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List sampleNoCombo=new ArrayList();
		List finalSampleNoCombo=new ArrayList();
		List<String> reportAvailAfter=new ArrayList<String>();
		List<String> reqStatus=new ArrayList<String>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_ADDENDUM.SAMPLENO_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
		String orderBy="order by sampleNo";
		String reqDtlValue="";
		String conditionLab="";
		//req dtl value division for new entry and modification
		if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
		{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
		}

		else
			reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") ";
		
		if(invresultentryvo.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
			
		
	
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(),invresultentryvo.getPatCRNo());
		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
		//populateMAP.put(sq.next(),invresultentryvo.getLabCode());



		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		query+=conditionLab;
		query=query+reqDtlValue+orderBy;

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return sampleNoCombo;
			}
			else
			{
				rs.beforeFirst();
				sampleNoCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			rs.beforeFirst();
			while(rs.next())
			{ reportAvailAfter.add(rs.getString(3));
			reqStatus.add(rs.getString(4));
			}

			for (int i = 0; i < reportAvailAfter.size(); i++)
			{
				//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];

				if(reqStatus.get(i)!=null && reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
				{
					if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
					{		
						if(!finalSampleNoCombo.contains(sampleNoCombo.get(i)))
						finalSampleNoCombo.add(sampleNoCombo.get(i))	; 
						//status 13 and 26 added if its report is available after this process only
					}

				}
				else{
					if(!finalSampleNoCombo.contains(sampleNoCombo.get(i)))
					finalSampleNoCombo.add(sampleNoCombo.get(i))	; //add all other values
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return sampleNoCombo;	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return finalSampleNoCombo;
	}

	//setTestGroupComboEssentials
	public List setTestGroupComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testGrpCombo=new ArrayList();
		List finalTestGroupCombo=new ArrayList();
		List<String> reportAvailAfter=new ArrayList<String>();
		List<String> reqStatus=new ArrayList<String>();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTGROUP_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
		String orderBy="order by groupName";
		String reqDtlValue="";
		String conditionLab="";
		//req dtl value division for new entry and modification
		if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
		{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_VALIDATION_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
		}

		else
			reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+") ";
		
		
		if(invresultentryvo.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
			
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
		//populateMAP.put(sq.next(),invresultentryvo.getLabCode());



		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		query+=conditionLab;

		query=query+reqDtlValue+orderBy;

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return testGrpCombo;
			}
			else
			{
				rs.beforeFirst();
				testGrpCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}

			rs.beforeFirst();
			while(rs.next())
			{ reportAvailAfter.add(rs.getString(3));
			reqStatus.add(rs.getString(4));
			}

			for (int i = 0; i < reportAvailAfter.size(); i++)
			{
				//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];

				if(reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
				{
					if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION))
					{						
						finalTestGroupCombo.add(testGrpCombo.get(i))	; 
						//status 13 and 26 added if its report is available after this process only
					}

				}
				else{
					finalTestGroupCombo.add(testGrpCombo.get(i))	; //add all other values
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return testGrpCombo;	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return finalTestGroupCombo;
	}



	public void insertResultLogDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.RESULT.LOG.DETAIL.HIVT_RESULT_LOG_DTL";


		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {



			populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
			populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
			populateMAP.put(sq.next(), voResultEntry.getParantParaCode());  
			populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());

			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
			populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
			populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());
            
           

			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), voResultEntry.getResultEntryValue());
			populateMAP.put(sq.next(), voResultEntry.getPreviousValue());

			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), InvestigationConfig.REPORT_ADDENDUM_PROCESS);
			
			populateMAP.put(sq.next(), voResultEntry.getPatName());
			populateMAP.put(sq.next(), voResultEntry.getLabCode());
			populateMAP.put(sq.next(), voResultEntry.getPatLabName());
			populateMAP.put(sq.next(), voResultEntry.getPatAge().trim());
			populateMAP.put(sq.next(), voResultEntry.getPatGender());
			populateMAP.put(sq.next(), voResultEntry.getTestName());
		//	populateMAP.put(sq.next(), voResultEntry.getRefRange());
			populateMAP.put(sq.next(), voResultEntry.getSampleNo());
			populateMAP.put(sq.next(), voResultEntry.getLabNo());
			populateMAP.put(sq.next(), voResultEntry.getRefRange());
			populateMAP.put(sq.next(), voResultEntry.getDetpUnitCode());
			populateMAP.put(sq.next(), voResultEntry.getDepartmentcode());
			populateMAP.put(sq.next(), voResultEntry.getRequisitionTypeCode());
			populateMAP.put(sq.next(), voResultEntry.getPatUnitName());
			populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
			populateMAP.put(sq.next(), voResultEntry.getTempSampleNo());
			populateMAP.put(sq.next(), voResultEntry.getSampleName());
			
			
			
 

			//populateMAP.put(sq.next(), voResultEntry.getRefRange()); 






		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in hivt_result_log_dtl Table");
		}

	}




public void updateFinalRemarkInRequisitionHeader(String reqNo,String finalRemark, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.FINALREMARK.HIVT_REQUISITION_HEADER";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
			
		
		populateMAP.put(sq.next(), finalRemark);
		  
		populateMAP.put(sq.next(), reqNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}

	//to get new entries

public ResultEntryVO getNewEntriesPatient(ResultEntryVO InvResultEntryVO,UserVO _UserVO)
{
	//String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();

	String query=null;
	ResultSet rs=null;

	Sequence sq = new Sequence();
	Map populateMap= new HashMap();

	List<TriStringObject> resultValidationList=new ArrayList<TriStringObject>();	
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL";			
	Connection conn = super.getTransactionContext().getConnection();	
	ResultEntryVO newEntryVO=new ResultEntryVO();
	
	populateMap.put(sq.next(), _UserVO.getHospitalCode());
	populateMap.put(sq.next(), InvResultEntryVO.getPatCRNo());




	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query :new patient entries "+query);
	} 
	catch (Exception e) 
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try
	{


		rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);


		while(rs.next())
		{

			 HelperMethods.populateVOfrmRS(newEntryVO, rs);
		}

	}		
	catch(Exception e)
	{
		e.printStackTrace();
		throw  new HisDataAccessException("RESULT Validation :getResultValidfationDataList  :"+ e);
	}		
	return newEntryVO;


}




public ResultEntryVO getOldEntriesPatient(ResultEntryVO InvResultEntryVO,UserVO _UserVO)
{
	//String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();

	String query=null;
	ResultSet rs=null;

	Sequence sq = new Sequence();
	Map populateMap= new HashMap();

	List<TriStringObject> resultValidationList=new ArrayList<TriStringObject>();	
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT_PATIENT_DETAILS.HIVT_REQUISITION_HEADER";			
	Connection conn = super.getTransactionContext().getConnection();	
	ResultEntryVO newEntryVO=new ResultEntryVO();
	
	populateMap.put(sq.next(), _UserVO.getHospitalCode());
	populateMap.put(sq.next(), InvResultEntryVO.getPatCRNo());


	

	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query :new patient entries "+query);
	} 
	catch (Exception e) 
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try
	{


		rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);


		while(rs.next())
		{

			 HelperMethods.populateVOfrmRS(newEntryVO, rs);
		}

	}		
	catch(Exception e)
	{
		e.printStackTrace();
		throw  new HisDataAccessException("RESULT Validation :getResultValidfationDataList  :"+ e);
	}		
	return newEntryVO;


}




//update values in header

public void updateDemographicsInRequisitionHeader(ResultEntryVO tempvo, ResultEntryVO newvo, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.DEMGRAPHICS.HIVT_REQUISITION_HEADER";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{	
		
		populateMAP.put(sq.next(), (newvo.getPatFirstName())+" "+(newvo.getPatMiddleName()==null?"":newvo.getPatMiddleName())+" "+(newvo.getPatLastName()==null?"":newvo.getPatLastName()));
		System.out.println( newvo.getPatFirstName()+" "+(newvo.getPatMiddleName()==null?"":newvo.getPatMiddleName())+" "+newvo.getPatLastName()==null?"":newvo.getPatLastName());
		populateMAP.put(sq.next(), newvo.getPatGenderCode());
		populateMAP.put(sq.next(), newvo.getPatAge());
			
		populateMAP.put(sq.next(), tempvo.getPatCRNo());
		populateMAP.put(sq.next(), tempvo.getRequisitionNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}






//update values in header

public void updateNewValuesInRequisitionDtl(ResultEntryVO tempvo, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.NEWVALUES.HIVT_REQUISITION_DTL";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{	
		
		populateMAP.put(sq.next(), tempvo.getReportChangeFlag());//1 demogrphics 3 addendum 2 amendment
		populateMAP.put(sq.next(), _UserVO.getSeatId());
		populateMAP.put(sq.next(), tempvo.getReqDtlStatus());		
		populateMAP.put(sq.next(), tempvo.getRequisitionDNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}




public void updateAddendumRemarkInRequisitionHeader(String reqNo,String addendumRemark, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.ADDENDUMREMARK.HIVT_REQUISITION_HEADER";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
			
		
		populateMAP.put(sq.next(), addendumRemark);
		  
		populateMAP.put(sq.next(), reqNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}




//ADDENDUM LOG DTL TABLE
public void insertAddendumLogDtl(ResultEntryVO voResultEntry, UserVO _UserVO,String reasonCode) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="INSERT.HIVT_ADDENDUM_LOG_DTL";


	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
		populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 
		populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 
		populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
		populateMAP.put(sq.next(), _UserVO.getSeatId()); 
		populateMAP.put(sq.next(), voResultEntry.getReportUrl());
		populateMAP.put(sq.next(), InvestigationConfig.REPORT_ADDENDUM_PROCESS);
		populateMAP.put(sq.next(), voResultEntry.getLabCode());
		populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
		populateMAP.put(sq.next(), voResultEntry.getTestCode());
		populateMAP.put(sq.next(), reasonCode);
		
		
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in hivt_result_log_dtl Table");
	}

}



public List getreasonlist(UserVO _UserVO)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List parameterCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT_REASON_LIST.HIVT_ADDENDUMDUM_REASON_MST";
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
		rs =  HelperMethodsDAO.executeQuery(conn, query);
		if (!rs.next())
		{
			throw new HisRecordNotFoundException();
		}
		else
		{
			rs.beforeFirst();
			parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
	return parameterCombo;
}



public void updateNewValuesInRequisitionDtll(invReportAddendumVO tempvo, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.NEWVALUES.HIVT_REQUISITION_DTL";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{	
		
		populateMAP.put(sq.next(), "4");//1 demogrphics 3 addendum 2 amendment 4-newtest
		populateMAP.put(sq.next(), _UserVO.getSeatId());
		populateMAP.put(sq.next(), "14");  // 7 for result entry		
		populateMAP.put(sq.next(), tempvo.getRequisitionDNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}




public void insertAddendumLogDtll(invReportAddendumVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="INSERT.HIVT_ADDENDUM_LOG_DTL";


	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
		populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 
		populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 
		populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
		populateMAP.put(sq.next(), _UserVO.getSeatId()); 
		populateMAP.put(sq.next(), "");
		populateMAP.put(sq.next(), InvestigationConfig.REPORT_ADDENDUM_PROCESS);
		populateMAP.put(sq.next(), voResultEntry.getLabCode());
		populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
		populateMAP.put(sq.next(), voResultEntry.getTestCode());
		populateMAP.put(sq.next(), "");
		
		
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in hivt_result_log_dtl Table");
	}

}





public void updatepidvaluesresult(ResultEntryVO voResultEntry, UserVO _UserVO,String hiv1,String hiv2,String status) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE_HIVT_ICTC_DTL_RESULT_VALUE_hiv1";
	String queryKey1 ="UPDATE_HIVT_ICTC_DTL_RESULT_VALUE_hiv2";
	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		
		
		if( hiv1!=null && (!hiv1.equals("") && !hiv1.equals("--")))
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		 populateMAP.put(sq.next(), hiv1);
		
		}
		
		if( hiv2!=null && (!hiv2.equals("") && !hiv2.equals("--")))
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey1);
		 populateMAP.put(sq.next(), hiv2);
		
		}

		     populateMAP.put(sq.next(), status);
			populateMAP.put(sq.next(), voResultEntry.getRequisitionNo()); 

	        /*
        populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
        //Need To Be ADD IS_VALID_ACTIVE
        populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
        populateMAP.put(sq.next(), voResultEntry.getParantParaCode());  
        populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), value);
		populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode()); 
		populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
		populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
		populateMAP.put(sq.next(), "0");
		//populateMAP.put(sq.next(), voResultEntry.getParameterOrder().equals("-")?"0":voResultEntry.getParameterOrder());
		//populateMAP.put(sq.next(), voResultEntry.getRefRange()); 

		
*/		
		
    	
        	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}




public void updateHyperLinkDetails(ResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.HIVT_WO_ORGAANTIBIOTICS_DTL1";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
		populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
        populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
        //Need To Be ADD IS_VALID_ACTIVE
     //   populateMAP.put(sq.next(), testparacode); 
        populateMAP.put(sq.next(),_UserVO.getHospitalCode());
   //     populateMAP.put(sq.next(),organismcode);
   //     populateMAP.put(sq.next(),antibioticCode);
       
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}





public void insertHyperLinkDetailsNew(ResultEntryVO voResultEntry, UserVO _UserVO,antibioticprocessVO voo) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="INSERT.RESULT.ENTRY.HYPERLINK.HIVT_WO_ORGAANTIBIOTICS_DTL";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
		populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
        populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
        //Need To Be ADD IS_VALID_ACTIVE
        populateMAP.put(sq.next(), voo.getTestParaCode()); 
        populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        populateMAP.put(sq.next(),voo.getOrganismcode());
        populateMAP.put(sq.next(),voo.getAntibioticcode());
        populateMAP.put(sq.next(), voo.getResult()); 
        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
        populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(),voo.getGrowth());
         populateMAP.put(sq.next(),voo.getGrowthname());
        populateMAP.put(sq.next(),voo.getRemark());

		//populateMAP.put(sq.next(), voResultEntry.getRefRange());
		

	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}


public void updateechodata(ResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.IN.HIVT_ECHO";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		
		String echoval=voResultEntry.getFinalechovalue()==null?"":voResultEntry.getFinalechovalue();
		
		if(echoval.contains("^^^"))
		{
			String[] echovar=echoval.split("\\^\\^\\^");
					
			System.out.println("val");
			
			 populateMAP.put(sq.next(), echovar[1]);
			 
			 populateMAP.put(sq.next(), echovar[0]);
		        //Need To Be ADD IS_VALID_ACTIVE
		        
		        
		}
		
		
       
       
		
		//populateMAP.put(sq.next(), voResultEntry.getParameterOrder().equals("-")?"0":voResultEntry.getParameterOrder());
		//populateMAP.put(sq.next(), voResultEntry.getRefRange()); 

		
		
		
    	
        	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}




//update values in header

public void updateDemographicsInRequisitionHeaderdtl(ResultEntryVO tempvo, ResultEntryVO newvo, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.DEMGRAPHICS.HIVT_REQUISITION_HEADER_DTL";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{	
		
		populateMAP.put(sq.next(), (newvo.getPatFirstName())+" "+(newvo.getPatMiddleName()==null?"":newvo.getPatMiddleName())+" "+(newvo.getPatLastName()==null?"":newvo.getPatLastName()));
		System.out.println( newvo.getPatFirstName()+" "+(newvo.getPatMiddleName()==null?"":newvo.getPatMiddleName())+" "+newvo.getPatLastName()==null?"":newvo.getPatLastName());
		populateMAP.put(sq.next(), newvo.getPatGenderCode());
		populateMAP.put(sq.next(), newvo.getPatAge());
			
		populateMAP.put(sq.next(), tempvo.getPatCRNo());
		populateMAP.put(sq.next(), tempvo.getRequisitionNo());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}





public void updateFinalRemarkInRequisitionHeaderdtl(String reqNo,String finalRemark, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.FINALREMARK.HIVT_REQUISITION_HEADER_DTL";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
			
		
		populateMAP.put(sq.next(), finalRemark);
		  
		populateMAP.put(sq.next(), reqNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}



public void updateAddendumRemarkInRequisitionHeaderdtl(String reqNo,String addendumRemark, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.ADDENDUMREMARK.HIVT_REQUISITION_HEADER_DTL";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
			
		
		populateMAP.put(sq.next(), addendumRemark);
		  
		populateMAP.put(sq.next(), reqNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}


}








