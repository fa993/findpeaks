import com.fa993.types.LocalMaximaOutput;
import com.fa993.variations.LocalMaximaJIU;
import com.fa993.variations.LocalMaximaCC;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test class for LocalMaxima
 */
public class LocalMaximaTest {

	private final LocalMaximaJIU way1 = new LocalMaximaJIU();
	private final LocalMaximaCC way2 = new LocalMaximaCC();

	// Run for 1000 times, fail if even 1 is wrong
	@RepeatedTest(value = 1000, failureThreshold = 1)
	void testRandomPeaksForLocalMaxima1000Values() {
		// generate 1000 random values
		double[] points = TestUtils.getRandomPoints(1000);
		LocalMaximaOutput ans1 = way1.localMaxima1D(points);
		LocalMaximaOutput ans2 = way2.localMaxima1D(points);
		assertEquals(ans1, ans2);
	}

	@Test
	void testPythonLocalMaximaWithJava() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		LocalMaximaOutput[] outsJIU = new LocalMaximaOutput[iters];
		LocalMaximaOutput[] outsCC = new LocalMaximaOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_maxima.sh", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			outsJIU[i] = way1.localMaxima1D(points);
			outsCC[i] = way2.localMaxima1D(points);
			return List.of(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
		});
		LocalMaximaOutput[] pyex = new LocalMaximaOutput[iters];
		int ind = 0;
		for(int i = 0; i < lst.size(); i+=3) {
			String mids = lst.get(i);
			String lefts = lst.get(i + 1);
			String rights = lst.get(i + 2);
			pyex[ind++] = new LocalMaximaOutput(
				Arrays.stream(TestUtils.parsePythonArrayToInteger(mids)).mapToInt(y -> y).toArray(), Arrays.stream(TestUtils.parsePythonArrayToInteger(lefts)).mapToInt(y -> y).toArray(), Arrays.stream(TestUtils.parsePythonArrayToInteger(rights)).mapToInt(y -> y).toArray()
			);
		}
		assertArrayEquals(outsCC, outsJIU);
		assertArrayEquals(pyex, outsCC);

	}



}
