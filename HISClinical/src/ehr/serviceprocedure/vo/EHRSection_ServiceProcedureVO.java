/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.serviceprocedure.vo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hisglobal.utility.HelperMethods;
import hisglobal.vo.ValueObject;

	public class EHRSection_ServiceProcedureVO extends ValueObject
	{
		private String procedureCode;
		private String procedureName;
		private String entryDate;
		
		
		public String getProcedureCode() {
			return procedureCode;
		}
		public void setProcedureCode(String procedureCode) {
			this.procedureCode = procedureCode;
		}
		public String getProcedureName() {
			return procedureName;
		}
		public void setProcedureName(String procedureName) {
			this.procedureName = procedureName;
		}
		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		
	}
