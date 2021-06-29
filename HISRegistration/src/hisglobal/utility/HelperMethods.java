package hisglobal.utility;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisNoRecordException;
import hisglobal.vo.ValueObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class HelperMethods
{
	// Static Operating System Name 
	private static final String OS_NAME = System.getProperties().getProperty("os.name");
	private static  ResourceBundle mstRes = null;

	public static void populate(Object obj1, Object obj2)
	{
		// data from object2 is populated into obj1
		// getter of object2 is called
		// setter of object1 is called

		Class cls2 = obj2.getClass();

		Method[] cls2Methods = cls2.getMethods();

		HashMap mpGettersInCls2 = new HashMap();
		for (int i = 0; i < cls2Methods.length; i++)
		{
			if (cls2Methods[i].getName().indexOf("get") == 0)
			{
				// if the method name starts with set
				mpGettersInCls2.put(cls2Methods[i].getName().substring(3), new Integer(i));
			}
		}

		Class cls1 = obj1.getClass();
		Method[] cls1Methods = cls1.getMethods();

		int i=0;
		try
		{
			for (i = 0; i < cls1Methods.length; i++)
			{
				// System.out.println("cls1Methods[i].getName().indexOf(set): "+cls1Methods[i].getName().indexOf("set"));
				if (cls1Methods[i].getName().indexOf("set") == 0)
				{
					// if the method name starts with set
					if (mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(3)))
					{
						//System.out.println("pos2 of " + i);
						int idx = ((Integer) mpGettersInCls2.get(cls1Methods[i].getName().substring(3))).intValue();
						//System.out.println("pos3 of " + i + " cls1Methods[i].getName():  " + cls1Methods[i].getName()
						//		+ "  cls2Methods[idx].getName():  " + cls2Methods[idx].getName());
						Object str = cls2Methods[idx].invoke(obj2, null);
						// System.out.println("pos4 of "+i +" str: "+ str);
						cls1Methods[i].invoke(obj1, new Object[]
						{ str });
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException("Helpermethods.populate::" + e);
		}
		//System.out.println("after populate ");
	}
	
	/**
	 * This method is used to populate an object from JPA (Entity) Object
	 * @param obj1 is any Class Object 
	 * @param obj2 is JPA Entity Object
	 * 
	 * Created By : Aadil
	 * Date		  : Jan 2014
	 */
	public static void populateJPA(Object obj1, Object obj2)
	{
		// data from object2 is populated into obj1
		// getter of object2 is called
		// setter of object1 is called

		Class cls2 = obj2.getClass();

		Method[] cls2Methods = cls2.getMethods();

		HashMap mpGettersInCls2 = new HashMap();
		
		try
		{
			for (int i = 0; i < cls2Methods.length; i++)
			{
				if (cls2Methods[i].getName().indexOf("get") == 0)
				{
					// if the method name starts with set
					if(cls2Methods[i].getName().indexOf("getId") == 0){
						populateJPA(obj1 ,cls2Methods[i].invoke(obj2, null));
						continue;
					}
					mpGettersInCls2.put(cls2Methods[i].getName().substring(6), new Integer(i));
				}
			}

			Class cls1 = obj1.getClass();
			Method[] cls1Methods = cls1.getMethods();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

			int i=0;
			for (i = 0; i < cls1Methods.length; i++)
			{
				// System.out.println("cls1Methods[i].getName().indexOf(set): "+cls1Methods[i].getName().indexOf("set"));
				if (cls1Methods[i].getName().indexOf("set") == 0)
				{
					// if the method name starts with set
					if (mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(6)))
					{
						//System.out.println("pos2 of " + i);
						int idx = ((Integer) mpGettersInCls2.get(cls1Methods[i].getName().substring(6))).intValue();
						//System.out.println("pos3 of " + i + " cls1Methods[i].getName():  " + cls1Methods[i].getName()
							//	+ "  cls2Methods[idx].getName():  " + cls2Methods[idx].getName());
						
						Object str = cls2Methods[idx].invoke(obj2, null);

						 //System.out.println("pos4 of "+i +" str: "+ str);
						
						if(cls2Methods[idx].getReturnType().equals(Integer.TYPE)){
							if(str!=null)
								cls1Methods[i].invoke(obj1, Integer.toString((Integer) str ));
							continue;
						}
						else if(cls2Methods[idx].getReturnType().equals(Long.TYPE)){
							if(str!=null)
								cls1Methods[i].invoke(obj1, Long.toString((Long)str ));
							continue;
						}
						else if(cls2Methods[idx].getReturnType().equals(BigDecimal.class)){
							if(str!=null)
								cls1Methods[i].invoke(obj1, (str.toString() ));
							continue;
						}
						if(cls2Methods[idx].getReturnType().equals(String.class)){
							cls1Methods[i].invoke(obj1, new Object[]{ str });
							continue;
						}
						else if(cls2Methods[idx].getReturnType().equals(Date.class)){
							if(str!=null)
								cls1Methods[i].invoke(obj1, formatter.format((Date)str ));
							continue;
						}
						else
							System.out.println("populateJPA :: else of Return Type check");
						
						
						//cls1Methods[i].invoke(obj1, new Object[]{ str });
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException("Helpermethods.populate::" + e);
		}
		//System.out.println("after populate ");
	}
	
	/**
	 * @param VO or Class (i.e Action Support)
	 * @return
	 * 
	 * Created By : Aadil
	 * Date		  : Jan 2014
	 */
	public static String createJSONObjectString(Object obj)
	{
		// getter of object is called

		Class cls = obj.getClass();

		Method[] clsMethods = cls.getMethods();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strVarName="",strvarValue;
		String strJSONString="";
		String strJSONContentString="";
		try{
			for (int i = 0; i < clsMethods.length; i++)
			{
				if (clsMethods[i].getName().indexOf("get") == 0)
				{
					Object str = clsMethods[i].invoke(obj, null);
					// if the method name starts with set
					String strVarNameTmp= clsMethods[i].getName().substring(3);
					strVarName= Character.toLowerCase(strVarNameTmp.charAt(0))+ strVarNameTmp.substring(1);
					strvarValue="";
					
					if(str!=null && !str.equals("")){
						if(clsMethods[i].getReturnType().equals(Integer.TYPE))
							strvarValue= Integer.toString((Integer) str );
						else if(clsMethods[i].getReturnType().equals(Long.TYPE))
							strvarValue= Long.toString((Long)str );
						else if(clsMethods[i].getReturnType().equals(BigDecimal.class))
							strvarValue=str.toString() ;
						else if(clsMethods[i].getReturnType().equals(String.class))
							strvarValue=(String)str;
						else if(clsMethods[i].getReturnType().equals(Date.class))
							strvarValue= formatter.format((Date)str );
						
						if(strJSONContentString.equals(""))
							strJSONContentString="\""+strVarName+"\":\""+strvarValue+"\"";
						else
							strJSONContentString+=",\""+strVarName+"\":\""+strvarValue+"\"";
					}
				}
			}
			strJSONString="{"+strJSONContentString+"}";
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException("Helpermethods.populate::" + e);
		}
		return strJSONString;
		//System.out.println("after populate ");
	}

	/**
	 * sets Emergency New Patient Registration Essentials.
	 * 
	 * @param _fb EmgNewPatientRegFB form bean
	 * @param _request
	 */
	public static void checkNotNullOrEmpty(Object obj, Map map)
	{
		
		
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("get") == 0)
			{
				//System.out.println("clsMethods[i].getName()"+clsMethods[i].getName());
				//System.out.println("obj"+obj.getClass());
				try
				{
					if(clsMethods[i].getName().equals("getTexts") || clsMethods[i].getName().equals("getText"))
						continue;
					
					//System.out.println("clsMethods[i].getParameterTypes().length :"+clsMethods[i].getParameterTypes().length);
					//System.out.println("(clsMethods[i].invoke(obj, null) :"+ (clsMethods[i].invoke(obj, null)));
					
					//if ((clsMethods[i].getParameterTypes().length==1) && (clsMethods[i].invoke(obj, null) instanceof String))
					// Above Condition is commented by Aadil and below Condition is appended in else also
					if ((clsMethods[i].getParameterTypes().length==0) && (clsMethods[i].invoke(obj, null) instanceof String))
					{
						String str = (String) clsMethods[i].invoke(obj, null);
						if (str.equals("")) map.put(clsMethods[i].getName().substring(3), cls.toString() + i);

					}// end of if instanceof
					else
					{
						if ((clsMethods[i].getParameterTypes().length==0) && (clsMethods[i].invoke(obj, null) == null))
						{
							map.put(clsMethods[i].getName().substring(3), cls.toString() + i);
						}
						/*
						 * else{ if(clsMethods[i].invoke(obj,null) instanceof Object){
						 * if(clsMethods[i].getReturnType().equals(null))
						 * map.put(clsMethods[i].getName().substring(3),cls.toString()+i); } }
						 */
					}
					//System.out.println("value of i"+i);

				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}// end if getname
			
		}// end of for
	}

	public static void populatetToNullOrEmpty(Object obj1, Object obj2)
	{
		// data from object2 is populated into obj1
		// getter of object2 is called
		// setter of object1 is called

		Class cls2 = obj2.getClass();

		Method[] cls2Methods = cls2.getMethods();

		HashMap mpGettersInCls2 = new HashMap();
		for (int i = 0; i < cls2Methods.length; i++)
		{
			if (cls2Methods[i].getName().indexOf("get") == 0)
			{
				// if the method name starts with get
				mpGettersInCls2.put(cls2Methods[i].getName().substring(3), new Integer(i));
			}
		}

		Class cls1 = obj1.getClass();
		Method[] cls1Methods = cls1.getMethods();

		try
		{
			HashMap mpSetterInCls1 = new HashMap();
			checkNotNullOrEmpty(obj1, mpSetterInCls1);
			/*
			 * for(int i=0; i<cls1Methods.length; i++){ if(cls1Methods[i].getName().indexOf("get")==0){
			 * //System.out.println((String)cls1Methods[i].invoke(obj1,null));
			 * 
			 * if(cls1Methods[i].invoke(obj1,null)instanceof String) {String str=(String)cls1Methods[i].invoke(obj1,null);
			 * if(str==null || str==""){ mpSetterInCls1.put(cls1Methods[i].getName().substring(3), new Integer(i)); } } } }
			 */

			for (int i = 0; i < cls1Methods.length; i++)
			{
				// System.out.println("cls1Methods[i].getName().indexOf(set): "+cls1Methods[i].getName().indexOf("set"));
				if (cls1Methods[i].getName().indexOf("set") == 0)
				{
					// if the method name starts with set
					if (mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(3))
							&& (mpSetterInCls1.containsKey(cls1Methods[i].getName().substring(3))))
					{
						//System.out.println("pos2 of " + i);
						int idx = ((Integer) mpGettersInCls2.get(cls1Methods[i].getName().substring(3))).intValue();
						//System.out.println("pos3 of " + i + " cls1Methods[i].getName():  " + cls1Methods[i].getName()
						//		+ "  cls2Methods[idx].getName():  " + cls2Methods[idx].getName());
						Object str = cls2Methods[idx].invoke(obj2, null);

						// System.out.println("pos4 of "+i +" str: "+ str);
						cls1Methods[i].invoke(obj1, new Object[]
						{ str });
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException("Helpermethods.populate::" + e);
			
		}
		//System.out.println("after populate ");
	}

	public static void populateVOfrmRS(ValueObject _vo, ResultSet _rs)
	{
		// data from object2 is populated into obj1
		// getString of RS is called for a col.
		// setter of VO is called for the corresponding data member...
		// Map keeps the mapping
		try
		{
			Class cls = _vo.getClass();
			Method[] clsMethods = cls.getMethods();
			// List alMethods = Arrays.asList(clsMethods);

			ResultSetMetaData rsMetaData = _rs.getMetaData();
			int rsCols = rsMetaData.getColumnCount();
			//System.out.println("rsCols" + rsCols);

			if (_rs.isBeforeFirst()) _rs.next();
			if (_rs.isAfterLast()) throw new HisNoRecordException();
			// while(_rs.next())

			for (int i = 1; i <= rsCols; i++)
			{

				String strColLabel = rsMetaData.getColumnLabel(i);
				//System.out.println("strColLabel:  " + strColLabel);

				String strColVal = _rs.getString(rsMetaData.getColumnName(i));
				//System.out.println("strColVal:  " + strColVal);
				char[] arrCh = strColLabel.toCharArray();
				arrCh[0] = Character.toUpperCase(arrCh[0]);
				//System.out.println("strColLabel:  " + strColLabel);

				String strMethodName = new String(arrCh);
				strMethodName = "set" + strMethodName;
				//System.out.println("strMethodName:  " + strMethodName);

				int j;
				for (j = 0; j < clsMethods.length; j++)
				{
					 //System.out.println("clsMethods[i].getName(): "+clsMethods[j].getName()+" strMethodName "+strMethodName);

					if (clsMethods[j].getName().equalsIgnoreCase(strMethodName))
					{// if the method name starts with set
						clsMethods[j].invoke(_vo, new Object[]
						{ strColVal });
						break;
					}

				}
				if (j > clsMethods.length) throw new HisException("HelperMethods.populateVOfrmRS(): No setter for " + strMethodName);
			}
		}
		catch (HisException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new HisException("Helpermethods.populateVOfrmRS::" + e);
		}
	}

	public static ValueObject[] populateVOfrmRS(Class _voClass, ResultSet _rs)
	{
		// data from object2 is populated into obj1
		// getString of RS is called for a col.
		// setter of VO is called for the corresponding data member...
		// Map keeps the mapping
		//System.out.println("Class _voClass :: " + _voClass);
		boolean flag=false;
		Class clsTemp = _voClass;
		while(clsTemp != Object.class)
		{
			if(clsTemp.getSuperclass() == ValueObject.class)
			{
				flag=true;
				break;
			}
			clsTemp = clsTemp.getSuperclass();
		}
		//if (_voClass.getSuperclass() != ValueObject.class) throw new HisException("populateVOfrmRS:  illegal argument Exception");
		if (!flag) throw new HisException("populateVOfrmRS:  illegal argument Exception");

		ArrayList alVO = new ArrayList();
		try
		{
			Class cls = _voClass;
			Method[] clsMethods = cls.getMethods();
			// List alMethods = Arrays.asList(clsMethods);

			ResultSetMetaData rsMetaData = _rs.getMetaData();
			int rsCols = rsMetaData.getColumnCount();
			/*
			 * int y=0; for(int m=0;_rs.next();m++){ y++; } System.out.println("rows returned by rs....y ::"+y);
			 */
			//System.out.println("rsCols" + rsCols);

			for (int rsCounter = 0; _rs.next(); rsCounter++)
			{
				ValueObject _vo = (ValueObject) cls.newInstance();
				for (int i = 1; i <= rsCols; i++)
				{

					String strColLabel = rsMetaData.getColumnLabel(i);
					//System.out.println("strColLabel:  " + strColLabel);

					String strColVal = _rs.getString(rsMetaData.getColumnName(i));
					//System.out.println("strColVal:  " + strColVal);
					char[] arrCh = strColLabel.toCharArray();
					arrCh[0] = Character.toUpperCase(arrCh[0]);
					//System.out.println("strColLabel:  " + strColLabel);

					String strMethodName = new String(arrCh);
					strMethodName = "set" + strMethodName;
					//System.out.println("strMethodName:  " + strMethodName);

					int j;
					for (j = 0; j < clsMethods.length; j++)
					{
						// System.out.println("clsMethods[j].getName(): "+clsMethods[j].getName()+" strMethodName:
						// "+strMethodName);

						if (clsMethods[j].getName().equalsIgnoreCase(strMethodName))
						{// if the method name starts with set
							clsMethods[j].invoke(_vo, new Object[]
							{ strColVal });

							break;
						}

					}
					if (j > clsMethods.length) throw new HisException("HelperMethods.populateVOfrmRS(): No setter for " + strMethodName);

				}

				alVO.add(_vo);

			}
		}
		catch (Exception e)
		{
			throw new HisException("Helpermethods.populateVOfrmRS::" + e);
		}

		ValueObject[] arr = new ValueObject[alVO.size()];

		for (int i = 0; i < alVO.size(); i++)
			arr[i] = (ValueObject) alVO.get(i);

		//System.out.println("befor returning arr in populateVOfrmRS(Class _voClass, ResultSet _rs)");
		return arr;
	}

	public static String getSysdate(java.util.Date _dt, String _pattern)
	{
		String strDate = "";
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getInstance();
		// df.applyPattern("dd/MM/yyyy hh:mm:ss a");
		df.applyPattern(_pattern);
		strDate = df.format(_dt);
		//System.out.println("formatedDate" + strDate);
		return strDate;
	}

	public static String storeImageInCorrectFileSystem(byte[] _imageArr, String _fileName, String _newFileName, String _windowsPath, String _linuxPath)
			throws FileNotFoundException, IOException
	{
		//System.out.println("Entered in Image to Correct File System Writing ........................");

		FileOutputStream fos = null;

		String storagePath = "";
		String filePath = "";

		if (OS_NAME.startsWith("Linux"))
		{
			storagePath = "/root" + _linuxPath; // Linux Path should start from / forward slash
			filePath = storagePath + "/" + _newFileName;
		}
		else
		{
			storagePath = _windowsPath; // Windows Path should start from drive name e.g C:\ABC etc.
			filePath = storagePath + "\\" + _newFileName;
		}

		File dir = new File(storagePath);
		File file = new File(dir, _newFileName);
		if (!dir.exists()) dir.mkdir();
		if (dir.exists())
		{
			fos = new FileOutputStream(file);
			fos.write(_imageArr);
		//	System.out.println("After Writing to File........................");
		}

		try
		{
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception while closing");
		}
		return filePath;
	}

	public static String storeImageInFileSystem(byte[] _imageArr, String _fileName, String _newFileName, String _storagePath)
			throws FileNotFoundException, IOException
	{
		//System.out.println("inside storeImageInFileSystem");
		FileOutputStream fos = null;
		int idx = _fileName.lastIndexOf(".");
		String ext = _fileName.substring(idx);
		File file = new File(_storagePath);
		String filePath = _storagePath + "\\" + _newFileName;
		if (!file.exists())
		{
			if (file.mkdir())
			{
				File file1 = new File(_storagePath + "\\" + _newFileName);
				fos = new FileOutputStream(file1);
				fos.write(_imageArr);
				//System.out.println("after writing to file first time");
			}
		}
		else
		{
			File file1 = new File(_storagePath + "\\" + _newFileName);
			fos = new FileOutputStream(file1);
			fos.write(_imageArr);
			//System.out.println("after writing to file");
		}
		try
		{
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception while closing");
		}
		return filePath;
	}// end of method processimage

	public static String storeHTMLFileSystem(String _fileArray, String _fileName, String _newFileName, String _storagePath)
			throws FileNotFoundException, IOException
	{
		//System.out.println("inside storeImageInFileSystem");
		FileOutputStream fos = null;
		int idx = _fileName.lastIndexOf(".");
		String ext = _fileName.substring(idx);
		File file = new File(_storagePath);
		byte[] byteArray = _fileArray.getBytes();
		String filePath = _storagePath + "\\" + _newFileName;
		if (!file.exists())
		{
			if (file.mkdir())
			{
				File file1 = new File(_storagePath + "\\" + _newFileName);
				fos = new FileOutputStream(file1);
				fos.write(byteArray);
				//System.out.println("after writing to file first time");
			}
		}
		else
		{
			File file1 = new File(_storagePath + "\\" + _newFileName);
			fos = new FileOutputStream(file1);
			fos.write(byteArray);
			//System.out.println("after writing to file");
		}
		try
		{
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception while closing");
		}
		return filePath;
	}// end of method processimage

	public static byte[] getByteArrayOfImage(String _fileNameWithPath) throws IOException, FileNotFoundException
	{
		byte[] readBytes = null;
		try
		{
			FileInputStream fos = new FileInputStream(_fileNameWithPath);
			readBytes = new byte[fos.available()];
			fos.read(readBytes);

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			// throw new HisRecordNotFoundException("No Specified Image Record Found");
		}
		return readBytes;
	}/*

	public static void populateParameteresForReport(Map _parameterMap, ReportVO _reportVO)
	{

		Class reportVOClass = _reportVO.getClass();
		Method[] methodsReportVO = reportVOClass.getMethods();
		try
		{
			for (int i = 0; i < methodsReportVO.length; i++)
			{
				String methodName = methodsReportVO[i].getName();
				String keyName = methodName.substring(3);
				String k = keyName.substring(1);
				String fistChar = keyName.substring(0, 1).toLowerCase();
				String finalKey = fistChar + k;
				if (methodName.startsWith("get"))
				{
					Object methodValue = methodsReportVO[i].invoke(_reportVO, null);
					if (methodValue instanceof String)
					{
						String Value = (String) methodValue;
						_parameterMap.put(finalKey, Value);
					}
				}
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException();

		}

	}
*/
	/*
	 * public static List getOpdMenuDtl(HttpServletRequest request){ HttpSession session=request.getSession(); List
	 * list=(List) session.getAttribute(OpdConfig.OPD_DESK_MENU_DTL); OpdEssentialDelegate opdEssentialDelegate=new
	 * OpdEssentialDelegate(); if(list==null){ list=opdEssentialDelegate.getOpdMenuDetail(ControllerUTIL.getUserVO(request));
	 * session.setAttribute(OpdConfig.OPD_DESK_MENU_DTL,list); } return list; }
	 */
	public static String getFileName(char seprator, String[] collecOfNameParts)
	{
		String fileName = "";
		for (int i = 0; i < collecOfNameParts.length; i++)
		{
			fileName = fileName + collecOfNameParts[i];
			if (i < (collecOfNameParts.length - 1))
			{
				fileName = fileName + seprator;
			}
		}
		return fileName;
	}

	public static HashMap createQueryMapFromVO(Object _classVOObject)
	{
		HashMap queryMap = new HashMap();
		Class classVO = _classVOObject.getClass();
		Method[] classVOMethod = classVO.getMethods();

		for (int i = 0; i < classVOMethod.length; i++)
		{
			String methodName = classVOMethod[i].getName();
			Object methodValue = "";

			String mapKey = "";
			if (methodName.indexOf("get") == 0)
			{
				try
				{
					methodValue = classVOMethod[i].invoke(_classVOObject, null);
					boolean fl = methodValue instanceof String;

				}
				catch (IllegalArgumentException e)
				{

					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{

					e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{

					e.printStackTrace();
				}
				if ((methodValue instanceof String) && (methodValue != null && !methodValue.equals("") && !methodValue.equals("-1")))
				{
					mapKey = methodName.substring(3);
					mapKey = mapKey.toLowerCase();
					queryMap.put(mapKey, methodValue);

				}

			}
		}

		return queryMap;
	}
	
	
	
	/**
	 * Set Null to empty
	 * @param obj
	 * @param map
	 */
	public static void setNullToEmpty(Object obj)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{""};
		List <String> methodList=new ArrayList<String>();
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("get") == 0)
			{
				try
				{
					if (clsMethods[i].invoke(obj, null) == null)
					{
						Class returnType=clsMethods[i].getReturnType();
						//System.out.println(clsMethods[i].getDeclaringClass());
						if (returnType==String.class && clsMethods[i].getDeclaringClass()==cls) {
							setEmpty(obj, clsMethods[i].getName().substring(3));
						}	
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}// end if getname
		}// end of for
		
	}
	
	
	/**
	 * Set Null to empty
	 * @param obj
	 * @param map
	 */
	public static void setEmptyToNull(Object obj)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{""};
		List <String> methodList=new ArrayList<String>();
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("get") == 0)
			{
				try
				{
					if (clsMethods[i].invoke(obj, null) == "")
					{
						Class returnType=clsMethods[i].getReturnType();
						//System.out.println(clsMethods[i].getDeclaringClass());
						if (returnType==String.class && clsMethods[i].getDeclaringClass()==cls) {
							setNull(obj, clsMethods[i].getName().substring(3));
						}	
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}// end if getname
		}// end of for
		
	}
	
	public static void setNull(Object obj,String methodName)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{null};
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("set") == 0)
			{
				try
				{
					//System.out.println("Method Name : " + clsMethods[i].getName());
					if (clsMethods[i].getName().substring(3).equalsIgnoreCase(methodName))
					{
						clsMethods[i].invoke(obj, arg);
						
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}// end for
		
	}	
	public static void setEmpty(Object obj,String methodName)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{""};
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("set") == 0)
			{
				try
				{
					//System.out.println("Method Name : " + clsMethods[i].getName());
					if (clsMethods[i].getName().substring(3).equalsIgnoreCase(methodName))
					{
						clsMethods[i].invoke(obj, arg);
						
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}// end for
		
	}

	
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
	

	
	public static String getQuery(int index,String qryIndex,String mstQuery) throws Exception
	{

		String qry = null;
		try
		{
			
					 mstRes = ResourceBundle.getBundle(mstQuery);
					 qry = mstRes.getString(qryIndex);
					//System.out.println("Masters 1 "+qry);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisException("Helpermethods.getQuery::" + e);
		}
		if(qry==null)
			throw new HisException("Helpermethods.getQuery:: Lavels not upload");

		return qry;
	}

	
	
}
