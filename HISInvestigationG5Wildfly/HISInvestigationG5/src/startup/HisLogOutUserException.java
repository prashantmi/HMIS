package startup;

public class HisLogOutUserException extends HisException{
	
	public HisLogOutUserException(){ super("HIS Data Access Exception"); }
    public HisLogOutUserException(String _msg){ super(_msg);}

}