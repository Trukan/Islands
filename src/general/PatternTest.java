package general;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatternTest {

	Pattern[] testP;

	@BeforeEach
	void setUp() throws Exception {
		testP = new Pattern[5];
		for (int i = 0; i < testP.length; i++) {
			testP[i] = new Pattern(i);
		}
		testP[0].addCell(1, 2);
		testP[0].addCell(2, 3);
		testP[0].addCell(3, 4);
		testP[0].addCell(4, 5);
		testP[0].addCell(5, 6);
		testP[1].addCell(11, 12);
		testP[1].addCell(12, 13);
		testP[1].addCell(13, 14);
		testP[1].addCell(14, 15);
		testP[1].addCell(15, 16);
		testP[2].addCell(1, 2);
		testP[2].addCell(2, 3);
		testP[2].addCell(3, 4);
		testP[2].addCell(4, 5);
		testP[3].addCell(0, 0);
		testP[3].addCell(1, 1);
		testP[3].addCell(2, 2);
		testP[3].addCell(3, 3);
		testP[3].addCell(4, 4);
		testP[4].addCell(0, 0);
		testP[4].addCell(1, 0);
		testP[4].addCell(2, 0);
		testP[4].addCell(3, 0);
		testP[4].addCell(4, 0);

	}

	@Test
	final void testOptimal() {
		testP[0].optimal();
		testP[3].optimal();
			
			assertTrue(testP[3].form.containsAll(testP[0].form));
	}
	@Test
	final void testCompare() {
		testP[0].optimal();
		testP[1].optimal();
		testP[2].optimal();
		testP[3].optimal();
		testP[4].optimal();
		assertTrue(testP[0].equals(testP[1]));
		assertFalse(testP[0].equals(testP[2]));
		assertTrue(testP[0].equals(testP[3]));
		assertFalse(testP[0].equals(testP[4]));
	}

	

}
