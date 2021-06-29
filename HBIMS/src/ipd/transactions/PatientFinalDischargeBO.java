package ipd.transactions;

import javax.sql.rowset.WebRowSet;

public class PatientFinalDischargeBO {
	public void getRsnRmk(PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.getRsnRmk(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString(" PatientFinalDischargeBO.getRsnRmk() ---> "
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void insert(PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.insert(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString(" PatientFinalDischargeBO.insert() ---> "
							+ _patientFinalDischargeVO.getStrMsgString());

	}

	public void getPatientDischargeParameter(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO
				.getPatientDischargeParameter(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getPatientDeschargeParameter()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}
	
	public void getHospitalList(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO
				.getHospitalList(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getHospitalList()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void initFinalDiagnosis(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.initFinalDiagnosis(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.initFinalDiagnosis()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void setIcdDiagnosis(PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.getIcd10DiagnosisDtl(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1")) 
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.setIcdDiagnosis()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void getPatientDischargeTypeCombo(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO
				.getPatientDischargeTypeCombo(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getPatientDischargeTypeCombo()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void getPatientDeathDetails(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO
				.getPatientDeathDetails(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1")) 
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getPatientDeathDetails()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void getStrDeathMannerComboValues(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO
				.getStrDeathMannerComboValues(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getStrDeathMannerComboValues()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void getStrDeathCauseComboValues(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO
				.getStrDeathCauseComboValues(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getStrDeathCauseComboValues()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}

	public void getGender(PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.getGender(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getGender()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}
	
	public void checkAdmin(PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.checkAdmin(_patientFinalDischargeVO);
		PatientFinalDischargeDAO.getIcuPvtBillStatus(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeBO.checkAdmin()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}
	public void getApprovalByCMO(PatientFinalDischargeVO _patientFinalDischargeVO) {
		WebRowSet ws=null;
		ws=PatientFinalDischargeDAO.getAdvisedBy(_patientFinalDischargeVO);
		 _patientFinalDischargeVO.setStrApprovByWS_MLC(ws);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getApprovalByCMO()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}
	
	public void getOPDVisitRoom(PatientFinalDischargeVO _patientFinalDischargeVO) {
	//	System.out.println("INBO->");
		PatientFinalDischargeDAO.getOPDVisitRoom(_patientFinalDischargeVO);
	//	System.out.println("msgTypBO->"+_patientFinalDischargeVO.getStrMsgType());
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getOPDVisitRoom()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}
	
	public void checkForBillClearance(PatientFinalDischargeVO _patientFinalDischargeVO) {
			PatientFinalDischargeDAO.checkForBillClearance(_patientFinalDischargeVO);
			if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
				_patientFinalDischargeVO
						.setStrMsgString("PatientAdmissionTransBO.checkForBillClearance()---->"
								+ _patientFinalDischargeVO.getStrMsgString());
	}
	public void checkTransitTimeClearance(PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.checkTransitTimeClearance(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.checkTransitTimeClearance()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
}
	public void getStrTreatmentResultCombo(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO
				.getStrTreatmentResultCombo(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getStrTreatmentResultCombo()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}
	public void getStrProfileId(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		PatientFinalDischargeDAO.getStrProfileId(_patientFinalDischargeVO);
		if (_patientFinalDischargeVO.getStrMsgType().equals("1"))
			_patientFinalDischargeVO
					.setStrMsgString("PatientAdmissionTransBO.getStrProfileId()---->"
							+ _patientFinalDischargeVO.getStrMsgString());
	}
}
