package hisglobal.business;

import org.jfree.chart.JFreeChart;

import net.sf.jasperreports.engine.JasperPrint;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.vo.ReportVO;

public class ReportsDelegate extends Delegate
{

	public ReportsDelegate()
	{
		super(new ReportBO()); // /<<Setting the service provider
	}

	public JasperPrint generateReport(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateReport(_reportVO));
	}

	public JFreeChart generateGraph(ReportVO _reportVO)
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateGraph(_reportVO));
	}

	public JFreeChart generateAgewiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateAgewiseGraph(_reportVO));
	}

	public JFreeChart generateCategoryWiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateCategoryWiseGraph(_reportVO));
	}

	public JFreeChart generateGroupWiseCashCollGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateGroupWiseCashCollGraph(_reportVO));
	}

	public JFreeChart generateDepartmentWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateDepartmentWiseTotalPatGraph(_reportVO));
	}

	public JFreeChart generateRoomWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateRoomWiseTotalPatGraph(_reportVO));
	}

	public JFreeChart generateTotalPatStatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		ReportBOi serviceBO = (ReportBOi) super.getServiceProvider();
		return (serviceBO.generateTotalPatStatGraph(_reportVO));
	}
}
