package Ex4;

import java.util.Arrays;

/**
 * @author mulugeta
 * Date - June 16-2019
 *
 this class has two programs that find the median number 
 from the given array and return new array that contain
 only numbers greater than the median.
 the first program use threads for every value  and algorithm 
 the second use simple function to merge both array and find the median number
 */
public class BigThanMedian {


	/**
	 * @param a first array
	 * @param b second array
	 * @return new array that contain all numbers that greeter than median number
	 * both array length must be same.
	 * Median : the median is the value separating the higher half 
	 * from the lower half of a data sample.
	 * For a data set, it may be thought of as the "middle" value.
	 *  For example, in the data set {1, 3, 3, 6, 7, 8, 9}, 
	 *  the median is 6, the fourth largest, and also the fourth smallest, 
	 *  number in the sample. For a continuous probability distribution, 
	 *  the median is the value such that a number is equally likely 
	 *  to fall above or below it.
	 *  for more information https://en.wikipedia.org/wiki/Median
	 */

	public static int[] bigThanMedianAlgo(int a[] ,int b[]) {
		int a_size=a.length,b_size = b.length;
		int merg_arr[] =new int[a_size+b_size]; 

		//array to store numbers greater than median
		int ans[] = new int[a.length];
		long final_time=0,start_time=0,end_time=0;
		//array of threads for every value greater than median
		Thread[] threads = new Thread[merg_arr.length/2];

		for (int i = 0; i < threads.length; i++) {
			start_time=System.currentTimeMillis();
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < threads.length; j++) {
						/*
						 * the max between the last value in the array and the first
						 * value in the current index.
						 * the array is already sorted so max will be the right side index
						 * the point is to do that until we get the medium index
						 */

						ans[j] = Math.max(a[j], b[b.length-1-j]);
					}
				}
			});
			//start single thread
			threads[i].start();
			try {
				//join thread
				threads[i].join();

			} catch (Exception e) {
			}
			end_time=System.currentTimeMillis();
			final_time = final_time+(end_time-start_time);

		}
		//sort the array for tester
		Arrays.sort(ans);
		System.out.println("bigThanMedianAlgo running time:" +final_time+" ms");
		return ans;
	}

	/**
	 * @param a first array
	 * @param b second array
	 * @return new array that contain all numbers that greeter than median number
	 * 
	 * this program  doesn't use any algorithm 
	 * the program merge the given two arrays  to one array and sort  them
	 * and then insert to the new array every number that greater than the median number
	 */
	public static int[] bigThanMedianMerge(int[]a, int[] b) {
		int ans[] = new int[a.length];
		long final_time=0,start_time=System.currentTimeMillis(),end_time=0;
		int median=0,index=1;
		int merg_arr[]=new int[a.length+b.length];
		for(int i=0;i<a.length;i++) {

			merg_arr[i]=a[i];//array1 is copied to new array
		}
		for(int i=0;i<b.length;i++) {
			merg_arr[a.length-1+index]=b[i];//array2 is copied to new array
			index++;
		}
		// -1-   array3 is combined array
		int l=merg_arr.length;
		int temp[]=new int[l];
		//sort the given two arrays as one array
		for(int i=0;i<l;i++) {
			for(int j=i+1;j<l;j++) {
				if(merg_arr[i]>merg_arr[j]) {
					temp[i]=merg_arr[i];
					merg_arr[i]=merg_arr[j];
					merg_arr[j]=temp[i];
				}
			}
		}
		/*
	
		 * there is no need in this program because
		 * the array a and b have the same length
		 * so merge array length is always even
		 */

		if(merg_arr.length%2!=0) {//	 * if the array length is odd
			median = merg_arr[merg_arr.length/2];
		}
		else {
			median = (merg_arr[(merg_arr.length/2)-1]+merg_arr[merg_arr.length/2])/2;
		}
		int j=0;
		for (int i = 0; i < merg_arr.length; i++) {
			if(merg_arr[i]>median) {
				ans[j++] = merg_arr[i];
			}
		}
		
		end_time=System.currentTimeMillis();
		final_time = final_time+(end_time-start_time);
		System.out.println("bigThanMedianMerge running time:" +final_time+" ms");
		return ans;
	}
}
