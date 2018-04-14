package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import general.Main;

class MainTest {



	@Test
	final void testMain() {
		String[] filename1= {"test01.txt"};
		String[] filename2= {"test02.txt"};
		String[] filename3= {"test03.txt"};
		Main.main(filename1);
		assertTrue(Main.getMap().getNumIsland()==5);
		assertTrue(Main.getMap().getNumUniqForm()==5);
		Main.main(filename2);
		assertTrue(Main.getMap().getNumIsland()==21);
		assertTrue(Main.getMap().getNumUniqForm()==5);
	}

}
