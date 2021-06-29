package mrd.transaction.dao;

	
	import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.WebRowSet;
import mrd.MrdConfig;
import mrd.vo.CertificateBEntryFormReqVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

	public class CertificateBEntryFormDAO  extends DataAccessObject {

		public CertificateBEntryFormDAO(TransactionContext _tx) {
			super(_tx);
		}

		
		public List getRecordType(UserVO _userVO)
		{

			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
			String queryKey = "GET_RECORD_TYPE.HPMRT_MRD_RECORDTYPE_MST";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException(
						"No Record Found  ");
			}
			catch (Exception e)
			{

				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{

				throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
			}

			return alRecord;
		}
		
		public List getEmpList(UserVO _userVO)
		{

			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
			String queryKey = "GET_EMP_LIST.PIST_EMP_REGISTRATION_DTL";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.PROCESS_ID_FOR_CERTIFICATE_B_ENTRY_FORM_REQUEST);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
			

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException(
						" ");
			}
			catch (Exception e)
			{

				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{

				throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
			}

			return alRecord;
		}
		/**
		 * Get Bill No Status for Certificate B Request
		 * 
		 * @param hisDAO_p
		 * @param UserVO
		 * @return
		 * @throws Exception
		 * @author manisha gangwar on 20-oct-2015
		 */
		public String getBillNoQtyDtl(CertificateBEntryFormReqVO certBReqVO,UserVO userVO) 
		{
			String count = "";
			ResultSet rs = null;
			String errorMsg="";
			String billNoQty;		
			try
			{
				Procedure strProc=new Procedure(MrdConfig.MEDICAL_CERTIFICATE_ONLINE_BILL_DTL);
				strProc.addInParameter(1,Types.VARCHAR,userVO.getHospitalCode());
				/*strProc.addInParameter(2,Types.VARCHAR,certBReqVO.getRecordType());*/
				strProc.addInParameter(2,Types.VARCHAR,"1");
			    strProc.addInParameter(3,Types.VARCHAR,"8");
			    strProc.addInParameter(4,Types.VARCHAR,certBReqVO.getCertificateId()); 
				strProc.addInParameter(5,Types.VARCHAR,"0");
				strProc.setReturnType(Types.VARCHAR);
				return	billNoQty = (String)strProc.execute(super.getTransactionContext().getConnection());
			}
			catch(HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
		}
		
		/**
		 * List all the Certificate B Requests
		 * 
		 * @param hisDAO_p
		 * @param UserVO
		 * @return
		 * @throws Exception
		 * @author manisha gangwar on 19-oct-2015
		 */
		
		public CertificateBEntryFormReqVO[] getCertificateBEntryFormDtl(UserVO strUserVO) 
		{
			CertificateBEntryFormReqVO[] certBEntryVOs = null;
			ValueObject[] valueObjects = null;
			WebRowSet rs = null;
			HisDAO daoObj = null;
			String strProcName = "{call pkg_mrd_view.proc_hpmrt_certificate_b_entry_form(?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";
			try 
			{
			daoObj = new HisDAO("MRD","CertificateBEntryFormDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "req_status", MrdConfig.REQUESTED,1);
			daoObj.setProcInValue(nProcIndex, "isvalid", Config.IS_VALID_ACTIVE,2);	
			daoObj.setProcInValue(nProcIndex, "hoscode", strUserVO.getHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err",1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			finally{
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
			}
			try
			{
				
				if (!rs.next())
				{
					//certBEntryVOs = new CertificateBEntryFormReqVO[0];
					throw new HisRecordNotFoundException("No Request Record Detail Found");
					
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(CertificateBEntryFormReqVO.class, rs);
					certBEntryVOs = new CertificateBEntryFormReqVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						certBEntryVOs[i] = (CertificateBEntryFormReqVO) valueObjects[i];
					}
				}
				
				
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
			}
			return certBEntryVOs;
		}
		
		/**
		 * Inserting/Update/Delete Certificate B 
		 * 
		 * @param hisDAO_p
		 * @param strMode_p 1-Insert, 2-Update
		 * @param voCertBEntryVO_p
		 * @param voUser_p
		 * @return
		 * @throws Exception
		 * @author manisha gangwar on 14-oct-2015
		 */
		public void dml(HisDAO hisDAO_p, String strMode_p, CertificateBEntryFormReqVO voCertBEntryVO_p, UserVO voUser_p) throws Exception
		{
			String errorMsg = null;
			int nProcedureIndex;
			
			//String recordStatus=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER;
			//String strDBErr;
			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_CERTIFICATE_B_ENTRY_FORM_REQUEST);

				// Setting and Registering In and Out Parameters 
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_puk", (voCertBEntryVO_p.getPatCrNo()==null) ? "":voCertBEntryVO_p.getPatCrNo(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_certificate_id", (voCertBEntryVO_p.getCertificateId()==null) ? "":voCertBEntryVO_p.getCertificateId(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_episode_code", (voCertBEntryVO_p.getEpisodeCode()==null) ? "":voCertBEntryVO_p.getEpisodeCode(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no", "",5);
				//hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no", (voCertBEntryVO_p.getEpisodeVisitNo()==null) ? "":voCertBEntryVO_p.getEpisodeVisitNo(),5);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_request_status", MrdConfig.REQUESTED,6);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_no", (voCertBEntryVO_p.getEmpNo()==null) ? "":voCertBEntryVO_p.getEmpNo(),7);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_admission_no", (voCertBEntryVO_p.getPatAdmNo()==null) ? "":voCertBEntryVO_p.getPatAdmNo(), 8);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_bill_no", (voCertBEntryVO_p.getBillNo()==null) ? "":voCertBEntryVO_p.getBillNo(),9);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE,10);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_handover_to", (voCertBEntryVO_p.getHandoverTo()==null) ? "":voCertBEntryVO_p.getHandoverTo(),11);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_handover_to_name", (voCertBEntryVO_p.getHandoverToName()==null) ? "":voCertBEntryVO_p.getHandoverToName(),12);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (voCertBEntryVO_p.getSeatId()==null) ? "":voCertBEntryVO_p.getSeatId(),13);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_handover_to_relation", (voCertBEntryVO_p.getHandoverToRel()==null) ? "":voCertBEntryVO_p.getHandoverToRel(),14);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),15);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_handover_to_address", (voCertBEntryVO_p.getHandoverToAddress()==null) ? "":voCertBEntryVO_p.getHandoverToAddress(),16);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_handover_to_contact", (voCertBEntryVO_p.getHandoverToContact()==null) ? "":voCertBEntryVO_p.getHandoverToContact(),17);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_ip_add", (voCertBEntryVO_p.getSystemIPAddress()==null) ? "":voCertBEntryVO_p.getSystemIPAddress(),18);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", voUser_p.getSeatId(),19);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_lstmod_date", (voCertBEntryVO_p.getLastModifyDate()==null) ? "":voCertBEntryVO_p.getLastModifyDate(),20);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_lstmod_seatid", (voCertBEntryVO_p.getSeatId()==null) ? "":voCertBEntryVO_p.getSeatId(),21);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_remarks", (voCertBEntryVO_p.getRemarks()==null) ? "":voCertBEntryVO_p.getRemarks(),22);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_patPrimaryCatCode", (voCertBEntryVO_p.getPatPrimaryCatCode()==null) ? "":voCertBEntryVO_p.getPatPrimaryCatCode(),23);
				

						
				hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 24); // varchar

				// Executing Procedure 
				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				// Getting out parameters not in case of Batch Processing 
//				strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
	//
//				// If Database Error Occurs, No farther processing is required. 
//				if (strDBErr != null && !strDBErr.equals(""))
//				{
//					throw new Exception("Data Base Error:" + strDBErr);
//				}
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			finally{
				if (hisDAO_p != null) {
					hisDAO_p.free();
					
				}
				hisDAO_p = null;
			}
		}
		
		
	}
