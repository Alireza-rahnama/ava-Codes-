package factorTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class FactorsTester {
	
	@Test
	void testPerfect1() {
		// TEST 1: should throw the exception because the parameter value is less than 1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.perfect(0));
	}

	@Test
	void testPerfect2() {
		// TEST 2: should succeed because 1 is a valid parameter value, but is not a perfect number
		assertFalse(FactorsUtility.perfect(1));
	}

	@Test
	void testPerfect3() {
		// TEST 3: should succeed because 6 is a valid parameter value, and is a perfect number
		assertTrue(FactorsUtility.perfect(6));
	}

	@Test
	void testPerfect4() {
		// TEST 4: should succeed because 7 is a valid parameter value, but is not a perfect number
		// I've coded this using assertEquals to show that there's often more than one way to write a test
		boolean expected = false;
		assertEquals(expected, FactorsUtility.perfect(7));
	}

	@Test
	void getFactorTest1() {
		//TEST 1 : should succeed because 2 is a valid parameter value  
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		assertEquals(expected, FactorsUtility.getFactors(2));
	}

	@Test
	void getFactorTest2() {
		//TEST 2 : should succeed because 1 is a valid parameter value
		ArrayList<Integer> expected = new ArrayList<Integer>();
		assertEquals(expected, FactorsUtility.getFactors(1));
	}

	@Test
	void getFactorTest3() {
		//TEST 3 : should succeed because 0 is a valid parameter value
		ArrayList<Integer> expected = new ArrayList<Integer>();
		assertEquals(expected, FactorsUtility.getFactors(0));
	}

	@Test
	void getFactorTest4() {
		//TEST 4 : should throw the exception because the parameter value is less than 0
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.getFactors(-1));
	}

	@Test
	void getFactorTest5() {
		//TEST 5 : should succeed because 12 is a valid parameter value
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		expected.add(4);
		expected.add(6);
		assertEquals(expected, FactorsUtility.getFactors(12));
	}

	@Test
	void testFactors1() {
		//TEST 1 : should throw the exception because a is less than 0 and b is less than 1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-2, 0));
	}

	@Test
	void testFactors2() {
		//TEST 2 : should throw the exception because a is less than 0
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-1, 3));
	}

	@Test
	void testFactors3() {
		//TEST 3 : should throw the exception because b is less than 1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(2, 0));
	}

	@Test
	void testFactors4() {
		//TEST 4 : should succeed because 1 and 1 are valid parameter values, is a factor
		boolean expected = true;
		assertEquals(expected, FactorsUtility.factor(1, 1));
	}

	@Test
	void testFactors5() {
		//TEST 5 : should succeed because 7 and 3 are valid parameter values, but is not a factor
		boolean expected = false;
		assertEquals(expected, FactorsUtility.factor(7, 3));
	}

}