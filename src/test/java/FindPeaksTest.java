import com.fa993.FindPeaks;
import com.fa993.types.FindPeaksOutput;
import com.fa993.types.supertype.NumOrTwoSeqOrNdArr;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FindPeaksTest {

	private final FindPeaks fp = new FindPeaks();

	@Test
	public void testBasic() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] outs = new FindPeaksOutput[iters];
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] points = TestUtils.getRandomPoints(count);
			outs[i] = fp.call(points);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double rndH = TestUtils.getRandomPoints(1)[0];
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.first(rndH), null, null, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(1, Double.toString(rndH));
			return smLs;
		});
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
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.second(rndH), null, null, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(1, Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(2 * count);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.third(TestUtils.reshape(rndH, 2)), null, null, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(1, Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double rndH = TestUtils.getRandomPoints(1)[0];
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, NumOrTwoSeqOrNdArr.first(rndH));
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(8, Double.toString(rndH));
			return smLs;
		});
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
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, NumOrTwoSeqOrNdArr.second(rndH));
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(8, Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(2 * count);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, null, null, null, null, null, null, NumOrTwoSeqOrNdArr.third(TestUtils.reshape(rndH, 2)));
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(8, Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.first(rndH[0]), null, null, null, null, null, null, NumOrTwoSeqOrNdArr.first(rndH[1]));
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(1, Double.toString(rndH[0]));
			smLs.set(8, Double.toString(rndH[1]));
			return smLs;
		});
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
	public void testWithThreshold() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double rndH = TestUtils.getRandomPoints(1)[0];
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, NumOrTwoSeqOrNdArr.first(rndH), null, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(2, Double.toString(rndH));
			return smLs;
		});
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
	public void testWithThresholdUpperLower() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, NumOrTwoSeqOrNdArr.second(rndH), null, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(2, Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
	public void testWithThresholdAll() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(2 * count);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, NumOrTwoSeqOrNdArr.third(TestUtils.reshape(rndH, 2)), null, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(2, Arrays.stream(rndH).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
	public void testWithHeightAndPlateauAndThreshold() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(3);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.first(rndH[0]), NumOrTwoSeqOrNdArr.first(rndH[1]), null, null, null, null, null, NumOrTwoSeqOrNdArr.first(rndH[2]));
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(1, Double.toString(rndH[0]));
			smLs.set(2, Double.toString(rndH[1]));
			smLs.set(8, Double.toString(rndH[2]));
			return smLs;
		});
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
	public void testWithDistance() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double rndH = (TestUtils.getRandomPoints(1)[0] * 10) + 1;
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, null, rndH, null, null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(3, Double.toString(rndH));
			return smLs;
		});
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
	public void testWithHeightAndPlateauAndDistance() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] rndH = TestUtils.getRandomPoints(3);
			double dis = (TestUtils.getRandomPoints(1)[0] * 10) + 1;
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, NumOrTwoSeqOrNdArr.first(rndH[0]), NumOrTwoSeqOrNdArr.first(rndH[1]), dis, null, null, null, null, NumOrTwoSeqOrNdArr.first(rndH[2]));
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(1, Double.toString(rndH[0]));
			smLs.set(2, Double.toString(rndH[1]));
			smLs.set(3, Double.toString(dis));
			smLs.set(8, Double.toString(rndH[2]));
			return smLs;
		});
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
	public void testWithProminence() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double prom = TestUtils.getRandomPoints(1)[0];
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, null, null, NumOrTwoSeqOrNdArr.first(prom), null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(4, Double.toString(prom));
			return smLs;
		});
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
	public void testWithProminenceSeq() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] prom = TestUtils.getRandomPoints(2);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, null, null, NumOrTwoSeqOrNdArr.second(prom), null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(4, Arrays.stream(prom).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
	public void testWithProminenceFull() throws IOException, InterruptedException {
		int iters = 1000;
		int count = 1000;
		FindPeaksOutput[] overloadOuts = new FindPeaksOutput[iters];
		List<String> lst = TestUtils.runAgainstShellScript("test_find_peaks.sh", iters, (i) -> {
			double[] prom = TestUtils.getRandomPoints(2 * count);
			double[] points = TestUtils.getRandomPoints(count);
			overloadOuts[i] = fp.call(points, null, null, null, NumOrTwoSeqOrNdArr.third(TestUtils.reshape(prom, 2)), null, null, null, null);
			List<String> smLs = Stream.generate(() -> "").limit(9).collect(Collectors.toList());
			smLs.set(0, Arrays.stream(points).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			smLs.set(4, Arrays.stream(prom).mapToObj(Double::toString).collect(Collectors.joining(" ")));
			return smLs;
		});
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
