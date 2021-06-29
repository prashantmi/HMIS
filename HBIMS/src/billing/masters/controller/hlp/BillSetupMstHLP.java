
/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: BillSetup Master HLP
 * 
 * Created on: 16-09-2011
 */



package billing.masters.controller.hlp;

import hisglobal.utility.HisUtil;
import billing.masters.controller.data.BillSetupMstDATA;
import billing.masters.controller.fb.BillSetupMstFB;

public class BillSetupMstHLP {

	/**
	 * returns Emergency MultiRow Html Contents based on the index passed.
	 * 
	 * @param formBean
	 * @param index
	 * @return Emergency MultiRow Contests.
	 */
	public String getMultiRow(BillSetupMstFB fb, int index) {

		StringBuffer sBuffer = new StringBuffer("");
		int gLen = 0;
		if (fb.getEmgGroupName() == null) {

			gLen = 0;

		} else {
			gLen = fb.getEmgGroupName().length;

		}
		fb.setTotalRows(gLen);

		for (int i = 1; i <= fb.getTotalRows(); i++) {

			fb.setEmgGrpTmpName(fb.getEmgGroupName()[i - 1]);
			fb.setEmgTrfTmpName(fb.getEmgTariffName()[i - 1]);
			fb.setEmgUntTmpName(fb.getEmgUnit()[i - 1]);

			sBuffer.append("<div id=\"id" + index + "-" + i + "\" >");
			sBuffer
					.append("<table width='100%'> <tr><td width='32%' class='multiControl'><select name='emgGroupName'");
			sBuffer.append("id='emgGroupName" + index + "-" + i
					+ "' onChange=\"myFunc('1',this,'" + index + "-" + i
					+ "');\">");
			sBuffer.append(fb.getEmgGroupValues());
			sBuffer.append("</select></td>");
			sBuffer.append("<td width='31%' class='multiControl'>");
			sBuffer.append("<div id='emgTariffId" + index + "-" + i
					+ "'> <select name='emgTariffName' id='emgTariffName"
					+ index + "-" + i + "'>");
			sBuffer.append(fb.getEmgTariffValues());
			sBuffer
					.append("<td width='31%' class='multiControl'><select name='emgUnit' id='emgUnit"
							+ index + "-" + i + "'>");
			if (fb.getEmgUntTmpName().equals("1"))
				sBuffer
						.append("<option value='1' selected='selected' >Daily</option><option value='0'>One Time</option></select></td>");
			else
				sBuffer
						.append("<option value='1' >Daily</option><option value='0' selected='selected'>One Time</option></select></td>");

			sBuffer
					.append("<td width='6%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
			sBuffer.append(" onClick=\"deleteRow('" + index + "-" + i + "','"
					+ index + "','0');\"></td></tr></table>");
			sBuffer.append("</div>");

		}

		fb.setEmgGrpTmpName("0");
		fb.setEmgTrfTmpName("0");
		fb.setEmgUntTmpName("0");

		return sBuffer.toString();
	}

	/**
	 * returns IPD MultiRow Html Contents based on the index passed.
	 * 
	 * @param fb
	 * @param index
	 * @return Ipd MultiRow Contests.
	 */
	public String getIpdMultiRow(BillSetupMstFB fb, int index) {

		StringBuffer sBuffer = new StringBuffer("");
		int gLen = 0;

		if (fb.getGroupName() == null) {
			gLen = 0;
		} else {

			gLen = fb.getGroupName().length;
		}
		fb.setTotalRows(gLen);

		for (int i = 1; i <= fb.getTotalRows(); i++) {

			fb.setIpdGrpTmpName(fb.getGroupName()[i - 1]);
			fb.setIpdTrfTmpName(fb.getTariffName()[i - 1]);
			fb.setIpdUntTmpName(fb.getUnit()[i - 1]);

			sBuffer.append("<div id=\"id" + index + "-" + i + "\" >");
			sBuffer
					.append("<table width='100%'> <tr><td width='32%' class='multiControl'><select name='groupName'");
			sBuffer.append("id='groupName" + index + "-" + i
					+ "' onChange=\"myFunc('1',this,'" + index + "-" + i
					+ "');\">");
			sBuffer.append(fb.getIpdComplChrgGroupValues());
			sBuffer.append("</select></td>");
			sBuffer.append("<td width='31%' class='multiControl'>");
			sBuffer.append("<div id='tariffId1" + index + "-" + i
					+ "'> <select name='tariffName' id='tariffName" + index
					+ "-" + i + "'>");
			sBuffer.append(fb.getIpdComplChrgTariffValues());
			sBuffer
					.append("<td width='31%' class='multiControl'><select name='unit' id='unit"
							+ index + "-" + i + "'>");
			if (fb.getIpdUntTmpName().equals("1"))
				sBuffer
						.append("<option value='1' selected='selected' >Daily</option><option value='0'>One Time</option></select></td>");
			else
				sBuffer
						.append("<option value='1' >Daily</option><option value='0' selected='selected'>One Time</option></select></td>");

			sBuffer
					.append("<td width='6%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
			sBuffer.append(" onClick=\"deleteRow('" + index + "-" + i + "','"
					+ index + "','0');\"></td></tr></table>");
			sBuffer.append("</div>");

		}

		fb.setIpdGrpTmpName("0");
		fb.setIpdTrfTmpName("0");
		fb.setIpdUntTmpName("0");

		return sBuffer.toString();
	}

	/**
	 * returns IPDSecMultiRow Html Contents based on the index passed.
	 * 
	 * @param fb
	 * @param index
	 * @return IPDSecMultiRow MultiRow Contests.
	 */
	public String getIpdSecMultiRow(BillSetupMstFB fb, int index) {

		StringBuffer sBuffer = new StringBuffer("");
		HisUtil hisUtil =null;
		hisUtil = new HisUtil("Bill Setup",	"BillSetupMstVO.getGroupValues");
		
		BillSetupMstDATA data=new BillSetupMstDATA();

		try {
			int gLen = 0;
			if (fb.getSecGroupName() == null) {
				gLen = 0;
			} else {

				gLen = fb.getSecGroupName().length;
			}
			fb.setTotalRows(gLen);

			for (int i = 1; i <= fb.getTotalRows(); i++) {
				fb.setIpdSecWrdTmpName(fb.getSecWardName()[i - 1]);
				fb.setIpdSecGrpTmpName(fb.getSecGroupName()[i - 1]);
				fb.setIpdSecTrfTmpName(fb.getSecTariffName()[i - 1]);

							
				String id="secWardName"+index+"-"+i;
				sBuffer.append("<div id=\"id" + index + "-" + i + "\" >");
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellpadding='1' cellspacing='1'> <tr><td width='15%' class='multiControl'>");
				sBuffer.append("<select name='secWardName' value='"+fb.getIpdSecWrdTmpName()+"' id='"+id+"' >");
				sBuffer.append(data.getIpdComplChrgWardValues(fb));
				sBuffer.append("</select></td>");
				sBuffer.append("<td width='30%' class='multiControl'><select name='secGroupName' class='comboMax' value ="+fb.getIpdSecGrpTmpName()+" ");
				sBuffer.append("id='secGroupName" + index + "-" + i+ "' onChange=\"myFunc('4',this,'" + index + "-"+ i+ "');\">");
				sBuffer.append(data.getIpdComplChrgGroupValues(fb));
				sBuffer.append("</select></td>");
				sBuffer.append("<td width='50%' class='multiControl'>");
				sBuffer.append("<div id='secTariff" + index + "-" + i + "'>");
				sBuffer.append("<select name='secTariffName' value ="+fb.getIpdSecTrfTmpName()+"  class='comboBig' id='secTariffName"+ index + "-" + i + "'>");
				sBuffer.append(data.getIpdComplChrgTariffValues(fb));
				sBuffer.append("</select><td width='5%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
				sBuffer.append(" onClick=\"deleteRow('" + index + "-" + i + "','"	+ index + "','0');\"></td></tr></table>");
				sBuffer.append("</div>");

			}

			fb.setIpdSecWrdTmpName("0");
			fb.setIpdSecGrpTmpName("0");
			fb.setIpdSecTrfTmpName("0");
		} catch (Exception e) {
	 
			e.printStackTrace();
		}
		return sBuffer.toString();
	}

	/**
	 * returns IpdThirdMultiRow Html Contents based on the index passed.
	 * 
	 * @param fb
	 * @param index
	 * @return IpdThirdMultiRow MultiRow Contests.
	 */
	public String getIpdThirdMultiRow(BillSetupMstFB fb, int index) {

		StringBuffer sBuffer = new StringBuffer("");
		BillSetupMstDATA data=new BillSetupMstDATA();

		int gLen = 0;
		if (fb.getThirdGroupName() == null) {
			gLen = 0;
		} else {

			gLen = fb.getThirdGroupName().length;
		}
		fb.setTotal3Rows(gLen);

		for (int i = 1; i <= fb.getTotal3Rows(); i++) {
			fb.setIpdThirdWardTmpName(fb.getThirdWardName()[i - 1]);
			fb.setIpdThirdGrpTmpName(fb.getThirdGroupName()[i - 1]);
			fb.setIpdThirdTrfTmpName(fb.getThirdTariffName()[i - 1]);
			
			
			fb.setIpdSecWrdTmpName(fb.getIpdThirdWardTmpName());
			fb.setIpdSecGrpTmpName(fb.getIpdThirdGrpTmpName());
			fb.setIpdSecTrfTmpName(fb.getIpdThirdTrfTmpName());
			
			String id="thirdWardName"+index+"-"+i;

			sBuffer.append("<div id=\"id" + index + "-" + i + "\" >");
			sBuffer.append("<table class='TABLEWIDTH' align='center' cellpadding='1' cellspacing='1'> <tr><td width='15%' class='multiControl'>");
			sBuffer.append("<select name='thirdWardName' selected ="+fb.getIpdThirdWardTmpName()+" id='"+id+"' ");
			sBuffer.append(data.getIpdComplChrgWardValues(fb));
			sBuffer.append("</select></td>");
			sBuffer
					.append("<td width='30%' class='multiControl'><select name='thirdGroupName' class='comboMax' selected="+fb.getIpdThirdGrpTmpName()+" ");
			sBuffer.append("id='thirdGroupName" + index + "-" + i+ "' onChange=\"myFunc('2',this,'" + index + "-" + i
					+ "');\">");
			sBuffer.append(data.getIpdComplChrgGroupValues(fb));
			sBuffer.append("</select></td>");
			sBuffer.append("<td width='50%' class='multiControl'>");
			sBuffer.append("<div id='thirdTariff" + index + "-" + i
					+ "'> <select name='thirdTariffName' selected ="+fb.getIpdThirdTrfTmpName()+" class='comboBig' id='thirdTariffName" 
					+ index + "-" + i + "'>");
			sBuffer.append(data.getIpdComplChrgTariffValues(fb));
			sBuffer
					.append("</select><td width='5%' class='multiControl' ><img src='../../hisglobal/images/minus.gif'");
			sBuffer.append(" onClick=\"deleteRow('" + index + "-" + i + "','"
					+ index + "','0');\"></td></tr></table>");
			sBuffer.append("</div>");

		}
		fb.setIpdThirdWardTmpName("0");
		fb.setIpdThirdGrpTmpName("0");
		fb.setIpdThirdTrfTmpName("0");

		return sBuffer.toString();
	}

}
