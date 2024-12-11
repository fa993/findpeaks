import com.fa993.types.FindPeaksOutput;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FindPeaksOutputTest {
	@Test
	public void testEquals() {
		FindPeaksOutput op1 = new FindPeaksOutput(new int[] {}, new HashMap<>());
		FindPeaksOutput op2 = new FindPeaksOutput(new int[] {}, new HashMap<>());
		assertEquals(op1, op2);
	}

	@Test
	public void testNotEquals() {
		FindPeaksOutput op1 = new FindPeaksOutput(new int[] {1}, new HashMap<>());
		FindPeaksOutput op2 = new FindPeaksOutput(new int[] {}, new HashMap<>());
		assertNotEquals(op1, op2);
	}

	@Test
	public void testNotEquals2() {
		FindPeaksOutput op1 = new FindPeaksOutput(new int[] {1}, new HashMap<>());
		FindPeaksOutput op2 = new FindPeaksOutput(new int[] {2}, new HashMap<>());
		assertNotEquals(op1, op2);
	}

	@Test
	public void testNotEquals3() {
		Map<String, Object> m1 = new HashMap<>();
		Map<String, Object> m2 = new HashMap<>();
		m1.put("asd", "asd");
		m2.put("asd", "bsds");
		FindPeaksOutput op1 = new FindPeaksOutput(new int[] {1}, m1);
		FindPeaksOutput op2 = new FindPeaksOutput(new int[] {1}, m2);
		assertNotEquals(op1, op2);
	}

	@Test
	public void testEquals2() {
		Map<String, Object> m1 = new HashMap<>();
		Map<String, Object> m2 = new HashMap<>();
		m1.put("asd", new int[] {});
		m2.put("asd", new int[] {});
//		assertTrue();
		FindPeaksOutput op1 = new FindPeaksOutput(new int[] {1}, m1);
		FindPeaksOutput op2 = new FindPeaksOutput(new int[] {1}, m2);
		assertEquals(op1, op2);
	}

	@Test
	public void testEquals3() {
		Map<String, Object> m1 = new HashMap<>();
		Map<String, Object> m2 = new HashMap<>();
		m1.put("asd", new int[] {});
		m2.put("asd", new Object[] {});
		FindPeaksOutput op1 = new FindPeaksOutput(new int[] {1}, m1);
		FindPeaksOutput op2 = new FindPeaksOutput(new int[] {1}, m2);
		assertEquals(op1, op2);
	}
}
