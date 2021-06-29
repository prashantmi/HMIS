package opd.icdsearchengine.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.icdsearchengine.util.ICDSearchEngineUTL;
import opd.icdsearchengine.vo.ICDSearchEngineVO;

public class ICDSearchEngineAJAXServlet extends HttpServlet implements Servlet
{
	public static String MODE_SUB_DISEASE_LIST = "SUBDIS";
	public static String MODE_AUTO_INDEX_TERM_LIST = "AUTOINDEXTERM";
	public static String MODE_SUB_TREE = "SUBTREE";
	public static String MODE_SUB_SUB_TREE = "SUBSUBTREE";
	public static String MODE_ALL_SUB_GROUP = "ALLSUBGROUP";
	public static String MODE_ALL_DISEASE = "ALLDISEASE";
	
	protected void doGet(HttpServletRequest request_p, HttpServletResponse response_p) throws ServletException, IOException
	{
		String mode = "";
		StringBuffer strBuff = new StringBuffer();
		try
		{
			mode = (String) request_p.getParameter("mode");
			
			// Sub Disease List on click of Main Disease Code
			if(mode.equals(MODE_SUB_DISEASE_LIST))
			{
				ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
				engineVO.setStrRecordType((String) request_p.getParameter("strRecordType"));
				engineVO.setStrIcdCodeMain((String) request_p.getParameter("strIcdCodeMain"));
				
				strBuff = ICDSearchEngineUTL.getICDSearchingResulforVol1Sub(engineVO, request_p);
			}		
			if(mode.equals(MODE_AUTO_INDEX_TERM_LIST))	// Index Term List for Volume 3 Search
			{
				strBuff = ICDSearchEngineUTL.getICDSearchingIndexAutoCompleteVol3(request_p);
			}		
			if(mode.equals(MODE_SUB_TREE))	// Modifier Index Term List for Volume 3 Tree Level 1
			{
				ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
				engineVO.setStrIndexCode((String) request_p.getParameter("strIndexCode"));

				strBuff = ICDSearchEngineUTL.getIndexTermModForVol3Tree(engineVO, request_p);
			}		
			if(mode.equals(MODE_SUB_SUB_TREE))	// Modifier Index Term List for Volume 3 Tree Next Level
			{
				ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
				engineVO.setStrIndexCode((String) request_p.getParameter("strIndexCode"));
				engineVO.setStrIndexModifierID((String) request_p.getParameter("strIndexModifierID"));

				strBuff = ICDSearchEngineUTL.getModifierTermVol3Tree(engineVO, request_p);
			}		
			if(mode.equals(MODE_ALL_SUB_GROUP))	// All Sub Group of Given Group for Reports
			{
				ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
				engineVO.setStrIcdGroupCode((String) request_p.getParameter("strIcdGroupCode"));

				strBuff = ICDSearchEngineUTL.getAllSubGroupForReports(engineVO, request_p);
			}		
			if(mode.equals(MODE_ALL_DISEASE))	// All Diseases of Given Sub Group for Reports
			{
				ICDSearchEngineVO engineVO = new ICDSearchEngineVO();
				engineVO.setStrIcdSubGroupCode((String) request_p.getParameter("strIcdSubGroupCode"));

				strBuff = ICDSearchEngineUTL.getAllDiseasesForReports(engineVO, request_p);
			}		
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			PrintWriter printWriter = response_p.getWriter();
			printWriter.write(strBuff.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request,response);
	}
}
