package registration.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.PatientVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientVisitSUP;

public class EpisodeRefDtlDAO extends RegistrationDAO{

	public EpisodeRefDtlDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	public EpisodeRefDtlVO saveEpisodeRefDtl(HisDAO objDAO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p, UserVO objUserVO_p,String strMode_p) {
		
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			HelperMethods.setNullToEmpty(objEpisodeRefDtlVO_p);
			if(objEpisodeRefDtlVO_p.getSerialNo().equals(""))
				objEpisodeRefDtlVO_p.setSerialNo("1");
			System.out.println("**************************************");
			System.out.println("EpisodeRefDtlDAO :: saveEpisodeRefDtl()");
			///////////////////////
			System.out.println("p_hrgnum_puk :"+(objEpisodeRefDtlVO_p.getPatCrNo().equals("")?"0":objEpisodeRefDtlVO_p.getPatCrNo()));
			System.out.println("p_hrgnum_episode_code :"+ (objEpisodeRefDtlVO_p.getEpisodeCode().equals("")?"0": objEpisodeRefDtlVO_p.getEpisodeCode()));
			System.out.println("p_hrgnum_visit_no :"+ (objEpisodeRefDtlVO_p.getEpisodeVisitNo().equals("")?"1": objEpisodeRefDtlVO_p.getEpisodeVisitNo()));
			System.out.println("p_hrgnum_s_no :"+objEpisodeRefDtlVO_p.getSerialNo());
			System.out.println("p_hrgnum_admission_no :"+(objEpisodeRefDtlVO_p.getPatAdmNo().equals("")?"0":objEpisodeRefDtlVO_p.getPatAdmNo()));
			System.out.println("p_hrgnum_from_dept_code :"+(objEpisodeRefDtlVO_p.getFromDepartmentCode().equals("")?"0":objEpisodeRefDtlVO_p.getFromDepartmentCode()));
			System.out.println("p_hgnum_from_deptunitcode :"+(objEpisodeRefDtlVO_p.getFromDepartmentUnitCode().equals("")?"0":objEpisodeRefDtlVO_p.getFromDepartmentUnitCode()));
			System.out.println("p_hrgstr_from_doct_code :"+objEpisodeRefDtlVO_p.getFromDoctorCode());
			System.out.println("p_hrgnum_from_ward_code :"+(objEpisodeRefDtlVO_p.getFromWardCode().equals("")?"0":objEpisodeRefDtlVO_p.getFromWardCode()));
			if(objEpisodeRefDtlVO_p.getToDepartmentCode().length()>3)
			{
			System.out.println("p_hrgnum_to_dept_code :"+(objEpisodeRefDtlVO_p.getToDepartmentCode().substring(0,3).equals("")?"0":objEpisodeRefDtlVO_p.getToDepartmentCode().substring(0,3)));
			}else{
				System.out.println("p_hrgnum_to_dept_code :"+(objEpisodeRefDtlVO_p.getToDepartmentCode().equals("")?"0":objEpisodeRefDtlVO_p.getToDepartmentCode()));	
			}
			System.out.println("p_hgnum_to_deptunitcode :"+(objEpisodeRefDtlVO_p.getToDepartmentUnitCode().equals("")?"0":objEpisodeRefDtlVO_p.getToDepartmentUnitCode()));
			System.out.println("p_hrgstr_to_doct_code :"+objEpisodeRefDtlVO_p.getToDoctorCode());
			System.out.println("p_hrgnum_to_ward_code :"+(objEpisodeRefDtlVO_p.getToWardCode().equals("")?"0":objEpisodeRefDtlVO_p.getToWardCode()));
			System.out.println("p_hrgstr_to_episode_code :"+(objEpisodeRefDtlVO_p.getToEpisodeCode().equals("")?"0":objEpisodeRefDtlVO_p.getToEpisodeCode()));
			System.out.println("p_hrgnum_to_visit_no :"+(objEpisodeRefDtlVO_p.getToEpisodeVisitNo().equals("")?"0":objEpisodeRefDtlVO_p.getToEpisodeVisitNo()));
			System.out.println("p_hrgnum_ext_hospital_code :"+(objEpisodeRefDtlVO_p.getExternalHospitalCode().equals("")?"0":objEpisodeRefDtlVO_p.getExternalHospitalCode()));
			System.out.println("p_hrgstr_ext_hospital_name :"+objUserVO_p.getStrHospitalMstVo().getHospitalName());
			System.out.println("p_hrgstr_ext_hosp_doct_name :"+objEpisodeRefDtlVO_p.getExternalHospitalDoctorName());
			System.out.println("p_hrgnum_ext_hospital_crno :"+(objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo().equals("")?"0":objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo()));
			System.out.println("p_hrgnum_ext_hospital_dept :"+objEpisodeRefDtlVO_p.getExternalHospitalDepartment());
			System.out.println("p_hrgnum_ext_hosp_dept_unit :"+objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit());
			System.out.println("p_hrgstr_is_reff_in_out :"+(objEpisodeRefDtlVO_p.getIsRefferInOut().equals("")?"0":objEpisodeRefDtlVO_p.getIsRefferInOut()));
			System.out.println("p_hrgstr_is_reff_in_out_ext :"+RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL);	// for reffer date
			System.out.println("p_hrgstr_is_reff_in_out_int :"+RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);	// for reffer date
			System.out.println("p_hrgstr_is_reff_acc_ext :"+RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);	// for acceptance date
			System.out.println("p_hrgstr_is_reff_acc_int :"+RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);	// for acceptance date
			System.out.println("p_hrgdt_reff_date_time :"+"");
			System.out.println("p_gdt_acceptance_date :"+"");
			System.out.println("p_hrgstr_ip_add :"+objUserVO_p.getIpAddress());
			System.out.println("p_gnum_seat_id :"+(objUserVO_p.getSeatId().equals("")?"0":objUserVO_p.getSeatId()));
			System.out.println("p_gdt_entry_date :"+"");
			System.out.println("p_gnum_isvalid :"+"1");
			System.out.println("p_gdt_lstmod_date :"+objEpisodeRefDtlVO_p.getLastModifiedDate());
			System.out.println("p_gnum_lstmod_seatid :"+(objEpisodeRefDtlVO_p.getLastModifiedSeatId().equals("")?"0":objEpisodeRefDtlVO_p.getLastModifiedSeatId()));
			System.out.println("p_gstr_remarks :"+objEpisodeRefDtlVO_p.getRemarks());
			System.out.println("p_gnum_hospital_code :"+(objUserVO_p.getHospitalCode().equals("")?"0":objUserVO_p.getHospitalCode()));
			////////////////////////
			
			
																		 /*******************   5   ******************	   *******************   10   ******************	*******************   15   ******************	 *************   20   ***************	 *************   25   ***************	  *********   30   *********	************   35   ***************	    *****   38  ****/
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_episode_refer_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,   ?::numeric,?::numeric,?::numeric,?,?::numeric,   ?::numeric,?::numeric,?,?::numeric,?::numeric,   ?::numeric,?::numeric,?,?,?::numeric,   ?,?,?::numeric,?::numeric,?::numeric,   ?::numeric,?::numeric,?,?,?,   ?::numeric,?,?::numeric,?,?::numeric,   ?,?::numeric,?)}";
			
			nProcIndex1 = objDAO_p.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(objEpisodeRefDtlVO_p);
			
			objDAO_p.setProcInValue(nProcIndex1, "p_modeVal", strMode_p,1);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objEpisodeRefDtlVO_p.getPatCrNo().equals("")?"0":objEpisodeRefDtlVO_p.getPatCrNo(),2);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objEpisodeRefDtlVO_p.getEpisodeCode().split("###")[0] ,3);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objEpisodeRefDtlVO_p.getEpisodeVisitNo().equals("")?"1": objEpisodeRefDtlVO_p.getEpisodeVisitNo(),4);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no",objEpisodeRefDtlVO_p.getSerialNo(),5);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no",objEpisodeRefDtlVO_p.getPatAdmNo(),6);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code",objEpisodeRefDtlVO_p.getFromDepartmentCode(),7);
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode",objEpisodeRefDtlVO_p.getFromDepartmentUnitCode(),8);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code",objEpisodeRefDtlVO_p.getFromDoctorCode(),9);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code",objEpisodeRefDtlVO_p.getFromWardCode(),10);
			if(objEpisodeRefDtlVO_p.getToDepartmentCode().length()>3)
			{
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",objEpisodeRefDtlVO_p.getToDepartmentCode().substring(0,3),11);
			}else{
				objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",objEpisodeRefDtlVO_p.getToDepartmentCode(),11);	
			}
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",objEpisodeRefDtlVO_p.getToDepartmentUnitCode() ,12);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code",objEpisodeRefDtlVO_p.getToDoctorCode(),13);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code",objEpisodeRefDtlVO_p.getToWardCode(),14);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",objEpisodeRefDtlVO_p.getToEpisodeCode(),15);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",objEpisodeRefDtlVO_p.getToEpisodeVisitNo(),16);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code",objEpisodeRefDtlVO_p.getExternalHospitalCode(),17);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name",objUserVO_p.getStrHospitalMstVo().getHospitalName(),18);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name",objEpisodeRefDtlVO_p.getExternalHospitalDoctorName(),19);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno",objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo(),20);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept",objEpisodeRefDtlVO_p.getExternalHospitalDepartment(),21);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit",objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit(),22);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out",objEpisodeRefDtlVO_p.getIsRefferInOut().equals("")?"0":objEpisodeRefDtlVO_p.getIsRefferInOut(),23);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL,24);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL,25);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL,26);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL,27);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","",28);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","",29);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",(objUserVO_p.getIpAddress()==null||objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()),30);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",objUserVO_p.getSeatId(),31);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",32);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",33);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_lstmod_date",objEpisodeRefDtlVO_p.getLastModifiedDate(),34);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid",objEpisodeRefDtlVO_p.getLastModifiedSeatId(),35);
			objDAO_p.setProcInValue(nProcIndex1, "p_gstr_remarks",objEpisodeRefDtlVO_p.getRemarks(),36);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),37);
			//objDAO_p.setProcInValue(nProcIndex1, "p_gnum_referMode",objEpisodeRefDtlVO_p.getReferMode(),38);
			objDAO_p.setProcOutValue(nProcIndex1, "err", 1,38);
			
			objDAO_p.execute(nProcIndex1,1);	
			//objDAO_p.executeProcedureByPosition(nProcIndex1);			
			
			//strErr = objDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(objDAO_p.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(objDAO_p.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}	 	
	 	return objEpisodeRefDtlVO_p;
		
		
	}
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the patients referred from other hospital
	 * date : 7th oct 2016 */
