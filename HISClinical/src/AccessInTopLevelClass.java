public class AccessInTopLevelClass {
	
	int i=0;
	static int g=0;
	class A{		
		public void ss() throws Exception{
			Thread t= new Thread(){
				int c;
				{	if(c==0)
					throw new Exception();
				}
				
				
				public void run(){
					for(;AccessInTopLevelClass.this.i<3;i++){	
						System.out.println("Thread::::"+Thread.currentThread().getName());
					}					
				}				
			};
			t.start();		
		}		
	}
	public static void main(String aa[]) throws Exception{
		new AccessInTopLevelClass().new A().ss();		
	}   
}
