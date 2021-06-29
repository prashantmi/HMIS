/**
##		Created By				: Akash Singh
## 		Modification Log		: PATIENTSUMMARY					
##		Creation Date			: 15-01-2015
##		Reason	(CR/PRS)		: CR
*/
package mrd.vo;

import hisglobal.vo.ValueObject;

public class EMRPateintDataPolicyVO extends ValueObject
{
	private String deskId;
	private String deskMenuId;
	private String deskType;
	
	
	
	
	public String getDeskId() {
		return deskId;
	}
	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	
}
