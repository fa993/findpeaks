import com.fa993.function.FindPeaks;
import com.fa993.function.FindPeaksOutput;
import com.fa993.function.supertype.NumOrTwoSeqOrNdArr;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindPeaksTest {

	private FindPeaks fp = new FindPeaks();

	@Test
	public void testBasic() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] outs = new FindPeaksOutput[iters];
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			outs[i] = fp.call(points);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, null);
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(outs, overloadOuts);
		assertArrayEquals(overloadOuts, pyex);
	}

	@Test
	public void testWithHeight() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double rndH = TestUtils.getRandomPoints(1)[0];
			double[] points = TestUtils.getRandomPoints(1000);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.first(rndH), null, null, null, null, null, null, null);
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Double.toString(rndH));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(overloadOuts, pyex);
	}

	@Test
	public void testWithHeightUpperLower() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double[] rndH = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(1000);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.second(rndH), null, null, null, null, null, null, null);
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(overloadOuts, pyex);
	}

	@Test
	public void testWithHeightsAll() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double[] rndH = TestUtils.getRandomPoints(2 * 1000);
			double[] points = TestUtils.getRandomPoints(1000);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.third(TestUtils.reshape(rndH, 2)), null, null, null, null, null, null, null);
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(overloadOuts, pyex);
	}

	@Test
	public void testWithPlateau() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double rndH = TestUtils.getRandomPoints(1)[0];
			double[] points = TestUtils.getRandomPoints(1000);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, NumOrTwoSeqOrNdArr.first(rndH));
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write(Double.toString(rndH));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(overloadOuts, pyex);
	}

	@Test
	public void testWithPlateauUpperLower() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double[] rndH = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(1000);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, NumOrTwoSeqOrNdArr.second(rndH));
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write(Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(overloadOuts, pyex);
	}

	@Test
	public void testWithPlateauAll() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double[] rndH = TestUtils.getRandomPoints(2 * 1000);
			double[] points = TestUtils.getRandomPoints(1000);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, NumOrTwoSeqOrNdArr.third(TestUtils.reshape(rndH, 2)));
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write(Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(overloadOuts, pyex);
	}

	@Test
	public void testWithHeightAndPlateau() throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		input.delete();
		input.createNewFile();
//		input.deleteOnExit();
//		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		int iters = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		for(int i = 0; i < iters; i++) {
			double[] rndH = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(1000);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.first(rndH[0]), null, null, null, null, null, null, NumOrTwoSeqOrNdArr.first(rndH[1]));
			bw.write(Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			bw.newLine();
			bw.write(Double.toString(rndH[0]));
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write("");
			bw.newLine();
			bw.write(Double.toString(rndH[1]));
			bw.newLine();
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/test_find_peaks.sh");
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		FindPeaksOutput[] pyex = new FindPeaksOutput[iters];
		int ind = 0;
		int current = 0;
		while (current < lst.size()){
			int lC = Integer.parseInt(lst.get(current).trim());
			pyex[ind++] = TestUtils.parseFindPeaksOutputFromLines(lst.subList(current + 1, current + lC + 1));
			current += lC + 1;
		}
		assertArrayEquals(overloadOuts, pyex);
	}

}
