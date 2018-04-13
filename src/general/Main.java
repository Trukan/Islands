package general;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	static int threadsQuantity = 1;
	static Map map;
	static int tempCount, count;
	

	//@param "filename" xor " numCell numColumn 0|1 0|1 0|1 0|1 ... "
	public static void main(String[] args) {
		if(args.length==0)System.exit(3);
		if(args.length==1) {
			readFile(args[0]);
		}
		if (args.length > 2) {
			map = new Map(args);
		}
			tempCount = 0;
			count = 0;
			islandSearch();
			System.out.println("Quantity islands: " + map.getNumIsland());
			System.out.println("Unique islands: " + map.getNumUniqForm());
	}

	public static void readFile(String filename) {
		FileReader fin;
		int n=0,m=0;
		try {
			fin = new FileReader(filename);
			Scanner sc = new Scanner(fin);
			if (sc.hasNextInt()) {
				n = sc.nextInt();
			}
			if (sc.hasNextInt()) {
				m = sc.nextInt();
			}
			if(n==0|m==0) {
				fin.close();
				sc.close();
				System.out.println("file data error");
				System.exit(2);
			}
			map = new Map(n,m);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(sc.hasNextByte()) {
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

	public static void islandSearch() {
		int n = map.getMtr().length;
		int m = map.getMtr()[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map.getMtr(i, j) == 0)
					continue;
				if (map.getMtr(i, j) == 1)
					checkCell(i, j);
			}
		}
		

	}

	// check Land or not for start overrun
	public static void checkCell(int n, int m) {
		if (m > 0 && map.getMtr(n, m - 1) == 1)
			return;
		if (n > 0 && map.getMtr(n - 1, m) == 1)
			return;
		if (map.getNumCell(n, m) == 0)
			landOverrun(n, m);
	}

	//research landform
	public static void landOverrun(int n, int m) {
		if (map.getNumCell(n, m) == 0) {
			int temp = ++tempCount;
			map.setPat(new Pattern(temp));
			if (landStep("fromLeft", n, m, temp)) {
				count++;
				map.getPat(temp).optimal();
			}else {
				map.removePat(temp);
			}
		} else
			return;
	}

	// step by land, check around and step land again to side if can
	//research island form  
	private static boolean landStep(String from, int n, int m, int numCell) {
		map.getPat(numCell).addCell(n, m);
	//	System.out.println("from:" + from + " " + "n:" + n + " m:" + m + " numCell" + numCell);
		if (map.getNumCell(n, m) == 0 | map.getNumCell(n, m) > numCell)
			map.setNumCell(n, m, numCell);
		else
			return false;
		switch (from) {
		case "fromLeft":

			if (m < map.getMtr()[0].length - 1) {
				if (!checkStep(n, m + 1, "fromLeft", numCell))
					return false;
			}
			if (n < map.getMtr().length - 1) {
				if (!checkStep(n + 1, m, "fromTop", numCell))
					return false;
			}
			if (n > 0) {
				if (!checkStep(n - 1, m, "fromBottom", numCell))
					return false;
			}
			break;
		case "fromTop":

			if (m < map.getMtr()[0].length - 1) {
				if (!checkStep(n, m + 1, "fromLeft", numCell))
					return false;
			}
			if (n < map.getMtr().length - 1) {
				if (!checkStep(n + 1, m, "fromTop", numCell))
					return false;
			}
			if (m > 0) {
				if (!checkStep(n, m - 1, "fromRight", numCell))
					return false;
			}
			break;
		case "fromRight":
			if (n < map.getMtr().length - 1) {
				if (!checkStep(n + 1, m, "fromTop", numCell))
					return false;
			}
			if (m > 0) {
				if (!checkStep(n, m - 1, "fromRight", numCell))
					return false;
			}
			if (n > 0) {
				if (!checkStep(n - 1, m, "fromBottom", numCell))
					return false;
			}
			break;
		case "fromBottom":
			if (m < map.getMtr()[0].length - 1) {
				if (!checkStep(n, m + 1, "fromLeft", numCell))
					return false;
			}
			if (m > 0) {
				if (!checkStep(n, m - 1, "fromRight", numCell))
					return false;
			}
			if (n > 0) {
				if (!checkStep(n - 1, m, "fromBottom", numCell))
					return false;
			}
			break;
		default:
			System.out.println("case default!");
			break;
		}
		return true;
	}

	//check probably step to cell with coordinates (n,m), 
	//if numCell check other thread to decide this collision
	//return false if numCell currentThread > numCell other thread, else continue steping
	private static boolean checkStep(int n, int m, String from, int numCell) {
	//	System.out.println("--from:" + from + " " + "n:" + n + " m:" + m + " numCell" + numCell);
		if (map.getMtr(n, m) == 1) {
			if (map.getNumCell(n, m) == 0 | map.getNumCell(n, m) > numCell) {
				if (!landStep(from, n, m, numCell))
					return false;
			} else
				return false;
		}
		return true;
	}
	public static void setMap(Map m) {
		map=m;
	}
	public static Map getMap() {
		return map;
	}

}
