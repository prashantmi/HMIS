package hisglobal.presentation;

import org.jfree.chart.JFreeChart;
import net.sf.jasperreports.engine.JasperPrint;

import hisglobal.business.ReportsDelegate;
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.vo.ReportVO;

public class ReportDATA extends ControllerDATA
{

	public static JasperPrint generateReport(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		return new ReportsDelegate().generateReport(_reportVO);
	}

	public static JFreeChart generateGraph(ReportVO _reportVO)
	{
		return new ReportsDelegate().generateGraph(_reportVO);
	}

	public static JFreeChart generateAgewiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		return new ReportsDelegate().generateAgewiseGraph(_reportVO);
	}

	public static JFreeChart generateCategoryWiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		return new ReportsDelegate().generateCategoryWiseGraph(_reportVO);
	}

	public static JFreeChart generateGroupWiseCashCollGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		return new ReportsDelegate().generateGroupWiseCashCollGraph(_reportVO);
	}

	public static JFreeChart generateDepartmentWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		return new ReportsDelegate().generateDepartmentWiseTotalPatGraph(_reportVO);
	}

	public static JFreeChart generateRoomWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		return new ReportsDelegate().generateRoomWiseTotalPatGraph(_reportVO);
	}

	public static JFreeChart generateTotalPatStatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		return new ReportsDelegate().generateTotalPatStatGraph(_reportVO);
	}
}
