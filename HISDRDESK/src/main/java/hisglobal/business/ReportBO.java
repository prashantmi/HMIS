package hisglobal.business;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.ui.VerticalAlignment;

import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.ReportDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ReportVO;

public class ReportBO implements ReportBOi
{

	public JasperPrint generateReport(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		JasperPrint jPrint = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();

		try
		{
			//System.out.println(_reportVO.getJrxmlName());
			InputStream is = this.getClass().getResourceAsStream(_reportVO.getJrxmlPath() + _reportVO.getJrxmlName());
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			
			if(_reportVO.getDynamicQuery()!=null && _reportVO.getDynamicQuery().equals("")==false)
			{
			//System.out.println(jasperDesign.getQuery().getText());
			JRDesignQuery newQuery = new JRDesignQuery();
		    newQuery.setText(jasperDesign.getQuery().getText()+_reportVO.getDynamicQuery());
		   // System.out.println("all hospital Code "+_reportVO.getAllHospitalCode());
		   // System.out.println("explicit query ::"+jasperDesign.getQuery().getText()+_reportVO.getDynamicQuery());
		    jasperDesign.setQuery(newQuery);
			}
			//System.out.println("final Query="+jasperDesign.getQuery().getText());
			JasperReport jReport = JasperCompileManager.compileReport(jasperDesign);
			Map mpParameter = new HashMap();

			Class ReportVO = _reportVO.getClass();
			HelperMethods.populateParameteresForReport(mpParameter, _reportVO);
			//System.out.println(mpParameter);	
			jPrint = JasperFillManager.fillReport(jReport, mpParameter, tx.getConnection());
			List ls = jPrint.getPages();
			if (ls.size() == 0)
			{
				throw new HisReportDataNotFoundException();
			}
			return jPrint;

		}

		catch (JRException e)
		{

			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}

		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}

