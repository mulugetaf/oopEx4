package Ex4;

public class BigThanMedian {
	public static void main(String[] args) {
		System.gc();

		int a[]  =new int[10000];
		int b[] = new int[10000];
		int x= (int)Math.random();
		System.err.println(x);
		for (int i = 0; i < b.length; i++) {
			a[i] =x++;
			b[i] = x++;
		}
		int[] ans2 = bigThanMedianMerge(a, b);
		int[] ans1 = bigThanMedianAlgo(a, b);
		
		//for (int i = 0; i < ans1.length; i++) {
			///System.err.print(ans1[i] + ",");


	//	}
	}

	public static int[] bigThanMedianAlgo(int a[] ,int b[]) {
		int a_size=a.length,b_size = b.length;
		int index=1;
		int m_index = 0;
		int median = 0;
		int merg_arr[] =new int[a_size+b_size]; 
		
		int ans[] = new int[a.length];
		Thread[] threads = new Thread[merg_arr.length/2];
		long final_time=0,start_time=0,end_time=0;

		for (int i = 0; i < threads.length; i++) {
			start_time=System.currentTimeMillis();
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < threads.length; j++) {
						ans[j] = Math.max(a[j], b[b.length-1-j]);
					}
				}
			});
			threads[i].start();
			try {
				threads[i].join();

			} catch (Exception e) {
				// TODO: handle exception
			}
			end_time=System.currentTimeMillis();
			final_time = final_time+(end_time-start_time);

		}
		System.out.println("bigThanMedianAlgo running time:" +final_time+" ms");
		return ans;
	}
	//2//
	public static int[] bigThanMedianMerge(int[]a, int[] b) {
		
		int ans[] = new int[a.length];
		long final_time=0,start_time=0,end_time=0;

		for (int i = 0; i < ans.length; i++) {
			start_time=System.currentTimeMillis();
			ans[i] = Math.max(a[i], b[b.length-1-i]);
			end_time=System.currentTimeMillis();
			final_time = final_time+(end_time-start_time);

		}
		System.out.println("bigThanMedianMerge running time:" +final_time+" ms");
		return ans;
	}

}
