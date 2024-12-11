import com.fa993.types.supertype.NumOrTwoSeqOrNdArr;
import com.fa993.types.supertype.PairOfDoubleOrDArr;
import com.fa993.utils.UnpackConditionArgs;
import com.fa993.variations.LocalMaximaJIU;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class UnpackConditionArgsTest {

	private final LocalMaximaJIU way1 = new LocalMaximaJIU();

	@Test
	public void testAgainstPythonForNumber() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		int[][] outsJIU = new int[iters][];
		Double[][] ans = new Double[iters][];
		List<String> lst = TestUtils.runAgainstShellScript("test_unpack_condition_args.sh", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			double ptr = TestUtils.getRandomPoints(1)[0];
			outsJIU[i] = way1.localMaxima1D(points)[0];
			PairOfDoubleOrDArr out = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.first(ptr), points, outsJIU[i]);
			ans[i] = new Double []{out.getFirst().getFirst(), out.getSecond() == null ? null : out.getSecond().getFirst()};
			return List.of(
					Double.toString(ptr),
					Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")),
					Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" "))
			);
		});
		Double[][] pyex = new Double[iters][];
		int ind = 0;
		for (String mids : lst) {
			pyex[ind++] = TestUtils.parsePythonArrayToDouble(mids);
		}
		assertArrayEquals(pyex, ans);
	}

	@Test
	public void testAgainstPythonForTwoSeq() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		int[][] outsJIU = new int[iters][];
		Double[][] ans = new Double[iters][];
		List<String> lst = TestUtils.runAgainstShellScript("test_unpack_condition_args.sh", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			double[] ptrs = TestUtils.getRandomPoints(2);
			outsJIU[i] = way1.localMaxima1D(points)[0];
			PairOfDoubleOrDArr out = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.second(ptrs), points, outsJIU[i]);
			ans[i] = new Double []{out.getFirst().getFirst(), out.getSecond() == null ? null : out.getSecond().getFirst()};
			return List.of(
					Arrays.stream(ptrs).mapToObj(Double::toString).collect(Collectors.joining(" ")),
					Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")),
					Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" "))
			);
		});
		Double[][] pyex = new Double[iters][];
		int ind = 0;
		for (String mids : lst) {
			pyex[ind++] = TestUtils.parsePythonArrayToDouble(mids);
		}
		assertArrayEquals(pyex, ans);
	}

	@Test
	public void testAgainstPythonForFull() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		int[][] outsJIU = new int[iters][];
		double[][][] ans = new double[iters][][];
		List<String> lst = TestUtils.runAgainstShellScript("test_unpack_condition_args.sh", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			double[] ptrs = TestUtils.getRandomPoints(points.length * 2);
			double[][] pttrs = TestUtils.reshape(ptrs, 2);
			outsJIU[i] = way1.localMaxima1D(points)[0];
			PairOfDoubleOrDArr out = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.third(pttrs), points, outsJIU[i]);
			ans[i] = new double [][]{out.getFirst().getSecond(), out.getSecond().getSecond()};
			return List.of(
					Arrays.stream(ptrs).mapToObj(Double::toString).collect(Collectors.joining(" ")),
					Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")),
					Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" "))
			);
		});
		double[][][] pyex = new double[iters][][];
		int ind = 0;
		for(int i = 0; i < lst.size(); i+=2) {
			String mids = lst.get(i);
			String nexts = lst.get(i + 1);
			pyex[ind++] = new double[][] {Arrays.stream(TestUtils.parsePythonArrayToDouble(mids)).mapToDouble(t -> t).toArray(), Arrays.stream(TestUtils.parsePythonArrayToDouble(nexts)).mapToDouble(t -> t).toArray()};
		}
		assertArrayEquals(pyex, ans);
	}
}
