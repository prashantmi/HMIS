package hisglobal.business;

import org.jfree.chart.JFreeChart;

import net.sf.jasperreports.engine.JasperPrint;
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.vo.ReportVO;

public interface ReportBOi
{

	public JasperPrint generateReport(ReportVO _reportVO) throws HisReportDataNotFoundException;

	public JFreeChart generateGraph(ReportVO _reportVO);

	public JFreeChart generateAgewiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException;

	public JFreeChart generateCategoryWiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException;

	public JFreeChart generateGroupWiseCashCollGraph(ReportVO _reportVO) throws HisReportDataNotFoundException;

	public JFreeChart generateDepartmentWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException;

	public JFreeChart generateRoomWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException;

	public JFreeChart generateTotalPatStatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException;

}
