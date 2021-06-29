package billing.masters.controller.util;

import hisglobal.transactionmgnt.HisDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONObject;

import billing.masters.controller.fb.GblConfigTypeFB;

public class GblConfigTypeUtil 
{	
	static int id=0;
	public static String getGblConfigData(HttpServletRequest request,HttpServletResponse response ,GblConfigTypeFB fb ) throws SQLException 
	{
		WebRowSet ws = null;
		int nIndex;
		HisDAO hisdao = new HisDAO("GblConfigTypeUtil", "getGblConfigData()");
		StringBuffer html=null;
		html=new StringBuffer();
		String query ="";
		
		try
		{
			html.append(getHtmlHeader("Registration Data Status"));
			query = "select *  from hrgt_max_crno where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("hrgt_max_crno",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select HRGNUM_CONFIG_GROUP,HRGNUM_ISDUPLICATE_CHECK,HRGNUM_ISTEMP_REG,HRGNUM_TIMING_CHECK,HRGNUM_MLC_MODE,HRGNUM_IS_CROSSCONSULT_CHARGEABLE,HRGNUM_IS_APPOINTMENT_BASED,HRGNUM_IS_HOURBASED,HRGNUM_HOURS,HRGNUM_IS_AADHAAR_SEARCHABLE,HRGNUM_SENIOR_CITIZEN_AGE_LIMIT,HRGNUM_SENIOR_CITIZEN_CAT_CODE,	HRGNUM_IS_MOBILE_SEARCH,	HRGNUM_IS_IMAGE_FTP,	HRGNUM_IS_BARCODESLIP_PRINT,	HRGNUM_IS_QUEUENO_MANUAL  from hrgt_reg_configuration_mst where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("hrgt_reg_configuration_mst",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select HRGNUM_RENEWAL_GROUP,	GNUM_PATIENT_CAT_CODE,HRGNUM_RENEWAL_TYPE,	HRGNUM_RENEWAL_MODE,	HRGNUM_DAYS,	HRGNUM_ISMULTIPLE_MONTH,	HRGSTR_MONTH1,	HRGSTR_MONTH2,	HRGSTR_MONTH3  from hrgt_renewal_config where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("hrgt_renewal_config",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select NUM_PARAM_ID,	NUM_PARAM_VAL_ID,	STR_PARAM_VAL,	STR_PARAM_MAX_VAL,	STR_PARAM_MIN_VAL,	STR_PARAM_DISPLAY_NAME,	NUM_PARAM_ORDER_ID from dss_config_param_val_sys where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("dss_config_param_val_sys",ws));
			html.append(getHtml(ws));
			id++;
			
			html.append(getHtmlHeader("Billing Data Status"));
			
			nIndex=0;			
			
			query = "select HBLNUM_GROUP_ID,	HBLSTR_GROUP_NAME  from HBLT_GROUP_MST where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("HBLT_GROUP_MST",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select SBLNUM_CHARGETYPE_ID,	HBLNUM_GROUP_ID  from HBLT_HSERVICE_GROUP_MST where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("HBLT_HSERVICE_GROUP_MST",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select HBLNUM_TARIFF_ID,	SBLNUM_SERVICE_ID,	HBLSTR_TARIFF_NAME,	HBLNUM_GROUP_ID,	GSTR_TARIFF_ID,HBLSTR_TARIFF_CODE  from HBLT_TARIFF_MST where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("HBLT_TARIFF_MST",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			
			query = "select SBLNUM_CHARGETYPE_ID,	HBLNUM_TARIFF_ID,	GNUM_PATIENT_CAT_CODE,	SBLNUM_IPD_CHARGETYPE_ID,	HBLNUM_CHARGE_SLNO,	HBLNUM_GROUP_ID,HBLNUM_COST  from HBLT_CHARGE_MST where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("HBLT_CHARGE_MST",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select SBLNUM_IPD_CHARGETYPE_ID,	SBLSTR_IPD_CHARGETYPE_NAME,	GSTR_WARD_ID,SBLNUM_CHARGETYPE_ID  from SBLT_IPD_CHARGETYPE_MST where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("SBLT_IPD_CHARGETYPE_MST",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select  SBLSTR_KEY_NAME,	SBLSTR_FORMAT,	SBLNUM_CURR_SEQUENCE,	SBLSTR_FORMAT_VALUE,	SBLNUM_PREFIX_VALUE,	SBLNUM_SEQ_LENGTH,	SBLNUM_START_VALUE from SBLT_PRIMARYKEY_DTL where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("SBLT_PRIMARYKEY_DTL",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select *  from SBLT_SERVICE_MST where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("SBLT_SERVICE_MST",ws));
			html.append(getHtml(ws));
			id++;
			
			/*nIndex=0;
			
			query = "select *  from HBLT_CLIENT_MST where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("HBLT_CLIENT_MST",ws));
			html.append(getHtml(ws));
			id++;*/
			
			nIndex=0;
			
			query = "select HBLNUM_PARAM_TYPE,	HBLSTR_PARAM_NAME,	HBLSTR_PARAM_VALUE	  from HBLT_BILLING_PARAMETER_DTL where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("HBLT_BILLING_PARAMETER_DTL",ws));
			html.append(getHtml(ws));
			id++;
			
			nIndex=0;
			
			query = "select HIPNUM_PARAM_TYPE,	HIPSTR_PARAM_NAME,	HIPSTR_PARAM_VALUE  from HIPT_ADT_PARAMETER_DTL where gnum_hospital_code="+fb.getHospCode();
			nIndex = hisdao.setQuery(query);		
			ws=hisdao.executeQry(nIndex);
			html.append(checkRowCount("HIPT_ADT_PARAMETER_DTL",ws));
			html.append(getHtml(ws));
			id++;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (hisdao != null)
			{
				hisdao.free();
				hisdao = null;
			}
			if (ws != null)
			{
				ws.close();
				ws = null;
			}
		}
		fb.setStrHtmlString(html.toString());
		return html.toString();
	}
	
