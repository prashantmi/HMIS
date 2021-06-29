package hisglobal.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import sun.net.ftp.FtpClient;

public class FTPHelperMethods
{
	public static void saveAudioVideoFileInFTP(byte[] _arr, String _fileName) throws FileNotFoundException, IOException
	{
		try
		{
			String server = "10.103.0.20";
			String user = "administrator";
			String passwd = "hisregistration";
			//String fileName = "leave office.mpg";
			byte[] b = _arr;

			FtpClient ftpClient = new FtpClient();
			ftpClient.openServer(server);
			ftpClient.login(user, passwd);
			ftpClient.binary();
			ftpClient.cd("dir");

			//	FileOutputStream fos= new FileOutputStream("out.txt");
			//	fos.write(b);
			//	fos.close();

			OutputStream netOut = ftpClient.put(_fileName);

			FileInputStream fileIn = new FileInputStream("out.txt");

			int read = 0;

			while ((read = fileIn.read()) != -1)
			{
				netOut.write(read);
			}
			fileIn.close();
			netOut.close();
			ftpClient.closeServer();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
