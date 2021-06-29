package hisglobal.masterxml;

import hisglobal.masterxml.masterworkshop.tools.MasterListTO;
import hisglobal.masterxml.masterworkshop.tools.MasterTO;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

public class MastersUTIL
{

	public static void setListPageDefaults(MasterTO _mstTO)
	{
		System.out.println("i m in pagination");
		((MasterListTO) _mstTO).setStartval(0);
		((MasterListTO) _mstTO).setSortOn(1);/////value of sort on changed from 0 to 1   		
		((MasterListTO) _mstTO).setOrder(0);
		((MasterListTO) _mstTO).setSearchOrSort("sort");
		((MasterListTO) _mstTO).setSearchmode(0);
	}

	public static InputStream getResource(ServletContext _context, String _resourcePath)
	{
		URL myURL = null;
		_resourcePath = _resourcePath + ".xml";
		try
		{
			myURL = _context.getResource(_resourcePath);
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			return myURL.openStream();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
