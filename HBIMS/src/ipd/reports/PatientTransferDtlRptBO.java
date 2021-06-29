package ipd.reports;

public class PatientTransferDtlRptBO {

	public void getWardAll(PatientTransferDtlRptVO voObj){
		PatientTransferDtlRptDAO.getWardAll(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				String strErr = voObj.getStrMsgString();
				voObj.setStrMsgString("PatientTransferDtlRptBO.getWardAll()-->"+strErr);
					}
			}
    }
