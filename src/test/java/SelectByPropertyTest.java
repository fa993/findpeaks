import com.fa993.function.supertype.Either;
import com.fa993.function.utils.SelectByProperty;
import com.fa993.function.variations.LocalMaximaJIU;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectByPropertyTest {

	private LocalMaximaJIU way1 = new LocalMaximaJIU();

	@Test
	public void testForNumber() throws IOException, InterruptedException {
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
		boolean[][] ans = new boolean[iters][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			double[] ptrs = Arrays.stream(TestUtils.getRandomPoints(2)).map(t -> t * 50).toArray();
			outsJIU[i] = way1.localMaxima1D(points)[0];
			ans[i] = SelectByProperty.call(outsJIU[i], Either.OfTwo.first(ptrs[0]), Either.OfTwo.first(ptrs[1]));
			bw.write(Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Double.toString(ptrs[0]));
			bw.newLine();
			bw.write(Double.toString(ptrs[1]));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_select_by_property.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		boolean[][] pyex = new boolean[iters][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i++) {
			String mids = lst.get(i);
			pyex[ind++] = TestUtils.parsePythonArrayToBoolean(mids);
		}
		assertArrayEquals(pyex, ans);
		input.delete();
		output.delete();
	}

	@Test
	public void testForOneOpen() throws IOException, InterruptedException {
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
		boolean[][] ans = new boolean[iters][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			double[] ptrs = Arrays.stream(TestUtils.getRandomPoints(2)).map(t -> t * 50).toArray();
			outsJIU[i] = way1.localMaxima1D(points)[0];
			ans[i] = SelectByProperty.call(outsJIU[i], null, Either.OfTwo.first(ptrs[1]));
			bw.write(Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write(Double.toString(ptrs[1]));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_select_by_property.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		boolean[][] pyex = new boolean[iters][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i++) {
			String mids = lst.get(i);
			pyex[ind++] = TestUtils.parsePythonArrayToBoolean(mids);
		}
		assertArrayEquals(pyex, ans);
		input.delete();
		output.delete();
	}

	@Test
	public void testForOtherOpen() throws IOException, InterruptedException {
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
		boolean[][] ans = new boolean[iters][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			double[] ptrs = Arrays.stream(TestUtils.getRandomPoints(2)).map(t -> t * 50).toArray();
			outsJIU[i] = way1.localMaxima1D(points)[0];
			ans[i] = SelectByProperty.call(outsJIU[i], Either.OfTwo.first(ptrs[0]), null);
			bw.write(Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Double.toString(ptrs[0]));
			bw.newLine();
			bw.write("");
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_select_by_property.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		boolean[][] pyex = new boolean[iters][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i++) {
			String mids = lst.get(i);
			pyex[ind++] = TestUtils.parsePythonArrayToBoolean(mids);
		}
		assertArrayEquals(pyex, ans);
		input.delete();
		output.delete();
	}

}
