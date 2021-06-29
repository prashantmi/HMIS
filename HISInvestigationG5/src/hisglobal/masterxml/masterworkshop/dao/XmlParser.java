package hisglobal.masterxml.masterworkshop.dao;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XmlParser
{

	public static Document getDomTree(InputStream _xmlFile)
	{
		Document doc = null;

		try
		{
			//System.out.println("inside get dom tree");
			//System.out.println("file name" + _xmlFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(_xmlFile);
		}

		catch (Exception e)
		{
			System.out.println("Exception while parsing::" + e);
		}
		return doc;
	}
}
