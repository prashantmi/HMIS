package opd.transaction.controller.util;

/**
 * @copyright CDAC
 * @author Pragya Sharma
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.ChartCellData;
import hisglobal.utility.generictemplate.ChartColumnHead;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.ChartReportingDATA;
import opd.transaction.controller.fb.ChartReportingFB;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import registration.controller.fb.CRNoFB;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class ChartReportingUTIL extends ControllerUTIL
{
	/**
	 * Setting Chart Reporting Essentials
	 * 
	 * @param _fb Form Bean
	 * @param _rq HttpServletRequest
	 */
	
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setChartReportingEssentials(ChartReportingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map essentialMap = new HashMap();
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), null));
			_fb.setToDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), null));

			// Getting Patient Detail
			CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _rq);
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			// Setting Desk Essentials
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			//_fb.setDeptUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}*/
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setDeptUnitCode(voDP.getDepartmentUnitCode());
			if(voDP.getPatAdmNo()!=null)
				_fb.setAdmissionNo(voDP.getPatAdmNo());
			else
				_fb.setAdmissionNo("");
			_fb.setFromDate(voDP.getEpisodeDate());

			HelperMethods.populatetToNullOrEmpty(voDP, patDtlVO);

			essentialMap = ChartReportingDATA.getChartReportingEssentials(_fb.getDeskType(), _fb.getDeptUnitCode(),voDP, userVO);
			String strDate = (String) essentialMap.remove(OpdConfig.OPD_ESSENTIAL_DATE_EPISODE_OR_ADMISSION_START);
			_fb.setEpiAdmStartDate(strDate);
			_fb.setFromDate(strDate);
			_fb.setSortType(Config.SORT_TYPE_ASC);
			_fb.setOldSortType(Config.SORT_TYPE_ASC);
			
			Map<String, Map> mpDefulatCharts = (Map<String, Map>)essentialMap.get(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS);
			if(mpDefulatCharts.keySet().size()>0)
				_fb.setHaveDefault(true);

			WebUTIL.setMapInSession(essentialMap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/**
	 * Getting Chart Data
	 * 
	 * @param _fb Form Bean
	 * @param _rq HttpServletRequest
	 */
	public static boolean getChartViewData(ChartReportingFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mp = new HashMap();

		try
		{
			UserVO userVO = getUserVO(_rq);

			PatientDetailVO patDtlVO = new PatientDetailVO();
			HelperMethods.populate(patDtlVO, _fb);
			// Getting Patient Detail Age, Gender
			PatientDetailVO ptDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			HelperMethods.populatetToNullOrEmpty(patDtlVO, ptDtlVO);
			
			List<ChartMasterVO> lstCharts = (List<ChartMasterVO>)session.getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE);
			ChartMasterVO chartDtl = null;
			for(ChartMasterVO vo : lstCharts)
				if(vo.getChartId().equals(_fb.getChartId()))
				{
					chartDtl = vo;
					break;
				}				
			_fb.setChartHeader(chartDtl.getChartName());
			_fb.setIsGraph(chartDtl.getIsGraph());
			
			List<ChartParameterMappingVO> lstChartParas = null;			
			if(_fb.getChartPara()!=null && _fb.getChartPara().length>0)
			{
				lstChartParas = new ArrayList<ChartParameterMappingVO>();
				List<ChartParameterMappingVO> lstAllPara = (List<ChartParameterMappingVO>)session.getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_PARAMETERS);
				for(ChartParameterMappingVO vo : lstAllPara)
				{
					for(String para : _fb.getChartPara())
						if(para.equals(vo.getParaId()))
						{
							lstChartParas.add(vo);
							break;
						}
				}
			}
			//------
			_fb.setFilterCriteria(OpdConfig.CHOICE_DATE_WISE);
			mp = ChartReportingDATA.getChartReportingData(_fb.getDeskType(), patDtlVO, chartDtl, lstChartParas, _fb.getFilterCriteria(), _fb.getFromDate(), _fb.getToDate(), userVO);

			// Setting Selected Chart in the format as added all Default Chart for using common View Tile
			Map<String, Object> mpViewChart = new HashMap();
			LinkedHashMap<String, ChartColumnHead> mpColHead = (LinkedHashMap<String, ChartColumnHead>)mp.remove(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
			LinkedHashMap<String, Map<String,ChartCellData>> mpChartData = (LinkedHashMap<String, Map<String,ChartCellData>>)mp.remove(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);
			mpViewChart.put(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL, mpColHead);
			mpViewChart.put(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL, mpChartData);
			Map<String, Map> mpCharts = new HashMap<String, Map>();
			mpCharts.put(_fb.getChartId(), mpViewChart);			
			mp.put(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS, mpCharts);
			
			WebUTIL.setMapInSession(mp, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	/**
	 * Customize Chart Data
	 * 
	 * @param _fb Form Bean
	 * @param _rq HttpServletRequest
	 */
	public static boolean customizeChart(ChartReportingFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);

		try
		{
			UserVO userVO = getUserVO(_rq);

			PatientDetailVO patDtlVO = new PatientDetailVO();
			HelperMethods.populate(patDtlVO, _fb);
			
			List<ChartMasterVO> lstCharts = (List<ChartMasterVO>)session.getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE);
			ChartMasterVO chartDtl = null;
			for(ChartMasterVO vo : lstCharts)
				if(vo.getChartId().equals(_fb.getChartId()))
				{
					chartDtl = vo;
					break;
				}
				
			_fb.setChartHeader(chartDtl.getChartName());
			List<ChartParameterMappingVO> lstChartParas = ChartReportingDATA.getChartParamtersList(chartDtl, userVO);
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_PARAMETERS, lstChartParas);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	/**
	 * Order Chart Data
	 * 
	 * @param _fb Form Bean
	 * @param _rq HttpServletRequest
	 */
	public static boolean orderChart(ChartReportingFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);

		try
		{
			if(!_fb.getOldSortType().equals(_fb.getSortType()))
			{
				Map<String, Map> mpCharts = (Map<String, Map>) session.getAttribute(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS);
				Map<String, Object> mpViewChart = (Map<String, Object>) mpCharts.get(_fb.getSortChartId());
				
				LinkedHashMap<String, Map<String,ChartCellData>> mpChartData = (LinkedHashMap<String, Map<String,ChartCellData>>)mpViewChart.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);
				LinkedHashMap<String, Map<String,ChartCellData>> mpNewChartData = new LinkedHashMap<String, Map<String,ChartCellData>>();
				Set<String> stDates = mpChartData.keySet();
				LinkedList<String> lstDates = new LinkedList<String>();
				for(String date : stDates)
					lstDates.add(date);
				Collections.reverse(lstDates);
				for(String date : lstDates)
					mpNewChartData.put(date,mpChartData.get(date));
				
				mpViewChart.put(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL,mpNewChartData);
				mpCharts.put(_fb.getSortChartId(),mpViewChart);
				session.setAttribute(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS, mpCharts);
				_fb.setOldSortType(_fb.getSortType());
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	/**
	 * Order Chart Data
	 * 
	 * @param _fb Form Bean
	 * @param _rq HttpServletRequest
	 */
	public static boolean showChartGraph(ChartReportingFB _fb, HttpServletRequest _rq, HttpServletResponse _res)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);

		try
		{
			Map<String, Map> mpCharts = (Map<String, Map>) session.getAttribute(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS);
			Map<String, Object> mpViewChart = (Map<String, Object>) mpCharts.get(_fb.getGraphChartId());

			LinkedHashMap<String, ChartColumnHead> mpColHead = (LinkedHashMap<String, ChartColumnHead>)mpViewChart.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL);
			LinkedHashMap<String, Map<String,ChartCellData>> mpChartData = (LinkedHashMap<String, Map<String,ChartCellData>>)mpViewChart.get(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL);
				
			//JFreeChart jFreeChart = generateSingleYLineChart(_fb.getGraphChartName(), "Record Date", mpColHead.get((String)mpColHead.keySet().toArray()[0]).getName(),(String)mpColHead.keySet().toArray()[0], mpChartData );
			JFreeChart jFreeChart = generateMultipleAxisLineChart(_fb.getGraphChartName(), "Record Date", mpColHead, mpChartData );
			WebUTIL.getPreparedChart(jFreeChart,_rq,_res);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	// Generate Line Chart
	private static JFreeChart generateSingleYLineChart(String _title, String _xName, 
			String _yName, String _yNameVal,
			LinkedHashMap<String, Map<String,ChartCellData>> _mpChartData)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try
		{
			for(String xVal : _mpChartData.keySet())
				for(String yVal : _mpChartData.get(xVal).keySet())
					if(yVal.equals(_yNameVal))
						dataset.addValue(Double.parseDouble(_mpChartData.get(xVal).get(yVal).getValue()), _yName, xVal);
			
			chart = ChartFactory.createLineChart(_title, _xName, _yName, dataset, PlotOrientation.VERTICAL, true, true, false);
			System.out.println("plot in line ------------------------------------" + chart.getPlot());
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			plot.setNoDataMessage("NO DATA AVAILABLE!!");

			NumberAxis rangeaxis = (NumberAxis) plot.getRangeAxis();
			rangeaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeaxis.setAutoRange(true);
			
//			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//			renderer.setSeriesShapesFilled(1, true);
//			renderer.setSeriesStroke(0, new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,new float[]{ 10.0f, 6.0f }, 0.0f));
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	// Generate Line Chart
	private static JFreeChart generateMultipleYLineChart(String _title, String _xName, 
			LinkedHashMap<String, ChartColumnHead> _mpHeader, 
			LinkedHashMap<String, Map<String,ChartCellData>> _mpChartData)
	{
		JFreeChart chart = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try
		{
			for(String xVal : _mpChartData.keySet())
				for(String yVal : _mpChartData.get(xVal).keySet())
					dataset.addValue(Double.parseDouble(_mpChartData.get(xVal).get(yVal).getValue()), _mpHeader.get(yVal).getName(), xVal);
			
			// Creating Chart & Setting Related Features
			chart = ChartFactory.createLineChart(_title, _xName, "", dataset, PlotOrientation.VERTICAL, true, true, false);
			chart.setBackgroundPaint(Color.WHITE);
			
			CategoryPlot plot = (CategoryPlot) chart.getPlot();

			// Plot Related Features			
			plot.setBackgroundPaint(Color.WHITE);
			plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			plot.setNoDataMessage("NO DATA AVAILABLE!!");

			// Domain Axis
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

			// Range Axis
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				// change the auto tick unit selection to integer units only...
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setAutoRangeIncludesZero(false);
				// set the stroke for each series...
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
			renderer.setBaseShapesVisible(true);
			renderer.setBaseItemLabelsVisible(true);
			//renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,1.0f, new float[] {10.0f, 6.0f}, 0.0f));
			//renderer.setSeriesStroke(1, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,1.0f, new float[] {6.0f, 6.0f}, 0.0f));
			//renderer.setSeriesStroke(2, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,1.0f, new float[] {2.0f, 6.0f}, 0.0f));
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	// Generate Line Chart
	private static JFreeChart generateMultipleYTimeChart(String _title, String _xName, 
			LinkedHashMap<String, ChartColumnHead> _mpHeader, 
			LinkedHashMap<String, Map<String,ChartCellData>> _mpChartData)
	{
		JFreeChart chart = null;
		try
		{
			LinkedHashMap<String, TimeSeries> mpTimeSeries = new LinkedHashMap<String, TimeSeries>();
			for(String paraId : _mpHeader.keySet())
				mpTimeSeries.put(paraId, new TimeSeries(((ChartColumnHead)_mpHeader.get(paraId)).getName()));
			
			SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getInstance();
			for(String xVal : _mpChartData.keySet())
				for(String yVal : _mpChartData.get(xVal).keySet())
				{
					RegularTimePeriod regTime = null;
					Date objDate = null;
					
					try
					{
						sdf.applyPattern("dd-MMM-yyyy HH:mm");
						objDate = sdf.parse(xVal);
						regTime = new Minute(objDate);
					}
					catch(ParseException pe)
					{
						sdf.applyPattern("dd-MMM-yyyy");
						objDate = sdf.parse(xVal);
						regTime = new Day(objDate);
					}
					TimeSeries ser = mpTimeSeries.get(yVal);
					ser.add(regTime ,Double.parseDouble(_mpChartData.get(xVal).get(yVal).getValue()));
				}
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			for(String paraId : mpTimeSeries.keySet())
				dataset.addSeries(mpTimeSeries.get(paraId));

			// Creating Chart & Setting Related Features
			chart = ChartFactory.createTimeSeriesChart(_title, _xName, "", dataset, true, true, false);
			chart.setBackgroundPaint(Color.WHITE);
			
			XYPlot plot = (XYPlot) chart.getPlot();
			// Plot Related Features			
			plot.setBackgroundPaint(Color.WHITE);
			plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
			plot.setNoDataMessage("NO DATA AVAILABLE!!");

			// Domain Axis
			DateAxis domainDateAxis = (DateAxis) plot.getDomainAxis();
			domainDateAxis.setDateFormatOverride(sdf);
			domainDateAxis.setVerticalTickLabels(true);			
			domainDateAxis.setAutoTickUnitSelection(true,true);
			
			// Range Axis
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				// change the auto tick unit selection to integer units only...
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setAutoRangeIncludesZero(false);
				// set the stroke for each series...
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
			renderer.setBaseShapesVisible(true);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}
	
	// Generate Multiple Axis Line Chart
	private static JFreeChart generateMultipleAxisLineChart(String _title, String _xName, 
			LinkedHashMap<String, ChartColumnHead> _mpHeader, 
			LinkedHashMap<String, Map<String,ChartCellData>> _mpChartData)
	{
		JFreeChart chart = null;
		try
		{
//			DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
//			int option1 = 209, option2 = 4058, option4 = 3490, option3 = 5245, option5 = 980;
//			dataset1.addValue(option1, "Option", "Year 1");
//			dataset1.addValue(option2, "Option", "Year 2");
//			dataset1.addValue(option3, "Option", "Year 3");
//			dataset1.addValue(option4, "Option", "Year 4");
//			dataset1.addValue(option5, "Option", "Year 5");
//
//			// create the first renderer...
//			CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
//			CategoryItemRenderer renderer = new BarRenderer();
//			renderer.setBaseItemLabelGenerator(generator);
//			renderer.setBaseItemLabelsVisible(true);
//			// now create the second dataset and renderer...
//			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
//			dataset2.addValue(1.01, "Tag", "Year 1");
//			dataset2.addValue(2.09, "Tag", "Year 2");
//			dataset2.addValue(3.08, "Tag", "Year 3");
//			dataset2.addValue(4.07, "Tag", "Year 4");
//			dataset2.addValue(7.06, "Tag", "Year 5");
//
//			CategoryItemRenderer renderer2 = new LineAndShapeRenderer();

			LinkedHashMap<String, TimeSeries> mpTimeSeries = new LinkedHashMap<String, TimeSeries>();
			LinkedHashMap<String, TimeSeriesCollection> mpTimeSeriesColl = new LinkedHashMap<String, TimeSeriesCollection>();
			LinkedHashMap<String, XYLineAndShapeRenderer> mpRenderer = new LinkedHashMap<String, XYLineAndShapeRenderer>();
			
			for(String paraId : _mpHeader.keySet())
				mpTimeSeries.put(paraId, new TimeSeries(((ChartColumnHead)_mpHeader.get(paraId)).getName()));
			
			SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getInstance();
			for(String xVal : _mpChartData.keySet())
				for(String yVal : _mpChartData.get(xVal).keySet())
				{
					RegularTimePeriod regTime = null;
					Date objDate = null;
					
					try
					{
						sdf.applyPattern("dd-MMM-yyyy HH:mm");
						objDate = sdf.parse(xVal);
						regTime = new Minute(objDate);
					}
					catch(ParseException pe)
					{
						sdf.applyPattern("dd-MMM-yyyy");
						objDate = sdf.parse(xVal);
						regTime = new Day(objDate);
					}
					TimeSeries ser = mpTimeSeries.get(yVal);  //
					//if(!_mpChartData.get(xVal).get(yVal).getValue().equalsIgnoreCase("--")  || !_mpChartData.get(xVal).get(yVal).getValue().equalsIgnoreCase("c1"))
						if(_mpChartData.get(xVal).get(yVal).getValue().matches("-?\\d+(\\.\\d+)?"))
					ser.add(regTime ,Double.parseDouble(_mpChartData.get(xVal).get(yVal).getValue()));
				}

			for(String paraId : mpTimeSeries.keySet())
				mpTimeSeriesColl.put(paraId, new TimeSeriesCollection(mpTimeSeries.get(paraId)));

			// Domain Axis
			DateAxis domainDateAxis = new DateAxis(_xName);
			domainDateAxis.setDateFormatOverride(sdf);
			domainDateAxis.setVerticalTickLabels(true);			
			domainDateAxis.setAutoTickUnitSelection(true,true);
			
			for(String paraId : _mpHeader.keySet())
			{
				XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
				renderer.setBaseShapesVisible(true);
				renderer.setBaseItemLabelsVisible(true);
				mpRenderer.put(paraId, renderer);
			}
			
//			// Creating Chart & Setting Related Features
//			chart = ChartFactory.createTimeSeriesChart(_title, _xName, "", dataset, true, true, false);
//			chart.setBackgroundPaint(Color.WHITE);
			
			//CategoryPlot plot = new CategoryPlot();
//			plot.setDataset(dataset1);
//			plot.setRenderer(renderer);
//			plot.setRangeAxis(new NumberAxis("Options"));
//			plot.setDataset(1, dataset2);
//			plot.setRenderer(1, renderer2);
//			NumberAxis axis2 = new NumberAxis("Tag");
//			plot.setRangeAxis(1, axis2);
//			plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			//plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
			//plot.mapDatasetToRangeAxis(1, 1);

			XYPlot xyPlot = new XYPlot();
			// Plot Related Features			
			xyPlot.setBackgroundPaint(Color.WHITE);
			xyPlot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			xyPlot.setDomainGridlinePaint(Color.LIGHT_GRAY);
			xyPlot.setNoDataMessage("NO DATA AVAILABLE!!");
			xyPlot.setOrientation(PlotOrientation.VERTICAL);
			xyPlot.setRangeGridlinesVisible(true);
			xyPlot.setDomainGridlinesVisible(true);
			xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
			xyPlot.setDomainAxis(domainDateAxis);

			int i=0;
			for(String paraId : _mpHeader.keySet())
			{
				xyPlot.setDataset(i, mpTimeSeriesColl.get(paraId));
				xyPlot.setRenderer(i, mpRenderer.get(paraId));

				// Range Axis
				NumberAxis rangeAxis = new NumberAxis(((ChartColumnHead)_mpHeader.get(paraId)).getName());
				rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				rangeAxis.setAutoRangeIncludesZero(false);
				xyPlot.setRangeAxis(i, rangeAxis);
				xyPlot.mapDatasetToRangeAxis(i, i);
				if(i%2==0)
					xyPlot.setRangeAxisLocation(i, AxisLocation.BOTTOM_OR_LEFT);
				else
					xyPlot.setRangeAxisLocation(i, AxisLocation.BOTTOM_OR_RIGHT);
				i++;
			}
			chart = new JFreeChart(xyPlot);
			chart.setTitle(_title);
			chart.setBackgroundPaint(Color.WHITE);
			//ChartUtilities.applyCurrentTheme(chart);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}
	
	public static void writeChartAsPDF(OutputStream out, JFreeChart chart, int width, int height, FontMapper mapper) throws IOException
	{
		Rectangle pagesize = new Rectangle(width, height);
		Document document = new Document(pagesize, 50, 50, 50, 50);
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.addAuthor("JFreeChart");
			document.addSubject("Demonstration");
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			PdfTemplate tp = cb.createTemplate(width, height);
			Graphics2D g2 = tp.createGraphics(width, height, mapper);
			Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
			chart.draw(g2, r2D);
			g2.dispose();
			cb.addTemplate(tp, 0, 0);
		}
		catch (DocumentException de)
		{
			System.err.println(de.getMessage());
		}
	}
}
