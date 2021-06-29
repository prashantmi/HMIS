package ipd.transactions;

public class testInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test="55Yr";
		String  temp=  test.replace("Yr", "").trim();
		int x= Integer.parseInt(temp);
		System.out.println(" int: "+ x	+ " temp: "+ temp);

	}

}
