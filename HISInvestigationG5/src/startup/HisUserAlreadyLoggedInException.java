package startup;

public class HisUserAlreadyLoggedInException extends HisException{
	
	public HisUserAlreadyLoggedInException(){ super("HIS Data Access Exception"); }
    public HisUserAlreadyLoggedInException(String _msg){ super(_msg);}

}
