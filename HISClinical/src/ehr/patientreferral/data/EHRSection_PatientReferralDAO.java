/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.patientreferral.data;
import hisglobal.backutil.HisDAO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import opd.OpdConfig;
import ehr.EHRConfig;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import emr.vo.PatientClinicalDocDetailVO;



public class EHRSection_PatientReferralDAO extends DataAccessObject 
{

	
		public EHRSection_PatientReferralDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}
				
		//Added by Vasu on 21.May.2019
		public List<EHRSection_PatientReferralVO> getPatientReferralDetails(UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
		{
			ResultSet rs = null;
			String errorMsg="";
			
			try
			{
				Procedure strProc=new Procedure(EHRConfig.PROC_FOR_PAT_REFERRAL_DATA);
				strProc.addInParameter(1,Types.VARCHAR,"2");
				strProc.addInParameter(2,Types.VARCHAR,selectedPatientVO.getDepartmentCode());
				strProc.addInParameter(3,Types.VARCHAR,selectedPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(4,Types.VARCHAR,selectedPatientVO.getWardCode());
				strProc.addInParameter(5,Types.VARCHAR,userVO.getSeatId());  
				
				strProc.addInParameter(6,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(7,Types.VARCHAR,selectedPatientVO.getPatCrNo());
				strProc.addInParameter(8,Types.VARCHAR,selectedPatientVO.getEpisodeCode());
				strProc.addInParameter(9,Types.VARCHAR,selectedPatientVO.getEpisodeVisitNo());
				strProc.addInParameter(10,Types.VARCHAR,selectedPatientVO.getAddmissionNo());
				
				strProc.addInParameter(11,Types.VARCHAR,clinicalDocVO.getDocumentType());
				strProc.addInParameter(12,Types.VARCHAR,clinicalDocVO.getClinicalSectionCode());
				
				strProc.addOutParameter(13,Types.VARCHAR);
				strProc.addOutParameter(14,Types.REF);//OracleTypes.CURSOR);
				
				
				strProc.execute(super.getTransactionContext().getConnection());
				
				errorMsg=(String) strProc.getParameterAt(13);
				rs=(ResultSet) strProc.getParameterAt(14);
			}
			catch(HisRecordNotFoundException e)
			{
				e.printStackTrace();
				//throw new HisRecordNotFoundException("No Record Found");
			}
			catch (HisException e)
			{
				//throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			List<EHRSection_PatientReferralVO> lstPatReferralDtlVO = new ArrayList<EHRSection_PatientReferralVO>();
			ValueObject[] valueObjects = null;
			try
			{
				if(rs!=null){
					if(rs.next())
					{
						rs.beforeFirst();
						valueObjects = HelperMethods.populateVOfrmRS(EHRSection_PatientReferralVO.class, rs);
						for (int i = 0; i < valueObjects.length; i++)
							lstPatReferralDtlVO.add((EHRSection_PatientReferralVO) valueObjects[i]);
					}
			}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					/*if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					else throw new HisDataAccessException("PatientDocumentDetailDAO:getEpisodePatientDocuments::Patient Episode Documents:: " + e);*/
				}
			return lstPatReferralDtlVO;
		}

		//Added by Vasu on 12.Dec.2019
		public List getPatientReferralDepartments(String crNo,UserVO userVO,String unitType)
		{

			WebRowSet webRs = null;
			HisDAO daoObj = null;
			List alRecord = new ArrayList();
			String strProcName = "{call PKG_REG_VIEW.proc_referral_dept_cmb(?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";
			
			try 
			{
				daoObj = new HisDAO("Registration","EssentialDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "p_modeVal", "2",1);//from 1 to 2 by mukund on 04.01.18
				daoObj.setProcInValue(nProcIndex, "p_hosp_code",userVO.getHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "p_crno",crNo,3);
				daoObj.setProcInValue(nProcIndex, "p_unit_type",unitType,4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
				if(webRs.size()<=0)
					throw new HisRecordNotFoundException("No Depts for Referral Found");
				if (strErr.equals("")) 
				{
					alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
				} 
				else 
				{
					throw new Exception(strErr);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException( e.getMessage());
			} 
			finally 
			{
				if (daoObj != null) 
				{
					daoObj.free();
					daoObj = null;
				}
			}		
			return alRecord;
		}
		
      //Added by Vasu on 16.Dec.2019
		public void savePatientReferralDetails(EHRSection_PatientReferralVO ehrSection_PatientReferralVO,PatientDetailVO selectedPatientVO, UserVO userVO)
		{
			HisDAO daoObj = null;
			String strProcName = " {call pkg_ehr_dml.proc_Insert_hrgt_episode_ref_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex;
			String strErr = "";
			WebRowSet rs = null;

			try
			{
				// String pmode="1";
				daoObj = new HisDAO("Registration", "EssentialDAO");
				nProcIndex = daoObj.setProcedure(strProcName);


				daoObj.setProcInValue(nProcIndex, "pmode", "1", 1);
				daoObj.setProcInValue(nProcIndex, "patcr", selectedPatientVO.getPatCrNo(), 2);
				daoObj.setProcInValue(nProcIndex, "episodecode", selectedPatientVO.getEpisodeCode(), 3);
				daoObj.setProcInValue(nProcIndex, "episodevisitcode", selectedPatientVO.getEpisodeVisitNo(), 4);
				daoObj.setProcInValue(nProcIndex, "adm_num", (selectedPatientVO.getAddmissionNo()==null) ? "":selectedPatientVO.getAddmissionNo(), 5);
				daoObj.setProcInValue(nProcIndex, "dept_code", selectedPatientVO.getDepartmentCode(), 6);
				
				daoObj.setProcInValue(nProcIndex, "dep_unit_code", selectedPatientVO.getDepartmentUnitCode(), 7);
				
				daoObj.setProcInValue(nProcIndex, "seatid", userVO.getSeatId(), 8);
				
				daoObj.setProcInValue(nProcIndex, "hospitalcode", userVO.getHospitalCode(), 9);
				daoObj.setProcInValue(nProcIndex, "ref_dept_code", ehrSection_PatientReferralVO.getReferdeptCode(),10);
				daoObj.setProcInValue(nProcIndex, "ref_dept_unit_code", ehrSection_PatientReferralVO.getReferDeptUnitCode(),11);
				daoObj.setProcInValue(nProcIndex, "ref_type_code", ehrSection_PatientReferralVO.getReferTypeCode(),12);

				daoObj.setProcInValue(nProcIndex, "ref_reason", ehrSection_PatientReferralVO.getReferReason(),13);


				daoObj.setProcOutValue(nProcIndex, "err", 1,14);

				daoObj.executeProcedureByPosition(nProcIndex);
				// Getting out parameters 
				strErr = daoObj.getString(nProcIndex, "err");
			

			}
			catch (Exception e)
			{
				throw new HisDataAccessException((strErr != null ? "" : strErr) + e);
			}
		}

		//Added by Vasu on 18.Dec.2019
		public void deleteReferralDetails(EHRSection_PatientReferralVO referVO,UserVO userVO, PatientDetailVO ptaientDetailVO)
		{
			
			String query = "";
			Map populateMAP = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "REVOKE_HRGT_EPISODE_REF_DTL";
			Sequence sq = new Sequence();

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			try
			{
				
				populateMAP.put(sq.next(), ptaientDetailVO.getPatCrNo());
				populateMAP.put(sq.next(), ptaientDetailVO.getEpisodeCode());
				populateMAP.put(sq.next(), ptaientDetailVO.getEpisodeVisitNo());
				populateMAP.put(sq.next(), referVO.getSlNo());
				populateMAP.put(sq.next(), userVO.getHospitalCode());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DeskMenuMacroMasterDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
			
}











