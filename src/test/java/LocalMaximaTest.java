import com.fa993.function.variations.LocalMaximaJIU;
import com.fa993.function.variations.LocalMaximaCC;
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

	private LocalMaximaJIU way1 = new LocalMaximaJIU();
	private LocalMaximaCC way2 = new LocalMaximaCC();

	// Run for 1000 times, fail if even 1 is wrong
	@RepeatedTest(value = 1000, failureThreshold = 1)
	void testRandomPeaksForLocalMaxima1000Values() {
		// generate 1000 random values
		double[] points = TestUtils.getRandomPoints(1000);
		int[][] ans1 = way1.localMaxima1D(points);
		int[][] ans2 = way2.localMaxima1D(points);
		assertArrayEquals(ans1, ans2);
	}

	@Test
	void testPythonLocalMaximaWithJava() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		int[][][] outsJIU = new int[iters][][];
		int[][][] outsCC = new int[iters][][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			outsJIU[i] = way1.localMaxima1D(points);
			outsCC[i] = way2.localMaxima1D(points);
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_maxima.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		int[][][] pyex = new int[iters][][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i+=3) {
			String mids = lst.get(i);
			String lefts = lst.get(i + 1);
			String rights = lst.get(i + 2);
			pyex[ind++] = new int[][] {
				Arrays.stream(TestUtils.parsePythonArray(mids)).mapToInt(y -> y).toArray(), Arrays.stream(TestUtils.parsePythonArray(lefts)).mapToInt(y -> y).toArray(), Arrays.stream(TestUtils.parsePythonArray(rights)).mapToInt(y -> y).toArray()
			};
		}
		assertArrayEquals(outsCC, outsJIU);
		assertArrayEquals(pyex, outsCC);

	}



}
