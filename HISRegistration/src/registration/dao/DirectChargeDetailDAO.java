package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

import java.sql.SQLException;
import java.util.Map;

import vo.registration.DirectChageDetailVO;

public class DirectChargeDetailDAO extends DataAccessObject {

	public DirectChargeDetailDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public DirectChageDetailVO create(HisDAO objHisDAO_p,DirectChageDetailVO objDirectChageDetailVO_p, UserVO objUserVO_p) {
		
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hblt_direct_charge_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,	?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,		?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,		?::numeric,?,?::numeric,?::numeric,?,	?::numeric,?::numeric,?::numeric,?,?,?, ?::character varying,?::character varying,?::numeric,?::numeric,?::timestamp without time zone,?::character varying, ?::character varying,?::numeric,?,		?,?,?)}";//38 args
			//strProcName1 = "{call pkg_reg_dml.dml_hblt_direct_charge_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,	?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,		?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,		?::numeric,?,?::numeric,?::numeric,?,	?::numeric,?::numeric,?::numeric,?,?,?, ?)}";
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(objDirectChageDetailVO_p);
			
			System.out.println("---------------------------------");
			System.out.println("DirectChargeDetailDAO :: create()");
			//////////////////////////
			System.out.println("p_modeval : 1");
			System.out.println("p_hblnum_bill_no :"+objDirectChageDetailVO_p.getBillNo());
			System.out.println("p_hblnum_reciept_no :"+objDirectChageDetailVO_p.getRecieptNo());
			System.out.println("p_hrgnum_puk :"+objDirectChageDetailVO_p.getPatCrNo());
			System.out.println("p_hblnum_reciept_type :"+objDirectChageDetailVO_p.getRecieptType());
			System.out.println("p_gnum_dept_code :"+objDirectChageDetailVO_p.getDepartmentCode());
			System.out.println("p_hgnum_deptunitcode :"+objDirectChageDetailVO_p.getDepartmentUnitCode());
			System.out.println("p_hrgnum_episode_code :"+objDirectChageDetailVO_p.getEpisodeCode());
			System.out.println("p_hrgnum_visit_no :"+objDirectChageDetailVO_p.getEpisodeVisitNo());	
			System.out.println("p_hrgnum_admission_no :"+objDirectChageDetailVO_p.getAdmissionNo());
			System.out.println("p_hblstr_tariff_id :"+objDirectChageDetailVO_p.getTariffId());
			System.out.println("p_hgnum_patient_cat_code :"+objDirectChageDetailVO_p.getPatPrimaryCatCode());
			System.out.println("p_hgnum_treatment_cat_code :"+objDirectChageDetailVO_p.getPatSecondaryCatCode());			
			System.out.println("p_hipnum_ward_code :"+objDirectChageDetailVO_p.getPatSecondaryCatCode());
			System.out.println("p_hrgnum_amt_collected :"+objDirectChageDetailVO_p.getPatAmountCollected());
			System.out.println("p_gnum_counter_id :"+objDirectChageDetailVO_p.getPatAmountCollected());
			System.out.println("p_hrgstr_ip_add :"+(objUserVO_p.getIpAddress()==null || objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()));
			System.out.println("p_gnum_seat_id :"+objUserVO_p.getSeatId());
			System.out.println("p_hblnum_request_type :"+objDirectChageDetailVO_p.getRequestType());
			System.out.println("p_gnum_isvalid :"+Config.IS_VALID_ACTIVE);
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode());
			System.out.println("p_gnum_module_id :"+objUserVO_p.getModuleId());
			System.out.println("p_credit_bill_flag :"+objDirectChageDetailVO_p.getCreditBillFlag());
			System.out.println("p_credit_letter_ref_no :"+objDirectChageDetailVO_p.getCreditLetterRefNo());
			System.out.println("p_credit_letter_date :"+objDirectChageDetailVO_p.getCreditLetterDate());
			
			System.out.println("p_hrgstr_card_no :"+ objDirectChageDetailVO_p.getCardNo());
			System.out.println("p_hrgstr_card_holder_name :"+ objDirectChageDetailVO_p.getStaffCardName());
			System.out.println("p_hrgnum_dependent_relation_code :"+ objDirectChageDetailVO_p.getRelationWithStaff());
			System.out.println("p_hrgnum_card_district_code :"+ objDirectChageDetailVO_p.getAgsDistrictCode()); 
			System.out.println("p_hrgnum_card_validupto :"+ objDirectChageDetailVO_p.getCardvalidityDate()); 
			System.out.println("p_hrgnum_counter_no :"+ objDirectChageDetailVO_p.getCardvalidityDate()); 
			System.out.println("p_hrgnum_actual_amt :"+ objDirectChageDetailVO_p.getPatActualAmount());
			//System.out.println("p_hblstr_payment_ref_id",objDirectChageDetailVO_p.getPaymentModeRefId());

			/////////////////////
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeval", "1",1);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_bill_no",objDirectChageDetailVO_p.getBillNo(),2);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_reciept_no",objDirectChageDetailVO_p.getRecieptNo(),3);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk",objDirectChageDetailVO_p.getPatCrNo(),4);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_reciept_type",objDirectChageDetailVO_p.getRecieptType(),5);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_dept_code",objDirectChageDetailVO_p.getDepartmentCode(),6);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode",objDirectChageDetailVO_p.getDepartmentUnitCode(),7);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",objDirectChageDetailVO_p.getEpisodeCode(),8);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",objDirectChageDetailVO_p.getEpisodeVisitNo(),9);	
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_admission_no",objDirectChageDetailVO_p.getAdmissionNo().equals("")?"0":objDirectChageDetailVO_p.getAdmissionNo(),10);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblstr_tariff_id",objDirectChageDetailVO_p.getTariffId(),11);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code",objDirectChageDetailVO_p.getPatPrimaryCatCode(),12);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code",objDirectChageDetailVO_p.getPatSecondaryCatCode(),13);			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hipnum_ward_code",objDirectChageDetailVO_p.getPatSecondaryCatCode().equals("")?"0":objDirectChageDetailVO_p.getPatSecondaryCatCode(),14);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected",objDirectChageDetailVO_p.getPatAmountCollected(),15);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_counter_id",objDirectChageDetailVO_p.getPatAmountCollected(),16);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",(objUserVO_p.getIpAddress()==null || objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()),17);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id",objUserVO_p.getSeatId(),18);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_request_type",objDirectChageDetailVO_p.getRequestType(),19);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",20);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,21);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),22);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_module_id",objUserVO_p.getModuleId(),23);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_credit_bill_flag",objDirectChageDetailVO_p.getCreditBillFlag().equals("")?"0":objDirectChageDetailVO_p.getCreditBillFlag(),24);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_credit_letter_ref_no",objDirectChageDetailVO_p.getCreditLetterRefNo(),25);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_credit_letter_date",objDirectChageDetailVO_p.getCreditLetterDate(),26);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_no",objDirectChageDetailVO_p.getCardNo(),27);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_card_holder_name",objDirectChageDetailVO_p.getStaffCardName(),28);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_dependent_relation_code",objDirectChageDetailVO_p.getRelationWithStaff(),29);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_card_district_code",objDirectChageDetailVO_p.getAgsDistrictCode(),30);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_card_validupto",objDirectChageDetailVO_p.getCardvalidityDate(),31);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_counter_no",objDirectChageDetailVO_p.getAgsCounterNo(),32);
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_actual_amt",objDirectChageDetailVO_p.getPatActualAmount(),33);
			/* #Start				:Sheeldarshi 
			#Modify Date(CR/PRS)	:15thJuly'15 
			#Reason					:Change for adding Organisation combo on All Registration Pages
			 */
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_client_no",objDirectChageDetailVO_p.getClientCode(),34);
			//End
			objHisDAO_p.setProcInValue(nProcIndex1, "p_sblnum_chargetype_id",objDirectChageDetailVO_p.getChargeType(),35);//By Mukund on 15.05.2017
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblstr_payment_ref_id",objDirectChageDetailVO_p.getPaymentModeRefId(),36);//By warish on 10.08.2018
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hblnum_payment_mode",objDirectChageDetailVO_p.getPaymentMode().equals("")?"1":objDirectChageDetailVO_p.getPaymentMode(),37);//By warish on 10.08.2018
			
			objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,38);
			
			objHisDAO_p.execute(nProcIndex1,1);			
			
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(objHisDAO_p.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(objHisDAO_p.getString(nProcIndex1, "patAge"));
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return objDirectChageDetailVO_p;
		
		
	}

		public void populateMap(DirectChageDetailVO objDirectChageDetailVO_p,UserVO objUserVO_p,Map _populateMap)throws SQLException{
			Sequence sequence=new Sequence();
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getPatCrNo());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getDepartmentCode());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getDepartmentUnitCode());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getEpisodeCode());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getEpisodeVisitNo());	
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getAdmissionNo());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getServiceId());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getTariffId());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getPatPrimaryCatCode());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getPatSecondaryCatCode());			
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getPatAmountCollected());
			_populateMap.put(sequence.next(),objUserVO_p.getIpAddress());
			_populateMap.put(sequence.next(),objDirectChageDetailVO_p.getSeatId());
			_populateMap.put(sequence.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sequence.next(),objUserVO_p.getHospitalCode());
			
			
		}

}
