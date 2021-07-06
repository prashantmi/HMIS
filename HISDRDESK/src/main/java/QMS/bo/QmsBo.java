package QMS.bo;

import javax.servlet.http.HttpServletRequest;

import QMS.DAO.QmsDAO;
import QMS.vo.QmsVO;

public class QmsBo {

	public void getInitilasData(QmsVO vo) {
		
		//QmsDAO.getDeptDtl(vo);
		
		QmsDAO.getInitilaData(vo);
		//QmsDAO.getInvestogation(vo);
/*		QmsDAO.getdrugdtl(vo);
		QmsDAO.getdrug_dosage_dtl(vo);
		QmsDAO.getMacrosDtl(vo);
		QmsDAO.getRefferalDeptDtl(vo);
		QmsDAO.getBookMarksTestDtl(vo);
		QmsDAO.getDIAGNOSISDtl(vo);
		QmsDAO.getClinicalProcedureDtls(vo);*/
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("getInitilaData.getInitilasData() --> "
					+ vo.getStrMsgString());
		}
	}
public void getInitilasData1(QmsVO vo , HttpServletRequest request) {
		
		QmsDAO.getDeptDtl(vo,request);
		/*QmsDAO.getInitilaData(vo);
		QmsDAO.getInvestogation(vo);
		QmsDAO.getdrugdtl(vo);
		QmsDAO.getdrug_dosage_dtl(vo);
		QmsDAO.getMacrosDtl(vo);
		QmsDAO.getRefferalDeptDtl(vo);
		QmsDAO.getBookMarksTestDtl(vo);
		QmsDAO.getDIAGNOSISDtl(vo);
		QmsDAO.getClinicalProcedureDtls(vo);*/
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("getInitilaData.getInitilasData() --> "
					+ vo.getStrMsgString());
		}
	}
	
	public void getPrevData(QmsVO vo)
	{
		QmsDAO.getPrevData(vo);
		QmsDAO.getPrevDataInv(vo);
		QmsDAO.getPrevVisitReason(vo);
		//QmsDAO.getPrevDiagnosisDtl(vo);
		QmsDAO.getPrevVitalDtls(vo);
	}

}
