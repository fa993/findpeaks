import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtils {

	public static Random rand = new Random();

	public static double[] getRandomPoints(int n) {
		double[] arr = new double[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextDouble();
		}
		return arr;
	}

	public static Integer[] parsePythonArrayToInteger(String line) {
		return Arrays.stream(line.substring(1, line.length() - 1).split(",")).map(String::trim).map(t -> "None".equals(t) ? null: Integer.valueOf(t)).collect(Collectors.toList()).toArray(new Integer[]{});
	}

	public static Double[] parsePythonArrayToDouble(String line) {
		return Arrays.stream(line.substring(1, line.length() - 1).split(",")).map(String::trim).map(t -> "None".equals(t) ? null: Double.valueOf(t)).collect(Collectors.toList()).toArray(new Double[]{});
	}

	public static double[][] reshape(double[] base, int firstDim) {
		double[][] newArr = new double[firstDim][base.length / firstDim];
		int k = 0;
		for(int i = 0; i < firstDim; i++) {
			for(int j = 0; j < base.length / firstDim; j++) {
				newArr[i][j] = base[k++];
			}
		}
		return newArr;
	}

	public static boolean[] parsePythonArrayToBoolean(String line) {
		List<Boolean> asd = Arrays.stream(line.substring(1, line.length() - 1).split(",")).map(String::trim).map(String::toLowerCase).map(Boolean::valueOf).collect(Collectors.toList());
		boolean[] b2 = new boolean[asd.size()];
		for(int i = 0; i < asd.size(); i++) {
			b2[i] = asd.get(i);
		}
		return b2;
	}

}
