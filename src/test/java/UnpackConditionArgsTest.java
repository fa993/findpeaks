import com.fa993.function.supertype.NumOrTwoSeqOrNdArr;
import com.fa993.function.supertype.PairOfDoubleOrDArr;
import com.fa993.function.utils.UnpackConditionArgs;
import com.fa993.function.variations.LocalMaximaJIU;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnpackConditionArgsTest {

	private LocalMaximaJIU way1 = new LocalMaximaJIU();

	@Test
	public void testAgainstPythonForNumber() throws IOException, InterruptedException {
		int iters = 1000;
		int[][] outsJIU = new int[iters][];
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		output.delete();
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		Double[][] ans = new Double[iters][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			double ptr = (TestUtils.getRandomPoints(1)[0] * 50);
			outsJIU[i] = way1.localMaxima1D(points)[0];
			PairOfDoubleOrDArr out = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.first(ptr), points, outsJIU[i]);
			ans[i] = new Double []{out.getFirst().getFirst(), out.getSecond() == null ? null : out.getSecond().getFirst()};
			bw.write(Double.toString(ptr));
			bw.newLine();
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_unpack_condition_args.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		Double[][] pyex = new Double[iters][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i++) {
			String mids = lst.get(i);
			pyex[ind++] = TestUtils.parsePythonArrayToDouble(mids);
		}
		assertArrayEquals(pyex, ans);
		input.delete();
		output.delete();
	}

	@Test
	public void testAgainstPythonForTwoSeq() throws IOException, InterruptedException {
		int iters = 1000;
		int[][] outsJIU = new int[iters][];
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		output.delete();
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		Double[][] ans = new Double[iters][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			double[] ptrs = Arrays.stream(TestUtils.getRandomPoints(2)).map(t -> t * 50).toArray();
			outsJIU[i] = way1.localMaxima1D(points)[0];
			PairOfDoubleOrDArr out = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.second(ptrs), points, outsJIU[i]);
			ans[i] = new Double []{out.getFirst().getFirst(), out.getSecond() == null ? null : out.getSecond().getFirst()};
			bw.write(Arrays.stream(ptrs).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_unpack_condition_args.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		Double[][] pyex = new Double[iters][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i++) {
			String mids = lst.get(i);
			pyex[ind++] = TestUtils.parsePythonArrayToDouble(mids);
		}
		assertArrayEquals(pyex, ans);
		output.delete();
		input.delete();
	}

	@Test
	public void testAgainstPythonForFull() throws IOException, InterruptedException {
		int iters = 1000;
		int[][] outsJIU = new int[iters][];
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		output.delete();
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		double[][][] ans = new double[iters][][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			double[] ptrs = Arrays.stream(TestUtils.getRandomPoints(points.length * 2)).map(t -> t * 50).toArray();
			double[][] pttrs = TestUtils.reshape(ptrs, 2);
			outsJIU[i] = way1.localMaxima1D(points)[0];
			PairOfDoubleOrDArr out = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.third(pttrs), points, outsJIU[i]);
			ans[i] = new double [][]{out.getFirst().getSecond(), out.getSecond().getSecond()};
			bw.write(Arrays.stream(ptrs).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_unpack_condition_args.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		double[][][] pyex = new double[iters][][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i+=2) {
			String mids = lst.get(i);
			String nexts = lst.get(i + 1);
			pyex[ind++] = new double[][] {Arrays.stream(TestUtils.parsePythonArrayToDouble(mids)).mapToDouble(t -> t).toArray(), Arrays.stream(TestUtils.parsePythonArrayToDouble(nexts)).mapToDouble(t -> t).toArray()};
		}
		assertArrayEquals(pyex, ans);
		output.delete();
		input.delete();
	}
}
