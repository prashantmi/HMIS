package new_opd.bo;

import javax.servlet.http.HttpServletRequest;

import new_opd.DAO.IpdDoctorDeskDAO;
import new_opd.vo.IpdDoctorDeskVO;

public class IpdDoctorDeskBO {

	public void getInitilasData(IpdDoctorDeskVO vo) {
		
		//IpdDoctorDeskDAO.getDeptDtl(vo);
		IpdDoctorDeskDAO.getInitilaData(vo);
		IpdDoctorDeskDAO.getInvestogation(vo);
		IpdDoctorDeskDAO.getdrugdtl(vo);
		IpdDoctorDeskDAO.getdrug_dosage_dtl(vo);
		IpdDoctorDeskDAO.getMacrosDtl(vo);
		IpdDoctorDeskDAO.getRefferalDeptDtl(vo);
		IpdDoctorDeskDAO.getBookMarksTestDtl(vo);
		IpdDoctorDeskDAO.getDIAGNOSISDtl(vo);
		IpdDoctorDeskDAO.getClinicalProcedureDtls(vo);
		IpdDoctorDeskDAO.getDrugProfileDtl(vo);
		IpdDoctorDeskDAO.getPresriptionProfileDtl(vo);
		IpdDoctorDeskDAO.getpatientTeleConsultancyData(vo);
		IpdDoctorDeskDAO.getTemplateDtl(vo);
		IpdDoctorDeskDAO.getDrugProfileDtl(vo);
		IpdDoctorDeskDAO.getHospitalHeaderName(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("getInitilaData.getInitilasData() --> "
					+ vo.getStrMsgString());
		}
	}
public void getInitilasData1(IpdDoctorDeskVO vo , HttpServletRequest request) {
		
		IpdDoctorDeskDAO.getDeptDtl(vo,request);
		/*IpdDoctorDeskDAO.getInitilaData(vo);
		IpdDoctorDeskDAO.getInvestogation(vo);
		IpdDoctorDeskDAO.getdrugdtl(vo);
		IpdDoctorDeskDAO.getdrug_dosage_dtl(vo);
		IpdDoctorDeskDAO.getMacrosDtl(vo);
		IpdDoctorDeskDAO.getRefferalDeptDtl(vo);
		IpdDoctorDeskDAO.getBookMarksTestDtl(vo);
		IpdDoctorDeskDAO.getDIAGNOSISDtl(vo);
		IpdDoctorDeskDAO.getClinicalProcedureDtls(vo);*/
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("getInitilaData.getInitilasData() --> "
					+ vo.getStrMsgString());
		}
	}
	

public void getInitilasDataForMobileApp(IpdDoctorDeskVO vo , HttpServletRequest request) {
	
	IpdDoctorDeskDAO.getDeptDtl(vo,request);
	IpdDoctorDeskDAO.getInitilaData(vo);
	IpdDoctorDeskDAO.getInvestogation(vo);
	IpdDoctorDeskDAO.getdrugdtl(vo);
	IpdDoctorDeskDAO.getdrug_dosage_dtl(vo);
	IpdDoctorDeskDAO.getMacrosDtl(vo);
	IpdDoctorDeskDAO.getRefferalDeptDtl(vo);
	IpdDoctorDeskDAO.getBookMarksTestDtl(vo);
	IpdDoctorDeskDAO.getDIAGNOSISDtl(vo);
	IpdDoctorDeskDAO.getClinicalProcedureDtls(vo);
	IpdDoctorDeskDAO.getDrugProfileDtl(vo);
	IpdDoctorDeskDAO.getPresriptionProfileDtl(vo);
	IpdDoctorDeskDAO.getpatientTeleConsultancyData(vo);
	IpdDoctorDeskDAO.getDrugProfileDtl(vo);
	if (vo.getStrMsgType().equals("1")) {
		vo.setStrMsgString("getInitilaData.getInitilasData() --> "
				+ vo.getStrMsgString());
	}
}
	public void getPrevData(IpdDoctorDeskVO vo)
	{
		IpdDoctorDeskDAO.getPrevData(vo);
		IpdDoctorDeskDAO.getPrevDataInv(vo);
		IpdDoctorDeskDAO.getPrevVisitReason(vo);
		//IpdDoctorDeskDAO.getPrevDiagnosisDtl(vo);
		IpdDoctorDeskDAO.getPrevVitalDtls(vo);
		IpdDoctorDeskDAO.getPrevVitalforeTeleConsultancy(vo);
		IpdDoctorDeskDAO.getPrevvisitresonData(vo);
	}

}
