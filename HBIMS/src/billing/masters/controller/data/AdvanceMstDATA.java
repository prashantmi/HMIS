package billing.masters.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericBO;
import hisglobal.masterutil.GenericData;
import hisglobal.masterutil.GenericHLP;
import hisglobal.masterutil.GenericVO;
import hisglobal.masterutil.MasterInterface;
import hisglobal.utility.HisUtil;

import java.io.PrintWriter;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import billing.masters.bo.BOAdvanceMst;

public class AdvanceMstDATA extends GenericData {

	static String combo[] = null;
	public static void DELETELIST(HttpServletRequest request,
			HttpServletResponse response, MasterInterface masterObj) {
		String divid = "";
		PrintWriter out = null;
		GenericVO vo = null;
		GenericBO bo = null;
		GenericHLP hlp = null;
		String[] strData = null;
		HisUtil hisutil = new HisUtil("Master Template", "GenericData");

		try {
			out = response.getWriter();
			HttpSession htSession = request.getSession();
			htSession.setAttribute("MSTCOMBO", request
					.getParameterValues("combo"));

			//htSession.setAttribute("USERVALUE", userVal);
			masterObj.setHttpSession(htSession);
			// Setting Request
			Class classObj=masterObj.getClass();
			try
			{
				Field requestField=null;
				for(int i=0;i<classObj.getFields().length;i++)
				{
					
					if(classObj.getFields()[i].getName().equals("request"))
					{	
						requestField=classObj.getFields()[i];
					}
				}
				
				if(requestField!=null)
				{
					
								
					for(int i=0;i<classObj.getMethods().length;i++)
					{
						if(classObj.getMethods()[i].getName().equals("setHttpRequest"))
						{
							classObj.getMethods()[i].invoke(masterObj, request);
						}
				}
					
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			combo = request.getParameterValues("combo");
			vo = new GenericVO();
			vo.setCombo(combo);
			vo.setDivisionId(request.getParameter("divisionId"));
			vo.setChk(request.getParameter("chk"));
			if (request.getParameter("prevNext") == null
					|| request.getParameter("prevNext") == "")
				vo.setPrevNext("1");
			else
				vo.setPrevNext(request.getParameter("prevNext"));
			vo.setRowNum(request.getParameter("rowNum"));
			vo.setMinrow(request.getParameter("minrow"));
			vo.setBlockNo(request.getParameter("blockNo"));
			vo.setMax_rownum(request.getParameter("max_rownum"));
			vo.setSearch(request.getParameter("search"));
			vo.setSearchColumn(request.getParameter("searchColumn"));

			divid = request.getParameter("divisionId");

			bo = new GenericBO();
			BOAdvanceMst boMst = new BOAdvanceMst();
			String hosCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strChk = request.getParameter("chk");
			boMst.deleteData(hosCode,vo,strChk);
  
			bo.LISTPAGE(vo, masterObj.getRecord_per_page(), masterObj
					.getPage_per_block(), "DELETE", masterObj.getMainQuery(vo
					.getCombo()), masterObj.getOrderBy());
			if (masterObj.getComboQuery() != null) {
				strData = new String[masterObj.getComboHeader().length / 2];
				for (int nI = 0; nI < masterObj.getComboHeader().length / 2; nI++) {
					if (masterObj.getComboHeader()[2 * nI].equals("0")) {
						if (vo.getCombo() != null)
							strData[nI] = hisutil.getOptionValue(bo
									.getMainQuery(
											masterObj.getComboQuery()[nI], vo),
									vo.getCombo()[nI], "Select Value");
						else
							strData[nI] = hisutil.getOptionValue(bo
									.getMainQuery(
											masterObj.getComboQuery()[nI], vo),
									"0", "Select Value");
					}
				}
			}

			hlp = new GenericHLP();
			String p = hlp.generateData(vo, masterObj.getColumnHeader(),
					masterObj.getPage_per_block(), masterObj.getViewHeader(),
					masterObj.getMasterName(), masterObj.getSearchField(),
					masterObj.getRecord_per_page(), masterObj.getComboHeader(),
					masterObj.getComboQuery(), strData, masterObj.getOrderBy(),
					MasterInterface.nextprevcolor);
			
			out.print(p + "####" + hlp.deleteRecords(vo) + "####" + divid);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException(masterObj.getMasterName(),
					"GenericData-->DELETE()", vo.getStrMsgString());
			out.println("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			vo = null;
			bo = null;
			hlp = null;
			
		}
	}
	
}
