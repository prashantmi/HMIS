package startup;

public class HisRegisterUserException extends HisException{
	
	public HisRegisterUserException(){ super("HIS Data Access Exception"); }
    public HisRegisterUserException(String _msg){ super(_msg);}

}