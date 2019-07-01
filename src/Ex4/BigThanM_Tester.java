package Ex4;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author mulugeta
 *
 */
public class BigThanM_Tester {
	BigThanMedian test = new BigThanMedian();
	int x= (int)Math.random()*100;

	int a[]  =new int[100000];
	int b[] = new int[100000];

	{
		for (int i = 0; i < b.length; i++) {
			x++;
			a[i] =x;
			b[i] =x;
		}
	}
	/**
	 * test function that equals between two returned array
	 * from two different function
	 * The first use threads and Algorithm to get every numbers that greater then
	 * the median number
	 * The second with out thread and Algorithm
	 * both function supposed to return the same array with same values 
	 */
	@Test
	public void test() {
		int[] actuals = test.bigThanMedianAlgo(a, b);
		int[] expecteds = test.bigThanMedianMerge(a, b);
	assertArrayEquals(expecteds, actuals);
	}
}