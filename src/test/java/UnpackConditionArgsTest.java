import com.fa993.function.FindPeaks;
import com.fa993.function.supertype.Either;
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
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		Integer[][] ans = new Integer[iters][];
		for(int i = 0; i < iters; i++) {
			double[] points = TestUtils.getRandomPoints(1000);
			int ptr = (int) (TestUtils.getRandomPoints(1)[0] * 50);
			outsJIU[i] = way1.localMaxima1D(points)[0];
			PairOfDoubleOrDArr out = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.first((double) ptr), points, outsJIU[i]);
			ans[i] = new Integer[] {out.getFirst().getFirst().intValue(), out.getSecond() == null ? null : out.getSecond().getFirst().intValue()};
			bw.write(Integer.toString(ptr));
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
		Integer[][] pyex = new Integer[iters][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i++) {
			String mids = lst.get(i);
			pyex[ind++] = TestUtils.parsePythonArray(mids);
		}
		assertArrayEquals(pyex, ans);
	}
}
