package inpatient.transaction.controller.fb;

import opd.transaction.controller.fb.GenericTemplateTileFB;

public class VitalsRecordingFB extends GenericTemplateTileFB
{
	private String vitalChartFlag;
	private String htmlVitalChartData;

	public String getHtmlVitalChartData()
	{
		return htmlVitalChartData;
	}

	public void setHtmlVitalChartData(String htmlVitalChartData)
	{
		this.htmlVitalChartData = htmlVitalChartData;
	}

	public String getVitalChartFlag()
	{
		return vitalChartFlag;
	}

	public void setVitalChartFlag(String vitalChartFlag)
	{
		this.vitalChartFlag = vitalChartFlag;
	}
}
