import com.fa993.utils.SelectByPeakDistance;
import com.fa993.variations.LocalMaxima;
import com.fa993.variations.LocalMaximaJIU;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.RepeatedTest;

public class SelectByPeakDistanceTest {

	//Not needed
//	private final LocalMaxima lm = new LocalMaximaJIU();

//	@RepeatedTest(1000)
//	public void testImplsCCvsGPT() {
//		int count = 1000;
//		double rndH = (TestUtils.getRandomPoints(1)[0] * 10) + 1;
//		double[] points = TestUtils.getRandomPoints(count);
//		int[] peaks = lm.localMaxima1D(points)[0];
//		assertArrayEquals(SelectByPeakDistance.cc(peaks, points, rndH), SelectByPeakDistance.gpt(peaks, points, rndH));
//	}
//
//	@RepeatedTest(1000)
//	public void testImplsCCvsJIU() {
//		int count = 1000;
//		double rndH = (TestUtils.getRandomPoints(1)[0] * 10) + 1;
//		double[] points = TestUtils.getRandomPoints(count);
//		int[] peaks = lm.localMaxima1D(points)[0];
//		assertArrayEquals(SelectByPeakDistance.cc(peaks, points, rndH), SelectByPeakDistance.jiu(peaks, points, rndH));
//	}
//
//	@RepeatedTest(1000)
//	public void testImplsJIUvsGPT() {
//		int count = 1000;
//		double rndH = (TestUtils.getRandomPoints(1)[0] * 10) + 1;
//		double[] points = TestUtils.getRandomPoints(count);
//		int[] peaks = lm.localMaxima1D(points)[0];
//		assertArrayEquals(SelectByPeakDistance.jiu(peaks, points, rndH), SelectByPeakDistance.gpt(peaks, points, rndH));
//	}

}
