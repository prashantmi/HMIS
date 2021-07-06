package Cdac.in;

public class PalidromeNUmber {

		public static void main(String[] args) {
			
			int num =121 , orginalnum=0 , remender=0 , result=0;
			
			orginalnum=num;
			while (num!=0)
			{
				remender=num%10;
				result = (result*10)+ remender;
				num=num/10;
			}
			if(orginalnum == result)
			{
				System.out.println("Yes");
			}else{
				System.out.println("No");
			}
			
		}
}