public EpisodeRefDtlVO saveExtEpisodeRefDtl(HisDAO objDAO_p, PatientVO objPatientVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p, UserVO objUserVO_p,String strMode_p) {
		
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			HelperMethods.setNullToEmpty(objEpisodeRefDtlVO_p);
			if(objEpisodeRefDtlVO_p.getSerialNo().equals(""))
				objEpisodeRefDtlVO_p.setSerialNo("1");
			System.out.println("**************************************");
			System.out.println("EpisodeRefDtlDAO :: saveEpisodeRefDtl()");
			///////////////////////
			System.out.println("p_hrgnum_puk :"+(objEpisodeRefDtlVO_p.getPatCrNo().equals("")?"0":objEpisodeRefDtlVO_p.getPatCrNo()));
			System.out.println("p_hrgnum_episode_code :"+ (objEpisodeRefDtlVO_p.getEpisodeCode().equals("")?"0": objEpisodeRefDtlVO_p.getEpisodeCode()));
			System.out.println("p_hrgnum_visit_no :"+ (objEpisodeRefDtlVO_p.getEpisodeVisitNo().equals("")?"0": objEpisodeRefDtlVO_p.getEpisodeVisitNo()));
			System.out.println("p_hrgnum_s_no :"+objEpisodeRefDtlVO_p.getSerialNo());
			System.out.println("p_hrgnum_admission_no :"+(objEpisodeRefDtlVO_p.getPatAdmNo().equals("")?"0":objEpisodeRefDtlVO_p.getPatAdmNo()));
			System.out.println("p_hrgnum_from_dept_code :"+(objEpisodeRefDtlVO_p.getFromDepartmentCode().equals("")?"0":objEpisodeRefDtlVO_p.getFromDepartmentCode()));
			System.out.println("p_hgnum_from_deptunitcode :"+(objEpisodeRefDtlVO_p.getFromDepartmentUnitCode().equals("")?"0":objEpisodeRefDtlVO_p.getFromDepartmentUnitCode()));
			System.out.println("p_hrgstr_from_doct_code :"+objEpisodeRefDtlVO_p.getFromDoctorCode());
			System.out.println("p_hrgnum_from_ward_code :"+(objEpisodeRefDtlVO_p.getFromWardCode().equals("")?"0":objEpisodeRefDtlVO_p.getFromWardCode()));
			if(objEpisodeRefDtlVO_p.getToDepartmentCode().length()>3)
			{
			System.out.println("p_hrgnum_to_dept_code :"+(objEpisodeRefDtlVO_p.getToDepartmentCode().substring(0,3).equals("")?"0":objEpisodeRefDtlVO_p.getToDepartmentCode().substring(0,3)));
			}else{
				System.out.println("p_hrgnum_to_dept_code :"+(objEpisodeRefDtlVO_p.getToDepartmentCode().equals("")?"0":objEpisodeRefDtlVO_p.getToDepartmentCode()));	
			}
			System.out.println("p_hgnum_to_deptunitcode :"+(objEpisodeRefDtlVO_p.getToDepartmentUnitCode().equals("")?"0":objEpisodeRefDtlVO_p.getToDepartmentUnitCode()));
			System.out.println("p_hrgstr_to_doct_code :"+objEpisodeRefDtlVO_p.getToDoctorCode());
			System.out.println("p_hrgnum_to_ward_code :"+(objEpisodeRefDtlVO_p.getToWardCode().equals("")?"0":objEpisodeRefDtlVO_p.getToWardCode()));
			System.out.println("p_hrgstr_to_episode_code :"+(objEpisodeRefDtlVO_p.getToEpisodeCode().equals("")?"0":objEpisodeRefDtlVO_p.getToEpisodeCode()));
			System.out.println("p_hrgnum_to_visit_no :"+(objEpisodeRefDtlVO_p.getToEpisodeVisitNo().equals("")?"0":objEpisodeRefDtlVO_p.getToEpisodeVisitNo()));
			System.out.println("p_hrgnum_ext_hospital_code :"+(objEpisodeRefDtlVO_p.getExternalHospitalCode().equals("")?"0":objEpisodeRefDtlVO_p.getExternalHospitalCode()));
			System.out.println("p_hrgstr_ext_hospital_name :"+objPatientVO_p.getStrHospCode());
			//System.out.println("p_refer_mode :"+objEpisodeRefDtlVO_p.getReferMode());
			System.out.println("p_hrgstr_ext_hosp_doct_name :"+objEpisodeRefDtlVO_p.getExternalHospitalDoctorName());
			System.out.println("p_hrgnum_ext_hospital_crno :"+(objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo().equals("")?"0":objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo()));
			System.out.println("p_hrgnum_ext_hospital_dept :"+objEpisodeRefDtlVO_p.getExternalHospitalDepartment());
			System.out.println("p_hrgnum_ext_hosp_dept_unit :"+objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit());
			System.out.println("p_hrgstr_is_reff_in_out :"+(objEpisodeRefDtlVO_p.getIsRefferInOut().equals("")?"0":objEpisodeRefDtlVO_p.getIsRefferInOut()));
			System.out.println("p_hrgstr_is_reff_in_out_ext :"+RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL);	// for reffer date
			System.out.println("p_hrgstr_is_reff_in_out_int :"+RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);	// for reffer date
			System.out.println("p_hrgstr_is_reff_acc_ext :"+RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);	// for acceptance date
			System.out.println("p_hrgstr_is_reff_acc_int :"+RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);	// for acceptance date
			System.out.println("p_hrgdt_reff_date_time :"+"");
			System.out.println("p_gdt_acceptance_date :"+"");
			System.out.println("p_hrgstr_ip_add :"+objUserVO_p.getIpAddress());
			System.out.println("p_gnum_seat_id :"+(objEpisodeRefDtlVO_p.getSeatId().equals("")?"0":objEpisodeRefDtlVO_p.getSeatId()));
			System.out.println("p_gdt_entry_date :"+"");
			System.out.println("p_gnum_isvalid :"+"1");
			System.out.println("p_gdt_lstmod_date :"+objEpisodeRefDtlVO_p.getLastModifiedDate());
			System.out.println("p_gnum_lstmod_seatid :"+objUserVO_p.getSeatId());
			System.out.println("p_gstr_remarks :"+objEpisodeRefDtlVO_p.getRemarks());
			//System.out.println("p_gnum_referMode :"+objEpisodeRefDtlVO_p.getReferMode());
			System.out.println("p_gnum_hospital_code :"+(objPatientVO_p.getStrHospCode()));
			////////////////////////
			
			
																		 /*******************   5   ******************	   *******************   10   ******************	*******************   15   ******************	 *************   20   ***************	 *************   25   ***************	  *********   30   *********	************   35   ***************	    *****   38  ****/
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_episode_refer_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,   ?::numeric,?::numeric,?::numeric,?,?::numeric,   ?::numeric,?::numeric,?,?::numeric,?::numeric,   ?::numeric,?::numeric,?,?,?::numeric,   ?,?,?::numeric,?::numeric,?::numeric,   ?::numeric,?::numeric,?,?,?,   ?::numeric,?,?::numeric,?,?::numeric,   ?,?::numeric,?)}";
			
			nProcIndex1 = objDAO_p.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(objEpisodeRefDtlVO_p);
			
			objDAO_p.setProcInValue(nProcIndex1, "p_modeVal", strMode_p,1);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objEpisodeRefDtlVO_p.getPatCrNo().equals("")?"0":objEpisodeRefDtlVO_p.getPatCrNo(),2);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objPatientVO_p.getEpisodeCode() ,3);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", "1",4);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no",objEpisodeRefDtlVO_p.getSerialNo(),5);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no",objEpisodeRefDtlVO_p.getPatAdmNo(),6);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code",objEpisodeRefDtlVO_p.getFromDepartmentCode(),7);
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode",objEpisodeRefDtlVO_p.getFromDepartmentUnitCode(),8);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code",objEpisodeRefDtlVO_p.getFromDoctorCode(),9);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code",objEpisodeRefDtlVO_p.getFromWardCode(),10);
			if(objEpisodeRefDtlVO_p.getToDepartmentCode().length()>3)
			{
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",objEpisodeRefDtlVO_p.getToDepartmentCode().substring(0,3),11);
			}else{
				objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",objPatientVO_p.getDepartmentCode(),11);	
			}
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",objEpisodeRefDtlVO_p.getToDepartmentUnitCode() ,12);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code",objEpisodeRefDtlVO_p.getToDoctorCode(),13);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code",objEpisodeRefDtlVO_p.getToWardCode(),14);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",objEpisodeRefDtlVO_p.getEpisodeCode(),15);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",objEpisodeRefDtlVO_p.getEpisodeVisitNo(),16);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code",objPatientVO_p.getStrHospCode(),17);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name",objUserVO_p.getStrHospitalMstVo().getHospitalName(),18);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name",objEpisodeRefDtlVO_p.getExternalHospitalDoctorName(),19);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno",objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo(),20);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept",objEpisodeRefDtlVO_p.getExternalHospitalDepartment(),21);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit",objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit(),22);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out",objEpisodeRefDtlVO_p.getIsRefferInOut().equals("")?"0":objEpisodeRefDtlVO_p.getIsRefferInOut(),23);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL,24);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL,25);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL,26);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL,27);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","",28);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","",29);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",(objUserVO_p.getIpAddress()==null||objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()),30);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",objEpisodeRefDtlVO_p.getSeatId().equals("")?"0":objEpisodeRefDtlVO_p.getSeatId(),31);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",32);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",33);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_lstmod_date",objEpisodeRefDtlVO_p.getLastModifiedDate(),34);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid",objUserVO_p.getSeatId(),35);
			objDAO_p.setProcInValue(nProcIndex1, "p_gstr_remarks",objEpisodeRefDtlVO_p.getRemarks(),36);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objPatientVO_p.getStrHospCode(),37);
			//objDAO_p.setProcInValue(nProcIndex1, "p_gnum_referMode",objEpisodeRefDtlVO_p.getReferMode(),38);
			objDAO_p.setProcOutValue(nProcIndex1, "err", 1,38);
			
			objDAO_p.execute(nProcIndex1,1);	
			//objDAO_p.executeProcedureByPosition(nProcIndex1);			
			
			//strErr = objDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(objDAO_p.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(objDAO_p.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}	 	
	 	return objEpisodeRefDtlVO_p;
		
		
	}
	//end

	public EpisodeRefDtlVO createNewRegistration(HisDAO objDAO_p,EpisodeRefDtlVO objEpisodeRefDtlVO_p, UserVO objUserVO_p) {
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_refer_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = objDAO_p.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(objEpisodeRefDtlVO_p);
			objEpisodeRefDtlVO_p.setSeatId(objUserVO_p.getSeatId());
			
			objDAO_p.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objEpisodeRefDtlVO_p.getPatCrNo(),2);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",objEpisodeRefDtlVO_p.getEpisodeCode(),3);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",objEpisodeRefDtlVO_p.getEpisodeVisitNo(),4);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no","1",5);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no",objEpisodeRefDtlVO_p.getPatAdmNo(),6);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code",objEpisodeRefDtlVO_p.getFromDepartmentCode(),7);
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode",objEpisodeRefDtlVO_p.getFromDepartmentUnitCode(),8);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code",objEpisodeRefDtlVO_p.getFromDoctorCode(),9);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code",objEpisodeRefDtlVO_p.getFromWardCode(),10);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",objEpisodeRefDtlVO_p.getToDepartmentCode(),11);
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",objEpisodeRefDtlVO_p.getToDepartmentUnitCode(),12);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code",objEpisodeRefDtlVO_p.getToDoctorCode(),13);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code",objEpisodeRefDtlVO_p.getToWardCode(),14);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",objEpisodeRefDtlVO_p.getToEpisodeCode(),15);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",objEpisodeRefDtlVO_p.getToEpisodeVisitNo(),16);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code",objEpisodeRefDtlVO_p.getExternalHospitalCode(),17);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name",objEpisodeRefDtlVO_p.getExternalHospitalName(),18);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name",objEpisodeRefDtlVO_p.getExternalHospitalDoctorName(),19);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno",objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo(),20);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept",objEpisodeRefDtlVO_p.getExternalHospitalDepartment(),21);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit",objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit(),22);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out",objEpisodeRefDtlVO_p.getIsRefferInOut(),23);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL,24);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL,25);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL,26);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL,27);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","",28);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","",29);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",objUserVO_p.getIpAddress(),30);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",objEpisodeRefDtlVO_p.getSeatId(),31);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",32);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",33);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_lstmod_date",objEpisodeRefDtlVO_p.getLastModifiedDate(),34);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid",objEpisodeRefDtlVO_p.getLastModifiedSeatId(),35);
			objDAO_p.setProcInValue(nProcIndex1, "p_gstr_remarks",objEpisodeRefDtlVO_p.getRemarks(),36);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),37);
			objDAO_p.setProcOutValue(nProcIndex1, "err", 1,38);
			
			
			objDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(objDAO_p.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(objDAO_p.getString(nProcIndex1, "patAge"));
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objEpisodeRefDtlVO_p;
		
		
	}
	
	
	
		public void populateMap(EpisodeRefDtlVO objEpisodeRefDtlVO_p,UserVO objUserVO_p,Map _populateMap)throws SQLException{
			Sequence sequence=new Sequence();
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getPatCrNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEpisodeCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEpisodeVisitNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getPatCrNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEpisodeCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEpisodeVisitNo());
			_populateMap.put(sequence.next(),objUserVO_p.getHospitalCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getPatAdmNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromDepartmentCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromDepartmentUnitCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromDoctorCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromWardCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToDepartmentCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToDepartmentUnitCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToDoctorCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToWardCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToEpisodeCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToEpisodeVisitNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalName());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalDoctorName());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalDepartment());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),objUserVO_p.getIpAddress());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getSeatId());
			//_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEntryDate());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsValid());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getLastModifiedDate());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getLastModifiedSeatId());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getRemarks());
			_populateMap.put(sequence.next(),objUserVO_p.getHospitalCode());
			}

		public void populateMapNewRegistration(EpisodeRefDtlVO objEpisodeRefDtlVO_p,UserVO objUserVO_p,Map _populateMap)throws SQLException{
			Sequence sequence=new Sequence();
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getPatCrNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEpisodeCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEpisodeVisitNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getPatAdmNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromDepartmentCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromDepartmentUnitCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromDoctorCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getFromWardCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToDepartmentCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToDepartmentUnitCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToDoctorCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToWardCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToEpisodeCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getToEpisodeVisitNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalCode());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalName());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalDoctorName());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalDepartment());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),objUserVO_p.getIpAddress());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getSeatId());
			//_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getEntryDate());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getIsValid());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getLastModifiedDate());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getLastModifiedSeatId());
			_populateMap.put(sequence.next(),objEpisodeRefDtlVO_p.getRemarks());
			_populateMap.put(sequence.next(),objUserVO_p.getHospitalCode());
			}

		
	public int updateRefEpisodeDtlAtREG(HisDAO objDAO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p, UserVO objUserVO_p)
	{
		//String queryKey = "Update.HRGT_EPISODE_REF_DTL";
		int i=0;
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_refer_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = objDAO_p.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(objEpisodeRefDtlVO_p);
			
			/*UPDATE  HRGT_EPISODE_REF_DTL SET 
			 * GDT_ACCEPTANCE_DATE = SYSDATE, 
			 * HGNUM_TO_DEPTUNITCODE = hgnum_to_deptunitcode,
			 * HRGSTR_IS_REFF_IN_OUT = hrgstr_is_reff_in_out,
			 * HRGSTR_TO_EPISODE_CODE=hrgstr_to_episode_code,
			 * HRGNUM_TO_VISIT_NO=hrgnum_to_visit_no 
			WHERE HRGNUM_PUK = hrgnum_puk 
			AND HRGNUM_EPISODE_CODE = hrgnum_episode_code 
			AND HRGNUM_VISIT_NO = hrgnum_visit_no 
			AND HRGNUM_S_NO = hrgnum_s_no 
			AND GNUM_HOSPITAL_CODE=gnum_hospital_code;*/
			
			
			objDAO_p.setProcInValue(nProcIndex1, "p_modeVal", "3");
			
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objEpisodeRefDtlVO_p.getPatCrNo());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",objEpisodeRefDtlVO_p.getEpisodeCode());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",objEpisodeRefDtlVO_p.getEpisodeVisitNo());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no", objEpisodeRefDtlVO_p.getSerialNo());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",objEpisodeRefDtlVO_p.getToDepartmentCode());
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",objEpisodeRefDtlVO_p.getToDepartmentUnitCode());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",objEpisodeRefDtlVO_p.getToEpisodeCode());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",objEpisodeRefDtlVO_p.getToEpisodeVisitNo());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code",objEpisodeRefDtlVO_p.getExternalHospitalCode());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name",objEpisodeRefDtlVO_p.getExternalHospitalName());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name",objEpisodeRefDtlVO_p.getExternalHospitalDoctorName());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno",objEpisodeRefDtlVO_p.getExternalHospitalPatCrNo());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept",objEpisodeRefDtlVO_p.getExternalHospitalDepartment());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit",objEpisodeRefDtlVO_p.getExternalHospitalDepartmentUnit());
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext","");	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int","");	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext","");	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int","");	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","");
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","");
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",objUserVO_p.getIpAddress());
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id","");
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","");
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid","1");
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_lstmod_date","");
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid","");
			objDAO_p.setProcInValue(nProcIndex1, "p_gstr_remarks","");
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode());			
			objDAO_p.setProcOutValue(nProcIndex1, "err", 1);
			
			objDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						i=0;
						//_dailyPatientVO.setPatDOB(objDAO_p.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(objDAO_p.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
    	return i;
	}
	
	// getting patient for online department visit
	public EpisodeRefDtlVO[] getReferPat(UserVO objUserVO_p, String strMode_p)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_reg_view.proc_patient_referral_dtl(?,?,?,?,?, ?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeRefDtlVO[] objEpisodeRefDtlVO;
		try
		{
			System.out.println("EpisodeRefDtlDAO :: getReferPat()");
			System.out.println("strMode_p :"+strMode_p);
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),3);
			daoObj.setProcInValue(nProcIndex, "p_referral_acceptance_duration",RegistrationConfig.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS,4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");


			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			System.out.println("refVo size :"+webRs!=null? webRs.size():0);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] valueObject = {};
		try
		{
			webRs.beforeFirst();
			valueObject = HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class, webRs);
			objEpisodeRefDtlVO=new EpisodeRefDtlVO[valueObject.length];
			
			for(int i=0;i<valueObject.length;i++)
			{
				objEpisodeRefDtlVO[i]=(EpisodeRefDtlVO)valueObject[i];
				//Commented by Vasu on 28.May.18
				//objEpisodeRefDtlVO[i].setFromDepartmentUnit(objEpisodeRefDtlVO[i].getFromDepartmentUnit().length()>30?(objEpisodeRefDtlVO[i].getFromDepartmentUnit().substring(0, 30)+" "+objEpisodeRefDtlVO[i].getFromDepartmentUnit().substring(30, objEpisodeRefDtlVO[i].getFromDepartmentUnit().length())):objEpisodeRefDtlVO[i].getFromDepartmentUnit());
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objEpisodeRefDtlVO;
		
		
		
	}
	
	
	// To Fetch the Patient Referal Episodes Detail
	public EpisodeRefDtlVO[] getRefEpisodeDtlByCrNoByDeptByUnit(String strCrNo_p,String strDeptUnitCode_p, UserVO objUserVO_p, String strMode_p)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_reg_view.proc_patient_referral_episode_dtl(?,?,?,?,?, ?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeRefDtlVO[] objEpisodeRefDtlVO;
		try
		{
			System.out.println("EpisodeRefDtlDAO :: getRefEpisodeDtlByCrNoByDeptByUnit()");
			System.out.println("strMode_p :"+strMode_p);
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_crno", strCrNo_p,2);
			daoObj.setProcInValue(nProcIndex, "p_gnum_deptunitcode", strDeptUnitCode_p,3);
			daoObj.setProcInValue(nProcIndex, "p_referral_acceptance_duration",RegistrationConfig.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS,4);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),5);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");


			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			System.out.println("refVo size :"+webRs!=null? webRs.size():0);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] valueObject = {};
		try
		{
			webRs.beforeFirst();
			valueObject = HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class, webRs);
			objEpisodeRefDtlVO=new EpisodeRefDtlVO[valueObject.length];
			
			for(int i=0;i<valueObject.length;i++)
			{
				objEpisodeRefDtlVO[i]=(EpisodeRefDtlVO)valueObject[i];
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objEpisodeRefDtlVO;
		
		
		
	}
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the patients referred from other hospital
	 * date : 7th oct 2016 */
	public EpisodeRefDtlVO[] getExtRefEpisodeDtlByCrNoByDeptByUnit(PatientVisitSUP objPatVisitSUP_p,String strDeptUnitCode_p, UserVO objUserVO_p, String strMode_p)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_reg_view.proc_patient_referral_episode_dtl(?,?,?,?,?, ?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeRefDtlVO[] objEpisodeRefDtlVO;
		try
		{
			System.out.println("EpisodeRefDtlDAO :: getRefEpisodeDtlByCrNoByDeptByUnit()");
			System.out.println("strMode_p :"+strMode_p);
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_crno", objPatVisitSUP_p.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex, "p_gnum_deptunitcode", strDeptUnitCode_p,3);
			daoObj.setProcInValue(nProcIndex, "p_referral_acceptance_duration",RegistrationConfig.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS,4);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objPatVisitSUP_p.getFromHospital(),5);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");


			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			System.out.println("refVo size :"+webRs!=null? webRs.size():0);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] valueObject = {};
		try
		{
			webRs.beforeFirst();
			valueObject = HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class, webRs);
			objEpisodeRefDtlVO=new EpisodeRefDtlVO[valueObject.length];
			
			for(int i=0;i<valueObject.length;i++)
			{
				objEpisodeRefDtlVO[i]=(EpisodeRefDtlVO)valueObject[i];
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objEpisodeRefDtlVO;
		
		
		
	}
	//end

	/*

	//update patient  for online department visit	
	public int updateAcceptanceDate(EpisodeRefDtlVO objEpisodeRefDtlVO_p,UserVO objUserVO_p)
	{
		int i=0;
		String query="";
		Map _populateMap=new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.ACCEPTANCEDATE.HRGT_EPISODE_REF_DTL";		
    	Connection conn =super.getTransactionContext().getConnection();
    	
    	try
    	{
    		query=HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	Sequence sq=new Sequence();
    	
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToDepartmentUnitCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToEpisodeCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToEpisodeVisitNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getPatCrNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getEpisodeCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getEpisodeVisitNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getSerialNo());
    	_populateMap.put(sq.next(),objUserVO_p.getHospitalCode());
    	
    	try
    	{
    		i=HelperMethodsDAO.excecuteUpdate(conn,query,_populateMap);
    		if(i<1)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisUpdateUnsuccesfullException.class)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    		else
    		{
    			throw new HisDataAccessException("EpisodeReferDtlDAO::while updating data "+e);
    		}
    	}
    	return i;
		
	}*/
	public int updateAcceptanceDate(HisDAO objDAO_p,EpisodeRefDtlVO objEpisodeRefDtlVO_p,UserVO objUserVO_p)
	{
		int i=0;
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_refer_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = objDAO_p.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(objEpisodeRefDtlVO_p);
			
			/*UPDATE  HRGT_EPISODE_REF_DTL SET 
			 * GDT_ACCEPTANCE_DATE = SYSDATE, 
			 * HGNUM_TO_DEPTUNITCODE = hgnum_to_deptunitcode,
			 * HRGSTR_IS_REFF_IN_OUT = hrgstr_is_reff_in_out,
			 * HRGSTR_TO_EPISODE_CODE=hrgstr_to_episode_code,
			 * HRGNUM_TO_VISIT_NO=hrgnum_to_visit_no 
			WHERE HRGNUM_PUK = hrgnum_puk 
			AND HRGNUM_EPISODE_CODE = hrgnum_episode_code 
			AND HRGNUM_VISIT_NO = hrgnum_visit_no 
			AND HRGNUM_S_NO = hrgnum_s_no 
			AND GNUM_HOSPITAL_CODE=gnum_hospital_code;*/
			
			
			objDAO_p.setProcInValue(nProcIndex1, "p_modeVal", "2",1);
			
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objEpisodeRefDtlVO_p.getPatCrNo(),2);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",objEpisodeRefDtlVO_p.getEpisodeCode(),3);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",objEpisodeRefDtlVO_p.getEpisodeVisitNo(),4);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_s_no", objEpisodeRefDtlVO_p.getSerialNo(),5);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no","",6);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code","",7);
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode","",8);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code","",9);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code","",10);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",objEpisodeRefDtlVO_p.getToDepartmentCode(),11);
			objDAO_p.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",objEpisodeRefDtlVO_p.getToDepartmentUnitCode(),12);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code","",13);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code","",14);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",objEpisodeRefDtlVO_p.getToEpisodeCode(),15);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",objEpisodeRefDtlVO_p.getToEpisodeVisitNo(),16);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code","",16);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name","",17);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name","",18);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno","",19);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept","",20);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit","",21);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out","",22);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext","",23);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int","",24);	// for reffer date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext","",25);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int","",26);	// for acceptance date
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","",27);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","",28);
			objDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add","",29);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id","",30);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",31);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",32);
			objDAO_p.setProcInValue(nProcIndex1, "p_gdt_lstmod_date","",33);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid","",34);
			objDAO_p.setProcInValue(nProcIndex1, "p_gstr_remarks","",35);
			objDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),36);			
			objDAO_p.setProcOutValue(nProcIndex1, "err", 1,37);
			
			objDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						i=0;
						//_dailyPatientVO.setPatDOB(objDAO_p.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(objDAO_p.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
    	return i;
		
	}