	public static String getHtml( WebRowSet ws) throws SQLException
	{
		 int length=0;
		 String newId=Integer.toString(id);
		 ArrayList<String> al = new ArrayList<String>();	
		 StringBuffer sb=null;
		 sb=new StringBuffer();
		 sb.append("<table style='display:none;' border='1' id='DIV"+newId+"'>");
		 try
		 {
			 if(ws.size() > 0)
			 {			
				length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					String colName=ws.getMetaData().getColumnName(i).toUpperCase();
					/*if(colName.equals("GDT_ENTRY_DATE") || colName.equals("GNUM_SEAT_ID") || colName.equals("GDT_LSTMOD_DATE") || colName.equals("GNUM_LSTMOD_SEATID") || colName.equals("GNUM_SEAT_ID"))
						colName="";
					else*/
						al.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
				 
				 
				
				 if(al.size() > 0)
				 {
					 sb.append("<tr>");
					 for(int i=0; i<al.size() ;i++)
					 {					
						 sb.append("<th>"+al.get(i)+"</th>");
					 }
					 sb.append("</tr>");
				 }
				 while (ws.next()) 
				 {
					 sb.append("<tr>");
					 for (int j=1;j<=al.size();j++)
					 {
						 sb.append("<td>"+ws.getString(j)+"</td>");
					 }
					 sb.append("</tr>");
				 }
				
				 
			}
			else
			{
				 sb.append("<tr><td>No Reecord Found</td></tr>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		sb.append("</table>");	
		return sb.toString();
	}
	
	public static String checkRowCount( String tableName,WebRowSet ws) throws SQLException
	{
		StringBuffer sb=null;
		sb=new StringBuffer();
		String newId=Integer.toString(id);
		
		sb.append("<div class='row'>");
		sb.append("<div class='col-sm-4'>"+tableName.toUpperCase()+"</div>");
		String wsCountStatus=ws.size()==0?"<i class='far fa-times-circle' style='color:red;'></i>":"<i class='far fa-check-circle' style='color:#5fba7d;'></i>";
		sb.append("<div class='col-sm-1'>"+wsCountStatus+"</div>");
		sb.append("<div class='col-sm-1'><i class='fas fa-angle-double-right' style='color:#55acee;cursor:pointer' onclick='openDiv("+newId+")'></i></div>");
		sb.append("</div>");
			
		return sb.toString();
	}
	
	public static String getHtmlHeader(String header) throws SQLException
	{
		StringBuffer sb=null;
		sb=new StringBuffer();
		sb.append("<div class='row'>");
		sb.append("<div class='col-sm-12' align='center'>");
		sb.append("<hr>");
		sb.append("<h3>"+header+"</h3>");
		sb.append("<hr>");
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}
    	
}
