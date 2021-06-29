package registration.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.config.RegistrationConfig;
import vo.registration.BeneficiaryPatientVO;
import vo.registration.CrNoMergeDtlVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class CrNoMergeDAO extends RegistrationDAO{
	
	Logger log;

	public CrNoMergeDAO(TransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	public String countMergedOrMainCrNo(String strPatCrNo_p,UserVO objUserVO_p,String strMode)
	{
		String count="";
		int funcIndex=0;
		HisDAO objHisDAO_p=null;
		try 
		{
			objHisDAO_p = new HisDAO("Registration","CrNoMergeDAO");
			funcIndex = objHisDAO_p.setFunction("{? = call pkg_reg_util.fun_count_merged_main_crno(?,?,?,?)}");
			objHisDAO_p.setFuncInValue(funcIndex, 2, strMode);
			objHisDAO_p.setFuncInValue(funcIndex, 3, RegistrationConfig.CR_NUMBER_IS_MERGED_YES);
			objHisDAO_p.setFuncInValue(funcIndex, 4, Config.IS_VALID_ACTIVE);
			objHisDAO_p.setFuncInValue(funcIndex, 5, strPatCrNo_p);
			
			objHisDAO_p.setFuncOutValue(funcIndex,3);
			
			objHisDAO_p.executeFuncForNumeric(funcIndex);
			count = objHisDAO_p.getFuncNumeric(funcIndex)+"";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("CrNoMergeDAO.countMergedCrNo()" + e);
		}
		finally 
		{
			if (objHisDAO_p != null) 
			{
				objHisDAO_p.free();
				objHisDAO_p = null;
			}
		}
		return count;
	}
	
	
	public void saveNotUsedCrNo(HisDAO daoObj,CrNoMergeDtlVO crNoMergeDtlVO,UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			strProcName2 = "{call pkg_reg_dml.dml_hpmrt_crno_merge_dtl(?,?,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, 		?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(crNoMergeDtlVO);		
			
			daoObj.setProcInValue(nProcIndex2, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_maincrno",crNoMergeDtlVO.getPatMainCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_crno", crNoMergeDtlVO.getPatNotUsedCrNo(),3);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_ismerged", crNoMergeDtlVO.getIsMerged(),4);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_reason", crNoMergeDtlVO.getReason(),5);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_remarks", crNoMergeDtlVO.getRemarks(),6);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_isrevoked", RegistrationConfig.CR_NUMBER_IS_MERGED_REVOKED,7);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isvalid", Config.IS_VALID_ACTIVE,8);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_seat_id", objUserVO_p.getSeatId(),9);
			daoObj.setProcInValue(nProcIndex2, "p_hosp_code", objUserVO_p.getHospitalCode(),10);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,11);
									
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("CrNoMergeDAO.countMergedCrNo()" + e);

		}		
		finally 
		{
			
		}
	}
	
	
	public List getMergedCrNo(String _crNo_p,  UserVO objUserVO_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HPMRT_CRNO_MERGE_DTL(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr="",strMode = "1";
		try 
		{
		daoObj = new HisDAO("Registration","CrNoMergeDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_modeval", strMode,1);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_crno", _crNo_p,3);
		daoObj.setProcInValue(nProcIndex, "p_ismerged", RegistrationConfig.CR_NUMBER_IS_MERGED_YES,4);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,5);
		daoObj.setProcOutValue(nProcIndex, "err", 1,6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}

		List alRecord = new ArrayList();
		try {
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Merged CRNos Not Found");
			} else {
				rs.beforeFirst();
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("CrNoMergeDAO:getMergedCrNo:HelperMethods :: " + e);
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
	
	public void revokeMergedCRNo(HisDAO daoObj,String reason,String mainCrNo,String crNo,UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			strProcName2 = "{call pkg_reg_dml.dml_hpmrt_crno_merge_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
			nProcIndex2 = daoObj.setProcedure(strProcName2);			
				
			daoObj.setProcInValue(nProcIndex2, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_maincrno",mainCrNo,2);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_crno", crNo,3);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_ismerged", RegistrationConfig.CR_NUMBER_IS_MERGED_YES,4);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_reason", reason,5);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_remarks", "",6);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_isrevoked", RegistrationConfig.CR_NUMBER_IS_MERGED_REVOKED,7);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isvalid", Config.IS_VALID_ACTIVE,8);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_seat_id", objUserVO_p.getSeatId(),9);
			daoObj.setProcInValue(nProcIndex2, "p_hosp_code", objUserVO_p.getHospitalCode(),10);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,11);
			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("CrNoMergeDAO.revokeMergedCRNo()" + e);

		}		
		finally 
		{
			
		}
	}
	
	public void updatePatientMergeStatus(HisDAO daoObj,CrNoMergeDtlVO crNoMergeDtlVO,UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			
			strProcName2 = "{call pkg_reg_dml.dml_hpmrt_crno_merge_dtl(?,?,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, 		?::character varying,?::character varying,?::character varying,?::timestamp without time zone,?::character varying,		?,?,?)}";


			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(crNoMergeDtlVO);		
			
					
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_isvalid", Config.IS_VALID_ACTIVE,7);
			daoObj.setProcInValue(nProcIndex2, "p_hrgnum_seat_id", objUserVO_p.getSeatId(),8);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,9);
			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("CrNoMergeDAO.updatePatientMergeStatus()" + e);

		}		
		finally 
		{
			
		}
	}
	
	

}
