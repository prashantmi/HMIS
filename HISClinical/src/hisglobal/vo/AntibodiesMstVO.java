package hisglobal.vo;

public class AntibodiesMstVO extends ValueObject
{		
		private String antibodyTypeCode;
		private String antibodyName;
		private String remarks;
		private String slNo;
		private String transfusionFlag;
		private String entryDate;
		private String lastModifyDate;
		private String isActive;
		private String hmode;
		private String chk[];
		private String hospitalCode;
		
		public String getAntibodyTypeCode()
		{
			return antibodyTypeCode;
		}
		public void setAntibodyTypeCode(String antibodyTypeCode)
		{
			this.antibodyTypeCode = antibodyTypeCode;
		}
		public String getAntibodyName()
		{
			return antibodyName;
		}
		public void setAntibodyName(String antibodyName)
		{
			this.antibodyName = antibodyName;
		}
		public String getRemarks()
		{
			return remarks;
		}
		public void setRemarks(String remarks)
		{
			this.remarks = remarks;
		}
		public String getSlNo()
		{
			return slNo;
		}
		public void setSlNo(String slNo)
		{
			this.slNo = slNo;
		}
		public String getTransfusionFlag()
		{
			return transfusionFlag;
		}
		public void setTransfusionFlag(String transfusionFlag)
		{
			this.transfusionFlag = transfusionFlag;
		}
		public String getEntryDate()
		{
			return entryDate;
		}
		public void setEntryDate(String entryDate)
		{
			this.entryDate = entryDate;
		}
		public String getLastModifyDate()
		{
			return lastModifyDate;
		}
		public void setLastModifyDate(String lastModifyDate)
		{
			this.lastModifyDate = lastModifyDate;
		}
		public String getIsActive()
		{
			return isActive;
		}
		public void setIsActive(String isActive)
		{
			this.isActive = isActive;
		}
		public String getHmode()
		{
			return hmode;
		}
		public void setHmode(String hmode)
		{
			this.hmode = hmode;
		}
		public String[] getChk()
		{
			return chk;
		}
		public void setChk(String[] chk)
		{
			this.chk = chk;
		}
		public String getHospitalCode()
		{
			return hospitalCode;
		}
		public void setHospitalCode(String hospitalCode)
		{
			this.hospitalCode = hospitalCode;
		}

}
