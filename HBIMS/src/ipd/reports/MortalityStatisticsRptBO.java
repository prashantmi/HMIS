package ipd.reports;

public class MortalityStatisticsRptBO {

public void getDepartmentDetails(MortalityStatisticsRptVO voObj){
		
	MortalityStatisticsRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("MortalityStatisticsRptBO.getDepartmentDetails()-->"+strErr);
	}
			
		}
		
	public void getUnitDetails(MortalityStatisticsRptVO voObj){
			
		MortalityStatisticsRptDAO.getUnitList(voObj);
		MortalityStatisticsRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("MortalityStatisticsRptBO.getUnitDetails()-->"+strErr);
		}
			
		}
	public void getWardCombo(MortalityStatisticsRptVO voObj){
		
		
		MortalityStatisticsRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("MortalityStatisticsRptBO.getWardCombo()-->"+strErr);
		}
			
		}
	public void getWardDetails(MortalityStatisticsRptVO voObj){
		
		MortalityStatisticsRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("MortalityStatisticsRptBO.getWardDetails()-->"+strErr);
		}
		
	}
public void getHospitalName(MortalityStatisticsRptVO voObj){
		
	MortalityStatisticsRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("MortalityStatisticsRptBO.getHospitalName()-->"+strErr);
		}
		
	}

public void getdeptComboDetails(MortalityStatisticsRptVO voObj){
	
	
	MortalityStatisticsRptDAO.getdeptComboDetails(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("MortalityStatisticsRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}
}
