
	package hisglobal.vo;

	public class ServiceAreaProcedureMasterVO extends ValueObject
	{
		private String serviceAreaCode;
		private String serviceAreaName;
		private String hospitalCode;
		private String procedureName;
		private String isActive="";
		private String procedureCode;
		private String isActiveLabel="";
		private String serviceAreaCodeOlder;
		private String procedureCodeOlder;
		
		
		
		public String getProcedureName() {
			return procedureName;
		}
		public void setProcedureName(String procedureName) {
			this.procedureName = procedureName;
		}
		public String getServiceAreaCode()
		{
			return serviceAreaCode;
		}
		public void setServiceAreaCode(String serviceAreaCode)
		{
			this.serviceAreaCode = serviceAreaCode;
		}
		public String getServiceAreaName()
		{
			return serviceAreaName;
		}
		public void setServiceAreaName(String serviceAreaName)
		{
			this.serviceAreaName = serviceAreaName;
		}
		public String getHospitalCode() {
			return hospitalCode;
		}
		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}
		public String getIsActive() {
			return isActive;
		}
		public void setIsActive(String isActive) {
			this.isActive = isActive;
		}
		public String getProcedureCode() {
			return procedureCode;
		}
		public void setProcedureCode(String procedureCode) {
			this.procedureCode = procedureCode;
		}
		
		public String getIsActiveLabel() {
			return isActiveLabel;
		}
		public void setIsActiveLabel(String isActiveLabel) {
			this.isActiveLabel = isActiveLabel;
		}
		public String getServiceAreaCodeOlder() {
			return serviceAreaCodeOlder;
		}
		public void setServiceAreaCodeOlder(String serviceAreaCodeOlder) {
			this.serviceAreaCodeOlder = serviceAreaCodeOlder;
		}
		public String getProcedureCodeOlder() {
			return procedureCodeOlder;
		}
		public void setProcedureCodeOlder(String procedureCodeOlder) {
			this.procedureCodeOlder = procedureCodeOlder;
		}
		
	}


