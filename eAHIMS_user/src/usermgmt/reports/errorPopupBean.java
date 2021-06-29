package usermgmt.reports;
import java.io.*;

public class errorPopupBean
{
	public String getContents(String file)
	{
		

		
		file= file.replace('*','\\');
		

		StringBuffer st = new StringBuffer(100);
		try
		{

				File f = new File(file);
				FileInputStream istream = new FileInputStream(f);
				BufferedInputStream br	=	new BufferedInputStream(istream);

				char ch ;
				boolean eof = false;

				while(!eof)
				{
					int i=	br.read();
					if(i==-1)
						eof=true;
					else
					{
						ch =(char)(i);
						st.append(ch);
					}
				}

		}
		catch(Exception e)
		{
			System.out.println("Exception while reading file "+e);
		}
		return st.toString();
	}


}
