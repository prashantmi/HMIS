package startup;

public class HisInvalidUserNameException extends HisException{
	
	public HisInvalidUserNameException(){ super("HIS Invalid UserName/Password Exception"); }
    public HisInvalidUserNameException(String _msg){ super(_msg);}

}
