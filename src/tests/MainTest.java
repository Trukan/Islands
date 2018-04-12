package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import general.Main;

class MainTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void testMain() {
		String[] mapString = {"6", "7", 
				"0", "0", "1", "0", "0", "0", "1",
				"0", "1", "0", "0", "1", "0", "1",
				"0", "1", "1", "0", "1", "1", "1",
				"1", "1", "0", "0", "1", "0", "0",
				"0", "0", "1", "0", "0", "1", "0",
				"0", "1", "1", "0", "0", "1", "0" };
		Main.main(mapString);
	}

}
