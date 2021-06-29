package auditlogClient.util;

import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import auditlogClient.serviceCall.AuditlogServiceCall;
import auditlogClient.vo.AuditLogVO;

public class AuditlogDATA {
	private static  final Class<?> []PRIMITIVE_TYPES = { 
		String.class,StringBuffer.class,Integer.class,
		Float.class,Boolean.class,Double.class,Long.class
	};
	private static  final Class<?>[] ARRAY_PRIMITIVE_TYPES = { 
	        int[].class, float[].class, double[].class, boolean[].class, 
	        byte[].class, short[].class, long[].class, char[].class, String[].class };
			
	
	private static  final Class<?> []Collection_TYPES = { 
		java.util.ArrayList.class,java.util.HashMap.class
	};
	
	private static final String []valueToDiscard={"-1", "\"java/lang/String\":[]"};
	private static Map<String, String > mpCompare= new LinkedHashMap<String, String >();
	private static boolean compareFlag;
	public static void initAuditLog(String moduleId, String processId, Map mp,UserVO userVO, HttpServletRequest request) {	
		
		request.getSession().removeAttribute("AUDITOBJ");
		try{
			mpCompare=null;
			mpCompare= new LinkedHashMap<String , String>();
			compareFlag=false;
			AuditLogVO objAuditLogVO= new AuditLogVO(moduleId,processId);
			HelperMethods.populate(objAuditLogVO, userVO);
			objAuditLogVO =AuditlogServiceCall.checkAuditLogEnabledForObject(objAuditLogVO);
		if(objAuditLogVO!=null){
			
			if(objAuditLogVO.getIsInsertLogOn()==0)
				System.out.println("Insert Audil log is off for this process");
			
			if(objAuditLogVO.getIsUpdateLogOn()==0)
				System.out.println("Modify Audil log is off for this process");
			
			
			if(objAuditLogVO.getIsDeleteLogOn()==0)
				System.out.println("Delete Audil log is off for this process");
			//this.strModuleId = strModuleId;
			//this.strProcessId = strProcessId;
			/*objAuditLogVO.setStrModuleId(objInitAuditLogVO.getStrModuleId());
			objAuditLogVO.setStrProcessId(objInitAuditLogVO.getStrProcessId());*/
		//	objAuditLogVO.setMpobjectsOnInit(mp);
			if (objAuditLogVO.getIsDeleteLogOn()!=0 || objAuditLogVO.getIsUpdateLogOn()!=0){
				if(mp!=null && mp.size()>0){
					objAuditLogVO.setStrInitVariableJSON(getProcessedToAuditObject(mp));
					objAuditLogVO.setMpCompare(mpCompare);
					mpCompare=null;
				}
				else{
					System.out.println("object map object empty .. Audit logging cannot be done");
					return;
				}			
			}
			mp=null;
			request.getSession().setAttribute("AUDITOBJ",objAuditLogVO);
			
		}	
		else
			System.out.println("Audil log off .. use audit log process master to set it on");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Audil log service is not working!");
		}
	}
	
