package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import general.Main;

class MainTest {



	@Test
	final void testMain() {
		String[] filename1= {"test01 5 5.txt"};
		String[] filename2= {"test02 22 6.txt"};
		String[] filename3= {"test03 10404 .txt"};
		String[] filename4= {"test03 41616 .txt"};
		String[] filename5= {"world map1.txt"};
		long temp =  System.currentTimeMillis();
		Main.main(filename1);
		System.out.println(filename1[0]+"  time (sec): \t" + (double)(System.currentTimeMillis()-temp)/1000);
		assertTrue(Main.getMap().getNumIsland()==5);
		assertTrue(Main.getMap().getNumUniqForm()==5);
		temp =  System.currentTimeMillis();
		Main.main(filename2);
		System.out.println(filename2[0]+"  time (sec): \t" + (double)(System.currentTimeMillis()-temp)/1000);
		assertTrue(Main.getMap().getNumIsland()==22);
		assertTrue(Main.getMap().getNumUniqForm()==6);
		temp =  System.currentTimeMillis();
		Main.main(filename3);
		System.out.println(filename3[0]+"  time (sec): \t" + (double)(System.currentTimeMillis()-temp)/1000);
		assertTrue(Main.getMap().getNumIsland()==10404);
		assertTrue(Main.getMap().getNumUniqForm()==11);
		temp =  System.currentTimeMillis();
		Main.main(filename4);
		System.out.println(filename4[0]+"  time (sec): \t" + (double)(System.currentTimeMillis()-temp)/1000);
		assertTrue(Main.getMap().getNumIsland()==41616);
		assertTrue(Main.getMap().getNumUniqForm()==6);
		temp =  System.currentTimeMillis();
		Main.main(filename5);
		System.out.println(filename5[0]+"  time (sec): \t" + (double)(System.currentTimeMillis()-temp)/1000);
	//	assertTrue(Main.getMap().getNumIsland()==);
	}

}
