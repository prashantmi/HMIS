package new_opd.bo;

import javax.servlet.http.HttpServletRequest;

import new_opd.DAO.DoctorDeskDAO;
import new_opd.vo.DoctorDeskVO;

public class DoctorDeskBO {

	public void getInitilasData(DoctorDeskVO vo) {
		
		//DoctorDeskDAO.getDeptDtl(vo);
		DoctorDeskDAO.getInitilaData(vo);
		DoctorDeskDAO.getConfigData(vo);
		DoctorDeskDAO.getInvestogation(vo);
		DoctorDeskDAO.getdrugdtl(vo);
		DoctorDeskDAO.getdrug_dosage_dtl(vo);
		DoctorDeskDAO.getMacrosDtl(vo);
		DoctorDeskDAO.getRefferalDeptDtl(vo);
		DoctorDeskDAO.getBookMarksTestDtl(vo);
		DoctorDeskDAO.getDIAGNOSISDtl(vo);
		DoctorDeskDAO.getClinicalProcedureDtls(vo);
		DoctorDeskDAO.getDrugProfileDtl(vo);
		DoctorDeskDAO.getPresriptionProfileDtl(vo);
		DoctorDeskDAO.getpatientTeleConsultancyData(vo);
		DoctorDeskDAO.getpatientRefferalConsultancyData(vo);
		DoctorDeskDAO.getTemplateDtl(vo);
		DoctorDeskDAO.getDrugProfileDtl(vo);
		DoctorDeskDAO.getHospitalHeaderName(vo);
		DoctorDeskDAO.getExternalHospital(vo);
		DoctorDeskDAO.getExternalInstitute(vo);
		DoctorDeskDAO.getExternalDepartmentList(vo);
		DoctorDeskDAO.getServiceAreaList(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("getInitilaData.getInitilasData() --> "
					+ vo.getStrMsgString());
		}
	}
public void getInitilasData1(DoctorDeskVO vo , HttpServletRequest request) {
		
		DoctorDeskDAO.getDeptDtl(vo,request);
		/*DoctorDeskDAO.getInitilaData(vo);
		DoctorDeskDAO.getInvestogation(vo);
		DoctorDeskDAO.getdrugdtl(vo);
		DoctorDeskDAO.getdrug_dosage_dtl(vo);
		DoctorDeskDAO.getMacrosDtl(vo);
		DoctorDeskDAO.getRefferalDeptDtl(vo);
		DoctorDeskDAO.getBookMarksTestDtl(vo);
		DoctorDeskDAO.getDIAGNOSISDtl(vo);
		DoctorDeskDAO.getClinicalProcedureDtls(vo);*/
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("getInitilaData.getInitilasData() --> "
					+ vo.getStrMsgString());
		}
	}
	

public void getInitilasDataForMobileApp(DoctorDeskVO vo , HttpServletRequest request) {
	
	DoctorDeskDAO.getDeptDtl(vo,request);
	DoctorDeskDAO.getInitilaData(vo);
	DoctorDeskDAO.getInvestogation(vo);
	DoctorDeskDAO.getdrugdtl(vo);
	DoctorDeskDAO.getdrug_dosage_dtl(vo);
	DoctorDeskDAO.getMacrosDtl(vo);
	DoctorDeskDAO.getRefferalDeptDtl(vo);
	DoctorDeskDAO.getBookMarksTestDtl(vo);
	DoctorDeskDAO.getDIAGNOSISDtl(vo);
	DoctorDeskDAO.getClinicalProcedureDtls(vo);
	DoctorDeskDAO.getDrugProfileDtl(vo);
	DoctorDeskDAO.getPresriptionProfileDtl(vo);
	DoctorDeskDAO.getpatientTeleConsultancyData(vo);
	DoctorDeskDAO.getpatientRefferalConsultancyData(vo);
	DoctorDeskDAO.getDrugProfileDtl(vo);
	DoctorDeskDAO.getExternalHospital(vo);
	DoctorDeskDAO.getExternalInstitute(vo);
	DoctorDeskDAO.getHospitalHeaderName(vo);
	DoctorDeskDAO.getExternalDepartmentList(vo);
	DoctorDeskDAO.getHospitalHeaderName(vo);
	DoctorDeskDAO.getExternalHospital(vo);
	DoctorDeskDAO.getExternalInstitute(vo);
	DoctorDeskDAO.getExternalDepartmentList(vo);
	DoctorDeskDAO.getServiceAreaList(vo);
	
	if (vo.getStrMsgType().equals("1")) {
		vo.setStrMsgString("getInitilaData.getInitilasData() --> "
				+ vo.getStrMsgString());
	}
}
	public void getPrevData(DoctorDeskVO vo)
	{
		DoctorDeskDAO.getPrevData(vo);
		DoctorDeskDAO.getPrevDataInv(vo);
		DoctorDeskDAO.getPrevVisitReason(vo);
		//DoctorDeskDAO.getPrevDiagnosisDtl(vo);
		DoctorDeskDAO.getPrevVitalDtls(vo);
		DoctorDeskDAO.getPrevVitalforeTeleConsultancy(vo);
		DoctorDeskDAO.getPrevvisitresonData(vo);
	}

	public void getHospitalHeader(DoctorDeskVO vo) {
		DoctorDeskDAO.getHospitalHeaderName(vo);
	}
	public void saveFileData(DoctorDeskVO vo) {
		DoctorDeskDAO.saveFileData(vo);
	}
}
