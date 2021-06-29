package registration.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import vo.registration.MlcParameterDtlVO;
import vo.registration.MlcVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class MlcParameterDAO extends DataAccessObject
{
	public MlcParameterDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}	
	
	public void saveMlcParameterDtl(String strMode_p, HisDAO objHisDAO_p, MlcParameterDtlVO objMlcParameterDtlVO_p, UserVO objUserVO_p,String strModeExecuteProc_p)
	{
		//String queryKey = "INSERT.HRGT_PATIENT_MLC_DTL";
    	String strErr = "";
		int nProcIndex1 = 0, index=1;
		String strProcName1="";
		try 
		{
			System.out.println("----------------------------------------");
			System.out.println("MlcParameterDAO :: saveMlcParameterDtl()");
			////////////////////////////////////////////////////////   /***************** 1-5 **********************/		/** 6-10 ********/		/******** 11-15 ***********/	/** 16-20 **/	
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_mlc_parameter_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,		?,?,?,?::numeric,?,		?::numeric,?::numeric,?,?,?,	?,?,?,?,?)}";
			
			HelperMethods.setNullToEmpty(objMlcParameterDtlVO_p);
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", strMode_p, index++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objMlcParameterDtlVO_p.getPatCrNo(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objMlcParameterDtlVO_p.getEpisodeCode(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no", objMlcParameterDtlVO_p.getMlcParamSerialNo(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objMlcParameterDtlVO_p.getEpisodeVisitNo(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_poision_remark", objMlcParameterDtlVO_p.getPoisonRemarks(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_burn_percentage", objMlcParameterDtlVO_p.getBurnPercentage(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_remarks", objMlcParameterDtlVO_p.getStrRemarks(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date", objMlcParameterDtlVO_p.getEntryDate(), index++); 
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid", objMlcParameterDtlVO_p.getIsValid(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", objMlcParameterDtlVO_p.getMlcNo(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_mlc_case_type", objMlcParameterDtlVO_p.getMlcTypeCode(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_injury_nature_code", objMlcParameterDtlVO_p.getInjuryNatureCode(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_weapon_type", objMlcParameterDtlVO_p.getTypeOfWeapon(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_injury_size", objMlcParameterDtlVO_p.getInjurySize(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_injury_depth", objMlcParameterDtlVO_p.getInjuryDepth(), index++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_disease_site_id", objMlcParameterDtlVO_p.getInjurySide(), index++); 
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,index++);
			
			
			
			
			////////////////////// 	Printing Values on Console	////////////////////////////////
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+ objMlcParameterDtlVO_p.getPatCrNo()); 
			System.out.println("p_hrgnum_episode_code :"+ objMlcParameterDtlVO_p.getEpisodeCode()); 
			System.out.println("p_hrgnum_s_no :"+ objMlcParameterDtlVO_p.getMlcParamSerialNo()); 
			System.out.println("p_hrgnum_visit_no :"+ objMlcParameterDtlVO_p.getEpisodeVisitNo()); 
			System.out.println("p_hrgstr_poision_remark :"+ objMlcParameterDtlVO_p.getPoisonRemarks()); 
			System.out.println("p_hrgnum_burn_percentage :"+ objMlcParameterDtlVO_p.getBurnPercentage()); 
			System.out.println("p_hrgstr_remarks :"+ objMlcParameterDtlVO_p.getStrRemarks()); 
			System.out.println("p_gnum_seat_id :"+ objUserVO_p.getSeatId()); 
			System.out.println("p_gdt_entry_date :"+ objMlcParameterDtlVO_p.getEntryDate()); 
			System.out.println("p_gnum_isvalid :"+ objMlcParameterDtlVO_p.getIsValid()); 
			System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode()); 
			System.out.println("p_hrgstr_mlc_no :"+ objMlcParameterDtlVO_p.getMlcNo()); 
			System.out.println("p_hrgnum_mlc_case_type :"+ objMlcParameterDtlVO_p.getMlcTypeCode()); 
			System.out.println("p_hrgnum_injury_nature_code :"+ objMlcParameterDtlVO_p.getInjuryNatureCode()); 
			System.out.println("p_hrgstr_weapon_type :"+ objMlcParameterDtlVO_p.getTypeOfWeapon()); 
			System.out.println("p_hrgstr_injury_size :"+ objMlcParameterDtlVO_p.getInjurySize()); 
			System.out.println("p_hrgstr_injury_depth :"+ objMlcParameterDtlVO_p.getInjuryDepth()); 
			System.out.println("p_hgnum_disease_site_id :"+ objMlcParameterDtlVO_p.getInjurySide()); 
			//////////////////////////////////////////////////////////////////////////////////////
			
			
			if(!strModeExecuteProc_p.isEmpty() && strModeExecuteProc_p.equals("1")){
				objHisDAO_p.execute(nProcIndex1,1);
			}else{
				objHisDAO_p.executeProcedureByPosition(nProcIndex1);
				strErr = objHisDAO_p.getString(nProcIndex1, "err");
			}
			
			System.out.println("----------------------------------------");
			if (!strErr.isEmpty()) 
			{
				throw new Exception(strErr);
			}
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<MlcParameterDtlVO> getMlcParameterDtlList( MlcParameterDtlVO objMlcParameterDtlVO_p, 
			UserVO objUserVO_p,	String strMode_p) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_mlc_parameter_dtl(?,?,?,?,?, ?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		List<MlcParameterDtlVO> lstMlcParameterDtlVO=new ArrayList<MlcParameterDtlVO>();
		try 
		{
			daoObj = new HisDAO("Registration","MlcParameterDAO");
			HelperMethods.setNullToEmpty(objMlcParameterDtlVO_p);
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", objMlcParameterDtlVO_p.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_episode_code", objMlcParameterDtlVO_p.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_no", objMlcParameterDtlVO_p.getEpisodeVisitNo(),5);
			daoObj.setProcInValue(nProcIndex, "p_hrgstr_mlc_no", objMlcParameterDtlVO_p.getMlcNo(),6);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_mlc_case_type", objMlcParameterDtlVO_p.getMlcTypeCode(),7);
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(e.getMessage());
		}

		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No MLC Parameter Detail Found");
			}
			else
			{
				rs.beforeFirst();
				while(rs.next()){
					MlcParameterDtlVO vo = new MlcParameterDtlVO();
					HelperMethods.populateVOfrmRS(vo, rs);
					lstMlcParameterDtlVO.add(vo);
				}
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("MlcParameterDAO: getMlcParameterDtlList :: " + e);
		}
		return lstMlcParameterDtlVO;
	}
}
