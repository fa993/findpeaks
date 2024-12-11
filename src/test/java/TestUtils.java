import com.fa993.types.FindPeaksOutput;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	public static int[] parseSpaceSeparatedPythonIntArray(String line) {
		return Arrays.stream(line.split(" ")).filter(t -> !t.isEmpty()).map(String::trim).mapToInt(Integer::valueOf).toArray();
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

	public static FindPeaksOutput parseFindPeaksOutputFromLines(List<String> lines) {
		// at least one entry
		int[] peaks = parseSpaceSeparatedPythonIntArray(lines.get(0));
		Map<String, Object> props = new HashMap<>();
		// format is first word is key, rest all is vals
		lines.stream().skip(1).forEach(t -> {
			String[] items = t.split(" ");
			String key = items[0];
			if(items.length == 1) {
				props.put(key, new Object[]{});
				return;
			}
			String pot = items[1];
			if (isInteger(pot)) {
				props.put(key, Arrays.stream(items).skip(1).mapToInt(Integer::parseInt).toArray());
			} else if (isDouble(pot)) {
				props.put(key, Arrays.stream(items).skip(1).mapToDouble(Double::parseDouble).toArray());
			} else {
				props.put(key, Arrays.stream(items).skip(1));
			}
		});
		return new FindPeaksOutput(peaks, props);
	}

	public static boolean isDouble(String num) {
		try
		{
			Double.parseDouble(num.trim());
		}
		catch(NumberFormatException e)
		{
			// not a double
			return false;
		}
		return true;
	}

	public static boolean isInteger(String num) {
		try
		{
			Integer.parseInt(num.trim());
		}
		catch(NumberFormatException e)
		{
			// not a double
			return false;
		}
		return true;
	}

	public static List<String> runAgainstShellScript(String scriptName, int iters, Function<Integer, List<String>> producer) throws IOException, InterruptedException {
		File input = new File(System.getProperty("user.dir") + "/input.txt");
		File output = new File(System.getProperty("user.dir") + "/output.txt");
		output.delete();
		input.delete();
		input.createNewFile();
		input.deleteOnExit();
		output.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(input));
		for(int i = 0; i < iters; i++) {
			List<String> l0 = producer.apply(i);
			for(String l : l0) {
				bw.write(l);
				bw.newLine();
			}
		}
		bw.close();
		ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/" + scriptName);
		processBuilder.redirectOutput(output);
		Process process = processBuilder.start();
		int exitCode = process.waitFor();
		assertEquals(0, exitCode);
		BufferedReader br = new BufferedReader(new FileReader(output));
		List<String> lst = br.lines().collect(Collectors.toList());
		input.delete();
		output.delete();
		return lst;
	}

}
