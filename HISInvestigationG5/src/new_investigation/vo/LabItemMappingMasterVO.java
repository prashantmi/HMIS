package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class LabItemMappingMasterVO extends ValueObject 
{

	
	private String labCode;
	private String hospitalCode;
	
	


	
	
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	
	
	
	}

