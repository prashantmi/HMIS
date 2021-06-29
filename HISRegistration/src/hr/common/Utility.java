package hr.common;


import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisNoRecordException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONValue;

import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;

public class Utility {

	public static String getListQueryWithCondition (String query, String where, String orderBy, String orderCond, int offset, int limit) {
		return "Select * from (" + query + " ) " + where + " order by " + orderBy + " " + orderCond + " offset " + offset + " limit " + limit;
	}
	
	public static String getListQueryWithCondition (String query, String where) {
		return "Select count(*) from (" + query + " ) " + where;
	}
	
	public static String getReportQueryWithCondition (String query, String where) {
		return "Select * from (" + query + " ) " + where;
	}
	
	public static JSONObject createErrorJSONMessage(String message) throws JSONException {
		JSONObject jobj = new JSONObject();
		jobj.put("status", 0);
		jobj.put("msg", message);
		return jobj;
	}
	
	public static JSONObject createInfoJSONMessage(String message) throws JSONException {
		JSONObject jobj = new JSONObject();
		jobj.put("status", 1);
		jobj.put("msg", message);
		return jobj;
	}
	
	public static void logQueryAndParams (String query, List<? extends Object> params) {
		if (query != null)
			System.out.println("Query = " + query);
		else 
			System.out.println("Query is Null.");
		if (params != null) 
			System.out.println("Parameters = " + params);
		else 
			System.out.println("Parameters are Null.");
	}
	
