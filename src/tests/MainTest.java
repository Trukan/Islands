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
		String[] filename= {"test01.txt"};
		Main.main(filename);
	}

}
