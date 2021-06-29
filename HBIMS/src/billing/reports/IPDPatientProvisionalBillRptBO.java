

	package billing.reports;

	public class IPDPatientProvisionalBillRptBO {
		
		public void getPatientDtls(IPDPatientProvisionalBillRptVO voObj) {

			try {

				IPDPatientProvisionalBillRptDAO.getPatientDtls(voObj);

				if (voObj.getStrMsgType().equals("1")) {
					voObj.setStrMsgString("IpdBillManagementTransNewBO."
							+ "getPatientDtls() --> " + voObj.getStrMsgString());
				}
			} catch (Exception e) {
				voObj.setStrMsgString("IpdBillManagementTransNewBO.getPatientDtls() --> "
						+ e.getMessage());
				voObj.setStrMsgType("1");
			} 

		}

	}
