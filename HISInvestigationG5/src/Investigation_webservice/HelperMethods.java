package Investigation_webservice;


import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HelperMethods {
	private static final String OS_NAME = System.getProperties().getProperty("os.name");

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
			//throw new HisException("Helpermethods.populate::" + e);
		}
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
					if (clsMethods[i].invoke(obj, null) instanceof String)
					{
						String str = (String) clsMethods[i].invoke(obj, null);
						if (str.equals("")) map.put(clsMethods[i].getName().substring(3), cls.toString() + i);

					}// end of if instanceof
					else
					{
						if (clsMethods[i].invoke(obj, null) == null)
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
			//throw new HisException("Helpermethods.populate::" + e);
			
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
		//if (!flag) throw new HisException("populateVOfrmRS:  illegal argument Exception");

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
					//if (j > clsMethods.length) throw new HisException("HelperMethods.populateVOfrmRS(): No setter for " + strMethodName);

				}

				alVO.add(_vo);

		
		}
		catch (Exception e)
		{
			//throw new HisException("Helpermethods.populateVOfrmRS::" + e);
		}

		ValueObject[] arr = new ValueObject[alVO.size()];

		for (int i = 0; i < alVO.size(); i++)
			arr[i] = (ValueObject) alVO.get(i);

		//System.out.println("befor returning arr in populateVOfrmRS(Class _voClass, ResultSet _rs)");
		return arr;
	}

public static void populateVOfrmRS(ValueObject _vo, ResultSet _rs) throws Exception
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
			if (_rs.isAfterLast()) System.out.println("is afterLast Exception HIS required");
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
				if (j > clsMethods.length) throw new Exception("HelperMethods.populateVOfrmRS(): No setter for " + strMethodName);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

}
