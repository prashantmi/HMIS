/**
 * 
 */
package loinc.masters.controller.util;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
import loinc.vo.LoincVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import loinc.bo.LoincMasterBO;
import loinc.masters.controller.data.LoincMstDATA;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.bo.RegMasterBO;
import registration.masters.controller.data.LocationMstDATA;
import vo.registration.LocationVO;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;
/**
 * @author Sheeldarshi
 * Creation Date:- 26thSep'14
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Loinc
 *
 */
public class LoincMstUTIL implements MasterInterface 
{
	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	public static HttpServletRequest request = null;

	public String getButtons() 
	{
		
		StringBuilder strButtons = new StringBuilder();
	
		strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' edit(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='modify'>Modify</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='report'>Report</span></a>");
		strButtons.append("<a href='#' class='button' onclick='  cancelList(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='cancel'>Cancel</span></a>");
	
		return strButtons.toString();
	}

	public String[] getColsWidth()
	{
		return null;
	}

	public String[] getColumnHeader()
	{
		String[] columnHdr = { "Test Name","Test Para Name","Test Sample","Unit of Measurement","Loinc Code"};
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery() 
	{
		String[] strComboQuery = new String[1];
		strComboQuery[0] = "1^Active";//#2^Inactive";
		return strComboQuery;
	}

	public String[] getDeleteQuery() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getJsFiles() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getMainQuery(String[] cmb) 
	{
		
		StringBuffer brMainQuery = new StringBuffer();
		
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		
		List<String> list = new ArrayList<String>();
		//list.add(Config.IS_VALID_ACTIVE);
		list.add(uservo.getHospitalCode());

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"loincMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}
		
		return brMainQuery.toString();
	}

	public String getMasterName()
	{		
		String strMasterName = "Loinc Master";
		return strMasterName;
	}

	public String[] getOrderBy()
	{	
		
		String strOrderBy[] = { "1", "UPPER(gstr_test_name)","2","UPPER(hgstr_parameter)"};
		return strOrderBy;
	}

	public int getPage_per_block() 
	{
		return 10;
	}

	public int getRecord_per_page()
	{
		return 10;
	}

	public String[] getSearchField()
	{		
		String strSearchField[] = { "1", " UPPER(gstr_test_name)","2","UPPER(hgstr_parameter)"};
		return strSearchField;
	}

	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Test Name :");
		viewHdr.add("D");
		viewHdr.add("Test Para Name :");
		viewHdr.add("D");
		viewHdr.add("Sample :");
		viewHdr.add("D");
		viewHdr.add("Unit Of Measurement :");
		viewHdr.add("D");
		viewHdr.add("Loinc Code :");
		viewHdr.add("D");
		return viewHdr;
	}

