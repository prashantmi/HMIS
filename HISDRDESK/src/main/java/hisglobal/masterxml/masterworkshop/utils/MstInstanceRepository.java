package hisglobal.masterxml.masterworkshop.utils;

import java.io.InputStream;
import java.util.*;

/**
 * @author Administrator
 */
public class MstInstanceRepository
{

	public static Map instanceMap = new HashMap();

	public static MasterBuilder getMasterInstace(String _mstName, InputStream _is)
	{

		//System.out.println(" inside getMastersnstance");
		MasterBuilder objMstBuilder = null;
		try
		{
			Set st = instanceMap.keySet();
			Iterator it = st.iterator();
			while (it.hasNext())
			{
				System.out.println("instance map keys" + (String) it.next());
			}
			System.out.println("_mstName" + _mstName);

			//System.out.println("object against mstname"+(String)instanceMap.get(_mstName.trim()));		 
			System.out.println("contains key:::" + instanceMap.containsKey(_mstName.trim()));
			if (!instanceMap.containsKey(_mstName.trim()))
			{
				//System.out.println("Masters instance not present");
				//System.out.println("master name" + _mstName);

				objMstBuilder = MasterBuilder.getInstance(_is);
				instanceMap.put(_mstName.trim(), objMstBuilder);
				return objMstBuilder;
			}
			//if(objMasterBuilder==null)
			else
			{
				//System.out.println("inside else of getMasterInstace");
				objMstBuilder = (MasterBuilder) instanceMap.get(_mstName.trim());

				//System.out.println("objMstBuilder" + objMstBuilder);
				return objMstBuilder;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception:: in getMasterInstace" + e);
			e.printStackTrace();
		}
		return objMstBuilder;
	}

}