//	getting patient  for online Special Clinic Department visit
	
	/*public EpisodeRefDtlVO[] getSCReferPat(UserVO objUserVO_p)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.SPECIAL_CLINIC.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] objEpisodeRefDtlVO_p;
		
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		
		_populateMap.put(sq.next(),objUserVO_p.getHospitalCode());
		_populateMap.put(sq.next(),objUserVO_p.getSeatId());
		_populateMap.put(sq.next(),Config.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("");
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			objEpisodeRefDtlVO_p=new EpisodeRefDtlVO[vo.length];
		
			for(int i=0;i<vo.length;i++)
			{
				objEpisodeRefDtlVO_p[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return objEpisodeRefDtlVO_p;
	}*/
	
//	update patient  for online special clinic department visit	
	/*public int updateSCAcceptanceDate(EpisodeRefDtlVO objEpisodeRefDtlVO_p,UserVO objUserVO_p)
	{
		int i=0;
		String query="";
		Map _populateMap=new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.SC_ACCEPTANCEDATE.HRGT_EPISODE_REF_DTL";		
    	Connection conn =super.getTransactionContext().getConnection();
    	
    	try
    	{
    		query=HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	Sequence sq=new Sequence();
    	
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToDepartmentCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToEpisodeCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToEpisodeVisitNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getPatCrNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getEpisodeCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getEpisodeVisitNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getSerialNo());
    	_populateMap.put(sq.next(),objUserVO_p.getHospitalCode());
    	
    	try
    	{
    		i=HelperMethodsDAO.excecuteUpdate(conn,query,_populateMap);
    		if(i<1)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisUpdateUnsuccesfullException.class)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    		else
    		{
    			throw new HisDataAccessException("EpisodeReferDtlDAO::while updating data "+e);
    		}
    	}
    	return i;
		
	}
	
	public EpisodeRefDtlVO[] getOldPatReferDtl(UserVO userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.OLD_PAT_REFER.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeOldPatRefDtlVO;
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),userVO.getSeatId());
		_populateMap.put(sq.next(),Config.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("");
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeOldPatRefDtlVO=new EpisodeRefDtlVO[vo.length];
		
			for(int i=0;i<vo.length;i++)
			{
				_episodeOldPatRefDtlVO[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeOldPatRefDtlVO;
	}

	
	public int updateOldPatVisitAcceptance(EpisodeRefDtlVO objEpisodeRefDtlVO_p,UserVO objUserVO_p)
	{
		int i=0;
		String query="";
		Map _populateMap=new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.OLD_PAT_ACCEPTANCEDATE.HRGT_EPISODE_REF_DTL";		
    	Connection conn =super.getTransactionContext().getConnection();
    	
    	try
    	{
    		query=HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	Sequence sq=new Sequence();
    	
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToDepartmentCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToDepartmentUnitCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getToEpisodeCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getIsRefferInOut());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getPatCrNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getEpisodeCode());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getEpisodeVisitNo());
    	_populateMap.put(sq.next(),objEpisodeRefDtlVO_p.getSerialNo());
    	_populateMap.put(sq.next(),objUserVO_p.getHospitalCode());
    	
    	try
    	{
    		i=HelperMethodsDAO.excecuteUpdate(conn,query,_populateMap);
    		if(i<1)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisUpdateUnsuccesfullException.class)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    		else
    		{
    			throw new HisDataAccessException("EpisodeReferDtlDAO::while updating data "+e);
    		}
    	}
    	return i;
		
	}*/
	
	/**
	 * get detail of episode refer of patient by crno and specific episode.
	 * @param userVO
	 * @return
	 */
	/*public EpisodeRefDtlVO[] getEpisodeReferDtlByCrNoEpisodeCode(EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.EPISODE_REF_DTL.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeRefDtlVOArray;
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),episodeRefDtlVO.getPatCrNo());
		_populateMap.put(sq.next(),episodeRefDtlVO.getEpisodeCode());
		_populateMap.put(sq.next(),episodeRefDtlVO.getEpisodeVisitNo());
		_populateMap.put(sq.next(),userVO.getHospitalCode());
				
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("");
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeRefDtlVOArray=new EpisodeRefDtlVO[vo.length];
		
			for(int i=0;i<vo.length;i++)
			{
				_episodeRefDtlVOArray[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeRefDtlVOArray;
	}*/

	
	/**
	 * get detail of episode refer of patient by crno
	 * @param userVO
	 * @return
	 */
	/*public EpisodeRefDtlVO[] getEpisodeReferDtlByCrNoEmr(EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.BY_CR_NO.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeRefDtlVOArray;
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),episodeRefDtlVO.getPatCrNo());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);
		_populateMap.put(sq.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
		
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("");
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeRefDtlVOArray=new EpisodeRefDtlVO[vo.length];
			
			for(int i=0;i<vo.length;i++)
			{
				_episodeRefDtlVOArray[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeRefDtlVOArray;
	}*/
	
	
}
