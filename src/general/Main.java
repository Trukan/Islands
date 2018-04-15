package general;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	static int threadsQuantity = 10;
	public static ThreadPool thpool;
	static Map map;
	public static Vector<Pattern> patAr;
	final static byte FROMLEFT = 0;
	final static byte FROMTOP = 1;
	final static byte FROMRIGHT = 2;
	final static byte FROMBOTTOM = 3;

	// @param "filename" xor String[] ={"numCell", "numColumn", "0|1", "0|1", "0|1", "0|1", ...}
	public static void main(String[] args) {
		if (args.length == 0)
			System.exit(3);
		if (args.length == 1) {
			readFile(args[0]);
		}
		if (args.length > 2) {
			map = new Map(args);
		}
		Overrun.setMap(map);
		thpool = new ThreadPool(threadsQuantity);
		patAr = new Vector<Pattern>();
		islandSearch();
		map.setPat(patAr);
		System.out.println("Quantity islands: " + map.getNumIsland());
		System.out.println("Unique islands: " + map.getNumUniqForm());
	}

	public static void islandSearch() {
		int n = map.getMtr().length;
		int m = map.getMtr()[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map.getMtr(i, j) == 0)
					continue;
				if (map.getMtr(i, j) == 1) {
					if (j > 0 && map.getMtr(i, j - 1) == 1)
						continue;
					if (i > 0 && map.getMtr(i - 1, j) == 1)
						continue;
					checkCellandOverrun(i, j);
					
				}
			}
		}
		
		thpool.join();
		
	}

	// check Land or not for start overrun
	public static void checkCellandOverrun(int n, int m) {
		if (map.getNumCell(n, m) == 0) {
	//		 landOverrun(n, m);
			new Overrun(n, m).run();
			Thread temp = thpool.getThread();
			temp = new Thread(new Overrun(n, m), "th" + n + ":" + m);
			temp.start();

		}
	}


	public static void readFile(String filename) {
		FileReader fin;
		int n = 0, m = 0;
		try {
			fin = new FileReader(filename);
			Scanner sc = new Scanner(fin);
			if (sc.hasNextInt()) {
				n = sc.nextInt();
			}
			if (sc.hasNextInt()) {
				m = sc.nextInt();
			}
			if (n == 0 | m == 0) {
				fin.close();
				sc.close();
				System.out.println("file data error");
				System.exit(2);
			}
			map = new Map(n, m);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (sc.hasNextByte()) {
						map.setMtr(i, j, sc.nextByte());
					}
				}
			}
			fin.close();
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("file data error");
			e.printStackTrace();
			System.exit(2);
		}

	}

	public static void setMap(Map m) {
		map = m;
	}

	public static Map getMap() {
		return map;
	}

}
