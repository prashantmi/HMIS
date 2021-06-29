package mrd.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;


public class ASRDORWiseHospitalRegistrationReportFB extends ReportFB
	{
		private String religionCode;
		private String genderCode;
		private String fromAge;
		private String toAge;
		private String option;
		private String ageRange;
		
		public void reset(ActionMapping mapping, HttpServletRequest request)
		{
			super.reset(mapping, request);
			this.setReligionCode("%");
			this.setAgeRange("%");
			this.setGenderCode("%");
			this.setFromAge("%");
			this.setToAge("%");
		}

		public String getAgeRange() {
			return ageRange;
		}
		public void setAgeRange(String ageRange) {
			this.ageRange = ageRange;
		}
		public String getReligionCode() {
			return religionCode;
		}
		public void setReligionCode(String religionCode) {
			this.religionCode = religionCode;
		}
		public String getGenderCode() {
			return genderCode;
		}
		public void setGenderCode(String genderCode) {
			this.genderCode = genderCode;
		}
		public String getFromAge() {
			return fromAge;
		}
		public void setFromAge(String fromAge) {
			this.fromAge = fromAge;
		}
		public String getToAge() {
			return toAge;
		}
		public void setToAge(String toAge) {
			this.toAge = toAge;
		}
		public String getOption() {
			return option;
		}
		public void setOption(String option) {
			this.option = option;
		}
		
	}