	private static  String getStringForPrimitiveDataType(Object val){
	    
		//System.out.println("getStringForPrimitiveDataType ---" + val);
	    String stroutput="";
	    boolean found=false;
	    if(val==null || val.equals(""))
	    	return "";
	    for(Class<?> arrKlass : PRIMITIVE_TYPES){
	        if(val.getClass().equals(arrKlass)){
	        	stroutput=val +"";
	        	found=true;
	            break;
	        }
	    }
	    if(found==false){
	    	Class<?> valKlass = val.getClass();
		    for(Class<?> arrKlass : ARRAY_PRIMITIVE_TYPES){
		        if(valKlass.isAssignableFrom(arrKlass)){
		            int arrlength = Array.getLength(val);
		            //outputArray = new Object[arrlength];
		            for(int i = 0; i < arrlength; ++i){
		              //  System.out.println(Array.get(val, i));
		              stroutput+=Array.get(val, i).toString()+ ",";
		            }
		            break;
		        }
		    }
	    }
	   
	    if(stroutput!=null && !stroutput.equals("") &&  stroutput.substring(stroutput.length()-1,stroutput.length()).equals(","))
	    	stroutput= stroutput.substring(0, stroutput.length()-1 );
	   //System.out.println(stroutput); 
	    return stroutput;
	}

	
	/*function to convert single object to json style to be saved in string  */
	private static String getAllVariableJSONString(Object obj){
		String strResult="";
		try{			
		Method[] cls2Methods = obj.getClass().getMethods();

		for(int i=0; i<cls2Methods.length; i++){
			if(cls2Methods[i].getName().indexOf("get")==0 && !cls2Methods[i].getName().equals("getClass") && !cls2Methods[i].getName().equals("getBytes")&& !cls2Methods[i].getName().equals("getChars") && !cls2Methods[i].getName().contains("ServletWrapper")  ){
				Object objField = cls2Methods[i].invoke(obj, null);
				if(objField!=null){
					//System.out.println("method----->" + cls2Methods[i].getName());
					String strVal=getStringForPrimitiveDataType(objField);					
					String variableName=cls2Methods[i].getName().substring(3,cls2Methods[i].getName().length());
					if(!objField.equals("") && strVal.equals(""))
						strResult+=getStringForObjectDataType(objField,cls2Methods[i].getName().substring(3,cls2Methods[i].getName().length()));					
					else{
						//System.out.println(mpCompare);
						if(compareFlag && mpCompare!=null && mpCompare.size()>0){
							if(mpCompare.containsKey(variableName))
								strResult+="{\""+ variableName +"\":\""+ strVal + "\"},";
							else{
								if(checkInDiscardList(variableName,strVal))
									strResult+="{\""+ variableName +"\":\""+ strVal + "\"},";
							}
						}
						else{
							if(checkInDiscardList(variableName,strVal)){
								strResult+="{\""+ variableName +"\":\""+ strVal + "\"},";
								if(mpCompare!=null && variableName!=null && !mpCompare.containsKey(variableName))
									mpCompare.put(variableName, strVal);
							}
						}
					}
				}
			}
		}
		if(strResult!=null && strResult.length()>0 && strResult.substring(strResult.length()-1,strResult.length()).equals(","))
			strResult= strResult.substring(0, strResult.length()-1 );
		//System.out.println("VariableJSON--"+ strResult);
		//strResult=strResult+ "]";	
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return strResult;
	}
	
	/*function to convert single object to json style to be saved in string  */
	private static String createVariableJSON(Object obj){
		String strResult="";
		try{
			if(obj!=null){
			String className = obj.getClass().getName().replace(".","/");
			strResult=getAllVariableJSONString(obj);
			if(strResult!=null && !strResult.equals(""))
				strResult="\""+ className +"\":[" + strResult+ "]";
			}
			else
				strResult="";
			//System.out.println("inside createVariableJSON---" + strResult);
		}catch(Exception e){
			e.printStackTrace();
		}
		return strResult;
	}
	

	private static boolean checkInDiscardList(String variableName, String strVal){
		boolean flag=true;
		if(strVal!=null && !strVal.equals("")){
			for(int i=0;i<valueToDiscard.length;i++){
				if(valueToDiscard[i].equals(strVal)){
					flag=false;
					break;
				}
			}
		}
		else
			flag=false;
		return flag;		
	}
	/*function to convert object in collection to json style to be saved in string  */
	private static  String getStringForCollectionDataType(Object objField){
		String strResult="";
		if(objField.getClass().equals(java.util.ArrayList.class)){
			List lstData=(List)objField;
			
			for(int j=0;j<lstData.size();j++){
				Object objInList=lstData.get(j);
				String str=getStringForPrimitiveDataType(objInList);
				if(str!=null && !str.equals(""))
					strResult+=str+ ",";
				else
					strResult+=createVariableJSON(objInList)+ ",";
			}
			lstData=null;
			 
		}
		else if(objField.getClass().equals(java.util.HashMap.class)){
			Map mp=(Map)objField;
			Set s=mp.entrySet();
	 		Iterator it=s.iterator();
	 		while(it.hasNext())
	 	    {
	 	       Map.Entry m =(Map.Entry)it.next();
	 	       Object objInMap= m.getValue();
	 	       String str=getStringForPrimitiveDataType(objInMap);
	 	       if(str!=null && !str.equals(""))
	 	    	   strResult+=str+ ",";
	 	       else
	 	    	   strResult+=createVariableJSON(objInMap)+ ",";
	 	    }
		}
		
		if(!strResult.equals(""))
			strResult=strResult.substring(0,strResult.length()-1);
		return strResult;
	   
	}
	
	private static  String getStringForObjectDataType(Object objField , String varName){
		String strResult="";
		String className = objField.getClass().getName().replace(".","/");
		if(objField.getClass().isArray()){
			int arrlength = Array.getLength(objField);
			for(int j=0;j<arrlength;j++){
				Object obj=Array.get(objField, j);
				strResult+=getAllVariableJSONString(obj)+ ",";
			}
		}
		else
			strResult+=getAllVariableJSONString(objField)+ ",";
		
		if(!strResult.equals("")){
			strResult=strResult.substring(0,strResult.length()-1);
			strResult="{\""+varName +"-"+ className +"\":["+strResult+ "]},";
			//System.out.println("className--" + varName + "---strResult--" +strResult);	
		}
		/*else{
			//System.out.println("not making json for className--" + varName + "---strResult--" +strResult);
		}*/
		
		return strResult;
	   
	}
	
	public static AuditLogVO getInitAuditLog( HttpServletRequest request){
		
		AuditLogVO objAuditLogVO=(AuditLogVO) request.getSession().getAttribute("AUDITOBJ");
		return objAuditLogVO;
	}
	
	
	
	
	private static String getProcessedToAuditObject(Map mp) {
		
		
		String strResult="";		
		Set s=mp.entrySet();
	 	Iterator it=s.iterator();
	 	while(it.hasNext())
	 	{
	 		String str="";
	 	    Map.Entry m =(Map.Entry)it.next();
	 	    Object objInMap= m.getValue();
	 	    // checking whether object is primitive datatype
	 	    str=getStringForPrimitiveDataType(objInMap);
 	    	// checking for object  type	 	    
	 	    if(str==null || str.equals("")){

	 	    	str+=createVariableJSON(objInMap);
	 	    }
 	    	// checking for array of object  type	 	   
	 	   if(str==null || str.equals("") && objInMap!=null && objInMap.getClass().isArray()){
	 		   int arrlength = Array.getLength(objInMap);
	 		  // String strArrResult="";
			   for(int i = 0; i < arrlength; ++i){
				   str=createVariableJSON(Array.get(objInMap, i));
				   strResult+="{"+str+ "},";				   
			    }
		    }
	 	   else{
	 		   if(str!=null && !str.equals(""))
	 			   strResult+="{"+str+ "},";	 		 
	 	   }	 	     
	 	}
	 	if(!strResult.equals("")){
			strResult=strResult.substring(0,strResult.length()-1);
			strResult="["+ strResult+ "]";
	 	}
	 	System.out.println("final json--" + strResult);
		return strResult;
	}

	public static void saveInsertAuditLog(Map mp, Object userVO,HttpServletRequest objRequest_p, String[] arrKeyVariables){
		AuditLogVO objAuditLogVO=validateSaveServiceEssensial(1,mp, arrKeyVariables,objRequest_p,userVO);
		if(objAuditLogVO!=null){
			AuditlogServiceCall.SaveAuditLogService(objAuditLogVO);
		}	
	}
	
	
	public static void saveUpdateAuditLog(Map mp, Object userVO,HttpServletRequest objRequest_p, String[] arrKeyVariables){
		AuditLogVO objAuditLogVO=validateSaveServiceEssensial(2,mp, arrKeyVariables,objRequest_p,userVO);
		if(objAuditLogVO!=null){
			AuditlogServiceCall.SaveAuditLogService(objAuditLogVO);
		}	
	}
	
	public static void saveDeleteAuditLog(Map mp, Object userVO,HttpServletRequest objRequest_p, String[] arrKeyVariables){
		AuditLogVO objAuditLogVO=validateSaveServiceEssensial(2,mp, arrKeyVariables,objRequest_p,userVO);
		if(objAuditLogVO!=null){
			AuditlogServiceCall.SaveAuditLogService(objAuditLogVO);
		}	
	}
	

	public static AuditLogVO validateSaveServiceEssensial(int status, Map mp,String[] arrKeyVariablesValue, HttpServletRequest objRequest_p ,Object userVO){
		AuditLogVO objAuditLogVO= new AuditLogVO();
		AuditLogVO objInit=(AuditLogVO) objRequest_p.getSession().getAttribute("AUDITOBJ");
		boolean flag=false;
		if(objInit!=null){
			if (status==1)
				flag=objInit.getIsInsertLogOn()!=0? true:false;
			else{
				if (status==2)
					flag=objInit.getIsUpdateLogOn()!=0? true:false;
				else{
					if (status==0)
						flag=objInit.getIsDeleteLogOn()!=0? true:false;
				}						
			}
				
			if(flag){
				objAuditLogVO.setArrKeyVariablesValue(arrKeyVariablesValue);
				objAuditLogVO.setStrInitVariableJSON(objInit.getStrInitVariableJSON());
				objAuditLogVO.setMpCompare(objInit.getMpCompare());
				objAuditLogVO.setStrModuleId(objInit.getStrModuleId());
				objAuditLogVO.setStrModuleName(objInit.getStrModuleName());
				objAuditLogVO.setStrProcessId(objInit.getStrProcessId() );
				objAuditLogVO.setStrProcessName(objInit.getStrProcessName());
				Usefulmethods.populate(objAuditLogVO, userVO);
				objAuditLogVO.setRecordStatus(""+ status);
				flag=true;	
				if(flag && objAuditLogVO.getStrModuleId()!=null && objAuditLogVO.getStrProcessId()!=null )
					flag=true;			
				else{
					System.out.println("Audit log service prerequisite failed..\n module Id and processId not specified");
					objAuditLogVO=null;
					flag=false;
				}
				
				if(flag && objAuditLogVO.getArrKeyVariablesValue()!=null && objAuditLogVO.getArrKeyVariablesValue().length>0 ){
					flag=true;			
				}
				else{
					System.out.println("Audit log service prerequisite failed..\n key name/ kev value is empty ");
					objAuditLogVO=null;
					flag=false;
				}
				
				if(flag && (status==1 ||status==2) && mp !=null  && mp.size()>0)
					flag=true;			
				else
				{System.out.println("Audit log service prerequisite failed..\n object Map  is null populate this with your save vo objects ");
					objAuditLogVO=null;
					flag=false;
				}
			}
			else{
				System.out.println("Audil log off .. \n reasons\n 1. Audit log object is not initialized \n 2.use audit log process master to set it on");
				flag=false;
				objAuditLogVO=null;
			}
		}
		else{
			System.out.println("Audil log off .. \n reasons\n 1. Audit log object is not initialized \n 2.use audit log process master to set it on");
			flag=false;
			objAuditLogVO=null;
		}
		
		if(flag){
			if(objAuditLogVO.getStrInitVariableJSON()!=null && !objAuditLogVO.getStrInitVariableJSON().equals("")){
				if(objAuditLogVO.getMpCompare()!=null && objAuditLogVO.getMpCompare().size()>0){
					mpCompare= new LinkedHashMap();
					mpCompare.putAll(objAuditLogVO.getMpCompare());
					//System.out.println(mpCompare);
					compareFlag=true;
				}
			}
			if(mp!=null && mp.size()>0){
				objAuditLogVO.setStrVariableJSON(getProcessedToAuditObject(mp));
				compareFlag=false;
			}
			mp=null;
			mpCompare=null;
			mpCompare= new LinkedHashMap<String , String>();
		}
		return objAuditLogVO;
	}
	

	

	
}
