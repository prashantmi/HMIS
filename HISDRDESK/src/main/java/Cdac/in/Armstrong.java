package Cdac.in;

public class Armstrong {
	
	public static void main(String[] args) {
		
		int num=1531, originalnum=0 , result =0 , remandert=0;
		
		originalnum=num;
		while(num!=0)
		{
			remandert=num%10;
			result = (int) (result+ Math.pow(remandert, 3));
			num= num/10;
			
		}
		System.out.println();
		if(result == originalnum)
			System.out.println("Numbers are AramStrong Number");
		else
			System.out.println("Numbers not Arastrong number");
		
	}

}