		return jPrint;

	}

	public JFreeChart generateGraph(ReportVO _reportVO)
	{
		JFreeChart chart = null;
		//System.out.println("in bo choice :::::::::::::::::::::" + _reportVO.getChoice());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DefaultPieDataset data = new DefaultPieDataset();

		tx.begin();
		try
		{
			ResultSet rs = null;
			ReportDAO reportDAO = new ReportDAO(tx);
			if (_reportVO.getChoice().equals("D"))

			{
				rs = reportDAO.generateGraphAgeWiseDatewise(_reportVO);
			}
			else
			// {rs= reportDAO.generateGraphAgeWise(_reportVO);}

			{
			}

			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			while (rs.next())
			{
				dataset.addValue(rs.getBigDecimal(8), rs.getString(2), rs.getString(1));
			}

			chart = ChartFactory.createLineChart3D("LINE CHART", // chart
					// title
					"Type", // domain axis label
					"NO. OF PATIENTS", // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // orientation
					true, // include legend
					true, // tooltips
					false // urls
					);
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.darkGray);
			plot.setOutlinePaint(Color.red);
			plot.setRangeGridlinePaint(Color.blue);
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setAutoRangeIncludesZero(true);
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

			// renderer.setDrawShapes(true);
			plot.setNoDataMessage("no!!!!!");
			renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
					new float[]
					{ 10.0f, 6.0f }, 0.0f));

		}

		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();

		}
		finally
		{
			//System.out.println("Close the transaction...");
			tx.close();
		}

		return chart;

	}

	public class CustomRenderer extends BarRenderer3D
	{

		private Paint[] colors;

		public CustomRenderer(final Paint[] colors)
		{
			this.colors = colors;
			this.setMaximumBarWidth(0.1);

		}

		public Paint getItemPaint(final int row, final int column)
		{
			return this.colors[column % this.colors.length];

		}
	}

	public JFreeChart genrateLineChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;
		String deptName = "";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try
		{
			while (rs.next())
			{
				deptName = rs.getString(2);
				dataset.addValue(rs.getInt(4), rs.getString(5), rs.getString(1));
			}
			chart = ChartFactory.createLineChart3D(chartTitle, "AGE RANGE", "NO OF PATIENTS", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			Font font = new Font("Dialog", Font.BOLD, 20);
			Paint paint = Color.BLACK;
			TextTitle deptTitle = new TextTitle("DEPARTMENT : " + deptName, font, paint, RectangleEdge.BOTTOM,
					HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			chart.addSubtitle(deptTitle);
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.DARK_GRAY);
			plot.setRangeGridlinePaint(Color.PINK);

			NumberAxis rangeaxis = (NumberAxis) plot.getRangeAxis();
			rangeaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeaxis.setAutoRangeIncludesZero(true);
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

			renderer.setSeriesShapesFilled(1, true);
			plot.setNoDataMessage("NO DATA AVAILABLE!!");
			renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
					new float[]
					{ 10.0f, 6.0f }, 0.0f));
			// Image i =new Image();
			// plot.setBackgroundImage()
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart genrateBarChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;
		try
		{
			if (rs == null)
			{
				//System.out.println("rs=null--------------------------------");
			}
			rs.last();
			int countRow = rs.getRow();
			String[] category = new String[1];
			String deptName = "";

			String[] ageRange = new String[countRow];

			double[][] noOfPatients = new double[1][];
			noOfPatients[0] = new double[countRow];
			int nextValueIndex = 0;
			rs.beforeFirst();
			while (rs.next())
			{
				category[0] = "Chart For" + rs.getString(5);
				deptName = rs.getString(2);
				//System.out.println(rs.getString(1));

				ageRange[nextValueIndex] = "AGE : " + rs.getString(1);
				noOfPatients[0][nextValueIndex] = rs.getInt(4);
				nextValueIndex++;
			}
			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(category, ageRange, noOfPatients);

			chart = ChartFactory.createBarChart3D("", // chart title
					"AGE RANGE", // domain axis label
					"NO. OF PATIENTS", // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // the plot orientation
					true, // include legend
					true, true);
			Font font = new Font("Dialog", Font.BOLD, 20);
			Paint paint = Color.BLACK;
			TextTitle deptTitle = new TextTitle("DEPARTMENT : " + deptName, font, paint, RectangleEdge.BOTTOM,
					HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			TextTitle chartTitleName = new TextTitle(chartTitle, font, Color.BLACK, RectangleEdge.TOP,
					HorizontalAlignment.CENTER, VerticalAlignment.TOP, Title.DEFAULT_PADDING);
			chart.setBackgroundPaint(Color.lightGray);
			chart.addSubtitle(deptTitle);
			chart.setTitle(chartTitleName);
			// get a reference to the plot for further customisation...
			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			final CategoryPlot plot = chart.getCategoryPlot();
			plot.setNoDataMessage("NO DATA AVAILABLE!");
			//System.out.println("plot type:::::::::::::::::::::::" + plot.getPlotType());

			final CategoryItemRenderer renderer = new CustomRenderer(new Paint[]
			{ Color.magenta, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.PINK, Color.gray,
					Color.red, Color.darkGray, Color.WHITE });
			renderer.setItemLabelsVisible(true);
			final ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER,
					45.0);
			renderer.setPositiveItemLabelPosition(p);
			renderer.setItemLabelPaint(Color.RED);
			plot.setRenderer(renderer);
			// change the margin at the top of the range axis...
			final ValueAxis rangeAxis = plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setLowerMargin(0.15);
			rangeAxis.setUpperMargin(0.15);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart genratePieChart(ResultSet rs, String chartTitel)
	{
		JFreeChart chart = null;
		DefaultPieDataset data = new DefaultPieDataset();
		String dep = "";
		String gender = "";
		try
		{
			while (rs.next())
			{
				String age = " AGERANGE = " + rs.getString(1) + "   " + "NO. OF PATIENT=" + rs.getInt(4);
				dep = rs.getString(2);
				gender = rs.getString(5);
				//System.out.println(age);
				data.setValue(age, rs.getBigDecimal(4));
			}

			//System.out.println(rs);

			chart = ChartFactory.createPieChart3D(chartTitel, data, true, true, true);

			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			PiePlot3D ppd = (PiePlot3D) chart.getPlot();
			ppd.setNoDataMessage("NO DATA AVAILABLE!");

			ppd.setMinimumArcAngleToDraw(5);

			//System.out.println("plot type:::::::::::::::::::::::" + ppd.getPlotType());

			Font font = new Font("Dialog", Font.BOLD, 20);
			Paint paint = Color.BLACK;
			TextTitle deptTitle = new TextTitle(dep, font, paint, RectangleEdge.BOTTOM, HorizontalAlignment.CENTER,
					VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			TextTitle genderTitle = new TextTitle(gender, font, paint, RectangleEdge.TOP, HorizontalAlignment.CENTER,
					VerticalAlignment.TOP, Title.DEFAULT_PADDING);
			chart.addSubtitle(deptTitle);
			chart.addSubtitle(genderTitle);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart generateAgewiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		JFreeChart chart = null;
		//System.out.println("in bo choice :::::::::::::::::::::" + _reportVO.getChoice());
		JDBCTransactionContext tx = new JDBCTransactionContext();

		tx.begin();

		try
		{
			ResultSet rs = null;
			ReportDAO reportDAO = new ReportDAO(tx);

			//System.out.println(_reportVO.getChoice());
			if (_reportVO.getChoice().equals("D"))
			{
				rs = reportDAO.generateGraphAgeWiseDatewise(_reportVO);
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();

				//System.out.println(_reportVO.getChartType());
				if (_reportVO.getChartType().equalsIgnoreCase("3"))
				{
					chart = genrateLineChart(rs, "AGEWISE DATEWISE CHART");
				}// end of inner if
				else
				{
					if (_reportVO.getChartType().equalsIgnoreCase("1"))
					{
						chart = genratePieChart(rs, "AGEWISE DATEWISE CHART");
					}
					else
					{
						chart = genrateBarChart(rs, "AGEWISE DATEWISE CHART");
					}
				}// end of else

				return chart;
			}
			else
			{
				rs = reportDAO.generateGraphAgeWiseToday(_reportVO);
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();
				if (_reportVO.getChartType().equalsIgnoreCase("3"))
				{
					chart = genrateLineChart(rs, "AGEWISE DATEWISE CHART");
				}// end of inner if
				else
				{
					if (_reportVO.getChartType().equalsIgnoreCase("1"))
					{
						chart = genratePieChart(rs, "AGEWISE TODAY CHART");
					}
					else
					{
						chart = genrateBarChart(rs, "AGEWISE TODAY CHART");
					}
				}
				return chart;
			}
		}// end of try
		catch (HisReportDataNotFoundException hfd)
		{
			throw new HisReportDataNotFoundException();
		}
		catch (Exception e)
		{

		}
		finally
		{
			//System.out.println("Close the transaction...");
			tx.close();

		}
		return null;

	}

	public JFreeChart generateCategoryWiseGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		JFreeChart chart = null;
		//System.out.println("in bo choice :::::::::::::::::::::" + _reportVO.getChoice());
		JDBCTransactionContext tx = new JDBCTransactionContext();

		tx.begin();

		try
		{
			ResultSet rs = null;
			ReportDAO reportDAO = new ReportDAO(tx);

			//System.out.println(_reportVO.getChoice());
			if (_reportVO.getChoice().equals("D"))
			{
				rs = reportDAO.generateGraphAgeWiseDatewise(_reportVO);
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();

				//System.out.println(_reportVO.getChartType());
				if (_reportVO.getChartType().equalsIgnoreCase("3"))
				{
					chart = genrateLineChart(rs, "AGEWISE DATEWISE CHART");
				}// end of inner if
				else
				{
					if (_reportVO.getChartType().equalsIgnoreCase("1"))
					{
						chart = genratePieChart(rs, "AGEWISE DATEWISE CHART");
					}
					else
					{
						chart = genrateBarChart(rs, "AGEWISE DATEWISE CHART");
					}
				}// end of else

				return chart;
			}
			else
			{
				rs = reportDAO.generateGraphAgeWiseToday(_reportVO);
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();
				if (_reportVO.getChartType().equalsIgnoreCase("3"))
				{
					chart = genrateLineChart(rs, "AGEWISE DATEWISE CHART");
				}// end of inner if
				else
				{
					if (_reportVO.getChartType().equalsIgnoreCase("1"))
					{
						chart = genratePieChart(rs, "AGEWISE TODAY CHART");
					}
					else
					{
						chart = genrateBarChart(rs, "AGEWISE TODAY CHART");
					}
				}
				return chart;
			}
		}// end of try
		catch (HisReportDataNotFoundException hfd)
		{
			throw new HisReportDataNotFoundException();
		}
		catch (Exception e)
		{

		}
		finally
		{
			//System.out.println("Close the transaction...");
			tx.close();

		}
		return null;

	}

	public JFreeChart groupwisegenrateBarChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;
		try
		{

			rs.last();

			int countRow = rs.getRow();
			String[] category = new String[1];

			String[] cat = new String[countRow];
			double[][] amount = new double[1][];
			amount[0] = new double[countRow];

			int nextValueIndex = 0;
			rs.beforeFirst();
			category[0] = "jhhnmj";
			while (rs.next())
			{

				cat[nextValueIndex] = rs.getString(1) + " (" + rs.getInt(2) + ")";
				amount[0][nextValueIndex] = rs.getInt(3);
				nextValueIndex++;
			}

			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(category, cat, amount);

			chart = ChartFactory.createBarChart3D("GROUP WISE CASH COLLECTION", // chart
					// title
					"", // domain axis label
					"AMOUNT COLLECTED", // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // the plot orientation
					false, // include legend
					true, true);
			Font font = new Font("Dialog", Font.BOLD, 20);
			Font font1 = new Font("Dialog", Font.ITALIC, 16);
			Paint paint = Color.PINK;
			TextTitle deptTitle = new TextTitle("Figures in Bracket indicates \"No Of Patient\"", font1, Color.RED,
					RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			TextTitle chartTitleName = new TextTitle(chartTitle, font, Color.BLACK, RectangleEdge.TOP,
					HorizontalAlignment.CENTER, VerticalAlignment.TOP, Title.DEFAULT_PADDING);
			chart.setBackgroundPaint(Color.lightGray);
			chart.addSubtitle(deptTitle);
			chart.setTitle(chartTitleName);
			// get a reference to the plot for further customisation...
			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			final CategoryPlot plot = chart.getCategoryPlot();
			plot.setNoDataMessage("NO DATA AVAILABLE!");
			//System.out.println("plot type:::::::::::::::::::::::" + plot.getPlotType());

			final CategoryItemRenderer renderer = new CustomRenderer(new Paint[]
			{ Color.magenta, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.PINK, Color.gray,
					Color.red, Color.darkGray, Color.WHITE });
			renderer.setItemLabelsVisible(true);
			final ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER,
					45.0);
			renderer.setPositiveItemLabelPosition(p);
			renderer.setItemLabelPaint(Color.RED);
			renderer.getSeriesItemLabelGenerator(1);

			plot.setRenderer(renderer);
			// change the margin at the top of the range axis...
			final ValueAxis rangeAxis = plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setLowerMargin(0.15);
			rangeAxis.setUpperMargin(0.15);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart groupwisePieChart(ResultSet rs, String chartTitel)
	{
		JFreeChart chart = null;
		DefaultPieDataset data = new DefaultPieDataset();
		String dep = "";
		String gender = "";
		try
		{
			while (rs.next())
			{
				String age = " " + rs.getString(1) + " (" + rs.getInt(2) + ")" + "               " + "AMOUNT COLLECTED="
						+ rs.getInt(3);
				//System.out.println(age);

			}
			data.setValue("gg", 100);
			data.setValue("hh", 80);

			//System.out.println(rs);

			chart = ChartFactory.createPieChart3D(chartTitel, data, true, true, true);

			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			PiePlot3D ppd = (PiePlot3D) chart.getPlot();

			ppd.setNoDataMessage("NO DATA AVAILABLE!");

			ppd.setIgnoreNullValues(true);

			ppd.setBaseSectionOutlinePaint(Color.WHITE);
			ppd.setNoDataMessagePaint(Color.RED);
			ppd.setStartAngle(50);

			ppd.setMinimumArcAngleToDraw(.5);
			//System.out.println("plot type:::::::::::::::::::::::" + ppd.getPlotType());

			Font font1 = new Font("Dialog", Font.ITALIC, 16);
			TextTitle deptTitle = new TextTitle("Figures in Bracket indicates \"No Of Patient\"", font1, Color.RED,
					RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			chart.addSubtitle(deptTitle);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart groupwiseLineChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try
		{
			while (rs.next())
			{

				dataset.addValue(rs.getInt(3), "LINE", rs.getString(1) + "(" + rs.getInt(2) + ")");
			}
			chart = ChartFactory.createLineChart3D(chartTitle, "", "AMOUNT COLLECTED", dataset, PlotOrientation.VERTICAL,
					false, true, false);
			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			Font font1 = new Font("Dialog", Font.ITALIC, 16);
			TextTitle deptTitle = new TextTitle("Figures in Bracket indicates \"No Of Patient\"", font1, Color.RED,
					RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			chart.addSubtitle(deptTitle);
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.DARK_GRAY);
			plot.setRangeGridlinePaint(Color.PINK);

			NumberAxis rangeaxis = (NumberAxis) plot.getRangeAxis();
			rangeaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeaxis.setAutoRangeIncludesZero(true);
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

			renderer.setSeriesShapesFilled(1, true);
			plot.setNoDataMessage("NO DATA AVAILABLE!!");
			renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
					new float[]
					{ 10.0f, 6.0f }, 0.0f));

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	// Groupwise Cash Collection Graph
	public JFreeChart generateGroupWiseCashCollGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		JFreeChart chart = null;

		//System.out.println("in bo choice :::::::::::::::::::::" + _reportVO.getChoice());
		JDBCTransactionContext tx = new JDBCTransactionContext();

		String title = "";

		tx.begin();
		try
		{
			ResultSet rs = null;
			ReportDAO reportDAO = new ReportDAO(tx);
			if (_reportVO.getChoice().equals("D"))
			{
				if (_reportVO.getPatListing().equalsIgnoreCase("1"))
				{
					rs = reportDAO.generateGroupWiseCashCollOldGraph(_reportVO);
					title = "GROUP WISE CASH COLLECTION FOR NEW PATIENTS";
					if (!rs.next())
					{

						throw new HisReportDataNotFoundException();
					}
					rs.beforeFirst();
				}
				else
				{
					rs = reportDAO.generateGroupWiseCashCollNewGraph(_reportVO);
					title = "GROUP WISE CASH COLLECTION FOR OLD PATIENTS";
					if (!rs.next())
					{

						throw new HisReportDataNotFoundException();
					}
					rs.beforeFirst();
				}
			}
			else
			{

				if (_reportVO.getPatListing().equals(Config.NEW))
				{
					rs = reportDAO.generateGroupWiseCashCollTodayNewGraph(_reportVO);
					title = "GROUP WISE CASH COLLECTION FOR TODAY NEW PATIENTS";
					if (!rs.next())
					{

						throw new HisReportDataNotFoundException();
					}
					rs.beforeFirst();
				}
				else
				{
					rs = reportDAO.generateGroupWiseCashCollTodayOldGraph(_reportVO);
					title = "GROUP WISE CASH COLLECTION FOR TODAY OLD PATIENTS";
					if (!rs.next())
					{

						throw new HisReportDataNotFoundException();
					}
					rs.beforeFirst();
				}
			}
			if (_reportVO.getChartType().equals("2"))
			{
				chart = groupwisegenrateBarChart(rs, title);
			}
			else
			{
				chart = groupwiseLineChart(rs, title);
			}

			return chart;

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisReportDataNotFoundException hfd)
		{
			throw new HisReportDataNotFoundException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();

		}
		finally
		{
			//System.out.println("Close the transaction...");
			tx.close();
		}

		return null;

	}

	// ////////DEPARTMENT WISE TOTAL PATIENT
	// STATISTICS//////////////////////////////////////////////////////////////////////////
	public JFreeChart genrateDEPPieChart(ResultSet rs, String chartTitel)
	{
		JFreeChart chart = null;
		DefaultPieDataset data = new DefaultPieDataset();

		try
		{

			while (rs.next())
			{

				data.setValue("MALE", rs.getBigDecimal(2));
				data.setValue("FEMALE", rs.getBigDecimal(3));
				data.setValue("MALECHILD", rs.getBigDecimal(4));
				data.setValue("FEMALECHILD", rs.getBigDecimal(5));
			}

			//System.out.println(rs);

			chart = ChartFactory.createPieChart3D(chartTitel, data, true, true, true);

			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			PiePlot3D ppd = (PiePlot3D) chart.getPlot();
			ppd.setNoDataMessage("NO DATA AVAILABLE!");

			ppd.setMinimumArcAngleToDraw(5);

			//System.out.println("plot type:::::::::::::::::::::::" + ppd.getPlotType());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart generateDepartmentWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		JFreeChart chart = null;

		//System.out.println("in bo choice :::::::::::::::::::::" + _reportVO.getChoice());
		JDBCTransactionContext tx = new JDBCTransactionContext();

		String title = "DEPARTMENT WISE TOTAL PATIENT STATISTICS";

		tx.begin();
		try
		{
			ResultSet rs = null;
			ReportDAO reportDAO = new ReportDAO(tx);
			if (_reportVO.getChoice().equals(Config.CHOICE_DATE_WISE))
			{
				rs = reportDAO.generateDepartmentWiseTotalPatDatewiseGraph(_reportVO);
				if (!rs.next())
				{
					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();
			}
			else
			{
				rs = reportDAO.generateDepartmentWiseTotalPatTodayGraph(_reportVO);
				if (!rs.next())
				{
					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();

			}

			if (_reportVO.getChartType().equals(Config.PIE))
			{
				chart = genrateDEPPieChart(rs, title);
			}

			if (_reportVO.getChartType().equals(Config.BAR))
			{
				chart = genrateDEPBarChart(rs, title);
			}
			if (_reportVO.getChartType().equals(Config.LINE))
			{
				chart = genrateDEPLineChart(rs, title);
			}

			return chart;

		}

		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisReportDataNotFoundException hdf)
		{
			throw new HisReportDataNotFoundException();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();

		}
		finally
		{
			//System.out.println("Close the transaction...");
			tx.close();
		}

		return null;

	}

	public JFreeChart genrateDEPBarChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;
		try
		{

			String dep[] = new String[1];
			double noOfPatients[][] = new double[1][4];
			String cat[] =
			{ "MALE", "FEMALE", "MALECHILD", "FEMALECHILD" };

			while (rs.next())
			{

				dep[0] = rs.getString(1);
				noOfPatients[0][0] = rs.getInt(2);
				noOfPatients[0][1] = rs.getInt(3);
				noOfPatients[0][2] = rs.getInt(4);
				noOfPatients[0][3] = rs.getInt(5);

			}

			//System.out.println("");
			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(dep, cat, noOfPatients);

			chart = ChartFactory.createBarChart3D("", // chart title
					"Category", // domain axis label
					"NO. OF PATIENTS", // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // the plot orientation
					true, // include legend
					true, true);
			Font font = new Font("Dialog", Font.BOLD, 20);
			Paint paint = Color.PINK;

			TextTitle chartTitleName = new TextTitle(chartTitle, font, Color.BLACK, RectangleEdge.TOP,
					HorizontalAlignment.CENTER, VerticalAlignment.TOP, Title.DEFAULT_PADDING);
			chart.setBackgroundPaint(Color.lightGray);

			chart.setTitle(chartTitleName);

			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			final CategoryPlot plot = chart.getCategoryPlot();
			plot.setNoDataMessage("NO DATA AVAILABLE!");
			//System.out.println("plot type:::::::::::::::::::::::" + plot.getPlotType());

			final CategoryItemRenderer renderer = new CustomRenderer(new Paint[]
			{ Color.magenta, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.PINK, Color.gray,
					Color.red, Color.darkGray, Color.WHITE });
			renderer.setItemLabelsVisible(true);
			final ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER,
					45.0);
			renderer.setPositiveItemLabelPosition(p);
			renderer.setItemLabelPaint(Color.RED);
			plot.setRenderer(renderer);
			// change the margin at the top of the range axis...
			final ValueAxis rangeAxis = plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setLowerMargin(0.15);
			rangeAxis.setUpperMargin(0.15);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart genrateDEPLineChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;
		String deptName = "";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try
		{
			while (rs.next())
			{
				deptName = "CATEGORY";
				dataset.addValue(rs.getInt(2), rs.getString(1), "MALE");
				dataset.addValue(rs.getInt(3), rs.getString(1), "FEMALE");
				dataset.addValue(rs.getInt(4), rs.getString(1), "MALECHILD");
				dataset.addValue(rs.getInt(5), rs.getString(1), "FEMALECHILD");
			}
			chart = ChartFactory.createLineChart3D(chartTitle, deptName, "NO OF PATIENTS", dataset,
					PlotOrientation.VERTICAL, true, true, false);
			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.DARK_GRAY);
			plot.setRangeGridlinePaint(Color.PINK);

			NumberAxis rangeaxis = (NumberAxis) plot.getRangeAxis();
			rangeaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeaxis.setAutoRangeIncludesZero(true);
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

			renderer.setSeriesShapesFilled(1, true);
			plot.setNoDataMessage("NO DATA AVAILABLE!!");
			renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
					new float[]
					{ 10.0f, 6.0f }, 0.0f));

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	// //////////////////////////////////generateRoomWiseTotalPatGraph/////////////////////////////////////

	public JFreeChart generateRoomWiseTotalPatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		JFreeChart chart = null;

		//System.out.println("in bo choice :::::::::::::::::::::" + _reportVO.getChoice());
		JDBCTransactionContext tx = new JDBCTransactionContext();

		String title = "ROOM WISE TOTAL PATIENT STATISTICS";

		tx.begin();
		try
		{
			ResultSet rs = null;
			ReportDAO reportDAO = new ReportDAO(tx);
			if (_reportVO.getChoice().equals(Config.CHOICE_DATE_WISE))
			{
				rs = reportDAO.generateRoomWiseTotalPatDatewiseGraph(_reportVO);
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();
			}
			else
			{
				rs = reportDAO.generateRoomWiseTotalPatTodayGraph(_reportVO);
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();
			}

			if (_reportVO.getChartType().equals(Config.PIE))
			{
				chart = genrateDEPPieChart(rs, title);
			}

			if (_reportVO.getChartType().equals(Config.BAR))
			{
				chart = genrateDEPBarChart(rs, title);
			}
			if (_reportVO.getChartType().equals(Config.LINE))
			{
				chart = genrateDEPLineChart(rs, title);
			}

			return chart;

		}

		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisReportDataNotFoundException hdf)
		{
			throw new HisReportDataNotFoundException();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();

		}
		finally
		{
			//System.out.println("Close the transaction...");
			tx.close();
		}

		return null;

	}

	// ////////////////////////////////////////////////TotalPatStatGraph/////////////////////////////////////////////////////////////////////////////

	public JFreeChart generateTotalPatStatGraph(ReportVO _reportVO) throws HisReportDataNotFoundException
	{
		JFreeChart chart = null;

		//System.out.println("in bo choice :::::::::::::::::::::" + _reportVO.getChoice());
		JDBCTransactionContext tx = new JDBCTransactionContext();

		String title = "TOTAL PATIENT STATISTICS";

		tx.begin();
		try
		{
			ResultSet rs = null;
			ReportDAO reportDAO = new ReportDAO(tx);
			if (_reportVO.getChoice().equals(Config.CHOICE_DATE_WISE))
			{
				if (_reportVO.getPatListing().equals(Config.OLD))
				{
					rs = reportDAO.generateTotalPatStatDatewiseOldGraph(_reportVO);
				}
				else
				{
					rs = reportDAO.generateTotalPatStatDatewiseNewGraph(_reportVO);
				}
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();
			}
			else
			{

				if (_reportVO.getPatListing().equals(Config.NEW))
				{
					rs = reportDAO.generateTotalPatStatTodayNewGraph(_reportVO);
				}
				else
				{
					rs = reportDAO.generateTotalPatStatTodayOldGraph(_reportVO);
				}
				if (!rs.next())
				{

					throw new HisReportDataNotFoundException();
				}
				rs.beforeFirst();
			}

			if (_reportVO.getChartType().equals(Config.PIE))
			{
				chart = genrateDEPPieChart(rs, title);
			}

			if (_reportVO.getChartType().equals(Config.BAR))
			{
				chart = genrateDEPBarChart(rs, title);
			}
			if (_reportVO.getChartType().equals(Config.LINE))
			{
				chart = genrateDEPLineChart(rs, title);
			}

			return chart;

		}

		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisReportDataNotFoundException hdf)
		{
			throw new HisReportDataNotFoundException();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();

		}
		finally
		{
			//System.out.println("Close the transaction...");
			tx.close();
		}

		return null;

	}

	public JFreeChart totalPatStatgenrateBarChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;
		try
		{

			rs.last();

			int countRow = rs.getRow();
			String[] category = new String[countRow];

			double[][] noOfPatients = new double[countRow][4];

			String cat[] =
			{ "MALE", "FEMALE", "MALECHILD", "FEMALECHILD" };

			int nextValueIndex = 0;
			rs.beforeFirst();
			category[0] = "jhhnmj";
			while (rs.next())
			{

				// deptName=rs.getString(2);
				// //System.out.println(rs.getString(1));
				category[nextValueIndex] = rs.getString(1);

				noOfPatients[nextValueIndex][0] = rs.getInt(2);
				noOfPatients[nextValueIndex][1] = rs.getInt(3);
				noOfPatients[nextValueIndex][2] = rs.getInt(4);
				noOfPatients[nextValueIndex][3] = rs.getInt(5);

				nextValueIndex++;
			}
			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(cat, category, noOfPatients);

			chart = ChartFactory.createBarChart3D(chartTitle, // chart title
					"", // domain axis label
					"AMOUNT COLLECTED", // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // the plot orientation
					true, // include legend
					true, true);
			Font font = new Font("Dialog", Font.BOLD, 20);
			Font font1 = new Font("Dialog", Font.ITALIC, 16);

			TextTitle deptTitle = new TextTitle("Figures in Bracket indicates \"No Of Patient\"", font1, Color.RED,
					RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			TextTitle chartTitleName = new TextTitle(chartTitle, font, Color.BLUE, RectangleEdge.TOP,
					HorizontalAlignment.CENTER, VerticalAlignment.TOP, Title.DEFAULT_PADDING);
			chart.setBackgroundPaint(Color.lightGray);
			chart.addSubtitle(deptTitle);
			chart.setTitle(chartTitleName);
			// get a reference to the plot for further customisation...
			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			final CategoryPlot plot = chart.getCategoryPlot();
			plot.setNoDataMessage("NO DATA AVAILABLE!");
			//System.out.println("plot type:::::::::::::::::::::::" + plot.getPlotType());

			final CategoryItemRenderer renderer = new CustomRenderer(new Paint[]
			{ Color.magenta, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.PINK, Color.gray,
					Color.red, Color.darkGray, Color.WHITE });
			renderer.setItemLabelsVisible(true);
			final ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER,
					45.0);
			renderer.setPositiveItemLabelPosition(p);
			renderer.setItemLabelPaint(Color.RED);
			renderer.getSeriesItemLabelGenerator(1);

			plot.setRenderer(renderer);
			// change the margin at the top of the range axis...
			final ValueAxis rangeAxis = plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setLowerMargin(0.15);
			rangeAxis.setUpperMargin(0.15);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart totalPatStatPieChart(ResultSet rs, String chartTitel)
	{
		JFreeChart chart = null;
		DefaultPieDataset data = new DefaultPieDataset();

		try
		{
			while (rs.next())
			{
				String age = " " + rs.getString(1) + " (" + rs.getInt(2) + ")" + "               " + "AMOUNT COLLECTED="
						+ rs.getInt(3);
				//System.out.println(age);

			}
			data.setValue("gg", 100);
			data.setValue("hh", 80);

			//System.out.println(rs);

			chart = ChartFactory.createPieChart3D(chartTitel, data, true, true, true);

			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			PiePlot3D ppd = (PiePlot3D) chart.getPlot();

			ppd.setNoDataMessage("NO DATA AVAILABLE!");

			ppd.setIgnoreNullValues(true);

			ppd.setBaseSectionOutlinePaint(Color.WHITE);
			ppd.setNoDataMessagePaint(Color.RED);
			ppd.setStartAngle(50);

			ppd.setMinimumArcAngleToDraw(.5);
			//System.out.println("plot type:::::::::::::::::::::::" + ppd.getPlotType());

			Font font1 = new Font("Dialog", Font.ITALIC, 16);
			TextTitle deptTitle = new TextTitle("Figures in Bracket indicates \"No Of Patient\"", font1, Color.RED,
					RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			chart.addSubtitle(deptTitle);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

	public JFreeChart totalPatStatLineChart(ResultSet rs, String chartTitle)
	{
		JFreeChart chart = null;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try
		{
			while (rs.next())
			{

				dataset.addValue(rs.getInt(3), "LINE", rs.getString(1) + "(" + rs.getInt(2) + ")");
			}
			chart = ChartFactory.createLineChart3D(chartTitle, "", "AMOUNT COLLECTED", dataset, PlotOrientation.VERTICAL,
					false, true, false);
			//System.out.println("plot in line ------------------------------------" + chart.getPlot());
			Font font1 = new Font("Dialog", Font.ITALIC, 16);
			TextTitle deptTitle = new TextTitle("Figures in Bracket indicates \"No Of Patient\"", font1, Color.RED,
					RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, Title.DEFAULT_PADDING);
			chart.addSubtitle(deptTitle);
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.DARK_GRAY);
			plot.setRangeGridlinePaint(Color.PINK);

			NumberAxis rangeaxis = (NumberAxis) plot.getRangeAxis();
			rangeaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeaxis.setAutoRangeIncludesZero(true);
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

			renderer.setSeriesShapesFilled(1, true);
			plot.setNoDataMessage("NO DATA AVAILABLE!!");
			renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
					new float[]
					{ 10.0f, 6.0f }, 0.0f));

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		return chart;
	}

}// end of report BO

