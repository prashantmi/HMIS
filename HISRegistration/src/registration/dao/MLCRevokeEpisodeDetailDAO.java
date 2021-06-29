package registration.dao;

import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import vo.registration.MlcToNonMlcVO;

public class MLCRevokeEpisodeDetailDAO extends RegistrationDAO {
	
	public MLCRevokeEpisodeDetailDAO(TransactionContext tx) {
		super(tx);
	}

	public void saveMlcRevokeDtl(HisDAO objHisDAO_p,MlcToNonMlcVO objMlcToNonMlcVO_p, UserVO objUserVO_p,String strMode_p) 
	{

		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		int nIndex=1;
		
		try 
		{
			/**************************************************/   /*** 1-5 ***/	/*** 6-10 **/
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_mlc_revoke_dtl(?,?,?,?,?,	?,?,?,?,?)}";


			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);			
			HelperMethods.setNullToEmpty(objMlcToNonMlcVO_p);
			
			System.out.println("---------------------------------------------");
			System.out.println("EpisodeDAO :: MLCRevokeEpisodeDetailDAO()");
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_mode", strMode_p,	nIndex++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objMlcToNonMlcVO_p.getPatCrNo(), nIndex++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objMlcToNonMlcVO_p.getEpisodeCode(),nIndex++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objMlcToNonMlcVO_p.getEpisodeVisitNo(),	nIndex++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_remarks", objMlcToNonMlcVO_p.getRemarks(),	nIndex++);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add :",objUserVO_p.getIpAddress(), nIndex++);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid :",objMlcToNonMlcVO_p.getIsValid(), nIndex++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(), nIndex++); 
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(), nIndex++); 
			
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,nIndex++);

			
			//objHisDAO_p.executeProcedureByPosition(nProcIndex1);
			objHisDAO_p.execute(nProcIndex1,1);	
			
			
			///////////////
			System.out.println("p_mode :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+objMlcToNonMlcVO_p.getPatCrNo());
			System.out.println("p_hrgnum_episode_code :"+ objMlcToNonMlcVO_p.getEpisodeCode());
			System.out.println("p_hrgnum_visit_no :"+ objMlcToNonMlcVO_p.getEpisodeVisitNo());
			System.out.println("p_hrgstr_remarks :"+ objMlcToNonMlcVO_p.getRemarks());
			System.out.println("p_hrgstr_ip_add : "+ objUserVO_p.getIpAddress());
			System.out.println("p_gnum_isvalid : "+ objMlcToNonMlcVO_p.getIsValid()); 
			
			System.out.println("p_gnum_seat_id :"+ objUserVO_p.getSeatId()); 
			System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode()); 
			
			System.out.println("-----------------------------------------");
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
}
