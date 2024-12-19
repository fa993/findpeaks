import com.fa993.types.supertype.Either;
import com.fa993.utils.SelectByProperty;
import com.fa993.variations.LocalMaximaJIU;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SelectByPropertyTest {

	private final LocalMaximaJIU way1 = new LocalMaximaJIU();

	@Test
	public void testForNumber() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		int[][] outsJIU = new int[iters][];
		boolean[][] ans = new boolean[iters][];
		List<String> lst = TestUtils.runAgainstPythonScript("test_select_by_property.py", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			double[] ptrs = TestUtils.getRandomPoints(2);
			outsJIU[i] = way1.localMaxima1D(points).getMidpoints();
			ans[i] = SelectByProperty.call(outsJIU[i], Either.OfTwo.first(ptrs[0]), Either.OfTwo.first(ptrs[1]));
			return List.of(
					Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")),
					Double.toString(ptrs[0]),
					Double.toString(ptrs[1])
			);
		});
		boolean[][] pyex = new boolean[iters][];
		int ind = 0;
		for (String mids : lst) {
			pyex[ind++] = TestUtils.parsePythonArrayToBoolean(mids);
		}
		assertArrayEquals(pyex, ans);
	}

	@Test
	public void testForOneOpen() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		int[][] outsJIU = new int[iters][];
		boolean[][] ans = new boolean[iters][];
		List<String> lst = TestUtils.runAgainstPythonScript("test_select_by_property.py", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			double[] ptrs = TestUtils.getRandomPoints(2);
			outsJIU[i] = way1.localMaxima1D(points).getMidpoints();
			ans[i] = SelectByProperty.call(outsJIU[i], null, Either.OfTwo.first(ptrs[1]));
			return List.of(
					Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")),
					"",
					Double.toString(ptrs[1])
			);
		});
		boolean[][] pyex = new boolean[iters][];
		int ind = 0;
		for (String mids : lst) {
			pyex[ind++] = TestUtils.parsePythonArrayToBoolean(mids);
		}
		assertArrayEquals(pyex, ans);
	}

	@Test
	public void testForOtherOpen() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		int[][] outsJIU = new int[iters][];
		boolean[][] ans = new boolean[iters][];
		List<String> lst = TestUtils.runAgainstPythonScript("test_select_by_property.py", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			double[] ptrs = TestUtils.getRandomPoints(2);
			outsJIU[i] = way1.localMaxima1D(points).getMidpoints();
			ans[i] = SelectByProperty.call(outsJIU[i], Either.OfTwo.first(ptrs[0]), null);
			return List.of(
					Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")),
					Double.toString(ptrs[0]),
					""
			);
		});
		boolean[][] pyex = new boolean[iters][];
		int ind = 0;
		for (String mids : lst) {
			pyex[ind++] = TestUtils.parsePythonArrayToBoolean(mids);
		}
		assertArrayEquals(pyex, ans);
	}

	@Test
	public void testForFullBothClose() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		int[][] outsJIU = new int[iters][];
		boolean[][] ans = new boolean[iters][];
		List<String> lst = TestUtils.runAgainstPythonScript("test_select_by_property.py", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			outsJIU[i] = way1.localMaxima1D(points).getMidpoints();
			double[] ptrs = TestUtils.getRandomPoints(2 * outsJIU[i].length);
			double[][] pttrs = TestUtils.reshape(ptrs, 2);
			ans[i] = SelectByProperty.call(outsJIU[i], Either.OfTwo.second(pttrs[0]), Either.OfTwo.second(pttrs[1]));
			return List.of(
					Arrays.stream(outsJIU[i]).mapToObj(Integer::toString).collect(Collectors.joining(" ")),
					Arrays.stream(pttrs[0]).mapToObj(Double::toString).collect(Collectors.joining(" ")),
					Arrays.stream(pttrs[1]).mapToObj(Double::toString).collect(Collectors.joining(" "))
			);
		});
		boolean[][] pyex = new boolean[iters][];
		int ind = 0;
		for (String mids : lst) {
			pyex[ind++] = TestUtils.parsePythonArrayToBoolean(mids);
		}
		assertArrayEquals(pyex, ans);
	}

}
