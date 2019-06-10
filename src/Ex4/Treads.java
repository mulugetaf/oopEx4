package Ex4;
//https://stackoverflow.com/questions/11164383/combining-and-sorting-two-arrays-java
public class Treads implements Runnable{
	static int a , b;
	static int ans;
	int id;
	Treads(int a , int b ,int i){
		this.a = a;
		this.b = b;
		id =i;
		ans=0;
	}
	@Override
	public void run() {
		System.out.println("Thread id = "+id+" started!");
		m_ans(a, b);	
	}
	public static int getAns() {
		return ans;
	}
	public static void setAns(int ans) {
		Treads.ans = ans;
	}
	public int m_ans(int a, int b) {
		ans =Math.max(a, b);
		return ans;
	}
	
}
