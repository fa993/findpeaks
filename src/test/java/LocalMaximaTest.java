import com.fa993.function.variations.LocalMaximaJIU;
import com.fa993.function.variations.LocalMaximaCC;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Random;

/**
 * Test class for LocalMaxima
 */
public class LocalMaximaTest {

	private LocalMaximaJIU way1 = new LocalMaximaJIU();
	private LocalMaximaCC way2 = new LocalMaximaCC();
	private Random rand = new Random();

	private double[] getRandomPoints(int n) {
		double[] arr = new double[n];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextDouble();
		}
		return arr;
	}

	// Run for 1000 times, fail if even 1 is wrong
	@RepeatedTest(value = 1000, failureThreshold = 1)
	void testRandomPeaksForLocalMaxima1000Values() {
		// generate 1000 random values
		double[] points = getRandomPoints(1000);
		int[][] ans1 = way1.localMaxima1D(points);
		int[][] ans2 = way2.localMaxima1D(points);
		assertArrayEquals(ans1, ans2);
	}

}
