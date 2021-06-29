package startup;

public class HisException extends RuntimeException
{
	public HisException(){ super("HiS Exception"); }
    public HisException(String _msg){ super(_msg);}
}
