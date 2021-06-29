package startup;

public class HisLoginInsertFailed extends HisException{
	
	public HisLoginInsertFailed(){ super("HIS Data Access Exception"); }
    public HisLoginInsertFailed(String _msg){ super(_msg);}

}