	public static void printVO (Object obj){
		Class<? extends Object> cls = obj.getClass();
		Method[] methods = cls.getDeclaredMethods();
		System.out.println("Printing getters for values = " + cls.getName());
		for (int i=0;i<methods.length;++i) {
			String mName = methods[i].getName();
			if (mName.startsWith("get")) {
				try {
					Object o = methods[i].invoke(obj, (Object[]) null);
					if (o == null) {
						System.out.println(mName.substring(3) + " = null");
					} else {
						if (o instanceof Object[]) {
							System.out.print(mName.substring(3) + " = ");
							Object[] oa = (Object[]) o;
							String res = "[";
							int j=0;
							for (;j<oa.length;++j)
								res += oa[j].toString() + ",";
							if (j>0)
								res = res.substring(0, res.length()-1);
							res += "]";
							System.out.println(res);
						} else {
							System.out.println(mName.substring(3) + " = " + o.toString());
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void printVOWithoutBlank (Object obj){
		Class<? extends Object> cls = obj.getClass();
		Method[] methods = cls.getDeclaredMethods();
		System.out.println("Printing getters for values = " + cls.getName());
		for (int i=0;i<methods.length;++i) {
			String mName = methods[i].getName();
			if (mName.startsWith("get")) {
				try {
					Object o = methods[i].invoke(obj, (Object[]) null);
					if (o == null) {
						continue;
					} else {
						if (o instanceof Object[]) {
							Object[] oa = (Object[]) o;
							if (oa.length <= 0)
								continue;
							System.out.print(mName.substring(3) + " = ");							
							String res = "[";
							int j=0;
							for (;j<oa.length;++j)
								res += oa[j].toString() + ",";
							if (j>0)
								res = res.substring(0, res.length()-1);
							res += "]";
							System.out.println(res);
						} else {
							if (!"".equals(o.toString()))
								System.out.println(mName.substring(3) + " = " + o.toString());
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Date getDateFromString (String date) {
		Date dt = null;
        Calendar cal = Calendar.getInstance();
        String[] dateFormats = {
        		"dd-MMM-yyyy hh:mm", "dd-MMM-yyyy", "dd-MMM-yyyy hh:mm:ss",
                "dd-MM-yyyy hh:mm", "dd-MM-yyyy", "dd-MM-yyyy hh:mm:ss",
                "MM-dd-yyyy hh:mm", "MM-dd-yyyy", "MM-dd-yyyy hh:mm:ss",
                "yyyy-MM-dd hh:mm", "yyyy-MM-dd", "yyyy-MM-dd hh:mm:ss",
                "dd/MMM/yyyy hh:mm", "dd/MMM/yyyy", "dd/MMM/yyyy hh:mm:ss",
                "dd/MM/yyyy hh:mm", "dd/MM/yyyy", "dd/MM/yyyy hh:mm:ss",
                "MM/dd/yyyy hh:mm", "MM/dd/yyyy", "MM/dd/yyyy hh:mm:ss",
                "yyyy/MM/dd hh:mm", "yyyy/MM/dd", "yyyy/MM/dd hh:mm:ss"
        };
        for (String dateFormat: dateFormats) {
            try {
                cal.setTime(new SimpleDateFormat(dateFormat).parse(String.valueOf(date)));
                dt = new Date(cal.getTimeInMillis());
                break;
            } catch (ParseException ignore) {}
        }
        return dt;
	}
	
	public static List<String> jsonStringFromResultset (ResultSet rs) 
			throws SQLException {
		List<String> lst = new ArrayList<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {			
			String s = "{";
			for (int i=1;i<=rsmd.getColumnCount();++i) {
				s += "\"" + rsmd.getColumnLabel(i) + "\":\"" + (rs.getString(i) == null ? "" : rs.getString(i)) + "\",";
			}
			s = s.substring(0, s.length()-1);
			s += "}";
			lst.add(s);
		}
		return lst;
	}
	
	public static String getComboAsJSONStringFromResultSet(ResultSet rs) {
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();		
		try {
			obj.put("key", "-1");
			obj.put("value", "Select Value");
			arr.put(obj);
    		while (rs.next()) {
    			obj = new JSONObject();
    			obj.put("key", rs.getString(1));
    			obj.put("value", rs.getString(2));
    			arr.put(obj);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr.toString();
	}
	
	public static void populateVOfrmRS(Object _vo, ResultSet _rs) {
		try {
			Class<? extends Object> cls = _vo.getClass();
			Method[] clsMethods = cls.getMethods();

			ResultSetMetaData rsMetaData = _rs.getMetaData();
			int rsCols = rsMetaData.getColumnCount();

			if (_rs.isBeforeFirst())
				_rs.next();
			if (_rs.isAfterLast())
				throw new HisNoRecordException();

			for (int i = 1; i <= rsCols; i++) {
				String strColLabel = rsMetaData.getColumnLabel(i);
				String strColVal = _rs.getString(rsMetaData.getColumnName(i));
				char[] arrCh = strColLabel.toCharArray();
				arrCh[0] = Character.toUpperCase(arrCh[0]);
				String strMethodName = new String(arrCh);
				strMethodName = "set" + strMethodName;
				int j;
				for (j = 0; j < clsMethods.length; j++) {
					if (clsMethods[j].getName().equalsIgnoreCase(strMethodName)) {
						clsMethods[j].invoke(_vo, new Object[] { strColVal });
						break;
					}
				}
				if (j > clsMethods.length)
					throw new HisException("PaybillUtility.populateVOfrmRS(): No setter for " + strMethodName);
			}
		} catch (HisException e) {
			throw e;
		} catch (Exception e) {
			throw new HisException("PaybillUtility.populateVOfrmRS::" + e);
		}
	}
	
	public static void writeXMLResponse(HttpServletResponse resp, String output) {
        try {
            resp.reset();
            resp.setContentType("text/xml");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-cache");
            resp.getWriter().write(output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	public static void writeHTMLResponse(HttpServletResponse resp, String output) {
        try {
            resp.reset();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-cache");
            resp.getWriter().write(output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	 
	public static void writeJSONResponse(HttpServletResponse resp, String output) {
	    try {
	        resp.reset();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        resp.setHeader("Cache-Control", "no-cache");
	        resp.getWriter().write(output);
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
	
	public static void writeResponse(HttpServletResponse resp, String output) {
	    try {
	        resp.reset();
	        resp.setContentType("text/xml");
	        resp.setCharacterEncoding("UTF-8");
	        resp.setHeader("Cache-Control", "no-cache");
	        resp.getWriter().write(output);
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
	
	public static String getPrintString() throws Exception {
		StringBuffer s = new StringBuffer();

		s.append("function settingHasFocus() {\n");
		s.append("\t\t  var AllElts=new Array();\n AllElts=document.getElementsByTagName('img');\n ");
		s.append("\t\tfor ( var j=0;j<AllElts.length;j++)\n { \n AllElts[j].hasFocus=false; \n AllElts[j].onfocus=function(){ \n this.hasFocus=true;}\n  AllElts[j].onblur =function(){ \n this.hasFocus=false;}\n ");
		s.append("\t\t AllElts[j].setAttribute('tabIndex','1');\n ");
		s.append("\t\t} \n } \n ");

		s.append("\t\tfunction keypressed(event){\n");
		s.append("\t\t  var key; if(navigator.appName == 'Microsoft Internet Explorer') { key=window.event.keyCode; \n } \n else \n { key=event.keyCode; \n }  \n if(key=='13') { \n TrackFocus(event); // Function called on pressing enter key \n ");
		s.append("\t\t} \n }");
		s.append("\t\tfunction TrackFocus(event) { \n\n");
		s.append("var AllElts=new Array();\n var fname;\n ");
		s.append("\t\t  \n AllElts=document.getElementsByTagName('img');\n ");
		s.append("\t\tfor ( var j=0;j<AllElts.length;j++){ \n if(AllElts[j]['onclick']) { \n  \n if(AllElts[j].hasFocus==true) { \n ");
		s.append("\t\t  fname=AllElts[j].funcName;\n  var retEval=eval(fname); \n break; \n ");
		s.append("\t\t} \n } \n } \n } \n");
/*
		s.append("\t\tfunction load() {\n ");
		s.append("\t\t  \n var AllElts=new Array();\n AllElts=document.getElementsByTagName('img')\n ");
		s.append("\t\tfor ( var j=0;j<AllElts.length;j++){\n var imgElem=AllElts[j];\n var temp =AllElts[j].getAttribute('src');\n var tempSubstr=temp.substr((temp.lastIndexOf('/')+1),10);\n  if(tempSubstr.toUpperCase()=='NC_PRINTER'){\n  AllElts[j].onclick = function(){onPrintCall();};  AllElts[j].funcName='onPrintCall()'}\n else if (tempSubstr.toUpperCase()=='CANCEL'){\n AllElts[j].onclick = function(){cancelLoc();}; \n AllElts[j].funcName='cancelLoc()';\n }\n}\n ");
		s.append("\t\t \n printButtonRemove();\n printButtonShow();\n settingHasFocus(); \n ");
		s.append("\t\t}\n");
		
		s.append("\t\tfunction onPrintCall(){\n");
		s.append("\t\t  \n printButtonRemove(); window.print(); setTimeout(\"printButtonShow()\", 3000);\n ");
		s.append("\t\t}");
		s.append("\t\tvar AllElts=new Array();\n\n");
		s.append("\t\tfunction printButtonRemove(){\n");
		s.append("\t\t  \n while((el=document.getElementsByTagName('img')).length){AllElts.push(el[0]);\n el[0].parentNode.removeChild(el[0])}\n ");
		s.append("\t\t}");
		
		s.append("\t\tfunction printButtonShow(){\n");
		s.append("\t\t  \n  \n  var body = document.getElementsByTagName('body');\n body[0].onkeypress = function(event){keypressed(event);}; \n var div1= document.createElement('DIV');\n div1.style.cssText='position:absolute;height:50px;top:10px;right:10px;'; \n body[0].appendChild(div1);\n div1.appendChild(AllElts[0]);\n div1.appendChild(AllElts[1]);\n ");
		s.append("\t\t}");
		s.append("\t\tfunction cancelLoc(){\n");
		s.append("\t\t  \n window.location=this.location;\n ");
		s.append("\t\t}");
*/
		
		s.append("\t\tfunction load(url) {\n ");
		s.append("\t\t  \n var AllElts=new Array();\n AllElts=document.getElementsByTagName('img')\n ");
		s.append("\t\tfor ( var j=0;j<AllElts.length;j++){\n var imgElem=AllElts[j];\n var temp =AllElts[j].getAttribute('src');\n var tempSubstr=temp.substr((temp.lastIndexOf('/')+1),10);\n  if(tempSubstr.toUpperCase()=='NC_PRINTER'){\n  AllElts[j].onclick = function(){onPrintCall();};  AllElts[j].funcName='onPrintCall()'}\n else if (tempSubstr.toUpperCase()=='NC_CANCEL.'){\n AllElts[j].onclick = function(){ alert('1:'+url); cancelLoc(url);}; \n AllElts[j].funcName='cancelLoc()';\n }\n}\n ");
		//s.append("\t\t  \n AllElts=document.getElementsByTagName('td')\n ");
		//s.append("\t\tfor ( var j=0;j<AllElts.length;j++){\n var imgElem=AllElts[j];\n var temp =AllElts[j].getAttribute('border');\n if(temp.toUpperCase()=='0.5'){\n AllElts[j].style.border='0.5px solid'; }\n else if (tempSubstr.toUpperCase()=='CANCEL'){\n }\n}\n ");
		//s.append("\t\t \n printButtonRemove();\n printButtonShow();\n settingHasFocus(); \n ");
		s.append("\t\t}\n");
		
		s.append("\t\tfunction onPrintCall(){\n");
		s.append("\t\t  \n printButtonRemove(); window.print(); setTimeout(\"printButtonShow()\", 3000);\n ");
		s.append("\t\t}");
		s.append("\t\tvar AllElts=new Array();\n\n");
		s.append("\t\tfunction printButtonRemove(){\n");
		//s.append("\t\t  \n while((el=document.getElementsByTagName('img')).length){AllElts.push(el[0]);\n el[0].parentNode.removeChild(el[0])}\n ");
		s.append("\t\t  \n var AllElts=new Array();\n AllElts=document.getElementsByTagName('img')\n ");
		s.append("\t\tfor ( var j=0;j<AllElts.length;j++){\n var imgElem=AllElts[j];\n var temp =AllElts[j].getAttribute('src');\n var tempSubstr=temp.substr((temp.lastIndexOf('/')+1),10);\n  if(tempSubstr.toUpperCase()=='NC_PRINTER'){\n AllElts[j].style.display='none';}\n else if (tempSubstr.toUpperCase()=='NC_CANCEL'){\n }\n}\n ");
		s.append("\t\t}");
				
		s.append("\t\tfunction printButtonShow(){\n");
		//s.append("\t\t  \n  \n  var body = document.getElementsByTagName('body');\n body[0].onkeypress = function(event){keypressed(event);}; \n var div1= document.createElement('DIV');\n div1.style.cssText='position:absolute;height:50px;top:10px;right:10px;'; \n body[0].appendChild(div1);\n div1.appendChild(AllElts[0]);\n div1.appendChild(AllElts[1]);\n ");
		s.append("\t\t  \n var AllElts=new Array();\n AllElts=document.getElementsByTagName('img')\n ");
		s.append("\t\tfor ( var j=0;j<AllElts.length;j++){\n var imgElem=AllElts[j];\n var temp =AllElts[j].getAttribute('src');\n var tempSubstr=temp.substr((temp.lastIndexOf('/')+1),10);\n  if(tempSubstr.toUpperCase()=='NC_PRINTER'){\n  AllElts[j].style.display='';}\n else if (tempSubstr.toUpperCase()=='NC_CANCEL'){\n }\n}\n ");
		s.append("\t\t}");
		s.append("\t\tfunction cancelLoc(url){\n");
		//s.append("\t\t  \n window.location=this.location;\n ");
		s.append("\t\t  \n alert('2:'+url);\n ");	 
		  
		 // s.append("\t\t  \n callMenu(url);\n ");
		// s.append("\t\t  \n window.location=url;\n ");
		s.append("\t\t  \n  window.history.go(-1);\n ");
	//	s.append("\t\t  \n  location.replace(this.url);\n ");
		
		
		  //s.append("\t\t  \n javascript:history.back(-1);\n ");
		//s.append("\t\t  \n alert('3:'+url); window.location.replace(url);\n ");
		 
		
		
		s.append("\t\t}");
		
		return s.toString();
	}

	// Create a Horizontal Line in iText
	public static Cell horizontalLine (int cols) {
        Cell pcell = new Cell("");
		pcell.add(new Phrase(" ", new Font(Font.TIMES_ROMAN, 8, Font.BOLD)));
		pcell.setColspan(cols);
		pcell.setBorder(Rectangle.BOTTOM);
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		return pcell;
    }
	
	// Create End of Report Line in iText
	public static Cell endOfReport (int cols) {
        Cell pcell = new Cell("");
		pcell.add(new Phrase("******** End of Report ********", new Font(Font.TIMES_ROMAN, 8, Font.BOLD)));
		pcell.setColspan(cols);
		pcell.setBorder(Rectangle.NO_BORDER);
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return pcell;
    }
	
	public static boolean isLeapYear(int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}
	
	
	
	
	
	
	
	
	
	
	/*
	public  static void populateHospitalDetails(Object obj1, HttpServletRequest objRequest) 
	{		
		HospitalMstVO objHospitalMstVO = ControllerUTIL.getHospitalVO(objRequest);	
		Object obj2 = objHospitalMstVO;
		
	 	//private String hospitalCode;
		//private String hospitalName;	
		//private String address1;
		//private String address2;
		//private String city;
		//private String state;	
		//private String phone;
		//private String fax;
		//private String email;
		//private String contactPerson;
		//private String isActive;
		//private String isValid;
		//private String seatId;
		//private String entryDate;
		//private String hl7Code;
		//private String hospitalShortName;	
		//private String lastModifyDate;
		//private String lastModifySeatId;	
		//private String remarks;
		//private String isAssociated;
		//private String hospitalTypeCode;
		//private String hospitalTypeName;
		//private String hospitalCategory;
		//private String orgType;
		//private String password;	
		//private String userName;	
		//private String busRouteNo;
		//private String bedCapacity;	
		//private String weekdayTimings;
		//private String saturdayTimings;
		//private String lunchTimings;
		//private String pinCode;
		//private String districtCode;
		//private String panNo;
		//private String tanNo;
		//private String userLiscenceAllowed;
		//private String languageCode;
		//private String localLangCode;
		//private String languageName;
		//private String localLangName;
		//private String districtName;
		//private String stateName;
	 
		
			Class cls2 = obj2.getClass();
			Method[] cls2Methods = cls2.getMethods();

			HashMap mpGettersInCls2 = new HashMap();
			for(int i=0; i<cls2Methods.length; i++)
			{
				if(cls2Methods[i].getName().indexOf("get")==0)
				{//if the method name starts with set
					mpGettersInCls2.put(cls2Methods[i].getName().substring(3), new Integer(i));
				}
			}

			Class cls1 = obj1.getClass();
			Method[] cls1Methods = cls1.getMethods();

			try
			{
				for(int i=0; i<cls1Methods.length; i++){
				if(cls1Methods[i].getName().indexOf("set")==0)
				{//if the method name starts with set
					if(mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(3)))
					{
						int idx = ((Integer)mpGettersInCls2.get(cls1Methods[i].getName().substring(3))).intValue();
						Object str = cls2Methods[idx].invoke(obj2, null);
						cls1Methods[i].invoke(obj1, new Object[]{str});
					}
				}
				obj1.getClass().getMethod("setCityName",String.class).invoke(obj1, (obj2.getClass().getMethod("getCity").invoke(obj2, null)));
				obj1.getClass().getMethod("setStateCode",String.class).invoke(obj1, (obj2.getClass().getMethod("getState").invoke(obj2, null)));
			}
			}catch(Exception e){
				e.printStackTrace();				
			}
			
		}
	 */
	
	/*
	 
	
	/**
	 * This method is used to create filter criteria for list page
	 * @param _filters is String Parameter 
	 * @param _search is String Parameter
	 * @param calMode is int Parameter
	 * @return where condition
	 * Created By : Ashwini Mishra
	 * Date		  : Apr 2014
	 */
	
	public static String getListFilterCriteria(String _filters, String _search, int calMode)
	{
		String _where="";
		try
		{
		  if(_filters!=null){if((_search.equalsIgnoreCase("true")) &&(_filters != "")) {
			JSONObject myObject=new JSONObject(_filters);
	    	JSONArray $rules=myObject.getJSONArray("rules");
	    	String $groupOperation=myObject.getString("groupOp"), _fo = "";
	    	if($rules.length()>0){if(calMode==2)_where+=" ( ";else _where+=" where ( ";}
	    	if(_filters.contains("groups")){   		
	    	JSONArray $groups=myObject.getJSONArray("groups");
	    	for(int i=0;i<$groups.length();i++){ _where+=getListFilterCriteria($groups.getString(i), "true",2);}}
	    	for(int i=0;i<$rules.length();i++){
	    	JSONObject myObject2 = $rules.getJSONObject(i);
	    	String $fieldName=myObject2.getString("field"), _fd = myObject2.getString("data"),
	    	$fieldOp = myObject2.getString("op"), _where_temp="";
	    	if(_fd!=null&&_fd!=""&&_fd.length()>0){switch ($fieldOp) {
	    	case "eq":_fo=" = upper('"+_fd+"')"; break; case "ne":_fo=" != upper('"+_fd+"')"; break;
	    	case "lt":_fo=" < upper('"+_fd+"')"; break;case "gt":_fo=" > upper('"+_fd+"')"; break;
            case "le":_fo=" <= upper('"+_fd+"')"; break;case "ge":_fo=" >= upper('"+_fd+"')"; break;
	    	case "nu":_fo=" = upper('')"; break;case "nn":_fo=" != upper('')"; break;
	    	case "in":_fo=" IN (upper('"+_fd+"'))"; break;case "ni":_fo=" NOT IN upper('"+_fd+"')"; break;
	    	case "bw":_fo=" LIKE upper('"+_fd+"%')"; break;case "bn":_fo=" NOT LIKE upper('"+_fd+"%')"; break;
	    	case "ew":_fo=" LIKE upper('%"+_fd+"')"; break;case "en":_fo=" NOT LIKE upper('%"+_fd+"')"; break;
	    	case "cn":_fo=" LIKE upper('%"+_fd+"%')"; break;case "nc":_fo=" NOT LIKE upper('%"+_fd+"%')"; break;
	    	default:_fo=""; break;}
	    	if(_fo != "") _where_temp = " upper("+$fieldName+") "+_fo;}else{_where_temp = " 1=1 ";}
	    	//if (_where.length()>0){_where+=(" "+$groupOperation+" "+ _where_temp);}else{_where+=(" where "+_where_temp);}	    		            
	    	if (i==0){_where+=(""+_where_temp);}else{_where+=(" "+$groupOperation+" "+ _where_temp);}
	    	} if($rules.length()>0){if(calMode==2)_where+=" ) "+$groupOperation+" ";else _where+=" ) ";}  }}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException("Helpermethods.getListFilterCriteria::" + e);
		}
		//System.out.println("after getListFilterCriteria ");
		return _where;
	}
	
	
	/**
	 * This method is used to create JSON String from ResultSet
	 * @param _rs is ResultSet Parameter 
	 * @return JSON String
	 * Created By : Ashwini Mishra
	 * Date		  : 07 Apr 2016
	 */
	public static String jsonStringWithColumnNameFromResultSet(ResultSet _rs) throws SQLException
	{
		_rs.beforeFirst();
		List<Map<String, Object>> outerList = new ArrayList<Map<String, Object>>();
		ResultSetMetaData rsmd = _rs.getMetaData();
		_rs.beforeFirst();
		int col = 0;
		while (_rs.next())
		{
			Map<String, Object> mapObj = new HashMap<String, Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++){
				mapObj.put(rsmd.getColumnLabel(i), _rs.getString(i));
			}
			outerList.add(col, mapObj);
			col++;			
		}
		
		if(outerList!=null && outerList.size()>0)
		{
		}
		else
		{
			System.out.println("List is Null or Empty.");
		}
		
		String outputString=JSONValue.toJSONString(outerList);
		return outputString;
		
	}
	
	/**
	 * This method is used to create JSON String from WebRowSet
	 * @param _rs is WebRowSet Parameter 
	 * @return JSON String
	 * Created By : Ashwini Mishra
	 * Date		  : 07 Apr 2016
	 */
	public static String jsonStringWithColumnNameFromWebRowSet(WebRowSet _webRs) throws SQLException
	{
		_webRs.beforeFirst();
		List<Map<String, Object>> outerList = new ArrayList<Map<String, Object>>();
		ResultSetMetaData rsmd = _webRs.getMetaData();
		_webRs.beforeFirst();
		int col = 0;
		while (_webRs.next())
		{
			Map<String, Object> mapObj = new HashMap<String, Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++){
				mapObj.put(rsmd.getColumnLabel(i), _webRs.getString(i));
			}
			outerList.add(col, mapObj);
			col++;			
		}
		
		if(outerList!=null && outerList.size()>0)
		{
		}
		else
		{
			System.out.println("List is Null or Empty.");
		}
		
		String outputString=JSONValue.toJSONString(outerList);
		return outputString;
		
	}
	
}
