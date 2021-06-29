/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import TemplateHelper.vo.ValueObject;
import TemplateHelper.vo.ResultEntryVO;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class DataHandler {

    public static void populateVOfrmRS(ValueObject _vo, ResultSet _rs) {
		// data from object2 is populated into obj1
        // getString of RS is called for a col.
        // setter of VO is called for the corresponding data member...
        // Map keeps the mapping
        try {
            Class cls = _vo.getClass();
            Method[] clsMethods = cls.getMethods();
            // List alMethods = Arrays.asList(clsMethods);

            ResultSetMetaData rsMetaData = _rs.getMetaData();
            int rsCols = rsMetaData.getColumnCount();
            //System.out.println("rsCols" + rsCols);

            if (_rs.isBeforeFirst()) {
                _rs.next();
            }
			//if (_rs.isAfterLast()) throw new HisNoRecordException();

            for (int i = 1; i <= rsCols; i++) {

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
                for (j = 0; j < clsMethods.length; j++) {
                    //System.out.println("clsMethods[i].getName(): "+clsMethods[j].getName()+" strMethodName "+strMethodName);

                    if (clsMethods[j].getName().equalsIgnoreCase(strMethodName)) {// if the method name starts with set
                        clsMethods[j].invoke(_vo, new Object[]{strColVal});
                        break;
                    }

                }
                if (j > clsMethods.length) {
                    throw new Exception("HelperMethods.populateVOfrmRS(): No setter for " + strMethodName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void populate(Object obj1, Object obj2) {
		// data from object2 is populated into obj1
        // getter of object2 is called
        // setter of object1 is called

        Class cls2 = obj2.getClass();

        Method[] cls2Methods = cls2.getMethods();

        HashMap mpGettersInCls2 = new HashMap();
        for (int i = 0; i < cls2Methods.length; i++) {
            if (cls2Methods[i].getName().indexOf("get") == 0) {
                // if the method name starts with set
                mpGettersInCls2.put(cls2Methods[i].getName().substring(3), new Integer(i));
            }
        }

        Class cls1 = obj1.getClass();
        Method[] cls1Methods = cls1.getMethods();

        int i = 0;
        try {
            for (i = 0; i < cls1Methods.length; i++) {
                // System.out.println("cls1Methods[i].getName().indexOf(set): "+cls1Methods[i].getName().indexOf("set"));
                if (cls1Methods[i].getName().indexOf("set") == 0) {
                    // if the method name starts with set
                    if (mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(3))) {
                        //System.out.println("pos2 of " + i);
                        int idx = ((Integer) mpGettersInCls2.get(cls1Methods[i].getName().substring(3))).intValue();
						//System.out.println("pos3 of " + i + " cls1Methods[i].getName():  " + cls1Methods[i].getName()
                        //		+ "  cls2Methods[idx].getName():  " + cls2Methods[idx].getName());
                        Object str = cls2Methods[idx].invoke(obj2, null);
                        // System.out.println("pos4 of "+i +" str: "+ str);
                        cls1Methods[i].invoke(obj1, new Object[]{str});
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        //System.out.println("after populate ");
    }

    
}
