/**
##		Date					: 05-Aug-2019
##		Reason	(CR/PRS)		: External Investigation Section at SPD 
##		Created By				: Vasu
*/



package ehr.externalinvestigation.data;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
/*import hisglobal.vo.FormAReportVO;
import hisglobal.vo.FormCReportVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.QualityControlMstVO;
import hisglobal.vo.QualityParameterMappingVO;
import hisglobal.vo.RefreshmentItemMstVO;
import hisglobal.vo.ScreeningOfOtherTTDReportVO;
import hisglobal.vo.TherapeuticPatientDtlVO;*/
//import hisglobal.vo.TherapeuticTypeMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
//import hisglobal.vo.VoluntaryCardDtlVO;










import java.sql.Types;

import org.apache.log4j.Logger;

import ehr.EHRConfig;
import ehr.examination.vo.EHRSection_ExaminationVO;
import ehr.externalinvestigation.vo.EHRSection_ExternalInvestigationVO;
import opd.OpdConfig;
import opd.dao.OpdDaoConfig;

public class EHRSection_ExternalInvestigationDAO extends DataAccessObject 
{

	
		public EHRSection_ExternalInvestigationDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}
		
		Logger log;




		public List getParameterForExtInv(UserVO userVO)
		{
			
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "GET_PARA_LIST_TEMP_CAT_WISE.HGBT_PARAMETER_MST";
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			//log.error(query + "\n");

			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), GenericTemplateConfig.TEMPLATE_GROUP_CLINICAL);
			//populateMAP.put(sq.next(), GenericTemplateConfig.PARAMETER_TYPE_VITAL_MONITORING);

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException("No Parameter Found  ");
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




		public List getTestNamesForExtInv(UserVO userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "GET_TEST_NAMES_LIST.HIVT_TEST_MST";
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			//log.error(query + "\n");

			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), GenericTemplateConfig.TEMPLATE_GROUP_CLINICAL);
			//populateMAP.put(sq.next(), GenericTemplateConfig.PARAMETER_TYPE_VITAL_MONITORING);

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException("No Parameter Found  ");
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




		public EHRSection_ExternalInvestigationVO[] getAddedExtInvestigationData(PatientDetailVO selectedPatientVO, UserVO userVO)
		{
			EHRSection_ExternalInvestigationVO[] arrAddedExtInvVO=null;
			ValueObject vo[]= null;
			String query  = "";
	        Map populateMAP =new HashMap();
	        Sequence sq=new Sequence();
	        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
	        String queryKey="SELECT_ADDED_EXT_INV.HRGT_EPISODE_EXTINV_DTL_SPD";
	        
	        try
	        {
	        	query =HelperMethodsDAO.getQuery(filename,queryKey);
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	        }
	        
	       // populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	        populateMAP.put(sq.next(), selectedPatientVO.getPatCrNo());
	       // populateMAP.put(sq.next(), selectedPatientVO.getEpisodeCode());
	        //populateMAP.put(sq.next(), selectedPatientVO.getEpisodeVisitNo());
	        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	        populateMAP.put(sq.next(), userVO.getHospitalCode());
	        
	        
	        try
	     	{
	     		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 	 	    /*if(!rs.next())
	 	 	    {
	 	 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
	 	 	    }*/
	     		if(rs!=null){
	 	 	    rs.beforeFirst();
	 	 	   
	 	 	    vo=HelperMethods.populateVOfrmRS(EHRSection_ExternalInvestigationVO.class, rs);
	 	 	    arrAddedExtInvVO= new EHRSection_ExternalInvestigationVO[vo.length];
	 	 	    for(int i=0;i<vo.length;i++)
	 	 	    {
	 	 	    	arrAddedExtInvVO[i]= (EHRSection_ExternalInvestigationVO) vo[i];
	 	 	    }
	     		}
	 	 	}
	 		catch(Exception e)
	 		{
	 			if(e.getClass()==HisRecordNotFoundException.class)
	 			{
	 				throw new HisRecordNotFoundException(e.getMessage());	
	 			}
	 			else	
	 				e.printStackTrace();
	 			// throw new HisDataAccessException("Application Execution Exception"+e.printStackTrace());			 
	 		}
	 		return arrAddedExtInvVO;
		}




		public void saveExtInvestigationDetail(String pmode,EHRSection_ExternalInvestigationVO vo,PatientDetailVO selectedPatientVO, UserVO userVO)
		{
			
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_ExternalInvestigationDAO");
			String strDBErr;
			ResultSet objResSet;
			
			try
			{
								
			nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_EXT_INV_DETAILS);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_puk", (selectedPatientVO.getPatCrNo()==null) ? "":selectedPatientVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (selectedPatientVO.getEpisodeCode()==null) ? "": selectedPatientVO.getEpisodeCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no",  (selectedPatientVO.getEpisodeVisitNo()==null) ? "":selectedPatientVO.getEpisodeVisitNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_admissionno", (selectedPatientVO.getAddmissionNo()==null) ? "":selectedPatientVO.getAddmissionNo(),5);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_entry_date",(vo.getSelRecordDate()==null) ? "": vo.getSelRecordDate(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_record_date",(vo.getSelRecordDate()+" "+vo.getRecordTime()==null) ? "": vo.getSelRecordDate()+" "+vo.getRecordTime(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_test_name",(vo.getSelTestName()==null) ? "": vo.getSelTestName(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_test_code",(vo.getSelTestCode()==null) ? "": vo.getSelTestCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_para_name",(vo.getSelParaName()==null) ? "": vo.getSelParaName(),10);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_para_id",(vo.getSelParaCode()==null) ? "": vo.getSelParaCode(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_para_value",(vo.getSelParaValue()==null) ? "": vo.getSelParaValue(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_lab",(vo.getTestConductedFrom()==null) ? "": vo.getTestConductedFrom(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_lab_name",(vo.getExtLabName()==null) ? "": vo.getExtLabName(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_lab_add",(vo.getExtLabAdd()==null) ? "": vo.getExtLabAdd(),15);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_lab_contact",(vo.getExtLabContactNo()==null) ? "": vo.getExtLabContactNo(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id",(userVO.getSeatId()==null) ? "": userVO.getSeatId(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hosp_code",(userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_valid",Config.IS_VALID_ACTIVE,19);
			
			
			hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,20); // varchar
			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		
			
			// If Database Error Occurs, No further processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("EHRSection_TreatmentDAO_Create::hisDAO_p.execute" + EHRConfig.SAVE_EXT_INV_DETAILS
						+ ") -> " + e.getMessage());
			}
			finally{
				if (hisDAO_p != null) {
					hisDAO_p.free();
				}
				hisDAO_p = null;
			}
		}




		public void deletePreviousExtInvestigations(EHRSection_ExternalInvestigationVO eHRExtInvDetailsVO,UserVO userVO)
		{
			
			String query = "";
			Map populateMAP = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "DELETE_HRGT_EPISODE_EXTEXAM_DTL_SPD";
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
				
				populateMAP.put(sq.next(), eHRExtInvDetailsVO.getPatCrNo());
				populateMAP.put(sq.next(), eHRExtInvDetailsVO.getEpisodeCode());
				populateMAP.put(sq.next(), eHRExtInvDetailsVO.getEpisodeVisitNo());
				populateMAP.put(sq.next(), eHRExtInvDetailsVO.getSlno());
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
		
		
		

	} //END Class






