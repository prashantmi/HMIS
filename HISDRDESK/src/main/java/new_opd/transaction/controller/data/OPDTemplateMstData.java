package new_opd.transaction.controller.data;

import hisglobal.config.HISConfig;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import new_opd.bo.OPDTemplateMstBO;
import new_opd.transaction.controller.fb.OPDTemplateMstFb;
import new_opd.transaction.controller.util.OPDTemplateMstUtil;
import new_opd.vo.OPDTemplateMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class OPDTemplateMstData {

	public static void getDeptDtlData(OPDTemplateMstFb formBean,
			HttpServletRequest request) {
		OPDTemplateMstVO vo=null;
		OPDTemplateMstBO bo=null;
		Map<String, String> DeptCmb=null;
		HisUtil hisutil = null;
		String cmb = "";
		try
		{
			vo=new OPDTemplateMstVO();
			bo=new OPDTemplateMstBO();
			hisutil = new HisUtil("OPD", "getDeptDtlData");
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrSeatId(SeatId);
			bo.getDeptCodeList(vo);
			bo.getTemplateCatCMB(vo);
			bo.getTemplateListingData(vo);
			
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			
				/*DeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrDeptWb().size()>0)
				{
					while (vo.getStrDeptWb().next()) {
						DeptCmb.put(vo.getStrDeptWb().getString(1), vo.getStrDeptWb().getString(2));
						
					}
				}*/
				
				
				if (vo.getStrDeptWb() != null && vo.getStrDeptWb().size() > 0) {
					cmb = hisutil.getOptionValue(vo.getStrDeptWb(), "",
							"", false);
				} else {
					cmb = "<option value='0'>Select Value</option>";
				}
				
				formBean.setStrDeptValues(cmb);
				String cmb1="";
				if (vo.getStrTemplateCatWb() != null && vo.getStrTemplateCatWb().size() > 0) {
					cmb1 = hisutil.getOptionValue(vo.getStrTemplateCatWb(), "",
							"0^Select Value", false);
				} else {
					cmb1 = "<option value='0'>Select Value</option>";
				}
				formBean.setStrTemplateCatValues(cmb1);
				formBean.setStrTemalteListingData(vo.getStrTemalteListingData());
				if(vo.getStrTemalteListingData() !=null && vo.getStrTemalteListingData().size() >0)
				request.getSession().setAttribute("TEMPLTELISTINGDATA", vo.getStrTemalteListingData());
				else
				request.getSession().setAttribute("TEMPLTELISTINGDATA", null);
					
		}catch(Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("mms",
					"ItemMstDATA->initAdd()", e.getMessage());
		}
			
	
		
	}

	public static void SaveData(OPDTemplateMstFb formBean,
			HttpServletRequest request) {
		OPDTemplateMstVO vo=null;
		OPDTemplateMstBO bo=null;
		
		try
		{
			
			vo=new OPDTemplateMstVO();
			bo=new OPDTemplateMstBO();
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrSeatId(SeatId);
			//System.out.println("formBean.getStrHtmlString()"+formBean.getStrHtmlString());
			//System.out.println("formBean.getStrJsonParaMetereIdString()"+formBean.getStrJsonParaMetereIdString().replaceAll("form-group", "col-sm-6"));
			vo.setStrHtmlString(formBean.getStrHtmlString());
			vo.setStrJsonParaMetereIdString(formBean.getStrJsonParaMetereIdString());
			vo.setStrModifyData(formBean.getStrModifyData());
			  System.out.println("formBean.getStrEssentilaJson()"+formBean.getStrEssentilaJson()); 
			  JSONParser parser = new JSONParser();
			/*
			 * org.json.simple.JSONObject json = (org.json.simple.JSONObject)
			 * parser.parse(formBean.getStrEssentilaJson().toString());
			 */
			
			  JSONObject object = new JSONObject(formBean.getStrEssentilaJson()); 
			  System.out.println();
			  JSONArray DeptCodeArray = (JSONArray) object.get("DeptCode");
			  String[] deptcode= new String[DeptCodeArray.length()]; for(int i=0 ;i< DeptCodeArray.length() ;i++){
			  System.out.println("DeptCodeArray.get(i).toString()"+DeptCodeArray.get(i).toString()); 
			  deptcode[i]=DeptCodeArray.get(i).toString(); 
			  }
			  formBean.setStrDeptCode(deptcode);
			  vo.setStrTemplateCatCode(object.getString("TemplateCat"));
				vo.setStrIsActive(object.getString("IsTemplateActive"));
				vo.setStrTemplateType(object.getString("TemplateType"));
				
				//TemplateType
			  formBean.setStrTemplateName(object.getString("TemplateName"));
			 bo.getTemplateNo(vo);
			
			
			  System.out.println("Template Name :::::"+formBean.getStrTemplateName());
			  System.out.println("Dept Length :::::"+formBean.getStrDeptCode().length);
			  vo.setStrTemplateName(formBean.getStrTemplateName());
			  vo.setStrDeptCode(formBean.getStrDeptCode());
			 
			bo.saveData(vo);
			
			OPDTemplateMstUtil.UplodaHTML(vo);
			OPDTemplateMstUtil.UplodaModifyHTML(vo);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		
	}

	public static void GETPARAMETER(OPDTemplateMstFb formBean, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		OPDTemplateMstVO vo = null;
		OPDTemplateMstBO bo = null;
		WebRowSet wb = null;
		JSONObject js = null;
		org.json.simple.JSONArray arr = new org.json.simple.JSONArray();

		try {

			vo = new OPDTemplateMstVO();
			bo = new OPDTemplateMstBO();

			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId = (String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode((String) request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrReplaceTerm(request.getParameter("term"));

			bo.GETPARAMETER(vo);

			wb = vo.getStrHiddenParameter();

			while (wb.next()) {
				js = new JSONObject();
				js.put("value", wb.getString(1));
				js.put("id", wb.getString(2));
				System.out.println("----------------");

				System.out.println(js.toString());

				arr.add(js);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	
	public static void GETDRUG(OPDTemplateMstFb formBean, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		OPDTemplateMstVO vo = null;
		OPDTemplateMstBO bo = null;
		WebRowSet wb = null;
		JSONObject js = null;
		org.json.simple.JSONArray arr = new org.json.simple.JSONArray();

		try {

			vo = new OPDTemplateMstVO();
			bo = new OPDTemplateMstBO();

			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId = (String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode((String) request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrReplaceTerm(request.getParameter("term"));

			bo.GETDRUG(vo);

			wb = vo.getStrHiddenParameter();

			while (wb.next()) {
				js = new JSONObject();
				js.put("value", wb.getString(1));
				js.put("id", wb.getString(2));
				System.out.println("----------------");

				System.out.println(js.toString());

				arr.add(js);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}
	
	public static void GETTEST(OPDTemplateMstFb formBean, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		OPDTemplateMstVO vo = null;
		OPDTemplateMstBO bo = null;
		WebRowSet wb = null;
		JSONObject js = null;
		org.json.simple.JSONArray arr = new org.json.simple.JSONArray();

		try {

			vo = new OPDTemplateMstVO();
			bo = new OPDTemplateMstBO();

			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId = (String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode((String) request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrReplaceTerm(request.getParameter("term"));

			bo.GETTEST(vo);

			wb = vo.getStrHiddenParameter();

			while (wb.next()) {
				js = new JSONObject();
				js.put("value", wb.getString(1));
				js.put("id", wb.getString(2));
				System.out.println("----------------");

				System.out.println(js.toString());

				arr.add(js);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}
	
	
	public static void GETICD(OPDTemplateMstFb formBean, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		OPDTemplateMstVO vo = null;
		OPDTemplateMstBO bo = null;
		WebRowSet wb = null;
		JSONObject js = null;
		org.json.simple.JSONArray arr = new org.json.simple.JSONArray();

		try {

			vo = new OPDTemplateMstVO();
			bo = new OPDTemplateMstBO();

			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId = (String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode((String) request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrReplaceTerm(request.getParameter("term"));

			bo.GETICD(vo);

			wb = vo.getStrHiddenParameter();

			while (wb.next()) {
				js = new JSONObject();
				js.put("value", wb.getString(1));
				js.put("id", wb.getString(2));
				System.out.println("----------------");

				System.out.println(js.toString());

				arr.add(js);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}
	
	public static void GETSITE(OPDTemplateMstFb formBean, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		OPDTemplateMstVO vo = null;
		OPDTemplateMstBO bo = null;
		WebRowSet wb = null;
		JSONObject js = null;
		org.json.simple.JSONArray arr = new org.json.simple.JSONArray();

		try {

			vo = new OPDTemplateMstVO();
			bo = new OPDTemplateMstBO();

			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId = (String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode((String) request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrReplaceTerm(request.getParameter("term"));

			bo.GETSITE(vo);

			wb = vo.getStrHiddenParameter();

			while (wb.next()) {
				js = new JSONObject();
				js.put("value", wb.getString(1));
				js.put("id", wb.getString(2));
				System.out.println("----------------");

				System.out.println(js.toString());

				arr.add(js);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(arr.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public static void GETAUTOCOMPLETEDATA(OPDTemplateMstFb formBean, HttpServletRequest request,
			HttpServletResponse response) {
		OPDTemplateMstVO vo = null;
		OPDTemplateMstBO bo = null;
		WebRowSet ws = null;
		final StringBuffer sb =new StringBuffer(1000);

		try {

			vo = new OPDTemplateMstVO();
			bo = new OPDTemplateMstBO();

			String SeatId = (String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode((String) request.getSession().getAttribute("HOSPITAL_CODE"));

			String HsopitalCode = (String) request.getSession().getAttribute("HOSPITAL_CODE");

			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrSeatId(SeatId);
			bo.getAutoCompletData(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception();
			}
			sb.append("<option value='0'>Select Value</option>");
			if (vo.getStrAutoCompleteWs() != null && vo.getStrAutoCompleteWs().size() > 0) {
				ws=vo.getStrAutoCompleteWs();
				
				while(ws.next())		
					sb.append("<option title='"+ws.getString(1)+"' value='"+ws.getString(2)+"' data-fetch='"+ws.getString(10)+"'>"+ws.getString(1)+"</option>");
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public static void DeteleRecord(OPDTemplateMstFb formBean, HttpServletRequest request) {
		OPDTemplateMstVO vo = null;
		OPDTemplateMstBO bo = null;
		Map<String, String> DeptCmb = null;
		HisUtil hisutil = null;
		String cmb = "";
		try {
			vo = new OPDTemplateMstVO();
			bo = new OPDTemplateMstBO();
			hisutil = new HisUtil("OPD", "getDeptDtlData");
			String HsopitalCode = (String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId = (String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrSeatId(SeatId);
			// bo.getDeptCodeList(vo);
			vo.setStrTemplateNo(formBean.getStrTemplateDeleteParm().split("#")[0]);
			vo.setStrDeptUnitCode(formBean.getStrTemplateDeleteParm().split("#")[1]);
			bo.getDeleteRecord(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception();
			}

			/*
			 * DeptCmb=new LinkedHashMap<String, String>(); if(vo.getStrDeptWb().size()>0) {
			 * while (vo.getStrDeptWb().next()) {
			 * DeptCmb.put(vo.getStrDeptWb().getString(1), vo.getStrDeptWb().getString(2));
			 * 
			 * } }
			 */

			if (vo.getStrDeptWb() != null && vo.getStrDeptWb().size() > 0) {
				cmb = hisutil.getOptionValue(vo.getStrDeptWb(), "", "0^Select Value", false);
			} else {
				cmb = "<option value='0'>Select Value</option>";
			}

			formBean.setStrDeptValues(cmb);
			String cmb1 = "";
			if (vo.getStrTemplateCatWb() != null && vo.getStrTemplateCatWb().size() > 0) {
				cmb1 = hisutil.getOptionValue(vo.getStrTemplateCatWb(), "", "0^Select Value", false);
			} else {
				cmb1 = "<option value='0'>Select Value</option>";
			}
			formBean.setStrTemplateCatValues(cmb1);
			formBean.setStrTemalteListingData(vo.getStrTemalteListingData());
			if (vo.getStrTemalteListingData() != null && vo.getStrTemalteListingData().size() > 0)
				request.getSession().setAttribute("TEMPLTELISTINGDATA", vo.getStrTemalteListingData());
			else
				request.getSession().setAttribute("TEMPLTELISTINGDATA", null);

		} catch (Exception e) {
			e.printStackTrace();
			HisException eObj = new HisException("mms", "ItemMstDATA->initAdd()", e.getMessage());
		}
	}
	public static void getModifyDTL(OPDTemplateMstFb formBean,
			HttpServletRequest request) {
		OPDTemplateMstVO vo=null;
		OPDTemplateMstBO bo=null;
		Map<String, String> DeptCmb=null;
		HisUtil hisutil = null;
		String cmb = "";
		try
		{
			vo=new OPDTemplateMstVO();
			bo=new OPDTemplateMstBO();
			hisutil = new HisUtil("OPD", "getDeptDtlData");
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrTemplateNo(formBean.getChk()[0].split("#")[0]);
			vo.setStrDeptUnitCode(formBean.getChk()[0].split("#")[1]);
			vo.setStrSeatId(SeatId);
			bo.getDeptCodeList(vo);
			bo.getTemplateCatCMB(vo);
			bo.getTemplateListingData(vo);
			formBean.setStrChk(formBean.getChk()[0]);
			formBean.setStrTemplateName(formBean.getChk()[0].split("#")[6]);
			if (vo.getStrDeptWb() != null && vo.getStrDeptWb().size() > 0) {
				cmb = hisutil.getOptionValue(vo.getStrDeptWb(), formBean.getChk()[0].split("#")[1],
						"", false);
			} else {
				cmb = "<option value='0'>Select Value</option>";
			}
			
			formBean.setStrDeptValues(cmb);
			String cmb1="";
			if (vo.getStrTemplateCatWb() != null && vo.getStrTemplateCatWb().size() > 0) {
				cmb1 = hisutil.getOptionValue(vo.getStrTemplateCatWb(), formBean.getChk()[0].split("#")[3],
						"0^Select Value", false);
			} else {
				cmb1 = "<option value='0'>Select Value</option>";
			}
			formBean.setStrTemplateCatValues(cmb1);
			String html=OPDTemplateMstUtil.getModifyHtmlFileFromFTP(formBean.getChk()[0].split("#")[0], HsopitalCode) ;
			System.out.println("::::::::\n"+html);
			
			formBean.setStrTemplateModifyHtml(html);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			
					
		}catch(Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("mms",
					"ItemMstDATA->initAdd()", e.getMessage());
		}
			
	
		
	}
	
	public static void ModifySave(OPDTemplateMstFb formBean,
			HttpServletRequest request) {
		OPDTemplateMstVO vo=null;
		OPDTemplateMstBO bo=null;
		
		try
		{
			
			vo=new OPDTemplateMstVO();
			bo=new OPDTemplateMstBO();
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrSeatId(SeatId);
			vo.setStrHtmlString(formBean.getStrHtmlString());
			vo.setStrJsonParaMetereIdString(formBean.getStrJsonParaMetereIdString());
			vo.setStrModifyData(formBean.getStrModifyData());
			  System.out.println("formBean.getStrEssentilaJson()"+formBean.getStrEssentilaJson()); 
			  JSONParser parser = new JSONParser();
			/*
			 * org.json.simple.JSONObject json = (org.json.simple.JSONObject)
			 * parser.parse(formBean.getStrEssentilaJson().toString());
			 */
			
			  JSONObject object = new JSONObject(formBean.getStrEssentilaJson()); 
			  System.out.println();
			  JSONArray DeptCodeArray = (JSONArray) object.get("DeptCode");
			  String[] deptcode= new String[DeptCodeArray.length()]; for(int i=0 ;i< DeptCodeArray.length() ;i++){
			  System.out.println("DeptCodeArray.get(i).toString()"+DeptCodeArray.get(i).toString()); 
			  deptcode[i]=DeptCodeArray.get(i).toString(); 
			  }
			  formBean.setStrDeptCode(deptcode);
			  vo.setStrTemplateCatCode(object.getString("TemplateCat"));
				vo.setStrIsActive(object.getString("IsTemplateActive"));
				vo.setStrTemplateType(object.getString("TemplateType"));
				
				//TemplateType
			  formBean.setStrTemplateName(object.getString("TemplateName"));
			 //bo.getTemplateNo(vo);
			 vo.setStrTemplateNo(formBean.getStrChk().split("#")[0]);
			
			
			  System.out.println("Template Name :::::"+formBean.getStrTemplateName());
			  System.out.println("Dept Length :::::"+formBean.getStrDeptCode().length);
			  vo.setStrTemplateName(formBean.getStrTemplateName());
			  vo.setStrDeptCode(formBean.getStrDeptCode());
			 
			bo.Modifysave(vo);
			
			OPDTemplateMstUtil.UplodaHTML(vo);
			OPDTemplateMstUtil.UplodaModifyHTML(vo);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		
		
	}

}