	public String getViewQuery()
	{
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"loincMst.view");
	return strViewQuery;
	}

	public String isGlobal()
	{
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http.HttpSession)
	 */
	public void setHttpSession(HttpSession session) 
	{
		httpSession = session;

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#setHttpSessionMap(java.util.Map)
	 */
	public void setHttpSessionMap(Map session)
	{
		// TODO Auto-generated method stub

	}
	
	public void setHttpRequest(HttpServletRequest request) 
	{
		this.request = request;
	}
	
	public static  LoincVO getModifyComboValues(HttpServletRequest objRequest, HttpServletResponse objResponse)
	{
		UserVO userVO = ControllerUTIL.getUserVO(objRequest);
		String strTemp[] = null;
		String strChk = "";
		String strTestCode="";
		String strTestType="";
		String StrTimeofMeasurement="";
		LoincVO locModel =new LoincVO();
		LoincMasterBO bo = new LoincMasterBO();
		
		try
		{
			
			strChk = objRequest.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			String str1[]= strTemp[0].replace('@', '#').split("#");
			String strOldLoincCode = str1[0];
			String StrSeqNo = str1[1];
			
			locModel.setStrOldLoincCode(strOldLoincCode);
			locModel.setStrSeqNo(StrSeqNo);
			List lstCheckboxValues = bo.GetValuesForSelectedCheckbox(locModel, userVO);
	
			//get the value of the checkbox selected
			Entry e=(Entry)lstCheckboxValues.get(0);
			 strChk= e.getLabel();
			 strTemp = strChk.replace('&', '#').split("#");
		
		String strTestNameCode= strTemp[0].replace('@','^');
		String strTestParaNameCode = strTemp[1].replace('@','^');
		String strTestSampleNameCode=  strTemp[2].replace('@','^');
		String StrTestNameValue =  strTemp[3].replace('@','^');
		
		String[] strTestNameCodeSplit =strTestNameCode.replace('^', '#').split("#");
		strTestCode = strTestNameCodeSplit[0]; 
		strTestType = strTestNameCodeSplit[1];
		StrTimeofMeasurement = strTestNameCodeSplit[2];
		
		String[] strTestParaNameCodeSplit =strTestParaNameCode.replace('^', '#').split("#");
		String strScale = strTestParaNameCodeSplit[1]; 
		locModel.setStrScale(strScale);
				
		//locModel.setstrTestSampleCode(strTestSampleNameCode);//set sample code
		
		String[] strTestSampleNameCodeSplit =strTestSampleNameCode.replace('^', '#').split("#");
		String strUOMCode;
		String strUOMCode1=strTestSampleNameCodeSplit[1];
		String strUOMCode2=strTestSampleNameCodeSplit[2];
		String append="^";
		strUOMCode = strUOMCode1 + append + strUOMCode2;
		//locModel.setStrUOMCode(strUOMCode);
		
		locModel.setStrProperty(strUOMCode.replace('^', '#').split("#")[1]);
		
		String StrSystem= strTestSampleNameCodeSplit[3];
		locModel.setStrSystem(StrSystem);//set system
		
		List lstTestPara=LoincMstDATA.getTestParaName_AJAX(userVO,strTestCode);
		objRequest.getSession().setAttribute("testParaName",lstTestPara);
		List lstSample=LoincMstDATA.getTestSample_AJAX(userVO,strTestCode,strTestType);	
		objRequest.getSession().setAttribute("testSample",lstSample);
		
		locModel.setStrTestNameValue(StrTestNameValue.trim());
		locModel.setStrTestNameCode(strTestNameCode);
		locModel.setstrTestParaNameCode(strTestParaNameCode);
		locModel.setstrTestSampleCode(strTestSampleNameCode);//set sample code
		locModel.setStrTimeofMeasurement(StrTimeofMeasurement);
		locModel.setStrUOMCode(strUOMCode);
		
		//save seq and old loinc code in session to get while updation
		objRequest.getSession().setAttribute("StrSeqNo",StrSeqNo);
		objRequest.getSession().setAttribute("StrOldLoincCode",strOldLoincCode);
		
		LoincVO[] arrLoincVo =  bo.searchSuggestiveLoinc(locModel, userVO);
		
		if(arrLoincVo == null && arrLoincVo.length == 0)
			locModel.setStrErrorMsg("No LOINC Code found");
		WebUTIL.setAttributeInSession(objRequest,"lstLoincVo",arrLoincVo);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		finally 
		{
			//LocationMstDATA_obj = null;
		//	strMsgText = null;
		}
		return locModel;
	}
	public static void getTestParaName_AJAX(LoincVO locModel,HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		//LoincVO locModel= new locModel;
		locModel = new LoincVO();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		String strTestCode="";
		String strTestType="";
		String StrTimeofMeasurement="";
		String strAjaxParaTypeCode="";
		UserVO userVO = ControllerUTIL.getUserVO(objRequest);
		try
		{
			
			 strAjaxParaTypeCode =	(String)objRequest.getParameter("strAjaxParaTypeCode");
			if(strAjaxParaTypeCode!=null)
			{
				
			locModel.setStrTestNameCode(strAjaxParaTypeCode);
			String strAjaxParaTypeCodeValue =	strAjaxParaTypeCode.replace('^', '#');
			String[] parts = strAjaxParaTypeCodeValue.split("#");
			strTestCode = parts[0]; 
			 strTestType = parts[1];
			 StrTimeofMeasurement = parts[2];
			
			locModel.setStrTestNameValue(strTestCode);
			String a = locModel.getStrTestNameCode();
			locModel.setStrTimeofMeasurement(StrTimeofMeasurement);
					
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);		
			
			List lstTestPara=LoincMstDATA.getTestParaName_AJAX(userVO,strTestCode);			
			objRequest.getSession().setAttribute("testParaName",lstTestPara);
			createOptionObject((List<Entry>)lstTestPara,responseDocument,"patAddTestParaCode");				
			List lstSample=LoincMstDATA.getTestSample_AJAX(userVO,strTestCode,strTestType);			
			createOptionObject((List<Entry>)lstSample,responseDocument,"patAddSampleCode");
			objRequest.getSession().setAttribute("testSample",lstSample);
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			if(strAjaxParaTypeCode!=null)
			{
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			writeResponse(objResponse, outputString);
		}	
			}
		
	}
	
	public static boolean updateLoincDtl(HttpServletRequest request, HttpServletResponse objResponse,LoincVO locModel)
	{
	LoincVO objLocModel;
	boolean bExistStatus=false;
	try 
	{		
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		//get Loinc Code
		String strLoincCode= request.getParameter("strLoincCode").toString();
		
		// get Old Loinc code 
		String strOldLoincCode= request.getSession().getAttribute("StrOldLoincCode").toString();
				
		if(strLoincCode==strOldLoincCode)
		{
			locModel.setStrWarning("Same Loinc Code selected ..!");
		
		}
		else
		{
		//set Seq no and Loinc Code
		String StrSeqNo= request.getSession().getAttribute("StrSeqNo").toString();
		locModel.setStrSeqNo(StrSeqNo);
		locModel.setStrLoincCode(strLoincCode);
		locModel.setStrOldLoincCode(strOldLoincCode);
		
		objLocModel=locModel;
		//objLocModel.setStrHospitalCode(uservo.getHospitalCode());
		LoincMasterBO bo = new LoincMasterBO();
		bExistStatus =  bo.updateLoincDtl(objLocModel,uservo);
		if(bExistStatus)
		{
			//mp.put("save_1"  , objLocModel);
			//String[] arrKeyVariables= new String[1];
			//arrKeyVariables[0]=objLocModel.getStrLocCode();
			//AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
		

		if (objLocModel.getStrMsgType()!=null && objLocModel.getStrMsgType().equals("1"))
		{
			throw new Exception(objLocModel.getStrMsgString());
		}

		if (bExistStatus == false) 
		{
			objLocModel.setStrWarning("Duplicate Name Exist..!");
		} 
		else 
		{
			objLocModel.setStrMsg("Data Saved Successfully");
		}
		}
		}
		//Audit Log on update
		
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		String strmsgText = "LocationMstDATA.updateLocationDtl(vo) --> "+ e.getMessage();
		locModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
	} 
	finally 
	{
		objLocModel = null;
	}
	return bExistStatus;
}

public static void GetPropertyCombo(HttpServletRequest objRequest, HttpServletResponse objResponse) {
	
	
	try{
		
		UserVO userVO = ControllerUTIL.getUserVO(objRequest);
				
		List lstProperty=LoincMstDATA.GetPropertyCombo(objRequest);
		objRequest.getSession().setAttribute("UOMList",lstProperty);					
	}
	
	catch(Exception e){
		e.printStackTrace();
	}		
	
	
}
public static void writeResponse(HttpServletResponse resp, String output){
	try{
		resp.reset();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/xml");
		resp.setHeader("Cache-Control", "no-cache");
		System.out.println(output);
	resp.getWriter().write(output);
	}
	catch(Exception e){
		e.printStackTrace();
		System.out.println(e);
	}
}
private static void createOptionObject(List<Entry> list,Document responseDocument, String fieldName) 
{
 
	Element fieldElement=responseDocument.createElement(fieldName);
	
	for(Entry entry:list)
	{
		Element option= responseDocument.createElement("option");
		fieldElement.appendChild(option);
		
		option.setAttribute("value", entry.getValue());			
		option.setAttribute("label", entry.getLabel());
	}
	responseDocument.getFirstChild().appendChild(fieldElement);
}
		

}
