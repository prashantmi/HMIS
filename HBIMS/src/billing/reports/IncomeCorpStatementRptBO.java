package billing.reports;

public class IncomeCorpStatementRptBO {
	
	public void getHospSerDetails(IncomeCorpStatementRptVO voObj){
		
		IncomeCorpStatementRptDAO.getHospSerList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("IncomeCorpStatementRptBO.getHospSerDetails()-->"+strErr);
			}
				
			}

}
