package opd.audioVideoPlayer;


import java.io.*;

import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.PullSourceStream;
import javax.media.protocol.SourceStream;

public class FTPSourceStream implements PullSourceStream
{
protected InputStream dataIn;
protected boolean eofMarker;
protected ContentDescriptor cd;
public FTPSourceStream(InputStream in)
{
this.dataIn = in;
eofMarker = false;
cd = new ContentDescriptor("unknown");
}
// SourceSteam methods
public ContentDescriptor getContentDescriptor()
{
return cd;
}
public void close() throws IOException
{
dataIn.close();
}
public boolean endOfStream()
{
return eofMarker;
}
// PullSourceStream methods
public int available() throws IOException
{
return dataIn.available();
}
public int read(byte[] buffer, int offset, int length) throws
IOException
{
int n = dataIn.read(buffer, offset, length);
if (n == -1)
{
eofMarker = true;
}
return n;
}

public long getContentLength()
{
return SourceStream.LENGTH_UNKNOWN;
}
public Object getControl(String arg0) {
	// TODO Auto-generated method stub
	return null;
}
public Object[] getControls() {
	// TODO Auto-generated method stub
	return null;
}
public boolean willReadBlock() {
	// TODO Auto-generated method stub
	return false;
}
}
