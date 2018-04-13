package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import general.Main;
import general.Map;

class MapTest {
	byte[][][] testMapByte;
	String[] testMapString;
	
	
	{
		byte[][][] mapByte = { 
				{{0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }},
				
				{{0, 0, 1, 0, 0, 0, 1 }, 
				{ 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 0, 1, 1, 0 }, 
				{ 1, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 1, 0, 0, 1, 0 }, 
				{ 0, 0, 1, 0, 0, 1, 0 }}, //4uniq 7all
				
				{{0, 0, 1, 0, 1, 0, 1 }, 
				{ 0, 1, 1, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 1, 0, 1 }, 
				{ 0, 1, 0, 1, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 1, 0 }, 
				{ 0, 1, 1, 0, 0, 1, 0 }}
		};
		
		testMapByte = mapByte;
		String[] mapString = {"6", "7", 
				"0", "0", "1", "0", "0", "0", "1",
				"0", "1", "0", "0", "1", "0", "1",
				"0", "1", "1", "0", "1", "1", "0",
				"1", "0", "0", "0", "1", "0", "0",
				"0", "0", "1", "0", "0", "1", "0",
				"0", "0", "1", "0", "0", "1", "0" };
		testMapString = mapString;
		
		//for testGetNumUniqForm()
		Map checkedMap = new Map(7,8);
		checkedMap.setMtr(testMapByte[1]);
		Main.setMap(checkedMap);
		Main.islandSearch();
	}

	@Test
	final void testMapIntInt() {	
		Map checkedMap = new Map(7,8);
		for (int i = 0; i < testMapByte[0].length; i++) {
			assertArrayEquals(testMapByte[0][i], checkedMap.getMtr()[i]);
		}
	}

	@Test
	final void testMapStringArray() {	
		Map checkedMap = new Map(testMapString);
		for (int i = 0; i < testMapByte[1].length; i++) {
			assertArrayEquals(testMapByte[1][i], checkedMap.getMtr()[i]);
		}
	}

	@Test
	final void testGetNumUniqForm() {
		
		assertTrue(Main.getMap().getNumUniqForm()==4);
		assertTrue(Main.getMap().getNumIsland()==7);
	}
}
