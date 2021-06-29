package startup;

//import his.global.dao.*;
import java.util.*;
import hisglobal.*;
import hisglobal.utility.Entry;

import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

//import registration.controller.fb.EpisodeActNotAttendedFB;


public class WebUTIL extends HelperMethods{
	
	public static void setMapInRequest(Map _mp, HttpServletRequest request){
		Set stEntry = _mp.entrySet();
		Iterator itr = stEntry.iterator();
		while(itr.hasNext()){
			Map.Entry entry = (Map.Entry)itr.next();
			String strKey = (String)entry.getKey();
			request.setAttribute(strKey, entry.getValue());
		}
	}
	public static void setMapInSession(Map _mp, HttpServletRequest request){
		Set stEntry = _mp.entrySet();
		Iterator itr = stEntry.iterator();
		HttpSession session = request.getSession();
		while(itr.hasNext()){
			Map.Entry entry = (Map.Entry)itr.next();
			String strKey = (String)entry.getKey();
			//String  strval =entry.getValue();
			System.out.println("strKey"+strKey);
		    //System.out.println("strval"+strval);			
			session.setAttribute(strKey, entry.getValue());
		}
	}
	
	public static  Collection removeEntriesfromOptions(Collection  _entryCollection,String[] _entryvalues ){
		
        Collection newEntryCollection=new ArrayList(_entryCollection);
        Collection toRemove=new ArrayList();  
        
        for(int i=0;i<_entryvalues.length;i++)
        {
        	Entry objEntry =new Entry(null,_entryvalues[i]);
        	toRemove.add(objEntry);
        }
        System.out.println("toRemove"+toRemove);
        newEntryCollection.removeAll(toRemove);
        System.out.println("newEntryCollection "+newEntryCollection);
        
        sortEntryCollection(newEntryCollection);
        System.out.println("after sort");
        System.out.println("newEntryCollection after sort"+newEntryCollection);        
		return newEntryCollection;		
	}
	public static void sortEntryCollection(Collection _col)
	{	
		Collections.sort((ArrayList)_col,new Entry.EntryComparator());		
	}
	
	public static String[] removeFromArray(String[] _arrObj, int _idx){
		
		//List lst = Arrays.asList(_arrObj);
		String[] tmp =new String[_arrObj.length-1];	
		
		for(int i=0,j=0;i<_arrObj.length; i++)
		{
			if(i!=_idx){
				tmp[j++]=_arrObj[i];
			}
		}
		return tmp;
	}
	
	public static String[] addNewElementToArray(String[] _arrStr){
		return (String[]) addElementToArray(_arrStr, new String()); 
	}
	//to be made generic
	public static String[] addElementToArray(String[] _arrObj, String _obj){
		
		String[] tmp = new String[_arrObj.length+1];
		System.out.println("dfsdgh");
		for(int i=0;i<_arrObj.length;i++)
		{
			tmp[i]=_arrObj[i];			
		}
		tmp[_arrObj.length]=_obj;		
		System.out.println("dfsdgh");
		return tmp;
	}
	
	public static HttpSession getSession(HttpServletRequest _request){
		HttpSession session=_request.getSession();		
		return session;		
	}
	
	
	}
